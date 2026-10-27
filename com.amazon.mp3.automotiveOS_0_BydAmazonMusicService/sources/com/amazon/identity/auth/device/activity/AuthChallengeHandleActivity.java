package com.amazon.identity.auth.device.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPErrorCallbackHelper;
import com.amazon.identity.auth.device.api.TokenKeys;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.h9;
import com.amazon.identity.auth.device.q4;
import com.amazon.identity.auth.device.v6;
import com.amazon.identity.auth.device.va;
import java.net.URI;
import java.util.Locale;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class AuthChallengeHandleActivity extends MAPWebviewActivityTemplate {
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;

    /* JADX INFO: compiled from: DCP */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AuthChallengeHandleActivity authChallengeHandleActivity = AuthChallengeHandleActivity.this;
            authChallengeHandleActivity.c.loadUrl(authChallengeHandleActivity.m);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Bundle f186a;

        public b(Bundle bundle) {
            this.f186a = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            Bundle bundle = this.f186a;
            if (bundle != null) {
                AuthChallengeHandleActivity.this.h.onError(bundle);
            } else {
                AuthChallengeHandleActivity.this.h.onError(MAPErrorCallbackHelper.getErrorBundle(MAPError.CommonError.OPERATION_CANCELLED, "Operation canceled by customer"));
            }
            AuthChallengeHandleActivity.this.finish();
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class c extends WebViewClient {

        /* JADX INFO: compiled from: DCP */
        public class a implements Callback {

            /* JADX INFO: renamed from: com.amazon.identity.auth.device.activity.AuthChallengeHandleActivity$c$a$a, reason: collision with other inner class name */
            /* JADX INFO: compiled from: DCP */
            public class RunnableC0050a implements Runnable {

                /* JADX INFO: renamed from: a, reason: collision with root package name */
                public final /* synthetic */ Bundle f189a;

                public RunnableC0050a(Bundle bundle) {
                    this.f189a = bundle;
                }

                @Override // java.lang.Runnable
                public void run() {
                    Log.i(ga.a("AuthChallengeHandleActivity"), "Successfully get actor token with failure context!");
                    AuthChallengeHandleActivity.this.h.onSuccess(this.f189a);
                    AuthChallengeHandleActivity.this.finish();
                }
            }

            public a() {
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle) {
                AuthChallengeHandleActivity.this.a(bundle);
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle) {
                va.a(new RunnableC0050a(bundle));
            }
        }

        public c() {
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            Log.e(ga.a("AuthChallengeHandleActivity"), "Got an error from the webview. Aborting...");
            AuthChallengeHandleActivity.this.a(MAPErrorCallbackHelper.getErrorBundle(MAPError.CommonError.NETWORK_ERROR, "Got an error from the webview. Aborting..."));
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            AuthChallengeHandleActivity.this.a(MAPErrorCallbackHelper.getErrorBundle(MAPError.CommonError.NETWORK_ERROR, String.format(Locale.ENGLISH, "SSL Failure. SSL Error code %d.", Integer.valueOf(sslError.getPrimaryError()))));
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            AuthChallengeHandleActivity.this.c(str);
            if (!AuthChallengeHandleActivity.a(AuthChallengeHandleActivity.this, q4.c(str))) {
                return false;
            }
            Log.i(ga.a("AuthChallengeHandleActivity"), "Handling return to URL, calling get actor token without FC...");
            h9 h9VarA = h9.a(AuthChallengeHandleActivity.this.f191a);
            AuthChallengeHandleActivity authChallengeHandleActivity = AuthChallengeHandleActivity.this;
            h9VarA.a((Context) null, authChallengeHandleActivity.n, authChallengeHandleActivity.o, authChallengeHandleActivity.p, (String) null, (Bundle) null, new a(), authChallengeHandleActivity.d);
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            ga.a("AuthChallengeHandleActivity");
            if (q4.b(q4.c(str))) {
                Log.e(ga.a("AuthChallengeHandleActivity"), "Customer canceled the flow");
                AuthChallengeHandleActivity.this.a(MAPErrorCallbackHelper.getErrorBundle(MAPError.CommonError.OPERATION_CANCELLED, "Customer canceled the flow"));
            }
        }
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public String a() {
        return "AuthChallengeHandleActivity";
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public void a(Bundle bundle) {
        va.a(new b(bundle));
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public String b() {
        return this.n;
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public String[] c() {
        return this.b.getBundle("actor_cookies_for_request").getStringArray(CookieKeys.KEY_COOKIES);
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public String d() {
        return "AuthChallengeHandleActivity_";
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public RemoteCallbackWrapper e() {
        return (RemoteCallbackWrapper) this.b.getParcelable("callback");
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public String g() {
        return this.m;
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public String h() {
        return "authchallengehandleactivitywebview";
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public String i() {
        return "authchallengehandleactivitylayout";
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public void k() {
        Log.i(ga.a("AuthChallengeHandleActivity"), "Initializing params for Auth challenge UI Activity");
        this.b.getBundle("auth_portal_config").getString(TokenKeys.Options.KEY_CHALLENGE_URL_FULL_DOMAIN);
        this.m = this.b.getString("challenge_url");
        this.n = this.b.getString("account_id");
        this.o = this.b.getString("actor_id");
        this.p = this.b.getString("com.amazon.dcp.sso.token.oauth.amazon.actor.access_token");
        String string = this.b.getBundle("auth_portal_config").getString(TokenKeys.Options.KEY_OVERRIDE_CHALLENGE_URL_RETURNTO_FULL_DOMAIN);
        this.q = string;
        this.r = OpenIdRequest.b(string);
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public void l() {
        Log.i(ga.a("AuthChallengeHandleActivity"), "Setting up webview client for Auth challenge activity.");
        this.c.setWebViewClient(new c());
    }

    public static boolean a(AuthChallengeHandleActivity authChallengeHandleActivity, URI uri) {
        URI uriC = q4.c(authChallengeHandleActivity.r);
        return uri != null && uriC != null && uri.getHost().equals(uriC.getHost()) && "/ap/maplanding".equals(uri.getPath());
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate, android.app.Activity
    public void onCreate(Bundle bundle) {
        Log.i(ga.a("AuthChallengeHandleActivity"), String.format("Auth challenge webview called in package %s by package %s", getPackageName(), getCallingPackage()));
        super.onCreate(bundle);
        WebView webView = this.c;
        webView.addJavascriptInterface(new v6(webView, this.j, this.l), "MAPAndroidJSBridge");
        runOnUiThread(new a());
    }
}
