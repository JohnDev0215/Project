package com.amazon.podcast.views.galleryTemplate;

import SOACoreInterface.v1_0.Method;
import android.view.ViewTreeObserver;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.metrics.ui.UIContentViewCache.UIContentViewCacheItem;
import com.amazon.podcast.metrics.ui.UIContentViewCache.UIContentViewWidgetIndexCache;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public interface ScrollableWidgetView {
    public static final UIContentViewWidgetIndexCache contentViewCache = UIContentViewWidgetIndexCache.INSTANCE;

    default void handleFirstOnContentFirstViewed(final RecyclerView recyclerView, final String widgetId, final MethodsDispatcher methodsDispatcher, final String ownerId, final List<Method> onContentFirstViewed) {
        if (recyclerView == null) {
            return;
        }
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.podcast.views.galleryTemplate.ScrollableWidgetView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ScrollableWidgetView.this.handleOnContentFirstViewed(recyclerView, widgetId, methodsDispatcher, ownerId, onContentFirstViewed);
                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    default void addOnScrollListener(final RecyclerView recyclerView, final String widgetId, final MethodsDispatcher methodsDispatcher, final String ownerId, final List<Method> onContentFirstViewed) {
        if (recyclerView == null) {
            return;
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.podcast.views.galleryTemplate.ScrollableWidgetView.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NonNull final RecyclerView recyclerView2, final int newState) {
                super.onScrollStateChanged(recyclerView2, newState);
                if (newState == 0) {
                    ScrollableWidgetView.this.handleOnContentFirstViewed(recyclerView2, widgetId, methodsDispatcher, ownerId, onContentFirstViewed);
                }
            }
        });
    }

    default void handleOnContentFirstViewed(final RecyclerView recyclerView, final String widgetId, final MethodsDispatcher methodsDispatcher, final String ownerId, final List<Method> onContentFirstViewed) {
        UIContentViewCacheItem widget;
        if (widgetId == null || (widget = contentViewCache.getWidget(widgetId)) == null) {
            return;
        }
        updateViewedIndicesOnContentViewed(widgetId, recyclerView);
        int size = widget.getBatchesOfIndicesToReport().size();
        for (int i = 0; i < size; i++) {
            methodsDispatcher.dispatch(ownerId, onContentFirstViewed);
        }
    }

    default void updateViewedIndicesOnContentViewed(final String widgetId, final RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        if (linearLayoutManager == null) {
            return;
        }
        int iFindLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i <= iFindLastVisibleItemPosition; i++) {
            arrayList.add(Integer.valueOf(i));
        }
        if (StringUtils.isBlank(widgetId)) {
            return;
        }
        contentViewCache.updateViewedIndices(widgetId, arrayList);
    }
}
