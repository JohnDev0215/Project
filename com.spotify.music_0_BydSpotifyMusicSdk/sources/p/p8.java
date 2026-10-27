package p;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.view.View;
import androidx.fragment.app.SpecialEffectsController$Operation$State;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public abstract class p8 {
    public Object a;
    public final Object b;

    public p8(bf2 bf2Var, cq cqVar) {
        this.a = bf2Var;
        this.b = cqVar;
    }

    public final void a() {
        Object obj = this.a;
        if (((BroadcastReceiver) obj) != null) {
            try {
                ((s8) this.b).s.unregisterReceiver((BroadcastReceiver) obj);
            } catch (IllegalArgumentException unused) {
            }
            this.a = null;
        }
    }

    public final void b() {
        bf2 bf2Var = (bf2) this.a;
        cq cqVar = (cq) this.b;
        bf2Var.getClass();
        v03.j(cqVar, "signal");
        LinkedHashSet linkedHashSet = bf2Var.e;
        if (linkedHashSet.remove(cqVar) && linkedHashSet.isEmpty()) {
            bf2Var.b();
        }
    }

    public abstract IntentFilter c();

    public abstract int d();

    public final boolean e() {
        SpecialEffectsController$Operation$State specialEffectsController$Operation$State;
        View view = ((bf2) this.a).c.M;
        v03.i(view, "operation.fragment.mView");
        SpecialEffectsController$Operation$State specialEffectsController$Operation$StateA = p02.a(view);
        SpecialEffectsController$Operation$State specialEffectsController$Operation$State2 = ((bf2) this.a).a;
        return specialEffectsController$Operation$StateA == specialEffectsController$Operation$State2 || !(specialEffectsController$Operation$StateA == (specialEffectsController$Operation$State = SpecialEffectsController$Operation$State.e) || specialEffectsController$Operation$State2 == specialEffectsController$Operation$State);
    }

    public abstract void f();

    public final void g() {
        a();
        IntentFilter intentFilterC = c();
        if (intentFilterC == null || intentFilterC.countActions() == 0) {
            return;
        }
        if (((BroadcastReceiver) this.a) == null) {
            this.a = new o8(0, this);
        }
        ((s8) this.b).s.registerReceiver((BroadcastReceiver) this.a, intentFilterC);
    }

    public p8(s8 s8Var) {
        this.b = s8Var;
    }
}
