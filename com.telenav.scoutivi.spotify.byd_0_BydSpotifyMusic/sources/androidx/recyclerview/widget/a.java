package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.l;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AdapterHelper.java */
/* JADX INFO: loaded from: classes.dex */
class a implements l.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private h0.e<b> f3006a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayList<b> f3007b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ArrayList<b> f3008c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final InterfaceC0027a f3009d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    Runnable f3010e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final boolean f3011f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    final l f3012g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f3013h;

    /* JADX INFO: renamed from: androidx.recyclerview.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AdapterHelper.java */
    interface InterfaceC0027a {
        void a(int i7, int i8);

        void b(b bVar);

        RecyclerView.c0 c(int i7);

        void d(int i7, int i8);

        void e(int i7, int i8);

        void f(b bVar);

        void g(int i7, int i8);

        void h(int i7, int i8, Object obj);
    }

    /* JADX INFO: compiled from: AdapterHelper.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f3014a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f3015b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        Object f3016c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f3017d;

        b(int i7, int i8, int i9, Object obj) {
            this.f3014a = i7;
            this.f3015b = i8;
            this.f3017d = i9;
            this.f3016c = obj;
        }

        String a() {
            int i7 = this.f3014a;
            return i7 != 1 ? i7 != 2 ? i7 != 4 ? i7 != 8 ? "??" : "mv" : "up" : "rm" : "add";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            int i7 = this.f3014a;
            if (i7 != bVar.f3014a) {
                return false;
            }
            if (i7 == 8 && Math.abs(this.f3017d - this.f3015b) == 1 && this.f3017d == bVar.f3015b && this.f3015b == bVar.f3017d) {
                return true;
            }
            if (this.f3017d != bVar.f3017d || this.f3015b != bVar.f3015b) {
                return false;
            }
            Object obj2 = this.f3016c;
            if (obj2 != null) {
                if (!obj2.equals(bVar.f3016c)) {
                    return false;
                }
            } else if (bVar.f3016c != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.f3014a * 31) + this.f3015b) * 31) + this.f3017d;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + a() + ",s:" + this.f3015b + "c:" + this.f3017d + ",p:" + this.f3016c + "]";
        }
    }

    a(InterfaceC0027a interfaceC0027a) {
        this(interfaceC0027a, false);
    }

    private void c(b bVar) {
        v(bVar);
    }

    private void d(b bVar) {
        v(bVar);
    }

    private void f(b bVar) {
        boolean z6;
        byte b7;
        int i7 = bVar.f3015b;
        int i8 = bVar.f3017d + i7;
        byte b8 = -1;
        int i9 = i7;
        int i10 = 0;
        while (i9 < i8) {
            if (this.f3009d.c(i9) != null || h(i9)) {
                if (b8 == 0) {
                    k(b(2, i7, i10, null));
                    z6 = true;
                } else {
                    z6 = false;
                }
                b7 = 1;
            } else {
                if (b8 == 1) {
                    v(b(2, i7, i10, null));
                    z6 = true;
                } else {
                    z6 = false;
                }
                b7 = 0;
            }
            if (z6) {
                i9 -= i10;
                i8 -= i10;
                i10 = 1;
            } else {
                i10++;
            }
            i9++;
            b8 = b7;
        }
        if (i10 != bVar.f3017d) {
            a(bVar);
            bVar = b(2, i7, i10, null);
        }
        if (b8 == 0) {
            k(bVar);
        } else {
            v(bVar);
        }
    }

    private void g(b bVar) {
        int i7 = bVar.f3015b;
        int i8 = bVar.f3017d + i7;
        int i9 = 0;
        byte b7 = -1;
        int i10 = i7;
        while (i7 < i8) {
            if (this.f3009d.c(i7) != null || h(i7)) {
                if (b7 == 0) {
                    k(b(4, i10, i9, bVar.f3016c));
                    i10 = i7;
                    i9 = 0;
                }
                b7 = 1;
            } else {
                if (b7 == 1) {
                    v(b(4, i10, i9, bVar.f3016c));
                    i10 = i7;
                    i9 = 0;
                }
                b7 = 0;
            }
            i9++;
            i7++;
        }
        if (i9 != bVar.f3017d) {
            Object obj = bVar.f3016c;
            a(bVar);
            bVar = b(4, i10, i9, obj);
        }
        if (b7 == 0) {
            k(bVar);
        } else {
            v(bVar);
        }
    }

    private boolean h(int i7) {
        int size = this.f3008c.size();
        for (int i8 = 0; i8 < size; i8++) {
            b bVar = this.f3008c.get(i8);
            int i9 = bVar.f3014a;
            if (i9 == 8) {
                if (n(bVar.f3017d, i8 + 1) == i7) {
                    return true;
                }
            } else if (i9 == 1) {
                int i10 = bVar.f3015b;
                int i11 = bVar.f3017d + i10;
                while (i10 < i11) {
                    if (n(i10, i8 + 1) == i7) {
                        return true;
                    }
                    i10++;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    private void k(b bVar) {
        int i7;
        int i8 = bVar.f3014a;
        if (i8 == 1 || i8 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iZ = z(bVar.f3015b, i8);
        int i9 = bVar.f3015b;
        int i10 = bVar.f3014a;
        if (i10 == 2) {
            i7 = 0;
        } else {
            if (i10 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + bVar);
            }
            i7 = 1;
        }
        int i11 = 1;
        for (int i12 = 1; i12 < bVar.f3017d; i12++) {
            int iZ2 = z(bVar.f3015b + (i7 * i12), bVar.f3014a);
            int i13 = bVar.f3014a;
            if (i13 == 2 ? iZ2 == iZ : i13 == 4 && iZ2 == iZ + 1) {
                i11++;
            } else {
                b bVarB = b(i13, iZ, i11, bVar.f3016c);
                l(bVarB, i9);
                a(bVarB);
                if (bVar.f3014a == 4) {
                    i9 += i11;
                }
                i11 = 1;
                iZ = iZ2;
            }
        }
        Object obj = bVar.f3016c;
        a(bVar);
        if (i11 > 0) {
            b bVarB2 = b(bVar.f3014a, iZ, i11, obj);
            l(bVarB2, i9);
            a(bVarB2);
        }
    }

    private void v(b bVar) {
        this.f3008c.add(bVar);
        int i7 = bVar.f3014a;
        if (i7 == 1) {
            this.f3009d.g(bVar.f3015b, bVar.f3017d);
            return;
        }
        if (i7 == 2) {
            this.f3009d.e(bVar.f3015b, bVar.f3017d);
            return;
        }
        if (i7 == 4) {
            this.f3009d.h(bVar.f3015b, bVar.f3017d, bVar.f3016c);
        } else {
            if (i7 == 8) {
                this.f3009d.a(bVar.f3015b, bVar.f3017d);
                return;
            }
            throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    private int z(int i7, int i8) {
        int i9;
        int i10;
        for (int size = this.f3008c.size() - 1; size >= 0; size--) {
            b bVar = this.f3008c.get(size);
            int i11 = bVar.f3014a;
            if (i11 == 8) {
                int i12 = bVar.f3015b;
                int i13 = bVar.f3017d;
                if (i12 < i13) {
                    i10 = i12;
                    i9 = i13;
                } else {
                    i9 = i12;
                    i10 = i13;
                }
                if (i7 < i10 || i7 > i9) {
                    if (i7 < i12) {
                        if (i8 == 1) {
                            bVar.f3015b = i12 + 1;
                            bVar.f3017d = i13 + 1;
                        } else if (i8 == 2) {
                            bVar.f3015b = i12 - 1;
                            bVar.f3017d = i13 - 1;
                        }
                    }
                } else if (i10 == i12) {
                    if (i8 == 1) {
                        bVar.f3017d = i13 + 1;
                    } else if (i8 == 2) {
                        bVar.f3017d = i13 - 1;
                    }
                    i7++;
                } else {
                    if (i8 == 1) {
                        bVar.f3015b = i12 + 1;
                    } else if (i8 == 2) {
                        bVar.f3015b = i12 - 1;
                    }
                    i7--;
                }
            } else {
                int i14 = bVar.f3015b;
                if (i14 <= i7) {
                    if (i11 == 1) {
                        i7 -= bVar.f3017d;
                    } else if (i11 == 2) {
                        i7 += bVar.f3017d;
                    }
                } else if (i8 == 1) {
                    bVar.f3015b = i14 + 1;
                } else if (i8 == 2) {
                    bVar.f3015b = i14 - 1;
                }
            }
        }
        for (int size2 = this.f3008c.size() - 1; size2 >= 0; size2--) {
            b bVar2 = this.f3008c.get(size2);
            if (bVar2.f3014a == 8) {
                int i15 = bVar2.f3017d;
                if (i15 == bVar2.f3015b || i15 < 0) {
                    this.f3008c.remove(size2);
                    a(bVar2);
                }
            } else if (bVar2.f3017d <= 0) {
                this.f3008c.remove(size2);
                a(bVar2);
            }
        }
        return i7;
    }

    @Override // androidx.recyclerview.widget.l.a
    public void a(b bVar) {
        if (this.f3011f) {
            return;
        }
        bVar.f3016c = null;
        this.f3006a.a(bVar);
    }

    @Override // androidx.recyclerview.widget.l.a
    public b b(int i7, int i8, int i9, Object obj) {
        b bVarB = this.f3006a.b();
        if (bVarB == null) {
            return new b(i7, i8, i9, obj);
        }
        bVarB.f3014a = i7;
        bVarB.f3015b = i8;
        bVarB.f3017d = i9;
        bVarB.f3016c = obj;
        return bVarB;
    }

    public int e(int i7) {
        int size = this.f3007b.size();
        for (int i8 = 0; i8 < size; i8++) {
            b bVar = this.f3007b.get(i8);
            int i9 = bVar.f3014a;
            if (i9 != 1) {
                if (i9 == 2) {
                    int i10 = bVar.f3015b;
                    if (i10 <= i7) {
                        int i11 = bVar.f3017d;
                        if (i10 + i11 > i7) {
                            return -1;
                        }
                        i7 -= i11;
                    } else {
                        continue;
                    }
                } else if (i9 == 8) {
                    int i12 = bVar.f3015b;
                    if (i12 == i7) {
                        i7 = bVar.f3017d;
                    } else {
                        if (i12 < i7) {
                            i7--;
                        }
                        if (bVar.f3017d <= i7) {
                            i7++;
                        }
                    }
                }
            } else if (bVar.f3015b <= i7) {
                i7 += bVar.f3017d;
            }
        }
        return i7;
    }

    void i() {
        int size = this.f3008c.size();
        for (int i7 = 0; i7 < size; i7++) {
            this.f3009d.b(this.f3008c.get(i7));
        }
        x(this.f3008c);
        this.f3013h = 0;
    }

    void j() {
        i();
        int size = this.f3007b.size();
        for (int i7 = 0; i7 < size; i7++) {
            b bVar = this.f3007b.get(i7);
            int i8 = bVar.f3014a;
            if (i8 == 1) {
                this.f3009d.b(bVar);
                this.f3009d.g(bVar.f3015b, bVar.f3017d);
            } else if (i8 == 2) {
                this.f3009d.b(bVar);
                this.f3009d.d(bVar.f3015b, bVar.f3017d);
            } else if (i8 == 4) {
                this.f3009d.b(bVar);
                this.f3009d.h(bVar.f3015b, bVar.f3017d, bVar.f3016c);
            } else if (i8 == 8) {
                this.f3009d.b(bVar);
                this.f3009d.a(bVar.f3015b, bVar.f3017d);
            }
            Runnable runnable = this.f3010e;
            if (runnable != null) {
                runnable.run();
            }
        }
        x(this.f3007b);
        this.f3013h = 0;
    }

    void l(b bVar, int i7) {
        this.f3009d.f(bVar);
        int i8 = bVar.f3014a;
        if (i8 == 2) {
            this.f3009d.d(i7, bVar.f3017d);
        } else {
            if (i8 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            this.f3009d.h(i7, bVar.f3017d, bVar.f3016c);
        }
    }

    int m(int i7) {
        return n(i7, 0);
    }

    int n(int i7, int i8) {
        int size = this.f3008c.size();
        while (i8 < size) {
            b bVar = this.f3008c.get(i8);
            int i9 = bVar.f3014a;
            if (i9 == 8) {
                int i10 = bVar.f3015b;
                if (i10 == i7) {
                    i7 = bVar.f3017d;
                } else {
                    if (i10 < i7) {
                        i7--;
                    }
                    if (bVar.f3017d <= i7) {
                        i7++;
                    }
                }
            } else {
                int i11 = bVar.f3015b;
                if (i11 > i7) {
                    continue;
                } else if (i9 == 2) {
                    int i12 = bVar.f3017d;
                    if (i7 < i11 + i12) {
                        return -1;
                    }
                    i7 -= i12;
                } else if (i9 == 1) {
                    i7 += bVar.f3017d;
                }
            }
            i8++;
        }
        return i7;
    }

    boolean o(int i7) {
        return (i7 & this.f3013h) != 0;
    }

    boolean p() {
        return this.f3007b.size() > 0;
    }

    boolean q() {
        return (this.f3008c.isEmpty() || this.f3007b.isEmpty()) ? false : true;
    }

    boolean r(int i7, int i8, Object obj) {
        if (i8 < 1) {
            return false;
        }
        this.f3007b.add(b(4, i7, i8, obj));
        this.f3013h |= 4;
        return this.f3007b.size() == 1;
    }

    boolean s(int i7, int i8) {
        if (i8 < 1) {
            return false;
        }
        this.f3007b.add(b(1, i7, i8, null));
        this.f3013h |= 1;
        return this.f3007b.size() == 1;
    }

    boolean t(int i7, int i8, int i9) {
        if (i7 == i8) {
            return false;
        }
        if (i9 != 1) {
            throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
        }
        this.f3007b.add(b(8, i7, i8, null));
        this.f3013h |= 8;
        return this.f3007b.size() == 1;
    }

    boolean u(int i7, int i8) {
        if (i8 < 1) {
            return false;
        }
        this.f3007b.add(b(2, i7, i8, null));
        this.f3013h |= 2;
        return this.f3007b.size() == 1;
    }

    void w() {
        this.f3012g.b(this.f3007b);
        int size = this.f3007b.size();
        for (int i7 = 0; i7 < size; i7++) {
            b bVar = this.f3007b.get(i7);
            int i8 = bVar.f3014a;
            if (i8 == 1) {
                c(bVar);
            } else if (i8 == 2) {
                f(bVar);
            } else if (i8 == 4) {
                g(bVar);
            } else if (i8 == 8) {
                d(bVar);
            }
            Runnable runnable = this.f3010e;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.f3007b.clear();
    }

    void x(List<b> list) {
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            a(list.get(i7));
        }
        list.clear();
    }

    void y() {
        x(this.f3007b);
        x(this.f3008c);
        this.f3013h = 0;
    }

    a(InterfaceC0027a interfaceC0027a, boolean z6) {
        this.f3006a = new h0.f(30);
        this.f3007b = new ArrayList<>();
        this.f3008c = new ArrayList<>();
        this.f3013h = 0;
        this.f3009d = interfaceC0027a;
        this.f3011f = z6;
        this.f3012g = new l(this);
    }
}
