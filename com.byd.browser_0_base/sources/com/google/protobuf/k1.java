package com.google.protobuf;

import com.google.protobuf.d0;
import defpackage.lk;
import defpackage.nu;
import java.util.Arrays;

/* JADX INFO: compiled from: UnknownFieldSetLite.java */
/* JADX INFO: loaded from: classes.dex */
public final class k1 {
    public static final k1 f = new k1(0, new int[0], new Object[0], false);
    public int a;
    public int[] b;
    public Object[] c;
    public int d;
    public boolean e;

    public k1() {
        this(0, new int[8], new Object[8], true);
    }

    public final int a() {
        int iM0;
        int i = this.d;
        if (i != -1) {
            return i;
        }
        int iA = 0;
        for (int i2 = 0; i2 < this.a; i2++) {
            int i3 = this.b[i2];
            int i4 = i3 >>> 3;
            int i5 = i3 & 7;
            if (i5 == 0) {
                iM0 = nu.m0(i4, ((Long) this.c[i2]).longValue());
            } else if (i5 == 1) {
                ((Long) this.c[i2]).longValue();
                iM0 = nu.P(i4);
            } else if (i5 == 2) {
                iM0 = nu.K(i4, (lk) this.c[i2]);
            } else if (i5 == 3) {
                iA = ((k1) this.c[i2]).a() + (nu.i0(i4) * 2) + iA;
            } else {
                if (i5 != 5) {
                    int i6 = d0.f;
                    throw new IllegalStateException(new d0.a());
                }
                ((Integer) this.c[i2]).intValue();
                iM0 = nu.O(i4);
            }
            iA = iM0 + iA;
        }
        this.d = iA;
        return iA;
    }

    public final void b(int i, Object obj) {
        if (!this.e) {
            throw new UnsupportedOperationException();
        }
        int i2 = this.a;
        int[] iArr = this.b;
        if (i2 == iArr.length) {
            int i3 = i2 + (i2 < 4 ? 8 : i2 >> 1);
            this.b = Arrays.copyOf(iArr, i3);
            this.c = Arrays.copyOf(this.c, i3);
        }
        int[] iArr2 = this.b;
        int i4 = this.a;
        iArr2[i4] = i;
        this.c[i4] = obj;
        this.a = i4 + 1;
    }

    public final void c(i iVar) {
        if (this.a == 0) {
            return;
        }
        iVar.getClass();
        for (int i = 0; i < this.a; i++) {
            int i2 = this.b[i];
            Object obj = this.c[i];
            int i3 = i2 >>> 3;
            int i4 = i2 & 7;
            if (i4 == 0) {
                iVar.l(i3, ((Long) obj).longValue());
            } else if (i4 == 1) {
                iVar.g(i3, ((Long) obj).longValue());
            } else if (i4 == 2) {
                iVar.b(i3, (lk) obj);
            } else if (i4 == 3) {
                nu nuVar = iVar.a;
                nuVar.M0(i3, 3);
                ((k1) obj).c(iVar);
                nuVar.M0(i3, 4);
            } else {
                if (i4 != 5) {
                    int i5 = d0.f;
                    throw new RuntimeException(new d0.a());
                }
                iVar.e(i3, ((Integer) obj).intValue());
            }
        }
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof k1)) {
            return false;
        }
        k1 k1Var = (k1) obj;
        int i = this.a;
        if (i == k1Var.a) {
            int[] iArr = this.b;
            int[] iArr2 = k1Var.b;
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    z = true;
                    break;
                }
                if (iArr[i2] != iArr2[i2]) {
                    z = false;
                    break;
                }
                i2++;
            }
            if (z) {
                Object[] objArr = this.c;
                Object[] objArr2 = k1Var.c;
                int i3 = this.a;
                int i4 = 0;
                while (true) {
                    if (i4 >= i3) {
                        z2 = true;
                        break;
                    }
                    if (!objArr[i4].equals(objArr2[i4])) {
                        z2 = false;
                        break;
                    }
                    i4++;
                }
                if (z2) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = this.a;
        int i2 = (527 + i) * 31;
        int[] iArr = this.b;
        int iHashCode = 17;
        int i3 = 17;
        for (int i4 = 0; i4 < i; i4++) {
            i3 = (i3 * 31) + iArr[i4];
        }
        int i5 = (i2 + i3) * 31;
        Object[] objArr = this.c;
        int i6 = this.a;
        for (int i7 = 0; i7 < i6; i7++) {
            iHashCode = (iHashCode * 31) + objArr[i7].hashCode();
        }
        return i5 + iHashCode;
    }

    public k1(int i, int[] iArr, Object[] objArr, boolean z) {
        this.d = -1;
        this.a = i;
        this.b = iArr;
        this.c = objArr;
        this.e = z;
    }
}
