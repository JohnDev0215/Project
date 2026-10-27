package skin.support.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.R;
import androidx.appcompat.widget.AppCompatButton;
import defpackage.go2;
import defpackage.ro2;
import defpackage.to2;

/* JADX INFO: loaded from: classes3.dex */
public class SkinCompatButton extends AppCompatButton implements ro2 {
    public final to2 h;
    public final go2 i;

    public SkinCompatButton(Context context) {
        this(context, null);
    }

    public void e() {
        go2 go2Var = this.i;
        if (go2Var != null) {
            go2Var.J();
        }
        to2 to2Var = this.h;
        if (to2Var != null) {
            to2Var.L();
        }
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        go2 go2Var = this.i;
        if (go2Var != null) {
            go2Var.L(i);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
        to2 to2Var = this.h;
        if (to2Var != null) {
            to2Var.P(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        super.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
        to2 to2Var = this.h;
        if (to2Var != null) {
            to2Var.N(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i) {
        setTextAppearance(getContext(), i);
    }

    public SkinCompatButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.buttonStyle);
    }

    @Override // androidx.appcompat.widget.AppCompatButton, android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        to2 to2Var = this.h;
        if (to2Var != null) {
            to2Var.O(context, i);
        }
    }

    public SkinCompatButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        go2 go2Var = new go2(this);
        this.i = go2Var;
        go2Var.K(attributeSet, i);
        to2 to2Var = new to2(this);
        this.h = to2Var;
        to2Var.M(attributeSet, i);
    }
}
