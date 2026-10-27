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

/* JADX INFO: loaded from: classes.dex */
public class o extends MultiAutoCompleteTextView implements a.g.k.s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f987b = {R.attr.popupBackground};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e f988c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final x f989d;

    public o(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.a.a.p);
    }

    public o(Context context, AttributeSet attributeSet, int i) {
        super(s0.b(context), attributeSet, i);
        q0.a(this, getContext());
        v0 v0VarV = v0.v(getContext(), attributeSet, f987b, i, 0);
        if (v0VarV.s(0)) {
            setDropDownBackgroundDrawable(v0VarV.g(0));
        }
        v0VarV.w();
        e eVar = new e(this);
        this.f988c = eVar;
        eVar.e(attributeSet, i);
        x xVar = new x(this);
        this.f989d = xVar;
        xVar.m(attributeSet, i);
        xVar.b();
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        e eVar = this.f988c;
        if (eVar != null) {
            eVar.b();
        }
        x xVar = this.f989d;
        if (xVar != null) {
            xVar.b();
        }
    }

    @Override // a.g.k.s
    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.f988c;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    @Override // a.g.k.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.f988c;
        if (eVar != null) {
            return eVar.d();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return k.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.f988c;
        if (eVar != null) {
            eVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        e eVar = this.f988c;
        if (eVar != null) {
            eVar.g(i);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i) {
        setDropDownBackgroundDrawable(a.a.k.a.a.d(getContext(), i));
    }

    @Override // a.g.k.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e eVar = this.f988c;
        if (eVar != null) {
            eVar.i(colorStateList);
        }
    }

    @Override // a.g.k.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e eVar = this.f988c;
        if (eVar != null) {
            eVar.j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        x xVar = this.f989d;
        if (xVar != null) {
            xVar.q(context, i);
        }
    }
}
