package com.fasterxml.jackson.annotation;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class JsonFormat$Shape {
    public static final JsonFormat$Shape c;
    public static final /* synthetic */ JsonFormat$Shape[] e;

    static {
        JsonFormat$Shape jsonFormat$Shape = new JsonFormat$Shape("ANY", 0);
        c = jsonFormat$Shape;
        e = new JsonFormat$Shape[]{jsonFormat$Shape, new JsonFormat$Shape("NATURAL", 1), new JsonFormat$Shape("SCALAR", 2), new JsonFormat$Shape("ARRAY", 3), new JsonFormat$Shape("OBJECT", 4), new JsonFormat$Shape("NUMBER", 5), new JsonFormat$Shape("NUMBER_FLOAT", 6), new JsonFormat$Shape("NUMBER_INT", 7), new JsonFormat$Shape("STRING", 8), new JsonFormat$Shape("BOOLEAN", 9), new JsonFormat$Shape("BINARY", 10)};
    }

    public static JsonFormat$Shape valueOf(String str) {
        return (JsonFormat$Shape) Enum.valueOf(JsonFormat$Shape.class, str);
    }

    public static JsonFormat$Shape[] values() {
        return (JsonFormat$Shape[]) e.clone();
    }
}
