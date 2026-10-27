package org.chromium.content.browser.input;

import android.view.MotionEvent;
import org.chromium.content.browser.ContentViewCore;

/* JADX INFO: loaded from: classes3.dex */
public class JoystickZoomProvider {
    private static final float JOYSTICK_NOISE_THRESHOLD = 0.2f;
    private static final String TAG = "JoystickZoomProvider";
    private static final float ZOOM_SPEED = 1.65f;
    protected final ContentViewCore mContentViewCore;
    protected float mDeviceScaleFactor;
    private long mLastAnimateTimeMillis;
    private AnimationIntervalProvider mSystemAnimationIntervalProvider;
    private float mZoomInVelocity;
    private float mZoomOutVelocity;
    protected Runnable mZoomRunnable;
    private int mZoomXcoord;
    private int mZoomYcoord;

    public JoystickZoomProvider(ContentViewCore contentViewCore, AnimationIntervalProvider animationIntervalProvider) {
        this.mContentViewCore = contentViewCore;
        this.mDeviceScaleFactor = contentViewCore.getRenderCoordinates().getDeviceScaleFactor();
        this.mZoomXcoord = contentViewCore.getViewportWidthPix() / 2;
        this.mZoomYcoord = contentViewCore.getViewportHeightPix() / 2;
        this.mSystemAnimationIntervalProvider = animationIntervalProvider;
    }

    public boolean onMotion(MotionEvent motionEvent) {
        if ((motionEvent.getSource() & 16) == 0) {
            return false;
        }
        computeNewZoomVelocity(motionEvent);
        if (this.mZoomInVelocity == 0.0f && this.mZoomOutVelocity == 0.0f) {
            stop();
            return false;
        }
        if (this.mZoomRunnable == null) {
            this.mZoomRunnable = new Runnable() { // from class: org.chromium.content.browser.input.JoystickZoomProvider.1
                @Override // java.lang.Runnable
                public void run() {
                    JoystickZoomProvider.this.animateZoom();
                }
            };
        }
        if (this.mLastAnimateTimeMillis != 0) {
            return true;
        }
        this.mLastAnimateTimeMillis = this.mSystemAnimationIntervalProvider.getLastAnimationFrameInterval();
        this.mContentViewCore.getContainerView().postOnAnimation(this.mZoomRunnable);
        this.mContentViewCore.pinchBegin(this.mZoomXcoord, this.mZoomYcoord);
        return true;
    }

    protected void stop() {
        if (this.mLastAnimateTimeMillis != 0) {
            this.mContentViewCore.pinchEnd();
            this.mLastAnimateTimeMillis = 0L;
        }
    }

    private void computeNewZoomVelocity(MotionEvent motionEvent) {
        this.mZoomInVelocity = getFilteredAxisValue(motionEvent, 18);
        this.mZoomOutVelocity = getFilteredAxisValue(motionEvent, 17);
    }

    protected void animateZoom() {
        if (!this.mContentViewCore.getContainerView().hasFocus()) {
            stop();
            return;
        }
        if (this.mLastAnimateTimeMillis == 0) {
            return;
        }
        long lastAnimationFrameInterval = this.mSystemAnimationIntervalProvider.getLastAnimationFrameInterval();
        this.mContentViewCore.pinchBy(this.mZoomXcoord, this.mZoomYcoord, (float) Math.pow(1.649999976158142d, ((this.mDeviceScaleFactor * (this.mZoomInVelocity - this.mZoomOutVelocity)) * (lastAnimationFrameInterval - this.mLastAnimateTimeMillis)) / 1000.0f));
        this.mLastAnimateTimeMillis = lastAnimationFrameInterval;
        this.mContentViewCore.getContainerView().postOnAnimation(this.mZoomRunnable);
    }

    private float getFilteredAxisValue(MotionEvent motionEvent, int i) {
        float axisValue = motionEvent.getAxisValue(i);
        if (axisValue > JOYSTICK_NOISE_THRESHOLD) {
            return axisValue;
        }
        return 0.0f;
    }
}
