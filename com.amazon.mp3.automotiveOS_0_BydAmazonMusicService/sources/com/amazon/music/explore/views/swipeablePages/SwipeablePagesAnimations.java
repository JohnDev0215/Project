package com.amazon.music.explore.views.swipeablePages;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.Interpolator;
import androidx.core.view.animation.PathInterpolatorCompat;

/* JADX INFO: loaded from: classes4.dex */
public class SwipeablePagesAnimations {
    private static final float CUBIC_IN_X1 = 0.4f;
    private static final float CUBIC_IN_X2 = 0.68f;
    private static final float CUBIC_IN_Y1 = 0.0f;
    private static final float CUBIC_IN_Y2 = 0.06f;
    private static final int DURATION_MS = 500;
    private static final float OFFSET = 40.0f;
    private static final float SIZE_SCALING_MAX = 1.0f;
    private static final float SIZE_SCALING_MIN = 0.75f;
    private static final float SLIDE_FADE_X1 = 0.25f;
    private static final float SLIDE_FADE_X2 = 0.25f;
    private static final float SLIDE_FADE_Y1 = 0.1f;
    private static final float SLIDE_FADE_Y2 = 1.0f;
    private static final float SLIDE_IN_X1 = 0.0f;
    private static final float SLIDE_IN_X2 = 0.0f;
    private static final float SLIDE_IN_Y1 = 1.0f;
    private static final float SLIDE_IN_Y2 = 1.0f;

    public AnimatorSet resetScroll(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500L);
        animatorSet.play(scrollToTop(view));
        return animatorSet;
    }

    public AnimatorSet fadeOutToTop(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500L);
        animatorSet.playTogether(translateUp(view), fadeOut(view));
        return animatorSet;
    }

    public AnimatorSet fadeOutToBottom(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500L);
        animatorSet.playTogether(translateDown(view), fadeOut(view));
        return animatorSet;
    }

    public AnimatorSet fadeInFromBottom(View view) {
        view.setY(view.getY() + OFFSET);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500L);
        animatorSet.playTogether(translateUp(view), fadeIn(view));
        return animatorSet;
    }

    public AnimatorSet fadeInFromTop(View view) {
        view.setY(view.getY() - OFFSET);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500L);
        animatorSet.playTogether(translateDown(view), fadeIn(view));
        return animatorSet;
    }

    public AnimatorSet fadeInBackgroundImage(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500L);
        animatorSet.play(fadeIn(view));
        return animatorSet;
    }

    private ObjectAnimator translateUp(View view) {
        return ObjectAnimator.ofFloat(view, "y", view.getY() - OFFSET);
    }

    private ObjectAnimator translateDown(View view) {
        return ObjectAnimator.ofFloat(view, "y", view.getY() + OFFSET);
    }

    public AnimatorSet slideInFadeInEntrance(View view, float f, int i) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translate(view, PathInterpolatorCompat.create(0.25f, SLIDE_FADE_Y1, 0.25f, 1.0f), f, i), fadeIn(view));
        return animatorSet;
    }

    public AnimatorSet slideOutFadeOutExit(View view, float f, int i) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translate(view, PathInterpolatorCompat.create(CUBIC_IN_X1, 0.0f, CUBIC_IN_X2, CUBIC_IN_Y2), f, i), fadeOut(view));
        return animatorSet;
    }

    public AnimatorSet fadeOutCubicInExit(View view, long j) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(PathInterpolatorCompat.create(CUBIC_IN_X1, 0.0f, CUBIC_IN_X2, CUBIC_IN_Y2));
        animatorSet.setDuration(j);
        animatorSet.play(fadeOut(view));
        return animatorSet;
    }

    public AnimatorSet fadeInSlideInEntry(View view, long j) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(PathInterpolatorCompat.create(0.0f, 1.0f, 0.0f, 1.0f));
        animatorSet.setDuration(j);
        animatorSet.play(fadeIn(view));
        return animatorSet;
    }

    public AnimatorSet fadeInCubicInEntry(View view, long j) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(PathInterpolatorCompat.create(CUBIC_IN_X1, 0.0f, CUBIC_IN_X2, CUBIC_IN_Y2));
        animatorSet.setDuration(j);
        animatorSet.play(fadeIn(view));
        return animatorSet;
    }

    private ObjectAnimator translate(View view, Interpolator interpolator, float f, long j) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "y", view.getY() + f);
        objectAnimatorOfFloat.setDuration(j);
        objectAnimatorOfFloat.setInterpolator(interpolator);
        return objectAnimatorOfFloat;
    }

    private ObjectAnimator scaleX(View view, Interpolator interpolator, float f, float f2, long j) {
        ObjectAnimator objectAnimatorOfFloat;
        if (f != -1.0f) {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "scaleX", f, f2);
        } else {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "scaleX", f2);
        }
        objectAnimatorOfFloat.setDuration(j);
        objectAnimatorOfFloat.setInterpolator(interpolator);
        return objectAnimatorOfFloat;
    }

    private ObjectAnimator scaleY(View view, Interpolator interpolator, float f, float f2, long j) {
        ObjectAnimator objectAnimatorOfFloat;
        if (f != -1.0f) {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "scaleY", f, f2);
        } else {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "scaleY", f2);
        }
        objectAnimatorOfFloat.setDuration(j);
        objectAnimatorOfFloat.setInterpolator(interpolator);
        return objectAnimatorOfFloat;
    }

    private ObjectAnimator fadeIn(View view) {
        return ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
    }

    private ObjectAnimator fadeOut(View view) {
        return ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f);
    }

    private ObjectAnimator scrollToTop(View view) {
        return ObjectAnimator.ofInt(view, "scrollY", 0);
    }
}
