package com.android.launcher3;

import android.animation.LayoutTransition;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Interpolator;
import android.widget.ScrollView;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.compat.AccessibilityManagerCompat;
import com.android.launcher3.config.FeatureFlags;
import com.android.launcher3.pageindicators.PageIndicator;
import com.android.launcher3.touch.OverScroll;
import com.android.launcher3.util.OverScroller;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public abstract class PagedView<T extends View & PageIndicator> extends ViewGroup {
    private static final boolean DEBUG = false;
    private static final int FLING_THRESHOLD_VELOCITY = 500;
    protected static final int INVALID_PAGE = -1;
    protected static final int INVALID_POINTER = -1;
    public static final int INVALID_RESTORE_PAGE = -1001;
    private static final float MAX_SCROLL_PROGRESS = 1.0f;
    private static final int MIN_FLING_VELOCITY = 250;
    private static final int MIN_SNAP_VELOCITY = 1500;
    private static final int OVERSCROLL_PAGE_SNAP_ANIMATION_DURATION = 270;
    public static final int PAGE_SNAP_ANIMATION_DURATION = 750;
    private static final float RETURN_TO_ORIGINAL_PAGE_THRESHOLD = 0.33f;
    private static final float SIGNIFICANT_MOVE_THRESHOLD = 0.4f;
    protected static final ComputePageScrollsLogic SIMPLE_SCROLL_LOGIC = new ComputePageScrollsLogic() { // from class: com.android.launcher3.-$$Lambda$PagedView$bFsGWHKJCiy-iqldga8RW5Ge_gk
        @Override // com.android.launcher3.PagedView.ComputePageScrollsLogic
        public final boolean shouldIncludeView(View view) {
            return PagedView.lambda$static$0(view);
        }
    };
    private static final String TAG = "PagedView";
    protected int mActivePointerId;
    protected boolean mAllowOverScroll;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    protected int mCurrentPage;
    private Interpolator mDefaultInterpolator;
    private float mDownMotionX;
    private float mDownMotionY;
    protected boolean mFirstLayout;
    protected int mFlingThresholdVelocity;
    private boolean mFreeScroll;
    protected final Rect mInsets;
    private boolean mIsBeingDragged;
    protected boolean mIsLayoutValid;
    protected boolean mIsPageInTransition;
    protected boolean mIsRtl;
    private float mLastMotionX;
    private float mLastMotionXRemainder;
    protected int mMaxScrollX;
    private int mMaximumVelocity;
    protected int mMinFlingVelocity;
    protected int mMinScrollX;
    protected int mMinSnapVelocity;

    @ViewDebug.ExportedProperty(category = Context.LAUNCHER_SERVICE)
    protected int mNextPage;
    protected T mPageIndicator;
    int mPageIndicatorViewId;
    protected int[] mPageScrolls;
    protected int mPageSpacing;
    protected OverScroller mScroller;
    protected float mSpringOverScrollX;
    private int[] mTmpIntPair;
    private float mTotalMotionX;
    protected int mTouchSlop;
    protected int mUnboundedScrollX;
    private VelocityTracker mVelocityTracker;
    protected boolean mWasInOverscroll;

    protected interface ComputePageScrollsLogic {
        boolean shouldIncludeView(View view);
    }

    protected boolean canAnnouncePageDescription() {
        return true;
    }

    protected int computeMinScrollX() {
        return 0;
    }

    protected int getChildGap() {
        return 0;
    }

    protected int indexToPage(int index) {
        return index;
    }

    protected boolean isPageOrderFlipped() {
        return false;
    }

    protected void onPageBeginTransition() {
    }

    protected void onScrollInteractionBegin() {
    }

    protected void onScrollInteractionEnd() {
    }

    static /* synthetic */ boolean lambda$static$0(View view) {
        return view.getVisibility() != 8;
    }

    public PagedView(Context context) {
        this(context, null);
    }

    public PagedView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagedView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mFreeScroll = false;
        this.mFirstLayout = true;
        this.mNextPage = -1;
        this.mPageSpacing = 0;
        this.mAllowOverScroll = true;
        this.mActivePointerId = -1;
        this.mIsPageInTransition = false;
        this.mWasInOverscroll = false;
        this.mInsets = new Rect();
        this.mTmpIntPair = new int[2];
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.PagedView, defStyle, 0);
        this.mPageIndicatorViewId = typedArrayObtainStyledAttributes.getResourceId(0, -1);
        typedArrayObtainStyledAttributes.recycle();
        setHapticFeedbackEnabled(false);
        this.mIsRtl = Utilities.isRtl(getResources());
        init();
    }

    protected void init() {
        this.mScroller = new OverScroller(getContext());
        setDefaultInterpolator(Interpolators.SCROLL);
        this.mCurrentPage = 0;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        float f = getResources().getDisplayMetrics().density;
        this.mFlingThresholdVelocity = (int) (500.0f * f);
        this.mMinFlingVelocity = (int) (250.0f * f);
        this.mMinSnapVelocity = (int) (f * 1500.0f);
        if (Utilities.ATLEAST_OREO) {
            setDefaultFocusHighlightEnabled(false);
        }
    }

    protected void setDefaultInterpolator(Interpolator interpolator) {
        this.mDefaultInterpolator = interpolator;
        this.mScroller.setInterpolator(interpolator);
    }

    public void initParentViews(View view) {
        int i = this.mPageIndicatorViewId;
        if (i > -1) {
            T t = (T) view.findViewById(i);
            this.mPageIndicator = t;
            t.setMarkersCount(getChildCount());
        }
    }

    public T getPageIndicator() {
        return this.mPageIndicator;
    }

    public int getCurrentPage() {
        return this.mCurrentPage;
    }

    public int getNextPage() {
        int i = this.mNextPage;
        return i != -1 ? i : this.mCurrentPage;
    }

    public int getPageCount() {
        return getChildCount();
    }

    public View getPageAt(int index) {
        return getChildAt(index);
    }

    protected void updateCurrentPageScroll() {
        int i = this.mCurrentPage;
        int scrollForPage = (i < 0 || i >= getPageCount()) ? 0 : getScrollForPage(this.mCurrentPage);
        scrollTo(scrollForPage, 0);
        OverScroller overScroller = this.mScroller;
        overScroller.startScroll(overScroller.getCurrPos(), scrollForPage - this.mScroller.getCurrPos());
        forceFinishScroller(true);
    }

    private void abortScrollerAnimation(boolean resetNextPage) {
        this.mScroller.abortAnimation();
        if (resetNextPage) {
            this.mNextPage = -1;
            pageEndTransition();
        }
    }

    private void forceFinishScroller(boolean resetNextPage) {
        this.mScroller.forceFinished(true);
        if (resetNextPage) {
            this.mNextPage = -1;
            pageEndTransition();
        }
    }

    private int validateNewPage(int newPage) {
        return Utilities.boundToRange(ensureWithinScrollBounds(newPage), 0, getPageCount() - 1);
    }

    private int ensureWithinScrollBounds(int page) {
        int i = !this.mIsRtl ? 1 : -1;
        int scrollForPage = getScrollForPage(page);
        while (true) {
            if (scrollForPage >= this.mMinScrollX) {
                break;
            }
            page += i;
            int scrollForPage2 = getScrollForPage(page);
            if (scrollForPage2 <= scrollForPage) {
                Log.e(TAG, "validateNewPage: failed to find a page > mMinScrollX");
                scrollForPage = scrollForPage2;
                break;
            }
            scrollForPage = scrollForPage2;
        }
        while (true) {
            if (scrollForPage <= this.mMaxScrollX) {
                break;
            }
            page -= i;
            int scrollForPage3 = getScrollForPage(page);
            if (scrollForPage3 >= scrollForPage) {
                Log.e(TAG, "validateNewPage: failed to find a page < mMaxScrollX");
                break;
            }
            scrollForPage = scrollForPage3;
        }
        return page;
    }

    public void setCurrentPage(int currentPage) {
        setCurrentPage(currentPage, -1);
    }

    public void setCurrentPage(int currentPage, int overridePrevPage) {
        if (!this.mScroller.isFinished()) {
            abortScrollerAnimation(true);
        }
        if (getChildCount() == 0) {
            return;
        }
        if (overridePrevPage == -1) {
            overridePrevPage = this.mCurrentPage;
        }
        this.mCurrentPage = validateNewPage(currentPage);
        updateCurrentPageScroll();
        notifyPageSwitchListener(overridePrevPage);
        invalidate();
    }

    protected void notifyPageSwitchListener(int prevPage) {
        updatePageIndicator();
    }

    private void updatePageIndicator() {
        T t = this.mPageIndicator;
        if (t != null) {
            t.setActiveMarker(getNextPage());
        }
    }

    protected void pageBeginTransition() {
        if (this.mIsPageInTransition) {
            return;
        }
        this.mIsPageInTransition = true;
        onPageBeginTransition();
    }

    protected void pageEndTransition() {
        if (this.mIsPageInTransition) {
            this.mIsPageInTransition = false;
            onPageEndTransition();
        }
    }

    protected boolean isPageInTransition() {
        return this.mIsPageInTransition;
    }

    protected void onPageEndTransition() {
        this.mWasInOverscroll = false;
    }

    protected int getUnboundedScrollX() {
        return this.mUnboundedScrollX;
    }

    @Override // android.view.View
    public void scrollBy(int x, int y) {
        scrollTo(getUnboundedScrollX() + x, getScrollY() + y);
    }

    @Override // android.view.View
    public void scrollTo(int x, int y) {
        this.mUnboundedScrollX = x;
        boolean z = this.mIsRtl;
        boolean z2 = !z ? x >= this.mMinScrollX : x <= this.mMaxScrollX;
        boolean z3 = !z ? x <= this.mMaxScrollX : x >= this.mMinScrollX;
        if (!z2 && !z3) {
            this.mSpringOverScrollX = 0.0f;
        }
        if (z2) {
            super.scrollTo(z ? this.mMaxScrollX : this.mMinScrollX, y);
            if (this.mAllowOverScroll) {
                this.mWasInOverscroll = true;
                if (this.mIsRtl) {
                    overScroll(x - this.mMaxScrollX);
                    return;
                } else {
                    overScroll(x - this.mMinScrollX);
                    return;
                }
            }
            return;
        }
        if (z3) {
            super.scrollTo(z ? this.mMinScrollX : this.mMaxScrollX, y);
            if (this.mAllowOverScroll) {
                this.mWasInOverscroll = true;
                if (this.mIsRtl) {
                    overScroll(x - this.mMinScrollX);
                    return;
                } else {
                    overScroll(x - this.mMaxScrollX);
                    return;
                }
            }
            return;
        }
        if (this.mWasInOverscroll) {
            overScroll(0);
            this.mWasInOverscroll = false;
        }
        super.scrollTo(x, y);
    }

    private void sendScrollAccessibilityEvent() {
        if (!AccessibilityManagerCompat.isObservedEventType(getContext(), 4096) || this.mCurrentPage == getNextPage()) {
            return;
        }
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(4096);
        accessibilityEventObtain.setScrollable(true);
        accessibilityEventObtain.setScrollX(getScrollX());
        accessibilityEventObtain.setScrollY(getScrollY());
        accessibilityEventObtain.setMaxScrollX(this.mMaxScrollX);
        accessibilityEventObtain.setMaxScrollY(0);
        sendAccessibilityEventUnchecked(accessibilityEventObtain);
    }

    protected boolean computeScrollHelper() {
        return computeScrollHelper(true);
    }

    protected void announcePageForAccessibility() {
        if (AccessibilityManagerCompat.isAccessibilityEnabled(getContext())) {
            announceForAccessibility(getCurrentPageDescription());
        }
    }

    protected boolean computeScrollHelper(boolean shouldInvalidate) {
        if (this.mScroller.computeScrollOffset()) {
            if (getUnboundedScrollX() != this.mScroller.getCurrPos() || getScrollX() != this.mScroller.getCurrPos()) {
                scrollTo(this.mScroller.getCurrPos(), 0);
            }
            if (!shouldInvalidate) {
                return true;
            }
            invalidate();
            return true;
        }
        if (this.mNextPage != -1 && shouldInvalidate) {
            sendScrollAccessibilityEvent();
            int i = this.mCurrentPage;
            this.mCurrentPage = validateNewPage(this.mNextPage);
            this.mNextPage = -1;
            notifyPageSwitchListener(i);
            if (!this.mIsBeingDragged) {
                pageEndTransition();
            }
            if (canAnnouncePageDescription()) {
                announcePageForAccessibility();
            }
        }
        return false;
    }

    @Override // android.view.View
    public void computeScroll() {
        computeScrollHelper();
    }

    public int getExpectedHeight() {
        return getMeasuredHeight();
    }

    public int getNormalChildHeight() {
        return (((getExpectedHeight() - getPaddingTop()) - getPaddingBottom()) - this.mInsets.top) - this.mInsets.bottom;
    }

    public int getExpectedWidth() {
        return getMeasuredWidth();
    }

    public int getNormalChildWidth() {
        return (((getExpectedWidth() - getPaddingLeft()) - getPaddingRight()) - this.mInsets.left) - this.mInsets.right;
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        this.mIsLayoutValid = false;
        super.requestLayout();
    }

    @Override // android.view.View
    public void forceLayout() {
        this.mIsLayoutValid = false;
        super.forceLayout();
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getChildCount() == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        if (mode == 0 || mode2 == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else if (size <= 0 || size2 <= 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            measureChildren(View.MeasureSpec.makeMeasureSpec((size - this.mInsets.left) - this.mInsets.right, 1073741824), View.MeasureSpec.makeMeasureSpec((size2 - this.mInsets.top) - this.mInsets.bottom, 1073741824));
            setMeasuredDimension(size, size2);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        boolean z;
        int i;
        this.mIsLayoutValid = true;
        int childCount = getChildCount();
        int[] iArr = this.mPageScrolls;
        if (iArr == null || childCount != iArr.length) {
            this.mPageScrolls = new int[childCount];
            z = true;
        } else {
            z = false;
        }
        if (childCount == 0) {
            return;
        }
        boolean z2 = getPageScrolls(this.mPageScrolls, true, SIMPLE_SCROLL_LOGIC) ? true : z;
        LayoutTransition layoutTransition = getLayoutTransition();
        if (layoutTransition != null && layoutTransition.isRunning()) {
            layoutTransition.addTransitionListener(new LayoutTransition.TransitionListener() { // from class: com.android.launcher3.PagedView.1
                @Override // android.animation.LayoutTransition.TransitionListener
                public void startTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                }

                @Override // android.animation.LayoutTransition.TransitionListener
                public void endTransition(LayoutTransition transition, ViewGroup container, View view, int transitionType) {
                    if (transition.isRunning()) {
                        return;
                    }
                    transition.removeTransitionListener(this);
                    PagedView.this.updateMinAndMaxScrollX();
                }
            });
        } else {
            updateMinAndMaxScrollX();
        }
        if (this.mFirstLayout && (i = this.mCurrentPage) >= 0 && i < childCount) {
            updateCurrentPageScroll();
            this.mFirstLayout = false;
        }
        if (this.mScroller.isFinished() && z2) {
            setCurrentPage(getNextPage());
        }
    }

    protected boolean getPageScrolls(int[] outPageScrolls, boolean layoutChildren, ComputePageScrollsLogic scrollLogic) {
        int childCount = getChildCount();
        boolean z = this.mIsRtl;
        if (z) {
            childCount = -1;
        }
        int i = z ? -1 : 1;
        int paddingTop = ((((getPaddingTop() + getMeasuredHeight()) + this.mInsets.top) - this.mInsets.bottom) - getPaddingBottom()) / 2;
        int paddingLeft = this.mInsets.left + getPaddingLeft();
        int width = (getWidth() - getPaddingRight()) - this.mInsets.right;
        boolean z2 = false;
        int childGap = paddingLeft;
        for (int i2 = z ? childCount - 1 : 0; i2 != childCount; i2 += i) {
            View pageAt = getPageAt(i2);
            if (scrollLogic.shouldIncludeView(pageAt)) {
                int measuredWidth = pageAt.getMeasuredWidth();
                int i3 = childGap + measuredWidth;
                if (layoutChildren) {
                    int measuredHeight = pageAt.getMeasuredHeight();
                    int i4 = paddingTop - (measuredHeight / 2);
                    pageAt.layout(childGap, i4, i3, measuredHeight + i4);
                }
                int iMax = this.mIsRtl ? childGap - paddingLeft : Math.max(0, i3 - width);
                if (outPageScrolls[i2] != iMax) {
                    outPageScrolls[i2] = iMax;
                    z2 = true;
                }
                childGap += measuredWidth + this.mPageSpacing + getChildGap();
            }
        }
        return z2;
    }

    protected void updateMinAndMaxScrollX() {
        this.mMinScrollX = computeMinScrollX();
        this.mMaxScrollX = computeMaxScrollX();
    }

    protected int computeMaxScrollX() {
        int childCount = getChildCount();
        if (childCount > 0) {
            return getScrollForPage(this.mIsRtl ? 0 : childCount - 1);
        }
        return 0;
    }

    public void setPageSpacing(int pageSpacing) {
        this.mPageSpacing = pageSpacing;
        requestLayout();
    }

    public int getPageSpacing() {
        return this.mPageSpacing;
    }

    private void dispatchPageCountChanged() {
        T t = this.mPageIndicator;
        if (t != null) {
            t.setMarkersCount(getChildCount());
        }
        invalidate();
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        dispatchPageCountChanged();
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        this.mCurrentPage = validateNewPage(this.mCurrentPage);
        dispatchPageCountChanged();
    }

    protected int getChildOffset(int index) {
        if (index < 0 || index > getChildCount() - 1) {
            return 0;
        }
        return getPageAt(index).getLeft();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View child, Rect rectangle, boolean immediate) {
        int iIndexToPage = indexToPage(indexOfChild(child));
        if (iIndexToPage == this.mCurrentPage && this.mScroller.isFinished()) {
            return false;
        }
        if (immediate) {
            setCurrentPage(iIndexToPage);
            return true;
        }
        snapToPage(iIndexToPage);
        return true;
    }

    @Override // android.view.ViewGroup
    protected boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
        int i = this.mNextPage;
        if (i == -1) {
            i = this.mCurrentPage;
        }
        View pageAt = getPageAt(i);
        if (pageAt != null) {
            return pageAt.requestFocus(direction, previouslyFocusedRect);
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchUnhandledMove(View focused, int direction) {
        if (super.dispatchUnhandledMove(focused, direction)) {
            return true;
        }
        if (this.mIsRtl) {
            if (direction == 17) {
                direction = 66;
            } else if (direction == 66) {
                direction = 17;
            }
        }
        if (direction == 17) {
            if (getCurrentPage() <= 0) {
                return false;
            }
            snapToPage(getCurrentPage() - 1);
            getChildAt(getCurrentPage() - 1).requestFocus(direction);
            return true;
        }
        if (direction != 66 || getCurrentPage() >= getPageCount() - 1) {
            return false;
        }
        snapToPage(getCurrentPage() + 1);
        getChildAt(getCurrentPage() + 1).requestFocus(direction);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        if (getDescendantFocusability() == 393216) {
            return;
        }
        int i = this.mCurrentPage;
        if (i >= 0 && i < getPageCount()) {
            getPageAt(this.mCurrentPage).addFocusables(views, direction, focusableMode);
        }
        if (direction == 17) {
            int i2 = this.mCurrentPage;
            if (i2 > 0) {
                getPageAt(i2 - 1).addFocusables(views, direction, focusableMode);
                return;
            }
            return;
        }
        if (direction != 66 || this.mCurrentPage >= getPageCount() - 1) {
            return;
        }
        getPageAt(this.mCurrentPage + 1).addFocusables(views, direction, focusableMode);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void focusableViewAvailable(View focused) {
        View pageAt = getPageAt(this.mCurrentPage);
        for (View view = focused; view != pageAt; view = (View) view.getParent()) {
            if (view == this || !(view.getParent() instanceof View)) {
                return;
            }
        }
        super.focusableViewAvailable(focused);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        if (disallowIntercept) {
            getPageAt(this.mCurrentPage).cancelLongPress();
        }
        super.requestDisallowInterceptTouchEvent(disallowIntercept);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x003f  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            int r0 = r5.getChildCount()
            r1 = 0
            if (r0 <= 0) goto L9e
            boolean r0 = com.android.launcher3.Utilities.shouldDisableGestures(r6)
            if (r0 == 0) goto Lf
            goto L9e
        Lf:
            r5.acquireVelocityTrackerAndAddMovement(r6)
            int r0 = r6.getAction()
            r2 = 2
            r3 = 1
            if (r0 != r2) goto L1f
            boolean r4 = r5.mIsBeingDragged
            if (r4 == 0) goto L1f
            return r3
        L1f:
            r0 = r0 & 255(0xff, float:3.57E-43)
            r4 = 3
            if (r0 == 0) goto L43
            if (r0 == r3) goto L3f
            if (r0 == r2) goto L36
            if (r0 == r4) goto L3f
            r1 = 6
            if (r0 == r1) goto L2f
            goto L9b
        L2f:
            r5.onSecondaryPointerUp(r6)
            r5.releaseVelocityTracker()
            goto L9b
        L36:
            int r0 = r5.mActivePointerId
            r1 = -1
            if (r0 == r1) goto L9b
            r5.determineScrollingStart(r6)
            goto L9b
        L3f:
            r5.resetTouchState()
            goto L9b
        L43:
            float r0 = r6.getX()
            float r2 = r6.getY()
            r5.mDownMotionX = r0
            r5.mDownMotionY = r2
            r5.mLastMotionX = r0
            r0 = 0
            r5.mLastMotionXRemainder = r0
            r5.mTotalMotionX = r0
            int r6 = r6.getPointerId(r1)
            r5.mActivePointerId = r6
            com.android.launcher3.util.OverScroller r6 = r5.mScroller
            int r6 = r6.getFinalPos()
            com.android.launcher3.util.OverScroller r0 = r5.mScroller
            int r0 = r0.getCurrPos()
            int r6 = r6 - r0
            int r6 = java.lang.Math.abs(r6)
            com.android.launcher3.util.OverScroller r0 = r5.mScroller
            boolean r0 = r0.isFinished()
            if (r0 != 0) goto L7d
            int r0 = r5.mTouchSlop
            int r0 = r0 / r4
            if (r6 >= r0) goto L7b
            goto L7d
        L7b:
            r6 = r1
            goto L7e
        L7d:
            r6 = r3
        L7e:
            if (r6 == 0) goto L99
            r5.mIsBeingDragged = r1
            com.android.launcher3.util.OverScroller r6 = r5.mScroller
            boolean r6 = r6.isFinished()
            if (r6 != 0) goto L9b
            boolean r6 = r5.mFreeScroll
            if (r6 != 0) goto L9b
            int r6 = r5.getNextPage()
            r5.setCurrentPage(r6)
            r5.pageEndTransition()
            goto L9b
        L99:
            r5.mIsBeingDragged = r3
        L9b:
            boolean r5 = r5.mIsBeingDragged
            return r5
        L9e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.PagedView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean isHandlingTouch() {
        return this.mIsBeingDragged;
    }

    protected void determineScrollingStart(MotionEvent ev) {
        determineScrollingStart(ev, 1.0f);
    }

    protected void determineScrollingStart(MotionEvent ev, float touchSlopScale) {
        int iFindPointerIndex = ev.findPointerIndex(this.mActivePointerId);
        if (iFindPointerIndex == -1) {
            return;
        }
        float x = ev.getX(iFindPointerIndex);
        if (((int) Math.abs(x - this.mLastMotionX)) > Math.round(touchSlopScale * ((float) this.mTouchSlop))) {
            this.mIsBeingDragged = true;
            this.mTotalMotionX += Math.abs(this.mLastMotionX - x);
            this.mLastMotionX = x;
            this.mLastMotionXRemainder = 0.0f;
            onScrollInteractionBegin();
            pageBeginTransition();
            requestDisallowInterceptTouchEvent(true);
        }
    }

    protected void cancelCurrentPageLongPress() {
        View pageAt = getPageAt(this.mCurrentPage);
        if (pageAt != null) {
            pageAt.cancelLongPress();
        }
    }

    protected float getScrollProgress(int screenCenter, View v, int page) {
        int measuredWidth;
        int scrollForPage = screenCenter - (getScrollForPage(page) + (getMeasuredWidth() / 2));
        int childCount = getChildCount();
        int i = page + 1;
        if ((scrollForPage < 0 && !this.mIsRtl) || (scrollForPage > 0 && this.mIsRtl)) {
            i = page - 1;
        }
        if (i < 0 || i > childCount - 1) {
            measuredWidth = this.mPageSpacing + v.getMeasuredWidth();
        } else {
            measuredWidth = Math.abs(getScrollForPage(i) - getScrollForPage(page));
        }
        return Math.max(Math.min(scrollForPage / (measuredWidth * 1.0f), 1.0f), -1.0f);
    }

    public int getScrollForPage(int index) {
        int[] iArr = this.mPageScrolls;
        if (iArr == null || index >= iArr.length || index < 0) {
            return 0;
        }
        return iArr[index];
    }

    public int getLayoutTransitionOffsetForPage(int index) {
        int[] iArr = this.mPageScrolls;
        if (iArr == null || index >= iArr.length || index < 0) {
            return 0;
        }
        return (int) (getChildAt(index).getX() - (this.mPageScrolls[index] + (this.mIsRtl ? getPaddingRight() : getPaddingLeft())));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (this.mScroller.isSpringing() && this.mSpringOverScrollX != 0.0f) {
            int iSave = canvas.save();
            canvas.translate(-this.mSpringOverScrollX, 0.0f);
            super.dispatchDraw(canvas);
            canvas.restoreToCount(iSave);
            return;
        }
        super.dispatchDraw(canvas);
    }

    protected void dampedOverScroll(int amount) {
        float f = amount;
        this.mSpringOverScrollX = f;
        if (amount == 0) {
            return;
        }
        int iDampedScroll = OverScroll.dampedScroll(f, getMeasuredWidth());
        this.mSpringOverScrollX = iDampedScroll;
        if (this.mScroller.isSpringing()) {
            invalidate();
        } else {
            super.scrollTo(Utilities.boundToRange(getScrollX(), this.mMinScrollX, this.mMaxScrollX) + iDampedScroll, getScrollY());
            invalidate();
        }
    }

    protected void overScroll(int amount) {
        this.mSpringOverScrollX = amount;
        if (this.mScroller.isSpringing()) {
            invalidate();
            return;
        }
        if (amount == 0) {
            return;
        }
        if (!this.mFreeScroll || this.mScroller.isFinished()) {
            dampedOverScroll(amount);
        } else if (amount < 0) {
            super.scrollTo(this.mMinScrollX + amount, getScrollY());
        } else {
            super.scrollTo(this.mMaxScrollX + amount, getScrollY());
        }
    }

    public void setEnableFreeScroll(boolean freeScroll) {
        boolean z = this.mFreeScroll;
        if (z == freeScroll) {
            return;
        }
        this.mFreeScroll = freeScroll;
        if (freeScroll) {
            setCurrentPage(getNextPage());
        } else if (z) {
            snapToPage(getNextPage());
        }
    }

    protected void setEnableOverscroll(boolean enable) {
        this.mAllowOverScroll = enable;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        int i;
        int i2;
        if (getChildCount() <= 0 || Utilities.shouldDisableGestures(ev)) {
            return false;
        }
        acquireVelocityTrackerAndAddMovement(ev);
        int action = ev.getAction() & 255;
        if (action == 0) {
            if (!this.mScroller.isFinished()) {
                abortScrollerAnimation(false);
            }
            float x = ev.getX();
            this.mLastMotionX = x;
            this.mDownMotionX = x;
            this.mDownMotionY = ev.getY();
            this.mLastMotionXRemainder = 0.0f;
            this.mTotalMotionX = 0.0f;
            this.mActivePointerId = ev.getPointerId(0);
            if (this.mIsBeingDragged) {
                onScrollInteractionBegin();
                pageBeginTransition();
            }
        } else if (action == 1) {
            if (this.mIsBeingDragged) {
                float x2 = ev.getX(ev.findPointerIndex(this.mActivePointerId));
                VelocityTracker velocityTracker = this.mVelocityTracker;
                velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
                int xVelocity = (int) velocityTracker.getXVelocity(this.mActivePointerId);
                int i3 = (int) (x2 - this.mDownMotionX);
                float measuredWidth = getPageAt(this.mCurrentPage).getMeasuredWidth();
                boolean z = ((float) Math.abs(i3)) > 0.4f * measuredWidth;
                float fAbs = this.mTotalMotionX + Math.abs((this.mLastMotionX + this.mLastMotionXRemainder) - x2);
                this.mTotalMotionX = fAbs;
                boolean z2 = fAbs > ((float) this.mTouchSlop) && shouldFlingForVelocity(xVelocity);
                boolean z3 = this.mIsRtl;
                boolean z4 = !z3 ? i3 >= 0 : i3 <= 0;
                boolean z5 = !z3 ? xVelocity >= 0 : xVelocity <= 0;
                if (this.mFreeScroll) {
                    if (!this.mScroller.isFinished()) {
                        abortScrollerAnimation(true);
                    }
                    int scrollX = getScrollX();
                    if ((scrollX >= this.mMaxScrollX && (z5 || !z2)) || (scrollX <= this.mMinScrollX && (!z5 || !z2))) {
                        this.mScroller.springBack(getScrollX(), this.mMinScrollX, this.mMaxScrollX);
                        this.mNextPage = getPageNearestToCenterOfScreen();
                    } else {
                        this.mScroller.setInterpolator(this.mDefaultInterpolator);
                        this.mScroller.fling(scrollX, -xVelocity, this.mMinScrollX, this.mMaxScrollX, Math.round(getWidth() * 0.5f * 0.07f));
                        int finalPos = this.mScroller.getFinalPos();
                        this.mNextPage = getPageNearestToCenterOfScreen(finalPos);
                        int scrollForPage = getScrollForPage(!this.mIsRtl ? 0 : getPageCount() - 1);
                        int scrollForPage2 = getScrollForPage(this.mIsRtl ? 0 : getPageCount() - 1);
                        int scrollForPage3 = this.mMinScrollX;
                        if (finalPos > scrollForPage3 && finalPos < (i = this.mMaxScrollX)) {
                            if (finalPos >= (scrollForPage + scrollForPage3) / 2) {
                                scrollForPage3 = finalPos > (scrollForPage2 + i) / 2 ? i : getScrollForPage(this.mNextPage);
                            }
                            this.mScroller.setFinalPos(scrollForPage3);
                            int duration = 270 - this.mScroller.getDuration();
                            if (duration > 0) {
                                this.mScroller.extendDuration(duration);
                            }
                        }
                    }
                    invalidate();
                } else {
                    if (Math.abs(i3) > measuredWidth * RETURN_TO_ORIGINAL_PAGE_THRESHOLD && Math.signum(xVelocity) != Math.signum(i3) && z2) {
                        i = 1;
                    }
                    if (((z && !z4 && !z2) || (z2 && !z5)) && (i2 = this.mCurrentPage) > 0) {
                        if (i == 0) {
                            i2--;
                        }
                        snapToPageWithVelocity(i2, xVelocity);
                    } else if (((z && z4 && !z2) || (z2 && z5)) && this.mCurrentPage < getChildCount() - 1) {
                        int i4 = this.mCurrentPage;
                        if (i == 0) {
                            i4++;
                        }
                        snapToPageWithVelocity(i4, xVelocity);
                    } else {
                        snapToDestination();
                    }
                }
                onScrollInteractionEnd();
            }
            resetTouchState();
        } else if (action != 2) {
            if (action == 3) {
                if (this.mIsBeingDragged) {
                    snapToDestination();
                    onScrollInteractionEnd();
                }
                resetTouchState();
            } else if (action == 6) {
                onSecondaryPointerUp(ev);
                releaseVelocityTracker();
            }
        } else if (this.mIsBeingDragged) {
            int iFindPointerIndex = ev.findPointerIndex(this.mActivePointerId);
            if (iFindPointerIndex == -1) {
                return true;
            }
            float x3 = ev.getX(iFindPointerIndex);
            float f = (this.mLastMotionX + this.mLastMotionXRemainder) - x3;
            this.mTotalMotionX += Math.abs(f);
            if (Math.abs(f) >= 1.0f) {
                int i5 = (int) f;
                scrollBy(i5, 0);
                this.mLastMotionX = x3;
                this.mLastMotionXRemainder = f - i5;
            } else {
                awakenScrollBars();
            }
        } else {
            determineScrollingStart(ev);
        }
        return true;
    }

    protected boolean shouldFlingForVelocity(int velocityX) {
        return Math.abs(velocityX) > this.mFlingThresholdVelocity;
    }

    private void resetTouchState() {
        releaseVelocityTracker();
        this.mIsBeingDragged = false;
        this.mActivePointerId = -1;
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent event) {
        float f;
        float axisValue;
        if ((event.getSource() & 2) != 0 && event.getAction() == 8) {
            if ((event.getMetaState() & 1) != 0) {
                axisValue = event.getAxisValue(9);
                f = 0.0f;
            } else {
                f = -event.getAxisValue(9);
                axisValue = event.getAxisValue(10);
            }
            if (axisValue != 0.0f || f != 0.0f) {
                boolean z = false;
                if (!this.mIsRtl ? axisValue > 0.0f || f > 0.0f : axisValue < 0.0f || f < 0.0f) {
                    z = true;
                }
                if (z) {
                    scrollRight();
                } else {
                    scrollLeft();
                }
                return true;
            }
        }
        return super.onGenericMotionEvent(event);
    }

    private void acquireVelocityTrackerAndAddMovement(MotionEvent ev) {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(ev);
    }

    private void releaseVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    private void onSecondaryPointerUp(MotionEvent ev) {
        int action = (ev.getAction() & 65280) >> 8;
        if (ev.getPointerId(action) == this.mActivePointerId) {
            int i = action == 0 ? 1 : 0;
            float x = ev.getX(i);
            this.mDownMotionX = x;
            this.mLastMotionX = x;
            this.mLastMotionXRemainder = 0.0f;
            this.mActivePointerId = ev.getPointerId(i);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View child, View focused) {
        super.requestChildFocus(child, focused);
        int iIndexToPage = indexToPage(indexOfChild(child));
        if (iIndexToPage < 0 || iIndexToPage == getCurrentPage() || isInTouchMode()) {
            return;
        }
        snapToPage(iIndexToPage);
    }

    public int getPageNearestToCenterOfScreen() {
        return getPageNearestToCenterOfScreen(getScrollX());
    }

    private int getPageNearestToCenterOfScreen(int scaledScrollX) {
        int measuredWidth = scaledScrollX + (getMeasuredWidth() / 2);
        int childCount = getChildCount();
        int i = Integer.MAX_VALUE;
        int i2 = -1;
        for (int i3 = 0; i3 < childCount; i3++) {
            int iAbs = Math.abs((getChildOffset(i3) + (getPageAt(i3).getMeasuredWidth() / 2)) - measuredWidth);
            if (iAbs < i) {
                i2 = i3;
                i = iAbs;
            }
        }
        return i2;
    }

    protected void snapToDestination() {
        snapToPage(getPageNearestToCenterOfScreen(), getPageSnapDuration());
    }

    protected boolean isInOverScroll() {
        return getScrollX() > this.mMaxScrollX || getScrollX() < this.mMinScrollX;
    }

    protected int getPageSnapDuration() {
        return isInOverScroll() ? 270 : 750;
    }

    private float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((float) (((double) (f - 0.5f)) * 0.4712389167638204d));
    }

    protected boolean snapToPageWithVelocity(int whichPage, int velocity) {
        int iValidateNewPage = validateNewPage(whichPage);
        int measuredWidth = getMeasuredWidth() / 2;
        int scrollForPage = getScrollForPage(iValidateNewPage) - getUnboundedScrollX();
        if (Math.abs(velocity) < this.mMinFlingVelocity) {
            return snapToPage(iValidateNewPage, 750);
        }
        float fMin = Math.min(1.0f, (Math.abs(scrollForPage) * 1.0f) / (measuredWidth * 2));
        float f = measuredWidth;
        float fDistanceInfluenceForSnapDuration = f + (distanceInfluenceForSnapDuration(fMin) * f);
        float fMax = Math.max(this.mMinSnapVelocity, Math.abs(velocity));
        int iRound = Math.round(Math.abs(fDistanceInfluenceForSnapDuration / fMax) * 1000.0f) * 4;
        if (FeatureFlags.QUICKSTEP_SPRINGS.get()) {
            return snapToPage(iValidateNewPage, scrollForPage, iRound, false, null, fMax * Math.signum(r0 - getUnboundedScrollX()), true);
        }
        return snapToPage(iValidateNewPage, scrollForPage, iRound);
    }

    public boolean snapToPage(int whichPage) {
        return snapToPage(whichPage, 750);
    }

    public boolean snapToPageImmediately(int whichPage) {
        return snapToPage(whichPage, 750, true, null);
    }

    public boolean snapToPage(int whichPage, int duration) {
        return snapToPage(whichPage, duration, false, null);
    }

    public boolean snapToPage(int whichPage, int duration, TimeInterpolator interpolator) {
        return snapToPage(whichPage, duration, false, interpolator);
    }

    protected boolean snapToPage(int whichPage, int duration, boolean immediate, TimeInterpolator interpolator) {
        int iValidateNewPage = validateNewPage(whichPage);
        return snapToPage(iValidateNewPage, getScrollForPage(iValidateNewPage) - getUnboundedScrollX(), duration, immediate, interpolator, 0.0f, false);
    }

    protected boolean snapToPage(int whichPage, int delta, int duration) {
        return snapToPage(whichPage, delta, duration, false, null, 0.0f, false);
    }

    protected boolean snapToPage(int whichPage, int delta, int duration, boolean immediate, TimeInterpolator interpolator, float velocity, boolean spring) {
        if (this.mFirstLayout) {
            setCurrentPage(whichPage);
            return false;
        }
        this.mNextPage = validateNewPage(whichPage);
        awakenScrollBars(duration);
        if (immediate) {
            duration = 0;
        } else if (duration == 0) {
            duration = Math.abs(delta);
        }
        if (duration != 0) {
            pageBeginTransition();
        }
        if (!this.mScroller.isFinished()) {
            abortScrollerAnimation(false);
        }
        if (interpolator != null) {
            this.mScroller.setInterpolator(interpolator);
        } else {
            this.mScroller.setInterpolator(this.mDefaultInterpolator);
        }
        if (spring && FeatureFlags.QUICKSTEP_SPRINGS.get()) {
            this.mScroller.startScrollSpring(getUnboundedScrollX(), delta, duration, velocity);
        } else {
            this.mScroller.startScroll(getUnboundedScrollX(), delta, duration);
        }
        updatePageIndicator();
        if (immediate) {
            computeScroll();
            pageEndTransition();
        }
        invalidate();
        return Math.abs(delta) > 0;
    }

    public boolean scrollLeft() {
        if (getNextPage() <= 0) {
            return false;
        }
        snapToPage(getNextPage() - 1);
        return true;
    }

    public boolean scrollRight() {
        if (getNextPage() >= getChildCount() - 1) {
            return false;
        }
        snapToPage(getNextPage() + 1);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return ScrollView.class.getName();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
        boolean zIsPageOrderFlipped = isPageOrderFlipped();
        info.setScrollable(getPageCount() > 1);
        if (getCurrentPage() < getPageCount() - 1) {
            info.addAction(zIsPageOrderFlipped ? 8192 : 4096);
        }
        if (getCurrentPage() > 0) {
            info.addAction(zIsPageOrderFlipped ? 4096 : 8192);
        }
        info.setLongClickable(false);
        info.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_LONG_CLICK);
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEvent(int eventType) {
        if (eventType != 4096) {
            super.sendAccessibilityEvent(eventType);
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
        event.setScrollable(getPageCount() > 1);
    }

    @Override // android.view.View
    public boolean performAccessibilityAction(int action, Bundle arguments) {
        if (super.performAccessibilityAction(action, arguments)) {
            return true;
        }
        boolean zIsPageOrderFlipped = isPageOrderFlipped();
        if (action == 4096) {
            if (zIsPageOrderFlipped) {
                if (!scrollLeft()) {
                    return false;
                }
            } else if (!scrollRight()) {
                return false;
            }
            return true;
        }
        if (action != 8192) {
            return false;
        }
        if (zIsPageOrderFlipped) {
            if (!scrollRight()) {
                return false;
            }
        } else if (!scrollLeft()) {
            return false;
        }
        return true;
    }

    protected String getCurrentPageDescription() {
        return getContext().getString(R.string.default_scroll_format, Integer.valueOf(getNextPage() + 1), Integer.valueOf(getChildCount()));
    }

    protected float getDownMotionX() {
        return this.mDownMotionX;
    }

    protected float getDownMotionY() {
        return this.mDownMotionY;
    }

    public int[] getVisibleChildrenRange() {
        float f = 0.0f;
        float measuredWidth = getMeasuredWidth() + 0.0f;
        float scaleX = getScaleX();
        if (scaleX < 1.0f && scaleX > 0.0f) {
            float measuredWidth2 = getMeasuredWidth() / 2;
            f = measuredWidth2 - ((measuredWidth2 - 0.0f) / scaleX);
            measuredWidth = ((measuredWidth - measuredWidth2) / scaleX) + measuredWidth2;
        }
        int childCount = getChildCount();
        int i = -1;
        int i2 = -1;
        for (int i3 = 0; i3 < childCount; i3++) {
            float left = (r8.getLeft() + getPageAt(i3).getTranslationX()) - getScrollX();
            if (left <= measuredWidth && left + r8.getMeasuredWidth() >= f) {
                if (i == -1) {
                    i = i3;
                }
                i2 = i3;
            }
        }
        int[] iArr = this.mTmpIntPair;
        iArr[0] = i;
        iArr[1] = i2;
        return iArr;
    }
}
