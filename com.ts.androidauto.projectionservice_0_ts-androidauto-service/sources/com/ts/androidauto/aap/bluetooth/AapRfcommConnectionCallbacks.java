package com.ts.androidauto.aap.bluetooth;

import com.ts.androidauto.fsdk.common.AndroidAutoConst;
import com.ts.androidauto.fsdk.data.AndroidAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public interface AapRfcommConnectionCallbacks {
    int[] getWifiChannels();

    void notifyMdRequestWifiStart(AndroidAutoDevice androidAutoDevice);

    void notifyRfcommDisconnect(AndroidAutoDevice androidAutoDevice);

    void notifyWifiConnectError(AndroidAutoDevice androidAutoDevice, AndroidAutoConst.LinkErrorCode linkErrorCode, String str);

    void notifyWifiSupportState(AndroidAutoDevice androidAutoDevice, boolean z, String str);
}
