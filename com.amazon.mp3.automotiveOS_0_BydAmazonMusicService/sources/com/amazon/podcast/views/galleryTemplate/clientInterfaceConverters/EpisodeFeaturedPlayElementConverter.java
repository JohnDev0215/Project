package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.EpisodeFeaturedPlayElementInterface.v1_0.EpisodeFeaturedPlayElement;

/* JADX INFO: loaded from: classes5.dex */
public final class EpisodeFeaturedPlayElementConverter {
    public static final EpisodeFeaturedPlayElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodeFeaturedPlayElement element) {
        if (element == null) {
            return null;
        }
        return EpisodeFeaturedPlayElement.builder().withId(element.getId()).withForegroundImage(element.getForegroundImage()).withBackgroundImage(element.getBackgroundImage()).withLabel(element.getLabel()).withTitle(element.getTitle()).withPrimaryText(element.getPrimaryText()).withSecondaryText(element.getSecondaryText()).withTertiaryText(element.getTertiaryText()).withOnItemSelected(element.getOnItemSelected()).withPlay(EpisodePlayElementConverter.convert(element.getPlay())).withOnContentFirstViewed(element.getOnContentFirstViewed()).withOnViewed(element.getOnViewed()).build();
    }
}
