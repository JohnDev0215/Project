package com.fasterxml.jackson.annotation;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class JsonInclude$Include {
    public static final JsonInclude$Include c;
    public static final /* synthetic */ JsonInclude$Include[] e;

    /* JADX INFO: Fake field, exist only in values array */
    JsonInclude$Include EF0;

    static {
        JsonInclude$Include jsonInclude$Include = new JsonInclude$Include("ALWAYS", 0);
        JsonInclude$Include jsonInclude$Include2 = new JsonInclude$Include("NON_NULL", 1);
        JsonInclude$Include jsonInclude$Include3 = new JsonInclude$Include("NON_ABSENT", 2);
        JsonInclude$Include jsonInclude$Include4 = new JsonInclude$Include("NON_EMPTY", 3);
        JsonInclude$Include jsonInclude$Include5 = new JsonInclude$Include("NON_DEFAULT", 4);
        JsonInclude$Include jsonInclude$Include6 = new JsonInclude$Include("CUSTOM", 5);
        JsonInclude$Include jsonInclude$Include7 = new JsonInclude$Include("USE_DEFAULTS", 6);
        c = jsonInclude$Include7;
        e = new JsonInclude$Include[]{jsonInclude$Include, jsonInclude$Include2, jsonInclude$Include3, jsonInclude$Include4, jsonInclude$Include5, jsonInclude$Include6, jsonInclude$Include7};
    }

    public static JsonInclude$Include valueOf(String str) {
        return (JsonInclude$Include) Enum.valueOf(JsonInclude$Include.class, str);
    }

    public static JsonInclude$Include[] values() {
        return (JsonInclude$Include[]) e.clone();
    }
}
