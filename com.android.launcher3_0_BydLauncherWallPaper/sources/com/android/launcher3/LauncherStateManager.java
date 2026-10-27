package com.android.launcher3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.android.launcher3.anim.AnimationSuccessListener;
import com.android.launcher3.anim.AnimatorPlaybackController;
import com.android.launcher3.anim.AnimatorSetBuilder;
import com.android.launcher3.anim.PropertySetter;
import com.android.launcher3.compat.AccessibilityManagerCompat;
import com.android.launcher3.testing.TestProtocol;
import com.android.launcher3.uioverrides.UiFactory;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherStateManager {
    public static final int ANIM_ALL = 7;
    public static final int ATOMIC_OVERVIEW_PEEK_COMPONENT = 4;
    public static final int ATOMIC_OVERVIEW_SCALE_COMPONENT = 2;
    public static final int NON_ATOMIC_COMPONENT = 1;
    public static final String TAG = "StateManager";
    private final Launcher mLauncher;
    private LauncherState mRestState;
    private Animator[] mStateElementAnimators;
    private StateHandler[] mStateHandlers;
    private final AnimationConfig mConfig = new AnimationConfig();
    private final ArrayList<StateListener> mListeners = new ArrayList<>();
    private LauncherState mState = LauncherState.NORMAL;
    private LauncherState mLastStableState = LauncherState.NORMAL;
    private LauncherState mCurrentStableState = LauncherState.NORMAL;
    private final Handler mUiHandler = new Handler(Looper.getMainLooper());

    @Retention(RetentionPolicy.SOURCE)
    public @interface AnimationComponents {
    }

    public interface StateHandler {
        void setState(LauncherState state);

        void setStateWithAnimation(LauncherState toState, AnimatorSetBuilder builder, AnimationConfig config);
    }

    public interface StateListener {
        void onStateTransitionComplete(LauncherState finalState);

        void onStateTransitionStart(LauncherState toState);
    }

    public LauncherStateManager(Launcher l) {
        this.mLauncher = l;
    }

    public LauncherState getState() {
        return this.mState;
    }

    public LauncherState getCurrentStableState() {
        return this.mCurrentStableState;
    }

    public void dump(String prefix, PrintWriter writer) {
        writer.println(prefix + "LauncherState");
        writer.println(prefix + "\tmLastStableState:" + this.mLastStableState);
        writer.println(prefix + "\tmCurrentStableState:" + this.mCurrentStableState);
        writer.println(prefix + "\tmState:" + this.mState);
        writer.println(prefix + "\tmRestState:" + this.mRestState);
        writer.println(prefix + "\tisInTransition:" + (this.mConfig.mCurrentAnimation != null));
    }

    public StateHandler[] getStateHandlers() {
        if (this.mStateHandlers == null) {
            this.mStateHandlers = UiFactory.getStateHandler(this.mLauncher);
        }
        return this.mStateHandlers;
    }

    public void addStateListener(StateListener listener) {
        this.mListeners.add(listener);
    }

    public void removeStateListener(StateListener listener) {
        this.mListeners.remove(listener);
    }

    public boolean shouldAnimateStateChange() {
        return !this.mLauncher.isForceInvisible() && this.mLauncher.isStarted();
    }

    public void goToState(LauncherState state) {
        goToState(state, shouldAnimateStateChange());
    }

    public void goToState(LauncherState state, boolean animated) {
        goToState(state, animated, 0L, null);
    }

    public void goToState(LauncherState state, boolean animated, Runnable onCompleteRunnable) {
        goToState(state, animated, 0L, onCompleteRunnable);
    }

    public void goToState(LauncherState state, long delay, Runnable onCompleteRunnable) {
        goToState(state, true, delay, onCompleteRunnable);
    }

    public void goToState(LauncherState state, long delay) {
        goToState(state, true, delay, null);
    }

    public void reapplyState() {
        reapplyState(false);
    }

    public void reapplyState(boolean cancelCurrentAnimation) {
        boolean z = this.mConfig.mCurrentAnimation != null;
        if (cancelCurrentAnimation) {
            cancelAllStateElementAnimation();
            cancelAnimation();
        }
        if (this.mConfig.mCurrentAnimation == null) {
            for (StateHandler stateHandler : getStateHandlers()) {
                stateHandler.setState(this.mState);
            }
            if (z) {
                onStateTransitionEnd(this.mState);
            }
        }
    }

    private void goToState(final LauncherState state, boolean animated, long delay, final Runnable onCompleteRunnable) {
        boolean zAreAnimationsEnabled = animated & Utilities.areAnimationsEnabled(this.mLauncher);
        if (this.mLauncher.isInState(state)) {
            if (this.mConfig.mCurrentAnimation == null) {
                if (onCompleteRunnable != null) {
                    onCompleteRunnable.run();
                    return;
                }
                return;
            } else if (!this.mConfig.userControlled && zAreAnimationsEnabled && this.mConfig.mTargetState == state) {
                if (onCompleteRunnable != null) {
                    this.mConfig.mCurrentAnimation.addListener(new AnimationSuccessListener() { // from class: com.android.launcher3.LauncherStateManager.1
                        @Override // com.android.launcher3.anim.AnimationSuccessListener
                        public void onAnimationSuccess(Animator animator) {
                            onCompleteRunnable.run();
                        }
                    });
                    return;
                }
                return;
            }
        }
        final LauncherState launcherState = this.mState;
        this.mConfig.reset();
        if (zAreAnimationsEnabled) {
            if (delay <= 0) {
                goToStateAnimated(state, launcherState, onCompleteRunnable);
                return;
            } else {
                final int i = this.mConfig.mChangeId;
                this.mUiHandler.postDelayed(new Runnable() { // from class: com.android.launcher3.-$$Lambda$LauncherStateManager$8GLIuGHYHZjNw6jLC0a7dXq_hNc
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$goToState$0$LauncherStateManager(i, state, launcherState, onCompleteRunnable);
                    }
                }, delay);
                return;
            }
        }
        cancelAllStateElementAnimation();
        onStateTransitionStart(state);
        for (StateHandler stateHandler : getStateHandlers()) {
            stateHandler.setState(state);
        }
        onStateTransitionEnd(state);
        if (onCompleteRunnable != null) {
            onCompleteRunnable.run();
        }
    }

    public /* synthetic */ void lambda$goToState$0$LauncherStateManager(int i, LauncherState launcherState, LauncherState launcherState2, Runnable runnable) {
        if (this.mConfig.mChangeId == i) {
            goToStateAnimated(launcherState, launcherState2, runnable);
        }
    }

    private void goToStateAnimated(LauncherState state, LauncherState fromState, Runnable onCompleteRunnable) {
        this.mConfig.duration = state == LauncherState.NORMAL ? fromState.transitionDuration : state.transitionDuration;
        AnimatorSetBuilder animatorSetBuilder = new AnimatorSetBuilder();
        prepareForAtomicAnimation(fromState, state, animatorSetBuilder);
        this.mUiHandler.post(new StartAnimRunnable(createAnimationToNewWorkspaceInternal(state, animatorSetBuilder, onCompleteRunnable)));
    }

    public void prepareForAtomicAnimation(LauncherState fromState, LauncherState toState, AnimatorSetBuilder builder) {
        toState.prepareForAtomicAnimation(this.mLauncher, fromState, builder);
    }

    public AnimatorSet createAtomicAnimation(LauncherState fromState, LauncherState toState, AnimatorSetBuilder builder, int atomicComponent, long duration) {
        prepareForAtomicAnimation(fromState, toState, builder);
        AnimationConfig animationConfig = new AnimationConfig();
        animationConfig.animComponents = atomicComponent;
        animationConfig.duration = duration;
        for (StateHandler stateHandler : this.mLauncher.getStateManager().getStateHandlers()) {
            stateHandler.setStateWithAnimation(toState, builder, animationConfig);
        }
        return builder.build();
    }

    public AnimatorPlaybackController createAnimationToNewWorkspace(LauncherState fromState, LauncherState state, long duration) {
        LauncherState launcherState = this.mRestState;
        this.mRestState = state;
        this.mConfig.reset();
        this.mRestState = launcherState;
        for (StateHandler stateHandler : getStateHandlers()) {
            stateHandler.setState(fromState);
        }
        return createAnimationToNewWorkspace(state, duration);
    }

    public AnimatorPlaybackController createAnimationToNewWorkspace(LauncherState state, long duration) {
        return createAnimationToNewWorkspace(state, duration, 7);
    }

    public AnimatorPlaybackController createAnimationToNewWorkspace(LauncherState state, long duration, int animComponents) {
        return createAnimationToNewWorkspace(state, new AnimatorSetBuilder(), duration, null, animComponents);
    }

    public AnimatorPlaybackController createAnimationToNewWorkspace(LauncherState state, AnimatorSetBuilder builder, long duration, Runnable onCancelRunnable, int animComponents) {
        this.mConfig.reset();
        this.mConfig.userControlled = true;
        this.mConfig.animComponents = animComponents;
        this.mConfig.duration = duration;
        this.mConfig.playbackController = AnimatorPlaybackController.wrap(createAnimationToNewWorkspaceInternal(state, builder, null), duration, onCancelRunnable);
        return this.mConfig.playbackController;
    }

    protected AnimatorSet createAnimationToNewWorkspaceInternal(final LauncherState state, AnimatorSetBuilder builder, final Runnable onCompleteRunnable) {
        for (StateHandler stateHandler : getStateHandlers()) {
            stateHandler.setStateWithAnimation(state, builder, this.mConfig);
        }
        AnimatorSet animatorSetBuild = builder.build();
        animatorSetBuild.addListener(new AnimationSuccessListener() { // from class: com.android.launcher3.LauncherStateManager.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                LauncherStateManager.this.onStateTransitionStart(state);
            }

            @Override // com.android.launcher3.anim.AnimationSuccessListener
            public void onAnimationSuccess(Animator animator) {
                Runnable runnable = onCompleteRunnable;
                if (runnable != null) {
                    runnable.run();
                }
                LauncherStateManager.this.onStateTransitionEnd(state);
            }
        });
        this.mConfig.setAnimation(animatorSetBuild, state);
        return this.mConfig.mCurrentAnimation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStateTransitionStart(LauncherState state) {
        if (TestProtocol.sDebugTracing) {
            Log.d(TestProtocol.NO_DRAG_TAG, "onStateTransitionStart");
        }
        LauncherState launcherState = this.mState;
        if (launcherState != state) {
            launcherState.onStateDisabled(this.mLauncher);
        }
        this.mState = state;
        state.onStateEnabled(this.mLauncher);
        this.mLauncher.onStateSet(this.mState);
        if (state.disablePageClipping) {
            this.mLauncher.getWorkspace().setClipChildren(false);
        }
        UiFactory.onLauncherStateOrResumeChanged(this.mLauncher);
        for (int size = this.mListeners.size() - 1; size >= 0; size--) {
            this.mListeners.get(size).onStateTransitionStart(state);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStateTransitionEnd(LauncherState state) {
        LauncherState launcherState = this.mCurrentStableState;
        if (state != launcherState) {
            this.mLastStableState = state.getHistoryForState(launcherState);
            if (TestProtocol.sDebugTracing) {
                Log.d(TestProtocol.NO_ALLAPPS_EVENT_TAG, "mCurrentStableState = " + state.getClass().getSimpleName() + " @ " + Log.getStackTraceString(new Throwable()));
            }
            this.mCurrentStableState = state;
        }
        state.onStateTransitionEnd(this.mLauncher);
        this.mLauncher.getWorkspace().setClipChildren(!state.disablePageClipping);
        this.mLauncher.finishAutoCancelActionMode();
        if (state == LauncherState.NORMAL) {
            setRestState(null);
        }
        UiFactory.onLauncherStateOrResumeChanged(this.mLauncher);
        for (int size = this.mListeners.size() - 1; size >= 0; size--) {
            this.mListeners.get(size).onStateTransitionComplete(state);
        }
        AccessibilityManagerCompat.sendStateEventToTest(this.mLauncher, state.ordinal);
    }

    public void onWindowFocusChanged() {
        UiFactory.onLauncherStateOrFocusChanged(this.mLauncher);
    }

    public LauncherState getLastState() {
        return this.mLastStableState;
    }

    public void moveToRestState() {
        if ((this.mConfig.mCurrentAnimation == null || !this.mConfig.userControlled) && this.mState.disableRestore) {
            goToState(getRestState());
            this.mLastStableState = LauncherState.NORMAL;
        }
    }

    public LauncherState getRestState() {
        LauncherState launcherState = this.mRestState;
        return launcherState == null ? LauncherState.NORMAL : launcherState;
    }

    public void setRestState(LauncherState restState) {
        this.mRestState = restState;
    }

    public void cancelAnimation() {
        this.mConfig.reset();
    }

    public void setCurrentUserControlledAnimation(AnimatorPlaybackController controller) {
        clearCurrentAnimation();
        setCurrentAnimation(controller.getTarget(), new Animator[0]);
        this.mConfig.userControlled = true;
        this.mConfig.playbackController = controller;
    }

    public void setCurrentAnimation(AnimatorSet anim, Animator... childAnimations) {
        int length = childAnimations.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Animator animator = childAnimations[i];
            if (animator != null) {
                if (this.mConfig.playbackController != null && this.mConfig.playbackController.getTarget() == animator) {
                    clearCurrentAnimation();
                    break;
                } else if (this.mConfig.mCurrentAnimation == animator) {
                    clearCurrentAnimation();
                    break;
                }
            }
            i++;
        }
        boolean z = this.mConfig.mCurrentAnimation != null;
        cancelAnimation();
        if (z) {
            reapplyState();
            onStateTransitionEnd(this.mState);
        }
        this.mConfig.setAnimation(anim, null);
    }

    private void cancelAllStateElementAnimation() {
        Animator[] animatorArr = this.mStateElementAnimators;
        if (animatorArr == null) {
            return;
        }
        for (Animator animator : animatorArr) {
            if (animator != null) {
                animator.cancel();
            }
        }
    }

    public void cancelStateElementAnimation(int index) {
        Animator[] animatorArr = this.mStateElementAnimators;
        if (animatorArr == null || animatorArr[index] == null) {
            return;
        }
        animatorArr[index].cancel();
    }

    public Animator createStateElementAnimation(final int index, float... values) {
        cancelStateElementAnimation(index);
        LauncherAppTransitionManager appTransitionManager = this.mLauncher.getAppTransitionManager();
        if (this.mStateElementAnimators == null) {
            this.mStateElementAnimators = new Animator[appTransitionManager.getStateElementAnimationsCount()];
        }
        Animator animatorCreateStateElementAnimation = appTransitionManager.createStateElementAnimation(index, values);
        this.mStateElementAnimators[index] = animatorCreateStateElementAnimation;
        animatorCreateStateElementAnimation.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.LauncherStateManager.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                LauncherStateManager.this.mStateElementAnimators[index] = null;
            }
        });
        return animatorCreateStateElementAnimation;
    }

    private void clearCurrentAnimation() {
        if (this.mConfig.mCurrentAnimation != null) {
            this.mConfig.mCurrentAnimation.removeListener(this.mConfig);
            this.mConfig.mCurrentAnimation = null;
        }
        this.mConfig.playbackController = null;
    }

    private class StartAnimRunnable implements Runnable {
        private final AnimatorSet mAnim;

        public StartAnimRunnable(AnimatorSet anim) {
            if (TestProtocol.sDebugTracing) {
                Log.d(TestProtocol.NO_DRAG_TAG, "StartAnimRunnable");
            }
            this.mAnim = anim;
        }

        @Override // java.lang.Runnable
        public void run() {
            AnimatorSet animatorSet = LauncherStateManager.this.mConfig.mCurrentAnimation;
            AnimatorSet animatorSet2 = this.mAnim;
            if (animatorSet != animatorSet2) {
                return;
            }
            animatorSet2.start();
        }
    }

    public static class AnimationConfig extends AnimatorListenerAdapter {
        public long duration;
        private AnimatorSet mCurrentAnimation;
        private PropertySetter mPropertySetter;
        private LauncherState mTargetState;
        public AnimatorPlaybackController playbackController;
        public boolean userControlled;
        public int animComponents = 7;
        private int mChangeId = 0;

        public void reset() {
            this.duration = 0L;
            this.userControlled = false;
            this.animComponents = 7;
            this.mPropertySetter = null;
            this.mTargetState = null;
            AnimatorPlaybackController animatorPlaybackController = this.playbackController;
            if (animatorPlaybackController != null) {
                animatorPlaybackController.getAnimationPlayer().cancel();
                this.playbackController.dispatchOnCancel();
            } else {
                AnimatorSet animatorSet = this.mCurrentAnimation;
                if (animatorSet != null) {
                    animatorSet.setDuration(0L);
                    this.mCurrentAnimation.cancel();
                }
            }
            this.mCurrentAnimation = null;
            this.playbackController = null;
            this.mChangeId++;
        }

        public PropertySetter getPropertySetter(AnimatorSetBuilder builder) {
            if (this.mPropertySetter == null) {
                this.mPropertySetter = this.duration == 0 ? PropertySetter.NO_ANIM_PROPERTY_SETTER : new PropertySetter.AnimatedPropertySetter(this.duration, builder);
            }
            return this.mPropertySetter;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation) {
            AnimatorPlaybackController animatorPlaybackController = this.playbackController;
            if (animatorPlaybackController != null && animatorPlaybackController.getTarget() == animation) {
                this.playbackController = null;
            }
            if (this.mCurrentAnimation == animation) {
                this.mCurrentAnimation = null;
            }
        }

        public void setAnimation(AnimatorSet animation, LauncherState targetState) {
            this.mCurrentAnimation = animation;
            this.mTargetState = targetState;
            animation.addListener(this);
        }

        public boolean playAtomicOverviewScaleComponent() {
            return (this.animComponents & 2) != 0;
        }

        public boolean playAtomicOverviewPeekComponent() {
            return (this.animComponents & 4) != 0;
        }

        public boolean playNonAtomicComponent() {
            return (this.animComponents & 1) != 0;
        }
    }
}
