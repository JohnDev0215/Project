package v6;

import java.lang.reflect.Array;
import java.util.Arrays;

/* JADX INFO: compiled from: ByteMatrix.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[][] f8586a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f8587b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f8588c;

    public b(int i8, int i9) {
        this.f8586a = (byte[][]) Array.newInstance((Class<?>) byte.class, i9, i8);
        this.f8587b = i8;
        this.f8588c = i9;
    }

    public void a(byte b8) {
        for (byte[] bArr : this.f8586a) {
            Arrays.fill(bArr, b8);
        }
    }

    public byte b(int i8, int i9) {
        return this.f8586a[i9][i8];
    }

    public byte[][] c() {
        return this.f8586a;
    }

    public int d() {
        return this.f8588c;
    }

    public int e() {
        return this.f8587b;
    }

    public void f(int i8, int i9, int i10) {
        this.f8586a[i9][i8] = (byte) i10;
    }

    public void g(int i8, int i9, boolean z7) {
        this.f8586a[i9][i8] = z7 ? (byte) 1 : (byte) 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder((this.f8587b * 2 * this.f8588c) + 2);
        for (int i8 = 0; i8 < this.f8588c; i8++) {
            byte[] bArr = this.f8586a[i8];
            for (int i9 = 0; i9 < this.f8587b; i9++) {
                byte b8 = bArr[i9];
                if (b8 == 0) {
                    sb.append(" 0");
                } else if (b8 != 1) {
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
