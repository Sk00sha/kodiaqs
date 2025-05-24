package org.example.dataframe;

import org.example.structure.ColumnTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DataFrame {
    private Map<String,Column> df = new HashMap<>();
    public DataFrame(Map<String, ColumnTypes> schema,List<Object> ...columnValues){
        if (schema.size() != columnValues.length) {
            throw new IllegalArgumentException("Schema size and number of column value lists must match.");
        }
        int i=0;
        for (Map.Entry<String, ColumnTypes> entry : schema.entrySet()) {
            String name = entry.getKey();
            ColumnTypes type = entry.getValue();
            List<Object> values = columnValues[i++];
            df.put(name,new Column(type,values));
        }


    }
    public void describe(){
        var es = df.entrySet();
        for(Map.Entry<String,Column> entry: es){
            System.out.printf("%s -> %s%n",entry.getKey(),entry.getValue().getType());
        }
    }
    public void show(){
        List<String> headers = new ArrayList<>(df.keySet());
        int numRows = df.values().iterator().next().getValues().size();
        for(String h: headers){
            System.out.printf("%-10s",h);
        }
        System.out.println();
        for (int row = 0; row < numRows; row++) {
            for (String header : headers) {
                Column col = df.get(header);
                Object value = col.getValues().get(row);
                System.out.printf("%-10s", value != null ? value.toString() : "null");
            }
            System.out.println();
        }
    }
}
