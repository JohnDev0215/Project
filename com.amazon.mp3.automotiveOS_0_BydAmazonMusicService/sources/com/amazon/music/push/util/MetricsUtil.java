package com.amazon.music.push.util;

import android.util.Log;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.MetricsManager;
import com.amazon.music.metrics.mts.event.definition.push.PushCampaignEvent;
import com.amazon.music.metrics.technical.TechnicalMetricsCollection;
import com.amazon.music.metrics.technical.TechnicalMetricsRecorder;
import com.amazon.music.push.config.PushMtsEnabledFeature;

/* JADX INFO: loaded from: classes4.dex */
public final class MetricsUtil {
    private static final String TAG = "MetricsUtil";
    private static final PushMtsEnabledFeature pushMtsEnabledFeature = new PushMtsEnabledFeature();

    public static void sendEventToMTS(String str, String str2, String str3, String str4, String str5) {
        try {
            if (pushMtsEnabledFeature.isEnabled()) {
                MetricsManager manager = MetricsHolder.getManager();
                PushCampaignEvent pushCampaignEventBuild = PushCampaignEvent.builder(str, str2, str3, str4, str5).build();
                Log.i(TAG, String.format("Sending metrics to MTS with data (actionType = '%s', appInstallId = '%s', campaignId = '%s', treatmentId = '%s', campaignType = '%s')", str, str2, str3, str4, str5));
                manager.handleEvent(pushCampaignEventBuild);
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to send PushCampaignEvent to MTS with exception: " + e);
        }
    }

    public static TechnicalMetricsCollection initializeTechnicalCollectionMetrics(String str) {
        String musicTerritory;
        try {
            musicTerritory = AccountManagerSingleton.get().getMusicTerritory();
        } catch (Exception e) {
            Log.w(TAG, "getMusicTerritory failed " + e);
            musicTerritory = "";
        }
        return TechnicalMetricsRecorder.getTechnicalMetricsCollection(str + ":" + musicTerritory);
    }
}
