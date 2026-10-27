package com.byd.imageedit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import b.a.j.b;
import com.byd.drawable.CorrectDrawable;

/* JADX INFO: loaded from: classes2.dex */
public class CropImageView extends ImageView {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Rect f2862a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f2863b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2864c;
    private Matrix d;
    private Matrix e;
    private int f;
    private int g;
    private Matrix h;
    private int i;
    private int j;
    private CorrectDrawable k;
    private boolean l;
    private boolean m;
    private int n;
    private boolean o;
    private boolean p;
    private int q;
    private int r;
    private Point s;
    private WindowManager t;
    private boolean u;
    private boolean v;
    private Point w;
    private float x;
    private int y;
    private a z;

    public interface a {
        void a(float f, float f2, int i);

        void b();
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = -1;
        this.j = -1;
        this.o = true;
        this.s = new Point();
        this.v = true;
        c(context);
    }

    private boolean a() {
        if (!this.u) {
            return true;
        }
        int i = getResources().getConfiguration().orientation;
        Point point = this.s;
        return point.x > point.y ? i == 2 : i == 1;
    }

    private void c(Context context) {
        this.t = (WindowManager) context.getSystemService("window");
        setLayerType(1, null);
    }

    private void d() {
        if (this.w == null) {
            getImageInfo();
            this.w = new Point(this.f2862a.centerX(), this.f2862a.centerY());
        }
    }

    private void e() {
        if (this.e == null) {
            Matrix matrix = new Matrix();
            this.e = matrix;
            matrix.set(getImageMatrix());
        }
    }

    private Bitmap getFromCropShape() {
        Rect rectQ = this.f2863b.q();
        Rect bounds = getDrawable().getBounds();
        float[] fArr = new float[9];
        getImageMatrix().getValues(fArr);
        int paddingLeft = (int) (fArr[2] + getPaddingLeft());
        int paddingTop = (int) (fArr[5] + getPaddingTop());
        if (paddingLeft != 0 || paddingTop != 0) {
            rectQ.offset(-paddingLeft, -paddingTop);
        }
        k(rectQ, bounds, fArr[0], fArr[4]);
        Log.e("m_tag", bounds.width() + " x " + bounds.height() + " scaleWidth:" + fArr[0] + " scaleHeight:" + fArr[4] + " " + rectQ);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bounds.width(), bounds.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        if (getDrawable() != null) {
            Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
            Paint paint = new Paint(1);
            paint.setAntiAlias(true);
            Path pathS = this.f2863b.s();
            Matrix matrix = new Matrix();
            if (paddingLeft != 0 || paddingTop != 0) {
                matrix.postTranslate(-paddingLeft, -paddingTop);
            }
            matrix.postScale(1.0f / fArr[0], 1.0f / fArr[4]);
            pathS.transform(matrix);
            canvas.drawPath(pathS, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        } else {
            draw(canvas);
        }
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapCreateBitmap, rectQ.left, rectQ.top, rectQ.width(), rectQ.height());
        bitmapCreateBitmap.recycle();
        return bitmapCreateBitmap2;
    }

    private Rect getImageInfo() {
        if (this.d == null) {
            Matrix matrix = new Matrix();
            this.d = matrix;
            matrix.set(getImageMatrix());
        }
        if (this.h == null) {
            Matrix matrix2 = new Matrix();
            this.h = matrix2;
            matrix2.set(this.d);
        }
        Rect bounds = getDrawable().getBounds();
        float[] fArr = new float[9];
        getImageMatrix().getValues(fArr);
        float fWidth = bounds.width() * fArr[0];
        float fHeight = bounds.height() * fArr[4];
        float paddingLeft = fArr[2] + getPaddingLeft();
        float paddingTop = fArr[5] + getPaddingTop();
        float f = fWidth + paddingLeft;
        float f2 = fHeight + paddingTop;
        Rect rect = this.f2862a;
        if (rect == null) {
            this.f2862a = new Rect((int) paddingLeft, (int) paddingTop, (int) f, (int) f2);
        } else {
            rect.set((int) paddingLeft, (int) paddingTop, (int) f, (int) f2);
        }
        if (this.f == 0 || this.g == 0) {
            this.f = this.f2862a.width();
            this.g = this.f2862a.height();
        }
        int iCenterX = this.f2862a.centerX();
        int iCenterY = this.f2862a.centerY();
        Point point = this.w;
        if (point == null) {
            this.w = new Point(iCenterX, iCenterY);
        } else {
            point.set(iCenterX, iCenterY);
        }
        Log.e("m_tag", "getImageInfo : " + this.f2862a + " " + this.w + " " + bounds);
        return this.f2862a;
    }

