package android.app.backup;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IRestoreObserver extends IInterface {

    public static class Default implements IRestoreObserver {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.backup.IRestoreObserver
        public void onUpdate(int i, String str) throws RemoteException {
        }

        @Override // android.app.backup.IRestoreObserver
        public void restoreFinished(int i) throws RemoteException {
        }

        @Override // android.app.backup.IRestoreObserver
        public void restoreSetsAvailable(RestoreSet[] restoreSetArr) throws RemoteException {
        }

        @Override // android.app.backup.IRestoreObserver
        public void restoreStarting(int i) throws RemoteException {
        }
    }

    void onUpdate(int i, String str) throws RemoteException;

    void restoreFinished(int i) throws RemoteException;

    void restoreSetsAvailable(RestoreSet[] restoreSetArr) throws RemoteException;

    void restoreStarting(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IRestoreObserver {
        private static final String DESCRIPTOR = "android.app.backup.IRestoreObserver";
        static final int TRANSACTION_onUpdate = 3;
        static final int TRANSACTION_restoreFinished = 4;
        static final int TRANSACTION_restoreSetsAvailable = 1;
        static final int TRANSACTION_restoreStarting = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "restoreSetsAvailable";
            }
            if (i == 2) {
                return "restoreStarting";
            }
            if (i == 3) {
                return "onUpdate";
            }
            if (i != 4) {
                return null;
            }
            return "restoreFinished";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRestoreObserver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IRestoreObserver)) {
                return (IRestoreObserver) iInterfaceQueryLocalInterface;
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
                restoreSetsAvailable((RestoreSet[]) parcel.createTypedArray(RestoreSet.CREATOR));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                restoreStarting(parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onUpdate(parcel.readInt(), parcel.readString());
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                restoreFinished(parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IRestoreObserver {
            public static IRestoreObserver sDefaultImpl;
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

            @Override // android.app.backup.IRestoreObserver
            public void restoreSetsAvailable(RestoreSet[] restoreSetArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(restoreSetArr, 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().restoreSetsAvailable(restoreSetArr);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IRestoreObserver
            public void restoreStarting(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().restoreStarting(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IRestoreObserver
            public void onUpdate(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUpdate(i, str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.backup.IRestoreObserver
            public void restoreFinished(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().restoreFinished(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRestoreObserver iRestoreObserver) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iRestoreObserver == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRestoreObserver;
            return true;
        }

        public static IRestoreObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
