package com.amazon.mp3.ftu;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.account.activity.SignInFlowLauncherActivity;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.ATCWebActivity;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.CookieConsentActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.cookieconsent.CookieConsentManager;
import com.amazon.mp3.environment.MusicTerritory;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.inappmessaging.DynamicMessagingManager;
import com.amazon.mp3.inappmessaging.InAppMessagingManager;
import com.amazon.mp3.library.dialog.NetworkErrorDialog;
import com.amazon.mp3.navigation.DeeplinkHandlerActivity;
import com.amazon.mp3.prime.upsell.PrimeUpsellActivity;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.SplashScreen;
import com.amazon.mp3.util.UniqueCodeUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.inappmessaging.external.IAMRequest;
import com.amazon.music.inappmessaging.external.InAppMessageBroadcasts;
import com.amazon.music.inappmessaging.external.WeblabHelper;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.subscription.UserSubscription;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public class FTUActivity extends BaseActivity {
    public static final int REQUEST_CODE = UniqueCodeUtil.nextUniqueCode();
    private static final String TAG = "FTUActivity";
    private DynamicMessagingManager mDynamicMessagingManager;
    private InAppMessagingManager mInAppMessagingManager;
    private Boolean isShowingIAM = Boolean.FALSE;
    private final BroadcastReceiver mIAMBroadcastReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.ftu.FTUActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, final Intent intent) {
            String action = intent.getAction();
            if (action == null) {
                Log.warning(FTUActivity.TAG, "The intent action is null for FTUActivity receiver. Doing nothing.");
                return;
            }
            if (action.equals(InAppMessageBroadcasts.NON_FULLSCREEN_SPLASH) || action.equals(InAppMessageBroadcasts.SPLASH_DISMISSED)) {
                Log.warning(FTUActivity.TAG, "inappmessaging." + action + " broadcast received");
                FTUSettingsUtil.setSuppressHomeForFTUDeeplink(false);
                if (FTUActivity.shouldShowATC(context)) {
                    FTUActivity.this.showATCScreen(context.getApplicationContext());
                }
            }
        }
    };
    private final BroadcastReceiver mDisplayDeeplinkDynamicMessageReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.ftu.FTUActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (FTUActivity.this.mDynamicMessagingManager != null) {
                FTUActivity.this.mDynamicMessagingManager.displayDynamicMessage(FTUActivity.this, new DynamicMessageEvent(Trigger.APP_START), new DynamicMessageMetricsAttributes(null, null, true)).subscribe();
            }
        }
    };
    private final BroadcastReceiver mDeeplinkDynamicMessageFailureReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.ftu.FTUActivity.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (FTUActivity.shouldShowATC(context)) {
                FTUActivity.this.showATCScreen(context.getApplicationContext());
            }
        }
    };

    public static boolean start(final Activity activity, WebTarget webTarget) {
        if (!AccountRequestUtil.isAccountValid(activity)) {
            return false;
        }
        Intent intent = new Intent(activity, (Class<?>) FTUActivity.class);
        intent.putExtra("STARTED_FROM_FTU", true);
        if (webTarget != null) {
            intent.putExtra("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET", webTarget);
        }
        activity.startActivityForResult(intent, REQUEST_CODE);
        return true;
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras == null || !extras.getBoolean("STARTED_FROM_FTU")) {
            Log.warning(TAG, "FTUActivity was not started through the static FTUActivity.start() methods");
        }
        setContentView(R.layout.activity_ftu);
        init(getApplication().getApplicationContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void init(final Context context) {
        if (!ConnectivityUtil.hasAnyInternetConnection()) {
            handleNetworkError();
            return;
        }
        if (AccountCredentialUtil.get().isSignedIn()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(InAppMessageBroadcasts.NON_FULLSCREEN_SPLASH);
            intentFilter.addAction(InAppMessageBroadcasts.SPLASH_DISMISSED);
            registerReceiver(this.mIAMBroadcastReceiver, intentFilter);
            registerReceiver(this.mDisplayDeeplinkDynamicMessageReceiver, new IntentFilter(InAppMessageBroadcasts.DISPLAY_DEEPLINK_DYNAMIC_MESSAGE));
            registerReceiver(this.mDeeplinkDynamicMessageFailureReceiver, new IntentFilter(InAppMessageBroadcasts.DISPLAY_FAILURE_DEEPLINK_DYNAMIC_MESSAGE));
            startUIActivity(context);
            return;
        }
        AccountCredentialUtil.get(context).synchronizeWithDeviceRegistration(this, shouldEnsureRegistration());
    }

    private void startUIActivity(final Context context) {
        if (CookieConsentManager.instance.shouldStartCookieConsentFlow(true)) {
            showCookieConsentUIAndFetchStatusIfNeeded();
            return;
        }
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        boolean zShouldShowATC = shouldShowATC(context);
        if (zShouldShowATC && !userSubscription.isHawkfireAllDevices()) {
            showIAMScreen(context);
        } else if (zShouldShowATC) {
            showATCScreen(context);
        } else {
            showFTUScreen(context);
        }
    }

    private void showIAMScreen(final Context context) {
        IAMRequest iAMRequestAppStartIAMRequest;
        Boolean bool = Boolean.TRUE;
        this.isShowingIAM = bool;
        this.mInAppMessagingManager = new InAppMessagingManager(this);
        this.mDynamicMessagingManager = new DynamicMessagingManager(this);
        String stringExtra = getIntent().getStringExtra("com.amazon.mp3.iam.IAM_REQUEST");
        if (stringExtra != null) {
            iAMRequestAppStartIAMRequest = IAMRequest.fromJson(stringExtra);
        } else {
            iAMRequestAppStartIAMRequest = this.mInAppMessagingManager.appStartIAMRequest();
        }
        if (!WeblabHelper.getInstance().isSplashV2Enabled()) {
            this.mInAppMessagingManager.displayInAppMessage(this, iAMRequestAppStartIAMRequest, bool);
            this.mInAppMessagingManager.getIamShownObservable().take(1).subscribe(new Action1<Boolean>() { // from class: com.amazon.mp3.ftu.FTUActivity.4
                @Override // rx.functions.Action1
                public void call(final Boolean wasIamShown) {
                    if (!wasIamShown.booleanValue()) {
                        if (FTUActivity.shouldShowATC(context)) {
                            FTUActivity.this.showATCScreen(context.getApplicationContext());
                            return;
                        } else {
                            Log.debug(FTUActivity.TAG, "Didn't show ATCExperiment, finishing this activity to avoid infinite spinner");
                            FTUActivity.this.finish();
                            return;
                        }
                    }
                    AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(FTUActivity.this);
                    Log.debug(FTUActivity.TAG, "Didn't show IAM");
                    Log.debug(FTUActivity.TAG, "is normal login (not SSO): " + accountCredentialStorage.hasShownSsoWelcome());
                    if (accountCredentialStorage.hasShownSsoWelcome()) {
                        return;
                    }
                    Log.debug(FTUActivity.TAG, "coming through SSO, finishing this activity to avoid infinite spinner");
                    FTUActivity.this.finish();
                }
            });
        } else {
            this.mDynamicMessagingManager.loadDeeplinkDynamicMessage(new DynamicMessageEvent(Trigger.APP_START), true);
        }
    }

    private void showCookieConsentUIAndFetchStatusIfNeeded() {
        CookieConsentManager cookieConsentManager = CookieConsentManager.instance;
        if (cookieConsentManager.isCookieConsentStatusAvailable()) {
            lambda$showCookieConsentUIAndFetchStatusIfNeeded$0();
        } else {
            cookieConsentManager.cookieConsentSubscription(new Action() { // from class: com.amazon.mp3.ftu.FTUActivity$$ExternalSyntheticLambda0
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() throws Throwable {
                    this.f$0.lambda$showCookieConsentUIAndFetchStatusIfNeeded$0();
                }
            }, new Consumer() { // from class: com.amazon.mp3.ftu.FTUActivity$$ExternalSyntheticLambda1
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) throws Throwable {
                    this.f$0.lambda$showCookieConsentUIAndFetchStatusIfNeeded$1((Throwable) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showCookieConsentUIAndFetchStatusIfNeeded$1(Throwable th) throws Throwable {
        Log.error(TAG, "Failed to fetch cookie consent, error: ", th);
        onGeneralError();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showCookieConsentIfRequiredElseRestartUI, reason: merged with bridge method [inline-methods] */
    public void lambda$showCookieConsentUIAndFetchStatusIfNeeded$0() {
        try {
            if (CookieConsentManager.instance.shouldRequestUserForConsent()) {
                showCookieConsentScreen();
            } else {
                startUIActivity(getApplicationContext());
            }
        } catch (Exception unused) {
            Log.error(TAG, "Failed to show cookie consent UI");
            onGeneralError();
        }
    }

    private void showCookieConsentScreen() {
        SplashScreen.FTU.setShown(null);
        startActivityForResult(CookieConsentActivity.getStartIntent(this), CookieConsentActivity.getREQUEST_CODE());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showATCScreen(final Context context) {
        this.isShowingIAM = Boolean.FALSE;
        Log.debug(TAG, "ATC triggered");
        SplashScreen.FTU.setShown(null);
        FTUSettingsUtil.setFTUShown(this);
        startActivity(ATCWebActivity.start(context));
        finish();
    }

    private void showFTUScreen(final Context context) {
        Bundle extras = getIntent().getExtras();
        WebTarget webTarget = extras != null ? (WebTarget) extras.getSerializable("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET") : null;
        if (webTarget == null) {
            Log.debug(TAG, "webTarget in extras is null, not showing FTU screen");
            FTUSettingsUtil.setFTUShown(this);
            SplashScreen.FTU.setShown(null);
            finish();
            return;
        }
        if (!AccountDetailUtil.getMusicTerritoryOfResidence().equals(MusicTerritory.BRAZIL.value)) {
            Log.debug(TAG, "Not brazil, show welcome splash page");
            startActivityForResult(PrimeUpsellActivity.getWebIntent(this, webTarget), PrimeUpsellActivity.REQUEST_CODE);
            FTUSettingsUtil.setFTUShown(this);
            SplashScreen.FTU.setShown(null);
            return;
        }
        Log.debug(TAG, "this is brazil, skip ftu");
        FTUSettingsUtil.setFTUShown(this);
        SplashScreen.FTU.setShown(null);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGeneralError() {
        Log.warning(TAG, "Received an error while loading the FTU screen");
        setResult(2);
        finish();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SignInFlowLauncherActivity.REQUEST_CODE) {
            if (resultCode == -1) {
                if (shouldShowATC(getApplicationContext())) {
                    showATCScreen(getApplicationContext());
                    return;
                } else {
                    showFTUScreen(getApplicationContext());
                    return;
                }
            }
            Log.warning(TAG, "Unable to register account for FTU");
            onGeneralError();
            return;
        }
        if (requestCode == PrimeUpsellActivity.REQUEST_CODE) {
            if (resultCode == 2) {
                setResult(2);
            } else {
                PrimeUpsellActivity.PrimeUpsellResult primeUpsellResult = PrimeUpsellActivity.PrimeUpsellResult.UPSELL_BACK_BUTTON;
                if (data != null) {
                    primeUpsellResult = (PrimeUpsellActivity.PrimeUpsellResult) data.getSerializableExtra("com.amazon.mp3.extra.PrimeUpsellActivity.PrimeUpsellResult");
                }
                int i = AnonymousClass6.$SwitchMap$com$amazon$mp3$prime$upsell$PrimeUpsellActivity$PrimeUpsellResult[primeUpsellResult.ordinal()];
                if (i == 1 || i == 2) {
                    setResult(-1);
                } else if (i == 3) {
                    setResult(1);
                } else {
                    setResult(0);
                }
            }
            finish();
            return;
        }
        if (requestCode == CookieConsentActivity.getREQUEST_CODE()) {
            startUIActivity(getApplicationContext());
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.ftu.FTUActivity$6, reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$upsell$PrimeUpsellActivity$PrimeUpsellResult;

        static {
            int[] iArr = new int[PrimeUpsellActivity.PrimeUpsellResult.values().length];
            $SwitchMap$com$amazon$mp3$prime$upsell$PrimeUpsellActivity$PrimeUpsellResult = iArr;
            try {
                iArr[PrimeUpsellActivity.PrimeUpsellResult.EXPLORE_PRIME_MUSIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$PrimeUpsellActivity$PrimeUpsellResult[PrimeUpsellActivity.PrimeUpsellResult.UPSELL_SUCCESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$PrimeUpsellActivity$PrimeUpsellResult[PrimeUpsellActivity.PrimeUpsellResult.UPSELL_BACK_BUTTON.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$PrimeUpsellActivity$PrimeUpsellResult[PrimeUpsellActivity.PrimeUpsellResult.UPSELL_DEFER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void handleNetworkError() {
        NetworkErrorDialog.create(this, new NetworkErrorDialog.INetworkErrorDialogHandler() { // from class: com.amazon.mp3.ftu.FTUActivity.5
            @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
            public void onRetryConnection() {
                FTUActivity fTUActivity = FTUActivity.this;
                fTUActivity.init(fTUActivity.getApplicationContext());
            }

            @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
            public void onDialogDismiss() {
                FTUActivity.this.onGeneralError();
            }

            @Override // com.amazon.mp3.library.dialog.NetworkErrorDialog.INetworkErrorDialogHandler
            public void onConnectionErrorDialogCancel() {
                FTUActivity.this.onGeneralError();
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean shouldShowATC(final Context context) {
        return (ChildUserUtil.INSTANCE.isChildUser(context) || FTUSettingsUtil.customerCompletedATC(context) || DeeplinkHandlerActivity.isAppOpenedFromDeeplink() || !FTUSettingsUtil.hasNoRecentsHistory(context) || !((FeatureGateProvider) Providers.INSTANCE.get(FeatureGateProvider.class)).isFeatureAvailable(Feature.artist_taste_collection)) ? false : true;
    }

    public Boolean getIsShowingIAM() {
        return this.isShowingIAM;
    }
}
