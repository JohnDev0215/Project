package com.google.android.flexbox;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.flexbox.FlexboxHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public class FlexboxLayoutManager extends RecyclerView.LayoutManager implements FlexContainer, RecyclerView.SmoothScroller.ScrollVectorProvider {
    private static final Rect TEMP_RECT = new Rect();
    private int mAlignItems;
    private AnchorInfo mAnchorInfo;
    private final Context mContext;
    private int mDirtyPosition;
    private int mFlexDirection;
    private List<FlexLine> mFlexLines;
    private FlexboxHelper.FlexLinesResult mFlexLinesResult;
    private int mFlexWrap;
    private final FlexboxHelper mFlexboxHelper;
    private boolean mFromBottomToTop;
    private boolean mIsRtl;
    private int mJustifyContent;
    private int mLastHeight;
    private int mLastWidth;
    private LayoutState mLayoutState;
    private int mMaxLine;
    private OrientationHelper mOrientationHelper;
    private View mParent;
    private SavedState mPendingSavedState;
    private int mPendingScrollPosition;
    private int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private RecyclerView.Recycler mRecycler;
    private RecyclerView.State mState;
    private OrientationHelper mSubOrientationHelper;
    private SparseArray<View> mViewCache;

    @Override // com.google.android.flexbox.FlexContainer
    public int getAlignContent() {
        return 5;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void onNewFlexLineAdded(FlexLine flexLine) {
    }

    public FlexboxLayoutManager(Context context) {
        this(context, 0, 1);
    }

    public FlexboxLayoutManager(Context context, int i, int i2) {
        this.mMaxLine = -1;
        this.mFlexLines = new ArrayList();
        this.mFlexboxHelper = new FlexboxHelper(this);
        this.mAnchorInfo = new AnchorInfo();
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mLastWidth = Integer.MIN_VALUE;
        this.mLastHeight = Integer.MIN_VALUE;
        this.mViewCache = new SparseArray<>();
        this.mDirtyPosition = -1;
        this.mFlexLinesResult = new FlexboxHelper.FlexLinesResult();
        setFlexDirection(i);
        setFlexWrap(i2);
        setAlignItems(4);
        setAutoMeasureEnabled(true);
        this.mContext = context;
    }

    public FlexboxLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mMaxLine = -1;
        this.mFlexLines = new ArrayList();
        this.mFlexboxHelper = new FlexboxHelper(this);
        this.mAnchorInfo = new AnchorInfo();
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mLastWidth = Integer.MIN_VALUE;
        this.mLastHeight = Integer.MIN_VALUE;
        this.mViewCache = new SparseArray<>();
        this.mDirtyPosition = -1;
        this.mFlexLinesResult = new FlexboxHelper.FlexLinesResult();
        RecyclerView.LayoutManager.Properties properties = RecyclerView.LayoutManager.getProperties(context, attributeSet, i, i2);
        int i3 = properties.orientation;
        if (i3 != 0) {
            if (i3 == 1) {
                if (properties.reverseLayout) {
                    setFlexDirection(3);
                } else {
                    setFlexDirection(2);
                }
            }
        } else if (properties.reverseLayout) {
            setFlexDirection(1);
        } else {
            setFlexDirection(0);
        }
        setFlexWrap(1);
        setAlignItems(4);
        setAutoMeasureEnabled(true);
        this.mContext = context;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getFlexDirection() {
        return this.mFlexDirection;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setFlexDirection(int i) {
        if (this.mFlexDirection != i) {
            removeAllViews();
            this.mFlexDirection = i;
            this.mOrientationHelper = null;
            this.mSubOrientationHelper = null;
            clearFlexLines();
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getFlexWrap() {
        return this.mFlexWrap;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setFlexWrap(int i) {
        if (i == 2) {
            throw new UnsupportedOperationException("wrap_reverse is not supported in FlexboxLayoutManager");
        }
        int i2 = this.mFlexWrap;
        if (i2 != i) {
            if (i2 == 0 || i == 0) {
                removeAllViews();
                clearFlexLines();
            }
            this.mFlexWrap = i;
            this.mOrientationHelper = null;
            this.mSubOrientationHelper = null;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getJustifyContent() {
        return this.mJustifyContent;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setJustifyContent(int i) {
        if (this.mJustifyContent != i) {
            this.mJustifyContent = i;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getAlignItems() {
        return this.mAlignItems;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setAlignItems(int i) {
        int i2 = this.mAlignItems;
        if (i2 != i) {
            if (i2 == 4 || i == 4) {
                removeAllViews();
                clearFlexLines();
            }
            this.mAlignItems = i;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setAlignContent(int i) {
        throw new UnsupportedOperationException("Setting the alignContent in the FlexboxLayoutManager is not supported. Use FlexboxLayout if you need to use this attribute.");
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getMaxLine() {
        return this.mMaxLine;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setMaxLine(int i) {
        if (this.mMaxLine != i) {
            this.mMaxLine = i;
            requestLayout();
        }
    }

    public List<FlexLine> getFlexLines() {
        ArrayList arrayList = new ArrayList(this.mFlexLines.size());
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            FlexLine flexLine = this.mFlexLines.get(i);
            if (flexLine.getItemCount() != 0) {
                arrayList.add(flexLine);
            }
        }
        return arrayList;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getDecorationLengthMainAxis(View view, int i, int i2) {
        int topDecorationHeight;
        int bottomDecorationHeight;
        if (isMainAxisDirectionHorizontal()) {
            topDecorationHeight = getLeftDecorationWidth(view);
            bottomDecorationHeight = getRightDecorationWidth(view);
        } else {
            topDecorationHeight = getTopDecorationHeight(view);
            bottomDecorationHeight = getBottomDecorationHeight(view);
        }
        return topDecorationHeight + bottomDecorationHeight;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getDecorationLengthCrossAxis(View view) {
        int leftDecorationWidth;
        int rightDecorationWidth;
        if (isMainAxisDirectionHorizontal()) {
            leftDecorationWidth = getTopDecorationHeight(view);
            rightDecorationWidth = getBottomDecorationHeight(view);
        } else {
            leftDecorationWidth = getLeftDecorationWidth(view);
            rightDecorationWidth = getRightDecorationWidth(view);
        }
        return leftDecorationWidth + rightDecorationWidth;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void onNewFlexItemAdded(View view, int i, int i2, FlexLine flexLine) {
        calculateItemDecorationsForChild(view, TEMP_RECT);
        if (isMainAxisDirectionHorizontal()) {
            int leftDecorationWidth = getLeftDecorationWidth(view) + getRightDecorationWidth(view);
            flexLine.mMainSize += leftDecorationWidth;
            flexLine.mDividerLengthInMainSize += leftDecorationWidth;
        } else {
            int topDecorationHeight = getTopDecorationHeight(view) + getBottomDecorationHeight(view);
            flexLine.mMainSize += topDecorationHeight;
            flexLine.mDividerLengthInMainSize += topDecorationHeight;
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getFlexItemCount() {
        return this.mState.getItemCount();
    }

    public View getFlexItemAt(int i) {
        View view = this.mViewCache.get(i);
        return view != null ? view : this.mRecycler.getViewForPosition(i);
    }

    @Override // com.google.android.flexbox.FlexContainer
    public View getReorderedFlexItemAt(int i) {
        return getFlexItemAt(i);
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getChildWidthMeasureSpec(int i, int i2, int i3) {
        return RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), i2, i3, canScrollHorizontally());
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getChildHeightMeasureSpec(int i, int i2, int i3) {
        return RecyclerView.LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), i2, i3, canScrollVertically());
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getLargestMainSize() {
        if (this.mFlexLines.size() == 0) {
            return 0;
        }
        int iMax = Integer.MIN_VALUE;
        int size = this.mFlexLines.size();
        for (int i = 0; i < size; i++) {
            iMax = Math.max(iMax, this.mFlexLines.get(i).mMainSize);
        }
        return iMax;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getSumOfCrossSize() {
        int size = this.mFlexLines.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += this.mFlexLines.get(i2).mCrossSize;
        }
        return i;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public List<FlexLine> getFlexLinesInternal() {
        return this.mFlexLines;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void updateViewCache(int i, View view) {
        this.mViewCache.put(i, view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public PointF computeScrollVectorForPosition(int i) {
        if (getChildCount() == 0) {
            return null;
        }
        int i2 = i < getPosition(getChildAt(0)) ? -1 : 1;
        if (isMainAxisDirectionHorizontal()) {
            return new PointF(0.0f, i2);
        }
        return new PointF(i2, 0.0f);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        removeAllViews();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        if (this.mPendingSavedState != null) {
            return new SavedState(this.mPendingSavedState);
        }
        SavedState savedState = new SavedState();
        if (getChildCount() > 0) {
            View childClosestToStart = getChildClosestToStart();
            savedState.mAnchorPosition = getPosition(childClosestToStart);
            savedState.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart) - this.mOrientationHelper.getStartAfterPadding();
        } else {
            savedState.invalidateAnchor();
        }
        return savedState;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = (SavedState) parcelable;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsAdded(@NonNull RecyclerView recyclerView, int i, int i2) {
        super.onItemsAdded(recyclerView, i, i2);
        updateDirtyPosition(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(@NonNull RecyclerView recyclerView, int i, int i2, Object obj) {
        super.onItemsUpdated(recyclerView, i, i2, obj);
        updateDirtyPosition(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(@NonNull RecyclerView recyclerView, int i, int i2) {
        super.onItemsUpdated(recyclerView, i, i2);
        updateDirtyPosition(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(@NonNull RecyclerView recyclerView, int i, int i2) {
        super.onItemsRemoved(recyclerView, i, i2);
        updateDirtyPosition(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onItemsMoved(@NonNull RecyclerView recyclerView, int i, int i2, int i3) {
        super.onItemsMoved(recyclerView, i, i2, i3);
        updateDirtyPosition(Math.min(i, i2));
    }

    private void updateDirtyPosition(int i) {
        if (i >= findLastVisibleItemPosition()) {
            return;
        }
        int childCount = getChildCount();
        this.mFlexboxHelper.ensureMeasureSpecCache(childCount);
        this.mFlexboxHelper.ensureMeasuredSizeCache(childCount);
        this.mFlexboxHelper.ensureIndexToFlexLine(childCount);
        if (i >= this.mFlexboxHelper.mIndexToFlexLine.length) {
            return;
        }
        this.mDirtyPosition = i;
        View childClosestToStart = getChildClosestToStart();
        if (childClosestToStart == null) {
            return;
        }
        this.mPendingScrollPosition = getPosition(childClosestToStart);
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            this.mPendingScrollPositionOffset = this.mOrientationHelper.getDecoratedEnd(childClosestToStart) + this.mOrientationHelper.getEndPadding();
        } else {
            this.mPendingScrollPositionOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart) - this.mOrientationHelper.getStartAfterPadding();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i;
        int i2;
        this.mRecycler = recycler;
        this.mState = state;
        int itemCount = state.getItemCount();
        if (itemCount == 0 && state.isPreLayout()) {
            return;
        }
        resolveLayoutDirection();
        ensureOrientationHelper();
        ensureLayoutState();
        this.mFlexboxHelper.ensureMeasureSpecCache(itemCount);
        this.mFlexboxHelper.ensureMeasuredSizeCache(itemCount);
        this.mFlexboxHelper.ensureIndexToFlexLine(itemCount);
        this.mLayoutState.mShouldRecycle = false;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null && savedState.hasValidAnchor(itemCount)) {
            this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
        }
        if (!this.mAnchorInfo.mValid || this.mPendingScrollPosition != -1 || this.mPendingSavedState != null) {
            this.mAnchorInfo.reset();
            updateAnchorInfoForLayout(state, this.mAnchorInfo);
            this.mAnchorInfo.mValid = true;
        }
        detachAndScrapAttachedViews(recycler);
        if (this.mAnchorInfo.mLayoutFromEnd) {
            updateLayoutStateToFillStart(this.mAnchorInfo, false, true);
        } else {
            updateLayoutStateToFillEnd(this.mAnchorInfo, false, true);
        }
        updateFlexLines(itemCount);
        if (this.mAnchorInfo.mLayoutFromEnd) {
            fill(recycler, state, this.mLayoutState);
            i2 = this.mLayoutState.mOffset;
            updateLayoutStateToFillEnd(this.mAnchorInfo, true, false);
            fill(recycler, state, this.mLayoutState);
            i = this.mLayoutState.mOffset;
        } else {
            fill(recycler, state, this.mLayoutState);
            i = this.mLayoutState.mOffset;
            updateLayoutStateToFillStart(this.mAnchorInfo, true, false);
            fill(recycler, state, this.mLayoutState);
            i2 = this.mLayoutState.mOffset;
        }
        if (getChildCount() > 0) {
            if (this.mAnchorInfo.mLayoutFromEnd) {
                fixLayoutStartGap(i2 + fixLayoutEndGap(i, recycler, state, true), recycler, state, false);
            } else {
                fixLayoutEndGap(i + fixLayoutStartGap(i2, recycler, state, true), recycler, state, false);
            }
        }
    }

    private int fixLayoutStartGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int iHandleScrollingCrossAxis;
        int startAfterPadding;
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            int endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i;
            if (endAfterPadding <= 0) {
                return 0;
            }
            iHandleScrollingCrossAxis = handleScrollingCrossAxis(-endAfterPadding, recycler, state);
        } else {
            int startAfterPadding2 = i - this.mOrientationHelper.getStartAfterPadding();
            if (startAfterPadding2 <= 0) {
                return 0;
            }
            iHandleScrollingCrossAxis = -handleScrollingCrossAxis(startAfterPadding2, recycler, state);
        }
        int i2 = i + iHandleScrollingCrossAxis;
        if (!z || (startAfterPadding = i2 - this.mOrientationHelper.getStartAfterPadding()) <= 0) {
            return iHandleScrollingCrossAxis;
        }
        this.mOrientationHelper.offsetChildren(-startAfterPadding);
        return iHandleScrollingCrossAxis - startAfterPadding;
    }

    private int fixLayoutEndGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int iHandleScrollingCrossAxis;
        int endAfterPadding;
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            int startAfterPadding = i - this.mOrientationHelper.getStartAfterPadding();
            if (startAfterPadding <= 0) {
                return 0;
            }
            iHandleScrollingCrossAxis = handleScrollingCrossAxis(startAfterPadding, recycler, state);
        } else {
            int endAfterPadding2 = this.mOrientationHelper.getEndAfterPadding() - i;
            if (endAfterPadding2 <= 0) {
                return 0;
            }
            iHandleScrollingCrossAxis = -handleScrollingCrossAxis(-endAfterPadding2, recycler, state);
        }
        int i2 = i + iHandleScrollingCrossAxis;
        if (!z || (endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i2) <= 0) {
            return iHandleScrollingCrossAxis;
        }
        this.mOrientationHelper.offsetChildren(endAfterPadding);
        return endAfterPadding + iHandleScrollingCrossAxis;
    }

    private void updateFlexLines(int i) {
        boolean z;
        int i2;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        int width = getWidth();
        int height = getHeight();
        if (isMainAxisDirectionHorizontal()) {
            int i3 = this.mLastWidth;
            z = (i3 == Integer.MIN_VALUE || i3 == width) ? false : true;
            i2 = this.mLayoutState.mInfinite ? this.mContext.getResources().getDisplayMetrics().heightPixels : this.mLayoutState.mAvailable;
        } else {
            int i4 = this.mLastHeight;
            z = (i4 == Integer.MIN_VALUE || i4 == height) ? false : true;
            i2 = this.mLayoutState.mInfinite ? this.mContext.getResources().getDisplayMetrics().widthPixels : this.mLayoutState.mAvailable;
        }
        int i5 = i2;
        this.mLastWidth = width;
        this.mLastHeight = height;
        int i6 = this.mDirtyPosition;
        if (i6 != -1 || (this.mPendingScrollPosition == -1 && !z)) {
            int iMin = i6 != -1 ? Math.min(i6, this.mAnchorInfo.mPosition) : this.mAnchorInfo.mPosition;
            this.mFlexLinesResult.reset();
            if (isMainAxisDirectionHorizontal()) {
                if (this.mFlexLines.size() > 0) {
                    this.mFlexboxHelper.clearFlexLines(this.mFlexLines, iMin);
                    this.mFlexboxHelper.calculateFlexLines(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i5, iMin, this.mAnchorInfo.mPosition, this.mFlexLines);
                } else {
                    this.mFlexboxHelper.ensureIndexToFlexLine(i);
                    this.mFlexboxHelper.calculateHorizontalFlexLines(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i5, 0, this.mFlexLines);
                }
            } else if (this.mFlexLines.size() > 0) {
                this.mFlexboxHelper.clearFlexLines(this.mFlexLines, iMin);
                this.mFlexboxHelper.calculateFlexLines(this.mFlexLinesResult, iMakeMeasureSpec2, iMakeMeasureSpec, i5, iMin, this.mAnchorInfo.mPosition, this.mFlexLines);
            } else {
                this.mFlexboxHelper.ensureIndexToFlexLine(i);
                this.mFlexboxHelper.calculateVerticalFlexLines(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i5, 0, this.mFlexLines);
            }
            this.mFlexLines = this.mFlexLinesResult.mFlexLines;
            this.mFlexboxHelper.determineMainSize(iMakeMeasureSpec, iMakeMeasureSpec2, iMin);
            this.mFlexboxHelper.stretchViews(iMin);
            return;
        }
        if (this.mAnchorInfo.mLayoutFromEnd) {
            return;
        }
        this.mFlexLines.clear();
        this.mFlexLinesResult.reset();
        if (isMainAxisDirectionHorizontal()) {
            this.mFlexboxHelper.calculateHorizontalFlexLinesToIndex(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i5, this.mAnchorInfo.mPosition, this.mFlexLines);
        } else {
            this.mFlexboxHelper.calculateVerticalFlexLinesToIndex(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i5, this.mAnchorInfo.mPosition, this.mFlexLines);
        }
        this.mFlexLines = this.mFlexLinesResult.mFlexLines;
        this.mFlexboxHelper.determineMainSize(iMakeMeasureSpec, iMakeMeasureSpec2);
        this.mFlexboxHelper.stretchViews();
        AnchorInfo anchorInfo = this.mAnchorInfo;
        anchorInfo.mFlexLinePosition = this.mFlexboxHelper.mIndexToFlexLine[anchorInfo.mPosition];
        this.mLayoutState.mFlexLinePosition = this.mAnchorInfo.mFlexLinePosition;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mDirtyPosition = -1;
        this.mAnchorInfo.reset();
        this.mViewCache.clear();
    }

    boolean isLayoutRtl() {
        return this.mIsRtl;
    }

    private void resolveLayoutDirection() {
        int layoutDirection = getLayoutDirection();
        int i = this.mFlexDirection;
        if (i == 0) {
            this.mIsRtl = layoutDirection == 1;
            this.mFromBottomToTop = this.mFlexWrap == 2;
            return;
        }
        if (i == 1) {
            this.mIsRtl = layoutDirection != 1;
            this.mFromBottomToTop = this.mFlexWrap == 2;
            return;
        }
        if (i == 2) {
            boolean z = layoutDirection == 1;
            this.mIsRtl = z;
            if (this.mFlexWrap == 2) {
                this.mIsRtl = !z;
            }
            this.mFromBottomToTop = false;
            return;
        }
        if (i == 3) {
            boolean z2 = layoutDirection == 1;
            this.mIsRtl = z2;
            if (this.mFlexWrap == 2) {
                this.mIsRtl = !z2;
            }
            this.mFromBottomToTop = true;
            return;
        }
        this.mIsRtl = false;
        this.mFromBottomToTop = false;
    }

    private void updateAnchorInfoForLayout(RecyclerView.State state, AnchorInfo anchorInfo) {
        if (updateAnchorFromPendingState(state, anchorInfo, this.mPendingSavedState) || updateAnchorFromChildren(state, anchorInfo)) {
            return;
        }
        anchorInfo.assignCoordinateFromPadding();
        anchorInfo.mPosition = 0;
        anchorInfo.mFlexLinePosition = 0;
    }

    private boolean updateAnchorFromPendingState(RecyclerView.State state, AnchorInfo anchorInfo, SavedState savedState) {
        int i;
        int decoratedStart;
        if (!state.isPreLayout() && (i = this.mPendingScrollPosition) != -1) {
            if (i < 0 || i >= state.getItemCount()) {
                this.mPendingScrollPosition = -1;
                this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
            } else {
                anchorInfo.mPosition = this.mPendingScrollPosition;
                anchorInfo.mFlexLinePosition = this.mFlexboxHelper.mIndexToFlexLine[anchorInfo.mPosition];
                SavedState savedState2 = this.mPendingSavedState;
                if (savedState2 == null || !savedState2.hasValidAnchor(state.getItemCount())) {
                    if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                        View viewFindViewByPosition = findViewByPosition(this.mPendingScrollPosition);
                        if (viewFindViewByPosition != null) {
                            if (this.mOrientationHelper.getDecoratedMeasurement(viewFindViewByPosition) <= this.mOrientationHelper.getTotalSpace()) {
                                if (this.mOrientationHelper.getDecoratedStart(viewFindViewByPosition) - this.mOrientationHelper.getStartAfterPadding() >= 0) {
                                    if (this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(viewFindViewByPosition) < 0) {
                                        anchorInfo.mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                                        anchorInfo.mLayoutFromEnd = true;
                                        return true;
                                    }
                                    if (anchorInfo.mLayoutFromEnd) {
                                        decoratedStart = this.mOrientationHelper.getDecoratedEnd(viewFindViewByPosition) + this.mOrientationHelper.getTotalSpaceChange();
                                    } else {
                                        decoratedStart = this.mOrientationHelper.getDecoratedStart(viewFindViewByPosition);
                                    }
                                    anchorInfo.mCoordinate = decoratedStart;
                                } else {
                                    anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                                    anchorInfo.mLayoutFromEnd = false;
                                    return true;
                                }
                            } else {
                                anchorInfo.assignCoordinateFromPadding();
                                return true;
                            }
                        } else {
                            if (getChildCount() > 0) {
                                anchorInfo.mLayoutFromEnd = this.mPendingScrollPosition < getPosition(getChildAt(0));
                            }
                            anchorInfo.assignCoordinateFromPadding();
                        }
                        return true;
                    }
                    if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
                        anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset;
                    } else {
                        anchorInfo.mCoordinate = this.mPendingScrollPositionOffset - this.mOrientationHelper.getEndPadding();
                    }
                    return true;
                }
                anchorInfo.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + savedState.mAnchorOffset;
                anchorInfo.mAssignedFromSavedState = true;
                anchorInfo.mFlexLinePosition = -1;
                return true;
            }
        }
        return false;
    }

    private boolean updateAnchorFromChildren(RecyclerView.State state, AnchorInfo anchorInfo) {
        View viewFindFirstReferenceChild;
        int startAfterPadding;
        if (getChildCount() == 0) {
            return false;
        }
        if (anchorInfo.mLayoutFromEnd) {
            viewFindFirstReferenceChild = findLastReferenceChild(state.getItemCount());
        } else {
            viewFindFirstReferenceChild = findFirstReferenceChild(state.getItemCount());
        }
        if (viewFindFirstReferenceChild == null) {
            return false;
        }
        anchorInfo.assignFromView(viewFindFirstReferenceChild);
        if (!state.isPreLayout() && supportsPredictiveItemAnimations()) {
            if (this.mOrientationHelper.getDecoratedStart(viewFindFirstReferenceChild) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(viewFindFirstReferenceChild) < this.mOrientationHelper.getStartAfterPadding()) {
                if (anchorInfo.mLayoutFromEnd) {
                    startAfterPadding = this.mOrientationHelper.getEndAfterPadding();
                } else {
                    startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
                }
                anchorInfo.mCoordinate = startAfterPadding;
            }
        }
        return true;
    }

    private View findFirstReferenceChild(int i) {
        View viewFindReferenceChild = findReferenceChild(0, getChildCount(), i);
        if (viewFindReferenceChild == null) {
            return null;
        }
        int i2 = this.mFlexboxHelper.mIndexToFlexLine[getPosition(viewFindReferenceChild)];
        if (i2 == -1) {
            return null;
        }
        return findFirstReferenceViewInLine(viewFindReferenceChild, this.mFlexLines.get(i2));
    }

    private View findLastReferenceChild(int i) {
        View viewFindReferenceChild = findReferenceChild(getChildCount() - 1, -1, i);
        if (viewFindReferenceChild == null) {
            return null;
        }
        return findLastReferenceViewInLine(viewFindReferenceChild, this.mFlexLines.get(this.mFlexboxHelper.mIndexToFlexLine[getPosition(viewFindReferenceChild)]));
    }

    private View findReferenceChild(int i, int i2, int i3) {
        ensureOrientationHelper();
        ensureLayoutState();
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int i4 = i2 > i ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i != i2) {
            View childAt = getChildAt(i);
            int position = getPosition(childAt);
            if (position >= 0 && position < i3) {
                if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else {
                    if (this.mOrientationHelper.getDecoratedStart(childAt) >= startAfterPadding && this.mOrientationHelper.getDecoratedEnd(childAt) <= endAfterPadding) {
                        return childAt;
                    }
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i += i4;
        }
        return view != null ? view : view2;
    }

    private View getChildClosestToStart() {
        return getChildAt(0);
    }

    private int fill(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutState layoutState) {
        if (layoutState.mScrollingOffset != Integer.MIN_VALUE) {
            if (layoutState.mAvailable < 0) {
                layoutState.mScrollingOffset += layoutState.mAvailable;
            }
            recycleByLayoutState(recycler, layoutState);
        }
        int i = layoutState.mAvailable;
        int crossSize = layoutState.mAvailable;
        int iLayoutFlexLine = 0;
        boolean zIsMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        while (true) {
            if ((crossSize <= 0 && !this.mLayoutState.mInfinite) || !layoutState.hasMore(state, this.mFlexLines)) {
                break;
            }
            FlexLine flexLine = this.mFlexLines.get(layoutState.mFlexLinePosition);
            layoutState.mPosition = flexLine.mFirstIndex;
            iLayoutFlexLine += layoutFlexLine(flexLine, layoutState);
            if (zIsMainAxisDirectionHorizontal || !this.mIsRtl) {
                layoutState.mOffset += flexLine.getCrossSize() * layoutState.mLayoutDirection;
            } else {
                layoutState.mOffset -= flexLine.getCrossSize() * layoutState.mLayoutDirection;
            }
            crossSize -= flexLine.getCrossSize();
        }
        layoutState.mAvailable -= iLayoutFlexLine;
        if (layoutState.mScrollingOffset != Integer.MIN_VALUE) {
            layoutState.mScrollingOffset += iLayoutFlexLine;
            if (layoutState.mAvailable < 0) {
                layoutState.mScrollingOffset += layoutState.mAvailable;
            }
            recycleByLayoutState(recycler, layoutState);
        }
        return i - layoutState.mAvailable;
    }

    private void recycleByLayoutState(RecyclerView.Recycler recycler, LayoutState layoutState) {
        if (layoutState.mShouldRecycle) {
            if (layoutState.mLayoutDirection == -1) {
                recycleFlexLinesFromEnd(recycler, layoutState);
            } else {
                recycleFlexLinesFromStart(recycler, layoutState);
            }
        }
    }

    private void recycleFlexLinesFromStart(RecyclerView.Recycler recycler, LayoutState layoutState) {
        int childCount;
        if (layoutState.mScrollingOffset >= 0 && (childCount = getChildCount()) != 0) {
            int i = this.mFlexboxHelper.mIndexToFlexLine[getPosition(getChildAt(0))];
            int i2 = -1;
            if (i == -1) {
                return;
            }
            FlexLine flexLine = this.mFlexLines.get(i);
            int i3 = 0;
            while (true) {
                if (i3 >= childCount) {
                    break;
                }
                View childAt = getChildAt(i3);
                if (!canViewBeRecycledFromStart(childAt, layoutState.mScrollingOffset)) {
                    break;
                }
                if (flexLine.mLastIndex == getPosition(childAt)) {
                    if (i >= this.mFlexLines.size() - 1) {
                        i2 = i3;
                        break;
                    } else {
                        i += layoutState.mLayoutDirection;
                        flexLine = this.mFlexLines.get(i);
                        i2 = i3;
                    }
                }
                i3++;
            }
            recycleChildren(recycler, 0, i2);
        }
    }

    private boolean canViewBeRecycledFromStart(View view, int i) {
        return (isMainAxisDirectionHorizontal() || !this.mIsRtl) ? this.mOrientationHelper.getDecoratedEnd(view) <= i : this.mOrientationHelper.getEnd() - this.mOrientationHelper.getDecoratedStart(view) <= i;
    }

    private void recycleFlexLinesFromEnd(RecyclerView.Recycler recycler, LayoutState layoutState) {
        if (layoutState.mScrollingOffset < 0) {
            return;
        }
        this.mOrientationHelper.getEnd();
        int unused = layoutState.mScrollingOffset;
        int childCount = getChildCount();
        if (childCount == 0) {
            return;
        }
        int i = childCount - 1;
        int i2 = this.mFlexboxHelper.mIndexToFlexLine[getPosition(getChildAt(i))];
        if (i2 == -1) {
            return;
        }
        FlexLine flexLine = this.mFlexLines.get(i2);
        int i3 = i;
        while (true) {
            if (i3 < 0) {
                break;
            }
            View childAt = getChildAt(i3);
            if (!canViewBeRecycledFromEnd(childAt, layoutState.mScrollingOffset)) {
                break;
            }
            if (flexLine.mFirstIndex == getPosition(childAt)) {
                if (i2 <= 0) {
                    childCount = i3;
                    break;
                } else {
                    i2 += layoutState.mLayoutDirection;
                    flexLine = this.mFlexLines.get(i2);
                    childCount = i3;
                }
            }
            i3--;
        }
        recycleChildren(recycler, childCount, i);
    }

    private boolean canViewBeRecycledFromEnd(View view, int i) {
        return (isMainAxisDirectionHorizontal() || !this.mIsRtl) ? this.mOrientationHelper.getDecoratedStart(view) >= this.mOrientationHelper.getEnd() - i : this.mOrientationHelper.getDecoratedEnd(view) <= i;
    }

    private void recycleChildren(RecyclerView.Recycler recycler, int i, int i2) {
        while (i2 >= i) {
            removeAndRecycleViewAt(i2, recycler);
            i2--;
        }
    }

    private int layoutFlexLine(FlexLine flexLine, LayoutState layoutState) {
        if (isMainAxisDirectionHorizontal()) {
            return layoutFlexLineMainAxisHorizontal(flexLine, layoutState);
        }
        return layoutFlexLineMainAxisVertical(flexLine, layoutState);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00ce  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int layoutFlexLineMainAxisHorizontal(com.google.android.flexbox.FlexLine r22, com.google.android.flexbox.FlexboxLayoutManager.LayoutState r23) {
        /*
            Method dump skipped, instruction units count: 428
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.layoutFlexLineMainAxisHorizontal(com.google.android.flexbox.FlexLine, com.google.android.flexbox.FlexboxLayoutManager$LayoutState):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00d4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int layoutFlexLineMainAxisVertical(com.google.android.flexbox.FlexLine r26, com.google.android.flexbox.FlexboxLayoutManager.LayoutState r27) {
        /*
            Method dump skipped, instruction units count: 546
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.layoutFlexLineMainAxisVertical(com.google.android.flexbox.FlexLine, com.google.android.flexbox.FlexboxLayoutManager$LayoutState):int");
    }

    @Override // com.google.android.flexbox.FlexContainer
    public boolean isMainAxisDirectionHorizontal() {
        int i = this.mFlexDirection;
        return i == 0 || i == 1;
    }

    private void updateLayoutStateToFillEnd(AnchorInfo anchorInfo, boolean z, boolean z2) {
        if (z2) {
            resolveInfiniteAmount();
        } else {
            this.mLayoutState.mInfinite = false;
        }
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            this.mLayoutState.mAvailable = anchorInfo.mCoordinate - getPaddingRight();
        } else {
            this.mLayoutState.mAvailable = this.mOrientationHelper.getEndAfterPadding() - anchorInfo.mCoordinate;
        }
        this.mLayoutState.mPosition = anchorInfo.mPosition;
        this.mLayoutState.mItemDirection = 1;
        this.mLayoutState.mLayoutDirection = 1;
        this.mLayoutState.mOffset = anchorInfo.mCoordinate;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
        this.mLayoutState.mFlexLinePosition = anchorInfo.mFlexLinePosition;
        if (!z || this.mFlexLines.size() <= 1 || anchorInfo.mFlexLinePosition < 0 || anchorInfo.mFlexLinePosition >= this.mFlexLines.size() - 1) {
            return;
        }
        FlexLine flexLine = this.mFlexLines.get(anchorInfo.mFlexLinePosition);
        LayoutState.access$1508(this.mLayoutState);
        this.mLayoutState.mPosition += flexLine.getItemCount();
    }

    private void updateLayoutStateToFillStart(AnchorInfo anchorInfo, boolean z, boolean z2) {
        if (z2) {
            resolveInfiniteAmount();
        } else {
            this.mLayoutState.mInfinite = false;
        }
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            this.mLayoutState.mAvailable = (this.mParent.getWidth() - anchorInfo.mCoordinate) - this.mOrientationHelper.getStartAfterPadding();
        } else {
            this.mLayoutState.mAvailable = anchorInfo.mCoordinate - this.mOrientationHelper.getStartAfterPadding();
        }
        this.mLayoutState.mPosition = anchorInfo.mPosition;
        this.mLayoutState.mItemDirection = 1;
        this.mLayoutState.mLayoutDirection = -1;
        this.mLayoutState.mOffset = anchorInfo.mCoordinate;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
        this.mLayoutState.mFlexLinePosition = anchorInfo.mFlexLinePosition;
        if (!z || anchorInfo.mFlexLinePosition <= 0 || this.mFlexLines.size() <= anchorInfo.mFlexLinePosition) {
            return;
        }
        FlexLine flexLine = this.mFlexLines.get(anchorInfo.mFlexLinePosition);
        LayoutState.access$1510(this.mLayoutState);
        this.mLayoutState.mPosition -= flexLine.getItemCount();
    }

    private void resolveInfiniteAmount() {
        int widthMode;
        if (isMainAxisDirectionHorizontal()) {
            widthMode = getHeightMode();
        } else {
            widthMode = getWidthMode();
        }
        this.mLayoutState.mInfinite = widthMode == 0 || widthMode == Integer.MIN_VALUE;
    }

    private void ensureOrientationHelper() {
        if (this.mOrientationHelper != null) {
            return;
        }
        if (isMainAxisDirectionHorizontal()) {
            if (this.mFlexWrap == 0) {
                this.mOrientationHelper = OrientationHelper.createHorizontalHelper(this);
                this.mSubOrientationHelper = OrientationHelper.createVerticalHelper(this);
                return;
            } else {
                this.mOrientationHelper = OrientationHelper.createVerticalHelper(this);
                this.mSubOrientationHelper = OrientationHelper.createHorizontalHelper(this);
                return;
            }
        }
        if (this.mFlexWrap == 0) {
            this.mOrientationHelper = OrientationHelper.createVerticalHelper(this);
            this.mSubOrientationHelper = OrientationHelper.createHorizontalHelper(this);
        } else {
            this.mOrientationHelper = OrientationHelper.createHorizontalHelper(this);
            this.mSubOrientationHelper = OrientationHelper.createVerticalHelper(this);
        }
    }

    private void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = new LayoutState();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i) {
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.invalidateAnchor();
        }
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext());
        linearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(linearSmoothScroller);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.mParent = (View) recyclerView.getParent();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        if (this.mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(recycler);
            recycler.clear();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return !isMainAxisDirectionHorizontal() || getWidth() > this.mParent.getWidth();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return isMainAxisDirectionHorizontal() || getHeight() > this.mParent.getHeight();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!isMainAxisDirectionHorizontal()) {
            int iHandleScrollingCrossAxis = handleScrollingCrossAxis(i, recycler, state);
            this.mViewCache.clear();
            return iHandleScrollingCrossAxis;
        }
        int iHandleScrollingMainAxis = handleScrollingMainAxis(i);
        this.mAnchorInfo.mPerpendicularCoordinate += iHandleScrollingMainAxis;
        this.mSubOrientationHelper.offsetChildren(-iHandleScrollingMainAxis);
        return iHandleScrollingMainAxis;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (isMainAxisDirectionHorizontal()) {
            int iHandleScrollingCrossAxis = handleScrollingCrossAxis(i, recycler, state);
            this.mViewCache.clear();
            return iHandleScrollingCrossAxis;
        }
        int iHandleScrollingMainAxis = handleScrollingMainAxis(i);
        this.mAnchorInfo.mPerpendicularCoordinate += iHandleScrollingMainAxis;
        this.mSubOrientationHelper.offsetChildren(-iHandleScrollingMainAxis);
        return iHandleScrollingMainAxis;
    }

    private int handleScrollingCrossAxis(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        ensureOrientationHelper();
        int i2 = 1;
        this.mLayoutState.mShouldRecycle = true;
        boolean z = !isMainAxisDirectionHorizontal() && this.mIsRtl;
        if (!z ? i <= 0 : i >= 0) {
            i2 = -1;
        }
        int iAbs = Math.abs(i);
        updateLayoutState(i2, iAbs);
        int iFill = this.mLayoutState.mScrollingOffset + fill(recycler, state, this.mLayoutState);
        if (iFill < 0) {
            return 0;
        }
        if (z) {
            if (iAbs > iFill) {
                i = (-i2) * iFill;
            }
        } else if (iAbs > iFill) {
            i = i2 * iFill;
        }
        this.mOrientationHelper.offsetChildren(-i);
        this.mLayoutState.mLastScrollDelta = i;
        return i;
    }

    private int handleScrollingMainAxis(int i) {
        int iMin;
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        ensureOrientationHelper();
        boolean zIsMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        View view = this.mParent;
        int width = zIsMainAxisDirectionHorizontal ? view.getWidth() : view.getHeight();
        int width2 = zIsMainAxisDirectionHorizontal ? getWidth() : getHeight();
        if (getLayoutDirection() == 1) {
            int iAbs = Math.abs(i);
            if (i < 0) {
                iMin = Math.min((width2 + this.mAnchorInfo.mPerpendicularCoordinate) - width, iAbs);
            } else {
                if (this.mAnchorInfo.mPerpendicularCoordinate + i <= 0) {
                    return i;
                }
                iMin = this.mAnchorInfo.mPerpendicularCoordinate;
            }
        } else {
            if (i > 0) {
                return Math.min((width2 - this.mAnchorInfo.mPerpendicularCoordinate) - width, i);
            }
            if (this.mAnchorInfo.mPerpendicularCoordinate + i >= 0) {
                return i;
            }
            iMin = this.mAnchorInfo.mPerpendicularCoordinate;
        }
        return -iMin;
    }

    private void updateLayoutState(int i, int i2) {
        this.mLayoutState.mLayoutDirection = i;
        boolean zIsMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        boolean z = !zIsMainAxisDirectionHorizontal && this.mIsRtl;
        if (i == 1) {
            View childAt = getChildAt(getChildCount() - 1);
            this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(childAt);
            int position = getPosition(childAt);
            View viewFindLastReferenceViewInLine = findLastReferenceViewInLine(childAt, this.mFlexLines.get(this.mFlexboxHelper.mIndexToFlexLine[position]));
            this.mLayoutState.mItemDirection = 1;
            LayoutState layoutState = this.mLayoutState;
            layoutState.mPosition = position + layoutState.mItemDirection;
            if (this.mFlexboxHelper.mIndexToFlexLine.length > this.mLayoutState.mPosition) {
                LayoutState layoutState2 = this.mLayoutState;
                layoutState2.mFlexLinePosition = this.mFlexboxHelper.mIndexToFlexLine[layoutState2.mPosition];
            } else {
                this.mLayoutState.mFlexLinePosition = -1;
            }
            if (z) {
                this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(viewFindLastReferenceViewInLine);
                this.mLayoutState.mScrollingOffset = (-this.mOrientationHelper.getDecoratedStart(viewFindLastReferenceViewInLine)) + this.mOrientationHelper.getStartAfterPadding();
                LayoutState layoutState3 = this.mLayoutState;
                layoutState3.mScrollingOffset = layoutState3.mScrollingOffset >= 0 ? this.mLayoutState.mScrollingOffset : 0;
            } else {
                this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(viewFindLastReferenceViewInLine);
                this.mLayoutState.mScrollingOffset = this.mOrientationHelper.getDecoratedEnd(viewFindLastReferenceViewInLine) - this.mOrientationHelper.getEndAfterPadding();
            }
            if ((this.mLayoutState.mFlexLinePosition == -1 || this.mLayoutState.mFlexLinePosition > this.mFlexLines.size() - 1) && this.mLayoutState.mPosition <= getFlexItemCount()) {
                int i3 = i2 - this.mLayoutState.mScrollingOffset;
                this.mFlexLinesResult.reset();
                if (i3 > 0) {
                    if (zIsMainAxisDirectionHorizontal) {
                        this.mFlexboxHelper.calculateHorizontalFlexLines(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i3, this.mLayoutState.mPosition, this.mFlexLines);
                    } else {
                        this.mFlexboxHelper.calculateVerticalFlexLines(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i3, this.mLayoutState.mPosition, this.mFlexLines);
                    }
                    this.mFlexboxHelper.determineMainSize(iMakeMeasureSpec, iMakeMeasureSpec2, this.mLayoutState.mPosition);
                    this.mFlexboxHelper.stretchViews(this.mLayoutState.mPosition);
                }
            }
        } else {
            View childAt2 = getChildAt(0);
            this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(childAt2);
            int position2 = getPosition(childAt2);
            View viewFindFirstReferenceViewInLine = findFirstReferenceViewInLine(childAt2, this.mFlexLines.get(this.mFlexboxHelper.mIndexToFlexLine[position2]));
            this.mLayoutState.mItemDirection = 1;
            int i4 = this.mFlexboxHelper.mIndexToFlexLine[position2];
            if (i4 == -1) {
                i4 = 0;
            }
            if (i4 > 0) {
                this.mLayoutState.mPosition = position2 - this.mFlexLines.get(i4 - 1).getItemCount();
            } else {
                this.mLayoutState.mPosition = -1;
            }
            this.mLayoutState.mFlexLinePosition = i4 > 0 ? i4 - 1 : 0;
            if (z) {
                this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedEnd(viewFindFirstReferenceViewInLine);
                this.mLayoutState.mScrollingOffset = this.mOrientationHelper.getDecoratedEnd(viewFindFirstReferenceViewInLine) - this.mOrientationHelper.getEndAfterPadding();
                LayoutState layoutState4 = this.mLayoutState;
                layoutState4.mScrollingOffset = layoutState4.mScrollingOffset >= 0 ? this.mLayoutState.mScrollingOffset : 0;
            } else {
                this.mLayoutState.mOffset = this.mOrientationHelper.getDecoratedStart(viewFindFirstReferenceViewInLine);
                this.mLayoutState.mScrollingOffset = (-this.mOrientationHelper.getDecoratedStart(viewFindFirstReferenceViewInLine)) + this.mOrientationHelper.getStartAfterPadding();
            }
        }
        LayoutState layoutState5 = this.mLayoutState;
        layoutState5.mAvailable = i2 - layoutState5.mScrollingOffset;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.view.View findFirstReferenceViewInLine(android.view.View r6, com.google.android.flexbox.FlexLine r7) {
        /*
            r5 = this;
            boolean r0 = r5.isMainAxisDirectionHorizontal()
            int r7 = r7.mItemCount
            r1 = 1
        L7:
            if (r1 >= r7) goto L3f
            android.view.View r2 = r5.getChildAt(r1)
            if (r2 == 0) goto L3c
            int r3 = r2.getVisibility()
            r4 = 8
            if (r3 != r4) goto L18
            goto L3c
        L18:
            boolean r3 = r5.mIsRtl
            if (r3 == 0) goto L2d
            if (r0 != 0) goto L2d
            androidx.recyclerview.widget.OrientationHelper r3 = r5.mOrientationHelper
            int r3 = r3.getDecoratedEnd(r6)
            androidx.recyclerview.widget.OrientationHelper r4 = r5.mOrientationHelper
            int r4 = r4.getDecoratedEnd(r2)
            if (r3 >= r4) goto L3c
            goto L3b
        L2d:
            androidx.recyclerview.widget.OrientationHelper r3 = r5.mOrientationHelper
            int r3 = r3.getDecoratedStart(r6)
            androidx.recyclerview.widget.OrientationHelper r4 = r5.mOrientationHelper
            int r4 = r4.getDecoratedStart(r2)
            if (r3 <= r4) goto L3c
        L3b:
            r6 = r2
        L3c:
            int r1 = r1 + 1
            goto L7
        L3f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.findFirstReferenceViewInLine(android.view.View, com.google.android.flexbox.FlexLine):android.view.View");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.view.View findLastReferenceViewInLine(android.view.View r6, com.google.android.flexbox.FlexLine r7) {
        /*
            r5 = this;
            boolean r0 = r5.isMainAxisDirectionHorizontal()
            int r1 = r5.getChildCount()
            int r1 = r1 + (-2)
            int r2 = r5.getChildCount()
            int r7 = r7.mItemCount
            int r2 = r2 - r7
            int r2 = r2 + (-1)
        L13:
            if (r1 <= r2) goto L4b
            android.view.View r7 = r5.getChildAt(r1)
            if (r7 == 0) goto L48
            int r3 = r7.getVisibility()
            r4 = 8
            if (r3 != r4) goto L24
            goto L48
        L24:
            boolean r3 = r5.mIsRtl
            if (r3 == 0) goto L39
            if (r0 != 0) goto L39
            androidx.recyclerview.widget.OrientationHelper r3 = r5.mOrientationHelper
            int r3 = r3.getDecoratedStart(r6)
            androidx.recyclerview.widget.OrientationHelper r4 = r5.mOrientationHelper
            int r4 = r4.getDecoratedStart(r7)
            if (r3 <= r4) goto L48
            goto L47
        L39:
            androidx.recyclerview.widget.OrientationHelper r3 = r5.mOrientationHelper
            int r3 = r3.getDecoratedEnd(r6)
            androidx.recyclerview.widget.OrientationHelper r4 = r5.mOrientationHelper
            int r4 = r4.getDecoratedEnd(r7)
            if (r3 >= r4) goto L48
        L47:
            r6 = r7
        L48:
            int r1 = r1 + (-1)
            goto L13
        L4b:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.findLastReferenceViewInLine(android.view.View, com.google.android.flexbox.FlexLine):android.view.View");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    private int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        ensureOrientationHelper();
        View viewFindFirstReferenceChild = findFirstReferenceChild(itemCount);
        View viewFindLastReferenceChild = findLastReferenceChild(itemCount);
        if (state.getItemCount() == 0 || viewFindFirstReferenceChild == null || viewFindLastReferenceChild == null) {
            return 0;
        }
        return Math.min(this.mOrientationHelper.getTotalSpace(), this.mOrientationHelper.getDecoratedEnd(viewFindLastReferenceChild) - this.mOrientationHelper.getDecoratedStart(viewFindFirstReferenceChild));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    private int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View viewFindFirstReferenceChild = findFirstReferenceChild(itemCount);
        View viewFindLastReferenceChild = findLastReferenceChild(itemCount);
        if (state.getItemCount() != 0 && viewFindFirstReferenceChild != null && viewFindLastReferenceChild != null) {
            int position = getPosition(viewFindFirstReferenceChild);
            int position2 = getPosition(viewFindLastReferenceChild);
            int iAbs = Math.abs(this.mOrientationHelper.getDecoratedEnd(viewFindLastReferenceChild) - this.mOrientationHelper.getDecoratedStart(viewFindFirstReferenceChild));
            int i = this.mFlexboxHelper.mIndexToFlexLine[position];
            if (i != 0 && i != -1) {
                return Math.round((i * (iAbs / ((r4[position2] - i) + 1))) + (this.mOrientationHelper.getStartAfterPadding() - this.mOrientationHelper.getDecoratedStart(viewFindFirstReferenceChild)));
            }
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    private int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int itemCount = state.getItemCount();
        View viewFindFirstReferenceChild = findFirstReferenceChild(itemCount);
        View viewFindLastReferenceChild = findLastReferenceChild(itemCount);
        if (state.getItemCount() == 0 || viewFindFirstReferenceChild == null || viewFindLastReferenceChild == null) {
            return 0;
        }
        int iFindFirstVisibleItemPosition = findFirstVisibleItemPosition();
        return (int) ((Math.abs(this.mOrientationHelper.getDecoratedEnd(viewFindLastReferenceChild) - this.mOrientationHelper.getDecoratedStart(viewFindFirstReferenceChild)) / ((findLastVisibleItemPosition() - iFindFirstVisibleItemPosition) + 1)) * state.getItemCount());
    }

    private boolean shouldMeasureChild(View view, int i, int i2, RecyclerView.LayoutParams layoutParams) {
        return (!view.isLayoutRequested() && isMeasurementCacheEnabled() && isMeasurementUpToDate(view.getWidth(), i, ((ViewGroup.MarginLayoutParams) layoutParams).width) && isMeasurementUpToDate(view.getHeight(), i2, ((ViewGroup.MarginLayoutParams) layoutParams).height)) ? false : true;
    }

    private static boolean isMeasurementUpToDate(int i, int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (i3 > 0 && i != i3) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            return size >= i;
        }
        if (mode != 0) {
            return mode == 1073741824 && size == i;
        }
        return true;
    }

    private void clearFlexLines() {
        this.mFlexLines.clear();
        this.mAnchorInfo.reset();
        this.mAnchorInfo.mPerpendicularCoordinate = 0;
    }

    private int getChildLeft(View view) {
        return getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).leftMargin;
    }

    private int getChildRight(View view) {
        return getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).rightMargin;
    }

    private int getChildTop(View view) {
        return getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).topMargin;
    }

    private int getChildBottom(View view) {
        return getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) view.getLayoutParams())).bottomMargin;
    }

    private boolean isViewVisible(View view, boolean z) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = getWidth() - getPaddingRight();
        int height = getHeight() - getPaddingBottom();
        int childLeft = getChildLeft(view);
        int childTop = getChildTop(view);
        int childRight = getChildRight(view);
        int childBottom = getChildBottom(view);
        return z ? (paddingLeft <= childLeft && width >= childRight) && (paddingTop <= childTop && height >= childBottom) : (childLeft >= width || childRight >= paddingLeft) && (childTop >= height || childBottom >= paddingTop);
    }

    public int findFirstVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(0, getChildCount(), false);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return getPosition(viewFindOneVisibleChild);
    }

    public int findLastVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, false);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return getPosition(viewFindOneVisibleChild);
    }

    private View findOneVisibleChild(int i, int i2, boolean z) {
        int i3 = i2 > i ? 1 : -1;
        while (i != i2) {
            View childAt = getChildAt(i);
            if (isViewVisible(childAt, z)) {
                return childAt;
            }
            i += i3;
        }
        return null;
    }

    int getPositionToFlexLineIndex(int i) {
        return this.mFlexboxHelper.mIndexToFlexLine[i];
    }

    public static class LayoutParams extends RecyclerView.LayoutParams implements FlexItem {
        public static final Parcelable.Creator<LayoutParams> CREATOR = new Parcelable.Creator<LayoutParams>() { // from class: com.google.android.flexbox.FlexboxLayoutManager.LayoutParams.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LayoutParams createFromParcel(Parcel parcel) {
                return new LayoutParams(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LayoutParams[] newArray(int i) {
                return new LayoutParams[i];
            }
        };
        private int mAlignSelf;
        private float mFlexBasisPercent;
        private float mFlexGrow;
        private float mFlexShrink;
        private int mMaxHeight;
        private int mMaxWidth;
        private int mMinHeight;
        private int mMinWidth;
        private boolean mWrapBefore;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getOrder() {
            return 1;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getWidth() {
            return ((ViewGroup.MarginLayoutParams) this).width;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setWidth(int i) {
            ((ViewGroup.MarginLayoutParams) this).width = i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getHeight() {
            return ((ViewGroup.MarginLayoutParams) this).height;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setHeight(int i) {
            ((ViewGroup.MarginLayoutParams) this).height = i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float getFlexGrow() {
            return this.mFlexGrow;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setFlexGrow(float f) {
            this.mFlexGrow = f;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float getFlexShrink() {
            return this.mFlexShrink;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setFlexShrink(float f) {
            this.mFlexShrink = f;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getAlignSelf() {
            return this.mAlignSelf;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setAlignSelf(int i) {
            this.mAlignSelf = i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMinWidth() {
            return this.mMinWidth;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setMinWidth(int i) {
            this.mMinWidth = i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMinHeight() {
            return this.mMinHeight;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setMinHeight(int i) {
            this.mMinHeight = i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMaxWidth() {
            return this.mMaxWidth;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setMaxWidth(int i) {
            this.mMaxWidth = i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMaxHeight() {
            return this.mMaxHeight;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setMaxHeight(int i) {
            this.mMaxHeight = i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public boolean isWrapBefore() {
            return this.mWrapBefore;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float getFlexBasisPercent() {
            return this.mFlexBasisPercent;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setFlexBasisPercent(float f) {
            this.mFlexBasisPercent = f;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginLeft() {
            return ((ViewGroup.MarginLayoutParams) this).leftMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginTop() {
            return ((ViewGroup.MarginLayoutParams) this).topMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginRight() {
            return ((ViewGroup.MarginLayoutParams) this).rightMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginBottom() {
            return ((ViewGroup.MarginLayoutParams) this).bottomMargin;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = ViewCompat.MEASURED_SIZE_MASK;
            this.mMaxHeight = ViewCompat.MEASURED_SIZE_MASK;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = ViewCompat.MEASURED_SIZE_MASK;
            this.mMaxHeight = ViewCompat.MEASURED_SIZE_MASK;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setOrder(int i) {
            throw new UnsupportedOperationException("Setting the order in the FlexboxLayoutManager is not supported. Use FlexboxLayout if you need to reorder using the attribute.");
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.mFlexGrow);
            parcel.writeFloat(this.mFlexShrink);
            parcel.writeInt(this.mAlignSelf);
            parcel.writeFloat(this.mFlexBasisPercent);
            parcel.writeInt(this.mMinWidth);
            parcel.writeInt(this.mMinHeight);
            parcel.writeInt(this.mMaxWidth);
            parcel.writeInt(this.mMaxHeight);
            parcel.writeByte(this.mWrapBefore ? (byte) 1 : (byte) 0);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).bottomMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).leftMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).rightMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).topMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).height);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).width);
        }

        protected LayoutParams(Parcel parcel) {
            super(-2, -2);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = ViewCompat.MEASURED_SIZE_MASK;
            this.mMaxHeight = ViewCompat.MEASURED_SIZE_MASK;
            this.mFlexGrow = parcel.readFloat();
            this.mFlexShrink = parcel.readFloat();
            this.mAlignSelf = parcel.readInt();
            this.mFlexBasisPercent = parcel.readFloat();
            this.mMinWidth = parcel.readInt();
            this.mMinHeight = parcel.readInt();
            this.mMaxWidth = parcel.readInt();
            this.mMaxHeight = parcel.readInt();
            this.mWrapBefore = parcel.readByte() != 0;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).leftMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).rightMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).topMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).height = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).width = parcel.readInt();
        }
    }

    private class AnchorInfo {
        private boolean mAssignedFromSavedState;
        private int mCoordinate;
        private int mFlexLinePosition;
        private boolean mLayoutFromEnd;
        private int mPerpendicularCoordinate;
        private int mPosition;
        private boolean mValid;

        private AnchorInfo() {
            this.mPerpendicularCoordinate = 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void reset() {
            this.mPosition = -1;
            this.mFlexLinePosition = -1;
            this.mCoordinate = Integer.MIN_VALUE;
            this.mValid = false;
            this.mAssignedFromSavedState = false;
            if (FlexboxLayoutManager.this.isMainAxisDirectionHorizontal()) {
                if (FlexboxLayoutManager.this.mFlexWrap == 0) {
                    this.mLayoutFromEnd = FlexboxLayoutManager.this.mFlexDirection == 1;
                    return;
                } else {
                    this.mLayoutFromEnd = FlexboxLayoutManager.this.mFlexWrap == 2;
                    return;
                }
            }
            if (FlexboxLayoutManager.this.mFlexWrap == 0) {
                this.mLayoutFromEnd = FlexboxLayoutManager.this.mFlexDirection == 3;
            } else {
                this.mLayoutFromEnd = FlexboxLayoutManager.this.mFlexWrap == 2;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void assignCoordinateFromPadding() {
            if (!FlexboxLayoutManager.this.isMainAxisDirectionHorizontal() && FlexboxLayoutManager.this.mIsRtl) {
                this.mCoordinate = this.mLayoutFromEnd ? FlexboxLayoutManager.this.mOrientationHelper.getEndAfterPadding() : FlexboxLayoutManager.this.getWidth() - FlexboxLayoutManager.this.mOrientationHelper.getStartAfterPadding();
            } else {
                this.mCoordinate = this.mLayoutFromEnd ? FlexboxLayoutManager.this.mOrientationHelper.getEndAfterPadding() : FlexboxLayoutManager.this.mOrientationHelper.getStartAfterPadding();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void assignFromView(View view) {
            if (!FlexboxLayoutManager.this.isMainAxisDirectionHorizontal() && FlexboxLayoutManager.this.mIsRtl) {
                if (this.mLayoutFromEnd) {
                    this.mCoordinate = FlexboxLayoutManager.this.mOrientationHelper.getDecoratedStart(view) + FlexboxLayoutManager.this.mOrientationHelper.getTotalSpaceChange();
                } else {
                    this.mCoordinate = FlexboxLayoutManager.this.mOrientationHelper.getDecoratedEnd(view);
                }
            } else if (this.mLayoutFromEnd) {
                this.mCoordinate = FlexboxLayoutManager.this.mOrientationHelper.getDecoratedEnd(view) + FlexboxLayoutManager.this.mOrientationHelper.getTotalSpaceChange();
            } else {
                this.mCoordinate = FlexboxLayoutManager.this.mOrientationHelper.getDecoratedStart(view);
            }
            this.mPosition = FlexboxLayoutManager.this.getPosition(view);
            this.mAssignedFromSavedState = false;
            int[] iArr = FlexboxLayoutManager.this.mFlexboxHelper.mIndexToFlexLine;
            int i = this.mPosition;
            if (i == -1) {
                i = 0;
            }
            int i2 = iArr[i];
            this.mFlexLinePosition = i2 != -1 ? i2 : 0;
            if (FlexboxLayoutManager.this.mFlexLines.size() > this.mFlexLinePosition) {
                this.mPosition = ((FlexLine) FlexboxLayoutManager.this.mFlexLines.get(this.mFlexLinePosition)).mFirstIndex;
            }
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.mPosition + ", mFlexLinePosition=" + this.mFlexLinePosition + ", mCoordinate=" + this.mCoordinate + ", mPerpendicularCoordinate=" + this.mPerpendicularCoordinate + ", mLayoutFromEnd=" + this.mLayoutFromEnd + ", mValid=" + this.mValid + ", mAssignedFromSavedState=" + this.mAssignedFromSavedState + '}';
        }
    }

    private static class LayoutState {
        private int mAvailable;
        private int mFlexLinePosition;
        private boolean mInfinite;
        private int mItemDirection;
        private int mLastScrollDelta;
        private int mLayoutDirection;
        private int mOffset;
        private int mPosition;
        private int mScrollingOffset;
        private boolean mShouldRecycle;

        private LayoutState() {
            this.mItemDirection = 1;
            this.mLayoutDirection = 1;
        }

        static /* synthetic */ int access$1508(LayoutState layoutState) {
            int i = layoutState.mFlexLinePosition;
            layoutState.mFlexLinePosition = i + 1;
            return i;
        }

        static /* synthetic */ int access$1510(LayoutState layoutState) {
            int i = layoutState.mFlexLinePosition;
            layoutState.mFlexLinePosition = i - 1;
            return i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasMore(RecyclerView.State state, List<FlexLine> list) {
            int i;
            int i2 = this.mPosition;
            return i2 >= 0 && i2 < state.getItemCount() && (i = this.mFlexLinePosition) >= 0 && i < list.size();
        }

        public String toString() {
            return "LayoutState{mAvailable=" + this.mAvailable + ", mFlexLinePosition=" + this.mFlexLinePosition + ", mPosition=" + this.mPosition + ", mOffset=" + this.mOffset + ", mScrollingOffset=" + this.mScrollingOffset + ", mLastScrollDelta=" + this.mLastScrollDelta + ", mItemDirection=" + this.mItemDirection + ", mLayoutDirection=" + this.mLayoutDirection + '}';
        }
    }

    private static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.google.android.flexbox.FlexboxLayoutManager.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        private int mAnchorOffset;
        private int mAnchorPosition;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mAnchorOffset);
        }

        SavedState() {
        }

        private SavedState(Parcel parcel) {
            this.mAnchorPosition = parcel.readInt();
            this.mAnchorOffset = parcel.readInt();
        }

        private SavedState(SavedState savedState) {
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mAnchorOffset = savedState.mAnchorOffset;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void invalidateAnchor() {
            this.mAnchorPosition = -1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasValidAnchor(int i) {
            int i2 = this.mAnchorPosition;
            return i2 >= 0 && i2 < i;
        }

        public String toString() {
            return "SavedState{mAnchorPosition=" + this.mAnchorPosition + ", mAnchorOffset=" + this.mAnchorOffset + '}';
        }
    }
}
