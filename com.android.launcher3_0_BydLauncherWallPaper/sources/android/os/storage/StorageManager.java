package android.os.storage;

import android.Manifest;
import android.annotation.SystemApi;
import android.app.ActivityThread;
import android.app.AppGlobals;
import android.app.AppOpsManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.IPackageMoveObserver;
import android.content.res.ObbInfo;
import android.content.res.ObbScanner;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.FileUtils;
import android.os.Handler;
import android.os.IVoldTaskListener;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.ParcelableException;
import android.os.PersistableBundle;
import android.os.Process;
import android.os.ProxyFileDescriptorCallback;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.os.storage.IObbActionListener;
import android.os.storage.IStorageEventListener;
import android.os.storage.IStorageManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.sysprop.VoldProperties;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.text.TextUtils;
import android.util.DataUnit;
import android.util.Log;
import android.util.Pair;
import android.util.Slog;
import android.util.SparseArray;
import android.util.TimeUtils;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.os.AppFuseMount;
import com.android.internal.os.FuseAppLoop;
import com.android.internal.os.FuseUnavailableMountException;
import com.android.internal.os.RoSystemProperties;
import com.android.internal.util.Preconditions;
import dalvik.system.BlockGuard;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public class StorageManager {
    public static final String ACTION_CLEAR_APP_CACHE = "android.os.storage.action.CLEAR_APP_CACHE";
    public static final String ACTION_MANAGE_STORAGE = "android.os.storage.action.MANAGE_STORAGE";
    public static final int CRYPT_TYPE_DEFAULT = 1;
    public static final int CRYPT_TYPE_PASSWORD = 0;
    public static final int CRYPT_TYPE_PATTERN = 2;
    public static final int CRYPT_TYPE_PIN = 3;
    public static final int DEBUG_ADOPTABLE_FORCE_OFF = 2;
    public static final int DEBUG_ADOPTABLE_FORCE_ON = 1;
    public static final int DEBUG_EMULATE_FBE = 4;
    public static final int DEBUG_ISOLATED_STORAGE_FORCE_OFF = 128;
    public static final int DEBUG_ISOLATED_STORAGE_FORCE_ON = 64;
    public static final int DEBUG_SDCARDFS_FORCE_OFF = 16;
    public static final int DEBUG_SDCARDFS_FORCE_ON = 8;
    public static final int DEBUG_VIRTUAL_DISK = 32;
    private static final int DEFAULT_CACHE_PERCENTAGE = 10;
    private static final int DEFAULT_THRESHOLD_PERCENTAGE = 5;
    public static final int ENCRYPTION_STATE_ERROR_CORRUPT = -4;
    public static final int ENCRYPTION_STATE_ERROR_INCOMPLETE = -2;
    public static final int ENCRYPTION_STATE_ERROR_INCONSISTENT = -3;
    public static final int ENCRYPTION_STATE_ERROR_UNKNOWN = -1;
    public static final int ENCRYPTION_STATE_NONE = 1;
    public static final int ENCRYPTION_STATE_OK = 0;
    public static final String EXTRA_REQUESTED_BYTES = "android.os.storage.extra.REQUESTED_BYTES";
    public static final String EXTRA_UUID = "android.os.storage.extra.UUID";

    @SystemApi
    public static final int FLAG_ALLOCATE_AGGRESSIVE = 1;
    public static final int FLAG_ALLOCATE_CACHE_ONLY = 16;
    public static final int FLAG_ALLOCATE_DEFY_ALL_RESERVED = 2;
    public static final int FLAG_ALLOCATE_DEFY_HALF_RESERVED = 4;
    public static final int FLAG_ALLOCATE_NON_CACHE_ONLY = 8;
    public static final int FLAG_FOR_WRITE = 256;
    public static final int FLAG_INCLUDE_INVISIBLE = 1024;
    public static final int FLAG_INCLUDE_RECENT = 2048;
    public static final int FLAG_REAL_STATE = 512;
    public static final int FLAG_STORAGE_CE = 2;
    public static final int FLAG_STORAGE_DE = 1;
    public static final int FLAG_STORAGE_EXTERNAL = 4;
    public static final int FSTRIM_FLAG_DEEP = 1;
    public static final String OWNER_INFO_KEY = "OwnerInfo";
    public static final String PASSWORD_VISIBLE_KEY = "PasswordVisible";
    public static final String PATTERN_VISIBLE_KEY = "PatternVisible";
    public static final int PROJECT_ID_EXT_DEFAULT = 1000;
    public static final int PROJECT_ID_EXT_MEDIA_AUDIO = 1001;
    public static final int PROJECT_ID_EXT_MEDIA_IMAGE = 1003;
    public static final int PROJECT_ID_EXT_MEDIA_VIDEO = 1002;
    public static final String PROP_ADOPTABLE = "persist.sys.adoptable";
    public static final String PROP_EMULATE_FBE = "persist.sys.emulate_fbe";
    public static final String PROP_FORCED_SCOPED_STORAGE_WHITELIST = "forced_scoped_storage_whitelist";
    public static final String PROP_FUSE = "persist.sys.fuse";
    public static final String PROP_HAS_ADOPTABLE = "vold.has_adoptable";
    public static final String PROP_HAS_RESERVED = "vold.has_reserved";
    public static final String PROP_ISOLATED_STORAGE = "persist.sys.isolated_storage";
    public static final String PROP_ISOLATED_STORAGE_SNAPSHOT = "sys.isolated_storage_snapshot";
    public static final String PROP_PRIMARY_PHYSICAL = "ro.vold.primary_physical";
    public static final String PROP_SDCARDFS = "persist.sys.sdcardfs";
    public static final String PROP_SETTINGS_FUSE = "persist.sys.fflag.override.settings_fuse";
    public static final String PROP_VIRTUAL_DISK = "persist.sys.virtual_disk";

    @SystemApi
    public static final int QUOTA_TYPE_MEDIA_AUDIO = 2;

    @SystemApi
    public static final int QUOTA_TYPE_MEDIA_IMAGE = 1;

    @SystemApi
    public static final int QUOTA_TYPE_MEDIA_NONE = 0;

    @SystemApi
    public static final int QUOTA_TYPE_MEDIA_VIDEO = 3;
    public static final String SYSTEM_LOCALE_KEY = "SystemLocale";
    public static final String UUID_PRIMARY_PHYSICAL = "primary_physical";
    public static final String UUID_SYSTEM = "system";
    private static final String XATTR_CACHE_GROUP = "user.cache_group";
    private static final String XATTR_CACHE_TOMBSTONE = "user.cache_tombstone";
    private final AppOpsManager mAppOps;
    private final Context mContext;
    private final Looper mLooper;
    private final ContentResolver mResolver;
    private static final String TAG = "StorageManager";
    private static final boolean LOCAL_LOGV = Log.isLoggable(TAG, 2);
    public static final String UUID_PRIVATE_INTERNAL = null;
    public static final UUID UUID_DEFAULT = UUID.fromString("41217664-9172-527a-b3d5-edabb50a7d69");
    public static final UUID UUID_PRIMARY_PHYSICAL_ = UUID.fromString("0f95a519-dae7-5abf-9519-fbd6209e05fd");
    public static final UUID UUID_SYSTEM_ = UUID.fromString("5d258386-e60d-59e3-826d-0089cdd42cc0");
    private static volatile IStorageManager sStorageManager = null;
    private static final long DEFAULT_THRESHOLD_MAX_BYTES = DataUnit.MEBIBYTES.toBytes(500);
    private static final long DEFAULT_CACHE_MAX_BYTES = DataUnit.GIBIBYTES.toBytes(5);
    private static final long DEFAULT_FULL_THRESHOLD_BYTES = DataUnit.MEBIBYTES.toBytes(1);
    private final AtomicInteger mNextNonce = new AtomicInteger(0);
    private final ArrayList<StorageEventListenerDelegate> mDelegates = new ArrayList<>();
    private final ObbActionListener mObbActionListener = new ObbActionListener();
    private final Object mFuseAppLoopLock = new Object();
    private FuseAppLoop mFuseAppLoop = null;
    private final IStorageManager mStorageManager = IStorageManager.Stub.asInterface(ServiceManager.getServiceOrThrow("mount"));

    @Retention(RetentionPolicy.SOURCE)
    public @interface AllocateFlags {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface QuotaType {
    }

    public static class StorageVolumeCallback {
        public void onStateChanged(StorageVolume storageVolume) {
        }
    }

    private static long getProjectIdForUser(int i, int i2) {
        return (i * UserHandle.PER_USER_RANGE) + i2;
    }

    @Deprecated
    public static File maybeTranslateEmulatedPathToInternal(File file) {
        return file;
    }

    private static native boolean setQuotaProjectId(String str, long j);

    @Deprecated
    public void disableUsbMassStorage() {
    }

    @Deprecated
    public void enableUsbMassStorage() {
    }

    @Deprecated
    public boolean isUsbMassStorageConnected() {
        return false;
    }

    @Deprecated
    public boolean isUsbMassStorageEnabled() {
        return false;
    }

    public File translateAppToSystem(File file, int i, int i2) {
        return file;
    }

    public File translateSystemToApp(File file, int i, int i2) {
        return file;
    }

    /* JADX INFO: Access modifiers changed from: private */
    class StorageEventListenerDelegate extends IStorageEventListener.Stub {
        final StorageVolumeCallback mCallback;
        final Executor mExecutor;
        final StorageEventListener mListener;

        public StorageEventListenerDelegate(Executor executor, StorageEventListener storageEventListener, StorageVolumeCallback storageVolumeCallback) {
            this.mExecutor = executor;
            this.mListener = storageEventListener;
            this.mCallback = storageVolumeCallback;
        }

        @Override // android.os.storage.IStorageEventListener
        public void onUsbMassStorageConnectionChanged(final boolean z) throws RemoteException {
            this.mExecutor.execute(new Runnable() { // from class: android.os.storage.-$$Lambda$StorageManager$StorageEventListenerDelegate$84LmRF7oEc5wW8YPuFH0tFCkLKE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onUsbMassStorageConnectionChanged$0$StorageManager$StorageEventListenerDelegate(z);
                }
            });
        }

        public /* synthetic */ void lambda$onUsbMassStorageConnectionChanged$0$StorageManager$StorageEventListenerDelegate(boolean z) {
            this.mListener.onUsbMassStorageConnectionChanged(z);
        }

        @Override // android.os.storage.IStorageEventListener
        public void onStorageStateChanged(final String str, final String str2, final String str3) {
            this.mExecutor.execute(new Runnable() { // from class: android.os.storage.-$$Lambda$StorageManager$StorageEventListenerDelegate$iFaeZuRy2mhY5v_pz2Ex8CviIV4
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onStorageStateChanged$1$StorageManager$StorageEventListenerDelegate(str, str2, str3);
                }
            });
        }

        public /* synthetic */ void lambda$onStorageStateChanged$1$StorageManager$StorageEventListenerDelegate(String str, String str2, String str3) {
            this.mListener.onStorageStateChanged(str, str2, str3);
            if (str != null) {
                for (StorageVolume storageVolume : StorageManager.this.getStorageVolumes()) {
                    if (Objects.equals(str, storageVolume.getPath())) {
                        this.mCallback.onStateChanged(storageVolume);
                    }
                }
            }
        }

        @Override // android.os.storage.IStorageEventListener
        public void onVolumeStateChanged(final VolumeInfo volumeInfo, final int i, final int i2) {
            this.mExecutor.execute(new Runnable() { // from class: android.os.storage.-$$Lambda$StorageManager$StorageEventListenerDelegate$vS7zBGYJgJjXzaKSy7tlVQdALZk
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onVolumeStateChanged$2$StorageManager$StorageEventListenerDelegate(volumeInfo, i, i2);
                }
            });
        }

        public /* synthetic */ void lambda$onVolumeStateChanged$2$StorageManager$StorageEventListenerDelegate(VolumeInfo volumeInfo, int i, int i2) {
            this.mListener.onVolumeStateChanged(volumeInfo, i, i2);
            File pathForUser = volumeInfo.getPathForUser(UserHandle.myUserId());
            if (pathForUser != null) {
                for (StorageVolume storageVolume : StorageManager.this.getStorageVolumes()) {
                    if (Objects.equals(pathForUser.getAbsolutePath(), storageVolume.getPath())) {
                        this.mCallback.onStateChanged(storageVolume);
                    }
                }
            }
        }

        @Override // android.os.storage.IStorageEventListener
        public void onVolumeRecordChanged(final VolumeRecord volumeRecord) {
            this.mExecutor.execute(new Runnable() { // from class: android.os.storage.-$$Lambda$StorageManager$StorageEventListenerDelegate$0oQ_6nHj7u6ks-TfuxydXRlrwJY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onVolumeRecordChanged$3$StorageManager$StorageEventListenerDelegate(volumeRecord);
                }
            });
        }

        public /* synthetic */ void lambda$onVolumeRecordChanged$3$StorageManager$StorageEventListenerDelegate(VolumeRecord volumeRecord) {
            this.mListener.onVolumeRecordChanged(volumeRecord);
        }

        @Override // android.os.storage.IStorageEventListener
        public void onVolumeForgotten(final String str) {
            this.mExecutor.execute(new Runnable() { // from class: android.os.storage.-$$Lambda$StorageManager$StorageEventListenerDelegate$ecnlqAlEy0uz8qZah68cQjKCyyg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onVolumeForgotten$4$StorageManager$StorageEventListenerDelegate(str);
                }
            });
        }

        public /* synthetic */ void lambda$onVolumeForgotten$4$StorageManager$StorageEventListenerDelegate(String str) {
            this.mListener.onVolumeForgotten(str);
        }

        @Override // android.os.storage.IStorageEventListener
        public void onDiskScanned(final DiskInfo diskInfo, final int i) {
            this.mExecutor.execute(new Runnable() { // from class: android.os.storage.-$$Lambda$StorageManager$StorageEventListenerDelegate$xwytzXsFsI1_JMvEhlHTWnV_KyA
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDiskScanned$5$StorageManager$StorageEventListenerDelegate(diskInfo, i);
                }
            });
        }

        public /* synthetic */ void lambda$onDiskScanned$5$StorageManager$StorageEventListenerDelegate(DiskInfo diskInfo, int i) {
            this.mListener.onDiskScanned(diskInfo, i);
        }

        @Override // android.os.storage.IStorageEventListener
        public void onDiskDestroyed(final DiskInfo diskInfo) throws RemoteException {
            this.mExecutor.execute(new Runnable() { // from class: android.os.storage.-$$Lambda$StorageManager$StorageEventListenerDelegate$UAJBt2jFHyVaCY8QU9wgn-yvkyY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDiskDestroyed$6$StorageManager$StorageEventListenerDelegate(diskInfo);
                }
            });
        }

        public /* synthetic */ void lambda$onDiskDestroyed$6$StorageManager$StorageEventListenerDelegate(DiskInfo diskInfo) {
            this.mListener.onDiskDestroyed(diskInfo);
        }
    }

    private class ObbActionListener extends IObbActionListener.Stub {
        private SparseArray<ObbListenerDelegate> mListeners;

        private ObbActionListener() {
            this.mListeners = new SparseArray<>();
        }

        @Override // android.os.storage.IObbActionListener
        public void onObbResult(String str, int i, int i2) {
            ObbListenerDelegate obbListenerDelegate;
            synchronized (this.mListeners) {
                obbListenerDelegate = this.mListeners.get(i);
                if (obbListenerDelegate != null) {
                    this.mListeners.remove(i);
                }
            }
            if (obbListenerDelegate != null) {
                obbListenerDelegate.sendObbStateChanged(str, i2);
            }
        }

        public int addListener(OnObbStateChangeListener onObbStateChangeListener) {
            ObbListenerDelegate obbListenerDelegate = StorageManager.this.new ObbListenerDelegate(onObbStateChangeListener);
            synchronized (this.mListeners) {
                this.mListeners.put(obbListenerDelegate.nonce, obbListenerDelegate);
            }
            return obbListenerDelegate.nonce;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getNextNonce() {
        return this.mNextNonce.getAndIncrement();
    }

    private class ObbListenerDelegate {
        private final Handler mHandler;
        private final WeakReference<OnObbStateChangeListener> mObbEventListenerRef;
        private final int nonce;

        ObbListenerDelegate(OnObbStateChangeListener onObbStateChangeListener) {
            this.nonce = StorageManager.this.getNextNonce();
            this.mObbEventListenerRef = new WeakReference<>(onObbStateChangeListener);
            this.mHandler = new Handler(StorageManager.this.mLooper) { // from class: android.os.storage.StorageManager.ObbListenerDelegate.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    OnObbStateChangeListener listener = ObbListenerDelegate.this.getListener();
                    if (listener == null) {
                        return;
                    }
                    listener.onObbStateChange((String) message.obj, message.arg1);
                }
            };
        }

        OnObbStateChangeListener getListener() {
            WeakReference<OnObbStateChangeListener> weakReference = this.mObbEventListenerRef;
            if (weakReference == null) {
                return null;
            }
            return weakReference.get();
        }

        void sendObbStateChanged(String str, int i) {
            this.mHandler.obtainMessage(0, i, 0, str).sendToTarget();
        }
    }

    @Deprecated
    public static StorageManager from(Context context) {
        return (StorageManager) context.getSystemService(StorageManager.class);
    }

    public StorageManager(Context context, Looper looper) throws ServiceManager.ServiceNotFoundException {
        this.mContext = context;
        this.mResolver = context.getContentResolver();
        this.mLooper = looper;
        this.mAppOps = (AppOpsManager) context.getSystemService(AppOpsManager.class);
    }

    public void registerListener(StorageEventListener storageEventListener) {
        synchronized (this.mDelegates) {
            StorageEventListenerDelegate storageEventListenerDelegate = new StorageEventListenerDelegate(this.mContext.getMainExecutor(), storageEventListener, new StorageVolumeCallback());
            try {
                this.mStorageManager.registerListener(storageEventListenerDelegate);
                this.mDelegates.add(storageEventListenerDelegate);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void unregisterListener(StorageEventListener storageEventListener) {
        synchronized (this.mDelegates) {
            Iterator<StorageEventListenerDelegate> it = this.mDelegates.iterator();
            while (it.hasNext()) {
                StorageEventListenerDelegate next = it.next();
                if (next.mListener == storageEventListener) {
                    try {
                        this.mStorageManager.unregisterListener(next);
                        it.remove();
                    } catch (RemoteException e) {
                        throw e.rethrowFromSystemServer();
                    }
                }
            }
        }
    }

    public void registerStorageVolumeCallback(Executor executor, StorageVolumeCallback storageVolumeCallback) {
        synchronized (this.mDelegates) {
            StorageEventListenerDelegate storageEventListenerDelegate = new StorageEventListenerDelegate(executor, new StorageEventListener(), storageVolumeCallback);
            try {
                this.mStorageManager.registerListener(storageEventListenerDelegate);
                this.mDelegates.add(storageEventListenerDelegate);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void unregisterStorageVolumeCallback(StorageVolumeCallback storageVolumeCallback) {
        synchronized (this.mDelegates) {
            Iterator<StorageEventListenerDelegate> it = this.mDelegates.iterator();
            while (it.hasNext()) {
                StorageEventListenerDelegate next = it.next();
                if (next.mCallback == storageVolumeCallback) {
                    try {
                        this.mStorageManager.unregisterListener(next);
                        it.remove();
                    } catch (RemoteException e) {
                        throw e.rethrowFromSystemServer();
                    }
                }
            }
        }
    }

    public boolean mountObb(String str, String str2, OnObbStateChangeListener onObbStateChangeListener) {
        Preconditions.checkNotNull(str, "rawPath cannot be null");
        Preconditions.checkNotNull(onObbStateChangeListener, "listener cannot be null");
        try {
            String canonicalPath = new File(str).getCanonicalPath();
            this.mStorageManager.mountObb(str, canonicalPath, str2, this.mObbActionListener, this.mObbActionListener.addListener(onObbStateChangeListener), getObbInfo(canonicalPath));
            return true;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (IOException e2) {
            throw new IllegalArgumentException("Failed to resolve path: " + str, e2);
        }
    }

    private ObbInfo getObbInfo(String str) {
        try {
            return ObbScanner.getObbInfo(str);
        } catch (IOException e) {
            throw new IllegalArgumentException("Couldn't get OBB info for " + str, e);
        }
    }

    public boolean unmountObb(String str, boolean z, OnObbStateChangeListener onObbStateChangeListener) {
        Preconditions.checkNotNull(str, "rawPath cannot be null");
        Preconditions.checkNotNull(onObbStateChangeListener, "listener cannot be null");
        try {
            this.mStorageManager.unmountObb(str, z, this.mObbActionListener, this.mObbActionListener.addListener(onObbStateChangeListener));
            return true;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isObbMounted(String str) {
        Preconditions.checkNotNull(str, "rawPath cannot be null");
        try {
            return this.mStorageManager.isObbMounted(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public String getMountedObbPath(String str) {
        Preconditions.checkNotNull(str, "rawPath cannot be null");
        try {
            return this.mStorageManager.getMountedObbPath(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<DiskInfo> getDisks() {
        try {
            return Arrays.asList(this.mStorageManager.getDisks());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public DiskInfo findDiskById(String str) {
        Preconditions.checkNotNull(str);
        for (DiskInfo diskInfo : getDisks()) {
            if (Objects.equals(diskInfo.id, str)) {
                return diskInfo;
            }
        }
        return null;
    }

    public VolumeInfo findVolumeById(String str) {
        Preconditions.checkNotNull(str);
        for (VolumeInfo volumeInfo : getVolumes()) {
            if (Objects.equals(volumeInfo.id, str)) {
                return volumeInfo;
            }
        }
        return null;
    }

    public VolumeInfo findVolumeByUuid(String str) {
        Preconditions.checkNotNull(str);
        for (VolumeInfo volumeInfo : getVolumes()) {
            if (Objects.equals(volumeInfo.fsUuid, str)) {
                return volumeInfo;
            }
        }
        return null;
    }

    public VolumeRecord findRecordByUuid(String str) {
        Preconditions.checkNotNull(str);
        for (VolumeRecord volumeRecord : getVolumeRecords()) {
            if (Objects.equals(volumeRecord.fsUuid, str)) {
                return volumeRecord;
            }
        }
        return null;
    }

    public VolumeInfo findPrivateForEmulated(VolumeInfo volumeInfo) {
        if (volumeInfo == null) {
            return null;
        }
        String id = volumeInfo.getId();
        int iIndexOf = id.indexOf(";");
        if (iIndexOf != -1) {
            id = id.substring(0, iIndexOf);
        }
        return findVolumeById(id.replace(VolumeInfo.ID_EMULATED_INTERNAL, VolumeInfo.ID_PRIVATE_INTERNAL));
    }

    public VolumeInfo findEmulatedForPrivate(VolumeInfo volumeInfo) {
        if (volumeInfo != null) {
            return findVolumeById(volumeInfo.getId().replace(VolumeInfo.ID_PRIVATE_INTERNAL, VolumeInfo.ID_EMULATED_INTERNAL) + ";" + this.mContext.getUserId());
        }
        return null;
    }

    public VolumeInfo findVolumeByQualifiedUuid(String str) {
        if (Objects.equals(UUID_PRIVATE_INTERNAL, str)) {
            return findVolumeById(VolumeInfo.ID_PRIVATE_INTERNAL);
        }
        if (Objects.equals(UUID_PRIMARY_PHYSICAL, str)) {
            return getPrimaryPhysicalVolume();
        }
        return findVolumeByUuid(str);
    }

    public UUID getUuidForPath(File file) throws IOException {
        Preconditions.checkNotNull(file);
        String canonicalPath = file.getCanonicalPath();
        if (FileUtils.contains(Environment.getDataDirectory().getAbsolutePath(), canonicalPath)) {
            return UUID_DEFAULT;
        }
        try {
            VolumeInfo[] volumes = this.mStorageManager.getVolumes(0);
            int length = volumes.length;
            for (int i = 0; i < length; i++) {
                VolumeInfo volumeInfo = volumes[i];
                if (volumeInfo.path != null && FileUtils.contains(volumeInfo.path, canonicalPath) && volumeInfo.type != 0 && volumeInfo.type != 5) {
                    try {
                        return convert(volumeInfo.fsUuid);
                    } catch (IllegalArgumentException unused) {
                        continue;
                    }
                }
            }
            throw new FileNotFoundException("Failed to find a storage device for " + file);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public File findPathForUuid(String str) throws FileNotFoundException {
        VolumeInfo volumeInfoFindVolumeByQualifiedUuid = findVolumeByQualifiedUuid(str);
        if (volumeInfoFindVolumeByQualifiedUuid != null) {
            return volumeInfoFindVolumeByQualifiedUuid.getPath();
        }
        throw new FileNotFoundException("Failed to find a storage device for " + str);
    }

    public boolean isAllocationSupported(FileDescriptor fileDescriptor) {
        try {
            getUuidForPath(ParcelFileDescriptor.getFile(fileDescriptor));
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public List<VolumeInfo> getVolumes() {
        try {
            return Arrays.asList(this.mStorageManager.getVolumes(0));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<VolumeInfo> getWritablePrivateVolumes() {
        try {
            ArrayList arrayList = new ArrayList();
            for (VolumeInfo volumeInfo : this.mStorageManager.getVolumes(0)) {
                if (volumeInfo.getType() == 1 && volumeInfo.isMountedWritable()) {
                    arrayList.add(volumeInfo);
                }
            }
            return arrayList;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<VolumeRecord> getVolumeRecords() {
        try {
            return Arrays.asList(this.mStorageManager.getVolumeRecords(0));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public String getBestVolumeDescription(VolumeInfo volumeInfo) {
        VolumeRecord volumeRecordFindRecordByUuid;
        if (volumeInfo == null) {
            return null;
        }
        if (!TextUtils.isEmpty(volumeInfo.fsUuid) && (volumeRecordFindRecordByUuid = findRecordByUuid(volumeInfo.fsUuid)) != null && !TextUtils.isEmpty(volumeRecordFindRecordByUuid.nickname)) {
            return volumeRecordFindRecordByUuid.nickname;
        }
        if (!TextUtils.isEmpty(volumeInfo.getDescription())) {
            return volumeInfo.getDescription();
        }
        if (volumeInfo.disk != null) {
            return volumeInfo.disk.getDescription();
        }
        return null;
    }

    public VolumeInfo getPrimaryPhysicalVolume() {
        for (VolumeInfo volumeInfo : getVolumes()) {
            if (volumeInfo.isPrimaryPhysical()) {
                return volumeInfo;
            }
        }
        return null;
    }

    public void mount(String str) {
        try {
            this.mStorageManager.mount(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void unmount(String str) {
        try {
            this.mStorageManager.unmount(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void format(String str) {
        try {
            this.mStorageManager.format(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public long benchmark(String str) {
        final CompletableFuture completableFuture = new CompletableFuture();
        benchmark(str, new IVoldTaskListener.Stub() { // from class: android.os.storage.StorageManager.1
            @Override // android.os.IVoldTaskListener
            public void onStatus(int i, PersistableBundle persistableBundle) {
            }

            @Override // android.os.IVoldTaskListener
            public void onFinished(int i, PersistableBundle persistableBundle) {
                completableFuture.complete(persistableBundle);
            }
        });
        try {
            return ((PersistableBundle) completableFuture.get(3L, TimeUnit.MINUTES)).getLong("run", Long.MAX_VALUE) * TimeUtils.NANOS_PER_MS;
        } catch (Exception unused) {
            return Long.MAX_VALUE;
        }
    }

    public void benchmark(String str, IVoldTaskListener iVoldTaskListener) {
        try {
            this.mStorageManager.benchmark(str, iVoldTaskListener);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void partitionPublic(String str) {
        try {
            this.mStorageManager.partitionPublic(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void partitionPrivate(String str) {
        try {
            this.mStorageManager.partitionPrivate(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void partitionMixed(String str, int i) {
        try {
            this.mStorageManager.partitionMixed(str, i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void wipeAdoptableDisks() {
        for (DiskInfo diskInfo : getDisks()) {
            String id = diskInfo.getId();
            if (diskInfo.isAdoptable()) {
                Slog.d(TAG, "Found adoptable " + id + "; wiping");
                try {
                    this.mStorageManager.partitionPublic(id);
                } catch (Exception e) {
                    Slog.w(TAG, "Failed to wipe " + id + ", but soldiering onward", e);
                }
            } else {
                Slog.d(TAG, "Ignorning non-adoptable disk " + diskInfo.getId());
            }
        }
    }

    public void setVolumeNickname(String str, String str2) {
        try {
            this.mStorageManager.setVolumeNickname(str, str2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setVolumeInited(String str, boolean z) {
        try {
            this.mStorageManager.setVolumeUserFlags(str, z ? 1 : 0, 1);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setVolumeSnoozed(String str, boolean z) {
        try {
            this.mStorageManager.setVolumeUserFlags(str, z ? 2 : 0, 2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void forgetVolume(String str) {
        try {
            this.mStorageManager.forgetVolume(str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public String getPrimaryStorageUuid() {
        try {
            return this.mStorageManager.getPrimaryStorageUuid();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setPrimaryStorageUuid(String str, IPackageMoveObserver iPackageMoveObserver) {
        try {
            this.mStorageManager.setPrimaryStorageUuid(str, iPackageMoveObserver);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public StorageVolume getStorageVolume(File file) {
        return getStorageVolume(getVolumeList(), file);
    }

    public StorageVolume getStorageVolume(Uri uri) throws Throwable {
        String volumeName = MediaStore.getVolumeName(uri);
        if (Objects.equals(volumeName, "external")) {
            Cursor cursorQuery = this.mContext.getContentResolver().query(uri, new String[]{"volume_name"}, null, null);
            try {
                if (cursorQuery.moveToFirst()) {
                    volumeName = cursorQuery.getString(0);
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        volumeName.hashCode();
        if (volumeName.equals("external_primary")) {
            return getPrimaryStorageVolume();
        }
        for (StorageVolume storageVolume : getStorageVolumes()) {
            if (Objects.equals(storageVolume.getMediaStoreVolumeName(), volumeName)) {
                return storageVolume;
            }
        }
        throw new IllegalStateException("Unknown volume for " + uri);
    }

    public static StorageVolume getStorageVolume(File file, int i) {
        return getStorageVolume(getVolumeList(i, 0), file);
    }

    private static StorageVolume getStorageVolume(StorageVolume[] storageVolumeArr, File file) {
        if (file == null) {
            return null;
        }
        String absolutePath = file.getAbsolutePath();
        if (absolutePath.startsWith(ContentResolver.DEPRECATE_DATA_PREFIX)) {
            return ((StorageManager) AppGlobals.getInitialApplication().getSystemService(StorageManager.class)).getStorageVolume(ContentResolver.translateDeprecatedDataPath(absolutePath));
        }
        try {
            File canonicalFile = file.getCanonicalFile();
            for (StorageVolume storageVolume : storageVolumeArr) {
                if (FileUtils.contains(storageVolume.getPathFile().getCanonicalFile(), canonicalFile)) {
                    return storageVolume;
                }
            }
            return null;
        } catch (IOException unused) {
            Slog.d(TAG, "Could not get canonical path for " + file);
            return null;
        }
    }

    @Deprecated
    public String getVolumeState(String str) {
        StorageVolume storageVolume = getStorageVolume(new File(str));
        return storageVolume != null ? storageVolume.getState() : "unknown";
    }

    public List<StorageVolume> getStorageVolumes() {
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, getVolumeList(this.mContext.getUserId(), 1536));
        return arrayList;
    }

    public List<StorageVolume> getRecentStorageVolumes() {
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, getVolumeList(this.mContext.getUserId(), 3584));
        return arrayList;
    }

    public StorageVolume getPrimaryStorageVolume() {
        return getVolumeList(this.mContext.getUserId(), 1536)[0];
    }

    public static Pair<String, Long> getPrimaryStoragePathAndSize() {
        return Pair.create(null, Long.valueOf(FileUtils.roundStorageSize(Environment.getDataDirectory().getTotalSpace() + Environment.getRootDirectory().getTotalSpace())));
    }

    public long getPrimaryStorageSize() {
        return FileUtils.roundStorageSize(Environment.getDataDirectory().getTotalSpace() + Environment.getRootDirectory().getTotalSpace());
    }

    public void mkdirs(File file) {
        BlockGuard.getVmPolicy().onPathAccess(file.getAbsolutePath());
        try {
            this.mStorageManager.mkdirs(this.mContext.getOpPackageName(), file.getAbsolutePath());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public StorageVolume[] getVolumeList() {
        return getVolumeList(this.mContext.getUserId(), 0);
    }

    public static StorageVolume[] getVolumeList(int i, int i2) {
        IStorageManager iStorageManagerAsInterface = IStorageManager.Stub.asInterface(ServiceManager.getService("mount"));
        try {
            String strCurrentOpPackageName = ActivityThread.currentOpPackageName();
            if (strCurrentOpPackageName == null) {
                String[] packagesForUid = ActivityThread.getPackageManager().getPackagesForUid(Process.myUid());
                if (packagesForUid != null && packagesForUid.length > 0) {
                    strCurrentOpPackageName = packagesForUid[0];
                }
                Log.w(TAG, "Missing package names; no storage volumes available");
                return new StorageVolume[0];
            }
            int packageUid = ActivityThread.getPackageManager().getPackageUid(strCurrentOpPackageName, 268435456, i);
            if (packageUid <= 0) {
                Log.w(TAG, "Missing UID; no storage volumes available");
                return new StorageVolume[0];
            }
            return iStorageManagerAsInterface.getVolumeList(packageUid, strCurrentOpPackageName, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public String[] getVolumePaths() {
        StorageVolume[] volumeList = getVolumeList();
        int length = volumeList.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = volumeList[i].getPath();
        }
        return strArr;
    }

    public StorageVolume getPrimaryVolume() {
        return getPrimaryVolume(getVolumeList());
    }

    public static StorageVolume getPrimaryVolume(StorageVolume[] storageVolumeArr) {
        for (StorageVolume storageVolume : storageVolumeArr) {
            if (storageVolume.isPrimary()) {
                return storageVolume;
            }
        }
        throw new IllegalStateException("Missing primary storage");
    }

    public long getStorageBytesUntilLow(File file) {
        return file.getUsableSpace() - getStorageFullBytes(file);
    }

    public long getStorageLowBytes(File file) {
        return Math.min((file.getTotalSpace() * ((long) Settings.Global.getInt(this.mResolver, Settings.Global.SYS_STORAGE_THRESHOLD_PERCENTAGE, 5))) / 100, Settings.Global.getLong(this.mResolver, Settings.Global.SYS_STORAGE_THRESHOLD_MAX_BYTES, DEFAULT_THRESHOLD_MAX_BYTES));
    }

    public long getStorageCacheBytes(File file, int i) {
        long jMin = Math.min((file.getTotalSpace() * ((long) Settings.Global.getInt(this.mResolver, Settings.Global.SYS_STORAGE_CACHE_PERCENTAGE, 10))) / 100, Settings.Global.getLong(this.mResolver, Settings.Global.SYS_STORAGE_CACHE_MAX_BYTES, DEFAULT_CACHE_MAX_BYTES));
        if ((i & 1) == 0 && (i & 2) == 0) {
            return (i & 4) != 0 ? jMin / 2 : jMin;
        }
        return 0L;
    }

    public long getStorageFullBytes(File file) {
        return Settings.Global.getLong(this.mResolver, Settings.Global.SYS_STORAGE_FULL_THRESHOLD_BYTES, DEFAULT_FULL_THRESHOLD_BYTES);
    }

    public void createUserKey(int i, int i2, boolean z) {
        try {
            this.mStorageManager.createUserKey(i, i2, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void destroyUserKey(int i) {
        try {
            this.mStorageManager.destroyUserKey(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void unlockUserKey(int i, int i2, byte[] bArr, byte[] bArr2) {
        try {
            this.mStorageManager.unlockUserKey(i, i2, bArr, bArr2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void lockUserKey(int i) {
        try {
            this.mStorageManager.lockUserKey(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void prepareUserStorage(String str, int i, int i2, int i3) {
        try {
            this.mStorageManager.prepareUserStorage(str, i, i2, i3);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void destroyUserStorage(String str, int i, int i2) {
        try {
            this.mStorageManager.destroyUserStorage(str, i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static boolean isUserKeyUnlocked(int i) {
        if (sStorageManager == null) {
            sStorageManager = IStorageManager.Stub.asInterface(ServiceManager.getService("mount"));
        }
        if (sStorageManager == null) {
            Slog.w(TAG, "Early during boot, assuming locked");
            return false;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            try {
                return sStorageManager.isUserKeyUnlocked(i);
            } catch (RemoteException e) {
                throw e.rethrowAsRuntimeException();
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean isEncrypted(File file) {
        if (FileUtils.contains(Environment.getDataDirectory(), file)) {
            return isEncrypted();
        }
        return FileUtils.contains(Environment.getExpandDirectory(), file);
    }

    public static boolean isEncryptable() {
        return RoSystemProperties.CRYPTO_ENCRYPTABLE;
    }

    public static boolean isEncrypted() {
        return RoSystemProperties.CRYPTO_ENCRYPTED;
    }

    public static boolean isFileEncryptedNativeOnly() {
        if (isEncrypted()) {
            return RoSystemProperties.CRYPTO_FILE_ENCRYPTED;
        }
        return false;
    }

    public static boolean isBlockEncrypted() {
        if (isEncrypted()) {
            return RoSystemProperties.CRYPTO_BLOCK_ENCRYPTED;
        }
        return false;
    }

    public static boolean isNonDefaultBlockEncrypted() {
        if (!isBlockEncrypted()) {
            return false;
        }
        try {
            return IStorageManager.Stub.asInterface(ServiceManager.getService("mount")).getPasswordType() != 1;
        } catch (RemoteException unused) {
            Log.e(TAG, "Error getting encryption type");
            return false;
        }
    }

    public static boolean isBlockEncrypting() {
        return !"".equalsIgnoreCase(VoldProperties.encrypt_progress().orElse(""));
    }

    public static boolean inCryptKeeperBounce() {
        return "trigger_restart_min_framework".equals(VoldProperties.decrypt().orElse(""));
    }

    public static boolean isFileEncryptedEmulatedOnly() {
        return SystemProperties.getBoolean(PROP_EMULATE_FBE, false);
    }

    public static boolean isFileEncryptedNativeOrEmulated() {
        return isFileEncryptedNativeOnly() || isFileEncryptedEmulatedOnly();
    }

    public static boolean hasAdoptable() {
        String str = SystemProperties.get(PROP_ADOPTABLE);
        str.hashCode();
        if (str.equals("force_on")) {
            return true;
        }
        if (str.equals("force_off")) {
            return false;
        }
        return SystemProperties.getBoolean(PROP_HAS_ADOPTABLE, false);
    }

    @SystemApi
    public static boolean hasIsolatedStorage() {
        return SystemProperties.getBoolean(PROP_ISOLATED_STORAGE_SNAPSHOT, SystemProperties.getBoolean(PROP_ISOLATED_STORAGE, true));
    }

    public static boolean checkPermissionAndAppOp(Context context, boolean z, int i, int i2, String str, String str2, String str3, int i3) {
        return checkPermissionAndAppOp(context, z, i, i2, str, str2, str3, i3, true);
    }

    public static boolean checkPermissionAndCheckOp(Context context, boolean z, int i, int i2, String str, String str2, int i3) {
        return checkPermissionAndAppOp(context, z, i, i2, str, null, str2, i3, false);
    }

    private static boolean checkPermissionAndAppOp(Context context, boolean z, int i, int i2, String str, String str2, String str3, int i3, boolean z2) {
        int iCheckOpNoThrow;
        if (context.checkPermission(str3, i, i2) != 0) {
            if (z) {
                throw new SecurityException("Permission " + str3 + " denied for package " + str);
            }
            return false;
        }
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        if (z2) {
            iCheckOpNoThrow = appOpsManager.noteOpNoThrow(i3, i2, str, str2, (String) null);
        } else {
            try {
                appOpsManager.checkPackage(i2, str);
                iCheckOpNoThrow = appOpsManager.checkOpNoThrow(i3, i2, str);
            } catch (SecurityException e) {
                if (z) {
                    throw e;
                }
                return false;
            }
        }
        if (iCheckOpNoThrow == 0) {
            return true;
        }
        if (iCheckOpNoThrow != 1 && iCheckOpNoThrow != 2 && iCheckOpNoThrow != 3) {
            throw new IllegalStateException(AppOpsManager.opToName(i3) + " has unknown mode " + AppOpsManager.modeToName(iCheckOpNoThrow));
        }
        if (z) {
            throw new SecurityException("Op " + AppOpsManager.opToName(i3) + " " + AppOpsManager.modeToName(iCheckOpNoThrow) + " for package " + str);
        }
        return false;
    }

    private boolean checkPermissionAndAppOp(boolean z, int i, int i2, String str, String str2, String str3, int i3) {
        return checkPermissionAndAppOp(this.mContext, z, i, i2, str, str2, str3, i3);
    }

    private boolean noteAppOpAllowingLegacy(boolean z, int i, int i2, String str, String str2, int i3) {
        int iNoteOpNoThrow = this.mAppOps.noteOpNoThrow(i3, i2, str, str2, (String) null);
        if (iNoteOpNoThrow == 0) {
            return true;
        }
        if (iNoteOpNoThrow == 1 || iNoteOpNoThrow == 2 || iNoteOpNoThrow == 3) {
            if (this.mAppOps.checkOpNoThrow(87, i2, str) == 0) {
                return true;
            }
            if (z) {
                throw new SecurityException("Op " + AppOpsManager.opToName(i3) + " " + AppOpsManager.modeToName(iNoteOpNoThrow) + " for package " + str);
            }
            return false;
        }
        throw new IllegalStateException(AppOpsManager.opToName(i3) + " has unknown mode " + AppOpsManager.modeToName(iNoteOpNoThrow));
    }

    public boolean checkPermissionReadAudio(boolean z, int i, int i2, String str, String str2) {
        if (checkExternalStoragePermissionAndAppOp(z, i, i2, str, str2, Manifest.permission.READ_EXTERNAL_STORAGE, 59)) {
            return noteAppOpAllowingLegacy(z, i, i2, str, str2, 81);
        }
        return false;
    }

    public boolean checkPermissionWriteAudio(boolean z, int i, int i2, String str, String str2) {
        if (checkExternalStoragePermissionAndAppOp(z, i, i2, str, str2, Manifest.permission.WRITE_EXTERNAL_STORAGE, 60)) {
            return noteAppOpAllowingLegacy(z, i, i2, str, str2, 82);
        }
        return false;
    }

    public boolean checkPermissionReadVideo(boolean z, int i, int i2, String str, String str2) {
        if (checkExternalStoragePermissionAndAppOp(z, i, i2, str, str2, Manifest.permission.READ_EXTERNAL_STORAGE, 59)) {
            return noteAppOpAllowingLegacy(z, i, i2, str, str2, 83);
        }
        return false;
    }

    public boolean checkPermissionWriteVideo(boolean z, int i, int i2, String str, String str2) {
        if (checkExternalStoragePermissionAndAppOp(z, i, i2, str, str2, Manifest.permission.WRITE_EXTERNAL_STORAGE, 60)) {
            return noteAppOpAllowingLegacy(z, i, i2, str, str2, 84);
        }
        return false;
    }

    public boolean checkPermissionReadImages(boolean z, int i, int i2, String str, String str2) {
        if (checkExternalStoragePermissionAndAppOp(z, i, i2, str, str2, Manifest.permission.READ_EXTERNAL_STORAGE, 59)) {
            return noteAppOpAllowingLegacy(z, i, i2, str, str2, 85);
        }
        return false;
    }

    public boolean checkPermissionWriteImages(boolean z, int i, int i2, String str, String str2) {
        if (checkExternalStoragePermissionAndAppOp(z, i, i2, str, str2, Manifest.permission.WRITE_EXTERNAL_STORAGE, 60)) {
            return noteAppOpAllowingLegacy(z, i, i2, str, str2, 86);
        }
        return false;
    }

    private boolean checkExternalStoragePermissionAndAppOp(boolean z, int i, int i2, String str, String str2, String str3, int i3) {
        int iNoteOpNoThrow = this.mAppOps.noteOpNoThrow(92, i2, str, str2, (String) null);
        if (iNoteOpNoThrow == 0) {
            return true;
        }
        if (iNoteOpNoThrow == 3 && this.mContext.checkPermission(Manifest.permission.MANAGE_EXTERNAL_STORAGE, i, i2) == 0) {
            return true;
        }
        return checkPermissionAndAppOp(z, i, i2, str, str2, str3, i3);
    }

    public ParcelFileDescriptor openProxyFileDescriptor(int i, ProxyFileDescriptorCallback proxyFileDescriptorCallback, Handler handler, ThreadFactory threadFactory) throws IOException {
        ParcelFileDescriptor parcelFileDescriptorOpenProxyFileDescriptor;
        Preconditions.checkNotNull(proxyFileDescriptorCallback);
        MetricsLogger.count(this.mContext, "storage_open_proxy_file_descriptor", 1);
        while (true) {
            try {
                synchronized (this.mFuseAppLoopLock) {
                    boolean z = false;
                    if (this.mFuseAppLoop == null) {
                        AppFuseMount appFuseMountMountProxyFileDescriptorBridge = this.mStorageManager.mountProxyFileDescriptorBridge();
                        if (appFuseMountMountProxyFileDescriptorBridge == null) {
                            throw new IOException("Failed to mount proxy bridge");
                        }
                        this.mFuseAppLoop = new FuseAppLoop(appFuseMountMountProxyFileDescriptorBridge.mountPointId, appFuseMountMountProxyFileDescriptorBridge.fd, threadFactory);
                        z = true;
                    }
                    if (handler == null) {
                        handler = new Handler(Looper.getMainLooper());
                    }
                    try {
                        int iRegisterCallback = this.mFuseAppLoop.registerCallback(proxyFileDescriptorCallback, handler);
                        parcelFileDescriptorOpenProxyFileDescriptor = this.mStorageManager.openProxyFileDescriptor(this.mFuseAppLoop.getMountPointId(), iRegisterCallback, i);
                        if (parcelFileDescriptorOpenProxyFileDescriptor == null) {
                            this.mFuseAppLoop.unregisterCallback(iRegisterCallback);
                            throw new FuseUnavailableMountException(this.mFuseAppLoop.getMountPointId());
                        }
                    } catch (FuseUnavailableMountException e) {
                        if (z) {
                            throw new IOException(e);
                        }
                        this.mFuseAppLoop = null;
                    }
                }
                return parcelFileDescriptorOpenProxyFileDescriptor;
            } catch (RemoteException e2) {
                throw new IOException(e2);
            }
        }
    }

    public ParcelFileDescriptor openProxyFileDescriptor(int i, ProxyFileDescriptorCallback proxyFileDescriptorCallback) throws IOException {
        return openProxyFileDescriptor(i, proxyFileDescriptorCallback, null, null);
    }

    public ParcelFileDescriptor openProxyFileDescriptor(int i, ProxyFileDescriptorCallback proxyFileDescriptorCallback, Handler handler) throws IOException {
        Preconditions.checkNotNull(handler);
        return openProxyFileDescriptor(i, proxyFileDescriptorCallback, handler, null);
    }

    public int getProxyFileDescriptorMountPointId() {
        int mountPointId;
        synchronized (this.mFuseAppLoopLock) {
            FuseAppLoop fuseAppLoop = this.mFuseAppLoop;
            mountPointId = fuseAppLoop != null ? fuseAppLoop.getMountPointId() : -1;
        }
        return mountPointId;
    }

    public long getCacheQuotaBytes(UUID uuid) throws Throwable {
        try {
            return this.mStorageManager.getCacheQuotaBytes(convert(uuid), this.mContext.getApplicationInfo().uid);
        } catch (ParcelableException e) {
            e.maybeRethrow(IOException.class);
            throw new RuntimeException(e);
        } catch (RemoteException e2) {
            throw e2.rethrowFromSystemServer();
        }
    }

    public long getCacheSizeBytes(UUID uuid) throws Throwable {
        try {
            return this.mStorageManager.getCacheSizeBytes(convert(uuid), this.mContext.getApplicationInfo().uid);
        } catch (ParcelableException e) {
            e.maybeRethrow(IOException.class);
            throw new RuntimeException(e);
        } catch (RemoteException e2) {
            throw e2.rethrowFromSystemServer();
        }
    }

    public long getAllocatableBytes(UUID uuid) throws IOException {
        return getAllocatableBytes(uuid, 0);
    }

    @SystemApi
    public long getAllocatableBytes(UUID uuid, int i) throws Throwable {
        try {
            return this.mStorageManager.getAllocatableBytes(convert(uuid), i, this.mContext.getOpPackageName());
        } catch (ParcelableException e) {
            e.maybeRethrow(IOException.class);
            throw new RuntimeException(e);
        } catch (RemoteException e2) {
            throw e2.rethrowFromSystemServer();
        }
    }

    public void allocateBytes(UUID uuid, long j) throws Throwable {
        allocateBytes(uuid, j, 0);
    }

    @SystemApi
    public void allocateBytes(UUID uuid, long j, int i) throws Throwable {
        try {
            this.mStorageManager.allocateBytes(convert(uuid), j, i, this.mContext.getOpPackageName());
        } catch (ParcelableException e) {
            e.maybeRethrow(IOException.class);
        } catch (RemoteException e2) {
            throw e2.rethrowFromSystemServer();
        }
    }

    public void allocateBytes(FileDescriptor fileDescriptor, long j) throws Throwable {
        allocateBytes(fileDescriptor, j, 0);
    }

    @SystemApi
    public void allocateBytes(FileDescriptor fileDescriptor, long j, int i) throws Throwable {
        File file = ParcelFileDescriptor.getFile(fileDescriptor);
        UUID uuidForPath = getUuidForPath(file);
        for (int i2 = 0; i2 < 3; i2++) {
            try {
                long j2 = j - (Os.fstat(fileDescriptor).st_blocks * 512);
                if (j2 > 0) {
                    allocateBytes(uuidForPath, j2, i);
                }
                try {
                    Os.posix_fallocate(fileDescriptor, 0L, j);
                    return;
                } catch (ErrnoException e) {
                    if (e.errno != OsConstants.ENOSYS && e.errno != OsConstants.ENOTSUP) {
                        throw e;
                    }
                    Log.w(TAG, "fallocate() not supported; falling back to ftruncate()");
                    Os.ftruncate(fileDescriptor, j);
                    return;
                }
            } catch (ErrnoException e2) {
                if (e2.errno == OsConstants.ENOSPC) {
                    Log.w(TAG, "Odd, not enough space; let's try again?");
                } else {
                    throw e2.rethrowAsIOException();
                }
            }
        }
        throw new IOException("Well this is embarassing; we can't allocate " + j + " for " + file);
    }

    @SystemApi
    public void updateExternalStorageFileQuotaType(File file, int i) throws IOException {
        long projectIdForUser;
        String canonicalPath = file.getCanonicalPath();
        StorageVolume storageVolume = getStorageVolume(file);
        if (storageVolume == null) {
            Log.w(TAG, "Failed to update quota type for " + canonicalPath);
            return;
        }
        if (storageVolume.isEmulated()) {
            int identifier = storageVolume.getOwner().getIdentifier();
            if (identifier < 0) {
                throw new IllegalStateException("Failed to update quota type for " + canonicalPath);
            }
            if (i == 0) {
                projectIdForUser = getProjectIdForUser(identifier, 1000);
            } else if (i == 1) {
                projectIdForUser = getProjectIdForUser(identifier, 1003);
            } else if (i == 2) {
                projectIdForUser = getProjectIdForUser(identifier, 1001);
            } else if (i == 3) {
                projectIdForUser = getProjectIdForUser(identifier, 1002);
            } else {
                throw new IllegalArgumentException("Invalid quota type: " + i);
            }
            if (!setQuotaProjectId(canonicalPath, projectIdForUser)) {
                throw new IOException("Failed to update quota type for " + canonicalPath);
            }
        }
    }

    public void fixupAppDir(File file) {
        try {
            this.mStorageManager.fixupAppDir(file.getCanonicalPath());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (IOException e2) {
            Log.e(TAG, "Failed to get canonical path for " + file.getPath(), e2);
        }
    }

    private static void setCacheBehavior(File file, String str, boolean z) throws IOException {
        if (!file.isDirectory()) {
            throw new IOException("Cache behavior can only be set on directories");
        }
        if (z) {
            try {
                Os.setxattr(file.getAbsolutePath(), str, "1".getBytes(StandardCharsets.UTF_8), 0);
            } catch (ErrnoException e) {
                throw e.rethrowAsIOException();
            }
        } else {
            try {
                Os.removexattr(file.getAbsolutePath(), str);
            } catch (ErrnoException e2) {
                if (e2.errno != OsConstants.ENODATA) {
                    throw e2.rethrowAsIOException();
                }
            }
        }
    }

    private static boolean isCacheBehavior(File file, String str) throws IOException {
        try {
            Os.getxattr(file.getAbsolutePath(), str);
            return true;
        } catch (ErrnoException e) {
            if (e.errno == OsConstants.ENODATA) {
                return false;
            }
            throw e.rethrowAsIOException();
        }
    }

    public void setCacheBehaviorGroup(File file, boolean z) throws IOException {
        setCacheBehavior(file, XATTR_CACHE_GROUP, z);
    }

    public boolean isCacheBehaviorGroup(File file) throws IOException {
        return isCacheBehavior(file, XATTR_CACHE_GROUP);
    }

    public void setCacheBehaviorTombstone(File file, boolean z) throws IOException {
        setCacheBehavior(file, XATTR_CACHE_TOMBSTONE, z);
    }

    public boolean isCacheBehaviorTombstone(File file) throws IOException {
        return isCacheBehavior(file, XATTR_CACHE_TOMBSTONE);
    }

    public static UUID convert(String str) {
        if (Objects.equals(str, UUID_PRIVATE_INTERNAL)) {
            return UUID_DEFAULT;
        }
        if (Objects.equals(str, UUID_PRIMARY_PHYSICAL)) {
            return UUID_PRIMARY_PHYSICAL_;
        }
        if (Objects.equals(str, "system")) {
            return UUID_SYSTEM_;
        }
        return UUID.fromString(str);
    }

    public static String convert(UUID uuid) {
        if (UUID_DEFAULT.equals(uuid)) {
            return UUID_PRIVATE_INTERNAL;
        }
        return UUID_PRIMARY_PHYSICAL_.equals(uuid) ? UUID_PRIMARY_PHYSICAL : UUID_SYSTEM_.equals(uuid) ? "system" : uuid.toString();
    }

    public boolean isCheckpointSupported() {
        try {
            return this.mStorageManager.supportsCheckpoint();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}
