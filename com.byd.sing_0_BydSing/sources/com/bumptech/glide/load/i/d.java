package com.bumptech.glide.load.i;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;

/* JADX INFO: compiled from: DataFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public interface d<T> {

    /* JADX INFO: compiled from: DataFetcher.java */
    public interface a<T> {
        void c(Exception exc);

        void d(T t);
    }

    Class<T> a();

    void b();

    void cancel();

    DataSource e();

    void f(Priority priority, a<? super T> aVar);
}
