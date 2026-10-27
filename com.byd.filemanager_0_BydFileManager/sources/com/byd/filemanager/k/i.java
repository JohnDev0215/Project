package com.byd.filemanager.k;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: PaddingItemDecoration.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends RecyclerView.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int[] f3628a = {R.attr.listDivider};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f3629b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3630c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Drawable f3631d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3632e;
    private final Rect f;

    public i(Context context, int i, int i2) {
        this(context, 1);
        this.f3629b = context.getResources().getDimensionPixelSize(i);
        this.f3630c = context.getResources().getDimensionPixelSize(i2);
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
            recyclerView.getLayoutManager().Z(childAt, this.f);
            int iRound = this.f.right + Math.round(childAt.getTranslationX());
            this.f3631d.setBounds(iRound - this.f3631d.getIntrinsicWidth(), paddingTop, iRound, height);
            this.f3631d.draw(canvas);
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
            recyclerView.i0(childAt, this.f);
            int iRound = this.f.bottom + Math.round(childAt.getTranslationY());
            this.f3631d.setBounds(this.f3629b + paddingLeft, iRound - this.f3631d.getIntrinsicHeight(), width - this.f3630c, iRound);
            this.f3631d.draw(canvas);
        }
        canvas.restore();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void e(Rect rect, View view, RecyclerView recyclerView, RecyclerView.z zVar) {
        Drawable drawable = this.f3631d;
        if (drawable == null) {
            rect.set(0, 0, 0, 0);
        } else if (this.f3632e == 1) {
            rect.set(0, 0, 0, drawable.getIntrinsicHeight());
        } else {
            rect.set(0, 0, drawable.getIntrinsicWidth(), 0);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.n
    public void g(Canvas canvas, RecyclerView recyclerView, RecyclerView.z zVar) {
        if (recyclerView.getLayoutManager() == null || this.f3631d == null) {
            return;
        }
        if (this.f3632e == 1) {
            k(canvas, recyclerView);
        } else {
            j(canvas, recyclerView);
        }
    }

    public void l(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable cannot be null.");
        }
        this.f3631d = drawable;
    }

    public void m(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        this.f3632e = i;
    }

    public i(Context context, int i) {
        this.f = new Rect();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(f3628a);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
        this.f3631d = drawable;
        if (drawable == null) {
            Log.w("DividerItem", "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        typedArrayObtainStyledAttributes.recycle();
        m(i);
    }
}
