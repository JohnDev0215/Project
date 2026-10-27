package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.GalleryTemplateInterface.v2_0.TrailerElement;

/* JADX INFO: loaded from: classes5.dex */
public final class TrailerElementConverter {
    public static final TrailerElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.TrailerElement element) {
        if (element == null) {
            return null;
        }
        return TrailerElement.builder().withText(element.getText()).withOnItemSelected(element.getOnItemSelected()).build();
    }
}
