package b.b.g.d;

import b.b.g.n.q.u;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: ComModelStudy.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends l {
    @Override // b.b.g.d.l
    public boolean a() {
        return d() || c() || g() || j() || e() || k() != 0 || f() || i() || h();
    }

    public int b() {
        int iQ0 = b.b.g.n.q.m.p0().Q0();
        int iP0 = b.b.g.n.q.m.p0().P0();
        BydLogHelper.logD(d.class.getSimpleName(), "rearLeft , " + iQ0 + ", rearRight " + iP0);
        boolean z = iQ0 == 1 || iQ0 == 2;
        boolean z2 = iP0 == 1 || iP0 == 2;
        BydLogHelper.logD(d.class.getSimpleName(), "isRearLeft , " + z + ", isRearRight " + z2);
        if (z && z2) {
            return 3;
        }
        if (z2 || !z) {
            return (!z2 || z) ? 4 : 2;
        }
        return 1;
    }

    public boolean c() {
        int iB = b();
        BydLogHelper.logD(d.class.getSimpleName(), "isMirrorFoldSup  :  ,value " + iB);
        return iB == 3 || iB == 1 || iB == 2;
    }

    public boolean d() {
        int iD = u.I().d();
        BydLogHelper.logD(d.class.getSimpleName(), "isAutoRainWiperSup  :  ,state " + iD);
        return iD == 1;
    }

    public boolean e() {
        int iJ = b.b.g.n.q.m.p0().j();
        BydLogHelper.logD(d.class.getSimpleName(), "isMirrorFoldSup  :  ,state " + iJ);
        return iJ == 2;
    }

    public boolean f() {
        int iE = b.b.g.n.q.b.c().e(36);
        BydLogHelper.logD(d.class.getSimpleName(), "isSafeBeltSupport  :  ,state " + iE);
        return iE == 1;
    }

    public boolean g() {
        int iH = b.b.g.n.q.m.p0().H();
        BydLogHelper.logD(d.class.getSimpleName(), "isSeatWelComeSup  :  ,state_437 " + iH);
        if (iH == 1 || iH == 2) {
            return true;
        }
        int iG = b.b.g.n.q.m.p0().G();
        BydLogHelper.logD(d.class.getSimpleName(), "isSeatWelComeSup  :  ,state " + iG);
        return iG == 1 || iG == 2;
    }

    public boolean h() {
        return u.I().a0() == 1;
    }

    public boolean i() {
        return u.I().q0() == 0;
    }

    public boolean j() {
        int iC0 = u.I().c0();
        BydLogHelper.logD(d.class.getSimpleName(), "isWheelRestoreSup  :  ,state " + iC0);
        return iC0 == 1 || iC0 == 2;
    }

    public int k() {
        int iR0;
        int iS0;
        int iT0;
        if (b.b.g.r.e.b(174)) {
            iR0 = b.b.g.n.q.m.p0().R0();
            BydLogHelper.logD(d.class.getSimpleName(), "rearviewSupMode  :  ,state437 " + iR0);
        } else {
            iR0 = 0;
        }
        if (b.b.g.r.e.b(67)) {
            iS0 = b.b.g.n.q.m.p0().S0();
            iT0 = b.b.g.n.q.m.p0().T0();
            BydLogHelper.logD(d.class.getSimpleName(), "rearviewSupMode  :  ,state " + iS0 + ", stateSet " + iT0);
        } else {
            iS0 = 0;
            iT0 = 0;
        }
        if (iR0 == 2 || iR0 == 1) {
            return 1;
        }
        if (iS0 == 1 || iS0 == 2) {
            return (iT0 == 1 || iT0 == 2) ? 2 : 1;
        }
        return 0;
    }
}
