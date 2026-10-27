package b.b.g.d;

import b.b.g.n.q.u;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: DibarModelStudy.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends l {
    @Override // b.b.g.d.l
    public boolean a() {
        return f() || e() || d() || c();
    }

    public int b() {
        BydLogHelper.logD(e.class.getSimpleName(), "getRefrigeratorModeSup  :  ,state " + u.I().Z());
        return u.I().Z();
    }

    public boolean c() {
        int iC = b.b.g.n.q.m.p0().C();
        BydLogHelper.logD(e.class.getSimpleName(), "isDisinfSup  :  ,state " + iC);
        return iC == 1 || iC == 2;
    }

    public boolean d() {
        int iD1 = b.b.g.n.q.m.p0().d1();
        BydLogHelper.logD(e.class.getSimpleName(), "isMePowerOffSup  :  ,state " + iD1);
        return iD1 == 1 || iD1 == 2;
    }

    public boolean e() {
        return b() == 1 || b() == 2;
    }

    public boolean f() {
        int iB1 = b.b.g.n.q.m.p0().B1();
        BydLogHelper.logD(e.class.getSimpleName(), "isTraySup  :  ,state " + iB1);
        return iB1 == 1 || iB1 == 2;
    }
}
