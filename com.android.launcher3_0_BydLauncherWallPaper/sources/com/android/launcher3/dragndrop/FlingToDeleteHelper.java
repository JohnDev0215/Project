package com.android.launcher3.dragndrop;

import android.graphics.PointF;
import android.os.SystemClock;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import com.android.launcher3.ButtonDropTarget;
import com.android.launcher3.DropTarget;
import com.android.launcher3.Launcher;
import com.android.launcher3.R;
import com.android.launcher3.util.FlingAnimation;

/* JADX INFO: loaded from: classes4.dex */
public class FlingToDeleteHelper {
    private static final float MAX_FLING_DEGREES = 35.0f;
    private ButtonDropTarget mDropTarget;
    private final int mFlingToDeleteThresholdVelocity;
    private final Launcher mLauncher;
    private VelocityTracker mVelocityTracker;

    public FlingToDeleteHelper(Launcher launcher) {
        this.mLauncher = launcher;
        this.mFlingToDeleteThresholdVelocity = launcher.getResources().getDimensionPixelSize(R.dimen.drag_flingToDeleteMinVelocity);
    }

    public void recordMotionEvent(MotionEvent ev) {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(ev);
    }

    public void recordDragEvent(long dragStartTime, DragEvent event) {
        int i;
        int action = event.getAction();
        int i2 = 2;
        if (action != 1) {
            if (action != 2) {
                if (action != 4) {
                    return;
                } else {
                    i = 1;
                }
            }
            MotionEvent motionEventObtain = MotionEvent.obtain(dragStartTime, SystemClock.uptimeMillis(), i, event.getX(), event.getY(), 0);
            recordMotionEvent(motionEventObtain);
            motionEventObtain.recycle();
        }
        i2 = 0;
        i = i2;
        MotionEvent motionEventObtain2 = MotionEvent.obtain(dragStartTime, SystemClock.uptimeMillis(), i, event.getX(), event.getY(), 0);
        recordMotionEvent(motionEventObtain2);
        motionEventObtain2.recycle();
    }

    public void releaseVelocityTracker() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public DropTarget getDropTarget() {
        return this.mDropTarget;
    }

    public Runnable getFlingAnimation(DropTarget.DragObject dragObject, DragOptions options) {
        PointF pointFIsFlingingToDelete = isFlingingToDelete();
        options.isFlingToDelete = pointFIsFlingingToDelete != null;
        if (options.isFlingToDelete) {
            return new FlingAnimation(dragObject, pointFIsFlingingToDelete, this.mDropTarget, this.mLauncher, options);
        }
        return null;
    }

    private PointF isFlingingToDelete() {
        if (this.mDropTarget == null) {
            this.mDropTarget = (ButtonDropTarget) this.mLauncher.findViewById(R.id.delete_target_text);
        }
        ButtonDropTarget buttonDropTarget = this.mDropTarget;
        if (buttonDropTarget != null && buttonDropTarget.isDropEnabled()) {
            this.mVelocityTracker.computeCurrentVelocity(1000, ViewConfiguration.get(this.mLauncher).getScaledMaximumFlingVelocity());
            PointF pointF = new PointF(this.mVelocityTracker.getXVelocity(), this.mVelocityTracker.getYVelocity());
            float angleBetweenVectors = 36.0f;
            if (this.mVelocityTracker.getYVelocity() < this.mFlingToDeleteThresholdVelocity) {
                angleBetweenVectors = getAngleBetweenVectors(pointF, new PointF(0.0f, -1.0f));
            } else if (this.mLauncher.getDeviceProfile().isVerticalBarLayout() && this.mVelocityTracker.getXVelocity() < this.mFlingToDeleteThresholdVelocity) {
                angleBetweenVectors = getAngleBetweenVectors(pointF, new PointF(-1.0f, 0.0f));
            }
            if (angleBetweenVectors <= Math.toRadians(35.0d)) {
                return pointF;
            }
        }
        return null;
    }

    private float getAngleBetweenVectors(PointF vec1, PointF vec2) {
        return (float) Math.acos(((vec1.x * vec2.x) + (vec1.y * vec2.y)) / (vec1.length() * vec2.length()));
    }
}
