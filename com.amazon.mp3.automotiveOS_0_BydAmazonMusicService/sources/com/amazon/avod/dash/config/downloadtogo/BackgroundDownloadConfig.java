package com.amazon.avod.dash.config.downloadtogo;

import amazon.android.config.ConfigurationValue;
import com.amazon.avod.dash.config.g2s2.G2S2DownloadConfig;

/* JADX INFO: loaded from: classes2.dex */
public class BackgroundDownloadConfig extends G2S2DownloadConfig {
    private final ConfigurationValue<Integer> mMaxConcurrentRequests;

    public static BackgroundDownloadConfig getInstanceForBackgroundDownload() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static BackgroundDownloadConfig INSTANCE = new BackgroundDownloadConfig();

        private SingletonHolder() {
        }
    }

    private BackgroundDownloadConfig() {
        this.mMaxConcurrentRequests = newIntConfigValue("playback_dashBackgroundDownloadMaxConcurrentRequests", 5);
    }

    @Override // com.amazon.avod.dash.config.g2s2.G2S2DownloadConfig, com.amazon.avod.content.dash.quality.heuristic.DownloadConfig
    public int getMaxConcurrentRequests() {
        return this.mMaxConcurrentRequests.getValue().intValue();
    }
}
