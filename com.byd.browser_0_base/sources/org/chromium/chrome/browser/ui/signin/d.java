package org.chromium.chrome.browser.ui.signin;

import android.content.Context;
import defpackage.bi1;
import defpackage.di1;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ConfirmManagedSyncDataDialogCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d {
    public final a a;
    public final PropertyModel b;
    public final bi1 c;

    /* JADX INFO: compiled from: ConfirmManagedSyncDataDialogCoordinator.java */
    public interface a {
    }

    public d(Context context, bi1 bi1Var, a aVar, String str) {
        this.a = aVar;
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.c, context.getString(R$string.sign_in_managed_account));
        bVar.e(di1.f, context.getString(R$string.sign_in_managed_account_description, str));
        bVar.b(di1.q, true);
        bVar.e(di1.j, context.getString(R$string.policy_dialog_proceed));
        bVar.e(di1.m, context.getString(R$string.cancel));
        bVar.e(di1.a, new c(this));
        PropertyModel propertyModelA = bVar.a();
        this.b = propertyModelA;
        this.c = bi1Var;
        bi1Var.j(propertyModelA, 1, false);
    }
}
