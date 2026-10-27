package com.ecarx.xui.adaptapi.bt.hfp;

/* JADX INFO: loaded from: classes2.dex */
public interface IHfpCallback {
    void onHfpAudioStateChanged(String str, int i, int i2);

    void onHfpCallChanged(String str, HfpClientCall hfpClientCall);

    void onHfpErrorResponse(String str, int i);

    void onHfpRemoteBatteryIndicator(String str, int i, int i2, int i3);

    void onHfpRemoteRoamingStatus(String str, boolean z);

    void onHfpRemoteSignalStrength(String str, int i, int i2, int i3);

    void onHfpRemoteTelecomService(String str, boolean z);

    void onHfpServiceReady();

    void onHfpStateChanged(String str, int i, int i2);

    void onHfpVoiceDial(String str, boolean z);
}
