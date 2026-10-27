package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckBox;
import androidx.appcompat.R$attr;
import b.b.b.a.a;
import b.b.f.b0;
import b.b.f.d;
import b.b.f.e;
import b.b.f.m;
import b.b.f.z;
import b.g.j.s;
import b.g.k.j;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatCheckBox extends CheckBox implements j, s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f176b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f177c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m f178d;

    public AppCompatCheckBox(Context context) {
        this(context, null);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f177c;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.f178d;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        e eVar = this.f176b;
        return eVar != null ? eVar.b(compoundPaddingLeft) : compoundPaddingLeft;
    }

    @Override // b.g.j.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f177c;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // b.g.j.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f177c;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    @Override // b.g.k.j
    public ColorStateList getSupportButtonTintList() {
        e eVar = this.f176b;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        e eVar = this.f176b;
        if (eVar != null) {
            return eVar.d();
        }
        return null;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f177c;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        d dVar = this.f177c;
        if (dVar != null) {
            dVar.g(i);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        e eVar = this.f176b;
        if (eVar != null) {
            eVar.f();
        }
    }

    @Override // b.g.j.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f177c;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // b.g.j.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f177c;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // b.g.k.j
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        e eVar = this.f176b;
        if (eVar != null) {
            eVar.g(colorStateList);
        }
    }

    @Override // b.g.k.j
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        e eVar = this.f176b;
        if (eVar != null) {
            eVar.h(mode);
        }
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.checkboxStyle);
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(b0.b(context), attributeSet, i);
        z.a(this, getContext());
        e eVar = new e(this);
        this.f176b = eVar;
        eVar.e(attributeSet, i);
        d dVar = new d(this);
        this.f177c = dVar;
        dVar.e(attributeSet, i);
        m mVar = new m(this);
        this.f178d = mVar;
        mVar.m(attributeSet, i);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(a.d(getContext(), i));
    }
}
