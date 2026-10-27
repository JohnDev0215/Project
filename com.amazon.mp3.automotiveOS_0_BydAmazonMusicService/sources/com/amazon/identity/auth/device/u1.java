package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.ActivityManager;
import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CustomerAttributeKeys;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.t9;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class u1 implements t1 {
    public static final String h = "com.amazon.identity.auth.device.u1";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u7 f598a;
    public final t5 b;
    public final s1 c;
    public final q1 d;
    public final c8 e;
    public final f f;
    public final v1 g;

    /* JADX INFO: compiled from: DCP */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f599a;
        public final /* synthetic */ String b;
        public final /* synthetic */ s2 c;
        public final /* synthetic */ EnumSet d;
        public final /* synthetic */ y5 e;

        public a(String str, String str2, s2 s2Var, EnumSet enumSet, y5 y5Var) {
            this.f599a = str;
            this.b = str2;
            this.c = s2Var;
            this.d = enumSet;
            this.e = y5Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            MAPApplicationInformationQueryer mAPApplicationInformationQueryerA;
            fa faVarA = fa.a(this.f599a);
            MAPApplicationInformationQueryer.a(u1.this.b).e(faVarA.b);
            try {
                try {
                    if (qe.m(u1.this.b)) {
                        t5 t5Var = u1.this.b;
                    }
                    if (e.a(faVarA)) {
                        u1.a(u1.this, this.c, this.b, faVarA.c, this.d, this.e);
                    } else if (e.b(faVarA)) {
                        u1.a(u1.this, this.c, this.b, faVarA, this.d, this.e);
                    } else if ("com.amazon.dcp.sso.token.cookie.xmainAndXabcCookies".equals(faVarA.c)) {
                        Log.w(ga.a(u1.h), "KEY_XMAIN_AND_XACB_COOKIES is deprecated! Please use TokenManagement.getCookies API to get auth cookies for your use case.");
                        u1.b(u1.this, this.c, this.b, new fa(null, faVarA.c), this.d, this.e);
                    } else if ("com.amazon.identity.cookies.xfsn".equals(faVarA.c)) {
                        u1 u1Var = u1.this;
                        a9.a((Callback) this.c, u1Var.e.c(this.b, faVarA.f315a), false);
                    } else {
                        this.c.onError(u1.this.b(faVarA.f315a));
                    }
                    mAPApplicationInformationQueryerA = MAPApplicationInformationQueryer.a(u1.this.b);
                } catch (AuthenticatedURLConnection.AccountNeedsRecoveryException e) {
                    Bundle accountRecoverContextBundle = e.getAccountRecoverContextBundle();
                    Bundle bundleD = u1.this.d(this.f599a);
                    if (accountRecoverContextBundle != null) {
                        Log.e(ga.a(u1.h), "Database corrupted, need recover account.");
                        bundleD.putAll(d7.a(accountRecoverContextBundle).c());
                    }
                    this.c.onError(bundleD);
                    mAPApplicationInformationQueryerA = MAPApplicationInformationQueryer.a(u1.this.b);
                }
                mAPApplicationInformationQueryerA.g(faVarA.b);
            } catch (Throwable th) {
                MAPApplicationInformationQueryer.a(u1.this.b).g(faVarA.b);
                throw th;
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f600a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;
        public final /* synthetic */ s2 d;

        public b(String str, String str2, String str3, s2 s2Var) {
            this.f600a = str;
            this.b = str2;
            this.c = str3;
            this.d = s2Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            fa faVarA = fa.a(this.f600a);
            Log.i(ga.a(u1.h), String.format("Setting the attribute for key: %s", faVarA.f315a));
            u1.this.e.d(this.b, faVarA.f315a, this.c);
            u1.this.a(this.d, this.c, (String) null);
        }
    }

    public u1(t5 t5Var, u7 u7Var) {
        this.b = t5Var;
        this.f598a = u7Var;
        this.d = new q1(t5Var);
        this.c = s1.a(t5Var);
        this.e = new c8(t5Var, new BackwardsCompatiableDataStorage(t5Var));
        this.f = e.b(t5Var);
        this.g = new v1(t5Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(com.amazon.identity.auth.device.u1 r20, com.amazon.identity.auth.device.api.Callback r21, java.lang.String r22, java.lang.String r23, java.util.EnumSet r24, com.amazon.identity.auth.device.y5 r25) throws com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException {
        /*
            Method dump skipped, instruction units count: 557
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.u1.a(com.amazon.identity.auth.device.u1, com.amazon.identity.auth.device.api.Callback, java.lang.String, java.lang.String, java.util.EnumSet, com.amazon.identity.auth.device.y5):void");
    }

    public static void b(u1 u1Var, Callback callback, String str, fa faVar, EnumSet enumSet, y5 y5Var) throws AuthenticatedURLConnection.AccountNeedsRecoveryException {
        String strD = u1Var.e.d(str, faVar.f315a);
        if (strD == null || enumSet.contains(CustomerAttributeStore.GetAttributeOptions.ForceRefresh)) {
            if (!u1Var.b(str, faVar, y5Var)) {
                callback.onError(u1Var.d(faVar.c));
                return;
            }
            strD = u1Var.e.d(str, faVar.f315a);
        }
        u1Var.a(callback, strD, (String) null);
    }

    public final String c(String str) {
        try {
            String str2 = h;
            Log.i(ga.a(str2), "Using backwards compatabile way to get device email");
            Account accountA = r9.a(this.b, str);
            if (accountA != null) {
                return n8.a(this.b).a(accountA).a(j1.c("com.amazon.kindle"));
            }
            ga.a(str2);
            return null;
        } catch (AuthenticatorException e) {
            Log.e(ga.a(h), "AuthenticatorException: ", e);
            return null;
        } catch (OperationCanceledException e2) {
            Log.e(ga.a(h), "OperationCanceledException: ", e2);
            return null;
        } catch (IOException e3) {
            Log.e(ga.a(h), "IOException: ", e3);
            return null;
        } catch (IllegalArgumentException e4) {
            Log.e(ga.a(h), "IllegalArgumentException:", e4);
            return null;
        }
    }

    public final Bundle d(String str) {
        String str2 = String.format("Unable to retrieve attribute %s", str);
        Log.w(ga.a(h), str2);
        return e.a(MAPError.AttributeError.GET_ATTRIBUTE_FAILED, str2, 4, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean b(java.lang.String r8, com.amazon.identity.auth.device.fa r9, com.amazon.identity.auth.device.y5 r10) throws com.amazon.identity.auth.device.api.AuthenticatedURLConnection.AccountNeedsRecoveryException {
        /*
            r7 = this;
            com.amazon.identity.auth.device.t5 r0 = r7.b
            boolean r0 = com.amazon.identity.auth.device.qe.i(r0)
            if (r0 == 0) goto L91
            com.amazon.identity.auth.device.v1 r9 = r7.g
            r9.getClass()
            r10 = 0
            r0 = 1
            r1 = 0
            java.net.URL r2 = r9.a()     // Catch: java.io.IOException -> L53
            if (r2 != 0) goto L17
            goto L5f
        L17:
            com.amazon.identity.auth.device.api.AuthenticationMethodFactory r3 = new com.amazon.identity.auth.device.api.AuthenticationMethodFactory     // Catch: java.io.IOException -> L53
            com.amazon.identity.auth.device.t5 r4 = r9.f623a     // Catch: java.io.IOException -> L53
            r3.<init>(r4, r8)     // Catch: java.io.IOException -> L53
            com.amazon.identity.auth.device.api.AuthenticationType r4 = com.amazon.identity.auth.device.api.AuthenticationType.ADPAuthenticator     // Catch: java.io.IOException -> L53
            com.amazon.identity.auth.device.api.AuthenticationMethod r3 = r3.newAuthenticationMethod(r4)     // Catch: java.io.IOException -> L53
            java.net.HttpURLConnection r2 = com.amazon.identity.auth.device.api.AuthenticatedURLConnection.openConnection(r2, r3)     // Catch: java.io.IOException -> L53
            java.net.URLConnection r2 = com.amazon.identity.auth.device.ib.a(r2)     // Catch: java.io.IOException -> L53
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch: java.io.IOException -> L53
            java.lang.String r3 = "GET"
            r2.setRequestMethod(r3)     // Catch: java.io.IOException -> L53
            int r3 = com.amazon.identity.auth.device.framework.RetryLogic.b(r2)     // Catch: java.io.IOException -> L53
            java.lang.String r4 = com.amazon.identity.auth.device.v1.d     // Catch: java.io.IOException -> L53
            java.lang.String r5 = "Received Response %d from Firs Proxy getStoreCredentials"
            java.lang.Object[] r6 = new java.lang.Object[r0]     // Catch: java.io.IOException -> L53
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: java.io.IOException -> L53
            r6[r10] = r3     // Catch: java.io.IOException -> L53
            java.lang.String r3 = java.lang.String.format(r5, r6)     // Catch: java.io.IOException -> L53
            java.lang.String r4 = com.amazon.identity.auth.device.ga.a(r4)     // Catch: java.io.IOException -> L53
            android.util.Log.i(r4, r3)     // Catch: java.io.IOException -> L53
            com.amazon.identity.auth.device.t7 r2 = r9.a(r2)     // Catch: java.io.IOException -> L53
            goto L60
        L53:
            r2 = move-exception
            java.lang.String r3 = com.amazon.identity.auth.device.v1.d
            java.lang.String r3 = com.amazon.identity.auth.device.ga.a(r3)
            java.lang.String r4 = "Could not get cookies because we could not reach get Store Cookies endpoint."
            android.util.Log.e(r3, r4, r2)
        L5f:
            r2 = r1
        L60:
            if (r2 != 0) goto L6e
            java.lang.String r8 = com.amazon.identity.auth.device.v1.d
            java.lang.String r8 = com.amazon.identity.auth.device.ga.a(r8)
            java.lang.String r9 = "Cannot update store credential cookies"
            android.util.Log.e(r8, r9)
            goto L90
        L6e:
            com.amazon.identity.auth.device.o7 r10 = new com.amazon.identity.auth.device.o7
            r10.<init>(r8, r1, r1, r1)
            r2.a()
            java.lang.String r8 = r2.c
            java.util.Map<java.lang.String, java.lang.String> r1 = r10.b
            java.lang.String r3 = "com.amazon.dcp.sso.token.cookie.xmain"
            r1.put(r3, r8)
            java.lang.String r8 = r2.b()
            java.util.Map<java.lang.String, java.lang.String> r1 = r10.b
            java.lang.String r2 = "com.amazon.dcp.sso.token.cookie.xmainAndXabcCookies"
            r1.put(r2, r8)
            com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage r8 = r9.c
            r8.a(r10)
            r10 = r0
        L90:
            return r10
        L91:
            boolean r8 = r7.a(r8, r9, r10)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.u1.b(java.lang.String, com.amazon.identity.auth.device.fa, com.amazon.identity.auth.device.y5):boolean");
    }

    public final Bundle b(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("value_key", str);
        bundle.putString(CustomerAttributeStore.KEY_DEFAULT_VALUE, str2);
        return bundle;
    }

    public final Bundle b(String str) {
        String str2 = String.format("The attribute %s is not currently supported", str);
        Log.e(ga.a(h), str2);
        return e.a(MAPError.AttributeError.KEY_NOT_FOUND, str2, 2, str2);
    }

    public static void a(u1 u1Var, Callback callback, String str, fa faVar, EnumSet enumSet, y5 y5Var) throws AuthenticatedURLConnection.AccountNeedsRecoveryException {
        u1Var.getClass();
        if (enumSet.contains(CustomerAttributeStore.GetAttributeOptions.ForceRefresh) && !u1Var.a(str, faVar, y5Var)) {
            callback.onError(e.a(MAPError.CommonError.INTERNAL_ERROR, "Was unable to successfully refresh the credentials on a platform that supports it", 1, "Was unable to successfully refresh the credentials on a platform that supports it"));
            return;
        }
        String strD = u1Var.e.d(str, faVar.f315a);
        if (strD == null && !qe.j(u1Var.b)) {
            t5 t5Var = u1Var.b;
            strD = (TextUtils.equals(z9.c(t5Var, faVar.b), z9.c(t5Var, "com.amazon.kindle")) && "com.amazon.dcp.sso.property.deviceemail".equals(faVar.c)) ? u1Var.c(str) : null;
        }
        u1Var.a(callback, strD, (String) null);
    }

    @Override // com.amazon.identity.auth.device.t1
    public Bundle a(String str, String str2) {
        String strD;
        fa faVarA = fa.a(str2);
        if (e.a(faVarA)) {
            String str3 = faVarA.c;
            if (CustomerAttributeKeys.KEY_COR.equals(str3)) {
                return b(this.f598a.d(str, AccountConstants.KEY_COR), this.d.a());
            }
            if (CustomerAttributeKeys.KEY_PFM.equals(str3)) {
                return b(this.f598a.d(str, AccountConstants.KEY_PFM), this.d.b());
            }
            throw new IllegalStateException(String.format("Key %s not recognized as COR/PFM value", str3));
        }
        if (e.b(faVarA)) {
            return a(str, faVarA);
        }
        if ("com.amazon.dcp.sso.token.cookie.xmainAndXabcCookies".equals(faVarA.c)) {
            Log.w(ga.a(h), "KEY_XMAIN_AND_XACB_COOKIES is deprecated! Please use TokenManagement.peekCookies API to get auth cookies for your use case.");
            String strA = e.a((String) null, faVarA.c);
            c8 c8Var = this.e;
            String strA2 = c8Var.a(str, strA, false);
            if (strA2 == null) {
                Log.w(ga.a(c8.d), "peekUserData failed because key does not make sense on the platform");
                strD = null;
            } else {
                strD = c8Var.b.d(str, strA2);
            }
            return b(strD, null);
        }
        if ("com.amazon.identity.cookies.xfsn".equals(faVarA.c)) {
            return b(this.e.f(str, faVarA.f315a), null);
        }
        return b(str2);
    }

    @Override // com.amazon.identity.auth.device.t1
    public MAPFuture<Bundle> a(String str, String str2, Callback callback, Bundle bundle, EnumSet<CustomerAttributeStore.GetAttributeOptions> enumSet, y5 y5Var) {
        s2 s2Var = new s2(callback);
        if (enumSet.contains(CustomerAttributeStore.GetAttributeOptions.ForceRefresh)) {
            ga.d(h, "Packages are force refreshing key %s", str2);
        }
        va.f637a.execute(new a(str2, str, s2Var, enumSet, y5Var));
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.t1
    public MAPFuture<Bundle> a(String str, String str2, String str3, Callback callback) {
        s2 s2Var = new s2(callback);
        if (!a(str2)) {
            String str4 = String.format("Not authorized to setAttribute for key: %s.", str2);
            Log.e(ga.a(h), str4);
            e.a(callback, MAPError.AttributeError.SET_ATTRIBUTE_FAILED, str4, 5, str4);
            return s2Var;
        }
        va.f637a.execute(new b(str2, str, str3, s2Var));
        return s2Var;
    }

    public final boolean a(String str) {
        ActivityManager.RunningAppProcessInfo next;
        String[] strArr;
        boolean z;
        fa faVarA = fa.a(str);
        if (!faVarA.c.startsWith("com.amazon.dcp.sso.property.account.extratokens.")) {
            Log.e(ga.a(h), String.format("The key: %s does not have a valid prefix.", str));
            return false;
        }
        if (faVarA.b == null) {
            return true;
        }
        t5 t5Var = this.b;
        int callingPid = Binder.getCallingPid();
        ActivityManager activityManager = (ActivityManager) t5Var.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
        if (activityManager.getRunningAppProcesses() != null) {
            Iterator<ActivityManager.RunningAppProcessInfo> it = activityManager.getRunningAppProcesses().iterator();
            while (it.hasNext()) {
                next = it.next();
                if (next.pid == callingPid) {
                    break;
                }
            }
            next = null;
        } else {
            next = null;
        }
        if (next == null) {
            Log.e(ga.a("SecurityHelpers"), "Calling process could not be found. Cannot find it's package");
            strArr = new String[0];
        } else {
            strArr = next.pkgList;
        }
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = false;
                break;
            }
            if (TextUtils.equals(strArr[i], faVarA.b)) {
                z = true;
                break;
            }
            i++;
        }
        if (!z) {
            Log.e(ga.a(h), String.format("Cannot set the key: %s in the calling package.", str));
        }
        return z;
    }

    public final Bundle a(String str, fa faVar) {
        String strD;
        c8 c8Var = this.e;
        String strA = c8Var.a(str, faVar.f315a, false);
        if (strA == null) {
            Log.w(ga.a(c8.d), "peekUserData failed because key does not make sense on the platform");
            strD = null;
        } else {
            strD = c8Var.b.d(str, strA);
        }
        return b(strD, null);
    }

    public final void a(Callback callback, String str, String str2) {
        Log.i(ga.a(h), String.format("Callback with value empty: %b", Boolean.valueOf(TextUtils.isEmpty(str))));
        callback.onSuccess(b(str, str2));
    }

    public final boolean a(String str, fa faVar, y5 y5Var) throws AuthenticatedURLConnection.AccountNeedsRecoveryException {
        t9.a aVarB;
        Integer numA;
        t5 t5Var = this.b;
        if (qe.g == null) {
            synchronized (qe.n) {
                if (qe.g == null) {
                    qe.g = Boolean.valueOf(!qe.f(t5Var) || ((aVarB = t9.b(t5Var)) != null && (!"com.amazon.dcp".equals(aVarB.f583a) || ((numA = za.a(t5Var, "com.amazon.dcp")) != null && 1570 <= numA.intValue()))));
                }
            }
        }
        if (!qe.g.booleanValue() && z9.d(this.b, faVar.b)) {
            Log.i(ga.a(h), String.format("Forcing a refresh of attribute %s is not supported on the platform before Otter_Congo.", faVar.f315a));
            return true;
        }
        Log.i(ga.a(h), String.format("Forcing a refresh of attribute %s", faVar.f315a));
        try {
            return this.f.a(str, faVar, (Bundle) null, (Callback) null, y5Var).get() != null;
        } catch (MAPCallbackErrorException e) {
            d7 d7VarA = d7.a(e);
            if (d7VarA == null) {
                Log.e(ga.a(h), "Got MAPCallbackErrorException while trying to update credentials. Error Bundle: " + e.c(e.getErrorBundle()), e);
                return false;
            }
            Log.e(ga.a(h), "Got MAPCallbackErrorException while trying to update credentials. Recover bundle thrown");
            throw new AuthenticatedURLConnection.AccountNeedsRecoveryException("Error happened when try to get authentication bundle", d7VarA.b());
        } catch (InterruptedException e2) {
            Log.e(ga.a(h), "Got InterruptedException while trying to update credentials", e2);
            return false;
        } catch (ExecutionException e3) {
            Log.e(ga.a(h), "Got ExecutionException while trying to update credentials", e3);
            return false;
        }
    }
}
