package p;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.ToggleButton;

/* JADX INFO: loaded from: classes.dex */
public final class na extends ToggleButton {
    private final v7 c;
    private final ca e;
    private y8 k;

    public na(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.buttonStyleToggle);
        ik2.a(this, getContext());
        v7 v7Var = new v7(this);
        this.c = v7Var;
        v7Var.d(attributeSet, R.attr.buttonStyleToggle);
        ca caVar = new ca(this);
        this.e = caVar;
        caVar.m(attributeSet, R.attr.buttonStyleToggle);
        getEmojiTextViewHelper().b(attributeSet, R.attr.buttonStyleToggle);
    }

    private y8 getEmojiTextViewHelper() {
        if (this.k == null) {
            this.k = new y8(this);
        }
        return this.k;
    }

    @Override // android.widget.ToggleButton, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.a();
        }
        ca caVar = this.e;
        if (caVar != null) {
            caVar.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        v7 v7Var = this.c;
        if (v7Var != null) {
            return v7Var.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        v7 v7Var = this.c;
        if (v7Var != null) {
            return v7Var.c();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.e.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.e.k();
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().c(z);
    }

    @Override // android.widget.ToggleButton, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.f(i);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        ca caVar = this.e;
        if (caVar != null) {
            caVar.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        ca caVar = this.e;
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
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        v7 v7Var = this.c;
        if (v7Var != null) {
            v7Var.i(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.e.s(colorStateList);
        this.e.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.e.t(mode);
        this.e.b();
    }
}
