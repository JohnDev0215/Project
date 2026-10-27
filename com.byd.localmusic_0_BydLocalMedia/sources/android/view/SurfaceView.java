package android.view;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.HardwareRenderer;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RenderNode;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.SurfaceControlViewHost;
import android.view.SurfaceHolder;
import android.view.SyncRtSurfaceTransactionApplier;
import android.view.ViewRootImpl;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.IAccessibilityEmbeddedConnection;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes3.dex */
public class SurfaceView extends View implements ViewRootImpl.SurfaceChangedCallback {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_POSITION = false;
    private static final String TAG = "SurfaceView";
    private boolean mAttachedToWindow;
    int mBackgroundColor;
    SurfaceControl mBackgroundControl;
    final ArrayList<SurfaceHolder.Callback> mCallbacks;
    boolean mClipSurfaceToBounds;
    float mCornerRadius;
    SurfaceControl mDeferredDestroySurfaceControl;
    private boolean mDisableBackgroundLayer;
    boolean mDrawFinished;
    private final ViewTreeObserver.OnPreDrawListener mDrawListener;
    boolean mDrawingStopped;
    int mFormat;
    private boolean mGlobalListenersAdded;
    boolean mHaveFrame;
    boolean mIsCreating;
    long mLastLockTime;
    int mLastSurfaceHeight;
    int mLastSurfaceWidth;
    boolean mLastWindowVisibility;
    final int[] mLocation;
    private final float[] mMatrixValues;
    private int mParentSurfaceGenerationId;
    private int mPendingReportDraws;
    private RenderNode.PositionUpdateListener mPositionListener;
    private Rect mRTLastReportedPosition;
    private RemoteAccessibilityEmbeddedConnection mRemoteAccessibilityEmbeddedConnection;
    int mRequestedFormat;
    int mRequestedHeight;
    boolean mRequestedVisible;
    int mRequestedWidth;
    Paint mRoundedViewportPaint;
    private volatile boolean mRtHandlingPositionUpdates;
    private volatile boolean mRtReleaseSurfaces;
    private SurfaceControl.Transaction mRtTransaction;
    private final Matrix mScreenMatrixForEmbeddedHierarchy;
    final Rect mScreenRect;
    private final ViewTreeObserver.OnScrollChangedListener mScrollChangedListener;
    int mSubLayer;
    final Surface mSurface;
    float mSurfaceAlpha;
    SurfaceControl mSurfaceControl;
    final Object mSurfaceControlLock;
    boolean mSurfaceCreated;
    private int mSurfaceFlags;
    final Rect mSurfaceFrame;
    int mSurfaceHeight;
    private final SurfaceHolder mSurfaceHolder;
    final ReentrantLock mSurfaceLock;
    SurfaceControlViewHost.SurfacePackage mSurfacePackage;
    private final SurfaceSession mSurfaceSession;
    int mSurfaceWidth;
    private final Matrix mTmpMatrix;
    final Rect mTmpRect;
    private SurfaceControl.Transaction mTmpTransaction;
    boolean mUseAlpha;
    boolean mViewVisibility;
    boolean mVisible;
    int mWindowSpaceLeft;
    int mWindowSpaceTop;
    boolean mWindowStopped;
    boolean mWindowVisibility;

    protected void applyChildSurfaceTransaction_renderWorker(SurfaceControl.Transaction transaction, Surface surface, long j) {
    }

    public /* synthetic */ boolean lambda$new$0$SurfaceView() {
        this.mHaveFrame = getWidth() > 0 && getHeight() > 0;
        updateSurface();
        return true;
    }

    public SurfaceView(Context context) {
        this(context, null);
    }

