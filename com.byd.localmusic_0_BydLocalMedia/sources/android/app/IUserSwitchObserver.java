package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IUserSwitchObserver extends IInterface {

    public static class Default implements IUserSwitchObserver {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.IUserSwitchObserver
        public void onForegroundProfileSwitch(int i) throws RemoteException {
        }

        @Override // android.app.IUserSwitchObserver
        public void onLockedBootComplete(int i) throws RemoteException {
        }

        @Override // android.app.IUserSwitchObserver
        public void onUserSwitchComplete(int i) throws RemoteException {
        }

        @Override // android.app.IUserSwitchObserver
        public void onUserSwitching(int i, IRemoteCallback iRemoteCallback) throws RemoteException {
        }
    }

    void onForegroundProfileSwitch(int i) throws RemoteException;

    void onLockedBootComplete(int i) throws RemoteException;

    void onUserSwitchComplete(int i) throws RemoteException;

    void onUserSwitching(int i, IRemoteCallback iRemoteCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IUserSwitchObserver {
        private static final String DESCRIPTOR = "android.app.IUserSwitchObserver";
        static final int TRANSACTION_onForegroundProfileSwitch = 3;
        static final int TRANSACTION_onLockedBootComplete = 4;
        static final int TRANSACTION_onUserSwitchComplete = 2;
        static final int TRANSACTION_onUserSwitching = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onUserSwitching";
            }
            if (i == 2) {
                return "onUserSwitchComplete";
            }
            if (i == 3) {
                return "onForegroundProfileSwitch";
            }
            if (i != 4) {
                return null;
            }
            return "onLockedBootComplete";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUserSwitchObserver asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IUserSwitchObserver)) {
                return (IUserSwitchObserver) iInterfaceQueryLocalInterface;
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
                onUserSwitching(parcel.readInt(), IRemoteCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onUserSwitchComplete(parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onForegroundProfileSwitch(parcel.readInt());
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onLockedBootComplete(parcel.readInt());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IUserSwitchObserver {
            public static IUserSwitchObserver sDefaultImpl;
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

            @Override // android.app.IUserSwitchObserver
            public void onUserSwitching(int i, IRemoteCallback iRemoteCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iRemoteCallback != null ? iRemoteCallback.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUserSwitching(i, iRemoteCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUserSwitchObserver
            public void onUserSwitchComplete(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onUserSwitchComplete(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUserSwitchObserver
            public void onForegroundProfileSwitch(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onForegroundProfileSwitch(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.IUserSwitchObserver
            public void onLockedBootComplete(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onLockedBootComplete(i);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUserSwitchObserver iUserSwitchObserver) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iUserSwitchObserver == null) {
                return false;
            }
            Proxy.sDefaultImpl = iUserSwitchObserver;
            return true;
        }

        public static IUserSwitchObserver getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
