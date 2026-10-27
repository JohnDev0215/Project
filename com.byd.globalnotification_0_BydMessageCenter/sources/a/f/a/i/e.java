package a.f.a.i;

import a.b.k.l;
import a.f.a.i.d;
import a.f.a.i.l.b;
import a.f.a.i.l.o;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class e extends k {
    public int C0;
    public int D0;
    public int y0;
    public a.f.a.i.l.b w0 = new a.f.a.i.l.b(this);
    public a.f.a.i.l.e x0 = new a.f.a.i.l.e(this);
    public b.InterfaceC0007b z0 = null;
    public boolean A0 = false;
    public a.f.a.d B0 = new a.f.a.d();
    public int E0 = 0;
    public int F0 = 0;
    public b[] G0 = new b[4];
    public b[] H0 = new b[4];
    public int I0 = 257;
    public boolean J0 = false;
    public boolean K0 = false;
    public WeakReference<c> L0 = null;
    public WeakReference<c> M0 = null;
    public WeakReference<c> N0 = null;
    public WeakReference<c> O0 = null;
    public HashSet<d> P0 = new HashSet<>();
    public b.a Q0 = new b.a();

    public static boolean e0(int i, d dVar, b.InterfaceC0007b interfaceC0007b, b.a aVar, int i2) {
        int i3;
        int i4;
        d.a aVar2 = d.a.MATCH_CONSTRAINT;
        d.a aVar3 = d.a.WRAP_CONTENT;
        d.a aVar4 = d.a.FIXED;
        if (interfaceC0007b == null) {
            return false;
        }
        if (dVar.k0 == 8 || (dVar instanceof f) || (dVar instanceof a)) {
            aVar.f574e = 0;
            aVar.f575f = 0;
            return false;
        }
        aVar.f570a = dVar.m();
        aVar.f571b = dVar.t();
        aVar.f572c = dVar.u();
        aVar.f573d = dVar.l();
        aVar.i = false;
        aVar.j = i2;
        boolean z = aVar.f570a == aVar2;
        boolean z2 = aVar.f571b == aVar2;
        boolean z3 = z && dVar.a0 > 0.0f;
        boolean z4 = z2 && dVar.a0 > 0.0f;
        if (z && dVar.x(0) && dVar.t == 0 && !z3) {
            aVar.f570a = aVar3;
            if (z2 && dVar.u == 0) {
                aVar.f570a = aVar4;
            }
            z = false;
        }
        if (z2 && dVar.x(1) && dVar.u == 0 && !z4) {
            aVar.f571b = aVar3;
            if (z && dVar.t == 0) {
                aVar.f571b = aVar4;
            }
            z2 = false;
        }
        if (dVar.D()) {
            aVar.f570a = aVar4;
            z = false;
        }
        if (dVar.E()) {
            aVar.f571b = aVar4;
            z2 = false;
        }
        if (z3) {
            if (dVar.v[0] == 4) {
                aVar.f570a = aVar4;
            } else if (!z2) {
                if (aVar.f571b == aVar4) {
                    i4 = aVar.f573d;
                } else {
                    aVar.f570a = aVar3;
                    ((ConstraintLayout.b) interfaceC0007b).b(dVar, aVar);
                    i4 = aVar.f575f;
                }
                aVar.f570a = aVar4;
                aVar.f572c = (int) (dVar.a0 * i4);
            }
        }
        if (z4) {
            if (dVar.v[1] == 4) {
                aVar.f571b = aVar4;
            } else if (!z) {
                if (aVar.f570a == aVar4) {
                    i3 = aVar.f572c;
                } else {
                    aVar.f571b = aVar3;
                    ((ConstraintLayout.b) interfaceC0007b).b(dVar, aVar);
                    i3 = aVar.f574e;
                }
                aVar.f571b = aVar4;
                aVar.f573d = dVar.b0 == -1 ? (int) (i3 / dVar.a0) : (int) (dVar.a0 * i3);
            }
        }
        ((ConstraintLayout.b) interfaceC0007b).b(dVar, aVar);
        dVar.S(aVar.f574e);
        dVar.N(aVar.f575f);
        dVar.G = aVar.h;
        dVar.K(aVar.g);
        aVar.j = 0;
        return aVar.i;
    }

    @Override // a.f.a.i.k, a.f.a.i.d
    public void F() {
        this.B0.u();
        this.C0 = 0;
        this.D0 = 0;
        super.F();
    }

    @Override // a.f.a.i.d
    public void T(boolean z, boolean z2) {
        super.T(z, z2);
        int size = this.v0.size();
        for (int i = 0; i < size; i++) {
            this.v0.get(i).T(z, z2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:334:0x05ae  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x05e4  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x0610  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x0626  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x0655  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x0660  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x066c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:391:0x0679  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x0682  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x068d  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x06aa  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x07bd  */
    /* JADX WARN: Removed duplicated region for block: B:462:0x0804  */
    /* JADX WARN: Removed duplicated region for block: B:483:0x088d  */
    /* JADX WARN: Removed duplicated region for block: B:486:0x089f  */
    /* JADX WARN: Removed duplicated region for block: B:489:0x08ba  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:490:0x08c6  */
    /* JADX WARN: Removed duplicated region for block: B:492:0x08c9  */
    /* JADX WARN: Removed duplicated region for block: B:504:0x0900 A[PHI: r14 r19
  0x0900: PHI (r14v7 ??) = (r14v6 ??), (r14v9 ??), (r14v9 ??), (r14v9 ??) binds: [B:491:0x08c7, B:499:0x08e8, B:500:0x08ea, B:502:0x08f0] A[DONT_GENERATE, DONT_INLINE]
  0x0900: PHI (r19v9 boolean) = (r19v8 boolean), (r19v10 boolean), (r19v10 boolean), (r19v10 boolean) binds: [B:491:0x08c7, B:499:0x08e8, B:500:0x08ea, B:502:0x08f0] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:506:0x0906  */
    /* JADX WARN: Removed duplicated region for block: B:507:0x0908  */
    /* JADX WARN: Removed duplicated region for block: B:511:0x0916  */
    /* JADX WARN: Type inference failed for: r13v0 */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v20 */
    /* JADX WARN: Type inference failed for: r13v21 */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r13v57 */
    /* JADX WARN: Type inference failed for: r13v58 */
    /* JADX WARN: Type inference failed for: r13v59 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v11 */
    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v46 */
    /* JADX WARN: Type inference failed for: r14v47 */
    /* JADX WARN: Type inference failed for: r14v48 */
    /* JADX WARN: Type inference failed for: r14v49 */
    /* JADX WARN: Type inference failed for: r14v50 */
    /* JADX WARN: Type inference failed for: r14v51 */
    /* JADX WARN: Type inference failed for: r14v6 */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r14v9 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14, types: [boolean] */
    /* JADX WARN: Type inference failed for: r3v16 */
    @Override // a.f.a.i.k
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void V() {
        /*
            Method dump skipped, instruction units count: 2342
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.f.a.i.e.V():void");
    }

    public void W(d dVar, int i) {
        if (i == 0) {
            int i2 = this.E0 + 1;
            b[] bVarArr = this.H0;
            if (i2 >= bVarArr.length) {
                this.H0 = (b[]) Arrays.copyOf(bVarArr, bVarArr.length * 2);
            }
            this.H0[this.E0] = new b(dVar, 0, this.A0);
            this.E0++;
            return;
        }
        if (i == 1) {
            int i3 = this.F0 + 1;
            b[] bVarArr2 = this.G0;
            if (i3 >= bVarArr2.length) {
                this.G0 = (b[]) Arrays.copyOf(bVarArr2, bVarArr2.length * 2);
            }
            this.G0[this.F0] = new b(dVar, 1, this.A0);
            this.F0++;
        }
    }

    public boolean X(a.f.a.d dVar) {
        boolean z;
        d.a aVar = d.a.FIXED;
        d.a aVar2 = d.a.WRAP_CONTENT;
        boolean zF0 = f0(64);
        d(dVar, zF0);
        int size = this.v0.size();
        boolean z2 = false;
        for (int i = 0; i < size; i++) {
            d dVar2 = this.v0.get(i);
            boolean[] zArr = dVar2.V;
            zArr[0] = false;
            zArr[1] = false;
            if (dVar2 instanceof a) {
                z2 = true;
            }
        }
        if (z2) {
            for (int i2 = 0; i2 < size; i2++) {
                d dVar3 = this.v0.get(i2);
                if (dVar3 instanceof a) {
                    a aVar3 = (a) dVar3;
                    for (int i3 = 0; i3 < aVar3.w0; i3++) {
                        d dVar4 = aVar3.v0[i3];
                        if (aVar3.y0 || dVar4.e()) {
                            int i4 = aVar3.x0;
                            if (i4 == 0 || i4 == 1) {
                                dVar4.V[0] = true;
                            } else if (i4 == 2 || i4 == 3) {
                                dVar4.V[1] = true;
                            }
                        }
                    }
                }
            }
        }
        this.P0.clear();
        for (int i5 = 0; i5 < size; i5++) {
            d dVar5 = this.v0.get(i5);
            if (dVar5.c()) {
                if (dVar5 instanceof j) {
                    this.P0.add(dVar5);
                } else {
                    dVar5.d(dVar, zF0);
                }
            }
        }
        while (this.P0.size() > 0) {
            int size2 = this.P0.size();
            Iterator<d> it = this.P0.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                j jVar = (j) it.next();
                HashSet<d> hashSet = this.P0;
                int i6 = 0;
                while (true) {
                    if (i6 >= jVar.w0) {
                        z = false;
                        break;
                    }
                    if (hashSet.contains(jVar.v0[i6])) {
                        z = true;
                        break;
                    }
                    i6++;
                }
                if (z) {
                    jVar.d(dVar, zF0);
                    this.P0.remove(jVar);
                    break;
                }
            }
            if (size2 == this.P0.size()) {
                Iterator<d> it2 = this.P0.iterator();
                while (it2.hasNext()) {
                    it2.next().d(dVar, zF0);
                }
                this.P0.clear();
            }
        }
        if (a.f.a.d.r) {
            HashSet<d> hashSet2 = new HashSet<>();
            for (int i7 = 0; i7 < size; i7++) {
                d dVar6 = this.v0.get(i7);
                if (!dVar6.c()) {
                    hashSet2.add(dVar6);
                }
            }
            b(this, dVar, hashSet2, m() == aVar2 ? 0 : 1, false);
            for (d dVar7 : hashSet2) {
                i.a(this, dVar, dVar7);
                dVar7.d(dVar, zF0);
            }
        } else {
            for (int i8 = 0; i8 < size; i8++) {
                d dVar8 = this.v0.get(i8);
                if (dVar8 instanceof e) {
                    d.a[] aVarArr = dVar8.W;
                    d.a aVar4 = aVarArr[0];
                    d.a aVar5 = aVarArr[1];
                    if (aVar4 == aVar2) {
                        aVarArr[0] = aVar;
                    }
                    if (aVar5 == aVar2) {
                        dVar8.W[1] = aVar;
                    }
                    dVar8.d(dVar, zF0);
                    if (aVar4 == aVar2) {
                        dVar8.O(aVar4);
                    }
                    if (aVar5 == aVar2) {
                        dVar8.R(aVar5);
                    }
                } else {
                    i.a(this, dVar, dVar8);
                    if (!dVar8.c()) {
                        dVar8.d(dVar, zF0);
                    }
                }
            }
        }
        if (this.E0 > 0) {
            l.i.b(this, dVar, null, 0);
        }
        if (this.F0 > 0) {
            l.i.b(this, dVar, null, 1);
        }
        return true;
    }

    public void Y(c cVar) {
        WeakReference<c> weakReference = this.O0;
        if (weakReference == null || weakReference.get() == null || cVar.c() > this.O0.get().c()) {
            this.O0 = new WeakReference<>(cVar);
        }
    }

    public void Z(c cVar) {
        WeakReference<c> weakReference = this.M0;
        if (weakReference == null || weakReference.get() == null || cVar.c() > this.M0.get().c()) {
            this.M0 = new WeakReference<>(cVar);
        }
    }

    public void a0(c cVar) {
        WeakReference<c> weakReference = this.N0;
        if (weakReference == null || weakReference.get() == null || cVar.c() > this.N0.get().c()) {
            this.N0 = new WeakReference<>(cVar);
        }
    }

    public void b0(c cVar) {
        WeakReference<c> weakReference = this.L0;
        if (weakReference == null || weakReference.get() == null || cVar.c() > this.L0.get().c()) {
            this.L0 = new WeakReference<>(cVar);
        }
    }

    public boolean c0(boolean z, int i) {
        a.f.a.i.l.g gVar;
        int i2;
        boolean z2;
        a.f.a.i.l.g gVar2;
        int iL;
        a.f.a.i.l.e eVar = this.x0;
        d.a aVar = d.a.MATCH_PARENT;
        d.a aVar2 = d.a.WRAP_CONTENT;
        d.a aVar3 = d.a.FIXED;
        boolean z3 = true;
        boolean z4 = z & true;
        d.a aVarK = eVar.f576a.k(0);
        d.a aVarK2 = eVar.f576a.k(1);
        int iV = eVar.f576a.v();
        int iW = eVar.f576a.w();
        if (z4 && (aVarK == aVar2 || aVarK2 == aVar2)) {
            Iterator<o> it = eVar.f580e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                o next = it.next();
                if (next.f611f == i && !next.k()) {
                    z4 = false;
                    break;
                }
            }
            if (i == 0) {
                if (z4 && aVarK == aVar2) {
                    e eVar2 = eVar.f576a;
                    eVar2.W[0] = aVar3;
                    eVar2.S(eVar.d(eVar2, 0));
                    e eVar3 = eVar.f576a;
                    gVar2 = eVar3.f558d.f610e;
                    iL = eVar3.u();
                    gVar2.c(iL);
                }
            } else if (z4 && aVarK2 == aVar2) {
                e eVar4 = eVar.f576a;
                eVar4.W[1] = aVar3;
                eVar4.N(eVar.d(eVar4, 1));
                e eVar5 = eVar.f576a;
                gVar2 = eVar5.f559e.f610e;
                iL = eVar5.l();
                gVar2.c(iL);
            }
        }
        d.a[] aVarArr = eVar.f576a.W;
        if (i == 0) {
            if (aVarArr[0] == aVar3 || aVarArr[0] == aVar) {
                int iU = eVar.f576a.u() + iV;
                eVar.f576a.f558d.i.c(iU);
                gVar = eVar.f576a.f558d.f610e;
                i2 = iU - iV;
                gVar.c(i2);
                z2 = true;
            }
            z2 = false;
        } else {
            if (aVarArr[1] == aVar3 || aVarArr[1] == aVar) {
                int iL2 = eVar.f576a.l() + iW;
                eVar.f576a.f559e.i.c(iL2);
                gVar = eVar.f576a.f559e.f610e;
                i2 = iL2 - iW;
                gVar.c(i2);
                z2 = true;
            }
            z2 = false;
        }
        eVar.g();
        for (o oVar : eVar.f580e) {
            if (oVar.f611f == i && (oVar.f607b != eVar.f576a || oVar.g)) {
                oVar.e();
            }
        }
        for (o oVar2 : eVar.f580e) {
            if (oVar2.f611f == i && (z2 || oVar2.f607b != eVar.f576a)) {
                if (!oVar2.h.j || !oVar2.i.j || (!(oVar2 instanceof a.f.a.i.l.c) && !oVar2.f610e.j)) {
                    z3 = false;
                    break;
                }
            }
        }
        eVar.f576a.O(aVarK);
        eVar.f576a.R(aVarK2);
        return z3;
    }

    public void d0() {
        this.x0.f577b = true;
    }

    public boolean f0(int i) {
        return (this.I0 & i) == i;
    }

    public void g0(int i) {
        this.I0 = i;
        a.f.a.d.r = f0(512);
    }

    @Override // a.f.a.i.d
    public void q(StringBuilder sb) {
        sb.append(this.l + ":{\n");
        sb.append("  actualWidth:" + this.Y);
        sb.append("\n");
        sb.append("  actualHeight:" + this.Z);
        sb.append("\n");
        Iterator<d> it = this.v0.iterator();
        while (it.hasNext()) {
            it.next().q(sb);
            sb.append(",\n");
        }
        sb.append("}");
    }
}
