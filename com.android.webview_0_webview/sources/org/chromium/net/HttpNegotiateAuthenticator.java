package org.chromium.net;

import J.N;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import defpackage.AK;
import defpackage.DK;
import defpackage.Is;
import defpackage.Ts;
import defpackage.Z7;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class HttpNegotiateAuthenticator {
    public Bundle a;
    public final String b;

    public HttpNegotiateAuthenticator(String str) {
        this.b = str;
    }

    public static HttpNegotiateAuthenticator create(String str) {
        return new HttpNegotiateAuthenticator(str);
    }

    public boolean a(Context context, String str, boolean z) {
        return (!z || Build.VERSION.SDK_INT < 23) && context.checkPermission(str, Process.myPid(), Process.myUid()) != 0;
    }

    public void getNextAuthToken(long j, String str, String str2, boolean z) {
        Context context = Is.a;
        DK dk = new DK();
        dk.d = Z7.c("SPNEGO:HOSTBASED:", str);
        dk.b = AccountManager.get(context);
        dk.a = j;
        String[] strArr = {"SPNEGO"};
        Bundle bundle = new Bundle();
        dk.c = bundle;
        if (str2 != null) {
            bundle.putString("incomingAuthToken", str2);
        }
        Bundle bundle2 = this.a;
        if (bundle2 != null) {
            dk.c.putBundle("spnegoContext", bundle2);
        }
        dk.c.putBoolean("canDelegate", z);
        ApplicationStatus.a();
        if (!a(context, "android.permission.GET_ACCOUNTS", true)) {
            dk.b.getAccountsByTypeAndFeatures(this.b, strArr, new AK(this, dk), new Handler(ThreadUtils.d()));
        } else {
            Ts.a("net_auth", "ERR_MISCONFIGURED_AUTH_ENVIRONMENT: GET_ACCOUNTS permission not granted. Aborting authentication.", new Object[0]);
            N.M0s8NeYn(dk.a, this, -343, null);
        }
    }
}
