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
public class AppCompatEditText extends EditText implements b.g.k.s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final d f324b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final m f325c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final l f326d;

    public AppCompatEditText(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f324b;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.f325c;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // b.g.k.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f324b;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // b.g.k.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f324b;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        l lVar;
        return (Build.VERSION.SDK_INT >= 28 || (lVar = this.f326d) == null) ? super.getTextClassifier() : lVar.a();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return g.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f324b;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        d dVar = this.f324b;
        if (dVar != null) {
            dVar.g(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(androidx.core.widget.i.s(this, callback));
    }

    @Override // b.g.k.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f324b;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // b.g.k.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f324b;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        m mVar = this.f325c;
        if (mVar != null) {
            mVar.q(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        l lVar;
        if (Build.VERSION.SDK_INT >= 28 || (lVar = this.f326d) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            lVar.b(textClassifier);
        }
    }

    public AppCompatEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.a.a.C);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        return Build.VERSION.SDK_INT >= 28 ? super.getText() : super.getEditableText();
    }

    public AppCompatEditText(Context context, AttributeSet attributeSet, int i) {
        super(b0.b(context), attributeSet, i);
        z.a(this, getContext());
        d dVar = new d(this);
        this.f324b = dVar;
        dVar.e(attributeSet, i);
        m mVar = new m(this);
        this.f325c = mVar;
        mVar.m(attributeSet, i);
        mVar.b();
        this.f326d = new l(this);
    }
}
