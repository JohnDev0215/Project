package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.RegularBarkerElementInterface.v1_0.RegularBarkerElement;

/* JADX INFO: loaded from: classes5.dex */
public final class RegularBarkerElementConverter {
    public static final RegularBarkerElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.RegularBarkerElement element) {
        if (element == null) {
            return null;
        }
        return RegularBarkerElement.builder().withId(element.getId()).withBarker(BarkerItemElementConverter.convert(element)).withOnViewed(element.getOnViewed()).withOnContentFirstViewed(element.getOnContentFirstViewed()).build();
    }
}
