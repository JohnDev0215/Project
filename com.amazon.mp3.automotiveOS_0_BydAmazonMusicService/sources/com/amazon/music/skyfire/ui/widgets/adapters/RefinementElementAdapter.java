package com.amazon.music.skyfire.ui.widgets.adapters;

import Touch.WidgetsInterface.v1_0.RefinementElement;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.views.library.views.RefinementPillView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementElementAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0014\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\fJ\u0006\u0010\r\u001a\u00020\nJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016J\u0018\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u000fH\u0016J\u0018\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/adapters/RefinementElementAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/music/skyfire/ui/widgets/adapters/RefinementElementAdapter$RefinementElementViewHolder;", "bindingProvider", "Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;", "(Lcom/amazon/music/skyfire/ui/providers/ElementBindingProvider;)V", ContextMappingConstants.ITEMS, "", "LTouch/WidgetsInterface/v1_0/RefinementElement;", "addItems", "", "newItems", "", "clear", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "viewHolder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "RefinementElementViewHolder", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RefinementElementAdapter extends RecyclerView.Adapter<RefinementElementViewHolder> {
    private final ElementBindingProvider bindingProvider;
    private List<RefinementElement> items;

    public RefinementElementAdapter(ElementBindingProvider bindingProvider) {
        Intrinsics.checkNotNullParameter(bindingProvider, "bindingProvider");
        this.bindingProvider = bindingProvider;
        this.items = new ArrayList();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RefinementElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        return new RefinementElementViewHolder((RefinementPillView) this.bindingProvider.createView(viewType, parent));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return this.bindingProvider.getViewType(this.items.get(position).getClass());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RefinementElementViewHolder viewHolder, int position) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        this.bindingProvider.bind(this.items.get(position), viewHolder.getView());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    public final void addItems(List<? extends RefinementElement> newItems) {
        Intrinsics.checkNotNullParameter(newItems, "newItems");
        int size = this.items.size();
        this.items.addAll(newItems);
        notifyItemRangeInserted(size, newItems.size());
    }

    public final void clear() {
        this.items.clear();
        notifyDataSetChanged();
    }

    /* JADX INFO: compiled from: RefinementElementAdapter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/adapters/RefinementElementAdapter$RefinementElementViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Lcom/amazon/music/views/library/views/RefinementPillView;", "(Lcom/amazon/music/views/library/views/RefinementPillView;)V", "getView", "()Lcom/amazon/music/views/library/views/RefinementPillView;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class RefinementElementViewHolder extends RecyclerView.ViewHolder {
        private final RefinementPillView view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RefinementElementViewHolder(RefinementPillView view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.view = view;
        }

        public final RefinementPillView getView() {
            return this.view;
        }
    }
}
