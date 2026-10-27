package com.byd.cleardata.i;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class k extends RecyclerView.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f2712a = {R.attr.listDivider};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2713b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2714c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Drawable f2715d;
    private int e;
    private final Rect f;

    public k(Context context, int i) {
        this.f = new Rect();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(f2712a);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        this.f2715d = drawable;
        if (drawable == null) {
            Log.w("DividerItem", "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        typedArrayObtainStyledAttributes.recycle();
        m(i);
    }

    public k(Context context, int i, int i2) {
        this(context, 1);
        this.f2713b = context.getResources().getDimensionPixelSize(i);
        this.f2714c = context.getResources().getDimensionPixelSize(i2);
    }

    private void j(Canvas canvas, RecyclerView recyclerView) {
        int height;
        int paddingTop;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            paddingTop = recyclerView.getPaddingTop();
            height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
            canvas.clipRect(recyclerView.getPaddingLeft(), paddingTop, recyclerView.getWidth() - recyclerView.getPaddingRight(), height);
        } else {
            height = recyclerView.getHeight();
            paddingTop = 0;
        }
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            recyclerView.getLayoutManager().l0(childAt, this.f);
            int iRound = this.f.right + Math.round(childAt.getTranslationX());
            this.f2715d.setBounds(iRound - this.f2715d.getIntrinsicWidth(), paddingTop, iRound, height);
            this.f2715d.draw(canvas);
        }
        canvas.restore();
    }

    private void k(Canvas canvas, RecyclerView recyclerView) {
        int width;
        int paddingLeft;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            paddingLeft = recyclerView.getPaddingLeft();
            width = recyclerView.getWidth() - recyclerView.getPaddingRight();
            canvas.clipRect(paddingLeft, recyclerView.getPaddingTop(), width, recyclerView.getHeight() - recyclerView.getPaddingBottom());
        } else {
            width = recyclerView.getWidth();
            paddingLeft = 0;
        }
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            recyclerView.h0(childAt, this.f);
            int iRound = this.f.bottom + Math.round(childAt.getTranslationY());
            this.f2715d.setBounds(this.f2713b + paddingLeft, iRound - this.f2715d.getIntrinsicHeight(), width - this.f2714c, iRound);
            this.f2715d.draw(canvas);
        }
        canvas.restore();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void e(Rect rect, View view, RecyclerView recyclerView, RecyclerView.a0 a0Var) {
        Drawable drawable = this.f2715d;
        if (drawable == null) {
            rect.set(0, 0, 0, 0);
        } else if (this.e == 1) {
            rect.set(0, 0, 0, drawable.getIntrinsicHeight());
        } else {
            rect.set(0, 0, drawable.getIntrinsicWidth(), 0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.a0 a0Var) {
        if (recyclerView.getLayoutManager() == null || this.f2715d == null) {
            return;
        }
        if (this.e == 1) {
            k(canvas, recyclerView);
        } else {
            j(canvas, recyclerView);
        }
    }

    public void l(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable cannot be null.");
        }
        this.f2715d = drawable;
    }

    public void m(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        this.e = i;
    }
}
