package com.byd.view;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import b.a.i.a;
import com.byd.util.w;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class XVideoView extends TextureView implements VideoPlayerBaseView, TextureView.SurfaceTextureListener {
    private static final int DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
    private static final int MODE_MORE_POINTER = 2;
    private static final int MODE_SINGLE_POINTER = 1;
    private static final int TAP = 3;
    private static final int TAP_CANCEL = 4;
    private static final int TAP_TIMEOUT = 150;
    private boolean canZoom;
    private boolean isMove;
    private float lastDis;
    private PointF lastPoint;
    private long lastUpTime;
    private Handler mHandler;
    private Surface mSurface;
    private int mVideoHeight;
    private int mVideoWidth;
    private Matrix matrix;
    private int mode;
    private boolean multiPointer;
    private a onPlayerListener;
    private OnVideoViewListener onVideoViewListener;
    private float scale;
    private VelocityTracker tracker;
    float[] values;
    private int viewHeight;
    private int viewWidth;
    private float xVelocity;
    private float yVelocity;

    static class MyHandler extends Handler {
        private WeakReference<XVideoView> thisView;

        public MyHandler(XVideoView xVideoView) {
            this.thisView = new WeakReference<>(xVideoView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            WeakReference<XVideoView> weakReference = this.thisView;
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

    public XVideoView(Context context) {
        super(context);
        this.lastPoint = new PointF();
        this.scale = 1.0f;
        this.canZoom = false;
        this.xVelocity = 0.0f;
        this.yVelocity = 0.0f;
        this.values = new float[9];
        init(context);
    }

    public XVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.lastPoint = new PointF();
        this.scale = 1.0f;
        this.canZoom = false;
        this.xVelocity = 0.0f;
        this.yVelocity = 0.0f;
        this.values = new float[9];
        init(context);
    }

    public XVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.lastPoint = new PointF();
        this.scale = 1.0f;
        this.canZoom = false;
        this.xVelocity = 0.0f;
        this.yVelocity = 0.0f;
        this.values = new float[9];
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

    private PointF getCenter(MotionEvent motionEvent) {
        return new PointF((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f, (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f);
    }

    private float getSpacing(MotionEvent motionEvent) {
        return (float) Math.sqrt(Math.pow(motionEvent.getX(0) - motionEvent.getX(1), 2.0d) + Math.pow(motionEvent.getY(0) - motionEvent.getY(1), 2.0d));
    }

    private void init(Context context) {
        setLayerType(2, null);
        setSurfaceTextureListener(this);
        this.mHandler = new MyHandler(this);
        this.matrix = new Matrix();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x004b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean move(int r14, int r15, float r16, float r17) {
        /*
            r13 = this;
            r0 = r13
            r2 = r14
            r3 = r15
            android.graphics.Matrix r1 = r0.matrix
            float[] r4 = r0.values
            r1.getValues(r4)
            float[] r1 = r0.values
            r8 = 0
            r4 = r1[r8]
            r5 = 1065353216(0x3f800000, float:1.0)
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            r6 = 4
            if (r4 != 0) goto L2e
            r4 = r1[r6]
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 != 0) goto L2e
            b.a.i.a r1 = r0.onPlayerListener
            if (r1 == 0) goto L2d
            int r4 = r0.viewWidth
            int r5 = r0.viewHeight
            r2 = r14
            r3 = r15
            r6 = r16
            r7 = r17
            r1.g(r2, r3, r4, r5, r6, r7)
        L2d:
            return r8
        L2e:
            int r4 = r0.viewWidth
            float r5 = (float) r4
            r7 = r1[r8]
            float r5 = r5 * r7
            int r7 = r0.viewHeight
            float r9 = (float) r7
            r6 = r1[r6]
            float r9 = r9 * r6
            r6 = 2
            r6 = r1[r6]
            r10 = 5
            r1 = r1[r10]
            float r5 = r5 + r6
            float r9 = r9 + r1
            r10 = -1082130432(0xffffffffbf800000, float:-1.0)
            r11 = 0
            if (r2 <= 0) goto L56
            int r4 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r4 < 0) goto L4d
        L4b:
            r2 = r8
            goto L68
        L4d:
            float r4 = (float) r2
            float r4 = r4 + r6
            int r4 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r4 <= 0) goto L68
            float r6 = r6 * r10
            int r2 = (int) r6
            goto L68
        L56:
            if (r2 >= 0) goto L68
            float r6 = (float) r4
            int r6 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r6 > 0) goto L5e
            goto L4b
        L5e:
            float r6 = (float) r2
            float r6 = r6 + r5
            float r12 = (float) r4
            int r6 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r6 >= 0) goto L68
            float r2 = (float) r4
            float r2 = r2 - r5
            int r2 = (int) r2
        L68:
            if (r3 <= 0) goto L79
            int r4 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r4 < 0) goto L70
        L6e:
            r1 = r8
            goto L8c
        L70:
            float r4 = (float) r3
            float r4 = r4 + r1
            int r4 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r4 <= 0) goto L8b
            float r1 = r1 * r10
        L77:
            int r1 = (int) r1
            goto L8c
        L79:
            if (r3 >= 0) goto L8b
            float r1 = (float) r7
            int r1 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r1 > 0) goto L81
            goto L6e
        L81:
            float r1 = (float) r3
            float r1 = r1 + r9
            float r4 = (float) r7
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 >= 0) goto L8b
            float r1 = (float) r7
            float r1 = r1 - r9
            goto L77
        L8b:
            r1 = r3
        L8c:
            if (r2 == 0) goto L99
            if (r1 == 0) goto L99
            android.graphics.Matrix r3 = r0.matrix
            float r2 = (float) r2
            float r1 = (float) r1
            r3.postTranslate(r2, r1)
            r1 = 1
            return r1
        L99:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.view.XVideoView.move(int, int, float, float):boolean");
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

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int defaultSize = TextureView.getDefaultSize(this.mVideoWidth, i);
        int defaultSize2 = TextureView.getDefaultSize(this.mVideoHeight, i2);
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

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.mSurface = new Surface(surfaceTexture);
        w.c(hashCode() + " VideoView onSurfaceTextureAvailable");
        OnVideoViewListener onVideoViewListener = this.onVideoViewListener;
        if (onVideoViewListener != null) {
            onVideoViewListener.onSurfaceTextureAvailable(this.mSurface, i, i2);
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        w.c(hashCode() + " VideoView onSurfaceTextureDestroyed");
        OnVideoViewListener onVideoViewListener = this.onVideoViewListener;
        if (onVideoViewListener == null) {
            return true;
        }
        onVideoViewListener.onSurfaceDestroy();
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        w.c("VideoView onSurfaceTextureSizeChanged : " + i + " " + i2);
        a aVar = this.onPlayerListener;
        if (aVar != null) {
            aVar.onSizeChange(i, i2);
        }
        if (this.mVideoWidth <= 0 || this.mVideoHeight <= 0) {
            return;
        }
        requestLayout();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.canZoom) {
            if (action != 0) {
                if (action != 1) {
                    if (action == 2) {
                        VelocityTracker velocityTracker = this.tracker;
                        if (velocityTracker != null) {
                            velocityTracker.addMovement(motionEvent);
                            this.tracker.computeCurrentVelocity(1000);
                            this.xVelocity = this.tracker.getXVelocity();
                            this.yVelocity = this.tracker.getYVelocity();
                        }
                        int i = this.mode;
                        if (i == 1) {
                            float x = motionEvent.getX();
                            float y = motionEvent.getY();
                            PointF pointF = this.lastPoint;
                            float f = pointF.x;
                            if (x != f || y != pointF.y) {
                                if (move((int) (x - f), (int) (y - pointF.y), this.xVelocity, this.yVelocity)) {
                                    setTransform(this.matrix);
                                    this.mHandler.removeMessages(3);
                                }
                                this.lastPoint.set(x, y);
                            }
                        } else if (i == 2) {
                            float spacing = getSpacing(motionEvent);
                            float f2 = spacing / this.lastDis;
                            this.lastDis = spacing;
                            this.matrix.getValues(this.values);
                            float f3 = this.values[0];
                            if (f3 < 1.0f) {
                                this.matrix.reset();
                                this.scale = 1.0f;
                                setTransform(this.matrix);
                                return true;
                            }
                            if (f3 < 5.0f || (f3 >= 5.0f && f2 < 1.0f)) {
                                PointF center = getCenter(motionEvent);
                                this.matrix.postScale(f2, f2, center.x, center.y);
                                this.scale = f2;
                                setTransform(this.matrix);
                            }
                        }
                    } else if (action == 3) {
                        this.mHandler.removeMessages(3);
                    } else if (action == 5) {
                        this.mHandler.removeMessages(3);
                        this.mHandler.removeMessages(4);
                        float spacing2 = getSpacing(motionEvent);
                        this.lastDis = spacing2;
                        if (spacing2 >= 10.0f) {
                            this.mode = 2;
                        }
                    } else if (action == 6) {
                        this.mode = 0;
                    }
                } else if (this.mode == 1) {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - this.lastUpTime <= DOUBLE_TAP_TIMEOUT) {
                        this.mHandler.removeMessages(3);
                        this.lastUpTime = 0L;
                        if (this.scale != 1.0f) {
                            this.scale = 1.0f;
                            this.matrix.reset();
                        } else {
                            this.scale = 2.0f;
                            this.matrix.postScale(2.0f, 2.0f, motionEvent.getX(), motionEvent.getY());
                        }
                        setTransform(this.matrix);
                        a aVar = this.onPlayerListener;
                        if (aVar != null) {
                            aVar.a();
                        }
                    } else {
                        this.mHandler.removeMessages(4);
                        a aVar2 = this.onPlayerListener;
                        if (aVar2 != null) {
                            aVar2.b();
                        }
                    }
                    this.lastUpTime = jCurrentTimeMillis;
                }
                clearVelocity();
            } else {
                this.mHandler.removeMessages(3);
                this.mHandler.removeMessages(4);
                this.lastPoint.set(motionEvent.getX(), motionEvent.getY());
                this.mode = 1;
                if (this.tracker == null) {
                    this.tracker = VelocityTracker.obtain();
                }
                this.tracker.addMovement(motionEvent);
                this.mHandler.sendEmptyMessageDelayed(3, DOUBLE_TAP_TIMEOUT);
                this.mHandler.sendEmptyMessageDelayed(4, 150L);
            }
            return true;
        }
        if (action == 0) {
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
            a aVar3 = this.onPlayerListener;
            if (aVar3 != null) {
                aVar3.n(motionEvent.getRawX(), motionEvent.getRawY(), this.viewWidth, this.viewHeight);
            }
        } else if (action == 1) {
            long jCurrentTimeMillis2 = System.currentTimeMillis();
            if (this.multiPointer) {
                this.mHandler.removeCallbacksAndMessages(null);
                this.multiPointer = false;
            } else if (jCurrentTimeMillis2 - this.lastUpTime <= DOUBLE_TAP_TIMEOUT) {
                this.mHandler.removeMessages(3);
                this.lastUpTime = 0L;
                a aVar4 = this.onPlayerListener;
                if (aVar4 != null) {
                    aVar4.a();
                }
            } else if (!this.isMove || this.onPlayerListener == null) {
                this.mHandler.removeMessages(4);
                a aVar5 = this.onPlayerListener;
                if (aVar5 != null) {
                    aVar5.j();
                }
            } else {
                this.mHandler.removeMessages(3);
                this.mHandler.removeMessages(4);
                this.onPlayerListener.b();
            }
            clearVelocity();
            this.lastUpTime = jCurrentTimeMillis2;
            OnVideoViewListener onVideoViewListener2 = this.onVideoViewListener;
            if (onVideoViewListener2 != null) {
                onVideoViewListener2.onMotionEventUp();
            }
        } else if (action != 2) {
            if (action == 3) {
                if (!this.multiPointer) {
                    this.mHandler.removeMessages(3);
                    this.multiPointer = false;
                }
                OnVideoViewListener onVideoViewListener3 = this.onVideoViewListener;
                if (onVideoViewListener3 != null) {
                    onVideoViewListener3.onMotionEventUp();
                }
                clearVelocity();
                a aVar6 = this.onPlayerListener;
                if (aVar6 != null) {
                    aVar6.j();
                }
            } else if (action == 5) {
                this.multiPointer = true;
                this.mHandler.removeCallbacksAndMessages(null);
            } else if (action == 6) {
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
            VelocityTracker velocityTracker2 = this.tracker;
            if (velocityTracker2 != null) {
                velocityTracker2.addMovement(motionEvent);
                this.tracker.computeCurrentVelocity(1000);
                this.xVelocity = this.tracker.getXVelocity();
                this.yVelocity = this.tracker.getYVelocity();
            }
            float x2 = motionEvent.getX();
            float y2 = motionEvent.getY();
            PointF pointF2 = this.lastPoint;
            float f4 = pointF2.x;
            int i2 = (int) (x2 - f4);
            float f5 = pointF2.y;
            int i3 = (int) (y2 - f5);
            if (x2 != f4 || y2 != f5) {
                this.isMove = true;
                a aVar7 = this.onPlayerListener;
                if (aVar7 != null) {
                    aVar7.g(i2, i3, this.viewWidth, this.viewHeight, this.xVelocity, this.yVelocity);
                }
                this.lastPoint.set(x2, y2);
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
}
