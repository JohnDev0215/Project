package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckBox;
import androidx.appcompat.R$attr;
import j.a;
import l0.s;
import n0.j;
import o.b0;
import o.d;
import o.e;
import o.m;
import o.z;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatCheckBox extends CheckBox implements j, s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f449b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f450c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m f451d;

    public AppCompatCheckBox(Context context) {
        this(context, null);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f450c;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.f451d;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        e eVar = this.f449b;
        return eVar != null ? eVar.b(compoundPaddingLeft) : compoundPaddingLeft;
    }

    @Override // l0.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f450c;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // l0.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f450c;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    @Override // n0.j
    public ColorStateList getSupportButtonTintList() {
        e eVar = this.f449b;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        e eVar = this.f449b;
        if (eVar != null) {
            return eVar.d();
        }
        return null;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f450c;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i3) {
        super.setBackgroundResource(i3);
        d dVar = this.f450c;
        if (dVar != null) {
            dVar.g(i3);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        e eVar = this.f449b;
        if (eVar != null) {
            eVar.f();
        }
    }

    @Override // l0.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f450c;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // l0.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f450c;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // n0.j
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        e eVar = this.f449b;
        if (eVar != null) {
            eVar.g(colorStateList);
        }
    }

    @Override // n0.j
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        e eVar = this.f449b;
        if (eVar != null) {
            eVar.h(mode);
        }
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.checkboxStyle);
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeSet, int i3) {
        super(b0.b(context), attributeSet, i3);
        z.a(this, getContext());
        e eVar = new e(this);
        this.f449b = eVar;
        eVar.e(attributeSet, i3);
        d dVar = new d(this);
        this.f450c = dVar;
        dVar.e(attributeSet, i3);
        m mVar = new m(this);
        this.f451d = mVar;
        mVar.m(attributeSet, i3);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i3) {
        setButtonDrawable(a.d(getContext(), i3));
    }
}
