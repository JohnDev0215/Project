package p;

import android.util.Log;
import androidx.fragment.app.SpecialEffectsController$Operation$LifecycleImpact;
import androidx.fragment.app.SpecialEffectsController$Operation$State;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class bf2 {
    public SpecialEffectsController$Operation$State a;
    public SpecialEffectsController$Operation$LifecycleImpact b;
    public final ww0 c;
    public final ArrayList d = new ArrayList();
    public final LinkedHashSet e = new LinkedHashSet();
    public boolean f;
    public boolean g;

    public bf2(SpecialEffectsController$Operation$State specialEffectsController$Operation$State, SpecialEffectsController$Operation$LifecycleImpact specialEffectsController$Operation$LifecycleImpact, ww0 ww0Var, cq cqVar) {
        this.a = specialEffectsController$Operation$State;
        this.b = specialEffectsController$Operation$LifecycleImpact;
        this.c = ww0Var;
        cqVar.b(new af2(0, this));
    }

    public final void a() {
        if (this.f) {
            return;
        }
        this.f = true;
        LinkedHashSet linkedHashSet = this.e;
        if (linkedHashSet.isEmpty()) {
            b();
            return;
        }
        Iterator it = ou.p0(linkedHashSet).iterator();
        while (it.hasNext()) {
            ((cq) it.next()).a();
        }
    }

    public abstract void b();

    public final void c(SpecialEffectsController$Operation$State specialEffectsController$Operation$State, SpecialEffectsController$Operation$LifecycleImpact specialEffectsController$Operation$LifecycleImpact) {
        int iOrdinal = specialEffectsController$Operation$LifecycleImpact.ordinal();
        SpecialEffectsController$Operation$State specialEffectsController$Operation$State2 = SpecialEffectsController$Operation$State.c;
        ww0 ww0Var = this.c;
        if (iOrdinal == 0) {
            if (this.a != specialEffectsController$Operation$State2) {
                if (Log.isLoggable("FragmentManager", 2)) {
                    Objects.toString(ww0Var);
                    Objects.toString(this.a);
                    specialEffectsController$Operation$State.toString();
                }
                this.a = specialEffectsController$Operation$State;
                return;
            }
            return;
        }
        if (iOrdinal == 1) {
            if (this.a == specialEffectsController$Operation$State2) {
                if (Log.isLoggable("FragmentManager", 2)) {
                    Objects.toString(ww0Var);
                    Objects.toString(this.b);
                }
                this.a = SpecialEffectsController$Operation$State.e;
                this.b = SpecialEffectsController$Operation$LifecycleImpact.e;
                return;
            }
            return;
        }
        if (iOrdinal != 2) {
            return;
        }
        if (Log.isLoggable("FragmentManager", 2)) {
            Objects.toString(ww0Var);
            Objects.toString(this.a);
            Objects.toString(this.b);
        }
        this.a = specialEffectsController$Operation$State2;
        this.b = SpecialEffectsController$Operation$LifecycleImpact.k;
    }

    public abstract void d();

    public final String toString() {
        StringBuilder sbP = u4.p("Operation {", Integer.toHexString(System.identityHashCode(this)), "} {finalState = ");
        sbP.append(this.a);
        sbP.append(" lifecycleImpact = ");
        sbP.append(this.b);
        sbP.append(" fragment = ");
        sbP.append(this.c);
        sbP.append('}');
        return sbP.toString();
    }
}
