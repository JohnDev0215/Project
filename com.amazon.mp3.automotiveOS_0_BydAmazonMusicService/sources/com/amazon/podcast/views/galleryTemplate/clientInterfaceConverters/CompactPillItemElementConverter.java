package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.CompactPillItemElementInterface.v1_0.CompactPillItemElement;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class CompactPillItemElementConverter {
    public static final List<CompactPillItemElement> convert(final List<Podcast.Touch.GalleryTemplateInterface.v1_0.CompactPillItemElement> galleryElements) {
        ArrayList arrayList = new ArrayList();
        for (Podcast.Touch.GalleryTemplateInterface.v1_0.CompactPillItemElement compactPillItemElement : galleryElements) {
            if (compactPillItemElement != null) {
                arrayList.add(convert(compactPillItemElement));
            }
        }
        return arrayList;
    }

    private static CompactPillItemElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.CompactPillItemElement element) {
        if (element == null) {
            return null;
        }
        return CompactPillItemElement.builder().withText(element.getText()).withOnItemSelected(element.getOnItemSelected()).build();
    }
}
