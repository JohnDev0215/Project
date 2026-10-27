package com.android.car.ui.recyclerview;

import android.annotation.TargetApi;
import android.car.utils.Logs;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R$dimen;
import com.android.car.ui.R$id;
import java.util.Objects;
import spi.b2;
import spi.e8;
import spi.t1;
import spi.u3;
import spi.u9;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
class DefaultScrollBar implements u9 {
    private static final String TAG = "DefaultScrollBar";
    private float mButtonDisabledAlpha;
    private Context mContext;
    private View mDownButton;
    private OrientationHelper mOrientationHelper;
    private e8 mPageDownOnContinuousScrollListener;
    private e8 mPageUpOnContinuousScrollListener;
    private RecyclerView mRecyclerView;
    private View mScrollThumb;
    private View mScrollTrack;
    private View mScrollView;
    private int mScrollbarThumbMinHeight;
    private t1 mSnapHelper;
    private View mUpButton;
    private final SparseArray<Integer> mChildHeightByAdapterPosition = new SparseArray<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Interpolator mPaginationInterpolator = new AccelerateDecelerateInterpolator();
    private final RecyclerView.AdapterDataObserver mAdapterChangeObserver = new a();
    private final RecyclerView.OnScrollListener mRecyclerViewOnScrollListener = new b();

    public class b extends RecyclerView.OnScrollListener {
        public b() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public final void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
            DefaultScrollBar.this.updatePaginationButtons(true);
            DefaultScrollBar.this.cacheChildrenHeight(recyclerView.getLayoutManager());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cacheChildrenHeight(@Nullable RecyclerView.LayoutManager layoutManager) {
        if (layoutManager == null) {
            return;
        }
        for (int i = 0; i < layoutManager.getChildCount(); i++) {
            View childAt = layoutManager.getChildAt(i);
            if (childAt != null) {
                int position = layoutManager.getPosition(childAt);
                if (this.mChildHeightByAdapterPosition.indexOfKey(position) < 0) {
                    this.mChildHeightByAdapterPosition.put(position, Integer.valueOf(childAt.getHeight()));
                }
            } else {
                Log.e(TAG, "cacheChildrenHeight child == null !!!");
            }
        }
    }

    private int calculateScrollThumbLength(int i, int i2) {
        return Math.max(Math.round((i2 / i) * this.mScrollTrack.getHeight()), Math.min(this.mScrollbarThumbMinHeight, this.mScrollTrack.getHeight()));
    }

