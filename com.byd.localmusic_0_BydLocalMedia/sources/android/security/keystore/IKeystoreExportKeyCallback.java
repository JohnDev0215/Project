package android.security.keystore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.security.keymaster.ExportResult;

/* JADX INFO: loaded from: classes3.dex */
public interface IKeystoreExportKeyCallback extends IInterface {

    public static class Default implements IKeystoreExportKeyCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.security.keystore.IKeystoreExportKeyCallback
        public void onFinished(ExportResult exportResult) throws RemoteException {
        }
    }

    void onFinished(ExportResult exportResult) throws RemoteException;

    public static abstract class Stub extends Binder implements IKeystoreExportKeyCallback {
        private static final String DESCRIPTOR = "android.security.keystore.IKeystoreExportKeyCallback";
        static final int TRANSACTION_onFinished = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "onFinished";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IKeystoreExportKeyCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IKeystoreExportKeyCallback)) {
                return (IKeystoreExportKeyCallback) iInterfaceQueryLocalInterface;
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
                onFinished(parcel.readInt() != 0 ? ExportResult.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IKeystoreExportKeyCallback {
            public static IKeystoreExportKeyCallback sDefaultImpl;
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

            @Override // android.security.keystore.IKeystoreExportKeyCallback
            public void onFinished(ExportResult exportResult) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (exportResult != null) {
                        parcelObtain.writeInt(1);
                        exportResult.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFinished(exportResult);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeystoreExportKeyCallback iKeystoreExportKeyCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iKeystoreExportKeyCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iKeystoreExportKeyCallback;
            return true;
        }

        public static IKeystoreExportKeyCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
