package org.chromium.chrome.browser.ui.signin;

import defpackage.di1;
import defpackage.ke0;
import org.chromium.chrome.browser.ui.signin.h;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ConfirmSyncDataStateMachineDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g implements di1.a {
    public final /* synthetic */ h.a e;

    public g(h.a aVar) {
        this.e = aVar;
    }

    @Override // di1.a
    public final void b(int i) {
        if (i == 5 || i == 2) {
            ((f) ((ke0) this.e.a).f).a(false);
        }
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        if (i == 1) {
            h.a aVar = this.e;
            aVar.c.b(2, aVar.b);
        }
    }
}
