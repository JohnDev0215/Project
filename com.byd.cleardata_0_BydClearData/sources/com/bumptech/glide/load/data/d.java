package com.bumptech.glide.load.data;

/* JADX INFO: loaded from: classes.dex */
public interface d<T> {

    public interface a<T> {
        void d(Exception exc);

        void f(T t);
    }

    Class<T> a();

    void b();

    com.bumptech.glide.load.a c();

    void cancel();

    void e(com.bumptech.glide.g gVar, a<? super T> aVar);
}
