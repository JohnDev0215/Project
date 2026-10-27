package b.e.b.k.n;

import b.e.b.k.e;
import b.e.b.k.n.b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: DependencyGraph.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private b.e.b.k.f f2477a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private b.e.b.k.f f2480d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f2478b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2479c = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ArrayList<m> f2481e = new ArrayList<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private ArrayList<k> f2482f = new ArrayList<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private b.InterfaceC0038b f2483g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private b.a f2484h = new b.a();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    ArrayList<k> f2485i = new ArrayList<>();

    public e(b.e.b.k.f fVar) {
        this.f2477a = fVar;
        this.f2480d = fVar;
    }

    private void a(f fVar, int i2, int i3, f fVar2, ArrayList<k> arrayList, k kVar) {
        m mVar = fVar.f2489d;
        if (mVar.f2515c == null) {
            b.e.b.k.f fVar3 = this.f2477a;
            if (mVar == fVar3.f2443f || mVar == fVar3.f2444g) {
                return;
            }
            if (kVar == null) {
                kVar = new k(mVar, i3);
                arrayList.add(kVar);
            }
            mVar.f2515c = kVar;
            kVar.a(mVar);
            for (d dVar : mVar.f2520h.k) {
                if (dVar instanceof f) {
                    a((f) dVar, i2, 0, fVar2, arrayList, kVar);
                }
            }
            for (d dVar2 : mVar.f2521i.k) {
                if (dVar2 instanceof f) {
                    a((f) dVar2, i2, 1, fVar2, arrayList, kVar);
                }
            }
            if (i2 == 1 && (mVar instanceof l)) {
                for (d dVar3 : ((l) mVar).k.k) {
                    if (dVar3 instanceof f) {
                        a((f) dVar3, i2, 2, fVar2, arrayList, kVar);
                    }
                }
            }
            for (f fVar4 : mVar.f2520h.l) {
                if (fVar4 == fVar2) {
                    kVar.f2506c = true;
                }
                a(fVar4, i2, 0, fVar2, arrayList, kVar);
            }
            for (f fVar5 : mVar.f2521i.l) {
                if (fVar5 == fVar2) {
                    kVar.f2506c = true;
                }
                a(fVar5, i2, 1, fVar2, arrayList, kVar);
            }
            if (i2 == 1 && (mVar instanceof l)) {
                Iterator<f> it = ((l) mVar).k.l.iterator();
                while (it.hasNext()) {
                    a(it.next(), i2, 2, fVar2, arrayList, kVar);
                }
            }
        }
    }

    private boolean b(b.e.b.k.f fVar) {
        int iQ;
        e.b bVar;
        int iW;
        e.b bVar2;
        e.b bVar3;
        e.b bVar4;
        for (b.e.b.k.e eVar : fVar.G0) {
            e.b[] bVarArr = eVar.P;
            e.b bVar5 = bVarArr[0];
            e.b bVar6 = bVarArr[1];
            if (eVar.P() == 8) {
                eVar.f2439b = true;
            } else {
                if (eVar.r < 1.0f && bVar5 == e.b.MATCH_CONSTRAINT) {
                    eVar.m = 2;
                }
                if (eVar.u < 1.0f && bVar6 == e.b.MATCH_CONSTRAINT) {
                    eVar.n = 2;
                }
                if (eVar.u() > 0.0f) {
                    e.b bVar7 = e.b.MATCH_CONSTRAINT;
                    if (bVar5 == bVar7 && (bVar6 == e.b.WRAP_CONTENT || bVar6 == e.b.FIXED)) {
                        eVar.m = 3;
                    } else if (bVar6 == bVar7 && (bVar5 == e.b.WRAP_CONTENT || bVar5 == e.b.FIXED)) {
                        eVar.n = 3;
                    } else if (bVar5 == bVar7 && bVar6 == bVar7) {
                        if (eVar.m == 0) {
                            eVar.m = 3;
                        }
                        if (eVar.n == 0) {
                            eVar.n = 3;
                        }
                    }
                }
                e.b bVar8 = e.b.MATCH_CONSTRAINT;
                if (bVar5 == bVar8 && eVar.m == 1 && (eVar.E.f2425d == null || eVar.G.f2425d == null)) {
                    bVar5 = e.b.WRAP_CONTENT;
                }
                e.b bVar9 = bVar5;
                if (bVar6 == bVar8 && eVar.n == 1 && (eVar.F.f2425d == null || eVar.H.f2425d == null)) {
                    bVar6 = e.b.WRAP_CONTENT;
                }
                e.b bVar10 = bVar6;
                j jVar = eVar.f2443f;
                jVar.f2516d = bVar9;
                int i2 = eVar.m;
                jVar.f2513a = i2;
                l lVar = eVar.f2444g;
                lVar.f2516d = bVar10;
                int i3 = eVar.n;
                lVar.f2513a = i3;
                e.b bVar11 = e.b.MATCH_PARENT;
                if ((bVar9 == bVar11 || bVar9 == e.b.FIXED || bVar9 == e.b.WRAP_CONTENT) && (bVar10 == bVar11 || bVar10 == e.b.FIXED || bVar10 == e.b.WRAP_CONTENT)) {
                    int iQ2 = eVar.Q();
                    if (bVar9 == bVar11) {
                        iQ = (fVar.Q() - eVar.E.f2426e) - eVar.G.f2426e;
                        bVar = e.b.FIXED;
                    } else {
                        iQ = iQ2;
                        bVar = bVar9;
                    }
                    int iW2 = eVar.w();
                    if (bVar10 == bVar11) {
                        iW = (fVar.w() - eVar.F.f2426e) - eVar.H.f2426e;
                        bVar2 = e.b.FIXED;
                    } else {
                        iW = iW2;
                        bVar2 = bVar10;
                    }
                    l(eVar, bVar, iQ, bVar2, iW);
                    eVar.f2443f.f2517e.d(eVar.Q());
                    eVar.f2444g.f2517e.d(eVar.w());
                    eVar.f2439b = true;
                } else {
                    if (bVar9 == bVar8 && (bVar10 == (bVar4 = e.b.WRAP_CONTENT) || bVar10 == e.b.FIXED)) {
                        if (i2 == 3) {
                            if (bVar10 == bVar4) {
                                l(eVar, bVar4, 0, bVar4, 0);
                            }
                            int iW3 = eVar.w();
                            int i4 = (int) ((iW3 * eVar.T) + 0.5f);
                            e.b bVar12 = e.b.FIXED;
                            l(eVar, bVar12, i4, bVar12, iW3);
                            eVar.f2443f.f2517e.d(eVar.Q());
                            eVar.f2444g.f2517e.d(eVar.w());
                            eVar.f2439b = true;
                        } else if (i2 == 1) {
                            l(eVar, bVar4, 0, bVar10, 0);
                            eVar.f2443f.f2517e.m = eVar.Q();
                        } else if (i2 == 2) {
                            e.b[] bVarArr2 = fVar.P;
                            e.b bVar13 = bVarArr2[0];
                            e.b bVar14 = e.b.FIXED;
                            if (bVar13 == bVar14 || bVarArr2[0] == bVar11) {
                                l(eVar, bVar14, (int) ((eVar.r * fVar.Q()) + 0.5f), bVar10, eVar.w());
                                eVar.f2443f.f2517e.d(eVar.Q());
                                eVar.f2444g.f2517e.d(eVar.w());
                                eVar.f2439b = true;
                            }
                        } else {
                            b.e.b.k.d[] dVarArr = eVar.M;
                            if (dVarArr[0].f2425d == null || dVarArr[1].f2425d == null) {
                                l(eVar, bVar4, 0, bVar10, 0);
                                eVar.f2443f.f2517e.d(eVar.Q());
                                eVar.f2444g.f2517e.d(eVar.w());
                                eVar.f2439b = true;
                            }
                        }
                    }
                    if (bVar10 == bVar8 && (bVar9 == (bVar3 = e.b.WRAP_CONTENT) || bVar9 == e.b.FIXED)) {
                        if (i3 == 3) {
                            if (bVar9 == bVar3) {
                                l(eVar, bVar3, 0, bVar3, 0);
                            }
                            int iQ3 = eVar.Q();
                            float f2 = eVar.T;
                            if (eVar.v() == -1) {
                                f2 = 1.0f / f2;
                            }
                            e.b bVar15 = e.b.FIXED;
                            l(eVar, bVar15, iQ3, bVar15, (int) ((iQ3 * f2) + 0.5f));
                            eVar.f2443f.f2517e.d(eVar.Q());
                            eVar.f2444g.f2517e.d(eVar.w());
                            eVar.f2439b = true;
                        } else if (i3 == 1) {
                            l(eVar, bVar9, 0, bVar3, 0);
                            eVar.f2444g.f2517e.m = eVar.w();
                        } else if (i3 == 2) {
                            e.b[] bVarArr3 = fVar.P;
                            e.b bVar16 = bVarArr3[1];
                            e.b bVar17 = e.b.FIXED;
                            if (bVar16 == bVar17 || bVarArr3[1] == bVar11) {
                                l(eVar, bVar9, eVar.Q(), bVar17, (int) ((eVar.u * fVar.w()) + 0.5f));
                                eVar.f2443f.f2517e.d(eVar.Q());
                                eVar.f2444g.f2517e.d(eVar.w());
                                eVar.f2439b = true;
                            }
                        } else {
                            b.e.b.k.d[] dVarArr2 = eVar.M;
                            if (dVarArr2[2].f2425d == null || dVarArr2[3].f2425d == null) {
                                l(eVar, bVar3, 0, bVar10, 0);
                                eVar.f2443f.f2517e.d(eVar.Q());
                                eVar.f2444g.f2517e.d(eVar.w());
                                eVar.f2439b = true;
                            }
                        }
                    }
                    if (bVar9 == bVar8 && bVar10 == bVar8) {
                        if (i2 == 1 || i3 == 1) {
                            e.b bVar18 = e.b.WRAP_CONTENT;
                            l(eVar, bVar18, 0, bVar18, 0);
                            eVar.f2443f.f2517e.m = eVar.Q();
                            eVar.f2444g.f2517e.m = eVar.w();
                        } else if (i3 == 2 && i2 == 2) {
                            e.b[] bVarArr4 = fVar.P;
                            e.b bVar19 = bVarArr4[0];
                            e.b bVar20 = e.b.FIXED;
                            if (bVar19 == bVar20 || bVarArr4[0] == bVar20) {
                                if (bVarArr4[1] == bVar20 || bVarArr4[1] == bVar20) {
                                    l(eVar, bVar20, (int) ((eVar.r * fVar.Q()) + 0.5f), bVar20, (int) ((eVar.u * fVar.w()) + 0.5f));
                                    eVar.f2443f.f2517e.d(eVar.Q());
                                    eVar.f2444g.f2517e.d(eVar.w());
                                    eVar.f2439b = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private int e(b.e.b.k.f fVar, int i2) {
        int size = this.f2485i.size();
        long jMax = 0;
        for (int i3 = 0; i3 < size; i3++) {
            jMax = Math.max(jMax, this.f2485i.get(i3).b(fVar, i2));
        }
        return (int) jMax;
    }

    private void i(m mVar, int i2, ArrayList<k> arrayList) {
        for (d dVar : mVar.f2520h.k) {
            if (dVar instanceof f) {
                a((f) dVar, i2, 0, mVar.f2521i, arrayList, null);
            } else if (dVar instanceof m) {
                a(((m) dVar).f2520h, i2, 0, mVar.f2521i, arrayList, null);
            }
        }
        for (d dVar2 : mVar.f2521i.k) {
            if (dVar2 instanceof f) {
                a((f) dVar2, i2, 1, mVar.f2520h, arrayList, null);
            } else if (dVar2 instanceof m) {
                a(((m) dVar2).f2521i, i2, 1, mVar.f2520h, arrayList, null);
            }
        }
        if (i2 == 1) {
            for (d dVar3 : ((l) mVar).k.k) {
                if (dVar3 instanceof f) {
                    a((f) dVar3, i2, 2, null, arrayList, null);
                }
            }
        }
    }

    private void l(b.e.b.k.e eVar, e.b bVar, int i2, e.b bVar2, int i3) {
        b.a aVar = this.f2484h;
        aVar.f2468a = bVar;
        aVar.f2469b = bVar2;
        aVar.f2470c = i2;
        aVar.f2471d = i3;
        this.f2483g.a(eVar, aVar);
        eVar.F0(this.f2484h.f2472e);
        eVar.i0(this.f2484h.f2473f);
        eVar.h0(this.f2484h.f2475h);
        eVar.c0(this.f2484h.f2474g);
    }

    public void c() {
        d(this.f2481e);
        this.f2485i.clear();
        k.f2504a = 0;
        i(this.f2477a.f2443f, 0, this.f2485i);
        i(this.f2477a.f2444g, 1, this.f2485i);
        this.f2478b = false;
    }

    public void d(ArrayList<m> arrayList) {
        arrayList.clear();
        this.f2480d.f2443f.f();
        this.f2480d.f2444g.f();
        arrayList.add(this.f2480d.f2443f);
        arrayList.add(this.f2480d.f2444g);
        HashSet hashSet = null;
        for (b.e.b.k.e eVar : this.f2480d.G0) {
            if (eVar instanceof b.e.b.k.h) {
                arrayList.add(new h(eVar));
            } else {
                if (eVar.W()) {
                    if (eVar.f2441d == null) {
                        eVar.f2441d = new c(eVar, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(eVar.f2441d);
                } else {
                    arrayList.add(eVar.f2443f);
                }
                if (eVar.Y()) {
                    if (eVar.f2442e == null) {
                        eVar.f2442e = new c(eVar, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(eVar.f2442e);
                } else {
                    arrayList.add(eVar.f2444g);
                }
                if (eVar instanceof b.e.b.k.j) {
                    arrayList.add(new i(eVar));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator<m> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().f();
        }
        for (m mVar : arrayList) {
            if (mVar.f2514b != this.f2480d) {
                mVar.d();
            }
        }
    }

    public boolean f(boolean z) {
        boolean z2;
        boolean z3 = true;
        boolean z4 = z & true;
        if (this.f2478b || this.f2479c) {
            for (b.e.b.k.e eVar : this.f2477a.G0) {
                eVar.f2439b = false;
                eVar.f2443f.r();
                eVar.f2444g.q();
            }
            b.e.b.k.f fVar = this.f2477a;
            fVar.f2439b = false;
            fVar.f2443f.r();
            this.f2477a.f2444g.q();
            this.f2479c = false;
        }
        if (b(this.f2480d)) {
            return false;
        }
        this.f2477a.G0(0);
        this.f2477a.H0(0);
        e.b bVarT = this.f2477a.t(0);
        e.b bVarT2 = this.f2477a.t(1);
        if (this.f2478b) {
            c();
        }
        int iR = this.f2477a.R();
        int iS = this.f2477a.S();
        this.f2477a.f2443f.f2520h.d(iR);
        this.f2477a.f2444g.f2520h.d(iS);
        m();
        e.b bVar = e.b.WRAP_CONTENT;
        if (bVarT == bVar || bVarT2 == bVar) {
            if (z4) {
                Iterator<m> it = this.f2481e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    if (!it.next().m()) {
                        z4 = false;
                        break;
                    }
                }
            }
            if (z4 && bVarT == e.b.WRAP_CONTENT) {
                this.f2477a.m0(e.b.FIXED);
                b.e.b.k.f fVar2 = this.f2477a;
                fVar2.F0(e(fVar2, 0));
                b.e.b.k.f fVar3 = this.f2477a;
                fVar3.f2443f.f2517e.d(fVar3.Q());
            }
            if (z4 && bVarT2 == e.b.WRAP_CONTENT) {
                this.f2477a.B0(e.b.FIXED);
                b.e.b.k.f fVar4 = this.f2477a;
                fVar4.i0(e(fVar4, 1));
                b.e.b.k.f fVar5 = this.f2477a;
                fVar5.f2444g.f2517e.d(fVar5.w());
            }
        }
        b.e.b.k.f fVar6 = this.f2477a;
        e.b[] bVarArr = fVar6.P;
        e.b bVar2 = bVarArr[0];
        e.b bVar3 = e.b.FIXED;
        if (bVar2 == bVar3 || bVarArr[0] == e.b.MATCH_PARENT) {
            int iQ = fVar6.Q() + iR;
            this.f2477a.f2443f.f2521i.d(iQ);
            this.f2477a.f2443f.f2517e.d(iQ - iR);
            m();
            b.e.b.k.f fVar7 = this.f2477a;
            e.b[] bVarArr2 = fVar7.P;
            if (bVarArr2[1] == bVar3 || bVarArr2[1] == e.b.MATCH_PARENT) {
                int iW = fVar7.w() + iS;
                this.f2477a.f2444g.f2521i.d(iW);
                this.f2477a.f2444g.f2517e.d(iW - iS);
            }
            m();
            z2 = true;
        } else {
            z2 = false;
        }
        for (m mVar : this.f2481e) {
            if (mVar.f2514b != this.f2477a || mVar.f2519g) {
                mVar.e();
            }
        }
        for (m mVar2 : this.f2481e) {
            if (z2 || mVar2.f2514b != this.f2477a) {
                if (!mVar2.f2520h.j || ((!mVar2.f2521i.j && !(mVar2 instanceof h)) || (!mVar2.f2517e.j && !(mVar2 instanceof c) && !(mVar2 instanceof h)))) {
                    z3 = false;
                    break;
                }
            }
        }
        this.f2477a.m0(bVarT);
        this.f2477a.B0(bVarT2);
        return z3;
    }

    public boolean g(boolean z) {
        if (this.f2478b) {
            for (b.e.b.k.e eVar : this.f2477a.G0) {
                eVar.f2439b = false;
                j jVar = eVar.f2443f;
                jVar.f2517e.j = false;
                jVar.f2519g = false;
                jVar.r();
                l lVar = eVar.f2444g;
                lVar.f2517e.j = false;
                lVar.f2519g = false;
                lVar.q();
            }
            b.e.b.k.f fVar = this.f2477a;
            fVar.f2439b = false;
            j jVar2 = fVar.f2443f;
            jVar2.f2517e.j = false;
            jVar2.f2519g = false;
            jVar2.r();
            l lVar2 = this.f2477a.f2444g;
            lVar2.f2517e.j = false;
            lVar2.f2519g = false;
            lVar2.q();
            c();
        }
        if (b(this.f2480d)) {
            return false;
        }
        this.f2477a.G0(0);
        this.f2477a.H0(0);
        this.f2477a.f2443f.f2520h.d(0);
        this.f2477a.f2444g.f2520h.d(0);
        return true;
    }

    public boolean h(boolean z, int i2) {
        boolean z2;
        e.b bVar;
        boolean z3 = true;
        boolean z4 = z & true;
        e.b bVarT = this.f2477a.t(0);
        e.b bVarT2 = this.f2477a.t(1);
        int iR = this.f2477a.R();
        int iS = this.f2477a.S();
        if (z4 && (bVarT == (bVar = e.b.WRAP_CONTENT) || bVarT2 == bVar)) {
            Iterator<m> it = this.f2481e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                m next = it.next();
                if (next.f2518f == i2 && !next.m()) {
                    z4 = false;
                    break;
                }
            }
            if (i2 == 0) {
                if (z4 && bVarT == e.b.WRAP_CONTENT) {
                    this.f2477a.m0(e.b.FIXED);
                    b.e.b.k.f fVar = this.f2477a;
                    fVar.F0(e(fVar, 0));
                    b.e.b.k.f fVar2 = this.f2477a;
                    fVar2.f2443f.f2517e.d(fVar2.Q());
                }
            } else if (z4 && bVarT2 == e.b.WRAP_CONTENT) {
                this.f2477a.B0(e.b.FIXED);
                b.e.b.k.f fVar3 = this.f2477a;
                fVar3.i0(e(fVar3, 1));
                b.e.b.k.f fVar4 = this.f2477a;
                fVar4.f2444g.f2517e.d(fVar4.w());
            }
        }
        if (i2 == 0) {
            b.e.b.k.f fVar5 = this.f2477a;
            e.b[] bVarArr = fVar5.P;
            if (bVarArr[0] == e.b.FIXED || bVarArr[0] == e.b.MATCH_PARENT) {
                int iQ = fVar5.Q() + iR;
                this.f2477a.f2443f.f2521i.d(iQ);
                this.f2477a.f2443f.f2517e.d(iQ - iR);
                z2 = true;
            }
            z2 = false;
        } else {
            b.e.b.k.f fVar6 = this.f2477a;
            e.b[] bVarArr2 = fVar6.P;
            if (bVarArr2[1] == e.b.FIXED || bVarArr2[1] == e.b.MATCH_PARENT) {
                int iW = fVar6.w() + iS;
                this.f2477a.f2444g.f2521i.d(iW);
                this.f2477a.f2444g.f2517e.d(iW - iS);
                z2 = true;
            }
            z2 = false;
        }
        m();
        for (m mVar : this.f2481e) {
            if (mVar.f2518f == i2 && (mVar.f2514b != this.f2477a || mVar.f2519g)) {
                mVar.e();
            }
        }
        for (m mVar2 : this.f2481e) {
            if (mVar2.f2518f == i2 && (z2 || mVar2.f2514b != this.f2477a)) {
                if (!mVar2.f2520h.j || !mVar2.f2521i.j || (!(mVar2 instanceof c) && !mVar2.f2517e.j)) {
                    z3 = false;
                    break;
                }
            }
        }
        this.f2477a.m0(bVarT);
        this.f2477a.B0(bVarT2);
        return z3;
    }

    public void j() {
        this.f2478b = true;
    }

    public void k() {
        this.f2479c = true;
    }

    public void m() {
        g gVar;
        for (b.e.b.k.e eVar : this.f2477a.G0) {
            if (!eVar.f2439b) {
                e.b[] bVarArr = eVar.P;
                boolean z = false;
                e.b bVar = bVarArr[0];
                e.b bVar2 = bVarArr[1];
                int i2 = eVar.m;
                int i3 = eVar.n;
                e.b bVar3 = e.b.WRAP_CONTENT;
                boolean z2 = bVar == bVar3 || (bVar == e.b.MATCH_CONSTRAINT && i2 == 1);
                if (bVar2 == bVar3 || (bVar2 == e.b.MATCH_CONSTRAINT && i3 == 1)) {
                    z = true;
                }
                g gVar2 = eVar.f2443f.f2517e;
                boolean z3 = gVar2.j;
                g gVar3 = eVar.f2444g.f2517e;
                boolean z4 = gVar3.j;
                if (z3 && z4) {
                    e.b bVar4 = e.b.FIXED;
                    l(eVar, bVar4, gVar2.f2492g, bVar4, gVar3.f2492g);
                    eVar.f2439b = true;
                } else if (z3 && z) {
                    l(eVar, e.b.FIXED, gVar2.f2492g, bVar3, gVar3.f2492g);
                    if (bVar2 == e.b.MATCH_CONSTRAINT) {
                        eVar.f2444g.f2517e.m = eVar.w();
                    } else {
                        eVar.f2444g.f2517e.d(eVar.w());
                        eVar.f2439b = true;
                    }
                } else if (z4 && z2) {
                    l(eVar, bVar3, gVar2.f2492g, e.b.FIXED, gVar3.f2492g);
                    if (bVar == e.b.MATCH_CONSTRAINT) {
                        eVar.f2443f.f2517e.m = eVar.Q();
                    } else {
                        eVar.f2443f.f2517e.d(eVar.Q());
                        eVar.f2439b = true;
                    }
                }
                if (eVar.f2439b && (gVar = eVar.f2444g.l) != null) {
                    gVar.d(eVar.o());
                }
            }
        }
    }

    public void n(b.InterfaceC0038b interfaceC0038b) {
        this.f2483g = interfaceC0038b;
    }
}
