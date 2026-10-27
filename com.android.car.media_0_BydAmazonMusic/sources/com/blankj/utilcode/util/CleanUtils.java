package com.blankj.utilcode.util;

import android.app.ActivityManager;
import android.os.Environment;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.ActivityChooserModel;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public final class CleanUtils {
    private CleanUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    @RequiresApi(api = 19)
    public static void cleanAppUserData() {
        ((ActivityManager) Utils.getApp().getSystemService(ActivityChooserModel.ATTRIBUTE_ACTIVITY)).clearApplicationUserData();
    }

    public static boolean cleanCustomDir(String str) {
        return FileUtils.deleteAllInDir(FileUtils.getFileByPath(str));
    }

    public static boolean cleanExternalCache() {
        return "mounted".equals(Environment.getExternalStorageState()) && FileUtils.deleteAllInDir(Utils.getApp().getExternalCacheDir());
    }

    public static boolean cleanInternalCache() {
        return FileUtils.deleteAllInDir(Utils.getApp().getCacheDir());
    }

    public static boolean cleanInternalDbByName(String str) {
        return Utils.getApp().deleteDatabase(str);
    }

    public static boolean cleanInternalDbs() {
        return FileUtils.deleteAllInDir(new File(Utils.getApp().getFilesDir().getParent(), "databases"));
    }

    public static boolean cleanInternalFiles() {
        return FileUtils.deleteAllInDir(Utils.getApp().getFilesDir());
    }

    public static boolean cleanInternalSp() {
        return FileUtils.deleteAllInDir(new File(Utils.getApp().getFilesDir().getParent(), "shared_prefs"));
    }
}
