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
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.R$attr;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import defpackage.id3;
import defpackage.in3;
import defpackage.oj2;
import defpackage.om3;
import defpackage.r83;
import defpackage.sc1;
import defpackage.uc1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialToolbar extends Toolbar {
    public static final int g0 = R$style.Widget_MaterialComponents_Toolbar;
    public static final ImageView.ScaleType[] h0 = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    public Integer b0;
    public boolean c0;
    public boolean d0;
    public ImageView.ScaleType e0;
    public Boolean f0;

    public MaterialToolbar(Context context) {
        this(context, null);
    }

    public ImageView.ScaleType getLogoScaleType() {
        return this.e0;
    }

    public Integer getNavigationIconTint() {
        return this.b0;
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        oj2.d0(this);
    }

    @Override // androidx.appcompat.widget.Toolbar, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        ImageView imageView;
        Drawable drawable;
        super.onLayout(z, i, i2, i3, i4);
        int i5 = 0;
        ImageView imageView2 = null;
        if (this.c0 || this.d0) {
            ArrayList arrayListA = id3.a(this, getTitle());
            boolean zIsEmpty = arrayListA.isEmpty();
            id3.a aVar = id3.a;
            TextView textView = zIsEmpty ? null : (TextView) Collections.min(arrayListA, aVar);
            ArrayList arrayListA2 = id3.a(this, getSubtitle());
            TextView textView2 = arrayListA2.isEmpty() ? null : (TextView) Collections.max(arrayListA2, aVar);
            if (textView != null || textView2 != null) {
                int measuredWidth = getMeasuredWidth();
                int i6 = measuredWidth / 2;
                int paddingLeft = getPaddingLeft();
                int paddingRight = measuredWidth - getPaddingRight();
                for (int i7 = 0; i7 < getChildCount(); i7++) {
                    View childAt = getChildAt(i7);
                    if (childAt.getVisibility() != 8 && childAt != textView && childAt != textView2) {
                        if (childAt.getRight() < i6 && childAt.getRight() > paddingLeft) {
                            paddingLeft = childAt.getRight();
                        }
                        if (childAt.getLeft() > i6 && childAt.getLeft() < paddingRight) {
                            paddingRight = childAt.getLeft();
                        }
                    }
                }
                Pair pair = new Pair(Integer.valueOf(paddingLeft), Integer.valueOf(paddingRight));
                if (this.c0 && textView != null) {
                    x(textView, pair);
                }
                if (this.d0 && textView2 != null) {
                    x(textView2, pair);
                }
            }
        }
        Drawable logo = getLogo();
        if (logo != null) {
            while (true) {
                if (i5 >= getChildCount()) {
                    break;
                }
                View childAt2 = getChildAt(i5);
                if ((childAt2 instanceof ImageView) && (drawable = (imageView = (ImageView) childAt2).getDrawable()) != null && drawable.getConstantState() != null && drawable.getConstantState().equals(logo.getConstantState())) {
                    imageView2 = imageView;
                    break;
                }
                i5++;
            }
        }
        if (imageView2 != null) {
            Boolean bool = this.f0;
            if (bool != null) {
                imageView2.setAdjustViewBounds(bool.booleanValue());
            }
            ImageView.ScaleType scaleType = this.e0;
            if (scaleType != null) {
                imageView2.setScaleType(scaleType);
            }
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        oj2.c0(this, f);
    }

    public void setLogoAdjustViewBounds(boolean z) {
        Boolean bool = this.f0;
        if (bool == null || bool.booleanValue() != z) {
            this.f0 = Boolean.valueOf(z);
            requestLayout();
        }
    }

    public void setLogoScaleType(ImageView.ScaleType scaleType) {
        if (this.e0 != scaleType) {
            this.e0 = scaleType;
            requestLayout();
        }
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null && this.b0 != null) {
            drawable = drawable.mutate();
            drawable.setTint(this.b0.intValue());
        }
        super.setNavigationIcon(drawable);
    }

    public void setNavigationIconTint(int i) {
        this.b0 = Integer.valueOf(i);
        Drawable navigationIcon = getNavigationIcon();
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
    }

    public void setSubtitleCentered(boolean z) {
        if (this.d0 != z) {
            this.d0 = z;
            requestLayout();
        }
    }

    public void setTitleCentered(boolean z) {
        if (this.c0 != z) {
            this.c0 = z;
            requestLayout();
        }
    }

    public final void x(TextView textView, Pair pair) {
        int measuredWidth = getMeasuredWidth();
        int measuredWidth2 = textView.getMeasuredWidth();
        int i = (measuredWidth / 2) - (measuredWidth2 / 2);
        int i2 = measuredWidth2 + i;
        int iMax = Math.max(Math.max(((Integer) pair.first).intValue() - i, 0), Math.max(i2 - ((Integer) pair.second).intValue(), 0));
        if (iMax > 0) {
            i += iMax;
            i2 -= iMax;
            textView.measure(View.MeasureSpec.makeMeasureSpec(i2 - i, TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), textView.getMeasuredHeightAndState());
        }
        textView.layout(i, textView.getTop(), i2, textView.getBottom());
    }

    public MaterialToolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.toolbarStyle);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MaterialToolbar(Context context, AttributeSet attributeSet, int i) {
        int i2 = g0;
        super(uc1.a(context, attributeSet, i, i2), attributeSet, i);
        Context context2 = getContext();
        TypedArray typedArrayD = r83.d(context2, attributeSet, R$styleable.MaterialToolbar, i, i2, new int[0]);
        int i3 = R$styleable.MaterialToolbar_navigationIconTint;
        if (typedArrayD.hasValue(i3)) {
            setNavigationIconTint(typedArrayD.getColor(i3, -1));
        }
        this.c0 = typedArrayD.getBoolean(R$styleable.MaterialToolbar_titleCentered, false);
        this.d0 = typedArrayD.getBoolean(R$styleable.MaterialToolbar_subtitleCentered, false);
        int i4 = typedArrayD.getInt(R$styleable.MaterialToolbar_logoScaleType, -1);
        if (i4 >= 0) {
            ImageView.ScaleType[] scaleTypeArr = h0;
            if (i4 < scaleTypeArr.length) {
                this.e0 = scaleTypeArr[i4];
            }
        }
        int i5 = R$styleable.MaterialToolbar_logoAdjustViewBounds;
        if (typedArrayD.hasValue(i5)) {
            this.f0 = Boolean.valueOf(typedArrayD.getBoolean(i5, false));
        }
        typedArrayD.recycle();
        Drawable background = getBackground();
        if (background == null || (background instanceof ColorDrawable)) {
            sc1 sc1Var = new sc1();
            sc1Var.n(ColorStateList.valueOf(background != null ? ((ColorDrawable) background).getColor() : 0));
            sc1Var.k(context2);
            WeakHashMap<View, in3> weakHashMap = om3.a;
            sc1Var.m(om3.i.e(this));
            om3.d.n(this, sc1Var);
        }
    }
}
