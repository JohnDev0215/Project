package android.security.identity;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public interface IWritableCredential extends IInterface {

    public static class Default implements IWritableCredential {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.security.identity.IWritableCredential
        public byte[] getCredentialKeyCertificateChain(byte[] bArr) throws RemoteException {
            return null;
        }

        @Override // android.security.identity.IWritableCredential
        public byte[] personalize(AccessControlProfileParcel[] accessControlProfileParcelArr, EntryNamespaceParcel[] entryNamespaceParcelArr, long j) throws RemoteException {
            return null;
        }
    }

    byte[] getCredentialKeyCertificateChain(byte[] bArr) throws RemoteException;

    byte[] personalize(AccessControlProfileParcel[] accessControlProfileParcelArr, EntryNamespaceParcel[] entryNamespaceParcelArr, long j) throws RemoteException;

    public static abstract class Stub extends Binder implements IWritableCredential {
        private static final String DESCRIPTOR = "android.security.identity.IWritableCredential";
        static final int TRANSACTION_getCredentialKeyCertificateChain = 1;
        static final int TRANSACTION_personalize = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "getCredentialKeyCertificateChain";
            }
            if (i != 2) {
                return null;
            }
            return "personalize";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWritableCredential asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IWritableCredential)) {
                return (IWritableCredential) iInterfaceQueryLocalInterface;
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
                byte[] credentialKeyCertificateChain = getCredentialKeyCertificateChain(parcel.createByteArray());
                parcel2.writeNoException();
                parcel2.writeByteArray(credentialKeyCertificateChain);
                return true;
            }
            if (i != 2) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            byte[] bArrPersonalize = personalize((AccessControlProfileParcel[]) parcel.createTypedArray(AccessControlProfileParcel.CREATOR), (EntryNamespaceParcel[]) parcel.createTypedArray(EntryNamespaceParcel.CREATOR), parcel.readLong());
            parcel2.writeNoException();
            parcel2.writeByteArray(bArrPersonalize);
            return true;
        }

        private static class Proxy implements IWritableCredential {
            public static IWritableCredential sDefaultImpl;
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

            @Override // android.security.identity.IWritableCredential
            public byte[] getCredentialKeyCertificateChain(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCredentialKeyCertificateChain(bArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.identity.IWritableCredential
            public byte[] personalize(AccessControlProfileParcel[] accessControlProfileParcelArr, EntryNamespaceParcel[] entryNamespaceParcelArr, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeTypedArray(accessControlProfileParcelArr, 0);
                    parcelObtain.writeTypedArray(entryNamespaceParcelArr, 0);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().personalize(accessControlProfileParcelArr, entryNamespaceParcelArr, j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWritableCredential iWritableCredential) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iWritableCredential == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWritableCredential;
            return true;
        }

        public static IWritableCredential getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
