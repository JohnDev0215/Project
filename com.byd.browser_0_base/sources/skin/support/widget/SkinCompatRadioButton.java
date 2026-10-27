package skin.support.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.R;
import androidx.appcompat.widget.AppCompatRadioButton;
import defpackage.go2;
import defpackage.io2;
import defpackage.ro2;
import defpackage.to2;

/* JADX INFO: loaded from: classes3.dex */
public class SkinCompatRadioButton extends AppCompatRadioButton implements ro2 {
    public final to2 i;
    public final io2 j;
    public final go2 k;

    public SkinCompatRadioButton(Context context) {
        this(context, null);
    }

    @Override // defpackage.ro2
    public final void e() {
        go2 go2Var = this.k;
        if (go2Var != null) {
            go2Var.J();
        }
        io2 io2Var = this.j;
        if (io2Var != null) {
            io2Var.J();
        }
        to2 to2Var = this.i;
        if (to2Var != null) {
            to2Var.L();
        }
    }

    @Override // androidx.appcompat.widget.AppCompatRadioButton, android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        go2 go2Var = this.k;
        if (go2Var != null) {
            go2Var.L(i);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatRadioButton, android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        super.setButtonDrawable(i);
        io2 io2Var = this.j;
        if (io2Var != null) {
            io2Var.l = i;
            io2Var.J();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(i, i2, i3, i4);
        to2 to2Var = this.i;
        if (to2Var != null) {
            to2Var.P(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        super.setCompoundDrawablesWithIntrinsicBounds(i, i2, i3, i4);
        to2 to2Var = this.i;
        if (to2Var != null) {
            to2Var.N(i, i2, i3, i4);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i) {
        setTextAppearance(getContext(), i);
    }

    public SkinCompatRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.radioButtonStyle);
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        to2 to2Var = this.i;
        if (to2Var != null) {
            to2Var.O(context, i);
        }
    }

    public SkinCompatRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        io2 io2Var = new io2(this);
        this.j = io2Var;
        io2Var.K(attributeSet, i);
        to2 to2Var = new to2(this);
        this.i = to2Var;
        to2Var.M(attributeSet, i);
        go2 go2Var = new go2(this);
        this.k = go2Var;
        go2Var.K(attributeSet, i);
    }
}
