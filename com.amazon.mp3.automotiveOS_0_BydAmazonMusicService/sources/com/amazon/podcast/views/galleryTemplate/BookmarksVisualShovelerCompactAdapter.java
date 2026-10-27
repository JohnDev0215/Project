package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElement;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.transformations.RoundedCornersTransformation;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
final class BookmarksVisualShovelerCompactAdapter extends RecyclerView.Adapter<BookmarkVerticalItemViewHolder> {
    private final Context context;
    private List<BookmarkVerticalItem> items;
    private final LifecycleOwner lifecycleOwner;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final Resources resources;
    private final RoundedCornersTransformation transformation;
    private final int verticalItemDimension;

    public BookmarksVisualShovelerCompactAdapter(final String ownerId, final MethodsDispatcher methodsDispatcher, final Resources resources, final int verticalItemDimension, final LifecycleOwner lifecycleOwner, final Context context) {
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.transformation = new RoundedCornersTransformation(resources.getDimensionPixelSize(R$dimen.podcast_vertical_item_image_corner_radius));
        this.verticalItemDimension = verticalItemDimension;
        this.lifecycleOwner = lifecycleOwner;
        this.context = context;
        this.resources = resources;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public BookmarkVerticalItemViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int viewType) {
        return new BookmarkVerticalItemViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.podcast_vertical_item, viewGroup, false), this.ownerId, this.methodsDispatcher, this.transformation, this.verticalItemDimension, this.lifecycleOwner, this.context, this.resources);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(final BookmarkVerticalItemViewHolder viewHolder, final int position) {
        BookmarkVerticalItem bookmarkVerticalItem = this.items.get(position);
        viewHolder.bind(bookmarkVerticalItem.progressMilliSeconds, bookmarkVerticalItem.totalDurationMilliSeconds, bookmarkVerticalItem.element);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<BookmarkVerticalItem> list = this.items;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void bind(final List<BookmarkVerticalItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    static final class BookmarkVerticalItem {
        private VerticalItemElement element;
        private long progressMilliSeconds;
        private long totalDurationMilliSeconds;

        BookmarkVerticalItem(final VerticalItemElement element, final long progressMilliSeconds, final long totalDurationMilliSeconds) {
            this.element = element;
            this.progressMilliSeconds = progressMilliSeconds;
            this.totalDurationMilliSeconds = totalDurationMilliSeconds;
        }
    }
}
