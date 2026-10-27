package p;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import com.spotify.music.R;

/* JADX INFO: loaded from: classes.dex */
public final class y7 extends CheckedTextView {
    private final z7 c;
    private final v7 e;
    private final ca k;
    private y8 l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y7(Context context, AttributeSet attributeSet) {
        int iY;
        int iY2;
        super(context, attributeSet, R.attr.checkedTextViewStyle);
        wl2.a(context);
        ik2.a(this, getContext());
        ca caVar = new ca(this);
        this.k = caVar;
        caVar.m(attributeSet, R.attr.checkedTextViewStyle);
        caVar.b();
        v7 v7Var = new v7(this);
        this.e = v7Var;
        v7Var.d(attributeSet, R.attr.checkedTextViewStyle);
        this.c = new z7(this);
        Context context2 = getContext();
        int[] iArr = yz1.l;
        v4 v4VarJ = v4.J(context2, attributeSet, iArr, R.attr.checkedTextViewStyle);
        zt2.j(this, getContext(), iArr, attributeSet, (TypedArray) v4VarJ.k, R.attr.checkedTextViewStyle);
        try {
            if (v4VarJ.G(1) && (iY2 = v4VarJ.y(1, 0)) != 0) {
                try {
                    setCheckMarkDrawable(l13.f(getContext(), iY2));
                } catch (Resources.NotFoundException unused) {
                    if (v4VarJ.G(0)) {
                        setCheckMarkDrawable(l13.f(getContext(), iY));
                    }
                }
            } else if (v4VarJ.G(0) && (iY = v4VarJ.y(0, 0)) != 0) {
                setCheckMarkDrawable(l13.f(getContext(), iY));
            }
            if (v4VarJ.G(2)) {
                setCheckMarkTintList(v4VarJ.q(2));
            }
            if (v4VarJ.G(3)) {
                setCheckMarkTintMode(cb0.b(v4VarJ.w(3, -1), null));
            }
            v4VarJ.L();
            getEmojiTextViewHelper().b(attributeSet, R.attr.checkedTextViewStyle);
        } catch (Throwable th) {
            v4VarJ.L();
            throw th;
        }
    }

    private y8 getEmojiTextViewHelper() {
        if (this.l == null) {
            this.l = new y8(this);
        }
        return this.l;
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        ca caVar = this.k;
        if (caVar != null) {
            caVar.b();
        }
        v7 v7Var = this.e;
        if (v7Var != null) {
            v7Var.a();
        }
        z7 z7Var = this.c;
        if (z7Var != null) {
            z7Var.a();
        }
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        ActionMode.Callback customSelectionActionModeCallback = super.getCustomSelectionActionModeCallback();
        return customSelectionActionModeCallback instanceof fk2 ? ((fk2) customSelectionActionModeCallback).a : customSelectionActionModeCallback;
    }

    public ColorStateList getSupportBackgroundTintList() {
        v7 v7Var = this.e;
        if (v7Var != null) {
            return v7Var.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        v7 v7Var = this.e;
        if (v7Var != null) {
            return v7Var.c();
        }
        return null;
    }

    public ColorStateList getSupportCheckMarkTintList() {
        z7 z7Var = this.c;
        if (z7Var != null) {
            return z7Var.b;
        }
        return null;
    }

    public PorterDuff.Mode getSupportCheckMarkTintMode() {
        z7 z7Var = this.c;
        if (z7Var != null) {
            return z7Var.c;
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.k.j();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.k.k();
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        ln1.t(this, editorInfo, inputConnectionOnCreateInputConnection);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().c(z);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        v7 v7Var = this.e;
        if (v7Var != null) {
            v7Var.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        v7 v7Var = this.e;
        if (v7Var != null) {
            v7Var.f(i);
        }
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(Drawable drawable) {
        super.setCheckMarkDrawable(drawable);
        z7 z7Var = this.c;
        if (z7Var != null) {
            if (z7Var.f) {
                z7Var.f = false;
            } else {
                z7Var.f = true;
                z7Var.a();
            }
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        ca caVar = this.k;
        if (caVar != null) {
            caVar.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        ca caVar = this.k;
        if (caVar != null) {
            caVar.b();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(f60.T(callback, this));
    }

    public void setEmojiCompatEnabled(boolean z) {
        getEmojiTextViewHelper().d(z);
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        v7 v7Var = this.e;
        if (v7Var != null) {
            v7Var.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        v7 v7Var = this.e;
        if (v7Var != null) {
            v7Var.i(mode);
        }
    }

    public void setSupportCheckMarkTintList(ColorStateList colorStateList) {
        z7 z7Var = this.c;
        if (z7Var != null) {
            z7Var.b = colorStateList;
            z7Var.d = true;
            z7Var.a();
        }
    }

    public void setSupportCheckMarkTintMode(PorterDuff.Mode mode) {
        z7 z7Var = this.c;
        if (z7Var != null) {
            z7Var.c = mode;
            z7Var.e = true;
            z7Var.a();
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.k.s(colorStateList);
        this.k.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.k.t(mode);
        this.k.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        ca caVar = this.k;
        if (caVar != null) {
            caVar.n(context, i);
        }
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(l13.f(getContext(), i));
    }
}
