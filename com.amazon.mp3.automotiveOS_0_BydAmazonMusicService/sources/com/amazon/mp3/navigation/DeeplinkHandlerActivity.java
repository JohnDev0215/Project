package com.amazon.mp3.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.account.signintasks.AddHawkFireBenefitsForChildUser;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.VideoLandingPageBrushFragment;
import com.amazon.mp3.client.activity.LauncherActivity;
import com.amazon.mp3.config.AssociateTag;
import com.amazon.mp3.external.api.AmznUriHandler;
import com.amazon.mp3.init.ForegroundLaunchInitializationTask;
import com.amazon.mp3.init.InitializationHandler;
import com.amazon.mp3.library.dialog.BlockChildUserFlowDialog;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.navigation.BranchManager;
import com.amazon.mp3.playback.video.VideoUtil;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.mp3.service.error.CirrusError;
import com.amazon.mp3.service.error.CirrusErrorLogger;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.WebViewUtil;
import com.amazon.mp3.util.health.AppHealthUtil;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.destination.HomeDestination;
import com.amazon.music.destination.core.Destination;
import java.lang.ref.WeakReference;
import java.util.List;
import kotlinx.coroutines.DebugKt;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public class DeeplinkHandlerActivity extends Activity {
    public static final Uri DEFAULT_FALLBACK_URI = Uri.parse("https://music.amazon.com/");
    private static final String TAG = DeeplinkHandlerActivity.class.getSimpleName();
    private static String sBranchLink;
    private static Uri sDeeplinkRefererUri;
    private static Uri sDeeplinkUri;
    private static boolean sIsAppOpenedFromDeeplink;
    private boolean branchOpenedApp = false;
    private AddHawkFireBenefitsForChildUser.HawkfireBenefitStatus state = AddHawkFireBenefitsForChildUser.HawkfireBenefitStatus.SUBSCRIPTION_NOT_REQUIRED;

    public static boolean isAppOpenedFromDeeplink() {
        return sIsAppOpenedFromDeeplink;
    }

    public static void appOpenedFromDeeplink() {
        sIsAppOpenedFromDeeplink = true;
    }

    public static Uri getDeeplinkUri() {
        return sDeeplinkUri;
    }

    public static void setDeeplinkUri(final Uri deeplinkUri) {
        sDeeplinkUri = deeplinkUri;
    }

    public static Uri getDeeplinkRefererUri() {
        return sDeeplinkRefererUri;
    }

    public static void setDeeplinkRefererUri(final Uri deeplinkRefererUri) {
        sDeeplinkRefererUri = deeplinkRefererUri;
    }

    public static String getBranchLink() {
        return sBranchLink;
    }

    public static void setBranchLink(final String branchLink) {
        sBranchLink = branchLink;
    }

    @Override // android.app.Activity
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_loading_activity);
        new InitializationHandler(new ForegroundLaunchInitializationTask(this, new AppHealthUtil(PreferenceManager.getDefaultSharedPreferences(this)))).initializeOnNewThread();
        onIntentReceived(getIntent());
    }

    @Override // android.app.Activity
    protected void onNewIntent(final Intent intent) {
        onIntentReceived(intent);
    }

    private void onIntentReceived(final Intent intent) {
        setIntent(intent);
        setDeeplinkRefererUri(null);
        setBranchLink(null);
        appOpenedFromDeeplink();
        Uri data = intent.getData();
        MetricsLogger.sendDeeplinkIntentReceivedFlexEvent(data, PlatformUtil.isVersionOrGreater(22) ? getReferrer() : null, false);
        String str = TAG;
        Log.verbose(str, "Activity was launched with this URI in the intent: %s.This may not be the final URI that we navigate to.", data.toString());
        if (ChildUserUtil.INSTANCE.isChildUser(this)) {
            Log.verbose(str, "Applying hawkfire benefits for child user");
            this.state = AddHawkFireBenefitsForChildUser.HawkfireBenefitStatus.SUBSCRIBING;
            applyHawkFireBenefitsForChildUser();
        }
        handleNewNonNullIntent(data, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleNewNonNullIntent(Uri uri, boolean isAccountFull) {
        if (AccountCredentialUtil.get(getApplicationContext()).isSignInComplete() && isHawkfireSubscriptionCompletedForChildUser()) {
            bootstrapHandleUri(uri);
        } else if (this.state == AddHawkFireBenefitsForChildUser.HawkfireBenefitStatus.SUBSCRIPTION_FAILED_WITH_ERROR) {
            showCannotProceedDialog(isAccountFull);
        } else {
            handleUri(uri);
        }
    }

    private void showCannotProceedDialog(boolean isAccountFull) {
        Intent intent = new Intent(this, (Class<?>) BlockChildUserFlowDialog.class);
        intent.putExtra("accountFull", isAccountFull);
        startActivity(intent);
        finish();
    }

    private void bootstrapHandleUri(final Uri uri) {
        BootstrapSingletonTask.get(getApplicationContext()).registerFeatureFlagObserver().take(1).subscribe(new Action1<Void>() { // from class: com.amazon.mp3.navigation.DeeplinkHandlerActivity.1
            @Override // rx.functions.Action1
            public void call(final Void input) {
                DeeplinkHandlerActivity.this.handleUri(uri);
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.navigation.DeeplinkHandlerActivity.2
            @Override // rx.functions.Action1
            public void call(final Throwable throwable) {
                Log.warning(DeeplinkHandlerActivity.TAG, "BootstrapSingletonTask error: " + throwable.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleUri(final Uri uri) {
        AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.navigation.DeeplinkHandlerActivity.3
            @Override // java.lang.Runnable
            public void run() {
                if (DeeplinkHandlerActivity.this.isBranchLink(uri)) {
                    DeeplinkHandlerActivity.this.branchOpenedApp = true;
                } else {
                    DeeplinkHandlerActivity.this.handleDeeplink(uri);
                }
            }
        });
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        new BranchManager().getUriFromBranchReferringParameters(this).subscribe(new Action1<Uri>() { // from class: com.amazon.mp3.navigation.DeeplinkHandlerActivity.4
            @Override // rx.functions.Action1
            public void call(final Uri uri) {
                Log.debug(DeeplinkHandlerActivity.TAG, "Retrieved deep link URI from Branch. URI: " + uri.toString());
                DeeplinkHandlerActivity.this.handleDeeplink(uri);
            }
        }, new Action1<Throwable>() { // from class: com.amazon.mp3.navigation.DeeplinkHandlerActivity.5
            @Override // rx.functions.Action1
            public void call(Throwable throwable) {
                if (throwable instanceof BranchManager.BranchReturnedNoUriException) {
                    Log.debug(DeeplinkHandlerActivity.TAG, "Branch returned no Uri for this session. This is NOT an error if (Case 1) The app was already installed at the time of deep link click and the device is OS >=Android M (a common case) or (Case 2) There are multiple devices that click a Branch link on the same WiFi network (a rare case). Note: if you want to confirm that (Case 2) is happening, you must connect to a 3G hotspot and try again. If you still get this message with the 3G hotspot and (Case 1) does not apply, then this is a true error. Contact Branch.io representatives for support.Message : %s", throwable.getMessage());
                    if (DeeplinkHandlerActivity.this.branchOpenedApp) {
                        Log.error(DeeplinkHandlerActivity.TAG, "Branch opened app but did not return a URI for us to handle. This should not happen. Contact Branch.iorepresentatives for support. Falling back to default url.");
                        DeeplinkHandlerActivity.this.handleDeeplink(Uri.parse("https://music.amazon.com"));
                    }
                }
            }
        });
    }

    static Uri removeLocaleParamIfAny(@NonNull final Uri uri) {
        Uri.Builder builderBuildUpon = uri.buildUpon();
        List<String> pathSegments = uri.getPathSegments();
        if (!pathSegments.isEmpty() && pathSegments.get(0).matches("[A-Za-z]{2}-[A-Za-z]{2}")) {
            String strSubstring = uri.getPath().length() >= 7 ? uri.getPath().substring(6) : "/";
            builderBuildUpon.path(strSubstring);
            builderBuildUpon.encodedPath(strSubstring);
        }
        return builderBuildUpon.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDeeplink(final Uri uri) {
        final Uri uriRemoveLocaleParamIfAny = removeLocaleParamIfAny(uri);
        setDebugLoggingModeIfApplicable(uriRemoveLocaleParamIfAny);
        AssociateTag.getInstance(this).setAssociateTagFromDeeplink(uriRemoveLocaleParamIfAny);
        Uri referrer = PlatformUtil.isVersionOrGreater(22) ? getReferrer() : null;
        if (getDeeplinkRefererUri() != null) {
            referrer = getDeeplinkRefererUri();
        }
        Uri baseDeeplinkUrl = DeeplinksManager.get(this).getBaseDeeplinkUrl(uriRemoveLocaleParamIfAny);
        setDeeplinkUri(baseDeeplinkUrl);
        setDeeplinkRefererUri(referrer);
        MetricsLogger.sendDeeplinkInteractionEvent(baseDeeplinkUrl, baseDeeplinkUrl, referrer, "deeplinkInvoked", getBranchLink());
        if (!AccountCredentialUtil.get(this).isSignInComplete() || !DeviceAuthorizationManager.getInstance().isAuthorized()) {
            startActivityWithIntentUri(LauncherActivity.class, uriRemoveLocaleParamIfAny);
        } else if (!DeviceAuthorizationManager.getInstance().isAuthorized()) {
            AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.navigation.DeeplinkHandlerActivity.6
                @Override // java.lang.Runnable
                public void run() {
                    Intent deviceAuthorizationIntent = WebViewUtil.getDeviceAuthorizationIntent(DeeplinkHandlerActivity.this.getApplicationContext());
                    if (deviceAuthorizationIntent != null) {
                        DeeplinkHandlerActivity.this.startActivity(deviceAuthorizationIntent);
                        if (DeeplinkHandlerActivity.this.isFinishing()) {
                            return;
                        }
                        DeeplinkHandlerActivity.this.finish();
                        return;
                    }
                    DeeplinkHandlerActivity deeplinkHandlerActivity = DeeplinkHandlerActivity.this;
                    deeplinkHandlerActivity.handleDeepLinkHelper(deeplinkHandlerActivity, uriRemoveLocaleParamIfAny);
                }
            });
        } else {
            handleDeepLinkHelper(this, uriRemoveLocaleParamIfAny);
        }
    }

    static class FinishActivityTask implements Runnable {
        private Destination destination;
        private WeakReference<Activity> weakReference;

        FinishActivityTask(final Activity activity, final Destination destination) {
            this.weakReference = new WeakReference<>(activity);
            this.destination = destination;
        }

        @Override // java.lang.Runnable
        public void run() {
            Activity activity;
            if (this.destination.isSuccess()) {
                MetricsLogger.sendDeeplinkInteractionEvent(DeeplinkHandlerActivity.getDeeplinkUri(), DeeplinkHandlerActivity.getDeeplinkUri(), DeeplinkHandlerActivity.getDeeplinkRefererUri(), "deeplinkLanding", DeeplinkHandlerActivity.getBranchLink());
            } else {
                MetricsLogger.sendDeeplinkInteractionEvent(DeeplinkHandlerActivity.DEFAULT_FALLBACK_URI, DeeplinkHandlerActivity.getDeeplinkUri(), DeeplinkHandlerActivity.getDeeplinkRefererUri(), "deeplinkLanding", DeeplinkHandlerActivity.getBranchLink());
            }
            WeakReference<Activity> weakReference = this.weakReference;
            if (weakReference == null || (activity = weakReference.get()) == null || activity.isFinishing()) {
                return;
            }
            activity.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDeepLinkHelper(DeeplinkHandlerActivity deeplinkHandlerActivity, Uri uri) {
        if (deeplinkHandlerActivity.isFinishing()) {
            return;
        }
        SyncService.startSync(this, AccountDetailUtil.get().canSyncRemoteContent() ? 2 : 34);
        try {
            DeeplinksManager deeplinksManager = DeeplinksManager.get(deeplinkHandlerActivity);
            Log.verbose(TAG, "Navigating to deeplink: %s", uri.toString());
            Destination destination = deeplinksManager.getDestination(deeplinksManager.getSanitizedExternalUri(uri));
            if (destination.isChildInappropriate() && ChildUserUtil.INSTANCE.isChildUser(getApplicationContext())) {
                handleDeeplinkForChildUser();
            } else {
                deeplinksManager.handle(destination, new FinishActivityTask(this, destination));
            }
        } catch (Exception unused) {
            if (ChildUserUtil.INSTANCE.isChildUser(getApplicationContext())) {
                handleDeeplinkForChildUser();
                return;
            }
            if (handleAmznLegacyUri(uri) == 0) {
                Log.error(TAG, "Couldn't match uri with legacy uri handlers: %s. Going to home.", uri.toString());
                Context applicationContext = getApplicationContext();
                Intent homeIntent = PrimeActivityFactory.getHomeIntent(applicationContext);
                homeIntent.addFlags(268435456);
                applicationContext.startActivity(homeIntent);
            }
            MetricsLogger.sendDeeplinkInteractionEvent(DEFAULT_FALLBACK_URI, getDeeplinkUri(), getDeeplinkRefererUri(), "deeplinkLanding", getBranchLink());
        }
    }

    public void handleDeeplinkForChildUser() {
        Intent intent = new HomeDestinationHandler().getIntent(getApplicationContext(), new HomeDestination(null, null, null, null));
        intent.addFlags(1073741824);
        intent.putExtra("unavailableDeeplinkForChildUser", true);
        startActivity(intent);
        finish();
    }

    public void startActivityWithIntentUri(final Class activityClass, final Uri uri) {
        Intent intent = new Intent(getApplicationContext(), (Class<?>) activityClass);
        intent.putExtra("com.amazon.mp3.extra.EXTERNAL_URI", uri.toString());
        intent.addFlags(32768);
        intent.addFlags(268435456);
        startActivity(intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isBranchLink(final Uri uri) {
        return "amznmp3".equals(uri.getScheme()) && uri.getQueryParameter("link_click_id") != null;
    }

    private int handleAmznLegacyUri(final Uri uri) {
        Log.debug(TAG, "Handling legacy deeplink %s", uri.toString());
        Intent intentUriToIntent = AmznUriHandler.uriToIntent(this, uri, AmazonApplication.getCapabilities().shouldCloudBeSupported(), AmazonApplication.getCapabilities().isStoreSupported());
        if (intentUriToIntent != null) {
            intentUriToIntent.putExtra("com.amazon.mp3.launch.EXTERNAL", true);
            startActivity(intentUriToIntent);
            return -1;
        }
        CirrusErrorLogger.getInstance().log(CirrusError.PARTNER_API_ERROR, uri.toString());
        return 0;
    }

    private void setDebugLoggingModeIfApplicable(final Uri uri) {
        String queryParameter = uri.getQueryParameter("debug");
        if (queryParameter == null) {
            return;
        }
        setDebugLoggingEnabled("true".equals(queryParameter));
    }

    private void setDebugLoggingEnabled(final boolean shouldTurnOnDebugLogging) {
        Log.setNonCriticalLoggingEnabled(shouldTurnOnDebugLogging);
        Object[] objArr = new Object[1];
        objArr[0] = shouldTurnOnDebugLogging ? DebugKt.DEBUG_PROPERTY_VALUE_ON : DebugKt.DEBUG_PROPERTY_VALUE_OFF;
        Log.info(TAG, String.format("Debug logging has been turned %s.", objArr));
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (ChildUserUtil.INSTANCE.isChildUser(getApplicationContext())) {
            return;
        }
        sendVideoDeeplinkBroadcast();
    }

    private void sendVideoDeeplinkBroadcast() {
        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data.getEncodedPath().contains("videos")) {
            VideoUtil videoUtil = VideoUtil.INSTANCE;
            Intent intent2 = new Intent("com.amazon.mp3.util.handleVideoDeeplink");
            intent2.putExtra("com.amazon.mp3.util.videoDeeplink", data.getLastPathSegment());
            intent2.putExtra("com.amazon.mp3.fragment.extra", VideoLandingPageBrushFragment.class.getSimpleName());
            String stringExtra = intent.getStringExtra("market_referrer");
            if (intent.getExtras() != null && stringExtra != null) {
                String deeplinkParameterFromMarketRef = getDeeplinkParameterFromMarketRef(stringExtra, "ref=");
                if (deeplinkParameterFromMarketRef != null) {
                    intent2.putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), deeplinkParameterFromMarketRef);
                }
                String deeplinkParameterFromMarketRef2 = getDeeplinkParameterFromMarketRef(stringExtra, "tag=");
                if (deeplinkParameterFromMarketRef2 != null) {
                    intent2.putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), deeplinkParameterFromMarketRef2);
                }
            }
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);
        }
    }

    protected static String getDeeplinkParameterFromMarketRef(final String marketReferrer, final String parameter) {
        if (!marketReferrer.contains(parameter)) {
            return null;
        }
        String[] strArrSplit = marketReferrer.split(parameter);
        return strArrSplit[1].substring(0, strArrSplit[1].indexOf(38) != -1 ? strArrSplit[1].indexOf(38) : strArrSplit[1].length());
    }

    private void applyHawkFireBenefitsForChildUser() {
        final Uri data = getIntent().getData();
        new AddHawkFireBenefitsForChildUser(this, new AddHawkFireBenefitsForChildUser.AddHawkFireListener() { // from class: com.amazon.mp3.navigation.DeeplinkHandlerActivity.7
            @Override // com.amazon.mp3.account.signintasks.AddHawkFireBenefitsForChildUser.AddHawkFireListener
            public void onFailure(boolean isAccountFull) {
                Log.error(DeeplinkHandlerActivity.TAG, "Adding Hawkfire benefits to child user failed.");
                DeeplinkHandlerActivity.this.state = AddHawkFireBenefitsForChildUser.HawkfireBenefitStatus.SUBSCRIPTION_FAILED_WITH_ERROR;
                DeeplinkHandlerActivity.this.handleNewNonNullIntent(data, isAccountFull);
            }

            @Override // com.amazon.mp3.account.signintasks.AddHawkFireBenefitsForChildUser.AddHawkFireListener
            public void onSuccess() {
                Log.verbose(DeeplinkHandlerActivity.TAG, "Hawkfire benefits applied successfully to child user.");
                DeeplinkHandlerActivity.this.state = AddHawkFireBenefitsForChildUser.HawkfireBenefitStatus.SUBSCRIPTION_SUCCESS;
                DeeplinkHandlerActivity.this.handleNewNonNullIntent(data, false);
            }
        }).execute();
    }

    private boolean isHawkfireSubscriptionCompletedForChildUser() {
        AddHawkFireBenefitsForChildUser.HawkfireBenefitStatus hawkfireBenefitStatus = this.state;
        return hawkfireBenefitStatus == AddHawkFireBenefitsForChildUser.HawkfireBenefitStatus.SUBSCRIPTION_NOT_REQUIRED || hawkfireBenefitStatus == AddHawkFireBenefitsForChildUser.HawkfireBenefitStatus.SUBSCRIPTION_SUCCESS;
    }
}
