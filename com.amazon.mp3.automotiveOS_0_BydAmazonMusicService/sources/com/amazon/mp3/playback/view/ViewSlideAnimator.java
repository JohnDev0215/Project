package com.amazon.mp3.playback.view;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;

/* JADX INFO: loaded from: classes3.dex */
public class ViewSlideAnimator {
    private static int DEFAULT_TRANSITION_DURATION = 200;
    private View mCurrentView;
    private ViewPropertyAnimator mCurrentViewPropertyAnimator;
    private View mReplacementView;
    private ViewPropertyAnimator mReplacementViewPropertyAnimator;
    private TimeInterpolator mTimeInterpolator = new LinearInterpolator();
    private int transitionDuration = DEFAULT_TRANSITION_DURATION;

    public enum ViewSlideAction {
        LEFT,
        RIGHT,
        UP,
        DOWN,
        NONE
    }

    public ViewSlideAnimator(final View currentView) {
        this.mCurrentView = currentView;
    }

    public void setCurrentView(final View currentView) {
        this.mCurrentView = currentView;
    }

    public void setReplacementView(final View replacementView) {
        this.mReplacementView = replacementView;
    }

    public void setTransitionDuration(final int transitionDuration) {
        this.transitionDuration = transitionDuration;
    }

    public void executeSwipe(ViewSlideAction action) {
        View view = this.mCurrentView;
        if (view == null || action == ViewSlideAction.NONE) {
            return;
        }
        view.setVisibility(0);
        View view2 = this.mReplacementView;
        if (view2 != null) {
            view2.setVisibility(4);
        }
        cancelAnimations();
        float width = this.mCurrentView.getWidth();
        float height = this.mCurrentView.getHeight();
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$playback$view$ViewSlideAnimator$ViewSlideAction[action.ordinal()];
        if (i == 1) {
            animateViewHorizontally(-width);
            return;
        }
        if (i == 2) {
            animateViewHorizontally(width);
        } else if (i == 3) {
            animateViewVertically(-height);
        } else {
            if (i != 4) {
                return;
            }
            animateViewVertically(height);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.playback.view.ViewSlideAnimator$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$playback$view$ViewSlideAnimator$ViewSlideAction;

        static {
            int[] iArr = new int[ViewSlideAction.values().length];
            $SwitchMap$com$amazon$mp3$playback$view$ViewSlideAnimator$ViewSlideAction = iArr;
            try {
                iArr[ViewSlideAction.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$ViewSlideAnimator$ViewSlideAction[ViewSlideAction.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$ViewSlideAnimator$ViewSlideAction[ViewSlideAction.UP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$ViewSlideAnimator$ViewSlideAction[ViewSlideAction.DOWN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$view$ViewSlideAnimator$ViewSlideAction[ViewSlideAction.NONE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public void cancelAnimations() {
        ViewPropertyAnimator viewPropertyAnimator = this.mReplacementViewPropertyAnimator;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
        ViewPropertyAnimator viewPropertyAnimator2 = this.mCurrentViewPropertyAnimator;
        if (viewPropertyAnimator2 != null) {
            viewPropertyAnimator2.cancel();
        }
    }

    private void animateViewHorizontally(final float width) {
        this.mCurrentView.setTranslationX(0.0f);
        ViewPropertyAnimator viewPropertyAnimatorAnimate = this.mCurrentView.animate();
        this.mCurrentViewPropertyAnimator = viewPropertyAnimatorAnimate;
        int i = 0;
        AnonymousClass1 anonymousClass1 = null;
        viewPropertyAnimatorAnimate.translationXBy(width).setDuration(this.transitionDuration).setInterpolator(this.mTimeInterpolator).setListener(new ViewAnimation(this, this.mCurrentView, this.mReplacementView != null ? 4 : 0, anonymousClass1));
        View view = this.mReplacementView;
        if (view != null) {
            view.setTranslationX(-width);
            ViewAnimation viewAnimation = new ViewAnimation(this, this.mReplacementView, i, anonymousClass1);
            ViewPropertyAnimator viewPropertyAnimatorAnimate2 = this.mReplacementView.animate();
            this.mReplacementViewPropertyAnimator = viewPropertyAnimatorAnimate2;
            viewPropertyAnimatorAnimate2.translationXBy(width).setDuration(this.transitionDuration).setInterpolator(this.mTimeInterpolator).setListener(viewAnimation);
        }
    }

    private void animateViewVertically(final float height) {
        this.mCurrentView.setTranslationY(0.0f);
        ViewPropertyAnimator viewPropertyAnimatorAnimate = this.mCurrentView.animate();
        this.mCurrentViewPropertyAnimator = viewPropertyAnimatorAnimate;
        int i = 0;
        AnonymousClass1 anonymousClass1 = null;
        viewPropertyAnimatorAnimate.translationYBy(height).setDuration(this.transitionDuration).setInterpolator(this.mTimeInterpolator).setListener(new ViewAnimation(this, this.mCurrentView, this.mReplacementView != null ? 4 : 0, anonymousClass1));
        View view = this.mReplacementView;
        if (view != null) {
            view.setTranslationY(-height);
            ViewAnimation viewAnimation = new ViewAnimation(this, this.mReplacementView, i, anonymousClass1);
            ViewPropertyAnimator viewPropertyAnimatorAnimate2 = this.mReplacementView.animate();
            this.mReplacementViewPropertyAnimator = viewPropertyAnimatorAnimate2;
            viewPropertyAnimatorAnimate2.translationYBy(height).setDuration(this.transitionDuration).setInterpolator(this.mTimeInterpolator).setListener(viewAnimation);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCurrentView() {
        this.mCurrentView.animate().cancel();
        this.mCurrentView.animate().setListener(null);
        View view = this.mReplacementView;
        if (view != null) {
            this.mCurrentView = view;
            view.animate().setListener(null);
            this.mReplacementView = null;
        }
    }

    private class ViewAnimation implements Animator.AnimatorListener {
        int endVisibility;
        View v;

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animation) {
        }

        /* synthetic */ ViewAnimation(ViewSlideAnimator viewSlideAnimator, View view, int i, AnonymousClass1 anonymousClass1) {
            this(view, i);
        }

        private ViewAnimation(final View v, final int endVisibility) {
            this.v = v;
            this.endVisibility = endVisibility;
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animation) {
            this.v.setVisibility(0);
        }

        private void restoreView(View v) {
            v.setVisibility(this.endVisibility);
            v.setTranslationX(0.0f);
            v.setTranslationY(0.0f);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation) {
            restoreView(this.v);
            ViewSlideAnimator.this.updateCurrentView();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animation) {
            restoreView(this.v);
        }
    }
}
