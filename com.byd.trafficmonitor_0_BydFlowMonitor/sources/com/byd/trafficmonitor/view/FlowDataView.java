package com.byd.trafficmonitor.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import c.a.d.s.n;
import com.bydux.api.R;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class FlowDataView extends View {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Paint f4422b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Paint f4423c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Paint f4424d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f4425e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f4426f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f4427g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f4428h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f4429i;
    public final float j;
    public float k;
    public boolean l;

    public FlowDataView(Context context) {
        super(context);
        this.f4428h = "";
        this.j = 0.0f;
        this.k = 0.0f;
        e();
    }

    public final void a(Canvas canvas, String[] strArr) {
        float f2;
        float f3;
        Canvas canvas2;
        Canvas canvas3 = canvas;
        canvas3.translate(getWidth() / 2, getHeight() / 2);
        this.f4423c.setTextSize(getResources().getDimension(R.dimen.sp_42));
        Paint.FontMetrics fontMetrics = this.f4423c.getFontMetrics();
        float fAbs = Math.abs(fontMetrics.top);
        float fAbs2 = Math.abs(fontMetrics.ascent);
        float f4 = fontMetrics.descent;
        float f5 = fontMetrics.bottom;
        int length = strArr.length;
        float f6 = fAbs + f5;
        float f7 = length * f6;
        float f8 = (length - 1) / 2.0f;
        int i2 = 0;
        while (i2 < length) {
            if (TextUtils.isEmpty(strArr[i2])) {
                canvas2 = canvas3;
            } else {
                Resources.Theme theme = getContext().getTheme();
                if (i2 == 0) {
                    this.f4423c.setTextSize(getResources().getDimension(R.dimen.sp_18));
                    float fMeasureText = this.f4423c.measureText(strArr[i2]);
                    BydLogHelper.logD("textWidth:" + fMeasureText);
                    if (fMeasureText > 235.0f) {
                        this.f4423c.setTextSize(getResources().getDimension(R.dimen.sp_18) * (235.0f / fMeasureText));
                    }
                    this.f4423c.setColor(getResources().getColor(R.color.wave_text_1_color, theme));
                } else if (i2 == 1) {
                    this.f4423c.setTextSize(getResources().getDimension(R.dimen.sp_42));
                    this.f4423c.setColor(getResources().getColor(this.l ? R.color.wave_text_content_warn_color : R.color.wave_text_content_color, theme));
                } else if (i2 == 2) {
                    this.f4423c.setTextSize(getResources().getDimension(R.dimen.sp_20));
                    this.f4423c.setColor(getResources().getColor(this.l ? R.color.wave_text_content_warn_color : R.color.wave_sub_text_unit_color, theme));
                }
                float fMeasureText2 = this.f4423c.measureText(strArr[i2]);
                float f9 = i2;
                if (f9 < f8) {
                    f2 = 2.0f;
                    f3 = -(((f7 / 2.0f) - (f9 * f6)) - fAbs);
                } else {
                    f2 = 2.0f;
                    f3 = f9 > f8 ? ((f7 / 2.0f) - (((length - i2) - 1) * f6)) - (3.0f * f5) : (fAbs2 - f4) / 2.0f;
                }
                canvas2 = canvas;
                canvas2.drawText(strArr[i2], (-fMeasureText2) / f2, f3, this.f4423c);
            }
            i2++;
            canvas3 = canvas2;
        }
    }

    public final void b(Canvas canvas) {
        Paint paint = this.f4424d;
        float strokeWidth = paint == null ? 0.0f : paint.getStrokeWidth();
        if (strokeWidth > 0.0f) {
            canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, ((getWidth() - strokeWidth) / 2.0f) - 1.0f, this.f4424d);
        }
        this.f4422b.setShader(this.l ? new LinearGradient(0.0f, 0.0f, 0.0f, getHeight(), new int[]{Color.parseColor("#F47878"), Color.parseColor("#F47878")}, (float[]) null, Shader.TileMode.CLAMP) : new LinearGradient(0.0f, 0.0f, 0.0f, getHeight(), new int[]{Color.parseColor("#34F0FF"), Color.parseColor("#0086FB")}, (float[]) null, Shader.TileMode.CLAMP));
        float f2 = this.k;
        if (f2 >= 0.5d) {
            float fMin = Math.min(f2, 1.0f);
            this.k = fMin;
            double d2 = (fMin - 0.5f) / 0.5f;
            canvas.drawArc(0.0f, 0.0f, getWidth(), getHeight(), -((float) Math.toDegrees(Math.asin(d2))), 360.0f - (((float) Math.toDegrees(Math.acos(d2))) * 2.0f), false, this.f4422b);
            return;
        }
        float fMax = Math.max(f2, 0.0f);
        this.k = fMax;
        double d3 = (0.5f - fMax) / 0.5f;
        canvas.drawArc(0.0f, 0.0f, getWidth(), getHeight(), (float) Math.toDegrees(Math.asin(d3)), ((float) Math.toDegrees(Math.acos(d3))) * 2.0f, false, this.f4422b);
    }

    public final void c(Canvas canvas) {
        canvas.translate(getWidth() / 2, getHeight() / 2);
        this.f4423c.setTextSize(getResources().getDimension(R.dimen.sp_18));
        Paint.FontMetrics fontMetrics = this.f4423c.getFontMetrics();
        float dimension = getResources().getDimension(R.dimen.dp_10);
        float fAbs = Math.abs(fontMetrics.top);
        float fAbs2 = Math.abs(fontMetrics.ascent);
        float f2 = fontMetrics.descent;
        float f3 = fontMetrics.bottom;
        float f4 = (fAbs + f3) * 3.0f;
        this.f4423c.setTextSize(getResources().getDimension(R.dimen.sp_18));
        this.f4423c.setColor(getResources().getColor(R.color.wave_text_1_color, getContext().getTheme()));
        if (TextUtils.isEmpty(this.f4425e) || TextUtils.isEmpty(this.f4426f)) {
            canvas.drawText("----", (-this.f4423c.measureText("----")) / 2.0f, (fAbs2 - f2) / 2.0f, this.f4423c);
            return;
        }
        float f5 = f4 / 2.0f;
        canvas.drawText(this.f4425e, (-this.f4423c.measureText(this.f4425e)) / 2.0f, (-(f5 - fAbs)) - dimension, this.f4423c);
        canvas.drawText("↓", (-this.f4423c.measureText("↓")) / 2.0f, (fAbs2 - f2) / 2.0f, this.f4423c);
        canvas.drawText(this.f4426f, (-this.f4423c.measureText(this.f4425e)) / 2.0f, (f5 - (f3 * 3.0f)) + dimension, this.f4423c);
    }

    public final void d(Canvas canvas) {
        if (this.f4427g) {
            c(canvas);
            return;
        }
        String strD = n.d(((long) (this.k * this.f4429i)) + "");
        String strSubstring = strD.substring(strD.length() - 2);
        String strSubstring2 = strD.substring(0, strD.length() - 2);
        BydLogHelper.logD("show surplus flow:" + strSubstring2 + ",unit:" + strSubstring);
        a(canvas, new String[]{this.f4428h, strSubstring2, strSubstring});
    }

    public final void e() {
        Paint paint = new Paint();
        this.f4422b = paint;
        paint.setAntiAlias(true);
        this.f4422b.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.f4423c = paint2;
        paint2.setAntiAlias(true);
    }

    public void f(String str, String str2) {
        this.f4427g = true;
        this.f4425e = str;
        this.f4426f = str2;
        invalidate();
    }

    public void g(String str, long j) {
        this.f4427g = false;
        this.f4428h = str;
        this.f4429i = j;
        invalidate();
    }

    public void h(boolean z) {
        BydLogHelper.logD("updateWarnMark  isWarnMark   ----:" + (this.l ^ z));
        if (this.l ^ z) {
            this.l = z;
            if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                postInvalidate();
            } else {
                invalidate();
            }
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        b(canvas);
        d(canvas);
    }

    public void setRatio(float f2) {
        if (this.k != f2) {
            this.k = f2;
            invalidate();
        }
    }

    public FlowDataView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f4428h = "";
        this.j = 0.0f;
        this.k = 0.0f;
        e();
    }

    public FlowDataView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f4428h = "";
        this.j = 0.0f;
        this.k = 0.0f;
        e();
    }
}
