package com.bydux.api.common.tools.window;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.WindowManager;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes5.dex */
public class BnWindowHelper {
    public static final String ACTION_BT_CALL = "byd.bt.action.CALL";
    private static final String TAG = "BnWindowHelper";
    public static final String WINDOW_KEY_ACTION = "window_key_action";
    public static final String WINDOW_KEY_GET_TOP_ACTION = "window_key_get_top_action";
    public static final String WINDOW_KEY_OPERATE = "window_key_operate";
    public static final String WINDOW_KEY_PARAM = "window_key_param";
    public static final String WINDOW_KEY_TOP_ACTION = "window_key_top_action";

    public static void startActivity(Context context, Intent intent) {
        if (intent != null) {
            try {
                intent.addFlags(268435456);
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                BydLogHelper.logE(TAG, "startActivity", e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static void startActivityByPkg(Context context, String pkgName) {
        BydLogHelper.logV(TAG, "startActivityByPkg", "pkgName=" + pkgName);
        if (TextUtils.isEmpty(pkgName)) {
            return;
        }
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(pkgName);
            if (launchIntentForPackage != null) {
                launchIntentForPackage.addFlags(268435456);
                context.startActivity(launchIntentForPackage);
            }
        } catch (ActivityNotFoundException e) {
            BydLogHelper.logE(TAG, "startActivityByPkg", e.getMessage());
            e.printStackTrace();
        }
    }

    public static void startActivityByAction(Context context, String action) {
        BydLogHelper.logV(TAG, "startActivityByAction", "action=" + action);
        startActivityByAction(context, action, null);
    }

    public static void startActivityByAction(Context context, String action, Bundle bundle) {
        BydLogHelper.logV(TAG, "startActivityByAction", "action=" + action);
        if (TextUtils.isEmpty(action)) {
            return;
        }
        try {
            Intent intent = new Intent();
            intent.setAction(action);
            intent.putExtra(WINDOW_KEY_PARAM, bundle);
            intent.addFlags(268435456);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            BydLogHelper.logE(TAG, "startActivityByAction", e.getMessage());
            e.printStackTrace();
        }
    }

    public static void startActivityByClass(Context context, Class<?> cls) {
        BydLogHelper.logV(TAG, "startActivityByClass", "cls=" + cls);
        if (cls == null) {
            return;
        }
        try {
            Intent intent = new Intent(context, cls);
            intent.addFlags(268435456);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            BydLogHelper.logE(TAG, "startActivityByClass", e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean isActivityTop(Context context, Class<?> cls) {
        return cls != null && ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(1).get(0).topActivity.getClassName().equals(cls.getName());
    }

    public static boolean isActivityTop(Context context, String pkgName) {
        return !TextUtils.isEmpty(pkgName) && ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(1).get(0).topActivity.getPackageName().equals(pkgName);
    }

    public static void fullScreen(Activity activity, boolean bFull) {
        if (activity == null) {
            return;
        }
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        if (bFull) {
            attributes.flags |= 1024;
            activity.getWindow().setAttributes(attributes);
            activity.getWindow().addFlags(512);
        } else {
            attributes.flags &= -1025;
            activity.getWindow().setAttributes(attributes);
            activity.getWindow().clearFlags(512);
        }
    }

    public static boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags & 1024) != 0;
    }

    public static void hideWallpaper(Activity activity) {
        setWallpaperVisibility(activity, false);
    }

    public static void showWallpaper(Activity activity) {
        setWallpaperVisibility(activity, true);
    }

    private static void setWallpaperVisibility(Activity activity, boolean visible) {
        int i = visible ? 1048576 : 0;
        if (i != (activity.getWindow().getAttributes().flags & 1048576)) {
            activity.getWindow().setFlags(i, 1048576);
        }
    }
}
