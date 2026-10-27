package android.telephony;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.INetworkServiceCallback;

/* JADX INFO: loaded from: classes3.dex */
public interface INetworkService extends IInterface {

    public static class Default implements INetworkService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.telephony.INetworkService
        public void createNetworkServiceProvider(int i) throws RemoteException {
        }

        @Override // android.telephony.INetworkService
        public void registerForNetworkRegistrationInfoChanged(int i, INetworkServiceCallback iNetworkServiceCallback) throws RemoteException {
        }

        @Override // android.telephony.INetworkService
        public void removeNetworkServiceProvider(int i) throws RemoteException {
        }

        @Override // android.telephony.INetworkService
        public void requestNetworkRegistrationInfo(int i, int i2, INetworkServiceCallback iNetworkServiceCallback) throws RemoteException {
        }

        @Override // android.telephony.INetworkService
        public void unregisterForNetworkRegistrationInfoChanged(int i, INetworkServiceCallback iNetworkServiceCallback) throws RemoteException {
        }
    }

    void createNetworkServiceProvider(int i) throws RemoteException;

    void registerForNetworkRegistrationInfoChanged(int i, INetworkServiceCallback iNetworkServiceCallback) throws RemoteException;

    void removeNetworkServiceProvider(int i) throws RemoteException;

    void requestNetworkRegistrationInfo(int i, int i2, INetworkServiceCallback iNetworkServiceCallback) throws RemoteException;

    void unregisterForNetworkRegistrationInfoChanged(int i, INetworkServiceCallback iNetworkServiceCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements INetworkService {
        private static final String DESCRIPTOR = "android.telephony.INetworkService";
        static final int TRANSACTION_createNetworkServiceProvider = 1;
        static final int TRANSACTION_registerForNetworkRegistrationInfoChanged = 4;
        static final int TRANSACTION_removeNetworkServiceProvider = 2;
        static final int TRANSACTION_requestNetworkRegistrationInfo = 3;
        static final int TRANSACTION_unregisterForNetworkRegistrationInfoChanged = 5;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "createNetworkServiceProvider";
            }
            if (i == 2) {
                return "removeNetworkServiceProvider";
            }
            if (i == 3) {
                return "requestNetworkRegistrationInfo";
            }
            if (i == 4) {
                return "registerForNetworkRegistrationInfoChanged";
            }
            if (i != 5) {
                return null;
            }
            return "unregisterForNetworkRegistrationInfoChanged";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INetworkService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof INetworkService)) {
                return (INetworkService) iInterfaceQueryLocalInterface;
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
                createNetworkServiceProvider(parcel.readInt());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                removeNetworkServiceProvider(parcel.readInt());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                requestNetworkRegistrationInfo(parcel.readInt(), parcel.readInt(), INetworkServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                registerForNetworkRegistrationInfoChanged(parcel.readInt(), INetworkServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                unregisterForNetworkRegistrationInfoChanged(parcel.readInt(), INetworkServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements INetworkService {
            public static INetworkService sDefaultImpl;
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

            @Override // android.telephony.INetworkService
            public void createNetworkServiceProvider(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().createNetworkServiceProvider(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.INetworkService
            public void removeNetworkServiceProvider(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().removeNetworkServiceProvider(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.INetworkService
            public void requestNetworkRegistrationInfo(int i, int i2, INetworkServiceCallback iNetworkServiceCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iNetworkServiceCallback != null ? iNetworkServiceCallback.asBinder() : null);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().requestNetworkRegistrationInfo(i, i2, iNetworkServiceCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.INetworkService
            public void registerForNetworkRegistrationInfoChanged(int i, INetworkServiceCallback iNetworkServiceCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iNetworkServiceCallback != null ? iNetworkServiceCallback.asBinder() : null);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerForNetworkRegistrationInfoChanged(i, iNetworkServiceCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.telephony.INetworkService
            public void unregisterForNetworkRegistrationInfoChanged(int i, INetworkServiceCallback iNetworkServiceCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iNetworkServiceCallback != null ? iNetworkServiceCallback.asBinder() : null);
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregisterForNetworkRegistrationInfoChanged(i, iNetworkServiceCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INetworkService iNetworkService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iNetworkService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iNetworkService;
            return true;
        }

        public static INetworkService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
