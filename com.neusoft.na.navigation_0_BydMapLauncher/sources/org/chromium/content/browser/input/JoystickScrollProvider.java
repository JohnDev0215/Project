package org.chromium.content.browser.input;

import android.R;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import com.bydux.api.common.tools.util.ListUtils;
import org.chromium.base.Log;
import org.chromium.content.browser.ContentViewCore;

/* JADX INFO: loaded from: classes3.dex */
public class JoystickScrollProvider {
    private static final float JOYSTICK_SCROLL_DEADZONE = 0.2f;
    private static final float JOYSTICK_SCROLL_FACTOR_MULTIPLIER = 20.0f;
    private static final float SCROLL_FACTOR_FALLBACK = 128.0f;
    private static final String TAG = "JoystickScroll";
    private boolean mEnabled = true;
    private long mLastAnimateTimeMillis;
    private float mScrollFactor;
    private Runnable mScrollRunnable;
    private float mScrollVelocityX;
    private float mScrollVelocityY;
    private final ContentViewCore mView;

    public JoystickScrollProvider(ContentViewCore contentViewCore) {
        this.mView = contentViewCore;
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
        if (z) {
            return;
        }
        stop();
    }

    public boolean onMotion(MotionEvent motionEvent) {
        if (!this.mEnabled || (motionEvent.getSource() & 16) == 0) {
            return false;
        }
        Log.d(TAG, "Joystick left stick axis: " + motionEvent.getAxisValue(0) + ListUtils.DEFAULT_JOIN_SEPARATOR + motionEvent.getAxisValue(1));
        computeNewScrollVelocity(motionEvent);
        if (this.mScrollVelocityX == 0.0f && this.mScrollVelocityY == 0.0f) {
            stop();
            return false;
        }
        if (this.mScrollRunnable == null) {
            this.mScrollRunnable = new Runnable() { // from class: org.chromium.content.browser.input.JoystickScrollProvider.1
                @Override // java.lang.Runnable
                public void run() {
                    JoystickScrollProvider.this.animateScroll();
                }
            };
        }
        if (this.mLastAnimateTimeMillis == 0) {
            this.mView.getContainerView().postOnAnimation(this.mScrollRunnable);
            this.mLastAnimateTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animateScroll() {
        if (this.mLastAnimateTimeMillis == 0) {
            return;
        }
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        float f = jCurrentAnimationTimeMillis - this.mLastAnimateTimeMillis;
        this.mView.scrollBy((this.mScrollVelocityX * f) / 1000.0f, (this.mScrollVelocityY * f) / 1000.0f, true);
        this.mLastAnimateTimeMillis = jCurrentAnimationTimeMillis;
        this.mView.getContainerView().postOnAnimation(this.mScrollRunnable);
    }

    private void stop() {
        this.mLastAnimateTimeMillis = 0L;
    }

    private void computeNewScrollVelocity(MotionEvent motionEvent) {
        if (this.mScrollFactor == 0.0f) {
            TypedValue typedValue = new TypedValue();
            if (!this.mView.getContext().getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
                this.mScrollFactor = typedValue.getDimension(this.mView.getContext().getResources().getDisplayMetrics());
            } else {
                Log.d(TAG, "Theme attribute listPreferredItemHeight not definedswitching to fallback scroll factor ");
                this.mScrollFactor = this.mView.getRenderCoordinates().getDeviceScaleFactor() * SCROLL_FACTOR_FALLBACK;
            }
        }
        this.mScrollVelocityX = getFilteredAxisValue(motionEvent, 0) * this.mScrollFactor * JOYSTICK_SCROLL_FACTOR_MULTIPLIER;
        this.mScrollVelocityY = getFilteredAxisValue(motionEvent, 1) * this.mScrollFactor * JOYSTICK_SCROLL_FACTOR_MULTIPLIER;
    }

    private float getFilteredAxisValue(MotionEvent motionEvent, int i) {
        float axisValue = motionEvent.getAxisValue(i);
        if (axisValue > JOYSTICK_SCROLL_DEADZONE || axisValue < -0.2f) {
            return axisValue;
        }
        return 0.0f;
    }
}
