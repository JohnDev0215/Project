package defpackage;

import defpackage.di1;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TargetSelectorCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class s63 implements di1.a {
    public final /* synthetic */ t63 e;

    public s63(t63 t63Var) {
        this.e = t63Var;
    }

    @Override // di1.a
    public final void b(int i) {
        t63.j = null;
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        t63 t63Var = this.e;
        if (i == 0) {
            t63Var.f.b(1, t63Var.g);
            t63Var.b.onResult(t63Var.h);
        } else {
            if (i != 1) {
                return;
            }
            t63Var.f.b(2, t63Var.g);
        }
    }
}
