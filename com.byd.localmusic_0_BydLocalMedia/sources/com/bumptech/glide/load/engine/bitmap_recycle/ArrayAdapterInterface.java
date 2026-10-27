package com.bumptech.glide.load.engine.bitmap_recycle;

/* JADX INFO: loaded from: classes3.dex */
interface ArrayAdapterInterface<T> {
    int getArrayLength(T t);

    int getElementSizeInBytes();

    String getTag();

    T newArray(int i);
}
