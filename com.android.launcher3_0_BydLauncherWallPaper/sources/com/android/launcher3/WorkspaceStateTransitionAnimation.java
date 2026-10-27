package com.android.launcher3;

import android.animation.TimeInterpolator;
import android.view.View;
import android.view.animation.Interpolator;
import com.android.launcher3.LauncherState;
import com.android.launcher3.LauncherStateManager;
import com.android.launcher3.anim.AnimatorSetBuilder;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.anim.PropertySetter;
import com.android.launcher3.dragndrop.DragLayer;
import com.android.launcher3.graphics.WorkspaceAndHotseatScrim;

/* JADX INFO: loaded from: classes4.dex */
public class WorkspaceStateTransitionAnimation {
    private final Launcher mLauncher;
    private float mNewScale;
    private final Workspace mWorkspace;

    public WorkspaceStateTransitionAnimation(Launcher launcher, Workspace workspace) {
        this.mLauncher = launcher;
        this.mWorkspace = workspace;
    }

    public void setState(LauncherState toState) {
        setWorkspaceProperty(toState, PropertySetter.NO_ANIM_PROPERTY_SETTER, new AnimatorSetBuilder(), new LauncherStateManager.AnimationConfig());
    }

    public void setStateWithAnimation(LauncherState toState, AnimatorSetBuilder builder, LauncherStateManager.AnimationConfig config) {
        setWorkspaceProperty(toState, config.getPropertySetter(builder), builder, config);
    }

    public float getFinalScale() {
        return this.mNewScale;
    }

    private void setWorkspaceProperty(LauncherState state, PropertySetter propertySetter, AnimatorSetBuilder builder, LauncherStateManager.AnimationConfig config) {
        Interpolator interpolator;
        LauncherState.ScaleAndTranslation workspaceScaleAndTranslation = state.getWorkspaceScaleAndTranslation(this.mLauncher);
        LauncherState.ScaleAndTranslation hotseatScaleAndTranslation = state.getHotseatScaleAndTranslation(this.mLauncher);
        this.mNewScale = workspaceScaleAndTranslation.scale;
        LauncherState.PageAlphaProvider workspacePageAlphaProvider = state.getWorkspacePageAlphaProvider(this.mLauncher);
        int childCount = this.mWorkspace.getChildCount();
        for (int i = 0; i < childCount; i++) {
            applyChildState(state, (CellLayout) this.mWorkspace.getChildAt(i), i, workspacePageAlphaProvider, propertySetter, builder, config);
        }
        int visibleElements = state.getVisibleElements(this.mLauncher);
        TimeInterpolator interpolator2 = builder.getInterpolator(3, workspacePageAlphaProvider.interpolator);
        boolean zPlayAtomicOverviewScaleComponent = config.playAtomicOverviewScaleComponent();
        Hotseat hotseat = this.mWorkspace.getHotseat();
        if (zPlayAtomicOverviewScaleComponent) {
            Interpolator interpolator3 = builder.getInterpolator(1, Interpolators.ZOOM_OUT);
            propertySetter.setFloat(this.mWorkspace, LauncherAnimUtils.SCALE_PROPERTY, this.mNewScale, interpolator3);
            if (!hotseat.getRotationMode().isTransposed) {
                DragLayer dragLayer = this.mLauncher.getDragLayer();
                float[] fArr = {this.mWorkspace.getPivotX(), this.mWorkspace.getPivotY()};
                dragLayer.getDescendantCoordRelativeToSelf(this.mWorkspace, fArr);
                dragLayer.mapCoordInSelfToDescendant(hotseat, fArr);
                hotseat.setPivotX(fArr[0]);
                hotseat.setPivotY(fArr[1]);
            }
            propertySetter.setFloat(hotseat, LauncherAnimUtils.SCALE_PROPERTY, hotseatScaleAndTranslation.scale, builder.getInterpolator(4, interpolator3));
            float f = (visibleElements & 1) != 0 ? 1.0f : 0.0f;
            propertySetter.setViewAlpha(hotseat, f, interpolator2);
            propertySetter.setViewAlpha(this.mLauncher.getWorkspace().getPageIndicator(), f, interpolator2);
        }
        if (config.playNonAtomicComponent()) {
            if (!zPlayAtomicOverviewScaleComponent) {
                interpolator = Interpolators.LINEAR;
            } else {
                interpolator = builder.getInterpolator(2, Interpolators.ZOOM_OUT);
            }
            propertySetter.setFloat(this.mWorkspace, View.TRANSLATION_X, workspaceScaleAndTranslation.translationX, interpolator);
            propertySetter.setFloat(this.mWorkspace, View.TRANSLATION_Y, workspaceScaleAndTranslation.translationY, interpolator);
            TimeInterpolator interpolator4 = builder.getInterpolator(5, interpolator);
            propertySetter.setFloat(hotseat, View.TRANSLATION_Y, hotseatScaleAndTranslation.translationY, interpolator4);
            propertySetter.setFloat(this.mWorkspace.getPageIndicator(), View.TRANSLATION_Y, hotseatScaleAndTranslation.translationY, interpolator4);
            setScrim(propertySetter, state);
        }
    }

    public void setScrim(PropertySetter propertySetter, LauncherState state) {
        WorkspaceAndHotseatScrim scrim = this.mLauncher.getDragLayer().getScrim();
        propertySetter.setFloat(scrim, WorkspaceAndHotseatScrim.SCRIM_PROGRESS, state.getWorkspaceScrimAlpha(this.mLauncher), Interpolators.LINEAR);
        propertySetter.setFloat(scrim, WorkspaceAndHotseatScrim.SYSUI_PROGRESS, state.hasSysUiScrim ? 1.0f : 0.0f, Interpolators.LINEAR);
    }

    public void applyChildState(LauncherState state, CellLayout cl, int childIndex) {
        applyChildState(state, cl, childIndex, state.getWorkspacePageAlphaProvider(this.mLauncher), PropertySetter.NO_ANIM_PROPERTY_SETTER, new AnimatorSetBuilder(), new LauncherStateManager.AnimationConfig());
    }

    private void applyChildState(LauncherState state, CellLayout cl, int childIndex, LauncherState.PageAlphaProvider pageAlphaProvider, PropertySetter propertySetter, AnimatorSetBuilder builder, LauncherStateManager.AnimationConfig config) {
        float pageAlpha = pageAlphaProvider.getPageAlpha(childIndex);
        int iRound = Math.round((state.hasWorkspacePageBackground ? 255 : 0) * pageAlpha);
        if (config.playNonAtomicComponent()) {
            propertySetter.setInt(cl.getScrimBackground(), LauncherAnimUtils.DRAWABLE_ALPHA, iRound, Interpolators.ZOOM_OUT);
        }
        if (config.playAtomicOverviewScaleComponent()) {
            propertySetter.setFloat(cl.getShortcutsAndWidgets(), View.ALPHA, pageAlpha, builder.getInterpolator(3, pageAlphaProvider.interpolator));
        }
    }
}
