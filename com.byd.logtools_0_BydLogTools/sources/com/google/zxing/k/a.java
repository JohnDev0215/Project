package com.google.zxing.k;

import com.google.zxing.common.f;
import com.google.zxing.i;

/* JADX INFO: compiled from: AztecDetectorResult.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f4295c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f4296d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f4297e;

    public a(com.google.zxing.common.b bVar, i[] iVarArr, boolean z, int i, int i2) {
        super(bVar, iVarArr);
        this.f4295c = z;
        this.f4296d = i;
        this.f4297e = i2;
    }

    public int c() {
        return this.f4296d;
    }

    public int d() {
        return this.f4297e;
    }

    public boolean e() {
        return this.f4295c;
    }
}
