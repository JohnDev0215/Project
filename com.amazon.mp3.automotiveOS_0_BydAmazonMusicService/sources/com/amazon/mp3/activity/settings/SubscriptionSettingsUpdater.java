package com.amazon.mp3.activity.settings;

import android.content.Context;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.config.Market;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.weblab.LegacyWeblabController;
import com.amazon.mp3.weblab.Weblab;
import com.amazon.music.nautilus.SubscriptionManager;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.WeblabProvider;
import com.amazon.music.subscription.UserSubscription;

/* JADX INFO: loaded from: classes3.dex */
public class SubscriptionSettingsUpdater {
    private static final String TAG = "SubscriptionSettingsUpdater";
    final AccountDetailUtil mAccountDetailUtil;
    final PreferenceCategory mCategory;
    final PreferenceCategory mCategoryCancelMembership;
    final Context mContext;
    final SubscriptionManager mHawkfireSubscriptionManager;
    final Preference mManageCancelMembershipPreference;
    final Preference mManageFamilyPreference;
    final Preference mManageSubscriptionPreference;
    final UserSubscription mSubscription;
    final Preference mUpsellPreference;

    public enum CustomerConfiguration {
        HAS_HAWKFIRE_ALL_DEVICES,
        HAS_HAWKFIRE_HOME,
        HAS_HAWKFIRE_FAMILY,
        HAS_HAWKFIRE_KATANA,
        HAS_GBP_SUBSCRIPTION,
        HAS_ONLY_PRIME_IN_HAWKFIRE_MARKET_PLACE,
        HAS_NO_SUBSCRIPTION_IN_HAWKFIRE_MARKET_PLACE,
        HAS_SONIC_RUSH,
        HAS_ONLY_PRIME_NOT_IN_HAWKFIRE_MARKET_PLACE,
        HAS_NO_SUBSCRIPTION_NOT_IN_HAWKFIRE_MARKET_PLACE,
        IS_CHILD_USER,
        HAS_ONLY_PRIME_IN_HAWKFIRE_MARKET_PLACE_CONSUMPTION_ONLY,
        HAS_NO_SUBSCRIPTION_IN_HAWKFIRE_MARKET_PLACE_CONSUMPTION_ONLY,
        INVALID
    }

    public SubscriptionSettingsUpdater(final Preference upsellPreference, final Preference manageSubscriptionPreference, final Preference manageFamilyPreference, final Preference manageCancelMembershipPreference, final PreferenceCategory category, final PreferenceCategory categoryCancelMembership, final AccountDetailUtil accountDetailUtil, final SubscriptionManager hawkfireSubscriptionManager, final UserSubscription subscription, final Context context) {
        this.mUpsellPreference = upsellPreference;
        this.mManageSubscriptionPreference = manageSubscriptionPreference;
        this.mManageFamilyPreference = manageFamilyPreference;
        this.mManageCancelMembershipPreference = manageCancelMembershipPreference;
        this.mCategory = category;
        this.mCategoryCancelMembership = categoryCancelMembership;
        this.mAccountDetailUtil = accountDetailUtil;
        this.mHawkfireSubscriptionManager = hawkfireSubscriptionManager;
        this.mSubscription = subscription;
        this.mContext = context;
    }

