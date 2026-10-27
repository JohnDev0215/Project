package com.amazon.identity.auth.device.activity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.MAPErrorCallbackHelper;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.q4;
import com.amazon.identity.auth.device.v6;
import com.amazon.identity.auth.device.va;
import java.net.URI;
import java.util.Locale;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class ActorSignUpAndEnrollActivity extends MAPWebviewActivityTemplate {
    public String m;
    public String n;
    public String o;
    public v6 p;

    /* JADX INFO: compiled from: DCP */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ActorSignUpAndEnrollActivity actorSignUpAndEnrollActivity = ActorSignUpAndEnrollActivity.this;
            actorSignUpAndEnrollActivity.c.loadUrl(actorSignUpAndEnrollActivity.m);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Bundle f183a;

        public b(Bundle bundle) {
            this.f183a = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            Bundle bundle = this.f183a;
            if (bundle != null) {
                ActorSignUpAndEnrollActivity.this.h.onError(bundle);
            } else {
                ActorSignUpAndEnrollActivity.this.h.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.OPERATION_CANCELLED, "Operation canceled by customer", true));
            }
            ActorSignUpAndEnrollActivity.this.finish();
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class c extends WebViewClient {
        public c() {
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            Log.e(ga.a("ActorSignUpAndEnrollActivity"), "Got an error from the sign up and enroll webview. Aborting...");
            ActorSignUpAndEnrollActivity.this.a(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.NETWORK_ERROR, "Got an error from the sign up and enroll webview. Aborting...", true));
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            ActorSignUpAndEnrollActivity.this.a(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.NETWORK_ERROR, String.format(Locale.ENGLISH, "SSL Failure in sign up and enroll. SSL Error code %d.", Integer.valueOf(sslError.getPrimaryError())), false));
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            ActorSignUpAndEnrollActivity.this.c(str);
            if (!ActorSignUpAndEnrollActivity.a(ActorSignUpAndEnrollActivity.this, q4.c(str))) {
                return false;
            }
            ActorSignUpAndEnrollActivity actorSignUpAndEnrollActivity = ActorSignUpAndEnrollActivity.this;
            RemoteCallbackWrapper remoteCallbackWrapper = actorSignUpAndEnrollActivity.h;
            Bundle bundle = new Bundle();
            bundle.putInt("result_code", 1);
            bundle.putString("actor_id", actorSignUpAndEnrollActivity.p.g);
            remoteCallbackWrapper.onSuccess(bundle);
            ActorSignUpAndEnrollActivity.this.finish();
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            ga.a("ActorSignUpAndEnrollActivity");
            if (q4.b(q4.c(str))) {
                Log.e(ga.a("ActorSignUpAndEnrollActivity"), "Customer canceled the sign up and enroll flow");
                ActorSignUpAndEnrollActivity.this.a(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.OPERATION_CANCELLED, "Customer canceled the sign up and enroll flow", true));
            }
        }
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public String a() {
        return "ActorSignUpAndEnrollActivity";
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
        return this.b.getBundle("account_cookies_for_request").getStringArray(CookieKeys.KEY_COOKIES);
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public String d() {
        return "ActorSignUpAndEnrollActivity_";
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
        return "signupandenrollwebview";
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public String i() {
        return "signupandenrollwebviewlayout";
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public void k() {
        Log.i(ga.a("ActorSignUpAndEnrollActivity"), "Initializing params for actor sign up and enroll UI Activity");
        this.m = this.b.getString("load_url");
        this.n = this.b.getString("account_id");
        this.o = this.b.getString("return_to_url");
        this.p = new v6(this.c, this.j, this.l);
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public void l() {
        Log.i(ga.a("ActorSignUpAndEnrollActivity"), "Setting up webview client for sign up and enroll activity.");
        this.c.setWebViewClient(new c());
    }

    public static boolean a(ActorSignUpAndEnrollActivity actorSignUpAndEnrollActivity, URI uri) {
        URI uriC = q4.c(actorSignUpAndEnrollActivity.o);
        return uri != null && uriC != null && uri.getHost().equals(uriC.getHost()) && "/ap/maplanding".equals(uri.getPath());
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate, android.app.Activity
    public void onCreate(Bundle bundle) {
        Log.i(ga.a("ActorSignUpAndEnrollActivity"), String.format("Actor sign up and enroll webview called in package %s by package %s", getPackageName(), getCallingPackage()));
        super.onCreate(bundle);
        this.c.addJavascriptInterface(this.p, "MAPAndroidJSBridge");
        runOnUiThread(new a());
    }
}
