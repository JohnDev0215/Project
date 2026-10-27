package com.byd.bluetoothcall.view.azlist;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.byd.bluetoothcall.f.f;
import com.bydux.api.R;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AZSideBarView extends View {
    private volatile int A;
    private volatile int B;
    private ValueAnimator C;
    private float D;
    private c E;
    private int F;
    private String G;
    public b H;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f3325b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3326c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3327d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3328e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private List<String> q;
    private RectF r;
    private TextPaint s;
    private TextPaint t;
    private Paint u;
    private Drawable v;
    private float w;
    private float x;
    private volatile int y;
    private volatile int z;

    class a implements ValueAnimator.AnimatorUpdateListener {
        a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            AZSideBarView.this.D = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            if (AZSideBarView.this.D == 1.0f && AZSideBarView.this.z != AZSideBarView.this.A && AZSideBarView.this.A >= 0 && AZSideBarView.this.A < AZSideBarView.this.q.size()) {
                AZSideBarView aZSideBarView = AZSideBarView.this;
                aZSideBarView.y = aZSideBarView.A;
                if (AZSideBarView.this.E != null) {
                    AZSideBarView.this.E.a((String) AZSideBarView.this.q.get(AZSideBarView.this.A));
                }
            }
            AZSideBarView.this.invalidate();
        }
    }

    private class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<AZSideBarView> f3330a;

        public b(AZSideBarView aZSideBarView) {
            this.f3330a = new WeakReference<>(aZSideBarView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            WeakReference<AZSideBarView> weakReference = this.f3330a;
            if (weakReference == null || weakReference.get() == null || message.what != 13073) {
                return;
            }
            AZSideBarView.h(this.f3330a.get());
            this.f3330a.get().invalidate();
            if (AZSideBarView.this.E != null) {
                AZSideBarView.this.E.b();
            }
        }
    }

    public interface c {
        void a(String str);

        void b();
    }

    public AZSideBarView(Context context) {
        this(context, null);
        this.f3325b = context;
        n();
    }

    static /* synthetic */ int h(AZSideBarView aZSideBarView) {
        int i = aZSideBarView.B;
        aZSideBarView.B = i - 1;
        return i;
    }

    private void i(Canvas canvas) {
        if (this.r == null || this.y == -1 || this.B <= 0) {
            return;
        }
        float paddingTop = getPaddingTop();
        String strTrim = this.q.get(this.y).trim();
        RectF rectF = this.r;
        float size = ((rectF.bottom - rectF.top) - (this.n * 2)) / this.q.size();
        float fA = com.byd.bluetoothcall.view.azlist.a.a(this.r.top + this.n + (this.y * size) + (size / 2.0f), this.t, this.f);
        this.t.setColor(this.i);
        this.t.setTextSize(this.j);
        this.t.setTextAlign(Paint.Align.CENTER);
        Bitmap bitmapL = l(this.v);
        if (bitmapL != null) {
            this.u.setStyle(Paint.Style.FILL);
            this.u.setColor(this.l);
            int width = (bitmapL.getWidth() / 2) - 2;
            float fWidth = (this.r.width() / 2.0f) - this.x;
            float fWidth2 = (this.r.width() / 2.0f) + this.x;
            float f = fA + paddingTop;
            float height = (f - (bitmapL.getHeight() / 2)) - (this.s.getTextSize() / 2.0f);
            if (f.r()) {
                canvas.drawBitmap(bitmapL, fWidth2, height, this.u);
            } else {
                canvas.drawBitmap(bitmapL, fWidth, height, this.u);
            }
            this.t.measureText(strTrim);
            float f2 = width;
            float f3 = fWidth + f2;
            float f4 = fWidth2 + f2;
            if (f.r()) {
                canvas.drawText(strTrim, f4 + 5.0f, f, this.t);
            } else {
                canvas.drawText(strTrim, f3, f, this.t);
            }
        }
    }

    private void j(Canvas canvas) {
        float f;
        if (this.r == null) {
            return;
        }
        this.u.setStyle(Paint.Style.FILL);
        this.u.setColor(this.f3326c);
        this.u.setAntiAlias(true);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
        RectF rectF = this.r;
        int i = this.p;
        canvas.drawRoundRect(rectF, i / 2.0f, i / 2.0f, this.u);
        float paddingTop = getPaddingTop();
        RectF rectF2 = this.r;
        float size = ((rectF2.bottom - rectF2.top) - (this.n * 2)) / this.q.size();
        for (int i2 = 0; i2 < this.q.size(); i2++) {
            float fA = com.byd.bluetoothcall.view.azlist.a.a(this.r.top + this.n + (i2 * size) + (size / 2.0f), this.s, this.f);
            this.s.setColor(this.f3328e);
            this.s.setTextSize(this.f);
            this.s.setTextAlign(Paint.Align.CENTER);
            if (f.r()) {
                f = this.r.left - 60.0f;
            } else {
                RectF rectF3 = this.r;
                float f2 = rectF3.left;
                f = ((rectF3.right - f2) / 2.0f) + f2;
            }
            String str = this.q.get(i2);
            this.s.measureText(str);
            canvas.drawText(str, f, fA + paddingTop, this.s);
        }
    }

    private void k(Canvas canvas) {
        float f;
        if (this.r == null) {
            return;
        }
        float paddingTop = getPaddingTop();
        if (this.y != -1) {
            this.s.setColor(this.g);
            this.s.setTextSize(this.h);
            this.s.setTextAlign(Paint.Align.CENTER);
            RectF rectF = this.r;
            float size = ((rectF.bottom - rectF.top) - (this.n * 2)) / this.q.size();
            float fA = com.byd.bluetoothcall.view.azlist.a.a(this.r.top + this.n + (this.y * size) + (size / 2.0f), this.s, this.f);
            if (f.r()) {
                f = this.r.left - 60.0f;
            } else {
                RectF rectF2 = this.r;
                float f2 = rectF2.left;
                f = ((rectF2.right - f2) / 2.0f) + f2;
            }
            String str = this.q.get(this.y);
            this.s.measureText(str);
            canvas.drawText(str, f, fA + paddingTop, this.s);
        }
    }

    private Bitmap l(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    private void m(AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, com.byd.bluetoothcall.a.f3227a, i, 0);
        this.f3326c = typedArrayObtainStyledAttributes.getColor(0, Color.parseColor("#00000000"));
        this.f3327d = typedArrayObtainStyledAttributes.getColor(11, Color.parseColor("#000000"));
        if (f.l(this.f3325b) == 1) {
            this.f3328e = typedArrayObtainStyledAttributes.getColor(12, getResources().getColor(R.color.ts_b3_text_color));
        } else {
            this.f3328e = typedArrayObtainStyledAttributes.getColor(12, getResources().getColor(R.color.b3_text_color));
        }
        this.f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(13, (int) TypedValue.applyDimension(2, 12.0f, getResources().getDisplayMetrics()));
        if (f.l(this.f3325b) == 1) {
            this.g = typedArrayObtainStyledAttributes.getColor(9, Color.parseColor("#FF2270BE"));
        } else {
            this.g = typedArrayObtainStyledAttributes.getColor(9, Color.parseColor("#3AA7FF"));
        }
        this.h = typedArrayObtainStyledAttributes.getDimensionPixelOffset(10, (int) TypedValue.applyDimension(2, 12.0f, getResources().getDisplayMetrics()));
        this.i = typedArrayObtainStyledAttributes.getColor(7, Color.parseColor("#FFFFFF"));
        this.j = typedArrayObtainStyledAttributes.getDimensionPixelOffset(8, (int) TypedValue.applyDimension(2, 20.0f, getResources().getDisplayMetrics()));
        this.k = typedArrayObtainStyledAttributes.getDimensionPixelOffset(5, (int) TypedValue.applyDimension(1, 24.0f, getResources().getDisplayMetrics()));
        this.l = typedArrayObtainStyledAttributes.getColor(4, Color.parseColor("#2682DF"));
        this.m = typedArrayObtainStyledAttributes.getInteger(6, 0);
        this.n = typedArrayObtainStyledAttributes.getDimensionPixelOffset(3, (int) TypedValue.applyDimension(1, 2.0f, getResources().getDisplayMetrics()));
        this.o = typedArrayObtainStyledAttributes.getDimensionPixelOffset(1, (int) TypedValue.applyDimension(1, 6.0f, getResources().getDisplayMetrics()));
        this.p = typedArrayObtainStyledAttributes.getDimensionPixelOffset(2, (int) TypedValue.applyDimension(1, 0.0f, getResources().getDisplayMetrics()));
        int dimension = (int) this.f3325b.getResources().getDimension(R.dimen.text_sp_12);
        this.f = dimension;
        if (this.p == 0) {
            this.p = dimension * 2;
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void n() {
        this.q = Arrays.asList(getContext().getResources().getStringArray(R.array.slide_bar_value_list));
        this.s = new TextPaint();
        this.t = new TextPaint();
        Paint paint = new Paint();
        this.u = paint;
        paint.setAntiAlias(true);
        this.y = -1;
        this.w = this.f3325b.getResources().getDimension(R.dimen.dp_12);
        this.x = this.f3325b.getResources().getDimension(R.dimen.dp_4);
        if (f.r()) {
            this.v = getResources().getDrawable(R.drawable.ic_contacts_search_letter_list_rtl);
        } else {
            this.v = getResources().getDrawable(R.drawable.ic_contacts_search_letter_list_hint);
        }
    }

    private void o(float f) {
        if (this.C == null) {
            this.C = new ValueAnimator();
        }
        this.C.cancel();
        this.C.setFloatValues(f);
        this.C.addUpdateListener(new a());
        this.C.start();
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0065  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            float r0 = r5.getY()
            r5.getX()
            int r1 = r4.y
            r4.z = r1
            int r1 = r4.getPaddingTop()
            float r1 = (float) r1
            float r1 = r0 - r1
            android.graphics.RectF r2 = r4.r
            float r3 = r2.bottom
            float r2 = r2.top
            float r3 = r3 - r2
            float r1 = r1 / r3
            java.util.List<java.lang.String> r2 = r4.q
            int r2 = r2.size()
            float r2 = (float) r2
            float r1 = r1 * r2
            int r1 = (int) r1
            r4.A = r1
            int r5 = r5.getAction()
            r1 = 1
            if (r5 == 0) goto L73
            if (r5 == r1) goto L65
            r2 = 2
            if (r5 == r2) goto L35
            r0 = 3
            if (r5 == r0) goto L65
            goto La6
        L35:
            int r5 = (int) r0
            r4.F = r5
            int r5 = r4.z
            int r0 = r4.A
            if (r5 == r0) goto L61
            int r5 = r4.A
            if (r5 < 0) goto L61
            int r5 = r4.A
            java.util.List<java.lang.String> r0 = r4.q
            int r0 = r0.size()
            if (r5 >= r0) goto L61
            int r5 = r4.A
            r4.y = r5
            com.byd.bluetoothcall.view.azlist.AZSideBarView$c r5 = r4.E
            if (r5 == 0) goto L61
            java.util.List<java.lang.String> r0 = r4.q
            int r2 = r4.A
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            r5.a(r0)
        L61:
            r4.invalidate()
            goto La6
        L65:
            r5 = 0
            r4.o(r5)
            com.byd.bluetoothcall.view.azlist.AZSideBarView$b r5 = r4.H
            r0 = 13073(0x3311, float:1.8319E-41)
            r2 = 1000(0x3e8, double:4.94E-321)
            r5.sendEmptyMessageDelayed(r0, r2)
            goto La6
        L73:
            int r5 = r4.B
            int r5 = r5 + r1
            r4.B = r5
            int r5 = (int) r0
            r4.F = r5
            r5 = 1065353216(0x3f800000, float:1.0)
            r4.o(r5)
            int r5 = r4.A
            if (r5 < 0) goto La3
            int r5 = r4.A
            java.util.List<java.lang.String> r0 = r4.q
            int r0 = r0.size()
            if (r5 >= r0) goto La3
            int r5 = r4.A
            r4.y = r5
            com.byd.bluetoothcall.view.azlist.AZSideBarView$c r5 = r4.E
            if (r5 == 0) goto La3
            java.util.List<java.lang.String> r0 = r4.q
            int r2 = r4.A
            java.lang.Object r0 = r0.get(r2)
            java.lang.String r0 = (java.lang.String) r0
            r5.a(r0)
        La3:
            r4.invalidate()
        La6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.bluetoothcall.view.azlist.AZSideBarView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.H = new b(this);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b bVar = this.H;
        if (bVar != null) {
            bVar.removeCallbacksAndMessages(null);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        j(canvas);
        i(canvas);
        k(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getMeasuredHeight() < 400) {
            return;
        }
        if (this.r == null) {
            this.r = new RectF();
        }
        float measuredWidth = (getMeasuredWidth() - this.p) - this.o;
        int measuredWidth2 = getMeasuredWidth();
        int i3 = this.o;
        float measuredHeight = getMeasuredHeight() - this.o;
        this.r.set(measuredWidth, i3, measuredWidth2 - i3, measuredHeight);
    }

    public void setCurListLetter(String str) {
        this.G = str;
    }

    public void setLight(String str) {
        int i = 0;
        while (true) {
            if (i >= this.q.size()) {
                break;
            }
            if (str.equals(this.q.get(i))) {
                this.y = i;
                break;
            }
            i++;
        }
        invalidate();
    }

    public void setOnLetterChangeListener(c cVar) {
        this.E = cVar;
    }

    public AZSideBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
        this.f3325b = context;
        n();
    }

    public AZSideBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.w = -12.0f;
        this.x = 3.0f;
        this.B = 0;
        this.G = "";
        this.f3325b = context;
        m(attributeSet, i);
        n();
    }
}
