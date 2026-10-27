package com.bumptech.glide.load.j.h;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.bumptech.glide.load.engine.s;
import com.bumptech.glide.load.resource.bitmap.q;
import com.bumptech.glide.o.j;

/* JADX INFO: compiled from: BitmapDrawableTranscoder.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements e<Bitmap, BitmapDrawable> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Resources f3323a;

    public b(Resources resources) {
        this.f3323a = (Resources) j.d(resources);
    }

    @Override // com.bumptech.glide.load.j.h.e
    public s<BitmapDrawable> a(s<Bitmap> sVar, com.bumptech.glide.load.e eVar) {
        return q.f(this.f3323a, sVar);
    }
}
