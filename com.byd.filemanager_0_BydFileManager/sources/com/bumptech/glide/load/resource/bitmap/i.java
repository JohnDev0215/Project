package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import java.security.MessageDigest;

/* JADX INFO: compiled from: CenterCrop.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final byte[] f3359b = "com.bumptech.glide.load.resource.bitmap.CenterCrop".getBytes(com.bumptech.glide.load.c.f2926a);

    @Override // com.bumptech.glide.load.c
    public void a(MessageDigest messageDigest) {
        messageDigest.update(f3359b);
    }

    @Override // com.bumptech.glide.load.resource.bitmap.f
    protected Bitmap c(com.bumptech.glide.load.engine.x.e eVar, Bitmap bitmap, int i, int i2) {
        return u.b(eVar, bitmap, i, i2);
    }

    @Override // com.bumptech.glide.load.c
    public boolean equals(Object obj) {
        return obj instanceof i;
    }

    @Override // com.bumptech.glide.load.c
    public int hashCode() {
        return -599754482;
    }
}
