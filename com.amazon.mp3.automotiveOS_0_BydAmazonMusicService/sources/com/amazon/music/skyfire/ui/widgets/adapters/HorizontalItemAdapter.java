package com.amazon.music.skyfire.ui.widgets.adapters;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.HorizontalItemElement;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.ui.ktx.SkyFireKt;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.views.library.views.HorizontalTileView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HorizontalItemAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\"B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0014\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\fJ\u0006\u0010\u0013\u001a\u00020\u0011J\b\u0010\u0014\u001a\u00020\u000fH\u0016J\b\u0010\u0015\u001a\u00020\u0007H\u0002J\u0010\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u000fH\u0002J\u0018\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u000fH\u0016J\u0018\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u000fH\u0016J\u0016\u0010\u001d\u001a\u00020\u00112\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fJ\u000e\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u000fJ\u0010\u0010!\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/adapters/HorizontalItemAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/music/skyfire/ui/widgets/adapters/HorizontalItemAdapter$HorizontalItemViewHolder;", "bindingProvider", "Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "(Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;)V", "isLoadingMore", "", ContextMappingConstants.ITEMS, "", "LTouch/WidgetsInterface/v1_0/HorizontalItemElement;", "onEndOfList", "", "LCoreInterface/v1_0/Method;", "tileWidth", "", "addItems", "", "newItems", "clear", "getItemCount", "hasMoreItems", "isEndOfList", "position", "onBindViewHolder", "viewHolder", "onCreateViewHolder", "viewGroup", "Landroid/view/ViewGroup;", "setOnEndOfList", "methods", "setTileWidth", "width", "shouldLoadMore", "HorizontalItemViewHolder", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class HorizontalItemAdapter extends RecyclerView.Adapter<HorizontalItemViewHolder> {
    private final ElementBindingProvider bindingProvider;
    private boolean isLoadingMore;
    private List<HorizontalItemElement> items;
    private List<? extends Method> onEndOfList;
    private int tileWidth;

    public HorizontalItemAdapter(ElementBindingProvider bindingProvider) {
        Intrinsics.checkNotNullParameter(bindingProvider, "bindingProvider");
        this.bindingProvider = bindingProvider;
        this.items = new ArrayList();
        this.tileWidth = -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public HorizontalItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        View viewCreateView = this.bindingProvider.createView(this.items.get(position), viewGroup);
        if (viewCreateView != null) {
            return new HorizontalItemViewHolder((HorizontalTileView) viewCreateView);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.views.HorizontalTileView");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(HorizontalItemViewHolder viewHolder, int position) {
        Method[] methodArr;
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        this.bindingProvider.bindItem(this.items.get(position), viewHolder.getView(), this.tileWidth);
        if (shouldLoadMore(position)) {
            DispatcherContext dispatcherContext = this.bindingProvider.getDispatcherContext();
            if (dispatcherContext != null) {
                List<? extends Method> list = this.onEndOfList;
                if (list == null) {
                    methodArr = null;
                } else {
                    Object[] array = list.toArray(new Method[0]);
                    if (array == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                    }
                    methodArr = (Method[]) array;
                }
                if (methodArr == null) {
                    methodArr = new Method[0];
                }
                SkyFireKt.dispatch$default(dispatcherContext, (Method[]) Arrays.copyOf(methodArr, methodArr.length), (String) null, 2, (Object) null);
            }
            this.isLoadingMore = true;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    public final void addItems(List<? extends HorizontalItemElement> newItems) {
        Intrinsics.checkNotNullParameter(newItems, "newItems");
        int size = this.items.size();
        this.items.addAll(newItems);
        notifyItemRangeInserted(size, newItems.size());
    }

    public final void clear() {
        this.items.clear();
        notifyDataSetChanged();
    }

    public final void setTileWidth(int width) {
        this.tileWidth = width;
    }

    public final void setOnEndOfList(List<? extends Method> methods) {
        this.onEndOfList = methods == null ? null : CollectionsKt___CollectionsKt.toList(methods);
    }

    private final boolean shouldLoadMore(int position) {
        return !this.isLoadingMore && isEndOfList(position) && hasMoreItems();
    }

    private final boolean hasMoreItems() {
        return this.onEndOfList != null;
    }

    private final boolean isEndOfList(int position) {
        return position >= CollectionsKt__CollectionsKt.getLastIndex(this.items);
    }

    /* JADX INFO: compiled from: HorizontalItemAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/adapters/HorizontalItemAdapter$HorizontalItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Lcom/amazon/music/views/library/views/HorizontalTileView;", "(Lcom/amazon/music/views/library/views/HorizontalTileView;)V", "getView", "()Lcom/amazon/music/views/library/views/HorizontalTileView;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class HorizontalItemViewHolder extends RecyclerView.ViewHolder {
        private final HorizontalTileView view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HorizontalItemViewHolder(HorizontalTileView view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.view = view;
        }

        public final HorizontalTileView getView() {
            return this.view;
        }
    }
}
