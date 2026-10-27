package com.amazon.music.explore.widgets.binders;

import Touch.WidgetsInterface.v1_0.FeaturedHorizontalShovelerElement;
import Touch.WidgetsInterface.v1_0.HorizontalItemElement;
import com.amazon.music.explore.widgets.models.HorizontalItemModel;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.views.library.models.ShovelerModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class ShovelerElementBinder {
    public static ShovelerModel createFeaturedShovelerModel(String str, MethodsDispatcher methodsDispatcher, FeaturedHorizontalShovelerElement featuredHorizontalShovelerElement, List<String> list) {
        return new ShovelerModel(null, createHorizontalItemModels(str, methodsDispatcher, featuredHorizontalShovelerElement), 9, list, featuredHorizontalShovelerElement.primaryHeader(), null, null, null, null, false);
    }

    private static List<HorizontalItemModel> createHorizontalItemModels(String str, MethodsDispatcher methodsDispatcher, FeaturedHorizontalShovelerElement featuredHorizontalShovelerElement) {
        ArrayList arrayList = new ArrayList();
        Iterator<HorizontalItemElement> it = featuredHorizontalShovelerElement.items().iterator();
        while (it.hasNext()) {
            arrayList.add(new HorizontalItemModel(str, methodsDispatcher, it.next()));
        }
        return arrayList;
    }
}
