package b.d.a.a.p;

import android.animation.ValueAnimator;
import b.d.a.a.e0.g;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* JADX INFO: loaded from: classes.dex */
public class a implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ BottomSheetBehavior f2039a;

    public a(BottomSheetBehavior bottomSheetBehavior) {
        this.f2039a = bottomSheetBehavior;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        g gVar = this.f2039a.j;
        if (gVar != null) {
            gVar.r(fFloatValue);
        }
    }
}
