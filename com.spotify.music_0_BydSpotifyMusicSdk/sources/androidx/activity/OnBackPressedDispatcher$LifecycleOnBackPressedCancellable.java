package androidx.activity;

import android.os.Build;
import androidx.lifecycle.Lifecycle$Event;
import p.gx0;
import p.l91;
import p.np1;
import p.p91;
import p.s91;
import p.v03;
import p.xp;

/* JADX INFO: loaded from: classes.dex */
final class OnBackPressedDispatcher$LifecycleOnBackPressedCancellable implements p91, xp {
    public final l91 c;
    public final gx0 e;
    public np1 k;
    public final /* synthetic */ b l;

    public OnBackPressedDispatcher$LifecycleOnBackPressedCancellable(b bVar, l91 l91Var, gx0 gx0Var) {
        v03.j(gx0Var, "onBackPressedCallback");
        this.l = bVar;
        this.c = l91Var;
        this.e = gx0Var;
        l91Var.a(this);
    }

    @Override // p.xp
    public final void cancel() {
        this.c.c(this);
        gx0 gx0Var = this.e;
        gx0Var.getClass();
        gx0Var.b.remove(this);
        np1 np1Var = this.k;
        if (np1Var != null) {
            np1Var.cancel();
        }
        this.k = null;
    }

    @Override // p.p91
    public final void d(s91 s91Var, Lifecycle$Event lifecycle$Event) {
        if (lifecycle$Event != Lifecycle$Event.ON_START) {
            if (lifecycle$Event != Lifecycle$Event.ON_STOP) {
                if (lifecycle$Event == Lifecycle$Event.ON_DESTROY) {
                    cancel();
                    return;
                }
                return;
            } else {
                np1 np1Var = this.k;
                if (np1Var != null) {
                    np1Var.cancel();
                    return;
                }
                return;
            }
        }
        b bVar = this.l;
        bVar.getClass();
        gx0 gx0Var = this.e;
        v03.j(gx0Var, "onBackPressedCallback");
        bVar.b.addLast(gx0Var);
        np1 np1Var2 = new np1(bVar, gx0Var);
        gx0Var.b.add(np1Var2);
        if (Build.VERSION.SDK_INT >= 33) {
            bVar.c();
            gx0Var.c = bVar.c;
        }
        this.k = np1Var2;
    }
}
