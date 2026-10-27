package com.google.zxing.oned.rss.expanded.decoders;

/* JADX INFO: compiled from: DecodedInformation.java */
/* JADX INFO: loaded from: classes.dex */
final class n extends p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f4408b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4409c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f4410d;

    n(int i, String str) {
        super(i);
        this.f4408b = str;
        this.f4410d = false;
        this.f4409c = 0;
    }

    String b() {
        return this.f4408b;
    }

    int c() {
        return this.f4409c;
    }

    boolean d() {
        return this.f4410d;
    }

    n(int i, String str, int i2) {
        super(i);
        this.f4410d = true;
        this.f4409c = i2;
        this.f4408b = str;
    }
}
