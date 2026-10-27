package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.security.MessageDigest;

/* JADX INFO: compiled from: DrawableTransformation.java */
/* JADX INFO: loaded from: classes.dex */
public class j implements com.bumptech.glide.load.h<Drawable> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.h<Bitmap> f3129b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f3130c;

    public j(com.bumptech.glide.load.h<Bitmap> hVar, boolean z) {
        this.f3129b = hVar;
        this.f3130c = z;
    }

    private com.bumptech.glide.load.engine.s<Drawable> d(Context context, com.bumptech.glide.load.engine.s<Bitmap> sVar) {
        return o.e(context.getResources(), sVar);
    }

    @Override // com.bumptech.glide.load.h
    public com.bumptech.glide.load.engine.s<Drawable> a(Context context, com.bumptech.glide.load.engine.s<Drawable> sVar, int i, int i2) {
        com.bumptech.glide.load.engine.x.e eVarF = com.bumptech.glide.b.c(context).f();
        Drawable drawable = sVar.get();
        com.bumptech.glide.load.engine.s<Bitmap> sVarA = i.a(eVarF, drawable, i, i2);
        if (sVarA != null) {
            com.bumptech.glide.load.engine.s<Bitmap> sVarA2 = this.f3129b.a(context, sVarA, i, i2);
            if (!sVarA2.equals(sVarA)) {
                return d(context, sVarA2);
            }
            sVarA2.a();
            return sVar;
        }
        if (!this.f3130c) {
            return sVar;
        }
        throw new IllegalArgumentException("Unable to convert " + drawable + " to a Bitmap");
    }

    @Override // com.bumptech.glide.load.c
    public void b(MessageDigest messageDigest) {
        this.f3129b.b(messageDigest);
    }

    public com.bumptech.glide.load.h<BitmapDrawable> c() {
        return this;
    }

    @Override // com.bumptech.glide.load.c
    public boolean equals(Object obj) {
        if (obj instanceof j) {
            return this.f3129b.equals(((j) obj).f3129b);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.c
    public int hashCode() {
        return this.f3129b.hashCode();
    }
}
