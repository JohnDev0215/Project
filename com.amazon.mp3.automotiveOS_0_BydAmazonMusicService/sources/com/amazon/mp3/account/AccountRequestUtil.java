package com.amazon.mp3.account;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailStorage;
import com.amazon.mp3.account.details.AccountDetails;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.cirrus.CirrusExceptions;
import com.amazon.mp3.net.cirrus.CirrusV3Request;
import com.amazon.mp3.net.cirrus.LibraryRequest;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.store.html5.map.CookiesManager;
import com.amazon.mp3.store.html5.map.MAPCookiesManager;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.DataNotReadyException;
import com.amazon.music.account.MusicAccountNotCreatedException;
import com.amazon.music.account.User;
import com.amazon.music.config.Configuration;
import com.amazon.music.push.PushMessagingClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes3.dex */
public final class AccountRequestUtil {
    private static final String TAG = "AccountRequestUtil";
    private static String currentCustomerId;

    private AccountRequestUtil() {
    }

    private static void refreshFreeTrialEligibility(Context context) {
        AccountDetailStorage.get(context).setFreeTrialEligible(false);
    }

    public static void acceptTermsOfUse() {
        AccountManagerSingleton.get().acceptTermsOfUse();
    }

    public static synchronized void refreshAndStoreAccountDetailsForCurrentUser(Context context) throws Exception {
        AccountDetailStorage.get(context).setAccountDetails(getAccountDetails(context));
        AccountDetailStorage.get(context).setLastFetchedCirrusTrackCountTime(-1L);
        refreshAndStoreMRICTokenForCurrentUser(context);
    }

    private static synchronized void refreshAndStoreMRICTokenForCurrentUser(Context context) {
        ChildUserUtil childUserUtil = ChildUserUtil.INSTANCE;
        if (childUserUtil.isChildUser(context)) {
            childUserUtil.saveMusicRequestIdentityContextToken(context, new AccountManagerFactory().buildAccountManager(context).fetchMusicRequestIdentityContextToken());
        }
    }

    public static boolean setPrimeUserStatus(Context context, boolean primeAccessible) {
        boolean z = AccountDetailStorage.get(context).isPrimeMusicAccessible() != primeAccessible;
        if (z) {
            AccountDetailStorage.get(context).setPrimeMusicAccessible(primeAccessible);
            handlePrimeStatusChanged(context, primeAccessible);
        }
        return z;
    }

    public static String generateNonce(Context context) throws JSONException, AbstractHttpClient.HttpClientException, CirrusExceptions.CirrusException {
        if (!AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            return null;
        }
        JSONObject jSONObjectExecute = LibraryRequest.GenerateNonce.execute(new JSONObject().put("nonceContext", "accountValidation"));
        if (!jSONObjectExecute.isNull("generateNonceResponse")) {
            JSONObject jSONObject = jSONObjectExecute.getJSONObject("generateNonceResponse");
            if (!jSONObject.isNull("generateNonceResult")) {
                String strSanitizeResponseString = sanitizeResponseString(jSONObject.getJSONObject("generateNonceResult").getString("nonce"));
                AccountCredentialStorage.get(context).setCirrusNonce(strSanitizeResponseString);
                return strSanitizeResponseString;
            }
        }
        return null;
    }

    public static CustomerHomeDetails getCustomerHome(final Context context) throws Exception {
        User userRetrieveAccount = retrieveAccount(context);
        boolean zHasCloudPlayerAccount = userRetrieveAccount.hasCloudPlayerAccount();
        String obfuscatedId = userRetrieveAccount.getLibraryHomeMarketplace().getObfuscatedId();
        AccountDetailStorage accountDetailStorage = AccountDetailStorage.get(context);
        String libraryHomeMarketplace = accountDetailStorage.getLibraryHomeMarketplace("");
        accountDetailStorage.setCloudPlayerAccountExist(zHasCloudPlayerAccount);
        accountDetailStorage.setLibraryHomeMarketplace(obfuscatedId);
        if (!libraryHomeMarketplace.equals(obfuscatedId)) {
            loadNewRemoteConfig(context);
        }
        return new CustomerHomeDetails(zHasCloudPlayerAccount, obfuscatedId);
    }

    private static void loadNewRemoteConfig(final Context context) {
        BootstrapSingletonTask.get(context).registerUserObserver().observeOn(Schedulers.io()).doOnCompleted(new Action0() { // from class: com.amazon.mp3.account.AccountRequestUtil.2
            @Override // rx.functions.Action0
            public void call() {
                Configuration configuration = AmazonApplication.getConfiguration(context);
                configuration.clearConfiguration();
                configuration.reloadRemoteEndpoint();
            }
        }).doOnError(new Action1<Throwable>() { // from class: com.amazon.mp3.account.AccountRequestUtil.1
            @Override // rx.functions.Action1
            public void call(Throwable throwable) {
                Log.warning(AccountRequestUtil.TAG, "Error loading the new remote config for home market change. BootstrapSingletonTask error message: " + throwable.getMessage(), throwable);
            }
        }).subscribe();
    }

    public static AccountDetails getAccountDetails(final Context context) throws Exception {
        AccountDetailStorage accountDetailStorage = AccountDetailStorage.get(context);
        User userRetrieveAccount = retrieveAccount(context);
        boolean zIsAccountVerified = userRetrieveAccount.isAccountVerified();
        accountDetailStorage.setPrimeMusicMarket(userInPrimeSupportedMarketplace());
        return new AccountDetails(userRetrieveAccount.getCloudAccountStatus().toString(), userHasPrimeBenefit(), zIsAccountVerified);
    }

