package com.android.packageinstaller.wear;

import android.app.ActivityManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class WearPackageIconProvider extends ContentProvider {
    public static final String AUTHORITY = "com.google.android.packageinstaller.wear.provider";
    public static final String ICON_TYPE = "vnd.android.cursor.item/cw_package_icon";
    private static final String REQUIRED_PERMISSION = "com.google.android.permission.INSTALL_WEARABLE_PACKAGES";
    private static final String TAG = "WearPackageIconProvider";

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        throw new UnsupportedOperationException("Query is not supported.");
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        if (uri == null) {
            throw new IllegalArgumentException("URI passed in is null.");
        }
        if (AUTHORITY.equals(uri.getEncodedAuthority())) {
            return ICON_TYPE;
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("Insert is not supported.");
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        File file;
        if (uri == null) {
            throw new IllegalArgumentException("URI passed in is null.");
        }
        enforcePermissions(uri);
        if (ICON_TYPE.equals(getType(uri)) && (file = WearPackageUtil.getIconFile(getContext().getApplicationContext(), getPackageNameFromUri(uri))) != null) {
            file.delete();
            return 0;
        }
        return 0;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Update is not supported.");
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        File file;
        if (uri == null) {
            throw new IllegalArgumentException("URI passed in is null.");
        }
        enforcePermissions(uri);
        if (ICON_TYPE.equals(getType(uri)) && (file = WearPackageUtil.getIconFile(getContext().getApplicationContext(), getPackageNameFromUri(uri))) != null) {
            return ParcelFileDescriptor.open(file, 268435456);
        }
        return null;
    }

    public static Uri getUriForPackage(String packageName) {
        return Uri.parse("content://com.google.android.packageinstaller.wear.provider/icons/" + packageName + ".icon");
    }

    private String getPackageNameFromUri(Uri uri) {
        if (uri == null) {
            return null;
        }
        List<String> pathSegments = uri.getPathSegments();
        String packageName = pathSegments.get(pathSegments.size() - 1);
        if (packageName.endsWith(".icon")) {
            return packageName.substring(0, packageName.lastIndexOf("."));
        }
        return packageName;
    }

    private void enforcePermissions(Uri uri) {
        Context context = getContext();
        int pid = Binder.getCallingPid();
        int uid = Binder.getCallingUid();
        int myUid = Process.myUid();
        if (uid == myUid || isSystemApp(context, pid) || context.checkPermission(REQUIRED_PERMISSION, pid, uid) == 0 || context.checkUriPermission(uri, pid, uid, 1) == 0) {
            return;
        }
        throw new SecurityException("Permission Denial: reading " + getClass().getName() + " uri " + uri + " from pid=" + pid + ", uid=" + uid);
    }

    private boolean isSystemApp(Context context, int pid) {
        ActivityManager aManager = (ActivityManager) context.getSystemService("activity");
        List<ActivityManager.RunningAppProcessInfo> rapInfoList = aManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo rapInfo : rapInfoList) {
            if (rapInfo.pid == pid) {
                try {
                    PackageInfo pkgInfo = context.getPackageManager().getPackageInfo(rapInfo.pkgList[0], 0);
                    if (pkgInfo != null && pkgInfo.applicationInfo != null && (pkgInfo.applicationInfo.flags & 1) != 0) {
                        Log.d(TAG, pid + " is a system app.");
                        return true;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e(TAG, "Could not find package information.", e);
                    return false;
                }
            }
        }
        return false;
    }
}
