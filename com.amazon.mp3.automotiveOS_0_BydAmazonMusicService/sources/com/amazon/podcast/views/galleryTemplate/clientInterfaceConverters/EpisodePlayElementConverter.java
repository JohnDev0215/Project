package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0.EpisodePlayElement;

/* JADX INFO: loaded from: classes5.dex */
public final class EpisodePlayElementConverter {
    public static final EpisodePlayElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodePlayElement element) {
        if (element == null) {
            return null;
        }
        return EpisodePlayElement.builder().withMediaId(element.getMediaId()).withOnPlaySelected(element.getOnPlaySelected()).build();
    }
}
