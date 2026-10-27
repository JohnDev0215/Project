package org.chromium.chrome.browser.autofill;

import J.N;
import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;
import defpackage.bi1;
import defpackage.bq;
import defpackage.di1;
import defpackage.qa;
import defpackage.sk;
import defpackage.so;
import defpackage.t41;
import defpackage.tf0;
import defpackage.wd2;
import defpackage.ym2;
import defpackage.zj;
import java.util.HashMap;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes.dex */
public class SaveUpdateAddressProfilePrompt {
    public final SaveUpdateAddressProfilePromptController a;
    public final bi1 b;
    public final PropertyModel c;
    public final View d;
    public final tf0 e;
    public final org.chromium.chrome.browser.autofill.settings.a f;
    public boolean g;

    /* JADX WARN: Type inference failed for: r2v0, types: [T, ym2] */
    /* JADX WARN: Type inference failed for: r6v2, types: [T, android.view.View] */
    public SaveUpdateAddressProfilePrompt(SaveUpdateAddressProfilePromptController saveUpdateAddressProfilePromptController, bi1 bi1Var, Activity activity, Profile profile, PersonalDataManager.AutofillProfile autofillProfile, boolean z) {
        this.a = saveUpdateAddressProfilePromptController;
        this.b = bi1Var;
        ?? Inflate = LayoutInflater.from(activity).inflate(z ? R$layout.autofill_update_address_profile_prompt : R$layout.autofill_save_address_profile_prompt, (ViewGroup) null);
        this.d = Inflate;
        if (!z) {
            final TextInputLayout textInputLayout = (TextInputLayout) Inflate.findViewById(R$id.nickname_input_layout);
            sk skVar = bq.a;
            if (N.M09VlOh_("AutofillAddressProfileSavePromptNicknameSupport")) {
                final EditText editText = (EditText) Inflate.findViewById(R$id.nickname_input);
                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: xd2
                    @Override // android.view.View.OnFocusChangeListener
                    public final void onFocusChange(View view, boolean z2) {
                        textInputLayout.setHint((z2 || !TextUtils.isEmpty(editText.getText())) ? "Label" : "Add a label");
                    }
                });
                t41.f.a(new t41.b() { // from class: yd2
                    @Override // t41.b
                    public final void a(boolean z2) {
                        if (z2) {
                            return;
                        }
                        EditText editText2 = editText;
                        if (editText2.hasFocus()) {
                            editText2.clearFocus();
                        }
                    }
                });
            } else {
                textInputLayout.setVisibility(8);
            }
        }
        HashMap mapE = PropertyModel.e(di1.B);
        PropertyModel.l<di1.a> lVar = di1.a;
        ?? ym2Var = new ym2(bi1Var, new so(7, this));
        int i = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = ym2Var;
        mapE.put(lVar, gVar);
        PropertyModel.j jVar = di1.u;
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = 1;
        mapE.put(jVar, dVar);
        PropertyModel.r<View> rVar = di1.h;
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = Inflate;
        mapE.put(rVar, gVar2);
        this.c = new PropertyModel(mapE);
        tf0 tf0Var = new tf0(activity, null, profile, true);
        this.e = tf0Var;
        tf0Var.t = true;
        org.chromium.chrome.browser.autofill.settings.a aVar = new org.chromium.chrome.browser.autofill.settings.a(false);
        this.f = aVar;
        aVar.a = tf0Var;
        aVar.b = tf0Var.getContext();
        Inflate.findViewById(R$id.edit_button).setOnClickListener(new wd2(this, 0, new qa(activity, autofillProfile)));
    }

    public static void a(TextView textView, String str) {
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(str);
        }
    }

    @CalledByNative
    public static SaveUpdateAddressProfilePrompt create(WindowAndroid windowAndroid, SaveUpdateAddressProfilePromptController saveUpdateAddressProfilePromptController, Profile profile, PersonalDataManager.AutofillProfile autofillProfile, boolean z) {
        Activity activity = (Activity) windowAndroid.k().get();
        bi1 bi1VarN = windowAndroid.n();
        if (activity == null || bi1VarN == null) {
            return null;
        }
        return new SaveUpdateAddressProfilePrompt(saveUpdateAddressProfilePromptController, bi1VarN, activity, profile, autofillProfile, z);
    }

    @CalledByNative
    public final void dismiss() {
        if (!this.g) {
            tf0 tf0Var = this.e;
            if (tf0Var.isShowing()) {
                tf0Var.dismiss();
            }
        }
        this.b.b(4, this.c);
    }

    @CalledByNative
    public final void setDialogDetails(String str, String str2, String str3) {
        PropertyModel propertyModel = this.c;
        propertyModel.p(di1.c, str);
        propertyModel.p(di1.j, str2);
        propertyModel.p(di1.m, str3);
        this.f.p = str2;
    }

    @CalledByNative
    public final void setSaveDetails(String str, String str2, String str3) {
        int i = R$id.address;
        View view = this.d;
        a((TextView) view.findViewById(i), str);
        a((TextView) view.findViewById(R$id.email), str2);
        a((TextView) view.findViewById(R$id.phone), str3);
    }

    @CalledByNative
    public final void setUpdateDetails(String str, String str2, String str3) {
        int i = R$id.subtitle;
        View view = this.d;
        a((TextView) view.findViewById(i), str);
        boolean z = !TextUtils.isEmpty(str2);
        view.findViewById(R$id.header_new).setVisibility(z ? 0 : 8);
        view.findViewById(R$id.header_old).setVisibility(z ? 0 : 8);
        view.findViewById(R$id.no_header_space).setVisibility(z ? 8 : 0);
        a((TextView) view.findViewById(R$id.details_old), str2);
        a((TextView) view.findViewById(R$id.details_new), str3);
    }

    @CalledByNative
    public final void show() {
        this.b.j(this.c, 1, false);
    }
}
