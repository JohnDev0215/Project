package android.security.keystore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.security.keymaster.KeymasterCertificateChain;

/* JADX INFO: loaded from: classes2.dex */
public interface IKeystoreCertificateChainCallback extends IInterface {

    public static class Default implements IKeystoreCertificateChainCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.security.keystore.IKeystoreCertificateChainCallback
        public void onFinished(KeystoreResponse keystoreResponse, KeymasterCertificateChain keymasterCertificateChain) throws RemoteException {
        }
    }

    void onFinished(KeystoreResponse keystoreResponse, KeymasterCertificateChain keymasterCertificateChain) throws RemoteException;

    public static abstract class Stub extends Binder implements IKeystoreCertificateChainCallback {
        private static final String DESCRIPTOR = "android.security.keystore.IKeystoreCertificateChainCallback";
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

        public static IKeystoreCertificateChainCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IKeystoreCertificateChainCallback)) {
                return (IKeystoreCertificateChainCallback) iInterfaceQueryLocalInterface;
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
                onFinished(parcel.readInt() != 0 ? KeystoreResponse.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? KeymasterCertificateChain.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IKeystoreCertificateChainCallback {
            public static IKeystoreCertificateChainCallback sDefaultImpl;
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

            @Override // android.security.keystore.IKeystoreCertificateChainCallback
            public void onFinished(KeystoreResponse keystoreResponse, KeymasterCertificateChain keymasterCertificateChain) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (keystoreResponse != null) {
                        parcelObtain.writeInt(1);
                        keystoreResponse.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (keymasterCertificateChain != null) {
                        parcelObtain.writeInt(1);
                        keymasterCertificateChain.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFinished(keystoreResponse, keymasterCertificateChain);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeystoreCertificateChainCallback iKeystoreCertificateChainCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iKeystoreCertificateChainCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iKeystoreCertificateChainCallback;
            return true;
        }

        public static IKeystoreCertificateChainCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
