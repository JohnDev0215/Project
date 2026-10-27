package com.bumptech.glide.load.p;

import android.content.Context;
import com.bumptech.glide.load.m;
import com.bumptech.glide.load.n.v;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class c<T> implements m<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final m<?> f2313b = new c();

    private c() {
    }

    public static <T> c<T> c() {
        return (c) f2313b;
    }

    @Override // com.bumptech.glide.load.g
    public void a(MessageDigest messageDigest) {
    }

    @Override // com.bumptech.glide.load.m
    public v<T> b(Context context, v<T> vVar, int i, int i2) {
        return vVar;
    }
}
