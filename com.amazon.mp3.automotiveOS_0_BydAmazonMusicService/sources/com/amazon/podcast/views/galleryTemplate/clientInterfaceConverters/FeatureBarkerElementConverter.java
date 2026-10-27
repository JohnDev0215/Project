package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.FeaturedBarkerElementInterface.v1_0.FeaturedBarkerElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.FeatureBarkerElement;

/* JADX INFO: loaded from: classes5.dex */
public final class FeatureBarkerElementConverter {
    public static final FeaturedBarkerElement convert(final FeatureBarkerElement element) {
        if (element == null) {
            return null;
        }
        return FeaturedBarkerElement.builder().withId(element.getId()).withBarker(FeaturedBarkerItemElementConverter.convert(element.getBarker())).withOnViewed(element.getOnViewed()).withOnContentFirstViewed(element.getOnContentFirstViewed()).build();
    }
}
