package com.byd.dilink.statusbar;

import android.content.Intent;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkStatusBarManager {
    boolean closeAppWhenMultiWindow(@NonNull String str, int i);

    void collapsePanels();

    void enterSplitScreenModeByLauncher(String str, Intent intent);

    void enterSplitScreenModeByLauncher(String str, String str2);

    String getAppPkgByDirection(boolean z);

    int getSplitScreenStatus();

    void handleHomeButton();

    int isAppSupportSplit(@NonNull String str);

    boolean isDockState();

    boolean isFocusInPrimary();

    boolean isRunningTaskSupportsMultiWindow();

    boolean isSwapSplitScreenUsable();

    boolean moveAppToFullScreenByPkg(@NonNull String str);

    void notifyDockedStackExistsChanged(boolean z);

    void onSteeringWheelRotationClick();

    void registerCardListener(IDiLinkCardListener iDiLinkCardListener);

    void registerSceneModeCallback(@NonNull IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback);

    void registerSplitScreenCallBack(@NonNull IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack);

    void setActivityNameForStatusAndNavigationBar(@NonNull String str);

    void setActivityNameForStatusAndNavigationBar(@NonNull String str, int i);

    void setMultiWindow();

    void setNewFocusStatusForDivider();

    void setPackageNameForStatusAndNavigationBar(@NonNull String str);

    void setSceneMode(int i, int i2, int i3);

    void setSceneModeCallbackContent(String str);

    void setSplitScreenMode(boolean z);

    int startActivityWhenMultiWindow(@NonNull String str, @NonNull Intent intent, int i);

    void swapSplitScreenWindow();

    void toggleSplitScreen();

    void unregisterCardListener(IDiLinkCardListener iDiLinkCardListener);

    void unregisterSceneModeCallback(@NonNull IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback);

    void unregisterSplitScreenCallBack(@NonNull IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack);

    void userActivityNoUpdateLocked();
}
