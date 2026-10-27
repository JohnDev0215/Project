package com.android.launcher3.touch;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherAnimUtils;
import com.android.launcher3.LauncherState;
import com.android.launcher3.Utilities;
import com.android.launcher3.anim.AnimationSuccessListener;
import com.android.launcher3.anim.AnimatorPlaybackController;
import com.android.launcher3.anim.AnimatorSetBuilder;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.config.FeatureFlags;
import com.android.launcher3.testing.TestProtocol;
import com.android.launcher3.touch.SwipeDetector;
import com.android.launcher3.util.FlingBlockCheck;
import com.android.launcher3.util.PendingAnimation;
import com.android.launcher3.util.TouchController;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractStateChangeTouchController implements TouchController, SwipeDetector.Listener {
    public static final float ATOMIC_OVERVIEW_ANIM_THRESHOLD = 0.5f;
    public static final float SUCCESS_TRANSITION_PROGRESS = 0.5f;
    private static final String TAG = "ASCTouchController";
    protected AnimatorSet mAtomicAnim;
    private AutoPlayAtomicAnimationInfo mAtomicAnimAutoPlayInfo;
    private AnimatorPlaybackController mAtomicComponentsController;
    private float mAtomicComponentsStartProgress;
    private boolean mCanBlockFling;
    protected AnimatorPlaybackController mCurrentAnimation;
    protected final SwipeDetector mDetector;
    private float mDisplacementShift;
    protected LauncherState mFromState;
    protected final Launcher mLauncher;
    private boolean mNoIntercept;
    private boolean mPassedOverviewAtomicThreshold;
    protected PendingAnimation mPendingAnimation;
    private float mProgressMultiplier;
    private boolean mScheduleResumeAtomicComponent;
    protected int mStartContainerType;
    private float mStartProgress;
    protected LauncherState mStartState;
    protected final SwipeDetector.Direction mSwipeDirection;
    protected LauncherState mToState;
    protected final long ATOMIC_DURATION = getAtomicDuration();
    private FlingBlockCheck mFlingBlockCheck = new FlingBlockCheck();
    private LauncherState mAtomicComponentsTargetState = LauncherState.NORMAL;

    protected abstract boolean canInterceptTouch(MotionEvent ev);

    protected long getAtomicDuration() {
        return 200L;
    }

    protected abstract int getLogContainerTypeForNormalState();

    protected abstract LauncherState getTargetState(LauncherState fromState, boolean isDragTowardPositive);

    protected abstract float initCurrentAnimation(int animComponents);

    public AbstractStateChangeTouchController(Launcher l, SwipeDetector.Direction dir) {
        this.mLauncher = l;
        this.mDetector = new SwipeDetector(l, this, dir);
        this.mSwipeDirection = dir;
    }

    @Override // com.android.launcher3.util.TouchController
    public final boolean onControllerInterceptTouchEvent(MotionEvent ev) {
        int swipeDirection;
        if (TestProtocol.sDebugTracing) {
            Log.d(TestProtocol.NO_ALLAPPS_EVENT_TAG, "onControllerInterceptTouchEvent 1 " + ev);
        }
        if (ev.getAction() == 0) {
            boolean z = true;
            boolean z2 = !canInterceptTouch(ev);
            this.mNoIntercept = z2;
            if (z2) {
                return false;
            }
            if (this.mCurrentAnimation != null) {
                swipeDirection = 3;
            } else {
                swipeDirection = getSwipeDirection();
                if (swipeDirection == 0) {
                    this.mNoIntercept = true;
                    return false;
                }
                z = false;
            }
            this.mDetector.setDetectableScrollConditions(swipeDirection, z);
        }
        if (this.mNoIntercept) {
            return false;
        }
        if (TestProtocol.sDebugTracing) {
            Log.d(TestProtocol.NO_ALLAPPS_EVENT_TAG, "onControllerInterceptTouchEvent 2 ");
        }
        onControllerTouchEvent(ev);
        return this.mDetector.isDraggingOrSettling();
    }

    private int getSwipeDirection() {
        LauncherState state = this.mLauncher.getStateManager().getState();
        int i = getTargetState(state, true) == state ? 0 : 1;
        return getTargetState(state, false) != state ? i | 2 : i;
    }

    @Override // com.android.launcher3.util.TouchController
    public final boolean onControllerTouchEvent(MotionEvent ev) {
        return this.mDetector.onTouchEvent(ev);
    }

    protected float getShiftRange() {
        return this.mLauncher.getAllAppsController().getShiftRange();
    }

    private boolean reinitCurrentAnimation(boolean reachedToState, boolean isDragTowardPositive) {
        LauncherState state = this.mFromState;
        if (state == null) {
            state = this.mLauncher.getStateManager().getState();
        } else if (reachedToState) {
            state = this.mToState;
        }
        LauncherState targetState = getTargetState(state, isDragTowardPositive);
        if ((state == this.mFromState && targetState == this.mToState) || state == targetState) {
            return false;
        }
        this.mFromState = state;
        this.mToState = targetState;
        this.mStartProgress = 0.0f;
        this.mPassedOverviewAtomicThreshold = false;
        AnimatorPlaybackController animatorPlaybackController = this.mCurrentAnimation;
        if (animatorPlaybackController != null) {
            animatorPlaybackController.setOnCancelRunnable(null);
        }
        int i = goingBetweenNormalAndOverview(this.mFromState, this.mToState) ? 1 : 7;
        this.mScheduleResumeAtomicComponent = false;
        if (this.mAtomicAnim != null) {
            this.mScheduleResumeAtomicComponent = true;
            i = 1;
        }
        if (goingBetweenNormalAndOverview(this.mFromState, this.mToState) || this.mAtomicComponentsTargetState != this.mToState) {
            cancelAtomicComponentsController();
        }
        if (this.mAtomicComponentsController != null) {
            i &= -3;
        }
        this.mProgressMultiplier = initCurrentAnimation(i);
        this.mCurrentAnimation.dispatchOnStart();
        return true;
    }

    protected boolean goingBetweenNormalAndOverview(LauncherState fromState, LauncherState toState) {
        return (fromState == LauncherState.NORMAL || fromState == LauncherState.OVERVIEW) && (toState == LauncherState.NORMAL || toState == LauncherState.OVERVIEW) && this.mPendingAnimation == null;
    }

    @Override // com.android.launcher3.touch.SwipeDetector.Listener
    public void onDragStart(boolean start) {
        if (TestProtocol.sDebugTracing) {
            Log.d(TestProtocol.NO_ALLAPPS_EVENT_TAG, "onDragStart 1 " + start);
        }
        LauncherState state = this.mLauncher.getStateManager().getState();
        this.mStartState = state;
        if (state == LauncherState.ALL_APPS) {
            this.mStartContainerType = 4;
        } else if (this.mStartState == LauncherState.NORMAL) {
            this.mStartContainerType = getLogContainerTypeForNormalState();
        } else if (this.mStartState == LauncherState.OVERVIEW) {
            this.mStartContainerType = 12;
        }
        AnimatorPlaybackController animatorPlaybackController = this.mCurrentAnimation;
        if (animatorPlaybackController == null) {
            if (TestProtocol.sDebugTracing) {
                Log.d(TestProtocol.NO_ALLAPPS_EVENT_TAG, "onDragStart 2");
            }
            this.mFromState = this.mStartState;
            this.mToState = null;
            cancelAnimationControllers();
            reinitCurrentAnimation(false, this.mDetector.wasInitialTouchPositive());
            this.mDisplacementShift = 0.0f;
        } else {
            animatorPlaybackController.pause();
            this.mStartProgress = this.mCurrentAnimation.getProgressFraction();
            this.mAtomicAnimAutoPlayInfo = null;
            AnimatorPlaybackController animatorPlaybackController2 = this.mAtomicComponentsController;
            if (animatorPlaybackController2 != null) {
                animatorPlaybackController2.pause();
            }
        }
        this.mCanBlockFling = this.mFromState == LauncherState.NORMAL;
        this.mFlingBlockCheck.unblockFling();
    }

    @Override // com.android.launcher3.touch.SwipeDetector.Listener
    public boolean onDrag(float displacement) {
        float f = (this.mProgressMultiplier * (displacement - this.mDisplacementShift)) + this.mStartProgress;
        updateProgress(f);
        boolean zIsPositive = this.mSwipeDirection.isPositive(displacement - this.mDisplacementShift);
        if (f <= 0.0f) {
            if (reinitCurrentAnimation(false, zIsPositive)) {
                this.mDisplacementShift = displacement;
                if (this.mCanBlockFling) {
                    this.mFlingBlockCheck.blockFling();
                }
            }
        } else if (f >= 1.0f) {
            if (reinitCurrentAnimation(true, zIsPositive)) {
                this.mDisplacementShift = displacement;
                if (this.mCanBlockFling) {
                    this.mFlingBlockCheck.blockFling();
                }
            }
        } else {
            this.mFlingBlockCheck.onEvent();
        }
        return true;
    }

    protected void updateProgress(float fraction) {
        this.mCurrentAnimation.setPlayFraction(fraction);
        if (this.mAtomicComponentsController != null) {
            float fMin = Math.min(this.mAtomicComponentsStartProgress, 0.9f);
            this.mAtomicComponentsController.setPlayFraction((fraction - fMin) / (1.0f - fMin));
        }
        maybeUpdateAtomicAnim(this.mFromState, this.mToState, fraction);
    }

    private void maybeUpdateAtomicAnim(LauncherState fromState, LauncherState toState, float progress) {
        if (goingBetweenNormalAndOverview(fromState, toState)) {
            LauncherState launcherState = LauncherState.OVERVIEW;
            boolean z = progress >= 0.5f;
            if (z != this.mPassedOverviewAtomicThreshold) {
                LauncherState launcherState2 = z ? fromState : toState;
                if (z) {
                    fromState = toState;
                }
                this.mPassedOverviewAtomicThreshold = z;
                AnimatorSet animatorSet = this.mAtomicAnim;
                if (animatorSet != null) {
                    animatorSet.cancel();
                }
                AnimatorSet animatorSetCreateAtomicAnimForState = createAtomicAnimForState(launcherState2, fromState, this.ATOMIC_DURATION);
                this.mAtomicAnim = animatorSetCreateAtomicAnimForState;
                animatorSetCreateAtomicAnimForState.addListener(new AnimationSuccessListener() { // from class: com.android.launcher3.touch.AbstractStateChangeTouchController.1
                    @Override // com.android.launcher3.anim.AnimationSuccessListener, android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        AbstractStateChangeTouchController.this.mAtomicAnim = null;
                        AbstractStateChangeTouchController.this.mScheduleResumeAtomicComponent = false;
                    }

                    @Override // com.android.launcher3.anim.AnimationSuccessListener
                    public void onAnimationSuccess(Animator animator) {
                        if (AbstractStateChangeTouchController.this.mScheduleResumeAtomicComponent) {
                            AbstractStateChangeTouchController.this.cancelAtomicComponentsController();
                            if (AbstractStateChangeTouchController.this.mCurrentAnimation != null) {
                                AbstractStateChangeTouchController abstractStateChangeTouchController = AbstractStateChangeTouchController.this;
                                abstractStateChangeTouchController.mAtomicComponentsStartProgress = abstractStateChangeTouchController.mCurrentAnimation.getProgressFraction();
                                long shiftRange = (long) (AbstractStateChangeTouchController.this.getShiftRange() * 2.0f);
                                AbstractStateChangeTouchController abstractStateChangeTouchController2 = AbstractStateChangeTouchController.this;
                                abstractStateChangeTouchController2.mAtomicComponentsController = AnimatorPlaybackController.wrap(abstractStateChangeTouchController2.createAtomicAnimForState(abstractStateChangeTouchController2.mFromState, AbstractStateChangeTouchController.this.mToState, shiftRange), shiftRange);
                                AbstractStateChangeTouchController.this.mAtomicComponentsController.dispatchOnStart();
                                AbstractStateChangeTouchController abstractStateChangeTouchController3 = AbstractStateChangeTouchController.this;
                                abstractStateChangeTouchController3.mAtomicComponentsTargetState = abstractStateChangeTouchController3.mToState;
                                AbstractStateChangeTouchController.this.maybeAutoPlayAtomicComponentsAnim();
                            }
                        }
                    }
                });
                this.mAtomicAnim.start();
                this.mLauncher.getDragLayer().performHapticFeedback(1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AnimatorSet createAtomicAnimForState(LauncherState fromState, LauncherState targetState, long duration) {
        return this.mLauncher.getStateManager().createAtomicAnimation(fromState, targetState, getAnimatorSetBuilderForStates(fromState, targetState), 2, duration);
    }

    protected AnimatorSetBuilder getAnimatorSetBuilderForStates(LauncherState fromState, LauncherState toState) {
        return new AnimatorSetBuilder();
    }

    @Override // com.android.launcher3.touch.SwipeDetector.Listener
    public void onDragEnd(float velocity, boolean fling) {
        LauncherState launcherState;
        float fBoundToRange;
        long jCalculateDuration;
        float f;
        if (TestProtocol.sDebugTracing) {
            Log.e(TestProtocol.NO_ALLAPPS_EVENT_TAG, "onDragEnd");
        }
        final int i = fling ? 4 : 3;
        boolean z = fling && this.mFlingBlockCheck.isBlocked();
        boolean z2 = z ? false : fling;
        float progressFraction = this.mCurrentAnimation.getProgressFraction();
        float interpolatedProgress = this.mCurrentAnimation.getInterpolatedProgress();
        if (z2) {
            launcherState = Float.compare(Math.signum(velocity), Math.signum(this.mProgressMultiplier)) == 0 ? this.mToState : this.mFromState;
        } else {
            LauncherState launcherState2 = this.mToState;
            LauncherState launcherState3 = LauncherState.ALL_APPS;
            launcherState = interpolatedProgress > 0.5f ? this.mToState : this.mFromState;
        }
        final LauncherState launcherState4 = launcherState;
        int iBlockedFlingDurationFactor = (z && launcherState4 == this.mFromState) ? LauncherAnimUtils.blockedFlingDurationFactor(velocity) : 1;
        if (launcherState4 != this.mToState) {
            Runnable onCancelRunnable = this.mCurrentAnimation.getOnCancelRunnable();
            this.mCurrentAnimation.setOnCancelRunnable(null);
            this.mCurrentAnimation.dispatchOnCancel();
            this.mCurrentAnimation.setOnCancelRunnable(onCancelRunnable);
            if (progressFraction <= 0.0f) {
                fBoundToRange = 0.0f;
                f = fBoundToRange;
                jCalculateDuration = 0;
            } else {
                fBoundToRange = Utilities.boundToRange((velocity * 16.0f * this.mProgressMultiplier) + progressFraction, 0.0f, 1.0f);
                jCalculateDuration = SwipeDetector.calculateDuration(velocity, Math.min(progressFraction, 1.0f) - 0.0f) * ((long) iBlockedFlingDurationFactor);
                f = 0.0f;
            }
        } else if (progressFraction >= 1.0f) {
            fBoundToRange = 1.0f;
            f = fBoundToRange;
            jCalculateDuration = 0;
        } else {
            fBoundToRange = Utilities.boundToRange((velocity * 16.0f * this.mProgressMultiplier) + progressFraction, 0.0f, 1.0f);
            jCalculateDuration = SwipeDetector.calculateDuration(velocity, 1.0f - Math.max(progressFraction, 0.0f)) * ((long) iBlockedFlingDurationFactor);
            f = 1.0f;
        }
        this.mCurrentAnimation.setEndAction(new Runnable() { // from class: com.android.launcher3.touch.-$$Lambda$AbstractStateChangeTouchController$E6zxyyhpepApPfWYvmgRg_N0rwA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onDragEnd$0$AbstractStateChangeTouchController(launcherState4, i);
            }
        });
        ValueAnimator animationPlayer = this.mCurrentAnimation.getAnimationPlayer();
        animationPlayer.setFloatValues(fBoundToRange, f);
        maybeUpdateAtomicAnim(this.mFromState, launcherState4, launcherState4 != this.mToState ? 0.0f : 1.0f);
        updateSwipeCompleteAnimation(animationPlayer, Math.max(jCalculateDuration, getRemainingAtomicDuration()), launcherState4, velocity, z2);
        this.mCurrentAnimation.dispatchOnStartWithVelocity(f, velocity);
        if (z2 && launcherState4 == LauncherState.ALL_APPS && !FeatureFlags.QUICKSTEP_SPRINGS.get()) {
            this.mLauncher.getAppsView().addSpringFromFlingUpdateListener(animationPlayer, velocity);
        }
        animationPlayer.start();
        this.mAtomicAnimAutoPlayInfo = new AutoPlayAtomicAnimationInfo(f, animationPlayer.getDuration());
        maybeAutoPlayAtomicComponentsAnim();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeAutoPlayAtomicComponentsAnim() {
        final AnimatorPlaybackController animatorPlaybackController = this.mAtomicComponentsController;
        if (animatorPlaybackController == null || this.mAtomicAnimAutoPlayInfo == null) {
            return;
        }
        ValueAnimator animationPlayer = animatorPlaybackController.getAnimationPlayer();
        animationPlayer.setFloatValues(animatorPlaybackController.getProgressFraction(), this.mAtomicAnimAutoPlayInfo.toProgress);
        long jElapsedRealtime = this.mAtomicAnimAutoPlayInfo.endTime - SystemClock.elapsedRealtime();
        this.mAtomicAnimAutoPlayInfo = null;
        if (jElapsedRealtime <= 0) {
            animationPlayer.start();
            animationPlayer.end();
            this.mAtomicComponentsController = null;
        } else {
            animationPlayer.setDuration(jElapsedRealtime);
            animationPlayer.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.touch.AbstractStateChangeTouchController.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    if (AbstractStateChangeTouchController.this.mAtomicComponentsController == animatorPlaybackController) {
                        AbstractStateChangeTouchController.this.mAtomicComponentsController = null;
                    }
                }
            });
            animationPlayer.start();
        }
    }

    private long getRemainingAtomicDuration() {
        long jMax = 0;
        if (this.mAtomicAnim == null) {
            return 0L;
        }
        if (Utilities.ATLEAST_OREO) {
            return this.mAtomicAnim.getTotalDuration() - this.mAtomicAnim.getCurrentPlayTime();
        }
        Iterator<Animator> it = this.mAtomicAnim.getChildAnimations().iterator();
        while (it.hasNext()) {
            jMax = Math.max(jMax, it.next().getDuration());
        }
        return jMax;
    }

    protected void updateSwipeCompleteAnimation(ValueAnimator animator, long expectedDuration, LauncherState targetState, float velocity, boolean isFling) {
        animator.setDuration(expectedDuration).setInterpolator(Interpolators.scrollInterpolatorForVelocity(velocity));
    }

    protected int getDirectionForLog() {
        return this.mToState.ordinal > this.mFromState.ordinal ? 1 : 2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: onSwipeInteractionCompleted, reason: merged with bridge method [inline-methods] */
    public void lambda$onDragEnd$0$AbstractStateChangeTouchController(LauncherState targetState, int logAction) {
        if (TestProtocol.sDebugTracing) {
            Log.e(TestProtocol.NO_ALLAPPS_EVENT_TAG, "onSwipeInteractionCompleted 1");
        }
        AnimatorPlaybackController animatorPlaybackController = this.mAtomicComponentsController;
        if (animatorPlaybackController != null) {
            animatorPlaybackController.getAnimationPlayer().end();
            this.mAtomicComponentsController = null;
        }
        cancelAnimationControllers();
        PendingAnimation pendingAnimation = this.mPendingAnimation;
        boolean z = true;
        if (pendingAnimation != null) {
            boolean z2 = this.mToState == targetState;
            pendingAnimation.finish(z2, logAction);
            this.mPendingAnimation = null;
            z = true ^ z2;
        }
        if (z) {
            if (targetState != this.mStartState) {
                logReachedState(logAction, targetState);
            }
            this.mLauncher.getStateManager().goToState(targetState, false);
            if (TestProtocol.sDebugTracing) {
                Log.e(TestProtocol.NO_ALLAPPS_EVENT_TAG, "onSwipeInteractionCompleted 2");
            }
        }
    }

    private void logReachedState(int logAction, LauncherState targetState) {
        this.mLauncher.getUserEventDispatcher().logStateChangeAction(logAction, getDirectionForLog(), this.mDetector.getDownX(), this.mDetector.getDownY(), this.mStartContainerType, this.mStartState.containerType, targetState.containerType, this.mLauncher.getWorkspace().getCurrentPage());
    }

    protected void clearState() {
        cancelAnimationControllers();
        AnimatorSet animatorSet = this.mAtomicAnim;
        if (animatorSet != null) {
            animatorSet.cancel();
            this.mAtomicAnim = null;
        }
        this.mScheduleResumeAtomicComponent = false;
    }

    private void cancelAnimationControllers() {
        if (TestProtocol.sDebugTracing) {
            Log.d(TestProtocol.NO_ALLAPPS_EVENT_TAG, "cancelAnimationControllers");
        }
        this.mCurrentAnimation = null;
        cancelAtomicComponentsController();
        this.mDetector.finishedScrolling();
        this.mDetector.setDetectableScrollConditions(0, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelAtomicComponentsController() {
        AnimatorPlaybackController animatorPlaybackController = this.mAtomicComponentsController;
        if (animatorPlaybackController != null) {
            animatorPlaybackController.getAnimationPlayer().cancel();
            this.mAtomicComponentsController = null;
        }
        this.mAtomicAnimAutoPlayInfo = null;
    }

    private static class AutoPlayAtomicAnimationInfo {
        public final long endTime;
        public final float toProgress;

        AutoPlayAtomicAnimationInfo(float toProgress, long duration) {
            this.toProgress = toProgress;
            this.endTime = duration + SystemClock.elapsedRealtime();
        }
    }
}
