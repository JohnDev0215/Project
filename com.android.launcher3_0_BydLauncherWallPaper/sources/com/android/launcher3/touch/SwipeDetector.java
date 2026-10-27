package com.android.launcher3.touch;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import com.android.launcher3.Utilities;
import com.android.launcher3.testing.TestProtocol;

/* JADX INFO: loaded from: classes4.dex */
public class SwipeDetector {
    private static final float ANIMATION_DURATION = 1200.0f;
    private static final boolean DBG = false;
    public static final int DIRECTION_BOTH = 3;
    public static final int DIRECTION_NEGATIVE = 2;
    public static final int DIRECTION_POSITIVE = 1;
    public static final float RELEASE_VELOCITY_PX_MS = 1.0f;
    private static final String TAG = "SwipeDetector";
    protected int mActivePointerId;
    private final Direction mDir;
    private float mDisplacement;
    private final PointF mDownPos;
    private boolean mIgnoreSlopWhenSettling;
    private final boolean mIsRtl;
    private float mLastDisplacement;
    private final PointF mLastPos;
    private final Listener mListener;
    private final float mMaxVelocity;
    private int mScrollConditions;
    private ScrollState mState;
    private float mSubtractDisplacement;
    private final float mTouchSlop;
    private VelocityTracker mVelocityTracker;
    public static final Direction VERTICAL = new Direction() { // from class: com.android.launcher3.touch.SwipeDetector.1
        @Override // com.android.launcher3.touch.SwipeDetector.Direction
        boolean isNegative(float displacement) {
            return displacement > 0.0f;
        }

        @Override // com.android.launcher3.touch.SwipeDetector.Direction
        boolean isPositive(float displacement) {
            return displacement < 0.0f;
        }

        @Override // com.android.launcher3.touch.SwipeDetector.Direction
        float getDisplacement(MotionEvent ev, int pointerIndex, PointF refPoint, boolean isRtl) {
            return ev.getY(pointerIndex) - refPoint.y;
        }

        @Override // com.android.launcher3.touch.SwipeDetector.Direction
        float getActiveTouchSlop(MotionEvent ev, int pointerIndex, PointF downPos) {
            return Math.abs(ev.getX(pointerIndex) - downPos.x);
        }

        @Override // com.android.launcher3.touch.SwipeDetector.Direction
        float getVelocity(VelocityTracker tracker, boolean isRtl) {
            return tracker.getYVelocity();
        }
    };
    public static final Direction HORIZONTAL = new Direction() { // from class: com.android.launcher3.touch.SwipeDetector.2
        @Override // com.android.launcher3.touch.SwipeDetector.Direction
        boolean isNegative(float displacement) {
            return displacement < 0.0f;
        }

        @Override // com.android.launcher3.touch.SwipeDetector.Direction
        boolean isPositive(float displacement) {
            return displacement > 0.0f;
        }

        @Override // com.android.launcher3.touch.SwipeDetector.Direction
        float getDisplacement(MotionEvent ev, int pointerIndex, PointF refPoint, boolean isRtl) {
            float x = ev.getX(pointerIndex) - refPoint.x;
            return isRtl ? -x : x;
        }

        @Override // com.android.launcher3.touch.SwipeDetector.Direction
        float getActiveTouchSlop(MotionEvent ev, int pointerIndex, PointF downPos) {
            return Math.abs(ev.getY(pointerIndex) - downPos.y);
        }

        @Override // com.android.launcher3.touch.SwipeDetector.Direction
        float getVelocity(VelocityTracker tracker, boolean isRtl) {
            float xVelocity = tracker.getXVelocity();
            return isRtl ? -xVelocity : xVelocity;
        }
    };

    public static abstract class Direction {
        abstract float getActiveTouchSlop(MotionEvent ev, int pointerIndex, PointF downPos);

        abstract float getDisplacement(MotionEvent ev, int pointerIndex, PointF refPoint, boolean isRtl);

        abstract float getVelocity(VelocityTracker tracker, boolean isRtl);

        abstract boolean isNegative(float displacement);

        abstract boolean isPositive(float displacement);
    }

    enum ScrollState {
        IDLE,
        DRAGGING,
        SETTLING
    }

