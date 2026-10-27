package com.amazon.podcast.views.transcription;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes5.dex */
public class TranscriptLayoutManager extends LinearLayoutManager {
    private RecyclerView.SmoothScroller smoothScroller;

    public TranscriptLayoutManager(final Context context) {
        super(context);
        if (this.smoothScroller == null) {
            this.smoothScroller = new SmoothScroller(context);
        }
    }

    public TranscriptLayoutManager(final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(final RecyclerView recyclerView, final RecyclerView.State state, final int position) {
        this.smoothScroller.setTargetPosition(position);
        startSmoothScroll(this.smoothScroller);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int position) {
        super.scrollToPositionWithOffset(position, getCenterOffset(position));
    }

    private int getCenterOffset(int position) {
        int i;
        int i2;
        int height = getHeight() / 2;
        View viewFindViewByPosition = findViewByPosition(position);
        int measuredHeight = 0;
        if (viewFindViewByPosition != null) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) viewFindViewByPosition.getLayoutParams();
            i2 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin / 2;
            int i3 = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin / 2;
            measuredHeight = viewFindViewByPosition.getMeasuredHeight() / 2;
            i = i3;
        } else {
            i = 0;
            i2 = 0;
        }
        return height - ((measuredHeight + i2) + i);
    }

    public static class SmoothScroller extends LinearSmoothScroller {
        private final int TRANSCRIPT_AUTO_SCROLL_TIME_MS;

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        protected int calculateTimeForScrolling(final int dx) {
            return ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        protected int getVerticalSnapPreference() {
            return 0;
        }

        public SmoothScroller(Context context) {
            super(context);
            this.TRANSCRIPT_AUTO_SCROLL_TIME_MS = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateDtToFit(final int viewStart, final int viewEnd, final int boxStart, final int boxEnd, final int snapPreference) {
            return (boxStart + ((boxEnd - boxStart) / 2)) - (viewStart + ((viewEnd - viewStart) / 2));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
        @Nullable
        public PointF computeScrollVectorForPosition(final int targetPosition) {
            return super.computeScrollVectorForPosition(targetPosition);
        }
    }
}
