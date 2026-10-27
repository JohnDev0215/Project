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

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f719b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f720c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f721d;
    public int e;
    public int f;
    public int g;
    public Mode h;
    public List<b> i;

    public enum Mode {
        RING,
        STRIP
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f723a;

        static {
            int[] iArr = new int[Mode.values().length];
            f723a = iArr;
            try {
                iArr[Mode.RING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f723a[Mode.STRIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f724a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f725b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f726c = 0;
    }

    public DiskStatisticsView(Context context) {
        super(context);
        this.f719b = 0L;
        this.f720c = 20;
        this.f721d = 4;
        this.e = 4;
        this.f = R$color.bg_white;
        this.g = R$color.bg_gray;
        this.h = Mode.STRIP;
        this.i = null;
        d(context);
    }

    public final void a(Canvas canvas) {
        RectF rectF;
        if (this.i.size() <= 0) {
            return;
        }
        float fMin = Math.min(getWidth(), getHeight());
        float f = 0.0f;
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
        while (i < this.i.size()) {
            b bVar = this.i.get(i);
            float f2 = f;
            float f3 = ((bVar.f724a * 1.0f) / this.f719b) * 360.0f;
            int i2 = bVar.f726c;
            if (i2 == 0) {
                i2 = bVar.f725b;
            }
            SweepGradient sweepGradient = new SweepGradient(width2, height2, bVar.f725b, i2);
            Paint paint = new Paint();
            paint.setShader(sweepGradient);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawArc(rectF, f2 - 90.0f, f3, true, paint);
            paint.reset();
            i++;
            f = f3 + f2;
        }
        float f4 = f;
        int color = getContext().getColor(this.g);
        SweepGradient sweepGradient2 = new SweepGradient(width2, height2, color, color);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setShader(sweepGradient2);
        canvas.drawArc(rectF, f4 - 90.0f, 360.0f - f4, true, paint2);
        paint2.reset();
        Paint paint3 = new Paint();
        paint3.setColor(getContext().getColor(this.f));
        canvas.drawCircle(width2, height2, (fMin / 2.0f) - this.f720c, paint3);
    }

    public final void b(Canvas canvas) {
        b.a.d.b.a.a("DiskStatisticsView", "getWidth() = " + getWidth() + ", mItems.size: " + this.i.size());
        if (this.i.size() <= 0) {
            return;
        }
        RectF rectF = new RectF(0.0f, 0.0f, getWidth(), getHeight());
        int i = this.f721d;
        int i2 = this.e;
        float[] fArr = {i, i2, i, i2, i, i2, i, i2};
        Path path = new Path();
        path.addRoundRect(rectF, fArr, Path.Direction.CW);
        canvas.clipPath(path, Region.Op.INTERSECT);
        Paint paint = new Paint();
        canvas.save();
        int i3 = 0;
        float f = 0.0f;
        while (i3 < this.i.size()) {
            b bVar = this.i.get(i3);
            float width = ((bVar.f724a * 1.0f) / this.f719b) * getWidth();
            b.a.d.b.a.a("DiskStatisticsView", "startX = " + f + ", deltaX = " + width);
            paint.setColor(bVar.f725b);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            float f2 = width + f;
            RectF rectF2 = new RectF(f, 0.0f, f2, (float) getHeight());
            b.a.d.b.a.a("DiskStatisticsView", "rectF = " + rectF2);
            canvas.drawRect(rectF2, paint);
            paint.reset();
            i3++;
            f = f2;
        }
        RectF rectF3 = new RectF(f, 0.0f, getWidth(), getHeight());
        int color = getContext().getColor(this.g);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        canvas.drawRect(rectF3, paint);
        paint.reset();
        canvas.restore();
    }

    public final void c(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.disk_statistics);
        this.f720c = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.disk_statistics_ring_size, this.f720c);
        this.f721d = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.disk_statistics_rx, this.f721d);
        this.e = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.disk_statistics_ry, this.e);
        this.f = typedArrayObtainStyledAttributes.getResourceId(R$styleable.disk_statistics_bg_small_ring, this.f);
        this.g = typedArrayObtainStyledAttributes.getResourceId(R$styleable.disk_statistics_color_not_use, this.g);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void d(Context context) {
        this.i = new ArrayList();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = a.f723a[this.h.ordinal()];
        if (i == 1) {
            a(canvas);
        } else {
            if (i != 2) {
                return;
            }
            b(canvas);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        b.a.d.b.a.a("DiskStatisticsView", "ringSize = " + this.f720c);
    }

    public void setMOde(Mode mode) {
        this.h = mode;
    }

    public void setRingSize(int i) {
        this.f720c = i;
    }

    public void setTotal(long j) {
        this.f719b = j;
    }

    public DiskStatisticsView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f719b = 0L;
        this.f720c = 20;
        this.f721d = 4;
        this.e = 4;
        this.f = R$color.bg_white;
        this.g = R$color.bg_gray;
        this.h = Mode.STRIP;
        this.i = null;
        c(context, attributeSet);
        d(context);
    }

    public DiskStatisticsView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f719b = 0L;
        this.f720c = 20;
        this.f721d = 4;
        this.e = 4;
        this.f = R$color.bg_white;
        this.g = R$color.bg_gray;
        this.h = Mode.STRIP;
        this.i = null;
        c(context, attributeSet);
        d(context);
    }
}
