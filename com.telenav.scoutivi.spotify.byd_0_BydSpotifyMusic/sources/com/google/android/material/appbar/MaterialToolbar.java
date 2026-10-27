package com.google.android.material.appbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.internal.j;
import e3.g;
import e3.h;
import i0.u;
import o2.k;
import o2.l;

/* JADX INFO: loaded from: classes.dex */
public class MaterialToolbar extends Toolbar {

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    private static final int f4061a0 = k.Widget_MaterialComponents_Toolbar;
    private Integer U;
    private boolean V;
    private boolean W;

    public MaterialToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, o2.b.toolbarStyle);
    }

    private Pair<Integer, Integer> O(TextView textView, TextView textView2) {
        int measuredWidth = getMeasuredWidth();
        int i7 = measuredWidth / 2;
        int paddingLeft = getPaddingLeft();
        int paddingRight = measuredWidth - getPaddingRight();
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8 && childAt != textView && childAt != textView2) {
                if (childAt.getRight() < i7 && childAt.getRight() > paddingLeft) {
                    paddingLeft = childAt.getRight();
                }
                if (childAt.getLeft() > i7 && childAt.getLeft() < paddingRight) {
                    paddingRight = childAt.getLeft();
                }
            }
        }
        return new Pair<>(Integer.valueOf(paddingLeft), Integer.valueOf(paddingRight));
    }

    private void P(Context context) {
        Drawable background = getBackground();
        if (background == null || (background instanceof ColorDrawable)) {
            g gVar = new g();
            gVar.X(ColorStateList.valueOf(background != null ? ((ColorDrawable) background).getColor() : 0));
            gVar.M(context);
            gVar.W(u.t(this));
            u.n0(this, gVar);
        }
    }

    private void Q(View view, Pair<Integer, Integer> pair) {
        int measuredWidth = getMeasuredWidth();
        int measuredWidth2 = view.getMeasuredWidth();
        int i7 = (measuredWidth / 2) - (measuredWidth2 / 2);
        int i8 = measuredWidth2 + i7;
        int iMax = Math.max(Math.max(((Integer) pair.first).intValue() - i7, 0), Math.max(i8 - ((Integer) pair.second).intValue(), 0));
        if (iMax > 0) {
            i7 += iMax;
            i8 -= iMax;
            view.measure(View.MeasureSpec.makeMeasureSpec(i8 - i7, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), view.getMeasuredHeightAndState());
        }
        view.layout(i7, view.getTop(), i8, view.getBottom());
    }

    private void R() {
        if (this.V || this.W) {
            TextView textViewC = com.google.android.material.internal.k.c(this);
            TextView textViewA = com.google.android.material.internal.k.a(this);
            if (textViewC == null && textViewA == null) {
                return;
            }
            Pair<Integer, Integer> pairO = O(textViewC, textViewA);
            if (this.V && textViewC != null) {
                Q(textViewC, pairO);
            }
            if (!this.W || textViewA == null) {
                return;
            }
            Q(textViewA, pairO);
        }
    }

    private Drawable S(Drawable drawable) {
        if (drawable == null || this.U == null) {
            return drawable;
        }
        Drawable drawableL = a0.a.l(drawable);
        a0.a.h(drawableL, this.U.intValue());
        return drawableL;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        h.e(this);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        super.onLayout(z6, i7, i8, i9, i10);
        R();
    }

    @Override // android.view.View
    public void setElevation(float f7) {
        super.setElevation(f7);
        h.d(this, f7);
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        super.setNavigationIcon(S(drawable));
    }

    public void setNavigationIconTint(int i7) {
        this.U = Integer.valueOf(i7);
        Drawable navigationIcon = getNavigationIcon();
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
    }

    public void setSubtitleCentered(boolean z6) {
        if (this.W != z6) {
            this.W = z6;
            requestLayout();
        }
    }

    public void setTitleCentered(boolean z6) {
        if (this.V != z6) {
            this.V = z6;
            requestLayout();
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialToolbar(Context context, AttributeSet attributeSet, int i7) {
        int i8 = f4061a0;
        super(f3.a.c(context, attributeSet, i7, i8), attributeSet, i7);
        Context context2 = getContext();
        TypedArray typedArrayH = j.h(context2, attributeSet, l.MaterialToolbar, i7, i8, new int[0]);
        int i9 = l.MaterialToolbar_navigationIconTint;
        if (typedArrayH.hasValue(i9)) {
            setNavigationIconTint(typedArrayH.getColor(i9, -1));
        }
        this.V = typedArrayH.getBoolean(l.MaterialToolbar_titleCentered, false);
        this.W = typedArrayH.getBoolean(l.MaterialToolbar_subtitleCentered, false);
        typedArrayH.recycle();
        P(context2);
    }
}
