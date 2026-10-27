package android.app.blob;

import android.app.blob.IBlobStoreSession;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.text.TextUtils;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IBlobStoreManager extends IInterface {

    public static class Default implements IBlobStoreManager {
        @Override // android.app.blob.IBlobStoreManager
        public void abandonSession(long j, String str) throws RemoteException {
        }

        @Override // android.app.blob.IBlobStoreManager
        public void acquireLease(BlobHandle blobHandle, int i, CharSequence charSequence, long j, String str) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.blob.IBlobStoreManager
        public long createSession(BlobHandle blobHandle, String str) throws RemoteException {
            return 0L;
        }

        @Override // android.app.blob.IBlobStoreManager
        public void deleteBlob(long j) throws RemoteException {
        }

        @Override // android.app.blob.IBlobStoreManager
        public LeaseInfo getLeaseInfo(BlobHandle blobHandle, String str) throws RemoteException {
            return null;
        }

        @Override // android.app.blob.IBlobStoreManager
        public List<BlobHandle> getLeasedBlobs(String str) throws RemoteException {
            return null;
        }

        @Override // android.app.blob.IBlobStoreManager
        public long getRemainingLeaseQuotaBytes(String str) throws RemoteException {
            return 0L;
        }

        @Override // android.app.blob.IBlobStoreManager
        public ParcelFileDescriptor openBlob(BlobHandle blobHandle, String str) throws RemoteException {
            return null;
        }

        @Override // android.app.blob.IBlobStoreManager
        public IBlobStoreSession openSession(long j, String str) throws RemoteException {
            return null;
        }

        @Override // android.app.blob.IBlobStoreManager
        public List<BlobInfo> queryBlobsForUser(int i) throws RemoteException {
            return null;
        }

        @Override // android.app.blob.IBlobStoreManager
        public void releaseLease(BlobHandle blobHandle, String str) throws RemoteException {
        }

        @Override // android.app.blob.IBlobStoreManager
        public void waitForIdle(RemoteCallback remoteCallback) throws RemoteException {
        }
    }

    void abandonSession(long j, String str) throws RemoteException;

    void acquireLease(BlobHandle blobHandle, int i, CharSequence charSequence, long j, String str) throws RemoteException;

    long createSession(BlobHandle blobHandle, String str) throws RemoteException;

    void deleteBlob(long j) throws RemoteException;

    LeaseInfo getLeaseInfo(BlobHandle blobHandle, String str) throws RemoteException;

    List<BlobHandle> getLeasedBlobs(String str) throws RemoteException;

    long getRemainingLeaseQuotaBytes(String str) throws RemoteException;

    ParcelFileDescriptor openBlob(BlobHandle blobHandle, String str) throws RemoteException;

    IBlobStoreSession openSession(long j, String str) throws RemoteException;

    List<BlobInfo> queryBlobsForUser(int i) throws RemoteException;

    void releaseLease(BlobHandle blobHandle, String str) throws RemoteException;

    void waitForIdle(RemoteCallback remoteCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IBlobStoreManager {
        private static final String DESCRIPTOR = "android.app.blob.IBlobStoreManager";
        static final int TRANSACTION_abandonSession = 4;
        static final int TRANSACTION_acquireLease = 5;
        static final int TRANSACTION_createSession = 1;
        static final int TRANSACTION_deleteBlob = 10;
        static final int TRANSACTION_getLeaseInfo = 12;
        static final int TRANSACTION_getLeasedBlobs = 11;
        static final int TRANSACTION_getRemainingLeaseQuotaBytes = 7;
        static final int TRANSACTION_openBlob = 3;
        static final int TRANSACTION_openSession = 2;
        static final int TRANSACTION_queryBlobsForUser = 9;
        static final int TRANSACTION_releaseLease = 6;
        static final int TRANSACTION_waitForIdle = 8;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "createSession";
                case 2:
                    return "openSession";
                case 3:
                    return "openBlob";
                case 4:
                    return "abandonSession";
                case 5:
                    return "acquireLease";
                case 6:
                    return "releaseLease";
                case 7:
                    return "getRemainingLeaseQuotaBytes";
                case 8:
                    return "waitForIdle";
                case 9:
                    return "queryBlobsForUser";
                case 10:
                    return "deleteBlob";
                case 11:
                    return "getLeasedBlobs";
                case 12:
                    return "getLeaseInfo";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBlobStoreManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBlobStoreManager)) {
                return (IBlobStoreManager) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    long jCreateSession = createSession(parcel.readInt() != 0 ? BlobHandle.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeLong(jCreateSession);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    IBlobStoreSession iBlobStoreSessionOpenSession = openSession(parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iBlobStoreSessionOpenSession != null ? iBlobStoreSessionOpenSession.asBinder() : null);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParcelFileDescriptor parcelFileDescriptorOpenBlob = openBlob(parcel.readInt() != 0 ? BlobHandle.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    if (parcelFileDescriptorOpenBlob != null) {
                        parcel2.writeInt(1);
                        parcelFileDescriptorOpenBlob.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    abandonSession(parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    acquireLease(parcel.readInt() != 0 ? BlobHandle.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), parcel.readInt() != 0 ? TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) : null, parcel.readLong(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    releaseLease(parcel.readInt() != 0 ? BlobHandle.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    long remainingLeaseQuotaBytes = getRemainingLeaseQuotaBytes(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeLong(remainingLeaseQuotaBytes);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    waitForIdle(parcel.readInt() != 0 ? RemoteCallback.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<BlobInfo> listQueryBlobsForUser = queryBlobsForUser(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(listQueryBlobsForUser);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteBlob(parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<BlobHandle> leasedBlobs = getLeasedBlobs(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(leasedBlobs);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    LeaseInfo leaseInfo = getLeaseInfo(parcel.readInt() != 0 ? BlobHandle.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    if (leaseInfo != null) {
                        parcel2.writeInt(1);
                        leaseInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBlobStoreManager {
            public static IBlobStoreManager sDefaultImpl;
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

            @Override // android.app.blob.IBlobStoreManager
            public long createSession(BlobHandle blobHandle, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (blobHandle != null) {
                        parcelObtain.writeInt(1);
                        blobHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createSession(blobHandle, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public IBlobStoreSession openSession(long j, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openSession(j, str);
                    }
                    parcelObtain2.readException();
                    return IBlobStoreSession.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public ParcelFileDescriptor openBlob(BlobHandle blobHandle, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (blobHandle != null) {
                        parcelObtain.writeInt(1);
                        blobHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openBlob(blobHandle, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public void abandonSession(long j, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().abandonSession(j, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public void acquireLease(BlobHandle blobHandle, int i, CharSequence charSequence, long j, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (blobHandle != null) {
                        parcelObtain.writeInt(1);
                        blobHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (charSequence != null) {
                        parcelObtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().acquireLease(blobHandle, i, charSequence, j, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public void releaseLease(BlobHandle blobHandle, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (blobHandle != null) {
                        parcelObtain.writeInt(1);
                        blobHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().releaseLease(blobHandle, str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public long getRemainingLeaseQuotaBytes(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemainingLeaseQuotaBytes(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public void waitForIdle(RemoteCallback remoteCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (remoteCallback != null) {
                        parcelObtain.writeInt(1);
                        remoteCallback.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().waitForIdle(remoteCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public List<BlobInfo> queryBlobsForUser(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().queryBlobsForUser(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(BlobInfo.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public void deleteBlob(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteBlob(j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public List<BlobHandle> getLeasedBlobs(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLeasedBlobs(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createTypedArrayList(BlobHandle.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreManager
            public LeaseInfo getLeaseInfo(BlobHandle blobHandle, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (blobHandle != null) {
                        parcelObtain.writeInt(1);
                        blobHandle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLeaseInfo(blobHandle, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? LeaseInfo.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBlobStoreManager iBlobStoreManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iBlobStoreManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBlobStoreManager;
            return true;
        }

        public static IBlobStoreManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
