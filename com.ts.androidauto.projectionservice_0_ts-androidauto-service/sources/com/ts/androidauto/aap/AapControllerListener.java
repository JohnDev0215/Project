package com.ts.androidauto.aap;

import com.ts.androidauto.aap.data.MediaMetadata;
import com.ts.androidauto.aap.data.NavigationPositionData;
import com.ts.androidauto.aap.data.NavigationStateData;
import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.androidauto.fsdk.data.AndroidAutoDevice;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface AapControllerListener {
    List<AndroidAutoDevice> getCurrConnectedDevice();

    int[] getSupportedWiFiChannels();

    void notifiSupportAapWirelessResult(AndroidAutoDevice androidAutoDevice, boolean z, String str);

    void notifyClusterVideoFocusStatusChanged(int i);

    void notifyClusterVideoUpdateUiConfigReply(boolean z);

    void notifyMdConnectRequest(AndroidAutoDevice androidAutoDevice);

    void notifyMediaMetadataChange(MediaMetadata mediaMetadata);

    void notifyMediaProgressChange(int i);

    void notifyMediaStatusChange(int i);

    void notifyNaviFocusChanged(boolean z);

    void notifyNavigationCurrentPosition(NavigationPositionData navigationPositionData);

    void notifyNavigationState(NavigationStateData navigationStateData);

    void notifyNavigationStatusChanged(int i);

    void notifyVideoFocusStatusChanged(int i);

    void notifyVoiceSessionNotification(int i);

    void onMdAuthFailed();

    void onMergeDevice(String str, String str2);

    void onPhoneManagerBatteryStatusNotification(int i, int i2, boolean z);

    void onPhoneManagerNotSupportAa(AndroidAutoDevice androidAutoDevice);

    void onPhoneManagerPoorWirelessNetworkConnectivity();

    void onPhoneManagerSetupWifiAp(AndroidAutoDevice androidAutoDevice, boolean z);

    void onPhoneManagerStatusChanged(AndroidAutoDevice androidAutoDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode linkErrorCode);

    void onPhoneManagerStatusChanged(AndroidAutoDevice androidAutoDevice, AndroidAutoConst.LinkStatus linkStatus, AndroidAutoConst.LinkErrorCode linkErrorCode, String str);

    void onVehicleReqRfcommDisconnect(AndroidAutoDevice androidAutoDevice);
}
