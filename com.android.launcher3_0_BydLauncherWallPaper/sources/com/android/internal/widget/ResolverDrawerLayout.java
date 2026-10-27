package com.android.internal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.metrics.LogMaker;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.OverScroller;
import android.widget.ScrollView;
import com.android.internal.R;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.widget.RecyclerView;

/* JADX INFO: loaded from: classes4.dex */
public class ResolverDrawerLayout extends ViewGroup {
    private static final String TAG = "ResolverDrawerLayout";
    private int mActivePointerId;
    private int mAlwaysShowHeight;
    private float mCollapseOffset;
    private int mCollapsibleHeight;
    private int mCollapsibleHeightReserved;
    private boolean mDismissLocked;
    private boolean mDismissOnScrollerFinished;
    private float mDragRemainder;
    private float mInitialTouchX;
    private float mInitialTouchY;
    private boolean mIsDragging;
    private final boolean mIsMaxCollapsedHeightSmallExplicit;
    private float mLastTouchY;
    private int mMaxCollapsedHeight;
    private int mMaxCollapsedHeightSmall;
    private int mMaxWidth;
    private MetricsLogger mMetricsLogger;
    private final float mMinFlingVelocity;
    private AbsListView mNestedListChild;
    private RecyclerView mNestedRecyclerChild;
    private OnCollapsedChangedListener mOnCollapsedChangedListener;
    private OnDismissedListener mOnDismissedListener;
    private boolean mOpenOnClick;
    private boolean mOpenOnLayout;
    private RunOnDismissedListener mRunOnDismissedListener;
    private Drawable mScrollIndicatorDrawable;
    private final OverScroller mScroller;
    private boolean mShowAtTop;
    private boolean mSmallCollapsed;
    private final Rect mTempRect;
    private int mTopOffset;
    private final ViewTreeObserver.OnTouchModeChangeListener mTouchModeChangeListener;
    private final int mTouchSlop;
    private int mUncollapsibleHeight;
    private final VelocityTracker mVelocityTracker;

    public interface OnCollapsedChangedListener {
        void onCollapsedChanged(boolean z);
    }

    public interface OnDismissedListener {
        void onDismissed();
    }

    public ResolverDrawerLayout(Context context) {
        this(context, null);
    }

