package com.amazon.identity.auth.device.api;

import android.app.Activity;
import android.content.Context;
import android.net.UrlQuerySanitizer;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.URLUtil;
import android.webkit.WebView;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.a9;
import com.amazon.identity.auth.device.api.CookieKeys;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.bb;
import com.amazon.identity.auth.device.e;
import com.amazon.identity.auth.device.endpoint.OpenIdRequest;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.ga;
import com.amazon.identity.auth.device.i4;
import com.amazon.identity.auth.device.v6;
import com.amazon.identity.auth.device.va;
import com.amazon.identity.auth.device.ya;
import com.amazon.identity.auth.device.yd;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidParameterException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class MAPAndroidWebViewHelper {
    public static final String AUTHENTICATOR_JS_BRIDGE = "AuthenticatorJSBridge";
    public static final String JS_BRIDGE_TYPE = "JSBridgeType";

    @FireOsSdk
    public static final String ON_REGISTRATION_SUCCESS = "ON_REGISTRATION_SUCCESS";

    @FireOsSdk
    public static final String ON_UNABLE_TO_GET_COOKIES = "ON_UNABLE_TO_GET_COOKIES";

    @FireOsSdk
    public static final String USE_ASSOC_HANDLE_IN_URL_FOR_COOKIE_EXCHANGE = "USE_ASSOC_HANDLE_IN_URL";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Activity f212a;
    public final Context b;
    public final MAPAccountManager c;
    public final TokenManagement d;
    public Parameters e = new Parameters();

    /* JADX INFO: renamed from: com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper$4, reason: invalid class name */
    /* JADX INFO: compiled from: DCP */
    public class AnonymousClass4 implements Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ WebView f216a;
        public final /* synthetic */ String b;
        public final /* synthetic */ String c;
        public final /* synthetic */ MAPAndroidWebViewNavigator d;

        public AnonymousClass4(WebView webView, String str, String str2, MAPAndroidWebViewNavigator mAPAndroidWebViewNavigator) {
            this.f216a = webView;
            this.b = str;
            this.c = str2;
            this.d = mAPAndroidWebViewNavigator;
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onError(Bundle bundle) {
            MAPAndroidWebViewHelper mAPAndroidWebViewHelper = MAPAndroidWebViewHelper.this;
            WebView webView = this.f216a;
            String str = this.b;
            String str2 = this.c;
            MAPAndroidWebViewNavigator mAPAndroidWebViewNavigator = this.d;
            mAPAndroidWebViewHelper.getClass();
            if (bundle.getInt(MAPError.KEY_ERROR_CODE) == MAPError.AccountError.ACCOUNT_ALREADY_REGISTERED.getErrorCode()) {
                mAPAndroidWebViewHelper.a(webView, bundle, str, str2, mAPAndroidWebViewNavigator);
            } else {
                Log.e(ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper"), "Error in handleAuthActivityResultError");
                mAPAndroidWebViewHelper.onEvent(MAPAndroidWebViewHelper.ON_UNABLE_TO_GET_COOKIES, bundle);
            }
        }

        @Override // com.amazon.identity.auth.device.api.Callback
        public void onSuccess(Bundle bundle) {
            MAPAndroidWebViewHelper.this.a(this.f216a, bundle, this.b, this.c, this.d);
        }
    }

    /* JADX INFO: compiled from: DCP */
    public static class Parameters {
        public Bundle additionalSignInParams;
        public String associationHandle;
        public String claimedId;
        public String clientContext;
        public String domain;
        public String identity;
        public boolean isFederatedAuthentication;
        public String pageId;
        public String prompt;
        public String requestType;
        public String returnToURL;
        public String userAgent;

        public Parameters() {
        }
    }

    /* JADX INFO: compiled from: DCP */
    public enum URL_TYPE {
        REGULAR,
        SIGNIN,
        REGISTER,
        CONFIRM_CREDENTIAL
    }

    @FireOsSdk
    public MAPAndroidWebViewHelper(Context context) {
        e.a((Object) context, "Context");
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            this.f212a = activity;
            this.b = activity.getBaseContext().getApplicationContext();
        } else {
            this.f212a = null;
            this.b = context.getApplicationContext();
        }
        this.c = new MAPAccountManager(this.b);
        this.d = new TokenManagement(this.b);
    }

    @FireOsSdk
    public static boolean enableMAPForWebView(WebView webView, MAPAndroidWebViewClient mAPAndroidWebViewClient, Bundle bundle) {
        webView.setWebViewClient(mAPAndroidWebViewClient);
        return enableMAPJSBridgeForWebView(webView, bundle);
    }

    @FireOsSdk
    public static final boolean isInterceptableUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.contains("/ap/signin") || str.contains("/ap/register") || str.contains("/a/c/r/") || str.contains("/a/c/m/");
    }

    public final void a(String[] strArr, String str) {
        bb.a(this.b);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        for (String str2 : strArr) {
            cookieManager.setCookie(str, str2);
        }
        CookieSyncManager.getInstance().sync();
    }

    @FireOsSdk
    public String getAccount() {
        return this.c.getAccount();
    }

    @FireOsSdk
    public String getActor() {
        return null;
    }

    @FireOsSdk
    public Bundle getCookieExchangeOptions() {
        return new Bundle();
    }

    @FireOsSdk
    public Bundle getOptions() {
        return new Bundle();
    }

    @FireOsSdk
    public final boolean handleAuthentication(WebView webView, String str, Activity activity) {
        return handleAuthentication(webView, str, activity, null);
    }

    public void handleSSLError(SslError sslError) {
        String str = "SSL error: " + sslError.toString();
        onEvent(ON_UNABLE_TO_GET_COOKIES, a9.a(MAPError.CommonError.NETWORK_ERROR, str, MAPAccountManager.RegistrationError.NETWORK_FAILURE.value(), str));
    }

    public boolean isFederatedAuthentication() {
        return this.e.isFederatedAuthentication;
    }

    @FireOsSdk
    public void onEvent(String str, Bundle bundle) {
        bundle.toString();
        ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper");
    }

    public boolean shouldInterceptUrlForTIVPushNotification(UrlQuerySanitizer urlQuerySanitizer, String str) {
        return (str.contains("/a/c/r/") || str.contains("/a/c/m/")) && a(urlQuerySanitizer);
    }

    @FireOsSdk
    public boolean skipCookieRefreshPostRegistration() {
        return false;
    }

    @FireOsSdk
    public final boolean handleAuthentication(final WebView webView, final String str, final Activity activity, final MAPAndroidWebViewNavigator mAPAndroidWebViewNavigator) {
        if (!isInterceptableUrl(str)) {
            return false;
        }
        String userAgentString = webView.getSettings().getUserAgentString();
        try {
            UrlQuerySanitizer urlQuerySanitizerB = ya.b(str);
            if (shouldInterceptUrlForTIVPushNotification(urlQuerySanitizerB, str)) {
                if (!i4.a(this.b).a(str, this.f212a)) {
                    Log.e(ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper"), "AmazonAuthenticatorPlugin has not been initialized, continue rendering TIV approval page in app webview");
                    return false;
                }
                ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper");
                webView.stopLoading();
                return true;
            }
            if (!(urlQuerySanitizerB.getParameterList().isEmpty() ? false : a(urlQuerySanitizerB))) {
                return false;
            }
            a(urlQuerySanitizerB, str, userAgentString);
            webView.stopLoading();
            String str2 = this.e.requestType;
            ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper");
            final String account = getAccount();
            final String actor = getActor();
            if (activity == null) {
                onEvent(ON_UNABLE_TO_GET_COOKIES, a9.a(MAPError.CommonError.BAD_REQUEST, "Null activity passed to MAPAndroidWebViewHelper. Could not handle intercepted Auth Portal URL.", MAPAccountManager.RegistrationError.BAD_REQUEST.value(), "null activity passed to MAPAndroidWebViewHelper. Could not handle intercepted Auth Portal URL."));
            } else {
                String str3 = this.e.requestType;
                OpenIdRequest.REQUEST_TYPE request_type = OpenIdRequest.REQUEST_TYPE.REGISTER;
                if (TextUtils.equals(str3, request_type.toString())) {
                    Log.w(ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper"), "Create account page will ignore the actorId if you override getActor() method.");
                    a(activity, new AnonymousClass4(webView, null, str, mAPAndroidWebViewNavigator), request_type);
                } else if (TextUtils.equals(this.e.requestType, OpenIdRequest.REQUEST_TYPE.CONFIRM_CREDENTIAL.toString())) {
                    AnonymousClass4 anonymousClass4 = new AnonymousClass4(webView, actor, str, mAPAndroidWebViewNavigator);
                    Bundle bundle = new Bundle();
                    bundle.putString("com.amazon.identity.ap.assoc_handle", this.e.associationHandle);
                    bundle.putString("com.amazon.identity.ap.pageid", this.e.pageId);
                    bundle.putString(MAPAccountManager.AuthPortalOptions.KEY_CLIENT_CONTEXT, this.e.clientContext);
                    bundle.putString("com.amazon.identity.ap.domain", this.e.domain);
                    bundle.putAll(getOptions());
                    bundle.putString("com.amazon.dcp.sso.property.account.acctId", account);
                    this.c.authenticateAccountWithUI(activity, SigninOption.WebviewConfirmCredentials, bundle, anonymousClass4);
                } else if (TextUtils.isEmpty(account) || TextUtils.equals(this.e.prompt, "login")) {
                    a(activity, new AnonymousClass4(webView, actor, str, mAPAndroidWebViewNavigator), OpenIdRequest.REQUEST_TYPE.SIGN_IN);
                } else {
                    String str4 = this.e.domain;
                    ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper");
                    a(webView, true, account, actor, str4, str, new Callback() { // from class: com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper.1
                        @Override // com.amazon.identity.auth.device.api.Callback
                        public void onSuccess(Bundle bundle2) {
                            Log.i(ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper"), "Sign in skipped successfully. Loaded next URL in WebView.");
                        }

                        @Override // com.amazon.identity.auth.device.api.Callback
                        public void onError(Bundle bundle2) {
                            bundle2.getString("com.amazon.dcp.sso.ErrorMessage");
                            ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper");
                            if (MAPAndroidWebViewHelper.this.c.isAccountRegistered(account)) {
                                MAPAndroidWebViewHelper.this.onEvent(MAPAndroidWebViewHelper.ON_UNABLE_TO_GET_COOKIES, bundle2);
                                return;
                            }
                            ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper");
                            MAPAndroidWebViewHelper mAPAndroidWebViewHelper = MAPAndroidWebViewHelper.this;
                            Activity activity2 = activity;
                            WebView webView2 = webView;
                            String str5 = actor;
                            String str6 = str;
                            MAPAndroidWebViewNavigator mAPAndroidWebViewNavigator2 = mAPAndroidWebViewNavigator;
                            mAPAndroidWebViewHelper.getClass();
                            mAPAndroidWebViewHelper.a(activity2, mAPAndroidWebViewHelper.new AnonymousClass4(webView2, str5, str6, mAPAndroidWebViewNavigator2), OpenIdRequest.REQUEST_TYPE.SIGN_IN);
                        }
                    }, mAPAndroidWebViewNavigator);
                }
            }
            return true;
        } catch (MalformedURLException e) {
            onEvent(ON_UNABLE_TO_GET_COOKIES, a9.a(MAPError.CommonError.BAD_REQUEST, "URL is invalid. MalformedURLException: " + e.getMessage(), MAPAccountManager.RegistrationError.BAD_REQUEST.value(), "URL is invalid." + e.getMessage()));
            return true;
        } catch (InvalidParameterException e2) {
            String str5 = "Error occurred while getting parameters from url." + e2.getMessage();
            onEvent(ON_UNABLE_TO_GET_COOKIES, a9.a(MAPError.CommonError.BAD_REQUEST, str5, MAPAccountManager.RegistrationError.BAD_REQUEST.value(), str5));
            return true;
        }
    }

    @FireOsSdk
    public static boolean enableMAPJSBridgeForWebView(WebView webView, Bundle bundle) {
        if (!webView.getSettings().getJavaScriptEnabled()) {
            Log.w(ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper"), webView.getContext().getPackageName() + " disabled the JavaScript on WebView. MAP will enable the JavaScript");
            yd.b("MAPWebViewJavaScriptOriginallyDisabled:" + webView.getContext().getPackageName(), new String[0]);
            webView.getSettings().setJavaScriptEnabled(true);
        }
        webView.addJavascriptInterface(new v6(webView, null, null), "MAPAndroidJSBridge");
        return true;
    }

    public final void a(UrlQuerySanitizer urlQuerySanitizer, String str, String str2) throws InvalidParameterException, MalformedURLException {
        URL_TYPE url_type;
        this.e = new Parameters();
        String value = urlQuerySanitizer.getValue("openid.return_to");
        if (!TextUtils.isEmpty(value)) {
            Parameters parameters = this.e;
            parameters.returnToURL = value;
            parameters.associationHandle = urlQuerySanitizer.getValue("openid.assoc_handle");
            this.e.pageId = urlQuerySanitizer.getValue("pageId");
            this.e.clientContext = urlQuerySanitizer.getValue("clientContext");
            this.e.claimedId = urlQuerySanitizer.getValue("openid.claimed_id");
            this.e.identity = urlQuerySanitizer.getValue("openid.identity");
            this.e.prompt = urlQuerySanitizer.getValue("prompt");
            String strC = EnvironmentUtils.c.c(new URL(str).getHost());
            ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper");
            Parameters parameters2 = this.e;
            parameters2.domain = strC;
            parameters2.userAgent = str2;
            if (!TextUtils.isEmpty(parameters2.claimedId) && !TextUtils.isEmpty(parameters2.identity) && parameters2.claimedId.equals(parameters2.identity) && (parameters2.claimedId.equals("http://www.amazon.com/ap/specs/auth/confirm_credentials") || parameters2.claimedId.contains("/ap/id/"))) {
                Log.i(ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper"), "URL type set to confirm credential");
                url_type = URL_TYPE.CONFIRM_CREDENTIAL;
            } else if (str != null && str.contains("/ap/register")) {
                Log.i(ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper"), "URL type set to register");
                url_type = URL_TYPE.REGISTER;
            } else {
                url_type = URL_TYPE.SIGNIN;
            }
            parameters2.requestType = url_type.toString();
            Parameters parameters3 = this.e;
            Bundle bundle = new Bundle();
            for (UrlQuerySanitizer.ParameterValuePair parameterValuePair : urlQuerySanitizer.getParameterList()) {
                bundle.putString(parameterValuePair.mParameter, parameterValuePair.mValue);
            }
            parameters3.additionalSignInParams = bundle;
            Bundle bundle2 = this.e.additionalSignInParams;
            if (bundle2 != null) {
                String string = bundle2.getString("ab_federated_auth");
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                this.e.isFederatedAuthentication = Boolean.parseBoolean(string);
                return;
            }
            return;
        }
        Log.e(ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper"), "No Return to url in the main url");
        throw new InvalidParameterException("No Return to url in the main url");
    }

    public final void a(Activity activity, Callback callback, OpenIdRequest.REQUEST_TYPE request_type) {
        Bundle bundle;
        if (activity != null) {
            Bundle bundle2 = new Bundle();
            bundle2.putString("com.amazon.identity.ap.assoc_handle", this.e.associationHandle);
            bundle2.putString("com.amazon.identity.ap.pageid", this.e.pageId);
            bundle2.putString(MAPAccountManager.AuthPortalOptions.KEY_CLIENT_CONTEXT, this.e.clientContext);
            bundle2.putString("com.amazon.identity.ap.domain", this.e.domain);
            bundle2.putBundle(MAPAccountManager.AuthPortalOptions.KEY_ADDITIONAL_SIGN_IN_PARAMETERS, this.e.additionalSignInParams);
            bundle2.putString("requestType", request_type.toString());
            bundle2.putAll(getOptions());
            Bundle bundle3 = this.e.additionalSignInParams;
            if (bundle3 != null) {
                if (bundle2.containsKey(MAPAccountManager.AuthPortalOptions.KEY_REQUEST_PARAMETERS)) {
                    bundle = bundle2.getBundle(MAPAccountManager.AuthPortalOptions.KEY_REQUEST_PARAMETERS);
                } else {
                    Bundle bundle4 = new Bundle();
                    bundle2.putBundle(MAPAccountManager.AuthPortalOptions.KEY_REQUEST_PARAMETERS, bundle4);
                    bundle = bundle4;
                }
                String string = bundle3.getString("override.assoc_handle");
                if (!TextUtils.isEmpty(string)) {
                    bundle.putString("openid.assoc_handle", string);
                }
                String string2 = bundle3.getString("override.page_id");
                if (!TextUtils.isEmpty(string2)) {
                    bundle.putString("pageId", string2);
                }
            }
            Bundle bundle5 = this.e.additionalSignInParams;
            if (bundle5 != null) {
                String string3 = bundle5.getString("domain_hint");
                if (!TextUtils.isEmpty(string3)) {
                    bundle2.putString("domain_hint", string3);
                }
                String string4 = bundle5.getString("ab_federated_auth");
                if (!TextUtils.isEmpty(string4)) {
                    bundle2.putString("ab_federated_auth", string4);
                }
                String string5 = bundle5.getString(MAPAccountManager.KEY_COLOR_CODE_FOR_FEDERATED_CUSTOM_TAB);
                if (!TextUtils.isEmpty(string5)) {
                    bundle2.putString(MAPAccountManager.KEY_COLOR_CODE_FOR_FEDERATED_CUSTOM_TAB, string5);
                }
            }
            this.c.registerAccountWithUI(activity, request_type == OpenIdRequest.REQUEST_TYPE.REGISTER ? SigninOption.WebviewCreateAccount : SigninOption.WebviewSignin, bundle2, callback);
            return;
        }
        throw new InvalidParameterException("Activity object must not be null");
    }

    @FireOsSdk
    public MAPAndroidWebViewHelper(Activity activity) {
        e.a((Object) activity, "Activity");
        this.f212a = activity;
        Context applicationContext = activity.getBaseContext().getApplicationContext();
        this.b = applicationContext;
        this.c = new MAPAccountManager(applicationContext);
        this.d = new TokenManagement(applicationContext);
    }

    @FireOsSdk
    public final boolean handleAuthentication(WebView webView, String str) {
        return handleAuthentication(webView, str, this.f212a);
    }

    public final void a(WebView webView, Bundle bundle, String str, String str2, MAPAndroidWebViewNavigator mAPAndroidWebViewNavigator) {
        onEvent(ON_REGISTRATION_SUCCESS, bundle);
        Log.i(ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper"), "Registration successful. Starting get cookies.");
        if (!skipCookieRefreshPostRegistration()) {
            a(webView, false, bundle.getString("com.amazon.dcp.sso.property.account.acctId"), str, this.e.domain, str2, new Callback() { // from class: com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper.2
                @Override // com.amazon.identity.auth.device.api.Callback
                public void onError(Bundle bundle2) {
                    MAPAndroidWebViewHelper.this.onEvent(MAPAndroidWebViewHelper.ON_UNABLE_TO_GET_COOKIES, bundle2);
                }

                @Override // com.amazon.identity.auth.device.api.Callback
                public void onSuccess(Bundle bundle2) {
                    Log.i(ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper"), "Successfully registered account, set cookies in WebView, and loaded return_to url");
                }
            }, mAPAndroidWebViewNavigator);
            return;
        }
        Log.i(ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper"), "Skipped the cookie refresh, loading the returnToURL");
        Bundle bundle2 = bundle.getBundle(MAPAccountManager.KEY_ADDITIONAL_RETURN_TO_URL_PARAMETERS);
        String string = this.e.returnToURL;
        if (bundle2 != null) {
            ya.b bVar = new ya.b();
            for (String str3 : bundle2.keySet()) {
                String string2 = bundle2.getString(str3);
                if (str3 == null) {
                    Log.w(ga.a(ya.b.b), "Parameter name is <null>, will not append parameter to query string.", new IllegalArgumentException());
                } else if (string2 == null) {
                    Log.w(ga.a(ya.b.b), "Parameter '" + str3 + "' has <null> value, will not append parameter to query string.", new IllegalArgumentException());
                } else {
                    bVar.f683a.appendQueryParameter(str3, string2);
                }
            }
            String query = bVar.f683a.build().getQuery();
            try {
                URI uri = new URI(string);
                String query2 = uri.getQuery();
                if (!TextUtils.isEmpty(query2)) {
                    query = query2 + "&" + query;
                }
                string = new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), query, uri.getFragment()).toString();
            } catch (URISyntaxException unused) {
                Log.e(ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper"), "Error in appending the signIn query parameters to returnTo url.");
            }
        }
        a(webView, string, mAPAndroidWebViewNavigator);
    }

    public final void a(final WebView webView, boolean z, final String str, String str2, String str3, String str4, final Callback callback, final MAPAndroidWebViewNavigator mAPAndroidWebViewNavigator) {
        Bundle cookieExchangeOptions = getCookieExchangeOptions();
        if (cookieExchangeOptions == null) {
            cookieExchangeOptions = new Bundle();
        }
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(cookieExchangeOptions.getString(CookieKeys.Options.KEY_ASSOC_HANDLE))) {
            bundle.putString(CookieKeys.Options.KEY_ASSOC_HANDLE, cookieExchangeOptions.getString(CookieKeys.Options.KEY_ASSOC_HANDLE));
        } else if (cookieExchangeOptions.getBoolean(USE_ASSOC_HANDLE_IN_URL_FOR_COOKIE_EXCHANGE, false)) {
            bundle.putString(CookieKeys.Options.KEY_ASSOC_HANDLE, this.e.associationHandle);
        }
        bundle.putBoolean(CookieKeys.Options.KEY_FORCE_REFRESH, z);
        bundle.putString("domain", str3);
        bundle.putString("user_agent", this.e.userAgent);
        bundle.putString(CookieKeys.Options.KEY_SIGN_IN_URL, str4);
        Callback callback2 = new Callback() { // from class: com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper.5
            @Override // com.amazon.identity.auth.device.api.Callback
            public void onError(Bundle bundle2) {
                callback.onError(bundle2);
            }

            @Override // com.amazon.identity.auth.device.api.Callback
            public void onSuccess(Bundle bundle2) {
                String[] stringArray = bundle2.getStringArray(CookieKeys.KEY_COOKIES);
                MAPAndroidWebViewHelper mAPAndroidWebViewHelper = MAPAndroidWebViewHelper.this;
                String string = bundle2.getString(CookieKeys.KEY_RESPONSE_URL);
                mAPAndroidWebViewHelper.getClass();
                if (TextUtils.isEmpty(string)) {
                    string = mAPAndroidWebViewHelper.e.returnToURL;
                }
                if (stringArray.length != 0) {
                    if (!URLUtil.isHttpsUrl(string)) {
                        yd.a("MAPWebviewWarning:ReturnToURLNotHTTPS", new String[0]);
                        Log.w(ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper"), "The return_to url is not HTTPS protocol, which is not encouraged and will not be supported by Android in the future. Please make sure your return_to url is using HTTPS protocol.");
                    }
                    ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper");
                    MAPAndroidWebViewHelper.this.a(stringArray, string);
                }
                MAPAndroidWebViewHelper mAPAndroidWebViewHelper2 = MAPAndroidWebViewHelper.this;
                WebView webView2 = webView;
                MAPAndroidWebViewNavigator mAPAndroidWebViewNavigator2 = mAPAndroidWebViewNavigator;
                mAPAndroidWebViewHelper2.getClass();
                if (TextUtils.isEmpty(string)) {
                    string = mAPAndroidWebViewHelper2.e.returnToURL;
                }
                MAPAndroidWebViewHelper.a(webView2, string, mAPAndroidWebViewNavigator2);
                callback.onSuccess(bundle2);
            }
        };
        if (TextUtils.isEmpty(str2)) {
            ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper");
            this.d.getCookies(str, str3, bundle, callback2);
        } else {
            ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper");
            this.d.getCookiesForActor(str, str2, str3, bundle, callback2);
        }
    }

    public static void a(final WebView webView, final String str, final MAPAndroidWebViewNavigator mAPAndroidWebViewNavigator) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ga.a("com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper");
        va.b(new Runnable() { // from class: com.amazon.identity.auth.device.api.MAPAndroidWebViewHelper.3
            @Override // java.lang.Runnable
            public void run() {
                MAPAndroidWebViewNavigator mAPAndroidWebViewNavigator2 = mAPAndroidWebViewNavigator;
                if (mAPAndroidWebViewNavigator2 == null) {
                    webView.loadUrl(str);
                } else {
                    mAPAndroidWebViewNavigator2.navigate(webView, str);
                }
            }
        });
    }

    public final boolean a(UrlQuerySanitizer urlQuerySanitizer) {
        return !urlQuerySanitizer.hasParameter("intercept") || Boolean.valueOf(urlQuerySanitizer.getValue("intercept")).booleanValue();
    }
}
