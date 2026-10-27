package defpackage;

import androidx.constraintlayout.widget.ConstraintLayout;
import defpackage.ae;
import defpackage.qx;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: ConstraintWidgetContainer.java */
/* JADX INFO: loaded from: classes.dex */
public final class rx extends et3 {
    public int D0;
    public int E0;
    public int z0;
    public final ae x0 = new ae(this);
    public final n80 y0 = new n80(this);
    public ae.b A0 = null;
    public boolean B0 = false;
    public final q71 C0 = new q71();
    public int F0 = 0;
    public int G0 = 0;
    public vm[] H0 = new vm[4];
    public vm[] I0 = new vm[4];
    public int J0 = 257;
    public boolean K0 = false;
    public boolean L0 = false;
    public WeakReference<nx> M0 = null;
    public WeakReference<nx> N0 = null;
    public WeakReference<nx> O0 = null;
    public WeakReference<nx> P0 = null;
    public final HashSet<qx> Q0 = new HashSet<>();
    public final ae.a R0 = new ae.a();

    public static void Y(qx qxVar, ae.b bVar, ae.a aVar) {
        int i;
        int i2;
        if (bVar == null) {
            return;
        }
        if (qxVar.j0 == 8 || (qxVar instanceof rt0) || (qxVar instanceof yc)) {
            aVar.e = 0;
            aVar.f = 0;
            return;
        }
        qx.a[] aVarArr = qxVar.V;
        aVar.a = aVarArr[0];
        aVar.b = aVarArr[1];
        aVar.c = qxVar.u();
        aVar.d = qxVar.o();
        aVar.i = false;
        aVar.j = 0;
        qx.a aVar2 = aVar.a;
        qx.a aVar3 = qx.a.MATCH_CONSTRAINT;
        boolean z = aVar2 == aVar3;
        boolean z2 = aVar.b == aVar3;
        boolean z3 = z && qxVar.Z > 0.0f;
        boolean z4 = z2 && qxVar.Z > 0.0f;
        qx.a aVar4 = qx.a.WRAP_CONTENT;
        qx.a aVar5 = qx.a.FIXED;
        if (z && qxVar.x(0) && qxVar.s == 0 && !z3) {
            aVar.a = aVar4;
            if (z2 && qxVar.t == 0) {
                aVar.a = aVar5;
            }
            z = false;
        }
        if (z2 && qxVar.x(1) && qxVar.t == 0 && !z4) {
            aVar.b = aVar4;
            if (z && qxVar.s == 0) {
                aVar.b = aVar5;
            }
            z2 = false;
        }
        if (qxVar.E()) {
            aVar.a = aVar5;
            z = false;
        }
        if (qxVar.F()) {
            aVar.b = aVar5;
            z2 = false;
        }
        int[] iArr = qxVar.u;
        if (z3) {
            if (iArr[0] == 4) {
                aVar.a = aVar5;
            } else if (!z2) {
                if (aVar.b == aVar5) {
                    i2 = aVar.d;
                } else {
                    aVar.a = aVar4;
                    ((ConstraintLayout.a) bVar).b(qxVar, aVar);
                    i2 = aVar.f;
                }
                aVar.a = aVar5;
                aVar.c = (int) (qxVar.Z * i2);
            }
        }
        if (z4) {
            if (iArr[1] == 4) {
                aVar.b = aVar5;
            } else if (!z) {
                if (aVar.a == aVar5) {
                    i = aVar.c;
                } else {
                    aVar.b = aVar4;
                    ((ConstraintLayout.a) bVar).b(qxVar, aVar);
                    i = aVar.e;
                }
                aVar.b = aVar5;
                if (qxVar.a0 == -1) {
                    aVar.d = (int) (i / qxVar.Z);
                } else {
                    aVar.d = (int) (qxVar.Z * i);
                }
            }
        }
        ((ConstraintLayout.a) bVar).b(qxVar, aVar);
        qxVar.R(aVar.e);
        qxVar.O(aVar.f);
        qxVar.F = aVar.h;
        int i3 = aVar.g;
        qxVar.d0 = i3;
        qxVar.F = i3 > 0;
        aVar.j = 0;
    }

