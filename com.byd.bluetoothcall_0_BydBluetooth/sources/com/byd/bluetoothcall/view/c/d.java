package com.byd.bluetoothcall.view.c;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: LinearDividerItemDecoration.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends RecyclerView.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f3399a = {R.attr.listDivider};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f3400b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Drawable f3401c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3402d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3403e;
    private final Rect f = new Rect();
    private boolean g = true;

    public d(Context context, int i) {
        this.f3400b = context;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(f3399a);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        this.f3401c = drawable;
        if (drawable == null) {
            Log.w("DividerItem", "@android:attr/listDivider was not set in the theme used for this LinearDividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void k(Canvas canvas, RecyclerView recyclerView) {
        int width;
        int paddingLeft;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            paddingLeft = recyclerView.getPaddingLeft() - 110;
            width = (recyclerView.getWidth() - recyclerView.getPaddingRight()) - 24;
            canvas.clipRect(110, recyclerView.getPaddingTop(), width, recyclerView.getHeight() - recyclerView.getPaddingBottom());
        } else {
            width = recyclerView.getWidth();
            paddingLeft = 0;
        }
        int childCount = recyclerView.getChildCount();
        if (!this.g) {
            childCount--;
        }
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            recyclerView.j0(childAt, this.f);
            int iRound = this.f.bottom + Math.round(childAt.getTranslationY());
            this.f3401c.setBounds(paddingLeft, iRound - this.f3403e, width, iRound);
            this.f3401c.draw(canvas);
        }
        canvas.restore();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void e(Rect rect, View view, RecyclerView recyclerView, RecyclerView.z zVar) {
        if (this.f3401c == null) {
            rect.set(0, 0, 0, 0);
            return;
        }
        if (this.f3402d != 1) {
            int iB = zVar.b() - 1;
            int iF0 = recyclerView.f0(view);
            if (this.g || iF0 < iB) {
                rect.set(0, 0, this.f3403e, 0);
                return;
            } else {
                rect.set(0, 0, 0, 0);
                return;
            }
        }
        if (recyclerView.f0(view) != zVar.b() - 1) {
            rect.set(0, 0, 0, this.f3403e);
        } else if (this.g) {
            rect.set(0, 0, 0, this.f3403e);
        } else {
            rect.set(0, 0, 0, 0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
        if (recyclerView.getLayoutManager() == null || this.f3401c == null) {
            return;
        }
        k(canvas, recyclerView);
    }

    public int j(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void l(int i, int i2) {
        this.f3401c = new ColorDrawable(i);
        this.f3403e = j(this.f3400b, i2);
    }
}
