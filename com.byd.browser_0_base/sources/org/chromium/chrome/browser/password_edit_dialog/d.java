package org.chromium.chrome.browser.password_edit_dialog;

import android.R;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import defpackage.fx1;
import defpackage.rn1;
import defpackage.v62;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.R$layout;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: PasswordEditDialogViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d {
    public static void a(PropertyModel propertyModel, PasswordEditDialogView passwordEditDialogView, v62 v62Var) {
        PropertyModel.l<List<String>> lVar = fx1.a;
        PropertyModel.p pVar = fx1.c;
        if (v62Var == lVar) {
            if (passwordEditDialogView instanceof PasswordEditDialogWithDetailsView) {
                PasswordEditDialogWithDetailsView passwordEditDialogWithDetailsView = (PasswordEditDialogWithDetailsView) passwordEditDialogView;
                List<String> list = (List) propertyModel.j(lVar);
                passwordEditDialogWithDetailsView.l = list;
                passwordEditDialogWithDetailsView.f.setAdapter(new rn1(R$layout.password_edit_dialog_dropdown_item, passwordEditDialogWithDetailsView.getContext(), list));
                passwordEditDialogWithDetailsView.d(passwordEditDialogWithDetailsView.f.getText().toString());
            }
            if (passwordEditDialogView instanceof UsernameSelectionConfirmationView) {
                UsernameSelectionConfirmationView usernameSelectionConfirmationView = (UsernameSelectionConfirmationView) passwordEditDialogView;
                List list2 = (List) propertyModel.j(lVar);
                int iH = propertyModel.h(pVar);
                usernameSelectionConfirmationView.getClass();
                ArrayAdapter arrayAdapter = new ArrayAdapter(usernameSelectionConfirmationView.getContext(), R.layout.simple_spinner_item);
                arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                arrayAdapter.addAll(list2);
                usernameSelectionConfirmationView.f.setAdapter((SpinnerAdapter) arrayAdapter);
                usernameSelectionConfirmationView.f.setSelection(iH);
                return;
            }
            return;
        }
        PropertyModel.r<String> rVar = fx1.b;
        if (v62Var == rVar) {
            PasswordEditDialogWithDetailsView passwordEditDialogWithDetailsView2 = (PasswordEditDialogWithDetailsView) passwordEditDialogView;
            String str = (String) propertyModel.j(rVar);
            if (passwordEditDialogWithDetailsView2.f.getText().toString().equals(str)) {
                return;
            }
            passwordEditDialogWithDetailsView2.f.setText(str);
            passwordEditDialogWithDetailsView2.d(str);
            return;
        }
        if (v62Var == pVar) {
            return;
        }
        PropertyModel.l<Callback<String>> lVar2 = fx1.d;
        if (v62Var == lVar2) {
            ((PasswordEditDialogWithDetailsView) passwordEditDialogView).j = (Callback) propertyModel.j(lVar2);
            return;
        }
        PropertyModel.l<Callback<Integer>> lVar3 = fx1.e;
        if (v62Var == lVar3) {
            ((UsernameSelectionConfirmationView) passwordEditDialogView).g = (Callback) propertyModel.j(lVar3);
            return;
        }
        PropertyModel.r<String> rVar2 = fx1.f;
        if (v62Var == rVar2) {
            passwordEditDialogView.a((String) propertyModel.j(rVar2));
            return;
        }
        PropertyModel.l<Callback<String>> lVar4 = fx1.h;
        if (v62Var == lVar4) {
            passwordEditDialogView.b((Callback) propertyModel.j(lVar4));
            return;
        }
        PropertyModel.r<String> rVar3 = fx1.i;
        if (v62Var == rVar3) {
            passwordEditDialogView.c((String) propertyModel.j(rVar3));
            return;
        }
        PropertyModel.l<String> lVar5 = fx1.g;
        if (v62Var == lVar5) {
            String str2 = (String) propertyModel.j(lVar5);
            passwordEditDialogView.e.setVisibility(!TextUtils.isEmpty(str2) ? 0 : 8);
            passwordEditDialogView.e.setText(str2);
        }
    }
}
