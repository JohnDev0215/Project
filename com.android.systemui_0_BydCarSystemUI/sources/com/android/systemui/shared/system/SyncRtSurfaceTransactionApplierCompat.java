package com.android.systemui.shared.system;

import android.graphics.HardwareRenderer;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.os.Trace;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewRootImpl;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class SyncRtSurfaceTransactionApplierCompat {
    public static final int FLAG_ALL = -1;
    public static final int FLAG_ALPHA = 1;
    public static final int FLAG_BACKGROUND_BLUR_RADIUS = 32;
    public static final int FLAG_CORNER_RADIUS = 16;
    public static final int FLAG_LAYER = 8;
    public static final int FLAG_MATRIX = 2;
    public static final int FLAG_RELATIVE_LAYER = 128;
    public static final int FLAG_VISIBILITY = 64;
    public static final int FLAG_WINDOW_CROP = 4;
    private static final int MSG_UPDATE_SEQUENCE_NUMBER = 0;
    private Runnable mAfterApplyCallback;
    private final Handler mApplyHandler;
    private final SurfaceControl mBarrierSurfaceControl;
    private final ViewRootImpl mTargetViewRootImpl;
    private int mSequenceNumber = 0;
    private int mPendingSequenceNumber = 0;

    public SyncRtSurfaceTransactionApplierCompat(View targetView) {
        ViewRootImpl viewRootImpl = targetView != null ? targetView.getViewRootImpl() : null;
        this.mTargetViewRootImpl = viewRootImpl;
        this.mBarrierSurfaceControl = viewRootImpl != null ? viewRootImpl.getRenderSurfaceControl() : null;
        this.mApplyHandler = new Handler(new Handler.Callback() { // from class: com.android.systemui.shared.system.SyncRtSurfaceTransactionApplierCompat.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message msg) {
                if (msg.what != 0) {
                    return false;
                }
                SyncRtSurfaceTransactionApplierCompat.this.onApplyMessage(msg.arg1);
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onApplyMessage(int seqNo) {
        Runnable runnable;
        this.mSequenceNumber = seqNo;
        if (seqNo != this.mPendingSequenceNumber || (runnable = this.mAfterApplyCallback) == null) {
            return;
        }
        this.mAfterApplyCallback = null;
        runnable.run();
    }

    public void scheduleApply(final SurfaceParams... params) {
        ViewRootImpl viewRootImpl = this.mTargetViewRootImpl;
        if (viewRootImpl == null || viewRootImpl.getView() == null) {
            return;
        }
        final int i = this.mPendingSequenceNumber + 1;
        this.mPendingSequenceNumber = i;
        this.mTargetViewRootImpl.registerRtFrameCallback(new HardwareRenderer.FrameDrawingCallback() { // from class: com.android.systemui.shared.system.SyncRtSurfaceTransactionApplierCompat.2
            public void onFrameDraw(long frame) {
                if (SyncRtSurfaceTransactionApplierCompat.this.mBarrierSurfaceControl == null || !SyncRtSurfaceTransactionApplierCompat.this.mBarrierSurfaceControl.isValid()) {
                    Message.obtain(SyncRtSurfaceTransactionApplierCompat.this.mApplyHandler, 0, i, 0).sendToTarget();
                    return;
                }
                Trace.traceBegin(8L, "Sync transaction frameNumber=" + frame);
                SurfaceControl.Transaction transaction = new SurfaceControl.Transaction();
                for (int length = params.length - 1; length >= 0; length--) {
                    SurfaceParams surfaceParams = params[length];
                    transaction.deferTransactionUntil(surfaceParams.surface, SyncRtSurfaceTransactionApplierCompat.this.mBarrierSurfaceControl, frame);
                    surfaceParams.applyTo(transaction);
                }
                transaction.apply();
                Trace.traceEnd(8L);
                Message.obtain(SyncRtSurfaceTransactionApplierCompat.this.mApplyHandler, 0, i, 0).sendToTarget();
            }
        });
        this.mTargetViewRootImpl.getView().invalidate();
    }

    public void addAfterApplyCallback(final Runnable afterApplyCallback) {
        if (this.mSequenceNumber == this.mPendingSequenceNumber) {
            afterApplyCallback.run();
            return;
        }
        final Runnable runnable = this.mAfterApplyCallback;
        if (runnable == null) {
            this.mAfterApplyCallback = afterApplyCallback;
        } else {
            this.mAfterApplyCallback = new Runnable() { // from class: com.android.systemui.shared.system.SyncRtSurfaceTransactionApplierCompat.3
                @Override // java.lang.Runnable
                public void run() {
                    afterApplyCallback.run();
                    runnable.run();
                }
            };
        }
    }

    public static void applyParams(TransactionCompat t, SurfaceParams params) {
        params.applyTo(t.mTransaction);
    }

    public static void create(final View targetView, final Consumer<SyncRtSurfaceTransactionApplierCompat> callback) {
        if (targetView == null) {
            callback.accept(null);
        } else if (targetView.getViewRootImpl() != null) {
            callback.accept(new SyncRtSurfaceTransactionApplierCompat(targetView));
        } else {
            targetView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.android.systemui.shared.system.SyncRtSurfaceTransactionApplierCompat.4
                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View v) {
                }

                @Override // android.view.View.OnAttachStateChangeListener
                public void onViewAttachedToWindow(View v) {
                    targetView.removeOnAttachStateChangeListener(this);
                    callback.accept(new SyncRtSurfaceTransactionApplierCompat(targetView));
                }
            });
        }
    }

    public static class SurfaceParams {
        public final float alpha;
        public final int backgroundBlurRadius;
        public final float cornerRadius;
        private final int flags;
        public final int layer;
        private final float[] mTmpValues;
        public final Matrix matrix;
        public final int relativeLayer;
        public final SurfaceControl relativeTo;
        public final SurfaceControl surface;
        public final boolean visible;
        public final Rect windowCrop;

        public static class Builder {
            float alpha;
            int backgroundBlurRadius;
            float cornerRadius;
            int flags;
            int layer;
            Matrix matrix;
            int relativeLayer;
            SurfaceControl relativeTo;
            final SurfaceControl surface;
            boolean visible;
            Rect windowCrop;

            public Builder(SurfaceControlCompat surface) {
                this(surface.mSurfaceControl);
            }

            public Builder(SurfaceControl surface) {
                this.surface = surface;
            }

            public Builder withAlpha(float alpha) {
                this.alpha = alpha;
                this.flags |= 1;
                return this;
            }

            public Builder withMatrix(Matrix matrix) {
                this.matrix = matrix;
                this.flags |= 2;
                return this;
            }

            public Builder withWindowCrop(Rect windowCrop) {
                this.windowCrop = windowCrop;
                this.flags |= 4;
                return this;
            }

            public Builder withLayer(int layer) {
                this.layer = layer;
                this.flags |= 8;
                return this;
            }

            public Builder withRelativeLayerTo(SurfaceControl relativeTo, int relativeLayer) {
                this.relativeTo = relativeTo;
                this.relativeLayer = relativeLayer;
                this.flags |= 128;
                return this;
            }

            public Builder withCornerRadius(float radius) {
                this.cornerRadius = radius;
                this.flags |= 16;
                return this;
            }

            public Builder withBackgroundBlur(int radius) {
                this.backgroundBlurRadius = radius;
                this.flags |= 32;
                return this;
            }

            public Builder withVisibility(boolean visible) {
                this.visible = visible;
                this.flags |= 64;
                return this;
            }

            public SurfaceParams build() {
                return new SurfaceParams(this.surface, this.flags, this.alpha, this.matrix, this.windowCrop, this.layer, this.relativeTo, this.relativeLayer, this.cornerRadius, this.backgroundBlurRadius, this.visible);
            }
        }

        public SurfaceParams(SurfaceControlCompat surface, float alpha, Matrix matrix, Rect windowCrop, int layer, SurfaceControl relativeTo, int relativeLayer, float cornerRadius) {
            this(surface.mSurfaceControl, -97, alpha, matrix, windowCrop, layer, relativeTo, relativeLayer, cornerRadius, 0, true);
        }

        private SurfaceParams(SurfaceControl surface, int flags, float alpha, Matrix matrix, Rect windowCrop, int layer, SurfaceControl relativeTo, int relativeLayer, float cornerRadius, int backgroundBlurRadius, boolean visible) {
            this.mTmpValues = new float[9];
            this.flags = flags;
            this.surface = surface;
            this.alpha = alpha;
            this.matrix = new Matrix(matrix);
            this.windowCrop = windowCrop != null ? new Rect(windowCrop) : null;
            this.layer = layer;
            this.relativeTo = relativeTo;
            this.relativeLayer = relativeLayer;
            this.cornerRadius = cornerRadius;
            this.backgroundBlurRadius = backgroundBlurRadius;
            this.visible = visible;
        }

        public void applyTo(SurfaceControl.Transaction t) {
            if ((this.flags & 2) != 0) {
                t.setMatrix(this.surface, this.matrix, this.mTmpValues);
            }
            if ((this.flags & 4) != 0) {
                t.setWindowCrop(this.surface, this.windowCrop);
            }
            if ((this.flags & 1) != 0) {
                t.setAlpha(this.surface, this.alpha);
            }
            if ((this.flags & 8) != 0) {
                t.setLayer(this.surface, this.layer);
            }
            if ((this.flags & 16) != 0) {
                t.setCornerRadius(this.surface, this.cornerRadius);
            }
            if ((this.flags & 32) != 0) {
                t.setBackgroundBlurRadius(this.surface, this.backgroundBlurRadius);
            }
            if ((this.flags & 64) != 0) {
                if (this.visible) {
                    t.show(this.surface);
                } else {
                    t.hide(this.surface);
                }
            }
            if ((this.flags & 128) != 0) {
                t.setRelativeLayer(this.surface, this.relativeTo, this.relativeLayer);
            }
        }
    }
}
