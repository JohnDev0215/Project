package com.google.gson.stream;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class JsonToken {
    public static final JsonToken c;
    public static final JsonToken e;
    public static final JsonToken k;
    public static final JsonToken l;
    public static final JsonToken m;
    public static final JsonToken n;
    public static final JsonToken o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final JsonToken f40p;
    public static final JsonToken q;
    public static final JsonToken r;
    public static final /* synthetic */ JsonToken[] s;

    static {
        JsonToken jsonToken = new JsonToken("BEGIN_ARRAY", 0);
        c = jsonToken;
        JsonToken jsonToken2 = new JsonToken("END_ARRAY", 1);
        e = jsonToken2;
        JsonToken jsonToken3 = new JsonToken("BEGIN_OBJECT", 2);
        k = jsonToken3;
        JsonToken jsonToken4 = new JsonToken("END_OBJECT", 3);
        l = jsonToken4;
        JsonToken jsonToken5 = new JsonToken("NAME", 4);
        m = jsonToken5;
        JsonToken jsonToken6 = new JsonToken("STRING", 5);
        n = jsonToken6;
        JsonToken jsonToken7 = new JsonToken("NUMBER", 6);
        o = jsonToken7;
        JsonToken jsonToken8 = new JsonToken("BOOLEAN", 7);
        f40p = jsonToken8;
        JsonToken jsonToken9 = new JsonToken("NULL", 8);
        q = jsonToken9;
        JsonToken jsonToken10 = new JsonToken("END_DOCUMENT", 9);
        r = jsonToken10;
        s = new JsonToken[]{jsonToken, jsonToken2, jsonToken3, jsonToken4, jsonToken5, jsonToken6, jsonToken7, jsonToken8, jsonToken9, jsonToken10};
    }

    public static JsonToken valueOf(String str) {
        return (JsonToken) Enum.valueOf(JsonToken.class, str);
    }

    public static JsonToken[] values() {
        return (JsonToken[]) s.clone();
    }
}
