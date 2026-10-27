package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.MultiAutoCompleteTextView;
import androidx.appcompat.R$attr;
import defpackage.b3;
import defpackage.d3;
import defpackage.f2;
import defpackage.g3;
import defpackage.i2;
import defpackage.o2;
import defpackage.s9;
import defpackage.u0;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatMultiAutoCompleteTextView extends MultiAutoCompleteTextView implements s9 {
    public static final int[] b = {R.attr.popupBackground};
    public final f2 c;
    public final o2 d;

    public AppCompatMultiAutoCompleteTextView(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        f2 f2Var = this.c;
        if (f2Var != null) {
            f2Var.b();
        }
        o2 o2Var = this.d;
        if (o2Var != null) {
            o2Var.b();
        }
    }

    @Override // defpackage.s9
    public ColorStateList getSupportBackgroundTintList() {
        f2 f2Var = this.c;
        if (f2Var != null) {
            return f2Var.c();
        }
        return null;
    }

    @Override // defpackage.s9
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        f2 f2Var = this.c;
        if (f2Var != null) {
            return f2Var.d();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return i2.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        f2 f2Var = this.c;
        if (f2Var != null) {
            f2Var.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        f2 f2Var = this.c;
        if (f2Var != null) {
            f2Var.g(i);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i) {
        setDropDownBackgroundDrawable(u0.d(getContext(), i));
    }

    @Override // defpackage.s9
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        f2 f2Var = this.c;
        if (f2Var != null) {
            f2Var.i(colorStateList);
        }
    }

    @Override // defpackage.s9
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        f2 f2Var = this.c;
        if (f2Var != null) {
            f2Var.j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        o2 o2Var = this.d;
        if (o2Var != null) {
            o2Var.q(context, i);
        }
    }

    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
    }

    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(d3.b(context), attributeSet, i);
        b3.a(this, getContext());
        g3 g3VarV = g3.v(getContext(), attributeSet, b, i, 0);
        if (g3VarV.s(0)) {
            setDropDownBackgroundDrawable(g3VarV.g(0));
        }
        g3VarV.w();
        f2 f2Var = new f2(this);
        this.c = f2Var;
        f2Var.e(attributeSet, i);
        o2 o2Var = new o2(this);
        this.d = o2Var;
        o2Var.m(attributeSet, i);
        o2Var.b();
    }
}
