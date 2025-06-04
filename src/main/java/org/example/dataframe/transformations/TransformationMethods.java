package org.example.dataframe.transformations;

import org.example.dataframe.Column;
import org.example.dataframe.DataFrame;

import java.util.Map;
import java.util.function.Predicate;

public interface TransformationMethods {
    DataFrame filter(Column column, Predicate<Integer> predicate);

    DataFrame groupBy(Column colum, GroupByMethod method);

    DataFrame map(Predicate<Map<String, Column>> predicate);
}
