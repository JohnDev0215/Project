package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.WidgetElementsInterface.v1_0.JumpBackInElement;

/* JADX INFO: loaded from: classes5.dex */
public final class JumpBackInElementConverter {
    public static final JumpBackInElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.JumpBackInElement element) {
        if (element == null) {
            return null;
        }
        return JumpBackInElement.builder().withId(element.getId()).withHeader(element.getHeader()).withSeeMoreText(element.getSeeMoreText()).withOnSeeMoreSelected(element.getOnSeeMoreSelected()).withOnViewed(element.getOnViewed()).withOnContentFirstViewed(element.getOnContentFirstViewed()).build();
    }
}
