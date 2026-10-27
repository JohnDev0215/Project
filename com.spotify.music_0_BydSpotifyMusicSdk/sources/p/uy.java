package p;

import androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class uy extends hw2 {
    public pq[] A0;
    public pq[] B0;
    public int C0;
    public boolean D0;
    public boolean E0;
    public WeakReference F0;
    public WeakReference G0;
    public WeakReference H0;
    public WeakReference I0;
    public final HashSet J0;
    public final ml K0;
    public final v4 q0;
    public final h90 r0;
    public int s0;
    public ey t0;
    public boolean u0;
    public final ra1 v0;
    public int w0;
    public int x0;
    public int y0;
    public int z0;

    public uy() {
        this.p0 = new ArrayList();
        this.q0 = new v4(this);
        h90 h90Var = new h90();
        h90Var.b = true;
        h90Var.c = true;
        h90Var.e = new ArrayList();
        new ArrayList();
        h90Var.f = null;
        h90Var.g = new ml();
        h90Var.h = new ArrayList();
        h90Var.a = this;
        h90Var.d = this;
        this.r0 = h90Var;
        this.t0 = null;
        this.u0 = false;
        this.v0 = new ra1();
        this.y0 = 0;
        this.z0 = 0;
        this.A0 = new pq[4];
        this.B0 = new pq[4];
        this.C0 = 257;
        this.D0 = false;
        this.E0 = false;
        this.F0 = null;
        this.G0 = null;
        this.H0 = null;
        this.I0 = null;
        this.J0 = new HashSet();
        this.K0 = new ml();
    }

    public static void R(ty tyVar, ey eyVar, ml mlVar) {
        int i;
        int i2;
        if (eyVar == null) {
            return;
        }
        if (tyVar.g0 == 8 || (tyVar instanceof d11) || (tyVar instanceof al)) {
            mlVar.e = 0;
            mlVar.f = 0;
            return;
        }
        ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = tyVar.S;
        mlVar.a = constraintWidget$DimensionBehaviourArr[0];
        mlVar.b = constraintWidget$DimensionBehaviourArr[1];
        mlVar.c = tyVar.o();
        mlVar.d = tyVar.i();
        mlVar.i = false;
        mlVar.j = 0;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = mlVar.a;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = ConstraintWidget$DimensionBehaviour.k;
        boolean z = constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour2;
        boolean z2 = mlVar.b == constraintWidget$DimensionBehaviour2;
        boolean z3 = z && tyVar.W > 0.0f;
        boolean z4 = z2 && tyVar.W > 0.0f;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = ConstraintWidget$DimensionBehaviour.e;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = ConstraintWidget$DimensionBehaviour.c;
        if (z && tyVar.r(0) && tyVar.r == 0 && !z3) {
            mlVar.a = constraintWidget$DimensionBehaviour3;
            if (z2 && tyVar.s == 0) {
                mlVar.a = constraintWidget$DimensionBehaviour4;
            }
            z = false;
        }
        if (z2 && tyVar.r(1) && tyVar.s == 0 && !z4) {
            mlVar.b = constraintWidget$DimensionBehaviour3;
            if (z && tyVar.r == 0) {
                mlVar.b = constraintWidget$DimensionBehaviour4;
            }
            z2 = false;
        }
        if (tyVar.y()) {
            mlVar.a = constraintWidget$DimensionBehaviour4;
            z = false;
        }
        if (tyVar.z()) {
            mlVar.b = constraintWidget$DimensionBehaviour4;
            z2 = false;
        }
        int[] iArr = tyVar.t;
        if (z3) {
            if (iArr[0] == 4) {
                mlVar.a = constraintWidget$DimensionBehaviour4;
            } else if (!z2) {
                if (mlVar.b == constraintWidget$DimensionBehaviour4) {
                    i2 = mlVar.d;
                } else {
                    mlVar.a = constraintWidget$DimensionBehaviour3;
                    eyVar.b(tyVar, mlVar);
                    i2 = mlVar.f;
                }
                mlVar.a = constraintWidget$DimensionBehaviour4;
                mlVar.c = (int) (tyVar.W * i2);
            }
        }
        if (z4) {
            if (iArr[1] == 4) {
                mlVar.b = constraintWidget$DimensionBehaviour4;
            } else if (!z) {
                if (mlVar.a == constraintWidget$DimensionBehaviour4) {
                    i = mlVar.c;
                } else {
                    mlVar.b = constraintWidget$DimensionBehaviour3;
                    eyVar.b(tyVar, mlVar);
                    i = mlVar.e;
                }
                mlVar.b = constraintWidget$DimensionBehaviour4;
                if (tyVar.X == -1) {
                    mlVar.d = (int) (i / tyVar.W);
                } else {
                    mlVar.d = (int) (tyVar.W * i);
                }
            }
        }
        eyVar.b(tyVar, mlVar);
        tyVar.K(mlVar.e);
        tyVar.H(mlVar.f);
        tyVar.E = mlVar.h;
        int i3 = mlVar.g;
        tyVar.a0 = i3;
        tyVar.E = i3 > 0;
        mlVar.j = 0;
    }

    @Override // p.hw2, p.ty
    public final void A() {
        this.v0.t();
        this.w0 = 0;
        this.x0 = 0;
        super.A();
    }

    @Override // p.ty
    public final void L(boolean z, boolean z2) {
        super.L(z, z2);
        int size = this.p0.size();
        for (int i = 0; i < size; i++) {
            ((ty) this.p0.get(i)).L(z, z2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:341:0x05f5  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x061f  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x0650  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x0666  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x0752  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x07da  */
    /* JADX WARN: Removed duplicated region for block: B:465:0x07de A[PHI: r24 r25 r26 r27 r28
  0x07de: PHI (r24v3 p.xx) = (r24v5 p.xx), (r24v8 p.xx) binds: [B:471:0x080c, B:464:0x07db] A[DONT_GENERATE, DONT_INLINE]
  0x07de: PHI (r25v7 boolean) = (r25v22 boolean), (r25v29 boolean) binds: [B:471:0x080c, B:464:0x07db] A[DONT_GENERATE, DONT_INLINE]
  0x07de: PHI (r26v1 p.xx) = (r26v3 p.xx), (r26v11 p.xx) binds: [B:471:0x080c, B:464:0x07db] A[DONT_GENERATE, DONT_INLINE]
  0x07de: PHI (r27v2 java.util.ArrayList) = (r27v4 java.util.ArrayList), (r27v14 java.util.ArrayList) binds: [B:471:0x080c, B:464:0x07db] A[DONT_GENERATE, DONT_INLINE]
  0x07de: PHI (r28v2 int) = (r28v4 int), (r28v14 int) binds: [B:471:0x080c, B:464:0x07db] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:475:0x0825  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:482:0x0847  */
    /* JADX WARN: Removed duplicated region for block: B:488:0x0862 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:493:0x086e A[LOOP:13: B:492:0x086c->B:493:0x086e, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:496:0x08a1  */
    /* JADX WARN: Removed duplicated region for block: B:505:0x08d3  */
    /* JADX WARN: Removed duplicated region for block: B:508:0x08ed  */
    /* JADX WARN: Removed duplicated region for block: B:509:0x08f8  */
    /* JADX WARN: Removed duplicated region for block: B:511:0x08fb  */
    /* JADX WARN: Removed duplicated region for block: B:524:0x0933 A[PHI: r19 r25
  0x0933: PHI (r19v10 ??) = (r19v9 ??), (r19v13 ??), (r19v13 ??) binds: [B:510:0x08f9, B:518:0x0915, B:519:0x0917] A[DONT_GENERATE, DONT_INLINE]
  0x0933: PHI (r25v12 boolean) = (r25v11 boolean), (r25v15 boolean), (r25v15 boolean) binds: [B:510:0x08f9, B:518:0x0915, B:519:0x0917] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:526:0x0938  */
    /* JADX WARN: Removed duplicated region for block: B:527:0x093a  */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v39 */
    /* JADX WARN: Type inference failed for: r15v5 */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r15v7 */
    /* JADX WARN: Type inference failed for: r19v10 */
    /* JADX WARN: Type inference failed for: r19v11 */
    /* JADX WARN: Type inference failed for: r19v12 */
    /* JADX WARN: Type inference failed for: r19v13 */
    /* JADX WARN: Type inference failed for: r19v14 */
    /* JADX WARN: Type inference failed for: r19v15 */
    /* JADX WARN: Type inference failed for: r19v16 */
    /* JADX WARN: Type inference failed for: r19v28 */
    /* JADX WARN: Type inference failed for: r19v29 */
    /* JADX WARN: Type inference failed for: r19v30 */
    /* JADX WARN: Type inference failed for: r19v31 */
    /* JADX WARN: Type inference failed for: r19v32 */
    /* JADX WARN: Type inference failed for: r19v33 */
    /* JADX WARN: Type inference failed for: r19v34 */
    /* JADX WARN: Type inference failed for: r19v35 */
    /* JADX WARN: Type inference failed for: r19v9 */
    /* JADX WARN: Type inference failed for: r23v10 */
    /* JADX WARN: Type inference failed for: r23v2 */
    /* JADX WARN: Type inference failed for: r23v3 */
    /* JADX WARN: Type inference failed for: r23v4 */
    /* JADX WARN: Type inference failed for: r23v5 */
    /* JADX WARN: Type inference failed for: r23v6 */
    /* JADX WARN: Type inference failed for: r33v0, types: [p.hw2, p.ty, p.uy] */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v18 */
    @Override // p.hw2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void N() {
        /*
            Method dump skipped, instruction units count: 2393
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.uy.N():void");
    }

    public final void O(int i, ty tyVar) {
        if (i == 0) {
            int i2 = this.y0 + 1;
            pq[] pqVarArr = this.B0;
            if (i2 >= pqVarArr.length) {
                this.B0 = (pq[]) Arrays.copyOf(pqVarArr, pqVarArr.length * 2);
            }
            pq[] pqVarArr2 = this.B0;
            int i3 = this.y0;
            pqVarArr2[i3] = new pq(tyVar, 0, this.u0);
            this.y0 = i3 + 1;
            return;
        }
        if (i == 1) {
            int i4 = this.z0 + 1;
            pq[] pqVarArr3 = this.A0;
            if (i4 >= pqVarArr3.length) {
                this.A0 = (pq[]) Arrays.copyOf(pqVarArr3, pqVarArr3.length * 2);
            }
            pq[] pqVarArr4 = this.A0;
            int i5 = this.z0;
            pqVarArr4[i5] = new pq(tyVar, 1, this.u0);
            this.z0 = i5 + 1;
        }
    }

    public final void P(ra1 ra1Var) {
        boolean zS = S(64);
        b(ra1Var, zS);
        int size = this.p0.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ty tyVar = (ty) this.p0.get(i);
            boolean[] zArr = tyVar.R;
            zArr[0] = false;
            zArr[1] = false;
            if (tyVar instanceof al) {
                z = true;
            }
        }
        if (z) {
            for (int i2 = 0; i2 < size; i2++) {
                ty tyVar2 = (ty) this.p0.get(i2);
                if (tyVar2 instanceof al) {
                    al alVar = (al) tyVar2;
                    for (int i3 = 0; i3 < alVar.q0; i3++) {
                        ty tyVar3 = alVar.p0[i3];
                        if (alVar.s0 || tyVar3.c()) {
                            int i4 = alVar.r0;
                            if (i4 == 0 || i4 == 1) {
                                tyVar3.R[0] = true;
                            } else if (i4 == 2 || i4 == 3) {
                                tyVar3.R[1] = true;
                            }
                        }
                    }
                }
            }
        }
        HashSet hashSet = this.J0;
        hashSet.clear();
        for (int i5 = 0; i5 < size; i5++) {
            ty tyVar4 = (ty) this.p0.get(i5);
            tyVar4.getClass();
            if (tyVar4 instanceof d11) {
                tyVar4.b(ra1Var, zS);
            }
        }
        while (hashSet.size() > 0) {
            int size2 = hashSet.size();
            Iterator it = hashSet.iterator();
            if (it.hasNext()) {
                u4.v((ty) it.next());
                throw null;
            }
            if (size2 == hashSet.size()) {
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    ((ty) it2.next()).b(ra1Var, zS);
                }
                hashSet.clear();
            }
        }
        boolean z2 = ra1.f178p;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = ConstraintWidget$DimensionBehaviour.e;
        if (z2) {
            HashSet<ty> hashSet2 = new HashSet();
            for (int i6 = 0; i6 < size; i6++) {
                ty tyVar5 = (ty) this.p0.get(i6);
                tyVar5.getClass();
                if (!(tyVar5 instanceof d11)) {
                    hashSet2.add(tyVar5);
                }
            }
            a(this, ra1Var, hashSet2, this.S[0] == constraintWidget$DimensionBehaviour ? 0 : 1, false);
            for (ty tyVar6 : hashSet2) {
                ln1.c(this, ra1Var, tyVar6);
                tyVar6.b(ra1Var, zS);
            }
        } else {
            for (int i7 = 0; i7 < size; i7++) {
                ty tyVar7 = (ty) this.p0.get(i7);
                if (tyVar7 instanceof uy) {
                    ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = tyVar7.S;
                    ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = constraintWidget$DimensionBehaviourArr[0];
                    ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = constraintWidget$DimensionBehaviourArr[1];
                    ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = ConstraintWidget$DimensionBehaviour.c;
                    if (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour) {
                        tyVar7.I(constraintWidget$DimensionBehaviour4);
                    }
                    if (constraintWidget$DimensionBehaviour3 == constraintWidget$DimensionBehaviour) {
                        tyVar7.J(constraintWidget$DimensionBehaviour4);
                    }
                    tyVar7.b(ra1Var, zS);
                    if (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour) {
                        tyVar7.I(constraintWidget$DimensionBehaviour2);
                    }
                    if (constraintWidget$DimensionBehaviour3 == constraintWidget$DimensionBehaviour) {
                        tyVar7.J(constraintWidget$DimensionBehaviour3);
                    }
                } else {
                    ln1.c(this, ra1Var, tyVar7);
                    if (!(tyVar7 instanceof d11)) {
                        tyVar7.b(ra1Var, zS);
                    }
                }
            }
        }
        if (this.y0 > 0) {
            ci1.f(this, ra1Var, null, 0);
        }
        if (this.z0 > 0) {
            ci1.f(this, ra1Var, null, 1);
        }
    }

    public final boolean Q(int i, boolean z) {
        boolean z2;
        boolean z3;
        boolean z4;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour;
        boolean z5 = z & true;
        h90 h90Var = this.r0;
        uy uyVar = h90Var.a;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviourH = uyVar.h(0);
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviourH2 = uyVar.h(1);
        int iP = uyVar.p();
        int iQ = uyVar.q();
        ArrayList<androidx.constraintlayout.core.widgets.analyzer.f> arrayList = h90Var.e;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = ConstraintWidget$DimensionBehaviour.c;
        if (z5 && (constraintWidget$DimensionBehaviourH == (constraintWidget$DimensionBehaviour = ConstraintWidget$DimensionBehaviour.e) || constraintWidget$DimensionBehaviourH2 == constraintWidget$DimensionBehaviour)) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                androidx.constraintlayout.core.widgets.analyzer.f fVar = (androidx.constraintlayout.core.widgets.analyzer.f) it.next();
                if (fVar.f == i && !fVar.k()) {
                    z5 = false;
                    break;
                }
            }
            if (i == 0) {
                if (z5 && constraintWidget$DimensionBehaviourH == constraintWidget$DimensionBehaviour) {
                    uyVar.I(constraintWidget$DimensionBehaviour2);
                    uyVar.K(h90Var.d(uyVar, 0));
                    uyVar.d.e.d(uyVar.o());
                }
            } else if (z5 && constraintWidget$DimensionBehaviourH2 == constraintWidget$DimensionBehaviour) {
                uyVar.J(constraintWidget$DimensionBehaviour2);
                uyVar.H(h90Var.d(uyVar, 1));
                uyVar.e.e.d(uyVar.i());
            }
        }
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = ConstraintWidget$DimensionBehaviour.l;
        ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = uyVar.S;
        if (i == 0) {
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = constraintWidget$DimensionBehaviourArr[0];
            if (constraintWidget$DimensionBehaviour4 == constraintWidget$DimensionBehaviour2 || constraintWidget$DimensionBehaviour4 == constraintWidget$DimensionBehaviour3) {
                int iO = uyVar.o() + iP;
                uyVar.d.i.d(iO);
                uyVar.d.e.d(iO - iP);
                z3 = true;
                z2 = true;
            } else {
                z2 = true;
                z3 = false;
            }
        } else {
            z2 = true;
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour5 = constraintWidget$DimensionBehaviourArr[1];
            if (constraintWidget$DimensionBehaviour5 == constraintWidget$DimensionBehaviour2 || constraintWidget$DimensionBehaviour5 == constraintWidget$DimensionBehaviour3) {
                int i2 = uyVar.i() + iQ;
                uyVar.e.i.d(i2);
                uyVar.e.e.d(i2 - iQ);
                z3 = true;
            } else {
                z3 = false;
            }
        }
        h90Var.g();
        for (androidx.constraintlayout.core.widgets.analyzer.f fVar2 : arrayList) {
            if (fVar2.f == i && (fVar2.b != uyVar || fVar2.g)) {
                fVar2.e();
            }
        }
        for (androidx.constraintlayout.core.widgets.analyzer.f fVar3 : arrayList) {
            if (fVar3.f == i && (z3 || fVar3.b != uyVar)) {
                if (!fVar3.h.j || !fVar3.i.j || (!(fVar3 instanceof qq) && !fVar3.e.j)) {
                    z4 = false;
                    break;
                }
            }
        }
        z4 = z2;
        uyVar.I(constraintWidget$DimensionBehaviourH);
        uyVar.J(constraintWidget$DimensionBehaviourH2);
        return z4;
    }

    public final boolean S(int i) {
        return (this.C0 & i) == i;
    }

    @Override // p.ty
    public final void l(StringBuilder sb) {
        sb.append(this.j + ":{\n");
        StringBuilder sb2 = new StringBuilder("  actualWidth:");
        sb2.append(this.U);
        sb.append(sb2.toString());
        sb.append("\n");
        sb.append("  actualHeight:" + this.V);
        sb.append("\n");
        Iterator it = this.p0.iterator();
        while (it.hasNext()) {
            ((ty) it.next()).l(sb);
            sb.append(",\n");
        }
        sb.append("}");
    }
}
