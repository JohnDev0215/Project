package com.amazon.music.explore.widgets.models;

import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.views.library.models.base.BaseViewModel;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreModel extends BaseViewModel {
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;

    public ExploreModel(String str, MethodsDispatcher methodsDispatcher) {
        super(null, null);
        this.ownerId = str;
        this.methodsDispatcher = methodsDispatcher;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public MethodsDispatcher getMethodsDispatcher() {
        return this.methodsDispatcher;
    }
}
