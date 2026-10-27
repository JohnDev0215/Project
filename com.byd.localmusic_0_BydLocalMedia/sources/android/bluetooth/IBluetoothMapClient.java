package android.bluetooth;

import android.app.PendingIntent;
import android.media.MediaMetrics;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IBluetoothMapClient extends IInterface {

    public static class Default implements IBluetoothMapClient {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.bluetooth.IBluetoothMapClient
        public boolean connect(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothMapClient
        public boolean disconnect(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothMapClient
        public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothMapClient
        public int getConnectionPolicy(BluetoothDevice bluetoothDevice) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothMapClient
        public int getConnectionState(BluetoothDevice bluetoothDevice) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothMapClient
        public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr) throws RemoteException {
            return null;
        }

        @Override // android.bluetooth.IBluetoothMapClient
        public int getSupportedFeatures(BluetoothDevice bluetoothDevice) throws RemoteException {
            return 0;
        }

        @Override // android.bluetooth.IBluetoothMapClient
        public boolean getUnreadMessages(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothMapClient
        public boolean isConnected(BluetoothDevice bluetoothDevice) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothMapClient
        public boolean sendMessage(BluetoothDevice bluetoothDevice, Uri[] uriArr, String str, PendingIntent pendingIntent, PendingIntent pendingIntent2) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothMapClient
        public boolean setConnectionPolicy(BluetoothDevice bluetoothDevice, int i) throws RemoteException {
            return false;
        }

        @Override // android.bluetooth.IBluetoothMapClient
        public boolean setMessageStatus(BluetoothDevice bluetoothDevice, String str, int i) throws RemoteException {
            return false;
        }
    }

    boolean connect(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean disconnect(BluetoothDevice bluetoothDevice) throws RemoteException;

    List<BluetoothDevice> getConnectedDevices() throws RemoteException;

    int getConnectionPolicy(BluetoothDevice bluetoothDevice) throws RemoteException;

    int getConnectionState(BluetoothDevice bluetoothDevice) throws RemoteException;

    List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr) throws RemoteException;

    int getSupportedFeatures(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean getUnreadMessages(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean isConnected(BluetoothDevice bluetoothDevice) throws RemoteException;

    boolean sendMessage(BluetoothDevice bluetoothDevice, Uri[] uriArr, String str, PendingIntent pendingIntent, PendingIntent pendingIntent2) throws RemoteException;

    boolean setConnectionPolicy(BluetoothDevice bluetoothDevice, int i) throws RemoteException;

    boolean setMessageStatus(BluetoothDevice bluetoothDevice, String str, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IBluetoothMapClient {
        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothMapClient";
        static final int TRANSACTION_connect = 1;
        static final int TRANSACTION_disconnect = 2;
        static final int TRANSACTION_getConnectedDevices = 4;
        static final int TRANSACTION_getConnectionPolicy = 8;
        static final int TRANSACTION_getConnectionState = 6;
        static final int TRANSACTION_getDevicesMatchingConnectionStates = 5;
        static final int TRANSACTION_getSupportedFeatures = 11;
        static final int TRANSACTION_getUnreadMessages = 10;
        static final int TRANSACTION_isConnected = 3;
        static final int TRANSACTION_sendMessage = 9;
        static final int TRANSACTION_setConnectionPolicy = 7;
        static final int TRANSACTION_setMessageStatus = 12;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return MediaMetrics.Value.CONNECT;
                case 2:
                    return MediaMetrics.Value.DISCONNECT;
                case 3:
                    return "isConnected";
                case 4:
                    return "getConnectedDevices";
                case 5:
                    return "getDevicesMatchingConnectionStates";
                case 6:
                    return "getConnectionState";
                case 7:
                    return "setConnectionPolicy";
                case 8:
                    return "getConnectionPolicy";
                case 9:
                    return "sendMessage";
                case 10:
                    return "getUnreadMessages";
                case 11:
                    return "getSupportedFeatures";
                case 12:
                    return "setMessageStatus";
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

        public static IBluetoothMapClient asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBluetoothMapClient)) {
                return (IBluetoothMapClient) iInterfaceQueryLocalInterface;
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
                    boolean zIsConnected = isConnected(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsConnected ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<BluetoothDevice> connectedDevices = getConnectedDevices();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(connectedDevices);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<BluetoothDevice> devicesMatchingConnectionStates = getDevicesMatchingConnectionStates(parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(devicesMatchingConnectionStates);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int connectionState = getConnectionState(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(connectionState);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean connectionPolicy = setConnectionPolicy(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(connectionPolicy ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int connectionPolicy2 = getConnectionPolicy(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(connectionPolicy2);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSendMessage = sendMessage(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, (Uri[]) parcel.createTypedArray(Uri.CREATOR), parcel.readString(), parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? PendingIntent.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(zSendMessage ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean unreadMessages = getUnreadMessages(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(unreadMessages ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int supportedFeatures = getSupportedFeatures(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(supportedFeatures);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean messageStatus = setMessageStatus(parcel.readInt() != 0 ? BluetoothDevice.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(messageStatus ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBluetoothMapClient {
            public static IBluetoothMapClient sDefaultImpl;
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

            @Override // android.bluetooth.IBluetoothMapClient
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

            @Override // android.bluetooth.IBluetoothMapClient
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

            @Override // android.bluetooth.IBluetoothMapClient
            public boolean isConnected(BluetoothDevice bluetoothDevice) throws RemoteException {
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
                        return Stub.getDefaultImpl().isConnected(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMapClient
            public List<BluetoothDevice> getConnectedDevices() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectedDevices();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(BluetoothDevice.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMapClient
            public List<BluetoothDevice> getDevicesMatchingConnectionStates(int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDevicesMatchingConnectionStates(iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(BluetoothDevice.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMapClient
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
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectionState(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMapClient
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
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setConnectionPolicy(bluetoothDevice, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMapClient
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

            @Override // android.bluetooth.IBluetoothMapClient
            public boolean sendMessage(BluetoothDevice bluetoothDevice, Uri[] uriArr, String str, PendingIntent pendingIntent, PendingIntent pendingIntent2) throws Throwable {
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
                    parcelObtain.writeTypedArray(uriArr, 0);
                    parcelObtain.writeString(str);
                    if (pendingIntent != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (pendingIntent2 != null) {
                        parcelObtain.writeInt(1);
                        pendingIntent2.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        boolean zSendMessage = Stub.getDefaultImpl().sendMessage(bluetoothDevice, uriArr, str, pendingIntent, pendingIntent2);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return zSendMessage;
                    }
                    parcelObtain2.readException();
                    boolean z = parcelObtain2.readInt() != 0;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.bluetooth.IBluetoothMapClient
            public boolean getUnreadMessages(BluetoothDevice bluetoothDevice) throws RemoteException {
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
                        return Stub.getDefaultImpl().getUnreadMessages(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMapClient
            public int getSupportedFeatures(BluetoothDevice bluetoothDevice) throws RemoteException {
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
                        return Stub.getDefaultImpl().getSupportedFeatures(bluetoothDevice);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothMapClient
            public boolean setMessageStatus(BluetoothDevice bluetoothDevice, String str, int i) throws RemoteException {
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
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setMessageStatus(bluetoothDevice, str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothMapClient iBluetoothMapClient) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iBluetoothMapClient == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBluetoothMapClient;
            return true;
        }

        public static IBluetoothMapClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
