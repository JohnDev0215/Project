package org.chromium.components.signin;

import android.accounts.Account;
import android.app.Activity;
import defpackage.s62;
import defpackage.z1;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.components.signin.base.AccountCapabilities;
import org.chromium.components.signin.base.CoreAccountInfo;

/* JADX INFO: loaded from: classes3.dex */
public interface AccountManagerFacade {

    public interface a {
        void k(boolean z);
    }

    void a(z1 z1Var);

    s62<List<Account>> b();

    void c();

    s62<AccountCapabilities> d(Account account);

    void e();

    void f(Account account, Activity activity, Callback<Boolean> callback);

    void g(String str);

    void h(Account account, a aVar);

    s62<List<CoreAccountInfo>> i();

    void j(z1 z1Var);

    void k();
}
