package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.token.CentralTokenManagementCommunication;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class v8 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f635a;
    public final qa b;
    public final ha c;
    public final o5 d;

    public v8(t5 t5Var, qa qaVar) {
        this.f635a = t5Var;
        this.b = qaVar;
        this.d = (o5) t5Var.getSystemService("dcp_device_info");
        this.c = new ha(t5Var);
    }

    public boolean a(Integer num) {
        this.b.getClass();
        return AuthEndpointErrorParser.a(num);
    }

    public HttpURLConnection b(String str, String str2, String str3, y5 y5Var) throws JSONException, IOException {
        try {
            URL urlA = EnvironmentUtils.c.a(q9.a(this.f635a, str2), "/auth/token");
            Log.i(ga.a("PandaOAuthExchangeRequestHelper"), "Refreshing Normal OAuth token with exchange token endpoint " + urlA.toString() + " due to " + y5Var.a(this.f635a));
            return this.b.a((Context) this.f635a, urlA, a("refresh_token", str, "access_token", y5Var), false, (List<MAPCookie>) null, str2, str3, y5Var);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Should never occur, hardcoded constant.", e);
        }
    }

    public AuthEndpointErrorParser.a c(JSONObject jSONObject) {
        if (jSONObject == null) {
            ga.a("PandaOAuthExchangeRequestHelper");
            return null;
        }
        try {
            String string = jSONObject.getString("error_description");
            String string2 = jSONObject.getString("error");
            return new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.getAuthErrorTypeFromCode(string2), string, null, da.a(jSONObject, "error_index", (String) null), null);
        } catch (JSONException unused) {
            ga.a("PandaOAuthExchangeRequestHelper");
            return new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.ParseError, "Unable to parse response JSON.", null, null, null);
        }
    }

    public OAuthTokenManager.a d(JSONObject jSONObject) throws JSONException, ParseException {
        int i = jSONObject.getInt("expires_in");
        String string = jSONObject.getString(CentralTokenManagementCommunication.GetTokenForActorCommand.KEY_TOKEN_TYPE);
        String string2 = jSONObject.getString("access_token");
        String strOptString = jSONObject.optString("refresh_token", null);
        if (!"bearer".equals(string)) {
            throw new ParseException("Unexpected token type.", 0);
        }
        if (string2 != null) {
            return new OAuthTokenManager.a(string2, i, strOptString, null, null);
        }
        throw new ParseException("Incomplete response.", 0);
    }

    public OAuthTokenManager.a[] b(JSONObject jSONObject) throws OAuthTokenManager.OAuthTokenManagerException, ParseException {
        String strA = "OpenInWebView".equals(da.a(da.a(jSONObject, "ui_action"), "next_step", (String) null)) ? da.a(da.a(jSONObject, "ui_action"), ImagesContract.URL, (String) null) : null;
        try {
            if ("bearer".equals(jSONObject.getString(CentralTokenManagementCommunication.GetTokenForActorCommand.KEY_TOKEN_TYPE))) {
                JSONArray jSONArray = jSONObject.getJSONArray("device_tokens");
                if (jSONArray != null && jSONArray.length() != 0) {
                    int length = jSONArray.length();
                    OAuthTokenManager.a[] aVarArr = new OAuthTokenManager.a[length];
                    for (int i = 0; i < length; i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        aVarArr[i] = new OAuthTokenManager.a(jSONObject2.getJSONObject("actor_access_token").getString("token"), jSONObject2.getJSONObject("actor_access_token").getInt("expires_in"), a(jSONObject2, "account_refresh_token", "token", "Account refresh token"), a(jSONObject2, "actor_refresh_token", "token", "Actor refresh token"), jSONObject2.getString(CentralAccountManagerCommunication.RegisterChildApplication.KEY_DEVICE_TYPE));
                    }
                    return aVarArr;
                }
                throw new ParseException("Incomplete response, device tokens is null.", 0);
            }
            throw new ParseException("Unexpected token type.", 0);
        } catch (Exception e) {
            if (TextUtils.isEmpty(strA)) {
                throw new ParseException(e.getMessage(), 0);
            }
            throw new OAuthTokenManager.OAuthTokenManagerException(MAPError.AccountError.AUTHENTICATION_CHALLENGED, strA, MAPAccountManager.RegistrationError.AUTHENTICATION_CHALLENGED.value(), strA);
        }
    }

    public JSONObject a(String str, String str2, String str3, y5 y5Var) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        a(jSONObject);
        jSONObject.put("source_token_type", str);
        jSONObject.put("source_token", str2);
        jSONObject.put("requested_token_type", str3);
        if (y5Var != null && qe.f(this.f635a)) {
            jSONObject.put("calling_package_name", y5Var.a(this.f635a));
        }
        t5 t5Var = this.f635a;
        jSONObject.put("device_metadata", a7.a(z9.c(t5Var, t5Var.getPackageName()), this.d.a(), y5Var));
        jSONObject.putOpt("map_version", this.c.a());
        return jSONObject;
    }

    public final void a(JSONObject jSONObject) throws JSONException {
        jSONObject.put("app_name", this.f635a.getPackageName());
        jSONObject.put("app_version", s9.b());
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public HttpURLConnection a(String str, String str2, String str3, String str4, String str5, String str6, String str7, @NonNull Bundle bundle, y5 y5Var) throws JSONException, IOException {
        try {
            URL urlA = EnvironmentUtils.c.a(q9.a(this.f635a, str4), "/auth/token");
            Log.i(ga.a("PandaOAuthExchangeRequestHelper"), "Refreshing Actor OAuth token with exchange token endpoint " + urlA.toString() + " due to " + y5Var.a(this.f635a));
            Object obj = null;
            List<MAPCookie> listA = TextUtils.isEmpty(str7) ? null : v9.a(bundle.getBundle("actor_cookies_for_request"), str4);
            qa qaVar = this.b;
            t5 t5Var = this.f635a;
            JSONObject jSONObject = new JSONObject();
            a(jSONObject);
            jSONObject.put("source_token_type", "refresh_token");
            jSONObject.put("requested_token_type", "actor_access_token");
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(a(str, str3, null));
            if (str2 != null && !str2.equals(str) && !TextUtils.isEmpty(str6)) {
                jSONArray.put(a(str2, null, str6));
            }
            jSONObject.put("source_device_tokens", jSONArray);
            jSONObject.put("actor_id", str5);
            if (!TextUtils.isEmpty(bundle.getString("program"))) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("program", bundle.getString("program")).put("app_platform", "android").put("app_identifier", bundle.getString("app_identifier"));
                    obj = jSONObject2;
                } catch (JSONException unused) {
                    Log.e(ga.a("PandaOAuthExchangeRequestHelper"), "Cannot build switchContextJSon, return null.");
                    obj = null;
                }
            }
            jSONObject.putOpt("actor_switch_context", obj);
            if (!TextUtils.isEmpty(str7)) {
                jSONObject.put("token_validation_failure_context", str7);
                Bundle bundle2 = bundle.getBundle("auth_portal_config");
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("client_config", bundle2.getString(TokenKeys.Options.KEY_CHALLENGE_URL_ASSOC_HANDLE));
                jSONObject3.put("ui_config", bundle2.getString(TokenKeys.Options.KEY_CHALLENGE_URL_PAGE_ID));
                jSONObject3.put("domain", bundle2.getString(TokenKeys.Options.KEY_CHALLENGE_URL_FULL_DOMAIN));
                jSONObject3.put("redirect_uri", OpenIdRequest.b(bundle2.getString(TokenKeys.Options.KEY_OVERRIDE_CHALLENGE_URL_RETURNTO_FULL_DOMAIN)));
                jSONObject3.put("cancel_uri", OpenIdRequest.a(bundle2.getString(TokenKeys.Options.KEY_OVERRIDE_CHALLENGE_URL_RETURNTO_FULL_DOMAIN)));
                jSONObject.put("auth_portal_config", jSONObject3);
                jSONObject.put("ui_capacity", MAPWebviewActivityTemplate.f());
            }
            jSONObject.toString();
            ga.a("PandaOAuthExchangeRequestHelper");
            return qaVar.a((Context) t5Var, urlA, jSONObject, false, listA, str4, str6, y5Var);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Should never occur, hardcoded constant.", e);
        }
    }

    public final JSONObject a(String str, String str2, String str3) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (TextUtils.isEmpty(str3)) {
            ga.a("PandaOAuthExchangeRequestHelper");
            jSONObject.put(CentralAccountManagerCommunication.RegisterChildApplication.KEY_DEVICE_TYPE, z9.b(this.f635a));
        } else {
            ga.a("PandaOAuthExchangeRequestHelper");
            jSONObject.put(CentralAccountManagerCommunication.RegisterChildApplication.KEY_DEVICE_TYPE, z9.c(this.f635a, str3));
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("token", str);
        jSONObject.put("account_refresh_token", jSONObject2);
        if (!TextUtils.isEmpty(str2)) {
            jSONObject.put("actor_refresh_token", new JSONObject().put("token", str2));
        }
        jSONObject.toString();
        ga.a("PandaOAuthExchangeRequestHelper");
        return jSONObject;
    }

    public static String a(JSONObject jSONObject, String str, String str2, String str3) {
        try {
            return jSONObject.getJSONObject(str).getString(str2);
        } catch (Exception unused) {
            Log.w(ga.a("PandaOAuthExchangeRequestHelper"), String.format("%s is not set in the response, ignoring...", str3));
            return null;
        }
    }
}
