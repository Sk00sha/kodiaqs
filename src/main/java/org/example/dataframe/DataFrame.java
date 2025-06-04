package org.example.dataframe;

import org.example.dataframe.exceptions.TypeValidationException;
import org.example.dataframe.transformations.ColumnCondition;
import org.example.dataframe.transformations.GroupByMethod;
import org.example.dataframe.transformations.TransformationMethods;
import org.example.structure.ColumnTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
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

    public ColumnCondition where(String column) {
        return new ColumnCondition(column);
    }

    @Override
    public DataFrame filter(Column col, Predicate<Integer> predicate) {
        List<?> rawValues = col.getValues();
        List<Integer> indexesToKeep = new ArrayList<>();
        for (int i = 0; i < rawValues.size(); i++) {
            Object value = rawValues.get(i);
            if (value instanceof Integer && predicate.test((Integer) value)) {
                indexesToKeep.add(i);
            }
        }
        for (Column column : df) {
            List<?> originalValues = column.getValues();
            List<Object> filteredValues = new ArrayList<>(indexesToKeep.size());

            for (int index : indexesToKeep) {
                filteredValues.add(originalValues.get(index));
            }

            column.setValues(filteredValues); // assumes setter exists
        }

        return this;
    }

    @Override
    public DataFrame groupBy(Column colum, GroupByMethod method) {
        switch (method){
            case AVG -> {
                Map<String,Integer> produce = new HashMap<>();
                AtomicInteger sum = new AtomicInteger();
                colum.getValues().forEach(item-> sum.addAndGet((Integer) item));
                colum.getValues().size();
            }
        }
        return null;
    }


    private DataFrame createDataFrame(Column c,List<Object> list){
        return new DataFrame(new Schema().addColumn("name",ColumnTypes.BOOLEAN),list);
    }


    @Override
    public DataFrame map(Predicate<Map<String, Column>> predicate) {
        return null;
    }
}
