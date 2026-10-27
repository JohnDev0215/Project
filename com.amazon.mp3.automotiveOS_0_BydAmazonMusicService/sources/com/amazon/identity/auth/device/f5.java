package com.amazon.identity.auth.device;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import com.amazon.identity.auth.device.framework.MAPRuntimePermissionHandler;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class f5 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ t5 f309a;
    public final /* synthetic */ y5 b;
    public final /* synthetic */ WebView c;
    public final /* synthetic */ boolean d;
    public final /* synthetic */ d5 e;
    public final /* synthetic */ MAPRuntimePermissionHandler f;

    public f5(MAPRuntimePermissionHandler mAPRuntimePermissionHandler, t5 t5Var, y5 y5Var, WebView webView, boolean z, d5 d5Var) {
        this.f = mAPRuntimePermissionHandler;
        this.f309a = t5Var;
        this.b = y5Var;
        this.c = webView;
        this.d = z;
        this.e = d5Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        String str;
        boolean z;
        MAPRuntimePermissionHandler mAPRuntimePermissionHandler = this.f;
        t5 t5Var = this.f309a;
        y5 y5Var = this.b;
        mAPRuntimePermissionHandler.getClass();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("action", mAPRuntimePermissionHandler.b);
            String[] strArr = mAPRuntimePermissionHandler.d;
            int length = strArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = true;
                    break;
                }
                if (!(t5Var.getPackageManager().checkPermission(strArr[i], t5Var.getPackageName()) == 0)) {
                    z = false;
                    break;
                }
                i++;
            }
            if (!z) {
                jSONObject.put("result", "deny");
                if (TextUtils.equals(mAPRuntimePermissionHandler.b, "read_mobile_number")) {
                    y5Var.b("MAPRuntimePermission:ReadPhoneStateDeny");
                }
            } else if (TextUtils.equals(mAPRuntimePermissionHandler.b, "read_mobile_number")) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject.put("result", "error");
                String str2 = MAPRuntimePermissionHandler.g;
                Log.i(ga.a(str2), "Shouldn't get phone number from the device.");
                if (TextUtils.isEmpty(a7.b(t5Var, y5Var))) {
                    Log.e(ga.a(str2), "Can't get sim country iso from the device.");
                    y5Var.b("MAPRuntimePermissionError:CannotGetCountryISO");
                } else {
                    jSONObject2.put("country_code", a7.b(t5Var, y5Var));
                }
                jSONObject.put("extra_data", jSONObject2);
            } else {
                Log.e(ga.a(MAPRuntimePermissionHandler.g), "MAP can't understand the action: " + mAPRuntimePermissionHandler.b);
                jSONObject.put("result", "error");
            }
        } catch (JSONException e) {
            Log.e(ga.a(MAPRuntimePermissionHandler.g), "JSONException while building the callback json", e);
        }
        MAPRuntimePermissionHandler mAPRuntimePermissionHandler2 = this.f;
        t5 t5Var2 = this.f309a;
        WebView webView = this.c;
        y5 y5Var2 = this.b;
        boolean z2 = this.d;
        mAPRuntimePermissionHandler2.getClass();
        try {
            if (TextUtils.equals(jSONObject.getString("result"), "grant")) {
                String strA = a7.a(t5Var2, t5Var2.getPackageName(), y5Var2, z2);
                if (!TextUtils.isEmpty(strA)) {
                    va.b(new g5(mAPRuntimePermissionHandler2, t5Var2, webView, strA));
                }
            }
        } catch (JSONException e2) {
            Log.e(ga.a(MAPRuntimePermissionHandler.g), "JSONException happened. Probably due to no result being set in callback JSON", e2);
        }
        String str3 = MAPRuntimePermissionHandler.g;
        Log.i(ga.a(str3), "MAP is going to callback javascript function: " + this.f.c);
        jSONObject.toString();
        ga.a(str3);
        d5 d5Var = this.e;
        WebView webView2 = this.c;
        String str4 = this.f.c;
        String string = jSONObject.toString();
        d5Var.getClass();
        if (TextUtils.isEmpty(string)) {
            str = "";
        } else {
            str = "'" + string + "'";
        }
        String str5 = String.format("javascript:if (typeof %1$s !== 'undefined' && typeof %1$s === 'function'){%1$s(%2$s);}", str4, str);
        ga.a(d5.b);
        va.b(new e5(d5Var, webView2, str5));
        MAPRuntimePermissionHandler.e.remove(this.f.f324a);
    }
}
