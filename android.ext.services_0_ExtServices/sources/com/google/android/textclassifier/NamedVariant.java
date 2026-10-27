package com.google.android.textclassifier;

/* JADX INFO: loaded from: classes.dex */
public final class NamedVariant {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int TYPE_BOOL = 5;
    public static final int TYPE_DOUBLE = 4;
    public static final int TYPE_EMPTY = 0;
    public static final int TYPE_FLOAT = 3;
    public static final int TYPE_FLOAT_ARRAY = 8;
    public static final int TYPE_INT = 1;
    public static final int TYPE_INT_ARRAY = 9;
    public static final int TYPE_LONG = 2;
    public static final int TYPE_NAMED_VARIANT_ARRAY = 10;
    public static final int TYPE_STRING = 6;
    public static final int TYPE_STRING_ARRAY = 7;
    private boolean boolValue;
    private double doubleValue;
    private float[] floatArrValue;
    private float floatValue;
    private int[] intArrValue;
    private int intValue;
    private long longValue;
    private final String name;
    private NamedVariant[] namedVariantArray;
    private String[] stringArrValue;
    private String stringValue;
    private final int type = 4;

    public NamedVariant(String str, int i) {
        this.name = str;
        this.intValue = i;
    }

    public NamedVariant(String str, long j) {
        this.name = str;
        this.longValue = j;
    }

    public NamedVariant(String str, float f) {
        this.name = str;
        this.floatValue = f;
    }

    public NamedVariant(String str, double d) {
        this.name = str;
        this.doubleValue = d;
    }

    public NamedVariant(String str, boolean z) {
        this.name = str;
        this.boolValue = z;
    }

    public NamedVariant(String str, String str2) {
        this.name = str;
        this.stringValue = str2;
    }

    public NamedVariant(String str, String[] strArr) {
        this.name = str;
        this.stringArrValue = strArr;
    }

    public NamedVariant(String str, float[] fArr) {
        this.name = str;
        this.floatArrValue = fArr;
    }

    public NamedVariant(String str, int[] iArr) {
        this.name = str;
        this.intArrValue = iArr;
    }

    public NamedVariant(String str, NamedVariant[] namedVariantArr) {
        this.name = str;
        this.namedVariantArray = namedVariantArr;
    }

    public String getName() {
        return this.name;
    }

    public int getType() {
        return this.type;
    }

    public int getInt() {
        return this.intValue;
    }

    public long getLong() {
        return this.longValue;
    }

    public float getFloat() {
        return this.floatValue;
    }

    public double getDouble() {
        return this.doubleValue;
    }

    public boolean getBool() {
        return this.boolValue;
    }

    public String getString() {
        return this.stringValue;
    }

    public String[] getStringArray() {
        return this.stringArrValue;
    }

    public float[] getFloatArray() {
        return this.floatArrValue;
    }

    public int[] getIntArray() {
        return this.intArrValue;
    }

    public NamedVariant[] getNamedVariantArray() {
        return this.namedVariantArray;
    }
}
