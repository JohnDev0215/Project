package b.b.g.n.k;

import android.content.Context;
import b.b.g.o.a;
import b.b.g.o.c;
import com.byd.carsettings.BydApp;
import com.byd.carsettings.ui.fragment.dilink.SoundFragment;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: SoundPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class f extends b.b.m.e.g<SoundFragment> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.b.g.o.c f1137a = c.d.a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b.b.g.o.a f1138b = a.b.a();

    /* JADX INFO: compiled from: SoundPresenter.java */
    public class b implements b.b.g.o.g.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<f> f1139a;

        @Override // b.b.g.o.g.b
        public void a(boolean z) {
            if (g()) {
            }
        }

        @Override // b.b.g.o.g.b
        public void b(boolean z) {
            if (g()) {
                return;
            }
            ((SoundFragment) this.f1139a.get().getV()).n0(z);
        }

        @Override // b.b.g.o.g.b
        public void c(boolean z) {
            if (g()) {
                return;
            }
            ((SoundFragment) this.f1139a.get().getV()).g0(z);
        }

        @Override // b.b.g.o.g.b
        public void d(boolean z) {
            if (g()) {
                return;
            }
            ((SoundFragment) this.f1139a.get().getV()).l0(z);
        }

        @Override // b.b.g.o.g.b
        public void f(boolean z) {
            if (g()) {
                return;
            }
            ((SoundFragment) this.f1139a.get().getV()).i0(z);
        }

        public final boolean g() {
            WeakReference<f> weakReference = this.f1139a;
            return weakReference == null || weakReference.get() == null || this.f1139a.get().getV() == null;
        }

        public b(f fVar) {
            this.f1139a = new WeakReference<>(fVar);
        }
    }

    /* JADX INFO: compiled from: SoundPresenter.java */
    public class c implements b.b.g.o.g.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final WeakReference<f> f1141a;

        @Override // b.b.g.o.g.c
        public void a(int i) {
            if (g()) {
                return;
            }
            ((SoundFragment) this.f1141a.get().getV()).m0(i);
        }

        @Override // b.b.g.o.g.c
        public void b(int i) {
            if (g()) {
                return;
            }
            ((SoundFragment) this.f1141a.get().getV()).j0(i);
        }

        @Override // b.b.g.o.g.c
        public void c(int i) {
            if (g()) {
                return;
            }
            ((SoundFragment) this.f1141a.get().getV()).h0(i);
        }

        @Override // b.b.g.o.g.c
        public void d(int i) {
            if (g()) {
                return;
            }
            ((SoundFragment) this.f1141a.get().getV()).o0(i);
        }

        @Override // b.b.g.o.g.c
        public void f(int i) {
            if (g()) {
                return;
            }
            ((SoundFragment) this.f1141a.get().getV()).k0(i);
        }

        public final boolean g() {
            WeakReference<f> weakReference = this.f1141a;
            return weakReference == null || weakReference.get() == null || this.f1141a.get().getV() == null;
        }

        public c(f fVar) {
            this.f1141a = new WeakReference<>(fVar);
        }
    }

    public void A(boolean z) {
        this.f1137a.I(z);
    }

    public void B(boolean z) {
        this.f1138b.V(z);
    }

    public void C(int i) {
        this.f1138b.W(i);
    }

    public void D(boolean z) {
        this.f1138b.X(z);
    }

    public void E(int i) {
        this.f1138b.Y(i);
    }

    public void F(int i) {
        this.f1138b.Z(i);
    }

    public void G(boolean z) {
        this.f1138b.a0(z);
    }

    public void H(int i) {
        this.f1138b.b0(i);
    }

    public void I(boolean z) {
        this.f1138b.c0(z);
    }

    public void J(int i) {
        this.f1138b.d0(i);
    }

    public void init(Context context) {
        this.f1138b.T(f.class.getName(), new c(this));
        this.f1138b.S(f.class.getName(), new b(this));
        this.f1137a.r(BydApp.getContext());
    }

    public b.b.g.o.h.a o() {
        return this.f1137a.d();
    }

    public int q() {
        return this.f1138b.u();
    }

    public int r() {
        return this.f1138b.v();
    }

    public int s() {
        return this.f1138b.w();
    }

    public int t() {
        return this.f1138b.x();
    }

    public int u() {
        return this.f1138b.y();
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        b.b.g.o.c cVar = this.f1137a;
        if (cVar != null) {
            cVar.Z(f.class.getName());
        }
        b.b.g.o.a aVar = this.f1138b;
        if (aVar != null) {
            aVar.h0(f.class.getName());
            this.f1138b.g0(f.class.getName());
        }
    }

    public boolean v() {
        return this.f1137a.s();
    }

    public boolean w() {
        return this.f1138b.B();
    }

    public boolean x() {
        return this.f1138b.C();
    }

    public boolean y() {
        return this.f1138b.D();
    }

    public boolean z() {
        return this.f1138b.E();
    }
}
