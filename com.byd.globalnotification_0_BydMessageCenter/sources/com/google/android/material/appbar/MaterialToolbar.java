package com.google.android.material.appbar;

import a.b.k.l;
import a.h.k.q;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import b.d.a.a.b;
import b.d.a.a.e0.g;
import b.d.a.a.i0.a.a;
import b.d.a.a.j;
import b.d.a.a.z.k;

/* JADX INFO: loaded from: classes.dex */
public class MaterialToolbar extends Toolbar {
    public static final int Q = j.Widget_MaterialComponents_Toolbar;
    public Integer N;
    public boolean O;
    public boolean P;

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialToolbar(Context context, AttributeSet attributeSet) {
        int i = b.toolbarStyle;
        super(a.a(context, attributeSet, i, Q), attributeSet, i);
        Context context2 = getContext();
        TypedArray typedArrayD = k.d(context2, attributeSet, b.d.a.a.k.MaterialToolbar, i, Q, new int[0]);
        if (typedArrayD.hasValue(b.d.a.a.k.MaterialToolbar_navigationIconTint)) {
            setNavigationIconTint(typedArrayD.getColor(b.d.a.a.k.MaterialToolbar_navigationIconTint, -1));
        }
        this.O = typedArrayD.getBoolean(b.d.a.a.k.MaterialToolbar_titleCentered, false);
        this.P = typedArrayD.getBoolean(b.d.a.a.k.MaterialToolbar_subtitleCentered, false);
        typedArrayD.recycle();
        Drawable background = getBackground();
        if (background == null || (background instanceof ColorDrawable)) {
            g gVar = new g();
            gVar.q(ColorStateList.valueOf(background != null ? ((ColorDrawable) background).getColor() : 0));
            gVar.f1828a.f1836b = new b.d.a.a.w.a(context2);
            gVar.x();
            gVar.p(q.l(this));
            setBackground(gVar);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable background = getBackground();
        if (background instanceof g) {
            l.i.r0(this, (g) background);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.O || this.P) {
            TextView textViewG = l.i.G(this, getTitle());
            TextView textViewG2 = l.i.G(this, getSubtitle());
            if (textViewG == null && textViewG2 == null) {
                return;
            }
            int measuredWidth = getMeasuredWidth();
            int i5 = measuredWidth / 2;
            int paddingLeft = getPaddingLeft();
            int paddingRight = measuredWidth - getPaddingRight();
            for (int i6 = 0; i6 < getChildCount(); i6++) {
                View childAt = getChildAt(i6);
                if (childAt.getVisibility() != 8 && childAt != textViewG && childAt != textViewG2) {
                    if (childAt.getRight() < i5 && childAt.getRight() > paddingLeft) {
                        paddingLeft = childAt.getRight();
                    }
                    if (childAt.getLeft() > i5 && childAt.getLeft() < paddingRight) {
                        paddingRight = childAt.getLeft();
                    }
                }
            }
            Pair<Integer, Integer> pair = new Pair<>(Integer.valueOf(paddingLeft), Integer.valueOf(paddingRight));
            if (this.O && textViewG != null) {
                w(textViewG, pair);
            }
            if (!this.P || textViewG2 == null) {
                return;
            }
            w(textViewG2, pair);
        }
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        l.i.l0(this, f2);
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        Integer num;
        if (drawable != null && (num = this.N) != null) {
            drawable.setTint(num.intValue());
        }
        super.setNavigationIcon(drawable);
    }

    public void setNavigationIconTint(int i) {
        this.N = Integer.valueOf(i);
        Drawable navigationIcon = getNavigationIcon();
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
    }

    public void setSubtitleCentered(boolean z) {
        if (this.P != z) {
            this.P = z;
            requestLayout();
        }
    }

    public void setTitleCentered(boolean z) {
        if (this.O != z) {
            this.O = z;
            requestLayout();
        }
    }

    public final void w(View view, Pair<Integer, Integer> pair) {
        int measuredWidth = getMeasuredWidth();
        int measuredWidth2 = view.getMeasuredWidth();
        int i = (measuredWidth / 2) - (measuredWidth2 / 2);
        int i2 = measuredWidth2 + i;
        int iMax = Math.max(Math.max(((Integer) pair.first).intValue() - i, 0), Math.max(i2 - ((Integer) pair.second).intValue(), 0));
        if (iMax > 0) {
            i += iMax;
            i2 -= iMax;
            view.measure(View.MeasureSpec.makeMeasureSpec(i2 - i, 1073741824), view.getMeasuredHeightAndState());
        }
        view.layout(i, view.getTop(), i2, view.getBottom());
    }
}
