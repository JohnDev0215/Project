package com.amazon.music.explore.widgets.binders;

import CoreInterface.v1_0.Method;
import android.util.LruCache;
import com.amazon.music.explore.widgets.models.ExploreModel;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
final class ViewTriggerUtility {
    private static final int CACHE_SIZE = 10;
    private static ViewTriggerUtility instance;
    private final LruCache<String, ExploreModel> viewTriggerCache = new LruCache<>(10);

    private ViewTriggerUtility() {
    }

    public static synchronized ViewTriggerUtility getInstance() {
        if (instance == null) {
            instance = new ViewTriggerUtility();
        }
        return instance;
    }

    public void triggerElementView(ExploreModel exploreModel, List<Method> list) {
        if (exploreModel != this.viewTriggerCache.get(exploreModel.getOwnerId())) {
            exploreModel.getMethodsDispatcher().dispatchMethods(exploreModel.getOwnerId(), list);
            this.viewTriggerCache.put(exploreModel.getOwnerId(), exploreModel);
        }
    }
}
