package w0;

import java.util.List;
import w0.a;

/* JADX INFO: compiled from: OpReorderer.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f5650a;

    /* JADX INFO: compiled from: OpReorderer.java */
    public interface a {
        void a(a.b bVar);

        a.b b(int i3, int i4, int i5, Object obj);
    }

    public i(a aVar) {
        this.f5650a = aVar;
    }

    public final int a(List<a.b> list) {
        boolean z3 = false;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).f5525a != 8) {
                z3 = true;
            } else if (z3) {
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

    public final void c(List<a.b> list, int i3, a.b bVar, int i4, a.b bVar2) {
        int i5 = bVar.f5528d;
        int i6 = bVar2.f5526b;
        int i7 = i5 < i6 ? -1 : 0;
        int i8 = bVar.f5526b;
        if (i8 < i6) {
            i7++;
        }
        if (i6 <= i8) {
            bVar.f5526b = i8 + bVar2.f5528d;
        }
        int i9 = bVar2.f5526b;
        if (i9 <= i5) {
            bVar.f5528d = i5 + bVar2.f5528d;
        }
        bVar2.f5526b = i9 + i7;
        list.set(i3, bVar2);
        list.set(i4, bVar);
    }

    public final void d(List<a.b> list, int i3, int i4) {
        a.b bVar = list.get(i3);
        a.b bVar2 = list.get(i4);
        int i5 = bVar2.f5525a;
        if (i5 == 1) {
            c(list, i3, bVar, i4, bVar2);
        } else if (i5 == 2) {
            e(list, i3, bVar, i4, bVar2);
        } else {
            if (i5 != 4) {
                return;
            }
            f(list, i3, bVar, i4, bVar2);
        }
    }

    public void e(List<a.b> list, int i3, a.b bVar, int i4, a.b bVar2) {
        boolean z3;
        int i5 = bVar.f5526b;
        int i6 = bVar.f5528d;
        boolean z4 = false;
        if (i5 < i6) {
            if (bVar2.f5526b == i5 && bVar2.f5528d == i6 - i5) {
                z3 = false;
                z4 = true;
            } else {
                z3 = false;
            }
        } else if (bVar2.f5526b == i6 + 1 && bVar2.f5528d == i5 - i6) {
            z3 = true;
            z4 = true;
        } else {
            z3 = true;
        }
        int i7 = bVar2.f5526b;
        if (i6 < i7) {
            bVar2.f5526b = i7 - 1;
        } else {
            int i8 = bVar2.f5528d;
            if (i6 < i7 + i8) {
                bVar2.f5528d = i8 - 1;
                bVar.f5525a = 2;
                bVar.f5528d = 1;
                if (bVar2.f5528d == 0) {
                    list.remove(i4);
                    this.f5650a.a(bVar2);
                    return;
                }
                return;
            }
        }
        int i9 = bVar.f5526b;
        int i10 = bVar2.f5526b;
        a.b bVarB = null;
        if (i9 <= i10) {
            bVar2.f5526b = i10 + 1;
        } else {
            int i11 = bVar2.f5528d;
            if (i9 < i10 + i11) {
                bVarB = this.f5650a.b(2, i9 + 1, (i10 + i11) - i9, null);
                bVar2.f5528d = bVar.f5526b - bVar2.f5526b;
            }
        }
        if (z4) {
            list.set(i3, bVar2);
            list.remove(i4);
            this.f5650a.a(bVar);
            return;
        }
        if (z3) {
            if (bVarB != null) {
                int i12 = bVar.f5526b;
                if (i12 > bVarB.f5526b) {
                    bVar.f5526b = i12 - bVarB.f5528d;
                }
                int i13 = bVar.f5528d;
                if (i13 > bVarB.f5526b) {
                    bVar.f5528d = i13 - bVarB.f5528d;
                }
            }
            int i14 = bVar.f5526b;
            if (i14 > bVar2.f5526b) {
                bVar.f5526b = i14 - bVar2.f5528d;
            }
            int i15 = bVar.f5528d;
            if (i15 > bVar2.f5526b) {
                bVar.f5528d = i15 - bVar2.f5528d;
            }
        } else {
            if (bVarB != null) {
                int i16 = bVar.f5526b;
                if (i16 >= bVarB.f5526b) {
                    bVar.f5526b = i16 - bVarB.f5528d;
                }
                int i17 = bVar.f5528d;
                if (i17 >= bVarB.f5526b) {
                    bVar.f5528d = i17 - bVarB.f5528d;
                }
            }
            int i18 = bVar.f5526b;
            if (i18 >= bVar2.f5526b) {
                bVar.f5526b = i18 - bVar2.f5528d;
            }
            int i19 = bVar.f5528d;
            if (i19 >= bVar2.f5526b) {
                bVar.f5528d = i19 - bVar2.f5528d;
            }
        }
        list.set(i3, bVar2);
        if (bVar.f5526b != bVar.f5528d) {
            list.set(i4, bVar);
        } else {
            list.remove(i4);
        }
        if (bVarB != null) {
            list.add(i3, bVarB);
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
    public void f(java.util.List<w0.a.b> r9, int r10, w0.a.b r11, int r12, w0.a.b r13) {
        /*
            r8 = this;
            int r0 = r11.f5528d
            int r1 = r13.f5526b
            r2 = 4
            r3 = 0
            r4 = 1
            if (r0 >= r1) goto Ld
            int r1 = r1 - r4
            r13.f5526b = r1
            goto L20
        Ld:
            int r5 = r13.f5528d
            int r1 = r1 + r5
            if (r0 >= r1) goto L20
            int r5 = r5 - r4
            r13.f5528d = r5
            w0.i$a r0 = r8.f5650a
            int r1 = r11.f5526b
            java.lang.Object r5 = r13.f5527c
            w0.a$b r0 = r0.b(r2, r1, r4, r5)
            goto L21
        L20:
            r0 = r3
        L21:
            int r1 = r11.f5526b
            int r5 = r13.f5526b
            if (r1 > r5) goto L2b
            int r5 = r5 + r4
            r13.f5526b = r5
            goto L41
        L2b:
            int r6 = r13.f5528d
            int r7 = r5 + r6
            if (r1 >= r7) goto L41
            int r5 = r5 + r6
            int r5 = r5 - r1
            w0.i$a r3 = r8.f5650a
            int r1 = r1 + r4
            java.lang.Object r4 = r13.f5527c
            w0.a$b r3 = r3.b(r2, r1, r5, r4)
            int r1 = r13.f5528d
            int r1 = r1 - r5
            r13.f5528d = r1
        L41:
            r9.set(r12, r11)
            int r11 = r13.f5528d
            if (r11 <= 0) goto L4c
            r9.set(r10, r13)
            goto L54
        L4c:
            r9.remove(r10)
            w0.i$a r11 = r8.f5650a
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
        throw new UnsupportedOperationException("Method not decompiled: w0.i.f(java.util.List, int, w0.a$b, int, w0.a$b):void");
    }
}
