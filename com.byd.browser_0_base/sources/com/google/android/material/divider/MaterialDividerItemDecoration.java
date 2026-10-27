package com.google.android.material.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.in3;
import defpackage.om3;
import defpackage.r83;
import defpackage.rc1;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialDividerItemDecoration extends RecyclerView.l {
    public static final int i = R$style.Widget_MaterialComponents_MaterialDivider;
    public Drawable a;
    public final int b;
    public int c;
    public int d;
    public final int e;
    public final int f;
    public final boolean g;
    public final Rect h;

    public MaterialDividerItemDecoration(Context context, AttributeSet attributeSet, int i2) {
        int i3 = R$attr.materialDividerStyle;
        this.h = new Rect();
        TypedArray typedArrayD = r83.d(context, attributeSet, R$styleable.MaterialDivider, i3, i, new int[0]);
        this.c = rc1.a(context, typedArrayD, R$styleable.MaterialDivider_dividerColor).getDefaultColor();
        this.b = typedArrayD.getDimensionPixelSize(R$styleable.MaterialDivider_dividerThickness, context.getResources().getDimensionPixelSize(R$dimen.material_divider_thickness));
        this.e = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialDivider_dividerInsetStart, 0);
        this.f = typedArrayD.getDimensionPixelOffset(R$styleable.MaterialDivider_dividerInsetEnd, 0);
        this.g = typedArrayD.getBoolean(R$styleable.MaterialDivider_lastItemDecorated, true);
        typedArrayD.recycle();
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        int i4 = this.c;
        this.c = i4;
        this.a = shapeDrawable;
        shapeDrawable.setTint(i4);
        if (i2 == 0 || i2 == 1) {
            this.d = i2;
            return;
        }
        throw new IllegalArgumentException("Invalid orientation: " + i2 + ". It should be either HORIZONTAL or VERTICAL");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public final void g(Rect rect, View view, RecyclerView recyclerView, RecyclerView.w wVar) {
        rect.set(0, 0, 0, 0);
        if (!this.g) {
            recyclerView.getClass();
            RecyclerView.a0 a0VarK = RecyclerView.K(view);
            if ((a0VarK != null ? a0VarK.g() : -1) == wVar.b() - 1) {
                return;
            }
        }
        int i2 = this.d;
        int i3 = this.b;
        if (i2 == 1) {
            rect.bottom = this.a.getIntrinsicHeight() + i3;
        } else {
            rect.right = this.a.getIntrinsicWidth() + i3;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.l
    public final void h(Canvas canvas, RecyclerView recyclerView) {
        int height;
        int paddingTop;
        int width;
        int paddingLeft;
        if (recyclerView.getLayoutManager() == null) {
            return;
        }
        int i2 = this.d;
        int i3 = this.b;
        Rect rect = this.h;
        int i4 = this.f;
        int i5 = this.e;
        int i6 = 0;
        if (i2 != 1) {
            canvas.save();
            if (recyclerView.getClipToPadding()) {
                paddingTop = recyclerView.getPaddingTop();
                height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
                canvas.clipRect(recyclerView.getPaddingLeft(), paddingTop, recyclerView.getWidth() - recyclerView.getPaddingRight(), height);
            } else {
                height = recyclerView.getHeight();
                paddingTop = 0;
            }
            int i7 = paddingTop + i5;
            int i8 = height - i4;
            int childCount = recyclerView.getChildCount();
            while (i6 < childCount) {
                View childAt = recyclerView.getChildAt(i6);
                recyclerView.getLayoutManager().getClass();
                RecyclerView.L(childAt, rect);
                int iRound = Math.round(childAt.getTranslationX()) + rect.right;
                this.a.setBounds((iRound - this.a.getIntrinsicWidth()) - i3, i7, iRound, i8);
                this.a.draw(canvas);
                i6++;
            }
            canvas.restore();
            return;
        }
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            paddingLeft = recyclerView.getPaddingLeft();
            width = recyclerView.getWidth() - recyclerView.getPaddingRight();
            canvas.clipRect(paddingLeft, recyclerView.getPaddingTop(), width, recyclerView.getHeight() - recyclerView.getPaddingBottom());
        } else {
            width = recyclerView.getWidth();
            paddingLeft = 0;
        }
        WeakHashMap<View, in3> weakHashMap = om3.a;
        boolean z = om3.e.c(recyclerView) == 1;
        int i9 = paddingLeft + (z ? i4 : i5);
        if (z) {
            i4 = i5;
        }
        int i10 = width - i4;
        int childCount2 = recyclerView.getChildCount();
        if (!this.g) {
            childCount2--;
        }
        while (i6 < childCount2) {
            View childAt2 = recyclerView.getChildAt(i6);
            RecyclerView.L(childAt2, rect);
            int iRound2 = Math.round(childAt2.getTranslationY()) + rect.bottom;
            this.a.setBounds(i9, (iRound2 - this.a.getIntrinsicHeight()) - i3, i10, iRound2);
            this.a.draw(canvas);
            i6++;
        }
        canvas.restore();
    }
}
