package android.app.admin;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface StartInstallingUpdateCallback extends IInterface {

    public static class Default implements StartInstallingUpdateCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.admin.StartInstallingUpdateCallback
        public void onStartInstallingUpdateError(int i, String str) throws RemoteException {
        }
    }

    void onStartInstallingUpdateError(int i, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements StartInstallingUpdateCallback {
        private static final String DESCRIPTOR = "android.app.admin.StartInstallingUpdateCallback";
        static final int TRANSACTION_onStartInstallingUpdateError = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onStartInstallingUpdateError";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static StartInstallingUpdateCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof StartInstallingUpdateCallback)) {
                return (StartInstallingUpdateCallback) iInterfaceQueryLocalInterface;
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
                onStartInstallingUpdateError(parcel.readInt(), parcel.readString());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements StartInstallingUpdateCallback {
            public static StartInstallingUpdateCallback sDefaultImpl;
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

            @Override // android.app.admin.StartInstallingUpdateCallback
            public void onStartInstallingUpdateError(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onStartInstallingUpdateError(i, str);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(StartInstallingUpdateCallback startInstallingUpdateCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (startInstallingUpdateCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = startInstallingUpdateCallback;
            return true;
        }

        public static StartInstallingUpdateCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
