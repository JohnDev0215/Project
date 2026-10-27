package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPCallbackErrorException;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.identity.auth.device.f7;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import java.io.IOException;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class z {
    public static final String c = "com.amazon.identity.auth.device.z";
    public static final a d = new a(null, null, null, new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.CredentialError, "Credential Error", "Credential Error", "Credential Error", "No Request Id"), null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t5 f686a;
    public final AuthEndpointErrorParser b;

    /* JADX INFO: compiled from: DCP */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f687a;
        public final String b;
        public final ob c;
        public final AuthEndpointErrorParser.a d;
        public final d7 e;

        public a(String str, String str2, ob obVar, AuthEndpointErrorParser.a aVar, d7 d7Var) {
            this.f687a = str;
            this.b = str2;
            this.c = obVar;
            this.d = aVar;
            this.e = d7Var;
        }
    }

    public z(Context context) {
        this(t5.a(context), new AuthEndpointErrorParser());
    }

    public Bundle a(Bundle bundle, y5 y5Var) throws MAPCallbackErrorException {
        a aVar;
        a aVar2;
        String str;
        String string = bundle.getString(MAPAccountManager.KEY_AMAZON_ACCOUNT_LOGIN_NAME);
        String string2 = bundle.getString("password");
        String string3 = bundle.getString("com.amazon.dcp.sso.property.account.acctId");
        if (TextUtils.isEmpty(string2) || (TextUtils.isEmpty(string) && TextUtils.isEmpty(string3))) {
            MAPError.CommonError commonError = MAPError.CommonError.BAD_REQUEST;
            throw new MAPCallbackErrorException(y.b(commonError, "A login/directedId and password are required to authenticate/confirmCredential.", MAPAccountManager.RegistrationError.BAD_REQUEST.value(), "A login/directedId and password are required to authenticate/confirmCredential."), commonError, "A login/directedId and password are required to authenticate/confirmCredential.");
        }
        try {
            j7 j7VarA = a(bundle);
            f7.a aVarC = j7VarA.c(y5Var);
            Integer num = aVarC.d;
            if (num == null) {
                try {
                    a aVarA = a(aVarC);
                    AuthEndpointErrorParser.a aVar3 = aVarA.d;
                    if (aVar3 != null) {
                        j7VarA.a(aVarC.b, aVar3.f320a.getCode());
                    } else {
                        j7VarA.a(aVarC.b, (String) null);
                    }
                    aVar2 = aVarA;
                } catch (JSONException e) {
                    y5Var.a(e.b(j7VarA.g()), 0.0d);
                    String str2 = String.format(Locale.US, "Error parsing Panda sign-in response. Not of an expected format. Error: %s", e.getMessage());
                    Log.e(ga.a(c), str2);
                    aVar2 = new a(null, null, null, new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.ParseError, str2, null, null, null), null);
                }
            } else if (num.intValue() == 0) {
                aVar2 = d;
            } else {
                aVar2 = new a(null, null, null, new AuthEndpointErrorParser.a((aVarC.d.intValue() == 1 && (aVarC.c instanceof IOException)) ? AuthEndpointErrorParser.AuthErrorType.ServiceUnavailable : (aVarC.d.intValue() == 2 && (aVarC.c instanceof IOException)) ? AuthEndpointErrorParser.AuthErrorType.NetworkFailure : aVarC.d.intValue() == 3 ? AuthEndpointErrorParser.AuthErrorType.ParseError : AuthEndpointErrorParser.AuthErrorType.GenericError, "Error getting response from server", null, null, null), null);
            }
        } catch (MAPCallbackErrorException e2) {
            d7 d7VarA = d7.a(e2);
            if (d7VarA != null) {
                aVar2 = new a(null, null, null, new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.InvalidToken, "MAP internally can't get access_token for authentication", null, null, null), d7VarA);
            } else {
                aVar = new a(null, null, null, new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.GenericError, "MAP internally can't get access_token for authentication", null, null, null), null);
                aVar2 = aVar;
            }
        } catch (Exception unused) {
            aVar = new a(null, null, null, new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.GenericError, "MAP internally can't get access_token for authentication", null, null, null), null);
            aVar2 = aVar;
        }
        AuthEndpointErrorParser.a aVar4 = aVar2.d;
        if (!(aVar4 != null)) {
            yd.a("PandaService:SignIn:Success", new String[0]);
            Bundle bundle2 = new Bundle();
            bundle2.putString("com.amazon.dcp.sso.property.account.acctId", aVar2.b);
            bundle2.putString("com.amazon.dcp.sso.AddAccount.options.AccessToken", aVar2.f687a);
            return bundle2;
        }
        AuthEndpointErrorParser.AuthErrorType authErrorType = aVar4.f320a;
        ob obVar = aVar2.c;
        Bundle bundleB = y.b(authErrorType.getError(), authErrorType.getErrorMessage(), authErrorType.getRegistrationError().value(), authErrorType.getCode());
        if (obVar != null) {
            if (aVar2.d.f320a == AuthEndpointErrorParser.AuthErrorType.CredentialError && (str = obVar.e) != null) {
                bundleB.putString(MAPAccountManager.KEY_AUTH_DATA_ADDITIONAL_INFO, str);
            }
            bundleB.putBundle(MAPAccountManager.KEY_AUTHENTICATION_CHALLENGE, obVar.a());
        }
        d7 d7Var = aVar2.e;
        if (d7Var != null) {
            bundleB.putAll(d7Var.c());
        }
        yd.a("PandaService:SignIn:" + authErrorType.getCode(), new String[0]);
        throw new MAPCallbackErrorException(bundleB, authErrorType.getError(), authErrorType.getErrorMessage());
    }

    public z(t5 t5Var, AuthEndpointErrorParser authEndpointErrorParser) {
        this.f686a = t5Var;
        this.b = authEndpointErrorParser;
    }

    public j7 a(Bundle bundle) throws Exception {
        String string = bundle.getString("com.amazon.dcp.sso.property.account.acctId");
        if (!TextUtils.isEmpty(string) && (!string.startsWith("amzn") || string.contains("-"))) {
            Log.i(ga.a(c), "Legacy device with non-standard directed id.");
            try {
                bundle.putString("com.amazon.dcp.sso.token.oauth.amazon.access_token", new TokenManagement(this.f686a).getToken(string, "com.amazon.dcp.sso.token.oauth.amazon.access_token", null, null).get().getString("value_key"));
            } catch (Exception e) {
                Log.e(ga.a(c), "Can't get the access_token for authentication", e);
                throw e;
            }
        }
        return new j7(bundle, this.f686a);
    }

    public final a a(f7.a aVar) throws JSONException {
        AuthEndpointErrorParser.a aVar2;
        boolean zHas;
        JSONObject jSONObject = aVar.f312a;
        Integer num = aVar.b;
        if (jSONObject == null) {
            Log.e(ga.a(c), "Error parsing JSON in Panda response");
            return new a(null, null, null, new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.ParseError, "Error parsing JSON in Panda response", null, null, null), null);
        }
        if (AuthEndpointErrorParser.a(num)) {
            this.b.getClass();
            try {
                zHas = jSONObject.getJSONObject("response").has("challenge");
            } catch (JSONException unused) {
                zHas = false;
            }
            if (!zHas) {
                AuthEndpointErrorParser.a aVarB = this.b.b(jSONObject);
                if (aVarB == null) {
                    aVarB = AuthEndpointErrorParser.f319a;
                }
                AuthEndpointErrorParser.a aVar3 = aVarB;
                ga.b(c, "Error making request. Code: %s \n Message: %s \n Detail: %s \n Index: %s", aVar3.f320a.getCode(), aVar3.b, aVar3.c, aVar3.d);
                return new a(null, null, null, aVar3, null);
            }
        }
        String string = jSONObject.getString("request_id");
        String str = c;
        Log.i(ga.a(str), String.format("Request to panda signin API with request id %s", string));
        JSONObject jSONObject2 = jSONObject.getJSONObject("response");
        if (jSONObject2.has("success")) {
            JSONObject jSONObject3 = jSONObject2.getJSONObject("success");
            return new a(jSONObject3.getJSONObject("tokens").getJSONObject("bearer").getString("access_token"), jSONObject3.getString("customer_id"), null, null, null);
        }
        if (jSONObject2.has("challenge")) {
            ob obVarA = ob.a(jSONObject2.getJSONObject("challenge"));
            String strA = da.a(jSONObject2, "request_id", (String) null);
            String str2 = obVarA.f480a;
            if (!"AuthenticationFailed".equals(str2) && !"InvalidAuthenticationData".equals(str2)) {
                aVar2 = new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.AuthenticationChallenged, null, null, null, strA);
            } else {
                aVar2 = new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.CredentialError, null, null, null, strA);
            }
            return new a(null, null, obVarA, aVar2, null);
        }
        Log.e(ga.a(str), "Error parsing response. Empty response body.");
        return new a(null, null, null, new AuthEndpointErrorParser.a(AuthEndpointErrorParser.AuthErrorType.ParseError, "Error parsing response. Empty response body.", null, null, null), null);
    }
}
