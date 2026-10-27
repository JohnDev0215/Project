package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.i;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AdapterHelper.java */
/* JADX INFO: loaded from: classes.dex */
class a implements i.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b.g.j.e<b> f1206a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayList<b> f1207b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ArrayList<b> f1208c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final InterfaceC0020a f1209d;
    Runnable e;
    final boolean f;
    final i g;
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
        int f1210a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1211b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        Object f1212c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1213d;

        b(int i, int i2, int i3, Object obj) {
            this.f1210a = i;
            this.f1211b = i2;
            this.f1213d = i3;
            this.f1212c = obj;
        }

        String a() {
            int i = this.f1210a;
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
            int i = this.f1210a;
            if (i != bVar.f1210a) {
                return false;
            }
            if (i == 8 && Math.abs(this.f1213d - this.f1211b) == 1 && this.f1213d == bVar.f1211b && this.f1211b == bVar.f1213d) {
                return true;
            }
            if (this.f1213d != bVar.f1213d || this.f1211b != bVar.f1211b) {
                return false;
            }
            Object obj2 = this.f1212c;
            if (obj2 != null) {
                if (!obj2.equals(bVar.f1212c)) {
                    return false;
                }
            } else if (bVar.f1212c != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.f1210a * 31) + this.f1211b) * 31) + this.f1213d;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + a() + ",s:" + this.f1211b + "c:" + this.f1213d + ",p:" + this.f1212c + "]";
        }
    }

    a(InterfaceC0020a interfaceC0020a) {
        this(interfaceC0020a, false);
    }

    private void c(b bVar) {
        s(bVar);
    }

    private void d(b bVar) {
        s(bVar);
    }

    private void f(b bVar) {
        boolean z;
        byte b2;
        int i = bVar.f1211b;
        int i2 = bVar.f1213d + i;
        byte b3 = -1;
        int i3 = i;
        int i4 = 0;
        while (i3 < i2) {
            if (this.f1209d.c(i3) != null || h(i3)) {
                if (b3 == 0) {
                    k(b(2, i, i4, null));
                    z = true;
                } else {
                    z = false;
                }
                b2 = 1;
            } else {
                if (b3 == 1) {
                    s(b(2, i, i4, null));
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
        if (i4 != bVar.f1213d) {
            a(bVar);
            bVar = b(2, i, i4, null);
        }
        if (b3 == 0) {
            k(bVar);
        } else {
            s(bVar);
        }
    }

    private void g(b bVar) {
        int i = bVar.f1211b;
        int i2 = bVar.f1213d + i;
        int i3 = 0;
        byte b2 = -1;
        int i4 = i;
        while (i < i2) {
            if (this.f1209d.c(i) != null || h(i)) {
                if (b2 == 0) {
                    k(b(4, i4, i3, bVar.f1212c));
                    i4 = i;
                    i3 = 0;
                }
                b2 = 1;
            } else {
                if (b2 == 1) {
                    s(b(4, i4, i3, bVar.f1212c));
                    i4 = i;
                    i3 = 0;
                }
                b2 = 0;
            }
            i3++;
            i++;
        }
        if (i3 != bVar.f1213d) {
            Object obj = bVar.f1212c;
            a(bVar);
            bVar = b(4, i4, i3, obj);
        }
        if (b2 == 0) {
            k(bVar);
        } else {
            s(bVar);
        }
    }

    private boolean h(int i) {
        int size = this.f1208c.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.f1208c.get(i2);
            int i3 = bVar.f1210a;
            if (i3 == 8) {
                if (n(bVar.f1213d, i2 + 1) == i) {
                    return true;
                }
            } else if (i3 == 1) {
                int i4 = bVar.f1211b;
                int i5 = bVar.f1213d + i4;
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
        int i2 = bVar.f1210a;
        if (i2 == 1 || i2 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iW = w(bVar.f1211b, i2);
        int i3 = bVar.f1211b;
        int i4 = bVar.f1210a;
        if (i4 == 2) {
            i = 0;
        } else {
            if (i4 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + bVar);
            }
            i = 1;
        }
        int i5 = 1;
        for (int i6 = 1; i6 < bVar.f1213d; i6++) {
            int iW2 = w(bVar.f1211b + (i * i6), bVar.f1210a);
            int i7 = bVar.f1210a;
            if (i7 == 2 ? iW2 == iW : i7 == 4 && iW2 == iW + 1) {
                i5++;
            } else {
                b bVarB = b(i7, iW, i5, bVar.f1212c);
                l(bVarB, i3);
                a(bVarB);
                if (bVar.f1210a == 4) {
                    i3 += i5;
                }
                i5 = 1;
                iW = iW2;
            }
        }
        Object obj = bVar.f1212c;
        a(bVar);
        if (i5 > 0) {
            b bVarB2 = b(bVar.f1210a, iW, i5, obj);
            l(bVarB2, i3);
            a(bVarB2);
        }
    }

    private void s(b bVar) {
        this.f1208c.add(bVar);
        int i = bVar.f1210a;
        if (i == 1) {
            this.f1209d.g(bVar.f1211b, bVar.f1213d);
            return;
        }
        if (i == 2) {
            this.f1209d.e(bVar.f1211b, bVar.f1213d);
            return;
        }
        if (i == 4) {
            this.f1209d.h(bVar.f1211b, bVar.f1213d, bVar.f1212c);
        } else {
            if (i == 8) {
                this.f1209d.a(bVar.f1211b, bVar.f1213d);
                return;
            }
            throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    private int w(int i, int i2) {
        int i3;
        int i4;
        for (int size = this.f1208c.size() - 1; size >= 0; size--) {
            b bVar = this.f1208c.get(size);
            int i5 = bVar.f1210a;
            if (i5 == 8) {
                int i6 = bVar.f1211b;
                int i7 = bVar.f1213d;
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
                            bVar.f1211b = i6 + 1;
                            bVar.f1213d = i7 + 1;
                        } else if (i2 == 2) {
                            bVar.f1211b = i6 - 1;
                            bVar.f1213d = i7 - 1;
                        }
                    }
                } else if (i4 == i6) {
                    if (i2 == 1) {
                        bVar.f1213d = i7 + 1;
                    } else if (i2 == 2) {
                        bVar.f1213d = i7 - 1;
                    }
                    i++;
                } else {
                    if (i2 == 1) {
                        bVar.f1211b = i6 + 1;
                    } else if (i2 == 2) {
                        bVar.f1211b = i6 - 1;
                    }
                    i--;
                }
            } else {
                int i8 = bVar.f1211b;
                if (i8 <= i) {
                    if (i5 == 1) {
                        i -= bVar.f1213d;
                    } else if (i5 == 2) {
                        i += bVar.f1213d;
                    }
                } else if (i2 == 1) {
                    bVar.f1211b = i8 + 1;
                } else if (i2 == 2) {
                    bVar.f1211b = i8 - 1;
                }
            }
        }
        for (int size2 = this.f1208c.size() - 1; size2 >= 0; size2--) {
            b bVar2 = this.f1208c.get(size2);
            if (bVar2.f1210a == 8) {
                int i9 = bVar2.f1213d;
                if (i9 == bVar2.f1211b || i9 < 0) {
                    this.f1208c.remove(size2);
                    a(bVar2);
                }
            } else if (bVar2.f1213d <= 0) {
                this.f1208c.remove(size2);
                a(bVar2);
            }
        }
        return i;
    }

    @Override // androidx.recyclerview.widget.i.a
    public void a(b bVar) {
        if (this.f) {
            return;
        }
        bVar.f1212c = null;
        this.f1206a.a(bVar);
    }

    @Override // androidx.recyclerview.widget.i.a
    public b b(int i, int i2, int i3, Object obj) {
        b bVarB = this.f1206a.b();
        if (bVarB == null) {
            return new b(i, i2, i3, obj);
        }
        bVarB.f1210a = i;
        bVarB.f1211b = i2;
        bVarB.f1213d = i3;
        bVarB.f1212c = obj;
        return bVarB;
    }

    public int e(int i) {
        int size = this.f1207b.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.f1207b.get(i2);
            int i3 = bVar.f1210a;
            if (i3 != 1) {
                if (i3 == 2) {
                    int i4 = bVar.f1211b;
                    if (i4 <= i) {
                        int i5 = bVar.f1213d;
                        if (i4 + i5 > i) {
                            return -1;
                        }
                        i -= i5;
                    } else {
                        continue;
                    }
                } else if (i3 == 8) {
                    int i6 = bVar.f1211b;
                    if (i6 == i) {
                        i = bVar.f1213d;
                    } else {
                        if (i6 < i) {
                            i--;
                        }
                        if (bVar.f1213d <= i) {
                            i++;
                        }
                    }
                }
            } else if (bVar.f1211b <= i) {
                i += bVar.f1213d;
            }
        }
        return i;
    }

    void i() {
        int size = this.f1208c.size();
        for (int i = 0; i < size; i++) {
            this.f1209d.b(this.f1208c.get(i));
        }
        u(this.f1208c);
        this.h = 0;
    }

    void j() {
        i();
        int size = this.f1207b.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.f1207b.get(i);
            int i2 = bVar.f1210a;
            if (i2 == 1) {
                this.f1209d.b(bVar);
                this.f1209d.g(bVar.f1211b, bVar.f1213d);
            } else if (i2 == 2) {
                this.f1209d.b(bVar);
                this.f1209d.d(bVar.f1211b, bVar.f1213d);
            } else if (i2 == 4) {
                this.f1209d.b(bVar);
                this.f1209d.h(bVar.f1211b, bVar.f1213d, bVar.f1212c);
            } else if (i2 == 8) {
                this.f1209d.b(bVar);
                this.f1209d.a(bVar.f1211b, bVar.f1213d);
            }
            Runnable runnable = this.e;
            if (runnable != null) {
                runnable.run();
            }
        }
        u(this.f1207b);
        this.h = 0;
    }

    void l(b bVar, int i) {
        this.f1209d.f(bVar);
        int i2 = bVar.f1210a;
        if (i2 == 2) {
            this.f1209d.d(i, bVar.f1213d);
        } else {
            if (i2 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            this.f1209d.h(i, bVar.f1213d, bVar.f1212c);
        }
    }

    int m(int i) {
        return n(i, 0);
    }

    int n(int i, int i2) {
        int size = this.f1208c.size();
        while (i2 < size) {
            b bVar = this.f1208c.get(i2);
            int i3 = bVar.f1210a;
            if (i3 == 8) {
                int i4 = bVar.f1211b;
                if (i4 == i) {
                    i = bVar.f1213d;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (bVar.f1213d <= i) {
                        i++;
                    }
                }
            } else {
                int i5 = bVar.f1211b;
                if (i5 > i) {
                    continue;
                } else if (i3 == 2) {
                    int i6 = bVar.f1213d;
                    if (i < i5 + i6) {
                        return -1;
                    }
                    i -= i6;
                } else if (i3 == 1) {
                    i += bVar.f1213d;
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
        return this.f1207b.size() > 0;
    }

    boolean q() {
        return (this.f1208c.isEmpty() || this.f1207b.isEmpty()) ? false : true;
    }

    boolean r(int i, int i2, Object obj) {
        if (i2 < 1) {
            return false;
        }
        this.f1207b.add(b(4, i, i2, obj));
        this.h |= 4;
        return this.f1207b.size() == 1;
    }

    void t() {
        this.g.b(this.f1207b);
        int size = this.f1207b.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.f1207b.get(i);
            int i2 = bVar.f1210a;
            if (i2 == 1) {
                c(bVar);
            } else if (i2 == 2) {
                f(bVar);
            } else if (i2 == 4) {
                g(bVar);
            } else if (i2 == 8) {
                d(bVar);
            }
            Runnable runnable = this.e;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.f1207b.clear();
    }

    void u(List<b> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            a(list.get(i));
        }
        list.clear();
    }

    void v() {
        u(this.f1207b);
        u(this.f1208c);
        this.h = 0;
    }

    a(InterfaceC0020a interfaceC0020a, boolean z) {
        this.f1206a = new b.g.j.f(30);
        this.f1207b = new ArrayList<>();
        this.f1208c = new ArrayList<>();
        this.h = 0;
        this.f1209d = interfaceC0020a;
        this.f = z;
        this.g = new i(this);
    }
}
