package android.app;

import android.Manifest;
import android.annotation.IntRange;
import android.annotation.NonNull;
import android.annotation.SystemApi;
import android.app.AppOpsManager;
import android.app.blob.XmlTags;
import android.compat.Compatibility;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ParceledListSlice;
import android.database.DatabaseUtils;
import android.media.MediaMetrics;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerExecutor;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteCallback;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.UserManager;
import android.provider.Settings;
import android.security.keystore.KeyProperties;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.LongSparseArray;
import android.util.LongSparseLongArray;
import android.util.Pools;
import android.util.SparseArray;
import com.android.internal.app.IAppOpsActiveCallback;
import com.android.internal.app.IAppOpsAsyncNotedCallback;
import com.android.internal.app.IAppOpsCallback;
import com.android.internal.app.IAppOpsNotedCallback;
import com.android.internal.app.IAppOpsService;
import com.android.internal.app.IAppOpsStartedCallback;
import com.android.internal.app.MessageSamplingConfig;
import com.android.internal.content.NativeLibraryHelper;
import com.android.internal.os.RuntimeInit;
import com.android.internal.os.ZygoteInit;
import com.android.internal.util.AnnotationValidations;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.Parcelling;
import com.android.internal.util.Preconditions;
import com.byd.mediacenter.api.media.MediaControl;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes.dex */
public class AppOpsManager {
    public static final long CALL_BACK_ON_CHANGED_LISTENER_WITH_SWITCHED_OP_CHANGE = 148180766;
    public static final int CALL_BACK_ON_SWITCHED_OP = 2;
    private static final int COLLECT_ASYNC = 3;
    private static final int COLLECT_SELF = 1;
    private static final int COLLECT_SYNC = 2;
    private static final String DEBUG_LOGGING_ENABLE_PROP = "appops.logging_enabled";
    private static final String DEBUG_LOGGING_OPS_PROP = "appops.logging_ops";
    private static final String DEBUG_LOGGING_PACKAGES_PROP = "appops.logging_packages";
    private static final String DEBUG_LOGGING_TAG = "AppOpsManager";
    private static final int DONT_COLLECT = 0;
    public static final int FILTER_BY_ATTRIBUTION_TAG = 4;
    public static final int FILTER_BY_OP_NAMES = 8;
    public static final int FILTER_BY_PACKAGE_NAME = 2;
    public static final int FILTER_BY_UID = 1;
    private static final int FLAGS_MASK = -1;
    public static final int HISTORICAL_MODE_DISABLED = 0;
    public static final int HISTORICAL_MODE_ENABLED_ACTIVE = 1;
    public static final int HISTORICAL_MODE_ENABLED_PASSIVE = 2;
    public static final String KEY_BG_STATE_SETTLE_TIME = "bg_state_settle_time";
    public static final String KEY_FG_SERVICE_STATE_SETTLE_TIME = "fg_service_state_settle_time";
    public static final String KEY_HISTORICAL_OPS = "historical_ops";
    public static final String KEY_TOP_STATE_SETTLE_TIME = "top_state_settle_time";
    public static final int MAX_PRIORITY_UID_STATE = 100;
    private static final int MAX_UNFORWARDED_OPS = 10;
    public static final int MIN_PRIORITY_UID_STATE = 700;
    public static final int MODE_ALLOWED = 0;
    public static final int MODE_DEFAULT = 3;
    public static final int MODE_ERRORED = 2;
    public static final int MODE_FOREGROUND = 4;
    public static final int MODE_IGNORED = 1;
    public static final boolean NOTE_OP_COLLECTION_ENABLED = false;
    public static final int OP_ACCEPT_HANDOVER = 74;
    public static final int OP_ACCESS_ACCESSIBILITY = 88;
    public static final int OP_ACCESS_MEDIA_LOCATION = 90;
    public static final int OP_ACCESS_NOTIFICATIONS = 25;
    public static final int OP_ACTIVATE_PLATFORM_VPN = 94;
    public static final int OP_ACTIVATE_VPN = 47;
    public static final int OP_ACTIVITY_RECOGNITION = 79;
    public static final int OP_ADD_VOICEMAIL = 52;
    public static final int OP_ANSWER_PHONE_CALLS = 69;
    public static final int OP_ASSIST_SCREENSHOT = 50;
    public static final int OP_ASSIST_STRUCTURE = 49;
    public static final int OP_AUDIO_ACCESSIBILITY_VOLUME = 64;
    public static final int OP_AUDIO_ALARM_VOLUME = 37;
    public static final int OP_AUDIO_BLUETOOTH_VOLUME = 39;
    public static final int OP_AUDIO_MASTER_VOLUME = 33;
    public static final int OP_AUDIO_MEDIA_VOLUME = 36;
    public static final int OP_AUDIO_NOTIFICATION_VOLUME = 38;
    public static final int OP_AUDIO_RING_VOLUME = 35;
    public static final int OP_AUDIO_VOICE_VOLUME = 34;
    public static final int OP_AUTO_REVOKE_MANAGED_BY_INSTALLER = 98;
    public static final int OP_AUTO_REVOKE_PERMISSIONS_IF_UNUSED = 97;
    public static final int OP_BIND_ACCESSIBILITY_SERVICE = 73;
    public static final int OP_BLUETOOTH_SCAN = 77;
    public static final int OP_BODY_SENSORS = 56;
    public static final int OP_CALL_PHONE = 13;
    public static final int OP_CAMERA = 26;
    public static final int OP_CHANGE_WIFI_STATE = 71;
    public static final int OP_COARSE_LOCATION = 0;
    private static final int OP_DEPRECATED_1 = 96;
    public static final int OP_FINE_LOCATION = 1;

    @SystemApi
    public static final int OP_FLAGS_ALL = 31;

    @SystemApi
    public static final int OP_FLAGS_ALL_TRUSTED = 13;

    @SystemApi
    public static final int OP_FLAG_SELF = 1;

    @SystemApi
    public static final int OP_FLAG_TRUSTED_PROXIED = 8;

    @SystemApi
    public static final int OP_FLAG_TRUSTED_PROXY = 2;

    @SystemApi
    public static final int OP_FLAG_UNTRUSTED_PROXIED = 16;

    @SystemApi
    public static final int OP_FLAG_UNTRUSTED_PROXY = 4;
    public static final int OP_GET_ACCOUNTS = 62;
    public static final int OP_GET_USAGE_STATS = 43;
    public static final int OP_GPS = 2;
    public static final int OP_INSTANT_APP_START_FOREGROUND = 68;
    public static final int OP_INTERACT_ACROSS_PROFILES = 93;
    public static final int OP_LEGACY_STORAGE = 87;
    public static final int OP_LOADER_USAGE_STATS = 95;
    public static final int OP_MANAGE_EXTERNAL_STORAGE = 92;
    public static final int OP_MANAGE_IPSEC_TUNNELS = 75;
    public static final int OP_MOCK_LOCATION = 58;
    public static final int OP_MONITOR_HIGH_POWER_LOCATION = 42;
    public static final int OP_MONITOR_LOCATION = 41;
    public static final int OP_MUTE_MICROPHONE = 44;
    public static final int OP_NEIGHBORING_CELLS = 12;
    public static final int OP_NONE = -1;
    public static final int OP_NO_ISOLATED_STORAGE = 99;
    public static final int OP_PHONE_CALL_CAMERA = 101;
    public static final int OP_PHONE_CALL_MICROPHONE = 100;
    public static final int OP_PICTURE_IN_PICTURE = 67;
    public static final int OP_PLAY_AUDIO = 28;
    public static final int OP_POST_NOTIFICATION = 11;
    public static final int OP_PROCESS_OUTGOING_CALLS = 54;
    public static final int OP_PROJECT_MEDIA = 46;
    public static final int OP_QUERY_ALL_PACKAGES = 91;
    public static final int OP_READ_CALENDAR = 8;
    public static final int OP_READ_CALL_LOG = 6;
    public static final int OP_READ_CELL_BROADCASTS = 57;
    public static final int OP_READ_CLIPBOARD = 29;
    public static final int OP_READ_CONTACTS = 4;
    public static final int OP_READ_DEVICE_IDENTIFIERS = 89;
    public static final int OP_READ_EXTERNAL_STORAGE = 59;
    public static final int OP_READ_ICC_SMS = 21;
    public static final int OP_READ_MEDIA_AUDIO = 81;
    public static final int OP_READ_MEDIA_IMAGES = 85;
    public static final int OP_READ_MEDIA_VIDEO = 83;
    public static final int OP_READ_PHONE_NUMBERS = 65;
    public static final int OP_READ_PHONE_STATE = 51;
    public static final int OP_READ_SMS = 14;
    public static final int OP_RECEIVE_EMERGECY_SMS = 17;
    public static final int OP_RECEIVE_MMS = 18;
    public static final int OP_RECEIVE_SMS = 16;
    public static final int OP_RECEIVE_WAP_PUSH = 19;
    public static final int OP_RECORD_AUDIO = 27;
    public static final int OP_RECORD_AUDIO_HOTWORD = 102;
    public static final int OP_REQUEST_DELETE_PACKAGES = 72;
    public static final int OP_REQUEST_INSTALL_PACKAGES = 66;
    public static final int OP_RUN_ANY_IN_BACKGROUND = 70;
    public static final int OP_RUN_IN_BACKGROUND = 63;
    public static final int OP_SEND_SMS = 20;
    public static final int OP_SMS_FINANCIAL_TRANSACTIONS = 80;
    public static final int OP_START_FOREGROUND = 76;
    public static final int OP_SYSTEM_ALERT_WINDOW = 24;
    public static final int OP_TAKE_AUDIO_FOCUS = 32;
    public static final int OP_TAKE_MEDIA_BUTTONS = 31;
    public static final int OP_TOAST_WINDOW = 45;
    public static final int OP_TURN_SCREEN_ON = 61;
    public static final int OP_USE_BIOMETRIC = 78;
    public static final int OP_USE_FINGERPRINT = 55;
    public static final int OP_USE_SIP = 53;
    public static final int OP_VIBRATE = 3;
    public static final int OP_WAKE_LOCK = 40;
    public static final int OP_WIFI_SCAN = 10;
    public static final int OP_WRITE_CALENDAR = 9;
    public static final int OP_WRITE_CALL_LOG = 7;
    public static final int OP_WRITE_CLIPBOARD = 30;
    public static final int OP_WRITE_CONTACTS = 5;
    public static final int OP_WRITE_EXTERNAL_STORAGE = 60;
    public static final int OP_WRITE_ICC_SMS = 22;
    public static final int OP_WRITE_MEDIA_AUDIO = 82;
    public static final int OP_WRITE_MEDIA_IMAGES = 86;
    public static final int OP_WRITE_MEDIA_VIDEO = 84;
    public static final int OP_WRITE_SETTINGS = 23;
    public static final int OP_WRITE_SMS = 15;
    public static final int OP_WRITE_WALLPAPER = 48;
    public static final int SAMPLING_STRATEGY_BOOT_TIME_SAMPLING = 3;
    public static final int SAMPLING_STRATEGY_DEFAULT = 0;
    public static final int SAMPLING_STRATEGY_RARELY_USED = 2;
    public static final int SAMPLING_STRATEGY_UNIFORM = 1;
    private static final byte SHOULD_COLLECT_NOTE_OP = 2;
    private static final byte SHOULD_COLLECT_NOTE_OP_NOT_INITIALIZED = 0;
    private static final byte SHOULD_NOT_COLLECT_NOTE_OP = 1;

    @SystemApi
    public static final int UID_STATE_BACKGROUND = 600;

    @SystemApi
    public static final int UID_STATE_CACHED = 700;

    @SystemApi
    public static final int UID_STATE_FOREGROUND = 500;

    @SystemApi
    public static final int UID_STATE_FOREGROUND_SERVICE = 400;

    @SystemApi
    @Deprecated
    public static final int UID_STATE_FOREGROUND_SERVICE_LOCATION = 300;
    public static final int UID_STATE_MAX_LAST_NON_RESTRICTED = 500;
    private static final int UID_STATE_OFFSET = 31;

    @SystemApi
    public static final int UID_STATE_PERSISTENT = 100;

    @SystemApi
    public static final int UID_STATE_TOP = 200;
    public static final int WATCH_FOREGROUND_CHANGES = 1;
    public static final int _NUM_OP = 103;
    static IBinder sClientId;
    private static MessageSamplingConfig sConfig;
    private static OnOpNotedCallback sOnOpNotedCallback;
    static IAppOpsService sService;
    final Context mContext;
    final IAppOpsService mService;
    private static final Object sLock = new Object();
    private static ArrayList<AsyncNotedAppOp> sUnforwardedOps = new ArrayList<>();
    private static OnOpNotedCallback sMessageCollector = new OnOpNotedCallback() { // from class: android.app.AppOpsManager.1
        @Override // android.app.AppOpsManager.OnOpNotedCallback
        public void onAsyncNoted(AsyncNotedAppOp asyncNotedAppOp) {
        }

        @Override // android.app.AppOpsManager.OnOpNotedCallback
        public void onNoted(SyncNotedAppOp syncNotedAppOp) {
            reportStackTraceIfNeeded(syncNotedAppOp);
        }

        @Override // android.app.AppOpsManager.OnOpNotedCallback
        public void onSelfNoted(SyncNotedAppOp syncNotedAppOp) {
            reportStackTraceIfNeeded(syncNotedAppOp);
        }

        private void reportStackTraceIfNeeded(SyncNotedAppOp syncNotedAppOp) {
            if (AppOpsManager.isCollectingStackTraces()) {
                MessageSamplingConfig messageSamplingConfig = AppOpsManager.sConfig;
                if (AppOpsManager.leftCircularDistance(AppOpsManager.strOpToOp(syncNotedAppOp.getOp()), messageSamplingConfig.getSampledOpCode(), 103) <= messageSamplingConfig.getAcceptableLeftDistance() || messageSamplingConfig.getExpirationTimeSinceBootMillis() < SystemClock.elapsedRealtime()) {
                    String formattedStackTrace = AppOpsManager.getFormattedStackTrace();
                    try {
                        String strCurrentOpPackageName = ActivityThread.currentOpPackageName();
                        IAppOpsService service = AppOpsManager.getService();
                        if (strCurrentOpPackageName == null) {
                            strCurrentOpPackageName = "";
                        }
                        MessageSamplingConfig unused = AppOpsManager.sConfig = service.reportRuntimeAppOpAccessMessageAndGetConfig(strCurrentOpPackageName, syncNotedAppOp, formattedStackTrace);
                    } catch (RemoteException e) {
                        e.rethrowFromSystemServer();
                    }
                }
            }
        }
    };
    public static final String[] MODE_NAMES = {"allow", "ignore", "deny", "default", MediaControl.PARAM_FOREGROUND};
    public static final int[] UID_STATES = {100, 200, 300, 400, 500, 600, 700};
    private static final int[] RUNTIME_AND_APPOP_PERMISSIONS_OPS = {4, 5, 62, 8, 9, 20, 16, 14, 19, 18, 57, 59, 60, 90, 0, 1, 51, 65, 13, 6, 7, 52, 53, 54, 69, 74, 27, 26, 56, 79, 81, 82, 83, 84, 85, 86, 25, 24, 23, 66, 76, 80, 75, 68, 92, 93, 95};
    private static int[] sOpToSwitch = {0, 0, 0, 3, 4, 5, 6, 7, 8, 9, 0, 11, 0, 13, 14, 15, 16, 16, 18, 19, 20, 14, 15, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 0, 0, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 0, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102};
    public static final String OPSTR_COARSE_LOCATION = "android:coarse_location";
    public static final String OPSTR_FINE_LOCATION = "android:fine_location";

    @SystemApi
    public static final String OPSTR_GPS = "android:gps";

    @SystemApi
    public static final String OPSTR_VIBRATE = "android:vibrate";
    public static final String OPSTR_READ_CONTACTS = "android:read_contacts";
    public static final String OPSTR_WRITE_CONTACTS = "android:write_contacts";
    public static final String OPSTR_READ_CALL_LOG = "android:read_call_log";
    public static final String OPSTR_WRITE_CALL_LOG = "android:write_call_log";
    public static final String OPSTR_READ_CALENDAR = "android:read_calendar";
    public static final String OPSTR_WRITE_CALENDAR = "android:write_calendar";

    @SystemApi
    public static final String OPSTR_WIFI_SCAN = "android:wifi_scan";

    @SystemApi
    public static final String OPSTR_POST_NOTIFICATION = "android:post_notification";

    @SystemApi
    public static final String OPSTR_NEIGHBORING_CELLS = "android:neighboring_cells";
    public static final String OPSTR_CALL_PHONE = "android:call_phone";
    public static final String OPSTR_READ_SMS = "android:read_sms";

    @SystemApi
    public static final String OPSTR_WRITE_SMS = "android:write_sms";
    public static final String OPSTR_RECEIVE_SMS = "android:receive_sms";

    @SystemApi
    public static final String OPSTR_RECEIVE_EMERGENCY_BROADCAST = "android:receive_emergency_broadcast";
    public static final String OPSTR_RECEIVE_MMS = "android:receive_mms";
    public static final String OPSTR_RECEIVE_WAP_PUSH = "android:receive_wap_push";
    public static final String OPSTR_SEND_SMS = "android:send_sms";

    @SystemApi
    public static final String OPSTR_READ_ICC_SMS = "android:read_icc_sms";

    @SystemApi
    public static final String OPSTR_WRITE_ICC_SMS = "android:write_icc_sms";
    public static final String OPSTR_WRITE_SETTINGS = "android:write_settings";
    public static final String OPSTR_SYSTEM_ALERT_WINDOW = "android:system_alert_window";

    @SystemApi
    public static final String OPSTR_ACCESS_NOTIFICATIONS = "android:access_notifications";
    public static final String OPSTR_CAMERA = "android:camera";
    public static final String OPSTR_RECORD_AUDIO = "android:record_audio";

    @SystemApi
    public static final String OPSTR_PLAY_AUDIO = "android:play_audio";

    @SystemApi
    public static final String OPSTR_READ_CLIPBOARD = "android:read_clipboard";

    @SystemApi
    public static final String OPSTR_WRITE_CLIPBOARD = "android:write_clipboard";

    @SystemApi
    public static final String OPSTR_TAKE_MEDIA_BUTTONS = "android:take_media_buttons";

    @SystemApi
    public static final String OPSTR_TAKE_AUDIO_FOCUS = "android:take_audio_focus";

    @SystemApi
    public static final String OPSTR_AUDIO_MASTER_VOLUME = "android:audio_master_volume";

    @SystemApi
    public static final String OPSTR_AUDIO_VOICE_VOLUME = "android:audio_voice_volume";

    @SystemApi
    public static final String OPSTR_AUDIO_RING_VOLUME = "android:audio_ring_volume";

    @SystemApi
    public static final String OPSTR_AUDIO_MEDIA_VOLUME = "android:audio_media_volume";

    @SystemApi
    public static final String OPSTR_AUDIO_ALARM_VOLUME = "android:audio_alarm_volume";

    @SystemApi
    public static final String OPSTR_AUDIO_NOTIFICATION_VOLUME = "android:audio_notification_volume";

    @SystemApi
    public static final String OPSTR_AUDIO_BLUETOOTH_VOLUME = "android:audio_bluetooth_volume";

    @SystemApi
    public static final String OPSTR_WAKE_LOCK = "android:wake_lock";
    public static final String OPSTR_MONITOR_LOCATION = "android:monitor_location";
    public static final String OPSTR_MONITOR_HIGH_POWER_LOCATION = "android:monitor_location_high_power";
    public static final String OPSTR_GET_USAGE_STATS = "android:get_usage_stats";

    @SystemApi
    public static final String OPSTR_MUTE_MICROPHONE = "android:mute_microphone";

    @SystemApi
    public static final String OPSTR_TOAST_WINDOW = "android:toast_window";

    @SystemApi
    public static final String OPSTR_PROJECT_MEDIA = "android:project_media";

    @SystemApi
    public static final String OPSTR_ACTIVATE_VPN = "android:activate_vpn";

