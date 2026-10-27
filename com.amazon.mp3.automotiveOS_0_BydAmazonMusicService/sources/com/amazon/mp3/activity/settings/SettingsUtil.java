package com.amazon.mp3.activity.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.details.AccountDetailStorage;
import com.amazon.mp3.auto.carmode.CarModeConfigs;
import com.amazon.mp3.auto.carmode.CarModeShowFABPreferences;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.capability.Capabilities;
import com.amazon.mp3.download.DownloadQualityPreferences;
import com.amazon.mp3.download.service.AutoDownloadPurchaseReceiver;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabaseUtil;
import com.amazon.mp3.library.service.sync.SyncService;
import com.amazon.mp3.net.dmls.StreamingBitrate;
import com.amazon.mp3.playback.activity.StreamingNetworkPreferenceActivity;
import com.amazon.mp3.service.ClearCacheService;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.MetricsManager;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.push.PushMessagingClient;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class SettingsUtil {
    private static final String TAG = "SettingsUtil";
    private static SharedPreferences sSharedPreferences;

    public static void removeCustomerSpecificPreferences(final Context context) {
        SharedPreferences.Editor editorEdit = getPrefs(context).edit();
        editorEdit.remove(context.getString(R.string.setting_key_old_device_token));
        editorEdit.remove(context.getString(R.string.setting_key_old_account_username));
        editorEdit.remove(context.getString(R.string.setting_key_old_private_key));
        editorEdit.remove(context.getString(R.string.setting_key_old_account_access_token));
        editorEdit.remove(context.getString(R.string.setting_key_old_at_main_cookie_expires_at));
        editorEdit.remove(context.getString(R.string.setting_key_old_nonce_account_validation));
        editorEdit.remove(context.getString(R.string.setting_key_cirrus_download_pref));
        editorEdit.remove(context.getString(R.string.setting_key_greenpoint_endpoint_hack));
        editorEdit.remove(context.getString(R.string.setting_key_last_fetched_cirrus_track_count));
        editorEdit.remove(context.getString(R.string.setting_key_sync_force_incremental_update));
        editorEdit.remove(context.getString(R.string.setting_key_prime_cached_unfollow_luids_key));
        editorEdit.remove("voice.manager.locale.language");
        editorEdit.remove("voice.manager.locale.country");
        editorEdit.remove(context.getString(R.string.setting_key_wake_word_toggle));
        editorEdit.remove(context.getString(R.string.setting_key_sfa));
        editorEdit.remove("isAutoPlaySfaCustomer");
        editorEdit.remove("autoPlayDialogDismissed");
        editorEdit.remove(context.getString(R.string.setting_key_showcase_audio_quality_badge));
        editorEdit.apply();
    }

    public static Setting getClickedSetting(final Preference preference, final Context context) {
        String key = preference.getKey();
        if (context.getString(R.string.setting_key_clear_caches).equals(key)) {
            return Setting.CLEAR_CACHE;
        }
        if (context.getString(R.string.setting_key_resync).equals(key)) {
            return Setting.RESYNC;
        }
        if (context.getString(R.string.setting_key_sign_out).equals(key)) {
            return Setting.SIGN_OUT;
        }
        if (context.getString(R.string.setting_key_manage_subscription).equals(key)) {
            return Setting.MANAGE_SUBSCRIPTION;
        }
        if (context.getString(R.string.setting_key_manage_family).equals(key)) {
            return Setting.MANAGE_FAMILY;
        }
        if (context.getString(R.string.setting_key_cancel_membership).equals(key)) {
            return Setting.CANCEL_MEMBERSHIP;
        }
        if (context.getString(R.string.setting_key_upsell).equals(key)) {
            return Setting.UPSELL;
        }
        if (context.getString(R.string.setting_key_audio_streaming_network_pref).equals(key)) {
            return Setting.AUDIO_STREAMING_NETWORK;
        }
        if (context.getString(R.string.setting_key_video_streaming_network_pref).equals(key)) {
            return Setting.VIDEO_STREAMING_NETWORK;
        }
        if (context.getString(R.string.setting_key_multi_bitrate_streaming).equals(key)) {
            return Setting.STREAMING_PREFERENCE;
        }
        if (context.getString(R.string.setting_key_download_quality_pref).equals(key)) {
            return Setting.DOWNLOAD_QUALITY_PREFERENCE;
        }
        if (context.getString(R.string.setting_key_download_location_pref).equals(key)) {
            return Setting.DOWNLOAD_LOCATION;
        }
        if (context.getString(R.string.setting_key_download_greyed_songs).equals(key)) {
            return Setting.DOWNLOAD_MISSING_SDCARD_CONTENT;
        }
        if (context.getString(R.string.setting_key_remove_greyed_songs).equals(key)) {
            return Setting.REMOVE_MISSING_SDCARD_CONTENT;
        }
        if (context.getString(R.string.setting_key_contact_us).equals(key)) {
            return Setting.CONTACT_US;
        }
        if (context.getString(R.string.setting_key_legal_info).equals(key)) {
            return Setting.LEGAL_INFO;
        }
        if (context.getString(R.string.setting_key_legal_info_imprint).equals(key)) {
            return Setting.LEGAL_INFO_IMPRINT;
        }
        if (context.getString(R.string.setting_key_dev_extract_db_file).equals(key)) {
            return Setting.EXTRACT_DB_FILE;
        }
        if (context.getString(R.string.setting_key_get_help).equals(key)) {
            return Setting.GET_HELP;
        }
        if (context.getString(R.string.setting_key_clear_caches).equals(key)) {
            return Setting.CLEAR_CACHE;
        }
        if (context.getString(R.string.setting_key_dev_delete_drm_licenses).equals(key)) {
            return Setting.DELETE_DRM_LICENSES;
        }
        if (context.getString(R.string.setting_key_language_preference).equals(key)) {
            return Setting.LANGUAGE_PREFERENCE;
        }
        if (context.getString(R.string.setting_key_equalizer).equals(key)) {
            return Setting.EQUALIZER;
        }
        if (context.getString(R.string.setting_key_dev_widget_viewer).equals(key)) {
            return Setting.WIDGET_VIEWER;
        }
        if (context.getString(R.string.setting_key_push_topic).equals(key)) {
            return Setting.PUSH_TOPIC;
        }
        if (context.getString(R.string.setting_car_mode_ingress_fab).equals(key)) {
            return Setting.CAR_MODE_FAB;
        }
        if (context.getString(R.string.setting_pref_key_spatial_mode).equals(key)) {
            return Setting.SPATIAL_MODE;
        }
        if (context.getString(R.string.setting_pref_key_prioritize_downloads).equals(key)) {
            return Setting.PRIORITIZE_DOWNLOADS;
        }
        if (context.getString(R.string.setting_app_display_language).equals(key)) {
            return Setting.APP_DISPLAY_LANGUAGE;
        }
        if (context.getString(R.string.setting_key_dev_native_crash).equals(key)) {
            return Setting.NATIVE_CRASH;
        }
        if (context.getString(R.string.setting_key_manage_cookie_preferences).equals(key)) {
            return Setting.MANAGE_COOKIE_PREFERENCE;
        }
        return null;
    }

    public static Setting getClickedSetting(final androidx.preference.Preference preference, final Context context) {
        String key = preference.getKey();
        if (context.getString(R.string.setting_key_legal_terms_of_use).equals(key)) {
            return Setting.TERMS_OF_USE;
        }
        if (context.getString(R.string.setting_key_legal_alexa_device_terms).equals(key)) {
            return Setting.ALEXA_DEVICE_TERMS;
        }
        if (context.getString(R.string.setting_key_legal_privacy_notes).equals(key)) {
            return Setting.PRIVACY_NOTES;
        }
        if (context.getString(R.string.setting_key_legal_ad_privacy_choices).equals(key)) {
            return Setting.AD_PRIVACY_CHOICES;
        }
        if (context.getString(R.string.setting_key_legal_children_privacy_notice).equals(key)) {
            return Setting.CHILDREN_PRIVACY_NOTICE;
        }
        if (context.getString(R.string.setting_key_legal_attribution_notices).equals(key)) {
            return Setting.ATTRIBUTION_NOTICES;
        }
        if (context.getString(R.string.setting_key_legal_account_deletion).equals(key)) {
            return Setting.ACCOUNT_DELETION;
        }
        return null;
    }

    public static String getCloudPlayerUrl(Context context) {
        String host;
        if (AccountCredentialUtil.get(context).isSignedOut()) {
            host = "www.amazon.com";
        } else if (!AccountDetailStorage.get(context).getCloudPlayerAccountExist()) {
            host = Environment.STORE.get().toHost();
        } else {
            host = Environment.CLOUD.get().toHost();
        }
        return host + "/cloudplayer";
    }

    public static void setLastAutoDownloadDateToLastPurchaseDateIfEnabled(final Context context) {
        if (getPrefs(context).getBoolean(context.getString(R.string.setting_key_cirrus_download_pref), false)) {
            new Thread(new Runnable() { // from class: com.amazon.mp3.activity.settings.SettingsUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    SettingsUtil.setLastAutoDownloadDateToLastPurchaseDate(context);
                }
            }, "Set last download date thread").start();
        }
    }

    public static void setLastAutoDownloadDateToLastPurchaseDate(Context c) {
        setLastAutoDownloadDate(c, CirrusDatabaseUtil.getMostRecentDate(c, "date_purchased", "cirrus"));
    }

    public static void setCirrusDownloadPreference(Context context, boolean enabled) {
        SharedPreferences.Editor editorEdit = getPrefs(context).edit();
        editorEdit.putBoolean(context.getString(R.string.setting_key_cirrus_download_pref), enabled);
        editorEdit.apply();
    }

    public static boolean getCirrusDownloadPreference(Context context) {
        return getPrefs(context).getBoolean(context.getString(R.string.setting_key_cirrus_download_pref), false);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.activity.settings.SettingsUtil$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$playback$activity$StreamingNetworkPreferenceActivity$Type;

        static {
            int[] iArr = new int[StreamingNetworkPreferenceActivity.Type.values().length];
            $SwitchMap$com$amazon$mp3$playback$activity$StreamingNetworkPreferenceActivity$Type = iArr;
            try {
                iArr[StreamingNetworkPreferenceActivity.Type.STREAMING_NETWORK_PREFERENCE_TYPE_AUDIO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$playback$activity$StreamingNetworkPreferenceActivity$Type[StreamingNetworkPreferenceActivity.Type.STREAMING_NETWORK_PREFERENCE_TYPE_VIDEO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static void setStreamingNetworkPreference(final Context context, final String streamingNetwork, final StreamingNetworkPreferenceActivity.Type type) {
        SharedPreferences.Editor editorEdit = getPrefs(context).edit();
        int i = AnonymousClass3.$SwitchMap$com$amazon$mp3$playback$activity$StreamingNetworkPreferenceActivity$Type[type.ordinal()];
        if (i == 1) {
            editorEdit.putString(context.getString(R.string.setting_key_audio_streaming_network_pref), streamingNetwork);
            editorEdit.apply();
            Playback.getInstance().getPlaybackConfig().setStreamOnWifiOnly("WIFI_ONLY".equals(getStreamingNetworkPreference(context, type)));
            return;
        }
        if (i == 2) {
            editorEdit.putString(context.getString(R.string.setting_key_video_streaming_network_pref), streamingNetwork);
            editorEdit.apply();
            return;
        }
        Log.error(TAG, "Wrong type passed to setStreamingNetworkPreference! type: " + type);
    }

    public static void sendStreamingNetworkPreferenceMetrics(final String streamingNetwork, final StreamingNetworkPreferenceActivity.Type type) {
        ActionType actionType;
        if (AnonymousClass3.$SwitchMap$com$amazon$mp3$playback$activity$StreamingNetworkPreferenceActivity$Type[type.ordinal()] != 2) {
            return;
        }
        if (streamingNetwork.equals("ALL_NETWORKS")) {
            actionType = ActionType.VIDEO_WIFI_ONLY_OFF;
        } else {
            actionType = ActionType.VIDEO_WIFI_ONLY_ON;
        }
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withPageType(PageType.SETTINGS).withEventTime(System.currentTimeMillis()).build());
    }

    public static String getStreamingNetworkPreference(Context context, final StreamingNetworkPreferenceActivity.Type type) {
        String string;
        SharedPreferences prefs = getPrefs(context);
        int i = AnonymousClass3.$SwitchMap$com$amazon$mp3$playback$activity$StreamingNetworkPreferenceActivity$Type[type.ordinal()];
        if (i == 1) {
            string = context.getString(R.string.setting_key_audio_streaming_network_pref);
        } else if (i == 2) {
            string = context.getString(R.string.setting_key_video_streaming_network_pref);
        } else {
            Log.error(TAG, "Invalid type passed to getStreamingNetworkPreference, type: " + type);
            return "";
        }
        return prefs.getString(string, "ALL_NETWORKS");
    }

    public static void setLastAutoDownloadDate(Context context, long date) {
        SharedPreferences.Editor editorEdit = getPrefs(context).edit();
        editorEdit.putLong(context.getString(R.string.setting_key_auto_download_last_date), date);
        editorEdit.apply();
    }

    public static void clearCache(final Context context) {
        new Thread("Clear Cache") { // from class: com.amazon.mp3.activity.settings.SettingsUtil.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Log.info(SettingsUtil.TAG, "clearCache: Starting clear cache job (on the current thread).");
                ClearCacheService.clearCacheOnCurrentThread(context, 134413);
                SyncService.startSync(context, 13995);
            }
        }.start();
        setBooleanPref(R.string.setting_key_cirrus_download_pref, false);
        AutoDownloadPurchaseReceiver.setComponentEnabled(context, false);
        setLastAutoDownloadDate(context, -1L);
        setCirrusDownloadPreference(context, false);
    }

    @Deprecated
    public static boolean getBooleanPref(int settingKeyId) {
        return getBooleanPref(AmazonApplication.getContext(), settingKeyId);
    }

    public static boolean getBooleanPref(final Context context, int settingKeyId) {
        return getPrefs(context).getBoolean(context.getString(settingKeyId), false);
    }

    @Deprecated
    public static void setBooleanPref(int settingKeyId, boolean value) {
        setBooleanPref(AmazonApplication.getContext(), settingKeyId, value);
    }

    public static void setBooleanPref(final Context context, int settingKeyId, boolean value) {
        SharedPreferences.Editor editorEdit = getPrefs(context).edit();
        editorEdit.putBoolean(context.getString(settingKeyId), value);
        editorEdit.apply();
    }

    public static String getStringPref(final Context context, final int settingKeyId) {
        return getPrefs(context).getString(context.getString(settingKeyId), "");
    }

    public static synchronized long getLastLocalUpdatedTime(Context context) {
        return getPrefs(context).getLong(context.getString(R.string.setting_key_sync_last_local_updated_time), -1L);
    }

    public static synchronized void setLastLocalUpdatedTime(Context context, long time) {
        SharedPreferences.Editor editorEdit = getPrefs(context).edit();
        editorEdit.putLong(context.getString(R.string.setting_key_sync_last_local_updated_time), time);
        editorEdit.apply();
    }

    public static synchronized boolean shouldForceIncrementalUpdate(Context context) {
        return getPrefs(context).getBoolean(context.getString(R.string.setting_key_sync_force_incremental_update), false);
    }

    public static synchronized void setShouldForceIncrementalUpdate(Context context, boolean force) {
        SharedPreferences.Editor editorEdit = getPrefs(context).edit();
        editorEdit.putBoolean(context.getString(R.string.setting_key_sync_force_incremental_update), force);
        editorEdit.apply();
    }

    public static class EnvironmentPref {
        public static String getEnvironment(Context context, int key, String defValue) {
            return SettingsUtil.getPrefs(context).getString(context.getString(key), defValue);
        }

        public static void removeEnvironment(Context context) {
            SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(context).edit();
            editorEdit.remove(context.getString(R.string.setting_key_dev_store_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_cirrus_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_webview_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_config_file_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_playlist_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_dmls_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_cta_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_search_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_stratus_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_nautilus_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_cirrus_v3_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_ftu_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_automotive_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_browse_recommendations_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_stations_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_muse_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_iam_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_dynmsg_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_explore_service_endpoint_override));
            editorEdit.remove(context.getString(R.string.setting_key_dev_activity_feed_service_endpoint_override));
            editorEdit.remove(context.getString(R.string.setting_key_dev_cantilever_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_curate_service_endpoint_override));
            editorEdit.remove(context.getString(R.string.setting_key_podcast_endpoint_override));
            editorEdit.remove(context.getString(R.string.setting_key_dev_chromecast_receiver_app_id_override));
            editorEdit.remove(context.getString(R.string.setting_key_dev_cloudqueue_auth_endpoint));
            editorEdit.remove(context.getString(R.string.setting_key_dev_native_crash));
            editorEdit.apply();
        }
    }

    public static SharedPreferences getPrefs(Context context) {
        if (sSharedPreferences == null) {
            setSharedPreferences(context);
        }
        return sSharedPreferences;
    }

    private static synchronized void setSharedPreferences(Context context) {
        if (sSharedPreferences == null && context != null) {
            sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        }
    }

    public static void setHasTracksDevice(Context context, boolean hasTracks) {
        Log.info(TAG, "setHasTracksDevice: %s", Boolean.valueOf(hasTracks));
        SharedPreferences.Editor editorEdit = getPrefs(context).edit();
        editorEdit.putBoolean(context.getString(R.string.setting_key_have_tracks_device), hasTracks);
        editorEdit.apply();
    }

    public static void setHasOfflinePrimePlaylists(Context context, boolean hasPrimePlaylists) {
        Log.info(TAG, "setHasOfflinePrimePlaylists: %s", Boolean.valueOf(hasPrimePlaylists));
        SharedPreferences.Editor editorEdit = getPrefs(context).edit();
        editorEdit.putBoolean(context.getString(R.string.setting_key_have_offline_prime_playlists), hasPrimePlaylists);
        editorEdit.apply();
    }

    public static void setHasFollowedPrimePlaylists(Context context, boolean hasPrimePlaylists) {
        Log.info(TAG, "setHasFollowedPrimePlaylists: %s", Boolean.valueOf(hasPrimePlaylists));
        SharedPreferences.Editor editorEdit = getPrefs(context).edit();
        editorEdit.putBoolean(context.getString(R.string.setting_key_have_followed_prime_playlists), hasPrimePlaylists);
        editorEdit.apply();
    }

    public static boolean hasRenormalized(Context context) {
        return getPrefs(context).getBoolean(context.getString(R.string.setting_key_has_renormalized_local_tracks), false);
    }

    public static void setHasRenormalized(Context context, boolean value) {
        SharedPreferences.Editor editorEdit = getPrefs(context).edit();
        editorEdit.putBoolean(context.getString(R.string.setting_key_has_renormalized_local_tracks), value);
        editorEdit.apply();
    }

    public static void setDownloadQualityPreference(Context context, String downloadPreference) {
        SharedPreferences.Editor editorEdit = getPrefs(context).edit();
        editorEdit.putString(context.getString(R.string.setting_key_download_quality_pref), downloadPreference);
        editorEdit.apply();
    }

    public static String getDownloadQualityPreference(Context context) {
        return downgradeDownloadQualityPreferenceIfRequired(context, PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.setting_key_download_quality_pref), DownloadQualityPreferences.HIGH.getEntryValue()));
    }

    public static String getShowCarModeFABPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.setting_car_mode_ingress_fab), CarModeShowFABPreferences.IN_CAR.getEntryValue());
    }

    public static void setShowCarModeFABPreference(Context context, String showCarModeFABPreference) {
        SharedPreferences.Editor editorEdit = getPrefs(context).edit();
        editorEdit.putString(context.getString(R.string.setting_car_mode_ingress_fab), showCarModeFABPreference);
        editorEdit.apply();
    }

    public static Boolean getCarModeKeepScreenActivePreference(Context context) {
        return Boolean.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.setting_car_mode_keep_screen_active), true));
    }

    public static Boolean getCarModeLaunchAutomaticallyPreference(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (CarModeConfigs.isCarModeAutoLaunchAllowed()) {
            return Boolean.valueOf(defaultSharedPreferences.getBoolean(context.getString(R.string.setting_car_mode_launch_automatically), true));
        }
        return Boolean.FALSE;
    }

    public static String downgradeDownloadQualityPreferenceIfRequired(Context context, final String currentDownloadQualityPreference) {
        if ((!currentDownloadQualityPreference.equals(DownloadQualityPreferences.AUTO.getEntryValue()) && !currentDownloadQualityPreference.equals(DownloadQualityPreferences.HD.getEntryValue())) || UserSubscriptionUtil.getUserSubscription().isKatana()) {
            return currentDownloadQualityPreference;
        }
        DownloadQualityPreferences downloadQualityPreferences = DownloadQualityPreferences.HIGH;
        setDownloadQualityPreference(context, downloadQualityPreferences.getEntryValue());
        return downloadQualityPreferences.getEntryValue();
    }

    public static void setMultiBitrateStreamingPreferenceForOTA(Context context, String streamingPreference) {
        SharedPreferences.Editor editorEdit = getPrefs(context).edit();
        editorEdit.putString(context.getString(R.string.setting_key_multi_bitrate_streaming), streamingPreference);
        editorEdit.apply();
    }

    public static void setMultiBitrateStreamingPreferenceForWifi(Context context, String streamingPreference) {
        SharedPreferences.Editor editorEdit = getPrefs(context).edit();
        editorEdit.putString(context.getString(R.string.setting_key_multi_bitrate_streaming_for_wifi), streamingPreference);
        editorEdit.apply();
    }

    public static String getMultiBitrateStreamingPreferenceForWifi(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.setting_key_multi_bitrate_streaming_for_wifi), StreamingBitrate.HIGH.getEntryValue());
    }

    public static String getMultiBitrateStreamingPreferenceForOTA(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.setting_key_multi_bitrate_streaming), StreamingBitrate.HIGH.getEntryValue());
    }

    public static String getMultiBitrateStreamingPreferenceForDMLS(Context context) {
        return StreamingBitrate.fromEntryValueForDMLS(PreferenceManager.getDefaultSharedPreferences(context).getString(context.getString(R.string.setting_key_multi_bitrate_streaming), StreamingBitrate.AUTO.getEntryValue())).getEntryValue();
    }

    public static boolean isCustomEndpointEnabled(Context context) {
        return getPrefs(context).getBoolean(context.getString(R.string.setting_key_dev_custom_endpoint_enabled), false);
    }

    public static void setSleepTimerEnabled(Context context, boolean enabled) {
        SharedPreferences.Editor editorEdit = getPrefs(context).edit();
        editorEdit.putBoolean(context.getString(R.string.setting_key_sleep_timer), enabled);
        editorEdit.apply();
    }

    public static boolean isSleepTimerEnabled(Context context) {
        return getPrefs(context).getBoolean(context.getString(R.string.setting_key_sleep_timer), false);
    }

    public static boolean hasUsedExplicitLanguageFilter(Context context) {
        return getPrefs(context).getBoolean(context.getString(R.string.setting_key_has_used_parental_control_explicit_language), false);
    }

    public static void setHasUsedExplicitLanguageFilter(Context context) {
        SharedPreferences.Editor editorEdit = getPrefs(context).edit();
        editorEdit.putBoolean(context.getString(R.string.setting_key_has_used_parental_control_explicit_language), true);
        editorEdit.apply();
    }

    public static boolean hasCompletedWidevineMigration(Context context) {
        return getBooleanPref(context, R.string.setting_key_completed_widevine_migration);
    }

    public static void setHasCompletedWidevineMigration(final Context context, boolean b) {
        Log.info(TAG, "Setting Has Completed Widevine Migration to: %b", Boolean.valueOf(b));
        setBooleanPref(context, R.string.setting_key_completed_widevine_migration, b);
    }

    public static boolean hasCompletedWidevineDatabaseUpdate(Context context) {
        return getBooleanPref(context, R.string.setting_key_completed_widevine_database_update);
    }

    public static void setHasCompletedWidevineDatabaseUpdate(final Context context, boolean b) {
        Log.info(TAG, "Setting HasCompletedWidevineDatabaseUpdate to: %b", Boolean.valueOf(b));
        setBooleanPref(context, R.string.setting_key_completed_widevine_database_update, b);
    }

    public static boolean shouldShowRedownloadUIUntilAcknowledged(final Context context) {
        return getBooleanPref(context, R.string.setting_key_should_show_redownload_ui);
    }

    public static void setShouldShowRedownloadUIUntilAcknowledged(final Context context, boolean b) {
        Log.info(TAG, "Setting setShouldShowRedownloadUIUntilAcknowledged to: %b", Boolean.valueOf(b));
        setBooleanPref(context, R.string.setting_key_should_show_redownload_ui, b);
    }

    public static void updateSettings(Context context) {
        String string = context.getString(R.string.setting_key_settings_schema_version);
        SharedPreferences prefs = getPrefs(context);
        int i = prefs.getInt(string, 1);
        if (i == 2) {
            return;
        }
        if (i == 1) {
            SharedPreferences.Editor editorEdit = prefs.edit();
            editorEdit.putBoolean(context.getString(R.string.setting_key_downloads_wifi_only_pref), true);
            editorEdit.apply();
        }
        SharedPreferences.Editor editorEdit2 = prefs.edit();
        editorEdit2.putInt(string, 2);
        editorEdit2.apply();
        Log.info(TAG, "Schema update successful. Updated schema version %d -> %d", Integer.valueOf(i), 2);
    }

    public static void enableWakeWordUserSetting(final Context context, final boolean enabled) {
        SharedPreferences.Editor editorEdit = getPrefs(context).edit();
        editorEdit.putBoolean(context.getString(R.string.setting_key_wake_word_toggle), enabled);
        editorEdit.apply();
    }

    public static boolean isWakeWordUserSettingEnabled(final Context context) {
        return getPrefs(context).getBoolean(context.getString(R.string.setting_key_wake_word_toggle), false);
    }

    public static boolean isSfaSettingEnabled(final Context context) {
        return getPrefs(context).getBoolean(context.getString(R.string.setting_key_sfa), true);
    }

    public static void setSfa(final Context context, boolean value) {
        setBooleanPref(context, R.string.setting_key_sfa, value);
    }

    public static boolean isSpatialModeSettingEnabled(final Context context) {
        Capabilities capabilities = AmazonApplication.getCapabilities();
        SharedPreferences prefs = getPrefs(context);
        if (capabilities.isSpatialAudioEnabled(context)) {
            return prefs.getBoolean(context.getString(R.string.setting_pref_key_spatial_mode), true);
        }
        setSpatialMode(context, false);
        return prefs.getBoolean(context.getString(R.string.setting_pref_key_spatial_mode), false);
    }

    public static boolean isPrioritizeDownloadsEnabled(final Context context) {
        return getPrefs(context).getBoolean(context.getString(R.string.setting_pref_key_prioritize_downloads), false);
    }

    public static void setPrioritizeDownloadsPreference(final Context context, boolean value) {
        setBooleanPref(context, R.string.setting_pref_key_prioritize_downloads, value);
    }

    public static void setSpatialMode(final Context context, boolean value) {
        setBooleanPref(context, R.string.setting_pref_key_spatial_mode, value);
    }

    public static boolean isLoudnessNormalizationEnabled(final Context context) {
        return getPrefs(context).getBoolean(context.getString(R.string.setting_key_is_loudness_normalization_enabled), true);
    }

    public static boolean hasFetchedReferrerInformation(Context context) {
        return getBooleanPref(context, R.string.setting_key_fetch_referrer_information);
    }

    public static void setHasFetchedReferrerInformation(final Context context, boolean value) {
        Log.info(TAG, "Setting Has fetched referrer information to: %b", Boolean.valueOf(value));
        setBooleanPref(context, R.string.setting_key_fetch_referrer_information, value);
    }

    public static void setNotificationSettings(final Context context, final Map<String, Boolean> pushTopicOptInMap) {
        if (pushTopicOptInMap.containsValue(Boolean.FALSE)) {
            sendPushSaveEvent(ActionType.SELECT_NOTIFICATION_PREFERENCE_OFF);
        } else {
            sendPushSaveEvent(ActionType.SELECT_NOTIFICATION_PREFERENCE_ON);
        }
        new PushMessagingClient().setNotificationPreferenceInPFE(context, pushTopicOptInMap);
    }

    public static Map<String, Boolean> getNotificationSettings(final Context context) {
        return new PushMessagingClient().getNotificationPreference(context, Boolean.TRUE);
    }

    public static void sendPushSaveEvent(final ActionType actionType) {
        MetricsManager manager = MetricsHolder.getManager();
        if (manager != null) {
            manager.handleEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).build());
        }
    }
}
