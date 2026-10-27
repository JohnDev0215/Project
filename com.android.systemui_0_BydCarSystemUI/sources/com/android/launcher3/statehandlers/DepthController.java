package com.android.launcher3.statehandlers;

import android.util.FloatProperty;
import android.view.View;
import android.view.ViewTreeObserver;
import com.android.systemui.shared.system.RemoteAnimationTargetCompat;
import com.android.systemui.shared.system.SurfaceControlCompat;

/* JADX INFO: loaded from: classes.dex */
public class DepthController {
    public static final FloatProperty<DepthController> DEPTH = new FloatProperty<DepthController>("depth") { // from class: com.android.launcher3.statehandlers.DepthController.1
        @Override // android.util.FloatProperty
        public void setValue(DepthController depthController, float depth) {
            depthController.setDepth(depth);
        }

        @Override // android.util.Property
        public Float get(DepthController depthController) {
            return Float.valueOf(depthController.mDepth);
        }
    };
    private float mDepth;
    private int mMaxBlurRadius;
    private View.OnAttachStateChangeListener mOnAttachListener;
    private SurfaceControlCompat mSurface;
    private final ViewTreeObserver.OnDrawListener mOnDrawListener = new ViewTreeObserver.OnDrawListener() { // from class: com.android.launcher3.statehandlers.DepthController.2
        @Override // android.view.ViewTreeObserver.OnDrawListener
        public void onDraw() {
        }
    };
    private boolean mIgnoreStateChangesDuringMultiWindowAnimation = false;

    private void ensureDependencies() {
    }

    public void setActivityStarted(boolean isStarted) {
    }

    public static float boundToRange(float value, float lowerBound, float upperBound) {
        return Math.max(lowerBound, Math.min(value, upperBound));
    }

    public static int boundToRange(int value, int lowerBound, int upperBound) {
        return Math.max(lowerBound, Math.min(value, upperBound));
    }

    public static class ClampedDepthProperty extends FloatProperty<DepthController> {
        private final float mMaxValue;
        private final float mMinValue;

        public ClampedDepthProperty(float minValue, float maxValue) {
            super("depthClamped");
            this.mMinValue = minValue;
            this.mMaxValue = maxValue;
        }

        @Override // android.util.FloatProperty
        public void setValue(DepthController depthController, float depth) {
            depthController.setDepth(DepthController.boundToRange(depth, this.mMinValue, this.mMaxValue));
        }

        @Override // android.util.Property
        public Float get(DepthController depthController) {
            return Float.valueOf(depthController.mDepth);
        }
    }

    public void setSurfaceToApp(RemoteAnimationTargetCompat target) {
        if (target != null) {
            setSurface(target.leash);
        } else {
            setActivityStarted(false);
        }
    }

    private void setSurface(SurfaceControlCompat surface) {
        if (this.mSurface != surface) {
            this.mSurface = surface;
            if (surface != null) {
                setDepth(this.mDepth);
            } else {
                setDepth(0.0f);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDepth(float depth) {
        float fBoundToRange = ((int) (boundToRange(depth, 0.0f, 1.0f) * 256.0f)) / 256.0f;
        if (Float.compare(this.mDepth, fBoundToRange) == 0) {
            return;
        }
        this.mDepth = fBoundToRange;
        ensureDependencies();
    }
}
