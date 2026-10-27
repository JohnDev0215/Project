package com.apollographql.apollo3.api.http;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class HttpMethod {
    public static final HttpMethod c;
    public static final HttpMethod e;
    public static final /* synthetic */ HttpMethod[] k;

    static {
        HttpMethod httpMethod = new HttpMethod("Get", 0);
        c = httpMethod;
        HttpMethod httpMethod2 = new HttpMethod("Post", 1);
        e = httpMethod2;
        k = new HttpMethod[]{httpMethod, httpMethod2};
    }

    public static HttpMethod valueOf(String str) {
        return (HttpMethod) Enum.valueOf(HttpMethod.class, str);
    }

    public static HttpMethod[] values() {
        return (HttpMethod[]) k.clone();
    }
}
