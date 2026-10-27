package b.b.g.d;

import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: FreezerStudy.java */
/* JADX INFO: loaded from: classes2.dex */
public class g extends l {
    @Override // b.b.g.d.l
    public boolean a() {
        return j() || l() || h() || g() || i();
    }

    public int b() {
        int iV = b.b.g.n.q.m.p0().V();
        BydLogHelper.logD(g.class.getSimpleName(), "getFreezerColdSetAreaConfig  :  ,mode " + iV);
        return iV;
    }

    public int c() {
        return b.b.g.n.q.m.p0().U();
    }

    public int d() {
        return b.b.g.n.q.m.p0().a1();
    }

    public int e() {
        int iZ = b.b.g.n.q.m.p0().Z();
        BydLogHelper.logD(g.class.getSimpleName(), "getFreezerWorkType  :  ,mode " + iZ);
        return iZ;
    }

    public int f() {
        int iS = b.b.g.n.q.m.p0().S();
        BydLogHelper.logD(g.class.getSimpleName(), "getFridgeDelayedPowerOutage  :  ,mode " + iS);
        return iS;
    }

    public boolean g() {
        int iR = b.b.g.n.q.m.p0().R();
        BydLogHelper.logD(g.class.getSimpleName(), "getFridgeDelayedPowerOutage  :  ,mode " + iR);
        return iR == 1;
    }

    public boolean h() {
        int iR = b.b.g.n.q.m.p0().r();
        BydLogHelper.logD(g.class.getSimpleName(), "isDelayPowerOffShow  :  ,mode " + iR);
        return iR == 1;
    }

    public boolean i() {
        int iR = b.b.g.n.q.m.p0().r();
        BydLogHelper.logD(g.class.getSimpleName(), "getFridgeDelayedPowerOutage  :  ,value " + iR);
        if (iR != 1) {
            return false;
        }
        int iE = b.b.g.n.q.b.c().e(220);
        BydLogHelper.logD(g.class.getSimpleName(), "getFridgeDelayedPowerOutage  :  ,online " + iE);
        if (iE != 1) {
            return true;
        }
        int iA0 = b.b.g.n.q.m.p0().a0();
        BydLogHelper.logD(g.class.getSimpleName(), "getFridgeDelayedPowerOutage  :  ,value3D6 " + iA0);
        return iA0 == 0 || iA0 == 1 || iA0 == 3;
    }

    public boolean j() {
        int iC = c();
        BydLogHelper.logD(g.class.getSimpleName(), "isFreezerDoorShow  :  ,mode " + iC);
        return iC == 1 || iC == 3;
    }

    public boolean k() {
        return l();
    }

    public boolean l() {
        int iE = e();
        return iE == 1 || iE == 2;
    }
}
