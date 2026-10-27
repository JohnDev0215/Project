package p8;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class y extends AbstractC2129d {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final int[] f20996s;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f20997m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final AbstractC2129d f20998n;
    public final AbstractC2129d o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f20999p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final int f21000q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f21001r = 0;

    static {
        ArrayList arrayList = new ArrayList();
        int i7 = 1;
        int i10 = 1;
        while (i7 > 0) {
            arrayList.add(Integer.valueOf(i7));
            int i11 = i10 + i7;
            i10 = i7;
            i7 = i11;
        }
        arrayList.add(Integer.MAX_VALUE);
        f20996s = new int[arrayList.size()];
        int i12 = 0;
        while (true) {
            int[] iArr = f20996s;
            if (i12 >= iArr.length) {
                return;
            }
            iArr[i12] = ((Integer) arrayList.get(i12)).intValue();
            i12++;
        }
    }

    public y(AbstractC2129d abstractC2129d, AbstractC2129d abstractC2129d2) {
        this.f20998n = abstractC2129d;
        this.o = abstractC2129d2;
        int size = abstractC2129d.size();
        this.f20999p = size;
        this.f20997m = abstractC2129d2.size() + size;
        this.f21000q = Math.max(abstractC2129d.q(), abstractC2129d2.q()) + 1;
    }

    public final boolean equals(Object obj) {
        int iW;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractC2129d)) {
            return false;
        }
        AbstractC2129d abstractC2129d = (AbstractC2129d) obj;
        int size = abstractC2129d.size();
        int i7 = this.f20997m;
        if (i7 != size) {
            return false;
        }
        if (i7 == 0) {
            return true;
        }
        if (this.f21001r != 0 && (iW = abstractC2129d.w()) != 0 && this.f21001r != iW) {
            return false;
        }
        w wVar = new w(this);
        t tVarA = wVar.next();
        w wVar2 = new w(abstractC2129d);
        t tVarA2 = wVar2.next();
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        while (true) {
            int length = tVarA.f20991m.length - i10;
            int length2 = tVarA2.f20991m.length - i11;
            int iMin = Math.min(length, length2);
            if (!(i10 == 0 ? tVarA.A(tVarA2, i11, iMin) : tVarA2.A(tVarA, i10, iMin))) {
                return false;
            }
            i12 += iMin;
            if (i12 >= i7) {
                if (i12 == i7) {
                    return true;
                }
                throw new IllegalStateException();
            }
            if (iMin == length) {
                tVarA = wVar.next();
                i10 = 0;
            } else {
                i10 += iMin;
            }
            if (iMin == length2) {
                tVarA2 = wVar2.next();
                i11 = 0;
            } else {
                i11 += iMin;
            }
        }
    }

    public final int hashCode() {
        int iU = this.f21001r;
        if (iU == 0) {
            int i7 = this.f20997m;
            iU = u(i7, 0, i7);
            if (iU == 0) {
                iU = 1;
            }
            this.f21001r = iU;
        }
        return iU;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new x(this);
    }

    @Override // p8.AbstractC2129d
    public final void m(int i7, int i10, int i11, byte[] bArr) {
        int i12 = i7 + i11;
        AbstractC2129d abstractC2129d = this.f20998n;
        int i13 = this.f20999p;
        if (i12 <= i13) {
            abstractC2129d.m(i7, i10, i11, bArr);
            return;
        }
        AbstractC2129d abstractC2129d2 = this.o;
        if (i7 >= i13) {
            abstractC2129d2.m(i7 - i13, i10, i11, bArr);
            return;
        }
        int i14 = i13 - i7;
        abstractC2129d.m(i7, i10, i14, bArr);
        abstractC2129d2.m(0, i10 + i14, i11 - i14, bArr);
    }

    @Override // p8.AbstractC2129d
    public final int q() {
        return this.f21000q;
    }

    @Override // p8.AbstractC2129d
    public final boolean r() {
        return this.f20997m >= f20996s[this.f21000q];
    }

    @Override // p8.AbstractC2129d
    public final boolean s() {
        int iV = this.f20998n.v(0, 0, this.f20999p);
        AbstractC2129d abstractC2129d = this.o;
        return abstractC2129d.v(iV, 0, abstractC2129d.size()) == 0;
    }

    @Override // p8.AbstractC2129d
    public final int size() {
        return this.f20997m;
    }

    @Override // p8.AbstractC2129d
    public final int u(int i7, int i10, int i11) {
        int i12 = i10 + i11;
        AbstractC2129d abstractC2129d = this.f20998n;
        int i13 = this.f20999p;
        if (i12 <= i13) {
            return abstractC2129d.u(i7, i10, i11);
        }
        AbstractC2129d abstractC2129d2 = this.o;
        if (i10 >= i13) {
            return abstractC2129d2.u(i7, i10 - i13, i11);
        }
        int i14 = i13 - i10;
        return abstractC2129d2.u(abstractC2129d.u(i7, i10, i14), 0, i11 - i14);
    }

    @Override // p8.AbstractC2129d
    public final int v(int i7, int i10, int i11) {
        int i12 = i10 + i11;
        AbstractC2129d abstractC2129d = this.f20998n;
        int i13 = this.f20999p;
        if (i12 <= i13) {
            return abstractC2129d.v(i7, i10, i11);
        }
        AbstractC2129d abstractC2129d2 = this.o;
        if (i10 >= i13) {
            return abstractC2129d2.v(i7, i10 - i13, i11);
        }
        int i14 = i13 - i10;
        return abstractC2129d2.v(abstractC2129d.v(i7, i10, i14), 0, i11 - i14);
    }

    @Override // p8.AbstractC2129d
    public final int w() {
        return this.f21001r;
    }

    @Override // p8.AbstractC2129d
    public final String x() {
        byte[] bArr;
        int i7 = this.f20997m;
        if (i7 == 0) {
            bArr = AbstractC2141p.f20989a;
        } else {
            byte[] bArr2 = new byte[i7];
            m(0, 0, i7, bArr2);
            bArr = bArr2;
        }
        return new String(bArr, "UTF-8");
    }

    @Override // p8.AbstractC2129d
    public final void z(OutputStream outputStream, int i7, int i10) {
        int i11 = i7 + i10;
        AbstractC2129d abstractC2129d = this.f20998n;
        int i12 = this.f20999p;
        if (i11 <= i12) {
            abstractC2129d.z(outputStream, i7, i10);
            return;
        }
        AbstractC2129d abstractC2129d2 = this.o;
        if (i7 >= i12) {
            abstractC2129d2.z(outputStream, i7 - i12, i10);
            return;
        }
        int i13 = i12 - i7;
        abstractC2129d.z(outputStream, i7, i13);
        abstractC2129d2.z(outputStream, 0, i10 - i13);
    }
}
