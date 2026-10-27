package org.chromium.net;

import J.N;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import defpackage.C0175b;
import defpackage.fa1;
import defpackage.xz;
import java.io.IOException;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class HttpNegotiateAuthenticator {
    public Bundle a;
    public final String b;

    public class a implements AccountManagerCallback<Account[]> {
        public final c a;

        public a(c cVar) {
            this.a = cVar;
        }

        @Override // android.accounts.AccountManagerCallback
        public final void run(AccountManagerFuture<Account[]> accountManagerFuture) {
            c cVar = this.a;
            HttpNegotiateAuthenticator httpNegotiateAuthenticator = HttpNegotiateAuthenticator.this;
            try {
                Account[] result = accountManagerFuture.getResult();
                if (result.length == 0) {
                    Log.w("cr_net_auth", "ERR_MISSING_AUTH_CREDENTIALS: No account provided for the kerberos authentication. Please verify the configuration policies and that the CONTACTS runtime permission is granted. ");
                    N.M0s8NeYn(cVar.a, httpNegotiateAuthenticator, -341, null);
                    return;
                }
                if (result.length > 1) {
                    fa1.k("net_auth", "ERR_MISSING_AUTH_CREDENTIALS: Found %d accounts eligible for the kerberos authentication. Please fix the configuration by providing a single account.", Integer.valueOf(result.length));
                    N.M0s8NeYn(cVar.a, httpNegotiateAuthenticator, -341, null);
                    return;
                }
                Context context = xz.a;
                httpNegotiateAuthenticator.getClass();
                if (HttpNegotiateAuthenticator.a(context, "android.permission.USE_CREDENTIALS", true)) {
                    Log.e("cr_net_auth", "ERR_MISCONFIGURED_AUTH_ENVIRONMENT: USE_CREDENTIALS permission not granted. Aborting authentication.");
                    N.M0s8NeYn(cVar.a, httpNegotiateAuthenticator, -343, null);
                } else {
                    Account account = result[0];
                    cVar.e = account;
                    cVar.b.getAuthToken(account, cVar.d, cVar.c, true, (AccountManagerCallback<Bundle>) httpNegotiateAuthenticator.new b(cVar), new Handler(ThreadUtils.d()));
                }
            } catch (AuthenticatorException | OperationCanceledException | IOException e) {
                Log.w("cr_net_auth", "ERR_UNEXPECTED: Error while attempting to retrieve accounts.", e);
                N.M0s8NeYn(cVar.a, httpNegotiateAuthenticator, -9, null);
            }
        }
    }

    public class b implements AccountManagerCallback<Bundle> {
        public final c a;

        public class a extends BroadcastReceiver {
            public final /* synthetic */ Context a;

            public a(Context context) {
                this.a = context;
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                this.a.unregisterReceiver(this);
                b bVar = b.this;
                c cVar = bVar.a;
                cVar.b.getAuthToken(cVar.e, cVar.d, cVar.c, true, (AccountManagerCallback<Bundle>) HttpNegotiateAuthenticator.this.new b(cVar), (Handler) null);
            }
        }

        public b(c cVar) {
            this.a = cVar;
        }

        @Override // android.accounts.AccountManagerCallback
        public final void run(AccountManagerFuture<Bundle> accountManagerFuture) {
            HttpNegotiateAuthenticator httpNegotiateAuthenticator = HttpNegotiateAuthenticator.this;
            c cVar = this.a;
            int i = -9;
            try {
                Bundle result = accountManagerFuture.getResult();
                if (result.containsKey("intent")) {
                    Context context = xz.a;
                    xz.g(context, new a(context), new IntentFilter("android.accounts.LOGIN_ACCOUNTS_CHANGED"));
                    return;
                }
                httpNegotiateAuthenticator.getClass();
                httpNegotiateAuthenticator.a = result.getBundle("spnegoContext");
                int i2 = result.getInt("spnegoResult", 1);
                if (i2 != 0) {
                    switch (i2) {
                        case 2:
                            i = -3;
                            break;
                        case 3:
                            i = -342;
                            break;
                        case 4:
                            i = -320;
                            break;
                        case 5:
                            i = -338;
                            break;
                        case 6:
                            i = -339;
                            break;
                        case 7:
                            i = -341;
                            break;
                        case 8:
                            i = -344;
                            break;
                        case 9:
                            i = -329;
                            break;
                    }
                } else {
                    i = 0;
                }
                N.M0s8NeYn(cVar.a, httpNegotiateAuthenticator, i, result.getString("authtoken"));
            } catch (AuthenticatorException | OperationCanceledException | IOException e) {
                Log.w("cr_net_auth", "ERR_UNEXPECTED: Error while attempting to obtain a token.", e);
                N.M0s8NeYn(cVar.a, httpNegotiateAuthenticator, -9, null);
            }
        }
    }

    public static class c {
        public long a;
        public AccountManager b;
        public Bundle c;
        public String d;
        public Account e;
    }

    public HttpNegotiateAuthenticator(String str) {
        this.b = str;
    }

    public static boolean a(Context context, String str, boolean z) {
        return (z || context.checkPermission(str, Process.myPid(), Process.myUid()) == 0) ? false : true;
    }

    @CalledByNative
    public static HttpNegotiateAuthenticator create(String str) {
        return new HttpNegotiateAuthenticator(str);
    }

    @CalledByNative
    public void getNextAuthToken(long j, String str, String str2, boolean z) {
        Context context = xz.a;
        c cVar = new c();
        cVar.d = C0175b.j("SPNEGO:HOSTBASED:", str);
        cVar.b = AccountManager.get(context);
        cVar.a = j;
        String[] strArr = {"SPNEGO"};
        Bundle bundle = new Bundle();
        cVar.c = bundle;
        if (str2 != null) {
            bundle.putString("incomingAuthToken", str2);
        }
        Bundle bundle2 = this.a;
        if (bundle2 != null) {
            cVar.c.putBundle("spnegoContext", bundle2);
        }
        cVar.c.putBoolean("canDelegate", z);
        Activity activity = ApplicationStatus.c;
        if (activity != null) {
            if (!a(context, "android.permission.GET_ACCOUNTS", false)) {
                cVar.b.getAuthTokenByFeatures(this.b, cVar.d, strArr, activity, null, cVar.c, new b(cVar), new Handler(ThreadUtils.d()));
                return;
            } else {
                fa1.b("net_auth", "ERR_MISCONFIGURED_AUTH_ENVIRONMENT: %s permission not granted. Aborting authentication", "android.permission.GET_ACCOUNTS");
                N.M0s8NeYn(cVar.a, this, -343, null);
                return;
            }
        }
        if (a(context, "android.permission.GET_ACCOUNTS", true)) {
            Log.e("cr_net_auth", "ERR_MISCONFIGURED_AUTH_ENVIRONMENT: GET_ACCOUNTS permission not granted. Aborting authentication.");
            N.M0s8NeYn(cVar.a, this, -343, null);
        } else {
            cVar.b.getAccountsByTypeAndFeatures(this.b, strArr, new a(cVar), new Handler(ThreadUtils.d()));
        }
    }
}
