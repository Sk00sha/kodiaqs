package org.example.dataframe;

import org.example.dataframe.exceptions.TypeValidationException;
import org.example.structure.ColumnTypes;

import java.util.ArrayList;
import java.util.List;

public class Column {
    private final ColumnTypes type;
    private final String columnName;
    private List<Object> values;
    public Column(ColumnTypes t, String columnName, List<Object> values){
        this.type = t;
        this.columnName = columnName;
        this.values = values;
    }

    public ColumnTypes getType() {
        return type;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    public List<Object> getValues() {
        return values;
    }
}
