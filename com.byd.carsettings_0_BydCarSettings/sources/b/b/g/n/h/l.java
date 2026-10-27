package b.b.g.n.h;

import com.byd.carsettings.ui.fragment.carsettings.RearViewMirrorFragment;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: RearViewMirrorPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class l extends b.b.m.e.g<RearViewMirrorFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.b.g.d.n f1017a = new b.b.g.d.n();

    public int c() {
        return this.f1017a.b();
    }

    public int d() {
        return b.b.g.n.q.m.p0().W0();
    }

    public int e() {
        int iX1 = b.b.g.n.q.m.p0().x1();
        BydLogHelper.logD(l.class.getSimpleName(), "getSwControlState value : " + iX1);
        return iX1;
    }

    public boolean f() {
        return this.f1017a.d();
    }

    public int g(int i) {
        return b.b.g.n.q.m.p0().f3(i);
    }
}
