package android.net;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ISocketKeepaliveCallback extends IInterface {

    public static class Default implements ISocketKeepaliveCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.net.ISocketKeepaliveCallback
        public void onDataReceived() throws RemoteException {
        }

        @Override // android.net.ISocketKeepaliveCallback
        public void onError(int i) throws RemoteException {
        }

        @Override // android.net.ISocketKeepaliveCallback
        public void onStarted(int i) throws RemoteException {
        }

        @Override // android.net.ISocketKeepaliveCallback
        public void onStopped() throws RemoteException {
        }
    }

    void onDataReceived() throws RemoteException;

    void onError(int i) throws RemoteException;

    void onStarted(int i) throws RemoteException;

    void onStopped() throws RemoteException;

    public static abstract class Stub extends Binder implements ISocketKeepaliveCallback {
        private static final String DESCRIPTOR = "android.net.ISocketKeepaliveCallback";
        static final int TRANSACTION_onDataReceived = 4;
        static final int TRANSACTION_onError = 3;
        static final int TRANSACTION_onStarted = 1;
        static final int TRANSACTION_onStopped = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onStarted";
            }
            if (i == 2) {
                return "onStopped";
            }
            if (i == 3) {
                return "onError";
            }
            if (i != 4) {
                return null;
            }
            return "onDataReceived";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISocketKeepaliveCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISocketKeepaliveCallback)) {
                return (ISocketKeepaliveCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onStarted(parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onStopped();
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onError(parcel.readInt());
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onDataReceived();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ISocketKeepaliveCallback {
            public static ISocketKeepaliveCallback sDefaultImpl;
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

            @Override // android.net.ISocketKeepaliveCallback
            public void onStarted(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStarted(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ISocketKeepaliveCallback
            public void onStopped() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStopped();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ISocketKeepaliveCallback
            public void onError(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onError(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.net.ISocketKeepaliveCallback
            public void onDataReceived() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDataReceived();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISocketKeepaliveCallback iSocketKeepaliveCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSocketKeepaliveCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSocketKeepaliveCallback;
            return true;
        }

        public static ISocketKeepaliveCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
