package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IUidObserver extends IInterface {

    public static class Default implements IUidObserver {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.IUidObserver
        public void onUidActive(int i) throws RemoteException {
        }

        @Override // android.app.IUidObserver
        public void onUidCachedChanged(int i, boolean z) throws RemoteException {
        }

        @Override // android.app.IUidObserver
        public void onUidGone(int i, boolean z) throws RemoteException {
        }

        @Override // android.app.IUidObserver
        public void onUidIdle(int i, boolean z) throws RemoteException {
        }

        @Override // android.app.IUidObserver
        public void onUidStateChanged(int i, int i2, long j, int i3) throws RemoteException {
        }
    }

    void onUidActive(int i) throws RemoteException;

    void onUidCachedChanged(int i, boolean z) throws RemoteException;

    void onUidGone(int i, boolean z) throws RemoteException;

    void onUidIdle(int i, boolean z) throws RemoteException;

    void onUidStateChanged(int i, int i2, long j, int i3) throws RemoteException;

    public static abstract class Stub extends Binder implements IUidObserver {
        private static final String DESCRIPTOR = "android.app.IUidObserver";
        static final int TRANSACTION_onUidActive = 2;
        static final int TRANSACTION_onUidCachedChanged = 5;
        static final int TRANSACTION_onUidGone = 1;
        static final int TRANSACTION_onUidIdle = 3;
        static final int TRANSACTION_onUidStateChanged = 4;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onUidGone";
            }
            if (i == 2) {
                return "onUidActive";
            }
            if (i == 3) {
                return "onUidIdle";
            }
            if (i == 4) {
                return "onUidStateChanged";
            }
            if (i != 5) {
                return null;
            }
            return "onUidCachedChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUidObserver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IUidObserver)) {
                return (IUidObserver) iInterfaceQueryLocalInterface;
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
                onUidGone(parcel.readInt(), parcel.readInt() != 0);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onUidActive(parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onUidIdle(parcel.readInt(), parcel.readInt() != 0);
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onUidStateChanged(parcel.readInt(), parcel.readInt(), parcel.readLong(), parcel.readInt());
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                onUidCachedChanged(parcel.readInt(), parcel.readInt() != 0);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IUidObserver {
            public static IUidObserver sDefaultImpl;
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

            @Override // android.app.IUidObserver
            public void onUidGone(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUidGone(i, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUidObserver
            public void onUidActive(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUidActive(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUidObserver
            public void onUidIdle(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUidIdle(i, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUidObserver
            public void onUidStateChanged(int i, int i2, long j, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUidStateChanged(i, i2, j, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUidObserver
            public void onUidCachedChanged(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUidCachedChanged(i, z);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUidObserver iUidObserver) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iUidObserver == null) {
                return false;
            }
            Proxy.sDefaultImpl = iUidObserver;
            return true;
        }

        public static IUidObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
