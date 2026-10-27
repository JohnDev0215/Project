package p;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class em0 extends AnimatorListenerAdapter {
    public final /* synthetic */ int a = 1;
    public boolean b = false;
    public final Object c;

    public em0(View view) {
        this.c = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        switch (this.a) {
            case 0:
                this.b = true;
                break;
            default:
                super.onAnimationCancel(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        int i = this.a;
        Object obj = this.c;
        switch (i) {
            case 0:
                if (!this.b) {
                    gm0 gm0Var = (gm0) obj;
                    if (((Float) gm0Var.z.getAnimatedValue()).floatValue() != 0.0f) {
                        gm0Var.A = 2;
                        gm0Var.s.invalidate();
                    } else {
                        gm0Var.A = 0;
                        gm0Var.f(0);
                    }
                } else {
                    this.b = false;
                }
                break;
            default:
                View view = (View) obj;
                dv2.a.M(view, 1.0f);
                if (this.b) {
                    view.setLayerType(0, null);
                }
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.a) {
            case 1:
                View view = (View) this.c;
                WeakHashMap weakHashMap = zt2.a;
                if (it2.h(view) && view.getLayerType() == 0) {
                    this.b = true;
                    view.setLayerType(2, null);
                    break;
                }
                break;
            default:
                super.onAnimationStart(animator);
                break;
        }
    }

    public em0(gm0 gm0Var) {
        this.c = gm0Var;
    }
}
