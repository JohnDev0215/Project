package com.android.launcher3.util;

import android.app.AppOpsManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.android.launcher3.AppInfo;
import com.android.launcher3.ItemInfo;
import com.android.launcher3.LauncherAppWidgetInfo;
import com.android.launcher3.PendingAddItemInfo;
import com.android.launcher3.PromiseAppInfo;
import com.android.launcher3.R;
import com.android.launcher3.WorkspaceItemInfo;
import com.android.launcher3.compat.LauncherAppsCompat;
import java.net.URISyntaxException;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class PackageManagerHelper {
    private static final String TAG = "PackageManagerHelper";
    private final Context mContext;
    private final LauncherAppsCompat mLauncherApps;
    private final PackageManager mPm;

    public PackageManagerHelper(Context context) {
        this.mContext = context;
        this.mPm = context.getPackageManager();
        this.mLauncherApps = LauncherAppsCompat.getInstance(context);
    }

    public boolean isAppOnSdcard(String packageName, UserHandle user) {
        ApplicationInfo applicationInfo = this.mLauncherApps.getApplicationInfo(packageName, 8192, user);
        return (applicationInfo == null || (applicationInfo.flags & 262144) == 0) ? false : true;
    }

    public boolean isAppSuspended(String packageName, UserHandle user) {
        ApplicationInfo applicationInfo = this.mLauncherApps.getApplicationInfo(packageName, 0, user);
        return applicationInfo != null && isAppSuspended(applicationInfo);
    }

    public boolean isSafeMode() {
        return this.mContext.getPackageManager().isSafeMode();
    }

    public Intent getAppLaunchIntent(String pkg, UserHandle user) {
        List<LauncherActivityInfo> activityList = this.mLauncherApps.getActivityList(pkg, user);
        if (activityList.isEmpty()) {
            return null;
        }
        return AppInfo.makeLaunchIntent(activityList.get(0));
    }

    public static boolean isAppSuspended(ApplicationInfo info) {
        return (info.flags & 1073741824) != 0;
    }

    public boolean hasPermissionForActivity(Intent intent, String srcPackage) {
        ResolveInfo resolveInfoResolveActivity = this.mPm.resolveActivity(intent, 0);
        if (resolveInfoResolveActivity == null) {
            return false;
        }
        if (TextUtils.isEmpty(resolveInfoResolveActivity.activityInfo.permission)) {
            return true;
        }
        if (TextUtils.isEmpty(srcPackage) || this.mPm.checkPermission(resolveInfoResolveActivity.activityInfo.permission, srcPackage) != 0) {
            return false;
        }
        if (TextUtils.isEmpty(AppOpsManager.permissionToOp(resolveInfoResolveActivity.activityInfo.permission))) {
            return true;
        }
        try {
            return this.mPm.getApplicationInfo(srcPackage, 0).targetSdkVersion >= 23;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public Intent getMarketIntent(String packageName) {
        return new Intent("android.intent.action.VIEW").setData(new Uri.Builder().scheme("market").authority("details").appendQueryParameter("id", packageName).build()).putExtra(Intent.EXTRA_REFERRER, new Uri.Builder().scheme("android-app").authority(this.mContext.getPackageName()).build());
    }

    public static Intent getMarketSearchIntent(Context context, String query) {
        try {
            Intent uri = Intent.parseUri(context.getString(R.string.market_search_intent), 0);
            if (!TextUtils.isEmpty(query)) {
                uri.setData(uri.getData().buildUpon().appendQueryParameter("q", query).build());
            }
            return uri;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static Intent getStyleWallpapersIntent(Context context) {
        return new Intent(Intent.ACTION_SET_WALLPAPER).setComponent(new ComponentName(context.getString(R.string.wallpaper_picker_package), "com.android.customization.picker.CustomizationPickerActivity"));
    }

    public void startDetailsActivityForInfo(ItemInfo info, Rect sourceBounds, Bundle opts) {
        if (info instanceof PromiseAppInfo) {
            Context context = this.mContext;
            context.startActivity(((PromiseAppInfo) info).getMarketIntent(context));
            return;
        }
        ComponentName targetComponent = null;
        if (info instanceof AppInfo) {
            targetComponent = ((AppInfo) info).componentName;
        } else if (info instanceof WorkspaceItemInfo) {
            targetComponent = info.getTargetComponent();
        } else if (info instanceof PendingAddItemInfo) {
            targetComponent = ((PendingAddItemInfo) info).componentName;
        } else if (info instanceof LauncherAppWidgetInfo) {
            targetComponent = ((LauncherAppWidgetInfo) info).providerName;
        }
        if (targetComponent != null) {
            try {
                this.mLauncherApps.showAppDetailsForProfile(targetComponent, info.user, sourceBounds, opts);
            } catch (ActivityNotFoundException | SecurityException e) {
                Toast.makeText(this.mContext, R.string.activity_not_found, 0).show();
                Log.e(TAG, "Unable to launch settings", e);
            }
        }
    }

    public static IntentFilter getPackageFilter(String pkg, String... actions) {
        IntentFilter intentFilter = new IntentFilter();
        for (String str : actions) {
            intentFilter.addAction(str);
        }
        intentFilter.addDataScheme("package");
        intentFilter.addDataSchemeSpecificPart(pkg, 0);
        return intentFilter;
    }
}
