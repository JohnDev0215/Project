package b.r.d;

import androidx.recyclerview.widget.RecyclerView;
import b.r.d.i;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AdapterHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements i.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.h.l.d<b> f3015a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList<b> f3016b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<b> f3017c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final InterfaceC0056a f3018d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Runnable f3019e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f3020f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final i f3021g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3022h;

    /* JADX INFO: renamed from: b.r.d.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AdapterHelper.java */
    public interface InterfaceC0056a {
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
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f3023a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3024b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Object f3025c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3026d;

        public b(int i2, int i3, int i4, Object obj) {
            this.f3023a = i2;
            this.f3024b = i3;
            this.f3026d = i4;
            this.f3025c = obj;
        }

        public String a() {
            int i2 = this.f3023a;
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
            int i2 = this.f3023a;
            if (i2 != bVar.f3023a) {
                return false;
            }
            if (i2 == 8 && Math.abs(this.f3026d - this.f3024b) == 1 && this.f3026d == bVar.f3024b && this.f3024b == bVar.f3026d) {
                return true;
            }
            if (this.f3026d != bVar.f3026d || this.f3024b != bVar.f3024b) {
                return false;
            }
            Object obj2 = this.f3025c;
            if (obj2 != null) {
                if (!obj2.equals(bVar.f3025c)) {
                    return false;
                }
            } else if (bVar.f3025c != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.f3023a * 31) + this.f3024b) * 31) + this.f3026d;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + a() + ",s:" + this.f3024b + "c:" + this.f3026d + ",p:" + this.f3025c + "]";
        }
    }

    public a(InterfaceC0056a interfaceC0056a) {
        this(interfaceC0056a, false);
    }

    @Override // b.r.d.i.a
    public void a(b bVar) {
        if (this.f3020f) {
            return;
        }
        bVar.f3025c = null;
        this.f3015a.a(bVar);
    }

    @Override // b.r.d.i.a
    public b b(int i2, int i3, int i4, Object obj) {
        b bVarB = this.f3015a.b();
        if (bVarB == null) {
            return new b(i2, i3, i4, obj);
        }
        bVarB.f3023a = i2;
        bVarB.f3024b = i3;
        bVarB.f3026d = i4;
        bVarB.f3025c = obj;
        return bVarB;
    }

    public final void c(b bVar) {
        t(bVar);
    }

    public final void d(b bVar) {
        t(bVar);
    }

    public int e(int i2) {
        int size = this.f3016b.size();
        for (int i3 = 0; i3 < size; i3++) {
            b bVar = this.f3016b.get(i3);
            int i4 = bVar.f3023a;
            if (i4 != 1) {
                if (i4 == 2) {
                    int i5 = bVar.f3024b;
                    if (i5 <= i2) {
                        int i6 = bVar.f3026d;
                        if (i5 + i6 > i2) {
                            return -1;
                        }
                        i2 -= i6;
                    } else {
                        continue;
                    }
                } else if (i4 == 8) {
                    int i7 = bVar.f3024b;
                    if (i7 == i2) {
                        i2 = bVar.f3026d;
                    } else {
                        if (i7 < i2) {
                            i2--;
                        }
                        if (bVar.f3026d <= i2) {
                            i2++;
                        }
                    }
                }
            } else if (bVar.f3024b <= i2) {
                i2 += bVar.f3026d;
            }
        }
        return i2;
    }

    public final void f(b bVar) {
        boolean z;
        byte b2;
        int i2 = bVar.f3024b;
        int i3 = bVar.f3026d + i2;
        byte b3 = -1;
        int i4 = i2;
        int i5 = 0;
        while (i4 < i3) {
            if (this.f3018d.c(i4) != null || h(i4)) {
                if (b3 == 0) {
                    k(b(2, i2, i5, null));
                    z = true;
                } else {
                    z = false;
                }
                b2 = 1;
            } else {
                if (b3 == 1) {
                    t(b(2, i2, i5, null));
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
        if (i5 != bVar.f3026d) {
            a(bVar);
            bVar = b(2, i2, i5, null);
        }
        if (b3 == 0) {
            k(bVar);
        } else {
            t(bVar);
        }
    }

    public final void g(b bVar) {
        int i2 = bVar.f3024b;
        int i3 = bVar.f3026d + i2;
        int i4 = 0;
        byte b2 = -1;
        int i5 = i2;
        while (i2 < i3) {
            if (this.f3018d.c(i2) != null || h(i2)) {
                if (b2 == 0) {
                    k(b(4, i5, i4, bVar.f3025c));
                    i5 = i2;
                    i4 = 0;
                }
                b2 = 1;
            } else {
                if (b2 == 1) {
                    t(b(4, i5, i4, bVar.f3025c));
                    i5 = i2;
                    i4 = 0;
                }
                b2 = 0;
            }
            i4++;
            i2++;
        }
        if (i4 != bVar.f3026d) {
            Object obj = bVar.f3025c;
            a(bVar);
            bVar = b(4, i5, i4, obj);
        }
        if (b2 == 0) {
            k(bVar);
        } else {
            t(bVar);
        }
    }

    public final boolean h(int i2) {
        int size = this.f3017c.size();
        for (int i3 = 0; i3 < size; i3++) {
            b bVar = this.f3017c.get(i3);
            int i4 = bVar.f3023a;
            if (i4 == 8) {
                if (n(bVar.f3026d, i3 + 1) == i2) {
                    return true;
                }
            } else if (i4 == 1) {
                int i5 = bVar.f3024b;
                int i6 = bVar.f3026d + i5;
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
        int size = this.f3017c.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f3018d.b(this.f3017c.get(i2));
        }
        v(this.f3017c);
        this.f3022h = 0;
    }

    public void j() {
        i();
        int size = this.f3016b.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.f3016b.get(i2);
            int i3 = bVar.f3023a;
            if (i3 == 1) {
                this.f3018d.b(bVar);
                this.f3018d.g(bVar.f3024b, bVar.f3026d);
            } else if (i3 == 2) {
                this.f3018d.b(bVar);
                this.f3018d.d(bVar.f3024b, bVar.f3026d);
            } else if (i3 == 4) {
                this.f3018d.b(bVar);
                this.f3018d.h(bVar.f3024b, bVar.f3026d, bVar.f3025c);
            } else if (i3 == 8) {
                this.f3018d.b(bVar);
                this.f3018d.a(bVar.f3024b, bVar.f3026d);
            }
            Runnable runnable = this.f3019e;
            if (runnable != null) {
                runnable.run();
            }
        }
        v(this.f3016b);
        this.f3022h = 0;
    }

    public final void k(b bVar) {
        int i2;
        int i3 = bVar.f3023a;
        if (i3 == 1 || i3 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iX = x(bVar.f3024b, i3);
        int i4 = bVar.f3024b;
        int i5 = bVar.f3023a;
        if (i5 == 2) {
            i2 = 0;
        } else {
            if (i5 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + bVar);
            }
            i2 = 1;
        }
        int i6 = 1;
        for (int i7 = 1; i7 < bVar.f3026d; i7++) {
            int iX2 = x(bVar.f3024b + (i2 * i7), bVar.f3023a);
            int i8 = bVar.f3023a;
            if (i8 == 2 ? iX2 == iX : i8 == 4 && iX2 == iX + 1) {
                i6++;
            } else {
                b bVarB = b(i8, iX, i6, bVar.f3025c);
                l(bVarB, i4);
                a(bVarB);
                if (bVar.f3023a == 4) {
                    i4 += i6;
                }
                i6 = 1;
                iX = iX2;
            }
        }
        Object obj = bVar.f3025c;
        a(bVar);
        if (i6 > 0) {
            b bVarB2 = b(bVar.f3023a, iX, i6, obj);
            l(bVarB2, i4);
            a(bVarB2);
        }
    }

    public void l(b bVar, int i2) {
        this.f3018d.f(bVar);
        int i3 = bVar.f3023a;
        if (i3 == 2) {
            this.f3018d.d(i2, bVar.f3026d);
        } else {
            if (i3 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            this.f3018d.h(i2, bVar.f3026d, bVar.f3025c);
        }
    }

    public int m(int i2) {
        return n(i2, 0);
    }

    public int n(int i2, int i3) {
        int size = this.f3017c.size();
        while (i3 < size) {
            b bVar = this.f3017c.get(i3);
            int i4 = bVar.f3023a;
            if (i4 == 8) {
                int i5 = bVar.f3024b;
                if (i5 == i2) {
                    i2 = bVar.f3026d;
                } else {
                    if (i5 < i2) {
                        i2--;
                    }
                    if (bVar.f3026d <= i2) {
                        i2++;
                    }
                }
            } else {
                int i6 = bVar.f3024b;
                if (i6 > i2) {
                    continue;
                } else if (i4 == 2) {
                    int i7 = bVar.f3026d;
                    if (i2 < i6 + i7) {
                        return -1;
                    }
                    i2 -= i7;
                } else if (i4 == 1) {
                    i2 += bVar.f3026d;
                }
            }
            i3++;
        }
        return i2;
    }

    public boolean o(int i2) {
        return (i2 & this.f3022h) != 0;
    }

    public boolean p() {
        return this.f3016b.size() > 0;
    }

    public boolean q() {
        return (this.f3017c.isEmpty() || this.f3016b.isEmpty()) ? false : true;
    }

    public boolean r(int i2, int i3, Object obj) {
        if (i3 < 1) {
            return false;
        }
        this.f3016b.add(b(4, i2, i3, obj));
        this.f3022h |= 4;
        return this.f3016b.size() == 1;
    }

    public boolean s(int i2, int i3) {
        if (i3 < 1) {
            return false;
        }
        this.f3016b.add(b(1, i2, i3, null));
        this.f3022h |= 1;
        return this.f3016b.size() == 1;
    }

    public final void t(b bVar) {
        this.f3017c.add(bVar);
        int i2 = bVar.f3023a;
        if (i2 == 1) {
            this.f3018d.g(bVar.f3024b, bVar.f3026d);
            return;
        }
        if (i2 == 2) {
            this.f3018d.e(bVar.f3024b, bVar.f3026d);
            return;
        }
        if (i2 == 4) {
            this.f3018d.h(bVar.f3024b, bVar.f3026d, bVar.f3025c);
        } else {
            if (i2 == 8) {
                this.f3018d.a(bVar.f3024b, bVar.f3026d);
                return;
            }
            throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    public void u() {
        this.f3021g.b(this.f3016b);
        int size = this.f3016b.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.f3016b.get(i2);
            int i3 = bVar.f3023a;
            if (i3 == 1) {
                c(bVar);
            } else if (i3 == 2) {
                f(bVar);
            } else if (i3 == 4) {
                g(bVar);
            } else if (i3 == 8) {
                d(bVar);
            }
            Runnable runnable = this.f3019e;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.f3016b.clear();
    }

    public void v(List<b> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            a(list.get(i2));
        }
        list.clear();
    }

    public void w() {
        v(this.f3016b);
        v(this.f3017c);
        this.f3022h = 0;
    }

    public final int x(int i2, int i3) {
        int i4;
        int i5;
        for (int size = this.f3017c.size() - 1; size >= 0; size--) {
            b bVar = this.f3017c.get(size);
            int i6 = bVar.f3023a;
            if (i6 == 8) {
                int i7 = bVar.f3024b;
                int i8 = bVar.f3026d;
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
                            bVar.f3024b = i7 + 1;
                            bVar.f3026d = i8 + 1;
                        } else if (i3 == 2) {
                            bVar.f3024b = i7 - 1;
                            bVar.f3026d = i8 - 1;
                        }
                    }
                } else if (i5 == i7) {
                    if (i3 == 1) {
                        bVar.f3026d = i8 + 1;
                    } else if (i3 == 2) {
                        bVar.f3026d = i8 - 1;
                    }
                    i2++;
                } else {
                    if (i3 == 1) {
                        bVar.f3024b = i7 + 1;
                    } else if (i3 == 2) {
                        bVar.f3024b = i7 - 1;
                    }
                    i2--;
                }
            } else {
                int i9 = bVar.f3024b;
                if (i9 <= i2) {
                    if (i6 == 1) {
                        i2 -= bVar.f3026d;
                    } else if (i6 == 2) {
                        i2 += bVar.f3026d;
                    }
                } else if (i3 == 1) {
                    bVar.f3024b = i9 + 1;
                } else if (i3 == 2) {
                    bVar.f3024b = i9 - 1;
                }
            }
        }
        for (int size2 = this.f3017c.size() - 1; size2 >= 0; size2--) {
            b bVar2 = this.f3017c.get(size2);
            if (bVar2.f3023a == 8) {
                int i10 = bVar2.f3026d;
                if (i10 == bVar2.f3024b || i10 < 0) {
                    this.f3017c.remove(size2);
                    a(bVar2);
                }
            } else if (bVar2.f3026d <= 0) {
                this.f3017c.remove(size2);
                a(bVar2);
            }
        }
        return i2;
    }

    public a(InterfaceC0056a interfaceC0056a, boolean z) {
        this.f3015a = new b.h.l.e(30);
        this.f3016b = new ArrayList<>();
        this.f3017c = new ArrayList<>();
        this.f3022h = 0;
        this.f3018d = interfaceC0056a;
        this.f3020f = z;
        this.f3021g = new i(this);
    }
}
