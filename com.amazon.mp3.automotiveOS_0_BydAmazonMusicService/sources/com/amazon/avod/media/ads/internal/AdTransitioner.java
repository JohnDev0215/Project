package com.amazon.avod.media.ads.internal;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.platform.Handlers;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AdTransitioner {
    private final ExecutorService mExecutor;
    private ViewGroup mParentViewGroup;
    private final Animation mTransitionInAnimation;
    private final Animation mTransitionOutAnimation;
    private View mTransitionView;
    private final Handler mUiHandler;

    public AdTransitioner(ExecutorService executorService) {
        this(Handlers.getUIHandler(), null, executorService, createAlphaAnimation(1.0f, 0.0f, new AccelerateInterpolator()), createAlphaAnimation(0.0f, 1.0f, new DecelerateInterpolator()));
    }

    AdTransitioner(@Nonnull Handler handler, @Nullable View view, @Nonnull ExecutorService executorService, @Nonnull Animation animation, @Nonnull Animation animation2) {
        this.mUiHandler = (Handler) Preconditions.checkNotNull(handler, "uiHandler");
        this.mTransitionView = view;
        this.mExecutor = (ExecutorService) Preconditions.checkNotNull(executorService, "executor");
        this.mTransitionOutAnimation = (Animation) Preconditions.checkNotNull(animation2, "transitionOutAnimation");
        this.mTransitionInAnimation = (Animation) Preconditions.checkNotNull(animation, "transitionInAnimation");
    }

    public void initialize(@Nullable final ViewGroup viewGroup) {
        Preconditions.checkState(this.mParentViewGroup == null, "Initialize may only be called once.");
        if (viewGroup == null) {
            DLog.warnf("AdTransitioner disabled as the parent view group is null.");
            return;
        }
        this.mParentViewGroup = (ViewGroup) Preconditions.checkNotNull(viewGroup);
        if (this.mTransitionView == null) {
            this.mTransitionView = new View(viewGroup.getContext());
        }
        this.mUiHandler.post(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdTransitioner$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$initialize$0(viewGroup);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initialize$0(ViewGroup viewGroup) {
        this.mTransitionView.setVisibility(8);
        this.mTransitionView.setBackgroundColor(Color.rgb(0, 0, 0));
        viewGroup.addView(this.mTransitionView, 0, new RelativeLayout.LayoutParams(-1, -1));
    }

    public void terminate() {
        final ViewGroup viewGroup = this.mParentViewGroup;
        this.mParentViewGroup = null;
        if (viewGroup != null) {
            this.mUiHandler.post(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdTransitioner$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$terminate$1(viewGroup);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$terminate$1(ViewGroup viewGroup) {
        viewGroup.removeView(this.mTransitionView);
    }

    public void transitionOut(TimeSpan timeSpan, Runnable runnable) {
        startTransition(this.mTransitionOutAnimation, timeSpan, runnable, false);
    }

    public void transitionIn(TimeSpan timeSpan, Runnable runnable) {
        startTransition(this.mTransitionInAnimation, timeSpan, runnable, true);
    }

    private static AlphaAnimation createAlphaAnimation(float f, float f2, Interpolator interpolator) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setInterpolator(interpolator);
        alphaAnimation.setFillEnabled(true);
        alphaAnimation.setFillAfter(true);
        return alphaAnimation;
    }

    private void startTransition(final Animation animation, final TimeSpan timeSpan, final Runnable runnable, final boolean z) {
        this.mUiHandler.post(new Runnable() { // from class: com.amazon.avod.media.ads.internal.AdTransitioner$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$startTransition$2(animation, runnable, z, timeSpan);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startTransition$2(Animation animation, Runnable runnable, boolean z, TimeSpan timeSpan) {
        animation.setAnimationListener(new TransitionAnimationListener(runnable, z));
        animation.setDuration(timeSpan.getTotalMilliseconds());
        if (this.mTransitionView.getVisibility() == 8) {
            this.mTransitionView.setVisibility(4);
        }
        this.mTransitionView.startAnimation(animation);
    }

    class TransitionAnimationListener implements Animation.AnimationListener {
        private final Runnable mCompletionCallback;
        private final boolean mHideTransitionView;

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        public TransitionAnimationListener(Runnable runnable, boolean z) {
            this.mCompletionCallback = runnable;
            this.mHideTransitionView = z;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            if (this.mHideTransitionView) {
                AdTransitioner.this.mTransitionView.setVisibility(8);
            }
            if (this.mCompletionCallback != null) {
                AdTransitioner.this.mExecutor.submit(this.mCompletionCallback);
            }
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
            AdTransitioner.this.mTransitionView.setVisibility(0);
        }
    }
}
