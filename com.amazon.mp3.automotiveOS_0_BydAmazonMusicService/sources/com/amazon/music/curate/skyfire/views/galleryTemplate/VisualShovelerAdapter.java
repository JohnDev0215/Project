package com.amazon.music.curate.skyfire.views.galleryTemplate;

import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import android.content.Context;
import android.content.res.Resources;
import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.VerticalTileView;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
final class VisualShovelerAdapter extends RecyclerView.Adapter<VerticalItemViewHolder> {
    private final Context context;
    private final CurateBootstrapProviders curateBootstrapProviders;
    private List<VerticalItemElement> items;
    private final LifecycleOwner lifecycleOwner;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final Resources resources;
    private final StyleSheet styleSheet;
    private final int verticalItemDimension;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return position;
    }

    public VisualShovelerAdapter(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final Resources resources, final int verticalItemDimension, final LifecycleOwner lifecycleOwner, final CurateBootstrapProviders curateBootstrapProviders, final StyleSheet styleSheet) {
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.verticalItemDimension = verticalItemDimension;
        this.lifecycleOwner = lifecycleOwner;
        this.context = context;
        this.resources = resources;
        this.curateBootstrapProviders = curateBootstrapProviders;
        this.styleSheet = styleSheet;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public VerticalItemViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int viewType) {
        return new VerticalItemViewHolder(new VerticalTileView(viewGroup.getContext(), this.styleSheet), this.ownerId, this.methodsDispatcher, this.verticalItemDimension, this.lifecycleOwner, this.context, this.resources, this.curateBootstrapProviders);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final VerticalItemViewHolder viewHolder, final int position) {
        if (this.curateBootstrapProviders.isAtLeastOneProviderNull()) {
            return;
        }
        viewHolder.bind(this.items.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<VerticalItemElement> list = this.items;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void bind(final List<VerticalItemElement> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
