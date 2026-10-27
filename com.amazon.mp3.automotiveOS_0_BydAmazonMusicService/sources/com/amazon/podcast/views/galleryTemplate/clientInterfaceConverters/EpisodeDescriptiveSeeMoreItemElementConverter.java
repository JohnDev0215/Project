package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.EpisodeDescriptiveShovelerElementInterface.v1_0.EpisodeDescriptiveSeeMoreItemElement;

/* JADX INFO: loaded from: classes5.dex */
public final class EpisodeDescriptiveSeeMoreItemElementConverter {
    public static final EpisodeDescriptiveSeeMoreItemElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodeDescriptiveSeeMoreItemElement element) {
        if (element == null) {
            return null;
        }
        return EpisodeDescriptiveSeeMoreItemElement.builder().withText(element.getText()).withOnItemSelected(element.getOnItemSelected()).build();
    }
}
