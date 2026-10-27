package com.amazon.podcast.downloads;

import com.amazon.podcast.appsync.QueryParameters;

/* JADX INFO: loaded from: classes5.dex */
public final class DownloadQueryParameters extends QueryParameters {
    private final String podcastId;
    private final String seasonNumber;

    public DownloadQueryParameters() {
        this.podcastId = null;
        this.seasonNumber = null;
    }

    public DownloadQueryParameters(final String podcastId, final String seasonNumber) {
        this.podcastId = podcastId;
        this.seasonNumber = seasonNumber;
    }

    public String getPodcastId() {
        return this.podcastId;
    }

    public String getSeasonNumber() {
        if ("ALL_EPISODES".equals(this.seasonNumber)) {
            return null;
        }
        return this.seasonNumber;
    }
}
