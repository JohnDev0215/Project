package defpackage;

import defpackage.di1;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: InstanceSwitcherCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f11 implements di1.a {
    public final /* synthetic */ g11 e;

    public f11(g11 g11Var) {
        this.e = g11Var;
    }

    @Override // di1.a
    public final void b(int i) {
        g11.n = null;
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        if (i != 1) {
            return;
        }
        g11 g11Var = this.e;
        g11Var.e.b(2, g11Var.j);
    }
}
