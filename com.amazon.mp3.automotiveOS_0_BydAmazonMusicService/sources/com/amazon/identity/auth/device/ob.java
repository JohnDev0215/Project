package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class ob {
    public static final String f = "com.amazon.identity.auth.device.ob";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f480a;
    public String b;
    public String c;
    public String d;
    public String e;

    public ob(String str, String str2, String str3, String str4, String str5) {
        this.f480a = str;
        this.c = str3;
        this.b = a(str2);
        this.d = str4;
        this.e = str5;
    }

    public static ob a(JSONObject jSONObject) throws JSONException {
        return new ob(jSONObject.getString("challenge_reason"), jSONObject.optString("uri", null), jSONObject.optString("challenge_context", null), jSONObject.optString("required_authentication_method", null), jSONObject.optString(MAPAccountManager.KEY_AUTH_DATA_ADDITIONAL_INFO, null));
    }

    public Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString("com.amazon.identity.auth.ChallengeException.Reason", this.f480a);
        bundle.putString("com.amazon.identity.auth.ChallengeException.oAuthURI", this.b);
        bundle.putString("com.amazon.identity.auth.ChallengeException.Context", this.c);
        bundle.putString("com.amazon.identity.auth.ChallengeException.requiredAuthenticationMethod", this.d);
        bundle.putString(MAPAccountManager.KEY_AUTH_DATA_ADDITIONAL_INFO, this.e);
        return bundle;
    }

    public final String a(String str) {
        try {
            return new URL(str).toString();
        } catch (MalformedURLException unused) {
            Log.w(ga.a(f), " Malformed URL received: " + str);
            return null;
        }
    }
}
