package com.bumptech.glide.load.k.g;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.s;
import java.security.MessageDigest;

/* JADX INFO: compiled from: GifDrawableTransformation.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements com.bumptech.glide.load.h<c> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.h<Bitmap> f3076b;

    public f(com.bumptech.glide.load.h<Bitmap> hVar) {
        this.f3076b = (com.bumptech.glide.load.h) com.bumptech.glide.m.j.d(hVar);
    }

    @Override // com.bumptech.glide.load.h
    public s<c> a(Context context, s<c> sVar, int i, int i2) {
        c cVar = sVar.get();
        s<Bitmap> eVar = new com.bumptech.glide.load.resource.bitmap.e(cVar.e(), com.bumptech.glide.b.c(context).f());
        s<Bitmap> sVarA = this.f3076b.a(context, eVar, i, i2);
        if (!eVar.equals(sVarA)) {
            eVar.a();
        }
        cVar.m(this.f3076b, sVarA.get());
        return sVar;
    }

    @Override // com.bumptech.glide.load.c
    public void b(MessageDigest messageDigest) {
        this.f3076b.b(messageDigest);
    }

    @Override // com.bumptech.glide.load.c
    public boolean equals(Object obj) {
        if (obj instanceof f) {
            return this.f3076b.equals(((f) obj).f3076b);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.c
    public int hashCode() {
        return this.f3076b.hashCode();
    }
}
