package com.android.launcher3.home.bezier;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.android.launcher3.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class SmartSwipeWrapper extends ViewGroup {
    private static final int NESTED_TYPE_INVALID = -1;
    protected final List<SwipeConsumer> mConsumers;
    protected View mContentView;
    protected int mCurNestedType;
    protected SwipeHelper mHelper;
    protected final List<SwipeHelper> mHelpers;
    protected boolean mInflateFromXml;
    private final ArrayList<View> mMatchParentChildren;
    protected boolean mNestedFlyConsumed;

    private void init() {
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public SmartSwipeWrapper(Context context) {
        this(context, null, 0);
    }

    public SmartSwipeWrapper(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmartSwipeWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mHelpers = new LinkedList();
        this.mConsumers = new LinkedList();
        this.mMatchParentChildren = new ArrayList<>(1);
        this.mCurNestedType = -1;
        init();
    }

    public SmartSwipeWrapper(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mHelpers = new LinkedList();
        this.mConsumers = new LinkedList();
        this.mMatchParentChildren = new ArrayList<>(1);
        this.mCurNestedType = -1;
        init();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == 0) {
            this.mHelper = null;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        SwipeHelper swipeHelper = this.mHelper;
        if (swipeHelper != null) {
            return swipeHelper.shouldInterceptTouchEvent(ev);
        }
        for (SwipeHelper swipeHelper2 : this.mHelpers) {
            if (swipeHelper2.shouldInterceptTouchEvent(ev)) {
                this.mHelper = swipeHelper2;
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        SwipeHelper swipeHelper = this.mHelper;
        if (swipeHelper != null) {
            swipeHelper.processTouchEvent(event);
        } else {
            Iterator<SwipeHelper> it = this.mHelpers.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                SwipeHelper next = it.next();
                next.processTouchEvent(event);
                if (next.getDragState() == 1) {
                    this.mHelper = next;
                    break;
                }
            }
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        for (SwipeConsumer swipeConsumer : this.mConsumers) {
            if (swipeConsumer != null) {
                swipeConsumer.dispatchDraw(canvas);
            }
        }
    }

    public void drawChild(Canvas canvas, View child) {
        drawChild(canvas, child, getDrawingTime());
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (SwipeConsumer swipeConsumer : this.mConsumers) {
            if (swipeConsumer != null) {
                swipeConsumer.onDraw(canvas);
            }
        }
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        int childMeasureSpec;
        int childMeasureSpec2;
        int childCount = getChildCount();
        boolean z = (View.MeasureSpec.getMode(widthMeasureSpec) == 1073741824 && View.MeasureSpec.getMode(heightMeasureSpec) == 1073741824) ? false : true;
        this.mMatchParentChildren.clear();
        int iMax = 0;
        int iMax2 = 0;
        int iCombineMeasuredStates = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            childAt.measure(getChildMeasureSpec(widthMeasureSpec, 0, layoutParams.width), getChildMeasureSpec(heightMeasureSpec, 0, layoutParams.height));
            iMax2 = Math.max(iMax2, childAt.getMeasuredWidth());
            iMax = Math.max(iMax, childAt.getMeasuredHeight());
            iCombineMeasuredStates = combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            if (z && (layoutParams.width == -1 || layoutParams.height == -1)) {
                this.mMatchParentChildren.add(childAt);
            }
        }
        setMeasuredDimension(resolveSizeAndState(Math.max(iMax2, getSuggestedMinimumWidth()), widthMeasureSpec, iCombineMeasuredStates), resolveSizeAndState(Math.max(iMax, getSuggestedMinimumHeight()), heightMeasureSpec, iCombineMeasuredStates << 16));
        int size = this.mMatchParentChildren.size();
        if (size > 1) {
            for (int i3 = 0; i3 < size; i3++) {
                View view = this.mMatchParentChildren.get(i3);
                ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
                if (layoutParams2.width == -1) {
                    i = 1073741824;
                    childMeasureSpec = View.MeasureSpec.makeMeasureSpec(Math.max(0, getMeasuredWidth()), 1073741824);
                } else {
                    i = 1073741824;
                    childMeasureSpec = getChildMeasureSpec(widthMeasureSpec, 0, layoutParams2.width);
                }
                if (layoutParams2.height == -1) {
                    childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Math.max(0, getMeasuredHeight()), i);
                } else {
                    childMeasureSpec2 = getChildMeasureSpec(heightMeasureSpec, 0, layoutParams2.height);
                }
                view.measure(childMeasureSpec, childMeasureSpec2);
            }
        }
        for (SwipeConsumer swipeConsumer : this.mConsumers) {
            if (swipeConsumer != null) {
                swipeConsumer.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        boolean zOnLayout;
        View view;
        SwipeHelper swipeHelper = this.mHelper;
        if (swipeHelper != null) {
            zOnLayout = swipeHelper.getSwipeConsumer().onLayout(changed, left, top, right, bottom);
        } else {
            boolean z = false;
            for (SwipeConsumer swipeConsumer : this.mConsumers) {
                if (swipeConsumer != null && swipeConsumer.onLayout(changed, left, top, right, bottom)) {
                    z = true;
                }
            }
            zOnLayout = z;
        }
        if (zOnLayout || (view = this.mContentView) == null) {
            return;
        }
        view.layout(0, 0, view.getMeasuredWidth(), this.mContentView.getMeasuredHeight());
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mInflateFromXml = true;
        int childCount = getChildCount();
        if (childCount <= 0 || this.mContentView != null) {
            return;
        }
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if ((layoutParams instanceof LayoutParams) && ((LayoutParams) layoutParams).gravity == 0) {
                setContentView(childAt);
                return;
            }
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.mHelpers.isEmpty()) {
            return;
        }
        boolean z = false;
        Iterator<SwipeHelper> it = this.mHelpers.iterator();
        while (it.hasNext()) {
            if (it.next().continueSettling()) {
                z = true;
            }
        }
        if (z) {
            androidx.core.view.ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override // android.view.View
    public boolean canScrollVertically(int direction) {
        for (SwipeConsumer swipeConsumer : this.mConsumers) {
            if (direction < 0 && swipeConsumer.isTopEnable() && !swipeConsumer.isTopLocked()) {
                return swipeConsumer.getDirection() != 4 || swipeConsumer.getProgress() < 1.0f;
            }
            if (direction > 0 && swipeConsumer.isBottomEnable() && !swipeConsumer.isBottomLocked()) {
                return swipeConsumer.getDirection() != 8 || swipeConsumer.getProgress() < 1.0f;
            }
        }
        return super.canScrollVertically(direction);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int direction) {
        for (SwipeConsumer swipeConsumer : this.mConsumers) {
            if (direction < 0 && swipeConsumer.isLeftEnable() && !swipeConsumer.isLeftLocked()) {
                return swipeConsumer.getDirection() != 1 || swipeConsumer.getProgress() < 1.0f;
            }
            if (direction > 0 && swipeConsumer.isRightEnable() && !swipeConsumer.isRightLocked()) {
                return swipeConsumer.getDirection() != 2 || swipeConsumer.getProgress() < 1.0f;
            }
        }
        return super.canScrollHorizontally(direction);
    }

    public <T extends SwipeConsumer> T addConsumer(T consumer) {
        if (consumer != null) {
            this.mConsumers.add(consumer);
            SwipeHelper swipeHelper = consumer.getSwipeHelper();
            if (swipeHelper == null) {
                swipeHelper = SwipeHelper.create(this, consumer.getSensitivity(), consumer, consumer.getInterpolator());
            }
            consumer.onAttachToWrapper(this, swipeHelper);
            this.mHelpers.add(swipeHelper);
        }
        return consumer;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Iterator<SwipeConsumer> it = this.mConsumers.iterator();
        while (it.hasNext()) {
            it.next().close();
        }
    }

    public SmartSwipeWrapper removeAllConsumers() {
        Iterator<SwipeConsumer> it = this.mConsumers.iterator();
        while (it.hasNext()) {
            SwipeConsumer next = it.next();
            it.remove();
            if (next != null) {
                next.onDetachFromWrapper();
                SwipeHelper swipeHelper = next.getSwipeHelper();
                this.mHelpers.remove(swipeHelper);
                if (this.mHelper == swipeHelper) {
                    this.mHelper = null;
                }
            }
        }
        return this;
    }

    public SmartSwipeWrapper removeConsumer(SwipeConsumer consumer) {
        if (this.mConsumers.remove(consumer)) {
            consumer.onDetachFromWrapper();
            SwipeHelper swipeHelper = consumer.getSwipeHelper();
            this.mHelpers.remove(swipeHelper);
            if (this.mHelper == swipeHelper) {
                this.mHelper = null;
            }
        }
        return this;
    }

    public SwipeConsumer getConsumerByType(Class<? extends SwipeConsumer> clazz) {
        for (SwipeConsumer swipeConsumer : this.mConsumers) {
            if (swipeConsumer != null && swipeConsumer.getClass() == clazz) {
                return swipeConsumer;
            }
        }
        return null;
    }

    public void setContentView(View contentView) {
        if (contentView == null || this.mContentView == contentView) {
            return;
        }
        this.mContentView = contentView;
        if (contentView.getParent() == null) {
            addView(contentView);
        }
    }

    public View getContentView() {
        return this.mContentView;
    }

    public List<SwipeConsumer> getAllConsumers() {
        return this.mConsumers;
    }

    public SmartSwipeWrapper enableDirection(int direction) {
        return enableDirection(direction, true);
    }

    public SmartSwipeWrapper enableDirection(int direction, boolean enable) {
        Iterator<SwipeConsumer> it = this.mConsumers.iterator();
        while (it.hasNext()) {
            it.next().enableDirection(direction, enable);
        }
        return this;
    }

    public boolean isInflateFromXml() {
        return this.mInflateFromXml;
    }

    public void consumeInflateFromXml() {
        this.mInflateFromXml = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams lp) {
        if (lp instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) lp);
        }
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) lp);
        }
        return new LayoutParams(lp);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public static final int UNSPECIFIED_GRAVITY = 0;
        public int gravity;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            this.gravity = 0;
            TypedArray typedArrayObtainStyledAttributes = c.obtainStyledAttributes(attrs, R.styleable.SmartSwipeWrapper_Layout);
            this.gravity = typedArrayObtainStyledAttributes.getInt(0, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.gravity = 0;
        }

        public LayoutParams(int width, int height, int gravity) {
            super(width, height);
            this.gravity = 0;
            this.gravity = gravity;
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            this.gravity = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams source) {
            super(source);
            this.gravity = 0;
        }

        public LayoutParams(LayoutParams source) {
            super((ViewGroup.MarginLayoutParams) source);
            this.gravity = 0;
            this.gravity = source.gravity;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return onStartNestedScroll(child, target, nestedScrollAxes, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View child, View target, int axes) {
        onNestedScrollAccepted(child, target, axes, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View child) {
        onStopNestedScroll(child, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        onNestedPreScroll(target, dx, dy, consumed, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, 0);
    }

    public boolean onStartNestedScroll(View child, View target, int axes, int type) {
        if ((axes & 2) != 0) {
            for (SwipeConsumer swipeConsumer : this.mConsumers) {
                if (swipeConsumer.isTopEnable() || swipeConsumer.isBottomEnable()) {
                    return true;
                }
            }
            return false;
        }
        if ((axes & 1) == 0) {
            return false;
        }
        for (SwipeConsumer swipeConsumer2 : this.mConsumers) {
            if (swipeConsumer2.isLeftEnable() || swipeConsumer2.isRightEnable()) {
                return true;
            }
        }
        return false;
    }

    public void onNestedScrollAccepted(View child, View target, int axes, int type) {
        this.mNestedFlyConsumed = false;
        this.mCurNestedType = type;
        helperOnNestedScrollAccepted(child, target, axes, type);
    }

    public void onStopNestedScroll(View target, int type) {
        helperOnStopNestedScroll(target, type);
        if (type == this.mCurNestedType) {
            this.mCurNestedType = -1;
            SwipeHelper swipeHelper = this.mHelper;
            if (swipeHelper != null) {
                swipeHelper.nestedScrollingRelease();
            }
        }
    }

    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        if (dxUnconsumed != 0 || dyUnconsumed != 0) {
            if (type == 1) {
                requestDisallowInterceptTouchEvent(false);
            }
            int[] iArr = new int[2];
            wrapperNestedScroll(dxUnconsumed, dyUnconsumed, iArr, type);
            dxConsumed += iArr[0];
            dyConsumed += iArr[1];
            dxUnconsumed -= iArr[0];
            dyUnconsumed -= iArr[1];
        }
        helperOnNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
    }

    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed, int type) {
        SwipeHelper swipeHelper = this.mHelper;
        if (swipeHelper != null && swipeHelper.getSwipeConsumer().getDirection() != 0) {
            wrapperNestedScroll(dx, dy, consumed, type);
        }
        helperOnNestedPreScroll(target, dx, dy, consumed, type);
    }

    private void wrapperNestedScroll(int dxUnconsumed, int dyUnconsumed, int[] consumed, int type) {
        if (this.mCurNestedType == -1) {
            this.mCurNestedType = type;
            this.mNestedFlyConsumed = false;
        }
        boolean z = type == 1;
        SwipeHelper swipeHelper = this.mHelper;
        if (swipeHelper == null) {
            for (SwipeHelper swipeHelper2 : this.mHelpers) {
                if (swipeHelper2 != null) {
                    if (swipeHelper2.nestedScrollingDrag(-dxUnconsumed, -dyUnconsumed, consumed, type == 1)) {
                        this.mHelper = swipeHelper2;
                        return;
                    }
                }
            }
            return;
        }
        if (z) {
            if (this.mNestedFlyConsumed) {
                return;
            }
            if (swipeHelper.getSwipeConsumer().getProgress() >= 1.0f) {
                this.mNestedFlyConsumed = true;
                this.mHelper.nestedScrollingRelease();
                return;
            } else {
                this.mHelper.nestedScrollingDrag(-dxUnconsumed, -dyUnconsumed, consumed, z);
                return;
            }
        }
        swipeHelper.nestedScrollingDrag(-dxUnconsumed, -dyUnconsumed, consumed, z);
    }

    protected void helperOnNestedScrollAccepted(View child, View target, int axes, int type) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.onNestedScrollAccepted(child, target, axes);
        }
    }

    protected void helperOnNestedPreScroll(View target, int dx, int dy, int[] consumed, int type) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.onNestedPreScroll(target, dx, dy, consumed);
        }
    }

    protected void helperOnNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        }
    }

    protected void helperOnStopNestedScroll(View target, int type) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.onStopNestedScroll(target);
        }
    }
}
