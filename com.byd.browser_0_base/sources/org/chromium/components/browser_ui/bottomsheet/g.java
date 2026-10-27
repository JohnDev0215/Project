package org.chromium.components.browser_ui.bottomsheet;

import defpackage.bh0;
import defpackage.ii;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: BottomSheetControllerImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g extends bh0 {
    public boolean e;
    public final /* synthetic */ PropertyModel f;
    public final /* synthetic */ f g;

    public g(f fVar, PropertyModel propertyModel) {
        this.g = fVar;
        this.f = propertyModel;
    }

    @Override // defpackage.bh0, defpackage.vi
    public final void b(ii iiVar) {
        f fVar = this.g;
        fVar.w();
        if (iiVar != null) {
            return;
        }
        fVar.f.setVisibility(8);
    }

    @Override // defpackage.bh0, defpackage.vi
    public final void c(int i) {
        boolean z = this.e;
        f fVar = this.g;
        if (z) {
            fVar.n.get().b.a(300, true);
            this.e = false;
        }
        ii iiVar = fVar.e.t;
        if (iiVar != null && !iiVar.k()) {
            ii iiVar2 = fVar.e.t;
            ii iiVarPeek = fVar.g.peek();
            if (iiVar2 != null && iiVarPeek != null && iiVarPeek.e() < iiVar2.e()) {
                fVar.g.add(iiVar2);
                fVar.e.s(0, 0, true);
            }
        }
        fVar.w();
    }

    @Override // defpackage.bh0, defpackage.vi
    public final void d(int i, int i2) {
        if (i == 0) {
            f fVar = this.g;
            if (fVar.h || !fVar.o.b()) {
                ii iiVar = fVar.e.t;
                if (iiVar != null && !fVar.i) {
                    iiVar.destroy();
                }
                fVar.i = false;
                fVar.h = false;
                fVar.u(true);
                fVar.w();
            }
        }
    }

    @Override // defpackage.bh0, defpackage.vi
    public final void e() {
        f fVar = this.g;
        ii iiVar = fVar.e.t;
        if (iiVar != null && iiVar.f()) {
            fVar.w();
            return;
        }
        fVar.n.get().a(this.f);
        this.e = true;
        fVar.w();
    }
}
