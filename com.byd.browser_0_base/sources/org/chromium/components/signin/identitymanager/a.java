package org.chromium.components.signin.identitymanager;

import defpackage.h62;
import defpackage.s62;
import org.chromium.components.signin.base.AccountInfo;

/* JADX INFO: compiled from: AccountInfoService.java */
/* JADX INFO: loaded from: classes3.dex */
public interface a {

    /* JADX INFO: renamed from: org.chromium.components.signin.identitymanager.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AccountInfoService.java */
    public interface InterfaceC0143a {
        void a(AccountInfo accountInfo);
    }

    s62<AccountInfo> a(String str);

    void b(h62 h62Var);

    void d(h62 h62Var);

    void destroy();
}
