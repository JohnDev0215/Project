package p;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import com.google.android.material.transformation.ExpandableTransformationBehavior;

/* JADX INFO: loaded from: classes.dex */
public final class u3 extends AnimatorListenerAdapter {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ u3(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        switch (this.a) {
            case 0:
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.b;
                actionBarOverlayLayout.E = null;
                actionBarOverlayLayout.s = false;
                break;
            default:
                super.onAnimationCancel(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) obj;
                actionBarOverlayLayout.E = null;
                actionBarOverlayLayout.s = false;
                return;
            case 1:
                ((wn2) obj).p();
                animator.removeListener(this);
                return;
            case 2:
                ((HideBottomViewOnScrollBehavior) obj).c = null;
                return;
            case 3:
            default:
                ((ExpandableTransformationBehavior) obj).getClass();
                return;
            case 4:
                u4.z(obj);
                throw null;
            case 5:
                pb0 pb0Var = (pb0) obj;
                pb0Var.c.setChecked(pb0Var.k);
                pb0Var.q.start();
                return;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.a) {
            case 4:
                u4.z(this.b);
                throw null;
            default:
                super.onAnimationStart(animator);
                return;
        }
    }
}
