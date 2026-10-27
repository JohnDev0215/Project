package org.chromium.chrome.browser.ui.widget;

import android.R;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import defpackage.cc2;
import defpackage.ho2;
import defpackage.po2;
import defpackage.vd1;
import org.chromium.ui.R$style;
import skin.support.widget.SkinCompatButton;

/* JADX INFO: loaded from: classes2.dex */
public class SKinCustomButton extends SkinCompatButton {
    public final cc2 j;
    public final ho2 k;

    public SKinCustomButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$style.FilledButtonThemeOverlay);
    }

    private void setRaised(boolean z) {
        if (!z) {
            setElevation(0.0f);
            setStateListAnimator(null);
        } else {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, new int[]{R.attr.stateListAnimator}, 0, R.style.Widget.Material.Button);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
            typedArrayObtainStyledAttributes.recycle();
            setStateListAnimator(resourceId != 0 ? AnimatorInflater.loadStateListAnimator(getContext(), resourceId) : null);
        }
    }

    @Override // skin.support.widget.SkinCompatButton, defpackage.ro2
    public final void e() {
        ColorStateList colorStateListB;
        ColorStateList colorStateListB2;
        cc2 cc2Var;
        super.e();
        ho2 ho2Var = this.k;
        if (ho2Var != null) {
            int i = ho2Var.l;
            ho2Var.l = vd1.b(i);
            SkinCompatButton skinCompatButton = ho2Var.k;
            if (i > 0 && (colorStateListB2 = po2.b(skinCompatButton.getContext(), i)) != null && (cc2Var = ho2Var.n) != null && colorStateListB2 != cc2Var.a) {
                cc2Var.a = colorStateListB2;
                cc2Var.b.setColor(colorStateListB2);
            }
            int i2 = ho2Var.m;
            ho2Var.m = vd1.b(i2);
            if (i2 <= 0 || (colorStateListB = po2.b(skinCompatButton.getContext(), i2)) == null) {
                return;
            }
            skinCompatButton.setTextColor(colorStateListB);
        }
    }

    public void setButtonColor(int i) {
        ColorStateList colorStateListB;
        cc2 cc2Var;
        ho2 ho2Var = this.k;
        if (ho2Var != null) {
            ho2Var.l = vd1.b(i);
            if (i <= 0 || (colorStateListB = po2.b(ho2Var.k.getContext(), i)) == null || (cc2Var = ho2Var.n) == null || colorStateListB == cc2Var.a) {
                return;
            }
            cc2Var.a = colorStateListB;
            cc2Var.b.setColor(colorStateListB);
        }
    }

    public void setButtonTextColor(int i) {
        ho2 ho2Var = this.k;
        if (ho2Var != null) {
            ho2Var.m = vd1.b(i);
            if (i > 0) {
                SkinCompatButton skinCompatButton = ho2Var.k;
                ColorStateList colorStateListB = po2.b(skinCompatButton.getContext(), i);
                if (colorStateListB != null) {
                    skinCompatButton.setTextColor(colorStateListB);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x010e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public SKinCustomButton(android.content.Context r22, android.util.AttributeSet r23, int r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 482
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.ui.widget.SKinCustomButton.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setButtonColor(ColorStateList colorStateList) {
        cc2 cc2Var = this.j;
        if (colorStateList == cc2Var.a) {
            return;
        }
        cc2Var.a = colorStateList;
        cc2Var.b.setColor(colorStateList);
    }
}
