package com.bumptech.glide;

import com.bumptech.glide.l;

/* JADX INFO: loaded from: classes.dex */
public abstract class l<CHILD extends l<CHILD, TranscodeType>, TranscodeType> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.bumptech.glide.r.k.c<? super TranscodeType> f1958b = com.bumptech.glide.r.k.a.a();

    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final CHILD clone() {
        try {
            return (CHILD) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    final com.bumptech.glide.r.k.c<? super TranscodeType> b() {
        return this.f1958b;
    }
}
