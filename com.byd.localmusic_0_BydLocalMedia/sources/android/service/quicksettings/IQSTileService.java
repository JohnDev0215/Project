package android.service.quicksettings;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IQSTileService extends IInterface {

    public static class Default implements IQSTileService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.quicksettings.IQSTileService
        public void onClick(IBinder iBinder) throws RemoteException {
        }

        @Override // android.service.quicksettings.IQSTileService
        public void onStartListening() throws RemoteException {
        }

        @Override // android.service.quicksettings.IQSTileService
        public void onStopListening() throws RemoteException {
        }

        @Override // android.service.quicksettings.IQSTileService
        public void onTileAdded() throws RemoteException {
        }

        @Override // android.service.quicksettings.IQSTileService
        public void onTileRemoved() throws RemoteException {
        }

        @Override // android.service.quicksettings.IQSTileService
        public void onUnlockComplete() throws RemoteException {
        }
    }

    void onClick(IBinder iBinder) throws RemoteException;

    void onStartListening() throws RemoteException;

    void onStopListening() throws RemoteException;

    void onTileAdded() throws RemoteException;

    void onTileRemoved() throws RemoteException;

    void onUnlockComplete() throws RemoteException;

    public static abstract class Stub extends Binder implements IQSTileService {
        private static final String DESCRIPTOR = "android.service.quicksettings.IQSTileService";
        static final int TRANSACTION_onClick = 5;
        static final int TRANSACTION_onStartListening = 3;
        static final int TRANSACTION_onStopListening = 4;
        static final int TRANSACTION_onTileAdded = 1;
        static final int TRANSACTION_onTileRemoved = 2;
        static final int TRANSACTION_onUnlockComplete = 6;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "onTileAdded";
                case 2:
                    return "onTileRemoved";
                case 3:
                    return "onStartListening";
                case 4:
                    return "onStopListening";
                case 5:
                    return "onClick";
                case 6:
                    return "onUnlockComplete";
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

        public static IQSTileService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IQSTileService)) {
                return (IQSTileService) iInterfaceQueryLocalInterface;
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
                    onTileAdded();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    onTileRemoved();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    onStartListening();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    onStopListening();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    onClick(parcel.readStrongBinder());
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    onUnlockComplete();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IQSTileService {
            public static IQSTileService sDefaultImpl;
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

            @Override // android.service.quicksettings.IQSTileService
            public void onTileAdded() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTileAdded();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.quicksettings.IQSTileService
            public void onTileRemoved() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTileRemoved();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.quicksettings.IQSTileService
            public void onStartListening() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStartListening();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.quicksettings.IQSTileService
            public void onStopListening() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStopListening();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.quicksettings.IQSTileService
            public void onClick(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onClick(iBinder);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.quicksettings.IQSTileService
            public void onUnlockComplete() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUnlockComplete();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IQSTileService iQSTileService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iQSTileService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iQSTileService;
            return true;
        }

        public static IQSTileService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
