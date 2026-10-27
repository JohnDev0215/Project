package com.byd.filemanager.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;
import com.byd.filemanager.R;
import com.byd.filemanager.g;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PathView extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f3869b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Paint f3870c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Paint f3871d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3872e;
    private int f;
    private int g;
    private Bitmap h;
    private float i;
    private float j;
    private float k;
    private float l;
    private float m;
    private int n;
    private int o;
    private List<a> p;
    private int q;
    private Rect r;
    private Rect s;
    private int t;
    private float u;
    private final Scroller v;
    private VelocityTracker w;
    private final int x;

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static float f3873a = -1.0f;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f3874b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f3875c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f3876d;

        public a(String str) {
            float f = f3873a;
            this.f3875c = f;
            this.f3876d = f;
            this.f3874b = str;
        }
    }

    public interface b {
    }

    public PathView(Context context) {
        this(context, null);
    }

    private float a(float f) {
        float scrollX = getScrollX();
        if (f < 0.0f) {
            return scrollX + f > 0.0f ? f : -scrollX;
        }
        if (f <= 0.0f || getMinWidth() <= this.f3872e) {
            return 0.0f;
        }
        float minWidth = (getMinWidth() - this.f3872e) - scrollX;
        return minWidth - f >= 0.0f ? f : minWidth;
    }

    private int b(int i) {
        Paint.FontMetricsInt fontMetricsInt = this.f3870c.getFontMetricsInt();
        int iMax = (int) Math.max(fontMetricsInt.descent - fontMetricsInt.ascent, this.j);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? iMax : size : Math.min(iMax, size);
    }

    private int c(int i) {
        int minWidth = (int) getMinWidth();
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? minWidth : size : Math.min(minWidth, size);
    }

    private void d() {
        this.w.computeCurrentVelocity(1000);
        float xVelocity = this.w.getXVelocity();
        float minWidth = getMinWidth() - this.f3872e;
        if (Math.abs(xVelocity) > this.x) {
            if (xVelocity < 0.0f) {
                this.v.fling(getScrollX(), 0, (int) (-xVelocity), 0, 0, (int) minWidth, 0, 0);
            } else {
                this.v.fling(getScrollX(), 0, (int) (-xVelocity), 0, 0, getScrollX(), 0, 0);
            }
        }
        VelocityTracker velocityTracker = this.w;
        if (velocityTracker != null) {
            velocityTracker.clear();
            this.w.recycle();
            this.w = null;
        }
    }

    private float getMinWidth() {
        float fMeasureText = this.p.get(0) != null ? 0.0f + this.f3870c.measureText(this.p.get(0).f3874b) : 0.0f;
        if (this.p.size() > 1) {
            for (int i = 1; i < this.p.size(); i++) {
                fMeasureText += this.f3870c.measureText(this.p.get(i).f3874b) + this.i + this.l;
            }
        }
        return fMeasureText;
    }

    @Override // android.view.View
    public void computeScroll() {
        if (this.v.computeScrollOffset()) {
            scrollTo(this.v.getCurrX(), this.v.getCurrY());
            invalidate();
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Bitmap bitmap = this.h;
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = 0;
        if (this.s == null) {
            int i2 = this.f;
            float f = this.j;
            this.s = new Rect(0, (int) ((i2 / 2) - (f / 2.0f)), (int) this.i, (int) ((i2 / 2) + (f / 2.0f)));
        }
        if (this.t == 0) {
            this.t = (int) ((getHeight() / 2) - ((this.f3870c.descent() + this.f3870c.ascent()) / 2.0f));
        }
        canvas.save();
        a aVar = null;
        while (i < this.p.size()) {
            Paint paint = this.f3870c;
            int i3 = this.q;
            paint.setColor((i3 == -1 || i3 != i) ? this.n : this.o);
            a aVar2 = this.p.get(i);
            String str = aVar2.f3874b;
            if (i == 0) {
                aVar2.f3875c = 0.0f;
                aVar2.f3876d = this.f3870c.measureText(str);
                canvas.drawText(str, 0.0f, this.t, this.f3870c);
                canvas.translate(this.f3870c.measureText(str), 0.0f);
            } else {
                float f2 = aVar.f3876d + this.i + this.l;
                aVar2.f3875c = f2;
                aVar2.f3876d = f2 + this.f3870c.measureText(str);
                canvas.drawBitmap(this.h, this.r, this.s, this.f3871d);
                canvas.translate(this.i, 0.0f);
                canvas.drawText(str, this.l, this.t, this.f3870c);
                canvas.translate(this.l + this.f3870c.measureText(str), 0.0f);
            }
            i++;
            aVar = aVar2;
        }
        canvas.restore();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f3872e = c(i);
        int iB = b(i2);
        this.f = iB;
        setMeasuredDimension(this.f3872e, iB);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003a  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            android.view.VelocityTracker r0 = r3.w
            if (r0 != 0) goto Lf
            android.view.VelocityTracker r0 = android.view.VelocityTracker.obtain()
            r3.w = r0
            r1 = 1000(0x3e8, float:1.401E-42)
            r0.computeCurrentVelocity(r1)
        Lf:
            android.view.VelocityTracker r0 = r3.w
            r0.addMovement(r4)
            int r0 = r4.getAction()
            float r4 = r4.getX()
            r1 = 1
            if (r0 == 0) goto L3e
            if (r0 == r1) goto L3a
            r2 = 2
            if (r0 == r2) goto L2b
            r4 = 3
            if (r0 == r4) goto L3a
            r4 = 4
            if (r0 == r4) goto L3a
            goto L4d
        L2b:
            float r0 = r3.u
            float r0 = r0 - r4
            r3.u = r4
            float r4 = r3.a(r0)
            int r4 = (int) r4
            r0 = 0
            r3.scrollBy(r4, r0)
            goto L4d
        L3a:
            r3.d()
            goto L4d
        L3e:
            android.widget.Scroller r0 = r3.v
            boolean r0 = r0.isFinished()
            if (r0 != 0) goto L4b
            android.widget.Scroller r0 = r3.v
            r0.abortAnimation()
        L4b:
            r3.u = r4
        L4d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.filemanager.ui.view.PathView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setOnItemPopListener(b bVar) {
        this.f3869b = bVar;
    }

    public PathView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PathView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.p = new ArrayList();
        this.q = -1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, g.M0);
        this.g = typedArrayObtainStyledAttributes.getResourceId(3, R.mipmap.ic_launcher);
        this.h = BitmapFactory.decodeResource(getResources(), this.g);
        String string = typedArrayObtainStyledAttributes.getString(5);
        this.p.add(new a(string == null ? "" : string));
        this.i = typedArrayObtainStyledAttributes.getDimension(4, 0.0f);
        this.j = typedArrayObtainStyledAttributes.getDimension(1, 0.0f);
        this.k = typedArrayObtainStyledAttributes.getDimension(2, 0.0f);
        this.l = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, 0);
        this.m = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, 0);
        int color = typedArrayObtainStyledAttributes.getColor(6, -16777216);
        this.n = color;
        this.o = typedArrayObtainStyledAttributes.getColor(0, color);
        TextPaint textPaint = new TextPaint(1);
        this.f3870c = textPaint;
        textPaint.setTextSize(this.m);
        this.f3870c.setColor(this.n);
        this.f3871d = new Paint(1);
        this.r = new Rect(0, 0, this.h.getWidth(), this.h.getHeight());
        this.v = new Scroller(context);
        this.x = ViewConfiguration.get(context).getScaledTouchSlop();
    }
}
