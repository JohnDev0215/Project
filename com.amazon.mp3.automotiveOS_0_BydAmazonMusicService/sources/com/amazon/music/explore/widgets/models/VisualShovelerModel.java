package com.amazon.music.explore.widgets.models;

import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import Touch.WidgetsInterface.v1_0.VisualShovelerElement;
import com.amazon.music.explore.views.merch.VerticalItemElementBinder;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class VisualShovelerModel extends ExploreModel {
    private final List<String> tags;
    private final List<BaseViewModel> verticalTileModels;
    private final VisualShovelerElement widget;

    public VisualShovelerModel(String str, MethodsDispatcher methodsDispatcher, VisualShovelerElement visualShovelerElement, List<String> list) {
        super(str, methodsDispatcher);
        this.widget = visualShovelerElement;
        this.verticalTileModels = createVerticalTileModels();
        this.tags = list != null ? Collections.unmodifiableList(new ArrayList(list)) : null;
    }

    public VisualShovelerElement getWidget() {
        return this.widget;
    }

    public List<BaseViewModel> getVerticalTileModels() {
        return this.verticalTileModels;
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public List<String> getTags() {
        return this.tags;
    }

    private List<BaseViewModel> createVerticalTileModels() {
        ArrayList arrayList = new ArrayList();
        Iterator<VerticalItemElement> it = getWidget().items().iterator();
        while (it.hasNext()) {
            arrayList.add(VerticalItemElementBinder.bind(it.next(), getOwnerId(), getMethodsDispatcher()));
        }
        return arrayList;
    }
}
