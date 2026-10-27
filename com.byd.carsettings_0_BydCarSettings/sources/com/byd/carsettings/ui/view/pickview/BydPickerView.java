package com.byd.carsettings.ui.view.pickview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.core.view.ViewCompat;
import com.byd.carsettings.R;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class BydPickerView extends View {
    public boolean A;
    public int B;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public Paint K;
    public RectF L;
    public RectF M;
    public String N;
    public c O;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3305a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3306b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3307c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3308d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b f3309e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Paint f3310f;
    public Rect g;
    public Rect h;
    public GestureDetector i;
    public OverScroller j;
    public boolean k;
    public float l;
    public float m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public boolean t;
    public boolean u;
    public int[] v;
    public int[] w;
    public GradientDrawable x;
    public GradientDrawable y;
    public Layout.Alignment z;

    public class a extends GestureDetector.SimpleOnGestureListener {
        public a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            if (BydPickerView.this.m < BydPickerView.this.n || BydPickerView.this.m > BydPickerView.this.o) {
                return false;
            }
            BydPickerView.this.j.fling(0, (int) BydPickerView.this.m, 0, (int) f3, 0, 0, BydPickerView.this.n, BydPickerView.this.o, 0, BydPickerView.this.p);
            Log.d("PickerView", "fling: " + BydPickerView.this.m + ", velocityY: " + f3);
            BydPickerView.this.k = true;
            return true;
        }
    }

    public static abstract class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<BydPickerView> f3312a;

        public abstract int b();

        public abstract String c(int i);

        public void d() {
            BydPickerView bydPickerView;
            WeakReference<BydPickerView> weakReference = this.f3312a;
            if (weakReference == null || (bydPickerView = weakReference.get()) == null) {
                return;
            }
            bydPickerView.y();
            bydPickerView.n();
            if (!bydPickerView.j.isFinished()) {
                bydPickerView.j.forceFinished(true);
            }
            bydPickerView.v(0);
            bydPickerView.invalidate();
        }

        public final void e(BydPickerView bydPickerView) {
            this.f3312a = new WeakReference<>(bydPickerView);
        }
    }

    public interface c {
        void a(BydPickerView bydPickerView, int i);
    }

    public BydPickerView(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.j.computeScrollOffset()) {
            this.m = this.j.getCurrY();
            l();
            invalidate();
        } else if (this.k) {
            v(250);
            this.k = false;
        }
    }

    public b getAdapter() {
        return this.f3309e;
    }

    public int getPickMode() {
        return this.E;
    }

    public int getSelectedItemPosition() {
        return k(this.f3307c);
    }

    public final float j() {
        return (float) ((((double) this.f3306b) + 0.5d) - ((double) (this.m / this.q)));
    }

    public int k(int i) {
        if (this.f3309e.b() == 0) {
            return 0;
        }
        if (this.t) {
            if (i < 0) {
                i %= this.f3309e.b();
                if (i != 0) {
                    i += this.f3309e.b();
                }
            } else if (i >= this.f3309e.b()) {
                i %= this.f3309e.b();
            }
        }
        if (i < 0) {
            return 0;
        }
        return i >= this.f3309e.b() ? this.f3309e.b() - 1 : i;
    }

    public final void l() {
        if (this.t) {
            return;
        }
        int iB = this.f3309e.b();
        int i = this.f3306b;
        int i2 = this.q;
        int i3 = this.p;
        float f2 = (i * i2) + i3;
        float f3 = (((i - iB) + 1) * i2) - i3;
        float f4 = this.m;
        if (f4 > f2) {
            this.m = f2;
        } else if (f4 < f3) {
            this.m = f3;
        }
    }

    public final int m() {
        int iFloor = (int) Math.floor(j());
        if (!this.t) {
            iFloor = k(iFloor);
        }
        return (this.f3306b - iFloor) * this.q;
    }

    public final void n() {
        if (this.t) {
            this.n = Integer.MIN_VALUE;
            this.o = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        } else {
            int iB = this.f3309e.b() - 1;
            int i = this.f3306b;
            int i2 = this.q;
            this.n = (-(iB - i)) * i2;
            this.o = i * i2;
        }
        int i3 = this.f3306b;
        if (i3 > 3) {
            i3 = 3;
        }
        this.p = i3 * this.q;
    }

    public final void o() {
        this.m = (this.f3306b - this.f3307c) * this.q;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        b.b.g.q.g.a.a.a(this.f3309e, "adapter == null");
        if (this.f3309e.b() == 0 || this.q == 0) {
            return;
        }
        q(canvas);
        r(canvas);
        if (TextUtils.isEmpty(this.N) || this.f3307c == this.f3309e.b() - 1) {
            return;
        }
        p(canvas, this.N, this.q);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        b.b.g.q.g.a.a.a(this.f3309e, "adapter == null");
        int size = View.MeasureSpec.getSize(i2) / this.f3308d;
        this.q = size;
        int iResolveSizeAndState = View.resolveSizeAndState(((this.f3305a * 2) + 1) * size, i2, 0);
        this.f3306b = (int) Math.ceil((((16777215 & iResolveSizeAndState) / this.q) - 1) / 2.0f);
        o();
        n();
        setMeasuredDimension(i, iResolveSizeAndState);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.i.onTouchEvent(motionEvent)) {
            invalidate();
            return true;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            if (!this.j.isFinished()) {
                this.j.forceFinished(true);
            }
            this.l = motionEvent.getY();
        } else if (action != 2) {
            this.m += motionEvent.getY() - this.l;
            v(250);
        } else {
            this.m += motionEvent.getY() - this.l;
            l();
            this.l = motionEvent.getY();
        }
        invalidate();
        return true;
    }

    public final void p(Canvas canvas, String str, float f2) {
        this.f3310f.setTextSize(b.b.g.q.g.a.a.c(getContext(), (int) getContext().getResources().getDimension(R.dimen.sp_18)));
        this.f3310f.getTextBounds(str, 0, str.length(), this.h);
        this.f3310f.setTypeface(Typeface.DEFAULT);
        if (this.u) {
            while (getMeasuredWidth() < this.h.width() && this.f3310f.getTextSize() > 16.0f) {
                Paint paint = this.f3310f;
                paint.setTextSize(paint.getTextSize() - 1.0f);
                this.f3310f.getTextBounds(str, 0, str.length(), this.h);
            }
        }
        this.f3310f.setTextAlign(Paint.Align.CENTER);
        this.f3310f.setColor(this.H);
        canvas.drawText(str, (getMeasuredWidth() / 2) + b.b.g.q.g.a.a.b(getContext(), (int) getContext().getResources().getDimension(R.dimen.dp_47)), f2 + ((this.q + this.h.height()) / 2), this.f3310f);
    }

    public final void q(Canvas canvas) {
        int i;
        this.f3310f.setTypeface(Typeface.DEFAULT_BOLD);
        this.f3310f.setTextSize(this.r);
        y();
        float f2 = this.m;
        int i2 = this.f3307c;
        int i3 = this.f3306b;
        float f3 = f2 + ((i2 - i3) * r4);
        int i4 = i2 + i3 + 1;
        float f4 = f3 - this.q;
        for (int i5 = (i2 - i3) - 1; i5 <= i4; i5++) {
            int iK = k(i5);
            int iK2 = k(this.f3307c);
            if (i5 < 0) {
                if (this.t) {
                    String strC = this.f3309e.c(k(i5));
                    if (iK == iK2) {
                        this.f3310f.setColor(this.F);
                    } else {
                        this.f3310f.setColor(this.G);
                    }
                    s(canvas, strC, f4);
                }
                i = this.q;
            } else if (i5 >= this.f3309e.b()) {
                if (this.t) {
                    if (iK == iK2) {
                        this.f3310f.setColor(this.F);
                    } else {
                        this.f3310f.setColor(this.G);
                    }
                    s(canvas, this.f3309e.c(k(i5)), f4);
                }
                i = this.q;
            } else {
                String strC2 = this.f3309e.c(i5);
                if (iK == iK2) {
                    this.f3310f.setColor(this.F);
                } else {
                    this.f3310f.setColor(this.G);
                }
                s(canvas, strC2, f4);
                i = this.q;
            }
            f4 += i;
        }
    }

    public final void r(Canvas canvas) {
        this.L.set(0.0f, (int) ((this.f3306b * this.q) - getContext().getResources().getDimension(R.dimen.dp_1)), getMeasuredWidth(), this.f3306b * this.q);
        this.M.set(0.0f, (int) (((this.f3306b + 1) * this.q) + getContext().getResources().getDimension(R.dimen.dp_1)), getMeasuredWidth(), (int) (((this.f3306b + 1) * this.q) + getContext().getResources().getDimension(R.dimen.dp_2)));
        canvas.drawRect(this.L, this.K);
        canvas.drawRect(this.M, this.K);
    }

    public final void s(Canvas canvas, String str, float f2) {
        this.f3310f.setTextSize(this.r);
        this.f3310f.getTextBounds(str, 0, str.length(), this.g);
        while (true) {
            Rect rect = this.g;
            if (rect == null || rect.height() <= this.q) {
                break;
            }
            Paint paint = this.f3310f;
            paint.setTextSize(paint.getTextSize() - 1.0f);
            this.f3310f.getTextBounds(str, 0, str.length(), this.g);
        }
        if (this.u) {
            while (getMeasuredWidth() < this.g.width() && this.f3310f.getTextSize() > 16.0f) {
                Paint paint2 = this.f3310f;
                paint2.setTextSize(paint2.getTextSize() - 1.0f);
                this.f3310f.getTextBounds(str, 0, str.length(), this.g);
            }
        }
        float fHeight = f2 + ((this.q + this.g.height()) / 2);
        Layout.Alignment alignment = this.z;
        if (alignment == Layout.Alignment.ALIGN_CENTER) {
            this.f3310f.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(str, getMeasuredWidth() / 2, fHeight, this.f3310f);
        } else if (alignment == Layout.Alignment.ALIGN_OPPOSITE) {
            this.f3310f.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(str, getMeasuredWidth() - this.B, fHeight, this.f3310f);
        } else {
            this.f3310f.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(str, this.B + 0, fHeight, this.f3310f);
        }
    }

    public void setAdapter(b bVar) {
        b.b.g.q.g.a.a.a(bVar, "adapter == null");
        if (bVar.b() > ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED / this.q) {
            Log.w("PickerView", "getItemCount() is too large, unsupported yet");
        }
        bVar.e(this);
        this.f3309e = bVar;
    }

    public void setIsAnotherDay(boolean z) {
        if (this.E == 0) {
            this.A = z;
        } else {
            this.A = false;
        }
    }

    public void setItemHeight(int i) {
        this.q = i;
    }

    public void setOnSelectedItemChangedListener(c cVar) {
        this.O = cVar;
    }

    public void setPickMode(int i) {
        this.E = i;
    }

    public void setRecycle(boolean z) {
        this.t = z;
    }

    public void setSelectedItemPosition(int i, boolean z) {
        b.b.g.q.g.a.a.a(this.f3309e, "adapter must be set first");
        x(i, z);
        o();
        invalidate();
    }

    public void setUnitText(String str) {
        this.N = str;
        invalidate();
    }

    public final void t(Context context, AttributeSet attributeSet) {
        this.i = new GestureDetector(getContext(), new a());
        OverScroller overScroller = new OverScroller(getContext());
        this.j = overScroller;
        overScroller.setFriction(ViewConfiguration.getScrollFriction() * 6.0f);
        this.x = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, this.w);
        this.y = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, this.w);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BydPickerView);
        int i = typedArrayObtainStyledAttributes.getInt(6, 3);
        this.f3305a = i;
        if (i <= 0) {
            this.f3305a = 3;
        }
        int i2 = typedArrayObtainStyledAttributes.getInt(2, 3);
        this.f3308d = i2;
        if (i2 % 2 == 0) {
            this.f3308d = i2 + 1;
        }
        this.F = typedArrayObtainStyledAttributes.getColor(7, this.F);
        this.G = typedArrayObtainStyledAttributes.getColor(12, this.G);
        int iB = b.b.g.q.g.a.a.b(getContext(), 32);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, iB);
        this.q = dimensionPixelSize;
        if (dimensionPixelSize <= 0) {
            this.q = iB;
        }
        this.r = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, b.b.g.q.g.a.a.c(getContext(), 24));
        this.s = typedArrayObtainStyledAttributes.getColor(9, ViewCompat.MEASURED_STATE_MASK);
        this.H = typedArrayObtainStyledAttributes.getColor(14, ViewCompat.MEASURED_STATE_MASK);
        this.t = typedArrayObtainStyledAttributes.getBoolean(1, false);
        this.u = typedArrayObtainStyledAttributes.getBoolean(0, true);
        this.B = typedArrayObtainStyledAttributes.getDimensionPixelSize(10, 0);
        this.D = typedArrayObtainStyledAttributes.getInt(8, 0);
        this.N = typedArrayObtainStyledAttributes.getString(13);
        this.I = typedArrayObtainStyledAttributes.getColor(5, ViewCompat.MEASURED_STATE_MASK);
        this.J = typedArrayObtainStyledAttributes.getColor(4, ViewCompat.MEASURED_STATE_MASK);
        typedArrayObtainStyledAttributes.recycle();
        this.K.setColor(this.I);
        u();
    }

    public final void u() {
        Paint paint = new Paint();
        this.f3310f = paint;
        paint.setTextSize(this.r);
        int i = this.G;
        this.s = i;
        this.f3310f.setColor(i);
        this.f3310f.setAntiAlias(true);
        this.f3310f.setTypeface(Typeface.DEFAULT_BOLD);
        int i2 = this.D;
        if (i2 == 1) {
            this.z = Layout.Alignment.ALIGN_NORMAL;
        } else if (i2 == 2) {
            this.z = Layout.Alignment.ALIGN_OPPOSITE;
        } else {
            this.z = Layout.Alignment.ALIGN_CENTER;
        }
    }

    public final void v(int i) {
        int iM = m();
        Log.d("PickerView", "justify: duration = " + i + ", yOffset = " + this.m + ", finalY = " + iM);
        float f2 = (float) iM;
        float f3 = this.m;
        if (f2 != f3) {
            this.j.startScroll(0, (int) f3, 0, (int) (f2 - f3), i);
            Log.d("PickerView", "startScroll: " + this.m + ", to: " + iM);
            invalidate();
        }
    }

    public final void w(int i) {
        x(i, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void x(int r4, boolean r5) {
        /*
            r3 = this;
            int r0 = r3.k(r4)
            boolean r1 = r3.t
            r2 = 1
            if (r1 == 0) goto L10
            int r1 = r3.f3307c
            if (r1 == r4) goto L17
            r3.f3307c = r4
            goto L18
        L10:
            int r4 = r3.f3307c
            if (r4 == r0) goto L17
            r3.f3307c = r0
            goto L18
        L17:
            r2 = 0
        L18:
            if (r2 == 0) goto L21
            com.byd.carsettings.ui.view.pickview.BydPickerView$c r4 = r3.O
            if (r4 == 0) goto L21
            r4.a(r3, r0)
        L21:
            if (r2 == 0) goto L28
            if (r5 == 0) goto L28
            b.b.g.o.f.e(r3)
        L28:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.carsettings.ui.view.pickview.BydPickerView.x(int, boolean):void");
    }

    public final void y() {
        w((int) Math.floor(j()));
    }

    public BydPickerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BydPickerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3305a = 3;
        this.f3307c = 0;
        this.f3308d = 3;
        this.g = new Rect();
        this.h = new Rect();
        this.s = ViewCompat.MEASURED_STATE_MASK;
        int[] iArr = {-805635334, -1610941702, 1610283770};
        this.v = iArr;
        this.w = iArr;
        this.z = Layout.Alignment.ALIGN_CENTER;
        this.A = false;
        this.E = 0;
        this.F = Color.parseColor("#FF45A2FF");
        this.G = ViewCompat.MEASURED_STATE_MASK;
        this.K = new Paint();
        this.L = new RectF();
        this.M = new RectF();
        t(context, attributeSet);
    }

    public void setSelectedItemPosition(int i) {
        setSelectedItemPosition(i, true);
    }
}
