package org.example.dataframe.service;

import org.example.dataframe.Column;
import org.example.dataframe.DataFrame;
import org.example.dataframe.structure.Schema;
import org.example.dataframe.exceptions.TypeValidationException;
import org.example.dataframe.structure.ColumnTypes;

import java.util.List;
import java.util.Map;

public class DataFrameService {
    public static DataFrame createDataFame(Schema schema, List<Object>... columnValues){
        if (schema.getSchema().size() != columnValues.length) {
            throw new IllegalArgumentException("Schema and column values count do not match");
        }

        int i = 0;
        for (Map.Entry<String, ColumnTypes> entry : schema.getSchema().entrySet()) {
            String colName = entry.getKey();
            ColumnTypes type = entry.getValue();
            List<Object> values = columnValues[i];

            validateColumnValues(type, values);
            //df.add(new Column(type,colName, values));
            //columnRegistry.put(colName,i);
            i++;
        }
        return null;
    }
    private static void validateColumnValues(ColumnTypes type, List<Object> values){
        for (Object v:values){
            if (!type.getPrivateType().isInstance(v)) {
                System.out.println("Checking value: " + v + " (" + (v == null ? "null" : v.getClass().getName()) +
                        ") against type: " + type.getPrivateType().getName());

                throw new TypeValidationException("Type provided in schema does not correspond with values in column");
            }
        }
    }
}
