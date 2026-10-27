package android.bluetooth;

import android.media.MediaMetrics;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IBluetoothHeadsetClient extends IInterface {

    public static class Default implements IBluetoothHeadsetClient {
        @Override // android.bluetooth.IBluetoothHeadsetClient
        public boolean acceptCall(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public boolean connect(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public boolean connectAudio(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public BluetoothHeadsetClientCall dial(BluetoothDevice bluetoothDevice, String str) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public boolean disconnect(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public boolean disconnectAudio(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public boolean enterPrivateMode(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public boolean explicitCallTransfer(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public boolean getAudioRouteAllowed(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public int getAudioState(BluetoothDevice bluetoothDevice) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public int getConnectionPolicy(BluetoothDevice bluetoothDevice) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public int getConnectionState(BluetoothDevice bluetoothDevice) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public Bundle getCurrentAgEvents(BluetoothDevice bluetoothDevice) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public Bundle getCurrentAgFeatures(BluetoothDevice bluetoothDevice) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public List<BluetoothHeadsetClientCall> getCurrentCalls(BluetoothDevice bluetoothDevice) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public boolean getLastVoiceTagNumber(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public boolean holdCall(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public boolean rejectCall(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public boolean sendDTMF(BluetoothDevice bluetoothDevice, byte b) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public boolean sendVendorAtCommand(BluetoothDevice bluetoothDevice, int i, String str) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public void setAudioRouteAllowed(BluetoothDevice bluetoothDevice, boolean z) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public boolean setConnectionPolicy(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public boolean setConnectionPolicy2(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public boolean startVoiceRecognition(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public boolean stopVoiceRecognition(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothHeadsetClient
        public boolean terminateCall(BluetoothDevice bluetoothDevice, BluetoothHeadsetClientCall bluetoothHeadsetClientCall) throws RemoteException {
            return false;
        }
    }

    boolean acceptCall(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    boolean connect(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean connectAudio(BluetoothDevice bluetoothDevice) throws RemoteException;

    BluetoothHeadsetClientCall dial(BluetoothDevice bluetoothDevice, String str) throws RemoteException;

    boolean disconnect(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean disconnectAudio(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean enterPrivateMode(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    boolean explicitCallTransfer(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean getAudioRouteAllowed(BluetoothDevice bluetoothDevice) throws RemoteException;

    int getAudioState(BluetoothDevice bluetoothDevice) throws RemoteException;

    List<BluetoothDevice> getConnectedDevices() throws RemoteException;

    int getConnectionPolicy(BluetoothDevice bluetoothDevice) throws RemoteException;

    int getConnectionState(BluetoothDevice bluetoothDevice) throws RemoteException;

    Bundle getCurrentAgEvents(BluetoothDevice bluetoothDevice) throws RemoteException;

    Bundle getCurrentAgFeatures(BluetoothDevice bluetoothDevice) throws RemoteException;

    List<BluetoothHeadsetClientCall> getCurrentCalls(BluetoothDevice bluetoothDevice) throws RemoteException;

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr) throws RemoteException;

    boolean getLastVoiceTagNumber(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean holdCall(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean rejectCall(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean sendDTMF(BluetoothDevice bluetoothDevice, byte b) throws RemoteException;

    boolean sendVendorAtCommand(BluetoothDevice bluetoothDevice, int i, String str) throws RemoteException;

    void setAudioRouteAllowed(BluetoothDevice bluetoothDevice, boolean z) throws RemoteException;

    boolean setConnectionPolicy(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    boolean setConnectionPolicy2(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    boolean startVoiceRecognition(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean stopVoiceRecognition(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean terminateCall(BluetoothDevice bluetoothDevice, BluetoothHeadsetClientCall bluetoothHeadsetClientCall) throws RemoteException;

    public static abstract class Stub extends Binder implements IBluetoothHeadsetClient {
        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothHeadsetClient";
        static final int TRANSACTION_acceptCall = 13;
        static final int TRANSACTION_connect = 1;
        static final int TRANSACTION_connectAudio = 23;
        static final int TRANSACTION_dial = 19;
        static final int TRANSACTION_disconnect = 2;
        static final int TRANSACTION_disconnectAudio = 24;
        static final int TRANSACTION_enterPrivateMode = 17;
        static final int TRANSACTION_explicitCallTransfer = 18;
        static final int TRANSACTION_getAudioRouteAllowed = 26;
        static final int TRANSACTION_getAudioState = 22;
        static final int TRANSACTION_getConnectedDevices = 3;
        static final int TRANSACTION_getConnectionPolicy = 8;
        static final int TRANSACTION_getConnectionState = 5;
        static final int TRANSACTION_getCurrentAgEvents = 12;
        static final int TRANSACTION_getCurrentAgFeatures = 28;
        static final int TRANSACTION_getCurrentCalls = 11;
        static final int TRANSACTION_getDevicesMatchingConnectionStates = 4;
        static final int TRANSACTION_getLastVoiceTagNumber = 21;
        static final int TRANSACTION_holdCall = 14;
        static final int TRANSACTION_rejectCall = 15;
        static final int TRANSACTION_sendDTMF = 20;
        static final int TRANSACTION_sendVendorAtCommand = 27;
        static final int TRANSACTION_setAudioRouteAllowed = 25;
        static final int TRANSACTION_setConnectionPolicy = 6;
        static final int TRANSACTION_setConnectionPolicy2 = 7;
        static final int TRANSACTION_startVoiceRecognition = 9;
        static final int TRANSACTION_stopVoiceRecognition = 10;
        static final int TRANSACTION_terminateCall = 16;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return MediaMetrics.Value.CONNECT;
                case 2:
                    return MediaMetrics.Value.DISCONNECT;
                case 3:
                    return "getConnectedDevices";
                case 4:
                    return "getDevicesMatchingConnectionStates";
                case 5:
                    return "getConnectionState";
                case 6:
                    return "setConnectionPolicy";
                case 7:
                    return "setConnectionPolicy2";
                case 8:
                    return "getConnectionPolicy";
                case 9:
                    return "startVoiceRecognition";
                case 10:
                    return "stopVoiceRecognition";
                case 11:
                    return "getCurrentCalls";
                case 12:
                    return "getCurrentAgEvents";
                case 13:
                    return "acceptCall";
                case 14:
                    return "holdCall";
                case 15:
                    return "rejectCall";
                case 16:
                    return "terminateCall";
                case 17:
                    return "enterPrivateMode";
                case 18:
                    return "explicitCallTransfer";
                case 19:
                    return "dial";
                case 20:
                    return "sendDTMF";
                case 21:
                    return "getLastVoiceTagNumber";
                case 22:
                    return "getAudioState";
                case 23:
                    return "connectAudio";
                case 24:
                    return "disconnectAudio";
                case 25:
                    return "setAudioRouteAllowed";
                case 26:
                    return "getAudioRouteAllowed";
                case 27:
                    return "sendVendorAtCommand";
                case 28:
                    return "getCurrentAgFeatures";
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

        public static IBluetoothHeadsetClient asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBluetoothHeadsetClient)) {
                return (IBluetoothHeadsetClient) iInterfaceQueryLocalInterface;
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
                    boolean zConnect = connect(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zConnect ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDisconnect = disconnect(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zDisconnect ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<BluetoothDevice> connectedDevices = getConnectedDevices();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(connectedDevices);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<BluetoothDevice> devicesMatchingConnectionStates = getDevicesMatchingConnectionStates(parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(devicesMatchingConnectionStates);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int connectionState = getConnectionState(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(connectionState);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean connectionPolicy = setConnectionPolicy(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(connectionPolicy ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean connectionPolicy2 = setConnectionPolicy2(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(connectionPolicy2 ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int connectionPolicy3 = getConnectionPolicy(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(connectionPolicy3);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zStartVoiceRecognition = startVoiceRecognition(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zStartVoiceRecognition ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zStopVoiceRecognition = stopVoiceRecognition(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zStopVoiceRecognition ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<BluetoothHeadsetClientCall> currentCalls = getCurrentCalls(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeTypedList(currentCalls);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle currentAgEvents = getCurrentAgEvents(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (currentAgEvents != null) {
                        parcel2.writeInt(1);
                        currentAgEvents.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAcceptCall = acceptCall(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAcceptCall ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHoldCall = holdCall(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zHoldCall ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRejectCall = rejectCall(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zRejectCall ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zTerminateCall = terminateCall(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? BluetoothHeadsetClientCall.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zTerminateCall ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zEnterPrivateMode = enterPrivateMode(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zEnterPrivateMode ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zExplicitCallTransfer = explicitCallTransfer(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zExplicitCallTransfer ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    BluetoothHeadsetClientCall bluetoothHeadsetClientCallDial = dial(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    if (bluetoothHeadsetClientCallDial != null) {
                        parcel2.writeInt(1);
                        bluetoothHeadsetClientCallDial.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSendDTMF = sendDTMF(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readByte());
                    parcel2.writeNoException();
                    parcel2.writeInt(zSendDTMF ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean lastVoiceTagNumber = getLastVoiceTagNumber(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(lastVoiceTagNumber ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    int audioState = getAudioState(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(audioState);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zConnectAudio = connectAudio(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zConnectAudio ? 1 : 0);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDisconnectAudio = disconnectAudio(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zDisconnectAudio ? 1 : 0);
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAudioRouteAllowed(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean audioRouteAllowed = getAudioRouteAllowed(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(audioRouteAllowed ? 1 : 0);
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSendVendorAtCommand = sendVendorAtCommand(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zSendVendorAtCommand ? 1 : 0);
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bundle currentAgFeatures = getCurrentAgFeatures(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (currentAgFeatures != null) {
                        parcel2.writeInt(1);
                        currentAgFeatures.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBluetoothHeadsetClient {
            public static IBluetoothHeadsetClient sDefaultImpl;
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

            @Override // android.bluetooth.IBluetoothHeadsetClient
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
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().connect(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
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
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disconnect(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectedDevices();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(BluetoothDevice.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDevicesMatchingConnectionStates(iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(BluetoothDevice.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
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
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectionState(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
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
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setConnectionPolicy(bluetoothDevice, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public boolean setConnectionPolicy2(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
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
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setConnectionPolicy2(bluetoothDevice, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
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
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectionPolicy(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
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
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startVoiceRecognition(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
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
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopVoiceRecognition(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public List<BluetoothHeadsetClientCall> getCurrentCalls(BluetoothDevice bluetoothDevice) throws RemoteException {
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
                        return Stub.getDefaultImpl().getCurrentCalls(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(BluetoothHeadsetClientCall.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public Bundle getCurrentAgEvents(BluetoothDevice bluetoothDevice) throws RemoteException {
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
                        return Stub.getDefaultImpl().getCurrentAgEvents(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public boolean acceptCall(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
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
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().acceptCall(bluetoothDevice, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public boolean holdCall(BluetoothDevice bluetoothDevice) throws RemoteException {
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
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().holdCall(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public boolean rejectCall(BluetoothDevice bluetoothDevice) throws RemoteException {
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
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().rejectCall(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public boolean terminateCall(BluetoothDevice bluetoothDevice, BluetoothHeadsetClientCall bluetoothHeadsetClientCall) throws RemoteException {
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
                    if (bluetoothHeadsetClientCall != null) {
                        parcelObtain.writeInt(1);
                        bluetoothHeadsetClientCall.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().terminateCall(bluetoothDevice, bluetoothHeadsetClientCall);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public boolean enterPrivateMode(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
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
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enterPrivateMode(bluetoothDevice, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public boolean explicitCallTransfer(BluetoothDevice bluetoothDevice) throws RemoteException {
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
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().explicitCallTransfer(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public BluetoothHeadsetClientCall dial(BluetoothDevice bluetoothDevice, String str) throws RemoteException {
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
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().dial(bluetoothDevice, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? BluetoothHeadsetClientCall.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public boolean sendDTMF(BluetoothDevice bluetoothDevice, byte b) throws RemoteException {
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
                    parcelObtain.writeByte(b);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendDTMF(bluetoothDevice, b);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public boolean getLastVoiceTagNumber(BluetoothDevice bluetoothDevice) throws RemoteException {
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
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLastVoiceTagNumber(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
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
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAudioState(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public boolean connectAudio(BluetoothDevice bluetoothDevice) throws RemoteException {
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
                        return Stub.getDefaultImpl().connectAudio(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public boolean disconnectAudio(BluetoothDevice bluetoothDevice) throws RemoteException {
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
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disconnectAudio(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public void setAudioRouteAllowed(BluetoothDevice bluetoothDevice, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (bluetoothDevice != null) {
                        parcelObtain.writeInt(1);
                        bluetoothDevice.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAudioRouteAllowed(bluetoothDevice, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public boolean getAudioRouteAllowed(BluetoothDevice bluetoothDevice) throws RemoteException {
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
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAudioRouteAllowed(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public boolean sendVendorAtCommand(BluetoothDevice bluetoothDevice, int i, String str) throws RemoteException {
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
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().sendVendorAtCommand(bluetoothDevice, i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothHeadsetClient
            public Bundle getCurrentAgFeatures(BluetoothDevice bluetoothDevice) throws RemoteException {
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
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentAgFeatures(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothHeadsetClient iBluetoothHeadsetClient) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iBluetoothHeadsetClient == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBluetoothHeadsetClient;
            return true;
        }

        public static IBluetoothHeadsetClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
