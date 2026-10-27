package androidx.recyclerview.widget;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.j;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class a implements j.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a.g.j.e<b> f1520a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final ArrayList<b> f1521b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final ArrayList<b> f1522c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final InterfaceC0045a f1523d;
    Runnable e;
    final boolean f;
    final j g;
    private int h;

    /* JADX INFO: renamed from: androidx.recyclerview.widget.a$a, reason: collision with other inner class name */
    interface InterfaceC0045a {
        void a(int i, int i2);

        void b(b bVar);

        RecyclerView.d0 c(int i);

        void d(int i, int i2);

        void e(int i, int i2);

        void f(b bVar);

        void g(int i, int i2);

        void h(int i, int i2, Object obj);
    }

    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1524a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f1525b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        Object f1526c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1527d;

        b(int i, int i2, int i3, Object obj) {
            this.f1524a = i;
            this.f1525b = i2;
            this.f1527d = i3;
            this.f1526c = obj;
        }

        String a() {
            int i = this.f1524a;
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
            int i = this.f1524a;
            if (i != bVar.f1524a) {
                return false;
            }
            if (i == 8 && Math.abs(this.f1527d - this.f1525b) == 1 && this.f1527d == bVar.f1525b && this.f1525b == bVar.f1527d) {
                return true;
            }
            if (this.f1527d != bVar.f1527d || this.f1525b != bVar.f1525b) {
                return false;
            }
            Object obj2 = this.f1526c;
            Object obj3 = bVar.f1526c;
            if (obj2 != null) {
                if (!obj2.equals(obj3)) {
                    return false;
                }
            } else if (obj3 != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.f1524a * 31) + this.f1525b) * 31) + this.f1527d;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + a() + ",s:" + this.f1525b + "c:" + this.f1527d + ",p:" + this.f1526c + "]";
        }
    }

    a(InterfaceC0045a interfaceC0045a) {
        this(interfaceC0045a, false);
    }

    a(InterfaceC0045a interfaceC0045a, boolean z) {
        this.f1520a = new a.g.j.f(30);
        this.f1521b = new ArrayList<>();
        this.f1522c = new ArrayList<>();
        this.h = 0;
        this.f1523d = interfaceC0045a;
        this.f = z;
        this.g = new j(this);
    }

    private void c(b bVar) {
        u(bVar);
    }

    private void d(b bVar) {
        u(bVar);
    }

    private void f(b bVar) {
        boolean z;
        byte b2;
        int i = bVar.f1525b;
        int i2 = bVar.f1527d + i;
        byte b3 = -1;
        int i3 = i;
        int i4 = 0;
        while (i3 < i2) {
            if (this.f1523d.c(i3) != null || h(i3)) {
                if (b3 == 0) {
                    k(b(2, i, i4, null));
                    z = true;
                } else {
                    z = false;
                }
                b2 = 1;
            } else {
                if (b3 == 1) {
                    u(b(2, i, i4, null));
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
        if (i4 != bVar.f1527d) {
            a(bVar);
            bVar = b(2, i, i4, null);
        }
        if (b3 == 0) {
            k(bVar);
        } else {
            u(bVar);
        }
    }

    private void g(b bVar) {
        int i = bVar.f1525b;
        int i2 = bVar.f1527d + i;
        int i3 = 0;
        byte b2 = -1;
        int i4 = i;
        while (i < i2) {
            if (this.f1523d.c(i) != null || h(i)) {
                if (b2 == 0) {
                    k(b(4, i4, i3, bVar.f1526c));
                    i4 = i;
                    i3 = 0;
                }
                b2 = 1;
            } else {
                if (b2 == 1) {
                    u(b(4, i4, i3, bVar.f1526c));
                    i4 = i;
                    i3 = 0;
                }
                b2 = 0;
            }
            i3++;
            i++;
        }
        if (i3 != bVar.f1527d) {
            Object obj = bVar.f1526c;
            a(bVar);
            bVar = b(4, i4, i3, obj);
        }
        if (b2 == 0) {
            k(bVar);
        } else {
            u(bVar);
        }
    }

    private boolean h(int i) {
        int size = this.f1522c.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.f1522c.get(i2);
            int i3 = bVar.f1524a;
            if (i3 == 8) {
                if (n(bVar.f1527d, i2 + 1) == i) {
                    return true;
                }
            } else if (i3 == 1) {
                int i4 = bVar.f1525b;
                int i5 = bVar.f1527d + i4;
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
        int i2 = bVar.f1524a;
        if (i2 == 1 || i2 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iY = y(bVar.f1525b, i2);
        int i3 = bVar.f1525b;
        int i4 = bVar.f1524a;
        if (i4 == 2) {
            i = 0;
        } else {
            if (i4 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + bVar);
            }
            i = 1;
        }
        int i5 = 1;
        for (int i6 = 1; i6 < bVar.f1527d; i6++) {
            int iY2 = y(bVar.f1525b + (i * i6), bVar.f1524a);
            int i7 = bVar.f1524a;
            if (i7 == 2 ? iY2 == iY : i7 == 4 && iY2 == iY + 1) {
                i5++;
            } else {
                b bVarB = b(i7, iY, i5, bVar.f1526c);
                l(bVarB, i3);
                a(bVarB);
                if (bVar.f1524a == 4) {
                    i3 += i5;
                }
                i5 = 1;
                iY = iY2;
            }
        }
        Object obj = bVar.f1526c;
        a(bVar);
        if (i5 > 0) {
            b bVarB2 = b(bVar.f1524a, iY, i5, obj);
            l(bVarB2, i3);
            a(bVarB2);
        }
    }

    private void u(b bVar) {
        this.f1522c.add(bVar);
        int i = bVar.f1524a;
        if (i == 1) {
            this.f1523d.g(bVar.f1525b, bVar.f1527d);
            return;
        }
        if (i == 2) {
            this.f1523d.e(bVar.f1525b, bVar.f1527d);
            return;
        }
        if (i == 4) {
            this.f1523d.h(bVar.f1525b, bVar.f1527d, bVar.f1526c);
        } else {
            if (i == 8) {
                this.f1523d.a(bVar.f1525b, bVar.f1527d);
                return;
            }
            throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int y(int r9, int r10) {
        /*
            r8 = this;
            java.util.ArrayList<androidx.recyclerview.widget.a$b> r0 = r8.f1522c
            int r0 = r0.size()
            r1 = 1
            int r0 = r0 - r1
        L8:
            r2 = 8
            if (r0 < 0) goto L7d
            java.util.ArrayList<androidx.recyclerview.widget.a$b> r3 = r8.f1522c
            java.lang.Object r3 = r3.get(r0)
            androidx.recyclerview.widget.a$b r3 = (androidx.recyclerview.widget.a.b) r3
            int r4 = r3.f1524a
            r5 = 2
            if (r4 != r2) goto L5e
            int r2 = r3.f1525b
            int r4 = r3.f1527d
            if (r2 >= r4) goto L22
            r6 = r2
            r7 = r4
            goto L24
        L22:
            r7 = r2
            r6 = r4
        L24:
            if (r9 < r6) goto L48
            if (r9 > r7) goto L48
            if (r6 != r2) goto L39
            if (r10 != r1) goto L31
            int r4 = r4 + 1
        L2e:
            r3.f1527d = r4
            goto L36
        L31:
            if (r10 != r5) goto L36
            int r4 = r4 + (-1)
            goto L2e
        L36:
            int r9 = r9 + 1
            goto L7a
        L39:
            if (r10 != r1) goto L40
            int r2 = r2 + 1
        L3d:
            r3.f1525b = r2
            goto L45
        L40:
            if (r10 != r5) goto L45
            int r2 = r2 + (-1)
            goto L3d
        L45:
            int r9 = r9 + (-1)
            goto L7a
        L48:
            if (r9 >= r2) goto L7a
            if (r10 != r1) goto L55
            int r2 = r2 + 1
            r3.f1525b = r2
            int r4 = r4 + 1
        L52:
            r3.f1527d = r4
            goto L7a
        L55:
            if (r10 != r5) goto L7a
            int r2 = r2 + (-1)
            r3.f1525b = r2
            int r4 = r4 + (-1)
            goto L52
        L5e:
            int r2 = r3.f1525b
            if (r2 > r9) goto L6e
            if (r4 != r1) goto L68
            int r2 = r3.f1527d
            int r9 = r9 - r2
            goto L7a
        L68:
            if (r4 != r5) goto L7a
            int r2 = r3.f1527d
            int r9 = r9 + r2
            goto L7a
        L6e:
            if (r10 != r1) goto L75
            int r2 = r2 + 1
        L72:
            r3.f1525b = r2
            goto L7a
        L75:
            if (r10 != r5) goto L7a
            int r2 = r2 + (-1)
            goto L72
        L7a:
            int r0 = r0 + (-1)
            goto L8
        L7d:
            java.util.ArrayList<androidx.recyclerview.widget.a$b> r10 = r8.f1522c
            int r10 = r10.size()
            int r10 = r10 - r1
        L84:
            if (r10 < 0) goto Laa
            java.util.ArrayList<androidx.recyclerview.widget.a$b> r0 = r8.f1522c
            java.lang.Object r0 = r0.get(r10)
            androidx.recyclerview.widget.a$b r0 = (androidx.recyclerview.widget.a.b) r0
            int r1 = r0.f1524a
            if (r1 != r2) goto L9b
            int r1 = r0.f1527d
            int r3 = r0.f1525b
            if (r1 == r3) goto L9f
            if (r1 >= 0) goto La7
            goto L9f
        L9b:
            int r1 = r0.f1527d
            if (r1 > 0) goto La7
        L9f:
            java.util.ArrayList<androidx.recyclerview.widget.a$b> r1 = r8.f1522c
            r1.remove(r10)
            r8.a(r0)
        La7:
            int r10 = r10 + (-1)
            goto L84
        Laa:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.a.y(int, int):int");
    }

    @Override // androidx.recyclerview.widget.j.a
    public void a(b bVar) {
        if (this.f) {
            return;
        }
        bVar.f1526c = null;
        this.f1520a.a(bVar);
    }

    @Override // androidx.recyclerview.widget.j.a
    public b b(int i, int i2, int i3, Object obj) {
        b bVarB = this.f1520a.b();
        if (bVarB == null) {
            return new b(i, i2, i3, obj);
        }
        bVarB.f1524a = i;
        bVarB.f1525b = i2;
        bVarB.f1527d = i3;
        bVarB.f1526c = obj;
        return bVarB;
    }

    public int e(int i) {
        int size = this.f1521b.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.f1521b.get(i2);
            int i3 = bVar.f1524a;
            if (i3 != 1) {
                if (i3 == 2) {
                    int i4 = bVar.f1525b;
                    if (i4 <= i) {
                        int i5 = bVar.f1527d;
                        if (i4 + i5 > i) {
                            return -1;
                        }
                        i -= i5;
                    } else {
                        continue;
                    }
                } else if (i3 == 8) {
                    int i6 = bVar.f1525b;
                    if (i6 == i) {
                        i = bVar.f1527d;
                    } else {
                        if (i6 < i) {
                            i--;
                        }
                        if (bVar.f1527d <= i) {
                            i++;
                        }
                    }
                }
            } else if (bVar.f1525b <= i) {
                i += bVar.f1527d;
            }
        }
        return i;
    }

    void i() {
        int size = this.f1522c.size();
        for (int i = 0; i < size; i++) {
            this.f1523d.b(this.f1522c.get(i));
        }
        w(this.f1522c);
        this.h = 0;
    }

    void j() {
        i();
        int size = this.f1521b.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.f1521b.get(i);
            int i2 = bVar.f1524a;
            if (i2 == 1) {
                this.f1523d.b(bVar);
                this.f1523d.g(bVar.f1525b, bVar.f1527d);
            } else if (i2 == 2) {
                this.f1523d.b(bVar);
                this.f1523d.d(bVar.f1525b, bVar.f1527d);
            } else if (i2 == 4) {
                this.f1523d.b(bVar);
                this.f1523d.h(bVar.f1525b, bVar.f1527d, bVar.f1526c);
            } else if (i2 == 8) {
                this.f1523d.b(bVar);
                this.f1523d.a(bVar.f1525b, bVar.f1527d);
            }
            Runnable runnable = this.e;
            if (runnable != null) {
                runnable.run();
            }
        }
        w(this.f1521b);
        this.h = 0;
    }

    void l(b bVar, int i) {
        this.f1523d.f(bVar);
        int i2 = bVar.f1524a;
        if (i2 == 2) {
            this.f1523d.d(i, bVar.f1527d);
        } else {
            if (i2 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            this.f1523d.h(i, bVar.f1527d, bVar.f1526c);
        }
    }

    int m(int i) {
        return n(i, 0);
    }

    int n(int i, int i2) {
        int size = this.f1522c.size();
        while (i2 < size) {
            b bVar = this.f1522c.get(i2);
            int i3 = bVar.f1524a;
            if (i3 == 8) {
                int i4 = bVar.f1525b;
                if (i4 == i) {
                    i = bVar.f1527d;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (bVar.f1527d <= i) {
                        i++;
                    }
                }
            } else {
                int i5 = bVar.f1525b;
                if (i5 > i) {
                    continue;
                } else if (i3 == 2) {
                    int i6 = bVar.f1527d;
                    if (i < i5 + i6) {
                        return -1;
                    }
                    i -= i6;
                } else if (i3 == 1) {
                    i += bVar.f1527d;
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
        return this.f1521b.size() > 0;
    }

    boolean q() {
        return (this.f1522c.isEmpty() || this.f1521b.isEmpty()) ? false : true;
    }

    boolean r(int i, int i2, Object obj) {
        if (i2 < 1) {
            return false;
        }
        this.f1521b.add(b(4, i, i2, obj));
        this.h |= 4;
        return this.f1521b.size() == 1;
    }

    boolean s(int i, int i2) {
        if (i2 < 1) {
            return false;
        }
        this.f1521b.add(b(1, i, i2, null));
        this.h |= 1;
        return this.f1521b.size() == 1;
    }

    boolean t(int i, int i2) {
        if (i2 < 1) {
            return false;
        }
        this.f1521b.add(b(2, i, i2, null));
        this.h |= 2;
        return this.f1521b.size() == 1;
    }

    void v() {
        this.g.b(this.f1521b);
        int size = this.f1521b.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.f1521b.get(i);
            int i2 = bVar.f1524a;
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
        this.f1521b.clear();
    }

    void w(List<b> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            a(list.get(i));
        }
        list.clear();
    }

    void x() {
        w(this.f1521b);
        w(this.f1522c);
        this.h = 0;
    }
}
