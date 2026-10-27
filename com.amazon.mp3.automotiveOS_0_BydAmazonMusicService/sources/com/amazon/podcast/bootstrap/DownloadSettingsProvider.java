package com.amazon.podcast.bootstrap;

import com.amazon.podcast.downloads.PodcastDownloader;

/* JADX INFO: loaded from: classes5.dex */
public abstract class DownloadSettingsProvider {
    public boolean shouldShowDownloadSettings() {
        throw new UnsupportedOperationException();
    }

    public void showDownloadSettings(final PodcastDownloader downloader) {
        throw new UnsupportedOperationException();
    }

    public boolean shouldRequestStoragePermission() {
        throw new UnsupportedOperationException();
    }

    public void requestStoragePermission(final PodcastDownloader downloader) {
        throw new UnsupportedOperationException();
    }
}
