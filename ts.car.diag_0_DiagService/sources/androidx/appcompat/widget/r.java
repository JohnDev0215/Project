package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R$attr;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class r extends RadioButton implements androidx.core.widget.j, androidx.core.view.r {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final C0062h f382c;
    private final C0058d d;
    private final C0076w e;

    public r(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.radioButtonStyle);
    }

    @Override // androidx.core.widget.j
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void a(@Nullable PorterDuff.Mode mode) {
        C0062h c0062h = this.f382c;
        if (c0062h != null) {
            c0062h.f(mode);
        }
    }

    @Override // androidx.core.widget.j
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void b(@Nullable ColorStateList colorStateList) {
        C0062h c0062h = this.f382c;
        if (c0062h != null) {
            c0062h.e(colorStateList);
        }
    }

    @Override // androidx.core.view.r
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode c() {
        C0058d c0058d = this.d;
        if (c0058d != null) {
            return c0058d.d();
        }
        return null;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        C0058d c0058d = this.d;
        if (c0058d != null) {
            c0058d.b();
        }
        C0076w c0076w = this.e;
        if (c0076w != null) {
            c0076w.b();
        }
    }

    @Override // androidx.core.view.r
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public ColorStateList f() {
        C0058d c0058d = this.d;
        if (c0058d != null) {
            return c0058d.c();
        }
        return null;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        C0062h c0062h = this.f382c;
        return c0062h != null ? c0062h.b(compoundPaddingLeft) : compoundPaddingLeft;
    }

    @Override // androidx.core.view.r
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void h(@Nullable PorterDuff.Mode mode) {
        C0058d c0058d = this.d;
        if (c0058d != null) {
            c0058d.j(mode);
        }
    }

    @Override // androidx.core.view.r
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void i(@Nullable ColorStateList colorStateList) {
        C0058d c0058d = this.d;
        if (c0058d != null) {
            c0058d.i(colorStateList);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(@Nullable Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0058d c0058d = this.d;
        if (c0058d != null) {
            c0058d.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        C0058d c0058d = this.d;
        if (c0058d != null) {
            c0058d.g(i);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        C0062h c0062h = this.f382c;
        if (c0062h != null) {
            c0062h.d();
        }
    }

    public r(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(Q.b(context), attributeSet, i);
        O.a(this, getContext());
        C0062h c0062h = new C0062h(this);
        this.f382c = c0062h;
        c0062h.c(attributeSet, i);
        C0058d c0058d = new C0058d(this);
        this.d = c0058d;
        c0058d.e(attributeSet, i);
        C0076w c0076w = new C0076w(this);
        this.e = c0076w;
        c0076w.k(attributeSet, i);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(@DrawableRes int i) {
        setButtonDrawable(androidx.appcompat.a.a.a.b(getContext(), i));
    }
}
