package com.apollographql.apollo3.api.json;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class JsonReader$Token {
    public static final JsonReader$Token c;
    public static final JsonReader$Token e;
    public static final JsonReader$Token k;
    public static final JsonReader$Token l;
    public static final JsonReader$Token m;
    public static final JsonReader$Token n;
    public static final JsonReader$Token o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final JsonReader$Token f26p;
    public static final JsonReader$Token q;
    public static final JsonReader$Token r;
    public static final JsonReader$Token s;
    public static final /* synthetic */ JsonReader$Token[] t;

    static {
        JsonReader$Token jsonReader$Token = new JsonReader$Token("BEGIN_ARRAY", 0);
        c = jsonReader$Token;
        JsonReader$Token jsonReader$Token2 = new JsonReader$Token("END_ARRAY", 1);
        e = jsonReader$Token2;
        JsonReader$Token jsonReader$Token3 = new JsonReader$Token("BEGIN_OBJECT", 2);
        k = jsonReader$Token3;
        JsonReader$Token jsonReader$Token4 = new JsonReader$Token("END_OBJECT", 3);
        l = jsonReader$Token4;
        JsonReader$Token jsonReader$Token5 = new JsonReader$Token("NAME", 4);
        m = jsonReader$Token5;
        JsonReader$Token jsonReader$Token6 = new JsonReader$Token("STRING", 5);
        n = jsonReader$Token6;
        JsonReader$Token jsonReader$Token7 = new JsonReader$Token("NUMBER", 6);
        o = jsonReader$Token7;
        JsonReader$Token jsonReader$Token8 = new JsonReader$Token("LONG", 7);
        f26p = jsonReader$Token8;
        JsonReader$Token jsonReader$Token9 = new JsonReader$Token("BOOLEAN", 8);
        q = jsonReader$Token9;
        JsonReader$Token jsonReader$Token10 = new JsonReader$Token("NULL", 9);
        r = jsonReader$Token10;
        JsonReader$Token jsonReader$Token11 = new JsonReader$Token("END_DOCUMENT", 10);
        s = jsonReader$Token11;
        t = new JsonReader$Token[]{jsonReader$Token, jsonReader$Token2, jsonReader$Token3, jsonReader$Token4, jsonReader$Token5, jsonReader$Token6, jsonReader$Token7, jsonReader$Token8, jsonReader$Token9, jsonReader$Token10, jsonReader$Token11};
    }

    public static JsonReader$Token valueOf(String str) {
        return (JsonReader$Token) Enum.valueOf(JsonReader$Token.class, str);
    }

    public static JsonReader$Token[] values() {
        return (JsonReader$Token[]) t.clone();
    }
}
