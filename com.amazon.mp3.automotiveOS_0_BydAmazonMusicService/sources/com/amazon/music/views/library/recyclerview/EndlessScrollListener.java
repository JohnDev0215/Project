package com.amazon.music.views.library.recyclerview;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EndlessScrollListener.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H&J \u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0005H\u0016J\u0006\u0010\u0017\u001a\u00020\u000fR\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/EndlessScrollListener;", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "mLayoutManager", "Landroidx/recyclerview/widget/RecyclerView$LayoutManager;", "pageSize", "", "(Landroidx/recyclerview/widget/RecyclerView$LayoutManager;I)V", "currentPage", "invalidViewIndex", "loading", "", "previousTotalItemCount", "startingPageIndex", "visibleThreshold", "onLoadMore", "", "page", "totalItemsCount", "onScrolled", "view", "Landroidx/recyclerview/widget/RecyclerView;", "dx", "dy", "reset", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {
    private int currentPage;
    private final int invalidViewIndex;
    private boolean loading;
    private final RecyclerView.LayoutManager mLayoutManager;
    private final int pageSize;
    private int previousTotalItemCount;
    private final int startingPageIndex;
    private final int visibleThreshold;

    public abstract void onLoadMore(int page, int totalItemsCount);

    public /* synthetic */ EndlessScrollListener(RecyclerView.LayoutManager layoutManager, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(layoutManager, (i2 & 2) != 0 ? 0 : i);
    }

    @JvmOverloads
    public EndlessScrollListener(RecyclerView.LayoutManager mLayoutManager, int i) {
        Intrinsics.checkNotNullParameter(mLayoutManager, "mLayoutManager");
        this.mLayoutManager = mLayoutManager;
        this.pageSize = i;
        this.visibleThreshold = 5;
        this.invalidViewIndex = -1;
        this.loading = true;
    }

    public final void reset() {
        this.currentPage = 0;
        this.previousTotalItemCount = 0;
        this.loading = true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView view, int dx, int dy) {
        int iFindFirstVisibleItemPosition;
        Intrinsics.checkNotNullParameter(view, "view");
        RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
        if (layoutManager instanceof LinearLayoutManager) {
            iFindFirstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else {
            iFindFirstVisibleItemPosition = layoutManager instanceof GridLayoutManager ? ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition() : this.invalidViewIndex;
        }
        int childCount = view.getChildCount();
        int itemCount = this.mLayoutManager.getItemCount();
        if (itemCount < this.previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = itemCount;
            if (itemCount == 0) {
                this.loading = true;
            }
        }
        if (this.loading && itemCount > this.previousTotalItemCount) {
            this.loading = false;
            this.previousTotalItemCount = itemCount;
        }
        if (this.loading || itemCount - childCount > iFindFirstVisibleItemPosition + this.visibleThreshold) {
            return;
        }
        int i = this.currentPage + 1;
        this.currentPage = i;
        int i2 = this.pageSize;
        if (i2 != 0) {
            itemCount = i2;
        }
        onLoadMore(i, itemCount);
        this.loading = true;
    }
}