    public void update() {
        if (this.mCategory == null || this.mCategoryCancelMembership == null || this.mUpsellPreference == null || this.mManageSubscriptionPreference == null || this.mManageFamilyPreference == null || this.mManageCancelMembershipPreference == null) {
            return;
        }
        CustomerConfiguration customerConfigurationDetermineCustomerConfiguration = determineCustomerConfiguration();
        boolean zIsDECustomerAndInCancelMembershipWeblabT1 = isDECustomerAndInCancelMembershipWeblabT1();
        Log.debug(TAG, "subscription status userConfiguration is: " + customerConfigurationDetermineCustomerConfiguration.toString());
        switch (AnonymousClass1.$SwitchMap$com$amazon$mp3$activity$settings$SubscriptionSettingsUpdater$CustomerConfiguration[customerConfigurationDetermineCustomerConfiguration.ordinal()]) {
            case 1:
            case 2:
                removeUpsellPreference();
                removeManageSubscriptionPreference();
                removeManageFamilyPreference();
                removeCancelMembershipPreference();
                break;
            case 3:
                removeUpsellPreference();
                removeManageFamilyPreference();
                this.mManageSubscriptionPreference.setTitle(R.string.dmusic_setting_hawkfire_home_title);
                this.mManageSubscriptionPreference.setSummary(R.string.dmusic_setting_manage_subscription);
                if (zIsDECustomerAndInCancelMembershipWeblabT1) {
                    this.mManageCancelMembershipPreference.setTitle(R.string.dmusic_setting_cancel_membership_title);
                } else {
                    removeCancelMembershipPreference();
                }
                break;
            case 4:
                removeUpsellPreference();
                this.mManageSubscriptionPreference.setTitle(R.string.dmusic_setting_hawkfire_title);
                this.mManageSubscriptionPreference.setSummary(R.string.dmusic_setting_manage_subscription);
                this.mManageFamilyPreference.setTitle(R.string.dmusic_setting_manage_family_title);
                this.mManageFamilyPreference.setSummary(R.string.dmusic_setting_manage_family);
                if (zIsDECustomerAndInCancelMembershipWeblabT1) {
                    this.mManageCancelMembershipPreference.setTitle(R.string.dmusic_setting_cancel_membership_title);
                } else {
                    removeCancelMembershipPreference();
                }
                break;
            case 5:
                removeUpsellPreference();
                removeManageFamilyPreference();
                removeCancelMembershipPreference();
                if (zIsDECustomerAndInCancelMembershipWeblabT1) {
                    this.mManageSubscriptionPreference.setTitle(R.string.dmusic_setting_hawkfire_title);
                    this.mManageSubscriptionPreference.setSummary(R.string.dmusic_setting_gbp_manage_membership);
                } else {
                    this.mManageSubscriptionPreference.setTitle(R.string.dmusic_setting_hawkfire_title);
                    this.mManageSubscriptionPreference.setSummary(R.string.dmusic_setting_manage_subscription);
                }
                break;
            case 6:
                removeUpsellPreference();
                removeManageFamilyPreference();
                this.mManageSubscriptionPreference.setTitle(R.string.dmusic_setting_hawkfire_title);
                this.mManageSubscriptionPreference.setSummary(R.string.dmusic_setting_manage_subscription);
                if (zIsDECustomerAndInCancelMembershipWeblabT1) {
                    this.mManageCancelMembershipPreference.setTitle(R.string.dmusic_setting_cancel_membership_title);
                } else {
                    removeCancelMembershipPreference();
                }
                break;
            case 7:
                removeUpsellPreference();
                removeManageFamilyPreference();
                this.mManageSubscriptionPreference.setTitle(R.string.dmusic_setting_hawkfire_title);
                this.mManageSubscriptionPreference.setSummary(R.string.dmusic_setting_manage_subscription);
                if (zIsDECustomerAndInCancelMembershipWeblabT1) {
                    this.mManageCancelMembershipPreference.setTitle(R.string.dmusic_setting_cancel_membership_title);
                } else {
                    removeCancelMembershipPreference();
                }
                break;
            case 8:
                removeUpsellPreference();
                removeManageFamilyPreference();
                removeCancelMembershipPreference();
                this.mManageSubscriptionPreference.setTitle(R.string.dmusic_setting_manage_subscription_prime);
                break;
            case 9:
                updateUpsellPreference();
                removeManageSubscriptionPreference();
                removeManageFamilyPreference();
                removeCancelMembershipPreference();
                break;
            case 10:
            case 11:
                removeUpsellPreference();
                removeManageFamilyPreference();
                removeCancelMembershipPreference();
                this.mManageSubscriptionPreference.setTitle(R.string.dmusic_setting_manage_subscription_prime);
                break;
            case 12:
            case 13:
                removeUpsellPreference();
                removeManageSubscriptionPreference();
                removeManageFamilyPreference();
                removeCancelMembershipPreference();
                break;
            case 14:
                removeUpsellPreference();
                removeManageFamilyPreference();
                removeCancelMembershipPreference();
                this.mManageSubscriptionPreference.setTitle(R.string.dmusic_setting_manage_subscription_prime);
                break;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.activity.settings.SubscriptionSettingsUpdater$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$activity$settings$SubscriptionSettingsUpdater$CustomerConfiguration;

        static {
            int[] iArr = new int[CustomerConfiguration.values().length];
            $SwitchMap$com$amazon$mp3$activity$settings$SubscriptionSettingsUpdater$CustomerConfiguration = iArr;
            try {
                iArr[CustomerConfiguration.INVALID.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$SubscriptionSettingsUpdater$CustomerConfiguration[CustomerConfiguration.IS_CHILD_USER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$SubscriptionSettingsUpdater$CustomerConfiguration[CustomerConfiguration.HAS_HAWKFIRE_HOME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$SubscriptionSettingsUpdater$CustomerConfiguration[CustomerConfiguration.HAS_HAWKFIRE_FAMILY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$SubscriptionSettingsUpdater$CustomerConfiguration[CustomerConfiguration.HAS_GBP_SUBSCRIPTION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$SubscriptionSettingsUpdater$CustomerConfiguration[CustomerConfiguration.HAS_HAWKFIRE_KATANA.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$SubscriptionSettingsUpdater$CustomerConfiguration[CustomerConfiguration.HAS_HAWKFIRE_ALL_DEVICES.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$SubscriptionSettingsUpdater$CustomerConfiguration[CustomerConfiguration.HAS_ONLY_PRIME_IN_HAWKFIRE_MARKET_PLACE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$SubscriptionSettingsUpdater$CustomerConfiguration[CustomerConfiguration.HAS_NO_SUBSCRIPTION_IN_HAWKFIRE_MARKET_PLACE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$SubscriptionSettingsUpdater$CustomerConfiguration[CustomerConfiguration.HAS_ONLY_PRIME_NOT_IN_HAWKFIRE_MARKET_PLACE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$SubscriptionSettingsUpdater$CustomerConfiguration[CustomerConfiguration.HAS_ONLY_PRIME_IN_HAWKFIRE_MARKET_PLACE_CONSUMPTION_ONLY.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$SubscriptionSettingsUpdater$CustomerConfiguration[CustomerConfiguration.HAS_NO_SUBSCRIPTION_NOT_IN_HAWKFIRE_MARKET_PLACE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$SubscriptionSettingsUpdater$CustomerConfiguration[CustomerConfiguration.HAS_NO_SUBSCRIPTION_IN_HAWKFIRE_MARKET_PLACE_CONSUMPTION_ONLY.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$mp3$activity$settings$SubscriptionSettingsUpdater$CustomerConfiguration[CustomerConfiguration.HAS_SONIC_RUSH.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
        }
    }

    private boolean isDECustomerAndInCancelMembershipWeblabT1() {
        String homeMarketPlaceId = this.mAccountDetailUtil.getHomeMarketPlaceId();
        String musicTerritoryOfResidence = AccountDetailUtil.getMusicTerritoryOfResidence();
        if ((homeMarketPlaceId == null || !Market.GERMANY.getProdObfuscatedMarket().equalsIgnoreCase(homeMarketPlaceId)) && (musicTerritoryOfResidence == null || !Market.GERMANY.getPrimaryCountryCode().equalsIgnoreCase(musicTerritoryOfResidence))) {
            return false;
        }
        return isInCancelMembershipWeblabT1();
    }

    @VisibleForTesting
    public boolean isInCancelMembershipWeblabT1() {
        WeblabProvider weblabProvider = (WeblabProvider) Providers.INSTANCE.get(WeblabProvider.class);
        return weblabProvider != null && weblabProvider.getTreatment(Weblab.DM_ANDROID_SETTINGS_CANCEL_MEMBERSHIP.toString(), true) == WeblabTreatment.T1;
    }

    private CustomerConfiguration determineCustomerConfiguration() {
        if (this.mAccountDetailUtil == null || this.mSubscription == null) {
            return CustomerConfiguration.INVALID;
        }
        SubscriptionManager subscriptionManager = this.mHawkfireSubscriptionManager;
        if (subscriptionManager != null && subscriptionManager.isOnGoogleInAppBillingPlan()) {
            return CustomerConfiguration.HAS_GBP_SUBSCRIPTION;
        }
        if (ChildUserUtil.INSTANCE.isChildUser(this.mContext)) {
            return CustomerConfiguration.IS_CHILD_USER;
        }
        SubscriptionManager subscriptionManager2 = this.mHawkfireSubscriptionManager;
        if ((subscriptionManager2 != null && subscriptionManager2.isOnFamilyPlan()) && WeblabTreatment.T1 == LegacyWeblabController.getInstance().getTreatmentAndRecordTrigger(Weblab.DM_MANAGE_FAMILY_PAGE.toString())) {
            return CustomerConfiguration.HAS_HAWKFIRE_FAMILY;
        }
        if (this.mSubscription.isKatana()) {
            return CustomerConfiguration.HAS_HAWKFIRE_KATANA;
        }
        if (this.mSubscription.isHawkfireHome()) {
            return CustomerConfiguration.HAS_HAWKFIRE_HOME;
        }
        if (this.mSubscription.isHawkfireAllDevices()) {
            return CustomerConfiguration.HAS_HAWKFIRE_ALL_DEVICES;
        }
        if (this.mAccountDetailUtil.isUserInHawkfireMarketplace()) {
            if (this.mSubscription.isPrimeOnly()) {
                if (AmazonApplication.getCapabilities().isGPBLaunchedInTerritory()) {
                    return CustomerConfiguration.HAS_ONLY_PRIME_IN_HAWKFIRE_MARKET_PLACE;
                }
                return CustomerConfiguration.HAS_ONLY_PRIME_IN_HAWKFIRE_MARKET_PLACE_CONSUMPTION_ONLY;
            }
            if (this.mSubscription.isSonicRush()) {
                return CustomerConfiguration.HAS_SONIC_RUSH;
            }
            if (AmazonApplication.getCapabilities().isGPBLaunchedInTerritory()) {
                return CustomerConfiguration.HAS_NO_SUBSCRIPTION_IN_HAWKFIRE_MARKET_PLACE;
            }
            return CustomerConfiguration.HAS_NO_SUBSCRIPTION_IN_HAWKFIRE_MARKET_PLACE_CONSUMPTION_ONLY;
        }
        if (this.mSubscription.isPrimeOnly()) {
            return CustomerConfiguration.HAS_ONLY_PRIME_NOT_IN_HAWKFIRE_MARKET_PLACE;
        }
        if (this.mSubscription.isSonicRush()) {
            return CustomerConfiguration.HAS_SONIC_RUSH;
        }
        return CustomerConfiguration.HAS_NO_SUBSCRIPTION_NOT_IN_HAWKFIRE_MARKET_PLACE;
    }

    private void removeUpsellPreference() {
        this.mCategory.removePreference(this.mUpsellPreference);
    }

    private void removeManageFamilyPreference() {
        this.mCategory.removePreference(this.mManageFamilyPreference);
    }

    private void removeCancelMembershipPreference() {
        this.mCategoryCancelMembership.removePreference(this.mManageCancelMembershipPreference);
    }

    private void removeManageSubscriptionPreference() {
        this.mCategory.removePreference(this.mManageSubscriptionPreference);
    }

    private void updateUpsellPreference() {
        this.mUpsellPreference.setTitle(R.string.dmusic_setting_hawkfire_sign_up_title);
        SubscriptionManager subscriptionManager = this.mHawkfireSubscriptionManager;
        if (subscriptionManager == null) {
            return;
        }
        if (subscriptionManager.hasFreeTrial()) {
            this.mUpsellPreference.setSummary(R.string.dmusic_setting_hawkfire_start_trial);
        } else {
            this.mUpsellPreference.setSummary(R.string.dmusic_setting_hawkfire_sign_up);
        }
    }
}
