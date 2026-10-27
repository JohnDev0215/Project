package com.bumptech.glide.n;

import com.bumptech.glide.load.c;
import java.security.MessageDigest;

/* JADX INFO: compiled from: EmptySignature.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final a f3443b = new a();

    private a() {
    }

    public static a c() {
        return f3443b;
    }

    @Override // com.bumptech.glide.load.c
    public void a(MessageDigest messageDigest) {
    }

    public String toString() {
        return "EmptySignature";
    }
}
