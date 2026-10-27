package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IProcessObserver extends IInterface {

    public static class Default implements IProcessObserver {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.IProcessObserver
        public void onForegroundActivitiesChanged(int i, int i2, boolean z) throws RemoteException {
        }

        @Override // android.app.IProcessObserver
        public void onForegroundServicesChanged(int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.app.IProcessObserver
        public void onProcessDied(int i, int i2) throws RemoteException {
        }
    }

    void onForegroundActivitiesChanged(int i, int i2, boolean z) throws RemoteException;

    void onForegroundServicesChanged(int i, int i2, int i3) throws RemoteException;

    void onProcessDied(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IProcessObserver {
        private static final String DESCRIPTOR = "android.app.IProcessObserver";
        static final int TRANSACTION_onForegroundActivitiesChanged = 1;
        static final int TRANSACTION_onForegroundServicesChanged = 2;
        static final int TRANSACTION_onProcessDied = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onForegroundActivitiesChanged";
            }
            if (i == 2) {
                return "onForegroundServicesChanged";
            }
            if (i != 3) {
                return null;
            }
            return "onProcessDied";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IProcessObserver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IProcessObserver)) {
                return (IProcessObserver) iInterfaceQueryLocalInterface;
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
                onForegroundActivitiesChanged(parcel.readInt(), parcel.readInt(), parcel.readInt() != 0);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onForegroundServicesChanged(parcel.readInt(), parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onProcessDied(parcel.readInt(), parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IProcessObserver {
            public static IProcessObserver sDefaultImpl;
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

            @Override // android.app.IProcessObserver
            public void onForegroundActivitiesChanged(int i, int i2, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onForegroundActivitiesChanged(i, i2, z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IProcessObserver
            public void onForegroundServicesChanged(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onForegroundServicesChanged(i, i2, i3);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IProcessObserver
            public void onProcessDied(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onProcessDied(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IProcessObserver iProcessObserver) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iProcessObserver == null) {
                return false;
            }
            Proxy.sDefaultImpl = iProcessObserver;
            return true;
        }

        public static IProcessObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
