package com.amazon.identity.auth.device;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import com.amazon.identity.auth.device.api.FederatedAuthConfig;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import java.util.HashMap;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class z1 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ WebView f694a;
    public final /* synthetic */ OpenIdRequest b;
    public final /* synthetic */ AuthPortalUIActivity c;

    public z1(AuthPortalUIActivity authPortalUIActivity, WebView webView, OpenIdRequest openIdRequest) {
        this.c = authPortalUIActivity;
        this.f694a = webView;
        this.b = openIdRequest;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (!TextUtils.isEmpty(this.f694a.getUrl())) {
            this.f694a.requestLayout();
            return;
        }
        OpenIdRequest openIdRequest = this.b;
        AuthPortalUIActivity authPortalUIActivity = this.c;
        String str = AuthPortalUIActivity.Q;
        authPortalUIActivity.getClass();
        HashMap map = new HashMap();
        try {
            Bundle bundle = authPortalUIActivity.getPackageManager().getApplicationInfo(authPortalUIActivity.getPackageName(), 128).metaData;
            if (bundle != null) {
                String string = bundle.getString("debugParams");
                if (!TextUtils.isEmpty(string)) {
                    TextUtils.SimpleStringSplitter simpleStringSplitter = new TextUtils.SimpleStringSplitter(',');
                    simpleStringSplitter.setString(string);
                    while (simpleStringSplitter.hasNext()) {
                        String next = simpleStringSplitter.next();
                        TextUtils.SimpleStringSplitter simpleStringSplitter2 = new TextUtils.SimpleStringSplitter('=');
                        simpleStringSplitter2.setString(next);
                        if (simpleStringSplitter2.hasNext()) {
                            String next2 = simpleStringSplitter2.next();
                            if (simpleStringSplitter2.hasNext()) {
                                map.put(next2, simpleStringSplitter2.next());
                            }
                        }
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(ga.a(AuthPortalUIActivity.Q), "No meta found for this package", e);
        }
        openIdRequest.f = map;
        String strD = this.b.d();
        OpenIdRequest openIdRequest2 = this.b;
        openIdRequest2.getClass();
        HashMap map2 = new HashMap();
        FederatedAuthConfig federatedAuthConfig = (FederatedAuthConfig) openIdRequest2.c.getParcelable(MAPAccountManager.KEY_FEDERATED_AUTH_CONFIG);
        if (federatedAuthConfig != null) {
            map2.put("3p_access_token", federatedAuthConfig.getAccessToken());
        }
        ga.a(AuthPortalUIActivity.Q, "Loading AuthPortal Signin Url: %s", strD);
        AuthPortalUIActivity authPortalUIActivity2 = this.c;
        y5 y5Var = authPortalUIActivity2.C;
        if (y5Var != null) {
            authPortalUIActivity2.f157a = y5Var.c("AuthPortalUIActivity_FirstOnPageStarted:" + e.b(strD));
            AuthPortalUIActivity authPortalUIActivity3 = this.c;
            authPortalUIActivity3.b = authPortalUIActivity3.C.c("AuthPortalUIActivity_FirstPageLoad:" + e.b(strD));
            AuthPortalUIActivity authPortalUIActivity4 = this.c;
            authPortalUIActivity4.c = authPortalUIActivity4.C.c("AuthPortalUIActivity_FirstPageRender:" + e.b(strD));
            AuthPortalUIActivity authPortalUIActivity5 = this.c;
            authPortalUIActivity5.d = authPortalUIActivity5.C.c("AuthPortalUIActivity_BackPressedInWebView:" + e.b(strD));
        }
        AuthPortalUIActivity authPortalUIActivity6 = this.c;
        authPortalUIActivity6.getClass();
        y5 y5Var2 = authPortalUIActivity6.C;
        if (y5Var2 != null) {
            authPortalUIActivity6.h = y5Var2.c("AuthPortalUIActivity_CriticalFeatureLoaded:" + e.b(strD));
        }
        this.f694a.loadUrl(strD, map2);
    }
}