    public static boolean isAccountValid(final Context context) {
        try {
            if (!AccountManagerSingleton.get().getUser().isAccountValid()) {
                return false;
            }
            if (AccountManagerSingleton.get().shouldInvokeAccountWorkflow()) {
                if (!AccountDetailStorage.get(context).migrationCandidateCompletedAccountFlow()) {
                    return false;
                }
            }
            return true;
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused) {
            return false;
        }
    }

    public static boolean isMigrationCandidate() {
        try {
            if (AccountManagerSingleton.get().getUser().isAccountValid()) {
                return AccountManagerSingleton.get().shouldInvokeAccountWorkflow();
            }
            return false;
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused) {
            return false;
        }
    }

    private static boolean userInPrimeSupportedMarketplace() {
        try {
            return AccountManagerSingleton.get().getUser().isInPrimeMarketplace();
        } catch (DataNotReadyException | MusicAccountNotCreatedException unused) {
            return false;
        }
    }

    private static boolean userHasPrimeBenefit() {
        return UserSubscriptionUtil.getUserSubscription().isPrime();
    }

    public static void updateUser() {
        AccountManagerSingleton.get().updateUser();
    }

    @VisibleForTesting
    protected static User retrieveAccount(final Context context) throws Exception {
        AccountManagerSingleton.get().updateUser();
        User user = AccountManagerSingleton.get().getUser();
        final String customerId = user.getCustomerId();
        String str = currentCustomerId;
        if (str == null || !str.equals(customerId)) {
            BootstrapSingletonTask.get(context).registerFeatureFlagObserver().take(1).observeOn(Schedulers.io()).map(new Func1() { // from class: com.amazon.mp3.account.AccountRequestUtil$$ExternalSyntheticLambda0
                @Override // rx.functions.Func1
                public final Object call(Object obj) {
                    return AccountRequestUtil.lambda$retrieveAccount$0((Void) obj);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.account.AccountRequestUtil.3
                @Override // rx.functions.Action1
                public void call(final Object o) {
                    new PushMessagingClient().setUserId(customerId, context, o != null ? o.toString() : "");
                }
            });
        }
        return user;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$retrieveAccount$0(Void r0) {
        return getAuthToken();
    }

    public static boolean refreshPrimeBenefits(Context context) throws Exception {
        refreshFreeTrialEligibility(context);
        retrieveAccount(context);
        AccountDetailStorage.get(context).setPrimeMusicMarket(userInPrimeSupportedMarketplace());
        return setPrimeUserStatus(context, userHasPrimeBenefit());
    }

    private static void handlePrimeStatusChanged(Context context, boolean isPrimeCustomer) {
        context.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
        context.sendBroadcast(new Intent("com.amazon.mp3.account.credentials.ACTION_NEW_CREDENTIALS"));
        String str = TAG;
        Object[] objArr = new Object[1];
        objArr[0] = isPrimeCustomer ? "now" : "no longer";
        Log.info(str, "Prime content is %s accessible", objArr);
    }

    public static int getCirrusTrackCount() {
        Context context = AmazonApplication.getContext();
        if (!AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            AccountDetailStorage.get().setLastFetchedCirrusTrackCountTime(System.currentTimeMillis());
            return 0;
        }
        if (AccountCredentialUtil.get(context).isSignedOut()) {
            AccountDetailStorage.get().setLastFetchedCirrusTrackCountTime(-1L);
            return 0;
        }
        if (!AccountDetailStorage.get().isAccountVerified()) {
            AccountDetailStorage.get().setLastFetchedCirrusTrackCountTime(-1L);
            return 0;
        }
        try {
            JSONObject jSONObjectExecute = CirrusV3Request.GetLibraryCounts.execute(createCountTracksRequest());
            AccountDetailStorage.get().setLastFetchedCirrusTrackCountTime(System.currentTimeMillis());
            int i = jSONObjectExecute.getJSONObject("counts").getInt("TRACKS");
            AccountDetailStorage.get().setHasTracksCirrus(i > 0);
            return i;
        } catch (AbstractHttpClient.CanceledException e) {
            Log.error(TAG, "CanceledException during getCirrusTrackCount()", e);
            return 0;
        } catch (AbstractHttpClient.FailedException e2) {
            Log.error(TAG, "FailedException during getCirrusTrackCount()", e2);
            return 0;
        } catch (AbstractHttpClient.IncompleteResultException e3) {
            Log.error(TAG, "IncompleteResultExeception during getCirrusTrackCount()", e3);
            return 0;
        } catch (AbstractHttpClient.UnexpectedHttpStatusException e4) {
            Log.error(TAG, "UnexpectedHttpStatusException during getCirrusTrackCount()", e4);
            return 0;
        } catch (CirrusExceptions.CirrusException e5) {
            Log.error(TAG, "CirrusException during getCirrusTrackCount()", e5);
            return 0;
        } catch (ServiceException e6) {
            Log.error(TAG, "ServiceException during getCirrusTrackCount()", e6);
            return 0;
        } catch (JSONException e7) {
            Log.error(TAG, "JSONException during getCirrusTrackCount()", e7);
            return 0;
        }
    }

    private static JSONObject createCountTracksRequest() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("TRACKS");
        jSONObject.put("counts", jSONArray);
        return jSONObject;
    }

    private static String sanitizeResponseString(String str) {
        if (str == null || "null".equals(str) || str.length() == 0) {
            return null;
        }
        return str;
    }

    private static String getAuthToken() {
        CookiesManager cookiesManager = MAPCookiesManager.getCookiesManager();
        if (cookiesManager == null) {
            return "";
        }
        try {
            return cookiesManager.getToken();
        } catch (Exception e) {
            Log.error(TAG, "Exception from MAPCookiesManager getToken:", e);
            return "";
        }
    }
}
