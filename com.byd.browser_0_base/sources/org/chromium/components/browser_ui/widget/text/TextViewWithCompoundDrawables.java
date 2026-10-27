package org.chromium.components.browser_ui.widget.text;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import org.chromium.components.browser_ui.widget.R$styleable;

/* JADX INFO: loaded from: classes2.dex */
public class TextViewWithCompoundDrawables extends AppCompatTextView {
    public int k;
    public int l;
    public ColorStateList m;

    public TextViewWithCompoundDrawables(Context context) {
        this(context, null);
    }

    private void setDrawableBounds(Drawable[] drawableArr) {
        for (Drawable drawable : drawableArr) {
            if (drawable != null && (this.k > 0 || this.l > 0)) {
                Rect rectCopyBounds = drawable.copyBounds();
                int i = this.k;
                if (i > 0) {
                    rectCopyBounds.right = rectCopyBounds.left + i;
                }
                int i2 = this.l;
                if (i2 > 0) {
                    rectCopyBounds.bottom = rectCopyBounds.top + i2;
                }
                drawable.setBounds(rectCopyBounds);
            }
        }
    }

    private void setDrawableTint(Drawable[] drawableArr) {
        for (Drawable drawable : drawableArr) {
            if (drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(this.m.getColorForState(getDrawableState(), 0), PorterDuff.Mode.SRC_IN);
            }
        }
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.m != null) {
            setDrawableTint(getCompoundDrawablesRelative());
        }
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        Drawable[] drawableArr = {drawable, drawable2, drawable3, drawable4};
        setDrawableBounds(drawableArr);
        if (this.m != null) {
            setDrawableTint(drawableArr);
        }
        super.setCompoundDrawablesRelative(drawableArr[0], drawableArr[1], drawableArr[2], drawableArr[3]);
    }

    public void setDrawableHeight(int i) {
        this.l = i;
        setDrawableBounds(getCompoundDrawables());
    }

    public void setDrawableTintColor(ColorStateList colorStateList) {
        this.m = colorStateList;
        setDrawableTint(getCompoundDrawablesRelative());
    }

    public void setDrawableWidth(int i) {
        this.k = i;
        setDrawableBounds(getCompoundDrawables());
    }

    public TextViewWithCompoundDrawables(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TextViewWithCompoundDrawables(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.TextViewWithCompoundDrawables, i, 0);
        this.k = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.TextViewWithCompoundDrawables_drawableWidth, -1);
        this.l = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.TextViewWithCompoundDrawables_drawableHeight, -1);
        this.m = typedArrayObtainStyledAttributes.getColorStateList(R$styleable.TextViewWithCompoundDrawables_chromeDrawableTint);
        typedArrayObtainStyledAttributes.recycle();
        if (this.k > 0 || this.l > 0 || this.m != null) {
            Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
            setDrawableBounds(compoundDrawablesRelative);
            if (this.m != null) {
                setDrawableTint(compoundDrawablesRelative);
            }
            setCompoundDrawablesRelative(compoundDrawablesRelative[0], compoundDrawablesRelative[1], compoundDrawablesRelative[2], compoundDrawablesRelative[3]);
        }
    }
}
