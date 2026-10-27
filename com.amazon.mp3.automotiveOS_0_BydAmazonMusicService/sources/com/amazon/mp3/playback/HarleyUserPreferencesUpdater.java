package com.amazon.mp3.playback;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.download.DownloadQualityPreferences;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.helper.UserPreferenceProvider;
import com.amazon.mp3.net.dmls.StreamingBitrate;
import com.amazon.mp3.playback.harley.HarleyDownloadProperties;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.media.playback.Playback;

/* JADX INFO: loaded from: classes3.dex */
public class HarleyUserPreferencesUpdater {
    private static final String TAG = "HarleyUserPreferencesUpdater";
    private final Context mContext;
    private final UserPreferenceProvider mUserPreferenceProvider;

    public HarleyUserPreferencesUpdater(final Context context) {
        this.mContext = context;
        this.mUserPreferenceProvider = new UserPreferenceProvider(context);
    }

    public void updateUserQualityPreferences() {
        Log.info(TAG, "Updating the download/streaming configuration in Harley as User benefits have changed");
        new HarleyDownloadProperties();
        AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.playback.HarleyUserPreferencesUpdater.1
            @Override // java.lang.Runnable
            public void run() {
                MusicDownloader.getInstance(HarleyUserPreferencesUpdater.this.mContext).updateHarleyConfig();
                HarleyUserPreferencesUpdater.this.getUserApplicableDownloadSettingSelected();
                Playback.getInstance().getPlaybackConfig().setBitRateSelectionForWifi(HarleyUserPreferencesUpdater.this.getUserApplicableBitrateSelectedForWifi().convertToBitRateSelection());
                Playback.getInstance().getPlaybackConfig().setBitRateSelectionForOTA(HarleyUserPreferencesUpdater.this.getUserApplicableBitrateSelectedForOTA().convertToBitRateSelection());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public StreamingBitrate getUserApplicableBitrateSelectedForWifi() {
        StreamingBitrate currentStreamingBitrateForWifi = this.mUserPreferenceProvider.getCurrentStreamingBitrateForWifi();
        if (currentStreamingBitrateForWifi != StreamingBitrate.KATANA || UserSubscriptionUtil.getUserSubscription().isKatana()) {
            return currentStreamingBitrateForWifi;
        }
        StreamingBitrate streamingBitrate = StreamingBitrate.AUTO;
        this.mUserPreferenceProvider.setStreamingBitrateForWifi(streamingBitrate);
        return streamingBitrate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public StreamingBitrate getUserApplicableBitrateSelectedForOTA() {
        StreamingBitrate currentStreamingBitrateForOTA = this.mUserPreferenceProvider.getCurrentStreamingBitrateForOTA();
        if (currentStreamingBitrateForOTA != StreamingBitrate.KATANA || UserSubscriptionUtil.getUserSubscription().isKatana()) {
            return currentStreamingBitrateForOTA;
        }
        StreamingBitrate streamingBitrate = StreamingBitrate.AUTO;
        this.mUserPreferenceProvider.setStreamingBitrateForOTA(streamingBitrate);
        return streamingBitrate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadQualityPreferences getUserApplicableDownloadSettingSelected() {
        DownloadQualityPreferences currentDownloadQualityPreference = this.mUserPreferenceProvider.getCurrentDownloadQualityPreference();
        if (currentDownloadQualityPreference != DownloadQualityPreferences.AUTO || UserSubscriptionUtil.getUserSubscription().isKatana()) {
            return currentDownloadQualityPreference;
        }
        DownloadQualityPreferences downloadQualityPreferences = DownloadQualityPreferences.HIGH;
        this.mUserPreferenceProvider.setDownloadQualityPreference(downloadQualityPreferences);
        return downloadQualityPreferences;
    }
}
