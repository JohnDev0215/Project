package com.bumptech.glide.load.resource.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.io.IOException;

/* JADX INFO: compiled from: BitmapDrawableDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class a<DataType> implements com.bumptech.glide.load.f<DataType, BitmapDrawable> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.load.f<DataType, Bitmap> f3112a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Resources f3113b;

    public a(Resources resources, com.bumptech.glide.load.f<DataType, Bitmap> fVar) {
        this.f3113b = (Resources) com.bumptech.glide.m.j.d(resources);
        this.f3112a = (com.bumptech.glide.load.f) com.bumptech.glide.m.j.d(fVar);
    }

    @Override // com.bumptech.glide.load.f
    public com.bumptech.glide.load.engine.s<BitmapDrawable> a(DataType datatype, int i, int i2, com.bumptech.glide.load.e eVar) throws IOException {
        return o.e(this.f3113b, this.f3112a.a(datatype, i, i2, eVar));
    }

    @Override // com.bumptech.glide.load.f
    public boolean b(DataType datatype, com.bumptech.glide.load.e eVar) throws IOException {
        return this.f3112a.b(datatype, eVar);
    }
}
