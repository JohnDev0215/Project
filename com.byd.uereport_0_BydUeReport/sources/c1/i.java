package c1;

import c1.a;
import java.util.List;

/* JADX INFO: compiled from: OpReorderer.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f2270a;

    /* JADX INFO: compiled from: OpReorderer.java */
    public interface a {
        void a(a.b bVar);

        a.b b(int i8, int i9, int i10, Object obj);
    }

    public i(a aVar) {
        this.f2270a = aVar;
    }

    public final int a(List<a.b> list) {
        boolean z7 = false;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).f2153a != 8) {
                z7 = true;
            } else if (z7) {
                return size;
            }
        }
        return -1;
    }

    public void b(List<a.b> list) {
        while (true) {
            int iA = a(list);
            if (iA == -1) {
                return;
            } else {
                d(list, iA, iA + 1);
            }
        }
    }

    public final void c(List<a.b> list, int i8, a.b bVar, int i9, a.b bVar2) {
        int i10 = bVar.f2156d;
        int i11 = bVar2.f2154b;
        int i12 = i10 < i11 ? -1 : 0;
        int i13 = bVar.f2154b;
        if (i13 < i11) {
            i12++;
        }
        if (i11 <= i13) {
            bVar.f2154b = i13 + bVar2.f2156d;
        }
        int i14 = bVar2.f2154b;
        if (i14 <= i10) {
            bVar.f2156d = i10 + bVar2.f2156d;
        }
        bVar2.f2154b = i14 + i12;
        list.set(i8, bVar2);
        list.set(i9, bVar);
    }

    public final void d(List<a.b> list, int i8, int i9) {
        a.b bVar = list.get(i8);
        a.b bVar2 = list.get(i9);
        int i10 = bVar2.f2153a;
        if (i10 == 1) {
            c(list, i8, bVar, i9, bVar2);
        } else if (i10 == 2) {
            e(list, i8, bVar, i9, bVar2);
        } else {
            if (i10 != 4) {
                return;
            }
            f(list, i8, bVar, i9, bVar2);
        }
    }

    public void e(List<a.b> list, int i8, a.b bVar, int i9, a.b bVar2) {
        boolean z7;
        int i10 = bVar.f2154b;
        int i11 = bVar.f2156d;
        boolean z8 = false;
        if (i10 < i11) {
            if (bVar2.f2154b == i10 && bVar2.f2156d == i11 - i10) {
                z7 = false;
                z8 = true;
            } else {
                z7 = false;
            }
        } else if (bVar2.f2154b == i11 + 1 && bVar2.f2156d == i10 - i11) {
            z7 = true;
            z8 = true;
        } else {
            z7 = true;
        }
        int i12 = bVar2.f2154b;
        if (i11 < i12) {
            bVar2.f2154b = i12 - 1;
        } else {
            int i13 = bVar2.f2156d;
            if (i11 < i12 + i13) {
                bVar2.f2156d = i13 - 1;
                bVar.f2153a = 2;
                bVar.f2156d = 1;
                if (bVar2.f2156d == 0) {
                    list.remove(i9);
                    this.f2270a.a(bVar2);
                    return;
                }
                return;
            }
        }
        int i14 = bVar.f2154b;
        int i15 = bVar2.f2154b;
        a.b bVarB = null;
        if (i14 <= i15) {
            bVar2.f2154b = i15 + 1;
        } else {
            int i16 = bVar2.f2156d;
            if (i14 < i15 + i16) {
                bVarB = this.f2270a.b(2, i14 + 1, (i15 + i16) - i14, null);
                bVar2.f2156d = bVar.f2154b - bVar2.f2154b;
            }
        }
        if (z8) {
            list.set(i8, bVar2);
            list.remove(i9);
            this.f2270a.a(bVar);
            return;
        }
        if (z7) {
            if (bVarB != null) {
                int i17 = bVar.f2154b;
                if (i17 > bVarB.f2154b) {
                    bVar.f2154b = i17 - bVarB.f2156d;
                }
                int i18 = bVar.f2156d;
                if (i18 > bVarB.f2154b) {
                    bVar.f2156d = i18 - bVarB.f2156d;
                }
            }
            int i19 = bVar.f2154b;
            if (i19 > bVar2.f2154b) {
                bVar.f2154b = i19 - bVar2.f2156d;
            }
            int i20 = bVar.f2156d;
            if (i20 > bVar2.f2154b) {
                bVar.f2156d = i20 - bVar2.f2156d;
            }
        } else {
            if (bVarB != null) {
                int i21 = bVar.f2154b;
                if (i21 >= bVarB.f2154b) {
                    bVar.f2154b = i21 - bVarB.f2156d;
                }
                int i22 = bVar.f2156d;
                if (i22 >= bVarB.f2154b) {
                    bVar.f2156d = i22 - bVarB.f2156d;
                }
            }
            int i23 = bVar.f2154b;
            if (i23 >= bVar2.f2154b) {
                bVar.f2154b = i23 - bVar2.f2156d;
            }
            int i24 = bVar.f2156d;
            if (i24 >= bVar2.f2154b) {
                bVar.f2156d = i24 - bVar2.f2156d;
            }
        }
        list.set(i8, bVar2);
        if (bVar.f2154b != bVar.f2156d) {
            list.set(i9, bVar);
        } else {
            list.remove(i9);
        }
        if (bVarB != null) {
            list.add(i8, bVarB);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void f(java.util.List<c1.a.b> r9, int r10, c1.a.b r11, int r12, c1.a.b r13) {
        /*
            r8 = this;
            int r0 = r11.f2156d
            int r1 = r13.f2154b
            r2 = 4
            r3 = 0
            r4 = 1
            if (r0 >= r1) goto Ld
            int r1 = r1 - r4
            r13.f2154b = r1
            goto L20
        Ld:
            int r5 = r13.f2156d
            int r1 = r1 + r5
            if (r0 >= r1) goto L20
            int r5 = r5 - r4
            r13.f2156d = r5
            c1.i$a r0 = r8.f2270a
            int r1 = r11.f2154b
            java.lang.Object r5 = r13.f2155c
            c1.a$b r0 = r0.b(r2, r1, r4, r5)
            goto L21
        L20:
            r0 = r3
        L21:
            int r1 = r11.f2154b
            int r5 = r13.f2154b
            if (r1 > r5) goto L2b
            int r5 = r5 + r4
            r13.f2154b = r5
            goto L41
        L2b:
            int r6 = r13.f2156d
            int r7 = r5 + r6
            if (r1 >= r7) goto L41
            int r5 = r5 + r6
            int r5 = r5 - r1
            c1.i$a r3 = r8.f2270a
            int r1 = r1 + r4
            java.lang.Object r4 = r13.f2155c
            c1.a$b r3 = r3.b(r2, r1, r5, r4)
            int r1 = r13.f2156d
            int r1 = r1 - r5
            r13.f2156d = r1
        L41:
            r9.set(r12, r11)
            int r11 = r13.f2156d
            if (r11 <= 0) goto L4c
            r9.set(r10, r13)
            goto L54
        L4c:
            r9.remove(r10)
            c1.i$a r11 = r8.f2270a
            r11.a(r13)
        L54:
            if (r0 == 0) goto L59
            r9.add(r10, r0)
        L59:
            if (r3 == 0) goto L5e
            r9.add(r10, r3)
        L5e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: c1.i.f(java.util.List, int, c1.a$b, int, c1.a$b):void");
    }
}