    public ResolverDrawerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ResolverDrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDragRemainder = 0.0f;
        this.mActivePointerId = -1;
        this.mTempRect = new Rect();
        this.mTouchModeChangeListener = new ViewTreeObserver.OnTouchModeChangeListener() { // from class: com.android.internal.widget.ResolverDrawerLayout.1
            @Override // android.view.ViewTreeObserver.OnTouchModeChangeListener
            public void onTouchModeChanged(boolean z) {
                if (z || !ResolverDrawerLayout.this.hasFocus()) {
                    return;
                }
                ResolverDrawerLayout resolverDrawerLayout = ResolverDrawerLayout.this;
                if (resolverDrawerLayout.isDescendantClipped(resolverDrawerLayout.getFocusedChild())) {
                    ResolverDrawerLayout.this.smoothScrollTo(0, 0.0f);
                }
            }
        };
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ResolverDrawerLayout, i, 0);
        this.mMaxWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, -1);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        this.mMaxCollapsedHeight = dimensionPixelSize;
        this.mMaxCollapsedHeightSmall = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, dimensionPixelSize);
        this.mIsMaxCollapsedHeightSmallExplicit = typedArrayObtainStyledAttributes.hasValue(2);
        this.mShowAtTop = typedArrayObtainStyledAttributes.getBoolean(3, false);
        typedArrayObtainStyledAttributes.recycle();
        this.mScrollIndicatorDrawable = this.mContext.getDrawable(R.drawable.scroll_indicator_material);
        this.mScroller = new OverScroller(context, AnimationUtils.loadInterpolator(context, 17563653));
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMinFlingVelocity = r4.getScaledMinimumFlingVelocity();
        setImportantForAccessibility(1);
    }

    public void setMaxCollapsedHeight(int i) {
        if (i == this.mMaxCollapsedHeight) {
            return;
        }
        this.mMaxCollapsedHeight = i;
        if (!this.mIsMaxCollapsedHeightSmallExplicit) {
            this.mMaxCollapsedHeightSmall = i;
        }
        requestLayout();
    }

    public void setSmallCollapsed(boolean z) {
        this.mSmallCollapsed = z;
        requestLayout();
    }

    public boolean isSmallCollapsed() {
        return this.mSmallCollapsed;
    }

    public boolean isCollapsed() {
        return this.mCollapseOffset > 0.0f;
    }

    public void setShowAtTop(boolean z) {
        this.mShowAtTop = z;
        invalidate();
        requestLayout();
    }

    public boolean getShowAtTop() {
        return this.mShowAtTop;
    }

    public void setCollapsed(boolean z) {
        if (!isLaidOut()) {
            this.mOpenOnLayout = !z;
        } else {
            smoothScrollTo(z ? this.mCollapsibleHeight : 0, 0.0f);
        }
    }

    public void setCollapsibleHeightReserved(int i) {
        int i2 = this.mCollapsibleHeightReserved;
        this.mCollapsibleHeightReserved = i;
        int i3 = i - i2;
        if (i3 != 0 && this.mIsDragging) {
            this.mLastTouchY -= i3;
        }
        int i4 = this.mCollapsibleHeight;
        this.mCollapsibleHeight = Math.min(i4, getMaxCollapsedHeight());
        if (updateCollapseOffset(i4, !isDragging())) {
            return;
        }
        invalidate();
    }

    public void setDismissLocked(boolean z) {
        this.mDismissLocked = z;
    }

    private boolean isMoving() {
        return this.mIsDragging || !this.mScroller.isFinished();
    }

    private boolean isDragging() {
        return this.mIsDragging || getNestedScrollAxes() == 2;
    }

    private boolean updateCollapseOffset(int i, boolean z) {
        int i2;
        if (i == this.mCollapsibleHeight) {
            return false;
        }
        if (getShowAtTop()) {
            this.mCollapseOffset = 0.0f;
            return false;
        }
        if (isLaidOut()) {
            float f = this.mCollapseOffset;
            boolean z2 = f != 0.0f;
            if (z && i < (i2 = this.mCollapsibleHeight) && f == i) {
                this.mCollapseOffset = i2;
            } else {
                this.mCollapseOffset = Math.min(f, this.mCollapsibleHeight);
            }
            boolean z3 = this.mCollapseOffset != 0.0f;
            if (z2 != z3) {
                onCollapsedChanged(z3);
            }
        } else {
            this.mCollapseOffset = this.mOpenOnLayout ? 0.0f : this.mCollapsibleHeight;
        }
        return true;
    }

    private int getMaxCollapsedHeight() {
        return (isSmallCollapsed() ? this.mMaxCollapsedHeightSmall : this.mMaxCollapsedHeight) + this.mCollapsibleHeightReserved;
    }

    public void setOnDismissedListener(OnDismissedListener onDismissedListener) {
        this.mOnDismissedListener = onDismissedListener;
    }

    private boolean isDismissable() {
        return (this.mOnDismissedListener == null || this.mDismissLocked) ? false : true;
    }

    public void setOnCollapsedChangedListener(OnCollapsedChangedListener onCollapsedChangedListener) {
        this.mOnCollapsedChangedListener = onCollapsedChangedListener;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0065  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r9) {
        /*
            r8 = this;
            int r0 = r9.getActionMasked()
            if (r0 != 0) goto Lb
            android.view.VelocityTracker r1 = r8.mVelocityTracker
            r1.clear()
        Lb:
            android.view.VelocityTracker r1 = r8.mVelocityTracker
            r1.addMovement(r9)
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L69
            if (r0 == r2) goto L65
            r3 = 2
            if (r0 == r3) goto L24
            r3 = 3
            if (r0 == r3) goto L65
            r3 = 6
            if (r0 == r3) goto L20
            goto L89
        L20:
            r8.onSecondaryPointerUp(r9)
            goto L89
        L24:
            float r0 = r9.getX()
            float r4 = r9.getY()
            float r5 = r8.mInitialTouchY
            float r5 = r4 - r5
            float r6 = java.lang.Math.abs(r5)
            int r7 = r8.mTouchSlop
            float r7 = (float) r7
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 <= 0) goto L89
            android.view.View r0 = r8.findChildUnder(r0, r4)
            if (r0 == 0) goto L89
            int r0 = r8.getNestedScrollAxes()
            r0 = r0 & r3
            if (r0 != 0) goto L89
            int r9 = r9.getPointerId(r1)
            r8.mActivePointerId = r9
            r8.mIsDragging = r2
            float r9 = r8.mLastTouchY
            int r0 = r8.mTouchSlop
            float r3 = (float) r0
            float r3 = r9 - r3
            float r5 = r5 + r9
            float r0 = (float) r0
            float r9 = r9 + r0
            float r9 = java.lang.Math.min(r5, r9)
            float r9 = java.lang.Math.max(r3, r9)
            r8.mLastTouchY = r9
            goto L89
        L65:
            r8.resetTouch()
            goto L89
        L69:
            float r0 = r9.getX()
            float r9 = r9.getY()
            r8.mInitialTouchX = r0
            r8.mLastTouchY = r9
            r8.mInitialTouchY = r9
            boolean r9 = r8.isListChildUnderClipped(r0, r9)
            if (r9 == 0) goto L86
            float r9 = r8.mCollapseOffset
            r0 = 0
            int r9 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r9 <= 0) goto L86
            r9 = r2
            goto L87
        L86:
            r9 = r1
        L87:
            r8.mOpenOnClick = r9
        L89:
            boolean r9 = r8.mIsDragging
            if (r9 == 0) goto L90
            r8.abortAnimation()
        L90:
            boolean r9 = r8.mIsDragging
            if (r9 != 0) goto L98
            boolean r8 = r8.mOpenOnClick
            if (r8 == 0) goto L99
        L98:
            r1 = r2
        L99:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.widget.ResolverDrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    private boolean isNestedListChildScrolled() {
        AbsListView absListView = this.mNestedListChild;
        if (absListView == null || absListView.getChildCount() <= 0) {
            return false;
        }
        return this.mNestedListChild.getFirstVisiblePosition() > 0 || this.mNestedListChild.getChildAt(0).getTop() < 0;
    }

    private boolean isNestedRecyclerChildScrolled() {
        RecyclerView recyclerView = this.mNestedRecyclerChild;
        if (recyclerView == null || recyclerView.getChildCount() <= 0) {
            return false;
        }
        RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = this.mNestedRecyclerChild.findViewHolderForAdapterPosition(0);
        return viewHolderFindViewHolderForAdapterPosition == null || viewHolderFindViewHolderForAdapterPosition.itemView.getTop() < 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01ac  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            Method dump skipped, instruction units count: 522
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.widget.ResolverDrawerLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void scrollNestedScrollableChildBackToTop() {
        if (isNestedListChildScrolled()) {
            this.mNestedListChild.smoothScrollToPosition(0);
        } else if (isNestedRecyclerChildScrolled()) {
            this.mNestedRecyclerChild.smoothScrollToPosition(0);
        }
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            int i = actionIndex == 0 ? 1 : 0;
            this.mInitialTouchX = motionEvent.getX(i);
            float y = motionEvent.getY(i);
            this.mLastTouchY = y;
            this.mInitialTouchY = y;
            this.mActivePointerId = motionEvent.getPointerId(i);
        }
    }

    private void resetTouch() {
        this.mActivePointerId = -1;
        this.mIsDragging = false;
        this.mOpenOnClick = false;
        this.mLastTouchY = 0.0f;
        this.mInitialTouchY = 0.0f;
        this.mInitialTouchX = 0.0f;
        this.mVelocityTracker.clear();
    }

    private void dismiss() {
        RunOnDismissedListener runOnDismissedListener = new RunOnDismissedListener();
        this.mRunOnDismissedListener = runOnDismissedListener;
        post(runOnDismissedListener);
    }

    @Override // android.view.View
    public void computeScroll() {
        super.computeScroll();
        if (this.mScroller.computeScrollOffset()) {
            boolean z = !this.mScroller.isFinished();
            performDrag(this.mScroller.getCurrY() - this.mCollapseOffset);
            if (z) {
                postInvalidateOnAnimation();
            } else {
                if (!this.mDismissOnScrollerFinished || this.mOnDismissedListener == null) {
                    return;
                }
                dismiss();
            }
        }
    }

    private void abortAnimation() {
        this.mScroller.abortAnimation();
        this.mRunOnDismissedListener = null;
        this.mDismissOnScrollerFinished = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v7 */
    private float performDrag(float f) {
        if (getShowAtTop()) {
            return 0.0f;
        }
        float fMax = Math.max(0.0f, Math.min(this.mCollapseOffset + f, this.mCollapsibleHeight + this.mUncollapsibleHeight));
        float f2 = this.mCollapseOffset;
        if (fMax == f2) {
            return 0.0f;
        }
        float f3 = fMax - f2;
        float f4 = this.mDragRemainder + (f3 - ((int) f3));
        this.mDragRemainder = f4;
        if (f4 >= 1.0f) {
            this.mDragRemainder = f4 - 1.0f;
            f3 += 1.0f;
        } else if (f4 <= -1.0f) {
            this.mDragRemainder = f4 + 1.0f;
            f3 -= 1.0f;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (!((LayoutParams) childAt.getLayoutParams()).ignoreOffset) {
                childAt.offsetTopAndBottom((int) f3);
            }
        }
        boolean z = this.mCollapseOffset != 0.0f;
        this.mCollapseOffset = fMax;
        this.mTopOffset = (int) (this.mTopOffset + f3);
        ?? r4 = fMax == 0.0f ? 0 : 1;
        if (z != r4) {
            onCollapsedChanged(r4);
            getMetricsLogger().write(new LogMaker(1651).setSubtype(r4));
        }
        onScrollChanged(0, (int) fMax, 0, (int) (fMax - f3));
        postInvalidateOnAnimation();
        return f3;
    }

    private void onCollapsedChanged(boolean z) {
        notifyViewAccessibilityStateChangedIfNeeded(0);
        if (this.mScrollIndicatorDrawable != null) {
            setWillNotDraw(!z);
        }
        OnCollapsedChangedListener onCollapsedChangedListener = this.mOnCollapsedChangedListener;
        if (onCollapsedChangedListener != null) {
            onCollapsedChangedListener.onCollapsedChanged(z);
        }
    }

    void dispatchOnDismissed() {
        OnDismissedListener onDismissedListener = this.mOnDismissedListener;
        if (onDismissedListener != null) {
            onDismissedListener.onDismissed();
        }
        RunOnDismissedListener runOnDismissedListener = this.mRunOnDismissedListener;
        if (runOnDismissedListener != null) {
            removeCallbacks(runOnDismissedListener);
            this.mRunOnDismissedListener = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void smoothScrollTo(int i, float f) {
        int iAbs;
        abortAnimation();
        int i2 = (int) this.mCollapseOffset;
        int i3 = i - i2;
        if (i3 == 0) {
            return;
        }
        int height = getHeight();
        int i4 = height / 2;
        float f2 = height;
        float f3 = i4;
        float fDistanceInfluenceForSnapDuration = f3 + (distanceInfluenceForSnapDuration(Math.min(1.0f, (Math.abs(i3) * 1.0f) / f2)) * f3);
        float fAbs = Math.abs(f);
        if (fAbs > 0.0f) {
            iAbs = Math.round(Math.abs(fDistanceInfluenceForSnapDuration / fAbs) * 1000.0f) * 4;
        } else {
            iAbs = (int) (((Math.abs(i3) / f2) + 1.0f) * 100.0f);
        }
        this.mScroller.startScroll(0, i2, 0, i3, Math.min(iAbs, 300));
        postInvalidateOnAnimation();
    }

    private float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((float) (((double) (f - 0.5f)) * 0.4712389167638204d));
    }

    private View findChildUnder(float f, float f2) {
        return findChildUnder(this, f, f2);
    }

    private static View findChildUnder(ViewGroup viewGroup, float f, float f2) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (isChildUnder(childAt, f, f2)) {
                return childAt;
            }
        }
        return null;
    }

    private View findListChildUnder(float f, float f2) {
        View viewFindChildUnder = findChildUnder(f, f2);
        while (viewFindChildUnder != null) {
            f -= viewFindChildUnder.getX();
            f2 -= viewFindChildUnder.getY();
            if (viewFindChildUnder instanceof AbsListView) {
                return findChildUnder((ViewGroup) viewFindChildUnder, f, f2);
            }
            viewFindChildUnder = viewFindChildUnder instanceof ViewGroup ? findChildUnder((ViewGroup) viewFindChildUnder, f, f2) : null;
        }
        return viewFindChildUnder;
    }

    private boolean isListChildUnderClipped(float f, float f2) {
        View viewFindListChildUnder = findListChildUnder(f, f2);
        return viewFindListChildUnder != null && isDescendantClipped(viewFindListChildUnder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDescendantClipped(View view) {
        this.mTempRect.set(0, 0, view.getWidth(), view.getHeight());
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        if (view.getParent() != this) {
            ViewParent parent = view.getParent();
            while (parent != this) {
                view = parent;
                parent = view.getParent();
            }
        }
        int height = getHeight() - getPaddingBottom();
        int childCount = getChildCount();
        for (int iIndexOfChild = indexOfChild(view) + 1; iIndexOfChild < childCount; iIndexOfChild++) {
            View childAt = getChildAt(iIndexOfChild);
            if (childAt.getVisibility() != 8) {
                height = Math.min(height, childAt.getTop());
            }
        }
        return this.mTempRect.bottom > height;
    }

    private static boolean isChildUnder(View view, float f, float f2) {
        float x = view.getX();
        float y = view.getY();
        return f >= x && f2 >= y && f < ((float) view.getWidth()) + x && f2 < ((float) view.getHeight()) + y;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        super.requestChildFocus(view, view2);
        if (isInTouchMode() || !isDescendantClipped(view2)) {
            return;
        }
        smoothScrollTo(0, 0.0f);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnTouchModeChangeListener(this.mTouchModeChangeListener);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnTouchModeChangeListener(this.mTouchModeChangeListener);
        abortAnimation();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        if ((i & 2) == 0) {
            return false;
        }
        if (view2 instanceof AbsListView) {
            this.mNestedListChild = (AbsListView) view2;
        }
        if (!(view2 instanceof RecyclerView)) {
            return true;
        }
        this.mNestedRecyclerChild = (RecyclerView) view2;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i) {
        super.onNestedScrollAccepted(view, view2, i);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        super.onStopNestedScroll(view);
        if (this.mScroller.isFinished()) {
            float f = this.mCollapseOffset;
            int i = this.mCollapsibleHeight;
            if (f < i / 2) {
                i = 0;
            }
            smoothScrollTo(i, 0.0f);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        if (i4 < 0) {
            performDrag(-i4);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        if (i2 > 0) {
            iArr[1] = (int) (-performDrag(-i2));
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f, float f2) {
        if (getShowAtTop() || f2 <= this.mMinFlingVelocity || this.mCollapseOffset == 0.0f) {
            return false;
        }
        smoothScrollTo(0, f2);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x004c  */
    @Override // android.view.ViewGroup, android.view.ViewParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onNestedFling(android.view.View r4, float r5, float r6, boolean r7) {
        /*
            r3 = this;
            r4 = 0
            if (r7 != 0) goto L57
            float r5 = java.lang.Math.abs(r6)
            float r7 = r3.mMinFlingVelocity
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 <= 0) goto L57
            boolean r5 = r3.getShowAtTop()
            r7 = 1
            r0 = 0
            if (r5 == 0) goto L30
            boolean r5 = r3.isDismissable()
            if (r5 == 0) goto L26
            int r5 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r5 <= 0) goto L26
            r3.abortAnimation()
            r3.dismiss()
            goto L56
        L26:
            int r5 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r5 >= 0) goto L2c
            int r4 = r3.mCollapsibleHeight
        L2c:
            r3.smoothScrollTo(r4, r6)
            goto L56
        L30:
            boolean r5 = r3.isDismissable()
            if (r5 == 0) goto L4c
            int r5 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r5 >= 0) goto L4c
            float r5 = r3.mCollapseOffset
            int r1 = r3.mCollapsibleHeight
            float r2 = (float) r1
            int r5 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r5 <= 0) goto L4c
            int r4 = r3.mUncollapsibleHeight
            int r1 = r1 + r4
            r3.smoothScrollTo(r1, r6)
            r3.mDismissOnScrollerFinished = r7
            goto L56
        L4c:
            int r5 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r5 <= 0) goto L51
            goto L53
        L51:
            int r4 = r3.mCollapsibleHeight
        L53:
            r3.smoothScrollTo(r4, r6)
        L56:
            return r7
        L57:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.widget.ResolverDrawerLayout.onNestedFling(android.view.View, float, float, boolean):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0043  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean performAccessibilityActionCommon(int r6) {
        /*
            r5 = this;
            r0 = 4096(0x1000, float:5.74E-42)
            r1 = 0
            r2 = 1
            r3 = 0
            if (r6 == r0) goto L43
            r0 = 262144(0x40000, float:3.67342E-40)
            if (r6 == r0) goto L43
            r0 = 524288(0x80000, float:7.34684E-40)
            if (r6 == r0) goto L36
            r0 = 1048576(0x100000, float:1.469368E-39)
            if (r6 == r0) goto L19
            r0 = 16908346(0x102003a, float:2.3877392E-38)
            if (r6 == r0) goto L43
            goto L4d
        L19:
            float r6 = r5.mCollapseOffset
            int r0 = r5.mCollapsibleHeight
            int r4 = r5.mUncollapsibleHeight
            int r0 = r0 + r4
            float r0 = (float) r0
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r6 >= 0) goto L4d
            boolean r6 = r5.isDismissable()
            if (r6 == 0) goto L4d
            int r6 = r5.mCollapsibleHeight
            int r0 = r5.mUncollapsibleHeight
            int r6 = r6 + r0
            r5.smoothScrollTo(r6, r3)
            r5.mDismissOnScrollerFinished = r2
            return r2
        L36:
            float r6 = r5.mCollapseOffset
            int r0 = r5.mCollapsibleHeight
            float r4 = (float) r0
            int r6 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r6 >= 0) goto L4d
            r5.smoothScrollTo(r0, r3)
            return r2
        L43:
            float r6 = r5.mCollapseOffset
            int r6 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r6 == 0) goto L4d
            r5.smoothScrollTo(r1, r3)
            return r2
        L4d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.widget.ResolverDrawerLayout.performAccessibilityActionCommon(int):boolean");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPrePerformAccessibilityAction(View view, int i, Bundle bundle) {
        if (super.onNestedPrePerformAccessibilityAction(view, i, bundle)) {
            return true;
        }
        return performAccessibilityActionCommon(i);
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return ScrollView.class.getName();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilityNodeInfo);
        if (isEnabled()) {
            if (this.mCollapseOffset != 0.0f) {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_EXPAND);
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN);
                accessibilityNodeInfo.setScrollable(true);
            }
            float f = this.mCollapseOffset;
            int i = this.mCollapsibleHeight;
            if (f < this.mUncollapsibleHeight + i && (f < i || isDismissable())) {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP);
                accessibilityNodeInfo.setScrollable(true);
            }
            if (this.mCollapseOffset < this.mCollapsibleHeight) {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_COLLAPSE);
            }
            if (this.mCollapseOffset < this.mCollapsibleHeight + this.mUncollapsibleHeight && isDismissable()) {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_DISMISS);
            }
        }
        accessibilityNodeInfo.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_ACCESSIBILITY_FOCUS);
    }

    @Override // android.view.View
    public boolean performAccessibilityActionInternal(int i, Bundle bundle) {
        if (i == AccessibilityNodeInfo.AccessibilityAction.ACTION_ACCESSIBILITY_FOCUS.getId()) {
            return false;
        }
        if (super.performAccessibilityActionInternal(i, bundle)) {
            return true;
        }
        return performAccessibilityActionCommon(i);
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        Drawable drawable = this.mScrollIndicatorDrawable;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        super.onDrawForeground(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int i7 = this.mMaxWidth;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i7 >= 0 ? Math.min(size, i7) : size, 1073741824);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(size2, 1073741824);
        int childCount = getChildCount();
        int i8 = 0;
        int measuredHeight = 0;
        while (true) {
            i3 = -1;
            i4 = 8;
            if (i8 >= childCount) {
                break;
            }
            View childAt = getChildAt(i8);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.alwaysShow && childAt.getVisibility() != 8) {
                if (layoutParams.maxHeight != -1) {
                    int i9 = size2 - measuredHeight;
                    measureChildWithMargins(childAt, iMakeMeasureSpec, 0, View.MeasureSpec.makeMeasureSpec(layoutParams.maxHeight, Integer.MIN_VALUE), layoutParams.maxHeight > i9 ? layoutParams.maxHeight - i9 : 0);
                } else {
                    measureChildWithMargins(childAt, iMakeMeasureSpec, 0, iMakeMeasureSpec2, measuredHeight);
                }
                measuredHeight += childAt.getMeasuredHeight();
            }
            i8++;
        }
        this.mAlwaysShowHeight = measuredHeight;
        int i10 = 0;
        while (i10 < childCount) {
            View childAt2 = getChildAt(i10);
            LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
            if (layoutParams2.alwaysShow || childAt2.getVisibility() == i4) {
                i5 = i4;
                i6 = i3;
            } else {
                if (layoutParams2.maxHeight != i3) {
                    int i11 = size2 - measuredHeight;
                    i5 = i4;
                    i6 = i3;
                    measureChildWithMargins(childAt2, iMakeMeasureSpec, 0, View.MeasureSpec.makeMeasureSpec(layoutParams2.maxHeight, Integer.MIN_VALUE), layoutParams2.maxHeight > i11 ? layoutParams2.maxHeight - i11 : 0);
                } else {
                    i5 = i4;
                    i6 = i3;
                    measureChildWithMargins(childAt2, iMakeMeasureSpec, 0, iMakeMeasureSpec2, measuredHeight);
                }
                measuredHeight += childAt2.getMeasuredHeight();
            }
            i10++;
            i3 = i6;
            i4 = i5;
        }
        int i12 = this.mCollapsibleHeight;
        int iMax = Math.max(0, (measuredHeight - this.mAlwaysShowHeight) - getMaxCollapsedHeight());
        this.mCollapsibleHeight = iMax;
        this.mUncollapsibleHeight = measuredHeight - iMax;
        updateCollapseOffset(i12, !isDragging());
        if (getShowAtTop()) {
            this.mTopOffset = 0;
        } else {
            this.mTopOffset = Math.max(0, size2 - measuredHeight) + ((int) this.mCollapseOffset);
        }
        setMeasuredDimension(size, size2);
    }

    public int getAlwaysShowHeight() {
        return this.mAlwaysShowHeight;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width = getWidth();
        int i5 = this.mTopOffset;
        int paddingLeft = getPaddingLeft();
        int paddingRight = width - getPaddingRight();
        int childCount = getChildCount();
        View view = null;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.hasNestedScrollIndicator) {
                view = childAt;
            }
            if (childAt.getVisibility() != 8) {
                int i7 = i5 + layoutParams.topMargin;
                if (layoutParams.ignoreOffset) {
                    i7 = (int) (i7 - this.mCollapseOffset);
                }
                int measuredHeight = childAt.getMeasuredHeight() + i7;
                int measuredWidth = childAt.getMeasuredWidth();
                int i8 = (((paddingRight - paddingLeft) - measuredWidth) / 2) + paddingLeft;
                childAt.layout(i8, i7, measuredWidth + i8, measuredHeight);
                i5 = measuredHeight + layoutParams.bottomMargin;
            }
        }
        if (this.mScrollIndicatorDrawable != null) {
            if (view != null) {
                int left = view.getLeft();
                int right = view.getRight();
                int top = view.getTop();
                this.mScrollIndicatorDrawable.setBounds(left, top - this.mScrollIndicatorDrawable.getIntrinsicHeight(), right, top);
                setWillNotDraw(true ^ isCollapsed());
                return;
            }
            this.mScrollIndicatorDrawable = null;
            setWillNotDraw(true);
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.open = this.mCollapsibleHeight > 0 && this.mCollapseOffset == 0.0f;
        savedState.mCollapsibleHeightReserved = this.mCollapsibleHeightReserved;
        return savedState;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mOpenOnLayout = savedState.open;
        this.mCollapsibleHeightReserved = savedState.mCollapsibleHeightReserved;
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public boolean alwaysShow;
        public boolean hasNestedScrollIndicator;
        public boolean ignoreOffset;
        public int maxHeight;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ResolverDrawerLayout_LayoutParams);
            this.alwaysShow = typedArrayObtainStyledAttributes.getBoolean(1, false);
            this.ignoreOffset = typedArrayObtainStyledAttributes.getBoolean(3, false);
            this.hasNestedScrollIndicator = typedArrayObtainStyledAttributes.getBoolean(2, false);
            this.maxHeight = typedArrayObtainStyledAttributes.getDimensionPixelSize(4, -1);
            typedArrayObtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.alwaysShow = layoutParams.alwaysShow;
            this.ignoreOffset = layoutParams.ignoreOffset;
            this.hasNestedScrollIndicator = layoutParams.hasNestedScrollIndicator;
            this.maxHeight = layoutParams.maxHeight;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.android.internal.widget.ResolverDrawerLayout.SavedState.1
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
        private int mCollapsibleHeightReserved;
        boolean open;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.open = parcel.readInt() != 0;
            this.mCollapsibleHeightReserved = parcel.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.open ? 1 : 0);
            parcel.writeInt(this.mCollapsibleHeightReserved);
        }
    }

    private class RunOnDismissedListener implements Runnable {
        private RunOnDismissedListener() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ResolverDrawerLayout.this.dispatchOnDismissed();
        }
    }

    private MetricsLogger getMetricsLogger() {
        if (this.mMetricsLogger == null) {
            this.mMetricsLogger = new MetricsLogger();
        }
        return this.mMetricsLogger;
    }
}
