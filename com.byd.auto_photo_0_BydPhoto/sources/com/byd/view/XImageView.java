package com.byd.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import b.a.h.a;
import b.a.h.e;
import com.byd.auto_photo.R;
import com.byd.util.w;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes2.dex */
public class XImageView extends ImageView {
    private static final int DOUBLE_TAP_TIMEOUT = ViewConfiguration.getDoubleTapTimeout();
    private static final int MODE_DRAG = 1;
    public static final int MODE_ENABLE_ZOOM = 0;
    public static final int MODE_NONE = -1;
    private static final int MODE_ZOOM = 2;
    private static final int MSG_GIF_ERROR = 8;
    private static final int MSG_GIF_INIT_OK = 6;
    private static final int MSG_GIF_INIT_START = 5;
    private static final int MSG_GIF_SHOW_DEF_THUMB = 9;
    private static final int MSG_GIF_UPDATE = 7;
    private static final int TAP = 3;
    private static final int TAP_CANCEL = 4;
    private static final int TAP_TIMEOUT = 150;
    private Point center;
    private int currentIndex;
    private e.b[] gifFrames;
    private String gifTips;
    private RectF imageRect;
    private Future<?> initFuture;
    private boolean isChanged;
    private boolean isGif;
    private boolean isLoadingGif;
    private Point lastArcPoint;
    private float lastDis;
    private Matrix lastMatrix;
    private Point lastPoint;
    private long lastUpTime;
    private LoadGifRunnable loadGifRunnable;
    private ExecutorService mExecutor;
    private Handler mHandler;
    private Matrix mMatrix;
    private float[] matrixValues;
    private int mode;
    private Map<String, ImageState> oriState;
    private Rect textBounds;
    private Paint textPaint;
    private int viewHeight;
    private int viewWidth;

    private static class ImageState {
        float SCALE_MAX;
        Matrix matrix;
        RectF rect;
        float scale;

        private ImageState() {
        }
    }

    private class LoadGifRunnable implements Runnable, e.a {
        private String path;

        private LoadGifRunnable(String str) {
            this.path = str;
        }

