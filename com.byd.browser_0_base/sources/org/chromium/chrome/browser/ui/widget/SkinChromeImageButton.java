package org.chromium.chrome.browser.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import defpackage.go2;
import defpackage.mo2;
import defpackage.ro2;
import org.chromium.ui.widget.ChromeImageButton;

/* JADX INFO: loaded from: classes2.dex */
public class SkinChromeImageButton extends ChromeImageButton implements ro2 {
    public final go2 h;
    public final mo2 i;

    public SkinChromeImageButton(Context context) {
        this(context, null);
    }

    @Override // defpackage.ro2
    public final void e() {
        go2 go2Var = this.h;
        if (go2Var != null) {
            go2Var.J();
        }
        mo2 mo2Var = this.i;
        if (mo2Var != null) {
            mo2Var.J();
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageButton, android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        go2 go2Var = this.h;
        if (go2Var != null) {
            go2Var.L(i);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatImageButton, android.widget.ImageView
    public void setImageResource(int i) {
        mo2 mo2Var = this.i;
        if (mo2Var != null) {
            mo2Var.l = i;
            mo2Var.m = 0;
            mo2Var.J();
        }
    }

    public SkinChromeImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SkinChromeImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        go2 go2Var = new go2(this);
        this.h = go2Var;
        go2Var.K(attributeSet, i);
        mo2 mo2Var = new mo2(this);
        this.i = mo2Var;
        mo2Var.K(attributeSet, i);
    }
}
