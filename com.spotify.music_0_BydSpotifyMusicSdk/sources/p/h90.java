package p;

import androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class h90 {
    public uy a;
    public boolean b;
    public boolean c;
    public uy d;
    public ArrayList e;
    public ey f;
    public ml g;
    public ArrayList h;

    public final void a(androidx.constraintlayout.core.widgets.analyzer.a aVar, int i, ArrayList arrayList, u52 u52Var) {
        androidx.constraintlayout.core.widgets.analyzer.f fVar = aVar.d;
        if (fVar.c == null) {
            uy uyVar = this.a;
            if (fVar == uyVar.d || fVar == uyVar.e) {
                return;
            }
            if (u52Var == null) {
                u52Var = new u52();
                u52Var.a = null;
                u52Var.b = new ArrayList();
                u52Var.a = fVar;
                arrayList.add(u52Var);
            }
            fVar.c = u52Var;
            u52Var.b.add(fVar);
            androidx.constraintlayout.core.widgets.analyzer.a aVar2 = fVar.h;
            for (e90 e90Var : aVar2.k) {
                if (e90Var instanceof androidx.constraintlayout.core.widgets.analyzer.a) {
                    a((androidx.constraintlayout.core.widgets.analyzer.a) e90Var, i, arrayList, u52Var);
                }
            }
            androidx.constraintlayout.core.widgets.analyzer.a aVar3 = fVar.i;
            for (e90 e90Var2 : aVar3.k) {
                if (e90Var2 instanceof androidx.constraintlayout.core.widgets.analyzer.a) {
                    a((androidx.constraintlayout.core.widgets.analyzer.a) e90Var2, i, arrayList, u52Var);
                }
            }
            if (i == 1 && (fVar instanceof androidx.constraintlayout.core.widgets.analyzer.e)) {
                for (e90 e90Var3 : ((androidx.constraintlayout.core.widgets.analyzer.e) fVar).k.k) {
                    if (e90Var3 instanceof androidx.constraintlayout.core.widgets.analyzer.a) {
                        a((androidx.constraintlayout.core.widgets.analyzer.a) e90Var3, i, arrayList, u52Var);
                    }
                }
            }
            Iterator it = aVar2.l.iterator();
            while (it.hasNext()) {
                a((androidx.constraintlayout.core.widgets.analyzer.a) it.next(), i, arrayList, u52Var);
            }
            Iterator it2 = aVar3.l.iterator();
            while (it2.hasNext()) {
                a((androidx.constraintlayout.core.widgets.analyzer.a) it2.next(), i, arrayList, u52Var);
            }
            if (i == 1 && (fVar instanceof androidx.constraintlayout.core.widgets.analyzer.e)) {
                Iterator it3 = ((androidx.constraintlayout.core.widgets.analyzer.e) fVar).k.l.iterator();
                while (it3.hasNext()) {
                    a((androidx.constraintlayout.core.widgets.analyzer.a) it3.next(), i, arrayList, u52Var);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:85:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x018d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(p.uy r18) {
        /*
            Method dump skipped, instruction units count: 786
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.h90.b(p.uy):void");
    }

    public final void c() {
        ArrayList<androidx.constraintlayout.core.widgets.analyzer.f> arrayList = this.e;
        arrayList.clear();
        uy uyVar = this.d;
        uyVar.d.f();
        uyVar.e.f();
        arrayList.add(uyVar.d);
        arrayList.add(uyVar.e);
        HashSet hashSet = null;
        for (ty tyVar : uyVar.p0) {
            if (tyVar instanceof d11) {
                e11 e11Var = new e11(tyVar);
                tyVar.d.f();
                tyVar.e.f();
                e11Var.f = ((d11) tyVar).t0;
                arrayList.add(e11Var);
            } else {
                if (tyVar.v()) {
                    if (tyVar.b == null) {
                        tyVar.b = new qq(0, tyVar);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(tyVar.b);
                } else {
                    arrayList.add(tyVar.d);
                }
                if (tyVar.w()) {
                    if (tyVar.c == null) {
                        tyVar.c = new qq(1, tyVar);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(tyVar.c);
                } else {
                    arrayList.add(tyVar.e);
                }
                if (tyVar instanceof z11) {
                    arrayList.add(new androidx.constraintlayout.core.widgets.analyzer.c(tyVar));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((androidx.constraintlayout.core.widgets.analyzer.f) it.next()).f();
        }
        for (androidx.constraintlayout.core.widgets.analyzer.f fVar : arrayList) {
            if (fVar.b != uyVar) {
                fVar.d();
            }
        }
        ArrayList arrayList2 = this.h;
        arrayList2.clear();
        uy uyVar2 = this.a;
        e(uyVar2.d, 0, arrayList2);
        e(uyVar2.e, 1, arrayList2);
        this.b = false;
    }

    public final int d(uy uyVar, int i) {
        ArrayList arrayList;
        int i2;
        int i3;
        long jMax;
        float f;
        uy uyVar2 = uyVar;
        ArrayList arrayList2 = this.h;
        int size = arrayList2.size();
        int i4 = 0;
        long jMax2 = 0;
        while (i4 < size) {
            androidx.constraintlayout.core.widgets.analyzer.f fVar = ((u52) arrayList2.get(i4)).a;
            if (!(fVar instanceof qq) ? !(i != 0 ? (fVar instanceof androidx.constraintlayout.core.widgets.analyzer.e) : (fVar instanceof androidx.constraintlayout.core.widgets.analyzer.d)) : ((qq) fVar).f != i) {
                androidx.constraintlayout.core.widgets.analyzer.a aVar = (i == 0 ? uyVar2.d : uyVar2.e).h;
                androidx.constraintlayout.core.widgets.analyzer.a aVar2 = (i == 0 ? uyVar2.d : uyVar2.e).i;
                boolean zContains = fVar.h.l.contains(aVar);
                androidx.constraintlayout.core.widgets.analyzer.a aVar3 = fVar.i;
                boolean zContains2 = aVar3.l.contains(aVar2);
                long j = fVar.j();
                androidx.constraintlayout.core.widgets.analyzer.a aVar4 = fVar.h;
                if (zContains && zContains2) {
                    long jB = u52.b(aVar4, 0L);
                    ArrayList arrayList3 = arrayList2;
                    i2 = size;
                    long jA = u52.a(aVar3, 0L);
                    long j2 = jB - j;
                    int i5 = aVar3.f;
                    arrayList = arrayList3;
                    i3 = i4;
                    if (j2 >= (-i5)) {
                        j2 += (long) i5;
                    }
                    long j3 = (-jA) - j;
                    long j4 = aVar4.f;
                    long j5 = j3 - j4;
                    if (j5 >= j4) {
                        j5 -= j4;
                    }
                    ty tyVar = fVar.b;
                    if (i == 0) {
                        f = tyVar.d0;
                    } else if (i == 1) {
                        f = tyVar.e0;
                    } else {
                        tyVar.getClass();
                        f = -1.0f;
                    }
                    float f2 = f > 0.0f ? (long) ((j2 / (1.0f - f)) + (j5 / f)) : 0L;
                    jMax = (((long) aVar4.f) + ((((long) ((f2 * f) + 0.5f)) + j) + ((long) (((1.0f - f) * f2) + 0.5f)))) - ((long) aVar3.f);
                } else {
                    arrayList = arrayList2;
                    i2 = size;
                    i3 = i4;
                    jMax = zContains ? Math.max(u52.b(aVar4, aVar4.f), ((long) aVar4.f) + j) : zContains2 ? Math.max(-u52.a(aVar3, aVar3.f), ((long) (-aVar3.f)) + j) : (fVar.j() + ((long) aVar4.f)) - ((long) aVar3.f);
                }
            } else {
                arrayList = arrayList2;
                i2 = size;
                i3 = i4;
                jMax = 0;
            }
            jMax2 = Math.max(jMax2, jMax);
            i4 = i3 + 1;
            uyVar2 = uyVar;
            size = i2;
            arrayList2 = arrayList;
        }
        return (int) jMax2;
    }

    public final void e(androidx.constraintlayout.core.widgets.analyzer.f fVar, int i, ArrayList arrayList) {
        androidx.constraintlayout.core.widgets.analyzer.a aVar;
        Iterator it = fVar.h.k.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            aVar = fVar.i;
            if (!zHasNext) {
                break;
            }
            e90 e90Var = (e90) it.next();
            if (e90Var instanceof androidx.constraintlayout.core.widgets.analyzer.a) {
                a((androidx.constraintlayout.core.widgets.analyzer.a) e90Var, i, arrayList, null);
            } else if (e90Var instanceof androidx.constraintlayout.core.widgets.analyzer.f) {
                a(((androidx.constraintlayout.core.widgets.analyzer.f) e90Var).h, i, arrayList, null);
            }
        }
        for (e90 e90Var2 : aVar.k) {
            if (e90Var2 instanceof androidx.constraintlayout.core.widgets.analyzer.a) {
                a((androidx.constraintlayout.core.widgets.analyzer.a) e90Var2, i, arrayList, null);
            } else if (e90Var2 instanceof androidx.constraintlayout.core.widgets.analyzer.f) {
                a(((androidx.constraintlayout.core.widgets.analyzer.f) e90Var2).i, i, arrayList, null);
            }
        }
        if (i == 1) {
            for (e90 e90Var3 : ((androidx.constraintlayout.core.widgets.analyzer.e) fVar).k.k) {
                if (e90Var3 instanceof androidx.constraintlayout.core.widgets.analyzer.a) {
                    a((androidx.constraintlayout.core.widgets.analyzer.a) e90Var3, i, arrayList, null);
                }
            }
        }
    }

    public final void f(ty tyVar, ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour, int i, ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2, int i2) {
        ml mlVar = this.g;
        mlVar.a = constraintWidget$DimensionBehaviour;
        mlVar.b = constraintWidget$DimensionBehaviour2;
        mlVar.c = i;
        mlVar.d = i2;
        this.f.b(tyVar, mlVar);
        tyVar.K(mlVar.e);
        tyVar.H(mlVar.f);
        tyVar.E = mlVar.h;
        int i3 = mlVar.g;
        tyVar.a0 = i3;
        tyVar.E = i3 > 0;
    }

    public final void g() {
        ll llVar;
        for (ty tyVar : this.a.p0) {
            if (!tyVar.a) {
                ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = tyVar.S;
                boolean z = false;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = constraintWidget$DimensionBehaviourArr[0];
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = constraintWidget$DimensionBehaviourArr[1];
                int i = tyVar.r;
                int i2 = tyVar.s;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = ConstraintWidget$DimensionBehaviour.e;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = ConstraintWidget$DimensionBehaviour.k;
                boolean z2 = constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour3 || (constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour4 && i == 1);
                if (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour3 || (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour4 && i2 == 1)) {
                    z = true;
                }
                androidx.constraintlayout.core.widgets.analyzer.b bVar = tyVar.d.e;
                boolean z3 = bVar.j;
                androidx.constraintlayout.core.widgets.analyzer.b bVar2 = tyVar.e.e;
                boolean z4 = bVar2.j;
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour5 = ConstraintWidget$DimensionBehaviour.c;
                if (z3 && z4) {
                    f(tyVar, constraintWidget$DimensionBehaviour5, bVar.g, constraintWidget$DimensionBehaviour5, bVar2.g);
                    tyVar.a = true;
                } else if (z3 && z) {
                    f(tyVar, constraintWidget$DimensionBehaviour5, bVar.g, constraintWidget$DimensionBehaviour3, bVar2.g);
                    if (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour4) {
                        tyVar.e.e.m = tyVar.i();
                    } else {
                        tyVar.e.e.d(tyVar.i());
                        tyVar.a = true;
                    }
                } else if (z4 && z2) {
                    f(tyVar, constraintWidget$DimensionBehaviour3, bVar.g, constraintWidget$DimensionBehaviour5, bVar2.g);
                    if (constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour4) {
                        tyVar.d.e.m = tyVar.o();
                    } else {
                        tyVar.d.e.d(tyVar.o());
                        tyVar.a = true;
                    }
                }
                if (tyVar.a && (llVar = tyVar.e.l) != null) {
                    llVar.d(tyVar.a0);
                }
            }
        }
    }
}
