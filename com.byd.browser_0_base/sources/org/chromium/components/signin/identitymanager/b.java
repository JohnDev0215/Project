package org.chromium.components.signin.identitymanager;

import J.N;
import defpackage.h62;
import defpackage.s62;
import defpackage.w70;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.b;
import org.chromium.components.signin.base.AccountInfo;
import org.chromium.components.signin.base.CoreAccountInfo;
import org.chromium.components.signin.identitymanager.AccountTrackerService;
import org.chromium.components.signin.identitymanager.IdentityManager;
import org.chromium.components.signin.identitymanager.a;

/* JADX INFO: compiled from: AccountInfoServiceImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b implements IdentityManager.a, AccountTrackerService.a, a {
    public final IdentityManager e;
    public final AccountTrackerService f;
    public final org.chromium.base.b<a.InterfaceC0143a> g = new org.chromium.base.b<>();

    public b(IdentityManager identityManager, AccountTrackerService accountTrackerService) {
        this.e = identityManager;
        this.f = accountTrackerService;
        identityManager.b.a(this);
        accountTrackerService.h.a(this);
    }

    @Override // org.chromium.components.signin.identitymanager.a
    public final s62<AccountInfo> a(String str) {
        s62<AccountInfo> s62Var = new s62<>();
        this.f.c(new w70(this, 5, s62Var, str));
        return s62Var;
    }

    @Override // org.chromium.components.signin.identitymanager.a
    public final void b(h62 h62Var) {
        this.g.c(h62Var);
    }

    @Override // org.chromium.components.signin.identitymanager.AccountTrackerService.a
    public final void c(List list) {
        IdentityManager identityManager = this.e;
        identityManager.getClass();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            N.MxkwHIGI(identityManager.a, ((CoreAccountInfo) it.next()).getId());
        }
    }

    @Override // org.chromium.components.signin.identitymanager.a
    public final void d(h62 h62Var) {
        this.g.a(h62Var);
    }

    @Override // org.chromium.components.signin.identitymanager.a
    public final void destroy() {
        this.f.h.c(this);
        this.e.c(this);
    }

    @Override // org.chromium.components.signin.identitymanager.IdentityManager.a
    public final void k(AccountInfo accountInfo) {
        Iterator<a.InterfaceC0143a> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((a.InterfaceC0143a) aVar.next()).a(accountInfo);
            }
        }
    }
}
