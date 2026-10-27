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
import j.a;
import l0.s;
import n0.i;
import o.b0;
import o.d;
import o.e0;
import o.g;
import o.m;
import o.z;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatAutoCompleteTextView extends AutoCompleteTextView implements s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f444b = {R.attr.popupBackground};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f445c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m f446d;

    public AppCompatAutoCompleteTextView(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f445c;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.f446d;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // l0.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f445c;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // l0.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f445c;
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
        d dVar = this.f445c;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i3) {
        super.setBackgroundResource(i3);
        d dVar = this.f445c;
        if (dVar != null) {
            dVar.g(i3);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(i.s(this, callback));
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i3) {
        setDropDownBackgroundDrawable(a.d(getContext(), i3));
    }

    @Override // l0.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f445c;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // l0.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f445c;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i3) {
        super.setTextAppearance(context, i3);
        m mVar = this.f446d;
        if (mVar != null) {
            mVar.q(context, i3);
        }
    }

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.autoCompleteTextViewStyle);
    }

    public AppCompatAutoCompleteTextView(Context context, AttributeSet attributeSet, int i3) {
        super(b0.b(context), attributeSet, i3);
        z.a(this, getContext());
        e0 e0VarV = e0.v(getContext(), attributeSet, f444b, i3, 0);
        if (e0VarV.s(0)) {
            setDropDownBackgroundDrawable(e0VarV.g(0));
        }
        e0VarV.w();
        d dVar = new d(this);
        this.f445c = dVar;
        dVar.e(attributeSet, i3);
        m mVar = new m(this);
        this.f446d = mVar;
        mVar.m(attributeSet, i3);
        mVar.b();
    }
}
