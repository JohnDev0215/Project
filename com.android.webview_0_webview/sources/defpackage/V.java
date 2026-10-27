package defpackage;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AutoCompleteTextView;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class V extends AutoCompleteTextView {
    public static final int[] u = {R.attr.popupBackground};
    public final W s;
    public final C1026k0 t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public V(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        I0.a(context);
        L0 l0L = L0.l(getContext(), attributeSet, u, i, 0);
        if (l0L.k(0)) {
            setDropDownBackgroundDrawable(l0L.f(0));
        }
        l0L.b.recycle();
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

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i) {
        setDropDownBackgroundDrawable(AbstractC1631v.b(getContext(), i));
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        C1026k0 c1026k0 = this.t;
        if (c1026k0 != null) {
            c1026k0.f(context, i);
        }
    }
}
