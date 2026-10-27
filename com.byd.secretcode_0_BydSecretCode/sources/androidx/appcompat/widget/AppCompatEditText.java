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
import defpackage.b3;
import defpackage.d3;
import defpackage.f2;
import defpackage.i2;
import defpackage.n2;
import defpackage.o2;
import defpackage.qa;
import defpackage.s9;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatEditText extends EditText implements s9 {
    public final f2 b;
    public final o2 c;
    public final n2 d;

    public AppCompatEditText(Context context) {
        this(context, null);
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.b();
        }
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.b();
        }
    }

    @Override // defpackage.s9
    public ColorStateList getSupportBackgroundTintList() {
        f2 f2Var = this.b;
        if (f2Var != null) {
            return f2Var.c();
        }
        return null;
    }

    @Override // defpackage.s9
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        f2 f2Var = this.b;
        if (f2Var != null) {
            return f2Var.d();
        }
        return null;
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        n2 n2Var;
        return (Build.VERSION.SDK_INT >= 28 || (n2Var = this.d) == null) ? super.getTextClassifier() : n2Var.a();
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return i2.a(super.onCreateInputConnection(editorInfo), editorInfo, this);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.g(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(qa.s(this, callback));
    }

    @Override // defpackage.s9
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.i(colorStateList);
        }
    }

    @Override // defpackage.s9
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        f2 f2Var = this.b;
        if (f2Var != null) {
            f2Var.j(mode);
        }
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        o2 o2Var = this.c;
        if (o2Var != null) {
            o2Var.q(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        n2 n2Var;
        if (Build.VERSION.SDK_INT >= 28 || (n2Var = this.d) == null) {
            super.setTextClassifier(textClassifier);
        } else {
            n2Var.b(textClassifier);
        }
    }

    public AppCompatEditText(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.editTextStyle);
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        return Build.VERSION.SDK_INT >= 28 ? super.getText() : super.getEditableText();
    }

    public AppCompatEditText(Context context, AttributeSet attributeSet, int i) {
        super(d3.b(context), attributeSet, i);
        b3.a(this, getContext());
        f2 f2Var = new f2(this);
        this.b = f2Var;
        f2Var.e(attributeSet, i);
        o2 o2Var = new o2(this);
        this.c = o2Var;
        o2Var.m(attributeSet, i);
        o2Var.b();
        this.d = new n2(this);
    }
}
