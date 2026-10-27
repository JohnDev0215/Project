package org.chromium.chrome.browser.ui.signin;

import defpackage.di1;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ConfirmManagedSyncDataDialogCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements di1.a {
    public final /* synthetic */ d e;

    public c(d dVar) {
        this.e = dVar;
    }

    @Override // di1.a
    public final void b(int i) {
        if (i == 5 || i == 2) {
            ((f) this.e.a).a(false);
        }
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        d dVar = this.e;
        if (i == 0) {
            ((f) dVar.a).c();
            dVar.c.b(1, dVar.b);
        } else if (i == 1) {
            dVar.c.b(2, dVar.b);
        }
    }
}
