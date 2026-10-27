package com.bumptech.glide.request;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;

/* JADX INFO: compiled from: RequestListener.java */
/* JADX INFO: loaded from: classes.dex */
public interface d<R> {
    boolean a(R r, Object obj, com.bumptech.glide.request.h.e<R> eVar, DataSource dataSource, boolean z);

    boolean b(GlideException glideException, Object obj, com.bumptech.glide.request.h.e<R> eVar, boolean z);
}
