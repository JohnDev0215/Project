package android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface ISearchManagerCallback extends IInterface {

    public static class Default implements ISearchManagerCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.ISearchManagerCallback
        public void onCancel() throws RemoteException {
        }

        @Override // android.app.ISearchManagerCallback
        public void onDismiss() throws RemoteException {
        }
    }

    void onCancel() throws RemoteException;

    void onDismiss() throws RemoteException;

    public static abstract class Stub extends Binder implements ISearchManagerCallback {
        private static final String DESCRIPTOR = "android.app.ISearchManagerCallback";
        static final int TRANSACTION_onCancel = 2;
        static final int TRANSACTION_onDismiss = 1;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "onDismiss";
            }
            if (i != 2) {
                return null;
            }
            return "onCancel";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISearchManagerCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ISearchManagerCallback)) {
                return (ISearchManagerCallback) iInterfaceQueryLocalInterface;
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
                onDismiss();
                return true;
            }
            if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onCancel();
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ISearchManagerCallback {
            public static ISearchManagerCallback sDefaultImpl;
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

            @Override // android.app.ISearchManagerCallback
            public void onDismiss() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onDismiss();
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.ISearchManagerCallback
            public void onCancel() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onCancel();
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISearchManagerCallback iSearchManagerCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iSearchManagerCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSearchManagerCallback;
            return true;
        }

        public static ISearchManagerCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
