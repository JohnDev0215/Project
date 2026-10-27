package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import androidx.appcompat.R$attr;
import m.a;
import o0.s;
import r.b0;
import r.d;
import r.e;
import r.m;
import r.z;
import r0.j;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatRadioButton extends RadioButton implements j, s {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e f565d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d f566e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final m f567f;

    public AppCompatRadioButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f566e;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.f567f;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        e eVar = this.f565d;
        return eVar != null ? eVar.b(compoundPaddingLeft) : compoundPaddingLeft;
    }

    @Override // o0.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f566e;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // o0.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f566e;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    @Override // r0.j
    public ColorStateList getSupportButtonTintList() {
        e eVar = this.f565d;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        e eVar = this.f565d;
        if (eVar != null) {
            return eVar.d();
        }
        return null;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f566e;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i8) {
        super.setBackgroundResource(i8);
        d dVar = this.f566e;
        if (dVar != null) {
            dVar.g(i8);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        e eVar = this.f565d;
        if (eVar != null) {
            eVar.f();
        }
    }

    @Override // o0.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f566e;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // o0.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f566e;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // r0.j
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        e eVar = this.f565d;
        if (eVar != null) {
            eVar.g(colorStateList);
        }
    }

    @Override // r0.j
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        e eVar = this.f565d;
        if (eVar != null) {
            eVar.h(mode);
        }
    }

    public AppCompatRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.radioButtonStyle);
    }

    public AppCompatRadioButton(Context context, AttributeSet attributeSet, int i8) {
        super(b0.b(context), attributeSet, i8);
        z.a(this, getContext());
        e eVar = new e(this);
        this.f565d = eVar;
        eVar.e(attributeSet, i8);
        d dVar = new d(this);
        this.f566e = dVar;
        dVar.e(attributeSet, i8);
        m mVar = new m(this);
        this.f567f = mVar;
        mVar.m(attributeSet, i8);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i8) {
        setButtonDrawable(a.d(getContext(), i8));
    }
}
