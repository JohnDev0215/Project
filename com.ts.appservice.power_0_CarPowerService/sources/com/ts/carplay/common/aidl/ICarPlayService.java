package com.ts.carplay.common.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.Surface;
import com.ts.carplay.common.aidl.ICallStateCallback;
import com.ts.carplay.common.aidl.ICarPlayAppCallback;
import com.ts.carplay.common.aidl.ICarPlayDeviceListCallback;
import com.ts.carplay.common.aidl.ICarPlayServiceCallback;
import com.ts.carplay.common.aidl.ICarPlayStateCallback;
import com.ts.carplay.common.aidl.IContactListCallback;
import com.ts.carplay.common.aidl.INowPlayingCallback;
import com.ts.carplay.common.aidl.IRouteGuidanceCallback;
import com.ts.carplay.common.aidl.IWirelessCarPlayAvailabilityCallback;
import com.ts.carplay.common.data.CarPlayDevice;
import com.ts.carplay.common.data.ConnectRequest;
import com.ts.carplay.common.data.config.CarPlayConfig;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface ICarPlayService extends IInterface {
    void acceptCall(int i, String str) throws RemoteException;

    boolean cancelWirelessCarPlay(String str) throws RemoteException;

    void changeVideoFocus(int i, boolean z, String str, int i2) throws RemoteException;

    void checkWirelessCarPlayAvailability(String str, IWirelessCarPlayAvailabilityCallback iWirelessCarPlayAvailabilityCallback) throws RemoteException;

    void connectCarPlay(ConnectRequest connectRequest) throws RemoteException;

    void deleteCarPlayDevice(String str) throws RemoteException;

    void disconnectCarPlay(String str) throws RemoteException;

    void endCall(int i, String str) throws RemoteException;

    CarPlayDevice getActivedWirelessDevice() throws RemoteException;

    String getBluetoothMacAddress() throws RemoteException;

    CarPlayDevice getCarPlayDevice(String str) throws RemoteException;

    List<CarPlayDevice> getCarPlayDeviceList() throws RemoteException;

    boolean getCarPlayDisPlayStatus() throws RemoteException;

    int getCarPlaySessionStatus() throws RemoteException;

    boolean getLastUserMode(int i) throws RemoteException;

    int getSupportFeatures() throws RemoteException;

    List<CarPlayDevice> getUsbCarPlayDeviceList() throws RemoteException;

    void initiateCall(int i, String str, int i2) throws RemoteException;

    void launchSiri(int i, long j) throws RemoteException;

    void onDeviceMerged(CarPlayDevice carPlayDevice) throws RemoteException;

    void playSong(long j) throws RemoteException;

    void registerCallStateListener(ICallStateCallback iCallStateCallback) throws RemoteException;

    void registerCarPlayAppListener(ICarPlayAppCallback iCarPlayAppCallback) throws RemoteException;

    void registerCarPlayDeviceListListener(ICarPlayDeviceListCallback iCarPlayDeviceListCallback) throws RemoteException;

    void registerCarPlayServiceListener(ICarPlayServiceCallback iCarPlayServiceCallback) throws RemoteException;

    void registerCarPlayStateListener(ICarPlayStateCallback iCarPlayStateCallback) throws RemoteException;

    void registerContactListListener(IContactListCallback iContactListCallback) throws RemoteException;

    void registerNowPlayingListener(INowPlayingCallback iNowPlayingCallback) throws RemoteException;

    void registerRouteGuidanceListener(IRouteGuidanceCallback iRouteGuidanceCallback) throws RemoteException;

    void requestSiri(int i) throws RemoteException;

    void requestUi(int i) throws RemoteException;

    void screenTouchEvent(MotionEvent motionEvent) throws RemoteException;

    void sendDtmf(int i, String str) throws RemoteException;

    void sendHidEventOverCarPlay(int i, int i2) throws RemoteException;

    void sendHidEventOverIap(int i, int i2) throws RemoteException;

    void setCarPlayConfig(CarPlayConfig carPlayConfig) throws RemoteException;

    void setEnhancedSiriInfo(List<String> list, String str, int i) throws RemoteException;

    void setLastUserMode(int i, boolean z) throws RemoteException;

    void setLimitedUi(boolean z) throws RemoteException;

    int setMapAppearanceMode(String str, int i, int i2) throws RemoteException;

    void setNativeAppState(int i, boolean z, int i2) throws RemoteException;

    void setNightMode(boolean z) throws RemoteException;

    void setSongElapsedTime(long j) throws RemoteException;

    void setSurface(Surface surface) throws RemoteException;

    void setSurfaceWithType(Surface surface, int i) throws RemoteException;

    int setUiAppearanceMode(String str, int i, int i2) throws RemoteException;

    void showUi(int i, int i2) throws RemoteException;

    void stopUi(int i) throws RemoteException;

    void swapCalls() throws RemoteException;

    void unregisterCallStateListener(ICallStateCallback iCallStateCallback) throws RemoteException;

    void unregisterCarPlayAppListener(ICarPlayAppCallback iCarPlayAppCallback) throws RemoteException;

    void unregisterCarPlayDeviceListListener(ICarPlayDeviceListCallback iCarPlayDeviceListCallback) throws RemoteException;

    void unregisterCarPlayServiceListener(ICarPlayServiceCallback iCarPlayServiceCallback) throws RemoteException;

    void unregisterCarPlayStateListener(ICarPlayStateCallback iCarPlayStateCallback) throws RemoteException;

    void unregisterContactListListener(IContactListCallback iContactListCallback) throws RemoteException;

    void unregisterNowPlayingListener(INowPlayingCallback iNowPlayingCallback) throws RemoteException;

    void unregisterRouteGuidanceListener(IRouteGuidanceCallback iRouteGuidanceCallback) throws RemoteException;

    void updateViewArea(String str, int i, int[] iArr, int i2) throws RemoteException;

    public static class Default implements ICarPlayService {
        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void registerCarPlayAppListener(ICarPlayAppCallback callback) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void unregisterCarPlayAppListener(ICarPlayAppCallback callback) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void registerCarPlayStateListener(ICarPlayStateCallback callback) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void unregisterCarPlayStateListener(ICarPlayStateCallback callback) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void registerCarPlayServiceListener(ICarPlayServiceCallback callback) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void unregisterCarPlayServiceListener(ICarPlayServiceCallback callback) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void registerCarPlayDeviceListListener(ICarPlayDeviceListCallback callback) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void unregisterCarPlayDeviceListListener(ICarPlayDeviceListCallback callback) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void registerCallStateListener(ICallStateCallback callback) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void unregisterCallStateListener(ICallStateCallback callback) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void registerContactListListener(IContactListCallback callback) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void unregisterContactListListener(IContactListCallback callback) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void registerRouteGuidanceListener(IRouteGuidanceCallback callback) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void unregisterRouteGuidanceListener(IRouteGuidanceCallback callback) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void registerNowPlayingListener(INowPlayingCallback callback) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void unregisterNowPlayingListener(INowPlayingCallback callback) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void setNightMode(boolean mode) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void setLimitedUi(boolean mode) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void setLastUserMode(int resource, boolean mode) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public boolean getLastUserMode(int resource) throws RemoteException {
            return false;
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void changeVideoFocus(int priority, boolean backgroundSupport, String borrowId, int action) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public boolean getCarPlayDisPlayStatus() throws RemoteException {
            return false;
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public CarPlayDevice getActivedWirelessDevice() throws RemoteException {
            return null;
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void setSurface(Surface surface) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void setSurfaceWithType(Surface surface, int screenType) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public String getBluetoothMacAddress() throws RemoteException {
            return null;
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public int getSupportFeatures() throws RemoteException {
            return 0;
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void screenTouchEvent(MotionEvent event) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void requestUi(int launchMode) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void initiateCall(int callType, String destId, int callService) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void acceptCall(int action, String callUuid) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void endCall(int action, String callUuid) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void swapCalls() throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void sendDtmf(int toneType, String callUuid) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void sendHidEventOverIap(int keyCode, int keyAction) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void sendHidEventOverCarPlay(int keyCode, int keyAction) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void setNativeAppState(int appState, boolean enabled, int speechMode) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void setCarPlayConfig(CarPlayConfig carPlayConfig) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public int getCarPlaySessionStatus() throws RemoteException {
            return 0;
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void setSongElapsedTime(long elapsedTime) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void requestSiri(int siriAction) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void launchSiri(int siriAction, long siriTriggerTimestamp) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void setEnhancedSiriInfo(List<String> voiceModelSupportedLanguages, String voiceModelCurrentLanguage, int supportedSiriTriggerZones) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void playSong(long pbIndex) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void connectCarPlay(ConnectRequest request) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void disconnectCarPlay(String devId) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void deleteCarPlayDevice(String devId) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void checkWirelessCarPlayAvailability(String macAddress, IWirelessCarPlayAvailabilityCallback callback) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void onDeviceMerged(CarPlayDevice device) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public CarPlayDevice getCarPlayDevice(String devId) throws RemoteException {
            return null;
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public int setUiAppearanceMode(String uuid, int uiMode, int uiSetting) throws RemoteException {
            return 0;
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public int setMapAppearanceMode(String uuid, int mapMode, int mapSetting) throws RemoteException {
            return 0;
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public List<CarPlayDevice> getUsbCarPlayDeviceList() throws RemoteException {
            return null;
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public boolean cancelWirelessCarPlay(String mac) throws RemoteException {
            return false;
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public List<CarPlayDevice> getCarPlayDeviceList() throws RemoteException {
            return null;
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void showUi(int screenType, int url) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void stopUi(int screenType) throws RemoteException {
        }

        @Override // com.ts.carplay.common.aidl.ICarPlayService
        public void updateViewArea(String uuid, int index, int[] adjacentViewAreas, int animationDurationMillis) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICarPlayService {
        private static final String DESCRIPTOR = "com.ts.carplay.common.aidl.ICarPlayService";
        static final int TRANSACTION_acceptCall = 31;
        static final int TRANSACTION_cancelWirelessCarPlay = 54;
        static final int TRANSACTION_changeVideoFocus = 21;
        static final int TRANSACTION_checkWirelessCarPlayAvailability = 48;
        static final int TRANSACTION_connectCarPlay = 45;
        static final int TRANSACTION_deleteCarPlayDevice = 47;
        static final int TRANSACTION_disconnectCarPlay = 46;
        static final int TRANSACTION_endCall = 32;
        static final int TRANSACTION_getActivedWirelessDevice = 23;
        static final int TRANSACTION_getBluetoothMacAddress = 26;
        static final int TRANSACTION_getCarPlayDevice = 50;
        static final int TRANSACTION_getCarPlayDeviceList = 55;
        static final int TRANSACTION_getCarPlayDisPlayStatus = 22;
        static final int TRANSACTION_getCarPlaySessionStatus = 39;
        static final int TRANSACTION_getLastUserMode = 20;
        static final int TRANSACTION_getSupportFeatures = 27;
        static final int TRANSACTION_getUsbCarPlayDeviceList = 53;
        static final int TRANSACTION_initiateCall = 30;
        static final int TRANSACTION_launchSiri = 42;
        static final int TRANSACTION_onDeviceMerged = 49;
        static final int TRANSACTION_playSong = 44;
        static final int TRANSACTION_registerCallStateListener = 9;
        static final int TRANSACTION_registerCarPlayAppListener = 1;
        static final int TRANSACTION_registerCarPlayDeviceListListener = 7;
        static final int TRANSACTION_registerCarPlayServiceListener = 5;
        static final int TRANSACTION_registerCarPlayStateListener = 3;
        static final int TRANSACTION_registerContactListListener = 11;
        static final int TRANSACTION_registerNowPlayingListener = 15;
        static final int TRANSACTION_registerRouteGuidanceListener = 13;
        static final int TRANSACTION_requestSiri = 41;
        static final int TRANSACTION_requestUi = 29;
        static final int TRANSACTION_screenTouchEvent = 28;
        static final int TRANSACTION_sendDtmf = 34;
        static final int TRANSACTION_sendHidEventOverCarPlay = 36;
        static final int TRANSACTION_sendHidEventOverIap = 35;
        static final int TRANSACTION_setCarPlayConfig = 38;
        static final int TRANSACTION_setEnhancedSiriInfo = 43;
        static final int TRANSACTION_setLastUserMode = 19;
        static final int TRANSACTION_setLimitedUi = 18;
        static final int TRANSACTION_setMapAppearanceMode = 52;
        static final int TRANSACTION_setNativeAppState = 37;
        static final int TRANSACTION_setNightMode = 17;
        static final int TRANSACTION_setSongElapsedTime = 40;
        static final int TRANSACTION_setSurface = 24;
        static final int TRANSACTION_setSurfaceWithType = 25;
        static final int TRANSACTION_setUiAppearanceMode = 51;
        static final int TRANSACTION_showUi = 56;
        static final int TRANSACTION_stopUi = 57;
        static final int TRANSACTION_swapCalls = 33;
        static final int TRANSACTION_unregisterCallStateListener = 10;
        static final int TRANSACTION_unregisterCarPlayAppListener = 2;
        static final int TRANSACTION_unregisterCarPlayDeviceListListener = 8;
        static final int TRANSACTION_unregisterCarPlayServiceListener = 6;
        static final int TRANSACTION_unregisterCarPlayStateListener = 4;
        static final int TRANSACTION_unregisterContactListListener = 12;
        static final int TRANSACTION_unregisterNowPlayingListener = 16;
        static final int TRANSACTION_unregisterRouteGuidanceListener = 14;
        static final int TRANSACTION_updateViewArea = 58;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICarPlayService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof ICarPlayService)) {
                return (ICarPlayService) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Surface surface;
            Surface surface2;
            MotionEvent motionEvent;
            CarPlayConfig carPlayConfigCreateFromParcel;
            ConnectRequest connectRequestCreateFromParcel;
            CarPlayDevice carPlayDeviceCreateFromParcel;
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerCarPlayAppListener(ICarPlayAppCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterCarPlayAppListener(ICarPlayAppCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerCarPlayStateListener(ICarPlayStateCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterCarPlayStateListener(ICarPlayStateCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerCarPlayServiceListener(ICarPlayServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterCarPlayServiceListener(ICarPlayServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerCarPlayDeviceListListener(ICarPlayDeviceListCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterCarPlayDeviceListListener(ICarPlayDeviceListCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerCallStateListener(ICallStateCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterCallStateListener(ICallStateCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerContactListListener(IContactListCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterContactListListener(IContactListCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerRouteGuidanceListener(IRouteGuidanceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterRouteGuidanceListener(IRouteGuidanceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerNowPlayingListener(INowPlayingCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterNowPlayingListener(INowPlayingCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNightMode(parcel.readInt() != 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLimitedUi(parcel.readInt() != 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLastUserMode(parcel.readInt(), parcel.readInt() != 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean lastUserMode = getLastUserMode(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(lastUserMode ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    changeVideoFocus(parcel.readInt(), parcel.readInt() != 0, parcel.readString(), parcel.readInt());
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean carPlayDisPlayStatus = getCarPlayDisPlayStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(carPlayDisPlayStatus ? 1 : 0);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    CarPlayDevice activedWirelessDevice = getActivedWirelessDevice();
                    parcel2.writeNoException();
                    if (activedWirelessDevice != null) {
                        parcel2.writeInt(1);
                        activedWirelessDevice.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        surface = (Surface) Surface.CREATOR.createFromParcel(parcel);
                    } else {
                        surface = null;
                    }
                    setSurface(surface);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        surface2 = (Surface) Surface.CREATOR.createFromParcel(parcel);
                    } else {
                        surface2 = null;
                    }
                    setSurfaceWithType(surface2, parcel.readInt());
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    String bluetoothMacAddress = getBluetoothMacAddress();
                    parcel2.writeNoException();
                    parcel2.writeString(bluetoothMacAddress);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    int supportFeatures = getSupportFeatures();
                    parcel2.writeNoException();
                    parcel2.writeInt(supportFeatures);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        motionEvent = (MotionEvent) MotionEvent.CREATOR.createFromParcel(parcel);
                    } else {
                        motionEvent = null;
                    }
                    screenTouchEvent(motionEvent);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestUi(parcel.readInt());
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    initiateCall(parcel.readInt(), parcel.readString(), parcel.readInt());
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    acceptCall(parcel.readInt(), parcel.readString());
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    endCall(parcel.readInt(), parcel.readString());
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    swapCalls();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendDtmf(parcel.readInt(), parcel.readString());
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendHidEventOverIap(parcel.readInt(), parcel.readInt());
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendHidEventOverCarPlay(parcel.readInt(), parcel.readInt());
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNativeAppState(parcel.readInt(), parcel.readInt() != 0, parcel.readInt());
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        carPlayConfigCreateFromParcel = CarPlayConfig.CREATOR.createFromParcel(parcel);
                    } else {
                        carPlayConfigCreateFromParcel = null;
                    }
                    setCarPlayConfig(carPlayConfigCreateFromParcel);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    int carPlaySessionStatus = getCarPlaySessionStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(carPlaySessionStatus);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSongElapsedTime(parcel.readLong());
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestSiri(parcel.readInt());
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    launchSiri(parcel.readInt(), parcel.readLong());
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    setEnhancedSiriInfo(parcel.createStringArrayList(), parcel.readString(), parcel.readInt());
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    playSong(parcel.readLong());
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        connectRequestCreateFromParcel = ConnectRequest.CREATOR.createFromParcel(parcel);
                    } else {
                        connectRequestCreateFromParcel = null;
                    }
                    connectCarPlay(connectRequestCreateFromParcel);
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    disconnectCarPlay(parcel.readString());
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteCarPlayDevice(parcel.readString());
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    checkWirelessCarPlayAvailability(parcel.readString(), IWirelessCarPlayAvailabilityCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        carPlayDeviceCreateFromParcel = CarPlayDevice.CREATOR.createFromParcel(parcel);
                    } else {
                        carPlayDeviceCreateFromParcel = null;
                    }
                    onDeviceMerged(carPlayDeviceCreateFromParcel);
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    CarPlayDevice carPlayDevice = getCarPlayDevice(parcel.readString());
                    parcel2.writeNoException();
                    if (carPlayDevice != null) {
                        parcel2.writeInt(1);
                        carPlayDevice.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    int uiAppearanceMode = setUiAppearanceMode(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(uiAppearanceMode);
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    int mapAppearanceMode = setMapAppearanceMode(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(mapAppearanceMode);
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<CarPlayDevice> usbCarPlayDeviceList = getUsbCarPlayDeviceList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(usbCarPlayDeviceList);
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCancelWirelessCarPlay = cancelWirelessCarPlay(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCancelWirelessCarPlay ? 1 : 0);
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<CarPlayDevice> carPlayDeviceList = getCarPlayDeviceList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(carPlayDeviceList);
                    return true;
                case 56:
                    parcel.enforceInterface(DESCRIPTOR);
                    showUi(parcel.readInt(), parcel.readInt());
                    return true;
                case 57:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopUi(parcel.readInt());
                    return true;
                case 58:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateViewArea(parcel.readString(), parcel.readInt(), parcel.createIntArray(), parcel.readInt());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements ICarPlayService {
            public static ICarPlayService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void registerCarPlayAppListener(ICarPlayAppCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCarPlayAppListener(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void unregisterCarPlayAppListener(ICarPlayAppCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterCarPlayAppListener(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void registerCarPlayStateListener(ICarPlayStateCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCarPlayStateListener(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void unregisterCarPlayStateListener(ICarPlayStateCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterCarPlayStateListener(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void registerCarPlayServiceListener(ICarPlayServiceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCarPlayServiceListener(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void unregisterCarPlayServiceListener(ICarPlayServiceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterCarPlayServiceListener(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void registerCarPlayDeviceListListener(ICarPlayDeviceListCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(7, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCarPlayDeviceListListener(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void unregisterCarPlayDeviceListListener(ICarPlayDeviceListCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(8, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterCarPlayDeviceListListener(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void registerCallStateListener(ICallStateCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(9, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerCallStateListener(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void unregisterCallStateListener(ICallStateCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(10, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterCallStateListener(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void registerContactListListener(IContactListCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(11, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerContactListListener(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void unregisterContactListListener(IContactListCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(12, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterContactListListener(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void registerRouteGuidanceListener(IRouteGuidanceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(13, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerRouteGuidanceListener(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void unregisterRouteGuidanceListener(IRouteGuidanceCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(14, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterRouteGuidanceListener(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void registerNowPlayingListener(INowPlayingCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerNowPlayingListener(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void unregisterNowPlayingListener(INowPlayingCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterNowPlayingListener(callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void setNightMode(boolean mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode ? 1 : 0);
                    boolean _status = this.mRemote.transact(17, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNightMode(mode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void setLimitedUi(boolean mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode ? 1 : 0);
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLimitedUi(mode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void setLastUserMode(int resource, boolean mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(resource);
                    _data.writeInt(mode ? 1 : 0);
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLastUserMode(resource, mode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public boolean getLastUserMode(int resource) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(resource);
                    boolean _status = this.mRemote.transact(20, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLastUserMode(resource);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void changeVideoFocus(int priority, boolean backgroundSupport, String borrowId, int action) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(priority);
                    _data.writeInt(backgroundSupport ? 1 : 0);
                    _data.writeString(borrowId);
                    _data.writeInt(action);
                    boolean _status = this.mRemote.transact(21, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().changeVideoFocus(priority, backgroundSupport, borrowId, action);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public boolean getCarPlayDisPlayStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarPlayDisPlayStatus();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public CarPlayDevice getActivedWirelessDevice() throws RemoteException {
                CarPlayDevice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActivedWirelessDevice();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CarPlayDevice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void setSurface(Surface surface) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (surface != null) {
                        _data.writeInt(1);
                        surface.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(24, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSurface(surface);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void setSurfaceWithType(Surface surface, int screenType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (surface != null) {
                        _data.writeInt(1);
                        surface.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(screenType);
                    boolean _status = this.mRemote.transact(25, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSurfaceWithType(surface, screenType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public String getBluetoothMacAddress() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBluetoothMacAddress();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public int getSupportFeatures() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(27, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSupportFeatures();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void screenTouchEvent(MotionEvent event) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (event != null) {
                        _data.writeInt(1);
                        event.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(28, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().screenTouchEvent(event);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void requestUi(int launchMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(launchMode);
                    boolean _status = this.mRemote.transact(29, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestUi(launchMode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void initiateCall(int callType, String destId, int callService) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(callType);
                    _data.writeString(destId);
                    _data.writeInt(callService);
                    boolean _status = this.mRemote.transact(30, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().initiateCall(callType, destId, callService);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void acceptCall(int action, String callUuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(action);
                    _data.writeString(callUuid);
                    boolean _status = this.mRemote.transact(31, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().acceptCall(action, callUuid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void endCall(int action, String callUuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(action);
                    _data.writeString(callUuid);
                    boolean _status = this.mRemote.transact(32, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().endCall(action, callUuid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void swapCalls() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(33, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().swapCalls();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void sendDtmf(int toneType, String callUuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(toneType);
                    _data.writeString(callUuid);
                    boolean _status = this.mRemote.transact(34, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendDtmf(toneType, callUuid);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void sendHidEventOverIap(int keyCode, int keyAction) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(keyCode);
                    _data.writeInt(keyAction);
                    boolean _status = this.mRemote.transact(35, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendHidEventOverIap(keyCode, keyAction);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void sendHidEventOverCarPlay(int keyCode, int keyAction) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(keyCode);
                    _data.writeInt(keyAction);
                    boolean _status = this.mRemote.transact(36, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendHidEventOverCarPlay(keyCode, keyAction);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void setNativeAppState(int appState, boolean enabled, int speechMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(appState);
                    _data.writeInt(enabled ? 1 : 0);
                    _data.writeInt(speechMode);
                    boolean _status = this.mRemote.transact(37, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNativeAppState(appState, enabled, speechMode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void setCarPlayConfig(CarPlayConfig carPlayConfig) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (carPlayConfig != null) {
                        _data.writeInt(1);
                        carPlayConfig.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(38, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setCarPlayConfig(carPlayConfig);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public int getCarPlaySessionStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarPlaySessionStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void setSongElapsedTime(long elapsedTime) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(elapsedTime);
                    boolean _status = this.mRemote.transact(40, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSongElapsedTime(elapsedTime);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void requestSiri(int siriAction) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(siriAction);
                    boolean _status = this.mRemote.transact(41, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestSiri(siriAction);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void launchSiri(int siriAction, long siriTriggerTimestamp) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(siriAction);
                    _data.writeLong(siriTriggerTimestamp);
                    boolean _status = this.mRemote.transact(42, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().launchSiri(siriAction, siriTriggerTimestamp);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void setEnhancedSiriInfo(List<String> voiceModelSupportedLanguages, String voiceModelCurrentLanguage, int supportedSiriTriggerZones) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStringList(voiceModelSupportedLanguages);
                    _data.writeString(voiceModelCurrentLanguage);
                    _data.writeInt(supportedSiriTriggerZones);
                    boolean _status = this.mRemote.transact(43, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setEnhancedSiriInfo(voiceModelSupportedLanguages, voiceModelCurrentLanguage, supportedSiriTriggerZones);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void playSong(long pbIndex) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(pbIndex);
                    boolean _status = this.mRemote.transact(44, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().playSong(pbIndex);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void connectCarPlay(ConnectRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(45, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().connectCarPlay(request);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void disconnectCarPlay(String devId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    boolean _status = this.mRemote.transact(46, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disconnectCarPlay(devId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void deleteCarPlayDevice(String devId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    boolean _status = this.mRemote.transact(47, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteCarPlayDevice(devId);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void checkWirelessCarPlayAvailability(String macAddress, IWirelessCarPlayAvailabilityCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(macAddress);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _status = this.mRemote.transact(48, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().checkWirelessCarPlayAvailability(macAddress, callback);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void onDeviceMerged(CarPlayDevice device) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (device != null) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(49, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onDeviceMerged(device);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public CarPlayDevice getCarPlayDevice(String devId) throws RemoteException {
                CarPlayDevice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    boolean _status = this.mRemote.transact(50, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarPlayDevice(devId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = CarPlayDevice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public int setUiAppearanceMode(String uuid, int uiMode, int uiSetting) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uuid);
                    _data.writeInt(uiMode);
                    _data.writeInt(uiSetting);
                    boolean _status = this.mRemote.transact(51, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setUiAppearanceMode(uuid, uiMode, uiSetting);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public int setMapAppearanceMode(String uuid, int mapMode, int mapSetting) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uuid);
                    _data.writeInt(mapMode);
                    _data.writeInt(mapSetting);
                    boolean _status = this.mRemote.transact(52, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setMapAppearanceMode(uuid, mapMode, mapSetting);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public List<CarPlayDevice> getUsbCarPlayDeviceList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(53, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUsbCarPlayDeviceList();
                    }
                    _reply.readException();
                    List<CarPlayDevice> _result = _reply.createTypedArrayList(CarPlayDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public boolean cancelWirelessCarPlay(String mac) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(mac);
                    boolean _status = this.mRemote.transact(54, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelWirelessCarPlay(mac);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public List<CarPlayDevice> getCarPlayDeviceList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(55, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCarPlayDeviceList();
                    }
                    _reply.readException();
                    List<CarPlayDevice> _result = _reply.createTypedArrayList(CarPlayDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void showUi(int screenType, int url) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(screenType);
                    _data.writeInt(url);
                    boolean _status = this.mRemote.transact(56, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showUi(screenType, url);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void stopUi(int screenType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(screenType);
                    boolean _status = this.mRemote.transact(57, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopUi(screenType);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.carplay.common.aidl.ICarPlayService
            public void updateViewArea(String uuid, int index, int[] adjacentViewAreas, int animationDurationMillis) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(uuid);
                    _data.writeInt(index);
                    _data.writeIntArray(adjacentViewAreas);
                    _data.writeInt(animationDurationMillis);
                    boolean _status = this.mRemote.transact(58, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateViewArea(uuid, index, adjacentViewAreas, animationDurationMillis);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICarPlayService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static ICarPlayService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
