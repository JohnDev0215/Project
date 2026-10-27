package android.content.pm;

import android.annotation.SystemApi;
import android.app.AppGlobals;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.IPackageInstallerCallback;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.FileBridge;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.ParcelableException;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.system.ErrnoException;
import android.system.Os;
import android.util.ArraySet;
import android.util.ExceptionUtils;
import com.android.internal.util.IndentingPrintWriter;
import com.android.internal.util.function.TriConsumer;
import com.android.internal.util.function.pooled.PooledLambda;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes.dex */
public class PackageInstaller {
    public static final String ACTION_CONFIRM_INSTALL = "android.content.pm.action.CONFIRM_INSTALL";
    public static final String ACTION_SESSION_COMMITTED = "android.content.pm.action.SESSION_COMMITTED";
    public static final String ACTION_SESSION_DETAILS = "android.content.pm.action.SESSION_DETAILS";
    public static final String ACTION_SESSION_UPDATED = "android.content.pm.action.SESSION_UPDATED";

    @SystemApi
    public static final int DATA_LOADER_TYPE_INCREMENTAL = 2;

    @SystemApi
    public static final int DATA_LOADER_TYPE_NONE = 0;

    @SystemApi
    public static final int DATA_LOADER_TYPE_STREAMING = 1;
    public static final boolean ENABLE_REVOCABLE_FD = SystemProperties.getBoolean("fw.revocable_fd", false);
    public static final String EXTRA_CALLBACK = "android.content.pm.extra.CALLBACK";

    @SystemApi
    public static final String EXTRA_DATA_LOADER_TYPE = "android.content.pm.extra.DATA_LOADER_TYPE";
    public static final String EXTRA_LEGACY_BUNDLE = "android.content.pm.extra.LEGACY_BUNDLE";
    public static final String EXTRA_LEGACY_STATUS = "android.content.pm.extra.LEGACY_STATUS";
    public static final String EXTRA_OTHER_PACKAGE_NAME = "android.content.pm.extra.OTHER_PACKAGE_NAME";
    public static final String EXTRA_PACKAGE_NAME = "android.content.pm.extra.PACKAGE_NAME";

    @Deprecated
    public static final String EXTRA_PACKAGE_NAMES = "android.content.pm.extra.PACKAGE_NAMES";
    public static final String EXTRA_SESSION = "android.content.pm.extra.SESSION";
    public static final String EXTRA_SESSION_ID = "android.content.pm.extra.SESSION_ID";
    public static final String EXTRA_STATUS = "android.content.pm.extra.STATUS";
    public static final String EXTRA_STATUS_MESSAGE = "android.content.pm.extra.STATUS_MESSAGE";
    public static final String EXTRA_STORAGE_PATH = "android.content.pm.extra.STORAGE_PATH";

    @SystemApi
    public static final int LOCATION_DATA_APP = 0;

    @SystemApi
    public static final int LOCATION_MEDIA_DATA = 2;

    @SystemApi
    public static final int LOCATION_MEDIA_OBB = 1;
    public static final int STATUS_FAILURE = 1;
    public static final int STATUS_FAILURE_ABORTED = 3;
    public static final int STATUS_FAILURE_BLOCKED = 2;
    public static final int STATUS_FAILURE_CONFLICT = 5;
    public static final int STATUS_FAILURE_INCOMPATIBLE = 7;
    public static final int STATUS_FAILURE_INVALID = 4;
    public static final int STATUS_FAILURE_STORAGE = 6;
    public static final int STATUS_PENDING_STREAMING = -2;
    public static final int STATUS_PENDING_USER_ACTION = -1;
    public static final int STATUS_SUCCESS = 0;
    private static final String TAG = "PackageInstaller";
    private final ArrayList<SessionCallbackDelegate> mDelegates = new ArrayList<>();
    private final IPackageInstaller mInstaller;
    private final String mInstallerPackageName;
    private final int mUserId;

    @Retention(RetentionPolicy.SOURCE)
    public @interface FileLocation {
    }

    public static abstract class SessionCallback {
        public abstract void onActiveChanged(int i, boolean z);

        public abstract void onBadgingChanged(int i);

        public abstract void onCreated(int i);

        public abstract void onFinished(int i, boolean z);

        public abstract void onProgressChanged(int i, float f);
    }

    public PackageInstaller(IPackageInstaller iPackageInstaller, String str, int i) {
        this.mInstaller = iPackageInstaller;
        this.mInstallerPackageName = str;
        this.mUserId = i;
    }

