package android.service.euicc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IDownloadSubscriptionCallback extends IInterface {

    public static class Default implements IDownloadSubscriptionCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.service.euicc.IDownloadSubscriptionCallback
        public void onComplete(DownloadSubscriptionResult downloadSubscriptionResult) throws RemoteException {
        }
    }

    void onComplete(DownloadSubscriptionResult downloadSubscriptionResult) throws RemoteException;

    public static abstract class Stub extends Binder implements IDownloadSubscriptionCallback {
        private static final String DESCRIPTOR = "android.service.euicc.IDownloadSubscriptionCallback";
        static final int TRANSACTION_onComplete = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onComplete";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadSubscriptionCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDownloadSubscriptionCallback)) {
                return (IDownloadSubscriptionCallback) iInterfaceQueryLocalInterface;
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
                onComplete(parcel.readInt() != 0 ? DownloadSubscriptionResult.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IDownloadSubscriptionCallback {
            public static IDownloadSubscriptionCallback sDefaultImpl;
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

            @Override // android.service.euicc.IDownloadSubscriptionCallback
            public void onComplete(DownloadSubscriptionResult downloadSubscriptionResult) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (downloadSubscriptionResult != null) {
                        parcelObtain.writeInt(1);
                        downloadSubscriptionResult.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onComplete(downloadSubscriptionResult);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadSubscriptionCallback iDownloadSubscriptionCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDownloadSubscriptionCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDownloadSubscriptionCallback;
            return true;
        }

        public static IDownloadSubscriptionCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
