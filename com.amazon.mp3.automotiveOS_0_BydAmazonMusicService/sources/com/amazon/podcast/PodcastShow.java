package com.amazon.podcast;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastShow {
    private final String deeplink;
    private final String description;
    private final boolean followed;
    private final String image;
    private final String publisher;
    private final String title;

    public PodcastShow(final String title, final String image, final String description, final String publisher, final boolean followed, final String deeplink) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.publisher = publisher;
        this.followed = followed;
        this.deeplink = deeplink;
    }

    public String getImage() {
        return this.image;
    }

    public String getDeeplink() {
        return this.deeplink;
    }
}
