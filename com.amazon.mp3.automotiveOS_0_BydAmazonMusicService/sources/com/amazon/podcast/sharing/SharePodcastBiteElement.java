package com.amazon.podcast.sharing;

/* JADX INFO: loaded from: classes5.dex */
public class SharePodcastBiteElement {
    private final String deeplinkUrl;
    private final String description;
    private final String id;
    private final String imageUrl;
    private final String label;
    private final String metricsEntityType;
    private final String prompt;
    private final String title;

    public SharePodcastBiteElement(final String id, final String title, final String description, final String label, final String prompt, final String imageUrl, final String deeplinkUrl, final String metricsEntityType) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.label = label;
        this.prompt = prompt;
        this.imageUrl = imageUrl;
        this.deeplinkUrl = deeplinkUrl;
        this.metricsEntityType = metricsEntityType;
    }

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLabel() {
        return this.label;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getDeeplinkUrl() {
        return this.deeplinkUrl;
    }

    public String getMetricsEntityType() {
        return this.metricsEntityType;
    }
}
