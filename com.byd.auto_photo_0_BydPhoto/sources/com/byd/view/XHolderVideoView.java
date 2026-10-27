package com.byd.view;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.annotation.NonNull;
import b.a.i.a;
import com.byd.util.w;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class XHolderVideoView extends SurfaceView implements VideoPlayerBaseView, SurfaceHolder.Callback {
    private static final int DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
    private static final int TAP = 3;
    private static final int TAP_CANCEL = 4;
    private static final int TAP_TIMEOUT = 150;
    private boolean canZoom;
    private boolean isMove;
    private final PointF lastPoint;
    private long lastUpTime;
    private Handler mHandler;
    private Surface mSurface;
    private int mVideoHeight;
    private int mVideoWidth;
    private boolean multiPointer;
    private a onPlayerListener;
    private OnVideoViewListener onVideoViewListener;
    private VelocityTracker tracker;
    private int viewHeight;
    private int viewWidth;
    private float xVelocity;
    private float yVelocity;

    static class MyHandler extends Handler {
        private WeakReference<XHolderVideoView> thisView;

        public MyHandler(XHolderVideoView xHolderVideoView) {
            this.thisView = new WeakReference<>(xHolderVideoView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            WeakReference<XHolderVideoView> weakReference = this.thisView;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            int i = message.what;
            if (i == 3) {
                this.thisView.get().performClick();
            } else {
                if (i != 4) {
                    return;
                }
                removeMessages(3);
            }
        }
    }

    public XHolderVideoView(Context context) {
        super(context);
        this.lastPoint = new PointF();
        this.canZoom = false;
        this.xVelocity = 0.0f;
        this.yVelocity = 0.0f;
        init(context);
    }

    public XHolderVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.lastPoint = new PointF();
        this.canZoom = false;
        this.xVelocity = 0.0f;
        this.yVelocity = 0.0f;
        init(context);
    }

    public XHolderVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.lastPoint = new PointF();
        this.canZoom = false;
        this.xVelocity = 0.0f;
        this.yVelocity = 0.0f;
        init(context);
    }

    private void clearVelocity() {
        VelocityTracker velocityTracker = this.tracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
            this.tracker.recycle();
            this.tracker = null;
        }
    }

    private void init(Context context) {
        getHolder().addCallback(this);
        this.mHandler = new MyHandler(this);
    }

    @Override // com.byd.view.VideoPlayerBaseView
    public a getOnPlayerListener() {
        return this.onPlayerListener;
    }

    @Override // com.byd.view.VideoPlayerBaseView
    public int getVideoHeight() {
        return this.mVideoHeight;
    }

    @Override // com.byd.view.VideoPlayerBaseView
    public int getVideoWidth() {
        return this.mVideoWidth;
    }

    @Override // com.byd.view.VideoPlayerBaseView
    public boolean isSurfaceCreated() {
        return this.mSurface != null;
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int defaultSize = SurfaceView.getDefaultSize(this.mVideoWidth, i);
        int defaultSize2 = SurfaceView.getDefaultSize(this.mVideoHeight, i2);
        if (this.mVideoWidth > 0 && this.mVideoHeight > 0) {
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size2 = View.MeasureSpec.getSize(i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                int i5 = this.mVideoWidth;
                int i6 = i5 * size2;
                int i7 = this.mVideoHeight;
                if (i6 < size * i7) {
                    defaultSize = (i5 * size2) / i7;
                    defaultSize2 = size2;
                } else {
                    if (i5 * size2 > size * i7) {
                        defaultSize2 = (i7 * size) / i5;
                        defaultSize = size;
                    }
                    defaultSize = size;
                    defaultSize2 = size2;
                }
            } else if (mode == 1073741824) {
                int i8 = (this.mVideoHeight * size) / this.mVideoWidth;
                if (mode2 != Integer.MIN_VALUE || i8 <= size2) {
                    defaultSize2 = i8;
                    defaultSize = size;
                }
                defaultSize = size;
                defaultSize2 = size2;
            } else {
                if (mode2 == 1073741824) {
                    i4 = (this.mVideoWidth * size2) / this.mVideoHeight;
                    if (mode == Integer.MIN_VALUE && i4 > size) {
                        defaultSize = size;
                    }
                    defaultSize2 = size2;
                } else {
                    int i9 = this.mVideoWidth;
                    int i10 = this.mVideoHeight;
                    if (mode2 != Integer.MIN_VALUE || i10 <= size2) {
                        i4 = i9;
                        size2 = i10;
                    } else {
                        i4 = (size2 * i9) / i10;
                    }
                    if (mode == Integer.MIN_VALUE && i4 > size) {
                        defaultSize2 = (i10 * size) / i9;
                        defaultSize = size;
                    }
                }
                defaultSize = i4;
                defaultSize2 = size2;
            }
        }
        int i11 = this.viewWidth;
        if (i11 > 0 && (i3 = this.viewHeight) > 0) {
            if (i11 == defaultSize && i3 == defaultSize2) {
                setMeasuredDimension(defaultSize, defaultSize2);
                return;
            } else if (defaultSize == 0 || defaultSize2 == 0) {
                setMeasuredDimension(i11, i3);
                return;
            }
        }
        setMeasuredDimension(defaultSize, defaultSize2);
        this.viewWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        this.viewHeight = measuredHeight;
        OnVideoViewListener onVideoViewListener = this.onVideoViewListener;
        if (onVideoViewListener != null) {
            onVideoViewListener.onViewSizeChange(this.viewWidth, measuredHeight);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (!this.canZoom) {
            int i = action & 255;
            if (i == 0) {
                OnVideoViewListener onVideoViewListener = this.onVideoViewListener;
                if (onVideoViewListener != null) {
                    onVideoViewListener.onMotionEventDown();
                }
                this.mHandler.removeMessages(3);
                this.mHandler.removeMessages(4);
                this.isMove = false;
                this.multiPointer = false;
                this.lastPoint.set(motionEvent.getX(), motionEvent.getY());
                this.mHandler.sendEmptyMessageDelayed(3, DOUBLE_TAP_TIMEOUT);
                this.mHandler.sendEmptyMessageDelayed(4, 150L);
                if (this.tracker == null) {
                    this.tracker = VelocityTracker.obtain();
                }
                this.tracker.addMovement(motionEvent);
                a aVar = this.onPlayerListener;
                if (aVar != null) {
                    aVar.n(motionEvent.getRawX(), motionEvent.getRawY(), this.viewWidth, this.viewHeight);
                }
            } else if (i == 1) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (this.multiPointer) {
                    this.mHandler.removeCallbacksAndMessages(null);
                    this.multiPointer = false;
                } else if (jCurrentTimeMillis - this.lastUpTime <= DOUBLE_TAP_TIMEOUT) {
                    this.mHandler.removeMessages(3);
                    this.lastUpTime = 0L;
                    a aVar2 = this.onPlayerListener;
                    if (aVar2 != null) {
                        aVar2.a();
                    }
                } else if (!this.isMove || this.onPlayerListener == null) {
                    this.mHandler.removeMessages(4);
                    a aVar3 = this.onPlayerListener;
                    if (aVar3 != null) {
                        aVar3.j();
                    }
                } else {
                    this.mHandler.removeMessages(3);
                    this.mHandler.removeMessages(4);
                    this.onPlayerListener.b();
                }
                clearVelocity();
                this.lastUpTime = jCurrentTimeMillis;
                OnVideoViewListener onVideoViewListener2 = this.onVideoViewListener;
                if (onVideoViewListener2 != null) {
                    onVideoViewListener2.onMotionEventUp();
                }
            } else if (i != 2) {
                if (i == 3) {
                    if (!this.multiPointer) {
                        this.mHandler.removeMessages(3);
                        this.multiPointer = false;
                    }
                    OnVideoViewListener onVideoViewListener3 = this.onVideoViewListener;
                    if (onVideoViewListener3 != null) {
                        onVideoViewListener3.onMotionEventUp();
                    }
                    clearVelocity();
                    a aVar4 = this.onPlayerListener;
                    if (aVar4 != null) {
                        aVar4.j();
                    }
                } else if (i == 5) {
                    this.multiPointer = true;
                    this.mHandler.removeCallbacksAndMessages(null);
                } else if (i == 6) {
                    this.multiPointer = false;
                }
            } else {
                if (this.multiPointer) {
                    return true;
                }
                if (motionEvent.getPointerCount() > 1) {
                    this.multiPointer = true;
                    return true;
                }
                VelocityTracker velocityTracker = this.tracker;
                if (velocityTracker != null) {
                    velocityTracker.addMovement(motionEvent);
                    this.tracker.computeCurrentVelocity(1000);
                    this.xVelocity = this.tracker.getXVelocity();
                    this.yVelocity = this.tracker.getYVelocity();
                }
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                PointF pointF = this.lastPoint;
                float f = pointF.x;
                int i2 = (int) (x - f);
                float f2 = pointF.y;
                int i3 = (int) (y - f2);
                if (x != f || y != f2) {
                    this.isMove = true;
                    a aVar5 = this.onPlayerListener;
                    if (aVar5 != null) {
                        aVar5.g(i2, i3, this.viewWidth, this.viewHeight, this.xVelocity, this.yVelocity);
                    }
                    this.lastPoint.set(x, y);
                }
            }
        }
        return true;
    }

    @Override // com.byd.view.VideoPlayerBaseView
    public void setCanZoom(boolean z) {
        this.canZoom = z;
    }

    @Override // com.byd.view.VideoPlayerBaseView
    public void setOnPlayerListener(a aVar) {
        this.onPlayerListener = aVar;
    }

    @Override // com.byd.view.VideoPlayerBaseView
    public void setOnVideoViewListener(OnVideoViewListener onVideoViewListener) {
        this.onVideoViewListener = onVideoViewListener;
    }

    @Override // com.byd.view.VideoPlayerBaseView
    public void setVideoSize(int i, int i2) {
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
        requestLayout();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        w.c("VideoView surfaceChanged : " + i2 + " " + i3);
        a aVar = this.onPlayerListener;
        if (aVar != null) {
            aVar.onSizeChange(i2, i3);
        }
        if (this.mVideoWidth <= 0 || this.mVideoHeight <= 0) {
            return;
        }
        requestLayout();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        this.mSurface = surfaceHolder.getSurface();
        w.c(hashCode() + " VideoView surfaceCreated");
        if (this.onVideoViewListener != null) {
            Rect surfaceFrame = surfaceHolder.getSurfaceFrame();
            this.onVideoViewListener.onSurfaceTextureAvailable(this.mSurface, surfaceFrame.width(), surfaceFrame.height());
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        w.c(hashCode() + " VideoView surfaceDestroyed");
        OnVideoViewListener onVideoViewListener = this.onVideoViewListener;
        if (onVideoViewListener != null) {
            onVideoViewListener.onSurfaceDestroy();
        }
        this.mSurface = null;
    }
}
