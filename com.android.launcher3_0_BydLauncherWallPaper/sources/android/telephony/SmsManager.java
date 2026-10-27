package android.telephony;

import android.annotation.SystemApi;
import android.app.PendingIntent;
import android.compat.Compatibility;
import android.content.Context;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.telephony.IIntegerConsumer;
import com.android.internal.telephony.ISms;
import com.android.internal.telephony.ITelephony;
import com.android.internal.telephony.SmsRawData;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final class SmsManager {
    public static final int CDMA_SMS_RECORD_LENGTH = 255;
    public static final String EXTRA_MMS_DATA = "android.telephony.extra.MMS_DATA";
    public static final String EXTRA_MMS_HTTP_STATUS = "android.telephony.extra.MMS_HTTP_STATUS";
    public static final String EXTRA_SIM_SUBSCRIPTION_ID = "android.telephony.extra.SIM_SUBSCRIPTION_ID";
    public static final String EXTRA_SMS_MESSAGE = "android.telephony.extra.SMS_MESSAGE";
    public static final String EXTRA_STATUS = "android.telephony.extra.STATUS";
    private static final long GET_TARGET_SDK_VERSION_CODE_CHANGE = 145147528;
    public static final String MMS_CONFIG_ALIAS_ENABLED = "aliasEnabled";
    public static final String MMS_CONFIG_ALIAS_MAX_CHARS = "aliasMaxChars";
    public static final String MMS_CONFIG_ALIAS_MIN_CHARS = "aliasMinChars";
    public static final String MMS_CONFIG_ALLOW_ATTACH_AUDIO = "allowAttachAudio";
    public static final String MMS_CONFIG_APPEND_TRANSACTION_ID = "enabledTransID";
    public static final String MMS_CONFIG_CLOSE_CONNECTION = "mmsCloseConnection";
    public static final String MMS_CONFIG_EMAIL_GATEWAY_NUMBER = "emailGatewayNumber";
    public static final String MMS_CONFIG_GROUP_MMS_ENABLED = "enableGroupMms";
    public static final String MMS_CONFIG_HTTP_PARAMS = "httpParams";
    public static final String MMS_CONFIG_HTTP_SOCKET_TIMEOUT = "httpSocketTimeout";
    public static final String MMS_CONFIG_MAX_IMAGE_HEIGHT = "maxImageHeight";
    public static final String MMS_CONFIG_MAX_IMAGE_WIDTH = "maxImageWidth";
    public static final String MMS_CONFIG_MAX_MESSAGE_SIZE = "maxMessageSize";
    public static final String MMS_CONFIG_MESSAGE_TEXT_MAX_SIZE = "maxMessageTextSize";
    public static final String MMS_CONFIG_MMS_DELIVERY_REPORT_ENABLED = "enableMMSDeliveryReports";
    public static final String MMS_CONFIG_MMS_ENABLED = "enabledMMS";
    public static final String MMS_CONFIG_MMS_READ_REPORT_ENABLED = "enableMMSReadReports";
    public static final String MMS_CONFIG_MULTIPART_SMS_ENABLED = "enableMultipartSMS";
    public static final String MMS_CONFIG_NAI_SUFFIX = "naiSuffix";
    public static final String MMS_CONFIG_NOTIFY_WAP_MMSC_ENABLED = "enabledNotifyWapMMSC";
    public static final String MMS_CONFIG_RECIPIENT_LIMIT = "recipientLimit";
    public static final String MMS_CONFIG_SEND_MULTIPART_SMS_AS_SEPARATE_MESSAGES = "sendMultipartSmsAsSeparateMessages";
    public static final String MMS_CONFIG_SHOW_CELL_BROADCAST_APP_LINKS = "config_cellBroadcastAppLinks";
    public static final String MMS_CONFIG_SMS_DELIVERY_REPORT_ENABLED = "enableSMSDeliveryReports";
    public static final String MMS_CONFIG_SMS_TO_MMS_TEXT_LENGTH_THRESHOLD = "smsToMmsTextLengthThreshold";
    public static final String MMS_CONFIG_SMS_TO_MMS_TEXT_THRESHOLD = "smsToMmsTextThreshold";
    public static final String MMS_CONFIG_SUBJECT_MAX_LENGTH = "maxSubjectLength";
    public static final String MMS_CONFIG_SUPPORT_HTTP_CHARSET_HEADER = "supportHttpCharsetHeader";
    public static final String MMS_CONFIG_SUPPORT_MMS_CONTENT_DISPOSITION = "supportMmsContentDisposition";
    public static final String MMS_CONFIG_UA_PROF_TAG_NAME = "uaProfTagName";
    public static final String MMS_CONFIG_UA_PROF_URL = "uaProfUrl";
    public static final String MMS_CONFIG_USER_AGENT = "userAgent";
    public static final int MMS_ERROR_CONFIGURATION_ERROR = 7;
    public static final int MMS_ERROR_HTTP_FAILURE = 4;
    public static final int MMS_ERROR_INVALID_APN = 2;
    public static final int MMS_ERROR_IO_ERROR = 5;
    public static final int MMS_ERROR_NO_DATA_NETWORK = 8;
    public static final int MMS_ERROR_RETRY = 6;
    public static final int MMS_ERROR_UNABLE_CONNECT_MMS = 3;
    public static final int MMS_ERROR_UNSPECIFIED = 1;

    @SystemApi
    public static final int PREMIUM_SMS_CONSENT_ALWAYS_ALLOW = 3;

    @SystemApi
    public static final int PREMIUM_SMS_CONSENT_ASK_USER = 1;

    @SystemApi
    public static final int PREMIUM_SMS_CONSENT_NEVER_ALLOW = 2;

    @SystemApi
    public static final int PREMIUM_SMS_CONSENT_UNKNOWN = 0;
    public static final String REGEX_PREFIX_DELIMITER = ",";
    public static final int RESULT_BLUETOOTH_DISCONNECTED = 27;
    public static final int RESULT_CANCELLED = 23;
    public static final int RESULT_ENCODING_ERROR = 18;
    public static final int RESULT_ERROR_FDN_CHECK_FAILURE = 6;
    public static final int RESULT_ERROR_GENERIC_FAILURE = 1;
    public static final int RESULT_ERROR_LIMIT_EXCEEDED = 5;
    public static final int RESULT_ERROR_NONE = 0;
    public static final int RESULT_ERROR_NO_SERVICE = 4;
    public static final int RESULT_ERROR_NULL_PDU = 3;
    public static final int RESULT_ERROR_RADIO_OFF = 2;
    public static final int RESULT_ERROR_SHORT_CODE_NEVER_ALLOWED = 8;
    public static final int RESULT_ERROR_SHORT_CODE_NOT_ALLOWED = 7;
    public static final int RESULT_INTERNAL_ERROR = 21;
    public static final int RESULT_INVALID_ARGUMENTS = 11;
    public static final int RESULT_INVALID_BLUETOOTH_ADDRESS = 26;
    public static final int RESULT_INVALID_SMSC_ADDRESS = 19;
    public static final int RESULT_INVALID_SMS_FORMAT = 14;
    public static final int RESULT_INVALID_STATE = 12;
    public static final int RESULT_MODEM_ERROR = 16;
    public static final int RESULT_NETWORK_ERROR = 17;
    public static final int RESULT_NETWORK_REJECT = 10;
    public static final int RESULT_NO_BLUETOOTH_SERVICE = 25;
    public static final int RESULT_NO_DEFAULT_SMS_APP = 32;
    public static final int RESULT_NO_MEMORY = 13;
    public static final int RESULT_NO_RESOURCES = 22;
    public static final int RESULT_OPERATION_NOT_ALLOWED = 20;
    public static final int RESULT_RADIO_NOT_AVAILABLE = 9;
    public static final int RESULT_RECEIVE_DISPATCH_FAILURE = 500;
    public static final int RESULT_RECEIVE_INJECTED_NULL_PDU = 501;
    public static final int RESULT_RECEIVE_NULL_MESSAGE_FROM_RIL = 503;
    public static final int RESULT_RECEIVE_RUNTIME_EXCEPTION = 502;
    public static final int RESULT_RECEIVE_SQL_EXCEPTION = 505;
    public static final int RESULT_RECEIVE_URI_EXCEPTION = 506;
    public static final int RESULT_RECEIVE_WHILE_ENCRYPTED = 504;
    public static final int RESULT_REMOTE_EXCEPTION = 31;
    public static final int RESULT_REQUEST_NOT_SUPPORTED = 24;
    public static final int RESULT_RIL_CANCELLED = 119;
    public static final int RESULT_RIL_ENCODING_ERR = 109;
    public static final int RESULT_RIL_INTERNAL_ERR = 113;
    public static final int RESULT_RIL_INVALID_ARGUMENTS = 104;
    public static final int RESULT_RIL_INVALID_MODEM_STATE = 115;
    public static final int RESULT_RIL_INVALID_SMSC_ADDRESS = 110;
    public static final int RESULT_RIL_INVALID_SMS_FORMAT = 107;
    public static final int RESULT_RIL_INVALID_STATE = 103;
    public static final int RESULT_RIL_MODEM_ERR = 111;
    public static final int RESULT_RIL_NETWORK_ERR = 112;
    public static final int RESULT_RIL_NETWORK_NOT_READY = 116;
    public static final int RESULT_RIL_NETWORK_REJECT = 102;
    public static final int RESULT_RIL_NO_MEMORY = 105;
    public static final int RESULT_RIL_NO_RESOURCES = 118;
    public static final int RESULT_RIL_OPERATION_NOT_ALLOWED = 117;
    public static final int RESULT_RIL_RADIO_NOT_AVAILABLE = 100;
    public static final int RESULT_RIL_REQUEST_NOT_SUPPORTED = 114;
    public static final int RESULT_RIL_REQUEST_RATE_LIMITED = 106;
    public static final int RESULT_RIL_SIM_ABSENT = 120;
    public static final int RESULT_RIL_SMS_SEND_FAIL_RETRY = 101;
    public static final int RESULT_RIL_SYSTEM_ERR = 108;
    public static final int RESULT_SMS_BLOCKED_DURING_EMERGENCY = 29;
    public static final int RESULT_SMS_SEND_RETRY_FAILED = 30;
    public static final int RESULT_STATUS_SUCCESS = 0;
    public static final int RESULT_STATUS_TIMEOUT = 1;
    public static final int RESULT_SYSTEM_ERROR = 15;
    public static final int RESULT_UNEXPECTED_EVENT_STOP_SENDING = 28;
    public static final int SMS_CATEGORY_FREE_SHORT_CODE = 1;
    public static final int SMS_CATEGORY_NOT_SHORT_CODE = 0;
    public static final int SMS_CATEGORY_POSSIBLE_PREMIUM_SHORT_CODE = 3;
    public static final int SMS_CATEGORY_PREMIUM_SHORT_CODE = 4;
    public static final int SMS_CATEGORY_STANDARD_SHORT_CODE = 2;
    public static final int SMS_MESSAGE_PERIOD_NOT_SPECIFIED = -1;
    public static final int SMS_MESSAGE_PRIORITY_NOT_SPECIFIED = -1;
    public static final int SMS_RECORD_LENGTH = 176;
    public static final int STATUS_ON_ICC_FREE = 0;
    public static final int STATUS_ON_ICC_READ = 1;
    public static final int STATUS_ON_ICC_SENT = 5;
    public static final int STATUS_ON_ICC_UNREAD = 3;
    public static final int STATUS_ON_ICC_UNSENT = 7;
    private static final String TAG = "SmsManager";
    private static final SmsManager sInstance = new SmsManager(Integer.MAX_VALUE);
    private static final Object sLockObject = new Object();
    private static final Map<Integer, SmsManager> sSubInstances = new ArrayMap();
    private int mSubId;

    public static abstract class FinancialSmsCallback {
        public abstract void onFinancialSmsMessages(CursorWindow cursorWindow);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PremiumSmsConsent {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Result {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SmsShortCodeCategory {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StatusOnIcc {
    }

    private interface SubscriptionResolverResult {
        void onFailure();

        void onSuccess(int i);
    }

    public void getSmsMessagesForFinancialApp(Bundle bundle, Executor executor, FinancialSmsCallback financialSmsCallback) {
    }

    public void sendTextMessage(String str, String str2, String str3, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        sendTextMessageInternal(str, str2, str3, pendingIntent, pendingIntent2, true, (String) null, (String) null, 0L);
    }

    public void sendTextMessage(String str, String str2, String str3, PendingIntent pendingIntent, PendingIntent pendingIntent2, long j) {
        sendTextMessageInternal(str, str2, str3, pendingIntent, pendingIntent2, true, (String) null, (String) null, j);
    }

    public void sendTextMessage(String str, String str2, String str3, PendingIntent pendingIntent, PendingIntent pendingIntent2, int i, boolean z, int i2) {
        sendTextMessageInternal(str, str2, str3, pendingIntent, pendingIntent2, true, i, z, i2);
    }

    private void sendTextMessageInternal(final String str, final String str2, final String str3, final PendingIntent pendingIntent, final PendingIntent pendingIntent2, final boolean z, final String str4, final String str5, final long j) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Invalid destinationAddress");
        }
        if (TextUtils.isEmpty(str3)) {
            throw new IllegalArgumentException("Invalid message body");
        }
        if (z) {
            resolveSubscriptionForOperation(new SubscriptionResolverResult() { // from class: android.telephony.SmsManager.1
                @Override // android.telephony.SmsManager.SubscriptionResolverResult
                public void onSuccess(int i) {
                    try {
                        SmsManager.getISmsServiceOrThrow().sendTextForSubscriber(i, str4, str5, str, str2, str3, pendingIntent, pendingIntent2, z, j);
                    } catch (RemoteException e) {
                        Log.e(SmsManager.TAG, "sendTextMessageInternal: Couldn't send SMS, exception - " + e.getMessage() + " id: " + j);
                        SmsManager.notifySmsError(pendingIntent, 31);
                    }
                }

                @Override // android.telephony.SmsManager.SubscriptionResolverResult
                public void onFailure() {
                    SmsManager.notifySmsError(pendingIntent, 32);
                }
            });
            return;
        }
        try {
            getISmsServiceOrThrow().sendTextForSubscriber(getSubscriptionId(), str4, str5, str, str2, str3, pendingIntent, pendingIntent2, z, j);
        } catch (RemoteException e) {
            Log.e(TAG, "sendTextMessageInternal (no persist): Couldn't send SMS, exception - " + e.getMessage() + " id: " + j);
            notifySmsError(pendingIntent, 31);
        }
    }

    public void sendTextMessageWithoutPersisting(String str, String str2, String str3, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        sendTextMessageInternal(str, str2, str3, pendingIntent, pendingIntent2, false, (String) null, (String) null, 0L);
    }

    private void sendTextMessageInternal(final String str, final String str2, final String str3, final PendingIntent pendingIntent, final PendingIntent pendingIntent2, final boolean z, int i, final boolean z2, int i2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Invalid destinationAddress");
        }
        if (TextUtils.isEmpty(str3)) {
            throw new IllegalArgumentException("Invalid message body");
        }
        int i3 = (i < 0 || i > 3) ? -1 : i;
        int i4 = (i2 < 5 || i2 > 635040) ? -1 : i2;
        if (z) {
            final int i5 = i3;
            final int i6 = i4;
            resolveSubscriptionForOperation(new SubscriptionResolverResult() { // from class: android.telephony.SmsManager.2
                @Override // android.telephony.SmsManager.SubscriptionResolverResult
                public void onSuccess(int i7) {
                    try {
                        ISms iSmsServiceOrThrow = SmsManager.getISmsServiceOrThrow();
                        if (iSmsServiceOrThrow != null) {
                            iSmsServiceOrThrow.sendTextForSubscriberWithOptions(i7, null, null, str, str2, str3, pendingIntent, pendingIntent2, z, i5, z2, i6);
                        }
                    } catch (RemoteException e) {
                        Log.e(SmsManager.TAG, "sendTextMessageInternal: Couldn't send SMS, exception - " + e.getMessage());
                        SmsManager.notifySmsError(pendingIntent, 31);
                    }
                }

                @Override // android.telephony.SmsManager.SubscriptionResolverResult
                public void onFailure() {
                    SmsManager.notifySmsError(pendingIntent, 32);
                }
            });
            return;
        }
        try {
            ISms iSmsServiceOrThrow = getISmsServiceOrThrow();
            if (iSmsServiceOrThrow != null) {
                iSmsServiceOrThrow.sendTextForSubscriberWithOptions(getSubscriptionId(), null, null, str, str2, str3, pendingIntent, pendingIntent2, z, i3, z2, i4);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "sendTextMessageInternal(no persist): Couldn't send SMS, exception - " + e.getMessage());
            notifySmsError(pendingIntent, 31);
        }
    }

    public void sendTextMessageWithoutPersisting(String str, String str2, String str3, PendingIntent pendingIntent, PendingIntent pendingIntent2, int i, boolean z, int i2) {
        sendTextMessageInternal(str, str2, str3, pendingIntent, pendingIntent2, false, i, z, i2);
    }

    public void injectSmsPdu(byte[] bArr, String str, PendingIntent pendingIntent) {
        if (!str.equals("3gpp") && !str.equals("3gpp2")) {
            throw new IllegalArgumentException("Invalid pdu format. format must be either 3gpp or 3gpp2");
        }
        try {
            ISms smsService = TelephonyManager.getSmsService();
            if (smsService != null) {
                smsService.injectSmsPduForSubscriber(getSubscriptionId(), bArr, str, pendingIntent);
            }
        } catch (RemoteException unused) {
            if (pendingIntent != null) {
                try {
                    pendingIntent.send(31);
                } catch (PendingIntent.CanceledException unused2) {
                }
            }
        }
    }

    public ArrayList<String> divideMessage(String str) {
        if (str == null) {
            throw new IllegalArgumentException("text is null");
        }
        return SmsMessage.fragmentText(str, getSubscriptionId());
    }

    public void sendMultipartTextMessage(String str, String str2, ArrayList<String> arrayList, ArrayList<PendingIntent> arrayList2, ArrayList<PendingIntent> arrayList3) {
        sendMultipartTextMessageInternal(str, str2, (List<String>) arrayList, (List<PendingIntent>) arrayList2, (List<PendingIntent>) arrayList3, true, (String) null, (String) null, 0L);
    }

    public void sendMultipartTextMessage(String str, String str2, List<String> list, List<PendingIntent> list2, List<PendingIntent> list3, long j) {
        sendMultipartTextMessageInternal(str, str2, list, list2, list3, true, (String) null, (String) null, j);
    }

    public void sendMultipartTextMessage(String str, String str2, List<String> list, List<PendingIntent> list2, List<PendingIntent> list3, String str3, String str4) {
        sendMultipartTextMessageInternal(str, str2, list, list2, list3, true, str3, str4, 0L);
    }

    private void sendMultipartTextMessageInternal(final String str, final String str2, final List<String> list, final List<PendingIntent> list2, final List<PendingIntent> list3, final boolean z, final String str3, final String str4, final long j) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Invalid destinationAddress");
        }
        if (list == null || list.size() < 1) {
            throw new IllegalArgumentException("Invalid message body");
        }
        if (list.size() <= 1) {
            PendingIntent pendingIntent = null;
            PendingIntent pendingIntent2 = (list2 == null || list2.size() <= 0) ? null : list2.get(0);
            if (list3 != null && list3.size() > 0) {
                pendingIntent = list3.get(0);
            }
            sendTextMessageInternal(str, str2, list.get(0), pendingIntent2, pendingIntent, true, str3, str4, j);
            return;
        }
        if (z) {
            resolveSubscriptionForOperation(new SubscriptionResolverResult() { // from class: android.telephony.SmsManager.3
                @Override // android.telephony.SmsManager.SubscriptionResolverResult
                public void onSuccess(int i) {
                    try {
                        SmsManager.getISmsServiceOrThrow().sendMultipartTextForSubscriber(i, str3, str4, str, str2, list, list2, list3, z, j);
                    } catch (RemoteException e) {
                        Log.e(SmsManager.TAG, "sendMultipartTextMessageInternal: Couldn't send SMS - " + e.getMessage() + " id: " + j);
                        SmsManager.notifySmsError((List<PendingIntent>) list2, 31);
                    }
                }

                @Override // android.telephony.SmsManager.SubscriptionResolverResult
                public void onFailure() {
                    SmsManager.notifySmsError((List<PendingIntent>) list2, 32);
                }
            });
            return;
        }
        try {
            ISms iSmsServiceOrThrow = getISmsServiceOrThrow();
            if (iSmsServiceOrThrow != null) {
                iSmsServiceOrThrow.sendMultipartTextForSubscriber(getSubscriptionId(), str3, str4, str, str2, list, list2, list3, z, j);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "sendMultipartTextMessageInternal: Couldn't send SMS - " + e.getMessage() + " id: " + j);
            notifySmsError(list2, 31);
        }
    }

    @SystemApi
    public void sendMultipartTextMessageWithoutPersisting(String str, String str2, List<String> list, List<PendingIntent> list2, List<PendingIntent> list3) {
        sendMultipartTextMessageInternal(str, str2, list, list2, list3, false, (String) null, (String) null, 0L);
    }

    public void sendMultipartTextMessage(String str, String str2, ArrayList<String> arrayList, ArrayList<PendingIntent> arrayList2, ArrayList<PendingIntent> arrayList3, int i, boolean z, int i2) {
        sendMultipartTextMessageInternal(str, str2, (List<String>) arrayList, (List<PendingIntent>) arrayList2, (List<PendingIntent>) arrayList3, true, i, z, i2);
    }

    private void sendMultipartTextMessageInternal(final String str, final String str2, final List<String> list, final List<PendingIntent> list2, final List<PendingIntent> list3, final boolean z, int i, final boolean z2, int i2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Invalid destinationAddress");
        }
        if (list == null || list.size() < 1) {
            throw new IllegalArgumentException("Invalid message body");
        }
        int i3 = (i < 0 || i > 3) ? -1 : i;
        int i4 = (i2 < 5 || i2 > 635040) ? -1 : i2;
        if (list.size() <= 1) {
            PendingIntent pendingIntent = null;
            PendingIntent pendingIntent2 = (list2 == null || list2.size() <= 0) ? null : list2.get(0);
            if (list3 != null && list3.size() > 0) {
                pendingIntent = list3.get(0);
            }
            sendTextMessageInternal(str, str2, list.get(0), pendingIntent2, pendingIntent, z, i3, z2, i4);
            return;
        }
        if (z) {
            final int i5 = i3;
            final int i6 = i4;
            resolveSubscriptionForOperation(new SubscriptionResolverResult() { // from class: android.telephony.SmsManager.4
                @Override // android.telephony.SmsManager.SubscriptionResolverResult
                public void onSuccess(int i7) {
                    try {
                        ISms iSmsServiceOrThrow = SmsManager.getISmsServiceOrThrow();
                        if (iSmsServiceOrThrow != null) {
                            iSmsServiceOrThrow.sendMultipartTextForSubscriberWithOptions(i7, null, null, str, str2, list, list2, list3, z, i5, z2, i6);
                        }
                    } catch (RemoteException e) {
                        Log.e(SmsManager.TAG, "sendMultipartTextMessageInternal: Couldn't send SMS - " + e.getMessage());
                        SmsManager.notifySmsError((List<PendingIntent>) list2, 31);
                    }
                }

                @Override // android.telephony.SmsManager.SubscriptionResolverResult
                public void onFailure() {
                    SmsManager.notifySmsError((List<PendingIntent>) list2, 32);
                }
            });
            return;
        }
        try {
            ISms iSmsServiceOrThrow = getISmsServiceOrThrow();
            if (iSmsServiceOrThrow != null) {
                iSmsServiceOrThrow.sendMultipartTextForSubscriberWithOptions(getSubscriptionId(), null, null, str, str2, list, list2, list3, z, i3, z2, i4);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "sendMultipartTextMessageInternal (no persist): Couldn't send SMS - " + e.getMessage());
            notifySmsError(list2, 31);
        }
    }

    public void sendDataMessage(final String str, final String str2, final short s, final byte[] bArr, final PendingIntent pendingIntent, final PendingIntent pendingIntent2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Invalid destinationAddress");
        }
        if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException("Invalid message data");
        }
        resolveSubscriptionForOperation(new SubscriptionResolverResult() { // from class: android.telephony.SmsManager.5
            @Override // android.telephony.SmsManager.SubscriptionResolverResult
            public void onSuccess(int i) {
                try {
                    SmsManager.getISmsServiceOrThrow().sendDataForSubscriber(i, null, null, str, str2, 65535 & s, bArr, pendingIntent, pendingIntent2);
                } catch (RemoteException e) {
                    Log.e(SmsManager.TAG, "sendDataMessage: Couldn't send SMS - Exception: " + e.getMessage());
                    SmsManager.notifySmsError(pendingIntent, 31);
                }
            }

            @Override // android.telephony.SmsManager.SubscriptionResolverResult
            public void onFailure() {
                SmsManager.notifySmsError(pendingIntent, 32);
            }
        });
    }

    public static SmsManager getDefault() {
        return sInstance;
    }

    public static SmsManager getSmsManagerForSubscriptionId(int i) {
        SmsManager smsManager;
        synchronized (sLockObject) {
            Map<Integer, SmsManager> map = sSubInstances;
            smsManager = map.get(Integer.valueOf(i));
            if (smsManager == null) {
                smsManager = new SmsManager(i);
                map.put(Integer.valueOf(i), smsManager);
            }
        }
        return smsManager;
    }

    private SmsManager(int i) {
        this.mSubId = i;
    }

    public int getSubscriptionId() {
        try {
            int i = this.mSubId;
            return i == Integer.MAX_VALUE ? getISmsServiceOrThrow().getPreferredSmsSubscription() : i;
        } catch (RemoteException unused) {
            return -1;
        }
    }

    private void resolveSubscriptionForOperation(final SubscriptionResolverResult subscriptionResolverResult) {
        ISms iSmsService;
        int subscriptionId = getSubscriptionId();
        try {
            iSmsService = getISmsService();
        } catch (RemoteException e) {
            Log.e(TAG, "resolveSubscriptionForOperation", e);
        }
        boolean zIsSmsSimPickActivityNeeded = iSmsService != null ? iSmsService.isSmsSimPickActivityNeeded(subscriptionId) : false;
        if (!zIsSmsSimPickActivityNeeded) {
            sendResolverResult(subscriptionResolverResult, subscriptionId, false);
            return;
        }
        Log.d(TAG, "resolveSubscriptionForOperation isSmsSimPickActivityNeeded is true for calling package. ");
        try {
            getITelephony().enqueueSmsPickResult(null, null, new IIntegerConsumer.Stub() { // from class: android.telephony.SmsManager.6
                @Override // com.android.internal.telephony.IIntegerConsumer
                public void accept(int i) {
                    SmsManager.this.sendResolverResult(subscriptionResolverResult, i, true);
                }
            });
        } catch (RemoteException e2) {
            Log.e(TAG, "Unable to launch activity", e2);
            sendResolverResult(subscriptionResolverResult, subscriptionId, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendResolverResult(SubscriptionResolverResult subscriptionResolverResult, int i, boolean z) {
        if (SubscriptionManager.isValidSubscriptionId(i)) {
            subscriptionResolverResult.onSuccess(i);
        } else if (!Compatibility.isChangeEnabled(GET_TARGET_SDK_VERSION_CODE_CHANGE) && !z) {
            subscriptionResolverResult.onSuccess(i);
        } else {
            subscriptionResolverResult.onFailure();
        }
    }

    private static ITelephony getITelephony() {
        ITelephony iTelephonyAsInterface = ITelephony.Stub.asInterface(TelephonyFrameworkInitializer.getTelephonyServiceManager().getTelephonyServiceRegisterer().get());
        if (iTelephonyAsInterface != null) {
            return iTelephonyAsInterface;
        }
        throw new RuntimeException("Could not find Telephony Service.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void notifySmsError(PendingIntent pendingIntent, int i) {
        if (pendingIntent != null) {
            try {
                pendingIntent.send(i);
            } catch (PendingIntent.CanceledException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void notifySmsError(List<PendingIntent> list, int i) {
        if (list != null) {
            Iterator<PendingIntent> it = list.iterator();
            while (it.hasNext()) {
                notifySmsError(it.next(), i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ISms getISmsServiceOrThrow() {
        ISms smsService = TelephonyManager.getSmsService();
        if (smsService != null) {
            return smsService;
        }
        throw new UnsupportedOperationException("Sms is not supported");
    }

    private static ISms getISmsService() {
        return TelephonyManager.getSmsService();
    }

    public boolean copyMessageToIcc(byte[] bArr, byte[] bArr2, int i) {
        if (bArr2 == null) {
            throw new IllegalArgumentException("pdu is null");
        }
        try {
            ISms iSmsService = getISmsService();
            if (iSmsService != null) {
                return iSmsService.copyMessageToIccEfForSubscriber(getSubscriptionId(), null, i, bArr2, bArr);
            }
            return false;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean deleteMessageFromIcc(int i) {
        try {
            ISms iSmsService = getISmsService();
            if (iSmsService != null) {
                return iSmsService.updateMessageOnIccEfForSubscriber(getSubscriptionId(), null, i, 0, null);
            }
            return false;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public boolean updateMessageOnIcc(int i, int i2, byte[] bArr) {
        try {
            ISms iSmsService = getISmsService();
            if (iSmsService != null) {
                return iSmsService.updateMessageOnIccEfForSubscriber(getSubscriptionId(), null, i, i2, bArr);
            }
            return false;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public List<SmsMessage> getMessagesFromIcc() {
        return getAllMessagesFromIcc();
    }

    public ArrayList<SmsMessage> getAllMessagesFromIcc() {
        List<SmsRawData> allMessagesFromIccEfForSubscriber = null;
        try {
            ISms iSmsService = getISmsService();
            if (iSmsService != null) {
                allMessagesFromIccEfForSubscriber = iSmsService.getAllMessagesFromIccEfForSubscriber(getSubscriptionId(), null);
            }
        } catch (RemoteException unused) {
        }
        return createMessageListFromRawRecords(allMessagesFromIccEfForSubscriber);
    }

    @SystemApi
    public boolean enableCellBroadcastRange(int i, int i2, int i3) {
        if (i2 < i) {
            throw new IllegalArgumentException("endMessageId < startMessageId");
        }
        try {
            ISms iSmsService = getISmsService();
            if (iSmsService != null) {
                return iSmsService.enableCellBroadcastRangeForSubscriber(getSubscriptionId(), i, i2, i3);
            }
            return false;
        } catch (RemoteException unused) {
            return false;
        }
    }

    @SystemApi
    public boolean disableCellBroadcastRange(int i, int i2, int i3) {
        if (i2 < i) {
            throw new IllegalArgumentException("endMessageId < startMessageId");
        }
        try {
            ISms iSmsService = getISmsService();
            if (iSmsService != null) {
                return iSmsService.disableCellBroadcastRangeForSubscriber(getSubscriptionId(), i, i2, i3);
            }
            return false;
        } catch (RemoteException unused) {
            return false;
        }
    }

    private ArrayList<SmsMessage> createMessageListFromRawRecords(List<SmsRawData> list) {
        SmsMessage smsMessageCreateFromEfRecord;
        ArrayList<SmsMessage> arrayList = new ArrayList<>();
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                SmsRawData smsRawData = list.get(i);
                if (smsRawData != null && (smsMessageCreateFromEfRecord = SmsMessage.createFromEfRecord(i + 1, smsRawData.getBytes(), getSubscriptionId())) != null) {
                    arrayList.add(smsMessageCreateFromEfRecord);
                }
            }
        }
        return arrayList;
    }

    public boolean isImsSmsSupported() {
        try {
            ISms iSmsService = getISmsService();
            if (iSmsService != null) {
                return iSmsService.isImsSmsSupportedForSubscriber(getSubscriptionId());
            }
            return false;
        } catch (RemoteException unused) {
            return false;
        }
    }

    public String getImsSmsFormat() {
        try {
            ISms iSmsService = getISmsService();
            return iSmsService != null ? iSmsService.getImsSmsFormatForSubscriber(getSubscriptionId()) : "unknown";
        } catch (RemoteException unused) {
            return "unknown";
        }
    }

    public static int getDefaultSmsSubscriptionId() {
        try {
            return getISmsService().getPreferredSmsSubscription();
        } catch (RemoteException | NullPointerException unused) {
            return -1;
        }
    }

    public boolean isSMSPromptEnabled() {
        try {
            return TelephonyManager.getSmsService().isSMSPromptEnabled();
        } catch (RemoteException | NullPointerException unused) {
            return false;
        }
    }

    @SystemApi
    public int getSmsCapacityOnIcc() {
        try {
            ISms iSmsService = getISmsService();
            if (iSmsService != null) {
                return iSmsService.getSmsCapacityOnIccForSubscriber(getSubscriptionId());
            }
            return 0;
        } catch (RemoteException unused) {
            return 0;
        }
    }

    public void sendMultimediaMessage(Context context, Uri uri, String str, Bundle bundle, PendingIntent pendingIntent) {
        if (uri == null) {
            throw new IllegalArgumentException("Uri contentUri null");
        }
        MmsManager mmsManager = (MmsManager) context.getSystemService("mms");
        if (mmsManager != null) {
            mmsManager.sendMultimediaMessage(getSubscriptionId(), uri, str, bundle, pendingIntent, 0L);
        }
    }

    public void downloadMultimediaMessage(Context context, String str, Uri uri, Bundle bundle, PendingIntent pendingIntent) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Empty MMS location URL");
        }
        if (uri == null) {
            throw new IllegalArgumentException("Uri contentUri null");
        }
        MmsManager mmsManager = (MmsManager) context.getSystemService("mms");
        if (mmsManager != null) {
            mmsManager.downloadMultimediaMessage(getSubscriptionId(), str, uri, bundle, pendingIntent, 0L);
        }
    }

    public Bundle getCarrierConfigValues() {
        try {
            ISms iSmsService = getISmsService();
            if (iSmsService != null) {
                return iSmsService.getCarrierConfigValuesForSubscriber(getSubscriptionId());
            }
        } catch (RemoteException unused) {
        }
        return new Bundle();
    }

    public String createAppSpecificSmsToken(PendingIntent pendingIntent) {
        try {
            return getISmsServiceOrThrow().createAppSpecificSmsToken(getSubscriptionId(), null, pendingIntent);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return null;
        }
    }

    public String createAppSpecificSmsTokenWithPackageInfo(String str, PendingIntent pendingIntent) {
        try {
            return getISmsServiceOrThrow().createAppSpecificSmsTokenWithPackageInfo(getSubscriptionId(), null, str, pendingIntent);
        } catch (RemoteException e) {
            e.rethrowFromSystemServer();
            return null;
        }
    }

    public int checkSmsShortCodeDestination(String str, String str2) {
        try {
            ISms iSmsServiceOrThrow = getISmsServiceOrThrow();
            if (iSmsServiceOrThrow != null) {
                return iSmsServiceOrThrow.checkSmsShortCodeDestination(getSubscriptionId(), null, null, str, str2);
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "checkSmsShortCodeDestination() RemoteException", e);
            return 0;
        }
    }

    public String getSmscAddress() {
        try {
            ISms iSmsService = getISmsService();
            if (iSmsService != null) {
                return iSmsService.getSmscAddressFromIccEfForSubscriber(getSubscriptionId(), null);
            }
            return null;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean setSmscAddress(String str) {
        try {
            ISms iSmsService = getISmsService();
            if (iSmsService != null) {
                return iSmsService.setSmscAddressOnIccEfForSubscriber(str, getSubscriptionId(), null);
            }
            return false;
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @SystemApi
    public int getPremiumSmsConsent(String str) {
        try {
            ISms iSmsService = getISmsService();
            if (iSmsService != null) {
                return iSmsService.getPremiumSmsPermission(str);
            }
            return 0;
        } catch (RemoteException e) {
            Log.e(TAG, "getPremiumSmsPermission() RemoteException", e);
            return 0;
        }
    }

    @SystemApi
    public void setPremiumSmsConsent(String str, int i) {
        try {
            ISms iSmsService = getISmsService();
            if (iSmsService != null) {
                iSmsService.setPremiumSmsPermission(str, i);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "setPremiumSmsPermission() RemoteException", e);
        }
    }

    public boolean resetAllCellBroadcastRanges() {
        try {
            ISms iSmsService = getISmsService();
            if (iSmsService != null) {
                return iSmsService.resetAllCellBroadcastRanges(getSubscriptionId());
            }
            return false;
        } catch (RemoteException unused) {
            return false;
        }
    }
}
