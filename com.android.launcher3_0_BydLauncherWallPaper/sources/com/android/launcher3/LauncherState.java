package com.android.launcher3;

import android.view.animation.Interpolator;
import com.android.launcher3.anim.AnimatorSetBuilder;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.states.SpringLoadedState;
import com.android.launcher3.uioverrides.UiFactory;
import com.android.launcher3.uioverrides.states.AllAppsState;
import com.android.launcher3.uioverrides.states.OverviewState;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class LauncherState {
    public static final int ALL_APPS_CONTENT = 16;
    public static final int ALL_APPS_HEADER = 4;
    public static final int ALL_APPS_HEADER_EXTRA = 8;
    protected static final int FLAG_DISABLE_ACCESSIBILITY = 2;
    protected static final int FLAG_DISABLE_INTERACTION = 64;
    protected static final int FLAG_DISABLE_PAGE_CLIPPING = 16;
    protected static final int FLAG_DISABLE_RESTORE = 4;
    protected static final int FLAG_HAS_SYS_UI_SCRIM = 512;
    protected static final int FLAG_HIDE_BACK_BUTTON = 256;
    protected static final int FLAG_MULTI_PAGE = 1;
    protected static final int FLAG_OVERVIEW_UI = 128;
    protected static final int FLAG_PAGE_BACKGROUNDS = 32;
    protected static final int FLAG_WORKSPACE_ICONS_CAN_BE_DRAGGED = 8;
    public static final int HOTSEAT_ICONS = 1;
    public static final int HOTSEAT_SEARCH_BOX = 2;
    public static final int NONE = 0;
    public static final int RECENTS_CLEAR_ALL_BUTTON = 64;
    public static final int VERTICAL_SWIPE_INDICATOR = 32;
    public final int containerType;
    public final boolean disableInteraction;
    public final boolean disablePageClipping;
    public final boolean disableRestore;
    public final boolean hasMultipleVisiblePages;
    public final boolean hasSysUiScrim;
    public final boolean hasWorkspacePageBackground;
    public final boolean hideBackButton;
    public final int ordinal;
    public final boolean overviewUi;
    public final int transitionDuration;
    public final int workspaceAccessibilityFlag;
    public final boolean workspaceIconsCanBeDragged;
    protected static final PageAlphaProvider DEFAULT_ALPHA_PROVIDER = new PageAlphaProvider(Interpolators.ACCEL_2) { // from class: com.android.launcher3.LauncherState.1
        @Override // com.android.launcher3.LauncherState.PageAlphaProvider
        public float getPageAlpha(int pageIndex) {
            return 1.0f;
        }
    };
    private static final LauncherState[] sAllStates = new LauncherState[7];
    public static final LauncherState NORMAL = new LauncherState(0, 1, 0, 780);
    public static final LauncherState SPRING_LOADED = new SpringLoadedState(1);
    public static final LauncherState ALL_APPS = new AllAppsState(5);
    public static final LauncherState OVERVIEW = new OverviewState(2);
    public static final LauncherState OVERVIEW_PEEK = OverviewState.newPeekState(3);
    public static final LauncherState QUICK_SWITCH = OverviewState.newSwitchState(4);
    public static final LauncherState BACKGROUND_APP = OverviewState.newBackgroundState(6);

    static /* synthetic */ float lambda$prepareForAtomicAnimation$0(float f) {
        return f < 1.0f ? 0.0f : 1.0f;
    }

    public float getOverviewFullscreenProgress() {
        return 0.0f;
    }

    public float getVerticalProgress(Launcher launcher) {
        return 1.0f;
    }

    public float getWorkspaceScrimAlpha(Launcher launcher) {
        return 0.0f;
    }

    public void onStateDisabled(Launcher launcher) {
    }

    public LauncherState(int id, int containerType, int transitionDuration, int flags) {
        this.containerType = containerType;
        this.transitionDuration = transitionDuration;
        this.hasWorkspacePageBackground = (flags & 32) != 0;
        this.hasMultipleVisiblePages = (flags & 1) != 0;
        this.workspaceAccessibilityFlag = (flags & 2) != 0 ? 4 : 0;
        this.disableRestore = (flags & 4) != 0;
        this.workspaceIconsCanBeDragged = (flags & 8) != 0;
        this.disablePageClipping = (flags & 16) != 0;
        this.disableInteraction = (flags & 64) != 0;
        this.overviewUi = (flags & 128) != 0;
        this.hideBackButton = (flags & 256) != 0;
        this.hasSysUiScrim = (flags & 512) != 0;
        this.ordinal = id;
        sAllStates[id] = this;
    }

    public static LauncherState[] values() {
        LauncherState[] launcherStateArr = sAllStates;
        return (LauncherState[]) Arrays.copyOf(launcherStateArr, launcherStateArr.length);
    }

    public ScaleAndTranslation getWorkspaceScaleAndTranslation(Launcher launcher) {
        return new ScaleAndTranslation(1.0f, 0.0f, 0.0f);
    }

    public ScaleAndTranslation getHotseatScaleAndTranslation(Launcher launcher) {
        return getWorkspaceScaleAndTranslation(launcher);
    }

    public ScaleAndTranslation getOverviewScaleAndTranslation(Launcher launcher) {
        return UiFactory.getOverviewScaleAndTranslationForNormalState(launcher);
    }

    public void onStateEnabled(Launcher launcher) {
        dispatchWindowStateChanged(launcher);
    }

    public int getVisibleElements(Launcher launcher) {
        return launcher.getDeviceProfile().isVerticalBarLayout() ? 33 : 35;
    }

    public String getDescription(Launcher launcher) {
        return launcher.getWorkspace().getCurrentPageDescription();
    }

    public PageAlphaProvider getWorkspacePageAlphaProvider(Launcher launcher) {
        if (this != NORMAL || !launcher.getDeviceProfile().shouldFadeAdjacentWorkspaceScreens()) {
            return DEFAULT_ALPHA_PROVIDER;
        }
        final int nextPage = launcher.getWorkspace().getNextPage();
        return new PageAlphaProvider(Interpolators.ACCEL_2) { // from class: com.android.launcher3.LauncherState.2
            @Override // com.android.launcher3.LauncherState.PageAlphaProvider
            public float getPageAlpha(int pageIndex) {
                return pageIndex != nextPage ? 0.0f : 1.0f;
            }
        };
    }

    public LauncherState getHistoryForState(LauncherState previousState) {
        return NORMAL;
    }

    public void onStateTransitionEnd(Launcher launcher) {
        if (this == NORMAL) {
            launcher.getRotationHelper().setCurrentStateRequest(0);
        }
    }

    public void onBackPressed(Launcher launcher) {
        if (this != NORMAL) {
            LauncherStateManager stateManager = launcher.getStateManager();
            stateManager.goToState(stateManager.getLastState());
        }
    }

    public void prepareForAtomicAnimation(Launcher launcher, LauncherState fromState, AnimatorSetBuilder builder) {
        LauncherState launcherState = NORMAL;
        if (this == launcherState && fromState == OVERVIEW) {
            builder.setInterpolator(1, Interpolators.DEACCEL);
            builder.setInterpolator(3, Interpolators.ACCEL);
            builder.setInterpolator(6, Interpolators.clampToProgress(Interpolators.ACCEL, 0.0f, 0.9f));
            builder.setInterpolator(7, Interpolators.ACCEL);
            builder.setInterpolator(9, Interpolators.DEACCEL_1_7);
            Workspace workspace = launcher.getWorkspace();
            boolean z = workspace.getVisibility() == 0;
            if (z) {
                CellLayout cellLayout = (CellLayout) workspace.getChildAt(workspace.getCurrentPage());
                z = cellLayout.getVisibility() == 0 && cellLayout.getShortcutsAndWidgets().getAlpha() > 0.0f;
            }
            if (!z) {
                workspace.setScaleX(0.92f);
                workspace.setScaleY(0.92f);
            }
            Hotseat hotseat = launcher.getHotseat();
            if (hotseat.getVisibility() == 0 && hotseat.getAlpha() > 0.0f) {
                return;
            }
            hotseat.setScaleX(0.92f);
            hotseat.setScaleY(0.92f);
            return;
        }
        if (this == launcherState && fromState == OVERVIEW_PEEK) {
            builder.setInterpolator(9, new Interpolator() { // from class: com.android.launcher3.-$$Lambda$LauncherState$tXP9KctGQVmal3ETQMOt7VbEnpA
                @Override // android.animation.TimeInterpolator
                public final float getInterpolation(float f) {
                    return LauncherState.lambda$prepareForAtomicAnimation$0(f);
                }
            });
        }
    }

    protected static void dispatchWindowStateChanged(Launcher launcher) {
        launcher.getWindow().getDecorView().sendAccessibilityEvent(32);
    }

    public static abstract class PageAlphaProvider {
        public final Interpolator interpolator;

        public abstract float getPageAlpha(int pageIndex);

        public PageAlphaProvider(Interpolator interpolator) {
            this.interpolator = interpolator;
        }
    }

    public static class ScaleAndTranslation {
        public float scale;
        public float translationX;
        public float translationY;

        public ScaleAndTranslation(float scale, float translationX, float translationY) {
            this.scale = scale;
            this.translationX = translationX;
            this.translationY = translationY;
        }
    }
}
