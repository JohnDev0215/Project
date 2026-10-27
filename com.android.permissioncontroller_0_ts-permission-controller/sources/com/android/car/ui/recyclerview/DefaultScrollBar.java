package com.android.car.ui.recyclerview;

import android.content.res.Resources;
import android.os.Handler;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R;
import com.android.car.ui.utils.CarUiUtils;

/* JADX INFO: loaded from: classes7.dex */
class DefaultScrollBar implements ScrollBar {
    private float mButtonDisabledAlpha;
    private View mDownButton;
    private OrientationHelper mOrientationHelper;
    private OnContinuousScrollListener mPageDownOnContinuousScrollListener;
    private OnContinuousScrollListener mPageUpOnContinuousScrollListener;
    private RecyclerView mRecyclerView;
    private View mScrollThumb;
    private View mScrollTrack;
    private View mScrollView;
    private int mScrollbarThumbMinHeight;
    private CarUiSnapHelper mSnapHelper;
    private View mUpButton;
    private final Interpolator mPaginationInterpolator = new AccelerateDecelerateInterpolator();
    private final Handler mHandler = new Handler();
    private final RecyclerView.OnScrollListener mRecyclerViewOnScrollListener = new RecyclerView.OnScrollListener() { // from class: com.android.car.ui.recyclerview.DefaultScrollBar.1
        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            DefaultScrollBar.this.updatePaginationButtons();
            DefaultScrollBar.this.cacheChildrenHeight(recyclerView.getLayoutManager());
        }
    };
    private final SparseArray<Integer> mChildHeightByAdapterPosition = new SparseArray<>();
    private final RecyclerView.AdapterDataObserver mAdapterChangeObserver = new RecyclerView.AdapterDataObserver() { // from class: com.android.car.ui.recyclerview.DefaultScrollBar.2
        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            DefaultScrollBar.this.clearCachedHeights();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            DefaultScrollBar.this.clearCachedHeights();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int positionStart, int itemCount) {
            DefaultScrollBar.this.clearCachedHeights();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int positionStart, int itemCount) {
            DefaultScrollBar.this.clearCachedHeights();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            DefaultScrollBar.this.clearCachedHeights();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            DefaultScrollBar.this.clearCachedHeights();
        }
    };

    DefaultScrollBar() {
    }

    @Override // com.android.car.ui.recyclerview.ScrollBar
    public void initialize(RecyclerView rv, View scrollView) {
        this.mRecyclerView = rv;
        this.mScrollView = scrollView;
        Resources res = rv.getContext().getResources();
        this.mButtonDisabledAlpha = CarUiUtils.getFloat(res, R.dimen.car_ui_button_disabled_alpha);
        this.mScrollbarThumbMinHeight = rv.getContext().getResources().getDimensionPixelSize(R.dimen.car_ui_scrollbar_min_thumb_height);
        getRecyclerView().addOnScrollListener(this.mRecyclerViewOnScrollListener);
        getRecyclerView().getRecycledViewPool().setMaxRecycledViews(0, 12);
        this.mUpButton = CarUiUtils.requireViewByRefId(this.mScrollView, R.id.car_ui_scrollbar_page_up);
        View.OnClickListener paginateUpButtonOnClickListener = new View.OnClickListener() { // from class: com.android.car.ui.recyclerview.-$$Lambda$DefaultScrollBar$ougX9W0-wDNQIsw8jl5lSk437JM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initialize$0$DefaultScrollBar(view);
            }
        };
        this.mUpButton.setOnClickListener(paginateUpButtonOnClickListener);
        OnContinuousScrollListener onContinuousScrollListener = new OnContinuousScrollListener(rv.getContext(), paginateUpButtonOnClickListener);
        this.mPageUpOnContinuousScrollListener = onContinuousScrollListener;
        this.mUpButton.setOnTouchListener(onContinuousScrollListener);
        this.mDownButton = CarUiUtils.requireViewByRefId(this.mScrollView, R.id.car_ui_scrollbar_page_down);
        View.OnClickListener paginateDownButtonOnClickListener = new View.OnClickListener() { // from class: com.android.car.ui.recyclerview.-$$Lambda$DefaultScrollBar$aefeBYoqe3QtKEgRWHADVIqkTDg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initialize$1$DefaultScrollBar(view);
            }
        };
        this.mDownButton.setOnClickListener(paginateDownButtonOnClickListener);
        OnContinuousScrollListener onContinuousScrollListener2 = new OnContinuousScrollListener(rv.getContext(), paginateDownButtonOnClickListener);
        this.mPageDownOnContinuousScrollListener = onContinuousScrollListener2;
        this.mDownButton.setOnTouchListener(onContinuousScrollListener2);
        this.mScrollTrack = CarUiUtils.requireViewByRefId(this.mScrollView, R.id.car_ui_scrollbar_track);
        this.mScrollThumb = CarUiUtils.requireViewByRefId(this.mScrollView, R.id.car_ui_scrollbar_thumb);
        this.mSnapHelper = new CarUiSnapHelper(rv.getContext());
        getRecyclerView().setOnFlingListener(null);
        this.mSnapHelper.attachToRecyclerView(getRecyclerView());
        FastScroller fastScroller = new FastScroller(this.mRecyclerView, this.mScrollTrack, this.mScrollView);
        fastScroller.enable();
        this.mScrollView.setVisibility(4);
        this.mScrollView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.car.ui.recyclerview.-$$Lambda$DefaultScrollBar$MgGXBm2I-0F9RprZkrtHA57cGvc
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                this.f$0.lambda$initialize$2$DefaultScrollBar(view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
    }

    public /* synthetic */ void lambda$initialize$0$DefaultScrollBar(View v) {
        pageUp();
    }

    public /* synthetic */ void lambda$initialize$1$DefaultScrollBar(View v) {
        pageDown();
    }

    public /* synthetic */ void lambda$initialize$2$DefaultScrollBar(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        this.mHandler.post(new Runnable() { // from class: com.android.car.ui.recyclerview.-$$Lambda$DefaultScrollBar$8vQl6fixN4bSs-CT-YNHzqnXe1s
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.updatePaginationButtons();
            }
        });
    }

    public RecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    @Override // com.android.car.ui.recyclerview.ScrollBar
    public void requestLayout() {
        this.mScrollView.requestLayout();
    }

    @Override // com.android.car.ui.recyclerview.ScrollBar
    public void setPadding(int paddingStart, int paddingEnd) {
        View view = this.mScrollView;
        view.setPadding(view.getPaddingLeft(), paddingStart, this.mScrollView.getPaddingRight(), paddingEnd);
    }

    @Override // com.android.car.ui.recyclerview.ScrollBar
    public void adapterChanged(RecyclerView.Adapter adapter) {
        try {
            if (this.mRecyclerView.getAdapter() != null) {
                this.mRecyclerView.getAdapter().unregisterAdapterDataObserver(this.mAdapterChangeObserver);
            }
            if (adapter != null) {
                adapter.registerAdapterDataObserver(this.mAdapterChangeObserver);
            }
        } catch (IllegalStateException e) {
        }
    }

    private void setUpEnabled(boolean enabled) {
        if (!enabled) {
            this.mPageUpOnContinuousScrollListener.cancelPendingScroll();
        }
        this.mUpButton.setEnabled(enabled);
        this.mUpButton.setAlpha(enabled ? 1.0f : this.mButtonDisabledAlpha);
    }

    private void setDownEnabled(boolean enabled) {
        if (!enabled) {
            this.mPageDownOnContinuousScrollListener.cancelPendingScroll();
        }
        this.mDownButton.setEnabled(enabled);
        this.mDownButton.setAlpha(enabled ? 1.0f : this.mButtonDisabledAlpha);
    }

    private boolean isDownEnabled() {
        return this.mDownButton.isEnabled();
    }

    private void setParameters(int range, int offset, int extent) {
        if (!this.mScrollView.isLaidOut() || this.mScrollView.getVisibility() == 8 || range == 0) {
            return;
        }
        int thumbLength = calculateScrollThumbLength(range, extent);
        int thumbOffset = calculateScrollThumbOffset(range, offset, thumbLength);
        ViewGroup.LayoutParams lp = this.mScrollThumb.getLayoutParams();
        if (lp.height != thumbLength || thumbLength < this.mScrollThumb.getHeight()) {
            lp.height = thumbLength;
            this.mScrollThumb.requestLayout();
        }
        moveY(this.mScrollThumb, thumbOffset);
    }

    private int calculateScrollThumbLength(int range, int extent) {
        return Math.max(Math.round((extent / range) * this.mScrollTrack.getHeight()), this.mScrollbarThumbMinHeight);
    }

    private int calculateScrollThumbOffset(int range, int offset, int thumbLength) {
        int height;
        int top = this.mScrollTrack.getTop();
        if (isDownEnabled()) {
            height = Math.round((offset / range) * (this.mScrollTrack.getHeight() - thumbLength));
        } else {
            height = this.mScrollTrack.getHeight() - thumbLength;
        }
        return top + height;
    }

    private void moveY(View view, float newPosition) {
        view.animate().y(newPosition).setDuration(0L).setInterpolator(this.mPaginationInterpolator).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCachedHeights() {
        this.mChildHeightByAdapterPosition.clear();
        cacheChildrenHeight(this.mRecyclerView.getLayoutManager());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cacheChildrenHeight(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager == null) {
            return;
        }
        for (int i = 0; i < layoutManager.getChildCount(); i++) {
            View child = layoutManager.getChildAt(i);
            int childPosition = layoutManager.getPosition(child);
            if (this.mChildHeightByAdapterPosition.indexOfKey(childPosition) < 0) {
                this.mChildHeightByAdapterPosition.put(childPosition, Integer.valueOf(child.getHeight()));
            }
        }
    }

    private int estimateNextPositionScrollUp(int currentPos, int scrollDistance, OrientationHelper orientationHelper) {
        int distance = 0;
        for (int i = currentPos - 1; i >= 0; i--) {
            if (this.mChildHeightByAdapterPosition.indexOfKey(i) < 0) {
                int nextPos = this.mSnapHelper.estimateNextPositionDiffForScrollDistance(orientationHelper, -scrollDistance);
                return nextPos;
            }
            if (this.mChildHeightByAdapterPosition.get(i).intValue() + distance > Math.abs(scrollDistance)) {
                int nextPos2 = (i - currentPos) + 1;
                return nextPos2;
            }
            distance += this.mChildHeightByAdapterPosition.get(i).intValue();
        }
        return 0;
    }

    private OrientationHelper getOrientationHelper(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.mOrientationHelper;
        if (orientationHelper == null || orientationHelper.getLayoutManager() != layoutManager) {
            this.mOrientationHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.mOrientationHelper;
    }

    void pageUp() {
        int currentOffset = getRecyclerView().computeVerticalScrollOffset();
        RecyclerView.LayoutManager layoutManager = getRecyclerView().getLayoutManager();
        if (layoutManager == null || layoutManager.getChildCount() == 0 || currentOffset == 0) {
            return;
        }
        OrientationHelper orientationHelper = getOrientationHelper(layoutManager);
        int screenSize = orientationHelper.getTotalSpace();
        View currentPosView = getFirstMostVisibleChild(orientationHelper);
        int currentPos = currentPosView != null ? this.mRecyclerView.getLayoutManager().getPosition(currentPosView) : 0;
        int nextPos = estimateNextPositionScrollUp(currentPos, screenSize - Math.max(0, orientationHelper.getStartAfterPadding() - orientationHelper.getDecoratedStart(currentPosView)), orientationHelper);
        if (nextPos == 0) {
            this.mRecyclerView.smoothScrollBy(0, -screenSize);
        } else {
            this.mRecyclerView.smoothScrollToPosition(Math.max(0, currentPos + nextPos));
        }
    }

    private View getFirstMostVisibleChild(OrientationHelper helper) {
        float mostVisiblePercent = 0.0f;
        View mostVisibleView = null;
        for (int i = 0; i < getRecyclerView().getLayoutManager().getChildCount(); i++) {
            View child = getRecyclerView().getLayoutManager().getChildAt(i);
            float visiblePercentage = CarUiSnapHelper.getPercentageVisible(child, helper);
            if (visiblePercentage != 1.0f) {
                if (visiblePercentage > mostVisiblePercent) {
                    mostVisiblePercent = visiblePercentage;
                    mostVisibleView = child;
                }
            } else {
                return child;
            }
        }
        return mostVisibleView;
    }

    void pageDown() {
        RecyclerView.LayoutManager layoutManager = getRecyclerView().getLayoutManager();
        if (layoutManager == null || layoutManager.getChildCount() == 0) {
            return;
        }
        OrientationHelper orientationHelper = getOrientationHelper(layoutManager);
        int screenSize = orientationHelper.getTotalSpace();
        int scrollDistance = screenSize;
        View currentPosView = getFirstMostVisibleChild(orientationHelper);
        if (layoutManager.isViewPartiallyVisible(currentPosView, false, false) && orientationHelper.getDecoratedEnd(currentPosView) > orientationHelper.getEndAfterPadding()) {
            scrollDistance = Math.min(screenSize, orientationHelper.getDecoratedEnd(currentPosView) - orientationHelper.getEndAfterPadding());
        }
        int i = layoutManager.getChildCount() - 1;
        while (true) {
            if (i < 0) {
                break;
            }
            View child = layoutManager.getChildAt(i);
            if (orientationHelper.getDecoratedStart(child) <= orientationHelper.getDecoratedStart(currentPosView)) {
                break;
            }
            if (orientationHelper.getDecoratedStart(child) - orientationHelper.getStartAfterPadding() > screenSize) {
                i--;
            } else if (orientationHelper.getDecoratedEnd(child) <= orientationHelper.getEndAfterPadding()) {
                scrollDistance = orientationHelper.getDecoratedEnd(child) - orientationHelper.getStartAfterPadding();
            } else {
                scrollDistance = orientationHelper.getDecoratedStart(child) - orientationHelper.getStartAfterPadding();
            }
        }
        this.mRecyclerView.smoothScrollBy(0, scrollDistance);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePaginationButtons() {
        boolean isAtStart = isAtStart();
        boolean isAtEnd = isAtEnd();
        RecyclerView.LayoutManager layoutManager = getRecyclerView().getLayoutManager();
        setUpEnabled(!isAtStart);
        setDownEnabled(!isAtEnd);
        if ((isAtStart && isAtEnd) || layoutManager == null || layoutManager.getItemCount() == 0) {
            this.mScrollView.setVisibility(4);
        } else {
            OrientationHelper orientationHelper = getOrientationHelper(layoutManager);
            int screenSize = orientationHelper.getTotalSpace();
            int touchTargetSize = (int) getRecyclerView().getContext().getResources().getDimension(R.dimen.car_ui_touch_target_size);
            ConstraintLayout.LayoutParams upButtonLayoutParam = (ConstraintLayout.LayoutParams) this.mUpButton.getLayoutParams();
            int upButtonMargin = upButtonLayoutParam.topMargin + upButtonLayoutParam.bottomMargin;
            ConstraintLayout.LayoutParams downButtonLayoutParam = (ConstraintLayout.LayoutParams) this.mDownButton.getLayoutParams();
            int downButtonMargin = downButtonLayoutParam.topMargin + downButtonLayoutParam.bottomMargin;
            int margin = upButtonMargin + downButtonMargin;
            if (screenSize < (touchTargetSize * 2) + margin) {
                this.mScrollView.setVisibility(4);
            } else {
                ConstraintLayout.LayoutParams trackLayoutParam = (ConstraintLayout.LayoutParams) this.mScrollTrack.getLayoutParams();
                int trackMargin = trackLayoutParam.topMargin + trackLayoutParam.bottomMargin;
                if (screenSize < (touchTargetSize * 3) + margin + trackMargin) {
                    this.mScrollTrack.setVisibility(4);
                    this.mScrollThumb.setVisibility(4);
                }
                this.mScrollView.setVisibility(0);
            }
        }
        if (layoutManager == null) {
            return;
        }
        if (layoutManager.canScrollVertically()) {
            setParameters(getRecyclerView().computeVerticalScrollRange(), getRecyclerView().computeVerticalScrollOffset(), getRecyclerView().computeVerticalScrollExtent());
        } else {
            setParameters(getRecyclerView().computeHorizontalScrollRange(), getRecyclerView().computeHorizontalScrollOffset(), getRecyclerView().computeHorizontalScrollExtent());
        }
        this.mScrollView.invalidate();
    }

    boolean isAtStart() {
        return this.mSnapHelper.isAtStart(getRecyclerView().getLayoutManager());
    }

    boolean isAtEnd() {
        return this.mSnapHelper.isAtEnd(getRecyclerView().getLayoutManager());
    }
}
