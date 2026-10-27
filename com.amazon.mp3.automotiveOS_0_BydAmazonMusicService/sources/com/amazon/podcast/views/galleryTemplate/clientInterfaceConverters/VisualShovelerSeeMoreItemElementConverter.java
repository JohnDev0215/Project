package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.GalleryTemplateInterface.v1_0.VerticalSeeMoreItemElement;
import Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerSeeMoreItemElement;

/* JADX INFO: loaded from: classes5.dex */
final class VisualShovelerSeeMoreItemElementConverter {
    VisualShovelerSeeMoreItemElementConverter() {
    }

    static final VisualShovelerSeeMoreItemElement convert(final VerticalSeeMoreItemElement element) {
        if (element == null) {
            return null;
        }
        return VisualShovelerSeeMoreItemElement.builder().withOnItemSelected(element.getOnItemSelected()).withText(element.getText()).build();
    }

    static final VisualShovelerSeeMoreItemElement convert(final Podcast.Touch.VerticalSeeMoreItemElementInterface.v1_0.VerticalSeeMoreItemElement element) {
        if (element == null) {
            return null;
        }
        return VisualShovelerSeeMoreItemElement.builder().withOnItemSelected(element.getOnItemSelected()).withText(element.getText()).build();
    }
}
