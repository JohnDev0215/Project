package android.os;

import android.os.storage.CrateMetadata;
import java.io.FileDescriptor;

/* JADX INFO: loaded from: classes2.dex */
public interface IInstalld extends IInterface {
    public static final int FLAG_CLEAR_APP_DATA_KEEP_ART_PROFILES = 131072;
    public static final int FLAG_CLEAR_CACHE_ONLY = 16;
    public static final int FLAG_CLEAR_CODE_CACHE_ONLY = 32;
    public static final int FLAG_FORCE = 8192;
    public static final int FLAG_FREE_CACHE_NOOP = 1024;
    public static final int FLAG_FREE_CACHE_V2 = 256;
    public static final int FLAG_FREE_CACHE_V2_DEFY_QUOTA = 512;
    public static final int FLAG_STORAGE_CE = 2;
    public static final int FLAG_STORAGE_DE = 1;
    public static final int FLAG_STORAGE_EXTERNAL = 4;
    public static final int FLAG_USE_QUOTA = 4096;

    public static class Default implements IInstalld {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.os.IInstalld
        public void assertFsverityRootHashMatches(String str, byte[] bArr) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void clearAppData(String str, String str2, int i, int i2, long j) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void clearAppProfiles(String str, String str2) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public boolean compileLayouts(String str, String str2, String str3, int i) throws RemoteException {
            return false;
        }

        @Override // android.os.IInstalld
        public boolean copySystemProfile(String str, int i, String str2, String str3) throws RemoteException {
            return false;
        }

        @Override // android.os.IInstalld
        public long createAppData(String str, String str2, int i, int i2, int i3, String str3, int i4) throws RemoteException {
            return 0L;
        }

        @Override // android.os.IInstalld
        public long createAppDataBatched(String[] strArr, String[] strArr2, int i, int i2, int[] iArr, String[] strArr3, int[] iArr2) throws RemoteException {
            return 0L;
        }

        @Override // android.os.IInstalld
        public void createOatDir(String str, String str2) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public boolean createProfileSnapshot(int i, String str, String str2, String str3) throws RemoteException {
            return false;
        }

        @Override // android.os.IInstalld
        public void createUserData(String str, int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void deleteOdex(String str, String str2, String str3) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void destroyAppData(String str, String str2, int i, int i2, long j) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void destroyAppDataSnapshot(String str, String str2, int i, long j, int i2, int i3) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void destroyAppProfiles(String str) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void destroyCeSnapshotsNotSpecified(String str, int i, int[] iArr) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void destroyProfileSnapshot(String str, String str2) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void destroyUserData(String str, int i, int i2) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void dexopt(String str, int i, String str2, String str3, int i2, String str4, int i3, String str5, String str6, String str7, String str8, boolean z, int i4, String str9, String str10, String str11) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public boolean dumpProfiles(int i, String str, String str2, String str3) throws RemoteException {
            return false;
        }

        @Override // android.os.IInstalld
        public void fixupAppData(String str, int i) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void freeCache(String str, long j, long j2, int i) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public CrateMetadata[] getAppCrates(String str, String[] strArr, int i) throws RemoteException {
            return null;
        }

        @Override // android.os.IInstalld
        public long[] getAppSize(String str, String[] strArr, int i, int i2, int i3, long[] jArr, String[] strArr2) throws RemoteException {
            return null;
        }

        @Override // android.os.IInstalld
        public long[] getExternalSize(String str, int i, int i2, int[] iArr) throws RemoteException {
            return null;
        }

        @Override // android.os.IInstalld
        public CrateMetadata[] getUserCrates(String str, int i) throws RemoteException {
            return null;
        }

        @Override // android.os.IInstalld
        public long[] getUserSize(String str, int i, int i2, int[] iArr) throws RemoteException {
            return null;
        }

        @Override // android.os.IInstalld
        public byte[] hashSecondaryDexFile(String str, String str2, int i, String str3, int i2) throws RemoteException {
            return null;
        }

        @Override // android.os.IInstalld
        public void installApkVerity(String str, FileDescriptor fileDescriptor, int i) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void invalidateMounts() throws RemoteException {
        }

        @Override // android.os.IInstalld
        public boolean isQuotaSupported(String str) throws RemoteException {
            return false;
        }

        @Override // android.os.IInstalld
        public void linkFile(String str, String str2, String str3) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void linkNativeLibraryDirectory(String str, String str2, String str3, int i) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public boolean mergeProfiles(int i, String str, String str2) throws RemoteException {
            return false;
        }

        @Override // android.os.IInstalld
        public void migrateAppData(String str, String str2, int i, int i2) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void migrateLegacyObbData() throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void moveAb(String str, String str2, String str3) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void moveCompleteApp(String str, String str2, String str3, int i, String str4, int i2, String str5) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void onPrivateVolumeRemoved(String str) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public boolean prepareAppProfile(String str, int i, int i2, String str2, String str3, String str4) throws RemoteException {
            return false;
        }

        @Override // android.os.IInstalld
        public boolean reconcileSecondaryDexFile(String str, String str2, int i, String[] strArr, String str3, int i2) throws RemoteException {
            return false;
        }

        @Override // android.os.IInstalld
        public void restoreAppDataSnapshot(String str, String str2, int i, String str3, int i2, int i3, int i4) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void restoreconAppData(String str, String str2, int i, int i2, int i3, String str3) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void rmPackageDir(String str) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void rmdex(String str, String str2) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public void setAppQuota(String str, int i, int i2, long j) throws RemoteException {
        }

        @Override // android.os.IInstalld
        public long snapshotAppData(String str, String str2, int i, int i2, int i3) throws RemoteException {
            return 0L;
        }

        @Override // android.os.IInstalld
        public void tryMountDataMirror(String str) throws RemoteException {
        }
    }

