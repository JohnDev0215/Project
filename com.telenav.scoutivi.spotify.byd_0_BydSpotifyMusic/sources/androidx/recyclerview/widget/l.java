package androidx.recyclerview.widget;

import androidx.recyclerview.widget.a;
import java.util.List;

/* JADX INFO: compiled from: OpReorderer.java */
/* JADX INFO: loaded from: classes.dex */
class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final a f3163a;

    /* JADX INFO: compiled from: OpReorderer.java */
    interface a {
        void a(a.b bVar);

        a.b b(int i7, int i8, int i9, Object obj);
    }

    l(a aVar) {
        this.f3163a = aVar;
    }

    private int a(List<a.b> list) {
        boolean z6 = false;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).f3014a != 8) {
                z6 = true;
            } else if (z6) {
                return size;
            }
        }
        return -1;
    }

    private void c(List<a.b> list, int i7, a.b bVar, int i8, a.b bVar2) {
        int i9 = bVar.f3017d;
        int i10 = bVar2.f3015b;
        int i11 = i9 < i10 ? -1 : 0;
        int i12 = bVar.f3015b;
        if (i12 < i10) {
            i11++;
        }
        if (i10 <= i12) {
            bVar.f3015b = i12 + bVar2.f3017d;
        }
        int i13 = bVar2.f3015b;
        if (i13 <= i9) {
            bVar.f3017d = i9 + bVar2.f3017d;
        }
        bVar2.f3015b = i13 + i11;
        list.set(i7, bVar2);
        list.set(i8, bVar);
    }

    private void d(List<a.b> list, int i7, int i8) {
        a.b bVar = list.get(i7);
        a.b bVar2 = list.get(i8);
        int i9 = bVar2.f3014a;
        if (i9 == 1) {
            c(list, i7, bVar, i8, bVar2);
        } else if (i9 == 2) {
            e(list, i7, bVar, i8, bVar2);
        } else {
            if (i9 != 4) {
                return;
            }
            f(list, i7, bVar, i8, bVar2);
        }
    }

    void b(List<a.b> list) {
        while (true) {
            int iA = a(list);
            if (iA == -1) {
                return;
            } else {
                d(list, iA, iA + 1);
            }
        }
    }

    void e(List<a.b> list, int i7, a.b bVar, int i8, a.b bVar2) {
        boolean z6;
        int i9 = bVar.f3015b;
        int i10 = bVar.f3017d;
        boolean z7 = false;
        if (i9 < i10) {
            if (bVar2.f3015b == i9 && bVar2.f3017d == i10 - i9) {
                z6 = false;
                z7 = true;
            } else {
                z6 = false;
            }
        } else if (bVar2.f3015b == i10 + 1 && bVar2.f3017d == i9 - i10) {
            z6 = true;
            z7 = true;
        } else {
            z6 = true;
        }
        int i11 = bVar2.f3015b;
        if (i10 < i11) {
            bVar2.f3015b = i11 - 1;
        } else {
            int i12 = bVar2.f3017d;
            if (i10 < i11 + i12) {
                bVar2.f3017d = i12 - 1;
                bVar.f3014a = 2;
                bVar.f3017d = 1;
                if (bVar2.f3017d == 0) {
                    list.remove(i8);
                    this.f3163a.a(bVar2);
                    return;
                }
                return;
            }
        }
        int i13 = bVar.f3015b;
        int i14 = bVar2.f3015b;
        a.b bVarB = null;
        if (i13 <= i14) {
            bVar2.f3015b = i14 + 1;
        } else {
            int i15 = bVar2.f3017d;
            if (i13 < i14 + i15) {
                bVarB = this.f3163a.b(2, i13 + 1, (i14 + i15) - i13, null);
                bVar2.f3017d = bVar.f3015b - bVar2.f3015b;
            }
        }
        if (z7) {
            list.set(i7, bVar2);
            list.remove(i8);
            this.f3163a.a(bVar);
            return;
        }
        if (z6) {
            if (bVarB != null) {
                int i16 = bVar.f3015b;
                if (i16 > bVarB.f3015b) {
                    bVar.f3015b = i16 - bVarB.f3017d;
                }
                int i17 = bVar.f3017d;
                if (i17 > bVarB.f3015b) {
                    bVar.f3017d = i17 - bVarB.f3017d;
                }
            }
            int i18 = bVar.f3015b;
            if (i18 > bVar2.f3015b) {
                bVar.f3015b = i18 - bVar2.f3017d;
            }
            int i19 = bVar.f3017d;
            if (i19 > bVar2.f3015b) {
                bVar.f3017d = i19 - bVar2.f3017d;
            }
        } else {
            if (bVarB != null) {
                int i20 = bVar.f3015b;
                if (i20 >= bVarB.f3015b) {
                    bVar.f3015b = i20 - bVarB.f3017d;
                }
                int i21 = bVar.f3017d;
                if (i21 >= bVarB.f3015b) {
                    bVar.f3017d = i21 - bVarB.f3017d;
                }
            }
            int i22 = bVar.f3015b;
            if (i22 >= bVar2.f3015b) {
                bVar.f3015b = i22 - bVar2.f3017d;
            }
            int i23 = bVar.f3017d;
            if (i23 >= bVar2.f3015b) {
                bVar.f3017d = i23 - bVar2.f3017d;
            }
        }
        list.set(i7, bVar2);
        if (bVar.f3015b != bVar.f3017d) {
            list.set(i8, bVar);
        } else {
            list.remove(i8);
        }
        if (bVarB != null) {
            list.add(i7, bVarB);
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
    void f(java.util.List<androidx.recyclerview.widget.a.b> r9, int r10, androidx.recyclerview.widget.a.b r11, int r12, androidx.recyclerview.widget.a.b r13) {
        /*
            r8 = this;
            int r0 = r11.f3017d
            int r1 = r13.f3015b
            r2 = 4
            r3 = 0
            r4 = 1
            if (r0 >= r1) goto Ld
            int r1 = r1 - r4
            r13.f3015b = r1
            goto L20
        Ld:
            int r5 = r13.f3017d
            int r1 = r1 + r5
            if (r0 >= r1) goto L20
            int r5 = r5 - r4
            r13.f3017d = r5
            androidx.recyclerview.widget.l$a r0 = r8.f3163a
            int r1 = r11.f3015b
            java.lang.Object r5 = r13.f3016c
            androidx.recyclerview.widget.a$b r0 = r0.b(r2, r1, r4, r5)
            goto L21
        L20:
            r0 = r3
        L21:
            int r1 = r11.f3015b
            int r5 = r13.f3015b
            if (r1 > r5) goto L2b
            int r5 = r5 + r4
            r13.f3015b = r5
            goto L41
        L2b:
            int r6 = r13.f3017d
            int r7 = r5 + r6
            if (r1 >= r7) goto L41
            int r5 = r5 + r6
            int r5 = r5 - r1
            androidx.recyclerview.widget.l$a r3 = r8.f3163a
            int r1 = r1 + r4
            java.lang.Object r4 = r13.f3016c
            androidx.recyclerview.widget.a$b r3 = r3.b(r2, r1, r5, r4)
            int r1 = r13.f3017d
            int r1 = r1 - r5
            r13.f3017d = r1
        L41:
            r9.set(r12, r11)
            int r11 = r13.f3017d
            if (r11 <= 0) goto L4c
            r9.set(r10, r13)
            goto L54
        L4c:
            r9.remove(r10)
            androidx.recyclerview.widget.l$a r11 = r8.f3163a
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
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.l.f(java.util.List, int, androidx.recyclerview.widget.a$b, int, androidx.recyclerview.widget.a$b):void");
    }
}
