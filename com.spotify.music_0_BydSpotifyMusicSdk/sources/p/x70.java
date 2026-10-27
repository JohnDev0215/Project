package p;

import android.transition.Transition;
import androidx.fragment.app.SpecialEffectsController$Operation$State;

/* JADX INFO: loaded from: classes.dex */
public final class x70 extends p8 {
    public final Object c;
    public final boolean d;
    public final Object e;

    public x70(bf2 bf2Var, cq cqVar, boolean z, boolean z2) {
        Object obj;
        Object obj2;
        super(bf2Var, cqVar);
        SpecialEffectsController$Operation$State specialEffectsController$Operation$State = bf2Var.a;
        SpecialEffectsController$Operation$State specialEffectsController$Operation$State2 = SpecialEffectsController$Operation$State.e;
        Object obj3 = null;
        ww0 ww0Var = bf2Var.c;
        if (specialEffectsController$Operation$State == specialEffectsController$Operation$State2) {
            if (z) {
                uw0 uw0Var = ww0Var.P;
                if (uw0Var == null || (obj = uw0Var.j) == ww0.a0) {
                }
            } else {
                ww0Var.getClass();
            }
            obj = null;
        } else {
            if (z) {
                uw0 uw0Var2 = ww0Var.P;
                if (uw0Var2 == null || (obj = uw0Var2.i) == ww0.a0) {
                }
            } else {
                ww0Var.getClass();
            }
            obj = null;
        }
        this.c = obj;
        if (bf2Var.a == specialEffectsController$Operation$State2) {
            if (z) {
                uw0 uw0Var3 = ww0Var.P;
            } else {
                uw0 uw0Var4 = ww0Var.P;
            }
        }
        this.d = true;
        if (z2) {
            if (z) {
                uw0 uw0Var5 = ww0Var.P;
                if (uw0Var5 != null && (obj2 = uw0Var5.k) != ww0.a0) {
                    obj3 = obj2;
                }
            } else {
                ww0Var.getClass();
            }
        }
        this.e = obj3;
    }

    public final dy0 h() {
        Object obj = this.c;
        dy0 dy0VarI = i(obj);
        Object obj2 = this.e;
        dy0 dy0VarI2 = i(obj2);
        if (dy0VarI == null || dy0VarI2 == null || dy0VarI == dy0VarI2) {
            return dy0VarI == null ? dy0VarI2 : dy0VarI;
        }
        throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + ((bf2) this.a).c + " returned Transition " + obj + " which uses a different Transition  type than its shared element transition " + obj2).toString());
    }

    public final dy0 i(Object obj) {
        if (obj == null) {
            return null;
        }
        by0 by0Var = wx0.a;
        if (obj instanceof Transition) {
            return by0Var;
        }
        dy0 dy0Var = wx0.b;
        if (dy0Var != null && dy0Var.e(obj)) {
            return dy0Var;
        }
        throw new IllegalArgumentException("Transition " + obj + " for fragment " + ((bf2) this.a).c + " is not a valid framework Transition or AndroidX Transition");
    }
}
