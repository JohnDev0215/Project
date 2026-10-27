package com.amazon.mp3.service.metrics;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.core.app.NotificationCompat;
import com.amazon.mp3.util.Log;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BatteryStatusCollector.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001c\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007R\u001c\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\u00050\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/service/metrics/BatteryStatusCollector;", "", "Landroid/content/Context;", "context", "", "", "getBatteryData", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BatteryStatusCollector {
    public static final BatteryStatusCollector INSTANCE = new BatteryStatusCollector();
    private static final String TAG = BatteryStatusCollector.class.getSimpleName();

    private BatteryStatusCollector() {
    }

    @JvmStatic
    public static final Map<String, Object> getBatteryData(Context context) {
        String str;
        Intrinsics.checkNotNullParameter(context, "context");
        IntentFilter intentFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        HashMap map = new HashMap();
        String str2 = "";
        float f = 0.0f;
        try {
            try {
                Intent intentRegisterReceiver = context.registerReceiver(null, intentFilter);
                if (intentRegisterReceiver != null) {
                    if (2 == intentRegisterReceiver.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1)) {
                        int intExtra = intentRegisterReceiver.getIntExtra("plugged", -1);
                        str = intExtra != 1 ? intExtra != 2 ? intExtra != 4 ? "other" : "wireless" : "usb" : "ac";
                    } else {
                        str = "no";
                    }
                    str2 = str;
                    int intExtra2 = intentRegisterReceiver.getIntExtra("level", -1);
                    int intExtra3 = intentRegisterReceiver.getIntExtra("scale", -1);
                    if (-1 != intExtra2 && -1 != intExtra3) {
                        f = (intExtra2 * 100.0f) / intExtra3;
                    }
                }
            } catch (Exception e) {
                Log.debug(TAG, Intrinsics.stringPlus("Exception getting battery status: ", e.getMessage()));
            }
            return map;
        } finally {
            map.put("btch", str2);
            map.put("btl", Float.valueOf(0.0f));
        }
    }
}
