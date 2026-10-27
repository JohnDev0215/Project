package com.google.zxing.qrcode.decoder;

import com.google.zxing.i;

/* JADX INFO: compiled from: QRCodeDecoderMetaData.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f4498a;

    f(boolean z) {
        this.f4498a = z;
    }

    public void a(i[] iVarArr) {
        if (!this.f4498a || iVarArr == null || iVarArr.length < 3) {
            return;
        }
        i iVar = iVarArr[0];
        iVarArr[0] = iVarArr[2];
        iVarArr[2] = iVar;
    }
}
