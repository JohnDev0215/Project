package org.chromium.chrome.browser.signin.services;

import android.accounts.Account;
import defpackage.im2;
import defpackage.x10;
import org.chromium.components.signin.identitymanager.IdentityManager;

/* JADX INFO: loaded from: classes2.dex */
public interface SigninManager {

    public interface a {
        void a();

        void b();
    }

    public interface c {
    }

    void a(String str, x10 x10Var);

    boolean b();

    void c(Account account, a aVar);

    void d(int i, Account account, a aVar);

    boolean e();

    String f();

    void g(Runnable runnable);

    void h(int i, im2 im2Var);

    void i(b bVar);

    void j();

    IdentityManager l();

    void n(b bVar);

    void o();

    String p(String str);

    void q(Runnable runnable);

    public interface b {
        default void c() {
        }

        default void j() {
        }

        default void w() {
        }
    }
}
