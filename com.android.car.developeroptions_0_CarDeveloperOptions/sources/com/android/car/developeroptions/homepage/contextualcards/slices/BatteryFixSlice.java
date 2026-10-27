package com.android.car.developeroptions.homepage.contextualcards.slices;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.ArrayMap;
import com.android.car.developeroptions.fuelgauge.BatteryStatsHelperLoader;
import com.android.car.developeroptions.fuelgauge.batterytip.BatteryTipLoader;
import com.android.car.developeroptions.fuelgauge.batterytip.tips.BatteryTip;
import com.android.car.developeroptions.slices.CustomSliceable;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.utils.ThreadUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public class BatteryFixSlice implements CustomSliceable {
    static final String KEY_CURRENT_TIPS_TYPE = "current_tip_type";
    static final String PREFS = "battery_fix_prefs";
    private static final Map<Integer, List<Integer>> UNIMPORTANT_BATTERY_TIPS;

    @Override // com.android.car.developeroptions.slices.CustomSliceable
    public void onNotifyChange(Intent intent) {
    }

    static {
        ArrayMap arrayMap = new ArrayMap();
        UNIMPORTANT_BATTERY_TIPS = arrayMap;
        arrayMap.put(6, Arrays.asList(0, 1));
        UNIMPORTANT_BATTERY_TIPS.put(3, Arrays.asList(1));
    }

    public BatteryFixSlice(Context context) {
    }

    public static void updateBatteryTipAvailabilityCache(final Context context) {
        ThreadUtils.postOnBackgroundThread(new Callable() { // from class: com.android.car.developeroptions.homepage.contextualcards.slices.-$$Lambda$BatteryFixSlice$_F28zTUfymlg0P-QUH5qdVBUbx4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return BatteryFixSlice.refreshBatteryTips(context);
            }
        });
    }

    static boolean isBatteryTipAvailableFromCache(Context context) {
        boolean z = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS, 0);
        int i = sharedPreferences.getInt(KEY_CURRENT_TIPS_TYPE, 6);
        int i2 = sharedPreferences.getInt("current_tip_state", 2);
        if (i2 == 2) {
            return false;
        }
        if (UNIMPORTANT_BATTERY_TIPS.containsKey(Integer.valueOf(i)) && UNIMPORTANT_BATTERY_TIPS.get(Integer.valueOf(i)).contains(Integer.valueOf(i2))) {
            z = true;
        }
        return !z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<BatteryTip> refreshBatteryTips(Context context) {
        List<BatteryTip> listLoadInBackground = new BatteryTipLoader(context, new BatteryStatsHelperLoader(context).loadInBackground()).loadInBackground();
        Iterator<BatteryTip> it = listLoadInBackground.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            BatteryTip next = it.next();
            if (next.getState() != 2) {
                context.getSharedPreferences(PREFS, 0).edit().putInt(KEY_CURRENT_TIPS_TYPE, next.getType()).putInt("current_tip_state", next.getState()).apply();
                break;
            }
        }
        return listLoadInBackground;
    }

    public static class BatteryTipWorker extends SliceBackgroundWorker<BatteryTip> {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        public BatteryTipWorker(Context context, Uri uri) {
            super(context, uri);
        }
    }
}
