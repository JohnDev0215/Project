package org.chromium.components.browser_ui.settings;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.preference.Preference;
import defpackage.mb1;
import defpackage.n3;
import defpackage.n32;
import defpackage.nb1;

/* JADX INFO: loaded from: classes2.dex */
public class ChromeBasePreference extends Preference {
    public final ColorStateList S;
    public mb1 T;
    public final boolean U;
    public Boolean V;
    public Boolean W;
    public final String X;

    public ChromeBasePreference(Context context) {
        this(context, null);
    }

    public final void Y(boolean z) {
        this.W = Boolean.valueOf(z);
    }

    @Override // androidx.preference.Preference
    public void y(n32 n32Var) {
        ColorStateList colorStateList;
        int i;
        super.y(n32Var);
        if (this.o == null && (i = this.n) != 0) {
            this.o = AppCompatResources.a(this.e, i);
        }
        Drawable drawable = this.o;
        if (drawable != null && (colorStateList = this.S) != null) {
            drawable.setColorFilter(colorStateList.getDefaultColor(), PorterDuff.Mode.SRC_IN);
        }
        nb1.d(this.T, this, n32Var.e);
        Boolean bool = this.V;
        if (bool != null) {
            n32Var.B = bool.booleanValue();
        }
        Boolean bool2 = this.W;
        if (bool2 != null) {
            n32Var.C = bool2.booleanValue();
        }
    }

    @Override // androidx.preference.Preference
    public final void z() {
        String str;
        if (nb1.e(this.T, this) || (str = this.X) == null) {
            return;
        }
        n3.X0(str);
    }

    public ChromeBasePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        P();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ChromeBasePreference);
        this.S = typedArrayObtainStyledAttributes.getColorStateList(R$styleable.ChromeBasePreference_iconTint);
        this.X = typedArrayObtainStyledAttributes.getString(R$styleable.ChromeBasePreference_userAction);
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R$styleable.Preference);
        this.U = (typedArrayObtainStyledAttributes2.getResourceId(R$styleable.Preference_android_layout, 0) == 0 && typedArrayObtainStyledAttributes2.getResourceId(R$styleable.Preference_layout, 0) == 0) ? false : true;
    }
}
