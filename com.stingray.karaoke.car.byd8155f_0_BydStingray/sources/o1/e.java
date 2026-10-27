package o1;

import h3.y;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import l3.P;
import p1.C2074b;
import p1.C2076d;
import p1.C2078f;
import p1.InterfaceC2075c;
import p1.p;

/* JADX INFO: loaded from: classes.dex */
public final class e extends d {

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public int f20335u0;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public int f20339y0;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public int f20340z0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public ArrayList f20332r0 = new ArrayList();

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public y f20333s0 = new y(this);

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public C2078f f20334t0 = new C2078f(this);

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public InterfaceC2075c f20336v0 = null;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public boolean f20337w0 = false;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public h1.c f20338x0 = new h1.c();

    /* JADX INFO: renamed from: A0, reason: collision with root package name */
    public int f20321A0 = 0;
    public int B0 = 0;

    /* JADX INFO: renamed from: C0, reason: collision with root package name */
    public b[] f20322C0 = new b[4];

    /* JADX INFO: renamed from: D0, reason: collision with root package name */
    public b[] f20323D0 = new b[4];

    /* JADX INFO: renamed from: E0, reason: collision with root package name */
    public int f20324E0 = 257;

    /* JADX INFO: renamed from: F0, reason: collision with root package name */
    public boolean f20325F0 = false;
    public boolean G0 = false;

    /* JADX INFO: renamed from: H0, reason: collision with root package name */
    public WeakReference f20326H0 = null;

    /* JADX INFO: renamed from: I0, reason: collision with root package name */
    public WeakReference f20327I0 = null;

    /* JADX INFO: renamed from: J0, reason: collision with root package name */
    public WeakReference f20328J0 = null;

    /* JADX INFO: renamed from: K0, reason: collision with root package name */
    public WeakReference f20329K0 = null;

    /* JADX INFO: renamed from: L0, reason: collision with root package name */
    public HashSet f20330L0 = new HashSet();

    /* JADX INFO: renamed from: M0, reason: collision with root package name */
    public C2074b f20331M0 = new C2074b();

    public static void b0(d dVar, InterfaceC2075c interfaceC2075c, C2074b c2074b) {
        int i7;
        int i10;
        if (interfaceC2075c == null) {
            return;
        }
        if (dVar.f20295h0 == 8 || (dVar instanceof h) || (dVar instanceof a)) {
            c2074b.f20646e = 0;
            c2074b.f20647f = 0;
            return;
        }
        int[] iArr = dVar.f20311q0;
        c2074b.f20644a = iArr[0];
        c2074b.f20645b = iArr[1];
        c2074b.c = dVar.r();
        c2074b.d = dVar.l();
        c2074b.f20650i = false;
        c2074b.f20651j = 0;
        boolean z6 = c2074b.f20644a == 3;
        boolean z10 = c2074b.f20645b == 3;
        boolean z11 = z6 && dVar.f20279X > 0.0f;
        boolean z12 = z10 && dVar.f20279X > 0.0f;
        if (z6 && dVar.u(0) && dVar.f20313s == 0 && !z11) {
            c2074b.f20644a = 2;
            if (z10 && dVar.f20314t == 0) {
                c2074b.f20644a = 1;
            }
            z6 = false;
        }
        if (z10 && dVar.u(1) && dVar.f20314t == 0 && !z12) {
            c2074b.f20645b = 2;
            if (z6 && dVar.f20313s == 0) {
                c2074b.f20645b = 1;
            }
            z10 = false;
        }
        if (dVar.B()) {
            c2074b.f20644a = 1;
            z6 = false;
        }
        if (dVar.C()) {
            c2074b.f20645b = 1;
            z10 = false;
        }
        int[] iArr2 = dVar.f20315u;
        if (z11) {
            if (iArr2[0] == 4) {
                c2074b.f20644a = 1;
            } else if (!z10) {
                if (c2074b.f20645b == 1) {
                    i10 = c2074b.d;
                } else {
                    c2074b.f20644a = 2;
                    interfaceC2075c.b(dVar, c2074b);
                    i10 = c2074b.f20647f;
                }
                c2074b.f20644a = 1;
                c2074b.c = (int) (dVar.f20279X * i10);
            }
        }
        if (z12) {
            if (iArr2[1] == 4) {
                c2074b.f20645b = 1;
            } else if (!z6) {
                if (c2074b.f20644a == 1) {
                    i7 = c2074b.c;
                } else {
                    c2074b.f20645b = 2;
                    interfaceC2075c.b(dVar, c2074b);
                    i7 = c2074b.f20646e;
                }
                c2074b.f20645b = 1;
                if (dVar.f20280Y == -1) {
                    c2074b.d = (int) (i7 / dVar.f20279X);
                } else {
                    c2074b.d = (int) (dVar.f20279X * i7);
                }
            }
        }
        interfaceC2075c.b(dVar, c2074b);
        dVar.S(c2074b.f20646e);
        dVar.N(c2074b.f20647f);
        dVar.f20261F = c2074b.f20649h;
        dVar.J(c2074b.f20648g);
        c2074b.f20651j = 0;
    }

