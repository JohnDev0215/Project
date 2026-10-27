package com.amazon.podcast.views.episodeRowItemsListView;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import SOATemplateListInterface.v1_0.TemplateElement;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.views.FooterElement;
import com.amazon.podcast.views.FooterItemViewHolder;
import com.amazon.podcast.views.TemplateContext;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class EpisodeRowItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private UiMetricAttributes$ContentName contentName;
    private final Context context;
    private final int episodeRowItemWidth;
    private FragmentManager fragmentManager;
    private List<TemplateElement> items;
    private final LifecycleOwner lifecycleOwner;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final UiMetricAttributes$PageType pageType;
    private final Resources resources;
    private String seeAllPageTitle;
    private final TemplateContext templateContext;

    private enum ViewType {
        episodeRow(0),
        footer(1);

        private final int value;

        ViewType(final int value) {
            this.value = value;
        }
    }

    public EpisodeRowItemsAdapter(final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner, final Context context, final TemplateContext templateContext, final UiMetricAttributes$PageType pageType) {
        this.context = context;
        this.resources = context.getResources();
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.templateContext = templateContext;
        this.pageType = pageType;
        this.episodeRowItemWidth = 0;
        this.contentName = EpisodeRowItemsMethods.getContentName(pageType);
        this.items = new ArrayList();
    }

    public EpisodeRowItemsAdapter(final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner, final Context context, final TemplateContext templateContext, final int episodeRowItemWidth, final UiMetricAttributes$PageType pageType) {
        this.context = context;
        this.resources = context.getResources();
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.templateContext = templateContext;
        this.episodeRowItemWidth = episodeRowItemWidth;
        this.pageType = pageType;
        this.contentName = EpisodeRowItemsMethods.getContentName(pageType);
        this.items = new ArrayList();
    }

    public EpisodeRowItemsAdapter(final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner, final Context context, final FragmentManager fragmentManager, final int episodeRowItemWidth, final UiMetricAttributes$PageType pageType) {
        this.context = context;
        this.resources = context.getResources();
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.fragmentManager = fragmentManager;
        this.templateContext = null;
        this.episodeRowItemWidth = episodeRowItemWidth;
        this.pageType = pageType;
        this.contentName = EpisodeRowItemsMethods.getContentName(pageType);
        this.items = new ArrayList();
    }

    public EpisodeRowItemsAdapter(final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner, final Context context, final TemplateContext templateContext, final UiMetricAttributes$PageType pageType, final String seeAllPageTitle) {
        this.context = context;
        this.resources = context.getResources();
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.templateContext = templateContext;
        this.pageType = pageType;
        this.seeAllPageTitle = seeAllPageTitle;
        this.items = new ArrayList();
        this.episodeRowItemWidth = 0;
    }

    public void addItems(final List<EpisodeRowItemElement> items) {
        if (CollectionUtils.isEmpty(items)) {
            return;
        }
        int size = this.items.size() - 1;
        if (this.items.get(size) instanceof FooterElement) {
            this.items.remove(size);
            this.items.addAll(items);
            this.items.add(new FooterElement());
        } else {
            this.items.addAll(items);
        }
        notifyDataSetChanged();
    }

    public List<TemplateElement> getItems() {
        return this.items;
    }

    public void removeItems(final List<EpisodeRowItemElement> items) {
        if (CollectionUtils.isEmpty(items)) {
            return;
        }
        this.items.removeAll(items);
        notifyDataSetChanged();
    }

    public void bind(final List<EpisodeRowItemElement> items) {
        if (items == null) {
            return;
        }
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void addFooterItem() {
        this.items.add(new FooterElement());
        notifyItemInserted(this.items.size() - 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int viewType) {
        EpisodeRowItemView episodeRowItemView;
        if (ViewType.episodeRow.value == viewType) {
            if (UiMetricAttributes$PageType.BROWSE_HOME.equals(this.pageType)) {
                episodeRowItemView = new EpisodeRowItemView(this.context, this.ownerId, this.methodsDispatcher, this.lifecycleOwner, this.fragmentManager, this.pageType, this.seeAllPageTitle);
            } else {
                episodeRowItemView = new EpisodeRowItemView(this.context, this.ownerId, this.methodsDispatcher, this.lifecycleOwner, this.templateContext, this.pageType, this.seeAllPageTitle);
            }
            int i = this.episodeRowItemWidth;
            episodeRowItemView.setLayoutParams(new RecyclerView.LayoutParams(i != 0 ? i : -1, -2));
            return new EpisodeRowItemViewHolder(episodeRowItemView);
        }
        return new FooterItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.podcast_explore_item, viewGroup, false), this.ownerId, this.methodsDispatcher, this.resources, this.context, this.pageType, this.contentName);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof EpisodeRowItemViewHolder) {
            ((EpisodeRowItemViewHolder) viewHolder).bind((EpisodeRowItemElement) this.items.get(position));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<TemplateElement> list = this.items;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return this.items.get(position) instanceof EpisodeRowItemElement ? ViewType.episodeRow.value : ViewType.footer.value;
    }
}
