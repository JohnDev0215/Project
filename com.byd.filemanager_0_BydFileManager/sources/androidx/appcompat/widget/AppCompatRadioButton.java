package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatRadioButton extends RadioButton implements androidx.core.widget.j, b.g.k.s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e f334b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final d f335c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final m f336d;

    public AppCompatRadioButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f335c;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.f336d;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        e eVar = this.f334b;
        return eVar != null ? eVar.b(compoundPaddingLeft) : compoundPaddingLeft;
    }

    @Override // b.g.k.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f335c;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // b.g.k.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f335c;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    @Override // androidx.core.widget.j
    public ColorStateList getSupportButtonTintList() {
        e eVar = this.f334b;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        e eVar = this.f334b;
        if (eVar != null) {
            return eVar.d();
        }
        return null;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f335c;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        d dVar = this.f335c;
        if (dVar != null) {
            dVar.g(i);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        e eVar = this.f334b;
        if (eVar != null) {
            eVar.f();
        }
    }

    @Override // b.g.k.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f335c;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // b.g.k.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f335c;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // androidx.core.widget.j
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        e eVar = this.f334b;
        if (eVar != null) {
            eVar.g(colorStateList);
        }
    }

    @Override // androidx.core.widget.j
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        e eVar = this.f334b;
        if (eVar != null) {
            eVar.h(mode);
        }
    }

    public AppCompatRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.a.a.H);
    }

    public AppCompatRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(b0.b(context), attributeSet, i);
        z.a(this, getContext());
        e eVar = new e(this);
        this.f334b = eVar;
        eVar.e(attributeSet, i);
        d dVar = new d(this);
        this.f335c = dVar;
        dVar.e(attributeSet, i);
        m mVar = new m(this);
        this.f336d = mVar;
        mVar.m(attributeSet, i);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(b.a.k.a.a.d(getContext(), i));
    }
}
