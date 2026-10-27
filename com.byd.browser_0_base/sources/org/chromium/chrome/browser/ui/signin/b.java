package org.chromium.chrome.browser.ui.signin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import defpackage.bi1;
import defpackage.di1;
import defpackage.gc3;
import defpackage.ie0;
import defpackage.ke0;
import defpackage.z7;
import java.util.Arrays;
import java.util.List;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.widget.RadioButtonWithDescription;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ConfirmImportSyncDataDialogCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {
    public final a a;
    public final RadioButtonWithDescription b;
    public final PropertyModel c;
    public final bi1 d;

    /* JADX INFO: compiled from: ConfirmImportSyncDataDialogCoordinator.java */
    public interface a {
    }

    public b(Context context, bi1 bi1Var, a aVar, String str, String str2) {
        this.a = aVar;
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.confirm_import_sync_data, (ViewGroup) null);
        RadioButtonWithDescription radioButtonWithDescription = (RadioButtonWithDescription) viewInflate.findViewById(R$id.sync_confirm_import_choice);
        RadioButtonWithDescription radioButtonWithDescription2 = (RadioButtonWithDescription) viewInflate.findViewById(R$id.sync_keep_separate_choice);
        this.b = radioButtonWithDescription2;
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        boolean z = z7.D(profileD).f() != null;
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.b(di1.q, true);
        bVar.e(di1.j, context.getString(R$string.continue_button));
        bVar.b(di1.l, !z);
        bVar.e(di1.m, context.getString(R$string.cancel));
        bVar.e(di1.h, viewInflate);
        bVar.e(di1.a, new org.chromium.chrome.browser.ui.signin.a(this));
        PropertyModel propertyModelA = bVar.a();
        this.c = propertyModelA;
        this.d = bi1Var;
        ((TextView) viewInflate.findViewById(R$id.sync_import_data_prompt)).setText(context.getString(R$string.sync_import_data_prompt, str));
        radioButtonWithDescription.setDescriptionText(context.getString(R$string.sync_import_existing_data_subtext, str2));
        radioButtonWithDescription2.setDescriptionText(context.getString(R$string.sync_keep_existing_data_separate_subtext_existing_data));
        List<RadioButtonWithDescription> listAsList = Arrays.asList(radioButtonWithDescription, radioButtonWithDescription2);
        radioButtonWithDescription.setRadioButtonGroup(listAsList);
        radioButtonWithDescription2.setRadioButtonGroup(listAsList);
        if (z) {
            radioButtonWithDescription2.setChecked(true);
            radioButtonWithDescription.setOnClickListener(new ie0(25, context));
        } else {
            radioButtonWithDescription2.setOnCheckedChangeListener(new gc3(this));
            radioButtonWithDescription.setOnCheckedChangeListener(new ke0(26, this));
        }
        bi1Var.j(propertyModelA, 1, false);
    }
}
