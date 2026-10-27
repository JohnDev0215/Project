package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElement;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class VerticalItemElementConverter {
    public static final List<VerticalItemElement> convert(final List<Podcast.Touch.GalleryTemplateInterface.v1_0.VerticalItemElement> galleryElements) {
        ArrayList arrayList = new ArrayList();
        for (Podcast.Touch.GalleryTemplateInterface.v1_0.VerticalItemElement verticalItemElement : galleryElements) {
            if (verticalItemElement != null) {
                arrayList.add(convert(verticalItemElement));
            }
        }
        return arrayList;
    }

    private static VerticalItemElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.VerticalItemElement element) {
        if (element == null) {
            return null;
        }
        return VerticalItemElement.builder().withAltText(element.getAltText()).withBookmark(BookmarkElementConverter.convert(element.getBookmark())).withImage(element.getImage()).withIsDisabled(Boolean.TRUE.equals(element.isIsDisabled())).withOnItemSelected(element.getOnItemSelected()).withPrimaryText(element.getPrimaryText()).withSecondaryText(element.getSecondaryText()).build();
    }
}
