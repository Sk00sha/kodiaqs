package org.example;

import org.example.dataframe.Column;
import org.example.dataframe.DataFrame;
import org.example.dataframe.Schema;
import org.example.readers.Reader;
import org.example.runner.Environment;
import org.example.structure.ColumnTypes;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Schema schema = new Schema()
                .addColumn("names", ColumnTypes.STRING)
                .addColumn("age", ColumnTypes.INTEGER)
                .addColumn("isDriver",ColumnTypes.BOOLEAN);

        var df = new DataFrame(
                schema,
                List.of("Maggie", "Jenny","Mark"),
                List.of(55, 32,19),
                List.of(true,true,true)
        );
        DataFrame df2 = df.filter(df.getColumn("age"),column -> column>20);
        df2.show();
        df.describe();
    }
}