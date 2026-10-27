package coil.request;

import coil.target.GenericViewTarget;
import java.util.concurrent.CancellationException;
import kotlinx.coroutines.android.a;
import p.av2;
import p.de1;
import p.ha0;
import p.i;
import p.l31;
import p.l91;
import p.o32;
import p.p61;
import p.q31;
import p.r91;
import p.s70;
import p.s91;
import p.u01;
import p.uf2;
import p.vr2;

/* JADX INFO: loaded from: classes.dex */
public final class ViewTargetRequestDelegate implements o32 {
    public final l31 c;
    public final q31 e;
    public final GenericViewTarget k;
    public final l91 l;
    public final p61 m;

    public ViewTargetRequestDelegate(l31 l31Var, q31 q31Var, GenericViewTarget genericViewTarget, l91 l91Var, p61 p61Var) {
        this.c = l31Var;
        this.e = q31Var;
        this.k = genericViewTarget;
        this.l = l91Var;
        this.m = p61Var;
    }

    @Override // p.o32
    public final void b() {
        l91 l91Var = this.l;
        l91Var.a(this);
        GenericViewTarget genericViewTarget = this.k;
        if (genericViewTarget instanceof r91) {
            l91Var.c(genericViewTarget);
            l91Var.a(genericViewTarget);
        }
        av2 av2VarC = i.c(genericViewTarget.i());
        ViewTargetRequestDelegate viewTargetRequestDelegate = av2VarC.k;
        if (viewTargetRequestDelegate != null) {
            viewTargetRequestDelegate.m.i(null);
            GenericViewTarget genericViewTarget2 = viewTargetRequestDelegate.k;
            boolean z = genericViewTarget2 instanceof r91;
            l91 l91Var2 = viewTargetRequestDelegate.l;
            if (z) {
                l91Var2.c(genericViewTarget2);
            }
            l91Var2.c(viewTargetRequestDelegate);
        }
        av2VarC.k = this;
    }

    @Override // p.o32
    public final void e() {
        GenericViewTarget genericViewTarget = this.k;
        if (genericViewTarget.i().isAttachedToWindow()) {
            return;
        }
        av2 av2VarC = i.c(genericViewTarget.i());
        ViewTargetRequestDelegate viewTargetRequestDelegate = av2VarC.k;
        if (viewTargetRequestDelegate != null) {
            viewTargetRequestDelegate.m.i(null);
            GenericViewTarget genericViewTarget2 = viewTargetRequestDelegate.k;
            boolean z = genericViewTarget2 instanceof r91;
            l91 l91Var = viewTargetRequestDelegate.l;
            if (z) {
                l91Var.c(genericViewTarget2);
            }
            l91Var.c(viewTargetRequestDelegate);
        }
        av2VarC.k = this;
        throw new CancellationException("'ViewTarget.view' must be attached to a window.");
    }

    @Override // p.n70
    public final void onDestroy(s91 s91Var) {
        av2 av2VarC = i.c(this.k.i());
        synchronized (av2VarC) {
            uf2 uf2Var = av2VarC.e;
            if (uf2Var != null) {
                uf2Var.i(null);
            }
            u01 u01Var = u01.c;
            s70 s70Var = ha0.a;
            av2VarC.e = vr2.l(u01Var, ((a) de1.a).n, null, new ViewTargetRequestManager$dispose$1(av2VarC, null), 2);
            av2VarC.c = null;
        }
    }
}
