package com.byd.music.utils;

import android.Manifest;
import android.app.Activity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.blankj.utilcode.util.Utils;

/* JADX INFO: loaded from: classes5.dex */
public class PermissionUtil {
    private static final String[] BASIC_PERMISSIONS = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECEIVE_BOOT_COMPLETED, Manifest.permission.USE_FULL_SCREEN_INTENT};

    public static void checkPermission(Activity activity) {
        for (String str : BASIC_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), str) != 0) {
                ActivityCompat.requestPermissions(activity, BASIC_PERMISSIONS, 123);
                LogUtil.d("checkPermission: permission boom with " + str);
                return;
            }
        }
    }

    public static boolean hasPermission() {
        for (String str : BASIC_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(Utils.getApp(), str) != 0) {
                return false;
            }
        }
        return true;
    }
}
