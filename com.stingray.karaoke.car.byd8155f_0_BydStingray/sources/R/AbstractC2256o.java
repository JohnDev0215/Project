package r;

import a.AbstractC0904a;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.CheckBox;
import com.stingray.drivemodedetector.R;
import n.AbstractC1850a;

/* JADX INFO: renamed from: r.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2256o extends CheckBox {
    public final C2257p l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final i2.q f21540m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C2215C f21541n;
    public C2261t o;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractC2256o(Context context, AttributeSet attributeSet) {
        int resourceId;
        int resourceId2;
        super(context, attributeSet, R.attr.chipStyle);
        r0.a(context);
        q0.a(this, getContext());
        this.l = new C2257p(this);
        Context context2 = getContext();
        int[] iArr = AbstractC1850a.f19359j;
        h3.y yVarF = h3.y.F(context2, attributeSet, iArr, R.attr.chipStyle);
        TypedArray typedArray = (TypedArray) yVarF.f15500n;
        Context context3 = getContext();
        int[] iArr2 = F1.H.f2303a;
        F1.E.b(this, context3, iArr, attributeSet, (TypedArray) yVarF.f15500n, R.attr.chipStyle, 0);
        try {
            if (typedArray.hasValue(1) && (resourceId2 = typedArray.getResourceId(1, 0)) != 0) {
                try {
                    setButtonDrawable(h6.f.k(getContext(), resourceId2));
                } catch (Resources.NotFoundException unused) {
                    if (typedArray.hasValue(0)) {
                        setButtonDrawable(h6.f.k(getContext(), resourceId));
                    }
                }
            } else if (typedArray.hasValue(0) && (resourceId = typedArray.getResourceId(0, 0)) != 0) {
                setButtonDrawable(h6.f.k(getContext(), resourceId));
            }
            if (typedArray.hasValue(2)) {
                setButtonTintList(yVarF.p(2));
            }
            if (typedArray.hasValue(3)) {
                setButtonTintMode(AbstractC2225M.b(typedArray.getInt(3, -1), null));
            }
            yVarF.G();
            i2.q qVar = new i2.q(this);
            this.f21540m = qVar;
            qVar.f(attributeSet, R.attr.chipStyle);
            C2215C c2215c = new C2215C(this);
            this.f21541n = c2215c;
            c2215c.d(attributeSet, R.attr.chipStyle);
            getEmojiTextViewHelper().a(attributeSet, R.attr.chipStyle);
        } catch (Throwable th) {
            yVarF.G();
            throw th;
        }
    }

    private C2261t getEmojiTextViewHelper() {
        if (this.o == null) {
            this.o = new C2261t(this);
        }
        return this.o;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        i2.q qVar = this.f21540m;
        if (qVar != null) {
            qVar.b();
        }
        C2215C c2215c = this.f21541n;
        if (c2215c != null) {
            c2215c.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        i2.q qVar = this.f21540m;
        if (qVar != null) {
            return qVar.d();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        i2.q qVar = this.f21540m;
        if (qVar != null) {
            return qVar.e();
        }
        return null;
    }

    public ColorStateList getSupportButtonTintList() {
        C2257p c2257p = this.l;
        if (c2257p != null) {
            return (ColorStateList) c2257p.f21544e;
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        C2257p c2257p = this.l;
        if (c2257p != null) {
            return (PorterDuff.Mode) c2257p.f21545f;
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        c9.m mVar = this.f21541n.f21423h;
        if (mVar != null) {
            return (ColorStateList) mVar.c;
        }
        return null;
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        c9.m mVar = this.f21541n.f21423h;
        if (mVar != null) {
            return (PorterDuff.Mode) mVar.d;
        }
        return null;
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z6) {
        super.setAllCaps(z6);
        ((AbstractC0904a) getEmojiTextViewHelper().f21559b.l).Q(z6);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        i2.q qVar = this.f21540m;
        if (qVar != null) {
            qVar.g();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        i2.q qVar = this.f21540m;
        if (qVar != null) {
            qVar.h(i7);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        C2257p c2257p = this.l;
        if (c2257p != null) {
            if (c2257p.c) {
                c2257p.c = false;
            } else {
                c2257p.c = true;
                c2257p.a();
            }
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        C2215C c2215c = this.f21541n;
        if (c2215c != null) {
            c2215c.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        C2215C c2215c = this.f21541n;
        if (c2215c != null) {
            c2215c.b();
        }
    }

    public void setEmojiCompatEnabled(boolean z6) {
        ((AbstractC0904a) getEmojiTextViewHelper().f21559b.l).R(z6);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(((AbstractC0904a) getEmojiTextViewHelper().f21559b.l).E(inputFilterArr));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        i2.q qVar = this.f21540m;
        if (qVar != null) {
            qVar.j(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        i2.q qVar = this.f21540m;
        if (qVar != null) {
            qVar.k(mode);
        }
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) {
        C2257p c2257p = this.l;
        if (c2257p != null) {
            c2257p.f21544e = colorStateList;
            c2257p.f21542a = true;
            c2257p.a();
        }
    }

    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        C2257p c2257p = this.l;
        if (c2257p != null) {
            c2257p.f21545f = mode;
            c2257p.f21543b = true;
            c2257p.a();
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        C2215C c2215c = this.f21541n;
        c2215c.f(colorStateList);
        c2215c.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        C2215C c2215c = this.f21541n;
        c2215c.g(mode);
        c2215c.b();
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i7) {
        setButtonDrawable(h6.f.k(getContext(), i7));
    }
}
