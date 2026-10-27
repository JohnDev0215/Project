package org.chromium.components.browser_ui.widget.text;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import defpackage.AbstractC0678dn;
import defpackage.C1082l0;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class TextViewWithCompoundDrawables extends C1082l0 {
    public int v;
    public int w;
    public ColorStateList x;

    public TextViewWithCompoundDrawables(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0678dn.E0, 0, 0);
        this.v = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, -1);
        this.w = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, -1);
        this.x = typedArrayObtainStyledAttributes.getColorStateList(0);
        typedArrayObtainStyledAttributes.recycle();
        if (this.v > 0 || this.w > 0 || this.x != null) {
            Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
            for (Drawable drawable : compoundDrawablesRelative) {
                if (drawable != null && (this.v > 0 || this.w > 0)) {
                    Rect bounds = drawable.getBounds();
                    int i = this.v;
                    if (i > 0) {
                        bounds.right = bounds.left + i;
                    }
                    int i2 = this.w;
                    if (i2 > 0) {
                        bounds.bottom = bounds.top + i2;
                    }
                    drawable.setBounds(bounds);
                }
            }
            if (this.x != null) {
                b(compoundDrawablesRelative);
            }
            setCompoundDrawablesRelative(compoundDrawablesRelative[0], compoundDrawablesRelative[1], compoundDrawablesRelative[2], compoundDrawablesRelative[3]);
        }
    }

    public final void b(Drawable[] drawableArr) {
        for (Drawable drawable : drawableArr) {
            if (drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(this.x.getColorForState(getDrawableState(), 0), PorterDuff.Mode.SRC_IN);
            }
        }
    }

    @Override // defpackage.C1082l0, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.x != null) {
            b(getCompoundDrawablesRelative());
        }
    }
}
