package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.CompactPillNavigatorElementInterface.v1_0.CompactPillNavigator;

/* JADX INFO: loaded from: classes5.dex */
public final class CompactPillNavigatorConverter {
    public static final CompactPillNavigator convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.CompactPillNavigator element) {
        if (element == null) {
            return null;
        }
        return CompactPillNavigator.builder().withItems(CompactPillItemElementConverter.convert(element.getItems())).build();
    }
}
