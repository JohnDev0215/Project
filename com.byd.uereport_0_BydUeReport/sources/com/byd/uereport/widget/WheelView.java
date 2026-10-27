package com.byd.uereport.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;
import com.byd.uereport.R;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class WheelView extends View {
    public int A;
    public int B;
    public int C;
    public boolean D;
    public float E;
    public c F;
    public TextPaint G;
    public Rect H;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f3429d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f3430e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Paint f3431f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3432g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f3433h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f3434i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f3435j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f3436k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f3437l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3438m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f3439n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f3440o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f3441p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f3442q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public d f3443r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f3444s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f3445t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public GestureDetector f3446u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public OverScroller f3447v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f3448w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public float f3449x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public float f3450y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f3451z;

    public class a extends GestureDetector.SimpleOnGestureListener {
        public a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f8, float f9) {
            if (WheelView.this.f3450y < WheelView.this.f3451z || WheelView.this.f3450y > WheelView.this.A) {
                return false;
            }
            int i8 = (int) (f9 * WheelView.this.E);
            if (i8 > 1500) {
                i8 = 1500;
            }
            WheelView.this.f3447v.fling(0, (int) WheelView.this.f3450y, 0, i8 < -1500 ? -1500 : i8, 0, 0, WheelView.this.f3451z, WheelView.this.A, 0, WheelView.this.B);
            WheelView.this.f3448w = true;
            return true;
        }
    }

    public class b extends c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ List f3453b;

        public b(List list) {
            this.f3453b = list;
        }

        @Override // com.byd.uereport.widget.WheelView.c
        public int b() {
            return this.f3453b.size();
        }

        @Override // com.byd.uereport.widget.WheelView.c
        public String c(int i8) {
            return (String) this.f3453b.get(i8);
        }
    }

    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<WheelView> f3455a;

        public abstract int b();

        public abstract String c(int i8);

        public final void d(WheelView wheelView) {
            this.f3455a = new WeakReference<>(wheelView);
        }
    }

    public interface d {
        void a(View view, int i8, String str);
    }

    public WheelView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f3432g = 253698854;
        this.f3433h = getResources().getDimensionPixelSize(R.dimen.dp_1);
        this.f3434i = getResources().getDimensionPixelSize(R.dimen.sp_24);
        this.f3435j = getResources().getDimensionPixelSize(R.dimen.sp_24);
        this.f3436k = getResources().getDimensionPixelSize(R.dimen.dp_64);
        this.f3437l = -1;
        this.f3438m = 3;
        this.f3439n = 1;
        this.f3440o = -1943655117;
        this.f3441p = -15752970;
        this.f3442q = 48.0f;
        this.f3444s = true;
        this.f3445t = true;
        this.C = 0;
        this.D = true;
        this.E = 0.5f;
        this.H = new Rect();
        r(context, attributeSet);
        s();
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.f3447v.computeScrollOffset()) {
            this.f3450y = this.f3447v.getCurrY();
            j();
            invalidate();
        } else if (this.f3448w) {
            t(250);
            this.f3448w = false;
        }
    }

    public c getAdapter() {
        return this.F;
    }

    public int getListSize() {
        c cVar = this.F;
        if (cVar == null) {
            return 0;
        }
        return cVar.b();
    }

    public int getSelected() {
        if (this.F == null) {
            return 0;
        }
        return i(this.C);
    }

    public String getSelectedText() {
        if (this.F == null) {
            return "";
        }
        return this.F.c(i(this.C));
    }

    public final float h() {
        return (float) ((((double) this.f3439n) + 0.5d) - ((double) (this.f3450y / this.f3436k)));
    }

    public int i(int i8) {
        if (this.F.b() == 0) {
            return 0;
        }
        int iB = this.F.b();
        if (this.D) {
            if (i8 < 0) {
                i8 %= iB;
                if (i8 != 0) {
                    i8 += iB;
                }
            } else if (i8 >= iB) {
                i8 %= iB;
            }
        }
        if (i8 < 0) {
            return 0;
        }
        return i8 >= iB ? iB - 1 : i8;
    }

    public final void j() {
        if (this.D) {
            return;
        }
        int iB = this.F.b();
        int i8 = this.f3439n;
        int i9 = this.f3436k;
        int i10 = this.B;
        float f8 = (i8 * i9) + i10;
        float f9 = (((i8 - iB) + 1) * i9) - i10;
        float f10 = this.f3450y;
        if (f10 > f8) {
            this.f3450y = f8;
        } else if (f10 < f9) {
            this.f3450y = f9;
        }
    }

    public final int k() {
        int iFloor = (int) Math.floor(h());
        if (!this.D) {
            iFloor = i(iFloor);
        }
        return (this.f3439n - iFloor) * this.f3436k;
    }

    public final void l() {
        if (this.D) {
            this.f3451z = Integer.MIN_VALUE;
            this.A = Integer.MAX_VALUE;
        } else {
            int iB = this.F.b() - 1;
            int i8 = this.f3439n;
            int i9 = this.f3436k;
            this.f3451z = (-(iB - i8)) * i9;
            this.A = i8 * i9;
        }
        int i10 = this.f3439n;
        if (i10 > 3) {
            i10 = 3;
        }
        this.B = i10 * this.f3436k;
    }

    public final void m() {
        this.f3450y = (this.f3439n - this.C) * this.f3436k;
    }

    public final void n(Canvas canvas) {
        int i8;
        v();
        float f8 = this.f3450y;
        int i9 = this.C;
        int i10 = this.f3439n;
        float f9 = f8 + ((i9 - i10) * r4);
        int i11 = i9 + i10 + 1;
        float f10 = f9 - this.f3436k;
        for (int i12 = (i9 - i10) - 1; i12 <= i11; i12++) {
            int i13 = i(i12);
            boolean z7 = i13 == i(this.C);
            String strC = this.F.c(i13);
            if (i12 < 0) {
                if (this.D) {
                    q(canvas, strC, f10, z7);
                }
                i8 = this.f3436k;
            } else if (i12 >= this.F.b()) {
                if (this.D) {
                    q(canvas, strC, f10, z7);
                }
                i8 = this.f3436k;
            } else {
                q(canvas, strC, f10, z7);
                i8 = this.f3436k;
            }
            f10 += i8;
        }
    }

    public final void o(Canvas canvas) {
        float f8 = this.f3430e;
        int i8 = this.f3436k;
        float f9 = this.f3433h;
        canvas.drawLine(0.0f, ((f8 / 2.0f) - (i8 / 2.0f)) + f9, this.f3429d, ((f8 / 2.0f) - (i8 / 2.0f)) + f9, this.f3431f);
        float f10 = this.f3430e;
        int i9 = this.f3436k;
        float f11 = this.f3433h;
        canvas.drawLine(0.0f, ((f10 / 2.0f) + (i9 / 2.0f)) - f11, this.f3429d, ((f10 / 2.0f) + (i9 / 2.0f)) - f11, this.f3431f);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        c cVar = this.F;
        if (cVar == null || cVar.b() == 0 || this.f3436k == 0) {
            return;
        }
        o(canvas);
        n(canvas);
        p(canvas);
    }

    @Override // android.view.View
    public void onMeasure(int i8, int i9) {
        super.onMeasure(i8, i9);
        this.f3429d = getMeasuredWidth();
        l();
        if (this.f3429d != 0.0f) {
            setMeasuredDimension(getMeasuredWidth(), this.f3438m * this.f3436k);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f3446u.onTouchEvent(motionEvent)) {
            invalidate();
            return true;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            if (!this.f3447v.isFinished()) {
                this.f3447v.forceFinished(true);
            }
            this.f3449x = motionEvent.getY();
        } else if (action != 2) {
            this.f3450y += motionEvent.getY() - this.f3449x;
            t(250);
        } else {
            this.f3450y += (motionEvent.getY() - this.f3449x) * 2.5f;
            j();
            this.f3449x = motionEvent.getY();
        }
        invalidate();
        return true;
    }

    public final void p(Canvas canvas) {
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, this.f3442q, 15921906, 15921906, Shader.TileMode.MIRROR);
        Paint paint = new Paint();
        paint.setShader(linearGradient);
        canvas.drawRect(0.0f, 0.0f, this.f3429d, this.f3442q, paint);
        float f8 = this.f3430e;
        LinearGradient linearGradient2 = new LinearGradient(0.0f, f8 - this.f3442q, 0.0f, f8, 15921906, 15921906, Shader.TileMode.MIRROR);
        Paint paint2 = new Paint();
        paint2.setShader(linearGradient2);
        float f9 = this.f3430e;
        canvas.drawRect(0.0f, f9 - this.f3442q, this.f3429d, f9, paint2);
    }

    public final void q(Canvas canvas, String str, float f8, boolean z7) {
        this.G.setTypeface(z7 ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        this.G.setTextAlign(Paint.Align.CENTER);
        this.G.setColor(z7 ? this.f3441p : this.f3440o);
        this.G.setTextSize(z7 ? this.f3435j : this.f3434i);
        this.G.getTextBounds(str, 0, str.length(), this.H);
        float fHeight = f8 + ((this.f3436k + this.H.height()) / 2.0f);
        int i8 = this.f3437l;
        if (i8 == -1) {
            canvas.drawText(str, this.f3429d / 2.0f, fHeight, this.G);
        } else {
            canvas.drawText(str, i8, fHeight, this.G);
        }
    }

    public final void r(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WheelView);
        this.f3436k = (int) typedArrayObtainStyledAttributes.getDimension(11, this.f3436k);
        this.f3437l = (int) typedArrayObtainStyledAttributes.getDimension(4, -1.0f);
        this.f3438m = typedArrayObtainStyledAttributes.getInt(1, this.f3438m);
        this.f3434i = typedArrayObtainStyledAttributes.getDimension(8, this.f3434i);
        this.f3435j = typedArrayObtainStyledAttributes.getDimension(10, this.f3435j);
        this.f3440o = typedArrayObtainStyledAttributes.getColor(7, this.f3440o);
        this.f3441p = typedArrayObtainStyledAttributes.getColor(9, this.f3441p);
        this.f3432g = typedArrayObtainStyledAttributes.getColor(2, this.f3432g);
        this.f3433h = typedArrayObtainStyledAttributes.getDimension(3, this.f3433h);
        this.f3442q = typedArrayObtainStyledAttributes.getDimension(5, this.f3442q);
        this.f3445t = typedArrayObtainStyledAttributes.getBoolean(6, true);
        this.f3444s = typedArrayObtainStyledAttributes.getBoolean(0, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void s() {
        if (this.G == null) {
            TextPaint textPaint = new TextPaint();
            this.G = textPaint;
            textPaint.setAntiAlias(true);
        }
        if (this.f3431f == null) {
            Paint paint = new Paint();
            this.f3431f = paint;
            paint.setColor(this.f3432g);
            this.f3431f.setAntiAlias(true);
            this.f3431f.setStrokeWidth(this.f3433h);
        }
        int i8 = this.f3438m;
        if (i8 % 2 == 0) {
            this.f3438m = i8 + 1;
        }
        int i9 = this.f3438m;
        this.f3430e = this.f3436k * i9;
        this.f3439n = (int) Math.ceil((i9 - 1) / 2.0f);
        this.f3446u = new GestureDetector(getContext(), new a());
        this.f3447v = new OverScroller(getContext());
    }

    public void setData(List<String> list) {
        if (list == null) {
            return;
        }
        b bVar = new b(list);
        this.F = bVar;
        bVar.d(this);
    }

    public void setDefault(int i8) {
        if (this.F == null) {
            return;
        }
        u(i8, true);
        m();
        invalidate();
    }

    public void setEnable(boolean z7) {
        this.f3444s = z7;
    }

    public void setOnSelectListener(d dVar) {
        this.f3443r = dVar;
    }

    public final void t(int i8) {
        float fK = k();
        float f8 = this.f3450y;
        if (fK != f8) {
            this.f3447v.startScroll(0, (int) f8, 0, (int) (fK - f8), i8);
            invalidate();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void u(int r4, boolean r5) {
        /*
            r3 = this;
            int r0 = r3.i(r4)
            boolean r1 = r3.D
            r2 = 1
            if (r1 == 0) goto L15
            int r1 = r3.C
            if (r1 == r4) goto L24
            r3.C = r4
            if (r5 != 0) goto L25
            g5.o.c(r3)
            goto L25
        L15:
            int r4 = r3.C
            if (r4 == r0) goto L24
            r3.C = r0
            g5.o.c(r3)
            if (r5 != 0) goto L25
            g5.o.c(r3)
            goto L25
        L24:
            r2 = 0
        L25:
            if (r2 == 0) goto L36
            com.byd.uereport.widget.WheelView$d r4 = r3.f3443r
            if (r4 == 0) goto L36
            com.byd.uereport.widget.WheelView$c r5 = r3.F
            int r1 = r3.C
            java.lang.String r5 = r5.c(r1)
            r4.a(r3, r0, r5)
        L36:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.uereport.widget.WheelView.u(int, boolean):void");
    }

    public final void v() {
        u((int) Math.floor(h()), false);
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3432g = 253698854;
        this.f3433h = getResources().getDimensionPixelSize(R.dimen.dp_1);
        this.f3434i = getResources().getDimensionPixelSize(R.dimen.sp_24);
        this.f3435j = getResources().getDimensionPixelSize(R.dimen.sp_24);
        this.f3436k = getResources().getDimensionPixelSize(R.dimen.dp_64);
        this.f3437l = -1;
        this.f3438m = 3;
        this.f3439n = 1;
        this.f3440o = -1943655117;
        this.f3441p = -15752970;
        this.f3442q = 48.0f;
        this.f3444s = true;
        this.f3445t = true;
        this.C = 0;
        this.D = true;
        this.E = 0.5f;
        this.H = new Rect();
        r(context, attributeSet);
        s();
    }

    public WheelView(Context context) {
        super(context);
        this.f3432g = 253698854;
        this.f3433h = getResources().getDimensionPixelSize(R.dimen.dp_1);
        this.f3434i = getResources().getDimensionPixelSize(R.dimen.sp_24);
        this.f3435j = getResources().getDimensionPixelSize(R.dimen.sp_24);
        this.f3436k = getResources().getDimensionPixelSize(R.dimen.dp_64);
        this.f3437l = -1;
        this.f3438m = 3;
        this.f3439n = 1;
        this.f3440o = -1943655117;
        this.f3441p = -15752970;
        this.f3442q = 48.0f;
        this.f3444s = true;
        this.f3445t = true;
        this.C = 0;
        this.D = true;
        this.E = 0.5f;
        this.H = new Rect();
        s();
    }
}
