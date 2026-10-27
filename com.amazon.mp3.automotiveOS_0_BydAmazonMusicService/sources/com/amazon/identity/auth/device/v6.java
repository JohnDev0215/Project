package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.amazon.identity.auth.device.actor.ActorManagerCommunication;
import com.amazon.identity.auth.device.api.ActorInfo;
import com.amazon.identity.auth.device.api.MAPActorManager;
import com.amazon.identity.auth.device.framework.MAPSmsReceiver;
import com.amazon.identity.auth.device.framework.smartlock.CustomerInformationManager;
import com.amazon.identity.auth.device.r6;
import com.amazon.identity.mobi.common.javascript.JavaScriptBridgeCommon;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class v6 extends u6 {
    public final s8 d;
    public final MAPActorManager e;
    public final Context f;
    public String g;
    public final CustomerInformationManager h;
    public final r6 i;

    /* JADX INFO: compiled from: DCP */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f627a;

        public a(String str) {
            this.f627a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (v6.this.a("upgradeToken")) {
                v6 v6Var = v6.this;
                String str = this.f627a;
                v6Var.getClass();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    String strOptString = jSONObject.optString("cid");
                    String strOptString2 = jSONObject.optString("pid");
                    v6Var.g = strOptString2;
                    String strOptString3 = jSONObject.optString("authCode");
                    Log.i(ga.a("MAPJavaScriptBridge"), String.format("MAP JS bridge upgradeToken is called with callbackId: %s, callback name: %s", jSONObject.optString("callingId"), jSONObject.optString("callbackFunctionNameKey", "mapJSCallback")));
                    String.format("accountId: %s, actorId: %s, authCode: %s", strOptString, strOptString2, strOptString3);
                    ga.a("MAPJavaScriptBridge");
                    Bundle bundle = new Bundle();
                    bundle.putString("key_auth_code", strOptString3);
                    bundle.putString("key_token_type", "token_type_oauth_refresh_token");
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("returnValue", "MAP_Native_Acknowledged");
                    v6Var.d.a(strOptString, strOptString2, bundle, new w6(v6Var), new x6(v6Var, str, jSONObject2));
                } catch (JSONException unused) {
                    v6Var.b("mapJSCallback", str, JavaScriptBridgeCommon.INPUT_ERROR);
                }
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f628a;

        public b(String str) {
            this.f628a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (v6.this.a("switchActor")) {
                v6 v6Var = v6.this;
                String str = this.f628a;
                v6Var.getClass();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    ActorInfo actorInfo = new ActorInfo(jSONObject.optString("program"), jSONObject.optString("cid"), jSONObject.optString("pid"), jSONObject.optString("actor_type"));
                    MAPActorManager.ActorSwitchMode actorSwitchMode = TextUtils.equals(jSONObject.optString(ActorManagerCommunication.SwitchActorAction.KEY_ACTOR_SWITCH_MODE), "Force") ? MAPActorManager.ActorSwitchMode.Force : MAPActorManager.ActorSwitchMode.Normal;
                    Log.i(ga.a("MAPJavaScriptBridge"), String.format("MAP JS bridge switchActor is called with callbackId: %s, callback name: %s", jSONObject.optString("callingId"), jSONObject.optString("callbackFunctionNameKey", "mapJSCallback")));
                    v6Var.e.switchActor(actorSwitchMode, actorInfo, new Bundle(), new y6(v6Var));
                } catch (JSONException unused) {
                    v6Var.b("mapJSCallback", str, JavaScriptBridgeCommon.INPUT_ERROR);
                }
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f629a;

        public c(String str) {
            this.f629a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (v6.this.a("getMAPAndroidBridgeVersion")) {
                v6 v6Var = v6.this;
                String str = this.f629a;
                v6Var.getClass();
                try {
                    Log.i(ga.a("MAPJavaScriptBridge"), "MAP JS bridge getMAPAndroidBridgeVersion is called");
                    JSONObject jSONObject = new JSONObject(str);
                    Log.i(ga.a("MAPJavaScriptBridge"), String.format("MAP JS bridge getMAPAndroidBridgeVersion is called. callbackId: %s , callback name: %s", jSONObject.optString("callingId"), jSONObject.optString("callbackFunctionNameKey", "mapJSCallback")));
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("mapJSVersion", "MAP_Android_1");
                    v6Var.b("mapJSCallback", str, jSONObject2.toString());
                } catch (JSONException unused) {
                    v6Var.b("mapJSCallback", str, JavaScriptBridgeCommon.INPUT_ERROR);
                }
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f630a;

        /* JADX INFO: compiled from: DCP */
        public class a implements CustomerInformationManager.a {
            public a() {
            }
        }

        public d(String str) {
            this.f630a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            v6 v6Var = v6.this;
            if (v6Var.h == null) {
                v6Var.b("mapJSCallback", this.f630a, JavaScriptBridgeCommon.NOT_SUPPORTED_ERROR);
                return;
            }
            try {
                JSONArray jSONArray = new JSONObject(this.f630a).getJSONArray("hintTypes");
                HashSet hashSet = new HashSet();
                for (int i = 0; i < jSONArray.length(); i++) {
                    hashSet.add(CustomerInformationManager.HintType.valueOf(jSONArray.getString(i)));
                }
                v6.this.h.a(hashSet, new a());
            } catch (JSONException unused) {
                v6.this.b("mapJSCallback", this.f630a, JavaScriptBridgeCommon.INPUT_ERROR);
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class e implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f632a;

        public e(String str) {
            this.f632a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            String string;
            v6 v6Var = v6.this;
            r6 r6Var = v6Var.i;
            if (r6Var == null) {
                v6Var.b("mapJSCallback", this.f632a, JavaScriptBridgeCommon.NOT_SUPPORTED_ERROR);
                return;
            }
            boolean z = r6Var.d;
            String str = "";
            if (z) {
                try {
                    String strA = com.amazon.identity.auth.device.e.a(z5.a(r6Var.f528a.getPackageName(), 64, r6Var.f528a.getPackageManager()));
                    Log.i(ga.a("SmsRetrieverManager"), "appSmsHash =  " + strA);
                    str = strA;
                } catch (PackageManager.NameNotFoundException unused) {
                    Log.e(ga.a("SmsRetrieverManager"), "NameNotFoundException when getting packageInfo for appSmsHash");
                }
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("isSupported", z);
                if (str != null) {
                    jSONObject.put("appHash", str);
                }
                string = jSONObject.toString();
            } catch (JSONException unused2) {
                string = JavaScriptBridgeCommon.GENERAL_ERROR;
            }
            v6.this.b("mapJSCallback", this.f632a, string);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f633a;

        /* JADX INFO: compiled from: DCP */
        public class a implements r6.a<q6> {
            public a() {
            }

            public void a(Object obj) {
                String string;
                q6 q6Var = (q6) obj;
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("isRegistered", q6Var.f510a);
                    String str = q6Var.b;
                    if (str != null) {
                        jSONObject.put("sms", str);
                    }
                    string = jSONObject.toString();
                } catch (JSONException unused) {
                    string = JavaScriptBridgeCommon.GENERAL_ERROR;
                }
                f fVar = f.this;
                v6.this.b("mapJSCallback", fVar.f633a, string);
            }
        }

        public f(String str) {
            this.f633a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            v6 v6Var = v6.this;
            r6 r6Var = v6Var.i;
            if (r6Var == null) {
                v6Var.b("mapJSCallback", this.f633a, JavaScriptBridgeCommon.NOT_SUPPORTED_ERROR);
                return;
            }
            r6Var.c = new a();
            MAPSmsReceiver mAPSmsReceiver = r6Var.b;
            if (mAPSmsReceiver == null || !r6Var.d) {
                r6Var.a(new q6(false, ""));
            } else {
                mAPSmsReceiver.a(r6Var.f528a, r6Var);
            }
        }
    }

    public v6(WebView webView, CustomerInformationManager customerInformationManager, r6 r6Var) {
        super(webView, "MAPJavaScriptBridge");
        Context applicationContext = webView.getContext().getApplicationContext();
        this.f = applicationContext;
        this.d = new s8(applicationContext);
        this.e = new MAPActorManager(applicationContext);
        this.g = null;
        this.h = customerInformationManager;
        this.i = r6Var;
    }

    @JavascriptInterface
    public void getCustomerInformationHint(String str) {
        a("getCustomerInformationHint", new d(str));
    }

    @JavascriptInterface
    public void getMAPAndroidBridgeVersion(String str) {
        va.b(new c(str));
    }

    @JavascriptInterface
    public void isSmsRetrieverEnabled(String str) {
        a("isSmsRetrieverEnabled", new e(str));
    }

    @JavascriptInterface
    public void registerMAPSmsReceiver(String str) {
        a("registerMAPSmsReceiver", new f(str));
    }

    @JavascriptInterface
    public void switchActor(String str) {
        va.b(new b(str));
    }

    @JavascriptInterface
    public void upgradeToken(String str) {
        va.b(new a(str));
    }
}
