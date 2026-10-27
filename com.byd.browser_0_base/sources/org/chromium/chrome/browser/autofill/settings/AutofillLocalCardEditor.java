package org.chromium.chrome.browser.autofill.settings;

import J.N;
import android.R;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.google.android.material.textfield.TextInputLayout;
import defpackage.n3;
import defpackage.sa;
import defpackage.sf;
import defpackage.w10;
import defpackage.ya;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import org.chromium.base.ThreadUtils;
import org.chromium.build.annotations.UsedByReflection;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* JADX INFO: loaded from: classes.dex */
public class AutofillLocalCardEditor extends sa {
    public Button f0;
    public TextInputLayout g0;
    public EditText h0;
    public TextInputLayout i0;
    public EditText j0;
    public TextInputLayout k0;
    public EditText l0;
    public Spinner m0;
    public Spinner n0;
    public boolean o0 = true;
    public int p0;
    public int q0;

    @UsedByReflection
    public AutofillLocalCardEditor() {
    }

    @Override // org.chromium.chrome.browser.autofill.AutofillEditorBase
    public final void H0() {
        if (this.Z != null) {
            PersonalDataManager personalDataManagerC = PersonalDataManager.c();
            String str = this.Z;
            personalDataManagerC.getClass();
            Object obj = ThreadUtils.a;
            N.MIAwuIe5(personalDataManagerC.a, personalDataManagerC, str);
        }
    }

    @Override // org.chromium.chrome.browser.autofill.AutofillEditorBase
    public final int I0() {
        return R$layout.autofill_local_card_editor;
    }

    @Override // org.chromium.chrome.browser.autofill.AutofillEditorBase
    public final int J0(boolean z) {
        return z ? R$string.autofill_create_credit_card : R$string.autofill_edit_credit_card;
    }

    @Override // org.chromium.chrome.browser.autofill.AutofillEditorBase
    public final boolean K0() {
        String strReplaceAll = this.l0.getText().toString().replaceAll("\\s+", "");
        PersonalDataManager personalDataManagerC = PersonalDataManager.c();
        personalDataManagerC.getClass();
        Object obj = ThreadUtils.a;
        if (TextUtils.isEmpty(N.My_CbjBa(personalDataManagerC.a, personalDataManagerC, strReplaceAll, true))) {
            this.k0.setError(this.b0.getString(R$string.payments_card_number_invalid_validation_message));
            return false;
        }
        long j = personalDataManagerC.a;
        PersonalDataManager.CreditCard creditCard = (PersonalDataManager.CreditCard) N.MHzz0BSK(j, personalDataManagerC, strReplaceAll);
        creditCard.a = this.Z;
        creditCard.b = "Chrome settings";
        creditCard.f = this.h0.getText().toString().trim();
        creditCard.h = String.valueOf(this.m0.getSelectedItemPosition() + 1);
        creditCard.i = (String) this.n0.getSelectedItem();
        creditCard.l = ((PersonalDataManager.AutofillProfile) this.d0.getSelectedItem()).getGUID();
        creditCard.p = this.j0.getText().toString().trim();
        creditCard.a = N.M7sdleUt(j, personalDataManagerC, creditCard);
        if (this.a0) {
            n3.X0("AutofillCreditCardsAdded");
            if (!creditCard.getNickname().isEmpty()) {
                n3.X0("AutofillCreditCardsAddedWithNickname");
            }
        }
        return true;
    }

    @Override // defpackage.sa
    public final void L0(View view) {
        super.L0(view);
        this.h0.addTextChangedListener(this);
        this.l0.addTextChangedListener(this);
        this.m0.setOnItemSelectedListener(this);
        this.n0.setOnItemSelectedListener(this);
        this.m0.setOnTouchListener(this);
        this.n0.setOnTouchListener(this);
    }

    public final void M0() {
        this.f0.setEnabled(!TextUtils.isEmpty(this.l0.getText()) && this.o0);
    }

