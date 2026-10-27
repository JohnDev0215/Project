package org.chromium.chrome.browser.ui.signin;

import defpackage.d92;
import defpackage.di1;
import defpackage.ke0;
import defpackage.x10;
import defpackage.z7;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.ui.signin.h;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ConfirmSyncDataStateMachineDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i implements di1.a {
    public final /* synthetic */ h.c e;

    public i(h.c cVar) {
        this.e = cVar;
    }

    @Override // di1.a
    public final void b(int i) {
        if (i == 5 || i == 2) {
            ((e) this.e.a).a.a(false);
        }
    }

    @Override // di1.a
    public final void c(int i, PropertyModel propertyModel) {
        h.c cVar = this.e;
        if (i != 0) {
            if (i == 1) {
                cVar.c.b(2, cVar.b);
                return;
            }
            return;
        }
        f fVar = ((e) cVar.a).a;
        fVar.getClass();
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        z7.D(profileD).a(fVar.d, new x10(14, fVar));
        if (fVar.i == null) {
            fVar.i = new d92(18, fVar);
        }
        fVar.f.postDelayed(fVar.i, 30000L);
        ke0 ke0Var = new ke0(27, fVar);
        h hVar = fVar.e;
        hVar.a();
        hVar.c = new h.a(hVar.b, hVar.a, ke0Var);
        cVar.c.b(1, cVar.b);
    }
}
