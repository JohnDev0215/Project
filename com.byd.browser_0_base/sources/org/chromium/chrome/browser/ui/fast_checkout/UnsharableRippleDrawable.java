package org.chromium.chrome.browser.ui.fast_checkout;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;

/* JADX INFO: loaded from: classes2.dex */
public class UnsharableRippleDrawable extends RippleDrawable {
    public static final ColorStateList e = ColorStateList.valueOf(16777215);

    public UnsharableRippleDrawable(ColorStateList colorStateList, Drawable drawable, Drawable drawable2) {
        super(colorStateList, drawable, drawable2);
    }

    @Override // android.graphics.drawable.RippleDrawable, android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return null;
    }

    public UnsharableRippleDrawable() {
        this(e, null, null);
    }
}
