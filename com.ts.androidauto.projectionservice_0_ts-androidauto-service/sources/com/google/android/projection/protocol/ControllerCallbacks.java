package com.google.android.projection.protocol;

import com.google.android.projection.protocol.GalReceiver;

/* JADX INFO: loaded from: classes.dex */
interface ControllerCallbacks extends NativeCallback {
    void audioFocusRequestCallback(int i);

    void batteryStatusNotificationCallback(int i, int i2, boolean z);

    void bugreportRequestCallback(long j);

    void byeByeRequestCallback(int i);

    void byeByeResponseCallback();

    @Deprecated
    void carConnectedDevicesRequestCallback();

    void highLatencyCallback(int[] iArr);

    void latencyBackToNormalCallback(int[] iArr);

    void navigationFocusCallback(int i);

    void serviceDiscoveryRequestCallback(byte[] bArr, byte[] bArr2, byte[] bArr3, String str, String str2, String str3);

    void unrecoverableErrorCallback(int i);

    @Deprecated
    void userSwitchRequestCallback(GalReceiver.ConnectedDevice connectedDevice);

    void voiceSessionNotificationCallback(int i);
}
