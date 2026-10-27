package com.fasterxml.jackson.databind.type;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class LogicalType {
    public static final LogicalType c;
    public static final LogicalType e;
    public static final LogicalType k;
    public static final /* synthetic */ LogicalType[] l;

    /* JADX INFO: Fake field, exist only in values array */
    LogicalType EF0;

    static {
        LogicalType logicalType = new LogicalType("Array", 0);
        LogicalType logicalType2 = new LogicalType("Collection", 1);
        LogicalType logicalType3 = new LogicalType("Map", 2);
        LogicalType logicalType4 = new LogicalType("POJO", 3);
        LogicalType logicalType5 = new LogicalType("Untyped", 4);
        LogicalType logicalType6 = new LogicalType("Integer", 5);
        c = logicalType6;
        LogicalType logicalType7 = new LogicalType("Float", 6);
        e = logicalType7;
        LogicalType logicalType8 = new LogicalType("Boolean", 7);
        k = logicalType8;
        l = new LogicalType[]{logicalType, logicalType2, logicalType3, logicalType4, logicalType5, logicalType6, logicalType7, logicalType8, new LogicalType("Enum", 8), new LogicalType("Textual", 9), new LogicalType("Binary", 10), new LogicalType("DateTime", 11), new LogicalType("OtherScalar", 12)};
    }

    public static LogicalType valueOf(String str) {
        return (LogicalType) Enum.valueOf(LogicalType.class, str);
    }

    public static LogicalType[] values() {
        return (LogicalType[]) l.clone();
    }
}
