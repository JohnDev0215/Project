package com.amazon.identity.auth.device;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.WebView;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.mobi.common.javascript.JavaScriptBridgeCommon;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class u6 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WebView f606a;
    public Map<String, String> b = Collections.synchronizedMap(new HashMap());
    public String c;

    /* JADX INFO: compiled from: DCP */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f607a;

        public a(String str) {
            this.f607a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            u6.this.f606a.loadUrl(this.f607a);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f608a;
        public final /* synthetic */ Runnable b;

        public b(String str, Runnable runnable) {
            this.f608a = str;
            this.b = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (u6.this.a(this.f608a)) {
                this.b.run();
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f609a;
        public final /* synthetic */ String b;
        public final /* synthetic */ s6 c;

        /* JADX INFO: compiled from: DCP */
        public class a implements z6 {
            public a() {
            }

            public void a(String str) {
                c cVar = c.this;
                u6.this.b("mapJSCallback", cVar.b, str);
            }

            public void a(String str, String str2) {
                c cVar = c.this;
                u6 u6Var = u6.this;
                String str3 = cVar.b;
                u6Var.getClass();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    jSONObject.put("errorMessage", str2);
                    u6Var.b("mapJSCallback", str3, jSONObject.toString());
                } catch (Exception unused) {
                    u6Var.b("mapJSCallback", str3, JavaScriptBridgeCommon.GENERAL_ERROR);
                }
            }
        }

        public c(String str, String str2, s6 s6Var) {
            this.f609a = str;
            this.b = str2;
            this.c = s6Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (u6.this.a(this.f609a)) {
                    if (this.b != null) {
                        JSONObject jSONObject = new JSONObject(this.b);
                        if (!jSONObject.getString("callingId").matches("\\w+\\d+")) {
                            u6.this.a(this.f609a, "\"Invalid JSON Input\"", JavaScriptBridgeCommon.INPUT_ERROR, "Invalid callbackId");
                            return;
                        } else {
                            if (u6.this.a(this.b, this.f609a)) {
                                this.c.a(jSONObject, new a());
                                return;
                            }
                            return;
                        }
                    }
                    Log.i(ga.a(u6.this.c), "Call to " + this.f609a + " failed because JSON input was null");
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.f609a);
                    sb.append(":NullInput");
                    yd.b(sb.toString(), new String[0]);
                    u6.this.a(this.f609a, null, JavaScriptBridgeCommon.INPUT_ERROR, "JSON input was null");
                }
            } catch (JSONException unused) {
                u6.this.a(this.f609a, "\"Invalid JSON Input\"", JavaScriptBridgeCommon.INPUT_ERROR, "JSONException while parsing input");
            } catch (Exception unused2) {
                u6.this.a(this.f609a, this.b, JavaScriptBridgeCommon.GENERAL_ERROR, "Exception thrown while executing function");
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f611a;

        public d(String str) {
            this.f611a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            u6.this.b.remove(this.f611a);
        }
    }

    public u6(WebView webView, String str) {
        this.f606a = webView;
        this.c = str;
    }

    public void a(String str, Runnable runnable) {
        va.b(new b(str, runnable));
    }

    public void b(String str, String str2, String str3) {
        Log.i(ga.a(this.c), "loadCallbackFunction");
        String str4 = String.format("javascript:if (typeof %1$s !== 'undefined' && typeof %1$s === 'function'){%1$s(%2$s,%3$s);}", str, str2, str3);
        ga.a(this.c);
        va.b(new a(str4));
    }

    public void a(String str, String str2, s6 s6Var) {
        va.b(new c(str, str2, s6Var));
    }

    public boolean a(String str) {
        URL urlA = ya.a(this.f606a.getUrl());
        if (urlA != null) {
            String host = urlA.getHost();
            String path = urlA.getPath();
            ga.a("JavaScriptBridgeBase");
            yd.b(str + ":" + host + ":" + path, new String[0]);
            Map<String, String> map = EnvironmentUtils.f306a;
            HashSet hashSet = new HashSet(EnvironmentUtils.b.keySet());
            hashSet.add(".primevideo.com");
            hashSet.add(".primevideo.co.uk");
            hashSet.add(".primevideo.co.jp");
            hashSet.add(".primevideo.de");
            hashSet.add(".primevideo.in");
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                if (host.endsWith((String) it.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String strOptString = jSONObject.optString("token", null);
            String string = jSONObject.getString("callingId");
            if (strOptString == null) {
                Log.i(ga.a(this.c), "Injecting token for " + str2 + " call.");
                a(string, str2, str);
                return false;
            }
            String strRemove = this.b.remove(string);
            if (!(strRemove != null ? strRemove.equals(strOptString) : false)) {
                Log.i(ga.a(this.c), str2 + " call failed due to authentication error.");
                yd.b(str2 + ":InvalidAuthToken", new String[0]);
                a(str2, str, JavaScriptBridgeCommon.GENERAL_ERROR, "Unauthorized to call");
                return false;
            }
            Log.i(ga.a(this.c), str2 + " call was successfully authenticated.");
            yd.b(str2 + ":SuccessfulAuth", new String[0]);
            return true;
        } catch (JSONException unused) {
            Log.i(ga.a(this.c), str2 + " call failed due to invalid input");
            a(str2, "Invalid JSON input", JavaScriptBridgeCommon.INPUT_ERROR, "Unauthorized to call");
            return false;
        } catch (Exception unused2) {
            Log.i(ga.a(this.c), str2 + " call failed due exception being thrown while authenticating the call");
            a(str2, str, JavaScriptBridgeCommon.GENERAL_ERROR, "Unauthorized to call");
            return false;
        }
    }

    public void a(String str, String str2, String str3) {
        try {
            if (this.b.containsKey(str)) {
                Log.i(ga.a(this.c), str2 + " inject token failed because the token map already contains an entry for the callbackId.");
                yd.b(str2 + ":TokenAlreadyInjected", new String[0]);
                a(str2, str3, JavaScriptBridgeCommon.GENERAL_ERROR, "Unauthorized to call");
                return;
            }
            if (this.b.size() >= 15) {
                Log.i(ga.a(this.c), str2 + " inject token failed due to too many token keys in the token map.");
                yd.b(str2 + ":TooManyTokens", new String[0]);
                a(str2, str3, JavaScriptBridgeCommon.GENERAL_ERROR, "Unauthorized to call");
                return;
            }
            String string = UUID.randomUUID().toString();
            this.f606a.loadUrl("javascript: var newDiv = document.createElement(\"div\");newDiv.innerText = \"" + string + "\";newDiv.setAttribute(\"id\",\"" + str + "\");newDiv.style.display=\"none\";document.body.appendChild(newDiv);");
            this.b.put(str, string);
            new Handler(Looper.getMainLooper()).postDelayed(new d(str), 10000L);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("callingId", str);
            jSONObject.put("originalFunction", str2);
            b("injectTokenCallback", str3, jSONObject.toString());
        } catch (Exception unused) {
            Log.i(ga.a(this.c), str2 + " inject token call failed because an exception was thrown.");
            yd.b(str2 + ":InjectTokenException", new String[0]);
            a(str2, str3, JavaScriptBridgeCommon.GENERAL_ERROR, "Unauthorized to call");
        }
    }

    public final void a(String str, String str2, String str3, String str4) {
        if (!str.equals("isUserVerifyingPlatformAuthenticatorAvailable") && !str.equals("isAuthenticatorCredentialAvailable")) {
            try {
                JSONObject jSONObject = new JSONObject(str3);
                jSONObject.put("errorMessage", str4);
                b("mapJSCallback", str2, jSONObject.toString());
                return;
            } catch (Exception unused) {
                b("mapJSCallback", str2, JavaScriptBridgeCommon.GENERAL_ERROR);
                return;
            }
        }
        b("mapJSCallback", str2, String.valueOf(false));
    }
}
