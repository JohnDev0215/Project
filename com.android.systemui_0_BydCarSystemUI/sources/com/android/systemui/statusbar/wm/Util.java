package com.android.systemui.statusbar.wm;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.provider.Settings;
import android.view.WindowManager;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Util {
    public static final String BYD_SOUND_IDLER_WHEEL_ENABLE = "byd_idler_wheel";
    public static final String BYD_SOUND_SCREEN_SHOT_ENABLE = "byd_screen_shot";

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int getNavigationBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int[] getWindowWidthAndHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        return new int[]{point.x, point.y};
    }

    public static boolean isSoundScreenShot(Context context) {
        int i = Settings.System.getInt(context.getContentResolver(), BYD_SOUND_SCREEN_SHOT_ENABLE, 1);
        BydLogHelper.logV("isSoundScreenShot", "value:" + i);
        return i == 1;
    }

    public static boolean isSoundWheel(Context context) {
        int i = Settings.System.getInt(context.getContentResolver(), BYD_SOUND_IDLER_WHEEL_ENABLE, 1);
        BydLogHelper.logV("isSoundWheel", "value:" + i);
        return i == 1;
    }

    public static HashMap<String, String> getLauncherList(Context context) {
        HashMap<String, String> map = new HashMap<>();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
        for (int i = 0; i < listQueryIntentActivities.size(); i++) {
            ResolveInfo resolveInfo = listQueryIntentActivities.get(i);
            map.put(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
            BydLogHelper.logV("getLauncherList", "packageName =" + resolveInfo.activityInfo.packageName);
        }
        return map;
    }
}
