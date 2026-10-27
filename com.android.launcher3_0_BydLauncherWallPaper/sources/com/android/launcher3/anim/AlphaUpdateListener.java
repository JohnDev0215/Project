package com.android.launcher3.anim;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes4.dex */
public class AlphaUpdateListener extends AnimationSuccessListener implements ValueAnimator.AnimatorUpdateListener {
    private static final float ALPHA_CUTOFF_THRESHOLD = 0.01f;
    private View mView;

    public AlphaUpdateListener(View v) {
        this.mView = v;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator arg0) {
        updateVisibility(this.mView);
    }

    @Override // com.android.launcher3.anim.AnimationSuccessListener
    public void onAnimationSuccess(Animator animator) {
        updateVisibility(this.mView);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator arg0) {
        this.mView.setVisibility(0);
    }

    public static void updateVisibility(View view) {
        if (view.getAlpha() < ALPHA_CUTOFF_THRESHOLD && view.getVisibility() != 4) {
            view.setVisibility(4);
            return;
        }
        if (view.getAlpha() <= ALPHA_CUTOFF_THRESHOLD || view.getVisibility() == 0) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int descendantFocusability = viewGroup.getDescendantFocusability();
            viewGroup.setDescendantFocusability(393216);
            viewGroup.setVisibility(0);
            viewGroup.setDescendantFocusability(descendantFocusability);
            return;
        }
        view.setVisibility(0);
    }
}
