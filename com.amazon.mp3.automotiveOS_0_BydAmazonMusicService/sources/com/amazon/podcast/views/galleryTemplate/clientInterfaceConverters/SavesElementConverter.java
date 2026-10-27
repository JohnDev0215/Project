package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.WidgetElementsInterface.v1_0.SavesElement;

/* JADX INFO: loaded from: classes5.dex */
public final class SavesElementConverter {
    public static final SavesElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.SavesElement element) {
        if (element == null) {
            return null;
        }
        return SavesElement.builder().withId(element.getId()).withHeader(element.getHeader()).withSeeMoreText(element.getSeeMoreText()).withOnViewed(element.getOnViewed()).withOnContentFirstViewed(element.getOnContentFirstViewed()).build();
    }
}
