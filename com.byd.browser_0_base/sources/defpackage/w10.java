package defpackage;

import J.N;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.autofill.PersonalDataManager;

/* JADX INFO: compiled from: CreditCardNumberFormattingTextWatcher.java */
/* JADX INFO: loaded from: classes.dex */
public final class w10 implements TextWatcher {
    public boolean e = true;
    public boolean f;
    public boolean g;

    public static void a(Editable editable) {
        PersonalDataManager personalDataManagerC = PersonalDataManager.c();
        String string = editable.toString();
        personalDataManagerC.getClass();
        Object obj = ThreadUtils.a;
        for (int i : N.My_CbjBa(personalDataManagerC.a, personalDataManagerC, string, false).equals("amex") ? new int[]{4, 11} : new int[]{4, 9, 14}) {
            if (editable.length() > i) {
                editable.insert(i, " ");
            }
        }
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        if (this.f) {
            return;
        }
        this.f = true;
        if (this.e) {
            int iIndexOf = TextUtils.indexOf(editable, " ");
            while (iIndexOf >= 0) {
                int i = iIndexOf + 1;
                editable.delete(iIndexOf, i);
                iIndexOf = TextUtils.indexOf(editable, " ", i);
            }
            if (editable.length() > 16) {
                this.g = true;
                this.e = false;
            } else {
                a(editable);
            }
        } else if (this.g && editable.length() <= 16) {
            this.g = false;
            this.e = true;
            a(editable);
        }
        if (editable.length() == 0) {
            this.e = true;
        }
        this.f = false;
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.f || !this.e || i3 <= 0) {
            return;
        }
        int i4 = i3 + i;
        if ((TextUtils.indexOf(charSequence, " ", i, i4) == -1 && TextUtils.indexOf(charSequence, "-", i, i4) == -1) ? false : true) {
            this.e = false;
        }
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
