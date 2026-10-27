package android.service.quickaccesswallet;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks;

/* JADX INFO: loaded from: classes3.dex */
public interface IQuickAccessWalletService extends IInterface {

    public static class Default implements IQuickAccessWalletService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void onWalletCardSelected(SelectWalletCardRequest selectWalletCardRequest) throws RemoteException {
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void onWalletCardsRequested(GetWalletCardsRequest getWalletCardsRequest, IQuickAccessWalletServiceCallbacks iQuickAccessWalletServiceCallbacks) throws RemoteException {
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void onWalletDismissed() throws RemoteException {
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void registerWalletServiceEventListener(WalletServiceEventListenerRequest walletServiceEventListenerRequest, IQuickAccessWalletServiceCallbacks iQuickAccessWalletServiceCallbacks) throws RemoteException {
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletService
        public void unregisterWalletServiceEventListener(WalletServiceEventListenerRequest walletServiceEventListenerRequest) throws RemoteException {
        }
    }

    void onWalletCardSelected(SelectWalletCardRequest selectWalletCardRequest) throws RemoteException;

    void onWalletCardsRequested(GetWalletCardsRequest getWalletCardsRequest, IQuickAccessWalletServiceCallbacks iQuickAccessWalletServiceCallbacks) throws RemoteException;

    void onWalletDismissed() throws RemoteException;

    void registerWalletServiceEventListener(WalletServiceEventListenerRequest walletServiceEventListenerRequest, IQuickAccessWalletServiceCallbacks iQuickAccessWalletServiceCallbacks) throws RemoteException;

    void unregisterWalletServiceEventListener(WalletServiceEventListenerRequest walletServiceEventListenerRequest) throws RemoteException;

    public static abstract class Stub extends Binder implements IQuickAccessWalletService {
        private static final String DESCRIPTOR = "android.service.quickaccesswallet.IQuickAccessWalletService";
        static final int TRANSACTION_onWalletCardSelected = 2;
        static final int TRANSACTION_onWalletCardsRequested = 1;
        static final int TRANSACTION_onWalletDismissed = 3;
        static final int TRANSACTION_registerWalletServiceEventListener = 4;
        static final int TRANSACTION_unregisterWalletServiceEventListener = 5;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onWalletCardsRequested";
            }
            if (i == 2) {
                return "onWalletCardSelected";
            }
            if (i == 3) {
                return "onWalletDismissed";
            }
            if (i == 4) {
                return "registerWalletServiceEventListener";
            }
            if (i != 5) {
                return null;
            }
            return "unregisterWalletServiceEventListener";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IQuickAccessWalletService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IQuickAccessWalletService)) {
                return (IQuickAccessWalletService) iInterfaceQueryLocalInterface;
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
                onWalletCardsRequested(parcel.readInt() != 0 ? GetWalletCardsRequest.CREATOR.createFromParcel(parcel) : null, IQuickAccessWalletServiceCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onWalletCardSelected(parcel.readInt() != 0 ? SelectWalletCardRequest.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onWalletDismissed();
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                registerWalletServiceEventListener(parcel.readInt() != 0 ? WalletServiceEventListenerRequest.CREATOR.createFromParcel(parcel) : null, IQuickAccessWalletServiceCallbacks.Stub.asInterface(parcel.readStrongBinder()));
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                unregisterWalletServiceEventListener(parcel.readInt() != 0 ? WalletServiceEventListenerRequest.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IQuickAccessWalletService {
            public static IQuickAccessWalletService sDefaultImpl;
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

            @Override // android.service.quickaccesswallet.IQuickAccessWalletService
            public void onWalletCardsRequested(GetWalletCardsRequest getWalletCardsRequest, IQuickAccessWalletServiceCallbacks iQuickAccessWalletServiceCallbacks) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (getWalletCardsRequest != null) {
                        parcelObtain.writeInt(1);
                        getWalletCardsRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iQuickAccessWalletServiceCallbacks != null ? iQuickAccessWalletServiceCallbacks.asBinder() : null);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onWalletCardsRequested(getWalletCardsRequest, iQuickAccessWalletServiceCallbacks);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.quickaccesswallet.IQuickAccessWalletService
            public void onWalletCardSelected(SelectWalletCardRequest selectWalletCardRequest) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (selectWalletCardRequest != null) {
                        parcelObtain.writeInt(1);
                        selectWalletCardRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onWalletCardSelected(selectWalletCardRequest);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.quickaccesswallet.IQuickAccessWalletService
            public void onWalletDismissed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onWalletDismissed();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.quickaccesswallet.IQuickAccessWalletService
            public void registerWalletServiceEventListener(WalletServiceEventListenerRequest walletServiceEventListenerRequest, IQuickAccessWalletServiceCallbacks iQuickAccessWalletServiceCallbacks) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (walletServiceEventListenerRequest != null) {
                        parcelObtain.writeInt(1);
                        walletServiceEventListenerRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iQuickAccessWalletServiceCallbacks != null ? iQuickAccessWalletServiceCallbacks.asBinder() : null);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().registerWalletServiceEventListener(walletServiceEventListenerRequest, iQuickAccessWalletServiceCallbacks);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.quickaccesswallet.IQuickAccessWalletService
            public void unregisterWalletServiceEventListener(WalletServiceEventListenerRequest walletServiceEventListenerRequest) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (walletServiceEventListenerRequest != null) {
                        parcelObtain.writeInt(1);
                        walletServiceEventListenerRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().unregisterWalletServiceEventListener(walletServiceEventListenerRequest);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IQuickAccessWalletService iQuickAccessWalletService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iQuickAccessWalletService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iQuickAccessWalletService;
            return true;
        }

        public static IQuickAccessWalletService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
