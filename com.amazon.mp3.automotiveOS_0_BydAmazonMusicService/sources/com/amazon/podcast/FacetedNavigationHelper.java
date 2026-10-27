package com.amazon.podcast;

import Podcast.Touch.GalleryTemplateInterface.v2_0.GalleryTemplate;
import SOATemplateListInterface.v1_0.Template;

/* JADX INFO: loaded from: classes5.dex */
public class FacetedNavigationHelper {
    public static boolean isGalleryTemplateV2(final Template template) {
        return template instanceof GalleryTemplate;
    }
}
