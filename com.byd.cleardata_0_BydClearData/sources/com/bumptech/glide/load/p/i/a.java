package com.bumptech.glide.load.p.i;

import android.graphics.Bitmap;
import com.bumptech.glide.load.i;
import com.bumptech.glide.load.n.v;
import java.io.ByteArrayOutputStream;

/* JADX INFO: loaded from: classes.dex */
public class a implements e<Bitmap, byte[]> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Bitmap.CompressFormat f2409a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f2410b;

    public a() {
        this(Bitmap.CompressFormat.JPEG, 100);
    }

    public a(Bitmap.CompressFormat compressFormat, int i) {
        this.f2409a = compressFormat;
        this.f2410b = i;
    }

    @Override // com.bumptech.glide.load.p.i.e
    public v<byte[]> a(v<Bitmap> vVar, i iVar) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        vVar.b().compress(this.f2409a, this.f2410b, byteArrayOutputStream);
        vVar.f();
        return new com.bumptech.glide.load.p.e.b(byteArrayOutputStream.toByteArray());
    }
}