    public int createSession(SessionParams sessionParams) throws Throwable {
        try {
            return this.mInstaller.createSession(sessionParams, this.mInstallerPackageName, this.mUserId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (RuntimeException e2) {
            ExceptionUtils.maybeUnwrapIOException(e2);
            throw e2;
        }
    }

    public Session openSession(int i) throws Throwable {
        try {
            try {
                return new Session(this.mInstaller.openSession(i));
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } catch (RuntimeException e2) {
            ExceptionUtils.maybeUnwrapIOException(e2);
            throw e2;
        }
    }

    public void updateSessionAppIcon(int i, Bitmap bitmap) {
        try {
            this.mInstaller.updateSessionAppIcon(i, bitmap);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void updateSessionAppLabel(int i, CharSequence charSequence) {
        String string;
        if (charSequence != null) {
            try {
                string = charSequence.toString();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        } else {
            string = null;
        }
        this.mInstaller.updateSessionAppLabel(i, string);
    }

    public void abandonSession(int i) {
        try {
            this.mInstaller.abandonSession(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public SessionInfo getSessionInfo(int i) {
        try {
            return this.mInstaller.getSessionInfo(i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<SessionInfo> getAllSessions() {
        try {
            return this.mInstaller.getAllSessions(this.mUserId).getList();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<SessionInfo> getMySessions() {
        try {
            return this.mInstaller.getMySessions(this.mInstallerPackageName, this.mUserId).getList();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<SessionInfo> getStagedSessions() {
        try {
            return this.mInstaller.getStagedSessions().getList();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public SessionInfo getActiveStagedSession() {
        List<SessionInfo> activeStagedSessions = getActiveStagedSessions();
        if (activeStagedSessions.isEmpty()) {
            return null;
        }
        return activeStagedSessions.get(0);
    }

    public List<SessionInfo> getActiveStagedSessions() {
        ArrayList arrayList = new ArrayList();
        List<SessionInfo> stagedSessions = getStagedSessions();
        for (int i = 0; i < stagedSessions.size(); i++) {
            SessionInfo sessionInfo = stagedSessions.get(i);
            if (sessionInfo.isStagedSessionActive()) {
                arrayList.add(sessionInfo);
            }
        }
        return arrayList;
    }

    public void uninstall(String str, IntentSender intentSender) {
        uninstall(str, 0, intentSender);
    }

    public void uninstall(String str, int i, IntentSender intentSender) {
        uninstall(new VersionedPackage(str, -1), i, intentSender);
    }

    public void uninstall(VersionedPackage versionedPackage, IntentSender intentSender) {
        uninstall(versionedPackage, 0, intentSender);
    }

    public void uninstall(VersionedPackage versionedPackage, int i, IntentSender intentSender) {
        Objects.requireNonNull(versionedPackage, "versionedPackage cannot be null");
        try {
            this.mInstaller.uninstall(versionedPackage, this.mInstallerPackageName, i, intentSender, this.mUserId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void installExistingPackage(String str, int i, IntentSender intentSender) {
        Objects.requireNonNull(str, "packageName cannot be null");
        try {
            this.mInstaller.installExistingPackage(str, 4194304, i, intentSender, this.mUserId, null);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void uninstallExistingPackage(String str, IntentSender intentSender) {
        Objects.requireNonNull(str, "packageName cannot be null");
        try {
            this.mInstaller.uninstallExistingPackage(new VersionedPackage(str, -1), this.mInstallerPackageName, intentSender, this.mUserId);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setPermissionsResult(int i, boolean z) {
        try {
            this.mInstaller.setPermissionsResult(i, z);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    static class SessionCallbackDelegate extends IPackageInstallerCallback.Stub {
        private static final int MSG_SESSION_ACTIVE_CHANGED = 3;
        private static final int MSG_SESSION_BADGING_CHANGED = 2;
        private static final int MSG_SESSION_CREATED = 1;
        private static final int MSG_SESSION_FINISHED = 5;
        private static final int MSG_SESSION_PROGRESS_CHANGED = 4;
        final SessionCallback mCallback;
        final Executor mExecutor;

        SessionCallbackDelegate(SessionCallback sessionCallback, Executor executor) {
            this.mCallback = sessionCallback;
            this.mExecutor = executor;
        }

        @Override // android.content.pm.IPackageInstallerCallback
        public void onSessionCreated(int i) {
            this.mExecutor.execute(PooledLambda.obtainRunnable(new BiConsumer() { // from class: android.content.pm.-$$Lambda$ciir_QAmv6RwJro4I58t77dPnxU
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((PackageInstaller.SessionCallback) obj).onCreated(((Integer) obj2).intValue());
                }
            }, this.mCallback, Integer.valueOf(i)).recycleOnUse());
        }

        @Override // android.content.pm.IPackageInstallerCallback
        public void onSessionBadgingChanged(int i) {
            this.mExecutor.execute(PooledLambda.obtainRunnable(new BiConsumer() { // from class: android.content.pm.-$$Lambda$B12dZLpdwpXn89QSesmkaZjD72Q
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((PackageInstaller.SessionCallback) obj).onBadgingChanged(((Integer) obj2).intValue());
                }
            }, this.mCallback, Integer.valueOf(i)).recycleOnUse());
        }

        @Override // android.content.pm.IPackageInstallerCallback
        public void onSessionActiveChanged(int i, boolean z) {
            this.mExecutor.execute(PooledLambda.obtainRunnable(new TriConsumer() { // from class: android.content.pm.-$$Lambda$T1UQAuePWRRmVQ1KzTyMAktZUPM
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((PackageInstaller.SessionCallback) obj).onActiveChanged(((Integer) obj2).intValue(), ((Boolean) obj3).booleanValue());
                }
            }, this.mCallback, Integer.valueOf(i), Boolean.valueOf(z)).recycleOnUse());
        }

        @Override // android.content.pm.IPackageInstallerCallback
        public void onSessionProgressChanged(int i, float f) {
            this.mExecutor.execute(PooledLambda.obtainRunnable(new TriConsumer() { // from class: android.content.pm.-$$Lambda$n3uXeb1v-YRmq_BWTfosEqUUr9g
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((PackageInstaller.SessionCallback) obj).onProgressChanged(((Integer) obj2).intValue(), ((Float) obj3).floatValue());
                }
            }, this.mCallback, Integer.valueOf(i), Float.valueOf(f)).recycleOnUse());
        }

        @Override // android.content.pm.IPackageInstallerCallback
        public void onSessionFinished(int i, boolean z) {
            this.mExecutor.execute(PooledLambda.obtainRunnable(new TriConsumer() { // from class: android.content.pm.-$$Lambda$zO9HBUVgPeroyDQPLJE-MNMvSqc
                @Override // com.android.internal.util.function.TriConsumer
                public final void accept(Object obj, Object obj2, Object obj3) {
                    ((PackageInstaller.SessionCallback) obj).onFinished(((Integer) obj2).intValue(), ((Boolean) obj3).booleanValue());
                }
            }, this.mCallback, Integer.valueOf(i), Boolean.valueOf(z)).recycleOnUse());
        }
    }

    @Deprecated
    public void addSessionCallback(SessionCallback sessionCallback) {
        registerSessionCallback(sessionCallback);
    }

    public void registerSessionCallback(SessionCallback sessionCallback) {
        registerSessionCallback(sessionCallback, new Handler());
    }

    @Deprecated
    public void addSessionCallback(SessionCallback sessionCallback, Handler handler) {
        registerSessionCallback(sessionCallback, handler);
    }

    public void registerSessionCallback(SessionCallback sessionCallback, Handler handler) {
        synchronized (this.mDelegates) {
            SessionCallbackDelegate sessionCallbackDelegate = new SessionCallbackDelegate(sessionCallback, new HandlerExecutor(handler));
            try {
                this.mInstaller.registerCallback(sessionCallbackDelegate, this.mUserId);
                this.mDelegates.add(sessionCallbackDelegate);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    @Deprecated
    public void removeSessionCallback(SessionCallback sessionCallback) {
        unregisterSessionCallback(sessionCallback);
    }

    public void unregisterSessionCallback(SessionCallback sessionCallback) {
        synchronized (this.mDelegates) {
            Iterator<SessionCallbackDelegate> it = this.mDelegates.iterator();
            while (it.hasNext()) {
                SessionCallbackDelegate next = it.next();
                if (next.mCallback == sessionCallback) {
                    try {
                        this.mInstaller.unregisterCallback(next);
                        it.remove();
                    } catch (RemoteException e) {
                        throw e.rethrowFromSystemServer();
                    }
                }
            }
        }
    }

    public static class Session implements Closeable {
        protected final IPackageInstallerSession mSession;

        public Session(IPackageInstallerSession iPackageInstallerSession) {
            this.mSession = iPackageInstallerSession;
        }

        @Deprecated
        public void setProgress(float f) {
            setStagingProgress(f);
        }

        public void setStagingProgress(float f) {
            try {
                this.mSession.setClientProgress(f);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        public void addProgress(float f) {
            try {
                this.mSession.addClientProgress(f);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        public OutputStream openWrite(String str, long j, long j2) throws Throwable {
            try {
                if (PackageInstaller.ENABLE_REVOCABLE_FD) {
                    return new ParcelFileDescriptor.AutoCloseOutputStream(this.mSession.openWrite(str, j, j2));
                }
                return new FileBridge.FileBridgeOutputStream(this.mSession.openWrite(str, j, j2));
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            } catch (RuntimeException e2) {
                ExceptionUtils.maybeUnwrapIOException(e2);
                throw e2;
            }
        }

        public void write(String str, long j, long j2, ParcelFileDescriptor parcelFileDescriptor) throws Throwable {
            try {
                this.mSession.write(str, j, j2, parcelFileDescriptor);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            } catch (RuntimeException e2) {
                ExceptionUtils.maybeUnwrapIOException(e2);
                throw e2;
            }
        }

        public void fsync(OutputStream outputStream) throws IOException {
            if (PackageInstaller.ENABLE_REVOCABLE_FD) {
                if (outputStream instanceof ParcelFileDescriptor.AutoCloseOutputStream) {
                    try {
                        Os.fsync(((ParcelFileDescriptor.AutoCloseOutputStream) outputStream).getFD());
                        return;
                    } catch (ErrnoException e) {
                        throw e.rethrowAsIOException();
                    }
                }
                throw new IllegalArgumentException("Unrecognized stream");
            }
            if (outputStream instanceof FileBridge.FileBridgeOutputStream) {
                ((FileBridge.FileBridgeOutputStream) outputStream).fsync();
                return;
            }
            throw new IllegalArgumentException("Unrecognized stream");
        }

        public String[] getNames() throws Throwable {
            try {
                return this.mSession.getNames();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            } catch (RuntimeException e2) {
                ExceptionUtils.maybeUnwrapIOException(e2);
                throw e2;
            }
        }

        public InputStream openRead(String str) throws Throwable {
            try {
                return new ParcelFileDescriptor.AutoCloseInputStream(this.mSession.openRead(str));
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            } catch (RuntimeException e2) {
                ExceptionUtils.maybeUnwrapIOException(e2);
                throw e2;
            }
        }

        public void removeSplit(String str) throws Throwable {
            try {
                this.mSession.removeSplit(str);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            } catch (RuntimeException e2) {
                ExceptionUtils.maybeUnwrapIOException(e2);
                throw e2;
            }
        }

        @SystemApi
        public DataLoaderParams getDataLoaderParams() {
            try {
                DataLoaderParamsParcel dataLoaderParams = this.mSession.getDataLoaderParams();
                if (dataLoaderParams == null) {
                    return null;
                }
                return new DataLoaderParams(dataLoaderParams);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        @SystemApi
        public void addFile(int i, String str, long j, byte[] bArr, byte[] bArr2) {
            try {
                this.mSession.addFile(i, str, j, bArr, bArr2);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        @SystemApi
        public void removeFile(int i, String str) {
            try {
                this.mSession.removeFile(i, str);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        public void commit(IntentSender intentSender) {
            try {
                this.mSession.commit(intentSender, false);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        @SystemApi
        public void commitTransferred(IntentSender intentSender) {
            try {
                this.mSession.commit(intentSender, true);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        public void transfer(String str) throws Throwable {
            Objects.requireNonNull(str);
            try {
                this.mSession.transfer(str);
            } catch (ParcelableException e) {
                e.maybeRethrow(PackageManager.NameNotFoundException.class);
                throw new RuntimeException(e);
            } catch (RemoteException e2) {
                throw e2.rethrowFromSystemServer();
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            try {
                this.mSession.close();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        public void abandon() {
            try {
                this.mSession.abandon();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        public boolean isMultiPackage() {
            try {
                return this.mSession.isMultiPackage();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        public boolean isStaged() {
            try {
                return this.mSession.isStaged();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        public int getParentSessionId() {
            try {
                return this.mSession.getParentSessionId();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        public int[] getChildSessionIds() {
            try {
                return this.mSession.getChildSessionIds();
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }

        public void addChildSessionId(int i) {
            try {
                this.mSession.addChildSessionId(i);
            } catch (RemoteException e) {
                e.rethrowFromSystemServer();
            }
        }

        public void removeChildSessionId(int i) {
            try {
                this.mSession.removeChildSessionId(i);
            } catch (RemoteException e) {
                e.rethrowFromSystemServer();
            }
        }
    }

    public static class SessionParams implements Parcelable {
        public static final int MAX_PACKAGE_NAME_LENGTH = 255;
        public static final int MODE_FULL_INSTALL = 1;
        public static final int MODE_INHERIT_EXISTING = 2;
        public static final int MODE_INVALID = -1;
        public static final int UID_UNKNOWN = -1;
        public String abiOverride;
        public Bitmap appIcon;
        public long appIconLastModified;
        public String appLabel;
        public String appPackageName;
        public int autoRevokePermissionsMode;
        public DataLoaderParams dataLoaderParams;
        public boolean forceQueryableOverride;
        public String[] grantedRuntimePermissions;
        public int installFlags;
        public int installLocation;
        public int installReason;
        public String installerPackageName;
        public boolean isMultiPackage;
        public boolean isStaged;
        public int mode;
        public int originatingUid;
        public Uri originatingUri;
        public Uri referrerUri;
        public long requiredInstalledVersionCode;
        public int rollbackDataPolicy;
        public long sizeBytes;
        public String volumeUuid;
        public List<String> whitelistedRestrictedPermissions;
        public static final Set<String> RESTRICTED_PERMISSIONS_ALL = new ArraySet();
        public static final Parcelable.Creator<SessionParams> CREATOR = new Parcelable.Creator<SessionParams>() { // from class: android.content.pm.PackageInstaller.SessionParams.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SessionParams createFromParcel(Parcel parcel) {
                return new SessionParams(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SessionParams[] newArray(int i) {
                return new SessionParams[i];
            }
        };

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public SessionParams(int i) {
            this.mode = -1;
            this.installFlags = 4194304;
            this.installLocation = 1;
            this.installReason = 0;
            this.sizeBytes = -1L;
            this.appIconLastModified = -1L;
            this.originatingUid = -1;
            this.autoRevokePermissionsMode = 3;
            this.requiredInstalledVersionCode = -1L;
            this.rollbackDataPolicy = 0;
            this.mode = i;
        }

        public SessionParams(Parcel parcel) {
            this.mode = -1;
            this.installFlags = 4194304;
            this.installLocation = 1;
            this.installReason = 0;
            this.sizeBytes = -1L;
            this.appIconLastModified = -1L;
            this.originatingUid = -1;
            this.autoRevokePermissionsMode = 3;
            this.requiredInstalledVersionCode = -1L;
            this.rollbackDataPolicy = 0;
            this.mode = parcel.readInt();
            this.installFlags = parcel.readInt();
            this.installLocation = parcel.readInt();
            this.installReason = parcel.readInt();
            this.sizeBytes = parcel.readLong();
            this.appPackageName = parcel.readString();
            this.appIcon = (Bitmap) parcel.readParcelable(null);
            this.appLabel = parcel.readString();
            this.originatingUri = (Uri) parcel.readParcelable(null);
            this.originatingUid = parcel.readInt();
            this.referrerUri = (Uri) parcel.readParcelable(null);
            this.abiOverride = parcel.readString();
            this.volumeUuid = parcel.readString();
            this.grantedRuntimePermissions = parcel.readStringArray();
            this.whitelistedRestrictedPermissions = parcel.createStringArrayList();
            this.autoRevokePermissionsMode = parcel.readInt();
            this.installerPackageName = parcel.readString();
            this.isMultiPackage = parcel.readBoolean();
            this.isStaged = parcel.readBoolean();
            this.forceQueryableOverride = parcel.readBoolean();
            this.requiredInstalledVersionCode = parcel.readLong();
            DataLoaderParamsParcel dataLoaderParamsParcel = (DataLoaderParamsParcel) parcel.readParcelable(DataLoaderParamsParcel.class.getClassLoader());
            if (dataLoaderParamsParcel != null) {
                this.dataLoaderParams = new DataLoaderParams(dataLoaderParamsParcel);
            }
            this.rollbackDataPolicy = parcel.readInt();
        }

        public SessionParams copy() {
            SessionParams sessionParams = new SessionParams(this.mode);
            sessionParams.installFlags = this.installFlags;
            sessionParams.installLocation = this.installLocation;
            sessionParams.installReason = this.installReason;
            sessionParams.sizeBytes = this.sizeBytes;
            sessionParams.appPackageName = this.appPackageName;
            sessionParams.appIcon = this.appIcon;
            sessionParams.appLabel = this.appLabel;
            sessionParams.originatingUri = this.originatingUri;
            sessionParams.originatingUid = this.originatingUid;
            sessionParams.referrerUri = this.referrerUri;
            sessionParams.abiOverride = this.abiOverride;
            sessionParams.volumeUuid = this.volumeUuid;
            sessionParams.grantedRuntimePermissions = this.grantedRuntimePermissions;
            sessionParams.whitelistedRestrictedPermissions = this.whitelistedRestrictedPermissions;
            sessionParams.autoRevokePermissionsMode = this.autoRevokePermissionsMode;
            sessionParams.installerPackageName = this.installerPackageName;
            sessionParams.isMultiPackage = this.isMultiPackage;
            sessionParams.isStaged = this.isStaged;
            sessionParams.forceQueryableOverride = this.forceQueryableOverride;
            sessionParams.requiredInstalledVersionCode = this.requiredInstalledVersionCode;
            sessionParams.dataLoaderParams = this.dataLoaderParams;
            sessionParams.rollbackDataPolicy = this.rollbackDataPolicy;
            return sessionParams;
        }

        public boolean areHiddenOptionsSet() {
            int i = this.installFlags;
            return ((1169536 & i) == i && this.abiOverride == null && this.volumeUuid == null) ? false : true;
        }

        public void setInstallLocation(int i) {
            this.installLocation = i;
        }

        public void setSize(long j) {
            this.sizeBytes = j;
        }

        public void setAppPackageName(String str) {
            this.appPackageName = str;
        }

        public void setAppIcon(Bitmap bitmap) {
            this.appIcon = bitmap;
        }

        public void setAppLabel(CharSequence charSequence) {
            this.appLabel = charSequence != null ? charSequence.toString() : null;
        }

        public void setOriginatingUri(Uri uri) {
            this.originatingUri = uri;
        }

        public void setOriginatingUid(int i) {
            this.originatingUid = i;
        }

        public void setReferrerUri(Uri uri) {
            this.referrerUri = uri;
        }

        @SystemApi
        public void setGrantedRuntimePermissions(String[] strArr) {
            this.installFlags |= 256;
            this.grantedRuntimePermissions = strArr;
        }

        public void setWhitelistedRestrictedPermissions(Set<String> set) {
            if (set == RESTRICTED_PERMISSIONS_ALL) {
                this.installFlags |= 4194304;
                this.whitelistedRestrictedPermissions = null;
            } else {
                this.installFlags &= -4194305;
                this.whitelistedRestrictedPermissions = set != null ? new ArrayList(set) : null;
            }
        }

        public void setAutoRevokePermissionsMode(boolean z) {
            this.autoRevokePermissionsMode = !z ? 1 : 0;
        }

        @SystemApi
        public void setEnableRollback(boolean z) {
            if (z) {
                this.installFlags |= 262144;
            } else {
                this.installFlags &= -262145;
            }
            this.rollbackDataPolicy = 0;
        }

        @SystemApi
        public void setEnableRollback(boolean z, int i) {
            if (z) {
                this.installFlags |= 262144;
            } else {
                this.installFlags &= -262145;
            }
            this.rollbackDataPolicy = i;
        }

        @SystemApi
        @Deprecated
        public void setAllowDowngrade(boolean z) {
            setRequestDowngrade(z);
        }

        @SystemApi
        public void setRequestDowngrade(boolean z) {
            if (z) {
                this.installFlags |= 128;
            } else {
                this.installFlags &= -129;
            }
        }

        public void setRequiredInstalledVersionCode(long j) {
            this.requiredInstalledVersionCode = j;
        }

        public void setInstallFlagsForcePermissionPrompt() {
            this.installFlags |= 1024;
        }

        @SystemApi
        public void setDontKillApp(boolean z) {
            if (z) {
                this.installFlags |= 4096;
            } else {
                this.installFlags &= -4097;
            }
        }

        @SystemApi
        public void setInstallAsInstantApp(boolean z) {
            if (z) {
                int i = this.installFlags | 2048;
                this.installFlags = i;
                this.installFlags = i & (-16385);
            } else {
                int i2 = this.installFlags & (-2049);
                this.installFlags = i2;
                this.installFlags = i2 | 16384;
            }
        }

        @SystemApi
        public void setInstallAsVirtualPreload() {
            this.installFlags |= 65536;
        }

        public void setInstallReason(int i) {
            this.installReason = i;
        }

        @SystemApi
        public void setAllocateAggressive(boolean z) {
            if (z) {
                this.installFlags |= 32768;
            } else {
                this.installFlags &= -32769;
            }
        }

        public void setInstallerPackageName(String str) {
            this.installerPackageName = str;
        }

        public void setMultiPackage() {
            this.isMultiPackage = true;
        }

        @SystemApi
        public void setStaged() {
            this.isStaged = true;
        }

        @SystemApi
        public void setInstallAsApex() {
            this.installFlags |= 131072;
        }

        public boolean getEnableRollback() {
            return (this.installFlags & 262144) != 0;
        }

        @SystemApi
        public void setDataLoaderParams(DataLoaderParams dataLoaderParams) {
            this.dataLoaderParams = dataLoaderParams;
        }

        public void setForceQueryable() {
            this.forceQueryableOverride = true;
        }

        public void dump(IndentingPrintWriter indentingPrintWriter) {
            indentingPrintWriter.printPair("mode", Integer.valueOf(this.mode));
            indentingPrintWriter.printHexPair("installFlags", this.installFlags);
            indentingPrintWriter.printPair("installLocation", Integer.valueOf(this.installLocation));
            indentingPrintWriter.printPair("sizeBytes", Long.valueOf(this.sizeBytes));
            indentingPrintWriter.printPair("appPackageName", this.appPackageName);
            indentingPrintWriter.printPair("appIcon", Boolean.valueOf(this.appIcon != null));
            indentingPrintWriter.printPair("appLabel", this.appLabel);
            indentingPrintWriter.printPair("originatingUri", this.originatingUri);
            indentingPrintWriter.printPair("originatingUid", Integer.valueOf(this.originatingUid));
            indentingPrintWriter.printPair("referrerUri", this.referrerUri);
            indentingPrintWriter.printPair("abiOverride", this.abiOverride);
            indentingPrintWriter.printPair("volumeUuid", this.volumeUuid);
            indentingPrintWriter.printPair("grantedRuntimePermissions", (Object[]) this.grantedRuntimePermissions);
            indentingPrintWriter.printPair("whitelistedRestrictedPermissions", this.whitelistedRestrictedPermissions);
            indentingPrintWriter.printPair("autoRevokePermissions", Integer.valueOf(this.autoRevokePermissionsMode));
            indentingPrintWriter.printPair("installerPackageName", this.installerPackageName);
            indentingPrintWriter.printPair("isMultiPackage", Boolean.valueOf(this.isMultiPackage));
            indentingPrintWriter.printPair("isStaged", Boolean.valueOf(this.isStaged));
            indentingPrintWriter.printPair("forceQueryable", Boolean.valueOf(this.forceQueryableOverride));
            indentingPrintWriter.printPair("requiredInstalledVersionCode", Long.valueOf(this.requiredInstalledVersionCode));
            indentingPrintWriter.printPair("dataLoaderParams", this.dataLoaderParams);
            indentingPrintWriter.printPair("rollbackDataPolicy", Integer.valueOf(this.rollbackDataPolicy));
            indentingPrintWriter.println();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mode);
            parcel.writeInt(this.installFlags);
            parcel.writeInt(this.installLocation);
            parcel.writeInt(this.installReason);
            parcel.writeLong(this.sizeBytes);
            parcel.writeString(this.appPackageName);
            parcel.writeParcelable(this.appIcon, i);
            parcel.writeString(this.appLabel);
            parcel.writeParcelable(this.originatingUri, i);
            parcel.writeInt(this.originatingUid);
            parcel.writeParcelable(this.referrerUri, i);
            parcel.writeString(this.abiOverride);
            parcel.writeString(this.volumeUuid);
            parcel.writeStringArray(this.grantedRuntimePermissions);
            parcel.writeStringList(this.whitelistedRestrictedPermissions);
            parcel.writeInt(this.autoRevokePermissionsMode);
            parcel.writeString(this.installerPackageName);
            parcel.writeBoolean(this.isMultiPackage);
            parcel.writeBoolean(this.isStaged);
            parcel.writeBoolean(this.forceQueryableOverride);
            parcel.writeLong(this.requiredInstalledVersionCode);
            DataLoaderParams dataLoaderParams = this.dataLoaderParams;
            if (dataLoaderParams != null) {
                parcel.writeParcelable(dataLoaderParams.getData(), i);
            } else {
                parcel.writeParcelable(null, i);
            }
            parcel.writeInt(this.rollbackDataPolicy);
        }
    }

    public static class SessionInfo implements Parcelable {
        public static final int INVALID_ID = -1;
        public static final int STAGED_SESSION_ACTIVATION_FAILED = 2;
        public static final int STAGED_SESSION_NO_ERROR = 0;
        public static final int STAGED_SESSION_UNKNOWN = 3;
        public static final int STAGED_SESSION_VERIFICATION_FAILED = 1;
        public boolean active;
        public Bitmap appIcon;
        public CharSequence appLabel;
        public String appPackageName;
        public int autoRevokePermissionsMode;
        public int[] childSessionIds;
        public long createdMillis;
        public boolean forceQueryable;
        public String[] grantedRuntimePermissions;
        public int installFlags;
        public int installLocation;
        public int installReason;
        public String installerPackageName;
        public boolean isCommitted;
        public boolean isMultiPackage;
        public boolean isStaged;
        public boolean isStagedSessionApplied;
        public boolean isStagedSessionFailed;
        public boolean isStagedSessionReady;
        private int mStagedSessionErrorCode;
        private String mStagedSessionErrorMessage;
        public int mode;
        public int originatingUid;
        public Uri originatingUri;
        public int parentSessionId;
        public float progress;
        public Uri referrerUri;
        public String resolvedBaseCodePath;
        public int rollbackDataPolicy;
        public boolean sealed;
        public int sessionId;
        public long sizeBytes;
        public long updatedMillis;
        public int userId;
        public List<String> whitelistedRestrictedPermissions;
        private static final int[] NO_SESSIONS = new int[0];
        public static final Parcelable.Creator<SessionInfo> CREATOR = new Parcelable.Creator<SessionInfo>() { // from class: android.content.pm.PackageInstaller.SessionInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SessionInfo createFromParcel(Parcel parcel) {
                return new SessionInfo(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SessionInfo[] newArray(int i) {
                return new SessionInfo[i];
            }
        };

        @Retention(RetentionPolicy.SOURCE)
        public @interface StagedSessionErrorCode {
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public SessionInfo() {
            this.autoRevokePermissionsMode = 3;
            this.parentSessionId = -1;
            this.childSessionIds = NO_SESSIONS;
        }

        public SessionInfo(Parcel parcel) {
            this.autoRevokePermissionsMode = 3;
            this.parentSessionId = -1;
            this.childSessionIds = NO_SESSIONS;
            this.sessionId = parcel.readInt();
            this.userId = parcel.readInt();
            this.installerPackageName = parcel.readString();
            this.resolvedBaseCodePath = parcel.readString();
            this.progress = parcel.readFloat();
            this.sealed = parcel.readInt() != 0;
            this.active = parcel.readInt() != 0;
            this.mode = parcel.readInt();
            this.installReason = parcel.readInt();
            this.sizeBytes = parcel.readLong();
            this.appPackageName = parcel.readString();
            this.appIcon = (Bitmap) parcel.readParcelable(null);
            this.appLabel = parcel.readString();
            this.installLocation = parcel.readInt();
            this.originatingUri = (Uri) parcel.readParcelable(null);
            this.originatingUid = parcel.readInt();
            this.referrerUri = (Uri) parcel.readParcelable(null);
            this.grantedRuntimePermissions = parcel.readStringArray();
            this.whitelistedRestrictedPermissions = parcel.createStringArrayList();
            this.autoRevokePermissionsMode = parcel.readInt();
            this.installFlags = parcel.readInt();
            this.isMultiPackage = parcel.readBoolean();
            this.isStaged = parcel.readBoolean();
            this.forceQueryable = parcel.readBoolean();
            this.parentSessionId = parcel.readInt();
            int[] iArrCreateIntArray = parcel.createIntArray();
            this.childSessionIds = iArrCreateIntArray;
            if (iArrCreateIntArray == null) {
                this.childSessionIds = NO_SESSIONS;
            }
            this.isStagedSessionApplied = parcel.readBoolean();
            this.isStagedSessionReady = parcel.readBoolean();
            this.isStagedSessionFailed = parcel.readBoolean();
            this.mStagedSessionErrorCode = parcel.readInt();
            this.mStagedSessionErrorMessage = parcel.readString();
            this.isCommitted = parcel.readBoolean();
            this.rollbackDataPolicy = parcel.readInt();
            this.createdMillis = parcel.readLong();
        }

        public int getSessionId() {
            return this.sessionId;
        }

        public UserHandle getUser() {
            return new UserHandle(this.userId);
        }

        public String getInstallerPackageName() {
            return this.installerPackageName;
        }

        public float getProgress() {
            return this.progress;
        }

        public boolean isActive() {
            return this.active;
        }

        public boolean isSealed() {
            return this.sealed;
        }

        public int getInstallReason() {
            return this.installReason;
        }

        @Deprecated
        public boolean isOpen() {
            return isActive();
        }

        public String getAppPackageName() {
            return this.appPackageName;
        }

        public Bitmap getAppIcon() {
            if (this.appIcon == null) {
                try {
                    SessionInfo sessionInfo = AppGlobals.getPackageManager().getPackageInstaller().getSessionInfo(this.sessionId);
                    this.appIcon = sessionInfo != null ? sessionInfo.appIcon : null;
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
            return this.appIcon;
        }

        public CharSequence getAppLabel() {
            return this.appLabel;
        }

        public Intent createDetailsIntent() {
            Intent intent = new Intent(PackageInstaller.ACTION_SESSION_DETAILS);
            intent.putExtra(PackageInstaller.EXTRA_SESSION_ID, this.sessionId);
            intent.setPackage(this.installerPackageName);
            intent.setFlags(268435456);
            return intent;
        }

        public int getMode() {
            return this.mode;
        }

        public int getInstallLocation() {
            return this.installLocation;
        }

        public long getSize() {
            return this.sizeBytes;
        }

        public Uri getOriginatingUri() {
            return this.originatingUri;
        }

        public int getOriginatingUid() {
            return this.originatingUid;
        }

        public Uri getReferrerUri() {
            return this.referrerUri;
        }

        @SystemApi
        public String[] getGrantedRuntimePermissions() {
            return this.grantedRuntimePermissions;
        }

        @SystemApi
        public Set<String> getWhitelistedRestrictedPermissions() {
            if ((this.installFlags & 4194304) != 0) {
                return SessionParams.RESTRICTED_PERMISSIONS_ALL;
            }
            if (this.whitelistedRestrictedPermissions != null) {
                return new ArraySet(this.whitelistedRestrictedPermissions);
            }
            return Collections.emptySet();
        }

        @SystemApi
        public int getAutoRevokePermissionsMode() {
            return this.autoRevokePermissionsMode;
        }

        @SystemApi
        @Deprecated
        public boolean getAllowDowngrade() {
            return getRequestDowngrade();
        }

        @SystemApi
        public boolean getRequestDowngrade() {
            return (this.installFlags & 128) != 0;
        }

        @SystemApi
        public boolean getDontKillApp() {
            return (this.installFlags & 4096) != 0;
        }

        @SystemApi
        public boolean getInstallAsInstantApp(boolean z) {
            return (this.installFlags & 2048) != 0;
        }

        @SystemApi
        public boolean getInstallAsFullApp(boolean z) {
            return (this.installFlags & 16384) != 0;
        }

        @SystemApi
        public boolean getInstallAsVirtualPreload() {
            return (this.installFlags & 65536) != 0;
        }

        @SystemApi
        public boolean getEnableRollback() {
            return (this.installFlags & 262144) != 0;
        }

        @SystemApi
        public boolean getAllocateAggressive() {
            return (this.installFlags & 32768) != 0;
        }

        @Deprecated
        public Intent getDetailsIntent() {
            return createDetailsIntent();
        }

        public boolean isMultiPackage() {
            return this.isMultiPackage;
        }

        public boolean isStaged() {
            return this.isStaged;
        }

        @SystemApi
        public int getRollbackDataPolicy() {
            return this.rollbackDataPolicy;
        }

        public boolean isForceQueryable() {
            return this.forceQueryable;
        }

        public boolean isStagedSessionActive() {
            return (!this.isStaged || !this.isCommitted || this.isStagedSessionApplied || this.isStagedSessionFailed || hasParentSessionId()) ? false : true;
        }

        public int getParentSessionId() {
            return this.parentSessionId;
        }

        public boolean hasParentSessionId() {
            return this.parentSessionId != -1;
        }

        public int[] getChildSessionIds() {
            return this.childSessionIds;
        }

        private void checkSessionIsStaged() {
            if (!this.isStaged) {
                throw new IllegalStateException("Session is not marked as staged.");
            }
        }

        public boolean isStagedSessionApplied() {
            checkSessionIsStaged();
            return this.isStagedSessionApplied;
        }

        public boolean isStagedSessionReady() {
            checkSessionIsStaged();
            return this.isStagedSessionReady;
        }

        public boolean isStagedSessionFailed() {
            checkSessionIsStaged();
            return this.isStagedSessionFailed;
        }

        public int getStagedSessionErrorCode() {
            checkSessionIsStaged();
            return this.mStagedSessionErrorCode;
        }

        public String getStagedSessionErrorMessage() {
            checkSessionIsStaged();
            return this.mStagedSessionErrorMessage;
        }

        public void setStagedSessionErrorCode(int i, String str) {
            this.mStagedSessionErrorCode = i;
            this.mStagedSessionErrorMessage = str;
        }

        public boolean isCommitted() {
            return this.isCommitted;
        }

        public long getCreatedMillis() {
            return this.createdMillis;
        }

        public long getUpdatedMillis() {
            return this.updatedMillis;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.sessionId);
            parcel.writeInt(this.userId);
            parcel.writeString(this.installerPackageName);
            parcel.writeString(this.resolvedBaseCodePath);
            parcel.writeFloat(this.progress);
            parcel.writeInt(this.sealed ? 1 : 0);
            parcel.writeInt(this.active ? 1 : 0);
            parcel.writeInt(this.mode);
            parcel.writeInt(this.installReason);
            parcel.writeLong(this.sizeBytes);
            parcel.writeString(this.appPackageName);
            parcel.writeParcelable(this.appIcon, i);
            CharSequence charSequence = this.appLabel;
            parcel.writeString(charSequence != null ? charSequence.toString() : null);
            parcel.writeInt(this.installLocation);
            parcel.writeParcelable(this.originatingUri, i);
            parcel.writeInt(this.originatingUid);
            parcel.writeParcelable(this.referrerUri, i);
            parcel.writeStringArray(this.grantedRuntimePermissions);
            parcel.writeStringList(this.whitelistedRestrictedPermissions);
            parcel.writeInt(this.autoRevokePermissionsMode);
            parcel.writeInt(this.installFlags);
            parcel.writeBoolean(this.isMultiPackage);
            parcel.writeBoolean(this.isStaged);
            parcel.writeBoolean(this.forceQueryable);
            parcel.writeInt(this.parentSessionId);
            parcel.writeIntArray(this.childSessionIds);
            parcel.writeBoolean(this.isStagedSessionApplied);
            parcel.writeBoolean(this.isStagedSessionReady);
            parcel.writeBoolean(this.isStagedSessionFailed);
            parcel.writeInt(this.mStagedSessionErrorCode);
            parcel.writeString(this.mStagedSessionErrorMessage);
            parcel.writeBoolean(this.isCommitted);
            parcel.writeInt(this.rollbackDataPolicy);
            parcel.writeLong(this.createdMillis);
        }
    }
}
