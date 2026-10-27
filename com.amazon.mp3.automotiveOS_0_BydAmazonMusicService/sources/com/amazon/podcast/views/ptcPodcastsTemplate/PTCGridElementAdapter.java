package com.amazon.podcast.views.ptcPodcastsTemplate;

import Podcast.Touch.PTCTemplateInterface.v1_0.GridItemElement;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.ptcPodcastsTemplate.PTCGridElementViewHolder;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
final class PTCGridElementAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private PTCGridElementViewHolder.OnBottomSheetSelectedListener bottomSheetSelectedListener;
    private FragmentManager fragmentManager;
    private PTCGridElementViewHolder.OnGridItemUpdateListener gridItemUpdateListener;
    private List<PTCPodcastsTemplateSection> items;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private Map<String, GridItemElement> selectedItems;
    private TemplateContext templateContext;

    private enum ViewType {
        header(0),
        gridElement(1);

        private final int value;

        ViewType(final int value) {
            this.value = value;
        }
    }

    PTCGridElementAdapter(final String ownerId, final TemplateContext templateContext, final MethodsDispatcher methodsDispatcher, final FragmentManager fragmentManager, final PTCGridElementViewHolder.OnGridItemUpdateListener gridItemUpdateListener, final PTCGridElementViewHolder.OnBottomSheetSelectedListener bottomSheetSelectedListener) {
        this.ownerId = ownerId;
        this.templateContext = templateContext;
        this.methodsDispatcher = methodsDispatcher;
        this.fragmentManager = fragmentManager;
        this.gridItemUpdateListener = gridItemUpdateListener;
        this.bottomSheetSelectedListener = bottomSheetSelectedListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int viewType) {
        if (viewType == ViewType.gridElement.value) {
            return new PTCGridElementViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.ptc_grid_widget, viewGroup, false), this.ownerId, this.templateContext, this.methodsDispatcher, this.fragmentManager, this.gridItemUpdateListener, this.bottomSheetSelectedListener);
        }
        return new PTCHeaderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.ptc_header_view, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof PTCGridElementViewHolder) {
            ((PTCGridElementViewHolder) viewHolder).bind(this.items.get(i).getElement(), this.selectedItems);
        } else if (viewHolder instanceof PTCHeaderViewHolder) {
            PTCPodcastsTemplateSection pTCPodcastsTemplateSection = this.items.get(i);
            ((PTCHeaderViewHolder) viewHolder).bind(pTCPodcastsTemplateSection.getTitle(), pTCPodcastsTemplateSection.getSubtitle());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(final int position) {
        return this.items.get(position).isHeader() ? ViewType.header.value : ViewType.gridElement.value;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(final RecyclerView.ViewHolder viewHolder) {
        if (viewHolder instanceof PTCGridElementViewHolder) {
            ((PTCGridElementViewHolder) viewHolder).handleOnViewed();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<PTCPodcastsTemplateSection> list = this.items;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void bind(final List<PTCPodcastsTemplateSection> items, final Map<String, GridItemElement> selectedItems) {
        this.items = items;
        this.selectedItems = selectedItems;
        notifyDataSetChanged();
    }

    void addItems(final List<PTCPodcastsTemplateSection> items) {
        if (items == null || items.isEmpty()) {
            return;
        }
        int itemCount = getItemCount();
        this.items.addAll(items);
        notifyItemRangeInserted(itemCount, items.size());
    }
}
