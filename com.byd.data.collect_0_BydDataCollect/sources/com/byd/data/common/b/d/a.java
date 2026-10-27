package com.byd.data.common.b.d;

import com.google.gson.Gson;
import f.f0;
import f.h0;
import h.h;
import h.u;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Objects;

/* JADX INFO: compiled from: MyGsonConverterFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends h.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Gson f3296a;

    private a(Gson gson) {
        this.f3296a = gson;
    }

    public static a f(Gson gson) {
        Objects.requireNonNull(gson, "gson == null");
        return new a(gson);
    }

    @Override // h.h.a
    public h<?, f0> c(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, u uVar) {
        return new b(this.f3296a, this.f3296a.k(com.google.gson.v.a.b(type)));
    }

    @Override // h.h.a
    public h<h0, ?> d(Type type, Annotation[] annotationArr, u uVar) {
        return new c(this.f3296a, this.f3296a.k(com.google.gson.v.a.b(type)));
    }
}
