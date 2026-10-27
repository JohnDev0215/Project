package com.bumptech.glide.load.data;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;

/* JADX INFO: compiled from: DataFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public interface d<T> {

    /* JADX INFO: compiled from: DataFetcher.java */
    public interface a<T> {
        void d(Exception exc);

        void f(T t);
    }

    Class<T> a();

    void b();

    DataSource c();

    void cancel();

    void e(Priority priority, a<? super T> aVar);
}
