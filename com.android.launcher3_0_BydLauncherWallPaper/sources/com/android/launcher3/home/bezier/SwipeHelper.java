package com.android.launcher3.home.bezier;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class SwipeHelper {
    public static final int INVALID_POINTER = -1;
    public static final int POINTER_NESTED_FLY = -3;
    public static final int POINTER_NESTED_SCROLL = -2;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_NONE_TOUCH = 3;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "SwipeHelper";
    private static final Interpolator sInterpolator = new Interpolator() { // from class: com.android.launcher3.home.bezier.SwipeHelper.1
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float t) {
            float f = t - 1.0f;
            return (f * f * f * f * f) + 1.0f;
        }
    };
    private int mClampedDistanceX;
    private int mClampedDistanceY;
    private int mDragState;
    private float[] mInitialMotionX;
    private float[] mInitialMotionY;
    private float[] mLastMotionX;
    private float[] mLastMotionY;
    private float mMaxVelocity;
    private float mMinVelocity;
    private final ViewGroup mParentView;
    private int mPointersDown;
    private boolean mReleaseInProgress;
    private OverScroller mScroller;
    private final SwipeConsumer mSwipeConsumer;
    private int mTouchSlop;
    private VelocityTracker mVelocityTracker;
    private final ViewConfiguration viewConfiguration;
    private int maxSettleDuration = 600;
    private int mActivePointerId = -1;
    public boolean isUp = false;

    public static SwipeHelper create(ViewGroup forParent, SwipeConsumer consumer, Interpolator interpolator) {
        return new SwipeHelper(forParent.getContext(), forParent, consumer, interpolator);
    }

    public static SwipeHelper create(ViewGroup forParent, SwipeConsumer consumer) {
        return create(forParent, consumer, (Interpolator) null);
    }

    public static SwipeHelper create(ViewGroup forParent, float sensitivity, SwipeConsumer consumer, Interpolator interpolator) {
        SwipeHelper swipeHelperCreate = create(forParent, consumer, interpolator);
        swipeHelperCreate.mTouchSlop = (int) (swipeHelperCreate.mTouchSlop * (1.0f / sensitivity));
        return swipeHelperCreate;
    }

    public static SwipeHelper create(ViewGroup forParent, float sensitivity, SwipeConsumer cb) {
        return create(forParent, sensitivity, cb, null);
    }

    public void setSensitivity(float sensitivity) {
        this.mTouchSlop = (int) (this.viewConfiguration.getScaledTouchSlop() * (1.0f / sensitivity));
    }

    private SwipeHelper(Context context, ViewGroup forParent, SwipeConsumer cb, Interpolator interpolator) {
        if (forParent == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (cb == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.mParentView = forParent;
        this.mSwipeConsumer = cb;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.viewConfiguration = viewConfiguration;
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMaxVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mMinVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        setInterpolator(context, interpolator);
    }

    public void setInterpolator(Context context, Interpolator interpolator) {
        if (interpolator == null) {
            interpolator = sInterpolator;
        }
        if (this.mScroller != null) {
            abort();
            this.mScroller = null;
        }
        this.mScroller = new OverScroller(context, interpolator);
    }

    public SwipeHelper setMinVelocity(float minVel) {
        this.mMinVelocity = minVel;
        return this;
    }

    public float getMinVelocity() {
        return this.mMinVelocity;
    }

    public int getDragState() {
        return this.mDragState;
    }

    public int getActivePointerId() {
        return this.mActivePointerId;
    }

    public int getTouchSlop() {
        return this.mTouchSlop;
    }

    public void cancel() {
        this.mActivePointerId = -1;
        clearMotionHistory();
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void abort() {
        cancel();
        int i = this.mDragState;
        if (i == 2 || i == 3) {
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            this.mScroller.abortAnimation();
            int currX2 = this.mScroller.getCurrX();
            int currY2 = this.mScroller.getCurrY();
            this.mSwipeConsumer.onSwipeDistanceChanged(currX2, currY2, currX2 - currX, currY2 - currY);
        }
        setDragState(0);
    }

    public boolean smoothSlideTo(int startX, int startY, int finalX, int finalY) {
        this.mClampedDistanceX = startX;
        this.mClampedDistanceY = startY;
        return smoothSlideTo(finalX, finalY);
    }

    public boolean smoothSlideTo(int finalX, int finalY) {
        boolean zSmoothSettleCapturedViewTo;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            zSmoothSettleCapturedViewTo = smoothSettleCapturedViewTo(finalX, finalY, (int) velocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId));
        } else {
            zSmoothSettleCapturedViewTo = smoothSettleCapturedViewTo(finalX, finalY, 0, 0);
        }
        this.mActivePointerId = -1;
        return zSmoothSettleCapturedViewTo;
    }

    public boolean settleCapturedViewAt(int finalX, int finalY) {
        if (!this.mReleaseInProgress) {
            throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
        }
        return smoothSettleCapturedViewTo(finalX, finalY, (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId));
    }

    private boolean smoothSettleCapturedViewTo(int finalX, int finalY, int xvel, int yvel) {
        int i = this.mClampedDistanceX;
        int i2 = this.mClampedDistanceY;
        int i3 = finalX - i;
        int i4 = finalY - i2;
        this.mScroller.abortAnimation();
        if (i3 == 0 && i4 == 0) {
            setDragState(2);
            this.mSwipeConsumer.onSwipeDistanceChanged(finalX, finalY, i3, i4);
            setDragState(0);
            return false;
        }
        int iComputeSettleDuration = computeSettleDuration(i3, i4, xvel, yvel);
        Log.e(TAG, "smoothSettleCapturedViewTo: duration = " + iComputeSettleDuration);
        this.mScroller.startScroll(i, i2, i3, i4, iComputeSettleDuration);
        Log.e(TAG, "smoothSettleCapturedViewTo: startX =" + i + ",startTop =" + i2);
        Log.e(TAG, "smoothSettleCapturedViewTo: finalX =" + finalX + ",finalY =" + finalY);
        Log.e(TAG, "smoothSettleCapturedViewTo: xvel =" + xvel + ",yvel =" + yvel);
        Log.e(TAG, "smoothSettleCapturedViewTo: dx =" + i3 + ",dy =" + i4);
        int i5 = 40;
        while (i < 0) {
            int i6 = i + i5;
            if (i6 > 0) {
                i5 = -i;
                i6 = 0;
            }
            Log.e(TAG, "smoothSettleCapturedViewTo: duration =" + (((int) (Math.pow(i, 2.0d) - Math.pow(i6, 2.0d))) / 80) + ",startX= " + i + ",start = " + i6 + ",offer=" + i5);
            i = i6;
        }
        setDragState(2);
        return true;
    }

    private int computeSettleDuration(int dx, int dy, int xvel, int yvel) {
        float f;
        float f2;
        float f3;
        float f4;
        int iClampMag = clampMag(xvel, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int iClampMag2 = clampMag(yvel, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int iAbs = Math.abs(dx);
        int iAbs2 = Math.abs(dy);
        int iAbs3 = Math.abs(iClampMag);
        int iAbs4 = Math.abs(iClampMag2);
        int i = iAbs3 + iAbs4;
        int i2 = iAbs + iAbs2;
        if (iClampMag != 0) {
            f = iAbs3;
            f2 = i;
        } else {
            f = iAbs;
            f2 = i2;
        }
        float f5 = f / f2;
        if (iClampMag2 != 0) {
            f3 = iAbs4;
            f4 = i;
        } else {
            f3 = iAbs2;
            f4 = i2;
        }
        float f6 = dx;
        float f7 = dy;
        return (int) ((computeAxisDuration(dx, iClampMag, this.mSwipeConsumer.getHorizontalRange(f6, f7)) * f5) + (computeAxisDuration(dy, iClampMag2, this.mSwipeConsumer.getVerticalRange(f6, f7)) * (f3 / f4)));
    }

    private int computeAxisDuration(int delta, int velocity, int motionRange) {
        int iAbs;
        if (delta == 0) {
            return 0;
        }
        int width = this.mParentView.getWidth();
        float f = width >> 1;
        float fDistanceInfluenceForSnapDuration = f + (distanceInfluenceForSnapDuration(Math.min(1.0f, Math.abs(delta) / width)) * f);
        int iAbs2 = Math.abs(velocity);
        if (iAbs2 > 0) {
            iAbs = Math.round(Math.abs(fDistanceInfluenceForSnapDuration / iAbs2) * 1000.0f) * 4;
        } else {
            iAbs = (int) ((Math.abs(delta) / motionRange) * this.maxSettleDuration);
        }
        return Math.min(iAbs, this.maxSettleDuration);
    }

    private int clampMag(int value, int absMin, int absMax) {
        int iAbs = Math.abs(value);
        if (iAbs < absMin) {
            return 0;
        }
        return iAbs > absMax ? value > 0 ? absMax : -absMax : value;
    }

    private float clampMag(float value, float absMin, float absMax) {
        float fAbs = Math.abs(value);
        if (fAbs < absMin) {
            return 0.0f;
        }
        return fAbs > absMax ? value > 0.0f ? absMax : -absMax : value;
    }

    private float distanceInfluenceForSnapDuration(float f) {
        return (float) Math.sin((f - 0.5f) * 0.47123894f);
    }

    public boolean continueSettling() {
        if (this.mDragState == 2) {
            boolean zComputeScrollOffset = this.mScroller.computeScrollOffset();
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            int i = currX - this.mClampedDistanceX;
            int i2 = currY - this.mClampedDistanceY;
            if (i != 0) {
                this.mClampedDistanceX = currX;
            }
            if (i2 != 0) {
                this.mClampedDistanceY = currY;
            }
            if (i != 0 || i2 != 0) {
                this.mSwipeConsumer.onSwipeDistanceChanged(currX, currY, i, i2);
            }
            if (zComputeScrollOffset && currX == this.mScroller.getFinalX() && currY == this.mScroller.getFinalY()) {
                this.mScroller.abortAnimation();
                zComputeScrollOffset = false;
            }
            if (!zComputeScrollOffset) {
                setDragState(0);
            }
        }
        return this.mDragState == 2;
    }

    public void dispatchViewReleased(float xvel, float yvel) {
        this.mReleaseInProgress = true;
        this.mSwipeConsumer.onSwipeReleased(xvel, yvel);
        this.mReleaseInProgress = false;
        if (this.mDragState == 1) {
            setDragState(0);
        }
    }

    private void clearMotionHistory() {
        float[] fArr = this.mInitialMotionX;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, 0.0f);
        Arrays.fill(this.mInitialMotionY, 0.0f);
        Arrays.fill(this.mLastMotionX, 0.0f);
        Arrays.fill(this.mLastMotionY, 0.0f);
        this.mPointersDown = 0;
    }

    private void clearMotionHistory(int pointerId) {
        if (this.mInitialMotionX == null || !isPointerDown(pointerId)) {
            return;
        }
        this.mInitialMotionX[pointerId] = 0.0f;
        this.mInitialMotionY[pointerId] = 0.0f;
        this.mLastMotionX[pointerId] = 0.0f;
        this.mLastMotionY[pointerId] = 0.0f;
        this.mPointersDown = (~(1 << pointerId)) & this.mPointersDown;
    }

    private void ensureMotionHistorySizeForId(int pointerId) {
        float[] fArr = this.mInitialMotionX;
        if (fArr == null || fArr.length <= pointerId) {
            int i = pointerId + 1;
            float[] fArr2 = new float[i];
            float[] fArr3 = new float[i];
            float[] fArr4 = new float[i];
            float[] fArr5 = new float[i];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.mInitialMotionY;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.mLastMotionX;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.mLastMotionY;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
            }
            this.mInitialMotionX = fArr2;
            this.mInitialMotionY = fArr3;
            this.mLastMotionX = fArr4;
            this.mLastMotionY = fArr5;
        }
    }

    private void saveInitialMotion(float x, float y, int pointerId) {
        ensureMotionHistorySizeForId(pointerId);
        float[] fArr = this.mInitialMotionX;
        this.mLastMotionX[pointerId] = x;
        fArr[pointerId] = x;
        float[] fArr2 = this.mInitialMotionY;
        this.mLastMotionY[pointerId] = y;
        fArr2[pointerId] = y;
        this.mPointersDown |= 1 << pointerId;
    }

    private void saveLastMotion(MotionEvent ev) {
        int pointerCount = ev.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = ev.getPointerId(i);
            if (isValidPointerForActionMove(pointerId)) {
                float x = ev.getX(i);
                float y = ev.getY(i);
                this.mLastMotionX[pointerId] = x;
                this.mLastMotionY[pointerId] = y;
            }
        }
    }

    public boolean isPointerDown(int pointerId) {
        return (this.mPointersDown & (1 << pointerId)) != 0;
    }

    void setDragState(int state) {
        if (this.mDragState != state) {
            this.mDragState = state;
            this.mSwipeConsumer.onStateChanged(state);
        }
    }

    private boolean trySwipe(int pointerId, boolean settling, float downX, float downY, float dx, float dy) {
        return trySwipe(pointerId, settling, downX, downY, dx, dy, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean trySwipe(int r11, boolean r12, float r13, float r14, float r15, float r16, boolean r17) {
        /*
            r10 = this;
            r0 = r10
            r7 = r11
            r8 = r12
            int r1 = r0.mActivePointerId
            r9 = 1
            if (r1 != r7) goto L9
            return r9
        L9:
            if (r8 != 0) goto L1e
            int r1 = r0.mDragState
            r2 = 2
            if (r1 != r2) goto L11
            goto L1e
        L11:
            com.android.launcher3.home.bezier.SwipeConsumer r1 = r0.mSwipeConsumer
            r2 = r11
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            boolean r1 = r1.tryAcceptMoving(r2, r3, r4, r5, r6)
            goto L26
        L1e:
            com.android.launcher3.home.bezier.SwipeConsumer r1 = r0.mSwipeConsumer
            r2 = r13
            r3 = r14
            boolean r1 = r1.tryAcceptSettling(r11, r13, r14)
        L26:
            r2 = 0
            if (r1 == 0) goto L5c
            r0.mActivePointerId = r7
            r1 = 0
            if (r7 < 0) goto L3d
            float[] r3 = r0.mInitialMotionX
            int r4 = r3.length
            if (r7 >= r4) goto L3d
            float[] r4 = r0.mInitialMotionY
            int r5 = r4.length
            if (r7 >= r5) goto L3d
            r1 = r3[r7]
            r3 = r4[r7]
            goto L3e
        L3d:
            r3 = r1
        L3e:
            com.android.launcher3.home.bezier.SwipeConsumer r4 = r0.mSwipeConsumer
            r4.onSwipeAccepted(r11, r12, r1, r3)
            com.android.launcher3.home.bezier.SwipeConsumer r1 = r0.mSwipeConsumer
            int r1 = r1.clampDistanceHorizontal(r2, r2)
            r0.mClampedDistanceX = r1
            com.android.launcher3.home.bezier.SwipeConsumer r1 = r0.mSwipeConsumer
            int r1 = r1.clampDistanceVertical(r2, r2)
            r0.mClampedDistanceY = r1
            if (r17 == 0) goto L57
            r1 = r9
            goto L58
        L57:
            r1 = 3
        L58:
            r10.setDragState(r1)
            return r9
        L5c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.home.bezier.SwipeHelper.trySwipe(int, boolean, float, float, float, float, boolean):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean shouldInterceptTouchEvent(android.view.MotionEvent r14) {
        /*
            Method dump skipped, instruction units count: 239
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.home.bezier.SwipeHelper.shouldInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public void processTouchEvent(MotionEvent motionEvent) {
        int iFindPointerIndex;
        int i;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0 && this.mDragState != 1) {
            cancel();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int i2 = 0;
        if (actionMasked == 0) {
            this.isUp = false;
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            saveInitialMotion(x, y, pointerId);
            int i3 = this.mDragState;
            if (i3 != 1) {
                trySwipe(pointerId, i3 == 2 || i3 == 3, x, y, 0.0f, 0.0f);
                return;
            }
            return;
        }
        if (actionMasked == 1) {
            this.isUp = true;
            if (this.mDragState == 1) {
                releaseViewForPointerUp();
            }
            cancel();
            return;
        }
        if (actionMasked == 2) {
            if (this.mDragState == 1) {
                if (!isValidPointerForActionMove(this.mActivePointerId) || (iFindPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId)) < 0) {
                    return;
                }
                float x2 = motionEvent.getX(iFindPointerIndex);
                float y2 = motionEvent.getY(iFindPointerIndex);
                float[] fArr = this.mLastMotionX;
                int i4 = this.mActivePointerId;
                int i5 = (int) (x2 - fArr[i4]);
                int i6 = (int) (y2 - this.mLastMotionY[i4]);
                dragTo(this.mClampedDistanceX + i5, this.mClampedDistanceY + i6, i5, i6);
            } else {
                int pointerCount = motionEvent.getPointerCount();
                for (int i7 = 0; i7 < pointerCount; i7++) {
                    int pointerId2 = motionEvent.getPointerId(i7);
                    if (isValidPointerForActionMove(pointerId2)) {
                        float x3 = motionEvent.getX(i7);
                        float y3 = motionEvent.getY(i7);
                        float f = this.mInitialMotionX[pointerId2];
                        float f2 = this.mInitialMotionY[pointerId2];
                        float f3 = x3 - f;
                        float f4 = y3 - f2;
                        if (checkTouchSlop(f3, f4) && trySwipe(pointerId2, false, f, f2, f3, f4)) {
                            break;
                        }
                    }
                }
            }
            saveLastMotion(motionEvent);
            return;
        }
        if (actionMasked == 3) {
            if (this.mDragState == 1) {
                dispatchViewReleased(0.0f, 0.0f);
            }
            cancel();
            return;
        }
        if (actionMasked == 5) {
            int pointerId3 = motionEvent.getPointerId(actionIndex);
            float x4 = motionEvent.getX(actionIndex);
            float y4 = motionEvent.getY(actionIndex);
            saveInitialMotion(x4, y4, pointerId3);
            if (this.mDragState == 1) {
                trySwipe(pointerId3, true, x4, y4, 0.0f, 0.0f);
                return;
            }
            return;
        }
        if (actionMasked != 6) {
            return;
        }
        int pointerId4 = motionEvent.getPointerId(actionIndex);
        if (this.mDragState == 1 && pointerId4 == this.mActivePointerId) {
            int pointerCount2 = motionEvent.getPointerCount();
            while (true) {
                if (i2 >= pointerCount2) {
                    i = -1;
                    break;
                }
                int pointerId5 = motionEvent.getPointerId(i2);
                if (pointerId5 != this.mActivePointerId && isValidPointerForActionMove(pointerId5)) {
                    float[] fArr2 = this.mInitialMotionX;
                    if (trySwipe(pointerId5, true, fArr2[pointerId5], fArr2[pointerId5], 0.0f, 0.0f)) {
                        i = this.mActivePointerId;
                        break;
                    }
                }
                i2++;
            }
            if (i == -1) {
                releaseViewForPointerUp();
            }
        }
        clearMotionHistory(pointerId4);
    }

    private boolean checkTouchSlop(float dx, float dy) {
        boolean z = this.mSwipeConsumer.getHorizontalRange(dx, dy) > 0;
        boolean z2 = this.mSwipeConsumer.getVerticalRange(dx, dy) > 0;
        if (!z || !z2) {
            return z ? Math.abs(dx) > ((float) this.mTouchSlop) : z2 && Math.abs(dy) > ((float) this.mTouchSlop);
        }
        float f = (dx * dx) + (dy * dy);
        int i = this.mTouchSlop;
        return f > ((float) (i * i));
    }

    private void releaseViewForPointerUp() {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
        dispatchViewReleased(clampMag(this.mVelocityTracker.getXVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity), clampMag(this.mVelocityTracker.getYVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity));
    }

    public boolean nestedScrollingDrag(int dx, int dy, int[] consumed, boolean fly) {
        int iClampDistanceHorizontal;
        int iClampDistanceVertical;
        if (this.mDragState == 0) {
            if (!trySwipe(fly ? -3 : -2, false, 0.0f, 0.0f, dx, dy, false)) {
                return false;
            }
        }
        int i = this.mClampedDistanceX;
        if (i == 0 && dx == 0) {
            iClampDistanceHorizontal = 0;
        } else {
            iClampDistanceHorizontal = this.mSwipeConsumer.clampDistanceHorizontal(i + dx, dx);
            consumed[0] = this.mClampedDistanceX - iClampDistanceHorizontal;
        }
        int i2 = this.mClampedDistanceY;
        if (i2 == 0 && dy == 0) {
            iClampDistanceVertical = 0;
        } else {
            iClampDistanceVertical = this.mSwipeConsumer.clampDistanceVertical(i2 + dy, dy);
            consumed[1] = this.mClampedDistanceY - iClampDistanceVertical;
        }
        if (this.mClampedDistanceX == 0 && this.mClampedDistanceY == 0 && consumed[0] == 0 && consumed[1] == 0) {
            this.mActivePointerId = -1;
            setDragState(0);
            return false;
        }
        dragTo(iClampDistanceHorizontal, iClampDistanceVertical, -consumed[0], -consumed[1]);
        return true;
    }

    public void nestedScrollingRelease() {
        if (this.mDragState == 3) {
            dispatchViewReleased(0.0f, 0.0f);
        }
    }

    private void dragTo(int x, int y, int dx, int dy) {
        int i = this.mClampedDistanceX;
        int i2 = this.mClampedDistanceY;
        if (dx != 0) {
            x = this.mSwipeConsumer.clampDistanceHorizontal(x, dx);
            this.mClampedDistanceX = x;
        }
        if (dy != 0) {
            y = this.mSwipeConsumer.clampDistanceVertical(y, dy);
            this.mClampedDistanceY = y;
        }
        if (dx == 0 && dy == 0) {
            return;
        }
        this.mSwipeConsumer.onSwipeDistanceChanged(x, y, x - i, y - i2);
    }

    public SwipeConsumer getSwipeConsumer() {
        return this.mSwipeConsumer;
    }

    private boolean isValidPointerForActionMove(int pointerId) {
        if (isPointerDown(pointerId)) {
            return true;
        }
        Log.e(TAG, "Ignoring pointerId=" + pointerId + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  SwipeHelper did not receive all the events in the event stream.");
        return false;
    }

    public int getMaxSettleDuration() {
        return this.maxSettleDuration;
    }

    public void setMaxSettleDuration(int maxSettleDuration) {
        this.maxSettleDuration = maxSettleDuration;
    }
}
