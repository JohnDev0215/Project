package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.FormatException;

/* JADX INFO: compiled from: DecodedNumeric.java */
/* JADX INFO: loaded from: classes.dex */
final class o extends p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f4411b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4412c;

    o(int i, int i2, int i3) throws FormatException {
        super(i);
        if (i2 < 0 || i2 > 10 || i3 < 0 || i3 > 10) {
            throw FormatException.getFormatInstance();
        }
        this.f4411b = i2;
        this.f4412c = i3;
    }

    int b() {
        return this.f4411b;
    }

    int c() {
        return this.f4412c;
    }

    boolean d() {
        return this.f4411b == 10;
    }

    boolean e() {
        return this.f4412c == 10;
    }
}
