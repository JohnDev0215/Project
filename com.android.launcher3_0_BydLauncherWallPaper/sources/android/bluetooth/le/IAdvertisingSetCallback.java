package android.bluetooth.le;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IAdvertisingSetCallback extends IInterface {

    public static class Default implements IAdvertisingSetCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onAdvertisingDataSet(int i, int i2) throws RemoteException {
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onAdvertisingEnabled(int i, boolean z, int i2) throws RemoteException {
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onAdvertisingParametersUpdated(int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onAdvertisingSetStarted(int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onAdvertisingSetStopped(int i) throws RemoteException {
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onOwnAddressRead(int i, int i2, String str) throws RemoteException {
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onPeriodicAdvertisingDataSet(int i, int i2) throws RemoteException {
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onPeriodicAdvertisingEnabled(int i, boolean z, int i2) throws RemoteException {
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onPeriodicAdvertisingParametersUpdated(int i, int i2) throws RemoteException {
        }

        @Override // android.bluetooth.le.IAdvertisingSetCallback
        public void onScanResponseDataSet(int i, int i2) throws RemoteException {
        }
    }

    void onAdvertisingDataSet(int i, int i2) throws RemoteException;

    void onAdvertisingEnabled(int i, boolean z, int i2) throws RemoteException;

    void onAdvertisingParametersUpdated(int i, int i2, int i3) throws RemoteException;

    void onAdvertisingSetStarted(int i, int i2, int i3) throws RemoteException;

    void onAdvertisingSetStopped(int i) throws RemoteException;

    void onOwnAddressRead(int i, int i2, String str) throws RemoteException;

    void onPeriodicAdvertisingDataSet(int i, int i2) throws RemoteException;

    void onPeriodicAdvertisingEnabled(int i, boolean z, int i2) throws RemoteException;

    void onPeriodicAdvertisingParametersUpdated(int i, int i2) throws RemoteException;

    void onScanResponseDataSet(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IAdvertisingSetCallback {
        private static final String DESCRIPTOR = "android.bluetooth.le.IAdvertisingSetCallback";
        static final int TRANSACTION_onAdvertisingDataSet = 5;
        static final int TRANSACTION_onAdvertisingEnabled = 4;
        static final int TRANSACTION_onAdvertisingParametersUpdated = 7;
        static final int TRANSACTION_onAdvertisingSetStarted = 1;
        static final int TRANSACTION_onAdvertisingSetStopped = 3;
        static final int TRANSACTION_onOwnAddressRead = 2;
        static final int TRANSACTION_onPeriodicAdvertisingDataSet = 9;
        static final int TRANSACTION_onPeriodicAdvertisingEnabled = 10;
        static final int TRANSACTION_onPeriodicAdvertisingParametersUpdated = 8;
        static final int TRANSACTION_onScanResponseDataSet = 6;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onAdvertisingSetStarted";
                case 2:
                    return "onOwnAddressRead";
                case 3:
                    return "onAdvertisingSetStopped";
                case 4:
                    return "onAdvertisingEnabled";
                case 5:
                    return "onAdvertisingDataSet";
                case 6:
                    return "onScanResponseDataSet";
                case 7:
                    return "onAdvertisingParametersUpdated";
                case 8:
                    return "onPeriodicAdvertisingParametersUpdated";
                case 9:
                    return "onPeriodicAdvertisingDataSet";
                case 10:
                    return "onPeriodicAdvertisingEnabled";
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

        public static IAdvertisingSetCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IAdvertisingSetCallback)) {
                return (IAdvertisingSetCallback) iInterfaceQueryLocalInterface;
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
                    onAdvertisingSetStarted(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onOwnAddressRead(parcel.readInt(), parcel.readInt(), parcel.readString());
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAdvertisingSetStopped(parcel.readInt());
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAdvertisingEnabled(parcel.readInt(), parcel.readInt() != 0, parcel.readInt());
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAdvertisingDataSet(parcel.readInt(), parcel.readInt());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onScanResponseDataSet(parcel.readInt(), parcel.readInt());
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    onAdvertisingParametersUpdated(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPeriodicAdvertisingParametersUpdated(parcel.readInt(), parcel.readInt());
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPeriodicAdvertisingDataSet(parcel.readInt(), parcel.readInt());
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPeriodicAdvertisingEnabled(parcel.readInt(), parcel.readInt() != 0, parcel.readInt());
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IAdvertisingSetCallback {
            public static IAdvertisingSetCallback sDefaultImpl;
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

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onAdvertisingSetStarted(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAdvertisingSetStarted(i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onOwnAddressRead(int i, int i2, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onOwnAddressRead(i, i2, str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onAdvertisingSetStopped(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAdvertisingSetStopped(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onAdvertisingEnabled(int i, boolean z, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAdvertisingEnabled(i, z, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onAdvertisingDataSet(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAdvertisingDataSet(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onScanResponseDataSet(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onScanResponseDataSet(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onAdvertisingParametersUpdated(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(7, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onAdvertisingParametersUpdated(i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onPeriodicAdvertisingParametersUpdated(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(8, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPeriodicAdvertisingParametersUpdated(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onPeriodicAdvertisingDataSet(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(9, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPeriodicAdvertisingDataSet(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.bluetooth.le.IAdvertisingSetCallback
            public void onPeriodicAdvertisingEnabled(int i, boolean z, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(10, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onPeriodicAdvertisingEnabled(i, z, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IAdvertisingSetCallback iAdvertisingSetCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iAdvertisingSetCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iAdvertisingSetCallback;
            return true;
        }

        public static IAdvertisingSetCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
