package com.amazon.music.connect.contacts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.fragment.app.Fragment;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.connect.R$id;
import com.amazon.music.connect.R$layout;
import com.amazon.music.connect.contacts.ContactImportProvider;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.platform.Outcome;
import com.amazon.music.platform.data.AuthCookies;
import com.amazon.music.platform.data.OAuthInfo;
import com.amazon.music.platform.metrics.event.FlexWrapper;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.share.ProfileShareProvider;
import com.dee.app.contacts.interfaces.models.apis.getaccounts.GetAccountsRequest;
import com.dee.app.contacts.interfaces.models.apis.getaccounts.GetAccountsResponse;
import com.dee.app.contacts.interfaces.models.apis.getcontacts.getcontactsv2.GetContactsV2QueryConfig;
import com.dee.app.contacts.interfaces.models.apis.getcontacts.getcontactsv2.GetContactsV2Request;
import com.dee.app.contacts.interfaces.models.apis.getcontacts.getcontactsv2.GetContactsV2Response;
import com.dee.app.contacts.interfaces.models.apis.getpnvurl.GetPhoneNumberVerificationURLBody;
import com.dee.app.contacts.interfaces.models.apis.getpnvurl.GetPhoneNumberVerificationURLRequest;
import com.dee.app.contacts.interfaces.models.apis.getpnvurl.GetPhoneNumberVerificationURLResponse;
import com.dee.app.contacts.interfaces.models.apis.getpreferenceforowner.GetPreferenceForOwnerQueryConfig;
import com.dee.app.contacts.interfaces.models.apis.getpreferenceforowner.GetPreferenceForOwnerRequest;
import com.dee.app.contacts.interfaces.models.apis.getpreferenceforowner.GetPreferenceForOwnerResponse;
import com.dee.app.contacts.interfaces.models.apis.setpreferenceforowner.SetPreferenceForOwnerQueryConfig;
import com.dee.app.contacts.interfaces.models.apis.setpreferenceforowner.SetPreferenceForOwnerRequest;
import com.dee.app.contacts.interfaces.models.apis.setpreferenceforowner.SetPreferenceForOwnerResponse;
import com.dee.app.contacts.interfaces.models.apis.synccontacts.ContactsUpSyncRequest;
import com.dee.app.contacts.interfaces.models.apis.synccontacts.ContactsUpSyncResponse;
import com.dee.app.contacts.interfaces.models.data.enums.OwnerIdType;
import com.dee.app.contacts.interfaces.models.data.enums.PreferenceNameSpace;
import com.dee.app.contacts.interfaces.models.data.preference.contactownerpreference.ContactOwnerPreference;
import com.dee.app.contacts.interfaces.models.data.preference.contactownerpreference.ContactOwnerPreferenceKey;
import com.fasterxml.jackson.core.JsonPointer;
import com.google.gson.Gson;
import dagger.android.support.AndroidSupportInjection;
import java.net.HttpCookie;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.json.JSONObject;
import rx.Observable;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

