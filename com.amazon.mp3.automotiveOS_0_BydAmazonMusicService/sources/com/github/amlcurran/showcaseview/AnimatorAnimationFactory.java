package com.github.amlcurran.showcaseview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.graphics.Point;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import com.github.amlcurran.showcaseview.AnimationFactory;

/* JADX INFO: loaded from: classes6.dex */
@TargetApi(11)
class AnimatorAnimationFactory implements AnimationFactory {
    private final AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();

    @Override // com.github.amlcurran.showcaseview.AnimationFactory
    public void fadeInView(View view, long j, final AnimationFactory.AnimationStartListener animationStartListener) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        objectAnimatorOfFloat.setDuration(j).addListener(new AnimatorListenerAdapter() { // from class: com.github.amlcurran.showcaseview.AnimatorAnimationFactory.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                animationStartListener.onAnimationStart();
            }
        });
        objectAnimatorOfFloat.start();
    }

    @Override // com.github.amlcurran.showcaseview.AnimationFactory
    public void fadeOutView(View view, long j, final AnimationFactory.AnimationEndListener animationEndListener) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "alpha", 0.0f);
        objectAnimatorOfFloat.setDuration(j).addListener(new AnimatorListenerAdapter() { // from class: com.github.amlcurran.showcaseview.AnimatorAnimationFactory.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                animationEndListener.onAnimationEnd();
            }
        });
        objectAnimatorOfFloat.start();
    }

    @Override // com.github.amlcurran.showcaseview.AnimationFactory
    public void animateTargetToPoint(ShowcaseView showcaseView, Point point) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofInt(showcaseView, "showcaseX", point.x), ObjectAnimator.ofInt(showcaseView, "showcaseY", point.y));
        animatorSet.setInterpolator(this.interpolator);
        animatorSet.start();
    }
}
