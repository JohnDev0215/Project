package w0;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import w0.i;

/* JADX INFO: compiled from: AdapterHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements i.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public k0.e<b> f5517a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList<b> f5518b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<b> f5519c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final InterfaceC0068a f5520d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Runnable f5521e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f5522f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final i f5523g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f5524h;

    /* JADX INFO: renamed from: w0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AdapterHelper.java */
    public interface InterfaceC0068a {
        void a(int i3, int i4);

        void b(b bVar);

        RecyclerView.c0 c(int i3);

        void d(int i3, int i4);

        void e(int i3, int i4);

        void f(b bVar);

        void g(int i3, int i4);

        void h(int i3, int i4, Object obj);
    }

    /* JADX INFO: compiled from: AdapterHelper.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f5525a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f5526b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Object f5527c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5528d;

        public b(int i3, int i4, int i5, Object obj) {
            this.f5525a = i3;
            this.f5526b = i4;
            this.f5528d = i5;
            this.f5527c = obj;
        }

        public String a() {
            int i3 = this.f5525a;
            return i3 != 1 ? i3 != 2 ? i3 != 4 ? i3 != 8 ? "??" : "mv" : "up" : "rm" : "add";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            int i3 = this.f5525a;
            if (i3 != bVar.f5525a) {
                return false;
            }
            if (i3 == 8 && Math.abs(this.f5528d - this.f5526b) == 1 && this.f5528d == bVar.f5526b && this.f5526b == bVar.f5528d) {
                return true;
            }
            if (this.f5528d != bVar.f5528d || this.f5526b != bVar.f5526b) {
                return false;
            }
            Object obj2 = this.f5527c;
            if (obj2 != null) {
                if (!obj2.equals(bVar.f5527c)) {
                    return false;
                }
            } else if (bVar.f5527c != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.f5525a * 31) + this.f5526b) * 31) + this.f5528d;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + a() + ",s:" + this.f5526b + "c:" + this.f5528d + ",p:" + this.f5527c + "]";
        }
    }

    public a(InterfaceC0068a interfaceC0068a) {
        this(interfaceC0068a, false);
    }

    @Override // w0.i.a
    public void a(b bVar) {
        if (this.f5522f) {
            return;
        }
        bVar.f5527c = null;
        this.f5517a.a(bVar);
    }

    @Override // w0.i.a
    public b b(int i3, int i4, int i5, Object obj) {
        b bVarB = this.f5517a.b();
        if (bVarB == null) {
            return new b(i3, i4, i5, obj);
        }
        bVarB.f5525a = i3;
        bVarB.f5526b = i4;
        bVarB.f5528d = i5;
        bVarB.f5527c = obj;
        return bVarB;
    }

    public final void c(b bVar) {
        r(bVar);
    }

    public final void d(b bVar) {
        r(bVar);
    }

    public int e(int i3) {
        int size = this.f5518b.size();
        for (int i4 = 0; i4 < size; i4++) {
            b bVar = this.f5518b.get(i4);
            int i5 = bVar.f5525a;
            if (i5 != 1) {
                if (i5 == 2) {
                    int i6 = bVar.f5526b;
                    if (i6 <= i3) {
                        int i7 = bVar.f5528d;
                        if (i6 + i7 > i3) {
                            return -1;
                        }
                        i3 -= i7;
                    } else {
                        continue;
                    }
                } else if (i5 == 8) {
                    int i8 = bVar.f5526b;
                    if (i8 == i3) {
                        i3 = bVar.f5528d;
                    } else {
                        if (i8 < i3) {
                            i3--;
                        }
                        if (bVar.f5528d <= i3) {
                            i3++;
                        }
                    }
                }
            } else if (bVar.f5526b <= i3) {
                i3 += bVar.f5528d;
            }
        }
        return i3;
    }

    public final void f(b bVar) {
        boolean z3;
        byte b3;
        int i3 = bVar.f5526b;
        int i4 = bVar.f5528d + i3;
        byte b4 = -1;
        int i5 = i3;
        int i6 = 0;
        while (i5 < i4) {
            if (this.f5520d.c(i5) != null || h(i5)) {
                if (b4 == 0) {
                    k(b(2, i3, i6, null));
                    z3 = true;
                } else {
                    z3 = false;
                }
                b3 = 1;
            } else {
                if (b4 == 1) {
                    r(b(2, i3, i6, null));
                    z3 = true;
                } else {
                    z3 = false;
                }
                b3 = 0;
            }
            if (z3) {
                i5 -= i6;
                i4 -= i6;
                i6 = 1;
            } else {
                i6++;
            }
            i5++;
            b4 = b3;
        }
        if (i6 != bVar.f5528d) {
            a(bVar);
            bVar = b(2, i3, i6, null);
        }
        if (b4 == 0) {
            k(bVar);
        } else {
            r(bVar);
        }
    }

    public final void g(b bVar) {
        int i3 = bVar.f5526b;
        int i4 = bVar.f5528d + i3;
        int i5 = 0;
        byte b3 = -1;
        int i6 = i3;
        while (i3 < i4) {
            if (this.f5520d.c(i3) != null || h(i3)) {
                if (b3 == 0) {
                    k(b(4, i6, i5, bVar.f5527c));
                    i6 = i3;
                    i5 = 0;
                }
                b3 = 1;
            } else {
                if (b3 == 1) {
                    r(b(4, i6, i5, bVar.f5527c));
                    i6 = i3;
                    i5 = 0;
                }
                b3 = 0;
            }
            i5++;
            i3++;
        }
        if (i5 != bVar.f5528d) {
            Object obj = bVar.f5527c;
            a(bVar);
            bVar = b(4, i6, i5, obj);
        }
        if (b3 == 0) {
            k(bVar);
        } else {
            r(bVar);
        }
    }

    public final boolean h(int i3) {
        int size = this.f5519c.size();
        for (int i4 = 0; i4 < size; i4++) {
            b bVar = this.f5519c.get(i4);
            int i5 = bVar.f5525a;
            if (i5 == 8) {
                if (n(bVar.f5528d, i4 + 1) == i3) {
                    return true;
                }
            } else if (i5 == 1) {
                int i6 = bVar.f5526b;
                int i7 = bVar.f5528d + i6;
                while (i6 < i7) {
                    if (n(i6, i4 + 1) == i3) {
                        return true;
                    }
                    i6++;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    public void i() {
        int size = this.f5519c.size();
        for (int i3 = 0; i3 < size; i3++) {
            this.f5520d.b(this.f5519c.get(i3));
        }
        t(this.f5519c);
        this.f5524h = 0;
    }

    public void j() {
        i();
        int size = this.f5518b.size();
        for (int i3 = 0; i3 < size; i3++) {
            b bVar = this.f5518b.get(i3);
            int i4 = bVar.f5525a;
            if (i4 == 1) {
                this.f5520d.b(bVar);
                this.f5520d.g(bVar.f5526b, bVar.f5528d);
            } else if (i4 == 2) {
                this.f5520d.b(bVar);
                this.f5520d.d(bVar.f5526b, bVar.f5528d);
            } else if (i4 == 4) {
                this.f5520d.b(bVar);
                this.f5520d.h(bVar.f5526b, bVar.f5528d, bVar.f5527c);
            } else if (i4 == 8) {
                this.f5520d.b(bVar);
                this.f5520d.a(bVar.f5526b, bVar.f5528d);
            }
            Runnable runnable = this.f5521e;
            if (runnable != null) {
                runnable.run();
            }
        }
        t(this.f5518b);
        this.f5524h = 0;
    }

    public final void k(b bVar) {
        int i3;
        int i4 = bVar.f5525a;
        if (i4 == 1 || i4 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iV = v(bVar.f5526b, i4);
        int i5 = bVar.f5526b;
        int i6 = bVar.f5525a;
        if (i6 == 2) {
            i3 = 0;
        } else {
            if (i6 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + bVar);
            }
            i3 = 1;
        }
        int i7 = 1;
        for (int i8 = 1; i8 < bVar.f5528d; i8++) {
            int iV2 = v(bVar.f5526b + (i3 * i8), bVar.f5525a);
            int i9 = bVar.f5525a;
            if (i9 == 2 ? iV2 == iV : i9 == 4 && iV2 == iV + 1) {
                i7++;
            } else {
                b bVarB = b(i9, iV, i7, bVar.f5527c);
                l(bVarB, i5);
                a(bVarB);
                if (bVar.f5525a == 4) {
                    i5 += i7;
                }
                i7 = 1;
                iV = iV2;
            }
        }
        Object obj = bVar.f5527c;
        a(bVar);
        if (i7 > 0) {
            b bVarB2 = b(bVar.f5525a, iV, i7, obj);
            l(bVarB2, i5);
            a(bVarB2);
        }
    }

    public void l(b bVar, int i3) {
        this.f5520d.f(bVar);
        int i4 = bVar.f5525a;
        if (i4 == 2) {
            this.f5520d.d(i3, bVar.f5528d);
        } else {
            if (i4 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            this.f5520d.h(i3, bVar.f5528d, bVar.f5527c);
        }
    }

    public int m(int i3) {
        return n(i3, 0);
    }

    public int n(int i3, int i4) {
        int size = this.f5519c.size();
        while (i4 < size) {
            b bVar = this.f5519c.get(i4);
            int i5 = bVar.f5525a;
            if (i5 == 8) {
                int i6 = bVar.f5526b;
                if (i6 == i3) {
                    i3 = bVar.f5528d;
                } else {
                    if (i6 < i3) {
                        i3--;
                    }
                    if (bVar.f5528d <= i3) {
                        i3++;
                    }
                }
            } else {
                int i7 = bVar.f5526b;
                if (i7 > i3) {
                    continue;
                } else if (i5 == 2) {
                    int i8 = bVar.f5528d;
                    if (i3 < i7 + i8) {
                        return -1;
                    }
                    i3 -= i8;
                } else if (i5 == 1) {
                    i3 += bVar.f5528d;
                }
            }
            i4++;
        }
        return i3;
    }

    public boolean o(int i3) {
        return (i3 & this.f5524h) != 0;
    }

    public boolean p() {
        return this.f5518b.size() > 0;
    }

    public boolean q() {
        return (this.f5519c.isEmpty() || this.f5518b.isEmpty()) ? false : true;
    }

    public final void r(b bVar) {
        this.f5519c.add(bVar);
        int i3 = bVar.f5525a;
        if (i3 == 1) {
            this.f5520d.g(bVar.f5526b, bVar.f5528d);
            return;
        }
        if (i3 == 2) {
            this.f5520d.e(bVar.f5526b, bVar.f5528d);
            return;
        }
        if (i3 == 4) {
            this.f5520d.h(bVar.f5526b, bVar.f5528d, bVar.f5527c);
        } else {
            if (i3 == 8) {
                this.f5520d.a(bVar.f5526b, bVar.f5528d);
                return;
            }
            throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    public void s() {
        this.f5523g.b(this.f5518b);
        int size = this.f5518b.size();
        for (int i3 = 0; i3 < size; i3++) {
            b bVar = this.f5518b.get(i3);
            int i4 = bVar.f5525a;
            if (i4 == 1) {
                c(bVar);
            } else if (i4 == 2) {
                f(bVar);
            } else if (i4 == 4) {
                g(bVar);
            } else if (i4 == 8) {
                d(bVar);
            }
            Runnable runnable = this.f5521e;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.f5518b.clear();
    }

    public void t(List<b> list) {
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            a(list.get(i3));
        }
        list.clear();
    }

    public void u() {
        t(this.f5518b);
        t(this.f5519c);
        this.f5524h = 0;
    }

    public final int v(int i3, int i4) {
        int i5;
        int i6;
        for (int size = this.f5519c.size() - 1; size >= 0; size--) {
            b bVar = this.f5519c.get(size);
            int i7 = bVar.f5525a;
            if (i7 == 8) {
                int i8 = bVar.f5526b;
                int i9 = bVar.f5528d;
                if (i8 < i9) {
                    i6 = i8;
                    i5 = i9;
                } else {
                    i5 = i8;
                    i6 = i9;
                }
                if (i3 < i6 || i3 > i5) {
                    if (i3 < i8) {
                        if (i4 == 1) {
                            bVar.f5526b = i8 + 1;
                            bVar.f5528d = i9 + 1;
                        } else if (i4 == 2) {
                            bVar.f5526b = i8 - 1;
                            bVar.f5528d = i9 - 1;
                        }
                    }
                } else if (i6 == i8) {
                    if (i4 == 1) {
                        bVar.f5528d = i9 + 1;
                    } else if (i4 == 2) {
                        bVar.f5528d = i9 - 1;
                    }
                    i3++;
                } else {
                    if (i4 == 1) {
                        bVar.f5526b = i8 + 1;
                    } else if (i4 == 2) {
                        bVar.f5526b = i8 - 1;
                    }
                    i3--;
                }
            } else {
                int i10 = bVar.f5526b;
                if (i10 <= i3) {
                    if (i7 == 1) {
                        i3 -= bVar.f5528d;
                    } else if (i7 == 2) {
                        i3 += bVar.f5528d;
                    }
                } else if (i4 == 1) {
                    bVar.f5526b = i10 + 1;
                } else if (i4 == 2) {
                    bVar.f5526b = i10 - 1;
                }
            }
        }
        for (int size2 = this.f5519c.size() - 1; size2 >= 0; size2--) {
            b bVar2 = this.f5519c.get(size2);
            if (bVar2.f5525a == 8) {
                int i11 = bVar2.f5528d;
                if (i11 == bVar2.f5526b || i11 < 0) {
                    this.f5519c.remove(size2);
                    a(bVar2);
                }
            } else if (bVar2.f5528d <= 0) {
                this.f5519c.remove(size2);
                a(bVar2);
            }
        }
        return i3;
    }

    public a(InterfaceC0068a interfaceC0068a, boolean z3) {
        this.f5517a = new k0.f(30);
        this.f5518b = new ArrayList<>();
        this.f5519c = new ArrayList<>();
        this.f5524h = 0;
        this.f5520d = interfaceC0068a;
        this.f5522f = z3;
        this.f5523g = new i(this);
    }
}
