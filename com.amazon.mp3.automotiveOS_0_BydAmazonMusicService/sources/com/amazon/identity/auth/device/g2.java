package com.amazon.identity.auth.device;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.activity.ActorEnrollActivity;
import com.amazon.identity.auth.device.activity.ActorSignUpAndEnrollActivity;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPErrorCallbackHelper;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import com.amazon.identity.auth.device.f7;
import com.amazon.identity.auth.device.framework.AuthEndpointErrorParser;
import com.amazon.identity.auth.device.token.OAuthTokenManager;
import com.google.android.gms.common.internal.ImagesContract;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class g2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final OAuthTokenManager f346a;

    public g2(OAuthTokenManager oAuthTokenManager) {
        this.f346a = oAuthTokenManager;
    }

    public void a(s2 s2Var, f7.a aVar) {
        JSONObject jSONObject = aVar.f312a;
        if (jSONObject == null) {
            Log.e(ga.a("ActorSignUpAndEnrollHelper"), "No json in the response!");
            s2Var.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.PARSE_ERROR, "No json in the response!", true));
            return;
        }
        String strA = da.a(jSONObject, "error", "ParseError");
        String strA2 = da.a(aVar.f312a, "error_description", "Service returned known error.");
        int iOrdinal = AuthEndpointErrorParser.AuthErrorType.getAuthErrorTypeFromCode(strA).ordinal();
        if (iOrdinal == 2) {
            s2Var.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.ActorError.ACTOR_SIGNUP_FAILED, strA2, true));
            return;
        }
        if (iOrdinal == 18) {
            s2Var.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.ActorError.ACTOR_NOT_ASSOCIATED, strA2, false));
        } else if (iOrdinal != 20) {
            s2Var.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.PARSE_ERROR, strA2, true));
        } else {
            s2Var.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.BAD_REQUEST, strA2, false));
        }
    }

    public void a(Context context, String str, s2 s2Var, Bundle bundle, JSONObject jSONObject, y5 y5Var) {
        Intent intentA = ca.a(context, null, ActorSignUpAndEnrollActivity.class.getName());
        if (intentA != null) {
            if (y5Var != null) {
                y5Var.a(intentA);
            }
            try {
                bundle.putString("account_id", str);
                bundle.putString("load_url", a(jSONObject));
                bundle.putString("return_to_url", a(bundle));
                this.f346a.a(str, bundle.getString(MAPAccountManager.KEY_SIGN_IN_ENDPOINT), bundle, y5Var);
                intentA.putExtras(bundle);
                intentA.putExtra("callback", new RemoteCallbackWrapper(s2Var));
                if (!(context instanceof Activity)) {
                    intentA.addFlags(268435456);
                }
                context.startActivity(intentA);
                return;
            } catch (OAuthTokenManager.OAuthTokenManagerException e) {
                Log.e(ga.a("ActorSignUpAndEnrollHelper"), "Cannot get cookies before launching the signUpAndEnroll UI");
                MAPErrorCallbackHelper.onError(s2Var, e.e(), "Cannot get cookies before launching the signUpAndEnroll UI");
                return;
            } catch (JSONException unused) {
                MAPErrorCallbackHelper.onError(s2Var, MAPError.CommonError.PARSE_ERROR, "Cannot parse response");
                return;
            }
        }
        throw new RuntimeException("No activity can handle the intent. Probably because you do not declare ActorSignUpAndEnrollActivity in Android manifest");
    }

    public void a(Context context, String str, String str2, s2 s2Var, Bundle bundle, JSONObject jSONObject, y5 y5Var) {
        Intent intentA = ca.a(context, null, ActorEnrollActivity.class.getName());
        if (y5Var != null) {
            y5Var.a(intentA);
        }
        if (intentA != null) {
            try {
                bundle.putString("account_id", str);
                bundle.putString("load_url", a(jSONObject));
                bundle.putString("return_to_url", a(bundle));
                this.f346a.a(str, str2, bundle.getString(MAPAccountManager.KEY_SIGN_IN_ENDPOINT), bundle, y5Var);
                intentA.putExtras(bundle);
                intentA.putExtra("callback", new RemoteCallbackWrapper(s2Var));
                if (!(context instanceof Activity)) {
                    intentA.addFlags(268435456);
                }
                context.startActivity(intentA);
                return;
            } catch (OAuthTokenManager.OAuthTokenManagerException e) {
                Log.e(ga.a("ActorSignUpAndEnrollHelper"), "Cannot get cookies before launching the actor enroll UI");
                MAPErrorCallbackHelper.onError(s2Var, e.e(), "Cannot get cookies before launching the actor enroll UI");
                return;
            } catch (JSONException unused) {
                MAPErrorCallbackHelper.onError(s2Var, MAPError.CommonError.PARSE_ERROR, "Cannot parse response for enroll actor request.");
                return;
            }
        }
        throw new RuntimeException("No activity can handle the intent. Probably because you do not declare ActorEnrollActivity in Android manifest");
    }

    public final String a(JSONObject jSONObject) throws JSONException {
        try {
            return jSONObject.getJSONObject("ui_action").getString(ImagesContract.URL);
        } catch (Exception unused) {
            jSONObject.toString();
            ga.a("ActorSignUpAndEnrollHelper");
            throw new JSONException("Cannot get loading url from json response");
        }
    }

    public final String a(Bundle bundle) {
        String str = MAPAccountManager.KEY_OVERRIDE_RETURN_TO_DOMAIN;
        if (TextUtils.isEmpty(bundle.getString(MAPAccountManager.KEY_OVERRIDE_RETURN_TO_DOMAIN))) {
            str = MAPAccountManager.KEY_SIGN_IN_ENDPOINT;
        }
        return OpenIdRequest.b(bundle.getString(str));
    }
}
