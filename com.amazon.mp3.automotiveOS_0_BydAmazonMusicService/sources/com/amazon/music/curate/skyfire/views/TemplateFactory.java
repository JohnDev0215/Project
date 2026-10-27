package com.amazon.music.curate.skyfire.views;

import CoreInterface.v1_0.Template;
import Touch.GalleryTemplateInterface.v1_0.GalleryTemplate;
import android.view.View;
import com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders;
import com.amazon.music.curate.skyfire.views.defaultTemplate.DefaultTemplateView;
import com.amazon.music.curate.skyfire.views.galleryTemplate.GalleryTemplateView;

/* JADX INFO: loaded from: classes4.dex */
public final class TemplateFactory {
    public static View fromTemplate(final Template template, final TemplateViewContext templateViewContext, final CurateBootstrapProviders curateBootstrapProviders) {
        if (template instanceof GalleryTemplate) {
            return new GalleryTemplateView(templateViewContext, curateBootstrapProviders);
        }
        return new DefaultTemplateView(templateViewContext);
    }
}
