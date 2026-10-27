package com.amazon.podcast.skills;

/* JADX INFO: loaded from: classes5.dex */
public final class RestoreDownloadedEpisodesPlaybackTarget extends LocalSkillTarget {
    private final String episodeId;

    public RestoreDownloadedEpisodesPlaybackTarget(final String episodeId) {
        this.episodeId = episodeId;
    }

    public String getEpisodeId() {
        return this.episodeId;
    }
}
