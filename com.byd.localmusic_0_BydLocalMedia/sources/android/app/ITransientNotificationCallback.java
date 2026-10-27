package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ITransientNotificationCallback extends IInterface {

    public static class Default implements ITransientNotificationCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.ITransientNotificationCallback
        public void onToastHidden() throws RemoteException {
        }

        @Override // android.app.ITransientNotificationCallback
        public void onToastShown() throws RemoteException {
        }
    }

    void onToastHidden() throws RemoteException;

    void onToastShown() throws RemoteException;

    public static abstract class Stub extends Binder implements ITransientNotificationCallback {
        private static final String DESCRIPTOR = "android.app.ITransientNotificationCallback";
        static final int TRANSACTION_onToastHidden = 2;
        static final int TRANSACTION_onToastShown = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onToastShown";
            }
            if (i != 2) {
                return null;
            }
            return "onToastHidden";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITransientNotificationCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ITransientNotificationCallback)) {
                return (ITransientNotificationCallback) iInterfaceQueryLocalInterface;
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
                onToastShown();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onToastHidden();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ITransientNotificationCallback {
            public static ITransientNotificationCallback sDefaultImpl;
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

            @Override // android.app.ITransientNotificationCallback
            public void onToastShown() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onToastShown();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.ITransientNotificationCallback
            public void onToastHidden() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onToastHidden();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITransientNotificationCallback iTransientNotificationCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iTransientNotificationCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iTransientNotificationCallback;
            return true;
        }

        public static ITransientNotificationCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
