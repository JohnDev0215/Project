package android.os.image;

import android.gsi.AvbPublicKey;
import android.gsi.GsiProgress;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public interface IDynamicSystemService extends IInterface {

    public static class Default implements IDynamicSystemService {
        @Override // android.os.image.IDynamicSystemService
        public boolean abort() throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean createPartition(String str, long j, boolean z) throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean finishInstallation() throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean getAvbPublicKey(AvbPublicKey avbPublicKey) throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public GsiProgress getInstallationProgress() throws RemoteException {
            return null;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean isEnabled() throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean isInUse() throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean isInstalled() throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean remove() throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean setAshmem(ParcelFileDescriptor parcelFileDescriptor, long j) throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean setEnable(boolean z, boolean z2) throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean startInstallation(String str) throws RemoteException {
            return false;
        }

        @Override // android.os.image.IDynamicSystemService
        public boolean submitFromAshmem(long j) throws RemoteException {
            return false;
        }
    }

    boolean abort() throws RemoteException;

    boolean createPartition(String str, long j, boolean z) throws RemoteException;

    boolean finishInstallation() throws RemoteException;

    boolean getAvbPublicKey(AvbPublicKey avbPublicKey) throws RemoteException;

    GsiProgress getInstallationProgress() throws RemoteException;

    boolean isEnabled() throws RemoteException;

    boolean isInUse() throws RemoteException;

    boolean isInstalled() throws RemoteException;

    boolean remove() throws RemoteException;

    boolean setAshmem(ParcelFileDescriptor parcelFileDescriptor, long j) throws RemoteException;

    boolean setEnable(boolean z, boolean z2) throws RemoteException;

    boolean startInstallation(String str) throws RemoteException;

    boolean submitFromAshmem(long j) throws RemoteException;

    public static abstract class Stub extends Binder implements IDynamicSystemService {
        private static final String DESCRIPTOR = "android.os.image.IDynamicSystemService";
        static final int TRANSACTION_abort = 5;
        static final int TRANSACTION_createPartition = 2;
        static final int TRANSACTION_finishInstallation = 3;
        static final int TRANSACTION_getAvbPublicKey = 13;
        static final int TRANSACTION_getInstallationProgress = 4;
        static final int TRANSACTION_isEnabled = 8;
        static final int TRANSACTION_isInUse = 6;
        static final int TRANSACTION_isInstalled = 7;
        static final int TRANSACTION_remove = 9;
        static final int TRANSACTION_setAshmem = 11;
        static final int TRANSACTION_setEnable = 10;
        static final int TRANSACTION_startInstallation = 1;
        static final int TRANSACTION_submitFromAshmem = 12;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "startInstallation";
                case 2:
                    return "createPartition";
                case 3:
                    return "finishInstallation";
                case 4:
                    return "getInstallationProgress";
                case 5:
                    return "abort";
                case 6:
                    return "isInUse";
                case 7:
                    return "isInstalled";
                case 8:
                    return "isEnabled";
                case 9:
                    return "remove";
                case 10:
                    return "setEnable";
                case 11:
                    return "setAshmem";
                case 12:
                    return "submitFromAshmem";
                case 13:
                    return "getAvbPublicKey";
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

        public static IDynamicSystemService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IDynamicSystemService)) {
                return (IDynamicSystemService) iInterfaceQueryLocalInterface;
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
                    boolean zStartInstallation = startInstallation(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zStartInstallation ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCreatePartition = createPartition(parcel.readString(), parcel.readLong(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(zCreatePartition ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zFinishInstallation = finishInstallation();
                    parcel2.writeNoException();
                    parcel2.writeInt(zFinishInstallation ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    GsiProgress installationProgress = getInstallationProgress();
                    parcel2.writeNoException();
                    if (installationProgress != null) {
                        parcel2.writeInt(1);
                        installationProgress.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAbort = abort();
                    parcel2.writeNoException();
                    parcel2.writeInt(zAbort ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsInUse = isInUse();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsInUse ? 1 : 0);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsInstalled = isInstalled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsInstalled ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsEnabled = isEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsEnabled ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRemove = remove();
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemove ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean enable = setEnable(parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(enable ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean ashmem = setAshmem(parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(ashmem ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zSubmitFromAshmem = submitFromAshmem(parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(zSubmitFromAshmem ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    AvbPublicKey avbPublicKey = new AvbPublicKey();
                    boolean avbPublicKey2 = getAvbPublicKey(avbPublicKey);
                    parcel2.writeNoException();
                    parcel2.writeInt(avbPublicKey2 ? 1 : 0);
                    parcel2.writeInt(1);
                    avbPublicKey.writeToParcel(parcel2, 1);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IDynamicSystemService {
            public static IDynamicSystemService sDefaultImpl;
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

            @Override // android.os.image.IDynamicSystemService
            public boolean startInstallation(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startInstallation(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean createPartition(String str, long j, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createPartition(str, j, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean finishInstallation() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().finishInstallation();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public GsiProgress getInstallationProgress() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstallationProgress();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? GsiProgress.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean abort() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().abort();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean isInUse() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInUse();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean isInstalled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInstalled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean isEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean remove() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().remove();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean setEnable(boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(z2 ? 1 : 0);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setEnable(z, z2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean setAshmem(ParcelFileDescriptor parcelFileDescriptor, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setAshmem(parcelFileDescriptor, j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean submitFromAshmem(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().submitFromAshmem(j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.image.IDynamicSystemService
            public boolean getAvbPublicKey(AvbPublicKey avbPublicKey) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvbPublicKey(avbPublicKey);
                    }
                    parcelObtain2.readException();
                    boolean z = parcelObtain2.readInt() != 0;
                    if (parcelObtain2.readInt() != 0) {
                        avbPublicKey.readFromParcel(parcelObtain2);
                    }
                    return z;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDynamicSystemService iDynamicSystemService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iDynamicSystemService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDynamicSystemService;
            return true;
        }

        public static IDynamicSystemService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
