package com.google.zxing.qrcode.decoder;

/* JADX INFO: loaded from: classes.dex */
public enum ErrorCorrectionLevel {
    L(1),
    /* JADX INFO: Fake field, exist only in values array */
    M(0),
    /* JADX INFO: Fake field, exist only in values array */
    Q(3),
    /* JADX INFO: Fake field, exist only in values array */
    H(2);

    private final int bits;

    ErrorCorrectionLevel(int i) {
        this.bits = i;
    }

    public final int a() {
        return this.bits;
    }
}
