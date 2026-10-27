package android.telephony;

import android.Manifest;
import android.annotation.SystemApi;
import android.app.PendingIntent;
import android.compat.Compatibility;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.PersistableBundle;
import android.os.Process;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.os.SystemProperties;
import android.os.WorkSource;
import android.provider.Settings;
import android.provider.Telephony;
import android.service.carrier.CarrierIdentifier;
import android.sysprop.TelephonyProperties;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.ICellInfoCallback;
import android.telephony.TelephonyScanManager;
import android.telephony.data.ApnSetting;
import android.telephony.emergency.EmergencyNumber;
import android.telephony.ims.aidl.IImsConfig;
import android.telephony.ims.aidl.IImsMmTelFeature;
import android.telephony.ims.aidl.IImsRcsFeature;
import android.telephony.ims.aidl.IImsRegistration;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.android.ims.internal.IImsServiceFeatureCallback;
import com.android.internal.R;
import com.android.internal.telephony.CellNetworkScanResult;
import com.android.internal.telephony.DctConstants;
import com.android.internal.telephony.IBooleanConsumer;
import com.android.internal.telephony.INumberVerificationCallback;
import com.android.internal.telephony.IOns;
import com.android.internal.telephony.IPhoneSubInfo;
import com.android.internal.telephony.ISetOpportunisticDataCallback;
import com.android.internal.telephony.ISms;
import com.android.internal.telephony.ISub;
import com.android.internal.telephony.ITelephony;
import com.android.internal.telephony.IUpdateAvailableNetworksCallback;
import com.android.internal.telephony.OperatorInfo;
import com.android.internal.telephony.PhoneConstants;
import com.android.internal.telephony.RILConstants;
import com.android.internal.telephony.SmsApplication;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public class TelephonyManager {

    @SystemApi
    public static final String ACTION_ANOMALY_REPORTED = "android.telephony.action.ANOMALY_REPORTED";
    public static final String ACTION_CALL_DISCONNECT_CAUSE_CHANGED = "android.intent.action.CALL_DISCONNECT_CAUSE";
    public static final String ACTION_CARRIER_MESSAGING_CLIENT_SERVICE = "android.telephony.action.CARRIER_MESSAGING_CLIENT_SERVICE";
    public static final String ACTION_CARRIER_SIGNAL_DEFAULT_NETWORK_AVAILABLE = "com.android.internal.telephony.CARRIER_SIGNAL_DEFAULT_NETWORK_AVAILABLE";
    public static final String ACTION_CARRIER_SIGNAL_PCO_VALUE = "com.android.internal.telephony.CARRIER_SIGNAL_PCO_VALUE";
    public static final String ACTION_CARRIER_SIGNAL_REDIRECTED = "com.android.internal.telephony.CARRIER_SIGNAL_REDIRECTED";
    public static final String ACTION_CARRIER_SIGNAL_REQUEST_NETWORK_FAILED = "com.android.internal.telephony.CARRIER_SIGNAL_REQUEST_NETWORK_FAILED";
    public static final String ACTION_CARRIER_SIGNAL_RESET = "com.android.internal.telephony.CARRIER_SIGNAL_RESET";
    public static final String ACTION_CONFIGURE_VOICEMAIL = "android.telephony.action.CONFIGURE_VOICEMAIL";
    public static final String ACTION_DATA_STALL_DETECTED = "android.intent.action.DATA_STALL_DETECTED";

    @SystemApi
    public static final String ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED = "android.intent.action.ACTION_DEFAULT_DATA_SUBSCRIPTION_CHANGED";

    @SystemApi
    public static final String ACTION_DEFAULT_VOICE_SUBSCRIPTION_CHANGED = "android.intent.action.ACTION_DEFAULT_VOICE_SUBSCRIPTION_CHANGED";

    @SystemApi
    public static final String ACTION_EMERGENCY_ASSISTANCE = "android.telephony.action.EMERGENCY_ASSISTANCE";

    @SystemApi
    public static final String ACTION_EMERGENCY_CALLBACK_MODE_CHANGED = "android.intent.action.EMERGENCY_CALLBACK_MODE_CHANGED";

    @SystemApi
    public static final String ACTION_EMERGENCY_CALL_STATE_CHANGED = "android.intent.action.EMERGENCY_CALL_STATE_CHANGED";
    public static final String ACTION_MULTI_SIM_CONFIG_CHANGED = "android.telephony.action.MULTI_SIM_CONFIG_CHANGED";
    public static final String ACTION_NETWORK_COUNTRY_CHANGED = "android.telephony.action.NETWORK_COUNTRY_CHANGED";
    public static final String ACTION_PHONE_STATE_CHANGED = "android.intent.action.PHONE_STATE";
    public static final String ACTION_PRIMARY_SUBSCRIPTION_LIST_CHANGED = "android.telephony.action.PRIMARY_SUBSCRIPTION_LIST_CHANGED";

    @SystemApi
    public static final String ACTION_REQUEST_OMADM_CONFIGURATION_UPDATE = "com.android.omadm.service.CONFIGURATION_UPDATE";
    public static final String ACTION_RESPOND_VIA_MESSAGE = "android.intent.action.RESPOND_VIA_MESSAGE";
    public static final String ACTION_SECRET_CODE = "android.telephony.action.SECRET_CODE";
    public static final String ACTION_SERVICE_PROVIDERS_UPDATED = "android.telephony.action.SERVICE_PROVIDERS_UPDATED";

    @SystemApi
    public static final String ACTION_SHOW_NOTICE_ECM_BLOCK_OTHERS = "android.telephony.action.SHOW_NOTICE_ECM_BLOCK_OTHERS";
    public static final String ACTION_SHOW_VOICEMAIL_NOTIFICATION = "android.telephony.action.SHOW_VOICEMAIL_NOTIFICATION";

    @SystemApi
    public static final String ACTION_SIM_APPLICATION_STATE_CHANGED = "android.telephony.action.SIM_APPLICATION_STATE_CHANGED";

    @SystemApi
    public static final String ACTION_SIM_CARD_STATE_CHANGED = "android.telephony.action.SIM_CARD_STATE_CHANGED";

    @SystemApi
    public static final String ACTION_SIM_SLOT_STATUS_CHANGED = "android.telephony.action.SIM_SLOT_STATUS_CHANGED";
    public static final String ACTION_SUBSCRIPTION_CARRIER_IDENTITY_CHANGED = "android.telephony.action.SUBSCRIPTION_CARRIER_IDENTITY_CHANGED";
    public static final String ACTION_SUBSCRIPTION_SPECIFIC_CARRIER_IDENTITY_CHANGED = "android.telephony.action.SUBSCRIPTION_SPECIFIC_CARRIER_IDENTITY_CHANGED";
    public static final int ALLOWED_NETWORK_TYPES_REASON_POWER = 0;
    public static final int APPTYPE_CSIM = 4;
    public static final int APPTYPE_ISIM = 5;
    public static final int APPTYPE_RUIM = 3;
    public static final int APPTYPE_SIM = 1;
    public static final int APPTYPE_USIM = 2;
    public static final int AUTHTYPE_EAP_AKA = 129;
    public static final int AUTHTYPE_EAP_SIM = 128;
    private static final long CALLBACK_ON_MORE_ERROR_CODE_CHANGE = 130595455;
    public static final int CALL_STATE_IDLE = 0;
    public static final int CALL_STATE_OFFHOOK = 2;
    public static final int CALL_STATE_RINGING = 1;
    public static final int CALL_WAITING_STATUS_ACTIVE = 1;
    public static final int CALL_WAITING_STATUS_INACTIVE = 2;
    public static final int CALL_WAITING_STATUS_NOT_SUPPORTED = 4;
    public static final int CALL_WAITING_STATUS_UNKNOWN_ERROR = 3;
    public static final int CARD_POWER_DOWN = 0;
    public static final int CARD_POWER_UP = 1;
    public static final int CARD_POWER_UP_PASS_THROUGH = 2;

    @SystemApi
    public static final int CARRIER_PRIVILEGE_STATUS_ERROR_LOADING_RULES = -2;

    @SystemApi
    public static final int CARRIER_PRIVILEGE_STATUS_HAS_ACCESS = 1;

    @SystemApi
    public static final int CARRIER_PRIVILEGE_STATUS_NO_ACCESS = 0;

    @SystemApi
    public static final int CARRIER_PRIVILEGE_STATUS_RULES_NOT_LOADED = -1;
    public static final int CDMA_ROAMING_MODE_AFFILIATED = 1;
    public static final int CDMA_ROAMING_MODE_ANY = 2;
    public static final int CDMA_ROAMING_MODE_HOME = 0;
    public static final int CDMA_ROAMING_MODE_RADIO_DEFAULT = -1;
    public static final int CDMA_SUBSCRIPTION_NV = 1;
    public static final int CDMA_SUBSCRIPTION_RUIM_SIM = 0;
    public static final int CDMA_SUBSCRIPTION_UNKNOWN = -1;
    public static final int CHANGE_ICC_LOCK_SUCCESS = Integer.MAX_VALUE;
    public static final int DATA_ACTIVITY_DORMANT = 4;
    public static final int DATA_ACTIVITY_IN = 1;
    public static final int DATA_ACTIVITY_INOUT = 3;
    public static final int DATA_ACTIVITY_NONE = 0;
    public static final int DATA_ACTIVITY_OUT = 2;
    public static final int DATA_CONNECTED = 2;
    public static final int DATA_CONNECTING = 1;
    public static final int DATA_DISCONNECTED = 0;
    public static final int DATA_DISCONNECTING = 4;
    public static final int DATA_SUSPENDED = 3;
    public static final int DATA_UNKNOWN = -1;
    public static final boolean EMERGENCY_ASSISTANCE_ENABLED = true;
    public static final String EVENT_CALL_FORWARDED = "android.telephony.event.EVENT_CALL_FORWARDED";
    public static final String EVENT_DOWNGRADE_DATA_DISABLED = "android.telephony.event.EVENT_DOWNGRADE_DATA_DISABLED";
    public static final String EVENT_DOWNGRADE_DATA_LIMIT_REACHED = "android.telephony.event.EVENT_DOWNGRADE_DATA_LIMIT_REACHED";
    public static final String EVENT_HANDOVER_TO_WIFI_FAILED = "android.telephony.event.EVENT_HANDOVER_TO_WIFI_FAILED";
    public static final String EVENT_HANDOVER_VIDEO_FROM_LTE_TO_WIFI = "android.telephony.event.EVENT_HANDOVER_VIDEO_FROM_LTE_TO_WIFI";
    public static final String EVENT_HANDOVER_VIDEO_FROM_WIFI_TO_LTE = "android.telephony.event.EVENT_HANDOVER_VIDEO_FROM_WIFI_TO_LTE";
    public static final String EVENT_NOTIFY_INTERNATIONAL_CALL_ON_WFC = "android.telephony.event.EVENT_NOTIFY_INTERNATIONAL_CALL_ON_WFC";
    public static final String EVENT_SUPPLEMENTARY_SERVICE_NOTIFICATION = "android.telephony.event.EVENT_SUPPLEMENTARY_SERVICE_NOTIFICATION";
    public static final String EXTRA_ACTIVE_SIM_SUPPORTED_COUNT = "android.telephony.extra.ACTIVE_SIM_SUPPORTED_COUNT";

    @SystemApi
    public static final String EXTRA_ANOMALY_DESCRIPTION = "android.telephony.extra.ANOMALY_DESCRIPTION";

    @SystemApi
    public static final String EXTRA_ANOMALY_ID = "android.telephony.extra.ANOMALY_ID";

    @Deprecated
    public static final String EXTRA_APN_PROTOCOL = "apnProto";
    public static final String EXTRA_APN_PROTOCOL_INT = "apnProtoInt";

    @Deprecated
    public static final String EXTRA_APN_TYPE = "apnType";
    public static final String EXTRA_APN_TYPE_INT = "apnTypeInt";
    public static final String EXTRA_CALL_VOICEMAIL_INTENT = "android.telephony.extra.CALL_VOICEMAIL_INTENT";
    public static final String EXTRA_CARRIER_ID = "android.telephony.extra.CARRIER_ID";
    public static final String EXTRA_CARRIER_NAME = "android.telephony.extra.CARRIER_NAME";
    public static final String EXTRA_DATA_SPN = "android.telephony.extra.DATA_SPN";
    public static final String EXTRA_DEFAULT_NETWORK_AVAILABLE = "defaultNetworkAvailable";
    public static final String EXTRA_DEFAULT_SUBSCRIPTION_SELECT_TYPE = "android.telephony.extra.DEFAULT_SUBSCRIPTION_SELECT_TYPE";
    public static final int EXTRA_DEFAULT_SUBSCRIPTION_SELECT_TYPE_ALL = 4;
    public static final int EXTRA_DEFAULT_SUBSCRIPTION_SELECT_TYPE_DATA = 1;
    public static final int EXTRA_DEFAULT_SUBSCRIPTION_SELECT_TYPE_DISMISS = 5;
    public static final int EXTRA_DEFAULT_SUBSCRIPTION_SELECT_TYPE_NONE = 0;
    public static final int EXTRA_DEFAULT_SUBSCRIPTION_SELECT_TYPE_SMS = 3;
    public static final int EXTRA_DEFAULT_SUBSCRIPTION_SELECT_TYPE_VOICE = 2;

    @Deprecated
    public static final String EXTRA_DISCONNECT_CAUSE = "disconnect_cause";
    public static final String EXTRA_ERROR_CODE = "errorCode";
    public static final String EXTRA_HIDE_PUBLIC_SETTINGS = "android.telephony.extra.HIDE_PUBLIC_SETTINGS";

    @Deprecated
    public static final String EXTRA_INCOMING_NUMBER = "incoming_number";
    public static final String EXTRA_IS_REFRESH = "android.telephony.extra.IS_REFRESH";
    public static final String EXTRA_LAST_KNOWN_NETWORK_COUNTRY = "android.telephony.extra.LAST_KNOWN_NETWORK_COUNTRY";
    public static final String EXTRA_LAUNCH_VOICEMAIL_SETTINGS_INTENT = "android.telephony.extra.LAUNCH_VOICEMAIL_SETTINGS_INTENT";
    public static final String EXTRA_NETWORK_COUNTRY = "android.telephony.extra.NETWORK_COUNTRY";
    public static final String EXTRA_NOTIFICATION_CODE = "android.telephony.extra.NOTIFICATION_CODE";
    public static final String EXTRA_NOTIFICATION_COUNT = "android.telephony.extra.NOTIFICATION_COUNT";
    public static final String EXTRA_NOTIFICATION_MESSAGE = "android.telephony.extra.NOTIFICATION_MESSAGE";
    public static final String EXTRA_NOTIFICATION_TYPE = "android.telephony.extra.NOTIFICATION_TYPE";
    public static final String EXTRA_PCO_ID = "pcoId";
    public static final String EXTRA_PCO_VALUE = "pcoValue";
    public static final String EXTRA_PHONE_ACCOUNT_HANDLE = "android.telephony.extra.PHONE_ACCOUNT_HANDLE";

    @SystemApi
    public static final String EXTRA_PHONE_IN_ECM_STATE = "android.telephony.extra.PHONE_IN_ECM_STATE";

    @SystemApi
    public static final String EXTRA_PHONE_IN_EMERGENCY_CALL = "android.telephony.extra.PHONE_IN_EMERGENCY_CALL";
    public static final String EXTRA_PLMN = "android.telephony.extra.PLMN";
    public static final String EXTRA_PRECISE_DISCONNECT_CAUSE = "precise_disconnect_cause";
    public static final String EXTRA_RECOVERY_ACTION = "recoveryAction";
    public static final String EXTRA_REDIRECTION_URL = "redirectionUrl";
    public static final String EXTRA_SHOW_PLMN = "android.telephony.extra.SHOW_PLMN";
    public static final String EXTRA_SHOW_SPN = "android.telephony.extra.SHOW_SPN";
    public static final String EXTRA_SIM_COMBINATION_NAMES = "android.telephony.extra.SIM_COMBINATION_NAMES";
    public static final String EXTRA_SIM_COMBINATION_WARNING_TYPE = "android.telephony.extra.SIM_COMBINATION_WARNING_TYPE";
    public static final int EXTRA_SIM_COMBINATION_WARNING_TYPE_DUAL_CDMA = 1;
    public static final int EXTRA_SIM_COMBINATION_WARNING_TYPE_NONE = 0;

    @SystemApi
    public static final String EXTRA_SIM_STATE = "android.telephony.extra.SIM_STATE";
    public static final String EXTRA_SPECIFIC_CARRIER_ID = "android.telephony.extra.SPECIFIC_CARRIER_ID";
    public static final String EXTRA_SPECIFIC_CARRIER_NAME = "android.telephony.extra.SPECIFIC_CARRIER_NAME";
    public static final String EXTRA_SPN = "android.telephony.extra.SPN";
    public static final String EXTRA_STATE = "state";
    public static final String EXTRA_SUBSCRIPTION_ID = "android.telephony.extra.SUBSCRIPTION_ID";

    @SystemApi
    public static final String EXTRA_VISUAL_VOICEMAIL_ENABLED_BY_USER_BOOL = "android.telephony.extra.VISUAL_VOICEMAIL_ENABLED_BY_USER_BOOL";
    public static final String EXTRA_VOICEMAIL_NUMBER = "android.telephony.extra.VOICEMAIL_NUMBER";

    @SystemApi
    public static final String EXTRA_VOICEMAIL_SCRAMBLED_PIN_STRING = "android.telephony.extra.VOICEMAIL_SCRAMBLED_PIN_STRING";
    private static final long GET_DATA_STATE_R_VERSION = 148534348;
    public static final int INDICATION_FILTER_DATA_CALL_DORMANCY_CHANGED = 4;
    public static final int INDICATION_FILTER_FULL_NETWORK_STATE = 2;
    public static final int INDICATION_FILTER_LINK_CAPACITY_ESTIMATE = 8;
    public static final int INDICATION_FILTER_PHYSICAL_CHANNEL_CONFIG = 16;
    public static final int INDICATION_FILTER_SIGNAL_STRENGTH = 1;
    public static final int INDICATION_UPDATE_MODE_IGNORE_SCREEN_OFF = 2;
    public static final int INDICATION_UPDATE_MODE_NORMAL = 1;

    @SystemApi
    public static final int INVALID_EMERGENCY_NUMBER_DB_VERSION = -1;

    @SystemApi
    public static final int KEY_TYPE_EPDG = 1;

    @SystemApi
    public static final int KEY_TYPE_WLAN = 2;
    private static final long MAX_NUMBER_VERIFICATION_TIMEOUT_MILLIS = 60000;
    public static final String METADATA_HIDE_VOICEMAIL_SETTINGS_MENU = "android.telephony.HIDE_VOICEMAIL_SETTINGS_MENU";
    public static final String MODEM_ACTIVITY_RESULT_KEY = "controller_activity";
    public static final int MULTISIM_ALLOWED = 0;
    public static final int MULTISIM_NOT_SUPPORTED_BY_CARRIER = 2;
    public static final int MULTISIM_NOT_SUPPORTED_BY_HARDWARE = 1;
    public static final long NETWORK_CLASS_BITMASK_2G = 32843;
    public static final long NETWORK_CLASS_BITMASK_3G = 93108;
    public static final long NETWORK_CLASS_BITMASK_4G = 397312;
    public static final long NETWORK_CLASS_BITMASK_5G = 524288;
    public static final int NETWORK_MODE_CDMA_EVDO = 4;
    public static final int NETWORK_MODE_CDMA_NO_EVDO = 5;
    public static final int NETWORK_MODE_EVDO_NO_CDMA = 6;
    public static final int NETWORK_MODE_GLOBAL = 7;
    public static final int NETWORK_MODE_GSM_ONLY = 1;
    public static final int NETWORK_MODE_GSM_UMTS = 3;
    public static final int NETWORK_MODE_LTE_CDMA_EVDO = 8;
    public static final int NETWORK_MODE_LTE_CDMA_EVDO_GSM_WCDMA = 10;
    public static final int NETWORK_MODE_LTE_GSM_WCDMA = 9;
    public static final int NETWORK_MODE_LTE_ONLY = 11;
    public static final int NETWORK_MODE_LTE_TDSCDMA = 15;
    public static final int NETWORK_MODE_LTE_TDSCDMA_CDMA_EVDO_GSM_WCDMA = 22;
    public static final int NETWORK_MODE_LTE_TDSCDMA_GSM = 17;
    public static final int NETWORK_MODE_LTE_TDSCDMA_GSM_WCDMA = 20;
    public static final int NETWORK_MODE_LTE_TDSCDMA_WCDMA = 19;
    public static final int NETWORK_MODE_LTE_WCDMA = 12;
    public static final int NETWORK_MODE_NR_LTE = 24;
    public static final int NETWORK_MODE_NR_LTE_CDMA_EVDO = 25;
    public static final int NETWORK_MODE_NR_LTE_CDMA_EVDO_GSM_WCDMA = 27;
    public static final int NETWORK_MODE_NR_LTE_GSM_WCDMA = 26;
    public static final int NETWORK_MODE_NR_LTE_TDSCDMA = 29;
    public static final int NETWORK_MODE_NR_LTE_TDSCDMA_CDMA_EVDO_GSM_WCDMA = 33;
    public static final int NETWORK_MODE_NR_LTE_TDSCDMA_GSM = 30;
    public static final int NETWORK_MODE_NR_LTE_TDSCDMA_GSM_WCDMA = 32;
    public static final int NETWORK_MODE_NR_LTE_TDSCDMA_WCDMA = 31;
    public static final int NETWORK_MODE_NR_LTE_WCDMA = 28;
    public static final int NETWORK_MODE_NR_ONLY = 23;
    public static final int NETWORK_MODE_TDSCDMA_CDMA_EVDO_GSM_WCDMA = 21;
    public static final int NETWORK_MODE_TDSCDMA_GSM = 16;
    public static final int NETWORK_MODE_TDSCDMA_GSM_WCDMA = 18;
    public static final int NETWORK_MODE_TDSCDMA_ONLY = 13;
    public static final int NETWORK_MODE_TDSCDMA_WCDMA = 14;
    public static final int NETWORK_MODE_WCDMA_ONLY = 2;
    public static final int NETWORK_MODE_WCDMA_PREF = 0;
    public static final int NETWORK_SELECTION_MODE_AUTO = 1;
    public static final int NETWORK_SELECTION_MODE_MANUAL = 2;
    public static final int NETWORK_SELECTION_MODE_UNKNOWN = 0;
    public static final long NETWORK_STANDARDS_FAMILY_BITMASK_3GPP = 906119;
    public static final long NETWORK_STANDARDS_FAMILY_BITMASK_3GPP2 = 10360;
    public static final int NETWORK_TYPE_1xRTT = 7;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_1xRTT = 64;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_CDMA = 8;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_EDGE = 2;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_EHRPD = 8192;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_EVDO_0 = 16;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_EVDO_A = 32;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_EVDO_B = 2048;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_GPRS = 1;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_GSM = 32768;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_HSDPA = 128;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_HSPA = 512;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_HSPAP = 16384;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_HSUPA = 256;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_IWLAN = 131072;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_LTE = 4096;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_LTE_CA = 262144;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_NR = 524288;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_TD_SCDMA = 65536;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_UMTS = 4;

    @SystemApi
    public static final long NETWORK_TYPE_BITMASK_UNKNOWN = 0;
    public static final int NETWORK_TYPE_CDMA = 4;
    public static final int NETWORK_TYPE_EDGE = 2;
    public static final int NETWORK_TYPE_EHRPD = 14;
    public static final int NETWORK_TYPE_EVDO_0 = 5;
    public static final int NETWORK_TYPE_EVDO_A = 6;
    public static final int NETWORK_TYPE_EVDO_B = 12;
    public static final int NETWORK_TYPE_GPRS = 1;
    public static final int NETWORK_TYPE_GSM = 16;
    public static final int NETWORK_TYPE_HSDPA = 8;
    public static final int NETWORK_TYPE_HSPA = 10;
    public static final int NETWORK_TYPE_HSPAP = 15;
    public static final int NETWORK_TYPE_HSUPA = 9;
    public static final int NETWORK_TYPE_IDEN = 11;
    public static final int NETWORK_TYPE_IWLAN = 18;
    public static final int NETWORK_TYPE_LTE = 13;
    public static final int NETWORK_TYPE_LTE_CA = 19;
    public static final int NETWORK_TYPE_NR = 20;
    public static final int NETWORK_TYPE_TD_SCDMA = 17;
    public static final int NETWORK_TYPE_UMTS = 3;
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    public static final int OTASP_NEEDED = 2;
    public static final int OTASP_NOT_NEEDED = 3;
    public static final int OTASP_SIM_UNPROVISIONED = 5;
    public static final int OTASP_UNINITIALIZED = 0;
    public static final int OTASP_UNKNOWN = 1;
    public static final String PHONE_PROCESS_NAME = "com.android.phone";
    public static final int PHONE_TYPE_CDMA = 2;
    public static final int PHONE_TYPE_GSM = 1;
    public static final int PHONE_TYPE_IMS = 5;
    public static final int PHONE_TYPE_NONE = 0;
    public static final int PHONE_TYPE_SIP = 3;
    public static final int PHONE_TYPE_THIRD_PARTY = 4;
    public static final int PREFERRED_CDMA_SUBSCRIPTION = 0;

    @SystemApi
    public static final int RADIO_POWER_OFF = 0;

    @SystemApi
    public static final int RADIO_POWER_ON = 1;

    @SystemApi
    public static final int RADIO_POWER_UNAVAILABLE = 2;

    @SystemApi
    public static final int SET_CARRIER_RESTRICTION_ERROR = 2;

    @SystemApi
    public static final int SET_CARRIER_RESTRICTION_NOT_SUPPORTED = 1;

    @SystemApi
    public static final int SET_CARRIER_RESTRICTION_SUCCESS = 0;
    public static final int SET_OPPORTUNISTIC_SUB_INACTIVE_SUBSCRIPTION = 2;
    public static final int SET_OPPORTUNISTIC_SUB_NO_OPPORTUNISTIC_SUB_AVAILABLE = 3;
    public static final int SET_OPPORTUNISTIC_SUB_REMOTE_SERVICE_EXCEPTION = 4;
    public static final int SET_OPPORTUNISTIC_SUB_SUCCESS = 0;
    public static final int SET_OPPORTUNISTIC_SUB_VALIDATION_FAILED = 1;

    @SystemApi
    public static final int SIM_ACTIVATION_STATE_ACTIVATED = 2;

    @SystemApi
    public static final int SIM_ACTIVATION_STATE_ACTIVATING = 1;

    @SystemApi
    public static final int SIM_ACTIVATION_STATE_DEACTIVATED = 3;

    @SystemApi
    public static final int SIM_ACTIVATION_STATE_RESTRICTED = 4;

    @SystemApi
    public static final int SIM_ACTIVATION_STATE_UNKNOWN = 0;
    public static final int SIM_STATE_ABSENT = 1;
    public static final int SIM_STATE_CARD_IO_ERROR = 8;
    public static final int SIM_STATE_CARD_RESTRICTED = 9;

    @SystemApi
    public static final int SIM_STATE_LOADED = 10;
    public static final int SIM_STATE_NETWORK_LOCKED = 4;
    public static final int SIM_STATE_NOT_READY = 6;
    public static final int SIM_STATE_PERM_DISABLED = 7;
    public static final int SIM_STATE_PIN_REQUIRED = 2;

    @SystemApi
    public static final int SIM_STATE_PRESENT = 11;
    public static final int SIM_STATE_PUK_REQUIRED = 3;
    public static final int SIM_STATE_READY = 5;
    public static final int SIM_STATE_UNKNOWN = 0;

    @SystemApi
    public static final int SRVCC_STATE_HANDOVER_CANCELED = 3;

    @SystemApi
    public static final int SRVCC_STATE_HANDOVER_COMPLETED = 1;

    @SystemApi
    public static final int SRVCC_STATE_HANDOVER_FAILED = 2;

    @SystemApi
    public static final int SRVCC_STATE_HANDOVER_NONE = -1;

    @SystemApi
    public static final int SRVCC_STATE_HANDOVER_STARTED = 0;
    private static final String TAG = "TelephonyManager";
    public static final int UNINITIALIZED_CARD_ID = -2;
    public static final int UNKNOWN_CARRIER_ID = -1;
    public static final int UNKNOWN_CARRIER_ID_LIST_VERSION = -1;
    public static final int UNSUPPORTED_CARD_ID = -1;
    public static final int UPDATE_AVAILABLE_NETWORKS_ABORTED = 2;
    public static final int UPDATE_AVAILABLE_NETWORKS_DISABLE_MODEM_FAIL = 5;
    public static final int UPDATE_AVAILABLE_NETWORKS_ENABLE_MODEM_FAIL = 6;
    public static final int UPDATE_AVAILABLE_NETWORKS_INVALID_ARGUMENTS = 3;
    public static final int UPDATE_AVAILABLE_NETWORKS_MULTIPLE_NETWORKS_NOT_SUPPORTED = 7;
    public static final int UPDATE_AVAILABLE_NETWORKS_NO_CARRIER_PRIVILEGE = 4;
    public static final int UPDATE_AVAILABLE_NETWORKS_NO_OPPORTUNISTIC_SUB_AVAILABLE = 8;
    public static final int UPDATE_AVAILABLE_NETWORKS_REMOTE_SERVICE_EXCEPTION = 9;
    public static final int UPDATE_AVAILABLE_NETWORKS_SERVICE_IS_DISABLED = 10;
    public static final int UPDATE_AVAILABLE_NETWORKS_SUCCESS = 0;
    public static final int UPDATE_AVAILABLE_NETWORKS_UNKNOWN_FAILURE = 1;
    public static final int USSD_ERROR_SERVICE_UNAVAIL = -2;
    public static final String USSD_RESPONSE = "USSD_RESPONSE";
    public static final int USSD_RETURN_FAILURE = -1;
    public static final int USSD_RETURN_SUCCESS = 100;
    public static final String VVM_TYPE_CVVM = "vvm_type_cvvm";
    public static final String VVM_TYPE_OMTP = "vvm_type_omtp";
    private static IPhoneSubInfo sIPhoneSubInfo = null;
    private static ISms sISms = null;
    private static ISub sISub = null;
    private static boolean sServiceHandleCacheEnabled = true;
    private final Context mContext;
    private final int mSubId;
    private SubscriptionManager mSubscriptionManager;
    private TelephonyScanManager mTelephonyScanManager;
    private static final Object sCacheLock = new Object();
    private static final DeathRecipient sServiceDeath = new DeathRecipient(null);
    private static TelephonyManager sInstance = new TelephonyManager();
    public static final String EXTRA_STATE_IDLE = PhoneConstants.State.IDLE.toString();
    public static final String EXTRA_STATE_RINGING = PhoneConstants.State.RINGING.toString();
    public static final String EXTRA_STATE_OFFHOOK = PhoneConstants.State.OFFHOOK.toString();
    private static final String sKernelCmdLine = getProcCmdLine();
    private static final Pattern sProductTypePattern = Pattern.compile("\\sproduct_type\\s*=\\s*(\\w+)");
    private static final String sLteOnCdmaProductType = TelephonyProperties.lte_on_cdma_product_type().orElse("");
    private static final int[] NETWORK_TYPES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    public static final int DEFAULT_PREFERRED_NETWORK_MODE = RILConstants.PREFERRED_NETWORK_MODE;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AllowedNetworkTypesReason {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CallWaitingStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CdmaRoamingMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface CdmaSubscription {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DataState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface DefaultSubscriptionSelectType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface IsMultiSimSupportedResult {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface KeyType {
    }

    public enum MultiSimVariants {
        DSDS,
        DSDA,
        TSTS,
        UNKNOWN
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NetworkSelectionMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NetworkTypeBitMask {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PrefNetworkMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SetCarrierRestrictionResult {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SetOpportunisticSubscriptionResult {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SimCombinationWarningType {
    }

    public @interface SimState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface UpdateAvailableNetworksResult {
    }

    public static abstract class UssdResponseCallback {
        public void onReceiveUssdResponse(TelephonyManager telephonyManager, String str, CharSequence charSequence) {
        }

        public void onReceiveUssdResponseFailed(TelephonyManager telephonyManager, String str, int i) {
        }
    }

    public interface WifiCallingChoices {
        public static final int ALWAYS_USE = 0;
        public static final int ASK_EVERY_TIME = 1;
        public static final int NEVER_USE = 2;
    }

    public static long getAllNetworkTypesBitmask() {
        return 916479L;
    }

    public static long getBitMaskForNetworkType(int i) {
        switch (i) {
            case 1:
                return 1L;
            case 2:
                return 2L;
            case 3:
                return 4L;
            case 4:
                return 8L;
            case 5:
                return 16L;
            case 6:
                return 32L;
            case 7:
                return 64L;
            case 8:
                return 128L;
            case 9:
                return 256L;
            case 10:
                return 512L;
            case 11:
            case 18:
            default:
                return 0L;
            case 12:
                return 2048L;
            case 13:
                return 4096L;
            case 14:
                return 8192L;
            case 15:
                return 16384L;
            case 16:
                return 32768L;
            case 17:
                return 65536L;
            case 19:
                return 262144L;
            case 20:
                return 524288L;
        }
    }

    @SystemApi
    public static long getMaxNumberVerificationTimeoutMillis() {
        return 60000L;
    }

    public static String getNetworkTypeName(int i) {
        switch (i) {
            case 1:
                return "GPRS";
            case 2:
                return "EDGE";
            case 3:
                return "UMTS";
            case 4:
                return "CDMA";
            case 5:
                return "CDMA - EvDo rev. 0";
            case 6:
                return "CDMA - EvDo rev. A";
            case 7:
                return "CDMA - 1xRTT";
            case 8:
                return "HSDPA";
            case 9:
                return "HSUPA";
            case 10:
                return "HSPA";
            case 11:
                return "iDEN";
            case 12:
                return "CDMA - EvDo rev. B";
            case 13:
                return DctConstants.RAT_NAME_LTE;
            case 14:
                return "CDMA - eHRPD";
            case 15:
                return "HSPA+";
            case 16:
                return "GSM";
            case 17:
                return "TD_SCDMA";
            case 18:
                return "IWLAN";
            case 19:
                return "LTE_CA";
            case 20:
                return "NR";
            default:
                return "UNKNOWN";
        }
    }

    private int getSimApplicationStateFromSimState(int i) {
        if (i == 0 || i == 1) {
            return 0;
        }
        if (i == 5) {
            return 6;
        }
        if (i == 8 || i == 9) {
            return 0;
        }
        return i;
    }

    private int getSimCardStateFromSimState(int i) {
        if (i == 0 || i == 1 || i == 8 || i == 9) {
            return i;
        }
        return 11;
    }

    private static boolean isKeyEnabled(int i, int i2) {
        return ((i >> (i2 - 1)) & 1) == 1;
    }

    @SystemApi
    @Deprecated
    public void answerRingingCall() {
    }

    @SystemApi
    @Deprecated
    public boolean endCall() {
        return false;
    }

    @SystemApi
    public boolean isVisualVoicemailEnabled(PhoneAccountHandle phoneAccountHandle) {
        return false;
    }

    @SystemApi
    public void setVisualVoicemailEnabled(PhoneAccountHandle phoneAccountHandle, boolean z) {
    }

    @SystemApi
    @Deprecated
    public void silenceRinger() {
    }

    public TelephonyManager(Context context) {
        this(context, Integer.MAX_VALUE);
    }

    public TelephonyManager(Context context, int i) {
        this.mSubId = i;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            if (Objects.equals(context.getAttributionTag(), applicationContext.getAttributionTag())) {
                this.mContext = applicationContext;
            } else {
                this.mContext = applicationContext.createAttributionContext(context.getAttributionTag());
            }
        } else {
            this.mContext = context;
        }
        this.mSubscriptionManager = SubscriptionManager.from(this.mContext);
    }

    private TelephonyManager() {
        this.mContext = null;
        this.mSubId = -1;
    }

    @Deprecated
    public static TelephonyManager getDefault() {
        return sInstance;
    }

    private String getOpPackageName() {
        Context context = this.mContext;
        if (context != null) {
            return context.getOpPackageName();
        }
        ITelephony iTelephony = getITelephony();
        if (iTelephony == null) {
            return null;
        }
        try {
            return iTelephony.getCurrentPackageName();
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    private String getAttributionTag() {
        Context context = this.mContext;
        if (context != null) {
            return context.getAttributionTag();
        }
        return null;
    }

    private boolean isSystemProcess() {
        return Process.myUid() == 1000;
    }

    public MultiSimVariants getMultiSimConfiguration() {
        String strOrElse = TelephonyProperties.multi_sim_config().orElse("");
        if (strOrElse.equals("dsds")) {
            return MultiSimVariants.DSDS;
        }
        if (strOrElse.equals("dsda")) {
            return MultiSimVariants.DSDA;
        }
        if (strOrElse.equals("tsts")) {
            return MultiSimVariants.TSTS;
        }
        return MultiSimVariants.UNKNOWN;
    }

    @Deprecated
    public int getPhoneCount() {
        return getActiveModemCount();
    }

    /* JADX INFO: renamed from: android.telephony.TelephonyManager$8, reason: invalid class name */
    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$android$telephony$TelephonyManager$MultiSimVariants;

        static {
            int[] iArr = new int[MultiSimVariants.values().length];
            $SwitchMap$android$telephony$TelephonyManager$MultiSimVariants = iArr;
            try {
                iArr[MultiSimVariants.UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$telephony$TelephonyManager$MultiSimVariants[MultiSimVariants.DSDS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$telephony$TelephonyManager$MultiSimVariants[MultiSimVariants.DSDA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$telephony$TelephonyManager$MultiSimVariants[MultiSimVariants.TSTS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public int getActiveModemCount() {
        int i = AnonymousClass8.$SwitchMap$android$telephony$TelephonyManager$MultiSimVariants[getMultiSimConfiguration().ordinal()];
        if (i != 1) {
            if (i == 2 || i == 3) {
                return 2;
            }
            if (i == 4) {
                return 3;
            }
        } else if (!isVoiceCapable() && !isSmsCapable() && !isDataCapable()) {
            return 0;
        }
        return 1;
    }

    public int getSupportedModemCount() {
        return TelephonyProperties.max_active_modems().orElse(Integer.valueOf(getActiveModemCount())).intValue();
    }

    @SystemApi
    public int getMaxNumberOfSimultaneouslyActiveSims() {
        return AnonymousClass8.$SwitchMap$android$telephony$TelephonyManager$MultiSimVariants[getMultiSimConfiguration().ordinal()] != 3 ? 1 : 2;
    }

    public static TelephonyManager from(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    public TelephonyManager createForSubscriptionId(int i) {
        return new TelephonyManager(this.mContext, i);
    }

    public TelephonyManager createForPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle) {
        int subscriptionId = getSubscriptionId(phoneAccountHandle);
        if (SubscriptionManager.isValidSubscriptionId(subscriptionId)) {
            return new TelephonyManager(this.mContext, subscriptionId);
        }
        return null;
    }

    public boolean isMultiSimEnabled() {
        return getPhoneCount() > 1;
    }

    public String getDeviceSoftwareVersion() {
        return getDeviceSoftwareVersion(getSlotIndex());
    }

    @SystemApi
    public String getDeviceSoftwareVersion(int i) {
        ITelephony iTelephony = getITelephony();
        if (iTelephony == null) {
            return null;
        }
        try {
            return iTelephony.getDeviceSoftwareVersionForSlot(i, getOpPackageName(), getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    @Deprecated
    public String getDeviceId() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return null;
            }
            return iTelephony.getDeviceIdWithFeature(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    @Deprecated
    public String getDeviceId(int i) {
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                return null;
            }
            return subscriberInfoService.getDeviceIdForPhone(i, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public String getImei() {
        return getImei(getSlotIndex());
    }

    public String getImei(int i) {
        ITelephony iTelephony = getITelephony();
        if (iTelephony == null) {
            return null;
        }
        try {
            return iTelephony.getImeiForSlot(i, getOpPackageName(), getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public String getTypeAllocationCode() {
        return getTypeAllocationCode(getSlotIndex());
    }

    public String getTypeAllocationCode(int i) {
        ITelephony iTelephony = getITelephony();
        if (iTelephony == null) {
            return null;
        }
        try {
            return iTelephony.getTypeAllocationCodeForSlot(i);
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public String getMeid() {
        return getMeid(getSlotIndex());
    }

    public String getMeid(int i) {
        ITelephony iTelephony = getITelephony();
        if (iTelephony == null) {
            return null;
        }
        try {
            String meidForSlot = iTelephony.getMeidForSlot(i, getOpPackageName(), getAttributionTag());
            if (!TextUtils.isEmpty(meidForSlot)) {
                return meidForSlot;
            }
            Log.d(TAG, "getMeid: return null because MEID is not available");
            return null;
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public String getManufacturerCode() {
        return getManufacturerCode(getSlotIndex());
    }

    public String getManufacturerCode(int i) {
        ITelephony iTelephony = getITelephony();
        if (iTelephony == null) {
            return null;
        }
        try {
            return iTelephony.getManufacturerCodeForSlot(i);
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public String getNai() {
        return getNaiBySubscriberId(getSubId());
    }

    public String getNai(int i) {
        int[] subId = SubscriptionManager.getSubId(i);
        if (subId == null) {
            return null;
        }
        return getNaiBySubscriberId(subId[0]);
    }

    private String getNaiBySubscriberId(int i) {
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                return null;
            }
            String naiForSubscriber = subscriberInfoService.getNaiForSubscriber(i, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            if (Log.isLoggable(TAG, 2)) {
                com.android.telephony.Rlog.v(TAG, "Nai = " + naiForSubscriber);
            }
            return naiForSubscriber;
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    @Deprecated
    public CellLocation getCellLocation() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                com.android.telephony.Rlog.d(TAG, "getCellLocation returning null because telephony is null");
                return null;
            }
            CellLocation cellLocationAsCellLocation = iTelephony.getCellLocation(this.mContext.getOpPackageName(), this.mContext.getAttributionTag()).asCellLocation();
            if (cellLocationAsCellLocation != null && !cellLocationAsCellLocation.isEmpty()) {
                return cellLocationAsCellLocation;
            }
            com.android.telephony.Rlog.d(TAG, "getCellLocation returning null because CellLocation is empty or phone type doesn't match CellLocation type");
            return null;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.d(TAG, "getCellLocation returning null due to RemoteException " + e);
            return null;
        }
    }

    public void enableLocationUpdates() {
        enableLocationUpdates(getSubId());
    }

    public void enableLocationUpdates(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.enableLocationUpdatesForSubscriber(i);
            }
        } catch (RemoteException | NullPointerException unused) {
        }
    }

    public void disableLocationUpdates() {
        disableLocationUpdates(getSubId());
    }

    public void disableLocationUpdates(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.disableLocationUpdatesForSubscriber(i);
            }
        } catch (RemoteException | NullPointerException unused) {
        }
    }

    @Deprecated
    public List<NeighboringCellInfo> getNeighboringCellInfo() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return null;
            }
            return iTelephony.getNeighboringCellInfo(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    @SystemApi
    public int getCurrentPhoneType() {
        return getCurrentPhoneType(getSubId());
    }

    @SystemApi
    public int getCurrentPhoneType(int i) {
        return getCurrentPhoneTypeForSlot(i == -1 ? 0 : SubscriptionManager.getPhoneId(i));
    }

    public int getCurrentPhoneTypeForSlot(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getActivePhoneTypeForSlot(i);
            }
            return getPhoneTypeFromProperty(i);
        } catch (RemoteException unused) {
            return getPhoneTypeFromProperty(i);
        } catch (NullPointerException unused2) {
            return getPhoneTypeFromProperty(i);
        }
    }

    public int getPhoneType() {
        if (isVoiceCapable()) {
            return getCurrentPhoneType();
        }
        return 0;
    }

    private int getPhoneTypeFromProperty() {
        return getPhoneTypeFromProperty(getPhoneId());
    }

    private int getPhoneTypeFromProperty(int i) {
        Integer num = (Integer) getTelephonyProperty(i, TelephonyProperties.current_active_phone(), (Object) null);
        if (num != null) {
            return num.intValue();
        }
        return getPhoneTypeFromNetworkType(i);
    }

    private int getPhoneTypeFromNetworkType() {
        return getPhoneTypeFromNetworkType(getPhoneId());
    }

    private int getPhoneTypeFromNetworkType(int i) {
        Integer num = (Integer) getTelephonyProperty(i, TelephonyProperties.default_network(), (Object) null);
        if (num != null) {
            return getPhoneType(num.intValue());
        }
        return 0;
    }

    public static int getPhoneType(int i) {
        if (i == 11) {
            return getLteOnCdmaModeStatic() == 1 ? 2 : 1;
        }
        if (i != 21) {
            switch (i) {
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    break;
                default:
                    return 1;
            }
        }
        return 2;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:0|2|36|3|4|(3:29|5|(2:7|8))|31|9|20|21|(1:(0))) */
    /* JADX WARN: Removed duplicated region for block: B:34:0x005d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String getProcCmdLine() throws java.lang.Throwable {
        /*
            java.lang.String r0 = "TelephonyManager"
            java.lang.String r1 = ""
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L23 java.io.IOException -> L25
            java.lang.String r4 = "/proc/cmdline"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L23 java.io.IOException -> L25
            r2 = 2048(0x800, float:2.87E-42)
            byte[] r2 = new byte[r2]     // Catch: java.io.IOException -> L21 java.lang.Throwable -> L59
            int r4 = r3.read(r2)     // Catch: java.io.IOException -> L21 java.lang.Throwable -> L59
            if (r4 <= 0) goto L1d
            java.lang.String r5 = new java.lang.String     // Catch: java.io.IOException -> L21 java.lang.Throwable -> L59
            r6 = 0
            r5.<init>(r2, r6, r4)     // Catch: java.io.IOException -> L21 java.lang.Throwable -> L59
            r1 = r5
        L1d:
            r3.close()     // Catch: java.io.IOException -> L42
            goto L42
        L21:
            r2 = move-exception
            goto L29
        L23:
            r0 = move-exception
            goto L5b
        L25:
            r3 = move-exception
            r7 = r3
            r3 = r2
            r2 = r7
        L29:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L59
            r4.<init>()     // Catch: java.lang.Throwable -> L59
            java.lang.String r5 = "No /proc/cmdline exception="
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch: java.lang.Throwable -> L59
            java.lang.StringBuilder r2 = r4.append(r2)     // Catch: java.lang.Throwable -> L59
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L59
            com.android.telephony.Rlog.d(r0, r2)     // Catch: java.lang.Throwable -> L59
            if (r3 == 0) goto L42
            goto L1d
        L42:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "/proc/cmdline="
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r1)
            java.lang.String r2 = r2.toString()
            com.android.telephony.Rlog.d(r0, r2)
            return r1
        L59:
            r0 = move-exception
            r2 = r3
        L5b:
            if (r2 == 0) goto L60
            r2.close()     // Catch: java.io.IOException -> L60
        L60:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.telephony.TelephonyManager.getProcCmdLine():java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0034 A[PHI: r4
  0x0034: PHI (r4v2 java.lang.String) = (r4v0 java.lang.String), (r4v3 java.lang.String) binds: [B:5:0x0025, B:7:0x0031] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getLteOnCdmaModeStatic() {
        /*
            java.util.Optional r0 = android.sysprop.TelephonyProperties.lte_on_cdma_device()
            r1 = -1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)
            java.lang.Object r0 = r0.orElse(r2)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            r2 = 1
            r3 = 0
            java.lang.String r4 = ""
            if (r0 != r1) goto L36
            java.util.regex.Pattern r1 = android.telephony.TelephonyManager.sProductTypePattern
            java.lang.String r5 = android.telephony.TelephonyManager.sKernelCmdLine
            java.util.regex.Matcher r1 = r1.matcher(r5)
            boolean r5 = r1.find()
            if (r5 == 0) goto L34
            java.lang.String r4 = r1.group(r2)
            java.lang.String r1 = android.telephony.TelephonyManager.sLteOnCdmaProductType
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L34
            goto L37
        L34:
            r2 = r3
            goto L37
        L36:
            r2 = r0
        L37:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "getLteOnCdmaMode="
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r3 = " curVal="
            java.lang.StringBuilder r1 = r1.append(r3)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r1 = " product_type='"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.StringBuilder r0 = r0.append(r4)
            java.lang.String r1 = "' lteOnCdmaProductType='"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = android.telephony.TelephonyManager.sLteOnCdmaProductType
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = "'"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "TelephonyManager"
            com.android.telephony.Rlog.d(r1, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.telephony.TelephonyManager.getLteOnCdmaModeStatic():int");
    }

    public String getNetworkOperatorName() {
        return getNetworkOperatorName(getSubId());
    }

    public String getNetworkOperatorName(int i) {
        return (String) getTelephonyProperty(SubscriptionManager.getPhoneId(i), TelephonyProperties.operator_alpha(), "");
    }

    public String getNetworkOperator() {
        return getNetworkOperatorForPhone(getPhoneId());
    }

    public String getNetworkOperator(int i) {
        return getNetworkOperatorForPhone(SubscriptionManager.getPhoneId(i));
    }

    public String getNetworkOperatorForPhone(int i) {
        return (String) getTelephonyProperty(i, TelephonyProperties.operator_numeric(), "");
    }

    public String getNetworkSpecifier() {
        return String.valueOf(getSubId());
    }

    public PersistableBundle getCarrierConfig() {
        return ((CarrierConfigManager) this.mContext.getSystemService(CarrierConfigManager.class)).getConfigForSubId(getSubId());
    }

    public boolean isNetworkRoaming() {
        return isNetworkRoaming(getSubId());
    }

    public boolean isNetworkRoaming(int i) {
        return ((Boolean) getTelephonyProperty(SubscriptionManager.getPhoneId(i), (List<boolean>) TelephonyProperties.operator_is_roaming(), false)).booleanValue();
    }

    public String getNetworkCountryIso() {
        return getNetworkCountryIso(getSlotIndex());
    }

    public String getNetworkCountryIso(int i) {
        if (i != Integer.MAX_VALUE) {
            try {
                if (!SubscriptionManager.isValidSlotIndex(i)) {
                    throw new IllegalArgumentException("invalid slot index " + i);
                }
            } catch (RemoteException unused) {
                return "";
            }
        }
        ITelephony iTelephony = getITelephony();
        return iTelephony == null ? "" : iTelephony.getNetworkCountryIsoForPhone(i);
    }

    @Deprecated
    public String getNetworkCountryIsoForPhone(int i) {
        return getNetworkCountryIso(i);
    }

    public static int[] getAllNetworkTypes() {
        return NETWORK_TYPES;
    }

    @Deprecated
    public int getNetworkType() {
        return getNetworkType(getSubId(SubscriptionManager.getActiveDataSubscriptionId()));
    }

    public int getNetworkType(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getNetworkTypeForSubscriber(i, getOpPackageName(), getAttributionTag());
            }
        } catch (RemoteException | NullPointerException unused) {
        }
        return 0;
    }

    public int getDataNetworkType() {
        return getDataNetworkType(getSubId(SubscriptionManager.getActiveDataSubscriptionId()));
    }

    public int getDataNetworkType(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getDataNetworkTypeForSubscriber(i, getOpPackageName(), getAttributionTag());
            }
        } catch (RemoteException | NullPointerException unused) {
        }
        return 0;
    }

    public int getVoiceNetworkType() {
        return getVoiceNetworkType(getSubId());
    }

    public int getVoiceNetworkType(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getVoiceNetworkTypeForSubscriber(i, getOpPackageName(), getAttributionTag());
            }
        } catch (RemoteException | NullPointerException unused) {
        }
        return 0;
    }

    public String getNetworkTypeName() {
        return getNetworkTypeName(getNetworkType());
    }

    public boolean hasIccCard() {
        return hasIccCard(getSlotIndex());
    }

    public boolean hasIccCard(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return false;
            }
            return iTelephony.hasIccCardUsingSlotIndex(i);
        } catch (RemoteException | NullPointerException unused) {
            return false;
        }
    }

    public int getSimState() {
        int simStateIncludingLoaded = getSimStateIncludingLoaded();
        if (simStateIncludingLoaded == 10) {
            return 5;
        }
        return simStateIncludingLoaded;
    }

    private int getSimStateIncludingLoaded() {
        int slotIndex = getSlotIndex();
        if (slotIndex < 0) {
            for (int i = 0; i < getPhoneCount(); i++) {
                int simState = getSimState(i);
                if (simState != 1) {
                    com.android.telephony.Rlog.d(TAG, "getSimState: default sim:" + slotIndex + ", sim state for slotIndex=" + i + " is " + simState + ", return state as unknown");
                    return 0;
                }
            }
            com.android.telephony.Rlog.d(TAG, "getSimState: default sim:" + slotIndex + ", all SIMs absent, return state as absent");
            return 1;
        }
        return SubscriptionManager.getSimStateForSlotIndex(slotIndex);
    }

    @SystemApi
    public int getSimCardState() {
        return getSimCardStateFromSimState(getSimState());
    }

    @SystemApi
    public int getSimCardState(int i) {
        return getSimCardStateFromSimState(getSimState(getLogicalSlotIndex(i)));
    }

    private int getLogicalSlotIndex(int i) {
        UiccSlotInfo[] uiccSlotsInfo = getUiccSlotsInfo();
        if (uiccSlotsInfo == null || i < 0 || i >= uiccSlotsInfo.length) {
            return -1;
        }
        return uiccSlotsInfo[i].getLogicalSlotIdx();
    }

    @SystemApi
    public int getSimApplicationState() {
        return getSimApplicationStateFromSimState(getSimStateIncludingLoaded());
    }

    @SystemApi
    public int getSimApplicationState(int i) {
        return getSimApplicationStateFromSimState(SubscriptionManager.getSimStateForSlotIndex(getLogicalSlotIndex(i)));
    }

    @SystemApi
    public boolean isApplicationOnUicc(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isApplicationOnUicc(getSubId(), i);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isApplicationOnUicc", e);
            return false;
        }
    }

    public int getSimState(int i) {
        int simStateForSlotIndex = SubscriptionManager.getSimStateForSlotIndex(i);
        if (simStateForSlotIndex == 10) {
            return 5;
        }
        return simStateForSlotIndex;
    }

    public String getSimOperator() {
        return getSimOperatorNumeric();
    }

    public String getSimOperator(int i) {
        return getSimOperatorNumeric(i);
    }

    public String getSimOperatorNumeric() {
        int defaultDataSubscriptionId = this.mSubId;
        if (!SubscriptionManager.isUsableSubIdValue(defaultDataSubscriptionId)) {
            defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
            if (!SubscriptionManager.isUsableSubIdValue(defaultDataSubscriptionId)) {
                defaultDataSubscriptionId = SubscriptionManager.getDefaultSmsSubscriptionId();
                if (!SubscriptionManager.isUsableSubIdValue(defaultDataSubscriptionId)) {
                    defaultDataSubscriptionId = SubscriptionManager.getDefaultVoiceSubscriptionId();
                    if (!SubscriptionManager.isUsableSubIdValue(defaultDataSubscriptionId)) {
                        defaultDataSubscriptionId = SubscriptionManager.getDefaultSubscriptionId();
                    }
                }
            }
        }
        return getSimOperatorNumeric(defaultDataSubscriptionId);
    }

    public String getSimOperatorNumeric(int i) {
        return getSimOperatorNumericForPhone(SubscriptionManager.getPhoneId(i));
    }

    public String getSimOperatorNumericForPhone(int i) {
        return (String) getTelephonyProperty(i, TelephonyProperties.icc_operator_numeric(), "");
    }

    public String getSimOperatorName() {
        return getSimOperatorNameForPhone(getPhoneId());
    }

    public String getSimOperatorName(int i) {
        return getSimOperatorNameForPhone(SubscriptionManager.getPhoneId(i));
    }

    public String getSimOperatorNameForPhone(int i) {
        return (String) getTelephonyProperty(i, TelephonyProperties.icc_operator_alpha(), "");
    }

    public String getSimCountryIso() {
        return getSimCountryIsoForPhone(getPhoneId());
    }

    public static String getSimCountryIso(int i) {
        return getSimCountryIsoForPhone(SubscriptionManager.getPhoneId(i));
    }

    public static String getSimCountryIsoForPhone(int i) {
        return (String) getTelephonyProperty(i, TelephonyProperties.icc_operator_iso_country(), "");
    }

    public String getSimSerialNumber() {
        return getSimSerialNumber(getSubId());
    }

    public String getSimSerialNumber(int i) {
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                return null;
            }
            return subscriberInfoService.getIccSerialNumberForSubscriber(i, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    @SystemApi
    public boolean isLteCdmaEvdoGsmWcdmaEnabled() {
        return getLteOnCdmaMode(getSubId()) == 1;
    }

    public int getLteOnCdmaMode(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return -1;
            }
            return iTelephony.getLteOnCdmaModeForSubscriber(i, getOpPackageName(), getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return -1;
        }
    }

    public int getCardIdForDefaultEuicc() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return -2;
            }
            return iTelephony.getCardIdForDefaultEuicc(this.mSubId, this.mContext.getOpPackageName());
        } catch (RemoteException unused) {
            return -2;
        }
    }

    public List<UiccCardInfo> getUiccCardsInfo() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                Log.e(TAG, "Error in getUiccCardsInfo: unable to connect to Telephony service.");
                return new ArrayList();
            }
            return iTelephony.getUiccCardsInfo(this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            Log.e(TAG, "Error in getUiccCardsInfo: " + e);
            return new ArrayList();
        }
    }

    @SystemApi
    public UiccSlotInfo[] getUiccSlotsInfo() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return null;
            }
            return iTelephony.getUiccSlotsInfo();
        } catch (RemoteException unused) {
            return null;
        }
    }

    public void refreshUiccProfile() {
        try {
            getITelephony().refreshUiccProfile(this.mSubId);
        } catch (RemoteException e) {
            com.android.telephony.Rlog.w(TAG, "RemoteException", e);
        }
    }

    @SystemApi
    public boolean switchSlots(int[] iArr) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return false;
            }
            return iTelephony.switchSlots(iArr);
        } catch (RemoteException unused) {
            return false;
        }
    }

    @SystemApi
    public Map<Integer, Integer> getLogicalToPhysicalSlotMapping() {
        HashMap map = new HashMap();
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                int[] slotsMapping = iTelephony.getSlotsMapping();
                for (int i = 0; i < slotsMapping.length; i++) {
                    map.put(Integer.valueOf(i), Integer.valueOf(slotsMapping[i]));
                }
            }
        } catch (RemoteException e) {
            Log.e(TAG, "getSlotsMapping RemoteException", e);
        }
        return map;
    }

    public String getSubscriberId() {
        return getSubscriberId(getSubId());
    }

    public String getSubscriberId(int i) {
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                return null;
            }
            return subscriberInfoService.getSubscriberIdForSubscriber(i, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    @SystemApi
    public ImsiEncryptionInfo getCarrierInfoForImsiEncryption(int i) {
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                com.android.telephony.Rlog.e(TAG, "IMSI error: Subscriber Info is null");
                return null;
            }
            int subId = getSubId(SubscriptionManager.getDefaultDataSubscriptionId());
            if (i != 1 && i != 2) {
                throw new IllegalArgumentException("IMSI error: Invalid key type");
            }
            ImsiEncryptionInfo carrierInfoForImsiEncryption = subscriberInfoService.getCarrierInfoForImsiEncryption(subId, i, this.mContext.getOpPackageName());
            if (carrierInfoForImsiEncryption == null && isImsiEncryptionRequired(subId, i)) {
                com.android.telephony.Rlog.e(TAG, "IMSI error: key is required but not found");
                throw new IllegalArgumentException("IMSI error: key is required but not found");
            }
            return carrierInfoForImsiEncryption;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getCarrierInfoForImsiEncryption RemoteException" + e);
            return null;
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "getCarrierInfoForImsiEncryption NullPointerException" + e2);
            return null;
        }
    }

    @SystemApi
    public void resetCarrierKeysForImsiEncryption() {
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                com.android.telephony.Rlog.e(TAG, "IMSI error: Subscriber Info is null");
                if (!isSystemProcess()) {
                    throw new RuntimeException("IMSI error: Subscriber Info is null");
                }
                return;
            }
            subscriberInfoService.resetCarrierKeysForImsiEncryption(getSubId(SubscriptionManager.getDefaultDataSubscriptionId()), this.mContext.getOpPackageName());
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getCarrierInfoForImsiEncryption RemoteException" + e);
            if (isSystemProcess()) {
                return;
            }
            e.rethrowAsRuntimeException();
        }
    }

    private boolean isImsiEncryptionRequired(int i, int i2) {
        PersistableBundle configForSubId;
        CarrierConfigManager carrierConfigManager = (CarrierConfigManager) this.mContext.getSystemService("carrier_config");
        if (carrierConfigManager == null || (configForSubId = carrierConfigManager.getConfigForSubId(i)) == null) {
            return false;
        }
        return isKeyEnabled(configForSubId.getInt(CarrierConfigManager.IMSI_KEY_AVAILABILITY_INT), i2);
    }

    public void setCarrierInfoForImsiEncryption(ImsiEncryptionInfo imsiEncryptionInfo) {
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                return;
            }
            subscriberInfoService.setCarrierInfoForImsiEncryption(this.mSubId, this.mContext.getOpPackageName(), imsiEncryptionInfo);
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "setCarrierInfoForImsiEncryption RemoteException", e);
        } catch (NullPointerException unused) {
        }
    }

    public String getGroupIdLevel1() {
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                return null;
            }
            return subscriberInfoService.getGroupIdLevel1ForSubscriber(getSubId(), this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public String getGroupIdLevel1(int i) {
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                return null;
            }
            return subscriberInfoService.getGroupIdLevel1ForSubscriber(i, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public String getLine1Number() {
        return getLine1Number(getSubId());
    }

    public String getLine1Number(int i) {
        ITelephony iTelephony;
        try {
            iTelephony = getITelephony();
        } catch (RemoteException | NullPointerException unused) {
        }
        String line1NumberForDisplay = iTelephony != null ? iTelephony.getLine1NumberForDisplay(i, this.mContext.getOpPackageName(), this.mContext.getAttributionTag()) : null;
        if (line1NumberForDisplay != null) {
            return line1NumberForDisplay;
        }
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                return null;
            }
            return subscriberInfoService.getLine1NumberForSubscriber(i, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
        } catch (RemoteException | NullPointerException unused2) {
            return null;
        }
    }

    public boolean setLine1NumberForDisplay(String str, String str2) {
        return setLine1NumberForDisplay(getSubId(), str, str2);
    }

    public boolean setLine1NumberForDisplay(int i, String str, String str2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.setLine1NumberForDisplayForSubscriber(i, str, str2);
            }
            return false;
        } catch (RemoteException | NullPointerException unused) {
            return false;
        }
    }

    public String getLine1AlphaTag() {
        return getLine1AlphaTag(getSubId());
    }

    public String getLine1AlphaTag(int i) {
        ITelephony iTelephony;
        try {
            iTelephony = getITelephony();
        } catch (RemoteException | NullPointerException unused) {
        }
        String line1AlphaTagForDisplay = iTelephony != null ? iTelephony.getLine1AlphaTagForDisplay(i, getOpPackageName(), getAttributionTag()) : null;
        if (line1AlphaTagForDisplay != null) {
            return line1AlphaTagForDisplay;
        }
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                return null;
            }
            return subscriberInfoService.getLine1AlphaTagForSubscriber(i, getOpPackageName(), getAttributionTag());
        } catch (RemoteException | NullPointerException unused2) {
            return null;
        }
    }

    @Deprecated
    public String[] getMergedSubscriberIds() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getMergedSubscriberIds(getSubId(), getOpPackageName(), getAttributionTag());
            }
            return null;
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    @SystemApi
    public String[] getMergedImsisFromGroup() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getMergedImsisFromGroup(getSubId(), getOpPackageName());
            }
        } catch (RemoteException | NullPointerException unused) {
        }
        return new String[0];
    }

    public String getMsisdn() {
        return getMsisdn(getSubId());
    }

    public String getMsisdn(int i) {
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                return null;
            }
            return subscriberInfoService.getMsisdnForSubscriber(i, getOpPackageName(), getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public String getVoiceMailNumber() {
        return getVoiceMailNumber(getSubId());
    }

    public String getVoiceMailNumber(int i) {
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                return null;
            }
            return subscriberInfoService.getVoiceMailNumberForSubscriber(i, getOpPackageName(), getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public boolean setVoiceMailNumber(String str, String str2) {
        return setVoiceMailNumber(getSubId(), str, str2);
    }

    public boolean setVoiceMailNumber(int i, String str, String str2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.setVoiceMailNumber(i, str, str2);
            }
            return false;
        } catch (RemoteException | NullPointerException unused) {
            return false;
        }
    }

    @SystemApi
    public Bundle getVisualVoicemailSettings() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getVisualVoicemailSettings(this.mContext.getOpPackageName(), this.mSubId);
            }
            return null;
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public String getVisualVoicemailPackageName() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getVisualVoicemailPackageName(this.mContext.getOpPackageName(), getAttributionTag(), getSubId());
            }
            return null;
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public void setVisualVoicemailSmsFilterSettings(VisualVoicemailSmsFilterSettings visualVoicemailSmsFilterSettings) {
        if (visualVoicemailSmsFilterSettings == null) {
            disableVisualVoicemailSmsFilter(this.mSubId);
        } else {
            enableVisualVoicemailSmsFilter(this.mSubId, visualVoicemailSmsFilterSettings);
        }
    }

    public void sendVisualVoicemailSms(String str, int i, String str2, PendingIntent pendingIntent) {
        sendVisualVoicemailSmsForSubscriber(this.mSubId, str, i, str2, pendingIntent);
    }

    public void enableVisualVoicemailSmsFilter(int i, VisualVoicemailSmsFilterSettings visualVoicemailSmsFilterSettings) {
        if (visualVoicemailSmsFilterSettings == null) {
            throw new IllegalArgumentException("Settings cannot be null");
        }
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.enableVisualVoicemailSmsFilter(this.mContext.getOpPackageName(), i, visualVoicemailSmsFilterSettings);
            }
        } catch (RemoteException | NullPointerException unused) {
        }
    }

    public void disableVisualVoicemailSmsFilter(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.disableVisualVoicemailSmsFilter(this.mContext.getOpPackageName(), i);
            }
        } catch (RemoteException | NullPointerException unused) {
        }
    }

    public VisualVoicemailSmsFilterSettings getVisualVoicemailSmsFilterSettings(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getVisualVoicemailSmsFilterSettings(this.mContext.getOpPackageName(), i);
            }
            return null;
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public VisualVoicemailSmsFilterSettings getActiveVisualVoicemailSmsFilterSettings(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getActiveVisualVoicemailSmsFilterSettings(i);
            }
            return null;
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public void sendVisualVoicemailSmsForSubscriber(int i, String str, int i2, String str2, PendingIntent pendingIntent) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.sendVisualVoicemailSmsForSubscriber(this.mContext.getOpPackageName(), this.mContext.getAttributionTag(), i, str, i2, str2, pendingIntent);
            }
        } catch (RemoteException unused) {
        }
    }

    @SystemApi
    public void setVoiceActivationState(int i) {
        setVoiceActivationState(getSubId(), i);
    }

    public void setVoiceActivationState(int i, int i2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.setVoiceActivationState(i, i2);
            }
        } catch (RemoteException | NullPointerException unused) {
        }
    }

    @SystemApi
    public void setDataActivationState(int i) {
        setDataActivationState(getSubId(), i);
    }

    public void setDataActivationState(int i, int i2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.setDataActivationState(i, i2);
            }
        } catch (RemoteException | NullPointerException unused) {
        }
    }

    @SystemApi
    public int getVoiceActivationState() {
        return getVoiceActivationState(getSubId());
    }

    public int getVoiceActivationState(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getVoiceActivationState(i, getOpPackageName());
            }
            return 0;
        } catch (RemoteException | NullPointerException unused) {
            return 0;
        }
    }

    @SystemApi
    public int getDataActivationState() {
        return getDataActivationState(getSubId());
    }

    public int getDataActivationState(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getDataActivationState(i, getOpPackageName());
            }
            return 0;
        } catch (RemoteException | NullPointerException unused) {
            return 0;
        }
    }

    public int getVoiceMessageCount() {
        return getVoiceMessageCount(getSubId());
    }

    public int getVoiceMessageCount(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return 0;
            }
            return iTelephony.getVoiceMessageCountForSubscriber(i, getOpPackageName(), getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return 0;
        }
    }

    public String getVoiceMailAlphaTag() {
        return getVoiceMailAlphaTag(getSubId());
    }

    public String getVoiceMailAlphaTag(int i) {
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                return null;
            }
            return subscriberInfoService.getVoiceMailAlphaTagForSubscriber(i, getOpPackageName(), getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public void sendDialerSpecialCode(String str) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                if (!isSystemProcess()) {
                    throw new RuntimeException("Telephony service unavailable");
                }
            } else {
                iTelephony.sendDialerSpecialCode(this.mContext.getOpPackageName(), str);
            }
        } catch (RemoteException e) {
            if (isSystemProcess()) {
                return;
            }
            e.rethrowAsRuntimeException();
        }
    }

    public String getIsimImpi() {
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                return null;
            }
            return subscriberInfoService.getIsimImpi(getSubId());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    @SystemApi
    public String getIsimDomain() {
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                return null;
            }
            return subscriberInfoService.getIsimDomain(getSubId());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public String[] getIsimImpu() {
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                return null;
            }
            return subscriberInfoService.getIsimImpu(getSubId());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public int getCallState() {
        TelecomManager telecomManager;
        Context context = this.mContext;
        if (context == null || (telecomManager = (TelecomManager) context.getSystemService(TelecomManager.class)) == null) {
            return 0;
        }
        return telecomManager.getCallState();
    }

    public int getCallState(int i) {
        return getCallStateForSlot(SubscriptionManager.getPhoneId(i));
    }

    private IPhoneSubInfo getSubscriberInfo() {
        return getSubscriberInfoService();
    }

    public int getCallStateForSlot(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return 0;
            }
            return iTelephony.getCallStateForSlot(i);
        } catch (RemoteException | NullPointerException unused) {
            return 0;
        }
    }

    public int getDataActivity() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return 0;
            }
            return iTelephony.getDataActivityForSubId(getSubId(SubscriptionManager.getActiveDataSubscriptionId()));
        } catch (RemoteException | NullPointerException unused) {
            return 0;
        }
    }

    public int getDataState() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return 0;
            }
            int dataStateForSubId = iTelephony.getDataStateForSubId(getSubId(SubscriptionManager.getActiveDataSubscriptionId()));
            if (dataStateForSubId != 4) {
                return dataStateForSubId;
            }
            if (Compatibility.isChangeEnabled(GET_DATA_STATE_R_VERSION)) {
                return dataStateForSubId;
            }
            return 2;
        } catch (RemoteException | NullPointerException unused) {
            return 0;
        }
    }

    public static String dataStateToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNKNOWN(" + i + ")" : "DISCONNECTING" : "SUSPENDED" : "CONNECTED" : "CONNECTING" : "DISCONNECTED";
    }

    private ITelephony getITelephony() {
        return ITelephony.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getTelephonyServiceRegisterer().get());
    }

    private IOns getIOns() {
        return IOns.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getOpportunisticNetworkServiceRegisterer().get());
    }

    public void listen(PhoneStateListener phoneStateListener, int i) {
        if (this.mContext == null) {
            return;
        }
        boolean z = getITelephony() != null;
        TelephonyRegistryManager telephonyRegistryManager = (TelephonyRegistryManager) this.mContext.getSystemService(Context.TELEPHONY_REGISTRY_SERVICE);
        if (telephonyRegistryManager != null) {
            telephonyRegistryManager.listenForSubscriber(this.mSubId, getOpPackageName(), getAttributionTag(), phoneStateListener, i, z);
        } else {
            com.android.telephony.Rlog.w(TAG, "telephony registry not ready.");
        }
    }

    public CdmaEriInformation getCdmaEriInformation() {
        return new CdmaEriInformation(getCdmaEriIconIndex(getSubId()), getCdmaEriIconMode(getSubId()));
    }

    public int getCdmaEriIconIndex(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return -1;
            }
            return iTelephony.getCdmaEriIconIndexForSubscriber(i, getOpPackageName(), getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return -1;
        }
    }

    public int getCdmaEriIconMode(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return -1;
            }
            return iTelephony.getCdmaEriIconModeForSubscriber(i, getOpPackageName(), getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return -1;
        }
    }

    public String getCdmaEriText() {
        return getCdmaEriText(getSubId());
    }

    public String getCdmaEriText(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return null;
            }
            return iTelephony.getCdmaEriTextForSubscriber(i, getOpPackageName(), getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public boolean isVoiceCapable() {
        Context context = this.mContext;
        if (context == null) {
            return true;
        }
        return context.getResources().getBoolean(R.bool.config_voice_capable);
    }

    public boolean isSmsCapable() {
        Context context = this.mContext;
        if (context == null) {
            return true;
        }
        return context.getResources().getBoolean(R.bool.config_sms_capable);
    }

    public List<CellInfo> getAllCellInfo() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return null;
            }
            return iTelephony.getAllCellInfo(getOpPackageName(), getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public static abstract class CellInfoCallback {
        public static final int ERROR_MODEM_ERROR = 2;
        public static final int ERROR_TIMEOUT = 1;

        @Retention(RetentionPolicy.SOURCE)
        public @interface CellInfoCallbackError {
        }

        public abstract void onCellInfo(List<CellInfo> list);

        public void onError(int i, Throwable th) {
            onCellInfo(new ArrayList());
        }
    }

    public void requestCellInfoUpdate(Executor executor, CellInfoCallback cellInfoCallback) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return;
            }
            iTelephony.requestCellInfoUpdate(getSubId(), new AnonymousClass1(executor, cellInfoCallback), getOpPackageName(), getAttributionTag());
        } catch (RemoteException unused) {
        }
    }

    /* JADX INFO: renamed from: android.telephony.TelephonyManager$1, reason: invalid class name */
    class AnonymousClass1 extends ICellInfoCallback.Stub {
        final /* synthetic */ CellInfoCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass1(Executor executor, CellInfoCallback cellInfoCallback) {
            this.val$executor = executor;
            this.val$callback = cellInfoCallback;
        }

        @Override // android.telephony.ICellInfoCallback
        public void onCellInfo(final List<CellInfo> list) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final CellInfoCallback cellInfoCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$TelephonyManager$1$_lhRGaHON83XJVYOs3znpMCjFkk
                    @Override // java.lang.Runnable
                    public final void run() {
                        cellInfoCallback.onCellInfo(list);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        @Override // android.telephony.ICellInfoCallback
        public void onError(final int i, final String str, final String str2) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final CellInfoCallback cellInfoCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$TelephonyManager$1$U-D6vai2XzRYmZ15_lwv-SXh4NA
                    @Override // java.lang.Runnable
                    public final void run() {
                        cellInfoCallback.onError(i, TelephonyManager.createThrowableByClassName(str, str2));
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    @SystemApi
    public void requestCellInfoUpdate(WorkSource workSource, Executor executor, CellInfoCallback cellInfoCallback) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return;
            }
            iTelephony.requestCellInfoUpdateWithWorkSource(getSubId(), new AnonymousClass2(executor, cellInfoCallback), getOpPackageName(), getAttributionTag(), workSource);
        } catch (RemoteException unused) {
        }
    }

    /* JADX INFO: renamed from: android.telephony.TelephonyManager$2, reason: invalid class name */
    class AnonymousClass2 extends ICellInfoCallback.Stub {
        final /* synthetic */ CellInfoCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass2(Executor executor, CellInfoCallback cellInfoCallback) {
            this.val$executor = executor;
            this.val$callback = cellInfoCallback;
        }

        @Override // android.telephony.ICellInfoCallback
        public void onCellInfo(final List<CellInfo> list) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final CellInfoCallback cellInfoCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$TelephonyManager$2$M0b84V4CLCwTE8bT2wen-Evmyo8
                    @Override // java.lang.Runnable
                    public final void run() {
                        cellInfoCallback.onCellInfo(list);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        @Override // android.telephony.ICellInfoCallback
        public void onError(final int i, final String str, final String str2) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final CellInfoCallback cellInfoCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$TelephonyManager$2$e4OFWIpC0nrdt3SzbszJ4MtNcTs
                    @Override // java.lang.Runnable
                    public final void run() {
                        cellInfoCallback.onError(i, TelephonyManager.createThrowableByClassName(str, str2));
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Throwable createThrowableByClassName(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            return (Throwable) Class.forName(str).getConstructor(String.class).newInstance(str2);
        } catch (ClassCastException | ReflectiveOperationException unused) {
            return new RuntimeException(str + ": " + str2);
        }
    }

    public void setCellInfoListRate(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.setCellInfoListRate(i);
            }
        } catch (RemoteException | NullPointerException unused) {
        }
    }

    public String getMmsUserAgent() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getMmsUserAgent(getSubId());
            }
            return null;
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public String getMmsUAProfUrl() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getMmsUAProfUrl(getSubId());
            }
            return null;
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    @Deprecated
    public IccOpenLogicalChannelResponse iccOpenLogicalChannel(String str) {
        return iccOpenLogicalChannel(getSubId(), str, -1);
    }

    @SystemApi
    public IccOpenLogicalChannelResponse iccOpenLogicalChannelBySlot(int i, String str, int i2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.iccOpenLogicalChannelBySlot(i, getOpPackageName(), str, i2);
            }
            return null;
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    @Deprecated
    public IccOpenLogicalChannelResponse iccOpenLogicalChannel(String str, int i) {
        return iccOpenLogicalChannel(getSubId(), str, i);
    }

    @Deprecated
    public IccOpenLogicalChannelResponse iccOpenLogicalChannel(int i, String str, int i2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.iccOpenLogicalChannel(i, getOpPackageName(), str, i2);
            }
            return null;
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    @SystemApi
    @Deprecated
    public boolean iccCloseLogicalChannelBySlot(int i, int i2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.iccCloseLogicalChannelBySlot(i, i2);
            }
            return false;
        } catch (RemoteException | NullPointerException unused) {
            return false;
        }
    }

    @Deprecated
    public boolean iccCloseLogicalChannel(int i) {
        return iccCloseLogicalChannel(getSubId(), i);
    }

    @Deprecated
    public boolean iccCloseLogicalChannel(int i, int i2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.iccCloseLogicalChannel(i, i2);
            }
            return false;
        } catch (RemoteException | NullPointerException unused) {
            return false;
        }
    }

    @SystemApi
    @Deprecated
    public String iccTransmitApduLogicalChannelBySlot(int i, int i2, int i3, int i4, int i5, int i6, int i7, String str) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.iccTransmitApduLogicalChannelBySlot(i, i2, i3, i4, i5, i6, i7, str);
            }
            return null;
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    @Deprecated
    public String iccTransmitApduLogicalChannel(int i, int i2, int i3, int i4, int i5, int i6, String str) {
        return iccTransmitApduLogicalChannel(getSubId(), i, i2, i3, i4, i5, i6, str);
    }

    @Deprecated
    public String iccTransmitApduLogicalChannel(int i, int i2, int i3, int i4, int i5, int i6, int i7, String str) {
        try {
            ITelephony iTelephony = getITelephony();
            return iTelephony != null ? iTelephony.iccTransmitApduLogicalChannel(i, i2, i3, i4, i5, i6, i7, str) : "";
        } catch (RemoteException | NullPointerException unused) {
            return "";
        }
    }

    @SystemApi
    @Deprecated
    public String iccTransmitApduBasicChannelBySlot(int i, int i2, int i3, int i4, int i5, int i6, String str) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.iccTransmitApduBasicChannelBySlot(i, getOpPackageName(), i2, i3, i4, i5, i6, str);
            }
            return null;
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    @Deprecated
    public String iccTransmitApduBasicChannel(int i, int i2, int i3, int i4, int i5, String str) {
        return iccTransmitApduBasicChannel(getSubId(), i, i2, i3, i4, i5, str);
    }

    @Deprecated
    public String iccTransmitApduBasicChannel(int i, int i2, int i3, int i4, int i5, int i6, String str) {
        try {
            ITelephony iTelephony = getITelephony();
            return iTelephony != null ? iTelephony.iccTransmitApduBasicChannel(i, getOpPackageName(), i2, i3, i4, i5, i6, str) : "";
        } catch (RemoteException | NullPointerException unused) {
            return "";
        }
    }

    @Deprecated
    public byte[] iccExchangeSimIO(int i, int i2, int i3, int i4, int i5, String str) {
        return iccExchangeSimIO(getSubId(), i, i2, i3, i4, i5, str);
    }

    @Deprecated
    public byte[] iccExchangeSimIO(int i, int i2, int i3, int i4, int i5, int i6, String str) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.iccExchangeSimIO(i, i2, i3, i4, i5, i6, str);
            }
            return null;
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    @Deprecated
    public String sendEnvelopeWithStatus(String str) {
        return sendEnvelopeWithStatus(getSubId(), str);
    }

    @Deprecated
    public String sendEnvelopeWithStatus(int i, String str) {
        try {
            ITelephony iTelephony = getITelephony();
            return iTelephony != null ? iTelephony.sendEnvelopeWithStatus(i, str) : "";
        } catch (RemoteException | NullPointerException unused) {
            return "";
        }
    }

    public String nvReadItem(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            return iTelephony != null ? iTelephony.nvReadItem(i) : "";
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "nvReadItem RemoteException", e);
            return "";
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "nvReadItem NPE", e2);
            return "";
        }
    }

    public boolean nvWriteItem(int i, String str) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.nvWriteItem(i, str);
            }
            return false;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "nvWriteItem RemoteException", e);
            return false;
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "nvWriteItem NPE", e2);
            return false;
        }
    }

    public boolean nvWriteCdmaPrl(byte[] bArr) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.nvWriteCdmaPrl(bArr);
            }
            return false;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "nvWriteCdmaPrl RemoteException", e);
            return false;
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "nvWriteCdmaPrl NPE", e2);
            return false;
        }
    }

    public boolean nvResetConfig(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return false;
            }
            if (i == 1) {
                return iTelephony.rebootModem(getSlotIndex());
            }
            if (i == 3) {
                return iTelephony.resetModemConfig(getSlotIndex());
            }
            com.android.telephony.Rlog.e(TAG, "nvResetConfig unsupported reset type");
            return false;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "nvResetConfig RemoteException", e);
            return false;
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "nvResetConfig NPE", e2);
            return false;
        }
    }

    @SystemApi
    public boolean resetRadioConfig() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.resetModemConfig(getSlotIndex());
            }
            return false;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "resetRadioConfig RemoteException", e);
            return false;
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "resetRadioConfig NPE", e2);
            return false;
        }
    }

    @SystemApi
    public boolean rebootRadio() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.rebootModem(getSlotIndex());
            }
            return false;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "rebootRadio RemoteException", e);
            return false;
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "rebootRadio NPE", e2);
            return false;
        }
    }

    public int getSubscriptionId() {
        return getSubId();
    }

    private int getSubId() {
        if (SubscriptionManager.isUsableSubIdValue(this.mSubId)) {
            return this.mSubId;
        }
        return SubscriptionManager.getDefaultSubscriptionId();
    }

    private int getSubId(int i) {
        return SubscriptionManager.isUsableSubIdValue(this.mSubId) ? this.mSubId : i;
    }

    private int getPhoneId() {
        return SubscriptionManager.getPhoneId(getSubId());
    }

    private int getPhoneId(int i) {
        return SubscriptionManager.getPhoneId(getSubId(i));
    }

    public int getSlotIndex() {
        int slotIndex = SubscriptionManager.getSlotIndex(getSubId());
        if (slotIndex == -1) {
            return Integer.MAX_VALUE;
        }
        return slotIndex;
    }

    @SystemApi
    public void requestNumberVerification(PhoneNumberRange phoneNumberRange, long j, Executor executor, final NumberVerificationCallback numberVerificationCallback) {
        Objects.requireNonNull(executor, "Executor must be non-null");
        Objects.requireNonNull(numberVerificationCallback, "Callback must be non-null");
        AnonymousClass3 anonymousClass3 = new AnonymousClass3(executor, numberVerificationCallback);
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.requestNumberVerification(phoneNumberRange, j, anonymousClass3, getOpPackageName());
            }
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "requestNumberVerification RemoteException", e);
            executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$TelephonyManager$XFU-vZJrOZusIeu_3KPF1kdE-gY
                @Override // java.lang.Runnable
                public final void run() {
                    numberVerificationCallback.onVerificationFailed(0);
                }
            });
        }
    }

    /* JADX INFO: renamed from: android.telephony.TelephonyManager$3, reason: invalid class name */
    class AnonymousClass3 extends INumberVerificationCallback.Stub {
        final /* synthetic */ NumberVerificationCallback val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass3(Executor executor, NumberVerificationCallback numberVerificationCallback) {
            this.val$executor = executor;
            this.val$callback = numberVerificationCallback;
        }

        @Override // com.android.internal.telephony.INumberVerificationCallback
        public void onCallReceived(final String str) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final NumberVerificationCallback numberVerificationCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$TelephonyManager$3$fF3BbTP9yMQKvQ20Fpb7V-gO8Hw
                    @Override // java.lang.Runnable
                    public final void run() {
                        numberVerificationCallback.onCallReceived(str);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }

        @Override // com.android.internal.telephony.INumberVerificationCallback
        public void onVerificationFailed(final int i) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final NumberVerificationCallback numberVerificationCallback = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$TelephonyManager$3$_a6YNbHbIiEVgP5Odq2FyWkJGqY
                    @Override // java.lang.Runnable
                    public final void run() {
                        numberVerificationCallback.onVerificationFailed(i);
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    private static <T> List<T> updateTelephonyProperty(List<T> list, int i, T t) {
        ArrayList arrayList = new ArrayList(list);
        while (arrayList.size() <= i) {
            arrayList.add(null);
        }
        arrayList.set(i, t);
        return arrayList;
    }

    public static int getIntAtIndex(ContentResolver contentResolver, String str, int i) throws Settings.SettingNotFoundException {
        String string = Settings.Global.getString(contentResolver, str);
        if (string != null) {
            String[] strArrSplit = string.split(SmsManager.REGEX_PREFIX_DELIMITER);
            if (i >= 0 && i < strArrSplit.length && strArrSplit[i] != null) {
                try {
                    return Integer.parseInt(strArrSplit[i]);
                } catch (NumberFormatException unused) {
                }
            }
        }
        throw new Settings.SettingNotFoundException(str);
    }

    public static boolean putIntAtIndex(ContentResolver contentResolver, String str, int i, int i2) {
        String string = Settings.Global.getString(contentResolver, str);
        if (i == Integer.MAX_VALUE) {
            throw new IllegalArgumentException("putIntAtIndex index == MAX_VALUE index=" + i);
        }
        if (i < 0) {
            throw new IllegalArgumentException("putIntAtIndex index < 0 index=" + i);
        }
        String[] strArrSplit = string != null ? string.split(SmsManager.REGEX_PREFIX_DELIMITER) : null;
        int i3 = 0;
        String str2 = "";
        while (i3 < i) {
            str2 = str2 + ((strArrSplit == null || i3 >= strArrSplit.length) ? "" : strArrSplit[i3]) + SmsManager.REGEX_PREFIX_DELIMITER;
            i3++;
        }
        String str3 = str2 + i2;
        if (strArrSplit != null) {
            while (true) {
                i++;
                if (i >= strArrSplit.length) {
                    break;
                }
                str3 = str3 + SmsManager.REGEX_PREFIX_DELIMITER + strArrSplit[i];
            }
        }
        return Settings.Global.putString(contentResolver, str, str3);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getTelephonyProperty(int r1, java.lang.String r2, java.lang.String r3) {
        /*
            java.lang.String r2 = android.os.SystemProperties.get(r2)
            if (r2 == 0) goto L1e
            int r0 = r2.length()
            if (r0 <= 0) goto L1e
            java.lang.String r0 = ","
            java.lang.String[] r2 = r2.split(r0)
            if (r1 < 0) goto L1e
            int r0 = r2.length
            if (r1 >= r0) goto L1e
            r0 = r2[r1]
            if (r0 == 0) goto L1e
            r1 = r2[r1]
            goto L1f
        L1e:
            r1 = 0
        L1f:
            if (r1 != 0) goto L22
            goto L23
        L22:
            r3 = r1
        L23:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: android.telephony.TelephonyManager.getTelephonyProperty(int, java.lang.String, java.lang.String):java.lang.String");
    }

    private static <T> T getTelephonyProperty(int i, List<T> list, T t) {
        T t2 = (i < 0 || i >= list.size()) ? null : list.get(i);
        return t2 != null ? t2 : t;
    }

    public static String getTelephonyProperty(String str, String str2) {
        String str3 = SystemProperties.get(str);
        return TextUtils.isEmpty(str3) ? str2 : str3;
    }

    public int getSimCount() {
        return getPhoneCount();
    }

    @SystemApi
    public String getIsimIst() {
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                return null;
            }
            return subscriberInfoService.getIsimIst(getSubId());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public String[] getIsimPcscf() {
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                return null;
            }
            return subscriberInfoService.getIsimPcscf(getSubId());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public String getIccAuthentication(int i, int i2, String str) {
        return getIccAuthentication(getSubId(), i, i2, str);
    }

    public String getIccAuthentication(int i, int i2, int i3, String str) {
        try {
            IPhoneSubInfo subscriberInfoService = getSubscriberInfoService();
            if (subscriberInfoService == null) {
                return null;
            }
            return subscriberInfoService.getIccSimChallengeResponse(i, i2, i3, str);
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public String[] getForbiddenPlmns() {
        return getForbiddenPlmns(getSubId(), 2);
    }

    public String[] getForbiddenPlmns(int i, int i2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return null;
            }
            return iTelephony.getForbiddenPlmns(i, i2, this.mContext.getOpPackageName(), getAttributionTag());
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    public int setForbiddenPlmns(List<String> list) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return -1;
            }
            return iTelephony.setForbiddenPlmns(getSubId(), 2, list, getOpPackageName(), getAttributionTag());
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "setForbiddenPlmns RemoteException: " + e.getMessage());
            return -1;
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "setForbiddenPlmns NullPointerException: " + e2.getMessage());
            return -1;
        }
    }

    public String[] getPcscfAddress(String str) {
        try {
            ITelephony iTelephony = getITelephony();
            return iTelephony == null ? new String[0] : iTelephony.getPcscfAddress(str, getOpPackageName(), getAttributionTag());
        } catch (RemoteException unused) {
            return new String[0];
        }
    }

    @SystemApi
    public void resetIms(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.resetIms(i);
            }
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "toggleImsOnOff, RemoteException: " + e.getMessage());
        }
    }

    public void enableIms(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.enableIms(i);
            }
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "enableIms, RemoteException: " + e.getMessage());
        }
    }

    public void disableIms(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.disableIms(i);
            }
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "disableIms, RemoteException: " + e.getMessage());
        }
    }

    public IImsMmTelFeature getImsMmTelFeatureAndListen(int i, IImsServiceFeatureCallback iImsServiceFeatureCallback) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getMmTelFeatureAndListen(i, iImsServiceFeatureCallback);
            }
            return null;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getImsMmTelFeatureAndListen, RemoteException: " + e.getMessage());
            return null;
        }
    }

    public IImsRcsFeature getImsRcsFeatureAndListen(int i, IImsServiceFeatureCallback iImsServiceFeatureCallback) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getRcsFeatureAndListen(i, iImsServiceFeatureCallback);
            }
            return null;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getImsRcsFeatureAndListen, RemoteException: " + e.getMessage());
            return null;
        }
    }

    public void unregisterImsFeatureCallback(int i, int i2, IImsServiceFeatureCallback iImsServiceFeatureCallback) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.unregisterImsFeatureCallback(i, i2, iImsServiceFeatureCallback);
            }
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "unregisterImsFeatureCallback, RemoteException: " + e.getMessage());
        }
    }

    public IImsRegistration getImsRegistration(int i, int i2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getImsRegistration(i, i2);
            }
            return null;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getImsRegistration, RemoteException: " + e.getMessage());
            return null;
        }
    }

    public IImsConfig getImsConfig(int i, int i2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getImsConfig(i, i2);
            }
            return null;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getImsRegistration, RemoteException: " + e.getMessage());
            return null;
        }
    }

    public void setImsRegistrationState(boolean z) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.setImsRegistrationState(z);
            }
        } catch (RemoteException unused) {
        }
    }

    @Deprecated
    public int getPreferredNetworkType(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getPreferredNetworkType(i);
            }
            return -1;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getPreferredNetworkType RemoteException", e);
            return -1;
        }
    }

    @SystemApi
    public long getPreferredNetworkTypeBitmask() {
        try {
            if (getITelephony() != null) {
                return RadioAccessFamily.getRafFromNetworkType(r0.getPreferredNetworkType(getSubId()));
            }
            return 0L;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getPreferredNetworkTypeBitmask RemoteException", e);
            return 0L;
        }
    }

    @SystemApi
    public long getAllowedNetworkTypes() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getAllowedNetworkTypes(getSubId());
            }
            return -1L;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getAllowedNetworkTypes RemoteException", e);
            return -1L;
        }
    }

    public void setNetworkSelectionModeAutomatic() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.setNetworkSelectionModeAutomatic(getSubId());
            }
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "setNetworkSelectionModeAutomatic RemoteException", e);
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "setNetworkSelectionModeAutomatic NPE", e2);
        }
    }

    public CellNetworkScanResult getAvailableNetworks() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getCellNetworkScanResults(getSubId(), getOpPackageName(), getAttributionTag());
            }
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getAvailableNetworks RemoteException", e);
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "getAvailableNetworks NPE", e2);
        }
        return new CellNetworkScanResult(4, (List<OperatorInfo>) null);
    }

    public NetworkScan requestNetworkScan(NetworkScanRequest networkScanRequest, Executor executor, TelephonyScanManager.NetworkScanCallback networkScanCallback) {
        synchronized (this) {
            if (this.mTelephonyScanManager == null) {
                this.mTelephonyScanManager = new TelephonyScanManager();
            }
        }
        return this.mTelephonyScanManager.requestNetworkScan(getSubId(), networkScanRequest, executor, networkScanCallback, getOpPackageName(), getAttributionTag());
    }

    @Deprecated
    public NetworkScan requestNetworkScan(NetworkScanRequest networkScanRequest, TelephonyScanManager.NetworkScanCallback networkScanCallback) {
        return requestNetworkScan(networkScanRequest, AsyncTask.SERIAL_EXECUTOR, networkScanCallback);
    }

    public boolean setNetworkSelectionModeManual(String str, boolean z) {
        return setNetworkSelectionModeManual(new OperatorInfo("", "", str), z);
    }

    public boolean setNetworkSelectionModeManual(String str, boolean z, int i) {
        return setNetworkSelectionModeManual(new OperatorInfo("", "", str, i), z);
    }

    public boolean setNetworkSelectionModeManual(OperatorInfo operatorInfo, boolean z) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.setNetworkSelectionModeManual(getSubId(), operatorInfo, z);
            }
            return false;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "setNetworkSelectionModeManual RemoteException", e);
            return false;
        }
    }

    public int getNetworkSelectionMode() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getNetworkSelectionMode(getSubId());
            }
            return 0;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getNetworkSelectionMode RemoteException", e);
            return 0;
        }
    }

    public String getManualNetworkSelectionPlmn() {
        try {
            ITelephony iTelephony = getITelephony();
            return (iTelephony == null || !isManualNetworkSelectionAllowed()) ? "" : iTelephony.getManualNetworkSelectionPlmn(getSubId());
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getManualNetworkSelectionPlmn RemoteException", e);
            return "";
        }
    }

    public boolean isInEmergencySmsMode() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isInEmergencySmsMode();
            }
            return false;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "isInEmergencySmsMode RemoteException", e);
            return false;
        }
    }

    @Deprecated
    public boolean setPreferredNetworkType(int i, int i2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.setPreferredNetworkType(i, i2);
            }
            return false;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "setPreferredNetworkType RemoteException", e);
            return false;
        }
    }

    @SystemApi
    public boolean setPreferredNetworkTypeBitmask(long j) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.setPreferredNetworkType(getSubId(), RadioAccessFamily.getNetworkTypeFromRaf((int) j));
            }
            return false;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "setPreferredNetworkTypeBitmask RemoteException", e);
            return false;
        }
    }

    @SystemApi
    public boolean setAllowedNetworkTypes(long j) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.setAllowedNetworkTypes(getSubId(), j);
            }
            return false;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "setAllowedNetworkTypes RemoteException", e);
            return false;
        }
    }

    public void setAllowedNetworkTypesForReason(int i, long j) {
        if (i != 0) {
            throw new IllegalArgumentException("invalid AllowedNetworkTypesReason.");
        }
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.setAllowedNetworkTypesForReason(getSubId(), i, j);
                return;
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "setAllowedNetworkTypesForReason RemoteException", e);
            e.rethrowFromSystemServer();
        }
    }

    public long getAllowedNetworkTypesForReason(int i) {
        if (i != 0) {
            throw new IllegalArgumentException("invalid AllowedNetworkTypesReason.");
        }
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getAllowedNetworkTypesForReason(getSubId(), i);
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getAllowedNetworkTypesForReason RemoteException", e);
            e.rethrowFromSystemServer();
            return -1L;
        }
    }

    public long getEffectiveAllowedNetworkTypes() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getEffectiveAllowedNetworkTypes(getSubId());
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getEffectiveAllowedNetworkTypes RemoteException", e);
            e.rethrowFromSystemServer();
            return -1L;
        }
    }

    public boolean setPreferredNetworkTypeToGlobal() {
        return setPreferredNetworkTypeToGlobal(getSubId());
    }

    public boolean setPreferredNetworkTypeToGlobal(int i) {
        return setPreferredNetworkType(i, 10);
    }

    @SystemApi
    public boolean isTetheringApnRequired() {
        return isTetheringApnRequired(getSubId(SubscriptionManager.getActiveDataSubscriptionId()));
    }

    public boolean isTetheringApnRequired(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isTetheringApnRequiredForSubscriber(i);
            }
            return false;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "hasMatchedTetherApnSetting RemoteException", e);
            return false;
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "hasMatchedTetherApnSetting NPE", e2);
            return false;
        }
    }

    public boolean hasCarrierPrivileges() {
        return hasCarrierPrivileges(getSubId());
    }

    public boolean hasCarrierPrivileges(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getCarrierPrivilegeStatus(i) == 1;
            }
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "hasCarrierPrivileges RemoteException", e);
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "hasCarrierPrivileges NPE", e2);
        }
        return false;
    }

    public boolean setOperatorBrandOverride(String str) {
        return setOperatorBrandOverride(getSubId(), str);
    }

    public boolean setOperatorBrandOverride(int i, String str) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.setOperatorBrandOverride(i, str);
            }
            return false;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "setOperatorBrandOverride RemoteException", e);
            return false;
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "setOperatorBrandOverride NPE", e2);
            return false;
        }
    }

    public boolean setRoamingOverride(List<String> list, List<String> list2, List<String> list3, List<String> list4) {
        return setRoamingOverride(getSubId(), list, list2, list3, list4);
    }

    public boolean setRoamingOverride(int i, List<String> list, List<String> list2, List<String> list3, List<String> list4) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.setRoamingOverride(i, list, list2, list3, list4);
            }
            return false;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "setRoamingOverride RemoteException", e);
            return false;
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "setRoamingOverride NPE", e2);
            return false;
        }
    }

    @SystemApi
    public String getCdmaMdn() {
        return getCdmaMdn(getSubId());
    }

    @SystemApi
    public String getCdmaMdn(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return null;
            }
            return iTelephony.getCdmaMdn(i);
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    @SystemApi
    public String getCdmaMin() {
        return getCdmaMin(getSubId());
    }

    @SystemApi
    public String getCdmaMin(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return null;
            }
            return iTelephony.getCdmaMin(i);
        } catch (RemoteException | NullPointerException unused) {
            return null;
        }
    }

    @SystemApi
    public int checkCarrierPrivilegesForPackage(String str) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.checkCarrierPrivilegesForPackage(getSubId(), str);
            }
            return 0;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "checkCarrierPrivilegesForPackage RemoteException", e);
            return 0;
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "checkCarrierPrivilegesForPackage NPE", e2);
            return 0;
        }
    }

    @SystemApi
    public int checkCarrierPrivilegesForPackageAnyPhone(String str) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.checkCarrierPrivilegesForPackageAnyPhone(str);
            }
            return 0;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "checkCarrierPrivilegesForPackageAnyPhone RemoteException", e);
            return 0;
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "checkCarrierPrivilegesForPackageAnyPhone NPE", e2);
            return 0;
        }
    }

    @SystemApi
    public List<String> getCarrierPackageNamesForIntent(Intent intent) {
        return getCarrierPackageNamesForIntentAndPhone(intent, getPhoneId());
    }

    @SystemApi
    public List<String> getCarrierPackageNamesForIntentAndPhone(Intent intent, int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getCarrierPackageNamesForIntentAndPhone(intent, i);
            }
            return null;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getCarrierPackageNamesForIntentAndPhone RemoteException", e);
            return null;
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "getCarrierPackageNamesForIntentAndPhone NPE", e2);
            return null;
        }
    }

    public List<String> getPackagesWithCarrierPrivileges() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getPackagesWithCarrierPrivileges(getPhoneId());
            }
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getPackagesWithCarrierPrivileges RemoteException", e);
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "getPackagesWithCarrierPrivileges NPE", e2);
        }
        return Collections.EMPTY_LIST;
    }

    @SystemApi
    public List<String> getCarrierPrivilegedPackagesForAllActiveSubscriptions() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getPackagesWithCarrierPrivilegesForAllPhones();
            }
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getCarrierPrivilegedPackagesForAllActiveSubscriptions RemoteException", e);
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "getCarrierPrivilegedPackagesForAllActiveSubscriptions NPE", e2);
        }
        return Collections.EMPTY_LIST;
    }

    @SystemApi
    public void dial(String str) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.dial(str);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#dial", e);
        }
    }

    @SystemApi
    @Deprecated
    public void call(String str, String str2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.call(str, str2);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#call", e);
        }
    }

    @SystemApi
    @Deprecated
    public boolean isOffhook() {
        return ((TelecomManager) this.mContext.getSystemService(Context.TELECOM_SERVICE)).isInCall();
    }

    @SystemApi
    @Deprecated
    public boolean isRinging() {
        return ((TelecomManager) this.mContext.getSystemService(Context.TELECOM_SERVICE)).isRinging();
    }

    @SystemApi
    @Deprecated
    public boolean isIdle() {
        return !((TelecomManager) this.mContext.getSystemService(Context.TELECOM_SERVICE)).isInCall();
    }

    @SystemApi
    @Deprecated
    public boolean isRadioOn() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isRadioOnWithFeature(getOpPackageName(), getAttributionTag());
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isRadioOn", e);
            return false;
        }
    }

    @SystemApi
    public boolean supplyPin(String str) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.supplyPinForSubscriber(getSubId(), str);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#supplyPinForSubscriber", e);
            return false;
        }
    }

    @SystemApi
    public boolean supplyPuk(String str, String str2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.supplyPukForSubscriber(getSubId(), str, str2);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#supplyPukForSubscriber", e);
            return false;
        }
    }

    @SystemApi
    public int[] supplyPinReportResult(String str) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.supplyPinReportResultForSubscriber(getSubId(), str);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#supplyPinReportResultForSubscriber", e);
        }
        return new int[0];
    }

    @SystemApi
    public int[] supplyPukReportResult(String str, String str2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.supplyPukReportResultForSubscriber(getSubId(), str, str2);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#]", e);
        }
        return new int[0];
    }

    public PinResult supplyPinReportPinResult(String str) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return null;
            }
            int[] iArrSupplyPinReportResultForSubscriber = iTelephony.supplyPinReportResultForSubscriber(getSubId(), str);
            return new PinResult(iArrSupplyPinReportResultForSubscriber[0], iArrSupplyPinReportResultForSubscriber[1]);
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#supplyPinReportResultForSubscriber", e);
            return null;
        }
    }

    public PinResult supplyPukReportPinResult(String str, String str2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return null;
            }
            int[] iArrSupplyPukReportResultForSubscriber = iTelephony.supplyPukReportResultForSubscriber(getSubId(), str, str2);
            return new PinResult(iArrSupplyPukReportResultForSubscriber[0], iArrSupplyPukReportResultForSubscriber[1]);
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#]", e);
            return null;
        }
    }

    public void sendUssdRequest(String str, final UssdResponseCallback ussdResponseCallback, Handler handler) {
        Preconditions.checkNotNull(ussdResponseCallback, "UssdResponseCallback cannot be null.");
        ResultReceiver resultReceiver = new ResultReceiver(handler) { // from class: android.telephony.TelephonyManager.4
            @Override // android.os.ResultReceiver
            protected void onReceiveResult(int i, Bundle bundle) {
                com.android.telephony.Rlog.d(TelephonyManager.TAG, "USSD:" + i);
                Preconditions.checkNotNull(bundle, "ussdResponse cannot be null.");
                UssdResponse ussdResponse = (UssdResponse) bundle.getParcelable(TelephonyManager.USSD_RESPONSE);
                if (i == 100) {
                    ussdResponseCallback.onReceiveUssdResponse(this, ussdResponse.getUssdRequest(), ussdResponse.getReturnMessage());
                } else {
                    ussdResponseCallback.onReceiveUssdResponseFailed(this, ussdResponse.getUssdRequest(), i);
                }
            }
        };
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.handleUssdRequest(getSubId(), str, resultReceiver);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#sendUSSDCode", e);
            UssdResponse ussdResponse = new UssdResponse(str, "");
            Bundle bundle = new Bundle();
            bundle.putParcelable(USSD_RESPONSE, ussdResponse);
            resultReceiver.send(-2, bundle);
        }
    }

    public boolean isConcurrentVoiceAndDataSupported() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return false;
            }
            return iTelephony.isConcurrentVoiceAndDataAllowed(getSubId());
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isConcurrentVoiceAndDataAllowed", e);
            return false;
        }
    }

    @SystemApi
    public boolean handlePinMmi(String str) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.handlePinMmi(str);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#handlePinMmi", e);
            return false;
        }
    }

    @SystemApi
    public boolean handlePinMmiForSubscriber(int i, String str) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.handlePinMmiForSubscriber(i, str);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#handlePinMmi", e);
            return false;
        }
    }

    @SystemApi
    public void toggleRadioOnOff() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.toggleRadioOnOff();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#toggleRadioOnOff", e);
        }
    }

    @SystemApi
    public boolean setRadio(boolean z) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.setRadio(z);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#setRadio", e);
            return false;
        }
    }

    @SystemApi
    public boolean setRadioPower(boolean z) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.setRadioPower(z);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#setRadioPower", e);
            return false;
        }
    }

    @SystemApi
    public void shutdownAllRadios() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.shutdownMobileRadios();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#shutdownAllRadios", e);
            e.rethrowAsRuntimeException();
        }
    }

    @SystemApi
    public boolean isAnyRadioPoweredOn() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.needMobileRadioShutdown();
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isAnyRadioPoweredOn", e);
            e.rethrowAsRuntimeException();
            return false;
        }
    }

    @SystemApi
    public int getRadioPowerState() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getRadioPowerState(getSlotIndex(), this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
            return 2;
        } catch (RemoteException unused) {
            return 2;
        }
    }

    @SystemApi
    public void updateServiceLocation() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.updateServiceLocation();
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#updateServiceLocation", e);
        }
    }

    @SystemApi
    public boolean enableDataConnectivity() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.enableDataConnectivity();
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#enableDataConnectivity", e);
            return false;
        }
    }

    @SystemApi
    public boolean disableDataConnectivity() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.disableDataConnectivity();
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#disableDataConnectivity", e);
            return false;
        }
    }

    @SystemApi
    public boolean isDataConnectivityPossible() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isDataConnectivityPossible(getSubId(SubscriptionManager.getActiveDataSubscriptionId()));
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isDataAllowed", e);
            return false;
        }
    }

    @SystemApi
    public boolean needsOtaServiceProvisioning() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.needsOtaServiceProvisioning();
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#needsOtaServiceProvisioning", e);
            return false;
        }
    }

    public void setDataEnabled(boolean z) {
        setDataEnabled(getSubId(SubscriptionManager.getDefaultDataSubscriptionId()), z);
    }

    @SystemApi
    @Deprecated
    public void setDataEnabled(int i, boolean z) {
        try {
            Log.d(TAG, "setDataEnabled: enabled=" + z);
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.setUserDataEnabled(i, z);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#setUserDataEnabled", e);
        }
    }

    @SystemApi
    @Deprecated
    public boolean getDataEnabled() {
        return isDataEnabled();
    }

    public boolean isDataEnabled() {
        return getDataEnabled(getSubId(SubscriptionManager.getDefaultDataSubscriptionId()));
    }

    public boolean isDataRoamingEnabled() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isDataRoamingEnabled(getSubId(SubscriptionManager.getDefaultDataSubscriptionId()));
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isDataRoamingEnabled", e);
            return false;
        }
    }

    public int getCdmaRoamingMode() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getCdmaRoamingMode(getSubId());
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getCdmaRoamingMode", e);
            return -1;
        }
    }

    public boolean setCdmaRoamingMode(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.setCdmaRoamingMode(getSubId(), i);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#setCdmaRoamingMode", e);
            return false;
        }
    }

    public boolean setCdmaSubscriptionMode(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.setCdmaSubscriptionMode(getSubId(), i);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#setCdmaSubscriptionMode", e);
            return false;
        }
    }

    @SystemApi
    public void setDataRoamingEnabled(boolean z) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.setDataRoamingEnabled(getSubId(SubscriptionManager.getDefaultDataSubscriptionId()), z);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#setDataRoamingEnabled", e);
        }
    }

    @SystemApi
    @Deprecated
    public boolean getDataEnabled(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isUserDataEnabled(i);
            }
            return false;
        } catch (RemoteException | NullPointerException e) {
            Log.e(TAG, "Error calling ITelephony#isUserDataEnabled", e);
            return false;
        }
    }

    @Deprecated
    public int invokeOemRilRequestRaw(byte[] bArr, byte[] bArr2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.invokeOemRilRequestRaw(bArr, bArr2);
            }
            return -1;
        } catch (RemoteException | NullPointerException unused) {
            return -1;
        }
    }

    @SystemApi
    @Deprecated
    public void enableVideoCalling(boolean z) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.enableVideoCalling(z);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#enableVideoCalling", e);
        }
    }

    @SystemApi
    @Deprecated
    public boolean isVideoCallingEnabled() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isVideoCallingEnabled(getOpPackageName(), getAttributionTag());
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isVideoCallingEnabled", e);
            return false;
        }
    }

    public boolean canChangeDtmfToneLength() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.canChangeDtmfToneLength(this.mSubId, getOpPackageName(), getAttributionTag());
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#canChangeDtmfToneLength", e);
            return false;
        } catch (SecurityException e2) {
            Log.e(TAG, "Permission error calling ITelephony#canChangeDtmfToneLength", e2);
            return false;
        }
    }

    public boolean isWorldPhone() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isWorldPhone(this.mSubId, getOpPackageName(), getAttributionTag());
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isWorldPhone", e);
            return false;
        } catch (SecurityException e2) {
            Log.e(TAG, "Permission error calling ITelephony#isWorldPhone", e2);
            return false;
        }
    }

    @Deprecated
    public boolean isTtyModeSupported() {
        try {
            Context context = this.mContext;
            TelecomManager telecomManager = context != null ? (TelecomManager) context.getSystemService(TelecomManager.class) : null;
            if (telecomManager != null) {
                return telecomManager.isTtySupported();
            }
            return false;
        } catch (SecurityException e) {
            Log.e(TAG, "Permission error calling TelecomManager#isTtySupported", e);
            return false;
        }
    }

    public boolean isRttSupported() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isRttSupported(this.mSubId);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isRttSupported", e);
            return false;
        } catch (SecurityException e2) {
            Log.e(TAG, "Permission error calling ITelephony#isWorldPhone", e2);
            return false;
        }
    }

    public boolean isHearingAidCompatibilitySupported() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isHearingAidCompatibilitySupported();
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isHearingAidCompatibilitySupported", e);
            return false;
        } catch (SecurityException e2) {
            Log.e(TAG, "Permission error calling ITelephony#isHearingAidCompatibilitySupported", e2);
            return false;
        }
    }

    public boolean isImsRegistered(int i) {
        try {
            return getITelephony().isImsRegistered(i);
        } catch (RemoteException | NullPointerException unused) {
            return false;
        }
    }

    public boolean isImsRegistered() {
        try {
            return getITelephony().isImsRegistered(getSubId());
        } catch (RemoteException | NullPointerException unused) {
            return false;
        }
    }

    public boolean isVolteAvailable() {
        try {
            return getITelephony().isAvailable(getSubId(), 1, 0);
        } catch (RemoteException | NullPointerException unused) {
            return false;
        }
    }

    public boolean isVideoTelephonyAvailable() {
        try {
            return getITelephony().isVideoTelephonyAvailable(getSubId());
        } catch (RemoteException | NullPointerException unused) {
            return false;
        }
    }

    public boolean isWifiCallingAvailable() {
        try {
            return getITelephony().isWifiCallingAvailable(getSubId());
        } catch (RemoteException | NullPointerException unused) {
            return false;
        }
    }

    public int getImsRegTechnologyForMmTel() {
        try {
            return getITelephony().getImsRegTechnologyForMmTel(getSubId());
        } catch (RemoteException unused) {
            return -1;
        }
    }

    public void setSimOperatorNumeric(String str) {
        setSimOperatorNumericForPhone(getPhoneId(), str);
    }

    public void setSimOperatorNumericForPhone(int i, String str) {
        if (SubscriptionManager.isValidPhoneId(i)) {
            TelephonyProperties.icc_operator_numeric(updateTelephonyProperty(TelephonyProperties.icc_operator_numeric(), i, str));
        }
    }

    public void setSimOperatorName(String str) {
        setSimOperatorNameForPhone(getPhoneId(), str);
    }

    public void setSimOperatorNameForPhone(int i, String str) {
        if (SubscriptionManager.isValidPhoneId(i)) {
            TelephonyProperties.icc_operator_alpha(updateTelephonyProperty(TelephonyProperties.icc_operator_alpha(), i, str));
        }
    }

    public void setSimCountryIso(String str) {
        setSimCountryIsoForPhone(getPhoneId(), str);
    }

    public void setSimCountryIsoForPhone(int i, String str) {
        if (SubscriptionManager.isValidPhoneId(i)) {
            TelephonyProperties.icc_operator_iso_country(updateTelephonyProperty(TelephonyProperties.icc_operator_iso_country(), i, str));
        }
    }

    public void setSimState(String str) {
        setSimStateForPhone(getPhoneId(), str);
    }

    public void setSimStateForPhone(int i, String str) {
        if (SubscriptionManager.isValidPhoneId(i)) {
            TelephonyProperties.sim_state(updateTelephonyProperty(TelephonyProperties.sim_state(), i, str));
        }
    }

    @SystemApi
    public void setSimPowerState(int i) {
        setSimPowerStateForSlot(getSlotIndex(), i);
    }

    @SystemApi
    public void setSimPowerStateForSlot(int i, int i2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.setSimPowerStateForSlot(i, i2);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#setSimPowerStateForSlot", e);
        } catch (SecurityException e2) {
            Log.e(TAG, "Permission error calling ITelephony#setSimPowerStateForSlot", e2);
        }
    }

    public void setBasebandVersion(String str) {
        setBasebandVersionForPhone(getPhoneId(), str);
    }

    public void setBasebandVersionForPhone(int i, String str) {
        if (SubscriptionManager.isValidPhoneId(i)) {
            TelephonyProperties.baseband_version(updateTelephonyProperty(TelephonyProperties.baseband_version(), i, str));
        }
    }

    public String getBasebandVersion() {
        return getBasebandVersionForPhone(getPhoneId());
    }

    public String getBasebandVersionForPhone(int i) {
        return (String) getTelephonyProperty(i, TelephonyProperties.baseband_version(), "");
    }

    public void setPhoneType(int i) {
        setPhoneType(getPhoneId(), i);
    }

    public void setPhoneType(int i, int i2) {
        if (SubscriptionManager.isValidPhoneId(i)) {
            TelephonyProperties.current_active_phone(updateTelephonyProperty(TelephonyProperties.current_active_phone(), i, Integer.valueOf(i2)));
        }
    }

    public String getOtaSpNumberSchema(String str) {
        return getOtaSpNumberSchemaForPhone(getPhoneId(), str);
    }

    public String getOtaSpNumberSchemaForPhone(int i, String str) {
        return SubscriptionManager.isValidPhoneId(i) ? (String) getTelephonyProperty(i, TelephonyProperties.otasp_num_schema(), str) : str;
    }

    public boolean getSmsReceiveCapable(boolean z) {
        return getSmsReceiveCapableForPhone(getPhoneId(), z);
    }

    public boolean getSmsReceiveCapableForPhone(int i, boolean z) {
        return SubscriptionManager.isValidPhoneId(i) ? ((Boolean) getTelephonyProperty(i, TelephonyProperties.sms_receive(), Boolean.valueOf(z))).booleanValue() : z;
    }

    public boolean getSmsSendCapable(boolean z) {
        return getSmsSendCapableForPhone(getPhoneId(), z);
    }

    public boolean getSmsSendCapableForPhone(int i, boolean z) {
        return SubscriptionManager.isValidPhoneId(i) ? ((Boolean) getTelephonyProperty(i, TelephonyProperties.sms_send(), Boolean.valueOf(z))).booleanValue() : z;
    }

    @SystemApi
    public ComponentName getAndUpdateDefaultRespondViaMessageApplication() {
        return SmsApplication.getDefaultRespondViaMessageApplication(this.mContext, true);
    }

    @SystemApi
    public ComponentName getDefaultRespondViaMessageApplication() {
        return SmsApplication.getDefaultRespondViaMessageApplication(this.mContext, false);
    }

    public void setNetworkOperatorName(String str) {
        setNetworkOperatorNameForPhone(getPhoneId(), str);
    }

    public void setNetworkOperatorNameForPhone(int i, String str) {
        if (SubscriptionManager.isValidPhoneId(i)) {
            TelephonyProperties.operator_alpha(updateTelephonyProperty(TelephonyProperties.operator_alpha(), i, str));
        }
    }

    public void setNetworkOperatorNumeric(String str) {
        setNetworkOperatorNumericForPhone(getPhoneId(), str);
    }

    public void setNetworkOperatorNumericForPhone(int i, String str) {
        if (SubscriptionManager.isValidPhoneId(i)) {
            TelephonyProperties.operator_numeric(updateTelephonyProperty(TelephonyProperties.operator_numeric(), i, str));
        }
    }

    public void setNetworkRoaming(boolean z) {
        setNetworkRoamingForPhone(getPhoneId(), z);
    }

    public void setNetworkRoamingForPhone(int i, boolean z) {
        if (SubscriptionManager.isValidPhoneId(i)) {
            TelephonyProperties.operator_is_roaming(updateTelephonyProperty(TelephonyProperties.operator_is_roaming(), i, Boolean.valueOf(z)));
        }
    }

    public void setDataNetworkType(int i) {
        setDataNetworkTypeForPhone(getPhoneId(SubscriptionManager.getDefaultDataSubscriptionId()), i);
    }

    public void setDataNetworkTypeForPhone(int i, int i2) {
        if (SubscriptionManager.isValidPhoneId(i)) {
            TelephonyProperties.data_network_type(updateTelephonyProperty(TelephonyProperties.data_network_type(), i, ServiceState.rilRadioTechnologyToString(i2)));
        }
    }

    public int getSubIdForPhoneAccount(PhoneAccount phoneAccount) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getSubIdForPhoneAccount(phoneAccount);
            }
            return -1;
        } catch (RemoteException unused) {
            return -1;
        }
    }

    public PhoneAccountHandle getPhoneAccountHandleForSubscriptionId(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getPhoneAccountHandleForSubscriptionId(i);
            }
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public int getSubscriptionId(PhoneAccountHandle phoneAccountHandle) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getSubIdForPhoneAccountHandle(phoneAccountHandle, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "getSubscriptionId RemoteException", e);
            e.rethrowAsRuntimeException();
            return -1;
        }
    }

    public void factoryReset(int i) {
        try {
            Log.d(TAG, "factoryReset: subId=" + i);
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.factoryReset(i);
            }
        } catch (RemoteException unused) {
        }
    }

    @SystemApi
    public void resetSettings() {
        try {
            Log.d(TAG, "resetSettings: subId=" + getSubId());
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.factoryReset(getSubId());
            }
        } catch (RemoteException unused) {
        }
    }

    @SystemApi
    public Locale getSimLocale() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return null;
            }
            String simLocaleForSubscriber = iTelephony.getSimLocaleForSubscriber(getSubId());
            if (TextUtils.isEmpty(simLocaleForSubscriber)) {
                return null;
            }
            return Locale.forLanguageTag(simLocaleForSubscriber);
        } catch (RemoteException unused) {
            return null;
        }
    }

    public String getLocaleFromDefaultSim() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getSimLocaleForSubscriber(getSubId());
            }
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public void requestModemActivityInfo(ResultReceiver resultReceiver) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.requestModemActivityInfo(resultReceiver);
                return;
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getModemActivityInfo", e);
        }
        resultReceiver.send(0, null);
    }

    public ServiceState getServiceState() {
        return getServiceStateForSubscriber(getSubId());
    }

    public ServiceState getServiceStateForSubscriber(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getServiceStateForSubscriber(i, getOpPackageName(), getAttributionTag());
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getServiceStateForSubscriber", e);
            return null;
        } catch (NullPointerException unused) {
            AnomalyReporter.reportAnomaly(UUID.fromString("a3ab0b9d-f2aa-4baf-911d-7096c0d4645a"), "getServiceStateForSubscriber " + i + " NPE");
            return null;
        }
    }

    public Uri getVoicemailRingtoneUri(PhoneAccountHandle phoneAccountHandle) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getVoicemailRingtoneUri(phoneAccountHandle);
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getVoicemailRingtoneUri", e);
            return null;
        }
    }

    public void setVoicemailRingtoneUri(PhoneAccountHandle phoneAccountHandle, Uri uri) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.setVoicemailRingtoneUri(getOpPackageName(), phoneAccountHandle, uri);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#setVoicemailRingtoneUri", e);
        }
    }

    public boolean isVoicemailVibrationEnabled(PhoneAccountHandle phoneAccountHandle) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isVoicemailVibrationEnabled(phoneAccountHandle);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isVoicemailVibrationEnabled", e);
            return false;
        }
    }

    public void setVoicemailVibrationEnabled(PhoneAccountHandle phoneAccountHandle, boolean z) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.setVoicemailVibrationEnabled(getOpPackageName(), phoneAccountHandle, z);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isVoicemailVibrationEnabled", e);
        }
    }

    public int getSimCarrierId() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getSubscriptionCarrierId(getSubId());
            }
            return -1;
        } catch (RemoteException unused) {
            return -1;
        }
    }

    public CharSequence getSimCarrierIdName() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getSubscriptionCarrierName(getSubId());
            }
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public int getSimSpecificCarrierId() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getSubscriptionSpecificCarrierId(getSubId());
            }
            return -1;
        } catch (RemoteException unused) {
            return -1;
        }
    }

    public CharSequence getSimSpecificCarrierIdName() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getSubscriptionSpecificCarrierName(getSubId());
            }
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public int getCarrierIdFromSimMccMnc() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getCarrierIdFromMccMnc(getSlotIndex(), getSimOperator(), true);
            }
            return -1;
        } catch (RemoteException unused) {
            return -1;
        }
    }

    public int getCarrierIdFromMccMnc(String str) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getCarrierIdFromMccMnc(getSlotIndex(), str, false);
            }
            return -1;
        } catch (RemoteException unused) {
            return -1;
        }
    }

    public List<String> getCertsFromCarrierPrivilegeAccessRules() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getCertsFromCarrierPrivilegeAccessRules(getSubId());
            }
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    @SystemApi
    public String getAidForAppType(int i) {
        return getAidForAppType(getSubId(), i);
    }

    public String getAidForAppType(int i, int i2) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getAidForAppType(i, i2);
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getAidForAppType", e);
            return null;
        }
    }

    public String getEsn() {
        return getEsn(getSubId());
    }

    public String getEsn(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getEsn(i);
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getEsn", e);
            return null;
        }
    }

    @SystemApi
    public String getCdmaPrlVersion() {
        return getCdmaPrlVersion(getSubId());
    }

    public String getCdmaPrlVersion(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getCdmaPrlVersion(i);
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getCdmaPrlVersion", e);
            return null;
        }
    }

    @SystemApi
    public List<TelephonyHistogram> getTelephonyHistograms() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getTelephonyHistograms();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getTelephonyHistograms", e);
            return null;
        }
    }

    @SystemApi
    public int setAllowedCarriers(int i, List<CarrierIdentifier> list) {
        if (list != null && SubscriptionManager.isValidPhoneId(i) && setCarrierRestrictionRules(CarrierRestrictionRules.newBuilder().setAllowedCarriers(list).setDefaultCarrierRestriction(0).build()) == 0) {
            return list.size();
        }
        return -1;
    }

    @SystemApi
    public int setCarrierRestrictionRules(CarrierRestrictionRules carrierRestrictionRules) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.setAllowedCarriers(carrierRestrictionRules);
            }
            return 2;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#setAllowedCarriers", e);
            return 2;
        } catch (NullPointerException e2) {
            Log.e(TAG, "Error calling ITelephony#setAllowedCarriers", e2);
            return 2;
        }
    }

    @SystemApi
    @Deprecated
    public List<CarrierIdentifier> getAllowedCarriers(int i) {
        CarrierRestrictionRules carrierRestrictionRules;
        if (SubscriptionManager.isValidPhoneId(i) && (carrierRestrictionRules = getCarrierRestrictionRules()) != null) {
            return carrierRestrictionRules.getAllowedCarriers();
        }
        return new ArrayList(0);
    }

    @SystemApi
    public CarrierRestrictionRules getCarrierRestrictionRules() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getAllowedCarriers();
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getAllowedCarriers", e);
            return null;
        } catch (NullPointerException e2) {
            Log.e(TAG, "Error calling ITelephony#getAllowedCarriers", e2);
            return null;
        }
    }

    @SystemApi
    public void setCarrierDataEnabled(boolean z) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.carrierActionSetMeteredApnsEnabled(getSubId(SubscriptionManager.getDefaultDataSubscriptionId()), z);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#setCarrierDataEnabled", e);
        }
    }

    @SystemApi
    public void setRadioEnabled(boolean z) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.carrierActionSetRadioEnabled(getSubId(SubscriptionManager.getDefaultDataSubscriptionId()), z);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#carrierActionSetRadioEnabled", e);
        }
    }

    @SystemApi
    public void reportDefaultNetworkStatus(boolean z) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.carrierActionReportDefaultNetworkStatus(getSubId(SubscriptionManager.getDefaultDataSubscriptionId()), z);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#carrierActionReportDefaultNetworkStatus", e);
        }
    }

    @SystemApi
    public void resetAllCarrierActions() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.carrierActionResetAll(getSubId(SubscriptionManager.getDefaultDataSubscriptionId()));
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#carrierActionResetAll", e);
        }
    }

    public void setPolicyDataEnabled(boolean z) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.setPolicyDataEnabled(z, getSubId());
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#setPolicyDataEnabled", e);
        }
    }

    public List<ClientRequestStats> getClientRequestStats(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getClientRequestStats(getOpPackageName(), getAttributionTag(), i);
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getClientRequestStats", e);
            return null;
        }
    }

    @SystemApi
    public boolean getEmergencyCallbackMode() {
        return getEmergencyCallbackMode(getSubId());
    }

    public boolean getEmergencyCallbackMode(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony == null) {
                return false;
            }
            return iTelephony.getEmergencyCallbackMode(i);
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getEmergencyCallbackMode", e);
            return false;
        }
    }

    public boolean isManualNetworkSelectionAllowed() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isManualNetworkSelectionAllowed(getSubId());
            }
            return true;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#isManualNetworkSelectionAllowed", e);
            return true;
        }
    }

    public void setAlwaysReportSignalStrength(boolean z) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.setAlwaysReportSignalStrength(getSubId(), z);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "setAlwaysReportSignalStrength RemoteException", e);
            e.rethrowAsRuntimeException();
        }
    }

    public SignalStrength getSignalStrength() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getSignalStrength(getSubId());
            }
            return null;
        } catch (RemoteException e) {
            Log.e(TAG, "Error calling ITelephony#getSignalStrength", e);
            return null;
        }
    }

    @SystemApi
    public boolean isDataConnectionAllowed() {
        try {
            int subId = getSubId(SubscriptionManager.getDefaultDataSubscriptionId());
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isDataEnabled(subId);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "Error isDataConnectionAllowed", e);
            return false;
        }
    }

    public boolean isDataCapable() {
        Context context = this.mContext;
        if (context == null) {
            return true;
        }
        return context.getResources().getBoolean(R.bool.config_mobile_data_capable);
    }

    @Deprecated
    public void setCarrierTestOverride(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.setCarrierTestOverride(getSubId(), str, str2, str3, str4, str5, str6, str7, null, null);
            }
        } catch (RemoteException unused) {
        }
    }

    public void setCarrierTestOverride(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.setCarrierTestOverride(getSubId(), str, str2, str3, str4, str5, str6, str7, str8, str9);
            }
        } catch (RemoteException unused) {
        }
    }

    public int getCarrierIdListVersion() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getCarrierIdListVersion(getSubId());
            }
            return -1;
        } catch (RemoteException unused) {
            return -1;
        }
    }

    public int getNumberOfModemsWithSimultaneousDataConnections() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getNumberOfModemsWithSimultaneousDataConnections(getSubId(), getOpPackageName(), getAttributionTag());
            }
            return 0;
        } catch (RemoteException unused) {
            return 0;
        }
    }

    @SystemApi
    public boolean setOpportunisticNetworkState(boolean z) {
        Context context = this.mContext;
        String opPackageName = context != null ? context.getOpPackageName() : "<unknown>";
        try {
            IOns iOns = getIOns();
            if (iOns != null) {
                return iOns.setEnable(z, opPackageName);
            }
            return false;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "enableOpportunisticNetwork RemoteException", e);
            return false;
        }
    }

    @SystemApi
    public boolean isOpportunisticNetworkEnabled() {
        Context context = this.mContext;
        String opPackageName = context != null ? context.getOpPackageName() : "<unknown>";
        try {
            IOns iOns = getIOns();
            if (iOns != null) {
                return iOns.isEnabled(opPackageName);
            }
            return false;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "enableOpportunisticNetwork RemoteException", e);
            return false;
        }
    }

    @SystemApi
    public long getSupportedRadioAccessFamily() {
        try {
            if (getITelephony() != null) {
                return r2.getRadioAccessFamily(getSlotIndex(), getOpPackageName());
            }
            return 0L;
        } catch (RemoteException | NullPointerException unused) {
            return 0L;
        }
    }

    @SystemApi
    public void notifyOtaEmergencyNumberDbInstalled() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.notifyOtaEmergencyNumberDbInstalled();
                return;
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException e) {
            Log.e(TAG, "notifyOtaEmergencyNumberDatabaseInstalled RemoteException", e);
            e.rethrowAsRuntimeException();
        }
    }

    @SystemApi
    public void updateOtaEmergencyNumberDbFilePath(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.updateOtaEmergencyNumberDbFilePath(parcelFileDescriptor);
                return;
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException e) {
            Log.e(TAG, "updateOtaEmergencyNumberDbFilePath RemoteException", e);
            e.rethrowAsRuntimeException();
        }
    }

    @SystemApi
    public void resetOtaEmergencyNumberDbFilePath() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.resetOtaEmergencyNumberDbFilePath();
                return;
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException e) {
            Log.e(TAG, "resetOtaEmergencyNumberDbFilePath RemoteException", e);
            e.rethrowAsRuntimeException();
        }
    }

    @SystemApi
    public boolean isEmergencyAssistanceEnabled() {
        this.mContext.enforceCallingOrSelfPermission(Manifest.permission.READ_PRIVILEGED_PHONE_STATE, "isEmergencyAssistanceEnabled");
        return true;
    }

    public Map<Integer, List<EmergencyNumber>> getEmergencyNumberList() {
        HashMap map = new HashMap();
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getEmergencyNumberList(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException e) {
            Log.e(TAG, "getEmergencyNumberList RemoteException", e);
            e.rethrowAsRuntimeException();
            return map;
        }
    }

    public Map<Integer, List<EmergencyNumber>> getEmergencyNumberList(int i) {
        HashMap map = new HashMap();
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                Map<Integer, List<EmergencyNumber>> emergencyNumberList = iTelephony.getEmergencyNumberList(this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
                if (emergencyNumberList != null) {
                    Iterator<Integer> it = emergencyNumberList.keySet().iterator();
                    while (it.hasNext()) {
                        List<EmergencyNumber> list = emergencyNumberList.get(it.next());
                        for (EmergencyNumber emergencyNumber : list) {
                            if (!emergencyNumber.isInEmergencyServiceCategories(i)) {
                                list.remove(emergencyNumber);
                            }
                        }
                    }
                }
                return emergencyNumberList;
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException e) {
            Log.e(TAG, "getEmergencyNumberList with Categories RemoteException", e);
            e.rethrowAsRuntimeException();
            return map;
        }
    }

    public boolean isEmergencyNumber(String str) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isEmergencyNumber(str, true);
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException e) {
            Log.e(TAG, "isEmergencyNumber RemoteException", e);
            e.rethrowAsRuntimeException();
            return false;
        }
    }

    @SystemApi
    public boolean isPotentialEmergencyNumber(String str) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isEmergencyNumber(str, false);
            }
            throw new IllegalStateException("telephony service is null.");
        } catch (RemoteException e) {
            Log.e(TAG, "isEmergencyNumber RemoteException", e);
            e.rethrowAsRuntimeException();
            return false;
        }
    }

    @SystemApi
    public int getEmergencyNumberDbVersion() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getEmergencyNumberDbVersion(getSubId());
            }
            return -1;
        } catch (RemoteException e) {
            Log.e(TAG, "getEmergencyNumberDbVersion RemoteException", e);
            e.rethrowAsRuntimeException();
            return -1;
        }
    }

    public void setPreferredOpportunisticDataSubscription(int i, boolean z, Executor executor, final Consumer<Integer> consumer) {
        Context context = this.mContext;
        String opPackageName = context != null ? context.getOpPackageName() : "<unknown>";
        try {
            IOns iOns = getIOns();
            if (iOns != null) {
                iOns.setPreferredDataSubscriptionId(i, z, new AnonymousClass5(executor, consumer), opPackageName);
                return;
            }
            if (executor != null && consumer != null) {
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$TelephonyManager$hDOQpur-zHQ1Ya4JqdqLlQCs5m8
                        @Override // java.lang.Runnable
                        public final void run() {
                            TelephonyManager.lambda$setPreferredOpportunisticDataSubscription$1(consumer);
                        }
                    });
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            }
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "setPreferredDataSubscriptionId RemoteException", e);
        }
    }

    static /* synthetic */ void lambda$setPreferredOpportunisticDataSubscription$1(Consumer consumer) {
        if (Compatibility.isChangeEnabled(CALLBACK_ON_MORE_ERROR_CODE_CHANGE)) {
            consumer.accept(4);
        } else {
            consumer.accept(2);
        }
    }

    /* JADX INFO: renamed from: android.telephony.TelephonyManager$5, reason: invalid class name */
    class AnonymousClass5 extends ISetOpportunisticDataCallback.Stub {
        final /* synthetic */ Consumer val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass5(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$callback = consumer;
        }

        @Override // com.android.internal.telephony.ISetOpportunisticDataCallback
        public void onComplete(final int i) {
            if (this.val$executor == null || this.val$callback == null) {
                return;
            }
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final Consumer consumer = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$TelephonyManager$5$UjK7nKD7UQAwEJlQsDMTEn08kGk
                    @Override // java.lang.Runnable
                    public final void run() {
                        consumer.accept(Integer.valueOf(i));
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    public int getPreferredOpportunisticDataSubscription() {
        Context context = this.mContext;
        String opPackageName = context != null ? context.getOpPackageName() : "<unknown>";
        Context context2 = this.mContext;
        String attributionTag = context2 != null ? context2.getAttributionTag() : null;
        try {
            IOns iOns = getIOns();
            if (iOns != null) {
                return iOns.getPreferredDataSubscriptionId(opPackageName, attributionTag);
            }
            return -1;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getPreferredDataSubscriptionId RemoteException", e);
            return -1;
        }
    }

    public void updateAvailableNetworks(List<AvailableNetworkInfo> list, Executor executor, final Consumer<Integer> consumer) {
        IOns iOns;
        long jClearCallingIdentity;
        Context context = this.mContext;
        String opPackageName = context != null ? context.getOpPackageName() : "<unknown>";
        try {
            iOns = getIOns();
            if (iOns != null && list != null) {
                iOns.updateAvailableNetworks(list, new AnonymousClass6(executor, consumer), opPackageName);
                return;
            }
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "updateAvailableNetworks RemoteException", e);
        }
        if (executor != null && consumer != null) {
            if (iOns == null) {
                jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$TelephonyManager$vp9Jt6l4Pj9SUKtKSO1iThRFrt8
                        @Override // java.lang.Runnable
                        public final void run() {
                            TelephonyManager.lambda$updateAvailableNetworks$2(consumer);
                        }
                    });
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    return;
                } finally {
                }
            }
            jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$TelephonyManager$EHXxf8h2aA7pCXSzxt8P7AuQzIg
                    @Override // java.lang.Runnable
                    public final void run() {
                        consumer.accept(3);
                    }
                });
                return;
            } finally {
            }
            com.android.telephony.Rlog.e(TAG, "updateAvailableNetworks RemoteException", e);
        }
    }

    static /* synthetic */ void lambda$updateAvailableNetworks$2(Consumer consumer) {
        if (Compatibility.isChangeEnabled(CALLBACK_ON_MORE_ERROR_CODE_CHANGE)) {
            consumer.accept(9);
        } else {
            consumer.accept(1);
        }
    }

    /* JADX INFO: renamed from: android.telephony.TelephonyManager$6, reason: invalid class name */
    class AnonymousClass6 extends IUpdateAvailableNetworksCallback.Stub {
        final /* synthetic */ Consumer val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass6(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$callback = consumer;
        }

        @Override // com.android.internal.telephony.IUpdateAvailableNetworksCallback
        public void onComplete(final int i) {
            if (this.val$executor == null || this.val$callback == null) {
                return;
            }
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                Executor executor = this.val$executor;
                final Consumer consumer = this.val$callback;
                executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$TelephonyManager$6$pAGi7nptkgSKijqG3Qvsxo0yl74
                    @Override // java.lang.Runnable
                    public final void run() {
                        consumer.accept(Integer.valueOf(i));
                    }
                });
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    @SystemApi
    public boolean enableModemForSlot(int i, boolean z) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.enableModemForSlot(i, z);
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "enableModem RemoteException", e);
            return false;
        }
    }

    public boolean isModemEnabledForSlot(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isModemEnabledForSlot(i, this.mContext.getOpPackageName(), this.mContext.getAttributionTag());
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "enableModem RemoteException", e);
            return false;
        }
    }

    @SystemApi
    public void setMultiSimCarrierRestriction(boolean z) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.setMultiSimCarrierRestriction(z);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "setMultiSimCarrierRestriction RemoteException", e);
        }
    }

    public int isMultiSimSupported() {
        if (getSupportedModemCount() < 2) {
            return 1;
        }
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isMultiSimSupported(getOpPackageName(), getAttributionTag());
            }
        } catch (RemoteException e) {
            Log.e(TAG, "isMultiSimSupported RemoteException", e);
        }
        return 1;
    }

    public void switchMultiSimConfig(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.switchMultiSimConfig(i);
            }
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "switchMultiSimConfig RemoteException", e);
        }
    }

    public boolean doesSwitchMultiSimConfigTriggerReboot() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.doesSwitchMultiSimConfigTriggerReboot(getSubId(), getOpPackageName(), getAttributionTag());
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "doesSwitchMultiSimConfigTriggerReboot RemoteException", e);
            return false;
        }
    }

    public Pair<Integer, Integer> getRadioHalVersion() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                int radioHalVersion = iTelephony.getRadioHalVersion();
                if (radioHalVersion == -1) {
                    return new Pair<>(-1, -1);
                }
                return new Pair<>(Integer.valueOf(radioHalVersion / 100), Integer.valueOf(radioHalVersion % 100));
            }
        } catch (RemoteException e) {
            Log.e(TAG, "getRadioHalVersion() RemoteException", e);
        }
        return new Pair<>(-1, -1);
    }

    @SystemApi
    public int getCarrierPrivilegeStatus(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getCarrierPrivilegeStatusForUid(getSubId(), i);
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "getCarrierPrivilegeStatus RemoteException", e);
            return 0;
        }
    }

    public List<ApnSetting> getDevicePolicyOverrideApns(Context context) {
        Cursor cursorQuery = context.getContentResolver().query(Telephony.Carriers.DPC_URI, null, null, null, null);
        try {
            if (cursorQuery == null) {
                List<ApnSetting> listEmptyList = Collections.emptyList();
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return listEmptyList;
            }
            ArrayList arrayList = new ArrayList();
            cursorQuery.moveToPosition(-1);
            while (cursorQuery.moveToNext()) {
                arrayList.add(ApnSetting.makeApnSetting(cursorQuery));
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public int addDevicePolicyOverrideApn(Context context, ApnSetting apnSetting) {
        Uri uriInsert = context.getContentResolver().insert(Telephony.Carriers.DPC_URI, apnSetting.toContentValues());
        if (uriInsert != null) {
            try {
                return Integer.parseInt(uriInsert.getLastPathSegment());
            } catch (NumberFormatException unused) {
                com.android.telephony.Rlog.e(TAG, "Failed to parse inserted override APN id: " + uriInsert.getLastPathSegment());
            }
        }
        return -1;
    }

    public boolean modifyDevicePolicyOverrideApn(Context context, int i, ApnSetting apnSetting) {
        return context.getContentResolver().update(Uri.withAppendedPath(Telephony.Carriers.DPC_URI, Integer.toString(i)), apnSetting.toContentValues(), null, null) > 0;
    }

    @SystemApi
    public boolean isDataEnabledForApn(int i) {
        Context context = this.mContext;
        String opPackageName = context != null ? context.getOpPackageName() : "<unknown>";
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isDataEnabledForApn(i, getSubId(), opPackageName);
            }
            return false;
        } catch (RemoteException e) {
            if (this.isSystemProcess()) {
                return false;
            }
            e.rethrowAsRuntimeException();
            return false;
        }
    }

    @SystemApi
    public boolean isApnMetered(int i) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isApnMetered(i, getSubId());
            }
            return true;
        } catch (RemoteException e) {
            if (this.isSystemProcess()) {
                return true;
            }
            e.rethrowAsRuntimeException();
            return true;
        }
    }

    @SystemApi
    public void setSystemSelectionChannels(List<RadioAccessSpecifier> list, Executor executor, Consumer<Boolean> consumer) {
        Objects.requireNonNull(list, "Specifiers must not be null.");
        Objects.requireNonNull(executor, "Executor must not be null.");
        Objects.requireNonNull(consumer, "Callback must not be null.");
        setSystemSelectionChannelsInternal(list, executor, consumer);
    }

    @SystemApi
    public void setSystemSelectionChannels(List<RadioAccessSpecifier> list) {
        Objects.requireNonNull(list, "Specifiers must not be null.");
        setSystemSelectionChannelsInternal(list, null, null);
    }

    /* JADX INFO: renamed from: android.telephony.TelephonyManager$7, reason: invalid class name */
    class AnonymousClass7 extends IBooleanConsumer.Stub {
        final /* synthetic */ Consumer val$callback;
        final /* synthetic */ Executor val$executor;

        AnonymousClass7(Executor executor, Consumer consumer) {
            this.val$executor = executor;
            this.val$callback = consumer;
        }

        @Override // com.android.internal.telephony.IBooleanConsumer
        public void accept(final boolean z) {
            Executor executor = this.val$executor;
            final Consumer consumer = this.val$callback;
            executor.execute(new Runnable() { // from class: android.telephony.-$$Lambda$TelephonyManager$7$pfpM9jkeymjeUAv4OpFSqMw74EE
                @Override // java.lang.Runnable
                public final void run() {
                    consumer.accept(Boolean.valueOf(z));
                }
            });
        }
    }

    private void setSystemSelectionChannelsInternal(List<RadioAccessSpecifier> list, Executor executor, Consumer<Boolean> consumer) {
        AnonymousClass7 anonymousClass7 = consumer == null ? null : new AnonymousClass7(executor, consumer);
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.setSystemSelectionChannels(list, getSubId(), anonymousClass7);
            }
        } catch (RemoteException e) {
            if (isSystemProcess()) {
                return;
            }
            e.rethrowAsRuntimeException();
        }
    }

    @SystemApi
    public boolean matchesCurrentSimOperator(String str, int i, String str2) {
        ITelephony iTelephony;
        try {
            if (str.equals(getSimOperator()) && (iTelephony = getITelephony()) != null) {
                return iTelephony.isMvnoMatched(getSubId(), i, str2);
            }
        } catch (RemoteException e) {
            if (!this.isSystemProcess()) {
                e.rethrowAsRuntimeException();
            }
        }
        return false;
    }

    public CallForwardingInfo getCallForwarding(int i) {
        if (i < 0 || i > 5) {
            throw new IllegalArgumentException("callForwardingReason is out of range");
        }
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getCallForwarding(getSubId(), i);
            }
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getCallForwarding RemoteException", e);
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "getCallForwarding NPE", e2);
        }
        return new CallForwardingInfo(3, 0, null, 0);
    }

    public boolean setCallForwarding(CallForwardingInfo callForwardingInfo) {
        if (callForwardingInfo == null) {
            throw new IllegalArgumentException("callForwardingInfo is null");
        }
        int status = callForwardingInfo.getStatus();
        if (status != 1 && status != 0) {
            throw new IllegalArgumentException("callForwardingStatus is neither active nor inactive");
        }
        int reason = callForwardingInfo.getReason();
        if (reason < 0 || reason > 5) {
            throw new IllegalArgumentException("callForwardingReason is out of range");
        }
        if (callForwardingInfo.getNumber() == null) {
            throw new IllegalArgumentException("callForwarding number is null");
        }
        if (callForwardingInfo.getTimeoutSeconds() <= 0) {
            throw new IllegalArgumentException("callForwarding timeout isn't positive");
        }
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.setCallForwarding(getSubId(), callForwardingInfo);
            }
            return false;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "setCallForwarding RemoteException", e);
            return false;
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "setCallForwarding NPE", e2);
            return false;
        }
    }

    public int getCallWaitingStatus() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.getCallWaitingStatus(getSubId());
            }
            return 3;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "getCallWaitingStatus RemoteException", e);
            return 3;
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "getCallWaitingStatus NPE", e2);
            return 3;
        }
    }

    public boolean setCallWaitingStatus(boolean z) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.setCallWaitingStatus(getSubId(), z);
            }
            return false;
        } catch (RemoteException e) {
            com.android.telephony.Rlog.e(TAG, "setCallWaitingStatus RemoteException", e);
            return false;
        } catch (NullPointerException e2) {
            com.android.telephony.Rlog.e(TAG, "setCallWaitingStatus NPE", e2);
            return false;
        }
    }

    public boolean setDataAllowedDuringVoiceCall(boolean z) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.setDataAllowedDuringVoiceCall(getSubId(), z);
            }
            return false;
        } catch (RemoteException e) {
            if (this.isSystemProcess()) {
                return false;
            }
            e.rethrowAsRuntimeException();
            return false;
        }
    }

    public boolean isDataAllowedInVoiceCall() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isDataAllowedInVoiceCall(getSubId());
            }
            return false;
        } catch (RemoteException e) {
            if (this.isSystemProcess()) {
                return false;
            }
            e.rethrowAsRuntimeException();
            return false;
        }
    }

    public boolean setAlwaysAllowMmsData(boolean z) {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.setAlwaysAllowMmsData(getSubId(), z);
            }
            return false;
        } catch (RemoteException e) {
            if (this.isSystemProcess()) {
                return false;
            }
            e.rethrowAsRuntimeException();
            return false;
        }
    }

    @SystemApi
    public boolean isIccLockEnabled() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.isIccLockEnabled(getSubId());
            }
            return false;
        } catch (RemoteException e) {
            Log.e(TAG, "isIccLockEnabled RemoteException", e);
            return false;
        }
    }

    public int setIccLockEnabled(boolean z, String str) {
        Preconditions.checkNotNull(str, "setIccLockEnabled password can't be null.");
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.setIccLockEnabled(getSubId(), z, str);
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "setIccLockEnabled RemoteException", e);
            return 0;
        }
    }

    public int changeIccLockPassword(String str, String str2) {
        Preconditions.checkNotNull(str, "changeIccLockPassword oldPassword can't be null.");
        Preconditions.checkNotNull(str2, "changeIccLockPassword newPassword can't be null.");
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                return iTelephony.changeIccLockPassword(getSubId(), str, str2);
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "changeIccLockPassword RemoteException", e);
            return 0;
        }
    }

    public void notifyUserActivity() {
        try {
            ITelephony iTelephony = getITelephony();
            if (iTelephony != null) {
                iTelephony.userActivity();
            }
        } catch (RemoteException e) {
            Log.w(TAG, "notifyUserActivity exception: " + e.getMessage());
        }
    }

    private static class DeathRecipient implements IBinder.DeathRecipient {
        private DeathRecipient() {
        }

        /* synthetic */ DeathRecipient(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            TelephonyManager.resetServiceCache();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void resetServiceCache() {
        synchronized (sCacheLock) {
            ISub iSub = sISub;
            if (iSub != null) {
                iSub.asBinder().unlinkToDeath(sServiceDeath, 0);
                sISub = null;
                SubscriptionManager.clearCaches();
            }
            ISms iSms = sISms;
            if (iSms != null) {
                iSms.asBinder().unlinkToDeath(sServiceDeath, 0);
                sISms = null;
            }
            IPhoneSubInfo iPhoneSubInfo = sIPhoneSubInfo;
            if (iPhoneSubInfo != null) {
                iPhoneSubInfo.asBinder().unlinkToDeath(sServiceDeath, 0);
                sIPhoneSubInfo = null;
            }
        }
    }

    static IPhoneSubInfo getSubscriberInfoService() {
        if (!sServiceHandleCacheEnabled) {
            return IPhoneSubInfo.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getPhoneSubServiceRegisterer().get());
        }
        if (sIPhoneSubInfo == null) {
            IPhoneSubInfo iPhoneSubInfoAsInterface = IPhoneSubInfo.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getPhoneSubServiceRegisterer().get());
            synchronized (sCacheLock) {
                if (sIPhoneSubInfo == null && iPhoneSubInfoAsInterface != null) {
                    try {
                        sIPhoneSubInfo = iPhoneSubInfoAsInterface;
                        iPhoneSubInfoAsInterface.asBinder().linkToDeath(sServiceDeath, 0);
                    } catch (Exception unused) {
                        sIPhoneSubInfo = null;
                    }
                }
            }
        }
        return sIPhoneSubInfo;
    }

    static ISub getSubscriptionService() {
        if (!sServiceHandleCacheEnabled) {
            return ISub.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getSubscriptionServiceRegisterer().get());
        }
        if (sISub == null) {
            ISub iSubAsInterface = ISub.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getSubscriptionServiceRegisterer().get());
            synchronized (sCacheLock) {
                if (sISub == null && iSubAsInterface != null) {
                    try {
                        sISub = iSubAsInterface;
                        iSubAsInterface.asBinder().linkToDeath(sServiceDeath, 0);
                    } catch (Exception unused) {
                        sISub = null;
                    }
                }
            }
        }
        return sISub;
    }

    static ISms getSmsService() {
        if (!sServiceHandleCacheEnabled) {
            return ISms.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getSmsServiceRegisterer().get());
        }
        if (sISms == null) {
            ISms iSmsAsInterface = ISms.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getSmsServiceRegisterer().get());
            synchronized (sCacheLock) {
                if (sISms == null && iSmsAsInterface != null) {
                    try {
                        sISms = iSmsAsInterface;
                        iSmsAsInterface.asBinder().linkToDeath(sServiceDeath, 0);
                    } catch (Exception unused) {
                        sISms = null;
                    }
                }
            }
        }
        return sISms;
    }

    public static void disableServiceHandleCaching() {
        sServiceHandleCacheEnabled = false;
    }

    public static void enableServiceHandleCaching() {
        sServiceHandleCacheEnabled = true;
    }

    public boolean canConnectTo5GInDsdsMode() {
        ITelephony iTelephony = getITelephony();
        if (iTelephony == null) {
            return true;
        }
        try {
            return iTelephony.canConnectTo5GInDsdsMode();
        } catch (RemoteException | NullPointerException unused) {
            return true;
        }
    }
}
