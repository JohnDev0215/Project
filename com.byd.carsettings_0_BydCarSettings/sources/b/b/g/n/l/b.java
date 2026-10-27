package b.b.g.n.l;

import android.hardware.bydauto.dipilot.BYDAutoDiPilotDevice;
import b.b.g.n.q.u;
import b.b.m.e.c;
import b.b.m.e.g;
import com.byd.base.hilt.Router;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: BaseDipilotPresent.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class b<V extends b.b.m.e.c> extends g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.b.g.n.q.d f1160a = b.b.g.n.q.d.x();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public u f1161b = u.I();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public b.b.g.n.q.b f1162c = b.b.g.n.q.b.c();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b.b.g.n.q.g f1163d = b.b.g.n.q.g.g();

    public int c() {
        return this.f1160a.b();
    }

    public BYDAutoDiPilotDevice d() {
        return this.f1160a.p();
    }

    public boolean e(int i) {
        return i > 0 && i < 4;
    }

    public int f() {
        int iE = this.f1163d.e();
        BydLogHelper.logD("getGearboxAutoModeType " + iE);
        return iE;
    }

    public byte g() {
        return Router.b().d().a();
    }
}
