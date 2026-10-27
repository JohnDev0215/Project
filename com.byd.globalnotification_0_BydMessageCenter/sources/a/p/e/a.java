package a.p.e;

import a.p.e.t;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a implements t.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final InterfaceC0025a f1132d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a.h.j.d<b> f1129a = new a.h.j.d<>(30);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList<b> f1130b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList<b> f1131c = new ArrayList<>();
    public int g = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f1133e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final t f1134f = new t(this);

    /* JADX INFO: renamed from: a.p.e.a$a, reason: collision with other inner class name */
    public interface InterfaceC0025a {
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f1135a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1136b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public Object f1137c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1138d;

        public b(int i, int i2, int i3, Object obj) {
            this.f1135a = i;
            this.f1136b = i2;
            this.f1138d = i3;
            this.f1137c = obj;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || b.class != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            int i = this.f1135a;
            if (i != bVar.f1135a) {
                return false;
            }
            if (i == 8 && Math.abs(this.f1138d - this.f1136b) == 1 && this.f1138d == bVar.f1136b && this.f1136b == bVar.f1138d) {
                return true;
            }
            if (this.f1138d != bVar.f1138d || this.f1136b != bVar.f1136b) {
                return false;
            }
            Object obj2 = this.f1137c;
            if (obj2 != null) {
                if (!obj2.equals(bVar.f1137c)) {
                    return false;
                }
            } else if (bVar.f1137c != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (((this.f1135a * 31) + this.f1136b) * 31) + this.f1138d;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(Integer.toHexString(System.identityHashCode(this)));
            sb.append("[");
            int i = this.f1135a;
            sb.append(i != 1 ? i != 2 ? i != 4 ? i != 8 ? "??" : "mv" : "up" : "rm" : "add");
            sb.append(",s:");
            sb.append(this.f1136b);
            sb.append("c:");
            sb.append(this.f1138d);
            sb.append(",p:");
            sb.append(this.f1137c);
            sb.append("]");
            return sb.toString();
        }
    }

    public a(InterfaceC0025a interfaceC0025a) {
        this.f1132d = interfaceC0025a;
    }

    public final boolean a(int i) {
        int size = this.f1131c.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.f1131c.get(i2);
            int i3 = bVar.f1135a;
            if (i3 == 8) {
                if (f(bVar.f1138d, i2 + 1) == i) {
                    return true;
                }
            } else if (i3 == 1) {
                int i4 = bVar.f1136b;
                int i5 = bVar.f1138d + i4;
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
        int size = this.f1131c.size();
        for (int i = 0; i < size; i++) {
            ((a0) this.f1132d).a(this.f1131c.get(i));
        }
        l(this.f1131c);
        this.g = 0;
    }

    public void c() {
        b();
        int size = this.f1130b.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.f1130b.get(i);
            int i2 = bVar.f1135a;
            if (i2 == 1) {
                ((a0) this.f1132d).a(bVar);
                ((a0) this.f1132d).d(bVar.f1136b, bVar.f1138d);
            } else if (i2 == 2) {
                ((a0) this.f1132d).a(bVar);
                InterfaceC0025a interfaceC0025a = this.f1132d;
                int i3 = bVar.f1136b;
                int i4 = bVar.f1138d;
                a0 a0Var = (a0) interfaceC0025a;
                a0Var.f1139a.S(i3, i4, true);
                RecyclerView recyclerView = a0Var.f1139a;
                recyclerView.h0 = true;
                recyclerView.e0.f1691c += i4;
            } else if (i2 == 4) {
                ((a0) this.f1132d).a(bVar);
                ((a0) this.f1132d).c(bVar.f1136b, bVar.f1138d, bVar.f1137c);
            } else if (i2 == 8) {
                ((a0) this.f1132d).a(bVar);
                ((a0) this.f1132d).e(bVar.f1136b, bVar.f1138d);
            }
        }
        l(this.f1130b);
        this.g = 0;
    }

    public final void d(b bVar) {
        int i;
        int i2 = bVar.f1135a;
        if (i2 == 1 || i2 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iM = m(bVar.f1136b, i2);
        int i3 = bVar.f1136b;
        int i4 = bVar.f1135a;
        if (i4 == 2) {
            i = 0;
        } else {
            if (i4 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + bVar);
            }
            i = 1;
        }
        int i5 = 1;
        for (int i6 = 1; i6 < bVar.f1138d; i6++) {
            int iM2 = m((i * i6) + bVar.f1136b, bVar.f1135a);
            int i7 = bVar.f1135a;
            if (i7 == 2 ? iM2 == iM : i7 == 4 && iM2 == iM + 1) {
                i5++;
            } else {
                b bVarH = h(bVar.f1135a, iM, i5, bVar.f1137c);
                e(bVarH, i3);
                if (!this.f1133e) {
                    bVarH.f1137c = null;
                    this.f1129a.b(bVarH);
                }
                if (bVar.f1135a == 4) {
                    i3 += i5;
                }
                i5 = 1;
                iM = iM2;
            }
        }
        Object obj = bVar.f1137c;
        if (!this.f1133e) {
            bVar.f1137c = null;
            this.f1129a.b(bVar);
        }
        if (i5 > 0) {
            b bVarH2 = h(bVar.f1135a, iM, i5, obj);
            e(bVarH2, i3);
            if (this.f1133e) {
                return;
            }
            bVarH2.f1137c = null;
            this.f1129a.b(bVarH2);
        }
    }

    public void e(b bVar, int i) {
        ((a0) this.f1132d).a(bVar);
        int i2 = bVar.f1135a;
        if (i2 != 2) {
            if (i2 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            ((a0) this.f1132d).c(i, bVar.f1138d, bVar.f1137c);
            return;
        }
        InterfaceC0025a interfaceC0025a = this.f1132d;
        int i3 = bVar.f1138d;
        a0 a0Var = (a0) interfaceC0025a;
        a0Var.f1139a.S(i, i3, true);
        RecyclerView recyclerView = a0Var.f1139a;
        recyclerView.h0 = true;
        recyclerView.e0.f1691c += i3;
    }

    public int f(int i, int i2) {
        int size = this.f1131c.size();
        while (i2 < size) {
            b bVar = this.f1131c.get(i2);
            int i3 = bVar.f1135a;
            if (i3 == 8) {
                int i4 = bVar.f1136b;
                if (i4 == i) {
                    i = bVar.f1138d;
                } else {
                    if (i4 < i) {
                        i--;
                    }
                    if (bVar.f1138d <= i) {
                        i++;
                    }
                }
            } else {
                int i5 = bVar.f1136b;
                if (i5 > i) {
                    continue;
                } else if (i3 == 2) {
                    int i6 = bVar.f1138d;
                    if (i < i5 + i6) {
                        return -1;
                    }
                    i -= i6;
                } else if (i3 == 1) {
                    i += bVar.f1138d;
                }
            }
            i2++;
        }
        return i;
    }

    public boolean g() {
        return this.f1130b.size() > 0;
    }

    public b h(int i, int i2, int i3, Object obj) {
        b bVarA = this.f1129a.a();
        if (bVarA == null) {
            return new b(i, i2, i3, obj);
        }
        bVarA.f1135a = i;
        bVarA.f1136b = i2;
        bVarA.f1138d = i3;
        bVarA.f1137c = obj;
        return bVarA;
    }

    public final void i(b bVar) {
        this.f1131c.add(bVar);
        int i = bVar.f1135a;
        if (i == 1) {
            ((a0) this.f1132d).d(bVar.f1136b, bVar.f1138d);
            return;
        }
        if (i == 2) {
            a0 a0Var = (a0) this.f1132d;
            a0Var.f1139a.S(bVar.f1136b, bVar.f1138d, false);
            a0Var.f1139a.h0 = true;
            return;
        }
        if (i == 4) {
            ((a0) this.f1132d).c(bVar.f1136b, bVar.f1138d, bVar.f1137c);
        } else if (i == 8) {
            ((a0) this.f1132d).e(bVar.f1136b, bVar.f1138d);
        } else {
            throw new IllegalArgumentException("Unknown update op type for " + bVar);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x0195 A[PHI: r6
  0x0195: PHI (r6v30 int) = (r6v24 int), (r6v34 int) binds: [B:105:0x0193, B:92:0x0169] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x00ac A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0140 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0129 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:202:0x01ac A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:204:0x00d9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0009 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0009 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x010e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void j() {
        /*
            Method dump skipped, instruction units count: 720
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.p.e.a.j():void");
    }

    public void k(b bVar) {
        if (this.f1133e) {
            return;
        }
        bVar.f1137c = null;
        this.f1129a.b(bVar);
    }

    public void l(List<b> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            k(list.get(i));
        }
        list.clear();
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int m(int r8, int r9) {
        /*
            r7 = this;
            java.util.ArrayList<a.p.e.a$b> r0 = r7.f1131c
            int r0 = r0.size()
            r1 = 1
            int r0 = r0 - r1
        L8:
            r2 = 8
            if (r0 < 0) goto L84
            java.util.ArrayList<a.p.e.a$b> r3 = r7.f1131c
            java.lang.Object r3 = r3.get(r0)
            a.p.e.a$b r3 = (a.p.e.a.b) r3
            int r4 = r3.f1135a
            r5 = 2
            if (r4 != r2) goto L65
            int r2 = r3.f1136b
            int r4 = r3.f1138d
            if (r2 >= r4) goto L20
            goto L23
        L20:
            r6 = r4
            r4 = r2
            r2 = r6
        L23:
            if (r8 < r2) goto L4b
            if (r8 > r4) goto L4b
            int r4 = r3.f1136b
            if (r2 != r4) goto L3c
            if (r9 != r1) goto L33
            int r2 = r3.f1138d
            int r2 = r2 + r1
        L30:
            r3.f1138d = r2
            goto L39
        L33:
            if (r9 != r5) goto L39
            int r2 = r3.f1138d
            int r2 = r2 - r1
            goto L30
        L39:
            int r8 = r8 + 1
            goto L81
        L3c:
            if (r9 != r1) goto L43
            int r4 = r4 + 1
        L40:
            r3.f1136b = r4
            goto L48
        L43:
            if (r9 != r5) goto L48
            int r4 = r4 + (-1)
            goto L40
        L48:
            int r8 = r8 + (-1)
            goto L81
        L4b:
            int r2 = r3.f1136b
            if (r8 >= r2) goto L81
            if (r9 != r1) goto L5b
            int r2 = r2 + 1
            r3.f1136b = r2
            int r2 = r3.f1138d
            int r2 = r2 + r1
        L58:
            r3.f1138d = r2
            goto L81
        L5b:
            if (r9 != r5) goto L81
            int r2 = r2 + (-1)
            r3.f1136b = r2
            int r2 = r3.f1138d
            int r2 = r2 - r1
            goto L58
        L65:
            int r2 = r3.f1136b
            if (r2 > r8) goto L75
            if (r4 != r1) goto L6f
            int r2 = r3.f1138d
            int r8 = r8 - r2
            goto L81
        L6f:
            if (r4 != r5) goto L81
            int r2 = r3.f1138d
            int r8 = r8 + r2
            goto L81
        L75:
            if (r9 != r1) goto L7c
            int r2 = r2 + 1
        L79:
            r3.f1136b = r2
            goto L81
        L7c:
            if (r9 != r5) goto L81
            int r2 = r2 + (-1)
            goto L79
        L81:
            int r0 = r0 + (-1)
            goto L8
        L84:
            java.util.ArrayList<a.p.e.a$b> r9 = r7.f1131c
            int r9 = r9.size()
            int r9 = r9 - r1
        L8b:
            if (r9 < 0) goto Lc4
            java.util.ArrayList<a.p.e.a$b> r0 = r7.f1131c
            java.lang.Object r0 = r0.get(r9)
            a.p.e.a$b r0 = (a.p.e.a.b) r0
            int r1 = r0.f1135a
            r3 = 0
            if (r1 != r2) goto Lb3
            int r1 = r0.f1138d
            int r4 = r0.f1136b
            if (r1 == r4) goto La2
            if (r1 >= 0) goto Lc1
        La2:
            java.util.ArrayList<a.p.e.a$b> r1 = r7.f1131c
            r1.remove(r9)
            boolean r1 = r7.f1133e
            if (r1 != 0) goto Lc1
        Lab:
            r0.f1137c = r3
            a.h.j.d<a.p.e.a$b> r1 = r7.f1129a
            r1.b(r0)
            goto Lc1
        Lb3:
            int r1 = r0.f1138d
            if (r1 > 0) goto Lc1
            java.util.ArrayList<a.p.e.a$b> r1 = r7.f1131c
            r1.remove(r9)
            boolean r1 = r7.f1133e
            if (r1 != 0) goto Lc1
            goto Lab
        Lc1:
            int r9 = r9 + (-1)
            goto L8b
        Lc4:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: a.p.e.a.m(int, int):int");
    }
}
