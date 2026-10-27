package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.BookmarkElementInterface.v1_0.BookmarkElement;

/* JADX INFO: loaded from: classes5.dex */
final class BookmarkElementConverter {
    BookmarkElementConverter() {
    }

    static final BookmarkElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.BookmarkElement element) {
        if (element == null) {
            return null;
        }
        return BookmarkElement.builder().withId(element.getId()).build();
    }
}
