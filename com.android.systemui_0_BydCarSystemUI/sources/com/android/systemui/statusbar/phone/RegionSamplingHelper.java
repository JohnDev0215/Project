package com.android.systemui.statusbar.phone;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.view.CompositionSamplingListener;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewRootImpl;
import android.view.ViewTreeObserver;
import com.android.systemui.R;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class RegionSamplingHelper implements View.OnAttachStateChangeListener, View.OnLayoutChangeListener {
    private final SamplingCallback mCallback;
    private float mCurrentMedianLuma;
    private boolean mFirstSamplingAfterStart;
    private boolean mIsDestroyed;
    private float mLastMedianLuma;
    private final float mLuminanceChangeThreshold;
    private final float mLuminanceThreshold;
    private final View mSampledView;
    private final CompositionSamplingListener mSamplingListener;
    private boolean mWaitingOnDraw;
    private boolean mWindowVisible;
    private final Handler mHandler = new Handler();
    private final Rect mSamplingRequestBounds = new Rect();
    private final Rect mRegisteredSamplingBounds = new Rect();
    private boolean mSamplingEnabled = false;
    private boolean mSamplingListenerRegistered = false;
    private SurfaceControl mRegisteredStopLayer = null;
    private ViewTreeObserver.OnDrawListener mUpdateOnDraw = new ViewTreeObserver.OnDrawListener() { // from class: com.android.systemui.statusbar.phone.RegionSamplingHelper.1
        @Override // android.view.ViewTreeObserver.OnDrawListener
        public void onDraw() {
            RegionSamplingHelper.this.mHandler.post(RegionSamplingHelper.this.mRemoveDrawRunnable);
            RegionSamplingHelper.this.onDraw();
        }
    };
    private Runnable mRemoveDrawRunnable = new Runnable() { // from class: com.android.systemui.statusbar.phone.RegionSamplingHelper.2
        @Override // java.lang.Runnable
        public void run() {
            RegionSamplingHelper.this.mSampledView.getViewTreeObserver().removeOnDrawListener(RegionSamplingHelper.this.mUpdateOnDraw);
        }
    };

    public interface SamplingCallback {
        Rect getSampledRegion(View sampledView);

        default boolean isSamplingEnabled() {
            return true;
        }

        void onRegionDarknessChanged(boolean isRegionDark);
    }

    public RegionSamplingHelper(View sampledView, SamplingCallback samplingCallback) {
        this.mSamplingListener = new CompositionSamplingListener(sampledView.getContext().getMainExecutor()) { // from class: com.android.systemui.statusbar.phone.RegionSamplingHelper.3
            public void onSampleCollected(float medianLuma) {
                if (RegionSamplingHelper.this.mSamplingEnabled) {
                    RegionSamplingHelper.this.updateMediaLuma(medianLuma);
                }
            }
        };
        this.mSampledView = sampledView;
        sampledView.addOnAttachStateChangeListener(this);
        sampledView.addOnLayoutChangeListener(this);
        Resources resources = sampledView.getResources();
        this.mLuminanceThreshold = resources.getFloat(R.dimen.navigation_luminance_threshold);
        this.mLuminanceChangeThreshold = resources.getFloat(R.dimen.navigation_luminance_change_threshold);
        this.mCallback = samplingCallback;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDraw() {
        if (this.mWaitingOnDraw) {
            this.mWaitingOnDraw = false;
            updateSamplingListener();
        }
    }

    void start(Rect initialSamplingBounds) {
        if (this.mCallback.isSamplingEnabled()) {
            if (initialSamplingBounds != null) {
                this.mSamplingRequestBounds.set(initialSamplingBounds);
            }
            this.mSamplingEnabled = true;
            this.mLastMedianLuma = -1.0f;
            this.mFirstSamplingAfterStart = true;
            updateSamplingListener();
        }
    }

    void stop() {
        this.mSamplingEnabled = false;
        updateSamplingListener();
    }

    void stopAndDestroy() {
        stop();
        this.mSamplingListener.destroy();
        this.mIsDestroyed = true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        updateSamplingListener();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        stopAndDestroy();
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        updateSamplingRect();
    }

    private void updateSamplingListener() {
        if (this.mSamplingEnabled && !this.mSamplingRequestBounds.isEmpty() && this.mWindowVisible && (this.mSampledView.isAttachedToWindow() || this.mFirstSamplingAfterStart)) {
            ViewRootImpl viewRootImpl = this.mSampledView.getViewRootImpl();
            SurfaceControl surfaceControl = null;
            SurfaceControl surfaceControl2 = viewRootImpl != null ? viewRootImpl.getSurfaceControl() : null;
            if (surfaceControl2 != null && surfaceControl2.isValid()) {
                surfaceControl = surfaceControl2;
            } else if (!this.mWaitingOnDraw) {
                this.mWaitingOnDraw = true;
                if (this.mHandler.hasCallbacks(this.mRemoveDrawRunnable)) {
                    this.mHandler.removeCallbacks(this.mRemoveDrawRunnable);
                } else {
                    this.mSampledView.getViewTreeObserver().addOnDrawListener(this.mUpdateOnDraw);
                }
            }
            if (!this.mSamplingRequestBounds.equals(this.mRegisteredSamplingBounds) || this.mRegisteredStopLayer != surfaceControl) {
                unregisterSamplingListener();
                this.mSamplingListenerRegistered = true;
                CompositionSamplingListener.register(this.mSamplingListener, 0, surfaceControl, this.mSamplingRequestBounds);
                this.mRegisteredSamplingBounds.set(this.mSamplingRequestBounds);
                this.mRegisteredStopLayer = surfaceControl;
            }
            this.mFirstSamplingAfterStart = false;
            return;
        }
        unregisterSamplingListener();
    }

    private void unregisterSamplingListener() {
        if (this.mSamplingListenerRegistered) {
            this.mSamplingListenerRegistered = false;
            this.mRegisteredStopLayer = null;
            this.mRegisteredSamplingBounds.setEmpty();
            CompositionSamplingListener.unregister(this.mSamplingListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMediaLuma(float medianLuma) {
        this.mCurrentMedianLuma = medianLuma;
        if (Math.abs(medianLuma - this.mLastMedianLuma) > this.mLuminanceChangeThreshold) {
            this.mCallback.onRegionDarknessChanged(medianLuma < this.mLuminanceThreshold);
            this.mLastMedianLuma = medianLuma;
        }
    }

    public void updateSamplingRect() {
        Rect sampledRegion = this.mCallback.getSampledRegion(this.mSampledView);
        if (this.mSamplingRequestBounds.equals(sampledRegion)) {
            return;
        }
        this.mSamplingRequestBounds.set(sampledRegion);
        updateSamplingListener();
    }

    void setWindowVisible(boolean visible) {
        this.mWindowVisible = visible;
        updateSamplingListener();
    }

    void dump(PrintWriter pw) {
        pw.println("RegionSamplingHelper:");
        pw.println("  sampleView isAttached: " + this.mSampledView.isAttachedToWindow());
        pw.println("  sampleView isScValid: " + (this.mSampledView.isAttachedToWindow() ? Boolean.valueOf(this.mSampledView.getViewRootImpl().getSurfaceControl().isValid()) : "notAttached"));
        pw.println("  mSamplingEnabled: " + this.mSamplingEnabled);
        pw.println("  mSamplingListenerRegistered: " + this.mSamplingListenerRegistered);
        pw.println("  mSamplingRequestBounds: " + this.mSamplingRequestBounds);
        pw.println("  mRegisteredSamplingBounds: " + this.mRegisteredSamplingBounds);
        pw.println("  mLastMedianLuma: " + this.mLastMedianLuma);
        pw.println("  mCurrentMedianLuma: " + this.mCurrentMedianLuma);
        pw.println("  mWindowVisible: " + this.mWindowVisible);
        pw.println("  mWaitingOnDraw: " + this.mWaitingOnDraw);
        pw.println("  mRegisteredStopLayer: " + this.mRegisteredStopLayer);
        pw.println("  mIsDestroyed: " + this.mIsDestroyed);
    }
}
