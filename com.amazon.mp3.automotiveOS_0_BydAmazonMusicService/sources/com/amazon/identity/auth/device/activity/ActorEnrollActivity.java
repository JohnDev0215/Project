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
public class ActorEnrollActivity extends MAPWebviewActivityTemplate {
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
            ActorEnrollActivity actorEnrollActivity = ActorEnrollActivity.this;
            actorEnrollActivity.c.loadUrl(actorEnrollActivity.m);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Bundle f180a;

        public b(Bundle bundle) {
            this.f180a = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            Bundle bundle = this.f180a;
            if (bundle != null) {
                ActorEnrollActivity.this.h.onError(bundle);
            } else {
                ActorEnrollActivity.this.h.onError(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.OPERATION_CANCELLED, "Operation canceled by customer", true));
            }
            ActorEnrollActivity.this.finish();
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class c extends WebViewClient {
        public c() {
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            Log.e(ga.a("ActorEnrollActivity"), "Got an error from the actor enroll webview. Aborting...");
            ActorEnrollActivity.this.a(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.NETWORK_ERROR, "Got an error from the actor enroll webview. Aborting...", true));
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            ActorEnrollActivity.this.a(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.NETWORK_ERROR, String.format(Locale.ENGLISH, "SSL Failure in actor enroll. SSL Error code %d.", Integer.valueOf(sslError.getPrimaryError())), false));
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            ActorEnrollActivity.this.c(str);
            if (!ActorEnrollActivity.a(ActorEnrollActivity.this, q4.c(str))) {
                return false;
            }
            ActorEnrollActivity actorEnrollActivity = ActorEnrollActivity.this;
            RemoteCallbackWrapper remoteCallbackWrapper = actorEnrollActivity.h;
            Bundle bundle = new Bundle();
            bundle.putInt("result_code", 1);
            bundle.putString("actor_id", actorEnrollActivity.p.g);
            remoteCallbackWrapper.onSuccess(bundle);
            ActorEnrollActivity.this.finish();
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            ga.a("ActorEnrollActivity");
            if (q4.b(q4.c(str))) {
                Log.e(ga.a("ActorEnrollActivity"), "Customer canceled the actor enroll flow");
                ActorEnrollActivity.this.a(MAPErrorCallbackHelper.getErrorBundleForActorAPI(MAPError.CommonError.OPERATION_CANCELLED, "Customer canceled the actor enroll flow", true));
            }
        }
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public String a() {
        return "EnrollActivity";
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
        return "ActorEnrollActivity_";
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
        return "enrollwebview";
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public String i() {
        return "enrollwebviewlayout";
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public void k() {
        Log.i(ga.a("ActorEnrollActivity"), "Initializing params for actor enroll UI Activity");
        this.m = this.b.getString("load_url");
        this.n = this.b.getString("account_id");
        this.o = this.b.getString("return_to_url");
        this.p = new v6(this.c, this.j, this.l);
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate
    public void l() {
        Log.i(ga.a("ActorEnrollActivity"), "Setting up webview client for actor enroll activity.");
        this.c.setWebViewClient(new c());
    }

    public static boolean a(ActorEnrollActivity actorEnrollActivity, URI uri) {
        URI uriC = q4.c(actorEnrollActivity.o);
        return uri != null && uriC != null && uri.getHost().equals(uriC.getHost()) && "/ap/maplanding".equals(uri.getPath());
    }

    @Override // com.amazon.identity.auth.device.activity.MAPWebviewActivityTemplate, android.app.Activity
    public void onCreate(Bundle bundle) {
        Log.i(ga.a("ActorEnrollActivity"), String.format("Actor enroll webview called in package %s by package %s", getPackageName(), getCallingPackage()));
        super.onCreate(bundle);
        this.c.addJavascriptInterface(this.p, "MAPAndroidJSBridge");
        runOnUiThread(new a());
    }
}