    void assertFsverityRootHashMatches(String str, byte[] bArr) throws RemoteException;

    void clearAppData(String str, String str2, int i, int i2, long j) throws RemoteException;

    void clearAppProfiles(String str, String str2) throws RemoteException;

    boolean compileLayouts(String str, String str2, String str3, int i) throws RemoteException;

    boolean copySystemProfile(String str, int i, String str2, String str3) throws RemoteException;

    long createAppData(String str, String str2, int i, int i2, int i3, String str3, int i4) throws RemoteException;

    long createAppDataBatched(String[] strArr, String[] strArr2, int i, int i2, int[] iArr, String[] strArr3, int[] iArr2) throws RemoteException;

    void createOatDir(String str, String str2) throws RemoteException;

    boolean createProfileSnapshot(int i, String str, String str2, String str3) throws RemoteException;

    void createUserData(String str, int i, int i2, int i3) throws RemoteException;

    void deleteOdex(String str, String str2, String str3) throws RemoteException;

    void destroyAppData(String str, String str2, int i, int i2, long j) throws RemoteException;

    void destroyAppDataSnapshot(String str, String str2, int i, long j, int i2, int i3) throws RemoteException;

    void destroyAppProfiles(String str) throws RemoteException;

    void destroyCeSnapshotsNotSpecified(String str, int i, int[] iArr) throws RemoteException;

    void destroyProfileSnapshot(String str, String str2) throws RemoteException;

    void destroyUserData(String str, int i, int i2) throws RemoteException;

    void dexopt(String str, int i, String str2, String str3, int i2, String str4, int i3, String str5, String str6, String str7, String str8, boolean z, int i4, String str9, String str10, String str11) throws RemoteException;

    boolean dumpProfiles(int i, String str, String str2, String str3) throws RemoteException;

    void fixupAppData(String str, int i) throws RemoteException;

    void freeCache(String str, long j, long j2, int i) throws RemoteException;

    CrateMetadata[] getAppCrates(String str, String[] strArr, int i) throws RemoteException;

    long[] getAppSize(String str, String[] strArr, int i, int i2, int i3, long[] jArr, String[] strArr2) throws RemoteException;

    long[] getExternalSize(String str, int i, int i2, int[] iArr) throws RemoteException;

    CrateMetadata[] getUserCrates(String str, int i) throws RemoteException;

    long[] getUserSize(String str, int i, int i2, int[] iArr) throws RemoteException;

    byte[] hashSecondaryDexFile(String str, String str2, int i, String str3, int i2) throws RemoteException;

    void installApkVerity(String str, FileDescriptor fileDescriptor, int i) throws RemoteException;

    void invalidateMounts() throws RemoteException;

    boolean isQuotaSupported(String str) throws RemoteException;

    void linkFile(String str, String str2, String str3) throws RemoteException;

    void linkNativeLibraryDirectory(String str, String str2, String str3, int i) throws RemoteException;

    boolean mergeProfiles(int i, String str, String str2) throws RemoteException;

    void migrateAppData(String str, String str2, int i, int i2) throws RemoteException;

