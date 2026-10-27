package com.byd.carsettings.ui.widget.wheelview;

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
import androidx.appcompat.widget.ActivityChooserView;
import com.byd.carsettings.R;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class WheelView extends View {
    public boolean A;
    public c B;
    public TextPaint D;
    public Rect E;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f3486a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f3487b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Paint f3488c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3489d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f3490e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f3491f;
    public float g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public float m;
    public d n;
    public boolean o;
    public boolean p;
    public float q;
    public GestureDetector r;
    public OverScroller s;
    public boolean t;
    public float u;
    public float v;
    public int w;
    public int x;
    public int y;
    public int z;

    public class a extends GestureDetector.SimpleOnGestureListener {
        public a() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f2, float f3) {
            if (WheelView.this.v < WheelView.this.w || WheelView.this.v > WheelView.this.x) {
                return false;
            }
            int i = (int) (f3 * WheelView.this.q);
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
        public final /* synthetic */ List f3493b;

        public b(List list) {
            this.f3493b = list;
        }

        @Override // com.byd.carsettings.ui.widget.wheelview.WheelView.c
        public int b() {
            return this.f3493b.size();
        }

        @Override // com.byd.carsettings.ui.widget.wheelview.WheelView.c
        public String c(int i) {
            return (String) this.f3493b.get(i);
        }
    }

    public static abstract class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<WheelView> f3495a;

        public abstract int b();

        public abstract String c(int i);

        public final void d(WheelView wheelView) {
            this.f3495a = new WeakReference<>(wheelView);
        }
    }

    public interface d {
        void a(View view, int i, String str);
    }

    public WheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3489d = 253698854;
        this.f3490e = getResources().getDimensionPixelSize(R.dimen.dp_1);
        this.f3491f = getResources().getDimensionPixelSize(R.dimen.sp_24);
        this.g = getResources().getDimensionPixelSize(R.dimen.sp_24);
        this.h = getResources().getDimensionPixelSize(R.dimen.dp_64);
        this.i = 3;
        this.j = 1;
        this.k = -1943655117;
        this.l = -12213505;
        this.m = 48.0f;
        this.o = true;
        this.p = true;
        this.q = 0.5f;
        this.z = 0;
        this.A = true;
        this.E = new Rect();
        r(context, attributeSet);
        s();
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.s.computeScrollOffset()) {
            this.v = this.s.getCurrY();
            j();
            invalidate();
        } else if (this.t) {
            t(250);
            this.t = false;
        }
    }

    public c getAdapter() {
        return this.B;
    }

    public int getListSize() {
        c cVar = this.B;
        if (cVar == null) {
            return 0;
        }
        return cVar.b();
    }

    public int getSelected() {
        if (this.B == null) {
            return 0;
        }
        return i(this.z);
    }

    public String getSelectedText() {
        if (this.B == null) {
            return "";
        }
        return this.B.c(i(this.z));
    }

    public final float h() {
        return (float) ((((double) this.j) + 0.5d) - ((double) (this.v / this.h)));
    }

    public int i(int i) {
        if (this.B.b() == 0) {
            return 0;
        }
        int iB = this.B.b();
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
        int iB = this.B.b();
        int i = this.j;
        int i2 = this.h;
        int i3 = this.y;
        float f2 = (i * i2) + i3;
        float f3 = (((i - iB) + 1) * i2) - i3;
        float f4 = this.v;
        if (f4 > f2) {
            this.v = f2;
        } else if (f4 < f3) {
            this.v = f3;
        }
    }

    public final int k() {
        int iFloor = (int) Math.floor(h());
        if (!this.A) {
            iFloor = i(iFloor);
        }
        return (this.j - iFloor) * this.h;
    }

    public final void l() {
        if (this.A) {
            this.w = Integer.MIN_VALUE;
            this.x = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        } else {
            int iB = this.B.b() - 1;
            int i = this.j;
            int i2 = this.h;
            this.w = (-(iB - i)) * i2;
            this.x = i * i2;
        }
        int i3 = this.j;
        if (i3 > 3) {
            i3 = 3;
        }
        this.y = i3 * this.h;
    }

    public final void m() {
        this.v = (this.j - this.z) * this.h;
    }

    public final void n(Canvas canvas) {
        int i;
        v();
        float f2 = this.v;
        int i2 = this.z;
        int i3 = this.j;
        float f3 = f2 + ((i2 - i3) * r4);
        int i4 = i2 + i3 + 1;
        float f4 = f3 - this.h;
        for (int i5 = (i2 - i3) - 1; i5 <= i4; i5++) {
            int i6 = i(i5);
            boolean z = i6 == i(this.z);
            String strC = this.B.c(i6);
            if (i5 < 0) {
                if (this.A) {
                    q(canvas, strC, f4, z);
                }
                i = this.h;
            } else if (i5 >= this.B.b()) {
                if (this.A) {
                    q(canvas, strC, f4, z);
                }
                i = this.h;
            } else {
                q(canvas, strC, f4, z);
                i = this.h;
            }
            f4 += i;
        }
    }

    public final void o(Canvas canvas) {
        float f2 = this.f3487b;
        int i = this.h;
        float f3 = this.f3490e;
        canvas.drawLine(0.0f, ((f2 / 2.0f) - (i / 2.0f)) + f3, this.f3486a, ((f2 / 2.0f) - (i / 2.0f)) + f3, this.f3488c);
        float f4 = this.f3487b;
        int i2 = this.h;
        float f5 = this.f3490e;
        canvas.drawLine(0.0f, ((f4 / 2.0f) + (i2 / 2.0f)) - f5, this.f3486a, ((f4 / 2.0f) + (i2 / 2.0f)) - f5, this.f3488c);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        c cVar = this.B;
        if (cVar == null || cVar.b() == 0 || this.h == 0) {
            return;
        }
        o(canvas);
        n(canvas);
        p(canvas);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f3486a = getMeasuredWidth();
        l();
        if (this.f3486a != 0.0f) {
            setMeasuredDimension(getMeasuredWidth(), this.i * this.h);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
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
            t(250);
        } else {
            this.v += motionEvent.getY() - this.u;
            j();
            this.u = motionEvent.getY();
        }
        invalidate();
        return true;
    }

    public final void p(Canvas canvas) {
        LinearGradient linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, this.m, 15921906, 15921906, Shader.TileMode.MIRROR);
        Paint paint = new Paint();
        paint.setShader(linearGradient);
        canvas.drawRect(0.0f, 0.0f, this.f3486a, this.m, paint);
        float f2 = this.f3487b;
        LinearGradient linearGradient2 = new LinearGradient(0.0f, f2 - this.m, 0.0f, f2, 15921906, 15921906, Shader.TileMode.MIRROR);
        Paint paint2 = new Paint();
        paint2.setShader(linearGradient2);
        float f3 = this.f3487b;
        canvas.drawRect(0.0f, f3 - this.m, this.f3486a, f3, paint2);
    }

    public final void q(Canvas canvas, String str, float f2, boolean z) {
        this.D.setTypeface(z ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        this.D.setTextAlign(Paint.Align.CENTER);
        this.D.setColor(z ? this.l : this.k);
        this.D.setTextSize(z ? this.g : this.f3491f);
        this.D.getTextBounds(str, 0, str.length(), this.E);
        canvas.drawText(str, this.f3486a / 2.0f, f2 + ((this.h + this.E.height()) / 2.0f), this.D);
    }

    public final void r(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.WheelView);
        this.h = (int) typedArrayObtainStyledAttributes.getDimension(10, this.h);
        this.i = typedArrayObtainStyledAttributes.getInt(1, this.i);
        this.f3491f = typedArrayObtainStyledAttributes.getDimension(7, this.f3491f);
        this.g = typedArrayObtainStyledAttributes.getDimension(9, this.g);
        this.k = typedArrayObtainStyledAttributes.getColor(6, this.k);
        this.l = typedArrayObtainStyledAttributes.getColor(8, this.l);
        this.f3489d = typedArrayObtainStyledAttributes.getColor(2, this.f3489d);
        this.f3490e = typedArrayObtainStyledAttributes.getDimension(3, this.f3490e);
        this.m = typedArrayObtainStyledAttributes.getDimension(4, this.m);
        this.p = typedArrayObtainStyledAttributes.getBoolean(5, true);
        this.o = typedArrayObtainStyledAttributes.getBoolean(0, true);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void s() {
        if (this.D == null) {
            TextPaint textPaint = new TextPaint();
            this.D = textPaint;
            textPaint.setAntiAlias(true);
        }
        if (this.f3488c == null) {
            Paint paint = new Paint();
            this.f3488c = paint;
            paint.setColor(this.f3489d);
            this.f3488c.setAntiAlias(true);
            this.f3488c.setStrokeWidth(this.f3490e);
        }
        int i = this.i;
        if (i % 2 == 0) {
            this.i = i + 1;
        }
        int i2 = this.i;
        this.f3487b = this.h * i2;
        this.j = (int) Math.ceil((i2 - 1) / 2.0f);
        this.r = new GestureDetector(getContext(), new a());
        this.s = new OverScroller(getContext());
    }

    public void setData(List<String> list) {
        if (list == null) {
            return;
        }
        b bVar = new b(list);
        this.B = bVar;
        bVar.d(this);
    }

    public void setDefault(int i) {
        if (this.B == null) {
            return;
        }
        u(i, true);
        m();
        invalidate();
    }

    public void setEnable(boolean z) {
        this.o = z;
    }

    public void setOnSelectListener(d dVar) {
        this.n = dVar;
    }

    public final void t(int i) {
        float fK = k();
        float f2 = this.v;
        if (fK != f2) {
            this.s.startScroll(0, (int) f2, 0, (int) (fK - f2), i);
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
            boolean r1 = r3.A
            r2 = 1
            if (r1 == 0) goto L15
            int r1 = r3.z
            if (r1 == r4) goto L24
            r3.z = r4
            if (r5 != 0) goto L25
            b.b.g.o.f.e(r3)
            goto L25
        L15:
            int r4 = r3.z
            if (r4 == r0) goto L24
            r3.z = r0
            b.b.g.o.f.e(r3)
            if (r5 != 0) goto L25
            b.b.g.o.f.e(r3)
            goto L25
        L24:
            r2 = 0
        L25:
            if (r2 == 0) goto L36
            com.byd.carsettings.ui.widget.wheelview.WheelView$d r4 = r3.n
            if (r4 == 0) goto L36
            com.byd.carsettings.ui.widget.wheelview.WheelView$c r5 = r3.B
            int r1 = r3.z
            java.lang.String r5 = r5.c(r1)
            r4.a(r3, r0, r5)
        L36:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.carsettings.ui.widget.wheelview.WheelView.u(int, boolean):void");
    }

    public final void v() {
        u((int) Math.floor(h()), false);
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3489d = 253698854;
        this.f3490e = getResources().getDimensionPixelSize(R.dimen.dp_1);
        this.f3491f = getResources().getDimensionPixelSize(R.dimen.sp_24);
        this.g = getResources().getDimensionPixelSize(R.dimen.sp_24);
        this.h = getResources().getDimensionPixelSize(R.dimen.dp_64);
        this.i = 3;
        this.j = 1;
        this.k = -1943655117;
        this.l = -12213505;
        this.m = 48.0f;
        this.o = true;
        this.p = true;
        this.q = 0.5f;
        this.z = 0;
        this.A = true;
        this.E = new Rect();
        r(context, attributeSet);
        s();
    }

    public WheelView(Context context) {
        super(context);
        this.f3489d = 253698854;
        this.f3490e = getResources().getDimensionPixelSize(R.dimen.dp_1);
        this.f3491f = getResources().getDimensionPixelSize(R.dimen.sp_24);
        this.g = getResources().getDimensionPixelSize(R.dimen.sp_24);
        this.h = getResources().getDimensionPixelSize(R.dimen.dp_64);
        this.i = 3;
        this.j = 1;
        this.k = -1943655117;
        this.l = -12213505;
        this.m = 48.0f;
        this.o = true;
        this.p = true;
        this.q = 0.5f;
        this.z = 0;
        this.A = true;
        this.E = new Rect();
        s();
    }
}
