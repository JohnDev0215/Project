package com.bumptech.glide.load.resource.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.io.IOException;

/* JADX INFO: compiled from: BitmapDrawableDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public class a<DataType> implements com.bumptech.glide.load.f<DataType, BitmapDrawable> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.load.f<DataType, Bitmap> f3347a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Resources f3348b;

    public a(Resources resources, com.bumptech.glide.load.f<DataType, Bitmap> fVar) {
        this.f3348b = (Resources) com.bumptech.glide.o.j.d(resources);
        this.f3347a = (com.bumptech.glide.load.f) com.bumptech.glide.o.j.d(fVar);
    }

    @Override // com.bumptech.glide.load.f
    public com.bumptech.glide.load.engine.s<BitmapDrawable> a(DataType datatype, int i, int i2, com.bumptech.glide.load.e eVar) throws IOException {
        return q.f(this.f3348b, this.f3347a.a(datatype, i, i2, eVar));
    }

    @Override // com.bumptech.glide.load.f
    public boolean b(DataType datatype, com.bumptech.glide.load.e eVar) throws IOException {
        return this.f3347a.b(datatype, eVar);
    }
}