    @SystemApi
    public static final String OPSTR_WRITE_WALLPAPER = "android:write_wallpaper";

    @SystemApi
    public static final String OPSTR_ASSIST_STRUCTURE = "android:assist_structure";

    @SystemApi
    public static final String OPSTR_ASSIST_SCREENSHOT = "android:assist_screenshot";
    public static final String OPSTR_READ_PHONE_STATE = "android:read_phone_state";
    public static final String OPSTR_ADD_VOICEMAIL = "android:add_voicemail";
    public static final String OPSTR_USE_SIP = "android:use_sip";
    public static final String OPSTR_PROCESS_OUTGOING_CALLS = "android:process_outgoing_calls";
    public static final String OPSTR_USE_FINGERPRINT = "android:use_fingerprint";
    public static final String OPSTR_BODY_SENSORS = "android:body_sensors";
    public static final String OPSTR_READ_CELL_BROADCASTS = "android:read_cell_broadcasts";
    public static final String OPSTR_MOCK_LOCATION = "android:mock_location";
    public static final String OPSTR_READ_EXTERNAL_STORAGE = "android:read_external_storage";
    public static final String OPSTR_WRITE_EXTERNAL_STORAGE = "android:write_external_storage";

    @SystemApi
    public static final String OPSTR_TURN_SCREEN_ON = "android:turn_screen_on";

    @SystemApi
    public static final String OPSTR_GET_ACCOUNTS = "android:get_accounts";

    @SystemApi
    public static final String OPSTR_RUN_IN_BACKGROUND = "android:run_in_background";

    @SystemApi
    public static final String OPSTR_AUDIO_ACCESSIBILITY_VOLUME = "android:audio_accessibility_volume";
    public static final String OPSTR_READ_PHONE_NUMBERS = "android:read_phone_numbers";

    @SystemApi
    public static final String OPSTR_REQUEST_INSTALL_PACKAGES = "android:request_install_packages";
    public static final String OPSTR_PICTURE_IN_PICTURE = "android:picture_in_picture";

    @SystemApi
    public static final String OPSTR_INSTANT_APP_START_FOREGROUND = "android:instant_app_start_foreground";
    public static final String OPSTR_ANSWER_PHONE_CALLS = "android:answer_phone_calls";

    @SystemApi
    public static final String OPSTR_RUN_ANY_IN_BACKGROUND = "android:run_any_in_background";

    @SystemApi
    public static final String OPSTR_CHANGE_WIFI_STATE = "android:change_wifi_state";

    @SystemApi
    public static final String OPSTR_REQUEST_DELETE_PACKAGES = "android:request_delete_packages";

    @SystemApi
    public static final String OPSTR_BIND_ACCESSIBILITY_SERVICE = "android:bind_accessibility_service";

    @SystemApi
    public static final String OPSTR_ACCEPT_HANDOVER = "android:accept_handover";

    @SystemApi
    public static final String OPSTR_MANAGE_IPSEC_TUNNELS = "android:manage_ipsec_tunnels";

    @SystemApi
    public static final String OPSTR_START_FOREGROUND = "android:start_foreground";
    public static final String OPSTR_BLUETOOTH_SCAN = "android:bluetooth_scan";
    public static final String OPSTR_USE_BIOMETRIC = "android:use_biometric";
    public static final String OPSTR_ACTIVITY_RECOGNITION = "android:activity_recognition";
    public static final String OPSTR_SMS_FINANCIAL_TRANSACTIONS = "android:sms_financial_transactions";

    @SystemApi
    public static final String OPSTR_READ_MEDIA_AUDIO = "android:read_media_audio";

    @SystemApi
    public static final String OPSTR_WRITE_MEDIA_AUDIO = "android:write_media_audio";

    @SystemApi
    public static final String OPSTR_READ_MEDIA_VIDEO = "android:read_media_video";

    @SystemApi
    public static final String OPSTR_WRITE_MEDIA_VIDEO = "android:write_media_video";

    @SystemApi
    public static final String OPSTR_READ_MEDIA_IMAGES = "android:read_media_images";

    @SystemApi
    public static final String OPSTR_WRITE_MEDIA_IMAGES = "android:write_media_images";

    @SystemApi
    public static final String OPSTR_LEGACY_STORAGE = "android:legacy_storage";

    @SystemApi
    public static final String OPSTR_ACCESS_ACCESSIBILITY = "android:access_accessibility";
    public static final String OPSTR_READ_DEVICE_IDENTIFIERS = "android:read_device_identifiers";
    public static final String OPSTR_ACCESS_MEDIA_LOCATION = "android:access_media_location";
    public static final String OPSTR_QUERY_ALL_PACKAGES = "android:query_all_packages";

    @SystemApi
    public static final String OPSTR_MANAGE_EXTERNAL_STORAGE = "android:manage_external_storage";

    @SystemApi
    public static final String OPSTR_INTERACT_ACROSS_PROFILES = "android:interact_across_profiles";
    public static final String OPSTR_ACTIVATE_PLATFORM_VPN = "android:activate_platform_vpn";

    @SystemApi
    public static final String OPSTR_LOADER_USAGE_STATS = "android:loader_usage_stats";

    @SystemApi
    public static final String OPSTR_AUTO_REVOKE_PERMISSIONS_IF_UNUSED = "android:auto_revoke_permissions_if_unused";

