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

/* JADX INFO: compiled from: AppCompatMultiAutoCompleteTextView.java */
/* JADX INFO: loaded from: classes.dex */
public class n extends MultiAutoCompleteTextView {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final int[] f1151h = {R.attr.popupBackground};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final d f1152e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final v f1153g;

    public n(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, e.a.autoCompleteTextViewStyle);
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f1152e;
        if (dVar != null) {
            dVar.b();
        }
        v vVar = this.f1153g;
        if (vVar != null) {
            vVar.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f1152e;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f1152e;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return j.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f1152e;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i7) {
        super.setBackgroundResource(i7);
        d dVar = this.f1152e;
        if (dVar != null) {
            dVar.g(i7);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i7) {
        setDropDownBackgroundDrawable(f.a.b(getContext(), i7));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f1152e;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f1152e;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i7) {
        super.setTextAppearance(context, i7);
        v vVar = this.f1153g;
        if (vVar != null) {
            vVar.q(context, i7);
        }
    }

    public n(Context context, AttributeSet attributeSet, int i7) {
        super(p0.b(context), attributeSet, i7);
        n0.a(this, getContext());
        s0 s0VarV = s0.v(getContext(), attributeSet, f1151h, i7, 0);
        if (s0VarV.s(0)) {
            setDropDownBackgroundDrawable(s0VarV.g(0));
        }
        s0VarV.w();
        d dVar = new d(this);
        this.f1152e = dVar;
        dVar.e(attributeSet, i7);
        v vVar = new v(this);
        this.f1153g = vVar;
        vVar.m(attributeSet, i7);
        vVar.b();
    }
}
