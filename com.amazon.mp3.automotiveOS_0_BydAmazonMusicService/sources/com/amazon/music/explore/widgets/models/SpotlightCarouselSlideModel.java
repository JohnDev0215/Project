package com.amazon.music.explore.widgets.models;

import Touch.WidgetsInterface.v1_0.SpotlightCarouselSlideElement;
import com.amazon.music.skyfire.platform.MethodsDispatcher;

/* JADX INFO: loaded from: classes4.dex */
public class SpotlightCarouselSlideModel extends ExploreModel {
    private final SpotlightCarouselSlideElement slide;

    public SpotlightCarouselSlideModel(String str, MethodsDispatcher methodsDispatcher, SpotlightCarouselSlideElement spotlightCarouselSlideElement) {
        super(str, methodsDispatcher);
        this.slide = spotlightCarouselSlideElement;
    }

    public SpotlightCarouselSlideElement getSlide() {
        return this.slide;
    }
}
