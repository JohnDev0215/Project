package com.byd.auto_emanual.util;

import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.os.RemoteException;
import com.byd.auto_emanual.BuildConfig;
import com.byd.auto_emanual.ExecutorManager;
import com.byd.auto_emanual.MApp;

/* JADX INFO: loaded from: classes.dex */
public class MobileDataUtil {
    public static final String SHOW_MOBILE_DATA_PROMPT_MB_100 = "showed_prompt_mb_100";
    public static final String SHOW_MOBILE_DATA_PROMPT_MB_200 = "showed_prompt_mb_200";
    public static final String SHOW_MOBILE_DATA_PROMPT_MB_30 = "showed_prompt_mb_30";
    private static final String TAG = "MobileDataUtil";
    public static final int THRESHOLD_MB_100 = 100;
    public static final int THRESHOLD_MB_200 = 200;
    public static final int THRESHOLD_MB_30 = 30;
    public static final int timeInterval = 10000;

    public interface MobileDataUsageListener {
        void onMobileDataUsageReceived(long useBytes);
    }

    public static void getMobileDataUsageAsync(final long startTime, final long endTime, final MobileDataUsageListener listener) {
        ExecutorManager.getInstance().execute(new Runnable() { // from class: com.byd.auto_emanual.util.-$$Lambda$MobileDataUtil$a9-8cp3K9HXbcEAL5e6b-VYRD_U
            @Override // java.lang.Runnable
            public final void run() {
                MobileDataUtil.lambda$getMobileDataUsageAsync$0(startTime, endTime, listener);
            }
        });
    }

    static /* synthetic */ void lambda$getMobileDataUsageAsync$0(long j, long j2, MobileDataUsageListener mobileDataUsageListener) {
        NetworkStatsManager networkStatsManager = (NetworkStatsManager) MApp.context.getSystemService("netstats");
        NetworkStats.Bucket bucket = new NetworkStats.Bucket();
        long rxBytes = 0;
        try {
            NetworkStats networkStatsQuerySummary = networkStatsManager.querySummary(0, null, j, j2);
            int applicationUid = Utils.getApplicationUid(BuildConfig.APPLICATION_ID);
            while (networkStatsQuerySummary != null) {
                if (!networkStatsQuerySummary.hasNextBucket()) {
                    break;
                }
                networkStatsQuerySummary.getNextBucket(bucket);
                if (applicationUid == bucket.getUid()) {
                    rxBytes = bucket.getRxBytes() + rxBytes + bucket.getTxBytes();
                }
            }
        } catch (RemoteException e) {
            MLog.e(TAG, "getMobileDataUsageAsync exception.", e);
        }
        MLog.i(TAG, "useBytes = " + rxBytes);
        mobileDataUsageListener.onMobileDataUsageReceived(rxBytes);
    }

    public static void calcMobileData(MobileDataUsageListener listener) {
        if (isShowedMobileDataPrompt(SHOW_MOBILE_DATA_PROMPT_MB_200)) {
            return;
        }
        getMobileDataUsageAsync(0L, System.currentTimeMillis(), listener);
    }

    public static boolean isShowedMobileDataPrompt(String key) {
        boolean z = Boolean.parseBoolean(SharedPrefUtil.load(MApp.context, key));
        MLog.i(TAG, "isShowedMobileDataPrompt " + key + " " + z);
        return z;
    }

    private static void promptMobileData(String key, String value) {
        SharedPrefUtil.save(MApp.context, key, value);
    }

    public static void promptMobileData(int threshHold) {
        if (200 == threshHold) {
            promptMobileData(SHOW_MOBILE_DATA_PROMPT_MB_200, String.valueOf(true));
        } else if (100 == threshHold) {
            promptMobileData(SHOW_MOBILE_DATA_PROMPT_MB_100, String.valueOf(true));
        } else if (30 == threshHold) {
            promptMobileData(SHOW_MOBILE_DATA_PROMPT_MB_30, String.valueOf(true));
        }
    }
}
