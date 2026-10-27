package com.android.systemui.util.wifi;

import android.content.Context;
import android.net.ScoredNetwork;
import com.android.systemui.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class AccessPoint {

    @Retention(RetentionPolicy.SOURCE)
    public @interface Speed {
        public static final int FAST = 20;
        public static final int MODERATE = 10;
        public static final int NONE = 0;
        public static final int SLOW = 5;
        public static final int VERY_FAST = 30;
    }

    private static int roundToClosestSpeedEnum(int speed) {
        if (speed < 5) {
            return 0;
        }
        if (speed < 7) {
            return 5;
        }
        if (speed < 15) {
            return 10;
        }
        return speed < 25 ? 20 : 30;
    }

    private static String getSpeedLabel(Context context, int speed) {
        if (speed == 5) {
            return context.getString(R.string.speed_label_slow);
        }
        if (speed == 10) {
            return context.getString(R.string.speed_label_okay);
        }
        if (speed == 20) {
            return context.getString(R.string.speed_label_fast);
        }
        if (speed != 30) {
            return null;
        }
        return context.getString(R.string.speed_label_very_fast);
    }

    public static String getSpeedLabel(Context context, ScoredNetwork scoredNetwork, int rssi) {
        return getSpeedLabel(context, roundToClosestSpeedEnum(scoredNetwork.calculateBadge(rssi)));
    }
}
