package com.bumptech.glide.load.j.h;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.s;
import java.io.ByteArrayOutputStream;

/* JADX INFO: compiled from: BitmapBytesTranscoder.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements e<Bitmap, byte[]> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Bitmap.CompressFormat f3321a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f3322b;

    public a() {
        this(Bitmap.CompressFormat.JPEG, 100);
    }

    @Override // com.bumptech.glide.load.j.h.e
    public s<byte[]> a(s<Bitmap> sVar, com.bumptech.glide.load.e eVar) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        sVar.get().compress(this.f3321a, this.f3322b, byteArrayOutputStream);
        sVar.a();
        return new com.bumptech.glide.load.j.d.b(byteArrayOutputStream.toByteArray());
    }

    public a(Bitmap.CompressFormat compressFormat, int i) {
        this.f3321a = compressFormat;
        this.f3322b = i;
    }
}
