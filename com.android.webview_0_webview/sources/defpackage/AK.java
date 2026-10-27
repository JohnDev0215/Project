package defpackage;

import J.N;
import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.os.Bundle;
import android.os.Handler;
import java.io.IOException;
import org.chromium.base.ThreadUtils;
import org.chromium.net.HttpNegotiateAuthenticator;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AK implements AccountManagerCallback {
    public final DK a;
    public final /* synthetic */ HttpNegotiateAuthenticator b;

    public AK(HttpNegotiateAuthenticator httpNegotiateAuthenticator, DK dk) {
        this.b = httpNegotiateAuthenticator;
        this.a = dk;
    }

    @Override // android.accounts.AccountManagerCallback
    public void run(AccountManagerFuture accountManagerFuture) {
        try {
            Account[] accountArr = (Account[]) accountManagerFuture.getResult();
            if (accountArr.length == 0) {
                Ts.f("net_auth", "ERR_MISSING_AUTH_CREDENTIALS: No account provided for the kerberos authentication. Please verify the configuration policies and that the CONTACTS runtime permission is granted. ", new Object[0]);
                N.M0s8NeYn(this.a.a, this.b, -341, null);
                return;
            }
            if (accountArr.length > 1) {
                Ts.f("net_auth", "ERR_MISSING_AUTH_CREDENTIALS: Found %d accounts eligible for the kerberos authentication. Please fix the configuration by providing a single account.", Integer.valueOf(accountArr.length));
                N.M0s8NeYn(this.a.a, this.b, -341, null);
            } else if (this.b.a(Is.a, "android.permission.USE_CREDENTIALS", true)) {
                Ts.a("net_auth", "ERR_MISCONFIGURED_AUTH_ENVIRONMENT: USE_CREDENTIALS permission not granted. Aborting authentication.", new Object[0]);
                N.M0s8NeYn(this.a.a, this.b, -343, null);
            } else {
                DK dk = this.a;
                Account account = accountArr[0];
                dk.e = account;
                dk.b.getAuthToken(account, dk.d, dk.c, true, (AccountManagerCallback<Bundle>) new CK(this.b, dk), new Handler(ThreadUtils.d()));
            }
        } catch (AuthenticatorException | OperationCanceledException | IOException e) {
            Ts.f("net_auth", "ERR_UNEXPECTED: Error while attempting to retrieve accounts.", e);
            N.M0s8NeYn(this.a.a, this.b, -9, null);
        }
    }
}
