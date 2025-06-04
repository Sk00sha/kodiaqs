package org.example;

import org.example.dataframe.DataFrame;
import org.example.dataframe.structure.Schema;
import org.example.dataframe.transformations.FilterFunction;
import org.example.dataframe.structure.ColumnTypes;
import org.example.dataframe.transformations.GroupByMethod;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Schema schema = new Schema()
                .addColumn("names", ColumnTypes.STRING)
                .addColumn("age", ColumnTypes.INTEGER)
                .addColumn("isDriver",ColumnTypes.BOOLEAN);

        var df = new DataFrame(
                schema,
                List.of("Maggie", "Maggie","Mark"),
                List.of(55, 32,19),
                List.of(true,true,true)
        );

        var df2 = df.groupBy(df.getColumn("names"), GroupByMethod.COUNT);
        df2.show();
    }
}