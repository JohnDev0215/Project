package com.android.launcher3.allapps;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.FloatProperty;
import android.util.Log;
import android.view.animation.Interpolator;
import com.android.launcher3.DeviceProfile;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherState;
import com.android.launcher3.LauncherStateManager;
import com.android.launcher3.R;
import com.android.launcher3.anim.AnimationSuccessListener;
import com.android.launcher3.anim.AnimatorSetBuilder;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.anim.PropertySetter;
import com.android.launcher3.anim.SpringObjectAnimator;
import com.android.launcher3.testing.TestProtocol;
import com.android.launcher3.util.Themes;
import com.android.launcher3.views.ScrimView;

/* JADX INFO: loaded from: classes4.dex */
public class AllAppsTransitionController implements LauncherStateManager.StateHandler, DeviceProfile.OnDeviceProfileChangeListener {
    public static final FloatProperty<AllAppsTransitionController> ALL_APPS_PROGRESS = new FloatProperty<AllAppsTransitionController>("allAppsProgress") { // from class: com.android.launcher3.allapps.AllAppsTransitionController.1
        @Override // android.util.Property
        public Float get(AllAppsTransitionController controller) {
            return Float.valueOf(controller.mProgress);
        }

        @Override // android.util.FloatProperty
        public void setValue(AllAppsTransitionController controller, float progress) {
            controller.setProgress(progress);
        }
    };
    private static final int APPS_VIEW_ALPHA_CHANNEL_INDEX = 0;
    private static final float SPRING_DAMPING_RATIO = 0.9f;
    private static final float SPRING_STIFFNESS = 600.0f;
    private AllAppsContainerView mAppsView;
    private final boolean mIsDarkTheme;
    private boolean mIsVerticalLayout;
    private final Launcher mLauncher;
    private ScrimView mScrimView;
    private float mShiftRange;
    private float mScrollRangeDelta = 0.0f;
    private float mProgress = 1.0f;

    @Override // com.android.launcher3.LauncherStateManager.StateHandler
    public void setState(LauncherState state) {
    }

    public AllAppsTransitionController(Launcher l) {
        this.mLauncher = l;
        this.mShiftRange = l.getDeviceProfile().heightPx;
        this.mIsDarkTheme = Themes.getAttrBoolean(l, R.attr.isMainColorDark);
        this.mIsVerticalLayout = l.getDeviceProfile().isVerticalBarLayout();
        l.addOnDeviceProfileChangeListener(this);
    }

    public float getShiftRange() {
        return this.mShiftRange;
    }

    @Override // com.android.launcher3.DeviceProfile.OnDeviceProfileChangeListener
    public void onDeviceProfileChanged(DeviceProfile dp) {
        this.mIsVerticalLayout = dp.isVerticalBarLayout();
        setScrollRangeDelta(this.mScrollRangeDelta);
        if (this.mIsVerticalLayout) {
            this.mAppsView.getAlphaProperty(0).setValue(1.0f);
            this.mLauncher.getHotseat().setTranslationY(0.0f);
            this.mLauncher.getWorkspace().getPageIndicator().setTranslationY(0.0f);
        }
    }

    public void setProgress(float progress) {
        this.mProgress = progress;
        this.mScrimView.setProgress(progress);
        float f = this.mShiftRange * progress;
        this.mAppsView.setTranslationY(f);
        if (f - ((float) this.mScrimView.getDragHandleSize()) <= ((float) (this.mLauncher.getDeviceProfile().getInsets().top / 2))) {
            this.mLauncher.getSystemUiController().updateUiState(1, !this.mIsDarkTheme);
        } else {
            this.mLauncher.getSystemUiController().updateUiState(1, 0);
        }
        if ((LauncherState.OVERVIEW.getVisibleElements(this.mLauncher) & 1) != 0) {
            float verticalProgress = LauncherState.OVERVIEW.getVerticalProgress(this.mLauncher);
            if (progress >= verticalProgress || this.mLauncher.isInState(LauncherState.BACKGROUND_APP)) {
                this.mLauncher.getHotseat().setTranslationY((progress - verticalProgress) * this.mShiftRange);
            }
        }
    }

