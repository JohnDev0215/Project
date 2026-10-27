package android.service.quickaccesswallet;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IQuickAccessWalletServiceCallbacks extends IInterface {

    public static class Default implements IQuickAccessWalletServiceCallbacks {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks
        public void onGetWalletCardsFailure(GetWalletCardsError getWalletCardsError) throws RemoteException {
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks
        public void onGetWalletCardsSuccess(GetWalletCardsResponse getWalletCardsResponse) throws RemoteException {
        }

        @Override // android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks
        public void onWalletServiceEvent(WalletServiceEvent walletServiceEvent) throws RemoteException {
        }
    }

    void onGetWalletCardsFailure(GetWalletCardsError getWalletCardsError) throws RemoteException;

    void onGetWalletCardsSuccess(GetWalletCardsResponse getWalletCardsResponse) throws RemoteException;

    void onWalletServiceEvent(WalletServiceEvent walletServiceEvent) throws RemoteException;

    public static abstract class Stub extends Binder implements IQuickAccessWalletServiceCallbacks {
        private static final String DESCRIPTOR = "android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks";
        static final int TRANSACTION_onGetWalletCardsFailure = 2;
        static final int TRANSACTION_onGetWalletCardsSuccess = 1;
        static final int TRANSACTION_onWalletServiceEvent = 3;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onGetWalletCardsSuccess";
            }
            if (i == 2) {
                return "onGetWalletCardsFailure";
            }
            if (i != 3) {
                return null;
            }
            return "onWalletServiceEvent";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IQuickAccessWalletServiceCallbacks asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IQuickAccessWalletServiceCallbacks)) {
                return (IQuickAccessWalletServiceCallbacks) iInterfaceQueryLocalInterface;
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
                onGetWalletCardsSuccess(parcel.readInt() != 0 ? GetWalletCardsResponse.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onGetWalletCardsFailure(parcel.readInt() != 0 ? GetWalletCardsError.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onWalletServiceEvent(parcel.readInt() != 0 ? WalletServiceEvent.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IQuickAccessWalletServiceCallbacks {
            public static IQuickAccessWalletServiceCallbacks sDefaultImpl;
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

            @Override // android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks
            public void onGetWalletCardsSuccess(GetWalletCardsResponse getWalletCardsResponse) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (getWalletCardsResponse != null) {
                        parcelObtain.writeInt(1);
                        getWalletCardsResponse.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGetWalletCardsSuccess(getWalletCardsResponse);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks
            public void onGetWalletCardsFailure(GetWalletCardsError getWalletCardsError) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (getWalletCardsError != null) {
                        parcelObtain.writeInt(1);
                        getWalletCardsError.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onGetWalletCardsFailure(getWalletCardsError);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.service.quickaccesswallet.IQuickAccessWalletServiceCallbacks
            public void onWalletServiceEvent(WalletServiceEvent walletServiceEvent) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (walletServiceEvent != null) {
                        parcelObtain.writeInt(1);
                        walletServiceEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onWalletServiceEvent(walletServiceEvent);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IQuickAccessWalletServiceCallbacks iQuickAccessWalletServiceCallbacks) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iQuickAccessWalletServiceCallbacks == null) {
                return false;
            }
            Proxy.sDefaultImpl = iQuickAccessWalletServiceCallbacks;
            return true;
        }

        public static IQuickAccessWalletServiceCallbacks getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
