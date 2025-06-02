package org.example.dataframe;

import org.example.dataframe.exceptions.DuplicateColumnException;
import org.example.structure.ColumnTypes;

import java.util.LinkedHashMap;
import java.util.Map;

public class Schema {
    private final LinkedHashMap<String, ColumnTypes> schema = new LinkedHashMap<>();

    public Schema addColumn(String columnName, ColumnTypes type) {
        if (schema.containsKey(columnName)) {
            throw new DuplicateColumnException("Schema already contains",columnName);
        }
        schema.put(columnName, type);
        return this;
    }

    public Map<String, ColumnTypes> getSchema() {
        return schema;
    }
}