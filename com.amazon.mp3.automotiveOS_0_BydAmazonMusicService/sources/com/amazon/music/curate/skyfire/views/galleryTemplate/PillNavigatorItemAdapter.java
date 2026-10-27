package com.amazon.music.curate.skyfire.views.galleryTemplate;

import Touch.WidgetsInterface.v1_0.PillNavigatorWidgetItemElement;
import android.util.Log;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.views.library.views.PillCellView;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
final class PillNavigatorItemAdapter extends RecyclerView.Adapter<PillNavigatorItemViewHolder> {
    private final CurateBootstrapProviders curateBootstrapProviders;
    private List<PillNavigatorWidgetItemElement> items;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;

    public PillNavigatorItemAdapter(final String ownerId, final MethodsDispatcher methodsDispatcher, final CurateBootstrapProviders curateBootstrapProviders) {
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.curateBootstrapProviders = curateBootstrapProviders;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public PillNavigatorItemViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int position) {
        PillCellView pillCellView;
        if (this.curateBootstrapProviders.getStyleSheetProvider() == null) {
            Log.d(PillNavigatorItemAdapter.class.getSimpleName(), "Stylesheet provider was null. Using default constructor.");
            pillCellView = new PillCellView(viewGroup.getContext());
        } else {
            pillCellView = new PillCellView(viewGroup.getContext(), this.curateBootstrapProviders.getStyleSheetProvider().getStyleSheet().getValue());
        }
        return new PillNavigatorItemViewHolder(pillCellView, this.ownerId, this.methodsDispatcher);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final PillNavigatorItemViewHolder viewHolder, final int position) {
        if (this.curateBootstrapProviders.isAtLeastOneProviderNull()) {
            return;
        }
        viewHolder.bind(this.items.get(position));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<PillNavigatorWidgetItemElement> list = this.items;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void bind(final List<PillNavigatorWidgetItemElement> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
