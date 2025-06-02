package org.example.dataframe.transformations;

import org.example.dataframe.Column;
import org.example.dataframe.DataFrame;

import java.util.Map;
import java.util.function.Predicate;

public interface TransformationMethods {
    public DataFrame filter(Predicate<Column> predicate);

    DataFrame filter(Column column, Predicate<Integer> predicate);

    public DataFrame map(Predicate<Map<String, Column>> predicate);
}
