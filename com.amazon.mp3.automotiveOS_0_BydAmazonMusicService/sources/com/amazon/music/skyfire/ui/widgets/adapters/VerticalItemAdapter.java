package com.amazon.music.skyfire.ui.widgets.adapters;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.views.library.views.VerticalTileView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VerticalItemAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001%B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0016\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011H\u0016J\u0006\u0010\u0019\u001a\u00020\u0017J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\fH\u0002J\u0010\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001bH\u0002J\u0018\u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001bH\u0016J\u0018\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001e\u001a\u00020\u001bH\u0016J\u0010\u0010$\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001bH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/adapters/VerticalItemAdapter;", "Lcom/amazon/music/skyfire/ui/widgets/adapters/AbstractVerticalItemAdapter;", "Lcom/amazon/music/skyfire/ui/widgets/adapters/VerticalItemAdapter$VerticalItemViewHolder;", "ownerId", "", "dispatcher", "Lcom/amazon/music/skyfire/platform/MethodsDispatcher;", "LCoreInterface/v1_0/Method;", "bindingProvider", "Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "(Ljava/lang/String;Lcom/amazon/music/skyfire/platform/MethodsDispatcher;Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;)V", "isLoadingMore", "", ContextMappingConstants.ITEMS, "", "LTouch/WidgetsInterface/v1_0/VerticalItemElement;", "onEndOfList", "", "getOnEndOfList", "()Ljava/util/List;", "setOnEndOfList", "(Ljava/util/List;)V", "addItems", "", "newItems", "clear", "getItemCount", "", "hasMoreItems", "isEndOfList", "position", "onBindViewHolder", "viewHolder", "onCreateViewHolder", "viewGroup", "Landroid/view/ViewGroup;", "shouldLoadMore", "VerticalItemViewHolder", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VerticalItemAdapter extends AbstractVerticalItemAdapter<VerticalItemViewHolder> {
    private final ElementBindingProvider bindingProvider;
    private final MethodsDispatcher<Method> dispatcher;
    private boolean isLoadingMore;
    private List<VerticalItemElement> items;
    private List<? extends Method> onEndOfList;
    private final String ownerId;

    public VerticalItemAdapter(String ownerId, MethodsDispatcher<Method> dispatcher, ElementBindingProvider bindingProvider) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        Intrinsics.checkNotNullParameter(bindingProvider, "bindingProvider");
        this.ownerId = ownerId;
        this.dispatcher = dispatcher;
        this.bindingProvider = bindingProvider;
        this.items = new ArrayList();
    }

    public final List<Method> getOnEndOfList() {
        return this.onEndOfList;
    }

    public final void setOnEndOfList(List<? extends Method> list) {
        this.onEndOfList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public VerticalItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        View viewCreateView = this.bindingProvider.createView(this.items.get(position), viewGroup);
        if (viewCreateView != null) {
            return new VerticalItemViewHolder((VerticalTileView) viewCreateView);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.views.VerticalTileView");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(VerticalItemViewHolder viewHolder, int position) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        this.bindingProvider.bindItem(this.items.get(position), viewHolder.getView(), getTileWidth());
        if (shouldLoadMore(position)) {
            MethodsDispatcher<Method> methodsDispatcher = this.dispatcher;
            String str = this.ownerId;
            List<? extends Method> listEmptyList = this.onEndOfList;
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt__CollectionsKt.emptyList();
            }
            methodsDispatcher.dispatchMethods(str, listEmptyList);
            this.isLoadingMore = true;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    @Override // com.amazon.music.skyfire.ui.widgets.adapters.AbstractVerticalItemAdapter
    public void addItems(List<? extends VerticalItemElement> newItems) {
        Intrinsics.checkNotNullParameter(newItems, "newItems");
        int size = this.items.size();
        this.items.addAll(newItems);
        notifyItemRangeInserted(size, newItems.size());
        this.isLoadingMore = false;
    }

    public final void clear() {
        this.items.clear();
        notifyDataSetChanged();
    }

    private final boolean shouldLoadMore(int position) {
        return !this.isLoadingMore && isEndOfList(position) && hasMoreItems();
    }

    private final boolean hasMoreItems() {
        return this.onEndOfList != null;
    }

    private final boolean isEndOfList(int position) {
        return position >= this.items.size() - 1;
    }

    /* JADX INFO: compiled from: VerticalItemAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/adapters/VerticalItemAdapter$VerticalItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Lcom/amazon/music/views/library/views/VerticalTileView;", "(Lcom/amazon/music/views/library/views/VerticalTileView;)V", "getView", "()Lcom/amazon/music/views/library/views/VerticalTileView;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class VerticalItemViewHolder extends RecyclerView.ViewHolder {
        private final VerticalTileView view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public VerticalItemViewHolder(VerticalTileView view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.view = view;
        }

        public final VerticalTileView getView() {
            return this.view;
        }
    }
}
