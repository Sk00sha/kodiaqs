package org.example.dataframe;

import org.example.structure.ColumnTypes;

import java.util.List;

public class Column {
    private final ColumnTypes type;
    private final List<Object> values;
    public Column( ColumnTypes t, List<Object> values){

        this.type = t;
        this.values = values;
    }


    public ColumnTypes getType() {
        return type;
    }

    public List<Object> getValues() {
        return values;
    }
}
