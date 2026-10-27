package com.android.launcher3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.android.systemui.shared.system.RemoteAnimationRunnerCompat;
import com.android.systemui.shared.system.RemoteAnimationTargetCompat;

/* JADX INFO: loaded from: classes.dex */
public abstract class LauncherAnimationRunner implements RemoteAnimationRunnerCompat, WrappedAnimationRunnerImpl {
    private static final String TAG = "LauncherAnimationRunner";
    private AnimationResult mAnimationResult;
    private final Handler mHandler;
    private final boolean mStartAtFrontOfQueue;

    @Override // com.android.launcher3.WrappedAnimationRunnerImpl
    public abstract void onCreateAnimation(RemoteAnimationTargetCompat[] appTargets, RemoteAnimationTargetCompat[] wallpaperTargets, AnimationResult result);

    public LauncherAnimationRunner(Handler handler, boolean startAtFrontOfQueue) {
        this.mHandler = handler;
        this.mStartAtFrontOfQueue = startAtFrontOfQueue;
    }

    @Override // com.android.launcher3.WrappedAnimationRunnerImpl
    public Handler getHandler() {
        return this.mHandler;
    }

    public static void postAsyncCallback(Handler handler, Runnable callback) {
        Message messageObtain = Message.obtain(handler, callback);
        messageObtain.setAsynchronous(true);
        handler.sendMessage(messageObtain);
    }

    public static void postAtFrontOfQueueAsynchronously(Handler h, Runnable r) {
        h.sendMessageAtFrontOfQueue(h.obtainMessage().setCallback(r));
    }

    @Override // com.android.systemui.shared.system.RemoteAnimationRunnerCompat
    public void onAnimationStart(final RemoteAnimationTargetCompat[] appTargets, final RemoteAnimationTargetCompat[] wallpaperTargets, final Runnable runnable) {
        Runnable runnable2 = new Runnable() { // from class: com.android.launcher3.-$$Lambda$LauncherAnimationRunner$J43QECvbm4A5M7oPzYZap2S44QY
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onAnimationStart$1$LauncherAnimationRunner(runnable, appTargets, wallpaperTargets);
            }
        };
        if (this.mStartAtFrontOfQueue) {
            postAtFrontOfQueueAsynchronously(this.mHandler, runnable2);
        } else {
            postAsyncCallback(this.mHandler, runnable2);
        }
    }

    public /* synthetic */ void lambda$onAnimationStart$1$LauncherAnimationRunner(final Runnable runnable, RemoteAnimationTargetCompat[] remoteAnimationTargetCompatArr, RemoteAnimationTargetCompat[] remoteAnimationTargetCompatArr2) {
        finishExistingAnimation();
        AnimationResult animationResult = new AnimationResult(new Runnable() { // from class: com.android.launcher3.-$$Lambda$LauncherAnimationRunner$hmGgrdPLzMYT-hYYtY9kHk809PE
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onAnimationStart$0$LauncherAnimationRunner(runnable);
            }
        });
        this.mAnimationResult = animationResult;
        onCreateAnimation(remoteAnimationTargetCompatArr, remoteAnimationTargetCompatArr2, animationResult);
    }

    public /* synthetic */ void lambda$onAnimationStart$0$LauncherAnimationRunner(Runnable runnable) {
        runnable.run();
        this.mAnimationResult = null;
    }

    @Deprecated
    public void onAnimationStart(RemoteAnimationTargetCompat[] appTargets, Runnable runnable) {
        onAnimationStart(appTargets, new RemoteAnimationTargetCompat[0], runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishExistingAnimation() {
        AnimationResult animationResult = this.mAnimationResult;
        if (animationResult != null) {
            animationResult.finish();
            this.mAnimationResult = null;
        }
    }

    @Override // com.android.systemui.shared.system.RemoteAnimationRunnerCompat
    public void onAnimationCancelled() {
        postAsyncCallback(this.mHandler, new Runnable() { // from class: com.android.launcher3.-$$Lambda$LauncherAnimationRunner$bCW3MpRWbzzWwjKeihVd8hXMaUw
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.finishExistingAnimation();
            }
        });
    }

    public static final class AnimationResult {
        private AnimatorSet mAnimator;
        private final Runnable mFinishRunnable;
        private boolean mFinished;
        private boolean mInitialized;

        private AnimationResult(Runnable finishRunnable) {
            this.mFinished = false;
            this.mInitialized = false;
            this.mFinishRunnable = finishRunnable;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void finish() {
            if (this.mFinished) {
                return;
            }
            this.mFinishRunnable.run();
            this.mFinished = true;
        }

        public void setAnimation(AnimatorSet animation, Context context) {
            if (this.mInitialized) {
                throw new IllegalStateException("Animation already initialized");
            }
            this.mInitialized = true;
            this.mAnimator = animation;
            if (animation == null) {
                finish();
            } else if (this.mFinished) {
                animation.start();
                this.mAnimator.end();
            } else {
                animation.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.LauncherAnimationRunner.AnimationResult.1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animation2) {
                        AnimationResult.this.finish();
                    }
                });
                this.mAnimator.start();
            }
        }
    }
}
