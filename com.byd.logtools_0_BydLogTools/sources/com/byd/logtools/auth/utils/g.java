package com.byd.logtools.auth.utils;

import android.content.Context;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.os.SystemProperties;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: compiled from: VehicleConfigUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f3153a = false;

    public static String a(Context context) {
        return BYDAutoBodyworkDevice.getInstance(context).getRealAutoVIN();
    }

    public static String b(Context context) {
        return BYDAutoBodyworkDevice.getInstance(context).getAutoVIN();
    }

    public static boolean c(Context context) {
        int i = SystemProperties.getInt("persist.vendor.byd.cfg.huc.steering.wheel.position", 0);
        f3153a = i > 0;
        BydLogHelper.logI("VehicleConfigUtils", "---isRightRudder---value: " + i + ", isRightRudder: " + f3153a);
        return i > 0;
    }
}
