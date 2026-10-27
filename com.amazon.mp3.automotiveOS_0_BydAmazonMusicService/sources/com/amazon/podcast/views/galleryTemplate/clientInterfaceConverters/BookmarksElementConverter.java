package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.WidgetElementsInterface.v1_0.BookmarksElement;

/* JADX INFO: loaded from: classes5.dex */
public final class BookmarksElementConverter {
    public static final BookmarksElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.BookmarksElement element) {
        if (element == null) {
            return null;
        }
        return BookmarksElement.builder().withId(element.getId()).withHeader(element.getHeader()).withSeeMoreText(element.getSeeMoreText()).withOnSeeMoreSelected(element.getOnSeeMoreSelected()).withOnContentFirstViewed(element.getOnContentFirstViewed()).withOnViewed(element.getOnViewed()).build();
    }
}
