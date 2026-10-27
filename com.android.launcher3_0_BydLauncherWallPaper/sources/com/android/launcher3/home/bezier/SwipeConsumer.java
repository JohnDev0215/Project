package com.android.launcher3.home.bezier;

import android.graphics.Canvas;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.AbsSeekBar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes4.dex */
public abstract class SwipeConsumer {
    public static int DEFAULT_OPEN_DISTANCE_IN_DP = 150;
    public static final int DIRECTION_ALL = 15;
    public static final int DIRECTION_BOTTOM = 8;
    public static final int DIRECTION_HORIZONTAL = 3;
    public static final int DIRECTION_LEFT = 1;
    public static final int DIRECTION_NONE = 0;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_TOP = 4;
    public static final int DIRECTION_VERTICAL = 12;
    public static final float PROGRESS_CLOSE = 0.0f;
    public static final float PROGRESS_OPEN = 1.0f;
    public static final int RELEASE_MODE_AUTO_CLOSE = 1;
    public static final int RELEASE_MODE_AUTO_OPEN = 2;
    public static final int RELEASE_MODE_AUTO_OPEN_CLOSE = 3;
    public static final int RELEASE_MODE_HOLE_OPEN = 4;
    public static final int RELEASE_MODE_NONE = 0;
    protected int mCachedSwipeDistanceX;
    protected int mCachedSwipeDistanceY;
    protected int mCurDisplayDistanceX;
    protected int mCurDisplayDistanceY;
    protected int mCurSwipeDistanceX;
    protected int mCurSwipeDistanceY;
    protected int mDirection;
    protected boolean mDisableNestedFly;
    protected boolean mDisableNestedScroll;
    protected boolean mDisableSwipeOnSettling;
    protected int mEdgeSize;
    protected int mHeight;
    protected Interpolator mInterpolator;
    protected Integer mMaxSettleDuration;
    protected int mOpenDistance;
    protected boolean mOpenDistanceSpecified;
    protected float mProgress;
    protected SwipeDistanceCalculator mSwipeDistanceCalculator;
    protected SwipeHelper mSwipeHelper;
    protected int mSwipeMaxDistance;
    protected int mSwipeOpenDistance;
    protected volatile boolean mSwiping;
    protected Object mTag;
    protected int mWidth;
    protected SmartSwipeWrapper mWrapper;
    private int mEnableDirection = 0;
    private int mLockDirection = 0;
    private float mSensitivity = 1.0f;
    protected int mReleaseMode = 1;
    protected final List<SwipeListener> mListeners = new CopyOnWriteArrayList();
    protected float mOverSwipeFactor = 0.0f;

    /* JADX WARN: Multi-variable type inference failed */
    public <T extends SwipeConsumer> T as(Class<T> clazz) {
        return this;
    }

    public void dispatchDraw(Canvas canvas) {
    }

    protected void initChildrenFormXml() {
    }

    protected abstract void onDisplayDistanceChanged(int distanceXToDisplay, int distanceYToDisplay, int dx, int dy);

    public void onDraw(Canvas canvas) {
    }

    public boolean onLayout(boolean changed, int left, int top, int right, int bottom) {
        return false;
    }