    public SurfaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SurfaceView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public SurfaceView(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, attributeSet, i, i2, false);
    }

    public SurfaceView(Context context, AttributeSet attributeSet, int i, int i2, boolean z) {
        super(context, attributeSet, i, i2);
        this.mCallbacks = new ArrayList<>();
        this.mLocation = new int[2];
        this.mSurfaceLock = new ReentrantLock();
        this.mSurface = new Surface();
        this.mDrawingStopped = true;
        this.mDrawFinished = false;
        this.mScreenRect = new Rect();
        this.mSurfaceSession = new SurfaceSession();
        this.mDisableBackgroundLayer = false;
        this.mSurfaceControlLock = new Object();
        this.mTmpRect = new Rect();
        this.mSubLayer = -2;
        this.mIsCreating = false;
        this.mRtHandlingPositionUpdates = false;
        this.mRtReleaseSurfaces = false;
        this.mScrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() { // from class: android.view.-$$Lambda$PYGleuqIeCxjTD1pJqqx1opFv1g
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public final void onScrollChanged() {
                this.f$0.updateSurface();
            }
        };
        this.mDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: android.view.-$$Lambda$SurfaceView$w68OV7dB_zKVNsA-r0IrAUtyWas
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                return this.f$0.lambda$new$0$SurfaceView();
            }
        };
        this.mRequestedVisible = false;
        this.mWindowVisibility = false;
        this.mLastWindowVisibility = false;
        this.mViewVisibility = false;
        this.mWindowStopped = false;
        this.mRequestedWidth = -1;
        this.mRequestedHeight = -1;
        this.mRequestedFormat = 4;
        this.mUseAlpha = false;
        this.mSurfaceAlpha = 1.0f;
        this.mBackgroundColor = -16777216;
        this.mHaveFrame = false;
        this.mSurfaceCreated = false;
        this.mLastLockTime = 0L;
        this.mVisible = false;
        this.mWindowSpaceLeft = -1;
        this.mWindowSpaceTop = -1;
        this.mSurfaceWidth = -1;
        this.mSurfaceHeight = -1;
        this.mFormat = -1;
        this.mSurfaceFrame = new Rect();
        this.mLastSurfaceWidth = -1;
        this.mLastSurfaceHeight = -1;
        this.mSurfaceFlags = 4;
        this.mRtTransaction = new SurfaceControl.Transaction();
        this.mTmpTransaction = new SurfaceControl.Transaction();
        this.mScreenMatrixForEmbeddedHierarchy = new Matrix();
        this.mTmpMatrix = new Matrix();
        this.mMatrixValues = new float[9];
        this.mRTLastReportedPosition = new Rect();
        this.mPositionListener = new RenderNode.PositionUpdateListener() { // from class: android.view.SurfaceView.1
            @Override // android.graphics.RenderNode.PositionUpdateListener
            public void positionChanged(long j, int i3, int i4, int i5, int i6) {
                if (SurfaceView.this.mSurfaceControl == null) {
                    return;
                }
                synchronized (SurfaceView.this.mSurfaceControlLock) {
                    SurfaceView.this.mRtHandlingPositionUpdates = true;
                }
                if (SurfaceView.this.mRTLastReportedPosition.left == i3 && SurfaceView.this.mRTLastReportedPosition.top == i4 && SurfaceView.this.mRTLastReportedPosition.right == i5 && SurfaceView.this.mRTLastReportedPosition.bottom == i6) {
                    return;
                }
                try {
                    SurfaceView.this.mRTLastReportedPosition.set(i3, i4, i5, i6);
                    SurfaceView.this.setParentSpaceRectangle(SurfaceView.this.mRTLastReportedPosition, j);
                } catch (Exception e) {
                    Log.e(SurfaceView.TAG, "Exception from repositionChild", e);
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:27:0x006f A[Catch: all -> 0x00ab, TryCatch #0 {, blocks: (B:22:0x0047, B:24:0x004f, B:25:0x0060, B:27:0x006f, B:28:0x0097, B:29:0x009c), top: B:37:0x0047 }] */
            @Override // android.graphics.RenderNode.PositionUpdateListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void positionLost(long r9) {
                /*
                    r8 = this;
                    android.view.SurfaceView r0 = android.view.SurfaceView.this
                    android.view.ViewRootImpl r0 = r0.getViewRootImpl()
                    r1 = 0
                    if (r0 == 0) goto L11
                    boolean r2 = r0.isDrawingToBLASTTransaction()
                    if (r2 == 0) goto L11
                    r2 = 1
                    goto L12
                L11:
                    r2 = r1
                L12:
                    android.view.SurfaceView r3 = android.view.SurfaceView.this
                    android.graphics.Rect r3 = android.view.SurfaceView.access$100(r3)
                    r3.setEmpty()
                    android.view.SurfaceView r3 = android.view.SurfaceView.this
                    android.view.SurfaceControl r3 = r3.mSurfaceControl
                    if (r3 != 0) goto L22
                    return
                L22:
                    if (r2 == 0) goto L32
                    if (r0 == 0) goto L2b
                    android.view.SurfaceControl$Transaction r3 = r0.getBLASTSyncTransaction()
                    goto L38
                L2b:
                    android.view.SurfaceView r3 = android.view.SurfaceView.this
                    android.view.SurfaceControl$Transaction r3 = android.view.SurfaceView.access$300(r3)
                    goto L38
                L32:
                    android.view.SurfaceView r3 = android.view.SurfaceView.this
                    android.view.SurfaceControl$Transaction r3 = android.view.SurfaceView.access$300(r3)
                L38:
                    android.view.SurfaceView r4 = android.view.SurfaceView.this
                    java.lang.Object r4 = r4.mSurfaceControlLock
                    monitor-enter(r4)
                    r5 = 0
                    int r5 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
                    if (r5 <= 0) goto L60
                    if (r0 == 0) goto L60
                    if (r2 != 0) goto L60
                    android.view.Surface r5 = r0.mSurface     // Catch: java.lang.Throwable -> Lab
                    boolean r5 = r5.isValid()     // Catch: java.lang.Throwable -> Lab
                    if (r5 == 0) goto L60
                    android.view.SurfaceView r5 = android.view.SurfaceView.this     // Catch: java.lang.Throwable -> Lab
                    android.view.SurfaceControl$Transaction r5 = android.view.SurfaceView.access$300(r5)     // Catch: java.lang.Throwable -> Lab
                    android.view.SurfaceView r6 = android.view.SurfaceView.this     // Catch: java.lang.Throwable -> Lab
                    android.view.SurfaceControl r6 = r6.mSurfaceControl     // Catch: java.lang.Throwable -> Lab
                    android.view.SurfaceControl r7 = r0.getRenderSurfaceControl()     // Catch: java.lang.Throwable -> Lab
                    r5.deferTransactionUntil(r6, r7, r9)     // Catch: java.lang.Throwable -> Lab
                L60:
                    android.view.SurfaceView r9 = android.view.SurfaceView.this     // Catch: java.lang.Throwable -> Lab
                    android.view.SurfaceControl r9 = r9.mSurfaceControl     // Catch: java.lang.Throwable -> Lab
                    r3.hide(r9)     // Catch: java.lang.Throwable -> Lab
                    android.view.SurfaceView r9 = android.view.SurfaceView.this     // Catch: java.lang.Throwable -> Lab
                    boolean r9 = android.view.SurfaceView.access$400(r9)     // Catch: java.lang.Throwable -> Lab
                    if (r9 == 0) goto L97
                    android.view.SurfaceView r9 = android.view.SurfaceView.this     // Catch: java.lang.Throwable -> Lab
                    android.view.SurfaceView.access$402(r9, r1)     // Catch: java.lang.Throwable -> Lab
                    android.view.SurfaceView r9 = android.view.SurfaceView.this     // Catch: java.lang.Throwable -> Lab
                    android.view.SurfaceControl$Transaction r9 = android.view.SurfaceView.access$300(r9)     // Catch: java.lang.Throwable -> Lab
                    android.view.SurfaceView r10 = android.view.SurfaceView.this     // Catch: java.lang.Throwable -> Lab
                    android.view.SurfaceControl r10 = r10.mSurfaceControl     // Catch: java.lang.Throwable -> Lab
                    r9.remove(r10)     // Catch: java.lang.Throwable -> Lab
                    android.view.SurfaceView r9 = android.view.SurfaceView.this     // Catch: java.lang.Throwable -> Lab
                    android.view.SurfaceControl$Transaction r9 = android.view.SurfaceView.access$300(r9)     // Catch: java.lang.Throwable -> Lab
                    android.view.SurfaceView r10 = android.view.SurfaceView.this     // Catch: java.lang.Throwable -> Lab
                    android.view.SurfaceControl r10 = r10.mBackgroundControl     // Catch: java.lang.Throwable -> Lab
                    r9.remove(r10)     // Catch: java.lang.Throwable -> Lab
                    android.view.SurfaceView r9 = android.view.SurfaceView.this     // Catch: java.lang.Throwable -> Lab
                    r10 = 0
                    r9.mSurfaceControl = r10     // Catch: java.lang.Throwable -> Lab
                    android.view.SurfaceView r9 = android.view.SurfaceView.this     // Catch: java.lang.Throwable -> Lab
                    r9.mBackgroundControl = r10     // Catch: java.lang.Throwable -> Lab
                L97:
                    android.view.SurfaceView r9 = android.view.SurfaceView.this     // Catch: java.lang.Throwable -> Lab
                    android.view.SurfaceView.access$002(r9, r1)     // Catch: java.lang.Throwable -> Lab
                    monitor-exit(r4)     // Catch: java.lang.Throwable -> Lab
                    if (r2 == 0) goto La1
                    if (r0 != 0) goto Laa
                La1:
                    android.view.SurfaceView r9 = android.view.SurfaceView.this
                    android.view.SurfaceControl$Transaction r9 = android.view.SurfaceView.access$300(r9)
                    r9.apply()
                Laa:
                    return
                Lab:
                    r9 = move-exception
                    monitor-exit(r4)     // Catch: java.lang.Throwable -> Lab
                    throw r9
                */
                throw new UnsupportedOperationException("Method not decompiled: android.view.SurfaceView.AnonymousClass1.positionLost(long):void");
            }
        };
        this.mSurfaceHolder = new AnonymousClass2();
        this.mRenderNode.addPositionUpdateListener(this.mPositionListener);
        setWillNotDraw(true);
        this.mDisableBackgroundLayer = z;
    }

    public SurfaceHolder getHolder() {
        return this.mSurfaceHolder;
    }

    private void updateRequestedVisibility() {
        this.mRequestedVisible = this.mViewVisibility && this.mWindowVisibility && !this.mWindowStopped;
    }

    private void setWindowStopped(boolean z) {
        this.mWindowStopped = z;
        updateRequestedVisibility();
        updateSurface();
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewRootImpl().addSurfaceChangedCallback(this);
        this.mWindowStopped = false;
        this.mViewVisibility = getVisibility() == 0;
        updateRequestedVisibility();
        this.mAttachedToWindow = true;
        this.mParent.requestTransparentRegion(this);
        if (this.mGlobalListenersAdded) {
            return;
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        viewTreeObserver.addOnScrollChangedListener(this.mScrollChangedListener);
        viewTreeObserver.addOnPreDrawListener(this.mDrawListener);
        this.mGlobalListenersAdded = true;
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.mWindowVisibility = i == 0;
        updateRequestedVisibility();
        updateSurface();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        this.mViewVisibility = z;
        boolean z2 = this.mWindowVisibility && z && !this.mWindowStopped;
        if (z2 != this.mRequestedVisible) {
            requestLayout();
        }
        this.mRequestedVisible = z2;
        updateSurface();
    }

    public void setUseAlpha() {
        if (this.mUseAlpha) {
            return;
        }
        this.mUseAlpha = true;
        updateSurfaceAlpha();
    }

    @Override // android.view.View
    public void setAlpha(float f) {
        super.setAlpha(f);
        updateSurfaceAlpha();
    }

    private float getFixedAlpha() {
        float alpha = getAlpha();
        if (!this.mUseAlpha || (this.mSubLayer <= 0 && alpha != 0.0f)) {
            return 1.0f;
        }
        return alpha;
    }

    private void updateSurfaceAlpha() {
        final ViewRootImpl viewRootImpl;
        final Surface surface;
        if (this.mUseAlpha) {
            float alpha = getAlpha();
            if (this.mSubLayer < 0 && 0.0f < alpha && alpha < 1.0f) {
                Log.w(TAG, System.identityHashCode(this) + " updateSurfaceAlpha: translucent color is not supported for a surface placed z-below.");
            }
            if (!this.mHaveFrame || (viewRootImpl = getViewRootImpl()) == null || this.mSurfaceControl == null || (surface = viewRootImpl.mSurface) == null || !surface.isValid()) {
                return;
            }
            final float fixedAlpha = getFixedAlpha();
            if (fixedAlpha != this.mSurfaceAlpha) {
                if (isHardwareAccelerated()) {
                    final boolean zUseBLAST = viewRootImpl.useBLAST();
                    viewRootImpl.registerRtFrameCallback(new HardwareRenderer.FrameDrawingCallback() { // from class: android.view.-$$Lambda$SurfaceView$DKSyxzWn62XKbC15Dh1hMSfxKQg
                        @Override // android.graphics.HardwareRenderer.FrameDrawingCallback
                        public final void onFrameDraw(long j) {
                            this.f$0.lambda$updateSurfaceAlpha$1$SurfaceView(zUseBLAST, viewRootImpl, surface, fixedAlpha, j);
                        }
                    });
                    damageInParent();
                } else {
                    this.mTmpTransaction.setAlpha(this.mSurfaceControl, fixedAlpha).apply();
                }
                this.mSurfaceAlpha = fixedAlpha;
            }
        }
    }

    public /* synthetic */ void lambda$updateSurfaceAlpha$1$SurfaceView(boolean z, ViewRootImpl viewRootImpl, Surface surface, float f, long j) {
        try {
            SurfaceControl.Transaction bLASTSyncTransaction = z ? viewRootImpl.getBLASTSyncTransaction() : new SurfaceControl.Transaction();
            synchronized (this.mSurfaceControlLock) {
                if (surface.isValid()) {
                    if (this.mSurfaceControl == null) {
                        return;
                    }
                    bLASTSyncTransaction.setAlpha(this.mSurfaceControl, f);
                    if (!z) {
                        bLASTSyncTransaction.deferTransactionUntil(this.mSurfaceControl, viewRootImpl.getRenderSurfaceControl(), j);
                    }
                    bLASTSyncTransaction.apply();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, System.identityHashCode(this) + "updateSurfaceAlpha RT: Exception during surface transaction", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performDrawFinished() {
        if (this.mDeferredDestroySurfaceControl != null) {
            synchronized (this.mSurfaceControlLock) {
                this.mTmpTransaction.remove(this.mDeferredDestroySurfaceControl).apply();
                this.mDeferredDestroySurfaceControl = null;
            }
        }
        if (this.mPendingReportDraws > 0) {
            this.mDrawFinished = true;
            if (this.mAttachedToWindow) {
                this.mParent.requestTransparentRegion(this);
                notifyDrawFinished();
                invalidate();
                return;
            }
            return;
        }
        Log.e(TAG, System.identityHashCode(this) + "finished drawing but no pending report draw (extra call to draw completion runnable?)");
    }

    void notifyDrawFinished() {
        ViewRootImpl viewRootImpl = getViewRootImpl();
        if (viewRootImpl != null) {
            viewRootImpl.pendingDrawFinished();
        }
        this.mPendingReportDraws--;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        ViewRootImpl viewRootImpl = getViewRootImpl();
        if (viewRootImpl != null) {
            viewRootImpl.removeSurfaceChangedCallback(this);
        }
        this.mAttachedToWindow = false;
        if (this.mGlobalListenersAdded) {
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            viewTreeObserver.removeOnScrollChangedListener(this.mScrollChangedListener);
            viewTreeObserver.removeOnPreDrawListener(this.mDrawListener);
            this.mGlobalListenersAdded = false;
        }
        while (this.mPendingReportDraws > 0) {
            notifyDrawFinished();
        }
        this.mRequestedVisible = false;
        updateSurface();
        releaseSurfaces();
        SurfaceControlViewHost.SurfacePackage surfacePackage = this.mSurfacePackage;
        if (surfacePackage != null) {
            surfacePackage.release();
            this.mSurfacePackage = null;
        }
        this.mHaveFrame = false;
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int defaultSize;
        int defaultSize2;
        int i3 = this.mRequestedWidth;
        if (i3 >= 0) {
            defaultSize = resolveSizeAndState(i3, i, 0);
        } else {
            defaultSize = getDefaultSize(0, i);
        }
        int i4 = this.mRequestedHeight;
        if (i4 >= 0) {
            defaultSize2 = resolveSizeAndState(i4, i2, 0);
        } else {
            defaultSize2 = getDefaultSize(0, i2);
        }
        setMeasuredDimension(defaultSize, defaultSize2);
    }

    @Override // android.view.View
    protected boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        updateSurface();
        return frame;
    }

    @Override // android.view.View
    public boolean gatherTransparentRegion(Region region) {
        if (isAboveParent() || !this.mDrawFinished) {
            return super.gatherTransparentRegion(region);
        }
        boolean zGatherTransparentRegion = true;
        if ((this.mPrivateFlags & 128) == 0) {
            zGatherTransparentRegion = super.gatherTransparentRegion(region);
        } else if (region != null) {
            int width = getWidth();
            int height = getHeight();
            if (width > 0 && height > 0) {
                getLocationInWindow(this.mLocation);
                int[] iArr = this.mLocation;
                int i = iArr[0];
                int i2 = iArr[1];
                region.op(i, i2, i + width, i2 + height, Region.Op.UNION);
            }
        }
        if (PixelFormat.formatHasAlpha(this.mRequestedFormat)) {
            return false;
        }
        return zGatherTransparentRegion;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        if (this.mDrawFinished && !isAboveParent() && (this.mPrivateFlags & 128) == 0) {
            clearSurfaceViewPort(canvas);
        }
        super.draw(canvas);
    }

    @Override // android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (this.mDrawFinished && !isAboveParent() && (this.mPrivateFlags & 128) == 128) {
            clearSurfaceViewPort(canvas);
        }
        super.dispatchDraw(canvas);
    }

    public void setEnableSurfaceClipping(boolean z) {
        this.mClipSurfaceToBounds = z;
        invalidate();
    }

    @Override // android.view.View
    public void setClipBounds(Rect rect) {
        super.setClipBounds(rect);
        if (this.mClipSurfaceToBounds) {
            if (this.mCornerRadius > 0.0f && !isAboveParent()) {
                invalidate();
            }
            if (this.mSurfaceControl != null) {
                if (this.mClipBounds != null) {
                    this.mTmpRect.set(this.mClipBounds);
                } else {
                    this.mTmpRect.set(0, 0, this.mSurfaceWidth, this.mSurfaceHeight);
                }
                new SyncRtSurfaceTransactionApplier(this).scheduleApply(new SyncRtSurfaceTransactionApplier.SurfaceParams.Builder(this.mSurfaceControl).withWindowCrop(this.mTmpRect).build());
            }
        }
    }

    private void clearSurfaceViewPort(Canvas canvas) {
        if (this.mCornerRadius > 0.0f) {
            canvas.getClipBounds(this.mTmpRect);
            if (this.mClipSurfaceToBounds && this.mClipBounds != null) {
                this.mTmpRect.intersect(this.mClipBounds);
            }
            float f = this.mTmpRect.left;
            float f2 = this.mTmpRect.top;
            float f3 = this.mTmpRect.right;
            float f4 = this.mTmpRect.bottom;
            float f5 = this.mCornerRadius;
            canvas.drawRoundRect(f, f2, f3, f4, f5, f5, this.mRoundedViewportPaint);
            return;
        }
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
    }

    public void setCornerRadius(float f) {
        this.mCornerRadius = f;
        if (f > 0.0f && this.mRoundedViewportPaint == null) {
            Paint paint = new Paint(1);
            this.mRoundedViewportPaint = paint;
            paint.setBlendMode(BlendMode.CLEAR);
            this.mRoundedViewportPaint.setColor(0);
        }
        invalidate();
    }

    public float getCornerRadius() {
        return this.mCornerRadius;
    }

    public void setZOrderMediaOverlay(boolean z) {
        this.mSubLayer = z ? -1 : -2;
    }

    public void setZOrderOnTop(boolean z) {
        setZOrderedOnTop(z, getContext().getApplicationInfo().targetSdkVersion > 29);
    }

    public boolean isZOrderedOnTop() {
        return this.mSubLayer > 0;
    }

    public boolean setZOrderedOnTop(boolean z, boolean z2) {
        final ViewRootImpl viewRootImpl;
        final Surface surface;
        int i = z ? 1 : -2;
        if (this.mSubLayer == i) {
            return false;
        }
        this.mSubLayer = i;
        if (!z2) {
            return false;
        }
        if (this.mSurfaceControl != null && (viewRootImpl = getViewRootImpl()) != null && (surface = viewRootImpl.mSurface) != null && surface.isValid()) {
            final boolean zUseBLAST = viewRootImpl.useBLAST();
            viewRootImpl.registerRtFrameCallback(new HardwareRenderer.FrameDrawingCallback() { // from class: android.view.-$$Lambda$SurfaceView$LV-kXr5_jITlrRhkV6FsfMSbnO8
                @Override // android.graphics.HardwareRenderer.FrameDrawingCallback
                public final void onFrameDraw(long j) {
                    this.f$0.lambda$setZOrderedOnTop$2$SurfaceView(zUseBLAST, viewRootImpl, surface, j);
                }
            });
            invalidate();
        }
        return true;
    }

    public /* synthetic */ void lambda$setZOrderedOnTop$2$SurfaceView(boolean z, ViewRootImpl viewRootImpl, Surface surface, long j) {
        SurfaceControl.Transaction transaction;
        try {
            if (z) {
                transaction = viewRootImpl.getBLASTSyncTransaction();
            } else {
                transaction = new SurfaceControl.Transaction();
            }
            synchronized (this.mSurfaceControlLock) {
                if (surface.isValid() && this.mSurfaceControl != null) {
                    updateRelativeZ(transaction);
                    if (!z) {
                        transaction.deferTransactionUntil(this.mSurfaceControl, viewRootImpl.getRenderSurfaceControl(), j);
                    }
                    transaction.apply();
                }
            }
        } catch (Exception e) {
            Log.e(TAG, System.identityHashCode(this) + "setZOrderOnTop RT: Exception during surface transaction", e);
        }
    }

    public void setSecure(boolean z) {
        if (z) {
            this.mSurfaceFlags |= 128;
        } else {
            this.mSurfaceFlags &= -129;
        }
    }

    private void updateOpaqueFlag() {
        if (!PixelFormat.formatHasAlpha(this.mRequestedFormat)) {
            this.mSurfaceFlags |= 1024;
        } else {
            this.mSurfaceFlags &= -1025;
        }
    }

    private void updateBackgroundVisibility(SurfaceControl.Transaction transaction) {
        SurfaceControl surfaceControl = this.mBackgroundControl;
        if (surfaceControl == null) {
            return;
        }
        if (this.mSubLayer < 0 && (this.mSurfaceFlags & 1024) != 0 && !this.mDisableBackgroundLayer) {
            transaction.show(surfaceControl);
        } else {
            transaction.hide(this.mBackgroundControl);
        }
    }

    private SurfaceControl.Transaction updateBackgroundColor(SurfaceControl.Transaction transaction) {
        transaction.setColor(this.mBackgroundControl, new float[]{Color.red(this.mBackgroundColor) / 255.0f, Color.green(this.mBackgroundColor) / 255.0f, Color.blue(this.mBackgroundColor) / 255.0f});
        return transaction;
    }

    private void releaseSurfaces() {
        this.mSurfaceAlpha = 1.0f;
        synchronized (this.mSurfaceControlLock) {
            this.mSurface.release();
            if (this.mRtHandlingPositionUpdates) {
                this.mRtReleaseSurfaces = true;
                return;
            }
            if (this.mSurfaceControl != null) {
                this.mTmpTransaction.remove(this.mSurfaceControl);
                this.mSurfaceControl = null;
            }
            if (this.mBackgroundControl != null) {
                this.mTmpTransaction.remove(this.mBackgroundControl);
                this.mBackgroundControl = null;
            }
            this.mTmpTransaction.apply();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:157:0x02fd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void updateSurface() {
        /*
            Method dump skipped, instruction units count: 972
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.SurfaceView.updateSurface():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDrawFinished() {
        runOnUiThread(new Runnable() { // from class: android.view.-$$Lambda$SurfaceView$TWz4D2u33ZlAmRtgKzbqqDue3iM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.performDrawFinished();
            }
        });
    }

    protected void onSetSurfacePositionAndScaleRT(SurfaceControl.Transaction transaction, SurfaceControl surfaceControl, int i, int i2, float f, float f2) {
        transaction.setPosition(surfaceControl, i, i2);
        transaction.setMatrix(surfaceControl, f, 0.0f, 0.0f, f2);
    }

    public void requestUpdateSurfacePositionAndScale() {
        SurfaceControl surfaceControl = this.mSurfaceControl;
        if (surfaceControl == null) {
            return;
        }
        onSetSurfacePositionAndScaleRT(this.mTmpTransaction, surfaceControl, this.mScreenRect.left, this.mScreenRect.top, this.mScreenRect.width() / this.mSurfaceWidth, this.mScreenRect.height() / this.mSurfaceHeight);
        this.mTmpTransaction.apply();
    }

    private void applySurfaceTransforms(SurfaceControl surfaceControl, SurfaceControl.Transaction transaction, Rect rect, long j) {
        ViewRootImpl viewRootImpl = getViewRootImpl();
        if (j > 0 && viewRootImpl != null && !viewRootImpl.isDrawingToBLASTTransaction()) {
            transaction.deferTransactionUntil(surfaceControl, viewRootImpl.getRenderSurfaceControl(), j);
        }
        onSetSurfacePositionAndScaleRT(transaction, surfaceControl, rect.left, rect.top, rect.width() / this.mSurfaceWidth, rect.height() / this.mSurfaceHeight);
        if (this.mViewVisibility) {
            transaction.show(surfaceControl);
        }
    }

    public Rect getSurfaceRenderPosition() {
        return this.mRTLastReportedPosition;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setParentSpaceRectangle(Rect rect, long j) {
        SurfaceControl.Transaction bLASTSyncTransaction;
        ViewRootImpl viewRootImpl = getViewRootImpl();
        boolean zIsDrawingToBLASTTransaction = viewRootImpl.isDrawingToBLASTTransaction();
        if (zIsDrawingToBLASTTransaction) {
            bLASTSyncTransaction = viewRootImpl.getBLASTSyncTransaction();
        } else {
            bLASTSyncTransaction = this.mRtTransaction;
        }
        applySurfaceTransforms(this.mSurfaceControl, bLASTSyncTransaction, rect, j);
        applyChildSurfaceTransaction_renderWorker(bLASTSyncTransaction, viewRootImpl.mSurface, j);
        if (zIsDrawingToBLASTTransaction) {
            return;
        }
        bLASTSyncTransaction.apply();
    }

    private SurfaceHolder.Callback[] getSurfaceCallbacks() {
        SurfaceHolder.Callback[] callbackArr;
        synchronized (this.mCallbacks) {
            callbackArr = new SurfaceHolder.Callback[this.mCallbacks.size()];
            this.mCallbacks.toArray(callbackArr);
        }
        return callbackArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runOnUiThread(Runnable runnable) {
        Handler handler = getHandler();
        if (handler != null && handler.getLooper() != Looper.myLooper()) {
            handler.post(runnable);
        } else {
            runnable.run();
        }
    }

    public boolean isFixedSize() {
        return (this.mRequestedWidth == -1 && this.mRequestedHeight == -1) ? false : true;
    }

    private boolean isAboveParent() {
        return this.mSubLayer >= 0;
    }

    public void setResizeBackgroundColor(int i) {
        if (this.mBackgroundControl == null) {
            return;
        }
        this.mBackgroundColor = i;
        updateBackgroundColor(this.mTmpTransaction).apply();
    }

    /* JADX INFO: renamed from: android.view.SurfaceView$2, reason: invalid class name */
    class AnonymousClass2 implements SurfaceHolder {
        private static final String LOG_TAG = "SurfaceHolder";

        @Override // android.view.SurfaceHolder
        @Deprecated
        public void setType(int i) {
        }

        AnonymousClass2() {
        }

        @Override // android.view.SurfaceHolder
        public boolean isCreating() {
            return SurfaceView.this.mIsCreating;
        }

        @Override // android.view.SurfaceHolder
        public void addCallback(SurfaceHolder.Callback callback) {
            synchronized (SurfaceView.this.mCallbacks) {
                if (!SurfaceView.this.mCallbacks.contains(callback)) {
                    SurfaceView.this.mCallbacks.add(callback);
                }
            }
        }

        @Override // android.view.SurfaceHolder
        public void removeCallback(SurfaceHolder.Callback callback) {
            synchronized (SurfaceView.this.mCallbacks) {
                SurfaceView.this.mCallbacks.remove(callback);
            }
        }

        @Override // android.view.SurfaceHolder
        public void setFixedSize(int i, int i2) {
            if (SurfaceView.this.mRequestedWidth == i && SurfaceView.this.mRequestedHeight == i2) {
                return;
            }
            SurfaceView.this.mRequestedWidth = i;
            SurfaceView.this.mRequestedHeight = i2;
            SurfaceView.this.requestLayout();
        }

        @Override // android.view.SurfaceHolder
        public void setSizeFromLayout() {
            if (SurfaceView.this.mRequestedWidth == -1 && SurfaceView.this.mRequestedHeight == -1) {
                return;
            }
            SurfaceView surfaceView = SurfaceView.this;
            surfaceView.mRequestedHeight = -1;
            surfaceView.mRequestedWidth = -1;
            SurfaceView.this.requestLayout();
        }

        @Override // android.view.SurfaceHolder
        public void setFormat(int i) {
            if (i == -1) {
                i = 4;
            }
            SurfaceView.this.mRequestedFormat = i;
            if (SurfaceView.this.mSurfaceControl != null) {
                SurfaceView.this.updateSurface();
            }
        }

        public /* synthetic */ void lambda$setKeepScreenOn$0$SurfaceView$2(boolean z) {
            SurfaceView.this.setKeepScreenOn(z);
        }

        @Override // android.view.SurfaceHolder
        public void setKeepScreenOn(final boolean z) {
            SurfaceView.this.runOnUiThread(new Runnable() { // from class: android.view.-$$Lambda$SurfaceView$2$yPLKBEhjLeg2pTjLhVjBxCxl3rE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$setKeepScreenOn$0$SurfaceView$2(z);
                }
            });
        }

        @Override // android.view.SurfaceHolder
        public Canvas lockCanvas() {
            return internalLockCanvas(null, false);
        }

        @Override // android.view.SurfaceHolder
        public Canvas lockCanvas(Rect rect) {
            return internalLockCanvas(rect, false);
        }

        @Override // android.view.SurfaceHolder
        public Canvas lockHardwareCanvas() {
            return internalLockCanvas(null, true);
        }

        private Canvas internalLockCanvas(Rect rect, boolean z) {
            Canvas canvasLockCanvas;
            SurfaceView.this.mSurfaceLock.lock();
            if (SurfaceView.this.mDrawingStopped || SurfaceView.this.mSurfaceControl == null) {
                canvasLockCanvas = null;
            } else {
                try {
                    if (z) {
                        canvasLockCanvas = SurfaceView.this.mSurface.lockHardwareCanvas();
                    } else {
                        canvasLockCanvas = SurfaceView.this.mSurface.lockCanvas(rect);
                    }
                } catch (Exception e) {
                    Log.e(LOG_TAG, "Exception locking surface", e);
                    canvasLockCanvas = null;
                }
            }
            if (canvasLockCanvas != null) {
                SurfaceView.this.mLastLockTime = SystemClock.uptimeMillis();
                return canvasLockCanvas;
            }
            long jUptimeMillis = SystemClock.uptimeMillis();
            long j = SurfaceView.this.mLastLockTime + 100;
            if (j > jUptimeMillis) {
                try {
                    Thread.sleep(j - jUptimeMillis);
                } catch (InterruptedException unused) {
                }
                jUptimeMillis = SystemClock.uptimeMillis();
            }
            SurfaceView.this.mLastLockTime = jUptimeMillis;
            SurfaceView.this.mSurfaceLock.unlock();
            return null;
        }

        @Override // android.view.SurfaceHolder
        public void unlockCanvasAndPost(Canvas canvas) {
            SurfaceView.this.mSurface.unlockCanvasAndPost(canvas);
            SurfaceView.this.mSurfaceLock.unlock();
        }

        @Override // android.view.SurfaceHolder
        public Surface getSurface() {
            return SurfaceView.this.mSurface;
        }

        @Override // android.view.SurfaceHolder
        public Rect getSurfaceFrame() {
            return SurfaceView.this.mSurfaceFrame;
        }
    }

    public SurfaceControl getSurfaceControl() {
        return this.mSurfaceControl;
    }

    public IBinder getHostToken() {
        ViewRootImpl viewRootImpl = getViewRootImpl();
        if (viewRootImpl == null) {
            return null;
        }
        return viewRootImpl.getInputToken();
    }

    @Override // android.view.ViewRootImpl.SurfaceChangedCallback
    public void surfaceCreated(SurfaceControl.Transaction transaction) {
        setWindowStopped(false);
    }

    @Override // android.view.ViewRootImpl.SurfaceChangedCallback
    public void surfaceDestroyed() {
        setWindowStopped(true);
        setRemoteAccessibilityEmbeddedConnection(null, null);
    }

    @Override // android.view.ViewRootImpl.SurfaceChangedCallback
    public void surfaceReplaced(SurfaceControl.Transaction transaction) {
        if (this.mSurfaceControl == null || this.mBackgroundControl == null) {
            return;
        }
        updateRelativeZ(transaction);
    }

    private void updateRelativeZ(SurfaceControl.Transaction transaction) {
        ViewRootImpl viewRootImpl = getViewRootImpl();
        if (viewRootImpl == null) {
            return;
        }
        SurfaceControl surfaceControl = viewRootImpl.getSurfaceControl();
        transaction.setRelativeLayer(this.mBackgroundControl, surfaceControl, Integer.MIN_VALUE);
        transaction.setRelativeLayer(this.mSurfaceControl, surfaceControl, this.mSubLayer);
    }

    public void setChildSurfacePackage(SurfaceControlViewHost.SurfacePackage surfacePackage) {
        if (surfacePackage != null) {
            surfacePackage.getSurfaceControl();
        }
        SurfaceControlViewHost.SurfacePackage surfacePackage2 = this.mSurfacePackage;
        SurfaceControl surfaceControl = surfacePackage2 != null ? surfacePackage2.getSurfaceControl() : null;
        if (this.mSurfaceControl != null && surfaceControl != null) {
            this.mTmpTransaction.reparent(surfaceControl, null).apply();
            this.mSurfacePackage.release();
        } else if (this.mSurfaceControl != null) {
            reparentSurfacePackage(this.mTmpTransaction, surfacePackage);
            this.mTmpTransaction.apply();
        }
        this.mSurfacePackage = surfacePackage;
    }

    private void reparentSurfacePackage(SurfaceControl.Transaction transaction, SurfaceControlViewHost.SurfacePackage surfacePackage) {
        initEmbeddedHierarchyForAccessibility(surfacePackage);
        SurfaceControl surfaceControl = surfacePackage.getSurfaceControl();
        transaction.reparent(surfaceControl, this.mSurfaceControl).show(surfaceControl);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfoInternal(accessibilityNodeInfo);
        RemoteAccessibilityEmbeddedConnection remoteAccessibilityEmbeddedConnection = getRemoteAccessibilityEmbeddedConnection();
        if (remoteAccessibilityEmbeddedConnection == null) {
            return;
        }
        accessibilityNodeInfo.addChild(remoteAccessibilityEmbeddedConnection.getLeashToken());
    }

    @Override // android.view.View
    public int getImportantForAccessibility() {
        int importantForAccessibility = super.getImportantForAccessibility();
        if (this.mRemoteAccessibilityEmbeddedConnection == null || importantForAccessibility != 0) {
            return importantForAccessibility;
        }
        return 1;
    }

    private void initEmbeddedHierarchyForAccessibility(SurfaceControlViewHost.SurfacePackage surfacePackage) {
        IAccessibilityEmbeddedConnection accessibilityEmbeddedConnection = surfacePackage.getAccessibilityEmbeddedConnection();
        RemoteAccessibilityEmbeddedConnection remoteAccessibilityEmbeddedConnection = getRemoteAccessibilityEmbeddedConnection();
        if (remoteAccessibilityEmbeddedConnection == null || !remoteAccessibilityEmbeddedConnection.getConnection().equals(accessibilityEmbeddedConnection)) {
            setRemoteAccessibilityEmbeddedConnection(null, null);
            try {
                setRemoteAccessibilityEmbeddedConnection(accessibilityEmbeddedConnection, accessibilityEmbeddedConnection.associateEmbeddedHierarchy(getViewRootImpl().mLeashToken, getAccessibilityViewId()));
            } catch (RemoteException e) {
                Log.d(TAG, "Error while associateEmbeddedHierarchy " + e);
            }
            updateScreenMatrixForEmbeddedHierarchy();
        }
    }

    private void setRemoteAccessibilityEmbeddedConnection(IAccessibilityEmbeddedConnection iAccessibilityEmbeddedConnection, IBinder iBinder) {
        try {
            if (this.mRemoteAccessibilityEmbeddedConnection != null) {
                this.mRemoteAccessibilityEmbeddedConnection.getConnection().disassociateEmbeddedHierarchy();
                this.mRemoteAccessibilityEmbeddedConnection.unlinkToDeath();
                this.mRemoteAccessibilityEmbeddedConnection = null;
            }
            if (iAccessibilityEmbeddedConnection == null || iBinder == null) {
                return;
            }
            RemoteAccessibilityEmbeddedConnection remoteAccessibilityEmbeddedConnection = new RemoteAccessibilityEmbeddedConnection(iAccessibilityEmbeddedConnection, iBinder);
            this.mRemoteAccessibilityEmbeddedConnection = remoteAccessibilityEmbeddedConnection;
            remoteAccessibilityEmbeddedConnection.linkToDeath();
        } catch (RemoteException e) {
            Log.d(TAG, "Error while setRemoteEmbeddedConnection " + e);
        }
    }

    private RemoteAccessibilityEmbeddedConnection getRemoteAccessibilityEmbeddedConnection() {
        return this.mRemoteAccessibilityEmbeddedConnection;
    }

    private void updateScreenMatrixForEmbeddedHierarchy() {
        getBoundsOnScreen(this.mTmpRect);
        this.mTmpMatrix.reset();
        this.mTmpMatrix.setTranslate(this.mTmpRect.left, this.mTmpRect.top);
        this.mTmpMatrix.postScale(this.mScreenRect.width() / this.mSurfaceWidth, this.mScreenRect.height() / this.mSurfaceHeight);
        if (this.mTmpMatrix.isIdentity() || this.mTmpMatrix.equals(this.mScreenMatrixForEmbeddedHierarchy)) {
            return;
        }
        try {
            RemoteAccessibilityEmbeddedConnection remoteAccessibilityEmbeddedConnection = getRemoteAccessibilityEmbeddedConnection();
            if (remoteAccessibilityEmbeddedConnection == null) {
                return;
            }
            this.mTmpMatrix.getValues(this.mMatrixValues);
            remoteAccessibilityEmbeddedConnection.getConnection().setScreenMatrix(this.mMatrixValues);
            this.mScreenMatrixForEmbeddedHierarchy.set(this.mTmpMatrix);
        } catch (RemoteException e) {
            Log.d(TAG, "Error while setScreenMatrix " + e);
        }
    }

    private void notifySurfaceDestroyed() {
        if (this.mSurface.isValid()) {
            for (SurfaceHolder.Callback callback : getSurfaceCallbacks()) {
                callback.surfaceDestroyed(this.mSurfaceHolder);
            }
            if (this.mSurface.isValid()) {
                this.mSurface.forceScopedDisconnect();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    final class RemoteAccessibilityEmbeddedConnection implements IBinder.DeathRecipient {
        private final IAccessibilityEmbeddedConnection mConnection;
        private final IBinder mLeashToken;

        RemoteAccessibilityEmbeddedConnection(IAccessibilityEmbeddedConnection iAccessibilityEmbeddedConnection, IBinder iBinder) {
            this.mConnection = iAccessibilityEmbeddedConnection;
            this.mLeashToken = iBinder;
        }

        IAccessibilityEmbeddedConnection getConnection() {
            return this.mConnection;
        }

        IBinder getLeashToken() {
            return this.mLeashToken;
        }

        void linkToDeath() throws RemoteException {
            this.mConnection.asBinder().linkToDeath(this, 0);
        }

        void unlinkToDeath() {
            this.mConnection.asBinder().unlinkToDeath(this, 0);
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            unlinkToDeath();
            SurfaceView.this.runOnUiThread(new Runnable() { // from class: android.view.-$$Lambda$SurfaceView$RemoteAccessibilityEmbeddedConnection$MJCC8_qn1j4IJab7lJYkrpYVv74
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$binderDied$0$SurfaceView$RemoteAccessibilityEmbeddedConnection();
                }
            });
        }

        public /* synthetic */ void lambda$binderDied$0$SurfaceView$RemoteAccessibilityEmbeddedConnection() {
            if (SurfaceView.this.mRemoteAccessibilityEmbeddedConnection == this) {
                SurfaceView.this.mRemoteAccessibilityEmbeddedConnection = null;
            }
        }
    }
}
