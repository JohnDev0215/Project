package com.bydux.api.common.tools.permission;

import android.app.Activity;
import android.content.Context;
import b.h.e.a;

/* JADX INFO: loaded from: classes.dex */
public class BydPermissionHelper {

    public interface PermissionCheckCallBack {
        void onHasPermission();

        void onUserHasAlreadyTurnedDown(String str);

        void onUserHasAlreadyTurnedDownAndDontAsk(String str);
    }

    public interface PermissionRequestSuccessCallBack {
        void onHasPermission();
    }

    public static void checkAndRequestPermission(Context context, String str, int i2, PermissionRequestSuccessCallBack permissionRequestSuccessCallBack) {
        if (checkPermission(context, str)) {
            permissionRequestSuccessCallBack.onHasPermission();
        } else {
            requestPermission(context, str, i2);
        }
    }

    public static void checkAndRequestPermissions(Context context, String[] strArr, int i2, PermissionRequestSuccessCallBack permissionRequestSuccessCallBack) {
        if (checkPermissions(context, strArr)) {
            permissionRequestSuccessCallBack.onHasPermission();
        } else {
            requestPermissions(context, strArr, i2);
        }
    }

    public static boolean checkPermission(Context context, String str) {
        return a.a(context, str) == 0;
    }

    private static boolean checkPermissions(Context context, String[] strArr) {
        for (String str : strArr) {
            if (!checkPermission(context, str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPermissionRequestSuccess(int i2) {
        return i2 == 0;
    }

    private static boolean judgePermission(Context context, String str) {
        return b.h.d.a.p((Activity) context, str);
    }

    public static void requestPermission(Context context, String str, int i2) {
        requestPermissions((Activity) context, new String[]{str}, i2);
    }

    public static void requestPermissions(Context context, String[] strArr, int i2) {
        b.h.d.a.m((Activity) context, strArr, i2);
    }

    public static void checkPermission(Context context, String str, PermissionCheckCallBack permissionCheckCallBack) {
        if (checkPermission(context, str)) {
            permissionCheckCallBack.onHasPermission();
        } else if (judgePermission(context, str)) {
            permissionCheckCallBack.onUserHasAlreadyTurnedDown(str);
        } else {
            permissionCheckCallBack.onUserHasAlreadyTurnedDownAndDontAsk(str);
        }
    }
}