    private void h() {
        a aVar = this.z;
        if (aVar != null) {
            b bVar = this.f2863b;
            if (bVar != null) {
                aVar.a(this.x, bVar.v(), this.y);
            } else {
                aVar.a(this.x, 0.0f, this.y);
            }
        }
    }

    private void k(Rect rect, Rect rect2, float f, float f2) {
        int i = (int) (rect.left / f);
        int i2 = (int) (rect.top / f2);
        int i3 = (int) (rect.right / f);
        int i4 = (int) (rect.bottom / f2);
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        int i5 = rect2.right;
        if (i3 > i5) {
            i3 = i5;
        }
        int i6 = rect2.bottom;
        if (i4 > i6) {
            i4 = i6;
        }
        rect.set(i, i2, i3, i4);
    }

    public void b() {
        this.f2863b = null;
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        a aVar;
        b bVar = this.f2863b;
        if (bVar == null) {
            CorrectDrawable correctDrawable = this.k;
            if (correctDrawable != null) {
                return correctDrawable.dispatchTouchEvent(motionEvent);
            }
            return true;
        }
        bVar.x(motionEvent);
        if ((motionEvent.getAction() == 1 || motionEvent.getAction() == 3) && (aVar = this.z) != null) {
            int i = this.f2864c;
            if (i == 1 || i == 2) {
                aVar.b();
            } else if (i == 3 && this.f2863b.v() != 0.0f) {
                h();
            }
        }
        return true;
    }

    public void f() {
        this.e.set(this.h);
    }

    public void g() {
        Log.e("m_tag", "CropImageView reset " + hashCode());
        Matrix matrix = this.d;
        if (matrix != null) {
            setImageMatrix(matrix);
            Matrix matrix2 = this.e;
            if (matrix2 != null) {
                matrix2.set(this.d);
            }
            Matrix matrix3 = this.h;
            if (matrix3 != null) {
                matrix3.set(this.d);
            }
        }
        CorrectDrawable correctDrawable = this.k;
        if (correctDrawable != null) {
            correctDrawable.reset();
            this.k = null;
        }
        this.x = 0.0f;
        this.y = 0;
        b bVar = this.f2863b;
        if (bVar != null) {
            int i = this.f;
            int i2 = this.g;
            Point point = this.w;
            bVar.H(i, i2, point.x, point.y);
            this.f2863b.G(0.0f);
        }
        invalidate();
    }

