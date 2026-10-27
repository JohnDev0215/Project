package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckBox;
import b.b.a;
import b.b.q.b0;
import b.b.q.d;
import b.b.q.e;
import b.b.q.m;
import b.b.q.z;
import b.h.m.s;
import b.h.n.j;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatCheckBox extends CheckBox implements j, s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f403b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f404c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m f405d;

    public AppCompatCheckBox(Context context) {
        this(context, null);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f404c;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.f405d;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        e eVar = this.f403b;
        return eVar != null ? eVar.b(compoundPaddingLeft) : compoundPaddingLeft;
    }

    @Override // b.h.m.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f404c;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // b.h.m.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f404c;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    @Override // b.h.n.j
    public ColorStateList getSupportButtonTintList() {
        e eVar = this.f403b;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        e eVar = this.f403b;
        if (eVar != null) {
            return eVar.d();
        }
        return null;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f404c;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        d dVar = this.f404c;
        if (dVar != null) {
            dVar.g(i2);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        e eVar = this.f403b;
        if (eVar != null) {
            eVar.f();
        }
    }

    @Override // b.h.m.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f404c;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // b.h.m.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f404c;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // b.h.n.j
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        e eVar = this.f403b;
        if (eVar != null) {
            eVar.g(colorStateList);
        }
    }

    @Override // b.h.n.j
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        e eVar = this.f403b;
        if (eVar != null) {
            eVar.h(mode);
        }
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.checkboxStyle);
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeSet, int i2) {
        super(b0.b(context), attributeSet, i2);
        z.a(this, getContext());
        e eVar = new e(this);
        this.f403b = eVar;
        eVar.e(attributeSet, i2);
        d dVar = new d(this);
        this.f404c = dVar;
        dVar.e(attributeSet, i2);
        m mVar = new m(this);
        this.f405d = mVar;
        mVar.m(attributeSet, i2);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i2) {
        setButtonDrawable(b.b.l.a.a.d(getContext(), i2));
    }
}
