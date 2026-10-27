package android.gsi;

import android.gsi.IGsiServiceCallback;
import android.gsi.IImageService;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IGsiService extends IInterface {
    public static final int INSTALL_ERROR_FILE_SYSTEM_CLUTTERED = 3;
    public static final int INSTALL_ERROR_GENERIC = 1;
    public static final int INSTALL_ERROR_NO_SPACE = 2;
    public static final int INSTALL_OK = 0;
    public static final int STATUS_COMPLETE = 2;
    public static final int STATUS_NO_OPERATION = 0;
    public static final int STATUS_WORKING = 1;

    public static class Default implements IGsiService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.gsi.IGsiService
        public boolean cancelGsiInstall() throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public int closeInstall() throws RemoteException {
            return 0;
        }

        @Override // android.gsi.IGsiService
        public boolean commitGsiChunkFromAshmem(long j) throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public boolean commitGsiChunkFromStream(ParcelFileDescriptor parcelFileDescriptor, long j) throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public int createPartition(String str, long j, boolean z) throws RemoteException {
            return 0;
        }

        @Override // android.gsi.IGsiService
        public boolean disableGsi() throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public String dumpDeviceMapperDevices() throws RemoteException {
            return null;
        }

        @Override // android.gsi.IGsiService
        public int enableGsi(boolean z, String str) throws RemoteException {
            return 0;
        }

        @Override // android.gsi.IGsiService
        public void enableGsiAsync(boolean z, String str, IGsiServiceCallback iGsiServiceCallback) throws RemoteException {
        }

        @Override // android.gsi.IGsiService
        public String getActiveDsuSlot() throws RemoteException {
            return null;
        }

        @Override // android.gsi.IGsiService
        public int getAvbPublicKey(AvbPublicKey avbPublicKey) throws RemoteException {
            return 0;
        }

        @Override // android.gsi.IGsiService
        public GsiProgress getInstallProgress() throws RemoteException {
            return null;
        }

        @Override // android.gsi.IGsiService
        public List<String> getInstalledDsuSlots() throws RemoteException {
            return null;
        }

        @Override // android.gsi.IGsiService
        public String getInstalledGsiImageDir() throws RemoteException {
            return null;
        }

        @Override // android.gsi.IGsiService
        public boolean isGsiEnabled() throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public boolean isGsiInstallInProgress() throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public boolean isGsiInstalled() throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public boolean isGsiRunning() throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public IImageService openImageService(String str) throws RemoteException {
            return null;
        }

        @Override // android.gsi.IGsiService
        public int openInstall(String str) throws RemoteException {
            return 0;
        }

        @Override // android.gsi.IGsiService
        public boolean removeGsi() throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public void removeGsiAsync(IGsiServiceCallback iGsiServiceCallback) throws RemoteException {
        }

        @Override // android.gsi.IGsiService
        public boolean setGsiAshmem(ParcelFileDescriptor parcelFileDescriptor, long j) throws RemoteException {
            return false;
        }

        @Override // android.gsi.IGsiService
        public int zeroPartition(String str) throws RemoteException {
            return 0;
        }
    }

    boolean cancelGsiInstall() throws RemoteException;

    int closeInstall() throws RemoteException;

    boolean commitGsiChunkFromAshmem(long j) throws RemoteException;

    boolean commitGsiChunkFromStream(ParcelFileDescriptor parcelFileDescriptor, long j) throws RemoteException;

    int createPartition(String str, long j, boolean z) throws RemoteException;

    boolean disableGsi() throws RemoteException;

    String dumpDeviceMapperDevices() throws RemoteException;

    int enableGsi(boolean z, String str) throws RemoteException;

    void enableGsiAsync(boolean z, String str, IGsiServiceCallback iGsiServiceCallback) throws RemoteException;

    String getActiveDsuSlot() throws RemoteException;

    int getAvbPublicKey(AvbPublicKey avbPublicKey) throws RemoteException;

    GsiProgress getInstallProgress() throws RemoteException;

    List<String> getInstalledDsuSlots() throws RemoteException;

    String getInstalledGsiImageDir() throws RemoteException;

    boolean isGsiEnabled() throws RemoteException;

    boolean isGsiInstallInProgress() throws RemoteException;

    boolean isGsiInstalled() throws RemoteException;

    boolean isGsiRunning() throws RemoteException;

    IImageService openImageService(String str) throws RemoteException;

    int openInstall(String str) throws RemoteException;

    boolean removeGsi() throws RemoteException;

    void removeGsiAsync(IGsiServiceCallback iGsiServiceCallback) throws RemoteException;

    boolean setGsiAshmem(ParcelFileDescriptor parcelFileDescriptor, long j) throws RemoteException;

    int zeroPartition(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IGsiService {
        private static final String DESCRIPTOR = "android.gsi.IGsiService";
        static final int TRANSACTION_cancelGsiInstall = 8;
        static final int TRANSACTION_closeInstall = 19;
        static final int TRANSACTION_commitGsiChunkFromAshmem = 4;
        static final int TRANSACTION_commitGsiChunkFromStream = 1;
        static final int TRANSACTION_createPartition = 20;
        static final int TRANSACTION_disableGsi = 12;
        static final int TRANSACTION_dumpDeviceMapperDevices = 23;
        static final int TRANSACTION_enableGsi = 5;
        static final int TRANSACTION_enableGsiAsync = 6;
        static final int TRANSACTION_getActiveDsuSlot = 15;
        static final int TRANSACTION_getAvbPublicKey = 24;
        static final int TRANSACTION_getInstallProgress = 2;
        static final int TRANSACTION_getInstalledDsuSlots = 17;
        static final int TRANSACTION_getInstalledGsiImageDir = 16;
        static final int TRANSACTION_isGsiEnabled = 7;
        static final int TRANSACTION_isGsiInstallInProgress = 9;
        static final int TRANSACTION_isGsiInstalled = 13;
        static final int TRANSACTION_isGsiRunning = 14;
        static final int TRANSACTION_openImageService = 22;
        static final int TRANSACTION_openInstall = 18;
        static final int TRANSACTION_removeGsi = 10;
        static final int TRANSACTION_removeGsiAsync = 11;
        static final int TRANSACTION_setGsiAshmem = 3;
        static final int TRANSACTION_zeroPartition = 21;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "commitGsiChunkFromStream";
                case 2:
                    return "getInstallProgress";
                case 3:
                    return "setGsiAshmem";
                case 4:
                    return "commitGsiChunkFromAshmem";
                case 5:
                    return "enableGsi";
                case 6:
                    return "enableGsiAsync";
                case 7:
                    return "isGsiEnabled";
                case 8:
                    return "cancelGsiInstall";
                case 9:
                    return "isGsiInstallInProgress";
                case 10:
                    return "removeGsi";
                case 11:
                    return "removeGsiAsync";
                case 12:
                    return "disableGsi";
                case 13:
                    return "isGsiInstalled";
                case 14:
                    return "isGsiRunning";
                case 15:
                    return "getActiveDsuSlot";
                case 16:
                    return "getInstalledGsiImageDir";
                case 17:
                    return "getInstalledDsuSlots";
                case 18:
                    return "openInstall";
                case 19:
                    return "closeInstall";
                case 20:
                    return "createPartition";
                case 21:
                    return "zeroPartition";
                case 22:
                    return "openImageService";
                case 23:
                    return "dumpDeviceMapperDevices";
                case 24:
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

        public static IGsiService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IGsiService)) {
                return (IGsiService) iInterfaceQueryLocalInterface;
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
                    boolean zCommitGsiChunkFromStream = commitGsiChunkFromStream(parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCommitGsiChunkFromStream ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    GsiProgress installProgress = getInstallProgress();
                    parcel2.writeNoException();
                    if (installProgress != null) {
                        parcel2.writeInt(1);
                        installProgress.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean gsiAshmem = setGsiAshmem(parcel.readInt() != 0 ? ParcelFileDescriptor.CREATOR.createFromParcel(parcel) : null, parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(gsiAshmem ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCommitGsiChunkFromAshmem = commitGsiChunkFromAshmem(parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCommitGsiChunkFromAshmem ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iEnableGsi = enableGsi(parcel.readInt() != 0, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iEnableGsi);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableGsiAsync(parcel.readInt() != 0, parcel.readString(), IGsiServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsGsiEnabled = isGsiEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsGsiEnabled ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCancelGsiInstall = cancelGsiInstall();
                    parcel2.writeNoException();
                    parcel2.writeInt(zCancelGsiInstall ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsGsiInstallInProgress = isGsiInstallInProgress();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsGsiInstallInProgress ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRemoveGsi = removeGsi();
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveGsi ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeGsiAsync(IGsiServiceCallback.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDisableGsi = disableGsi();
                    parcel2.writeNoException();
                    parcel2.writeInt(zDisableGsi ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsGsiInstalled = isGsiInstalled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsGsiInstalled ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsGsiRunning = isGsiRunning();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsGsiRunning ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    String activeDsuSlot = getActiveDsuSlot();
                    parcel2.writeNoException();
                    parcel2.writeString(activeDsuSlot);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    String installedGsiImageDir = getInstalledGsiImageDir();
                    parcel2.writeNoException();
                    parcel2.writeString(installedGsiImageDir);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> installedDsuSlots = getInstalledDsuSlots();
                    parcel2.writeNoException();
                    parcel2.writeStringList(installedDsuSlots);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iOpenInstall = openInstall(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iOpenInstall);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCloseInstall = closeInstall();
                    parcel2.writeNoException();
                    parcel2.writeInt(iCloseInstall);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iCreatePartition = createPartition(parcel.readString(), parcel.readLong(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(iCreatePartition);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iZeroPartition = zeroPartition(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iZeroPartition);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    IImageService iImageServiceOpenImageService = openImageService(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iImageServiceOpenImageService != null ? iImageServiceOpenImageService.asBinder() : null);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    String strDumpDeviceMapperDevices = dumpDeviceMapperDevices();
                    parcel2.writeNoException();
                    parcel2.writeString(strDumpDeviceMapperDevices);
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    AvbPublicKey avbPublicKey = new AvbPublicKey();
                    int avbPublicKey2 = getAvbPublicKey(avbPublicKey);
                    parcel2.writeNoException();
                    parcel2.writeInt(avbPublicKey2);
                    parcel2.writeInt(1);
                    avbPublicKey.writeToParcel(parcel2, 1);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IGsiService {
            public static IGsiService sDefaultImpl;
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

            @Override // android.gsi.IGsiService
            public boolean commitGsiChunkFromStream(ParcelFileDescriptor parcelFileDescriptor, long j) throws RemoteException {
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
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().commitGsiChunkFromStream(parcelFileDescriptor, j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public GsiProgress getInstallProgress() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstallProgress();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? GsiProgress.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public boolean setGsiAshmem(ParcelFileDescriptor parcelFileDescriptor, long j) throws RemoteException {
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
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setGsiAshmem(parcelFileDescriptor, j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public boolean commitGsiChunkFromAshmem(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().commitGsiChunkFromAshmem(j);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public int enableGsi(boolean z, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enableGsi(z, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public void enableGsiAsync(boolean z, String str, IGsiServiceCallback iGsiServiceCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(iGsiServiceCallback != null ? iGsiServiceCallback.asBinder() : null);
                    if (this.mRemote.transact(6, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().enableGsiAsync(z, str, iGsiServiceCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public boolean isGsiEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isGsiEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public boolean cancelGsiInstall() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cancelGsiInstall();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public boolean isGsiInstallInProgress() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isGsiInstallInProgress();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public boolean removeGsi() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeGsi();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public void removeGsiAsync(IGsiServiceCallback iGsiServiceCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iGsiServiceCallback != null ? iGsiServiceCallback.asBinder() : null);
                    if (this.mRemote.transact(11, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().removeGsiAsync(iGsiServiceCallback);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public boolean disableGsi() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().disableGsi();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public boolean isGsiInstalled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isGsiInstalled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public boolean isGsiRunning() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isGsiRunning();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public String getActiveDsuSlot() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getActiveDsuSlot();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public String getInstalledGsiImageDir() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstalledGsiImageDir();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public List<String> getInstalledDsuSlots() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInstalledDsuSlots();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public int openInstall(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openInstall(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public int closeInstall() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().closeInstall();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public int createPartition(String str, long j, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createPartition(str, j, z);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public int zeroPartition(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().zeroPartition(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public IImageService openImageService(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openImageService(str);
                    }
                    parcelObtain2.readException();
                    return IImageService.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public String dumpDeviceMapperDevices() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().dumpDeviceMapperDevices();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.gsi.IGsiService
            public int getAvbPublicKey(AvbPublicKey avbPublicKey) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAvbPublicKey(avbPublicKey);
                    }
                    parcelObtain2.readException();
                    int i = parcelObtain2.readInt();
                    if (parcelObtain2.readInt() != 0) {
                        avbPublicKey.readFromParcel(parcelObtain2);
                    }
                    return i;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGsiService iGsiService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iGsiService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iGsiService;
            return true;
        }

        public static IGsiService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