    public float getProgress() {
        return this.mProgress;
    }

    @Override // com.android.launcher3.LauncherStateManager.StateHandler
    public void setStateWithAnimation(LauncherState toState, AnimatorSetBuilder builder, LauncherStateManager.AnimationConfig config) {
        if (TestProtocol.sDebugTracing) {
            Log.d(TestProtocol.NO_ALLAPPS_EVENT_TAG, "setStateWithAnimation " + toState.getClass().getSimpleName());
        }
    }

    public Animator createSpringAnimation(float... progressValues) {
        return new SpringObjectAnimator(this, ALL_APPS_PROGRESS, 1.0f / this.mShiftRange, SPRING_DAMPING_RATIO, SPRING_STIFFNESS, progressValues);
    }

    private void setAlphas(LauncherState toState, LauncherStateManager.AnimationConfig config, AnimatorSetBuilder builder) {
        setAlphas(toState.getVisibleElements(this.mLauncher), config, builder);
    }

    public void setAlphas(int visibleElements, LauncherStateManager.AnimationConfig config, AnimatorSetBuilder builder) {
        PropertySetter propertySetter;
        if (config == null) {
            propertySetter = PropertySetter.NO_ANIM_PROPERTY_SETTER;
        } else {
            propertySetter = config.getPropertySetter(builder);
        }
        boolean z = (visibleElements & 8) != 0;
        boolean z2 = (visibleElements & 16) != 0;
        Interpolator interpolator = builder.getInterpolator(10, Interpolators.LINEAR);
        propertySetter.setViewAlpha(this.mAppsView.getContentView(), z2 ? 1.0f : 0.0f, interpolator);
        propertySetter.setViewAlpha(this.mAppsView.getScrollBar(), z2 ? 1.0f : 0.0f, interpolator);
        this.mAppsView.getFloatingHeaderView().setContentVisibility(z, z2, propertySetter, interpolator);
        this.mAppsView.getSearchUiManager().setContentVisibility(visibleElements, propertySetter, interpolator);
        propertySetter.setInt(this.mScrimView, ScrimView.DRAG_HANDLE_ALPHA, (visibleElements & 32) != 0 ? 255 : 0, interpolator);
    }

    public AnimatorListenerAdapter getProgressAnimatorListener() {
        return new AnimationSuccessListener() { // from class: com.android.launcher3.allapps.AllAppsTransitionController.2
            @Override // com.android.launcher3.anim.AnimationSuccessListener
            public void onAnimationSuccess(Animator animator) {
                AllAppsTransitionController.this.onProgressAnimationEnd();
            }
        };
    }

    public void setupViews(AllAppsContainerView appsView) {
        this.mAppsView = appsView;
        this.mScrimView = (ScrimView) this.mLauncher.findViewById(R.id.scrim_view);
    }

    void setScrollRangeDelta(float delta) {
        this.mScrollRangeDelta = delta;
        this.mShiftRange = this.mLauncher.getDeviceProfile().heightPx - this.mScrollRangeDelta;
        ScrimView scrimView = this.mScrimView;
        if (scrimView != null) {
            scrimView.reInitUi();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProgressAnimationEnd() {
        if (Float.compare(this.mProgress, 1.0f) == 0) {
            this.mAppsView.reset(false);
        } else if (isAllAppsExpanded()) {
            this.mAppsView.onScrollUpEnd();
        }
    }

    private boolean isAllAppsExpanded() {
        return Float.compare(this.mProgress, 0.0f) == 0;
    }

    public void highlightWorkTabIfNecessary() {
        if (isAllAppsExpanded()) {
            this.mAppsView.highlightWorkTabIfNecessary();
        }
    }
}
