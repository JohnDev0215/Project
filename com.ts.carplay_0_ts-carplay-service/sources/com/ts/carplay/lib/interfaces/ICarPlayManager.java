package com.ts.carplay.lib.interfaces;

import android.content.Context;
import android.view.MotionEvent;
import android.view.Surface;
import com.ts.carplay.common.data.CarPlayDevice;
import com.ts.carplay.common.data.ConnectRequest;
import com.ts.carplay.common.data.FavoriteContactListInfo;
import com.ts.carplay.common.data.RecentContactListInfo;
import com.ts.carplay.common.data.config.CarPlayConfig;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ICarPlayManager {
    void acceptCall(int i, String str);

    boolean cancelWirelessCarPlay(String str);

    void changeVideoFocus(int i, boolean z, String str, int i2);

    void checkWirelessCarPlayAvailability(String str, ICarPlayCapabilityListener iCarPlayCapabilityListener);

    void connectCarPlay(ConnectRequest connectRequest);

    void deleteCarPlayDevice(String str);

    void disconnectCarPlay(String str);

    void endCall(int i, String str);

    CarPlayDevice getActivedWirelessDevice();

    String getBluetoothMacAddress();

    CarPlayDevice getCarPlayAidlDevice(String str);

    List<CarPlayDevice> getCarPlayDeviceList();

    boolean getCarPlayDisPlayStatus();

    int getContactListCount(int i);

    Object getCurrentInfo(int i);

    List<FavoriteContactListInfo> getFavoriteListInfo();

    boolean getLastUserMode(int i);

    List<RecentContactListInfo> getRecentListInfo();

    int getSupportFeatures();

    List<CarPlayDevice> getUsbCarPlayDeviceList();

    void init(Context context);

    void initiateCall(int i, String str, int i2);

    void launchSiri(int i, long j);

    void onRequestViewArea(int i);

    void playSong(long j);

    void registerListener(ICarPlayManagerListener iCarPlayManagerListener);

    void requestSiri(int i);

    void requestUi(int i);

    void screenTouchEvent(MotionEvent motionEvent);

    void sendDtmf(int i, String str);

    void sendHidEventOverCarPlay(int i, int i2);

    void sendHidEventOverIap(int i, int i2);

    void setCallbackUpdate(int i, boolean z);

    void setCarPlayConfig(CarPlayConfig carPlayConfig);

    void setEnhancedSiriInfo(List<String> list, String str, int i);

    void setLastUserMode(int i, boolean z);

    void setLimitedUi(boolean z);

    int setMapAppearanceMode(String str, int i, int i2);

    void setNativeAppState(int i, boolean z, int i2);

    void setNightMode(boolean z);

    void setSongElapsedTime(long j);

    void setSurface(Surface surface);

    void setSurface(Surface surface, int i);

    int setUiAppearanceMode(String str, int i, int i2);

    void showUi(int i, int i2);

    void stopUi(int i);

    void swapCalls();

    void uninit();

    void unregisterListener(ICarPlayManagerListener iCarPlayManagerListener);

    void updateViewArea(String str, int i, int[] iArr, int i2);
}
