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
import b.b.l.a.a;
import b.b.q.b0;
import b.b.q.d;
import b.b.q.e0;
import b.b.q.g;
import b.b.q.m;
import b.b.q.z;
import b.h.m.s;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatMultiAutoCompleteTextView extends MultiAutoCompleteTextView implements s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f415b = {R.attr.popupBackground};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d f416c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final m f417d;

    public AppCompatMultiAutoCompleteTextView(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f416c;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.f417d;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // b.h.m.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f416c;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // b.h.m.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f416c;
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
        d dVar = this.f416c;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        d dVar = this.f416c;
        if (dVar != null) {
            dVar.g(i2);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i2) {
        setDropDownBackgroundDrawable(a.d(getContext(), i2));
    }

    @Override // b.h.m.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f416c;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // b.h.m.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f416c;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        m mVar = this.f417d;
        if (mVar != null) {
            mVar.q(context, i2);
        }
    }

    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.b.a.autoCompleteTextViewStyle);
    }

    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet, int i2) {
        super(b0.b(context), attributeSet, i2);
        z.a(this, getContext());
        e0 e0VarV = e0.v(getContext(), attributeSet, f415b, i2, 0);
        if (e0VarV.s(0)) {
            setDropDownBackgroundDrawable(e0VarV.g(0));
        }
        e0VarV.w();
        d dVar = new d(this);
        this.f416c = dVar;
        dVar.e(attributeSet, i2);
        m mVar = new m(this);
        this.f417d = mVar;
        mVar.m(attributeSet, i2);
        mVar.b();
    }
}
