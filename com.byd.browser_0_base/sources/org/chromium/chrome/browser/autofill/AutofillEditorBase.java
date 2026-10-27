package org.chromium.chrome.browser.autofill;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import androidx.fragment.app.Fragment;
import defpackage.pj2;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.components.browser_ui.widget.FadingEdgeScrollView;

/* JADX INFO: loaded from: classes.dex */
public abstract class AutofillEditorBase extends Fragment implements AdapterView.OnItemSelectedListener, View.OnTouchListener, TextWatcher {
    public String Z;
    public boolean a0;
    public Context b0;

    public abstract int I0();

    public abstract int J0(boolean z);

    public abstract boolean K0();

    /* JADX WARN: Removed duplicated region for block: B:9:0x001b  */
    @Override // androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d0(android.view.Menu r2, android.view.MenuInflater r3) {
        /*
            r1 = this;
            r2.clear()
            int r0 = org.chromium.chrome.R$menu.prefeditor_editor_menu
            r3.inflate(r0, r2)
            int r3 = org.chromium.chrome.R$id.delete_menu_id
            android.view.MenuItem r2 = r2.findItem(r3)
            if (r2 == 0) goto L1f
            boolean r3 = r1.a0
            if (r3 != 0) goto L1b
            boolean r1 = r1 instanceof org.chromium.chrome.browser.autofill.settings.AutofillServerCardEditor
            r3 = 1
            r1 = r1 ^ r3
            if (r1 == 0) goto L1b
            goto L1c
        L1b:
            r3 = 0
        L1c:
            r2.setVisible(r3)
        L1f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.autofill.AutofillEditorBase.d0(android.view.Menu, android.view.MenuInflater):void");
    }

    @Override // androidx.fragment.app.Fragment
    public View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        D0();
        this.b0 = viewGroup.getContext();
        Bundle bundle2 = this.j;
        if (bundle2 != null) {
            this.Z = bundle2.getString("guid");
        }
        if (this.Z == null) {
            this.Z = "";
            this.a0 = true;
        } else {
            this.a0 = false;
        }
        D().setTitle(J0(this.a0));
        View viewInflate = layoutInflater.inflate(R$layout.autofill_editor_base, viewGroup, false);
        FadingEdgeScrollView fadingEdgeScrollView = (FadingEdgeScrollView) viewInflate.findViewById(R$id.scroll_view);
        fadingEdgeScrollView.h = 0;
        fadingEdgeScrollView.i = 1;
        fadingEdgeScrollView.invalidate();
        fadingEdgeScrollView.getViewTreeObserver().addOnScrollChangedListener(new pj2(fadingEdgeScrollView, viewInflate.findViewById(R$id.shadow)));
        LinearLayout linearLayout = (LinearLayout) fadingEdgeScrollView.findViewById(R$id.content);
        layoutInflater.inflate(I0(), (ViewGroup) linearLayout, true);
        layoutInflater.inflate(R$layout.autofill_editor_base_buttons, (ViewGroup) linearLayout, true);
        return viewInflate;
    }

    @Override // android.view.View.OnTouchListener
    @SuppressLint({"ClickableViewAccessibility"})
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (view instanceof Spinner) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        return false;
    }

    public void H0() {
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
