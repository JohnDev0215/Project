package com.android.packageinstaller;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class PackageUtil {
    public static final String INTENT_ATTR_APPLICATION_INFO = "com.android.packageinstaller.applicationInfo";
    public static final String INTENT_ATTR_INSTALL_STATUS = "com.android.packageinstaller.installStatus";
    public static final String INTENT_ATTR_PACKAGE_NAME = "com.android.packageinstaller.PackageName";
    public static final String INTENT_ATTR_PERMISSIONS_LIST = "com.android.packageinstaller.PermissionsList";
    private static final String LOG_TAG = PackageUtil.class.getSimpleName();
    public static final String PREFIX = "com.android.packageinstaller.";

    public static PackageInfo getPackageInfo(Context context, File sourceFile, int flags) {
        try {
            return context.getPackageManager().getPackageArchiveInfo(sourceFile.getAbsolutePath(), flags);
        } catch (Exception e) {
            return null;
        }
    }

    public static View initSnippet(View snippetView, CharSequence label, Drawable icon) {
        ((ImageView) snippetView.findViewById(R.id.app_icon)).setImageDrawable(icon);
        ((TextView) snippetView.findViewById(R.id.app_name)).setText(label);
        return snippetView;
    }

    public static View initSnippetForInstalledApp(Context pContext, ApplicationInfo appInfo, View snippetView) {
        return initSnippetForInstalledApp(pContext, appInfo, snippetView, null);
    }

    public static View initSnippetForInstalledApp(Context pContext, ApplicationInfo appInfo, View snippetView, UserHandle user) {
        PackageManager pm = pContext.getPackageManager();
        Drawable icon = appInfo.loadIcon(pm);
        if (user != null) {
            icon = pContext.getPackageManager().getUserBadgedIcon(icon, user);
        }
        return initSnippet(snippetView, appInfo.loadLabel(pm), icon);
    }

    public static class AppSnippet {
        public Drawable icon;
        public CharSequence label;

        public AppSnippet(CharSequence label, Drawable icon) {
            this.label = label;
            this.icon = icon;
        }
    }

    public static AppSnippet getAppSnippet(Activity pContext, ApplicationInfo appInfo, File sourceFile) {
        String archiveFilePath = sourceFile.getAbsolutePath();
        Resources pRes = pContext.getResources();
        AssetManager assmgr = new AssetManager();
        assmgr.addAssetPath(archiveFilePath);
        Resources res = new Resources(assmgr, pRes.getDisplayMetrics(), pRes.getConfiguration());
        CharSequence label = null;
        if (appInfo.labelRes != 0) {
            try {
                label = res.getText(appInfo.labelRes);
            } catch (Resources.NotFoundException e) {
            }
        }
        if (label == null) {
            label = appInfo.nonLocalizedLabel != null ? appInfo.nonLocalizedLabel : appInfo.packageName;
        }
        Drawable icon = null;
        try {
            if (appInfo.icon != 0) {
                try {
                    icon = res.getDrawable(appInfo.icon);
                } catch (Resources.NotFoundException e2) {
                }
            }
            if (icon == null) {
                icon = pContext.getPackageManager().getDefaultActivityIcon();
            }
        } catch (OutOfMemoryError e3) {
            Log.i(LOG_TAG, "Could not load app icon", e3);
        }
        return new AppSnippet(label, icon);
    }

    static int getMaxTargetSdkVersionForUid(Context context, int uid) {
        PackageManager pm = context.getPackageManager();
        String[] packages = pm.getPackagesForUid(uid);
        int targetSdkVersion = -1;
        if (packages != null) {
            for (String packageName : packages) {
                try {
                    ApplicationInfo info = pm.getApplicationInfo(packageName, 0);
                    targetSdkVersion = Math.max(targetSdkVersion, info.targetSdkVersion);
                } catch (PackageManager.NameNotFoundException e) {
                }
            }
        }
        return targetSdkVersion;
    }

    public static String getPackageNameForUid(Context context, int sourceUid, String packageName) {
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(sourceUid);
        if (packagesForUid == null || packagesForUid.length == 0) {
            return null;
        }
        if (packagesForUid.length > 1) {
            if (packageName != null) {
                for (String packageForUid : packagesForUid) {
                    if (packageName.equals(packageForUid)) {
                        return packageName;
                    }
                }
            }
            Log.i(LOG_TAG, "Multiple packages found for source uid " + sourceUid);
        }
        return packagesForUid[0];
    }
}
