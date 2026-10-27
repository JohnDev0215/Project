package com.amazon.music.explore.widgets.models;

import Touch.WidgetsInterface.v1_0.FeaturedHorizontalShovelerElement;
import Touch.WidgetsInterface.v1_0.HorizontalItemElement;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class FeaturedShovelerModel extends ExploreModel {
    private final List<BaseViewModel> childrenModels;
    private final List<String> tags;
    private final FeaturedHorizontalShovelerElement widget;

    public FeaturedShovelerModel(String str, MethodsDispatcher methodsDispatcher, FeaturedHorizontalShovelerElement featuredHorizontalShovelerElement, List<String> list) {
        super(str, methodsDispatcher);
        this.widget = featuredHorizontalShovelerElement;
        this.childrenModels = new ArrayList();
        ArrayList arrayList = new ArrayList();
        this.tags = arrayList;
        if (list != null) {
            arrayList.addAll(list);
        }
    }

    public FeaturedHorizontalShovelerElement getWidget() {
        return this.widget;
    }

    public List<BaseViewModel> getChildrenModels() {
        return this.childrenModels;
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public List<String> getTags() {
        return this.tags;
    }

    public void resetModels(List<BaseViewModel> list) {
        this.childrenModels.clear();
        this.childrenModels.addAll(createChildModels());
        this.childrenModels.addAll(list);
    }

    private List<BaseViewModel> createChildModels() {
        ArrayList arrayList = new ArrayList();
        Iterator<HorizontalItemElement> it = this.widget.items().iterator();
        while (it.hasNext()) {
            arrayList.add(new HorizontalItemModel(getOwnerId(), getMethodsDispatcher(), it.next()));
        }
        return arrayList;
    }
}
