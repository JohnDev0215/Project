package com.bumptech.glide.load.p.h;

import android.graphics.Bitmap;
import com.bumptech.glide.n.a;

/* JADX INFO: loaded from: classes.dex */
public final class b implements a.InterfaceC0084a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final com.bumptech.glide.load.n.a0.e f2391a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.bumptech.glide.load.n.a0.b f2392b;

    public b(com.bumptech.glide.load.n.a0.e eVar, com.bumptech.glide.load.n.a0.b bVar) {
        this.f2391a = eVar;
        this.f2392b = bVar;
    }

    @Override // com.bumptech.glide.n.a.InterfaceC0084a
    public int[] a(int i) {
        com.bumptech.glide.load.n.a0.b bVar = this.f2392b;
        return bVar == null ? new int[i] : (int[]) bVar.e(i, int[].class);
    }

    @Override // com.bumptech.glide.n.a.InterfaceC0084a
    public Bitmap b(int i, int i2, Bitmap.Config config) {
        return this.f2391a.e(i, i2, config);
    }

    @Override // com.bumptech.glide.n.a.InterfaceC0084a
    public void c(byte[] bArr) {
        com.bumptech.glide.load.n.a0.b bVar = this.f2392b;
        if (bVar == null) {
            return;
        }
        bVar.d(bArr);
    }

    @Override // com.bumptech.glide.n.a.InterfaceC0084a
    public void d(Bitmap bitmap) {
        this.f2391a.d(bitmap);
    }

    @Override // com.bumptech.glide.n.a.InterfaceC0084a
    public byte[] e(int i) {
        com.bumptech.glide.load.n.a0.b bVar = this.f2392b;
        return bVar == null ? new byte[i] : (byte[]) bVar.e(i, byte[].class);
    }

    @Override // com.bumptech.glide.n.a.InterfaceC0084a
    public void f(int[] iArr) {
        com.bumptech.glide.load.n.a0.b bVar = this.f2392b;
        if (bVar == null) {
            return;
        }
        bVar.d(iArr);
    }
}
