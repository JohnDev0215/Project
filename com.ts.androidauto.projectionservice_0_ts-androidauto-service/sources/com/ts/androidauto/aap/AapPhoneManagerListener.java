package com.ts.androidauto.aap;

import com.ts.androidauto.aap.data.MediaMetadata;
import com.ts.androidauto.aap.data.NavigationPositionData;
import com.ts.androidauto.aap.data.NavigationStateData;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.androidauto.fsdk.common.BluetoothConst;
import com.ts.androidauto.fsdk.data.AndroidAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public interface AapPhoneManagerListener {
    void notifyAutoConnectStatusChanged(AndroidAutoDevice androidAutoDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode linkErrorCode);

    void notifyClusterVideoFocusStatusChanged(int i);

    void notifyClusterVideoUpdateUiConfigReply(boolean z);

    void notifyMdConnectRequest(AndroidAutoDevice androidAutoDevice);

    void notifyMediaMetadataChange(MediaMetadata mediaMetadata);

    void notifyMediaProgressChange(int i);

    void notifyMediaStatusChange(int i);

    void notifyMergeDevice(String str, int i, String str2);

    void notifyNaviFocusChanged(boolean z);

    void notifyNavigationCurrentPosition(NavigationPositionData navigationPositionData);

    void notifyNavigationState(NavigationStateData navigationStateData);

    void notifyNavigationStatusChanged(int i);

    void notifyProfileChange(String str, String str2, BluetoothConst.ProfileStatus profileStatus);

    void notifyProjectionBluetoothAddressChanged(String str);

    void notifyRequestAoaMode(AndroidAutoDevice androidAutoDevice, String str);

    void notifyRequestClusterVideoFocus(int i);

    void notifyRequestVideoFocus(int i);

    void notifyStatusChanged(AndroidAutoDevice androidAutoDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode linkErrorCode, String str);

    void notifyUpdateConnectionStatus(AndroidAutoDevice androidAutoDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode linkErrorCode);

    void notifyUpdateProjectionCapability(String str, int i, boolean z);

    void notifyVideoFocusStatusChanged(int i);

    void notifyVoiceSessionNotification(int i);

    void notifyWirelessAvailable(String str, boolean z);

    void onBatteryStatusNotification(int i, int i2, boolean z);
}
