package android.media.tv;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface ITvInputManagerCallback extends IInterface {

    public static class Default implements ITvInputManagerCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.media.tv.ITvInputManagerCallback
        public void onInputAdded(String str) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputManagerCallback
        public void onInputRemoved(String str) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputManagerCallback
        public void onInputStateChanged(String str, int i) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputManagerCallback
        public void onInputUpdated(String str) throws RemoteException {
        }

        @Override // android.media.tv.ITvInputManagerCallback
        public void onTvInputInfoUpdated(TvInputInfo tvInputInfo) throws RemoteException {
        }
    }

    void onInputAdded(String str) throws RemoteException;

    void onInputRemoved(String str) throws RemoteException;

    void onInputStateChanged(String str, int i) throws RemoteException;

    void onInputUpdated(String str) throws RemoteException;

    void onTvInputInfoUpdated(TvInputInfo tvInputInfo) throws RemoteException;

    public static abstract class Stub extends Binder implements ITvInputManagerCallback {
        private static final String DESCRIPTOR = "android.media.tv.ITvInputManagerCallback";
        static final int TRANSACTION_onInputAdded = 1;
        static final int TRANSACTION_onInputRemoved = 2;
        static final int TRANSACTION_onInputStateChanged = 4;
        static final int TRANSACTION_onInputUpdated = 3;
        static final int TRANSACTION_onTvInputInfoUpdated = 5;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onInputAdded";
            }
            if (i == 2) {
                return "onInputRemoved";
            }
            if (i == 3) {
                return "onInputUpdated";
            }
            if (i == 4) {
                return "onInputStateChanged";
            }
            if (i != 5) {
                return null;
            }
            return "onTvInputInfoUpdated";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITvInputManagerCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITvInputManagerCallback)) {
                return (ITvInputManagerCallback) iInterfaceQueryLocalInterface;
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
                onInputAdded(parcel.readString());
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onInputRemoved(parcel.readString());
                return true;
            }
            if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                onInputUpdated(parcel.readString());
                return true;
            }
            if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                onInputStateChanged(parcel.readString(), parcel.readInt());
                return true;
            }
            if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                onTvInputInfoUpdated(parcel.readInt() != 0 ? TvInputInfo.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ITvInputManagerCallback {
            public static ITvInputManagerCallback sDefaultImpl;
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

            @Override // android.media.tv.ITvInputManagerCallback
            public void onInputAdded(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onInputAdded(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputManagerCallback
            public void onInputRemoved(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onInputRemoved(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputManagerCallback
            public void onInputUpdated(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(3, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onInputUpdated(str);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputManagerCallback
            public void onInputStateChanged(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(4, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onInputStateChanged(str, i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.media.tv.ITvInputManagerCallback
            public void onTvInputInfoUpdated(TvInputInfo tvInputInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (tvInputInfo != null) {
                        parcelObtain.writeInt(1);
                        tvInputInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(5, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onTvInputInfoUpdated(tvInputInfo);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITvInputManagerCallback iTvInputManagerCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTvInputManagerCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTvInputManagerCallback;
            return true;
        }

        public static ITvInputManagerCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
