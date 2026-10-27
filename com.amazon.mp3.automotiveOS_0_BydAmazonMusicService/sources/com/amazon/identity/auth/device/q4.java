package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import com.amazon.identity.auth.device.AuthPortalUIActivity;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import com.amazon.identity.auth.device.framework.MAPSmsReceiver;
import com.amazon.identity.auth.device.me;
import com.amazon.identity.auth.device.metrics.SSOMetrics;
import com.amazon.identity.auth.device.utils.ResourceHelper;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class q4 extends WebViewClient {
    public final a b;
    public final String c;
    public final Set<String> d;
    public final boolean e;
    public final OpenIdRequest.TOKEN_SCOPE f;
    public final OpenIdRequest.REQUEST_TYPE g;
    public de h;
    public de i;
    public final y5 k;
    public final Context l;
    public final MAPSmsReceiver m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f508a = false;
    public de j = null;
    public volatile boolean n = false;
    public volatile boolean o = false;

    /* JADX INFO: compiled from: DCP */
    public interface a {
    }

    public q4(Context context, MAPSmsReceiver mAPSmsReceiver, OpenIdRequest.REQUEST_TYPE request_type, String str, OpenIdRequest.TOKEN_SCOPE token_scope, Set<String> set, boolean z, a aVar, y5 y5Var) {
        e.a(aVar, "callback");
        this.l = context;
        this.m = mAPSmsReceiver;
        this.c = str;
        this.b = aVar;
        this.f = token_scope;
        this.g = request_type;
        this.d = set;
        this.e = z;
        this.k = y5Var;
    }

    public static boolean c(URI uri) {
        return uri != null && TextUtils.equals(uri.getPath(), "/ap/maplanding");
    }

    public final void a(String str) {
        this.f508a = true;
        ga.a("AuthenticationWebViewClient");
        x3 x3Var = new x3(str);
        ga.a("AuthenticationWebViewClient");
        String strB = x3Var.b();
        if (!"device_auth_access".equalsIgnoreCase(x3Var.e()) && TextUtils.isEmpty(strB)) {
            yd.b("WebViewFailure:InvalidScope:" + this.g.name() + ":" + e.b(str), new String[0]);
            String str2 = String.format("Received token with invalid scope %s and no authorization code", x3Var.e());
            a aVar = this.b;
            Bundle bundleB = y.b(MAPError.CommonError.PARSE_ERROR, str2, MAPAccountManager.RegistrationError.PARSE_ERROR.value(), str2);
            AuthPortalUIActivity.e eVar = (AuthPortalUIActivity.e) aVar;
            AuthPortalUIActivity authPortalUIActivity = AuthPortalUIActivity.this;
            String str3 = AuthPortalUIActivity.Q;
            authPortalUIActivity.a();
            AuthPortalUIActivity.a(AuthPortalUIActivity.this, bundleB);
            return;
        }
        if (TextUtils.isEmpty(x3Var.a()) && TextUtils.isEmpty(strB)) {
            yd.b("WebViewFailure:NoAccessTokenAndAuthorizationCode:" + this.g.name() + ":" + e.b(str), new String[0]);
            yd.b("MAPError:AuthenticationFailed", new String[0]);
            a aVar2 = this.b;
            Bundle bundleB2 = y.b(MAPError.AccountError.REGISTER_FAILED, "Sign in failed because the access token is not set in the return_to_url. Please contact the AuthPortal team to understand the reason.", MAPAccountManager.RegistrationError.REGISTER_FAILED.value(), "Received empty access token and authorization code from AP response");
            AuthPortalUIActivity.e eVar2 = (AuthPortalUIActivity.e) aVar2;
            AuthPortalUIActivity authPortalUIActivity2 = AuthPortalUIActivity.this;
            String str4 = AuthPortalUIActivity.Q;
            authPortalUIActivity2.a();
            AuthPortalUIActivity.a(AuthPortalUIActivity.this, bundleB2);
            return;
        }
        if (!TextUtils.isEmpty(x3Var.c())) {
            AuthPortalUIActivity.e eVar3 = (AuthPortalUIActivity.e) this.b;
            MAPSmsReceiver mAPSmsReceiver = AuthPortalUIActivity.this.E;
            synchronized (mAPSmsReceiver) {
                if (mAPSmsReceiver.e) {
                    mAPSmsReceiver.f325a.b("MOA:AutoPVSuccess");
                }
            }
            AuthPortalUIActivity.this.a(eVar3.f162a.b, x3Var, eVar3.b);
            return;
        }
        yd.a("WebViewFailure:NoDirectedID:" + this.g.name() + ":" + e.b(str), new String[0]);
        yd.a("MAPError:AuthenticationFailed", new String[0]);
        a aVar3 = this.b;
        Bundle bundleB3 = y.b(MAPError.AccountError.REGISTER_FAILED, "Sign in failed because the directedId is not set in the return_to_url. Please contact the AuthPortal team to understand the reason.", MAPAccountManager.RegistrationError.REGISTER_FAILED.value(), "Registration response received invalid because it did not contain a directed id");
        AuthPortalUIActivity.e eVar4 = (AuthPortalUIActivity.e) aVar3;
        AuthPortalUIActivity authPortalUIActivity3 = AuthPortalUIActivity.this;
        String str5 = AuthPortalUIActivity.Q;
        authPortalUIActivity3.a();
        AuthPortalUIActivity.a(AuthPortalUIActivity.this, bundleB3);
    }

    public final boolean b(String str) {
        return (str.contains("ap/signin") || str.contains("ap/register")) && !str.contains("openid.assoc_handle");
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        de deVar;
        de deVar2;
        ga.a("AuthenticationWebViewClient");
        super.onPageFinished(webView, str);
        bb.a(this.l);
        if (this.n && (deVar2 = this.h) != null) {
            deVar2.c();
        }
        if (this.o && (deVar = this.i) != null) {
            deVar.c();
        }
        if (str.startsWith(this.c) || this.f508a) {
            de deVar3 = this.j;
            if (deVar3 != null) {
                deVar3.c();
                this.j = null;
                return;
            }
            return;
        }
        AuthPortalUIActivity.e eVar = (AuthPortalUIActivity.e) this.b;
        ImageView imageView = (ImageView) AuthPortalUIActivity.this.findViewById(ResourceHelper.a(AuthPortalUIActivity.this, "id", "apimageview"));
        WebView webViewD = AuthPortalUIActivity.this.d();
        if (webViewD == null) {
            AuthPortalUIActivity.a(AuthPortalUIActivity.this, y.b(MAPError.CommonError.INTERNAL_ERROR, "An unexpected error occured while setting up the WebView.", MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), "An unexpected error occured while setting up the WebView."));
            return;
        }
        if (imageView.getVisibility() == 0) {
            imageView.setVisibility(8);
        }
        if (webViewD.getVisibility() != 0) {
            webViewD.setVisibility(0);
            webViewD.requestFocusFromTouch();
        }
        AuthPortalUIActivity.this.a(100);
        AuthPortalUIActivity.this.e();
        de deVar4 = AuthPortalUIActivity.this.c;
        if (deVar4 != null) {
            double d = deVar4.d();
            me.a aVar = (me.a) yd.a();
            aVar.f438a = AuthPortalUIActivity.this.C.f667a;
            aVar.b = "AuthPortalUIActivity_FirstPageRender";
            aVar.d = e.b(webViewD.getUrl());
            aVar.f = Double.valueOf(d);
            aVar.a().a();
        }
        de deVar5 = AuthPortalUIActivity.this.b;
        if (deVar5 != null) {
            double d2 = deVar5.d();
            me.a aVar2 = (me.a) yd.a();
            aVar2.f438a = AuthPortalUIActivity.this.C.f667a;
            aVar2.b = "AuthPortalUIActivity_FirstPageLoad";
            aVar2.d = e.b(webViewD.getUrl());
            aVar2.f = Double.valueOf(d2);
            aVar2.a().a();
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        Log.e(ga.a("AuthenticationWebViewClient"), "Got an error from the webview. Returning false for SignIn (" + i + ") " + str);
        SSOMetrics.a();
        StringBuilder sb = new StringBuilder();
        sb.append("Path:");
        sb.append(e.c(str2));
        SSOMetrics.a("WebViewLoadFailure", sb.toString(), Integer.toString(i));
        yd.b("NetworkError3:AuthenticationWebViewClient", new String[0]);
        String str3 = String.format(Locale.ENGLISH, "Received error code %d and description: %s", Integer.valueOf(i), str);
        a aVar = this.b;
        Bundle bundleB = y.b(MAPError.CommonError.NETWORK_ERROR, String.format("A network error occurred: %s", str), MAPAccountManager.RegistrationError.NETWORK_FAILURE.value(), str3);
        AuthPortalUIActivity.e eVar = (AuthPortalUIActivity.e) aVar;
        AuthPortalUIActivity authPortalUIActivity = AuthPortalUIActivity.this;
        String str4 = AuthPortalUIActivity.Q;
        authPortalUIActivity.a();
        AuthPortalUIActivity.a(AuthPortalUIActivity.this, bundleB);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        sslError.toString();
        ga.a("AuthenticationWebViewClient");
        yd.b("NetworkError5:AuthenticationWebViewClient", new String[0]);
        String str = String.format(Locale.ENGLISH, "SSL Failure. SSL Error code %d.", Integer.valueOf(sslError.getPrimaryError()));
        Bundle bundleB = y.b(MAPError.CommonError.NETWORK_ERROR, str, MAPAccountManager.RegistrationError.NETWORK_FAILURE.value(), str);
        String url = sslError.getUrl();
        URL urlA = ya.a(url);
        SSOMetrics.a(url, sslError.getPrimaryError());
        if (urlA != null) {
            String str2 = urlA.getHost() + urlA.getPath();
            Log.e(ga.a("AuthenticationWebViewClient"), "SSL error for: " + str2);
            yd.b("MAPWebViewSSLError_" + str2, new String[0]);
            bundleB.putString(MAPAccountManager.KEY_ERROR_DOMAIN_PATH_WEBVIEW_SSL_ERROR, str2);
        }
        bundleB.putInt(MAPAccountManager.KEY_ERROR_CODE_WEBVIEW_SSL_ERROR, sslError.getPrimaryError());
        AuthPortalUIActivity.e eVar = (AuthPortalUIActivity.e) this.b;
        AuthPortalUIActivity authPortalUIActivity = AuthPortalUIActivity.this;
        String str3 = AuthPortalUIActivity.Q;
        authPortalUIActivity.a();
        AuthPortalUIActivity.a(AuthPortalUIActivity.this, bundleB);
    }

    @Override // android.webkit.WebViewClient
    @SuppressLint({"NewApi"})
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        y5 y5Var;
        if (b(str) && this.j == null && (y5Var = this.k) != null) {
            this.j = y5Var.c("AuthenticationWebViewClient_SignInRegisterPost:" + this.g.name());
        }
        return super.shouldInterceptRequest(webView, str);
    }

    public static URI c(String str) {
        try {
            return new URI(str);
        } catch (URISyntaxException e) {
            Log.e(ga.a("AuthenticationWebViewClient"), "Exception while trying to parse url in onPageStarted. Continue with page load.", e);
            yd.b("MAP_URISyntaxException", new String[0]);
            return null;
        }
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        ga.a("AuthenticationWebViewClient");
        Objects.toString(this.f);
        ga.a("AuthenticationWebViewClient");
        if (!this.m.a(str, this.l)) {
            this.m.b(this.l);
        }
        ((AuthPortalUIActivity.e) this.b).a(str);
        URI uriC = c(str);
        if (a(uriC)) {
            a aVar = this.b;
            Bundle bundleA = y.a(MAPError.CommonError.OPERATION_CANCELLED, "Registration canceled", 4, "Registration canceled");
            AuthPortalUIActivity.e eVar = (AuthPortalUIActivity.e) aVar;
            AuthPortalUIActivity authPortalUIActivity = AuthPortalUIActivity.this;
            String str2 = AuthPortalUIActivity.Q;
            authPortalUIActivity.a();
            AuthPortalUIActivity.a(AuthPortalUIActivity.this, bundleA);
            if (this.k == null) {
                return;
            }
            if (this.o) {
                this.k.b("DCQCanceled");
            }
            if (this.n) {
                this.k.b("MFACanceled");
                return;
            }
            return;
        }
        if (c(uriC)) {
            a(str);
            Log.w(ga.a("AuthenticationWebViewClient"), "ReturnToUrl is loaded by webview! This shouldn't happen");
            yd.b("ReturnToUrl_OnPageStarted", new String[0]);
            return;
        }
        if (b(str) && (this.n || this.o)) {
            AuthPortalUIActivity.e eVar2 = (AuthPortalUIActivity.e) this.b;
            eVar2.getClass();
            String str3 = AuthPortalUIActivity.Q;
            ga.a(str3);
            if (AuthPortalUIActivity.this.D.n && AuthPortalUIActivity.this.e != null) {
                ga.a(str3);
                AuthPortalUIActivity.this.e.d();
                AuthPortalUIActivity.this.e = null;
            }
            if (AuthPortalUIActivity.this.D.o && AuthPortalUIActivity.this.f != null) {
                ga.a(str3);
                AuthPortalUIActivity.this.f.d();
                AuthPortalUIActivity.this.f = null;
            }
        }
        if (str.contains("ap/dcq")) {
            y5 y5Var = this.k;
            if (y5Var != null) {
                y5Var.b("WebView:ContactedDCQ:" + this.g.name());
                de deVarB = this.k.e.b("DCQ:PageRender");
                deVarB.b();
                this.i = deVarB;
            }
            this.o = true;
            this.n = false;
            return;
        }
        if (!str.contains("ap/mfa")) {
            this.n = false;
            this.o = false;
            return;
        }
        y5 y5Var2 = this.k;
        if (y5Var2 != null) {
            y5Var2.b("WebView:ContactedMFA:" + this.g.name());
            de deVarB2 = this.k.e.b("MFA:PageRender");
            deVarB2.b();
            this.h = deVarB2;
        }
        this.n = true;
        this.o = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01e0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x019c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // android.webkit.WebViewClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean shouldOverrideUrlLoading(android.webkit.WebView r12, java.lang.String r13) {
        /*
            Method dump skipped, instruction units count: 503
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.q4.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
    }

    public static boolean b(URI uri) {
        if (uri == null) {
            return false;
        }
        boolean z = TextUtils.equals("/ap/mapcancel", uri.getPath()) || TextUtils.equals("/ap/mapcancel/", uri.getPath());
        ga.a("AuthenticationWebViewClient");
        return z;
    }

    public static boolean a(URI uri) {
        boolean z = false;
        if (uri == null) {
            return false;
        }
        if (uri.getQuery() == null && (TextUtils.equals("/gp/yourstore/home", uri.getPath()) || TextUtils.equals("/gp/yourstore/home/", uri.getPath()))) {
            z = true;
        }
        ga.a("AuthenticationWebViewClient");
        return z;
    }
}
