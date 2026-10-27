package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IBluetoothGattServerCallback extends IInterface {

    public static class Default implements IBluetoothGattServerCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.bluetooth.IBluetoothGattServerCallback
        public void onCharacteristicReadRequest(String str, int i, int i2, boolean z, int i3) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattServerCallback
        public void onCharacteristicWriteRequest(String str, int i, int i2, int i3, boolean z, boolean z2, int i4, byte[] bArr) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattServerCallback
        public void onConnectionUpdated(String str, int i, int i2, int i3, int i4) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattServerCallback
        public void onDescriptorReadRequest(String str, int i, int i2, boolean z, int i3) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattServerCallback
        public void onDescriptorWriteRequest(String str, int i, int i2, int i3, boolean z, boolean z2, int i4, byte[] bArr) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattServerCallback
        public void onExecuteWrite(String str, int i, boolean z) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattServerCallback
        public void onMtuChanged(String str, int i) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattServerCallback
        public void onNotificationSent(String str, int i) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattServerCallback
        public void onPhyRead(String str, int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattServerCallback
        public void onPhyUpdate(String str, int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattServerCallback
        public void onServerConnectionState(int i, int i2, boolean z, String str) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattServerCallback
        public void onServerRegistered(int i, int i2) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattServerCallback
        public void onServiceAdded(int i, BluetoothGattService bluetoothGattService) throws RemoteException {
        }
    }

    void onCharacteristicReadRequest(String str, int i, int i2, boolean z, int i3) throws RemoteException;

    void onCharacteristicWriteRequest(String str, int i, int i2, int i3, boolean z, boolean z2, int i4, byte[] bArr) throws RemoteException;

    void onConnectionUpdated(String str, int i, int i2, int i3, int i4) throws RemoteException;

    void onDescriptorReadRequest(String str, int i, int i2, boolean z, int i3) throws RemoteException;

    void onDescriptorWriteRequest(String str, int i, int i2, int i3, boolean z, boolean z2, int i4, byte[] bArr) throws RemoteException;

    void onExecuteWrite(String str, int i, boolean z) throws RemoteException;

    void onMtuChanged(String str, int i) throws RemoteException;

    void onNotificationSent(String str, int i) throws RemoteException;

    void onPhyRead(String str, int i, int i2, int i3) throws RemoteException;

    void onPhyUpdate(String str, int i, int i2, int i3) throws RemoteException;

    void onServerConnectionState(int i, int i2, boolean z, String str) throws RemoteException;

    void onServerRegistered(int i, int i2) throws RemoteException;

    void onServiceAdded(int i, BluetoothGattService bluetoothGattService) throws RemoteException;

    public static abstract class Stub extends Binder implements IBluetoothGattServerCallback {
        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothGattServerCallback";
        static final int TRANSACTION_onCharacteristicReadRequest = 4;
        static final int TRANSACTION_onCharacteristicWriteRequest = 6;
        static final int TRANSACTION_onConnectionUpdated = 13;
        static final int TRANSACTION_onDescriptorReadRequest = 5;
        static final int TRANSACTION_onDescriptorWriteRequest = 7;
        static final int TRANSACTION_onExecuteWrite = 8;
        static final int TRANSACTION_onMtuChanged = 10;
        static final int TRANSACTION_onNotificationSent = 9;
        static final int TRANSACTION_onPhyRead = 12;
        static final int TRANSACTION_onPhyUpdate = 11;
        static final int TRANSACTION_onServerConnectionState = 2;
        static final int TRANSACTION_onServerRegistered = 1;
        static final int TRANSACTION_onServiceAdded = 3;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onServerRegistered";
                case 2:
                    return "onServerConnectionState";
                case 3:
                    return "onServiceAdded";
                case 4:
                    return "onCharacteristicReadRequest";
                case 5:
                    return "onDescriptorReadRequest";
                case 6:
                    return "onCharacteristicWriteRequest";
                case 7:
                    return "onDescriptorWriteRequest";
                case 8:
                    return "onExecuteWrite";
                case 9:
                    return "onNotificationSent";
                case 10:
                    return "onMtuChanged";
                case 11:
                    return "onPhyUpdate";
                case 12:
                    return "onPhyRead";
                case 13:
                    return "onConnectionUpdated";
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

        public static IBluetoothGattServerCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBluetoothGattServerCallback)) {
                return (IBluetoothGattServerCallback) iInterfaceQueryLocalInterface;
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
                    onServerRegistered(parcel.readInt(), parcel.readInt());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onServerConnectionState(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, parcel.readString());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onServiceAdded(parcel.readInt(), parcel.readInt() != 0 ? BluetoothGattService.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCharacteristicReadRequest(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, parcel.readInt());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDescriptorReadRequest(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, parcel.readInt());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCharacteristicWriteRequest(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt(), parcel.createByteArray());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDescriptorWriteRequest(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt(), parcel.createByteArray());
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onExecuteWrite(parcel.readString(), parcel.readInt(), parcel.readInt() != 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotificationSent(parcel.readString(), parcel.readInt());
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onMtuChanged(parcel.readString(), parcel.readInt());
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPhyUpdate(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPhyRead(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    onConnectionUpdated(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBluetoothGattServerCallback {
            public static IBluetoothGattServerCallback sDefaultImpl;
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

            @Override // android.bluetooth.IBluetoothGattServerCallback
            public void onServerRegistered(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onServerRegistered(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattServerCallback
            public void onServerConnectionState(int i, int i2, boolean z, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onServerConnectionState(i, i2, z, str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattServerCallback
            public void onServiceAdded(int i, BluetoothGattService bluetoothGattService) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (bluetoothGattService != null) {
                        parcelObtain.writeInt(1);
                        bluetoothGattService.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onServiceAdded(i, bluetoothGattService);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattServerCallback
            public void onCharacteristicReadRequest(String str, int i, int i2, boolean z, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCharacteristicReadRequest(str, i, i2, z, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattServerCallback
            public void onDescriptorReadRequest(String str, int i, int i2, boolean z, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDescriptorReadRequest(str, i, i2, z, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattServerCallback
            public void onCharacteristicWriteRequest(String str, int i, int i2, int i3, boolean z, boolean z2, int i4, byte[] bArr) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeByteArray(bArr);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        parcelObtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onCharacteristicWriteRequest(str, i, i2, i3, z, z2, i4, bArr);
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.bluetooth.IBluetoothGattServerCallback
            public void onDescriptorWriteRequest(String str, int i, int i2, int i3, boolean z, boolean z2, int i4, byte[] bArr) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeByteArray(bArr);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        parcelObtain.recycle();
                    } else {
                        Stub.getDefaultImpl().onDescriptorWriteRequest(str, i, i2, i3, z, z2, i4, bArr);
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.bluetooth.IBluetoothGattServerCallback
            public void onExecuteWrite(String str, int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onExecuteWrite(str, i, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattServerCallback
            public void onNotificationSent(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNotificationSent(str, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattServerCallback
            public void onMtuChanged(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onMtuChanged(str, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattServerCallback
            public void onPhyUpdate(String str, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPhyUpdate(str, i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattServerCallback
            public void onPhyRead(String str, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPhyRead(str, i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattServerCallback
            public void onConnectionUpdated(String str, int i, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConnectionUpdated(str, i, i2, i3, i4);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothGattServerCallback iBluetoothGattServerCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iBluetoothGattServerCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBluetoothGattServerCallback;
            return true;
        }

        public static IBluetoothGattServerCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
