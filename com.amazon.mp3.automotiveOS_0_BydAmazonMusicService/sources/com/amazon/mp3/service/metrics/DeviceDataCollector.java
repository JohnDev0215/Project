package com.amazon.mp3.service.metrics;

import android.content.Context;
import android.os.SystemProperties;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.amazon.mp3.util.Log;
import com.google.gson.Gson;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.io.FilesKt__FileReadWriteKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DeviceDataCollector.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0007J\u001c\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\b\u0010\n\u001a\u00020\u0004H\u0007R\u001f\u0010\f\u001a\n \u000b*\u0004\u0018\u00010\u00040\u00048\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/service/metrics/DeviceDataCollector;", "", "Landroid/content/Context;", "context", "", "getDeviceData", "key", "getSystemProperty", "", "getScreenMetrics", "getPreinstallInfo", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "getTAG", "()Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class DeviceDataCollector {
    public static final DeviceDataCollector INSTANCE = new DeviceDataCollector();
    private static final String TAG = DeviceDataCollector.class.getSimpleName();

    private DeviceDataCollector() {
    }

    @JvmStatic
    public static final String getDeviceData(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        HashMap map = new HashMap();
        map.put("cpu_abi", getSystemProperty("ro.product.cpu.abi"));
        map.put("cpu_abi2", getSystemProperty("ro.product.cpu.abi2"));
        map.put("arch", getSystemProperty("os.arch"));
        map.put("build_display_id", getSystemProperty("ro.build.display.id"));
        map.put("dim", getScreenMetrics(context));
        map.putAll(BatteryStatusCollector.getBatteryData(context));
        String json = new Gson().toJson(map);
        Intrinsics.checkNotNullExpressionValue(json, "Gson().toJson(deviceData)");
        return json;
    }

    @JvmStatic
    public static final String getSystemProperty(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            String str = SystemProperties.get(key, "");
            Intrinsics.checkNotNullExpressionValue(str, "get(key, EMPTY_STRING)");
            return str;
        } catch (Throwable th) {
            Log.debug(TAG, "Exception getting system property: " + key + ((Object) th.getMessage()));
            return "";
        }
    }

    @JvmStatic
    public static final Map<String, String> getScreenMetrics(Context context) {
        DisplayMetrics displayMetrics;
        Object systemService;
        Intrinsics.checkNotNullParameter(context, "context");
        HashMap map = new HashMap();
        try {
            displayMetrics = new DisplayMetrics();
            systemService = context.getSystemService("window");
        } catch (Throwable th) {
            Log.debug(TAG, Intrinsics.stringPlus("Exception getting screen metrics: ", th.getMessage()));
        }
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.WindowManager");
        }
        ((WindowManager) systemService).getDefaultDisplay().getMetrics(displayMetrics);
        int i = context.getResources().getConfiguration().screenLayout & 15;
        map.put("x_px", String.valueOf(displayMetrics.widthPixels));
        map.put("y_px", String.valueOf(displayMetrics.heightPixels));
        map.put("d_dpi", String.valueOf(displayMetrics.densityDpi));
        map.put("size", String.valueOf(i));
        map.put("xdp", String.valueOf(displayMetrics.xdpi));
        map.put("ydp", String.valueOf(displayMetrics.ydpi));
        return map;
    }

    @JvmStatic
    public static final String getPreinstallInfo() {
        try {
            return FilesKt__FileReadWriteKt.readText$default(new File(getSystemProperty("ro.appsflyer.preinstall.path")), null, 1, null);
        } catch (Throwable th) {
            Log.error(TAG, Intrinsics.stringPlus("Exception reading preinstall info: ", th.getMessage()));
            return "";
        }
    }
}
