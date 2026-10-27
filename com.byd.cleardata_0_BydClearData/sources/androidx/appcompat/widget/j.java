package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
public class j extends EditText implements a.g.k.s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e f967b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final x f968c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final w f969d;

    public j(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.a.a.A);
    }

    public j(Context context, AttributeSet attributeSet, int i) {
        super(s0.b(context), attributeSet, i);
        q0.a(this, getContext());
        e eVar = new e(this);
        this.f967b = eVar;
        eVar.e(attributeSet, i);
        x xVar = new x(this);
        this.f968c = xVar;
        xVar.m(attributeSet, i);
        xVar.b();
        this.f969d = new w(this);
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        e eVar = this.f967b;
        if (eVar != null) {
            eVar.b();
        }
        x xVar = this.f968c;
        if (xVar != null) {
            xVar.b();
        }
    }

    @Override // a.g.k.s
    public ColorStateList getSupportBackgroundTintList() {
        e eVar = this.f967b;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    @Override // a.g.k.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        e eVar = this.f967b;
        if (eVar != null) {
            return eVar.d();
        }
        return null;
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        return Build.VERSION.SDK_INT >= 28 ? super.getText() : super.getEditableText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        w wVar;
        return (Build.VERSION.SDK_INT >= 28 || (wVar = this.f969d) == null) ? super.getTextClassifier() : wVar.a();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return k.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        e eVar = this.f967b;
        if (eVar != null) {
            eVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        e eVar = this.f967b;
        if (eVar != null) {
            eVar.g(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.i.s(this, callback));
    }

    @Override // a.g.k.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        e eVar = this.f967b;
        if (eVar != null) {
            eVar.i(colorStateList);
        }
    }

    @Override // a.g.k.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        e eVar = this.f967b;
        if (eVar != null) {
            eVar.j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        x xVar = this.f968c;
        if (xVar != null) {
            xVar.q(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        w wVar;
        if (Build.VERSION.SDK_INT >= 28 || (wVar = this.f969d) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            wVar.b(textClassifier);
        }
    }
}
