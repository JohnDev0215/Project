package com.bumptech.glide;

import com.bumptech.glide.l;

/* JADX INFO: compiled from: TransitionOptions.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class l<CHILD extends l<CHILD, TranscodeType>, TranscodeType> implements Cloneable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private i2.e<? super TranscodeType> f4016e = i2.c.c();

    private CHILD c() {
        return this;
    }

    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final CHILD clone() {
        try {
            return (CHILD) super.clone();
        } catch (CloneNotSupportedException e7) {
            throw new RuntimeException(e7);
        }
    }

    final i2.e<? super TranscodeType> b() {
        return this.f4016e;
    }

    public final CHILD d(i2.e<? super TranscodeType> eVar) {
        this.f4016e = (i2.e) k2.j.d(eVar);
        return (CHILD) c();
    }
}
