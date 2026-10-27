package b.b.g.n.m;

import b.b.g.p.f;
import b.b.m.e.g;
import com.byd.carsettings.ui.fragment.DiLinkFragment;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: DiLinkPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends g<DiLinkFragment> {

    /* JADX INFO: compiled from: DiLinkPresenter.java */
    public class a implements b.b.g.p.j.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<d> f1207a;

        public a(d dVar) {
            this.f1207a = new WeakReference<>(dVar);
        }

        @Override // b.b.g.p.j.c
        public void onUpdateFlag(boolean z) {
            WeakReference<d> weakReference = this.f1207a;
            if (weakReference == null || weakReference.get() == null || this.f1207a.get().getV() == null) {
                return;
            }
            ((DiLinkFragment) this.f1207a.get().getV()).R(z);
        }
    }

    public boolean e() {
        return f.c.a().g();
    }

    public void f() {
        f.c.a().l(d.class.getName(), new a(this));
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        f.c.a().p(d.class.getName());
    }
}
