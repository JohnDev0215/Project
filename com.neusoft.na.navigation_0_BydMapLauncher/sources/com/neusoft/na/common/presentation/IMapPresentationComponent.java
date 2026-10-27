package com.neusoft.na.common.presentation;

import android.graphics.SurfaceTexture;

/* JADX INFO: loaded from: classes2.dex */
public interface IMapPresentationComponent {
    public static final String INTENT_ACTION_ACTIVATION_PRIVACY_POLICY_STATUS_CHANGED = "com.neusoft.na.navigation.activation_privacy_policy_status_changed";
    public static final int PRESENTATION_FULL_MODE = 4;
    public static final int PRESENTATION_SMALL_MODE = 3;

    void closePresentation();

    SurfaceTexture getSurfaceTexture();

    void hidePresentation();

    void init();

    void initSystemInterface();

    boolean isShowingPresentation();

    void onTestINSNavDisplayMode(int i);

    void onTestMapFocusChanged(int i);

    void setActivationPrivacyPolicyStatus(boolean z, boolean z2);

    void setDayNightModeChange(int i);

    void showPresentation();
}
