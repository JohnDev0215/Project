package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AuthenticatorDescription;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.accounts.InvalidSubAuthenticatorDefinitionException;
import com.amazon.identity.auth.accounts.RegisterChildApplicationAction;
import com.amazon.identity.auth.attributes.CORPFMResponse;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPFuture;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.identity.auth.device.api.RegistrationType;
import com.amazon.identity.auth.device.api.SigninOption;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.identity.auth.device.c;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.l1;
import com.amazon.identity.auth.device.p;
import com.amazon.identity.auth.device.q0;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.z0;
import com.amazon.identity.platform.setting.PlatformSettings;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class g implements com.amazon.identity.auth.device.f {
    public static final com.amazon.identity.auth.device.c q = new com.amazon.identity.auth.device.c();
    public static final String r = g.class.getName();
    public static g s;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f335a;
    public final l1 b;
    public final q0 c;
    public final RegisterChildApplicationAction d;
    public final AmazonAccountManager e;
    public final u f;
    public final p g;
    public final z h;
    public final u7 i;
    public final z0 j;
    public final x0 k;
    public final s1 l;
    public final OAuthTokenManager m;
    public final s7 n;
    public final d1 o;
    public final w5 p;

    /* JADX INFO: compiled from: DCP */
    public class a implements q0.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f336a;
        public final /* synthetic */ String b;
        public final /* synthetic */ Account c;
        public final /* synthetic */ Set d;
        public final /* synthetic */ Bundle e;
        public final /* synthetic */ Callback f;

        public a(boolean z, String str, Account account, Set set, Bundle bundle, Callback callback) {
            this.f336a = z;
            this.b = str;
            this.c = account;
            this.d = set;
            this.e = bundle;
            this.f = callback;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class b implements c.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f337a;
        public final /* synthetic */ String b;
        public final /* synthetic */ Bundle c;
        public final /* synthetic */ y5 d;

        public b(String str, String str2, Bundle bundle, y5 y5Var) {
            this.f337a = str;
            this.b = str2;
            this.c = bundle;
            this.d = y5Var;
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x0085  */
        @Override // com.amazon.identity.auth.device.c.b
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public android.os.Bundle a(com.amazon.identity.auth.device.api.Callback r19) {
            /*
                Method dump skipped, instruction units count: 362
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.g.b.a(com.amazon.identity.auth.device.api.Callback):android.os.Bundle");
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class c implements c.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Callback f338a;
        public final /* synthetic */ Bundle b;
        public final /* synthetic */ y5 c;

        /* JADX INFO: compiled from: DCP */
        public class a implements Callback {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ Callback f339a;

            public a(Callback callback) {
                this.f339a = callback;
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                this.f339a.onError(bundle);
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                c cVar = c.this;
                g.this.i.b("dcp.third.party.device.state", "serial.number", cVar.b.getString(DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER));
                r3.a().b();
                this.f339a.onSuccess(bundle);
            }
        }

        public c(Callback callback, Bundle bundle, y5 y5Var) {
            this.f338a = callback;
            this.b = bundle;
            this.c = y5Var;
        }

        @Override // com.amazon.identity.auth.device.c.b
        public Bundle a(Callback callback) {
            if (((HashSet) g.this.e.c()).isEmpty()) {
                g.a(g.this, RegistrationType.FROM_ADP_TOKEN, this.b, new a(callback), this.c);
                return null;
            }
            Log.e(ga.a(g.r), "Registered account found on device. bootstrap API works only on unregistered devices");
            y.a(this.f338a, g.this.e.b());
            return null;
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Bundle f340a;
        public final /* synthetic */ String b;
        public final /* synthetic */ Callback c;
        public final /* synthetic */ y5 d;
        public final /* synthetic */ Activity e;

        public d(Bundle bundle, String str, Callback callback, y5 y5Var, Activity activity) {
            this.f340a = bundle;
            this.b = str;
            this.c = callback;
            this.d = y5Var;
            this.e = activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayList<String> stringArrayList = this.f340a.getStringArrayList(MAPAccountManager.KEY_ENSURE_ACCOUNT_STATE_ATTRIBUTES);
            try {
                ArrayList<String> arrayList = null;
                String string = new TokenManagement(g.this.f335a).getToken(this.b, "com.amazon.dcp.sso.token.oauth.amazon.access_token", null, null).get().getString("value_key");
                HashSet hashSet = new HashSet();
                hashSet.addAll(stringArrayList);
                if (hashSet.contains("user_id")) {
                    hashSet.remove("user_id");
                    hashSet.add("id");
                }
                JSONObject jSONObjectA = new i2(string, this.f340a, hashSet, this.d).a();
                ArrayList<String> arrayList2 = new ArrayList<>();
                if (com.amazon.identity.auth.device.e.a(stringArrayList)) {
                    arrayList = arrayList2;
                } else if (jSONObjectA == null || jSONObjectA.length() == 0) {
                    Log.e(ga.a(i2.f), "ProfileJSON is null or empty");
                } else {
                    for (String str : stringArrayList) {
                        if (jSONObjectA.has(str)) {
                            try {
                                if (TextUtils.isEmpty(jSONObjectA.getString(str))) {
                                    arrayList2.add(str);
                                }
                            } catch (JSONException unused) {
                                arrayList2.add(str);
                            }
                        } else {
                            arrayList2.add(str);
                        }
                    }
                    arrayList = arrayList2;
                }
                if (arrayList == null) {
                    Log.e(ga.a(g.r), "Cannot fetch user profile from Panda");
                    this.c.onError(a9.a(MAPError.CommonError.INTERNAL_ERROR, "MAP cannot get user profile from Panda", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "MAP cannot get user profile from Panda"));
                    return;
                }
                if (!arrayList.isEmpty()) {
                    this.f340a.putStringArrayList(MAPAccountManager.KEY_ACCOUNT_MISSING_ATTRIBUTES, arrayList);
                    this.f340a.putBoolean(AccountConstants.KEY_SET_COOKIE_FOR_AUTHENTICATE_ACCOUNT_WITH_UI, true);
                    this.f340a.putString("com.amazon.dcp.sso.property.account.acctId", this.b);
                    this.f340a.putBoolean("isAccountStateFixUpFlow", true);
                    g.this.a((Context) this.e, SigninOption.WebviewSignin, this.f340a, this.c, this.d);
                    return;
                }
                g gVar = g.this;
                Callback callback = this.c;
                gVar.getClass();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(MAPAccountManager.KEY_ENSURE_ACCOUNT_STATE_ATTRIBUTES, stringArrayList);
                callback.onSuccess(bundle);
            } catch (Exception unused2) {
                Log.e(ga.a(g.r), "Cannot get access token");
                this.c.onError(a9.a(MAPError.CommonError.INTERNAL_ERROR, "MAP cannot get an access token to ensure the account state", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "MAP cannot get access token for ensuring the account state"));
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Bundle f342a;
        public final /* synthetic */ y5 b;
        public final /* synthetic */ Callback c;

        public f(Bundle bundle, y5 y5Var, Callback callback) {
            this.f342a = bundle;
            this.b = y5Var;
            this.c = callback;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.c.onSuccess(g.this.h.a(this.f342a, this.b));
            } catch (MAPCallbackErrorException e) {
                this.c.onError(e.getErrorBundle());
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.identity.auth.device.g$g, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DCP */
    public static class C0057g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f343a;
        public boolean b;
        public Account c;
        public Set<Integer> d;
    }

    public g(t5 t5Var, q0 q0Var, RegisterChildApplicationAction registerChildApplicationAction, l1 l1Var, AmazonAccountManager amazonAccountManager, u uVar, p pVar, u7 u7Var, z0 z0Var, x0 x0Var, s1 s1Var, p0 p0Var, OAuthTokenManager oAuthTokenManager, s7 s7Var, d1 d1Var, z zVar, w5 w5Var) {
        this.f335a = t5Var;
        this.c = q0Var;
        this.d = registerChildApplicationAction;
        this.b = l1Var;
        this.e = amazonAccountManager;
        this.f = uVar;
        this.g = pVar;
        this.i = u7Var;
        this.j = z0Var;
        this.k = x0Var;
        this.l = s1Var;
        this.m = oAuthTokenManager;
        this.n = s7Var;
        this.o = d1Var;
        this.h = zVar;
        this.p = w5Var;
    }

    public static void a(g gVar, String str, String str2) {
        gVar.getClass();
        Log.i(ga.a(r), "Notify if the default primary has changed");
        if (str.equals(str2)) {
            String strB = gVar.e.b();
            if (TextUtils.isEmpty(strB) || strB.equals(str2)) {
                return;
            }
            yd.b("DefaultPrimaryAccountChanged", new String[0]);
            String.format("The default primary account %s has been deregistered and the new one is %s", str2, strB);
            t5 t5Var = gVar.f335a;
            z0 z0Var = gVar.j;
            x0 x0Var = gVar.k;
            c7.a(t5Var, new MAPAccountManager(t5Var).getAccount());
            va.f637a.execute(new w(t5Var, strB, z0Var, x0Var, false, null, null));
        }
    }

    public static synchronized g b(Context context) {
        if (s == null || xa.a()) {
            a(context);
        }
        return s;
    }

    public final Bundle c(Bundle bundle, Callback callback, y5 y5Var) {
        Intent intentA = ca.a(this.f335a, null, AuthPortalUIActivity.class.getName());
        if (y5Var != null) {
            y5Var.a(intentA);
        }
        if (intentA == null) {
            throw new RuntimeException("No activity can handle the intent. Probably because you do not declare AuthPortalUIActivity in android manifest");
        }
        intentA.putExtras(bundle);
        if (bundle.getBoolean(MAPAccountManager.KEY_IS_CALLBACK_FROM_3P_PARAM) || bundle.containsKey(MAPAccountManager.KEY_FEDERATED_AUTH_CONFIG)) {
            intentA.putExtra("requestType", OpenIdRequest.REQUEST_TYPE.CALLBACK_FOR_3P_LOGIN.toString());
        } else if (!bundle.containsKey("requestType")) {
            intentA.putExtra("requestType", OpenIdRequest.REQUEST_TYPE.SIGN_IN.toString());
        }
        intentA.setFlags(131072);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable(MAPAccountManager.KEY_INTENT, intentA);
        intentA.putExtra("callback", new RemoteCallbackWrapper(callback));
        return bundle2;
    }

    public final boolean d(Bundle bundle) {
        return bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsSecondary") || bundle.getBoolean("com.amazon.dcp.sso.AddAccount.options.AddAsPrimary");
    }

    @Override // com.amazon.identity.auth.device.f
    public void b(Activity activity, SigninOption signinOption, Bundle bundle, Callback callback, y5 y5Var) {
        boolean z;
        Log.i(ga.a(r), "registerAccountWithUI SigninOption:" + signinOption.name());
        if (bundle == null) {
            bundle = new Bundle();
        }
        com.amazon.identity.auth.device.e.b(bundle);
        boolean z2 = false;
        boolean z3 = bundle.getBoolean(MAPAccountManager.KEY_DEREGISTERALL_AND_REGISTER_THIS_AS_PRIMARY_ACCOUNT, false);
        if (this.e.e() && !d(bundle) && !z3) {
            y.a(callback, this.e.b());
            return;
        }
        Bundle bundleC = null;
        int iOrdinal = signinOption.ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                bundle.putString("requestType", OpenIdRequest.REQUEST_TYPE.REGISTER.toString());
                bundleC = c(bundle, callback, y5Var);
            } else if (iOrdinal == 2) {
                bundleC = a(bundle, callback);
            } else if (iOrdinal != 4) {
                y.a(callback, MAPError.CommonError.BAD_REQUEST, String.format("Sign-in option %s is not supported", signinOption.name()), 7, String.format("Signin Options %s is not supported", signinOption.name()));
            } else {
                bundle.putString("requestType", OpenIdRequest.REQUEST_TYPE.FORGOT_PASSWORD.toString());
                bundleC = c(bundle, callback, y5Var);
            }
        } else if (bundle.containsKey(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE)) {
            bundle.putString("requestType", OpenIdRequest.REQUEST_TYPE.SIGN_IN.toString());
            bundleC = c(bundle, callback, y5Var);
        } else {
            t5 t5Var = this.f335a;
            String str = qe.f519a;
            AuthenticatorDescription authenticatorDescriptionA = t9.a((Context) t5Var, false);
            if (authenticatorDescriptionA == null || !authenticatorDescriptionA.packageName.equals("com.amazon.fv") || za.a(t5Var, authenticatorDescriptionA.packageName).intValue() < 5) {
                z = false;
            } else {
                Log.i(ga.a(qe.f519a), "Device has Grover with version 3 or later");
                z = true;
            }
            if (!z) {
                AuthenticatorDescription authenticatorDescriptionA2 = t9.a((Context) this.f335a, false);
                if (authenticatorDescriptionA2 != null && authenticatorDescriptionA2.packageName.equals("com.amazon.canary")) {
                    z2 = true;
                }
                if (!z2) {
                    bundleC = a(bundle, callback);
                }
            }
            if (bundleC == null) {
                bundle.putString("requestType", OpenIdRequest.REQUEST_TYPE.SIGN_IN.toString());
                bundleC = c(bundle, callback, y5Var);
            }
        }
        a(activity, callback, bundleC, "Could not find the sign in UI. If the option passed in was MyAccount, you are on a 3rd party device. Otherwise, this more than likely represents a bug.");
    }

    /* JADX INFO: compiled from: DCP */
    public class e implements p.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f341a;
        public final /* synthetic */ fa b;
        public final /* synthetic */ s2 c;

        public e(String str, fa faVar, s2 s2Var) {
            this.f341a = str;
            this.b = faVar;
            this.c = s2Var;
        }

        @Override // com.amazon.identity.auth.device.p.b
        public void a(String str, String str2, Bundle bundle) {
            g.this.a(this.f341a, this.b.b, bundle);
            Bundle bundle2 = new Bundle();
            t5 t5Var = g.this.f335a;
            String str3 = this.f341a;
            Account accountA = r9.a(t5Var, str3);
            r9.a(accountA == null ? null : accountA.name, str3, bundle2);
            bundle2.putString("authtoken", g.this.i.d(this.f341a, this.b.c));
            this.c.onSuccess(bundle2);
        }

        @Override // com.amazon.identity.auth.device.p.b
        public void a(MAPError mAPError, String str, MAPAccountManager.RegistrationError registrationError, String str2, Bundle bundle) {
            y.a(this.c, mAPError, str, registrationError.value(), str2, bundle);
        }
    }

    public final Bundle c(Bundle bundle) {
        String string = bundle.getString("com.amazon.dcp.sso.token.oauth.amazon.access_token");
        Bundle bundle2 = new Bundle();
        if (!TextUtils.isEmpty(string)) {
            bundle2.putString("com.amazon.dcp.sso.token.oauth.amazon.access_token", string);
            bundle2.putString(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT, bundle.getString(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT));
            bundle2.putString(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN, bundle.getString(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN));
        }
        bundle.remove("com.amazon.dcp.sso.token.oauth.amazon.access_token");
        bundle.remove(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT);
        bundle.remove(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN);
        return bundle2;
    }

    @Override // com.amazon.identity.auth.device.f
    public Set<String> c() {
        return this.e.c();
    }

    @Override // com.amazon.identity.auth.device.f
    @Deprecated
    public void b(Activity activity, String str, Bundle bundle, Callback callback, y5 y5Var) {
        a((Context) activity, str, true, bundle, callback, y5Var);
    }

    public final Map<String, Map<String, String>> b(Bundle bundle) {
        HashMap map;
        String string = bundle.getString(AccountConstants.KEY_DEVICE_CREDENTIALS);
        if (string == null) {
            map = null;
        } else {
            HashMap map2 = new HashMap();
            try {
                JSONObject jSONObject = new JSONObject(string);
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                    Iterator<String> itKeys2 = jSONObject2.keys();
                    HashMap map3 = new HashMap();
                    while (itKeys2.hasNext()) {
                        String next2 = itKeys2.next();
                        map3.put(next2, jSONObject2.getString(next2));
                    }
                    map2.put(next, map3);
                }
            } catch (JSONException e2) {
                Log.w(ga.a(sb.f546a), "CredentialMapSerializer.toMap failed - ignoring the credentials received: %s", e2);
            }
            map = map2;
        }
        bundle.remove(AccountConstants.KEY_DEVICE_CREDENTIALS);
        return map == null ? Collections.emptyMap() : map;
    }

    public final Bundle b(String str, Callback callback, y5 y5Var, Bundle bundle) {
        List<j1> listUnmodifiableList;
        Resources resourcesForApplication;
        int identifier;
        XmlResourceParser xmlResourceParserLoadXmlMetaData;
        String str2;
        Log.i(ga.a(r), "Starting Deregistration");
        Account accountA = r9.a(this.f335a, str);
        boolean zA = this.j.a(str);
        Set<Integer> setA = this.j.a(this.f335a, str);
        q0 q0Var = this.c;
        l1 l1Var = this.b;
        synchronized (l1Var.d) {
            listUnmodifiableList = l1Var.e;
            int i = 2;
            if (listUnmodifiableList == null) {
                ArrayList<j1> arrayList = new ArrayList();
                Iterator it = ((ArrayList) l1Var.f414a.a(new Intent("com.amazon.dcp.sso.AccountSubAuthenticator"), 128)).iterator();
                while (it.hasNext()) {
                    try {
                        ServiceInfo serviceInfo = ((ResolveInfo) it.next()).serviceInfo;
                        z5 z5Var = l1Var.f414a;
                        z5Var.getClass();
                        if (serviceInfo == null) {
                            Log.e(ga.a(z5.f), "PackageItemInfo cannot be null in getParserForPackage");
                            xmlResourceParserLoadXmlMetaData = null;
                        } else {
                            xmlResourceParserLoadXmlMetaData = serviceInfo.loadXmlMetaData(z5Var.b, "com.amazon.dcp.sso.AccountSubAuthenticator");
                        }
                        if (xmlResourceParserLoadXmlMetaData != null) {
                            j1 j1VarA = l1Var.b.a(((PackageItemInfo) serviceInfo).packageName, ((PackageItemInfo) serviceInfo).name, xmlResourceParserLoadXmlMetaData);
                            arrayList.add(j1VarA);
                            String str3 = l1.f;
                            Object[] objArr = new Object[i];
                            objArr[0] = j1VarA.f390a;
                            objArr[1] = j1VarA.b;
                            String.format("Detected sub-authenticator: %s/%s", objArr);
                            ga.a(str3);
                            String.format(" Supports token types:", new Object[0]);
                            ga.a(str3);
                            for (String str4 : j1VarA.c) {
                                String str5 = l1.f;
                                String.format("  %s", str4);
                                ga.a(str5);
                            }
                        } else {
                            throw new InvalidSubAuthenticatorDefinitionException(String.format("%s does not have a valid sub authenticator metadata file", ((PackageItemInfo) serviceInfo).packageName));
                        }
                    } catch (InvalidSubAuthenticatorDefinitionException e2) {
                        Log.e(ga.a(l1.f), String.format("Ignored invalid sub authenticator from calling package : %s", e2.getMessage()));
                    }
                    i = 2;
                }
                ArrayList<j1> arrayList2 = new ArrayList();
                Set<String> setA2 = l1Var.f414a.a();
                ArrayList<l1.a> arrayList3 = new ArrayList();
                for (String str6 : (HashSet) setA2) {
                    try {
                        z5 z5Var2 = l1Var.f414a;
                        if (((m6) z5.g).a(z5Var2.d, str6, false)) {
                            try {
                                resourcesForApplication = z5Var2.b.getResourcesForApplication(str6);
                            } catch (PackageManager.NameNotFoundException e3) {
                                throw e3;
                            } catch (Exception e4) {
                                z5.a(e4);
                                resourcesForApplication = z5Var2.b.getResourcesForApplication(str6);
                            }
                        } else {
                            resourcesForApplication = null;
                        }
                        if (resourcesForApplication != null && (identifier = resourcesForApplication.getIdentifier(AccountConstants.DMS_SUB_AUTHENTICATOR_XML_FILE_NAME, "xml", str6)) != 0) {
                            arrayList3.add(new l1.a(str6, resourcesForApplication.getXml(identifier)));
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                        ga.a(l1.f);
                    }
                }
                for (l1.a aVar : arrayList3) {
                    try {
                        j1 j1VarA2 = l1Var.b.a(aVar.f415a, null, aVar.b);
                        arrayList2.add(j1VarA2);
                        String str7 = l1.f;
                        String.format("Detected DMS sub-authenticator: %s/%s", j1VarA2.f390a, j1VarA2.b);
                        ga.a(str7);
                        String.format(" Supports token types:", new Object[0]);
                        ga.a(str7);
                        Iterator<String> it2 = j1VarA2.c.iterator();
                        while (it2.hasNext()) {
                            String next = it2.next();
                            String str8 = l1.f;
                            Iterator<String> it3 = it2;
                            String.format("  %s", next);
                            ga.a(str8);
                            it2 = it3;
                        }
                    } catch (InvalidSubAuthenticatorDefinitionException e5) {
                        Log.e(ga.a(l1.f), String.format("Ignored invalid sub authenticator from calling package : %s", e5.getMessage()));
                    }
                }
                ArrayList<j1> arrayList4 = new ArrayList();
                HashSet hashSet = new HashSet();
                for (j1 j1Var : arrayList) {
                    hashSet.add(j1Var.f390a);
                    arrayList4.add(j1Var);
                }
                for (j1 j1Var2 : arrayList2) {
                    if (!hashSet.contains(j1Var2.f390a)) {
                        arrayList4.add(j1Var2);
                    } else {
                        ga.a(l1.f);
                    }
                }
                ArrayList arrayList5 = new ArrayList();
                for (j1 j1Var3 : arrayList4) {
                    String str9 = j1Var3.f390a;
                    if (l1Var.c.a(str9) == null) {
                        ga.a(l1.f, "Package %s is not a MAP R5 app, so using it's Sub Auth.", str9);
                        arrayList5.add(j1Var3);
                    } else {
                        ga.a(l1.f, "Package %s is a MAP R5 app, so not using it's Sub Auth.", str9);
                    }
                }
                listUnmodifiableList = Collections.unmodifiableList(arrayList5);
                synchronized (l1Var.d) {
                    l1Var.e = listUnmodifiableList;
                }
            }
        }
        a aVar2 = new a(zA, str, accountA, setA, bundle, callback);
        q0Var.getClass();
        Log.i(ga.a(q0.f), "Starting deregister request");
        Bundle bundleA = com.amazon.identity.auth.device.e.a(bundle);
        if (q0Var.d.b.d(str)) {
            bundleA.putBoolean("DeregisteringDefaultPrimary", true);
        }
        d3 d3VarA = q0Var.e.a().a(str, (String) null, (p.b) null);
        a1 a1Var = q0Var.d;
        a1Var.getClass();
        if (bundleA.getBoolean("DeregisteringDevice") || !bundleA.getBoolean("DeregisteringDefaultPrimary")) {
            str2 = null;
        } else {
            for (String str10 : a1Var.b.c()) {
                if (a1Var.b.e(str10) && a1Var.a(str10)) {
                    str2 = str10;
                    break;
                }
            }
            str2 = null;
        }
        if (!TextUtils.isEmpty(str2)) {
            String.format("Promote account %s as the new default primary", str2);
            a1Var.c.d(str2, AccountConstants.KEY_SECONDARY_AMAZON_ACCOUNT, null);
            bundleA.putString("NewDefaultPrimary", str2);
            if (a1Var.b.a(str, AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT) != null) {
                a1Var.c.d(str2, AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT, "true");
            }
        }
        AmazonAccountManager.AccountRegistrationStatus accountRegistrationStatus = AmazonAccountManager.AccountRegistrationStatus.Deregistering;
        String.format("Set status for account %s as %s", str, accountRegistrationStatus.getValue());
        AmazonAccountManager amazonAccountManager = a1Var.b;
        amazonAccountManager.getClass();
        amazonAccountManager.f144a.d(str, AccountConstants.KEY_ACCOUNT_STATUS, accountRegistrationStatus.getValue());
        z0 z0Var = q0Var.b;
        z0Var.c();
        if (!z0Var.b.a(str)) {
            Log.e(ga.a(z0.g), "Cannot remove all account mappings since the account doesn't exist");
        } else {
            ArrayList arrayList6 = new ArrayList();
            t5 t5Var = z0Var.f688a;
            AmazonAccountManager amazonAccountManager2 = z0Var.b;
            ArrayList arrayList7 = new ArrayList();
            if (amazonAccountManager2.a(str, AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT) != null) {
                arrayList7.add(new z0.i(t5Var, amazonAccountManager2));
            }
            arrayList6.addAll(arrayList7);
            arrayList6.addAll(z0.g.a(z0Var.f688a, z0Var.b, str));
            t5 t5Var2 = z0Var.f688a;
            AmazonAccountManager amazonAccountManager3 = z0Var.b;
            Set<String> setA3 = z0.d.a(new BackwardsCompatiableDataStorage(t5Var2, t5Var2.a()), str, "com.amazon.dcp.sso.property.account.extratokens.account_packages");
            ArrayList arrayList8 = new ArrayList();
            Iterator it4 = ((HashSet) setA3).iterator();
            while (it4.hasNext()) {
                arrayList8.add(new z0.f(t5Var2, new MultipleAccountManager.PackageMappingType((String) it4.next()), amazonAccountManager3));
            }
            arrayList6.addAll(arrayList8);
            t5 t5Var3 = z0Var.f688a;
            AmazonAccountManager amazonAccountManager4 = z0Var.b;
            Set<String> setA4 = z0.d.a(new BackwardsCompatiableDataStorage(t5Var3, t5Var3.a()), str, "com.amazon.dcp.sso.property.account.extratokens.custom_keys");
            ArrayList arrayList9 = new ArrayList();
            Iterator it5 = ((HashSet) setA4).iterator();
            while (it5.hasNext()) {
                arrayList9.add(new z0.b(t5Var3, new MultipleAccountManager.CustomKeyMappingType((String) it5.next()), amazonAccountManager4));
            }
            arrayList6.addAll(arrayList9);
            t5 t5Var4 = z0Var.f688a;
            AmazonAccountManager amazonAccountManager5 = z0Var.b;
            ArrayList arrayList10 = new ArrayList();
            if (amazonAccountManager5.a(str, AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT) != null) {
                arrayList10.add(new z0.h(t5Var4, MultipleAccountManager.SessionPackageMappingType.createSessionPackageMappingInstance(t5Var4), amazonAccountManager5));
            }
            arrayList6.addAll(arrayList10);
            z0Var.b();
            ArrayList arrayList11 = new ArrayList();
            Iterator it6 = arrayList6.iterator();
            while (it6.hasNext()) {
                arrayList11.addAll(((z0.a) it6.next()).b(str));
            }
            z0Var.a(arrayList11);
        }
        q0.d dVar = new q0.d(q0Var.f500a, str, listUnmodifiableList, q0Var.c, d3VarA, y5Var, bundleA);
        r0 r0Var = new r0(q0Var, aVar2);
        synchronized (dVar) {
            dVar.n = r0Var;
        }
        q0.b bVar = q0Var.e;
        synchronized (dVar) {
            dVar.m = bVar;
        }
        q0.g.execute(dVar);
        return null;
    }

    @Override // com.amazon.identity.auth.device.f
    public String b() {
        return this.j.a(MultipleAccountManager.PrimaryUserMappingType.createPrimaryMappingForProfile(m4.a()));
    }

    @Override // com.amazon.identity.auth.device.f
    public String b(String str) {
        return this.j.a(this.k.a(str, m4.a()));
    }

    @Override // com.amazon.identity.auth.device.f
    public MAPFuture<Bundle> b(String str, String str2, Bundle bundle, Callback callback, y5 y5Var) {
        Log.i(ga.a(r), "renameDevice logic called");
        s2 s2Var = new s2(callback);
        q.f252a.execute(new c.a(new b(str, str2, bundle, y5Var), s2Var, "RenameDevice"));
        return s2Var;
    }

    public void b(Bundle bundle, Callback callback, y5 y5Var) {
        if (!qe.a(this.f335a)) {
            Log.e(ga.a(r), "BootstrapWithADPToken API is only supported for isolated applications for now.");
            y.a(callback, MAPError.CommonError.UNSUPPORTED_OPERATION, "BootstrapWithADPToken API is only supported for isolated applications for now.", MAPAccountManager.RegistrationError.BAD_REQUEST.value(), "BootstrapWithADPToken API is only supported for isolated applications for now.", null);
        } else if (!((HashSet) this.e.c()).isEmpty()) {
            Log.e(ga.a(r), "Registered account found on device. bootstrap API works only on unregistered devices");
            y.a(callback, this.e.b());
        } else {
            q.f252a.execute(new c.a(new c(callback, bundle, y5Var), callback, "BootstrapMAPWithADPToken"));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00b6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.os.Bundle a(com.amazon.identity.auth.device.g r20, com.amazon.identity.auth.device.api.RegistrationType r21, android.os.Bundle r22, com.amazon.identity.auth.device.api.Callback r23, com.amazon.identity.auth.device.y5 r24) {
        /*
            Method dump skipped, instruction units count: 2426
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.g.a(com.amazon.identity.auth.device.g, com.amazon.identity.auth.device.api.RegistrationType, android.os.Bundle, com.amazon.identity.auth.device.api.Callback, com.amazon.identity.auth.device.y5):android.os.Bundle");
    }

    public static void a(Context context) {
        s7 b8Var;
        t5 t5VarA = t5.a(context.getApplicationContext());
        q0 q0Var = new q0(t5VarA);
        RegisterChildApplicationAction registerChildApplicationAction = new RegisterChildApplicationAction(t5VarA);
        l1 l1Var = new l1(t5VarA);
        AmazonAccountManager amazonAccountManager = new AmazonAccountManager(t5VarA);
        u uVar = new u(t5VarA);
        p pVar = new p(t5VarA);
        u7 u7VarA = t5VarA.a();
        z0 z0VarA = z0.a(t5VarA);
        x0 x0VarA = y0.a(t5VarA);
        s1 s1VarA = s1.a(t5VarA);
        p0 p0Var = new p0();
        OAuthTokenManager oAuthTokenManager = new OAuthTokenManager(t5VarA);
        if (qe.k(((i5) t5VarA.getSystemService("sso_platform")).f382a)) {
            b8Var = new h8(t5VarA);
        } else {
            b8Var = new b8(t5.a(t5VarA).a());
        }
        s = new g(t5VarA, q0Var, registerChildApplicationAction, l1Var, amazonAccountManager, uVar, pVar, u7VarA, z0VarA, x0VarA, s1VarA, p0Var, oAuthTokenManager, b8Var, new d1(t5VarA), new z(t5VarA), new w5());
    }

    public final void a(Context context, Callback callback, Bundle bundle, String str) {
        if (bundle != null && context != null) {
            a(context, bundle, callback);
            return;
        }
        if (bundle != null) {
            y.a(callback, bundle);
            return;
        }
        MAPError.CommonError commonError = MAPError.CommonError.UI_NOT_FOUND;
        MAPAccountManager.RegistrationError registrationError = MAPAccountManager.RegistrationError.UI_NOT_FOUND;
        String str2 = a9.f175a;
        callback.onError(a9.a(commonError, str, registrationError.value(), str));
    }

    public final void a(Context context, Bundle bundle, Callback callback) {
        Intent intent = (Intent) bundle.getParcelable(MAPAccountManager.KEY_INTENT);
        bundle.remove(MAPAccountManager.KEY_INTENT);
        if (intent == null) {
            Log.e(ga.a(r), "Failed to locate an activity containing the sign-in UI");
            y.a(callback, MAPError.CommonError.UI_NOT_FOUND, "Failed to locate an activity containing the sign-in UI", 6, "Failed to locate an activity containing the sign-in UI");
        } else {
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            context.startActivity(intent);
        }
    }

    public final Bundle a(Bundle bundle, Callback callback) {
        List<ResolveInfo> listQueryIntentActivities;
        Intent intent = new Intent(AccountConstants.ACTION_ACCOUNT_ADD_INTENT);
        z5 z5Var = new z5(this.f335a, false);
        try {
            listQueryIntentActivities = z5Var.b.queryIntentActivities(intent, 0);
        } catch (Exception e2) {
            z5.a(e2);
            listQueryIntentActivities = z5Var.b.queryIntentActivities(intent, 0);
        }
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : listQueryIntentActivities) {
            if (z5Var.a(resolveInfo.activityInfo.packageName, false)) {
                arrayList.add(resolveInfo);
            }
        }
        ActivityInfo activityInfo = !com.amazon.identity.auth.device.e.a(arrayList) ? ((ResolveInfo) arrayList.get(0)).activityInfo : null;
        if (activityInfo == null) {
            intent = null;
        } else {
            intent.setComponent(new ComponentName(activityInfo.packageName, activityInfo.name));
        }
        if (intent == null) {
            Log.i(ga.a(r), "No intent for MyAccount.");
            return null;
        }
        Log.i(ga.a(r), "Register with My Account");
        intent.putExtras(bundle);
        com.amazon.identity.auth.device.d dVar = new com.amazon.identity.auth.device.d(callback);
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeStrongBinder(dVar.asBinder());
        parcelObtain.setDataPosition(0);
        intent.putExtra("accountAuthenticatorResponse", new AccountAuthenticatorResponse(parcelObtain));
        intent.putExtra(AccountConstants.KEY_ADD_ACCOUNT_AUTHTOKENTYPE, bundle.getString(AccountConstants.KEY_ADD_ACCOUNT_AUTHTOKENTYPE));
        intent.putExtra(AccountConstants.KEY_ADD_ACCOUNT_REQUIREDFEATURES, bundle.getStringArray(AccountConstants.KEY_ADD_ACCOUNT_REQUIREDFEATURES));
        intent.putExtra(AccountConstants.KEY_ADD_ACCOUNT_OPTIONS, bundle.getBundle(AccountConstants.KEY_ADD_ACCOUNT_OPTIONS));
        intent.putExtra(AccountConstants.KEY_ADD_ACCOUNT_CALLER_INFORMATION, this.f335a.getPackageName());
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable(MAPAccountManager.KEY_INTENT, intent);
        return bundle2;
    }

    @Override // com.amazon.identity.auth.device.f
    public void a(RegistrationType registrationType, Bundle bundle, Callback callback, y5 y5Var) {
        com.amazon.identity.auth.device.e.a(registrationType, "RegistrationType");
        Log.i(ga.a(r), "registerAccount:" + registrationType.getName());
        if (registrationType == RegistrationType.FROM_ADP_TOKEN) {
            b(bundle, callback, y5Var);
            return;
        }
        if (bundle.getBoolean(MAPAccountManager.KEY_DEREGISTERALL_AND_REGISTER_THIS_AS_PRIMARY_ACCOUNT, false) && a(new s2(null))) {
            a(MAPError.AccountError.DEREGISTER_FAILED, "Cannot deregister all accounts because the system property, ignore.deregister, is set to true", MAPAccountManager.RegistrationError.DEREGISTER_FAILED, callback, (Bundle) null, "Cannot deregister all accounts before register primary account, ignore.deregister is set in system property.");
            return;
        }
        q.f252a.execute(new c.a(new l(this, registrationType, bundle, y5Var), callback, "AddAccount"));
    }

    @Override // com.amazon.identity.auth.device.f
    public MAPFuture<Bundle> a(String str, String str2, Bundle bundle, Callback callback, y5 y5Var) {
        com.amazon.identity.auth.device.e.a((Object) str, "directedId");
        com.amazon.identity.auth.device.e.a((Object) str2, Splash.PARAMS_DEVICE_TYPE);
        Log.i(ga.a(r), "registerChildApplication device type:" + str2);
        s2 s2Var = new s2(callback);
        try {
            this.d.a(str, str2, bundle, s2Var, y5Var);
        } catch (RegisterChildApplicationAction.NotChildApplicationException unused) {
            String str3 = String.format("%s is not a child application device type", str2);
            y.a(s2Var, MAPError.AttributeError.NOT_A_CHILD_DEVICE_TYPE, str3, MAPAccountManager.RegistrationError.REGISTER_FAILED.value(), str3, null);
        }
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.f
    public MAPFuture<Bundle> a(String str, Callback callback, y5 y5Var, Bundle bundle) {
        String str2 = r;
        Log.i(ga.a(str2), "deregisterAccount logic called");
        s2 s2Var = new s2(callback);
        Bundle bundleA = com.amazon.identity.auth.device.e.a(bundle);
        Log.i(ga.a(str2), "Deregister initiated");
        if (!a(s2Var)) {
            if (!this.e.a(str)) {
                a((Callback) s2Var, true);
            } else {
                String strB = this.e.b();
                if (str.equals(strB)) {
                    Log.i(ga.a(str2), "Deregistering a default primary");
                    bundleA.putBoolean("DeregisteringDefaultPrimary", true);
                }
                q.f252a.execute(new c.a(new o(this, str, strB, y5Var, bundleA), s2Var, "DeregisterAccount"));
            }
        }
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.f
    public MAPFuture<Bundle> a(Callback callback, y5 y5Var, Bundle bundle) {
        Log.i(ga.a(r), "deregisterDevice logic called");
        s2 s2Var = new s2(callback);
        Bundle bundleA = com.amazon.identity.auth.device.e.a(bundle);
        bundleA.putBoolean("DeregisteringDevice", true);
        Set<String> setC = this.e.c();
        if (!a(s2Var)) {
            q.f252a.execute(new c.a(new j(this, setC, y5Var, bundleA), s2Var, "DeregisterAccountsInner"));
        }
        return s2Var;
    }

    @Override // com.amazon.identity.auth.device.f
    public MAPFuture<Bundle> a(String str, fa faVar, Bundle bundle, Callback callback, y5 y5Var) {
        boolean z;
        s2 s2Var = new s2(callback);
        if (str == null || !this.e.a(str)) {
            y.a(s2Var, MAPError.AccountError.CUSTOMER_NOT_FOUND, "Customer account does not exist or directedId is null", 7, "The provided account does not exist", null);
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            return s2Var;
        }
        if (!(com.amazon.identity.auth.device.e.b(faVar) || "com.amazon.dcp.sso.token.cookie.xmainAndXabcCookies".equals(faVar.c))) {
            y.a(s2Var, MAPError.CommonError.BAD_REQUEST, String.format("The key %s is not valid", faVar.c), MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), String.format("key %s is not valid", faVar.c), null);
            return s2Var;
        }
        e eVar = new e(str, faVar, s2Var);
        p pVar = this.g;
        String str2 = faVar.b;
        Bundle bundle2 = bundle != null ? bundle : new Bundle();
        pVar.getClass();
        if (str2 == null) {
            pVar.a(bundle2.getString("com.amazon.dcp.sso.AddAccount.options.URL"), str, (String) null, eVar, y5Var);
        } else {
            pVar.a(bundle2.getString("com.amazon.dcp.sso.AddAccount.options.URL"), str, str2, eVar, y5Var);
        }
        return s2Var;
    }

    @Deprecated
    public final void a(Context context, String str, boolean z, Bundle bundle, Callback callback, y5 y5Var) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        com.amazon.identity.auth.device.e.b(bundle);
        ga.c(r, "Confirm Credential called with options: %s.", bundle.toString());
        if (TextUtils.isEmpty(str)) {
            MAPError.CommonError commonError = MAPError.CommonError.BAD_REQUEST;
            MAPAccountManager.RegistrationError registrationError = MAPAccountManager.RegistrationError.BAD_REQUEST;
            String str2 = a9.f175a;
            callback.onError(a9.a(commonError, "Cannot confirm credentials because the directedId is empty", registrationError.value(), "Cannot confirm credential given empty directedId."));
            return;
        }
        if (z && !this.e.a(str)) {
            String str3 = String.format("Customer %s is not registered.", str);
            callback.onError(a9.a(MAPError.AccountError.CUSTOMER_NOT_FOUND, str3, MAPAccountManager.RegistrationError.CUSTOMER_NOT_FOUND.value(), str3));
            return;
        }
        bundle.putString("requestType", OpenIdRequest.REQUEST_TYPE.CONFIRM_CREDENTIAL.toString());
        bundle.putString("directedid", str);
        Bundle bundleC = c(bundle, callback, y5Var);
        if (context != null) {
            a(context, bundleC, callback);
        } else {
            y.a(callback, bundleC);
        }
    }

    @Override // com.amazon.identity.auth.device.f
    public void a(Bundle bundle, Callback callback, y5 y5Var) {
        if (bundle != null && ((bundle.containsKey("com.amazon.dcp.sso.property.account.acctId") || bundle.containsKey(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME)) && bundle.containsKey("password"))) {
            if (bundle.containsKey(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME) && bundle.containsKey("com.amazon.dcp.sso.property.account.acctId")) {
                MAPError.CommonError commonError = MAPError.CommonError.BAD_REQUEST;
                MAPAccountManager.RegistrationError registrationError = MAPAccountManager.RegistrationError.BAD_REQUEST;
                String str = a9.f175a;
                callback.onError(a9.a(commonError, "Cannot pass in both a login and directedId to the authenticateAccount API.", registrationError.value(), "Cannot pass in both login and directedId to authenticateAccount API."));
                return;
            }
            va.f637a.execute(new f(bundle, y5Var, callback));
            return;
        }
        MAPError.CommonError commonError2 = MAPError.CommonError.BAD_REQUEST;
        MAPAccountManager.RegistrationError registrationError2 = MAPAccountManager.RegistrationError.BAD_REQUEST;
        String str2 = a9.f175a;
        callback.onError(a9.a(commonError2, "A login/directedId and password are required to authenticate/confirm credentials.", registrationError2.value(), "A login/directedId and password are required to authenticate/confirmCredential."));
    }

    @Override // com.amazon.identity.auth.device.f
    public void a(Activity activity, SigninOption signinOption, Bundle bundle, Callback callback, y5 y5Var) {
        a((Context) activity, signinOption, bundle, callback, y5Var);
    }

    public final void a(Context context, SigninOption signinOption, Bundle bundle, Callback callback, y5 y5Var) {
        String[] stringArray;
        com.amazon.identity.auth.device.e.a(signinOption, "option");
        String str = r;
        signinOption.name();
        ga.a(str);
        Bundle bundle2 = bundle != null ? bundle : new Bundle();
        com.amazon.identity.auth.device.e.b(bundle2);
        Bundle bundleC = null;
        if (bundle2.getBoolean(AccountConstants.KEY_SET_COOKIE_FOR_AUTHENTICATE_ACCOUNT_WITH_UI)) {
            String string = bundle.getString("com.amazon.identity.ap.domain");
            String string2 = bundle.getString("com.amazon.dcp.sso.property.account.acctId");
            TokenManagement tokenManagement = new TokenManagement(this.f335a);
            Bundle bundle3 = new Bundle();
            bundle3.putBoolean(CookieKeys.Options.KEY_FORCE_REFRESH, true);
            bundle3.putString("domain", string);
            try {
                stringArray = tokenManagement.getCookies(string2, string, bundle3, null).get().getStringArray(CookieKeys.KEY_COOKIES);
            } catch (MAPCallbackErrorException e2) {
                Bundle errorBundle = e2.getErrorBundle();
                if (errorBundle != null) {
                    Log.e(ga.a(r), "Cannot refresh the cookie to start auth portal attributes fix up flow. Error Code:" + errorBundle.getInt("com.amazon.dcp.sso.ErrorCode") + " Error message:" + errorBundle.getString("com.amazon.dcp.sso.ErrorMessage"));
                }
                callback.onError(a9.a(MAPError.TokenError.COOKIE_REFRESH_FAILED, "Cookie force refresh failed before launching the AuthPortal UI", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "Cookie force refresh fail before lauching auth portal ui for fix up page"));
            } catch (InterruptedException unused) {
                callback.onError(a9.a(MAPError.TokenError.COOKIE_REFRESH_FAILED, "Cookie force refresh failed before launching the AuthPortal UI", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "Cookie force refresh fail before lauching auth portal ui for fix up page"));
            } catch (ExecutionException unused2) {
                callback.onError(a9.a(MAPError.TokenError.COOKIE_REFRESH_FAILED, "Cookie force refresh failed before launching the AuthPortal UI", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "Cookie force refresh fail before lauching auth portal ui for fix up page"));
            }
            if (stringArray == null || stringArray.length == 0) {
                callback.onError(a9.a(MAPError.TokenError.COOKIE_REFRESH_FAILED, "Cookie force refresh failed before launching the AuthPortal UI", MAPAccountManager.RegistrationError.INTERNAL_ERROR.value(), "Cookie force refresh fail before lauching auth portal ui for fix up page"));
                stringArray = null;
            }
            if (stringArray == null) {
                return;
            } else {
                bundle2.putStringArray("InjectCookiesToAuthPortalUIActivity", stringArray);
            }
        }
        int iOrdinal = signinOption.ordinal();
        if (iOrdinal == 0) {
            bundle2.putString("requestType", OpenIdRequest.REQUEST_TYPE.AUTHENTICATE.toString());
            bundleC = c(bundle2, callback, y5Var);
        } else if (iOrdinal == 3) {
            a(context, bundle.getString("com.amazon.dcp.sso.property.account.acctId"), false, bundle, callback, y5Var);
            return;
        } else if (iOrdinal != 4) {
            y.a(callback, MAPError.CommonError.BAD_REQUEST, String.format("Sign-in option %s is not supported", signinOption.name()), 7, String.format("Signin Options %s is not supported", signinOption.name()));
        } else {
            bundle2.putString("requestType", OpenIdRequest.REQUEST_TYPE.FORGOT_PASSWORD.toString());
            bundleC = c(bundle2, callback, y5Var);
        }
        a(context, callback, bundleC, "Could not find the sign in UI. This more than likely represents a bug.");
    }

    public final List<C0057g> a(Set<String> set) {
        ArrayList arrayList = new ArrayList();
        if (set != null) {
            for (String str : set) {
                boolean zA = this.j.a(str);
                Account accountA = r9.a(this.f335a, str);
                Set<Integer> setA = this.j.a(this.f335a, str);
                C0057g c0057g = new C0057g();
                c0057g.b = zA;
                c0057g.c = accountA;
                c0057g.f343a = str;
                c0057g.d = setA;
                arrayList.add(c0057g);
            }
        }
        return arrayList;
    }

    public final void a(List<C0057g> list, Bundle bundle) {
        for (C0057g c0057g : list) {
            x.a(this.f335a, c0057g.b, c0057g.f343a, c0057g.c, null, c0057g.d, bundle);
        }
    }

    public final void a(Bundle bundle, Bundle bundle2) {
        String strA;
        String str;
        String string = bundle2.getString(AccountConstants.KEY_CUSTOMER_REGION);
        String strB = null;
        if (TextUtils.isEmpty(string)) {
            strA = null;
            str = null;
        } else {
            if (TextUtils.isEmpty(string)) {
                Log.i(ga.a(q9.f514a), "Empty customer region, returning null domain ");
            } else {
                EnvironmentUtils environmentUtils = EnvironmentUtils.c;
                if (string.equalsIgnoreCase("NA")) {
                    strA = environmentUtils.d();
                } else if (string.equalsIgnoreCase("EU")) {
                    strA = environmentUtils.c();
                } else if (string.equalsIgnoreCase("FE")) {
                    strA = environmentUtils.b();
                } else if (string.equalsIgnoreCase("CN")) {
                    strA = environmentUtils.a();
                } else {
                    Log.e(ga.a(q9.f514a), "Ignoring unknown customer region:  " + string);
                }
                str = "customer region (home region)";
            }
            strA = null;
            str = "customer region (home region)";
        }
        if (TextUtils.isEmpty(strA)) {
            String string2 = bundle2.getString("com.amazon.dcp.sso.token.device.accountpool");
            if (!TextUtils.isEmpty(string2)) {
                if (TextUtils.isEmpty(string2)) {
                    Log.i(ga.a(q9.f514a), "Empty account pool, returning null domain ");
                } else {
                    EnvironmentUtils environmentUtils2 = EnvironmentUtils.c;
                    if (string2.equalsIgnoreCase("Amazon")) {
                        strB = environmentUtils2.d();
                    } else if (string2.equalsIgnoreCase("AmazonCN")) {
                        strB = environmentUtils2.a();
                    } else if (string2.equalsIgnoreCase("AmazonJP")) {
                        strB = environmentUtils2.b();
                    } else {
                        Log.e(ga.a(q9.f514a), "Ignoring unknown account pool:  " + string2);
                    }
                }
                str = "account pool";
                strA = strB;
            }
        }
        if (!TextUtils.isEmpty(strA)) {
            String strA2 = EnvironmentUtils.c.a(strA);
            Log.i(ga.a(r), String.format("Marking the amazon domain for added account: %s. It's generated base on %s.", strA2, str));
            bundle2.putString("key_auth_portal_endpoint", strA2);
            bundle2.putString("authDomain", strA2);
        }
        bundle2.putString("key_panda_endpoint", EnvironmentUtils.c.e(q9.b(bundle)));
        bundle2.putString("key_panda_marketplace_header", EnvironmentUtils.c.c(bundle));
        bundle2.putString("x-amzn-identity-auth-domain", q9.a(bundle));
    }

    public final List<MAPCookie> a(String str, Bundle bundle) {
        List<MAPCookie> arrayList = new ArrayList<>();
        String string = bundle.getString(AccountConstants.KEY_WEBSITE_COOKIES_JSON_ARRAY);
        ga.a(r);
        if (TextUtils.isEmpty(string)) {
            return arrayList;
        }
        try {
            try {
                arrayList = new t8(this.f335a).a(str, new JSONArray(string), (String) null);
            } catch (JSONException e2) {
                Log.e(ga.a(r), "Failed to parse the cookie JSONArray : " + e2.getMessage());
            }
            bundle.remove(AccountConstants.KEY_WEBSITE_COOKIES_JSON_ARRAY);
            return arrayList;
        } catch (JSONException e3) {
            Log.e(ga.a(r), "String to JSONArray Conversion failed : " + e3.getMessage());
            return arrayList;
        }
    }

    public final Map<String, String> a(String str, Bundle bundle, List<MAPCookie> list, String str2, String str3) {
        Map mapEmptyMap;
        HashMap map = new HashMap();
        this.m.getClass();
        if (bundle.size() <= 0) {
            mapEmptyMap = Collections.emptyMap();
        } else {
            HashMap map2 = new HashMap();
            String string = bundle.getString("com.amazon.dcp.sso.token.oauth.amazon.access_token");
            int i = 0;
            try {
                i = Integer.parseInt(bundle.getString(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT));
            } catch (NumberFormatException unused) {
                Log.e(ga.a("OAuthTokenManager"), "NumberFormatException fetching expiresInSeconds data");
            }
            String string2 = bundle.getString(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN);
            long jCurrentTimeMillis = System.currentTimeMillis();
            long jConvert = TimeUnit.MILLISECONDS.convert(i, TimeUnit.SECONDS) + jCurrentTimeMillis;
            map2.put(TokenKeys.getAccessTokenKeyForPackage(null), string);
            map2.put(com.amazon.identity.auth.device.e.a((String) null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT), Long.toString(jConvert));
            map2.put(com.amazon.identity.auth.device.e.a((String) null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN), string2);
            map2.put(com.amazon.identity.auth.device.e.a((String) null, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_REFRESHED_AT), Long.toString(jCurrentTimeMillis));
            mapEmptyMap = map2;
        }
        map.putAll(mapEmptyMap);
        String strA = t8.a(list);
        if (!TextUtils.isEmpty(strA)) {
            t8.b(list);
            ga.a("MAPCookieManager");
            map.put(AccountConstants.TOKEN_TYPE_COOKIE_MFA_SID_TOKEN, strA);
        }
        map.putAll(this.n.a(str, list, str2, (String) null));
        return map;
    }

    public final CORPFMResponse a(Bundle bundle) {
        String string = bundle.getString(AccountConstants.KEY_COR);
        String string2 = bundle.getString(AccountConstants.KEY_COR_SOURCE);
        String string3 = bundle.getString(AccountConstants.KEY_PFM);
        bundle.remove(AccountConstants.KEY_COR);
        bundle.remove(AccountConstants.KEY_COR_SOURCE);
        bundle.remove(AccountConstants.KEY_PFM);
        String str = s1.i;
        if (!((string == null || TextUtils.isEmpty(string2) || string3 == null) ? false : true)) {
            Log.i(ga.a(r), "Registering account did not return cor/pfm.");
            return null;
        }
        Log.i(ga.a(r), String.format("Using COR/SourceOfCor/PFM/ returned when registering the account: %s, %s, %s", string, string2, string3));
        this.p.getClass();
        return new CORPFMResponse(string, string3, string2, Long.valueOf(System.currentTimeMillis()));
    }

    public final void a(Set<String> set, Callback callback, y5 y5Var, Bundle bundle) {
        Log.i(ga.a(r), "Deregister all accounts initiated");
        for (String str : set) {
            if (this.e.a(str)) {
                try {
                    s2 s2Var = new s2(null);
                    b(str, s2Var, y5Var, bundle);
                    if (!xa.a()) {
                        s2Var.get();
                    }
                } catch (MAPCallbackErrorException e2) {
                    Log.e(ga.a(r), "MAP Error calling deregister. Error: " + com.amazon.identity.auth.device.e.c(e2.getErrorBundle()), e2);
                } catch (InterruptedException e3) {
                    Log.e(ga.a(r), "InterruptedException calling deregister.", e3);
                } catch (ExecutionException e4) {
                    Log.e(ga.a(r), "ExecutionException calling deregister", e4);
                }
            }
        }
        a(callback, true);
    }

    public final void a(Callback callback, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("booleanResult", true);
        bundle.putBoolean(MAPAccountManager.KEY_SERVER_SIDE_DEREGISTRATION_RESULT, z);
        callback.onSuccess(bundle);
    }

    public final void a(String str, String str2, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        BackwardsCompatiableDataStorage backwardsCompatiableDataStorage = new BackwardsCompatiableDataStorage(this.f335a, this.i);
        String strD = backwardsCompatiableDataStorage.d(str, "com.amazon.dcp.sso.property.devicename");
        String strD2 = backwardsCompatiableDataStorage.d(str, "com.amazon.dcp.sso.property.deviceemail");
        if (str2 == null) {
            o7 o7Var = new o7(str, null, null, null);
            for (String str3 : bundle.keySet()) {
                o7Var.b.put(str3, bundle.getString(str3));
            }
            backwardsCompatiableDataStorage.a(o7Var);
        } else {
            c8 c8Var = new c8(this.f335a, backwardsCompatiableDataStorage);
            if (!TextUtils.isEmpty(str)) {
                HashMap map = new HashMap();
                HashMap map2 = new HashMap();
                for (String str4 : bundle.keySet()) {
                    map.put(com.amazon.identity.auth.device.e.a(str2, str4), bundle.getString(str4));
                }
                c8Var.b.a(new o7(str, c8Var.c(str, map), c8Var.c(str, map2), null));
            } else {
                throw new IllegalArgumentException("AccountData directedId cannot be null");
            }
        }
        String strD3 = backwardsCompatiableDataStorage.d(str, "com.amazon.dcp.sso.property.devicename");
        String strD4 = backwardsCompatiableDataStorage.d(str, "com.amazon.dcp.sso.property.deviceemail");
        if (!TextUtils.equals(strD, strD3) && z9.d(this.f335a, str2)) {
            ga.a(r);
            w1.b(this.f335a, str, strD3);
        }
        if (TextUtils.equals(strD2, strD4)) {
            return;
        }
        t5 t5Var = this.f335a;
        if (TextUtils.equals(z9.c(t5Var, str2), z9.c(t5Var, "com.amazon.kindle"))) {
            ga.a(r);
            w1.a(this.f335a, str, strD4);
        }
    }

    public final void a(MAPError mAPError, String str, MAPAccountManager.RegistrationError registrationError, Callback callback, Bundle bundle, String str2) {
        Log.e(ga.a(r), "Error msg:" + str2);
        y.a(callback, mAPError, str, registrationError.value(), str2, bundle);
    }

    @Override // com.amazon.identity.auth.device.f
    public boolean a() {
        return this.e.e();
    }

    @Override // com.amazon.identity.auth.device.f
    public boolean a(String str) {
        return this.e.c().contains(str);
    }

    @Override // com.amazon.identity.auth.device.f
    public void a(Context context, Bundle bundle, Bundle bundle2, Callback callback, y5 y5Var) {
        if (!bundle2.containsKey(MAPAccountManager.KEY_LINK_CODE) && !bundle2.containsKey(MAPAccountManager.KEY_PRE_AUTHORIZED_LINK_CODE)) {
            a(context, SigninOption.WebviewConfirmCredentials, bundle2, callback, (y5) null);
        } else {
            a(RegistrationType.WITH_LINK_CODE, bundle2, callback, y5Var);
        }
    }

    @Override // com.amazon.identity.auth.device.f
    public void a(Activity activity, String str, Bundle bundle, Callback callback, y5 y5Var) {
        va.f637a.execute(new d(bundle, str, callback, y5Var, activity));
    }

    public final boolean a(Callback callback) {
        if (!PlatformSettings.a(this.f335a).a("ignore.deregister", false).booleanValue()) {
            return false;
        }
        Log.i(ga.a(r), "Ignoring deregister based on system property ignore.deregister");
        Bundle bundle = new Bundle();
        bundle.putBoolean("booleanResult", false);
        bundle.putBoolean(MAPAccountManager.KEY_SERVER_SIDE_DEREGISTRATION_RESULT, false);
        callback.onSuccess(bundle);
        return true;
    }
}
