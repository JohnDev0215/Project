package com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters;

import Podcast.Touch.PillItemElementInterface.v1_0.PillItemElement;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class PillItemElementConverter {
    public static final List<PillItemElement> convert(final List<Podcast.Touch.GalleryTemplateInterface.v1_0.PillItemElement> galleryElements) {
        ArrayList arrayList = new ArrayList();
        for (Podcast.Touch.GalleryTemplateInterface.v1_0.PillItemElement pillItemElement : galleryElements) {
            if (pillItemElement != null) {
                arrayList.add(convert(pillItemElement));
            }
        }
        return arrayList;
    }

    private static PillItemElement convert(final Podcast.Touch.GalleryTemplateInterface.v1_0.PillItemElement element) {
        if (element == null) {
            return null;
        }
        return PillItemElement.builder().withText(element.getText()).withOnItemSelected(element.getOnItemSelected()).build();
    }
}
