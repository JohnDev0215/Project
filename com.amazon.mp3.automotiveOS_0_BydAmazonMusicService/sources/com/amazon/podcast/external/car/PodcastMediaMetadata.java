package com.amazon.podcast.external.car;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastMediaMetadata {
    private final String artwork;
    private final Long durationSeconds;
    private final String mediaCollectionId;
    private final String mediaId;
    private final String playbackDeeplink;
    private final String subtitle;
    private final String title;

    public PodcastMediaMetadata(final String playbackDeeplink, final String mediaCollectionId, final String mediaId, final String subtitle, final String title, final String artwork, final Long durationSeconds) {
        this.playbackDeeplink = playbackDeeplink;
        this.mediaCollectionId = mediaCollectionId;
        this.mediaId = mediaId;
        this.subtitle = subtitle;
        this.title = title;
        this.artwork = artwork;
        this.durationSeconds = durationSeconds;
    }

    public String getMediaCollectionId() {
        return this.mediaCollectionId;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getTitle() {
        return this.title;
    }

    public String getArtwork() {
        return this.artwork;
    }

    public Long getDurationSeconds() {
        return this.durationSeconds;
    }
}
