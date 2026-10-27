package com.byd.data.common.b.d;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import f.f0;
import f.z;
import h.h;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import okio.Buffer;

/* JADX INFO: compiled from: MyGsonRequestBodyConverter.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<T> implements h<T, f0> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final z f3297a = z.d("application/json; charset=UTF-8");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Gson f3298b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final TypeAdapter<T> f3299c;

    b(Gson gson, TypeAdapter<T> typeAdapter) {
        this.f3298b = gson;
        this.f3299c = typeAdapter;
    }

    @Override // h.h
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public f0 a(T t) throws IOException {
        Buffer buffer = new Buffer();
        com.google.gson.w.c cVarP = this.f3298b.p(new OutputStreamWriter(buffer.Y(), StandardCharsets.UTF_8));
        this.f3299c.write(cVarP, t);
        cVarP.close();
        return f0.e(f3297a, buffer.a0());
    }
}
