package android.security;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IFileIntegrityService extends IInterface {

    public static class Default implements IFileIntegrityService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.security.IFileIntegrityService
        public boolean isApkVeritySupported() throws RemoteException {
            return false;
        }

        @Override // android.security.IFileIntegrityService
        public boolean isAppSourceCertificateTrusted(byte[] bArr, String str) throws RemoteException {
            return false;
        }
    }

    boolean isApkVeritySupported() throws RemoteException;

    boolean isAppSourceCertificateTrusted(byte[] bArr, String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IFileIntegrityService {
        private static final String DESCRIPTOR = "android.security.IFileIntegrityService";
        static final int TRANSACTION_isApkVeritySupported = 1;
        static final int TRANSACTION_isAppSourceCertificateTrusted = 2;

        public static String getDefaultTransactionName(int i) {
            if (i == 1) {
                return "isApkVeritySupported";
            }
            if (i != 2) {
                return null;
            }
            return "isAppSourceCertificateTrusted";
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IFileIntegrityService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IFileIntegrityService)) {
                return (IFileIntegrityService) iInterfaceQueryLocalInterface;
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
                boolean zIsApkVeritySupported = isApkVeritySupported();
                parcel2.writeNoException();
                parcel2.writeInt(zIsApkVeritySupported ? 1 : 0);
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
            boolean zIsAppSourceCertificateTrusted = isAppSourceCertificateTrusted(parcel.createByteArray(), parcel.readString());
            parcel2.writeNoException();
            parcel2.writeInt(zIsAppSourceCertificateTrusted ? 1 : 0);
            return true;
        }

        private static class Proxy implements IFileIntegrityService {
            public static IFileIntegrityService sDefaultImpl;
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

            @Override // android.security.IFileIntegrityService
            public boolean isApkVeritySupported() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isApkVeritySupported();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.security.IFileIntegrityService
            public boolean isAppSourceCertificateTrusted(byte[] bArr, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeByteArray(bArr);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAppSourceCertificateTrusted(bArr, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IFileIntegrityService iFileIntegrityService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iFileIntegrityService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iFileIntegrityService;
            return true;
        }

        public static IFileIntegrityService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
