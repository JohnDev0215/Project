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
import b.f.m.r;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatMultiAutoCompleteTextView extends MultiAutoCompleteTextView implements r {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f200c = {R.attr.popupBackground};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f201d;
    public final m s;

    public AppCompatMultiAutoCompleteTextView(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f201d;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.s;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // b.f.m.r
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f201d;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // b.f.m.r
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f201d;
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
        d dVar = this.f201d;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i2) {
        super.setBackgroundResource(i2);
        d dVar = this.f201d;
        if (dVar != null) {
            dVar.g(i2);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i2) {
        setDropDownBackgroundDrawable(a.d(getContext(), i2));
    }

    @Override // b.f.m.r
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f201d;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // b.f.m.r
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f201d;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i2) {
        super.setTextAppearance(context, i2);
        m mVar = this.s;
        if (mVar != null) {
            mVar.q(context, i2);
        }
    }

    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.b.a.q);
    }

    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeSet, int i2) {
        super(b0.b(context), attributeSet, i2);
        z.a(this, getContext());
        e0 e0VarU = e0.u(getContext(), attributeSet, f200c, i2, 0);
        if (e0VarU.r(0)) {
            setDropDownBackgroundDrawable(e0VarU.f(0));
        }
        e0VarU.v();
        d dVar = new d(this);
        this.f201d = dVar;
        dVar.e(attributeSet, i2);
        m mVar = new m(this);
        this.s = mVar;
        mVar.m(attributeSet, i2);
        mVar.b();
    }
}
