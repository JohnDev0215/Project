package org.chromium.chrome.browser.ui.signin;

import defpackage.di1;
import defpackage.n3;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ConfirmImportSyncDataDialogCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a implements di1.a {
    public final /* synthetic */ b e;

    public a(b bVar) {
        this.e = bVar;
    }

    @Override // di1.a
    public final void b(int i) {
        if (i == 5 || i == 2) {
            n3.X0("Signin_ImportDataPrompt_Cancel");
            ((f) this.e.a).a(false);
        }
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        b bVar = this.e;
        if (i != 0) {
            if (i == 1) {
                bVar.d.b(2, bVar.c);
            }
        } else {
            n3.X0(bVar.b.b() ? "Signin_ImportDataPrompt_DontImport" : "Signin_ImportDataPrompt_ImportData");
            f fVar = (f) bVar.a;
            fVar.g = bVar.b.b();
            fVar.c();
            bVar.d.b(1, bVar.c);
        }
    }
}