    void migrateLegacyObbData() throws RemoteException;

    void moveAb(String str, String str2, String str3) throws RemoteException;

    void moveCompleteApp(String str, String str2, String str3, int i, String str4, int i2, String str5) throws RemoteException;

    void onPrivateVolumeRemoved(String str) throws RemoteException;

    boolean prepareAppProfile(String str, int i, int i2, String str2, String str3, String str4) throws RemoteException;

    boolean reconcileSecondaryDexFile(String str, String str2, int i, String[] strArr, String str3, int i2) throws RemoteException;

    void restoreAppDataSnapshot(String str, String str2, int i, String str3, int i2, int i3, int i4) throws RemoteException;

    void restoreconAppData(String str, String str2, int i, int i2, int i3, String str3) throws RemoteException;

    void rmPackageDir(String str) throws RemoteException;

    void rmdex(String str, String str2) throws RemoteException;

    void setAppQuota(String str, int i, int i2, long j) throws RemoteException;

    long snapshotAppData(String str, String str2, int i, int i2, int i3) throws RemoteException;

    void tryMountDataMirror(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IInstalld {
        private static final String DESCRIPTOR = "android.os.IInstalld";
        static final int TRANSACTION_assertFsverityRootHashMatches = 35;
        static final int TRANSACTION_clearAppData = 7;
        static final int TRANSACTION_clearAppProfiles = 23;
        static final int TRANSACTION_compileLayouts = 18;
        static final int TRANSACTION_copySystemProfile = 22;
        static final int TRANSACTION_createAppData = 3;
        static final int TRANSACTION_createAppDataBatched = 4;
        static final int TRANSACTION_createOatDir = 30;
        static final int TRANSACTION_createProfileSnapshot = 25;
        static final int TRANSACTION_createUserData = 1;
        static final int TRANSACTION_deleteOdex = 33;
        static final int TRANSACTION_destroyAppData = 8;
        static final int TRANSACTION_destroyAppDataSnapshot = 43;
        static final int TRANSACTION_destroyAppProfiles = 24;
        static final int TRANSACTION_destroyCeSnapshotsNotSpecified = 44;
        static final int TRANSACTION_destroyProfileSnapshot = 26;
        static final int TRANSACTION_destroyUserData = 2;
        static final int TRANSACTION_dexopt = 17;
        static final int TRANSACTION_dumpProfiles = 21;
        static final int TRANSACTION_fixupAppData = 9;
        static final int TRANSACTION_freeCache = 28;
        static final int TRANSACTION_getAppCrates = 13;
        static final int TRANSACTION_getAppSize = 10;
        static final int TRANSACTION_getExternalSize = 12;
        static final int TRANSACTION_getUserCrates = 14;
        static final int TRANSACTION_getUserSize = 11;
        static final int TRANSACTION_hashSecondaryDexFile = 37;
        static final int TRANSACTION_installApkVerity = 34;
        static final int TRANSACTION_invalidateMounts = 38;
        static final int TRANSACTION_isQuotaSupported = 39;
        static final int TRANSACTION_linkFile = 31;
        static final int TRANSACTION_linkNativeLibraryDirectory = 29;
        static final int TRANSACTION_mergeProfiles = 20;
        static final int TRANSACTION_migrateAppData = 6;
        static final int TRANSACTION_migrateLegacyObbData = 47;
        static final int TRANSACTION_moveAb = 32;
        static final int TRANSACTION_moveCompleteApp = 16;
        static final int TRANSACTION_onPrivateVolumeRemoved = 46;
        static final int TRANSACTION_prepareAppProfile = 40;
        static final int TRANSACTION_reconcileSecondaryDexFile = 36;
        static final int TRANSACTION_restoreAppDataSnapshot = 42;
        static final int TRANSACTION_restoreconAppData = 5;
        static final int TRANSACTION_rmPackageDir = 27;
        static final int TRANSACTION_rmdex = 19;
        static final int TRANSACTION_setAppQuota = 15;
        static final int TRANSACTION_snapshotAppData = 41;
        static final int TRANSACTION_tryMountDataMirror = 45;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "createUserData";
                case 2:
                    return "destroyUserData";
                case 3:
                    return "createAppData";
                case 4:
                    return "createAppDataBatched";
                case 5:
                    return "restoreconAppData";
                case 6:
                    return "migrateAppData";
                case 7:
                    return "clearAppData";
                case 8:
                    return "destroyAppData";
                case 9:
                    return "fixupAppData";
                case 10:
                    return "getAppSize";
                case 11:
                    return "getUserSize";
                case 12:
                    return "getExternalSize";
                case 13:
                    return "getAppCrates";
                case 14:
                    return "getUserCrates";
                case 15:
                    return "setAppQuota";
                case 16:
                    return "moveCompleteApp";
                case 17:
                    return "dexopt";
                case 18:
                    return "compileLayouts";
                case 19:
                    return "rmdex";
                case 20:
                    return "mergeProfiles";
                case 21:
                    return "dumpProfiles";
                case 22:
                    return "copySystemProfile";
                case 23:
                    return "clearAppProfiles";
                case 24:
                    return "destroyAppProfiles";
                case 25:
                    return "createProfileSnapshot";
                case 26:
                    return "destroyProfileSnapshot";
                case 27:
                    return "rmPackageDir";
                case 28:
                    return "freeCache";
                case 29:
                    return "linkNativeLibraryDirectory";
                case 30:
                    return "createOatDir";
                case 31:
                    return "linkFile";
                case 32:
                    return "moveAb";
                case 33:
                    return "deleteOdex";
                case 34:
                    return "installApkVerity";
                case 35:
                    return "assertFsverityRootHashMatches";
                case 36:
                    return "reconcileSecondaryDexFile";
                case 37:
                    return "hashSecondaryDexFile";
                case 38:
                    return "invalidateMounts";
                case 39:
                    return "isQuotaSupported";
                case 40:
                    return "prepareAppProfile";
                case 41:
                    return "snapshotAppData";
                case 42:
                    return "restoreAppDataSnapshot";
                case 43:
                    return "destroyAppDataSnapshot";
                case 44:
                    return "destroyCeSnapshotsNotSpecified";
                case 45:
                    return "tryMountDataMirror";
                case 46:
                    return "onPrivateVolumeRemoved";
                case 47:
                    return "migrateLegacyObbData";
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

        public static IInstalld asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IInstalld)) {
                return (IInstalld) iInterfaceQueryLocalInterface;
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
                    createUserData(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    destroyUserData(parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    long jCreateAppData = createAppData(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(jCreateAppData);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    long jCreateAppDataBatched = createAppDataBatched(parcel.createStringArray(), parcel.createStringArray(), parcel.readInt(), parcel.readInt(), parcel.createIntArray(), parcel.createStringArray(), parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeLong(jCreateAppDataBatched);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    restoreconAppData(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    migrateAppData(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearAppData(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    destroyAppData(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    fixupAppData(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    long[] appSize = getAppSize(parcel.readString(), parcel.createStringArray(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.createLongArray(), parcel.createStringArray());
                    parcel2.writeNoException();
                    parcel2.writeLongArray(appSize);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    long[] userSize = getUserSize(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeLongArray(userSize);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    long[] externalSize = getExternalSize(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.createIntArray());
                    parcel2.writeNoException();
                    parcel2.writeLongArray(externalSize);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    CrateMetadata[] appCrates = getAppCrates(parcel.readString(), parcel.createStringArray(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(appCrates, 1);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    CrateMetadata[] userCrates = getUserCrates(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedArray(userCrates, 1);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAppQuota(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    moveCompleteApp(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    dexopt(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCompileLayouts = compileLayouts(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCompileLayouts ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    rmdex(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zMergeProfiles = mergeProfiles(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zMergeProfiles ? 1 : 0);
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDumpProfiles = dumpProfiles(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zDumpProfiles ? 1 : 0);
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCopySystemProfile = copySystemProfile(parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCopySystemProfile ? 1 : 0);
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearAppProfiles(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    destroyAppProfiles(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zCreateProfileSnapshot = createProfileSnapshot(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zCreateProfileSnapshot ? 1 : 0);
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    destroyProfileSnapshot(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    rmPackageDir(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    freeCache(parcel.readString(), parcel.readLong(), parcel.readLong(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    linkNativeLibraryDirectory(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    createOatDir(parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    linkFile(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    moveAb(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    deleteOdex(parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    installApkVerity(parcel.readString(), parcel.readRawFileDescriptor(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    assertFsverityRootHashMatches(parcel.readString(), parcel.createByteArray());
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zReconcileSecondaryDexFile = reconcileSecondaryDexFile(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.createStringArray(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zReconcileSecondaryDexFile ? 1 : 0);
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    byte[] bArrHashSecondaryDexFile = hashSecondaryDexFile(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeByteArray(bArrHashSecondaryDexFile);
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    invalidateMounts();
                    parcel2.writeNoException();
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsQuotaSupported = isQuotaSupported(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsQuotaSupported ? 1 : 0);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zPrepareAppProfile = prepareAppProfile(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zPrepareAppProfile ? 1 : 0);
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    long jSnapshotAppData = snapshotAppData(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(jSnapshotAppData);
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    restoreAppDataSnapshot(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    destroyAppDataSnapshot(parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readLong(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    destroyCeSnapshotsNotSpecified(parcel.readString(), parcel.readInt(), parcel.createIntArray());
                    parcel2.writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    tryMountDataMirror(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    onPrivateVolumeRemoved(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    migrateLegacyObbData();
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IInstalld {
            public static IInstalld sDefaultImpl;
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

            @Override // android.os.IInstalld
            public void createUserData(String str, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().createUserData(str, i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void destroyUserData(String str, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().destroyUserData(str, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public long createAppData(String str, String str2, int i, int i2, int i3, String str3, int i4) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i4);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        long jCreateAppData = Stub.getDefaultImpl().createAppData(str, str2, i, i2, i3, str3, i4);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return jCreateAppData;
                    }
                    parcelObtain2.readException();
                    long j = parcelObtain2.readLong();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return j;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.os.IInstalld
            public long createAppDataBatched(String[] strArr, String[] strArr2, int i, int i2, int[] iArr, String[] strArr3, int[] iArr2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeStringArray(strArr2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeIntArray(iArr);
                    parcelObtain.writeStringArray(strArr3);
                    parcelObtain.writeIntArray(iArr2);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        long jCreateAppDataBatched = Stub.getDefaultImpl().createAppDataBatched(strArr, strArr2, i, i2, iArr, strArr3, iArr2);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return jCreateAppDataBatched;
                    }
                    parcelObtain2.readException();
                    long j = parcelObtain2.readLong();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return j;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.os.IInstalld
            public void restoreconAppData(String str, String str2, int i, int i2, int i3, String str3) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeString(str3);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().restoreconAppData(str, str2, i, i2, i3, str3);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.os.IInstalld
            public void migrateAppData(String str, String str2, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().migrateAppData(str, str2, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void clearAppData(String str, String str2, int i, int i2, long j) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeLong(j);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearAppData(str, str2, i, i2, j);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.os.IInstalld
            public void destroyAppData(String str, String str2, int i, int i2, long j) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeLong(j);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().destroyAppData(str, str2, i, i2, j);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.os.IInstalld
            public void fixupAppData(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().fixupAppData(str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public long[] getAppSize(String str, String[] strArr, int i, int i2, int i3, long[] jArr, String[] strArr2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeLongArray(jArr);
                    parcelObtain.writeStringArray(strArr2);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        long[] appSize = Stub.getDefaultImpl().getAppSize(str, strArr, i, i2, i3, jArr, strArr2);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return appSize;
                    }
                    parcelObtain2.readException();
                    long[] jArrCreateLongArray = parcelObtain2.createLongArray();
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return jArrCreateLongArray;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.os.IInstalld
            public long[] getUserSize(String str, int i, int i2, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserSize(str, i, i2, iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createLongArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public long[] getExternalSize(String str, int i, int i2, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getExternalSize(str, i, i2, iArr);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createLongArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public CrateMetadata[] getAppCrates(String str, String[] strArr, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppCrates(str, strArr, i);
                    }
                    parcelObtain2.readException();
                    return (CrateMetadata[]) parcelObtain2.createTypedArray(CrateMetadata.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public CrateMetadata[] getUserCrates(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserCrates(str, i);
                    }
                    parcelObtain2.readException();
                    return (CrateMetadata[]) parcelObtain2.createTypedArray(CrateMetadata.CREATOR);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void setAppQuota(String str, int i, int i2, long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeLong(j);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAppQuota(str, i, i2, j);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void moveCompleteApp(String str, String str2, String str3, int i, String str4, int i2, String str5) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str4);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str5);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().moveCompleteApp(str, str2, str3, i, str4, i2, str5);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.os.IInstalld
            public void dexopt(String str, int i, String str2, String str3, int i2, String str4, int i3, String str5, String str6, String str7, String str8, boolean z, int i4, String str9, String str10, String str11) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str4);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeString(str5);
                    parcelObtain.writeString(str6);
                    parcelObtain.writeString(str7);
                    parcelObtain.writeString(str8);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i4);
                    parcelObtain.writeString(str9);
                    parcelObtain.writeString(str10);
                    parcelObtain.writeString(str11);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dexopt(str, i, str2, str3, i2, str4, i3, str5, str6, str7, str8, z, i4, str9, str10, str11);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public boolean compileLayouts(String str, String str2, String str3, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().compileLayouts(str, str2, str3, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void rmdex(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().rmdex(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public boolean mergeProfiles(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().mergeProfiles(i, str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public boolean dumpProfiles(int i, String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().dumpProfiles(i, str, str2, str3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public boolean copySystemProfile(String str, int i, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().copySystemProfile(str, i, str2, str3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void clearAppProfiles(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearAppProfiles(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void destroyAppProfiles(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().destroyAppProfiles(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public boolean createProfileSnapshot(int i, String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createProfileSnapshot(i, str, str2, str3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void destroyProfileSnapshot(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().destroyProfileSnapshot(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void rmPackageDir(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().rmPackageDir(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void freeCache(String str, long j, long j2, int i) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeLong(j2);
                    parcelObtain.writeInt(i);
                    try {
                        if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                            Stub.getDefaultImpl().freeCache(str, j, j2, i);
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        } else {
                            parcelObtain2.readException();
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        }
                    } catch (Throwable th) {
                        th = th;
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }

            @Override // android.os.IInstalld
            public void linkNativeLibraryDirectory(String str, String str2, String str3, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().linkNativeLibraryDirectory(str, str2, str3, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void createOatDir(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().createOatDir(str, str2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void linkFile(String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().linkFile(str, str2, str3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void moveAb(String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().moveAb(str, str2, str3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void deleteOdex(String str, String str2, String str3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().deleteOdex(str, str2, str3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void installApkVerity(String str, FileDescriptor fileDescriptor, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeRawFileDescriptor(fileDescriptor);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().installApkVerity(str, fileDescriptor, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void assertFsverityRootHashMatches(String str, byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeByteArray(bArr);
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().assertFsverityRootHashMatches(str, bArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public boolean reconcileSecondaryDexFile(String str, String str2, int i, String[] strArr, String str3, int i2) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStringArray(strArr);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i2);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        boolean zReconcileSecondaryDexFile = Stub.getDefaultImpl().reconcileSecondaryDexFile(str, str2, i, strArr, str3, i2);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return zReconcileSecondaryDexFile;
                    }
                    parcelObtain2.readException();
                    boolean z = parcelObtain2.readInt() != 0;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.os.IInstalld
            public byte[] hashSecondaryDexFile(String str, String str2, int i, String str3, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hashSecondaryDexFile(str, str2, i, str3, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createByteArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void invalidateMounts() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().invalidateMounts();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public boolean isQuotaSupported(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isQuotaSupported(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public boolean prepareAppProfile(String str, int i, int i2, String str2, String str3, String str4) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeString(str4);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        boolean zPrepareAppProfile = Stub.getDefaultImpl().prepareAppProfile(str, i, i2, str2, str3, str4);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return zPrepareAppProfile;
                    }
                    parcelObtain2.readException();
                    boolean z = parcelObtain2.readInt() != 0;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.os.IInstalld
            public long snapshotAppData(String str, String str2, int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().snapshotAppData(str, str2, i, i2, i3);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void restoreAppDataSnapshot(String str, String str2, int i, String str3, int i2, int i3, int i4) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    parcelObtain.writeInt(i4);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().restoreAppDataSnapshot(str, str2, i, str3, i2, i3, i4);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.os.IInstalld
            public void destroyAppDataSnapshot(String str, String str2, int i, long j, int i2, int i3) throws Throwable {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().destroyAppDataSnapshot(str, str2, i, j, i2, i3);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    } else {
                        parcelObtain2.readException();
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.os.IInstalld
            public void destroyCeSnapshotsNotSpecified(String str, int i, int[] iArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeIntArray(iArr);
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().destroyCeSnapshotsNotSpecified(str, i, iArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void tryMountDataMirror(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tryMountDataMirror(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void onPrivateVolumeRemoved(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().onPrivateVolumeRemoved(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInstalld
            public void migrateLegacyObbData() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().migrateLegacyObbData();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInstalld iInstalld) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iInstalld == null) {
                return false;
            }
            Proxy.sDefaultImpl = iInstalld;
            return true;
        }

        public static IInstalld getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
