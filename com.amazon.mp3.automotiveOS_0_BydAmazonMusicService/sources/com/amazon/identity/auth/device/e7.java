package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import com.amazon.identity.auth.device.token.MAPCookie;
import com.google.firebase.perf.FirebasePerformance;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class e7 extends f7 {
    public final String d;
    public final String e;
    public final String f;
    public final Bundle g;
    public final Bundle h;

    /* JADX INFO: compiled from: DCP */
    public static final class a extends e7 {
        public a(t5 t5Var, String str, String str2, String str3, Bundle bundle, Bundle bundle2) {
            super(t5Var, str, str2, str3, bundle, bundle2);
        }

        @Override // com.amazon.identity.auth.device.f7
        public String h() {
            return "/auth/startActorEnrollment";
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static final class b extends e7 {
        public b(t5 t5Var, String str, String str2, String str3, Bundle bundle, Bundle bundle2) {
            super(t5Var, str, str2, str3, bundle, bundle2);
        }

        @Override // com.amazon.identity.auth.device.f7
        public String h() {
            return "/auth/startActorCreationAndEnrollment";
        }
    }

    public e7(t5 t5Var, String str, String str2, String str3, Bundle bundle, Bundle bundle2) {
        super(t5Var);
        this.d = str;
        this.e = str2;
        this.f = str3;
        this.g = bundle;
        this.h = bundle2;
    }

    @Override // com.amazon.identity.auth.device.f7
    public String a(JSONObject jSONObject) {
        return da.a(jSONObject, "error_index", (String) null);
    }

    @Override // com.amazon.identity.auth.device.f7
    public AuthenticationMethod b() {
        return null;
    }

    @Override // com.amazon.identity.auth.device.f7
    public JSONObject b(y5 y5Var) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("access_token", this.f);
        jSONObject.put("policy_handle", this.e);
        jSONObject.put("ui_capacity", MAPWebviewActivityTemplate.f());
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("domain", j());
        jSONObject2.put("client_config", i());
        String strI = i();
        Bundle bundle = this.g.getBundle(MAPAccountManager.AuthPortalOptions.KEY_REQUEST_PARAMETERS);
        if (bundle == null || TextUtils.isEmpty(bundle.getString("pageId"))) {
            Log.w(ga.a("PandaActorInvolvedApiCall"), "page_iid is not passed in, fall back to MAP's assoc_handle");
        } else {
            strI = bundle.getString("pageId");
        }
        jSONObject2.put("ui_config", strI);
        jSONObject2.put("redirect_uri", OpenIdRequest.b(TextUtils.isEmpty(this.g.getString(MAPAccountManager.KEY_OVERRIDE_RETURN_TO_DOMAIN)) ? j() : this.g.getString(MAPAccountManager.KEY_OVERRIDE_RETURN_TO_DOMAIN)));
        jSONObject2.put("cancel_uri", OpenIdRequest.a(TextUtils.isEmpty(this.g.getString(MAPAccountManager.KEY_OVERRIDE_RETURN_TO_DOMAIN)) ? j() : this.g.getString(MAPAccountManager.KEY_OVERRIDE_RETURN_TO_DOMAIN)));
        jSONObject.put("auth_portal_config", jSONObject2);
        return jSONObject;
    }

    @Override // com.amazon.identity.auth.device.f7
    public List<MAPCookie> c() {
        return v9.a(this.h, this.d);
    }

    @Override // com.amazon.identity.auth.device.f7
    public String d() {
        return q9.a(this.f311a, this.d);
    }

    @Override // com.amazon.identity.auth.device.f7
    public String e() {
        return FirebasePerformance.HttpMethod.POST;
    }

    @Override // com.amazon.identity.auth.device.f7
    public String f() {
        return q9.b(this.f311a, this.d);
    }

    public final String i() {
        Bundle bundle = this.g.getBundle(MAPAccountManager.AuthPortalOptions.KEY_REQUEST_PARAMETERS);
        if (bundle != null && !TextUtils.isEmpty(bundle.getString("openid.assoc_handle"))) {
            return bundle.getString("openid.assoc_handle");
        }
        Log.w(ga.a("PandaActorInvolvedApiCall"), "assoc_handle is not passed in, fall back to MAP's default: amzn_device_android");
        return "amzn_device_android";
    }

    public final String j() {
        return TextUtils.isEmpty(this.g.getString(MAPAccountManager.KEY_SIGN_IN_ENDPOINT)) ? q9.a((Context) this.f311a, this.d) : this.g.getString(MAPAccountManager.KEY_SIGN_IN_ENDPOINT);
    }
}