    @Override // org.chromium.chrome.browser.autofill.AutofillEditorBase, android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        M0();
    }

    @Override // defpackage.sa, org.chromium.chrome.browser.autofill.AutofillEditorBase, androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        WindowManager.LayoutParams attributes = D().getWindow().getAttributes();
        attributes.flags |= 8192;
        D().getWindow().setAttributes(attributes);
        View viewE0 = super.e0(layoutInflater, viewGroup, bundle);
        this.f0 = (Button) viewE0.findViewById(R$id.button_primary);
        this.g0 = (TextInputLayout) viewE0.findViewById(R$id.credit_card_name_label);
        this.h0 = (EditText) viewE0.findViewById(R$id.credit_card_name_edit);
        this.i0 = (TextInputLayout) viewE0.findViewById(R$id.credit_card_nickname_label);
        this.j0 = (EditText) viewE0.findViewById(R$id.credit_card_nickname_edit);
        this.k0 = (TextInputLayout) viewE0.findViewById(R$id.credit_card_number_label);
        this.l0 = (EditText) viewE0.findViewById(R$id.credit_card_number_edit);
        this.j0.addTextChangedListener(new ya(this));
        this.j0.setOnFocusChangeListener(new sf(2, this));
        this.l0.addTextChangedListener(new w10());
        this.m0 = (Spinner) viewE0.findViewById(R$id.autofill_credit_card_editor_month_spinner);
        this.n0 = (Spinner) viewE0.findViewById(R$id.autofill_credit_card_editor_year_spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(D(), R.layout.simple_spinner_item);
        Calendar calendar = Calendar.getInstance();
        boolean z = true;
        calendar.set(5, 1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM (MM)", Locale.getDefault());
        for (int i = 0; i < 12; i++) {
            calendar.set(2, i);
            arrayAdapter.add(simpleDateFormat.format(calendar.getTime()));
        }
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        this.m0.setAdapter((SpinnerAdapter) arrayAdapter);
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(D(), R.layout.simple_spinner_item);
        int i2 = calendar.get(1);
        for (int i3 = i2; i3 < i2 + 10; i3++) {
            arrayAdapter2.add(Integer.toString(i3));
        }
        arrayAdapter2.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        this.n0.setAdapter((SpinnerAdapter) arrayAdapter2);
        PersonalDataManager.CreditCard creditCard = this.c0;
        if (creditCard == null) {
            this.k0.requestFocus();
        } else {
            if (!TextUtils.isEmpty(creditCard.getName())) {
                this.g0.getEditText().setText(this.c0.getName());
            }
            if (!TextUtils.isEmpty(this.c0.getNumber())) {
                this.k0.getEditText().setText(this.c0.getNumber());
            }
            this.g0.setFocusableInTouchMode(true);
            int i4 = (!this.c0.getMonth().isEmpty() ? Integer.parseInt(this.c0.getMonth()) : 1) - 1;
            this.p0 = i4;
            this.m0.setSelection(i4);
            this.q0 = 0;
            int i5 = 0;
            while (true) {
                if (i5 >= this.n0.getAdapter().getCount()) {
                    z = false;
                    break;
                }
                if (this.c0.getYear().equals(this.n0.getAdapter().getItem(i5))) {
                    this.q0 = i5;
                    break;
                }
                i5++;
            }
            if (!z && !this.c0.getYear().isEmpty()) {
                ((ArrayAdapter) this.n0.getAdapter()).insert(this.c0.getYear(), 0);
                this.q0 = 0;
            }
            this.n0.setSelection(this.q0);
            if (!this.c0.getNickname().isEmpty()) {
                this.j0.setText(this.c0.getNickname());
            }
        }
        L0(viewE0);
        return viewE0;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        if ((adapterView != this.n0 || i == this.q0) && ((adapterView != this.m0 || i == this.p0) && (adapterView != this.d0 || i == this.e0))) {
            return;
        }
        M0();
    }
}
