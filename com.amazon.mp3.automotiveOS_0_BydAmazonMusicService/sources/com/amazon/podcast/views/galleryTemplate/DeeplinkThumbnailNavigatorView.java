package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.ThumbnailNavigatorWidgetElementInterface.v1_0.ThumbnailNavigatorWidgetElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$integer;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.deeplinkThumbnailNavigator.DeeplinkThumbnailNavigatorAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class DeeplinkThumbnailNavigatorView extends LinearLayout implements ScrollableWidgetView {
    private EmberTextView header;
    private boolean isFirstView;
    private int itemWidth;
    private MethodsDispatcher methodsDispatcher;
    private int numberOfItemsPerRow;
    private List<Method> onContentFirstViewed;
    private List<Method> onViewed;
    private String ownerId;
    private RecyclerView recyclerView;

    public DeeplinkThumbnailNavigatorView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher) {
        super(context);
        this.isFirstView = true;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_deeplink_thumbnail_navigator, this);
        Resources resources = getResources();
        this.numberOfItemsPerRow = resources.getInteger(R$integer.deeplink_thumbnail_navigator_items_per_row);
        this.header = (EmberTextView) findViewById(R$id.thumbnail_navigator_header_text_view);
        RecyclerView recyclerView = (RecyclerView) findViewById(R$id.thumbnail_navigator_recycler_view);
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(getLayoutManager());
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.gutter);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R$dimen.margin);
        this.itemWidth = getItemWidth(resources, dimensionPixelSize, dimensionPixelSize2);
        this.recyclerView.setAdapter(new DeeplinkThumbnailNavigatorAdapter(this.ownerId, getContext(), getResources(), this.methodsDispatcher, this.itemWidth, resources.getDimensionPixelSize(R$dimen.deeplink_thumbnail_navigator_item_height)));
        this.recyclerView.addItemDecoration(new MarginItemDecoration(dimensionPixelSize2, this.numberOfItemsPerRow));
    }

    public void bind(final ThumbnailNavigatorWidgetElement element) {
        this.header.setText(element.getHeader());
        ((DeeplinkThumbnailNavigatorAdapter) this.recyclerView.getAdapter()).bind(element.getItems());
        this.recyclerView.setLayoutManager(getLayoutManager());
        this.onViewed = element.getOnViewed();
        this.onContentFirstViewed = element.getOnContentFirstViewed();
    }

    private GridLayoutManager getLayoutManager() {
        return new GridLayoutManager(getContext(), this.numberOfItemsPerRow, 1, false);
    }

    private int getItemWidth(final Resources resources, final int gutterSize, final int spacerSize) {
        int recyclerViewWidth = getRecyclerViewWidth(resources, gutterSize);
        int i = this.numberOfItemsPerRow;
        return (recyclerViewWidth - ((i + 1) * spacerSize)) / i;
    }

    private int getRecyclerViewWidth(final Resources resources, final int gutterSize) {
        return resources.getDisplayMetrics().widthPixels - (gutterSize * 2);
    }

    public void handleOnViewed() {
        this.methodsDispatcher.dispatch(this.ownerId, this.onViewed);
        if (this.isFirstView) {
            handleOnContentFirstViewed();
        }
    }

    private void handleOnContentFirstViewed() {
        this.methodsDispatcher.dispatch(this.ownerId, this.onContentFirstViewed);
        this.isFirstView = false;
    }

    static final class MarginItemDecoration extends RecyclerView.ItemDecoration {
        private final int numberOfItemsPerRow;
        private final int spacerSize;

        MarginItemDecoration(int spacerSize, int numberOfItemsPerRow) {
            this.spacerSize = spacerSize;
            this.numberOfItemsPerRow = numberOfItemsPerRow;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int childAdapterPosition = parent.getChildAdapterPosition(view);
            int i = this.numberOfItemsPerRow;
            double d = ((double) (i - (childAdapterPosition % i))) / ((double) i);
            int i2 = this.spacerSize;
            outRect.top = i2;
            outRect.left = (int) (((double) i2) * d);
            outRect.right = 0;
            outRect.bottom = 0;
        }
    }
}
