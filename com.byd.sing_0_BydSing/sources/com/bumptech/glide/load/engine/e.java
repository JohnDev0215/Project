package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.DataSource;

/* JADX INFO: compiled from: DataFetcherGenerator.java */
/* JADX INFO: loaded from: classes.dex */
interface e {

    /* JADX INFO: compiled from: DataFetcherGenerator.java */
    public interface a {
        void a();

        void b(com.bumptech.glide.load.c cVar, Exception exc, com.bumptech.glide.load.i.d<?> dVar, DataSource dataSource);

        void c(com.bumptech.glide.load.c cVar, Object obj, com.bumptech.glide.load.i.d<?> dVar, DataSource dataSource, com.bumptech.glide.load.c cVar2);
    }

    void cancel();

    boolean e();
}
