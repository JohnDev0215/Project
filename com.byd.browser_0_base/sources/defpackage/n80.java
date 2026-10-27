package defpackage;

import androidx.constraintlayout.widget.ConstraintLayout;
import defpackage.ae;
import defpackage.qx;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: DependencyGraph.java */
/* JADX INFO: loaded from: classes.dex */
public final class n80 {
    public final rx a;
    public final rx d;
    public ae.b f;
    public final ae.a g;
    public final ArrayList<yc2> h;
    public boolean b = true;
    public boolean c = true;
    public final ArrayList<gt3> e = new ArrayList<>();

    public n80(rx rxVar) {
        new ArrayList();
        this.f = null;
        this.g = new ae.a();
        this.h = new ArrayList<>();
        this.a = rxVar;
        this.d = rxVar;
    }

    public final void a(o80 o80Var, int i, int i2, ArrayList arrayList, yc2 yc2Var) {
        gt3 gt3Var = o80Var.d;
        if (gt3Var.c == null) {
            rx rxVar = this.a;
            if (gt3Var == rxVar.d || gt3Var == rxVar.e) {
                return;
            }
            if (yc2Var == null) {
                yc2Var = new yc2(gt3Var);
                arrayList.add(yc2Var);
            }
            gt3Var.c = yc2Var;
            yc2Var.b.add(gt3Var);
            o80 o80Var2 = gt3Var.h;
            for (m80 m80Var : o80Var2.k) {
                if (m80Var instanceof o80) {
                    a((o80) m80Var, i, 0, arrayList, yc2Var);
                }
            }
            o80 o80Var3 = gt3Var.i;
            for (m80 m80Var2 : o80Var3.k) {
                if (m80Var2 instanceof o80) {
                    a((o80) m80Var2, i, 1, arrayList, yc2Var);
                }
            }
            if (i == 1 && (gt3Var instanceof nl3)) {
                for (m80 m80Var3 : ((nl3) gt3Var).k.k) {
                    if (m80Var3 instanceof o80) {
                        a((o80) m80Var3, i, 2, arrayList, yc2Var);
                    }
                }
            }
            Iterator it = o80Var2.l.iterator();
            while (it.hasNext()) {
                a((o80) it.next(), i, 0, arrayList, yc2Var);
            }
            Iterator it2 = o80Var3.l.iterator();
            while (it2.hasNext()) {
                a((o80) it2.next(), i, 1, arrayList, yc2Var);
            }
            if (i == 1 && (gt3Var instanceof nl3)) {
                Iterator it3 = ((nl3) gt3Var).k.l.iterator();
                while (it3.hasNext()) {
                    a((o80) it3.next(), i, 2, arrayList, yc2Var);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0183  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(defpackage.rx r18) {
        /*
            Method dump skipped, instruction units count: 786
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.n80.b(rx):void");
    }

    public final void c() {
        ArrayList<gt3> arrayList = this.e;
        arrayList.clear();
        rx rxVar = this.d;
        rxVar.d.f();
        rxVar.e.f();
        arrayList.add(rxVar.d);
        arrayList.add(rxVar.e);
        HashSet hashSet = null;
        for (qx qxVar : rxVar.w0) {
            if (qxVar instanceof rt0) {
                arrayList.add(new st0(qxVar));
            } else {
                if (qxVar.B()) {
                    if (qxVar.b == null) {
                        qxVar.b = new wm(0, qxVar);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(qxVar.b);
                } else {
                    arrayList.add(qxVar.d);
                }
                if (qxVar.C()) {
                    if (qxVar.c == null) {
                        qxVar.c = new wm(1, qxVar);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(qxVar.c);
                } else {
                    arrayList.add(qxVar.e);
                }
                if (qxVar instanceof ku0) {
                    arrayList.add(new ju0(qxVar));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator<gt3> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().f();
        }
        for (gt3 gt3Var : arrayList) {
            if (gt3Var.b != rxVar) {
                gt3Var.d();
            }
        }
        ArrayList<yc2> arrayList2 = this.h;
        arrayList2.clear();
        rx rxVar2 = this.a;
        e(rxVar2.d, 0, arrayList2);
        e(rxVar2.e, 1, arrayList2);
        this.b = false;
    }

    public final int d(rx rxVar, int i) {
        ArrayList<yc2> arrayList;
        int i2;
        int i3;
        float f;
        rx rxVar2 = rxVar;
        ArrayList<yc2> arrayList2 = this.h;
        int size = arrayList2.size();
        int i4 = 0;
        long jMax = 0;
        long jMax2 = 0;
        while (i4 < size) {
            gt3 gt3Var = arrayList2.get(i4).a;
            if (!(gt3Var instanceof wm) ? !(i != 0 ? (gt3Var instanceof nl3) : (gt3Var instanceof tv0)) : ((wm) gt3Var).f != i) {
                o80 o80Var = (i == 0 ? rxVar2.d : rxVar2.e).h;
                o80 o80Var2 = (i == 0 ? rxVar2.d : rxVar2.e).i;
                boolean zContains = gt3Var.h.l.contains(o80Var);
                o80 o80Var3 = gt3Var.i;
                boolean zContains2 = o80Var3.l.contains(o80Var2);
                long j = gt3Var.j();
                o80 o80Var4 = gt3Var.h;
                if (zContains && zContains2) {
                    long jB = yc2.b(o80Var4, jMax2);
                    long jA = yc2.a(o80Var3, jMax2);
                    long j2 = jB - j;
                    int i5 = o80Var3.f;
                    arrayList = arrayList2;
                    i2 = size;
                    i3 = i4;
                    if (j2 >= (-i5)) {
                        j2 += (long) i5;
                    }
                    long j3 = o80Var4.f;
                    long j4 = ((-jA) - j) - j3;
                    if (j4 >= j3) {
                        j4 -= j3;
                    }
                    qx qxVar = gt3Var.b;
                    if (i == 0) {
                        f = qxVar.g0;
                    } else if (i == 1) {
                        f = qxVar.h0;
                    } else {
                        qxVar.getClass();
                        f = -1.0f;
                    }
                    float f2 = f > 0.0f ? (long) ((j2 / (1.0f - f)) + (j4 / f)) : 0L;
                    jMax2 = (((long) o80Var4.f) + ((((long) ((f2 * f) + 0.5f)) + j) + ((long) C0173a.f(1.0f, f, f2, 0.5f)))) - ((long) o80Var3.f);
                } else {
                    arrayList = arrayList2;
                    i2 = size;
                    i3 = i4;
                    jMax2 = zContains ? Math.max(yc2.b(o80Var4, o80Var4.f), ((long) o80Var4.f) + j) : zContains2 ? Math.max(-yc2.a(o80Var3, o80Var3.f), ((long) (-o80Var3.f)) + j) : (gt3Var.j() + ((long) o80Var4.f)) - ((long) o80Var3.f);
                }
            } else {
                arrayList = arrayList2;
                i2 = size;
                i3 = i4;
            }
            jMax = Math.max(jMax, jMax2);
            i4 = i3 + 1;
            jMax2 = 0;
            arrayList2 = arrayList;
            rxVar2 = rxVar;
            size = i2;
        }
        return (int) jMax;
    }

    public final void e(gt3 gt3Var, int i, ArrayList<yc2> arrayList) {
        o80 o80Var;
        Iterator it = gt3Var.h.k.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            o80Var = gt3Var.i;
            if (!zHasNext) {
                break;
            }
            m80 m80Var = (m80) it.next();
            if (m80Var instanceof o80) {
                a((o80) m80Var, i, 0, arrayList, null);
            } else if (m80Var instanceof gt3) {
                a(((gt3) m80Var).h, i, 0, arrayList, null);
            }
        }
        for (m80 m80Var2 : o80Var.k) {
            if (m80Var2 instanceof o80) {
                a((o80) m80Var2, i, 1, arrayList, null);
            } else if (m80Var2 instanceof gt3) {
                a(((gt3) m80Var2).i, i, 1, arrayList, null);
            }
        }
        if (i == 1) {
            for (m80 m80Var3 : ((nl3) gt3Var).k.k) {
                if (m80Var3 instanceof o80) {
                    a((o80) m80Var3, i, 2, arrayList, null);
                }
            }
        }
    }

    public final void f(qx qxVar, qx.a aVar, int i, qx.a aVar2, int i2) {
        ae.a aVar3 = this.g;
        aVar3.a = aVar;
        aVar3.b = aVar2;
        aVar3.c = i;
        aVar3.d = i2;
        ((ConstraintLayout.a) this.f).b(qxVar, aVar3);
        qxVar.R(aVar3.e);
        qxVar.O(aVar3.f);
        qxVar.F = aVar3.h;
        int i3 = aVar3.g;
        qxVar.d0 = i3;
        qxVar.F = i3 > 0;
    }

    public final void g() {
        xd xdVar;
        for (qx qxVar : this.a.w0) {
            if (!qxVar.a) {
                qx.a[] aVarArr = qxVar.V;
                boolean z = false;
                qx.a aVar = aVarArr[0];
                qx.a aVar2 = aVarArr[1];
                int i = qxVar.s;
                int i2 = qxVar.t;
                qx.a aVar3 = qx.a.WRAP_CONTENT;
                qx.a aVar4 = qx.a.MATCH_CONSTRAINT;
                boolean z2 = aVar == aVar3 || (aVar == aVar4 && i == 1);
                if (aVar2 == aVar3 || (aVar2 == aVar4 && i2 == 1)) {
                    z = true;
                }
                ca0 ca0Var = qxVar.d.e;
                boolean z3 = ca0Var.j;
                ca0 ca0Var2 = qxVar.e.e;
                boolean z4 = ca0Var2.j;
                qx.a aVar5 = qx.a.FIXED;
                if (z3 && z4) {
                    f(qxVar, aVar5, ca0Var.g, aVar5, ca0Var2.g);
                    qxVar.a = true;
                } else if (z3 && z) {
                    f(qxVar, aVar5, ca0Var.g, aVar3, ca0Var2.g);
                    if (aVar2 == aVar4) {
                        qxVar.e.e.m = qxVar.o();
                    } else {
                        qxVar.e.e.d(qxVar.o());
                        qxVar.a = true;
                    }
                } else if (z4 && z2) {
                    f(qxVar, aVar3, ca0Var.g, aVar5, ca0Var2.g);
                    if (aVar == aVar4) {
                        qxVar.d.e.m = qxVar.u();
                    } else {
                        qxVar.d.e.d(qxVar.u());
                        qxVar.a = true;
                    }
                }
                if (qxVar.a && (xdVar = qxVar.e.l) != null) {
                    xdVar.d(qxVar.d0);
                }
            }
        }
    }
}
