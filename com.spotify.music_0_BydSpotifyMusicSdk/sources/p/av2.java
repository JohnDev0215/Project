package p;

import android.view.View;
import coil.request.ViewTargetRequestDelegate;
import coil.target.GenericViewTarget;

/* JADX INFO: loaded from: classes.dex */
public final class av2 implements View.OnAttachStateChangeListener {
    public zb0 c;
    public uf2 e;
    public ViewTargetRequestDelegate k;
    public boolean l;

    public av2(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        ViewTargetRequestDelegate viewTargetRequestDelegate = this.k;
        if (viewTargetRequestDelegate == null) {
            return;
        }
        this.l = true;
        ((coil.b) viewTargetRequestDelegate.c).b(viewTargetRequestDelegate.e);
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        ViewTargetRequestDelegate viewTargetRequestDelegate = this.k;
        if (viewTargetRequestDelegate != null) {
            viewTargetRequestDelegate.m.i(null);
            GenericViewTarget genericViewTarget = viewTargetRequestDelegate.k;
            boolean z = genericViewTarget instanceof r91;
            l91 l91Var = viewTargetRequestDelegate.l;
            if (z) {
                l91Var.c(genericViewTarget);
            }
            l91Var.c(viewTargetRequestDelegate);
        }
    }
}
