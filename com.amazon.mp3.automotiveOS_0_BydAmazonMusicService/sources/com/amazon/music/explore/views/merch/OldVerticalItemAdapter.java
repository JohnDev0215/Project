package com.amazon.music.explore.views.merch;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.views.library.binders.VerticalTileBinder;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.VerticalTileView;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public class OldVerticalItemAdapter extends RecyclerView.Adapter<VerticalItemViewHolder> {
    private final MethodsDispatcher dispatcher;
    private boolean isLoadingMore;

    @Nullable
    private List<Method> onEndOfList;
    private final String ownerId;
    private final StyleSheet styleSheet;
    private final VerticalTileBinder verticalTileBinder;
    private int tileWidth = -1;
    private final List<VerticalItemElement> items = new ArrayList();

    public OldVerticalItemAdapter(@NonNull String str, @NonNull MethodsDispatcher methodsDispatcher, @NonNull VerticalTileBinder verticalTileBinder, @NonNull StyleSheet styleSheet) {
        this.ownerId = str;
        this.dispatcher = methodsDispatcher;
        this.verticalTileBinder = verticalTileBinder;
        this.styleSheet = styleSheet;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public VerticalItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new VerticalItemViewHolder(new VerticalTileView(viewGroup.getContext(), this.styleSheet));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull VerticalItemViewHolder verticalItemViewHolder, int i) {
        this.verticalTileBinder.bind(verticalItemViewHolder.getView(), VerticalItemElementBinder.bind(this.items.get(i), this.ownerId, this.dispatcher), this.tileWidth);
        if (shouldLoadMore(i)) {
            this.dispatcher.dispatchMethods(this.ownerId, this.onEndOfList);
            this.isLoadingMore = true;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    public void addItems(@NonNull List<VerticalItemElement> list) {
        int size = this.items.size();
        this.items.addAll(list);
        notifyItemRangeInserted(size, list.size());
        this.isLoadingMore = false;
    }

    public void clear() {
        this.items.clear();
        notifyDataSetChanged();
    }

    public void setTileWidth(int i) {
        this.tileWidth = i;
    }

    public void setOnEndOfList(@Nullable List<Method> list) {
        this.onEndOfList = list;
    }

    private boolean shouldLoadMore(int i) {
        return !this.isLoadingMore && isEndOfList(i) && hasMoreItems();
    }

    private boolean hasMoreItems() {
        return this.onEndOfList != null;
    }

    private boolean isEndOfList(int i) {
        return i >= this.items.size() - 1;
    }
}
