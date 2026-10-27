package b.b.g.n.k;

import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import b.b.g.p.d;
import b.b.g.p.f;
import com.byd.carsettings.BydApp;
import com.byd.carsettings.ui.fragment.dilink.VersionManageFragment;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: VersionManagePresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class h extends b.b.m.e.g<VersionManageFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.b.g.p.d f1154a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public BYDAutoStudy f1155b;

    /* JADX INFO: compiled from: VersionManagePresenter.java */
    public class a implements b.b.g.p.j.b {
        public a() {
        }

        @Override // b.b.g.p.j.b
        public void c() {
            b.b.m.c.a.g("VersionManagePresenter", "---restoreFactorySettings---onStarted---do restore car devices.");
        }

        @Override // b.b.g.p.j.b
        public void d() {
            b.b.m.c.a.g("VersionManagePresenter", "---restoreFactorySettings---onCompleted---do restore factory.");
            b.b.g.p.h.t(BydApp.getContext());
        }
    }

    /* JADX INFO: compiled from: VersionManagePresenter.java */
    public class b implements b.b.g.p.j.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<h> f1157a;

        public b(h hVar) {
            this.f1157a = new WeakReference<>(hVar);
        }

        @Override // b.b.g.p.j.c
        public void onUpdateFlag(boolean z) {
            WeakReference<h> weakReference = this.f1157a;
            if (weakReference == null || weakReference.get() == null || this.f1157a.get().getV() == null) {
                return;
            }
            ((VersionManageFragment) this.f1157a.get().getV()).X(z);
        }

        @Override // b.b.g.p.j.c
        public void onUpdateTime(long j) {
            WeakReference<h> weakReference = this.f1157a;
            if (weakReference == null || weakReference.get() == null || this.f1157a.get().getV() == null) {
                return;
            }
            ((VersionManageFragment) this.f1157a.get().getV()).Y(j);
        }
    }

    public void g() {
        f.c.a().e();
    }

    public String h() {
        return this.f1154a.q(getV().getContext());
    }

    public long i() {
        return f.c.a().f();
    }

    public boolean j() {
        return f.c.a().g();
    }

    public void l() {
        b.b.g.p.d dVarA = d.g.a();
        this.f1154a = dVarA;
        dVarA.W(getV().getContext());
        f.c.a().l(h.class.getName(), new b(this));
        this.f1155b = BYDAutoStudy.getInstance(getV().getContext());
    }

    public boolean m() {
        return this.f1154a.c0(getV().getContext());
    }

    public void n() {
        f.c.a().n();
    }

    public void o() {
        b.b.m.c.a.g("VersionManagePresenter", "---restoreFactorySettings---");
        this.f1154a.u0(getV().getContext(), null, new a());
    }

    public void q() {
        f.c.a().o();
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        f.c.a().p(h.class.getName());
    }
}
