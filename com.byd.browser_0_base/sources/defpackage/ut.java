package defpackage;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Magnifier;
import com.google.android.material.internal.CheckableImageButton;
import defpackage.lw1;
import org.chromium.chrome.browser.toolbar.top.CustomTabSwitcherModeTopToolbar;
import org.chromium.chrome.browser.toolbar.top.i;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content.browser.selection.a;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class ut implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ ut(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        View viewO;
        int i = this.e;
        Object obj = this.f;
        switch (i) {
            case 0:
                zt ztVar = (zt) obj;
                ztVar.getClass();
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                CheckableImageButton checkableImageButton = ztVar.d;
                checkableImageButton.setScaleX(fFloatValue);
                checkableImageButton.setScaleY(fFloatValue);
                break;
            case 1:
                yv1 yv1Var = (yv1) obj;
                yv1Var.getClass();
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                Activity activity = yv1Var.e;
                WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
                attributes.y = iIntValue;
                activity.getWindow().setAttributes(attributes);
                break;
            case 2:
                lw1 lw1Var = (lw1) obj;
                lw1.a aVar = lw1.L;
                lw1Var.getClass();
                int iIntValue2 = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                Activity activity2 = lw1Var.e;
                WindowManager.LayoutParams attributes2 = activity2.getWindow().getAttributes();
                attributes2.x = iIntValue2;
                activity2.getWindow().setAttributes(attributes2);
                break;
            case 3:
                CustomTabSwitcherModeTopToolbar customTabSwitcherModeTopToolbar = (CustomTabSwitcherModeTopToolbar) obj;
                int i2 = CustomTabSwitcherModeTopToolbar.x;
                customTabSwitcherModeTopToolbar.getClass();
                Object animatedValue = valueAnimator.getAnimatedValue();
                i iVar = customTabSwitcherModeTopToolbar.t;
                if (iVar != null && (animatedValue instanceof Float)) {
                    iVar.h = ((Float) animatedValue).floatValue();
                    iVar.a();
                    break;
                }
                break;
            default:
                ab1 ab1Var = (ab1) obj;
                float f = ab1Var.f;
                ab1Var.d = (valueAnimator.getAnimatedFraction() * (ab1Var.h - f)) + f;
                float f2 = ab1Var.g;
                float animatedFraction = (valueAnimator.getAnimatedFraction() * (ab1Var.i - f2)) + f2;
                ab1Var.e = animatedFraction;
                float f3 = ab1Var.d;
                a aVar2 = (a) ab1Var.a;
                SelectionPopupControllerImpl selectionPopupControllerImpl = (SelectionPopupControllerImpl) ((gc3) aVar2.b).e;
                if (SelectionPopupControllerImpl.M) {
                    WindowAndroid windowAndroid = selectionPopupControllerImpl.h;
                    viewO = windowAndroid == null ? null : windowAndroid.o();
                } else {
                    viewO = selectionPopupControllerImpl.p;
                }
                if (viewO != null) {
                    if (aVar2.a == null) {
                        aVar2.a = new Magnifier(viewO);
                    }
                    aVar2.a.show(f3, animatedFraction);
                    break;
                }
                break;
        }
    }
}