    public Bitmap getBitmap() {
        float f;
        int i;
        Bitmap bitmapCreateBitmap;
        b bVar;
        int i2 = this.f2864c;
        if (i2 != 3) {
            if (i2 == 10) {
                return this.k.createCorrectBitmap();
            }
            this.f2863b.z(false);
            Bitmap fromCropShape = getFromCropShape();
            this.f2863b.z(true);
            return fromCropShape;
        }
        if (this.x == 0.0f && this.y == 0 && ((bVar = this.f2863b) == null || bVar.v() == 0.0f)) {
            return ((BitmapDrawable) getDrawable()).getBitmap();
        }
        if (getDrawable() != null) {
            float fAbs = Math.abs(this.x);
            Rect bounds = getDrawable().getBounds();
            int iWidth = bounds.width();
            int iHeight = bounds.height();
            Matrix matrix = new Matrix();
            int i3 = iWidth >> 1;
            int i4 = iHeight >> 1;
            if (fAbs == 90.0f || fAbs == 270.0f) {
                int iWidth2 = bounds.width();
                matrix.postTranslate((iHeight / 2) - i3, (iWidth2 / 2) - i4);
                int i5 = iWidth2 >> 1;
                matrix.postRotate(this.x, i4, i5);
                iWidth = iHeight;
                i3 = i4;
                iHeight = iWidth2;
                i4 = i5;
            } else if (fAbs == 180.0f) {
                matrix.postScale(-1.0f, -1.0f, i3, i4);
            }
            int i6 = this.y;
            if (i6 != 0) {
                if (i6 == 1) {
                    matrix.postScale(-1.0f, 1.0f, i3, i4);
                } else if (i6 == 2) {
                    matrix.postScale(1.0f, -1.0f, i3, i4);
                } else if (i6 == 3) {
                    matrix.postScale(-1.0f, -1.0f, i3, i4);
                }
            }
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(iWidth, iHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap2);
            float fV = this.f2863b.v();
            if (fV != 0.0f) {
                float fW = this.f2863b.w(fV);
                Log.e("m_tag", "cx : " + i3 + " cy : " + i4 + " scale : " + fW + " degrees : " + fV);
                float f2 = (float) i3;
                float f3 = (float) i4;
                matrix.postScale(fW, fW, f2, f3);
                matrix.postRotate(fV, f2, f3);
            }
            canvas.drawBitmap(((BitmapDrawable) getDrawable()).getBitmap(), matrix, null);
            return bitmapCreateBitmap2;
        }
        this.f2863b.z(false);
        Bitmap bitmapCreateBitmap3 = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        draw(new Canvas(bitmapCreateBitmap3));
        this.f2863b.z(true);
        if (this.f2863b.v() != 0.0f) {
            Rect rectQ = this.f2863b.q();
            bitmapCreateBitmap = Bitmap.createBitmap(bitmapCreateBitmap3, rectQ.left, rectQ.top, rectQ.width(), rectQ.height());
        } else {
            if (Math.abs(this.x) == 90.0f || Math.abs(this.x) == 270.0f) {
                f = this.g;
                i = this.f;
            } else {
                f = this.f;
                i = this.g;
            }
            float f4 = i;
            float width = getWidth() / f;
            float height = (getHeight() - getPaddingBottom()) / f4;
            Log.e("m_tag", "--> w:" + f + " h:" + f4 + " scaleX:" + width + " scaleY:" + height);
            if (width < 1.0f || height < 1.0f) {
                if (width >= height) {
                    width = height;
                }
                f *= width;
                f4 *= width;
            }
            float fCenterX = this.f2862a.centerX() - (f / 2.0f);
            float fCenterY = this.f2862a.centerY() - (f4 / 2.0f);
            Log.e("m_tag", "==>" + this.f2862a.toShortString());
            Log.e("m_tag", "degrees:" + this.x + " x:" + fCenterX + " y:" + fCenterY + " w:" + f + " h:" + f4 + " " + this.f + " " + this.g);
            if (fCenterX < 0.0f) {
                fCenterX = 0.0f;
            }
            bitmapCreateBitmap = Bitmap.createBitmap(bitmapCreateBitmap3, (int) fCenterX, (int) (fCenterY >= 0.0f ? fCenterY : 0.0f), (int) f, (int) f4);
        }
        bitmapCreateBitmap3.recycle();
        return bitmapCreateBitmap;
    }

    public int getCropType() {
        return this.f2864c;
    }

    public float getDegree() {
        return this.x;
    }

    public int getMirrorType() {
        return this.y;
    }

    public Matrix getStateMatrix() {
        return this.e;
    }

