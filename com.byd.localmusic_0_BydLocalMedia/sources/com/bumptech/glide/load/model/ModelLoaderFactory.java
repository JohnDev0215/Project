package com.bumptech.glide.load.model;

/* JADX INFO: loaded from: classes4.dex */
public interface ModelLoaderFactory<T, Y> {
    ModelLoader<T, Y> build(MultiModelLoaderFactory multiModelLoaderFactory);

    void teardown();
}
