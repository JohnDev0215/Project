package com.amazon.podcast.views.episodeRowItemsListView;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.TemplateElement;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.views.DividerItemDecorator;
import com.amazon.podcast.views.TemplateContext;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class EpisodeRowItemsListView extends RelativeLayout {
    private EpisodeRowItemsAdapter adapter;
    private final Context context;
    private boolean isEndOfListTriggered;
    private LinearLayoutManager layoutManager;
    private LifecycleOwner lifecycleOwner;
    private MethodsDispatcher methodsDispatcher;
    private List<Method> onEndOfList;
    private List<Method> onViewed;
    private String ownerId;
    private UiMetricAttributes$PageType pageType;
    private RecyclerView recyclerView;
    private String seeAllPageTitle;
    private TemplateContext templateContext;

    public EpisodeRowItemsListView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public void create(final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner, final TemplateContext templateContext, final UiMetricAttributes$PageType pageType) {
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.templateContext = templateContext;
        this.pageType = pageType;
        EpisodeRowItemsAdapter episodeRowItemsAdapter = new EpisodeRowItemsAdapter(ownerId, methodsDispatcher, lifecycleOwner, getContext(), templateContext, pageType);
        this.adapter = episodeRowItemsAdapter;
        this.recyclerView.setAdapter(episodeRowItemsAdapter);
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemsListView.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (EpisodeRowItemsListView.this.layoutManager.getItemCount() - EpisodeRowItemsListView.this.layoutManager.findLastVisibleItemPosition() > 4 || EpisodeRowItemsListView.this.isEndOfListTriggered) {
                    return;
                }
                methodsDispatcher.dispatch(ownerId, EpisodeRowItemsListView.this.onEndOfList);
                EpisodeRowItemsListView.this.isEndOfListTriggered = true;
            }
        });
        this.isEndOfListTriggered = false;
    }

    public void create(final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner, final TemplateContext templateContext, final UiMetricAttributes$PageType pageType, final String seeAllPageTitle) {
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.templateContext = templateContext;
        this.pageType = pageType;
        this.seeAllPageTitle = seeAllPageTitle;
        EpisodeRowItemsAdapter episodeRowItemsAdapter = new EpisodeRowItemsAdapter(ownerId, methodsDispatcher, lifecycleOwner, getContext(), templateContext, pageType, seeAllPageTitle);
        this.adapter = episodeRowItemsAdapter;
        this.recyclerView.setAdapter(episodeRowItemsAdapter);
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemsListView.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (EpisodeRowItemsListView.this.layoutManager.getItemCount() - EpisodeRowItemsListView.this.layoutManager.findLastVisibleItemPosition() > 4 || EpisodeRowItemsListView.this.isEndOfListTriggered) {
                    return;
                }
                methodsDispatcher.dispatch(ownerId, EpisodeRowItemsListView.this.onEndOfList);
                EpisodeRowItemsListView.this.isEndOfListTriggered = true;
            }
        });
        this.isEndOfListTriggered = false;
    }

    public void bind(final List<EpisodeRowItemElement> items, final List<Method> onEndOfListMethods, final List<Method> onViewedMethods) {
        this.adapter.bind(items);
        this.onEndOfList = onEndOfListMethods;
        this.onViewed = onViewedMethods;
        this.isEndOfListTriggered = false;
    }

    public void addItems(final List<Method> onEndOfListMethods, final List<Method> onViewedMethods, final List<EpisodeRowItemElement> items) {
        this.onEndOfList = onEndOfListMethods;
        this.onViewed = onViewedMethods;
        this.adapter.addItems(items);
        handleOnViewed();
        this.isEndOfListTriggered = false;
    }

    public void addFooter() {
        this.adapter.addFooterItem();
    }

    public List<TemplateElement> getItems() {
        return this.adapter.getItems();
    }

    public void removeItems(final List<EpisodeRowItemElement> items) {
        this.adapter.removeItems(items);
    }

    public void handleOnViewed() {
        this.methodsDispatcher.dispatch(this.ownerId, this.onViewed);
    }

    public void onConfigurationChanged() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 1, false);
        this.layoutManager = linearLayoutManager;
        this.recyclerView.setLayoutManager(linearLayoutManager);
        EpisodeRowItemsAdapter episodeRowItemsAdapter = new EpisodeRowItemsAdapter(this.ownerId, this.methodsDispatcher, this.lifecycleOwner, getContext(), this.templateContext, this.pageType);
        this.adapter = episodeRowItemsAdapter;
        this.recyclerView.setAdapter(episodeRowItemsAdapter);
    }

    private void init() {
        View.inflate(this.context, R$layout.podcast_episode_row_items_list_view, this);
        this.recyclerView = (RecyclerView) findViewById(R$id.podcast_episode_row_items_list_view_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 1, false);
        this.layoutManager = linearLayoutManager;
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setItemViewCacheSize(10);
        this.recyclerView.addItemDecoration(new DividerItemDecorator(ContextCompat.getDrawable(this.context, R$drawable.visual_row_items_divider_view)));
    }
}
