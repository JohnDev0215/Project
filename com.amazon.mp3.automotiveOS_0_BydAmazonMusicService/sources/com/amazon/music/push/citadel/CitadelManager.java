package com.amazon.music.push.citadel;

import android.content.Context;
import android.util.Log;
import com.amazon.music.push.sonarpush.SonarPushCacheManager;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class CitadelManager {
    private static final String TAG = "CitadelManager";
    private static CitadelManager citadelManager;

    public static boolean isProd(Context context) {
        return false;
    }

    private CitadelManager() {
    }

    public static synchronized CitadelManager getInstance() {
        if (citadelManager == null) {
            Log.d(TAG, "Initializing Citadel");
            citadelManager = new CitadelManager();
        }
        return citadelManager;
    }

    public static synchronized boolean challengeLatestStateUpdate(Context context, Long l) {
        String string = SonarPushCacheManager.getString(context, "latestCitadelSync");
        if (!StringUtils.isEmpty(string) && l.longValue() <= Long.parseLong(string)) {
            return false;
        }
        SonarPushCacheManager.putString(context, "latestCitadelSync", l.toString());
        return true;
    }

    public static void performHealthCheck(Context context) {
        String str = TAG;
        Log.d(str, "Checking Citadel connection health");
        try {
            String string = SonarPushCacheManager.getString(context, "latestCitadelSync");
            if (string != null && !string.equals("")) {
                if (TimeUnit.MILLISECONDS.toDays(new Date().getTime() - new Date(Long.parseLong(string)).getTime()) > 7) {
                    Log.d(str, "Making Citadel health ping");
                    CitadelClient.getInstance().updateCustomerState(context);
                    return;
                } else {
                    Log.w(str, "Skipping Citadel health ping -- sync already performed within time window");
                    return;
                }
            }
            Log.w(str, "Skipping Citadel health ping -- latest sync time is blank");
        } catch (Exception unused) {
            Log.w(TAG, "Failed to check Citadel connection health");
        }
    }
}
