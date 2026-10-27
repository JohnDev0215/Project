package E5;

import O.C0481l;
import androidx.datastore.preferences.protobuf.AbstractC0947j;
import androidx.datastore.preferences.protobuf.AbstractC0957u;
import androidx.datastore.preferences.protobuf.AbstractC0959w;
import androidx.datastore.preferences.protobuf.C0944g;
import androidx.datastore.preferences.protobuf.C0950m;
import androidx.datastore.preferences.protobuf.C0960x;
import androidx.datastore.preferences.protobuf.C0961y;
import androidx.datastore.preferences.protobuf.V;
import androidx.datastore.preferences.protobuf.Y;
import androidx.datastore.preferences.protobuf.z0;
import e2.C1202j;
import g1.AbstractC1274a;
import h2.AbstractC1346c;
import java.nio.charset.Charset;
import java.util.List;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes.dex */
public final class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1840a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1841b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f1842e;

    public /* synthetic */ z(int i7, byte b10) {
        this.f1840a = i7;
    }

    public void A(List list) throws C0961y {
        int iU;
        int i7 = this.f1841b & 7;
        AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
        if (i7 == 0) {
            do {
                list.add(Long.valueOf(abstractC0947j.w()));
                if (abstractC0947j.c()) {
                    return;
                } else {
                    iU = abstractC0947j.u();
                }
            } while (iU == this.f1841b);
            this.d = iU;
            return;
        }
        if (i7 != 2) {
            throw C0961y.b();
        }
        int iB = abstractC0947j.b() + abstractC0947j.v();
        do {
            list.add(Long.valueOf(abstractC0947j.w()));
        } while (abstractC0947j.b() < iB);
        C(iB);
    }

    public long B() {
        int i7 = this.c;
        if (i7 == 0) {
            throw new NoSuchElementException();
        }
        int i10 = this.f1841b;
        long j3 = ((long[]) this.f1842e)[i10];
        this.f1841b = (i10 + 1) & this.d;
        this.c = i7 - 1;
        return j3;
    }

    public void C(int i7) throws C0961y {
        if (((AbstractC0947j) this.f1842e).b() != i7) {
            throw C0961y.e();
        }
    }

    public void D(int i7) throws C0960x {
        if ((this.f1841b & 7) != i7) {
            throw C0961y.b();
        }
    }

    public boolean E() {
        int i7;
        AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
        if (abstractC0947j.c() || (i7 = this.f1841b) == this.c) {
            return false;
        }
        return abstractC0947j.x(i7);
    }

    public void a(D d) {
        d.f1755n = null;
        d.l = null;
        d.f1754m = null;
        d.f1760t = 1;
        int i7 = this.f1841b;
        if (i7 > 0) {
            int i10 = this.d;
            if ((i10 & 1) == 0) {
                this.d = i10 + 1;
                this.f1841b = i7 - 1;
                this.c++;
            }
        }
        d.l = (D) this.f1842e;
        this.f1842e = d;
        int i11 = this.d;
        int i12 = i11 + 1;
        this.d = i12;
        int i13 = this.f1841b;
        if (i13 > 0 && (i12 & 1) == 0) {
            this.d = i11 + 2;
            this.f1841b = i13 - 1;
            this.c++;
        }
        int i14 = 4;
        while (true) {
            int i15 = i14 - 1;
            if ((this.d & i15) != i15) {
                return;
            }
            int i16 = this.c;
            if (i16 == 0) {
                D d10 = (D) this.f1842e;
                D d11 = d10.l;
                D d12 = d11.l;
                d11.l = d12.l;
                this.f1842e = d11;
                d11.f1754m = d12;
                d11.f1755n = d10;
                d11.f1760t = d10.f1760t + 1;
                d12.l = d11;
                d10.l = d11;
            } else if (i16 == 1) {
                D d13 = (D) this.f1842e;
                D d14 = d13.l;
                this.f1842e = d14;
                d14.f1755n = d13;
                d14.f1760t = d13.f1760t + 1;
                d13.l = d14;
                this.c = 0;
            } else if (i16 == 2) {
                this.c = 0;
            }
            i14 *= 2;
        }
    }

    public C0481l b(int i7) {
        return new C0481l(AbstractC1274a.O((N0.H) this.f1842e, i7), i7, 1L);
    }

    public C1202j c() {
        AbstractC1346c.c(this.c <= this.d);
        return new C1202j(this);
    }

    public int d() {
        return this.d - this.c;
    }

    public int e() {
        int i7 = this.d;
        if (i7 != 0) {
            this.f1841b = i7;
            this.d = 0;
        } else {
            this.f1841b = ((AbstractC0947j) this.f1842e).u();
        }
        int i10 = this.f1841b;
        if (i10 == 0 || i10 == this.c) {
            return Integer.MAX_VALUE;
        }
        return i10 >>> 3;
    }

    public int f(int i7) {
        return ((U.D) this.f1842e).f8937f[this.c + i7];
    }

    public Object g(int i7) {
        return ((U.D) this.f1842e).f8939h[this.d + i7];
    }

    public void h(Object obj, Y y10, C0950m c0950m) {
        int i7 = this.c;
        this.c = ((this.f1841b >>> 3) << 3) | 4;
        try {
            y10.i(obj, this, c0950m);
            if (this.f1841b == this.c) {
            } else {
                throw new C0961y("Failed to parse the message.");
            }
        } finally {
            this.c = i7;
        }
    }

    public void i(Object obj, Y y10, C0950m c0950m) throws C0961y {
        AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
        int iV = abstractC0947j.v();
        if (abstractC0947j.f11862a >= 100) {
            throw new C0961y("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int iE = abstractC0947j.e(iV);
        abstractC0947j.f11862a++;
        y10.i(obj, this, c0950m);
        abstractC0947j.a(0);
        abstractC0947j.f11862a--;
        abstractC0947j.d(iE);
    }

    public void j(List list) throws C0961y {
        int iU;
        int i7 = this.f1841b & 7;
        AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
        if (i7 == 0) {
            do {
                list.add(Boolean.valueOf(abstractC0947j.f()));
                if (abstractC0947j.c()) {
                    return;
                } else {
                    iU = abstractC0947j.u();
                }
            } while (iU == this.f1841b);
            this.d = iU;
            return;
        }
        if (i7 != 2) {
            throw C0961y.b();
        }
        int iB = abstractC0947j.b() + abstractC0947j.v();
        do {
            list.add(Boolean.valueOf(abstractC0947j.f()));
        } while (abstractC0947j.b() < iB);
        C(iB);
    }

    public C0944g k() throws C0960x {
        D(2);
        return ((AbstractC0947j) this.f1842e).g();
    }

    public void l(List list) throws C0960x {
        int iU;
        if ((this.f1841b & 7) != 2) {
            throw C0961y.b();
        }
        do {
            list.add(k());
            AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
            if (abstractC0947j.c()) {
                return;
            } else {
                iU = abstractC0947j.u();
            }
        } while (iU == this.f1841b);
        this.d = iU;
    }

    public void m(List list) throws C0961y {
        int iU;
        int i7 = this.f1841b & 7;
        AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
        if (i7 == 1) {
            do {
                list.add(Double.valueOf(abstractC0947j.h()));
                if (abstractC0947j.c()) {
                    return;
                } else {
                    iU = abstractC0947j.u();
                }
            } while (iU == this.f1841b);
            this.d = iU;
            return;
        }
        if (i7 != 2) {
            throw C0961y.b();
        }
        int iV = abstractC0947j.v();
        if ((iV & 7) != 0) {
            throw new C0961y("Failed to parse the message.");
        }
        int iB = abstractC0947j.b() + iV;
        do {
            list.add(Double.valueOf(abstractC0947j.h()));
        } while (abstractC0947j.b() < iB);
    }

    public void n(List list) throws C0961y {
        int iU;
        int i7 = this.f1841b & 7;
        AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
        if (i7 == 0) {
            do {
                list.add(Integer.valueOf(abstractC0947j.i()));
                if (abstractC0947j.c()) {
                    return;
                } else {
                    iU = abstractC0947j.u();
                }
            } while (iU == this.f1841b);
            this.d = iU;
            return;
        }
        if (i7 != 2) {
            throw C0961y.b();
        }
        int iB = abstractC0947j.b() + abstractC0947j.v();
        do {
            list.add(Integer.valueOf(abstractC0947j.i()));
        } while (abstractC0947j.b() < iB);
        C(iB);
    }

    public Object o(z0 z0Var, Class cls, C0950m c0950m) throws C0961y {
        int iOrdinal = z0Var.ordinal();
        AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
        switch (iOrdinal) {
            case 0:
                D(1);
                return Double.valueOf(abstractC0947j.h());
            case 1:
                D(5);
                return Float.valueOf(abstractC0947j.l());
            case 2:
                D(0);
                return Long.valueOf(abstractC0947j.n());
            case 3:
                D(0);
                return Long.valueOf(abstractC0947j.w());
            case 4:
                D(0);
                return Integer.valueOf(abstractC0947j.m());
            case 5:
                D(1);
                return Long.valueOf(abstractC0947j.k());
            case 6:
                D(5);
                return Integer.valueOf(abstractC0947j.j());
            case 7:
                D(0);
                return Boolean.valueOf(abstractC0947j.f());
            case 8:
                D(2);
                return abstractC0947j.t();
            case 9:
            default:
                throw new IllegalArgumentException("unsupported field type.");
            case 10:
                D(2);
                Y yA = V.c.a(cls);
                AbstractC0957u abstractC0957uH = yA.h();
                i(abstractC0957uH, yA, c0950m);
                yA.d(abstractC0957uH);
                return abstractC0957uH;
            case 11:
                return k();
            case 12:
                D(0);
                return Integer.valueOf(abstractC0947j.v());
            case 13:
                D(0);
                return Integer.valueOf(abstractC0947j.i());
            case 14:
                D(5);
                return Integer.valueOf(abstractC0947j.o());
            case 15:
                D(1);
                return Long.valueOf(abstractC0947j.p());
            case 16:
                D(0);
                return Integer.valueOf(abstractC0947j.q());
            case 17:
                D(0);
                return Long.valueOf(abstractC0947j.r());
        }
    }

    public void p(List list) throws C0961y {
        int iU;
        int i7 = this.f1841b & 7;
        AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
        if (i7 == 2) {
            int iV = abstractC0947j.v();
            if ((iV & 3) != 0) {
                throw new C0961y("Failed to parse the message.");
            }
            int iB = abstractC0947j.b() + iV;
            do {
                list.add(Integer.valueOf(abstractC0947j.j()));
            } while (abstractC0947j.b() < iB);
            return;
        }
        if (i7 != 5) {
            throw C0961y.b();
        }
        do {
            list.add(Integer.valueOf(abstractC0947j.j()));
            if (abstractC0947j.c()) {
                return;
            } else {
                iU = abstractC0947j.u();
            }
        } while (iU == this.f1841b);
        this.d = iU;
    }

    public void q(List list) throws C0961y {
        int iU;
        int i7 = this.f1841b & 7;
        AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
        if (i7 == 1) {
            do {
                list.add(Long.valueOf(abstractC0947j.k()));
                if (abstractC0947j.c()) {
                    return;
                } else {
                    iU = abstractC0947j.u();
                }
            } while (iU == this.f1841b);
            this.d = iU;
            return;
        }
        if (i7 != 2) {
            throw C0961y.b();
        }
        int iV = abstractC0947j.v();
        if ((iV & 7) != 0) {
            throw new C0961y("Failed to parse the message.");
        }
        int iB = abstractC0947j.b() + iV;
        do {
            list.add(Long.valueOf(abstractC0947j.k()));
        } while (abstractC0947j.b() < iB);
    }

    public void r(List list) throws C0961y {
        int iU;
        int i7 = this.f1841b & 7;
        AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
        if (i7 == 2) {
            int iV = abstractC0947j.v();
            if ((iV & 3) != 0) {
                throw new C0961y("Failed to parse the message.");
            }
            int iB = abstractC0947j.b() + iV;
            do {
                list.add(Float.valueOf(abstractC0947j.l()));
            } while (abstractC0947j.b() < iB);
            return;
        }
        if (i7 != 5) {
            throw C0961y.b();
        }
        do {
            list.add(Float.valueOf(abstractC0947j.l()));
            if (abstractC0947j.c()) {
                return;
            } else {
                iU = abstractC0947j.u();
            }
        } while (iU == this.f1841b);
        this.d = iU;
    }

    public void s(List list) throws C0961y {
        int iU;
        int i7 = this.f1841b & 7;
        AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
        if (i7 == 0) {
            do {
                list.add(Integer.valueOf(abstractC0947j.m()));
                if (abstractC0947j.c()) {
                    return;
                } else {
                    iU = abstractC0947j.u();
                }
            } while (iU == this.f1841b);
            this.d = iU;
            return;
        }
        if (i7 != 2) {
            throw C0961y.b();
        }
        int iB = abstractC0947j.b() + abstractC0947j.v();
        do {
            list.add(Integer.valueOf(abstractC0947j.m()));
        } while (abstractC0947j.b() < iB);
        C(iB);
    }

    public void t(List list) throws C0961y {
        int iU;
        int i7 = this.f1841b & 7;
        AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
        if (i7 == 0) {
            do {
                list.add(Long.valueOf(abstractC0947j.n()));
                if (abstractC0947j.c()) {
                    return;
                } else {
                    iU = abstractC0947j.u();
                }
            } while (iU == this.f1841b);
            this.d = iU;
            return;
        }
        if (i7 != 2) {
            throw C0961y.b();
        }
        int iB = abstractC0947j.b() + abstractC0947j.v();
        do {
            list.add(Long.valueOf(abstractC0947j.n()));
        } while (abstractC0947j.b() < iB);
        C(iB);
    }

    public String toString() {
        switch (this.f1840a) {
            case 2:
                StringBuilder sb = new StringBuilder("SelectionInfo(id=1, range=(");
                int i7 = this.f1841b;
                sb.append(i7);
                sb.append('-');
                N0.H h10 = (N0.H) this.f1842e;
                sb.append(AbstractC1274a.O(h10, i7));
                sb.append(',');
                int i10 = this.c;
                sb.append(i10);
                sb.append('-');
                sb.append(AbstractC1274a.O(h10, i10));
                sb.append("), prevOffset=");
                return android.hardware.bydauto.ac.tsimpl.a.j(sb, this.d, ')');
            case 3:
                return "";
            default:
                return super.toString();
        }
    }

    public void u(List list) throws C0961y {
        int iU;
        int i7 = this.f1841b & 7;
        AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
        if (i7 == 2) {
            int iV = abstractC0947j.v();
            if ((iV & 3) != 0) {
                throw new C0961y("Failed to parse the message.");
            }
            int iB = abstractC0947j.b() + iV;
            do {
                list.add(Integer.valueOf(abstractC0947j.o()));
            } while (abstractC0947j.b() < iB);
            return;
        }
        if (i7 != 5) {
            throw C0961y.b();
        }
        do {
            list.add(Integer.valueOf(abstractC0947j.o()));
            if (abstractC0947j.c()) {
                return;
            } else {
                iU = abstractC0947j.u();
            }
        } while (iU == this.f1841b);
        this.d = iU;
    }

    public void v(List list) throws C0961y {
        int iU;
        int i7 = this.f1841b & 7;
        AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
        if (i7 == 1) {
            do {
                list.add(Long.valueOf(abstractC0947j.p()));
                if (abstractC0947j.c()) {
                    return;
                } else {
                    iU = abstractC0947j.u();
                }
            } while (iU == this.f1841b);
            this.d = iU;
            return;
        }
        if (i7 != 2) {
            throw C0961y.b();
        }
        int iV = abstractC0947j.v();
        if ((iV & 7) != 0) {
            throw new C0961y("Failed to parse the message.");
        }
        int iB = abstractC0947j.b() + iV;
        do {
            list.add(Long.valueOf(abstractC0947j.p()));
        } while (abstractC0947j.b() < iB);
    }

    public void w(List list) throws C0961y {
        int iU;
        int i7 = this.f1841b & 7;
        AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
        if (i7 == 0) {
            do {
                list.add(Integer.valueOf(abstractC0947j.q()));
                if (abstractC0947j.c()) {
                    return;
                } else {
                    iU = abstractC0947j.u();
                }
            } while (iU == this.f1841b);
            this.d = iU;
            return;
        }
        if (i7 != 2) {
            throw C0961y.b();
        }
        int iB = abstractC0947j.b() + abstractC0947j.v();
        do {
            list.add(Integer.valueOf(abstractC0947j.q()));
        } while (abstractC0947j.b() < iB);
        C(iB);
    }

    public void x(List list) throws C0961y {
        int iU;
        int i7 = this.f1841b & 7;
        AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
        if (i7 == 0) {
            do {
                list.add(Long.valueOf(abstractC0947j.r()));
                if (abstractC0947j.c()) {
                    return;
                } else {
                    iU = abstractC0947j.u();
                }
            } while (iU == this.f1841b);
            this.d = iU;
            return;
        }
        if (i7 != 2) {
            throw C0961y.b();
        }
        int iB = abstractC0947j.b() + abstractC0947j.v();
        do {
            list.add(Long.valueOf(abstractC0947j.r()));
        } while (abstractC0947j.b() < iB);
        C(iB);
    }

    public void y(List list, boolean z6) throws C0960x {
        String strS;
        int iU;
        int iU2;
        if ((this.f1841b & 7) != 2) {
            throw C0961y.b();
        }
        boolean z10 = list instanceof androidx.datastore.preferences.protobuf.B;
        AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
        if (z10 && !z6) {
            androidx.datastore.preferences.protobuf.B b10 = (androidx.datastore.preferences.protobuf.B) list;
            do {
                b10.j(k());
                if (abstractC0947j.c()) {
                    return;
                } else {
                    iU2 = abstractC0947j.u();
                }
            } while (iU2 == this.f1841b);
            this.d = iU2;
            return;
        }
        do {
            if (z6) {
                D(2);
                strS = abstractC0947j.t();
            } else {
                D(2);
                strS = abstractC0947j.s();
            }
            list.add(strS);
            if (abstractC0947j.c()) {
                return;
            } else {
                iU = abstractC0947j.u();
            }
        } while (iU == this.f1841b);
        this.d = iU;
    }

    public void z(List list) throws C0961y {
        int iU;
        int i7 = this.f1841b & 7;
        AbstractC0947j abstractC0947j = (AbstractC0947j) this.f1842e;
        if (i7 == 0) {
            do {
                list.add(Integer.valueOf(abstractC0947j.v()));
                if (abstractC0947j.c()) {
                    return;
                } else {
                    iU = abstractC0947j.u();
                }
            } while (iU == this.f1841b);
            this.d = iU;
            return;
        }
        if (i7 != 2) {
            throw C0961y.b();
        }
        int iB = abstractC0947j.b() + abstractC0947j.v();
        do {
            list.add(Integer.valueOf(abstractC0947j.v()));
        } while (abstractC0947j.b() < iB);
        C(iB);
    }

    public z(AbstractC0947j abstractC0947j) {
        this.f1840a = 5;
        this.d = 0;
        Charset charset = AbstractC0959w.f11890a;
        this.f1842e = abstractC0947j;
        abstractC0947j.f11863b = this;
    }

    public z(int i7) {
        this.f1840a = 6;
        this.f1841b = i7;
    }

    public z(U.D d) {
        this.f1840a = 4;
        this.f1842e = d;
    }

    public z(int i7, int i10, int i11, N0.H h10) {
        this.f1840a = 2;
        this.f1841b = i7;
        this.c = i10;
        this.d = i11;
        this.f1842e = h10;
    }

    public z(int i7, Class cls, int i10, int i11) {
        this.f1840a = 1;
        this.f1841b = i7;
        this.f1842e = cls;
        this.d = i10;
        this.c = i11;
    }
}
