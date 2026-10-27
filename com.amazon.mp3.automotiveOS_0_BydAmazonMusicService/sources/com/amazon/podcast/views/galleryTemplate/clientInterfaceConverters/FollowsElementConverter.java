package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.WidgetElementsInterface.v1_0.FollowsElement;

/* JADX INFO: loaded from: classes5.dex */
public final class FollowsElementConverter {
    public static final FollowsElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.FollowsElement element) {
        if (element == null) {
            return null;
        }
        return FollowsElement.builder().withId(element.getId()).withHeader(element.getHeader()).withSeeMoreText(element.getSeeMoreText()).withOnSeeMoreSelected(element.getOnSeeMoreSelected()).withOnViewed(element.getOnViewed()).withOnContentFirstViewed(element.getOnContentFirstViewed()).build();
    }
}
