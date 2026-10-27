package r;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AutoCompleteTextView;
import l8.C1789g;

/* JADX INFO: renamed from: r.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2254m extends AutoCompleteTextView {
    public static final int[] o = {R.attr.popupBackground};
    public final i2.q l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C2215C f21530m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final C1789g f21531n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractC2254m(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, com.stingray.drivemodedetector.R.attr.autoCompleteTextViewStyle);
        r0.a(context);
        q0.a(this, getContext());
        h3.y yVarF = h3.y.F(getContext(), attributeSet, o, com.stingray.drivemodedetector.R.attr.autoCompleteTextViewStyle);
        if (((TypedArray) yVarF.f15500n).hasValue(0)) {
            setDropDownBackgroundDrawable(yVarF.s(0));
        }
        yVarF.G();
        i2.q qVar = new i2.q(this);
        this.l = qVar;
        qVar.f(attributeSet, com.stingray.drivemodedetector.R.attr.autoCompleteTextViewStyle);
        C2215C c2215c = new C2215C(this);
        this.f21530m = c2215c;
        c2215c.d(attributeSet, com.stingray.drivemodedetector.R.attr.autoCompleteTextViewStyle);
        c2215c.b();
        C1789g c1789g = new C1789g(this);
        this.f21531n = c1789g;
        c1789g.C(attributeSet, com.stingray.drivemodedetector.R.attr.autoCompleteTextViewStyle);
        KeyListener keyListener = getKeyListener();
        if (keyListener instanceof NumberKeyListener) {
            return;
        }
        boolean zIsFocusable = super.isFocusable();
        boolean zIsClickable = super.isClickable();
        boolean zIsLongClickable = super.isLongClickable();
        int inputType = super.getInputType();
        KeyListener keyListenerY = c1789g.y(keyListener);
        if (keyListenerY == keyListener) {
            return;
        }
        super.setKeyListener(keyListenerY);
        super.setRawInputType(inputType);
        super.setFocusable(zIsFocusable);
        super.setClickable(zIsClickable);
        super.setLongClickable(zIsLongClickable);
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.b();
        }
        C2215C c2215c = this.f21530m;
        if (c2215c != null) {
            c2215c.b();
        }
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return super.getCustomSelectionActionModeCallback();
    }

    public ColorStateList getSupportBackgroundTintList() {
        i2.q qVar = this.l;
        if (qVar != null) {
            return qVar.d();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        i2.q qVar = this.l;
        if (qVar != null) {
            return qVar.e();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        c9.m mVar = this.f21530m.f21423h;
        if (mVar != null) {
            return (ColorStateList) mVar.c;
        }
        return null;
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        c9.m mVar = this.f21530m.f21423h;
        if (mVar != null) {
            return (PorterDuff.Mode) mVar.d;
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        n7.z.t(inputConnectionOnCreateInputConnection, editorInfo, this);
        return this.f21531n.D(inputConnectionOnCreateInputConnection, editorInfo);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.g();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.h(i7);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        C2215C c2215c = this.f21530m;
        if (c2215c != null) {
            c2215c.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        C2215C c2215c = this.f21530m;
        if (c2215c != null) {
            c2215c.b();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(callback);
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i7) {
        setDropDownBackgroundDrawable(h6.f.k(getContext(), i7));
    }

    public void setEmojiCompatEnabled(boolean z6) {
        this.f21531n.E(z6);
    }

    @Override // android.widget.TextView
    public void setKeyListener(KeyListener keyListener) {
        super.setKeyListener(this.f21531n.y(keyListener));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.j(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        i2.q qVar = this.l;
        if (qVar != null) {
            qVar.k(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        C2215C c2215c = this.f21530m;
        c2215c.f(colorStateList);
        c2215c.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        C2215C c2215c = this.f21530m;
        c2215c.g(mode);
        c2215c.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        C2215C c2215c = this.f21530m;
        if (c2215c != null) {
            c2215c.e(context, i7);
        }
    }
}