/* JADX INFO: compiled from: ContactImportWebViewFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001BG\u0012\b\u00100\u001a\u0004\u0018\u00010\r\u0012\b\u0010B\u001a\u0004\u0018\u00010\r\u0012\b\u00102\u001a\u0004\u0018\u00010\r\u0012\b\u00103\u001a\u0004\u0018\u00010\r\u0012\b\u00104\u001a\u0004\u0018\u00010\r\u0012\f\u00107\u001a\b\u0012\u0004\u0012\u00020605¢\u0006\u0004\bm\u0010nJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\b\u0010\t\u001a\u00020\u0004H\u0002J,\u0010\u0010\u001a\u00020\u0004\"\u0004\b\u0000\u0010\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0002J\u001a\u0010\u0013\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0012\u001a\u00020\rH\u0002J\u0010\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J&\u0010\u001d\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\b\u0010\u001c\u001a\u0004\u0018\u00010\u001bH\u0016J\b\u0010\u001e\u001a\u00020\u0004H\u0016J\b\u0010\u001f\u001a\u00020\u0004H\u0016J\u0018\u0010 \u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0007J\u0018\u0010!\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0007J\u0018\u0010\"\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0007J0\u0010%\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\rH\u0007J(\u0010&\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\rH\u0007J(\u0010)\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020\rH\u0007J\u0018\u0010*\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0007J\u0018\u0010+\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0007J(\u0010.\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010,\u001a\u00020\r2\u0006\u0010-\u001a\u00020\rH\u0007J\u0018\u0010/\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rH\u0007R\u0016\u00100\u001a\u0004\u0018\u00010\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u0016\u00102\u001a\u0004\u0018\u00010\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00101R\u0016\u00103\u001a\u0004\u0018\u00010\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00101R\u0016\u00104\u001a\u0004\u0018\u00010\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00101R\u001a\u00107\u001a\b\u0012\u0004\u0012\u000206058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u0014\u00109\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b9\u00101R\u0014\u0010:\u001a\u00020\r8\u0002X\u0082D¢\u0006\u0006\n\u0004\b:\u00101R\u0014\u0010;\u001a\u00020\r8\u0002X\u0082D¢\u0006\u0006\n\u0004\b;\u00101R\u0014\u0010=\u001a\u00020<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010>R\u0018\u0010@\u001a\u0004\u0018\u00010?8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\u0014\u0010B\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u00101R\u0016\u0010C\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bC\u00101R\u0016\u0010E\u001a\u00020D8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bE\u0010FR\u0016\u0010H\u001a\u00020G8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bH\u0010IR\"\u0010K\u001a\u00020J8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bK\u0010L\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\"\u0010R\u001a\u00020Q8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bR\u0010S\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\"\u0010Y\u001a\u00020X8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bY\u0010Z\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^R\"\u0010`\u001a\u00020_8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b`\u0010a\u001a\u0004\bb\u0010c\"\u0004\bd\u0010eR\"\u0010g\u001a\u00020f8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bg\u0010h\u001a\u0004\bi\u0010j\"\u0004\bk\u0010l¨\u0006o"}, d2 = {"Lcom/amazon/music/connect/contacts/ContactImportWebViewFragment;", "Landroidx/fragment/app/Fragment;", "Landroid/view/View;", "view", "", "initWebView", "", "newProgress", "onWebChromeClientProgressChanged", "setCookies", "T", "Lrx/Observable;", "response", "", "successCallback", "errorCallback", "handleResponse", "value", "callback", "onComplete", "Landroid/content/Context;", "context", "onAttach", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/os/Bundle;", "savedInstanceState", "onCreateView", "onResume", "onDestroy", "getCustomerInfo", "getAccount", "getContacts", "key", "target", "setPreferenceForOwner", "getPreferenceForOwner", "profileId", "profileName", "shareProfile", "openOSAppSettings", "startContactsUpSync", "actorId", "redirectURL", "getPhoneNumberVerificationURL", "requestOSContactsPermissions", "customerId", "Ljava/lang/String;", Splash.PARAMS_MUSIC_TERRITORY, "path", "refMarker", "Lrx/subjects/PublishSubject;", "Lcom/amazon/music/connect/contacts/ContactImportEvent;", "webEventPublisher", "Lrx/subjects/PublishSubject;", "TAG", "CONTACT_IMPORT_WEBVIEW_TIMEOUT", "WEBVIEW_DOMAIN", "Lcom/google/gson/Gson;", "gson", "Lcom/google/gson/Gson;", "Landroid/os/CountDownTimer;", "timeoutTimer", "Landroid/os/CountDownTimer;", "musicDomain", "phoneVerificationRedirectUrl", "Landroid/webkit/WebView;", "mWebView", "Landroid/webkit/WebView;", "Lcom/amazon/music/connect/contacts/ContactsSDKWrapper;", "contactsSDKWrapper", "Lcom/amazon/music/connect/contacts/ContactsSDKWrapper;", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "authenticationProvider", "Lcom/amazon/music/platform/providers/AuthenticationProvider;", "getAuthenticationProvider", "()Lcom/amazon/music/platform/providers/AuthenticationProvider;", "setAuthenticationProvider", "(Lcom/amazon/music/platform/providers/AuthenticationProvider;)V", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "deviceInfoProvider", "Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "getDeviceInfoProvider", "()Lcom/amazon/music/platform/providers/DeviceInfoProvider;", "setDeviceInfoProvider", "(Lcom/amazon/music/platform/providers/DeviceInfoProvider;)V", "Lcom/amazon/music/platform/providers/BuildInfoProvider;", "buildInfoProvider", "Lcom/amazon/music/platform/providers/BuildInfoProvider;", "getBuildInfoProvider", "()Lcom/amazon/music/platform/providers/BuildInfoProvider;", "setBuildInfoProvider", "(Lcom/amazon/music/platform/providers/BuildInfoProvider;)V", "Lcom/amazon/music/platform/providers/MetricsProvider;", "metricsProvider", "Lcom/amazon/music/platform/providers/MetricsProvider;", "getMetricsProvider", "()Lcom/amazon/music/platform/providers/MetricsProvider;", "setMetricsProvider", "(Lcom/amazon/music/platform/providers/MetricsProvider;)V", "Lcom/amazon/music/connect/contacts/ContactImportProvider;", "contactImportAppInterface", "Lcom/amazon/music/connect/contacts/ContactImportProvider;", "getContactImportAppInterface", "()Lcom/amazon/music/connect/contacts/ContactImportProvider;", "setContactImportAppInterface", "(Lcom/amazon/music/connect/contacts/ContactImportProvider;)V", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lrx/subjects/PublishSubject;)V", "DMMConnect_release"}, k = 1, mv = {1, 6, 0})
public final class ContactImportWebViewFragment extends Fragment {
    private final String CONTACT_IMPORT_WEBVIEW_TIMEOUT;
    private final String TAG;
    private final String WEBVIEW_DOMAIN;
    public AuthenticationProvider authenticationProvider;
    public BuildInfoProvider buildInfoProvider;
    public ContactImportProvider contactImportAppInterface;
    private ContactsSDKWrapper contactsSDKWrapper;
    private final String customerId;
    public DeviceInfoProvider deviceInfoProvider;
    private final Gson gson;
    private WebView mWebView;
    public MetricsProvider metricsProvider;
    private final String musicDomain;
    private final String musicTerritory;
    private final String path;
    private String phoneVerificationRedirectUrl;
    private final String refMarker;
    private CountDownTimer timeoutTimer;
    private final PublishSubject<ContactImportEvent> webEventPublisher;

    public ContactImportWebViewFragment(String str, String str2, String str3, String str4, String str5, PublishSubject<ContactImportEvent> webEventPublisher) {
        Intrinsics.checkNotNullParameter(webEventPublisher, "webEventPublisher");
        this.customerId = str;
        this.musicTerritory = str3;
        this.path = str4;
        this.refMarker = str5;
        this.webEventPublisher = webEventPublisher;
        String simpleName = ContactImportWebViewFragment.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "ContactImportWebViewFrag…lass.java.getSimpleName()");
        this.TAG = simpleName;
        this.CONTACT_IMPORT_WEBVIEW_TIMEOUT = "ContactImportWebviewTimeout";
        this.WEBVIEW_DOMAIN = "https://prod.contact-import.music.amazon.dev/";
        this.gson = new Gson();
        this.musicDomain = str2 == null ? "music.amazon.com" : str2;
        this.phoneVerificationRedirectUrl = "";
    }

    public final AuthenticationProvider getAuthenticationProvider() {
        AuthenticationProvider authenticationProvider = this.authenticationProvider;
        if (authenticationProvider != null) {
            return authenticationProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("authenticationProvider");
        return null;
    }

    public final DeviceInfoProvider getDeviceInfoProvider() {
        DeviceInfoProvider deviceInfoProvider = this.deviceInfoProvider;
        if (deviceInfoProvider != null) {
            return deviceInfoProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("deviceInfoProvider");
        return null;
    }

    public final BuildInfoProvider getBuildInfoProvider() {
        BuildInfoProvider buildInfoProvider = this.buildInfoProvider;
        if (buildInfoProvider != null) {
            return buildInfoProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("buildInfoProvider");
        return null;
    }

    public final MetricsProvider getMetricsProvider() {
        MetricsProvider metricsProvider = this.metricsProvider;
        if (metricsProvider != null) {
            return metricsProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("metricsProvider");
        return null;
    }

    public final ContactImportProvider getContactImportAppInterface() {
        ContactImportProvider contactImportProvider = this.contactImportAppInterface;
        if (contactImportProvider != null) {
            return contactImportProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("contactImportAppInterface");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R$layout.contact_import_webview_fragment, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…agment, container, false)");
        Context context = getContext();
        if (context != null) {
            this.contactsSDKWrapper = new ContactsSDKWrapper(context, getAuthenticationProvider(), getMetricsProvider());
            initWebView(viewInflate);
        } else {
            Log.w(this.TAG, "Missing Context on Initializing Contact Import Webview");
            this.webEventPublisher.onNext(new ContactImportEvent(ContactImportWebViewActivity.INSTANCE.getCONTACT_IMPORT_ON_ERROR()));
        }
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.timeoutTimer == null) {
            TimeUnit timeUnit = TimeUnit.SECONDS;
            CountDownTimer countDownTimer = new CountDownTimer(timeUnit.toMillis(10L), timeUnit.toMillis(10L)) { // from class: com.amazon.music.connect.contacts.ContactImportWebViewFragment.onResume.1
                @Override // android.os.CountDownTimer
                public void onTick(long l) {
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    ContactImportWebViewFragment.this.getMetricsProvider().sendMetricsEvent(new FlexWrapper.Builder().withName(ContactImportWebViewFragment.this.CONTACT_IMPORT_WEBVIEW_TIMEOUT).build());
                    Log.w(ContactImportWebViewFragment.this.TAG, "Timed out while trying to load Contact Import WebView");
                    ContactImportWebViewFragment.this.webEventPublisher.onNext(new ContactImportEvent(ContactImportWebViewActivity.INSTANCE.getCONTACT_IMPORT_LOAD_TIMED_OUT()));
                }
            };
            this.timeoutTimer = countDownTimer;
            countDownTimer.start();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        CountDownTimer countDownTimer = this.timeoutTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        super.onDestroy();
    }

    private final void initWebView(View view) {
        View viewFindViewById = view.findViewById(R$id.WebView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.WebView)");
        WebView webView = (WebView) viewFindViewById;
        this.mWebView = webView;
        WebView webView2 = null;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            webView = null;
        }
        webView.getSettings().setJavaScriptEnabled(true);
        WebView webView3 = this.mWebView;
        if (webView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            webView3 = null;
        }
        webView3.setOverScrollMode(2);
        WebView webView4 = this.mWebView;
        if (webView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            webView4 = null;
        }
        webView4.setWebChromeClient(new WebChromeClient() { // from class: com.amazon.music.connect.contacts.ContactImportWebViewFragment.initWebView.1
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView view2, int newProgress) {
                Intrinsics.checkNotNullParameter(view2, "view");
                ContactImportWebViewFragment.this.onWebChromeClientProgressChanged(newProgress);
            }
        });
        WebView webView5 = this.mWebView;
        if (webView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            webView5 = null;
        }
        webView5.setWebViewClient(new WebViewClient() { // from class: com.amazon.music.connect.contacts.ContactImportWebViewFragment.initWebView.2
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view2, String url) {
                Intrinsics.checkNotNullParameter(view2, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                if (Intrinsics.areEqual(ContactImportWebViewFragment.this.phoneVerificationRedirectUrl, url)) {
                    view2.loadUrl(StringsKt__StringsJVMKt.replace$default(url, "https://" + ContactImportWebViewFragment.this.musicDomain + JsonPointer.SEPARATOR, ContactImportWebViewFragment.this.WEBVIEW_DOMAIN, false, 4, (Object) null));
                    return true;
                }
                if (!StringsKt__StringsJVMKt.startsWith$default(url, "https://music.amazon.", false, 2, null)) {
                    return false;
                }
                ContactImportWebViewFragment.this.webEventPublisher.onNext(new ContactImportEvent(ContactImportWebViewActivity.INSTANCE.getCUSTOMER_COMPLETED_CONTACT_IMPORT(), url));
                return true;
            }
        });
        WebView.setWebContentsDebuggingEnabled(getBuildInfoProvider().getIsDebug());
        WebView webView6 = this.mWebView;
        if (webView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            webView6 = null;
        }
        webView6.addJavascriptInterface(this, "Android");
        WebView webView7 = this.mWebView;
        if (webView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
        } else {
            webView2 = webView7;
        }
        webView2.loadUrl(Intrinsics.stringPlus(this.WEBVIEW_DOMAIN, this.path));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onWebChromeClientProgressChanged(int newProgress) {
        if (newProgress > 70) {
            CountDownTimer countDownTimer = this.timeoutTimer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            WebView webView = this.mWebView;
            if (webView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mWebView");
                webView = null;
            }
            webView.setVisibility(0);
        }
    }

    @JavascriptInterface
    public final void getCustomerInfo(String successCallback, String errorCallback) {
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(errorCallback, "errorCallback");
        Outcome<OAuthInfo> oAuthInfoBlocking = getAuthenticationProvider().getOAuthInfoBlocking();
        if (oAuthInfoBlocking instanceof Outcome.Success) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("oAuthToken", ((OAuthInfo) ((Outcome.Success) oAuthInfoBlocking).getValue()).getAccessToken());
            jSONObject.put("customerId", this.customerId);
            jSONObject.put(Splash.PARAMS_MUSIC_TERRITORY, this.musicTerritory);
            jSONObject.put(Splash.PARAMS_DEVICE_ID, getDeviceInfoProvider().getDeviceIdentity().getDeviceId());
            jSONObject.put(Splash.PARAMS_DEVICE_TYPE, getDeviceInfoProvider().getDeviceIdentity().getDeviceTypeId());
            jSONObject.put(Splash.PARAMS_LOCALE, CollectionsKt___CollectionsKt.first((List) getDeviceInfoProvider().getLocales()));
            onComplete(jSONObject.toString(), successCallback);
            return;
        }
        onComplete("Failed to fetch access token", errorCallback);
    }

    @JavascriptInterface
    public final void getAccount(String successCallback, String errorCallback) {
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(errorCallback, "errorCallback");
        ContactsSDKWrapper contactsSDKWrapper = this.contactsSDKWrapper;
        if (contactsSDKWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contactsSDKWrapper");
            contactsSDKWrapper = null;
        }
        Observable<GetAccountsResponse> accounts = contactsSDKWrapper.getContactsApiComponent().getCustomerIdentityManager().getAccounts(new GetAccountsRequest());
        Intrinsics.checkNotNullExpressionValue(accounts, "contactsSDKWrapper.conta…nts(GetAccountsRequest())");
        handleResponse(accounts, successCallback, errorCallback);
    }

    @JavascriptInterface
    public final void getContacts(String successCallback, String errorCallback) {
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(errorCallback, "errorCallback");
        GetContactsV2Request getContactsV2Request = new GetContactsV2Request();
        getContactsV2Request.setUserId(AccountManagerSingleton.get().getUser().getCustomerId());
        getContactsV2Request.setQueryConfig(new GetContactsV2QueryConfig());
        getContactsV2Request.getQueryConfig().setOwnerIdType(OwnerIdType.CUSTOMER_ID);
        ContactsSDKWrapper contactsSDKWrapper = this.contactsSDKWrapper;
        if (contactsSDKWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contactsSDKWrapper");
            contactsSDKWrapper = null;
        }
        Observable<GetContactsV2Response> response = contactsSDKWrapper.getContactsSyncComponent().getContactsSyncManager().startContactsDownSync(getContactsV2Request);
        Intrinsics.checkNotNullExpressionValue(response, "response");
        handleResponse(response, successCallback, errorCallback);
    }

    @JavascriptInterface
    public final void setPreferenceForOwner(String successCallback, String errorCallback, String key, String target, String value) {
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(errorCallback, "errorCallback");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(value, "value");
        SetPreferenceForOwnerRequest setPreferenceForOwnerRequest = new SetPreferenceForOwnerRequest(ContactOwnerPreference.getContactPreferenceForKeyAndValue(key, value), target, PreferenceNameSpace.AMAZON);
        setPreferenceForOwnerRequest.setUserId(AccountManagerSingleton.get().getUser().getCustomerId());
        setPreferenceForOwnerRequest.setQueryConfig(new SetPreferenceForOwnerQueryConfig());
        setPreferenceForOwnerRequest.getQueryConfig().setOwnerIdType(OwnerIdType.CUSTOMER_ID);
        ContactsSDKWrapper contactsSDKWrapper = this.contactsSDKWrapper;
        if (contactsSDKWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contactsSDKWrapper");
            contactsSDKWrapper = null;
        }
        Observable<SetPreferenceForOwnerResponse> response = contactsSDKWrapper.getContactsApiComponent().getPreferenceManager().setPreferenceForOwner(setPreferenceForOwnerRequest);
        Intrinsics.checkNotNullExpressionValue(response, "response");
        handleResponse(response, successCallback, errorCallback);
    }

    @JavascriptInterface
    public final void getPreferenceForOwner(String successCallback, String errorCallback, String key, String target) {
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(errorCallback, "errorCallback");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(target, "target");
        GetPreferenceForOwnerRequest getPreferenceForOwnerRequest = new GetPreferenceForOwnerRequest(ContactOwnerPreferenceKey.valueOf(key));
        getPreferenceForOwnerRequest.setUserId(AccountManagerSingleton.get().getUser().getCustomerId());
        getPreferenceForOwnerRequest.setQueryConfig(new GetPreferenceForOwnerQueryConfig());
        getPreferenceForOwnerRequest.getQueryConfig().setOwnerIdType(OwnerIdType.CUSTOMER_ID);
        getPreferenceForOwnerRequest.getQueryConfig().setTarget(target);
        ContactsSDKWrapper contactsSDKWrapper = this.contactsSDKWrapper;
        if (contactsSDKWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contactsSDKWrapper");
            contactsSDKWrapper = null;
        }
        Observable<GetPreferenceForOwnerResponse> response = contactsSDKWrapper.getContactsApiComponent().getPreferenceManager().getPreferenceForOwner(getPreferenceForOwnerRequest);
        Intrinsics.checkNotNullExpressionValue(response, "response");
        handleResponse(response, successCallback, errorCallback);
    }

    @JavascriptInterface
    public final void shareProfile(String successCallback, String errorCallback, String profileId, String profileName) {
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(errorCallback, "errorCallback");
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        Intrinsics.checkNotNullParameter(profileName, "profileName");
        Context context = getContext();
        if (context != null) {
            new ProfileShareProvider(context, this.musicDomain, profileId, profileName, null, null).startShare();
            onComplete("Success", successCallback);
        } else {
            Log.w(this.TAG, "Missing Context on Initializing Contact Import Webview");
            onComplete("Missing Context on Initializing Contact Import Webview", errorCallback);
        }
    }

    @JavascriptInterface
    public final void openOSAppSettings(String successCallback, String errorCallback) {
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(errorCallback, "errorCallback");
        Context context = getContext();
        if (context != null) {
            Intent intent = new Intent();
            Uri uri = Uri.parse(Intrinsics.stringPlus("package:", context.getPackageName()));
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.setData(uri);
            intent.addFlags(268435456);
            intent.addFlags(1073741824);
            intent.addFlags(8388608);
            context.startActivity(intent);
            onComplete("Success", successCallback);
            return;
        }
        Log.w(this.TAG, "Missing Context on Initializing Contact Import Webview");
        onComplete("Missing Context on Initializing Contact Import Webview", errorCallback);
    }

    @JavascriptInterface
    public final void startContactsUpSync(String successCallback, String errorCallback) {
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(errorCallback, "errorCallback");
        ContactsUpSyncRequest contactsUpSyncRequest = new ContactsUpSyncRequest("");
        contactsUpSyncRequest.setOwnerId(AccountManagerSingleton.get().getUser().getCustomerId());
        contactsUpSyncRequest.setOwnerIdType(OwnerIdType.CUSTOMER_ID);
        ContactsSDKWrapper contactsSDKWrapper = this.contactsSDKWrapper;
        if (contactsSDKWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contactsSDKWrapper");
            contactsSDKWrapper = null;
        }
        Observable<ContactsUpSyncResponse> response = contactsSDKWrapper.getContactsSyncComponent().getContactsSyncManager().startContactsUpSync(contactsUpSyncRequest);
        Intrinsics.checkNotNullExpressionValue(response, "response");
        handleResponse(response, successCallback, errorCallback);
    }

    @JavascriptInterface
    public final void getPhoneNumberVerificationURL(String successCallback, String errorCallback, String actorId, String redirectURL) {
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(errorCallback, "errorCallback");
        Intrinsics.checkNotNullParameter(actorId, "actorId");
        Intrinsics.checkNotNullParameter(redirectURL, "redirectURL");
        setCookies();
        Outcome<OAuthInfo> oAuthInfoForActor = getContactImportAppInterface().getOAuthInfoForActor(actorId);
        if (oAuthInfoForActor instanceof Outcome.Success) {
            this.phoneVerificationRedirectUrl = StringsKt__StringsJVMKt.replace$default(redirectURL, this.WEBVIEW_DOMAIN, "https://" + this.musicDomain + JsonPointer.SEPARATOR, false, 4, (Object) null);
            ContactsSDKWrapper contactsSDKWrapper = this.contactsSDKWrapper;
            if (contactsSDKWrapper == null) {
                Intrinsics.throwUninitializedPropertyAccessException("contactsSDKWrapper");
                contactsSDKWrapper = null;
            }
            Observable<GetPhoneNumberVerificationURLResponse> response = contactsSDKWrapper.getContactsApiComponent().getCustomerIdentityManager().getPhoneNumberVerificationURL(new GetPhoneNumberVerificationURLRequest(new GetPhoneNumberVerificationURLBody(((OAuthInfo) ((Outcome.Success) oAuthInfoForActor).getValue()).getAccessToken(), actorId, this.phoneVerificationRedirectUrl)));
            Intrinsics.checkNotNullExpressionValue(response, "response");
            handleResponse(response, successCallback, errorCallback);
            return;
        }
        Log.e(this.TAG, "Failed to fetch Actor OAuth Info from MAP");
        onComplete("Failed to fetch Actor OAuth Info from MAP", errorCallback);
    }

    @JavascriptInterface
    public final void requestOSContactsPermissions(final String successCallback, final String errorCallback) {
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(errorCallback, "errorCallback");
        getContactImportAppInterface().requestContactPermissionsIfNotGranted(new ContactImportProvider.ContactsPermissionsCallback() { // from class: com.amazon.music.connect.contacts.ContactImportWebViewFragment$requestOSContactsPermissions$contactsPermissionsCallback$1
            @Override // com.amazon.music.connect.contacts.ContactImportProvider.ContactsPermissionsCallback
            public void onGranted() {
                this.this$0.onComplete("Success", successCallback);
            }

            @Override // com.amazon.music.connect.contacts.ContactImportProvider.ContactsPermissionsCallback
            public void onDenied(String[] permissions) {
                Intrinsics.checkNotNullParameter(permissions, "permissions");
                ContactImportWebViewFragment contactImportWebViewFragment = this.this$0;
                contactImportWebViewFragment.onComplete(contactImportWebViewFragment.gson.toJson(permissions), errorCallback);
            }
        });
    }

    private final void setCookies() {
        Outcome authCookiesBlocking$default = AuthenticationProvider.DefaultImpls.getAuthCookiesBlocking$default(getAuthenticationProvider(), null, 1, null);
        if (authCookiesBlocking$default instanceof Outcome.Success) {
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            for (HttpCookie httpCookie : ((AuthCookies) ((Outcome.Success) authCookiesBlocking$default).getValue()).getCookies()) {
                String strReplace$default = StringsKt__StringsJVMKt.replace$default(this.musicDomain, "music", "", false, 4, (Object) null);
                cookieManager.setCookie("https://www" + strReplace$default + JsonPointer.SEPARATOR, httpCookie + "; Secure; Domain=" + strReplace$default + "; Path=/");
            }
            cookieManager.flush();
            return;
        }
        Log.e(this.TAG, "Failed to fetch auth cookies from MAP");
    }

    private final <T> void handleResponse(Observable<T> response, final String successCallback, final String errorCallback) {
        response.subscribe(new Action1() { // from class: com.amazon.music.connect.contacts.ContactImportWebViewFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ContactImportWebViewFragment.m1895handleResponse$lambda1(this.f$0, successCallback, obj);
            }
        }, new Action1() { // from class: com.amazon.music.connect.contacts.ContactImportWebViewFragment$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ContactImportWebViewFragment.m1896handleResponse$lambda2(this.f$0, errorCallback, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleResponse$lambda-1, reason: not valid java name */
    public static final void m1895handleResponse$lambda1(ContactImportWebViewFragment this$0, String successCallback, Object obj) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(successCallback, "$successCallback");
        this$0.onComplete(this$0.gson.toJson(obj), successCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleResponse$lambda-2, reason: not valid java name */
    public static final void m1896handleResponse$lambda2(ContactImportWebViewFragment this$0, String errorCallback, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(errorCallback, "$errorCallback");
        this$0.onComplete(th.getMessage(), errorCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onComplete(final String value, final String callback) {
        WebView webView = this.mWebView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            webView = null;
        }
        webView.post(new Runnable() { // from class: com.amazon.music.connect.contacts.ContactImportWebViewFragment$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ContactImportWebViewFragment.m1897onComplete$lambda3(this.f$0, callback, value);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onComplete$lambda-3, reason: not valid java name */
    public static final void m1897onComplete$lambda3(ContactImportWebViewFragment this$0, String callback, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        WebView webView = this$0.mWebView;
        if (webView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mWebView");
            webView = null;
        }
        webView.evaluateJavascript(callback + "('" + ((Object) str) + "')", null);
    }
}
