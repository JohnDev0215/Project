package com.byd.music.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DiLinkDumpUtils {
    private static final String SERVICE_CLASS = "com.byd.dialogservice.DialogService";
    private static final String SERVICE_PKG = "com.byd.carsettings";
    static final String TAG = "DiLinkDumpUtils";

    public static void go2SoundEffectPage(Context context, PackageManager packageManager) {
        StringBuilder sb = new StringBuilder();
        sb.append("go2SoundEffectPage ");
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?activity=sound_settings"));
            List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
            sb.append("resolveInfos=");
            sb.append(listQueryIntentServices);
            LogUtil.d(TAG, sb.toString());
            intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
            if (listQueryIntentServices.isEmpty()) {
                return;
            }
            context.startService(intent);
        } catch (Throwable th) {
            sb.append(" error");
            sb.append(th);
            LogUtil.e(sb.toString());
        }
    }
}
