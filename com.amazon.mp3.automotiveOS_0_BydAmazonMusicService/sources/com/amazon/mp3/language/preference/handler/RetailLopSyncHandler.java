package com.amazon.mp3.language.preference.handler;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.work.PeriodicWorkRequest;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.dialog.fragment.RetailLOPFtuDialogFragment;
import com.amazon.mp3.language.preference.AppDisplayLanguageSetting;
import com.amazon.mp3.language.preference.locale.AppLocales;
import com.amazon.mp3.language.preference.persistence.RetailLopSyncManager;
import com.amazon.mp3.net.stratus.preferences.RetailPreferencesRetriever;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.amazonaws.util.StringUtils;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class RetailLopSyncHandler {

    @NonNull
    private final AccountCredentialStorage accountCredentialStorage;

    @NonNull
    private final Context context;

    @NonNull
    private final String customerId;

    @NonNull
    private final FragmentManager fragmentManager;

    @NonNull
    private final RetailLopSyncManager retailLopSyncManager;

    @NonNull
    private final RetailPreferencesRetriever retailPreferencesRetriever;
    private final String TAG = RetailLopSyncHandler.class.getSimpleName();
    private final long DURATION_MILLI_SECONDS = 1200;
    private final String UNDER_SCORE_DELIMITER = Attributes.PREDEFINED_ATTRIBUTE_PREFIX;
    private final String RETAIL_LANGUAGE_OF_PREFERENCE_IDENTIFIER = "retailLanguageOfPreference";
    private final String RETAIL_LOP_PRESENT_ALERT = "retailLoPPresentAlert";
    private final String RETAIL_LOP_SYNC_ALERT_LATENCY = "retailLoPSyncAlertLatency";
    private final String RETAIL_LOP_SYNC_ALERT_LOP_TIME_LIMIT_EXCEEDED = "retailLoPSyncAlertLoPTimeLimitExceeded";
    private final String RETAIL_LOP_SYNC_CACHED_LOP_EXPIRED = "retailLoPSyncCachedLoPExpired";
    private final String RETAIL_LOP_SYNC_SAME_LANGUAGE_AS_BOOTSTRAP = "retailLoPSyncSameLanguageAsBootstrap";
    private final String RETAIL_LOP_SYNC_UNSUPPORTED_LOP_BY_AM = "retailLoPSyncUnsupportedLoPByAM";
    private final String RETAIL_LOP_SYNC_USER_HAS_PREFERRED_LANGUAGE = "retailLoPSyncUserHasPreferredLanguage";
    private final String FLEX_STR_3_IDENTIFIER = "flexStr3";
    private final String FLEX_NUM_1_IDENTIFIER = "flexNum1";
    private final long INVALID_NUM = -1;
    private final long TTL_FOR_RETAIL_LOP_IN_DAYS = 21;
    private final long RANDOM_DURATION_FOR_TTL_IN_DAYS = 2;
    private final long MINUTES_IN_A_DAY = 1440;
    private final long MS_IN_A_MINUTE = 60000;
    private final long EXPIRATION_DURATION_FOR_DEBUG = PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS;

    public RetailLopSyncHandler(@NonNull final RetailPreferencesRetriever retailPreferencesRetriever, @NonNull final RetailLopSyncManager retailLopSyncManager, @NonNull final AccountCredentialStorage accountCredentialStorage, @NonNull final FragmentManager fragmentManager, @NonNull final String customerId, @NonNull final Context context) {
        this.retailPreferencesRetriever = retailPreferencesRetriever;
        this.retailLopSyncManager = retailLopSyncManager;
        this.accountCredentialStorage = accountCredentialStorage;
        this.fragmentManager = fragmentManager;
        this.customerId = customerId;
        this.context = context;
    }

    public void initiateRetailLopFtuPrompt() {
        if (this.retailLopSyncManager.isRetailLopFetched(this.customerId) && !isRetailLopStale().booleanValue()) {
            showRetailLopPrompt();
            return;
        }
        String deviceId = this.accountCredentialStorage.getDeviceId();
        String deviceType = this.accountCredentialStorage.getDeviceType();
        String appDisplayLanguage = getAppDisplayLanguage();
        String deviceLanguage = getDeviceLanguage(this.context);
        if (isRetailLopStale().booleanValue()) {
            sendUiFlexEventMetric("retailLoPSyncCachedLoPExpired", appDisplayLanguage, deviceLanguage, this.retailLopSyncManager.fetchRetailLanguageOfPreference(this.customerId), -1L);
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        String strRetrievePreference = this.retailPreferencesRetriever.retrievePreference(this.customerId, deviceId, deviceType, "retailLanguageOfPreference");
        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
        Log.info(this.TAG, "Call for fetching retail language of preference took " + jCurrentTimeMillis2 + "ms");
        if (StringUtils.isBlank(strRetrievePreference)) {
            return;
        }
        Log.info(this.TAG, "Retail Language of Preference for Customer Id " + this.customerId + " is " + strRetrievePreference);
        this.retailLopSyncManager.saveRetailLanguageOfPreference(strRetrievePreference, this.customerId);
        this.retailLopSyncManager.saveTimeOfRetailLopFetch(this.customerId);
        sendUiFlexEventMetric("retailLoPSyncAlertLatency", appDisplayLanguage, deviceLanguage, strRetrievePreference, Long.valueOf(jCurrentTimeMillis2));
        if (jCurrentTimeMillis2 <= 1200) {
            showRetailLopPrompt();
        } else {
            sendUiFlexEventMetric("retailLoPSyncAlertLoPTimeLimitExceeded", appDisplayLanguage, deviceLanguage, strRetrievePreference, Long.valueOf(jCurrentTimeMillis2));
        }
    }

    public Boolean shouldDisplayRetailLopFtuPrompt() {
        return Boolean.valueOf(((this.retailLopSyncManager.isRetailLopSyncNotificationShown(this.customerId) && this.retailLopSyncManager.isRetailLopSyncCompleted(this.customerId)) || hasUserInteractedWithAMLanguageSelector().booleanValue()) ? false : true);
    }

    private Boolean hasUserInteractedWithAMLanguageSelector() {
        Boolean boolValueOf = Boolean.valueOf(this.retailLopSyncManager.hasUserInteractedWithAMLanguageSelector());
        if (boolValueOf.booleanValue()) {
            sendUiFlexEventMetric("retailLoPSyncUserHasPreferredLanguage", getAppDisplayLanguage(), getDeviceLanguage(this.context), null, -1L);
        }
        return boolValueOf;
    }

    private void showRetailLopPrompt() {
        String strFetchRetailLanguageOfPreference = this.retailLopSyncManager.fetchRetailLanguageOfPreference(this.customerId);
        if (!isRetailLopSupportedByAmazonMusic(strFetchRetailLanguageOfPreference) || !isRetailLopDifferentThanAMLanguage(strFetchRetailLanguageOfPreference)) {
            Log.info(this.TAG, "Conditions not fulfilled for showing Retail LOP Sync prompt");
            return;
        }
        AmazonApplication.getDefaultHandler().post(new Runnable() { // from class: com.amazon.mp3.language.preference.handler.RetailLopSyncHandler.1
            @Override // java.lang.Runnable
            public void run() {
                FragmentTransaction fragmentTransactionBeginTransaction = RetailLopSyncHandler.this.fragmentManager.beginTransaction();
                fragmentTransactionBeginTransaction.add(new RetailLOPFtuDialogFragment(), RetailLOPFtuDialogFragment.class.getSimpleName());
                fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            }
        });
        sendUiPageViewMetric(PageType.RETAIL_LOP_SYNC_NOTIFICATION);
        sendUiFlexEventMetric("retailLoPPresentAlert", getAppDisplayLanguage(), getDeviceLanguage(this.context), strFetchRetailLanguageOfPreference, -1L);
        this.retailLopSyncManager.retailLopSyncNotificationShown(this.customerId);
    }

    private boolean isRetailLopSupportedByAmazonMusic(final String retailLanguageOfPreference) {
        String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
        if (StringUtils.isBlank(retailLanguageOfPreference)) {
            return false;
        }
        boolean zIsAppLocaleSupportedByMusicTerritory = AppDisplayLanguageSetting.INSTANCE.isAppLocaleSupportedByMusicTerritory(getAppLocale(retailLanguageOfPreference), musicTerritoryOfResidence);
        if (!zIsAppLocaleSupportedByMusicTerritory) {
            sendUiFlexEventMetric("retailLoPSyncUnsupportedLoPByAM", getAppDisplayLanguage(), getDeviceLanguage(this.context), retailLanguageOfPreference, -1L);
        }
        return zIsAppLocaleSupportedByMusicTerritory;
    }

    private boolean isRetailLopDifferentThanAMLanguage(final String retailLanguageOfPreference) {
        String appDisplayLanguage = getAppDisplayLanguage();
        if (StringUtils.isBlank(retailLanguageOfPreference)) {
            return false;
        }
        boolean z = !appDisplayLanguage.equals(getAppLocale(retailLanguageOfPreference).toString());
        if (!z) {
            sendUiFlexEventMetric("retailLoPSyncSameLanguageAsBootstrap", appDisplayLanguage, getDeviceLanguage(this.context), retailLanguageOfPreference, -1L);
        }
        return z;
    }

    @VisibleForTesting
    public String getAppDisplayLanguage() {
        return AppDisplayLanguageSetting.INSTANCE.getDisplayLanguage(this.context);
    }

    private AppLocales getAppLocale(final String languageOfPreference) {
        return AppLocales.getAppLocale(new Locale(languageOfPreference.split(Attributes.PREDEFINED_ATTRIBUTE_PREFIX)[0], languageOfPreference.split(Attributes.PREDEFINED_ATTRIBUTE_PREFIX)[1]));
    }

    private String getDeviceLanguage(final Context context) {
        return context.getResources().getConfiguration().getLocales().get(0).getLanguage();
    }

    private void sendUiPageViewMetric(final PageType pageType) {
        UiPageViewEvent uiPageViewEventBuild = UiPageViewEvent.builder(pageType.getMetricsValue()).withViewType(ScreenUtil.getScreenViewType(this.context)).build();
        if (uiPageViewEventBuild != null) {
            MetricsHolder.getManager().handleEvent(uiPageViewEventBuild);
        }
    }

    private void sendUiFlexEventMetric(final String flexEventName, final String flexStr1, final String flexStr2, final String flexStr3, final Long flexNum1) {
        FlexEvent flexEventBuild = FlexEvent.builder(flexEventName).withFlexStr1(flexStr1).withFlexStr2(flexStr2).build();
        if (flexStr3 != null) {
            flexEventBuild.addAttribute("flexStr3", flexStr3);
        }
        if (flexNum1.longValue() != -1) {
            flexEventBuild.addAttribute("flexNum1", flexNum1);
        }
        if (flexEventBuild != null) {
            MetricsHolder.getManager().handleEvent(flexEventBuild);
        }
    }

    private Boolean isRetailLopStale() {
        Long lValueOf = Long.valueOf(this.retailLopSyncManager.fetchRetailLopFetchTime(this.customerId));
        if (lValueOf.longValue() == 0) {
            return Boolean.FALSE;
        }
        Boolean boolValueOf = Boolean.valueOf(Math.abs(Long.valueOf(System.currentTimeMillis()).longValue() - lValueOf.longValue()) >= getExpirationDurationForRetailLop().longValue());
        if (boolValueOf.booleanValue()) {
            Log.info(this.TAG, "Stored Retail Lop is Stale, Will need to call Stratus again for fetching retail language of Pref");
        }
        return boolValueOf;
    }

    private Long getExpirationDurationForRetailLop() {
        return Long.valueOf((((long) Math.random()) * 2 * 1440 * 60000) + 1814400000);
    }
}
