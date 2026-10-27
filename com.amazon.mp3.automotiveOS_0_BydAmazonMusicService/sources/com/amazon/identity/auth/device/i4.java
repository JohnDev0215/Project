package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.identity.auth.device.api.WebViewHelper;
import com.amazon.identity.auth.device.de;
import com.amazon.identity.auth.device.f7;
import com.amazon.identity.mobi.authenticator.api.AmazonAuthenticatorDependency;
import com.amazon.identity.mobi.authenticator.api.AmazonAuthenticatorPlugin;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class i4 {
    public static i4 d;
    public static volatile Boolean e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f379a;
    public f8 b;
    public AmazonAuthenticatorDependency c;

    /* JADX INFO: compiled from: DCP */
    public class a implements AmazonAuthenticatorDependency {

        /* JADX INFO: renamed from: com.amazon.identity.auth.device.i4$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: DCP */
        public class C0058a implements Callback {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ com.amazon.identity.mobi.authenticator.api.Callback f381a;
            public final /* synthetic */ String b;

            public C0058a(com.amazon.identity.mobi.authenticator.api.Callback callback, String str) {
                this.f381a = callback;
                this.b = str;
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                String string = bundle.getString("com.amazon.dcp.sso.ErrorMessage");
                Log.i(ga.a("AmazonAuthenticatorPluginHelper"), "onError callback invoked for GetPushNotificationsForApplication call. Error = " + string);
                yd.b("AmazonAuthenticatorPlugin:OnError", new String[0]);
                this.f381a.onError(i4.a(i4.this, string));
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                String string = bundle.getString("value_key");
                if (string == null) {
                    Log.i(ga.a("AmazonAuthenticatorPluginHelper"), "Retrieved null access token for GetPushNotificationsForApplication call");
                    yd.b("AmazonAuthenticatorPlugin:NullAccessToken", new String[0]);
                    this.f381a.onError(i4.a(i4.this, "Null access token"));
                    return;
                }
                i4 i4Var = i4.this;
                String str = this.b;
                i4Var.getClass();
                f7.a aVarC = new g7(i4Var.f379a, str, string).c(new y5("GetPushNotificationsForApplication"));
                JSONObject jSONObject = aVarC.f312a;
                if (jSONObject == null) {
                    this.f381a.onError(i4.a(i4.this, "Null response from Panda Service"));
                } else if (jSONObject.has("pushNotifications")) {
                    this.f381a.onSuccess(aVarC.f312a);
                } else {
                    this.f381a.onError(aVarC.f312a);
                }
            }
        }

        public a() {
        }

        public boolean enableAmazonAuthenticatorJSInterface(WebView webView, Bundle bundle) {
            return WebViewHelper.enableAmazonAuthenticatorForWebView(webView, bundle);
        }

        public String getCurrentAccount() {
            i4 i4Var = i4.this;
            i4Var.getClass();
            return new MAPAccountManager(i4Var.f379a).getAccount();
        }

        public void getPushNotificationsForApplication(String str, com.amazon.identity.mobi.authenticator.api.Callback callback) {
            if (str != null) {
                new TokenManagement(i4.this.f379a).getToken(str, "com.amazon.dcp.sso.token.oauth.amazon.access_token", null, new C0058a(callback, str));
                return;
            }
            Log.i(ga.a("AmazonAuthenticatorPluginHelper"), "Retrieved null directedId for GetPushNotificationsForApplication call");
            yd.b("AmazonAuthenticatorPlugin:NullDirectedId", new String[0]);
            callback.onError(i4.a(i4.this, "Null directedId"));
        }

        public void incrementCounterAndRecord(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            yd.b(str, new String[0]);
        }

        public boolean isCredentialIdAvailable(String str) {
            return Boolean.valueOf(i4.this.b.f313a.getBoolean(str, false)).booleanValue();
        }

        public void recordTimerMetric(String str, long j) {
            Context context = yd.f685a;
            String str2 = "AmazonAuthenticatorPlugin" + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + str;
            ce ceVarA = yd.a(yd.f685a);
            (ceVarA != null ? ceVarA.b(str2) : new de.b()).a(j);
        }
    }

    public i4(Context context) {
        this.f379a = context;
        this.b = f8.a(context, "fido_authenticator_credential_namespace");
    }

    public static JSONObject a(i4 i4Var, String str) {
        i4Var.getClass();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Error", str);
            return jSONObject;
        } catch (JSONException unused) {
            Log.i(ga.a("AmazonAuthenticatorPluginHelper"), "JSONException occurred while constructing error JSONObject");
            return null;
        }
    }

    public static synchronized i4 a(Context context) {
        if (d == null) {
            d = new i4(context.getApplicationContext());
        }
        return d;
    }

    public synchronized boolean a(String str, Context context) {
        Log.i(ga.a("AmazonAuthenticatorPluginHelper"), "Trying to start TIV Approval process with MAP ScreenTakeover");
        if (!e.booleanValue()) {
            Log.w(ga.a("AmazonAuthenticatorPluginHelper"), "AmazonAuthenticatorPlugin is not supported");
            return false;
        }
        AmazonAuthenticatorPlugin amazonAuthenticatorPlugin = AmazonAuthenticatorPlugin.getInstance();
        if (amazonAuthenticatorPlugin == null) {
            Log.i(ga.a("AmazonAuthenticatorPluginHelper"), "AmazonAuthenticator Plugin has not been initialized yet.");
            return false;
        }
        return amazonAuthenticatorPlugin.startTIVApprovalForPushNotification(str, context);
    }

    public synchronized void a() {
        boolean z;
        boolean zBooleanValue;
        synchronized (i4.class) {
            z = false;
            if (e != null) {
                zBooleanValue = e.booleanValue();
            } else {
                try {
                    Class.forName("com.amazon.identity.mobi.authenticator.api.AmazonAuthenticatorPlugin");
                    Log.i(ga.a("AmazonAuthenticatorPluginHelper"), "AmazonAuthenticatorPlugin is supported");
                    e = Boolean.TRUE;
                    zBooleanValue = true;
                } catch (ClassNotFoundException unused) {
                    Log.w(ga.a("AmazonAuthenticatorPluginHelper"), "AmazonAuthenticatorPlugin is not supported");
                    e = Boolean.FALSE;
                    zBooleanValue = false;
                }
            }
        }
        if (zBooleanValue) {
            synchronized (this) {
                if (qe.m(this.f379a)) {
                    f8 f8Var = this.b;
                    f8Var.getClass();
                    HashMap map = new HashMap();
                    Map<String, ?> all = f8Var.f313a.getAll();
                    if (all != null) {
                        for (Map.Entry<String, ?> entry : all.entrySet()) {
                            if (all.get(entry.getKey()) instanceof Boolean) {
                                map.put(entry.getKey(), (Boolean) all.get(entry.getKey()));
                            }
                        }
                    }
                    z = map.size() > 0;
                }
                if (!z) {
                    ga.a("AmazonAuthenticatorPluginHelper");
                } else {
                    if (AmazonAuthenticatorPlugin.getInstance() != null) {
                        Log.i(ga.a("AmazonAuthenticatorPluginHelper"), "Amazon Authenticator Plugin has already been initialized.");
                        return;
                    }
                    this.c = new a();
                    Log.i(ga.a("AmazonAuthenticatorPluginHelper"), "Initializing Amazon Authenticator Plugin.");
                    AmazonAuthenticatorPlugin.init(this.f379a, this.c);
                }
            }
        }
    }
}
