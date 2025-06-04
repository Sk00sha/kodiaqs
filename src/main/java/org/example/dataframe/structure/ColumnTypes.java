package org.example.dataframe.structure;

public enum ColumnTypes {
    STRING(String.class),
    INTEGER(Integer.class),
    DOUBLE(Double.class),
    BOOLEAN(Boolean.class);

    private final Class<?> privateType;

    ColumnTypes(Class<?> privateType) {
        this.privateType = privateType;
    }

    public Class<?> getPrivateType() {
        return privateType;
    }
}
