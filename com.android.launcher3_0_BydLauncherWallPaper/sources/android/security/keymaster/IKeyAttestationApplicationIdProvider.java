package android.security.keymaster;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IKeyAttestationApplicationIdProvider extends IInterface {

    public static class Default implements IKeyAttestationApplicationIdProvider {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.security.keymaster.IKeyAttestationApplicationIdProvider
        public KeyAttestationApplicationId getKeyAttestationApplicationId(int i) throws RemoteException {
            return null;
        }
    }

    KeyAttestationApplicationId getKeyAttestationApplicationId(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IKeyAttestationApplicationIdProvider {
        private static final String DESCRIPTOR = "android.security.keymaster.IKeyAttestationApplicationIdProvider";
        static final int TRANSACTION_getKeyAttestationApplicationId = 1;

        public static String getDefaultTransactionName(int i) {
            if (i != 1) {
                return null;
            }
            return "getKeyAttestationApplicationId";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IKeyAttestationApplicationIdProvider asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IKeyAttestationApplicationIdProvider)) {
                return (IKeyAttestationApplicationIdProvider) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            KeyAttestationApplicationId keyAttestationApplicationId = getKeyAttestationApplicationId(parcel.readInt());
            parcel2.writeNoException();
            if (keyAttestationApplicationId != null) {
                parcel2.writeInt(1);
                keyAttestationApplicationId.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }

        private static class Proxy implements IKeyAttestationApplicationIdProvider {
            public static IKeyAttestationApplicationIdProvider sDefaultImpl;
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

            @Override // android.security.keymaster.IKeyAttestationApplicationIdProvider
            public KeyAttestationApplicationId getKeyAttestationApplicationId(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getKeyAttestationApplicationId(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? KeyAttestationApplicationId.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IKeyAttestationApplicationIdProvider iKeyAttestationApplicationIdProvider) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iKeyAttestationApplicationIdProvider == null) {
                return false;
            }
            Proxy.sDefaultImpl = iKeyAttestationApplicationIdProvider;
            return true;
        }

        public static IKeyAttestationApplicationIdProvider getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
