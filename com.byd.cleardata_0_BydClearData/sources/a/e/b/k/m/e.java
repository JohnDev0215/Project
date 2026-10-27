package a.e.b.k.m;

import a.e.b.k.e;
import a.e.b.k.m.b;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a.e.b.k.f f230a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private a.e.b.k.f f233d;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f231b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f232c = true;
    private ArrayList<m> e = new ArrayList<>();
    private ArrayList<k> f = new ArrayList<>();
    private b.InterfaceC0006b g = null;
    private b.a h = new b.a();
    ArrayList<k> i = new ArrayList<>();

    public e(a.e.b.k.f fVar) {
        this.f230a = fVar;
        this.f233d = fVar;
    }

    private void a(f fVar, int i, int i2, f fVar2, ArrayList<k> arrayList, k kVar) {
        m mVar = fVar.f237d;
        if (mVar.f249c == null) {
            a.e.b.k.f fVar3 = this.f230a;
            if (mVar == fVar3.f || mVar == fVar3.g) {
                return;
            }
            if (kVar == null) {
                kVar = new k(mVar, i2);
                arrayList.add(kVar);
            }
            mVar.f249c = kVar;
            kVar.a(mVar);
            for (d dVar : mVar.h.k) {
                if (dVar instanceof f) {
                    a((f) dVar, i, 0, fVar2, arrayList, kVar);
                }
            }
            for (d dVar2 : mVar.i.k) {
                if (dVar2 instanceof f) {
                    a((f) dVar2, i, 1, fVar2, arrayList, kVar);
                }
            }
            if (i == 1 && (mVar instanceof l)) {
                for (d dVar3 : ((l) mVar).k.k) {
                    if (dVar3 instanceof f) {
                        a((f) dVar3, i, 2, fVar2, arrayList, kVar);
                    }
                }
            }
            for (f fVar4 : mVar.h.l) {
                if (fVar4 == fVar2) {
                    kVar.f244c = true;
                }
                a(fVar4, i, 0, fVar2, arrayList, kVar);
            }
            for (f fVar5 : mVar.i.l) {
                if (fVar5 == fVar2) {
                    kVar.f244c = true;
                }
                a(fVar5, i, 1, fVar2, arrayList, kVar);
            }
            if (i == 1 && (mVar instanceof l)) {
                Iterator<f> it = ((l) mVar).k.l.iterator();
                while (it.hasNext()) {
                    a(it.next(), i, 2, fVar2, arrayList, kVar);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:124:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x01c3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0008 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x014a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean b(a.e.b.k.f r17) {
        /*
            Method dump skipped, instruction units count: 621
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.e.b.k.m.e.b(a.e.b.k.f):boolean");
    }

    private int e(a.e.b.k.f fVar, int i) {
        int size = this.i.size();
        long jMax = 0;
        for (int i2 = 0; i2 < size; i2++) {
            jMax = Math.max(jMax, this.i.get(i2).b(fVar, i));
        }
        return (int) jMax;
    }

    private void i(m mVar, int i, ArrayList<k> arrayList) {
        for (d dVar : mVar.h.k) {
            if (dVar instanceof f) {
                a((f) dVar, i, 0, mVar.i, arrayList, null);
            } else if (dVar instanceof m) {
                a(((m) dVar).h, i, 0, mVar.i, arrayList, null);
            }
        }
        for (d dVar2 : mVar.i.k) {
            if (dVar2 instanceof f) {
                a((f) dVar2, i, 1, mVar.h, arrayList, null);
            } else if (dVar2 instanceof m) {
                a(((m) dVar2).i, i, 1, mVar.h, arrayList, null);
            }
        }
        if (i == 1) {
            for (d dVar3 : ((l) mVar).k.k) {
                if (dVar3 instanceof f) {
                    a((f) dVar3, i, 2, null, arrayList, null);
                }
            }
        }
    }

    private void l(a.e.b.k.e eVar, e.b bVar, int i, e.b bVar2, int i2) {
        b.a aVar = this.h;
        aVar.f226a = bVar;
        aVar.f227b = bVar2;
        aVar.f228c = i;
        aVar.f229d = i2;
        this.g.a(eVar, aVar);
        eVar.B0(this.h.e);
        eVar.e0(this.h.f);
        eVar.d0(this.h.h);
        eVar.Y(this.h.g);
    }

    public void c() {
        d(this.e);
        this.i.clear();
        k.f242a = 0;
        i(this.f230a.f, 0, this.i);
        i(this.f230a.g, 1, this.i);
        this.f231b = false;
    }

    public void d(ArrayList<m> arrayList) {
        m hVar;
        arrayList.clear();
        this.f233d.f.f();
        this.f233d.g.f();
        arrayList.add(this.f233d.f);
        arrayList.add(this.f233d.g);
        HashSet hashSet = null;
        for (a.e.b.k.e eVar : this.f233d.w0) {
            if (eVar instanceof a.e.b.k.g) {
                hVar = new h(eVar);
            } else {
                if (eVar.T()) {
                    if (eVar.f215d == null) {
                        eVar.f215d = new c(eVar, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(eVar.f215d);
                } else {
                    arrayList.add(eVar.f);
                }
                if (eVar.V()) {
                    if (eVar.e == null) {
                        eVar.e = new c(eVar, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(eVar.e);
                } else {
                    arrayList.add(eVar.g);
                }
                if (eVar instanceof a.e.b.k.i) {
                    hVar = new i(eVar);
                }
            }
            arrayList.add(hVar);
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        Iterator<m> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().f();
        }
        for (m mVar : arrayList) {
            if (mVar.f248b != this.f233d) {
                mVar.d();
            }
        }
    }

    public boolean f(boolean z) {
        boolean z2;
        boolean z3 = true;
        boolean z4 = z & true;
        if (this.f231b || this.f232c) {
            for (a.e.b.k.e eVar : this.f230a.w0) {
                eVar.f213b = false;
                eVar.f.r();
                eVar.g.q();
            }
            a.e.b.k.f fVar = this.f230a;
            fVar.f213b = false;
            fVar.f.r();
            this.f230a.g.q();
            this.f232c = false;
        }
        if (b(this.f233d)) {
            return false;
        }
        this.f230a.C0(0);
        this.f230a.D0(0);
        e.b bVarQ = this.f230a.q(0);
        e.b bVarQ2 = this.f230a.q(1);
        if (this.f231b) {
            c();
        }
        int iO = this.f230a.O();
        int iP = this.f230a.P();
        this.f230a.f.h.d(iO);
        this.f230a.g.h.d(iP);
        m();
        e.b bVar = e.b.WRAP_CONTENT;
        if (bVarQ == bVar || bVarQ2 == bVar) {
            if (z4) {
                Iterator<m> it = this.e.iterator();
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
            if (z4 && bVarQ == e.b.WRAP_CONTENT) {
                this.f230a.i0(e.b.FIXED);
                a.e.b.k.f fVar2 = this.f230a;
                fVar2.B0(e(fVar2, 0));
                a.e.b.k.f fVar3 = this.f230a;
                fVar3.f.e.d(fVar3.N());
            }
            if (z4 && bVarQ2 == e.b.WRAP_CONTENT) {
                this.f230a.x0(e.b.FIXED);
                a.e.b.k.f fVar4 = this.f230a;
                fVar4.e0(e(fVar4, 1));
                a.e.b.k.f fVar5 = this.f230a;
                fVar5.g.e.d(fVar5.t());
            }
        }
        a.e.b.k.f fVar6 = this.f230a;
        e.b[] bVarArr = fVar6.N;
        e.b bVar2 = bVarArr[0];
        e.b bVar3 = e.b.FIXED;
        if (bVar2 == bVar3 || bVarArr[0] == e.b.MATCH_PARENT) {
            int iN = fVar6.N() + iO;
            this.f230a.f.i.d(iN);
            this.f230a.f.e.d(iN - iO);
            m();
            a.e.b.k.f fVar7 = this.f230a;
            e.b[] bVarArr2 = fVar7.N;
            if (bVarArr2[1] == bVar3 || bVarArr2[1] == e.b.MATCH_PARENT) {
                int iT = fVar7.t() + iP;
                this.f230a.g.i.d(iT);
                this.f230a.g.e.d(iT - iP);
            }
            m();
            z2 = true;
        } else {
            z2 = false;
        }
        for (m mVar : this.e) {
            if (mVar.f248b != this.f230a || mVar.g) {
                mVar.e();
            }
        }
        for (m mVar2 : this.e) {
            if (z2 || mVar2.f248b != this.f230a) {
                if (!mVar2.h.j || ((!mVar2.i.j && !(mVar2 instanceof h)) || (!mVar2.e.j && !(mVar2 instanceof c) && !(mVar2 instanceof h)))) {
                    z3 = false;
                    break;
                }
            }
        }
        this.f230a.i0(bVarQ);
        this.f230a.x0(bVarQ2);
        return z3;
    }

    public boolean g(boolean z) {
        if (this.f231b) {
            for (a.e.b.k.e eVar : this.f230a.w0) {
                eVar.f213b = false;
                j jVar = eVar.f;
                jVar.e.j = false;
                jVar.g = false;
                jVar.r();
                l lVar = eVar.g;
                lVar.e.j = false;
                lVar.g = false;
                lVar.q();
            }
            a.e.b.k.f fVar = this.f230a;
            fVar.f213b = false;
            j jVar2 = fVar.f;
            jVar2.e.j = false;
            jVar2.g = false;
            jVar2.r();
            l lVar2 = this.f230a.g;
            lVar2.e.j = false;
            lVar2.g = false;
            lVar2.q();
            c();
        }
        if (b(this.f233d)) {
            return false;
        }
        this.f230a.C0(0);
        this.f230a.D0(0);
        this.f230a.f.h.d(0);
        this.f230a.g.h.d(0);
        return true;
    }

    public boolean h(boolean z, int i) {
        boolean z2;
        e.b bVar;
        g gVar;
        int iT;
        boolean z3 = true;
        boolean z4 = z & true;
        e.b bVarQ = this.f230a.q(0);
        e.b bVarQ2 = this.f230a.q(1);
        int iO = this.f230a.O();
        int iP = this.f230a.P();
        if (z4 && (bVarQ == (bVar = e.b.WRAP_CONTENT) || bVarQ2 == bVar)) {
            Iterator<m> it = this.e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                m next = it.next();
                if (next.f == i && !next.m()) {
                    z4 = false;
                    break;
                }
            }
            if (i == 0) {
                if (z4 && bVarQ == e.b.WRAP_CONTENT) {
                    this.f230a.i0(e.b.FIXED);
                    a.e.b.k.f fVar = this.f230a;
                    fVar.B0(e(fVar, 0));
                    a.e.b.k.f fVar2 = this.f230a;
                    gVar = fVar2.f.e;
                    iT = fVar2.N();
                    gVar.d(iT);
                }
            } else if (z4 && bVarQ2 == e.b.WRAP_CONTENT) {
                this.f230a.x0(e.b.FIXED);
                a.e.b.k.f fVar3 = this.f230a;
                fVar3.e0(e(fVar3, 1));
                a.e.b.k.f fVar4 = this.f230a;
                gVar = fVar4.g.e;
                iT = fVar4.t();
                gVar.d(iT);
            }
        }
        a.e.b.k.f fVar5 = this.f230a;
        if (i == 0) {
            e.b[] bVarArr = fVar5.N;
            if (bVarArr[0] == e.b.FIXED || bVarArr[0] == e.b.MATCH_PARENT) {
                int iN = fVar5.N() + iO;
                this.f230a.f.i.d(iN);
                this.f230a.f.e.d(iN - iO);
                z2 = true;
            }
            z2 = false;
        } else {
            e.b[] bVarArr2 = fVar5.N;
            if (bVarArr2[1] == e.b.FIXED || bVarArr2[1] == e.b.MATCH_PARENT) {
                int iT2 = fVar5.t() + iP;
                this.f230a.g.i.d(iT2);
                this.f230a.g.e.d(iT2 - iP);
                z2 = true;
            }
            z2 = false;
        }
        m();
        for (m mVar : this.e) {
            if (mVar.f == i && (mVar.f248b != this.f230a || mVar.g)) {
                mVar.e();
            }
        }
        for (m mVar2 : this.e) {
            if (mVar2.f == i && (z2 || mVar2.f248b != this.f230a)) {
                if (!mVar2.h.j || !mVar2.i.j || (!(mVar2 instanceof c) && !mVar2.e.j)) {
                    z3 = false;
                    break;
                }
            }
        }
        this.f230a.i0(bVarQ);
        this.f230a.x0(bVarQ2);
        return z3;
    }

    public void j() {
        this.f231b = true;
    }

    public void k() {
        this.f232c = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00b2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0008 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m() {
        /*
            r12 = this;
            a.e.b.k.f r0 = r12.f230a
            java.util.ArrayList<a.e.b.k.e> r0 = r0.w0
            java.util.Iterator r0 = r0.iterator()
        L8:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto Lc1
            java.lang.Object r1 = r0.next()
            a.e.b.k.e r1 = (a.e.b.k.e) r1
            boolean r2 = r1.f213b
            if (r2 == 0) goto L19
            goto L8
        L19:
            a.e.b.k.e$b[] r2 = r1.N
            r3 = 0
            r8 = r2[r3]
            r9 = 1
            r10 = r2[r9]
            int r2 = r1.m
            int r4 = r1.n
            a.e.b.k.e$b r6 = a.e.b.k.e.b.WRAP_CONTENT
            if (r8 == r6) goto L32
            a.e.b.k.e$b r5 = a.e.b.k.e.b.MATCH_CONSTRAINT
            if (r8 != r5) goto L30
            if (r2 != r9) goto L30
            goto L32
        L30:
            r2 = r3
            goto L33
        L32:
            r2 = r9
        L33:
            if (r10 == r6) goto L3b
            a.e.b.k.e$b r5 = a.e.b.k.e.b.MATCH_CONSTRAINT
            if (r10 != r5) goto L3c
            if (r4 != r9) goto L3c
        L3b:
            r3 = r9
        L3c:
            a.e.b.k.m.j r4 = r1.f
            a.e.b.k.m.g r4 = r4.e
            boolean r5 = r4.j
            a.e.b.k.m.l r7 = r1.g
            a.e.b.k.m.g r7 = r7.e
            boolean r11 = r7.j
            if (r5 == 0) goto L5b
            if (r11 == 0) goto L5b
            a.e.b.k.e$b r6 = a.e.b.k.e.b.FIXED
            int r5 = r4.g
            int r7 = r7.g
            r2 = r12
            r3 = r1
            r4 = r6
            r2.l(r3, r4, r5, r6, r7)
        L58:
            r1.f213b = r9
            goto Lae
        L5b:
            if (r5 == 0) goto L87
            if (r3 == 0) goto L87
            a.e.b.k.e$b r5 = a.e.b.k.e.b.FIXED
            int r8 = r4.g
            int r7 = r7.g
            r2 = r12
            r3 = r1
            r4 = r5
            r5 = r8
            r2.l(r3, r4, r5, r6, r7)
            a.e.b.k.e$b r2 = a.e.b.k.e.b.MATCH_CONSTRAINT
            if (r10 != r2) goto L7b
            a.e.b.k.m.l r2 = r1.g
            a.e.b.k.m.g r2 = r2.e
            int r3 = r1.t()
        L78:
            r2.m = r3
            goto Lae
        L7b:
            a.e.b.k.m.l r2 = r1.g
            a.e.b.k.m.g r2 = r2.e
            int r3 = r1.t()
        L83:
            r2.d(r3)
            goto L58
        L87:
            if (r11 == 0) goto Lae
            if (r2 == 0) goto Lae
            int r5 = r4.g
            a.e.b.k.e$b r10 = a.e.b.k.e.b.FIXED
            int r7 = r7.g
            r2 = r12
            r3 = r1
            r4 = r6
            r6 = r10
            r2.l(r3, r4, r5, r6, r7)
            a.e.b.k.e$b r2 = a.e.b.k.e.b.MATCH_CONSTRAINT
            if (r8 != r2) goto La5
            a.e.b.k.m.j r2 = r1.f
            a.e.b.k.m.g r2 = r2.e
            int r3 = r1.N()
            goto L78
        La5:
            a.e.b.k.m.j r2 = r1.f
            a.e.b.k.m.g r2 = r2.e
            int r3 = r1.N()
            goto L83
        Lae:
            boolean r2 = r1.f213b
            if (r2 == 0) goto L8
            a.e.b.k.m.l r2 = r1.g
            a.e.b.k.m.g r2 = r2.l
            if (r2 == 0) goto L8
            int r1 = r1.l()
            r2.d(r1)
            goto L8
        Lc1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: a.e.b.k.m.e.m():void");
    }

    public void n(b.InterfaceC0006b interfaceC0006b) {
        this.g = interfaceC0006b;
    }
}
