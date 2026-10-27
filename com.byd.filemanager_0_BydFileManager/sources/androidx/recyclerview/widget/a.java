package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.j;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AdapterHelper.java */
/* JADX INFO: loaded from: classes.dex */
class a implements j.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b.g.j.e<b> f1381a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayList<b> f1382b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ArrayList<b> f1383c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final InterfaceC0020a f1384d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    Runnable f1385e;
    final boolean f;
    final j g;
    private int h;

    /* JADX INFO: renamed from: androidx.recyclerview.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AdapterHelper.java */
    interface InterfaceC0020a {
        void a(int i, int i2);

        void b(b bVar);

        RecyclerView.c0 c(int i);

        void d(int i, int i2);

        void e(int i, int i2);

        void f(b bVar);

        void g(int i, int i2);

        void h(int i, int i2, Object obj);
    }

    /* JADX INFO: compiled from: AdapterHelper.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1386a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1387b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        Object f1388c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1389d;

        b(int i, int i2, int i3, Object obj) {
            this.f1386a = i;
            this.f1387b = i2;
            this.f1389d = i3;
            this.f1388c = obj;
        }

        String a() {
            int i = this.f1386a;
            return i != 1 ? i != 2 ? i != 4 ? i != 8 ? "??" : "mv" : "up" : "rm" : "add";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            int i = this.f1386a;
            if (i != bVar.f1386a) {
                return false;
            }
            if (i == 8 && Math.abs(this.f1389d - this.f1387b) == 1 && this.f1389d == bVar.f1387b && this.f1387b == bVar.f1389d) {
                return true;
            }
            if (this.f1389d != bVar.f1389d || this.f1387b != bVar.f1387b) {
                return false;
            }
            Object obj2 = this.f1388c;
            if (obj2 != null) {
                if (!obj2.equals(bVar.f1388c)) {
                    return false;
                }
            } else if (bVar.f1388c != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.f1386a * 31) + this.f1387b) * 31) + this.f1389d;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + a() + ",s:" + this.f1387b + "c:" + this.f1389d + ",p:" + this.f1388c + "]";
        }
    }

    a(InterfaceC0020a interfaceC0020a) {
        this(interfaceC0020a, false);
    }

    private void c(b bVar) {
        t(bVar);
    }

    private void d(b bVar) {
        t(bVar);
    }

    private void f(b bVar) {
        boolean z;
        byte b2;
        int i = bVar.f1387b;
        int i2 = bVar.f1389d + i;
        byte b3 = -1;
        int i3 = i;
        int i4 = 0;
        while (i3 < i2) {
            if (this.f1384d.c(i3) != null || h(i3)) {
                if (b3 == 0) {
                    k(b(2, i, i4, null));
                    z = true;
                } else {
                    z = false;
                }
                b2 = 1;
            } else {
                if (b3 == 1) {
                    t(b(2, i, i4, null));
                    z = true;
                } else {
                    z = false;
                }
                b2 = 0;
            }
            if (z) {
                i3 -= i4;
                i2 -= i4;
                i4 = 1;
            } else {
                i4++;
            }
            i3++;
            b3 = b2;
        }
        if (i4 != bVar.f1389d) {
            a(bVar);
            bVar = b(2, i, i4, null);
        }
        if (b3 == 0) {
            k(bVar);
        } else {
            t(bVar);
        }
    }

    private void g(b bVar) {
        int i = bVar.f1387b;
        int i2 = bVar.f1389d + i;
        int i3 = 0;
        byte b2 = -1;
        int i4 = i;
        while (i < i2) {
            if (this.f1384d.c(i) != null || h(i)) {
                if (b2 == 0) {
                    k(b(4, i4, i3, bVar.f1388c));
                    i4 = i;
                    i3 = 0;
                }
                b2 = 1;
            } else {
                if (b2 == 1) {
                    t(b(4, i4, i3, bVar.f1388c));
                    i4 = i;
                    i3 = 0;
                }
                b2 = 0;
            }
            i3++;
            i++;
        }
        if (i3 != bVar.f1389d) {
            Object obj = bVar.f1388c;
            a(bVar);
            bVar = b(4, i4, i3, obj);
        }
        if (b2 == 0) {
            k(bVar);
        } else {
            t(bVar);
        }
    }

    private boolean h(int i) {
        int size = this.f1383c.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.f1383c.get(i2);
            int i3 = bVar.f1386a;
            if (i3 == 8) {
                if (n(bVar.f1389d, i2 + 1) == i) {
                    return true;
                }
            } else if (i3 == 1) {
                int i4 = bVar.f1387b;
                int i5 = bVar.f1389d + i4;
                while (i4 < i5) {
                    if (n(i4, i2 + 1) == i) {
                        return true;
                    }
                    i4++;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    private void k(b bVar) {
        int i;
        int i2 = bVar.f1386a;
        if (i2 == 1 || i2 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iX = x(bVar.f1387b, i2);
        int i3 = bVar.f1387b;
        int i4 = bVar.f1386a;
        if (i4 == 2) {
            i = 0;
        } else {
            if (i4 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + bVar);
            }
            i = 1;
        }
        int i5 = 1;
        for (int i6 = 1; i6 < bVar.f1389d; i6++) {
            int iX2 = x(bVar.f1387b + (i * i6), bVar.f1386a);
            int i7 = bVar.f1386a;
            if (i7 == 2 ? iX2 == iX : i7 == 4 && iX2 == iX + 1) {
                i5++;
            } else {
                b bVarB = b(i7, iX, i5, bVar.f1388c);
                l(bVarB, i3);
                a(bVarB);
                if (bVar.f1386a == 4) {
                    i3 += i5;
                }
                i5 = 1;
                iX = iX2;
            }
        }
        Object obj = bVar.f1388c;
        a(bVar);
        if (i5 > 0) {
            b bVarB2 = b(bVar.f1386a, iX, i5, obj);
            l(bVarB2, i3);
            a(bVarB2);
        }
    }

    private void t(b bVar) {
        this.f1383c.add(bVar);
        int i = bVar.f1386a;
        if (i == 1) {
            this.f1384d.g(bVar.f1387b, bVar.f1389d);
            return;
        }
        if (i == 2) {
            this.f1384d.e(bVar.f1387b, bVar.f1389d);
            return;
        }
        if (i == 4) {
            this.f1384d.h(bVar.f1387b, bVar.f1389d, bVar.f1388c);
        } else {
            if (i == 8) {
                this.f1384d.a(bVar.f1387b, bVar.f1389d);
                return;
            }
            throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    private int x(int i, int i2) {
        int i3;
        int i4;
        for (int size = this.f1383c.size() - 1; size >= 0; size--) {
            b bVar = this.f1383c.get(size);
            int i5 = bVar.f1386a;
            if (i5 == 8) {
                int i6 = bVar.f1387b;
                int i7 = bVar.f1389d;
                if (i6 < i7) {
                    i4 = i6;
                    i3 = i7;
                } else {
                    i3 = i6;
                    i4 = i7;
                }
                if (i < i4 || i > i3) {
                    if (i < i6) {
                        if (i2 == 1) {
                            bVar.f1387b = i6 + 1;
                            bVar.f1389d = i7 + 1;
                        } else if (i2 == 2) {
                            bVar.f1387b = i6 - 1;
                            bVar.f1389d = i7 - 1;
                        }
                    }
                } else if (i4 == i6) {
                    if (i2 == 1) {
                        bVar.f1389d = i7 + 1;
                    } else if (i2 == 2) {
                        bVar.f1389d = i7 - 1;
                    }
                    i++;
                } else {
                    if (i2 == 1) {
                        bVar.f1387b = i6 + 1;
                    } else if (i2 == 2) {
                        bVar.f1387b = i6 - 1;
                    }
                    i--;
                }
            } else {
                int i8 = bVar.f1387b;
                if (i8 <= i) {
                    if (i5 == 1) {
                        i -= bVar.f1389d;
                    } else if (i5 == 2) {
                        i += bVar.f1389d;
                    }
                } else if (i2 == 1) {
                    bVar.f1387b = i8 + 1;
                } else if (i2 == 2) {
                    bVar.f1387b = i8 - 1;
                }
            }
        }
        for (int size2 = this.f1383c.size() - 1; size2 >= 0; size2--) {
            b bVar2 = this.f1383c.get(size2);
            if (bVar2.f1386a == 8) {
                int i9 = bVar2.f1389d;
                if (i9 == bVar2.f1387b || i9 < 0) {
                    this.f1383c.remove(size2);
                    a(bVar2);
                }
            } else if (bVar2.f1389d <= 0) {
                this.f1383c.remove(size2);
                a(bVar2);
            }
        }
        return i;
    }

    @Override // androidx.recyclerview.widget.j.a
    public void a(b bVar) {
        if (this.f) {
            return;
        }
        bVar.f1388c = null;
        this.f1381a.a(bVar);
    }

    @Override // androidx.recyclerview.widget.j.a
    public b b(int i, int i2, int i3, Object obj) {
        b bVarB = this.f1381a.b();
        if (bVarB == null) {
            return new b(i, i2, i3, obj);
        }
        bVarB.f1386a = i;
        bVarB.f1387b = i2;
        bVarB.f1389d = i3;
        bVarB.f1388c = obj;
        return bVarB;
    }

    public int e(int i) {
        int size = this.f1382b.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.f1382b.get(i2);
            int i3 = bVar.f1386a;
            if (i3 != 1) {
                if (i3 == 2) {
                    int i4 = bVar.f1387b;
                    if (i4 <= i) {
                        int i5 = bVar.f1389d;
                        if (i4 + i5 > i) {
                            return -1;
                        }
                        i -= i5;
                    } else {
                        continue;
                    }
                } else if (i3 == 8) {
                    int i6 = bVar.f1387b;
                    if (i6 == i) {
                        i = bVar.f1389d;
                    } else {
                        if (i6 < i) {
                            i--;
                        }
                        if (bVar.f1389d <= i) {
                            i++;
                        }
                    }
                }
            } else if (bVar.f1387b <= i) {
                i += bVar.f1389d;
            }
        }
        return i;
    }

    void i() {
        int size = this.f1383c.size();
        for (int i = 0; i < size; i++) {
            this.f1384d.b(this.f1383c.get(i));
        }
        v(this.f1383c);
        this.h = 0;
    }

    void j() {
        i();
        int size = this.f1382b.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.f1382b.get(i);
            int i2 = bVar.f1386a;
            if (i2 == 1) {
                this.f1384d.b(bVar);
                this.f1384d.g(bVar.f1387b, bVar.f1389d);
            } else if (i2 == 2) {
                this.f1384d.b(bVar);
                this.f1384d.d(bVar.f1387b, bVar.f1389d);
            } else if (i2 == 4) {
                this.f1384d.b(bVar);
                this.f1384d.h(bVar.f1387b, bVar.f1389d, bVar.f1388c);
            } else if (i2 == 8) {
                this.f1384d.b(bVar);
                this.f1384d.a(bVar.f1387b, bVar.f1389d);
            }
            Runnable runnable = this.f1385e;
            if (runnable != null) {
                runnable.run();
            }
        }
        v(this.f1382b);
        this.h = 0;
    }

    void l(b bVar, int i) {
        this.f1384d.f(bVar);
        int i2 = bVar.f1386a;
        if (i2 == 2) {
            this.f1384d.d(i, bVar.f1389d);
        } else {
            if (i2 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            this.f1384d.h(i, bVar.f1389d, bVar.f1388c);
        }
    }

    int m(int i) {
        return n(i, 0);
    }

    int n(int i, int i2) {
        int size = this.f1383c.size();
        while (i2 < size) {
            b bVar = this.f1383c.get(i2);
            int i3 = bVar.f1386a;
            if (i3 == 8) {
                int i4 = bVar.f1387b;
                if (i4 == i) {
                    i = bVar.f1389d;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (bVar.f1389d <= i) {
                        i++;
                    }
                }
            } else {
                int i5 = bVar.f1387b;
                if (i5 > i) {
                    continue;
                } else if (i3 == 2) {
                    int i6 = bVar.f1389d;
                    if (i < i5 + i6) {
                        return -1;
                    }
                    i -= i6;
                } else if (i3 == 1) {
                    i += bVar.f1389d;
                }
            }
            i2++;
        }
        return i;
    }

    boolean o(int i) {
        return (i & this.h) != 0;
    }

    boolean p() {
        return this.f1382b.size() > 0;
    }

    boolean q() {
        return (this.f1383c.isEmpty() || this.f1382b.isEmpty()) ? false : true;
    }

    boolean r(int i, int i2, Object obj) {
        if (i2 < 1) {
            return false;
        }
        this.f1382b.add(b(4, i, i2, obj));
        this.h |= 4;
        return this.f1382b.size() == 1;
    }

    boolean s(int i, int i2) {
        if (i2 < 1) {
            return false;
        }
        this.f1382b.add(b(1, i, i2, null));
        this.h |= 1;
        return this.f1382b.size() == 1;
    }

    void u() {
        this.g.b(this.f1382b);
        int size = this.f1382b.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.f1382b.get(i);
            int i2 = bVar.f1386a;
            if (i2 == 1) {
                c(bVar);
            } else if (i2 == 2) {
                f(bVar);
            } else if (i2 == 4) {
                g(bVar);
            } else if (i2 == 8) {
                d(bVar);
            }
            Runnable runnable = this.f1385e;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.f1382b.clear();
    }

    void v(List<b> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            a(list.get(i));
        }
        list.clear();
    }

    void w() {
        v(this.f1382b);
        v(this.f1383c);
        this.h = 0;
    }

    a(InterfaceC0020a interfaceC0020a, boolean z) {
        this.f1381a = new b.g.j.f(30);
        this.f1382b = new ArrayList<>();
        this.f1383c = new ArrayList<>();
        this.h = 0;
        this.f1384d = interfaceC0020a;
        this.f = z;
        this.g = new j(this);
    }
}
