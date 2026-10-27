package com.bumptech.glide.load.k;

import android.content.Context;
import com.bumptech.glide.load.engine.s;
import com.bumptech.glide.load.h;
import java.security.MessageDigest;

/* JADX INFO: compiled from: UnitTransformation.java */
/* JADX INFO: loaded from: classes.dex */
public final class c<T> implements h<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final h<?> f3059b = new c();

    private c() {
    }

    public static <T> c<T> c() {
        return (c) f3059b;
    }

    @Override // com.bumptech.glide.load.h
    public s<T> a(Context context, s<T> sVar, int i, int i2) {
        return sVar;
    }

    @Override // com.bumptech.glide.load.c
    public void b(MessageDigest messageDigest) {
    }
}
