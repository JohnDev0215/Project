package p;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.CheckBox;

/* JADX INFO: loaded from: classes.dex */
public class x7 extends CheckBox {
    private final a8 c;
    private final v7 e;
    private final ca k;
    private y8 l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x7(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        wl2.a(context);
        ik2.a(this, getContext());
        a8 a8Var = new a8(this);
        this.c = a8Var;
        a8Var.b(attributeSet, i);
        v7 v7Var = new v7(this);
        this.e = v7Var;
        v7Var.d(attributeSet, i);
        ca caVar = new ca(this);
        this.k = caVar;
        caVar.m(attributeSet, i);
        getEmojiTextViewHelper().b(attributeSet, i);
    }

    private y8 getEmojiTextViewHelper() {
        if (this.l == null) {
            this.l = new y8(this);
        }
        return this.l;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        v7 v7Var = this.e;
        if (v7Var != null) {
            v7Var.a();
        }
        ca caVar = this.k;
        if (caVar != null) {
            caVar.b();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        a8 a8Var = this.c;
        if (a8Var != null) {
            a8Var.getClass();
        }
        return compoundPaddingLeft;
    }

    public ColorStateList getSupportBackgroundTintList() {
        v7 v7Var = this.e;
        if (v7Var != null) {
            return v7Var.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        v7 v7Var = this.e;
        if (v7Var != null) {
            return v7Var.c();
        }
        return null;
    }

    public ColorStateList getSupportButtonTintList() {
        a8 a8Var = this.c;
        if (a8Var != null) {
            return a8Var.b;
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        a8 a8Var = this.c;
        if (a8Var != null) {
            return a8Var.c;
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.k.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.k.k();
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().c(z);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        v7 v7Var = this.e;
        if (v7Var != null) {
            v7Var.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        v7 v7Var = this.e;
        if (v7Var != null) {
            v7Var.f(i);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        a8 a8Var = this.c;
        if (a8Var != null) {
            if (a8Var.f) {
                a8Var.f = false;
            } else {
                a8Var.f = true;
                a8Var.a();
            }
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        ca caVar = this.k;
        if (caVar != null) {
            caVar.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        ca caVar = this.k;
        if (caVar != null) {
            caVar.b();
        }
    }

    public void setEmojiCompatEnabled(boolean z) {
        getEmojiTextViewHelper().d(z);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().a(inputFilterArr));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        v7 v7Var = this.e;
        if (v7Var != null) {
            v7Var.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        v7 v7Var = this.e;
        if (v7Var != null) {
            v7Var.i(mode);
        }
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) {
        a8 a8Var = this.c;
        if (a8Var != null) {
            a8Var.b = colorStateList;
            a8Var.d = true;
            a8Var.a();
        }
    }

    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        a8 a8Var = this.c;
        if (a8Var != null) {
            a8Var.c = mode;
            a8Var.e = true;
            a8Var.a();
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.k.s(colorStateList);
        this.k.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.k.t(mode);
        this.k.b();
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(l13.f(getContext(), i));
    }
}
