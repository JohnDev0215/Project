package androidx.leanback.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.FocusFinder;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import androidx.collection.CircularIntArray;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.leanback.widget.BaseGridView;
import androidx.leanback.widget.Grid;
import androidx.leanback.widget.ItemAlignmentFacet;
import androidx.leanback.widget.WindowAlignment;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class GridLayoutManager extends RecyclerView.LayoutManager {
    static final boolean DEBUG = false;
    static final int DEFAULT_MAX_PENDING_MOVES = 10;
    static final int MIN_MS_SMOOTH_SCROLL_MAIN_SCREEN = 30;
    private static final int NEXT_ITEM = 1;
    private static final int NEXT_ROW = 3;
    static final int PF_FAST_RELAYOUT = 4;
    static final int PF_FAST_RELAYOUT_UPDATED_SELECTED_POSITION = 8;
    static final int PF_FOCUS_OUT_END = 4096;
    static final int PF_FOCUS_OUT_FRONT = 2048;
    static final int PF_FOCUS_OUT_MASKS = 6144;
    static final int PF_FOCUS_OUT_SIDE_END = 16384;
    static final int PF_FOCUS_OUT_SIDE_MASKS = 24576;
    static final int PF_FOCUS_OUT_SIDE_START = 8192;
    static final int PF_FOCUS_SEARCH_DISABLED = 32768;
    static final int PF_FORCE_FULL_LAYOUT = 256;
    static final int PF_IN_LAYOUT_SEARCH_FOCUS = 16;
    static final int PF_IN_SELECTION = 32;
    static final int PF_LAYOUT_EATEN_IN_SLIDING = 128;
    static final int PF_LAYOUT_ENABLED = 512;
    static final int PF_PRUNE_CHILD = 65536;
    static final int PF_REVERSE_FLOW_MASK = 786432;
    static final int PF_REVERSE_FLOW_PRIMARY = 262144;
    static final int PF_REVERSE_FLOW_SECONDARY = 524288;
    static final int PF_ROW_SECONDARY_SIZE_REFRESH = 1024;
    static final int PF_SCROLL_ENABLED = 131072;
    static final int PF_SLIDING = 64;
    static final int PF_STAGE_LAYOUT = 1;
    static final int PF_STAGE_MASK = 3;
    static final int PF_STAGE_SCROLL = 2;
    private static final int PREV_ITEM = 0;
    private static final int PREV_ROW = 2;
    private static final String TAG = "GridLayoutManager";
    static final boolean TRACE = false;
    private static final Rect sTempRect = new Rect();
    static int[] sTwoInts = new int[2];
    final BaseGridView mBaseGridView;
    GridLinearSmoothScroller mCurrentSmoothScroller;
    int[] mDisappearingPositions;
    private int mExtraLayoutSpace;
    int mExtraLayoutSpaceInPreLayout;
    private FacetProviderAdapter mFacetProviderAdapter;
    private int mFixedRowSizeSecondary;
    Grid mGrid;
    private int mHorizontalSpacing;
    private int mMaxSizeSecondary;
    int mNumRows;
    PendingMoveSmoothScroller mPendingMoveSmoothScroller;
    int mPositionDeltaInPreLayout;
    private int mPrimaryScrollExtra;
    RecyclerView.Recycler mRecycler;
    private int[] mRowSizeSecondary;
    private int mRowSizeSecondaryRequested;
    private int mSaveContextLevel;
    int mScrollOffsetSecondary;
    private int mSizePrimary;
    private int mSpacingPrimary;
    private int mSpacingSecondary;
    RecyclerView.State mState;
    private int mVerticalSpacing;
    float mSmoothScrollSpeedFactor = 1.0f;
    int mMaxPendingMoves = 10;
    int mOrientation = 0;
    private OrientationHelper mOrientationHelper = OrientationHelper.createHorizontalHelper(this);
    final SparseIntArray mPositionToRowInPostLayout = new SparseIntArray();
    int mFlag = 221696;
    private OnChildSelectedListener mChildSelectedListener = null;
    private ArrayList<OnChildViewHolderSelectedListener> mChildViewHolderSelectedListeners = null;
    ArrayList<BaseGridView.OnLayoutCompletedListener> mOnLayoutCompletedListeners = null;
    OnChildLaidOutListener mChildLaidOutListener = null;
    int mFocusPosition = -1;
    int mSubFocusPosition = 0;
    private int mFocusPositionOffset = 0;
    private int mGravity = 8388659;
    private int mNumRowsRequested = 1;
    private int mFocusScrollStrategy = 0;
    final WindowAlignment mWindowAlignment = new WindowAlignment();
    private final ItemAlignment mItemAlignment = new ItemAlignment();
    private int[] mMeasuredDimension = new int[2];
    final ViewsStateBundle mChildrenStates = new ViewsStateBundle();
    private final Runnable mRequestLayoutRunnable = new Runnable() { // from class: androidx.leanback.widget.GridLayoutManager.1
        @Override // java.lang.Runnable
        public void run() {
            GridLayoutManager.this.requestLayout();
        }
    };
    private Grid.Provider mGridProvider = new Grid.Provider() { // from class: androidx.leanback.widget.GridLayoutManager.2
        @Override // androidx.leanback.widget.Grid.Provider
        public int getMinIndex() {
            return GridLayoutManager.this.mPositionDeltaInPreLayout;
        }

        @Override // androidx.leanback.widget.Grid.Provider
        public int getCount() {
            return GridLayoutManager.this.mState.getItemCount() + GridLayoutManager.this.mPositionDeltaInPreLayout;
        }

        @Override // androidx.leanback.widget.Grid.Provider
        public int createItem(int index, boolean append, Object[] item, boolean disappearingItem) {
            GridLayoutManager gridLayoutManager = GridLayoutManager.this;
            View v = gridLayoutManager.getViewForPosition(index - gridLayoutManager.mPositionDeltaInPreLayout);
            LayoutParams lp = (LayoutParams) v.getLayoutParams();
            if (!lp.isItemRemoved()) {
                if (disappearingItem) {
                    if (append) {
                        GridLayoutManager.this.addDisappearingView(v);
                    } else {
                        GridLayoutManager.this.addDisappearingView(v, 0);
                    }
                } else if (append) {
                    GridLayoutManager.this.addView(v);
                } else {
                    GridLayoutManager.this.addView(v, 0);
                }
                if (GridLayoutManager.this.mChildVisibility != -1) {
                    v.setVisibility(GridLayoutManager.this.mChildVisibility);
                }
                if (GridLayoutManager.this.mPendingMoveSmoothScroller != null) {
                    GridLayoutManager.this.mPendingMoveSmoothScroller.consumePendingMovesBeforeLayout();
                }
                int subindex = GridLayoutManager.this.getSubPositionByView(v, v.findFocus());
                if ((GridLayoutManager.this.mFlag & 3) != 1) {
                    if (index == GridLayoutManager.this.mFocusPosition && subindex == GridLayoutManager.this.mSubFocusPosition && GridLayoutManager.this.mPendingMoveSmoothScroller == null) {
                        GridLayoutManager.this.dispatchChildSelected();
                    }
                } else if ((GridLayoutManager.this.mFlag & 4) == 0) {
                    if ((GridLayoutManager.this.mFlag & 16) == 0 && index == GridLayoutManager.this.mFocusPosition && subindex == GridLayoutManager.this.mSubFocusPosition) {
                        GridLayoutManager.this.dispatchChildSelected();
                    } else if ((GridLayoutManager.this.mFlag & 16) != 0 && index >= GridLayoutManager.this.mFocusPosition && v.hasFocusable()) {
                        GridLayoutManager.this.mFocusPosition = index;
                        GridLayoutManager.this.mSubFocusPosition = subindex;
                        GridLayoutManager.this.mFlag &= -17;
                        GridLayoutManager.this.dispatchChildSelected();
                    }
                }
                GridLayoutManager.this.measureChild(v);
            }
            item[0] = v;
            return GridLayoutManager.this.mOrientation == 0 ? GridLayoutManager.this.getDecoratedMeasuredWidthWithMargin(v) : GridLayoutManager.this.getDecoratedMeasuredHeightWithMargin(v);
        }

        @Override // androidx.leanback.widget.Grid.Provider
        public void addItem(Object item, int index, int length, int rowIndex, int edge) {
            int size;
            int edge2;
            int start;
            int end;
            View v = (View) item;
            if (edge == Integer.MIN_VALUE || edge == Integer.MAX_VALUE) {
                if (!GridLayoutManager.this.mGrid.isReversedFlow()) {
                    size = GridLayoutManager.this.mWindowAlignment.mainAxis().getPaddingMin();
                } else {
                    size = GridLayoutManager.this.mWindowAlignment.mainAxis().getSize() - GridLayoutManager.this.mWindowAlignment.mainAxis().getPaddingMax();
                }
                edge2 = size;
            } else {
                edge2 = edge;
            }
            boolean edgeIsMin = !GridLayoutManager.this.mGrid.isReversedFlow();
            if (edgeIsMin) {
                int start2 = edge2;
                start = start2;
                end = edge2 + length;
            } else {
                int start3 = edge2 - length;
                start = start3;
                end = edge2;
            }
            int startSecondary = (GridLayoutManager.this.getRowStartSecondary(rowIndex) + GridLayoutManager.this.mWindowAlignment.secondAxis().getPaddingMin()) - GridLayoutManager.this.mScrollOffsetSecondary;
            GridLayoutManager.this.mChildrenStates.loadView(v, index);
            GridLayoutManager.this.layoutChild(rowIndex, v, start, end, startSecondary);
            if (!GridLayoutManager.this.mState.isPreLayout()) {
                GridLayoutManager.this.updateScrollLimits();
            }
            if ((GridLayoutManager.this.mFlag & 3) != 1 && GridLayoutManager.this.mPendingMoveSmoothScroller != null) {
                GridLayoutManager.this.mPendingMoveSmoothScroller.consumePendingMovesAfterLayout();
            }
            if (GridLayoutManager.this.mChildLaidOutListener != null) {
                RecyclerView.ViewHolder vh = GridLayoutManager.this.mBaseGridView.getChildViewHolder(v);
                GridLayoutManager.this.mChildLaidOutListener.onChildLaidOut(GridLayoutManager.this.mBaseGridView, v, index, vh == null ? -1L : vh.getItemId());
            }
        }

        @Override // androidx.leanback.widget.Grid.Provider
        public void removeItem(int index) {
            GridLayoutManager gridLayoutManager = GridLayoutManager.this;
            View v = gridLayoutManager.findViewByPosition(index - gridLayoutManager.mPositionDeltaInPreLayout);
            if ((GridLayoutManager.this.mFlag & 3) == 1) {
                GridLayoutManager gridLayoutManager2 = GridLayoutManager.this;
                gridLayoutManager2.detachAndScrapView(v, gridLayoutManager2.mRecycler);
            } else {
                GridLayoutManager gridLayoutManager3 = GridLayoutManager.this;
                gridLayoutManager3.removeAndRecycleView(v, gridLayoutManager3.mRecycler);
            }
        }

        @Override // androidx.leanback.widget.Grid.Provider
        public int getEdge(int index) {
            GridLayoutManager gridLayoutManager = GridLayoutManager.this;
            View v = gridLayoutManager.findViewByPosition(index - gridLayoutManager.mPositionDeltaInPreLayout);
            return (GridLayoutManager.this.mFlag & 262144) != 0 ? GridLayoutManager.this.getViewMax(v) : GridLayoutManager.this.getViewMin(v);
        }

        @Override // androidx.leanback.widget.Grid.Provider
        public int getSize(int index) {
            GridLayoutManager gridLayoutManager = GridLayoutManager.this;
            return gridLayoutManager.getViewPrimarySize(gridLayoutManager.findViewByPosition(index - gridLayoutManager.mPositionDeltaInPreLayout));
        }
    };
    int mChildVisibility = -1;

    static final class LayoutParams extends RecyclerView.LayoutParams {
        private int[] mAlignMultiple;
        private int mAlignX;
        private int mAlignY;
        private ItemAlignmentFacet mAlignmentFacet;
        int mBottomInset;
        int mLeftInset;
        int mRightInset;
        int mTopInset;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(RecyclerView.LayoutParams source) {
            super(source);
        }

        public LayoutParams(LayoutParams source) {
            super((RecyclerView.LayoutParams) source);
        }

        int getAlignX() {
            return this.mAlignX;
        }

        int getAlignY() {
            return this.mAlignY;
        }

        int getOpticalLeft(View view) {
            return view.getLeft() + this.mLeftInset;
        }

        int getOpticalTop(View view) {
            return view.getTop() + this.mTopInset;
        }

        int getOpticalRight(View view) {
            return view.getRight() - this.mRightInset;
        }

        int getOpticalBottom(View view) {
            return view.getBottom() - this.mBottomInset;
        }

        int getOpticalWidth(View view) {
            return (view.getWidth() - this.mLeftInset) - this.mRightInset;
        }

        int getOpticalHeight(View view) {
            return (view.getHeight() - this.mTopInset) - this.mBottomInset;
        }

        int getOpticalLeftInset() {
            return this.mLeftInset;
        }

        int getOpticalRightInset() {
            return this.mRightInset;
        }

        int getOpticalTopInset() {
            return this.mTopInset;
        }

        int getOpticalBottomInset() {
            return this.mBottomInset;
        }

        void setAlignX(int alignX) {
            this.mAlignX = alignX;
        }

        void setAlignY(int alignY) {
            this.mAlignY = alignY;
        }

        void setItemAlignmentFacet(ItemAlignmentFacet facet) {
            this.mAlignmentFacet = facet;
        }

        ItemAlignmentFacet getItemAlignmentFacet() {
            return this.mAlignmentFacet;
        }

        void calculateItemAlignments(int orientation, View view) {
            ItemAlignmentFacet.ItemAlignmentDef[] defs = this.mAlignmentFacet.getAlignmentDefs();
            int[] iArr = this.mAlignMultiple;
            if (iArr == null || iArr.length != defs.length) {
                this.mAlignMultiple = new int[defs.length];
            }
            for (int i = 0; i < defs.length; i++) {
                this.mAlignMultiple[i] = ItemAlignmentFacetHelper.getAlignmentPosition(view, defs[i], orientation);
            }
            if (orientation == 0) {
                this.mAlignX = this.mAlignMultiple[0];
            } else {
                this.mAlignY = this.mAlignMultiple[0];
            }
        }

        int[] getAlignMultiple() {
            return this.mAlignMultiple;
        }

        void setOpticalInsets(int leftInset, int topInset, int rightInset, int bottomInset) {
            this.mLeftInset = leftInset;
            this.mTopInset = topInset;
            this.mRightInset = rightInset;
            this.mBottomInset = bottomInset;
        }
    }

    abstract class GridLinearSmoothScroller extends LinearSmoothScroller {
        boolean mSkipOnStopInternal;

        GridLinearSmoothScroller() {
            super(GridLayoutManager.this.mBaseGridView.getContext());
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
        protected void onStop() {
            super.onStop();
            if (!this.mSkipOnStopInternal) {
                onStopInternal();
            }
            if (GridLayoutManager.this.mCurrentSmoothScroller == this) {
                GridLayoutManager.this.mCurrentSmoothScroller = null;
            }
            if (GridLayoutManager.this.mPendingMoveSmoothScroller == this) {
                GridLayoutManager.this.mPendingMoveSmoothScroller = null;
            }
        }

        protected void onStopInternal() {
            View targetView = findViewByPosition(getTargetPosition());
            if (targetView == null) {
                if (getTargetPosition() >= 0) {
                    GridLayoutManager.this.scrollToSelection(getTargetPosition(), 0, false, 0);
                    return;
                }
                return;
            }
            if (GridLayoutManager.this.mFocusPosition != getTargetPosition()) {
                GridLayoutManager.this.mFocusPosition = getTargetPosition();
            }
            if (GridLayoutManager.this.hasFocus()) {
                GridLayoutManager.this.mFlag |= 32;
                targetView.requestFocus();
                GridLayoutManager.this.mFlag &= -33;
            }
            GridLayoutManager.this.dispatchChildSelected();
            GridLayoutManager.this.dispatchChildSelectedAndPositioned();
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return super.calculateSpeedPerPixel(displayMetrics) * GridLayoutManager.this.mSmoothScrollSpeedFactor;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        protected int calculateTimeForScrolling(int dx) {
            int ms = super.calculateTimeForScrolling(dx);
            if (GridLayoutManager.this.mWindowAlignment.mainAxis().getSize() > 0) {
                float minMs = (30.0f / GridLayoutManager.this.mWindowAlignment.mainAxis().getSize()) * dx;
                if (ms < minMs) {
                    return (int) minMs;
                }
                return ms;
            }
            return ms;
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller, androidx.recyclerview.widget.RecyclerView.SmoothScroller
        protected void onTargetFound(View targetView, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
            int dx;
            int dy;
            if (GridLayoutManager.this.getScrollPosition(targetView, null, GridLayoutManager.sTwoInts)) {
                if (GridLayoutManager.this.mOrientation == 0) {
                    dx = GridLayoutManager.sTwoInts[0];
                    dy = GridLayoutManager.sTwoInts[1];
                } else {
                    dx = GridLayoutManager.sTwoInts[1];
                    dy = GridLayoutManager.sTwoInts[0];
                }
                int distance = (int) Math.sqrt((dx * dx) + (dy * dy));
                int time = calculateTimeForDeceleration(distance);
                action.update(dx, dy, time, this.mDecelerateInterpolator);
            }
        }
    }

    final class PendingMoveSmoothScroller extends GridLinearSmoothScroller {
        static final int TARGET_UNDEFINED = -2;
        private int mPendingMoves;
        private final boolean mStaggeredGrid;

        PendingMoveSmoothScroller(int initialPendingMoves, boolean staggeredGrid) {
            super();
            this.mPendingMoves = initialPendingMoves;
            this.mStaggeredGrid = staggeredGrid;
            setTargetPosition(-2);
        }

        void increasePendingMoves() {
            if (this.mPendingMoves < GridLayoutManager.this.mMaxPendingMoves) {
                this.mPendingMoves++;
            }
        }

        void decreasePendingMoves() {
            if (this.mPendingMoves > (-GridLayoutManager.this.mMaxPendingMoves)) {
                this.mPendingMoves--;
            }
        }

        void consumePendingMovesBeforeLayout() {
            int i;
            View v;
            if (this.mStaggeredGrid || (i = this.mPendingMoves) == 0) {
                return;
            }
            View newSelected = null;
            int startPos = i > 0 ? GridLayoutManager.this.mFocusPosition + GridLayoutManager.this.mNumRows : GridLayoutManager.this.mFocusPosition - GridLayoutManager.this.mNumRows;
            int pos = startPos;
            while (this.mPendingMoves != 0 && (v = findViewByPosition(pos)) != null) {
                if (GridLayoutManager.this.canScrollTo(v)) {
                    newSelected = v;
                    GridLayoutManager.this.mFocusPosition = pos;
                    GridLayoutManager.this.mSubFocusPosition = 0;
                    int i2 = this.mPendingMoves;
                    if (i2 > 0) {
                        this.mPendingMoves = i2 - 1;
                    } else {
                        this.mPendingMoves = i2 + 1;
                    }
                }
                pos = this.mPendingMoves > 0 ? GridLayoutManager.this.mNumRows + pos : pos - GridLayoutManager.this.mNumRows;
            }
            if (newSelected != null && GridLayoutManager.this.hasFocus()) {
                GridLayoutManager.this.mFlag |= 32;
                newSelected.requestFocus();
                GridLayoutManager.this.mFlag &= -33;
            }
        }

        void consumePendingMovesAfterLayout() {
            int i;
            if (this.mStaggeredGrid && (i = this.mPendingMoves) != 0) {
                this.mPendingMoves = GridLayoutManager.this.processSelectionMoves(true, i);
            }
            int i2 = this.mPendingMoves;
            if (i2 == 0 || ((i2 > 0 && GridLayoutManager.this.hasCreatedLastItem()) || (this.mPendingMoves < 0 && GridLayoutManager.this.hasCreatedFirstItem()))) {
                setTargetPosition(GridLayoutManager.this.mFocusPosition);
                stop();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
        public PointF computeScrollVectorForPosition(int targetPosition) {
            if (this.mPendingMoves == 0) {
                return null;
            }
            int direction = ((GridLayoutManager.this.mFlag & 262144) == 0 ? this.mPendingMoves >= 0 : this.mPendingMoves <= 0) ? 1 : -1;
            if (GridLayoutManager.this.mOrientation == 0) {
                return new PointF(direction, 0.0f);
            }
            return new PointF(0.0f, direction);
        }

        @Override // androidx.leanback.widget.GridLayoutManager.GridLinearSmoothScroller
        protected void onStopInternal() {
            super.onStopInternal();
            this.mPendingMoves = 0;
            View v = findViewByPosition(getTargetPosition());
            if (v != null) {
                GridLayoutManager.this.scrollToView(v, true);
            }
        }
    }

    String getTag() {
        return "GridLayoutManager:" + this.mBaseGridView.getId();
    }

    public GridLayoutManager(BaseGridView baseGridView) {
        this.mBaseGridView = baseGridView;
        setItemPrefetchEnabled(false);
    }

    public void setOrientation(int orientation) {
        if (orientation != 0 && orientation != 1) {
            return;
        }
        this.mOrientation = orientation;
        this.mOrientationHelper = OrientationHelper.createOrientationHelper(this, orientation);
        this.mWindowAlignment.setOrientation(orientation);
        this.mItemAlignment.setOrientation(orientation);
        this.mFlag |= 256;
    }

    public void onRtlPropertiesChanged(int layoutDirection) {
        int flags;
        if (this.mOrientation == 0) {
            flags = layoutDirection == 1 ? 262144 : 0;
        } else {
            flags = layoutDirection == 1 ? 524288 : 0;
        }
        int i = this.mFlag;
        if ((PF_REVERSE_FLOW_MASK & i) == flags) {
            return;
        }
        int i2 = (i & (-786433)) | flags;
        this.mFlag = i2;
        this.mFlag = i2 | 256;
        this.mWindowAlignment.horizontal.setReversedFlow(layoutDirection == 1);
    }

    public int getFocusScrollStrategy() {
        return this.mFocusScrollStrategy;
    }

    public void setFocusScrollStrategy(int focusScrollStrategy) {
        this.mFocusScrollStrategy = focusScrollStrategy;
    }

    public void setWindowAlignment(int windowAlignment) {
        this.mWindowAlignment.mainAxis().setWindowAlignment(windowAlignment);
    }

    public int getWindowAlignment() {
        return this.mWindowAlignment.mainAxis().getWindowAlignment();
    }

    public void setWindowAlignmentOffset(int alignmentOffset) {
        this.mWindowAlignment.mainAxis().setWindowAlignmentOffset(alignmentOffset);
    }

    public int getWindowAlignmentOffset() {
        return this.mWindowAlignment.mainAxis().getWindowAlignmentOffset();
    }

    public void setWindowAlignmentOffsetPercent(float offsetPercent) {
        this.mWindowAlignment.mainAxis().setWindowAlignmentOffsetPercent(offsetPercent);
    }

    public float getWindowAlignmentOffsetPercent() {
        return this.mWindowAlignment.mainAxis().getWindowAlignmentOffsetPercent();
    }

    public void setItemAlignmentOffset(int alignmentOffset) {
        this.mItemAlignment.mainAxis().setItemAlignmentOffset(alignmentOffset);
        updateChildAlignments();
    }

    public int getItemAlignmentOffset() {
        return this.mItemAlignment.mainAxis().getItemAlignmentOffset();
    }

    public void setItemAlignmentOffsetWithPadding(boolean withPadding) {
        this.mItemAlignment.mainAxis().setItemAlignmentOffsetWithPadding(withPadding);
        updateChildAlignments();
    }

    public boolean isItemAlignmentOffsetWithPadding() {
        return this.mItemAlignment.mainAxis().isItemAlignmentOffsetWithPadding();
    }

    public void setItemAlignmentOffsetPercent(float offsetPercent) {
        this.mItemAlignment.mainAxis().setItemAlignmentOffsetPercent(offsetPercent);
        updateChildAlignments();
    }

    public float getItemAlignmentOffsetPercent() {
        return this.mItemAlignment.mainAxis().getItemAlignmentOffsetPercent();
    }

    public void setItemAlignmentViewId(int viewId) {
        this.mItemAlignment.mainAxis().setItemAlignmentViewId(viewId);
        updateChildAlignments();
    }

    public int getItemAlignmentViewId() {
        return this.mItemAlignment.mainAxis().getItemAlignmentViewId();
    }

    public void setFocusOutAllowed(boolean throughFront, boolean throughEnd) {
        this.mFlag = (this.mFlag & (-6145)) | (throughFront ? 2048 : 0) | (throughEnd ? 4096 : 0);
    }

    public void setFocusOutSideAllowed(boolean throughStart, boolean throughEnd) {
        this.mFlag = (this.mFlag & (-24577)) | (throughStart ? 8192 : 0) | (throughEnd ? 16384 : 0);
    }

    public void setNumRows(int numRows) {
        if (numRows < 0) {
            throw new IllegalArgumentException();
        }
        this.mNumRowsRequested = numRows;
    }

    public void setRowHeight(int height) {
        if (height >= 0 || height == -2) {
            this.mRowSizeSecondaryRequested = height;
            return;
        }
        throw new IllegalArgumentException("Invalid row height: " + height);
    }

    public void setItemSpacing(int space) {
        this.mHorizontalSpacing = space;
        this.mVerticalSpacing = space;
        this.mSpacingSecondary = space;
        this.mSpacingPrimary = space;
    }

    public void setVerticalSpacing(int space) {
        if (this.mOrientation == 1) {
            this.mVerticalSpacing = space;
            this.mSpacingPrimary = space;
        } else {
            this.mVerticalSpacing = space;
            this.mSpacingSecondary = space;
        }
    }

    public void setHorizontalSpacing(int space) {
        if (this.mOrientation == 0) {
            this.mHorizontalSpacing = space;
            this.mSpacingPrimary = space;
        } else {
            this.mHorizontalSpacing = space;
            this.mSpacingSecondary = space;
        }
    }

    public int getVerticalSpacing() {
        return this.mVerticalSpacing;
    }

    public int getHorizontalSpacing() {
        return this.mHorizontalSpacing;
    }

    public void setGravity(int gravity) {
        this.mGravity = gravity;
    }

    protected boolean hasDoneFirstLayout() {
        return this.mGrid != null;
    }

    public void setOnChildSelectedListener(OnChildSelectedListener listener) {
        this.mChildSelectedListener = listener;
    }

    public void setOnChildViewHolderSelectedListener(OnChildViewHolderSelectedListener listener) {
        if (listener == null) {
            this.mChildViewHolderSelectedListeners = null;
            return;
        }
        ArrayList<OnChildViewHolderSelectedListener> arrayList = this.mChildViewHolderSelectedListeners;
        if (arrayList == null) {
            this.mChildViewHolderSelectedListeners = new ArrayList<>();
        } else {
            arrayList.clear();
        }
        this.mChildViewHolderSelectedListeners.add(listener);
    }

    public void addOnChildViewHolderSelectedListener(OnChildViewHolderSelectedListener listener) {
        if (this.mChildViewHolderSelectedListeners == null) {
            this.mChildViewHolderSelectedListeners = new ArrayList<>();
        }
        this.mChildViewHolderSelectedListeners.add(listener);
    }

    public void removeOnChildViewHolderSelectedListener(OnChildViewHolderSelectedListener listener) {
        ArrayList<OnChildViewHolderSelectedListener> arrayList = this.mChildViewHolderSelectedListeners;
        if (arrayList != null) {
            arrayList.remove(listener);
        }
    }

    boolean hasOnChildViewHolderSelectedListener() {
        ArrayList<OnChildViewHolderSelectedListener> arrayList = this.mChildViewHolderSelectedListeners;
        return arrayList != null && arrayList.size() > 0;
    }

    void fireOnChildViewHolderSelected(RecyclerView parent, RecyclerView.ViewHolder child, int position, int subposition) {
        ArrayList<OnChildViewHolderSelectedListener> arrayList = this.mChildViewHolderSelectedListeners;
        if (arrayList == null) {
            return;
        }
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            this.mChildViewHolderSelectedListeners.get(i).onChildViewHolderSelected(parent, child, position, subposition);
        }
    }

    void fireOnChildViewHolderSelectedAndPositioned(RecyclerView parent, RecyclerView.ViewHolder child, int position, int subposition) {
        ArrayList<OnChildViewHolderSelectedListener> arrayList = this.mChildViewHolderSelectedListeners;
        if (arrayList == null) {
            return;
        }
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            this.mChildViewHolderSelectedListeners.get(i).onChildViewHolderSelectedAndPositioned(parent, child, position, subposition);
        }
    }

    public void addOnLayoutCompletedListener(BaseGridView.OnLayoutCompletedListener listener) {
        if (this.mOnLayoutCompletedListeners == null) {
            this.mOnLayoutCompletedListeners = new ArrayList<>();
        }
        this.mOnLayoutCompletedListeners.add(listener);
    }

    public void removeOnLayoutCompletedListener(BaseGridView.OnLayoutCompletedListener listener) {
        ArrayList<BaseGridView.OnLayoutCompletedListener> arrayList = this.mOnLayoutCompletedListeners;
        if (arrayList != null) {
            arrayList.remove(listener);
        }
    }

    void setOnChildLaidOutListener(OnChildLaidOutListener listener) {
        this.mChildLaidOutListener = listener;
    }

    private int getAdapterPositionByView(View view) {
        LayoutParams params;
        if (view == null || (params = (LayoutParams) view.getLayoutParams()) == null || params.isItemRemoved()) {
            return -1;
        }
        return params.getViewAdapterPosition();
    }

    int getSubPositionByView(View view, View childView) {
        if (view == null || childView == null) {
            return 0;
        }
        LayoutParams lp = (LayoutParams) view.getLayoutParams();
        ItemAlignmentFacet facet = lp.getItemAlignmentFacet();
        if (facet != null) {
            ItemAlignmentFacet.ItemAlignmentDef[] defs = facet.getAlignmentDefs();
            if (defs.length > 1) {
                while (childView != view) {
                    int id = childView.getId();
                    if (id != -1) {
                        for (int i = 1; i < defs.length; i++) {
                            if (defs[i].getItemAlignmentFocusViewId() == id) {
                                return i;
                            }
                        }
                    }
                    childView = (View) childView.getParent();
                }
            }
        }
        return 0;
    }

    private int getAdapterPositionByIndex(int index) {
        return getAdapterPositionByView(getChildAt(index));
    }

    void dispatchChildSelected() {
        if (this.mChildSelectedListener == null && !hasOnChildViewHolderSelectedListener()) {
            return;
        }
        int i = this.mFocusPosition;
        View view = i == -1 ? null : findViewByPosition(i);
        if (view != null) {
            RecyclerView.ViewHolder vh = this.mBaseGridView.getChildViewHolder(view);
            OnChildSelectedListener onChildSelectedListener = this.mChildSelectedListener;
            if (onChildSelectedListener != null) {
                onChildSelectedListener.onChildSelected(this.mBaseGridView, view, this.mFocusPosition, vh == null ? -1L : vh.getItemId());
            }
            fireOnChildViewHolderSelected(this.mBaseGridView, vh, this.mFocusPosition, this.mSubFocusPosition);
        } else {
            OnChildSelectedListener onChildSelectedListener2 = this.mChildSelectedListener;
            if (onChildSelectedListener2 != null) {
                onChildSelectedListener2.onChildSelected(this.mBaseGridView, null, -1, -1L);
            }
            fireOnChildViewHolderSelected(this.mBaseGridView, null, -1, 0);
        }
        if ((this.mFlag & 3) != 1 && !this.mBaseGridView.isLayoutRequested()) {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                if (getChildAt(i2).isLayoutRequested()) {
                    forceRequestLayout();
                    return;
                }
            }
        }
    }

    void dispatchChildSelectedAndPositioned() {
        if (!hasOnChildViewHolderSelectedListener()) {
            return;
        }
        int i = this.mFocusPosition;
        View view = i == -1 ? null : findViewByPosition(i);
        if (view != null) {
            RecyclerView.ViewHolder vh = this.mBaseGridView.getChildViewHolder(view);
            fireOnChildViewHolderSelectedAndPositioned(this.mBaseGridView, vh, this.mFocusPosition, this.mSubFocusPosition);
        } else {
            OnChildSelectedListener onChildSelectedListener = this.mChildSelectedListener;
            if (onChildSelectedListener != null) {
                onChildSelectedListener.onChildSelected(this.mBaseGridView, null, -1, -1L);
            }
            fireOnChildViewHolderSelectedAndPositioned(this.mBaseGridView, null, -1, 0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.mOrientation == 0 || this.mNumRows > 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.mOrientation == 1 || this.mNumRows > 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attrs) {
        return new LayoutParams(context, attrs);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        if (lp instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) lp);
        }
        if (lp instanceof RecyclerView.LayoutParams) {
            return new LayoutParams((RecyclerView.LayoutParams) lp);
        }
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) lp);
        }
        return new LayoutParams(lp);
    }

    protected View getViewForPosition(int position) {
        View v = this.mRecycler.getViewForPosition(position);
        LayoutParams lp = (LayoutParams) v.getLayoutParams();
        RecyclerView.ViewHolder vh = this.mBaseGridView.getChildViewHolder(v);
        lp.setItemAlignmentFacet((ItemAlignmentFacet) getFacet(vh, ItemAlignmentFacet.class));
        return v;
    }

    final int getOpticalLeft(View v) {
        return ((LayoutParams) v.getLayoutParams()).getOpticalLeft(v);
    }

    final int getOpticalRight(View v) {
        return ((LayoutParams) v.getLayoutParams()).getOpticalRight(v);
    }

    final int getOpticalTop(View v) {
        return ((LayoutParams) v.getLayoutParams()).getOpticalTop(v);
    }

    final int getOpticalBottom(View v) {
        return ((LayoutParams) v.getLayoutParams()).getOpticalBottom(v);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getDecoratedLeft(View child) {
        return super.getDecoratedLeft(child) + ((LayoutParams) child.getLayoutParams()).mLeftInset;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getDecoratedTop(View child) {
        return super.getDecoratedTop(child) + ((LayoutParams) child.getLayoutParams()).mTopInset;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getDecoratedRight(View child) {
        return super.getDecoratedRight(child) - ((LayoutParams) child.getLayoutParams()).mRightInset;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getDecoratedBottom(View child) {
        return super.getDecoratedBottom(child) - ((LayoutParams) child.getLayoutParams()).mBottomInset;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void getDecoratedBoundsWithMargins(View view, Rect outBounds) {
        super.getDecoratedBoundsWithMargins(view, outBounds);
        LayoutParams params = (LayoutParams) view.getLayoutParams();
        outBounds.left += params.mLeftInset;
        outBounds.top += params.mTopInset;
        outBounds.right -= params.mRightInset;
        outBounds.bottom -= params.mBottomInset;
    }

    int getViewMin(View v) {
        return this.mOrientationHelper.getDecoratedStart(v);
    }

    int getViewMax(View v) {
        return this.mOrientationHelper.getDecoratedEnd(v);
    }

    int getViewPrimarySize(View view) {
        getDecoratedBoundsWithMargins(view, sTempRect);
        return this.mOrientation == 0 ? sTempRect.width() : sTempRect.height();
    }

    private int getViewCenter(View view) {
        return this.mOrientation == 0 ? getViewCenterX(view) : getViewCenterY(view);
    }

    private int getViewCenterSecondary(View view) {
        return this.mOrientation == 0 ? getViewCenterY(view) : getViewCenterX(view);
    }

    private int getViewCenterX(View v) {
        LayoutParams p = (LayoutParams) v.getLayoutParams();
        return p.getOpticalLeft(v) + p.getAlignX();
    }

    private int getViewCenterY(View v) {
        LayoutParams p = (LayoutParams) v.getLayoutParams();
        return p.getOpticalTop(v) + p.getAlignY();
    }

    private void saveContext(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mSaveContextLevel == 0) {
            this.mRecycler = recycler;
            this.mState = state;
            this.mPositionDeltaInPreLayout = 0;
            this.mExtraLayoutSpaceInPreLayout = 0;
        }
        this.mSaveContextLevel++;
    }

    private void leaveContext() {
        int i = this.mSaveContextLevel - 1;
        this.mSaveContextLevel = i;
        if (i == 0) {
            this.mRecycler = null;
            this.mState = null;
            this.mPositionDeltaInPreLayout = 0;
            this.mExtraLayoutSpaceInPreLayout = 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean layoutInit() {
        /*
            r6 = this;
            androidx.recyclerview.widget.RecyclerView$State r0 = r6.mState
            int r0 = r0.getItemCount()
            r1 = -1
            r2 = 0
            if (r0 != 0) goto Lf
            r6.mFocusPosition = r1
            r6.mSubFocusPosition = r2
            goto L22
        Lf:
            int r3 = r6.mFocusPosition
            if (r3 < r0) goto L1a
            int r1 = r0 + (-1)
            r6.mFocusPosition = r1
            r6.mSubFocusPosition = r2
            goto L22
        L1a:
            if (r3 != r1) goto L22
            if (r0 <= 0) goto L22
            r6.mFocusPosition = r2
            r6.mSubFocusPosition = r2
        L22:
            androidx.recyclerview.widget.RecyclerView$State r1 = r6.mState
            boolean r1 = r1.didStructureChange()
            r3 = 1
            if (r1 != 0) goto L53
            androidx.leanback.widget.Grid r1 = r6.mGrid
            if (r1 == 0) goto L53
            int r1 = r1.getFirstVisibleIndex()
            if (r1 < 0) goto L53
            int r1 = r6.mFlag
            r1 = r1 & 256(0x100, float:3.59E-43)
            if (r1 != 0) goto L53
            androidx.leanback.widget.Grid r1 = r6.mGrid
            int r1 = r1.getNumRows()
            int r4 = r6.mNumRows
            if (r1 != r4) goto L53
            r6.updateScrollController()
            r6.updateSecondaryScrollLimits()
            androidx.leanback.widget.Grid r1 = r6.mGrid
            int r2 = r6.mSpacingPrimary
            r1.setSpacing(r2)
            return r3
        L53:
            int r1 = r6.mFlag
            r1 = r1 & (-257(0xfffffffffffffeff, float:NaN))
            r6.mFlag = r1
            androidx.leanback.widget.Grid r1 = r6.mGrid
            r4 = 262144(0x40000, float:3.67342E-40)
            if (r1 == 0) goto L77
            int r5 = r6.mNumRows
            int r1 = r1.getNumRows()
            if (r5 != r1) goto L77
            int r1 = r6.mFlag
            r1 = r1 & r4
            if (r1 == 0) goto L6e
            r1 = r3
            goto L6f
        L6e:
            r1 = r2
        L6f:
            androidx.leanback.widget.Grid r5 = r6.mGrid
            boolean r5 = r5.isReversedFlow()
            if (r1 == r5) goto L90
        L77:
            int r1 = r6.mNumRows
            androidx.leanback.widget.Grid r1 = androidx.leanback.widget.Grid.createGrid(r1)
            r6.mGrid = r1
            androidx.leanback.widget.Grid$Provider r5 = r6.mGridProvider
            r1.setProvider(r5)
            androidx.leanback.widget.Grid r1 = r6.mGrid
            int r5 = r6.mFlag
            r4 = r4 & r5
            if (r4 == 0) goto L8c
            goto L8d
        L8c:
            r3 = r2
        L8d:
            r1.setReversedFlow(r3)
        L90:
            r6.initScrollController()
            r6.updateSecondaryScrollLimits()
            androidx.leanback.widget.Grid r1 = r6.mGrid
            int r3 = r6.mSpacingPrimary
            r1.setSpacing(r3)
            androidx.recyclerview.widget.RecyclerView$Recycler r1 = r6.mRecycler
            r6.detachAndScrapAttachedViews(r1)
            androidx.leanback.widget.Grid r1 = r6.mGrid
            r1.resetVisibleIndex()
            androidx.leanback.widget.WindowAlignment r1 = r6.mWindowAlignment
            androidx.leanback.widget.WindowAlignment$Axis r1 = r1.mainAxis()
            r1.invalidateScrollMin()
            androidx.leanback.widget.WindowAlignment r1 = r6.mWindowAlignment
            androidx.leanback.widget.WindowAlignment$Axis r1 = r1.mainAxis()
            r1.invalidateScrollMax()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GridLayoutManager.layoutInit():boolean");
    }

    private int getRowSizeSecondary(int rowIndex) {
        int i = this.mFixedRowSizeSecondary;
        if (i != 0) {
            return i;
        }
        int[] iArr = this.mRowSizeSecondary;
        if (iArr == null) {
            return 0;
        }
        return iArr[rowIndex];
    }

    int getRowStartSecondary(int rowIndex) {
        int start = 0;
        if ((this.mFlag & 524288) != 0) {
            for (int i = this.mNumRows - 1; i > rowIndex; i--) {
                start += getRowSizeSecondary(i) + this.mSpacingSecondary;
            }
        } else {
            for (int i2 = 0; i2 < rowIndex; i2++) {
                start += getRowSizeSecondary(i2) + this.mSpacingSecondary;
            }
        }
        return start;
    }

    private int getSizeSecondary() {
        int rightmostIndex = (this.mFlag & 524288) != 0 ? 0 : this.mNumRows - 1;
        return getRowStartSecondary(rightmostIndex) + getRowSizeSecondary(rightmostIndex);
    }

    int getDecoratedMeasuredWidthWithMargin(View v) {
        LayoutParams lp = (LayoutParams) v.getLayoutParams();
        return getDecoratedMeasuredWidth(v) + lp.leftMargin + lp.rightMargin;
    }

    int getDecoratedMeasuredHeightWithMargin(View v) {
        LayoutParams lp = (LayoutParams) v.getLayoutParams();
        return getDecoratedMeasuredHeight(v) + lp.topMargin + lp.bottomMargin;
    }

    private void measureScrapChild(int position, int widthSpec, int heightSpec, int[] measuredDimension) {
        View view = this.mRecycler.getViewForPosition(position);
        if (view != null) {
            LayoutParams p = (LayoutParams) view.getLayoutParams();
            calculateItemDecorationsForChild(view, sTempRect);
            int widthUsed = p.leftMargin + p.rightMargin + sTempRect.left + sTempRect.right;
            int heightUsed = p.topMargin + p.bottomMargin + sTempRect.top + sTempRect.bottom;
            int childWidthSpec = ViewGroup.getChildMeasureSpec(widthSpec, getPaddingLeft() + getPaddingRight() + widthUsed, p.width);
            int childHeightSpec = ViewGroup.getChildMeasureSpec(heightSpec, getPaddingTop() + getPaddingBottom() + heightUsed, p.height);
            view.measure(childWidthSpec, childHeightSpec);
            measuredDimension[0] = getDecoratedMeasuredWidthWithMargin(view);
            measuredDimension[1] = getDecoratedMeasuredHeightWithMargin(view);
            this.mRecycler.recycleView(view);
        }
    }

    private boolean processRowSizeSecondary(boolean measure) {
        int i;
        int secondarySize;
        if (this.mFixedRowSizeSecondary != 0 || this.mRowSizeSecondary == null) {
            return false;
        }
        Grid grid = this.mGrid;
        CircularIntArray[] rows = grid == null ? null : grid.getItemPositionsInRows();
        boolean changed = false;
        int scrapeChildSize = -1;
        for (int rowIndex = 0; rowIndex < this.mNumRows; rowIndex++) {
            CircularIntArray row = rows == null ? null : rows[rowIndex];
            int rowItemsPairCount = row == null ? 0 : row.size();
            int rowSize = -1;
            for (int rowItemPairIndex = 0; rowItemPairIndex < rowItemsPairCount; rowItemPairIndex += 2) {
                int rowIndexStart = row.get(rowItemPairIndex);
                int rowIndexEnd = row.get(rowItemPairIndex + 1);
                for (int i2 = rowIndexStart; i2 <= rowIndexEnd; i2++) {
                    View view = findViewByPosition(i2 - this.mPositionDeltaInPreLayout);
                    if (view != null) {
                        if (measure) {
                            measureChild(view);
                        }
                        if (this.mOrientation == 0) {
                            secondarySize = getDecoratedMeasuredHeightWithMargin(view);
                        } else {
                            secondarySize = getDecoratedMeasuredWidthWithMargin(view);
                        }
                        if (secondarySize > rowSize) {
                            rowSize = secondarySize;
                        }
                    }
                }
            }
            int itemCount = this.mState.getItemCount();
            if (!this.mBaseGridView.hasFixedSize() && measure && rowSize < 0 && itemCount > 0) {
                if (scrapeChildSize < 0) {
                    int position = this.mFocusPosition;
                    if (position < 0) {
                        position = 0;
                    } else if (position >= itemCount) {
                        position = itemCount - 1;
                    }
                    if (getChildCount() > 0) {
                        int firstPos = this.mBaseGridView.getChildViewHolder(getChildAt(0)).getLayoutPosition();
                        int lastPos = this.mBaseGridView.getChildViewHolder(getChildAt(getChildCount() - 1)).getLayoutPosition();
                        if (position >= firstPos && position <= lastPos) {
                            position = position - firstPos <= lastPos - position ? firstPos - 1 : lastPos + 1;
                            if (position < 0 && lastPos < itemCount - 1) {
                                position = lastPos + 1;
                            } else if (position >= itemCount && firstPos > 0) {
                                position = firstPos - 1;
                            }
                        }
                    }
                    if (position >= 0 && position < itemCount) {
                        measureScrapChild(position, View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0), this.mMeasuredDimension);
                        if (this.mOrientation == 0) {
                            i = this.mMeasuredDimension[1];
                        } else {
                            i = this.mMeasuredDimension[0];
                        }
                        scrapeChildSize = i;
                    }
                }
                if (scrapeChildSize >= 0) {
                    rowSize = scrapeChildSize;
                }
            }
            if (rowSize < 0) {
                rowSize = 0;
            }
            int[] iArr = this.mRowSizeSecondary;
            if (iArr[rowIndex] != rowSize) {
                iArr[rowIndex] = rowSize;
                changed = true;
            }
        }
        return changed;
    }

    private void updateRowSecondarySizeRefresh() {
        int i = (this.mFlag & (-1025)) | (processRowSizeSecondary(false) ? 1024 : 0);
        this.mFlag = i;
        if ((i & 1024) != 0) {
            forceRequestLayout();
        }
    }

    private void forceRequestLayout() {
        ViewCompat.postOnAnimation(this.mBaseGridView, this.mRequestLayoutRunnable);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00e9  */
    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(androidx.recyclerview.widget.RecyclerView.Recycler r11, androidx.recyclerview.widget.RecyclerView.State r12, int r13, int r14) {
        /*
            Method dump skipped, instruction units count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GridLayoutManager.onMeasure(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State, int, int):void");
    }

    void measureChild(View child) {
        int secondarySpec;
        int heightSpec;
        int widthSpec;
        LayoutParams lp = (LayoutParams) child.getLayoutParams();
        calculateItemDecorationsForChild(child, sTempRect);
        int widthUsed = lp.leftMargin + lp.rightMargin + sTempRect.left + sTempRect.right;
        int heightUsed = lp.topMargin + lp.bottomMargin + sTempRect.top + sTempRect.bottom;
        if (this.mRowSizeSecondaryRequested == -2) {
            secondarySpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        } else {
            secondarySpec = View.MeasureSpec.makeMeasureSpec(this.mFixedRowSizeSecondary, 1073741824);
        }
        if (this.mOrientation == 0) {
            widthSpec = ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), widthUsed, lp.width);
            heightSpec = ViewGroup.getChildMeasureSpec(secondarySpec, heightUsed, lp.height);
        } else {
            heightSpec = ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), heightUsed, lp.height);
            widthSpec = ViewGroup.getChildMeasureSpec(secondarySpec, widthUsed, lp.width);
        }
        child.measure(widthSpec, heightSpec);
    }

    /* JADX WARN: Multi-variable type inference failed */
    <E> E getFacet(RecyclerView.ViewHolder viewHolder, Class<? extends E> cls) {
        FacetProviderAdapter facetProviderAdapter;
        FacetProvider facetProvider;
        E e = null;
        if (viewHolder instanceof FacetProvider) {
            e = (E) ((FacetProvider) viewHolder).getFacet(cls);
        }
        return (e != null || (facetProviderAdapter = this.mFacetProviderAdapter) == null || (facetProvider = facetProviderAdapter.getFacetProvider(viewHolder.getItemViewType())) == null) ? e : (E) facetProvider.getFacet(cls);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void layoutChild(int r18, android.view.View r19, int r20, int r21, int r22) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GridLayoutManager.layoutChild(int, android.view.View, int, int, int):void");
    }

    private void updateChildAlignments(View v) {
        LayoutParams p = (LayoutParams) v.getLayoutParams();
        if (p.getItemAlignmentFacet() == null) {
            p.setAlignX(this.mItemAlignment.horizontal.getAlignmentPosition(v));
            p.setAlignY(this.mItemAlignment.vertical.getAlignmentPosition(v));
            return;
        }
        p.calculateItemAlignments(this.mOrientation, v);
        if (this.mOrientation == 0) {
            p.setAlignY(this.mItemAlignment.vertical.getAlignmentPosition(v));
        } else {
            p.setAlignX(this.mItemAlignment.horizontal.getAlignmentPosition(v));
        }
    }

    private void updateChildAlignments() {
        int c = getChildCount();
        for (int i = 0; i < c; i++) {
            updateChildAlignments(getChildAt(i));
        }
    }

    void setExtraLayoutSpace(int extraLayoutSpace) {
        int i = this.mExtraLayoutSpace;
        if (i == extraLayoutSpace) {
            return;
        }
        if (i < 0) {
            throw new IllegalArgumentException("ExtraLayoutSpace must >= 0");
        }
        this.mExtraLayoutSpace = extraLayoutSpace;
        requestLayout();
    }

    int getExtraLayoutSpace() {
        return this.mExtraLayoutSpace;
    }

    private void removeInvisibleViewsAtEnd() {
        int i = this.mFlag;
        if ((65600 & i) == 65536) {
            this.mGrid.removeInvisibleItemsAtEnd(this.mFocusPosition, (i & 262144) != 0 ? -this.mExtraLayoutSpace : this.mSizePrimary + this.mExtraLayoutSpace);
        }
    }

    private void removeInvisibleViewsAtFront() {
        int i = this.mFlag;
        if ((65600 & i) == 65536) {
            this.mGrid.removeInvisibleItemsAtFront(this.mFocusPosition, (i & 262144) != 0 ? this.mSizePrimary + this.mExtraLayoutSpace : -this.mExtraLayoutSpace);
        }
    }

    private boolean appendOneColumnVisibleItems() {
        return this.mGrid.appendOneColumnVisibleItems();
    }

    void slideIn() {
        int i = this.mFlag;
        if ((i & 64) != 0) {
            int i2 = i & (-65);
            this.mFlag = i2;
            int i3 = this.mFocusPosition;
            if (i3 >= 0) {
                scrollToSelection(i3, this.mSubFocusPosition, true, this.mPrimaryScrollExtra);
            } else {
                this.mFlag = i2 & (-129);
                requestLayout();
            }
            int i4 = this.mFlag;
            if ((i4 & 128) != 0) {
                this.mFlag = i4 & (-129);
                if (this.mBaseGridView.getScrollState() != 0 || isSmoothScrolling()) {
                    this.mBaseGridView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: androidx.leanback.widget.GridLayoutManager.3
                        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                            if (newState == 0) {
                                GridLayoutManager.this.mBaseGridView.removeOnScrollListener(this);
                                GridLayoutManager.this.requestLayout();
                            }
                        }
                    });
                } else {
                    requestLayout();
                }
            }
        }
    }

    int getSlideOutDistance() {
        int start;
        int start2;
        int top;
        if (this.mOrientation == 1) {
            int distance = -getHeight();
            if (getChildCount() > 0 && (top = getChildAt(0).getTop()) < 0) {
                return distance + top;
            }
            return distance;
        }
        int distance2 = this.mFlag;
        if ((distance2 & 262144) != 0) {
            int distance3 = getWidth();
            if (getChildCount() > 0 && (start2 = getChildAt(0).getRight()) > distance3) {
                return start2;
            }
            return distance3;
        }
        int distance4 = getWidth();
        int distance5 = -distance4;
        if (getChildCount() > 0 && (start = getChildAt(0).getLeft()) < 0) {
            return distance5 + start;
        }
        return distance5;
    }

    boolean isSlidingChildViews() {
        return (this.mFlag & 64) != 0;
    }

    void slideOut() {
        int i = this.mFlag;
        if ((i & 64) != 0) {
            return;
        }
        this.mFlag = i | 64;
        if (getChildCount() == 0) {
            return;
        }
        if (this.mOrientation == 1) {
            this.mBaseGridView.smoothScrollBy(0, getSlideOutDistance(), new AccelerateDecelerateInterpolator());
        } else {
            this.mBaseGridView.smoothScrollBy(getSlideOutDistance(), 0, new AccelerateDecelerateInterpolator());
        }
    }

    private boolean prependOneColumnVisibleItems() {
        return this.mGrid.prependOneColumnVisibleItems();
    }

    private void appendVisibleItems() {
        int i;
        Grid grid = this.mGrid;
        if ((this.mFlag & 262144) != 0) {
            i = (-this.mExtraLayoutSpace) - this.mExtraLayoutSpaceInPreLayout;
        } else {
            i = this.mSizePrimary + this.mExtraLayoutSpace + this.mExtraLayoutSpaceInPreLayout;
        }
        grid.appendVisibleItems(i);
    }

    private void prependVisibleItems() {
        int i;
        Grid grid = this.mGrid;
        if ((this.mFlag & 262144) != 0) {
            i = this.mSizePrimary + this.mExtraLayoutSpace + this.mExtraLayoutSpaceInPreLayout;
        } else {
            i = (-this.mExtraLayoutSpace) - this.mExtraLayoutSpaceInPreLayout;
        }
        grid.prependVisibleItems(i);
    }

    private void fastRelayout() {
        View view;
        int primarySize;
        int end;
        boolean invalidateAfter = false;
        int childCount = getChildCount();
        int position = this.mGrid.getFirstVisibleIndex();
        this.mFlag &= -9;
        int position2 = position;
        int index = 0;
        while (true) {
            if (index >= childCount) {
                break;
            }
            View view2 = getChildAt(index);
            if (position2 != getAdapterPositionByView(view2)) {
                invalidateAfter = true;
                break;
            }
            Grid.Location location = this.mGrid.getLocation(position2);
            if (location != null) {
                int startSecondary = (getRowStartSecondary(location.row) + this.mWindowAlignment.secondAxis().getPaddingMin()) - this.mScrollOffsetSecondary;
                int start = getViewMin(view2);
                int oldPrimarySize = getViewPrimarySize(view2);
                LayoutParams lp = (LayoutParams) view2.getLayoutParams();
                if (lp.viewNeedsUpdate()) {
                    this.mFlag |= 8;
                    detachAndScrapView(view2, this.mRecycler);
                    View view3 = getViewForPosition(position2);
                    addView(view3, index);
                    view = view3;
                } else {
                    view = view2;
                }
                measureChild(view);
                if (this.mOrientation == 0) {
                    int primarySize2 = getDecoratedMeasuredWidthWithMargin(view);
                    primarySize = primarySize2;
                    end = start + primarySize2;
                } else {
                    int primarySize3 = getDecoratedMeasuredHeightWithMargin(view);
                    primarySize = primarySize3;
                    end = start + primarySize3;
                }
                boolean invalidateAfter2 = invalidateAfter;
                int primarySize4 = primarySize;
                layoutChild(location.row, view, start, end, startSecondary);
                if (oldPrimarySize == primarySize4) {
                    index++;
                    position2++;
                    invalidateAfter = invalidateAfter2;
                } else {
                    invalidateAfter = true;
                    break;
                }
            } else {
                invalidateAfter = true;
                break;
            }
        }
        if (invalidateAfter) {
            int savedLastPos = this.mGrid.getLastVisibleIndex();
            for (int i = childCount - 1; i >= index; i--) {
                View v = getChildAt(i);
                detachAndScrapView(v, this.mRecycler);
            }
            this.mGrid.invalidateItemsAfter(position2);
            if ((this.mFlag & 65536) != 0) {
                appendVisibleItems();
                int i2 = this.mFocusPosition;
                if (i2 >= 0 && i2 <= savedLastPos) {
                    while (this.mGrid.getLastVisibleIndex() < this.mFocusPosition) {
                        this.mGrid.appendOneColumnVisibleItems();
                    }
                }
            } else {
                while (this.mGrid.appendOneColumnVisibleItems() && this.mGrid.getLastVisibleIndex() < savedLastPos) {
                }
            }
        }
        updateScrollLimits();
        updateSecondaryScrollLimits();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void removeAndRecycleAllViews(RecyclerView.Recycler recycler) {
        for (int i = getChildCount() - 1; i >= 0; i--) {
            removeAndRecycleViewAt(i, recycler);
        }
    }

    private void focusToViewInLayout(boolean hadFocus, boolean alignToView, int extraDelta, int extraDeltaSecondary) {
        View focusView = findViewByPosition(this.mFocusPosition);
        if (focusView != null && alignToView) {
            scrollToView(focusView, false, extraDelta, extraDeltaSecondary);
        }
        if (focusView != null && hadFocus && !focusView.hasFocus()) {
            focusView.requestFocus();
            return;
        }
        if (!hadFocus && !this.mBaseGridView.hasFocus()) {
            if (focusView != null && focusView.hasFocusable()) {
                this.mBaseGridView.focusableViewAvailable(focusView);
            } else {
                int i = 0;
                int count = getChildCount();
                while (true) {
                    if (i < count) {
                        focusView = getChildAt(i);
                        if (focusView == null || !focusView.hasFocusable()) {
                            i++;
                        } else {
                            this.mBaseGridView.focusableViewAvailable(focusView);
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (alignToView && focusView != null && focusView.hasFocus()) {
                scrollToView(focusView, false, extraDelta, extraDeltaSecondary);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        ArrayList<BaseGridView.OnLayoutCompletedListener> arrayList = this.mOnLayoutCompletedListeners;
        if (arrayList != null) {
            for (int i = arrayList.size() - 1; i >= 0; i--) {
                this.mOnLayoutCompletedListeners.get(i).onLayoutCompleted(state);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        return true;
    }

    void updatePositionToRowMapInPostLayout() {
        Grid.Location loc;
        this.mPositionToRowInPostLayout.clear();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            int position = this.mBaseGridView.getChildViewHolder(getChildAt(i)).getOldPosition();
            if (position >= 0 && (loc = this.mGrid.getLocation(position)) != null) {
                this.mPositionToRowInPostLayout.put(position, loc.row);
            }
        }
    }

    void fillScrapViewsInPostLayout() {
        List<RecyclerView.ViewHolder> scrapList = this.mRecycler.getScrapList();
        int scrapSize = scrapList.size();
        if (scrapSize == 0) {
            return;
        }
        int[] iArr = this.mDisappearingPositions;
        if (iArr == null || scrapSize > iArr.length) {
            int[] iArr2 = this.mDisappearingPositions;
            int length = iArr2 == null ? 16 : iArr2.length;
            while (length < scrapSize) {
                length <<= 1;
            }
            this.mDisappearingPositions = new int[length];
        }
        int totalItems = 0;
        for (int i = 0; i < scrapSize; i++) {
            int pos = scrapList.get(i).getAbsoluteAdapterPosition();
            if (pos >= 0) {
                this.mDisappearingPositions[totalItems] = pos;
                totalItems++;
            }
        }
        if (totalItems > 0) {
            Arrays.sort(this.mDisappearingPositions, 0, totalItems);
            this.mGrid.fillDisappearingItems(this.mDisappearingPositions, totalItems, this.mPositionToRowInPostLayout);
        }
        this.mPositionToRowInPostLayout.clear();
    }

    void updatePositionDeltaInPreLayout() {
        if (getChildCount() > 0) {
            View view = getChildAt(0);
            LayoutParams lp = (LayoutParams) view.getLayoutParams();
            this.mPositionDeltaInPreLayout = this.mGrid.getFirstVisibleIndex() - lp.getViewLayoutPosition();
            return;
        }
        this.mPositionDeltaInPreLayout = 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int deltaSecondary;
        int deltaPrimary;
        int startFromPosition;
        int endPos;
        int i;
        int i2;
        int i3;
        if (this.mNumRows == 0) {
            return;
        }
        int itemCount = state.getItemCount();
        if (itemCount < 0) {
            return;
        }
        if ((this.mFlag & 64) != 0 && getChildCount() > 0) {
            this.mFlag |= 128;
            return;
        }
        int i4 = this.mFlag;
        if ((i4 & 512) != 0) {
            this.mFlag = (i4 & (-4)) | 1;
            saveContext(recycler, state);
            if (state.isPreLayout()) {
                updatePositionDeltaInPreLayout();
                int childCount = getChildCount();
                if (this.mGrid != null && childCount > 0) {
                    int minChangedEdge = Integer.MAX_VALUE;
                    int maxChangeEdge = Integer.MIN_VALUE;
                    int minOldAdapterPosition = this.mBaseGridView.getChildViewHolder(getChildAt(0)).getOldPosition();
                    int maxOldAdapterPosition = this.mBaseGridView.getChildViewHolder(getChildAt(childCount - 1)).getOldPosition();
                    for (int i5 = 0; i5 < childCount; i5++) {
                        View view = getChildAt(i5);
                        LayoutParams lp = (LayoutParams) view.getLayoutParams();
                        int newAdapterPosition = this.mBaseGridView.getChildAdapterPosition(view);
                        if (lp.isItemChanged() || lp.isItemRemoved() || view.isLayoutRequested() || ((!view.hasFocus() && this.mFocusPosition == lp.getViewAdapterPosition()) || ((view.hasFocus() && this.mFocusPosition != lp.getViewAdapterPosition()) || newAdapterPosition < minOldAdapterPosition || newAdapterPosition > maxOldAdapterPosition))) {
                            minChangedEdge = Math.min(minChangedEdge, getViewMin(view));
                            maxChangeEdge = Math.max(maxChangeEdge, getViewMax(view));
                        }
                    }
                    if (maxChangeEdge > minChangedEdge) {
                        this.mExtraLayoutSpaceInPreLayout = maxChangeEdge - minChangedEdge;
                    }
                    appendVisibleItems();
                    prependVisibleItems();
                }
                int minChangedEdge2 = this.mFlag;
                this.mFlag = minChangedEdge2 & (-4);
                leaveContext();
                return;
            }
            if (state.willRunPredictiveAnimations()) {
                updatePositionToRowMapInPostLayout();
            }
            boolean scrollToFocus = !isSmoothScrolling() && this.mFocusScrollStrategy == 0;
            int i6 = this.mFocusPosition;
            if (i6 != -1 && (i3 = this.mFocusPositionOffset) != Integer.MIN_VALUE) {
                this.mFocusPosition = i6 + i3;
                this.mSubFocusPosition = 0;
            }
            this.mFocusPositionOffset = 0;
            View savedFocusView = findViewByPosition(this.mFocusPosition);
            int savedFocusPos = this.mFocusPosition;
            int savedSubFocusPos = this.mSubFocusPosition;
            boolean hadFocus = this.mBaseGridView.hasFocus();
            Grid grid = this.mGrid;
            int firstVisibleIndex = grid != null ? grid.getFirstVisibleIndex() : -1;
            Grid grid2 = this.mGrid;
            int lastVisibleIndex = grid2 != null ? grid2.getLastVisibleIndex() : -1;
            if (this.mOrientation == 0) {
                deltaPrimary = state.getRemainingScrollHorizontal();
                deltaSecondary = state.getRemainingScrollVertical();
            } else {
                deltaSecondary = state.getRemainingScrollHorizontal();
                deltaPrimary = state.getRemainingScrollVertical();
            }
            if (layoutInit()) {
                this.mFlag |= 4;
                this.mGrid.setStart(this.mFocusPosition);
                fastRelayout();
            } else {
                int i7 = this.mFlag & (-5);
                this.mFlag = i7;
                this.mFlag = (scrollToFocus ? 16 : 0) | (i7 & (-17));
                if (scrollToFocus && (firstVisibleIndex < 0 || (i = this.mFocusPosition) > lastVisibleIndex || i < firstVisibleIndex)) {
                    startFromPosition = this.mFocusPosition;
                    endPos = startFromPosition;
                } else {
                    startFromPosition = firstVisibleIndex;
                    endPos = lastVisibleIndex;
                }
                this.mGrid.setStart(startFromPosition);
                if (endPos != -1) {
                    while (appendOneColumnVisibleItems() && findViewByPosition(endPos) == null) {
                    }
                }
            }
            while (true) {
                updateScrollLimits();
                int oldFirstVisible = this.mGrid.getFirstVisibleIndex();
                int oldLastVisible = this.mGrid.getLastVisibleIndex();
                focusToViewInLayout(hadFocus, scrollToFocus, -deltaPrimary, -deltaSecondary);
                appendVisibleItems();
                prependVisibleItems();
                if (this.mGrid.getFirstVisibleIndex() == oldFirstVisible && this.mGrid.getLastVisibleIndex() == oldLastVisible) {
                    break;
                }
            }
            removeInvisibleViewsAtFront();
            removeInvisibleViewsAtEnd();
            if (state.willRunPredictiveAnimations()) {
                fillScrapViewsInPostLayout();
            }
            int i8 = this.mFlag;
            if ((i8 & 1024) != 0) {
                this.mFlag = i8 & (-1025);
            } else {
                updateRowSecondarySizeRefresh();
            }
            if (((this.mFlag & 4) != 0 && ((i2 = this.mFocusPosition) != savedFocusPos || this.mSubFocusPosition != savedSubFocusPos || findViewByPosition(i2) != savedFocusView || (this.mFlag & 8) != 0)) || (this.mFlag & 20) == 16) {
                dispatchChildSelected();
            }
            dispatchChildSelectedAndPositioned();
            if ((this.mFlag & 64) != 0) {
                scrollDirectionPrimary(getSlideOutDistance());
            }
            this.mFlag &= -4;
            leaveContext();
            return;
        }
        discardLayoutInfo();
        removeAndRecycleAllViews(recycler);
    }

    private void offsetChildrenSecondary(int increment) {
        int childCount = getChildCount();
        if (this.mOrientation == 0) {
            for (int i = 0; i < childCount; i++) {
                getChildAt(i).offsetTopAndBottom(increment);
            }
            return;
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).offsetLeftAndRight(increment);
        }
    }

    private void offsetChildrenPrimary(int increment) {
        int childCount = getChildCount();
        if (this.mOrientation == 1) {
            for (int i = 0; i < childCount; i++) {
                getChildAt(i).offsetTopAndBottom(increment);
            }
            return;
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            getChildAt(i2).offsetLeftAndRight(increment);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int result;
        if ((this.mFlag & 512) == 0 || !hasDoneFirstLayout()) {
            return 0;
        }
        saveContext(recycler, state);
        this.mFlag = (this.mFlag & (-4)) | 2;
        if (this.mOrientation == 0) {
            result = scrollDirectionPrimary(dx);
        } else {
            result = scrollDirectionSecondary(dx);
        }
        leaveContext();
        this.mFlag &= -4;
        return result;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int result;
        if ((this.mFlag & 512) == 0 || !hasDoneFirstLayout()) {
            return 0;
        }
        this.mFlag = (this.mFlag & (-4)) | 2;
        saveContext(recycler, state);
        if (this.mOrientation == 1) {
            result = scrollDirectionPrimary(dy);
        } else {
            result = scrollDirectionSecondary(dy);
        }
        leaveContext();
        this.mFlag &= -4;
        return result;
    }

    private int scrollDirectionPrimary(int da) {
        int minScroll;
        int maxScroll;
        int i = this.mFlag;
        if ((i & 64) == 0 && (i & 3) != 1) {
            if (da > 0) {
                if (!this.mWindowAlignment.mainAxis().isMaxUnknown() && da > (maxScroll = this.mWindowAlignment.mainAxis().getMaxScroll())) {
                    da = maxScroll;
                }
            } else if (da < 0 && !this.mWindowAlignment.mainAxis().isMinUnknown() && da < (minScroll = this.mWindowAlignment.mainAxis().getMinScroll())) {
                da = minScroll;
            }
        }
        if (da == 0) {
            return 0;
        }
        offsetChildrenPrimary(-da);
        if ((this.mFlag & 3) == 1) {
            updateScrollLimits();
            return da;
        }
        int childCount = getChildCount();
        if ((this.mFlag & 262144) == 0 ? da < 0 : da > 0) {
            prependVisibleItems();
        } else {
            appendVisibleItems();
        }
        boolean updated = getChildCount() > childCount;
        int childCount2 = getChildCount();
        if ((262144 & this.mFlag) == 0 ? da < 0 : da > 0) {
            removeInvisibleViewsAtEnd();
        } else {
            removeInvisibleViewsAtFront();
        }
        if (updated | (getChildCount() < childCount2)) {
            updateRowSecondarySizeRefresh();
        }
        this.mBaseGridView.invalidate();
        updateScrollLimits();
        return da;
    }

    private int scrollDirectionSecondary(int dy) {
        if (dy == 0) {
            return 0;
        }
        offsetChildrenSecondary(-dy);
        this.mScrollOffsetSecondary += dy;
        updateSecondaryScrollLimits();
        this.mBaseGridView.invalidate();
        return dy;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void collectAdjacentPrefetchPositions(int dx, int dy, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int fromLimit;
        try {
            saveContext(null, state);
            int da = this.mOrientation == 0 ? dx : dy;
            if (getChildCount() != 0 && da != 0) {
                if (da < 0) {
                    fromLimit = -this.mExtraLayoutSpace;
                } else {
                    fromLimit = this.mSizePrimary + this.mExtraLayoutSpace;
                }
                this.mGrid.collectAdjacentPrefetchPositions(fromLimit, da, layoutPrefetchRegistry);
            }
        } finally {
            leaveContext();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void collectInitialPrefetchPositions(int adapterItemCount, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int numToPrefetch = this.mBaseGridView.mInitialPrefetchItemCount;
        if (adapterItemCount != 0 && numToPrefetch != 0) {
            int initialPos = Math.max(0, Math.min(this.mFocusPosition - ((numToPrefetch - 1) / 2), adapterItemCount - numToPrefetch));
            for (int i = initialPos; i < adapterItemCount && i < initialPos + numToPrefetch; i++) {
                layoutPrefetchRegistry.addPosition(i, 0);
            }
        }
    }

    void updateScrollLimits() {
        int highVisiblePos;
        int highMaxPos;
        int lowVisiblePos;
        int lowMinPos;
        int maxEdge;
        int maxViewCenter;
        int minEdge;
        int minViewCenter;
        if (this.mState.getItemCount() == 0) {
            return;
        }
        if ((this.mFlag & 262144) == 0) {
            highVisiblePos = this.mGrid.getLastVisibleIndex();
            highMaxPos = this.mState.getItemCount() - 1;
            lowVisiblePos = this.mGrid.getFirstVisibleIndex();
            lowMinPos = 0;
        } else {
            highVisiblePos = this.mGrid.getFirstVisibleIndex();
            highMaxPos = 0;
            lowVisiblePos = this.mGrid.getLastVisibleIndex();
            lowMinPos = this.mState.getItemCount() - 1;
        }
        if (highVisiblePos < 0 || lowVisiblePos < 0) {
            return;
        }
        boolean highAvailable = highVisiblePos == highMaxPos;
        boolean lowAvailable = lowVisiblePos == lowMinPos;
        if (!highAvailable && this.mWindowAlignment.mainAxis().isMaxUnknown() && !lowAvailable && this.mWindowAlignment.mainAxis().isMinUnknown()) {
            return;
        }
        if (highAvailable) {
            maxEdge = this.mGrid.findRowMax(true, sTwoInts);
            View maxChild = findViewByPosition(sTwoInts[1]);
            maxViewCenter = getViewCenter(maxChild);
            LayoutParams lp = (LayoutParams) maxChild.getLayoutParams();
            int[] multipleAligns = lp.getAlignMultiple();
            if (multipleAligns != null && multipleAligns.length > 0) {
                maxViewCenter += multipleAligns[multipleAligns.length - 1] - multipleAligns[0];
            }
        } else {
            maxEdge = Integer.MAX_VALUE;
            maxViewCenter = Integer.MAX_VALUE;
        }
        if (lowAvailable) {
            minEdge = this.mGrid.findRowMin(false, sTwoInts);
            View minChild = findViewByPosition(sTwoInts[1]);
            minViewCenter = getViewCenter(minChild);
        } else {
            minEdge = Integer.MIN_VALUE;
            minViewCenter = Integer.MIN_VALUE;
        }
        this.mWindowAlignment.mainAxis().updateMinMax(minEdge, maxEdge, minViewCenter, maxViewCenter);
    }

    private void updateSecondaryScrollLimits() {
        WindowAlignment.Axis secondAxis = this.mWindowAlignment.secondAxis();
        int minEdge = secondAxis.getPaddingMin() - this.mScrollOffsetSecondary;
        int maxEdge = getSizeSecondary() + minEdge;
        secondAxis.updateMinMax(minEdge, maxEdge, minEdge, maxEdge);
    }

    private void initScrollController() {
        this.mWindowAlignment.reset();
        this.mWindowAlignment.horizontal.setSize(getWidth());
        this.mWindowAlignment.vertical.setSize(getHeight());
        this.mWindowAlignment.horizontal.setPadding(getPaddingLeft(), getPaddingRight());
        this.mWindowAlignment.vertical.setPadding(getPaddingTop(), getPaddingBottom());
        this.mSizePrimary = this.mWindowAlignment.mainAxis().getSize();
        this.mScrollOffsetSecondary = 0;
    }

    private void updateScrollController() {
        this.mWindowAlignment.horizontal.setSize(getWidth());
        this.mWindowAlignment.vertical.setSize(getHeight());
        this.mWindowAlignment.horizontal.setPadding(getPaddingLeft(), getPaddingRight());
        this.mWindowAlignment.vertical.setPadding(getPaddingTop(), getPaddingBottom());
        this.mSizePrimary = this.mWindowAlignment.mainAxis().getSize();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int position) {
        setSelection(position, 0, false, 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        setSelection(position, 0, true, 0);
    }

    public void setSelection(int position, int primaryScrollExtra) {
        setSelection(position, 0, false, primaryScrollExtra);
    }

    public void setSelectionSmooth(int position) {
        setSelection(position, 0, true, 0);
    }

    public void setSelectionWithSub(int position, int subposition, int primaryScrollExtra) {
        setSelection(position, subposition, false, primaryScrollExtra);
    }

    public void setSelectionSmoothWithSub(int position, int subposition) {
        setSelection(position, subposition, true, 0);
    }

    public int getSelection() {
        return this.mFocusPosition;
    }

    public int getSubSelection() {
        return this.mSubFocusPosition;
    }

    public void setSelection(int position, int subposition, boolean smooth, int primaryScrollExtra) {
        if ((this.mFocusPosition != position && position != -1) || subposition != this.mSubFocusPosition || primaryScrollExtra != this.mPrimaryScrollExtra) {
            scrollToSelection(position, subposition, smooth, primaryScrollExtra);
        }
    }

    void scrollToSelection(int position, int subposition, boolean smooth, int primaryScrollExtra) {
        this.mPrimaryScrollExtra = primaryScrollExtra;
        View view = findViewByPosition(position);
        boolean notSmoothScrolling = !isSmoothScrolling();
        if (notSmoothScrolling && !this.mBaseGridView.isLayoutRequested() && view != null && getAdapterPositionByView(view) == position) {
            this.mFlag |= 32;
            scrollToView(view, smooth);
            this.mFlag &= -33;
            return;
        }
        int i = this.mFlag;
        if ((i & 512) == 0 || (i & 64) != 0) {
            this.mFocusPosition = position;
            this.mSubFocusPosition = subposition;
            this.mFocusPositionOffset = Integer.MIN_VALUE;
            return;
        }
        if (smooth && !this.mBaseGridView.isLayoutRequested()) {
            this.mFocusPosition = position;
            this.mSubFocusPosition = subposition;
            this.mFocusPositionOffset = Integer.MIN_VALUE;
            if (!hasDoneFirstLayout()) {
                Log.w(getTag(), "setSelectionSmooth should not be called before first layout pass");
                return;
            }
            int position2 = startPositionSmoothScroller(position);
            if (position2 != this.mFocusPosition) {
                this.mFocusPosition = position2;
                this.mSubFocusPosition = 0;
                return;
            }
            return;
        }
        if (!notSmoothScrolling) {
            skipSmoothScrollerOnStopInternal();
            this.mBaseGridView.stopScroll();
        }
        if (!this.mBaseGridView.isLayoutRequested() && view != null && getAdapterPositionByView(view) == position) {
            this.mFlag |= 32;
            scrollToView(view, smooth);
            this.mFlag &= -33;
        } else {
            this.mFocusPosition = position;
            this.mSubFocusPosition = subposition;
            this.mFocusPositionOffset = Integer.MIN_VALUE;
            this.mFlag |= 256;
            requestLayout();
        }
    }

    int startPositionSmoothScroller(int position) {
        LinearSmoothScroller linearSmoothScroller = new GridLinearSmoothScroller() { // from class: androidx.leanback.widget.GridLayoutManager.4
            @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller
            public PointF computeScrollVectorForPosition(int targetPosition) {
                if (getChildCount() == 0) {
                    return null;
                }
                GridLayoutManager gridLayoutManager = GridLayoutManager.this;
                boolean isStart = false;
                int firstChildPos = gridLayoutManager.getPosition(gridLayoutManager.getChildAt(0));
                if ((GridLayoutManager.this.mFlag & 262144) != 0) {
                    if (targetPosition > firstChildPos) {
                        isStart = true;
                    }
                } else if (targetPosition < firstChildPos) {
                    isStart = true;
                }
                int direction = isStart ? -1 : 1;
                if (GridLayoutManager.this.mOrientation == 0) {
                    return new PointF(direction, 0.0f);
                }
                return new PointF(0.0f, direction);
            }
        };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
        return linearSmoothScroller.getTargetPosition();
    }

    void skipSmoothScrollerOnStopInternal() {
        GridLinearSmoothScroller gridLinearSmoothScroller = this.mCurrentSmoothScroller;
        if (gridLinearSmoothScroller != null) {
            gridLinearSmoothScroller.mSkipOnStopInternal = true;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void startSmoothScroll(RecyclerView.SmoothScroller smoothScroller) {
        skipSmoothScrollerOnStopInternal();
        super.startSmoothScroll(smoothScroller);
        if (smoothScroller.isRunning() && (smoothScroller instanceof GridLinearSmoothScroller)) {
            GridLinearSmoothScroller gridLinearSmoothScroller = (GridLinearSmoothScroller) smoothScroller;
            this.mCurrentSmoothScroller = gridLinearSmoothScroller;
            if (gridLinearSmoothScroller instanceof PendingMoveSmoothScroller) {
                this.mPendingMoveSmoothScroller = (PendingMoveSmoothScroller) gridLinearSmoothScroller;
                return;
            } else {
                this.mPendingMoveSmoothScroller = null;
                return;
            }
        }
        this.mCurrentSmoothScroller = null;
        this.mPendingMoveSmoothScroller = null;
    }

    void processPendingMovement(boolean forward) {
        if (forward) {
            if (hasCreatedLastItem()) {
                return;
            }
        } else if (hasCreatedFirstItem()) {
            return;
        }
        PendingMoveSmoothScroller pendingMoveSmoothScroller = this.mPendingMoveSmoothScroller;
        if (pendingMoveSmoothScroller == null) {
            PendingMoveSmoothScroller linearSmoothScroller = new PendingMoveSmoothScroller(forward ? 1 : -1, this.mNumRows > 1);
            this.mFocusPositionOffset = 0;
            startSmoothScroll(linearSmoothScroller);
        } else if (forward) {
            pendingMoveSmoothScroller.increasePendingMoves();
        } else {
            pendingMoveSmoothScroller.decreasePendingMoves();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int positionStart, int itemCount) {
        Grid grid;
        int i;
        if (this.mFocusPosition != -1 && (grid = this.mGrid) != null && grid.getFirstVisibleIndex() >= 0 && (i = this.mFocusPositionOffset) != Integer.MIN_VALUE) {
            int pos = this.mFocusPosition + i;
            if (positionStart <= pos) {
                this.mFocusPositionOffset = i + itemCount;
            }
        }
        this.mChildrenStates.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView recyclerView) {
        this.mFocusPositionOffset = 0;
        this.mChildrenStates.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int positionStart, int itemCount) {
        Grid grid;
        int i;
        int i2;
        int pos;
        if (this.mFocusPosition != -1 && (grid = this.mGrid) != null && grid.getFirstVisibleIndex() >= 0 && (i = this.mFocusPositionOffset) != Integer.MIN_VALUE && positionStart <= (pos = (i2 = this.mFocusPosition) + i)) {
            if (positionStart + itemCount > pos) {
                int i3 = i + (positionStart - pos);
                this.mFocusPositionOffset = i3;
                this.mFocusPosition = i2 + i3;
                this.mFocusPositionOffset = Integer.MIN_VALUE;
            } else {
                this.mFocusPositionOffset = i - itemCount;
            }
        }
        this.mChildrenStates.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView recyclerView, int fromPosition, int toPosition, int itemCount) {
        int i;
        int i2 = this.mFocusPosition;
        if (i2 != -1 && (i = this.mFocusPositionOffset) != Integer.MIN_VALUE) {
            int pos = i2 + i;
            if (fromPosition <= pos && pos < fromPosition + itemCount) {
                this.mFocusPositionOffset = i + (toPosition - fromPosition);
            } else if (fromPosition < pos && toPosition > pos - itemCount) {
                this.mFocusPositionOffset -= itemCount;
            } else if (fromPosition > pos && toPosition < pos) {
                this.mFocusPositionOffset += itemCount;
            }
        }
        this.mChildrenStates.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int positionStart, int itemCount) {
        int end = positionStart + itemCount;
        for (int i = positionStart; i < end; i++) {
            this.mChildrenStates.remove(i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean onRequestChildFocus(RecyclerView parent, View child, View focused) {
        if ((this.mFlag & 32768) == 0 && getAdapterPositionByView(child) != -1 && (this.mFlag & 35) == 0) {
            scrollToView(child, focused, true);
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean requestChildRectangleOnScreen(RecyclerView parent, View view, Rect rect, boolean immediate) {
        return false;
    }

    public void getViewSelectedOffsets(View view, int[] offsets) {
        if (this.mOrientation == 0) {
            offsets[0] = getPrimaryAlignedScrollDistance(view);
            offsets[1] = getSecondaryScrollDistance(view);
        } else {
            offsets[1] = getPrimaryAlignedScrollDistance(view);
            offsets[0] = getSecondaryScrollDistance(view);
        }
    }

    private int getPrimaryAlignedScrollDistance(View view) {
        return this.mWindowAlignment.mainAxis().getScroll(getViewCenter(view));
    }

    private int getAdjustedPrimaryAlignedScrollDistance(int scrollPrimary, View view, View childView) {
        int subindex = getSubPositionByView(view, childView);
        if (subindex != 0) {
            LayoutParams lp = (LayoutParams) view.getLayoutParams();
            return scrollPrimary + (lp.getAlignMultiple()[subindex] - lp.getAlignMultiple()[0]);
        }
        return scrollPrimary;
    }

    private int getSecondaryScrollDistance(View view) {
        int viewCenterSecondary = getViewCenterSecondary(view);
        return this.mWindowAlignment.secondAxis().getScroll(viewCenterSecondary);
    }

    void scrollToView(View view, boolean smooth) {
        scrollToView(view, view == null ? null : view.findFocus(), smooth);
    }

    void scrollToView(View view, boolean smooth, int extraDelta, int extraDeltaSecondary) {
        scrollToView(view, view == null ? null : view.findFocus(), smooth, extraDelta, extraDeltaSecondary);
    }

    private void scrollToView(View view, View childView, boolean smooth) {
        scrollToView(view, childView, smooth, 0, 0);
    }

    private void scrollToView(View view, View childView, boolean smooth, int extraDelta, int extraDeltaSecondary) {
        if ((this.mFlag & 64) != 0) {
            return;
        }
        int newFocusPosition = getAdapterPositionByView(view);
        int newSubFocusPosition = getSubPositionByView(view, childView);
        if (newFocusPosition != this.mFocusPosition || newSubFocusPosition != this.mSubFocusPosition) {
            this.mFocusPosition = newFocusPosition;
            this.mSubFocusPosition = newSubFocusPosition;
            this.mFocusPositionOffset = 0;
            if ((this.mFlag & 3) != 1) {
                dispatchChildSelected();
            }
            if (this.mBaseGridView.isChildrenDrawingOrderEnabledInternal()) {
                this.mBaseGridView.invalidate();
            }
        }
        if (view == null) {
            return;
        }
        if (!view.hasFocus() && this.mBaseGridView.hasFocus()) {
            view.requestFocus();
        }
        if ((this.mFlag & 131072) == 0 && smooth) {
            return;
        }
        if (getScrollPosition(view, childView, sTwoInts) || extraDelta != 0 || extraDeltaSecondary != 0) {
            int[] iArr = sTwoInts;
            scrollGrid(iArr[0] + extraDelta, iArr[1] + extraDeltaSecondary, smooth);
        }
    }

    boolean getScrollPosition(View view, View childView, int[] deltas) {
        int i = this.mFocusScrollStrategy;
        if (i != 1 && i != 2) {
            return getAlignedPosition(view, childView, deltas);
        }
        return getNoneAlignedPosition(view, deltas);
    }

    private boolean getNoneAlignedPosition(View view, int[] deltas) {
        View secondaryAlignedView;
        int pos = getAdapterPositionByView(view);
        int viewMin = getViewMin(view);
        int viewMax = getViewMax(view);
        View firstView = null;
        View lastView = null;
        int paddingMin = this.mWindowAlignment.mainAxis().getPaddingMin();
        int clientSize = this.mWindowAlignment.mainAxis().getClientSize();
        int row = this.mGrid.getRowIndex(pos);
        if (viewMin < paddingMin) {
            firstView = view;
            if (this.mFocusScrollStrategy == 2) {
                while (true) {
                    if (!prependOneColumnVisibleItems()) {
                        break;
                    }
                    Grid grid = this.mGrid;
                    CircularIntArray positions = grid.getItemPositionsInRows(grid.getFirstVisibleIndex(), pos)[row];
                    firstView = findViewByPosition(positions.get(0));
                    if (viewMax - getViewMin(firstView) > clientSize) {
                        if (positions.size() > 2) {
                            firstView = findViewByPosition(positions.get(2));
                        }
                    }
                }
            }
        } else if (viewMax > clientSize + paddingMin) {
            if (this.mFocusScrollStrategy == 2) {
                while (true) {
                    Grid grid2 = this.mGrid;
                    CircularIntArray positions2 = grid2.getItemPositionsInRows(pos, grid2.getLastVisibleIndex())[row];
                    lastView = findViewByPosition(positions2.get(positions2.size() - 1));
                    if (getViewMax(lastView) - viewMin > clientSize) {
                        lastView = null;
                        break;
                    }
                    if (!appendOneColumnVisibleItems()) {
                        break;
                    }
                }
                firstView = lastView != null ? null : view;
            } else {
                lastView = view;
            }
        }
        int scrollPrimary = 0;
        if (firstView != null) {
            scrollPrimary = getViewMin(firstView) - paddingMin;
        } else if (lastView != null) {
            scrollPrimary = getViewMax(lastView) - (paddingMin + clientSize);
        }
        if (firstView != null) {
            secondaryAlignedView = firstView;
        } else if (lastView != null) {
            secondaryAlignedView = lastView;
        } else {
            secondaryAlignedView = view;
        }
        int scrollSecondary = getSecondaryScrollDistance(secondaryAlignedView);
        if (scrollPrimary == 0 && scrollSecondary == 0) {
            return false;
        }
        deltas[0] = scrollPrimary;
        deltas[1] = scrollSecondary;
        return true;
    }

    private boolean getAlignedPosition(View view, View childView, int[] deltas) {
        int scrollPrimary = getPrimaryAlignedScrollDistance(view);
        if (childView != null) {
            scrollPrimary = getAdjustedPrimaryAlignedScrollDistance(scrollPrimary, view, childView);
        }
        int scrollSecondary = getSecondaryScrollDistance(view);
        int scrollPrimary2 = scrollPrimary + this.mPrimaryScrollExtra;
        if (scrollPrimary2 != 0 || scrollSecondary != 0) {
            deltas[0] = scrollPrimary2;
            deltas[1] = scrollSecondary;
            return true;
        }
        deltas[0] = 0;
        deltas[1] = 0;
        return false;
    }

    private void scrollGrid(int scrollPrimary, int scrollSecondary, boolean smooth) {
        int scrollX;
        int scrollY;
        if ((this.mFlag & 3) == 1) {
            scrollDirectionPrimary(scrollPrimary);
            scrollDirectionSecondary(scrollSecondary);
            return;
        }
        if (this.mOrientation == 0) {
            scrollX = scrollPrimary;
            scrollY = scrollSecondary;
        } else {
            scrollX = scrollSecondary;
            scrollY = scrollPrimary;
        }
        if (smooth) {
            this.mBaseGridView.smoothScrollBy(scrollX, scrollY);
        } else {
            this.mBaseGridView.scrollBy(scrollX, scrollY);
            dispatchChildSelectedAndPositioned();
        }
    }

    public void setPruneChild(boolean pruneChild) {
        if (((this.mFlag & 65536) != 0) != pruneChild) {
            this.mFlag = (this.mFlag & (-65537)) | (pruneChild ? 65536 : 0);
            if (pruneChild) {
                requestLayout();
            }
        }
    }

    public boolean getPruneChild() {
        return (this.mFlag & 65536) != 0;
    }

    public void setScrollEnabled(boolean scrollEnabled) {
        int i;
        if (((this.mFlag & 131072) != 0) != scrollEnabled) {
            int i2 = (this.mFlag & (-131073)) | (scrollEnabled ? 131072 : 0);
            this.mFlag = i2;
            if ((i2 & 131072) != 0 && this.mFocusScrollStrategy == 0 && (i = this.mFocusPosition) != -1) {
                scrollToSelection(i, this.mSubFocusPosition, true, this.mPrimaryScrollExtra);
            }
        }
    }

    public boolean isScrollEnabled() {
        return (this.mFlag & 131072) != 0;
    }

    private int findImmediateChildIndex(View view) {
        View view2;
        BaseGridView baseGridView = this.mBaseGridView;
        if (baseGridView != null && view != baseGridView && (view2 = findContainingItemView(view)) != null) {
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                if (getChildAt(i) == view2) {
                    return i;
                }
            }
            return -1;
        }
        return -1;
    }

    void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        if (gainFocus) {
            int i = this.mFocusPosition;
            while (true) {
                View view = findViewByPosition(i);
                if (view != null) {
                    if (view.getVisibility() != 0 || !view.hasFocusable()) {
                        i++;
                    } else {
                        view.requestFocus();
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    void setFocusSearchDisabled(boolean disabled) {
        this.mFlag = (this.mFlag & (-32769)) | (disabled ? 32768 : 0);
    }

    boolean isFocusSearchDisabled() {
        return (this.mFlag & 32768) != 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public View onInterceptFocusSearch(View focused, int direction) {
        if ((this.mFlag & 32768) != 0) {
            return focused;
        }
        FocusFinder ff = FocusFinder.getInstance();
        View result = null;
        if (direction == 2 || direction == 1) {
            if (canScrollVertically()) {
                int absDir = direction == 2 ? 130 : 33;
                result = ff.findNextFocus(this.mBaseGridView, focused, absDir);
            }
            if (canScrollHorizontally()) {
                boolean rtl = getLayoutDirection() == 1;
                int absDir2 = (direction == 2) ^ rtl ? 66 : 17;
                result = ff.findNextFocus(this.mBaseGridView, focused, absDir2);
            }
        } else {
            result = ff.findNextFocus(this.mBaseGridView, focused, direction);
        }
        if (result != null) {
            return result;
        }
        if (this.mBaseGridView.getDescendantFocusability() == 393216) {
            return this.mBaseGridView.getParent().focusSearch(focused, direction);
        }
        int movement = getMovement(direction);
        boolean isScroll = this.mBaseGridView.getScrollState() != 0;
        if (movement == 1) {
            if (isScroll || (this.mFlag & 4096) == 0) {
                result = focused;
            }
            if ((this.mFlag & 131072) != 0 && !hasCreatedLastItem()) {
                processPendingMovement(true);
                result = focused;
            }
        } else if (movement == 0) {
            if (isScroll || (this.mFlag & 2048) == 0) {
                result = focused;
            }
            if ((this.mFlag & 131072) != 0 && !hasCreatedFirstItem()) {
                processPendingMovement(false);
                result = focused;
            }
        } else if (movement == 3) {
            if (isScroll || (this.mFlag & 16384) == 0) {
                result = focused;
            }
        } else if (movement == 2 && (isScroll || (this.mFlag & 8192) == 0)) {
            result = focused;
        }
        if (result != null) {
            return result;
        }
        View result2 = this.mBaseGridView.getParent().focusSearch(focused, direction);
        if (result2 != null) {
            return result2;
        }
        return focused != null ? focused : this.mBaseGridView;
    }

    boolean hasPreviousViewInSameRow(int pos) {
        Grid grid = this.mGrid;
        if (grid == null || pos == -1 || grid.getFirstVisibleIndex() < 0) {
            return false;
        }
        if (this.mGrid.getFirstVisibleIndex() > 0) {
            return true;
        }
        int focusedRow = this.mGrid.getLocation(pos).row;
        for (int i = getChildCount() - 1; i >= 0; i--) {
            int position = getAdapterPositionByIndex(i);
            Grid.Location loc = this.mGrid.getLocation(position);
            if (loc != null && loc.row == focusedRow && position < pos) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x00a3 A[PHI: r12
  0x00a3: PHI (r12v2 'loop_end' int) = (r12v1 'loop_end' int), (r12v4 'loop_end' int) binds: [B:56:0x00a1, B:53:0x009c] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onAddFocusables(androidx.recyclerview.widget.RecyclerView r23, java.util.ArrayList<android.view.View> r24, int r25, int r26) {
        /*
            Method dump skipped, instruction units count: 465
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.GridLayoutManager.onAddFocusables(androidx.recyclerview.widget.RecyclerView, java.util.ArrayList, int, int):boolean");
    }

    boolean hasCreatedLastItem() {
        int count = getItemCount();
        return count == 0 || this.mBaseGridView.findViewHolderForAdapterPosition(count + (-1)) != null;
    }

    boolean hasCreatedFirstItem() {
        int count = getItemCount();
        return count == 0 || this.mBaseGridView.findViewHolderForAdapterPosition(0) != null;
    }

    boolean isItemFullyVisible(int pos) {
        RecyclerView.ViewHolder vh = this.mBaseGridView.findViewHolderForAdapterPosition(pos);
        return vh != null && vh.itemView.getLeft() >= 0 && vh.itemView.getRight() <= this.mBaseGridView.getWidth() && vh.itemView.getTop() >= 0 && vh.itemView.getBottom() <= this.mBaseGridView.getHeight();
    }

    boolean canScrollTo(View view) {
        return view.getVisibility() == 0 && (!hasFocus() || view.hasFocusable());
    }

    boolean gridOnRequestFocusInDescendants(RecyclerView recyclerView, int direction, Rect previouslyFocusedRect) {
        int i = this.mFocusScrollStrategy;
        if (i != 1 && i != 2) {
            return gridOnRequestFocusInDescendantsAligned(direction, previouslyFocusedRect);
        }
        return gridOnRequestFocusInDescendantsUnaligned(direction, previouslyFocusedRect);
    }

    private boolean gridOnRequestFocusInDescendantsAligned(int direction, Rect previouslyFocusedRect) {
        View view = findViewByPosition(this.mFocusPosition);
        if (view != null) {
            boolean result = view.requestFocus(direction, previouslyFocusedRect);
            return result;
        }
        return false;
    }

    private boolean gridOnRequestFocusInDescendantsUnaligned(int direction, Rect previouslyFocusedRect) {
        int index;
        int increment;
        int end;
        int count = getChildCount();
        if ((direction & 2) != 0) {
            index = 0;
            increment = 1;
            end = count;
        } else {
            index = count - 1;
            increment = -1;
            end = -1;
        }
        int left = this.mWindowAlignment.mainAxis().getPaddingMin();
        int right = this.mWindowAlignment.mainAxis().getClientSize() + left;
        for (int i = index; i != end; i += increment) {
            View child = getChildAt(i);
            if (child.getVisibility() == 0 && getViewMin(child) >= left && getViewMax(child) <= right && child.requestFocus(direction, previouslyFocusedRect)) {
                return true;
            }
        }
        return false;
    }

    private int getMovement(int direction) {
        int i = this.mOrientation;
        if (i == 0) {
            if (direction == 17) {
                int movement = (this.mFlag & 262144) == 0 ? 0 : 1;
                return movement;
            }
            if (direction == 33) {
                return 2;
            }
            if (direction == 66) {
                int movement2 = (this.mFlag & 262144) != 0 ? 0 : 1;
                return movement2;
            }
            if (direction != 130) {
                return 17;
            }
            return 3;
        }
        if (i != 1) {
            return 17;
        }
        if (direction == 17) {
            int movement3 = (this.mFlag & 524288) != 0 ? 3 : 2;
            return movement3;
        }
        if (direction == 33) {
            return 0;
        }
        if (direction == 66) {
            int movement4 = (this.mFlag & 524288) == 0 ? 3 : 2;
            return movement4;
        }
        if (direction != 130) {
            return 17;
        }
        return 1;
    }

    int getChildDrawingOrder(RecyclerView recyclerView, int childCount, int i) {
        int focusIndex;
        View view = findViewByPosition(this.mFocusPosition);
        if (view == null || i < (focusIndex = recyclerView.indexOfChild(view))) {
            return i;
        }
        if (i < childCount - 1) {
            return ((focusIndex + childCount) - 1) - i;
        }
        return focusIndex;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter oldAdapter, RecyclerView.Adapter adapter) {
        if (oldAdapter != null) {
            discardLayoutInfo();
            this.mFocusPosition = -1;
            this.mFocusPositionOffset = 0;
            this.mChildrenStates.clear();
        }
        if (adapter instanceof FacetProviderAdapter) {
            this.mFacetProviderAdapter = (FacetProviderAdapter) adapter;
        } else {
            this.mFacetProviderAdapter = null;
        }
        super.onAdapterChanged(oldAdapter, adapter);
    }

    private void discardLayoutInfo() {
        this.mGrid = null;
        this.mRowSizeSecondary = null;
        this.mFlag &= -1025;
    }

    public void setLayoutEnabled(boolean layoutEnabled) {
        if (((this.mFlag & 512) != 0) != layoutEnabled) {
            this.mFlag = (this.mFlag & (-513)) | (layoutEnabled ? 512 : 0);
            requestLayout();
        }
    }

    void setChildrenVisibility(int visibility) {
        this.mChildVisibility = visibility;
        if (visibility != -1) {
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                getChildAt(i).setVisibility(this.mChildVisibility);
            }
        }
    }

    static final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: androidx.leanback.widget.GridLayoutManager.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        Bundle childStates;
        int index;

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(this.index);
            out.writeBundle(this.childStates);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        SavedState(Parcel in) {
            this.childStates = Bundle.EMPTY;
            this.index = in.readInt();
            this.childStates = in.readBundle(GridLayoutManager.class.getClassLoader());
        }

        SavedState() {
            this.childStates = Bundle.EMPTY;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState();
        ss.index = getSelection();
        Bundle bundle = this.mChildrenStates.saveAsBundle();
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            int position = getAdapterPositionByView(view);
            if (position != -1) {
                bundle = this.mChildrenStates.saveOnScreenView(bundle, view, position);
            }
        }
        ss.childStates = bundle;
        return ss;
    }

    void onChildRecycled(RecyclerView.ViewHolder holder) {
        int position = holder.getAbsoluteAdapterPosition();
        if (position != -1) {
            this.mChildrenStates.saveOffscreenView(holder.itemView, position);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof SavedState)) {
            return;
        }
        SavedState loadingState = (SavedState) state;
        this.mFocusPosition = loadingState.index;
        this.mFocusPositionOffset = 0;
        this.mChildrenStates.loadFromBundle(loadingState.childStates);
        this.mFlag |= 256;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Grid grid;
        if (this.mOrientation == 0 && (grid = this.mGrid) != null) {
            return grid.getNumRows();
        }
        return super.getRowCountForAccessibility(recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Grid grid;
        if (this.mOrientation == 1 && (grid = this.mGrid) != null) {
            return grid.getNumRows();
        }
        return super.getColumnCountForAccessibility(recycler, state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View host, AccessibilityNodeInfoCompat info) {
        ViewGroup.LayoutParams lp = host.getLayoutParams();
        if (this.mGrid == null || !(lp instanceof LayoutParams)) {
            return;
        }
        LayoutParams glp = (LayoutParams) lp;
        int position = glp.getViewAdapterPosition();
        int rowIndex = position >= 0 ? this.mGrid.getRowIndex(position) : -1;
        if (rowIndex < 0) {
            return;
        }
        int guessSpanIndex = position / this.mGrid.getNumRows();
        if (this.mOrientation == 0) {
            info.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(rowIndex, 1, guessSpanIndex, 1, false, false));
        } else {
            info.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(guessSpanIndex, 1, rowIndex, 1, false, false));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean performAccessibilityAction(RecyclerView.Recycler recycler, RecyclerView.State state, int action, Bundle args) {
        int i;
        int i2;
        if (!isScrollEnabled()) {
            return true;
        }
        saveContext(recycler, state);
        int translatedAction = action;
        boolean reverseFlowPrimary = (this.mFlag & 262144) != 0;
        if (Build.VERSION.SDK_INT >= 23) {
            if (this.mOrientation == 0) {
                if (action == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT.getId()) {
                    if (reverseFlowPrimary) {
                        i2 = 4096;
                    } else {
                        i2 = 8192;
                    }
                    translatedAction = i2;
                } else if (action == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT.getId()) {
                    if (reverseFlowPrimary) {
                        i = 8192;
                    } else {
                        i = 4096;
                    }
                    translatedAction = i;
                }
            } else if (action == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_UP.getId()) {
                translatedAction = 8192;
            } else if (action == AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_DOWN.getId()) {
                translatedAction = 4096;
            }
        }
        if (translatedAction == 4096) {
            processPendingMovement(true);
            processSelectionMoves(false, 1);
        } else if (translatedAction == 8192) {
            processPendingMovement(false);
            processSelectionMoves(false, -1);
        }
        leaveContext();
        return true;
    }

    int processSelectionMoves(boolean preventScroll, int moves) {
        int focusedRow;
        Grid grid = this.mGrid;
        if (grid == null) {
            return moves;
        }
        int focusPosition = this.mFocusPosition;
        if (focusPosition == -1) {
            focusedRow = -1;
        } else {
            focusedRow = grid.getRowIndex(focusPosition);
        }
        View newSelected = null;
        int count = getChildCount();
        for (int i = 0; i < count && moves != 0; i++) {
            int index = moves > 0 ? i : (count - 1) - i;
            View child = getChildAt(index);
            if (canScrollTo(child)) {
                int position = getAdapterPositionByIndex(index);
                int rowIndex = this.mGrid.getRowIndex(position);
                if (focusedRow == -1) {
                    focusPosition = position;
                    newSelected = child;
                    focusedRow = rowIndex;
                } else if (rowIndex == focusedRow && ((moves > 0 && position > focusPosition) || (moves < 0 && position < focusPosition))) {
                    focusPosition = position;
                    newSelected = child;
                    moves = moves > 0 ? moves - 1 : moves + 1;
                }
            }
        }
        if (newSelected != null) {
            if (preventScroll) {
                if (hasFocus()) {
                    this.mFlag |= 32;
                    newSelected.requestFocus();
                    this.mFlag &= -33;
                }
                this.mFocusPosition = focusPosition;
                this.mSubFocusPosition = 0;
            } else {
                scrollToView(newSelected, true);
            }
        }
        return moves;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityNodeInfo(RecyclerView.Recycler recycler, RecyclerView.State state, AccessibilityNodeInfoCompat info) {
        AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat;
        AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat2;
        saveContext(recycler, state);
        int count = state.getItemCount();
        boolean reverseFlowPrimary = (this.mFlag & 262144) != 0;
        if (count > 1 && !isItemFullyVisible(0)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (this.mOrientation == 0) {
                    if (reverseFlowPrimary) {
                        accessibilityActionCompat2 = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT;
                    } else {
                        accessibilityActionCompat2 = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT;
                    }
                    info.addAction(accessibilityActionCompat2);
                } else {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_UP);
                }
            } else {
                info.addAction(8192);
            }
            info.setScrollable(true);
        }
        if (count > 1 && !isItemFullyVisible(count - 1)) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (this.mOrientation == 0) {
                    if (reverseFlowPrimary) {
                        accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT;
                    } else {
                        accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT;
                    }
                    info.addAction(accessibilityActionCompat);
                } else {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_DOWN);
                }
            } else {
                info.addAction(4096);
            }
            info.setScrollable(true);
        }
        AccessibilityNodeInfoCompat.CollectionInfoCompat collectionInfo = AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(getRowCountForAccessibility(recycler, state), getColumnCountForAccessibility(recycler, state), isLayoutHierarchical(recycler, state), getSelectionModeForAccessibility(recycler, state));
        info.setCollectionInfo(collectionInfo);
        leaveContext();
    }
}