    public float getSwapDegrees() {
        b bVar = this.f2863b;
        if (bVar != null) {
            return bVar.v();
        }
        return 0.0f;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x007d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean i(java.lang.String r9) throws java.lang.Throwable {
        /*
            r8 = this;
            java.lang.String r0 = "m_tag"
            android.graphics.Bitmap r1 = r8.getBitmap()
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            r3 = 0
            java.io.File r4 = new java.io.File     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            r4.<init>(r9)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            r5.<init>()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            java.lang.String r6 = "crop save path:"
            r5.append(r6)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            r5.append(r9)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            java.lang.String r9 = r5.toString()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            android.util.Log.e(r0, r9)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            r9.<init>(r4)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4c
            int r3 = r8.f2864c     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L79
            r4 = 2
            if (r3 == r4) goto L31
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L79
            goto L33
        L31:
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.PNG     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L79
        L33:
            r4 = 100
            r1.compress(r3, r4, r9)     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L79
            r9.flush()     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L79
            r0 = 1
            r9.close()     // Catch: java.io.IOException -> L40
            goto L44
        L40:
            r9 = move-exception
            r9.printStackTrace()
        L44:
            r1.recycle()
            return r0
        L48:
            r3 = move-exception
            goto L50
        L4a:
            r0 = move-exception
            goto L7b
        L4c:
            r9 = move-exception
            r7 = r3
            r3 = r9
            r9 = r7
        L50:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L79
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L79
            r4.<init>()     // Catch: java.lang.Throwable -> L79
            java.lang.String r5 = "crop save exception : "
            r4.append(r5)     // Catch: java.lang.Throwable -> L79
            java.lang.String r3 = r3.getMessage()     // Catch: java.lang.Throwable -> L79
            r4.append(r3)     // Catch: java.lang.Throwable -> L79
            java.lang.String r3 = r4.toString()     // Catch: java.lang.Throwable -> L79
            android.util.Log.e(r0, r3)     // Catch: java.lang.Throwable -> L79
            if (r9 == 0) goto L75
            r9.close()     // Catch: java.io.IOException -> L71
            goto L75
        L71:
            r9 = move-exception
            r9.printStackTrace()
        L75:
            r1.recycle()
            return r2
        L79:
            r0 = move-exception
            r3 = r9
        L7b:
            if (r3 == 0) goto L85
            r3.close()     // Catch: java.io.IOException -> L81
            goto L85
        L81:
            r9 = move-exception
            r9.printStackTrace()
        L85:
            r1.recycle()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.imageedit.CropImageView.i(java.lang.String):boolean");
    }

    public void j(int i, int i2) {
        this.i = i;
        this.j = i2;
        int i3 = this.f2864c;
        if (i3 == 0 || i3 == 3) {
            return;
        }
        if (i3 == 10) {
            setCropType(1);
            this.f2863b = new b(this, false);
            getImageInfo();
            b bVar = this.f2863b;
            Rect rect = this.f2862a;
            Point point = this.w;
            bVar.k(rect, i, i2, point.x, point.y);
        }
        if (this.f2862a == null) {
            getImageInfo();
        }
        b bVar2 = this.f2863b;
        Rect rect2 = this.f2862a;
        Point point2 = this.w;
        bVar2.k(rect2, i, i2, point2.x, point2.y);
        setImageMatrix(this.d);
        this.x = 0.0f;
        this.y = 0;
        invalidate();
        a aVar = this.z;
        if (aVar != null) {
            aVar.b();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e("m_tag", "CropImageView onDetachedFromWindow " + hashCode());
        b();
        this.d = null;
        this.h = null;
        this.e = null;
        CorrectDrawable correctDrawable = this.k;
        if (correctDrawable != null) {
            correctDrawable.reset();
            this.k = null;
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        b bVar;
        int paddingLeft;
        if (a()) {
            if (this.e != null) {
                getImageMatrix().set(this.e);
            }
            super.onDraw(canvas);
            if (getDrawable() == null) {
                return;
            }
            if (this.e == null) {
                e();
            }
            int i = this.f2864c;
            if (i == 0) {
                d();
                return;
            }
            if (i == 10) {
                if (this.k == null) {
                    this.k = new CorrectDrawable();
                    getImageInfo();
                    this.k.init(this, ((BitmapDrawable) getDrawable()).getBitmap(), this.f2862a);
                }
                this.k.draw(canvas);
                return;
            }
            if (this.f2863b == null) {
                Log.e("m_tag", "CropImageView init cropShape --draw " + hashCode());
                int i2 = this.f2864c;
                if (i2 == 1) {
                    bVar = new b(this, false);
                } else if (i2 != 2) {
                    if (i2 == 3) {
                        b bVar2 = new b(this, true);
                        this.f2863b = bVar2;
                        bVar2.E(this.l);
                        this.f2863b.B(this.m);
                        this.f2863b.F(this.n);
                        this.f2863b.C(this.o);
                    }
                    getImageInfo();
                    this.f2863b.D(this.v);
                    paddingLeft = getPaddingLeft();
                    int paddingTop = getPaddingTop();
                    if (paddingLeft >= 10 && paddingTop >= 10) {
                        this.f2863b.A(true);
                    }
                    b bVar3 = this.f2863b;
                    Rect rect = this.f2862a;
                    int i3 = this.i;
                    int i4 = this.j;
                    Point point = this.w;
                    bVar3.k(rect, i3, i4, point.x, point.y);
                } else {
                    bVar = new b.a.j.a(this);
                }
                this.f2863b = bVar;
                getImageInfo();
                this.f2863b.D(this.v);
                paddingLeft = getPaddingLeft();
                int paddingTop2 = getPaddingTop();
                if (paddingLeft >= 10) {
                    this.f2863b.A(true);
                }
                b bVar32 = this.f2863b;
                Rect rect2 = this.f2862a;
                int i32 = this.i;
                int i42 = this.j;
                Point point2 = this.w;
                bVar32.k(rect2, i32, i42, point2.x, point2.y);
            }
            this.f2863b.p(canvas);
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.q = getMeasuredWidth();
        this.r = getMeasuredHeight();
        Log.e("m_tag", "CropImageView onMeasure : " + this.q + " " + this.r);
        this.t.getDefaultDisplay().getRealSize(this.s);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.t.getDefaultDisplay().getRealSize(this.s);
        if (!a()) {
            Log.e("m_tag", "checkOrientation fail " + hashCode());
            return;
        }
        if (this.p) {
            return;
        }
        Log.e("m_tag", "CropImageView onSizeChanged : " + i + " " + i2 + " " + i3 + " " + i4 + " " + hashCode());
        if (this.f2863b != null) {
            getImageInfo();
            b bVar = this.f2863b;
            Rect rect = this.f2862a;
            int i5 = this.i;
            int i6 = this.j;
            Point point = this.w;
            bVar.k(rect, i5, i6, point.x, point.y);
        }
    }

    public void setCropType(int i) {
        if (this.f2864c != i) {
            this.f2864c = i;
            this.f2863b = null;
            g();
        }
    }

    public void setDegree(float f) {
        b bVar;
        int i;
        int i2;
        float f2 = f % 360.0f;
        if (f2 < 0.0f) {
            f2 += 360.0f;
        }
        if ((f2 > 0.0f && f2 <= 45.0f) || (f2 > 315.0f && f2 <= 360.0f)) {
            f2 = 0.0f;
        } else if (f2 > 45.0f && f2 <= 135.0f) {
            f2 = 90.0f;
        } else if (f2 > 135.0f && f2 <= 225.0f) {
            f2 = 180.0f;
        } else if (f2 > 225.0f && f2 <= 315.0f) {
            f2 = 270.0f;
        }
        if (this.x != f2) {
            this.e.set(this.d);
            d();
            if (this.f2863b != null) {
                float fAbs = Math.abs(f2);
                if (fAbs == 90.0f || fAbs == 270.0f) {
                    bVar = this.f2863b;
                    i = this.g;
                    i2 = this.f;
                } else if (fAbs == 180.0f || (this.x != 0.0f && f2 == 0.0f)) {
                    bVar = this.f2863b;
                    i = this.f;
                    i2 = this.g;
                }
                Point point = this.w;
                bVar.H(i, i2, point.x, point.y);
            }
            Log.e("m_tag", "setDegrees : " + f2);
            if (f2 != 0.0f) {
                this.h.set(this.d);
                Matrix matrix = this.h;
                Point point2 = this.w;
                matrix.postRotate(f2, point2.x, point2.y);
                Matrix matrix2 = this.e;
                Point point3 = this.w;
                matrix2.postRotate(f2, point3.x, point3.y);
            } else {
                this.h.set(this.d);
            }
            int i3 = this.y;
            if (i3 == 1) {
                Matrix matrix3 = this.h;
                Point point4 = this.w;
                matrix3.postScale(-1.0f, 1.0f, point4.x, point4.y);
                Matrix matrix4 = this.e;
                Point point5 = this.w;
                matrix4.postScale(-1.0f, 1.0f, point5.x, point5.y);
            } else if (i3 == 2) {
                Matrix matrix5 = this.h;
                Point point6 = this.w;
                matrix5.postScale(1.0f, -1.0f, point6.x, point6.y);
                Matrix matrix6 = this.e;
                Point point7 = this.w;
                matrix6.postScale(1.0f, -1.0f, point7.x, point7.y);
            } else if (i3 == 3) {
                Matrix matrix7 = this.h;
                Point point8 = this.w;
                matrix7.postScale(-1.0f, -1.0f, point8.x, point8.y);
                Matrix matrix8 = this.e;
                Point point9 = this.w;
                matrix8.postScale(-1.0f, -1.0f, point9.x, point9.y);
            }
            b bVar2 = this.f2863b;
            if (bVar2 != null && bVar2.v() != 0.0f) {
                this.f2863b.u(r0.v());
                Matrix matrix9 = this.e;
                float fV = this.f2863b.v();
                Point point10 = this.w;
                matrix9.postRotate(fV, point10.x, point10.y);
                Log.e("m_tag", "postRotate cropShape degrees: " + f2);
            }
            if (Math.abs(f2) == 90.0f || Math.abs(f2) == 270.0f) {
                float width = getWidth() / this.g;
                float height = (getHeight() - getPaddingBottom()) / this.f;
                if (width < 1.0f || height < 1.0f) {
                    if (width >= height) {
                        width = height;
                    }
                    Matrix matrix10 = this.h;
                    Point point11 = this.w;
                    matrix10.postScale(width, width, point11.x, point11.y);
                    Matrix matrix11 = this.e;
                    Point point12 = this.w;
                    matrix11.postScale(width, width, point12.x, point12.y);
                    Log.e("m_tag", "postScale scale: " + width);
                }
            }
            this.x = f2;
            invalidate();
            h();
        }
    }

    public void setGradientScalePlate(boolean z) {
        b bVar;
        this.m = z;
        if (this.f2864c != 3 || (bVar = this.f2863b) == null) {
            return;
        }
        bVar.B(z);
    }

    public void setHasShadeOutside(boolean z) {
        b bVar;
        this.o = z;
        if (this.f2864c != 3 || (bVar = this.f2863b) == null) {
            return;
        }
        bVar.C(z);
    }

    public void setInnerLine(boolean z) {
        this.v = z;
        b bVar = this.f2863b;
        if (bVar != null) {
            bVar.D(z);
        }
    }

    public void setLineScalePlate(boolean z) {
        b bVar;
        this.l = z;
        if (this.f2864c != 3 || (bVar = this.f2863b) == null) {
            return;
        }
        bVar.E(z);
    }

    public void setMirror(int i) {
        d();
        if (i == 1) {
            Matrix matrix = this.e;
            Point point = this.w;
            matrix.postScale(-1.0f, 1.0f, point.x, point.y);
            Matrix matrix2 = this.h;
            Point point2 = this.w;
            matrix2.postScale(-1.0f, 1.0f, point2.x, point2.y);
            b bVar = this.f2863b;
            if (bVar != null && bVar.v() != 0.0f) {
                b bVar2 = this.f2863b;
                bVar2.G(-bVar2.v());
            }
        } else if (i == 2) {
            Matrix matrix3 = this.e;
            Point point3 = this.w;
            matrix3.postScale(1.0f, -1.0f, point3.x, point3.y);
            Matrix matrix4 = this.h;
            Point point4 = this.w;
            matrix4.postScale(1.0f, -1.0f, point4.x, point4.y);
        }
        this.y = i ^ this.y;
        invalidate();
        h();
    }

    public void setOnCropChange(a aVar) {
        this.z = aVar;
    }

    public void setScalePlateColor(int i) {
        b bVar;
        this.n = i;
        if (this.f2864c != 3 || (bVar = this.f2863b) == null) {
            return;
        }
        bVar.F(i);
    }

    public void setSizeAfterOrientation(boolean z) {
        this.u = z;
    }

    public void setUiStop(boolean z) {
        this.p = z;
    }
}
