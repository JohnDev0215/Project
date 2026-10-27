package com.bumptech.glide.load.p.d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public class l implements com.bumptech.glide.load.m<Drawable> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.m<Bitmap> f2343b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f2344c;

    public l(com.bumptech.glide.load.m<Bitmap> mVar, boolean z) {
        this.f2343b = mVar;
        this.f2344c = z;
    }

    private com.bumptech.glide.load.n.v<Drawable> d(Context context, com.bumptech.glide.load.n.v<Bitmap> vVar) {
        return q.g(context.getResources(), vVar);
    }

    @Override // com.bumptech.glide.load.g
    public void a(MessageDigest messageDigest) {
        this.f2343b.a(messageDigest);
    }

    @Override // com.bumptech.glide.load.m
    public com.bumptech.glide.load.n.v<Drawable> b(Context context, com.bumptech.glide.load.n.v<Drawable> vVar, int i, int i2) {
        com.bumptech.glide.load.n.a0.e eVarF = com.bumptech.glide.c.c(context).f();
        Drawable drawableB = vVar.b();
        com.bumptech.glide.load.n.v<Bitmap> vVarA = k.a(eVarF, drawableB, i, i2);
        if (vVarA != null) {
            com.bumptech.glide.load.n.v<Bitmap> vVarB = this.f2343b.b(context, vVarA, i, i2);
            if (!vVarB.equals(vVarA)) {
                return d(context, vVarB);
            }
            vVarB.f();
            return vVar;
        }
        if (!this.f2344c) {
            return vVar;
        }
        throw new IllegalArgumentException("Unable to convert " + drawableB + " to a Bitmap");
    }

    public com.bumptech.glide.load.m<BitmapDrawable> c() {
        return this;
    }

    @Override // com.bumptech.glide.load.g
    public boolean equals(Object obj) {
        if (obj instanceof l) {
            return this.f2343b.equals(((l) obj).f2343b);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.g
    public int hashCode() {
        return this.f2343b.hashCode();
    }
}
