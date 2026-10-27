package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.GalleryTemplateInterface.v1_0.ThumbnailNavigationWidgetElement;
import Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0.ThumbnailNavigatorWidgetElement;

/* JADX INFO: loaded from: classes5.dex */
public final class ThumbnailNavigationWidgetElementConverter {
    public static final ThumbnailNavigatorWidgetElement convert(final ThumbnailNavigationWidgetElement element) {
        if (element == null) {
            return null;
        }
        return ThumbnailNavigatorWidgetElement.builder().withId(element.getId()).withHeader(element.getHeader()).withItems(element.getItems()).withOnViewed(element.getOnViewed()).withOnContentFirstViewed(element.getOnContentFirstViewed()).build();
    }
}
