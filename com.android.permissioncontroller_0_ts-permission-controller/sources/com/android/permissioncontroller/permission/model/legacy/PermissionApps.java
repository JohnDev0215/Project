package com.android.permissioncontroller.permission.model.legacy;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
@Deprecated
public class PermissionApps {
    private static final String LOG_TAG = "PermissionApps";
    private final AppDataCache mAppDataCache;
    private ArrayMap<String, PermissionApp> mAppLookup;
    private final Callback mCallback;
    private final Context mContext;
    private CharSequence mDescription;
    private CharSequence mFullLabel;
    private final String mGroupName;
    private Drawable mIcon;
    private CharSequence mLabel;
    private final String mPackageName;
    private List<PermissionApp> mPermApps;
    private final PackageManager mPm;
    private final PmCache mPmCache;
    private boolean mRefreshing;
    private boolean mSkipUi;

    public interface Callback {
        void onPermissionsLoaded(PermissionApps permissionApps);
    }

    public PermissionApps(Context context, String groupName, Callback callback) {
        this(context, groupName, null, callback, null, null);
    }

    public PermissionApps(Context context, String groupName, String packageName, Callback callback, PmCache pmCache, AppDataCache appDataCache) {
        this.mPmCache = pmCache;
        this.mAppDataCache = appDataCache;
        this.mContext = context;
        this.mPm = context.getPackageManager();
        this.mGroupName = groupName;
        this.mCallback = callback;
        this.mPackageName = packageName;
        loadGroupInfo();
    }

    public String getGroupName() {
        return this.mGroupName;
    }

    public void refresh(boolean getUiInfo) {
        if (!this.mRefreshing) {
            this.mRefreshing = true;
            this.mSkipUi = !getUiInfo;
            new PermissionAppsLoader().execute(new Void[0]);
        }
    }

    public void refreshSync(boolean getUiInfo) {
        this.mSkipUi = !getUiInfo;
        createMap(loadPermissionApps());
    }

    public int getGrantedCount() {
        int count = 0;
        for (PermissionApp app : this.mPermApps) {
            if (app.getAppInfo().enabled && Utils.shouldShowPermission(this.mContext, app.getPermissionGroup()) && Utils.isGroupOrBgGroupUserSensitive(app.mAppPermissionGroup) && app.areRuntimePermissionsGranted()) {
                count++;
            }
        }
        return count;
    }

    public int getTotalCount() {
        int count = 0;
        for (PermissionApp app : this.mPermApps) {
            if (app.getAppInfo().enabled && Utils.shouldShowPermission(this.mContext, app.getPermissionGroup()) && Utils.isGroupOrBgGroupUserSensitive(app.mAppPermissionGroup)) {
                count++;
            }
        }
        return count;
    }

    public List<PermissionApp> getApps() {
        return this.mPermApps;
    }

    public PermissionApp getApp(String key) {
        return this.mAppLookup.get(key);
    }

    public CharSequence getLabel() {
        return this.mLabel;
    }

    public CharSequence getFullLabel() {
        return this.mFullLabel;
    }

    public Drawable getIcon() {
        return this.mIcon;
    }

    public CharSequence getDescription() {
        return this.mDescription;
    }

