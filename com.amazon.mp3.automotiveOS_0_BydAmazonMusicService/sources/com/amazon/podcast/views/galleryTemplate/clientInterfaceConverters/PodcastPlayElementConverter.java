package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0.PodcastPlayElement;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastPlayElementConverter {
    public static final PodcastPlayElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.PodcastPlayElement element) {
        if (element == null) {
            return null;
        }
        return PodcastPlayElement.builder().withCollectionId(element.getCollectionId()).withOnPlaySelected(element.getOnPlaySelected()).build();
    }
}
