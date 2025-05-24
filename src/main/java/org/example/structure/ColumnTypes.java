package org.example.structure;

public enum ColumnTypes {
    /*
    Types supported by columns
     */
    STRING(String.class),
    INTEGER(Integer.class),
    LONG(Long.class),
    DOUBLE(Double.class),
    BOOLEAN(Boolean.class);
    private Class<?> privateType;
    ColumnTypes(Class<?> type) {
        privateType = type;
    }
    public Class<?> getPrivateType() {
        return privateType;
    }
}
