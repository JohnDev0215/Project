package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

/* JADX INFO: loaded from: classes.dex */
public class r extends RadioButton implements androidx.core.widget.j, a.g.k.s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final h f1013b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e f1014c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final x f1015d;

    public r(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.a.a.F);
    }

    public r(Context context, AttributeSet attributeSet, int i) {
        super(s0.b(context), attributeSet, i);
        q0.a(this, getContext());
        h hVar = new h(this);
        this.f1013b = hVar;
        hVar.e(attributeSet, i);
        e eVar = new e(this);
        this.f1014c = eVar;
        eVar.e(attributeSet, i);
        x xVar = new x(this);
        this.f1015d = xVar;
        xVar.m(attributeSet, i);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        e eVar = this.f1014c;
        if (eVar != null) {
            eVar.b();
        }
        x xVar = this.f1015d;
        if (xVar != null) {
            xVar.b();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        h hVar = this.f1013b;
        return hVar != null ? hVar.b(compoundPaddingLeft) : compoundPaddingLeft;
    }

    @Override // a.g.k.s
    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.f1014c;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    @Override // a.g.k.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.f1014c;
        if (eVar != null) {
            return eVar.d();
        }
        return null;
    }

    @Override // androidx.core.widget.j
    public ColorStateList getSupportButtonTintList() {
        h hVar = this.f1013b;
        if (hVar != null) {
            return hVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        h hVar = this.f1013b;
        if (hVar != null) {
            return hVar.d();
        }
        return null;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.f1014c;
        if (eVar != null) {
            eVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        e eVar = this.f1014c;
        if (eVar != null) {
            eVar.g(i);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(a.a.k.a.a.d(getContext(), i));
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        h hVar = this.f1013b;
        if (hVar != null) {
            hVar.f();
        }
    }

    @Override // a.g.k.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e eVar = this.f1014c;
        if (eVar != null) {
            eVar.i(colorStateList);
        }
    }

    @Override // a.g.k.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e eVar = this.f1014c;
        if (eVar != null) {
            eVar.j(mode);
        }
    }

    @Override // androidx.core.widget.j
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        h hVar = this.f1013b;
        if (hVar != null) {
            hVar.g(colorStateList);
        }
    }

    @Override // androidx.core.widget.j
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        h hVar = this.f1013b;
        if (hVar != null) {
            hVar.h(mode);
        }
    }
}
