package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import java.io.IOException;

/* JADX INFO: compiled from: ParcelFileDescriptorBitmapDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class r implements com.bumptech.glide.load.f<ParcelFileDescriptor, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final j f3382a;

    public r(j jVar) {
        this.f3382a = jVar;
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public com.bumptech.glide.load.engine.s<Bitmap> a(ParcelFileDescriptor parcelFileDescriptor, int i, int i2, com.bumptech.glide.load.e eVar) throws IOException {
        return this.f3382a.d(parcelFileDescriptor, i, i2, eVar);
    }

    @Override // com.bumptech.glide.load.f
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(ParcelFileDescriptor parcelFileDescriptor, com.bumptech.glide.load.e eVar) {
        return this.f3382a.o(parcelFileDescriptor);
    }
}
