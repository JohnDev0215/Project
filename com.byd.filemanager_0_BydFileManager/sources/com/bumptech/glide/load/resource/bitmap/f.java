package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;

/* JADX INFO: compiled from: BitmapTransformation.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f implements com.bumptech.glide.load.h<Bitmap> {
    @Override // com.bumptech.glide.load.h
    public final com.bumptech.glide.load.engine.s<Bitmap> b(Context context, com.bumptech.glide.load.engine.s<Bitmap> sVar, int i, int i2) {
        if (!com.bumptech.glide.o.k.r(i, i2)) {
            throw new IllegalArgumentException("Cannot apply transformation on width: " + i + " or height: " + i2 + " less than or equal to zero and not Target.SIZE_ORIGINAL");
        }
        com.bumptech.glide.load.engine.x.e eVarF = com.bumptech.glide.c.c(context).f();
        Bitmap bitmap = sVar.get();
        if (i == Integer.MIN_VALUE) {
            i = bitmap.getWidth();
        }
        if (i2 == Integer.MIN_VALUE) {
            i2 = bitmap.getHeight();
        }
        Bitmap bitmapC = c(eVarF, bitmap, i, i2);
        return bitmap.equals(bitmapC) ? sVar : e.f(bitmapC, eVarF);
    }

    protected abstract Bitmap c(com.bumptech.glide.load.engine.x.e eVar, Bitmap bitmap, int i, int i2);
}
