package com.google.zxing.n.r;

import com.google.zxing.i;

/* JADX INFO: compiled from: FinderPattern.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f4365a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int[] f4366b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final i[] f4367c;

    public c(int i, int[] iArr, int i2, int i3, int i4) {
        this.f4365a = i;
        this.f4366b = iArr;
        float f2 = i4;
        this.f4367c = new i[]{new i(i2, f2), new i(i3, f2)};
    }

    public i[] a() {
        return this.f4367c;
    }

    public int[] b() {
        return this.f4366b;
    }

    public int c() {
        return this.f4365a;
    }

    public boolean equals(Object obj) {
        return (obj instanceof c) && this.f4365a == ((c) obj).f4365a;
    }

    public int hashCode() {
        return this.f4365a;
    }
}
