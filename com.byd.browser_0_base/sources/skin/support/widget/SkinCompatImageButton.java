package skin.support.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.R;
import androidx.appcompat.widget.AppCompatImageButton;
import defpackage.go2;
import defpackage.mo2;
import defpackage.ro2;

/* JADX INFO: loaded from: classes3.dex */
public class SkinCompatImageButton extends AppCompatImageButton implements ro2 {
    public final go2 h;
    public final mo2 i;

    public SkinCompatImageButton(Context context) {
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

    public SkinCompatImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.imageButtonStyle);
    }

    public SkinCompatImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        go2 go2Var = new go2(this);
        this.h = go2Var;
        go2Var.K(attributeSet, i);
        mo2 mo2Var = new mo2(this);
        this.i = mo2Var;
        mo2Var.K(attributeSet, i);
    }
}
