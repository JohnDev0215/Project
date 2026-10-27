package com.android.car.settings.applications.specialaccess;

import android.app.AppGlobals;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.IPackageManager;
import android.content.pm.PackageInfo;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.ArrayMap;
import android.util.SparseArray;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import com.android.car.settings.applications.specialaccess.AppEntryListManager;
import com.android.car.settings.common.Logger;
import com.android.internal.util.ArrayUtils;
import com.android.settingslib.applications.ApplicationsState;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class AppStateAppOpsBridge implements AppEntryListManager.ExtraInfoBridge {
    private static final Logger LOG = new Logger((Class<?>) AppStateAppOpsBridge.class);
    private final AppOpsManager mAppOpsManager;
    private final int mAppOpsOpCode;
    private final Context mContext;
    private final IPackageManager mIPackageManager;
    private final String mPermission;
    private final List<UserHandle> mProfiles;

    public AppStateAppOpsBridge(Context context, int appOpsOpCode, String permission) {
        this(context, appOpsOpCode, permission, AppGlobals.getPackageManager());
    }

    AppStateAppOpsBridge(Context context, int appOpsOpCode, String permission, IPackageManager packageManager) {
        this.mContext = context;
        this.mIPackageManager = packageManager;
        this.mProfiles = UserManager.get(context).getUserProfiles();
        this.mAppOpsManager = (AppOpsManager) context.getSystemService("appops");
        this.mAppOpsOpCode = appOpsOpCode;
        this.mPermission = permission;
    }

    @Override // com.android.car.settings.applications.specialaccess.AppEntryListManager.ExtraInfoBridge
    public void loadExtraInfo(List<ApplicationsState.AppEntry> entries) {
        SparseArray<Map<String, PermissionState>> packageToStatesMapByProfileId = getPackageToStateMapsByProfileId();
        loadAppOpModes(packageToStatesMapByProfileId);
        for (ApplicationsState.AppEntry entry : entries) {
            Map<String, PermissionState> packageStatesMap = packageToStatesMapByProfileId.get(UserHandle.getUserId(entry.info.uid));
            entry.extraInfo = packageStatesMap != null ? packageStatesMap.get(entry.info.packageName) : null;
        }
    }

    private SparseArray<Map<String, PermissionState>> getPackageToStateMapsByProfileId() {
        SparseArray<Map<String, PermissionState>> entries = new SparseArray<>();
        try {
            for (UserHandle profile : this.mProfiles) {
                int profileId = profile.getIdentifier();
                List<PackageInfo> packageInfos = getPackageInfos(profileId);
                Map<String, PermissionState> entriesForProfile = new ArrayMap<>();
                entries.put(profileId, entriesForProfile);
                for (PackageInfo packageInfo : packageInfos) {
                    boolean isAvailable = this.mIPackageManager.isPackageAvailable(packageInfo.packageName, profileId);
                    if (shouldIgnorePackage(packageInfo) || !isAvailable) {
                        LOG.d("Ignoring " + packageInfo.packageName + " isAvailable=" + isAvailable);
                    } else {
                        PermissionState newEntry = new PermissionState();
                        newEntry.mRequestedPermissions = packageInfo.requestedPermissions;
                        entriesForProfile.put(packageInfo.packageName, newEntry);
                    }
                }
            }
        } catch (RemoteException e) {
            LOG.w("PackageManager is dead. Can't get list of packages requesting " + this.mPermission, e);
        }
        return entries;
    }

    private List<PackageInfo> getPackageInfos(int profileId) throws RemoteException {
        return this.mIPackageManager.getPackagesHoldingPermissions(new String[]{this.mPermission}, 4096, profileId).getList();
    }

    private boolean shouldIgnorePackage(PackageInfo packageInfo) {
        return packageInfo.packageName.equals(SystemMediaRouteProvider.PACKAGE_NAME) || packageInfo.packageName.equals(this.mContext.getPackageName()) || !ArrayUtils.contains(packageInfo.requestedPermissions, this.mPermission);
    }

    private void loadAppOpModes(SparseArray<Map<String, PermissionState>> packageToStateMapsByProfileId) {
        List<AppOpsManager.PackageOps> packageOps = this.mAppOpsManager.getPackagesForOps(new int[]{this.mAppOpsOpCode});
        if (packageOps == null) {
            return;
        }
        for (AppOpsManager.PackageOps packageOp : packageOps) {
            int userId = UserHandle.getUserId(packageOp.getUid());
            Map<String, PermissionState> packageStateMap = packageToStateMapsByProfileId.get(userId);
            if (packageStateMap != null) {
                PermissionState permissionState = packageStateMap.get(packageOp.getPackageName());
                if (permissionState == null) {
                    LOG.w("AppOp permission exists for package " + packageOp.getPackageName() + " of user " + userId + " but package doesn't exist or did not request " + this.mPermission + " access");
                } else if (packageOp.getOps().size() < 1) {
                    LOG.w("No AppOps permission exists for package " + packageOp.getPackageName());
                } else {
                    permissionState.mAppOpMode = ((AppOpsManager.OpEntry) packageOp.getOps().get(0)).getMode();
                }
            }
        }
    }

    public static class PermissionState {
        private int mAppOpMode = 3;
        private String[] mRequestedPermissions;

        public boolean isPermissible() {
            int i = this.mAppOpMode;
            return i == 3 || i == 0;
        }

        public String[] getRequestedPermissions() {
            return this.mRequestedPermissions;
        }
    }
}
