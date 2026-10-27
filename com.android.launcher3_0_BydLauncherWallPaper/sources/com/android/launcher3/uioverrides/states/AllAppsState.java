package com.android.launcher3.uioverrides.states;

import com.android.launcher3.AbstractFloatingView;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherState;
import com.android.launcher3.R;
import com.android.launcher3.allapps.DiscoveryBounce;
import com.android.launcher3.anim.Interpolators;

/* JADX INFO: loaded from: classes4.dex */
public class AllAppsState extends LauncherState {
    private static final LauncherState.PageAlphaProvider PAGE_ALPHA_PROVIDER = new LauncherState.PageAlphaProvider(Interpolators.DEACCEL_2) { // from class: com.android.launcher3.uioverrides.states.AllAppsState.1
        @Override // com.android.launcher3.LauncherState.PageAlphaProvider
        public float getPageAlpha(int pageIndex) {
            return 0.0f;
        }
    };
    private static final float PARALLAX_COEFFICIENT = 0.125f;
    private static final int STATE_FLAGS = 2;

    @Override // com.android.launcher3.LauncherState
    public float getVerticalProgress(Launcher launcher) {
        return 0.0f;
    }

    @Override // com.android.launcher3.LauncherState
    public int getVisibleElements(Launcher launcher) {
        return 20;
    }

    public AllAppsState(int id) {
        super(id, 4, 320, 2);
    }

    @Override // com.android.launcher3.LauncherState
    public void onStateEnabled(Launcher launcher) {
        if (!launcher.getSharedPrefs().getBoolean(DiscoveryBounce.HOME_BOUNCE_SEEN, false)) {
            launcher.getSharedPrefs().edit().putBoolean(DiscoveryBounce.HOME_BOUNCE_SEEN, true).apply();
        }
        AbstractFloatingView.closeAllOpenViews(launcher);
        dispatchWindowStateChanged(launcher);
    }

    @Override // com.android.launcher3.LauncherState
    public String getDescription(Launcher launcher) {
        return launcher.getString(R.string.all_apps_button_label);
    }

    @Override // com.android.launcher3.LauncherState
    public LauncherState.ScaleAndTranslation getWorkspaceScaleAndTranslation(Launcher launcher) {
        return new LauncherState.ScaleAndTranslation(1.0f, 0.0f, (-launcher.getAllAppsController().getShiftRange()) * PARALLAX_COEFFICIENT);
    }

    @Override // com.android.launcher3.LauncherState
    public LauncherState.PageAlphaProvider getWorkspacePageAlphaProvider(Launcher launcher) {
        return PAGE_ALPHA_PROVIDER;
    }
}
