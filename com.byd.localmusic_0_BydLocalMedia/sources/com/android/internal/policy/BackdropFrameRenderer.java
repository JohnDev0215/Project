package com.android.internal.policy;

import android.graphics.Insets;
import android.graphics.RecordingCanvas;
import android.graphics.Rect;
import android.graphics.RenderNode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.Choreographer;
import android.view.ThreadedRenderer;

/* JADX INFO: loaded from: classes4.dex */
public class BackdropFrameRenderer extends Thread implements Choreographer.FrameCallback {
    private Drawable mCaptionBackgroundDrawable;
    private Choreographer mChoreographer;
    private DecorView mDecorView;
    private RenderNode mFrameAndBackdropNode;
    private boolean mFullscreen;
    private int mLastCaptionHeight;
    private int mLastContentHeight;
    private int mLastContentWidth;
    private int mLastXOffset;
    private int mLastYOffset;
    private ColorDrawable mNavigationBarColor;
    private boolean mOldFullscreen;
    private ThreadedRenderer mRenderer;
    private boolean mReportNextDraw;
    private Drawable mResizingBackgroundDrawable;
    private ColorDrawable mStatusBarColor;
    private RenderNode mSystemBarBackgroundNode;
    private Drawable mUserCaptionBackgroundDrawable;
    private final Rect mTargetRect = new Rect();
    private final Rect mOldTargetRect = new Rect();
    private final Rect mNewTargetRect = new Rect();
    private final Rect mOldSystemBarInsets = new Rect();
    private final Rect mSystemBarInsets = new Rect();
    private final Rect mTmpRect = new Rect();

    public BackdropFrameRenderer(DecorView decorView, ThreadedRenderer threadedRenderer, Rect rect, Drawable drawable, Drawable drawable2, Drawable drawable3, int i, int i2, boolean z, Insets insets) {
        setName("ResizeFrame");
        this.mRenderer = threadedRenderer;
        onResourcesLoaded(decorView, drawable, drawable2, drawable3, i, i2);
        RenderNode renderNodeCreate = RenderNode.create("FrameAndBackdropNode", null);
        this.mFrameAndBackdropNode = renderNodeCreate;
        this.mRenderer.addRenderNode(renderNodeCreate, true);
        this.mTargetRect.set(rect);
        this.mFullscreen = z;
        this.mOldFullscreen = z;
        this.mSystemBarInsets.set(insets.toRect());
        this.mOldSystemBarInsets.set(insets.toRect());
        start();
    }

    void onResourcesLoaded(DecorView decorView, Drawable drawable, Drawable drawable2, Drawable drawable3, int i, int i2) {
        synchronized (this) {
            this.mDecorView = decorView;
            this.mResizingBackgroundDrawable = (drawable == null || drawable.getConstantState() == null) ? null : drawable.getConstantState().newDrawable();
            this.mCaptionBackgroundDrawable = (drawable2 == null || drawable2.getConstantState() == null) ? null : drawable2.getConstantState().newDrawable();
            this.mUserCaptionBackgroundDrawable = (drawable3 == null || drawable3.getConstantState() == null) ? null : drawable3.getConstantState().newDrawable();
            if (this.mCaptionBackgroundDrawable == null) {
                this.mCaptionBackgroundDrawable = this.mResizingBackgroundDrawable;
            }
            if (i != 0) {
                this.mStatusBarColor = new ColorDrawable(i);
                addSystemBarNodeIfNeeded();
            } else {
                this.mStatusBarColor = null;
            }
            if (i2 != 0) {
                this.mNavigationBarColor = new ColorDrawable(i2);
                addSystemBarNodeIfNeeded();
            } else {
                this.mNavigationBarColor = null;
            }
        }
    }

    private void addSystemBarNodeIfNeeded() {
        if (this.mSystemBarBackgroundNode != null) {
            return;
        }
        RenderNode renderNodeCreate = RenderNode.create("SystemBarBackgroundNode", null);
        this.mSystemBarBackgroundNode = renderNodeCreate;
        this.mRenderer.addRenderNode(renderNodeCreate, false);
    }

    public void setTargetRect(Rect rect, boolean z, Rect rect2) {
        synchronized (this) {
            this.mFullscreen = z;
            this.mTargetRect.set(rect);
            this.mSystemBarInsets.set(rect2);
            pingRenderLocked(false);
        }
    }

    public void onConfigurationChange() {
        synchronized (this) {
            if (this.mRenderer != null) {
                this.mOldTargetRect.set(0, 0, 0, 0);
                pingRenderLocked(false);
            }
        }
    }

    void releaseRenderer() {
        synchronized (this) {
            if (this.mRenderer != null) {
                this.mRenderer.setContentDrawBounds(0, 0, 0, 0);
                this.mRenderer.removeRenderNode(this.mFrameAndBackdropNode);
                if (this.mSystemBarBackgroundNode != null) {
                    this.mRenderer.removeRenderNode(this.mSystemBarBackgroundNode);
                }
                this.mRenderer = null;
                pingRenderLocked(false);
            }
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            Looper.prepare();
            synchronized (this) {
                this.mChoreographer = Choreographer.getInstance();
            }
            Looper.loop();
            synchronized (this) {
                this.mChoreographer = null;
                Choreographer.releaseInstance();
            }
        } finally {
            releaseRenderer();
        }
    }

