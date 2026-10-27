package com.bumptech.glide.load.j.g;

import android.graphics.Bitmap;
import com.bumptech.glide.k.a;

/* JADX INFO: compiled from: GifBitmapProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements a.InterfaceC0060a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.x.e f3300a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.x.b f3301b;

    public b(com.bumptech.glide.load.engine.x.e eVar, com.bumptech.glide.load.engine.x.b bVar) {
        this.f3300a = eVar;
        this.f3301b = bVar;
    }

    @Override // com.bumptech.glide.k.a.InterfaceC0060a
    public int[] a(int i) {
        com.bumptech.glide.load.engine.x.b bVar = this.f3301b;
        return bVar == null ? new int[i] : (int[]) bVar.e(i, int[].class);
    }

    @Override // com.bumptech.glide.k.a.InterfaceC0060a
    public Bitmap b(int i, int i2, Bitmap.Config config) {
        return this.f3300a.e(i, i2, config);
    }

    @Override // com.bumptech.glide.k.a.InterfaceC0060a
    public void c(byte[] bArr) {
        com.bumptech.glide.load.engine.x.b bVar = this.f3301b;
        if (bVar == null) {
            return;
        }
        bVar.d(bArr);
    }

    @Override // com.bumptech.glide.k.a.InterfaceC0060a
    public void d(Bitmap bitmap) {
        this.f3300a.d(bitmap);
    }

    @Override // com.bumptech.glide.k.a.InterfaceC0060a
    public byte[] e(int i) {
        com.bumptech.glide.load.engine.x.b bVar = this.f3301b;
        return bVar == null ? new byte[i] : (byte[]) bVar.e(i, byte[].class);
    }

    @Override // com.bumptech.glide.k.a.InterfaceC0060a
    public void f(int[] iArr) {
        com.bumptech.glide.load.engine.x.b bVar = this.f3301b;
        if (bVar == null) {
            return;
        }
        bVar.d(iArr);
    }
}
