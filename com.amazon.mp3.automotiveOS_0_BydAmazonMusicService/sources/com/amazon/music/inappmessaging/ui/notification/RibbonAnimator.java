package com.amazon.music.inappmessaging.ui.notification;

import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.amazon.music.inappmessaging.R;
import com.amazon.music.inappmessaging.ui.notification.Ribbon;

/* JADX INFO: loaded from: classes4.dex */
public final class RibbonAnimator {
    private Runnable animation;
    private final Ribbon.Duration duration;
    private final Ribbon ribbon;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private State state = State.INVISIBLE;

    private enum State {
        VISIBLE,
        INVISIBLE,
        TRANSITION
    }

    public RibbonAnimator(final Ribbon ribbon, final Ribbon.Duration duration) {
        this.ribbon = ribbon;
        this.duration = duration;
    }

    public void showRibbon() {
        ensureUIThread();
        if (this.state == State.INVISIBLE) {
            this.ribbon.setVisibility(4);
            this.state = State.TRANSITION;
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.ribbon.getContext(), R.anim.ribbon_animation_appear);
            animationLoadAnimation.setAnimationListener(new AnimationListener() { // from class: com.amazon.music.inappmessaging.ui.notification.RibbonAnimator.1
                @Override // com.amazon.music.inappmessaging.ui.notification.AnimationListener, android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    RibbonAnimator.this.scheduleRibbonDismissal();
                    RibbonAnimator.this.state = State.VISIBLE;
                }
            });
            this.ribbon.startAnimation(animationLoadAnimation);
        }
    }

    public void dismissRibbon() {
        ensureUIThread();
        if (State.VISIBLE == this.state) {
            this.state = State.TRANSITION;
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.ribbon.getContext(), R.anim.ribbon_animation_disappear);
            this.ribbon.startAnimation(animationLoadAnimation);
            animationLoadAnimation.setAnimationListener(new AnimationListener() { // from class: com.amazon.music.inappmessaging.ui.notification.RibbonAnimator.2
                @Override // com.amazon.music.inappmessaging.ui.notification.AnimationListener, android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    RibbonAnimator.this.cancelScheduledRibbonDismissal();
                    RibbonAnimator.this.state = State.INVISIBLE;
                    RibbonAnimator.this.ribbon.setVisibility(8);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleRibbonDismissal() {
        if (Ribbon.Duration.INDEFINITE != this.duration) {
            Runnable runnable = new Runnable() { // from class: com.amazon.music.inappmessaging.ui.notification.RibbonAnimator.3
                @Override // java.lang.Runnable
                public void run() {
                    RibbonAnimator.this.dismissRibbon();
                }
            };
            this.animation = runnable;
            this.handler.postDelayed(runnable, this.duration.time * 1000);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelScheduledRibbonDismissal() {
        this.handler.removeCallbacks(this.animation);
        this.animation = null;
    }

    private void ensureUIThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new RuntimeException("RibbonAnimator must be used in UI Thread");
        }
    }
}
