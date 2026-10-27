package b.b.g.d;

import b.b.g.n.q.u;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: LightModelStudy.java */
/* JADX INFO: loaded from: classes2.dex */
public class j extends l {
    @Override // b.b.g.d.l
    public boolean a() {
        return i() || h() || j() || g() || d() || c() || b() || p() || o();
    }

    public boolean b() {
        int iE0 = u.I().e0();
        BydLogHelper.logD(j.class.getSimpleName(), "getAfsSupport  :  ,state " + iE0);
        return iE0 == 1;
    }

    public boolean c() {
        return false;
    }

    public boolean d() {
        int iP0 = u.I().p0();
        BydLogHelper.logD(j.class.getSimpleName(), "getGusetLightSupport  :  ,state " + iP0);
        return iP0 == 2;
    }

    public int e() {
        int iG = u.I().G();
        BydLogHelper.logD(j.class.getSimpleName(), "getHeadlampLevelConfig  :  ,state " + iG);
        return iG;
    }

    public int f() {
        int iF = b.b.g.n.q.m.p0().f();
        BydLogHelper.logD(j.class.getSimpleName(), "getIALConfig  :  ,state " + iF);
        return iF;
    }

    public boolean g() {
        int iG0 = u.I().g0();
        BydLogHelper.logD(j.class.getSimpleName(), "getLampSignalSupport  :  ,state " + iG0);
        return iG0 == 1;
    }

    public boolean h() {
        int iP = b.b.g.n.q.m.p0().p();
        BydLogHelper.logD(j.class.getSimpleName(), "getLightBackSupport  :  ,state " + iP);
        return iP != 0;
    }

    public boolean i() {
        int iH = u.I().H();
        BydLogHelper.logD(j.class.getSimpleName(), "getLightHSupport  :  ,state " + iH);
        return iH == 1;
    }

    public boolean j() {
        int iW0 = b.b.g.n.q.m.p0().w0();
        BydLogHelper.logD(j.class.getSimpleName(), "getLightLeaveSupport  :  ,state " + iW0);
        return iW0 != 0;
    }

    public boolean k() {
        return f() > 1;
    }

    public boolean l() {
        int iG = b.b.g.n.q.m.p0().g();
        BydLogHelper.logD(j.class.getSimpleName(), "isCanSetLight  :  ,state " + iG);
        return iG == 2;
    }

    public boolean m() {
        return n() && b.b.g.n.q.i.k().h() == 1;
    }

    public boolean n() {
        return u.I().Q() == 7;
    }

    public boolean o() {
        return n() || m() || c();
    }

    public boolean p() {
        return k() && l();
    }
}
