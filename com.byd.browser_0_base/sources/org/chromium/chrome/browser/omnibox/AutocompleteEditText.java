package org.chromium.chrome.browser.omnibox;

import android.content.Context;
import android.graphics.Rect;
import android.provider.Settings;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import defpackage.ha;
import defpackage.ia;
import defpackage.jq2;
import defpackage.ku2;
import defpackage.pa;
import jq2.a;
import org.chromium.components.browser_ui.widget.text.VerticallyFixedEditText;

/* JADX INFO: loaded from: classes2.dex */
public class AutocompleteEditText extends VerticallyFixedEditText implements ia {
    public final AccessibilityManager k;
    public jq2 l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public View.OnKeyListener s;

    public AutocompleteEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.m = true;
        this.k = (AccessibilityManager) context.getSystemService("accessibility");
        addTextChangedListener(new ha(this));
    }

    private View.OnKeyListener getOnKeyListener() {
        return this.s;
    }

    @Override // org.chromium.components.browser_ui.widget.text.VerticallyFixedEditText, android.widget.TextView
    public boolean bringPointIntoView(int i) {
        if (this.q) {
            return false;
        }
        return super.bringPointIntoView(i);
    }

    @Override // android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        boolean zDispatchKeyEvent;
        View.OnKeyListener onKeyListener = getOnKeyListener();
        try {
            setOnKeyListener(null);
            if (onKeyListener != null && onKeyListener.onKey(this, keyEvent.getKeyCode(), keyEvent)) {
                return true;
            }
            if (this.r) {
                return true;
            }
            jq2 jq2Var = this.l;
            if (jq2Var == null) {
                return super.dispatchKeyEvent(keyEvent);
            }
            jq2.a aVar = jq2Var.f;
            ia iaVar = jq2Var.a;
            if (aVar == null) {
                zDispatchKeyEvent = super.dispatchKeyEvent(keyEvent);
            } else {
                aVar.d();
                if (keyEvent.getKeyCode() == 66 && keyEvent.getAction() == 0) {
                    jq2Var.f.a();
                }
                zDispatchKeyEvent = super.dispatchKeyEvent(keyEvent);
                jq2Var.f.e();
            }
            return zDispatchKeyEvent;
        } finally {
            setOnKeyListener(onKeyListener);
        }
    }

    public final boolean f() {
        jq2 jq2Var = this.l;
        if (jq2Var == null || jq2Var.i != 0 || !jq2Var.g) {
            return false;
        }
        pa paVar = jq2Var.b;
        if (!paVar.d()) {
            return false;
        }
        String keyboardPackageName = jq2Var.a.getKeyboardPackageName();
        if ((keyboardPackageName.contains(".iqqi") || keyboardPackageName.contains("omronsoft") || keyboardPackageName.contains(".iwnn")) ? false : true) {
            return jq2.n.matcher(paVar.a).matches();
        }
        return false;
    }

    public AccessibilityManager getAccessibilityManagerForTesting() {
        return this.k;
    }

    public int getAutocompleteLength() {
        jq2 jq2Var = this.l;
        if (jq2Var == null) {
            return 0;
        }
        return jq2Var.b.b.length();
    }

    public InputConnection getInputConnection() {
        jq2 jq2Var = this.l;
        if (jq2Var == null) {
            return null;
        }
        return jq2Var.f;
    }

    @Override // defpackage.ia
    public String getKeyboardPackageName() {
        String string = Settings.Secure.getString(getContext().getContentResolver(), "default_input_method");
        return string == null ? "" : string;
    }

    public String getTextWithAutocomplete() {
        jq2 jq2Var = this.l;
        return jq2Var == null ? "" : jq2Var.b.b();
    }

    public String getTextWithoutAutocomplete() {
        jq2 jq2Var = this.l;
        return jq2Var == null ? "" : jq2Var.b.a;
    }

    @Override // androidx.appcompat.widget.AppCompatEditText, android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        jq2.a aVar;
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        if (inputConnectionOnCreateInputConnection == null && this.l == null) {
            return null;
        }
        if (this.l == null) {
            jq2 jq2Var = new jq2(this);
            this.l = jq2Var;
            jq2Var.h = true;
            if (!hasFocus()) {
                pa paVar = jq2Var.c;
                paVar.c = -1;
                paVar.d = -1;
                pa paVar2 = jq2Var.b;
                paVar2.c = -1;
                paVar2.d = -1;
            }
            this.l.c(getText());
            jq2 jq2Var2 = this.l;
            Editable text = getText();
            getText().length();
            jq2Var2.d(text);
            this.l.b(getSelectionStart(), getSelectionEnd());
            if (this.n) {
                this.l.getClass();
            }
            this.l.h = this.m;
        }
        jq2 jq2Var3 = this.l;
        ia iaVar = jq2Var3.a;
        jq2Var3.k = iaVar.getSelectionStart();
        jq2Var3.l = iaVar.getSelectionEnd();
        jq2Var3.i = 0;
        if (inputConnectionOnCreateInputConnection == null) {
            jq2Var3.f = null;
            aVar = null;
        } else {
            jq2.a aVar2 = jq2Var3.new a();
            jq2Var3.f = aVar2;
            aVar2.setTarget(inputConnectionOnCreateInputConnection);
            aVar = jq2Var3.f;
        }
        if (this.r) {
            return null;
        }
        return aVar;
    }

    @Override // android.widget.TextView, android.view.View
    public void onFocusChanged(boolean z, int i, Rect rect) {
        jq2 jq2Var = this.l;
        if (jq2Var != null && !z) {
            pa paVar = jq2Var.c;
            paVar.c = -1;
            paVar.d = -1;
            pa paVar2 = jq2Var.b;
            paVar2.c = -1;
            paVar2.d = -1;
        }
        super.onFocusChanged(z, i, rect);
        if (z) {
            return;
        }
        setCursorVisible(false);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        ku2 ku2VarT = ku2.t();
        try {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            ku2VarT.close();
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @Override // android.view.View
    public final void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // android.widget.TextView, android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        boolean zOnPreDraw = super.onPreDraw();
        if (!this.q) {
            return zOnPreDraw;
        }
        this.q = false;
        bringPointIntoView(getSelectionStart());
        return true;
    }

    @Override // android.widget.TextView
    public final void onSelectionChanged(int i, int i2) {
        jq2 jq2Var = this.l;
        if (jq2Var != null) {
            jq2Var.b(i, i2);
        }
        super.onSelectionChanged(i, i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0012  */
    @Override // android.widget.TextView
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onTextChanged(java.lang.CharSequence r1, int r2, int r3, int r4) {
        /*
            r0 = this;
            super.onTextChanged(r1, r2, r3, r4)
            if (r2 != 0) goto L12
            int r4 = r4 - r3
            r2 = 1
            if (r4 <= r2) goto L12
            boolean r3 = r0.o
            if (r3 != 0) goto L12
            boolean r3 = r0.m
            if (r3 != 0) goto L12
            goto L13
        L12:
            r2 = 0
        L13:
            r0.n = r2
            jq2 r0 = r0.l
            if (r0 == 0) goto L1c
            r0.d(r1)
        L1c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.omnibox.AutocompleteEditText.onTextChanged(java.lang.CharSequence, int, int, int):void");
    }

    @Override // android.widget.TextView, android.view.View, android.view.accessibility.AccessibilityEventSource, defpackage.ia
    public final void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        jq2 jq2Var;
        if ((this.m || ((jq2Var = this.l) != null && jq2Var.m)) && (accessibilityEvent.getEventType() == 8192 || accessibilityEvent.getEventType() == 16)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public void setIgnoreImeForTest(boolean z) {
        this.r = z;
    }

    public void setIgnoreTextChangesForAutocomplete(boolean z) {
        this.m = z;
        jq2 jq2Var = this.l;
        if (jq2Var != null) {
            jq2Var.h = z;
        }
    }

    @Override // android.view.View
    public void setOnKeyListener(View.OnKeyListener onKeyListener) {
        super.setOnKeyListener(onKeyListener);
        this.s = onKeyListener;
    }

    @Override // android.widget.EditText, android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        this.q = false;
        ku2 ku2VarT = ku2.t();
        try {
            super.setText(charSequence, bufferType);
            ku2VarT.close();
            jq2 jq2Var = this.l;
            if (jq2Var != null) {
                jq2Var.c(charSequence);
            }
        } catch (Throwable th) {
            try {
                ku2VarT.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    @Override // defpackage.ia
    public void b() {
    }
}
