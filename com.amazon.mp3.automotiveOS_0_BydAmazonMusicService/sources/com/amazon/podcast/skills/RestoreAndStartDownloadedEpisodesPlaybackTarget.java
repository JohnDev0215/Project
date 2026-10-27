package com.amazon.podcast.skills;

/* JADX INFO: loaded from: classes5.dex */
public final class RestoreAndStartDownloadedEpisodesPlaybackTarget extends LocalSkillTarget {
    private final String episodeId;

    public RestoreAndStartDownloadedEpisodesPlaybackTarget(final String episodeId) {
        this.episodeId = episodeId;
    }

    public String getEpisodeId() {
        return this.episodeId;
    }
}
