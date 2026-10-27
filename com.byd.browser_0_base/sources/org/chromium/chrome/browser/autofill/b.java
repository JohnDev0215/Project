package org.chromium.chrome.browser.autofill;

import J.N;
import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import defpackage.di1;
import defpackage.ie0;
import defpackage.za;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: AutofillNameFixFlowPrompt.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends c implements TextWatcher {
    public final a i;
    public final EditText j;
    public final ImageView k;
    public PopupWindow l;

    /* JADX INFO: compiled from: AutofillNameFixFlowPrompt.java */
    public interface a {
    }

    public b(Activity activity, a aVar, String str, String str2, int i, String str3) {
        super(activity, R$layout.autofill_name_fixflow, str2, i, str3);
        this.i = aVar;
        this.h.findViewById(R$id.cc_details).setVisibility(8);
        EditText editText = (EditText) this.h.findViewById(R$id.cc_name_edit);
        this.j = editText;
        editText.setText(str, TextView.BufferType.EDITABLE);
        ImageView imageView = (ImageView) this.h.findViewById(R$id.cc_name_tooltip_icon);
        this.k = imageView;
        this.e.l(di1.l, str.isEmpty());
        if (TextUtils.isEmpty(str)) {
            imageView.setVisibility(8);
        } else {
            imageView.setOnClickListener(new ie0(3, this));
        }
        editText.setOnEditorActionListener(new za(0, this));
        editText.addTextChangedListener(this);
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        this.e.l(di1.l, this.j.getText().toString().trim().isEmpty());
    }

    @Override // di1.a
    public final void b(int i) {
        a aVar = this.i;
        if (i == 2) {
            N.MDdVATYP(((AutofillNameFixFlowBridge) aVar).a);
        }
        AutofillNameFixFlowBridge autofillNameFixFlowBridge = (AutofillNameFixFlowBridge) aVar;
        N.MriHT7LJ(autofillNameFixFlowBridge.a, autofillNameFixFlowBridge);
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        if (i != 0) {
            if (i == 1) {
                this.f.b(2, propertyModel);
            }
        } else {
            String string = this.j.getText().toString();
            AutofillNameFixFlowBridge autofillNameFixFlowBridge = (AutofillNameFixFlowBridge) this.i;
            N.MW86M3Ok(autofillNameFixFlowBridge.a, autofillNameFixFlowBridge, string);
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
