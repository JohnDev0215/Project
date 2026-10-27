package com.bumptech.glide;

import com.bumptech.glide.g;

/* JADX INFO: compiled from: TransitionOptions.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class g<CHILD extends g<CHILD, TranscodeType>, TranscodeType> implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.bumptech.glide.request.i.c<? super TranscodeType> f2662b = com.bumptech.glide.request.i.a.a();

    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final CHILD clone() {
        try {
            return (CHILD) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    final com.bumptech.glide.request.i.c<? super TranscodeType> b() {
        return this.f2662b;
    }
}
