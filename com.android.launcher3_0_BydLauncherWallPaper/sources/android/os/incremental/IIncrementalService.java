package android.os.incremental;

import android.content.pm.DataLoaderParamsParcel;
import android.content.pm.IDataLoaderStatusListener;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.incremental.IStorageHealthListener;

/* JADX INFO: loaded from: classes2.dex */
public interface IIncrementalService extends IInterface {
    public static final int BIND_PERMANENT = 1;
    public static final int BIND_TEMPORARY = 0;
    public static final int CREATE_MODE_CREATE = 4;
    public static final int CREATE_MODE_OPEN_EXISTING = 8;
    public static final int CREATE_MODE_PERMANENT_BIND = 2;
    public static final int CREATE_MODE_TEMPORARY_BIND = 1;

    public static class Default implements IIncrementalService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.incremental.IIncrementalService
        public boolean configureNativeBinaries(int i, String str, String str2, String str3, boolean z) throws RemoteException {
            return false;
        }

        @Override // android.os.incremental.IIncrementalService
        public int createLinkedStorage(String str, int i, int i2) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int createStorage(String str, DataLoaderParamsParcel dataLoaderParamsParcel, int i, IDataLoaderStatusListener iDataLoaderStatusListener, StorageHealthCheckParams storageHealthCheckParams, IStorageHealthListener iStorageHealthListener) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int deleteBindMount(int i, String str) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public void deleteStorage(int i) throws RemoteException {
        }

        @Override // android.os.incremental.IIncrementalService
        public void disableReadLogs(int i) throws RemoteException {
        }

        @Override // android.os.incremental.IIncrementalService
        public byte[] getMetadataById(int i, byte[] bArr) throws RemoteException {
            return null;
        }

        @Override // android.os.incremental.IIncrementalService
        public byte[] getMetadataByPath(int i, String str) throws RemoteException {
            return null;
        }

        @Override // android.os.incremental.IIncrementalService
        public boolean isFileRangeLoaded(int i, String str, long j, long j2) throws RemoteException {
            return false;
        }

        @Override // android.os.incremental.IIncrementalService
        public int makeBindMount(int i, String str, String str2, int i2) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int makeDirectories(int i, String str) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int makeDirectory(int i, String str) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int makeFile(int i, String str, IncrementalNewFileParams incrementalNewFileParams) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int makeFileFromRange(int i, String str, String str2, long j, long j2) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int makeLink(int i, String str, int i2, String str2) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public int openStorage(String str) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public boolean startLoading(int i) throws RemoteException {
            return false;
        }

        @Override // android.os.incremental.IIncrementalService
        public int unlink(int i, String str) throws RemoteException {
            return 0;
        }

