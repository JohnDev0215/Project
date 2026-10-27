package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.BarkerItemElementInterface.v1_0.BarkerItemElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.RegularBarkerElement;

/* JADX INFO: loaded from: classes5.dex */
public final class BarkerItemElementConverter {
    public static final BarkerItemElement convert(final RegularBarkerElement element) {
        if (element == null) {
            return null;
        }
        return BarkerItemElement.builder().withTitle(element.getTitle()).withBackgroundImageSmall(element.getBackgroundImageSmall()).withBackgroundImageLarge(element.getBackgroundImageLarge()).withOnItemSelected(element.getOnItemSelected()).build();
    }
}
