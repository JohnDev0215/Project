package p;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class b5 {
    public final p12 d;
    public final nw1 a = new nw1(30, 1);
    public final ArrayList b = new ArrayList();
    public final ArrayList c = new ArrayList();
    public final du e = new du(2, this);

    public b5(p12 p12Var) {
        this.d = p12Var;
    }

    public final boolean a(int i) {
        ArrayList arrayList = this.c;
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            a5 a5Var = (a5) arrayList.get(i2);
            int i3 = a5Var.a;
            if (i3 == 8) {
                if (f(a5Var.d, i2 + 1) == i) {
                    return true;
                }
            } else if (i3 == 1) {
                int i4 = a5Var.b;
                int i5 = a5Var.d + i4;
                while (i4 < i5) {
                    if (f(i4, i2 + 1) == i) {
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

    public final void b() {
        ArrayList arrayList = this.c;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            this.d.a((a5) arrayList.get(i));
        }
        l(arrayList);
    }

    public final void c() {
        b();
        ArrayList arrayList = this.b;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            a5 a5Var = (a5) arrayList.get(i);
            int i2 = a5Var.a;
            p12 p12Var = this.d;
            if (i2 == 1) {
                p12Var.a(a5Var);
                p12Var.d(a5Var.b, a5Var.d);
            } else if (i2 == 2) {
                p12Var.a(a5Var);
                int i3 = a5Var.b;
                int i4 = a5Var.d;
                RecyclerView recyclerView = p12Var.a;
                recyclerView.O(i3, i4, true);
                recyclerView.n0 = true;
                recyclerView.k0.c += i4;
            } else if (i2 == 4) {
                p12Var.a(a5Var);
                p12Var.c(a5Var.b, a5Var.d, a5Var.c);
            } else if (i2 == 8) {
                p12Var.a(a5Var);
                p12Var.e(a5Var.b, a5Var.d);
            }
        }
        l(arrayList);
    }

    public final void d(a5 a5Var) {
        int i;
        int i2 = a5Var.a;
        if (i2 == 1 || i2 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iM = m(a5Var.b, i2);
        int i3 = a5Var.b;
        int i4 = a5Var.a;
        if (i4 == 2) {
            i = 0;
        } else {
            if (i4 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + a5Var);
            }
            i = 1;
        }
        int i5 = 1;
        for (int i6 = 1; i6 < a5Var.d; i6++) {
            int iM2 = m((i * i6) + a5Var.b, a5Var.a);
            int i7 = a5Var.a;
            if (i7 == 2 ? iM2 != iM : !(i7 == 4 && iM2 == iM + 1)) {
                a5 a5VarH = h(a5Var.c, i7, iM, i5);
                e(a5VarH, i3);
                k(a5VarH);
                if (a5Var.a == 4) {
                    i3 += i5;
                }
                i5 = 1;
                iM = iM2;
            } else {
                i5++;
            }
        }
        Object obj = a5Var.c;
        k(a5Var);
        if (i5 > 0) {
            a5 a5VarH2 = h(obj, a5Var.a, iM, i5);
            e(a5VarH2, i3);
            k(a5VarH2);
        }
    }

    public final void e(a5 a5Var, int i) {
        p12 p12Var = this.d;
        p12Var.a(a5Var);
        int i2 = a5Var.a;
        if (i2 != 2) {
            if (i2 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            p12Var.c(i, a5Var.d, a5Var.c);
        } else {
            int i3 = a5Var.d;
            RecyclerView recyclerView = p12Var.a;
            recyclerView.O(i, i3, true);
            recyclerView.n0 = true;
            recyclerView.k0.c += i3;
        }
    }

    public final int f(int i, int i2) {
        ArrayList arrayList = this.c;
        int size = arrayList.size();
        while (i2 < size) {
            a5 a5Var = (a5) arrayList.get(i2);
            int i3 = a5Var.a;
            if (i3 == 8) {
                int i4 = a5Var.b;
                if (i4 == i) {
                    i = a5Var.d;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (a5Var.d <= i) {
                        i++;
                    }
                }
            } else {
                int i5 = a5Var.b;
                if (i5 > i) {
                    continue;
                } else if (i3 == 2) {
                    int i6 = a5Var.d;
                    if (i < i5 + i6) {
                        return -1;
                    }
                    i -= i6;
                } else if (i3 == 1) {
                    i += a5Var.d;
                }
            }
            i2++;
        }
        return i;
    }

    public final boolean g() {
        return this.b.size() > 0;
    }

    public final a5 h(Object obj, int i, int i2, int i3) {
        a5 a5Var = (a5) this.a.a();
        if (a5Var != null) {
            a5Var.a = i;
            a5Var.b = i2;
            a5Var.d = i3;
            a5Var.c = obj;
            return a5Var;
        }
        a5 a5Var2 = new a5();
        a5Var2.a = i;
        a5Var2.b = i2;
        a5Var2.d = i3;
        a5Var2.c = obj;
        return a5Var2;
    }

    public final void i(a5 a5Var) {
        this.c.add(a5Var);
        int i = a5Var.a;
        p12 p12Var = this.d;
        if (i == 1) {
            p12Var.d(a5Var.b, a5Var.d);
            return;
        }
        if (i == 2) {
            int i2 = a5Var.b;
            int i3 = a5Var.d;
            RecyclerView recyclerView = p12Var.a;
            recyclerView.O(i2, i3, false);
            recyclerView.n0 = true;
            return;
        }
        if (i == 4) {
            p12Var.c(a5Var.b, a5Var.d, a5Var.c);
        } else if (i == 8) {
            p12Var.e(a5Var.b, a5Var.d);
        } else {
            throw new IllegalArgumentException("Unknown update op type for " + a5Var);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:182:0x00a1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0009 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x009c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void j() {
        /*
            Method dump skipped, instruction units count: 655
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.b5.j():void");
    }

    public final void k(a5 a5Var) {
        a5Var.c = null;
        this.a.b(a5Var);
    }

    public final void l(ArrayList arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            k((a5) arrayList.get(i));
        }
        arrayList.clear();
    }

    public final int m(int i, int i2) {
        int i3;
        int i4;
        ArrayList arrayList = this.c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            a5 a5Var = (a5) arrayList.get(size);
            int i5 = a5Var.a;
            if (i5 == 8) {
                int i6 = a5Var.b;
                int i7 = a5Var.d;
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
                            a5Var.b = i6 + 1;
                            a5Var.d = i7 + 1;
                        } else if (i2 == 2) {
                            a5Var.b = i6 - 1;
                            a5Var.d = i7 - 1;
                        }
                    }
                } else if (i4 == i6) {
                    if (i2 == 1) {
                        a5Var.d = i7 + 1;
                    } else if (i2 == 2) {
                        a5Var.d = i7 - 1;
                    }
                    i++;
                } else {
                    if (i2 == 1) {
                        a5Var.b = i6 + 1;
                    } else if (i2 == 2) {
                        a5Var.b = i6 - 1;
                    }
                    i--;
                }
            } else {
                int i8 = a5Var.b;
                if (i8 <= i) {
                    if (i5 == 1) {
                        i -= a5Var.d;
                    } else if (i5 == 2) {
                        i += a5Var.d;
                    }
                } else if (i2 == 1) {
                    a5Var.b = i8 + 1;
                } else if (i2 == 2) {
                    a5Var.b = i8 - 1;
                }
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            a5 a5Var2 = (a5) arrayList.get(size2);
            if (a5Var2.a == 8) {
                int i9 = a5Var2.d;
                if (i9 == a5Var2.b || i9 < 0) {
                    arrayList.remove(size2);
                    k(a5Var2);
                }
            } else if (a5Var2.d <= 0) {
                arrayList.remove(size2);
                k(a5Var2);
            }
        }
        return i;
    }
}
