package defpackage;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.view.WindowManager;
import org.chromium.chrome.browser.toolbar.top.g;
import org.chromium.chrome.browser.toolbar.top.i;
import org.chromium.chrome.browser.toolbar.top.m;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class jw1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ jw1(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                lw1 lw1Var = (lw1) obj;
                lw1Var.getClass();
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                Activity activity = lw1Var.e;
                WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                attributes.x = iIntValue;
                activity.getWindow().setAttributes(attributes);
                break;
            case 1:
                qt2 qt2Var = (qt2) obj;
                qt2Var.getClass();
                qt2Var.e.m(rt2.f, ((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            case 2:
                g gVar = (g) obj;
                gVar.getClass();
                Object animatedValue = valueAnimator.getAnimatedValue();
                m.a aVar = gVar.F;
                if (aVar != null && (animatedValue instanceof Float)) {
                    i iVar = (i) aVar;
                    iVar.h = ((Float) animatedValue).floatValue();
                    iVar.a();
                    break;
                }
                break;
            default:
                if2 if2Var = (if2) obj;
                if2Var.getClass();
                if2Var.b(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
        }
    }
}
