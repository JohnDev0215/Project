package android.provider;

import android.Manifest;
import android.annotation.SystemApi;
import android.app.ActivityThread;
import android.app.AppOpsManager;
import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.IContentProvider;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.media.AudioSystem;
import android.media.MediaMetrics;
import android.net.Uri;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Process;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.DeviceConfig;
import android.text.TextUtils;
import android.util.AndroidException;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.MemoryIntArray;
import com.android.internal.util.Preconditions;
import com.android.internal.widget.ILockSettings;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class Settings {

    @SystemApi
    public static final String ACTION_ACCESSIBILITY_DETAILS_SETTINGS = "android.settings.ACCESSIBILITY_DETAILS_SETTINGS";
    public static final String ACTION_ACCESSIBILITY_SETTINGS = "android.settings.ACCESSIBILITY_SETTINGS";
    public static final String ACTION_ADD_ACCOUNT = "android.settings.ADD_ACCOUNT_SETTINGS";
    public static final String ACTION_AIRPLANE_MODE_SETTINGS = "android.settings.AIRPLANE_MODE_SETTINGS";
    public static final String ACTION_ALL_APPS_NOTIFICATION_SETTINGS = "android.settings.ALL_APPS_NOTIFICATION_SETTINGS";
    public static final String ACTION_APN_SETTINGS = "android.settings.APN_SETTINGS";
    public static final String ACTION_APPLICATION_DETAILS_SETTINGS = "android.settings.APPLICATION_DETAILS_SETTINGS";
    public static final String ACTION_APPLICATION_DEVELOPMENT_SETTINGS = "android.settings.APPLICATION_DEVELOPMENT_SETTINGS";
    public static final String ACTION_APPLICATION_SETTINGS = "android.settings.APPLICATION_SETTINGS";
    public static final String ACTION_APP_NOTIFICATION_BUBBLE_SETTINGS = "android.settings.APP_NOTIFICATION_BUBBLE_SETTINGS";
    public static final String ACTION_APP_NOTIFICATION_REDACTION = "android.settings.ACTION_APP_NOTIFICATION_REDACTION";
    public static final String ACTION_APP_NOTIFICATION_SETTINGS = "android.settings.APP_NOTIFICATION_SETTINGS";
    public static final String ACTION_APP_OPEN_BY_DEFAULT_SETTINGS = "com.android.settings.APP_OPEN_BY_DEFAULT_SETTINGS";
    public static final String ACTION_APP_OPS_SETTINGS = "android.settings.APP_OPS_SETTINGS";
    public static final String ACTION_APP_SEARCH_SETTINGS = "android.settings.APP_SEARCH_SETTINGS";
    public static final String ACTION_APP_USAGE_SETTINGS = "android.settings.action.APP_USAGE_SETTINGS";
    public static final String ACTION_ASSIST_GESTURE_SETTINGS = "android.settings.ASSIST_GESTURE_SETTINGS";
    public static final String ACTION_BATTERY_SAVER_SETTINGS = "android.settings.BATTERY_SAVER_SETTINGS";
    public static final String ACTION_BIOMETRIC_ENROLL = "android.settings.BIOMETRIC_ENROLL";
    public static final String ACTION_BLUETOOTH_SETTINGS = "android.settings.BLUETOOTH_SETTINGS";

    @SystemApi
    public static final String ACTION_BUGREPORT_HANDLER_SETTINGS = "android.settings.BUGREPORT_HANDLER_SETTINGS";
    public static final String ACTION_CAPTIONING_SETTINGS = "android.settings.CAPTIONING_SETTINGS";
    public static final String ACTION_CAST_SETTINGS = "android.settings.CAST_SETTINGS";
    public static final String ACTION_CHANNEL_NOTIFICATION_SETTINGS = "android.settings.CHANNEL_NOTIFICATION_SETTINGS";
    public static final String ACTION_CONDITION_PROVIDER_SETTINGS = "android.settings.ACTION_CONDITION_PROVIDER_SETTINGS";
    public static final String ACTION_CONVERSATION_SETTINGS = "android.settings.CONVERSATION_SETTINGS";
    public static final String ACTION_DARK_THEME_SETTINGS = "android.settings.DARK_THEME_SETTINGS";
    public static final String ACTION_DATA_ROAMING_SETTINGS = "android.settings.DATA_ROAMING_SETTINGS";
    public static final String ACTION_DATA_SAVER_SETTINGS = "android.settings.DATA_SAVER_SETTINGS";
    public static final String ACTION_DATA_USAGE_SETTINGS = "android.settings.DATA_USAGE_SETTINGS";
    public static final String ACTION_DATE_SETTINGS = "android.settings.DATE_SETTINGS";
    public static final String ACTION_DEVICE_CONTROLS_SETTINGS = "android.settings.ACTION_DEVICE_CONTROLS_SETTINGS";
    public static final String ACTION_DEVICE_INFO_SETTINGS = "android.settings.DEVICE_INFO_SETTINGS";
    public static final String ACTION_DISPLAY_SETTINGS = "android.settings.DISPLAY_SETTINGS";
    public static final String ACTION_DREAM_SETTINGS = "android.settings.DREAM_SETTINGS";
    public static final String ACTION_ENABLE_MMS_DATA_REQUEST = "android.settings.ENABLE_MMS_DATA_REQUEST";

    @SystemApi
    public static final String ACTION_ENTERPRISE_PRIVACY_SETTINGS = "android.settings.ENTERPRISE_PRIVACY_SETTINGS";

    @Deprecated
    public static final String ACTION_FINGERPRINT_ENROLL = "android.settings.FINGERPRINT_ENROLL";
    public static final String ACTION_FOREGROUND_SERVICES_SETTINGS = "android.settings.FOREGROUND_SERVICES_SETTINGS";
    public static final String ACTION_HARD_KEYBOARD_SETTINGS = "android.settings.HARD_KEYBOARD_SETTINGS";
    public static final String ACTION_HOME_SETTINGS = "android.settings.HOME_SETTINGS";
    public static final String ACTION_IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS = "android.settings.IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS";
    public static final String ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS = "android.settings.IGNORE_BATTERY_OPTIMIZATION_SETTINGS";
    public static final String ACTION_INPUT_METHOD_SETTINGS = "android.settings.INPUT_METHOD_SETTINGS";
    public static final String ACTION_INPUT_METHOD_SUBTYPE_SETTINGS = "android.settings.INPUT_METHOD_SUBTYPE_SETTINGS";
    public static final String ACTION_INTERNAL_STORAGE_SETTINGS = "android.settings.INTERNAL_STORAGE_SETTINGS";
    public static final String ACTION_LOCALE_SETTINGS = "android.settings.LOCALE_SETTINGS";

    @SystemApi
    public static final String ACTION_LOCATION_CONTROLLER_EXTRA_PACKAGE_SETTINGS = "android.settings.LOCATION_CONTROLLER_EXTRA_PACKAGE_SETTINGS";
    public static final String ACTION_LOCATION_SCANNING_SETTINGS = "android.settings.LOCATION_SCANNING_SETTINGS";
    public static final String ACTION_LOCATION_SOURCE_SETTINGS = "android.settings.LOCATION_SOURCE_SETTINGS";
    public static final String ACTION_MANAGED_PROFILE_SETTINGS = "android.settings.MANAGED_PROFILE_SETTINGS";
    public static final String ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS = "android.settings.MANAGE_ALL_APPLICATIONS_SETTINGS";
    public static final String ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION = "android.settings.MANAGE_ALL_FILES_ACCESS_PERMISSION";
    public static final String ACTION_MANAGE_APPLICATIONS_SETTINGS = "android.settings.MANAGE_APPLICATIONS_SETTINGS";
    public static final String ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION = "android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION";

    @SystemApi
    public static final String ACTION_MANAGE_APP_OVERLAY_PERMISSION = "android.settings.MANAGE_APP_OVERLAY_PERMISSION";
    public static final String ACTION_MANAGE_CROSS_PROFILE_ACCESS = "android.settings.MANAGE_CROSS_PROFILE_ACCESS";
    public static final String ACTION_MANAGE_DEFAULT_APPS_SETTINGS = "android.settings.MANAGE_DEFAULT_APPS_SETTINGS";

    @SystemApi
    public static final String ACTION_MANAGE_DOMAIN_URLS = "android.settings.MANAGE_DOMAIN_URLS";

    @SystemApi
    public static final String ACTION_MANAGE_MORE_DEFAULT_APPS_SETTINGS = "android.settings.MANAGE_MORE_DEFAULT_APPS_SETTINGS";
    public static final String ACTION_MANAGE_OVERLAY_PERMISSION = "android.settings.action.MANAGE_OVERLAY_PERMISSION";
    public static final String ACTION_MANAGE_UNKNOWN_APP_SOURCES = "android.settings.MANAGE_UNKNOWN_APP_SOURCES";
    public static final String ACTION_MANAGE_WRITE_SETTINGS = "android.settings.action.MANAGE_WRITE_SETTINGS";
    public static final String ACTION_MEDIA_CONTROLS_SETTINGS = "android.settings.ACTION_MEDIA_CONTROLS_SETTINGS";
    public static final String ACTION_MEMORY_CARD_SETTINGS = "android.settings.MEMORY_CARD_SETTINGS";
    public static final String ACTION_MMS_MESSAGE_SETTING = "android.settings.MMS_MESSAGE_SETTING";
    public static final String ACTION_MOBILE_DATA_USAGE = "android.settings.MOBILE_DATA_USAGE";
    public static final String ACTION_MONITORING_CERT_INFO = "com.android.settings.MONITORING_CERT_INFO";
    public static final String ACTION_NETWORK_OPERATOR_SETTINGS = "android.settings.NETWORK_OPERATOR_SETTINGS";
    public static final String ACTION_NFCSHARING_SETTINGS = "android.settings.NFCSHARING_SETTINGS";
    public static final String ACTION_NFC_PAYMENT_SETTINGS = "android.settings.NFC_PAYMENT_SETTINGS";
    public static final String ACTION_NFC_SETTINGS = "android.settings.NFC_SETTINGS";
    public static final String ACTION_NIGHT_DISPLAY_SETTINGS = "android.settings.NIGHT_DISPLAY_SETTINGS";
    public static final String ACTION_NOTIFICATION_ASSISTANT_SETTINGS = "android.settings.NOTIFICATION_ASSISTANT_SETTINGS";
    public static final String ACTION_NOTIFICATION_HISTORY = "android.settings.NOTIFICATION_HISTORY";
    public static final String ACTION_NOTIFICATION_LISTENER_DETAIL_SETTINGS = "android.settings.NOTIFICATION_LISTENER_DETAIL_SETTINGS";
    public static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";

    @SystemApi
    public static final String ACTION_NOTIFICATION_POLICY_ACCESS_DETAIL_SETTINGS = "android.settings.NOTIFICATION_POLICY_ACCESS_DETAIL_SETTINGS";
    public static final String ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS = "android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS";
    public static final String ACTION_NOTIFICATION_SETTINGS = "android.settings.NOTIFICATION_SETTINGS";
    public static final String ACTION_PAIRING_SETTINGS = "android.settings.PAIRING_SETTINGS";
    public static final String ACTION_PICTURE_IN_PICTURE_SETTINGS = "android.settings.PICTURE_IN_PICTURE_SETTINGS";
    public static final String ACTION_POWER_MENU_SETTINGS = "android.settings.ACTION_POWER_MENU_SETTINGS";
    public static final String ACTION_PRINT_SETTINGS = "android.settings.ACTION_PRINT_SETTINGS";
    public static final String ACTION_PRIVACY_SETTINGS = "android.settings.PRIVACY_SETTINGS";
    public static final String ACTION_PROCESS_WIFI_EASY_CONNECT_URI = "android.settings.PROCESS_WIFI_EASY_CONNECT_URI";
    public static final String ACTION_QUICK_ACCESS_WALLET_SETTINGS = "android.settings.QUICK_ACCESS_WALLET_SETTINGS";
    public static final String ACTION_QUICK_LAUNCH_SETTINGS = "android.settings.QUICK_LAUNCH_SETTINGS";

    @SystemApi
    public static final String ACTION_REQUEST_ENABLE_CONTENT_CAPTURE = "android.settings.REQUEST_ENABLE_CONTENT_CAPTURE";
    public static final String ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS = "android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS";
    public static final String ACTION_REQUEST_SET_AUTOFILL_SERVICE = "android.settings.REQUEST_SET_AUTOFILL_SERVICE";
    public static final String ACTION_SEARCH_SETTINGS = "android.search.action.SEARCH_SETTINGS";
    public static final String ACTION_SECURITY_SETTINGS = "android.settings.SECURITY_SETTINGS";
    public static final String ACTION_SETTINGS = "android.settings.SETTINGS";

    @SystemApi
    public static final String ACTION_SHOW_ADMIN_SUPPORT_DETAILS = "android.settings.SHOW_ADMIN_SUPPORT_DETAILS";
    public static final String ACTION_SHOW_REGULATORY_INFO = "android.settings.SHOW_REGULATORY_INFO";
    public static final String ACTION_SHOW_REMOTE_BUGREPORT_DIALOG = "android.settings.SHOW_REMOTE_BUGREPORT_DIALOG";
    public static final String ACTION_SHOW_WORK_POLICY_INFO = "android.settings.SHOW_WORK_POLICY_INFO";
    public static final String ACTION_SOUND_SETTINGS = "android.settings.SOUND_SETTINGS";
    public static final String ACTION_STORAGE_MANAGER_SETTINGS = "android.settings.STORAGE_MANAGER_SETTINGS";

    @Deprecated
    public static final String ACTION_STORAGE_VOLUME_ACCESS_SETTINGS = "android.settings.STORAGE_VOLUME_ACCESS_SETTINGS";
    public static final String ACTION_SYNC_SETTINGS = "android.settings.SYNC_SETTINGS";
    public static final String ACTION_SYSTEM_UPDATE_SETTINGS = "android.settings.SYSTEM_UPDATE_SETTINGS";

    @SystemApi
    public static final String ACTION_TETHER_PROVISIONING_UI = "android.settings.TETHER_PROVISIONING_UI";

    @SystemApi
    public static final String ACTION_TETHER_SETTINGS = "android.settings.TETHER_SETTINGS";
    public static final String ACTION_TRUSTED_CREDENTIALS_USER = "com.android.settings.TRUSTED_CREDENTIALS_USER";
    public static final String ACTION_USAGE_ACCESS_SETTINGS = "android.settings.USAGE_ACCESS_SETTINGS";
    public static final String ACTION_USER_DICTIONARY_INSERT = "com.android.settings.USER_DICTIONARY_INSERT";
    public static final String ACTION_USER_DICTIONARY_SETTINGS = "android.settings.USER_DICTIONARY_SETTINGS";
    public static final String ACTION_USER_SETTINGS = "android.settings.USER_SETTINGS";
    public static final String ACTION_VIEW_ADVANCED_POWER_USAGE_DETAIL = "android.settings.VIEW_ADVANCED_POWER_USAGE_DETAIL";
    public static final String ACTION_VOICE_CONTROL_AIRPLANE_MODE = "android.settings.VOICE_CONTROL_AIRPLANE_MODE";
    public static final String ACTION_VOICE_CONTROL_BATTERY_SAVER_MODE = "android.settings.VOICE_CONTROL_BATTERY_SAVER_MODE";
    public static final String ACTION_VOICE_CONTROL_DO_NOT_DISTURB_MODE = "android.settings.VOICE_CONTROL_DO_NOT_DISTURB_MODE";
    public static final String ACTION_VOICE_INPUT_SETTINGS = "android.settings.VOICE_INPUT_SETTINGS";
    public static final String ACTION_VPN_SETTINGS = "android.settings.VPN_SETTINGS";
    public static final String ACTION_VR_LISTENER_SETTINGS = "android.settings.VR_LISTENER_SETTINGS";
    public static final String ACTION_WEBVIEW_SETTINGS = "android.settings.WEBVIEW_SETTINGS";
    public static final String ACTION_WIFI_ADD_NETWORKS = "android.settings.WIFI_ADD_NETWORKS";
    public static final String ACTION_WIFI_IP_SETTINGS = "android.settings.WIFI_IP_SETTINGS";
    public static final String ACTION_WIFI_SETTINGS = "android.settings.WIFI_SETTINGS";
    public static final String ACTION_WIRELESS_SETTINGS = "android.settings.WIRELESS_SETTINGS";
    public static final String ACTION_ZEN_MODE_AUTOMATION_SETTINGS = "android.settings.ZEN_MODE_AUTOMATION_SETTINGS";
    public static final String ACTION_ZEN_MODE_EVENT_RULE_SETTINGS = "android.settings.ZEN_MODE_EVENT_RULE_SETTINGS";
    public static final String ACTION_ZEN_MODE_EXTERNAL_RULE_SETTINGS = "android.settings.ZEN_MODE_EXTERNAL_RULE_SETTINGS";
    public static final String ACTION_ZEN_MODE_PRIORITY_SETTINGS = "android.settings.ZEN_MODE_PRIORITY_SETTINGS";
    public static final String ACTION_ZEN_MODE_SCHEDULE_RULE_SETTINGS = "android.settings.ZEN_MODE_SCHEDULE_RULE_SETTINGS";
    public static final String ACTION_ZEN_MODE_SETTINGS = "android.settings.ZEN_MODE_SETTINGS";
    public static final int ADD_WIFI_RESULT_ADD_OR_UPDATE_FAILED = 1;
    public static final int ADD_WIFI_RESULT_ALREADY_EXISTS = 2;
    public static final int ADD_WIFI_RESULT_SUCCESS = 0;
    public static final String AUTHORITY = "settings";
    public static final String CALL_METHOD_DELETE_CONFIG = "DELETE_config";
    public static final String CALL_METHOD_DELETE_GLOBAL = "DELETE_global";
    public static final String CALL_METHOD_DELETE_SECURE = "DELETE_secure";
    public static final String CALL_METHOD_DELETE_SYSTEM = "DELETE_system";
    public static final String CALL_METHOD_FLAGS_KEY = "_flags";
    public static final String CALL_METHOD_GENERATION_INDEX_KEY = "_generation_index";
    public static final String CALL_METHOD_GENERATION_KEY = "_generation";
    public static final String CALL_METHOD_GET_CONFIG = "GET_config";
    public static final String CALL_METHOD_GET_GLOBAL = "GET_global";
    public static final String CALL_METHOD_GET_SECURE = "GET_secure";
    public static final String CALL_METHOD_GET_SYSTEM = "GET_system";
    public static final String CALL_METHOD_LIST_CONFIG = "LIST_config";
    public static final String CALL_METHOD_LIST_GLOBAL = "LIST_global";
    public static final String CALL_METHOD_LIST_SECURE = "LIST_secure";
    public static final String CALL_METHOD_LIST_SYSTEM = "LIST_system";
    public static final String CALL_METHOD_MAKE_DEFAULT_KEY = "_make_default";
    public static final String CALL_METHOD_MONITOR_CALLBACK_KEY = "_monitor_callback_key";
    public static final String CALL_METHOD_OVERRIDEABLE_BY_RESTORE_KEY = "_overrideable_by_restore";
    public static final String CALL_METHOD_PREFIX_KEY = "_prefix";
    public static final String CALL_METHOD_PUT_CONFIG = "PUT_config";
    public static final String CALL_METHOD_PUT_GLOBAL = "PUT_global";
    public static final String CALL_METHOD_PUT_SECURE = "PUT_secure";
    public static final String CALL_METHOD_PUT_SYSTEM = "PUT_system";
    public static final String CALL_METHOD_REGISTER_MONITOR_CALLBACK_CONFIG = "REGISTER_MONITOR_CALLBACK_config";
    public static final String CALL_METHOD_RESET_CONFIG = "RESET_config";
    public static final String CALL_METHOD_RESET_GLOBAL = "RESET_global";
    public static final String CALL_METHOD_RESET_MODE_KEY = "_reset_mode";
    public static final String CALL_METHOD_RESET_SECURE = "RESET_secure";
    public static final String CALL_METHOD_SET_ALL_CONFIG = "SET_ALL_config";
    public static final String CALL_METHOD_TAG_KEY = "_tag";
    public static final String CALL_METHOD_TRACK_GENERATION_KEY = "_track_generation";
    public static final String CALL_METHOD_USER_KEY = "_user";
    public static final boolean DEFAULT_OVERRIDEABLE_BY_RESTORE = false;
    public static final String DEVICE_NAME_SETTINGS = "android.settings.DEVICE_NAME";
    public static final int ENABLE_MMS_DATA_REQUEST_REASON_INCOMING_MMS = 0;
    public static final int ENABLE_MMS_DATA_REQUEST_REASON_OUTGOING_MMS = 1;
    public static final String EXTRA_ACCESS_CALLBACK = "access_callback";
    public static final String EXTRA_ACCOUNT_TYPES = "account_types";
    public static final String EXTRA_AIRPLANE_MODE_ENABLED = "airplane_mode_enabled";
    public static final String EXTRA_APP_PACKAGE = "android.provider.extra.APP_PACKAGE";
    public static final String EXTRA_APP_UID = "app_uid";
    public static final String EXTRA_AUTHORITIES = "authorities";
    public static final String EXTRA_BATTERY_SAVER_MODE_ENABLED = "android.settings.extra.battery_saver_mode_enabled";
    public static final String EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED = "android.provider.extra.BIOMETRIC_AUTHENTICATORS_ALLOWED";
    public static final String EXTRA_CALLING_PACKAGE = "calling_package";
    public static final String EXTRA_CHANNEL_ID = "android.provider.extra.CHANNEL_ID";
    public static final String EXTRA_CONVERSATION_ID = "android.provider.extra.CONVERSATION_ID";
    public static final String EXTRA_DO_NOT_DISTURB_MODE_ENABLED = "android.settings.extra.do_not_disturb_mode_enabled";
    public static final String EXTRA_DO_NOT_DISTURB_MODE_MINUTES = "android.settings.extra.do_not_disturb_mode_minutes";
    public static final String EXTRA_EASY_CONNECT_ATTEMPTED_SSID = "android.provider.extra.EASY_CONNECT_ATTEMPTED_SSID";
    public static final String EXTRA_EASY_CONNECT_BAND_LIST = "android.provider.extra.EASY_CONNECT_BAND_LIST";
    public static final String EXTRA_EASY_CONNECT_CHANNEL_LIST = "android.provider.extra.EASY_CONNECT_CHANNEL_LIST";
    public static final String EXTRA_EASY_CONNECT_ERROR_CODE = "android.provider.extra.EASY_CONNECT_ERROR_CODE";
    public static final String EXTRA_ENABLE_MMS_DATA_REQUEST_REASON = "android.settings.extra.ENABLE_MMS_DATA_REQUEST_REASON";
    public static final String EXTRA_INPUT_DEVICE_IDENTIFIER = "input_device_identifier";
    public static final String EXTRA_INPUT_METHOD_ID = "input_method_id";
    public static final String EXTRA_MONITOR_CALLBACK_TYPE = "monitor_callback_type";
    public static final String EXTRA_NAMESPACE = "namespace";
    public static final String EXTRA_NAMESPACE_UPDATED_CALLBACK = "namespace_updated_callback";
    public static final String EXTRA_NETWORK_TEMPLATE = "network_template";
    public static final String EXTRA_NOTIFICATION_LISTENER_COMPONENT_NAME = "android.provider.extra.NOTIFICATION_LISTENER_COMPONENT_NAME";
    public static final String EXTRA_NUMBER_OF_CERTIFICATES = "android.settings.extra.number_of_certificates";
    public static final String EXTRA_SUB_ID = "android.provider.extra.SUB_ID";
    public static final String EXTRA_WIFI_NETWORK_LIST = "android.provider.extra.WIFI_NETWORK_LIST";
    public static final String EXTRA_WIFI_NETWORK_RESULT_LIST = "android.provider.extra.WIFI_NETWORK_RESULT_LIST";
    public static final String INTENT_CATEGORY_USAGE_ACCESS_CONFIG = "android.intent.category.USAGE_ACCESS_CONFIG";
    private static final String JID_RESOURCE_PREFIX = "android";
    public static final String KEY_CONFIG_SET_RETURN = "config_set_return";
    private static final boolean LOCAL_LOGV = false;
    public static final String METADATA_USAGE_ACCESS_REASON = "android.settings.metadata.USAGE_ACCESS_REASON";
    public static final int RESET_MODE_PACKAGE_DEFAULTS = 1;
    public static final int RESET_MODE_TRUSTED_DEFAULTS = 4;
    public static final int RESET_MODE_UNTRUSTED_CHANGES = 3;
    public static final int RESET_MODE_UNTRUSTED_DEFAULTS = 2;
    private static final String TAG = "Settings";
    public static final String ZEN_MODE_BLOCKED_EFFECTS_SETTINGS = "android.settings.ZEN_MODE_BLOCKED_EFFECTS_SETTINGS";
    public static final String ZEN_MODE_ONBOARDING = "android.settings.ZEN_MODE_ONBOARDING";
    private static boolean sInSystemServer = false;
    private static final Object sInSystemServerLock = new Object();
    private static final String[] PM_WRITE_SETTINGS = {Manifest.permission.WRITE_SETTINGS};
    private static final String[] PM_CHANGE_NETWORK_STATE = {Manifest.permission.CHANGE_NETWORK_STATE, Manifest.permission.WRITE_SETTINGS};
    private static final String[] PM_SYSTEM_ALERT_WINDOW = {Manifest.permission.SYSTEM_ALERT_WINDOW};

    @Retention(RetentionPolicy.SOURCE)
    public @interface AddWifiResult {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface EnableMmsDataReason {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ResetMode {
    }

    public static void setInSystemServer() {
        synchronized (sInSystemServerLock) {
            sInSystemServer = true;
        }
    }

    public static boolean isInSystemServer() {
        boolean z;
        synchronized (sInSystemServerLock) {
            z = sInSystemServer;
        }
        return z;
    }

    public static class SettingNotFoundException extends AndroidException {
        public SettingNotFoundException(String str) {
            super(str);
        }
    }

    public static class NameValueTable implements BaseColumns {
        public static final String IS_PRESERVED_IN_RESTORE = "is_preserved_in_restore";
        public static final String NAME = "name";
        public static final String VALUE = "value";

        protected static boolean putString(ContentResolver contentResolver, Uri uri, String str, String str2) {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", str);
                contentValues.put("value", str2);
                contentResolver.insert(uri, contentValues);
                return true;
            } catch (SQLException e) {
                Log.w(Settings.TAG, "Can't set key " + str + " in " + uri, e);
                return false;
            }
        }

        public static Uri getUriFor(Uri uri, String str) {
            return Uri.withAppendedPath(uri, str);
        }
    }

    private static final class GenerationTracker {
        private final MemoryIntArray mArray;
        private int mCurrentGeneration;
        private final Runnable mErrorHandler;
        private final int mIndex;

        public GenerationTracker(MemoryIntArray memoryIntArray, int i, int i2, Runnable runnable) {
            this.mArray = memoryIntArray;
            this.mIndex = i;
            this.mErrorHandler = runnable;
            this.mCurrentGeneration = i2;
        }

        public boolean isGenerationChanged() {
            int currentGeneration = readCurrentGeneration();
            if (currentGeneration < 0) {
                return true;
            }
            if (currentGeneration == this.mCurrentGeneration) {
                return false;
            }
            this.mCurrentGeneration = currentGeneration;
            return true;
        }

        public int getCurrentGeneration() {
            return this.mCurrentGeneration;
        }

        private int readCurrentGeneration() {
            try {
                return this.mArray.get(this.mIndex);
            } catch (IOException e) {
                Log.e(Settings.TAG, "Error getting current generation", e);
                Runnable runnable = this.mErrorHandler;
                if (runnable == null) {
                    return -1;
                }
                runnable.run();
                return -1;
            }
        }

        public void destroy() {
            try {
                this.mArray.close();
            } catch (IOException e) {
                Log.e(Settings.TAG, "Error closing backing array", e);
                Runnable runnable = this.mErrorHandler;
                if (runnable != null) {
                    runnable.run();
                }
            }
        }
    }

    private static final class ContentProviderHolder {
        private IContentProvider mContentProvider;
        private final Object mLock = new Object();
        private final Uri mUri;

        public ContentProviderHolder(Uri uri) {
            this.mUri = uri;
        }

        public IContentProvider getProvider(ContentResolver contentResolver) {
            IContentProvider iContentProvider;
            synchronized (this.mLock) {
                if (this.mContentProvider == null) {
                    this.mContentProvider = contentResolver.acquireProvider(this.mUri.getAuthority());
                }
                iContentProvider = this.mContentProvider;
            }
            return iContentProvider;
        }

        public void clearProviderForTest() {
            synchronized (this.mLock) {
                this.mContentProvider = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class NameValueCache {
        private static final boolean DEBUG = false;
        private static final String NAME_EQ_PLACEHOLDER = "name=?";
        private static final String[] SELECT_VALUE_PROJECTION = {"value"};
        private final String mCallGetCommand;
        private final String mCallListCommand;
        private final String mCallSetAllCommand;
        private final String mCallSetCommand;
        private GenerationTracker mGenerationTracker;
        private final ContentProviderHolder mProviderHolder;
        private final Uri mUri;
        private final ArrayMap<String, String> mValues;

        public NameValueCache(Uri uri, String str, String str2, ContentProviderHolder contentProviderHolder) {
            this(uri, str, str2, null, null, contentProviderHolder);
        }

        NameValueCache(Uri uri, String str, String str2, String str3, String str4, ContentProviderHolder contentProviderHolder) {
            this.mValues = new ArrayMap<>();
            this.mUri = uri;
            this.mCallGetCommand = str;
            this.mCallSetCommand = str2;
            this.mCallListCommand = str3;
            this.mCallSetAllCommand = str4;
            this.mProviderHolder = contentProviderHolder;
        }

        public boolean putStringForUser(ContentResolver contentResolver, String str, String str2, String str3, boolean z, int i, boolean z2) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString("value", str2);
                bundle.putInt(Settings.CALL_METHOD_USER_KEY, i);
                if (str3 != null) {
                    bundle.putString(Settings.CALL_METHOD_TAG_KEY, str3);
                }
                if (z) {
                    bundle.putBoolean(Settings.CALL_METHOD_MAKE_DEFAULT_KEY, true);
                }
                if (z2) {
                    bundle.putBoolean(Settings.CALL_METHOD_OVERRIDEABLE_BY_RESTORE_KEY, true);
                }
                this.mProviderHolder.getProvider(contentResolver).call(contentResolver.getPackageName(), contentResolver.getAttributionTag(), this.mProviderHolder.mUri.getAuthority(), this.mCallSetCommand, str, bundle);
                return true;
            } catch (RemoteException e) {
                Log.w(Settings.TAG, "Can't set key " + str + " in " + this.mUri, e);
                return false;
            }
        }

        public boolean setStringsForPrefix(ContentResolver contentResolver, String str, HashMap<String, String> map) {
            if (this.mCallSetAllCommand == null) {
                return false;
            }
            try {
                Bundle bundle = new Bundle();
                bundle.putString(Settings.CALL_METHOD_PREFIX_KEY, str);
                bundle.putSerializable(Settings.CALL_METHOD_FLAGS_KEY, map);
                return this.mProviderHolder.getProvider(contentResolver).call(contentResolver.getPackageName(), contentResolver.getAttributionTag(), this.mProviderHolder.mUri.getAuthority(), this.mCallSetAllCommand, null, bundle).getBoolean(Settings.KEY_CONFIG_SET_RETURN);
            } catch (RemoteException unused) {
                return false;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:131:0x0208  */
        /* JADX WARN: Removed duplicated region for block: B:135:0x020f  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0044  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x00bc A[Catch: RemoteException -> 0x012f, TryCatch #8 {RemoteException -> 0x012f, blocks: (B:32:0x005b, B:34:0x0067, B:43:0x0082, B:45:0x0088, B:47:0x0092, B:49:0x00b3, B:55:0x00db, B:57:0x00e4, B:75:0x012a, B:51:0x00b8, B:52:0x00bb, B:53:0x00bc, B:79:0x012e, B:59:0x00e7, B:62:0x00f9, B:64:0x0103, B:65:0x0108, B:66:0x0115, B:68:0x0119, B:70:0x0121, B:71:0x0126, B:36:0x006a, B:39:0x0070, B:40:0x0075, B:42:0x0081, B:48:0x0096), top: B:148:0x005b, inners: #2, #6, #9 }] */
        /* JADX WARN: Removed duplicated region for block: B:55:0x00db A[Catch: RemoteException -> 0x012f, TryCatch #8 {RemoteException -> 0x012f, blocks: (B:32:0x005b, B:34:0x0067, B:43:0x0082, B:45:0x0088, B:47:0x0092, B:49:0x00b3, B:55:0x00db, B:57:0x00e4, B:75:0x012a, B:51:0x00b8, B:52:0x00bb, B:53:0x00bc, B:79:0x012e, B:59:0x00e7, B:62:0x00f9, B:64:0x0103, B:65:0x0108, B:66:0x0115, B:68:0x0119, B:70:0x0121, B:71:0x0126, B:36:0x006a, B:39:0x0070, B:40:0x0075, B:42:0x0081, B:48:0x0096), top: B:148:0x005b, inners: #2, #6, #9 }] */
        /* JADX WARN: Type inference failed for: r16v0, types: [android.content.IContentProvider] */
        /* JADX WARN: Type inference failed for: r8v0 */
        /* JADX WARN: Type inference failed for: r8v1 */
        /* JADX WARN: Type inference failed for: r8v11 */
        /* JADX WARN: Type inference failed for: r8v12, types: [android.os.Bundle] */
        /* JADX WARN: Type inference failed for: r8v13 */
        /* JADX WARN: Type inference failed for: r8v14 */
        /* JADX WARN: Type inference failed for: r8v15 */
        /* JADX WARN: Type inference failed for: r8v16 */
        /* JADX WARN: Type inference failed for: r8v17 */
        /* JADX WARN: Type inference failed for: r8v18 */
        /* JADX WARN: Type inference failed for: r8v19 */
        /* JADX WARN: Type inference failed for: r8v2, types: [android.database.Cursor] */
        /* JADX WARN: Type inference failed for: r8v3 */
        /* JADX WARN: Type inference failed for: r8v4 */
        /* JADX WARN: Type inference failed for: r8v5, types: [android.database.Cursor] */
        /* JADX WARN: Type inference failed for: r8v6 */
        /* JADX WARN: Type inference failed for: r8v7, types: [android.database.Cursor] */
        /* JADX WARN: Type inference failed for: r8v8, types: [android.database.Cursor] */
        /* JADX WARN: Type inference failed for: r8v9 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.String getStringForUser(android.content.ContentResolver r23, java.lang.String r24, int r25) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 531
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.provider.Settings.NameValueCache.getStringForUser(android.content.ContentResolver, java.lang.String, int):java.lang.String");
        }

        public /* synthetic */ void lambda$getStringForUser$0$Settings$NameValueCache() {
            synchronized (this) {
                Log.e(Settings.TAG, "Error accessing generation tracker - removing");
                if (this.mGenerationTracker != null) {
                    GenerationTracker generationTracker = this.mGenerationTracker;
                    this.mGenerationTracker = null;
                    generationTracker.destroy();
                    this.mValues.clear();
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:32:0x0098  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public android.util.ArrayMap<java.lang.String, java.lang.String> getStringsForPrefix(android.content.ContentResolver r17, java.lang.String r18, java.util.List<java.lang.String> r19) {
            /*
                Method dump skipped, instruction units count: 368
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.provider.Settings.NameValueCache.getStringsForPrefix(android.content.ContentResolver, java.lang.String, java.util.List):android.util.ArrayMap");
        }

        public /* synthetic */ void lambda$getStringsForPrefix$1$Settings$NameValueCache() {
            synchronized (this) {
                Log.e(Settings.TAG, "Error accessing generation tracker - removing");
                if (this.mGenerationTracker != null) {
                    GenerationTracker generationTracker = this.mGenerationTracker;
                    this.mGenerationTracker = null;
                    generationTracker.destroy();
                    this.mValues.clear();
                }
            }
        }

        public void clearGenerationTrackerForTest() {
            synchronized (this) {
                if (this.mGenerationTracker != null) {
                    this.mGenerationTracker.destroy();
                }
                this.mValues.clear();
                this.mGenerationTracker = null;
            }
        }
    }

    public static boolean canDrawOverlays(Context context) {
        return isCallingPackageAllowedToDrawOverlays(context, Process.myUid(), context.getOpPackageName(), false);
    }

    public static final class System extends NameValueTable {
        public static final String ACCELEROMETER_ROTATION = "accelerometer_rotation";
        public static final String ADAPTIVE_SLEEP = "adaptive_sleep";

        @Deprecated
        public static final String ADB_ENABLED = "adb_enabled";
        public static final String ADVANCED_SETTINGS = "advanced_settings";
        public static final int ADVANCED_SETTINGS_DEFAULT = 0;

        @Deprecated
        public static final String AIRPLANE_MODE_ON = "airplane_mode_on";

        @Deprecated
        public static final String AIRPLANE_MODE_RADIOS = "airplane_mode_radios";

        @Deprecated
        public static final String AIRPLANE_MODE_TOGGLEABLE_RADIOS = "airplane_mode_toggleable_radios";
        public static final String ALARM_ALERT = "alarm_alert";
        public static final String ALARM_ALERT_CACHE = "alarm_alert_cache";
        public static final Uri ALARM_ALERT_CACHE_URI;

        @Deprecated
        public static final String ALWAYS_FINISH_ACTIVITIES = "always_finish_activities";

        @Deprecated
        public static final String ANDROID_ID = "android_id";

        @Deprecated
        public static final String ANIMATOR_DURATION_SCALE = "animator_duration_scale";
        public static final String APPEND_FOR_LAST_AUDIBLE = "_last_audible";

        @Deprecated
        public static final String AUTO_TIME = "auto_time";

        @Deprecated
        public static final String AUTO_TIME_ZONE = "auto_time_zone";
        public static final String BLUETOOTH_DISCOVERABILITY = "bluetooth_discoverability";
        public static final String BLUETOOTH_DISCOVERABILITY_TIMEOUT = "bluetooth_discoverability_timeout";

        @Deprecated
        public static final String BLUETOOTH_ON = "bluetooth_on";

        @Deprecated
        public static final String CAR_DOCK_SOUND = "car_dock_sound";

        @Deprecated
        public static final String CAR_UNDOCK_SOUND = "car_undock_sound";
        public static final Map<String, String> CLONE_FROM_PARENT_ON_VALUE;
        private static final Set<String> CLONE_TO_MANAGED_PROFILE;

        @Deprecated
        public static final String DATA_ROAMING = "data_roaming";
        public static final String DATE_FORMAT = "date_format";

        @Deprecated
        public static final String DEBUG_APP = "debug_app";
        public static final String DEBUG_ENABLE_ENHANCED_CALL_BLOCKING = "debug.enable_enhanced_calling";
        public static final Uri DEFAULT_ALARM_ALERT_URI;
        private static final float DEFAULT_FONT_SCALE = 1.0f;
        public static final Uri DEFAULT_NOTIFICATION_URI;
        public static final Uri DEFAULT_RINGTONE_URI;

        @Deprecated
        public static final String DESK_DOCK_SOUND = "desk_dock_sound";

        @Deprecated
        public static final String DESK_UNDOCK_SOUND = "desk_undock_sound";

        @Deprecated
        public static final String DEVICE_PROVISIONED = "device_provisioned";

        @Deprecated
        public static final String DIM_SCREEN = "dim_screen";
        public static final String DISPLAY_COLOR_MODE = "display_color_mode";

        @Deprecated
        public static final String DOCK_SOUNDS_ENABLED = "dock_sounds_enabled";
        public static final String DTMF_TONE_TYPE_WHEN_DIALING = "dtmf_tone_type";
        public static final String DTMF_TONE_WHEN_DIALING = "dtmf_tone";
        public static final String EGG_MODE = "egg_mode";
        public static final String END_BUTTON_BEHAVIOR = "end_button_behavior";
        public static final int END_BUTTON_BEHAVIOR_DEFAULT = 2;
        public static final int END_BUTTON_BEHAVIOR_HOME = 1;
        public static final int END_BUTTON_BEHAVIOR_SLEEP = 2;
        public static final String FONT_SCALE = "font_scale";
        public static final String HAPTIC_FEEDBACK_ENABLED = "haptic_feedback_enabled";
        public static final String HAPTIC_FEEDBACK_INTENSITY = "haptic_feedback_intensity";
        public static final String HEARING_AID = "hearing_aid";
        public static final String HIDE_ROTATION_LOCK_TOGGLE_FOR_ACCESSIBILITY = "hide_rotation_lock_toggle_for_accessibility";

        @Deprecated
        public static final String HTTP_PROXY = "http_proxy";

        @Deprecated
        public static final String INSTALL_NON_MARKET_APPS = "install_non_market_apps";
        public static final Set<String> INSTANT_APP_SETTINGS;
        public static final String[] LEGACY_RESTORE_SETTINGS;

        @Deprecated
        public static final String LOCATION_PROVIDERS_ALLOWED = "location_providers_allowed";
        public static final String LOCKSCREEN_DISABLED = "lockscreen.disabled";
        public static final String LOCKSCREEN_SOUNDS_ENABLED = "lockscreen_sounds_enabled";

        @Deprecated
        public static final String LOCK_PATTERN_ENABLED = "lock_pattern_autolock";

        @Deprecated
        public static final String LOCK_PATTERN_TACTILE_FEEDBACK_ENABLED = "lock_pattern_tactile_feedback_enabled";

        @Deprecated
        public static final String LOCK_PATTERN_VISIBLE = "lock_pattern_visible_pattern";

        @Deprecated
        public static final String LOCK_SOUND = "lock_sound";
        public static final String LOCK_TO_APP_ENABLED = "lock_to_app_enabled";

        @Deprecated
        public static final String LOGGING_ID = "logging_id";

        @Deprecated
        public static final String LOW_BATTERY_SOUND = "low_battery_sound";
        public static final String MASTER_BALANCE = "master_balance";
        public static final String MASTER_MONO = "master_mono";
        public static final String MEDIA_BUTTON_RECEIVER = "media_button_receiver";
        public static final String MIN_REFRESH_RATE = "min_refresh_rate";

        @Deprecated
        public static final String MODE_RINGER = "mode_ringer";
        public static final String MODE_RINGER_STREAMS_AFFECTED = "mode_ringer_streams_affected";
        private static final HashSet<String> MOVED_TO_GLOBAL;
        private static final HashSet<String> MOVED_TO_SECURE;
        private static final HashSet<String> MOVED_TO_SECURE_THEN_GLOBAL;
        public static final String MULTI_AUDIO_FOCUS_ENABLED = "multi_audio_focus_enabled";
        public static final String MUTE_STREAMS_AFFECTED = "mute_streams_affected";

        @Deprecated
        public static final String NETWORK_PREFERENCE = "network_preference";

        @Deprecated
        public static final String NEXT_ALARM_FORMATTED = "next_alarm_formatted";

        @Deprecated
        public static final String NOTIFICATIONS_USE_RING_VOLUME = "notifications_use_ring_volume";
        public static final String NOTIFICATION_LIGHT_PULSE = "notification_light_pulse";
        public static final String NOTIFICATION_SOUND = "notification_sound";
        public static final String NOTIFICATION_SOUND_CACHE = "notification_sound_cache";
        public static final Uri NOTIFICATION_SOUND_CACHE_URI;
        public static final String NOTIFICATION_VIBRATION_INTENSITY = "notification_vibration_intensity";

        @Deprecated
        public static final String PARENTAL_CONTROL_ENABLED = "parental_control_enabled";

        @Deprecated
        public static final String PARENTAL_CONTROL_LAST_UPDATE = "parental_control_last_update";

        @Deprecated
        public static final String PARENTAL_CONTROL_REDIRECT_URL = "parental_control_redirect_url";
        public static final String PEAK_REFRESH_RATE = "peak_refresh_rate";
        public static final String POINTER_LOCATION = "pointer_location";
        public static final String POINTER_SPEED = "pointer_speed";

        @Deprecated
        public static final String POWER_SOUNDS_ENABLED = "power_sounds_enabled";
        public static final Set<String> PRIVATE_SETTINGS;
        public static final Set<String> PUBLIC_SETTINGS;

        @Deprecated
        public static final String RADIO_BLUETOOTH = "bluetooth";

        @Deprecated
        public static final String RADIO_CELL = "cell";

        @Deprecated
        public static final String RADIO_NFC = "nfc";

        @Deprecated
        public static final String RADIO_WIFI = "wifi";

        @Deprecated
        public static final String RADIO_WIMAX = "wimax";
        public static final String RINGTONE = "ringtone";
        public static final String RINGTONE_CACHE = "ringtone_cache";
        public static final Uri RINGTONE_CACHE_URI;
        public static final String RING_VIBRATION_INTENSITY = "ring_vibration_intensity";
        public static final String SCREEN_AUTO_BRIGHTNESS_ADJ = "screen_auto_brightness_adj";
        public static final String SCREEN_BRIGHTNESS = "screen_brightness";
        public static final String SCREEN_BRIGHTNESS_FLOAT = "screen_brightness_float";
        public static final String SCREEN_BRIGHTNESS_FOR_VR = "screen_brightness_for_vr";
        public static final String SCREEN_BRIGHTNESS_FOR_VR_FLOAT = "screen_brightness_for_vr_float";
        public static final String SCREEN_BRIGHTNESS_MODE = "screen_brightness_mode";
        public static final int SCREEN_BRIGHTNESS_MODE_AUTOMATIC = 1;
        public static final int SCREEN_BRIGHTNESS_MODE_MANUAL = 0;
        public static final String SCREEN_OFF_TIMEOUT = "screen_off_timeout";
        public static final String SCREEN_ORIENTATION = "screen_orientation";

        @Deprecated
        public static final String SETTINGS_CLASSNAME = "settings_classname";
        public static final String SETUP_WIZARD_HAS_RUN = "setup_wizard_has_run";
        public static final String SHOW_BATTERY_PERCENT = "status_bar_show_battery_percent";
        public static final String SHOW_GTALK_SERVICE_STATUS = "SHOW_GTALK_SERVICE_STATUS";

        @Deprecated
        public static final String SHOW_PROCESSES = "show_processes";
        public static final String SHOW_TOUCHES = "show_touches";

        @Deprecated
        public static final String SHOW_WEB_SUGGESTIONS = "show_web_suggestions";
        public static final String SIP_ADDRESS_ONLY = "SIP_ADDRESS_ONLY";
        public static final String SIP_ALWAYS = "SIP_ALWAYS";

        @Deprecated
        public static final String SIP_ASK_ME_EACH_TIME = "SIP_ASK_ME_EACH_TIME";
        public static final String SIP_CALL_OPTIONS = "sip_call_options";
        public static final String SIP_RECEIVE_CALLS = "sip_receive_calls";
        public static final String SOUND_EFFECTS_ENABLED = "sound_effects_enabled";

        @Deprecated
        public static final String STAY_ON_WHILE_PLUGGED_IN = "stay_on_while_plugged_in";
        public static final String SYSTEM_LOCALES = "system_locales";
        public static final String TEXT_AUTO_CAPS = "auto_caps";
        public static final String TEXT_AUTO_PUNCTUATE = "auto_punctuate";
        public static final String TEXT_AUTO_REPLACE = "auto_replace";
        public static final String TEXT_SHOW_PASSWORD = "show_password";
        public static final String TIME_12_24 = "time_12_24";

        @Deprecated
        public static final String TRANSITION_ANIMATION_SCALE = "transition_animation_scale";
        public static final String TTY_MODE = "tty_mode";

        @Deprecated
        public static final String UNLOCK_SOUND = "unlock_sound";

        @Deprecated
        public static final String USB_MASS_STORAGE_ENABLED = "usb_mass_storage_enabled";
        public static final String USER_ROTATION = "user_rotation";

        @Deprecated
        public static final String USE_GOOGLE_MAIL = "use_google_mail";
        public static final String VIBRATE_INPUT_DEVICES = "vibrate_input_devices";
        public static final String VIBRATE_IN_SILENT = "vibrate_in_silent";
        public static final String VIBRATE_ON = "vibrate_on";
        public static final String VIBRATE_WHEN_RINGING = "vibrate_when_ringing";
        public static final String VOLUME_ACCESSIBILITY = "volume_a11y";
        public static final String VOLUME_ALARM = "volume_alarm";
        public static final String VOLUME_ASSISTANT = "volume_assistant";
        public static final String VOLUME_BLUETOOTH_SCO = "volume_bluetooth_sco";
        public static final String VOLUME_MASTER = "volume_master";
        public static final String VOLUME_MUSIC = "volume_music";
        public static final String VOLUME_NOTIFICATION = "volume_notification";
        public static final String VOLUME_RING = "volume_ring";
        public static final String[] VOLUME_SETTINGS;
        public static final String[] VOLUME_SETTINGS_INT;
        public static final String VOLUME_SYSTEM = "volume_system";
        public static final String VOLUME_VOICE = "volume_voice";

        @Deprecated
        public static final String WAIT_FOR_DEBUGGER = "wait_for_debugger";

        @Deprecated
        public static final String WALLPAPER_ACTIVITY = "wallpaper_activity";
        public static final String WHEN_TO_MAKE_WIFI_CALLS = "when_to_make_wifi_calls";

        @Deprecated
        public static final String WIFI_MAX_DHCP_RETRY_COUNT = "wifi_max_dhcp_retry_count";

        @Deprecated
        public static final String WIFI_MOBILE_DATA_TRANSITION_WAKELOCK_TIMEOUT_MS = "wifi_mobile_data_transition_wakelock_timeout_ms";

        @Deprecated
        public static final String WIFI_NETWORKS_AVAILABLE_NOTIFICATION_ON = "wifi_networks_available_notification_on";

        @Deprecated
        public static final String WIFI_NETWORKS_AVAILABLE_REPEAT_DELAY = "wifi_networks_available_repeat_delay";

        @Deprecated
        public static final String WIFI_NUM_OPEN_NETWORKS_KEPT = "wifi_num_open_networks_kept";

        @Deprecated
        public static final String WIFI_ON = "wifi_on";

        @Deprecated
        public static final String WIFI_SLEEP_POLICY = "wifi_sleep_policy";

        @Deprecated
        public static final int WIFI_SLEEP_POLICY_DEFAULT = 0;

        @Deprecated
        public static final int WIFI_SLEEP_POLICY_NEVER = 2;

        @Deprecated
        public static final int WIFI_SLEEP_POLICY_NEVER_WHILE_PLUGGED = 1;

        @Deprecated
        public static final String WIFI_STATIC_DNS1 = "wifi_static_dns1";

        @Deprecated
        public static final String WIFI_STATIC_DNS2 = "wifi_static_dns2";

        @Deprecated
        public static final String WIFI_STATIC_GATEWAY = "wifi_static_gateway";

        @Deprecated
        public static final String WIFI_STATIC_IP = "wifi_static_ip";

        @Deprecated
        public static final String WIFI_STATIC_NETMASK = "wifi_static_netmask";

        @Deprecated
        public static final String WIFI_USE_STATIC_IP = "wifi_use_static_ip";

        @Deprecated
        public static final String WIFI_WATCHDOG_ACCEPTABLE_PACKET_LOSS_PERCENTAGE = "wifi_watchdog_acceptable_packet_loss_percentage";

        @Deprecated
        public static final String WIFI_WATCHDOG_AP_COUNT = "wifi_watchdog_ap_count";

        @Deprecated
        public static final String WIFI_WATCHDOG_BACKGROUND_CHECK_DELAY_MS = "wifi_watchdog_background_check_delay_ms";

        @Deprecated
        public static final String WIFI_WATCHDOG_BACKGROUND_CHECK_ENABLED = "wifi_watchdog_background_check_enabled";

        @Deprecated
        public static final String WIFI_WATCHDOG_BACKGROUND_CHECK_TIMEOUT_MS = "wifi_watchdog_background_check_timeout_ms";

        @Deprecated
        public static final String WIFI_WATCHDOG_INITIAL_IGNORED_PING_COUNT = "wifi_watchdog_initial_ignored_ping_count";

        @Deprecated
        public static final String WIFI_WATCHDOG_MAX_AP_CHECKS = "wifi_watchdog_max_ap_checks";

        @Deprecated
        public static final String WIFI_WATCHDOG_ON = "wifi_watchdog_on";

        @Deprecated
        public static final String WIFI_WATCHDOG_PING_COUNT = "wifi_watchdog_ping_count";

        @Deprecated
        public static final String WIFI_WATCHDOG_PING_DELAY_MS = "wifi_watchdog_ping_delay_ms";

        @Deprecated
        public static final String WIFI_WATCHDOG_PING_TIMEOUT_MS = "wifi_watchdog_ping_timeout_ms";

        @Deprecated
        public static final String WINDOW_ANIMATION_SCALE = "window_animation_scale";
        public static final String WINDOW_ORIENTATION_LISTENER_LOG = "window_orientation_listener_log";
        public static final Uri CONTENT_URI = Uri.parse("content://settings/system");
        private static final ContentProviderHolder sProviderHolder = new ContentProviderHolder(CONTENT_URI);
        private static final NameValueCache sNameValueCache = new NameValueCache(CONTENT_URI, Settings.CALL_METHOD_GET_SYSTEM, Settings.CALL_METHOD_PUT_SYSTEM, sProviderHolder);

        public static boolean hasInterestingConfigurationChanges(int i) {
            return ((1073741824 & i) == 0 && (i & 4) == 0) ? false : true;
        }

        static {
            HashSet<String> hashSet = new HashSet<>(30);
            MOVED_TO_SECURE = hashSet;
            hashSet.add("adaptive_sleep");
            MOVED_TO_SECURE.add("android_id");
            MOVED_TO_SECURE.add("http_proxy");
            MOVED_TO_SECURE.add("location_providers_allowed");
            MOVED_TO_SECURE.add(Secure.LOCK_BIOMETRIC_WEAK_FLAGS);
            MOVED_TO_SECURE.add("lock_pattern_autolock");
            MOVED_TO_SECURE.add("lock_pattern_visible_pattern");
            MOVED_TO_SECURE.add("lock_pattern_tactile_feedback_enabled");
            MOVED_TO_SECURE.add("logging_id");
            MOVED_TO_SECURE.add("parental_control_enabled");
            MOVED_TO_SECURE.add("parental_control_last_update");
            MOVED_TO_SECURE.add("parental_control_redirect_url");
            MOVED_TO_SECURE.add("settings_classname");
            MOVED_TO_SECURE.add("use_google_mail");
            MOVED_TO_SECURE.add("wifi_networks_available_notification_on");
            MOVED_TO_SECURE.add("wifi_networks_available_repeat_delay");
            MOVED_TO_SECURE.add("wifi_num_open_networks_kept");
            MOVED_TO_SECURE.add("wifi_on");
            MOVED_TO_SECURE.add("wifi_watchdog_acceptable_packet_loss_percentage");
            MOVED_TO_SECURE.add("wifi_watchdog_ap_count");
            MOVED_TO_SECURE.add("wifi_watchdog_background_check_delay_ms");
            MOVED_TO_SECURE.add("wifi_watchdog_background_check_enabled");
            MOVED_TO_SECURE.add("wifi_watchdog_background_check_timeout_ms");
            MOVED_TO_SECURE.add("wifi_watchdog_initial_ignored_ping_count");
            MOVED_TO_SECURE.add("wifi_watchdog_max_ap_checks");
            MOVED_TO_SECURE.add("wifi_watchdog_on");
            MOVED_TO_SECURE.add("wifi_watchdog_ping_count");
            MOVED_TO_SECURE.add("wifi_watchdog_ping_delay_ms");
            MOVED_TO_SECURE.add("wifi_watchdog_ping_timeout_ms");
            MOVED_TO_SECURE.add("install_non_market_apps");
            MOVED_TO_GLOBAL = new HashSet<>();
            HashSet<String> hashSet2 = new HashSet<>();
            MOVED_TO_SECURE_THEN_GLOBAL = hashSet2;
            hashSet2.add("adb_enabled");
            MOVED_TO_SECURE_THEN_GLOBAL.add("bluetooth_on");
            MOVED_TO_SECURE_THEN_GLOBAL.add("data_roaming");
            MOVED_TO_SECURE_THEN_GLOBAL.add("device_provisioned");
            MOVED_TO_SECURE_THEN_GLOBAL.add("usb_mass_storage_enabled");
            MOVED_TO_SECURE_THEN_GLOBAL.add("http_proxy");
            MOVED_TO_GLOBAL.add("airplane_mode_on");
            MOVED_TO_GLOBAL.add("airplane_mode_radios");
            MOVED_TO_GLOBAL.add("airplane_mode_toggleable_radios");
            MOVED_TO_GLOBAL.add("auto_time");
            MOVED_TO_GLOBAL.add("auto_time_zone");
            MOVED_TO_GLOBAL.add("car_dock_sound");
            MOVED_TO_GLOBAL.add("car_undock_sound");
            MOVED_TO_GLOBAL.add("desk_dock_sound");
            MOVED_TO_GLOBAL.add("desk_undock_sound");
            MOVED_TO_GLOBAL.add("dock_sounds_enabled");
            MOVED_TO_GLOBAL.add("lock_sound");
            MOVED_TO_GLOBAL.add("unlock_sound");
            MOVED_TO_GLOBAL.add("low_battery_sound");
            MOVED_TO_GLOBAL.add("power_sounds_enabled");
            MOVED_TO_GLOBAL.add("stay_on_while_plugged_in");
            MOVED_TO_GLOBAL.add("wifi_sleep_policy");
            MOVED_TO_GLOBAL.add("mode_ringer");
            MOVED_TO_GLOBAL.add("window_animation_scale");
            MOVED_TO_GLOBAL.add("transition_animation_scale");
            MOVED_TO_GLOBAL.add("animator_duration_scale");
            MOVED_TO_GLOBAL.add(Global.FANCY_IME_ANIMATIONS);
            MOVED_TO_GLOBAL.add(Global.COMPATIBILITY_MODE);
            MOVED_TO_GLOBAL.add(Global.EMERGENCY_TONE);
            MOVED_TO_GLOBAL.add(Global.CALL_AUTO_RETRY);
            MOVED_TO_GLOBAL.add("debug_app");
            MOVED_TO_GLOBAL.add("wait_for_debugger");
            MOVED_TO_GLOBAL.add("always_finish_activities");
            MOVED_TO_GLOBAL.add(Global.TZINFO_UPDATE_CONTENT_URL);
            MOVED_TO_GLOBAL.add(Global.TZINFO_UPDATE_METADATA_URL);
            MOVED_TO_GLOBAL.add(Global.SELINUX_UPDATE_CONTENT_URL);
            MOVED_TO_GLOBAL.add(Global.SELINUX_UPDATE_METADATA_URL);
            MOVED_TO_GLOBAL.add(Global.SMS_SHORT_CODES_UPDATE_CONTENT_URL);
            MOVED_TO_GLOBAL.add(Global.SMS_SHORT_CODES_UPDATE_METADATA_URL);
            MOVED_TO_GLOBAL.add(Global.CERT_PIN_UPDATE_CONTENT_URL);
            MOVED_TO_GLOBAL.add(Global.CERT_PIN_UPDATE_METADATA_URL);
            VOLUME_SETTINGS = new String[]{VOLUME_VOICE, VOLUME_SYSTEM, VOLUME_RING, VOLUME_MUSIC, VOLUME_ALARM, VOLUME_NOTIFICATION, VOLUME_BLUETOOTH_SCO};
            VOLUME_SETTINGS_INT = new String[]{VOLUME_VOICE, VOLUME_SYSTEM, VOLUME_RING, VOLUME_MUSIC, VOLUME_ALARM, VOLUME_NOTIFICATION, VOLUME_BLUETOOTH_SCO, "", "", "", VOLUME_ACCESSIBILITY, VOLUME_ASSISTANT};
            DEFAULT_RINGTONE_URI = getUriFor(RINGTONE);
            RINGTONE_CACHE_URI = getUriFor(RINGTONE_CACHE);
            DEFAULT_NOTIFICATION_URI = getUriFor(NOTIFICATION_SOUND);
            NOTIFICATION_SOUND_CACHE_URI = getUriFor(NOTIFICATION_SOUND_CACHE);
            DEFAULT_ALARM_ALERT_URI = getUriFor(ALARM_ALERT);
            ALARM_ALERT_CACHE_URI = getUriFor(ALARM_ALERT_CACHE);
            LEGACY_RESTORE_SETTINGS = new String[0];
            ArraySet arraySet = new ArraySet();
            PUBLIC_SETTINGS = arraySet;
            arraySet.add(END_BUTTON_BEHAVIOR);
            PUBLIC_SETTINGS.add(WIFI_USE_STATIC_IP);
            PUBLIC_SETTINGS.add(WIFI_STATIC_IP);
            PUBLIC_SETTINGS.add(WIFI_STATIC_GATEWAY);
            PUBLIC_SETTINGS.add(WIFI_STATIC_NETMASK);
            PUBLIC_SETTINGS.add(WIFI_STATIC_DNS1);
            PUBLIC_SETTINGS.add(WIFI_STATIC_DNS2);
            PUBLIC_SETTINGS.add(BLUETOOTH_DISCOVERABILITY);
            PUBLIC_SETTINGS.add(BLUETOOTH_DISCOVERABILITY_TIMEOUT);
            PUBLIC_SETTINGS.add(NEXT_ALARM_FORMATTED);
            PUBLIC_SETTINGS.add(FONT_SCALE);
            PUBLIC_SETTINGS.add(SYSTEM_LOCALES);
            PUBLIC_SETTINGS.add(DIM_SCREEN);
            PUBLIC_SETTINGS.add(SCREEN_OFF_TIMEOUT);
            PUBLIC_SETTINGS.add("screen_brightness");
            PUBLIC_SETTINGS.add(SCREEN_BRIGHTNESS_FLOAT);
            PUBLIC_SETTINGS.add(SCREEN_BRIGHTNESS_FOR_VR);
            PUBLIC_SETTINGS.add(SCREEN_BRIGHTNESS_FOR_VR_FLOAT);
            PUBLIC_SETTINGS.add(SCREEN_BRIGHTNESS_MODE);
            PUBLIC_SETTINGS.add(MODE_RINGER_STREAMS_AFFECTED);
            PUBLIC_SETTINGS.add(MUTE_STREAMS_AFFECTED);
            PUBLIC_SETTINGS.add(VIBRATE_ON);
            PUBLIC_SETTINGS.add(VOLUME_RING);
            PUBLIC_SETTINGS.add(VOLUME_SYSTEM);
            PUBLIC_SETTINGS.add(VOLUME_VOICE);
            PUBLIC_SETTINGS.add(VOLUME_MUSIC);
            PUBLIC_SETTINGS.add(VOLUME_ALARM);
            PUBLIC_SETTINGS.add(VOLUME_NOTIFICATION);
            PUBLIC_SETTINGS.add(VOLUME_BLUETOOTH_SCO);
            PUBLIC_SETTINGS.add(VOLUME_ASSISTANT);
            PUBLIC_SETTINGS.add(RINGTONE);
            PUBLIC_SETTINGS.add(NOTIFICATION_SOUND);
            PUBLIC_SETTINGS.add(ALARM_ALERT);
            PUBLIC_SETTINGS.add(TEXT_AUTO_REPLACE);
            PUBLIC_SETTINGS.add(TEXT_AUTO_CAPS);
            PUBLIC_SETTINGS.add(TEXT_AUTO_PUNCTUATE);
            PUBLIC_SETTINGS.add(TEXT_SHOW_PASSWORD);
            PUBLIC_SETTINGS.add(SHOW_GTALK_SERVICE_STATUS);
            PUBLIC_SETTINGS.add(WALLPAPER_ACTIVITY);
            PUBLIC_SETTINGS.add("time_12_24");
            PUBLIC_SETTINGS.add(DATE_FORMAT);
            PUBLIC_SETTINGS.add(SETUP_WIZARD_HAS_RUN);
            PUBLIC_SETTINGS.add(ACCELEROMETER_ROTATION);
            PUBLIC_SETTINGS.add(USER_ROTATION);
            PUBLIC_SETTINGS.add(DTMF_TONE_WHEN_DIALING);
            PUBLIC_SETTINGS.add(SOUND_EFFECTS_ENABLED);
            PUBLIC_SETTINGS.add(HAPTIC_FEEDBACK_ENABLED);
            PUBLIC_SETTINGS.add(SHOW_WEB_SUGGESTIONS);
            PUBLIC_SETTINGS.add(VIBRATE_WHEN_RINGING);
            ArraySet arraySet2 = new ArraySet();
            PRIVATE_SETTINGS = arraySet2;
            arraySet2.add(WIFI_USE_STATIC_IP);
            PRIVATE_SETTINGS.add(END_BUTTON_BEHAVIOR);
            PRIVATE_SETTINGS.add(ADVANCED_SETTINGS);
            PRIVATE_SETTINGS.add(SCREEN_AUTO_BRIGHTNESS_ADJ);
            PRIVATE_SETTINGS.add(VIBRATE_INPUT_DEVICES);
            PRIVATE_SETTINGS.add(VOLUME_MASTER);
            PRIVATE_SETTINGS.add(MASTER_MONO);
            PRIVATE_SETTINGS.add(MASTER_BALANCE);
            PRIVATE_SETTINGS.add(NOTIFICATIONS_USE_RING_VOLUME);
            PRIVATE_SETTINGS.add(VIBRATE_IN_SILENT);
            PRIVATE_SETTINGS.add(MEDIA_BUTTON_RECEIVER);
            PRIVATE_SETTINGS.add(HIDE_ROTATION_LOCK_TOGGLE_FOR_ACCESSIBILITY);
            PRIVATE_SETTINGS.add(DTMF_TONE_TYPE_WHEN_DIALING);
            PRIVATE_SETTINGS.add(HEARING_AID);
            PRIVATE_SETTINGS.add(TTY_MODE);
            PRIVATE_SETTINGS.add(NOTIFICATION_LIGHT_PULSE);
            PRIVATE_SETTINGS.add(POINTER_LOCATION);
            PRIVATE_SETTINGS.add(SHOW_TOUCHES);
            PRIVATE_SETTINGS.add(WINDOW_ORIENTATION_LISTENER_LOG);
            PRIVATE_SETTINGS.add("power_sounds_enabled");
            PRIVATE_SETTINGS.add("dock_sounds_enabled");
            PRIVATE_SETTINGS.add(LOCKSCREEN_SOUNDS_ENABLED);
            PRIVATE_SETTINGS.add("lockscreen.disabled");
            PRIVATE_SETTINGS.add("low_battery_sound");
            PRIVATE_SETTINGS.add("desk_dock_sound");
            PRIVATE_SETTINGS.add("desk_undock_sound");
            PRIVATE_SETTINGS.add("car_dock_sound");
            PRIVATE_SETTINGS.add("car_undock_sound");
            PRIVATE_SETTINGS.add("lock_sound");
            PRIVATE_SETTINGS.add("unlock_sound");
            PRIVATE_SETTINGS.add(SIP_RECEIVE_CALLS);
            PRIVATE_SETTINGS.add(SIP_CALL_OPTIONS);
            PRIVATE_SETTINGS.add(SIP_ALWAYS);
            PRIVATE_SETTINGS.add(SIP_ADDRESS_ONLY);
            PRIVATE_SETTINGS.add(SIP_ASK_ME_EACH_TIME);
            PRIVATE_SETTINGS.add(POINTER_SPEED);
            PRIVATE_SETTINGS.add(LOCK_TO_APP_ENABLED);
            PRIVATE_SETTINGS.add(EGG_MODE);
            PRIVATE_SETTINGS.add(SHOW_BATTERY_PERCENT);
            PRIVATE_SETTINGS.add(DISPLAY_COLOR_MODE);
            ArraySet arraySet3 = new ArraySet();
            CLONE_TO_MANAGED_PROFILE = arraySet3;
            arraySet3.add(DATE_FORMAT);
            CLONE_TO_MANAGED_PROFILE.add(HAPTIC_FEEDBACK_ENABLED);
            CLONE_TO_MANAGED_PROFILE.add(SOUND_EFFECTS_ENABLED);
            CLONE_TO_MANAGED_PROFILE.add(TEXT_SHOW_PASSWORD);
            CLONE_TO_MANAGED_PROFILE.add("time_12_24");
            ArrayMap arrayMap = new ArrayMap();
            CLONE_FROM_PARENT_ON_VALUE = arrayMap;
            arrayMap.put(RINGTONE, Secure.SYNC_PARENT_SOUNDS);
            CLONE_FROM_PARENT_ON_VALUE.put(NOTIFICATION_SOUND, Secure.SYNC_PARENT_SOUNDS);
            CLONE_FROM_PARENT_ON_VALUE.put(ALARM_ALERT, Secure.SYNC_PARENT_SOUNDS);
            ArraySet arraySet4 = new ArraySet();
            INSTANT_APP_SETTINGS = arraySet4;
            arraySet4.add(TEXT_AUTO_REPLACE);
            INSTANT_APP_SETTINGS.add(TEXT_AUTO_CAPS);
            INSTANT_APP_SETTINGS.add(TEXT_AUTO_PUNCTUATE);
            INSTANT_APP_SETTINGS.add(TEXT_SHOW_PASSWORD);
            INSTANT_APP_SETTINGS.add(DATE_FORMAT);
            INSTANT_APP_SETTINGS.add(FONT_SCALE);
            INSTANT_APP_SETTINGS.add(HAPTIC_FEEDBACK_ENABLED);
            INSTANT_APP_SETTINGS.add("time_12_24");
            INSTANT_APP_SETTINGS.add(SOUND_EFFECTS_ENABLED);
            INSTANT_APP_SETTINGS.add(ACCELEROMETER_ROTATION);
        }

        public static void getMovedToGlobalSettings(Set<String> set) {
            set.addAll(MOVED_TO_GLOBAL);
            set.addAll(MOVED_TO_SECURE_THEN_GLOBAL);
        }

        public static void getMovedToSecureSettings(Set<String> set) {
            set.addAll(MOVED_TO_SECURE);
        }

        public static void getNonLegacyMovedKeys(HashSet<String> hashSet) {
            hashSet.addAll(MOVED_TO_GLOBAL);
        }

        public static void clearProviderForTest() {
            sProviderHolder.clearProviderForTest();
            sNameValueCache.clearGenerationTrackerForTest();
        }

        public static String getString(ContentResolver contentResolver, String str) {
            return getStringForUser(contentResolver, str, contentResolver.getUserId());
        }

        public static String getStringForUser(ContentResolver contentResolver, String str, int i) {
            if (MOVED_TO_SECURE.contains(str)) {
                Log.w(Settings.TAG, "Setting " + str + " has moved from android.provider.Settings.System to android.provider.Settings.Secure, returning read-only value.");
                return Secure.getStringForUser(contentResolver, str, i);
            }
            if (MOVED_TO_GLOBAL.contains(str) || MOVED_TO_SECURE_THEN_GLOBAL.contains(str)) {
                Log.w(Settings.TAG, "Setting " + str + " has moved from android.provider.Settings.System to android.provider.Settings.Global, returning read-only value.");
                return Global.getStringForUser(contentResolver, str, i);
            }
            return sNameValueCache.getStringForUser(contentResolver, str, i);
        }

        public static boolean putString(ContentResolver contentResolver, String str, String str2) {
            return putStringForUser(contentResolver, str, str2, contentResolver.getUserId());
        }

        @SystemApi
        public static boolean putString(ContentResolver contentResolver, String str, String str2, boolean z) {
            return putStringForUser(contentResolver, str, str2, contentResolver.getUserId(), z);
        }

        public static boolean putStringForUser(ContentResolver contentResolver, String str, String str2, int i) {
            return putStringForUser(contentResolver, str, str2, i, false);
        }

        private static boolean putStringForUser(ContentResolver contentResolver, String str, String str2, int i, boolean z) {
            if (MOVED_TO_SECURE.contains(str)) {
                Log.w(Settings.TAG, "Setting " + str + " has moved from android.provider.Settings.System to android.provider.Settings.Secure, value is unchanged.");
                return false;
            }
            if (MOVED_TO_GLOBAL.contains(str) || MOVED_TO_SECURE_THEN_GLOBAL.contains(str)) {
                Log.w(Settings.TAG, "Setting " + str + " has moved from android.provider.Settings.System to android.provider.Settings.Global, value is unchanged.");
                return false;
            }
            return sNameValueCache.putStringForUser(contentResolver, str, str2, null, false, i, z);
        }

        public static Uri getUriFor(String str) {
            if (MOVED_TO_SECURE.contains(str)) {
                Log.w(Settings.TAG, "Setting " + str + " has moved from android.provider.Settings.System to android.provider.Settings.Secure, returning Secure URI.");
                return Secure.getUriFor(Secure.CONTENT_URI, str);
            }
            if (MOVED_TO_GLOBAL.contains(str) || MOVED_TO_SECURE_THEN_GLOBAL.contains(str)) {
                Log.w(Settings.TAG, "Setting " + str + " has moved from android.provider.Settings.System to android.provider.Settings.Global, returning read-only global URI.");
                return Global.getUriFor(Global.CONTENT_URI, str);
            }
            return getUriFor(CONTENT_URI, str);
        }

        public static int getInt(ContentResolver contentResolver, String str, int i) {
            return getIntForUser(contentResolver, str, i, contentResolver.getUserId());
        }

        public static int getIntForUser(ContentResolver contentResolver, String str, int i, int i2) {
            String stringForUser = getStringForUser(contentResolver, str, i2);
            if (stringForUser == null) {
                return i;
            }
            try {
                return Integer.parseInt(stringForUser);
            } catch (NumberFormatException unused) {
                return i;
            }
        }

        public static int getInt(ContentResolver contentResolver, String str) throws SettingNotFoundException {
            return getIntForUser(contentResolver, str, contentResolver.getUserId());
        }

        public static int getIntForUser(ContentResolver contentResolver, String str, int i) throws SettingNotFoundException {
            try {
                return Integer.parseInt(getStringForUser(contentResolver, str, i));
            } catch (NumberFormatException unused) {
                throw new SettingNotFoundException(str);
            }
        }

        public static boolean putInt(ContentResolver contentResolver, String str, int i) {
            return putIntForUser(contentResolver, str, i, contentResolver.getUserId());
        }

        public static boolean putIntForUser(ContentResolver contentResolver, String str, int i, int i2) {
            return putStringForUser(contentResolver, str, Integer.toString(i), i2);
        }

        public static long getLong(ContentResolver contentResolver, String str, long j) {
            return getLongForUser(contentResolver, str, j, contentResolver.getUserId());
        }

        public static long getLongForUser(ContentResolver contentResolver, String str, long j, int i) {
            String stringForUser = getStringForUser(contentResolver, str, i);
            if (stringForUser == null) {
                return j;
            }
            try {
                return Long.parseLong(stringForUser);
            } catch (NumberFormatException unused) {
                return j;
            }
        }

        public static long getLong(ContentResolver contentResolver, String str) throws SettingNotFoundException {
            return getLongForUser(contentResolver, str, contentResolver.getUserId());
        }

        public static long getLongForUser(ContentResolver contentResolver, String str, int i) throws SettingNotFoundException {
            try {
                return Long.parseLong(getStringForUser(contentResolver, str, i));
            } catch (NumberFormatException unused) {
                throw new SettingNotFoundException(str);
            }
        }

        public static boolean putLong(ContentResolver contentResolver, String str, long j) {
            return putLongForUser(contentResolver, str, j, contentResolver.getUserId());
        }

        public static boolean putLongForUser(ContentResolver contentResolver, String str, long j, int i) {
            return putStringForUser(contentResolver, str, Long.toString(j), i);
        }

        public static float getFloat(ContentResolver contentResolver, String str, float f) {
            return getFloatForUser(contentResolver, str, f, contentResolver.getUserId());
        }

        public static float getFloatForUser(ContentResolver contentResolver, String str, float f, int i) {
            String stringForUser = getStringForUser(contentResolver, str, i);
            if (stringForUser == null) {
                return f;
            }
            try {
                return Float.parseFloat(stringForUser);
            } catch (NumberFormatException unused) {
                return f;
            }
        }

        public static float getFloat(ContentResolver contentResolver, String str) throws SettingNotFoundException {
            return getFloatForUser(contentResolver, str, contentResolver.getUserId());
        }

        public static float getFloatForUser(ContentResolver contentResolver, String str, int i) throws SettingNotFoundException {
            String stringForUser = getStringForUser(contentResolver, str, i);
            if (stringForUser == null) {
                throw new SettingNotFoundException(str);
            }
            try {
                return Float.parseFloat(stringForUser);
            } catch (NumberFormatException unused) {
                throw new SettingNotFoundException(str);
            }
        }

        public static boolean putFloat(ContentResolver contentResolver, String str, float f) {
            return putFloatForUser(contentResolver, str, f, contentResolver.getUserId());
        }

        public static boolean putFloatForUser(ContentResolver contentResolver, String str, float f, int i) {
            return putStringForUser(contentResolver, str, Float.toString(f), i);
        }

        public static void getConfiguration(ContentResolver contentResolver, Configuration configuration) {
            adjustConfigurationForUser(contentResolver, configuration, contentResolver.getUserId(), false);
        }

        public static void adjustConfigurationForUser(ContentResolver contentResolver, Configuration configuration, int i, boolean z) {
            configuration.fontScale = getFloatForUser(contentResolver, FONT_SCALE, 1.0f, i);
            if (configuration.fontScale < 0.0f) {
                configuration.fontScale = 1.0f;
            }
            String stringForUser = getStringForUser(contentResolver, SYSTEM_LOCALES, i);
            if (stringForUser != null) {
                configuration.setLocales(LocaleList.forLanguageTags(stringForUser));
            } else if (z) {
                putStringForUser(contentResolver, SYSTEM_LOCALES, configuration.getLocales().toLanguageTags(), i, false);
            }
        }

        public static void clearConfiguration(Configuration configuration) {
            configuration.fontScale = 0.0f;
            if (configuration.userSetLocale || configuration.getLocales().isEmpty()) {
                return;
            }
            configuration.clearLocales();
        }

        public static boolean putConfiguration(ContentResolver contentResolver, Configuration configuration) {
            return putConfigurationForUser(contentResolver, configuration, contentResolver.getUserId());
        }

        public static boolean putConfigurationForUser(ContentResolver contentResolver, Configuration configuration, int i) {
            return putFloatForUser(contentResolver, FONT_SCALE, configuration.fontScale, i) && putStringForUser(contentResolver, SYSTEM_LOCALES, configuration.getLocales().toLanguageTags(), i, false);
        }

        @Deprecated
        public static boolean getShowGTalkServiceStatus(ContentResolver contentResolver) {
            return getShowGTalkServiceStatusForUser(contentResolver, contentResolver.getUserId());
        }

        @Deprecated
        public static boolean getShowGTalkServiceStatusForUser(ContentResolver contentResolver, int i) {
            return getIntForUser(contentResolver, SHOW_GTALK_SERVICE_STATUS, 0, i) != 0;
        }

        @Deprecated
        public static void setShowGTalkServiceStatus(ContentResolver contentResolver, boolean z) {
            setShowGTalkServiceStatusForUser(contentResolver, z, contentResolver.getUserId());
        }

        @Deprecated
        public static void setShowGTalkServiceStatusForUser(ContentResolver contentResolver, boolean z, int i) {
            putIntForUser(contentResolver, SHOW_GTALK_SERVICE_STATUS, z ? 1 : 0, i);
        }

        public static void getCloneToManagedProfileSettings(Set<String> set) {
            set.addAll(CLONE_TO_MANAGED_PROFILE);
        }

        public static void getCloneFromParentOnValueSettings(Map<String, String> map) {
            map.putAll(CLONE_FROM_PARENT_ON_VALUE);
        }

        public static boolean canWrite(Context context) {
            return Settings.isCallingPackageAllowedToWriteSettings(context, Process.myUid(), context.getOpPackageName(), false);
        }
    }

    public static final class Secure extends NameValueTable {
        public static final String ACCESSIBILITY_AUTOCLICK_DELAY = "accessibility_autoclick_delay";
        public static final String ACCESSIBILITY_AUTOCLICK_ENABLED = "accessibility_autoclick_enabled";
        public static final String ACCESSIBILITY_BUTTON_TARGETS = "accessibility_button_targets";
        public static final String ACCESSIBILITY_BUTTON_TARGET_COMPONENT = "accessibility_button_target_component";
        public static final String ACCESSIBILITY_CAPTIONING_BACKGROUND_COLOR = "accessibility_captioning_background_color";
        public static final String ACCESSIBILITY_CAPTIONING_EDGE_COLOR = "accessibility_captioning_edge_color";
        public static final String ACCESSIBILITY_CAPTIONING_EDGE_TYPE = "accessibility_captioning_edge_type";
        public static final String ACCESSIBILITY_CAPTIONING_ENABLED = "accessibility_captioning_enabled";
        public static final String ACCESSIBILITY_CAPTIONING_FONT_SCALE = "accessibility_captioning_font_scale";
        public static final String ACCESSIBILITY_CAPTIONING_FOREGROUND_COLOR = "accessibility_captioning_foreground_color";
        public static final String ACCESSIBILITY_CAPTIONING_LOCALE = "accessibility_captioning_locale";
        public static final String ACCESSIBILITY_CAPTIONING_PRESET = "accessibility_captioning_preset";
        public static final String ACCESSIBILITY_CAPTIONING_TYPEFACE = "accessibility_captioning_typeface";
        public static final String ACCESSIBILITY_CAPTIONING_WINDOW_COLOR = "accessibility_captioning_window_color";
        public static final String ACCESSIBILITY_DISPLAY_DALTONIZER = "accessibility_display_daltonizer";
        public static final String ACCESSIBILITY_DISPLAY_DALTONIZER_ENABLED = "accessibility_display_daltonizer_enabled";
        public static final String ACCESSIBILITY_DISPLAY_INVERSION_ENABLED = "accessibility_display_inversion_enabled";

        @Deprecated
        public static final String ACCESSIBILITY_DISPLAY_MAGNIFICATION_AUTO_UPDATE = "accessibility_display_magnification_auto_update";
        public static final String ACCESSIBILITY_DISPLAY_MAGNIFICATION_ENABLED = "accessibility_display_magnification_enabled";

        @SystemApi
        public static final String ACCESSIBILITY_DISPLAY_MAGNIFICATION_NAVBAR_ENABLED = "accessibility_display_magnification_navbar_enabled";
        public static final String ACCESSIBILITY_DISPLAY_MAGNIFICATION_SCALE = "accessibility_display_magnification_scale";
        public static final String ACCESSIBILITY_ENABLED = "accessibility_enabled";
        public static final String ACCESSIBILITY_HIGH_TEXT_CONTRAST_ENABLED = "high_text_contrast_enabled";
        public static final String ACCESSIBILITY_INTERACTIVE_UI_TIMEOUT_MS = "accessibility_interactive_ui_timeout_ms";
        public static final String ACCESSIBILITY_LARGE_POINTER_ICON = "accessibility_large_pointer_icon";
        public static final String ACCESSIBILITY_MAGNIFICATION_MODE = "accessibility_magnification_mode";
        public static final int ACCESSIBILITY_MAGNIFICATION_MODE_FULLSCREEN = 1;
        public static final int ACCESSIBILITY_MAGNIFICATION_MODE_WINDOW = 2;
        public static final String ACCESSIBILITY_NON_INTERACTIVE_UI_TIMEOUT_MS = "accessibility_non_interactive_ui_timeout_ms";
        public static final String ACCESSIBILITY_SHORTCUT_DIALOG_SHOWN = "accessibility_shortcut_dialog_shown";
        public static final String ACCESSIBILITY_SHORTCUT_ON_LOCK_SCREEN = "accessibility_shortcut_on_lock_screen";
        public static final String ACCESSIBILITY_SHORTCUT_TARGET_MAGNIFICATION_CONTROLLER = "com.android.server.accessibility.MagnificationController";
        public static final String ACCESSIBILITY_SHORTCUT_TARGET_SERVICE = "accessibility_shortcut_target_service";
        public static final String ACCESSIBILITY_SOFT_KEYBOARD_MODE = "accessibility_soft_keyboard_mode";

        @Deprecated
        public static final String ACCESSIBILITY_SPEAK_PASSWORD = "speak_password";
        public static final String ADAPTIVE_CONNECTIVITY_ENABLED = "adaptive_connectivity_enabled";
        public static final String ADAPTIVE_SLEEP = "adaptive_sleep";

        @Deprecated
        public static final String ADB_ENABLED = "adb_enabled";
        public static final String ALLOWED_GEOLOCATION_ORIGINS = "allowed_geolocation_origins";

        @Deprecated
        public static final String ALLOW_MOCK_LOCATION = "mock_location";
        public static final String ALWAYS_ON_VPN_APP = "always_on_vpn_app";
        public static final String ALWAYS_ON_VPN_LOCKDOWN = "always_on_vpn_lockdown";
        public static final String ALWAYS_ON_VPN_LOCKDOWN_WHITELIST = "always_on_vpn_lockdown_whitelist";
        public static final String ANDROID_ID = "android_id";
        public static final String ANR_SHOW_BACKGROUND = "anr_show_background";
        public static final String ASSISTANT = "assistant";
        public static final String ASSIST_DISCLOSURE_ENABLED = "assist_disclosure_enabled";
        public static final String ASSIST_GESTURE_ENABLED = "assist_gesture_enabled";
        public static final String ASSIST_GESTURE_SENSITIVITY = "assist_gesture_sensitivity";

        @SystemApi
        public static final String ASSIST_GESTURE_SETUP_COMPLETE = "assist_gesture_setup_complete";
        public static final String ASSIST_GESTURE_SILENCE_ALERTS_ENABLED = "assist_gesture_silence_alerts_enabled";
        public static final String ASSIST_GESTURE_WAKE_ENABLED = "assist_gesture_wake_enabled";
        public static final String ASSIST_HANDLES_LEARNING_EVENT_COUNT = "reminder_exp_learning_event_count";
        public static final String ASSIST_HANDLES_LEARNING_TIME_ELAPSED_MILLIS = "reminder_exp_learning_time_elapsed";
        public static final String ASSIST_SCREENSHOT_ENABLED = "assist_screenshot_enabled";
        public static final String ASSIST_STRUCTURE_ENABLED = "assist_structure_enabled";
        public static final String ATTENTIVE_TIMEOUT = "attentive_timeout";

        @SystemApi
        public static final String AUTOFILL_FEATURE_FIELD_CLASSIFICATION = "autofill_field_classification";
        public static final String AUTOFILL_SERVICE = "autofill_service";
        public static final String AUTOFILL_SERVICE_SEARCH_URI = "autofill_service_search_uri";

        @SystemApi
        public static final String AUTOFILL_USER_DATA_MAX_CATEGORY_COUNT = "autofill_user_data_max_category_count";

        @SystemApi
        public static final String AUTOFILL_USER_DATA_MAX_FIELD_CLASSIFICATION_IDS_SIZE = "autofill_user_data_max_field_classification_size";

        @SystemApi
        public static final String AUTOFILL_USER_DATA_MAX_USER_DATA_SIZE = "autofill_user_data_max_user_data_size";

        @SystemApi
        public static final String AUTOFILL_USER_DATA_MAX_VALUE_LENGTH = "autofill_user_data_max_value_length";

        @SystemApi
        public static final String AUTOFILL_USER_DATA_MIN_VALUE_LENGTH = "autofill_user_data_min_value_length";
        public static final String AUTOMATIC_STORAGE_MANAGER_BYTES_CLEARED = "automatic_storage_manager_bytes_cleared";
        public static final String AUTOMATIC_STORAGE_MANAGER_DAYS_TO_RETAIN = "automatic_storage_manager_days_to_retain";
        public static final int AUTOMATIC_STORAGE_MANAGER_DAYS_TO_RETAIN_DEFAULT = 90;
        public static final String AUTOMATIC_STORAGE_MANAGER_ENABLED = "automatic_storage_manager_enabled";
        public static final String AUTOMATIC_STORAGE_MANAGER_LAST_RUN = "automatic_storage_manager_last_run";
        public static final String AUTOMATIC_STORAGE_MANAGER_TURNED_OFF_BY_POLICY = "automatic_storage_manager_turned_off_by_policy";

        @SystemApi
        public static final String AUTO_REVOKE_DISABLED = "auto_revoke_disabled";
        public static final String AWARE_ENABLED = "aware_enabled";
        public static final String AWARE_LOCK_ENABLED = "aware_lock_enabled";
        public static final String AWARE_TAP_PAUSE_GESTURE_COUNT = "aware_tap_pause_gesture_count";
        public static final String AWARE_TAP_PAUSE_TOUCH_COUNT = "aware_tap_pause_touch_count";

        @Deprecated
        public static final String BACKGROUND_DATA = "background_data";
        public static final String BACKUP_AUTO_RESTORE = "backup_auto_restore";
        public static final String BACKUP_ENABLED = "backup_enabled";
        public static final String BACKUP_LOCAL_TRANSPORT_PARAMETERS = "backup_local_transport_parameters";
        public static final String BACKUP_MANAGER_CONSTANTS = "backup_manager_constants";
        public static final String BACKUP_PROVISIONED = "backup_provisioned";
        public static final String BACKUP_TRANSPORT = "backup_transport";
        public static final String BACK_GESTURE_INSET_SCALE_LEFT = "back_gesture_inset_scale_left";
        public static final String BACK_GESTURE_INSET_SCALE_RIGHT = "back_gesture_inset_scale_right";
        public static final String BIOMETRIC_DEBUG_ENABLED = "biometric_debug_enabled";

        @Deprecated
        public static final String BLUETOOTH_ON = "bluetooth_on";
        public static final String BLUETOOTH_ON_WHILE_DRIVING = "bluetooth_on_while_driving";
        public static final String BUBBLE_IMPORTANT_CONVERSATIONS = "bubble_important_conversations";

        @Deprecated
        public static final String BUGREPORT_IN_POWER_MENU = "bugreport_in_power_menu";
        public static final String CALL_SCREENING_DEFAULT_COMPONENT = "call_screening_default_component";
        public static final String CAMERA_DOUBLE_TAP_POWER_GESTURE_DISABLED = "camera_double_tap_power_gesture_disabled";
        public static final String CAMERA_DOUBLE_TWIST_TO_FLIP_ENABLED = "camera_double_twist_to_flip_enabled";
        public static final String CAMERA_GESTURE_DISABLED = "camera_gesture_disabled";
        public static final String CAMERA_LIFT_TRIGGER_ENABLED = "camera_lift_trigger_enabled";
        public static final int CAMERA_LIFT_TRIGGER_ENABLED_DEFAULT = 1;
        public static final String CARRIER_APPS_HANDLED = "carrier_apps_handled";
        public static final String CHARGING_SOUNDS_ENABLED = "charging_sounds_enabled";
        public static final String CHARGING_VIBRATION_ENABLED = "charging_vibration_enabled";
        private static final Set<String> CLONE_TO_MANAGED_PROFILE;
        public static final String CMAS_ADDITIONAL_BROADCAST_PKG = "cmas_additional_broadcast_pkg";

        @SystemApi
        public static final String COMPLETED_CATEGORY_PREFIX = "suggested.completed_category.";
        public static final String CONNECTIVITY_RELEASE_PENDING_INTENT_DELAY_MS = "connectivity_release_pending_intent_delay_ms";
        public static final String CONTENT_CAPTURE_ENABLED = "content_capture_enabled";
        public static final String CONTROLS_ENABLED = "controls_enabled";
        public static final String CROSS_PROFILE_CALENDAR_ENABLED = "cross_profile_calendar_enabled";
        public static final String DARK_MODE_DIALOG_SEEN = "dark_mode_dialog_seen";
        public static final String DARK_THEME_CUSTOM_END_TIME = "dark_theme_custom_end_time";
        public static final String DARK_THEME_CUSTOM_START_TIME = "dark_theme_custom_start_time";

        @Deprecated
        public static final String DATA_ROAMING = "data_roaming";
        public static final String DEFAULT_INPUT_METHOD = "default_input_method";

        @Deprecated
        public static final String DEVELOPMENT_SETTINGS_ENABLED = "development_settings_enabled";
        public static final String DEVICE_PAIRED = "device_paired";

        @Deprecated
        public static final String DEVICE_PROVISIONED = "device_provisioned";
        public static final String DIALER_DEFAULT_APPLICATION = "dialer_default_application";
        public static final String DISABLED_PRINT_SERVICES = "disabled_print_services";
        public static final String DISABLED_SYSTEM_INPUT_METHODS = "disabled_system_input_methods";
        public static final String DISPLAY_DENSITY_FORCED = "display_density_forced";
        public static final String DISPLAY_WHITE_BALANCE_ENABLED = "display_white_balance_enabled";
        public static final String DOCKED_CLOCK_FACE = "docked_clock_face";
        public static final String DOUBLE_TAP_TO_WAKE = "double_tap_to_wake";

        @SystemApi
        public static final String DOZE_ALWAYS_ON = "doze_always_on";
        public static final String DOZE_DOUBLE_TAP_GESTURE = "doze_pulse_on_double_tap";
        public static final String DOZE_ENABLED = "doze_enabled";
        public static final String DOZE_PICK_UP_GESTURE = "doze_pulse_on_pick_up";
        public static final String DOZE_PULSE_ON_LONG_PRESS = "doze_pulse_on_long_press";
        public static final String DOZE_TAP_SCREEN_GESTURE = "doze_tap_gesture";
        public static final String DOZE_WAKE_DISPLAY_GESTURE = "doze_wake_display_gesture";
        public static final String DOZE_WAKE_LOCK_SCREEN_GESTURE = "doze_wake_screen_gesture";
        public static final String EMERGENCY_ASSISTANCE_APPLICATION = "emergency_assistance_application";
        public static final String ENABLED_ACCESSIBILITY_SERVICES = "enabled_accessibility_services";
        public static final String ENABLED_INPUT_METHODS = "enabled_input_methods";

        @Deprecated
        public static final String ENABLED_NOTIFICATION_ASSISTANT = "enabled_notification_assistant";

        @Deprecated
        public static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";

        @Deprecated
        public static final String ENABLED_NOTIFICATION_POLICY_ACCESS_PACKAGES = "enabled_notification_policy_access_packages";
        public static final String ENABLED_PRINT_SERVICES = "enabled_print_services";
        public static final String ENABLED_VR_LISTENERS = "enabled_vr_listeners";
        public static final String ENHANCED_VOICE_PRIVACY_ENABLED = "enhanced_voice_privacy_enabled";
        public static final String FACE_UNLOCK_ALWAYS_REQUIRE_CONFIRMATION = "face_unlock_always_require_confirmation";
        public static final String FACE_UNLOCK_APP_ENABLED = "face_unlock_app_enabled";
        public static final String FACE_UNLOCK_ATTENTION_REQUIRED = "face_unlock_attention_required";
        public static final String FACE_UNLOCK_DISMISSES_KEYGUARD = "face_unlock_dismisses_keyguard";
        public static final String FACE_UNLOCK_DIVERSITY_REQUIRED = "face_unlock_diversity_required";
        public static final String FACE_UNLOCK_KEYGUARD_ENABLED = "face_unlock_keyguard_enabled";
        public static final String FACE_UNLOCK_RE_ENROLL = "face_unlock_re_enroll";
        public static final String FLASHLIGHT_AVAILABLE = "flashlight_available";
        public static final String FLASHLIGHT_ENABLED = "flashlight_enabled";
        public static final String GLOBAL_ACTIONS_PANEL_AVAILABLE = "global_actions_panel_available";
        public static final String GLOBAL_ACTIONS_PANEL_DEBUG_ENABLED = "global_actions_panel_debug_enabled";
        public static final String GLOBAL_ACTIONS_PANEL_ENABLED = "global_actions_panel_enabled";

        @Deprecated
        public static final String HTTP_PROXY = "http_proxy";

        @SystemApi
        public static final String HUSH_GESTURE_USED = "hush_gesture_used";
        public static final String IMMERSIVE_MODE_CONFIRMATIONS = "immersive_mode_confirmations";
        public static final String INCALL_BACK_BUTTON_BEHAVIOR = "incall_back_button_behavior";
        public static final int INCALL_BACK_BUTTON_BEHAVIOR_DEFAULT = 0;
        public static final int INCALL_BACK_BUTTON_BEHAVIOR_HANGUP = 1;
        public static final int INCALL_BACK_BUTTON_BEHAVIOR_NONE = 0;
        public static final String INCALL_POWER_BUTTON_BEHAVIOR = "incall_power_button_behavior";
        public static final int INCALL_POWER_BUTTON_BEHAVIOR_DEFAULT = 1;
        public static final int INCALL_POWER_BUTTON_BEHAVIOR_HANGUP = 2;
        public static final int INCALL_POWER_BUTTON_BEHAVIOR_SCREEN_OFF = 1;
        public static final String INPUT_METHODS_SUBTYPE_HISTORY = "input_methods_subtype_history";
        public static final String INPUT_METHOD_SELECTOR_VISIBILITY = "input_method_selector_visibility";
        public static final String INSTALL_NON_MARKET_APPS = "install_non_market_apps";

        @SystemApi
        public static final String INSTANT_APPS_ENABLED = "instant_apps_enabled";
        public static final Set<String> INSTANT_APP_SETTINGS;
        public static final String IN_CALL_NOTIFICATION_ENABLED = "in_call_notification_enabled";
        public static final String KEYGUARD_SLICE_URI = "keyguard_slice_uri";

        @SystemApi
        public static final String LAST_SETUP_SHOWN = "last_setup_shown";
        public static final String[] LEGACY_RESTORE_SETTINGS;

        @SystemApi
        public static final String LOCATION_ACCESS_CHECK_DELAY_MILLIS = "location_access_check_delay_millis";

        @SystemApi
        public static final String LOCATION_ACCESS_CHECK_INTERVAL_MILLIS = "location_access_check_interval_millis";
        public static final String LOCATION_CHANGER = "location_changer";
        public static final int LOCATION_CHANGER_QUICK_SETTINGS = 2;
        public static final int LOCATION_CHANGER_SYSTEM_SETTINGS = 1;
        public static final int LOCATION_CHANGER_UNKNOWN = 0;
        public static final String LOCATION_COARSE_ACCURACY_M = "locationCoarseAccuracy";

        @Deprecated
        public static final String LOCATION_MODE = "location_mode";

        @Deprecated
        public static final int LOCATION_MODE_BATTERY_SAVING = 2;

        @Deprecated
        public static final int LOCATION_MODE_HIGH_ACCURACY = 3;
        public static final int LOCATION_MODE_OFF = 0;

        @SystemApi
        public static final int LOCATION_MODE_ON = 3;

        @Deprecated
        public static final int LOCATION_MODE_SENSORS_ONLY = 1;

        @SystemApi
        @Deprecated
        public static final String LOCATION_PERMISSIONS_UPGRADE_TO_Q_MODE = "location_permissions_upgrade_to_q_mode";

        @Deprecated
        public static final String LOCATION_PROVIDERS_ALLOWED = "location_providers_allowed";
        public static final String LOCKDOWN_IN_POWER_MENU = "lockdown_in_power_menu";

        @Deprecated
        public static final String LOCK_BIOMETRIC_WEAK_FLAGS = "lock_biometric_weak_flags";

        @Deprecated
        public static final String LOCK_PATTERN_ENABLED = "lock_pattern_autolock";

        @Deprecated
        public static final String LOCK_PATTERN_TACTILE_FEEDBACK_ENABLED = "lock_pattern_tactile_feedback_enabled";

        @Deprecated
        public static final String LOCK_PATTERN_VISIBLE = "lock_pattern_visible_pattern";

        @SystemApi
        public static final String LOCK_SCREEN_ALLOW_PRIVATE_NOTIFICATIONS = "lock_screen_allow_private_notifications";
        public static final String LOCK_SCREEN_ALLOW_REMOTE_INPUT = "lock_screen_allow_remote_input";

        @Deprecated
        public static final String LOCK_SCREEN_APPWIDGET_IDS = "lock_screen_appwidget_ids";
        public static final String LOCK_SCREEN_CUSTOM_CLOCK_FACE = "lock_screen_custom_clock_face";

        @Deprecated
        public static final String LOCK_SCREEN_FALLBACK_APPWIDGET_ID = "lock_screen_fallback_appwidget_id";
        public static final String LOCK_SCREEN_LOCK_AFTER_TIMEOUT = "lock_screen_lock_after_timeout";

        @Deprecated
        public static final String LOCK_SCREEN_OWNER_INFO = "lock_screen_owner_info";

        @Deprecated
        public static final String LOCK_SCREEN_OWNER_INFO_ENABLED = "lock_screen_owner_info_enabled";

        @SystemApi
        public static final String LOCK_SCREEN_SHOW_NOTIFICATIONS = "lock_screen_show_notifications";
        public static final String LOCK_SCREEN_SHOW_SILENT_NOTIFICATIONS = "lock_screen_show_silent_notifications";

        @Deprecated
        public static final String LOCK_SCREEN_STICKY_APPWIDGET = "lock_screen_sticky_appwidget";
        public static final String LOCK_SCREEN_WHEN_TRUST_LOST = "lock_screen_when_trust_lost";
        public static final String LOCK_TO_APP_EXIT_LOCKED = "lock_to_app_exit_locked";

        @Deprecated
        public static final String LOGGING_ID = "logging_id";
        public static final String LONG_PRESS_TIMEOUT = "long_press_timeout";
        public static final String LOW_POWER_MANUAL_ACTIVATION_COUNT = "low_power_manual_activation_count";
        public static final String LOW_POWER_WARNING_ACKNOWLEDGED = "low_power_warning_acknowledged";
        public static final String MANAGED_PROFILE_CONTACT_REMOTE_SEARCH = "managed_profile_contact_remote_search";
        public static final String MANAGED_PROVISIONING_DPC_DOWNLOADED = "managed_provisioning_dpc_downloaded";
        public static final String MANUAL_RINGER_TOGGLE_COUNT = "manual_ringer_toggle_count";
        public static final String MEDIA_CONTROLS_RESUME = "qs_media_resumption";
        public static final String MINIMAL_POST_PROCESSING_ALLOWED = "minimal_post_processing_allowed";
        public static final String MOUNT_PLAY_NOTIFICATION_SND = "mount_play_not_snd";
        public static final String MOUNT_UMS_AUTOSTART = "mount_ums_autostart";
        public static final String MOUNT_UMS_NOTIFY_ENABLED = "mount_ums_notify_enabled";
        public static final String MOUNT_UMS_PROMPT = "mount_ums_prompt";
        private static final HashSet<String> MOVED_TO_GLOBAL;
        private static final HashSet<String> MOVED_TO_LOCK_SETTINGS;
        public static final String MULTI_PRESS_TIMEOUT = "multi_press_timeout";
        public static final String NAVIGATION_MODE = "navigation_mode";
        public static final String NEARBY_SHARING_COMPONENT = "nearby_sharing_component";

        @Deprecated
        public static final String NETWORK_PREFERENCE = "network_preference";
        public static final String NFC_PAYMENT_DEFAULT_COMPONENT = "nfc_payment_default_component";
        public static final String NFC_PAYMENT_FOREGROUND = "nfc_payment_foreground";
        public static final String NIGHT_DISPLAY_ACTIVATED = "night_display_activated";
        public static final String NIGHT_DISPLAY_AUTO_MODE = "night_display_auto_mode";
        public static final String NIGHT_DISPLAY_COLOR_TEMPERATURE = "night_display_color_temperature";
        public static final String NIGHT_DISPLAY_CUSTOM_END_TIME = "night_display_custom_end_time";
        public static final String NIGHT_DISPLAY_CUSTOM_START_TIME = "night_display_custom_start_time";
        public static final String NIGHT_DISPLAY_LAST_ACTIVATED_TIME = "night_display_last_activated_time";
        public static final String NOTIFICATION_BADGING = "notification_badging";
        public static final String NOTIFICATION_DISMISS_RTL = "notification_dismiss_rtl";
        public static final String NOTIFICATION_HISTORY_ENABLED = "notification_history_enabled";
        public static final String NOTIFICATION_NEW_INTERRUPTION_MODEL = "new_interruption_model";
        public static final String NUM_ROTATION_SUGGESTIONS_ACCEPTED = "num_rotation_suggestions_accepted";

        @SystemApi
        public static final String ODI_CAPTIONS_ENABLED = "odi_captions_enabled";
        public static final String PACKAGES_TO_CLEAR_DATA_BEFORE_FULL_RESTORE = "packages_to_clear_data_before_full_restore";
        public static final String PARENTAL_CONTROL_ENABLED = "parental_control_enabled";
        public static final String PARENTAL_CONTROL_LAST_UPDATE = "parental_control_last_update";
        public static final String PARENTAL_CONTROL_REDIRECT_URL = "parental_control_redirect_url";
        public static final String PAYMENT_SERVICE_SEARCH_URI = "payment_service_search_uri";
        public static final String PEOPLE_STRIP = "people_strip";
        public static final String POWER_MENU_LOCKED_SHOW_CONTENT = "power_menu_locked_show_content";
        public static final String PREFERRED_TTY_MODE = "preferred_tty_mode";
        public static final String PRINT_SERVICE_SEARCH_URI = "print_service_search_uri";
        public static final String QS_AUTO_ADDED_TILES = "qs_auto_tiles";
        public static final String QS_TILES = "sysui_qs_tiles";
        public static final String RTT_CALLING_MODE = "rtt_calling_mode";
        public static final String SCREENSAVER_ACTIVATE_ON_DOCK = "screensaver_activate_on_dock";
        public static final String SCREENSAVER_ACTIVATE_ON_SLEEP = "screensaver_activate_on_sleep";
        public static final String SCREENSAVER_COMPONENTS = "screensaver_components";
        public static final String SCREENSAVER_DEFAULT_COMPONENT = "screensaver_default_component";
        public static final String SCREENSAVER_ENABLED = "screensaver_enabled";
        public static final String SEARCH_GLOBAL_SEARCH_ACTIVITY = "search_global_search_activity";
        public static final String SEARCH_MAX_RESULTS_PER_SOURCE = "search_max_results_per_source";
        public static final String SEARCH_MAX_RESULTS_TO_DISPLAY = "search_max_results_to_display";
        public static final String SEARCH_MAX_SHORTCUTS_RETURNED = "search_max_shortcuts_returned";
        public static final String SEARCH_MAX_SOURCE_EVENT_AGE_MILLIS = "search_max_source_event_age_millis";
        public static final String SEARCH_MAX_STAT_AGE_MILLIS = "search_max_stat_age_millis";
        public static final String SEARCH_MIN_CLICKS_FOR_SOURCE_RANKING = "search_min_clicks_for_source_ranking";
        public static final String SEARCH_MIN_IMPRESSIONS_FOR_SOURCE_RANKING = "search_min_impressions_for_source_ranking";
        public static final String SEARCH_NUM_PROMOTED_SOURCES = "search_num_promoted_sources";
        public static final String SEARCH_PER_SOURCE_CONCURRENT_QUERY_LIMIT = "search_per_source_concurrent_query_limit";
        public static final String SEARCH_PREFILL_MILLIS = "search_prefill_millis";
        public static final String SEARCH_PROMOTED_SOURCE_DEADLINE_MILLIS = "search_promoted_source_deadline_millis";
        public static final String SEARCH_QUERY_THREAD_CORE_POOL_SIZE = "search_query_thread_core_pool_size";
        public static final String SEARCH_QUERY_THREAD_MAX_POOL_SIZE = "search_query_thread_max_pool_size";
        public static final String SEARCH_SHORTCUT_REFRESH_CORE_POOL_SIZE = "search_shortcut_refresh_core_pool_size";
        public static final String SEARCH_SHORTCUT_REFRESH_MAX_POOL_SIZE = "search_shortcut_refresh_max_pool_size";
        public static final String SEARCH_SOURCE_TIMEOUT_MILLIS = "search_source_timeout_millis";
        public static final String SEARCH_THREAD_KEEPALIVE_SECONDS = "search_thread_keepalive_seconds";
        public static final String SEARCH_WEB_RESULTS_OVERRIDE_LIMIT = "search_web_results_override_limit";
        public static final String SECURE_FRP_MODE = "secure_frp_mode";
        public static final String SELECTED_INPUT_METHOD_SUBTYPE = "selected_input_method_subtype";
        public static final String SELECTED_SPELL_CHECKER = "selected_spell_checker";
        public static final String SELECTED_SPELL_CHECKER_SUBTYPE = "selected_spell_checker_subtype";
        public static final String SETTINGS_CLASSNAME = "settings_classname";
        public static final String SHOW_FIRST_CRASH_DIALOG_DEV_OPTION = "show_first_crash_dialog_dev_option";
        public static final String SHOW_IME_WITH_HARD_KEYBOARD = "show_ime_with_hard_keyboard";
        public static final String SHOW_MEDIA_WHEN_BYPASSING = "show_media_when_bypassing";
        public static final int SHOW_MODE_AUTO = 0;
        public static final int SHOW_MODE_HIDDEN = 1;
        public static final String SHOW_NOTE_ABOUT_NOTIFICATION_HIDING = "show_note_about_notification_hiding";
        public static final String SHOW_NOTIFICATION_SNOOZE = "show_notification_snooze";
        public static final String SHOW_ROTATION_SUGGESTIONS = "show_rotation_suggestions";
        public static final int SHOW_ROTATION_SUGGESTIONS_DEFAULT = 1;
        public static final int SHOW_ROTATION_SUGGESTIONS_DISABLED = 0;
        public static final int SHOW_ROTATION_SUGGESTIONS_ENABLED = 1;
        public static final String SHOW_ZEN_SETTINGS_SUGGESTION = "show_zen_settings_suggestion";
        public static final String SHOW_ZEN_UPGRADE_NOTIFICATION = "show_zen_upgrade_notification";
        public static final String SILENCE_ALARMS_GESTURE_COUNT = "silence_alarms_gesture_count";
        public static final String SILENCE_ALARMS_TOUCH_COUNT = "silence_alarms_touch_count";
        public static final String SILENCE_CALL_GESTURE_COUNT = "silence_call_gesture_count";
        public static final String SILENCE_CALL_TOUCH_COUNT = "silence_call_touch_count";
        public static final String SILENCE_GESTURE = "silence_gesture";
        public static final String SILENCE_TIMER_GESTURE_COUNT = "silence_timer_gesture_count";
        public static final String SILENCE_TIMER_TOUCH_COUNT = "silence_timer_touch_count";
        public static final String SKIP_DIRECTION = "skip_gesture_direction";
        public static final String SKIP_FIRST_USE_HINTS = "skip_first_use_hints";
        public static final String SKIP_GESTURE = "skip_gesture";
        public static final String SKIP_GESTURE_COUNT = "skip_gesture_count";
        public static final String SKIP_TOUCH_COUNT = "skip_touch_count";
        public static final String SLEEP_TIMEOUT = "sleep_timeout";
        public static final String SMS_DEFAULT_APPLICATION = "sms_default_application";
        public static final String SPELL_CHECKER_ENABLED = "spell_checker_enabled";
        public static final String SUPPRESS_AUTO_BATTERY_SAVER_SUGGESTION = "suppress_auto_battery_saver_suggestion";
        public static final String SUPPRESS_DOZE = "suppress_doze";
        public static final String SYNC_PARENT_SOUNDS = "sync_parent_sounds";
        public static final String SYSTEM_NAVIGATION_KEYS_ENABLED = "system_navigation_keys_enabled";
        public static final String TAP_GESTURE = "tap_gesture";

        @SystemApi
        public static final String THEME_CUSTOMIZATION_OVERLAY_PACKAGES = "theme_customization_overlay_packages";
        public static final String TOUCH_EXPLORATION_ENABLED = "touch_exploration_enabled";
        public static final String TOUCH_EXPLORATION_GRANTED_ACCESSIBILITY_SERVICES = "touch_exploration_granted_accessibility_services";
        public static final String TRUST_AGENTS_EXTEND_UNLOCK = "trust_agents_extend_unlock";
        public static final String TRUST_AGENTS_INITIALIZED = "trust_agents_initialized";

        @Deprecated
        public static final String TTS_DEFAULT_COUNTRY = "tts_default_country";

        @Deprecated
        public static final String TTS_DEFAULT_LANG = "tts_default_lang";
        public static final String TTS_DEFAULT_LOCALE = "tts_default_locale";
        public static final String TTS_DEFAULT_PITCH = "tts_default_pitch";
        public static final String TTS_DEFAULT_RATE = "tts_default_rate";
        public static final String TTS_DEFAULT_SYNTH = "tts_default_synth";

        @Deprecated
        public static final String TTS_DEFAULT_VARIANT = "tts_default_variant";
        public static final String TTS_ENABLED_PLUGINS = "tts_enabled_plugins";

        @Deprecated
        public static final String TTS_USE_DEFAULTS = "tts_use_defaults";
        public static final String TTY_MODE_ENABLED = "tty_mode_enabled";
        public static final String TV_APP_USES_NON_SYSTEM_INPUTS = "tv_app_uses_non_system_inputs";
        public static final String TV_INPUT_CUSTOM_LABELS = "tv_input_custom_labels";
        public static final String TV_INPUT_HIDDEN_INPUTS = "tv_input_hidden_inputs";
        public static final String TV_USER_SETUP_COMPLETE = "tv_user_setup_complete";
        public static final String UI_NIGHT_MODE = "ui_night_mode";
        public static final String UI_NIGHT_MODE_LAST_COMPUTED = "ui_night_mode_last_computed";
        public static final String UI_NIGHT_MODE_OVERRIDE_OFF = "ui_night_mode_override_off";
        public static final String UI_NIGHT_MODE_OVERRIDE_ON = "ui_night_mode_override_on";
        public static final String UNKNOWN_SOURCES_DEFAULT_REVERSED = "unknown_sources_default_reversed";
        public static final String UNSAFE_VOLUME_MUSIC_ACTIVE_MS = "unsafe_volume_music_active_ms";
        public static final String USB_AUDIO_AUTOMATIC_ROUTING_DISABLED = "usb_audio_automatic_routing_disabled";

        @Deprecated
        public static final String USB_MASS_STORAGE_ENABLED = "usb_mass_storage_enabled";

        @SystemApi
        public static final String USER_SETUP_COMPLETE = "user_setup_complete";

        @SystemApi
        public static final int USER_SETUP_PERSONALIZATION_COMPLETE = 10;

        @SystemApi
        public static final int USER_SETUP_PERSONALIZATION_NOT_STARTED = 0;

        @SystemApi
        public static final int USER_SETUP_PERSONALIZATION_PAUSED = 2;

        @SystemApi
        public static final int USER_SETUP_PERSONALIZATION_STARTED = 1;

        @SystemApi
        public static final String USER_SETUP_PERSONALIZATION_STATE = "user_setup_personalization_state";

        @Deprecated
        public static final String USE_GOOGLE_MAIL = "use_google_mail";
        public static final String VOICE_INTERACTION_SERVICE = "voice_interaction_service";
        public static final String VOICE_RECOGNITION_SERVICE = "voice_recognition_service";

        @SystemApi
        public static final String VOLUME_HUSH_GESTURE = "volume_hush_gesture";

        @SystemApi
        public static final int VOLUME_HUSH_MUTE = 2;

        @SystemApi
        public static final int VOLUME_HUSH_OFF = 0;

        @SystemApi
        public static final int VOLUME_HUSH_VIBRATE = 1;
        public static final String VR_DISPLAY_MODE = "vr_display_mode";
        public static final int VR_DISPLAY_MODE_LOW_PERSISTENCE = 0;
        public static final int VR_DISPLAY_MODE_OFF = 1;
        public static final String WAKE_GESTURE_ENABLED = "wake_gesture_enabled";

        @Deprecated
        public static final String WIFI_IDLE_MS = "wifi_idle_ms";

        @Deprecated
        public static final String WIFI_MAX_DHCP_RETRY_COUNT = "wifi_max_dhcp_retry_count";

        @Deprecated
        public static final String WIFI_MOBILE_DATA_TRANSITION_WAKELOCK_TIMEOUT_MS = "wifi_mobile_data_transition_wakelock_timeout_ms";

        @Deprecated
        public static final String WIFI_NETWORKS_AVAILABLE_NOTIFICATION_ON = "wifi_networks_available_notification_on";

        @Deprecated
        public static final String WIFI_NETWORKS_AVAILABLE_REPEAT_DELAY = "wifi_networks_available_repeat_delay";

        @Deprecated
        public static final String WIFI_NUM_OPEN_NETWORKS_KEPT = "wifi_num_open_networks_kept";

        @Deprecated
        public static final String WIFI_ON = "wifi_on";

        @Deprecated
        public static final String WIFI_WATCHDOG_ACCEPTABLE_PACKET_LOSS_PERCENTAGE = "wifi_watchdog_acceptable_packet_loss_percentage";

        @Deprecated
        public static final String WIFI_WATCHDOG_AP_COUNT = "wifi_watchdog_ap_count";

        @Deprecated
        public static final String WIFI_WATCHDOG_BACKGROUND_CHECK_DELAY_MS = "wifi_watchdog_background_check_delay_ms";

        @Deprecated
        public static final String WIFI_WATCHDOG_BACKGROUND_CHECK_ENABLED = "wifi_watchdog_background_check_enabled";

        @Deprecated
        public static final String WIFI_WATCHDOG_BACKGROUND_CHECK_TIMEOUT_MS = "wifi_watchdog_background_check_timeout_ms";

        @Deprecated
        public static final String WIFI_WATCHDOG_INITIAL_IGNORED_PING_COUNT = "wifi_watchdog_initial_ignored_ping_count";

        @Deprecated
        public static final String WIFI_WATCHDOG_MAX_AP_CHECKS = "wifi_watchdog_max_ap_checks";

        @Deprecated
        public static final String WIFI_WATCHDOG_ON = "wifi_watchdog_on";

        @Deprecated
        public static final String WIFI_WATCHDOG_PING_COUNT = "wifi_watchdog_ping_count";

        @Deprecated
        public static final String WIFI_WATCHDOG_PING_DELAY_MS = "wifi_watchdog_ping_delay_ms";

        @Deprecated
        public static final String WIFI_WATCHDOG_PING_TIMEOUT_MS = "wifi_watchdog_ping_timeout_ms";

        @Deprecated
        public static final String WIFI_WATCHDOG_WATCH_LIST = "wifi_watchdog_watch_list";
        public static final String WINDOW_MAGNIFICATION = "window_magnification";
        public static final String ZEN_DURATION = "zen_duration";
        public static final int ZEN_DURATION_FOREVER = 0;
        public static final int ZEN_DURATION_PROMPT = -1;
        public static final String ZEN_SETTINGS_SUGGESTION_VIEWED = "zen_settings_suggestion_viewed";
        public static final String ZEN_SETTINGS_UPDATED = "zen_settings_updated";
        private static boolean sIsSystemProcess;
        public static final Uri CONTENT_URI = Uri.parse("content://settings/secure");
        private static final ContentProviderHolder sProviderHolder = new ContentProviderHolder(CONTENT_URI);
        private static final NameValueCache sNameValueCache = new NameValueCache(CONTENT_URI, Settings.CALL_METHOD_GET_SECURE, Settings.CALL_METHOD_PUT_SECURE, sProviderHolder);
        private static ILockSettings sLockSettings = null;

        @Retention(RetentionPolicy.SOURCE)
        public @interface UserSetupPersonalization {
        }

        @Deprecated
        public static void setLocationProviderEnabled(ContentResolver contentResolver, String str, boolean z) {
        }

        static {
            HashSet<String> hashSet = new HashSet<>(3);
            MOVED_TO_LOCK_SETTINGS = hashSet;
            hashSet.add("lock_pattern_autolock");
            MOVED_TO_LOCK_SETTINGS.add("lock_pattern_visible_pattern");
            MOVED_TO_LOCK_SETTINGS.add("lock_pattern_tactile_feedback_enabled");
            HashSet<String> hashSet2 = new HashSet<>();
            MOVED_TO_GLOBAL = hashSet2;
            hashSet2.add("adb_enabled");
            MOVED_TO_GLOBAL.add(Global.ASSISTED_GPS_ENABLED);
            MOVED_TO_GLOBAL.add("bluetooth_on");
            MOVED_TO_GLOBAL.add("bugreport_in_power_menu");
            MOVED_TO_GLOBAL.add(Global.CDMA_CELL_BROADCAST_SMS);
            MOVED_TO_GLOBAL.add(Global.CDMA_ROAMING_MODE);
            MOVED_TO_GLOBAL.add(Global.CDMA_SUBSCRIPTION_MODE);
            MOVED_TO_GLOBAL.add(Global.DATA_ACTIVITY_TIMEOUT_MOBILE);
            MOVED_TO_GLOBAL.add(Global.DATA_ACTIVITY_TIMEOUT_WIFI);
            MOVED_TO_GLOBAL.add("data_roaming");
            MOVED_TO_GLOBAL.add("development_settings_enabled");
            MOVED_TO_GLOBAL.add("device_provisioned");
            MOVED_TO_GLOBAL.add(Global.DISPLAY_SIZE_FORCED);
            MOVED_TO_GLOBAL.add(Global.DOWNLOAD_MAX_BYTES_OVER_MOBILE);
            MOVED_TO_GLOBAL.add(Global.DOWNLOAD_RECOMMENDED_MAX_BYTES_OVER_MOBILE);
            MOVED_TO_GLOBAL.add(Global.MOBILE_DATA);
            MOVED_TO_GLOBAL.add(Global.NETSTATS_DEV_BUCKET_DURATION);
            MOVED_TO_GLOBAL.add(Global.NETSTATS_DEV_DELETE_AGE);
            MOVED_TO_GLOBAL.add(Global.NETSTATS_DEV_PERSIST_BYTES);
            MOVED_TO_GLOBAL.add(Global.NETSTATS_DEV_ROTATE_AGE);
            MOVED_TO_GLOBAL.add(Global.NETSTATS_ENABLED);
            MOVED_TO_GLOBAL.add(Global.NETSTATS_GLOBAL_ALERT_BYTES);
            MOVED_TO_GLOBAL.add(Global.NETSTATS_POLL_INTERVAL);
            MOVED_TO_GLOBAL.add(Global.NETSTATS_SAMPLE_ENABLED);
            MOVED_TO_GLOBAL.add(Global.NETSTATS_TIME_CACHE_MAX_AGE);
            MOVED_TO_GLOBAL.add(Global.NETSTATS_UID_BUCKET_DURATION);
            MOVED_TO_GLOBAL.add(Global.NETSTATS_UID_DELETE_AGE);
            MOVED_TO_GLOBAL.add(Global.NETSTATS_UID_PERSIST_BYTES);
            MOVED_TO_GLOBAL.add(Global.NETSTATS_UID_ROTATE_AGE);
            MOVED_TO_GLOBAL.add(Global.NETSTATS_UID_TAG_BUCKET_DURATION);
            MOVED_TO_GLOBAL.add(Global.NETSTATS_UID_TAG_DELETE_AGE);
            MOVED_TO_GLOBAL.add(Global.NETSTATS_UID_TAG_PERSIST_BYTES);
            MOVED_TO_GLOBAL.add(Global.NETSTATS_UID_TAG_ROTATE_AGE);
            MOVED_TO_GLOBAL.add("network_preference");
            MOVED_TO_GLOBAL.add(Global.NITZ_UPDATE_DIFF);
            MOVED_TO_GLOBAL.add(Global.NITZ_UPDATE_SPACING);
            MOVED_TO_GLOBAL.add(Global.NTP_SERVER);
            MOVED_TO_GLOBAL.add(Global.NTP_TIMEOUT);
            MOVED_TO_GLOBAL.add(Global.PDP_WATCHDOG_ERROR_POLL_COUNT);
            MOVED_TO_GLOBAL.add(Global.PDP_WATCHDOG_LONG_POLL_INTERVAL_MS);
            MOVED_TO_GLOBAL.add(Global.PDP_WATCHDOG_MAX_PDP_RESET_FAIL_COUNT);
            MOVED_TO_GLOBAL.add(Global.PDP_WATCHDOG_POLL_INTERVAL_MS);
            MOVED_TO_GLOBAL.add(Global.PDP_WATCHDOG_TRIGGER_PACKET_COUNT);
            MOVED_TO_GLOBAL.add(Global.SETUP_PREPAID_DATA_SERVICE_URL);
            MOVED_TO_GLOBAL.add(Global.SETUP_PREPAID_DETECTION_REDIR_HOST);
            MOVED_TO_GLOBAL.add(Global.SETUP_PREPAID_DETECTION_TARGET_URL);
            MOVED_TO_GLOBAL.add(Global.TETHER_DUN_APN);
            MOVED_TO_GLOBAL.add(Global.TETHER_DUN_REQUIRED);
            MOVED_TO_GLOBAL.add(Global.TETHER_SUPPORTED);
            MOVED_TO_GLOBAL.add("usb_mass_storage_enabled");
            MOVED_TO_GLOBAL.add("use_google_mail");
            MOVED_TO_GLOBAL.add(Global.WIFI_COUNTRY_CODE);
            MOVED_TO_GLOBAL.add(Global.WIFI_FRAMEWORK_SCAN_INTERVAL_MS);
            MOVED_TO_GLOBAL.add(Global.WIFI_FREQUENCY_BAND);
            MOVED_TO_GLOBAL.add("wifi_idle_ms");
            MOVED_TO_GLOBAL.add("wifi_max_dhcp_retry_count");
            MOVED_TO_GLOBAL.add("wifi_mobile_data_transition_wakelock_timeout_ms");
            MOVED_TO_GLOBAL.add("wifi_networks_available_notification_on");
            MOVED_TO_GLOBAL.add("wifi_networks_available_repeat_delay");
            MOVED_TO_GLOBAL.add("wifi_num_open_networks_kept");
            MOVED_TO_GLOBAL.add("wifi_on");
            MOVED_TO_GLOBAL.add(Global.WIFI_P2P_DEVICE_NAME);
            MOVED_TO_GLOBAL.add(Global.WIFI_SUPPLICANT_SCAN_INTERVAL_MS);
            MOVED_TO_GLOBAL.add(Global.WIFI_VERBOSE_LOGGING_ENABLED);
            MOVED_TO_GLOBAL.add(Global.WIFI_ENHANCED_AUTO_JOIN);
            MOVED_TO_GLOBAL.add(Global.WIFI_NETWORK_SHOW_RSSI);
            MOVED_TO_GLOBAL.add("wifi_watchdog_on");
            MOVED_TO_GLOBAL.add(Global.WIFI_WATCHDOG_POOR_NETWORK_TEST_ENABLED);
            MOVED_TO_GLOBAL.add(Global.WIFI_P2P_PENDING_FACTORY_RESET);
            MOVED_TO_GLOBAL.add(Global.WIMAX_NETWORKS_AVAILABLE_NOTIFICATION_ON);
            MOVED_TO_GLOBAL.add(Global.PACKAGE_VERIFIER_TIMEOUT);
            MOVED_TO_GLOBAL.add(Global.PACKAGE_VERIFIER_DEFAULT_RESPONSE);
            MOVED_TO_GLOBAL.add(Global.DATA_STALL_ALARM_NON_AGGRESSIVE_DELAY_IN_MS);
            MOVED_TO_GLOBAL.add(Global.DATA_STALL_ALARM_AGGRESSIVE_DELAY_IN_MS);
            MOVED_TO_GLOBAL.add(Global.GPRS_REGISTER_CHECK_PERIOD_MS);
            MOVED_TO_GLOBAL.add(Global.WTF_IS_FATAL);
            MOVED_TO_GLOBAL.add(Global.BATTERY_DISCHARGE_DURATION_THRESHOLD);
            MOVED_TO_GLOBAL.add(Global.BATTERY_DISCHARGE_THRESHOLD);
            MOVED_TO_GLOBAL.add(Global.SEND_ACTION_APP_ERROR);
            MOVED_TO_GLOBAL.add(Global.DROPBOX_AGE_SECONDS);
            MOVED_TO_GLOBAL.add(Global.DROPBOX_MAX_FILES);
            MOVED_TO_GLOBAL.add(Global.DROPBOX_QUOTA_KB);
            MOVED_TO_GLOBAL.add(Global.DROPBOX_QUOTA_PERCENT);
            MOVED_TO_GLOBAL.add(Global.DROPBOX_RESERVE_PERCENT);
            MOVED_TO_GLOBAL.add(Global.DROPBOX_TAG_PREFIX);
            MOVED_TO_GLOBAL.add(Global.ERROR_LOGCAT_PREFIX);
            MOVED_TO_GLOBAL.add(Global.SYS_FREE_STORAGE_LOG_INTERVAL);
            MOVED_TO_GLOBAL.add(Global.DISK_FREE_CHANGE_REPORTING_THRESHOLD);
            MOVED_TO_GLOBAL.add(Global.SYS_STORAGE_THRESHOLD_PERCENTAGE);
            MOVED_TO_GLOBAL.add(Global.SYS_STORAGE_THRESHOLD_MAX_BYTES);
            MOVED_TO_GLOBAL.add(Global.SYS_STORAGE_FULL_THRESHOLD_BYTES);
            MOVED_TO_GLOBAL.add(Global.SYNC_MAX_RETRY_DELAY_IN_SECONDS);
            MOVED_TO_GLOBAL.add(Global.CONNECTIVITY_CHANGE_DELAY);
            MOVED_TO_GLOBAL.add(Global.CAPTIVE_PORTAL_DETECTION_ENABLED);
            MOVED_TO_GLOBAL.add(Global.CAPTIVE_PORTAL_SERVER);
            MOVED_TO_GLOBAL.add(Global.NSD_ON);
            MOVED_TO_GLOBAL.add(Global.SET_INSTALL_LOCATION);
            MOVED_TO_GLOBAL.add(Global.DEFAULT_INSTALL_LOCATION);
            MOVED_TO_GLOBAL.add(Global.INET_CONDITION_DEBOUNCE_UP_DELAY);
            MOVED_TO_GLOBAL.add(Global.INET_CONDITION_DEBOUNCE_DOWN_DELAY);
            MOVED_TO_GLOBAL.add(Global.READ_EXTERNAL_STORAGE_ENFORCED_DEFAULT);
            MOVED_TO_GLOBAL.add("http_proxy");
            MOVED_TO_GLOBAL.add(Global.GLOBAL_HTTP_PROXY_HOST);
            MOVED_TO_GLOBAL.add(Global.GLOBAL_HTTP_PROXY_PORT);
            MOVED_TO_GLOBAL.add(Global.GLOBAL_HTTP_PROXY_EXCLUSION_LIST);
            MOVED_TO_GLOBAL.add(Global.SET_GLOBAL_HTTP_PROXY);
            MOVED_TO_GLOBAL.add(Global.DEFAULT_DNS_SERVER);
            MOVED_TO_GLOBAL.add(Global.PREFERRED_NETWORK_MODE);
            MOVED_TO_GLOBAL.add(Global.WEBVIEW_DATA_REDUCTION_PROXY_KEY);
            LEGACY_RESTORE_SETTINGS = new String[]{ENABLED_NOTIFICATION_LISTENERS, ENABLED_NOTIFICATION_ASSISTANT, ENABLED_NOTIFICATION_POLICY_ACCESS_PACKAGES};
            ArraySet arraySet = new ArraySet();
            CLONE_TO_MANAGED_PROFILE = arraySet;
            arraySet.add(ACCESSIBILITY_ENABLED);
            CLONE_TO_MANAGED_PROFILE.add(ALLOW_MOCK_LOCATION);
            CLONE_TO_MANAGED_PROFILE.add(ALLOWED_GEOLOCATION_ORIGINS);
            CLONE_TO_MANAGED_PROFILE.add(CONTENT_CAPTURE_ENABLED);
            CLONE_TO_MANAGED_PROFILE.add(ENABLED_ACCESSIBILITY_SERVICES);
            CLONE_TO_MANAGED_PROFILE.add(LOCATION_CHANGER);
            CLONE_TO_MANAGED_PROFILE.add(LOCATION_MODE);
            CLONE_TO_MANAGED_PROFILE.add("location_providers_allowed");
            CLONE_TO_MANAGED_PROFILE.add(SHOW_IME_WITH_HARD_KEYBOARD);
            ArraySet arraySet2 = new ArraySet();
            INSTANT_APP_SETTINGS = arraySet2;
            arraySet2.add(ENABLED_ACCESSIBILITY_SERVICES);
            INSTANT_APP_SETTINGS.add(ACCESSIBILITY_SPEAK_PASSWORD);
            INSTANT_APP_SETTINGS.add(ACCESSIBILITY_DISPLAY_INVERSION_ENABLED);
            INSTANT_APP_SETTINGS.add(ACCESSIBILITY_CAPTIONING_ENABLED);
            INSTANT_APP_SETTINGS.add(ACCESSIBILITY_CAPTIONING_PRESET);
            INSTANT_APP_SETTINGS.add(ACCESSIBILITY_CAPTIONING_EDGE_TYPE);
            INSTANT_APP_SETTINGS.add(ACCESSIBILITY_CAPTIONING_EDGE_COLOR);
            INSTANT_APP_SETTINGS.add(ACCESSIBILITY_CAPTIONING_LOCALE);
            INSTANT_APP_SETTINGS.add(ACCESSIBILITY_CAPTIONING_BACKGROUND_COLOR);
            INSTANT_APP_SETTINGS.add(ACCESSIBILITY_CAPTIONING_FOREGROUND_COLOR);
            INSTANT_APP_SETTINGS.add(ACCESSIBILITY_CAPTIONING_TYPEFACE);
            INSTANT_APP_SETTINGS.add(ACCESSIBILITY_CAPTIONING_FONT_SCALE);
            INSTANT_APP_SETTINGS.add(ACCESSIBILITY_CAPTIONING_WINDOW_COLOR);
            INSTANT_APP_SETTINGS.add(ACCESSIBILITY_DISPLAY_DALTONIZER_ENABLED);
            INSTANT_APP_SETTINGS.add(ACCESSIBILITY_DISPLAY_DALTONIZER);
            INSTANT_APP_SETTINGS.add(ACCESSIBILITY_AUTOCLICK_DELAY);
            INSTANT_APP_SETTINGS.add(ACCESSIBILITY_AUTOCLICK_ENABLED);
            INSTANT_APP_SETTINGS.add(ACCESSIBILITY_LARGE_POINTER_ICON);
            INSTANT_APP_SETTINGS.add(DEFAULT_INPUT_METHOD);
            INSTANT_APP_SETTINGS.add(ENABLED_INPUT_METHODS);
            INSTANT_APP_SETTINGS.add("android_id");
            INSTANT_APP_SETTINGS.add(ALLOW_MOCK_LOCATION);
        }

        public static void getMovedToGlobalSettings(Set<String> set) {
            set.addAll(MOVED_TO_GLOBAL);
        }

        public static void clearProviderForTest() {
            sProviderHolder.clearProviderForTest();
            sNameValueCache.clearGenerationTrackerForTest();
        }

        public static String getString(ContentResolver contentResolver, String str) {
            return getStringForUser(contentResolver, str, contentResolver.getUserId());
        }

        public static String getStringForUser(ContentResolver contentResolver, String str, int i) {
            if (MOVED_TO_GLOBAL.contains(str)) {
                Log.w(Settings.TAG, "Setting " + str + " has moved from android.provider.Settings.Secure to android.provider.Settings.Global.");
                return Global.getStringForUser(contentResolver, str, i);
            }
            if (MOVED_TO_LOCK_SETTINGS.contains(str)) {
                synchronized (Secure.class) {
                    if (sLockSettings == null) {
                        sLockSettings = ILockSettings.Stub.asInterface(ServiceManager.getService("lock_settings"));
                        sIsSystemProcess = Process.myUid() == 1000;
                    }
                }
                if (sLockSettings != null && !sIsSystemProcess) {
                    Application applicationCurrentApplication = ActivityThread.currentApplication();
                    if ((applicationCurrentApplication == null || applicationCurrentApplication.getApplicationInfo() == null || applicationCurrentApplication.getApplicationInfo().targetSdkVersion > 22) ? false : true) {
                        try {
                            return sLockSettings.getString(str, AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS, i);
                        } catch (RemoteException unused) {
                        }
                    } else {
                        throw new SecurityException("Settings.Secure." + str + " is deprecated and no longer accessible. See API documentation for potential replacements.");
                    }
                }
            }
            return sNameValueCache.getStringForUser(contentResolver, str, i);
        }

        public static boolean putString(ContentResolver contentResolver, String str, String str2, boolean z) {
            return putStringForUser(contentResolver, str, str2, null, false, contentResolver.getUserId(), z);
        }

        public static boolean putString(ContentResolver contentResolver, String str, String str2) {
            return putStringForUser(contentResolver, str, str2, contentResolver.getUserId());
        }

        public static boolean putStringForUser(ContentResolver contentResolver, String str, String str2, int i) {
            return putStringForUser(contentResolver, str, str2, null, false, i, false);
        }

        public static boolean putStringForUser(ContentResolver contentResolver, String str, String str2, String str3, boolean z, int i, boolean z2) {
            if (MOVED_TO_GLOBAL.contains(str)) {
                Log.w(Settings.TAG, "Setting " + str + " has moved from android.provider.Settings.Secure to android.provider.Settings.Global");
                return Global.putStringForUser(contentResolver, str, str2, str3, z, i, false);
            }
            return sNameValueCache.putStringForUser(contentResolver, str, str2, str3, z, i, z2);
        }

        @SystemApi
        public static boolean putString(ContentResolver contentResolver, String str, String str2, String str3, boolean z) {
            return putStringForUser(contentResolver, str, str2, str3, z, contentResolver.getUserId(), false);
        }

        @SystemApi
        public static void resetToDefaults(ContentResolver contentResolver, String str) {
            resetToDefaultsAsUser(contentResolver, str, 1, contentResolver.getUserId());
        }

        public static void resetToDefaultsAsUser(ContentResolver contentResolver, String str, int i, int i2) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt(Settings.CALL_METHOD_USER_KEY, i2);
                if (str != null) {
                    bundle.putString(Settings.CALL_METHOD_TAG_KEY, str);
                }
                bundle.putInt(Settings.CALL_METHOD_RESET_MODE_KEY, i);
                sProviderHolder.getProvider(contentResolver).call(contentResolver.getPackageName(), contentResolver.getAttributionTag(), sProviderHolder.mUri.getAuthority(), Settings.CALL_METHOD_RESET_SECURE, null, bundle);
            } catch (RemoteException e) {
                Log.w(Settings.TAG, "Can't reset do defaults for " + CONTENT_URI, e);
            }
        }

        public static Uri getUriFor(String str) {
            if (MOVED_TO_GLOBAL.contains(str)) {
                Log.w(Settings.TAG, "Setting " + str + " has moved from android.provider.Settings.Secure to android.provider.Settings.Global, returning global URI.");
                return Global.getUriFor(Global.CONTENT_URI, str);
            }
            return getUriFor(CONTENT_URI, str);
        }

        public static int getInt(ContentResolver contentResolver, String str, int i) {
            return getIntForUser(contentResolver, str, i, contentResolver.getUserId());
        }

        public static int getIntForUser(ContentResolver contentResolver, String str, int i, int i2) {
            String stringForUser = getStringForUser(contentResolver, str, i2);
            if (stringForUser == null) {
                return i;
            }
            try {
                return Integer.parseInt(stringForUser);
            } catch (NumberFormatException unused) {
                return i;
            }
        }

        public static int getInt(ContentResolver contentResolver, String str) throws SettingNotFoundException {
            return getIntForUser(contentResolver, str, contentResolver.getUserId());
        }

        public static int getIntForUser(ContentResolver contentResolver, String str, int i) throws SettingNotFoundException {
            try {
                return Integer.parseInt(getStringForUser(contentResolver, str, i));
            } catch (NumberFormatException unused) {
                throw new SettingNotFoundException(str);
            }
        }

        public static boolean putInt(ContentResolver contentResolver, String str, int i) {
            return putIntForUser(contentResolver, str, i, contentResolver.getUserId());
        }

        public static boolean putIntForUser(ContentResolver contentResolver, String str, int i, int i2) {
            return putStringForUser(contentResolver, str, Integer.toString(i), i2);
        }

        public static long getLong(ContentResolver contentResolver, String str, long j) {
            return getLongForUser(contentResolver, str, j, contentResolver.getUserId());
        }

        public static long getLongForUser(ContentResolver contentResolver, String str, long j, int i) {
            String stringForUser = getStringForUser(contentResolver, str, i);
            if (stringForUser == null) {
                return j;
            }
            try {
                return Long.parseLong(stringForUser);
            } catch (NumberFormatException unused) {
                return j;
            }
        }

        public static long getLong(ContentResolver contentResolver, String str) throws SettingNotFoundException {
            return getLongForUser(contentResolver, str, contentResolver.getUserId());
        }

        public static long getLongForUser(ContentResolver contentResolver, String str, int i) throws SettingNotFoundException {
            try {
                return Long.parseLong(getStringForUser(contentResolver, str, i));
            } catch (NumberFormatException unused) {
                throw new SettingNotFoundException(str);
            }
        }

        public static boolean putLong(ContentResolver contentResolver, String str, long j) {
            return putLongForUser(contentResolver, str, j, contentResolver.getUserId());
        }

        public static boolean putLongForUser(ContentResolver contentResolver, String str, long j, int i) {
            return putStringForUser(contentResolver, str, Long.toString(j), i);
        }

        public static float getFloat(ContentResolver contentResolver, String str, float f) {
            return getFloatForUser(contentResolver, str, f, contentResolver.getUserId());
        }

        public static float getFloatForUser(ContentResolver contentResolver, String str, float f, int i) {
            String stringForUser = getStringForUser(contentResolver, str, i);
            if (stringForUser == null) {
                return f;
            }
            try {
                return Float.parseFloat(stringForUser);
            } catch (NumberFormatException unused) {
                return f;
            }
        }

        public static float getFloat(ContentResolver contentResolver, String str) throws SettingNotFoundException {
            return getFloatForUser(contentResolver, str, contentResolver.getUserId());
        }

        public static float getFloatForUser(ContentResolver contentResolver, String str, int i) throws SettingNotFoundException {
            String stringForUser = getStringForUser(contentResolver, str, i);
            if (stringForUser == null) {
                throw new SettingNotFoundException(str);
            }
            try {
                return Float.parseFloat(stringForUser);
            } catch (NumberFormatException unused) {
                throw new SettingNotFoundException(str);
            }
        }

        public static boolean putFloat(ContentResolver contentResolver, String str, float f) {
            return putFloatForUser(contentResolver, str, f, contentResolver.getUserId());
        }

        public static boolean putFloatForUser(ContentResolver contentResolver, String str, float f, int i) {
            return putStringForUser(contentResolver, str, Float.toString(f), i);
        }

        public static void getCloneToManagedProfileSettings(Set<String> set) {
            set.addAll(CLONE_TO_MANAGED_PROFILE);
        }

        @Deprecated
        public static boolean isLocationProviderEnabled(ContentResolver contentResolver, String str) {
            return TextUtils.delimitedStringContains(getStringForUser(contentResolver, "location_providers_allowed", contentResolver.getUserId()), ',', str);
        }
    }

    public static final class Global extends NameValueTable {
        public static final String ACTIVITY_MANAGER_CONSTANTS = "activity_manager_constants";
        public static final String ACTIVITY_STARTS_LOGGING_ENABLED = "activity_starts_logging_enabled";
        public static final String ADAPTIVE_BATTERY_MANAGEMENT_ENABLED = "adaptive_battery_management_enabled";
        public static final String ADB_ALLOWED_CONNECTION_TIME = "adb_allowed_connection_time";
        public static final String ADB_ENABLED = "adb_enabled";
        public static final String ADB_WIFI_ENABLED = "adb_wifi_enabled";
        public static final String ADD_USERS_WHEN_LOCKED = "add_users_when_locked";
        public static final String ADVANCED_BATTERY_USAGE_AMOUNT = "advanced_battery_usage_amount";
        public static final String AIRPLANE_MODE_ON = "airplane_mode_on";
        public static final String AIRPLANE_MODE_RADIOS = "airplane_mode_radios";

        @SystemApi
        public static final String AIRPLANE_MODE_TOGGLEABLE_RADIOS = "airplane_mode_toggleable_radios";
        public static final String ALARM_MANAGER_CONSTANTS = "alarm_manager_constants";
        public static final String ALLOW_USER_SWITCHING_WHEN_SYSTEM_USER_LOCKED = "allow_user_switching_when_system_user_locked";
        public static final String ALWAYS_FINISH_ACTIVITIES = "always_finish_activities";
        public static final String ALWAYS_ON_DISPLAY_CONSTANTS = "always_on_display_constants";
        public static final String ANIMATOR_DURATION_SCALE = "animator_duration_scale";
        public static final String ANOMALY_CONFIG = "anomaly_config";
        public static final String ANOMALY_CONFIG_VERSION = "anomaly_config_version";
        public static final String ANOMALY_DETECTION_CONSTANTS = "anomaly_detection_constants";
        public static final String APN_DB_UPDATE_CONTENT_URL = "apn_db_content_url";
        public static final String APN_DB_UPDATE_METADATA_URL = "apn_db_metadata_url";
        public static final String APPLY_RAMPING_RINGER = "apply_ramping_ringer";
        public static final String APPOP_HISTORY_BASE_INTERVAL_MILLIS = "baseIntervalMillis";
        public static final String APPOP_HISTORY_INTERVAL_MULTIPLIER = "intervalMultiplier";
        public static final String APPOP_HISTORY_MODE = "mode";
        public static final String APPOP_HISTORY_PARAMETERS = "appop_history_parameters";
        public static final String APP_AUTO_RESTRICTION_ENABLED = "app_auto_restriction_enabled";
        public static final String APP_BINDING_CONSTANTS = "app_binding_constants";
        public static final String APP_IDLE_CONSTANTS = "app_idle_constants";
        public static final String APP_INTEGRITY_VERIFICATION_TIMEOUT = "app_integrity_verification_timeout";
        public static final String APP_OPS_CONSTANTS = "app_ops_constants";

        @SystemApi
        public static final String APP_STANDBY_ENABLED = "app_standby_enabled";
        public static final String APP_TIME_LIMIT_USAGE_SOURCE = "app_time_limit_usage_source";
        public static final String ART_VERIFIER_VERIFY_DEBUGGABLE = "art_verifier_verify_debuggable";
        public static final String ASSISTED_GPS_ENABLED = "assisted_gps_enabled";
        public static final String AUDIO_SAFE_VOLUME_STATE = "audio_safe_volume_state";

        @SystemApi
        public static final String AUTOFILL_COMPAT_MODE_ALLOWED_PACKAGES = "autofill_compat_mode_allowed_packages";
        public static final String AUTOFILL_LOGGING_LEVEL = "autofill_logging_level";
        public static final String AUTOFILL_MAX_PARTITIONS_SIZE = "autofill_max_partitions_size";
        public static final String AUTOFILL_MAX_VISIBLE_DATASETS = "autofill_max_visible_datasets";
        public static final String AUTOMATIC_POWER_SAVE_MODE = "automatic_power_save_mode";
        public static final String AUTO_REVOKE_PARAMETERS = "auto_revoke_parameters";
        public static final String AUTO_TIME = "auto_time";
        public static final String AUTO_TIME_ZONE = "auto_time_zone";
        public static final String AVERAGE_TIME_TO_DISCHARGE = "average_time_to_discharge";
        public static final String AWARE_ALLOWED = "aware_allowed";
        public static final String BACKUP_AGENT_TIMEOUT_PARAMETERS = "backup_agent_timeout_parameters";
        public static final String BATTERY_CHARGING_STATE_UPDATE_DELAY = "battery_charging_state_update_delay";
        public static final String BATTERY_DISCHARGE_DURATION_THRESHOLD = "battery_discharge_duration_threshold";
        public static final String BATTERY_DISCHARGE_THRESHOLD = "battery_discharge_threshold";
        public static final String BATTERY_ESTIMATES_LAST_UPDATE_TIME = "battery_estimates_last_update_time";
        public static final String BATTERY_SAVER_ADAPTIVE_CONSTANTS = "battery_saver_adaptive_constants";
        public static final String BATTERY_SAVER_ADAPTIVE_DEVICE_SPECIFIC_CONSTANTS = "battery_saver_adaptive_device_specific_constants";
        public static final String BATTERY_SAVER_CONSTANTS = "battery_saver_constants";
        public static final String BATTERY_SAVER_DEVICE_SPECIFIC_CONSTANTS = "battery_saver_device_specific_constants";
        public static final String BATTERY_STATS_CONSTANTS = "battery_stats_constants";
        public static final String BATTERY_TIP_CONSTANTS = "battery_tip_constants";
        public static final String BINDER_CALLS_STATS = "binder_calls_stats";
        public static final String BLE_SCAN_ALWAYS_AVAILABLE = "ble_scan_always_enabled";
        public static final String BLE_SCAN_BACKGROUND_MODE = "ble_scan_background_mode";
        public static final String BLE_SCAN_BALANCED_INTERVAL_MS = "ble_scan_balanced_interval_ms";
        public static final String BLE_SCAN_BALANCED_WINDOW_MS = "ble_scan_balanced_window_ms";
        public static final String BLE_SCAN_LOW_LATENCY_INTERVAL_MS = "ble_scan_low_latency_interval_ms";
        public static final String BLE_SCAN_LOW_LATENCY_WINDOW_MS = "ble_scan_low_latency_window_ms";
        public static final String BLE_SCAN_LOW_POWER_INTERVAL_MS = "ble_scan_low_power_interval_ms";
        public static final String BLE_SCAN_LOW_POWER_WINDOW_MS = "ble_scan_low_power_window_ms";
        public static final String BLOCKED_SLICES = "blocked_slices";
        public static final String BLOCKING_HELPER_DISMISS_TO_VIEW_RATIO_LIMIT = "blocking_helper_dismiss_to_view_ratio";
        public static final String BLOCKING_HELPER_STREAK_LIMIT = "blocking_helper_streak_limit";
        public static final String BLUETOOTH_A2DP_OPTIONAL_CODECS_ENABLED_PREFIX = "bluetooth_a2dp_optional_codecs_enabled_";
        public static final String BLUETOOTH_A2DP_SINK_PRIORITY_PREFIX = "bluetooth_a2dp_sink_priority_";
        public static final String BLUETOOTH_A2DP_SRC_PRIORITY_PREFIX = "bluetooth_a2dp_src_priority_";
        public static final String BLUETOOTH_A2DP_SUPPORTS_OPTIONAL_CODECS_PREFIX = "bluetooth_a2dp_supports_optional_codecs_";
        public static final String BLUETOOTH_BTSNOOP_DEFAULT_MODE = "bluetooth_btsnoop_default_mode";
        public static final String BLUETOOTH_CLASS_OF_DEVICE = "bluetooth_class_of_device";
        public static final String BLUETOOTH_DISABLED_PROFILES = "bluetooth_disabled_profiles";
        public static final String BLUETOOTH_HEADSET_PRIORITY_PREFIX = "bluetooth_headset_priority_";
        public static final String BLUETOOTH_HEARING_AID_PRIORITY_PREFIX = "bluetooth_hearing_aid_priority_";
        public static final String BLUETOOTH_INPUT_DEVICE_PRIORITY_PREFIX = "bluetooth_input_device_priority_";
        public static final String BLUETOOTH_INTEROPERABILITY_LIST = "bluetooth_interoperability_list";
        public static final String BLUETOOTH_MAP_CLIENT_PRIORITY_PREFIX = "bluetooth_map_client_priority_";
        public static final String BLUETOOTH_MAP_PRIORITY_PREFIX = "bluetooth_map_priority_";
        public static final String BLUETOOTH_ON = "bluetooth_on";
        public static final String BLUETOOTH_PAN_PRIORITY_PREFIX = "bluetooth_pan_priority_";
        public static final String BLUETOOTH_PBAP_CLIENT_PRIORITY_PREFIX = "bluetooth_pbap_client_priority_";
        public static final String BLUETOOTH_SAP_PRIORITY_PREFIX = "bluetooth_sap_priority_";
        public static final String BOOT_COUNT = "boot_count";
        public static final String BROADCAST_BG_CONSTANTS = "bcast_bg_constants";
        public static final String BROADCAST_FG_CONSTANTS = "bcast_fg_constants";
        public static final String BROADCAST_OFFLOAD_CONSTANTS = "bcast_offload_constants";
        public static final String BUGREPORT_IN_POWER_MENU = "bugreport_in_power_menu";
        public static final String CACHED_APPS_FREEZER_ENABLED = "cached_apps_freezer";
        public static final String CALL_AUTO_RETRY = "call_auto_retry";

        @Deprecated
        public static final String CAPTIVE_PORTAL_DETECTION_ENABLED = "captive_portal_detection_enabled";
        public static final String CAPTIVE_PORTAL_FALLBACK_PROBE_SPECS = "captive_portal_fallback_probe_specs";
        public static final String CAPTIVE_PORTAL_FALLBACK_URL = "captive_portal_fallback_url";
        public static final String CAPTIVE_PORTAL_HTTPS_URL = "captive_portal_https_url";
        public static final String CAPTIVE_PORTAL_HTTP_URL = "captive_portal_http_url";
        public static final String CAPTIVE_PORTAL_MODE = "captive_portal_mode";
        public static final int CAPTIVE_PORTAL_MODE_AVOID = 2;
        public static final int CAPTIVE_PORTAL_MODE_IGNORE = 0;
        public static final int CAPTIVE_PORTAL_MODE_PROMPT = 1;
        public static final String CAPTIVE_PORTAL_OTHER_FALLBACK_URLS = "captive_portal_other_fallback_urls";
        public static final String CAPTIVE_PORTAL_SERVER = "captive_portal_server";
        public static final String CAPTIVE_PORTAL_USER_AGENT = "captive_portal_user_agent";
        public static final String CAPTIVE_PORTAL_USE_HTTPS = "captive_portal_use_https";

        @SystemApi
        public static final String CARRIER_APP_NAMES = "carrier_app_names";

        @SystemApi
        public static final String CARRIER_APP_WHITELIST = "carrier_app_whitelist";
        public static final String CAR_DOCK_SOUND = "car_dock_sound";
        public static final String CAR_UNDOCK_SOUND = "car_undock_sound";
        public static final String CDMA_CELL_BROADCAST_SMS = "cdma_cell_broadcast_sms";
        public static final String CDMA_ROAMING_MODE = "roaming_settings";
        public static final String CDMA_SUBSCRIPTION_MODE = "subscription_mode";
        public static final String CELL_ON = "cell_on";
        public static final String CERT_PIN_UPDATE_CONTENT_URL = "cert_pin_content_url";
        public static final String CERT_PIN_UPDATE_METADATA_URL = "cert_pin_metadata_url";
        public static final String CHAINED_BATTERY_ATTRIBUTION_ENABLED = "chained_battery_attribution_enabled";

        @Deprecated
        public static final String CHARGING_SOUNDS_ENABLED = "charging_sounds_enabled";
        public static final String CHARGING_STARTED_SOUND = "charging_started_sound";

        @Deprecated
        public static final String CHARGING_VIBRATION_ENABLED = "charging_vibration_enabled";
        public static final String COMPATIBILITY_MODE = "compatibility_mode";
        public static final String CONNECTIVITY_CHANGE_DELAY = "connectivity_change_delay";
        public static final String CONNECTIVITY_METRICS_BUFFER_SIZE = "connectivity_metrics_buffer_size";
        public static final String CONNECTIVITY_SAMPLING_INTERVAL_IN_SECONDS = "connectivity_sampling_interval_in_seconds";
        public static final String CONTACTS_DATABASE_WAL_ENABLED = "contacts_database_wal_enabled";

        @Deprecated
        public static final String CONTACT_METADATA_SYNC = "contact_metadata_sync";
        public static final String CONTACT_METADATA_SYNC_ENABLED = "contact_metadata_sync_enabled";
        public static final String CONVERSATION_ACTIONS_UPDATE_CONTENT_URL = "conversation_actions_content_url";
        public static final String CONVERSATION_ACTIONS_UPDATE_METADATA_URL = "conversation_actions_metadata_url";
        public static final String CUSTOM_BUGREPORT_HANDLER_APP = "custom_bugreport_handler_app";
        public static final String CUSTOM_BUGREPORT_HANDLER_USER = "custom_bugreport_handler_user";
        public static final String DATABASE_CREATION_BUILDID = "database_creation_buildid";
        public static final String DATABASE_DOWNGRADE_REASON = "database_downgrade_reason";
        public static final String DATA_ACTIVITY_TIMEOUT_MOBILE = "data_activity_timeout_mobile";
        public static final String DATA_ACTIVITY_TIMEOUT_WIFI = "data_activity_timeout_wifi";
        public static final String DATA_ROAMING = "data_roaming";
        public static final String DATA_STALL_ALARM_AGGRESSIVE_DELAY_IN_MS = "data_stall_alarm_aggressive_delay_in_ms";
        public static final String DATA_STALL_ALARM_NON_AGGRESSIVE_DELAY_IN_MS = "data_stall_alarm_non_aggressive_delay_in_ms";
        public static final String DATA_STALL_RECOVERY_ON_BAD_NETWORK = "data_stall_recovery_on_bad_network";
        public static final String DEBUG_APP = "debug_app";
        public static final String DEBUG_VIEW_ATTRIBUTES = "debug_view_attributes";
        public static final String DEBUG_VIEW_ATTRIBUTES_APPLICATION_PACKAGE = "debug_view_attributes_application_package";
        public static final long DEFAULT_ADB_ALLOWED_CONNECTION_TIME = 604800000;
        public static final String DEFAULT_DNS_SERVER = "default_dns_server";
        public static final int DEFAULT_ENABLE_RESTRICTED_BUCKET = 0;
        public static final String DEFAULT_INSTALL_LOCATION = "default_install_location";
        public static final String DEFAULT_RESTRICT_BACKGROUND_DATA = "default_restrict_background_data";

        @SystemApi
        public static final String DEFAULT_SM_DP_PLUS = "default_sm_dp_plus";
        public static final String DESK_DOCK_SOUND = "desk_dock_sound";
        public static final String DESK_UNDOCK_SOUND = "desk_undock_sound";
        public static final String DEVELOPMENT_ENABLE_FREEFORM_WINDOWS_SUPPORT = "enable_freeform_support";
        public static final String DEVELOPMENT_ENABLE_SIZECOMPAT_FREEFORM = "enable_sizecompat_freeform";
        public static final String DEVELOPMENT_FORCE_DESKTOP_MODE_ON_EXTERNAL_DISPLAYS = "force_desktop_mode_on_external_displays";
        public static final String DEVELOPMENT_FORCE_RESIZABLE_ACTIVITIES = "force_resizable_activities";
        public static final String DEVELOPMENT_FORCE_RTL = "debug.force_rtl";
        public static final String DEVELOPMENT_RENDER_SHADOWS_IN_COMPOSITOR = "render_shadows_in_compositor";
        public static final String DEVELOPMENT_SETTINGS_ENABLED = "development_settings_enabled";

        @SystemApi
        public static final String DEVICE_DEMO_MODE = "device_demo_mode";
        public static final String DEVICE_IDLE_CONSTANTS = "device_idle_constants";
        public static final String DEVICE_NAME = "device_name";
        public static final String DEVICE_POLICY_CONSTANTS = "device_policy_constants";
        public static final String DEVICE_PROVISIONED = "device_provisioned";

        @SystemApi
        public static final String DEVICE_PROVISIONING_MOBILE_DATA_ENABLED = "device_provisioning_mobile_data";
        public static final String DISK_FREE_CHANGE_REPORTING_THRESHOLD = "disk_free_change_reporting_threshold";
        public static final String DISPLAY_PANEL_LPM = "display_panel_lpm";
        public static final String DISPLAY_SCALING_FORCE = "display_scaling_force";
        public static final String DISPLAY_SIZE_FORCED = "display_size_forced";
        public static final String DNS_RESOLVER_MAX_SAMPLES = "dns_resolver_max_samples";
        public static final String DNS_RESOLVER_MIN_SAMPLES = "dns_resolver_min_samples";
        public static final String DNS_RESOLVER_SAMPLE_VALIDITY_SECONDS = "dns_resolver_sample_validity_seconds";
        public static final String DNS_RESOLVER_SUCCESS_THRESHOLD_PERCENT = "dns_resolver_success_threshold_percent";
        public static final String DOCK_AUDIO_MEDIA_ENABLED = "dock_audio_media_enabled";
        public static final String DOCK_SOUNDS_ENABLED = "dock_sounds_enabled";
        public static final String DOCK_SOUNDS_ENABLED_WHEN_ACCESSIBILITY = "dock_sounds_enabled_when_accessbility";
        public static final String DOWNLOAD_MAX_BYTES_OVER_MOBILE = "download_manager_max_bytes_over_mobile";
        public static final String DOWNLOAD_RECOMMENDED_MAX_BYTES_OVER_MOBILE = "download_manager_recommended_max_bytes_over_mobile";
        public static final String DROPBOX_AGE_SECONDS = "dropbox_age_seconds";
        public static final String DROPBOX_MAX_FILES = "dropbox_max_files";
        public static final String DROPBOX_QUOTA_KB = "dropbox_quota_kb";
        public static final String DROPBOX_QUOTA_PERCENT = "dropbox_quota_percent";
        public static final String DROPBOX_RESERVE_PERCENT = "dropbox_reserve_percent";
        public static final String DROPBOX_TAG_PREFIX = "dropbox:";
        public static final String DYNAMIC_POWER_SAVINGS_DISABLE_THRESHOLD = "dynamic_power_savings_disable_threshold";
        public static final String DYNAMIC_POWER_SAVINGS_ENABLED = "dynamic_power_savings_enabled";
        public static final String EMERGENCY_AFFORDANCE_NEEDED = "emergency_affordance_needed";
        public static final String EMERGENCY_TONE = "emergency_tone";
        public static final String EMULATE_DISPLAY_CUTOUT = "emulate_display_cutout";
        public static final int EMULATE_DISPLAY_CUTOUT_OFF = 0;
        public static final int EMULATE_DISPLAY_CUTOUT_ON = 1;
        public static final String ENABLED_SUBSCRIPTION_FOR_SLOT = "enabled_subscription_for_slot";
        public static final String ENABLE_ACCESSIBILITY_GLOBAL_GESTURE_ENABLED = "enable_accessibility_global_gesture_enabled";
        public static final String ENABLE_ADB_INCREMENTAL_INSTALL_DEFAULT = "enable_adb_incremental_install_default";
        public static final String ENABLE_AUTOMATIC_SYSTEM_SERVER_HEAP_DUMPS = "enable_automatic_system_server_heap_dumps";
        public static final String ENABLE_CACHE_QUOTA_CALCULATION = "enable_cache_quota_calculation";
        public static final String ENABLE_CELLULAR_ON_BOOT = "enable_cellular_on_boot";
        public static final String ENABLE_DELETION_HELPER_NO_THRESHOLD_TOGGLE = "enable_deletion_helper_no_threshold_toggle";
        public static final String ENABLE_DISKSTATS_LOGGING = "enable_diskstats_logging";
        public static final String ENABLE_EPHEMERAL_FEATURE = "enable_ephemeral_feature";
        public static final String ENABLE_GNSS_RAW_MEAS_FULL_TRACKING = "enable_gnss_raw_meas_full_tracking";
        public static final String ENABLE_GPU_DEBUG_LAYERS = "enable_gpu_debug_layers";
        public static final String ENABLE_RADIO_BUG_DETECTION = "enable_radio_bug_detection";
        public static final String ENABLE_RESTRICTED_BUCKET = "enable_restricted_bucket";
        public static final String ENCODED_SURROUND_OUTPUT = "encoded_surround_output";
        public static final int ENCODED_SURROUND_OUTPUT_ALWAYS = 2;
        public static final int ENCODED_SURROUND_OUTPUT_AUTO = 0;
        public static final String ENCODED_SURROUND_OUTPUT_ENABLED_FORMATS = "encoded_surround_output_enabled_formats";
        public static final int ENCODED_SURROUND_OUTPUT_MANUAL = 3;
        public static final int ENCODED_SURROUND_OUTPUT_NEVER = 1;

        @Deprecated
        public static final String ENHANCED_4G_MODE_ENABLED = "volte_vt_enabled";
        public static final String EPHEMERAL_COOKIE_MAX_SIZE_BYTES = "ephemeral_cookie_max_size_bytes";
        public static final String ERROR_LOGCAT_PREFIX = "logcat_for_";
        public static final String EUICC_FACTORY_RESET_TIMEOUT_MILLIS = "euicc_factory_reset_timeout_millis";

        @SystemApi
        public static final String EUICC_PROVISIONED = "euicc_provisioned";
        public static final String EUICC_REMOVING_INVISIBLE_PROFILES_TIMEOUT_MILLIS = "euicc_removing_invisible_profiles_timeout_millis";

        @SystemApi
        public static final String EUICC_SUPPORTED_COUNTRIES = "euicc_supported_countries";

        @SystemApi
        public static final String EUICC_UNSUPPORTED_COUNTRIES = "euicc_unsupported_countries";
        public static final String FANCY_IME_ANIMATIONS = "fancy_ime_animations";
        public static final String FORCED_APP_STANDBY_ENABLED = "forced_app_standby_enabled";
        public static final String FORCED_APP_STANDBY_FOR_SMALL_BATTERY_ENABLED = "forced_app_standby_for_small_battery_enabled";
        public static final String FORCE_ALLOW_ON_EXTERNAL = "force_allow_on_external";
        public static final String FOREGROUND_SERVICE_STARTS_LOGGING_ENABLED = "foreground_service_starts_logging_enabled";
        public static final String FPS_DEVISOR = "fps_divisor";
        public static final String FSTRIM_MANDATORY_INTERVAL = "fstrim_mandatory_interval";
        public static final String GAME_DRIVER_ALL_APPS = "game_driver_all_apps";
        public static final String GAME_DRIVER_BLACKLIST = "game_driver_blacklist";
        public static final String GAME_DRIVER_BLACKLISTS = "game_driver_blacklists";
        public static final String GAME_DRIVER_OPT_IN_APPS = "game_driver_opt_in_apps";
        public static final String GAME_DRIVER_OPT_OUT_APPS = "game_driver_opt_out_apps";
        public static final String GAME_DRIVER_PRERELEASE_OPT_IN_APPS = "game_driver_prerelease_opt_in_apps";
        public static final String GAME_DRIVER_SPHAL_LIBRARIES = "game_driver_sphal_libraries";
        public static final String GAME_DRIVER_WHITELIST = "game_driver_whitelist";
        public static final String GLOBAL_HTTP_PROXY_EXCLUSION_LIST = "global_http_proxy_exclusion_list";
        public static final String GLOBAL_HTTP_PROXY_HOST = "global_http_proxy_host";
        public static final String GLOBAL_HTTP_PROXY_PAC = "global_proxy_pac_url";
        public static final String GLOBAL_HTTP_PROXY_PORT = "global_http_proxy_port";
        public static final String GLOBAL_SETTINGS_ANGLE_DEBUG_PACKAGE = "angle_debug_package";
        public static final String GLOBAL_SETTINGS_ANGLE_GL_DRIVER_ALL_ANGLE = "angle_gl_driver_all_angle";
        public static final String GLOBAL_SETTINGS_ANGLE_GL_DRIVER_SELECTION_PKGS = "angle_gl_driver_selection_pkgs";
        public static final String GLOBAL_SETTINGS_ANGLE_GL_DRIVER_SELECTION_VALUES = "angle_gl_driver_selection_values";
        public static final String GLOBAL_SETTINGS_ANGLE_WHITELIST = "angle_whitelist";
        public static final String GLOBAL_SETTINGS_SHOW_ANGLE_IN_USE_DIALOG_BOX = "show_angle_in_use_dialog_box";
        public static final String GNSS_HAL_LOCATION_REQUEST_DURATION_MILLIS = "gnss_hal_location_request_duration_millis";
        public static final String GNSS_SATELLITE_BLACKLIST = "gnss_satellite_blacklist";
        public static final String GPRS_REGISTER_CHECK_PERIOD_MS = "gprs_register_check_period_ms";
        public static final String GPU_DEBUG_APP = "gpu_debug_app";
        public static final String GPU_DEBUG_LAYERS = "gpu_debug_layers";
        public static final String GPU_DEBUG_LAYERS_GLES = "gpu_debug_layers_gles";
        public static final String GPU_DEBUG_LAYER_APP = "gpu_debug_layer_app";
        public static final String HDMI_CEC_SWITCH_ENABLED = "hdmi_cec_switch_enabled";
        public static final String HDMI_CONTROL_AUTO_DEVICE_OFF_ENABLED = "hdmi_control_auto_device_off_enabled";
        public static final String HDMI_CONTROL_AUTO_WAKEUP_ENABLED = "hdmi_control_auto_wakeup_enabled";
        public static final String HDMI_CONTROL_ENABLED = "hdmi_control_enabled";
        public static final String HDMI_CONTROL_VOLUME_CONTROL_ENABLED = "hdmi_control_volume_control_enabled";
        public static final String HDMI_SYSTEM_AUDIO_CONTROL_ENABLED = "hdmi_system_audio_control_enabled";
        public static final String HEADS_UP_NOTIFICATIONS_ENABLED = "heads_up_notifications_enabled";
        public static final int HEADS_UP_OFF = 0;
        public static final int HEADS_UP_ON = 1;
        public static final String HIDDEN_API_BLACKLIST_EXEMPTIONS = "hidden_api_blacklist_exemptions";
        public static final String HIDDEN_API_POLICY = "hidden_api_policy";
        public static final String HIDE_ERROR_DIALOGS = "hide_error_dialogs";
        public static final String HTTP_PROXY = "http_proxy";
        public static final String INET_CONDITION_DEBOUNCE_DOWN_DELAY = "inet_condition_debounce_down_delay";
        public static final String INET_CONDITION_DEBOUNCE_UP_DELAY = "inet_condition_debounce_up_delay";
        public static final String INSTALLED_INSTANT_APP_MAX_CACHE_PERIOD = "installed_instant_app_max_cache_period";
        public static final String INSTALLED_INSTANT_APP_MIN_CACHE_PERIOD = "installed_instant_app_min_cache_period";

        @SystemApi
        public static final String INSTALL_CARRIER_APP_NOTIFICATION_PERSISTENT = "install_carrier_app_notification_persistent";

        @SystemApi
        public static final String INSTALL_CARRIER_APP_NOTIFICATION_SLEEP_MILLIS = "install_carrier_app_notification_sleep_millis";

        @Deprecated
        public static final String INSTALL_NON_MARKET_APPS = "install_non_market_apps";
        public static final String INSTANT_APP_DEXOPT_ENABLED = "instant_app_dexopt_enabled";
        public static final Set<String> INSTANT_APP_SETTINGS;
        public static final String INTEGRITY_CHECK_INCLUDES_RULE_PROVIDER = "verify_integrity_for_rule_provider";
        public static final String INTENT_FIREWALL_UPDATE_CONTENT_URL = "intent_firewall_content_url";
        public static final String INTENT_FIREWALL_UPDATE_METADATA_URL = "intent_firewall_metadata_url";
        public static final String ISOLATED_STORAGE_LOCAL = "isolated_storage_local";
        public static final String ISOLATED_STORAGE_REMOTE = "isolated_storage_remote";
        public static final String JOB_SCHEDULER_CONSTANTS = "job_scheduler_constants";
        public static final String JOB_SCHEDULER_QUOTA_CONTROLLER_CONSTANTS = "job_scheduler_quota_controller_constants";
        public static final String JOB_SCHEDULER_TIME_CONTROLLER_CONSTANTS = "job_scheduler_time_controller_constants";
        public static final String KEEP_PROFILE_IN_BACKGROUND = "keep_profile_in_background";
        public static final String KERNEL_CPU_THREAD_READER = "kernel_cpu_thread_reader";
        public static final String LANG_ID_UPDATE_CONTENT_URL = "lang_id_content_url";
        public static final String LANG_ID_UPDATE_METADATA_URL = "lang_id_metadata_url";
        public static final String LID_BEHAVIOR = "lid_behavior";
        public static final String LOCATION_BACKGROUND_THROTTLE_INTERVAL_MS = "location_background_throttle_interval_ms";
        public static final String LOCATION_BACKGROUND_THROTTLE_PACKAGE_WHITELIST = "location_background_throttle_package_whitelist";
        public static final String LOCATION_BACKGROUND_THROTTLE_PROXIMITY_ALERT_INTERVAL_MS = "location_background_throttle_proximity_alert_interval_ms";
        public static final String LOCATION_IGNORE_SETTINGS_PACKAGE_WHITELIST = "location_ignore_settings_package_whitelist";
        public static final String LOCATION_SETTINGS_LINK_TO_PERMISSIONS_ENABLED = "location_settings_link_to_permissions_enabled";
        public static final String LOCK_SOUND = "lock_sound";
        public static final String LOOPER_STATS = "looper_stats";
        public static final String LOW_BATTERY_SOUND = "low_battery_sound";
        public static final String LOW_BATTERY_SOUND_TIMEOUT = "low_battery_sound_timeout";
        public static final String LOW_POWER_MODE = "low_power";
        public static final String LOW_POWER_MODE_STICKY = "low_power_sticky";
        public static final String LOW_POWER_MODE_STICKY_AUTO_DISABLE_ENABLED = "low_power_sticky_auto_disable_enabled";
        public static final String LOW_POWER_MODE_STICKY_AUTO_DISABLE_LEVEL = "low_power_sticky_auto_disable_level";
        public static final String LOW_POWER_MODE_SUGGESTION_PARAMS = "low_power_mode_suggestion_params";
        public static final String LOW_POWER_MODE_TRIGGER_LEVEL = "low_power_trigger_level";
        public static final String LOW_POWER_MODE_TRIGGER_LEVEL_MAX = "low_power_trigger_level_max";
        public static final String LTE_SERVICE_FORCED = "lte_service_forced";
        public static final String MAX_ERROR_BYTES_PREFIX = "max_error_bytes_for_";
        public static final String MAX_NOTIFICATION_ENQUEUE_RATE = "max_notification_enqueue_rate";
        public static final String MAX_SOUND_TRIGGER_DETECTION_SERVICE_OPS_PER_DAY = "max_sound_trigger_detection_service_ops_per_day";
        public static final String MDC_INITIAL_MAX_RETRY = "mdc_initial_max_retry";
        public static final String MHL_INPUT_SWITCHING_ENABLED = "mhl_input_switching_enabled";
        public static final String MHL_POWER_CHARGE_ENABLED = "mhl_power_charge_enabled";
        public static final String MIN_DURATION_BETWEEN_RECOVERY_STEPS_IN_MS = "min_duration_between_recovery_steps";
        public static final String MOBILE_DATA = "mobile_data";
        public static final String MOBILE_DATA_ALWAYS_ON = "mobile_data_always_on";
        public static final String MODEM_STACK_ENABLED_FOR_SLOT = "modem_stack_enabled_for_slot";
        public static final String MODE_RINGER = "mode_ringer";
        private static final HashSet<String> MOVED_TO_SECURE;
        public static final String MULTI_SIM_DATA_CALL_SUBSCRIPTION = "multi_sim_data_call";
        public static final String MULTI_SIM_SMS_PROMPT = "multi_sim_sms_prompt";
        public static final String MULTI_SIM_SMS_SUBSCRIPTION = "multi_sim_sms";
        public static final String[] MULTI_SIM_USER_PREFERRED_SUBS;
        public static final String MULTI_SIM_VOICE_CALL_SUBSCRIPTION = "multi_sim_voice_call";
        public static final String MULTI_SIM_VOICE_PROMPT = "multi_sim_voice_prompt";
        public static final String NATIVE_FLAGS_HEALTH_CHECK_ENABLED = "native_flags_health_check_enabled";
        public static final String NETPOLICY_OVERRIDE_ENABLED = "netpolicy_override_enabled";
        public static final String NETPOLICY_QUOTA_ENABLED = "netpolicy_quota_enabled";
        public static final String NETPOLICY_QUOTA_FRAC_JOBS = "netpolicy_quota_frac_jobs";
        public static final String NETPOLICY_QUOTA_FRAC_MULTIPATH = "netpolicy_quota_frac_multipath";
        public static final String NETPOLICY_QUOTA_LIMITED = "netpolicy_quota_limited";
        public static final String NETPOLICY_QUOTA_UNLIMITED = "netpolicy_quota_unlimited";
        public static final String NETSTATS_AUGMENT_ENABLED = "netstats_augment_enabled";
        public static final String NETSTATS_COMBINE_SUBTYPE_ENABLED = "netstats_combine_subtype_enabled";
        public static final String NETSTATS_DEV_BUCKET_DURATION = "netstats_dev_bucket_duration";
        public static final String NETSTATS_DEV_DELETE_AGE = "netstats_dev_delete_age";
        public static final String NETSTATS_DEV_PERSIST_BYTES = "netstats_dev_persist_bytes";
        public static final String NETSTATS_DEV_ROTATE_AGE = "netstats_dev_rotate_age";
        public static final String NETSTATS_ENABLED = "netstats_enabled";
        public static final String NETSTATS_GLOBAL_ALERT_BYTES = "netstats_global_alert_bytes";
        public static final String NETSTATS_POLL_INTERVAL = "netstats_poll_interval";
        public static final String NETSTATS_SAMPLE_ENABLED = "netstats_sample_enabled";

        @Deprecated
        public static final String NETSTATS_TIME_CACHE_MAX_AGE = "netstats_time_cache_max_age";
        public static final String NETSTATS_UID_BUCKET_DURATION = "netstats_uid_bucket_duration";
        public static final String NETSTATS_UID_DELETE_AGE = "netstats_uid_delete_age";
        public static final String NETSTATS_UID_PERSIST_BYTES = "netstats_uid_persist_bytes";
        public static final String NETSTATS_UID_ROTATE_AGE = "netstats_uid_rotate_age";
        public static final String NETSTATS_UID_TAG_BUCKET_DURATION = "netstats_uid_tag_bucket_duration";
        public static final String NETSTATS_UID_TAG_DELETE_AGE = "netstats_uid_tag_delete_age";
        public static final String NETSTATS_UID_TAG_PERSIST_BYTES = "netstats_uid_tag_persist_bytes";
        public static final String NETSTATS_UID_TAG_ROTATE_AGE = "netstats_uid_tag_rotate_age";
        public static final String NETWORK_ACCESS_TIMEOUT_MS = "network_access_timeout_ms";
        public static final String NETWORK_AVOID_BAD_WIFI = "network_avoid_bad_wifi";
        public static final String NETWORK_DEFAULT_DAILY_MULTIPATH_QUOTA_BYTES = "network_default_daily_multipath_quota_bytes";
        public static final String NETWORK_METERED_MULTIPATH_PREFERENCE = "network_metered_multipath_preference";
        public static final String NETWORK_PREFERENCE = "network_preference";
        public static final String NETWORK_RECOMMENDATIONS_ENABLED = "network_recommendations_enabled";
        public static final String NETWORK_RECOMMENDATIONS_PACKAGE = "network_recommendations_package";
        public static final String NETWORK_SCORER_APP = "network_scorer_app";
        public static final String NETWORK_SCORING_PROVISIONED = "network_scoring_provisioned";
        public static final String NETWORK_SCORING_UI_ENABLED = "network_scoring_ui_enabled";
        public static final String NETWORK_SWITCH_NOTIFICATION_DAILY_LIMIT = "network_switch_notification_daily_limit";
        public static final String NETWORK_SWITCH_NOTIFICATION_RATE_LIMIT_MILLIS = "network_switch_notification_rate_limit_millis";
        public static final String NETWORK_WATCHLIST_ENABLED = "network_watchlist_enabled";
        public static final String NETWORK_WATCHLIST_LAST_REPORT_TIME = "network_watchlist_last_report_time";
        public static final String NEW_CONTACT_AGGREGATOR = "new_contact_aggregator";
        public static final String NIGHT_DISPLAY_FORCED_AUTO_MODE_AVAILABLE = "night_display_forced_auto_mode_available";
        public static final String NITZ_UPDATE_DIFF = "nitz_update_diff";
        public static final String NITZ_UPDATE_SPACING = "nitz_update_spacing";
        public static final String NOTIFICATION_BUBBLES = "notification_bubbles";
        public static final String NOTIFICATION_SNOOZE_OPTIONS = "notification_snooze_options";
        public static final String NR_NSA_TRACKING_SCREEN_OFF_MODE = "nr_nsa_tracking_screen_off_mode";
        public static final String NSD_ON = "nsd_on";
        public static final String NTP_SERVER = "ntp_server";
        public static final String NTP_TIMEOUT = "ntp_timeout";

        @SystemApi
        public static final String OTA_DISABLE_AUTOMATIC_UPDATE = "ota_disable_automatic_update";
        public static final String OVERLAY_DISPLAY_DEVICES = "overlay_display_devices";
        public static final String OVERRIDE_SETTINGS_PROVIDER_RESTORE_ANY_VERSION = "override_settings_provider_restore_any_version";
        public static final String PACKAGE_VERIFIER_DEFAULT_RESPONSE = "verifier_default_response";
        public static final String PACKAGE_VERIFIER_INCLUDE_ADB = "verifier_verify_adb_installs";
        public static final String PACKAGE_VERIFIER_SETTING_VISIBLE = "verifier_setting_visible";
        public static final String PACKAGE_VERIFIER_TIMEOUT = "verifier_timeout";
        public static final String PAC_CHANGE_DELAY = "pac_change_delay";
        public static final String PDP_WATCHDOG_ERROR_POLL_COUNT = "pdp_watchdog_error_poll_count";
        public static final String PDP_WATCHDOG_ERROR_POLL_INTERVAL_MS = "pdp_watchdog_error_poll_interval_ms";
        public static final String PDP_WATCHDOG_LONG_POLL_INTERVAL_MS = "pdp_watchdog_long_poll_interval_ms";
        public static final String PDP_WATCHDOG_MAX_PDP_RESET_FAIL_COUNT = "pdp_watchdog_max_pdp_reset_fail_count";
        public static final String PDP_WATCHDOG_POLL_INTERVAL_MS = "pdp_watchdog_poll_interval_ms";
        public static final String PDP_WATCHDOG_TRIGGER_PACKET_COUNT = "pdp_watchdog_trigger_packet_count";
        public static final String POLICY_CONTROL = "policy_control";
        public static final String POWER_BUTTON_LONG_PRESS = "power_button_long_press";
        public static final String POWER_BUTTON_SUPPRESSION_DELAY_AFTER_GESTURE_WAKE = "power_button_suppression_delay_after_gesture_wake";
        public static final String POWER_BUTTON_VERY_LONG_PRESS = "power_button_very_long_press";
        public static final String POWER_MANAGER_CONSTANTS = "power_manager_constants";
        public static final String POWER_SOUNDS_ENABLED = "power_sounds_enabled";
        public static final String PREFERRED_NETWORK_MODE = "preferred_network_mode";
        public static final String PRIVATE_DNS_DEFAULT_MODE = "private_dns_default_mode";
        public static final String PRIVATE_DNS_MODE = "private_dns_mode";
        public static final String PRIVATE_DNS_SPECIFIER = "private_dns_specifier";
        public static final String PROVISIONING_APN_ALARM_DELAY_IN_MS = "provisioning_apn_alarm_delay_in_ms";
        public static final String RADIO_BLUETOOTH = "bluetooth";
        public static final String RADIO_BUG_SYSTEM_ERROR_COUNT_THRESHOLD = "radio_bug_system_error_count_threshold";
        public static final String RADIO_BUG_WAKELOCK_TIMEOUT_COUNT_THRESHOLD = "radio_bug_wakelock_timeout_count_threshold";
        public static final String RADIO_CELL = "cell";
        public static final String RADIO_NFC = "nfc";
        public static final String RADIO_WIFI = "wifi";
        public static final String RADIO_WIMAX = "wimax";
        public static final String READ_EXTERNAL_STORAGE_ENFORCED_DEFAULT = "read_external_storage_enforced_default";
        public static final String RECOMMENDED_NETWORK_EVALUATOR_CACHE_EXPIRY_MS = "recommended_network_evaluator_cache_expiry_ms";

        @SystemApi
        public static final String REQUIRE_PASSWORD_TO_DECRYPT = "require_password_to_decrypt";
        public static final String SAFE_BOOT_DISALLOWED = "safe_boot_disallowed";
        public static final String SELINUX_STATUS = "selinux_status";
        public static final String SELINUX_UPDATE_CONTENT_URL = "selinux_content_url";
        public static final String SELINUX_UPDATE_METADATA_URL = "selinux_metadata_url";
        public static final String SEND_ACTION_APP_ERROR = "send_action_app_error";
        public static final String SETTINGS_USE_EXTERNAL_PROVIDER_API = "settings_use_external_provider_api";
        public static final String SETTINGS_USE_PSD_API = "settings_use_psd_api";
        public static final String SETUP_PREPAID_DATA_SERVICE_URL = "setup_prepaid_data_service_url";
        public static final String SETUP_PREPAID_DETECTION_REDIR_HOST = "setup_prepaid_detection_redir_host";
        public static final String SETUP_PREPAID_DETECTION_TARGET_URL = "setup_prepaid_detection_target_url";
        public static final String SET_GLOBAL_HTTP_PROXY = "set_global_http_proxy";
        public static final String SET_INSTALL_LOCATION = "set_install_location";
        public static final String SHORTCUT_MANAGER_CONSTANTS = "shortcut_manager_constants";
        public static final String SHOW_FIRST_CRASH_DIALOG = "show_first_crash_dialog";
        public static final String SHOW_HIDDEN_LAUNCHER_ICON_APPS_ENABLED = "show_hidden_icon_apps_enabled";
        public static final String SHOW_MEDIA_ON_QUICK_SETTINGS = "qs_media_player";
        public static final String SHOW_MUTE_IN_CRASH_DIALOG = "show_mute_in_crash_dialog";
        public static final String SHOW_NEW_APP_INSTALLED_NOTIFICATION_ENABLED = "show_new_app_installed_notification_enabled";
        public static final String SHOW_NOTIFICATION_CHANNEL_WARNINGS = "show_notification_channel_warnings";

        @Deprecated
        public static final String SHOW_PROCESSES = "show_processes";
        public static final String SHOW_RESTART_IN_CRASH_DIALOG = "show_restart_in_crash_dialog";
        public static final String SHOW_TEMPERATURE_WARNING = "show_temperature_warning";
        public static final String SHOW_USB_TEMPERATURE_ALARM = "show_usb_temperature_alarm";

        @Deprecated
        public static final String SHOW_ZEN_SETTINGS_SUGGESTION = "show_zen_settings_suggestion";

        @Deprecated
        public static final String SHOW_ZEN_UPGRADE_NOTIFICATION = "show_zen_upgrade_notification";
        public static final String SIGNED_CONFIG_VERSION = "signed_config_version";
        public static final String SMART_REPLIES_IN_NOTIFICATIONS_FLAGS = "smart_replies_in_notifications_flags";
        public static final String SMART_SELECTION_UPDATE_CONTENT_URL = "smart_selection_content_url";
        public static final String SMART_SELECTION_UPDATE_METADATA_URL = "smart_selection_metadata_url";
        public static final String SMART_SUGGESTIONS_IN_NOTIFICATIONS_FLAGS = "smart_suggestions_in_notifications_flags";
        public static final String SMS_OUTGOING_CHECK_INTERVAL_MS = "sms_outgoing_check_interval_ms";
        public static final String SMS_OUTGOING_CHECK_MAX_COUNT = "sms_outgoing_check_max_count";
        public static final String SMS_SHORT_CODES_UPDATE_CONTENT_URL = "sms_short_codes_content_url";
        public static final String SMS_SHORT_CODES_UPDATE_METADATA_URL = "sms_short_codes_metadata_url";
        public static final String SMS_SHORT_CODE_CONFIRMATION = "sms_short_code_confirmation";
        public static final String SMS_SHORT_CODE_RULE = "sms_short_code_rule";
        public static final String SOFT_AP_TIMEOUT_ENABLED = "soft_ap_timeout_enabled";
        public static final String SOUND_TRIGGER_DETECTION_SERVICE_OP_TIMEOUT = "sound_trigger_detection_service_op_timeout";
        public static final String SPEED_LABEL_CACHE_EVICTION_AGE_MILLIS = "speed_label_cache_eviction_age_millis";
        public static final String SQLITE_COMPATIBILITY_WAL_FLAGS = "sqlite_compatibility_wal_flags";
        public static final String STAY_ON_WHILE_PLUGGED_IN = "stay_on_while_plugged_in";
        public static final String STORAGE_BENCHMARK_INTERVAL = "storage_benchmark_interval";
        public static final String STORAGE_SETTINGS_CLOBBER_THRESHOLD = "storage_settings_clobber_threshold";
        public static final String SYNC_MANAGER_CONSTANTS = "sync_manager_constants";
        public static final String SYNC_MAX_RETRY_DELAY_IN_SECONDS = "sync_max_retry_delay_in_seconds";
        public static final String SYS_FREE_STORAGE_LOG_INTERVAL = "sys_free_storage_log_interval";
        public static final String SYS_STORAGE_CACHE_MAX_BYTES = "sys_storage_cache_max_bytes";
        public static final String SYS_STORAGE_CACHE_PERCENTAGE = "sys_storage_cache_percentage";
        public static final String SYS_STORAGE_FULL_THRESHOLD_BYTES = "sys_storage_full_threshold_bytes";
        public static final String SYS_STORAGE_THRESHOLD_MAX_BYTES = "sys_storage_threshold_max_bytes";
        public static final String SYS_STORAGE_THRESHOLD_PERCENTAGE = "sys_storage_threshold_percentage";
        public static final String SYS_TRACED = "sys_traced";
        public static final String SYS_UIDCPUPOWER = "sys_uidcpupower";
        public static final String SYS_VDSO = "sys_vdso";
        public static final String TCP_DEFAULT_INIT_RWND = "tcp_default_init_rwnd";
        public static final String TETHER_DUN_APN = "tether_dun_apn";
        public static final String TETHER_DUN_REQUIRED = "tether_dun_required";
        public static final String TETHER_ENABLE_LEGACY_DHCP_SERVER = "tether_enable_legacy_dhcp_server";

        @SystemApi
        public static final String TETHER_OFFLOAD_DISABLED = "tether_offload_disabled";

        @SystemApi
        public static final String TETHER_SUPPORTED = "tether_supported";
        public static final String TEXT_CLASSIFIER_ACTION_MODEL_PARAMS = "text_classifier_action_model_params";
        public static final String TEXT_CLASSIFIER_CONSTANTS = "text_classifier_constants";

        @SystemApi
        public static final String THEATER_MODE_ON = "theater_mode_on";
        public static final String TIME_ONLY_MODE_CONSTANTS = "time_only_mode_constants";
        public static final String TIME_REMAINING_ESTIMATE_BASED_ON_USAGE = "time_remaining_estimate_based_on_usage";
        public static final String TIME_REMAINING_ESTIMATE_MILLIS = "time_remaining_estimate_millis";
        public static final String TRANSITION_ANIMATION_SCALE = "transition_animation_scale";
        public static final String TRUSTED_SOUND = "trusted_sound";
        public static final String TZINFO_UPDATE_CONTENT_URL = "tzinfo_content_url";
        public static final String TZINFO_UPDATE_METADATA_URL = "tzinfo_metadata_url";
        public static final String UNGAZE_SLEEP_ENABLED = "ungaze_sleep_enabled";
        public static final String UNINSTALLED_INSTANT_APP_MAX_CACHE_PERIOD = "uninstalled_instant_app_max_cache_period";
        public static final String UNINSTALLED_INSTANT_APP_MIN_CACHE_PERIOD = "uninstalled_instant_app_min_cache_period";
        public static final String UNLOCK_SOUND = "unlock_sound";
        public static final String UNUSED_STATIC_SHARED_LIB_MIN_CACHE_PERIOD = "unused_static_shared_lib_min_cache_period";
        public static final String USB_MASS_STORAGE_ENABLED = "usb_mass_storage_enabled";
        public static final String USER_ABSENT_RADIOS_OFF_FOR_SMALL_BATTERY_ENABLED = "user_absent_radios_off_for_small_battery_enabled";
        public static final String USER_ABSENT_TOUCH_OFF_FOR_SMALL_BATTERY_ENABLED = "user_absent_touch_off_for_small_battery_enabled";
        public static final String USER_SWITCHER_ENABLED = "user_switcher_enabled";
        public static final String USE_GOOGLE_MAIL = "use_google_mail";
        public static final String USE_OPEN_WIFI_PACKAGE = "use_open_wifi_package";

        @Deprecated
        public static final String VT_IMS_ENABLED = "vt_ims_enabled";
        public static final String WAIT_FOR_DEBUGGER = "wait_for_debugger";
        public static final String WARNING_TEMPERATURE = "warning_temperature";
        public static final String WEBVIEW_DATA_REDUCTION_PROXY_KEY = "webview_data_reduction_proxy_key";
        public static final String WEBVIEW_FALLBACK_LOGIC_ENABLED = "webview_fallback_logic_enabled";

        @SystemApi
        public static final String WEBVIEW_MULTIPROCESS = "webview_multiprocess";
        public static final String WEBVIEW_PROVIDER = "webview_provider";

        @Deprecated
        public static final String WFC_IMS_ENABLED = "wfc_ims_enabled";

        @Deprecated
        public static final String WFC_IMS_MODE = "wfc_ims_mode";

        @Deprecated
        public static final String WFC_IMS_ROAMING_ENABLED = "wfc_ims_roaming_enabled";

        @Deprecated
        public static final String WFC_IMS_ROAMING_MODE = "wfc_ims_roaming_mode";
        public static final String WIFI_ALWAYS_REQUESTED = "wifi_always_requested";

        @SystemApi
        public static final String WIFI_BADGING_THRESHOLDS = "wifi_badging_thresholds";
        public static final String WIFI_BOUNCE_DELAY_OVERRIDE_MS = "wifi_bounce_delay_override_ms";

        @Deprecated
        public static final String WIFI_CONNECTED_MAC_RANDOMIZATION_ENABLED = "wifi_connected_mac_randomization_enabled";
        public static final String WIFI_COUNTRY_CODE = "wifi_country_code";
        public static final String WIFI_DEVICE_OWNER_CONFIGS_LOCKDOWN = "wifi_device_owner_configs_lockdown";
        public static final String WIFI_DISPLAY_CERTIFICATION_ON = "wifi_display_certification_on";
        public static final String WIFI_DISPLAY_ON = "wifi_display_on";
        public static final String WIFI_DISPLAY_WPS_CONFIG = "wifi_display_wps_config";
        public static final String WIFI_ENHANCED_AUTO_JOIN = "wifi_enhanced_auto_join";
        public static final String WIFI_EPHEMERAL_OUT_OF_RANGE_TIMEOUT_MS = "wifi_ephemeral_out_of_range_timeout_ms";
        public static final String WIFI_FRAMEWORK_SCAN_INTERVAL_MS = "wifi_framework_scan_interval_ms";
        public static final String WIFI_FREQUENCY_BAND = "wifi_frequency_band";
        public static final String WIFI_IDLE_MS = "wifi_idle_ms";
        public static final String WIFI_MAX_DHCP_RETRY_COUNT = "wifi_max_dhcp_retry_count";
        public static final String WIFI_MIGRATION_COMPLETED = "wifi_migration_completed";
        public static final String WIFI_MOBILE_DATA_TRANSITION_WAKELOCK_TIMEOUT_MS = "wifi_mobile_data_transition_wakelock_timeout_ms";

        @Deprecated
        public static final String WIFI_NETWORKS_AVAILABLE_NOTIFICATION_ON = "wifi_networks_available_notification_on";

        @Deprecated
        public static final String WIFI_NETWORKS_AVAILABLE_REPEAT_DELAY = "wifi_networks_available_repeat_delay";
        public static final String WIFI_NETWORK_SHOW_RSSI = "wifi_network_show_rssi";

        @Deprecated
        public static final String WIFI_NUM_OPEN_NETWORKS_KEPT = "wifi_num_open_networks_kept";
        public static final String WIFI_ON = "wifi_on";
        public static final String WIFI_ON_WHEN_PROXY_DISCONNECTED = "wifi_on_when_proxy_disconnected";
        public static final String WIFI_P2P_DEVICE_NAME = "wifi_p2p_device_name";
        public static final String WIFI_P2P_PENDING_FACTORY_RESET = "wifi_p2p_pending_factory_reset";
        public static final String WIFI_SCAN_ALWAYS_AVAILABLE = "wifi_scan_always_enabled";
        public static final String WIFI_SCAN_INTERVAL_WHEN_P2P_CONNECTED_MS = "wifi_scan_interval_p2p_connected_ms";
        public static final String WIFI_SCAN_THROTTLE_ENABLED = "wifi_scan_throttle_enabled";
        public static final String WIFI_SCORE_PARAMS = "wifi_score_params";

        @Deprecated
        public static final String WIFI_SLEEP_POLICY = "wifi_sleep_policy";

        @Deprecated
        public static final int WIFI_SLEEP_POLICY_DEFAULT = 0;

        @Deprecated
        public static final int WIFI_SLEEP_POLICY_NEVER = 2;

        @Deprecated
        public static final int WIFI_SLEEP_POLICY_NEVER_WHILE_PLUGGED = 1;
        public static final String WIFI_SUPPLICANT_SCAN_INTERVAL_MS = "wifi_supplicant_scan_interval_ms";
        public static final String WIFI_VERBOSE_LOGGING_ENABLED = "wifi_verbose_logging_enabled";

        @SystemApi
        @Deprecated
        public static final String WIFI_WAKEUP_ENABLED = "wifi_wakeup_enabled";
        public static final String WIFI_WATCHDOG_ON = "wifi_watchdog_on";
        public static final String WIFI_WATCHDOG_POOR_NETWORK_TEST_ENABLED = "wifi_watchdog_poor_network_test_enabled";
        public static final String WIMAX_NETWORKS_AVAILABLE_NOTIFICATION_ON = "wimax_networks_available_notification_on";
        public static final String WINDOW_ANIMATION_SCALE = "window_animation_scale";
        public static final String WIRELESS_CHARGING_STARTED_SOUND = "wireless_charging_started_sound";
        public static final String WTF_IS_FATAL = "wtf_is_fatal";

        @Deprecated
        public static final String ZEN_DURATION = "zen_duration";

        @Deprecated
        public static final int ZEN_DURATION_FOREVER = 0;

        @Deprecated
        public static final int ZEN_DURATION_PROMPT = -1;
        public static final String ZEN_MODE = "zen_mode";
        public static final int ZEN_MODE_ALARMS = 3;
        public static final String ZEN_MODE_CONFIG_ETAG = "zen_mode_config_etag";
        public static final int ZEN_MODE_IMPORTANT_INTERRUPTIONS = 1;
        public static final int ZEN_MODE_NO_INTERRUPTIONS = 2;
        public static final int ZEN_MODE_OFF = 0;
        public static final String ZEN_MODE_RINGER_LEVEL = "zen_mode_ringer_level";

        @Deprecated
        public static final String ZEN_SETTINGS_SUGGESTION_VIEWED = "zen_settings_suggestion_viewed";

        @Deprecated
        public static final String ZEN_SETTINGS_UPDATED = "zen_settings_updated";
        public static final String ZRAM_ENABLED = "zram_enabled";
        public static final Uri CONTENT_URI = Uri.parse("content://settings/global");
        public static final String LOCATION_GLOBAL_KILL_SWITCH = "location_global_kill_switch";
        public static final String[] TRANSIENT_SETTINGS = {LOCATION_GLOBAL_KILL_SWITCH};
        public static final String[] LEGACY_RESTORE_SETTINGS = new String[0];
        private static final ContentProviderHolder sProviderHolder = new ContentProviderHolder(CONTENT_URI);
        private static final NameValueCache sNameValueCache = new NameValueCache(CONTENT_URI, Settings.CALL_METHOD_GET_GLOBAL, Settings.CALL_METHOD_PUT_GLOBAL, sProviderHolder);

        public static boolean isValidZenMode(int i) {
            return i == 0 || i == 1 || i == 2 || i == 3;
        }

        public static String zenModeToString(int i) {
            return i == 1 ? "ZEN_MODE_IMPORTANT_INTERRUPTIONS" : i == 3 ? "ZEN_MODE_ALARMS" : i == 2 ? "ZEN_MODE_NO_INTERRUPTIONS" : "ZEN_MODE_OFF";
        }

        static {
            HashSet<String> hashSet = new HashSet<>(8);
            MOVED_TO_SECURE = hashSet;
            hashSet.add("install_non_market_apps");
            MOVED_TO_SECURE.add("zen_duration");
            MOVED_TO_SECURE.add("show_zen_upgrade_notification");
            MOVED_TO_SECURE.add("show_zen_settings_suggestion");
            MOVED_TO_SECURE.add("zen_settings_updated");
            MOVED_TO_SECURE.add("zen_settings_suggestion_viewed");
            MOVED_TO_SECURE.add("charging_sounds_enabled");
            MOVED_TO_SECURE.add("charging_vibration_enabled");
            MULTI_SIM_USER_PREFERRED_SUBS = new String[]{"user_preferred_sub1", "user_preferred_sub2", "user_preferred_sub3"};
            ArraySet arraySet = new ArraySet();
            INSTANT_APP_SETTINGS = arraySet;
            arraySet.add("wait_for_debugger");
            INSTANT_APP_SETTINGS.add("device_provisioned");
            INSTANT_APP_SETTINGS.add(DEVELOPMENT_FORCE_RESIZABLE_ACTIVITIES);
            INSTANT_APP_SETTINGS.add(DEVELOPMENT_FORCE_RTL);
            INSTANT_APP_SETTINGS.add(EPHEMERAL_COOKIE_MAX_SIZE_BYTES);
            INSTANT_APP_SETTINGS.add("airplane_mode_on");
            INSTANT_APP_SETTINGS.add("window_animation_scale");
            INSTANT_APP_SETTINGS.add("transition_animation_scale");
            INSTANT_APP_SETTINGS.add("animator_duration_scale");
            INSTANT_APP_SETTINGS.add(DEBUG_VIEW_ATTRIBUTES);
            INSTANT_APP_SETTINGS.add(DEBUG_VIEW_ATTRIBUTES_APPLICATION_PACKAGE);
            INSTANT_APP_SETTINGS.add(WTF_IS_FATAL);
            INSTANT_APP_SETTINGS.add(SEND_ACTION_APP_ERROR);
            INSTANT_APP_SETTINGS.add(ZEN_MODE);
        }

        public static void getMovedToSecureSettings(Set<String> set) {
            set.addAll(MOVED_TO_SECURE);
        }

        public static void clearProviderForTest() {
            sProviderHolder.clearProviderForTest();
            sNameValueCache.clearGenerationTrackerForTest();
        }

        public static String getString(ContentResolver contentResolver, String str) {
            return getStringForUser(contentResolver, str, contentResolver.getUserId());
        }

        public static String getStringForUser(ContentResolver contentResolver, String str, int i) {
            if (MOVED_TO_SECURE.contains(str)) {
                Log.w(Settings.TAG, "Setting " + str + " has moved from android.provider.Settings.Global to android.provider.Settings.Secure, returning read-only value.");
                return Secure.getStringForUser(contentResolver, str, i);
            }
            return sNameValueCache.getStringForUser(contentResolver, str, i);
        }

        public static boolean putString(ContentResolver contentResolver, String str, String str2) {
            return putStringForUser(contentResolver, str, str2, null, false, contentResolver.getUserId(), false);
        }

        public static boolean putString(ContentResolver contentResolver, String str, String str2, String str3, boolean z, boolean z2) {
            return putStringForUser(contentResolver, str, str2, str3, z, contentResolver.getUserId(), z2);
        }

        @SystemApi
        public static boolean putString(ContentResolver contentResolver, String str, String str2, String str3, boolean z) {
            return putStringForUser(contentResolver, str, str2, str3, z, contentResolver.getUserId(), false);
        }

        @SystemApi
        public static void resetToDefaults(ContentResolver contentResolver, String str) {
            resetToDefaultsAsUser(contentResolver, str, 1, contentResolver.getUserId());
        }

        public static void resetToDefaultsAsUser(ContentResolver contentResolver, String str, int i, int i2) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt(Settings.CALL_METHOD_USER_KEY, i2);
                if (str != null) {
                    bundle.putString(Settings.CALL_METHOD_TAG_KEY, str);
                }
                bundle.putInt(Settings.CALL_METHOD_RESET_MODE_KEY, i);
                sProviderHolder.getProvider(contentResolver).call(contentResolver.getPackageName(), contentResolver.getAttributionTag(), sProviderHolder.mUri.getAuthority(), Settings.CALL_METHOD_RESET_GLOBAL, null, bundle);
            } catch (RemoteException e) {
                Log.w(Settings.TAG, "Can't reset do defaults for " + CONTENT_URI, e);
            }
        }

        public static boolean putStringForUser(ContentResolver contentResolver, String str, String str2, int i) {
            return putStringForUser(contentResolver, str, str2, null, false, i, false);
        }

        public static boolean putStringForUser(ContentResolver contentResolver, String str, String str2, String str3, boolean z, int i, boolean z2) {
            if (MOVED_TO_SECURE.contains(str)) {
                Log.w(Settings.TAG, "Setting " + str + " has moved from android.provider.Settings.Global to android.provider.Settings.Secure, value is unchanged.");
                return Secure.putStringForUser(contentResolver, str, str2, str3, z, i, z2);
            }
            return sNameValueCache.putStringForUser(contentResolver, str, str2, str3, z, i, z2);
        }

        public static Uri getUriFor(String str) {
            return getUriFor(CONTENT_URI, str);
        }

        public static int getInt(ContentResolver contentResolver, String str, int i) {
            String string = getString(contentResolver, str);
            if (string == null) {
                return i;
            }
            try {
                return Integer.parseInt(string);
            } catch (NumberFormatException unused) {
                return i;
            }
        }

        public static int getInt(ContentResolver contentResolver, String str) throws SettingNotFoundException {
            try {
                return Integer.parseInt(getString(contentResolver, str));
            } catch (NumberFormatException unused) {
                throw new SettingNotFoundException(str);
            }
        }

        public static boolean putInt(ContentResolver contentResolver, String str, int i) {
            return putString(contentResolver, str, Integer.toString(i));
        }

        public static long getLong(ContentResolver contentResolver, String str, long j) {
            String string = getString(contentResolver, str);
            if (string == null) {
                return j;
            }
            try {
                return Long.parseLong(string);
            } catch (NumberFormatException unused) {
                return j;
            }
        }

        public static long getLong(ContentResolver contentResolver, String str) throws SettingNotFoundException {
            try {
                return Long.parseLong(getString(contentResolver, str));
            } catch (NumberFormatException unused) {
                throw new SettingNotFoundException(str);
            }
        }

        public static boolean putLong(ContentResolver contentResolver, String str, long j) {
            return putString(contentResolver, str, Long.toString(j));
        }

        public static float getFloat(ContentResolver contentResolver, String str, float f) {
            String string = getString(contentResolver, str);
            if (string == null) {
                return f;
            }
            try {
                return Float.parseFloat(string);
            } catch (NumberFormatException unused) {
                return f;
            }
        }

        public static float getFloat(ContentResolver contentResolver, String str) throws SettingNotFoundException {
            String string = getString(contentResolver, str);
            if (string == null) {
                throw new SettingNotFoundException(str);
            }
            try {
                return Float.parseFloat(string);
            } catch (NumberFormatException unused) {
                throw new SettingNotFoundException(str);
            }
        }

        public static boolean putFloat(ContentResolver contentResolver, String str, float f) {
            return putString(contentResolver, str, Float.toString(f));
        }
    }

    public static final class Config extends NameValueTable {
        private static final ContentProviderHolder sProviderHolder = new ContentProviderHolder(DeviceConfig.CONTENT_URI);
        private static final NameValueCache sNameValueCache = new NameValueCache(DeviceConfig.CONTENT_URI, Settings.CALL_METHOD_GET_CONFIG, Settings.CALL_METHOD_PUT_CONFIG, Settings.CALL_METHOD_LIST_CONFIG, Settings.CALL_METHOD_SET_ALL_CONFIG, sProviderHolder);

        static String getString(ContentResolver contentResolver, String str) {
            return sNameValueCache.getStringForUser(contentResolver, str, contentResolver.getUserId());
        }

        public static Map<String, String> getStrings(ContentResolver contentResolver, String str, List<String> list) {
            ArrayList arrayList = new ArrayList(list.size());
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(createCompositeName(str, it.next()));
            }
            String strCreatePrefix = createPrefix(str);
            ArrayMap<String, String> stringsForPrefix = sNameValueCache.getStringsForPrefix(contentResolver, strCreatePrefix, arrayList);
            int size = stringsForPrefix.size();
            int length = strCreatePrefix.length();
            ArrayMap arrayMap = new ArrayMap(size);
            for (int i = 0; i < size; i++) {
                arrayMap.put(stringsForPrefix.keyAt(i).substring(length), stringsForPrefix.valueAt(i));
            }
            return arrayMap;
        }

        static boolean putString(ContentResolver contentResolver, String str, String str2, String str3, boolean z) {
            return sNameValueCache.putStringForUser(contentResolver, createCompositeName(str, str2), str3, null, z, contentResolver.getUserId(), false);
        }

        public static boolean setStrings(ContentResolver contentResolver, String str, Map<String, String> map) throws DeviceConfig.BadConfigException {
            HashMap<String, String> map2 = new HashMap<>(map.keySet().size());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                map2.put(createCompositeName(str, entry.getKey()), entry.getValue());
            }
            if (sNameValueCache.setStringsForPrefix(contentResolver, createPrefix(str), map2)) {
                return true;
            }
            throw new DeviceConfig.BadConfigException();
        }

        static void resetToDefaults(ContentResolver contentResolver, int i, String str) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt(Settings.CALL_METHOD_USER_KEY, contentResolver.getUserId());
                bundle.putInt(Settings.CALL_METHOD_RESET_MODE_KEY, i);
                if (str != null) {
                    bundle.putString(Settings.CALL_METHOD_PREFIX_KEY, createPrefix(str));
                }
                sProviderHolder.getProvider(contentResolver).call(contentResolver.getPackageName(), contentResolver.getAttributionTag(), sProviderHolder.mUri.getAuthority(), Settings.CALL_METHOD_RESET_CONFIG, null, bundle);
            } catch (RemoteException e) {
                Log.w(Settings.TAG, "Can't reset to defaults for " + DeviceConfig.CONTENT_URI, e);
            }
        }

        public static void registerMonitorCallback(ContentResolver contentResolver, RemoteCallback remoteCallback) {
            registerMonitorCallbackAsUser(contentResolver, contentResolver.getUserId(), remoteCallback);
        }

        private static void registerMonitorCallbackAsUser(ContentResolver contentResolver, int i, RemoteCallback remoteCallback) {
            try {
                Bundle bundle = new Bundle();
                bundle.putInt(Settings.CALL_METHOD_USER_KEY, i);
                bundle.putParcelable(Settings.CALL_METHOD_MONITOR_CALLBACK_KEY, remoteCallback);
                sProviderHolder.getProvider(contentResolver).call(contentResolver.getPackageName(), contentResolver.getAttributionTag(), sProviderHolder.mUri.getAuthority(), Settings.CALL_METHOD_REGISTER_MONITOR_CALLBACK_CONFIG, null, bundle);
            } catch (RemoteException e) {
                Log.w(Settings.TAG, "Can't register config monitor callback", e);
            }
        }

        public static void clearProviderForTest() {
            sProviderHolder.clearProviderForTest();
            sNameValueCache.clearGenerationTrackerForTest();
        }

        private static String createCompositeName(String str, String str2) {
            Preconditions.checkNotNull(str);
            Preconditions.checkNotNull(str2);
            return createPrefix(str) + str2;
        }

        private static String createPrefix(String str) {
            Preconditions.checkNotNull(str);
            return str + "/";
        }
    }

    public static final class Bookmarks implements BaseColumns {
        public static final String FOLDER = "folder";
        public static final String ID = "_id";
        public static final String INTENT = "intent";
        public static final String ORDERING = "ordering";
        public static final String SHORTCUT = "shortcut";
        private static final String TAG = "Bookmarks";
        public static final String TITLE = "title";
        private static final String sShortcutSelection = "shortcut=?";
        public static final Uri CONTENT_URI = Uri.parse("content://settings/bookmarks");
        private static final String[] sIntentProjection = {"intent"};
        private static final String[] sShortcutProjection = {"_id", "shortcut"};

        public static CharSequence getLabelForFolder(Resources resources, String str) {
            return str;
        }

        public static Intent getIntentForShortcut(ContentResolver contentResolver, char c) {
            Cursor cursorQuery = contentResolver.query(CONTENT_URI, sIntentProjection, sShortcutSelection, new String[]{String.valueOf((int) c)}, ORDERING);
            Intent uri = null;
            while (uri == null) {
                try {
                    if (!cursorQuery.moveToNext()) {
                        break;
                    }
                    try {
                        uri = Intent.parseUri(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("intent")), 0);
                    } catch (IllegalArgumentException e) {
                        Log.w(TAG, "Intent column not found", e);
                    } catch (URISyntaxException unused) {
                    }
                } finally {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                }
            }
            return uri;
        }

        public static Uri add(ContentResolver contentResolver, Intent intent, String str, String str2, char c, int i) {
            if (c != 0) {
                contentResolver.delete(CONTENT_URI, sShortcutSelection, new String[]{String.valueOf((int) c)});
            }
            ContentValues contentValues = new ContentValues();
            if (str != null) {
                contentValues.put("title", str);
            }
            if (str2 != null) {
                contentValues.put("folder", str2);
            }
            contentValues.put("intent", intent.toUri(0));
            if (c != 0) {
                contentValues.put("shortcut", Integer.valueOf(c));
            }
            contentValues.put(ORDERING, Integer.valueOf(i));
            return contentResolver.insert(CONTENT_URI, contentValues);
        }

        public static CharSequence getTitle(Context context, Cursor cursor) {
            int columnIndex = cursor.getColumnIndex("title");
            int columnIndex2 = cursor.getColumnIndex("intent");
            if (columnIndex == -1 || columnIndex2 == -1) {
                throw new IllegalArgumentException("The cursor must contain the TITLE and INTENT columns.");
            }
            String string = cursor.getString(columnIndex);
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            String string2 = cursor.getString(columnIndex2);
            if (TextUtils.isEmpty(string2)) {
                return "";
            }
            try {
                Intent uri = Intent.parseUri(string2, 0);
                PackageManager packageManager = context.getPackageManager();
                ResolveInfo resolveInfoResolveActivity = packageManager.resolveActivity(uri, 0);
                return resolveInfoResolveActivity != null ? resolveInfoResolveActivity.loadLabel(packageManager) : "";
            } catch (URISyntaxException unused) {
                return "";
            }
        }
    }

    public static final class Panel {
        public static final String ACTION_INTERNET_CONNECTIVITY = "android.settings.panel.action.INTERNET_CONNECTIVITY";
        public static final String ACTION_NFC = "android.settings.panel.action.NFC";
        public static final String ACTION_VOLUME = "android.settings.panel.action.VOLUME";
        public static final String ACTION_WIFI = "android.settings.panel.action.WIFI";

        private Panel() {
        }
    }

    public static boolean isCallingPackageAllowedToWriteSettings(Context context, int i, String str, boolean z) {
        return isCallingPackageAllowedToPerformAppOpsProtectedOperation(context, i, str, z, 23, PM_WRITE_SETTINGS, false);
    }

    @SystemApi
    public static boolean checkAndNoteWriteSettingsOperation(Context context, int i, String str, boolean z) {
        return isCallingPackageAllowedToPerformAppOpsProtectedOperation(context, i, str, z, 23, PM_WRITE_SETTINGS, true);
    }

    public static boolean checkAndNoteChangeNetworkStateOperation(Context context, int i, String str, boolean z) {
        if (context.checkCallingOrSelfPermission(Manifest.permission.CHANGE_NETWORK_STATE) == 0) {
            return true;
        }
        return isCallingPackageAllowedToPerformAppOpsProtectedOperation(context, i, str, z, 23, PM_CHANGE_NETWORK_STATE, true);
    }

    public static boolean isCallingPackageAllowedToDrawOverlays(Context context, int i, String str, boolean z) {
        return isCallingPackageAllowedToPerformAppOpsProtectedOperation(context, i, str, z, 24, PM_SYSTEM_ALERT_WINDOW, false);
    }

    public static boolean checkAndNoteDrawOverlaysOperation(Context context, int i, String str, boolean z) {
        return isCallingPackageAllowedToPerformAppOpsProtectedOperation(context, i, str, z, 24, PM_SYSTEM_ALERT_WINDOW, true);
    }

    public static boolean isCallingPackageAllowedToPerformAppOpsProtectedOperation(Context context, int i, String str, boolean z, int i2, String[] strArr, boolean z2) {
        int iCheckOpNoThrow;
        int i3 = 0;
        if (str == null) {
            return false;
        }
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        if (z2) {
            iCheckOpNoThrow = appOpsManager.noteOpNoThrow(i2, i, str);
        } else {
            iCheckOpNoThrow = appOpsManager.checkOpNoThrow(i2, i, str);
        }
        if (iCheckOpNoThrow == 0) {
            return true;
        }
        if (iCheckOpNoThrow == 3) {
            for (String str2 : strArr) {
                if (context.checkCallingOrSelfPermission(str2) == 0) {
                    return true;
                }
            }
        }
        if (!z) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" was not granted ");
        if (strArr.length > 1) {
            sb.append(" either of these permissions: ");
        } else {
            sb.append(" this permission: ");
        }
        while (i3 < strArr.length) {
            sb.append(strArr[i3]);
            sb.append(i3 == strArr.length - 1 ? MediaMetrics.SEPARATOR : ", ");
            i3++;
        }
        throw new SecurityException(sb.toString());
    }

    public static String getPackageNameForUid(Context context, int i) {
        String[] packagesForUid = context.getPackageManager().getPackagesForUid(i);
        if (packagesForUid == null) {
            return null;
        }
        return packagesForUid[0];
    }
}
