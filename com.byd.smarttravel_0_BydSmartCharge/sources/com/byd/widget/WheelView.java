package com.byd.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;
import com.byd.bydbase.R$dimen;
import com.byd.bydbase.R$styleable;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WheelView extends View {
    public transient boolean A;
    public transient float B;
    public transient c C;
    public transient TextPaint D;
    public transient Rect E;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public transient float f2000b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public transient float f2001c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public transient Paint f2002d;
    public transient int e;
    public transient float f;
    public transient float g;
    public transient float h;
    public transient int i;
    public transient int j;
    public transient int k;
    public transient int l;
    public transient int m;
    public transient int n;
    public transient float o;
    public transient d p;
    public transient boolean q;
    public transient GestureDetector r;
    public transient OverScroller s;
    public transient boolean t;
    public transient float u;
    public transient float v;
    public transient int w;
    public transient int x;
    public transient int y;
    public transient int z;

    public class a extends GestureDetector.SimpleOnGestureListener {
        public a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            if (WheelView.this.v < WheelView.this.w || WheelView.this.v > WheelView.this.x) {
                return false;
            }
            int i = (int) (f2 * WheelView.this.B);
            if (i > 1500) {
                i = 1500;
            }
            WheelView.this.s.fling(0, (int) WheelView.this.v, 0, i < -1500 ? -1500 : i, 0, 0, WheelView.this.w, WheelView.this.x, 0, WheelView.this.y);
            WheelView.this.t = true;
            return true;
        }
    }

    public class b extends c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ List f2004b;

        public b(List list) {
            this.f2004b = list;
        }

        @Override // com.byd.widget.WheelView.c
        public int b() {
            return this.f2004b.size();
        }

        @Override // com.byd.widget.WheelView.c
        public String c(int i) {
            return (i <= 0 || i >= this.f2004b.size()) ? this.f2004b.size() == 0 ? "" : (String) this.f2004b.get(0) : (String) this.f2004b.get(i);
        }
    }

    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public transient WeakReference<WheelView> f2006a;

        public abstract int b();

        public abstract String c(int i);

        public final void d(WheelView wheelView) {
            this.f2006a = new WeakReference<>(wheelView);
        }
    }

    public interface d {
        void a(View view, int i, String str);
    }

    public WheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = 253698854;
        this.f = getResources().getDimensionPixelSize(R$dimen.dp_1);
        Resources resources = getResources();
        int i2 = R$dimen.sp_24;
        this.g = resources.getDimensionPixelSize(i2);
        this.h = getResources().getDimensionPixelSize(i2);
        this.i = getResources().getDimensionPixelSize(R$dimen.dp_64);
        this.j = -1;
        this.k = 3;
        this.l = 1;
        this.m = -1943655117;
        this.n = -16732949;
        this.o = 48.0f;
        this.q = true;
        this.z = 0;
        this.A = true;
        this.B = 0.5f;
        this.E = new Rect();
        q(context, attributeSet);
        r();
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.s.computeScrollOffset()) {
            this.v = this.s.getCurrY();
            j();
            invalidate();
        } else if (this.t) {
            s(250);
            this.t = false;
        }
    }

    public c getAdapter() {
        return this.C;
    }

    public int getListSize() {
        c cVar = this.C;
        if (cVar == null) {
            return 0;
        }
        return cVar.b();
    }

    public int getSelected() {
        if (this.C == null) {
            return 0;
        }
        return i(this.z);
    }

    public String getSelectedText() {
        if (this.C == null) {
            return "";
        }
        return this.C.c(i(this.z));
    }

    public final float h() {
        return (float) ((((double) this.l) + 0.5d) - ((double) (this.v / this.i)));
    }

    public int i(int i) {
        if (this.C.b() == 0) {
            return 0;
        }
        int iB = this.C.b();
        if (this.A) {
            if (i < 0) {
                i %= iB;
                if (i != 0) {
                    i += iB;
                }
            } else if (i >= iB) {
                i %= iB;
            }
        }
        if (i < 0) {
            return 0;
        }
        return i >= iB ? iB - 1 : i;
    }

    public final void j() {
        if (this.A) {
            return;
        }
        int iB = this.C.b();
        int i = this.l;
        int i2 = this.i;
        int i3 = this.y;
        float f = (i * i2) + i3;
        float f2 = (((i - iB) + 1) * i2) - i3;
        float f3 = this.v;
        if (f3 > f) {
            this.v = f;
        } else if (f3 < f2) {
            this.v = f2;
        }
    }

    public final int k() {
        int iFloor = (int) Math.floor(h());
        if (!this.A) {
            iFloor = i(iFloor);
        }
        return (this.l - iFloor) * this.i;
    }

    public final void l() {
        if (this.A) {
            this.w = Integer.MIN_VALUE;
            this.x = Integer.MAX_VALUE;
        } else {
            int iB = this.C.b() - 1;
            int i = this.l;
            int i2 = this.i;
            this.w = (-(iB - i)) * i2;
            this.x = i * i2;
        }
        int i3 = this.l;
        if (i3 > 3) {
            i3 = 3;
        }
        this.y = i3 * this.i;
    }

    public final void m() {
        this.v = (this.l - this.z) * this.i;
    }

    public final void n(Canvas canvas) {
        int i;
        u();
        float f = this.v;
        int i2 = this.z;
        int i3 = this.l;
        float f2 = f + ((i2 - i3) * r4);
        int i4 = i2 + i3 + 1;
        float f3 = f2 - this.i;
        for (int i5 = (i2 - i3) - 1; i5 <= i4; i5++) {
            int i6 = i(i5);
            boolean z = i6 == i(this.z);
            String strC = this.C.c(i6);
            if (i5 < 0) {
                if (this.A) {
                    p(canvas, strC, f3, z);
                }
                i = this.i;
            } else if (i5 >= this.C.b()) {
                if (this.A) {
                    p(canvas, strC, f3, z);
                }
                i = this.i;
            } else {
                p(canvas, strC, f3, z);
                i = this.i;
            }
            f3 += i;
        }
    }

    public final void o(Canvas canvas) {
        float f = this.f2001c;
        int i = this.i;
        float f2 = this.f;
        canvas.drawLine(0.0f, ((f / 2.0f) - (i / 2.0f)) + f2, this.f2000b, ((f / 2.0f) - (i / 2.0f)) + f2, this.f2002d);
        float f3 = this.f2001c;
        int i2 = this.i;
        float f4 = this.f;
        canvas.drawLine(0.0f, ((f3 / 2.0f) + (i2 / 2.0f)) - f4, this.f2000b, ((f3 / 2.0f) + (i2 / 2.0f)) - f4, this.f2002d);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        c cVar = this.C;
        if (cVar == null || cVar.b() == 0 || this.i == 0) {
            return;
        }
        o(canvas);
        n(canvas);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f2000b = getMeasuredWidth();
        l();
        if (this.f2000b != 0.0f) {
            setMeasuredDimension(getMeasuredWidth(), this.k * this.i);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.q) {
            return true;
        }
        if (this.r.onTouchEvent(motionEvent)) {
            invalidate();
            return true;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            if (!this.s.isFinished()) {
                this.s.forceFinished(true);
            }
            this.u = motionEvent.getY();
        } else if (action != 2) {
            this.v += motionEvent.getY() - this.u;
            s(50);
        } else {
            this.v += motionEvent.getY() - this.u;
            j();
            this.u = motionEvent.getY();
        }
        invalidate();
        return true;
    }

    public final void p(Canvas canvas, String str, float f, boolean z) {
        this.D.setTypeface(z ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        this.D.setTextAlign(Paint.Align.CENTER);
        this.D.setColor(z ? this.n : this.m);
        this.D.setTextSize(z ? this.h : this.g);
        this.D.getTextBounds(str, 0, str.length(), this.E);
        float fHeight = f + ((this.i + this.E.height()) / 2.0f);
        int i = this.j;
        if (i == -1) {
            canvas.drawText(str, this.f2000b / 2.0f, fHeight, this.D);
        } else {
            canvas.drawText(str, i, fHeight, this.D);
        }
    }

    public final void q(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.WheelView);
        this.i = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.WheelView_unitHeight, this.i);
        this.j = (int) typedArrayObtainStyledAttributes.getDimension(R$styleable.WheelView_marginStart, -1.0f);
        this.k = typedArrayObtainStyledAttributes.getInt(R$styleable.WheelView_itemNumber, this.k);
        this.g = typedArrayObtainStyledAttributes.getDimension(R$styleable.WheelView_normalTextSize, this.g);
        this.h = typedArrayObtainStyledAttributes.getDimension(R$styleable.WheelView_selectedTextSize, this.h);
        this.m = typedArrayObtainStyledAttributes.getColor(R$styleable.WheelView_normalTextColor, this.m);
        this.n = typedArrayObtainStyledAttributes.getColor(R$styleable.WheelView_selectedTextColor, this.n);
        this.e = typedArrayObtainStyledAttributes.getColor(R$styleable.WheelView_lineColor, this.e);
        this.f = typedArrayObtainStyledAttributes.getDimension(R$styleable.WheelView_lineHeight, this.f);
        this.o = typedArrayObtainStyledAttributes.getDimension(R$styleable.WheelView_maskHeight, this.o);
        this.q = typedArrayObtainStyledAttributes.getBoolean(R$styleable.WheelView_isEnable, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void r() {
        if (this.D == null) {
            TextPaint textPaint = new TextPaint();
            this.D = textPaint;
            textPaint.setAntiAlias(true);
        }
        if (this.f2002d == null) {
            Paint paint = new Paint();
            this.f2002d = paint;
            paint.setColor(this.e);
            this.f2002d.setAntiAlias(true);
            this.f2002d.setStrokeWidth(this.f);
        }
        int i = this.k;
        if (i % 2 == 0) {
            this.k = i + 1;
        }
        int i2 = this.k;
        this.f2001c = this.i * i2;
        this.l = (int) Math.ceil((i2 - 1) / 2.0f);
        this.r = new GestureDetector(getContext(), new a());
        this.s = new OverScroller(getContext());
    }

    public final void s(int i) {
        float fK = k();
        float f = this.v;
        if (fK != f) {
            this.s.startScroll(0, (int) f, 0, (int) (fK - f), i);
            invalidate();
        }
    }

    public void setData(List<String> list) {
        if (list == null) {
            return;
        }
        b bVar = new b(list);
        this.C = bVar;
        bVar.d(this);
    }

    public void setDefault(int i) {
        if (this.C == null) {
            return;
        }
        t(i, true);
        m();
        invalidate();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        this.q = z;
    }

    public void setOnSelectListener(d dVar) {
        this.p = dVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void t(int r4, boolean r5) {
        /*
            r3 = this;
            int r0 = r3.i(r4)
            boolean r1 = r3.A
            r2 = 1
            if (r1 == 0) goto L15
            int r1 = r3.z
            if (r1 == r4) goto L24
            r3.z = r4
            if (r5 != 0) goto L25
            c.a.c.a.e(r3)
            goto L25
        L15:
            int r4 = r3.z
            if (r4 == r0) goto L24
            r3.z = r0
            c.a.c.a.e(r3)
            if (r5 != 0) goto L25
            c.a.c.a.e(r3)
            goto L25
        L24:
            r2 = 0
        L25:
            if (r2 == 0) goto L36
            com.byd.widget.WheelView$d r4 = r3.p
            if (r4 == 0) goto L36
            com.byd.widget.WheelView$c r5 = r3.C
            int r1 = r3.z
            java.lang.String r5 = r5.c(r1)
            r4.a(r3, r0, r5)
        L36:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.widget.WheelView.t(int, boolean):void");
    }

    public final void u() {
        t((int) Math.floor(h()), false);
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = 253698854;
        this.f = getResources().getDimensionPixelSize(R$dimen.dp_1);
        Resources resources = getResources();
        int i = R$dimen.sp_24;
        this.g = resources.getDimensionPixelSize(i);
        this.h = getResources().getDimensionPixelSize(i);
        this.i = getResources().getDimensionPixelSize(R$dimen.dp_64);
        this.j = -1;
        this.k = 3;
        this.l = 1;
        this.m = -1943655117;
        this.n = -16732949;
        this.o = 48.0f;
        this.q = true;
        this.z = 0;
        this.A = true;
        this.B = 0.5f;
        this.E = new Rect();
        q(context, attributeSet);
        r();
    }

    public WheelView(Context context) {
        super(context);
        this.e = 253698854;
        this.f = getResources().getDimensionPixelSize(R$dimen.dp_1);
        Resources resources = getResources();
        int i = R$dimen.sp_24;
        this.g = resources.getDimensionPixelSize(i);
        this.h = getResources().getDimensionPixelSize(i);
        this.i = getResources().getDimensionPixelSize(R$dimen.dp_64);
        this.j = -1;
        this.k = 3;
        this.l = 1;
        this.m = -1943655117;
        this.n = -16732949;
        this.o = 48.0f;
        this.q = true;
        this.z = 0;
        this.A = true;
        this.B = 0.5f;
        this.E = new Rect();
        r();
    }
}
