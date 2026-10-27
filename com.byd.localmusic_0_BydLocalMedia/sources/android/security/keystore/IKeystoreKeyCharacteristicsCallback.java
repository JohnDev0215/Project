package android.security.keystore;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.security.keymaster.KeyCharacteristics;

/* JADX INFO: loaded from: classes3.dex */
public interface IKeystoreKeyCharacteristicsCallback extends IInterface {

    public static class Default implements IKeystoreKeyCharacteristicsCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.security.keystore.IKeystoreKeyCharacteristicsCallback
        public void onFinished(KeystoreResponse keystoreResponse, KeyCharacteristics keyCharacteristics) throws RemoteException {
        }
    }

    void onFinished(KeystoreResponse keystoreResponse, KeyCharacteristics keyCharacteristics) throws RemoteException;

    public static abstract class Stub extends Binder implements IKeystoreKeyCharacteristicsCallback {
        private static final String DESCRIPTOR = "android.security.keystore.IKeystoreKeyCharacteristicsCallback";
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

        public static IKeystoreKeyCharacteristicsCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IKeystoreKeyCharacteristicsCallback)) {
                return (IKeystoreKeyCharacteristicsCallback) iInterfaceQueryLocalInterface;
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
                onFinished(parcel.readInt() != 0 ? KeystoreResponse.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? KeyCharacteristics.CREATOR.createFromParcel(parcel) : null);
                return true;
            }
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IKeystoreKeyCharacteristicsCallback {
            public static IKeystoreKeyCharacteristicsCallback sDefaultImpl;
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

            @Override // android.security.keystore.IKeystoreKeyCharacteristicsCallback
            public void onFinished(KeystoreResponse keystoreResponse, KeyCharacteristics keyCharacteristics) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (keystoreResponse != null) {
                        parcelObtain.writeInt(1);
                        keystoreResponse.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (keyCharacteristics != null) {
                        parcelObtain.writeInt(1);
                        keyCharacteristics.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.mRemote.transact(1, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().onFinished(keystoreResponse, keyCharacteristics);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeystoreKeyCharacteristicsCallback iKeystoreKeyCharacteristicsCallback) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iKeystoreKeyCharacteristicsCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iKeystoreKeyCharacteristicsCallback;
            return true;
        }

        public static IKeystoreKeyCharacteristicsCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
