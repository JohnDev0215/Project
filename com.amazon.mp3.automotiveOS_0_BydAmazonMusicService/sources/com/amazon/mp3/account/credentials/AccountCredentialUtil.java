package com.amazon.mp3.account.credentials;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.activity.SignInFlowLauncherActivity;
import com.amazon.mp3.account.activity.SsoWelcomeActivity;
import com.amazon.mp3.account.details.AccountDetailStorage;
import com.amazon.mp3.account.signintasks.InvalidSignInCacheStateException;
import com.amazon.mp3.account.signintasks.SignInFlowTasks;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.ftu.FTUSettingsUtil;
import com.amazon.mp3.library.provider.source.nowplaying.RecentItemsManager;
import com.amazon.mp3.ondemandplaylists.api.GetOnDemandPlaylistsService;
import com.amazon.mp3.playback.view.lyrics.FrugalLyricsChecker;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedManager;
import com.amazon.mp3.service.ClearCacheService;
import com.amazon.mp3.store.html5.webview.StoreWebView;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.voice.hints.cache.AlexaHintsCacheManagerImpl;
import com.amazon.mp3.voice.onboarding.AlexaOnboardingUtil;
import com.amazon.mp3.voice.wakeword.WakeWordHelper;
import com.amazon.music.inappmessaging.internal.content.BehavioralAdsUtil;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.media.auto.util.AutomotiveServiceUtil;
import com.amazon.music.push.PushMessagingClient;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public final class AccountCredentialUtil {
    private static final String TAG = "AccountCredentialUtil";
    private static AccountCredentialUtil sInstance;
    private final Context mContext;
    private final AccountCredentialStorage mCredentials;
    private final BehaviorSubject<Boolean> mIsAuthenticatedSubject;

    public static synchronized void createInstance(final Context context) {
        if (sInstance == null && context != null) {
            sInstance = new AccountCredentialUtil(context);
        }
    }

    public static AccountCredentialUtil get(final Context context) {
        if (sInstance == null) {
            createInstance(context);
        }
        return sInstance;
    }

    @Deprecated
    public static AccountCredentialUtil get() {
        if (sInstance == null) {
            createInstance(AmazonApplication.getContext());
        }
        return sInstance;
    }

    private AccountCredentialUtil(final Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mCredentials = AccountCredentialStorage.get(applicationContext);
        this.mIsAuthenticatedSubject = BehaviorSubject.createDefault(Boolean.FALSE);
    }

    public boolean isSignedOut() {
        boolean zIsEmpty = TextUtils.isEmpty(new MapAccount(this.mContext).getADPToken().adpToken);
        this.mIsAuthenticatedSubject.onNext(Boolean.valueOf(!zIsEmpty));
        return zIsEmpty;
    }

    public boolean isSignedIn() {
        return !isSignedOut();
    }

    public boolean isSignInComplete() {
        if (!isSignedIn()) {
            return false;
        }
        try {
            return new SignInFlowTasks(this.mContext).allSignInTasksHaveExecuted();
        } catch (InvalidSignInCacheStateException unused) {
            Log.warning(TAG, "Need to determine if all sign in tasks have been executed but SignInCache is in invalid state. Using fallback method to determine.");
            return isLibraryHomeMarketPlaceInitialized();
        }
    }

    private boolean isLibraryHomeMarketPlaceInitialized() {
        AccountDetailStorage accountDetailStorage = AccountDetailStorage.get(this.mContext);
        return (accountDetailStorage == null || accountDetailStorage.getLibraryHomeMarketplace(null) == null) ? false : true;
    }

    public void synchronizeWithDeviceRegistration(final Activity activity, final boolean automaticallyStartRegistration) {
        AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get(activity);
        String account = MapAccount.getManager(activity).getAccount();
        String accountId = accountCredentialStorage.getAccountId();
        if (TextUtils.isEmpty(account)) {
            if (TextUtils.isEmpty(accountId) || !isSignedIn()) {
                return;
            }
            removeCredentialsAndSettings();
            Log.warning(TAG, "synchronizeWithDeviceRegistration: mapAccount is empty, musicAccount is not empty, user is signed in. Starting clear cache job.");
            ClearCacheService.startClearCache(this.mContext, true);
            return;
        }
        if (!account.equals(accountId)) {
            if (isSignedIn()) {
                removeCredentialsAndSettings();
                Log.warning(TAG, "synchronizeWithDeviceRegistration: mapAccount is not empty, musicAccount is not equal to mapAccount. Starting clear cache job.");
                ClearCacheService.startClearCache(this.mContext, true);
            }
            if (automaticallyStartRegistration) {
                activity.startActivityForResult(new Intent(activity, (Class<?>) SignInFlowLauncherActivity.class), SignInFlowLauncherActivity.REQUEST_CODE);
                return;
            }
            return;
        }
        if (accountCredentialStorage.hasShownSsoWelcome() || AutomotiveServiceUtil.INSTANCE.isAutomotiveServiceRunning()) {
            return;
        }
        activity.startActivity(SsoWelcomeActivity.createIntent(activity));
    }

    public JSONObject buildCirrusCustomerInfo() throws JSONException {
        if (AmazonApplication.getDevModeCapabilities().isHackToCustomCirrusEnabled()) {
            return new JSONObject().put("customerId", "CUSTOMER_ID").put(Splash.PARAMS_DEVICE_TYPE, "NOAUTH").put(Splash.PARAMS_DEVICE_ID, "NOAUTH");
        }
        return new JSONObject().put("customerId", "").put(Splash.PARAMS_DEVICE_TYPE, this.mCredentials.getDeviceType()).put(Splash.PARAMS_DEVICE_ID, this.mCredentials.getDeviceId());
    }

    public void removeCredentialsAndSettings() {
        clearMAPWebViewCookies();
        WakeWordHelper.turnOffWakeWordService(this.mContext);
        SettingsUtil.removeCustomerSpecificPreferences(this.mContext);
        AlexaOnboardingUtil.INSTANCE.clearOnboardingSettings(this.mContext);
        new AlexaHintsCacheManagerImpl(this.mContext).removeAllHints();
        AccountDetailStorage.get(this.mContext).removeAll();
        FTUSettingsUtil.clearFTUSettings(this.mContext);
        RecentlyPlayedManager.getInstance(this.mContext).clearAll();
        RecentItemsManager.getInstance(this.mContext).clearRecentItems();
        DeviceAuthorizationManager.getInstance().removeAll();
        FrugalLyricsChecker.INSTANCE.reset();
        GetOnDemandPlaylistsService.INSTANCE.get().clearOnDemandPlaylists();
        new PushMessagingClient().setSignedIn(this.mContext, false);
        this.mCredentials.removeAll();
        this.mContext.getSharedPreferences("com.amazon.errorBannerSessionPreferences", 0).edit().clear().apply();
        this.mContext.getSharedPreferences(BehavioralAdsUtil.SHARED_PREF_NAME, 0).edit().clear().apply();
        AmazonApplication.getDefaultHandler().postDelayed(new Runnable() { // from class: com.amazon.mp3.account.credentials.AccountCredentialUtil.1
            @Override // java.lang.Runnable
            public void run() {
                StoreWebView.getInstance(AccountCredentialUtil.this.mContext).resetState();
            }
        }, 100L);
        this.mContext.sendBroadcast(new Intent("com.amazon.mp3.account.credentials.ACTION_NEW_CREDENTIALS"));
        Log.info(TAG, "Signed out");
    }

    private void clearMAPWebViewCookies() {
        CookieSyncManager cookieSyncManagerCreateInstance;
        try {
            cookieSyncManagerCreateInstance = CookieSyncManager.getInstance();
        } catch (IllegalStateException unused) {
            cookieSyncManagerCreateInstance = CookieSyncManager.createInstance(this.mContext);
        }
        CookieManager.getInstance().removeAllCookie();
        cookieSyncManagerCreateInstance.sync();
    }

    public String getFirstName() {
        String strTrim = AccountCredentialStorage.get(this.mContext).getUsername().trim();
        int iIndexOf = strTrim.indexOf(32);
        return iIndexOf > 0 ? strTrim.substring(0, iIndexOf) : strTrim;
    }

    public void logDebugInfo() {
        Log.info(TAG, isSignedIn() ? "Signed In" : "Signed Out");
    }

    public Observable<Boolean> getIsAuthenticatedObservable() {
        return this.mIsAuthenticatedSubject.hide();
    }
}
