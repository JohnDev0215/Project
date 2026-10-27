package com.byd.bluetoothcall.view.c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bydux.api.R;
import java.util.List;

/* JADX INFO: compiled from: TitleItemDecoration.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends RecyclerView.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private transient List<com.byd.bluetoothcall.b.b> f3435a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private transient Paint f3436b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private transient Rect f3437c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private transient int f3438d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3439e = Color.parseColor("#00000000");
    private int f;
    private int g;
    private int h;
    private int i;

    public k(Context context, List<com.byd.bluetoothcall.b.b> list) {
        this.g = context.getColor(R.color.d9_text_color);
        if (com.byd.bluetoothcall.f.f.l(context) == 1) {
            this.f = context.getColor(R.color.ts_list_title_bg_color);
            this.h = context.getColor(R.color.ts_list_line_1);
        } else {
            this.f = context.getColor(R.color.list_title_bg_color);
            this.h = context.getColor(R.color.list_line_1);
        }
        this.f3435a = list;
        this.f3436b = new Paint();
        this.f3437c = new Rect();
        this.f3438d = (int) TypedValue.applyDimension(1, 50.0f, context.getResources().getDisplayMetrics());
        int iApplyDimension = (int) TypedValue.applyDimension(2, 20.0f, context.getResources().getDisplayMetrics());
        this.i = iApplyDimension;
        this.f3436b.setTextSize(iApplyDimension);
        this.f3436b.setAntiAlias(true);
    }

    private void j(Canvas canvas, int i, int i2, View view, RecyclerView.p pVar, int i3) {
        this.f3436b.setColor(this.f3439e);
        canvas.drawRect(i, (view.getTop() - ((ViewGroup.MarginLayoutParams) pVar).topMargin) - this.f3438d, i2, view.getTop() - ((ViewGroup.MarginLayoutParams) pVar).topMargin, this.f3436b);
        this.f3436b.setColor(this.h);
        this.f3436b.setStrokeWidth(2.0f);
        canvas.drawLine(i + 40, view.getTop(), i2 - 24, view.getTop(), this.f3436b);
        this.f3436b.setColor(this.g);
        this.f3436b.getTextBounds(this.f3435a.get(i3).d(), 0, this.f3435a.get(i3).d().length(), this.f3437c);
        canvas.drawText(this.f3435a.get(i3).d(), view.getPaddingLeft() + 50, (view.getTop() - ((ViewGroup.MarginLayoutParams) pVar).topMargin) - (((this.f3438d - 16) / 2) - (this.f3437c.height() / 2)), this.f3436b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void e(Rect rect, View view, RecyclerView recyclerView, RecyclerView.z zVar) {
        super.e(rect, view, recyclerView, zVar);
        int iA = ((RecyclerView.p) view.getLayoutParams()).a();
        if (iA > -1) {
            if (iA == 0) {
                rect.set(0, this.f3438d, 0, 0);
            } else if (this.f3435a.get(iA).d() == null || this.f3435a.get(iA).d().equals(this.f3435a.get(iA - 1).d())) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(0, this.f3438d, 0, 0);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
        super.g(canvas, recyclerView, zVar);
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            RecyclerView.p pVar = (RecyclerView.p) childAt.getLayoutParams();
            int iA = pVar.a();
            if (iA > -1) {
                if (iA == 0) {
                    j(canvas, paddingLeft, width, childAt, pVar, iA);
                } else if (this.f3435a.get(iA).d() != null && !this.f3435a.get(iA).d().equals(this.f3435a.get(iA - 1).d())) {
                    j(canvas, paddingLeft, width, childAt, pVar, iA);
                }
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void i(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
        boolean z;
        int iA2 = ((LinearLayoutManager) recyclerView.getLayoutManager()).a2();
        if (iA2 == -1) {
            return;
        }
        String strD = this.f3435a.get(iA2).d();
        View view = recyclerView.a0(iA2).f1637b;
        int i = iA2 + 1;
        if (i >= this.f3435a.size() || strD == null || strD.equals(this.f3435a.get(i).d()) || view.getHeight() + view.getTop() > this.f3438d) {
            z = false;
        } else {
            canvas.save();
            canvas.translate(0.0f, (view.getHeight() + view.getTop()) - this.f3438d);
            z = true;
        }
        this.f3436b.setColor(this.h);
        this.f3436b.setStrokeWidth(2.0f);
        canvas.drawLine(40.0f, this.f3438d + 1, recyclerView.getWidth() - 24, this.f3438d + 1, this.f3436b);
        this.f3436b.setColor(this.f);
        canvas.drawRect(recyclerView.getPaddingLeft(), recyclerView.getPaddingTop(), (recyclerView.getRight() - recyclerView.getPaddingRight()) - 20, recyclerView.getPaddingTop() + this.f3438d, this.f3436b);
        this.f3436b.setColor(this.g);
        this.f3436b.getTextBounds(strD, 0, strD.length(), this.f3437c);
        float paddingLeft = view.getPaddingLeft() + 50;
        int paddingTop = recyclerView.getPaddingTop();
        int i2 = this.f3438d;
        canvas.drawText(strD, paddingLeft, (paddingTop + i2) - (((i2 - 16) / 2) - (this.f3437c.height() / 2)), this.f3436b);
        if (z) {
            canvas.restore();
        }
    }
}
