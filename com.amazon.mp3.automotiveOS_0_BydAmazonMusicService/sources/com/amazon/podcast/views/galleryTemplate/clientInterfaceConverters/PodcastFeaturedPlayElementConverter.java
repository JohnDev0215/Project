package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.PodcastFeaturedPlayElementInterface.v1_0.PodcastFeaturedPlayElement;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastFeaturedPlayElementConverter {
    public static final PodcastFeaturedPlayElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.PodcastFeaturedPlayElement element) {
        if (element == null) {
            return null;
        }
        return PodcastFeaturedPlayElement.builder().withId(element.getId()).withForegroundImage(element.getForegroundImage()).withBackgroundImage(element.getBackgroundImage()).withLabel(element.getLabel()).withTitle(element.getTitle()).withPrimaryText(element.getPrimaryText()).withSecondaryText(element.getSecondaryText()).withTertiaryText(element.getTertiaryText()).withOnItemSelected(element.getOnItemSelected()).withPlay(PodcastPlayElementConverter.convert(element.getPlay())).withOnContentFirstViewed(element.getOnContentFirstViewed()).withOnViewed(element.getOnViewed()).build();
    }
}
