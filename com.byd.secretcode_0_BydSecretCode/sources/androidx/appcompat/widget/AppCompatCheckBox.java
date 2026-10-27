package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckBox;
import androidx.appcompat.R$attr;
import defpackage.b3;
import defpackage.d3;
import defpackage.f2;
import defpackage.g2;
import defpackage.o2;
import defpackage.ra;
import defpackage.s9;
import defpackage.u0;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatCheckBox extends CheckBox implements ra, s9 {
    public final g2 b;
    public final f2 c;
    public final o2 d;

    public AppCompatCheckBox(Context context) {
        this(context, null);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        f2 f2Var = this.c;
        if (f2Var != null) {
            f2Var.b();
        }
        o2 o2Var = this.d;
        if (o2Var != null) {
            o2Var.b();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        g2 g2Var = this.b;
        return g2Var != null ? g2Var.b(compoundPaddingLeft) : compoundPaddingLeft;
    }

    @Override // defpackage.s9
    public ColorStateList getSupportBackgroundTintList() {
        f2 f2Var = this.c;
        if (f2Var != null) {
            return f2Var.c();
        }
        return null;
    }

    @Override // defpackage.s9
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        f2 f2Var = this.c;
        if (f2Var != null) {
            return f2Var.d();
        }
        return null;
    }

    @Override // defpackage.ra
    public ColorStateList getSupportButtonTintList() {
        g2 g2Var = this.b;
        if (g2Var != null) {
            return g2Var.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        g2 g2Var = this.b;
        if (g2Var != null) {
            return g2Var.d();
        }
        return null;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        f2 f2Var = this.c;
        if (f2Var != null) {
            f2Var.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        f2 f2Var = this.c;
        if (f2Var != null) {
            f2Var.g(i);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        g2 g2Var = this.b;
        if (g2Var != null) {
            g2Var.f();
        }
    }

    @Override // defpackage.s9
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        f2 f2Var = this.c;
        if (f2Var != null) {
            f2Var.i(colorStateList);
        }
    }

    @Override // defpackage.s9
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        f2 f2Var = this.c;
        if (f2Var != null) {
            f2Var.j(mode);
        }
    }

    @Override // defpackage.ra
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        g2 g2Var = this.b;
        if (g2Var != null) {
            g2Var.g(colorStateList);
        }
    }

    @Override // defpackage.ra
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        g2 g2Var = this.b;
        if (g2Var != null) {
            g2Var.h(mode);
        }
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.checkboxStyle);
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(d3.b(context), attributeSet, i);
        b3.a(this, getContext());
        g2 g2Var = new g2(this);
        this.b = g2Var;
        g2Var.e(attributeSet, i);
        f2 f2Var = new f2(this);
        this.c = f2Var;
        f2Var.e(attributeSet, i);
        o2 o2Var = new o2(this);
        this.d = o2Var;
        o2Var.m(attributeSet, i);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(u0.d(getContext(), i));
    }
}
