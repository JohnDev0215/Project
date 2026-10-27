package android.widget;

import android.app.slice.Slice;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Trace;
import android.util.AttributeSet;
import android.util.Log;
import android.util.MathUtils;
import android.util.SparseBooleanArray;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.RemotableViewMethod;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.ViewHierarchyEncoder;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import android.widget.AbsListView;
import android.widget.RemoteViews;
import com.android.internal.R;
import com.bydux.api.common.views.skin.entity.AttrFactory;
import com.google.android.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes3.dex */
@RemoteViews.RemoteView
public class ListView extends AbsListView {
    private static final float MAX_SCROLL_FACTOR = 0.33f;
    private static final int MIN_SCROLL_PREVIEW_PIXELS = 2;
    static final int NO_POSITION = -1;
    static final String TAG = "ListView";
    private boolean mAreAllItemsSelectable;
    private final ArrowScrollFocusResult mArrowScrollFocusResult;
    Drawable mDivider;
    int mDividerHeight;
    private boolean mDividerIsOpaque;
    private Paint mDividerPaint;
    private FocusSelector mFocusSelector;
    private boolean mFooterDividersEnabled;
    ArrayList<FixedViewInfo> mFooterViewInfos;
    private boolean mHeaderDividersEnabled;
    ArrayList<FixedViewInfo> mHeaderViewInfos;
    private boolean mIsCacheColorOpaque;
    private boolean mItemsCanFocus;
    Drawable mOverScrollFooter;
    Drawable mOverScrollHeader;
    private final Rect mTempRect;

    private int getTopSelectionPixel(int i, int i2, int i3) {
        return i3 > 0 ? i + i2 : i;
    }

    @ViewDebug.ExportedProperty(category = Slice.HINT_LIST)
    protected boolean recycleOnMeasure() {
        return true;
    }

    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion<ListView> {
        private int mDividerHeightId;
        private int mDividerId;
        private int mFooterDividersEnabledId;
        private int mHeaderDividersEnabledId;
        private boolean mPropertiesMapped = false;

        @Override // android.view.inspector.InspectionCompanion
        public void mapProperties(PropertyMapper propertyMapper) {
            this.mDividerId = propertyMapper.mapObject(AttrFactory.DIVIDER, 16843049);
            this.mDividerHeightId = propertyMapper.mapInt("dividerHeight", 16843050);
            this.mFooterDividersEnabledId = propertyMapper.mapBoolean("footerDividersEnabled", 16843311);
            this.mHeaderDividersEnabledId = propertyMapper.mapBoolean("headerDividersEnabled", 16843310);
            this.mPropertiesMapped = true;
        }

        @Override // android.view.inspector.InspectionCompanion
        public void readProperties(ListView listView, PropertyReader propertyReader) {
            if (!this.mPropertiesMapped) {
                throw new InspectionCompanion.UninitializedPropertyMapException();
            }
            propertyReader.readObject(this.mDividerId, listView.getDivider());
            propertyReader.readInt(this.mDividerHeightId, listView.getDividerHeight());
            propertyReader.readBoolean(this.mFooterDividersEnabledId, listView.areFooterDividersEnabled());
            propertyReader.readBoolean(this.mHeaderDividersEnabledId, listView.areHeaderDividersEnabled());
        }
    }

    public class FixedViewInfo {
        public Object data;
        public boolean isSelectable;
        public View view;

        public FixedViewInfo() {
        }
    }

    public ListView(Context context) {
        this(context, null);
    }

