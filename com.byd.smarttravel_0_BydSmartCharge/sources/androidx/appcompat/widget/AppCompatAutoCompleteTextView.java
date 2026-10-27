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
import b.b.b.a.a;
import b.b.f.b0;
import b.b.f.d;
import b.b.f.e0;
import b.b.f.g;
import b.b.f.m;
import b.b.f.z;
import b.g.j.s;
import b.g.k.i;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatAutoCompleteTextView extends AutoCompleteTextView implements s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f171b = {R.attr.popupBackground};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f172c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m f173d;

    public AppCompatAutoCompleteTextView(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f172c;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.f173d;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // b.g.j.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f172c;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // b.g.j.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f172c;
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
        d dVar = this.f172c;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        d dVar = this.f172c;
        if (dVar != null) {
            dVar.g(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(i.s(this, callback));
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i) {
        setDropDownBackgroundDrawable(a.d(getContext(), i));
    }

    @Override // b.g.j.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f172c;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // b.g.j.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f172c;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        m mVar = this.f173d;
        if (mVar != null) {
            mVar.q(context, i);
        }
    }

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
    }

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attributeSet, int i) {
        super(b0.b(context), attributeSet, i);
        z.a(this, getContext());
        e0 e0VarV = e0.v(getContext(), attributeSet, f171b, i, 0);
        if (e0VarV.s(0)) {
            setDropDownBackgroundDrawable(e0VarV.g(0));
        }
        e0VarV.w();
        d dVar = new d(this);
        this.f172c = dVar;
        dVar.e(attributeSet, i);
        m mVar = new m(this);
        this.f173d = mVar;
        mVar.m(attributeSet, i);
        mVar.b();
    }
}
