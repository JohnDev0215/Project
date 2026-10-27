package org.chromium.components.signin.identitymanager;

import J.N;
import android.accounts.Account;
import defpackage.C0210t;
import org.chromium.components.signin.d;
import org.chromium.components.signin.identitymanager.ProfileOAuth2TokenServiceDelegate;

/* JADX INFO: compiled from: ProfileOAuth2TokenServiceDelegate.java */
/* JADX INFO: loaded from: classes3.dex */
public final class d implements d.a<C0210t> {
    public final /* synthetic */ ProfileOAuth2TokenServiceDelegate.a e;
    public final /* synthetic */ ProfileOAuth2TokenServiceDelegate f;

    public d(ProfileOAuth2TokenServiceDelegate profileOAuth2TokenServiceDelegate, Account account, String str, c cVar) {
        this.f = profileOAuth2TokenServiceDelegate;
        this.e = cVar;
    }

    @Override // org.chromium.components.signin.d.a
    public final void a(C0210t c0210t) {
        c cVar = (c) this.e;
        cVar.getClass();
        c0210t.getClass();
        N.MTN9MD0o(null, 0L, false, cVar.a);
    }

    @Override // org.chromium.components.signin.d.a
    public final void b(boolean z) {
        N.MTN9MD0o(null, 0L, z, ((c) this.e).a);
    }

    @Override // org.chromium.components.signin.d.a
    public final C0210t run() {
        this.f.c.e();
        return null;
    }
}
