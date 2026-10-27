package b.n.d;

import androidx.recyclerview.widget.RecyclerView;
import b.n.d.g;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AdapterHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements g.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.f.l.d<b> f2008a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList<b> f2009b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<b> f2010c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final InterfaceC0038a f2011d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Runnable f2012e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f2013f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final g f2014g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2015h;

    /* JADX INFO: renamed from: b.n.d.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AdapterHelper.java */
    public interface InterfaceC0038a {
        void a(int i2, int i3);

        void b(b bVar);

        RecyclerView.c0 c(int i2);

        void d(int i2, int i3);

        void e(int i2, int i3);

        void f(b bVar);

        void g(int i2, int i3);

        void h(int i2, int i3, Object obj);
    }

    /* JADX INFO: compiled from: AdapterHelper.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2016a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2017b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Object f2018c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2019d;

        public b(int i2, int i3, int i4, Object obj) {
            this.f2016a = i2;
            this.f2017b = i3;
            this.f2019d = i4;
            this.f2018c = obj;
        }

        public String a() {
            int i2 = this.f2016a;
            return i2 != 1 ? i2 != 2 ? i2 != 4 ? i2 != 8 ? "??" : "mv" : "up" : "rm" : "add";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            int i2 = this.f2016a;
            if (i2 != bVar.f2016a) {
                return false;
            }
            if (i2 == 8 && Math.abs(this.f2019d - this.f2017b) == 1 && this.f2019d == bVar.f2017b && this.f2017b == bVar.f2019d) {
                return true;
            }
            if (this.f2019d != bVar.f2019d || this.f2017b != bVar.f2017b) {
                return false;
            }
            Object obj2 = this.f2018c;
            if (obj2 != null) {
                if (!obj2.equals(bVar.f2018c)) {
                    return false;
                }
            } else if (bVar.f2018c != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.f2016a * 31) + this.f2017b) * 31) + this.f2019d;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + a() + ",s:" + this.f2017b + "c:" + this.f2019d + ",p:" + this.f2018c + "]";
        }
    }

    public a(InterfaceC0038a interfaceC0038a) {
        this(interfaceC0038a, false);
    }

    @Override // b.n.d.g.a
    public void a(b bVar) {
        if (this.f2013f) {
            return;
        }
        bVar.f2018c = null;
        this.f2008a.a(bVar);
    }

    @Override // b.n.d.g.a
    public b b(int i2, int i3, int i4, Object obj) {
        b bVarB = this.f2008a.b();
        if (bVarB == null) {
            return new b(i2, i3, i4, obj);
        }
        bVarB.f2016a = i2;
        bVarB.f2017b = i3;
        bVarB.f2019d = i4;
        bVarB.f2018c = obj;
        return bVarB;
    }

    public final void c(b bVar) {
        r(bVar);
    }

    public final void d(b bVar) {
        r(bVar);
    }

    public int e(int i2) {
        int size = this.f2009b.size();
        for (int i3 = 0; i3 < size; i3++) {
            b bVar = this.f2009b.get(i3);
            int i4 = bVar.f2016a;
            if (i4 != 1) {
                if (i4 == 2) {
                    int i5 = bVar.f2017b;
                    if (i5 <= i2) {
                        int i6 = bVar.f2019d;
                        if (i5 + i6 > i2) {
                            return -1;
                        }
                        i2 -= i6;
                    } else {
                        continue;
                    }
                } else if (i4 == 8) {
                    int i7 = bVar.f2017b;
                    if (i7 == i2) {
                        i2 = bVar.f2019d;
                    } else {
                        if (i7 < i2) {
                            i2--;
                        }
                        if (bVar.f2019d <= i2) {
                            i2++;
                        }
                    }
                }
            } else if (bVar.f2017b <= i2) {
                i2 += bVar.f2019d;
            }
        }
        return i2;
    }

    public final void f(b bVar) {
        boolean z;
        byte b2;
        int i2 = bVar.f2017b;
        int i3 = bVar.f2019d + i2;
        byte b3 = -1;
        int i4 = i2;
        int i5 = 0;
        while (i4 < i3) {
            if (this.f2011d.c(i4) != null || h(i4)) {
                if (b3 == 0) {
                    k(b(2, i2, i5, null));
                    z = true;
                } else {
                    z = false;
                }
                b2 = 1;
            } else {
                if (b3 == 1) {
                    r(b(2, i2, i5, null));
                    z = true;
                } else {
                    z = false;
                }
                b2 = 0;
            }
            if (z) {
                i4 -= i5;
                i3 -= i5;
                i5 = 1;
            } else {
                i5++;
            }
            i4++;
            b3 = b2;
        }
        if (i5 != bVar.f2019d) {
            a(bVar);
            bVar = b(2, i2, i5, null);
        }
        if (b3 == 0) {
            k(bVar);
        } else {
            r(bVar);
        }
    }

    public final void g(b bVar) {
        int i2 = bVar.f2017b;
        int i3 = bVar.f2019d + i2;
        int i4 = 0;
        byte b2 = -1;
        int i5 = i2;
        while (i2 < i3) {
            if (this.f2011d.c(i2) != null || h(i2)) {
                if (b2 == 0) {
                    k(b(4, i5, i4, bVar.f2018c));
                    i5 = i2;
                    i4 = 0;
                }
                b2 = 1;
            } else {
                if (b2 == 1) {
                    r(b(4, i5, i4, bVar.f2018c));
                    i5 = i2;
                    i4 = 0;
                }
                b2 = 0;
            }
            i4++;
            i2++;
        }
        if (i4 != bVar.f2019d) {
            Object obj = bVar.f2018c;
            a(bVar);
            bVar = b(4, i5, i4, obj);
        }
        if (b2 == 0) {
            k(bVar);
        } else {
            r(bVar);
        }
    }

    public final boolean h(int i2) {
        int size = this.f2010c.size();
        for (int i3 = 0; i3 < size; i3++) {
            b bVar = this.f2010c.get(i3);
            int i4 = bVar.f2016a;
            if (i4 == 8) {
                if (n(bVar.f2019d, i3 + 1) == i2) {
                    return true;
                }
            } else if (i4 == 1) {
                int i5 = bVar.f2017b;
                int i6 = bVar.f2019d + i5;
                while (i5 < i6) {
                    if (n(i5, i3 + 1) == i2) {
                        return true;
                    }
                    i5++;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    public void i() {
        int size = this.f2010c.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f2011d.b(this.f2010c.get(i2));
        }
        t(this.f2010c);
        this.f2015h = 0;
    }

    public void j() {
        i();
        int size = this.f2009b.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.f2009b.get(i2);
            int i3 = bVar.f2016a;
            if (i3 == 1) {
                this.f2011d.b(bVar);
                this.f2011d.g(bVar.f2017b, bVar.f2019d);
            } else if (i3 == 2) {
                this.f2011d.b(bVar);
                this.f2011d.d(bVar.f2017b, bVar.f2019d);
            } else if (i3 == 4) {
                this.f2011d.b(bVar);
                this.f2011d.h(bVar.f2017b, bVar.f2019d, bVar.f2018c);
            } else if (i3 == 8) {
                this.f2011d.b(bVar);
                this.f2011d.a(bVar.f2017b, bVar.f2019d);
            }
            Runnable runnable = this.f2012e;
            if (runnable != null) {
                runnable.run();
            }
        }
        t(this.f2009b);
        this.f2015h = 0;
    }

    public final void k(b bVar) {
        int i2;
        int i3 = bVar.f2016a;
        if (i3 == 1 || i3 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iV = v(bVar.f2017b, i3);
        int i4 = bVar.f2017b;
        int i5 = bVar.f2016a;
        if (i5 == 2) {
            i2 = 0;
        } else {
            if (i5 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + bVar);
            }
            i2 = 1;
        }
        int i6 = 1;
        for (int i7 = 1; i7 < bVar.f2019d; i7++) {
            int iV2 = v(bVar.f2017b + (i2 * i7), bVar.f2016a);
            int i8 = bVar.f2016a;
            if (i8 == 2 ? iV2 == iV : i8 == 4 && iV2 == iV + 1) {
                i6++;
            } else {
                b bVarB = b(i8, iV, i6, bVar.f2018c);
                l(bVarB, i4);
                a(bVarB);
                if (bVar.f2016a == 4) {
                    i4 += i6;
                }
                i6 = 1;
                iV = iV2;
            }
        }
        Object obj = bVar.f2018c;
        a(bVar);
        if (i6 > 0) {
            b bVarB2 = b(bVar.f2016a, iV, i6, obj);
            l(bVarB2, i4);
            a(bVarB2);
        }
    }

    public void l(b bVar, int i2) {
        this.f2011d.f(bVar);
        int i3 = bVar.f2016a;
        if (i3 == 2) {
            this.f2011d.d(i2, bVar.f2019d);
        } else {
            if (i3 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            this.f2011d.h(i2, bVar.f2019d, bVar.f2018c);
        }
    }

    public int m(int i2) {
        return n(i2, 0);
    }

    public int n(int i2, int i3) {
        int size = this.f2010c.size();
        while (i3 < size) {
            b bVar = this.f2010c.get(i3);
            int i4 = bVar.f2016a;
            if (i4 == 8) {
                int i5 = bVar.f2017b;
                if (i5 == i2) {
                    i2 = bVar.f2019d;
                } else {
                    if (i5 < i2) {
                        i2--;
                    }
                    if (bVar.f2019d <= i2) {
                        i2++;
                    }
                }
            } else {
                int i6 = bVar.f2017b;
                if (i6 > i2) {
                    continue;
                } else if (i4 == 2) {
                    int i7 = bVar.f2019d;
                    if (i2 < i6 + i7) {
                        return -1;
                    }
                    i2 -= i7;
                } else if (i4 == 1) {
                    i2 += bVar.f2019d;
                }
            }
            i3++;
        }
        return i2;
    }

    public boolean o(int i2) {
        return (i2 & this.f2015h) != 0;
    }

    public boolean p() {
        return this.f2009b.size() > 0;
    }

    public boolean q() {
        return (this.f2010c.isEmpty() || this.f2009b.isEmpty()) ? false : true;
    }

    public final void r(b bVar) {
        this.f2010c.add(bVar);
        int i2 = bVar.f2016a;
        if (i2 == 1) {
            this.f2011d.g(bVar.f2017b, bVar.f2019d);
            return;
        }
        if (i2 == 2) {
            this.f2011d.e(bVar.f2017b, bVar.f2019d);
            return;
        }
        if (i2 == 4) {
            this.f2011d.h(bVar.f2017b, bVar.f2019d, bVar.f2018c);
        } else {
            if (i2 == 8) {
                this.f2011d.a(bVar.f2017b, bVar.f2019d);
                return;
            }
            throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    public void s() {
        this.f2014g.b(this.f2009b);
        int size = this.f2009b.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.f2009b.get(i2);
            int i3 = bVar.f2016a;
            if (i3 == 1) {
                c(bVar);
            } else if (i3 == 2) {
                f(bVar);
            } else if (i3 == 4) {
                g(bVar);
            } else if (i3 == 8) {
                d(bVar);
            }
            Runnable runnable = this.f2012e;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.f2009b.clear();
    }

    public void t(List<b> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            a(list.get(i2));
        }
        list.clear();
    }

    public void u() {
        t(this.f2009b);
        t(this.f2010c);
        this.f2015h = 0;
    }

    public final int v(int i2, int i3) {
        int i4;
        int i5;
        for (int size = this.f2010c.size() - 1; size >= 0; size--) {
            b bVar = this.f2010c.get(size);
            int i6 = bVar.f2016a;
            if (i6 == 8) {
                int i7 = bVar.f2017b;
                int i8 = bVar.f2019d;
                if (i7 < i8) {
                    i5 = i7;
                    i4 = i8;
                } else {
                    i4 = i7;
                    i5 = i8;
                }
                if (i2 < i5 || i2 > i4) {
                    if (i2 < i7) {
                        if (i3 == 1) {
                            bVar.f2017b = i7 + 1;
                            bVar.f2019d = i8 + 1;
                        } else if (i3 == 2) {
                            bVar.f2017b = i7 - 1;
                            bVar.f2019d = i8 - 1;
                        }
                    }
                } else if (i5 == i7) {
                    if (i3 == 1) {
                        bVar.f2019d = i8 + 1;
                    } else if (i3 == 2) {
                        bVar.f2019d = i8 - 1;
                    }
                    i2++;
                } else {
                    if (i3 == 1) {
                        bVar.f2017b = i7 + 1;
                    } else if (i3 == 2) {
                        bVar.f2017b = i7 - 1;
                    }
                    i2--;
                }
            } else {
                int i9 = bVar.f2017b;
                if (i9 <= i2) {
                    if (i6 == 1) {
                        i2 -= bVar.f2019d;
                    } else if (i6 == 2) {
                        i2 += bVar.f2019d;
                    }
                } else if (i3 == 1) {
                    bVar.f2017b = i9 + 1;
                } else if (i3 == 2) {
                    bVar.f2017b = i9 - 1;
                }
            }
        }
        for (int size2 = this.f2010c.size() - 1; size2 >= 0; size2--) {
            b bVar2 = this.f2010c.get(size2);
            if (bVar2.f2016a == 8) {
                int i10 = bVar2.f2019d;
                if (i10 == bVar2.f2017b || i10 < 0) {
                    this.f2010c.remove(size2);
                    a(bVar2);
                }
            } else if (bVar2.f2019d <= 0) {
                this.f2010c.remove(size2);
                a(bVar2);
            }
        }
        return i2;
    }

    public a(InterfaceC0038a interfaceC0038a, boolean z) {
        this.f2008a = new b.f.l.e(30);
        this.f2009b = new ArrayList<>();
        this.f2010c = new ArrayList<>();
        this.f2015h = 0;
        this.f2011d = interfaceC0038a;
        this.f2013f = z;
        this.f2014g = new g(this);
    }
}
