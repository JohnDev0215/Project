package com.google.zxing.n;

import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;

/* JADX INFO: compiled from: UPCEANExtensionSupport.java */
/* JADX INFO: loaded from: classes.dex */
final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f4346a = {1, 1, 2};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final m f4347b = new m();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final n f4348c = new n();

    o() {
    }

    com.google.zxing.h a(int i, com.google.zxing.common.a aVar, int i2) throws NotFoundException {
        int[] iArrM = p.m(aVar, i2, false, f4346a);
        try {
            return this.f4348c.b(i, aVar, iArrM);
        } catch (ReaderException unused) {
            return this.f4347b.b(i, aVar, iArrM);
        }
    }
}
