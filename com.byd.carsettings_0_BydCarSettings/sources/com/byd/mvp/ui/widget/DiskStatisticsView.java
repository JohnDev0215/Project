package com.byd.mvp.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.byd.mvp.R$color;
import com.byd.mvp.R$styleable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DiskStatisticsView extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f3789a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3790b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3791c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3792d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3793e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3794f;
    public Mode g;
    public List<b> h;

    public enum Mode {
        RING,
        STRIP
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f3796a;

        static {
            int[] iArr = new int[Mode.values().length];
            f3796a = iArr;
            try {
                iArr[Mode.RING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3796a[Mode.STRIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f3797a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f3798b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3799c = 0;
    }

    public DiskStatisticsView(Context context) {
        super(context);
        this.f3789a = 0L;
        this.f3790b = 20;
        this.f3791c = 4;
        this.f3792d = 4;
        this.f3793e = R$color.bg_white;
        this.f3794f = R$color.bg_gray;
        this.g = Mode.STRIP;
        this.h = null;
        f(context);
    }

    public void a(b bVar) {
        this.h.add(bVar);
        b.b.m.c.a.a("DiskStatisticsView", "mItems.size = " + this.h.size());
        requestLayout();
    }

    public final void b(Canvas canvas) {
        RectF rectF;
        if (this.h.size() <= 0) {
            return;
        }
        float fMin = Math.min(getWidth(), getHeight());
        float f2 = 0.0f;
        if (getWidth() == getHeight()) {
            rectF = new RectF(0.0f, 0.0f, fMin, fMin);
        } else if (getWidth() > getHeight()) {
            float width = (getWidth() - fMin) / 2.0f;
            rectF = new RectF(width, 0.0f, fMin + width, fMin);
        } else {
            float height = (getHeight() - fMin) / 2.0f;
            rectF = new RectF(0.0f, height, fMin, fMin + height);
        }
        float width2 = (getWidth() * 1.0f) / 2.0f;
        float height2 = (getHeight() * 1.0f) / 2.0f;
        int i = 0;
        while (i < this.h.size()) {
            b bVar = this.h.get(i);
            float f3 = f2;
            float f4 = ((bVar.f3797a * 1.0f) / this.f3789a) * 360.0f;
            int i2 = bVar.f3799c;
            if (i2 == 0) {
                i2 = bVar.f3798b;
            }
            SweepGradient sweepGradient = new SweepGradient(width2, height2, bVar.f3798b, i2);
            Paint paint = new Paint();
            paint.setShader(sweepGradient);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawArc(rectF, f3 - 90.0f, f4, true, paint);
            paint.reset();
            i++;
            f2 = f4 + f3;
        }
        float f5 = f2;
        int color = getContext().getColor(this.f3794f);
        SweepGradient sweepGradient2 = new SweepGradient(width2, height2, color, color);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setShader(sweepGradient2);
        canvas.drawArc(rectF, f5 - 90.0f, 360.0f - f5, true, paint2);
        paint2.reset();
        Paint paint3 = new Paint();
        paint3.setColor(getContext().getColor(this.f3793e));
        canvas.drawCircle(width2, height2, (fMin / 2.0f) - this.f3790b, paint3);
    }

    public final void c(Canvas canvas) {
        b.b.m.c.a.a("DiskStatisticsView", "getWidth() = " + getWidth() + ", mItems.size: " + this.h.size());
        if (this.h.size() <= 0) {
            return;
        }
        RectF rectF = new RectF(0.0f, 0.0f, getWidth(), getHeight());
        int i = this.f3791c;
        int i2 = this.f3792d;
        float[] fArr = {i, i2, i, i2, i, i2, i, i2};
        Path path = new Path();
        path.addRoundRect(rectF, fArr, Path.Direction.CW);
        canvas.clipPath(path, Region.Op.INTERSECT);
        Paint paint = new Paint();
        canvas.save();
        int i3 = 0;
        float f2 = 0.0f;
        while (i3 < this.h.size()) {
            b bVar = this.h.get(i3);
            float width = ((bVar.f3797a * 1.0f) / this.f3789a) * getWidth();
            b.b.m.c.a.a("DiskStatisticsView", "startX = " + f2 + ", deltaX = " + width);
            paint.setColor(bVar.f3798b);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            float f3 = width + f2;
            RectF rectF2 = new RectF(f2, 0.0f, f3, (float) getHeight());
            b.b.m.c.a.a("DiskStatisticsView", "rectF = " + rectF2);
            canvas.drawRect(rectF2, paint);
            paint.reset();
            i3++;
            f2 = f3;
        }
        RectF rectF3 = new RectF(f2, 0.0f, getWidth(), getHeight());
        int color = getContext().getColor(this.f3794f);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        canvas.drawRect(rectF3, paint);
        paint.reset();
        canvas.restore();
    }

    public b d(int i) {
        List<b> list = this.h;
        if (list == null || i < 0 || i >= list.size()) {
            return null;
        }
        return this.h.get(i);
    }

    public final void e(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.disk_statistics);
        this.f3790b = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.disk_statistics_ring_size, this.f3790b);
        this.f3791c = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.disk_statistics_rx, this.f3791c);
        this.f3792d = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.disk_statistics_ry, this.f3792d);
        this.f3793e = typedArrayObtainStyledAttributes.getResourceId(R$styleable.disk_statistics_bg_small_ring, this.f3793e);
        this.f3794f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.disk_statistics_color_not_use, this.f3794f);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void f(Context context) {
        this.h = new ArrayList();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = a.f3796a[this.g.ordinal()];
        if (i == 1) {
            b(canvas);
        } else {
            if (i != 2) {
                return;
            }
            c(canvas);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        b.b.m.c.a.a("DiskStatisticsView", "ringSize = " + this.f3790b);
    }

    public void setMOde(Mode mode) {
        this.g = mode;
    }

    public void setRingSize(int i) {
        this.f3790b = i;
    }

    public void setTotal(long j) {
        this.f3789a = j;
    }

    public DiskStatisticsView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3789a = 0L;
        this.f3790b = 20;
        this.f3791c = 4;
        this.f3792d = 4;
        this.f3793e = R$color.bg_white;
        this.f3794f = R$color.bg_gray;
        this.g = Mode.STRIP;
        this.h = null;
        e(context, attributeSet);
        f(context);
    }

    public DiskStatisticsView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3789a = 0L;
        this.f3790b = 20;
        this.f3791c = 4;
        this.f3792d = 4;
        this.f3793e = R$color.bg_white;
        this.f3794f = R$color.bg_gray;
        this.g = Mode.STRIP;
        this.h = null;
        e(context, attributeSet);
        f(context);
    }
}
