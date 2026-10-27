package com.spotify.remoteconfig.commons;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class AssignedPropertyValue$ValueType {
    public static final AssignedPropertyValue$ValueType c;
    public static final AssignedPropertyValue$ValueType e;
    public static final AssignedPropertyValue$ValueType k;
    public static final AssignedPropertyValue$ValueType l;
    public static final /* synthetic */ AssignedPropertyValue$ValueType[] m;

    static {
        AssignedPropertyValue$ValueType assignedPropertyValue$ValueType = new AssignedPropertyValue$ValueType("BOOL_VALUE", 0);
        c = assignedPropertyValue$ValueType;
        AssignedPropertyValue$ValueType assignedPropertyValue$ValueType2 = new AssignedPropertyValue$ValueType("INT_VALUE", 1);
        e = assignedPropertyValue$ValueType2;
        AssignedPropertyValue$ValueType assignedPropertyValue$ValueType3 = new AssignedPropertyValue$ValueType("ENUM_VALUE", 2);
        k = assignedPropertyValue$ValueType3;
        AssignedPropertyValue$ValueType assignedPropertyValue$ValueType4 = new AssignedPropertyValue$ValueType("NOT_SET", 3);
        l = assignedPropertyValue$ValueType4;
        m = new AssignedPropertyValue$ValueType[]{assignedPropertyValue$ValueType, assignedPropertyValue$ValueType2, assignedPropertyValue$ValueType3, assignedPropertyValue$ValueType4};
    }

    public static AssignedPropertyValue$ValueType valueOf(String str) {
        return (AssignedPropertyValue$ValueType) Enum.valueOf(AssignedPropertyValue$ValueType.class, str);
    }

    public static AssignedPropertyValue$ValueType[] values() {
        return (AssignedPropertyValue$ValueType[]) m.clone();
    }
}
