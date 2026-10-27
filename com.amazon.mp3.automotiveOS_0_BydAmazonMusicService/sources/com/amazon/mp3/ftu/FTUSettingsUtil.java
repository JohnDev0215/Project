package com.amazon.mp3.ftu;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.config.UpdateCheck;
import com.amazon.mp3.prime.upsell.PrimeUpsellActivity;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.marketplace.Marketplace;
import com.amazon.music.media.auto.util.AutomotiveServiceUtil;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class FTUSettingsUtil {
    private static boolean sCustomerCompletedATC;
    private static boolean sShouldShowIAMForFTUDeeplink;
    private static boolean sShouldSuppressHomeForFTUDeeplink;

    public static boolean shouldShowIAMForFTUDeeplink() {
        return sShouldShowIAMForFTUDeeplink;
    }

    public static void setShowIAMForFTUDeeplink(final boolean shouldShow) {
        sShouldShowIAMForFTUDeeplink = shouldShow;
    }

    public static boolean shouldSuppressHomeForFTUDeeplink() {
        return sShouldSuppressHomeForFTUDeeplink;
    }

    public static void setSuppressHomeForFTUDeeplink(final boolean shouldSuppress) {
        sShouldSuppressHomeForFTUDeeplink = shouldSuppress;
    }

    public static boolean isUserFTUByBusinessDefinition(final Context context) {
        return ((context instanceof FTUActivity) || (context instanceof PrimeUpsellActivity) || AccountCredentialUtil.get(context).isSignedOut() || AutomotiveServiceUtil.INSTANCE.isAutomotiveServiceRunning() || !UserSubscriptionUtil.getUserSubscription().isInSubscriptionMarketplace() || !SettingsUtil.getPrefs(context).getString(getFTUShownOnVersionKey(context), "").isEmpty() || !ConnectivityUtil.hasAnyInternetConnection()) ? false : true;
    }

    private static String getFTUShownOnVersionKey(Context context) {
        return Marketplace.USA.equals(AccountDetailUtil.get(context).getHomeMarketPlace()) ? "KEY_FTU_SHOWN_ON_VERSION_TEMP_US" : "KEY_FTU_SHOWN_ON_VERSION";
    }

    public static void setFTUShown(Context context) {
        String appVersion = getAppVersion(context);
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.putString(getFTUShownOnVersionKey(context), appVersion);
        editorEdit.apply();
    }

    public static void setHasNoRecentsHistory(final Context context, final boolean hasNoHistory) {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.putString("KEY_RECENTS_HISTORY_CHECKED_ON_VERSION", getAppVersion(context));
        editorEdit.putBoolean("KEY_HAS_NO_RECENTS_HISTORY", hasNoHistory);
        editorEdit.apply();
    }

    public static boolean hasNoRecentsHistory(final Context context) {
        String string = SettingsUtil.getPrefs(context).getString("KEY_RECENTS_HISTORY_CHECKED_ON_VERSION", "");
        if (string.isEmpty() || !string.equals(getAppVersion(context))) {
            return false;
        }
        return SettingsUtil.getPrefs(context).getBoolean("KEY_HAS_NO_RECENTS_HISTORY", false);
    }

    public static String getAppVersion(Context context) {
        try {
            UpdateCheck.Version version = UpdateCheck.Version.parse(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName);
            return String.format(Locale.US, "%d.%d", Integer.valueOf(version.getMajor()), Integer.valueOf(version.getMinor()));
        } catch (PackageManager.NameNotFoundException unused) {
            return SettingsUtil.getPrefs(context).getString(getFTUShownOnVersionKey(context), "");
        }
    }

    public static void clearFTUSettings(Context context) {
        sCustomerCompletedATC = false;
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.remove("KEY_FTU_HAS_CLOUD_TRACKS");
        editorEdit.remove("KEY_FTU_HAS_DEVICE_TRACKS");
        editorEdit.remove("KEY_FTU_SHOWN_ON_VERSION");
        editorEdit.remove("KEY_FTU_SHOWN_ON_VERSION_TEMP_US");
        editorEdit.remove("KEY_FTU_IS_UPGRADE_CUSTOMER");
        editorEdit.remove("KEY_HAS_NO_RECENTS_HISTORY");
        editorEdit.remove("KEY_RECENTS_HISTORY_CHECKED_ON_VERSION");
        editorEdit.remove("KEY_HAS_COMPLETED_ARTIST_TASTE_COLLECTION");
        editorEdit.remove("KEY_HAS_SKIPPED_ARTIST_TASTE_COLLECTION");
        editorEdit.remove("find_landing_tooltip_experience");
        editorEdit.apply();
    }

    public static void resetFTUShown(Context context) {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.remove(getFTUShownOnVersionKey(context));
        editorEdit.apply();
    }

    public static void setUpgradeCustomer(Context context, boolean isUpgrade) {
        SharedPreferences prefs = SettingsUtil.getPrefs(context);
        if (prefs.contains("KEY_FTU_IS_UPGRADE_CUSTOMER")) {
            return;
        }
        SharedPreferences.Editor editorEdit = prefs.edit();
        editorEdit.putBoolean("KEY_FTU_IS_UPGRADE_CUSTOMER", isUpgrade);
        editorEdit.apply();
    }

    public static boolean customerCompletedATCThisSession() {
        return sCustomerCompletedATC;
    }

    public static boolean customerCompletedATC(final Context context) {
        return SettingsUtil.getPrefs(context).getBoolean("KEY_HAS_COMPLETED_ARTIST_TASTE_COLLECTION", false);
    }

    public static void setCustomerCompletedATC(final Context context, final boolean customerCompletedATC) {
        sCustomerCompletedATC = customerCompletedATC;
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.putBoolean("KEY_HAS_COMPLETED_ARTIST_TASTE_COLLECTION", customerCompletedATC);
        editorEdit.apply();
    }

    public static void setCustomerSkippedATC(final Context context, final boolean customerSkippedATC) {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
        editorEdit.putBoolean("KEY_HAS_SKIPPED_ARTIST_TASTE_COLLECTION", customerSkippedATC);
        editorEdit.apply();
    }

    public static boolean customerSkippedATC(final Context context) {
        return SettingsUtil.getPrefs(context).getBoolean("KEY_HAS_SKIPPED_ARTIST_TASTE_COLLECTION", false);
    }

    public static boolean searchTooltipOnATCSkipShown(final Context context) {
        return SettingsUtil.getPrefs(context).getBoolean("find_landing_tooltip_experience", false);
    }
}
