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

/* JADX INFO: compiled from: RtlTitleItemDecoration.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends RecyclerView.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f3418a = Color.parseColor("#00000000");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f3419b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static int f3420c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static int f3421d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static int f3422e;
    private List<com.byd.bluetoothcall.b.b> f;
    private Paint g;
    private Rect h;
    private int i;

    public h(Context context, List<com.byd.bluetoothcall.b.b> list) {
        f3420c = context.getColor(R.color.d9_text_color);
        if (com.byd.bluetoothcall.f.f.l(context) == 1) {
            f3419b = context.getColor(R.color.ts_list_title_bg_color);
            f3421d = context.getColor(R.color.ts_list_line_1);
        } else {
            f3419b = context.getColor(R.color.list_title_bg_color);
            f3421d = context.getColor(R.color.list_line_1);
        }
        this.f = list;
        this.g = new Paint();
        this.h = new Rect();
        this.i = (int) TypedValue.applyDimension(1, 50.0f, context.getResources().getDisplayMetrics());
        int iApplyDimension = (int) TypedValue.applyDimension(2, 20.0f, context.getResources().getDisplayMetrics());
        f3422e = iApplyDimension;
        this.g.setTextSize(iApplyDimension);
        this.g.setAntiAlias(true);
    }

    private void j(Canvas canvas, int i, int i2, View view, RecyclerView.p pVar, int i3) {
        this.g.setColor(f3418a);
        canvas.drawRect(i, (view.getTop() - ((ViewGroup.MarginLayoutParams) pVar).topMargin) - this.i, i2, view.getTop() - ((ViewGroup.MarginLayoutParams) pVar).topMargin, this.g);
        this.g.setColor(f3421d);
        this.g.setStrokeWidth(2.0f);
        canvas.drawLine(i + 40, view.getTop(), i2 - 24, view.getTop(), this.g);
        this.g.setColor(f3420c);
        this.g.getTextBounds(this.f.get(i3).d(), 0, this.f.get(i3).d().length(), this.h);
        canvas.drawText(this.f.get(i3).d(), view.getWidth() - 50, (view.getTop() - ((ViewGroup.MarginLayoutParams) pVar).topMargin) - (((this.i - 16) / 2) - (this.h.height() / 2)), this.g);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void e(Rect rect, View view, RecyclerView recyclerView, RecyclerView.z zVar) {
        super.e(rect, view, recyclerView, zVar);
        int iA = ((RecyclerView.p) view.getLayoutParams()).a();
        if (iA > -1) {
            if (iA == 0) {
                rect.set(0, this.i, 0, 0);
            } else if (this.f.get(iA).d() == null || this.f.get(iA).d().equals(this.f.get(iA - 1).d())) {
                rect.set(0, 0, 0, 0);
            } else {
                rect.set(0, this.i, 0, 0);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
        super.g(canvas, recyclerView, zVar);
        int paddingStart = recyclerView.getPaddingStart();
        int width = recyclerView.getWidth() - recyclerView.getPaddingEnd();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            RecyclerView.p pVar = (RecyclerView.p) childAt.getLayoutParams();
            int iA = pVar.a();
            if (iA > -1) {
                if (iA == 0) {
                    j(canvas, paddingStart, width, childAt, pVar, iA);
                } else if (this.f.get(iA).d() != null && !this.f.get(iA).d().equals(this.f.get(iA - 1).d())) {
                    j(canvas, paddingStart, width, childAt, pVar, iA);
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
        String strD = this.f.get(iA2).d();
        View view = recyclerView.a0(iA2).f1637b;
        int i = iA2 + 1;
        if (i >= this.f.size() || strD == null || strD.equals(this.f.get(i).d()) || view.getHeight() + view.getTop() > this.i) {
            z = false;
        } else {
            canvas.save();
            canvas.translate(0.0f, (view.getHeight() + view.getTop()) - this.i);
            z = true;
        }
        this.g.setColor(f3421d);
        this.g.setStrokeWidth(2.0f);
        canvas.drawLine(40.0f, this.i + 1, recyclerView.getWidth() - 24, this.i + 1, this.g);
        this.g.setColor(f3419b);
        canvas.drawRect(recyclerView.getPaddingStart() + 40, recyclerView.getPaddingTop(), (recyclerView.getRight() - recyclerView.getPaddingEnd()) - 20, recyclerView.getPaddingTop() + this.i, this.g);
        this.g.setColor(f3420c);
        this.g.getTextBounds(strD, 0, strD.length(), this.h);
        int paddingTop = recyclerView.getPaddingTop();
        int i2 = this.i;
        canvas.drawText(strD, view.getWidth() - 50, (paddingTop + i2) - (((i2 - 16) / 2) - (this.h.height() / 2)), this.g);
        if (z) {
            canvas.restore();
        }
    }
}
