package com.byd.music.view.jingyun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.byd.music.utils.LogUtil;
import com.byd.music.view.jingyun.other.CirclePoint;
import com.byd.music.view.jingyun.other.Triangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes5.dex */
public class JingyunView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private static final int LUMP_COUNT = 180;
    private static final int LUMP_SPACE = 1;
    private static final double SMOOTHNESS = 0.4d;
    private static final int WAVE_SAMPLING_INTERVAL = 1;
    private static int addTriangleInterval = 50;
    private static int addTriangleOnece = 1;
    private static int refreshTime = 20;
    private int allTriangleCount;
    private double bezierDistance;
    private Bitmap bitmapBg;
    private ArrayList<CirclePoint> circlePointList;
    private byte[] mBytes;
    private int mCircleLineMargin;
    private int mCircleR;
    private boolean mIsDrawing;
    private Paint mPaint;
    private int mPaintColor;
    private float[] mPoints;
    private SurfaceHolder mSurfaceHolder;
    private double moveSpeed;
    private double towPointMargin;
    Path wavePath;
    Path wavePath2;
    private static List<Triangle> triangleList = new ArrayList();
    private static double ratio = 1.0d;
    public static int pointSize = 180;
    private static Long startTime = Long.valueOf(System.currentTimeMillis());

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void setBitmapBg(Bitmap bitmap) {
        this.bitmapBg = bitmap;
    }

    public JingyunView(Context context) {
        super(context);
        this.moveSpeed = 0.2d;
        this.allTriangleCount = 500;
        this.circlePointList = new ArrayList<>();
        this.mPaintColor = Color.parseColor("#cabfa3");
        this.wavePath = new Path();
        this.wavePath2 = new Path();
        initView();
    }

    public JingyunView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.moveSpeed = 0.2d;
        this.allTriangleCount = 500;
        this.circlePointList = new ArrayList<>();
        this.mPaintColor = Color.parseColor("#cabfa3");
        this.wavePath = new Path();
        this.wavePath2 = new Path();
        initView();
    }

    public JingyunView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.moveSpeed = 0.2d;
        this.allTriangleCount = 500;
        this.circlePointList = new ArrayList<>();
        this.mPaintColor = Color.parseColor("#cabfa3");
        this.wavePath = new Path();
        this.wavePath2 = new Path();
        initView();
    }

    private void initView() {
        SurfaceHolder holder = getHolder();
        this.mSurfaceHolder = holder;
        holder.addCallback(this);
        setFocusable(true);
        setKeepScreenOn(true);
        setFocusableInTouchMode(true);
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setColor(this.mPaintColor);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(5.0f);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mIsDrawing = true;
        this.mCircleR = (getWidth() / 2) - (getWidth() / 6);
        this.bezierDistance = (1.0d - Math.cos(Math.toRadians(1.0d))) * ((double) this.mCircleR);
        this.towPointMargin = Math.sin(Math.toRadians(1.0d)) * 2.0d * ((double) this.mCircleR);
        getHolder().setFormat(-3);
        getCirclePoint(this.mCircleR, getWidth() / 2, getHeight() / 2);
        new Thread(this).start();
    }

    private void getCirclePoint(double d, double d2, double d3) {
        this.circlePointList.clear();
        for (int i = -180; i < 180; i += 2) {
            this.circlePointList.add(new CirclePoint(i, d, d2, d3));
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mIsDrawing = false;
    }

    @Override // java.lang.Runnable
    public void run() {
        LogUtil.e("run: ", SystemClock.currentThreadTimeMillis() + "--" + this.mIsDrawing);
        while (this.mIsDrawing) {
            drawSomething();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003d A[PHI: r4
  0x003d: PHI (r4v4 android.graphics.Canvas) = (r4v1 android.graphics.Canvas), (r4v8 android.graphics.Canvas) binds: [B:13:0x003b, B:7:0x0032] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void drawSomething() {
        /*
            r10 = this;
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 0
            r4 = 0
            android.view.SurfaceHolder r5 = r10.mSurfaceHolder     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            android.graphics.Canvas r4 = r5.lockCanvas()     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            r5 = 0
            android.graphics.PorterDuff$Mode r6 = android.graphics.PorterDuff.Mode.CLEAR     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            r4.drawColor(r5, r6)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            android.graphics.Bitmap r5 = r10.bitmapBg     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            if (r5 == 0) goto L22
            android.graphics.Bitmap r5 = r10.bitmapBg     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            android.graphics.Paint r6 = new android.graphics.Paint     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            r6.<init>()     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            r7 = 0
            r4.drawBitmap(r5, r7, r7, r6)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
        L22:
            r10.drawCircleLine(r4)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            long r5 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            long r5 = r5 - r0
            double r5 = (double) r5     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            double r7 = r10.moveSpeed     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            double r5 = r5 * r7
            int r5 = (int) r5     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            r10.manageTriangle(r5, r4)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            if (r4 == 0) goto L42
            goto L3d
        L35:
            r5 = move-exception
            goto L53
        L37:
            r5 = move-exception
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L35
            if (r4 == 0) goto L42
        L3d:
            android.view.SurfaceHolder r5 = r10.mSurfaceHolder
            r5.unlockCanvasAndPost(r4)
        L42:
            int r4 = com.byd.music.view.jingyun.JingyunView.refreshTime
            long r4 = (long) r4
            long r6 = java.lang.System.currentTimeMillis()
            long r6 = r6 - r0
            long r4 = r4 - r6
            long r0 = java.lang.Math.max(r4, r2)
            android.os.SystemClock.sleep(r0)
            return
        L53:
            if (r4 == 0) goto L5a
            android.view.SurfaceHolder r6 = r10.mSurfaceHolder
            r6.unlockCanvasAndPost(r4)
        L5a:
            int r4 = com.byd.music.view.jingyun.JingyunView.refreshTime
            long r6 = (long) r4
            long r8 = java.lang.System.currentTimeMillis()
            long r8 = r8 - r0
            long r6 = r6 - r8
            long r0 = java.lang.Math.max(r6, r2)
            android.os.SystemClock.sleep(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.view.jingyun.JingyunView.drawSomething():void");
    }

    private void drawAudioLine(Canvas canvas) {
        float[] fArr = this.mPoints;
        if (fArr == null || fArr.length < this.mBytes.length * 4) {
            this.mPoints = new float[this.mBytes.length * 4];
        }
        for (int i = 1; i < pointSize; i++) {
            byte[] bArr = this.mBytes;
            if (bArr[i] < 0) {
                bArr[i] = ByteCompanionObject.MAX_VALUE;
            }
            int i2 = i * 4;
            this.mPoints[i2] = (getWidth() * i) / pointSize;
            this.mPoints[i2 + 1] = getHeight() / 2;
            this.mPoints[i2 + 2] = (getWidth() * i) / pointSize;
            this.mPoints[i2 + 3] = ((getHeight() / 2) + 2) - this.mBytes[i];
        }
        canvas.drawLines(this.mPoints, this.mPaint);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void drawCircleLine(android.graphics.Canvas r7) {
        /*
            r6 = this;
            android.graphics.Path r0 = r6.wavePath
            r0.reset()
            android.graphics.Path r0 = r6.wavePath2
            r0.reset()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            r2 = r1
        L11:
            java.util.ArrayList<com.byd.music.view.jingyun.other.CirclePoint> r3 = r6.circlePointList
            int r3 = r3.size()
            int r3 = r3 + (-1)
            if (r2 >= r3) goto L6a
            java.util.ArrayList<com.byd.music.view.jingyun.other.CirclePoint> r3 = r6.circlePointList
            java.lang.Object r3 = r3.get(r2)
            com.byd.music.view.jingyun.other.CirclePoint r3 = (com.byd.music.view.jingyun.other.CirclePoint) r3
            byte[] r4 = r6.mBytes
            r4 = r4[r2]
            r3.move(r4)
            byte[] r4 = r6.mBytes
            r5 = r4[r2]
            if (r5 != 0) goto L44
            int r5 = r2 + 1
            r4 = r4[r5]
            if (r4 != 0) goto L44
            java.util.ArrayList<com.byd.music.view.jingyun.other.CirclePoint> r4 = r6.circlePointList
            java.lang.Object r4 = r4.get(r5)
            android.graphics.Point r4 = (android.graphics.Point) r4
            android.graphics.Paint r5 = r6.mPaint
            com.byd.music.view.jingyun.util.DrawUtil.drawCicleLineFromTowPoints(r7, r3, r4, r5)
            goto L67
        L44:
            r0.add(r3)
            byte[] r3 = r6.mBytes
            int r4 = r2 + 1
            r3 = r3[r4]
            if (r3 != 0) goto L67
            java.util.ArrayList<com.byd.music.view.jingyun.other.CirclePoint> r3 = r6.circlePointList
            java.lang.Object r3 = r3.get(r4)
            com.byd.music.view.jingyun.other.CirclePoint r3 = (com.byd.music.view.jingyun.other.CirclePoint) r3
            r0.add(r3)
            r3 = 4600877379321698714(0x3fd999999999999a, double:0.4)
            android.graphics.Paint r5 = r6.mPaint
            com.byd.music.view.jingyun.util.DrawUtil.drawCurvesFromPoints(r7, r0, r3, r5)
            r0.clear()
        L67:
            int r2 = r2 + 1
            goto L11
        L6a:
            java.util.ArrayList<com.byd.music.view.jingyun.other.CirclePoint> r0 = r6.circlePointList
            int r2 = r0.size()
            int r2 = r2 + (-1)
            java.lang.Object r0 = r0.get(r2)
            android.graphics.Point r0 = (android.graphics.Point) r0
            java.util.ArrayList<com.byd.music.view.jingyun.other.CirclePoint> r2 = r6.circlePointList
            java.lang.Object r1 = r2.get(r1)
            android.graphics.Point r1 = (android.graphics.Point) r1
            android.graphics.Paint r2 = r6.mPaint
            com.byd.music.view.jingyun.util.DrawUtil.drawCicleLineFromTowPoints(r7, r0, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.view.jingyun.JingyunView.drawCircleLine(android.graphics.Canvas):void");
    }

    private void manageTriangle(int i, Canvas canvas) {
        Iterator<Triangle> it = triangleList.iterator();
        while (it.hasNext()) {
            Triangle next = it.next();
            if (next.isOut(getWidth(), getHeight())) {
                it.remove();
            } else {
                next.move(i);
            }
            drawTriangle(canvas, next, this.mPaintColor);
        }
        if (System.currentTimeMillis() - startTime.longValue() <= addTriangleInterval || triangleList.size() >= this.allTriangleCount) {
            return;
        }
        for (int i2 = 0; i2 < addTriangleOnece; i2++) {
            triangleList.add(Triangle.getRandomTriangle(getWidth() / 2, getHeight() / 2));
        }
        startTime = Long.valueOf(System.currentTimeMillis());
    }

    public void drawTriangle(Canvas canvas, Triangle triangle, int i) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(i);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAlpha(getAlpha(triangle));
        paint.setStrokeWidth(5.0f);
        Path path = new Path();
        path.moveTo(triangle.topPoint1.x, triangle.topPoint1.y);
        path.lineTo(triangle.topPoint2.x, triangle.topPoint2.y);
        path.lineTo(triangle.topPoint3.x, triangle.topPoint3.y);
        path.close();
        canvas.drawPath(path, paint);
    }

    public int getAlpha(Triangle triangle) {
        double dMax = Math.max(Math.max(Math.sqrt(Math.pow(triangle.topPoint1.x - (getWidth() / 2), 2.0d) + Math.pow(triangle.topPoint1.y - (getHeight() / 2), 2.0d)), Math.sqrt(Math.pow(triangle.topPoint2.x - (getWidth() / 2), 2.0d) + Math.pow(triangle.topPoint2.y - (getHeight() / 2), 2.0d))), Math.sqrt(Math.pow(triangle.topPoint3.x - (getWidth() / 2), 2.0d) + Math.pow(triangle.topPoint3.y - (getHeight() / 2), 2.0d)));
        if (dMax < ((double) getWidth()) * 0.3d) {
            return 255;
        }
        double width = ((((-1275.0d) / (((double) getWidth()) * 2.0d)) * dMax) + 637.0d) - 280.0d;
        if (width < 0.0d) {
            width = 0.0d;
        }
        return (int) width;
    }

    public void setmPaintColor(int i) {
        this.mPaintColor = i;
        this.mPaint.setColor(i);
    }

    public void setmBytes(byte[] bArr) {
        this.mBytes = bArr;
    }
}
