package com.google.android.material.appbar;

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
import com.spotify.music.R;
import java.util.WeakHashMap;
import p.f60;
import p.he1;
import p.it2;
import p.ot2;
import p.qf1;
import p.v03;
import p.xz1;
import p.ya0;
import p.zt2;

/* JADX INFO: loaded from: classes.dex */
public class MaterialToolbar extends Toolbar {
    public Integer c0;
    public boolean d0;
    public boolean e0;

    public MaterialToolbar(Context context, AttributeSet attributeSet) {
        super(f60.S(context, attributeSet, R.attr.toolbarStyle, R.style.Widget_MaterialComponents_Toolbar), attributeSet, 0);
        Context context2 = getContext();
        TypedArray typedArrayE = v03.E(context2, attributeSet, xz1.t, R.attr.toolbarStyle, R.style.Widget_MaterialComponents_Toolbar, new int[0]);
        if (typedArrayE.hasValue(0)) {
            setNavigationIconTint(typedArrayE.getColor(0, -1));
        }
        this.d0 = typedArrayE.getBoolean(2, false);
        this.e0 = typedArrayE.getBoolean(1, false);
        typedArrayE.recycle();
        Drawable background = getBackground();
        if (background == null || (background instanceof ColorDrawable)) {
            qf1 qf1Var = new qf1();
            qf1Var.k(ColorStateList.valueOf(background != null ? ((ColorDrawable) background).getColor() : 0));
            qf1Var.i(context2);
            WeakHashMap weakHashMap = zt2.a;
            qf1Var.j(ot2.i(this));
            it2.q(this, qf1Var);
        }
    }

    public Integer getNavigationIconTint() {
        return this.c0;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable background = getBackground();
        if (background instanceof qf1) {
            f60.I(this, (qf1) background);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.d0 || this.e0) {
            TextView textViewU = he1.u(this, getTitle());
            TextView textViewU2 = he1.u(this, getSubtitle());
            if (textViewU == null && textViewU2 == null) {
                return;
            }
            int measuredWidth = getMeasuredWidth();
            int i5 = measuredWidth / 2;
            int paddingLeft = getPaddingLeft();
            int paddingRight = measuredWidth - getPaddingRight();
            for (int i6 = 0; i6 < getChildCount(); i6++) {
                View childAt = getChildAt(i6);
                if (childAt.getVisibility() != 8 && childAt != textViewU && childAt != textViewU2) {
                    if (childAt.getRight() < i5 && childAt.getRight() > paddingLeft) {
                        paddingLeft = childAt.getRight();
                    }
                    if (childAt.getLeft() > i5 && childAt.getLeft() < paddingRight) {
                        paddingRight = childAt.getLeft();
                    }
                }
            }
            Pair pair = new Pair(Integer.valueOf(paddingLeft), Integer.valueOf(paddingRight));
            if (this.d0 && textViewU != null) {
                v(textViewU, pair);
            }
            if (!this.e0 || textViewU2 == null) {
                return;
            }
            v(textViewU2, pair);
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        Drawable background = getBackground();
        if (background instanceof qf1) {
            ((qf1) background).j(f);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null && this.c0 != null) {
            drawable = drawable.mutate();
            ya0.g(drawable, this.c0.intValue());
        }
        super.setNavigationIcon(drawable);
    }

    public void setNavigationIconTint(int i) {
        this.c0 = Integer.valueOf(i);
        Drawable navigationIcon = getNavigationIcon();
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
    }

    public void setSubtitleCentered(boolean z) {
        if (this.e0 != z) {
            this.e0 = z;
            requestLayout();
        }
    }

    public void setTitleCentered(boolean z) {
        if (this.d0 != z) {
            this.d0 = z;
            requestLayout();
        }
    }

    public final void v(TextView textView, Pair pair) {
        int measuredWidth = getMeasuredWidth();
        int measuredWidth2 = textView.getMeasuredWidth();
        int i = (measuredWidth / 2) - (measuredWidth2 / 2);
        int i2 = measuredWidth2 + i;
        int iMax = Math.max(Math.max(((Integer) pair.first).intValue() - i, 0), Math.max(i2 - ((Integer) pair.second).intValue(), 0));
        if (iMax > 0) {
            i += iMax;
            i2 -= iMax;
            textView.measure(View.MeasureSpec.makeMeasureSpec(i2 - i, 1073741824), textView.getMeasuredHeightAndState());
        }
        textView.layout(i, textView.getTop(), i2, textView.getBottom());
    }
}
