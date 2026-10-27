package com.amazon.music.skyfire.ui.widgets.adapters;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.ThumbnailNavigatorItemElement;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.views.library.views.ThumbnailCellView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ThumbnailNavigatorItemAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001cB#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0014\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u0013J\u0006\u0010\u0014\u001a\u00020\u0011J\b\u0010\u0015\u001a\u00020\u000fH\u0016J\u0018\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u000fH\u0016J\u0018\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u000fH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/adapters/ThumbnailNavigatorItemAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/music/skyfire/ui/widgets/adapters/ThumbnailNavigatorItemAdapter$ThumbnailNavigatorItemViewHolder;", "ownerId", "", "dispatcher", "Lcom/amazon/music/skyfire/platform/MethodsDispatcher;", "LCoreInterface/v1_0/Method;", "bindingProvider", "Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "(Ljava/lang/String;Lcom/amazon/music/skyfire/platform/MethodsDispatcher;Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;)V", ContextMappingConstants.ITEMS, "", "LTouch/WidgetsInterface/v1_0/ThumbnailNavigatorItemElement;", "thumbnailWidth", "", "addItems", "", "newItems", "", "clear", "getItemCount", "onBindViewHolder", "viewHolder", "position", "onCreateViewHolder", "viewGroup", "Landroid/view/ViewGroup;", "ThumbnailNavigatorItemViewHolder", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ThumbnailNavigatorItemAdapter extends RecyclerView.Adapter<ThumbnailNavigatorItemViewHolder> {
    private final ElementBindingProvider bindingProvider;
    private final MethodsDispatcher<Method> dispatcher;
    private final List<ThumbnailNavigatorItemElement> items;
    private final String ownerId;
    private int thumbnailWidth;

    public ThumbnailNavigatorItemAdapter(String ownerId, MethodsDispatcher<Method> dispatcher, ElementBindingProvider bindingProvider) {
        Intrinsics.checkNotNullParameter(ownerId, "ownerId");
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        Intrinsics.checkNotNullParameter(bindingProvider, "bindingProvider");
        this.ownerId = ownerId;
        this.dispatcher = dispatcher;
        this.bindingProvider = bindingProvider;
        this.items = new ArrayList();
        this.thumbnailWidth = -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ThumbnailNavigatorItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        View viewCreateView = this.bindingProvider.createView(this.items.get(position), viewGroup);
        if (viewCreateView != null) {
            return new ThumbnailNavigatorItemViewHolder((ThumbnailCellView) viewCreateView);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.views.ThumbnailCellView");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ThumbnailNavigatorItemViewHolder viewHolder, int position) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        this.bindingProvider.bindItem(this.items.get(position), viewHolder.getView(), this.thumbnailWidth);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    public final void addItems(List<? extends ThumbnailNavigatorItemElement> newItems) {
        Intrinsics.checkNotNullParameter(newItems, "newItems");
        int size = this.items.size();
        this.items.addAll(newItems);
        notifyItemRangeInserted(size, newItems.size());
    }

    public final void clear() {
        this.items.clear();
        notifyDataSetChanged();
    }

    /* JADX INFO: compiled from: ThumbnailNavigatorItemAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/adapters/ThumbnailNavigatorItemAdapter$ThumbnailNavigatorItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Lcom/amazon/music/views/library/views/ThumbnailCellView;", "(Lcom/amazon/music/views/library/views/ThumbnailCellView;)V", "getView", "()Lcom/amazon/music/views/library/views/ThumbnailCellView;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ThumbnailNavigatorItemViewHolder extends RecyclerView.ViewHolder {
        private final ThumbnailCellView view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ThumbnailNavigatorItemViewHolder(ThumbnailCellView view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.view = view;
        }

        public final ThumbnailCellView getView() {
            return this.view;
        }
    }
}
