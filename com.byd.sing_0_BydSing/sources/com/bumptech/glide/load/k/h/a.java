package com.bumptech.glide.load.k.h;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.s;
import java.io.ByteArrayOutputStream;

/* JADX INFO: compiled from: BitmapBytesTranscoder.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements e<Bitmap, byte[]> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Bitmap.CompressFormat f3088a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f3089b;

    public a() {
        this(Bitmap.CompressFormat.JPEG, 100);
    }

    @Override // com.bumptech.glide.load.k.h.e
    public s<byte[]> a(s<Bitmap> sVar, com.bumptech.glide.load.e eVar) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        sVar.get().compress(this.f3088a, this.f3089b, byteArrayOutputStream);
        sVar.a();
        return new com.bumptech.glide.load.k.d.b(byteArrayOutputStream.toByteArray());
    }

    public a(Bitmap.CompressFormat compressFormat, int i) {
        this.f3088a = compressFormat;
        this.f3089b = i;
    }
}
