package org.example.dataframe;

import org.example.dataframe.exceptions.TypeValidationException;
import org.example.dataframe.transformations.ColumnCondition;
import org.example.dataframe.transformations.TransformationMethods;
import org.example.structure.ColumnTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class DataFrame implements TransformationMethods {
    private List<Column> df = new ArrayList<>();
    private Map<String,Integer> columnRegistry = new HashMap<>();
    public DataFrame(Schema schema, List<Object>... columnValues) {
        if (schema.getSchema().size() != columnValues.length) {
            throw new IllegalArgumentException("Schema and column values count do not match");
        }

        int i = 0;
        for (Map.Entry<String, ColumnTypes> entry : schema.getSchema().entrySet()) {
            String colName = entry.getKey();
            ColumnTypes type = entry.getValue();
            List<Object> values = columnValues[i];

            validateColumnValues(type, values);
            df.add(new Column(type,colName, values));
            columnRegistry.put(colName,i);
            i++;
        }
    }
    public Column getColumn(String columnName){
        return df.get(columnRegistry.get(columnName));
    }
    private void validateColumnValues(ColumnTypes type,List<Object> values){
        for (Object v:values){
            if (!type.getPrivateType().isInstance(v)) {
                System.out.println("Checking value: " + v + " (" + (v == null ? "null" : v.getClass().getName()) +
                        ") against type: " + type.getPrivateType().getName());

                throw new TypeValidationException("Type provided in schema does not correspond with values in column");
            }
        }
    }

    public void describe(){
        for(Column c : df){
            System.out.printf("%s -> %s%n",c.getColumnName(),c.getType());
        }
    }
    public void show(){
        List<String> headers = new ArrayList<>(columnRegistry.keySet());
        int numRows = df.get(0).getValues().size();
        for(String h: headers){
            System.out.printf("%-10s",h);
        }
        System.out.println();
        for (int row = 0; row < numRows; row++) {
            for (String header : headers) {
                Column col = df.get(columnRegistry.get(header));
                Object value = col.getValues().get(row);
                System.out.printf("%-10s", value != null ? value.toString() : "null");
            }
            System.out.println();
        }
    }

    @Override
    public DataFrame filter(Predicate<Column> predicate) {
        return null;
    }
    public ColumnCondition where(String column) {
        return new ColumnCondition(column);
    }

    @Override
    public DataFrame filter(Column col, Predicate<Integer> predicate) {
        var a = col.getValues();
        List<Boolean> booleanMask = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
                booleanMask.add(predicate.test((Integer) a.get(i)));
        }
        df.forEach(listItem -> {
            List<?> originalValues = listItem.getValues();
            List<Object> mutableCopy = new ArrayList<>(originalValues);

            for (int j = booleanMask.size() - 1; j >= 0; j--) {
                if (Boolean.FALSE.equals(booleanMask.get(j))) {
                    mutableCopy.remove(j);
                }
            }
            listItem.setValues(mutableCopy); // assumes you have a setter
        });

        return this;
    }

    private DataFrame createDataFrame(Column c,List<Object> list){
        return new DataFrame(new Schema().addColumn("name",ColumnTypes.BOOLEAN),list);
    }


    @Override
    public DataFrame map(Predicate<Map<String, Column>> predicate) {
        return null;
    }
}
