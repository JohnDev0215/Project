package android.permission;

import android.annotation.SystemApi;
import android.app.ActivityManager;
import android.app.ActivityThread;
import android.app.IActivityManager;
import android.app.PropertyInvalidatedCache;
import android.content.Context;
import android.content.pm.IPackageManager;
import android.content.pm.permission.SplitPermissionInfoParcelable;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.UserHandle;
import android.permission.IPermissionManager;
import android.util.Slog;
import com.android.internal.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
@SystemApi
public final class PermissionManager {
    public static final String CACHE_KEY_PACKAGE_INFO = "cache_key.package_info";
    public static final String KILL_APP_REASON_GIDS_CHANGED = "permission grant or revoke changed gids";
    public static final String KILL_APP_REASON_PERMISSIONS_REVOKED = "permissions revoked";
    private static final String TAG = PermissionManager.class.getName();
    private static PropertyInvalidatedCache<PackageNamePermissionQuery, Integer> sPackageNamePermissionCache;
    private static final PropertyInvalidatedCache<PermissionQuery, Integer> sPermissionCache;
    private final Context mContext;
    private final IPackageManager mPackageManager;
    private final IPermissionManager mPermissionManager;
    private List<SplitPermissionInfo> mSplitPermissionInfos;

    static {
        int i = 16;
        String str = CACHE_KEY_PACKAGE_INFO;
        sPermissionCache = new PropertyInvalidatedCache<PermissionQuery, Integer>(i, str) { // from class: android.permission.PermissionManager.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.app.PropertyInvalidatedCache
            public Integer recompute(PermissionQuery permissionQuery) {
                return Integer.valueOf(PermissionManager.checkPermissionUncached(permissionQuery.permission, permissionQuery.pid, permissionQuery.uid));
            }
        };
        sPackageNamePermissionCache = new PropertyInvalidatedCache<PackageNamePermissionQuery, Integer>(i, str) { // from class: android.permission.PermissionManager.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.app.PropertyInvalidatedCache
            public Integer recompute(PackageNamePermissionQuery packageNamePermissionQuery) {
                return Integer.valueOf(PermissionManager.checkPackageNamePermissionUncached(packageNamePermissionQuery.permName, packageNamePermissionQuery.pkgName, packageNamePermissionQuery.uid));
            }
        };
    }

    public PermissionManager(Context context, IPackageManager iPackageManager) throws ServiceManager.ServiceNotFoundException {
        this(context, iPackageManager, IPermissionManager.Stub.asInterface(ServiceManager.getServiceOrThrow("permissionmgr")));
    }

    public PermissionManager(Context context, IPackageManager iPackageManager, IPermissionManager iPermissionManager) {
        this.mContext = context;
        this.mPackageManager = iPackageManager;
        this.mPermissionManager = iPermissionManager;
    }

