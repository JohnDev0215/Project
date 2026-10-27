package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.i;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AdapterHelper.java */
/* JADX INFO: loaded from: classes.dex */
class a implements i.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b.g.j.e<b> f1903a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayList<b> f1904b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ArrayList<b> f1905c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final InterfaceC0026a f1906d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    Runnable f1907e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final boolean f1908f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final i f1909g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f1910h;

    /* JADX INFO: renamed from: androidx.recyclerview.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AdapterHelper.java */
    interface InterfaceC0026a {
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
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1911a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1912b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        Object f1913c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1914d;

        b(int i2, int i3, int i4, Object obj) {
            this.f1911a = i2;
            this.f1912b = i3;
            this.f1914d = i4;
            this.f1913c = obj;
        }

        String a() {
            int i2 = this.f1911a;
            return i2 != 1 ? i2 != 2 ? i2 != 4 ? i2 != 8 ? "??" : "mv" : "up" : "rm" : "add";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            int i2 = this.f1911a;
            if (i2 != bVar.f1911a) {
                return false;
            }
            if (i2 == 8 && Math.abs(this.f1914d - this.f1912b) == 1 && this.f1914d == bVar.f1912b && this.f1912b == bVar.f1914d) {
                return true;
            }
            if (this.f1914d != bVar.f1914d || this.f1912b != bVar.f1912b) {
                return false;
            }
            Object obj2 = this.f1913c;
            if (obj2 != null) {
                if (!obj2.equals(bVar.f1913c)) {
                    return false;
                }
            } else if (bVar.f1913c != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.f1911a * 31) + this.f1912b) * 31) + this.f1914d;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + a() + ",s:" + this.f1912b + "c:" + this.f1914d + ",p:" + this.f1913c + "]";
        }
    }

    a(InterfaceC0026a interfaceC0026a) {
        this(interfaceC0026a, false);
    }

    private void c(b bVar) {
        r(bVar);
    }

    private void d(b bVar) {
        r(bVar);
    }

    private void f(b bVar) {
        boolean z;
        byte b2;
        int i2 = bVar.f1912b;
        int i3 = bVar.f1914d + i2;
        byte b3 = -1;
        int i4 = i2;
        int i5 = 0;
        while (i4 < i3) {
            if (this.f1906d.c(i4) != null || h(i4)) {
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
        if (i5 != bVar.f1914d) {
            a(bVar);
            bVar = b(2, i2, i5, null);
        }
        if (b3 == 0) {
            k(bVar);
        } else {
            r(bVar);
        }
    }

    private void g(b bVar) {
        int i2 = bVar.f1912b;
        int i3 = bVar.f1914d + i2;
        int i4 = 0;
        byte b2 = -1;
        int i5 = i2;
        while (i2 < i3) {
            if (this.f1906d.c(i2) != null || h(i2)) {
                if (b2 == 0) {
                    k(b(4, i5, i4, bVar.f1913c));
                    i5 = i2;
                    i4 = 0;
                }
                b2 = 1;
            } else {
                if (b2 == 1) {
                    r(b(4, i5, i4, bVar.f1913c));
                    i5 = i2;
                    i4 = 0;
                }
                b2 = 0;
            }
            i4++;
            i2++;
        }
        if (i4 != bVar.f1914d) {
            Object obj = bVar.f1913c;
            a(bVar);
            bVar = b(4, i5, i4, obj);
        }
        if (b2 == 0) {
            k(bVar);
        } else {
            r(bVar);
        }
    }

    private boolean h(int i2) {
        int size = this.f1905c.size();
        for (int i3 = 0; i3 < size; i3++) {
            b bVar = this.f1905c.get(i3);
            int i4 = bVar.f1911a;
            if (i4 == 8) {
                if (n(bVar.f1914d, i3 + 1) == i2) {
                    return true;
                }
            } else if (i4 == 1) {
                int i5 = bVar.f1912b;
                int i6 = bVar.f1914d + i5;
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

    private void k(b bVar) {
        int i2;
        int i3 = bVar.f1911a;
        if (i3 == 1 || i3 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iV = v(bVar.f1912b, i3);
        int i4 = bVar.f1912b;
        int i5 = bVar.f1911a;
        if (i5 == 2) {
            i2 = 0;
        } else {
            if (i5 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + bVar);
            }
            i2 = 1;
        }
        int i6 = 1;
        for (int i7 = 1; i7 < bVar.f1914d; i7++) {
            int iV2 = v(bVar.f1912b + (i2 * i7), bVar.f1911a);
            int i8 = bVar.f1911a;
            if (i8 == 2 ? iV2 == iV : i8 == 4 && iV2 == iV + 1) {
                i6++;
            } else {
                b bVarB = b(i8, iV, i6, bVar.f1913c);
                l(bVarB, i4);
                a(bVarB);
                if (bVar.f1911a == 4) {
                    i4 += i6;
                }
                i6 = 1;
                iV = iV2;
            }
        }
        Object obj = bVar.f1913c;
        a(bVar);
        if (i6 > 0) {
            b bVarB2 = b(bVar.f1911a, iV, i6, obj);
            l(bVarB2, i4);
            a(bVarB2);
        }
    }

    private void r(b bVar) {
        this.f1905c.add(bVar);
        int i2 = bVar.f1911a;
        if (i2 == 1) {
            this.f1906d.g(bVar.f1912b, bVar.f1914d);
            return;
        }
        if (i2 == 2) {
            this.f1906d.e(bVar.f1912b, bVar.f1914d);
            return;
        }
        if (i2 == 4) {
            this.f1906d.h(bVar.f1912b, bVar.f1914d, bVar.f1913c);
        } else {
            if (i2 == 8) {
                this.f1906d.a(bVar.f1912b, bVar.f1914d);
                return;
            }
            throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    private int v(int i2, int i3) {
        int i4;
        int i5;
        for (int size = this.f1905c.size() - 1; size >= 0; size--) {
            b bVar = this.f1905c.get(size);
            int i6 = bVar.f1911a;
            if (i6 == 8) {
                int i7 = bVar.f1912b;
                int i8 = bVar.f1914d;
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
                            bVar.f1912b = i7 + 1;
                            bVar.f1914d = i8 + 1;
                        } else if (i3 == 2) {
                            bVar.f1912b = i7 - 1;
                            bVar.f1914d = i8 - 1;
                        }
                    }
                } else if (i5 == i7) {
                    if (i3 == 1) {
                        bVar.f1914d = i8 + 1;
                    } else if (i3 == 2) {
                        bVar.f1914d = i8 - 1;
                    }
                    i2++;
                } else {
                    if (i3 == 1) {
                        bVar.f1912b = i7 + 1;
                    } else if (i3 == 2) {
                        bVar.f1912b = i7 - 1;
                    }
                    i2--;
                }
            } else {
                int i9 = bVar.f1912b;
                if (i9 <= i2) {
                    if (i6 == 1) {
                        i2 -= bVar.f1914d;
                    } else if (i6 == 2) {
                        i2 += bVar.f1914d;
                    }
                } else if (i3 == 1) {
                    bVar.f1912b = i9 + 1;
                } else if (i3 == 2) {
                    bVar.f1912b = i9 - 1;
                }
            }
        }
        for (int size2 = this.f1905c.size() - 1; size2 >= 0; size2--) {
            b bVar2 = this.f1905c.get(size2);
            if (bVar2.f1911a == 8) {
                int i10 = bVar2.f1914d;
                if (i10 == bVar2.f1912b || i10 < 0) {
                    this.f1905c.remove(size2);
                    a(bVar2);
                }
            } else if (bVar2.f1914d <= 0) {
                this.f1905c.remove(size2);
                a(bVar2);
            }
        }
        return i2;
    }

    @Override // androidx.recyclerview.widget.i.a
    public void a(b bVar) {
        if (this.f1908f) {
            return;
        }
        bVar.f1913c = null;
        this.f1903a.a(bVar);
    }

    @Override // androidx.recyclerview.widget.i.a
    public b b(int i2, int i3, int i4, Object obj) {
        b bVarB = this.f1903a.b();
        if (bVarB == null) {
            return new b(i2, i3, i4, obj);
        }
        bVarB.f1911a = i2;
        bVarB.f1912b = i3;
        bVarB.f1914d = i4;
        bVarB.f1913c = obj;
        return bVarB;
    }

    public int e(int i2) {
        int size = this.f1904b.size();
        for (int i3 = 0; i3 < size; i3++) {
            b bVar = this.f1904b.get(i3);
            int i4 = bVar.f1911a;
            if (i4 != 1) {
                if (i4 == 2) {
                    int i5 = bVar.f1912b;
                    if (i5 <= i2) {
                        int i6 = bVar.f1914d;
                        if (i5 + i6 > i2) {
                            return -1;
                        }
                        i2 -= i6;
                    } else {
                        continue;
                    }
                } else if (i4 == 8) {
                    int i7 = bVar.f1912b;
                    if (i7 == i2) {
                        i2 = bVar.f1914d;
                    } else {
                        if (i7 < i2) {
                            i2--;
                        }
                        if (bVar.f1914d <= i2) {
                            i2++;
                        }
                    }
                }
            } else if (bVar.f1912b <= i2) {
                i2 += bVar.f1914d;
            }
        }
        return i2;
    }

    void i() {
        int size = this.f1905c.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f1906d.b(this.f1905c.get(i2));
        }
        t(this.f1905c);
        this.f1910h = 0;
    }

    void j() {
        i();
        int size = this.f1904b.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.f1904b.get(i2);
            int i3 = bVar.f1911a;
            if (i3 == 1) {
                this.f1906d.b(bVar);
                this.f1906d.g(bVar.f1912b, bVar.f1914d);
            } else if (i3 == 2) {
                this.f1906d.b(bVar);
                this.f1906d.d(bVar.f1912b, bVar.f1914d);
            } else if (i3 == 4) {
                this.f1906d.b(bVar);
                this.f1906d.h(bVar.f1912b, bVar.f1914d, bVar.f1913c);
            } else if (i3 == 8) {
                this.f1906d.b(bVar);
                this.f1906d.a(bVar.f1912b, bVar.f1914d);
            }
            Runnable runnable = this.f1907e;
            if (runnable != null) {
                runnable.run();
            }
        }
        t(this.f1904b);
        this.f1910h = 0;
    }

    void l(b bVar, int i2) {
        this.f1906d.f(bVar);
        int i3 = bVar.f1911a;
        if (i3 == 2) {
            this.f1906d.d(i2, bVar.f1914d);
        } else {
            if (i3 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            this.f1906d.h(i2, bVar.f1914d, bVar.f1913c);
        }
    }

    int m(int i2) {
        return n(i2, 0);
    }

    int n(int i2, int i3) {
        int size = this.f1905c.size();
        while (i3 < size) {
            b bVar = this.f1905c.get(i3);
            int i4 = bVar.f1911a;
            if (i4 == 8) {
                int i5 = bVar.f1912b;
                if (i5 == i2) {
                    i2 = bVar.f1914d;
                } else {
                    if (i5 < i2) {
                        i2--;
                    }
                    if (bVar.f1914d <= i2) {
                        i2++;
                    }
                }
            } else {
                int i6 = bVar.f1912b;
                if (i6 > i2) {
                    continue;
                } else if (i4 == 2) {
                    int i7 = bVar.f1914d;
                    if (i2 < i6 + i7) {
                        return -1;
                    }
                    i2 -= i7;
                } else if (i4 == 1) {
                    i2 += bVar.f1914d;
                }
            }
            i3++;
        }
        return i2;
    }

    boolean o(int i2) {
        return (i2 & this.f1910h) != 0;
    }

    boolean p() {
        return this.f1904b.size() > 0;
    }

    boolean q() {
        return (this.f1905c.isEmpty() || this.f1904b.isEmpty()) ? false : true;
    }

    void s() {
        this.f1909g.b(this.f1904b);
        int size = this.f1904b.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.f1904b.get(i2);
            int i3 = bVar.f1911a;
            if (i3 == 1) {
                c(bVar);
            } else if (i3 == 2) {
                f(bVar);
            } else if (i3 == 4) {
                g(bVar);
            } else if (i3 == 8) {
                d(bVar);
            }
            Runnable runnable = this.f1907e;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.f1904b.clear();
    }

    void t(List<b> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            a(list.get(i2));
        }
        list.clear();
    }

    void u() {
        t(this.f1904b);
        t(this.f1905c);
        this.f1910h = 0;
    }

    a(InterfaceC0026a interfaceC0026a, boolean z) {
        this.f1903a = new b.g.j.f(30);
        this.f1904b = new ArrayList<>();
        this.f1905c = new ArrayList<>();
        this.f1910h = 0;
        this.f1906d = interfaceC0026a;
        this.f1908f = z;
        this.f1909g = new i(this);
    }
}
