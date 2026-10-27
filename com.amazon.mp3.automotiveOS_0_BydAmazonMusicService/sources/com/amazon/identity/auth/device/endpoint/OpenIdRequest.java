package com.amazon.identity.auth.device.endpoint;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.api.AccountStatusPolicy;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class OpenIdRequest {
    public static final String g = "com.amazon.identity.auth.device.endpoint.OpenIdRequest";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map<String, String> f304a;
    public final REQUEST_TYPE b;
    public final Bundle c;
    public String d;
    public final Uri.Builder e;
    public Map<String, String> f;

    /* JADX INFO: compiled from: DCP */
    public enum REQUEST_TYPE {
        SIGN_IN,
        REGISTER,
        CONFIRM_CREDENTIAL,
        AUTHENTICATE,
        FORGOT_PASSWORD,
        CALLBACK_FOR_3P_LOGIN,
        CNEP
    }

    /* JADX INFO: compiled from: DCP */
    public enum TOKEN_SCOPE {
        ACCESS,
        REFRESH
    }

    public OpenIdRequest(String str, REQUEST_TYPE request_type, Bundle bundle) throws IllegalArgumentException, NullPointerException {
        HashMap map = new HashMap();
        this.f304a = map;
        this.b = request_type;
        Bundle bundle2 = bundle != null ? bundle : new Bundle();
        this.c = bundle2;
        Uri uriB = b(bundle2);
        if (uriB == null) {
            this.e = new Uri.Builder();
        } else {
            this.e = uriB.buildUpon();
        }
        this.e.encodedAuthority(a(uriB));
        this.e.scheme("https");
        if (TextUtils.isEmpty(this.e.build().getPath())) {
            if (request_type == REQUEST_TYPE.REGISTER) {
                this.e.path("/ap/register");
            } else if (request_type == REQUEST_TYPE.FORGOT_PASSWORD) {
                this.e.path("/ap/forgotpassword");
            } else if (request_type == REQUEST_TYPE.CNEP) {
                this.e.path("/ap/cnep");
            } else if (request_type == REQUEST_TYPE.CALLBACK_FOR_3P_LOGIN) {
                this.e.path("/ap/3p_callback");
            } else {
                this.e.path("/ap/signin");
            }
        }
        c("amzn_device_na");
        map.put("language", e.a());
        if (bundle2.containsKey("app_name")) {
            map.put("app_name", bundle2.getString("app_name"));
        }
        if (bundle2.containsKey("domain_hint")) {
            map.put("domain_hint", bundle2.getString("domain_hint"));
        }
        if (bundle2.containsKey("ab_federated_auth")) {
            map.put("ab_federated_auth", bundle2.getString("ab_federated_auth"));
        }
        c(bundle);
        map.put("openid.ns", "http://specs.openid.net/auth/2.0");
        map.put("openid.mode", "checkid_setup");
        map.put("openid.claimed_id", "http://specs.openid.net/auth/2.0/identifier_select");
        map.put("openid.identity", "http://specs.openid.net/auth/2.0/identifier_select");
        map.put("openid.ns.pape", "http://specs.openid.net/extensions/pape/1.0");
        if (bundle2.getBoolean("use_code_response_type")) {
            map.put("openid.oa2.response_type", "code");
            map.put("openid.oa2.code_challenge", bundle2.getString(AccountConstants.KEY_CODE_CHALLENGE));
            map.put("openid.oa2.code_challenge_method", bundle2.getString(AccountConstants.KEY_CODE_CHALLENGE_METHOD));
        } else {
            map.put("openid.oa2.response_type", "token");
        }
        map.put("openid.ns.oa2", "http://www.amazon.com/ap/ext/oauth/2");
        map.put("accountStatusPolicy", a(bundle2));
        map.put("openid.oa2.scope", "device_auth_access");
        if (!bundle2.getBoolean("isAccountStateFixUpFlow")) {
            f("0");
        }
        g(b());
        if (!TextUtils.isEmpty(str)) {
            e(str);
        }
        ga.a(g, "OpenIdRequest created with reqType=" + request_type + " host=" + c());
    }

    public final String a(Bundle bundle) {
        AccountStatusPolicy accountStatusPolicy = (AccountStatusPolicy) bundle.getParcelable(MAPAccountManager.KEY_ACCOUNT_STATUS_POLICY);
        return AccountStatusPolicy.Policy.DEFAULT.equals(accountStatusPolicy != null ? accountStatusPolicy.getAccountStatusPolicy() : null) ? "DEFAULT" : "P1";
    }

    public String b() {
        String string = this.c.getString(MAPAccountManager.KEY_OVERRIDE_RETURN_TO_DOMAIN);
        String strA = !TextUtils.isEmpty(string) ? EnvironmentUtils.c.a(string) : c();
        Uri.Builder builder = new Uri.Builder();
        if (this.c.containsKey("ab_federated_auth") && this.c.containsKey("domain_hint") && this.c.containsKey("app_name")) {
            builder.scheme(this.c.getString("app_name"));
        } else {
            builder.scheme("https");
        }
        Map<String, String> map = EnvironmentUtils.f306a;
        builder.authority(strA);
        builder.path("/ap/maplanding");
        return builder.build().toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(android.os.Bundle r6) throws java.lang.IllegalArgumentException {
        /*
            Method dump skipped, instruction units count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.endpoint.OpenIdRequest.c(android.os.Bundle):void");
    }

    public void d(String str) {
        this.f304a.put("openid.claimed_id", str);
        this.f304a.put("openid.identity", str);
    }

    public void e(String str) {
        this.f304a.put("openid.oa2.client_id", "device:" + str);
    }

    public void f(String str) {
        this.f304a.put("openid.pape.max_auth_age", str);
    }

    public void g(String str) {
        this.d = str;
        this.f304a.put("openid.return_to", str);
    }

    public String d() {
        Uri.Builder builderBuildUpon = this.e.build().buildUpon();
        if (this.c.getBoolean(MAPAccountManager.KEY_IS_CALLBACK_FROM_3P_PARAM)) {
            builderBuildUpon.encodedQuery(this.c.getString(MAPAccountManager.KEY_3P_CALLBACK_QUERY_PARAM));
        } else {
            Map<String, String> map = this.f;
            if (map != null && map.size() > 0) {
                this.f304a.putAll(this.f);
                this.f.clear();
            }
            for (Map.Entry<String, String> entry : this.f304a.entrySet()) {
                builderBuildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        Uri uriBuild = builderBuildUpon.build();
        String queryParameter = uriBuild.getQueryParameter("openid.oa2.code_challenge");
        String queryParameter2 = uriBuild.getQueryParameter("openid.oa2.code_challenge_method");
        if (!TextUtils.equals(queryParameter, this.f304a.get("openid.oa2.code_challenge")) || !TextUtils.equals(queryParameter2, this.f304a.get("openid.oa2.code_challenge_method"))) {
            Set<String> queryParameterNames = uriBuild.getQueryParameterNames();
            Uri.Builder builderClearQuery = uriBuild.buildUpon().clearQuery();
            for (String str : queryParameterNames) {
                if (TextUtils.equals(str, "openid.oa2.code_challenge")) {
                    if (!TextUtils.isEmpty(this.f304a.get("openid.oa2.code_challenge"))) {
                        builderClearQuery.appendQueryParameter(str, this.f304a.get("openid.oa2.code_challenge"));
                    } else {
                        ga.a(g);
                    }
                } else if (TextUtils.equals(str, "openid.oa2.code_challenge_method")) {
                    if (!TextUtils.isEmpty(this.f304a.get("openid.oa2.code_challenge_method"))) {
                        builderClearQuery.appendQueryParameter(str, this.f304a.get("openid.oa2.code_challenge_method"));
                    } else {
                        ga.a(g);
                    }
                } else {
                    builderClearQuery.appendQueryParameter(str, uriBuild.getQueryParameter(str));
                }
            }
            uriBuild = builderClearQuery.build();
        }
        return uriBuild.toString();
    }

    public final String a(Uri uri) throws NullPointerException {
        String authority = (uri == null || TextUtils.isEmpty(uri.getHost())) ? null : uri.getAuthority();
        if (TextUtils.isEmpty(authority) || TextUtils.isEmpty(authority.trim())) {
            authority = EnvironmentUtils.c.b(this.c);
            Log.i(ga.a(g), "AuthPortal Host: " + authority);
        }
        if (!TextUtils.isEmpty(authority) && !TextUtils.isEmpty(authority.trim())) {
            return authority.trim();
        }
        Log.e(ga.a(g), "An unexpected error has occurred! Received null for URI host. This should not happen");
        throw new NullPointerException("An unexpected error has occurred! Received null for URI host. This should not happen");
    }

    public String a() {
        Uri uriBuild = this.e.build();
        return String.format(Locale.US, "%s://%s", uriBuild.getScheme(), uriBuild.getAuthority());
    }

    public static String b(@NonNull String str) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        String strA = EnvironmentUtils.c.a(str);
        Map<String, String> map = EnvironmentUtils.f306a;
        builder.authority(strA);
        builder.path("/ap/maplanding");
        return builder.build().toString();
    }

    public static String a(@NonNull String str) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        String strA = EnvironmentUtils.c.a(str);
        Map<String, String> map = EnvironmentUtils.f306a;
        builder.authority(strA);
        builder.path("/ap/mapcancel");
        return builder.build().toString();
    }

    public static Uri b(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE);
        String string = bundle2 == null ? null : bundle2.getString("com.amazon.identity.auth.ChallengeException.oAuthURI");
        if (string == null) {
            string = bundle.getString(MAPAccountManager.KEY_RESUME_AUTHENTICATION_URL);
        }
        if (string == null) {
            return null;
        }
        if (URLUtil.isValidUrl(string) && URLUtil.isHttpsUrl(string)) {
            Uri uri = Uri.parse(string);
            if (uri.isAbsolute() && uri.isHierarchical() && !TextUtils.isEmpty(uri.getAuthority())) {
                if (!TextUtils.isEmpty(uri.getHost()) && !TextUtils.isEmpty(uri.getPath())) {
                    return uri;
                }
                Log.w(ga.a(g), "Unusable OpenID URL received");
                return null;
            }
            Log.w(ga.a(g), "Unusable OpenID URL received");
            return null;
        }
        Log.w(ga.a(g), "Unusable OpenID URL received");
        return null;
    }

    public static String a(String str, String str2) {
        return String.format("%x", new BigInteger(String.format("%s#%s", str, str2).getBytes()));
    }

    public void c(String str) {
        this.f304a.put("openid.assoc_handle", str);
    }

    public String c() {
        return this.e.build().getAuthority();
    }
}
