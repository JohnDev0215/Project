package P4;

import Y4.f;
import Y4.g;
import a5.o;
import android.animation.ValueAnimator;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.textfield.TextInputLayout;
import s3.C2392l;

/* JADX INFO: loaded from: classes.dex */
public final class b implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f5333a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f5334b;

    public /* synthetic */ b(Object obj, int i7) {
        this.f5333a = i7;
        this.f5334b = obj;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.f5333a) {
            case 0:
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                g gVar = ((BottomSheetBehavior) this.f5334b).f12779i;
                if (gVar != null) {
                    f fVar = gVar.l;
                    if (fVar.f10714i != fFloatValue) {
                        fVar.f10714i = fFloatValue;
                        gVar.f10727p = true;
                        gVar.invalidateSelf();
                    }
                }
                break;
            case 1:
                ((o) this.f5334b).c.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            case 2:
                ((TextInputLayout) this.f5334b).f12897R0.i(((Float) valueAnimator.getAnimatedValue()).floatValue());
                break;
            default:
                int iFloatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
                C2392l c2392l = (C2392l) this.f5334b;
                c2392l.c.setAlpha(iFloatValue);
                c2392l.d.setAlpha(iFloatValue);
                c2392l.f22312s.invalidate();
                break;
        }
    }
}
