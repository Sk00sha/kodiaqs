package org.example.dataframe.transformations;

import java.util.List;
import java.util.function.Predicate;

public class ColumnCondition {
    private final String column;

    public ColumnCondition(String column){
        this.column = column;
    }
    public Predicate<Object> gt(int value) {
        return item -> item instanceof Number &&
                ((Integer)item > value);
    }
}
