package org.example.dataframe.transformations;

import java.util.function.Predicate;

public class FilterFunction {
    public static Predicate<Object> isEqualTo(String s){
        return o -> {
            String o1 = (String) o;
            return o1.equals(s);
        };
    }
    public static Predicate<Object> isEqualTo(Integer i){
        return o -> {
            Integer o1 = castInt(o);
            System.out.println("Trying "+o1 + " "+i);
            return o1.intValue() == i.intValue();
        };
    }
    public static Predicate<Object> isGreaterThan(Integer i){
        return o -> {
            Integer o1 = castInt(o);
            return o1 > i;
        };
    }
    public static Predicate<Object> isSmallerThan(Integer i){
        return o -> {
            Integer o1 = castInt(o);
            return o1 < i;
        };
    }
    private static int castInt(Object o){
        return (Integer) o;
    }
}
