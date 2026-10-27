package com.amazon.podcast.views.ptcPodcastsTemplate;

import Podcast.Touch.PTCTemplateInterface.v1_0.GridElement;

/* JADX INFO: loaded from: classes5.dex */
final class PTCPodcastsTemplateSection {
    private GridElement element;
    private boolean isHeader;
    private String subtitle;
    private String title;

    public PTCPodcastsTemplateSection(final GridElement element) {
        this.element = element;
    }

    public PTCPodcastsTemplateSection(final String title, final String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
        this.isHeader = true;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubtitle() {
        return this.subtitle;
    }

    public final boolean isHeader() {
        return this.isHeader;
    }

    public final GridElement getElement() {
        return this.element;
    }
}
