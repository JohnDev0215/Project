package com.king.zxing;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ViewfinderView extends View {
    private int A;
    private int B;
    private int C;
    private int D;
    private float E;
    private List<com.google.zxing.i> F;
    private List<com.google.zxing.i> G;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Paint f4542b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private TextPaint f4543c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4544d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4545e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f4546f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f4547g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f4548h;
    private float i;
    private TextLocation j;
    private String k;
    private int l;
    private float m;
    public int n;
    public int o;
    private boolean p;
    private int q;
    private int r;
    private int s;
    private int t;
    private LaserStyle u;
    private int v;
    private int w;
    private Rect x;
    private int y;
    private int z;

    public enum LaserStyle {
        NONE(0),
        LINE(1),
        GRID(2);

        private int mValue;

        LaserStyle(int i) {
            this.mValue = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static LaserStyle a(int i) {
            for (LaserStyle laserStyle : values()) {
                if (laserStyle.mValue == i) {
                    return laserStyle;
                }
            }
            return LINE;
        }
    }

    public enum TextLocation {
        TOP(0),
        BOTTOM(1);

        private int mValue;

        TextLocation(int i) {
            this.mValue = i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static TextLocation a(int i) {
            for (TextLocation textLocation : values()) {
                if (textLocation.mValue == i) {
                    return textLocation;
                }
            }
            return TOP;
        }
    }

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4551a;

        static {
            int[] iArr = new int[LaserStyle.values().length];
            f4551a = iArr;
            try {
                iArr[LaserStyle.LINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4551a[LaserStyle.GRID.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public ViewfinderView(Context context) {
        this(context, null);
    }

    private void b(Canvas canvas, Rect rect) {
        this.f4542b.setColor(this.f4547g);
        canvas.drawRect(rect.left, rect.top, r0 + this.y, r1 + this.z, this.f4542b);
        canvas.drawRect(rect.left, rect.top, r0 + this.z, r1 + this.y, this.f4542b);
        int i = rect.right;
        canvas.drawRect(i - this.y, rect.top, i, r1 + this.z, this.f4542b);
        int i2 = rect.right;
        canvas.drawRect(i2 - this.z, rect.top, i2, r1 + this.y, this.f4542b);
        canvas.drawRect(rect.left, r1 - this.y, r0 + this.z, rect.bottom, this.f4542b);
        canvas.drawRect(rect.left, r1 - this.z, r0 + this.y, rect.bottom, this.f4542b);
        int i3 = rect.right;
        canvas.drawRect(i3 - this.y, r1 - this.z, i3, rect.bottom, this.f4542b);
        int i4 = rect.right;
        canvas.drawRect(i4 - this.z, r10 - this.y, i4, rect.bottom, this.f4542b);
    }

    private void c(Canvas canvas, Rect rect, int i, int i2) {
        this.f4542b.setColor(this.f4544d);
        float f2 = i;
        canvas.drawRect(0.0f, 0.0f, f2, rect.top, this.f4542b);
        canvas.drawRect(0.0f, rect.top, rect.left, rect.bottom, this.f4542b);
        canvas.drawRect(rect.right, rect.top, f2, rect.bottom, this.f4542b);
        canvas.drawRect(0.0f, rect.bottom, f2, i2, this.f4542b);
    }

    private void d(Canvas canvas, Rect rect) {
        this.f4542b.setColor(this.f4545e);
        canvas.drawRect(rect.left, rect.top, rect.right, r0 + this.C, this.f4542b);
        canvas.drawRect(rect.left, rect.top, r0 + this.C, rect.bottom, this.f4542b);
        canvas.drawRect(r0 - this.C, rect.top, rect.right, rect.bottom, this.f4542b);
        canvas.drawRect(rect.left, r0 - this.C, rect.right, rect.bottom, this.f4542b);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void e(android.graphics.Canvas r14, android.graphics.Rect r15) {
        /*
            r13 = this;
            android.graphics.Paint r0 = r13.f4542b
            r1 = 2
            float r2 = (float) r1
            r0.setStrokeWidth(r2)
            int r0 = r13.w
            if (r0 <= 0) goto L15
            int r2 = r13.n
            int r3 = r15.top
            int r3 = r2 - r3
            if (r3 <= r0) goto L15
            int r2 = r2 - r0
            goto L17
        L15:
            int r2 = r15.top
        L17:
            android.graphics.LinearGradient r0 = new android.graphics.LinearGradient
            int r3 = r15.left
            int r4 = r15.width()
            int r4 = r4 / r1
            int r3 = r3 + r4
            float r4 = (float) r3
            float r2 = (float) r2
            int r3 = r15.left
            int r5 = r15.width()
            int r5 = r5 / r1
            int r3 = r3 + r5
            float r6 = (float) r3
            int r3 = r13.n
            float r7 = (float) r3
            int[] r8 = new int[r1]
            int r3 = r13.f4546f
            int r3 = r13.l(r3)
            r11 = 0
            r8[r11] = r3
            int r3 = r13.f4546f
            r12 = 1
            r8[r12] = r3
            float[] r9 = new float[r1]
            r9 = {x00b8: FILL_ARRAY_DATA , data: [0, 1065353216} // fill-array
            android.graphics.Shader$TileMode r10 = android.graphics.Shader.TileMode.CLAMP
            r3 = r0
            r5 = r2
            r3.<init>(r4, r5, r6, r7, r8, r9, r10)
            android.graphics.Paint r1 = r13.f4542b
            r1.setShader(r0)
            int r0 = r15.width()
            float r0 = (float) r0
            r1 = 1065353216(0x3f800000, float:1.0)
            float r0 = r0 * r1
            int r1 = r13.v
            float r1 = (float) r1
            float r0 = r0 / r1
        L5c:
            int r1 = r13.v
            if (r12 >= r1) goto L77
            int r1 = r15.left
            float r3 = (float) r1
            float r4 = (float) r12
            float r4 = r4 * r0
            float r6 = r3 + r4
            float r1 = (float) r1
            float r8 = r1 + r4
            int r1 = r13.n
            float r9 = (float) r1
            android.graphics.Paint r10 = r13.f4542b
            r5 = r14
            r7 = r2
            r5.drawLine(r6, r7, r8, r9, r10)
            int r12 = r12 + 1
            goto L5c
        L77:
            int r1 = r13.w
            if (r1 <= 0) goto L83
            int r2 = r13.n
            int r3 = r15.top
            int r2 = r2 - r3
            if (r2 <= r1) goto L83
            goto L88
        L83:
            int r1 = r13.n
            int r2 = r15.top
            int r1 = r1 - r2
        L88:
            float r2 = (float) r11
            float r3 = (float) r1
            float r3 = r3 / r0
            int r3 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r3 > 0) goto La7
            int r3 = r15.left
            float r5 = (float) r3
            int r3 = r13.n
            float r4 = (float) r3
            float r2 = r2 * r0
            float r6 = r4 - r2
            int r4 = r15.right
            float r7 = (float) r4
            float r3 = (float) r3
            float r8 = r3 - r2
            android.graphics.Paint r9 = r13.f4542b
            r4 = r14
            r4.drawLine(r5, r6, r7, r8, r9)
            int r11 = r11 + 1
            goto L88
        La7:
            int r14 = r13.n
            int r0 = r13.o
            if (r14 >= r0) goto Lb3
            int r15 = r13.A
            int r14 = r14 + r15
            r13.n = r14
            goto Lb7
        Lb3:
            int r14 = r15.top
            r13.n = r14
        Lb7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.king.zxing.ViewfinderView.e(android.graphics.Canvas, android.graphics.Rect):void");
    }

    private void f(Canvas canvas, Rect rect) {
        if (this.u != null) {
            this.f4542b.setColor(this.f4546f);
            int i = a.f4551a[this.u.ordinal()];
            if (i == 1) {
                g(canvas, rect);
            } else if (i == 2) {
                e(canvas, rect);
            }
            this.f4542b.setShader(null);
        }
    }

    private void g(Canvas canvas, Rect rect) {
        int i = rect.left;
        this.f4542b.setShader(new LinearGradient(i, this.n, i, r2 + this.B, l(this.f4546f), this.f4546f, Shader.TileMode.MIRROR));
        int i2 = this.n;
        if (i2 > this.o) {
            this.n = rect.top;
            return;
        }
        int i3 = rect.left;
        int i4 = this.B;
        canvas.drawOval(new RectF(i3 + (i4 * 2), i2, rect.right - (i4 * 2), i2 + i4), this.f4542b);
        this.n += this.A;
    }

    private DisplayMetrics getDisplayMetrics() {
        return getResources().getDisplayMetrics();
    }

    private void h(Canvas canvas, Rect rect) {
        if (this.p) {
            List<com.google.zxing.i> list = this.F;
            List<com.google.zxing.i> list2 = this.G;
            if (list.isEmpty()) {
                this.G = null;
            } else {
                this.F = new ArrayList(5);
                this.G = list;
                this.f4542b.setAlpha(160);
                this.f4542b.setColor(this.f4548h);
                synchronized (list) {
                    for (com.google.zxing.i iVar : list) {
                        canvas.drawCircle(iVar.c(), iVar.d(), 10.0f, this.f4542b);
                    }
                }
            }
            if (list2 != null) {
                this.f4542b.setAlpha(80);
                this.f4542b.setColor(this.f4548h);
                synchronized (list2) {
                    for (com.google.zxing.i iVar2 : list2) {
                        canvas.drawCircle(iVar2.c(), iVar2.d(), 10.0f, this.f4542b);
                    }
                }
            }
        }
    }

    private void i(Canvas canvas, Rect rect) {
        if (TextUtils.isEmpty(this.k)) {
            return;
        }
        this.f4543c.setColor(this.l);
        this.f4543c.setTextSize(this.m);
        this.f4543c.setTextAlign(Paint.Align.CENTER);
        StaticLayout staticLayout = new StaticLayout(this.k, this.f4543c, canvas.getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
        if (this.j == TextLocation.BOTTOM) {
            canvas.translate(rect.left + (rect.width() / 2), rect.bottom + this.i);
            staticLayout.draw(canvas);
        } else {
            canvas.translate(rect.left + (rect.width() / 2), (rect.top - this.i) - staticLayout.getHeight());
            staticLayout.draw(canvas);
        }
    }

    private void k(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, s.Q);
        this.f4544d = typedArrayObtainStyledAttributes.getColor(s.i0, androidx.core.content.a.b(context, o.f4642d));
        this.f4545e = typedArrayObtainStyledAttributes.getColor(s.U, androidx.core.content.a.b(context, o.f4640b));
        this.f4547g = typedArrayObtainStyledAttributes.getColor(s.R, androidx.core.content.a.b(context, o.f4639a));
        this.f4546f = typedArrayObtainStyledAttributes.getColor(s.g0, androidx.core.content.a.b(context, o.f4641c));
        this.f4548h = typedArrayObtainStyledAttributes.getColor(s.j0, androidx.core.content.a.b(context, o.f4643e));
        this.k = typedArrayObtainStyledAttributes.getString(s.b0);
        this.l = typedArrayObtainStyledAttributes.getColor(s.c0, androidx.core.content.a.b(context, o.f4644f));
        this.m = typedArrayObtainStyledAttributes.getDimension(s.f0, TypedValue.applyDimension(2, 14.0f, getResources().getDisplayMetrics()));
        this.i = typedArrayObtainStyledAttributes.getDimension(s.e0, TypedValue.applyDimension(1, 24.0f, getResources().getDisplayMetrics()));
        this.j = TextLocation.a(typedArrayObtainStyledAttributes.getInt(s.d0, 0));
        this.p = typedArrayObtainStyledAttributes.getBoolean(s.n0, false);
        this.s = typedArrayObtainStyledAttributes.getDimensionPixelSize(s.Y, 0);
        this.t = typedArrayObtainStyledAttributes.getDimensionPixelSize(s.V, 0);
        this.u = LaserStyle.a(typedArrayObtainStyledAttributes.getInt(s.h0, LaserStyle.LINE.mValue));
        this.v = typedArrayObtainStyledAttributes.getInt(s.Z, 20);
        this.w = (int) typedArrayObtainStyledAttributes.getDimension(s.a0, TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics()));
        this.y = (int) typedArrayObtainStyledAttributes.getDimension(s.T, TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics()));
        this.z = (int) typedArrayObtainStyledAttributes.getDimension(s.S, TypedValue.applyDimension(1, 16.0f, getResources().getDisplayMetrics()));
        this.A = (int) typedArrayObtainStyledAttributes.getDimension(s.m0, TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics()));
        this.B = (int) typedArrayObtainStyledAttributes.getDimension(s.l0, TypedValue.applyDimension(1, 5.0f, getResources().getDisplayMetrics()));
        this.C = (int) typedArrayObtainStyledAttributes.getDimension(s.W, TypedValue.applyDimension(1, 1.0f, getResources().getDisplayMetrics()));
        this.D = typedArrayObtainStyledAttributes.getInteger(s.k0, 15);
        this.E = typedArrayObtainStyledAttributes.getFloat(s.X, 0.625f);
        typedArrayObtainStyledAttributes.recycle();
        this.f4542b = new Paint(1);
        this.f4543c = new TextPaint(1);
        this.F = new ArrayList(5);
        this.G = null;
        this.q = getDisplayMetrics().widthPixels;
        this.r = getDisplayMetrics().heightPixels;
        int iMin = (int) (Math.min(this.q, r4) * this.E);
        int i = this.s;
        if (i <= 0 || i > this.q) {
            this.s = iMin;
        }
        int i2 = this.t;
        if (i2 <= 0 || i2 > this.r) {
            this.t = iMin;
        }
    }

    public void a(com.google.zxing.i iVar) {
        if (this.p) {
            List<com.google.zxing.i> list = this.F;
            synchronized (list) {
                list.add(iVar);
                int size = list.size();
                if (size > 20) {
                    list.subList(0, size - 10).clear();
                }
            }
        }
    }

    public void j() {
        invalidate();
    }

    public int l(int i) {
        return Integer.valueOf("01" + Integer.toHexString(i).substring(2), 16).intValue();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Rect rect = this.x;
        if (rect == null) {
            return;
        }
        if (this.n == 0 || this.o == 0) {
            this.n = rect.top;
            this.o = rect.bottom - this.B;
        }
        c(canvas, this.x, canvas.getWidth(), canvas.getHeight());
        f(canvas, this.x);
        d(canvas, this.x);
        b(canvas, this.x);
        i(canvas, this.x);
        h(canvas, this.x);
        long j = this.D;
        Rect rect2 = this.x;
        postInvalidateDelayed(j, rect2.left - 20, rect2.top - 20, rect2.right + 20, rect2.bottom + 20);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int paddingLeft = (((this.q - this.s) / 2) + getPaddingLeft()) - getPaddingRight();
        int paddingTop = (((this.r - this.t) / 2) + getPaddingTop()) - getPaddingBottom();
        this.x = new Rect(paddingLeft, paddingTop, this.s + paddingLeft, this.t + paddingTop);
    }

    public void setLabelText(String str) {
        this.k = str;
    }

    public void setLabelTextColor(int i) {
        this.l = i;
    }

    public void setLabelTextColorResource(int i) {
        this.l = androidx.core.content.a.b(getContext(), i);
    }

    public void setLabelTextSize(float f2) {
        this.m = f2;
    }

    public void setLaserStyle(LaserStyle laserStyle) {
        this.u = laserStyle;
    }

    public void setShowResultPoint(boolean z) {
        this.p = z;
    }

    public ViewfinderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ViewfinderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.n = 0;
        this.o = 0;
        k(context, attributeSet);
    }
}
