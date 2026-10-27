package com.amazon.identity.auth.device;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.api.RegistrationType;
import com.amazon.identity.auth.device.api.TokenManagement;
import com.amazon.identity.auth.device.api.WebViewHelper;
import com.amazon.identity.auth.device.attribute.DeviceAttribute;
import com.amazon.identity.auth.device.callback.RemoteCallbackWrapper;
import com.amazon.identity.auth.device.d5;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import com.amazon.identity.auth.device.framework.MAPRuntimePermissionHandler;
import com.amazon.identity.auth.device.framework.MAPSmsReceiver;
import com.amazon.identity.auth.device.framework.smartlock.CustomerInformationManager;
import com.amazon.identity.auth.device.me;
import com.amazon.identity.auth.device.q4;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.auth.device.utils.ResourceHelper;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class AuthPortalUIActivity extends Activity {
    public static final String Q = "com.amazon.identity.auth.device.AuthPortalUIActivity";
    public int A;
    public WebView B;
    public y5 C;
    public q4 D;
    public MAPSmsReceiver E;
    public d5 F;
    public boolean G;
    public ValueCallback<Uri[]> H;
    public CustomerInformationManager I;
    public r6 J;
    public String L;
    public Set<String> M;
    public u9 N;
    public de e;
    public de f;
    public String g;
    public Timer i;
    public MAPAccountManager j;
    public t5 k;
    public i5 l;
    public BackwardsCompatiableDataStorage m;
    public AmazonAccountManager n;
    public RemoteCallbackWrapper o;
    public String p;
    public String q;
    public Bundle r;
    public String s;
    public Set<String> t;
    public String u;
    public String v;
    public String w;
    public boolean x;
    public boolean y;
    public boolean z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public de f157a = null;
    public de b = null;
    public de c = null;
    public de d = null;
    public de h = null;
    public AtomicBoolean K = new AtomicBoolean(false);
    public AtomicBoolean O = new AtomicBoolean(false);
    public int P = 0;

    /* JADX INFO: compiled from: DCP */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ RemoteCallbackWrapper f158a;
        public final /* synthetic */ Bundle b;

        public a(RemoteCallbackWrapper remoteCallbackWrapper, Bundle bundle) {
            this.f158a = remoteCallbackWrapper;
            this.b = bundle;
        }

        @Override // java.lang.Runnable
        public void run() {
            RemoteCallbackWrapper remoteCallbackWrapper = this.f158a;
            if (remoteCallbackWrapper != null) {
                remoteCallbackWrapper.onSuccess(this.b);
            }
            AuthPortalUIActivity.this.finish();
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class b implements Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ boolean f159a;
        public final /* synthetic */ Bundle b;
        public final /* synthetic */ Callback c;
        public final /* synthetic */ x3 d;

        public b(boolean z, Bundle bundle, Callback callback, x3 x3Var) {
            this.f159a = z;
            this.b = bundle;
            this.c = callback;
            this.d = x3Var;
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle bundle) {
            Log.i(ga.a(AuthPortalUIActivity.Q), "Exchange token with authorization code failed");
            AuthPortalUIActivity.a(AuthPortalUIActivity.this, bundle, AuthPortalUIActivity.this.b());
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle bundle) {
            String str = AuthPortalUIActivity.Q;
            Log.i(ga.a(str), "Exchange token with authorization code succeed");
            String string = bundle.getString("value_key");
            if (!this.f159a) {
                AuthPortalUIActivity.this.a(this.d, string);
                return;
            }
            Log.i(ga.a(str), "Continue to perform device registration through FIRS");
            this.b.putString("com.amazon.dcp.sso.AddAccount.options.AccessToken", string);
            AuthPortalUIActivity.this.j.registerAccount(RegistrationType.FROM_ACCESS_TOKEN, this.b, this.c);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class c implements d5.c {
        public c() {
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ WebView f161a;
        public final /* synthetic */ OpenIdRequest b;

        public d(WebView webView, OpenIdRequest openIdRequest) {
            this.f161a = webView;
            this.b = openIdRequest;
        }

        @Override // java.lang.Runnable
        public void run() {
            AuthPortalUIActivity authPortalUIActivity = AuthPortalUIActivity.this;
            String strA = a7.a(authPortalUIActivity.k, authPortalUIActivity.getPackageName(), authPortalUIActivity.C, authPortalUIActivity.G);
            if (!TextUtils.isEmpty(strA)) {
                ab.a(authPortalUIActivity.k, authPortalUIActivity.s, "map-md", strA, "/ap", null, true, true, true);
            }
            AuthPortalUIActivity authPortalUIActivity2 = AuthPortalUIActivity.this;
            authPortalUIActivity2.runOnUiThread(new z1(authPortalUIActivity2, this.f161a, this.b));
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class e implements q4.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ OpenIdRequest f162a;
        public final /* synthetic */ String b;

        public e(OpenIdRequest openIdRequest, String str) {
            this.f162a = openIdRequest;
            this.b = str;
        }

        public void a(String str) {
            if (AuthPortalUIActivity.this.B != null) {
                String str2 = qe.f519a;
                try {
                } catch (Exception unused) {
                    Log.i(ga.a(qe.f519a), "Not able to identify if it's HarmonyOS");
                }
                boolean z = Build.MANUFACTURER.toLowerCase(Locale.US).contains("huawei");
                if (z) {
                    AuthPortalUIActivity.this.B.resumeTimers();
                }
            }
            AuthPortalUIActivity.this.b(str);
            de deVar = AuthPortalUIActivity.this.f157a;
            if (deVar != null) {
                double d = deVar.d();
                me.a aVar = (me.a) yd.a();
                aVar.f438a = AuthPortalUIActivity.this.C.f667a;
                aVar.b = "AuthPortalUIActivity_FirstOnPageStarted";
                aVar.d = com.amazon.identity.auth.device.e.b(str);
                aVar.f = Double.valueOf(d);
                aVar.a().a();
            }
            String str3 = "AuthPortalPageTimeout:" + this.f162a.b.name();
            if (AuthPortalUIActivity.this.D.n) {
                str3 = str3 + ":MFA";
            } else if (AuthPortalUIActivity.this.D.o) {
                str3 = str3 + ":DCQ";
            }
            AuthPortalUIActivity authPortalUIActivity = AuthPortalUIActivity.this;
            authPortalUIActivity.g = str3;
            authPortalUIActivity.a();
            AuthPortalUIActivity.this.i = new Timer();
            AuthPortalUIActivity.this.i.schedule(AuthPortalUIActivity.this.new g(), 250000L);
            AuthPortalUIActivity.this.g();
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ProgressBar f163a;

        public f(ProgressBar progressBar) {
            this.f163a = progressBar;
        }

        @Override // java.lang.Runnable
        public void run() {
            AuthPortalUIActivity authPortalUIActivity = AuthPortalUIActivity.this;
            if (authPortalUIActivity.z && authPortalUIActivity.x && !authPortalUIActivity.y) {
                Animation animationLoadAnimation = AnimationUtils.loadAnimation(authPortalUIActivity.getBaseContext(), ResourceHelper.a(authPortalUIActivity, "anim", "delay_fade_anim"));
                authPortalUIActivity.y = true;
                ProgressBar progressBar = (ProgressBar) authPortalUIActivity.findViewById(authPortalUIActivity.A);
                progressBar.startAnimation(animationLoadAnimation);
                progressBar.setVisibility(4);
            }
            if (AuthPortalUIActivity.this.x) {
                this.f163a.setVisibility(4);
            }
        }
    }

    /* JADX INFO: compiled from: DCP */
    public class g extends TimerTask {
        public g() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (AuthPortalUIActivity.this.C != null) {
                AuthPortalUIActivity.this.C.b(AuthPortalUIActivity.this.g + ":NetworkState:" + com.amazon.identity.auth.device.e.g(AuthPortalUIActivity.this.k));
            }
            me.a aVar = (me.a) yd.a();
            aVar.f438a = "NetworkError2:AuthPortalUIActivity";
            aVar.a().a();
            yd.b("NetworkError2:AuthPortalUIActivity", new String[0]);
            AuthPortalUIActivity.a(AuthPortalUIActivity.this, y.b(MAPError.CommonError.NETWORK_ERROR, "Unable to render content. Request timed out.", MAPAccountManager.RegistrationError.NETWORK_FAILURE.value(), "Unable to render content. Request timed out."));
        }
    }

    public static void a(AuthPortalUIActivity authPortalUIActivity, Bundle bundle, RemoteCallbackWrapper remoteCallbackWrapper) {
        authPortalUIActivity.getClass();
        va.a(new d2(authPortalUIActivity, bundle, remoteCallbackWrapper));
    }

    public final void b(String str) {
        String strA;
        if (TextUtils.isEmpty(this.L) || (strA = ab.a(str)) == null || this.M.contains(strA)) {
            return;
        }
        ga.a(Q);
        ab.a(this.k, strA, "frc", this.L, "/ap", null, true, true, true);
        this.M.add(strA);
    }

    public final RelativeLayout c() {
        return (RelativeLayout) findViewById(ResourceHelper.a(this, "id", "apparentlayout"));
    }

    public final WebView d() {
        WebView webView = this.B;
        if (webView != null) {
            return webView;
        }
        WebView webView2 = (WebView) findViewById(ResourceHelper.a(this, "id", "apwebview"));
        this.B = webView2;
        return webView2;
    }

    public final void e() {
        if (this.D.n && this.e == null && this.C != null) {
            ga.a(Q);
            de deVarB = this.C.e.b("MFA:ChallengeCodeEnterTime");
            deVarB.b();
            this.e = deVarB;
        }
        if (this.D.o && this.f == null && this.C != null) {
            ga.a(Q);
            de deVarB2 = this.C.e.b("DCQ:ChallengeQuestionEnterTime");
            deVarB2.b();
            this.f = deVarB2;
        }
        a();
        ProgressBar progressBar = (ProgressBar) findViewById(this.A);
        if (progressBar.getVisibility() == 0) {
            va.b(new f(progressBar));
        }
    }

    public final void f() {
        Bundle bundle = this.r;
        if (bundle == null) {
            return;
        }
        String string = bundle.getString("directedid");
        if (TextUtils.isEmpty(string)) {
            return;
        }
        String strC = this.m.c(string, AccountConstants.TOKEN_TYPE_COOKIE_MFA_SID_TOKEN);
        if (TextUtils.isEmpty(strC)) {
            return;
        }
        ab.a(this.k, this.s, "sid", strC, "/", v9.b(), true, true, false);
    }

    @Override // android.app.Activity
    public void finish() {
        if (this.K.get()) {
            this.K.set(false);
            super.finish();
            return;
        }
        ga.a(Q);
        Bundle bundleA = y.a(MAPError.CommonError.OPERATION_CANCELLED, "Registration canceled", 4, "Registration canceled");
        if (this.r.getBoolean("isAccountStateFixUpFlow")) {
            bundleA.putStringArrayList(MAPAccountManager.KEY_ACCOUNT_MISSING_ATTRIBUTES, this.r.getStringArrayList(MAPAccountManager.KEY_ACCOUNT_MISSING_ATTRIBUTES));
        }
        va.a(new c2(this, bundleA, b()));
    }

    public final void g() {
        this.y = false;
        if (this.x) {
            ProgressBar progressBar = (ProgressBar) findViewById(this.A);
            progressBar.setProgress(0);
            progressBar.setVisibility(0);
        }
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        Log.i(ga.a(Q), "AuthPortalUIActivity onActivityResult()");
        if (i != 1) {
            if (i != 2) {
                return;
            }
            this.I.a(i2, intent);
            return;
        }
        ValueCallback<Uri[]> valueCallback = this.H;
        if (valueCallback == null) {
            return;
        }
        if (intent == null || i2 != -1) {
            valueCallback.onReceiveValue(new Uri[0]);
            this.H = null;
        } else {
            String dataString = intent.getDataString();
            this.H.onReceiveValue(!TextUtils.isEmpty(dataString) ? new Uri[]{Uri.parse(dataString)} : null);
            this.H = null;
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.K.get()) {
            return;
        }
        de deVar = this.d;
        if (deVar != null) {
            double d2 = deVar.d();
            me.a aVar = (me.a) yd.a();
            aVar.f438a = this.C.f667a;
            aVar.b = "AuthPortalUIActivity_BackPressedInWebView";
            aVar.d = com.amazon.identity.auth.device.e.b(this.s);
            aVar.f = Double.valueOf(d2);
            aVar.a().a();
        }
        super.onBackPressed();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        try {
            WebView webViewD = d();
            if (webViewD != null) {
                webViewD.invalidate();
            }
        } catch (NoSuchFieldError e2) {
            String str = Q;
            e2.getMessage();
            ga.a(str);
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        String string;
        try {
            String str = Q;
            Log.i(ga.a(str), String.format("Login webview called in package %s by package %s", getPackageName(), getCallingPackage()));
            com.amazon.identity.auth.device.e.a((Activity) this);
            this.k = t5.a(getApplicationContext());
            this.j = new MAPAccountManager(this.k);
            this.n = new AmazonAccountManager(this.k);
            this.l = (i5) this.k.getSystemService("sso_platform");
            t5 t5Var = this.k;
            this.m = new BackwardsCompatiableDataStorage(t5Var, t5Var.a());
            this.M = new HashSet();
            this.t = new HashSet();
            this.u = qe.c(this);
            String strA = oa.a(this.k, DeviceAttribute.CentralDeviceType);
            this.v = strA;
            this.w = OpenIdRequest.a(this.u, strA);
            u9 u9Var = new u9();
            this.N = u9Var;
            u9Var.a();
            Intent intent = getIntent();
            b(intent);
            requestWindowFeature(1);
            super.onCreate(bundle);
            if (!TextUtils.isEmpty(this.u) && !TextUtils.isEmpty(this.v)) {
                OpenIdRequest openIdRequestA = a(intent);
                setContentView(ResourceHelper.a(this, "layout", "apwebviewlayout"));
                WebView webViewB = b(bundle);
                if (webViewB == null) {
                    va.a(new c2(this, y.b(MAPError.CommonError.INTERNAL_ERROR, "An unexpected error occured while setting up the WebView.", MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), "An unexpected error occured while setting up the WebView."), b()));
                    return;
                }
                this.E = new MAPSmsReceiver(this.C, webViewB);
                if (TextUtils.isEmpty(this.r.getString("directedid"))) {
                    ga.a(str);
                    string = null;
                } else {
                    string = this.r.getString("directedid");
                    ga.a(str);
                }
                a(openIdRequestA, string);
                this.I = new CustomerInformationManager(this, 2);
                this.J = new r6(this.k, this.E);
                if (!WebViewHelper.enableAmazonAuthenticatorForWebView(webViewB, null)) {
                    Log.i(ga.a(str), "Error occurred while enabling Amazon Authenticator JS bridge for MAP WebView");
                }
                a(webViewB, this.I, this.J, new c());
                String strA2 = openIdRequestA.a();
                this.s = strA2;
                a(strA2, "sid", "/", v9.b(), false);
                f();
                this.L = a7.a((Context) this.k, this.u);
                b(this.s);
                a(this.s);
                a(this.r);
                webViewB.setWebChromeClient(new a2(this));
                a(webViewB, this.r);
                if (!this.r.containsKey("domain_hint") || !this.r.containsKey("ab_federated_auth")) {
                    this.O.set(false);
                    this.P = 0;
                    va.a(new d(webViewB, openIdRequestA));
                    return;
                }
                this.O.set(true);
                this.P++;
                com.amazon.identity.auth.device.e.a(this, Uri.parse(openIdRequestA.d()), this.r.getString(MAPAccountManager.KEY_COLOR_CODE_FOR_FEDERATED_CUSTOM_TAB));
                yd.b("chromeCustomTabLaunch:OpenFederatedAuthRegistrationRequest:Url=" + openIdRequestA.d(), new String[0]);
                me.a aVar = (me.a) yd.a();
                aVar.f438a = "chromeCustomTabLaunch:OpenFederatedAuthRegistrationRequest";
                aVar.d = openIdRequestA.d();
                aVar.a().a();
                openIdRequestA.d();
                ga.a(str);
                return;
            }
            va.a(new c2(this, y.b(MAPError.CommonError.INTERNAL_ERROR, "An unexpected error occurred while setting up the WebView. Cannot fetch client id. If this is an unregistered Grover device or Canary device, this error is expected.", MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), "An unexpected error occured while setting up the WebView. Cannot fetch client id! If it is an unregistered Grover(versions lesser than )/Canary(all version) device, it is expected."), b()));
        } catch (IllegalArgumentException e2) {
            va.a(new c2(this, y.b(MAPError.CommonError.BAD_REQUEST, String.format("An IllegalArgumentException was thrown with message: %s", e2.getMessage()), MAPAccountManager.RegistrationError.BAD_REQUEST.value(), e2.getMessage()), b()));
        } catch (Exception e3) {
            va.a(new c2(this, y.b(MAPError.CommonError.INTERNAL_ERROR, String.format("An Exception was thrown with message: %s", e3.getMessage()), MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), e3.getMessage()), b()));
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        String str = Q;
        ga.a(str);
        MAPSmsReceiver mAPSmsReceiver = this.E;
        if (mAPSmsReceiver != null) {
            mAPSmsReceiver.b(this.k);
        }
        Set<String> set = this.M;
        if (set != null && set.size() > 0) {
            ga.a(str);
            Iterator<String> it = this.M.iterator();
            while (it.hasNext()) {
                a(it.next(), "frc", "/ap", null, true);
            }
            this.M.clear();
        }
        String str2 = this.s;
        if (str2 != null) {
            a(str2, "map-md", "/ap", null, true);
        }
        y5 y5Var = this.C;
        if (y5Var != null) {
            y5Var.e.a();
        }
        a();
        if (this.B != null) {
            c().removeView(this.B);
            this.B.removeAllViews();
            this.B.destroy();
            this.B = null;
        }
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        WebHistoryItem itemAtIndex;
        if (i == 4) {
            if (this.D.n && this.C != null) {
                Log.i(ga.a(Q), "MFA canceled");
                this.C.b("MFACanceled");
            }
            if (this.D.o && this.C != null) {
                Log.i(ga.a(Q), "DCQ canceled");
                this.C.b("DCQCanceled");
            }
            WebView webViewD = d();
            if (webViewD == null) {
                va.a(new c2(this, y.b(MAPError.CommonError.INTERNAL_ERROR, "An unexpected error occured while setting up the WebView.", MAPAccountManager.RegistrationError.UNRECOGNIZED.value(), "An unexpected error occured while setting up the WebView."), b()));
                return false;
            }
            if (webViewD.canGoBack()) {
                WebBackForwardList webBackForwardListCopyBackForwardList = webViewD.copyBackForwardList();
                if (webBackForwardListCopyBackForwardList != null && webBackForwardListCopyBackForwardList.getSize() > 0 && (itemAtIndex = webBackForwardListCopyBackForwardList.getItemAtIndex(webBackForwardListCopyBackForwardList.getCurrentIndex() - 1)) != null) {
                    if (this.E.a(itemAtIndex.getUrl(), this.k)) {
                        if (webViewD.canGoBackOrForward(-2)) {
                            webViewD.goBackOrForward(-2);
                            return true;
                        }
                        ga.a(Q, this.C, "Cannot go the the page before previous page. Something is wrong.", "SkipAutoPhoneVerificationPageError");
                        finish();
                        return false;
                    }
                }
                webViewD.goBack();
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        String str = Q;
        Log.i(ga.a(str), String.format("Existing login webview called in package %s by package %s", getPackageName(), getCallingPackage()));
        com.amazon.identity.auth.device.e.a((Activity) this);
        RemoteCallbackWrapper remoteCallbackWrapperB = b();
        Uri data = intent.getData();
        if (!(data != null && TextUtils.equals(data.getPath(), "/ap/maplanding"))) {
            if (remoteCallbackWrapperB != null) {
                remoteCallbackWrapperB.onError(y.a(MAPError.CommonError.OPERATION_CANCELLED, "Registration canceled", 4, "Registration canceled"));
            }
            b(intent);
            OpenIdRequest openIdRequestA = a(intent);
            Uri uri = Uri.parse(openIdRequestA.d());
            if (!("true".equalsIgnoreCase(uri.getQueryParameter("ab_federated_auth")) && !TextUtils.isEmpty(uri.getQueryParameter("domain_hint")))) {
                this.O.set(false);
                this.P = 0;
                d().loadUrl(openIdRequestA.d());
                return;
            }
            if (this.O.get()) {
                return;
            }
            this.O.set(true);
            this.P++;
            com.amazon.identity.auth.device.e.a(this, uri, this.r.getString(MAPAccountManager.KEY_COLOR_CODE_FOR_FEDERATED_CUSTOM_TAB));
            yd.b("chromeCustomTabLaunch:OpenFederatedAuthRegistrationRequest:Url=" + uri.toString(), new String[0]);
            me.a aVar = (me.a) yd.a();
            aVar.f438a = "chromeCustomTabLaunch:OpenFederatedAuthRegistrationRequest";
            aVar.d = uri.toString();
            aVar.a().a();
            uri.toString();
            ga.a(str);
            return;
        }
        this.P++;
        x3 x3Var = new x3(data.toString());
        this.K.set(false);
        this.o = remoteCallbackWrapperB;
        if (!this.x) {
            this.x = true;
            this.z = true;
            MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState progressBarState = MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState.get("spinner_medium");
            MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition screenPosition = MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition.get("center_center");
            this.z = true;
            int iA = a(progressBarState, false);
            ProgressBar progressBar = (ProgressBar) findViewById(this.A);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) progressBar.getLayoutParams();
            layoutParams.width = -2;
            progressBar.setVisibility(8);
            ProgressBar progressBar2 = new ProgressBar(this, null, iA);
            c().addView(progressBar2);
            int iA2 = ResourceHelper.a(this, "id", "apspinner_progressbar");
            progressBar2.setId(iA2);
            this.A = iA2;
            if (this.x) {
                progressBar2.setLayoutParams(a(layoutParams, screenPosition));
                progressBar2.bringToFront();
            }
        }
        g();
        getWindow().setFlags(16, 16);
        a(60);
        MAPSmsReceiver mAPSmsReceiver = this.E;
        synchronized (mAPSmsReceiver) {
            if (mAPSmsReceiver.e) {
                mAPSmsReceiver.f325a.b("MOA:AutoPVSuccess");
            }
        }
        a(OpenIdRequest.REQUEST_TYPE.SIGN_IN, x3Var, (String) null);
        yd.b("federatedAuthenticationCallbackUrlSuccess", new String[0]);
        me.a aVar2 = (me.a) yd.a();
        aVar2.f438a = "federatedAuthenticationCallbackUrlSuccess";
        aVar2.a().a();
        ga.a(str);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        MAPRuntimePermissionHandler mAPRuntimePermissionHandler = MAPRuntimePermissionHandler.e.get(Integer.valueOf(i));
        if (mAPRuntimePermissionHandler == null) {
            super.onRequestPermissionsResult(i, strArr, iArr);
            return;
        }
        t5 t5Var = this.k;
        d5 d5Var = this.F;
        va.a(new f5(mAPRuntimePermissionHandler, t5Var, this.C, this.B, this.G, d5Var));
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.O.get()) {
            int i = this.P - 1;
            this.P = i;
            if (i < 0) {
                yd.b("chromeCustomTabClosed:CloseFederatedAuthRegistrationRequest", new String[0]);
                me.a aVar = (me.a) yd.a();
                aVar.f438a = "chromeCustomTabClosed:CloseFederatedAuthRegistrationRequest";
                aVar.a().a();
                ga.a(Q);
                onBackPressed();
            }
        }
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        WebView webViewD = d();
        if (webViewD != null) {
            webViewD.saveState(bundle);
        }
    }

    public final void b(Intent intent) {
        int[] intArray;
        Window window = getWindow();
        if (window != null) {
            window.addFlags(65536);
            window.addFlags(256);
            window.addFlags(8192);
            Bundle extras = intent.getExtras();
            if (extras == null || (intArray = extras.getIntArray(MAPAccountManager.AuthPortalActivityUIOptions.KEY_WINDOW_FLAGS)) == null) {
                return;
            }
            for (int i : intArray) {
                window.addFlags(i);
            }
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public final WebView b(Bundle bundle) {
        WebView webViewD = d();
        if (webViewD == null) {
            return null;
        }
        if (bundle != null) {
            webViewD.restoreState(bundle);
        }
        webViewD.setScrollBarStyle(0);
        WebSettings settings = webViewD.getSettings();
        settings.setSavePassword(false);
        settings.setSaveFormData(false);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setAllowFileAccess(false);
        settings.setDomStorageEnabled(true);
        settings.setAllowContentAccess(false);
        settings.setMixedContentMode(1);
        webViewD.clearFormData();
        webViewD.getSettings().setJavaScriptEnabled(true);
        String str = Q;
        settings.getUserAgentString();
        ga.a(str);
        return webViewD;
    }

    public final RemoteCallbackWrapper b() {
        RemoteCallbackWrapper remoteCallbackWrapper = this.o;
        this.o = null;
        return remoteCallbackWrapper;
    }

    public final void a(WebView webView, Bundle bundle) {
        String string;
        int i;
        ImageView imageView = (ImageView) findViewById(ResourceHelper.a(this, "id", "apimageview"));
        if (bundle != null) {
            i = bundle.getInt(MAPAccountManager.AuthPortalActivityUIOptions.KEY_SPLASH_SCREEN_RESOURCE, -1);
            string = bundle.getString(MAPAccountManager.AuthPortalActivityUIOptions.KEY_SPLASH_SCREEN_SCALE_TYPE);
        } else {
            string = null;
            i = -1;
        }
        if (-1 == i) {
            imageView.setVisibility(8);
            webView.setVisibility(0);
            webView.requestFocusFromTouch();
        } else {
            imageView.setImageResource(i);
            if (TextUtils.isEmpty(string)) {
                return;
            }
            imageView.setScaleType(ImageView.ScaleType.valueOf(string));
        }
    }

    public final void a(String str, String str2, String str3, String str4, boolean z) {
        ab.a(this.k, str, str2, "", str3, str4, true, true, z);
    }

    public final void a(Bundle bundle) {
        boolean z;
        boolean z2;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState progressBarState = MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState.OFF;
        MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition screenPosition = MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition.BOTTOM_CENTER;
        this.z = true;
        if (bundle != null) {
            String string = bundle.getString(MAPAccountManager.AuthPortalActivityUIOptions.KEY_PROGRESSBAR_STATE);
            if (string != null) {
                progressBarState = MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState.get(string);
            }
            String string2 = bundle.getString(MAPAccountManager.AuthPortalActivityUIOptions.KEY_PROGRESSBAR_POSITION);
            if (string2 != null) {
                screenPosition = MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition.get(string2);
            }
            this.z = bundle.getBoolean(MAPAccountManager.AuthPortalActivityUIOptions.KEY_PROGRESSBAR_FADE, this.z);
            z = bundle.getBoolean(MAPAccountManager.AuthPortalActivityUIOptions.KEY_PROGRESSBAR_STRETCH, true);
            z2 = bundle.getBoolean(MAPAccountManager.AuthPortalActivityUIOptions.KEY_INVERT_SPINNER, false);
            i2 = bundle.getInt(MAPAccountManager.AuthPortalActivityUIOptions.KEY_PROGRESSBAR_RESOURCE, -1);
            i3 = bundle.getInt(MAPAccountManager.AuthPortalActivityUIOptions.KEY_PROGRESSBAR_BACKGROUND_RESOURCE, -1);
            i4 = bundle.getInt(MAPAccountManager.AuthPortalActivityUIOptions.KEY_PROGRESSBAR_PRIMARY_COLOR, -1);
            i = bundle.getInt(MAPAccountManager.AuthPortalActivityUIOptions.KEY_PROGRESSBAR_SECONDARY_COLOR, -1);
        } else {
            z = true;
            z2 = false;
            i = -1;
            i2 = -1;
            i3 = -1;
            i4 = -1;
        }
        int iA = ResourceHelper.a(this, "id", "approgressbar");
        this.A = iA;
        ProgressBar progressBar = (ProgressBar) findViewById(iA);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) progressBar.getLayoutParams();
        int iOrdinal = progressBarState.ordinal();
        if (iOrdinal == 0) {
            this.x = false;
            progressBar.setVisibility(8);
        } else if (iOrdinal != 1) {
            this.x = true;
            int iA2 = a(progressBarState, z2);
            layoutParams.width = -2;
            progressBar.setVisibility(8);
            progressBar = new ProgressBar(this, null, iA2);
            c().addView(progressBar);
            int iA3 = ResourceHelper.a(this, "id", "apspinner_progressbar");
            progressBar.setId(iA3);
            this.A = iA3;
        } else {
            this.x = true;
            if (z) {
                i5 = -1;
                layoutParams.width = -1;
            } else {
                i5 = -1;
                layoutParams.width = -2;
            }
            if (i5 != i2) {
                Drawable drawable = getResources().getDrawable(i2);
                progressBar.setProgressDrawable(new ClipDrawable(drawable, 3, 1));
                if (i5 != i3) {
                    progressBar.setBackgroundDrawable(getResources().getDrawable(i3));
                }
                layoutParams.width = drawable.getMinimumWidth();
            } else if (i5 != i4) {
                if (i5 == i) {
                    i = i4;
                }
                GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{i4, i});
                gradientDrawable.setShape(0);
                gradientDrawable.setCornerRadius(5.0f);
                progressBar.setProgressDrawable(new ClipDrawable(gradientDrawable, 3, 1));
                progressBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.progress_horizontal));
            }
        }
        if (this.x) {
            progressBar.setLayoutParams(a(layoutParams, screenPosition));
            progressBar.bringToFront();
        }
    }

    public final int a(MAPAccountManager.AuthPortalActivityUIOptions.ProgressBarState progressBarState, boolean z) {
        int iOrdinal = progressBarState.ordinal();
        if (iOrdinal == 2) {
            return z ? R.attr.progressBarStyleSmallInverse : R.attr.progressBarStyleSmall;
        }
        if (iOrdinal == 3) {
            return z ? R.attr.progressBarStyleInverse : R.attr.progressBarStyle;
        }
        if (iOrdinal == 4) {
            return z ? R.attr.progressBarStyleLargeInverse : R.attr.progressBarStyleLarge;
        }
        Log.e(ga.a(Q), "Bar State not recongized");
        return 0;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static RelativeLayout.LayoutParams a(RelativeLayout.LayoutParams layoutParams, MAPAccountManager.AuthPortalActivityUIOptions.ScreenPosition screenPosition) {
        ga.a(Q, "Bar Pos: %s", screenPosition.getValue());
        switch (screenPosition) {
            case TOP_LEFT:
            case TOP_CENTER:
            case TOP_RIGHT:
                layoutParams.addRule(10);
                break;
            case CENTER_LEFT:
            case CENTER_CENTER:
            case CENTER_RIGHT:
                layoutParams.addRule(15);
                break;
            case BOTTOM_LEFT:
            case BOTTOM_CENTER:
            case BOTTOM_RIGHT:
                layoutParams.addRule(12);
                break;
        }
        switch (screenPosition) {
            case TOP_LEFT:
            case CENTER_LEFT:
            case BOTTOM_LEFT:
                layoutParams.addRule(9);
                return layoutParams;
            case TOP_CENTER:
            case CENTER_CENTER:
            case BOTTOM_CENTER:
                layoutParams.addRule(14);
                return layoutParams;
            case TOP_RIGHT:
            case CENTER_RIGHT:
            case BOTTOM_RIGHT:
                layoutParams.addRule(11);
                return layoutParams;
            default:
                return layoutParams;
        }
    }

    public final void a(OpenIdRequest openIdRequest, String str) {
        e eVar = new e(openIdRequest, str);
        Bundle bundle = this.r;
        q4 q4Var = new q4(this.k, this.E, openIdRequest.b, openIdRequest.d, OpenIdRequest.TOKEN_SCOPE.ACCESS, this.t, bundle == null ? false : bundle.getBoolean(MAPAccountManager.KEY_ALLOW_ALL_SIGNIN_PATHS), eVar, this.C);
        this.D = q4Var;
        this.B.setWebViewClient(q4Var);
    }

    public final boolean a(OpenIdRequest.REQUEST_TYPE request_type) {
        if (request_type.equals(OpenIdRequest.REQUEST_TYPE.CONFIRM_CREDENTIAL)) {
            Bundle bundle = this.r;
            return bundle != null && bundle.getBoolean(AccountConstants.KEY_RECOVERY_ATTEMPT);
        }
        return !request_type.equals(OpenIdRequest.REQUEST_TYPE.AUTHENTICATE);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0135  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(com.amazon.identity.auth.device.endpoint.OpenIdRequest.REQUEST_TYPE r19, com.amazon.identity.auth.device.x3 r20, java.lang.String r21) {
        /*
            Method dump skipped, instruction units count: 900
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.AuthPortalUIActivity.a(com.amazon.identity.auth.device.endpoint.OpenIdRequest$REQUEST_TYPE, com.amazon.identity.auth.device.x3, java.lang.String):void");
    }

    public final void a(x3 x3Var, String str) {
        RemoteCallbackWrapper remoteCallbackWrapperB = b();
        Bundle bundle = new Bundle();
        String strC = x3Var.c();
        bundle.putString("com.amazon.dcp.sso.property.account.acctId", strC);
        if (TextUtils.isEmpty(str)) {
            bundle.putString("com.amazon.dcp.sso.AddAccount.options.AccessToken", this.m.c(strC, "com.amazon.dcp.sso.token.oauth.amazon.access_token"));
        } else {
            bundle.putString("com.amazon.dcp.sso.AddAccount.options.AccessToken", str);
        }
        a(bundle, remoteCallbackWrapperB);
    }

    public final void a(int i) {
        if (this.x) {
            ProgressBar progressBar = (ProgressBar) findViewById(this.A);
            if (progressBar.getVisibility() == 0) {
                progressBar.setProgress(i);
            }
        }
    }

    public static void a(AuthPortalUIActivity authPortalUIActivity, Bundle bundle) {
        va.a(new c2(authPortalUIActivity, bundle, authPortalUIActivity.b()));
    }

    public final void a(Bundle bundle, RemoteCallbackWrapper remoteCallbackWrapper) {
        va.a(new a(remoteCallbackWrapper, bundle));
    }

    public static void a(AuthPortalUIActivity authPortalUIActivity) {
        authPortalUIActivity.K.set(false);
        super.finish();
    }

    public final void a() {
        Timer timer = this.i;
        if (timer != null) {
            timer.cancel();
            this.i = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00f3 A[PHI: r6
  0x00f3: PHI (r6v19 com.amazon.identity.auth.device.endpoint.OpenIdRequest$REQUEST_TYPE) = 
  (r6v11 com.amazon.identity.auth.device.endpoint.OpenIdRequest$REQUEST_TYPE)
  (r6v12 com.amazon.identity.auth.device.endpoint.OpenIdRequest$REQUEST_TYPE)
  (r6v15 com.amazon.identity.auth.device.endpoint.OpenIdRequest$REQUEST_TYPE)
  (r6v16 com.amazon.identity.auth.device.endpoint.OpenIdRequest$REQUEST_TYPE)
  (r6v17 com.amazon.identity.auth.device.endpoint.OpenIdRequest$REQUEST_TYPE)
  (r6v18 com.amazon.identity.auth.device.endpoint.OpenIdRequest$REQUEST_TYPE)
 binds: [B:46:0x00f1, B:49:0x00ff, B:55:0x0117, B:58:0x0124, B:61:0x0131, B:64:0x013e] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.amazon.identity.auth.device.endpoint.OpenIdRequest a(android.content.Intent r20) {
        /*
            Method dump skipped, instruction units count: 744
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.AuthPortalUIActivity.a(android.content.Intent):com.amazon.identity.auth.device.endpoint.OpenIdRequest");
    }

    @SuppressLint({"AddJavascriptInterface"})
    public void a(WebView webView, CustomerInformationManager customerInformationManager, r6 r6Var, d5.c cVar) {
        d5 d5Var = new d5(cVar);
        this.F = d5Var;
        webView.addJavascriptInterface(d5Var, "embedNotification");
        webView.addJavascriptInterface(new v6(webView, customerInformationManager, r6Var), "MAPAndroidJSBridge");
    }

    public final void a(String str) {
        if (this.r.containsKey("InjectCookiesToAuthPortalUIActivity")) {
            Log.i(ga.a(Q), "Need to inject the cookies into the webview.");
            String[] stringArray = this.r.getStringArray("InjectCookiesToAuthPortalUIActivity");
            if (stringArray != null) {
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.setAcceptCookie(true);
                bb.a(this.k);
                for (String str2 : stringArray) {
                    ga.a(Q);
                    cookieManager.setCookie(str, str2);
                }
                bb.a(this.k);
            }
        }
    }

    public final void a(x3 x3Var, boolean z, Callback callback, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString(AccountConstants.KEY_AUTHORIZATION_CODE, x3Var.b());
        bundle2.putString(AccountConstants.KEY_CODE_VERIFIER, this.N.f613a);
        bundle2.putString(AccountConstants.KEY_CODE_CHALLENGE_METHOD, this.N.b);
        bundle2.putString(AccountConstants.KEY_CLIENT_DOMAIN, AccountConstants.CLIENT_DOMAIN_DEVICE_LEGACY);
        bundle2.putString(AccountConstants.KEY_CLIENT_ID, this.w);
        bundle2.putBoolean("authorizationCode", true);
        new TokenManagement(this.k).getToken(x3Var.c(), "com.amazon.dcp.sso.token.oauth.amazon.access_token", bundle2, new b(z, bundle, callback, x3Var));
    }

    public final boolean a(x3 x3Var) {
        return !TextUtils.isEmpty(x3Var.b());
    }
}
