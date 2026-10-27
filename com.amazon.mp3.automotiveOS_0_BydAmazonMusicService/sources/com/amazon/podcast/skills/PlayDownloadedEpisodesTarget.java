package com.amazon.podcast.skills;

/* JADX INFO: loaded from: classes5.dex */
public final class PlayDownloadedEpisodesTarget extends LocalSkillTarget {
    private final String episodeId;
    private final boolean launchNowPlaying;

    public PlayDownloadedEpisodesTarget(final String episodeId, final boolean launchNowPlaying) {
        this.episodeId = episodeId;
        this.launchNowPlaying = launchNowPlaying;
    }

    public String getEpisodeId() {
        return this.episodeId;
    }

    public boolean getLaunchNowPlaying() {
        return this.launchNowPlaying;
    }
}