    private List<PackageInfo> getPackageInfos(UserHandle user) {
        List<PackageInfo> apps;
        PmCache pmCache = this.mPmCache;
        if (pmCache != null) {
            apps = pmCache.getPackages(user.getIdentifier());
        } else {
            apps = null;
        }
        if (apps != null) {
            if (this.mPackageName != null) {
                int appCount = apps.size();
                for (int i = 0; i < appCount; i++) {
                    PackageInfo app = apps.get(i);
                    if (this.mPackageName.equals(app.packageName)) {
                        List<PackageInfo> apps2 = new ArrayList<>(1);
                        apps2.add(app);
                        return apps2;
                    }
                }
            }
            return apps;
        }
        String str = this.mPackageName;
        if (str == null) {
            return this.mPm.getInstalledPackagesAsUser(4096, user.getIdentifier());
        }
        try {
            PackageInfo packageInfo = this.mPm.getPackageInfo(str, 4096);
            List<PackageInfo> apps3 = new ArrayList<>(1);
            apps3.add(packageInfo);
            return apps3;
        } catch (PackageManager.NameNotFoundException e) {
            return Collections.emptyList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<PermissionApp> loadPermissionApps() {
        int i;
        int N;
        List<PackageInfo> apps;
        UserHandle user;
        int j;
        PackageInfo app;
        PackageInfo app2;
        String label;
        PermissionApps permissionApps = this;
        PackageItemInfo groupInfo = Utils.getGroupInfo(permissionApps.mGroupName, permissionApps.mContext);
        if (groupInfo == null) {
            return Collections.emptyList();
        }
        List<PermissionInfo> groupPermInfos = Utils.getGroupPermissionInfos(permissionApps.mGroupName, permissionApps.mContext);
        if (groupPermInfos == null) {
            return Collections.emptyList();
        }
        List<PermissionInfo> targetPermInfos = new ArrayList<>(groupPermInfos.size());
        for (int i2 = 0; i2 < groupPermInfos.size(); i2++) {
            PermissionInfo permInfo = groupPermInfos.get(i2);
            if ((permInfo.protectionLevel & 15) == 1 && (permInfo.flags & BasicMeasure.EXACTLY) != 0 && (permInfo.flags & 2) == 0) {
                targetPermInfos.add(permInfo);
            }
        }
        PackageManager packageManager = permissionApps.mContext.getPackageManager();
        CharSequence groupLabel = groupInfo.loadLabel(packageManager);
        CharSequence fullGroupLabel = groupInfo.loadSafeLabel(packageManager, 0.0f, 5);
        ArrayList<PermissionApp> permApps = new ArrayList<>();
        UserManager userManager = (UserManager) permissionApps.mContext.getSystemService(UserManager.class);
        for (UserHandle user2 : userManager.getUserProfiles()) {
            List<PackageInfo> apps2 = permissionApps.getPackageInfos(user2);
            int N2 = apps2.size();
            int i3 = 0;
            while (i3 < N2) {
                PackageInfo app3 = apps2.get(i3);
                if (app3.requestedPermissions == null) {
                    i = i3;
                    N = N2;
                    apps = apps2;
                    user = user2;
                } else {
                    int j2 = 0;
                    while (true) {
                        if (j2 >= app3.requestedPermissions.length) {
                            i = i3;
                            N = N2;
                            apps = apps2;
                            user = user2;
                            break;
                        }
                        String requestedPerm = app3.requestedPermissions[j2];
                        PermissionInfo requestedPermissionInfo = null;
                        Iterator<PermissionInfo> it = targetPermInfos.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                j = j2;
                                i = i3;
                                break;
                            }
                            j = j2;
                            PermissionInfo groupPermInfo = it.next();
                            i = i3;
                            if (requestedPerm.equals(groupPermInfo.name)) {
                                requestedPermissionInfo = groupPermInfo;
                                break;
                            }
                            i3 = i;
                            j2 = j;
                        }
                        if (requestedPermissionInfo == null) {
                            app = app3;
                            N = N2;
                            apps = apps2;
                            user = user2;
                        } else {
                            app = app3;
                            N = N2;
                            apps = apps2;
                            user = user2;
                            AppPermissionGroup group = AppPermissionGroup.create(permissionApps.mContext, app3, groupInfo, groupPermInfos, groupLabel, fullGroupLabel, false);
                            if (group != null) {
                                Pair<String, Drawable> appData = null;
                                AppDataCache appDataCache = permissionApps.mAppDataCache;
                                if (appDataCache == null || permissionApps.mSkipUi) {
                                    app2 = app;
                                } else {
                                    app2 = app;
                                    appData = appDataCache.getAppData(user.getIdentifier(), app2.applicationInfo);
                                }
                                if (permissionApps.mSkipUi) {
                                    label = app2.packageName;
                                } else if (appData != null) {
                                    label = (String) appData.first;
                                } else {
                                    label = app2.applicationInfo.loadLabel(permissionApps.mPm).toString();
                                }
                                Drawable icon = null;
                                if (!permissionApps.mSkipUi) {
                                    if (appData != null) {
                                        icon = (Drawable) appData.second;
                                    } else {
                                        icon = Utils.getBadgedIcon(permissionApps.mContext, app2.applicationInfo);
                                    }
                                }
                                PermissionApp permApp = new PermissionApp(app2.packageName, group, label, icon, app2.applicationInfo);
                                permApps.add(permApp);
                            }
                        }
                        j2 = j + 1;
                        i3 = i;
                        app3 = app;
                        N2 = N;
                        apps2 = apps;
                        user2 = user;
                    }
                }
                i3 = i + 1;
                permissionApps = this;
                N2 = N;
                apps2 = apps;
                user2 = user;
            }
            permissionApps = this;
        }
        Collections.sort(permApps);
        return permApps;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createMap(List<PermissionApp> result) {
        this.mAppLookup = new ArrayMap<>();
        for (PermissionApp app : result) {
            this.mAppLookup.put(app.getKey(), app);
        }
        this.mPermApps = result;
    }

    private void loadGroupInfo() {
        PackageItemInfo info;
        try {
            info = this.mPm.getPermissionGroupInfo(this.mGroupName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            try {
                PermissionInfo permInfo = this.mPm.getPermissionInfo(this.mGroupName, 0);
                if ((permInfo.protectionLevel & 15) != 1) {
                    Log.w(LOG_TAG, this.mGroupName + " is not a runtime permission");
                    return;
                }
                info = permInfo;
            } catch (PackageManager.NameNotFoundException reallyNotFound) {
                Log.w(LOG_TAG, "Can't find permission: " + this.mGroupName, reallyNotFound);
                return;
            }
        }
        this.mLabel = info.loadLabel(this.mPm);
        this.mFullLabel = info.loadSafeLabel(this.mPm, 0.0f, 5);
        if (info.icon != 0) {
            this.mIcon = info.loadUnbadgedIcon(this.mPm);
        } else {
            this.mIcon = this.mContext.getDrawable(R.drawable.ic_perm_device_info);
        }
        this.mIcon = Utils.applyTint(this.mContext, this.mIcon, android.R.attr.colorControlNormal);
        if (info instanceof PermissionGroupInfo) {
            this.mDescription = ((PermissionGroupInfo) info).loadDescription(this.mPm);
        } else if (info instanceof PermissionInfo) {
            this.mDescription = ((PermissionInfo) info).loadDescription(this.mPm);
        }
    }

    public static class PermissionApp implements Comparable<PermissionApp> {
        private final AppPermissionGroup mAppPermissionGroup;
        private Drawable mIcon;
        private final ApplicationInfo mInfo;
        private String mLabel;
        private final String mPackageName;

        public PermissionApp(String packageName, AppPermissionGroup appPermissionGroup, String label, Drawable icon, ApplicationInfo info) {
            this.mPackageName = packageName;
            this.mAppPermissionGroup = appPermissionGroup;
            this.mLabel = label;
            this.mIcon = icon;
            this.mInfo = info;
        }

        public ApplicationInfo getAppInfo() {
            return this.mInfo;
        }

        public String getKey() {
            return this.mPackageName + getUid();
        }

        public String getLabel() {
            return this.mLabel;
        }

        public Drawable getIcon() {
            return this.mIcon;
        }

        public boolean areRuntimePermissionsGranted() {
            return this.mAppPermissionGroup.areRuntimePermissionsGranted();
        }

        public boolean isReviewRequired() {
            return this.mAppPermissionGroup.isReviewRequired();
        }

        public void grantRuntimePermissions() {
            this.mAppPermissionGroup.grantRuntimePermissions(true, false);
        }

        public void revokeRuntimePermissions() {
            this.mAppPermissionGroup.revokeRuntimePermissions(false);
        }

        public boolean isPolicyFixed() {
            return this.mAppPermissionGroup.isPolicyFixed();
        }

        public boolean isSystemFixed() {
            return this.mAppPermissionGroup.isSystemFixed();
        }

        public boolean hasGrantedByDefaultPermissions() {
            return this.mAppPermissionGroup.hasGrantedByDefaultPermission();
        }

        public boolean doesSupportRuntimePermissions() {
            return this.mAppPermissionGroup.doesSupportRuntimePermissions();
        }

        public String getPackageName() {
            return this.mPackageName;
        }

        public AppPermissionGroup getPermissionGroup() {
            return this.mAppPermissionGroup;
        }

        @Override // java.lang.Comparable
        public int compareTo(PermissionApp another) {
            int result = this.mLabel.compareTo(another.mLabel);
            if (result == 0) {
                return getKey().compareTo(another.getKey());
            }
            return result;
        }

        public int getUid() {
            return this.mAppPermissionGroup.getApp().applicationInfo.uid;
        }
    }

    private class PermissionAppsLoader extends AsyncTask<Void, Void, List<PermissionApp>> {
        private PermissionAppsLoader() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public List<PermissionApp> doInBackground(Void... args) {
            return PermissionApps.this.loadPermissionApps();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(List<PermissionApp> result) {
            PermissionApps.this.mRefreshing = false;
            PermissionApps.this.createMap(result);
            if (PermissionApps.this.mCallback != null) {
                PermissionApps.this.mCallback.onPermissionsLoaded(PermissionApps.this);
            }
        }
    }

    public static class PmCache {
        private final SparseArray<List<PackageInfo>> mPackageInfoCache = new SparseArray<>();
        private final PackageManager mPm;

        public PmCache(PackageManager pm) {
            this.mPm = pm;
        }

        public synchronized List<PackageInfo> getPackages(int userId) {
            List ret;
            ret = this.mPackageInfoCache.get(userId);
            if (ret == null) {
                ret = this.mPm.getInstalledPackagesAsUser(4096, userId);
                this.mPackageInfoCache.put(userId, ret);
            }
            return ret;
        }
    }

    public static class AppDataCache {
        private final SparseArray<ArrayMap<String, Pair<String, Drawable>>> mCache = new SparseArray<>();
        private final Context mContext;
        private final PackageManager mPm;

        public AppDataCache(PackageManager pm, Context context) {
            this.mPm = pm;
            this.mContext = context;
        }

        public Pair<String, Drawable> getAppData(int userId, ApplicationInfo app) {
            ArrayMap<String, Pair<String, Drawable>> dataForUser = this.mCache.get(userId);
            if (dataForUser == null) {
                dataForUser = new ArrayMap<>();
                this.mCache.put(userId, dataForUser);
            }
            Pair<String, Drawable> data = dataForUser.get(app.packageName);
            if (data == null) {
                Pair<String, Drawable> data2 = Pair.create(app.loadLabel(this.mPm).toString(), Utils.getBadgedIcon(this.mContext, app));
                dataForUser.put(app.packageName, data2);
                return data2;
            }
            return data;
        }
    }
}
