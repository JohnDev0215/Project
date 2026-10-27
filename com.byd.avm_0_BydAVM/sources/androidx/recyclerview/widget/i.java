package androidx.recyclerview.widget;

import androidx.recyclerview.widget.a;
import java.util.List;

/* JADX INFO: compiled from: OpReorderer.java */
/* JADX INFO: loaded from: classes.dex */
class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final a f1296a;

    /* JADX INFO: compiled from: OpReorderer.java */
    interface a {
        void a(a.b bVar);

        a.b b(int i, int i2, int i3, Object obj);
    }

    i(a aVar) {
        this.f1296a = aVar;
    }

    private int a(List<a.b> list) {
        boolean z = false;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).f1228a != 8) {
                z = true;
            } else if (z) {
                return size;
            }
        }
        return -1;
    }

    private void c(List<a.b> list, int i, a.b bVar, int i2, a.b bVar2) {
        int i3 = bVar.f1231d;
        int i4 = bVar2.f1229b;
        int i5 = i3 < i4 ? -1 : 0;
        int i6 = bVar.f1229b;
        if (i6 < i4) {
            i5++;
        }
        if (i4 <= i6) {
            bVar.f1229b = i6 + bVar2.f1231d;
        }
        int i7 = bVar2.f1229b;
        if (i7 <= i3) {
            bVar.f1231d = i3 + bVar2.f1231d;
        }
        bVar2.f1229b = i7 + i5;
        list.set(i, bVar2);
        list.set(i2, bVar);
    }

    private void d(List<a.b> list, int i, int i2) {
        a.b bVar = list.get(i);
        a.b bVar2 = list.get(i2);
        int i3 = bVar2.f1228a;
        if (i3 == 1) {
            c(list, i, bVar, i2, bVar2);
        } else if (i3 == 2) {
            e(list, i, bVar, i2, bVar2);
        } else {
            if (i3 != 4) {
                return;
            }
            f(list, i, bVar, i2, bVar2);
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

    void e(List<a.b> list, int i, a.b bVar, int i2, a.b bVar2) {
        boolean z;
        int i3 = bVar.f1229b;
        int i4 = bVar.f1231d;
        boolean z2 = false;
        if (i3 < i4) {
            if (bVar2.f1229b == i3 && bVar2.f1231d == i4 - i3) {
                z = false;
                z2 = true;
            } else {
                z = false;
            }
        } else if (bVar2.f1229b == i4 + 1 && bVar2.f1231d == i3 - i4) {
            z = true;
            z2 = true;
        } else {
            z = true;
        }
        int i5 = bVar2.f1229b;
        if (i4 < i5) {
            bVar2.f1229b = i5 - 1;
        } else {
            int i6 = bVar2.f1231d;
            if (i4 < i5 + i6) {
                bVar2.f1231d = i6 - 1;
                bVar.f1228a = 2;
                bVar.f1231d = 1;
                if (bVar2.f1231d == 0) {
                    list.remove(i2);
                    this.f1296a.a(bVar2);
                    return;
                }
                return;
            }
        }
        int i7 = bVar.f1229b;
        int i8 = bVar2.f1229b;
        a.b bVarB = null;
        if (i7 <= i8) {
            bVar2.f1229b = i8 + 1;
        } else {
            int i9 = bVar2.f1231d;
            if (i7 < i8 + i9) {
                bVarB = this.f1296a.b(2, i7 + 1, (i8 + i9) - i7, null);
                bVar2.f1231d = bVar.f1229b - bVar2.f1229b;
            }
        }
        if (z2) {
            list.set(i, bVar2);
            list.remove(i2);
            this.f1296a.a(bVar);
            return;
        }
        if (z) {
            if (bVarB != null) {
                int i10 = bVar.f1229b;
                if (i10 > bVarB.f1229b) {
                    bVar.f1229b = i10 - bVarB.f1231d;
                }
                int i11 = bVar.f1231d;
                if (i11 > bVarB.f1229b) {
                    bVar.f1231d = i11 - bVarB.f1231d;
                }
            }
            int i12 = bVar.f1229b;
            if (i12 > bVar2.f1229b) {
                bVar.f1229b = i12 - bVar2.f1231d;
            }
            int i13 = bVar.f1231d;
            if (i13 > bVar2.f1229b) {
                bVar.f1231d = i13 - bVar2.f1231d;
            }
        } else {
            if (bVarB != null) {
                int i14 = bVar.f1229b;
                if (i14 >= bVarB.f1229b) {
                    bVar.f1229b = i14 - bVarB.f1231d;
                }
                int i15 = bVar.f1231d;
                if (i15 >= bVarB.f1229b) {
                    bVar.f1231d = i15 - bVarB.f1231d;
                }
            }
            int i16 = bVar.f1229b;
            if (i16 >= bVar2.f1229b) {
                bVar.f1229b = i16 - bVar2.f1231d;
            }
            int i17 = bVar.f1231d;
            if (i17 >= bVar2.f1229b) {
                bVar.f1231d = i17 - bVar2.f1231d;
            }
        }
        list.set(i, bVar2);
        if (bVar.f1229b != bVar.f1231d) {
            list.set(i2, bVar);
        } else {
            list.remove(i2);
        }
        if (bVarB != null) {
            list.add(i, bVarB);
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
            int r0 = r11.f1231d
            int r1 = r13.f1229b
            r2 = 4
            r3 = 0
            r4 = 1
            if (r0 >= r1) goto Ld
            int r1 = r1 - r4
            r13.f1229b = r1
            goto L20
        Ld:
            int r5 = r13.f1231d
            int r1 = r1 + r5
            if (r0 >= r1) goto L20
            int r5 = r5 - r4
            r13.f1231d = r5
            androidx.recyclerview.widget.i$a r0 = r8.f1296a
            int r1 = r11.f1229b
            java.lang.Object r5 = r13.f1230c
            androidx.recyclerview.widget.a$b r0 = r0.b(r2, r1, r4, r5)
            goto L21
        L20:
            r0 = r3
        L21:
            int r1 = r11.f1229b
            int r5 = r13.f1229b
            if (r1 > r5) goto L2b
            int r5 = r5 + r4
            r13.f1229b = r5
            goto L41
        L2b:
            int r6 = r13.f1231d
            int r7 = r5 + r6
            if (r1 >= r7) goto L41
            int r5 = r5 + r6
            int r5 = r5 - r1
            androidx.recyclerview.widget.i$a r3 = r8.f1296a
            int r1 = r1 + r4
            java.lang.Object r4 = r13.f1230c
            androidx.recyclerview.widget.a$b r3 = r3.b(r2, r1, r5, r4)
            int r1 = r13.f1231d
            int r1 = r1 - r5
            r13.f1231d = r1
        L41:
            r9.set(r12, r11)
            int r11 = r13.f1231d
            if (r11 <= 0) goto L4c
            r9.set(r10, r13)
            goto L54
        L4c:
            r9.remove(r10)
            androidx.recyclerview.widget.i$a r8 = r8.f1296a
            r8.a(r13)
        L54:
            if (r0 == 0) goto L59
            r9.add(r10, r0)
        L59:
            if (r3 == 0) goto L5e
            r9.add(r10, r3)
        L5e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.i.f(java.util.List, int, androidx.recyclerview.widget.a$b, int, androidx.recyclerview.widget.a$b):void");
    }
}
