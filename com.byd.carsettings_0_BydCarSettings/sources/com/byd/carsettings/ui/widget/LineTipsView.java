package com.byd.carsettings.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import b.b.a.i.a;
import com.byd.carsettings.R;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes2.dex */
public class LineTipsView extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Point f3420a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Point f3421b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Point f3422c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Point f3423d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f3424e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f3425f;
    public Paint g;
    public Paint h;
    public Bitmap i;
    public TextPaint j;
    public TextPaint k;
    public Rect l;
    public Rect m;
    public int n;
    public int o;

    public LineTipsView(Context context) {
        super(context);
        this.f3424e = 0.0f;
        this.l = new Rect();
        this.m = new Rect();
        b();
    }

    public final void a() {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dp_10);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.dp_10);
        this.j.setTextSize(getResources().getDimension(R.dimen.sp_16));
        TextPaint textPaint = this.j;
        String str = this.f3425f;
        textPaint.getTextBounds(str, 0, str.length(), this.l);
        this.k.getTextBounds("99%", 0, 3, this.m);
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(R.dimen.dp_112);
        int dimensionPixelSize4 = getResources().getDimensionPixelSize(R.dimen.dp_82);
        if (Math.max(this.l.width(), this.m.width()) + dimensionPixelSize + dimensionPixelSize2 > getResources().getDimensionPixelSize(R.dimen.dp_112)) {
            this.j.setTextSize(getResources().getDimension(R.dimen.sp_12));
            TextPaint textPaint2 = this.j;
            String str2 = this.f3425f;
            textPaint2.getTextBounds(str2, 0, str2.length(), this.l);
            dimensionPixelSize3 = Math.max(this.l.width(), this.m.width()) + dimensionPixelSize + dimensionPixelSize2;
        }
        int iHeight = (dimensionPixelSize4 - this.l.height()) - this.m.height();
        this.n = iHeight / 3;
        this.o = iHeight / 2;
        if (this.i == null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inMutable = true;
            this.i = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.img_dark_float_instruct, options), dimensionPixelSize3, dimensionPixelSize4, true);
        }
    }

    public final void b() {
        setRotationY(a.f531a ? 180.0f : 0.0f);
        this.h = new Paint();
        Paint paint = new Paint();
        this.g = paint;
        paint.setColor(ContextCompat.getColor(getContext(), R.color.chart_line));
        this.g.setStyle(Paint.Style.STROKE);
        this.g.setAntiAlias(true);
        this.g.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.dp_2));
        this.g.setPathEffect(new DashPathEffect(new float[]{getResources().getDimensionPixelSize(R.dimen.dp_4), getResources().getDimensionPixelSize(R.dimen.dp_2)}, 0.0f));
        TextPaint textPaint = new TextPaint();
        this.j = textPaint;
        textPaint.setTextAlign(Paint.Align.CENTER);
        this.j.setAntiAlias(true);
        this.j.setTextSize(getResources().getDimension(R.dimen.sp_16));
        this.j.setColor(ContextCompat.getColor(getContext(), R.color.dialog_text_color_tips));
        TextPaint textPaint2 = new TextPaint();
        this.k = textPaint2;
        textPaint2.setTextAlign(Paint.Align.CENTER);
        this.k.setAntiAlias(true);
        this.k.setTextSize(getResources().getDimension(R.dimen.sp_20));
        this.k.setColor(ContextCompat.getColor(getContext(), R.color.dialog_text_color_main));
    }

    public void c() {
        Bitmap bitmap = this.i;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.i = null;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        Point point;
        Point point2;
        Point point3;
        super.onDraw(canvas);
        try {
            Point point4 = this.f3420a;
            if (point4 == null || (point = this.f3421b) == null || (point2 = this.f3422c) == null || (point3 = this.f3423d) == null) {
                return;
            }
            float f2 = this.f3424e;
            if (f2 <= 0.0f || f2 >= 100.0f) {
                return;
            }
            if (a.f531a) {
                f2 = 100.0f - f2;
            }
            float f3 = point4.x + (((point.x - r6) * f2) / 100.0f);
            float f4 = point4.y + (((point.y - r0) * f2) / 100.0f);
            canvas.drawLine(f3, f4, point2.x + (((point3.x - r0) * f2) / 100.0f), point2.y + ((f2 * (point3.y - r0)) / 100.0f), this.g);
            if (this.i == null) {
                a();
            }
            Bitmap bitmap = this.i;
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, f3 - (bitmap.getWidth() / 2.0f), (f4 - this.i.getHeight()) - getResources().getDimensionPixelSize(R.dimen.dp_2), this.h);
                canvas.drawText(this.f3425f, f3, f4 - this.o, this.j);
                canvas.drawText(Math.round(this.f3424e) + "%", f3, (f4 - this.i.getHeight()) + this.n + this.m.height(), this.k);
            }
        } catch (Exception e2) {
            BydLogHelper.logE(e2.getMessage());
        }
    }

    public void setColor(int i) {
        this.g.setColor(i);
    }

    public void setPoint(Point point, Point point2, Point point3, Point point4) {
        this.f3420a = point;
        this.f3423d = point4;
        this.f3422c = point3;
        this.f3421b = point2;
    }

    public void setProgress(float f2) {
        this.f3424e = f2;
        invalidate();
    }

    public void setProgressText(String str) {
        this.f3425f = str;
        a();
    }

    public LineTipsView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3424e = 0.0f;
        this.l = new Rect();
        this.m = new Rect();
        b();
    }
}
