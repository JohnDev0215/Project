package com.ts.androidauto.sdk.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.Surface;
import com.ts.androidauto.sdk.aidl.IAndroidAutoAppCallback;
import com.ts.androidauto.sdk.aidl.IAndroidAutoDeviceListCallback;
import com.ts.androidauto.sdk.aidl.IAndroidAutoServiceCallback;
import com.ts.androidauto.sdk.aidl.IAndroidAutoSessionStatusCallback;
import com.ts.androidauto.sdk.aidl.IBatteryStatusCallback;
import com.ts.androidauto.sdk.aidl.IMediaDataCallback;
import com.ts.androidauto.sdk.aidl.INavigationDataCallback;
import com.ts.androidauto.sdk.aidl.data.AndroidAutoDevice;
import com.ts.androidauto.sdk.aidl.data.ConnectRequest;
import com.ts.androidauto.sdk.aidl.data.MediaMetadata;
import com.ts.androidauto.sdk.aidl.data.UiConfig;
import com.ts.androidauto.sdk.aidl.data.VehicleInfo;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IAndroidAutoService extends IInterface {
    void addAppCallback(IAndroidAutoAppCallback iAndroidAutoAppCallback) throws RemoteException;

    void addBatteryStatusCallback(IBatteryStatusCallback iBatteryStatusCallback) throws RemoteException;

    void addDeviceListCallback(IAndroidAutoDeviceListCallback iAndroidAutoDeviceListCallback) throws RemoteException;

    void addMediaDataCallback(IMediaDataCallback iMediaDataCallback) throws RemoteException;

    void addNavigationDataCallback(INavigationDataCallback iNavigationDataCallback) throws RemoteException;

    void addServiceCallback(IAndroidAutoServiceCallback iAndroidAutoServiceCallback) throws RemoteException;

    void addSessionStatusCallback(IAndroidAutoSessionStatusCallback iAndroidAutoSessionStatusCallback) throws RemoteException;

    void connectAndroidAuto(String str, boolean z) throws RemoteException;

    void connectAndroidAutoByRequest(ConnectRequest connectRequest) throws RemoteException;

    void deleteAndroidAutoDevice(String str) throws RemoteException;

    void disconnectAndroidAuto(String str) throws RemoteException;

    boolean getAndroidAutoAppStatus(int i) throws RemoteException;

    AndroidAutoDevice getAndroidAutoDevice(String str) throws RemoteException;

    List<AndroidAutoDevice> getAndroidAutoDeviceList() throws RemoteException;

    int getAndroidAutoSessionStatus() throws RemoteException;

    List<String> getAvailableDevices() throws RemoteException;

    int getLastAvailableCapability(String str) throws RemoteException;

    String getLastAvailableDevice() throws RemoteException;

    MediaMetadata getMediaMetadata() throws RemoteException;

    int getMediaProgress() throws RemoteException;

    int getMusicStatus() throws RemoteException;

    String getProjectionBluetoothAddress() throws RemoteException;

    List<AndroidAutoDevice> getUsbAndroidAutoDeviceList() throws RemoteException;

    int getVideoFocusStatus() throws RemoteException;

    void next() throws RemoteException;

    void pause() throws RemoteException;

    void play() throws RemoteException;

    void previous() throws RemoteException;

    void removeAppCallback(IAndroidAutoAppCallback iAndroidAutoAppCallback) throws RemoteException;

    void removeBatteryStatusCallback(IBatteryStatusCallback iBatteryStatusCallback) throws RemoteException;

    void removeDeviceListCallback(IAndroidAutoDeviceListCallback iAndroidAutoDeviceListCallback) throws RemoteException;

    void removeMediaDataCallback(IMediaDataCallback iMediaDataCallback) throws RemoteException;

    void removeNavigationDataCallback(INavigationDataCallback iNavigationDataCallback) throws RemoteException;

    void removeServiceCallback(IAndroidAutoServiceCallback iAndroidAutoServiceCallback) throws RemoteException;

    void removeSessionStatusCallback(IAndroidAutoSessionStatusCallback iAndroidAutoSessionStatusCallback) throws RemoteException;

    void requestUi(int i) throws RemoteException;

    void screenTouchEvent(MotionEvent motionEvent) throws RemoteException;

    void sendKeyEvent(int i, int i2) throws RemoteException;

    void sendKeyLongPressEvent(int i) throws RemoteException;

    void setClusterSurface(Surface surface, int i, int i2) throws RemoteException;

    void setClusterUiConfig(UiConfig uiConfig) throws RemoteException;

    void setNativeAppStates(int i, boolean z) throws RemoteException;

    void setNativePhoneCallStatus(int i) throws RemoteException;

    void setSurface(Surface surface, int i, int i2) throws RemoteException;

    void setVehicleInfo(VehicleInfo vehicleInfo) throws RemoteException;

    public static class Default implements IAndroidAutoService {
        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void addServiceCallback(IAndroidAutoServiceCallback linkCallback) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void removeServiceCallback(IAndroidAutoServiceCallback linkCallback) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void addDeviceListCallback(IAndroidAutoDeviceListCallback deviceListCallback) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void removeDeviceListCallback(IAndroidAutoDeviceListCallback deviceListCallback) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void addSessionStatusCallback(IAndroidAutoSessionStatusCallback linkCallback) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void removeSessionStatusCallback(IAndroidAutoSessionStatusCallback linkCallback) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void addAppCallback(IAndroidAutoAppCallback linkCallback) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void removeAppCallback(IAndroidAutoAppCallback linkCallback) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void addBatteryStatusCallback(IBatteryStatusCallback linkCallback) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void removeBatteryStatusCallback(IBatteryStatusCallback linkCallback) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void addMediaDataCallback(IMediaDataCallback linkCallback) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void removeMediaDataCallback(IMediaDataCallback linkCallback) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void addNavigationDataCallback(INavigationDataCallback linkCallback) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void removeNavigationDataCallback(INavigationDataCallback linkCallback) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void setSurface(Surface surface, int width, int height) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void setClusterSurface(Surface surface, int width, int height) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void screenTouchEvent(MotionEvent event) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void sendKeyEvent(int keyCode, int keyAction) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void sendKeyLongPressEvent(int keyCode) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void setVehicleInfo(VehicleInfo vehicleInfo) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public int getMusicStatus() throws RemoteException {
            return 0;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void play() throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void pause() throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void next() throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void previous() throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public int getAndroidAutoSessionStatus() throws RemoteException {
            return 0;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void requestUi(int launchMode) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void setNativeAppStates(int appState, boolean enabled) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public String getLastAvailableDevice() throws RemoteException {
            return null;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public int getLastAvailableCapability(String deviceId) throws RemoteException {
            return 0;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public List<String> getAvailableDevices() throws RemoteException {
            return null;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public int getVideoFocusStatus() throws RemoteException {
            return 0;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public String getProjectionBluetoothAddress() throws RemoteException {
            return null;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public boolean getAndroidAutoAppStatus(int appType) throws RemoteException {
            return false;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public MediaMetadata getMediaMetadata() throws RemoteException {
            return null;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public int getMediaProgress() throws RemoteException {
            return 0;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void setNativePhoneCallStatus(int status) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void connectAndroidAuto(String devId, boolean isUserRequest) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void connectAndroidAutoByRequest(ConnectRequest request) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void disconnectAndroidAuto(String devId) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void deleteAndroidAutoDevice(String devId) throws RemoteException {
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public AndroidAutoDevice getAndroidAutoDevice(String devId) throws RemoteException {
            return null;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public List<AndroidAutoDevice> getUsbAndroidAutoDeviceList() throws RemoteException {
            return null;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public List<AndroidAutoDevice> getAndroidAutoDeviceList() throws RemoteException {
            return null;
        }

        @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
        public void setClusterUiConfig(UiConfig uiConfig) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IAndroidAutoService {
        private static final String DESCRIPTOR = "com.ts.androidauto.sdk.aidl.IAndroidAutoService";
        static final int TRANSACTION_addAppCallback = 7;
        static final int TRANSACTION_addBatteryStatusCallback = 9;
        static final int TRANSACTION_addDeviceListCallback = 3;
        static final int TRANSACTION_addMediaDataCallback = 11;
        static final int TRANSACTION_addNavigationDataCallback = 13;
        static final int TRANSACTION_addServiceCallback = 1;
        static final int TRANSACTION_addSessionStatusCallback = 5;
        static final int TRANSACTION_connectAndroidAuto = 38;
        static final int TRANSACTION_connectAndroidAutoByRequest = 39;
        static final int TRANSACTION_deleteAndroidAutoDevice = 41;
        static final int TRANSACTION_disconnectAndroidAuto = 40;
        static final int TRANSACTION_getAndroidAutoAppStatus = 34;
        static final int TRANSACTION_getAndroidAutoDevice = 42;
        static final int TRANSACTION_getAndroidAutoDeviceList = 44;
        static final int TRANSACTION_getAndroidAutoSessionStatus = 26;
        static final int TRANSACTION_getAvailableDevices = 31;
        static final int TRANSACTION_getLastAvailableCapability = 30;
        static final int TRANSACTION_getLastAvailableDevice = 29;
        static final int TRANSACTION_getMediaMetadata = 35;
        static final int TRANSACTION_getMediaProgress = 36;
        static final int TRANSACTION_getMusicStatus = 21;
        static final int TRANSACTION_getProjectionBluetoothAddress = 33;
        static final int TRANSACTION_getUsbAndroidAutoDeviceList = 43;
        static final int TRANSACTION_getVideoFocusStatus = 32;
        static final int TRANSACTION_next = 24;
        static final int TRANSACTION_pause = 23;
        static final int TRANSACTION_play = 22;
        static final int TRANSACTION_previous = 25;
        static final int TRANSACTION_removeAppCallback = 8;
        static final int TRANSACTION_removeBatteryStatusCallback = 10;
        static final int TRANSACTION_removeDeviceListCallback = 4;
        static final int TRANSACTION_removeMediaDataCallback = 12;
        static final int TRANSACTION_removeNavigationDataCallback = 14;
        static final int TRANSACTION_removeServiceCallback = 2;
        static final int TRANSACTION_removeSessionStatusCallback = 6;
        static final int TRANSACTION_requestUi = 27;
        static final int TRANSACTION_screenTouchEvent = 17;
        static final int TRANSACTION_sendKeyEvent = 18;
        static final int TRANSACTION_sendKeyLongPressEvent = 19;
        static final int TRANSACTION_setClusterSurface = 16;
        static final int TRANSACTION_setClusterUiConfig = 45;
        static final int TRANSACTION_setNativeAppStates = 28;
        static final int TRANSACTION_setNativePhoneCallStatus = 37;
        static final int TRANSACTION_setSurface = 15;
        static final int TRANSACTION_setVehicleInfo = 20;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IAndroidAutoService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IAndroidAutoService)) {
                return (IAndroidAutoService) iin;
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
            VehicleInfo vehicleInfoCreateFromParcel;
            ConnectRequest connectRequestCreateFromParcel;
            UiConfig uiConfigCreateFromParcel;
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    addServiceCallback(IAndroidAutoServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeServiceCallback(IAndroidAutoServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    addDeviceListCallback(IAndroidAutoDeviceListCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeDeviceListCallback(IAndroidAutoDeviceListCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    addSessionStatusCallback(IAndroidAutoSessionStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeSessionStatusCallback(IAndroidAutoSessionStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    addAppCallback(IAndroidAutoAppCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeAppCallback(IAndroidAutoAppCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    addBatteryStatusCallback(IBatteryStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeBatteryStatusCallback(IBatteryStatusCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    addMediaDataCallback(IMediaDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeMediaDataCallback(IMediaDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    addNavigationDataCallback(INavigationDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeNavigationDataCallback(INavigationDataCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        surface = (Surface) Surface.CREATOR.createFromParcel(parcel);
                    } else {
                        surface = null;
                    }
                    setSurface(surface, parcel.readInt(), parcel.readInt());
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        surface2 = (Surface) Surface.CREATOR.createFromParcel(parcel);
                    } else {
                        surface2 = null;
                    }
                    setClusterSurface(surface2, parcel.readInt(), parcel.readInt());
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        motionEvent = (MotionEvent) MotionEvent.CREATOR.createFromParcel(parcel);
                    } else {
                        motionEvent = null;
                    }
                    screenTouchEvent(motionEvent);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendKeyEvent(parcel.readInt(), parcel.readInt());
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    sendKeyLongPressEvent(parcel.readInt());
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        vehicleInfoCreateFromParcel = VehicleInfo.CREATOR.createFromParcel(parcel);
                    } else {
                        vehicleInfoCreateFromParcel = null;
                    }
                    setVehicleInfo(vehicleInfoCreateFromParcel);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    int musicStatus = getMusicStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(musicStatus);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    play();
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    pause();
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    next();
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    previous();
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    int androidAutoSessionStatus = getAndroidAutoSessionStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(androidAutoSessionStatus);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestUi(parcel.readInt());
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNativeAppStates(parcel.readInt(), parcel.readInt() != 0);
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    String lastAvailableDevice = getLastAvailableDevice();
                    parcel2.writeNoException();
                    parcel2.writeString(lastAvailableDevice);
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    int lastAvailableCapability = getLastAvailableCapability(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(lastAvailableCapability);
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> availableDevices = getAvailableDevices();
                    parcel2.writeNoException();
                    parcel2.writeStringList(availableDevices);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    int videoFocusStatus = getVideoFocusStatus();
                    parcel2.writeNoException();
                    parcel2.writeInt(videoFocusStatus);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    String projectionBluetoothAddress = getProjectionBluetoothAddress();
                    parcel2.writeNoException();
                    parcel2.writeString(projectionBluetoothAddress);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean androidAutoAppStatus = getAndroidAutoAppStatus(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(androidAutoAppStatus ? 1 : 0);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    MediaMetadata mediaMetadata = getMediaMetadata();
                    parcel2.writeNoException();
                    if (mediaMetadata != null) {
                        parcel2.writeInt(1);
                        mediaMetadata.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    int mediaProgress = getMediaProgress();
                    parcel2.writeNoException();
                    parcel2.writeInt(mediaProgress);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNativePhoneCallStatus(parcel.readInt());
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    connectAndroidAuto(parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        connectRequestCreateFromParcel = ConnectRequest.CREATOR.createFromParcel(parcel);
                    } else {
                        connectRequestCreateFromParcel = null;
                    }
                    connectAndroidAutoByRequest(connectRequestCreateFromParcel);
                    parcel2.writeNoException();
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    disconnectAndroidAuto(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteAndroidAutoDevice(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    AndroidAutoDevice androidAutoDevice = getAndroidAutoDevice(parcel.readString());
                    parcel2.writeNoException();
                    if (androidAutoDevice != null) {
                        parcel2.writeInt(1);
                        androidAutoDevice.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<AndroidAutoDevice> usbAndroidAutoDeviceList = getUsbAndroidAutoDeviceList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(usbAndroidAutoDeviceList);
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<AndroidAutoDevice> androidAutoDeviceList = getAndroidAutoDeviceList();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(androidAutoDeviceList);
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        uiConfigCreateFromParcel = UiConfig.CREATOR.createFromParcel(parcel);
                    } else {
                        uiConfigCreateFromParcel = null;
                    }
                    setClusterUiConfig(uiConfigCreateFromParcel);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAndroidAutoService {
            public static IAndroidAutoService sDefaultImpl;
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

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void addServiceCallback(IAndroidAutoServiceCallback linkCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(linkCallback != null ? linkCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addServiceCallback(linkCallback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void removeServiceCallback(IAndroidAutoServiceCallback linkCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(linkCallback != null ? linkCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeServiceCallback(linkCallback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void addDeviceListCallback(IAndroidAutoDeviceListCallback deviceListCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(deviceListCallback != null ? deviceListCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addDeviceListCallback(deviceListCallback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void removeDeviceListCallback(IAndroidAutoDeviceListCallback deviceListCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(deviceListCallback != null ? deviceListCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeDeviceListCallback(deviceListCallback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void addSessionStatusCallback(IAndroidAutoSessionStatusCallback linkCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(linkCallback != null ? linkCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addSessionStatusCallback(linkCallback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void removeSessionStatusCallback(IAndroidAutoSessionStatusCallback linkCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(linkCallback != null ? linkCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeSessionStatusCallback(linkCallback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void addAppCallback(IAndroidAutoAppCallback linkCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(linkCallback != null ? linkCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addAppCallback(linkCallback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void removeAppCallback(IAndroidAutoAppCallback linkCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(linkCallback != null ? linkCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeAppCallback(linkCallback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void addBatteryStatusCallback(IBatteryStatusCallback linkCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(linkCallback != null ? linkCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addBatteryStatusCallback(linkCallback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void removeBatteryStatusCallback(IBatteryStatusCallback linkCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(linkCallback != null ? linkCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeBatteryStatusCallback(linkCallback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void addMediaDataCallback(IMediaDataCallback linkCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(linkCallback != null ? linkCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addMediaDataCallback(linkCallback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void removeMediaDataCallback(IMediaDataCallback linkCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(linkCallback != null ? linkCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeMediaDataCallback(linkCallback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void addNavigationDataCallback(INavigationDataCallback linkCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(linkCallback != null ? linkCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addNavigationDataCallback(linkCallback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void removeNavigationDataCallback(INavigationDataCallback linkCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(linkCallback != null ? linkCallback.asBinder() : null);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeNavigationDataCallback(linkCallback);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void setSurface(Surface surface, int width, int height) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (surface != null) {
                        _data.writeInt(1);
                        surface.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(width);
                    _data.writeInt(height);
                    boolean _status = this.mRemote.transact(15, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSurface(surface, width, height);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void setClusterSurface(Surface surface, int width, int height) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (surface != null) {
                        _data.writeInt(1);
                        surface.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(width);
                    _data.writeInt(height);
                    boolean _status = this.mRemote.transact(16, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setClusterSurface(surface, width, height);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
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
                    boolean _status = this.mRemote.transact(17, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().screenTouchEvent(event);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void sendKeyEvent(int keyCode, int keyAction) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(keyCode);
                    _data.writeInt(keyAction);
                    boolean _status = this.mRemote.transact(18, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendKeyEvent(keyCode, keyAction);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void sendKeyLongPressEvent(int keyCode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(keyCode);
                    boolean _status = this.mRemote.transact(19, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().sendKeyLongPressEvent(keyCode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void setVehicleInfo(VehicleInfo vehicleInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (vehicleInfo != null) {
                        _data.writeInt(1);
                        vehicleInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(20, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setVehicleInfo(vehicleInfo);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public int getMusicStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(21, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMusicStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void play() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(22, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().play();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void pause() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(23, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pause();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void next() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(24, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().next();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void previous() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(25, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().previous();
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public int getAndroidAutoSessionStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(26, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAndroidAutoSessionStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void requestUi(int launchMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(launchMode);
                    boolean _status = this.mRemote.transact(27, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestUi(launchMode);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void setNativeAppStates(int appState, boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(appState);
                    _data.writeInt(enabled ? 1 : 0);
                    boolean _status = this.mRemote.transact(28, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNativeAppStates(appState, enabled);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public String getLastAvailableDevice() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(29, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLastAvailableDevice();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public int getLastAvailableCapability(String deviceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(deviceId);
                    boolean _status = this.mRemote.transact(30, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLastAvailableCapability(deviceId);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public List<String> getAvailableDevices() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(31, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvailableDevices();
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public int getVideoFocusStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(32, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getVideoFocusStatus();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public String getProjectionBluetoothAddress() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(33, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getProjectionBluetoothAddress();
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public boolean getAndroidAutoAppStatus(int appType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(appType);
                    boolean _status = this.mRemote.transact(34, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAndroidAutoAppStatus(appType);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public MediaMetadata getMediaMetadata() throws RemoteException {
                MediaMetadata _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(35, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMediaMetadata();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = MediaMetadata.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public int getMediaProgress() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(36, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMediaProgress();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void setNativePhoneCallStatus(int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(status);
                    boolean _status = this.mRemote.transact(37, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNativePhoneCallStatus(status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void connectAndroidAuto(String devId, boolean isUserRequest) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    _data.writeInt(isUserRequest ? 1 : 0);
                    boolean _status = this.mRemote.transact(38, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().connectAndroidAuto(devId, isUserRequest);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void connectAndroidAutoByRequest(ConnectRequest request) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(39, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().connectAndroidAutoByRequest(request);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void disconnectAndroidAuto(String devId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    boolean _status = this.mRemote.transact(40, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disconnectAndroidAuto(devId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void deleteAndroidAutoDevice(String devId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    boolean _status = this.mRemote.transact(41, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteAndroidAutoDevice(devId);
                    } else {
                        _reply.readException();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public AndroidAutoDevice getAndroidAutoDevice(String devId) throws RemoteException {
                AndroidAutoDevice _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(devId);
                    boolean _status = this.mRemote.transact(42, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAndroidAutoDevice(devId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = AndroidAutoDevice.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public List<AndroidAutoDevice> getUsbAndroidAutoDeviceList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(43, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUsbAndroidAutoDeviceList();
                    }
                    _reply.readException();
                    List<AndroidAutoDevice> _result = _reply.createTypedArrayList(AndroidAutoDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public List<AndroidAutoDevice> getAndroidAutoDeviceList() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(44, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAndroidAutoDeviceList();
                    }
                    _reply.readException();
                    List<AndroidAutoDevice> _result = _reply.createTypedArrayList(AndroidAutoDevice.CREATOR);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.androidauto.sdk.aidl.IAndroidAutoService
            public void setClusterUiConfig(UiConfig uiConfig) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uiConfig != null) {
                        _data.writeInt(1);
                        uiConfig.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    boolean _status = this.mRemote.transact(45, _data, null, 1);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setClusterUiConfig(uiConfig);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAndroidAutoService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IAndroidAutoService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
