package b.b.g.d;

import b.b.g.n.q.u;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: AiModelStudy.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends l {
    @Override // b.b.g.d.l
    public boolean a() {
        return e() || g() || f() || d() || c();
    }

    public int b() {
        int iT = u.I().t();
        int iU = u.I().u();
        BydLogHelper.logD(a.class.getSimpleName(), "getDiSusMode  :  ,isCanShow " + iT + " ,isCanFdShow : " + iU);
        int i = 1;
        if (iT != 1 && iU != 1) {
            i = 2;
            if (iT != 2 && iU != 2) {
                return 0;
            }
        }
        return i;
    }

    public boolean c() {
        boolean zN = u.I().n();
        BydLogHelper.logD(a.class.getSimpleName(), "isComSpeedUpSup  :  ,state " + zN);
        return zN || u.I().s() == 1;
    }

    public boolean d() {
        if (c()) {
            BydLogHelper.logD(a.class.getSimpleName(), "isComSpeedUpSup  :  false ");
            return false;
        }
        int iO = u.I().o();
        BydLogHelper.logD(a.class.getSimpleName(), "getComfortableParkingSupport  :  ,state " + iO);
        return iO == 1;
    }

    public boolean e() {
        boolean zA = u.I().A();
        boolean zB = u.I().B();
        BydLogHelper.logD(a.class.getSimpleName(), "isDynamicSup  :  ,canEnable " + zA + " ,canFdEnable : " + zB);
        return zA || zB;
    }

    public boolean f() {
        int iF = u.I().f();
        BydLogHelper.logD(a.class.getSimpleName(), "isStopHelpSup  :  ,state " + iF);
        return iF == 1 || iF == 2;
    }

    public boolean g() {
        int iB0 = u.I().b0();
        BydLogHelper.logD(a.class.getSimpleName(), "isTurnHelpSup  :  ,state " + iB0);
        return iB0 == 1;
    }
}
