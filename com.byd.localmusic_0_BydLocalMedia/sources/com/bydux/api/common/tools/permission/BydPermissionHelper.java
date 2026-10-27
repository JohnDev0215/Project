package com.bydux.api.common.tools.permission;

import android.app.Activity;
import android.content.Context;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/* JADX INFO: loaded from: classes5.dex */
public class BydPermissionHelper {

    public interface PermissionCheckCallBack {
        void onHasPermission();

        void onUserHasAlreadyTurnedDown(String permission);

        void onUserHasAlreadyTurnedDownAndDontAsk(String permission);
    }

    public interface PermissionRequestSuccessCallBack {
        void onHasPermission();
    }

    public static boolean isPermissionRequestSuccess(int grantResult) {
        return grantResult == 0;
    }

    public static boolean checkPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == 0;
    }

    public static void checkPermission(Context context, String permission, PermissionCheckCallBack callBack) {
        if (checkPermission(context, permission)) {
            callBack.onHasPermission();
        } else if (judgePermission(context, permission)) {
            callBack.onUserHasAlreadyTurnedDown(permission);
        } else {
            callBack.onUserHasAlreadyTurnedDownAndDontAsk(permission);
        }
    }

    private static boolean checkPermissions(Context context, String[] permissions) {
        for (String str : permissions) {
            if (!checkPermission(context, str)) {
                return false;
            }
        }
        return true;
    }

    public static void requestPermission(Context context, String permission, int requestCode) {
        requestPermissions((Activity) context, new String[]{permission}, requestCode);
    }

    public static void requestPermissions(Context context, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions((Activity) context, permissions, requestCode);
    }

    public static void checkAndRequestPermission(Context context, String permission, int requestCode, PermissionRequestSuccessCallBack callBack) {
        if (checkPermission(context, permission)) {
            callBack.onHasPermission();
        } else {
            requestPermission(context, permission, requestCode);
        }
    }

    public static void checkAndRequestPermissions(Context context, String[] permissions, int requestCode, PermissionRequestSuccessCallBack callBack) {
        if (checkPermissions(context, permissions)) {
            callBack.onHasPermission();
        } else {
            requestPermissions(context, permissions, requestCode);
        }
    }

    private static boolean judgePermission(Context context, String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission);
    }
}
