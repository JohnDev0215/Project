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
import androidx.appcompat.R$attr;
import l0.s;
import n0.i;
import o.b0;
import o.d;
import o.g;
import o.l;
import o.m;
import o.z;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatEditText extends EditText implements s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f454b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final m f455c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final l f456d;

    public AppCompatEditText(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        d dVar = this.f454b;
        if (dVar != null) {
            dVar.b();
        }
        m mVar = this.f455c;
        if (mVar != null) {
            mVar.b();
        }
    }

    @Override // l0.s
    public ColorStateList getSupportBackgroundTintList() {
        d dVar = this.f454b;
        if (dVar != null) {
            return dVar.c();
        }
        return null;
    }

    @Override // l0.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        d dVar = this.f454b;
        if (dVar != null) {
            return dVar.d();
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        l lVar;
        return (Build.VERSION.SDK_INT >= 28 || (lVar = this.f456d) == null) ? super.getTextClassifier() : lVar.a();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return g.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        d dVar = this.f454b;
        if (dVar != null) {
            dVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i3) {
        super.setBackgroundResource(i3);
        d dVar = this.f454b;
        if (dVar != null) {
            dVar.g(i3);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(i.s(this, callback));
    }

    @Override // l0.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        d dVar = this.f454b;
        if (dVar != null) {
            dVar.i(colorStateList);
        }
    }

    @Override // l0.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        d dVar = this.f454b;
        if (dVar != null) {
            dVar.j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i3) {
        super.setTextAppearance(context, i3);
        m mVar = this.f455c;
        if (mVar != null) {
            mVar.q(context, i3);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        l lVar;
        if (Build.VERSION.SDK_INT >= 28 || (lVar = this.f456d) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            lVar.b(textClassifier);
        }
    }

    public AppCompatEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.editTextStyle);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        return Build.VERSION.SDK_INT >= 28 ? super.getText() : super.getEditableText();
    }

    public AppCompatEditText(Context context, AttributeSet attributeSet, int i3) {
        super(b0.b(context), attributeSet, i3);
        z.a(this, getContext());
        d dVar = new d(this);
        this.f454b = dVar;
        dVar.e(attributeSet, i3);
        m mVar = new m(this);
        this.f455c = mVar;
        mVar.m(attributeSet, i3);
        mVar.b();
        this.f456d = new l(this);
    }
}
