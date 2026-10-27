package com.android.permissioncontroller.permission.model.legacy;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes8.dex */
@Deprecated
public final class PermissionGroups implements LoaderManager.LoaderCallbacks<List<PermissionGroup>> {
    private final PermissionsGroupsChangeCallback mCallback;
    private final Context mContext;
    private final boolean mGetAppUiInfo;
    private final boolean mGetNonPlatformPermissions;
    private final ArrayList<PermissionGroup> mGroups = new ArrayList<>();

    public interface PermissionsGroupsChangeCallback {
        void onPermissionGroupsChanged();
    }

    public PermissionGroups(Context context, final LoaderManager loaderManager, PermissionsGroupsChangeCallback callback, boolean getAppUiInfo, boolean getNonPlatformPermissions) {
        this.mContext = context;
        this.mCallback = callback;
        this.mGetAppUiInfo = getAppUiInfo;
        this.mGetNonPlatformPermissions = getNonPlatformPermissions;
        new Handler().post(new Runnable() { // from class: com.android.permissioncontroller.permission.model.legacy.-$$Lambda$PermissionGroups$_U-FW8k_2eVFOEWBeXSTBqkzWl0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0$PermissionGroups(loaderManager);
            }
        });
    }

    public /* synthetic */ void lambda$new$0$PermissionGroups(LoaderManager loaderManager) {
        loaderManager.initLoader(0, null, this);
    }

    @Override // android.app.LoaderManager.LoaderCallbacks
    public Loader<List<PermissionGroup>> onCreateLoader(int id, Bundle args) {
        return new PermissionsLoader(this.mContext, this.mGetAppUiInfo, this.mGetNonPlatformPermissions);
    }

    @Override // android.app.LoaderManager.LoaderCallbacks
    public void onLoadFinished(Loader<List<PermissionGroup>> loader, List<PermissionGroup> groups) {
        if (this.mGroups.equals(groups)) {
            return;
        }
        this.mGroups.clear();
        this.mGroups.addAll(groups);
        this.mCallback.onPermissionGroupsChanged();
    }

    @Override // android.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<List<PermissionGroup>> loader) {
        this.mGroups.clear();
        this.mCallback.onPermissionGroupsChanged();
    }

    public List<PermissionGroup> getGroups() {
        return this.mGroups;
    }

    public PermissionGroup getGroup(String name) {
        for (PermissionGroup group : this.mGroups) {
            if (group.getName().equals(name)) {
                return group;
            }
        }
        return null;
    }

    private static CharSequence loadItemInfoLabel(Context context, PackageItemInfo itemInfo) {
        CharSequence label = itemInfo.loadSafeLabel(context.getPackageManager(), 0.0f, 5);
        if (label == null) {
            return itemInfo.name;
        }
        return label;
    }

    private static Drawable loadItemInfoIcon(Context context, PackageItemInfo itemInfo) {
        Drawable icon = null;
        if (itemInfo.icon > 0) {
            icon = Utils.loadDrawable(context.getPackageManager(), itemInfo.packageName, itemInfo.icon);
        }
        if (icon == null) {
            Drawable icon2 = context.getDrawable(R.drawable.ic_perm_device_info);
            return icon2;
        }
        return icon;
    }

    public static List<PermissionGroup> getAllPermissionGroups(Context context, Supplier<Boolean> isCanceled, boolean getAppUiInfo, boolean getNonPlatformPermissions) {
        return getPermissionGroups(context, isCanceled, getAppUiInfo, getNonPlatformPermissions, null, null);
    }

    /* JADX WARN: Incorrect condition in loop: B:18:0x0070 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.List<com.android.permissioncontroller.permission.model.legacy.PermissionGroup> getPermissionGroups(android.content.Context r36, java.util.function.Supplier<java.lang.Boolean> r37, boolean r38, boolean r39, java.lang.String[] r40, java.lang.String r41) {
        /*
            Method dump skipped, instruction units count: 587
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.model.legacy.PermissionGroups.getPermissionGroups(android.content.Context, java.util.function.Supplier, boolean, boolean, java.lang.String[], java.lang.String):java.util.List");
    }

    private static List<PermissionGroupInfo> getPermissionGroupInfos(Context context, String[] groupNames) {
        if (groupNames == null) {
            return context.getPackageManager().getAllPermissionGroups(0);
        }
        try {
            List<PermissionGroupInfo> groupInfos = new ArrayList<>(groupNames.length);
            for (String str : groupNames) {
                PermissionGroupInfo groupInfo = context.getPackageManager().getPermissionGroupInfo(str, 0);
                groupInfos.add(groupInfo);
            }
            return groupInfos;
        } catch (PackageManager.NameNotFoundException e) {
            return Collections.emptyList();
        }
    }

    private static final class PermissionsLoader extends AsyncTaskLoader<List<PermissionGroup>> implements PackageManager.OnPermissionsChangedListener {
        private final boolean mGetAppUiInfo;
        private final boolean mGetNonPlatformPermissions;

        PermissionsLoader(Context context, boolean getAppUiInfo, boolean getNonPlatformPermissions) {
            super(context);
            this.mGetAppUiInfo = getAppUiInfo;
            this.mGetNonPlatformPermissions = getNonPlatformPermissions;
        }

        @Override // android.content.Loader
        protected void onStartLoading() {
            getContext().getPackageManager().addOnPermissionsChangeListener(this);
            forceLoad();
        }

        @Override // android.content.Loader
        protected void onStopLoading() {
            getContext().getPackageManager().removeOnPermissionsChangeListener(this);
        }

        @Override // android.content.AsyncTaskLoader
        public List<PermissionGroup> loadInBackground() {
            return PermissionGroups.getAllPermissionGroups(getContext(), new Supplier() { // from class: com.android.permissioncontroller.permission.model.legacy.-$$Lambda$sdWQd7jJxLnUNx0leieQ4gmg3TU
                @Override // java.util.function.Supplier
                public final Object get() {
                    return Boolean.valueOf(this.f$0.isLoadInBackgroundCanceled());
                }
            }, this.mGetAppUiInfo, this.mGetNonPlatformPermissions);
        }

        public void onPermissionsChanged(int uid) {
            forceLoad();
        }
    }
}
