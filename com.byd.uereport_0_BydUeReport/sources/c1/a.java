package c1;

import androidx.recyclerview.widget.RecyclerView;
import c1.i;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AdapterHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements i.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public n0.e<b> f2145a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList<b> f2146b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<b> f2147c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final InterfaceC0008a f2148d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Runnable f2149e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f2150f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final i f2151g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2152h;

    /* JADX INFO: renamed from: c1.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AdapterHelper.java */
    public interface InterfaceC0008a {
        void a(int i8, int i9);

        void b(b bVar);

        RecyclerView.c0 c(int i8);

        void d(int i8, int i9);

        void e(int i8, int i9);

        void f(int i8, int i9);

        void g(b bVar);

        void h(int i8, int i9, Object obj);
    }

    /* JADX INFO: compiled from: AdapterHelper.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f2153a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2154b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Object f2155c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f2156d;

        public b(int i8, int i9, int i10, Object obj) {
            this.f2153a = i8;
            this.f2154b = i9;
            this.f2156d = i10;
            this.f2155c = obj;
        }

        public String a() {
            int i8 = this.f2153a;
            return i8 != 1 ? i8 != 2 ? i8 != 4 ? i8 != 8 ? "??" : "mv" : "up" : "rm" : "add";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            int i8 = this.f2153a;
            if (i8 != bVar.f2153a) {
                return false;
            }
            if (i8 == 8 && Math.abs(this.f2156d - this.f2154b) == 1 && this.f2156d == bVar.f2154b && this.f2154b == bVar.f2156d) {
                return true;
            }
            if (this.f2156d != bVar.f2156d || this.f2154b != bVar.f2154b) {
                return false;
            }
            Object obj2 = this.f2155c;
            if (obj2 != null) {
                if (!obj2.equals(bVar.f2155c)) {
                    return false;
                }
            } else if (bVar.f2155c != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.f2153a * 31) + this.f2154b) * 31) + this.f2156d;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + a() + ",s:" + this.f2154b + "c:" + this.f2156d + ",p:" + this.f2155c + "]";
        }
    }

    public a(InterfaceC0008a interfaceC0008a) {
        this(interfaceC0008a, false);
    }

    @Override // c1.i.a
    public void a(b bVar) {
        if (this.f2150f) {
            return;
        }
        bVar.f2155c = null;
        this.f2145a.a(bVar);
    }

    @Override // c1.i.a
    public b b(int i8, int i9, int i10, Object obj) {
        b bVarB = this.f2145a.b();
        if (bVarB == null) {
            return new b(i8, i9, i10, obj);
        }
        bVarB.f2153a = i8;
        bVarB.f2154b = i9;
        bVarB.f2156d = i10;
        bVarB.f2155c = obj;
        return bVarB;
    }

    public final void c(b bVar) {
        v(bVar);
    }

    public final void d(b bVar) {
        v(bVar);
    }

    public int e(int i8) {
        int size = this.f2146b.size();
        for (int i9 = 0; i9 < size; i9++) {
            b bVar = this.f2146b.get(i9);
            int i10 = bVar.f2153a;
            if (i10 != 1) {
                if (i10 == 2) {
                    int i11 = bVar.f2154b;
                    if (i11 <= i8) {
                        int i12 = bVar.f2156d;
                        if (i11 + i12 > i8) {
                            return -1;
                        }
                        i8 -= i12;
                    } else {
                        continue;
                    }
                } else if (i10 == 8) {
                    int i13 = bVar.f2154b;
                    if (i13 == i8) {
                        i8 = bVar.f2156d;
                    } else {
                        if (i13 < i8) {
                            i8--;
                        }
                        if (bVar.f2156d <= i8) {
                            i8++;
                        }
                    }
                }
            } else if (bVar.f2154b <= i8) {
                i8 += bVar.f2156d;
            }
        }
        return i8;
    }

    public final void f(b bVar) {
        boolean z7;
        byte b8;
        int i8 = bVar.f2154b;
        int i9 = bVar.f2156d + i8;
        byte b9 = -1;
        int i10 = i8;
        int i11 = 0;
        while (i10 < i9) {
            if (this.f2148d.c(i10) != null || h(i10)) {
                if (b9 == 0) {
                    k(b(2, i8, i11, null));
                    z7 = true;
                } else {
                    z7 = false;
                }
                b8 = 1;
            } else {
                if (b9 == 1) {
                    v(b(2, i8, i11, null));
                    z7 = true;
                } else {
                    z7 = false;
                }
                b8 = 0;
            }
            if (z7) {
                i10 -= i11;
                i9 -= i11;
                i11 = 1;
            } else {
                i11++;
            }
            i10++;
            b9 = b8;
        }
        if (i11 != bVar.f2156d) {
            a(bVar);
            bVar = b(2, i8, i11, null);
        }
        if (b9 == 0) {
            k(bVar);
        } else {
            v(bVar);
        }
    }

    public final void g(b bVar) {
        int i8 = bVar.f2154b;
        int i9 = bVar.f2156d + i8;
        int i10 = 0;
        byte b8 = -1;
        int i11 = i8;
        while (i8 < i9) {
            if (this.f2148d.c(i8) != null || h(i8)) {
                if (b8 == 0) {
                    k(b(4, i11, i10, bVar.f2155c));
                    i11 = i8;
                    i10 = 0;
                }
                b8 = 1;
            } else {
                if (b8 == 1) {
                    v(b(4, i11, i10, bVar.f2155c));
                    i11 = i8;
                    i10 = 0;
                }
                b8 = 0;
            }
            i10++;
            i8++;
        }
        if (i10 != bVar.f2156d) {
            Object obj = bVar.f2155c;
            a(bVar);
            bVar = b(4, i11, i10, obj);
        }
        if (b8 == 0) {
            k(bVar);
        } else {
            v(bVar);
        }
    }

    public final boolean h(int i8) {
        int size = this.f2147c.size();
        for (int i9 = 0; i9 < size; i9++) {
            b bVar = this.f2147c.get(i9);
            int i10 = bVar.f2153a;
            if (i10 == 8) {
                if (n(bVar.f2156d, i9 + 1) == i8) {
                    return true;
                }
            } else if (i10 == 1) {
                int i11 = bVar.f2154b;
                int i12 = bVar.f2156d + i11;
                while (i11 < i12) {
                    if (n(i11, i9 + 1) == i8) {
                        return true;
                    }
                    i11++;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    public void i() {
        int size = this.f2147c.size();
        for (int i8 = 0; i8 < size; i8++) {
            this.f2148d.b(this.f2147c.get(i8));
        }
        x(this.f2147c);
        this.f2152h = 0;
    }

    public void j() {
        i();
        int size = this.f2146b.size();
        for (int i8 = 0; i8 < size; i8++) {
            b bVar = this.f2146b.get(i8);
            int i9 = bVar.f2153a;
            if (i9 == 1) {
                this.f2148d.b(bVar);
                this.f2148d.e(bVar.f2154b, bVar.f2156d);
            } else if (i9 == 2) {
                this.f2148d.b(bVar);
                this.f2148d.f(bVar.f2154b, bVar.f2156d);
            } else if (i9 == 4) {
                this.f2148d.b(bVar);
                this.f2148d.h(bVar.f2154b, bVar.f2156d, bVar.f2155c);
            } else if (i9 == 8) {
                this.f2148d.b(bVar);
                this.f2148d.a(bVar.f2154b, bVar.f2156d);
            }
            Runnable runnable = this.f2149e;
            if (runnable != null) {
                runnable.run();
            }
        }
        x(this.f2146b);
        this.f2152h = 0;
    }

    public final void k(b bVar) {
        int i8;
        int i9 = bVar.f2153a;
        if (i9 == 1 || i9 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iZ = z(bVar.f2154b, i9);
        int i10 = bVar.f2154b;
        int i11 = bVar.f2153a;
        if (i11 == 2) {
            i8 = 0;
        } else {
            if (i11 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + bVar);
            }
            i8 = 1;
        }
        int i12 = 1;
        for (int i13 = 1; i13 < bVar.f2156d; i13++) {
            int iZ2 = z(bVar.f2154b + (i8 * i13), bVar.f2153a);
            int i14 = bVar.f2153a;
            if (i14 == 2 ? iZ2 == iZ : i14 == 4 && iZ2 == iZ + 1) {
                i12++;
            } else {
                b bVarB = b(i14, iZ, i12, bVar.f2155c);
                l(bVarB, i10);
                a(bVarB);
                if (bVar.f2153a == 4) {
                    i10 += i12;
                }
                i12 = 1;
                iZ = iZ2;
            }
        }
        Object obj = bVar.f2155c;
        a(bVar);
        if (i12 > 0) {
            b bVarB2 = b(bVar.f2153a, iZ, i12, obj);
            l(bVarB2, i10);
            a(bVarB2);
        }
    }

    public void l(b bVar, int i8) {
        this.f2148d.g(bVar);
        int i9 = bVar.f2153a;
        if (i9 == 2) {
            this.f2148d.f(i8, bVar.f2156d);
        } else {
            if (i9 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            this.f2148d.h(i8, bVar.f2156d, bVar.f2155c);
        }
    }

    public int m(int i8) {
        return n(i8, 0);
    }

    public int n(int i8, int i9) {
        int size = this.f2147c.size();
        while (i9 < size) {
            b bVar = this.f2147c.get(i9);
            int i10 = bVar.f2153a;
            if (i10 == 8) {
                int i11 = bVar.f2154b;
                if (i11 == i8) {
                    i8 = bVar.f2156d;
                } else {
                    if (i11 < i8) {
                        i8--;
                    }
                    if (bVar.f2156d <= i8) {
                        i8++;
                    }
                }
            } else {
                int i12 = bVar.f2154b;
                if (i12 > i8) {
                    continue;
                } else if (i10 == 2) {
                    int i13 = bVar.f2156d;
                    if (i8 < i12 + i13) {
                        return -1;
                    }
                    i8 -= i13;
                } else if (i10 == 1) {
                    i8 += bVar.f2156d;
                }
            }
            i9++;
        }
        return i8;
    }

    public boolean o(int i8) {
        return (i8 & this.f2152h) != 0;
    }

    public boolean p() {
        return this.f2146b.size() > 0;
    }

    public boolean q() {
        return (this.f2147c.isEmpty() || this.f2146b.isEmpty()) ? false : true;
    }

    public boolean r(int i8, int i9, Object obj) {
        if (i9 < 1) {
            return false;
        }
        this.f2146b.add(b(4, i8, i9, obj));
        this.f2152h |= 4;
        return this.f2146b.size() == 1;
    }

    public boolean s(int i8, int i9) {
        if (i9 < 1) {
            return false;
        }
        this.f2146b.add(b(1, i8, i9, null));
        this.f2152h |= 1;
        return this.f2146b.size() == 1;
    }

    public boolean t(int i8, int i9, int i10) {
        if (i8 == i9) {
            return false;
        }
        if (i10 != 1) {
            throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
        }
        this.f2146b.add(b(8, i8, i9, null));
        this.f2152h |= 8;
        return this.f2146b.size() == 1;
    }

    public boolean u(int i8, int i9) {
        if (i9 < 1) {
            return false;
        }
        this.f2146b.add(b(2, i8, i9, null));
        this.f2152h |= 2;
        return this.f2146b.size() == 1;
    }

    public final void v(b bVar) {
        this.f2147c.add(bVar);
        int i8 = bVar.f2153a;
        if (i8 == 1) {
            this.f2148d.e(bVar.f2154b, bVar.f2156d);
            return;
        }
        if (i8 == 2) {
            this.f2148d.d(bVar.f2154b, bVar.f2156d);
            return;
        }
        if (i8 == 4) {
            this.f2148d.h(bVar.f2154b, bVar.f2156d, bVar.f2155c);
        } else {
            if (i8 == 8) {
                this.f2148d.a(bVar.f2154b, bVar.f2156d);
                return;
            }
            throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    public void w() {
        this.f2151g.b(this.f2146b);
        int size = this.f2146b.size();
        for (int i8 = 0; i8 < size; i8++) {
            b bVar = this.f2146b.get(i8);
            int i9 = bVar.f2153a;
            if (i9 == 1) {
                c(bVar);
            } else if (i9 == 2) {
                f(bVar);
            } else if (i9 == 4) {
                g(bVar);
            } else if (i9 == 8) {
                d(bVar);
            }
            Runnable runnable = this.f2149e;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.f2146b.clear();
    }

    public void x(List<b> list) {
        int size = list.size();
        for (int i8 = 0; i8 < size; i8++) {
            a(list.get(i8));
        }
        list.clear();
    }

    public void y() {
        x(this.f2146b);
        x(this.f2147c);
        this.f2152h = 0;
    }

    public final int z(int i8, int i9) {
        int i10;
        int i11;
        for (int size = this.f2147c.size() - 1; size >= 0; size--) {
            b bVar = this.f2147c.get(size);
            int i12 = bVar.f2153a;
            if (i12 == 8) {
                int i13 = bVar.f2154b;
                int i14 = bVar.f2156d;
                if (i13 < i14) {
                    i11 = i13;
                    i10 = i14;
                } else {
                    i10 = i13;
                    i11 = i14;
                }
                if (i8 < i11 || i8 > i10) {
                    if (i8 < i13) {
                        if (i9 == 1) {
                            bVar.f2154b = i13 + 1;
                            bVar.f2156d = i14 + 1;
                        } else if (i9 == 2) {
                            bVar.f2154b = i13 - 1;
                            bVar.f2156d = i14 - 1;
                        }
                    }
                } else if (i11 == i13) {
                    if (i9 == 1) {
                        bVar.f2156d = i14 + 1;
                    } else if (i9 == 2) {
                        bVar.f2156d = i14 - 1;
                    }
                    i8++;
                } else {
                    if (i9 == 1) {
                        bVar.f2154b = i13 + 1;
                    } else if (i9 == 2) {
                        bVar.f2154b = i13 - 1;
                    }
                    i8--;
                }
            } else {
                int i15 = bVar.f2154b;
                if (i15 <= i8) {
                    if (i12 == 1) {
                        i8 -= bVar.f2156d;
                    } else if (i12 == 2) {
                        i8 += bVar.f2156d;
                    }
                } else if (i9 == 1) {
                    bVar.f2154b = i15 + 1;
                } else if (i9 == 2) {
                    bVar.f2154b = i15 - 1;
                }
            }
        }
        for (int size2 = this.f2147c.size() - 1; size2 >= 0; size2--) {
            b bVar2 = this.f2147c.get(size2);
            if (bVar2.f2153a == 8) {
                int i16 = bVar2.f2156d;
                if (i16 == bVar2.f2154b || i16 < 0) {
                    this.f2147c.remove(size2);
                    a(bVar2);
                }
            } else if (bVar2.f2156d <= 0) {
                this.f2147c.remove(size2);
                a(bVar2);
            }
        }
        return i8;
    }

    public a(InterfaceC0008a interfaceC0008a, boolean z7) {
        this.f2145a = new n0.f(30);
        this.f2146b = new ArrayList<>();
        this.f2147c = new ArrayList<>();
        this.f2152h = 0;
        this.f2148d = interfaceC0008a;
        this.f2150f = z7;
        this.f2151g = new i(this);
    }
}
