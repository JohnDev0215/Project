package com.amazon.identity.auth.device.activity;

import amazon.os.Build;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.amazon.identity.auth.device.a7;
import com.amazon.identity.auth.device.ab;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPErrorCallbackHelper;
import com.amazon.identity.auth.device.bb;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.framework.MAPSmsReceiver;
import com.amazon.identity.auth.device.framework.smartlock.CustomerInformationManager;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.qe;
import com.amazon.identity.auth.device.r6;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.t5;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.utils.ResourceHelper;
import com.amazon.identity.auth.device.v9;
import com.amazon.identity.auth.device.y5;
import com.amazon.identity.auth.device.yd;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class MAPWebviewActivityTemplate extends Activity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public t5 f191a;
    public Bundle b;
    public WebView c;
    public y5 d;
    public String e;
    public String f;
    public Set<String> g;
    public RemoteCallbackWrapper h;
    public BackwardsCompatiableDataStorage i;
    public CustomerInformationManager j;
    public MAPSmsReceiver k;
    public r6 l;

    public static JSONObject f() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        boolean z = true;
        try {
            Class.forName("android.webkit.WebView");
        } catch (Exception unused) {
            yd.b(String.format("NO_WEBVIEW_%s_%s_API_%d", Build.MANUFACTURER, Build.MODEL.trim().replaceAll("\\s+", Attributes.PREDEFINED_ATTRIBUTE_PREFIX), Integer.valueOf(Build.VERSION.SDK_INT)), new String[0]);
            Log.w(ga.a("MAPUIActivityTemplate"), "Webview is not supported on this device.");
            z = false;
        }
        if (z) {
            jSONObject.put("ui_type", "WebView");
            jSONObject.put("ui_version", "1.0");
        } else {
            yd.b("MAPWebviewActivityTemplate_NO_WEBVIEW", new String[0]);
            jSONObject.put("ui_type", "NoUISupported");
        }
        return jSONObject;
    }

    public abstract String a();

    public abstract void a(Bundle bundle);

    public void a(String str, String[] strArr) {
        ga.a("MAPUIActivityTemplate");
        if (strArr == null) {
            Log.e(ga.a("MAPUIActivityTemplate"), "Fail to detect account/actor auth cookies, it shouldn't happen for challenge use case.");
            a(MAPErrorCallbackHelper.getErrorBundle(MAPError.CommonError.INTERNAL_ERROR, "No auth cookies in the option bundle, this should not happen"));
            return;
        }
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        bb.a(this.f191a);
        for (String str2 : strArr) {
            ga.a("MAPUIActivityTemplate");
            cookieManager.setCookie(str, str2);
        }
        bb.a(this.f191a);
    }

    public abstract String b();

    public void b(String str) {
        ga.a("MAPUIActivityTemplate");
        String strA = a7.a(this.f191a, getPackageName(), this.d, true);
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        ab.a(this.f191a, str, "map-md", strA, "/ap", null, true, true, true);
    }

    public abstract String[] c();

    public abstract String d();

    public abstract RemoteCallbackWrapper e();

    public abstract String g();

    public abstract String h();

    public abstract String i();

    public void j() {
        requestWindowFeature(1);
        e.a((Activity) this);
        this.b = getIntent().getExtras();
        this.h = e();
        this.f191a = t5.a(getApplicationContext());
        setContentView(ResourceHelper.a(this, "layout", i()));
        WebView webView = (WebView) findViewById(ResourceHelper.a(this, "id", h()));
        this.c = webView;
        if (webView == null) {
            a(MAPErrorCallbackHelper.getErrorBundle(MAPError.CommonError.INTERNAL_ERROR, "Failed to get webview! This shouldn't happen."));
        }
        this.d = y5.a(getIntent(), a());
        String strC = qe.c(this);
        this.e = strC;
        this.f = a7.a((Context) this.f191a, strC);
        this.g = new HashSet();
        t5 t5Var = this.f191a;
        this.i = new BackwardsCompatiableDataStorage(t5Var, t5Var.a());
        this.j = new CustomerInformationManager(this, 1);
        MAPSmsReceiver mAPSmsReceiver = new MAPSmsReceiver(this.d, this.c);
        this.k = mAPSmsReceiver;
        this.l = new r6(this.f191a, mAPSmsReceiver);
    }

    public abstract void k();

    public abstract void l();

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        Log.i(ga.a("MAPUIActivityTemplate"), "onActivityResult()");
        if (i != 1) {
            return;
        }
        this.j.a(i2, intent);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
            j();
            k();
            b(bundle);
            String strG = g();
            Log.i(ga.a("MAPUIActivityTemplate"), "Clearing User Spec cookies");
            ab.a(this.f191a, strG, "sid", "", "/", v9.b(), true, true, false);
            a(g());
            c(g());
            a(g(), c());
            l();
            b(g());
        } catch (IllegalArgumentException e) {
            a(MAPErrorCallbackHelper.getErrorBundle(MAPError.CommonError.BAD_REQUEST, String.format("An IllegalArgumentException was thrown with message: %s", e.getMessage())));
        } catch (Exception e2) {
            a(MAPErrorCallbackHelper.getErrorBundle(MAPError.CommonError.INTERNAL_ERROR, String.format("An Exception was thrown with message: %s", e2.getMessage())));
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        Log.i(ga.a("MAPUIActivityTemplate"), a() + " onDestroy called");
        Log.i(ga.a("MAPUIActivityTemplate"), "Clearing frc cookies");
        Set<String> set = this.g;
        if (set != null && set.size() > 0) {
            ga.a("MAPUIActivityTemplate");
            Iterator<String> it = this.g.iterator();
            while (it.hasNext()) {
                ab.a(this.f191a, it.next(), "frc", "", "/ap", null, true, true, true);
            }
            this.g.clear();
        }
        String strG = g();
        Log.i(ga.a("MAPUIActivityTemplate"), "Clearing MAP MD cookies");
        ab.a(this.f191a, strG, "map-md", "", "/ap", null, true, true, true);
        this.d.e.a();
        this.c.removeAllViews();
        this.c.destroy();
        this.c = null;
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && this.c.canGoBack()) {
            this.c.goBack();
            return true;
        }
        yd.b(d() + "OPERATION_CANCELED", new String[0]);
        Log.e(ga.a("MAPUIActivityTemplate"), "Customer hit back and cannot go back in webview. Returning error.");
        a((Bundle) null);
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.c.saveState(bundle);
    }

    public void c(String str) {
        String strA;
        ga.a("MAPUIActivityTemplate");
        if (TextUtils.isEmpty(this.f) || (strA = ab.a(str)) == null || this.g.contains(strA)) {
            return;
        }
        a();
        ga.a("MAPUIActivityTemplate");
        ab.a(this.f191a, strA, "frc", this.f, "/ap", null, true, true, true);
        this.g.add(strA);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void b(Bundle bundle) {
        Log.i(ga.a("MAPUIActivityTemplate"), "Initializing auth challenge webview");
        if (bundle != null) {
            this.c.restoreState(bundle);
        }
        this.c.setScrollBarStyle(0);
        WebSettings settings = this.c.getSettings();
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setAllowFileAccess(false);
        settings.setDomStorageEnabled(true);
        settings.setAllowContentAccess(false);
        settings.setMixedContentMode(1);
        this.c.clearFormData();
        this.c.getSettings().setJavaScriptEnabled(true);
        settings.getUserAgentString();
        ga.a("MAPUIActivityTemplate");
    }

    public final void a(String str) {
        Log.i(ga.a("MAPUIActivityTemplate"), "Setting SID cookie");
        String strC = this.i.c(b(), AccountConstants.TOKEN_TYPE_COOKIE_MFA_SID_TOKEN);
        if (TextUtils.isEmpty(strC)) {
            return;
        }
        ab.a(this.f191a, str, "sid", strC, "/", v9.b(), true, true, false);
    }
}