    @Override // o1.d
    public final void D() {
        this.f20338x0.t();
        this.f20339y0 = 0;
        this.f20340z0 = 0;
        this.f20332r0.clear();
        super.D();
    }

    @Override // o1.d
    public final void G(P p10) {
        super.G(p10);
        int size = this.f20332r0.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((d) this.f20332r0.get(i7)).G(p10);
        }
    }

    @Override // o1.d
    public final void T(boolean z6, boolean z10) {
        super.T(z6, z10);
        int size = this.f20332r0.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((d) this.f20332r0.get(i7)).T(z6, z10);
        }
    }

    public final void V(d dVar) {
        this.f20332r0.add(dVar);
        e eVar = dVar.f20276U;
        if (eVar != null) {
            eVar.f20332r0.remove(dVar);
            dVar.D();
        }
        dVar.f20276U = this;
    }

    public final void W(d dVar, int i7) {
        if (i7 == 0) {
            int i10 = this.f20321A0 + 1;
            b[] bVarArr = this.f20323D0;
            if (i10 >= bVarArr.length) {
                this.f20323D0 = (b[]) Arrays.copyOf(bVarArr, bVarArr.length * 2);
            }
            b[] bVarArr2 = this.f20323D0;
            int i11 = this.f20321A0;
            bVarArr2[i11] = new b(dVar, 0, this.f20337w0);
            this.f20321A0 = i11 + 1;
            return;
        }
        if (i7 == 1) {
            int i12 = this.B0 + 1;
            b[] bVarArr3 = this.f20322C0;
            if (i12 >= bVarArr3.length) {
                this.f20322C0 = (b[]) Arrays.copyOf(bVarArr3, bVarArr3.length * 2);
            }
            b[] bVarArr4 = this.f20322C0;
            int i13 = this.B0;
            bVarArr4[i13] = new b(dVar, 1, this.f20337w0);
            this.B0 = i13 + 1;
        }
    }

    public final void X(h1.c cVar) {
        boolean zC0 = c0(64);
        c(cVar, zC0);
        int size = this.f20332r0.size();
        boolean z6 = false;
        for (int i7 = 0; i7 < size; i7++) {
            d dVar = (d) this.f20332r0.get(i7);
            boolean[] zArr = dVar.f20275T;
            zArr[0] = false;
            zArr[1] = false;
            if (dVar instanceof a) {
                z6 = true;
            }
        }
        if (z6) {
            for (int i10 = 0; i10 < size; i10++) {
                d dVar2 = (d) this.f20332r0.get(i10);
                if (dVar2 instanceof a) {
                    a aVar = (a) dVar2;
                    for (int i11 = 0; i11 < aVar.f20386s0; i11++) {
                        d dVar3 = aVar.f20385r0[i11];
                        if (aVar.f20234u0 || dVar3.d()) {
                            int i12 = aVar.f20233t0;
                            if (i12 == 0 || i12 == 1) {
                                dVar3.f20275T[0] = true;
                            } else if (i12 == 2 || i12 == 3) {
                                dVar3.f20275T[1] = true;
                            }
                        }
                    }
                }
            }
        }
        HashSet hashSet = this.f20330L0;
        hashSet.clear();
        for (int i13 = 0; i13 < size; i13++) {
            d dVar4 = (d) this.f20332r0.get(i13);
            dVar4.getClass();
            boolean z10 = dVar4 instanceof k;
            if (z10 || (dVar4 instanceof h)) {
                if (z10) {
                    hashSet.add(dVar4);
                } else {
                    dVar4.c(cVar, zC0);
                }
            }
        }
        while (hashSet.size() > 0) {
            int size2 = hashSet.size();
            Iterator it = hashSet.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                k kVar = (k) ((d) it.next());
                for (int i14 = 0; i14 < kVar.f20386s0; i14++) {
                    if (hashSet.contains(kVar.f20385r0[i14])) {
                        kVar.c(cVar, zC0);
                        hashSet.remove(kVar);
                        break;
                    }
                }
            }
            if (size2 == hashSet.size()) {
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    ((d) it2.next()).c(cVar, zC0);
                }
                hashSet.clear();
            }
        }
        if (h1.c.f15168q) {
            HashSet<d> hashSet2 = new HashSet();
            for (int i15 = 0; i15 < size; i15++) {
                d dVar5 = (d) this.f20332r0.get(i15);
                dVar5.getClass();
                if (!(dVar5 instanceof k) && !(dVar5 instanceof h)) {
                    hashSet2.add(dVar5);
                }
            }
            b(this, cVar, hashSet2, this.f20311q0[0] == 2 ? 0 : 1, false);
            for (d dVar6 : hashSet2) {
                j.b(this, cVar, dVar6);
                dVar6.c(cVar, zC0);
            }
        } else {
            for (int i16 = 0; i16 < size; i16++) {
                d dVar7 = (d) this.f20332r0.get(i16);
                if (dVar7 instanceof e) {
                    int[] iArr = dVar7.f20311q0;
                    int i17 = iArr[0];
                    int i18 = iArr[1];
                    if (i17 == 2) {
                        dVar7.O(1);
                    }
                    if (i18 == 2) {
                        dVar7.Q(1);
                    }
                    dVar7.c(cVar, zC0);
                    if (i17 == 2) {
                        dVar7.O(i17);
                    }
                    if (i18 == 2) {
                        dVar7.Q(i18);
                    }
                } else {
                    j.b(this, cVar, dVar7);
                    if (!(dVar7 instanceof k) && !(dVar7 instanceof h)) {
                        dVar7.c(cVar, zC0);
                    }
                }
            }
        }
        if (this.f20321A0 > 0) {
            j.a(this, cVar, null, 0);
        }
        if (this.B0 > 0) {
            j.a(this, cVar, null, 1);
        }
    }

    public final boolean Y(int i7, boolean z6) {
        boolean z10;
        C2078f c2078f = this.f20334t0;
        e eVar = (e) c2078f.d;
        boolean z11 = false;
        int iK = eVar.k(0);
        int iK2 = eVar.k(1);
        int iS = eVar.s();
        int iT = eVar.t();
        ArrayList<p> arrayList = (ArrayList) c2078f.f20656f;
        if (z6 && (iK == 2 || iK2 == 2)) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                p pVar = (p) it.next();
                if (pVar.f20682f == i7 && !pVar.k()) {
                    z6 = false;
                    break;
                }
            }
            if (i7 == 0) {
                if (z6 && iK == 2) {
                    eVar.O(1);
                    eVar.S(c2078f.d(eVar, 0));
                    eVar.d.f20681e.d(eVar.r());
                }
            } else if (z6 && iK2 == 2) {
                eVar.Q(1);
                eVar.N(c2078f.d(eVar, 1));
                eVar.f20288e.f20681e.d(eVar.l());
            }
        }
        int[] iArr = eVar.f20311q0;
        if (i7 == 0) {
            int i10 = iArr[0];
            if (i10 == 1 || i10 == 4) {
                int iR = eVar.r() + iS;
                eVar.d.f20685i.d(iR);
                eVar.d.f20681e.d(iR - iS);
                z10 = true;
            }
            z10 = false;
        } else {
            int i11 = iArr[1];
            if (i11 == 1 || i11 == 4) {
                int iL = eVar.l() + iT;
                eVar.f20288e.f20685i.d(iL);
                eVar.f20288e.f20681e.d(iL - iT);
                z10 = true;
            }
            z10 = false;
        }
        c2078f.g();
        for (p pVar2 : arrayList) {
            if (pVar2.f20682f == i7 && (pVar2.f20680b != eVar || pVar2.f20683g)) {
                pVar2.e();
            }
        }
        Iterator it2 = arrayList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                z11 = true;
                break;
            }
            p pVar3 = (p) it2.next();
            if (pVar3.f20682f == i7 && (z10 || pVar3.f20680b != eVar)) {
                if (!pVar3.f20684h.f20667j || !pVar3.f20685i.f20667j || (!(pVar3 instanceof C2076d) && !pVar3.f20681e.f20667j)) {
                    break;
                }
            }
        }
        eVar.O(iK);
        eVar.Q(iK2);
        return z11;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:349:0x05db  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x0606  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x0639  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x064f  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x065f  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x066a  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0675 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:396:0x067b  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x0683  */
    /* JADX WARN: Removed duplicated region for block: B:403:0x068a  */
    /* JADX WARN: Removed duplicated region for block: B:406:0x0694  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x06b1  */
    /* JADX WARN: Removed duplicated region for block: B:436:0x0713  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:480:0x07c0  */
    /* JADX WARN: Removed duplicated region for block: B:489:0x07fd  */
    /* JADX WARN: Removed duplicated region for block: B:495:0x0818 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:500:0x0824 A[LOOP:14: B:499:0x0822->B:500:0x0824, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:503:0x0858  */
    /* JADX WARN: Removed duplicated region for block: B:512:0x088a  */
    /* JADX WARN: Removed duplicated region for block: B:513:0x0896  */
    /* JADX WARN: Removed duplicated region for block: B:516:0x08a9  */
    /* JADX WARN: Removed duplicated region for block: B:517:0x08b2  */
    /* JADX WARN: Removed duplicated region for block: B:519:0x08b6  */
    /* JADX WARN: Removed duplicated region for block: B:532:0x08ed  */
    /* JADX WARN: Removed duplicated region for block: B:534:0x08f1  */
    /* JADX WARN: Removed duplicated region for block: B:538:0x08fe  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:611:0x08f2 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v81, types: [int] */
    /* JADX WARN: Type inference failed for: r0v89 */
    /* JADX WARN: Type inference failed for: r0v90 */
    /* JADX WARN: Type inference failed for: r0v91 */
    /* JADX WARN: Type inference failed for: r0v92 */
    /* JADX WARN: Type inference failed for: r0v93 */
    /* JADX WARN: Type inference failed for: r0v94 */
    /* JADX WARN: Type inference failed for: r0v95 */
    /* JADX WARN: Type inference failed for: r0v96 */
    /* JADX WARN: Type inference failed for: r0v97 */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v36 */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r12v9 */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r18v0 */
    /* JADX WARN: Type inference failed for: r18v1 */
    /* JADX WARN: Type inference failed for: r18v2 */
    /* JADX WARN: Type inference failed for: r21v10 */
    /* JADX WARN: Type inference failed for: r21v11 */
    /* JADX WARN: Type inference failed for: r21v12 */
    /* JADX WARN: Type inference failed for: r21v13 */
    /* JADX WARN: Type inference failed for: r21v14 */
    /* JADX WARN: Type inference failed for: r21v15 */
    /* JADX WARN: Type inference failed for: r21v16 */
    /* JADX WARN: Type inference failed for: r21v17 */
    /* JADX WARN: Type inference failed for: r21v18 */
    /* JADX WARN: Type inference failed for: r21v19 */
    /* JADX WARN: Type inference failed for: r21v2 */
    /* JADX WARN: Type inference failed for: r21v23 */
    /* JADX WARN: Type inference failed for: r21v24 */
    /* JADX WARN: Type inference failed for: r21v25 */
    /* JADX WARN: Type inference failed for: r21v26 */
    /* JADX WARN: Type inference failed for: r21v27 */
    /* JADX WARN: Type inference failed for: r21v28 */
    /* JADX WARN: Type inference failed for: r21v29 */
    /* JADX WARN: Type inference failed for: r21v3 */
    /* JADX WARN: Type inference failed for: r21v30 */
    /* JADX WARN: Type inference failed for: r21v4 */
    /* JADX WARN: Type inference failed for: r21v5 */
    /* JADX WARN: Type inference failed for: r21v6 */
    /* JADX WARN: Type inference failed for: r21v7 */
    /* JADX WARN: Type inference failed for: r21v8 */
    /* JADX WARN: Type inference failed for: r21v9 */
    /* JADX WARN: Type inference failed for: r23v0 */
    /* JADX WARN: Type inference failed for: r23v1 */
    /* JADX WARN: Type inference failed for: r23v2 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r31v0, types: [o1.d, o1.e] */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r5v40, types: [int] */
    /* JADX WARN: Type inference failed for: r6v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v75, types: [int] */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r7v28, types: [int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void Z() {
        /*
            Method dump skipped, instruction units count: 2314
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: o1.e.Z():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0221  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x03ab  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x03af A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a0(int r24, int r25, int r26, int r27, int r28, int r29, int r30) {
        /*
            Method dump skipped, instruction units count: 1411
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: o1.e.a0(int, int, int, int, int, int, int):void");
    }

    public final boolean c0(int i7) {
        return (this.f20324E0 & i7) == i7;
    }

    @Override // o1.d
    public final void o(StringBuilder sb) {
        sb.append(this.f20300k + ":{\n");
        StringBuilder sb2 = new StringBuilder("  actualWidth:");
        sb2.append(this.f20277V);
        sb.append(sb2.toString());
        sb.append("\n");
        sb.append("  actualHeight:" + this.f20278W);
        sb.append("\n");
        Iterator it = this.f20332r0.iterator();
        while (it.hasNext()) {
            ((d) it.next()).o(sb);
            sb.append(",\n");
        }
        sb.append("}");
    }
}
