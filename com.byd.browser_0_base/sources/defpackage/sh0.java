package defpackage;

import android.content.Context;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textfield.a;

/* JADX INFO: compiled from: EndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class sh0 {
    public final TextInputLayout a;
    public final a b;
    public final Context c;
    public final CheckableImageButton d;

    public sh0(a aVar) {
        this.a = aVar.e;
        this.b = aVar;
        this.c = aVar.getContext();
        this.d = aVar.k;
    }

    public int c() {
        return 0;
    }

    public int d() {
        return 0;
    }

    public View.OnFocusChangeListener e() {
        return null;
    }

    public View.OnClickListener f() {
        return null;
    }

    public View.OnFocusChangeListener g() {
        return null;
    }

    public InterfaceC0220y h() {
        return null;
    }

    public boolean i(int i) {
        return true;
    }

    public boolean j() {
        return false;
    }

    public boolean k() {
        return this instanceof pe0;
    }

    public boolean l() {
        return false;
    }

    public final void q() {
        this.b.e(false);
    }

    public void a() {
    }

    public void b() {
    }

    public void r() {
    }

    public void s() {
    }

    public void m(EditText editText) {
    }

    public void n(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
    }

    public void o(AccessibilityEvent accessibilityEvent) {
    }

    public void p(boolean z) {
    }
}
