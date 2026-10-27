package com.byd.logtools.auth.e;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.s;

/* JADX INFO: compiled from: RetrofitClient.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static s a(String str) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        builder.connectTimeout(10L, timeUnit).writeTimeout(10L, timeUnit).readTimeout(10L, timeUnit);
        return new s.b().b(str).a(retrofit2.x.a.a.f()).f(builder.build()).d();
    }
}
