package com.ecarx.xui.adaptapi.bt.spp;

/* JADX INFO: loaded from: classes2.dex */
public interface ISppCallback {
    void onSppAppleIapAuthenticationRequest(String str);

    void onSppDataReceived(String str, byte[] bArr);

    void onSppErrorResponse(String str, int i);

    void onSppSendData(String str, int i);

    void onSppServiceReady();

    void onSppStateChanged(String str, String str2, int i, int i2);

    void retSppConnectedDeviceAddressList(int i, String[] strArr, String[] strArr2);
}
