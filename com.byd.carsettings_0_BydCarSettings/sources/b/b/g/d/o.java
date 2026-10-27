package b.b.g.d;

import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: RemModelStudy.java */
/* JADX INFO: loaded from: classes2.dex */
public class o extends l {
    @Override // b.b.g.d.l
    public boolean a() {
        return true;
    }

    public boolean b() {
        int iT0 = b.b.g.n.q.m.p0().t0();
        BydLogHelper.logD(o.class.getSimpleName(), "isKeyVibratorSup  :  ,value " + iT0);
        return iT0 >= 1 && iT0 <= 4;
    }

    public boolean c() {
        int iJ1 = b.b.g.n.q.m.p0().j1();
        BydLogHelper.logD(o.class.getSimpleName(), "RemModelStudy  :  ,state " + iJ1);
        return iJ1 == 1 || iJ1 == 2;
    }

    public boolean d() {
        int iA = b.b.g.n.q.q.b().a();
        BydLogHelper.logD(o.class.getSimpleName(), "sourceType : " + iA);
        return (iA == 0 || iA == 1) ? false : true;
    }

    public boolean e() {
        return true;
    }
}
