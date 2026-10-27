package androidx.leanback.transition;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes3.dex */
class Scale extends Transition {
    private static final String PROPNAME_SCALE = "android:leanback:scale";

    private void captureValues(TransitionValues values) {
        View view = values.view;
        values.values.put(PROPNAME_SCALE, Float.valueOf(view.getScaleX()));
    }

    @Override // android.transition.Transition
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // android.transition.Transition
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // android.transition.Transition
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return null;
        }
        float startScale = ((Float) startValues.values.get(PROPNAME_SCALE)).floatValue();
        float endScale = ((Float) endValues.values.get(PROPNAME_SCALE)).floatValue();
        final View view = startValues.view;
        view.setScaleX(startScale);
        view.setScaleY(startScale);
        ValueAnimator animator = ValueAnimator.ofFloat(startScale, endScale);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.leanback.transition.Scale.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = ((Float) animation.getAnimatedValue()).floatValue();
                view.setScaleX(scale);
                view.setScaleY(scale);
            }
        });
        return animator;
    }
}
