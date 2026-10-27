package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveShovelerElement;

/* JADX INFO: loaded from: classes5.dex */
public final class EpisodeDescriptiveShovelerElementConverter {
    public static final EpisodeDescriptiveShovelerElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodeDescriptiveShovelerElement element) {
        if (element == null) {
            return null;
        }
        return EpisodeDescriptiveShovelerElement.builder().withId(element.getId()).withHeader(element.getHeader()).withItems(element.getItems()).withSeeMore(EpisodeDescriptiveSeeMoreItemElementConverter.convert(element.getSeeMore())).withOnContentFirstViewed(element.getOnContentFirstViewed()).withOnViewed(element.getOnViewed()).build();
    }
}
