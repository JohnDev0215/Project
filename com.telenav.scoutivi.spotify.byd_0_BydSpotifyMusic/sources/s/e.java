package s;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import r.e;
import s.b;

/* JADX INFO: compiled from: DependencyGraph.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private r.f f9398a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private r.f f9401d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f9399b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f9400c = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ArrayList<p> f9402e = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ArrayList<m> f9403f = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private b.InterfaceC0143b f9404g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private b.a f9405h = new b.a();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    ArrayList<m> f9406i = new ArrayList<>();

    public e(r.f fVar) {
        this.f9398a = fVar;
        this.f9401d = fVar;
    }

    private void a(f fVar, int i7, int i8, f fVar2, ArrayList<m> arrayList, m mVar) {
        p pVar = fVar.f9410d;
        if (pVar.f9454c == null) {
            r.f fVar3 = this.f9398a;
            if (pVar == fVar3.f9212e || pVar == fVar3.f9214f) {
                return;
            }
            if (mVar == null) {
                mVar = new m(pVar, i8);
                arrayList.add(mVar);
            }
            pVar.f9454c = mVar;
            mVar.a(pVar);
            for (d dVar : pVar.f9459h.f9417k) {
                if (dVar instanceof f) {
                    a((f) dVar, i7, 0, fVar2, arrayList, mVar);
                }
            }
            for (d dVar2 : pVar.f9460i.f9417k) {
                if (dVar2 instanceof f) {
                    a((f) dVar2, i7, 1, fVar2, arrayList, mVar);
                }
            }
            if (i7 == 1 && (pVar instanceof n)) {
                for (d dVar3 : ((n) pVar).f9434k.f9417k) {
                    if (dVar3 instanceof f) {
                        a((f) dVar3, i7, 2, fVar2, arrayList, mVar);
                    }
                }
            }
            for (f fVar4 : pVar.f9459h.f9418l) {
                if (fVar4 == fVar2) {
                    mVar.f9428b = true;
                }
                a(fVar4, i7, 0, fVar2, arrayList, mVar);
            }
            for (f fVar5 : pVar.f9460i.f9418l) {
                if (fVar5 == fVar2) {
                    mVar.f9428b = true;
                }
                a(fVar5, i7, 1, fVar2, arrayList, mVar);
            }
            if (i7 == 1 && (pVar instanceof n)) {
                Iterator<f> it = ((n) pVar).f9434k.f9418l.iterator();
                while (it.hasNext()) {
                    a(it.next(), i7, 2, fVar2, arrayList, mVar);
                }
            }
        }
    }

    private boolean b(r.f fVar) {
        int iY;
        e.b bVar;
        int iZ;
        e.b bVar2;
        e.b bVar3;
        e.b bVar4;
        for (r.e eVar : fVar.U0) {
            e.b[] bVarArr = eVar.f9207b0;
            e.b bVar5 = bVarArr[0];
            e.b bVar6 = bVarArr[1];
            if (eVar.X() == 8) {
                eVar.f9204a = true;
            } else {
                if (eVar.B < 1.0f && bVar5 == e.b.MATCH_CONSTRAINT) {
                    eVar.f9248w = 2;
                }
                if (eVar.E < 1.0f && bVar6 == e.b.MATCH_CONSTRAINT) {
                    eVar.f9250x = 2;
                }
                if (eVar.x() > 0.0f) {
                    e.b bVar7 = e.b.MATCH_CONSTRAINT;
                    if (bVar5 == bVar7 && (bVar6 == e.b.WRAP_CONTENT || bVar6 == e.b.FIXED)) {
                        eVar.f9248w = 3;
                    } else if (bVar6 == bVar7 && (bVar5 == e.b.WRAP_CONTENT || bVar5 == e.b.FIXED)) {
                        eVar.f9250x = 3;
                    } else if (bVar5 == bVar7 && bVar6 == bVar7) {
                        if (eVar.f9248w == 0) {
                            eVar.f9248w = 3;
                        }
                        if (eVar.f9250x == 0) {
                            eVar.f9250x = 3;
                        }
                    }
                }
                e.b bVar8 = e.b.MATCH_CONSTRAINT;
                if (bVar5 == bVar8 && eVar.f9248w == 1 && (eVar.Q.f9198f == null || eVar.S.f9198f == null)) {
                    bVar5 = e.b.WRAP_CONTENT;
                }
                e.b bVar9 = bVar5;
                if (bVar6 == bVar8 && eVar.f9250x == 1 && (eVar.R.f9198f == null || eVar.T.f9198f == null)) {
                    bVar6 = e.b.WRAP_CONTENT;
                }
                e.b bVar10 = bVar6;
                l lVar = eVar.f9212e;
                lVar.f9455d = bVar9;
                int i7 = eVar.f9248w;
                lVar.f9452a = i7;
                n nVar = eVar.f9214f;
                nVar.f9455d = bVar10;
                int i8 = eVar.f9250x;
                nVar.f9452a = i8;
                e.b bVar11 = e.b.MATCH_PARENT;
                if ((bVar9 == bVar11 || bVar9 == e.b.FIXED || bVar9 == e.b.WRAP_CONTENT) && (bVar10 == bVar11 || bVar10 == e.b.FIXED || bVar10 == e.b.WRAP_CONTENT)) {
                    int iY2 = eVar.Y();
                    if (bVar9 == bVar11) {
                        iY = (fVar.Y() - eVar.Q.f9199g) - eVar.S.f9199g;
                        bVar = e.b.FIXED;
                    } else {
                        iY = iY2;
                        bVar = bVar9;
                    }
                    int iZ2 = eVar.z();
                    if (bVar10 == bVar11) {
                        iZ = (fVar.z() - eVar.R.f9199g) - eVar.T.f9199g;
                        bVar2 = e.b.FIXED;
                    } else {
                        iZ = iZ2;
                        bVar2 = bVar10;
                    }
                    l(eVar, bVar, iY, bVar2, iZ);
                    eVar.f9212e.f9456e.d(eVar.Y());
                    eVar.f9214f.f9456e.d(eVar.z());
                    eVar.f9204a = true;
                } else {
                    if (bVar9 == bVar8 && (bVar10 == (bVar4 = e.b.WRAP_CONTENT) || bVar10 == e.b.FIXED)) {
                        if (i7 == 3) {
                            if (bVar10 == bVar4) {
                                l(eVar, bVar4, 0, bVar4, 0);
                            }
                            int iZ3 = eVar.z();
                            int i9 = (int) ((iZ3 * eVar.f9215f0) + 0.5f);
                            e.b bVar12 = e.b.FIXED;
                            l(eVar, bVar12, i9, bVar12, iZ3);
                            eVar.f9212e.f9456e.d(eVar.Y());
                            eVar.f9214f.f9456e.d(eVar.z());
                            eVar.f9204a = true;
                        } else if (i7 == 1) {
                            l(eVar, bVar4, 0, bVar10, 0);
                            eVar.f9212e.f9456e.f9420m = eVar.Y();
                        } else if (i7 == 2) {
                            e.b[] bVarArr2 = fVar.f9207b0;
                            e.b bVar13 = bVarArr2[0];
                            e.b bVar14 = e.b.FIXED;
                            if (bVar13 == bVar14 || bVarArr2[0] == bVar11) {
                                l(eVar, bVar14, (int) ((eVar.B * fVar.Y()) + 0.5f), bVar10, eVar.z());
                                eVar.f9212e.f9456e.d(eVar.Y());
                                eVar.f9214f.f9456e.d(eVar.z());
                                eVar.f9204a = true;
                            }
                        } else {
                            r.d[] dVarArr = eVar.Y;
                            if (dVarArr[0].f9198f == null || dVarArr[1].f9198f == null) {
                                l(eVar, bVar4, 0, bVar10, 0);
                                eVar.f9212e.f9456e.d(eVar.Y());
                                eVar.f9214f.f9456e.d(eVar.z());
                                eVar.f9204a = true;
                            }
                        }
                    }
                    if (bVar10 == bVar8 && (bVar9 == (bVar3 = e.b.WRAP_CONTENT) || bVar9 == e.b.FIXED)) {
                        if (i8 == 3) {
                            if (bVar9 == bVar3) {
                                l(eVar, bVar3, 0, bVar3, 0);
                            }
                            int iY3 = eVar.Y();
                            float f7 = eVar.f9215f0;
                            if (eVar.y() == -1) {
                                f7 = 1.0f / f7;
                            }
                            e.b bVar15 = e.b.FIXED;
                            l(eVar, bVar15, iY3, bVar15, (int) ((iY3 * f7) + 0.5f));
                            eVar.f9212e.f9456e.d(eVar.Y());
                            eVar.f9214f.f9456e.d(eVar.z());
                            eVar.f9204a = true;
                        } else if (i8 == 1) {
                            l(eVar, bVar9, 0, bVar3, 0);
                            eVar.f9214f.f9456e.f9420m = eVar.z();
                        } else if (i8 == 2) {
                            e.b[] bVarArr3 = fVar.f9207b0;
                            e.b bVar16 = bVarArr3[1];
                            e.b bVar17 = e.b.FIXED;
                            if (bVar16 == bVar17 || bVarArr3[1] == bVar11) {
                                l(eVar, bVar9, eVar.Y(), bVar17, (int) ((eVar.E * fVar.z()) + 0.5f));
                                eVar.f9212e.f9456e.d(eVar.Y());
                                eVar.f9214f.f9456e.d(eVar.z());
                                eVar.f9204a = true;
                            }
                        } else {
                            r.d[] dVarArr2 = eVar.Y;
                            if (dVarArr2[2].f9198f == null || dVarArr2[3].f9198f == null) {
                                l(eVar, bVar3, 0, bVar10, 0);
                                eVar.f9212e.f9456e.d(eVar.Y());
                                eVar.f9214f.f9456e.d(eVar.z());
                                eVar.f9204a = true;
                            }
                        }
                    }
                    if (bVar9 == bVar8 && bVar10 == bVar8) {
                        if (i7 == 1 || i8 == 1) {
                            e.b bVar18 = e.b.WRAP_CONTENT;
                            l(eVar, bVar18, 0, bVar18, 0);
                            eVar.f9212e.f9456e.f9420m = eVar.Y();
                            eVar.f9214f.f9456e.f9420m = eVar.z();
                        } else if (i8 == 2 && i7 == 2) {
                            e.b[] bVarArr4 = fVar.f9207b0;
                            e.b bVar19 = bVarArr4[0];
                            e.b bVar20 = e.b.FIXED;
                            if (bVar19 == bVar20 && bVarArr4[1] == bVar20) {
                                l(eVar, bVar20, (int) ((eVar.B * fVar.Y()) + 0.5f), bVar20, (int) ((eVar.E * fVar.z()) + 0.5f));
                                eVar.f9212e.f9456e.d(eVar.Y());
                                eVar.f9214f.f9456e.d(eVar.z());
                                eVar.f9204a = true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private int e(r.f fVar, int i7) {
        int size = this.f9406i.size();
        long jMax = 0;
        for (int i8 = 0; i8 < size; i8++) {
            jMax = Math.max(jMax, this.f9406i.get(i8).b(fVar, i7));
        }
        return (int) jMax;
    }

    private void i(p pVar, int i7, ArrayList<m> arrayList) {
        for (d dVar : pVar.f9459h.f9417k) {
            if (dVar instanceof f) {
                a((f) dVar, i7, 0, pVar.f9460i, arrayList, null);
            } else if (dVar instanceof p) {
                a(((p) dVar).f9459h, i7, 0, pVar.f9460i, arrayList, null);
            }
        }
        for (d dVar2 : pVar.f9460i.f9417k) {
            if (dVar2 instanceof f) {
                a((f) dVar2, i7, 1, pVar.f9459h, arrayList, null);
            } else if (dVar2 instanceof p) {
                a(((p) dVar2).f9460i, i7, 1, pVar.f9459h, arrayList, null);
            }
        }
        if (i7 == 1) {
            for (d dVar3 : ((n) pVar).f9434k.f9417k) {
                if (dVar3 instanceof f) {
                    a((f) dVar3, i7, 2, null, arrayList, null);
                }
            }
        }
    }

    private void l(r.e eVar, e.b bVar, int i7, e.b bVar2, int i8) {
        b.a aVar = this.f9405h;
        aVar.f9386a = bVar;
        aVar.f9387b = bVar2;
        aVar.f9388c = i7;
        aVar.f9389d = i8;
        this.f9404g.a(eVar, aVar);
        eVar.n1(this.f9405h.f9390e);
        eVar.O0(this.f9405h.f9391f);
        eVar.N0(this.f9405h.f9393h);
        eVar.D0(this.f9405h.f9392g);
    }

    public void c() {
        d(this.f9402e);
        this.f9406i.clear();
        m.f9426h = 0;
        i(this.f9398a.f9212e, 0, this.f9406i);
        i(this.f9398a.f9214f, 1, this.f9406i);
        this.f9399b = false;
    }

    public void d(ArrayList<p> arrayList) {
        arrayList.clear();
        this.f9401d.f9212e.f();
        this.f9401d.f9214f.f();
        arrayList.add(this.f9401d.f9212e);
        arrayList.add(this.f9401d.f9214f);
        HashSet hashSet = null;
        for (r.e eVar : this.f9401d.U0) {
            if (eVar instanceof r.h) {
                arrayList.add(new j(eVar));
            } else {
                if (eVar.k0()) {
                    if (eVar.f9208c == null) {
                        eVar.f9208c = new c(eVar, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(eVar.f9208c);
                } else {
                    arrayList.add(eVar.f9212e);
                }
                if (eVar.m0()) {
                    if (eVar.f9210d == null) {
                        eVar.f9210d = new c(eVar, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(eVar.f9210d);
                } else {
                    arrayList.add(eVar.f9214f);
                }
                if (eVar instanceof r.j) {
                    arrayList.add(new k(eVar));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator<p> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().f();
        }
        for (p pVar : arrayList) {
            if (pVar.f9453b != this.f9401d) {
                pVar.d();
            }
        }
    }

    public boolean f(boolean z6) {
        boolean z7;
        boolean z8 = true;
        boolean z9 = z6 & true;
        if (this.f9399b || this.f9400c) {
            for (r.e eVar : this.f9398a.U0) {
                eVar.p();
                eVar.f9204a = false;
                eVar.f9212e.r();
                eVar.f9214f.q();
            }
            this.f9398a.p();
            r.f fVar = this.f9398a;
            fVar.f9204a = false;
            fVar.f9212e.r();
            this.f9398a.f9214f.q();
            this.f9400c = false;
        }
        if (b(this.f9401d)) {
            return false;
        }
        this.f9398a.p1(0);
        this.f9398a.q1(0);
        e.b bVarW = this.f9398a.w(0);
        e.b bVarW2 = this.f9398a.w(1);
        if (this.f9399b) {
            c();
        }
        int iZ = this.f9398a.Z();
        int iA0 = this.f9398a.a0();
        this.f9398a.f9212e.f9459h.d(iZ);
        this.f9398a.f9214f.f9459h.d(iA0);
        m();
        e.b bVar = e.b.WRAP_CONTENT;
        if (bVarW == bVar || bVarW2 == bVar) {
            if (z9) {
                Iterator<p> it = this.f9402e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (!it.next().m()) {
                        z9 = false;
                        break;
                    }
                }
            }
            if (z9 && bVarW == e.b.WRAP_CONTENT) {
                this.f9398a.S0(e.b.FIXED);
                r.f fVar2 = this.f9398a;
                fVar2.n1(e(fVar2, 0));
                r.f fVar3 = this.f9398a;
                fVar3.f9212e.f9456e.d(fVar3.Y());
            }
            if (z9 && bVarW2 == e.b.WRAP_CONTENT) {
                this.f9398a.j1(e.b.FIXED);
                r.f fVar4 = this.f9398a;
                fVar4.O0(e(fVar4, 1));
                r.f fVar5 = this.f9398a;
                fVar5.f9214f.f9456e.d(fVar5.z());
            }
        }
        r.f fVar6 = this.f9398a;
        e.b[] bVarArr = fVar6.f9207b0;
        e.b bVar2 = bVarArr[0];
        e.b bVar3 = e.b.FIXED;
        if (bVar2 == bVar3 || bVarArr[0] == e.b.MATCH_PARENT) {
            int iY = fVar6.Y() + iZ;
            this.f9398a.f9212e.f9460i.d(iY);
            this.f9398a.f9212e.f9456e.d(iY - iZ);
            m();
            r.f fVar7 = this.f9398a;
            e.b[] bVarArr2 = fVar7.f9207b0;
            if (bVarArr2[1] == bVar3 || bVarArr2[1] == e.b.MATCH_PARENT) {
                int iZ2 = fVar7.z() + iA0;
                this.f9398a.f9214f.f9460i.d(iZ2);
                this.f9398a.f9214f.f9456e.d(iZ2 - iA0);
            }
            m();
            z7 = true;
        } else {
            z7 = false;
        }
        for (p pVar : this.f9402e) {
            if (pVar.f9453b != this.f9398a || pVar.f9458g) {
                pVar.e();
            }
        }
        for (p pVar2 : this.f9402e) {
            if (z7 || pVar2.f9453b != this.f9398a) {
                if (!pVar2.f9459h.f9416j || ((!pVar2.f9460i.f9416j && !(pVar2 instanceof j)) || (!pVar2.f9456e.f9416j && !(pVar2 instanceof c) && !(pVar2 instanceof j)))) {
                    z8 = false;
                    break;
                }
            }
        }
        this.f9398a.S0(bVarW);
        this.f9398a.j1(bVarW2);
        return z8;
    }

    public boolean g(boolean z6) {
        if (this.f9399b) {
            for (r.e eVar : this.f9398a.U0) {
                eVar.p();
                eVar.f9204a = false;
                l lVar = eVar.f9212e;
                lVar.f9456e.f9416j = false;
                lVar.f9458g = false;
                lVar.r();
                n nVar = eVar.f9214f;
                nVar.f9456e.f9416j = false;
                nVar.f9458g = false;
                nVar.q();
            }
            this.f9398a.p();
            r.f fVar = this.f9398a;
            fVar.f9204a = false;
            l lVar2 = fVar.f9212e;
            lVar2.f9456e.f9416j = false;
            lVar2.f9458g = false;
            lVar2.r();
            n nVar2 = this.f9398a.f9214f;
            nVar2.f9456e.f9416j = false;
            nVar2.f9458g = false;
            nVar2.q();
            c();
        }
        if (b(this.f9401d)) {
            return false;
        }
        this.f9398a.p1(0);
        this.f9398a.q1(0);
        this.f9398a.f9212e.f9459h.d(0);
        this.f9398a.f9214f.f9459h.d(0);
        return true;
    }

    public boolean h(boolean z6, int i7) {
        boolean z7;
        e.b bVar;
        boolean z8 = true;
        boolean z9 = z6 & true;
        e.b bVarW = this.f9398a.w(0);
        e.b bVarW2 = this.f9398a.w(1);
        int iZ = this.f9398a.Z();
        int iA0 = this.f9398a.a0();
        if (z9 && (bVarW == (bVar = e.b.WRAP_CONTENT) || bVarW2 == bVar)) {
            Iterator<p> it = this.f9402e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                p next = it.next();
                if (next.f9457f == i7 && !next.m()) {
                    z9 = false;
                    break;
                }
            }
            if (i7 == 0) {
                if (z9 && bVarW == e.b.WRAP_CONTENT) {
                    this.f9398a.S0(e.b.FIXED);
                    r.f fVar = this.f9398a;
                    fVar.n1(e(fVar, 0));
                    r.f fVar2 = this.f9398a;
                    fVar2.f9212e.f9456e.d(fVar2.Y());
                }
            } else if (z9 && bVarW2 == e.b.WRAP_CONTENT) {
                this.f9398a.j1(e.b.FIXED);
                r.f fVar3 = this.f9398a;
                fVar3.O0(e(fVar3, 1));
                r.f fVar4 = this.f9398a;
                fVar4.f9214f.f9456e.d(fVar4.z());
            }
        }
        if (i7 == 0) {
            r.f fVar5 = this.f9398a;
            e.b[] bVarArr = fVar5.f9207b0;
            if (bVarArr[0] == e.b.FIXED || bVarArr[0] == e.b.MATCH_PARENT) {
                int iY = fVar5.Y() + iZ;
                this.f9398a.f9212e.f9460i.d(iY);
                this.f9398a.f9212e.f9456e.d(iY - iZ);
                z7 = true;
            }
            z7 = false;
        } else {
            r.f fVar6 = this.f9398a;
            e.b[] bVarArr2 = fVar6.f9207b0;
            if (bVarArr2[1] == e.b.FIXED || bVarArr2[1] == e.b.MATCH_PARENT) {
                int iZ2 = fVar6.z() + iA0;
                this.f9398a.f9214f.f9460i.d(iZ2);
                this.f9398a.f9214f.f9456e.d(iZ2 - iA0);
                z7 = true;
            }
            z7 = false;
        }
        m();
        for (p pVar : this.f9402e) {
            if (pVar.f9457f == i7 && (pVar.f9453b != this.f9398a || pVar.f9458g)) {
                pVar.e();
            }
        }
        for (p pVar2 : this.f9402e) {
            if (pVar2.f9457f == i7 && (z7 || pVar2.f9453b != this.f9398a)) {
                if (!pVar2.f9459h.f9416j || !pVar2.f9460i.f9416j || (!(pVar2 instanceof c) && !pVar2.f9456e.f9416j)) {
                    z8 = false;
                    break;
                }
            }
        }
        this.f9398a.S0(bVarW);
        this.f9398a.j1(bVarW2);
        return z8;
    }

    public void j() {
        this.f9399b = true;
    }

    public void k() {
        this.f9400c = true;
    }

    public void m() {
        g gVar;
        for (r.e eVar : this.f9398a.U0) {
            if (!eVar.f9204a) {
                e.b[] bVarArr = eVar.f9207b0;
                boolean z6 = false;
                e.b bVar = bVarArr[0];
                e.b bVar2 = bVarArr[1];
                int i7 = eVar.f9248w;
                int i8 = eVar.f9250x;
                e.b bVar3 = e.b.WRAP_CONTENT;
                boolean z7 = bVar == bVar3 || (bVar == e.b.MATCH_CONSTRAINT && i7 == 1);
                if (bVar2 == bVar3 || (bVar2 == e.b.MATCH_CONSTRAINT && i8 == 1)) {
                    z6 = true;
                }
                g gVar2 = eVar.f9212e.f9456e;
                boolean z8 = gVar2.f9416j;
                g gVar3 = eVar.f9214f.f9456e;
                boolean z9 = gVar3.f9416j;
                if (z8 && z9) {
                    e.b bVar4 = e.b.FIXED;
                    l(eVar, bVar4, gVar2.f9413g, bVar4, gVar3.f9413g);
                    eVar.f9204a = true;
                } else if (z8 && z6) {
                    l(eVar, e.b.FIXED, gVar2.f9413g, bVar3, gVar3.f9413g);
                    if (bVar2 == e.b.MATCH_CONSTRAINT) {
                        eVar.f9214f.f9456e.f9420m = eVar.z();
                    } else {
                        eVar.f9214f.f9456e.d(eVar.z());
                        eVar.f9204a = true;
                    }
                } else if (z9 && z7) {
                    l(eVar, bVar3, gVar2.f9413g, e.b.FIXED, gVar3.f9413g);
                    if (bVar == e.b.MATCH_CONSTRAINT) {
                        eVar.f9212e.f9456e.f9420m = eVar.Y();
                    } else {
                        eVar.f9212e.f9456e.d(eVar.Y());
                        eVar.f9204a = true;
                    }
                }
                if (eVar.f9204a && (gVar = eVar.f9214f.f9435l) != null) {
                    gVar.d(eVar.r());
                }
            }
        }
    }

    public void n(b.InterfaceC0143b interfaceC0143b) {
        this.f9404g = interfaceC0143b;
    }
}
