package com.amazon.music.explore.widgets.models;

import Touch.WidgetsInterface.v1_0.SpotlightCarouselElement;
import Touch.WidgetsInterface.v1_0.SpotlightCarouselSlideElement;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class SpotlightCarouselModel extends ExploreModel {
    private final List<BaseViewModel> carouselSlideModels;
    private final SpotlightCarouselElement widget;

    public SpotlightCarouselModel(String str, MethodsDispatcher methodsDispatcher, SpotlightCarouselElement spotlightCarouselElement) {
        super(str, methodsDispatcher);
        this.widget = spotlightCarouselElement;
        this.carouselSlideModels = createCarouselSlideModels();
    }

    public SpotlightCarouselElement getWidget() {
        return this.widget;
    }

    public List<BaseViewModel> getCarouselSlideModels() {
        return this.carouselSlideModels;
    }

    private List<BaseViewModel> createCarouselSlideModels() {
        ArrayList arrayList = new ArrayList();
        Iterator<SpotlightCarouselSlideElement> it = getWidget().items().iterator();
        while (it.hasNext()) {
            arrayList.add(new SpotlightCarouselSlideModel(null, getMethodsDispatcher(), it.next()));
        }
        return arrayList;
    }
}
