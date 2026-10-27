package defpackage;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.view.WindowManager;
import defpackage.lw1;
import org.chromium.chrome.browser.toolbar.top.TabSwitcherModeTopToolbar;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class le0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ le0(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                pe0 pe0Var = (pe0) obj;
                pe0Var.getClass();
                pe0Var.d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            case 1:
                lw1 lw1Var = (lw1) obj;
                lw1.a aVar = lw1.L;
                lw1Var.getClass();
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                Activity activity = lw1Var.e;
                WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                attributes.y = iIntValue;
                activity.getWindow().setAttributes(attributes);
                break;
            case 2:
                int i2 = TabSwitcherModeTopToolbar.q;
                ((TabSwitcherModeTopToolbar) obj).getClass();
                valueAnimator.getAnimatedValue();
                break;
            default:
                if2 if2Var = (if2) obj;
                if2Var.getClass();
                if2Var.b(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
        }
    }
}
