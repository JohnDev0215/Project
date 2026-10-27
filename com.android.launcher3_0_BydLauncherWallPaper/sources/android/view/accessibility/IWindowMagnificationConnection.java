package android.view.accessibility;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.accessibility.IWindowMagnificationConnectionCallback;

/* JADX INFO: loaded from: classes3.dex */
public interface IWindowMagnificationConnection extends IInterface {

    public static class Default implements IWindowMagnificationConnection {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.accessibility.IWindowMagnificationConnection
        public void disableWindowMagnification(int i) throws RemoteException {
        }

        @Override // android.view.accessibility.IWindowMagnificationConnection
        public void enableWindowMagnification(int i, float f, float f2, float f3) throws RemoteException {
        }

        @Override // android.view.accessibility.IWindowMagnificationConnection
        public void moveWindowMagnifier(int i, float f, float f2) throws RemoteException {
        }

        @Override // android.view.accessibility.IWindowMagnificationConnection
        public void setConnectionCallback(IWindowMagnificationConnectionCallback iWindowMagnificationConnectionCallback) throws RemoteException {
        }

        @Override // android.view.accessibility.IWindowMagnificationConnection
        public void setScale(int i, float f) throws RemoteException {
        }
    }

    void disableWindowMagnification(int i) throws RemoteException;

    void enableWindowMagnification(int i, float f, float f2, float f3) throws RemoteException;

    void moveWindowMagnifier(int i, float f, float f2) throws RemoteException;

    void setConnectionCallback(IWindowMagnificationConnectionCallback iWindowMagnificationConnectionCallback) throws RemoteException;

    void setScale(int i, float f) throws RemoteException;

    public static abstract class Stub extends Binder implements IWindowMagnificationConnection {
        private static final String DESCRIPTOR = "android.view.accessibility.IWindowMagnificationConnection";
        static final int TRANSACTION_disableWindowMagnification = 3;
        static final int TRANSACTION_enableWindowMagnification = 1;
        static final int TRANSACTION_moveWindowMagnifier = 4;
        static final int TRANSACTION_setConnectionCallback = 5;
        static final int TRANSACTION_setScale = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "enableWindowMagnification";
            }
            if (i == 2) {
                return "setScale";
            }
            if (i == 3) {
                return "disableWindowMagnification";
            }
            if (i == 4) {
                return "moveWindowMagnifier";
            }
            if (i != 5) {
                return null;
            }
            return "setConnectionCallback";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWindowMagnificationConnection asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IWindowMagnificationConnection)) {
                return (IWindowMagnificationConnection) iInterfaceQueryLocalInterface;
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
                enableWindowMagnification(parcel.readInt(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                setScale(parcel.readInt(), parcel.readFloat());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                disableWindowMagnification(parcel.readInt());
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                moveWindowMagnifier(parcel.readInt(), parcel.readFloat(), parcel.readFloat());
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                setConnectionCallback(IWindowMagnificationConnectionCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IWindowMagnificationConnection {
            public static IWindowMagnificationConnection sDefaultImpl;
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

            @Override // android.view.accessibility.IWindowMagnificationConnection
            public void enableWindowMagnification(int i, float f, float f2, float f3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeFloat(f);
                    parcelObtain.writeFloat(f2);
                    parcelObtain.writeFloat(f3);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().enableWindowMagnification(i, f, f2, f3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.accessibility.IWindowMagnificationConnection
            public void setScale(int i, float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeFloat(f);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setScale(i, f);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.accessibility.IWindowMagnificationConnection
            public void disableWindowMagnification(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().disableWindowMagnification(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.accessibility.IWindowMagnificationConnection
            public void moveWindowMagnifier(int i, float f, float f2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeFloat(f);
                    parcelObtain.writeFloat(f2);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().moveWindowMagnifier(i, f, f2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.accessibility.IWindowMagnificationConnection
            public void setConnectionCallback(IWindowMagnificationConnectionCallback iWindowMagnificationConnectionCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindowMagnificationConnectionCallback != null ? iWindowMagnificationConnectionCallback.asBinder() : null);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setConnectionCallback(iWindowMagnificationConnectionCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWindowMagnificationConnection iWindowMagnificationConnection) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iWindowMagnificationConnection == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWindowMagnificationConnection;
            return true;
        }

        public static IWindowMagnificationConnection getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
