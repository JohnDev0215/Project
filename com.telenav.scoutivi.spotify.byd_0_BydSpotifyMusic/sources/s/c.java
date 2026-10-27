package s;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: ChainRun.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends p {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    ArrayList<p> f9396k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f9397l;

    public c(r.e eVar, int i7) {
        super(eVar);
        this.f9396k = new ArrayList<>();
        this.f9457f = i7;
        q();
    }

    private void q() {
        r.e eVar;
        r.e eVar2 = this.f9453b;
        r.e eVarN = eVar2.N(this.f9457f);
        while (true) {
            r.e eVar3 = eVarN;
            eVar = eVar2;
            eVar2 = eVar3;
            if (eVar2 == null) {
                break;
            } else {
                eVarN = eVar2.N(this.f9457f);
            }
        }
        this.f9453b = eVar;
        this.f9396k.add(eVar.P(this.f9457f));
        r.e eVarL = eVar.L(this.f9457f);
        while (eVarL != null) {
            this.f9396k.add(eVarL.P(this.f9457f));
            eVarL = eVarL.L(this.f9457f);
        }
        for (p pVar : this.f9396k) {
            int i7 = this.f9457f;
            if (i7 == 0) {
                pVar.f9453b.f9208c = this;
            } else if (i7 == 1) {
                pVar.f9453b.f9210d = this;
            }
        }
        if ((this.f9457f == 0 && ((r.f) this.f9453b.M()).S1()) && this.f9396k.size() > 1) {
            ArrayList<p> arrayList = this.f9396k;
            this.f9453b = arrayList.get(arrayList.size() - 1).f9453b;
        }
        this.f9397l = this.f9457f == 0 ? this.f9453b.B() : this.f9453b.U();
    }

    private r.e r() {
        for (int i7 = 0; i7 < this.f9396k.size(); i7++) {
            p pVar = this.f9396k.get(i7);
            if (pVar.f9453b.X() != 8) {
                return pVar.f9453b;
            }
        }
        return null;
    }

    private r.e s() {
        for (int size = this.f9396k.size() - 1; size >= 0; size--) {
            p pVar = this.f9396k.get(size);
            if (pVar.f9453b.X() != 8) {
                return pVar.f9453b;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00e9  */
    @Override // s.p, s.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(s.d r27) {
        /*
            Method dump skipped, instruction units count: 1064
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: s.c.a(s.d):void");
    }

    @Override // s.p
    void d() {
        Iterator<p> it = this.f9396k.iterator();
        while (it.hasNext()) {
            it.next().d();
        }
        int size = this.f9396k.size();
        if (size < 1) {
            return;
        }
        r.e eVar = this.f9396k.get(0).f9453b;
        r.e eVar2 = this.f9396k.get(size - 1).f9453b;
        if (this.f9457f == 0) {
            r.d dVar = eVar.Q;
            r.d dVar2 = eVar2.S;
            f fVarI = i(dVar, 0);
            int iF = dVar.f();
            r.e eVarR = r();
            if (eVarR != null) {
                iF = eVarR.Q.f();
            }
            if (fVarI != null) {
                b(this.f9459h, fVarI, iF);
            }
            f fVarI2 = i(dVar2, 0);
            int iF2 = dVar2.f();
            r.e eVarS = s();
            if (eVarS != null) {
                iF2 = eVarS.S.f();
            }
            if (fVarI2 != null) {
                b(this.f9460i, fVarI2, -iF2);
            }
        } else {
            r.d dVar3 = eVar.R;
            r.d dVar4 = eVar2.T;
            f fVarI3 = i(dVar3, 1);
            int iF3 = dVar3.f();
            r.e eVarR2 = r();
            if (eVarR2 != null) {
                iF3 = eVarR2.R.f();
            }
            if (fVarI3 != null) {
                b(this.f9459h, fVarI3, iF3);
            }
            f fVarI4 = i(dVar4, 1);
            int iF4 = dVar4.f();
            r.e eVarS2 = s();
            if (eVarS2 != null) {
                iF4 = eVarS2.T.f();
            }
            if (fVarI4 != null) {
                b(this.f9460i, fVarI4, -iF4);
            }
        }
        this.f9459h.f9407a = this;
        this.f9460i.f9407a = this;
    }

    @Override // s.p
    public void e() {
        for (int i7 = 0; i7 < this.f9396k.size(); i7++) {
            this.f9396k.get(i7).e();
        }
    }

    @Override // s.p
    void f() {
        this.f9454c = null;
        Iterator<p> it = this.f9396k.iterator();
        while (it.hasNext()) {
            it.next().f();
        }
    }

    @Override // s.p
    public long j() {
        int size = this.f9396k.size();
        long j7 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            p pVar = this.f9396k.get(i7);
            j7 = j7 + ((long) pVar.f9459h.f9412f) + pVar.j() + ((long) pVar.f9460i.f9412f);
        }
        return j7;
    }

    @Override // s.p
    boolean m() {
        int size = this.f9396k.size();
        for (int i7 = 0; i7 < size; i7++) {
            if (!this.f9396k.get(i7).m()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.f9457f == 0 ? "horizontal : " : "vertical : ");
        for (p pVar : this.f9396k) {
            sb.append("<");
            sb.append(pVar);
            sb.append("> ");
        }
        return sb.toString();
    }
}
