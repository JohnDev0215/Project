package com.google.zxing.p.c;

import java.lang.reflect.Array;
import java.util.Arrays;

/* JADX INFO: compiled from: ByteMatrix.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final byte[][] f4426a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f4427b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4428c;

    public b(int i, int i2) {
        this.f4426a = (byte[][]) Array.newInstance((Class<?>) byte.class, i2, i);
        this.f4427b = i;
        this.f4428c = i2;
    }

    public void a(byte b2) {
        for (byte[] bArr : this.f4426a) {
            Arrays.fill(bArr, b2);
        }
    }

    public byte b(int i, int i2) {
        return this.f4426a[i2][i];
    }

    public byte[][] c() {
        return this.f4426a;
    }

    public int d() {
        return this.f4428c;
    }

    public int e() {
        return this.f4427b;
    }

    public void f(int i, int i2, int i3) {
        this.f4426a[i2][i] = (byte) i3;
    }

    public void g(int i, int i2, boolean z) {
        this.f4426a[i2][i] = z ? (byte) 1 : (byte) 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((this.f4427b * 2 * this.f4428c) + 2);
        for (int i = 0; i < this.f4428c; i++) {
            byte[] bArr = this.f4426a[i];
            for (int i2 = 0; i2 < this.f4427b; i2++) {
                byte b2 = bArr[i2];
                if (b2 == 0) {
                    sb.append(" 0");
                } else if (b2 != 1) {
                    sb.append("  ");
                } else {
                    sb.append(" 1");
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
