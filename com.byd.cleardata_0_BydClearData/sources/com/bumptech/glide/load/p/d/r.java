package com.bumptech.glide.load.p.d;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;

/* JADX INFO: loaded from: classes.dex */
public final class r implements com.bumptech.glide.load.k<ParcelFileDescriptor, Bitmap> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final j f2358a;

    public r(j jVar) {
        this.f2358a = jVar;
    }

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public com.bumptech.glide.load.n.v<Bitmap> a(ParcelFileDescriptor parcelFileDescriptor, int i, int i2, com.bumptech.glide.load.i iVar) {
        return this.f2358a.d(parcelFileDescriptor, i, i2, iVar);
    }

    @Override // com.bumptech.glide.load.k
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public boolean b(ParcelFileDescriptor parcelFileDescriptor, com.bumptech.glide.load.i iVar) {
        return this.f2358a.o(parcelFileDescriptor);
    }
}
