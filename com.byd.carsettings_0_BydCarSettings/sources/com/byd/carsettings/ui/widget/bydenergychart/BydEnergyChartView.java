package com.byd.carsettings.ui.widget.bydenergychart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.byd.carsettings.R;
import com.byd.carsettings.db.model.EnergyBean;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BydEnergyChartView extends View {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static float f3468a = -9999.0f;
    public float A;
    public float B;
    public float D;
    public String E;
    public float F;
    public float G;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3469b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3470c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3471d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3472e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3473f;
    public float g;
    public float h;
    public float i;
    public float j;
    public float k;
    public float l;
    public TextPaint m;
    public TextPaint n;
    public Paint o;
    public Paint p;
    public Paint q;
    public Paint r;
    public Paint s;
    public Rect t;
    public List<EnergyBean> u;
    public List<EnergyBean> v;
    public List<Float> w;
    public List<Float> x;
    public List<Float> y;
    public float z;

    public BydEnergyChartView(Context context) {
        this(context, null);
    }

    public final void a(Canvas canvas) {
        if (this.f3471d != 1 && this.F != f3468a) {
            this.r.setColor(ContextCompat.getColor(getContext(), R.color.chart_ytext_fuel));
            float size = ((this.f3473f - this.i) - this.j) / (this.w.size() - 1);
            float f2 = size / this.A;
            float f3 = this.F;
            float fJ = j(f3 >= 0.0f ? ((this.f3473f - this.j) - size) - (f3 * f2) : ((this.f3473f - this.j) - size) + ((f3 * size) / this.x.get(0).floatValue()));
            canvas.drawLine(this.h, fJ, this.f3472e - this.g, fJ, this.r);
        }
        if (this.f3471d == 2 || this.G == f3468a) {
            return;
        }
        this.r.setColor(ContextCompat.getColor(getContext(), R.color.chart_ytext_elec));
        float size2 = ((this.f3473f - this.i) - this.j) / (this.x.size() - 1);
        float f4 = size2 / this.z;
        float f5 = this.G;
        float fJ2 = j(f5 >= 0.0f ? ((this.f3473f - this.j) - size2) - (f5 * f4) : ((this.f3473f - this.j) - size2) + ((f5 * size2) / this.x.get(0).floatValue()));
        canvas.drawLine(this.h, fJ2, this.f3472e - this.g, fJ2, this.r);
    }

    public final void b(Canvas canvas) {
        List<Float> list;
        String str;
        this.m.setColor(ContextCompat.getColor(getContext(), R.color.chart_ytext_elec));
        if (this.f3471d == 2 || (list = this.x) == null || list.size() <= 0) {
            return;
        }
        float size = ((this.f3473f - this.i) - this.j) / (this.x.size() - 1);
        int i = 0;
        while (i < this.x.size()) {
            if (i == 0) {
                str = "-x";
            } else {
                str = Math.round(this.x.get(i).floatValue()) + "";
            }
            this.m.getTextBounds(str, 0, str.length(), this.t);
            float f2 = (this.f3473f - this.j) - (i * size);
            canvas.drawText(str, (this.f3469b / 2.0f) + this.l, (this.t.height() / 2.0f) + f2, this.m);
            if (i == 0) {
                canvas.drawLine(this.h, f2, this.f3472e - this.g, f2, this.s);
            } else {
                this.q.setColor(ContextCompat.getColor(getContext(), i != this.x.size() + (-1) ? R.color.chart_line_trans : R.color.chart_line));
                canvas.drawLine(this.h, f2, this.f3472e - this.g, f2, this.q);
            }
            i++;
        }
    }

    public final void c(Canvas canvas) {
        List<Float> list;
        this.m.setColor(ContextCompat.getColor(getContext(), R.color.chart_ytext_fuel));
        this.q.setColor(ContextCompat.getColor(getContext(), R.color.chart_line_trans));
        if (this.f3471d == 1 || (list = this.w) == null || list.size() <= 0) {
            return;
        }
        float size = ((this.f3473f - this.i) - this.j) / (this.w.size() - 1);
        int i = 0;
        while (i < this.w.size()) {
            String str = "";
            if (i != 0) {
                str = Math.round(this.w.get(i).floatValue()) + "";
            }
            this.m.getTextBounds(str, 0, str.length(), this.t);
            float f2 = (this.f3473f - this.j) - (i * size);
            canvas.drawText(str, (this.f3472e - (this.f3470c / 2.0f)) - this.k, (this.t.height() / 2.0f) + f2, this.m);
            if (this.f3471d == 2) {
                if (i == 0) {
                    canvas.drawLine(this.h, f2, this.f3472e - this.g, f2, this.s);
                } else {
                    this.q.setColor(ContextCompat.getColor(getContext(), i != this.w.size() - 1 ? R.color.chart_line_trans : R.color.chart_line));
                    canvas.drawLine(this.h, f2, this.f3472e - this.g, f2, this.q);
                }
            }
            i++;
        }
    }

    public final void d(Canvas canvas, int i, List<Float> list, List<EnergyBean> list2, float f2, int i2, int i3) {
        float f3;
        float fA;
        if (list == null || list2 == null) {
            return;
        }
        try {
            if (list.size() > 1 && list2.size() != 0) {
                float size = ((this.f3473f - this.i) - this.j) / (list.size() - 1);
                float f4 = size / f2;
                List<Float> list3 = this.y;
                if (list3 != null && list3.size() >= 2) {
                    float fFloatValue = this.y.get(0).floatValue();
                    List<Float> list4 = this.y;
                    boolean z = fFloatValue < list4.get(list4.size() - 1).floatValue();
                    float fFloatValue2 = this.y.get(0).floatValue();
                    List<Float> list5 = this.y;
                    float fMax = ((this.f3472e - this.h) - this.g) / (Math.max(fFloatValue2, list5.get(list5.size() - 1).floatValue()) / this.D);
                    ArrayList arrayList = new ArrayList();
                    int i4 = 0;
                    while (i4 < list2.size()) {
                        if (list2.get(i4).a() > 0.0d) {
                            fA = ((this.f3473f - this.j) - size) - ((float) (list2.get(i4).a() * ((double) f4)));
                            f3 = f4;
                        } else {
                            f3 = f4;
                            fA = ((this.f3473f - this.j) - size) + ((float) ((list2.get(i4).a() * ((double) size)) / ((double) list.get(0).floatValue())));
                        }
                        arrayList.add(new PointF(i(z ? this.h + (i4 * fMax) : (this.f3472e - this.g) - (((list2.size() - 1) - i4) * fMax)), j(fA)));
                        i4++;
                        f4 = f3;
                    }
                    List<PointF> listF = f(arrayList);
                    Path path = new Path();
                    path.moveTo(arrayList.get(0).x, arrayList.get(0).y);
                    for (int i5 = 1; i5 < arrayList.size(); i5++) {
                        int i6 = i5 * 2;
                        int i7 = i6 - 2;
                        int i8 = i6 - 1;
                        path.cubicTo(listF.get(i7).x, listF.get(i7).y, listF.get(i8).x, listF.get(i8).y, arrayList.get(i5).x, arrayList.get(i5).y);
                    }
                    this.o.setColor(i);
                    canvas.drawPath(path, this.o);
                    path.lineTo(arrayList.get(arrayList.size() - 1).x, this.f3473f - this.j);
                    path.lineTo(arrayList.get(0).x, this.f3473f - this.j);
                    path.lineTo(arrayList.get(0).x, arrayList.get(arrayList.size() - 1).y);
                    float f5 = this.h;
                    this.p.setShader(new LinearGradient(f5, this.i, f5, this.f3473f - this.j, new int[]{i2, i3}, (float[]) null, Shader.TileMode.CLAMP));
                    canvas.drawPath(path, this.p);
                }
            }
        } catch (Exception e2) {
            BydLogHelper.logE(e2.getMessage());
        }
    }

    public final void e(Canvas canvas) {
        List<Float> list = this.y;
        if (list == null || list.size() <= 0) {
            return;
        }
        try {
            this.q.setColor(ContextCompat.getColor(getContext(), R.color.chart_line_trans));
            float size = ((this.f3472e - this.h) - this.g) / (this.y.size() - 1);
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            String str = "";
            int i = 0;
            while (i < this.y.size()) {
                String str2 = decimalFormat.format(this.y.get(i)) + "";
                this.n.getTextBounds(str2, 0, str2.length(), this.t);
                float f2 = this.h + (i * size);
                canvas.drawText(str2, f2, this.f3473f - (this.t.height() / 2.0f), this.n);
                canvas.drawLine(f2, this.f3473f - this.j, f2, this.i, (i == 0 || i == this.y.size() + (-1)) ? this.s : this.q);
                i++;
                str = str2;
            }
            this.n.getTextBounds(this.E, 0, str.length(), this.t);
            canvas.drawText(this.E, (this.f3472e - this.g) + getResources().getDimensionPixelSize(R.dimen.dp_18) + (this.t.width() / 2.0f), this.f3473f - (this.t.height() / 2.0f), this.n);
        } catch (Exception e2) {
            BydLogHelper.logE(e2.getMessage());
        }
    }

    public final List<PointF> f(List<PointF> list) {
        if (list.size() == 1) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                arrayList.add(new PointF(i(list.get(i).x + ((list.get(i + 1).x - list.get(i).x) * 0.5f)), j(list.get(i).y)));
            } else if (i == list.size() - 1) {
                arrayList.add(new PointF(i(list.get(i).x - ((list.get(i).x - list.get(i - 1).x) * 0.5f)), j(list.get(i).y)));
            } else {
                int i2 = i + 1;
                int i3 = i - 1;
                float f2 = list.get(i2).x - list.get(i3).x != 0.0f ? (list.get(i2).y - list.get(i3).y) / (list.get(i2).x - list.get(i3).x) : 0.0f;
                float f3 = list.get(i).y - (list.get(i).x * f2);
                float fI = i(list.get(i).x - ((list.get(i).x - list.get(i3).x) * 0.5f));
                arrayList.add(new PointF(fI, j((fI * f2) + f3)));
                float fI2 = i(list.get(i).x + ((list.get(i2).x - list.get(i).x) * 0.5f));
                arrayList.add(new PointF(fI2, j((f2 * fI2) + f3)));
            }
        }
        return arrayList;
    }

    public final void g() {
        Paint paint = new Paint();
        this.q = paint;
        paint.setColor(ContextCompat.getColor(getContext(), R.color.chart_line));
        this.q.setStyle(Paint.Style.STROKE);
        this.q.setAntiAlias(true);
        this.q.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.dp_1));
        this.q.setPathEffect(new DashPathEffect(new float[]{getResources().getDimensionPixelSize(R.dimen.dp_4), getResources().getDimensionPixelSize(R.dimen.dp_4)}, 0.0f));
        Paint paint2 = new Paint();
        this.r = paint2;
        paint2.setColor(ContextCompat.getColor(getContext(), R.color.chart_line));
        this.r.setStyle(Paint.Style.STROKE);
        this.r.setAntiAlias(true);
        this.r.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.dp_2));
        this.r.setPathEffect(new DashPathEffect(new float[]{getResources().getDimensionPixelSize(R.dimen.dp_4), getResources().getDimensionPixelSize(R.dimen.dp_4)}, 0.0f));
        Paint paint3 = new Paint();
        this.s = paint3;
        paint3.setStyle(Paint.Style.STROKE);
        this.s.setAntiAlias(true);
        this.s.setColor(ContextCompat.getColor(getContext(), R.color.chart_solidline));
        this.s.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.dp_1));
        TextPaint textPaint = new TextPaint();
        this.m = textPaint;
        textPaint.setTextAlign(Paint.Align.CENTER);
        this.m.setAntiAlias(true);
        this.m.setTextSize(getResources().getDimension(R.dimen.sp_14));
        this.m.setColor(ContextCompat.getColor(getContext(), R.color.chart_ytext_elec));
        TextPaint textPaint2 = new TextPaint();
        this.n = textPaint2;
        textPaint2.setTextAlign(Paint.Align.CENTER);
        this.n.setAntiAlias(true);
        this.n.setTextSize(getResources().getDimension(R.dimen.sp_16));
        this.n.setColor(ContextCompat.getColor(getContext(), R.color.chart_xtext));
        Paint paint4 = new Paint();
        this.o = paint4;
        paint4.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.dp_1));
        this.o.setAntiAlias(true);
        this.o.setStyle(Paint.Style.STROKE);
        Paint paint5 = new Paint();
        this.p = paint5;
        paint5.setAntiAlias(true);
        this.p.setStyle(Paint.Style.FILL);
    }

    public final void h() {
        this.g = getResources().getDimensionPixelSize(R.dimen.dp_62);
        this.h = getResources().getDimensionPixelSize(R.dimen.dp_62);
        this.i = getResources().getDimensionPixelSize(R.dimen.dp_18);
        this.j = getResources().getDimensionPixelSize(R.dimen.dp_40);
        this.k = getResources().getDimensionPixelSize(R.dimen.dp_30);
        this.l = getResources().getDimensionPixelSize(R.dimen.dp_30);
    }

    public final float i(float f2) {
        float f3 = this.h;
        if (f2 < f3) {
            f2 = f3;
        }
        int i = this.f3472e;
        float f4 = this.g;
        return f2 > ((float) i) - f4 ? i - f4 : f2;
    }

    public final float j(float f2) {
        float f3 = this.i;
        if (f2 < f3) {
            f2 = f3;
        }
        int i = this.f3473f;
        float f4 = this.j;
        return f2 > ((float) i) - f4 ? i - f4 : f2;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        b(canvas);
        c(canvas);
        e(canvas);
        a(canvas);
        int i = this.f3471d;
        if (i == 1) {
            d(canvas, ContextCompat.getColor(getContext(), R.color.chart_line_elec), this.x, this.u, this.z, ContextCompat.getColor(getContext(), R.color.chart_fill_start_elec), ContextCompat.getColor(getContext(), R.color.chart_fill_end_elec));
            return;
        }
        if (i == 2) {
            d(canvas, ContextCompat.getColor(getContext(), R.color.chart_line_fuel), this.w, this.v, this.A, ContextCompat.getColor(getContext(), R.color.chart_fill_start_fuel), ContextCompat.getColor(getContext(), R.color.chart_fill_end_fuel));
        } else if (i == 0) {
            d(canvas, ContextCompat.getColor(getContext(), R.color.chart_line_elec), this.x, this.u, this.z, ContextCompat.getColor(getContext(), R.color.chart_fill_start_elec), ContextCompat.getColor(getContext(), R.color.chart_fill_end_elec));
            d(canvas, ContextCompat.getColor(getContext(), R.color.chart_line_fuel), this.w, this.v, this.A, ContextCompat.getColor(getContext(), R.color.chart_fill_start_fuel), ContextCompat.getColor(getContext(), R.color.chart_fill_end_fuel));
        }
    }

    @Override // android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f3472e = View.MeasureSpec.getSize(i);
        this.f3473f = View.MeasureSpec.getSize(i2);
    }

    public void setDrawType(int i) {
        this.f3471d = i;
        invalidate();
    }

    public void setElecLine(float f2) {
        this.G = f2;
        invalidate();
    }

    public void setFuelLine(float f2) {
        this.F = f2;
        invalidate();
    }

    public void setListXY(List<Float> list, List<Float> list2, List<Float> list3, List<EnergyBean> list4, List<EnergyBean> list5) {
        this.x.clear();
        this.w.clear();
        this.y.clear();
        this.u.clear();
        this.v.clear();
        this.x.addAll(list2);
        this.w.addAll(list3);
        this.y.addAll(list);
        this.u.addAll(list4);
        this.v.addAll(list5);
        if (list2 != null) {
            this.f3469b = 0;
            for (int i = 0; i < list2.size(); i++) {
                this.m.getTextBounds(list2.get(i) + "", 0, (list2.get(i) + "").length(), this.t);
                if (this.f3469b < this.t.width()) {
                    this.f3469b = this.t.width();
                }
            }
        }
        if (list3 != null) {
            this.f3470c = 0;
            for (int i2 = 0; i2 < list3.size(); i2++) {
                String str = list3.get(i2) + "";
                this.m.getTextBounds(str, 0, str.length(), this.t);
                if (this.f3470c < this.t.width()) {
                    this.f3470c = this.t.width();
                }
            }
        }
        invalidate();
    }

    public void setXUnit(String str) {
        this.E = str;
    }

    public void setXYInterval(float f2, float f3, float f4) {
        this.z = f2;
        this.A = f3;
        this.B = f4;
    }

    public BydEnergyChartView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BydEnergyChartView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3469b = 0;
        this.f3470c = 0;
        this.f3471d = -1;
        this.t = new Rect();
        this.u = new ArrayList();
        this.v = new ArrayList();
        this.w = new ArrayList();
        this.x = new ArrayList();
        this.y = new ArrayList();
        this.z = 15.0f;
        this.A = 10.0f;
        this.B = 10.0f;
        this.D = 0.2f;
        this.F = 0.0f;
        this.G = 0.0f;
        h();
        g();
    }
}
