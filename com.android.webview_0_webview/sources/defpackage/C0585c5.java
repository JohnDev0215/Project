package defpackage;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: c5, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0585c5 implements D5 {
    public final InterfaceC0473a5 d;
    public InterfaceC0471a3 a = new C0527b3(30);
    public final ArrayList b = new ArrayList();
    public final ArrayList c = new ArrayList();
    public int g = 0;
    public final boolean e = false;
    public final E5 f = new E5(this);

    public C0585c5(InterfaceC0473a5 interfaceC0473a5) {
        this.d = interfaceC0473a5;
    }

    public final boolean a(int i) {
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0529b5 c0529b5 = (C0529b5) this.c.get(i2);
            int i3 = c0529b5.a;
            if (i3 == 8) {
                if (f(c0529b5.d, i2 + 1) == i) {
                    return true;
                }
            } else if (i3 == 1) {
                int i4 = c0529b5.b;
                int i5 = c0529b5.d + i4;
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

    public void b() {
        int size = this.c.size();
        for (int i = 0; i < size; i++) {
            ((M5) this.d).a((C0529b5) this.c.get(i));
        }
        l(this.c);
        this.g = 0;
    }

    public void c() {
        b();
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            C0529b5 c0529b5 = (C0529b5) this.b.get(i);
            int i2 = c0529b5.a;
            if (i2 == 1) {
                ((M5) this.d).a(c0529b5);
                ((M5) this.d).c(c0529b5.b, c0529b5.d);
            } else if (i2 == 2) {
                ((M5) this.d).a(c0529b5);
                InterfaceC0473a5 interfaceC0473a5 = this.d;
                int i3 = c0529b5.b;
                int i4 = c0529b5.d;
                M5 m5 = (M5) interfaceC0473a5;
                m5.a.w(i3, i4, true);
                RecyclerView recyclerView = m5.a;
                recyclerView.t0 = true;
                recyclerView.r0.c += i4;
            } else if (i2 == 4) {
                ((M5) this.d).a(c0529b5);
                ((M5) this.d).b(c0529b5.b, c0529b5.d, c0529b5.c);
            } else if (i2 == 8) {
                ((M5) this.d).a(c0529b5);
                ((M5) this.d).d(c0529b5.b, c0529b5.d);
            }
        }
        l(this.b);
        this.g = 0;
    }

    public final void d(C0529b5 c0529b5) {
        int i;
        int i2 = c0529b5.a;
        if (i2 == 1 || i2 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iM = m(c0529b5.b, i2);
        int i3 = c0529b5.b;
        int i4 = c0529b5.a;
        if (i4 == 2) {
            i = 0;
        } else {
            if (i4 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + c0529b5);
            }
            i = 1;
        }
        int i5 = 1;
        for (int i6 = 1; i6 < c0529b5.d; i6++) {
            int iM2 = m((i * i6) + c0529b5.b, c0529b5.a);
            int i7 = c0529b5.a;
            if (i7 == 2 ? iM2 == iM : i7 == 4 && iM2 == iM + 1) {
                i5++;
            } else {
                C0529b5 c0529b5H = h(c0529b5.a, iM, i5, c0529b5.c);
                e(c0529b5H, i3);
                k(c0529b5H);
                if (c0529b5.a == 4) {
                    i3 += i5;
                }
                iM = iM2;
                i5 = 1;
            }
        }
        Object obj = c0529b5.c;
        k(c0529b5);
        if (i5 > 0) {
            C0529b5 c0529b5H2 = h(c0529b5.a, iM, i5, obj);
            e(c0529b5H2, i3);
            k(c0529b5H2);
        }
    }

    public void e(C0529b5 c0529b5, int i) {
        ((M5) this.d).a(c0529b5);
        int i2 = c0529b5.a;
        if (i2 != 2) {
            if (i2 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            ((M5) this.d).b(i, c0529b5.d, c0529b5.c);
            return;
        }
        InterfaceC0473a5 interfaceC0473a5 = this.d;
        int i3 = c0529b5.d;
        M5 m5 = (M5) interfaceC0473a5;
        m5.a.w(i, i3, true);
        RecyclerView recyclerView = m5.a;
        recyclerView.t0 = true;
        recyclerView.r0.c += i3;
    }

    public int f(int i, int i2) {
        int size = this.c.size();
        while (i2 < size) {
            C0529b5 c0529b5 = (C0529b5) this.c.get(i2);
            int i3 = c0529b5.a;
            if (i3 == 8) {
                int i4 = c0529b5.b;
                if (i4 == i) {
                    i = c0529b5.d;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (c0529b5.d <= i) {
                        i++;
                    }
                }
            } else {
                int i5 = c0529b5.b;
                if (i5 > i) {
                    continue;
                } else if (i3 == 2) {
                    int i6 = c0529b5.d;
                    if (i < i5 + i6) {
                        return -1;
                    }
                    i -= i6;
                } else if (i3 == 1) {
                    i += c0529b5.d;
                }
            }
            i2++;
        }
        return i;
    }

    public boolean g() {
        return this.b.size() > 0;
    }

    public C0529b5 h(int i, int i2, int i3, Object obj) {
        C0529b5 c0529b5 = (C0529b5) this.a.b();
        if (c0529b5 == null) {
            return new C0529b5(i, i2, i3, obj);
        }
        c0529b5.a = i;
        c0529b5.b = i2;
        c0529b5.d = i3;
        c0529b5.c = obj;
        return c0529b5;
    }

    public final void i(C0529b5 c0529b5) {
        this.c.add(c0529b5);
        int i = c0529b5.a;
        if (i == 1) {
            ((M5) this.d).c(c0529b5.b, c0529b5.d);
            return;
        }
        if (i == 2) {
            M5 m5 = (M5) this.d;
            m5.a.w(c0529b5.b, c0529b5.d, false);
            m5.a.t0 = true;
            return;
        }
        if (i == 4) {
            ((M5) this.d).b(c0529b5.b, c0529b5.d, c0529b5.c);
        } else if (i == 8) {
            ((M5) this.d).d(c0529b5.b, c0529b5.d);
        } else {
            throw new IllegalArgumentException("Unknown update op type for " + c0529b5);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:175:0x00a3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0128 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0119 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x00d3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0009 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0100  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void j() {
        /*
            Method dump skipped, instruction units count: 678
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0585c5.j():void");
    }

    public void k(C0529b5 c0529b5) {
        c0529b5.c = null;
        this.a.a(c0529b5);
    }

    public void l(List list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            k((C0529b5) list.get(i));
        }
        list.clear();
    }

    public final int m(int i, int i2) {
        for (int size = this.c.size() - 1; size >= 0; size--) {
            C0529b5 c0529b5 = (C0529b5) this.c.get(size);
            int i3 = c0529b5.a;
            if (i3 == 8) {
                int i4 = c0529b5.b;
                int i5 = c0529b5.d;
                if (i4 >= i5) {
                    i5 = i4;
                    i4 = i5;
                }
                if (i < i4 || i > i5) {
                    int i6 = c0529b5.b;
                    if (i < i6) {
                        if (i2 == 1) {
                            c0529b5.b = i6 + 1;
                            c0529b5.d++;
                        } else if (i2 == 2) {
                            c0529b5.b = i6 - 1;
                            c0529b5.d--;
                        }
                    }
                } else {
                    int i7 = c0529b5.b;
                    if (i4 == i7) {
                        if (i2 == 1) {
                            c0529b5.d++;
                        } else if (i2 == 2) {
                            c0529b5.d--;
                        }
                        i++;
                    } else {
                        if (i2 == 1) {
                            c0529b5.b = i7 + 1;
                        } else if (i2 == 2) {
                            c0529b5.b = i7 - 1;
                        }
                        i--;
                    }
                }
            } else {
                int i8 = c0529b5.b;
                if (i8 <= i) {
                    if (i3 == 1) {
                        i -= c0529b5.d;
                    } else if (i3 == 2) {
                        i += c0529b5.d;
                    }
                } else if (i2 == 1) {
                    c0529b5.b = i8 + 1;
                } else if (i2 == 2) {
                    c0529b5.b = i8 - 1;
                }
            }
        }
        for (int size2 = this.c.size() - 1; size2 >= 0; size2--) {
            C0529b5 c0529b52 = (C0529b5) this.c.get(size2);
            if (c0529b52.a == 8) {
                int i9 = c0529b52.d;
                if (i9 == c0529b52.b || i9 < 0) {
                    this.c.remove(size2);
                    k(c0529b52);
                }
            } else if (c0529b52.d <= 0) {
                this.c.remove(size2);
                k(c0529b52);
            }
        }
        return i;
    }
}
