package com.bumptech.glide.s;

import com.bumptech.glide.load.g;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class a implements g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final a f2529b = new a();

    private a() {
    }

    public static a c() {
        return f2529b;
    }

    @Override // com.bumptech.glide.load.g
    public void a(MessageDigest messageDigest) {
    }

    public String toString() {
        return "EmptySignature";
    }
}
