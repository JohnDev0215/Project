package F0;

import A.C0004b;
import T.C0768u;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.lifecycle.EnumC0980s;
import androidx.lifecycle.InterfaceC0986y;
import com.stingray.drivemodedetector.R;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class u1 implements T.r, InterfaceC0986y {
    public final AndroidComposeView l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C0768u f2282m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f2283n;
    public A9.b o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public b0.b f2284p = AbstractC0224q0.f2250a;

    public u1(AndroidComposeView androidComposeView, C0768u c0768u) {
        this.l = androidComposeView;
        this.f2282m = c0768u;
    }

    public final void a() {
        if (!this.f2283n) {
            this.f2283n = true;
            this.l.getView().setTag(R.id.wrapped_composition_tag, null);
            A9.b bVar = this.o;
            if (bVar != null) {
                bVar.V0(this);
            }
        }
        this.f2282m.l();
    }

    @Override // androidx.lifecycle.InterfaceC0986y
    public final void b(androidx.lifecycle.A a10, EnumC0980s enumC0980s) {
        if (enumC0980s == EnumC0980s.ON_DESTROY) {
            a();
        } else {
            if (enumC0980s != EnumC0980s.ON_CREATE || this.f2283n) {
                return;
            }
            c(this.f2284p);
        }
    }

    public final void c(InterfaceC2785n interfaceC2785n) {
        this.l.setOnViewTreeOwnersAvailable(new C0004b(this, 14, (b0.b) interfaceC2785n));
    }
}
