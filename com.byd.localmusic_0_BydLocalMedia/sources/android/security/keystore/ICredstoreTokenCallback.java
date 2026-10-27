package android.security.keystore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface ICredstoreTokenCallback extends IInterface {

    public static class Default implements ICredstoreTokenCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.security.keystore.ICredstoreTokenCallback
        public void onFinished(boolean z, byte[] bArr, byte[] bArr2) throws RemoteException {
        }
    }

    void onFinished(boolean z, byte[] bArr, byte[] bArr2) throws RemoteException;

    public static abstract class Stub extends Binder implements ICredstoreTokenCallback {
        private static final String DESCRIPTOR = "android.security.keystore.ICredstoreTokenCallback";
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

        public static ICredstoreTokenCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof ICredstoreTokenCallback)) {
                return (ICredstoreTokenCallback) iInterfaceQueryLocalInterface;
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
                onFinished(parcel.readInt() != 0, parcel.createByteArray(), parcel.createByteArray());
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements ICredstoreTokenCallback {
            public static ICredstoreTokenCallback sDefaultImpl;
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

            @Override // android.security.keystore.ICredstoreTokenCallback
            public void onFinished(boolean z, byte[] bArr, byte[] bArr2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeByteArray(bArr2);
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFinished(z, bArr, bArr2);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICredstoreTokenCallback iCredstoreTokenCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iCredstoreTokenCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iCredstoreTokenCallback;
            return true;
        }

        public static ICredstoreTokenCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
