package android.bluetooth;

import android.media.MediaMetrics;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IBluetoothHeadset extends IInterface {

    public static class Default implements IBluetoothHeadset {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public void clccResponse(int i, int i2, int i3, int i4, boolean z, String str, int i5) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public boolean connect(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public boolean connectAudio() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public boolean disconnect(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public boolean disconnectAudio() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public BluetoothDevice getActiveDevice() throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public boolean getAudioRouteAllowed() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public int getAudioState(BluetoothDevice bluetoothDevice) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public int getConnectionPolicy(BluetoothDevice bluetoothDevice) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public int getConnectionState(BluetoothDevice bluetoothDevice) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public int getPriority(BluetoothDevice bluetoothDevice) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public boolean isAudioConnected(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public boolean isAudioOn() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public boolean isInbandRingingEnabled() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public void phoneStateChanged(int i, int i2, int i3, String str, int i4, String str2) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public boolean sendVendorSpecificResultCode(BluetoothDevice bluetoothDevice, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public boolean setActiveDevice(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public void setAudioRouteAllowed(boolean z) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public boolean setConnectionPolicy(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public void setForceScoAudio(boolean z) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public boolean setPriority(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public boolean startScoUsingVirtualVoiceCall() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public boolean startVoiceRecognition(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public boolean stopScoUsingVirtualVoiceCall() throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadset
        public boolean stopVoiceRecognition(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }
    }

    void clccResponse(int i, int i2, int i3, int i4, boolean z, String str, int i5) throws RemoteException;

    boolean connect(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean connectAudio() throws RemoteException;

    boolean disconnect(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean disconnectAudio() throws RemoteException;

    BluetoothDevice getActiveDevice() throws RemoteException;

    boolean getAudioRouteAllowed() throws RemoteException;

    int getAudioState(BluetoothDevice bluetoothDevice) throws RemoteException;

    List<BluetoothDevice> getConnectedDevices() throws RemoteException;

    int getConnectionPolicy(BluetoothDevice bluetoothDevice) throws RemoteException;

    int getConnectionState(BluetoothDevice bluetoothDevice) throws RemoteException;

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr) throws RemoteException;

    int getPriority(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean isAudioConnected(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean isAudioOn() throws RemoteException;

    boolean isInbandRingingEnabled() throws RemoteException;

    void phoneStateChanged(int i, int i2, int i3, String str, int i4, String str2) throws RemoteException;

    boolean sendVendorSpecificResultCode(BluetoothDevice bluetoothDevice, String str, String str2) throws RemoteException;

    boolean setActiveDevice(BluetoothDevice bluetoothDevice) throws RemoteException;

    void setAudioRouteAllowed(boolean z) throws RemoteException;

    boolean setConnectionPolicy(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    void setForceScoAudio(boolean z) throws RemoteException;

    boolean setPriority(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    boolean startScoUsingVirtualVoiceCall() throws RemoteException;

    boolean startVoiceRecognition(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean stopScoUsingVirtualVoiceCall() throws RemoteException;

    boolean stopVoiceRecognition(BluetoothDevice bluetoothDevice) throws RemoteException;

    public static abstract class Stub extends Binder implements IBluetoothHeadset {
        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothHeadset";
        static final int TRANSACTION_clccResponse = 22;
        static final int TRANSACTION_connect = 8;
        static final int TRANSACTION_connectAudio = 14;
        static final int TRANSACTION_disconnect = 9;
        static final int TRANSACTION_disconnectAudio = 15;
        static final int TRANSACTION_getActiveDevice = 24;
        static final int TRANSACTION_getAudioRouteAllowed = 17;
        static final int TRANSACTION_getAudioState = 12;
        static final int TRANSACTION_getConnectedDevices = 1;
        static final int TRANSACTION_getConnectionPolicy = 11;
        static final int TRANSACTION_getConnectionState = 3;
        static final int TRANSACTION_getDevicesMatchingConnectionStates = 2;
        static final int TRANSACTION_getPriority = 27;
        static final int TRANSACTION_isAudioConnected = 6;
        static final int TRANSACTION_isAudioOn = 13;
        static final int TRANSACTION_isInbandRingingEnabled = 25;
        static final int TRANSACTION_phoneStateChanged = 21;
        static final int TRANSACTION_sendVendorSpecificResultCode = 7;
        static final int TRANSACTION_setActiveDevice = 23;
        static final int TRANSACTION_setAudioRouteAllowed = 16;
        static final int TRANSACTION_setConnectionPolicy = 10;
        static final int TRANSACTION_setForceScoAudio = 18;
        static final int TRANSACTION_setPriority = 26;
        static final int TRANSACTION_startScoUsingVirtualVoiceCall = 19;
        static final int TRANSACTION_startVoiceRecognition = 4;
        static final int TRANSACTION_stopScoUsingVirtualVoiceCall = 20;
        static final int TRANSACTION_stopVoiceRecognition = 5;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "getConnectedDevices";
                case 2:
                    return "getDevicesMatchingConnectionStates";
                case 3:
                    return "getConnectionState";
                case 4:
                    return "startVoiceRecognition";
                case 5:
                    return "stopVoiceRecognition";
                case 6:
                    return "isAudioConnected";
                case 7:
                    return "sendVendorSpecificResultCode";
                case 8:
                    return MediaMetrics.Value.CONNECT;
                case 9:
                    return MediaMetrics.Value.DISCONNECT;
                case 10:
                    return "setConnectionPolicy";
                case 11:
                    return "getConnectionPolicy";
                case 12:
                    return "getAudioState";
                case 13:
                    return "isAudioOn";
                case 14:
                    return "connectAudio";
                case 15:
                    return "disconnectAudio";
                case 16:
                    return "setAudioRouteAllowed";
                case 17:
                    return "getAudioRouteAllowed";
                case 18:
                    return "setForceScoAudio";
                case 19:
                    return "startScoUsingVirtualVoiceCall";
                case 20:
                    return "stopScoUsingVirtualVoiceCall";
                case 21:
                    return "phoneStateChanged";
                case 22:
                    return "clccResponse";
                case 23:
                    return "setActiveDevice";
                case 24:
                    return "getActiveDevice";
                case 25:
                    return "isInbandRingingEnabled";
                case 26:
                    return "setPriority";
                case 27:
                    return "getPriority";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBluetoothHeadset asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBluetoothHeadset)) {
                return (IBluetoothHeadset) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<BluetoothDevice> connectedDevices = getConnectedDevices();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(connectedDevices);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<BluetoothDevice> devicesMatchingConnectionStates = getDevicesMatchingConnectionStates(parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(devicesMatchingConnectionStates);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int connectionState = getConnectionState(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(connectionState);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zStartVoiceRecognition = startVoiceRecognition(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zStartVoiceRecognition ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zStopVoiceRecognition = stopVoiceRecognition(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zStopVoiceRecognition ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAudioConnected = isAudioConnected(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAudioConnected ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSendVendorSpecificResultCode = sendVendorSpecificResultCode(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zSendVendorSpecificResultCode ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zConnect = connect(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zConnect ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDisconnect = disconnect(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zDisconnect ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean connectionPolicy = setConnectionPolicy(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(connectionPolicy ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int connectionPolicy2 = getConnectionPolicy(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(connectionPolicy2);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    int audioState = getAudioState(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(audioState);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsAudioOn = isAudioOn();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsAudioOn ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zConnectAudio = connectAudio();
                    parcel2.writeNoException();
                    parcel2.writeInt(zConnectAudio ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDisconnectAudio = disconnectAudio();
                    parcel2.writeNoException();
                    parcel2.writeInt(zDisconnectAudio ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAudioRouteAllowed(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean audioRouteAllowed = getAudioRouteAllowed();
                    parcel2.writeNoException();
                    parcel2.writeInt(audioRouteAllowed ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    setForceScoAudio(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zStartScoUsingVirtualVoiceCall = startScoUsingVirtualVoiceCall();
                    parcel2.writeNoException();
                    parcel2.writeInt(zStartScoUsingVirtualVoiceCall ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zStopScoUsingVirtualVoiceCall = stopScoUsingVirtualVoiceCall();
                    parcel2.writeNoException();
                    parcel2.writeInt(zStopScoUsingVirtualVoiceCall ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    phoneStateChanged(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readString());
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    clccResponse(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean activeDevice = setActiveDevice(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(activeDevice ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    BluetoothDevice activeDevice2 = getActiveDevice();
                    parcel2.writeNoException();
                    if (activeDevice2 != null) {
                        parcel2.writeInt(1);
                        activeDevice2.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsInbandRingingEnabled = isInbandRingingEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsInbandRingingEnabled ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean priority = setPriority(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(priority ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    int priority2 = getPriority(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(priority2);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBluetoothHeadset {
            public static IBluetoothHeadset sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectedDevices();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(BluetoothDevice.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDevicesMatchingConnectionStates(iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(BluetoothDevice.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public int getConnectionState(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectionState(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public boolean startVoiceRecognition(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startVoiceRecognition(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public boolean stopVoiceRecognition(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopVoiceRecognition(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public boolean isAudioConnected(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAudioConnected(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public boolean sendVendorSpecificResultCode(BluetoothDevice bluetoothDevice, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendVendorSpecificResultCode(bluetoothDevice, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public boolean connect(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connect(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public boolean disconnect(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disconnect(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public boolean setConnectionPolicy(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setConnectionPolicy(bluetoothDevice, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public int getConnectionPolicy(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectionPolicy(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public int getAudioState(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAudioState(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public boolean isAudioOn() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAudioOn();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public boolean connectAudio() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connectAudio();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public boolean disconnectAudio() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disconnectAudio();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public void setAudioRouteAllowed(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAudioRouteAllowed(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public boolean getAudioRouteAllowed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAudioRouteAllowed();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public void setForceScoAudio(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setForceScoAudio(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public boolean startScoUsingVirtualVoiceCall() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startScoUsingVirtualVoiceCall();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public boolean stopScoUsingVirtualVoiceCall() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopScoUsingVirtualVoiceCall();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public void phoneStateChanged(int i, int i2, int i3, String str, int i4, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeString(str2);
                    if (this.mRemote.transact(21, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().phoneStateChanged(i, i2, i3, str, i4, str2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public void clccResponse(int i, int i2, int i3, int i4, boolean z, String str, int i5) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i5);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clccResponse(i, i2, i3, i4, z, str, i5);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public boolean setActiveDevice(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setActiveDevice(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public BluetoothDevice getActiveDevice() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveDevice();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public boolean isInbandRingingEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInbandRingingEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public boolean setPriority(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setPriority(bluetoothDevice, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadset
            public int getPriority(BluetoothDevice bluetoothDevice) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPriority(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothHeadset iBluetoothHeadset) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iBluetoothHeadset == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBluetoothHeadset;
            return true;
        }

        public static IBluetoothHeadset getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
