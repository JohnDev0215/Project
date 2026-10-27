package defpackage;

import J.N;
import android.R;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import java.util.ArrayList;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.autofill.AutofillEditorBase;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: compiled from: AutofillCreditCardEditor.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class sa extends AutofillEditorBase {
    public PersonalDataManager.CreditCard c0;
    public Spinner d0;
    public int e0;

    public void L0(View view) {
        ((Button) view.findViewById(R$id.button_secondary)).setOnClickListener(new va(this));
        Button button = (Button) view.findViewById(R$id.button_primary);
        button.setOnClickListener(new wa(this));
        button.setEnabled(false);
        this.d0.setOnItemSelectedListener(this);
        this.d0.setOnTouchListener(this);
    }

    @Override // org.chromium.chrome.browser.autofill.AutofillEditorBase, androidx.fragment.app.Fragment
    public View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewE0 = super.e0(layoutInflater, viewGroup, bundle);
        D().getWindow().getDecorView().setImportantForAutofill(8);
        ArrayAdapter arrayAdapter = new ArrayAdapter(D(), R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        PersonalDataManager.AutofillProfile autofillProfile = new PersonalDataManager.AutofillProfile();
        autofillProfile.q = D().getString(R$string.select);
        arrayAdapter.add(autofillProfile);
        PersonalDataManager personalDataManagerC = PersonalDataManager.c();
        personalDataManagerC.getClass();
        Object obj = ThreadUtils.a;
        long j = personalDataManagerC.a;
        ArrayList<PersonalDataManager.AutofillProfile> arrayListE = personalDataManagerC.e(N.M6XJvXko(j, personalDataManagerC), N.M4q3jK16(j, personalDataManagerC));
        int i = 0;
        for (int i2 = 0; i2 < arrayListE.size(); i2++) {
            PersonalDataManager.AutofillProfile autofillProfile2 = arrayListE.get(i2);
            if (autofillProfile2.c && !TextUtils.isEmpty(autofillProfile2.getStreetAddress())) {
                arrayAdapter.add(autofillProfile2);
            }
        }
        Spinner spinner = (Spinner) viewE0.findViewById(R$id.autofill_credit_card_editor_billing_address_spinner);
        this.d0 = spinner;
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        if (arrayAdapter.getCount() == 1) {
            this.d0.setEnabled(false);
        }
        PersonalDataManager personalDataManagerC2 = PersonalDataManager.c();
        String str = this.Z;
        personalDataManagerC2.getClass();
        Object obj2 = ThreadUtils.a;
        PersonalDataManager.CreditCard creditCard = (PersonalDataManager.CreditCard) N.M3g2doJx(personalDataManagerC2.a, personalDataManagerC2, str);
        this.c0 = creditCard;
        if (creditCard != null && !TextUtils.isEmpty(creditCard.getBillingAddressId())) {
            while (true) {
                if (i >= this.d0.getAdapter().getCount()) {
                    break;
                }
                if (TextUtils.equals(((PersonalDataManager.AutofillProfile) this.d0.getAdapter().getItem(i)).getGUID(), this.c0.getBillingAddressId())) {
                    this.e0 = i;
                    this.d0.setSelection(i);
                    break;
                }
                i++;
            }
        }
        return viewE0;
    }

    @Override // androidx.fragment.app.Fragment
    public final boolean k0(MenuItem menuItem) {
        if (menuItem.getItemId() == R$id.delete_menu_id) {
            H0();
            D().finish();
            return true;
        }
        if (menuItem.getItemId() != R$id.help_menu_id) {
            return false;
        }
        ((hu0) hu0.a()).c(D(), D().getString(R$string.help_context_autofill), null, Profile.d());
        return true;
    }
}
