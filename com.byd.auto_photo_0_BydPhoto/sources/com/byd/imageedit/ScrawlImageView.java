package com.byd.imageedit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.core.internal.view.SupportMenu;
import java.util.LinkedList;
import java.util.Stack;

/* JADX INFO: loaded from: classes2.dex */
public class ScrawlImageView extends ImageView {
    private int A;
    private b B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Rect f2869a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Paint f2870b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private PorterDuffXfermode f2871c;
    private Bitmap d;
    private Bitmap e;
    private Bitmap f;
    private Bitmap g;
    private LinkedList<com.byd.imageedit.a> h;
    private Stack<com.byd.imageedit.a> i;
    private com.byd.imageedit.a j;
    private int k;
    private float l;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private Handler q;
    private int r;
    private int s;
    private Bitmap t;
    private Bitmap u;
    private Canvas v;
    private Canvas w;
    private Rect x;
    private float y;
    private float z;

    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Log.e("m_tag", "request layout");
            ScrawlImageView.this.requestLayout();
        }
    }

    public interface b {
        void a(ScrawlImageView scrawlImageView);
    }

    public ScrawlImageView(Context context) {
        this(context, null);
    }

    public ScrawlImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.k = SupportMenu.CATEGORY_MASK;
        this.l = 5.0f;
        this.n = 1;
        this.q = new a();
        this.x = new Rect();
        f(context);
    }

    private void a() {
        int i;
        int i2;
        if (this.f == null || (i = this.p) == 0 || (i2 = this.o) == 0) {
            return;
        }
        if (i2 == this.r && i == this.s) {
            return;
        }
        float width = (r0.getWidth() * 1.0f) / this.f.getHeight();
        int i3 = this.o;
        int i4 = this.p;
        float f = (i3 * 1.0f) / i4;
        boolean z = true;
        if (width < f) {
            this.o = (int) (i4 * width);
        } else if (width > f) {
            this.p = (int) (i3 / width);
        } else {
            z = false;
        }
        this.r = this.o;
        this.s = this.p;
        if (z) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.width = this.o;
            layoutParams.height = this.p;
            setLayoutParams(layoutParams);
            Log.d("m_tag", "ScrawlImageView calcViewSize viewWidth:" + this.o + " viewHeight:" + this.p);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [android.graphics.Rect, android.graphics.Xfermode] */
    /* JADX WARN: Type inference failed for: r5v4 */
    private void c(Canvas canvas, int i, int i2) {
        boolean z;
        ?? r5;
        Bitmap bitmap;
        Rect rect;
        Paint paint;
        if (this.h.isEmpty()) {
            return;
        }
        this.x.set(0, 0, i, i2);
        float f = i;
        float f2 = i2;
        int iSaveLayer = canvas.saveLayer(0.0f, 0.0f, f, f2, null, 31);
        int size = this.h.size();
        for (int i3 = 0; i3 < size; i3++) {
            com.byd.imageedit.a aVar = this.h.get(i3);
            if (aVar.f2873a) {
                int iSaveLayer2 = canvas.saveLayer(0.0f, 0.0f, f, f2, null, 31);
                aVar.a(canvas, this.f2870b);
                this.f2870b.setXfermode(this.f2871c);
                if (aVar.e == 10) {
                    bitmap = this.g;
                    rect = this.x;
                    paint = this.f2870b;
                    r5 = 0;
                } else {
                    r5 = 0;
                    bitmap = aVar.h;
                    if (bitmap == null) {
                        bitmap = this.d;
                    }
                    rect = this.x;
                    paint = this.f2870b;
                }
                canvas.drawBitmap(bitmap, (Rect) r5, rect, paint);
                this.f2870b.setXfermode(r5);
                canvas.restoreToCount(iSaveLayer2);
                z = false;
            } else {
                if (aVar.e == 10) {
                    this.f2870b.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                    z = false;
                    this.f2870b.setColor(0);
                } else {
                    z = false;
                }
                aVar.a(canvas, this.f2870b);
                this.f2870b.setXfermode(null);
            }
        }
        canvas.restoreToCount(iSaveLayer);
    }

    private void d(com.byd.imageedit.a aVar, Canvas canvas, int i, int i2) {
        Bitmap bitmap;
        if (!aVar.f2873a) {
            if (aVar.e == 10) {
                this.f2870b.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                this.f2870b.setColor(0);
            }
            aVar.a(canvas, this.f2870b);
            this.f2870b.setXfermode(null);
            return;
        }
        int iSaveLayer = canvas.saveLayer(0.0f, 0.0f, i, i2, null, 31);
        this.x.set(0, 0, i, i2);
        aVar.a(canvas, this.f2870b);
        this.f2870b.setXfermode(this.f2871c);
        if (aVar.e == 10) {
            bitmap = this.g;
        } else {
            bitmap = aVar.h;
            if (bitmap == null) {
                bitmap = this.d;
            }
        }
        canvas.drawBitmap(bitmap, (Rect) null, this.x, this.f2870b);
        this.f2870b.setXfermode(null);
        canvas.restoreToCount(iSaveLayer);
    }

    private Matrix e(int i, int i2) {
        Matrix matrix = new Matrix();
        Rect rect = this.f2869a;
        matrix.setTranslate(rect.left, rect.top);
        matrix.postScale(this.f2869a.width() / i, this.f2869a.height() / i2, this.f2869a.centerX(), this.f2869a.centerY());
        return matrix;
    }

    private void f(Context context) {
        setLayerType(1, null);
        Paint paint = new Paint();
        this.f2870b = paint;
        paint.setAntiAlias(true);
        this.f2870b.setStrokeCap(Paint.Cap.ROUND);
        this.f2870b.setStrokeJoin(Paint.Join.ROUND);
        this.f2870b.setStyle(Paint.Style.STROKE);
        this.f2871c = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
        this.h = new LinkedList<>();
        this.i = new Stack<>();
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        this.o = Math.min(point.x, point.y);
    }

    private void g(Bitmap bitmap) {
        this.f = bitmap;
        a();
        if (this.m) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            float f = 30.0f / width;
            Matrix matrix = new Matrix();
            matrix.postScale(f, f);
            this.d = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
            this.g = Bitmap.createBitmap(this.f);
        }
    }

    private Rect getImageInfo() {
        if (getDrawable() == null) {
            Log.e("m_tag", "ScrawlImageView getImageInfo , drawable is null");
            return null;
        }
        Rect bounds = getDrawable().getBounds();
        float[] fArr = new float[9];
        getImageMatrix().getValues(fArr);
        float fWidth = bounds.width() * fArr[0];
        float fHeight = bounds.height() * fArr[4];
        float f = fArr[2];
        float f2 = fArr[5];
        float f3 = fWidth + f;
        float f4 = fHeight + f2;
        Rect rect = this.f2869a;
        if (rect == null) {
            this.f2869a = new Rect((int) f, (int) f2, (int) f3, (int) f4);
        } else {
            rect.set((int) f, (int) f2, (int) f3, (int) f4);
        }
        return this.f2869a;
    }

    private void l() {
        Bitmap bitmap = this.e;
        if (bitmap != null) {
            Matrix matrixE = e(bitmap.getWidth(), this.e.getHeight());
            Bitmap bitmap2 = this.e;
            this.e = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), this.e.getHeight(), matrixE, true);
        }
    }

    public boolean b() {
        return !this.i.isEmpty();
    }

    public Bitmap getBitmap() {
        Rect bounds = getDrawable().getBounds();
        float[] fArr = new float[9];
        getImageMatrix().getValues(fArr);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bounds.width(), bounds.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(((BitmapDrawable) getDrawable()).getBitmap(), 0.0f, 0.0f, (Paint) null);
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            com.byd.imageedit.a aVar = this.h.get(i);
            Matrix matrix = new Matrix();
            float f = 1.0f / fArr[0];
            matrix.setScale(f, 1.0f / fArr[4]);
            aVar.d.transform(matrix);
            aVar.f2875c *= f;
        }
        c(canvas, canvas.getWidth(), canvas.getHeight());
        return bitmapCreateBitmap;
    }

    public boolean h() {
        return !this.h.isEmpty();
    }

    public void i() {
        if (this.i.isEmpty()) {
            return;
        }
        this.h.addLast(this.i.pop());
        this.v.drawColor(0, PorterDuff.Mode.CLEAR);
        c(this.v, this.o, this.p);
        invalidate();
        b bVar = this.B;
        if (bVar != null) {
            bVar.a(this);
        }
    }

    public void j() {
        this.q.removeCallbacksAndMessages(null);
        if (this.t != null) {
            this.t = null;
            this.v = null;
        }
        Bitmap bitmap = this.f;
        if (bitmap != null && !bitmap.isRecycled()) {
            this.f.recycle();
        }
        if (this.u != null) {
            this.u = null;
            this.w = null;
        }
        Bitmap bitmap2 = this.d;
        if (bitmap2 != null) {
            if (!bitmap2.isRecycled()) {
                this.d.recycle();
            }
            this.d = null;
        }
        Bitmap bitmap3 = this.g;
        if (bitmap3 != null) {
            if (!bitmap3.isRecycled()) {
                this.g.recycle();
            }
            this.g = null;
        }
        k();
    }

    public ScrawlImageView k() {
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            com.byd.imageedit.a aVarRemoveFirst = this.h.removeFirst();
            Bitmap bitmap = aVarRemoveFirst.h;
            if (bitmap != null && !bitmap.isRecycled()) {
                aVarRemoveFirst.h.recycle();
                aVarRemoveFirst.h = null;
            }
        }
        return this;
    }

    public ScrawlImageView m(int i) {
        this.k = i;
        return this;
    }

    public ScrawlImageView n(int i) {
        this.n = i;
        return this;
    }

    public ScrawlImageView o(boolean z) {
        this.m = z;
        return this;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        Canvas canvas2;
        super.onDraw(canvas);
        if (this.t == null || this.u == null) {
            return;
        }
        com.byd.imageedit.a aVar = this.j;
        if (aVar != null && (i = this.A) != -1) {
            if (i != 2 || aVar.e == 10) {
                canvas2 = this.v;
            } else {
                this.w.drawColor(0, PorterDuff.Mode.CLEAR);
                aVar = this.j;
                canvas2 = this.w;
            }
            d(aVar, canvas2, this.o, this.p);
        }
        canvas.drawBitmap(this.t, 0.0f, 0.0f, (Paint) null);
        int i2 = this.A;
        if (i2 == 1) {
            this.j = null;
            this.A = -1;
        } else if (i2 == 2 && this.j.e != 10) {
            canvas.drawBitmap(this.u, 0.0f, 0.0f, (Paint) null);
        }
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        String str;
        super.onLayout(z, i, i2, i3, i4);
        if (getDrawable() == null) {
            return;
        }
        getImageInfo();
        Rect rect = this.f2869a;
        if (rect == null || rect.width() <= 0 || this.f2869a.height() <= 0) {
            str = "ScrawlImageView onLayout getImageInfo fail";
        } else {
            a();
            Bitmap bitmap = this.d;
            if (bitmap != null && !bitmap.isRecycled() && this.d.getWidth() > 0 && this.d.getHeight() > 0) {
                int width = this.d.getWidth();
                int height = this.d.getHeight();
                Log.e("m_tag", "ScrawlImageView onLayout mDefMosaicBmp width:" + width + " height:" + height);
                if (this.d != null) {
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.d, 0, 0, width, height, e(width, height), false);
                    this.d.recycle();
                    this.d = bitmapCreateBitmap;
                    Matrix matrixE = e(this.g.getWidth(), this.g.getHeight());
                    Bitmap bitmap2 = this.g;
                    this.g = Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), this.g.getHeight(), matrixE, true);
                }
                l();
                return;
            }
            str = "ScrawlImageView onLayout mDefMosaicBmp is null or width and height is 0";
        }
        Log.e("m_tag", str);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.o = getMeasuredWidth();
        this.p = getMeasuredHeight();
        Log.d("m_tag", "ScrawlImageView onMeasure " + this.o + " " + this.p);
        a();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        Bitmap bitmap;
        if (this.f2869a == null) {
            return true;
        }
        this.A = motionEvent.getAction() & 255;
        if (motionEvent.getPointerCount() > 1) {
            Log.d("m_tag", "pointer count : " + motionEvent.getPointerCount() + " action : " + this.A);
            this.A = -1;
            return true;
        }
        int i = this.A;
        if (i != 0) {
            if (i == 1) {
                com.byd.imageedit.a aVar = this.j;
                if (aVar == null) {
                    return true;
                }
                aVar.c(motionEvent.getX(), motionEvent.getY());
                b bVar = this.B;
                if (bVar != null) {
                    bVar.a(this);
                }
            } else {
                if (i != 2 || this.j == null) {
                    return true;
                }
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                float fAbs = Math.abs(x - this.y);
                float fAbs2 = Math.abs(y - this.z);
                if (fAbs >= 4.0f || fAbs2 >= 4.0f) {
                    this.j.d(x, y);
                    this.y = x;
                    this.z = y;
                }
            }
            invalidate();
        } else {
            if (this.t == null) {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.o, this.p, Bitmap.Config.ARGB_8888);
                this.t = bitmapCreateBitmap;
                this.v = new Canvas(bitmapCreateBitmap);
                Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(this.o, this.p, Bitmap.Config.ARGB_8888);
                this.u = bitmapCreateBitmap2;
                this.w = new Canvas(bitmapCreateBitmap2);
            }
            if (this.n == 10 && this.h.size() == 0) {
                this.A = -1;
                this.j = null;
                return true;
            }
            this.y = motionEvent.getX();
            this.z = motionEvent.getY();
            com.byd.imageedit.a aVar2 = new com.byd.imageedit.a(this.m, this.k, this.l, this.n);
            this.j = aVar2;
            if (this.m && (bitmap = this.e) != null) {
                aVar2.h = bitmap;
            }
            this.h.addLast(aVar2);
            this.j.e(this.y, this.z);
        }
        return true;
    }

    public ScrawlImageView p(float f) {
        this.l = f;
        return this;
    }

    public void q() {
        if (this.h.isEmpty()) {
            return;
        }
        this.i.push(this.h.removeLast());
        this.v.drawColor(0, PorterDuff.Mode.CLEAR);
        c(this.v, this.o, this.p);
        invalidate();
        b bVar = this.B;
        if (bVar != null) {
            bVar.a(this);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        g(bitmap);
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (this.f == null && drawable != null && (drawable instanceof BitmapDrawable)) {
            g(((BitmapDrawable) drawable).getBitmap());
        }
    }

    public void setMasicBitmap(Bitmap bitmap) {
        this.e = bitmap;
        if (getDrawable() == null) {
            return;
        }
        if (bitmap != null) {
            getImageInfo();
        }
        l();
    }

    public void setOnScrawlListener(b bVar) {
        this.B = bVar;
    }
}
