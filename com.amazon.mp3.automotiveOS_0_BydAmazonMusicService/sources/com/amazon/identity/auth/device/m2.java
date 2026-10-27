package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.amazon.identity.auth.device.api.DefaultCallback;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class m2 extends j3 {
    public static final String i = "com.amazon.identity.auth.device.m2";
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final TokenManagement g;
    public final ArrayList<String> h;

    public m2(Context context, String str, String str2, String str3, TokenManagement tokenManagement, String str4, ArrayList<String> arrayList) {
        super(context);
        this.c = str4;
        this.d = str2;
        this.e = str3;
        this.f = str;
        this.g = tokenManagement;
        this.h = arrayList;
    }

    public JSONObject a() throws JSONException {
        String string;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, this.c);
        jSONObject2.put("appSignature", this.d);
        JSONObject jSONObject3 = new JSONObject();
        String str = this.f;
        try {
            string = this.g.getToken(str, AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN, new Bundle(), new DefaultCallback()).get().getString("value_key");
        } catch (Exception e) {
            Log.e(ga.a(i), "Exception while trying to get the refresh token in the authorizeLinkCode API", e);
            string = null;
        }
        jSONObject3.put("refreshToken", string);
        jSONObject3.put("appSignature", this.e);
        jSONObject.put("bootstrapAppInfo", jSONObject2);
        jSONObject.put("hostAppInfo", jSONObject3);
        if (!e.a(this.h)) {
            jSONObject.put("additionalData", new JSONArray((Collection<?>) this.h));
        }
        return jSONObject;
    }
}