    public boolean tryAcceptSettling(int pointerId, float downX, float downY) {
        if (isNestedAndDisabled(pointerId)) {
            return false;
        }
        return ((this.mDisableSwipeOnSettling && getDragState() == 2) || !isDirectionEnable(this.mDirection) || isDirectionLocked(this.mDirection)) ? false : true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean tryAcceptMoving(int r14, float r15, float r16, float r17, float r18) {
        /*
            Method dump skipped, instruction units count: 310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.home.bezier.SwipeConsumer.tryAcceptMoving(int, float, float, float, float):boolean");
    }

    protected boolean isNestedAndDisabled(int pointerId) {
        return (this.mDisableNestedScroll && pointerId == -2) || (this.mDisableNestedFly && pointerId == -3);
    }

    private boolean canChildScroll(ViewGroup parentView, int direction, int downX, int downY, float dx, float dy) {
        View viewFindTopChildUnder = findTopChildUnder(parentView, downX, downY);
        boolean zCanScrollHorizontally = false;
        if (viewFindTopChildUnder != null) {
            if (direction == 1 || direction == 2) {
                if (viewFindTopChildUnder instanceof AbsSeekBar) {
                    AbsSeekBar absSeekBar = (AbsSeekBar) viewFindTopChildUnder;
                    int progress = absSeekBar.getProgress();
                    int min = Build.VERSION.SDK_INT >= 26 ? absSeekBar.getMin() : 0;
                    int max = absSeekBar.getMax();
                    if ((dx > 0.0f && progress < max) || (dx < 0.0f && progress > min)) {
                        zCanScrollHorizontally = true;
                    }
                } else {
                    zCanScrollHorizontally = viewFindTopChildUnder.canScrollHorizontally(dx <= 0.0f ? 1 : -1);
                }
            } else if (direction == 4 || direction == 8) {
                int i = dy <= 0.0f ? 1 : -1;
                if (viewFindTopChildUnder instanceof AbsListView) {
                    zCanScrollHorizontally = ViewCompat.canListViewScrollVertical((AbsListView) viewFindTopChildUnder, i);
                } else {
                    zCanScrollHorizontally = viewFindTopChildUnder.canScrollVertically(i);
                }
            }
            if (!zCanScrollHorizontally && (viewFindTopChildUnder instanceof ViewGroup)) {
                return canChildScroll((ViewGroup) viewFindTopChildUnder, direction, downX - viewFindTopChildUnder.getLeft(), downY - viewFindTopChildUnder.getTop(), dx, dy);
            }
        }
        return zCanScrollHorizontally;
    }

    public void onSwipeAccepted(int activePointerId, boolean settling, float initialMotionX, float initialMotionY) {
        this.mSwiping = true;
        ViewParent parent = this.mWrapper.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
        int i = this.mCurSwipeDistanceX;
        if (i != 0 || this.mCurSwipeDistanceY != 0) {
            this.mCachedSwipeDistanceX = i;
            this.mCachedSwipeDistanceY = this.mCurSwipeDistanceY;
        }
        int swipeOpenDistance = getSwipeOpenDistance();
        this.mSwipeOpenDistance = swipeOpenDistance;
        float f = this.mOverSwipeFactor;
        if (f > 0.0f) {
            this.mSwipeMaxDistance = (int) (swipeOpenDistance * (f + 1.0f));
        } else {
            this.mSwipeMaxDistance = swipeOpenDistance;
        }
        notifySwipeStart();
    }

    public void onSwipeReleased(float xVelocity, float yVelocity) {
        ViewParent parent = this.mWrapper.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        notifySwipeRelease(xVelocity, yVelocity);
        float f = this.mProgress;
        if (f >= 1.0f && (this.mReleaseMode & 4) == 4) {
            smoothSlideTo(1.0f);
            return;
        }
        int i = this.mReleaseMode & 3;
        if (i == 1) {
            if (f >= 1.0f) {
                onOpened();
            }
            smoothSlideTo(0.0f);
        } else if (i == 2) {
            smoothSlideTo(1.0f);
        } else {
            if (i != 3) {
                return;
            }
            smoothOpenOrClose(xVelocity, yVelocity);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void smoothOpenOrClose(float r7, float r8) {
        /*
            r6 = this;
            int r0 = r6.mDirection
            r1 = 1056964608(0x3f000000, float:0.5)
            r2 = 1
            r3 = 0
            r4 = 0
            if (r0 == r2) goto L3f
            r5 = 2
            if (r0 == r5) goto L30
            r7 = 4
            if (r0 == r7) goto L23
            r7 = 8
            if (r0 == r7) goto L14
            goto L4e
        L14:
            int r7 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r7 < 0) goto L4d
            int r7 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r7 != 0) goto L4c
            float r7 = r6.mProgress
            int r7 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r7 <= 0) goto L4c
            goto L4d
        L23:
            int r7 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r7 > 0) goto L4d
            if (r7 != 0) goto L4c
            float r7 = r6.mProgress
            int r7 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r7 <= 0) goto L4c
            goto L4d
        L30:
            int r8 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r8 < 0) goto L4d
            int r7 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r7 != 0) goto L4c
            float r7 = r6.mProgress
            int r7 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r7 <= 0) goto L4c
            goto L4d
        L3f:
            int r7 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r7 > 0) goto L4d
            if (r7 != 0) goto L4c
            float r7 = r6.mProgress
            int r7 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r7 <= 0) goto L4c
            goto L4d
        L4c:
            r2 = r3
        L4d:
            r3 = r2
        L4e:
            if (r3 == 0) goto L52
            r4 = 1065353216(0x3f800000, float:1.0)
        L52:
            r6.smoothSlideTo(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.home.bezier.SwipeConsumer.smoothOpenOrClose(float, float):void");
    }

    public void setCurrentStateAsClosed() {
        onClosed();
        reset();
    }

    protected void notifySwipeOpened() {
        for (SwipeListener swipeListener : this.mListeners) {
            if (swipeListener != null) {
                swipeListener.onSwipeOpened(this.mWrapper, this, this.mDirection);
            }
        }
    }

    protected void notifySwipeClosed() {
        for (SwipeListener swipeListener : this.mListeners) {
            if (swipeListener != null) {
                swipeListener.onSwipeClosed(this.mWrapper, this, this.mDirection);
            }
        }
    }

    protected void notifyAttachToWrapper() {
        for (SwipeListener swipeListener : this.mListeners) {
            if (swipeListener != null) {
                swipeListener.onConsumerAttachedToWrapper(this.mWrapper, this);
            }
        }
    }

    protected void notifyDetachFromWrapper() {
        for (SwipeListener swipeListener : this.mListeners) {
            if (swipeListener != null) {
                swipeListener.onConsumerDetachedFromWrapper(this.mWrapper, this);
            }
        }
    }

    protected void notifySwipeStateChanged(int state) {
        for (SwipeListener swipeListener : this.mListeners) {
            if (swipeListener != null) {
                swipeListener.onSwipeStateChanged(this.mWrapper, this, state, this.mDirection, this.mProgress);
            }
        }
    }

    protected void notifySwipeStart() {
        for (SwipeListener swipeListener : this.mListeners) {
            if (swipeListener != null) {
                swipeListener.onSwipeStart(this.mWrapper, this, this.mDirection);
            }
        }
    }

    protected void notifySwipeRelease(float xVelocity, float yVelocity) {
        for (SwipeListener swipeListener : this.mListeners) {
            if (swipeListener != null) {
                swipeListener.onSwipeRelease(this.mWrapper, this, this.mDirection, this.mProgress, xVelocity, yVelocity);
            }
        }
    }

    protected void notifySwipeProgress(boolean settling) {
        for (SwipeListener swipeListener : this.mListeners) {
            if (swipeListener != null) {
                swipeListener.onSwipeProcess(this.mWrapper, this, this.mDirection, settling, this.mProgress);
            }
        }
    }

    public int getHorizontalRange(float dx, float dy) {
        if (this.mCurSwipeDistanceX != 0 || ((dx > 0.0f && isLeftEnable() && !isLeftLocked()) || (dx < 0.0f && isRightEnable() && !isRightLocked()))) {
            return getSwipeOpenDistance();
        }
        return 0;
    }

    public int getVerticalRange(float dx, float dy) {
        if (this.mCurSwipeDistanceY != 0 || ((dy > 0.0f && isTopEnable() && !isTopLocked()) || (dy < 0.0f && isBottomEnable() && !isBottomLocked()))) {
            return getSwipeOpenDistance();
        }
        return 0;
    }

    public int clampDistanceHorizontal(int distanceX, int dx) {
        int i = this.mCachedSwipeDistanceX;
        if (i != 0) {
            distanceX += i;
            this.mCachedSwipeDistanceX = 0;
        }
        if ((this.mDirection & 1) > 0 && isLeftEnable()) {
            return SmartSwipe.ensureBetween(distanceX, 0, this.mSwipeMaxDistance);
        }
        if ((this.mDirection & 2) <= 0 || !isRightEnable()) {
            return 0;
        }
        return SmartSwipe.ensureBetween(distanceX, -this.mSwipeMaxDistance, 0);
    }

    public int clampDistanceVertical(int distanceY, int dy) {
        int i = this.mCachedSwipeDistanceY;
        if (i != 0) {
            distanceY += i;
            this.mCachedSwipeDistanceY = 0;
        }
        if ((this.mDirection & 4) > 0 && isTopEnable()) {
            return SmartSwipe.ensureBetween(distanceY, 0, this.mSwipeMaxDistance);
        }
        if ((this.mDirection & 8) <= 0 || !isBottomEnable()) {
            return 0;
        }
        return SmartSwipe.ensureBetween(distanceY, -this.mSwipeMaxDistance, 0);
    }

    public void onSwipeDistanceChanged(int clampedDistanceX, int clampedDistanceY, int dx, int dy) {
        if (getOpenDistance() <= 0) {
            return;
        }
        float f = this.mProgress;
        if (clampedDistanceX != this.mCurSwipeDistanceX || clampedDistanceY != this.mCurSwipeDistanceY) {
            this.mCurSwipeDistanceX = clampedDistanceX;
            this.mCurSwipeDistanceY = clampedDistanceY;
            int i = this.mSwipeOpenDistance;
            if (i <= 0) {
                this.mProgress = 0.0f;
            } else {
                int i2 = this.mDirection;
                if (i2 == 1 || i2 == 2) {
                    this.mProgress = Math.abs(clampedDistanceX / i);
                } else if (i2 == 4 || i2 == 8) {
                    this.mProgress = Math.abs(clampedDistanceY / i);
                }
            }
            int i3 = this.mDirection;
            if ((i3 & 3) > 0) {
                SwipeDistanceCalculator swipeDistanceCalculator = this.mSwipeDistanceCalculator;
                if (swipeDistanceCalculator != null) {
                    clampedDistanceX = swipeDistanceCalculator.calculateSwipeDistance(clampedDistanceX, this.mProgress);
                }
                dx = clampedDistanceX - this.mCurDisplayDistanceX;
                this.mCurDisplayDistanceX = clampedDistanceX;
                dy = 0;
            } else if ((i3 & 12) > 0) {
                SwipeDistanceCalculator swipeDistanceCalculator2 = this.mSwipeDistanceCalculator;
                if (swipeDistanceCalculator2 != null) {
                    clampedDistanceY = swipeDistanceCalculator2.calculateSwipeDistance(clampedDistanceY, this.mProgress);
                }
                dy = clampedDistanceY - this.mCurDisplayDistanceY;
                this.mCurDisplayDistanceY = clampedDistanceY;
                dx = 0;
            }
            onDisplayDistanceChanged(this.mCurDisplayDistanceX, this.mCurDisplayDistanceY, dx, dy);
        }
        if (this.mProgress != f) {
            notifySwipeProgress(getDragState() == 2);
        }
    }

    public void onAttachToWrapper(SmartSwipeWrapper wrapper, SwipeHelper swipeHelper) {
        this.mWrapper = wrapper;
        if (this.mOpenDistance == 0) {
            this.mOpenDistance = SmartSwipe.dp2px(DEFAULT_OPEN_DISTANCE_IN_DP, wrapper.getContext());
        }
        this.mSwipeHelper = swipeHelper;
        Integer num = this.mMaxSettleDuration;
        if (num != null) {
            swipeHelper.setMaxSettleDuration(num.intValue());
        }
        if (this.mWrapper.isInflateFromXml()) {
            initChildrenFormXml();
        }
        notifyAttachToWrapper();
    }

    public void onDetachFromWrapper() {
        notifyDetachFromWrapper();
        reset();
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.mWidth = this.mWrapper.getMeasuredWidth();
        this.mHeight = this.mWrapper.getMeasuredHeight();
    }

    public boolean isViewUnder(View view, int x, int y) {
        return view != null && x >= view.getLeft() && x < view.getRight() && y >= view.getTop() && y < view.getBottom();
    }

    public View findTopChildUnder(ViewGroup parentView, int x, int y) {
        for (int childCount = parentView.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = parentView.getChildAt(childCount);
            if (x >= childAt.getLeft() && x < childAt.getRight() && y >= childAt.getTop() && y < childAt.getBottom() && childAt.getVisibility() == 0 && (!(childAt instanceof ScrimView) || childAt.isFocusable() || childAt.isClickable())) {
                return childAt;
            }
        }
        return null;
    }

    public void onStateChanged(int state) {
        notifySwipeStateChanged(state);
        if (state == 0) {
            this.mSwiping = false;
            float f = this.mProgress;
            if (f >= 1.0f) {
                onOpened();
            } else if (f <= 0.0f) {
                onClosed();
            }
        }
    }

    protected void onOpened() {
        notifySwipeOpened();
    }

    protected void onClosed() {
        notifySwipeClosed();
        this.mDirection = 0;
    }

    protected void reset() {
        this.mDirection = 0;
        this.mProgress = 0.0f;
        this.mCurDisplayDistanceX = 0;
        this.mCurSwipeDistanceX = 0;
        this.mCachedSwipeDistanceX = 0;
        this.mCurDisplayDistanceY = 0;
        this.mCurSwipeDistanceY = 0;
        this.mCachedSwipeDistanceY = 0;
    }

    public Interpolator getInterpolator() {
        return this.mInterpolator;
    }

    public SwipeConsumer setInterpolator(Interpolator interpolator) {
        SmartSwipeWrapper smartSwipeWrapper;
        this.mInterpolator = interpolator;
        SwipeHelper swipeHelper = this.mSwipeHelper;
        if (swipeHelper != null && (smartSwipeWrapper = this.mWrapper) != null) {
            swipeHelper.setInterpolator(smartSwipeWrapper.getContext(), interpolator);
        }
        return this;
    }

    public float getSensitivity() {
        return this.mSensitivity;
    }

    public SwipeConsumer setSensitivity(float sensitivity) {
        if (sensitivity > 0.0f) {
            this.mSensitivity = sensitivity;
            SwipeHelper swipeHelper = this.mSwipeHelper;
            if (swipeHelper != null) {
                swipeHelper.setSensitivity(sensitivity);
            }
        }
        return this;
    }

    public int getReleaseMode() {
        return this.mReleaseMode;
    }

    public SwipeConsumer setReleaseMode(int releaseMode) {
        this.mReleaseMode = releaseMode;
        return this;
    }

    public int getEdgeSize() {
        return this.mEdgeSize;
    }

    public SwipeConsumer setEdgeSize(int edgeSize) {
        this.mEdgeSize = edgeSize;
        return this;
    }

    public SmartSwipeWrapper getWrapper() {
        return this.mWrapper;
    }

    public SwipeHelper getSwipeHelper() {
        return this.mSwipeHelper;
    }

    public int getDragState() {
        return this.mSwipeHelper.getDragState();
    }

    public float getProgress() {
        return this.mProgress;
    }

    public boolean isSwiping() {
        return this.mSwiping;
    }

    public int getSwipeOpenDistance() {
        SwipeDistanceCalculator swipeDistanceCalculator = this.mSwipeDistanceCalculator;
        if (swipeDistanceCalculator != null) {
            return swipeDistanceCalculator.calculateSwipeOpenDistance(this.mOpenDistance);
        }
        return this.mOpenDistance;
    }

    public int getOpenDistance() {
        return this.mOpenDistance;
    }

    public SwipeConsumer setOpenDistance(int openDistance) {
        this.mOpenDistance = openDistance;
        this.mOpenDistanceSpecified = true;
        return this;
    }

    public boolean isDisableNestedScroll() {
        return this.mDisableNestedScroll;
    }

    public SwipeConsumer setDisableNestedScroll(boolean disable) {
        this.mDisableNestedScroll = disable;
        return this;
    }

    public boolean isDisableNestedFly() {
        return this.mDisableNestedFly;
    }

    public SwipeConsumer setDisableNestedFly(boolean disable) {
        this.mDisableNestedFly = disable;
        return this;
    }

    public SwipeConsumer removeAllListeners() {
        this.mListeners.clear();
        return this;
    }

    public SwipeConsumer removeListener(SwipeListener listener) {
        this.mListeners.remove(listener);
        return this;
    }

    public SwipeConsumer addListener(SwipeListener listener) {
        if (listener != null && !this.mListeners.contains(listener)) {
            this.mListeners.add(listener);
            SmartSwipeWrapper smartSwipeWrapper = this.mWrapper;
            if (smartSwipeWrapper != null) {
                listener.onConsumerAttachedToWrapper(smartSwipeWrapper, this);
            }
        }
        return this;
    }

    public SwipeConsumer setSwipeDistanceCalculator(SwipeDistanceCalculator calculator) {
        this.mSwipeDistanceCalculator = calculator;
        return this;
    }

    public SwipeDistanceCalculator getSwipeDistanceCalculator() {
        return this.mSwipeDistanceCalculator;
    }

    public boolean isDisableSwipeOnSetting() {
        return this.mDisableSwipeOnSettling;
    }

    public SwipeConsumer setDisableSwipeOnSettling(boolean disable) {
        this.mDisableSwipeOnSettling = disable;
        return this;
    }

    public float getOverSwipeFactor() {
        return this.mOverSwipeFactor;
    }

    public SwipeConsumer setOverSwipeFactor(float overSwipeFactor) {
        if (overSwipeFactor >= 0.0f) {
            this.mOverSwipeFactor = overSwipeFactor;
        }
        return this;
    }

    public Object getTag() {
        return this.mTag;
    }

    public SwipeConsumer setTag(Object tag) {
        this.mTag = tag;
        return this;
    }

    public Integer getMaxSettleDuration() {
        SwipeHelper swipeHelper = this.mSwipeHelper;
        if (swipeHelper != null) {
            return Integer.valueOf(swipeHelper.getMaxSettleDuration());
        }
        return this.mMaxSettleDuration;
    }

    public SwipeConsumer setMaxSettleDuration(int maxSettleDuration) {
        this.mMaxSettleDuration = Integer.valueOf(maxSettleDuration);
        SwipeHelper swipeHelper = this.mSwipeHelper;
        if (swipeHelper != null) {
            swipeHelper.setMaxSettleDuration(maxSettleDuration);
        }
        return this;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public SwipeConsumer setWidth(int width) {
        this.mWidth = width;
        return this;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public SwipeConsumer setHeight(int height) {
        this.mHeight = height;
        return this;
    }

    public SwipeConsumer setLeftOpen() {
        return open(false, 1);
    }

    public SwipeConsumer setRightOpen() {
        return open(false, 2);
    }

    public SwipeConsumer setTopOpen() {
        return open(false, 4);
    }

    public SwipeConsumer setBottomOpen() {
        return open(false, 8);
    }

    public SwipeConsumer smoothLeftOpen() {
        return open(true, 1);
    }

    public SwipeConsumer smoothRightOpen() {
        return open(true, 2);
    }

    public SwipeConsumer smoothTopOpen() {
        return open(true, 4);
    }

    public SwipeConsumer smoothBottomOpen() {
        return open(true, 8);
    }

    public SwipeConsumer open(final boolean smooth, final int direction) {
        int i = this.mDirection;
        if (i != 0) {
            if (i == direction && this.mProgress != 1.0f) {
            }
            return this;
        }
        if (!isDirectionEnable(direction)) {
            return this;
        }
        this.mDirection = direction;
        onSwipeAccepted(0, true, 0.0f, 0.0f);
        if (!isDirectionLocked(this.mDirection)) {
            final int i2 = this.mDirection;
            lockDirection(i2);
            addListener(new SimpleSwipeListener() { // from class: com.android.launcher3.home.bezier.SwipeConsumer.1
                @Override // com.android.launcher3.home.bezier.SimpleSwipeListener, com.android.launcher3.home.bezier.SwipeListener
                public void onSwipeOpened(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction2) {
                    SwipeConsumer.this.unlockDirection(i2);
                    SwipeConsumer.this.removeListener(this);
                }
            });
        }
        slideTo(smooth, 1.0f);
        return this;
    }

    public SwipeConsumer close() {
        return close(false);
    }

    public SwipeConsumer smoothClose() {
        return close(true);
    }

    public SwipeConsumer close(final boolean smooth) {
        if (this.mDirection != 0 && this.mProgress != 0.0f) {
            onSwipeAccepted(0, true, 0.0f, 0.0f);
            this.mCachedSwipeDistanceX = 0;
            this.mCachedSwipeDistanceY = 0;
            if (!isDirectionLocked(this.mDirection)) {
                lockDirection(this.mDirection);
                addListener(new SimpleSwipeListener() { // from class: com.android.launcher3.home.bezier.SwipeConsumer.2
                    @Override // com.android.launcher3.home.bezier.SimpleSwipeListener, com.android.launcher3.home.bezier.SwipeListener
                    public void onSwipeClosed(SmartSwipeWrapper wrapper, SwipeConsumer consumer, int direction) {
                        SwipeConsumer.this.unlockDirection(direction);
                        SwipeConsumer.this.removeListener(this);
                    }
                });
            }
            if (smooth) {
                smoothSlideTo(0, 0);
            } else {
                smoothSlideTo(0, 0, 0, 0);
            }
        }
        return this;
    }

    public SwipeConsumer smoothSlideTo(float progress) {
        slideTo(true, progress);
        return this;
    }

    public SwipeConsumer slideTo(boolean smooth, float progress) {
        int iEnsureBetween = (int) (this.mSwipeOpenDistance * SmartSwipe.ensureBetween(progress, 0.0f, 1.0f));
        int i = this.mDirection;
        int i2 = 0;
        if (i != 1) {
            if (i == 2) {
                iEnsureBetween = -iEnsureBetween;
            } else if (i == 4) {
                i2 = iEnsureBetween;
                iEnsureBetween = 0;
            } else if (i != 8) {
                iEnsureBetween = 0;
            } else {
                iEnsureBetween = -iEnsureBetween;
                i2 = iEnsureBetween;
                iEnsureBetween = 0;
            }
        }
        if (smooth) {
            smoothSlideTo(iEnsureBetween, i2);
        } else {
            smoothSlideTo(iEnsureBetween, i2, iEnsureBetween, i2);
        }
        return this;
    }

    public SwipeConsumer addToExclusiveGroup(SwipeConsumerExclusiveGroup group) {
        if (group != null) {
            group.add(this);
        }
        return this;
    }

    public int getDirection() {
        return this.mDirection;
    }

    public SwipeConsumer enableDirection(int direction, boolean enable) {
        if (enable) {
            return enableDirection(direction);
        }
        return disableDirection(direction);
    }

    public SwipeConsumer enableLeft() {
        return enableDirection(1);
    }

    public SwipeConsumer enableRight() {
        return enableDirection(2);
    }

    public SwipeConsumer enableTop() {
        return enableDirection(4);
    }

    public SwipeConsumer enableBottom() {
        return enableDirection(8);
    }

    public SwipeConsumer enableHorizontal() {
        return enableDirection(3);
    }

    public SwipeConsumer enableVertical() {
        return enableDirection(12);
    }

    public SwipeConsumer enableAllDirections() {
        return enableDirection(15);
    }

    public SwipeConsumer disableLeft() {
        return disableDirection(1);
    }

    public SwipeConsumer disableRight() {
        return disableDirection(2);
    }

    public SwipeConsumer disableTop() {
        return disableDirection(4);
    }

    public SwipeConsumer disableBottom() {
        return disableDirection(8);
    }

    public SwipeConsumer disableHorizontal() {
        return disableDirection(3);
    }

    public SwipeConsumer disableVertical() {
        return disableDirection(12);
    }

    public SwipeConsumer disableAllDirections() {
        return disableDirection(15);
    }

    public SwipeConsumer enableDirection(int direction) {
        this.mEnableDirection = direction | this.mEnableDirection;
        return this;
    }

    public SwipeConsumer disableDirection(int direction) {
        if ((this.mDirection & direction) != 0) {
            close();
        }
        this.mEnableDirection = (~direction) & this.mEnableDirection;
        return this;
    }

    public boolean isDirectionEnable(int direction) {
        return direction != 0 && (this.mEnableDirection & direction) == direction;
    }

    public boolean isAllDirectionsEnable() {
        return (this.mEnableDirection & 15) == 15;
    }

    public boolean isVerticalEnable() {
        return (this.mEnableDirection & 12) == 12;
    }

    public boolean isHorizontalEnable() {
        return (this.mEnableDirection & 3) == 3;
    }

    public boolean isLeftEnable() {
        return (this.mEnableDirection & 1) != 0;
    }

    public boolean isRightEnable() {
        return (this.mEnableDirection & 2) != 0;
    }

    public boolean isTopEnable() {
        return (this.mEnableDirection & 4) != 0;
    }

    public boolean isBottomEnable() {
        return (this.mEnableDirection & 8) != 0;
    }

    public SwipeConsumer lockDirection(int direction, boolean lock) {
        if (lock) {
            return lockDirection(direction);
        }
        return unlockDirection(direction);
    }

    public SwipeConsumer lockLeft() {
        return lockDirection(1);
    }

    public SwipeConsumer lockRight() {
        return lockDirection(2);
    }

    public SwipeConsumer lockTop() {
        return lockDirection(4);
    }

    public SwipeConsumer lockBottom() {
        return lockDirection(8);
    }

    public SwipeConsumer lockHorizontal() {
        return lockDirection(3);
    }

    public SwipeConsumer lockVertical() {
        return lockDirection(12);
    }

    public SwipeConsumer lockAllDirections() {
        return lockDirection(15);
    }

    public SwipeConsumer unlockLeft() {
        return unlockDirection(1);
    }

    public SwipeConsumer unlockRight() {
        return unlockDirection(2);
    }

    public SwipeConsumer unlockTop() {
        return unlockDirection(4);
    }

    public SwipeConsumer unlockBottom() {
        return unlockDirection(8);
    }

    public SwipeConsumer unlockHorizontal() {
        return unlockDirection(3);
    }

    public SwipeConsumer unlockVertical() {
        return unlockDirection(12);
    }

    public SwipeConsumer unlockAllDirections() {
        return unlockDirection(15);
    }

    public SwipeConsumer lockDirection(int direction) {
        this.mLockDirection = direction | this.mLockDirection;
        return this;
    }

    public SwipeConsumer unlockDirection(int direction) {
        this.mLockDirection = (~direction) & this.mLockDirection;
        return this;
    }

    public boolean isDirectionLocked(int direction) {
        return direction != 0 && (this.mLockDirection & direction) == direction;
    }

    public boolean isAllDirectionsLocked() {
        return (this.mLockDirection & 15) == 15;
    }

    public boolean isVerticalLocked() {
        return (this.mLockDirection & 12) == 12;
    }

    public boolean isHorizontalLocked() {
        return (this.mLockDirection & 3) == 3;
    }

    public boolean isLeftLocked() {
        return (this.mLockDirection & 1) != 0;
    }

    public boolean isRightLocked() {
        return (this.mLockDirection & 2) != 0;
    }

    public boolean isTopLocked() {
        return (this.mLockDirection & 4) != 0;
    }

    public boolean isBottomLocked() {
        return (this.mLockDirection & 8) != 0;
    }

    public boolean isVerticalDirection() {
        return (this.mDirection & 12) > 0;
    }

    public boolean isHorizontalDirection() {
        return (this.mDirection & 3) > 0;
    }

    public void smoothSlideTo(int startX, int startY, int finalX, int finalY) {
        SwipeHelper swipeHelper = this.mSwipeHelper;
        if (swipeHelper == null || this.mWrapper == null) {
            return;
        }
        swipeHelper.smoothSlideTo(startX, startY, finalX, finalY);
        ViewCompat.postInvalidateOnAnimation(this.mWrapper);
    }

    public void smoothSlideTo(int finalX, int finalY) {
        SwipeHelper swipeHelper = this.mSwipeHelper;
        if (swipeHelper == null || this.mWrapper == null) {
            return;
        }
        swipeHelper.smoothSlideTo(finalX, finalY);
        ViewCompat.postInvalidateOnAnimation(this.mWrapper);
    }

    public boolean isOpened() {
        return getDragState() == 0 && this.mProgress >= 1.0f;
    }

    public boolean isClosed() {
        return getDragState() == 0 && this.mProgress <= 0.0f;
    }

    public <T extends SwipeConsumer> T addConsumer(T t) {
        SmartSwipeWrapper smartSwipeWrapper = this.mWrapper;
        return smartSwipeWrapper != null ? (T) smartSwipeWrapper.addConsumer(t) : t;
    }
}
