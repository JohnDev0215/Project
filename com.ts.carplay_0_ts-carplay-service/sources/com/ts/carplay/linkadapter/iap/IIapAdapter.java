package com.ts.carplay.linkadapter.iap;

import android.content.Context;
import com.ts.carplay.common.data.FavoriteContactListInfo;
import com.ts.carplay.common.data.RecentContactListInfo;
import com.ts.carplay.linkadapter.iap.data.AccessoryWiFiInfo;
import com.ts.carplay.linkadapter.iap.data.CarplayStartSessionInfo;
import com.ts.carplay.linkadapter.iap.data.IapConfig;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IIapAdapter {
    void acceptCall(int i, int i2, String str);

    int connectSession(int i);

    boolean createSession(int i, IapConfig iapConfig);

    boolean destroySession(int i);

    int disconnectSession(int i);

    void endCall(int i, int i2, String str);

    int getContactListCount(int i);

    Object getCurrentInfo(int i);

    List<FavoriteContactListInfo> getFavoriteListInfo();

    int getNewConnectType();

    List<RecentContactListInfo> getRecentListInfo();

    void init(Context context);

    void initiateCall(int i, int i2, String str, int i3);

    int playSong(int i, long j);

    void registerListener(IIapAdapterListener iIapAdapterListener);

    int requestAppLaunch(int i, String str, int i2);

    int sendAccessoryWiFiConfigurationInformation(int i, AccessoryWiFiInfo accessoryWiFiInfo);

    int sendBluetoothPairingAccessoryInformation(int i, short s, byte[][] bArr, byte[][] bArr2);

    int sendBluetoothPairingStatus(int i, short s, boolean z, String str);

    int sendCarplayStartSession(int i, CarplayStartSessionInfo carplayStartSessionInfo);

    void sendDtmf(int i, int i2, String str);

    void sendHidEventOverIap(int i, int i2, int i3);

    int sendLocationInformation(int i, String str);

    int sendOobBtPairingAccessoryInformation(int i, long j);

    int sendOobBtPairingCompletionInformation(int i, int i2);

    void setCallbackUpdate(int i, boolean z);

    int setSongElapsedTime(int i, long j);

    void swapCalls(int i);

    void uninit();

    void unregisterListener(IIapAdapterListener iIapAdapterListener);
}
