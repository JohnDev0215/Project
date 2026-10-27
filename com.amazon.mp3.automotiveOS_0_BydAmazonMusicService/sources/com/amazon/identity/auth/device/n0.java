package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.dcp.sso.IAmazonAccountAuthenticator;
import com.amazon.dcp.sso.ISubAuthenticator;
import com.amazon.dcp.sso.ISubAuthenticatorResponse;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.api.CustomerAttributeKeys;
import com.amazon.identity.auth.device.features.Feature;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.kcpsdk.auth.RegisterDeviceRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class n0 implements ISubAuthenticator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f442a;
    public final AmazonAccountManager b;
    public final z0 c;
    public final u7 d;
    public final BackwardsCompatiableDataStorage e;
    public final s7 f;
    public final l4 g;
    public final i5 h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final Long m;
    public final boolean n;
    public final a o;
    public final a p;
    public final a q;
    public final a r;
    public final a s;
    public final a t;
    public final a u;
    public final a v;
    public final a w;
    public final a x;
    public final a y;
    public static final String z = j1.c("com.amazon.kindle");
    public static final String A = n0.class.getName();

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f443a;
        public final String b;

        public a(String str, String str2) {
            this.f443a = str;
            this.b = str2;
        }

        public String toString() {
            Object[] objArr = new Object[2];
            String str = this.f443a;
            if (str == null) {
                str = "none";
            }
            objArr[0] = str;
            String str2 = this.b;
            objArr[1] = str2 != null ? str2 : "none";
            return String.format("[%s,%s]", objArr);
        }
    }

    public n0(Context context, String str, String str2, String str3, Long l, boolean z2) {
        t5 t5VarA = t5.a(context);
        this.f442a = t5VarA;
        this.b = (AmazonAccountManager) t5VarA.getSystemService("dcp_amazon_account_man");
        this.c = z0.a(t5VarA);
        this.d = ((v7) t5VarA.getSystemService("dcp_data_storage_factory")).a();
        this.e = new BackwardsCompatiableDataStorage(t5VarA);
        this.g = (l4) t5VarA.getSystemService("sso_webservice_caller_creator");
        this.h = (i5) t5VarA.getSystemService("sso_platform");
        this.i = str2;
        this.j = str3;
        this.k = str;
        this.m = l;
        this.l = z9.a(context, str2, str);
        this.n = z2;
        this.o = new a(j1.b(str), e.a(t5VarA, str2, AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN));
        this.p = new a(j1.f(str), e.a(t5VarA, str2, AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY));
        this.q = new a(j1.e(str), e.a(t5VarA, str2, "com.amazon.dcp.sso.token.devicedevicetype"));
        this.r = new a(j1.d(str), e.a(t5VarA, str2, "com.amazon.dcp.sso.token.device.deviceserialname"));
        this.s = new a(j1.c(str), e.a(t5VarA, str2, "com.amazon.dcp.sso.property.deviceemail"));
        this.t = new a(j1.g(str), e.a(t5VarA, str2, "com.amazon.identity.cookies.xfsn"));
        this.u = new a(j1.k(str), e.a(t5VarA, str2, AccountConstants.TOKEN_TYPE_COOKIE_XMAIN_TOKEN));
        this.v = new a(j1.h(str), e.a(t5VarA, str2, "com.amazon.dcp.sso.property.devicename"));
        this.w = new a(j1.j(str), e.a(t5VarA, str2, "com.amazon.dcp.sso.property.username"));
        this.x = new a(j1.i(str), e.a(t5VarA, str2, "com.amazon.dcp.sso.property.firstname"));
        this.y = new a(j1.a(str), e.a(t5VarA, str2, "com.amazon.dcp.sso.token.device.accountpool"));
        this.f = qe.k(((i5) t5VarA.getSystemService("sso_platform")).f382a) ? new h8(t5VarA) : new b8(t5.a(t5VarA).a());
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(com.amazon.identity.auth.device.n0 r8, com.amazon.dcp.sso.ISubAuthenticatorResponse r9, com.amazon.identity.auth.device.kc r10, java.lang.String r11) throws android.os.RemoteException {
        /*
            r8.getClass()
            r0 = 5
            if (r10 != 0) goto Ld
            java.lang.String r8 = "Could not parse response"
            r9.onError(r0, r8)
            goto Lb5
        Ld:
            com.amazon.identity.auth.device.jc r1 = r10.l
            if (r1 == 0) goto L7c
            r10 = 0
            com.amazon.identity.kcpsdk.auth.RegisterDeviceErrorType r11 = r1.f399a
            int r11 = r11.ordinal()
            if (r11 == 0) goto L6e
            r2 = 1
            if (r11 == r2) goto L65
            r2 = 3
            if (r11 == r2) goto L5c
            r8 = 4
            if (r11 == r8) goto L41
            switch(r11) {
                case 16: goto L41;
                case 17: goto L41;
                case 18: goto L41;
                default: goto L26;
            }
        L26:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r11 = "Invalid Response: "
            r8.append(r11)
            com.amazon.identity.kcpsdk.auth.RegisterDeviceErrorType r11 = r1.f399a
            java.lang.String r11 = r11.getErrorString()
            r8.append(r11)
            java.lang.String r8 = r8.toString()
            r9.onError(r0, r8)
            goto L76
        L41:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r11 = "Unrecognized Response "
            r8.append(r11)
            com.amazon.identity.kcpsdk.auth.RegisterDeviceErrorType r11 = r1.f399a
            java.lang.String r11 = r11.getErrorString()
            r8.append(r11)
            java.lang.String r8 = r8.toString()
            r9.onError(r0, r8)
            goto L76
        L5c:
            r10 = 102(0x66, float:1.43E-43)
            java.lang.String r11 = "Duplicate device name"
            android.os.Bundle r10 = r8.a(r10, r11)
            goto L76
        L65:
            r10 = 101(0x65, float:1.42E-43)
            java.lang.String r11 = "Device already registered to another user."
            android.os.Bundle r10 = r8.a(r10, r11)
            goto L76
        L6e:
            r10 = 100
            java.lang.String r11 = "Invalid username or password"
            android.os.Bundle r10 = r8.a(r10, r11)
        L76:
            if (r10 == 0) goto Lb5
            r9.onResult(r10)
            goto Lb5
        L7c:
            com.amazon.identity.auth.device.t7 r0 = new com.amazon.identity.auth.device.t7
            java.util.List<com.amazon.identity.auth.device.qb> r1 = r10.t
            java.util.List r1 = java.util.Collections.unmodifiableList(r1)
            r0.<init>(r1)
            r0.a()
            java.lang.String r6 = r0.c
            boolean r0 = com.amazon.identity.auth.device.va.a()
            if (r0 == 0) goto La2
            com.amazon.identity.auth.device.k0 r0 = new com.amazon.identity.auth.device.k0
            r2 = r0
            r3 = r8
            r4 = r10
            r5 = r11
            r7 = r9
            r2.<init>(r3, r4, r5, r6, r7)
            java.util.concurrent.Executor r8 = com.amazon.identity.auth.device.va.f637a
            r8.execute(r0)
            goto Lb5
        La2:
            r8.a(r10, r11, r6)
            if (r9 == 0) goto Lb5
            java.lang.String r10 = com.amazon.identity.auth.device.n0.A
            java.lang.String r10 = com.amazon.identity.auth.device.ga.a(r10)
            java.lang.String r11 = "Callback with success after storing tokens for the child app."
            android.util.Log.i(r10, r11)
            r8.a(r9)
        Lb5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.n0.a(com.amazon.identity.auth.device.n0, com.amazon.dcp.sso.ISubAuthenticatorResponse, com.amazon.identity.auth.device.kc, java.lang.String):void");
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        throw new UnsupportedOperationException("asBinder is not supported in DMSSubAuthenticator");
    }

    public final void b(o7 o7Var, a aVar, String str) {
        String str2 = A;
        Objects.toString(aVar);
        ga.a(str2);
        if (str == null) {
            Log.i(ga.a(str2), String.format("Tried to set user data %s to null", aVar));
            return;
        }
        String str3 = aVar.f443a;
        if (str3 != null) {
            o7Var.c.put(str3, str);
        }
        String str4 = aVar.b;
        if (str4 != null) {
            o7Var.b.put(str4, str);
        }
    }

    @Override // com.amazon.dcp.sso.ISubAuthenticator
    public void getAccountRemovalAllowed(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, String str2) {
        String str3 = A;
        Log.w(ga.a(str3), "DMS sub authenticator getAccountRemovalAllowed was called");
        Account account = new Account(str2, str);
        if (qe.k(this.h.f382a)) {
            Log.i(ga.a(str3), "Generating local account removed broadcast.");
            String strA = r9.a(this.f442a, account);
            this.f.a(this.f442a, strA);
            Log.i(ga.a(str3), "Cleared local cookies in pre merge devices");
            x.a(this.f442a, this.c.a(strA), strA, account, this.f442a.getPackageName(), this.c.a(this.f442a, strA), null);
        } else {
            ga.a(str3);
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("booleanResult", true);
        try {
            iSubAuthenticatorResponse.onResult(bundle);
        } catch (RemoteException unused) {
            Log.e(ga.a(A), "RemoteException during getAccountRemovalAllowed in DMS sub authenticator");
        }
    }

    @Override // com.amazon.dcp.sso.ISubAuthenticator
    public void getAuthToken(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, String str2, String str3, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator) {
        y5 y5Var = new y5("DMSSubAuthenticator:GetAuthToken");
        if (!AccountConstants.AMAZON_ACCOUNT_TYPE.equals(str)) {
            Log.e(ga.a(A), "An Attempt to retrieve a token for a non amazon account.");
        } else {
            String strA = r9.a(this.f442a, new Account(str2, str));
            a(new i0(this, iSubAuthenticatorResponse, y5Var, strA, str3), iAmazonAccountAuthenticator != null ? new h1(iAmazonAccountAuthenticator) : new g1(this.f442a), strA, y5Var);
        }
    }

    @Override // com.amazon.dcp.sso.ISubAuthenticator
    public void updateAuthToken(ISubAuthenticatorResponse iSubAuthenticatorResponse, String str, String str2, String str3, Bundle bundle, IAmazonAccountAuthenticator iAmazonAccountAuthenticator) {
        y5 y5Var = new y5("DMSSubAuthenticator:UpdateAuthToken");
        Log.i(ga.a(A), "Updating DMS authentication tokens");
        String strA = r9.a(this.f442a, new Account(str2, str));
        i0 i0Var = new i0(this, iSubAuthenticatorResponse, y5Var, strA, str3);
        vc vcVar = new vc();
        Long l = this.m;
        if (l != null) {
            vcVar.d = new kd(Long.toString(l.longValue()));
        }
        String str4 = this.l;
        if (str4 != null) {
            vcVar.c = str4;
        }
        l0 l0Var = new l0(this, i0Var, strA);
        ld ldVarB = vcVar.b();
        wc wcVar = new wc();
        m0 m0Var = new m0(this, strA);
        new o4(this.g.f418a, ldVarB, new uc(new xc(wcVar), l0Var, ldVarB.j), new tc(m0Var), y5Var).a();
    }

    public final void a(ISubAuthenticatorResponse iSubAuthenticatorResponse) {
        try {
            iSubAuthenticatorResponse.onResult(new Bundle());
        } catch (RemoteException e) {
            Log.e(ga.a(A), "Error Callback Success", e);
        }
    }

    public static void a(Context context, o7 o7Var, String str, Map<String, String> map) {
        if (map != null && !map.isEmpty() && !TextUtils.isEmpty(str)) {
            if (f0.a(context, str)) {
                n0 n0Var = new n0(context, null, str, null, null, true);
                kc kcVar = null;
                if (map.isEmpty()) {
                    Log.e(ga.a(A), "The pre-populated credential map does not have any valid data, ignoring it for device type: " + str);
                } else {
                    String str2 = map.get("adp_token");
                    String str3 = map.get("device_private_key");
                    if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                        String str4 = map.get("store_authentication_cookie");
                        if (TextUtils.isEmpty(str4)) {
                            Log.i(ga.a(A), "The batch registration did not return store auth cookie for device type: " + str);
                        }
                        String strB = map.get("user_device_name");
                        if (TextUtils.isEmpty(strB)) {
                            strB = o7Var.b("com.amazon.dcp.sso.property.devicename");
                            String str5 = A;
                            String.format("Using the device name: %s of central device type for child device type: %s", strB, str);
                            ga.a(str5);
                        }
                        String str6 = strB;
                        String strB2 = map.get("kindle_email_address");
                        if (TextUtils.isEmpty(strB2)) {
                            strB2 = o7Var.b("com.amazon.dcp.sso.property.deviceemail");
                            String str7 = A;
                            String.format("Using the device email: %s of central device type for child device type: %s", strB2, str);
                            ga.a(str7);
                        }
                        String str8 = strB2;
                        String strB3 = map.get("name");
                        if (TextUtils.isEmpty(strB3)) {
                            strB3 = o7Var.b("com.amazon.dcp.sso.property.username");
                            String str9 = A;
                            String.format("Using the username: %s of central device type for child device type: %s", strB3, str);
                            ga.a(str9);
                        }
                        String str10 = strB3;
                        String strB4 = map.get("given_name");
                        if (TextUtils.isEmpty(strB4)) {
                            strB4 = o7Var.b("com.amazon.dcp.sso.property.firstname");
                            String str11 = A;
                            String.format("Using the first name: %s of central device type for child device type: %s", strB4, str);
                            ga.a(str11);
                        }
                        String str12 = strB4;
                        String strB5 = map.get("account_pool");
                        if (TextUtils.isEmpty(strB5)) {
                            strB5 = o7Var.b("com.amazon.dcp.sso.token.device.accountpool");
                            String str13 = A;
                            String.format("Using the account pool: %s of central device type for child device type: %s", strB5, str);
                            ga.a(str13);
                        }
                        kc kcVar2 = new kc(str2, null, null, 0, str6, str3, str10, str12, str8, null, null, null, null);
                        kcVar2.j = str4;
                        kcVar2.n = strB5;
                        kcVar = kcVar2;
                    } else {
                        String str14 = A;
                        Log.e(ga.a(str14), "The pre-populated credential map does not have valid ADP credentials, ignoring it for device type: " + str);
                        Log.e(ga.a(str14), "The pre-populated credential map contains the following  invalid key: " + map.keySet());
                    }
                }
                if (kcVar == null) {
                    return;
                }
                Log.i(ga.a(A), String.format("There are %d pre-populated tokens for child device type: %s", Integer.valueOf(map.size()), str));
                for (String str15 : map.keySet()) {
                    String str16 = A;
                    String.format("Pre-populating the token: %s for child device type: %s", str15, str);
                    ga.a(str16);
                }
                n0Var.a(kcVar, o7Var, o7Var.a(AccountConstants.TOKEN_TYPE_COOKIE_XMAIN_TOKEN), o7Var.b("com.amazon.dcp.sso.token.device.deviceserialname"));
                return;
            }
            return;
        }
        Log.e(ga.a(A), "The input device type or map is null or empty. Ignoring it.");
    }

    public final void a(ISubAuthenticatorResponse iSubAuthenticatorResponse, g0 g0Var, String str, y5 y5Var) {
        j0 j0Var = new j0(this, iSubAuthenticatorResponse, str);
        RegisterDeviceRequest registerDeviceRequest = new RegisterDeviceRequest(this.f442a, new Bundle());
        registerDeviceRequest.t = true;
        registerDeviceRequest.b(this.i);
        registerDeviceRequest.a(a(str));
        if (!TextUtils.isEmpty(this.j) && this.f442a.b().a(Feature.OverrideDeviceAttributes)) {
            registerDeviceRequest.x = true;
        }
        boolean z2 = !this.n ? false : !this.b.d(str);
        if (z2) {
            Log.i(ga.a(A), String.format("Registering secondary account for device type %s", this.i));
        }
        registerDeviceRequest.a(z2, RegisterDeviceRequest.DeviceAccountRole.SECONDARY);
        Long l = this.m;
        if (l != null) {
            kd kdVar = new kd(Long.toString(l.longValue()));
            if (!kdVar.a()) {
                Log.e(ga.a(RegisterDeviceRequest.b0), "setDeviceSoftwareVersion: device software version is invalid. Cannot be set.");
            } else {
                registerDeviceRequest.s = kdVar;
            }
        }
        String str2 = this.l;
        if (str2 != null) {
            registerDeviceRequest.B = str2;
        }
        ld ldVarC = registerDeviceRequest.c();
        if (ldVarC != null) {
            g0Var.a(str, ldVarC, j0Var, y5Var);
        } else {
            Log.e(ga.a(A), "Could not construct a valid child application registration request");
        }
    }

    public final void a(kc kcVar, String str, String str2) {
        String strA = a(str);
        o7 o7Var = new o7(str, new HashMap(), new HashMap(), this.e);
        a(kcVar, o7Var, str2, strA);
        this.e.a(o7Var);
    }

    public final void a(kc kcVar, o7 o7Var, String str, String str2) {
        a(o7Var, this.o, kcVar.f409a);
        a(o7Var, this.p, kcVar.f);
        a(o7Var, this.t, kcVar.j);
        a(o7Var, this.u, str);
        b(o7Var, this.q, this.i);
        b(o7Var, this.r, str2);
        b(o7Var, this.s, kcVar.i);
        b(o7Var, this.v, kcVar.e);
        b(o7Var, this.w, kcVar.g);
        b(o7Var, this.x, kcVar.h);
        b(o7Var, this.y, kcVar.n);
        z3 z3VarB = this.f442a.b();
        String str3 = this.i;
        String str4 = this.k;
        String str5 = this.j;
        Log.i(ga.a(f0.f307a), String.format("%s has registered", str3));
        o7Var.b.put(str3 + ".is_registered_key", "true");
        if (!TextUtils.isEmpty(str5) && z3VarB.a(Feature.OverrideDeviceAttributes)) {
            o7Var.b.put(CustomerAttributeKeys.getDsnKeyForPackage(str4), str5);
            synchronized (e.class) {
                String strA = o7Var.a("overriding_dsn_child_device_types_key");
                HashSet hashSet = new HashSet(Arrays.asList(TextUtils.isEmpty(strA) ? new String[0] : TextUtils.split(strA, ";")));
                hashSet.add(str4);
                o7Var.c.put("overriding_dsn_child_device_types_key", e.a(";", hashSet));
            }
        }
    }

    public final void a(o7 o7Var, a aVar, String str) {
        String str2 = A;
        Objects.toString(aVar);
        ga.a(str2);
        if (str == null) {
            Log.i(ga.a(str2), String.format("Tried to set token %s to null", aVar));
            return;
        }
        String str3 = aVar.f443a;
        if (str3 != null) {
            o7Var.c.put(str3, str);
        }
        String str4 = aVar.b;
        if (str4 != null) {
            o7Var.c.put(str4, str);
        }
    }

    public final Bundle a(int i, String str) {
        Log.e(ga.a(A), "Error gettingAuthToken " + str);
        Bundle bundle = new Bundle();
        bundle.putInt(AccountConstants.KEY_DMS_ERROR_CODE, i);
        bundle.putString(AccountConstants.KEY_DMS_ERROR_MESSAGE, str);
        return bundle;
    }

    public final String a(String str) {
        String str2 = this.j;
        if (str2 != null) {
            ga.a(A, "Using custom override DSN %s for registering of device type %s", str2, this.i);
            return this.j;
        }
        return new BackwardsCompatiableDataStorage(this.f442a, this.d).d(str, "com.amazon.dcp.sso.token.device.deviceserialname");
    }
}
