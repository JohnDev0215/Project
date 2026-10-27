package com.fasterxml.jackson.core;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class JsonParser$NumberType {
    public static final JsonParser$NumberType c;
    public static final /* synthetic */ JsonParser$NumberType[] e;

    static {
        JsonParser$NumberType jsonParser$NumberType = new JsonParser$NumberType("INT", 0);
        c = jsonParser$NumberType;
        e = new JsonParser$NumberType[]{jsonParser$NumberType, new JsonParser$NumberType("LONG", 1), new JsonParser$NumberType("BIG_INTEGER", 2), new JsonParser$NumberType("FLOAT", 3), new JsonParser$NumberType("DOUBLE", 4), new JsonParser$NumberType("BIG_DECIMAL", 5)};
    }

    public static JsonParser$NumberType valueOf(String str) {
        return (JsonParser$NumberType) Enum.valueOf(JsonParser$NumberType.class, str);
    }

    public static JsonParser$NumberType[] values() {
        return (JsonParser$NumberType[]) e.clone();
    }
}
