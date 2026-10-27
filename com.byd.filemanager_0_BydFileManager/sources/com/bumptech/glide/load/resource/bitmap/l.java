package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.security.MessageDigest;

/* JADX INFO: compiled from: DrawableTransformation.java */
/* JADX INFO: loaded from: classes.dex */
public class l implements com.bumptech.glide.load.h<Drawable> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.h<Bitmap> f3366b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f3367c;

    public l(com.bumptech.glide.load.h<Bitmap> hVar, boolean z) {
        this.f3366b = hVar;
        this.f3367c = z;
    }

    private com.bumptech.glide.load.engine.s<Drawable> d(Context context, com.bumptech.glide.load.engine.s<Bitmap> sVar) {
        return q.f(context.getResources(), sVar);
    }

    @Override // com.bumptech.glide.load.c
    public void a(MessageDigest messageDigest) {
        this.f3366b.a(messageDigest);
    }

    @Override // com.bumptech.glide.load.h
    public com.bumptech.glide.load.engine.s<Drawable> b(Context context, com.bumptech.glide.load.engine.s<Drawable> sVar, int i, int i2) {
        com.bumptech.glide.load.engine.x.e eVarF = com.bumptech.glide.c.c(context).f();
        Drawable drawable = sVar.get();
        com.bumptech.glide.load.engine.s<Bitmap> sVarA = k.a(eVarF, drawable, i, i2);
        if (sVarA != null) {
            com.bumptech.glide.load.engine.s<Bitmap> sVarB = this.f3366b.b(context, sVarA, i, i2);
            if (!sVarB.equals(sVarA)) {
                return d(context, sVarB);
            }
            sVarB.a();
            return sVar;
        }
        if (!this.f3367c) {
            return sVar;
        }
        throw new IllegalArgumentException("Unable to convert " + drawable + " to a Bitmap");
    }

    public com.bumptech.glide.load.h<BitmapDrawable> c() {
        return this;
    }

    @Override // com.bumptech.glide.load.c
    public boolean equals(Object obj) {
        if (obj instanceof l) {
            return this.f3366b.equals(((l) obj).f3366b);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.c
    public int hashCode() {
        return this.f3366b.hashCode();
    }
}
