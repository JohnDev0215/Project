package org.chromium.chrome.browser.privacy.secure_dns;

import J.N;
import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.preference.Preference;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import java.util.Collections;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.privacy.secure_dns.SecureDnsProviderPreference;
import org.chromium.chrome.browser.privacy.secure_dns.SecureDnsSpinner;
import org.chromium.chrome.browser.privacy.secure_dns.a;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescriptionLayout;

/* JADX INFO: loaded from: classes2.dex */
class SecureDnsProviderPreference extends Preference implements RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener, TextWatcher, SecureDnsSpinner.a {
    public final String S;
    public final String T;
    public final String U;
    public final ArrayList V;
    public RadioButtonWithDescriptionLayout W;
    public RadioButtonWithDescription X;
    public RadioButtonWithDescription Y;
    public SecureDnsSpinner Z;
    public TextView a0;
    public EditText b0;
    public TextInputLayout c0;
    public a d0;
    public final b e0;

    public static class a {
        public final boolean a;
        public final String b;
        public final boolean c;

        public a(String str, boolean z, boolean z2) {
            this.a = z;
            this.b = str;
            this.c = z2;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return aVar.a == this.a && aVar.b.equals(this.b) && aVar.c == this.c;
        }

        public final int hashCode() {
            return toString().hashCode();
        }

        public final String toString() {
            return String.format("State(%b, %s, %b)", Boolean.valueOf(this.a), this.b, Boolean.valueOf(this.c));
        }
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [org.chromium.chrome.browser.privacy.secure_dns.b] */
    public SecureDnsProviderPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e0 = new Runnable() { // from class: org.chromium.chrome.browser.privacy.secure_dns.b
            @Override // java.lang.Runnable
            public final void run() {
                final SecureDnsProviderPreference secureDnsProviderPreference = this.e;
                final String str = secureDnsProviderPreference.d0.b;
                if (str.isEmpty()) {
                    return;
                }
                SecureDnsProviderPreference.a aVar = secureDnsProviderPreference.d0;
                if (aVar.c && aVar.a) {
                    new Thread(new Runnable() { // from class: org.chromium.chrome.browser.privacy.secure_dns.c
                        @Override // java.lang.Runnable
                        public final void run() {
                            final SecureDnsProviderPreference secureDnsProviderPreference2 = secureDnsProviderPreference;
                            secureDnsProviderPreference2.getClass();
                            final String str2 = str;
                            if (N.MvzcX2os(str2)) {
                                return;
                            }
                            secureDnsProviderPreference2.b0.post(new Runnable() { // from class: org.chromium.chrome.browser.privacy.secure_dns.d
                                @Override // java.lang.Runnable
                                public final void run() {
                                    SecureDnsProviderPreference secureDnsProviderPreference3 = secureDnsProviderPreference2;
                                    if (secureDnsProviderPreference3.d0.b.contentEquals(str2)) {
                                        secureDnsProviderPreference3.c0.setError(secureDnsProviderPreference3.U);
                                    }
                                }
                            });
                        }
                    }).start();
                }
            }
        };
        this.f17J = R$layout.secure_dns_provider_preference;
        this.S = context.getString(R$string.settings_secure_dropdown_mode_privacy_policy);
        this.T = context.getString(R$string.settings_secure_dns_custom_format_error);
        this.U = context.getString(R$string.settings_secure_dns_custom_connection_error);
        ArrayList arrayListA = org.chromium.chrome.browser.privacy.secure_dns.a.a();
        ArrayList arrayList = new ArrayList(arrayListA.size() + 1);
        arrayList.add(new a.C0109a(context.getString(R$string.settings_custom), "", ""));
        Collections.shuffle(arrayListA);
        arrayList.addAll(arrayListA);
        this.V = arrayList;
    }

    public final int Y() {
        for (int i = 1; i < this.Z.getCount(); i++) {
            if (((a.C0109a) this.Z.getItemAtPosition(i)).b.equals(this.d0.b)) {
                return i;
            }
        }
        return 0;
    }

    public final void Z() {
        if (this.W == null) {
            return;
        }
        boolean zB = this.Y.b();
        boolean z = this.d0.a;
        if (zB != z) {
            this.Y.setChecked(z);
        }
        boolean z2 = !this.d0.a;
        if (this.X.b() != z2) {
            this.X.setChecked(z2);
        }
        int iY = Y();
        if (this.Z.getSelectedItemPosition() != iY) {
            this.Z.setSelection(iY);
        }
        if (this.d0.a) {
            this.Z.setVisibility(0);
            if (iY > 0) {
                this.a0.setText(Html.fromHtml(this.S.replace("$1", ((a.C0109a) this.Z.getSelectedItem()).c)));
                this.a0.setVisibility(0);
                this.c0.setVisibility(8);
            } else {
                if (!this.b0.getText().toString().equals(this.d0.b)) {
                    this.b0.setText(this.d0.b);
                    EditText editText = this.b0;
                    b bVar = this.e0;
                    editText.removeCallbacks(bVar);
                    if (this.d0.a) {
                        this.b0.requestFocus();
                        this.b0.postDelayed(bVar, 1000L);
                    }
                }
                a aVar = this.d0;
                this.c0.setError((aVar.c || "https://".startsWith(aVar.b)) ? false : true ? this.T : null);
                this.c0.setVisibility(0);
                this.a0.setVisibility(8);
            }
        } else {
            this.Z.setVisibility(8);
            this.a0.setVisibility(8);
            this.c0.setVisibility(8);
        }
        N.M6OgZ3EY(this.d0.c);
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
        a aVar = this.d0;
        a aVar2 = new a(editable.toString(), aVar.a, aVar.c);
        if (!h(aVar2)) {
            Z();
        } else if (!aVar2.equals(this.d0)) {
            this.d0 = aVar2;
            Z();
        }
        EditText editText = this.b0;
        b bVar = this.e0;
        editText.removeCallbacks(bVar);
        this.b0.postDelayed(bVar, 1000L);
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        boolean z = i == R$id.secure;
        a aVar = this.d0;
        if (aVar.a != z) {
            a aVar2 = new a(aVar.b, z, aVar.c);
            if (!h(aVar2)) {
                Z();
            } else {
                if (aVar2.equals(this.d0)) {
                    return;
                }
                this.d0 = aVar2;
                Z();
            }
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        int iY = Y();
        if (iY == i) {
            return;
        }
        a.C0109a c0109a = (a.C0109a) adapterView.getItemAtPosition(iY);
        a.C0109a c0109a2 = (a.C0109a) adapterView.getItemAtPosition(i);
        a aVar = this.d0;
        a aVar2 = new a(c0109a2.b, aVar.a, aVar.c);
        if (!h(aVar2)) {
            Z();
        } else if (!aVar2.equals(this.d0)) {
            this.d0 = aVar2;
            Z();
        }
        N.MHfKmORH(c0109a.b, c0109a2.b);
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView<?> adapterView) {
        Log.i("hello", "onNothingSelected");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x005f  */
    @Override // androidx.preference.Preference
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void y(defpackage.n32 r6) {
        /*
            Method dump skipped, instruction units count: 215
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.privacy.secure_dns.SecureDnsProviderPreference.y(n32):void");
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
