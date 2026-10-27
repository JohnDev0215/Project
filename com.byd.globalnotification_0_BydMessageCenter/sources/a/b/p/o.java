package a.b.p;

import a.b.k.l;
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
public class o extends MultiAutoCompleteTextView {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final int[] f326c = {R.attr.popupBackground};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f327a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final y f328b;

    /* JADX WARN: Illegal instructions before constructor call */
    public o(Context context, AttributeSet attributeSet) {
        int i = a.b.a.autoCompleteTextViewStyle;
        u0.a(context);
        super(context, attributeSet, i);
        s0.a(this, getContext());
        x0 x0VarQ = x0.q(getContext(), attributeSet, f326c, i, 0);
        if (x0VarQ.o(0)) {
            setDropDownBackgroundDrawable(x0VarQ.g(0));
        }
        x0VarQ.f390b.recycle();
        e eVar = new e(this);
        this.f327a = eVar;
        eVar.d(attributeSet, i);
        y yVar = new y(this);
        this.f328b = yVar;
        yVar.d(attributeSet, i);
        this.f328b.b();
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        e eVar = this.f327a;
        if (eVar != null) {
            eVar.a();
        }
        y yVar = this.f328b;
        if (yVar != null) {
            yVar.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.f327a;
        if (eVar != null) {
            return eVar.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.f327a;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        l.i.Z(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.f327a;
        if (eVar != null) {
            eVar.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        e eVar = this.f327a;
        if (eVar != null) {
            eVar.f(i);
        }
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i) {
        setDropDownBackgroundDrawable(a.b.l.a.a.b(getContext(), i));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e eVar = this.f327a;
        if (eVar != null) {
            eVar.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e eVar = this.f327a;
        if (eVar != null) {
            eVar.i(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        y yVar = this.f328b;
        if (yVar != null) {
            yVar.e(context, i);
        }
    }
}
