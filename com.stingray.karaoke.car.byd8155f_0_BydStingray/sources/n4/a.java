package N4;

import a5.o;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;

/* JADX INFO: loaded from: classes.dex */
public final class a extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f4589a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f4590b;

    public /* synthetic */ a(Object obj, int i7) {
        this.f4589a = i7;
        this.f4590b = obj;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        switch (this.f4589a) {
            case 2:
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.f4590b;
                actionBarOverlayLayout.f11313E = null;
                actionBarOverlayLayout.f11325t = false;
                break;
            default:
                super.onAnimationCancel(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.f4589a) {
            case 0:
                ((HideBottomViewOnScrollBehavior) this.f4590b).c = null;
                break;
            case 1:
                o oVar = (o) this.f4590b;
                oVar.c.setChecked(oVar.f11165j);
                oVar.f11169p.start();
                break;
            default:
                ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.f4590b;
                actionBarOverlayLayout.f11313E = null;
                actionBarOverlayLayout.f11325t = false;
                break;
        }
    }
}
