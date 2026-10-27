package com.android.launcher3.allapps;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.MotionEvent;
import com.android.launcher3.AbstractFloatingView;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherState;
import com.android.launcher3.LauncherStateManager;
import com.android.launcher3.R;
import com.android.launcher3.Utilities;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.states.InternalStateHandler;

/* JADX INFO: loaded from: classes4.dex */
public class DiscoveryBounce extends AbstractFloatingView {
    public static final int BOUNCE_MAX_COUNT = 3;
    private static final long DELAY_MS = 450;
    public static final String HOME_BOUNCE_COUNT = "launcher.home_bounce_count";
    public static final String HOME_BOUNCE_SEEN = "launcher.apps_view_shown";
    public static final String SHELF_BOUNCE_COUNT = "launcher.shelf_bounce_count";
    public static final String SHELF_BOUNCE_SEEN = "launcher.shelf_bounce_seen";
    private final Animator mDiscoBounceAnimation;
    private final Launcher mLauncher;
    private final LauncherStateManager.StateListener mStateListener;

    @Override // com.android.launcher3.AbstractFloatingView
    protected boolean isOfType(int type) {
        return (type & 64) != 0;
    }

    @Override // com.android.launcher3.AbstractFloatingView
    public void logActionCommand(int command) {
    }

    public DiscoveryBounce(Launcher launcher, float delta) {
        super(launcher, null);
        LauncherStateManager.StateListener stateListener = new LauncherStateManager.StateListener() { // from class: com.android.launcher3.allapps.DiscoveryBounce.1
            @Override // com.android.launcher3.LauncherStateManager.StateListener
            public void onStateTransitionComplete(LauncherState finalState) {
            }

            @Override // com.android.launcher3.LauncherStateManager.StateListener
            public void onStateTransitionStart(LauncherState toState) {
                DiscoveryBounce.this.handleClose(false);
            }
        };
        this.mStateListener = stateListener;
        this.mLauncher = launcher;
        AllAppsTransitionController allAppsController = launcher.getAllAppsController();
        Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(launcher, R.animator.discovery_bounce);
        this.mDiscoBounceAnimation = animatorLoadAnimator;
        animatorLoadAnimator.setTarget(new VerticalProgressWrapper(allAppsController, delta));
        animatorLoadAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.allapps.DiscoveryBounce.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                DiscoveryBounce.this.handleClose(false);
            }
        });
        animatorLoadAnimator.addListener(allAppsController.getProgressAnimatorListener());
        launcher.getStateManager().addStateListener(stateListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mDiscoBounceAnimation.start();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mDiscoBounceAnimation.isRunning()) {
            this.mDiscoBounceAnimation.end();
        }
    }

    @Override // com.android.launcher3.AbstractFloatingView
    public boolean onBackPressed() {
        super.onBackPressed();
        return false;
    }

    @Override // com.android.launcher3.util.TouchController
    public boolean onControllerInterceptTouchEvent(MotionEvent ev) {
        handleClose(false);
        return false;
    }

    @Override // com.android.launcher3.AbstractFloatingView
    protected void handleClose(boolean animate) {
        if (this.mIsOpen) {
            this.mIsOpen = false;
            this.mLauncher.getDragLayer().removeView(this);
            this.mLauncher.getAllAppsController().setProgress(this.mLauncher.getStateManager().getState().getVerticalProgress(this.mLauncher));
            this.mLauncher.getStateManager().removeStateListener(this.mStateListener);
        }
    }

    private void show(int containerType) {
        this.mIsOpen = true;
        this.mLauncher.getDragLayer().addView(this);
        this.mLauncher.getUserEventDispatcher().logActionBounceTip(containerType);
    }

    public static void showForHomeIfNeeded(Launcher launcher) {
        showForHomeIfNeeded(launcher, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showForHomeIfNeeded(final Launcher launcher, boolean withDelay) {
        if (launcher.isInState(LauncherState.NORMAL)) {
            if ((!launcher.getSharedPrefs().getBoolean(HOME_BOUNCE_SEEN, false) || shouldShowForWorkProfile(launcher)) && AbstractFloatingView.getTopOpenView(launcher) == null && !UserManagerCompat.getInstance(launcher).isDemoUser() && !Utilities.IS_RUNNING_IN_TEST_HARNESS) {
                if (withDelay) {
                    new Handler().postDelayed(new Runnable() { // from class: com.android.launcher3.allapps.-$$Lambda$DiscoveryBounce$bCHUhoKUJRagTYcvEt7yTLtOYlg
                        @Override // java.lang.Runnable
                        public final void run() {
                            DiscoveryBounce.showForHomeIfNeeded(launcher, false);
                        }
                    }, DELAY_MS);
                } else {
                    incrementHomeBounceCount(launcher);
                    new DiscoveryBounce(launcher, 0.0f).show(2);
                }
            }
        }
    }

    public static void showForOverviewIfNeeded(Launcher launcher) {
        showForOverviewIfNeeded(launcher, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showForOverviewIfNeeded(final Launcher launcher, boolean withDelay) {
        if (!launcher.isInState(LauncherState.OVERVIEW) || !launcher.hasBeenResumed() || launcher.isForceInvisible() || launcher.getDeviceProfile().isVerticalBarLayout()) {
            return;
        }
        if ((launcher.getSharedPrefs().getBoolean(SHELF_BOUNCE_SEEN, false) && !shouldShowForWorkProfile(launcher)) || UserManagerCompat.getInstance(launcher).isDemoUser() || Utilities.IS_RUNNING_IN_TEST_HARNESS) {
            return;
        }
        if (withDelay) {
            new Handler().postDelayed(new Runnable() { // from class: com.android.launcher3.allapps.-$$Lambda$DiscoveryBounce$2Y8N9puEquh-L8lyOLgWu20X9xI
                @Override // java.lang.Runnable
                public final void run() {
                    DiscoveryBounce.showForOverviewIfNeeded(launcher, false);
                }
            }, DELAY_MS);
        } else {
            if (InternalStateHandler.hasPending() || AbstractFloatingView.getTopOpenView(launcher) != null) {
                return;
            }
            incrementShelfBounceCount(launcher);
            new DiscoveryBounce(launcher, 1.0f - LauncherState.OVERVIEW.getVerticalProgress(launcher)).show(7);
        }
    }

    public static class VerticalProgressWrapper {
        private final AllAppsTransitionController mController;
        private final float mDelta;

        private VerticalProgressWrapper(AllAppsTransitionController controller, float delta) {
            this.mController = controller;
            this.mDelta = delta;
        }

        public float getProgress() {
            return this.mController.getProgress() + this.mDelta;
        }

        public void setProgress(float progress) {
            this.mController.setProgress(progress - this.mDelta);
        }
    }

    private static boolean shouldShowForWorkProfile(Launcher launcher) {
        return !launcher.getSharedPrefs().getBoolean(PersonalWorkSlidingTabStrip.KEY_SHOWED_PEEK_WORK_TAB, false) && UserManagerCompat.getInstance(launcher).hasWorkProfile();
    }

    private static void incrementShelfBounceCount(Launcher launcher) {
        SharedPreferences sharedPrefs = launcher.getSharedPrefs();
        int i = sharedPrefs.getInt(SHELF_BOUNCE_COUNT, 0);
        if (i > 3) {
            return;
        }
        sharedPrefs.edit().putInt(SHELF_BOUNCE_COUNT, i + 1).apply();
    }

    private static void incrementHomeBounceCount(Launcher launcher) {
        SharedPreferences sharedPrefs = launcher.getSharedPrefs();
        int i = sharedPrefs.getInt(HOME_BOUNCE_COUNT, 0);
        if (i > 3) {
            return;
        }
        sharedPrefs.edit().putInt(HOME_BOUNCE_COUNT, i + 1).apply();
    }
}
