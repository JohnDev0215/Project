package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.R$attr;
import m.a;
import o0.s;
import r.b0;
import r.d;
import r.e0;
import r.g;
import r.m;
import r.z;
import r0.i;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatAutoCompleteTextView extends AutoCompleteTextView implements s {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final int[] f545d = {R.attr.popupBackground};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d f546e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final m f547f;

    public AppCompatAutoCompleteTextView(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f546e;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.f547f;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // o0.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f546e;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // o0.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f546e;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return g.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f546e;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i8) {
        super.setBackgroundResource(i8);
        d dVar = this.f546e;
        if (dVar != null) {
            dVar.g(i8);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(i.s(this, callback));
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i8) {
        setDropDownBackgroundDrawable(a.d(getContext(), i8));
    }

    @Override // o0.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f546e;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // o0.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f546e;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i8) {
        super.setTextAppearance(context, i8);
        m mVar = this.f547f;
        if (mVar != null) {
            mVar.q(context, i8);
        }
    }

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
    }

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attributeSet, int i8) {
        super(b0.b(context), attributeSet, i8);
        z.a(this, getContext());
        e0 e0VarV = e0.v(getContext(), attributeSet, f545d, i8, 0);
        if (e0VarV.s(0)) {
            setDropDownBackgroundDrawable(e0VarV.g(0));
        }
        e0VarV.w();
        d dVar = new d(this);
        this.f546e = dVar;
        dVar.e(attributeSet, i8);
        m mVar = new m(this);
        this.f547f = mVar;
        mVar.m(attributeSet, i8);
        mVar.b();
    }
}
