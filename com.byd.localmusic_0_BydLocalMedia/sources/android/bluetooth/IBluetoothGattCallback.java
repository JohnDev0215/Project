package android.bluetooth;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IBluetoothGattCallback extends IInterface {

    public static class Default implements IBluetoothGattCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.bluetooth.IBluetoothGattCallback
        public void onCharacteristicRead(String str, int i, int i2, byte[] bArr) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattCallback
        public void onCharacteristicWrite(String str, int i, int i2) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattCallback
        public void onClientConnectionState(int i, int i2, boolean z, String str) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattCallback
        public void onClientRegistered(int i, int i2) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattCallback
        public void onConfigureMTU(String str, int i, int i2) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattCallback
        public void onConnectionUpdated(String str, int i, int i2, int i3, int i4) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattCallback
        public void onDescriptorRead(String str, int i, int i2, byte[] bArr) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattCallback
        public void onDescriptorWrite(String str, int i, int i2) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattCallback
        public void onExecuteWrite(String str, int i) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattCallback
        public void onNotify(String str, int i, byte[] bArr) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattCallback
        public void onPhyRead(String str, int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattCallback
        public void onPhyUpdate(String str, int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattCallback
        public void onReadRemoteRssi(String str, int i, int i2) throws RemoteException {
        }

        @Override // android.bluetooth.IBluetoothGattCallback
        public void onSearchComplete(String str, List<BluetoothGattService> list, int i) throws RemoteException {
        }
    }

    void onCharacteristicRead(String str, int i, int i2, byte[] bArr) throws RemoteException;

    void onCharacteristicWrite(String str, int i, int i2) throws RemoteException;

    void onClientConnectionState(int i, int i2, boolean z, String str) throws RemoteException;

    void onClientRegistered(int i, int i2) throws RemoteException;

    void onConfigureMTU(String str, int i, int i2) throws RemoteException;

    void onConnectionUpdated(String str, int i, int i2, int i3, int i4) throws RemoteException;

    void onDescriptorRead(String str, int i, int i2, byte[] bArr) throws RemoteException;

    void onDescriptorWrite(String str, int i, int i2) throws RemoteException;

    void onExecuteWrite(String str, int i) throws RemoteException;

    void onNotify(String str, int i, byte[] bArr) throws RemoteException;

    void onPhyRead(String str, int i, int i2, int i3) throws RemoteException;

    void onPhyUpdate(String str, int i, int i2, int i3) throws RemoteException;

    void onReadRemoteRssi(String str, int i, int i2) throws RemoteException;

    void onSearchComplete(String str, List<BluetoothGattService> list, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IBluetoothGattCallback {
        private static final String DESCRIPTOR = "android.bluetooth.IBluetoothGattCallback";
        static final int TRANSACTION_onCharacteristicRead = 6;
        static final int TRANSACTION_onCharacteristicWrite = 7;
        static final int TRANSACTION_onClientConnectionState = 2;
        static final int TRANSACTION_onClientRegistered = 1;
        static final int TRANSACTION_onConfigureMTU = 13;
        static final int TRANSACTION_onConnectionUpdated = 14;
        static final int TRANSACTION_onDescriptorRead = 9;
        static final int TRANSACTION_onDescriptorWrite = 10;
        static final int TRANSACTION_onExecuteWrite = 8;
        static final int TRANSACTION_onNotify = 11;
        static final int TRANSACTION_onPhyRead = 4;
        static final int TRANSACTION_onPhyUpdate = 3;
        static final int TRANSACTION_onReadRemoteRssi = 12;
        static final int TRANSACTION_onSearchComplete = 5;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onClientRegistered";
                case 2:
                    return "onClientConnectionState";
                case 3:
                    return "onPhyUpdate";
                case 4:
                    return "onPhyRead";
                case 5:
                    return "onSearchComplete";
                case 6:
                    return "onCharacteristicRead";
                case 7:
                    return "onCharacteristicWrite";
                case 8:
                    return "onExecuteWrite";
                case 9:
                    return "onDescriptorRead";
                case 10:
                    return "onDescriptorWrite";
                case 11:
                    return "onNotify";
                case 12:
                    return "onReadRemoteRssi";
                case 13:
                    return "onConfigureMTU";
                case 14:
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

        public static IBluetoothGattCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBluetoothGattCallback)) {
                return (IBluetoothGattCallback) iInterfaceQueryLocalInterface;
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
                    onClientRegistered(parcel.readInt(), parcel.readInt());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onClientConnectionState(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0, parcel.readString());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPhyUpdate(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPhyRead(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onSearchComplete(parcel.readString(), parcel.createTypedArrayList(BluetoothGattService.CREATOR), parcel.readInt());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCharacteristicRead(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onCharacteristicWrite(parcel.readString(), parcel.readInt(), parcel.readInt());
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onExecuteWrite(parcel.readString(), parcel.readInt());
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDescriptorRead(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.createByteArray());
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onDescriptorWrite(parcel.readString(), parcel.readInt(), parcel.readInt());
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    onNotify(parcel.readString(), parcel.readInt(), parcel.createByteArray());
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    onReadRemoteRssi(parcel.readString(), parcel.readInt(), parcel.readInt());
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    onConfigureMTU(parcel.readString(), parcel.readInt(), parcel.readInt());
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    onConnectionUpdated(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBluetoothGattCallback {
            public static IBluetoothGattCallback sDefaultImpl;
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

            @Override // android.bluetooth.IBluetoothGattCallback
            public void onClientRegistered(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onClientRegistered(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattCallback
            public void onClientConnectionState(int i, int i2, boolean z, String str) throws RemoteException {
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
                    Stub.getDefaultImpl().onClientConnectionState(i, i2, z, str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattCallback
            public void onPhyUpdate(String str, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPhyUpdate(str, i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattCallback
            public void onPhyRead(String str, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPhyRead(str, i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattCallback
            public void onSearchComplete(String str, List<BluetoothGattService> list, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeTypedList(list);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onSearchComplete(str, list, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattCallback
            public void onCharacteristicRead(String str, int i, int i2, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeByteArray(bArr);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCharacteristicRead(str, i, i2, bArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattCallback
            public void onCharacteristicWrite(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCharacteristicWrite(str, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattCallback
            public void onExecuteWrite(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onExecuteWrite(str, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattCallback
            public void onDescriptorRead(String str, int i, int i2, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeByteArray(bArr);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDescriptorRead(str, i, i2, bArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattCallback
            public void onDescriptorWrite(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDescriptorWrite(str, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattCallback
            public void onNotify(String str, int i, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeByteArray(bArr);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onNotify(str, i, bArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattCallback
            public void onReadRemoteRssi(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(12, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onReadRemoteRssi(str, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattCallback
            public void onConfigureMTU(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(13, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConfigureMTU(str, i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.IBluetoothGattCallback
            public void onConnectionUpdated(String str, int i, int i2, int i3, int i4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                    if (this.mRemote.transact(14, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onConnectionUpdated(str, i, i2, i3, i4);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBluetoothGattCallback iBluetoothGattCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iBluetoothGattCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBluetoothGattCallback;
            return true;
        }

        public static IBluetoothGattCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
