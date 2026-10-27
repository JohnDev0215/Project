package com.amazon.mp3.download.helper;

import android.content.Context;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.download.DownloadQualityPreferences;
import com.amazon.mp3.net.dmls.StreamingBitrate;

/* JADX INFO: loaded from: classes3.dex */
public class UserPreferenceProvider {
    private final Context mContext;

    public UserPreferenceProvider(final Context context) {
        this.mContext = context;
    }

    public DownloadQualityPreferences getCurrentDownloadQualityPreference() {
        return DownloadQualityPreferences.fromEntryValue(SettingsUtil.getDownloadQualityPreference(this.mContext));
    }

    public void setDownloadQualityPreference(final DownloadQualityPreferences downloadQualityPreference) {
        SettingsUtil.setDownloadQualityPreference(this.mContext, downloadQualityPreference.getEntryValue());
    }

    public StreamingBitrate getCurrentStreamingBitrateForWifi() {
        return StreamingBitrate.fromEntryValue(SettingsUtil.getMultiBitrateStreamingPreferenceForWifi(this.mContext));
    }

    public StreamingBitrate getCurrentStreamingBitrateForOTA() {
        return StreamingBitrate.fromEntryValue(SettingsUtil.getMultiBitrateStreamingPreferenceForOTA(this.mContext));
    }

    public void setStreamingBitrateForWifi(final StreamingBitrate streamingBitrate) {
        SettingsUtil.setMultiBitrateStreamingPreferenceForWifi(this.mContext, streamingBitrate.getEntryValue());
    }

    public void setStreamingBitrateForOTA(final StreamingBitrate streamingBitrate) {
        SettingsUtil.setMultiBitrateStreamingPreferenceForOTA(this.mContext, streamingBitrate.getEntryValue());
    }
}
