package p;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
public final class iq implements View.OnAttachStateChangeListener {
    public final /* synthetic */ int c;
    public final /* synthetic */ ej1 e;

    public /* synthetic */ iq(ej1 ej1Var, int i) {
        this.c = i;
        this.e = ej1Var;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        int i = this.c;
        ej1 ej1Var = this.e;
        switch (i) {
            case 0:
                lq lqVar = (lq) ej1Var;
                ViewTreeObserver viewTreeObserver = lqVar.G;
                if (viewTreeObserver != null) {
                    if (!viewTreeObserver.isAlive()) {
                        lqVar.G = view.getViewTreeObserver();
                    }
                    lqVar.G.removeGlobalOnLayoutListener(lqVar.r);
                }
                view.removeOnAttachStateChangeListener(this);
                break;
            default:
                vf2 vf2Var = (vf2) ej1Var;
                ViewTreeObserver viewTreeObserver2 = vf2Var.x;
                if (viewTreeObserver2 != null) {
                    if (!viewTreeObserver2.isAlive()) {
                        vf2Var.x = view.getViewTreeObserver();
                    }
                    vf2Var.x.removeGlobalOnLayoutListener(vf2Var.r);
                }
                view.removeOnAttachStateChangeListener(this);
                break;
        }
    }
}
