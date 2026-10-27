package com.amazon.avod.media.framework.resources;

/* JADX INFO: loaded from: classes2.dex */
public interface ResourcePool<E> {
    void releaseResource(E e);

    E requestResource();
}
