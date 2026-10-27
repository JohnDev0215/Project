package androidx.datastore.preferences.protobuf;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class i0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final i0 f11858f = new i0(0, new int[0], new Object[0], false);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f11859a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int[] f11860b;
    public Object[] c;
    public int d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f11861e;

    public i0(int i7, int[] iArr, Object[] objArr, boolean z6) {
        this.f11859a = i7;
        this.f11860b = iArr;
        this.c = objArr;
        this.f11861e = z6;
    }

    public final void a(int i7) {
        int[] iArr = this.f11860b;
        if (i7 > iArr.length) {
            int i10 = this.f11859a;
            int i11 = (i10 / 2) + i10;
            if (i11 >= i7) {
                i7 = i11;
            }
            if (i7 < 8) {
                i7 = 8;
            }
            this.f11860b = Arrays.copyOf(iArr, i7);
            this.c = Arrays.copyOf(this.c, i7);
        }
    }

    public final int b() {
        int iG;
        int i7 = this.d;
        if (i7 != -1) {
            return i7;
        }
        int iB = 0;
        for (int i10 = 0; i10 < this.f11859a; i10++) {
            int i11 = this.f11860b[i10];
            int i12 = i11 >>> 3;
            int i13 = i11 & 7;
            if (i13 == 0) {
                iG = C0948k.G(((Long) this.c[i10]).longValue(), i12);
            } else if (i13 == 1) {
                ((Long) this.c[i10]).getClass();
                iG = C0948k.r(i12);
            } else if (i13 == 2) {
                iG = C0948k.m(i12, (C0944g) this.c[i10]);
            } else if (i13 == 3) {
                iB = ((i0) this.c[i10]).b() + (C0948k.D(i12) * 2) + iB;
            } else {
                if (i13 != 5) {
                    throw new IllegalStateException(C0961y.b());
                }
                ((Integer) this.c[i10]).getClass();
                iG = C0948k.q(i12);
            }
            iB = iG + iB;
        }
        this.d = iB;
        return iB;
    }

    public final void c(int i7, Object obj) {
        if (!this.f11861e) {
            throw new UnsupportedOperationException();
        }
        a(this.f11859a + 1);
        int[] iArr = this.f11860b;
        int i10 = this.f11859a;
        iArr[i10] = i7;
        this.c[i10] = obj;
        this.f11859a = i10 + 1;
    }

    public final void d(G g7) throws IOException {
        if (this.f11859a == 0) {
            return;
        }
        g7.getClass();
        for (int i7 = 0; i7 < this.f11859a; i7++) {
            int i10 = this.f11860b[i7];
            Object obj = this.c[i7];
            int i11 = i10 >>> 3;
            int i12 = i10 & 7;
            if (i12 == 0) {
                g7.j(((Long) obj).longValue(), i11);
            } else if (i12 == 1) {
                g7.f(((Long) obj).longValue(), i11);
            } else if (i12 == 2) {
                g7.b(i11, (C0944g) obj);
            } else if (i12 == 3) {
                C0948k c0948k = (C0948k) g7.f11793a;
                c0948k.X(i11, 3);
                ((i0) obj).d(g7);
                c0948k.X(i11, 4);
            } else {
                if (i12 != 5) {
                    throw new RuntimeException(C0961y.b());
                }
                g7.e(i11, ((Integer) obj).intValue());
            }
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof i0)) {
            return false;
        }
        i0 i0Var = (i0) obj;
        int i7 = this.f11859a;
        if (i7 == i0Var.f11859a) {
            int[] iArr = this.f11860b;
            int[] iArr2 = i0Var.f11860b;
            int i10 = 0;
            while (true) {
                if (i10 >= i7) {
                    Object[] objArr = this.c;
                    Object[] objArr2 = i0Var.c;
                    int i11 = this.f11859a;
                    for (int i12 = 0; i12 < i11; i12++) {
                        if (objArr[i12].equals(objArr2[i12])) {
                        }
                    }
                    return true;
                }
                if (iArr[i10] != iArr2[i10]) {
                    break;
                }
                i10++;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i7 = this.f11859a;
        int i10 = (527 + i7) * 31;
        int[] iArr = this.f11860b;
        int iHashCode = 17;
        int i11 = 17;
        for (int i12 = 0; i12 < i7; i12++) {
            i11 = (i11 * 31) + iArr[i12];
        }
        int i13 = (i10 + i11) * 31;
        Object[] objArr = this.c;
        int i14 = this.f11859a;
        for (int i15 = 0; i15 < i14; i15++) {
            iHashCode = (iHashCode * 31) + objArr[i15].hashCode();
        }
        return i13 + iHashCode;
    }
}
