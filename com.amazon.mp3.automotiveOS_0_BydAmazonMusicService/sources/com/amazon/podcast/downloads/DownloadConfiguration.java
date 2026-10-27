package com.amazon.podcast.downloads;

import com.amazon.music.downloads.worker.Configuration;
import com.amazon.podcast.Podcast;

/* JADX INFO: loaded from: classes5.dex */
final class DownloadConfiguration implements Configuration {
    @Override // com.amazon.music.downloads.worker.Configuration
    public int getConnectionTimeout() {
        return 70000;
    }

    @Override // com.amazon.music.downloads.worker.Configuration
    public Configuration.NetworkRetryPolicyProvider getNetworkRetryPolicyProvider() {
        return null;
    }

    @Override // com.amazon.music.downloads.worker.Configuration
    public int getParallelDownloadsCount() {
        return 1;
    }

    DownloadConfiguration() {
    }

    @Override // com.amazon.music.downloads.worker.Configuration
    public String getAppVersion() {
        return Podcast.getBuildInfoProvider().getAppVersion();
    }
}