    @SystemApi
    public static final String OPSTR_AUTO_REVOKE_MANAGED_BY_INSTALLER = "android:auto_revoke_managed_by_installer";
    public static final String OPSTR_NO_ISOLATED_STORAGE = "android:no_isolated_storage";
    public static final String OPSTR_PHONE_CALL_MICROPHONE = "android:phone_call_microphone";
    public static final String OPSTR_PHONE_CALL_CAMERA = "android:phone_call_camera";
    public static final String OPSTR_RECORD_AUDIO_HOTWORD = "android:record_audio_hotword";
    private static String[] sOpToString = {OPSTR_COARSE_LOCATION, OPSTR_FINE_LOCATION, OPSTR_GPS, OPSTR_VIBRATE, OPSTR_READ_CONTACTS, OPSTR_WRITE_CONTACTS, OPSTR_READ_CALL_LOG, OPSTR_WRITE_CALL_LOG, OPSTR_READ_CALENDAR, OPSTR_WRITE_CALENDAR, OPSTR_WIFI_SCAN, OPSTR_POST_NOTIFICATION, OPSTR_NEIGHBORING_CELLS, OPSTR_CALL_PHONE, OPSTR_READ_SMS, OPSTR_WRITE_SMS, OPSTR_RECEIVE_SMS, OPSTR_RECEIVE_EMERGENCY_BROADCAST, OPSTR_RECEIVE_MMS, OPSTR_RECEIVE_WAP_PUSH, OPSTR_SEND_SMS, OPSTR_READ_ICC_SMS, OPSTR_WRITE_ICC_SMS, OPSTR_WRITE_SETTINGS, OPSTR_SYSTEM_ALERT_WINDOW, OPSTR_ACCESS_NOTIFICATIONS, OPSTR_CAMERA, OPSTR_RECORD_AUDIO, OPSTR_PLAY_AUDIO, OPSTR_READ_CLIPBOARD, OPSTR_WRITE_CLIPBOARD, OPSTR_TAKE_MEDIA_BUTTONS, OPSTR_TAKE_AUDIO_FOCUS, OPSTR_AUDIO_MASTER_VOLUME, OPSTR_AUDIO_VOICE_VOLUME, OPSTR_AUDIO_RING_VOLUME, OPSTR_AUDIO_MEDIA_VOLUME, OPSTR_AUDIO_ALARM_VOLUME, OPSTR_AUDIO_NOTIFICATION_VOLUME, OPSTR_AUDIO_BLUETOOTH_VOLUME, OPSTR_WAKE_LOCK, OPSTR_MONITOR_LOCATION, OPSTR_MONITOR_HIGH_POWER_LOCATION, OPSTR_GET_USAGE_STATS, OPSTR_MUTE_MICROPHONE, OPSTR_TOAST_WINDOW, OPSTR_PROJECT_MEDIA, OPSTR_ACTIVATE_VPN, OPSTR_WRITE_WALLPAPER, OPSTR_ASSIST_STRUCTURE, OPSTR_ASSIST_SCREENSHOT, OPSTR_READ_PHONE_STATE, OPSTR_ADD_VOICEMAIL, OPSTR_USE_SIP, OPSTR_PROCESS_OUTGOING_CALLS, OPSTR_USE_FINGERPRINT, OPSTR_BODY_SENSORS, OPSTR_READ_CELL_BROADCASTS, OPSTR_MOCK_LOCATION, OPSTR_READ_EXTERNAL_STORAGE, OPSTR_WRITE_EXTERNAL_STORAGE, OPSTR_TURN_SCREEN_ON, OPSTR_GET_ACCOUNTS, OPSTR_RUN_IN_BACKGROUND, OPSTR_AUDIO_ACCESSIBILITY_VOLUME, OPSTR_READ_PHONE_NUMBERS, OPSTR_REQUEST_INSTALL_PACKAGES, OPSTR_PICTURE_IN_PICTURE, OPSTR_INSTANT_APP_START_FOREGROUND, OPSTR_ANSWER_PHONE_CALLS, OPSTR_RUN_ANY_IN_BACKGROUND, OPSTR_CHANGE_WIFI_STATE, OPSTR_REQUEST_DELETE_PACKAGES, OPSTR_BIND_ACCESSIBILITY_SERVICE, OPSTR_ACCEPT_HANDOVER, OPSTR_MANAGE_IPSEC_TUNNELS, OPSTR_START_FOREGROUND, OPSTR_BLUETOOTH_SCAN, OPSTR_USE_BIOMETRIC, OPSTR_ACTIVITY_RECOGNITION, OPSTR_SMS_FINANCIAL_TRANSACTIONS, OPSTR_READ_MEDIA_AUDIO, OPSTR_WRITE_MEDIA_AUDIO, OPSTR_READ_MEDIA_VIDEO, OPSTR_WRITE_MEDIA_VIDEO, OPSTR_READ_MEDIA_IMAGES, OPSTR_WRITE_MEDIA_IMAGES, OPSTR_LEGACY_STORAGE, OPSTR_ACCESS_ACCESSIBILITY, OPSTR_READ_DEVICE_IDENTIFIERS, OPSTR_ACCESS_MEDIA_LOCATION, OPSTR_QUERY_ALL_PACKAGES, OPSTR_MANAGE_EXTERNAL_STORAGE, OPSTR_INTERACT_ACROSS_PROFILES, OPSTR_ACTIVATE_PLATFORM_VPN, OPSTR_LOADER_USAGE_STATS, "", OPSTR_AUTO_REVOKE_PERMISSIONS_IF_UNUSED, OPSTR_AUTO_REVOKE_MANAGED_BY_INSTALLER, OPSTR_NO_ISOLATED_STORAGE, OPSTR_PHONE_CALL_MICROPHONE, OPSTR_PHONE_CALL_CAMERA, OPSTR_RECORD_AUDIO_HOTWORD};
    private static String[] sOpNames = {"COARSE_LOCATION", "FINE_LOCATION", "GPS", "VIBRATE", "READ_CONTACTS", "WRITE_CONTACTS", "READ_CALL_LOG", "WRITE_CALL_LOG", "READ_CALENDAR", "WRITE_CALENDAR", "WIFI_SCAN", "POST_NOTIFICATION", "NEIGHBORING_CELLS", "CALL_PHONE", "READ_SMS", "WRITE_SMS", "RECEIVE_SMS", "RECEIVE_EMERGECY_SMS", "RECEIVE_MMS", "RECEIVE_WAP_PUSH", "SEND_SMS", "READ_ICC_SMS", "WRITE_ICC_SMS", "WRITE_SETTINGS", "SYSTEM_ALERT_WINDOW", "ACCESS_NOTIFICATIONS", "CAMERA", "RECORD_AUDIO", "PLAY_AUDIO", "READ_CLIPBOARD", "WRITE_CLIPBOARD", "TAKE_MEDIA_BUTTONS", "TAKE_AUDIO_FOCUS", "AUDIO_MASTER_VOLUME", "AUDIO_VOICE_VOLUME", "AUDIO_RING_VOLUME", "AUDIO_MEDIA_VOLUME", "AUDIO_ALARM_VOLUME", "AUDIO_NOTIFICATION_VOLUME", "AUDIO_BLUETOOTH_VOLUME", "WAKE_LOCK", "MONITOR_LOCATION", "MONITOR_HIGH_POWER_LOCATION", "GET_USAGE_STATS", "MUTE_MICROPHONE", "TOAST_WINDOW", "PROJECT_MEDIA", "ACTIVATE_VPN", "WRITE_WALLPAPER", "ASSIST_STRUCTURE", "ASSIST_SCREENSHOT", "READ_PHONE_STATE", "ADD_VOICEMAIL", "USE_SIP", "PROCESS_OUTGOING_CALLS", "USE_FINGERPRINT", "BODY_SENSORS", "READ_CELL_BROADCASTS", "MOCK_LOCATION", "READ_EXTERNAL_STORAGE", "WRITE_EXTERNAL_STORAGE", "TURN_ON_SCREEN", "GET_ACCOUNTS", "RUN_IN_BACKGROUND", "AUDIO_ACCESSIBILITY_VOLUME", "READ_PHONE_NUMBERS", "REQUEST_INSTALL_PACKAGES", "PICTURE_IN_PICTURE", "INSTANT_APP_START_FOREGROUND", "ANSWER_PHONE_CALLS", "RUN_ANY_IN_BACKGROUND", "CHANGE_WIFI_STATE", "REQUEST_DELETE_PACKAGES", "BIND_ACCESSIBILITY_SERVICE", "ACCEPT_HANDOVER", "MANAGE_IPSEC_TUNNELS", "START_FOREGROUND", "BLUETOOTH_SCAN", "USE_BIOMETRIC", "ACTIVITY_RECOGNITION", "SMS_FINANCIAL_TRANSACTIONS", "READ_MEDIA_AUDIO", "WRITE_MEDIA_AUDIO", "READ_MEDIA_VIDEO", "WRITE_MEDIA_VIDEO", "READ_MEDIA_IMAGES", "WRITE_MEDIA_IMAGES", "LEGACY_STORAGE", "ACCESS_ACCESSIBILITY", "READ_DEVICE_IDENTIFIERS", "ACCESS_MEDIA_LOCATION", "QUERY_ALL_PACKAGES", "MANAGE_EXTERNAL_STORAGE", "INTERACT_ACROSS_PROFILES", "ACTIVATE_PLATFORM_VPN", "LOADER_USAGE_STATS", "deprecated", "AUTO_REVOKE_PERMISSIONS_IF_UNUSED", "AUTO_REVOKE_MANAGED_BY_INSTALLER", "NO_ISOLATED_STORAGE", "PHONE_CALL_MICROPHONE", "PHONE_CALL_CAMERA", "RECORD_AUDIO_HOTWORD"};
    private static String[] sOpPerms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, null, Manifest.permission.VIBRATE, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CALL_LOG, Manifest.permission.WRITE_CALL_LOG, Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR, Manifest.permission.ACCESS_WIFI_STATE, null, null, Manifest.permission.CALL_PHONE, Manifest.permission.READ_SMS, null, Manifest.permission.RECEIVE_SMS, Manifest.permission.RECEIVE_EMERGENCY_BROADCAST, Manifest.permission.RECEIVE_MMS, Manifest.permission.RECEIVE_WAP_PUSH, Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS, null, Manifest.permission.WRITE_SETTINGS, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.ACCESS_NOTIFICATIONS, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, null, null, null, null, null, null, null, null, null, null, null, null, Manifest.permission.WAKE_LOCK, null, null, Manifest.permission.PACKAGE_USAGE_STATS, null, null, null, null, null, null, null, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ADD_VOICEMAIL, Manifest.permission.USE_SIP, Manifest.permission.PROCESS_OUTGOING_CALLS, Manifest.permission.USE_FINGERPRINT, Manifest.permission.BODY_SENSORS, Manifest.permission.READ_CELL_BROADCASTS, null, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, null, Manifest.permission.GET_ACCOUNTS, null, null, Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.REQUEST_INSTALL_PACKAGES, null, Manifest.permission.INSTANT_APP_FOREGROUND_SERVICE, Manifest.permission.ANSWER_PHONE_CALLS, null, Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.REQUEST_DELETE_PACKAGES, Manifest.permission.BIND_ACCESSIBILITY_SERVICE, Manifest.permission.ACCEPT_HANDOVER, Manifest.permission.MANAGE_IPSEC_TUNNELS, Manifest.permission.FOREGROUND_SERVICE, null, Manifest.permission.USE_BIOMETRIC, Manifest.permission.ACTIVITY_RECOGNITION, Manifest.permission.SMS_FINANCIAL_TRANSACTIONS, null, null, null, null, null, null, null, null, null, Manifest.permission.ACCESS_MEDIA_LOCATION, null, Manifest.permission.MANAGE_EXTERNAL_STORAGE, Manifest.permission.INTERACT_ACROSS_PROFILES, null, Manifest.permission.LOADER_USAGE_STATS, null, null, null, null, null, null, null};
    private static String[] sOpRestrictions = {UserManager.DISALLOW_SHARE_LOCATION, UserManager.DISALLOW_SHARE_LOCATION, UserManager.DISALLOW_SHARE_LOCATION, null, null, null, UserManager.DISALLOW_OUTGOING_CALLS, UserManager.DISALLOW_OUTGOING_CALLS, null, null, UserManager.DISALLOW_SHARE_LOCATION, null, null, null, UserManager.DISALLOW_SMS, UserManager.DISALLOW_SMS, UserManager.DISALLOW_SMS, null, UserManager.DISALLOW_SMS, null, UserManager.DISALLOW_SMS, UserManager.DISALLOW_SMS, UserManager.DISALLOW_SMS, null, UserManager.DISALLOW_CREATE_WINDOWS, null, UserManager.DISALLOW_CAMERA, UserManager.DISALLOW_RECORD_AUDIO, null, null, null, null, null, UserManager.DISALLOW_ADJUST_VOLUME, UserManager.DISALLOW_ADJUST_VOLUME, UserManager.DISALLOW_ADJUST_VOLUME, UserManager.DISALLOW_ADJUST_VOLUME, UserManager.DISALLOW_ADJUST_VOLUME, UserManager.DISALLOW_ADJUST_VOLUME, UserManager.DISALLOW_ADJUST_VOLUME, null, UserManager.DISALLOW_SHARE_LOCATION, UserManager.DISALLOW_SHARE_LOCATION, null, UserManager.DISALLOW_UNMUTE_MICROPHONE, UserManager.DISALLOW_CREATE_WINDOWS, null, null, UserManager.DISALLOW_WALLPAPER, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, UserManager.DISALLOW_ADJUST_VOLUME, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, UserManager.DISALLOW_SMS, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
    private static RestrictionBypass[] sOpAllowSystemRestrictionBypass = {new RestrictionBypass(true, false), new RestrictionBypass(true, false), null, null, null, null, null, null, null, null, new RestrictionBypass(true, false), null, null, null, null, null, null, null, null, null, null, null, null, null, new RestrictionBypass(true, false), null, null, new RestrictionBypass(false, true), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, new RestrictionBypass(true, false), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, new RestrictionBypass(true, false), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null};
    private static int[] sOpDefaultMode = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 3, getSystemAlertWindowDefault(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 3, 0, 3, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 3, 0, 2, 0, 2, 0, 2, 3, 0, 2, 0, 3, 3, 3, 1, 3, 1, 3, 0, 2, 0, 0, 0};
    private static boolean[] sOpDisableReset = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, false, true, true, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false};
    private static HashMap<String, Integer> sOpStrToOp = new HashMap<>();
    private static HashMap<String, Integer> sPermToOp = new HashMap<>();
    private static final ThreadLocal<Integer> sBinderThreadCallingUid = new ThreadLocal<>();
    private static final ThreadLocal<ArrayMap<String, long[]>> sAppOpsNotedInThisBinderTransaction = new ThreadLocal<>();
    private static final byte[] sAppOpsToNote = new byte[103];
    private final ArrayMap<OnOpChangedListener, IAppOpsCallback> mModeWatchers = new ArrayMap<>();
    private final ArrayMap<OnOpActiveChangedListener, IAppOpsActiveCallback> mActiveWatchers = new ArrayMap<>();
    private final ArrayMap<OnOpStartedListener, IAppOpsStartedCallback> mStartedWatchers = new ArrayMap<>();
    private final ArrayMap<OnOpNotedListener, IAppOpsNotedCallback> mNotedWatchers = new ArrayMap<>();

    @Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DataBucketKey {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface HistoricalMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface HistoricalOpsRequestFilter {
    }

    public interface HistoricalOpsVisitor {
        void visitHistoricalAttributionOps(AttributedHistoricalOps attributedHistoricalOps);

        void visitHistoricalOp(HistoricalOp historicalOp);

        void visitHistoricalOps(HistoricalOps historicalOps);

        void visitHistoricalPackageOps(HistoricalPackageOps historicalPackageOps);

        void visitHistoricalUidOps(HistoricalUidOps historicalUidOps);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface NotedOpCollectionMode {
    }

    public interface OnOpActiveChangedInternalListener extends OnOpActiveChangedListener {
        default void onOpActiveChanged(int i, int i2, String str, boolean z) {
        }

        @Override // android.app.AppOpsManager.OnOpActiveChangedListener
        default void onOpActiveChanged(String str, int i, String str2, boolean z) {
        }
    }

    public interface OnOpActiveChangedListener {
        void onOpActiveChanged(String str, int i, String str2, boolean z);
    }

    public static class OnOpChangedInternalListener implements OnOpChangedListener {
        public void onOpChanged(int i, String str) {
        }

        @Override // android.app.AppOpsManager.OnOpChangedListener
        public void onOpChanged(String str, String str2) {
        }
    }

    public interface OnOpChangedListener {
        void onOpChanged(String str, String str2);
    }

    public interface OnOpNotedListener {
        void onOpNoted(int i, int i2, String str, int i3);
    }

    public interface OnOpStartedListener {
        void onOpStarted(int i, int i2, String str, int i3);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OpFlags {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SamplingStrategy {
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface ShouldCollectNoteOp {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface UidState {
    }

    private void collectNoteOpCallsForValidation(int i) {
    }

    public static int extractFlagsFromKey(long j) {
        return (int) (j & (-1));
    }

    public static int extractUidStateFromKey(long j) {
        return (int) (j >> 31);
    }

    public static final String getFlagName(int i) {
        return i != 1 ? i != 2 ? i != 4 ? i != 8 ? i != 16 ? "unknown" : "upd" : "tpd" : MediaMetrics.Value.UP : "tp" : XmlTags.TAG_SESSION;
    }

    public static int getNumOps() {
        return 103;
    }

    public static String getUidStateName(int i) {
        return i != 100 ? i != 200 ? i != 300 ? i != 400 ? i != 500 ? i != 600 ? i != 700 ? "unknown" : "cch" : "bg" : "fg" : "fgsvc" : "fgsvcl" : "top" : "pers";
    }

    public static String historicalModeToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "UNKNOWN" : "HISTORICAL_MODE_ENABLED_PASSIVE" : "HISTORICAL_MODE_ENABLED_ACTIVE" : "HISTORICAL_MODE_DISABLED";
    }

    public static long makeKey(int i, int i2) {
        return ((long) i2) | (((long) i) << 31);
    }

    public static int resolveFirstUnrestrictedUidState(int i) {
        return 500;
    }

    public static int resolveLastRestrictedUidState(int i) {
        return 600;
    }

    public static String uidStateToString(int i) {
        return i != 100 ? i != 200 ? i != 300 ? i != 400 ? i != 500 ? i != 600 ? i != 700 ? "UNKNOWN" : "UID_STATE_CACHED" : "UID_STATE_BACKGROUND" : "UID_STATE_FOREGROUND" : "UID_STATE_FOREGROUND_SERVICE" : "UID_STATE_FOREGROUND_SERVICE_LOCATION" : "UID_STATE_TOP" : "UID_STATE_PERSISTENT";
    }

    static {
        if (sOpToSwitch.length != 103) {
            throw new IllegalStateException("sOpToSwitch length " + sOpToSwitch.length + " should be 103");
        }
        if (sOpToString.length != 103) {
            throw new IllegalStateException("sOpToString length " + sOpToString.length + " should be 103");
        }
        if (sOpNames.length != 103) {
            throw new IllegalStateException("sOpNames length " + sOpNames.length + " should be 103");
        }
        if (sOpPerms.length != 103) {
            throw new IllegalStateException("sOpPerms length " + sOpPerms.length + " should be 103");
        }
        if (sOpDefaultMode.length != 103) {
            throw new IllegalStateException("sOpDefaultMode length " + sOpDefaultMode.length + " should be 103");
        }
        if (sOpDisableReset.length != 103) {
            throw new IllegalStateException("sOpDisableReset length " + sOpDisableReset.length + " should be 103");
        }
        if (sOpRestrictions.length != 103) {
            throw new IllegalStateException("sOpRestrictions length " + sOpRestrictions.length + " should be 103");
        }
        if (sOpAllowSystemRestrictionBypass.length != 103) {
            throw new IllegalStateException("sOpAllowSYstemRestrictionsBypass length " + sOpRestrictions.length + " should be 103");
        }
        for (int i = 0; i < 103; i++) {
            String[] strArr = sOpToString;
            if (strArr[i] != null) {
                sOpStrToOp.put(strArr[i], Integer.valueOf(i));
            }
        }
        for (int i2 : RUNTIME_AND_APPOP_PERMISSIONS_OPS) {
            String[] strArr2 = sOpPerms;
            if (strArr2[i2] != null) {
                sPermToOp.put(strArr2[i2], Integer.valueOf(i2));
            }
        }
        sConfig = new MessageSamplingConfig(-1, 0, 0L);
    }

    public static String keyToString(long j) {
        return "[" + getUidStateName(extractUidStateFromKey(j)) + NativeLibraryHelper.CLEAR_ABI_OVERRIDE + flagsToString(extractFlagsFromKey(j)) + "]";
    }

    public static String flagsToString(int i) {
        StringBuilder sb = new StringBuilder();
        while (i != 0) {
            int iNumberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(i);
            i &= ~iNumberOfTrailingZeros;
            if (sb.length() > 0) {
                sb.append('|');
            }
            sb.append(getFlagName(iNumberOfTrailingZeros));
        }
        return sb.toString();
    }

    public static int opToSwitch(int i) {
        return sOpToSwitch[i];
    }

    public static String opToName(int i) {
        if (i == -1) {
            return KeyProperties.DIGEST_NONE;
        }
        String[] strArr = sOpNames;
        if (i < strArr.length) {
            return strArr[i];
        }
        return "Unknown(" + i + ")";
    }

    public static String opToPublicName(int i) {
        return sOpToString[i];
    }

    public static int strDebugOpToOp(String str) {
        int i = 0;
        while (true) {
            String[] strArr = sOpNames;
            if (i < strArr.length) {
                if (strArr[i].equals(str)) {
                    return i;
                }
                i++;
            } else {
                throw new IllegalArgumentException("Unknown operation string: " + str);
            }
        }
    }

    public static String opToPermission(int i) {
        return sOpPerms[i];
    }

    @SystemApi
    public static String opToPermission(String str) {
        return opToPermission(strOpToOp(str));
    }

    public static String opToRestriction(int i) {
        return sOpRestrictions[i];
    }

    public static int permissionToOpCode(String str) {
        Integer num = sPermToOp.get(str);
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

    public static RestrictionBypass opAllowSystemBypassRestriction(int i) {
        return sOpAllowSystemRestrictionBypass[i];
    }

    public static int opToDefaultMode(int i) {
        return sOpDefaultMode[i];
    }

    @SystemApi
    public static int opToDefaultMode(String str) {
        return opToDefaultMode(strOpToOp(str));
    }

    public static String modeToName(int i) {
        if (i >= 0) {
            String[] strArr = MODE_NAMES;
            if (i < strArr.length) {
                return strArr[i];
            }
        }
        return "mode=" + i;
    }

    public static boolean opAllowsReset(int i) {
        return !sOpDisableReset[i];
    }

    public static String toReceiverId(Object obj) {
        if (obj instanceof PendingIntent) {
            return toReceiverId((PendingIntent) obj);
        }
        return obj.getClass().getName() + "@" + System.identityHashCode(obj);
    }

    public static String toReceiverId(PendingIntent pendingIntent) {
        return pendingIntent.getTag("");
    }

    public static class RestrictionBypass {
        public static RestrictionBypass UNRESTRICTED = new RestrictionBypass(true, true);
        public boolean isPrivileged;
        public boolean isRecordAudioRestrictionExcept;

        public RestrictionBypass(boolean z, boolean z2) {
            this.isPrivileged = z;
            this.isRecordAudioRestrictionExcept = z2;
        }
    }

    @SystemApi
    public static final class PackageOps implements Parcelable {
        public static final Parcelable.Creator<PackageOps> CREATOR = new Parcelable.Creator<PackageOps>() { // from class: android.app.AppOpsManager.PackageOps.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PackageOps createFromParcel(Parcel parcel) {
                return new PackageOps(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PackageOps[] newArray(int i) {
                return new PackageOps[i];
            }
        };
        private final List<OpEntry> mEntries;
        private final String mPackageName;
        private final int mUid;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public PackageOps(String str, int i, List<OpEntry> list) {
            this.mPackageName = str;
            this.mUid = i;
            this.mEntries = list;
        }

        public String getPackageName() {
            return this.mPackageName;
        }

        public int getUid() {
            return this.mUid;
        }

        public List<OpEntry> getOps() {
            return this.mEntries;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mPackageName);
            parcel.writeInt(this.mUid);
            parcel.writeInt(this.mEntries.size());
            for (int i2 = 0; i2 < this.mEntries.size(); i2++) {
                this.mEntries.get(i2).writeToParcel(parcel, i);
            }
        }

        PackageOps(Parcel parcel) {
            this.mPackageName = parcel.readString();
            this.mUid = parcel.readInt();
            this.mEntries = new ArrayList();
            int i = parcel.readInt();
            for (int i2 = 0; i2 < i; i2++) {
                this.mEntries.add(OpEntry.CREATOR.createFromParcel(parcel));
            }
        }
    }

    @SystemApi
    public static final class OpEventProxyInfo implements Parcelable {
        public static final Parcelable.Creator<OpEventProxyInfo> CREATOR = new Parcelable.Creator<OpEventProxyInfo>() { // from class: android.app.AppOpsManager.OpEventProxyInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public OpEventProxyInfo[] newArray(int i) {
                return new OpEventProxyInfo[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public OpEventProxyInfo createFromParcel(Parcel parcel) {
                return new OpEventProxyInfo(parcel);
            }
        };
        private String mAttributionTag;
        private String mPackageName;
        private int mUid;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public void reinit(int i, String str, String str2) {
            this.mUid = Preconditions.checkArgumentNonnegative(i);
            this.mPackageName = str;
            this.mAttributionTag = str2;
        }

        public OpEventProxyInfo(int i, String str, String str2) {
            this.mUid = i;
            AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, i, "from", 0L);
            this.mPackageName = str;
            this.mAttributionTag = str2;
        }

        public OpEventProxyInfo(OpEventProxyInfo opEventProxyInfo) {
            this.mUid = opEventProxyInfo.mUid;
            this.mPackageName = opEventProxyInfo.mPackageName;
            this.mAttributionTag = opEventProxyInfo.mAttributionTag;
        }

        public int getUid() {
            return this.mUid;
        }

        public String getPackageName() {
            return this.mPackageName;
        }

        public String getAttributionTag() {
            return this.mAttributionTag;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            byte b = this.mPackageName != null ? (byte) 2 : (byte) 0;
            if (this.mAttributionTag != null) {
                b = (byte) (b | 4);
            }
            parcel.writeByte(b);
            parcel.writeInt(this.mUid);
            String str = this.mPackageName;
            if (str != null) {
                parcel.writeString(str);
            }
            String str2 = this.mAttributionTag;
            if (str2 != null) {
                parcel.writeString(str2);
            }
        }

        OpEventProxyInfo(Parcel parcel) {
            byte b = parcel.readByte();
            int i = parcel.readInt();
            String string = (b & 2) == 0 ? null : parcel.readString();
            String string2 = (b & 4) != 0 ? parcel.readString() : null;
            this.mUid = i;
            AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, i, "from", 0L);
            this.mPackageName = string;
            this.mAttributionTag = string2;
        }
    }

    public static final class NoteOpEvent implements Parcelable {
        public static final Parcelable.Creator<NoteOpEvent> CREATOR = new Parcelable.Creator<NoteOpEvent>() { // from class: android.app.AppOpsManager.NoteOpEvent.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public NoteOpEvent[] newArray(int i) {
                return new NoteOpEvent[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public NoteOpEvent createFromParcel(Parcel parcel) {
                return new NoteOpEvent(parcel);
            }
        };
        private long mDuration;
        private long mNoteTime;
        private OpEventProxyInfo mProxy;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public void reinit(long j, long j2, OpEventProxyInfo opEventProxyInfo, Pools.Pool<OpEventProxyInfo> pool) {
            this.mNoteTime = Preconditions.checkArgumentNonnegative(j);
            this.mDuration = Preconditions.checkArgumentInRange(j2, -1L, Long.MAX_VALUE, "duration");
            OpEventProxyInfo opEventProxyInfo2 = this.mProxy;
            if (opEventProxyInfo2 != null) {
                pool.release(opEventProxyInfo2);
            }
            this.mProxy = opEventProxyInfo;
        }

        public NoteOpEvent(NoteOpEvent noteOpEvent) {
            this(noteOpEvent.mNoteTime, noteOpEvent.mDuration, noteOpEvent.mProxy != null ? new OpEventProxyInfo(noteOpEvent.mProxy) : null);
        }

        public NoteOpEvent(long j, long j2, OpEventProxyInfo opEventProxyInfo) {
            this.mNoteTime = j;
            AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, j, "from", 0L);
            this.mDuration = j2;
            AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, j2, "from", -1L);
            this.mProxy = opEventProxyInfo;
        }

        public long getNoteTime() {
            return this.mNoteTime;
        }

        public long getDuration() {
            return this.mDuration;
        }

        public OpEventProxyInfo getProxy() {
            return this.mProxy;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeByte(this.mProxy != null ? (byte) 4 : (byte) 0);
            parcel.writeLong(this.mNoteTime);
            parcel.writeLong(this.mDuration);
            OpEventProxyInfo opEventProxyInfo = this.mProxy;
            if (opEventProxyInfo != null) {
                parcel.writeTypedObject(opEventProxyInfo, i);
            }
        }

        NoteOpEvent(Parcel parcel) {
            byte b = parcel.readByte();
            long j = parcel.readLong();
            long j2 = parcel.readLong();
            OpEventProxyInfo opEventProxyInfo = (b & 4) == 0 ? null : (OpEventProxyInfo) parcel.readTypedObject(OpEventProxyInfo.CREATOR);
            this.mNoteTime = j;
            AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, j, "from", 0L);
            this.mDuration = j2;
            AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, j2, "from", -1L);
            this.mProxy = opEventProxyInfo;
        }
    }

    @SystemApi
    public static final class AttributedOpEntry implements Parcelable {
        public static final Parcelable.Creator<AttributedOpEntry> CREATOR;
        static Parcelling<LongSparseArray<NoteOpEvent>> sParcellingForAccessEvents;
        static Parcelling<LongSparseArray<NoteOpEvent>> sParcellingForRejectEvents;
        private final LongSparseArray<NoteOpEvent> mAccessEvents;
        private final int mOp;
        private final LongSparseArray<NoteOpEvent> mRejectEvents;
        private final boolean mRunning;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public ArraySet<Long> collectKeys() {
            ArraySet<Long> arraySet = new ArraySet<>();
            LongSparseArray<NoteOpEvent> longSparseArray = this.mAccessEvents;
            if (longSparseArray != null) {
                int size = longSparseArray.size();
                for (int i = 0; i < size; i++) {
                    arraySet.add(Long.valueOf(this.mAccessEvents.keyAt(i)));
                }
            }
            LongSparseArray<NoteOpEvent> longSparseArray2 = this.mRejectEvents;
            if (longSparseArray2 != null) {
                int size2 = longSparseArray2.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    arraySet.add(Long.valueOf(this.mRejectEvents.keyAt(i2)));
                }
            }
            return arraySet;
        }

        public long getLastAccessTime(int i) {
            return getLastAccessTime(100, 700, i);
        }

        public long getLastAccessForegroundTime(int i) {
            return getLastAccessTime(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), i);
        }

        public long getLastAccessBackgroundTime(int i) {
            return getLastAccessTime(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public NoteOpEvent getLastAccessEvent(int i, int i2, int i3) {
            return AppOpsManager.getLastEvent(this.mAccessEvents, i, i2, i3);
        }

        public long getLastAccessTime(int i, int i2, int i3) {
            NoteOpEvent lastAccessEvent = getLastAccessEvent(i, i2, i3);
            if (lastAccessEvent == null) {
                return -1L;
            }
            return lastAccessEvent.getNoteTime();
        }

        public long getLastRejectTime(int i) {
            return getLastRejectTime(100, 700, i);
        }

        public long getLastRejectForegroundTime(int i) {
            return getLastRejectTime(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), i);
        }

        public long getLastRejectBackgroundTime(int i) {
            return getLastRejectTime(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public NoteOpEvent getLastRejectEvent(int i, int i2, int i3) {
            return AppOpsManager.getLastEvent(this.mRejectEvents, i, i2, i3);
        }

        public long getLastRejectTime(int i, int i2, int i3) {
            NoteOpEvent lastRejectEvent = getLastRejectEvent(i, i2, i3);
            if (lastRejectEvent == null) {
                return -1L;
            }
            return lastRejectEvent.getNoteTime();
        }

        public long getLastDuration(int i) {
            return getLastDuration(100, 700, i);
        }

        public long getLastForegroundDuration(int i) {
            return getLastDuration(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), i);
        }

        public long getLastBackgroundDuration(int i) {
            return getLastDuration(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, i);
        }

        public long getLastDuration(int i, int i2, int i3) {
            NoteOpEvent lastAccessEvent = getLastAccessEvent(i, i2, i3);
            if (lastAccessEvent == null) {
                return -1L;
            }
            return lastAccessEvent.getDuration();
        }

        public OpEventProxyInfo getLastProxyInfo(int i) {
            return getLastProxyInfo(100, 700, i);
        }

        public OpEventProxyInfo getLastForegroundProxyInfo(int i) {
            return getLastProxyInfo(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), i);
        }

        public OpEventProxyInfo getLastBackgroundProxyInfo(int i) {
            return getLastProxyInfo(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, i);
        }

        public OpEventProxyInfo getLastProxyInfo(int i, int i2, int i3) {
            NoteOpEvent lastAccessEvent = getLastAccessEvent(i, i2, i3);
            if (lastAccessEvent == null) {
                return null;
            }
            return lastAccessEvent.getProxy();
        }

        private static class LongSparseArrayParceling implements Parcelling<LongSparseArray<NoteOpEvent>> {
            private LongSparseArrayParceling() {
            }

            @Override // com.android.internal.util.Parcelling
            public void parcel(LongSparseArray<NoteOpEvent> longSparseArray, Parcel parcel, int i) {
                if (longSparseArray == null) {
                    parcel.writeInt(-1);
                    return;
                }
                int size = longSparseArray.size();
                parcel.writeInt(size);
                for (int i2 = 0; i2 < size; i2++) {
                    parcel.writeLong(longSparseArray.keyAt(i2));
                    parcel.writeParcelable(longSparseArray.valueAt(i2), i);
                }
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.android.internal.util.Parcelling
            public LongSparseArray<NoteOpEvent> unparcel(Parcel parcel) {
                int i = parcel.readInt();
                if (i == -1) {
                    return null;
                }
                LongSparseArray<NoteOpEvent> longSparseArray = new LongSparseArray<>(i);
                for (int i2 = 0; i2 < i; i2++) {
                    longSparseArray.put(parcel.readLong(), (NoteOpEvent) parcel.readParcelable(null));
                }
                return longSparseArray;
            }
        }

        public AttributedOpEntry(int i, boolean z, LongSparseArray<NoteOpEvent> longSparseArray, LongSparseArray<NoteOpEvent> longSparseArray2) {
            this.mOp = i;
            AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, i, "from", 0L, "to", 102L);
            this.mRunning = z;
            this.mAccessEvents = longSparseArray;
            this.mRejectEvents = longSparseArray2;
        }

        public boolean isRunning() {
            return this.mRunning;
        }

        static {
            Parcelling<LongSparseArray<NoteOpEvent>> parcelling = Parcelling.Cache.get(LongSparseArrayParceling.class);
            sParcellingForAccessEvents = parcelling;
            if (parcelling == null) {
                sParcellingForAccessEvents = Parcelling.Cache.put(new LongSparseArrayParceling());
            }
            Parcelling<LongSparseArray<NoteOpEvent>> parcelling2 = Parcelling.Cache.get(LongSparseArrayParceling.class);
            sParcellingForRejectEvents = parcelling2;
            if (parcelling2 == null) {
                sParcellingForRejectEvents = Parcelling.Cache.put(new LongSparseArrayParceling());
            }
            CREATOR = new Parcelable.Creator<AttributedOpEntry>() { // from class: android.app.AppOpsManager.AttributedOpEntry.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public AttributedOpEntry[] newArray(int i) {
                    return new AttributedOpEntry[i];
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // android.os.Parcelable.Creator
                public AttributedOpEntry createFromParcel(Parcel parcel) {
                    return new AttributedOpEntry(parcel);
                }
            };
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            byte b = this.mRunning ? (byte) 2 : (byte) 0;
            if (this.mAccessEvents != null) {
                b = (byte) (b | 4);
            }
            if (this.mRejectEvents != null) {
                b = (byte) (b | 8);
            }
            parcel.writeByte(b);
            parcel.writeInt(this.mOp);
            sParcellingForAccessEvents.parcel(this.mAccessEvents, parcel, i);
            sParcellingForRejectEvents.parcel(this.mRejectEvents, parcel, i);
        }

        AttributedOpEntry(Parcel parcel) {
            boolean z = (parcel.readByte() & 2) != 0;
            int i = parcel.readInt();
            LongSparseArray<NoteOpEvent> longSparseArrayUnparcel = sParcellingForAccessEvents.unparcel(parcel);
            LongSparseArray<NoteOpEvent> longSparseArrayUnparcel2 = sParcellingForRejectEvents.unparcel(parcel);
            this.mOp = i;
            AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, i, "from", 0L, "to", 102L);
            this.mRunning = z;
            this.mAccessEvents = longSparseArrayUnparcel;
            this.mRejectEvents = longSparseArrayUnparcel2;
        }
    }

    @SystemApi
    public static final class OpEntry implements Parcelable {
        public static final Parcelable.Creator<OpEntry> CREATOR = new Parcelable.Creator<OpEntry>() { // from class: android.app.AppOpsManager.OpEntry.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public OpEntry[] newArray(int i) {
                return new OpEntry[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public OpEntry createFromParcel(Parcel parcel) {
                return new OpEntry(parcel);
            }
        };
        private final Map<String, AttributedOpEntry> mAttributedOpEntries;
        private final int mMode;
        private final int mOp;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public int getOp() {
            return this.mOp;
        }

        public String getOpStr() {
            return AppOpsManager.sOpToString[this.mOp];
        }

        @Deprecated
        public long getTime() {
            return getLastAccessTime(31);
        }

        public long getLastAccessTime(int i) {
            return getLastAccessTime(100, 700, i);
        }

        public long getLastAccessForegroundTime(int i) {
            return getLastAccessTime(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), i);
        }

        public long getLastAccessBackgroundTime(int i) {
            return getLastAccessTime(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, i);
        }

        private NoteOpEvent getLastAccessEvent(int i, int i2, int i3) {
            Iterator<AttributedOpEntry> it = this.mAttributedOpEntries.values().iterator();
            NoteOpEvent noteOpEvent = null;
            while (it.hasNext()) {
                NoteOpEvent lastAccessEvent = it.next().getLastAccessEvent(i, i2, i3);
                if (noteOpEvent == null || (lastAccessEvent != null && lastAccessEvent.getNoteTime() > noteOpEvent.getNoteTime())) {
                    noteOpEvent = lastAccessEvent;
                }
            }
            return noteOpEvent;
        }

        public long getLastAccessTime(int i, int i2, int i3) {
            NoteOpEvent lastAccessEvent = getLastAccessEvent(i, i2, i3);
            if (lastAccessEvent == null) {
                return -1L;
            }
            return lastAccessEvent.getNoteTime();
        }

        @Deprecated
        public long getRejectTime() {
            return getLastRejectTime(31);
        }

        public long getLastRejectTime(int i) {
            return getLastRejectTime(100, 700, i);
        }

        public long getLastRejectForegroundTime(int i) {
            return getLastRejectTime(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), i);
        }

        public long getLastRejectBackgroundTime(int i) {
            return getLastRejectTime(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, i);
        }

        private NoteOpEvent getLastRejectEvent(int i, int i2, int i3) {
            Iterator<AttributedOpEntry> it = this.mAttributedOpEntries.values().iterator();
            NoteOpEvent noteOpEvent = null;
            while (it.hasNext()) {
                NoteOpEvent lastRejectEvent = it.next().getLastRejectEvent(i, i2, i3);
                if (noteOpEvent == null || (lastRejectEvent != null && lastRejectEvent.getNoteTime() > noteOpEvent.getNoteTime())) {
                    noteOpEvent = lastRejectEvent;
                }
            }
            return noteOpEvent;
        }

        public long getLastRejectTime(int i, int i2, int i3) {
            NoteOpEvent lastRejectEvent = getLastRejectEvent(i, i2, i3);
            if (lastRejectEvent == null) {
                return -1L;
            }
            return lastRejectEvent.getNoteTime();
        }

        public boolean isRunning() {
            Iterator<AttributedOpEntry> it = this.mAttributedOpEntries.values().iterator();
            while (it.hasNext()) {
                if (it.next().isRunning()) {
                    return true;
                }
            }
            return false;
        }

        @Deprecated
        public long getDuration() {
            return getLastDuration(31);
        }

        public long getLastDuration(int i) {
            return getLastDuration(100, 700, i);
        }

        public long getLastForegroundDuration(int i) {
            return getLastDuration(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), i);
        }

        public long getLastBackgroundDuration(int i) {
            return getLastDuration(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, i);
        }

        public long getLastDuration(int i, int i2, int i3) {
            NoteOpEvent lastAccessEvent = getLastAccessEvent(i, i2, i3);
            if (lastAccessEvent == null) {
                return -1L;
            }
            return lastAccessEvent.getDuration();
        }

        @Deprecated
        public int getProxyUid() {
            OpEventProxyInfo lastProxyInfo = getLastProxyInfo(31);
            if (lastProxyInfo == null) {
                return -1;
            }
            return lastProxyInfo.getUid();
        }

        @Deprecated
        public int getProxyUid(int i, int i2) {
            OpEventProxyInfo lastProxyInfo = getLastProxyInfo(i, i, i2);
            if (lastProxyInfo == null) {
                return -1;
            }
            return lastProxyInfo.getUid();
        }

        @Deprecated
        public String getProxyPackageName() {
            OpEventProxyInfo lastProxyInfo = getLastProxyInfo(31);
            if (lastProxyInfo == null) {
                return null;
            }
            return lastProxyInfo.getPackageName();
        }

        @Deprecated
        public String getProxyPackageName(int i, int i2) {
            OpEventProxyInfo lastProxyInfo = getLastProxyInfo(i, i, i2);
            if (lastProxyInfo == null) {
                return null;
            }
            return lastProxyInfo.getPackageName();
        }

        public OpEventProxyInfo getLastProxyInfo(int i) {
            return getLastProxyInfo(100, 700, i);
        }

        public OpEventProxyInfo getLastForegroundProxyInfo(int i) {
            return getLastProxyInfo(100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), i);
        }

        public OpEventProxyInfo getLastBackgroundProxyInfo(int i) {
            return getLastProxyInfo(AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, i);
        }

        public OpEventProxyInfo getLastProxyInfo(int i, int i2, int i3) {
            NoteOpEvent lastAccessEvent = getLastAccessEvent(i, i2, i3);
            if (lastAccessEvent == null) {
                return null;
            }
            return lastAccessEvent.getProxy();
        }

        public OpEntry(int i, int i2, Map<String, AttributedOpEntry> map) {
            this.mOp = i;
            AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, i, "from", 0L, "to", 102L);
            this.mMode = i2;
            AnnotationValidations.validate((Class<? extends Annotation>) Mode.class, (Annotation) null, i2);
            this.mAttributedOpEntries = map;
            AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) map);
        }

        public int getMode() {
            return this.mMode;
        }

        public Map<String, AttributedOpEntry> getAttributedOpEntries() {
            return this.mAttributedOpEntries;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mOp);
            parcel.writeInt(this.mMode);
            parcel.writeMap(this.mAttributedOpEntries);
        }

        OpEntry(Parcel parcel) {
            int i = parcel.readInt();
            int i2 = parcel.readInt();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            parcel.readMap(linkedHashMap, AttributedOpEntry.class.getClassLoader());
            this.mOp = i;
            AnnotationValidations.validate((Class<IntRange>) IntRange.class, (IntRange) null, i, "from", 0L, "to", 102L);
            this.mMode = i2;
            AnnotationValidations.validate((Class<? extends Annotation>) Mode.class, (Annotation) null, i2);
            this.mAttributedOpEntries = linkedHashMap;
            AnnotationValidations.validate((Class<NonNull>) NonNull.class, (NonNull) null, (Object) linkedHashMap);
        }
    }

    @SystemApi
    public static final class HistoricalOpsRequest {
        private final String mAttributionTag;
        private final long mBeginTimeMillis;
        private final long mEndTimeMillis;
        private final int mFilter;
        private final int mFlags;
        private final List<String> mOpNames;
        private final String mPackageName;
        private final int mUid;

        private HistoricalOpsRequest(int i, String str, String str2, List<String> list, int i2, long j, long j2, int i3) {
            this.mUid = i;
            this.mPackageName = str;
            this.mAttributionTag = str2;
            this.mOpNames = list;
            this.mFilter = i2;
            this.mBeginTimeMillis = j;
            this.mEndTimeMillis = j2;
            this.mFlags = i3;
        }

        @SystemApi
        public static final class Builder {
            private String mAttributionTag;
            private final long mBeginTimeMillis;
            private final long mEndTimeMillis;
            private int mFilter;
            private List<String> mOpNames;
            private String mPackageName;
            private int mUid = -1;
            private int mFlags = 31;

            public Builder(long j, long j2) {
                Preconditions.checkArgument(j >= 0 && j < j2, "beginTimeMillis must be non negative and lesser than endTimeMillis");
                this.mBeginTimeMillis = j;
                this.mEndTimeMillis = j2;
            }

            public Builder setUid(int i) {
                Preconditions.checkArgument(i == -1 || i >= 0, "uid must be -1 or non negative");
                this.mUid = i;
                if (i == -1) {
                    this.mFilter &= -2;
                } else {
                    this.mFilter |= 1;
                }
                return this;
            }

            public Builder setPackageName(String str) {
                this.mPackageName = str;
                if (str == null) {
                    this.mFilter &= -3;
                } else {
                    this.mFilter |= 2;
                }
                return this;
            }

            public Builder setAttributionTag(String str) {
                this.mAttributionTag = str;
                this.mFilter |= 4;
                return this;
            }

            public Builder setOpNames(List<String> list) {
                if (list != null) {
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        Preconditions.checkArgument(AppOpsManager.strOpToOp(list.get(i)) != -1);
                    }
                }
                this.mOpNames = list;
                if (list == null) {
                    this.mFilter &= -9;
                } else {
                    this.mFilter |= 8;
                }
                return this;
            }

            public Builder setFlags(int i) {
                Preconditions.checkFlagsArgument(i, 31);
                this.mFlags = i;
                return this;
            }

            public HistoricalOpsRequest build() {
                return new HistoricalOpsRequest(this.mUid, this.mPackageName, this.mAttributionTag, this.mOpNames, this.mFilter, this.mBeginTimeMillis, this.mEndTimeMillis, this.mFlags);
            }
        }
    }

    @SystemApi
    public static final class HistoricalOps implements Parcelable {
        public static final Parcelable.Creator<HistoricalOps> CREATOR = new Parcelable.Creator<HistoricalOps>() { // from class: android.app.AppOpsManager.HistoricalOps.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public HistoricalOps createFromParcel(Parcel parcel) {
                return new HistoricalOps(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public HistoricalOps[] newArray(int i) {
                return new HistoricalOps[i];
            }
        };
        private long mBeginTimeMillis;
        private long mEndTimeMillis;
        private SparseArray<HistoricalUidOps> mHistoricalUidOps;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public HistoricalOps(long j, long j2) {
            Preconditions.checkState(j <= j2);
            this.mBeginTimeMillis = j;
            this.mEndTimeMillis = j2;
        }

        public HistoricalOps(HistoricalOps historicalOps) {
            long j = historicalOps.mBeginTimeMillis;
            this.mBeginTimeMillis = j;
            long j2 = historicalOps.mEndTimeMillis;
            this.mEndTimeMillis = j2;
            Preconditions.checkState(j <= j2);
            if (historicalOps.mHistoricalUidOps != null) {
                int uidCount = historicalOps.getUidCount();
                for (int i = 0; i < uidCount; i++) {
                    HistoricalUidOps historicalUidOps = new HistoricalUidOps(historicalOps.getUidOpsAt(i));
                    if (this.mHistoricalUidOps == null) {
                        this.mHistoricalUidOps = new SparseArray<>(uidCount);
                    }
                    this.mHistoricalUidOps.put(historicalUidOps.getUid(), historicalUidOps);
                }
            }
        }

        private HistoricalOps(Parcel parcel) {
            this.mBeginTimeMillis = parcel.readLong();
            this.mEndTimeMillis = parcel.readLong();
            int[] iArrCreateIntArray = parcel.createIntArray();
            if (ArrayUtils.isEmpty(iArrCreateIntArray)) {
                return;
            }
            ParceledListSlice parceledListSlice = (ParceledListSlice) parcel.readParcelable(HistoricalOps.class.getClassLoader());
            List list = parceledListSlice != null ? parceledListSlice.getList() : null;
            if (list == null) {
                return;
            }
            for (int i = 0; i < iArrCreateIntArray.length; i++) {
                if (this.mHistoricalUidOps == null) {
                    this.mHistoricalUidOps = new SparseArray<>();
                }
                this.mHistoricalUidOps.put(iArrCreateIntArray[i], (HistoricalUidOps) list.get(i));
            }
        }

        public HistoricalOps spliceFromBeginning(double d) {
            return splice(d, true);
        }

        public HistoricalOps spliceFromEnd(double d) {
            return splice(d, false);
        }

        private HistoricalOps splice(double d, boolean z) {
            long durationMillis;
            long durationMillis2;
            if (z) {
                durationMillis = this.mBeginTimeMillis;
                durationMillis2 = (long) (durationMillis + (getDurationMillis() * d));
                this.mBeginTimeMillis = durationMillis2;
            } else {
                durationMillis = (long) (this.mEndTimeMillis - (getDurationMillis() * d));
                durationMillis2 = this.mEndTimeMillis;
                this.mEndTimeMillis = durationMillis;
            }
            HistoricalOps historicalOps = null;
            int uidCount = getUidCount();
            for (int i = 0; i < uidCount; i++) {
                HistoricalUidOps historicalUidOpsSplice = getUidOpsAt(i).splice(d);
                if (historicalUidOpsSplice != null) {
                    if (historicalOps == null) {
                        historicalOps = new HistoricalOps(durationMillis, durationMillis2);
                    }
                    if (historicalOps.mHistoricalUidOps == null) {
                        historicalOps.mHistoricalUidOps = new SparseArray<>();
                    }
                    historicalOps.mHistoricalUidOps.put(historicalUidOpsSplice.getUid(), historicalUidOpsSplice);
                }
            }
            return historicalOps;
        }

        public void merge(HistoricalOps historicalOps) {
            this.mBeginTimeMillis = Math.min(this.mBeginTimeMillis, historicalOps.mBeginTimeMillis);
            this.mEndTimeMillis = Math.max(this.mEndTimeMillis, historicalOps.mEndTimeMillis);
            int uidCount = historicalOps.getUidCount();
            for (int i = 0; i < uidCount; i++) {
                HistoricalUidOps uidOpsAt = historicalOps.getUidOpsAt(i);
                HistoricalUidOps uidOps = getUidOps(uidOpsAt.getUid());
                if (uidOps == null) {
                    if (this.mHistoricalUidOps == null) {
                        this.mHistoricalUidOps = new SparseArray<>();
                    }
                    this.mHistoricalUidOps.put(uidOpsAt.getUid(), uidOpsAt);
                } else {
                    uidOps.merge(uidOpsAt);
                }
            }
        }

        public void filter(int i, String str, String str2, String[] strArr, int i2, long j, long j2) {
            long durationMillis = getDurationMillis();
            this.mBeginTimeMillis = Math.max(this.mBeginTimeMillis, j);
            this.mEndTimeMillis = Math.min(this.mEndTimeMillis, j2);
            double dMin = Math.min((j2 - j) / durationMillis, 1.0d);
            for (int uidCount = getUidCount() - 1; uidCount >= 0; uidCount--) {
                HistoricalUidOps historicalUidOpsValueAt = this.mHistoricalUidOps.valueAt(uidCount);
                if ((i2 & 1) != 0 && i != historicalUidOpsValueAt.getUid()) {
                    this.mHistoricalUidOps.removeAt(uidCount);
                } else {
                    historicalUidOpsValueAt.filter(str, str2, strArr, i2, dMin);
                    if (historicalUidOpsValueAt.getPackageCount() == 0) {
                        this.mHistoricalUidOps.removeAt(uidCount);
                    }
                }
            }
        }

        public boolean isEmpty() {
            if (getBeginTimeMillis() >= getEndTimeMillis()) {
                return true;
            }
            for (int uidCount = getUidCount() - 1; uidCount >= 0; uidCount--) {
                if (!this.mHistoricalUidOps.valueAt(uidCount).isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        public long getDurationMillis() {
            return this.mEndTimeMillis - this.mBeginTimeMillis;
        }

        public void increaseAccessCount(int i, int i2, String str, String str2, int i3, int i4, long j) {
            getOrCreateHistoricalUidOps(i2).increaseAccessCount(i, str, str2, i3, i4, j);
        }

        public void increaseRejectCount(int i, int i2, String str, String str2, int i3, int i4, long j) {
            getOrCreateHistoricalUidOps(i2).increaseRejectCount(i, str, str2, i3, i4, j);
        }

        public void increaseAccessDuration(int i, int i2, String str, String str2, int i3, int i4, long j) {
            getOrCreateHistoricalUidOps(i2).increaseAccessDuration(i, str, str2, i3, i4, j);
        }

        public void offsetBeginAndEndTime(long j) {
            this.mBeginTimeMillis += j;
            this.mEndTimeMillis += j;
        }

        public void setBeginAndEndTime(long j, long j2) {
            this.mBeginTimeMillis = j;
            this.mEndTimeMillis = j2;
        }

        public void setBeginTime(long j) {
            this.mBeginTimeMillis = j;
        }

        public void setEndTime(long j) {
            this.mEndTimeMillis = j;
        }

        public long getBeginTimeMillis() {
            return this.mBeginTimeMillis;
        }

        public long getEndTimeMillis() {
            return this.mEndTimeMillis;
        }

        public int getUidCount() {
            SparseArray<HistoricalUidOps> sparseArray = this.mHistoricalUidOps;
            if (sparseArray == null) {
                return 0;
            }
            return sparseArray.size();
        }

        public HistoricalUidOps getUidOpsAt(int i) {
            SparseArray<HistoricalUidOps> sparseArray = this.mHistoricalUidOps;
            if (sparseArray == null) {
                throw new IndexOutOfBoundsException();
            }
            return sparseArray.valueAt(i);
        }

        public HistoricalUidOps getUidOps(int i) {
            SparseArray<HistoricalUidOps> sparseArray = this.mHistoricalUidOps;
            if (sparseArray == null) {
                return null;
            }
            return sparseArray.get(i);
        }

        public void clearHistory(int i, String str) {
            HistoricalUidOps orCreateHistoricalUidOps = getOrCreateHistoricalUidOps(i);
            orCreateHistoricalUidOps.clearHistory(str);
            if (orCreateHistoricalUidOps.isEmpty()) {
                this.mHistoricalUidOps.remove(i);
            }
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.mBeginTimeMillis);
            parcel.writeLong(this.mEndTimeMillis);
            SparseArray<HistoricalUidOps> sparseArray = this.mHistoricalUidOps;
            if (sparseArray != null) {
                int size = sparseArray.size();
                parcel.writeInt(size);
                for (int i2 = 0; i2 < size; i2++) {
                    parcel.writeInt(this.mHistoricalUidOps.keyAt(i2));
                }
                ArrayList arrayList = new ArrayList(size);
                for (int i3 = 0; i3 < size; i3++) {
                    arrayList.add(this.mHistoricalUidOps.valueAt(i3));
                }
                parcel.writeParcelable(new ParceledListSlice(arrayList), i);
                return;
            }
            parcel.writeInt(-1);
        }

        public void accept(HistoricalOpsVisitor historicalOpsVisitor) {
            historicalOpsVisitor.visitHistoricalOps(this);
            int uidCount = getUidCount();
            for (int i = 0; i < uidCount; i++) {
                getUidOpsAt(i).accept(historicalOpsVisitor);
            }
        }

        private HistoricalUidOps getOrCreateHistoricalUidOps(int i) {
            if (this.mHistoricalUidOps == null) {
                this.mHistoricalUidOps = new SparseArray<>();
            }
            HistoricalUidOps historicalUidOps = this.mHistoricalUidOps.get(i);
            if (historicalUidOps != null) {
                return historicalUidOps;
            }
            HistoricalUidOps historicalUidOps2 = new HistoricalUidOps(i);
            this.mHistoricalUidOps.put(i, historicalUidOps2);
            return historicalUidOps2;
        }

        public static double round(double d) {
            return new BigDecimal(d).setScale(0, RoundingMode.HALF_UP).doubleValue();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            HistoricalOps historicalOps = (HistoricalOps) obj;
            if (this.mBeginTimeMillis != historicalOps.mBeginTimeMillis || this.mEndTimeMillis != historicalOps.mEndTimeMillis) {
                return false;
            }
            SparseArray<HistoricalUidOps> sparseArray = this.mHistoricalUidOps;
            if (sparseArray == null) {
                if (historicalOps.mHistoricalUidOps != null) {
                    return false;
                }
            } else if (!sparseArray.equals(historicalOps.mHistoricalUidOps)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            long j = this.mBeginTimeMillis;
            return (((int) (j ^ (j >>> 32))) * 31) + this.mHistoricalUidOps.hashCode();
        }

        public String toString() {
            return getClass().getSimpleName() + "[from:" + this.mBeginTimeMillis + " to:" + this.mEndTimeMillis + "]";
        }
    }

    @SystemApi
    public static final class HistoricalUidOps implements Parcelable {
        public static final Parcelable.Creator<HistoricalUidOps> CREATOR = new Parcelable.Creator<HistoricalUidOps>() { // from class: android.app.AppOpsManager.HistoricalUidOps.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public HistoricalUidOps createFromParcel(Parcel parcel) {
                return new HistoricalUidOps(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public HistoricalUidOps[] newArray(int i) {
                return new HistoricalUidOps[i];
            }
        };
        private ArrayMap<String, HistoricalPackageOps> mHistoricalPackageOps;
        private final int mUid;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public HistoricalUidOps(int i) {
            this.mUid = i;
        }

        private HistoricalUidOps(HistoricalUidOps historicalUidOps) {
            this.mUid = historicalUidOps.mUid;
            int packageCount = historicalUidOps.getPackageCount();
            for (int i = 0; i < packageCount; i++) {
                HistoricalPackageOps historicalPackageOps = new HistoricalPackageOps(historicalUidOps.getPackageOpsAt(i));
                if (this.mHistoricalPackageOps == null) {
                    this.mHistoricalPackageOps = new ArrayMap<>(packageCount);
                }
                this.mHistoricalPackageOps.put(historicalPackageOps.getPackageName(), historicalPackageOps);
            }
        }

        private HistoricalUidOps(Parcel parcel) {
            this.mUid = parcel.readInt();
            this.mHistoricalPackageOps = parcel.createTypedArrayMap(HistoricalPackageOps.CREATOR);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public HistoricalUidOps splice(double d) {
            int packageCount = getPackageCount();
            HistoricalUidOps historicalUidOps = null;
            for (int i = 0; i < packageCount; i++) {
                HistoricalPackageOps historicalPackageOpsSplice = getPackageOpsAt(i).splice(d);
                if (historicalPackageOpsSplice != null) {
                    if (historicalUidOps == null) {
                        historicalUidOps = new HistoricalUidOps(this.mUid);
                    }
                    if (historicalUidOps.mHistoricalPackageOps == null) {
                        historicalUidOps.mHistoricalPackageOps = new ArrayMap<>();
                    }
                    historicalUidOps.mHistoricalPackageOps.put(historicalPackageOpsSplice.getPackageName(), historicalPackageOpsSplice);
                }
            }
            return historicalUidOps;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void merge(HistoricalUidOps historicalUidOps) {
            int packageCount = historicalUidOps.getPackageCount();
            for (int i = 0; i < packageCount; i++) {
                HistoricalPackageOps packageOpsAt = historicalUidOps.getPackageOpsAt(i);
                HistoricalPackageOps packageOps = getPackageOps(packageOpsAt.getPackageName());
                if (packageOps == null) {
                    if (this.mHistoricalPackageOps == null) {
                        this.mHistoricalPackageOps = new ArrayMap<>();
                    }
                    this.mHistoricalPackageOps.put(packageOpsAt.getPackageName(), packageOpsAt);
                } else {
                    packageOps.merge(packageOpsAt);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void filter(String str, String str2, String[] strArr, int i, double d) {
            for (int packageCount = getPackageCount() - 1; packageCount >= 0; packageCount--) {
                HistoricalPackageOps packageOpsAt = getPackageOpsAt(packageCount);
                if ((i & 2) != 0 && !str.equals(packageOpsAt.getPackageName())) {
                    this.mHistoricalPackageOps.removeAt(packageCount);
                } else {
                    packageOpsAt.filter(str2, strArr, i, d);
                    if (packageOpsAt.getAttributedOpsCount() == 0) {
                        this.mHistoricalPackageOps.removeAt(packageCount);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isEmpty() {
            for (int packageCount = getPackageCount() - 1; packageCount >= 0; packageCount--) {
                if (!this.mHistoricalPackageOps.valueAt(packageCount).isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void increaseAccessCount(int i, String str, String str2, int i2, int i3, long j) {
            getOrCreateHistoricalPackageOps(str).increaseAccessCount(i, str2, i2, i3, j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void increaseRejectCount(int i, String str, String str2, int i2, int i3, long j) {
            getOrCreateHistoricalPackageOps(str).increaseRejectCount(i, str2, i2, i3, j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void increaseAccessDuration(int i, String str, String str2, int i2, int i3, long j) {
            getOrCreateHistoricalPackageOps(str).increaseAccessDuration(i, str2, i2, i3, j);
        }

        public int getUid() {
            return this.mUid;
        }

        public int getPackageCount() {
            ArrayMap<String, HistoricalPackageOps> arrayMap = this.mHistoricalPackageOps;
            if (arrayMap == null) {
                return 0;
            }
            return arrayMap.size();
        }

        public HistoricalPackageOps getPackageOpsAt(int i) {
            ArrayMap<String, HistoricalPackageOps> arrayMap = this.mHistoricalPackageOps;
            if (arrayMap == null) {
                throw new IndexOutOfBoundsException();
            }
            return arrayMap.valueAt(i);
        }

        public HistoricalPackageOps getPackageOps(String str) {
            ArrayMap<String, HistoricalPackageOps> arrayMap = this.mHistoricalPackageOps;
            if (arrayMap == null) {
                return null;
            }
            return arrayMap.get(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearHistory(String str) {
            ArrayMap<String, HistoricalPackageOps> arrayMap = this.mHistoricalPackageOps;
            if (arrayMap != null) {
                arrayMap.remove(str);
            }
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mUid);
            parcel.writeTypedArrayMap(this.mHistoricalPackageOps, i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void accept(HistoricalOpsVisitor historicalOpsVisitor) {
            historicalOpsVisitor.visitHistoricalUidOps(this);
            int packageCount = getPackageCount();
            for (int i = 0; i < packageCount; i++) {
                getPackageOpsAt(i).accept(historicalOpsVisitor);
            }
        }

        private HistoricalPackageOps getOrCreateHistoricalPackageOps(String str) {
            if (this.mHistoricalPackageOps == null) {
                this.mHistoricalPackageOps = new ArrayMap<>();
            }
            HistoricalPackageOps historicalPackageOps = this.mHistoricalPackageOps.get(str);
            if (historicalPackageOps != null) {
                return historicalPackageOps;
            }
            HistoricalPackageOps historicalPackageOps2 = new HistoricalPackageOps(str);
            this.mHistoricalPackageOps.put(str, historicalPackageOps2);
            return historicalPackageOps2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            HistoricalUidOps historicalUidOps = (HistoricalUidOps) obj;
            if (this.mUid != historicalUidOps.mUid) {
                return false;
            }
            ArrayMap<String, HistoricalPackageOps> arrayMap = this.mHistoricalPackageOps;
            if (arrayMap == null) {
                if (historicalUidOps.mHistoricalPackageOps != null) {
                    return false;
                }
            } else if (!arrayMap.equals(historicalUidOps.mHistoricalPackageOps)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = this.mUid * 31;
            ArrayMap<String, HistoricalPackageOps> arrayMap = this.mHistoricalPackageOps;
            return i + (arrayMap != null ? arrayMap.hashCode() : 0);
        }
    }

    @SystemApi
    public static final class HistoricalPackageOps implements Parcelable {
        public static final Parcelable.Creator<HistoricalPackageOps> CREATOR = new Parcelable.Creator<HistoricalPackageOps>() { // from class: android.app.AppOpsManager.HistoricalPackageOps.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public HistoricalPackageOps createFromParcel(Parcel parcel) {
                return new HistoricalPackageOps(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public HistoricalPackageOps[] newArray(int i) {
                return new HistoricalPackageOps[i];
            }
        };
        private ArrayMap<String, AttributedHistoricalOps> mAttributedHistoricalOps;
        private final String mPackageName;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public HistoricalPackageOps(String str) {
            this.mPackageName = str;
        }

        private HistoricalPackageOps(HistoricalPackageOps historicalPackageOps) {
            this.mPackageName = historicalPackageOps.mPackageName;
            int attributedOpsCount = historicalPackageOps.getAttributedOpsCount();
            for (int i = 0; i < attributedOpsCount; i++) {
                AttributedHistoricalOps attributedHistoricalOps = new AttributedHistoricalOps(historicalPackageOps.getAttributedOpsAt(i));
                if (this.mAttributedHistoricalOps == null) {
                    this.mAttributedHistoricalOps = new ArrayMap<>(attributedOpsCount);
                }
                this.mAttributedHistoricalOps.put(attributedHistoricalOps.getTag(), attributedHistoricalOps);
            }
        }

        private HistoricalPackageOps(Parcel parcel) {
            this.mPackageName = parcel.readString();
            this.mAttributedHistoricalOps = parcel.createTypedArrayMap(AttributedHistoricalOps.CREATOR);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public HistoricalPackageOps splice(double d) {
            int attributedOpsCount = getAttributedOpsCount();
            HistoricalPackageOps historicalPackageOps = null;
            for (int i = 0; i < attributedOpsCount; i++) {
                AttributedHistoricalOps attributedHistoricalOpsSplice = getAttributedOpsAt(i).splice(d);
                if (attributedHistoricalOpsSplice != null) {
                    if (historicalPackageOps == null) {
                        historicalPackageOps = new HistoricalPackageOps(this.mPackageName);
                    }
                    if (historicalPackageOps.mAttributedHistoricalOps == null) {
                        historicalPackageOps.mAttributedHistoricalOps = new ArrayMap<>();
                    }
                    historicalPackageOps.mAttributedHistoricalOps.put(attributedHistoricalOpsSplice.getTag(), attributedHistoricalOpsSplice);
                }
            }
            return historicalPackageOps;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void merge(HistoricalPackageOps historicalPackageOps) {
            int attributedOpsCount = historicalPackageOps.getAttributedOpsCount();
            for (int i = 0; i < attributedOpsCount; i++) {
                AttributedHistoricalOps attributedOpsAt = historicalPackageOps.getAttributedOpsAt(i);
                AttributedHistoricalOps attributedOps = getAttributedOps(attributedOpsAt.getTag());
                if (attributedOps == null) {
                    if (this.mAttributedHistoricalOps == null) {
                        this.mAttributedHistoricalOps = new ArrayMap<>();
                    }
                    this.mAttributedHistoricalOps.put(attributedOpsAt.getTag(), attributedOpsAt);
                } else {
                    attributedOps.merge(attributedOpsAt);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void filter(String str, String[] strArr, int i, double d) {
            for (int attributedOpsCount = getAttributedOpsCount() - 1; attributedOpsCount >= 0; attributedOpsCount--) {
                AttributedHistoricalOps attributedOpsAt = getAttributedOpsAt(attributedOpsCount);
                if ((i & 4) != 0 && !Objects.equals(str, attributedOpsAt.getTag())) {
                    this.mAttributedHistoricalOps.removeAt(attributedOpsCount);
                } else {
                    attributedOpsAt.filter(strArr, i, d);
                    if (attributedOpsAt.getOpCount() == 0) {
                        this.mAttributedHistoricalOps.removeAt(attributedOpsCount);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void accept(HistoricalOpsVisitor historicalOpsVisitor) {
            historicalOpsVisitor.visitHistoricalPackageOps(this);
            int attributedOpsCount = getAttributedOpsCount();
            for (int i = 0; i < attributedOpsCount; i++) {
                getAttributedOpsAt(i).accept(historicalOpsVisitor);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isEmpty() {
            for (int attributedOpsCount = getAttributedOpsCount() - 1; attributedOpsCount >= 0; attributedOpsCount--) {
                if (!this.mAttributedHistoricalOps.valueAt(attributedOpsCount).isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void increaseAccessCount(int i, String str, int i2, int i3, long j) {
            getOrCreateAttributedHistoricalOps(str).increaseAccessCount(i, i2, i3, j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void increaseRejectCount(int i, String str, int i2, int i3, long j) {
            getOrCreateAttributedHistoricalOps(str).increaseRejectCount(i, i2, i3, j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void increaseAccessDuration(int i, String str, int i2, int i3, long j) {
            getOrCreateAttributedHistoricalOps(str).increaseAccessDuration(i, i2, i3, j);
        }

        public String getPackageName() {
            return this.mPackageName;
        }

        private AttributedHistoricalOps getOrCreateAttributedHistoricalOps(String str) {
            if (this.mAttributedHistoricalOps == null) {
                this.mAttributedHistoricalOps = new ArrayMap<>();
            }
            AttributedHistoricalOps attributedHistoricalOps = this.mAttributedHistoricalOps.get(str);
            if (attributedHistoricalOps != null) {
                return attributedHistoricalOps;
            }
            AttributedHistoricalOps attributedHistoricalOps2 = new AttributedHistoricalOps(str);
            this.mAttributedHistoricalOps.put(str, attributedHistoricalOps2);
            return attributedHistoricalOps2;
        }

        public int getOpCount() {
            int attributedOpsCount = getAttributedOpsCount();
            int i = 0;
            for (int i2 = 0; i2 < 103; i2++) {
                String strOpToPublicName = AppOpsManager.opToPublicName(i2);
                int i3 = 0;
                while (true) {
                    if (i3 >= attributedOpsCount) {
                        break;
                    }
                    if (getAttributedOpsAt(i3).getOp(strOpToPublicName) != null) {
                        i++;
                        break;
                    }
                    i3++;
                }
            }
            return i;
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0029, code lost:
        
            r2 = r2 + 1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public android.app.AppOpsManager.HistoricalOp getOpAt(int r8) {
            /*
                r7 = this;
                int r0 = r7.getAttributedOpsCount()
                r1 = 0
                r2 = r1
                r3 = r2
            L7:
                r4 = 103(0x67, float:1.44E-43)
                if (r2 >= r4) goto L2c
                java.lang.String r4 = android.app.AppOpsManager.opToPublicName(r2)
                r5 = r1
            L10:
                if (r5 >= r0) goto L29
                android.app.AppOpsManager$AttributedHistoricalOps r6 = r7.getAttributedOpsAt(r5)
                android.app.AppOpsManager$HistoricalOp r6 = r6.getOp(r4)
                if (r6 == 0) goto L26
                if (r3 != r8) goto L23
                android.app.AppOpsManager$HistoricalOp r8 = r7.getOp(r4)
                return r8
            L23:
                int r3 = r3 + 1
                goto L29
            L26:
                int r5 = r5 + 1
                goto L10
            L29:
                int r2 = r2 + 1
                goto L7
            L2c:
                java.lang.IndexOutOfBoundsException r8 = new java.lang.IndexOutOfBoundsException
                r8.<init>()
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: android.app.AppOpsManager.HistoricalPackageOps.getOpAt(int):android.app.AppOpsManager$HistoricalOp");
        }

        public HistoricalOp getOp(String str) {
            if (this.mAttributedHistoricalOps == null) {
                return null;
            }
            int attributedOpsCount = getAttributedOpsCount();
            HistoricalOp historicalOp = null;
            for (int i = 0; i < attributedOpsCount; i++) {
                HistoricalOp op = getAttributedOpsAt(i).getOp(str);
                if (op != null) {
                    if (historicalOp == null) {
                        historicalOp = new HistoricalOp(op);
                    } else {
                        historicalOp.merge(op);
                    }
                }
            }
            return historicalOp;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.mPackageName);
            parcel.writeTypedArrayMap(this.mAttributedHistoricalOps, i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            HistoricalPackageOps historicalPackageOps = (HistoricalPackageOps) obj;
            if (!this.mPackageName.equals(historicalPackageOps.mPackageName)) {
                return false;
            }
            ArrayMap<String, AttributedHistoricalOps> arrayMap = this.mAttributedHistoricalOps;
            if (arrayMap == null) {
                if (historicalPackageOps.mAttributedHistoricalOps != null) {
                    return false;
                }
            } else if (!arrayMap.equals(historicalPackageOps.mAttributedHistoricalOps)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            String str = this.mPackageName;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            ArrayMap<String, AttributedHistoricalOps> arrayMap = this.mAttributedHistoricalOps;
            return iHashCode + (arrayMap != null ? arrayMap.hashCode() : 0);
        }

        public int getAttributedOpsCount() {
            ArrayMap<String, AttributedHistoricalOps> arrayMap = this.mAttributedHistoricalOps;
            if (arrayMap == null) {
                return 0;
            }
            return arrayMap.size();
        }

        public AttributedHistoricalOps getAttributedOpsAt(int i) {
            ArrayMap<String, AttributedHistoricalOps> arrayMap = this.mAttributedHistoricalOps;
            if (arrayMap == null) {
                throw new IndexOutOfBoundsException();
            }
            return arrayMap.valueAt(i);
        }

        public AttributedHistoricalOps getAttributedOps(String str) {
            ArrayMap<String, AttributedHistoricalOps> arrayMap = this.mAttributedHistoricalOps;
            if (arrayMap == null) {
                return null;
            }
            return arrayMap.get(str);
        }
    }

    @SystemApi
    public static final class AttributedHistoricalOps implements Parcelable {
        public static final Parcelable.Creator<AttributedHistoricalOps> CREATOR = new Parcelable.Creator<AttributedHistoricalOps>() { // from class: android.app.AppOpsManager.AttributedHistoricalOps.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public AttributedHistoricalOps[] newArray(int i) {
                return new AttributedHistoricalOps[i];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public AttributedHistoricalOps createFromParcel(Parcel parcel) {
                return new AttributedHistoricalOps(parcel);
            }
        };
        private ArrayMap<String, HistoricalOp> mHistoricalOps;
        private final String mTag;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public AttributedHistoricalOps(String str) {
            this.mTag = str;
        }

        private AttributedHistoricalOps(AttributedHistoricalOps attributedHistoricalOps) {
            this.mTag = attributedHistoricalOps.mTag;
            int opCount = attributedHistoricalOps.getOpCount();
            for (int i = 0; i < opCount; i++) {
                HistoricalOp historicalOp = new HistoricalOp(attributedHistoricalOps.getOpAt(i));
                if (this.mHistoricalOps == null) {
                    this.mHistoricalOps = new ArrayMap<>(opCount);
                }
                this.mHistoricalOps.put(historicalOp.getOpName(), historicalOp);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public AttributedHistoricalOps splice(double d) {
            int opCount = getOpCount();
            AttributedHistoricalOps attributedHistoricalOps = null;
            for (int i = 0; i < opCount; i++) {
                HistoricalOp historicalOpSplice = getOpAt(i).splice(d);
                if (historicalOpSplice != null) {
                    if (attributedHistoricalOps == null) {
                        attributedHistoricalOps = new AttributedHistoricalOps(this.mTag, (ArrayMap<String, HistoricalOp>) null);
                    }
                    if (attributedHistoricalOps.mHistoricalOps == null) {
                        attributedHistoricalOps.mHistoricalOps = new ArrayMap<>();
                    }
                    attributedHistoricalOps.mHistoricalOps.put(historicalOpSplice.getOpName(), historicalOpSplice);
                }
            }
            return attributedHistoricalOps;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void merge(AttributedHistoricalOps attributedHistoricalOps) {
            int opCount = attributedHistoricalOps.getOpCount();
            for (int i = 0; i < opCount; i++) {
                HistoricalOp opAt = attributedHistoricalOps.getOpAt(i);
                HistoricalOp op = getOp(opAt.getOpName());
                if (op == null) {
                    if (this.mHistoricalOps == null) {
                        this.mHistoricalOps = new ArrayMap<>();
                    }
                    this.mHistoricalOps.put(opAt.getOpName(), opAt);
                } else {
                    op.merge(opAt);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void filter(String[] strArr, int i, double d) {
            for (int opCount = getOpCount() - 1; opCount >= 0; opCount--) {
                HistoricalOp historicalOpValueAt = this.mHistoricalOps.valueAt(opCount);
                if ((i & 8) != 0 && !ArrayUtils.contains(strArr, historicalOpValueAt.getOpName())) {
                    this.mHistoricalOps.removeAt(opCount);
                } else {
                    historicalOpValueAt.filter(d);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isEmpty() {
            for (int opCount = getOpCount() - 1; opCount >= 0; opCount--) {
                if (!this.mHistoricalOps.valueAt(opCount).isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void increaseAccessCount(int i, int i2, int i3, long j) {
            getOrCreateHistoricalOp(i).increaseAccessCount(i2, i3, j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void increaseRejectCount(int i, int i2, int i3, long j) {
            getOrCreateHistoricalOp(i).increaseRejectCount(i2, i3, j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void increaseAccessDuration(int i, int i2, int i3, long j) {
            getOrCreateHistoricalOp(i).increaseAccessDuration(i2, i3, j);
        }

        public int getOpCount() {
            ArrayMap<String, HistoricalOp> arrayMap = this.mHistoricalOps;
            if (arrayMap == null) {
                return 0;
            }
            return arrayMap.size();
        }

        public HistoricalOp getOpAt(int i) {
            ArrayMap<String, HistoricalOp> arrayMap = this.mHistoricalOps;
            if (arrayMap == null) {
                throw new IndexOutOfBoundsException();
            }
            return arrayMap.valueAt(i);
        }

        public HistoricalOp getOp(String str) {
            ArrayMap<String, HistoricalOp> arrayMap = this.mHistoricalOps;
            if (arrayMap == null) {
                return null;
            }
            return arrayMap.get(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void accept(HistoricalOpsVisitor historicalOpsVisitor) {
            historicalOpsVisitor.visitHistoricalAttributionOps(this);
            int opCount = getOpCount();
            for (int i = 0; i < opCount; i++) {
                getOpAt(i).accept(historicalOpsVisitor);
            }
        }

        private HistoricalOp getOrCreateHistoricalOp(int i) {
            if (this.mHistoricalOps == null) {
                this.mHistoricalOps = new ArrayMap<>();
            }
            String str = AppOpsManager.sOpToString[i];
            HistoricalOp historicalOp = this.mHistoricalOps.get(str);
            if (historicalOp != null) {
                return historicalOp;
            }
            HistoricalOp historicalOp2 = new HistoricalOp(i);
            this.mHistoricalOps.put(str, historicalOp2);
            return historicalOp2;
        }

        public AttributedHistoricalOps(String str, ArrayMap<String, HistoricalOp> arrayMap) {
            this.mTag = str;
            this.mHistoricalOps = arrayMap;
        }

        public String getTag() {
            return this.mTag;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AttributedHistoricalOps attributedHistoricalOps = (AttributedHistoricalOps) obj;
            return Objects.equals(this.mTag, attributedHistoricalOps.mTag) && Objects.equals(this.mHistoricalOps, attributedHistoricalOps.mHistoricalOps);
        }

        public int hashCode() {
            return ((Objects.hashCode(this.mTag) + 31) * 31) + Objects.hashCode(this.mHistoricalOps);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            byte b = this.mTag != null ? (byte) 1 : (byte) 0;
            if (this.mHistoricalOps != null) {
                b = (byte) (b | 2);
            }
            parcel.writeByte(b);
            String str = this.mTag;
            if (str != null) {
                parcel.writeString(str);
            }
            ArrayMap<String, HistoricalOp> arrayMap = this.mHistoricalOps;
            if (arrayMap != null) {
                parcel.writeMap(arrayMap);
            }
        }

        AttributedHistoricalOps(Parcel parcel) {
            byte b = parcel.readByte();
            ArrayMap<String, HistoricalOp> arrayMap = null;
            String string = (b & 1) == 0 ? null : parcel.readString();
            if ((b & 2) != 0) {
                arrayMap = new ArrayMap<>();
                parcel.readMap(arrayMap, HistoricalOp.class.getClassLoader());
            }
            this.mTag = string;
            this.mHistoricalOps = arrayMap;
        }
    }

    @SystemApi
    public static final class HistoricalOp implements Parcelable {
        public static final Parcelable.Creator<HistoricalOp> CREATOR = new Parcelable.Creator<HistoricalOp>() { // from class: android.app.AppOpsManager.HistoricalOp.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public HistoricalOp createFromParcel(Parcel parcel) {
                return new HistoricalOp(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public HistoricalOp[] newArray(int i) {
                return new HistoricalOp[i];
            }
        };
        private LongSparseLongArray mAccessCount;
        private LongSparseLongArray mAccessDuration;
        private final int mOp;
        private LongSparseLongArray mRejectCount;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public HistoricalOp(int i) {
            this.mOp = i;
        }

        private HistoricalOp(HistoricalOp historicalOp) {
            this.mOp = historicalOp.mOp;
            LongSparseLongArray longSparseLongArray = historicalOp.mAccessCount;
            if (longSparseLongArray != null) {
                this.mAccessCount = longSparseLongArray.m41clone();
            }
            LongSparseLongArray longSparseLongArray2 = historicalOp.mRejectCount;
            if (longSparseLongArray2 != null) {
                this.mRejectCount = longSparseLongArray2.m41clone();
            }
            LongSparseLongArray longSparseLongArray3 = historicalOp.mAccessDuration;
            if (longSparseLongArray3 != null) {
                this.mAccessDuration = longSparseLongArray3.m41clone();
            }
        }

        private HistoricalOp(Parcel parcel) {
            this.mOp = parcel.readInt();
            this.mAccessCount = AppOpsManager.readLongSparseLongArrayFromParcel(parcel);
            this.mRejectCount = AppOpsManager.readLongSparseLongArrayFromParcel(parcel);
            this.mAccessDuration = AppOpsManager.readLongSparseLongArrayFromParcel(parcel);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void filter(double d) {
            scale(this.mAccessCount, d);
            scale(this.mRejectCount, d);
            scale(this.mAccessDuration, d);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isEmpty() {
            return (hasData(this.mAccessCount) || hasData(this.mRejectCount) || hasData(this.mAccessDuration)) ? false : true;
        }

        private boolean hasData(LongSparseLongArray longSparseLongArray) {
            return longSparseLongArray != null && longSparseLongArray.size() > 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public HistoricalOp splice(double d) {
            HistoricalOp historicalOp = new HistoricalOp(this.mOp);
            splice(this.mAccessCount, new $$Lambda$AppOpsManager$HistoricalOp$HUOLFYs8TiaQIOXcrq6JzjxA6gs(historicalOp), d);
            splice(this.mRejectCount, new $$Lambda$AppOpsManager$HistoricalOp$DkVcBvqB32SMHlxw0sWQPh3GL1A(historicalOp), d);
            splice(this.mAccessDuration, new $$Lambda$AppOpsManager$HistoricalOp$Vs6pDL0wjOBTquwNnreWVbPQrn4(historicalOp), d);
            return historicalOp;
        }

        private static void splice(LongSparseLongArray longSparseLongArray, Supplier<LongSparseLongArray> supplier, double d) {
            if (longSparseLongArray != null) {
                int size = longSparseLongArray.size();
                for (int i = 0; i < size; i++) {
                    long jKeyAt = longSparseLongArray.keyAt(i);
                    long jValueAt = longSparseLongArray.valueAt(i);
                    long jRound = Math.round(jValueAt * d);
                    if (jRound > 0) {
                        supplier.get().put(jKeyAt, jRound);
                        longSparseLongArray.put(jKeyAt, jValueAt - jRound);
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void merge(HistoricalOp historicalOp) {
            merge(new $$Lambda$AppOpsManager$HistoricalOp$HUOLFYs8TiaQIOXcrq6JzjxA6gs(this), historicalOp.mAccessCount);
            merge(new $$Lambda$AppOpsManager$HistoricalOp$DkVcBvqB32SMHlxw0sWQPh3GL1A(this), historicalOp.mRejectCount);
            merge(new $$Lambda$AppOpsManager$HistoricalOp$Vs6pDL0wjOBTquwNnreWVbPQrn4(this), historicalOp.mAccessDuration);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void increaseAccessCount(int i, int i2, long j) {
            increaseCount(getOrCreateAccessCount(), i, i2, j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void increaseRejectCount(int i, int i2, long j) {
            increaseCount(getOrCreateRejectCount(), i, i2, j);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void increaseAccessDuration(int i, int i2, long j) {
            increaseCount(getOrCreateAccessDuration(), i, i2, j);
        }

        private void increaseCount(LongSparseLongArray longSparseLongArray, int i, int i2, long j) {
            while (i2 != 0) {
                int iNumberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(i2);
                i2 &= ~iNumberOfTrailingZeros;
                long jMakeKey = AppOpsManager.makeKey(i, iNumberOfTrailingZeros);
                longSparseLongArray.put(jMakeKey, longSparseLongArray.get(jMakeKey) + j);
            }
        }

        public String getOpName() {
            return AppOpsManager.sOpToString[this.mOp];
        }

        public int getOpCode() {
            return this.mOp;
        }

        public long getForegroundAccessCount(int i) {
            return AppOpsManager.sumForFlagsInStates(this.mAccessCount, 100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), i);
        }

        public long getBackgroundAccessCount(int i) {
            return AppOpsManager.sumForFlagsInStates(this.mAccessCount, AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, i);
        }

        public long getAccessCount(int i, int i2, int i3) {
            return AppOpsManager.sumForFlagsInStates(this.mAccessCount, i, i2, i3);
        }

        public long getForegroundRejectCount(int i) {
            return AppOpsManager.sumForFlagsInStates(this.mRejectCount, 100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), i);
        }

        public long getBackgroundRejectCount(int i) {
            return AppOpsManager.sumForFlagsInStates(this.mRejectCount, AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, i);
        }

        public long getRejectCount(int i, int i2, int i3) {
            return AppOpsManager.sumForFlagsInStates(this.mRejectCount, i, i2, i3);
        }

        public long getForegroundAccessDuration(int i) {
            return AppOpsManager.sumForFlagsInStates(this.mAccessDuration, 100, AppOpsManager.resolveFirstUnrestrictedUidState(this.mOp), i);
        }

        public long getBackgroundAccessDuration(int i) {
            return AppOpsManager.sumForFlagsInStates(this.mAccessDuration, AppOpsManager.resolveLastRestrictedUidState(this.mOp), 700, i);
        }

        public long getAccessDuration(int i, int i2, int i3) {
            return AppOpsManager.sumForFlagsInStates(this.mAccessDuration, i, i2, i3);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mOp);
            AppOpsManager.writeLongSparseLongArrayToParcel(this.mAccessCount, parcel);
            AppOpsManager.writeLongSparseLongArrayToParcel(this.mRejectCount, parcel);
            AppOpsManager.writeLongSparseLongArrayToParcel(this.mAccessDuration, parcel);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            HistoricalOp historicalOp = (HistoricalOp) obj;
            if (this.mOp == historicalOp.mOp && AppOpsManager.equalsLongSparseLongArray(this.mAccessCount, historicalOp.mAccessCount) && AppOpsManager.equalsLongSparseLongArray(this.mRejectCount, historicalOp.mRejectCount)) {
                return AppOpsManager.equalsLongSparseLongArray(this.mAccessDuration, historicalOp.mAccessDuration);
            }
            return false;
        }

        public int hashCode() {
            return (((((this.mOp * 31) + Objects.hashCode(this.mAccessCount)) * 31) + Objects.hashCode(this.mRejectCount)) * 31) + Objects.hashCode(this.mAccessDuration);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void accept(HistoricalOpsVisitor historicalOpsVisitor) {
            historicalOpsVisitor.visitHistoricalOp(this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public LongSparseLongArray getOrCreateAccessCount() {
            if (this.mAccessCount == null) {
                this.mAccessCount = new LongSparseLongArray();
            }
            return this.mAccessCount;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public LongSparseLongArray getOrCreateRejectCount() {
            if (this.mRejectCount == null) {
                this.mRejectCount = new LongSparseLongArray();
            }
            return this.mRejectCount;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public LongSparseLongArray getOrCreateAccessDuration() {
            if (this.mAccessDuration == null) {
                this.mAccessDuration = new LongSparseLongArray();
            }
            return this.mAccessDuration;
        }

        private static void scale(LongSparseLongArray longSparseLongArray, double d) {
            if (longSparseLongArray != null) {
                int size = longSparseLongArray.size();
                for (int i = 0; i < size; i++) {
                    longSparseLongArray.put(longSparseLongArray.keyAt(i), (long) HistoricalOps.round(longSparseLongArray.valueAt(i) * d));
                }
            }
        }

        private static void merge(Supplier<LongSparseLongArray> supplier, LongSparseLongArray longSparseLongArray) {
            if (longSparseLongArray != null) {
                int size = longSparseLongArray.size();
                for (int i = 0; i < size; i++) {
                    LongSparseLongArray longSparseLongArray2 = supplier.get();
                    long jKeyAt = longSparseLongArray.keyAt(i);
                    longSparseLongArray2.put(jKeyAt, longSparseLongArray2.get(jKeyAt) + longSparseLongArray.valueAt(i));
                }
            }
        }

        public LongSparseArray<Object> collectKeys() {
            return AppOpsManager.collectKeys(this.mAccessDuration, AppOpsManager.collectKeys(this.mRejectCount, AppOpsManager.collectKeys(this.mAccessCount, null)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long sumForFlagsInStates(LongSparseLongArray longSparseLongArray, int i, int i2, int i3) {
        long j = 0;
        if (longSparseLongArray == null) {
            return 0L;
        }
        while (i3 != 0) {
            int iNumberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(i3);
            i3 &= ~iNumberOfTrailingZeros;
            for (int i4 : UID_STATES) {
                if (i4 >= i && i4 <= i2) {
                    j += longSparseLongArray.get(makeKey(i4, iNumberOfTrailingZeros));
                }
            }
        }
        return j;
    }

    AppOpsManager(Context context, IAppOpsService iAppOpsService) {
        this.mContext = context;
        this.mService = iAppOpsService;
    }

    @SystemApi
    public List<PackageOps> getPackagesForOps(String[] strArr) {
        int length = strArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = sOpStrToOp.get(strArr[i]).intValue();
        }
        List<PackageOps> packagesForOps = getPackagesForOps(iArr);
        return packagesForOps != null ? packagesForOps : Collections.emptyList();
    }

    public List<PackageOps> getPackagesForOps(int[] iArr) {
        try {
            return this.mService.getPackagesForOps(iArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    @Deprecated
    public List<PackageOps> getOpsForPackage(int i, String str, int[] iArr) {
        try {
            return this.mService.getOpsForPackage(i, str, iArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public List<PackageOps> getOpsForPackage(int i, String str, String... strArr) {
        int[] iArr;
        if (strArr != null) {
            iArr = new int[strArr.length];
            for (int i2 = 0; i2 < strArr.length; i2++) {
                iArr[i2] = strOpToOp(strArr[i2]);
            }
        } else {
            iArr = null;
        }
        try {
            List<PackageOps> opsForPackage = this.mService.getOpsForPackage(i, str, iArr);
            return opsForPackage == null ? Collections.emptyList() : opsForPackage;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void getHistoricalOps(HistoricalOpsRequest historicalOpsRequest, final Executor executor, final Consumer<HistoricalOps> consumer) {
        Objects.requireNonNull(executor, "executor cannot be null");
        Objects.requireNonNull(consumer, "callback cannot be null");
        try {
            this.mService.getHistoricalOps(historicalOpsRequest.mUid, historicalOpsRequest.mPackageName, historicalOpsRequest.mAttributionTag, historicalOpsRequest.mOpNames, historicalOpsRequest.mFilter, historicalOpsRequest.mBeginTimeMillis, historicalOpsRequest.mEndTimeMillis, historicalOpsRequest.mFlags, new RemoteCallback(new RemoteCallback.OnResultListener() { // from class: android.app.-$$Lambda$AppOpsManager$4Zbi7CSLEt0nvOmfJBVYtJkauTQ
                @Override // android.os.RemoteCallback.OnResultListener
                public final void onResult(Bundle bundle) {
                    AppOpsManager.lambda$getHistoricalOps$1(executor, consumer, bundle);
                }
            }));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    static /* synthetic */ void lambda$getHistoricalOps$1(Executor executor, final Consumer consumer, Bundle bundle) {
        final HistoricalOps historicalOps = (HistoricalOps) bundle.getParcelable(KEY_HISTORICAL_OPS);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            executor.execute(new Runnable() { // from class: android.app.-$$Lambda$AppOpsManager$frSyqmhVUmNbhMckfMS3PSwTMlw
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(historicalOps);
                }
            });
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void getHistoricalOpsFromDiskRaw(HistoricalOpsRequest historicalOpsRequest, final Executor executor, final Consumer<HistoricalOps> consumer) {
        Objects.requireNonNull(executor, "executor cannot be null");
        Objects.requireNonNull(consumer, "callback cannot be null");
        try {
            this.mService.getHistoricalOpsFromDiskRaw(historicalOpsRequest.mUid, historicalOpsRequest.mPackageName, historicalOpsRequest.mAttributionTag, historicalOpsRequest.mOpNames, historicalOpsRequest.mFilter, historicalOpsRequest.mBeginTimeMillis, historicalOpsRequest.mEndTimeMillis, historicalOpsRequest.mFlags, new RemoteCallback(new RemoteCallback.OnResultListener() { // from class: android.app.-$$Lambda$AppOpsManager$5k42P8tID8pwpGFZvo7VQyru20E
                @Override // android.os.RemoteCallback.OnResultListener
                public final void onResult(Bundle bundle) {
                    AppOpsManager.lambda$getHistoricalOpsFromDiskRaw$3(executor, consumer, bundle);
                }
            }));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    static /* synthetic */ void lambda$getHistoricalOpsFromDiskRaw$3(Executor executor, final Consumer consumer, Bundle bundle) {
        final HistoricalOps historicalOps = (HistoricalOps) bundle.getParcelable(KEY_HISTORICAL_OPS);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            executor.execute(new Runnable() { // from class: android.app.-$$Lambda$AppOpsManager$VfNXTtJaOeEFrdIj0oDWr_N9nks
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(historicalOps);
                }
            });
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void reloadNonHistoricalState() {
        try {
            this.mService.reloadNonHistoricalState();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setUidMode(int i, int i2, int i3) {
        try {
            this.mService.setUidMode(i, i2, i3);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setUidMode(String str, int i, int i2) {
        try {
            this.mService.setUidMode(strOpToOp(str), i, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setUserRestriction(int i, boolean z, IBinder iBinder) {
        setUserRestriction(i, z, iBinder, null);
    }

    public void setUserRestriction(int i, boolean z, IBinder iBinder, String[] strArr) {
        setUserRestrictionForUser(i, z, iBinder, strArr, this.mContext.getUserId());
    }

    public void setUserRestrictionForUser(int i, boolean z, IBinder iBinder, String[] strArr, int i2) {
        try {
            this.mService.setUserRestriction(i, z, iBinder, i2, strArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setMode(int i, int i2, String str, int i3) {
        try {
            this.mService.setMode(i, i2, str, i3);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public void setMode(String str, int i, String str2, int i2) {
        try {
            this.mService.setMode(strOpToOp(str), i, str2, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setRestriction(int i, int i2, int i3, String[] strArr) {
        try {
            this.mService.setAudioRestriction(i, i2, Binder.getCallingUid(), i3, strArr);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void resetAllModes() {
        try {
            this.mService.resetAllModes(this.mContext.getUserId(), null);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static String permissionToOp(String str) {
        Integer num = sPermToOp.get(str);
        if (num == null) {
            return null;
        }
        return sOpToString[num.intValue()];
    }

    public void startWatchingMode(String str, String str2, OnOpChangedListener onOpChangedListener) {
        startWatchingMode(strOpToOp(str), str2, onOpChangedListener);
    }

    public void startWatchingMode(String str, String str2, int i, OnOpChangedListener onOpChangedListener) {
        startWatchingMode(strOpToOp(str), str2, i, onOpChangedListener);
    }

    public void startWatchingMode(int i, String str, OnOpChangedListener onOpChangedListener) {
        startWatchingMode(i, str, 0, onOpChangedListener);
    }

    public void startWatchingMode(int i, String str, int i2, final OnOpChangedListener onOpChangedListener) {
        synchronized (this.mModeWatchers) {
            IAppOpsCallback iAppOpsCallback = this.mModeWatchers.get(onOpChangedListener);
            if (iAppOpsCallback == null) {
                iAppOpsCallback = new IAppOpsCallback.Stub() { // from class: android.app.AppOpsManager.2
                    @Override // com.android.internal.app.IAppOpsCallback
                    public void opChanged(int i3, int i4, String str2) {
                        OnOpChangedListener onOpChangedListener2 = onOpChangedListener;
                        if (onOpChangedListener2 instanceof OnOpChangedInternalListener) {
                            ((OnOpChangedInternalListener) onOpChangedListener2).onOpChanged(i3, str2);
                        }
                        if (AppOpsManager.sOpToString[i3] != null) {
                            onOpChangedListener.onOpChanged(AppOpsManager.sOpToString[i3], str2);
                        }
                    }
                };
                this.mModeWatchers.put(onOpChangedListener, iAppOpsCallback);
            }
            if (!Compatibility.isChangeEnabled(CALL_BACK_ON_CHANGED_LISTENER_WITH_SWITCHED_OP_CHANGE)) {
                i2 |= 2;
            }
            try {
                this.mService.startWatchingModeWithFlags(i, str, i2, iAppOpsCallback);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void stopWatchingMode(OnOpChangedListener onOpChangedListener) {
        synchronized (this.mModeWatchers) {
            IAppOpsCallback iAppOpsCallbackRemove = this.mModeWatchers.remove(onOpChangedListener);
            if (iAppOpsCallbackRemove != null) {
                try {
                    this.mService.stopWatchingMode(iAppOpsCallbackRemove);
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        }
    }

    @Deprecated
    public void startWatchingActive(int[] iArr, OnOpActiveChangedListener onOpActiveChangedListener) {
        String[] strArr = new String[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            strArr[i] = opToPublicName(iArr[i]);
        }
        startWatchingActive(strArr, this.mContext.getMainExecutor(), onOpActiveChangedListener);
    }

    public void startWatchingActive(String[] strArr, Executor executor, OnOpActiveChangedListener onOpActiveChangedListener) {
        Objects.requireNonNull(strArr);
        Objects.requireNonNull(executor);
        Objects.requireNonNull(onOpActiveChangedListener);
        synchronized (this.mActiveWatchers) {
            if (this.mActiveWatchers.get(onOpActiveChangedListener) != null) {
                return;
            }
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(executor, onOpActiveChangedListener);
            this.mActiveWatchers.put(onOpActiveChangedListener, anonymousClass3);
            int[] iArr = new int[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                iArr[i] = strOpToOp(strArr[i]);
            }
            try {
                this.mService.startWatchingActive(iArr, anonymousClass3);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    /* JADX INFO: renamed from: android.app.AppOpsManager$3, reason: invalid class name */
    class AnonymousClass3 extends IAppOpsActiveCallback.Stub {
        final /* synthetic */ OnOpActiveChangedListener val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass3(Executor executor, OnOpActiveChangedListener onOpActiveChangedListener) {
            this.val$executor = executor;
            this.val$callback = onOpActiveChangedListener;
        }

        @Override // com.android.internal.app.IAppOpsActiveCallback
        public void opActiveChanged(final int i, final int i2, final String str, final boolean z) {
            Executor executor = this.val$executor;
            final OnOpActiveChangedListener onOpActiveChangedListener = this.val$callback;
            executor.execute(new Runnable() { // from class: android.app.-$$Lambda$AppOpsManager$3$aT8CbzI8Vm3cKKLkTbEyDBWuFQI
                @Override // java.lang.Runnable
                public final void run() {
                    AppOpsManager.AnonymousClass3.lambda$opActiveChanged$0(onOpActiveChangedListener, i, i2, str, z);
                }
            });
        }

        static /* synthetic */ void lambda$opActiveChanged$0(OnOpActiveChangedListener onOpActiveChangedListener, int i, int i2, String str, boolean z) {
            if (onOpActiveChangedListener instanceof OnOpActiveChangedInternalListener) {
                ((OnOpActiveChangedInternalListener) onOpActiveChangedListener).onOpActiveChanged(i, i2, str, z);
            }
            if (AppOpsManager.sOpToString[i] != null) {
                onOpActiveChangedListener.onOpActiveChanged(AppOpsManager.sOpToString[i], i2, str, z);
            }
        }
    }

    public void stopWatchingActive(OnOpActiveChangedListener onOpActiveChangedListener) {
        synchronized (this.mActiveWatchers) {
            IAppOpsActiveCallback iAppOpsActiveCallbackRemove = this.mActiveWatchers.remove(onOpActiveChangedListener);
            if (iAppOpsActiveCallbackRemove != null) {
                try {
                    this.mService.stopWatchingActive(iAppOpsActiveCallbackRemove);
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        }
    }

    public void startWatchingStarted(int[] iArr, final OnOpStartedListener onOpStartedListener) {
        synchronized (this.mStartedWatchers) {
            if (this.mStartedWatchers.containsKey(onOpStartedListener)) {
                return;
            }
            IAppOpsStartedCallback.Stub stub = new IAppOpsStartedCallback.Stub() { // from class: android.app.AppOpsManager.4
                @Override // com.android.internal.app.IAppOpsStartedCallback
                public void opStarted(int i, int i2, String str, int i3) {
                    onOpStartedListener.onOpStarted(i, i2, str, i3);
                }
            };
            this.mStartedWatchers.put(onOpStartedListener, stub);
            try {
                this.mService.startWatchingStarted(iArr, stub);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void stopWatchingStarted(OnOpStartedListener onOpStartedListener) {
        synchronized (this.mStartedWatchers) {
            IAppOpsStartedCallback iAppOpsStartedCallbackRemove = this.mStartedWatchers.remove(onOpStartedListener);
            if (iAppOpsStartedCallbackRemove != null) {
                try {
                    this.mService.stopWatchingStarted(iAppOpsStartedCallbackRemove);
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        }
    }

    public void startWatchingNoted(int[] iArr, final OnOpNotedListener onOpNotedListener) {
        synchronized (this.mNotedWatchers) {
            if (this.mNotedWatchers.get(onOpNotedListener) != null) {
                return;
            }
            IAppOpsNotedCallback.Stub stub = new IAppOpsNotedCallback.Stub() { // from class: android.app.AppOpsManager.5
                @Override // com.android.internal.app.IAppOpsNotedCallback
                public void opNoted(int i, int i2, String str, int i3) {
                    onOpNotedListener.onOpNoted(i, i2, str, i3);
                }
            };
            this.mNotedWatchers.put(onOpNotedListener, stub);
            try {
                this.mService.startWatchingNoted(iArr, stub);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public void stopWatchingNoted(OnOpNotedListener onOpNotedListener) {
        synchronized (this.mNotedWatchers) {
            IAppOpsNotedCallback iAppOpsNotedCallbackRemove = this.mNotedWatchers.remove(onOpNotedListener);
            if (iAppOpsNotedCallbackRemove != null) {
                try {
                    this.mService.stopWatchingNoted(iAppOpsNotedCallbackRemove);
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
        }
    }

    private String buildSecurityExceptionMsg(int i, int i2, String str) {
        return str + " from uid " + i2 + " not allowed to perform " + sOpNames[i];
    }

    public static int strOpToOp(String str) {
        Integer num = sOpStrToOp.get(str);
        if (num == null) {
            throw new IllegalArgumentException("Unknown operation string: " + str);
        }
        return num.intValue();
    }

    public int unsafeCheckOp(String str, int i, String str2) {
        return checkOp(strOpToOp(str), i, str2);
    }

    @Deprecated
    public int checkOp(String str, int i, String str2) {
        return checkOp(strOpToOp(str), i, str2);
    }

    public int unsafeCheckOpNoThrow(String str, int i, String str2) {
        return checkOpNoThrow(strOpToOp(str), i, str2);
    }

    @Deprecated
    public int checkOpNoThrow(String str, int i, String str2) {
        return checkOpNoThrow(strOpToOp(str), i, str2);
    }

    public int unsafeCheckOpRaw(String str, int i, String str2) {
        return unsafeCheckOpRawNoThrow(str, i, str2);
    }

    public int unsafeCheckOpRawNoThrow(String str, int i, String str2) {
        return unsafeCheckOpRawNoThrow(strOpToOp(str), i, str2);
    }

    public int unsafeCheckOpRawNoThrow(int i, int i2, String str) {
        try {
            return this.mService.checkOperationRaw(i, i2, str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public int noteOp(String str, int i, String str2) {
        return noteOp(str, i, str2, (String) null, (String) null);
    }

    @Deprecated
    public int noteOp(int i) {
        return noteOp(i, Process.myUid(), this.mContext.getOpPackageName(), (String) null, (String) null);
    }

    @Deprecated
    public int noteOp(int i, int i2, String str) {
        return noteOp(i, i2, str, (String) null, (String) null);
    }

    public int noteOp(String str, int i, String str2, String str3, String str4) {
        return noteOp(strOpToOp(str), i, str2, str3, str4);
    }

    public int noteOp(int i, int i2, String str, String str2, String str3) {
        int iNoteOpNoThrow = noteOpNoThrow(i, i2, str, str2, str3);
        if (iNoteOpNoThrow != 2) {
            return iNoteOpNoThrow;
        }
        throw new SecurityException(buildSecurityExceptionMsg(i, i2, str));
    }

    @Deprecated
    public int noteOpNoThrow(String str, int i, String str2) {
        return noteOpNoThrow(str, i, str2, (String) null, (String) null);
    }

    @Deprecated
    public int noteOpNoThrow(int i, int i2, String str) {
        return noteOpNoThrow(i, i2, str, (String) null, (String) null);
    }

    public int noteOpNoThrow(String str, int i, String str2, String str3, String str4) {
        return noteOpNoThrow(strOpToOp(str), i, str2, str3, str4);
    }

    public int noteOpNoThrow(int i, int i2, String str, String str2, String str3) {
        String formattedStackTrace;
        boolean z;
        try {
            collectNoteOpCallsForValidation(i);
            int notedOpCollectionMode = getNotedOpCollectionMode(i2, str, i);
            boolean z2 = Process.myUid() == 1000;
            if (notedOpCollectionMode == 3 && str3 == null) {
                formattedStackTrace = getFormattedStackTrace();
                z = true;
            } else {
                formattedStackTrace = str3;
                z = z2;
            }
            int iNoteOperation = this.mService.noteOperation(i, i2, str, str2, notedOpCollectionMode == 3, formattedStackTrace, z);
            if (iNoteOperation == 0) {
                if (notedOpCollectionMode == 1) {
                    collectNotedOpForSelf(i, str2);
                } else if (notedOpCollectionMode == 2) {
                    collectNotedOpSync(i, str2);
                }
            }
            return iNoteOperation;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public int noteProxyOp(String str, String str2) {
        return noteProxyOp(str, str2, Binder.getCallingUid(), (String) null, (String) null);
    }

    @Deprecated
    public int noteProxyOp(int i, String str) {
        return noteProxyOp(i, str, Binder.getCallingUid(), (String) null, (String) null);
    }

    public int noteProxyOp(int i, String str, int i2, String str2, String str3) {
        int iNoteProxyOpNoThrow = noteProxyOpNoThrow(i, str, i2, str2, str3);
        if (iNoteProxyOpNoThrow != 2) {
            return iNoteProxyOpNoThrow;
        }
        throw new SecurityException("Proxy package " + this.mContext.getOpPackageName() + " from uid " + Process.myUid() + " or calling package " + str + " from uid " + i2 + " not allowed to perform " + sOpNames[i]);
    }

    public int noteProxyOp(String str, String str2, int i, String str3, String str4) {
        return noteProxyOp(strOpToOp(str), str2, i, str3, str4);
    }

    @Deprecated
    public int noteProxyOpNoThrow(String str, String str2) {
        return noteProxyOpNoThrow(str, str2, Binder.getCallingUid(), (String) null, (String) null);
    }

    @Deprecated
    public int noteProxyOpNoThrow(String str, String str2, int i) {
        return noteProxyOpNoThrow(str, str2, i, (String) null, (String) null);
    }

    public int noteProxyOpNoThrow(String str, String str2, int i, String str3, String str4) {
        return noteProxyOpNoThrow(strOpToOp(str), str2, i, str3, str4);
    }

    public int noteProxyOpNoThrow(int i, String str, int i2, String str2, String str3) {
        String formattedStackTrace;
        boolean z;
        int iMyUid = Process.myUid();
        try {
            collectNoteOpCallsForValidation(i);
            int notedOpCollectionMode = getNotedOpCollectionMode(i2, str, i);
            boolean z2 = iMyUid == 1000;
            if (notedOpCollectionMode == 3 && str3 == null) {
                formattedStackTrace = getFormattedStackTrace();
                z = true;
            } else {
                formattedStackTrace = str3;
                z = z2;
            }
            int iNoteProxyOperation = this.mService.noteProxyOperation(i, i2, str, str2, iMyUid, this.mContext.getOpPackageName(), this.mContext.getAttributionTag(), notedOpCollectionMode == 3, formattedStackTrace, z);
            if (iNoteProxyOperation == 0) {
                if (notedOpCollectionMode == 1) {
                    collectNotedOpForSelf(i, str2);
                } else if (notedOpCollectionMode == 2 && (this.mContext.checkPermission(Manifest.permission.UPDATE_APP_OPS_STATS, -1, iMyUid) == 0 || isTrustedVoiceServiceProxy(this.mContext, this.mContext.getOpPackageName(), i, this.mContext.getUserId()))) {
                    collectNotedOpSync(i, str2);
                }
            }
            return iNoteProxyOperation;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static boolean isTrustedVoiceServiceProxy(Context context, String str, int i, int i2) {
        return i == 27 && Objects.equals(str, getComponentPackageNameFromString(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.VOICE_RECOGNITION_SERVICE))) && isPackagePreInstalled(context, str, i2);
    }

    private static String getComponentPackageNameFromString(String str) {
        ComponentName componentNameUnflattenFromString = str != null ? ComponentName.unflattenFromString(str) : null;
        return componentNameUnflattenFromString != null ? componentNameUnflattenFromString.getPackageName() : "";
    }

    private static boolean isPackagePreInstalled(Context context, String str, int i) {
        try {
            return (context.getPackageManager().getApplicationInfoAsUser(str, 0, i).flags & 1) != 0;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public int checkOp(int i, int i2, String str) {
        try {
            int iCheckOperation = this.mService.checkOperation(i, i2, str);
            if (iCheckOperation != 2) {
                return iCheckOperation;
            }
            throw new SecurityException(buildSecurityExceptionMsg(i, i2, str));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int checkOpNoThrow(int i, int i2, String str) {
        try {
            int iCheckOperation = this.mService.checkOperation(i, i2, str);
            if (iCheckOperation == 4) {
                return 0;
            }
            return iCheckOperation;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public void checkPackage(int i, String str) {
        try {
            if (this.mService.checkPackage(i, str) == 0) {
                return;
            }
            throw new SecurityException("Package " + str + " does not belong to " + i);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int checkAudioOp(int i, int i2, int i3, String str) {
        try {
            int iCheckAudioOperation = this.mService.checkAudioOperation(i, i2, i3, str);
            if (iCheckAudioOperation != 2) {
                return iCheckAudioOperation;
            }
            throw new SecurityException(buildSecurityExceptionMsg(i, i3, str));
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public int checkAudioOpNoThrow(int i, int i2, int i3, String str) {
        try {
            return this.mService.checkAudioOperation(i, i2, i3, str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public static IBinder getToken(IAppOpsService iAppOpsService) {
        return getClientId();
    }

    public static IBinder getClientId() {
        IBinder iBinder;
        synchronized (AppOpsManager.class) {
            if (sClientId == null) {
                sClientId = new Binder();
            }
            iBinder = sClientId;
        }
        return iBinder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static IAppOpsService getService() {
        IAppOpsService iAppOpsService;
        synchronized (sLock) {
            if (sService == null) {
                sService = IAppOpsService.Stub.asInterface(ServiceManager.getService(Context.APP_OPS_SERVICE));
            }
            iAppOpsService = sService;
        }
        return iAppOpsService;
    }

    @Deprecated
    public int startOp(String str, int i, String str2) {
        return startOp(str, i, str2, null, null);
    }

    @Deprecated
    public int startOp(int i) {
        return startOp(i, Process.myUid(), this.mContext.getOpPackageName(), false, null, null);
    }

    @Deprecated
    public int startOp(int i, int i2, String str) {
        return startOp(i, i2, str, false, null, null);
    }

    @Deprecated
    public int startOp(int i, int i2, String str, boolean z) {
        return startOp(i, i2, str, z, null, null);
    }

    public int startOp(String str, int i, String str2, String str3, String str4) {
        return startOp(strOpToOp(str), i, str2, false, str3, str4);
    }

    public int startOp(int i, int i2, String str, boolean z, String str2, String str3) {
        int iStartOpNoThrow = startOpNoThrow(i, i2, str, z, str2, str3);
        if (iStartOpNoThrow != 2) {
            return iStartOpNoThrow;
        }
        throw new SecurityException(buildSecurityExceptionMsg(i, i2, str));
    }

    @Deprecated
    public int startOpNoThrow(String str, int i, String str2) {
        return startOpNoThrow(str, i, str2, null, null);
    }

    @Deprecated
    public int startOpNoThrow(int i, int i2, String str) {
        return startOpNoThrow(i, i2, str, false, null, null);
    }

    @Deprecated
    public int startOpNoThrow(int i, int i2, String str, boolean z) {
        return startOpNoThrow(i, i2, str, z, null, null);
    }

    public int startOpNoThrow(String str, int i, String str2, String str3, String str4) {
        return startOpNoThrow(strOpToOp(str), i, str2, false, str3, str4);
    }

    public int startOpNoThrow(int i, int i2, String str, boolean z, String str2, String str3) {
        String formattedStackTrace;
        boolean z2;
        try {
            collectNoteOpCallsForValidation(i);
            int notedOpCollectionMode = getNotedOpCollectionMode(i2, str, i);
            boolean z3 = Process.myUid() == 1000;
            if (notedOpCollectionMode == 3 && str3 == null) {
                formattedStackTrace = getFormattedStackTrace();
                z2 = true;
            } else {
                formattedStackTrace = str3;
                z2 = z3;
            }
            int iStartOperation = this.mService.startOperation(getClientId(), i, i2, str, str2, z, notedOpCollectionMode == 3, formattedStackTrace, z2);
            if (iStartOperation == 0) {
                if (notedOpCollectionMode == 1) {
                    collectNotedOpForSelf(i, str2);
                } else if (notedOpCollectionMode == 2) {
                    collectNotedOpSync(i, str2);
                }
            }
            return iStartOperation;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @Deprecated
    public void finishOp(int i) {
        finishOp(i, Process.myUid(), this.mContext.getOpPackageName(), (String) null);
    }

    public void finishOp(String str, int i, String str2) {
        finishOp(strOpToOp(str), i, str2, (String) null);
    }

    public void finishOp(String str, int i, String str2, String str3) {
        finishOp(strOpToOp(str), i, str2, str3);
    }

    public void finishOp(int i, int i2, String str) {
        finishOp(i, i2, str, (String) null);
    }

    public void finishOp(int i, int i2, String str, String str2) {
        try {
            this.mService.finishOperation(getClientId(), i, i2, str, str2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isOpActive(String str, int i, String str2) {
        return isOperationActive(strOpToOp(str), i, str2);
    }

    public static void startNotedAppOpsCollection(int i) {
        sBinderThreadCallingUid.set(Integer.valueOf(i));
    }

    public static class PausedNotedAppOpsCollection {
        final ArrayMap<String, long[]> mCollectedNotedAppOps;
        final int mUid;

        PausedNotedAppOpsCollection(int i, ArrayMap<String, long[]> arrayMap) {
            this.mUid = i;
            this.mCollectedNotedAppOps = arrayMap;
        }
    }

    public static PausedNotedAppOpsCollection pauseNotedAppOpsCollection() {
        Integer num = sBinderThreadCallingUid.get();
        if (num == null) {
            return null;
        }
        ArrayMap<String, long[]> arrayMap = sAppOpsNotedInThisBinderTransaction.get();
        sBinderThreadCallingUid.remove();
        sAppOpsNotedInThisBinderTransaction.remove();
        return new PausedNotedAppOpsCollection(num.intValue(), arrayMap);
    }

    public static void resumeNotedAppOpsCollection(PausedNotedAppOpsCollection pausedNotedAppOpsCollection) {
        if (pausedNotedAppOpsCollection != null) {
            sBinderThreadCallingUid.set(Integer.valueOf(pausedNotedAppOpsCollection.mUid));
            if (pausedNotedAppOpsCollection.mCollectedNotedAppOps != null) {
                sAppOpsNotedInThisBinderTransaction.set(pausedNotedAppOpsCollection.mCollectedNotedAppOps);
            }
        }
    }

    public static void finishNotedAppOpsCollection() {
        sBinderThreadCallingUid.remove();
        sAppOpsNotedInThisBinderTransaction.remove();
    }

    private void collectNotedOpForSelf(int i, String str) {
        synchronized (sLock) {
            if (sOnOpNotedCallback != null) {
                sOnOpNotedCallback.onSelfNoted(new SyncNotedAppOp(i, str));
            }
        }
        sMessageCollector.onSelfNoted(new SyncNotedAppOp(i, str));
    }

    private void collectNotedOpSync(int i, String str) {
        ArrayMap<String, long[]> arrayMap = sAppOpsNotedInThisBinderTransaction.get();
        if (arrayMap == null) {
            arrayMap = new ArrayMap<>(1);
            sAppOpsNotedInThisBinderTransaction.set(arrayMap);
        }
        long[] jArr = arrayMap.get(str);
        if (jArr == null) {
            jArr = new long[2];
            arrayMap.put(str, jArr);
        }
        if (i < 64) {
            jArr[0] = jArr[0] | (1 << i);
        } else {
            jArr[1] = (1 << (i - 64)) | jArr[1];
        }
    }

    private int getNotedOpCollectionMode(int i, String str, int i2) {
        if (str == null) {
            str = "android";
        }
        if (sAppOpsToNote[i2] == 0) {
            try {
                if (this.mService.shouldCollectNotes(i2)) {
                    sAppOpsToNote[i2] = 2;
                } else {
                    sAppOpsToNote[i2] = 1;
                }
            } catch (RemoteException unused) {
                return 0;
            }
        }
        if (sAppOpsToNote[i2] != 2) {
            return 0;
        }
        synchronized (sLock) {
            if (i == Process.myUid() && str.equals(ActivityThread.currentOpPackageName())) {
                return 1;
            }
            Integer num = sBinderThreadCallingUid.get();
            return (num == null || num.intValue() != i) ? 3 : 2;
        }
    }

    public static void prefixParcelWithAppOpsIfNeeded(Parcel parcel) {
        ArrayMap<String, long[]> arrayMap = sAppOpsNotedInThisBinderTransaction.get();
        if (arrayMap == null) {
            return;
        }
        parcel.writeInt(-127);
        int size = arrayMap.size();
        parcel.writeInt(size);
        for (int i = 0; i < size; i++) {
            parcel.writeString(arrayMap.keyAt(i));
            parcel.writeLong(arrayMap.valueAt(i)[0]);
            parcel.writeLong(arrayMap.valueAt(i)[1]);
        }
    }

    public static void readAndLogNotedAppops(Parcel parcel) {
        int i = parcel.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            String string = parcel.readString();
            long[] jArr = {parcel.readLong(), parcel.readLong()};
            if (jArr[0] != 0 || jArr[1] != 0) {
                BitSet bitSetValueOf = BitSet.valueOf(jArr);
                synchronized (sLock) {
                    for (int iNextSetBit = bitSetValueOf.nextSetBit(0); iNextSetBit != -1; iNextSetBit = bitSetValueOf.nextSetBit(iNextSetBit + 1)) {
                        if (sOnOpNotedCallback != null) {
                            sOnOpNotedCallback.onNoted(new SyncNotedAppOp(iNextSetBit, string));
                        } else {
                            sUnforwardedOps.add(new AsyncNotedAppOp(iNextSetBit, Process.myUid(), string, getFormattedStackTrace(), System.currentTimeMillis()));
                            if (sUnforwardedOps.size() > 10) {
                                sUnforwardedOps.remove(0);
                            }
                        }
                    }
                }
                for (int iNextSetBit2 = bitSetValueOf.nextSetBit(0); iNextSetBit2 != -1; iNextSetBit2 = bitSetValueOf.nextSetBit(iNextSetBit2 + 1)) {
                    sMessageCollector.onNoted(new SyncNotedAppOp(iNextSetBit2, string));
                }
            }
        }
    }

    public void setOnOpNotedCallback(Executor executor, OnOpNotedCallback onOpNotedCallback) {
        boolean z = true;
        Preconditions.checkState((onOpNotedCallback == null) == (executor == null));
        synchronized (sLock) {
            List<AsyncNotedAppOp> listExtractAsyncOps = null;
            if (onOpNotedCallback == null) {
                if (sOnOpNotedCallback == null) {
                    z = false;
                }
                Preconditions.checkState(z, "No callback is currently registered");
                try {
                    this.mService.stopWatchingAsyncNoted(this.mContext.getPackageName(), sOnOpNotedCallback.mAsyncCb);
                } catch (RemoteException e) {
                    e.rethrowFromSystemServer();
                }
                sOnOpNotedCallback = null;
            } else {
                if (sOnOpNotedCallback != null) {
                    z = false;
                }
                Preconditions.checkState(z, "Another callback is already registered");
                onOpNotedCallback.mAsyncExecutor = executor;
                sOnOpNotedCallback = onOpNotedCallback;
                try {
                    this.mService.startWatchingAsyncNoted(this.mContext.getPackageName(), sOnOpNotedCallback.mAsyncCb);
                    listExtractAsyncOps = this.mService.extractAsyncOps(this.mContext.getPackageName());
                } catch (RemoteException e2) {
                    e2.rethrowFromSystemServer();
                }
                if (listExtractAsyncOps != null) {
                    int size = listExtractAsyncOps.size();
                    for (int i = 0; i < size; i++) {
                        final AsyncNotedAppOp asyncNotedAppOp = listExtractAsyncOps.get(i);
                        if (sOnOpNotedCallback != null) {
                            sOnOpNotedCallback.getAsyncNotedExecutor().execute(new Runnable() { // from class: android.app.-$$Lambda$AppOpsManager$PD824BJSYMsBzJsejbPpl76LS7s
                                @Override // java.lang.Runnable
                                public final void run() {
                                    AppOpsManager.sOnOpNotedCallback.onAsyncNoted(asyncNotedAppOp);
                                }
                            });
                        }
                    }
                }
                synchronized (this) {
                    int size2 = sUnforwardedOps.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        final AsyncNotedAppOp asyncNotedAppOp2 = sUnforwardedOps.get(i2);
                        if (sOnOpNotedCallback != null) {
                            sOnOpNotedCallback.getAsyncNotedExecutor().execute(new Runnable() { // from class: android.app.-$$Lambda$AppOpsManager$znmOchbf1jfrWSIXDpZNTtYuDQY
                                @Override // java.lang.Runnable
                                public final void run() {
                                    AppOpsManager.sOnOpNotedCallback.onAsyncNoted(asyncNotedAppOp2);
                                }
                            });
                        }
                    }
                    sUnforwardedOps.clear();
                }
            }
        }
    }

    @SystemApi
    @Deprecated
    public void setNotedAppOpsCollector(AppOpsCollector appOpsCollector) {
        synchronized (sLock) {
            if (appOpsCollector != null) {
                if (isListeningForOpNoted()) {
                    setOnOpNotedCallback(null, null);
                }
                setOnOpNotedCallback(new HandlerExecutor(Handler.getMain()), appOpsCollector);
            } else if (sOnOpNotedCallback != null) {
                setOnOpNotedCallback(null, null);
            }
        }
    }

    public static boolean isListeningForOpNoted() {
        return sOnOpNotedCallback != null || isCollectingStackTraces();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isCollectingStackTraces() {
        return sConfig.getSampledOpCode() != -1 || sConfig.getExpirationTimeSinceBootMillis() < SystemClock.elapsedRealtime();
    }

    public static abstract class OnOpNotedCallback {
        private final IAppOpsAsyncNotedCallback mAsyncCb = new AnonymousClass1();
        private Executor mAsyncExecutor;

        public abstract void onAsyncNoted(AsyncNotedAppOp asyncNotedAppOp);

        public abstract void onNoted(SyncNotedAppOp syncNotedAppOp);

        public abstract void onSelfNoted(SyncNotedAppOp syncNotedAppOp);

        /* JADX INFO: renamed from: android.app.AppOpsManager$OnOpNotedCallback$1, reason: invalid class name */
        class AnonymousClass1 extends IAppOpsAsyncNotedCallback.Stub {
            AnonymousClass1() {
            }

            @Override // com.android.internal.app.IAppOpsAsyncNotedCallback
            public void opNoted(final AsyncNotedAppOp asyncNotedAppOp) {
                Objects.requireNonNull(asyncNotedAppOp);
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    OnOpNotedCallback.this.getAsyncNotedExecutor().execute(new Runnable() { // from class: android.app.-$$Lambda$AppOpsManager$OnOpNotedCallback$1$Rp-GotIWwSMrFjDEmAcFNvnPRkY
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$opNoted$0$AppOpsManager$OnOpNotedCallback$1(asyncNotedAppOp);
                        }
                    });
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            }

            public /* synthetic */ void lambda$opNoted$0$AppOpsManager$OnOpNotedCallback$1(AsyncNotedAppOp asyncNotedAppOp) {
                OnOpNotedCallback.this.onAsyncNoted(asyncNotedAppOp);
            }
        }

        protected Executor getAsyncNotedExecutor() {
            return this.mAsyncExecutor;
        }
    }

    @SystemApi
    @Deprecated
    public static abstract class AppOpsCollector extends OnOpNotedCallback {
        @Override // android.app.AppOpsManager.OnOpNotedCallback
        public Executor getAsyncNotedExecutor() {
            return new HandlerExecutor(Handler.getMain());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getFormattedStackTrace() {
        int i;
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        int i2 = 0;
        for (int i3 = 0; i3 < stackTrace.length && (stackTrace[i3].getClassName().startsWith(AppOpsManager.class.getName()) || stackTrace[i3].getClassName().startsWith(Parcel.class.getName()) || stackTrace[i3].getClassName().contains("$Stub$Proxy") || stackTrace[i3].getClassName().startsWith(DatabaseUtils.class.getName()) || stackTrace[i3].getClassName().startsWith("android.content.ContentProviderProxy") || stackTrace[i3].getClassName().startsWith(ContentResolver.class.getName())); i3++) {
            i2 = i3;
        }
        int length = stackTrace.length - 1;
        int length2 = stackTrace.length - 1;
        while (true) {
            int i4 = length2;
            i = length;
            length = i4;
            if (length < 0 || !(stackTrace[length].getClassName().startsWith(HandlerThread.class.getName()) || stackTrace[length].getClassName().startsWith(Handler.class.getName()) || stackTrace[length].getClassName().startsWith(Looper.class.getName()) || stackTrace[length].getClassName().startsWith(Binder.class.getName()) || stackTrace[length].getClassName().startsWith(RuntimeInit.class.getName()) || stackTrace[length].getClassName().startsWith(ZygoteInit.class.getName()) || stackTrace[length].getClassName().startsWith(ActivityThread.class.getName()) || stackTrace[length].getClassName().startsWith(Method.class.getName()) || stackTrace[length].getClassName().startsWith("com.android.server.SystemServer"))) {
                break;
            }
            length2 = length - 1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i5 = i2; i5 <= i; i5++) {
            if (i5 != i2) {
                sb.append('\n');
            }
            if (sb.length() + stackTrace[i5].toString().length() > 600) {
                break;
            }
            sb.append(stackTrace[i5]);
        }
        return sb.toString();
    }

    public boolean isOperationActive(int i, int i2, String str) {
        try {
            return this.mService.isOperationActive(i, i2, str);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void setHistoryParameters(int i, long j, int i2) {
        try {
            this.mService.setHistoryParameters(i, j, i2);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void offsetHistory(long j) {
        try {
            this.mService.offsetHistory(j);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void addHistoricalOps(HistoricalOps historicalOps) {
        try {
            this.mService.addHistoricalOps(historicalOps);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void resetHistoryParameters() {
        try {
            this.mService.resetHistoryParameters();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void clearHistory() {
        try {
            this.mService.clearHistory();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public void rebootHistory(long j) {
        try {
            this.mService.rebootHistory(j);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public RuntimeAppOpAccessMessage collectRuntimeAppOpAccessMessage() {
        try {
            return this.mService.collectRuntimeAppOpAccessMessage();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    @SystemApi
    public static String[] getOpStrs() {
        String[] strArr = sOpToString;
        return (String[]) Arrays.copyOf(strArr, strArr.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static NoteOpEvent getLastEvent(LongSparseArray<NoteOpEvent> longSparseArray, int i, int i2, int i3) {
        NoteOpEvent noteOpEvent = null;
        if (longSparseArray == null) {
            return null;
        }
        while (i3 != 0) {
            int iNumberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(i3);
            i3 &= ~iNumberOfTrailingZeros;
            for (int i4 : UID_STATES) {
                if (i4 >= i && i4 <= i2) {
                    NoteOpEvent noteOpEvent2 = longSparseArray.get(makeKey(i4, iNumberOfTrailingZeros));
                    if (noteOpEvent == null || (noteOpEvent2 != null && noteOpEvent2.getNoteTime() > noteOpEvent.getNoteTime())) {
                        noteOpEvent = noteOpEvent2;
                    }
                }
            }
        }
        return noteOpEvent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean equalsLongSparseLongArray(LongSparseLongArray longSparseLongArray, LongSparseLongArray longSparseLongArray2) {
        if (longSparseLongArray == longSparseLongArray2) {
            return true;
        }
        if (longSparseLongArray == null || longSparseLongArray2 == null || longSparseLongArray.size() != longSparseLongArray2.size()) {
            return false;
        }
        int size = longSparseLongArray.size();
        for (int i = 0; i < size; i++) {
            if (longSparseLongArray.keyAt(i) != longSparseLongArray2.keyAt(i) || longSparseLongArray.valueAt(i) != longSparseLongArray2.valueAt(i)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void writeLongSparseLongArrayToParcel(LongSparseLongArray longSparseLongArray, Parcel parcel) {
        if (longSparseLongArray != null) {
            int size = longSparseLongArray.size();
            parcel.writeInt(size);
            for (int i = 0; i < size; i++) {
                parcel.writeLong(longSparseLongArray.keyAt(i));
                parcel.writeLong(longSparseLongArray.valueAt(i));
            }
            return;
        }
        parcel.writeInt(-1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static LongSparseLongArray readLongSparseLongArrayFromParcel(Parcel parcel) {
        int i = parcel.readInt();
        if (i < 0) {
            return null;
        }
        LongSparseLongArray longSparseLongArray = new LongSparseLongArray(i);
        for (int i2 = 0; i2 < i; i2++) {
            longSparseLongArray.append(parcel.readLong(), parcel.readLong());
        }
        return longSparseLongArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static LongSparseArray<Object> collectKeys(LongSparseLongArray longSparseLongArray, LongSparseArray<Object> longSparseArray) {
        if (longSparseLongArray != null) {
            if (longSparseArray == null) {
                longSparseArray = new LongSparseArray<>();
            }
            int size = longSparseLongArray.size();
            for (int i = 0; i < size; i++) {
                longSparseArray.put(longSparseLongArray.keyAt(i), null);
            }
        }
        return longSparseArray;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int parseHistoricalMode(java.lang.String r4) {
        /*
            int r0 = r4.hashCode()
            r1 = 155185419(0x93ff10b, float:2.3104127E-33)
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L1b
            r1 = 885538210(0x34c83da2, float:3.7297747E-7)
            if (r0 == r1) goto L11
            goto L25
        L11:
            java.lang.String r0 = "HISTORICAL_MODE_ENABLED_PASSIVE"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L25
            r4 = r3
            goto L26
        L1b:
            java.lang.String r0 = "HISTORICAL_MODE_ENABLED_ACTIVE"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L25
            r4 = r2
            goto L26
        L25:
            r4 = -1
        L26:
            if (r4 == 0) goto L2d
            if (r4 == r3) goto L2b
            return r2
        L2b:
            r4 = 2
            return r4
        L2d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.app.AppOpsManager.parseHistoricalMode(java.lang.String):int");
    }

    private static int getSystemAlertWindowDefault() {
        Application applicationCurrentApplication = ActivityThread.currentApplication();
        if (applicationCurrentApplication == null) {
            return 3;
        }
        return (!ActivityManager.isLowRamDeviceStatic() || applicationCurrentApplication.getPackageManager().hasSystemFeature(PackageManager.FEATURE_LEANBACK, 0)) ? 3 : 1;
    }

    public static int leftCircularDistance(int i, int i2, int i3) {
        return ((i2 + i3) - i) % i3;
    }
}
