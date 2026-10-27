package com.byd.hvac.view.datapickview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.hvac.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class WheelView extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f1009a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1010b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List<String> f1011c;
    public int d;
    public Paint e;
    public Paint f;
    public float g;
    public float h;
    public float i;
    public float j;
    public float k;
    public int l;
    public int m;
    public float n;
    public float o;
    public boolean p;
    public d q;
    public Timer r;
    public c s;
    public Handler t;
    public Handler u;
    public int v;
    public int w;
    public boolean x;
    public boolean y;
    public Typeface z;

    /* JADX INFO: compiled from: caradapter-bug */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            if (WheelView.this.x || WheelView.this.f1011c == null || WheelView.this.f1011c.isEmpty()) {
                return;
            }
            if (Math.abs(WheelView.this.o) < 10.0f) {
                WheelView.this.o = 0.0f;
                WheelView.this.y = false;
                if (WheelView.this.s != null) {
                    WheelView.this.s.cancel();
                    WheelView.this.s = null;
                    if (WheelView.this.x) {
                        return;
                    } else {
                        WheelView.this.s();
                    }
                }
            } else {
                WheelView.this.o -= (WheelView.this.o / Math.abs(WheelView.this.o)) * 10.0f;
                WheelView.this.y = true;
            }
            if (WheelView.this.x) {
                return;
            }
            WheelView.this.invalidate();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            if (WheelView.this.x || WheelView.this.f1011c == null || WheelView.this.f1011c.isEmpty()) {
                return;
            }
            if (Math.abs(WheelView.this.o) < 10.0f) {
                WheelView.this.o = 0.0f;
                if (WheelView.this.s != null) {
                    WheelView.this.s.cancel();
                    WheelView.this.s = null;
                }
            } else {
                WheelView.this.o -= (WheelView.this.o / Math.abs(WheelView.this.o)) * 10.0f;
            }
            if (WheelView.this.x) {
                return;
            }
            WheelView.this.invalidate();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class c extends TimerTask {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Handler f1014a;

        public c(Handler handler) {
            this.f1014a = handler;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            Handler handler = this.f1014a;
            handler.sendMessage(handler.obtainMessage());
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface d {
        void G(WheelView wheelView);

        void V(WheelView wheelView, int i, String str);

        void i0(WheelView wheelView, int i, String str);

        void q0(WheelView wheelView);

        void y(WheelView wheelView, int i);
    }

    public WheelView(Context context) {
        this(context, null);
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        getParent().requestDisallowInterceptTouchEvent(true);
        if (!isEnabled() || this.p) {
            return super.dispatchTouchEvent(motionEvent);
        }
        d dVar = this.q;
        if (dVar == null) {
            return false;
        }
        dVar.y(this, this.d);
        return false;
    }

    public int getCurrentSelectedIndex() {
        return this.d;
    }

    public String getCurrentSelectedItem() {
        List<String> list = this.f1011c;
        return list == null ? "" : list.get(this.d);
    }

    public final void i(MotionEvent motionEvent) {
        n();
        d dVar = this.q;
        if (dVar != null) {
            dVar.q0(this);
        }
        c cVar = this.s;
        if (cVar != null) {
            cVar.cancel();
            this.s = null;
        }
        this.n = motionEvent.getY();
    }

    public final void j(MotionEvent motionEvent) {
        List<String> list;
        float y = this.o + (motionEvent.getY() - this.n);
        this.o = y;
        float f = this.k;
        float f2 = this.h;
        if (y > (f * f2) / 2.0f) {
            boolean z = this.f1010b;
            if (!z && this.d == 0) {
                this.n = motionEvent.getY();
                invalidate();
                return;
            }
            if (!z) {
                this.d--;
                if (this.q != null && (list = this.f1011c) != null && !list.isEmpty()) {
                    d dVar = this.q;
                    int i = this.d;
                    dVar.V(this, i, this.f1011c.get(i));
                }
            }
            q();
            this.o -= this.k * this.h;
        } else if (y < ((-f) * f2) / 2.0f) {
            if (this.d == this.f1011c.size() - 1) {
                this.n = motionEvent.getY();
                invalidate();
                return;
            }
            if (!this.f1010b) {
                this.d++;
                if (this.q != null && !this.f1011c.isEmpty()) {
                    d dVar2 = this.q;
                    int i2 = this.d;
                    dVar2.V(this, i2, this.f1011c.get(i2));
                }
            }
            p();
            this.o += this.k * this.h;
        }
        this.n = motionEvent.getY();
        invalidate();
    }

    public final void k() {
        d dVar = this.q;
        if (dVar != null) {
            dVar.G(this);
        }
        if (Math.abs(this.o) < 1.0E-4d) {
            this.o = 0.0f;
            return;
        }
        c cVar = this.s;
        if (cVar != null) {
            cVar.cancel();
            this.s = null;
        }
        c cVar2 = new c(this.t);
        this.s = cVar2;
        this.r.schedule(cVar2, 0L, 10L);
    }

    public final void l(Canvas canvas) {
        List<String> list = this.f1011c;
        if (list == null || list.isEmpty() || this.x || this.d < 0) {
            return;
        }
        n();
        int i = this.d;
        if (i == 0) {
            this.e.setColor(getResources().getColor(R.color.tempture_hi));
        } else if (i == this.f1011c.size() - 1) {
            this.e.setColor(getResources().getColor(R.color.tempture_lo));
        } else {
            this.f.setColor(this.v);
            this.e.setColor(this.w);
        }
        float fR = r(this.l / 2.0f, this.o);
        float f = this.g;
        float f2 = this.h;
        float f3 = ((f - f2) * fR) + f2;
        this.e.setTextSize(f3);
        Typeface typeface = this.z;
        if (typeface != null) {
            this.e.setTypeface(typeface);
        }
        Paint paint = this.e;
        float f4 = this.i;
        float f5 = this.j;
        paint.setAlpha((int) (((f4 - f5) * fR) + f5));
        float f6 = (float) (((double) this.m) / 2.0d);
        float f7 = (float) ((((double) this.l) / 2.0d) + ((double) this.o));
        Paint.FontMetricsInt fontMetricsInt = this.e.getFontMetricsInt();
        float f8 = (float) (((double) f7) - ((((double) fontMetricsInt.bottom) / 2.0d) + (((double) fontMetricsInt.top) / 2.0d)));
        String str = this.f1011c.get(this.d);
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length > 1) {
            strArrSplit[1] = "." + strArrSplit[1];
            float f9 = f3 * 0.71f;
            if (this.k == 3.0f) {
                float f10 = (2.0f * f6) / 7.0f;
                canvas.drawText(strArrSplit[0], f6 - f10, f8, this.e);
                this.e.setTextSize(f9);
                canvas.drawText(strArrSplit[1], f6 + f10, f8, this.e);
            } else {
                float f11 = f6 / 3.0f;
                canvas.drawText(strArrSplit[0], f6 - f11, f8, this.e);
                this.e.setTextSize(f9);
                canvas.drawText(strArrSplit[1], f6 + f11, f8, this.e);
            }
        } else {
            canvas.drawText(str, f6, f8, this.e);
        }
        for (int i2 = 1; this.d - i2 >= 0; i2++) {
            m(canvas, i2, -1);
        }
        for (int i3 = 1; this.d + i3 < this.f1011c.size(); i3++) {
            m(canvas, i3, 1);
        }
    }

    public final void m(Canvas canvas, int i, int i2) {
        float f = i2;
        float f2 = (this.k * this.h * i) + (this.o * f);
        float fR = r(this.l / 4.0f, f2);
        float f3 = this.g;
        float f4 = this.h;
        this.f.setTextSize(((f3 - f4) * fR) + f4);
        Typeface typeface = this.z;
        if (typeface != null) {
            this.f.setTypeface(typeface);
        }
        Paint paint = this.f;
        float f5 = this.i;
        float f6 = this.j;
        paint.setAlpha((int) (((f5 - f6) * fR) + f6));
        float f7 = (float) ((((double) this.l) / 2.0d) + ((double) (f * f2)));
        Paint.FontMetricsInt fontMetricsInt = this.f.getFontMetricsInt();
        canvas.drawText(this.f1011c.get(this.d + (i2 * i)), (float) (((double) this.m) / 2.0d), (float) (((double) f7) - ((((double) fontMetricsInt.bottom) / 2.0d) + (((double) fontMetricsInt.top) / 2.0d))), this.f);
    }

    public final void n() {
        List<String> list = this.f1011c;
        if (list == null || list.isEmpty() || this.d < this.f1011c.size()) {
            return;
        }
        this.d = this.f1011c.size() / 2;
    }

    public final void o(TypedArray typedArray) {
        this.z = Typeface.createFromAsset(this.f1009a.getAssets(), "font/HFMoBestSans-R.otf");
        float fApplyDimension = TypedValue.applyDimension(2, 18.0f, getResources().getDisplayMetrics());
        float fApplyDimension2 = TypedValue.applyDimension(2, 14.0f, getResources().getDisplayMetrics());
        this.g = typedArray.getDimension(1, fApplyDimension);
        this.h = typedArray.getDimension(3, fApplyDimension2);
        this.i = typedArray.getInteger(0, 255);
        this.j = typedArray.getInteger(2, 120);
        this.k = typedArray.getFloat(6, 1.6f);
        float f = this.i;
        if (f > 255.0f) {
            f = 255.0f;
        }
        this.i = f;
        if (f < 0.0f) {
            f = 0.0f;
        }
        this.i = f;
        float f2 = this.j;
        float f3 = f2 <= 255.0f ? f2 : 255.0f;
        this.j = f3;
        this.j = f3 >= 0.0f ? f3 : 0.0f;
        this.r = new Timer();
        this.f1011c = new ArrayList();
        Paint paint = new Paint(1);
        this.e = paint;
        paint.setStyle(Paint.Style.FILL);
        this.e.setTextAlign(Paint.Align.CENTER);
        Paint paint2 = new Paint(1);
        this.f = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.f.setTextAlign(Paint.Align.CENTER);
        this.v = typedArray.getColor(5, -13421773);
        int color = typedArray.getColor(4, -10898788);
        this.w = color;
        setColors(this.v, color);
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        l(canvas);
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.l = getMeasuredHeight();
        this.m = getMeasuredWidth();
        invalidate();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001a  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            boolean r0 = r3.isEnabled()
            r1 = 1
            if (r0 == 0) goto L21
            int r0 = r4.getActionMasked()
            if (r0 == 0) goto L1e
            if (r0 == r1) goto L1a
            r2 = 2
            if (r0 == r2) goto L16
            r4 = 3
            if (r0 == r4) goto L1a
            goto L21
        L16:
            r3.j(r4)
            goto L21
        L1a:
            r3.k()
            goto L21
        L1e:
            r3.i(r4)
        L21:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.hvac.view.datapickview.WheelView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void p() {
        if (this.f1010b) {
            String str = this.f1011c.get(0);
            this.f1011c.remove(0);
            this.f1011c.add(str);
        }
    }

    public final void q() {
        if (this.f1010b) {
            String str = this.f1011c.get(r0.size() - 1);
            this.f1011c.remove(r1.size() - 1);
            this.f1011c.add(0, str);
        }
    }

    public final float r(float f, float f2) {
        float fPow = (float) (1.0d - Math.pow(f2 / f, 2.0d));
        if (fPow < 0.0f) {
            return 0.0f;
        }
        return fPow;
    }

    public final void s() {
        List<String> list;
        if (this.q == null || (list = this.f1011c) == null || list.isEmpty()) {
            return;
        }
        d dVar = this.q;
        int i = this.d;
        dVar.i0(this, i, this.f1011c.get(i));
    }

    public void setCanScroll(boolean z) {
        this.p = z;
    }

    public void setColors(@ColorInt int i, @ColorInt int i2) {
        this.f.setColor(i);
        this.e.setColor(i2);
    }

    public void setIsLoop(boolean z) {
        this.f1010b = z;
    }

    public void setItems(List<String> list) {
        this.f1011c = list;
    }

    public void setOnSelectListener(d dVar) {
        this.q = dVar;
    }

    public void setSelected(int i) {
        List<String> list;
        if (this.y || (list = this.f1011c) == null || list.isEmpty()) {
            return;
        }
        this.d = i;
        if (this.f1010b) {
            int size = (this.f1011c.size() / 2) - this.d;
            int i2 = 0;
            if (size < 0) {
                while (i2 < (-size)) {
                    p();
                    this.d--;
                    i2++;
                }
            } else if (size > 0) {
                while (i2 < size) {
                    q();
                    this.d++;
                    i2++;
                }
            }
        }
        invalidate();
        c cVar = this.s;
        if (cVar != null) {
            cVar.cancel();
            this.s = null;
        }
        Handler handler = this.t;
        handler.sendMessage(handler.obtainMessage());
    }

    public void setTextAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        this.i = f;
        this.j = f2;
        invalidate();
    }

    public void setTextSize(@FloatRange(from = 0.0d) float f, @FloatRange(from = 0.0d) float f2) {
        this.g = f;
        this.h = f2;
        invalidate();
    }

    public void setTextSpaceRatio(@FloatRange(from = 0.0d) float f) {
        this.k = f;
        invalidate();
    }

    public void t() {
        this.x = true;
        this.y = false;
        Handler handler = this.t;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.t = null;
        }
        Handler handler2 = this.u;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
            this.u = null;
        }
        c cVar = this.s;
        if (cVar != null) {
            cVar.cancel();
            this.s = null;
        }
    }

    public WheelView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WheelView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1010b = true;
        this.o = 0.0f;
        this.p = true;
        this.t = new a(Looper.getMainLooper());
        this.u = new b(Looper.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.wheel_view, i, 0);
        this.f1009a = context;
        o(typedArrayObtainStyledAttributes);
        typedArrayObtainStyledAttributes.recycle();
    }
}
