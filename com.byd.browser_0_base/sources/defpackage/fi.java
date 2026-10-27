package defpackage;

import android.animation.ValueAnimator;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* JADX INFO: compiled from: BottomSheetBehavior.java */
/* JADX INFO: loaded from: classes.dex */
public final class fi implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ BottomSheetBehavior e;

    public fi(BottomSheetBehavior bottomSheetBehavior) {
        this.e = bottomSheetBehavior;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        sc1 sc1Var = this.e.h;
        if (sc1Var != null) {
            sc1Var.o(fFloatValue);
        }
    }
}
