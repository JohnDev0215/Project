package com.amazon.podcast;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastEpisode {
    private final String authors;
    private final String deeplink;
    private final String description;
    private final String episodeId;
    private final String image;
    private final String podcastEpisodeVariantId;
    private final String podcastId;
    private final String podcastTitle;
    private final Integer progress;
    private final String remainingDurationText;
    private final String title;
    private final int totalDurationMilliseconds;
    private final String totalDurationText;

    public PodcastEpisode(final String episodeId, final String podcastId, final String title, final String podcastTitle, final String image, final String description, final String authors, final String deeplink, final Integer totalDurationMilliseconds, final String totalDurationText, final Integer progress, final String remainingDurationText, final String podcastEpisodeVariantId) {
        this.episodeId = episodeId;
        this.podcastId = podcastId;
        this.title = title;
        this.podcastTitle = podcastTitle;
        this.image = image;
        this.description = description;
        this.authors = authors;
        this.deeplink = deeplink;
        this.totalDurationMilliseconds = totalDurationMilliseconds.intValue();
        this.totalDurationText = totalDurationText;
        this.progress = progress;
        this.remainingDurationText = remainingDurationText;
        this.podcastEpisodeVariantId = podcastEpisodeVariantId;
    }

    public String getEpisodeId() {
        return this.episodeId;
    }

    public String getPodcastId() {
        return this.podcastId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPodcastTitle() {
        return this.podcastTitle;
    }

    public String getImage() {
        return this.image;
    }

    public String getDescription() {
        return this.description;
    }

    public String getAuthors() {
        return this.authors;
    }

    public String getDeeplink() {
        return this.deeplink;
    }

    public int getTotalDurationMilliseconds() {
        return this.totalDurationMilliseconds;
    }

    public String getTotalDurationText() {
        return this.totalDurationText;
    }

    public Integer getProgress() {
        return this.progress;
    }

    public String getRemainingDurationText() {
        return this.remainingDurationText;
    }

    public String getPodcastEpisodeVariantId() {
        return this.podcastEpisodeVariantId;
    }
}
