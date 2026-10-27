package com.amazon.mp3.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.activity.URLDisplayActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.cookieconsent.CookieConsentManager;
import com.amazon.mp3.environment.url.ConfigurableURLs;
import com.amazon.mp3.fragment.CookieConsentFragment;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.web.WebEvent;
import com.amazon.mp3.web.webtarget.BasicWebTarget;
import com.amazon.mp3.web.webtarget.WebTarget;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import java.io.Serializable;
import java.net.URL;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CookieConsentActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001d\u001eB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0004H\u0002J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\b\u0010\u0010\u001a\u00020\nH\u0014J\b\u0010\u0011\u001a\u00020\nH\u0016J\b\u0010\u0012\u001a\u00020\nH\u0002J\u0012\u0010\u0013\u001a\u00020\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018H\u0014J\u0010\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/activity/CookieConsentActivity;", "Lcom/amazon/mp3/activity/BaseWebActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "TAG_WEB_FRAGMENT", "mFragment", "Lcom/amazon/mp3/fragment/CookieConsentFragment;", "handleManageCookiePreference", "", "handleUpdateCookiePreference", "consent", "handleWebEvent", "webEvent", "Lcom/amazon/mp3/web/WebEvent;", "init", "onBackPressed", "onConsentFlowCompletion", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onNewIntent", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "onReceivedIntent", "setupNewFragment", "webTarget", "Lcom/amazon/mp3/web/webtarget/WebTarget;", "Companion", "CookieConsentWebEventEnum", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CookieConsentActivity extends BaseWebActivity {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private static final String URL;
    private CookieConsentFragment mFragment;
    private final String TAG_WEB_FRAGMENT = "CookieConsentActivity.TAG_WEB_FRAGMENT";
    private final String TAG = CookieConsentActivity.class.getSimpleName();

    /* JADX INFO: compiled from: CookieConsentActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/activity/CookieConsentActivity$CookieConsentWebEventEnum;", "", "(Ljava/lang/String;I)V", "CONSENT_ACCEPTED", "CONSENT_DENIED", "CONSENT_CUSTOMIZED", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum CookieConsentWebEventEnum {
        CONSENT_ACCEPTED,
        CONSENT_DENIED,
        CONSENT_CUSTOMIZED
    }

    public static final int getREQUEST_CODE() {
        return INSTANCE.getREQUEST_CODE();
    }

    @JvmStatic
    public static final Intent getStartIntent(Context context) {
        return INSTANCE.getStartIntent(context);
    }

    /* JADX INFO: compiled from: CookieConsentActivity.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/activity/CookieConsentActivity$Companion;", "", "()V", "REQUEST_CODE", "", "getREQUEST_CODE$annotations", "getREQUEST_CODE", "()I", "URL", "", "getStartIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Intent getStartIntent(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent targetStartEvent = BaseWebActivity.getTargetStartEvent(context, CookieConsentActivity.class, new BasicWebTarget(CookieConsentActivity.URL), "com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET");
            Intrinsics.checkNotNullExpressionValue(targetStartEvent, "getTargetStartEvent(\n   ….BUNDLE_KEY\n            )");
            return targetStartEvent;
        }

        public final int getREQUEST_CODE() {
            return CookieConsentActivity.REQUEST_CODE;
        }
    }

    static {
        String cookieConsentLegaleseUrl = CookieConsentManager.instance.getCookieConsentLegaleseUrl();
        if (cookieConsentLegaleseUrl == null) {
            cookieConsentLegaleseUrl = "";
        }
        URL = cookieConsentLegaleseUrl;
    }

    @Override // com.amazon.mp3.activity.BaseWebActivity, com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupPublisherForJSBridge();
        init();
    }

    @Override // com.amazon.mp3.activity.BaseWebActivity
    protected void init() {
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            handleNetworkError();
        }
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        onReceivedIntent(intent);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        setIntent(intent);
        onReceivedIntent(intent);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.mFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragment");
        }
        CookieConsentFragment cookieConsentFragment = this.mFragment;
        if (cookieConsentFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragment");
            cookieConsentFragment = null;
        }
        cookieConsentFragment.onBackPressed();
    }

    @Override // com.amazon.mp3.activity.BaseWebActivity
    protected void handleWebEvent(WebEvent webEvent) {
        Intrinsics.checkNotNullParameter(webEvent, "webEvent");
        if (Intrinsics.areEqual(CookieConsentWebEventEnum.CONSENT_ACCEPTED.name(), webEvent.id())) {
            handleUpdateCookiePreference("ACCEPT-ALL");
            return;
        }
        if (Intrinsics.areEqual(CookieConsentWebEventEnum.CONSENT_DENIED.name(), webEvent.id())) {
            handleUpdateCookiePreference("REJECT-ALL");
        } else if (Intrinsics.areEqual(CookieConsentWebEventEnum.CONSENT_CUSTOMIZED.name(), webEvent.id())) {
            onConsentFlowCompletion();
            handleManageCookiePreference();
        }
    }

    private final void onReceivedIntent(Intent intent) {
        Serializable serializableExtra = intent.getSerializableExtra("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET");
        if (serializableExtra == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.web.webtarget.WebTarget");
        }
        setupNewFragment((WebTarget) serializableExtra);
    }

    private final void setupNewFragment(WebTarget webTarget) {
        CookieConsentFragment cookieConsentFragmentNewInstance = CookieConsentFragment.INSTANCE.newInstance(webTarget);
        this.mFragment = cookieConsentFragmentNewInstance;
        CookieConsentFragment cookieConsentFragment = null;
        if (cookieConsentFragmentNewInstance == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragment");
            cookieConsentFragmentNewInstance = null;
        }
        cookieConsentFragmentNewInstance.setPublisher(this.mWebEventPublisher);
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        CookieConsentFragment cookieConsentFragment2 = this.mFragment;
        if (cookieConsentFragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFragment");
        } else {
            cookieConsentFragment = cookieConsentFragment2;
        }
        fragmentTransactionBeginTransaction.replace(R.id.FragmentContainer, cookieConsentFragment, this.TAG_WEB_FRAGMENT).commit();
    }

    private final void onConsentFlowCompletion() {
        CookieConsentManager.instance.markCookieConsentScreenAsShownToUser();
        setResult(-1);
        finish();
    }

    private final void handleManageCookiePreference() {
        URL value = ConfigurableURLs.COOKIE_CONSENT_PREFERENCE_URL.getValue();
        if (value != null) {
            URLDisplayActivity.Companion companion = URLDisplayActivity.INSTANCE;
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "this.applicationContext");
            String string = value.toString();
            Intrinsics.checkNotNullExpressionValue(string, "url.toString()");
            startActivity(companion.getStartIntent(applicationContext, string));
            return;
        }
        Log.error(this.TAG, "URL no found for COOKIE_CONSENT_PREFERENCE_URL.");
    }

    private final void handleUpdateCookiePreference(String consent) {
        CookieConsentManager.instance.cookieConsentUpdate(consent, new Action() { // from class: com.amazon.mp3.activity.CookieConsentActivity$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                CookieConsentActivity.m141handleUpdateCookiePreference$lambda0(this.f$0);
            }
        }, new Consumer() { // from class: com.amazon.mp3.activity.CookieConsentActivity$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                CookieConsentActivity.m142handleUpdateCookiePreference$lambda1(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleUpdateCookiePreference$lambda-0, reason: not valid java name */
    public static final void m141handleUpdateCookiePreference$lambda0(CookieConsentActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onConsentFlowCompletion();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleUpdateCookiePreference$lambda-1, reason: not valid java name */
    public static final void m142handleUpdateCookiePreference$lambda1(CookieConsentActivity this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "Failed to update cookie consent preferences for the user, error: ", th);
        this$0.onConsentFlowCompletion();
    }
}