        @Override // android.os.incremental.IIncrementalService
        public boolean waitForNativeBinariesExtraction(int i) throws RemoteException {
            return false;
        }
    }

    boolean configureNativeBinaries(int i, String str, String str2, String str3, boolean z) throws RemoteException;

    int createLinkedStorage(String str, int i, int i2) throws RemoteException;

    int createStorage(String str, DataLoaderParamsParcel dataLoaderParamsParcel, int i, IDataLoaderStatusListener iDataLoaderStatusListener, StorageHealthCheckParams storageHealthCheckParams, IStorageHealthListener iStorageHealthListener) throws RemoteException;

    int deleteBindMount(int i, String str) throws RemoteException;

    void deleteStorage(int i) throws RemoteException;

    void disableReadLogs(int i) throws RemoteException;

    byte[] getMetadataById(int i, byte[] bArr) throws RemoteException;

    byte[] getMetadataByPath(int i, String str) throws RemoteException;

    boolean isFileRangeLoaded(int i, String str, long j, long j2) throws RemoteException;

    int makeBindMount(int i, String str, String str2, int i2) throws RemoteException;

    int makeDirectories(int i, String str) throws RemoteException;

    int makeDirectory(int i, String str) throws RemoteException;

    int makeFile(int i, String str, IncrementalNewFileParams incrementalNewFileParams) throws RemoteException;

    int makeFileFromRange(int i, String str, String str2, long j, long j2) throws RemoteException;

    int makeLink(int i, String str, int i2, String str2) throws RemoteException;

    int openStorage(String str) throws RemoteException;

    boolean startLoading(int i) throws RemoteException;

    int unlink(int i, String str) throws RemoteException;

    boolean waitForNativeBinariesExtraction(int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IIncrementalService {
        private static final String DESCRIPTOR = "android.os.incremental.IIncrementalService";
        static final int TRANSACTION_configureNativeBinaries = 18;
        static final int TRANSACTION_createLinkedStorage = 3;
        static final int TRANSACTION_createStorage = 2;
        static final int TRANSACTION_deleteBindMount = 5;
        static final int TRANSACTION_deleteStorage = 16;
        static final int TRANSACTION_disableReadLogs = 17;
        static final int TRANSACTION_getMetadataById = 14;
        static final int TRANSACTION_getMetadataByPath = 13;
        static final int TRANSACTION_isFileRangeLoaded = 12;
        static final int TRANSACTION_makeBindMount = 4;
        static final int TRANSACTION_makeDirectories = 7;
        static final int TRANSACTION_makeDirectory = 6;
        static final int TRANSACTION_makeFile = 8;
        static final int TRANSACTION_makeFileFromRange = 9;
        static final int TRANSACTION_makeLink = 10;
        static final int TRANSACTION_openStorage = 1;
        static final int TRANSACTION_startLoading = 15;
        static final int TRANSACTION_unlink = 11;
        static final int TRANSACTION_waitForNativeBinariesExtraction = 19;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "openStorage";
                case 2:
                    return "createStorage";
                case 3:
                    return "createLinkedStorage";
                case 4:
                    return "makeBindMount";
                case 5:
                    return "deleteBindMount";
                case 6:
                    return "makeDirectory";
                case 7:
                    return "makeDirectories";
                case 8:
                    return "makeFile";
                case 9:
                    return "makeFileFromRange";
                case 10:
                    return "makeLink";
                case 11:
                    return "unlink";
                case 12:
                    return "isFileRangeLoaded";
                case 13:
                    return "getMetadataByPath";
                case 14:
                    return "getMetadataById";
                case 15:
                    return "startLoading";
                case 16:
                    return "deleteStorage";
                case 17:
                    return "disableReadLogs";
                case 18:
                    return "configureNativeBinaries";
                case 19:
                    return "waitForNativeBinariesExtraction";
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

        public static IIncrementalService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IIncrementalService)) {
                return (IIncrementalService) iInterfaceQueryLocalInterface;
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
                    int iOpenStorage = openStorage(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iOpenStorage);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCreateStorage = createStorage(parcel.readString(), parcel.readInt() != 0 ? DataLoaderParamsParcel.CREATOR.createFromParcel(parcel) : null, parcel.readInt(), IDataLoaderStatusListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? StorageHealthCheckParams.CREATOR.createFromParcel(parcel) : null, IStorageHealthListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(iCreateStorage);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCreateLinkedStorage = createLinkedStorage(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iCreateLinkedStorage);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iMakeBindMount = makeBindMount(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iMakeBindMount);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iDeleteBindMount = deleteBindMount(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iDeleteBindMount);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iMakeDirectory = makeDirectory(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iMakeDirectory);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iMakeDirectories = makeDirectories(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iMakeDirectories);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iMakeFile = makeFile(parcel.readInt(), parcel.readString(), parcel.readInt() != 0 ? IncrementalNewFileParams.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(iMakeFile);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iMakeFileFromRange = makeFileFromRange(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(iMakeFileFromRange);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iMakeLink = makeLink(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iMakeLink);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iUnlink = unlink(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iUnlink);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsFileRangeLoaded = isFileRangeLoaded(parcel.readInt(), parcel.readString(), parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsFileRangeLoaded ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] metadataByPath = getMetadataByPath(parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(metadataByPath);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] metadataById = getMetadataById(parcel.readInt(), parcel.createByteArray());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(metadataById);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zStartLoading = startLoading(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zStartLoading ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteStorage(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    disableReadLogs(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zConfigureNativeBinaries = configureNativeBinaries(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zConfigureNativeBinaries ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zWaitForNativeBinariesExtraction = waitForNativeBinariesExtraction(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zWaitForNativeBinariesExtraction ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IIncrementalService {
            public static IIncrementalService sDefaultImpl;
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

            @Override // android.os.incremental.IIncrementalService
            public int openStorage(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openStorage(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int createStorage(String str, DataLoaderParamsParcel dataLoaderParamsParcel, int i, IDataLoaderStatusListener iDataLoaderStatusListener, StorageHealthCheckParams storageHealthCheckParams, IStorageHealthListener iStorageHealthListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (dataLoaderParamsParcel != null) {
                        parcelObtain.writeInt(1);
                        dataLoaderParamsParcel.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iDataLoaderStatusListener != null ? iDataLoaderStatusListener.asBinder() : null);
                    if (storageHealthCheckParams != null) {
                        parcelObtain.writeInt(1);
                        storageHealthCheckParams.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iStorageHealthListener != null ? iStorageHealthListener.asBinder() : null);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createStorage(str, dataLoaderParamsParcel, i, iDataLoaderStatusListener, storageHealthCheckParams, iStorageHealthListener);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int createLinkedStorage(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createLinkedStorage(str, i, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int makeBindMount(int i, String str, String str2, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().makeBindMount(i, str, str2, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int deleteBindMount(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().deleteBindMount(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int makeDirectory(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().makeDirectory(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int makeDirectories(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().makeDirectories(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int makeFile(int i, String str, IncrementalNewFileParams incrementalNewFileParams) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (incrementalNewFileParams != null) {
                        parcelObtain.writeInt(1);
                        incrementalNewFileParams.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().makeFile(i, str, incrementalNewFileParams);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int makeFileFromRange(int i, String str, String str2, long j, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().makeFileFromRange(i, str, str2, j, j2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int makeLink(int i, String str, int i2, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().makeLink(i, str, i2, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public int unlink(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unlink(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public boolean isFileRangeLoaded(int i, String str, long j, long j2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isFileRangeLoaded(i, str, j, j2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public byte[] getMetadataByPath(int i, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMetadataByPath(i, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public byte[] getMetadataById(int i, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getMetadataById(i, bArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public boolean startLoading(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startLoading(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public void deleteStorage(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteStorage(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public void disableReadLogs(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableReadLogs(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public boolean configureNativeBinaries(int i, String str, String str2, String str3, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().configureNativeBinaries(i, str, str2, str3, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.incremental.IIncrementalService
            public boolean waitForNativeBinariesExtraction(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().waitForNativeBinariesExtraction(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IIncrementalService iIncrementalService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iIncrementalService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iIncrementalService;
            return true;
        }

        public static IIncrementalService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
