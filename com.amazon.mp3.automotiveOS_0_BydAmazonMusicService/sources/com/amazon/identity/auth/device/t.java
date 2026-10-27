package com.amazon.identity.auth.device;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.p;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.kcpsdk.auth.RegisterDeviceErrorType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class t extends p.c {
    public final /* synthetic */ u c;
    public final /* synthetic */ String d;
    public final /* synthetic */ p.b e;
    public final /* synthetic */ String f;
    public final /* synthetic */ String g;
    public final /* synthetic */ p h;

    /* JADX INFO: compiled from: DCP */
    public class a implements Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RegisterDeviceErrorType f558a;

        public a(t tVar, RegisterDeviceErrorType registerDeviceErrorType) {
            this.f558a = registerDeviceErrorType;
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle bundle) {
            String str = p.m;
            Log.e(ga.a(str), "Got error while deregistering device in response to error : " + this.f558a.name());
            int i = bundle.getInt("com.amazon.dcp.sso.ErrorCode", -1);
            String string = bundle.getString("com.amazon.dcp.sso.ErrorMessage");
            Log.e(ga.a(str), "Error Code: " + i);
            Log.e(ga.a(str), "Error message: " + string);
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle bundle) {
            if (bundle.containsKey("com.amazon.dcp.sso.ErrorCode")) {
                onError(bundle);
                return;
            }
            Log.e(ga.a(p.m), "Finished deregistering device in response to error : " + this.f558a.name());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(p pVar, p.b bVar, MAPAccountManager mAPAccountManager, u uVar, String str, p.b bVar2, String str2, String str3) {
        super(bVar, mAPAccountManager);
        this.h = pVar;
        this.c = uVar;
        this.d = str;
        this.e = bVar2;
        this.f = str2;
        this.g = str3;
    }

    @Override // com.amazon.identity.auth.device.p.c
    public p.d a(kc kcVar) {
        String string;
        p.a(this.h, kcVar, this.c);
        if (this.h.a(kcVar)) {
            p.b(this.h, kcVar, this.c);
            Log.w(ga.a(p.m), "Cannot register the device, because anonymous credentials were returned from the server. FRO has either already been called or this device is not associated with an account.");
            return new p.d(MAPError.AccountError.REGISTER_FAILED, "Cannot register the device, because anonymous credentials were returned from the server. FRO has either already been called or this device is not associated with an account.", MAPAccountManager.RegistrationError.REGISTER_FAILED);
        }
        Bundle bundle = new Bundle();
        String str = this.d;
        if (str != null) {
            bundle.putString(AccountConstants.KEY_USER_EMAIL, str);
        }
        String str2 = kcVar.g;
        if (TextUtils.isEmpty(str2)) {
            str2 = " ";
        }
        bundle.putString("com.amazon.dcp.sso.property.username", str2);
        bundle.putString("com.amazon.dcp.sso.property.firstname", kcVar.h);
        bundle.putString("com.amazon.dcp.sso.property.devicename", kcVar.e);
        bundle.putString("com.amazon.dcp.sso.property.deviceemail", kcVar.i);
        bundle.putString(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN, kcVar.f409a);
        if (kcVar.f != null) {
            Log.i(ga.a(p.m), "Registration returned server generated credentials.");
            bundle.putString(AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY, kcVar.f);
        } else {
            this.e.a(MAPError.AccountError.REGISTER_FAILED, "No private key to set after register.", MAPAccountManager.RegistrationError.REGISTER_FAILED, "No private key to set after register.", null);
        }
        bundle.putString("com.amazon.dcp.sso.token.device.deviceserialname", this.f);
        bundle.putString("com.amazon.dcp.sso.token.devicedevicetype", this.g);
        bundle.putString("com.amazon.dcp.sso.property.account.acctId", kcVar.k);
        t7 t7Var = new t7(Collections.unmodifiableList(kcVar.t));
        t7Var.a();
        bundle.putString(AccountConstants.TOKEN_TYPE_COOKIE_XMAIN_TOKEN, t7Var.c);
        bundle.putString("com.amazon.dcp.sso.token.cookie.xmainAndXabcCookies", t7Var.b());
        bundle.putString("com.amazon.identity.cookies.xfsn", kcVar.j);
        bundle.putString("com.amazon.dcp.sso.token.device.accountpool", kcVar.n);
        bundle.putString(AccountConstants.KEY_CUSTOMER_REGION, kcVar.r);
        bundle.putString(AccountConstants.KEY_COR, kcVar.o);
        bundle.putString(AccountConstants.KEY_COR_SOURCE, kcVar.p);
        bundle.putString(AccountConstants.KEY_PFM, kcVar.q);
        HashMap map = new HashMap();
        map.putAll(kcVar.v);
        if (map.size() == 0) {
            string = null;
        } else {
            try {
                JSONObject jSONObject = new JSONObject();
                for (Map.Entry entry : map.entrySet()) {
                    JSONObject jSONObject2 = new JSONObject();
                    for (Map.Entry entry2 : ((Map) entry.getValue()).entrySet()) {
                        jSONObject2.putOpt((String) entry2.getKey(), entry2.getValue());
                    }
                    jSONObject.put((String) entry.getKey(), jSONObject2);
                }
                string = jSONObject.toString();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        if (string != null) {
            bundle.putString(AccountConstants.KEY_DEVICE_CREDENTIALS, string);
        }
        bundle.putString(AccountConstants.KEY_WEBSITE_COOKIES_JSON_ARRAY, kcVar.u.toString());
        bundle.putString("com.amazon.dcp.sso.token.oauth.amazon.access_token", kcVar.c);
        bundle.putString(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_ACCESS_TOKEN_EXPIRES_AT, Integer.toString(kcVar.d));
        bundle.putString(AccountConstants.TOKEN_TYPE_DEVICE_AMAZON_OAUTH_REFRESH_TOKEN, kcVar.b);
        bundle.putString(MAPAccountManager.KEY_IS_AMAZON_BUSINESS_ACCOUNT, String.valueOf(kcVar.x));
        if (!bundle.containsKey("com.amazon.dcp.sso.property.username")) {
            throw new IllegalArgumentException("UserData username is invalid because it is not fulfilled on DMS, please contact DMS to fulfill your device.");
        }
        if (!bundle.containsKey("com.amazon.dcp.sso.property.firstname")) {
            throw new IllegalArgumentException("UserData firstname is invalid because it is not fulfilled on DMS, please contact DMS to fulfill your device.");
        }
        if (!bundle.containsKey("com.amazon.dcp.sso.property.devicename")) {
            throw new IllegalArgumentException("UserData devicename is invalid because it is not fulfilled on DMS, please contact DMS to fulfill your device.");
        }
        if (!bundle.containsKey(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN)) {
            throw new IllegalArgumentException("UserData ADPtoken is invalid because it is not fulfilled on DMS, please contact DMS to fulfill your device.");
        }
        if (!bundle.containsKey(AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY)) {
            throw new IllegalArgumentException("UserData privatekey is invalid because it is not fulfilled on DMS, please contact DMS to fulfill your device.");
        }
        if (!bundle.containsKey("com.amazon.dcp.sso.token.device.deviceserialname")) {
            throw new IllegalArgumentException("UserData is invalid because the serial number has not been set");
        }
        if (bundle.containsKey("com.amazon.dcp.sso.token.devicedevicetype")) {
            return new p.d((Bundle) bundle.clone());
        }
        throw new IllegalArgumentException("UserData is invalid because the device type has not been set");
    }

    @Override // com.amazon.identity.auth.device.p.c
    public void a(RegisterDeviceErrorType registerDeviceErrorType) {
        if (registerDeviceErrorType == RegisterDeviceErrorType.RegisterDeviceErrorTypeDeviceNotRegisteredPanda || registerDeviceErrorType == RegisterDeviceErrorType.RegisterDeviceErrorTypePrimaryAccountDeregisteredWhenRegisterSecondary) {
            if (this.b == null) {
                Log.e(ga.a(p.m), "MAPAccountManager not initialized. Not able to deregister the device due to error " + registerDeviceErrorType.name());
                return;
            }
            if ("com.amazon.imp".equals(this.h.f482a.getApplicationContext().getPackageName())) {
                return;
            }
            this.b.deregisterDevice(new a(this, registerDeviceErrorType));
        }
    }
}
