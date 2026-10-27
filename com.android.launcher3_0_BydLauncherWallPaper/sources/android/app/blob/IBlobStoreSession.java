package android.app.blob;

import android.app.blob.IBlobCommitCallback;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface IBlobStoreSession extends IInterface {

    public static class Default implements IBlobStoreSession {
        @Override // android.app.blob.IBlobStoreSession
        public void abandon() throws RemoteException {
        }

        @Override // android.app.blob.IBlobStoreSession
        public void allowPackageAccess(String str, byte[] bArr) throws RemoteException {
        }

        @Override // android.app.blob.IBlobStoreSession
        public void allowPublicAccess() throws RemoteException {
        }

        @Override // android.app.blob.IBlobStoreSession
        public void allowSameSignatureAccess() throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.app.blob.IBlobStoreSession
        public void close() throws RemoteException {
        }

        @Override // android.app.blob.IBlobStoreSession
        public void commit(IBlobCommitCallback iBlobCommitCallback) throws RemoteException {
        }

        @Override // android.app.blob.IBlobStoreSession
        public long getSize() throws RemoteException {
            return 0L;
        }

        @Override // android.app.blob.IBlobStoreSession
        public boolean isPackageAccessAllowed(String str, byte[] bArr) throws RemoteException {
            return false;
        }

        @Override // android.app.blob.IBlobStoreSession
        public boolean isPublicAccessAllowed() throws RemoteException {
            return false;
        }

        @Override // android.app.blob.IBlobStoreSession
        public boolean isSameSignatureAccessAllowed() throws RemoteException {
            return false;
        }

        @Override // android.app.blob.IBlobStoreSession
        public ParcelFileDescriptor openRead() throws RemoteException {
            return null;
        }

        @Override // android.app.blob.IBlobStoreSession
        public ParcelFileDescriptor openWrite(long j, long j2) throws RemoteException {
            return null;
        }
    }

    void abandon() throws RemoteException;

    void allowPackageAccess(String str, byte[] bArr) throws RemoteException;

    void allowPublicAccess() throws RemoteException;

    void allowSameSignatureAccess() throws RemoteException;

    void close() throws RemoteException;

    void commit(IBlobCommitCallback iBlobCommitCallback) throws RemoteException;

    long getSize() throws RemoteException;

    boolean isPackageAccessAllowed(String str, byte[] bArr) throws RemoteException;

    boolean isPublicAccessAllowed() throws RemoteException;

    boolean isSameSignatureAccessAllowed() throws RemoteException;

    ParcelFileDescriptor openRead() throws RemoteException;

    ParcelFileDescriptor openWrite(long j, long j2) throws RemoteException;

    public static abstract class Stub extends Binder implements IBlobStoreSession {
        private static final String DESCRIPTOR = "android.app.blob.IBlobStoreSession";
        static final int TRANSACTION_abandon = 11;
        static final int TRANSACTION_allowPackageAccess = 3;
        static final int TRANSACTION_allowPublicAccess = 5;
        static final int TRANSACTION_allowSameSignatureAccess = 4;
        static final int TRANSACTION_close = 10;
        static final int TRANSACTION_commit = 12;
        static final int TRANSACTION_getSize = 9;
        static final int TRANSACTION_isPackageAccessAllowed = 6;
        static final int TRANSACTION_isPublicAccessAllowed = 8;
        static final int TRANSACTION_isSameSignatureAccessAllowed = 7;
        static final int TRANSACTION_openRead = 2;
        static final int TRANSACTION_openWrite = 1;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "openWrite";
                case 2:
                    return "openRead";
                case 3:
                    return "allowPackageAccess";
                case 4:
                    return "allowSameSignatureAccess";
                case 5:
                    return "allowPublicAccess";
                case 6:
                    return "isPackageAccessAllowed";
                case 7:
                    return "isSameSignatureAccessAllowed";
                case 8:
                    return "isPublicAccessAllowed";
                case 9:
                    return "getSize";
                case 10:
                    return "close";
                case 11:
                    return "abandon";
                case 12:
                    return "commit";
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

        public static IBlobStoreSession asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IBlobStoreSession)) {
                return (IBlobStoreSession) iInterfaceQueryLocalInterface;
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
                    ParcelFileDescriptor parcelFileDescriptorOpenWrite = openWrite(parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    if (parcelFileDescriptorOpenWrite != null) {
                        parcel2.writeInt(1);
                        parcelFileDescriptorOpenWrite.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    ParcelFileDescriptor parcelFileDescriptorOpenRead = openRead();
                    parcel2.writeNoException();
                    if (parcelFileDescriptorOpenRead != null) {
                        parcel2.writeInt(1);
                        parcelFileDescriptorOpenRead.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    allowPackageAccess(parcel.readString(), parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    allowSameSignatureAccess();
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    allowPublicAccess();
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPackageAccessAllowed = isPackageAccessAllowed(parcel.readString(), parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPackageAccessAllowed ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSameSignatureAccessAllowed = isSameSignatureAccessAllowed();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSameSignatureAccessAllowed ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsPublicAccessAllowed = isPublicAccessAllowed();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsPublicAccessAllowed ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    long size = getSize();
                    parcel2.writeNoException();
                    parcel2.writeLong(size);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    close();
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    abandon();
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    commit(IBlobCommitCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IBlobStoreSession {
            public static IBlobStoreSession sDefaultImpl;
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

            @Override // android.app.blob.IBlobStoreSession
            public ParcelFileDescriptor openWrite(long j, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openWrite(j, j2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public ParcelFileDescriptor openRead() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openRead();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public void allowPackageAccess(String str, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().allowPackageAccess(str, bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public void allowSameSignatureAccess() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().allowSameSignatureAccess();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public void allowPublicAccess() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().allowPublicAccess();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public boolean isPackageAccessAllowed(String str, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPackageAccessAllowed(str, bArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public boolean isSameSignatureAccessAllowed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSameSignatureAccessAllowed();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public boolean isPublicAccessAllowed() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isPublicAccessAllowed();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public long getSize() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSize();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public void close() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().close();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public void abandon() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().abandon();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.app.blob.IBlobStoreSession
            public void commit(IBlobCommitCallback iBlobCommitCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBlobCommitCallback != null ? iBlobCommitCallback.asBinder() : null);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().commit(iBlobCommitCallback);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IBlobStoreSession iBlobStoreSession) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iBlobStoreSession == null) {
                return false;
            }
            Proxy.sDefaultImpl = iBlobStoreSession;
            return true;
        }

        public static IBlobStoreSession getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
