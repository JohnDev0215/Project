package b.e.b.k.n;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ChainRun.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends m {
    ArrayList<m> k;
    private int l;

    public c(b.e.b.k.e eVar, int i2) {
        super(eVar);
        this.k = new ArrayList<>();
        this.f2518f = i2;
        q();
    }

    private void q() {
        b.e.b.k.e eVar;
        b.e.b.k.e eVar2 = this.f2514b;
        b.e.b.k.e eVarI = eVar2.I(this.f2518f);
        while (true) {
            b.e.b.k.e eVar3 = eVarI;
            eVar = eVar2;
            eVar2 = eVar3;
            if (eVar2 == null) {
                break;
            } else {
                eVarI = eVar2.I(this.f2518f);
            }
        }
        this.f2514b = eVar;
        this.k.add(eVar.K(this.f2518f));
        b.e.b.k.e eVarG = eVar.G(this.f2518f);
        while (eVarG != null) {
            this.k.add(eVarG.K(this.f2518f));
            eVarG = eVarG.G(this.f2518f);
        }
        for (m mVar : this.k) {
            int i2 = this.f2518f;
            if (i2 == 0) {
                mVar.f2514b.f2441d = this;
            } else if (i2 == 1) {
                mVar.f2514b.f2442e = this;
            }
        }
        if ((this.f2518f == 0 && ((b.e.b.k.f) this.f2514b.H()).c1()) && this.k.size() > 1) {
            ArrayList<m> arrayList = this.k;
            this.f2514b = arrayList.get(arrayList.size() - 1).f2514b;
        }
        this.l = this.f2518f == 0 ? this.f2514b.y() : this.f2514b.M();
    }

    private b.e.b.k.e r() {
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            m mVar = this.k.get(i2);
            if (mVar.f2514b.P() != 8) {
                return mVar.f2514b;
            }
        }
        return null;
    }

    private b.e.b.k.e s() {
        for (int size = this.k.size() - 1; size >= 0; size--) {
            m mVar = this.k.get(size);
            if (mVar.f2514b.P() != 8) {
                return mVar.f2514b;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:120:0x01cd A[PHI: r1 r22 r23 r24
  0x01cd: PHI (r1v62 int) = (r1v60 int), (r1v68 int) binds: [B:119:0x01cb, B:110:0x01a5] A[DONT_GENERATE, DONT_INLINE]
  0x01cd: PHI (r22v3 float) = (r22v2 float), (r22v5 float) binds: [B:119:0x01cb, B:110:0x01a5] A[DONT_GENERATE, DONT_INLINE]
  0x01cd: PHI (r23v6 boolean) = (r23v5 boolean), (r23v8 boolean) binds: [B:119:0x01cb, B:110:0x01a5] A[DONT_GENERATE, DONT_INLINE]
  0x01cd: PHI (r24v6 int) = (r24v5 int), (r24v8 int) binds: [B:119:0x01cb, B:110:0x01a5] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00eb  */
    @Override // b.e.b.k.n.m, b.e.b.k.n.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(b.e.b.k.n.d r26) {
        /*
            Method dump skipped, instruction units count: 1088
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.e.b.k.n.c.a(b.e.b.k.n.d):void");
    }

    @Override // b.e.b.k.n.m
    void d() {
        Iterator<m> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().d();
        }
        int size = this.k.size();
        if (size < 1) {
            return;
        }
        b.e.b.k.e eVar = this.k.get(0).f2514b;
        b.e.b.k.e eVar2 = this.k.get(size - 1).f2514b;
        if (this.f2518f == 0) {
            b.e.b.k.d dVar = eVar.E;
            b.e.b.k.d dVar2 = eVar2.G;
            f fVarI = i(dVar, 0);
            int iC = dVar.c();
            b.e.b.k.e eVarR = r();
            if (eVarR != null) {
                iC = eVarR.E.c();
            }
            if (fVarI != null) {
                b(this.f2520h, fVarI, iC);
            }
            f fVarI2 = i(dVar2, 0);
            int iC2 = dVar2.c();
            b.e.b.k.e eVarS = s();
            if (eVarS != null) {
                iC2 = eVarS.G.c();
            }
            if (fVarI2 != null) {
                b(this.f2521i, fVarI2, -iC2);
            }
        } else {
            b.e.b.k.d dVar3 = eVar.F;
            b.e.b.k.d dVar4 = eVar2.H;
            f fVarI3 = i(dVar3, 1);
            int iC3 = dVar3.c();
            b.e.b.k.e eVarR2 = r();
            if (eVarR2 != null) {
                iC3 = eVarR2.F.c();
            }
            if (fVarI3 != null) {
                b(this.f2520h, fVarI3, iC3);
            }
            f fVarI4 = i(dVar4, 1);
            int iC4 = dVar4.c();
            b.e.b.k.e eVarS2 = s();
            if (eVarS2 != null) {
                iC4 = eVarS2.H.c();
            }
            if (fVarI4 != null) {
                b(this.f2521i, fVarI4, -iC4);
            }
        }
        this.f2520h.f2486a = this;
        this.f2521i.f2486a = this;
    }

    @Override // b.e.b.k.n.m
    public void e() {
        for (int i2 = 0; i2 < this.k.size(); i2++) {
            this.k.get(i2).e();
        }
    }

    @Override // b.e.b.k.n.m
    void f() {
        this.f2515c = null;
        Iterator<m> it = this.k.iterator();
        while (it.hasNext()) {
            it.next().f();
        }
    }

    @Override // b.e.b.k.n.m
    public long j() {
        int size = this.k.size();
        long j = 0;
        for (int i2 = 0; i2 < size; i2++) {
            m mVar = this.k.get(i2);
            j = j + ((long) mVar.f2520h.f2491f) + mVar.j() + ((long) mVar.f2521i.f2491f);
        }
        return j;
    }

    @Override // b.e.b.k.n.m
    boolean m() {
        int size = this.k.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!this.k.get(i2).m()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChainRun ");
        sb.append(this.f2518f == 0 ? "horizontal : " : "vertical : ");
        String string = sb.toString();
        Iterator<m> it = this.k.iterator();
        while (it.hasNext()) {
            String str = string + "<";
            string = (str + it.next()) + "> ";
        }
        return string;
    }
}