    private void setState(ScrollState newState) {
        if (TestProtocol.sDebugTracing) {
            Log.d(TestProtocol.NO_ALLAPPS_EVENT_TAG, "setState -- start: " + newState);
        }
        if (newState == ScrollState.DRAGGING) {
            initializeDragging();
            if (this.mState == ScrollState.IDLE) {
                if (TestProtocol.sDebugTracing) {
                    Log.d(TestProtocol.NO_ALLAPPS_EVENT_TAG, "setState -- 1: " + newState);
                }
                reportDragStart(false);
            } else if (this.mState == ScrollState.SETTLING) {
                reportDragStart(true);
            }
        }
        if (newState == ScrollState.SETTLING) {
            reportDragEnd();
        }
        this.mState = newState;
        if (TestProtocol.sDebugTracing) {
            Log.e(TestProtocol.NO_ALLAPPS_EVENT_TAG, "setState: " + newState + " @ " + Log.getStackTraceString(new Throwable()));
        }
    }

    public boolean isDraggingOrSettling() {
        return this.mState == ScrollState.DRAGGING || this.mState == ScrollState.SETTLING;
    }

    public int getDownX() {
        return (int) this.mDownPos.x;
    }

    public int getDownY() {
        return (int) this.mDownPos.y;
    }

    public boolean isIdleState() {
        return this.mState == ScrollState.IDLE;
    }

    public boolean isSettlingState() {
        return this.mState == ScrollState.SETTLING;
    }

    public boolean isDraggingState() {
        return this.mState == ScrollState.DRAGGING;
    }

    public interface Listener {
        boolean onDrag(float displacement);

        void onDragEnd(float velocity, boolean fling);

        void onDragStart(boolean start);

        default boolean onDrag(float displacement, MotionEvent event) {
            return onDrag(displacement);
        }
    }

    public SwipeDetector(Context context, Listener l, Direction dir) {
        this(ViewConfiguration.get(context), l, dir, Utilities.isRtl(context.getResources()));
    }

    protected SwipeDetector(ViewConfiguration config, Listener l, Direction dir, boolean isRtl) {
        this.mActivePointerId = -1;
        this.mState = ScrollState.IDLE;
        this.mDownPos = new PointF();
        this.mLastPos = new PointF();
        this.mListener = l;
        this.mDir = dir;
        this.mIsRtl = isRtl;
        this.mTouchSlop = config.getScaledTouchSlop();
        this.mMaxVelocity = config.getScaledMaximumFlingVelocity();
    }

    public void setDetectableScrollConditions(int scrollDirectionFlags, boolean ignoreSlop) {
        this.mScrollConditions = scrollDirectionFlags;
        this.mIgnoreSlopWhenSettling = ignoreSlop;
    }

    public int getScrollDirections() {
        return this.mScrollConditions;
    }

    private boolean shouldScrollStart(MotionEvent ev, int pointerIndex) {
        if (Math.max(this.mDir.getActiveTouchSlop(ev, pointerIndex, this.mDownPos), this.mTouchSlop) > Math.abs(this.mDisplacement)) {
            return false;
        }
        return ((this.mScrollConditions & 2) > 0 && this.mDir.isNegative(this.mDisplacement)) || ((this.mScrollConditions & 1) > 0 && this.mDir.isPositive(this.mDisplacement));
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            Method dump skipped, instruction units count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.touch.SwipeDetector.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void finishedScrolling() {
        setState(ScrollState.IDLE);
    }

    private boolean reportDragStart(boolean recatch) {
        this.mListener.onDragStart(!recatch);
        return true;
    }

    private void initializeDragging() {
        if (this.mState == ScrollState.SETTLING && this.mIgnoreSlopWhenSettling) {
            this.mSubtractDisplacement = 0.0f;
        }
        if (this.mDisplacement > 0.0f) {
            this.mSubtractDisplacement = this.mTouchSlop;
        } else {
            this.mSubtractDisplacement = -this.mTouchSlop;
        }
    }

    public boolean wasInitialTouchPositive() {
        return this.mDir.isPositive(this.mSubtractDisplacement);
    }

    private boolean reportDragging(MotionEvent event) {
        float f = this.mDisplacement;
        if (f == this.mLastDisplacement) {
            return true;
        }
        this.mLastDisplacement = f;
        return this.mListener.onDrag(f - this.mSubtractDisplacement, event);
    }

    private void reportDragEnd() {
        this.mVelocityTracker.computeCurrentVelocity(1000, this.mMaxVelocity);
        float velocity = this.mDir.getVelocity(this.mVelocityTracker, this.mIsRtl) / 1000.0f;
        this.mListener.onDragEnd(velocity, Math.abs(velocity) > 1.0f);
    }

    public static long calculateDuration(float velocity, float progressNeeded) {
        float fMax = Math.max(2.0f, Math.abs(velocity * 0.5f));
        return (long) Math.max(100.0f, (ANIMATION_DURATION / fMax) * Math.max(0.2f, progressNeeded));
    }
}
