package com.ecarx.xui.adaptapi.bt.avrcp;

/* JADX INFO: loaded from: classes2.dex */
public interface IAvrcp {
    String getAvrcpConnectedAddress();

    int getAvrcpConnectionState();

    boolean isAvrcp13Supported(String str);

    boolean isAvrcp14BrowsingChannelEstablished();

    boolean isAvrcp14Supported(String str);

    boolean isAvrcpConnected();

    boolean isAvrcpServiceReady();

    boolean registerAvrcpCallback(IAvrcpCallback iAvrcpCallback);

    boolean reqAvrcp13GetCapabilitiesSupportEvent();

    boolean reqAvrcp13GetElementAttributesPlaying();

    boolean reqAvrcp13GetPlayStatus();

    boolean reqAvrcp13GetPlayerSettingAttributesList();

    boolean reqAvrcp13GetPlayerSettingCurrentValues();

    boolean reqAvrcp13GetPlayerSettingValuesList(byte b);

    boolean reqAvrcp13NextGroup();

    boolean reqAvrcp13PreviousGroup();

    boolean reqAvrcp13SetPlayerSettingValue(byte b, byte b2);

    boolean reqAvrcp14AddToNowPlaying(byte b, int i, long j);

    boolean reqAvrcp14ChangePath(int i, long j, byte b);

    boolean reqAvrcp14GetFolderItems(byte b);

    boolean reqAvrcp14GetItemAttributes(byte b, int i, long j);

    boolean reqAvrcp14PlaySelectedItem(byte b, int i, long j);

    boolean reqAvrcp14Search(String str);

    boolean reqAvrcp14SetAbsoluteVolume(byte b);

    boolean reqAvrcp14SetAddressedPlayer(int i);

    boolean reqAvrcp14SetBrowsedPlayer(int i);

    boolean reqAvrcpBackward();

    boolean reqAvrcpConnect(String str);

    boolean reqAvrcpDisconnect(String str);

    boolean reqAvrcpForward();

    boolean reqAvrcpPause();

    boolean reqAvrcpPlay();

    boolean reqAvrcpQueryVersion(String str);

    boolean reqAvrcpRegisterEventWatcher(byte b, long j);

    boolean reqAvrcpStartFastForward();

    boolean reqAvrcpStartRewind();

    boolean reqAvrcpStop();

    boolean reqAvrcpStopFastForward();

    boolean reqAvrcpStopRewind();

    boolean reqAvrcpUnregisterEventWatcher(byte b);

    boolean reqAvrcpVolumeDown();

    boolean reqAvrcpVolumeUp();

    boolean unregisterAvrcpCallback(IAvrcpCallback iAvrcpCallback);
}
