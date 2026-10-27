package org.chromium.chrome.browser.password_edit_dialog;

import J.N;
import android.content.res.Resources;
import defpackage.bi1;
import defpackage.bq;
import defpackage.di1;
import defpackage.fx1;
import defpackage.sk;
import java.util.List;
import org.chromium.chrome.browser.password_edit_dialog.b;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: PasswordEditDialogMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements di1.a {
    public PropertyModel e;
    public PropertyModel f;
    public List<String> g;
    public final bi1 h;
    public final Resources i;
    public final b.a j;

    public c(bi1 bi1Var, Resources resources, b.a aVar) {
        this.h = bi1Var;
        this.i = resources;
        this.j = aVar;
    }

    @Override // di1.a
    public final void b(int i) {
        boolean z = i == 1;
        PasswordEditDialogBridge passwordEditDialogBridge = (PasswordEditDialogBridge) this.j;
        N.MOzJiylx(passwordEditDialogBridge.a, z);
        passwordEditDialogBridge.a = 0L;
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        if (i == 0) {
            sk skVar = bq.a;
            boolean zM09VlOh_ = N.M09VlOh_("PasswordEditDialogWithDetails");
            b.a aVar = this.j;
            if (zM09VlOh_) {
                N.MDYn9mHv(((PasswordEditDialogBridge) aVar).a, (String) this.e.j(fx1.b), (String) this.e.j(fx1.f));
            } else {
                N.Mqlx1IGp(((PasswordEditDialogBridge) aVar).a, this.e.h(fx1.c));
            }
        }
        this.h.b(i == 0 ? 1 : 2, propertyModel);
    }
}
