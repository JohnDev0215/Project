package a.b.p;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CheckBox;

/* JADX INFO: loaded from: classes.dex */
public class g extends CheckBox {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i f263a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final e f264b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final y f265c;

    public g(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.b.a.checkboxStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        u0.a(context);
        s0.a(this, getContext());
        i iVar = new i(this);
        this.f263a = iVar;
        iVar.b(attributeSet, i);
        e eVar = new e(this);
        this.f264b = eVar;
        eVar.d(attributeSet, i);
        y yVar = new y(this);
        this.f265c = yVar;
        yVar.d(attributeSet, i);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        e eVar = this.f264b;
        if (eVar != null) {
            eVar.a();
        }
        y yVar = this.f265c;
        if (yVar != null) {
            yVar.b();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        i iVar = this.f263a;
        return compoundPaddingLeft;
    }

    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.f264b;
        if (eVar != null) {
            return eVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.f264b;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    public ColorStateList getSupportButtonTintList() {
        i iVar = this.f263a;
        if (iVar != null) {
            return iVar.f277b;
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        i iVar = this.f263a;
        if (iVar != null) {
            return iVar.f278c;
        }
        return null;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.f264b;
        if (eVar != null) {
            eVar.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        e eVar = this.f264b;
        if (eVar != null) {
            eVar.f(i);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i) {
        setButtonDrawable(a.b.l.a.a.b(getContext(), i));
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        i iVar = this.f263a;
        if (iVar != null) {
            if (iVar.f281f) {
                iVar.f281f = false;
            } else {
                iVar.f281f = true;
                iVar.a();
            }
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e eVar = this.f264b;
        if (eVar != null) {
            eVar.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e eVar = this.f264b;
        if (eVar != null) {
            eVar.i(mode);
        }
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) {
        i iVar = this.f263a;
        if (iVar != null) {
            iVar.f277b = colorStateList;
            iVar.f279d = true;
            iVar.a();
        }
    }

    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        i iVar = this.f263a;
        if (iVar != null) {
            iVar.f278c = mode;
            iVar.f280e = true;
            iVar.a();
        }
    }
}