    @Override // android.view.Choreographer.FrameCallback
    public void doFrame(long j) {
        synchronized (this) {
            if (this.mRenderer == null) {
                reportDrawIfNeeded();
                Looper.myLooper().quit();
            } else {
                doFrameUncheckedLocked();
            }
        }
    }

    private void doFrameUncheckedLocked() {
        this.mNewTargetRect.set(this.mTargetRect);
        if (this.mNewTargetRect.equals(this.mOldTargetRect) && this.mOldFullscreen == this.mFullscreen && this.mSystemBarInsets.equals(this.mOldSystemBarInsets) && !this.mReportNextDraw) {
            return;
        }
        this.mOldFullscreen = this.mFullscreen;
        this.mOldTargetRect.set(this.mNewTargetRect);
        this.mOldSystemBarInsets.set(this.mSystemBarInsets);
        redrawLocked(this.mNewTargetRect, this.mFullscreen);
    }

    boolean onContentDrawn(int i, int i2, int i3, int i4) {
        boolean z;
        synchronized (this) {
            z = true;
            boolean z2 = this.mLastContentWidth == 0;
            this.mLastContentWidth = i3;
            int i5 = i4 - this.mLastCaptionHeight;
            this.mLastContentHeight = i5;
            this.mLastXOffset = i;
            this.mLastYOffset = i2;
            this.mRenderer.setContentDrawBounds(i, i2, i3 + i, this.mLastCaptionHeight + i2 + i5);
            if (!z2 || (this.mLastCaptionHeight == 0 && this.mDecorView.isShowingCaption())) {
                z = false;
            }
        }
        return z;
    }

    void onRequestDraw(boolean z) {
        synchronized (this) {
            this.mReportNextDraw = z;
            this.mOldTargetRect.set(0, 0, 0, 0);
            pingRenderLocked(true);
        }
    }

    private void redrawLocked(Rect rect, boolean z) {
        int captionHeight = this.mDecorView.getCaptionHeight();
        if (captionHeight != 0) {
            this.mLastCaptionHeight = captionHeight;
        }
        if ((this.mLastCaptionHeight == 0 && this.mDecorView.isShowingCaption()) || this.mLastContentWidth == 0 || this.mLastContentHeight == 0) {
            return;
        }
        int i = this.mLastXOffset + rect.left;
        int i2 = this.mLastYOffset + rect.top;
        int iWidth = rect.width();
        int iHeight = rect.height();
        int i3 = i + iWidth;
        int i4 = i2 + iHeight;
        this.mFrameAndBackdropNode.setLeftTopRightBottom(i, i2, i3, i4);
        RecordingCanvas recordingCanvasBeginRecording = this.mFrameAndBackdropNode.beginRecording(iWidth, iHeight);
        Drawable drawable = this.mUserCaptionBackgroundDrawable;
        if (drawable == null) {
            drawable = this.mCaptionBackgroundDrawable;
        }
        if (drawable != null) {
            drawable.setBounds(0, 0, i3, this.mLastCaptionHeight + i2);
            drawable.draw(recordingCanvasBeginRecording);
        }
        Drawable drawable2 = this.mResizingBackgroundDrawable;
        if (drawable2 != null) {
            drawable2.setBounds(0, this.mLastCaptionHeight, i3, i4);
            this.mResizingBackgroundDrawable.draw(recordingCanvasBeginRecording);
        }
        this.mFrameAndBackdropNode.endRecording();
        drawColorViews(i, i2, iWidth, iHeight, z);
        this.mRenderer.drawRenderNode(this.mFrameAndBackdropNode);
        reportDrawIfNeeded();
    }

    private void drawColorViews(int i, int i2, int i3, int i4, boolean z) {
        RenderNode renderNode = this.mSystemBarBackgroundNode;
        if (renderNode == null) {
            return;
        }
        RecordingCanvas recordingCanvasBeginRecording = renderNode.beginRecording(i3, i4);
        int i5 = i + i3;
        this.mSystemBarBackgroundNode.setLeftTopRightBottom(i, i2, i5, i2 + i4);
        int i6 = this.mSystemBarInsets.top;
        ColorDrawable colorDrawable = this.mStatusBarColor;
        if (colorDrawable != null) {
            colorDrawable.setBounds(0, 0, i5, i6);
            this.mStatusBarColor.draw(recordingCanvasBeginRecording);
        }
        if (this.mNavigationBarColor != null && z) {
            DecorView.getNavigationBarRect(i3, i4, this.mSystemBarInsets, this.mTmpRect, 1.0f);
            this.mNavigationBarColor.setBounds(this.mTmpRect);
            this.mNavigationBarColor.draw(recordingCanvasBeginRecording);
        }
        this.mSystemBarBackgroundNode.endRecording();
        this.mRenderer.drawRenderNode(this.mSystemBarBackgroundNode);
    }

    private void reportDrawIfNeeded() {
        if (this.mReportNextDraw) {
            if (this.mDecorView.isAttachedToWindow()) {
                this.mDecorView.getViewRootImpl().reportDrawFinish();
            }
            this.mReportNextDraw = false;
        }
    }

    private void pingRenderLocked(boolean z) {
        Choreographer choreographer = this.mChoreographer;
        if (choreographer != null && !z) {
            choreographer.postFrameCallback(this);
        } else {
            doFrameUncheckedLocked();
        }
    }

    void setUserCaptionBackgroundDrawable(Drawable drawable) {
        synchronized (this) {
            this.mUserCaptionBackgroundDrawable = drawable;
        }
    }
}