    private int calculateScrollThumbOffset(int i, int i2, int i3) {
        float f = i;
        return Math.round((i2 / f) * ((this.mScrollTrack.getHeight() + ((this.mScrollTrack.getHeight() / f) * this.mScrollTrack.getHeight())) - i3)) + this.mScrollTrack.getTop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearCachedHeights() {
        this.mChildHeightByAdapterPosition.clear();
        cacheChildrenHeight(getLayoutManager());
    }

    private int estimateNextPositionScrollUp(int i, int i2, OrientationHelper orientationHelper) {
        int iIntValue = 0;
        for (int i3 = i - 1; i3 >= 0; i3--) {
            if (this.mChildHeightByAdapterPosition.indexOfKey(i3) < 0) {
                t1 t1Var = this.mSnapHelper;
                int i4 = -i2;
                Objects.requireNonNull(t1Var);
                float fComputeDistancePerChild = t1Var.computeDistancePerChild(orientationHelper.getLayoutManager(), orientationHelper);
                if (fComputeDistancePerChild <= 0.0f) {
                    return 0;
                }
                return Math.round(i4 / fComputeDistancePerChild);
            }
            if (this.mChildHeightByAdapterPosition.get(i3).intValue() + iIntValue > Math.abs(i2)) {
                return (i3 - i) + 1;
            }
            iIntValue += this.mChildHeightByAdapterPosition.get(i3).intValue();
        }
        return 0;
    }

    private View getFirstMostVisibleChild(OrientationHelper orientationHelper) {
        float f = 0.0f;
        View view = null;
        for (int i = 0; i < getLayoutManager().getChildCount(); i++) {
            View childAt = getLayoutManager().getChildAt(i);
            float fA = t1.a(childAt, orientationHelper);
            if (fA == 1.0f) {
                return childAt;
            }
            if (fA > f) {
                view = childAt;
                f = fA;
            }
        }
        return view;
    }

    private OrientationHelper getOrientationHelper(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.mOrientationHelper;
        if (orientationHelper == null || orientationHelper.getLayoutManager() != layoutManager) {
            this.mOrientationHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.mOrientationHelper;
    }

    private boolean isDownEnabled() {
        return this.mDownButton.isEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initialize$0(View view) {
        pageUp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initialize$1(View view) {
        pageDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initialize$2() {
        updatePaginationButtons(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initialize$3(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.mHandler.post(new e(this, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$updatePaginationButtons$4() {
        this.mScrollView.requestLayout();
    }

    private void moveY(View view, float f) {
        view.animate().y(f).setDuration(0L).setInterpolator(this.mPaginationInterpolator).start();
    }

    private void setDownEnabled(boolean z) {
        if (!z) {
            e8 e8Var = this.mPageDownOnContinuousScrollListener;
            e8Var.a.removeCallbacks(e8Var.g);
            e8Var.f = false;
        }
        this.mDownButton.setEnabled(z);
        this.mDownButton.setAlpha(z ? 1.0f : this.mButtonDisabledAlpha);
    }

    private void setParameters(@IntRange(from = 0) int i, @IntRange(from = 0) int i2, @IntRange(from = 0) int i3, boolean z, boolean z2) {
        if (this.mScrollView.isLaidOut() && this.mScrollView.getVisibility() == 0 && i != 0) {
            int iCalculateScrollThumbLength = calculateScrollThumbLength(i, i3);
            int iCalculateScrollThumbOffset = calculateScrollThumbOffset(i, i2, iCalculateScrollThumbLength);
            ViewGroup.LayoutParams layoutParams = this.mScrollThumb.getLayoutParams();
            if (!z && (layoutParams.height != iCalculateScrollThumbLength || iCalculateScrollThumbLength < this.mScrollThumb.getHeight())) {
                layoutParams.height = iCalculateScrollThumbLength;
                this.mScrollThumb.requestLayout();
            }
            if (z2) {
                iCalculateScrollThumbOffset = this.mScrollThumb.getTop() + (((this.mScrollTrack.getHeight() + this.mScrollTrack.getTop()) - this.mScrollThumb.getTop()) - this.mScrollThumb.getHeight());
            }
            moveY(this.mScrollThumb, iCalculateScrollThumbOffset);
        }
    }

    private void setUpEnabled(boolean z) {
        if (!z) {
            e8 e8Var = this.mPageUpOnContinuousScrollListener;
            e8Var.a.removeCallbacks(e8Var.g);
            e8Var.f = false;
        }
        this.mUpButton.setEnabled(z);
        this.mUpButton.setAlpha(z ? 1.0f : this.mButtonDisabledAlpha);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePaginationButtons(boolean z) {
        boolean z2;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null) {
            this.mScrollView.setVisibility(8);
            return;
        }
        boolean zIsAtStart = isAtStart();
        boolean zIsAtEnd = isAtEnd();
        setUpEnabled(!zIsAtStart);
        setDownEnabled(!zIsAtEnd);
        int i = 1;
        boolean z3 = this.mScrollView.getVisibility() == 0;
        if ((zIsAtStart && zIsAtEnd) || layoutManager.getItemCount() == 0) {
            this.mScrollView.setVisibility(8);
            z2 = false;
        } else {
            int totalSpace = getOrientationHelper(layoutManager).getTotalSpace();
            int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R$dimen.car_ui_touch_target_size);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mUpButton.getLayoutParams();
            int i2 = marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mDownButton.getLayoutParams();
            int i3 = marginLayoutParams2.topMargin + marginLayoutParams2.bottomMargin + i2;
            int i4 = dimensionPixelSize * 2;
            if (totalSpace < i4 + i3) {
                this.mScrollView.setVisibility(8);
                z2 = z3;
            } else {
                ViewGroup.MarginLayoutParams marginLayoutParams3 = (ViewGroup.MarginLayoutParams) this.mScrollTrack.getLayoutParams();
                if (totalSpace < Math.max(dimensionPixelSize, this.mScrollbarThumbMinHeight) + i4 + marginLayoutParams3.topMargin + marginLayoutParams3.bottomMargin + i3) {
                    this.mScrollTrack.setVisibility(4);
                    this.mScrollThumb.setVisibility(4);
                } else {
                    this.mScrollTrack.setVisibility(0);
                    this.mScrollThumb.setVisibility(0);
                }
                z2 = !z3;
                this.mScrollView.setVisibility(0);
            }
        }
        if (layoutManager.canScrollVertically()) {
            setParameters(computeVerticalScrollRange(), computeVerticalScrollOffset(), computeVerticalScrollExtent(), z, zIsAtEnd);
        } else {
            setParameters(computeHorizontalScrollRange(), computeHorizontalScrollOffset(), computeHorizontalScrollExtent(), z, zIsAtEnd);
        }
        this.mScrollView.invalidate();
        if (z2) {
            this.mScrollView.post(new e(this, i));
        }
    }

    @Override // spi.u9
    public void adapterChanged(@Nullable RecyclerView.Adapter adapter) {
        try {
            if (this.mRecyclerView.getAdapter() != null) {
                this.mRecyclerView.getAdapter().unregisterAdapterDataObserver(this.mAdapterChangeObserver);
            }
        } catch (IllegalStateException unused) {
        }
        if (adapter != null) {
            try {
                adapter.registerAdapterDataObserver(this.mAdapterChangeObserver);
            } catch (IllegalStateException unused2) {
            }
        }
    }

    @VisibleForTesting
    public int computeHorizontalScrollExtent() {
        return getRecyclerView().computeHorizontalScrollExtent();
    }

    @VisibleForTesting
    public int computeHorizontalScrollOffset() {
        return getRecyclerView().computeHorizontalScrollOffset();
    }

    @VisibleForTesting
    public int computeHorizontalScrollRange() {
        return getRecyclerView().computeHorizontalScrollRange();
    }

    @VisibleForTesting
    public int computeVerticalScrollExtent() {
        return getRecyclerView().computeVerticalScrollExtent();
    }

    @VisibleForTesting
    public int computeVerticalScrollOffset() {
        return getRecyclerView().computeVerticalScrollOffset();
    }

    @VisibleForTesting
    public int computeVerticalScrollRange() {
        return getRecyclerView().computeVerticalScrollRange();
    }

    @VisibleForTesting
    public RecyclerView.LayoutManager getLayoutManager() {
        return getRecyclerView().getLayoutManager();
    }

    public RecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    @Override // spi.u9
    public void initialize(Context context, RecyclerView recyclerView, View view) {
        this.mContext = context;
        this.mRecyclerView = recyclerView;
        this.mScrollView = view;
        Resources resources = context.getResources();
        this.mButtonDisabledAlpha = b2.g(resources, R$dimen.car_ui_button_disabled_alpha);
        this.mScrollbarThumbMinHeight = resources.getDimensionPixelSize(R$dimen.car_ui_scrollbar_min_thumb_height);
        View viewN = b2.n(this.mScrollView, R$id.car_ui_scrollbar_page_up);
        this.mUpButton = viewN;
        final int i = 0;
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.android.car.ui.recyclerview.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                int i2 = i;
                DefaultScrollBar defaultScrollBar = (DefaultScrollBar) this;
                switch (i2) {
                    case 0:
                        defaultScrollBar.lambda$initialize$0(view2);
                        break;
                    default:
                        defaultScrollBar.lambda$initialize$1(view2);
                        break;
                }
            }
        };
        viewN.setOnClickListener(onClickListener);
        e8 e8Var = new e8(context, onClickListener);
        this.mPageUpOnContinuousScrollListener = e8Var;
        this.mUpButton.setOnTouchListener(e8Var);
        View viewN2 = b2.n(this.mScrollView, R$id.car_ui_scrollbar_page_down);
        this.mDownButton = viewN2;
        final int i2 = 1;
        View.OnClickListener onClickListener2 = new View.OnClickListener() { // from class: com.android.car.ui.recyclerview.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                int i22 = i2;
                DefaultScrollBar defaultScrollBar = (DefaultScrollBar) this;
                switch (i22) {
                    case 0:
                        defaultScrollBar.lambda$initialize$0(view2);
                        break;
                    default:
                        defaultScrollBar.lambda$initialize$1(view2);
                        break;
                }
            }
        };
        viewN2.setOnClickListener(onClickListener2);
        e8 e8Var2 = new e8(context, onClickListener2);
        this.mPageDownOnContinuousScrollListener = e8Var2;
        this.mDownButton.setOnTouchListener(e8Var2);
        this.mScrollTrack = b2.n(this.mScrollView, R$id.car_ui_scrollbar_track);
        this.mScrollThumb = b2.n(this.mScrollView, R$id.car_ui_scrollbar_thumb);
        this.mSnapHelper = new t1(context);
        u3 u3Var = new u3(this.mRecyclerView, this.mScrollTrack, this.mScrollView);
        u3Var.b.setOnTouchListener(u3Var);
        getRecyclerView().addOnScrollListener(this.mRecyclerViewOnScrollListener);
        this.mScrollView.setVisibility(8);
        this.mScrollView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.car.ui.recyclerview.d
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
                this.a.lambda$initialize$3(view2, i3, i4, i5, i6, i7, i8, i9, i10);
            }
        });
        updatePaginationButtons(false);
        if (this.mRecyclerView.getAdapter() != null) {
            adapterChanged(this.mRecyclerView.getAdapter());
        }
    }

    @Override // spi.u9
    public boolean isAtEnd() {
        return this.mSnapHelper.b(getLayoutManager());
    }

    public boolean isAtStart() {
        t1 t1Var = this.mSnapHelper;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        Objects.requireNonNull(t1Var);
        if (layoutManager == null || layoutManager.getChildCount() == 0) {
            return true;
        }
        View childAt = layoutManager.getChildAt(0);
        Objects.requireNonNull(childAt);
        OrientationHelper verticalHelper = layoutManager.canScrollVertically() ? t1Var.getVerticalHelper(layoutManager) : t1Var.getHorizontalHelper(layoutManager);
        return verticalHelper.getDecoratedStart(childAt) >= verticalHelper.getStartAfterPadding() && layoutManager.getPosition(childAt) == 0;
    }

    public void pageDown() {
        int decoratedStart;
        int startAfterPadding;
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || layoutManager.getChildCount() == 0) {
            return;
        }
        OrientationHelper orientationHelper = getOrientationHelper(layoutManager);
        int totalSpace = orientationHelper.getTotalSpace();
        View firstMostVisibleChild = getFirstMostVisibleChild(orientationHelper);
        int iMin = (!layoutManager.isViewPartiallyVisible(firstMostVisibleChild, false, false) || orientationHelper.getDecoratedEnd(firstMostVisibleChild) <= orientationHelper.getEndAfterPadding()) ? totalSpace : Math.min(totalSpace, orientationHelper.getDecoratedEnd(firstMostVisibleChild) - orientationHelper.getEndAfterPadding());
        int childCount = layoutManager.getChildCount() - 1;
        while (true) {
            if (childCount < 0) {
                break;
            }
            View childAt = layoutManager.getChildAt(childCount);
            if (orientationHelper.getDecoratedStart(childAt) <= orientationHelper.getDecoratedStart(firstMostVisibleChild)) {
                break;
            }
            if (orientationHelper.getDecoratedStart(childAt) - orientationHelper.getStartAfterPadding() <= totalSpace) {
                if (orientationHelper.getDecoratedEnd(childAt) <= orientationHelper.getEndAfterPadding()) {
                    decoratedStart = orientationHelper.getDecoratedEnd(childAt);
                    startAfterPadding = orientationHelper.getStartAfterPadding();
                } else {
                    decoratedStart = orientationHelper.getDecoratedStart(childAt);
                    startAfterPadding = orientationHelper.getStartAfterPadding();
                }
                iMin = decoratedStart - startAfterPadding;
            } else {
                childCount--;
            }
        }
        smoothScrollBy(0, iMin);
    }

    public void pageUp() {
        int iComputeVerticalScrollOffset = computeVerticalScrollOffset();
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager == null || layoutManager.getChildCount() == 0 || iComputeVerticalScrollOffset == 0) {
            return;
        }
        OrientationHelper orientationHelper = getOrientationHelper(layoutManager);
        int totalSpace = orientationHelper.getTotalSpace();
        View firstMostVisibleChild = getFirstMostVisibleChild(orientationHelper);
        int position = firstMostVisibleChild != null ? getLayoutManager().getPosition(firstMostVisibleChild) : 0;
        int iEstimateNextPositionScrollUp = estimateNextPositionScrollUp(position, totalSpace - Math.max(0, orientationHelper.getStartAfterPadding() - orientationHelper.getDecoratedStart(firstMostVisibleChild)), orientationHelper);
        if (iEstimateNextPositionScrollUp == 0) {
            smoothScrollBy(0, -totalSpace);
        } else {
            smoothScrollToPosition(Math.max(0, position + iEstimateNextPositionScrollUp));
        }
    }

    @Override // spi.u9
    public void requestLayout() {
        this.mScrollView.requestLayout();
    }

    @Override // spi.u9
    public void setHighlightThumb(boolean z) {
        this.mScrollThumb.setActivated(z);
    }

    @Override // spi.u9
    public void setPadding(int i, int i2) {
        View view = this.mScrollView;
        view.setPadding(view.getPaddingLeft(), i, this.mScrollView.getPaddingRight(), i2);
    }

    @VisibleForTesting
    public void smoothScrollBy(int i, int i2) {
        getRecyclerView().smoothScrollBy(i, i2);
    }

    @VisibleForTesting
    public void smoothScrollToPosition(int i) {
        getRecyclerView().smoothScrollToPosition(i);
    }

    public class a extends RecyclerView.AdapterDataObserver {
        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onChanged() {
            DefaultScrollBar.this.clearCachedHeights();
            Logs.d(DefaultScrollBar.TAG, "onChanged");
            DefaultScrollBar.this.updatePaginationButtons(false);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged(int i, int i2, Object obj) {
            Logs.d(DefaultScrollBar.TAG, "onItemRangeChanged payload: " + i + " " + i2);
            DefaultScrollBar.this.clearCachedHeights();
            DefaultScrollBar.this.updatePaginationButtons(false);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeInserted(int i, int i2) {
            Logs.d(DefaultScrollBar.TAG, "onItemRangeInserted: " + i + " " + i2);
            DefaultScrollBar.this.clearCachedHeights();
            DefaultScrollBar.this.updatePaginationButtons(false);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeMoved(int i, int i2, int i3) {
            StringBuilder sbM = spi.c.m("onItemRangeMoved: ", i, " ", i2, " ");
            sbM.append(i3);
            Logs.d(DefaultScrollBar.TAG, sbM.toString());
            DefaultScrollBar.this.clearCachedHeights();
            DefaultScrollBar.this.updatePaginationButtons(false);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeRemoved(int i, int i2) {
            Logs.d(DefaultScrollBar.TAG, "onItemRangeRemoved: " + i + " " + i2);
            DefaultScrollBar.this.clearCachedHeights();
            DefaultScrollBar.this.updatePaginationButtons(false);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public final void onItemRangeChanged(int i, int i2) {
            Logs.d(DefaultScrollBar.TAG, "onItemRangeChanged: " + i + " " + i2);
            DefaultScrollBar.this.clearCachedHeights();
            DefaultScrollBar.this.updatePaginationButtons(false);
        }
    }
}
