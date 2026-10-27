package com.bumptech.glide.load.k.g;

import android.graphics.Bitmap;
import com.bumptech.glide.i.a;

/* JADX INFO: compiled from: GifBitmapProvider.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements a.InterfaceC0083a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.x.e f3070a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.engine.x.b f3071b;

    public b(com.bumptech.glide.load.engine.x.e eVar, com.bumptech.glide.load.engine.x.b bVar) {
        this.f3070a = eVar;
        this.f3071b = bVar;
    }

    @Override // com.bumptech.glide.i.a.InterfaceC0083a
    public Bitmap a(int i, int i2, Bitmap.Config config) {
        return this.f3070a.e(i, i2, config);
    }

    @Override // com.bumptech.glide.i.a.InterfaceC0083a
    public void b(byte[] bArr) {
        com.bumptech.glide.load.engine.x.b bVar = this.f3071b;
        if (bVar == null) {
            return;
        }
        bVar.put(bArr);
    }

    @Override // com.bumptech.glide.i.a.InterfaceC0083a
    public byte[] c(int i) {
        com.bumptech.glide.load.engine.x.b bVar = this.f3071b;
        return bVar == null ? new byte[i] : (byte[]) bVar.d(i, byte[].class);
    }

    @Override // com.bumptech.glide.i.a.InterfaceC0083a
    public void d(int[] iArr) {
        com.bumptech.glide.load.engine.x.b bVar = this.f3071b;
        if (bVar == null) {
            return;
        }
        bVar.put(iArr);
    }

    @Override // com.bumptech.glide.i.a.InterfaceC0083a
    public int[] e(int i) {
        com.bumptech.glide.load.engine.x.b bVar = this.f3071b;
        return bVar == null ? new int[i] : (int[]) bVar.d(i, int[].class);
    }

    @Override // com.bumptech.glide.i.a.InterfaceC0083a
    public void f(Bitmap bitmap) {
        this.f3070a.d(bitmap);
    }
}
