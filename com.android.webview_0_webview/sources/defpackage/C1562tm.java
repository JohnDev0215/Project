package defpackage;

import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: renamed from: tm, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1562tm {
    public static final C1562tm f = new C1562tm(0, new int[0], new Object[0], false);
    public int a;
    public int[] b;
    public Object[] c;
    public int d = -1;
    public boolean e;

    public C1562tm(int i, int[] iArr, Object[] objArr, boolean z) {
        this.a = i;
        this.b = iArr;
        this.c = objArr;
        this.e = z;
    }

    public static void c(int i, Object obj, InterfaceC0192Km interfaceC0192Km) {
        int i2 = i >>> 3;
        int i3 = i & 7;
        if (i3 == 0) {
            ((C1397ql) interfaceC0192Km).a.T(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 1) {
            ((C1397ql) interfaceC0192Km).a.K(i2, ((Long) obj).longValue());
            return;
        }
        if (i3 == 2) {
            ((C1397ql) interfaceC0192Km).a.H(i2, (AbstractC0731el) obj);
        } else {
            if (i3 != 3) {
                if (i3 != 5) {
                    throw new RuntimeException(C0155Il.c());
                }
                ((C1397ql) interfaceC0192Km).a.I(i2, ((Integer) obj).intValue());
                return;
            }
            C1397ql c1397ql = (C1397ql) interfaceC0192Km;
            c1397ql.a.Q(i2, 3);
            ((C1562tm) obj).d(interfaceC0192Km);
            c1397ql.a.Q(i2, 4);
        }
    }

    public int a() {
        int iA;
        int i = this.d;
        if (i != -1) {
            return i;
        }
        int iA2 = 0;
        for (int i2 = 0; i2 < this.a; i2++) {
            int i3 = this.b[i2];
            int i4 = i3 >>> 3;
            int i5 = i3 & 7;
            if (i5 == 0) {
                iA = AbstractC1342pl.A(i4, ((Long) this.c[i2]).longValue());
            } else if (i5 == 1) {
                iA = AbstractC1342pl.h(i4, ((Long) this.c[i2]).longValue());
            } else if (i5 == 2) {
                iA = AbstractC1342pl.c(i4, (AbstractC0731el) this.c[i2]);
            } else if (i5 == 3) {
                iA2 = ((C1562tm) this.c[i2]).a() + (AbstractC1342pl.x(i4) * 2) + iA2;
            } else {
                if (i5 != 5) {
                    throw new IllegalStateException(C0155Il.c());
                }
                iA = AbstractC1342pl.g(i4, ((Integer) this.c[i2]).intValue());
            }
            iA2 = iA + iA2;
        }
        this.d = iA2;
        return iA2;
    }

    public void b(int i, Object obj) {
        if (!this.e) {
            throw new UnsupportedOperationException();
        }
        int i2 = this.a;
        if (i2 == this.b.length) {
            int i3 = this.a + (i2 < 4 ? 8 : i2 >> 1);
            this.b = Arrays.copyOf(this.b, i3);
            this.c = Arrays.copyOf(this.c, i3);
        }
        int[] iArr = this.b;
        int i4 = this.a;
        iArr[i4] = i;
        this.c[i4] = obj;
        this.a = i4 + 1;
    }

    public void d(InterfaceC0192Km interfaceC0192Km) {
        if (this.a == 0) {
            return;
        }
        Objects.requireNonNull((C1397ql) interfaceC0192Km);
        for (int i = 0; i < this.a; i++) {
            c(this.b[i], this.c[i], interfaceC0192Km);
        }
    }

    public boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C1562tm)) {
            return false;
        }
        C1562tm c1562tm = (C1562tm) obj;
        int i = this.a;
        if (i == c1562tm.a) {
            int[] iArr = this.b;
            int[] iArr2 = c1562tm.b;
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
                Object[] objArr2 = c1562tm.c;
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

    public int hashCode() {
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
}
