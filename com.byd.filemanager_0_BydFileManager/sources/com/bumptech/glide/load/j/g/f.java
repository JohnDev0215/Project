package com.bumptech.glide.load.j.g;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.s;
import java.security.MessageDigest;

/* JADX INFO: compiled from: GifDrawableTransformation.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements com.bumptech.glide.load.h<c> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.h<Bitmap> f3307b;

    public f(com.bumptech.glide.load.h<Bitmap> hVar) {
        this.f3307b = (com.bumptech.glide.load.h) com.bumptech.glide.o.j.d(hVar);
    }

    @Override // com.bumptech.glide.load.c
    public void a(MessageDigest messageDigest) {
        this.f3307b.a(messageDigest);
    }

    @Override // com.bumptech.glide.load.h
    public s<c> b(Context context, s<c> sVar, int i, int i2) {
        c cVar = sVar.get();
        s<Bitmap> eVar = new com.bumptech.glide.load.resource.bitmap.e(cVar.e(), com.bumptech.glide.c.c(context).f());
        s<Bitmap> sVarB = this.f3307b.b(context, eVar, i, i2);
        if (!eVar.equals(sVarB)) {
            eVar.a();
        }
        cVar.m(this.f3307b, sVarB.get());
        return sVar;
    }

    @Override // com.bumptech.glide.load.c
    public boolean equals(Object obj) {
        if (obj instanceof f) {
            return this.f3307b.equals(((f) obj).f3307b);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.c
    public int hashCode() {
        return this.f3307b.hashCode();
    }
}
