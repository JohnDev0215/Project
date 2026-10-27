package com.google.zxing;

import okhttp3.HttpUrl;

/* JADX INFO: compiled from: BinaryBitmap.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final a f4187a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.google.zxing.common.b f4188b;

    public b(a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("Binarizer must be non-null.");
        }
        this.f4187a = aVar;
    }

    public com.google.zxing.common.b a() throws NotFoundException {
        if (this.f4188b == null) {
            this.f4188b = this.f4187a.b();
        }
        return this.f4188b;
    }

    public com.google.zxing.common.a b(int i, com.google.zxing.common.a aVar) throws NotFoundException {
        return this.f4187a.c(i, aVar);
    }

    public int c() {
        return this.f4187a.d();
    }

    public int d() {
        return this.f4187a.f();
    }

    public boolean e() {
        return this.f4187a.e().f();
    }

    public b f() {
        return new b(this.f4187a.a(this.f4187a.e().g()));
    }

    public String toString() {
        try {
            return a().toString();
        } catch (NotFoundException unused) {
            return HttpUrl.FRAGMENT_ENCODE_SET;
        }
    }
}
