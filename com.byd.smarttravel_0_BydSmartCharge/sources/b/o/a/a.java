package b.o.a;

import androidx.recyclerview.widget.RecyclerView;
import b.o.a.i;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AdapterHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements i.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.g.i.e<b> f1330a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList<b> f1331b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<b> f1332c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final InterfaceC0034a f1333d;
    public Runnable e;
    public final boolean f;
    public final i g;
    public int h;

    /* JADX INFO: renamed from: b.o.a.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AdapterHelper.java */
    public interface InterfaceC0034a {
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
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1334a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1335b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Object f1336c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1337d;

        public b(int i, int i2, int i3, Object obj) {
            this.f1334a = i;
            this.f1335b = i2;
            this.f1337d = i3;
            this.f1336c = obj;
        }

        public String a() {
            int i = this.f1334a;
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
            int i = this.f1334a;
            if (i != bVar.f1334a) {
                return false;
            }
            if (i == 8 && Math.abs(this.f1337d - this.f1335b) == 1 && this.f1337d == bVar.f1335b && this.f1335b == bVar.f1337d) {
                return true;
            }
            if (this.f1337d != bVar.f1337d || this.f1335b != bVar.f1335b) {
                return false;
            }
            Object obj2 = this.f1336c;
            if (obj2 != null) {
                if (!obj2.equals(bVar.f1336c)) {
                    return false;
                }
            } else if (bVar.f1336c != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.f1334a * 31) + this.f1335b) * 31) + this.f1337d;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + a() + ",s:" + this.f1335b + "c:" + this.f1337d + ",p:" + this.f1336c + "]";
        }
    }

    public a(InterfaceC0034a interfaceC0034a) {
        this(interfaceC0034a, false);
    }

    @Override // b.o.a.i.a
    public void a(b bVar) {
        if (this.f) {
            return;
        }
        bVar.f1336c = null;
        this.f1330a.a(bVar);
    }

    @Override // b.o.a.i.a
    public b b(int i, int i2, int i3, Object obj) {
        b bVarB = this.f1330a.b();
        if (bVarB == null) {
            return new b(i, i2, i3, obj);
        }
        bVarB.f1334a = i;
        bVarB.f1335b = i2;
        bVarB.f1337d = i3;
        bVarB.f1336c = obj;
        return bVarB;
    }

    public final void c(b bVar) {
        r(bVar);
    }

    public final void d(b bVar) {
        r(bVar);
    }

    public int e(int i) {
        int size = this.f1331b.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.f1331b.get(i2);
            int i3 = bVar.f1334a;
            if (i3 != 1) {
                if (i3 == 2) {
                    int i4 = bVar.f1335b;
                    if (i4 <= i) {
                        int i5 = bVar.f1337d;
                        if (i4 + i5 > i) {
                            return -1;
                        }
                        i -= i5;
                    } else {
                        continue;
                    }
                } else if (i3 == 8) {
                    int i6 = bVar.f1335b;
                    if (i6 == i) {
                        i = bVar.f1337d;
                    } else {
                        if (i6 < i) {
                            i--;
                        }
                        if (bVar.f1337d <= i) {
                            i++;
                        }
                    }
                }
            } else if (bVar.f1335b <= i) {
                i += bVar.f1337d;
            }
        }
        return i;
    }

    public final void f(b bVar) {
        boolean z;
        byte b2;
        int i = bVar.f1335b;
        int i2 = bVar.f1337d + i;
        byte b3 = -1;
        int i3 = i;
        int i4 = 0;
        while (i3 < i2) {
            if (this.f1333d.c(i3) != null || h(i3)) {
                if (b3 == 0) {
                    k(b(2, i, i4, null));
                    z = true;
                } else {
                    z = false;
                }
                b2 = 1;
            } else {
                if (b3 == 1) {
                    r(b(2, i, i4, null));
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
        if (i4 != bVar.f1337d) {
            a(bVar);
            bVar = b(2, i, i4, null);
        }
        if (b3 == 0) {
            k(bVar);
        } else {
            r(bVar);
        }
    }

    public final void g(b bVar) {
        int i = bVar.f1335b;
        int i2 = bVar.f1337d + i;
        int i3 = 0;
        byte b2 = -1;
        int i4 = i;
        while (i < i2) {
            if (this.f1333d.c(i) != null || h(i)) {
                if (b2 == 0) {
                    k(b(4, i4, i3, bVar.f1336c));
                    i4 = i;
                    i3 = 0;
                }
                b2 = 1;
            } else {
                if (b2 == 1) {
                    r(b(4, i4, i3, bVar.f1336c));
                    i4 = i;
                    i3 = 0;
                }
                b2 = 0;
            }
            i3++;
            i++;
        }
        if (i3 != bVar.f1337d) {
            Object obj = bVar.f1336c;
            a(bVar);
            bVar = b(4, i4, i3, obj);
        }
        if (b2 == 0) {
            k(bVar);
        } else {
            r(bVar);
        }
    }

    public final boolean h(int i) {
        int size = this.f1332c.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.f1332c.get(i2);
            int i3 = bVar.f1334a;
            if (i3 == 8) {
                if (n(bVar.f1337d, i2 + 1) == i) {
                    return true;
                }
            } else if (i3 == 1) {
                int i4 = bVar.f1335b;
                int i5 = bVar.f1337d + i4;
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

    public void i() {
        int size = this.f1332c.size();
        for (int i = 0; i < size; i++) {
            this.f1333d.b(this.f1332c.get(i));
        }
        t(this.f1332c);
        this.h = 0;
    }

    public void j() {
        i();
        int size = this.f1331b.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.f1331b.get(i);
            int i2 = bVar.f1334a;
            if (i2 == 1) {
                this.f1333d.b(bVar);
                this.f1333d.g(bVar.f1335b, bVar.f1337d);
            } else if (i2 == 2) {
                this.f1333d.b(bVar);
                this.f1333d.d(bVar.f1335b, bVar.f1337d);
            } else if (i2 == 4) {
                this.f1333d.b(bVar);
                this.f1333d.h(bVar.f1335b, bVar.f1337d, bVar.f1336c);
            } else if (i2 == 8) {
                this.f1333d.b(bVar);
                this.f1333d.a(bVar.f1335b, bVar.f1337d);
            }
            Runnable runnable = this.e;
            if (runnable != null) {
                runnable.run();
            }
        }
        t(this.f1331b);
        this.h = 0;
    }

    public final void k(b bVar) {
        int i;
        int i2 = bVar.f1334a;
        if (i2 == 1 || i2 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iV = v(bVar.f1335b, i2);
        int i3 = bVar.f1335b;
        int i4 = bVar.f1334a;
        if (i4 == 2) {
            i = 0;
        } else {
            if (i4 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + bVar);
            }
            i = 1;
        }
        int i5 = 1;
        for (int i6 = 1; i6 < bVar.f1337d; i6++) {
            int iV2 = v(bVar.f1335b + (i * i6), bVar.f1334a);
            int i7 = bVar.f1334a;
            if (i7 == 2 ? iV2 == iV : i7 == 4 && iV2 == iV + 1) {
                i5++;
            } else {
                b bVarB = b(i7, iV, i5, bVar.f1336c);
                l(bVarB, i3);
                a(bVarB);
                if (bVar.f1334a == 4) {
                    i3 += i5;
                }
                i5 = 1;
                iV = iV2;
            }
        }
        Object obj = bVar.f1336c;
        a(bVar);
        if (i5 > 0) {
            b bVarB2 = b(bVar.f1334a, iV, i5, obj);
            l(bVarB2, i3);
            a(bVarB2);
        }
    }

    public void l(b bVar, int i) {
        this.f1333d.f(bVar);
        int i2 = bVar.f1334a;
        if (i2 == 2) {
            this.f1333d.d(i, bVar.f1337d);
        } else {
            if (i2 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            this.f1333d.h(i, bVar.f1337d, bVar.f1336c);
        }
    }

    public int m(int i) {
        return n(i, 0);
    }

    public int n(int i, int i2) {
        int size = this.f1332c.size();
        while (i2 < size) {
            b bVar = this.f1332c.get(i2);
            int i3 = bVar.f1334a;
            if (i3 == 8) {
                int i4 = bVar.f1335b;
                if (i4 == i) {
                    i = bVar.f1337d;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (bVar.f1337d <= i) {
                        i++;
                    }
                }
            } else {
                int i5 = bVar.f1335b;
                if (i5 > i) {
                    continue;
                } else if (i3 == 2) {
                    int i6 = bVar.f1337d;
                    if (i < i5 + i6) {
                        return -1;
                    }
                    i -= i6;
                } else if (i3 == 1) {
                    i += bVar.f1337d;
                }
            }
            i2++;
        }
        return i;
    }

    public boolean o(int i) {
        return (i & this.h) != 0;
    }

    public boolean p() {
        return this.f1331b.size() > 0;
    }

    public boolean q() {
        return (this.f1332c.isEmpty() || this.f1331b.isEmpty()) ? false : true;
    }

    public final void r(b bVar) {
        this.f1332c.add(bVar);
        int i = bVar.f1334a;
        if (i == 1) {
            this.f1333d.g(bVar.f1335b, bVar.f1337d);
            return;
        }
        if (i == 2) {
            this.f1333d.e(bVar.f1335b, bVar.f1337d);
            return;
        }
        if (i == 4) {
            this.f1333d.h(bVar.f1335b, bVar.f1337d, bVar.f1336c);
        } else {
            if (i == 8) {
                this.f1333d.a(bVar.f1335b, bVar.f1337d);
                return;
            }
            throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    public void s() {
        this.g.b(this.f1331b);
        int size = this.f1331b.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.f1331b.get(i);
            int i2 = bVar.f1334a;
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
        this.f1331b.clear();
    }

    public void t(List<b> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            a(list.get(i));
        }
        list.clear();
    }

    public void u() {
        t(this.f1331b);
        t(this.f1332c);
        this.h = 0;
    }

    public final int v(int i, int i2) {
        int i3;
        int i4;
        for (int size = this.f1332c.size() - 1; size >= 0; size--) {
            b bVar = this.f1332c.get(size);
            int i5 = bVar.f1334a;
            if (i5 == 8) {
                int i6 = bVar.f1335b;
                int i7 = bVar.f1337d;
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
                            bVar.f1335b = i6 + 1;
                            bVar.f1337d = i7 + 1;
                        } else if (i2 == 2) {
                            bVar.f1335b = i6 - 1;
                            bVar.f1337d = i7 - 1;
                        }
                    }
                } else if (i4 == i6) {
                    if (i2 == 1) {
                        bVar.f1337d = i7 + 1;
                    } else if (i2 == 2) {
                        bVar.f1337d = i7 - 1;
                    }
                    i++;
                } else {
                    if (i2 == 1) {
                        bVar.f1335b = i6 + 1;
                    } else if (i2 == 2) {
                        bVar.f1335b = i6 - 1;
                    }
                    i--;
                }
            } else {
                int i8 = bVar.f1335b;
                if (i8 <= i) {
                    if (i5 == 1) {
                        i -= bVar.f1337d;
                    } else if (i5 == 2) {
                        i += bVar.f1337d;
                    }
                } else if (i2 == 1) {
                    bVar.f1335b = i8 + 1;
                } else if (i2 == 2) {
                    bVar.f1335b = i8 - 1;
                }
            }
        }
        for (int size2 = this.f1332c.size() - 1; size2 >= 0; size2--) {
            b bVar2 = this.f1332c.get(size2);
            if (bVar2.f1334a == 8) {
                int i9 = bVar2.f1337d;
                if (i9 == bVar2.f1335b || i9 < 0) {
                    this.f1332c.remove(size2);
                    a(bVar2);
                }
            } else if (bVar2.f1337d <= 0) {
                this.f1332c.remove(size2);
                a(bVar2);
            }
        }
        return i;
    }

    public a(InterfaceC0034a interfaceC0034a, boolean z) {
        this.f1330a = new b.g.i.f(30);
        this.f1331b = new ArrayList<>();
        this.f1332c = new ArrayList<>();
        this.h = 0;
        this.f1333d = interfaceC0034a;
        this.f = z;
        this.g = new i(this);
    }
}
