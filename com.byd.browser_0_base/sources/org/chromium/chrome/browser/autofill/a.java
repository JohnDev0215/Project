package org.chromium.chrome.browser.autofill;

import J.N;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import defpackage.di1;
import defpackage.je0;
import defpackage.xa;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: AutofillExpirationDateFixFlowPrompt.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends c implements TextWatcher {
    public final InterfaceC0072a i;
    public final EditText j;
    public final EditText k;
    public final TextView l;
    public boolean m;
    public boolean n;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.autofill.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AutofillExpirationDateFixFlowPrompt.java */
    public interface InterfaceC0072a {
    }

    public a(Activity activity, InterfaceC0072a interfaceC0072a, String str, int i, String str2, String str3) {
        super(activity, R$layout.autofill_expiration_date_fix_flow, str, i, str3);
        this.i = interfaceC0072a;
        this.l = (TextView) this.h.findViewById(R$id.error_message);
        ((TextView) this.h.findViewById(R$id.cc_details_masked)).setText(str2);
        this.h.findViewById(R$id.message_divider).setVisibility(8);
        this.h.findViewById(R$id.google_pay_logo).setVisibility(8);
        EditText editText = (EditText) this.h.findViewById(R$id.cc_month_edit);
        this.j = editText;
        editText.addTextChangedListener(this);
        editText.setOnFocusChangeListener(new xa(0, this));
        EditText editText2 = (EditText) this.h.findViewById(R$id.cc_year_edit);
        this.k = editText2;
        editText2.addTextChangedListener(this);
        editText2.setOnFocusChangeListener(new je0(1, this));
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        boolean z = this.m;
        boolean z2 = this.n;
        EditText editText = this.j;
        EditText editText2 = this.k;
        int iC = d.c(editText, editText2, z, z2);
        this.e.l(di1.l, iC != 7);
        d.g(iC, this.g, this.l);
        d.h(iC, this.g, editText, editText2, null);
        if (editText.isFocused() && editText.getText().length() == 2 && iC != 1) {
            editText2.requestFocus();
            this.n = true;
        }
    }

    @Override // di1.a
    public final void b(int i) {
        InterfaceC0072a interfaceC0072a = this.i;
        if (i == 2) {
            AutofillExpirationDateFixFlowBridge autofillExpirationDateFixFlowBridge = (AutofillExpirationDateFixFlowBridge) interfaceC0072a;
            N.MVtRWd5A(autofillExpirationDateFixFlowBridge.a, autofillExpirationDateFixFlowBridge);
        }
        AutofillExpirationDateFixFlowBridge autofillExpirationDateFixFlowBridge2 = (AutofillExpirationDateFixFlowBridge) interfaceC0072a;
        N.MYC4Z0Ea(autofillExpirationDateFixFlowBridge2.a, autofillExpirationDateFixFlowBridge2);
        autofillExpirationDateFixFlowBridge2.a = 0L;
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        if (i != 0) {
            if (i == 1) {
                this.f.b(2, propertyModel);
            }
        } else {
            String strTrim = this.j.getText().toString().trim();
            String strTrim2 = this.k.getText().toString().trim();
            AutofillExpirationDateFixFlowBridge autofillExpirationDateFixFlowBridge = (AutofillExpirationDateFixFlowBridge) this.i;
            N.MX7djb2r(autofillExpirationDateFixFlowBridge.a, autofillExpirationDateFixFlowBridge, strTrim, strTrim2);
            this.f.b(1, propertyModel);
        }
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
