package b.b.g.d;

import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import b.b.g.n.q.u;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: DoorModelStudy.java */
/* JADX INFO: loaded from: classes2.dex */
public class f extends l {
    @Override // b.b.g.d.l
    public boolean a() {
        return c() || e() || n() || o() || m() || l() || p() || t() || r() || q() || h() || i() || s() || g() || j() || k() || b() != 0 || f();
    }

    public int b() {
        int iV = u.I().v();
        BydLogHelper.logD(f.class.getSimpleName(), "getDigitalMode  :  ,state " + iV);
        return iV;
    }

    public boolean c() {
        int iD = b.b.g.n.q.m.p0().D();
        BydLogHelper.logD(f.class.getSimpleName(), "isBackDoorSup  :  ,state " + iD);
        return iD == 1;
    }

    public boolean d() {
        return false;
    }

    public boolean e() {
        int iE = b.b.g.n.q.m.p0().E();
        BydLogHelper.logD(f.class.getSimpleName(), "isDoorknobSup  :  ,state " + iE);
        return iE >= 1 && iE <= 4;
    }

    public boolean f() {
        int iB = b.b.g.n.q.b.c().b();
        BydLogHelper.logD(f.class.getSimpleName(), "isElecSlideDoor  :  ,state " + iB);
        return iB == 1 || iB == 2;
    }

    public boolean g() {
        int iC0 = b.b.g.n.q.m.p0().c0();
        BydLogHelper.logD(f.class.getSimpleName(), "isHighSpeedLockSup  :  ,state " + iC0);
        return iC0 == 1 || iC0 == 2;
    }

    public boolean h() {
        int iL = b.b.g.n.q.m.p0().l();
        BydLogHelper.logD(f.class.getSimpleName(), "isIntelChargeSup  :  ,state " + iL);
        return iL == 2 || iL == 1;
    }

    public boolean i() {
        int iK = b.b.g.n.q.m.p0().k();
        BydLogHelper.logD(f.class.getSimpleName(), "isIntelOutChargeSup  :  ,state " + iK);
        return iK == 2 || iK == 1;
    }

    public boolean j() {
        int iR0 = b.b.g.n.q.m.p0().r0();
        BydLogHelper.logD(f.class.getSimpleName(), "isIntellLock  :  ,state " + iR0);
        return iR0 == 1 || iR0 == 2;
    }

    public boolean k() {
        int iS0 = b.b.g.n.q.m.p0().s0();
        BydLogHelper.logD(f.class.getSimpleName(), "isIntellUnLock  :  ,state " + iS0);
        return iS0 == 1 || iS0 == 2;
    }

    public boolean l() {
        int iA0 = b.b.g.n.q.m.p0().A0();
        BydLogHelper.logD(f.class.getSimpleName(), "isLPMicroCloseSup  :  ,state " + iA0);
        return iA0 == 1 || iA0 == 2;
    }

    public boolean m() {
        int iB0 = b.b.g.n.q.m.p0().B0();
        BydLogHelper.logD(f.class.getSimpleName(), "isLPMicroOpenSup  :  ,state " + iB0);
        return iB0 == 1 || iB0 == 2;
    }

    public boolean n() {
        int iF1 = b.b.g.n.q.m.p0().f1();
        BydLogHelper.logD(f.class.getSimpleName(), "isLPkeyCloseSup  :  ,state " + iF1);
        return iF1 == 1 || iF1 == 2;
    }

    public boolean o() {
        int iE1 = b.b.g.n.q.m.p0().e1();
        BydLogHelper.logD(f.class.getSimpleName(), "isLPkeyOpenSup  :  ,state " + iE1);
        return iE1 == 1 || iE1 == 2;
    }

    public boolean p() {
        int iZ0 = b.b.g.n.q.m.p0().z0();
        BydLogHelper.logD(f.class.getSimpleName(), "isLockByRemoteSup  :  ,state " + iZ0 + " isOnline : " + b.b.g.r.e.b(60));
        return b.b.g.r.e.b(60) && (iZ0 == 1 || iZ0 == 2);
    }

    public boolean q() {
        int iY0 = b.b.g.n.q.m.p0().y0();
        BydLogHelper.logD(f.class.getSimpleName(), "isLockRiseSup  :  ,state " + iY0);
        return iY0 == 1 || iY0 == 2;
    }

    public boolean r() {
        int iN0 = b.b.g.n.q.m.p0().N0();
        BydLogHelper.logD(f.class.getSimpleName(), "isRunLockedSup  :  ,state " + iN0);
        return iN0 == 1 || iN0 == 2;
    }

    public boolean s() {
        int iG1 = b.b.g.n.q.m.p0().g1();
        BydLogHelper.logD(f.class.getSimpleName(), "isRunCloseDoorSup  :  ,state " + iG1);
        int runingCloseDoorSupport = BYDAutoStudy.getInstance(b.b.a.a.a.b().a()).getRuningCloseDoorSupport();
        BydLogHelper.logD(f.class.getSimpleName(), "isRunCloseDoorSup  :  ,value " + runingCloseDoorSupport);
        return runingCloseDoorSupport >= 5 && runingCloseDoorSupport <= 9 && (iG1 == 1 || iG1 == 2);
    }

    public boolean t() {
        int iD0 = b.b.g.n.q.m.p0().D0();
        BydLogHelper.logD(f.class.getSimpleName(), "isRunLockedSup  :  ,value " + iD0);
        return b.b.g.n.q.b.c().e(60) != 0 && (iD0 == 1 || iD0 == 2) && !e();
    }
}
