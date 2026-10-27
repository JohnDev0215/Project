package com.ecarx.xui.adaptapi.bt.hfp;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IHfp {
    int getHfpAudioConnectionState();

    List<HfpClientCall> getHfpCallList();

    String getHfpConnectedAddress();

    int getHfpConnectionState();

    int getHfpRemoteBatteryIndicator();

    String getHfpRemoteNetworkOperator();

    int getHfpRemoteSignalStrength();

    String getHfpRemoteSubscriberNumber();

    boolean isHfpConnected();

    boolean isHfpInBandRingtoneSupport();

    boolean isHfpMicMute();

    boolean isHfpRemoteOnRoaming();

    boolean isHfpRemoteTelecomServiceOn();

    boolean isHfpRemoteVoiceDialOn();

    boolean isHfpServiceReady();

    void muteHfpMic(boolean z);

    void pauseHfpRender();

    boolean registerHfpCallback(IHfpCallback iHfpCallback);

    boolean reqHfpAnswerCall(int i);

    boolean reqHfpAudioTransferToCarkit();

    boolean reqHfpAudioTransferToPhone();

    boolean reqHfpConnect(String str);

    boolean reqHfpDialCall(String str);

    boolean reqHfpDisconnect(String str);

    boolean reqHfpMemoryDial(String str);

    boolean reqHfpReDial();

    boolean reqHfpRejectIncomingCall();

    boolean reqHfpSendDtmf(String str);

    boolean reqHfpTerminateCurrentCall();

    boolean reqHfpVoiceDial(boolean z);

    void startHfpRender();

    boolean unregisterHfpCallback(IHfpCallback iHfpCallback);
}
