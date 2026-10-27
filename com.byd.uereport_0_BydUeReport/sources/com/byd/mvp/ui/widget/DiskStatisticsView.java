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
import com.byd.mvp.R$color;
import com.byd.mvp.R$styleable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DiskStatisticsView extends View {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f2869d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2870e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2871f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2872g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2873h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2874i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Mode f2875j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public List<b> f2876k;

    public enum Mode {
        RING,
        STRIP
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f2877a;

        static {
            int[] iArr = new int[Mode.values().length];
            f2877a = iArr;
            try {
                iArr[Mode.RING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2877a[Mode.STRIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f2878a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f2879b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f2880c = 0;
    }

    public DiskStatisticsView(Context context) {
        super(context);
        this.f2869d = 0L;
        this.f2870e = 20;
        this.f2871f = 4;
        this.f2872g = 4;
        this.f2873h = R$color.bg_white;
        this.f2874i = R$color.bg_gray;
        this.f2875j = Mode.STRIP;
        this.f2876k = null;
        d(context);
    }

    public final void a(Canvas canvas) {
        RectF rectF;
        if (this.f2876k.size() <= 0) {
            return;
        }
        float fMin = Math.min(getWidth(), getHeight());
        float f8 = 0.0f;
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
        int i8 = 0;
        while (i8 < this.f2876k.size()) {
            b bVar = this.f2876k.get(i8);
            float f9 = f8;
            float f10 = ((bVar.f2878a * 1.0f) / this.f2869d) * 360.0f;
            int i9 = bVar.f2880c;
            if (i9 == 0) {
                i9 = bVar.f2879b;
            }
            SweepGradient sweepGradient = new SweepGradient(width2, height2, bVar.f2879b, i9);
            Paint paint = new Paint();
            paint.setShader(sweepGradient);
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawArc(rectF, f9 - 90.0f, f10, true, paint);
            paint.reset();
            i8++;
            f8 = f10 + f9;
        }
        float f11 = f8;
        int color = getContext().getColor(this.f2874i);
        SweepGradient sweepGradient2 = new SweepGradient(width2, height2, color, color);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setShader(sweepGradient2);
        canvas.drawArc(rectF, f11 - 90.0f, 360.0f - f11, true, paint2);
        paint2.reset();
        Paint paint3 = new Paint();
        paint3.setColor(getContext().getColor(this.f2873h));
        canvas.drawCircle(width2, height2, (fMin / 2.0f) - this.f2870e, paint3);
    }

    public final void b(Canvas canvas) {
        x3.a.a("DiskStatisticsView", "getWidth() = " + getWidth() + ", mItems.size: " + this.f2876k.size());
        if (this.f2876k.size() <= 0) {
            return;
        }
        RectF rectF = new RectF(0.0f, 0.0f, getWidth(), getHeight());
        int i8 = this.f2871f;
        int i9 = this.f2872g;
        float[] fArr = {i8, i9, i8, i9, i8, i9, i8, i9};
        Path path = new Path();
        path.addRoundRect(rectF, fArr, Path.Direction.CW);
        canvas.clipPath(path, Region.Op.INTERSECT);
        Paint paint = new Paint();
        canvas.save();
        int i10 = 0;
        float f8 = 0.0f;
        while (i10 < this.f2876k.size()) {
            b bVar = this.f2876k.get(i10);
            float width = ((bVar.f2878a * 1.0f) / this.f2869d) * getWidth();
            x3.a.a("DiskStatisticsView", "startX = " + f8 + ", deltaX = " + width);
            paint.setColor(bVar.f2879b);
            paint.setStyle(Paint.Style.FILL);
            paint.setAntiAlias(true);
            float f9 = width + f8;
            RectF rectF2 = new RectF(f8, 0.0f, f9, (float) getHeight());
            x3.a.a("DiskStatisticsView", "rectF = " + rectF2);
            canvas.drawRect(rectF2, paint);
            paint.reset();
            i10++;
            f8 = f9;
        }
        RectF rectF3 = new RectF(f8, 0.0f, getWidth(), getHeight());
        int color = getContext().getColor(this.f2874i);
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
        this.f2870e = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.disk_statistics_ring_size, this.f2870e);
        this.f2871f = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.disk_statistics_rx, this.f2871f);
        this.f2872g = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.disk_statistics_ry, this.f2872g);
        this.f2873h = typedArrayObtainStyledAttributes.getResourceId(R$styleable.disk_statistics_bg_small_ring, this.f2873h);
        this.f2874i = typedArrayObtainStyledAttributes.getResourceId(R$styleable.disk_statistics_color_not_use, this.f2874i);
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void d(Context context) {
        this.f2876k = new ArrayList();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i8 = a.f2877a[this.f2875j.ordinal()];
        if (i8 == 1) {
            a(canvas);
        } else {
            if (i8 != 2) {
                return;
            }
            b(canvas);
        }
    }

    @Override // android.view.View
    public void onMeasure(int i8, int i9) {
        super.onMeasure(i8, i9);
        x3.a.a("DiskStatisticsView", "ringSize = " + this.f2870e);
    }

    public void setMOde(Mode mode) {
        this.f2875j = mode;
    }

    public void setRingSize(int i8) {
        this.f2870e = i8;
    }

    public void setTotal(long j8) {
        this.f2869d = j8;
    }

    public DiskStatisticsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2869d = 0L;
        this.f2870e = 20;
        this.f2871f = 4;
        this.f2872g = 4;
        this.f2873h = R$color.bg_white;
        this.f2874i = R$color.bg_gray;
        this.f2875j = Mode.STRIP;
        this.f2876k = null;
        c(context, attributeSet);
        d(context);
    }

    public DiskStatisticsView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f2869d = 0L;
        this.f2870e = 20;
        this.f2871f = 4;
        this.f2872g = 4;
        this.f2873h = R$color.bg_white;
        this.f2874i = R$color.bg_gray;
        this.f2875j = Mode.STRIP;
        this.f2876k = null;
        c(context, attributeSet);
        d(context);
    }
}
