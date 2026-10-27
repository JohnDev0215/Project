package p;

import android.util.Log;
import android.view.View;
import androidx.fragment.app.SpecialEffectsController$Operation$LifecycleImpact;
import androidx.fragment.app.SpecialEffectsController$Operation$State;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class ze2 extends bf2 {
    public final androidx.fragment.app.b h;

    /* JADX WARN: Illegal instructions before constructor call */
    public ze2(SpecialEffectsController$Operation$State specialEffectsController$Operation$State, SpecialEffectsController$Operation$LifecycleImpact specialEffectsController$Operation$LifecycleImpact, androidx.fragment.app.b bVar, cq cqVar) {
        v03.j(bVar, "fragmentStateManager");
        ww0 ww0Var = bVar.c;
        v03.i(ww0Var, "fragmentStateManager.fragment");
        super(specialEffectsController$Operation$State, specialEffectsController$Operation$LifecycleImpact, ww0Var, cqVar);
        this.h = bVar;
    }

    @Override // p.bf2
    public final void b() {
        if (!this.g) {
            if (Log.isLoggable("FragmentManager", 2)) {
                toString();
            }
            this.g = true;
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
        }
        this.h.k();
    }

    @Override // p.bf2
    public final void d() {
        SpecialEffectsController$Operation$LifecycleImpact specialEffectsController$Operation$LifecycleImpact = this.b;
        SpecialEffectsController$Operation$LifecycleImpact specialEffectsController$Operation$LifecycleImpact2 = SpecialEffectsController$Operation$LifecycleImpact.e;
        androidx.fragment.app.b bVar = this.h;
        if (specialEffectsController$Operation$LifecycleImpact != specialEffectsController$Operation$LifecycleImpact2) {
            if (specialEffectsController$Operation$LifecycleImpact == SpecialEffectsController$Operation$LifecycleImpact.k) {
                ww0 ww0Var = bVar.c;
                v03.i(ww0Var, "fragmentStateManager.fragment");
                View viewC = ww0Var.C();
                if (Log.isLoggable("FragmentManager", 2)) {
                    Objects.toString(viewC.findFocus());
                    viewC.toString();
                    ww0Var.toString();
                }
                viewC.clearFocus();
                return;
            }
            return;
        }
        ww0 ww0Var2 = bVar.c;
        v03.i(ww0Var2, "fragmentStateManager.fragment");
        View viewFindFocus = ww0Var2.M.findFocus();
        if (viewFindFocus != null) {
            ww0Var2.c().m = viewFindFocus;
            if (Log.isLoggable("FragmentManager", 2)) {
                viewFindFocus.toString();
                ww0Var2.toString();
            }
        }
        View viewC2 = this.c.C();
        if (viewC2.getParent() == null) {
            bVar.b();
            viewC2.setAlpha(0.0f);
        }
        if (viewC2.getAlpha() == 0.0f && viewC2.getVisibility() == 0) {
            viewC2.setVisibility(4);
        }
        uw0 uw0Var = ww0Var2.P;
        viewC2.setAlpha(uw0Var == null ? 1.0f : uw0Var.l);
    }
}