    public ListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842868);
    }

    public ListView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ListView(Context context, AttributeSet attributeSet, int i, int i2) {
        int dimensionPixelSize;
        super(context, attributeSet, i, i2);
        this.mHeaderViewInfos = Lists.newArrayList();
        this.mFooterViewInfos = Lists.newArrayList();
        this.mAreAllItemsSelectable = true;
        this.mItemsCanFocus = false;
        this.mTempRect = new Rect();
        this.mArrowScrollFocusResult = new ArrowScrollFocusResult();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ListView, i, i2);
        saveAttributeDataForStyleable(context, R.styleable.ListView, attributeSet, typedArrayObtainStyledAttributes, i, i2);
        CharSequence[] textArray = typedArrayObtainStyledAttributes.getTextArray(0);
        if (textArray != null) {
            setAdapter((ListAdapter) new ArrayAdapter(context, 17367043, textArray));
        }
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        if (drawable != null) {
            setDivider(drawable);
        }
        Drawable drawable2 = typedArrayObtainStyledAttributes.getDrawable(5);
        if (drawable2 != null) {
            setOverscrollHeader(drawable2);
        }
        Drawable drawable3 = typedArrayObtainStyledAttributes.getDrawable(6);
        if (drawable3 != null) {
            setOverscrollFooter(drawable3);
        }
        if (typedArrayObtainStyledAttributes.hasValueOrEmpty(2) && (dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, 0)) != 0) {
            setDividerHeight(dimensionPixelSize);
        }
        this.mHeaderDividersEnabled = typedArrayObtainStyledAttributes.getBoolean(3, true);
        this.mFooterDividersEnabled = typedArrayObtainStyledAttributes.getBoolean(4, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    public int getMaxScrollAmount() {
        return (int) ((this.mBottom - this.mTop) * MAX_SCROLL_FACTOR);
    }

    private void adjustViewsUpOrDown() {
        int childCount = getChildCount();
        if (childCount > 0) {
            int i = 0;
            if (!this.mStackFromBottom) {
                int top = getChildAt(0).getTop() - this.mListPadding.top;
                if (this.mFirstPosition != 0) {
                    top -= this.mDividerHeight;
                }
                if (top >= 0) {
                    i = top;
                }
            } else {
                int bottom = getChildAt(childCount - 1).getBottom() - (getHeight() - this.mListPadding.bottom);
                if (this.mFirstPosition + childCount < this.mItemCount) {
                    bottom += this.mDividerHeight;
                }
                if (bottom <= 0) {
                    i = bottom;
                }
            }
            if (i != 0) {
                offsetChildrenTopAndBottom(-i);
            }
        }
    }

    public void addHeaderView(View view, Object obj, boolean z) {
        if (view.getParent() != null && view.getParent() != this && Log.isLoggable(TAG, 5)) {
            Log.w(TAG, "The specified child already has a parent. You must call removeView() on the child's parent first.");
        }
        FixedViewInfo fixedViewInfo = new FixedViewInfo();
        fixedViewInfo.view = view;
        fixedViewInfo.data = obj;
        fixedViewInfo.isSelectable = z;
        this.mHeaderViewInfos.add(fixedViewInfo);
        this.mAreAllItemsSelectable &= z;
        if (this.mAdapter != null) {
            if (!(this.mAdapter instanceof HeaderViewListAdapter)) {
                wrapHeaderListAdapterInternal();
            }
            if (this.mDataSetObserver != null) {
                this.mDataSetObserver.onChanged();
            }
        }
    }

    public void addHeaderView(View view) {
        addHeaderView(view, null, true);
    }

    @Override // android.widget.AbsListView
    public int getHeaderViewsCount() {
        return this.mHeaderViewInfos.size();
    }

    public boolean removeHeaderView(View view) {
        boolean z = false;
        if (this.mHeaderViewInfos.size() > 0) {
            if (this.mAdapter != null && ((HeaderViewListAdapter) this.mAdapter).removeHeader(view)) {
                if (this.mDataSetObserver != null) {
                    this.mDataSetObserver.onChanged();
                }
                z = true;
            }
            removeFixedViewInfo(view, this.mHeaderViewInfos);
        }
        return z;
    }

    private void removeFixedViewInfo(View view, ArrayList<FixedViewInfo> arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (arrayList.get(i).view == view) {
                arrayList.remove(i);
                return;
            }
        }
    }

    public void addFooterView(View view, Object obj, boolean z) {
        if (view.getParent() != null && view.getParent() != this && Log.isLoggable(TAG, 5)) {
            Log.w(TAG, "The specified child already has a parent. You must call removeView() on the child's parent first.");
        }
        FixedViewInfo fixedViewInfo = new FixedViewInfo();
        fixedViewInfo.view = view;
        fixedViewInfo.data = obj;
        fixedViewInfo.isSelectable = z;
        this.mFooterViewInfos.add(fixedViewInfo);
        this.mAreAllItemsSelectable &= z;
        if (this.mAdapter != null) {
            if (!(this.mAdapter instanceof HeaderViewListAdapter)) {
                wrapHeaderListAdapterInternal();
            }
            if (this.mDataSetObserver != null) {
                this.mDataSetObserver.onChanged();
            }
        }
    }

    public void addFooterView(View view) {
        addFooterView(view, null, true);
    }

    @Override // android.widget.AbsListView
    public int getFooterViewsCount() {
        return this.mFooterViewInfos.size();
    }

    public boolean removeFooterView(View view) {
        boolean z = false;
        if (this.mFooterViewInfos.size() > 0) {
            if (this.mAdapter != null && ((HeaderViewListAdapter) this.mAdapter).removeFooter(view)) {
                if (this.mDataSetObserver != null) {
                    this.mDataSetObserver.onChanged();
                }
                z = true;
            }
            removeFixedViewInfo(view, this.mFooterViewInfos);
        }
        return z;
    }

    @Override // android.widget.AdapterView
    public ListAdapter getAdapter() {
        return this.mAdapter;
    }

    @Override // android.widget.AbsListView
    @RemotableViewMethod(asyncImpl = "setRemoteViewsAdapterAsync")
    public void setRemoteViewsAdapter(Intent intent) {
        super.setRemoteViewsAdapter(intent);
    }

    @Override // android.widget.AbsListView, android.widget.AdapterView
    public void setAdapter(ListAdapter listAdapter) {
        int iLookForSelectablePosition;
        if (this.mAdapter != null && this.mDataSetObserver != null) {
            this.mAdapter.unregisterDataSetObserver(this.mDataSetObserver);
        }
        resetList();
        this.mRecycler.clear();
        if (this.mHeaderViewInfos.size() > 0 || this.mFooterViewInfos.size() > 0) {
            this.mAdapter = wrapHeaderListAdapterInternal(this.mHeaderViewInfos, this.mFooterViewInfos, listAdapter);
        } else {
            this.mAdapter = listAdapter;
        }
        this.mOldSelectedPosition = -1;
        this.mOldSelectedRowId = Long.MIN_VALUE;
        super.setAdapter(listAdapter);
        if (this.mAdapter != null) {
            this.mAreAllItemsSelectable = this.mAdapter.areAllItemsEnabled();
            this.mOldItemCount = this.mItemCount;
            this.mItemCount = this.mAdapter.getCount();
            checkFocus();
            this.mDataSetObserver = new AbsListView.AdapterDataSetObserver();
            this.mAdapter.registerDataSetObserver(this.mDataSetObserver);
            this.mRecycler.setViewTypeCount(this.mAdapter.getViewTypeCount());
            if (this.mStackFromBottom) {
                iLookForSelectablePosition = lookForSelectablePosition(this.mItemCount - 1, false);
            } else {
                iLookForSelectablePosition = lookForSelectablePosition(0, true);
            }
            setSelectedPositionInt(iLookForSelectablePosition);
            setNextSelectedPositionInt(iLookForSelectablePosition);
            if (this.mItemCount == 0) {
                checkSelectionChanged();
            }
        } else {
            this.mAreAllItemsSelectable = true;
            checkFocus();
            checkSelectionChanged();
        }
        requestLayout();
    }

    @Override // android.widget.AbsListView
    void resetList() {
        clearRecycledState(this.mHeaderViewInfos);
        clearRecycledState(this.mFooterViewInfos);
        super.resetList();
        this.mLayoutMode = 0;
    }

    private void clearRecycledState(ArrayList<FixedViewInfo> arrayList) {
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ViewGroup.LayoutParams layoutParams = arrayList.get(i).view.getLayoutParams();
                if (checkLayoutParams(layoutParams)) {
                    ((AbsListView.LayoutParams) layoutParams).recycledHeaderFooter = false;
                }
            }
        }
    }

    private boolean showingTopFadingEdge() {
        return this.mFirstPosition > 0 || getChildAt(0).getTop() > this.mScrollY + this.mListPadding.top;
    }

    private boolean showingBottomFadingEdge() {
        int childCount = getChildCount();
        return (this.mFirstPosition + childCount) - 1 < this.mItemCount - 1 || getChildAt(childCount + (-1)).getBottom() < (this.mScrollY + getHeight()) - this.mListPadding.bottom;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        int iMax;
        int i;
        int i2;
        int i3 = rect.top;
        rect.offset(view.getLeft(), view.getTop());
        rect.offset(-view.getScrollX(), -view.getScrollY());
        int height = getHeight();
        int scrollY = getScrollY();
        int i4 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (showingTopFadingEdge() && (this.mSelectedPosition > 0 || i3 > verticalFadingEdgeLength)) {
            scrollY += verticalFadingEdgeLength;
        }
        int bottom = getChildAt(getChildCount() - 1).getBottom();
        if (showingBottomFadingEdge() && (this.mSelectedPosition < this.mItemCount - 1 || rect.bottom < bottom - verticalFadingEdgeLength)) {
            i4 -= verticalFadingEdgeLength;
        }
        if (rect.bottom > i4 && rect.top > scrollY) {
            if (rect.height() > height) {
                i2 = rect.top - scrollY;
            } else {
                i2 = rect.bottom - i4;
            }
            iMax = Math.min(i2 + 0, bottom - i4);
        } else if (rect.top >= scrollY || rect.bottom >= i4) {
            iMax = 0;
        } else {
            if (rect.height() > height) {
                i = 0 - (i4 - rect.bottom);
            } else {
                i = 0 - (scrollY - rect.top);
            }
            iMax = Math.max(i, getChildAt(0).getTop() - scrollY);
        }
        boolean z2 = iMax != 0;
        if (z2) {
            scrollListItemsBy(-iMax);
            positionSelector(-1, view);
            this.mSelectedTop = view.getTop();
            invalidate();
        }
        return z2;
    }

    @Override // android.widget.AbsListView
    void fillGap(boolean z) {
        int height;
        int childCount = getChildCount();
        if (z) {
            int listPaddingTop = (this.mGroupFlags & 34) == 34 ? getListPaddingTop() : 0;
            if (childCount > 0) {
                listPaddingTop = this.mDividerHeight + getChildAt(childCount - 1).getBottom();
            }
            fillDown(this.mFirstPosition + childCount, listPaddingTop);
            correctTooHigh(getChildCount());
            return;
        }
        int listPaddingBottom = (this.mGroupFlags & 34) == 34 ? getListPaddingBottom() : 0;
        if (childCount > 0) {
            height = getChildAt(0).getTop() - this.mDividerHeight;
        } else {
            height = getHeight() - listPaddingBottom;
        }
        fillUp(this.mFirstPosition - 1, height);
        correctTooLow(getChildCount());
    }

    private View fillDown(int i, int i2) {
        int i3 = this.mBottom - this.mTop;
        View view = null;
        if ((this.mGroupFlags & 34) == 34) {
            i3 -= this.mListPadding.bottom;
        }
        int bottom = i2;
        while (true) {
            if (bottom >= i3 || i >= this.mItemCount) {
                break;
            }
            boolean z = i == this.mSelectedPosition;
            View viewMakeAndAddView = makeAndAddView(i, bottom, true, this.mListPadding.left, z);
            bottom = viewMakeAndAddView.getBottom() + this.mDividerHeight;
            if (z) {
                view = viewMakeAndAddView;
            }
            i++;
        }
        setVisibleRangeHint(this.mFirstPosition, (this.mFirstPosition + getChildCount()) - 1);
        return view;
    }

    private View fillUp(int i, int i2) {
        int top;
        int i3;
        View view = null;
        if ((this.mGroupFlags & 34) == 34) {
            i3 = this.mListPadding.top;
            top = i2;
        } else {
            top = i2;
            i3 = 0;
        }
        while (true) {
            if (top <= i3 || i < 0) {
                break;
            }
            boolean z = i == this.mSelectedPosition;
            View viewMakeAndAddView = makeAndAddView(i, top, false, this.mListPadding.left, z);
            top = viewMakeAndAddView.getTop() - this.mDividerHeight;
            if (z) {
                view = viewMakeAndAddView;
            }
            i--;
        }
        this.mFirstPosition = i + 1;
        setVisibleRangeHint(this.mFirstPosition, (this.mFirstPosition + getChildCount()) - 1);
        return view;
    }

    private View fillFromTop(int i) {
        this.mFirstPosition = Math.min(this.mFirstPosition, this.mSelectedPosition);
        this.mFirstPosition = Math.min(this.mFirstPosition, this.mItemCount - 1);
        if (this.mFirstPosition < 0) {
            this.mFirstPosition = 0;
        }
        return fillDown(this.mFirstPosition, i);
    }

    private View fillFromMiddle(int i, int i2) {
        int i3 = i2 - i;
        int iReconcileSelectedPosition = reconcileSelectedPosition();
        View viewMakeAndAddView = makeAndAddView(iReconcileSelectedPosition, i, true, this.mListPadding.left, true);
        this.mFirstPosition = iReconcileSelectedPosition;
        int measuredHeight = viewMakeAndAddView.getMeasuredHeight();
        if (measuredHeight <= i3) {
            viewMakeAndAddView.offsetTopAndBottom((i3 - measuredHeight) / 2);
        }
        fillAboveAndBelow(viewMakeAndAddView, iReconcileSelectedPosition);
        if (!this.mStackFromBottom) {
            correctTooHigh(getChildCount());
        } else {
            correctTooLow(getChildCount());
        }
        return viewMakeAndAddView;
    }

    private void fillAboveAndBelow(View view, int i) {
        int i2 = this.mDividerHeight;
        if (!this.mStackFromBottom) {
            fillUp(i - 1, view.getTop() - i2);
            adjustViewsUpOrDown();
            fillDown(i + 1, view.getBottom() + i2);
        } else {
            fillDown(i + 1, view.getBottom() + i2);
            adjustViewsUpOrDown();
            fillUp(i - 1, view.getTop() - i2);
        }
    }

    private View fillFromSelection(int i, int i2, int i3) {
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int i4 = this.mSelectedPosition;
        int topSelectionPixel = getTopSelectionPixel(i2, verticalFadingEdgeLength, i4);
        int bottomSelectionPixel = getBottomSelectionPixel(i3, verticalFadingEdgeLength, i4);
        View viewMakeAndAddView = makeAndAddView(i4, i, true, this.mListPadding.left, true);
        if (viewMakeAndAddView.getBottom() > bottomSelectionPixel) {
            viewMakeAndAddView.offsetTopAndBottom(-Math.min(viewMakeAndAddView.getTop() - topSelectionPixel, viewMakeAndAddView.getBottom() - bottomSelectionPixel));
        } else if (viewMakeAndAddView.getTop() < topSelectionPixel) {
            viewMakeAndAddView.offsetTopAndBottom(Math.min(topSelectionPixel - viewMakeAndAddView.getTop(), bottomSelectionPixel - viewMakeAndAddView.getBottom()));
        }
        fillAboveAndBelow(viewMakeAndAddView, i4);
        if (!this.mStackFromBottom) {
            correctTooHigh(getChildCount());
        } else {
            correctTooLow(getChildCount());
        }
        return viewMakeAndAddView;
    }

    private int getBottomSelectionPixel(int i, int i2, int i3) {
        return i3 != this.mItemCount + (-1) ? i - i2 : i;
    }

    @Override // android.widget.AbsListView
    @RemotableViewMethod
    public void smoothScrollToPosition(int i) {
        super.smoothScrollToPosition(i);
    }

    @Override // android.widget.AbsListView
    @RemotableViewMethod
    public void smoothScrollByOffset(int i) {
        super.smoothScrollByOffset(i);
    }

    private View moveSelection(View view, View view2, int i, int i2, int i3) {
        View viewMakeAndAddView;
        View viewMakeAndAddView2;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int i4 = this.mSelectedPosition;
        int topSelectionPixel = getTopSelectionPixel(i2, verticalFadingEdgeLength, i4);
        int bottomSelectionPixel = getBottomSelectionPixel(i2, verticalFadingEdgeLength, i4);
        if (i > 0) {
            View viewMakeAndAddView3 = makeAndAddView(i4 - 1, view.getTop(), true, this.mListPadding.left, false);
            int i5 = this.mDividerHeight;
            viewMakeAndAddView = makeAndAddView(i4, viewMakeAndAddView3.getBottom() + i5, true, this.mListPadding.left, true);
            if (viewMakeAndAddView.getBottom() > bottomSelectionPixel) {
                int i6 = -Math.min(Math.min(viewMakeAndAddView.getTop() - topSelectionPixel, viewMakeAndAddView.getBottom() - bottomSelectionPixel), (i3 - i2) / 2);
                viewMakeAndAddView3.offsetTopAndBottom(i6);
                viewMakeAndAddView.offsetTopAndBottom(i6);
            }
            if (!this.mStackFromBottom) {
                fillUp(this.mSelectedPosition - 2, viewMakeAndAddView.getTop() - i5);
                adjustViewsUpOrDown();
                fillDown(this.mSelectedPosition + 1, viewMakeAndAddView.getBottom() + i5);
            } else {
                fillDown(this.mSelectedPosition + 1, viewMakeAndAddView.getBottom() + i5);
                adjustViewsUpOrDown();
                fillUp(this.mSelectedPosition - 2, viewMakeAndAddView.getTop() - i5);
            }
        } else if (i < 0) {
            if (view2 != null) {
                viewMakeAndAddView2 = makeAndAddView(i4, view2.getTop(), true, this.mListPadding.left, true);
            } else {
                viewMakeAndAddView2 = makeAndAddView(i4, view.getTop(), false, this.mListPadding.left, true);
            }
            viewMakeAndAddView = viewMakeAndAddView2;
            if (viewMakeAndAddView.getTop() < topSelectionPixel) {
                viewMakeAndAddView.offsetTopAndBottom(Math.min(Math.min(topSelectionPixel - viewMakeAndAddView.getTop(), bottomSelectionPixel - viewMakeAndAddView.getBottom()), (i3 - i2) / 2));
            }
            fillAboveAndBelow(viewMakeAndAddView, i4);
        } else {
            int top = view.getTop();
            viewMakeAndAddView = makeAndAddView(i4, top, true, this.mListPadding.left, true);
            if (top < i2 && viewMakeAndAddView.getBottom() < i2 + 20) {
                viewMakeAndAddView.offsetTopAndBottom(i2 - viewMakeAndAddView.getTop());
            }
            fillAboveAndBelow(viewMakeAndAddView, i4);
        }
        return viewMakeAndAddView;
    }

    private class FocusSelector implements Runnable {
        private static final int STATE_REQUEST_FOCUS = 3;
        private static final int STATE_SET_SELECTION = 1;
        private static final int STATE_WAIT_FOR_LAYOUT = 2;
        private int mAction;
        private int mPosition;
        private int mPositionTop;

        private FocusSelector() {
        }

        FocusSelector setupForSetSelection(int i, int i2) {
            this.mPosition = i;
            this.mPositionTop = i2;
            this.mAction = 1;
            return this;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i = this.mAction;
            if (i == 1) {
                ListView.this.setSelectionFromTop(this.mPosition, this.mPositionTop);
                this.mAction = 2;
            } else if (i == 3) {
                View childAt = ListView.this.getChildAt(this.mPosition - ListView.this.mFirstPosition);
                if (childAt != null) {
                    childAt.requestFocus();
                }
                this.mAction = -1;
            }
        }

        Runnable setupFocusIfValid(int i) {
            if (this.mAction != 2 || i != this.mPosition) {
                return null;
            }
            this.mAction = 3;
            return this;
        }

        void onLayoutComplete() {
            if (this.mAction == 2) {
                this.mAction = -1;
            }
        }
    }

    @Override // android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        FocusSelector focusSelector = this.mFocusSelector;
        if (focusSelector != null) {
            removeCallbacks(focusSelector);
            this.mFocusSelector = null;
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.AbsListView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        View focusedChild;
        if (getChildCount() > 0 && (focusedChild = getFocusedChild()) != null) {
            int iIndexOfChild = this.mFirstPosition + indexOfChild(focusedChild);
            int top = focusedChild.getTop() - Math.max(0, focusedChild.getBottom() - (i2 - this.mPaddingTop));
            if (this.mFocusSelector == null) {
                this.mFocusSelector = new FocusSelector();
            }
            post(this.mFocusSelector.setupForSetSelection(iIndexOfChild, top));
        }
        super.onSizeChanged(i, i2, i3, i4);
    }

    @Override // android.widget.AbsListView, android.view.View
    protected void onMeasure(int i, int i2) {
        int measuredWidth;
        int measuredHeight;
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int i3 = 0;
        this.mItemCount = this.mAdapter == null ? 0 : this.mAdapter.getCount();
        if (this.mItemCount <= 0 || !(mode == 0 || mode2 == 0)) {
            measuredWidth = 0;
            measuredHeight = 0;
        } else {
            View viewObtainView = obtainView(0, this.mIsScrap);
            measureScrapChild(viewObtainView, 0, i, size2);
            measuredWidth = viewObtainView.getMeasuredWidth();
            measuredHeight = viewObtainView.getMeasuredHeight();
            int iCombineMeasuredStates = combineMeasuredStates(0, viewObtainView.getMeasuredState());
            if (recycleOnMeasure() && this.mRecycler.shouldRecycleViewType(((AbsListView.LayoutParams) viewObtainView.getLayoutParams()).viewType)) {
                this.mRecycler.addScrapView(viewObtainView, 0);
            }
            i3 = iCombineMeasuredStates;
        }
        int verticalScrollbarWidth = mode == 0 ? this.mListPadding.left + this.mListPadding.right + measuredWidth + getVerticalScrollbarWidth() : ((-16777216) & i3) | size;
        if (mode2 == 0) {
            size2 = this.mListPadding.top + this.mListPadding.bottom + measuredHeight + (getVerticalFadingEdgeLength() * 2);
        }
        int iMeasureHeightOfChildren = size2;
        if (mode2 == Integer.MIN_VALUE) {
            iMeasureHeightOfChildren = measureHeightOfChildren(i, 0, -1, iMeasureHeightOfChildren, -1);
        }
        setMeasuredDimension(verticalScrollbarWidth, iMeasureHeightOfChildren);
        this.mWidthMeasureSpec = i;
    }

    private void measureScrapChild(View view, int i, int i2, int i3) {
        int iMakeSafeMeasureSpec;
        AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = (AbsListView.LayoutParams) generateDefaultLayoutParams();
            view.setLayoutParams(layoutParams);
        }
        layoutParams.viewType = this.mAdapter.getItemViewType(i);
        layoutParams.isEnabled = this.mAdapter.isEnabled(i);
        layoutParams.forceAdd = true;
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i2, this.mListPadding.left + this.mListPadding.right, layoutParams.width);
        int i4 = layoutParams.height;
        if (i4 > 0) {
            iMakeSafeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i4, 1073741824);
        } else {
            iMakeSafeMeasureSpec = View.MeasureSpec.makeSafeMeasureSpec(i3, 0);
        }
        view.measure(childMeasureSpec, iMakeSafeMeasureSpec);
        view.forceLayout();
    }

    final int measureHeightOfChildren(int i, int i2, int i3, int i4, int i5) {
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter == null) {
            return this.mListPadding.top + this.mListPadding.bottom;
        }
        int measuredHeight = this.mListPadding.top + this.mListPadding.bottom;
        int i6 = this.mDividerHeight;
        int i7 = 0;
        if (i3 == -1) {
            i3 = listAdapter.getCount() - 1;
        }
        AbsListView.RecycleBin recycleBin = this.mRecycler;
        boolean zRecycleOnMeasure = recycleOnMeasure();
        boolean[] zArr = this.mIsScrap;
        while (i2 <= i3) {
            View viewObtainView = obtainView(i2, zArr);
            measureScrapChild(viewObtainView, i2, i, i4);
            if (i2 > 0) {
                measuredHeight += i6;
            }
            if (zRecycleOnMeasure && recycleBin.shouldRecycleViewType(((AbsListView.LayoutParams) viewObtainView.getLayoutParams()).viewType)) {
                recycleBin.addScrapView(viewObtainView, -1);
            }
            measuredHeight += viewObtainView.getMeasuredHeight();
            if (measuredHeight >= i4) {
                return (i5 < 0 || i2 <= i5 || i7 <= 0 || measuredHeight == i4) ? i4 : i7;
            }
            if (i5 >= 0 && i2 >= i5) {
                i7 = measuredHeight;
            }
            i2++;
        }
        return measuredHeight;
    }

    @Override // android.widget.AbsListView
    int findMotionRow(int i) {
        int childCount = getChildCount();
        if (childCount <= 0) {
            return -1;
        }
        if (this.mStackFromBottom) {
            for (int i2 = childCount - 1; i2 >= 0; i2--) {
                if (i >= getChildAt(i2).getTop()) {
                    return this.mFirstPosition + i2;
                }
            }
            return -1;
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            if (i <= getChildAt(i3).getBottom()) {
                return this.mFirstPosition + i3;
            }
        }
        return -1;
    }

    private View fillSpecific(int i, int i2) {
        View viewFillUp;
        View viewFillDown;
        boolean z = i == this.mSelectedPosition;
        View viewMakeAndAddView = makeAndAddView(i, i2, true, this.mListPadding.left, z);
        this.mFirstPosition = i;
        int i3 = this.mDividerHeight;
        if (!this.mStackFromBottom) {
            viewFillUp = fillUp(i - 1, viewMakeAndAddView.getTop() - i3);
            adjustViewsUpOrDown();
            viewFillDown = fillDown(i + 1, viewMakeAndAddView.getBottom() + i3);
            int childCount = getChildCount();
            if (childCount > 0) {
                correctTooHigh(childCount);
            }
        } else {
            View viewFillDown2 = fillDown(i + 1, viewMakeAndAddView.getBottom() + i3);
            adjustViewsUpOrDown();
            View viewFillUp2 = fillUp(i - 1, viewMakeAndAddView.getTop() - i3);
            int childCount2 = getChildCount();
            if (childCount2 > 0) {
                correctTooLow(childCount2);
            }
            viewFillUp = viewFillUp2;
            viewFillDown = viewFillDown2;
        }
        return z ? viewMakeAndAddView : viewFillUp != null ? viewFillUp : viewFillDown;
    }

    private void correctTooHigh(int i) {
        if ((this.mFirstPosition + i) - 1 != this.mItemCount - 1 || i <= 0) {
            return;
        }
        int bottom = ((this.mBottom - this.mTop) - this.mListPadding.bottom) - getChildAt(i - 1).getBottom();
        View childAt = getChildAt(0);
        int top = childAt.getTop();
        if (bottom > 0) {
            if (this.mFirstPosition > 0 || top < this.mListPadding.top) {
                if (this.mFirstPosition == 0) {
                    bottom = Math.min(bottom, this.mListPadding.top - top);
                }
                offsetChildrenTopAndBottom(bottom);
                if (this.mFirstPosition > 0) {
                    fillUp(this.mFirstPosition - 1, childAt.getTop() - this.mDividerHeight);
                    adjustViewsUpOrDown();
                }
            }
        }
    }

    private void correctTooLow(int i) {
        if (this.mFirstPosition != 0 || i <= 0) {
            return;
        }
        int top = getChildAt(0).getTop();
        int i2 = this.mListPadding.top;
        int i3 = (this.mBottom - this.mTop) - this.mListPadding.bottom;
        int iMin = top - i2;
        View childAt = getChildAt(i - 1);
        int bottom = childAt.getBottom();
        int i4 = (this.mFirstPosition + i) - 1;
        if (iMin > 0) {
            if (i4 < this.mItemCount - 1 || bottom > i3) {
                if (i4 == this.mItemCount - 1) {
                    iMin = Math.min(iMin, bottom - i3);
                }
                offsetChildrenTopAndBottom(-iMin);
                if (i4 < this.mItemCount - 1) {
                    fillDown(i4 + 1, childAt.getBottom() + this.mDividerHeight);
                    adjustViewsUpOrDown();
                    return;
                }
                return;
            }
            if (i4 == this.mItemCount - 1) {
                adjustViewsUpOrDown();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0091 A[Catch: all -> 0x036b, TryCatch #1 {all -> 0x036b, blocks: (B:6:0x000b, B:8:0x0015, B:15:0x0027, B:24:0x004a, B:27:0x0053, B:29:0x0059, B:31:0x0061, B:33:0x0068, B:40:0x008d, B:42:0x0091, B:43:0x0094, B:45:0x0098, B:52:0x00aa, B:54:0x00b4, B:56:0x00bf, B:58:0x00c5, B:61:0x00cd, B:63:0x00d3, B:65:0x00d9, B:70:0x00e5, B:72:0x00ed, B:75:0x00f5, B:77:0x00fb, B:79:0x0101, B:86:0x0112, B:83:0x0109, B:85:0x010f, B:69:0x00e1, B:34:0x0078, B:37:0x0081), top: B:227:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0098 A[Catch: all -> 0x036b, TRY_LEAVE, TryCatch #1 {all -> 0x036b, blocks: (B:6:0x000b, B:8:0x0015, B:15:0x0027, B:24:0x004a, B:27:0x0053, B:29:0x0059, B:31:0x0061, B:33:0x0068, B:40:0x008d, B:42:0x0091, B:43:0x0094, B:45:0x0098, B:52:0x00aa, B:54:0x00b4, B:56:0x00bf, B:58:0x00c5, B:61:0x00cd, B:63:0x00d3, B:65:0x00d9, B:70:0x00e5, B:72:0x00ed, B:75:0x00f5, B:77:0x00fb, B:79:0x0101, B:86:0x0112, B:83:0x0109, B:85:0x010f, B:69:0x00e1, B:34:0x0078, B:37:0x0081), top: B:227:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00aa A[Catch: all -> 0x036b, TRY_ENTER, TryCatch #1 {all -> 0x036b, blocks: (B:6:0x000b, B:8:0x0015, B:15:0x0027, B:24:0x004a, B:27:0x0053, B:29:0x0059, B:31:0x0061, B:33:0x0068, B:40:0x008d, B:42:0x0091, B:43:0x0094, B:45:0x0098, B:52:0x00aa, B:54:0x00b4, B:56:0x00bf, B:58:0x00c5, B:61:0x00cd, B:63:0x00d3, B:65:0x00d9, B:70:0x00e5, B:72:0x00ed, B:75:0x00f5, B:77:0x00fb, B:79:0x0101, B:86:0x0112, B:83:0x0109, B:85:0x010f, B:69:0x00e1, B:34:0x0078, B:37:0x0081), top: B:227:0x000b }] */
    @Override // android.widget.AbsListView
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void layoutChildren() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 908
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.ListView.layoutChildren():void");
    }

    @Override // android.widget.AbsListView
    boolean trackMotionScroll(int i, int i2) {
        boolean zTrackMotionScroll = super.trackMotionScroll(i, i2);
        removeUnusedFixedViews(this.mHeaderViewInfos);
        removeUnusedFixedViews(this.mFooterViewInfos);
        return zTrackMotionScroll;
    }

    private void removeUnusedFixedViews(List<FixedViewInfo> list) {
        if (list == null) {
            return;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            View view = list.get(size).view;
            AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) view.getLayoutParams();
            if (view.getParent() == null && layoutParams != null && layoutParams.recycledHeaderFooter) {
                removeDetachedView(view, false);
                layoutParams.recycledHeaderFooter = false;
            }
        }
    }

    private boolean isDirectChildHeaderOrFooter(View view) {
        ArrayList<FixedViewInfo> arrayList = this.mHeaderViewInfos;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (view == arrayList.get(i).view) {
                return true;
            }
        }
        ArrayList<FixedViewInfo> arrayList2 = this.mFooterViewInfos;
        int size2 = arrayList2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            if (view == arrayList2.get(i2).view) {
                return true;
            }
        }
        return false;
    }

    private View makeAndAddView(int i, int i2, boolean z, int i3, boolean z2) {
        View activeView;
        if (!this.mDataChanged && (activeView = this.mRecycler.getActiveView(i)) != null) {
            setupChild(activeView, i, i2, z, i3, z2, true);
            return activeView;
        }
        View viewObtainView = obtainView(i, this.mIsScrap);
        setupChild(viewObtainView, i, i2, z, i3, z2, this.mIsScrap[0]);
        return viewObtainView;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void setupChild(View view, int i, int i2, boolean z, int i3, boolean z2, boolean z3) {
        int iMakeSafeMeasureSpec;
        Trace.traceBegin(8L, "setupListItem");
        boolean z4 = z2 && shouldShowSelector();
        boolean z5 = z4 != view.isSelected();
        int i4 = this.mTouchMode;
        boolean z6 = i4 > 0 && i4 < 3 && this.mMotionPosition == i;
        boolean z7 = z6 != view.isPressed();
        boolean z8 = !z3 || z5 || view.isLayoutRequested();
        AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = (AbsListView.LayoutParams) generateDefaultLayoutParams();
        }
        layoutParams.viewType = this.mAdapter.getItemViewType(i);
        layoutParams.isEnabled = this.mAdapter.isEnabled(i);
        if (z5) {
            view.setSelected(z4);
        }
        if (z7) {
            view.setPressed(z6);
        }
        if (this.mChoiceMode != 0 && this.mCheckStates != null) {
            if (view instanceof Checkable) {
                ((Checkable) view).setChecked(this.mCheckStates.get(i));
            } else if (getContext().getApplicationInfo().targetSdkVersion >= 11) {
                view.setActivated(this.mCheckStates.get(i));
            }
        }
        if ((z3 && !layoutParams.forceAdd) || (layoutParams.recycledHeaderFooter && layoutParams.viewType == -2)) {
            attachViewToParent(view, z ? -1 : 0, layoutParams);
            if (z3 && ((AbsListView.LayoutParams) view.getLayoutParams()).scrappedFromPosition != i) {
                view.jumpDrawablesToCurrentState();
            }
        } else {
            layoutParams.forceAdd = false;
            if (layoutParams.viewType == -2) {
                layoutParams.recycledHeaderFooter = true;
            }
            addViewInLayout(view, z ? -1 : 0, layoutParams, true);
            view.resolveRtlPropertiesIfNeeded();
        }
        if (z8) {
            int childMeasureSpec = ViewGroup.getChildMeasureSpec(this.mWidthMeasureSpec, this.mListPadding.left + this.mListPadding.right, layoutParams.width);
            int i5 = layoutParams.height;
            if (i5 > 0) {
                iMakeSafeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
            } else {
                iMakeSafeMeasureSpec = View.MeasureSpec.makeSafeMeasureSpec(getMeasuredHeight(), 0);
            }
            view.measure(childMeasureSpec, iMakeSafeMeasureSpec);
        } else {
            cleanupLayoutState(view);
        }
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i6 = z ? i2 : i2 - measuredHeight;
        if (z8) {
            view.layout(i3, i6, measuredWidth + i3, measuredHeight + i6);
        } else {
            view.offsetLeftAndRight(i3 - view.getLeft());
            view.offsetTopAndBottom(i6 - view.getTop());
        }
        if (this.mCachingStarted && !view.isDrawingCacheEnabled()) {
            view.setDrawingCacheEnabled(true);
        }
        Trace.traceEnd(8L);
    }

    @Override // android.widget.AdapterView, android.view.ViewGroup
    protected boolean canAnimate() {
        return super.canAnimate() && this.mItemCount > 0;
    }

    @Override // android.widget.AdapterView
    public void setSelection(int i) {
        setSelectionFromTop(i, 0);
    }

    @Override // android.widget.AbsListView
    void setSelectionInt(int i) throws Throwable {
        setNextSelectedPositionInt(i);
        int i2 = this.mSelectedPosition;
        boolean z = true;
        if (i2 < 0 || (i != i2 - 1 && i != i2 + 1)) {
            z = false;
        }
        if (this.mPositionScroller != null) {
            this.mPositionScroller.stop();
        }
        layoutChildren();
        if (z) {
            awakenScrollBars();
        }
    }

    @Override // android.widget.AdapterView
    int lookForSelectablePosition(int i, boolean z) {
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter != null && !isInTouchMode()) {
            int count = listAdapter.getCount();
            if (!this.mAreAllItemsSelectable) {
                if (z) {
                    i = Math.max(0, i);
                    while (i < count && !listAdapter.isEnabled(i)) {
                        i++;
                    }
                } else {
                    i = Math.min(i, count - 1);
                    while (i >= 0 && !listAdapter.isEnabled(i)) {
                        i--;
                    }
                }
            }
            if (i >= 0 && i < count) {
                return i;
            }
        }
        return -1;
    }

    int lookForSelectablePositionAfter(int i, int i2, boolean z) {
        int iMax;
        ListAdapter listAdapter = this.mAdapter;
        if (listAdapter == null || isInTouchMode()) {
            return -1;
        }
        int iLookForSelectablePosition = lookForSelectablePosition(i2, z);
        if (iLookForSelectablePosition != -1) {
            return iLookForSelectablePosition;
        }
        int count = listAdapter.getCount() - 1;
        int iConstrain = MathUtils.constrain(i, -1, count);
        if (z) {
            iMax = Math.min(i2 - 1, count);
            while (iMax > iConstrain && !listAdapter.isEnabled(iMax)) {
                iMax--;
            }
            if (iMax <= iConstrain) {
                return -1;
            }
        } else {
            iMax = Math.max(0, i2 + 1);
            while (iMax < iConstrain && !listAdapter.isEnabled(iMax)) {
                iMax++;
            }
            if (iMax >= iConstrain) {
                return -1;
            }
        }
        return iMax;
    }

    public void setSelectionAfterHeaderView() {
        int headerViewsCount = getHeaderViewsCount();
        if (headerViewsCount > 0) {
            this.mNextSelectedPosition = 0;
        } else if (this.mAdapter != null) {
            setSelection(headerViewsCount);
        } else {
            this.mNextSelectedPosition = headerViewsCount;
            this.mLayoutMode = 2;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean zDispatchKeyEvent = super.dispatchKeyEvent(keyEvent);
        return (zDispatchKeyEvent || getFocusedChild() == null || keyEvent.getAction() != 0) ? zDispatchKeyEvent : onKeyDown(keyEvent.getKeyCode(), keyEvent);
    }

    @Override // android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return commonKey(i, 1, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return commonKey(i, i2, keyEvent);
    }

    @Override // android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return commonKey(i, 1, keyEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00ac  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean commonKey(int r9, int r10, android.view.KeyEvent r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 424
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.ListView.commonKey(int, int, android.view.KeyEvent):boolean");
    }

    boolean pageScroll(int i) throws Throwable {
        int iMin;
        boolean z;
        int iLookForSelectablePositionAfter;
        if (i != 33) {
            if (i == 130) {
                iMin = Math.min(this.mItemCount - 1, (this.mSelectedPosition + getChildCount()) - 1);
                z = true;
            }
            return false;
        }
        iMin = Math.max(0, (this.mSelectedPosition - getChildCount()) - 1);
        z = false;
        if (iMin >= 0 && (iLookForSelectablePositionAfter = lookForSelectablePositionAfter(this.mSelectedPosition, iMin, z)) >= 0) {
            this.mLayoutMode = 4;
            this.mSpecificTop = this.mPaddingTop + getVerticalFadingEdgeLength();
            if (z && iLookForSelectablePositionAfter > this.mItemCount - getChildCount()) {
                this.mLayoutMode = 3;
            }
            if (!z && iLookForSelectablePositionAfter < getChildCount()) {
                this.mLayoutMode = 1;
            }
            setSelectionInt(iLookForSelectablePositionAfter);
            invokeOnItemScrollListener();
            if (!awakenScrollBars()) {
                invalidate();
            }
            return true;
        }
        return false;
    }

    boolean fullScroll(int i) throws Throwable {
        int i2;
        boolean z = false;
        if (i == 33) {
            if (this.mSelectedPosition != 0) {
                int iLookForSelectablePositionAfter = lookForSelectablePositionAfter(this.mSelectedPosition, 0, true);
                if (iLookForSelectablePositionAfter >= 0) {
                    this.mLayoutMode = 1;
                    setSelectionInt(iLookForSelectablePositionAfter);
                    invokeOnItemScrollListener();
                }
                z = true;
            }
        } else if (i == 130 && this.mSelectedPosition < (i2 = this.mItemCount - 1)) {
            int iLookForSelectablePositionAfter2 = lookForSelectablePositionAfter(this.mSelectedPosition, i2, false);
            if (iLookForSelectablePositionAfter2 >= 0) {
                this.mLayoutMode = 3;
                setSelectionInt(iLookForSelectablePositionAfter2);
                invokeOnItemScrollListener();
            }
            z = true;
        }
        if (z && !awakenScrollBars()) {
            awakenScrollBars();
            invalidate();
        }
        return z;
    }

    private boolean handleHorizontalFocusWithinListItem(int i) {
        View selectedView;
        if (i != 17 && i != 66) {
            throw new IllegalArgumentException("direction must be one of {View.FOCUS_LEFT, View.FOCUS_RIGHT}");
        }
        int childCount = getChildCount();
        if (!this.mItemsCanFocus || childCount <= 0 || this.mSelectedPosition == -1 || (selectedView = getSelectedView()) == null || !selectedView.hasFocus() || !(selectedView instanceof ViewGroup)) {
            return false;
        }
        View viewFindFocus = selectedView.findFocus();
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus((ViewGroup) selectedView, viewFindFocus, i);
        if (viewFindNextFocus != null) {
            Rect rect = this.mTempRect;
            if (viewFindFocus != null) {
                viewFindFocus.getFocusedRect(rect);
                offsetDescendantRectToMyCoords(viewFindFocus, rect);
                offsetRectIntoDescendantCoords(viewFindNextFocus, rect);
            } else {
                rect = null;
            }
            if (viewFindNextFocus.requestFocus(i, rect)) {
                return true;
            }
        }
        View viewFindNextFocus2 = FocusFinder.getInstance().findNextFocus((ViewGroup) getRootView(), viewFindFocus, i);
        if (viewFindNextFocus2 != null) {
            return isViewAncestorOf(viewFindNextFocus2, this);
        }
        return false;
    }

    boolean arrowScroll(int i) {
        try {
            this.mInLayout = true;
            boolean zArrowScrollImpl = arrowScrollImpl(i);
            if (zArrowScrollImpl) {
                playSoundEffect(SoundEffectConstants.getContantForFocusDirection(i));
            }
            return zArrowScrollImpl;
        } finally {
            this.mInLayout = false;
        }
    }

    private final int nextSelectedPositionForDirection(View view, int i, int i2) {
        int i3;
        if (i2 == 130) {
            int height = getHeight() - this.mListPadding.bottom;
            if (view == null || view.getBottom() > height) {
                return -1;
            }
            i3 = (i == -1 || i < this.mFirstPosition) ? this.mFirstPosition : i + 1;
        } else {
            int i4 = this.mListPadding.top;
            if (view != null && view.getTop() >= i4) {
                int childCount = (this.mFirstPosition + getChildCount()) - 1;
                i3 = (i == -1 || i > childCount) ? childCount : i - 1;
            }
            return -1;
        }
        if (i3 >= 0 && i3 < this.mAdapter.getCount()) {
            return lookForSelectablePosition(i3, i2 == 130);
        }
        return -1;
    }

    private boolean arrowScrollImpl(int i) {
        View viewFindFocus;
        View focusedChild;
        if (getChildCount() <= 0) {
            return false;
        }
        View selectedView = getSelectedView();
        int i2 = this.mSelectedPosition;
        int iNextSelectedPositionForDirection = nextSelectedPositionForDirection(selectedView, i2, i);
        int iAmountToScroll = amountToScroll(i, iNextSelectedPositionForDirection);
        View view = null;
        ArrowScrollFocusResult arrowScrollFocusResultArrowScrollFocused = this.mItemsCanFocus ? arrowScrollFocused(i) : null;
        if (arrowScrollFocusResultArrowScrollFocused != null) {
            iNextSelectedPositionForDirection = arrowScrollFocusResultArrowScrollFocused.getSelectedPosition();
            iAmountToScroll = arrowScrollFocusResultArrowScrollFocused.getAmountToScroll();
        }
        boolean z = arrowScrollFocusResultArrowScrollFocused != null;
        if (iNextSelectedPositionForDirection != -1) {
            handleNewSelectionChange(selectedView, i, iNextSelectedPositionForDirection, arrowScrollFocusResultArrowScrollFocused != null);
            setSelectedPositionInt(iNextSelectedPositionForDirection);
            setNextSelectedPositionInt(iNextSelectedPositionForDirection);
            selectedView = getSelectedView();
            if (this.mItemsCanFocus && arrowScrollFocusResultArrowScrollFocused == null && (focusedChild = getFocusedChild()) != null) {
                focusedChild.clearFocus();
            }
            checkSelectionChanged();
            i2 = iNextSelectedPositionForDirection;
            z = true;
        }
        if (iAmountToScroll > 0) {
            if (i != 33) {
                iAmountToScroll = -iAmountToScroll;
            }
            scrollListItemsBy(iAmountToScroll);
            z = true;
        }
        if (this.mItemsCanFocus && arrowScrollFocusResultArrowScrollFocused == null && selectedView != null && selectedView.hasFocus() && (viewFindFocus = selectedView.findFocus()) != null && (!isViewAncestorOf(viewFindFocus, this) || distanceToView(viewFindFocus) > 0)) {
            viewFindFocus.clearFocus();
        }
        if (iNextSelectedPositionForDirection != -1 || selectedView == null || isViewAncestorOf(selectedView, this)) {
            view = selectedView;
        } else {
            hideSelector();
            this.mResurrectToPosition = -1;
        }
        if (!z) {
            return false;
        }
        if (view != null) {
            positionSelectorLikeFocus(i2, view);
            this.mSelectedTop = view.getTop();
        }
        if (!awakenScrollBars()) {
            invalidate();
        }
        invokeOnItemScrollListener();
        return true;
    }

    private void handleNewSelectionChange(View view, int i, int i2, boolean z) {
        View childAt;
        boolean z2;
        if (i2 == -1) {
            throw new IllegalArgumentException("newSelectedPosition needs to be valid");
        }
        int i3 = this.mSelectedPosition - this.mFirstPosition;
        int i4 = i2 - this.mFirstPosition;
        if (i == 33) {
            z2 = true;
            childAt = view;
            view = getChildAt(i4);
            i3 = i4;
            i4 = i3;
        } else {
            childAt = getChildAt(i4);
            z2 = false;
        }
        int childCount = getChildCount();
        if (view != null) {
            view.setSelected(!z && z2);
            measureAndAdjustDown(view, i3, childCount);
        }
        if (childAt != null) {
            childAt.setSelected((z || z2) ? false : true);
            measureAndAdjustDown(childAt, i4, childCount);
        }
    }

    private void measureAndAdjustDown(View view, int i, int i2) {
        int height = view.getHeight();
        measureItem(view);
        if (view.getMeasuredHeight() == height) {
            return;
        }
        relayoutMeasuredItem(view);
        int measuredHeight = view.getMeasuredHeight() - height;
        while (true) {
            i++;
            if (i >= i2) {
                return;
            } else {
                getChildAt(i).offsetTopAndBottom(measuredHeight);
            }
        }
    }

    private void measureItem(View view) {
        int iMakeSafeMeasureSpec;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(this.mWidthMeasureSpec, this.mListPadding.left + this.mListPadding.right, layoutParams.width);
        int i = layoutParams.height;
        if (i > 0) {
            iMakeSafeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i, 1073741824);
        } else {
            iMakeSafeMeasureSpec = View.MeasureSpec.makeSafeMeasureSpec(getMeasuredHeight(), 0);
        }
        view.measure(childMeasureSpec, iMakeSafeMeasureSpec);
    }

    private void relayoutMeasuredItem(View view) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i = this.mListPadding.left;
        int top = view.getTop();
        view.layout(i, top, measuredWidth + i, measuredHeight + top);
    }

    private int getArrowScrollPreviewLength() {
        return Math.max(2, getVerticalFadingEdgeLength());
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x009d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x008b -> B:27:0x0085). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int amountToScroll(int r7, int r8) {
        /*
            Method dump skipped, instruction units count: 225
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.widget.ListView.amountToScroll(int, int):int");
    }

    private static class ArrowScrollFocusResult {
        private int mAmountToScroll;
        private int mSelectedPosition;

        private ArrowScrollFocusResult() {
        }

        void populate(int i, int i2) {
            this.mSelectedPosition = i;
            this.mAmountToScroll = i2;
        }

        public int getSelectedPosition() {
            return this.mSelectedPosition;
        }

        public int getAmountToScroll() {
            return this.mAmountToScroll;
        }
    }

    private int lookForSelectablePositionOnScreen(int i) {
        int childCount;
        int i2 = this.mFirstPosition;
        if (i == 130) {
            int i3 = this.mSelectedPosition != -1 ? this.mSelectedPosition + 1 : i2;
            if (i3 >= this.mAdapter.getCount()) {
                return -1;
            }
            if (i3 < i2) {
                i3 = i2;
            }
            int lastVisiblePosition = getLastVisiblePosition();
            ListAdapter adapter = getAdapter();
            while (i3 <= lastVisiblePosition) {
                if (adapter.isEnabled(i3) && getChildAt(i3 - i2).getVisibility() == 0) {
                    return i3;
                }
                i3++;
            }
        } else {
            int childCount2 = (getChildCount() + i2) - 1;
            if (this.mSelectedPosition != -1) {
                childCount = this.mSelectedPosition;
            } else {
                childCount = getChildCount() + i2;
            }
            int i4 = childCount - 1;
            if (i4 >= 0 && i4 < this.mAdapter.getCount()) {
                if (i4 <= childCount2) {
                    childCount2 = i4;
                }
                ListAdapter adapter2 = getAdapter();
                while (childCount2 >= i2) {
                    if (adapter2.isEnabled(childCount2) && getChildAt(childCount2 - i2).getVisibility() == 0) {
                        return childCount2;
                    }
                    childCount2--;
                }
            }
        }
        return -1;
    }

    private ArrowScrollFocusResult arrowScrollFocused(int i) {
        View viewFindNextFocusFromRect;
        int iLookForSelectablePositionOnScreen;
        View selectedView = getSelectedView();
        if (selectedView != null && selectedView.hasFocus()) {
            viewFindNextFocusFromRect = FocusFinder.getInstance().findNextFocus(this, selectedView.findFocus(), i);
        } else {
            if (i == 130) {
                int arrowScrollPreviewLength = this.mListPadding.top + (this.mFirstPosition > 0 ? getArrowScrollPreviewLength() : 0);
                if (selectedView != null && selectedView.getTop() > arrowScrollPreviewLength) {
                    arrowScrollPreviewLength = selectedView.getTop();
                }
                this.mTempRect.set(0, arrowScrollPreviewLength, 0, arrowScrollPreviewLength);
            } else {
                int height = (getHeight() - this.mListPadding.bottom) - ((this.mFirstPosition + getChildCount()) - 1 < this.mItemCount ? getArrowScrollPreviewLength() : 0);
                if (selectedView != null && selectedView.getBottom() < height) {
                    height = selectedView.getBottom();
                }
                this.mTempRect.set(0, height, 0, height);
            }
            viewFindNextFocusFromRect = FocusFinder.getInstance().findNextFocusFromRect(this, this.mTempRect, i);
        }
        if (viewFindNextFocusFromRect != null) {
            int iPositionOfNewFocus = positionOfNewFocus(viewFindNextFocusFromRect);
            if (this.mSelectedPosition != -1 && iPositionOfNewFocus != this.mSelectedPosition && (iLookForSelectablePositionOnScreen = lookForSelectablePositionOnScreen(i)) != -1 && ((i == 130 && iLookForSelectablePositionOnScreen < iPositionOfNewFocus) || (i == 33 && iLookForSelectablePositionOnScreen > iPositionOfNewFocus))) {
                return null;
            }
            int iAmountToScrollToNewFocus = amountToScrollToNewFocus(i, viewFindNextFocusFromRect, iPositionOfNewFocus);
            int maxScrollAmount = getMaxScrollAmount();
            if (iAmountToScrollToNewFocus < maxScrollAmount) {
                viewFindNextFocusFromRect.requestFocus(i);
                this.mArrowScrollFocusResult.populate(iPositionOfNewFocus, iAmountToScrollToNewFocus);
                return this.mArrowScrollFocusResult;
            }
            if (distanceToView(viewFindNextFocusFromRect) < maxScrollAmount) {
                viewFindNextFocusFromRect.requestFocus(i);
                this.mArrowScrollFocusResult.populate(iPositionOfNewFocus, maxScrollAmount);
                return this.mArrowScrollFocusResult;
            }
        }
        return null;
    }

    private int positionOfNewFocus(View view) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (isViewAncestorOf(view, getChildAt(i))) {
                return this.mFirstPosition + i;
            }
        }
        throw new IllegalArgumentException("newFocus is not a child of any of the children of the list!");
    }

    private boolean isViewAncestorOf(View view, View view2) {
        if (view == view2) {
            return true;
        }
        Object parent = view.getParent();
        return (parent instanceof ViewGroup) && isViewAncestorOf((View) parent, view2);
    }

    private int amountToScrollToNewFocus(int i, View view, int i2) {
        int i3;
        int arrowScrollPreviewLength;
        view.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        if (i == 33) {
            if (this.mTempRect.top < this.mListPadding.top) {
                i3 = this.mListPadding.top - this.mTempRect.top;
                if (i2 <= 0) {
                    return i3;
                }
                arrowScrollPreviewLength = getArrowScrollPreviewLength();
                return i3 + arrowScrollPreviewLength;
            }
            return 0;
        }
        int height = getHeight() - this.mListPadding.bottom;
        if (this.mTempRect.bottom > height) {
            i3 = this.mTempRect.bottom - height;
            if (i2 >= this.mItemCount - 1) {
                return i3;
            }
            arrowScrollPreviewLength = getArrowScrollPreviewLength();
            return i3 + arrowScrollPreviewLength;
        }
        return 0;
    }

    private int distanceToView(View view) {
        view.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        int i = (this.mBottom - this.mTop) - this.mListPadding.bottom;
        if (this.mTempRect.bottom < this.mListPadding.top) {
            return this.mListPadding.top - this.mTempRect.bottom;
        }
        if (this.mTempRect.top > i) {
            return this.mTempRect.top - i;
        }
        return 0;
    }

    private void scrollListItemsBy(int i) {
        int i2;
        int i3 = this.mScrollX;
        int i4 = this.mScrollY;
        offsetChildrenTopAndBottom(i);
        int height = getHeight() - this.mListPadding.bottom;
        int i5 = this.mListPadding.top;
        AbsListView.RecycleBin recycleBin = this.mRecycler;
        if (i < 0) {
            int childCount = getChildCount();
            View childAt = getChildAt(childCount - 1);
            while (childAt.getBottom() < height && (this.mFirstPosition + childCount) - 1 < this.mItemCount - 1) {
                childAt = addViewBelow(childAt, i2);
                childCount++;
            }
            if (childAt.getBottom() < height) {
                offsetChildrenTopAndBottom(height - childAt.getBottom());
            }
            View childAt2 = getChildAt(0);
            while (childAt2.getBottom() < i5) {
                if (recycleBin.shouldRecycleViewType(((AbsListView.LayoutParams) childAt2.getLayoutParams()).viewType)) {
                    recycleBin.addScrapView(childAt2, this.mFirstPosition);
                }
                detachViewFromParent(childAt2);
                childAt2 = getChildAt(0);
                this.mFirstPosition++;
            }
        } else {
            View childAt3 = getChildAt(0);
            while (childAt3.getTop() > i5 && this.mFirstPosition > 0) {
                childAt3 = addViewAbove(childAt3, this.mFirstPosition);
                this.mFirstPosition--;
            }
            if (childAt3.getTop() > i5) {
                offsetChildrenTopAndBottom(i5 - childAt3.getTop());
            }
            int childCount2 = getChildCount() - 1;
            View childAt4 = getChildAt(childCount2);
            while (childAt4.getTop() > height) {
                if (recycleBin.shouldRecycleViewType(((AbsListView.LayoutParams) childAt4.getLayoutParams()).viewType)) {
                    recycleBin.addScrapView(childAt4, this.mFirstPosition + childCount2);
                }
                detachViewFromParent(childAt4);
                childCount2--;
                childAt4 = getChildAt(childCount2);
            }
        }
        recycleBin.fullyDetachScrapViews();
        removeUnusedFixedViews(this.mHeaderViewInfos);
        removeUnusedFixedViews(this.mFooterViewInfos);
        onScrollChanged(this.mScrollX, this.mScrollY, i3, i4);
    }

    private View addViewAbove(View view, int i) {
        int i2 = i - 1;
        View viewObtainView = obtainView(i2, this.mIsScrap);
        setupChild(viewObtainView, i2, view.getTop() - this.mDividerHeight, false, this.mListPadding.left, false, this.mIsScrap[0]);
        return viewObtainView;
    }

    private View addViewBelow(View view, int i) {
        int i2 = i + 1;
        View viewObtainView = obtainView(i2, this.mIsScrap);
        setupChild(viewObtainView, i2, view.getBottom() + this.mDividerHeight, true, this.mListPadding.left, false, this.mIsScrap[0]);
        return viewObtainView;
    }

    public void setItemsCanFocus(boolean z) {
        this.mItemsCanFocus = z;
        if (z) {
            return;
        }
        setDescendantFocusability(393216);
    }

    public boolean getItemsCanFocus() {
        return this.mItemsCanFocus;
    }

    @Override // android.view.View
    public boolean isOpaque() {
        boolean z = (this.mCachingActive && this.mIsCacheColorOpaque && this.mDividerIsOpaque && hasOpaqueScrollbars()) || super.isOpaque();
        if (z) {
            int i = this.mListPadding != null ? this.mListPadding.top : this.mPaddingTop;
            View childAt = getChildAt(0);
            if (childAt != null && childAt.getTop() <= i) {
                int height = getHeight() - (this.mListPadding != null ? this.mListPadding.bottom : this.mPaddingBottom);
                View childAt2 = getChildAt(getChildCount() - 1);
                if (childAt2 == null || childAt2.getBottom() < height) {
                }
            }
            return false;
        }
        return z;
    }

    @Override // android.widget.AbsListView
    public void setCacheColorHint(int i) {
        boolean z = (i >>> 24) == 255;
        this.mIsCacheColorOpaque = z;
        if (z) {
            if (this.mDividerPaint == null) {
                this.mDividerPaint = new Paint();
            }
            this.mDividerPaint.setColor(i);
        }
        super.setCacheColorHint(i);
    }

    void drawOverscrollHeader(Canvas canvas, Drawable drawable, Rect rect) {
        int minimumHeight = drawable.getMinimumHeight();
        canvas.save();
        canvas.clipRect(rect);
        if (rect.bottom - rect.top < minimumHeight) {
            rect.top = rect.bottom - minimumHeight;
        }
        drawable.setBounds(rect);
        drawable.draw(canvas);
        canvas.restore();
    }

    void drawOverscrollFooter(Canvas canvas, Drawable drawable, Rect rect) {
        int minimumHeight = drawable.getMinimumHeight();
        canvas.save();
        canvas.clipRect(rect);
        if (rect.bottom - rect.top < minimumHeight) {
            rect.bottom = rect.top + minimumHeight;
        }
        drawable.setBounds(rect);
        drawable.draw(canvas);
        canvas.restore();
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        ListAdapter listAdapter;
        int i;
        int i2;
        int i3;
        Drawable drawable;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z;
        ListAdapter listAdapter2;
        Paint paint;
        if (this.mCachingStarted) {
            this.mCachingActive = true;
        }
        int i9 = this.mDividerHeight;
        Drawable drawable2 = this.mOverScrollHeader;
        Drawable drawable3 = this.mOverScrollFooter;
        int i10 = drawable2 != null ? 1 : 0;
        boolean z2 = drawable3 != null;
        boolean z3 = i9 > 0 && this.mDivider != null;
        if (z3 || i10 != 0 || z2) {
            Rect rect = this.mTempRect;
            rect.left = this.mPaddingLeft;
            rect.right = (this.mRight - this.mLeft) - this.mPaddingRight;
            int childCount = getChildCount();
            int headerViewsCount = getHeaderViewsCount();
            int i11 = this.mItemCount;
            int size = i11 - this.mFooterViewInfos.size();
            boolean z4 = this.mHeaderDividersEnabled;
            boolean z5 = this.mFooterDividersEnabled;
            int i12 = this.mFirstPosition;
            ListAdapter listAdapter3 = this.mAdapter;
            boolean z6 = isOpaque() && !super.isOpaque();
            if (z6) {
                i = i11;
                if (this.mDividerPaint == null && this.mIsCacheColorOpaque) {
                    Paint paint2 = new Paint();
                    this.mDividerPaint = paint2;
                    listAdapter = listAdapter3;
                    paint2.setColor(getCacheColorHint());
                } else {
                    listAdapter = listAdapter3;
                }
            } else {
                listAdapter = listAdapter3;
                i = i11;
            }
            Paint paint3 = this.mDividerPaint;
            if ((this.mGroupFlags & 34) == 34) {
                int i13 = this.mListPadding.top;
                i2 = this.mListPadding.bottom;
                i3 = i13;
            } else {
                i2 = 0;
                i3 = 0;
            }
            boolean z7 = z2;
            int i14 = ((this.mBottom - this.mTop) - i2) + this.mScrollY;
            if (!this.mStackFromBottom) {
                int i15 = this.mScrollY;
                if (childCount > 0 && i15 < 0) {
                    if (i10 != 0) {
                        rect.bottom = 0;
                        rect.top = i15;
                        drawOverscrollHeader(canvas, drawable2, rect);
                    } else if (z3) {
                        rect.bottom = 0;
                        rect.top = -i9;
                        drawDivider(canvas, rect, -1);
                    }
                }
                int bottom = 0;
                int i16 = 0;
                while (i16 < childCount) {
                    int i17 = i12 + i16;
                    boolean z8 = i17 < headerViewsCount;
                    boolean z9 = i17 >= size;
                    if ((z4 || !z8) && (z5 || !z9)) {
                        bottom = getChildAt(i16).getBottom();
                        i7 = i12;
                        boolean z10 = i16 == childCount + (-1);
                        if (!z3 || bottom >= i14 || (z7 && z10)) {
                            i8 = i14;
                        } else {
                            i8 = i14;
                            int i18 = i17 + 1;
                            z = z3;
                            listAdapter2 = listAdapter;
                            if (!listAdapter2.isEnabled(i17) || ((!z4 && (z8 || i18 < headerViewsCount)) || (!z10 && (!listAdapter2.isEnabled(i18) || (!z5 && (z9 || i18 >= size)))))) {
                                if (z6) {
                                    rect.top = bottom;
                                    rect.bottom = bottom + i9;
                                    paint = paint3;
                                    canvas.drawRect(rect, paint);
                                }
                                i16++;
                                paint3 = paint;
                                listAdapter = listAdapter2;
                                i12 = i7;
                                i14 = i8;
                                z3 = z;
                            } else {
                                rect.top = bottom;
                                rect.bottom = bottom + i9;
                                drawDivider(canvas, rect, i16);
                            }
                            paint = paint3;
                            i16++;
                            paint3 = paint;
                            listAdapter = listAdapter2;
                            i12 = i7;
                            i14 = i8;
                            z3 = z;
                        }
                    } else {
                        i8 = i14;
                        i7 = i12;
                    }
                    z = z3;
                    listAdapter2 = listAdapter;
                    paint = paint3;
                    i16++;
                    paint3 = paint;
                    listAdapter = listAdapter2;
                    i12 = i7;
                    i14 = i8;
                    z3 = z;
                }
                int i19 = i12;
                int i20 = this.mBottom + this.mScrollY;
                if (z7 && i19 + childCount == i && i20 > bottom) {
                    rect.top = bottom;
                    rect.bottom = i20;
                    drawOverscrollFooter(canvas, drawable3, rect);
                }
            } else {
                boolean z11 = z3;
                Drawable drawable4 = drawable3;
                ListAdapter listAdapter4 = listAdapter;
                int i21 = this.mScrollY;
                if (childCount > 0 && i10 != 0) {
                    rect.top = i21;
                    rect.bottom = getChildAt(0).getTop();
                    drawOverscrollHeader(canvas, drawable2, rect);
                }
                int i22 = i10;
                while (i22 < childCount) {
                    int i23 = i12 + i22;
                    boolean z12 = i23 < headerViewsCount;
                    boolean z13 = i23 >= size;
                    if ((z4 || !z12) && (z5 || !z13)) {
                        drawable = drawable4;
                        int top = getChildAt(i22).getTop();
                        if (z11) {
                            i4 = i21;
                            i5 = i3;
                            if (top > i5) {
                                boolean z14 = i22 == i10;
                                i6 = i10;
                                int i24 = i23 - 1;
                                if (listAdapter4.isEnabled(i23) && ((z4 || (!z12 && i24 >= headerViewsCount)) && (z14 || (listAdapter4.isEnabled(i24) && (z5 || (!z13 && i24 < size)))))) {
                                    rect.top = top - i9;
                                    rect.bottom = top;
                                    drawDivider(canvas, rect, i22 - 1);
                                } else if (z6) {
                                    rect.top = top - i9;
                                    rect.bottom = top;
                                    canvas.drawRect(rect, paint3);
                                }
                            } else {
                                i6 = i10;
                            }
                        }
                        i22++;
                        i3 = i5;
                        i21 = i4;
                        drawable4 = drawable;
                        i10 = i6;
                    } else {
                        drawable = drawable4;
                    }
                    i6 = i10;
                    i4 = i21;
                    i5 = i3;
                    i22++;
                    i3 = i5;
                    i21 = i4;
                    drawable4 = drawable;
                    i10 = i6;
                }
                Drawable drawable5 = drawable4;
                int i25 = i21;
                if (childCount > 0 && i25 > 0) {
                    if (z7) {
                        int i26 = this.mBottom;
                        rect.top = i26;
                        rect.bottom = i26 + i25;
                        drawOverscrollFooter(canvas, drawable5, rect);
                    } else if (z11) {
                        rect.top = i14;
                        rect.bottom = i14 + i9;
                        drawDivider(canvas, rect, -1);
                    }
                }
            }
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas, View view, long j) {
        boolean zDrawChild = super.drawChild(canvas, view, j);
        if (this.mCachingActive && view.mCachingFailed) {
            this.mCachingActive = false;
        }
        return zDrawChild;
    }

    void drawDivider(Canvas canvas, Rect rect, int i) {
        Drawable drawable = this.mDivider;
        drawable.setBounds(rect);
        drawable.draw(canvas);
    }

    public Drawable getDivider() {
        return this.mDivider;
    }

    public void setDivider(Drawable drawable) {
        if (drawable != null) {
            this.mDividerHeight = drawable.getIntrinsicHeight();
        } else {
            this.mDividerHeight = 0;
        }
        this.mDivider = drawable;
        this.mDividerIsOpaque = drawable == null || drawable.getOpacity() == -1;
        requestLayout();
        invalidate();
    }

    public int getDividerHeight() {
        return this.mDividerHeight;
    }

    public void setDividerHeight(int i) {
        this.mDividerHeight = i;
        requestLayout();
        invalidate();
    }

    public void setHeaderDividersEnabled(boolean z) {
        this.mHeaderDividersEnabled = z;
        invalidate();
    }

    public boolean areHeaderDividersEnabled() {
        return this.mHeaderDividersEnabled;
    }

    public void setFooterDividersEnabled(boolean z) {
        this.mFooterDividersEnabled = z;
        invalidate();
    }

    public boolean areFooterDividersEnabled() {
        return this.mFooterDividersEnabled;
    }

    public void setOverscrollHeader(Drawable drawable) {
        this.mOverScrollHeader = drawable;
        if (this.mScrollY < 0) {
            invalidate();
        }
    }

    public Drawable getOverscrollHeader() {
        return this.mOverScrollHeader;
    }

    public void setOverscrollFooter(Drawable drawable) {
        this.mOverScrollFooter = drawable;
        invalidate();
    }

    public Drawable getOverscrollFooter() {
        return this.mOverScrollFooter;
    }

    @Override // android.widget.AbsListView, android.view.View
    protected void onFocusChanged(boolean z, int i, Rect rect) throws Throwable {
        super.onFocusChanged(z, i, rect);
        ListAdapter listAdapter = this.mAdapter;
        int i2 = 0;
        int i3 = -1;
        if (listAdapter != null && z && rect != null) {
            rect.offset(this.mScrollX, this.mScrollY);
            if (listAdapter.getCount() < getChildCount() + this.mFirstPosition) {
                this.mLayoutMode = 0;
                layoutChildren();
            }
            Rect rect2 = this.mTempRect;
            int childCount = getChildCount();
            int i4 = this.mFirstPosition;
            int i5 = Integer.MAX_VALUE;
            int i6 = -1;
            int top = 0;
            while (i2 < childCount) {
                if (listAdapter.isEnabled(i4 + i2)) {
                    View childAt = getChildAt(i2);
                    childAt.getDrawingRect(rect2);
                    offsetDescendantRectToMyCoords(childAt, rect2);
                    int distance = getDistance(rect, rect2, i);
                    if (distance < i5) {
                        top = childAt.getTop();
                        i6 = i2;
                        i5 = distance;
                    }
                }
                i2++;
            }
            i2 = top;
            i3 = i6;
        }
        if (i3 >= 0) {
            setSelectionFromTop(i3 + this.mFirstPosition, i2);
        } else {
            requestLayout();
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount > 0) {
            for (int i = 0; i < childCount; i++) {
                addHeaderView(getChildAt(i));
            }
            removeAllViews();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected <T extends View> T findViewTraversal(int i) {
        T t = (T) super.findViewTraversal(i);
        if (t == null) {
            T t2 = (T) findViewInHeadersOrFooters(this.mHeaderViewInfos, i);
            if (t2 != null) {
                return t2;
            }
            t = (T) findViewInHeadersOrFooters(this.mFooterViewInfos, i);
            if (t != null) {
            }
        }
        return t;
    }

    View findViewInHeadersOrFooters(ArrayList<FixedViewInfo> arrayList, int i) {
        View viewFindViewById;
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = arrayList.get(i2).view;
            if (!view.isRootNamespace() && (viewFindViewById = view.findViewById(i)) != null) {
                return viewFindViewById;
            }
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected <T extends View> T findViewWithTagTraversal(Object obj) {
        T t = (T) super.findViewWithTagTraversal(obj);
        if (t == null) {
            T t2 = (T) findViewWithTagInHeadersOrFooters(this.mHeaderViewInfos, obj);
            if (t2 != null) {
                return t2;
            }
            t = (T) findViewWithTagInHeadersOrFooters(this.mFooterViewInfos, obj);
            if (t != null) {
            }
        }
        return t;
    }

    View findViewWithTagInHeadersOrFooters(ArrayList<FixedViewInfo> arrayList, Object obj) {
        View viewFindViewWithTag;
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            View view = arrayList.get(i).view;
            if (!view.isRootNamespace() && (viewFindViewWithTag = view.findViewWithTag(obj)) != null) {
                return viewFindViewWithTag;
            }
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected <T extends View> T findViewByPredicateTraversal(Predicate<View> predicate, View view) {
        T t = (T) super.findViewByPredicateTraversal(predicate, view);
        if (t == null) {
            T t2 = (T) findViewByPredicateInHeadersOrFooters(this.mHeaderViewInfos, predicate, view);
            if (t2 != null) {
                return t2;
            }
            t = (T) findViewByPredicateInHeadersOrFooters(this.mFooterViewInfos, predicate, view);
            if (t != null) {
            }
        }
        return t;
    }

    View findViewByPredicateInHeadersOrFooters(ArrayList<FixedViewInfo> arrayList, Predicate<View> predicate, View view) {
        View viewFindViewByPredicate;
        if (arrayList == null) {
            return null;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            View view2 = arrayList.get(i).view;
            if (view2 != view && !view2.isRootNamespace() && (viewFindViewByPredicate = view2.findViewByPredicate(predicate)) != null) {
                return viewFindViewByPredicate;
            }
        }
        return null;
    }

    @Deprecated
    public long[] getCheckItemIds() {
        if (this.mAdapter != null && this.mAdapter.hasStableIds()) {
            return getCheckedItemIds();
        }
        if (this.mChoiceMode == 0 || this.mCheckStates == null || this.mAdapter == null) {
            return new long[0];
        }
        SparseBooleanArray sparseBooleanArray = this.mCheckStates;
        int size = sparseBooleanArray.size();
        long[] jArr = new long[size];
        ListAdapter listAdapter = this.mAdapter;
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            if (sparseBooleanArray.valueAt(i2)) {
                jArr[i] = listAdapter.getItemId(sparseBooleanArray.keyAt(i2));
                i++;
            }
        }
        if (i == size) {
            return jArr;
        }
        long[] jArr2 = new long[i];
        System.arraycopy(jArr, 0, jArr2, 0, i);
        return jArr2;
    }

    @Override // android.widget.AbsListView
    int getHeightForPosition(int i) {
        int heightForPosition = super.getHeightForPosition(i);
        return shouldAdjustHeightForDivider(i) ? heightForPosition + this.mDividerHeight : heightForPosition;
    }

    private boolean shouldAdjustHeightForDivider(int i) {
        int i2 = this.mDividerHeight;
        Drawable drawable = this.mOverScrollHeader;
        Drawable drawable2 = this.mOverScrollFooter;
        int i3 = drawable != null ? 1 : 0;
        boolean z = drawable2 != null;
        if (i2 > 0 && this.mDivider != null) {
            boolean z2 = isOpaque() && !super.isOpaque();
            int i4 = this.mItemCount;
            int headerViewsCount = getHeaderViewsCount();
            int size = i4 - this.mFooterViewInfos.size();
            boolean z3 = i < headerViewsCount;
            boolean z4 = i >= size;
            boolean z5 = this.mHeaderDividersEnabled;
            boolean z6 = this.mFooterDividersEnabled;
            if ((z5 || !z3) && (z6 || !z4)) {
                ListAdapter listAdapter = this.mAdapter;
                if (this.mStackFromBottom) {
                    boolean z7 = i == i3;
                    if (!z7) {
                        int i5 = i - 1;
                        if ((listAdapter.isEnabled(i) && ((z5 || (!z3 && i5 >= headerViewsCount)) && (z7 || (listAdapter.isEnabled(i5) && (z6 || (!z4 && i5 < size)))))) || z2) {
                            return true;
                        }
                    }
                } else {
                    boolean z8 = i == i4 - 1;
                    if (!z || !z8) {
                        int i6 = i + 1;
                        if ((listAdapter.isEnabled(i) && ((z5 || (!z3 && i6 >= headerViewsCount)) && (z8 || (listAdapter.isEnabled(i6) && (z6 || (!z4 && i6 < size)))))) || z2) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override // android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return ListView.class.getName();
    }

    @Override // android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilityNodeInfo);
        int count = getCount();
        accessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(-1, -1, false, getSelectionModeForAccessibility()));
        if (count > 0) {
            accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION);
        }
    }

    @Override // android.widget.AbsListView, android.view.View
    public boolean performAccessibilityActionInternal(int i, Bundle bundle) {
        if (super.performAccessibilityActionInternal(i, bundle)) {
            return true;
        }
        if (i != 16908343) {
            return false;
        }
        int i2 = bundle.getInt("android.view.accessibility.action.ARGUMENT_ROW_INT", -1);
        int iMin = Math.min(i2, getCount() - 1);
        if (i2 < 0) {
            return false;
        }
        smoothScrollToPosition(iMin);
        return true;
    }

    @Override // android.widget.AbsListView
    public void onInitializeAccessibilityNodeInfoForItem(View view, int i, AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfoForItem(view, i, accessibilityNodeInfo);
        AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) view.getLayoutParams();
        accessibilityNodeInfo.setCollectionItemInfo(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, 1, 0, 1, layoutParams != null && layoutParams.viewType == -2, isItemChecked(i)));
    }

    @Override // android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void encodeProperties(ViewHierarchyEncoder viewHierarchyEncoder) {
        super.encodeProperties(viewHierarchyEncoder);
        viewHierarchyEncoder.addProperty("recycleOnMeasure", recycleOnMeasure());
    }

    protected HeaderViewListAdapter wrapHeaderListAdapterInternal(ArrayList<FixedViewInfo> arrayList, ArrayList<FixedViewInfo> arrayList2, ListAdapter listAdapter) {
        return new HeaderViewListAdapter(arrayList, arrayList2, listAdapter);
    }

    protected void wrapHeaderListAdapterInternal() {
        this.mAdapter = wrapHeaderListAdapterInternal(this.mHeaderViewInfos, this.mFooterViewInfos, this.mAdapter);
    }

    protected void dispatchDataSetObserverOnChangedInternal() {
        if (this.mDataSetObserver != null) {
            this.mDataSetObserver.onChanged();
        }
    }
}