        @Override // b.a.h.e.a
        public boolean canDecodeContent(int i, int i2) {
            return XImageView.this.gifContentDecode(i, i2);
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            Message messageObtainMessage;
            XImageView.this.mHandler.sendEmptyMessage(5);
            FileInputStream fileInputStream = null;
            try {
                try {
                    try {
                        Thread.sleep(0L);
                        e eVar = new e();
                        eVar.u(this);
                        FileInputStream fileInputStream2 = new FileInputStream(this.path);
                        try {
                            w.c("gif load : " + this.path);
                            int iJ = eVar.j(fileInputStream2);
                            w.c("gif load state : " + iJ);
                            if (iJ != 0 || eVar.d() <= 0) {
                                messageObtainMessage = XImageView.this.mHandler.obtainMessage(9, this.path);
                            } else {
                                e.b[] bVarArrE = eVar.e();
                                if (bVarArrE.length > 1) {
                                    if (bVarArrE[XImageView.this.currentIndex].f484b == 0) {
                                        XImageView.this.resetDelayTime(bVarArrE);
                                    }
                                    messageObtainMessage = XImageView.this.mHandler.obtainMessage(6, eVar.g(), eVar.f(), bVarArrE);
                                } else {
                                    messageObtainMessage = XImageView.this.mHandler.obtainMessage(9, this.path);
                                }
                            }
                            messageObtainMessage.sendToTarget();
                            fileInputStream2.close();
                        } catch (Exception e) {
                            e = e;
                            fileInputStream = fileInputStream2;
                            w.c("load gif in XImageView -- exception : " + this.path + " " + e.getMessage());
                            XImageView.this.mHandler.sendEmptyMessage(8);
                            if (fileInputStream == null) {
                            } else {
                                fileInputStream.close();
                            }
                        } catch (Throwable th) {
                            th = th;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }

        public void setPath(String str) {
            this.path = str;
        }
    }

    private class LoadGifStreamRunnable implements Runnable, e.a {
        private InputStream in;

        private LoadGifStreamRunnable(InputStream inputStream) {
            this.in = inputStream;
        }

        @Override // b.a.h.e.a
        public boolean canDecodeContent(int i, int i2) {
            return XImageView.this.gifContentDecode(i, i2);
        }

        @Override // java.lang.Runnable
        public void run() {
            ByteArrayOutputStream byteArrayOutputStream;
            byte[] bArr;
            Message messageObtainMessage;
            XImageView.this.mHandler.sendEmptyMessage(5);
            try {
                try {
                    try {
                        Thread.sleep(0L);
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        bArr = new byte[1024];
                    } catch (Exception e) {
                        w.c("load gif stream in XImageView -- exception : " + e.getMessage());
                        XImageView.this.mHandler.sendEmptyMessage(8);
                        this.in.close();
                    }
                    while (true) {
                        int i = this.in.read(bArr);
                        if (i == -1) {
                            break;
                        } else {
                            byteArrayOutputStream.write(bArr, 0, i);
                        }
                    }
                    byteArrayOutputStream.flush();
                    e eVar = new e();
                    eVar.u(this);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    int iJ = eVar.j(new ByteArrayInputStream(byteArray));
                    w.c("LoadGifStreamRunnable run : " + iJ);
                    if (iJ != 0 || eVar.d() <= 0) {
                        Bitmap bitmapI = a.i(byteArray, 1080, 1080, 1, Bitmap.Config.RGB_565, false);
                        w.c("decodeStream : " + bitmapI);
                        messageObtainMessage = XImageView.this.mHandler.obtainMessage(9, bitmapI);
                    } else {
                        e.b[] bVarArrE = eVar.e();
                        w.c("==>" + bVarArrE.length + " " + bVarArrE[XImageView.this.currentIndex].f484b);
                        if (bVarArrE.length > 1) {
                            if (bVarArrE[XImageView.this.currentIndex].f484b == 0) {
                                XImageView.this.resetDelayTime(bVarArrE);
                            }
                            messageObtainMessage = XImageView.this.mHandler.obtainMessage(6, eVar.g(), eVar.f(), bVarArrE);
                        } else {
                            messageObtainMessage = XImageView.this.mHandler.obtainMessage(9, bVarArrE[0].f483a != null ? bVarArrE[0].f483a : a.i(byteArray, 1080, 1080, 1, Bitmap.Config.RGB_565, false));
                        }
                    }
                    messageObtainMessage.sendToTarget();
                    this.in.close();
                } catch (Throwable th) {
                    try {
                        this.in.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    private static class MHandler extends Handler {
        private WeakReference<XImageView> v;

        private MHandler(XImageView xImageView) {
            this.v = new WeakReference<>(xImageView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Bitmap bitmapH = null;
            if (this.v.get() == null) {
                removeCallbacksAndMessages(null);
                return;
            }
            switch (message.what) {
                case 3:
                    this.v.get().performClick();
                    return;
                case 4:
                    removeMessages(3);
                    return;
                case 5:
                    this.v.get().gifTips = this.v.get().getContext().getResources().getString(R.string.gif_loading);
                    this.v.get().isLoadingGif = true;
                    break;
                case 6:
                    this.v.get().isLoadingGif = false;
                    this.v.get().gifFrames = (e.b[]) message.obj;
                    if (this.v.get().gifFrames == null || this.v.get().gifFrames.length <= 0) {
                        return;
                    }
                    this.v.get().requestLayout();
                    sendEmptyMessage(7);
                    this.v.get().currentIndex = 0;
                    return;
                case 7:
                    this.v.get().updateGifFrame();
                    return;
                case 8:
                    this.v.get().gifTips = this.v.get().getContext().getResources().getString(R.string.gif_load_fail);
                    break;
                case 9:
                    this.v.get().isLoadingGif = false;
                    Object obj = message.obj;
                    if (obj != null) {
                        if (obj instanceof String) {
                            bitmapH = a.h((String) obj, 1080, 1080, 1, Bitmap.Config.RGB_565, false);
                        } else if (obj instanceof Bitmap) {
                            bitmapH = (Bitmap) obj;
                        }
                    }
                    if (bitmapH != null) {
                        this.v.get().setImageBitmap(bitmapH);
                        return;
                    } else {
                        sendEmptyMessage(8);
                        return;
                    }
                default:
                    return;
            }
            this.v.get().invalidate();
        }
    }

    public XImageView(Context context) {
        super(context);
        this.mode = 0;
        this.oriState = new HashMap();
        this.gifTips = "gif图像加载中";
        this.matrixValues = new float[9];
        init();
    }

    public XImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mode = 0;
        this.oriState = new HashMap();
        this.gifTips = "gif图像加载中";
        this.matrixValues = new float[9];
        init();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkBound() {
        /*
            r8 = this;
            android.graphics.Matrix r0 = r8.mMatrix
            r8.getImageInfo(r0)
            android.graphics.RectF r0 = r8.imageRect
            float r1 = r0.left
            r2 = 0
            int r3 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            r4 = 0
            if (r3 <= 0) goto L21
            float r3 = r0.right
            int r5 = r8.viewWidth
            float r6 = (float) r5
            int r6 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r6 <= 0) goto L21
            float r0 = (float) r5
            float r3 = r3 - r0
            float r0 = java.lang.Math.min(r1, r3)
            float r0 = -r0
        L1f:
            int r0 = (int) r0
            goto L37
        L21:
            int r3 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r3 >= 0) goto L36
            float r0 = r0.right
            int r3 = r8.viewWidth
            float r5 = (float) r3
            int r5 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r5 >= 0) goto L36
            float r3 = (float) r3
            float r3 = r3 - r0
            float r0 = -r1
            float r0 = java.lang.Math.min(r3, r0)
            goto L1f
        L36:
            r0 = r4
        L37:
            android.graphics.RectF r1 = r8.imageRect
            float r3 = r1.top
            int r5 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r5 <= 0) goto L51
            float r5 = r1.bottom
            int r6 = r8.viewHeight
            float r7 = (float) r6
            int r7 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r7 <= 0) goto L51
            float r1 = (float) r6
            float r5 = r5 - r1
            float r1 = java.lang.Math.min(r3, r5)
            float r1 = -r1
        L4f:
            int r4 = (int) r1
            goto L66
        L51:
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 >= 0) goto L66
            float r1 = r1.bottom
            int r2 = r8.viewHeight
            float r5 = (float) r2
            int r5 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r5 >= 0) goto L66
            float r2 = (float) r2
            float r2 = r2 - r1
            float r1 = -r3
            float r1 = java.lang.Math.min(r2, r1)
            goto L4f
        L66:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "checkBound : "
            r1.append(r2)
            android.graphics.RectF r2 = r8.imageRect
            r1.append(r2)
            java.lang.String r2 = " "
            r1.append(r2)
            r1.append(r0)
            r1.append(r2)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            com.byd.util.w.c(r1)
            if (r0 != 0) goto L8e
            if (r4 == 0) goto L9c
        L8e:
            android.graphics.Matrix r1 = r8.mMatrix
            float r0 = (float) r0
            float r2 = (float) r4
            r1.postTranslate(r0, r2)
            android.graphics.Matrix r0 = r8.mMatrix
            java.lang.String r1 = "check_bound"
            r8.updateImageView(r0, r1)
        L9c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.view.XImageView.checkBound():void");
    }

    private void drawTipsText(Canvas canvas, String str) {
        if (this.textPaint == null) {
            Paint paint = new Paint();
            this.textPaint = paint;
            paint.setColor(-1);
            this.textPaint.setTextSize(32.0f);
        }
        if (this.textBounds == null) {
            this.textBounds = new Rect();
        }
        this.textPaint.getTextBounds(str, 0, str.length(), this.textBounds);
        canvas.drawText(str, (getWidth() - this.textBounds.width()) >> 1, (getHeight() - this.textBounds.height()) >> 1, this.textPaint);
    }

    private void getCenter(MotionEvent motionEvent) {
        this.center.set((int) ((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f), (int) ((motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f));
    }

    private float getDegress(float f, float f2, float f3) {
        Point point = this.center;
        float degrees = ((float) Math.toDegrees((float) Math.atan(((f2 - point.y) + f3) / Math.abs(f - point.x)))) * 2.0f;
        return f < ((float) this.center.x) ? 360.0f - degrees : degrees;
    }

    private float getDistance(MotionEvent motionEvent) {
        return (float) Math.sqrt(Math.pow(motionEvent.getX(0) - motionEvent.getX(1), 2.0d) + Math.pow(motionEvent.getY(0) - motionEvent.getY(1), 2.0d));
    }

    private float getRotateRadius(float f, float f2) {
        return (float) Math.sqrt(Math.pow(f - this.center.x, 2.0d) + Math.pow(f2 - this.center.y, 2.0d));
    }

    private String getStateKey() {
        return this.viewWidth + "-" + this.viewHeight;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean gifContentDecode(int i, int i2) {
        return !(i >= 2000 || i2 >= 2000);
    }

    private void init() {
        this.lastPoint = new Point();
        this.center = new Point();
        this.lastArcPoint = new Point();
        this.mHandler = new MHandler();
        if (com.byd.util.e.E()) {
            this.mode = -1;
        }
    }

    private void initOriginal() {
        String stateKey = getStateKey();
        if (this.oriState.get(stateKey) == null) {
            ImageState imageState = new ImageState();
            Matrix matrix = new Matrix();
            matrix.set(getImageMatrix());
            float[] fArr = new float[9];
            matrix.getValues(fArr);
            float f = fArr[0];
            imageState.scale = f;
            imageState.SCALE_MAX = f * 5.0f;
            imageState.matrix = matrix;
            imageState.rect = new RectF(getImageInfo(matrix));
            this.oriState.put(stateKey, imageState);
            w.c("initOriginal : " + this.oriState.size() + " " + fArr[0] + " " + fArr[4]);
        }
    }

    private void preLoadGif() {
        if (this.mExecutor == null) {
            this.mExecutor = Executors.newSingleThreadExecutor();
        }
        Future<?> future = this.initFuture;
        if (future != null && !future.isCancelled() && !this.initFuture.isDone()) {
            Log.e("m_tag", "loadGif cancel current");
            this.initFuture.cancel(true);
        }
        this.isGif = true;
    }

    private void releaseGif() {
        Future<?> future = this.initFuture;
        if (future != null && !future.isDone() && !this.initFuture.isCancelled()) {
            this.initFuture.cancel(true);
            this.initFuture = null;
        }
        ExecutorService executorService = this.mExecutor;
        if (executorService != null) {
            executorService.shutdownNow();
            this.mExecutor = null;
        }
        if (this.loadGifRunnable != null) {
            this.loadGifRunnable = null;
        }
        if (this.isGif) {
            this.mHandler.removeMessages(7);
            Log.e("m_tag", "releaseGif");
            e.b[] bVarArr = this.gifFrames;
            if (bVarArr != null) {
                int length = bVarArr.length;
                for (int i = 0; i < length; i++) {
                    Bitmap bitmap = this.gifFrames[i].f483a;
                    if (bitmap != null && !bitmap.isRecycled()) {
                        bitmap.recycle();
                    }
                }
                this.gifFrames = null;
            }
            this.isGif = false;
            this.currentIndex = 0;
        }
        this.isLoadingGif = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetDelayTime(e.b[] bVarArr) {
        for (e.b bVar : bVarArr) {
            if (bVar.f484b == 0) {
                bVar.f484b = 50;
            }
        }
    }

    private void resetGifHandler() {
        this.mHandler.removeMessages(5);
        this.mHandler.removeMessages(6);
        this.mHandler.removeMessages(7);
        this.mHandler.removeMessages(8);
        this.mHandler.removeMessages(9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateGifFrame() {
        if (getDrawable() == null) {
            super.setImageBitmap(this.gifFrames[this.currentIndex].f483a);
        } else {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable();
            try {
                Method method = bitmapDrawable.getClass().getMethod("setBitmap", Bitmap.class);
                method.setAccessible(true);
                method.invoke(bitmapDrawable, this.gifFrames[this.currentIndex].f483a);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        e.b[] bVarArr = this.gifFrames;
        if (bVarArr.length > 1) {
            if (bVarArr[this.currentIndex].f484b > 0) {
                this.mHandler.sendEmptyMessageDelayed(7, bVarArr[r3].f484b);
                int i = this.currentIndex;
                if (i < this.gifFrames.length - 1) {
                    this.currentIndex = i + 1;
                } else {
                    this.currentIndex = 0;
                }
            }
        }
    }

    private void updateImageView(Matrix matrix, String str) {
        updateImageView(matrix, str, true);
    }

    public void clearAllState() {
        if (this.oriState.isEmpty()) {
            return;
        }
        this.oriState.clear();
    }

    public void copyOriginalState(XImageView xImageView) {
        Map<String, ImageState> map = xImageView.oriState;
        if (map.size() == 0) {
            return;
        }
        for (Map.Entry<String, ImageState> entry : map.entrySet()) {
            this.oriState.put(entry.getKey(), entry.getValue());
        }
    }

    public RectF getImageInfo() {
        return getImageInfo(getImageMatrix());
    }

    public RectF getImageInfo(Matrix matrix) {
        Rect bounds = getDrawable().getBounds();
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        float fWidth = bounds.width() * fArr[0];
        float fHeight = bounds.height() * fArr[4];
        float f = fArr[2];
        float f2 = fArr[5];
        float f3 = fWidth + f;
        float f4 = fHeight + f2;
        RectF rectF = this.imageRect;
        if (rectF == null) {
            this.imageRect = new RectF(f, f2, f3, f4);
        } else {
            rectF.set(f, f2, f3, f4);
        }
        return this.imageRect;
    }

    public int getMode() {
        return this.mode;
    }

    public int getViewHeight() {
        return this.viewHeight;
    }

    public int getViewWidth() {
        return this.viewWidth;
    }

    public boolean isMatrixChanged() {
        ImageState imageState = this.oriState.get(getStateKey());
        if (imageState == null) {
            return false;
        }
        getImageMatrix().getValues(new float[9]);
        imageState.matrix.getValues(new float[9]);
        return !Arrays.equals(r2, r1);
    }

    public void loadGifPath(String str) {
        LoadGifRunnable loadGifRunnable = this.loadGifRunnable;
        if (loadGifRunnable == null || !loadGifRunnable.path.equals(str)) {
            preLoadGif();
            resetGifHandler();
            LoadGifRunnable loadGifRunnable2 = this.loadGifRunnable;
            if (loadGifRunnable2 == null) {
                this.loadGifRunnable = new LoadGifRunnable(str);
            } else {
                loadGifRunnable2.setPath(str);
            }
            this.initFuture = this.mExecutor.submit(this.loadGifRunnable);
        }
    }

    public void loadGifStream(InputStream inputStream) {
        preLoadGif();
        resetGifHandler();
        this.initFuture = this.mExecutor.submit(new LoadGifStreamRunnable(inputStream));
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        w.c("XImageView onDetachedFromWindow:" + getTag());
        this.mHandler.removeCallbacksAndMessages(null);
        if (this.isGif) {
            releaseGif();
        }
        super.onDetachedFromWindow();
        this.lastMatrix = null;
        clearAllState();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap;
        if (this.isLoadingGif) {
            drawTipsText(canvas, this.gifTips);
            return;
        }
        if (getDrawable() == null) {
            return;
        }
        if (this.lastMatrix != null) {
            w.c("set last matrix");
            getImageMatrix().set(this.lastMatrix);
        }
        if ((getDrawable() instanceof BitmapDrawable) && ((bitmap = ((BitmapDrawable) getDrawable()).getBitmap()) == null || bitmap.isRecycled())) {
            return;
        }
        super.onDraw(canvas);
        initOriginal();
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.viewWidth == i && this.viewHeight == i2) {
            return;
        }
        if (this.lastMatrix != null) {
            ImageState imageState = this.oriState.get(i3 + "-" + i4);
            this.lastMatrix = null;
            if (imageState != null) {
                w.c("===resetImage onSizeChanged===");
                updateImageView(imageState.matrix, "reset", false);
            }
        }
        this.viewWidth = i;
        this.viewHeight = i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0199  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r17) {
        /*
            Method dump skipped, instruction units count: 716
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.view.XImageView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void resetImage() {
        ImageState imageState = this.oriState.get(getStateKey());
        if (imageState == null) {
            return;
        }
        w.c("===resetImage===");
        this.lastMatrix = null;
        updateImageView(imageState.matrix, "reset", false);
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        releaseGif();
        clearAllState();
        if (bitmap != null) {
            w.c("setImageBitmap : " + bitmap.getWidth() + " " + bitmap.getHeight());
        }
        super.setImageBitmap(bitmap);
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        releaseGif();
        clearAllState();
        super.setImageResource(i);
    }

    @Override // android.widget.ImageView
    public void setImageURI(@Nullable Uri uri) {
        releaseGif();
        clearAllState();
        super.setImageURI(uri);
    }

    public void setMode(int i) {
        if (com.byd.util.e.E()) {
            return;
        }
        this.mode = i;
        if (i == -1) {
            w.c("set mode none , reset");
            resetImage();
        }
    }

    public void updateImageView(Matrix matrix, String str, boolean z) {
        if (z) {
            Matrix matrix2 = this.lastMatrix;
            if (matrix2 == null) {
                this.lastMatrix = new Matrix(matrix);
            } else {
                matrix2.set(matrix);
            }
        }
        try {
            Method method = getClass().getMethod("animateTransform", Matrix.class);
            method.setAccessible(true);
            method.invoke(this, matrix);
        } catch (Exception e) {
            e.printStackTrace();
            w.c("animateTransform exception : " + e.getMessage());
            getImageMatrix().set(matrix);
            invalidate();
        }
    }
}
