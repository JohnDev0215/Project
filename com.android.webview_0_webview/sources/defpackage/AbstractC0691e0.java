package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;

/* JADX INFO: renamed from: e0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0691e0 extends EditText {
    public final W s;
    public final C1026k0 t;

    /* JADX WARN: Illegal instructions before constructor call */
    public AbstractC0691e0(Context context, AttributeSet attributeSet) {
        int i = AbstractC0245Nm.S2;
        I0.a(context);
        super(context, attributeSet, i);
        W w = new W(this);
        this.s = w;
        w.b(attributeSet, i);
        C1026k0 c1026k0 = new C1026k0(this);
        this.t = c1026k0;
        c1026k0.e(attributeSet, i);
        this.t.b();
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        W w = this.s;
        if (w != null) {
            w.a();
        }
        C1026k0 c1026k0 = this.t;
        if (c1026k0 != null) {
            c1026k0.b();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        AbstractC0747f0.a(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        W w = this.s;
        if (w != null) {
            w.c();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        W w = this.s;
        if (w != null) {
            w.d(i);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(AbstractC1855z3.e(this, callback));
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        C1026k0 c1026k0 = this.t;
        if (c1026k0 != null) {
            c1026k0.f(context, i);
        }
    }

    @Override // android.widget.EditText, android.widget.TextView
    public Editable getText() {
        return Build.VERSION.SDK_INT >= 28 ? super.getText() : super.getEditableText();
    }
}
