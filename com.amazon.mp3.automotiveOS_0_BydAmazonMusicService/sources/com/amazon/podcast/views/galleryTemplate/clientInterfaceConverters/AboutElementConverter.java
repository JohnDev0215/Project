package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.GalleryTemplateInterface.v2_0.AboutElement;

/* JADX INFO: loaded from: classes5.dex */
public final class AboutElementConverter {
    public static final AboutElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.AboutElement element) {
        if (element == null) {
            return null;
        }
        return AboutElement.builder().withDescription(element.getDescription()).withTrailer(TrailerElementConverter.convert(element.getTrailer())).withCompactPillNavigator(CompactPillNavigatorConverter.convert(element.getCompactPillNavigator())).build();
    }
}
