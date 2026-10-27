package com.google.zxing.common;

import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.Arrays;

/* JADX INFO: compiled from: BitMatrix.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements Cloneable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f4195b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f4196c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f4197d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int[] f4198e;

    public b(int i) {
        this(i, i);
    }

    private String a(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder(this.f4196c * (this.f4195b + 1));
        for (int i = 0; i < this.f4196c; i++) {
            for (int i2 = 0; i2 < this.f4195b; i2++) {
                sb.append(d(i2, i) ? str : str2);
            }
            sb.append(str3);
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public b clone() {
        return new b(this.f4195b, this.f4196c, this.f4197d, (int[]) this.f4198e.clone());
    }

    public void c(int i, int i2) {
        int i3 = (i2 * this.f4197d) + (i / 32);
        int[] iArr = this.f4198e;
        iArr[i3] = (1 << (i & 31)) ^ iArr[i3];
    }

    public boolean d(int i, int i2) {
        return ((this.f4198e[(i2 * this.f4197d) + (i / 32)] >>> (i & 31)) & 1) != 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f4195b == bVar.f4195b && this.f4196c == bVar.f4196c && this.f4197d == bVar.f4197d && Arrays.equals(this.f4198e, bVar.f4198e);
    }

    public int[] f() {
        int length = this.f4198e.length - 1;
        while (length >= 0 && this.f4198e[length] == 0) {
            length--;
        }
        if (length < 0) {
            return null;
        }
        int i = this.f4197d;
        int i2 = length / i;
        int i3 = (length % i) << 5;
        int i4 = 31;
        while ((this.f4198e[length] >>> i4) == 0) {
            i4--;
        }
        return new int[]{i3 + i4, i2};
    }

    public int[] g() {
        int i = this.f4195b;
        int i2 = this.f4196c;
        int i3 = -1;
        int i4 = -1;
        for (int i5 = 0; i5 < this.f4196c; i5++) {
            int i6 = 0;
            while (true) {
                int i7 = this.f4197d;
                if (i6 < i7) {
                    int i8 = this.f4198e[(i7 * i5) + i6];
                    if (i8 != 0) {
                        if (i5 < i2) {
                            i2 = i5;
                        }
                        if (i5 > i4) {
                            i4 = i5;
                        }
                        int i9 = i6 << 5;
                        if (i9 < i) {
                            int i10 = 0;
                            while ((i8 << (31 - i10)) == 0) {
                                i10++;
                            }
                            int i11 = i10 + i9;
                            if (i11 < i) {
                                i = i11;
                            }
                        }
                        if (i9 + 31 > i3) {
                            int i12 = 31;
                            while ((i8 >>> i12) == 0) {
                                i12--;
                            }
                            int i13 = i9 + i12;
                            if (i13 > i3) {
                                i3 = i13;
                            }
                        }
                    }
                    i6++;
                }
            }
        }
        if (i3 < i || i4 < i2) {
            return null;
        }
        return new int[]{i, i2, (i3 - i) + 1, (i4 - i2) + 1};
    }

    public int h() {
        return this.f4196c;
    }

    public int hashCode() {
        int i = this.f4195b;
        return (((((((i * 31) + i) * 31) + this.f4196c) * 31) + this.f4197d) * 31) + Arrays.hashCode(this.f4198e);
    }

    public a i(int i, a aVar) {
        if (aVar == null || aVar.l() < this.f4195b) {
            aVar = new a(this.f4195b);
        } else {
            aVar.d();
        }
        int i2 = i * this.f4197d;
        for (int i3 = 0; i3 < this.f4197d; i3++) {
            aVar.r(i3 << 5, this.f4198e[i2 + i3]);
        }
        return aVar;
    }

    public int[] j() {
        int[] iArr;
        int i = 0;
        while (true) {
            iArr = this.f4198e;
            if (i >= iArr.length || iArr[i] != 0) {
                break;
            }
            i++;
        }
        if (i == iArr.length) {
            return null;
        }
        int i2 = this.f4197d;
        int i3 = i / i2;
        int i4 = (i % i2) << 5;
        int i5 = iArr[i];
        int i6 = 0;
        while ((i5 << (31 - i6)) == 0) {
            i6++;
        }
        return new int[]{i4 + i6, i3};
    }

    public int k() {
        return this.f4195b;
    }

    public void l() {
        int iK = k();
        int iH = h();
        a aVar = new a(iK);
        a aVar2 = new a(iK);
        for (int i = 0; i < (iH + 1) / 2; i++) {
            aVar = i(i, aVar);
            int i2 = (iH - 1) - i;
            aVar2 = i(i2, aVar2);
            aVar.p();
            aVar2.p();
            o(i, aVar2);
            o(i2, aVar);
        }
    }

    public void m(int i, int i2) {
        int i3 = (i2 * this.f4197d) + (i / 32);
        int[] iArr = this.f4198e;
        iArr[i3] = (1 << (i & 31)) | iArr[i3];
    }

    public void n(int i, int i2, int i3, int i4) {
        if (i2 < 0 || i < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        }
        if (i4 <= 0 || i3 <= 0) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        }
        int i5 = i3 + i;
        int i6 = i4 + i2;
        if (i6 > this.f4196c || i5 > this.f4195b) {
            throw new IllegalArgumentException("The region must fit inside the matrix");
        }
        while (i2 < i6) {
            int i7 = this.f4197d * i2;
            for (int i8 = i; i8 < i5; i8++) {
                int[] iArr = this.f4198e;
                int i9 = (i8 / 32) + i7;
                iArr[i9] = iArr[i9] | (1 << (i8 & 31));
            }
            i2++;
        }
    }

    public void o(int i, a aVar) {
        int[] iArrI = aVar.i();
        int[] iArr = this.f4198e;
        int i2 = this.f4197d;
        System.arraycopy(iArrI, 0, iArr, i * i2, i2);
    }

    public String p(String str, String str2) {
        return a(str, str2, BydAdbHelper.COMMAND_LINE_END);
    }

    public String toString() {
        return p("X ", "  ");
    }

    public b(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.f4195b = i;
        this.f4196c = i2;
        int i3 = (i + 31) / 32;
        this.f4197d = i3;
        this.f4198e = new int[i3 * i2];
    }

    private b(int i, int i2, int i3, int[] iArr) {
        this.f4195b = i;
        this.f4196c = i2;
        this.f4197d = i3;
        this.f4198e = iArr;
    }
}
