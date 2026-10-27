package com.android.internal.view;

import android.graphics.HardwareRenderer;
import android.graphics.Matrix;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.RenderNode;
import android.os.Handler;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.ScrollCaptureCallback;
import android.view.ScrollCaptureSession;
import android.view.Surface;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes4.dex */
public class ScrollCaptureViewSupport<V extends View> implements ScrollCaptureCallback {
    private boolean mEnded;
    private ViewRenderer mRenderer = new ViewRenderer();
    private boolean mStarted;
    private Handler mUiHandler;
    private final ScrollCaptureViewHelper<V> mViewHelper;
    private final WeakReference<V> mWeakView;

    static <V extends View> ScrollCaptureCallback createCallback(V v, ScrollCaptureViewHelper<V> scrollCaptureViewHelper) {
        return new ScrollCaptureViewSupport(v, scrollCaptureViewHelper);
    }

    ScrollCaptureViewSupport(V v, ScrollCaptureViewHelper<V> scrollCaptureViewHelper) {
        this.mWeakView = new WeakReference<>(v);
        this.mUiHandler = v.getHandler();
        this.mViewHelper = scrollCaptureViewHelper;
    }

    @Override // android.view.ScrollCaptureCallback
    public final void onScrollCaptureSearch(Consumer<Rect> consumer) {
        V v = this.mWeakView.get();
        this.mStarted = false;
        this.mEnded = false;
        if (v != null && v.isVisibleToUser() && this.mViewHelper.onAcceptSession(v)) {
            consumer.accept(this.mViewHelper.onComputeScrollBounds(v));
        } else {
            consumer.accept(null);
        }
    }

    @Override // android.view.ScrollCaptureCallback
    public final void onScrollCaptureStart(ScrollCaptureSession scrollCaptureSession, Runnable runnable) {
        V v = this.mWeakView.get();
        this.mEnded = false;
        this.mStarted = true;
        if (v != null && v.isVisibleToUser()) {
            this.mRenderer.setSurface(scrollCaptureSession.getSurface());
            this.mViewHelper.onPrepareForStart(v, scrollCaptureSession.getScrollBounds());
        }
        runnable.run();
    }

    @Override // android.view.ScrollCaptureCallback
    public final void onScrollCaptureImageRequest(final ScrollCaptureSession scrollCaptureSession, Rect rect) {
        V v = this.mWeakView.get();
        if (v == null || !v.isVisibleToUser()) {
            scrollCaptureSession.notifyBufferSent(0L, null);
        } else {
            final Rect rectOnScrollRequested = this.mViewHelper.onScrollRequested(v, scrollCaptureSession.getScrollBounds(), rect);
            this.mRenderer.renderFrame(v, rectOnScrollRequested, this.mUiHandler, new Runnable() { // from class: com.android.internal.view.-$$Lambda$ScrollCaptureViewSupport$WhYdis6PgpbNdc_fEsMsJ4b7okA
                @Override // java.lang.Runnable
                public final void run() {
                    scrollCaptureSession.notifyBufferSent(0L, rectOnScrollRequested);
                }
            });
        }
    }

    @Override // android.view.ScrollCaptureCallback
    public final void onScrollCaptureEnd(Runnable runnable) {
        V v = this.mWeakView.get();
        if (this.mStarted && !this.mEnded) {
            this.mViewHelper.onPrepareForEnd(v);
            this.mEnded = true;
            this.mRenderer.trimMemory();
            this.mRenderer.setSurface(null);
        }
        runnable.run();
    }

    static final class ViewRenderer {
        private static final float AMBIENT_SHADOW_ALPHA = 0.039f;
        private static final float LIGHT_RADIUS_DP = 800.0f;
        private static final float LIGHT_Z_DP = 400.0f;
        private static final float SPOT_SHADOW_ALPHA = 0.039f;
        private static final String TAG = "ViewRenderer";
        private RenderNode mRootRenderNode;
        private final RectF mTempRectF = new RectF();
        private final Rect mSourceRect = new Rect();
        private final Rect mTempRect = new Rect();
        private final Matrix mTempMatrix = new Matrix();
        private final int[] mTempLocation = new int[2];
        private long mLastRenderedSourceDrawingId = -1;
        private HardwareRenderer mRenderer = new HardwareRenderer();

        ViewRenderer() {
            RenderNode renderNode = new RenderNode("ScrollCaptureRoot");
            this.mRootRenderNode = renderNode;
            this.mRenderer.setContentRoot(renderNode);
            this.mRenderer.setOpaque(false);
        }

        public void setSurface(Surface surface) {
            this.mRenderer.setSurface(surface);
        }

        private boolean updateForView(View view) {
            if (this.mLastRenderedSourceDrawingId == view.getUniqueDrawingId()) {
                return false;
            }
            this.mLastRenderedSourceDrawingId = view.getUniqueDrawingId();
            return true;
        }

        private void setupLighting(View view) {
            this.mLastRenderedSourceDrawingId = view.getUniqueDrawingId();
            DisplayMetrics displayMetrics = view.getResources().getDisplayMetrics();
            view.getLocationOnScreen(this.mTempLocation);
            this.mRenderer.setLightSourceGeometry((displayMetrics.widthPixels / 2.0f) - this.mTempLocation[0], displayMetrics.heightPixels - this.mTempLocation[1], (int) (displayMetrics.density * 400.0f), (int) (displayMetrics.density * LIGHT_RADIUS_DP));
            this.mRenderer.setLightSourceAlpha(0.039f, 0.039f);
        }

        public void renderFrame(View view, Rect rect, final Handler handler, Runnable runnable) {
            if (updateForView(view)) {
                setupLighting(view);
            }
            buildRootDisplayList(view, rect);
            HardwareRenderer.FrameRenderRequest frameRenderRequestCreateRenderRequest = this.mRenderer.createRenderRequest();
            frameRenderRequestCreateRenderRequest.setVsyncTime(SystemClock.elapsedRealtimeNanos());
            Objects.requireNonNull(handler);
            frameRenderRequestCreateRenderRequest.setFrameCommitCallback(new Executor() { // from class: com.android.internal.view.-$$Lambda$LfzJt661qZfn2w-6SYHFbD3aMy0
                @Override // java.util.concurrent.Executor
                public final void execute(Runnable runnable2) {
                    handler.post(runnable2);
                }
            }, runnable);
            frameRenderRequestCreateRenderRequest.setWaitForPresent(true);
            frameRenderRequestCreateRenderRequest.syncAndDraw();
        }

        public void trimMemory() {
            this.mRenderer.clearContent();
        }

        public void destroy() {
            this.mRenderer.destroy();
        }

        private void transformToRoot(View view, Rect rect, Rect rect2) {
            this.mTempMatrix.reset();
            view.transformMatrixToGlobal(this.mTempMatrix);
            this.mTempRectF.set(rect);
            this.mTempMatrix.mapRect(this.mTempRectF);
            this.mTempRectF.round(rect2);
        }

        private void buildRootDisplayList(View view, Rect rect) {
            View rootView = view.getRootView();
            transformToRoot(view, rect, this.mTempRect);
            this.mRootRenderNode.setPosition(0, 0, this.mTempRect.width(), this.mTempRect.height());
            RecordingCanvas recordingCanvasBeginRecording = this.mRootRenderNode.beginRecording(this.mTempRect.width(), this.mTempRect.height());
            recordingCanvasBeginRecording.translate(-this.mTempRect.left, -this.mTempRect.top);
            recordingCanvasBeginRecording.drawRenderNode(rootView.updateDisplayListIfDirty());
            this.mRootRenderNode.endRecording();
        }
    }
}
