package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.FeaturedBarkerItemElementInterface.v1_0.FeaturedBarkerItemElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.BarkerItemElement;

/* JADX INFO: loaded from: classes5.dex */
public final class FeaturedBarkerItemElementConverter {
    public static final FeaturedBarkerItemElement convert(final BarkerItemElement element) {
        if (element == null) {
            return null;
        }
        return FeaturedBarkerItemElement.builder().withOnItemSelected(element.getOnItemSelected()).withImage(element.getImage()).withLabel(element.getLabel()).withPrimaryText(element.getPrimaryText()).withSecondaryText(element.getPrimaryText()).withBackgroundImageSmall(element.getBackgroundImageSmall()).withBackgroundImageLarge(element.getBackgroundImageLarge()).build();
    }
}