    @Override // defpackage.et3, defpackage.qx
    public final void G() {
        this.C0.t();
        this.D0 = 0;
        this.E0 = 0;
        super.G();
    }

    @Override // defpackage.qx
    public final void S(boolean z, boolean z2) {
        super.S(z, z2);
        int size = this.w0.size();
        for (int i = 0; i < size; i++) {
            this.w0.get(i).S(z, z2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:351:0x0603  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x062d  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x0638  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x0659  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x066f  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x0676  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x06a7  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x06b2  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x06bd A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:404:0x06c3  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x06cc  */
    /* JADX WARN: Removed duplicated region for block: B:411:0x06d3  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x06dd  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x06fa  */
    /* JADX WARN: Removed duplicated region for block: B:443:0x075a  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:490:0x0816  */
    /* JADX WARN: Removed duplicated region for block: B:503:0x085d  */
    /* JADX WARN: Removed duplicated region for block: B:509:0x087a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:514:0x0886 A[LOOP:13: B:513:0x0884->B:514:0x0886, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:517:0x08b9  */
    /* JADX WARN: Removed duplicated region for block: B:526:0x08ef  */
    /* JADX WARN: Removed duplicated region for block: B:529:0x090b  */
    /* JADX WARN: Removed duplicated region for block: B:530:0x0918  */
    /* JADX WARN: Removed duplicated region for block: B:532:0x091b  */
    /* JADX WARN: Removed duplicated region for block: B:544:0x0956 A[PHI: r18 r23
  0x0956: PHI (r18v7 ??) = (r18v6 ??), (r18v10 ??), (r18v10 ??) binds: [B:531:0x0919, B:539:0x093b, B:540:0x093d] A[DONT_GENERATE, DONT_INLINE]
  0x0956: PHI (r23v11 boolean) = (r23v10 boolean), (r23v14 boolean), (r23v14 boolean) binds: [B:531:0x0919, B:539:0x093b, B:540:0x093d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:547:0x095f  */
    /* JADX WARN: Removed duplicated region for block: B:548:0x0961  */
    /* JADX WARN: Removed duplicated region for block: B:552:0x0974  */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v38 */
    /* JADX WARN: Type inference failed for: r15v4 */
    /* JADX WARN: Type inference failed for: r18v10 */
    /* JADX WARN: Type inference failed for: r18v11 */
    /* JADX WARN: Type inference failed for: r18v12 */
    /* JADX WARN: Type inference failed for: r18v13 */
    /* JADX WARN: Type inference failed for: r18v33 */
    /* JADX WARN: Type inference failed for: r18v34 */
    /* JADX WARN: Type inference failed for: r18v35 */
    /* JADX WARN: Type inference failed for: r18v36 */
    /* JADX WARN: Type inference failed for: r18v37 */
    /* JADX WARN: Type inference failed for: r18v38 */
    /* JADX WARN: Type inference failed for: r18v39 */
    /* JADX WARN: Type inference failed for: r18v40 */
    /* JADX WARN: Type inference failed for: r18v6 */
    /* JADX WARN: Type inference failed for: r18v7 */
    /* JADX WARN: Type inference failed for: r18v8 */
    /* JADX WARN: Type inference failed for: r18v9 */
    /* JADX WARN: Type inference failed for: r21v12 */
    /* JADX WARN: Type inference failed for: r21v13 */
    /* JADX WARN: Type inference failed for: r21v2 */
    /* JADX WARN: Type inference failed for: r21v3 */
    /* JADX WARN: Type inference failed for: r21v4 */
    /* JADX WARN: Type inference failed for: r21v5 */
    /* JADX WARN: Type inference failed for: r21v6 */
    /* JADX WARN: Type inference failed for: r21v7 */
    /* JADX WARN: Type inference failed for: r21v8 */
    /* JADX WARN: Type inference failed for: r32v0, types: [et3, qx, rx] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v14 */
    @Override // defpackage.et3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void U() {
        /*
            Method dump skipped, instruction units count: 2434
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.rx.U():void");
    }

    public final void V(int i, qx qxVar) {
        if (i == 0) {
            int i2 = this.F0 + 1;
            vm[] vmVarArr = this.I0;
            if (i2 >= vmVarArr.length) {
                this.I0 = (vm[]) Arrays.copyOf(vmVarArr, vmVarArr.length * 2);
            }
            vm[] vmVarArr2 = this.I0;
            int i3 = this.F0;
            vmVarArr2[i3] = new vm(qxVar, 0, this.B0);
            this.F0 = i3 + 1;
            return;
        }
        if (i == 1) {
            int i4 = this.G0 + 1;
            vm[] vmVarArr3 = this.H0;
            if (i4 >= vmVarArr3.length) {
                this.H0 = (vm[]) Arrays.copyOf(vmVarArr3, vmVarArr3.length * 2);
            }
            vm[] vmVarArr4 = this.H0;
            int i5 = this.G0;
            vmVarArr4[i5] = new vm(qxVar, 1, this.B0);
            this.G0 = i5 + 1;
        }
    }

    public final void W(q71 q71Var) {
        boolean z;
        boolean Z = Z(64);
        e(q71Var, Z);
        int size = this.w0.size();
        boolean z2 = false;
        for (int i = 0; i < size; i++) {
            qx qxVar = this.w0.get(i);
            boolean[] zArr = qxVar.U;
            zArr[0] = false;
            zArr[1] = false;
            if (qxVar instanceof yc) {
                z2 = true;
            }
        }
        if (z2) {
            for (int i2 = 0; i2 < size; i2++) {
                qx qxVar2 = this.w0.get(i2);
                if (qxVar2 instanceof yc) {
                    yc ycVar = (yc) qxVar2;
                    for (int i3 = 0; i3 < ycVar.x0; i3++) {
                        qx qxVar3 = ycVar.w0[i3];
                        if (ycVar.z0 || qxVar3.f()) {
                            int i4 = ycVar.y0;
                            if (i4 == 0 || i4 == 1) {
                                qxVar3.U[0] = true;
                            } else if (i4 == 2 || i4 == 3) {
                                qxVar3.U[1] = true;
                            }
                        }
                    }
                }
            }
        }
        HashSet<qx> hashSet = this.Q0;
        hashSet.clear();
        for (int i5 = 0; i5 < size; i5++) {
            qx qxVar4 = this.w0.get(i5);
            qxVar4.getClass();
            if ((qxVar4 instanceof lo3) || (qxVar4 instanceof rt0)) {
                if (qxVar4 instanceof lo3) {
                    hashSet.add(qxVar4);
                } else {
                    qxVar4.e(q71Var, Z);
                }
            }
        }
        while (hashSet.size() > 0) {
            int size2 = hashSet.size();
            Iterator<qx> it = hashSet.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                lo3 lo3Var = (lo3) it.next();
                int i6 = 0;
                while (true) {
                    if (i6 >= lo3Var.x0) {
                        z = false;
                        break;
                    } else {
                        if (hashSet.contains(lo3Var.w0[i6])) {
                            z = true;
                            break;
                        }
                        i6++;
                    }
                }
                if (z) {
                    lo3Var.e(q71Var, Z);
                    hashSet.remove(lo3Var);
                    break;
                }
            }
            if (size2 == hashSet.size()) {
                Iterator<qx> it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    it2.next().e(q71Var, Z);
                }
                hashSet.clear();
            }
        }
        boolean z3 = q71.p;
        qx.a aVar = qx.a.WRAP_CONTENT;
        if (z3) {
            HashSet<qx> hashSet2 = new HashSet<>();
            for (int i7 = 0; i7 < size; i7++) {
                qx qxVar5 = this.w0.get(i7);
                qxVar5.getClass();
                if (!((qxVar5 instanceof lo3) || (qxVar5 instanceof rt0))) {
                    hashSet2.add(qxVar5);
                }
            }
            d(this, q71Var, hashSet2, this.V[0] == aVar ? 0 : 1, false);
            for (qx qxVar6 : hashSet2) {
                xs1.a(this, q71Var, qxVar6);
                qxVar6.e(q71Var, Z);
            }
        } else {
            for (int i8 = 0; i8 < size; i8++) {
                qx qxVar7 = this.w0.get(i8);
                if (qxVar7 instanceof rx) {
                    qx.a[] aVarArr = qxVar7.V;
                    qx.a aVar2 = aVarArr[0];
                    qx.a aVar3 = aVarArr[1];
                    qx.a aVar4 = qx.a.FIXED;
                    if (aVar2 == aVar) {
                        qxVar7.P(aVar4);
                    }
                    if (aVar3 == aVar) {
                        qxVar7.Q(aVar4);
                    }
                    qxVar7.e(q71Var, Z);
                    if (aVar2 == aVar) {
                        qxVar7.P(aVar2);
                    }
                    if (aVar3 == aVar) {
                        qxVar7.Q(aVar3);
                    }
                } else {
                    xs1.a(this, q71Var, qxVar7);
                    if (!((qxVar7 instanceof lo3) || (qxVar7 instanceof rt0))) {
                        qxVar7.e(q71Var, Z);
                    }
                }
            }
        }
        if (this.F0 > 0) {
            um.a(this, q71Var, null, 0);
        }
        if (this.G0 > 0) {
            um.a(this, q71Var, null, 1);
        }
    }

    public final boolean X(int i, boolean z) {
        boolean z2;
        qx.a aVar;
        boolean z3 = true;
        boolean z4 = z & true;
        n80 n80Var = this.y0;
        rx rxVar = n80Var.a;
        qx.a aVarN = rxVar.n(0);
        qx.a aVarN2 = rxVar.n(1);
        int iV = rxVar.v();
        int iW = rxVar.w();
        ArrayList<gt3> arrayList = n80Var.e;
        qx.a aVar2 = qx.a.FIXED;
        if (z4 && (aVarN == (aVar = qx.a.WRAP_CONTENT) || aVarN2 == aVar)) {
            Iterator<gt3> it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                gt3 next = it.next();
                if (next.f == i && !next.k()) {
                    z4 = false;
                    break;
                }
            }
            if (i == 0) {
                if (z4 && aVarN == aVar) {
                    rxVar.P(aVar2);
                    rxVar.R(n80Var.d(rxVar, 0));
                    rxVar.d.e.d(rxVar.u());
                }
            } else if (z4 && aVarN2 == aVar) {
                rxVar.Q(aVar2);
                rxVar.O(n80Var.d(rxVar, 1));
                rxVar.e.e.d(rxVar.o());
            }
        }
        qx.a aVar3 = qx.a.MATCH_PARENT;
        if (i == 0) {
            qx.a aVar4 = rxVar.V[0];
            if (aVar4 == aVar2 || aVar4 == aVar3) {
                int iU = rxVar.u() + iV;
                rxVar.d.i.d(iU);
                rxVar.d.e.d(iU - iV);
                z2 = true;
            }
            z2 = false;
        } else {
            qx.a aVar5 = rxVar.V[1];
            if (aVar5 == aVar2 || aVar5 == aVar3) {
                int iO = rxVar.o() + iW;
                rxVar.e.i.d(iO);
                rxVar.e.e.d(iO - iW);
                z2 = true;
            }
            z2 = false;
        }
        n80Var.g();
        for (gt3 gt3Var : arrayList) {
            if (gt3Var.f == i && (gt3Var.b != rxVar || gt3Var.g)) {
                gt3Var.e();
            }
        }
        for (gt3 gt3Var2 : arrayList) {
            if (gt3Var2.f == i && (z2 || gt3Var2.b != rxVar)) {
                if (!gt3Var2.h.j || !gt3Var2.i.j || (!(gt3Var2 instanceof wm) && !gt3Var2.e.j)) {
                    z3 = false;
                    break;
                }
            }
        }
        rxVar.P(aVarN);
        rxVar.Q(aVarN2);
        return z3;
    }

    public final boolean Z(int i) {
        return (this.J0 & i) == i;
    }

    @Override // defpackage.qx
    public final void r(StringBuilder sb) {
        sb.append(this.k + ":{\n");
        StringBuilder sb2 = new StringBuilder("  actualWidth:");
        sb2.append(this.X);
        sb.append(sb2.toString());
        sb.append("\n");
        sb.append("  actualHeight:" + this.Y);
        sb.append("\n");
        Iterator<qx> it = this.w0.iterator();
        while (it.hasNext()) {
            it.next().r(sb);
            sb.append(",\n");
        }
        sb.append("}");
    }
}
