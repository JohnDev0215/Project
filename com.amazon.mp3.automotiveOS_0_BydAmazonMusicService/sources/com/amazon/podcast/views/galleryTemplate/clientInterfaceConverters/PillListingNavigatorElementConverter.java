package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.PillListingNavigatorElementInterface.v1_0.PillListingNavigatorElement;

/* JADX INFO: loaded from: classes5.dex */
public final class PillListingNavigatorElementConverter {
    public static final PillListingNavigatorElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.PillListingNavigatorElement element) {
        if (element == null) {
            return null;
        }
        return PillListingNavigatorElement.builder().withId(element.getId()).withHeader(element.getHeader()).withItems(PillItemElementConverter.convert(element.getItems())).withOnViewed(element.getOnViewed()).withOnContentFirstViewed(element.getOnContentFirstViewed()).build();
    }
}
