package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerCompactElement;

/* JADX INFO: loaded from: classes5.dex */
public final class VisualShovelerCompactElementConverter {
    public static final VisualShovelerCompactElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.VisualShovelerCompactElement element) {
        if (element == null) {
            return null;
        }
        return VisualShovelerCompactElement.builder().withId(element.getId()).withHeader(element.getHeader()).withItems(VerticalItemElementConverter.convert(element.getItems())).withOnViewed(element.getOnViewed()).withOnContentFirstViewed(element.getOnContentFirstViewed()).withSeeMore(VisualShovelerSeeMoreItemElementConverter.convert(element.getSeeMore())).build();
    }

    public static final VisualShovelerCompactElement convert(final Podcast.Touch.WidgetsInterface.v1_0.VisualShovelerCompactElement element) {
        if (element == null) {
            return null;
        }
        return VisualShovelerCompactElement.builder().withId(element.getId()).withHeader(element.getHeader()).withItems(element.getItems()).withOnViewed(element.getOnViewed()).withOnContentFirstViewed(element.getOnContentFirstViewed()).withSeeMore(VisualShovelerSeeMoreItemElementConverter.convert(element.getSeeMore())).build();
    }
}