    @SystemApi
    public int getRuntimePermissionsVersion() {
        try {
            return this.mPackageManager.getRuntimePermissionsVersion(this.mContext.getUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setRuntimePermissionsVersion(int i) {
        try {
            this.mPackageManager.setRuntimePermissionsVersion(i, this.mContext.getUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public List<SplitPermissionInfo> getSplitPermissions() {
        List<SplitPermissionInfo> list = this.mSplitPermissionInfos;
        if (list != null) {
            return list;
        }
        try {
            List<SplitPermissionInfo> listSplitPermissionInfoListToNonParcelableList = splitPermissionInfoListToNonParcelableList(ActivityThread.getPermissionManager().getSplitPermissions());
            this.mSplitPermissionInfos = listSplitPermissionInfoListToNonParcelableList;
            return listSplitPermissionInfoListToNonParcelableList;
        } catch (RemoteException e) {
            Slog.e(TAG, "Error getting split permissions", e);
            return Collections.emptyList();
        }
    }

    public void grantDefaultPermissionsToLuiApp(String str, UserHandle userHandle, Executor executor, final Consumer<Boolean> consumer) {
        try {
            this.mPermissionManager.grantDefaultPermissionsToActiveLuiApp(str, userHandle.getIdentifier());
            executor.execute(new Runnable() { // from class: android.permission.-$$Lambda$PermissionManager$0l4DEq2HXCCVCBUfdxUjWk3qCIU
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(true);
                }
            });
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void revokeDefaultPermissionsFromLuiApps(String[] strArr, UserHandle userHandle, Executor executor, final Consumer<Boolean> consumer) {
        try {
            this.mPermissionManager.revokeDefaultPermissionsFromLuiApps(strArr, userHandle.getIdentifier());
            executor.execute(new Runnable() { // from class: android.permission.-$$Lambda$PermissionManager$06sjxg82u271BU6DexEeXFtA39s
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(true);
                }
            });
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void grantDefaultPermissionsToEnabledImsServices(String[] strArr, UserHandle userHandle, Executor executor, final Consumer<Boolean> consumer) {
        try {
            this.mPermissionManager.grantDefaultPermissionsToEnabledImsServices(strArr, userHandle.getIdentifier());
            executor.execute(new Runnable() { // from class: android.permission.-$$Lambda$PermissionManager$614OrkjHJQ8919GHESV4U3WEYEY
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(true);
                }
            });
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void grantDefaultPermissionsToEnabledTelephonyDataServices(String[] strArr, UserHandle userHandle, Executor executor, final Consumer<Boolean> consumer) {
        try {
            this.mPermissionManager.grantDefaultPermissionsToEnabledTelephonyDataServices(strArr, userHandle.getIdentifier());
            executor.execute(new Runnable() { // from class: android.permission.-$$Lambda$PermissionManager$akRjCijSz__ZCVgA58pgpRUIzHU
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(true);
                }
            });
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void revokeDefaultPermissionsFromDisabledTelephonyDataServices(String[] strArr, UserHandle userHandle, Executor executor, final Consumer<Boolean> consumer) {
        try {
            this.mPermissionManager.revokeDefaultPermissionsFromDisabledTelephonyDataServices(strArr, userHandle.getIdentifier());
            executor.execute(new Runnable() { // from class: android.permission.-$$Lambda$PermissionManager$DhpssISwP0aRU4I94NcrcBz5vI0
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(true);
                }
            });
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    public void grantDefaultPermissionsToEnabledCarrierApps(String[] strArr, UserHandle userHandle, Executor executor, final Consumer<Boolean> consumer) {
        try {
            this.mPermissionManager.grantDefaultPermissionsToEnabledCarrierApps(strArr, userHandle.getIdentifier());
            executor.execute(new Runnable() { // from class: android.permission.-$$Lambda$PermissionManager$FyB4K5KfEKqwp-uJUXvNLbTkmAg
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(true);
                }
            });
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public Set<String> getAutoRevokeExemptionRequestedPackages() {
        try {
            return CollectionUtils.toSet(this.mPermissionManager.getAutoRevokeExemptionRequestedPackages(this.mContext.getUser().getIdentifier()));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public Set<String> getAutoRevokeExemptionGrantedPackages() {
        try {
            return CollectionUtils.toSet(this.mPermissionManager.getAutoRevokeExemptionGrantedPackages(this.mContext.getUser().getIdentifier()));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private List<SplitPermissionInfo> splitPermissionInfoListToNonParcelableList(List<SplitPermissionInfoParcelable> list) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new SplitPermissionInfo(list.get(i)));
        }
        return arrayList;
    }

    public static List<SplitPermissionInfoParcelable> splitPermissionInfoListToParcelableList(List<SplitPermissionInfo> list) {
        int size = list.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            SplitPermissionInfo splitPermissionInfo = list.get(i);
            arrayList.add(new SplitPermissionInfoParcelable(splitPermissionInfo.getSplitPermission(), splitPermissionInfo.getNewPermissions(), splitPermissionInfo.getTargetSdk()));
        }
        return arrayList;
    }

    public static final class SplitPermissionInfo {
        private final SplitPermissionInfoParcelable mSplitPermissionInfoParcelable;

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.mSplitPermissionInfoParcelable.equals(((SplitPermissionInfo) obj).mSplitPermissionInfoParcelable);
        }

        public int hashCode() {
            return this.mSplitPermissionInfoParcelable.hashCode();
        }

        public String getSplitPermission() {
            return this.mSplitPermissionInfoParcelable.getSplitPermission();
        }

        public List<String> getNewPermissions() {
            return this.mSplitPermissionInfoParcelable.getNewPermissions();
        }

        public int getTargetSdk() {
            return this.mSplitPermissionInfoParcelable.getTargetSdk();
        }

        public SplitPermissionInfo(String str, List<String> list, int i) {
            this(new SplitPermissionInfoParcelable(str, list, i));
        }

        private SplitPermissionInfo(SplitPermissionInfoParcelable splitPermissionInfoParcelable) {
            this.mSplitPermissionInfoParcelable = splitPermissionInfoParcelable;
        }
    }

    @SystemApi
    public void startOneTimePermissionSession(String str, long j, int i, int i2) {
        try {
            this.mPermissionManager.startOneTimePermissionSession(str, this.mContext.getUserId(), j, i, i2);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void stopOneTimePermissionSession(String str) {
        try {
            this.mPermissionManager.stopOneTimePermissionSession(str, this.mContext.getUserId());
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public int checkDeviceIdentifierAccess(String str, String str2, String str3, int i, int i2) {
        try {
            return this.mPermissionManager.checkDeviceIdentifierAccess(str, str2, str3, i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int checkPermissionUncached(String str, int i, int i2) {
        IActivityManager service = ActivityManager.getService();
        if (service == null) {
            int appId = UserHandle.getAppId(i2);
            if (appId == 0 || appId == 1000) {
                Slog.w(TAG, "Missing ActivityManager; assuming " + i2 + " holds " + str);
                return 0;
            }
            Slog.w(TAG, "Missing ActivityManager; assuming " + i2 + " does not hold " + str);
            return -1;
        }
        try {
            return service.checkPermission(str, i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private static final class PermissionQuery {
        final String permission;
        final int pid;
        final int uid;

        PermissionQuery(String str, int i, int i2) {
            this.permission = str;
            this.pid = i;
            this.uid = i2;
        }

        public String toString() {
            return String.format("PermissionQuery(permission=\"%s\", pid=%s, uid=%s)", this.permission, Integer.valueOf(this.pid), Integer.valueOf(this.uid));
        }

        public int hashCode() {
            return (Objects.hashCode(this.permission) * 13) + Objects.hashCode(Integer.valueOf(this.uid));
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            try {
                PermissionQuery permissionQuery = (PermissionQuery) obj;
                return this.uid == permissionQuery.uid && Objects.equals(this.permission, permissionQuery.permission);
            } catch (ClassCastException unused) {
                return false;
            }
        }
    }

    public static int checkPermission(String str, int i, int i2) {
        return sPermissionCache.query(new PermissionQuery(str, i, i2)).intValue();
    }

    public static void disablePermissionCache() {
        sPermissionCache.disableLocal();
    }

    private static final class PackageNamePermissionQuery {
        final String permName;
        final String pkgName;
        final int uid;

        PackageNamePermissionQuery(String str, String str2, int i) {
            this.permName = str;
            this.pkgName = str2;
            this.uid = i;
        }

        public String toString() {
            return String.format("PackageNamePermissionQuery(pkgName=\"%s\", permName=\"%s, uid=%s\")", this.pkgName, this.permName, Integer.valueOf(this.uid));
        }

        public int hashCode() {
            return Objects.hash(this.permName, this.pkgName, Integer.valueOf(this.uid));
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            try {
                PackageNamePermissionQuery packageNamePermissionQuery = (PackageNamePermissionQuery) obj;
                return Objects.equals(this.permName, packageNamePermissionQuery.permName) && Objects.equals(this.pkgName, packageNamePermissionQuery.pkgName) && this.uid == packageNamePermissionQuery.uid;
            } catch (ClassCastException unused) {
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int checkPackageNamePermissionUncached(String str, String str2, int i) {
        try {
            return ActivityThread.getPermissionManager().checkPermission(str, str2, i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static int checkPackageNamePermission(String str, String str2, int i) {
        return sPackageNamePermissionCache.query(new PackageNamePermissionQuery(str, str2, i)).intValue();
    }

    public static void disablePackageNamePermissionCache() {
        sPackageNamePermissionCache.disableLocal();
    }
}
