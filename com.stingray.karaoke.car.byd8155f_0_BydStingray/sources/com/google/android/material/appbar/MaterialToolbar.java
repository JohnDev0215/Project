package com.google.android.material.appbar;

import F1.A;
import F1.H;
import K4.a;
import U4.k;
import Y4.g;
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
import b5.AbstractC0997a;
import com.bumptech.glide.d;
import com.stingray.drivemodedetector.R;

/* JADX INFO: loaded from: classes.dex */
public class MaterialToolbar extends Toolbar {

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public Integer f12742e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public boolean f12743f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public boolean f12744g0;

    public MaterialToolbar(Context context, AttributeSet attributeSet) {
        super(AbstractC0997a.a(context, attributeSet, R.attr.toolbarStyle, R.style.Widget_MaterialComponents_Toolbar), attributeSet, 0);
        Context context2 = getContext();
        TypedArray typedArrayE = k.e(context2, attributeSet, a.o, R.attr.toolbarStyle, R.style.Widget_MaterialComponents_Toolbar, new int[0]);
        if (typedArrayE.hasValue(0)) {
            setNavigationIconTint(typedArrayE.getColor(0, -1));
        }
        this.f12743f0 = typedArrayE.getBoolean(2, false);
        this.f12744g0 = typedArrayE.getBoolean(1, false);
        typedArrayE.recycle();
        Drawable background = getBackground();
        if (background == null || (background instanceof ColorDrawable)) {
            g gVar = new g();
            gVar.h(ColorStateList.valueOf(background != null ? ((ColorDrawable) background).getColor() : 0));
            gVar.f(context2);
            int[] iArr = H.f2303a;
            gVar.g(A.e(this));
            setBackground(gVar);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable background = getBackground();
        if (background instanceof g) {
            d.L(this, (g) background);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        super.onLayout(z6, i7, i10, i11, i12);
        if (this.f12743f0 || this.f12744g0) {
            TextView textViewD = k.d(this, getTitle());
            TextView textViewD2 = k.d(this, getSubtitle());
            if (textViewD == null && textViewD2 == null) {
                return;
            }
            int measuredWidth = getMeasuredWidth();
            int i13 = measuredWidth / 2;
            int paddingLeft = getPaddingLeft();
            int paddingRight = measuredWidth - getPaddingRight();
            for (int i14 = 0; i14 < getChildCount(); i14++) {
                View childAt = getChildAt(i14);
                if (childAt.getVisibility() != 8 && childAt != textViewD && childAt != textViewD2) {
                    if (childAt.getRight() < i13 && childAt.getRight() > paddingLeft) {
                        paddingLeft = childAt.getRight();
                    }
                    if (childAt.getLeft() > i13 && childAt.getLeft() < paddingRight) {
                        paddingRight = childAt.getLeft();
                    }
                }
            }
            Pair pair = new Pair(Integer.valueOf(paddingLeft), Integer.valueOf(paddingRight));
            if (this.f12743f0 && textViewD != null) {
                t(textViewD, pair);
            }
            if (!this.f12744g0 || textViewD2 == null) {
                return;
            }
            t(textViewD2, pair);
        }
    }

    @Override // android.view.View
    public void setElevation(float f10) {
        super.setElevation(f10);
        Drawable background = getBackground();
        if (background instanceof g) {
            ((g) background).g(f10);
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        Integer num;
        if (drawable != null && (num = this.f12742e0) != null) {
            drawable.setTint(num.intValue());
        }
        super.setNavigationIcon(drawable);
    }

    public void setNavigationIconTint(int i7) {
        this.f12742e0 = Integer.valueOf(i7);
        Drawable navigationIcon = getNavigationIcon();
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
    }

    public void setSubtitleCentered(boolean z6) {
        if (this.f12744g0 != z6) {
            this.f12744g0 = z6;
            requestLayout();
        }
    }

    public void setTitleCentered(boolean z6) {
        if (this.f12743f0 != z6) {
            this.f12743f0 = z6;
            requestLayout();
        }
    }

    public final void t(TextView textView, Pair pair) {
        int measuredWidth = getMeasuredWidth();
        int measuredWidth2 = textView.getMeasuredWidth();
        int i7 = (measuredWidth / 2) - (measuredWidth2 / 2);
        int i10 = measuredWidth2 + i7;
        int iMax = Math.max(Math.max(((Integer) pair.first).intValue() - i7, 0), Math.max(i10 - ((Integer) pair.second).intValue(), 0));
        if (iMax > 0) {
            i7 += iMax;
            i10 -= iMax;
            textView.measure(View.MeasureSpec.makeMeasureSpec(i10 - i7, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), textView.getMeasuredHeightAndState());
        }
        textView.layout(i7, textView.getTop(), i10, textView.getBottom());
    }
}
