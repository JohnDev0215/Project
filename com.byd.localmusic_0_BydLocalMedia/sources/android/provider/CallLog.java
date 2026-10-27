package android.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.UserInfo;
import android.database.Cursor;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.location.Country;
import android.location.CountryDetector;
import android.media.AudioSystem;
import android.net.Uri;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.ContactsContract;
import android.telecom.CallerInfo;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class CallLog {
    public static final String AUTHORITY = "call_log";
    public static final Uri CONTENT_URI = Uri.parse("content://call_log");
    private static final String LOG_TAG = "CallLog";
    public static final String SHADOW_AUTHORITY = "call_log_shadow";
    private static final boolean VERBOSE_LOG = false;

    public static class Calls implements BaseColumns {
        public static final String ADD_FOR_ALL_USERS = "add_for_all_users";
        public static final int ANSWERED_EXTERNALLY_TYPE = 7;
        public static final int BLOCKED_TYPE = 6;
        public static final String BLOCK_REASON = "block_reason";
        public static final int BLOCK_REASON_BLOCKED_NUMBER = 3;
        public static final int BLOCK_REASON_CALL_SCREENING_SERVICE = 1;
        public static final int BLOCK_REASON_DIRECT_TO_VOICEMAIL = 2;
        public static final int BLOCK_REASON_NOT_BLOCKED = 0;
        public static final int BLOCK_REASON_NOT_IN_CONTACTS = 7;
        public static final int BLOCK_REASON_PAY_PHONE = 6;
        public static final int BLOCK_REASON_RESTRICTED_NUMBER = 5;
        public static final int BLOCK_REASON_UNKNOWN_NUMBER = 4;
        public static final String CACHED_FORMATTED_NUMBER = "formatted_number";
        public static final String CACHED_LOOKUP_URI = "lookup_uri";
        public static final String CACHED_MATCHED_NUMBER = "matched_number";
        public static final String CACHED_NAME = "name";
        public static final String CACHED_NORMALIZED_NUMBER = "normalized_number";
        public static final String CACHED_NUMBER_LABEL = "numberlabel";
        public static final String CACHED_NUMBER_TYPE = "numbertype";
        public static final String CACHED_PHOTO_ID = "photo_id";
        public static final String CACHED_PHOTO_URI = "photo_uri";
        public static final String CALL_SCREENING_APP_NAME = "call_screening_app_name";
        public static final String CALL_SCREENING_COMPONENT_NAME = "call_screening_component_name";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/calls";
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/calls";
        public static final String COUNTRY_ISO = "countryiso";
        public static final String DATA_USAGE = "data_usage";
        public static final String DATE = "date";
        public static final String DEFAULT_SORT_ORDER = "date DESC";
        public static final String DURATION = "duration";
        public static final String EXTRA_CALL_TYPE_FILTER = "android.provider.extra.CALL_TYPE_FILTER";
        public static final String FEATURES = "features";
        public static final int FEATURES_ASSISTED_DIALING_USED = 16;
        public static final int FEATURES_HD_CALL = 4;
        public static final int FEATURES_PULLED_EXTERNALLY = 2;
        public static final int FEATURES_RTT = 32;
        public static final int FEATURES_VIDEO = 1;
        public static final int FEATURES_VOLTE = 64;
        public static final int FEATURES_WIFI = 8;
        public static final String GEOCODED_LOCATION = "geocoded_location";
        public static final int INCOMING_TYPE = 1;
        public static final String IS_READ = "is_read";
        public static final String LAST_MODIFIED = "last_modified";
        public static final String LIMIT_PARAM_KEY = "limit";
        private static final int MIN_DURATION_FOR_NORMALIZED_NUMBER_UPDATE_MS = 10000;
        public static final int MISSED_TYPE = 3;
        public static final String NEW = "new";
        public static final String NUMBER = "number";
        public static final String NUMBER_PRESENTATION = "presentation";
        public static final String OFFSET_PARAM_KEY = "offset";
        public static final int OUTGOING_TYPE = 2;
        public static final String PHONE_ACCOUNT_ADDRESS = "phone_account_address";
        public static final String PHONE_ACCOUNT_COMPONENT_NAME = "subscription_component_name";
        public static final String PHONE_ACCOUNT_HIDDEN = "phone_account_hidden";
        public static final String PHONE_ACCOUNT_ID = "subscription_id";
        public static final String POST_DIAL_DIGITS = "post_dial_digits";
        public static final int PRESENTATION_ALLOWED = 1;
        public static final int PRESENTATION_PAYPHONE = 4;
        public static final int PRESENTATION_RESTRICTED = 2;
        public static final int PRESENTATION_UNKNOWN = 3;
        public static final int REJECTED_TYPE = 5;
        public static final String SUB_ID = "sub_id";
        public static final String TRANSCRIPTION = "transcription";
        public static final String TRANSCRIPTION_STATE = "transcription_state";
        public static final String TYPE = "type";
        public static final String VIA_NUMBER = "via_number";
        public static final int VOICEMAIL_TYPE = 4;
        public static final String VOICEMAIL_URI = "voicemail_uri";
        public static final Uri CONTENT_URI = Uri.parse(BYDAutoSysPropertiesDevice.URL_CALLLOG_CONTACT);
        public static final Uri SHADOW_CONTENT_URI = Uri.parse("content://call_log_shadow/calls");
        public static final Uri CONTENT_FILTER_URI = Uri.parse("content://call_log/calls/filter");
        private static final Uri CONTENT_URI_LIMIT_1 = CONTENT_URI.buildUpon().appendQueryParameter("limit", "1").build();
        public static final String ALLOW_VOICEMAILS_PARAM_KEY = "allow_voicemails";
        public static final Uri CONTENT_URI_WITH_VOICEMAIL = CONTENT_URI.buildUpon().appendQueryParameter(ALLOW_VOICEMAILS_PARAM_KEY, "true").build();

        public static Uri addCall(CallerInfo callerInfo, Context context, String str, int i, int i2, int i3, PhoneAccountHandle phoneAccountHandle, long j, int i4, Long l) {
            return addCall(callerInfo, context, str, "", "", i, i2, i3, phoneAccountHandle, j, i4, l, false, null, false, 0, null, null);
        }

        public static Uri addCall(CallerInfo callerInfo, Context context, String str, String str2, String str3, int i, int i2, int i3, PhoneAccountHandle phoneAccountHandle, long j, int i4, Long l, boolean z, UserHandle userHandle) {
            return addCall(callerInfo, context, str, str2, str3, i, i2, i3, phoneAccountHandle, j, i4, l, z, userHandle, false, 0, null, null);
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x003e  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x008f  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00c3  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00f9  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x011c  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x018c  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x01e6  */
        /* JADX WARN: Removed duplicated region for block: B:82:0x0150 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static android.net.Uri addCall(android.telecom.CallerInfo r18, android.content.Context r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, int r23, int r24, int r25, android.telecom.PhoneAccountHandle r26, long r27, int r29, java.lang.Long r30, boolean r31, android.os.UserHandle r32, boolean r33, int r34, java.lang.CharSequence r35, java.lang.String r36) {
            /*
                Method dump skipped, instruction units count: 500
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: android.provider.CallLog.Calls.addCall(android.telecom.CallerInfo, android.content.Context, java.lang.String, java.lang.String, java.lang.String, int, int, int, android.telecom.PhoneAccountHandle, long, int, java.lang.Long, boolean, android.os.UserHandle, boolean, int, java.lang.CharSequence, java.lang.String):android.net.Uri");
        }

        private static String charSequenceToString(CharSequence charSequence) {
            if (charSequence == null) {
                return null;
            }
            return charSequence.toString();
        }

        public static boolean shouldHaveSharedCallLogEntries(Context context, UserManager userManager, int i) {
            UserInfo userInfo;
            return (userManager.hasUserRestriction(UserManager.DISALLOW_OUTGOING_CALLS, UserHandle.of(i)) || (userInfo = userManager.getUserInfo(i)) == null || userInfo.isManagedProfile()) ? false : true;
        }

        public static String getLastOutgoingCall(Context context) {
            ContentResolver contentResolver = context.getContentResolver();
            Cursor cursorQuery = null;
            try {
                cursorQuery = contentResolver.query(CONTENT_URI_LIMIT_1, new String[]{"number"}, "type = 2", null, "date DESC");
                if (cursorQuery != null && cursorQuery.moveToFirst()) {
                    return cursorQuery.getString(0);
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return "";
            } finally {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        }

        private static Uri addEntryAndRemoveExpiredEntries(Context context, UserManager userManager, UserHandle userHandle, ContentValues contentValues) {
            ContentResolver contentResolver = context.getContentResolver();
            Uri uriMaybeAddUserId = ContentProvider.maybeAddUserId(userManager.isUserUnlocked(userHandle) ? CONTENT_URI : SHADOW_CONTENT_URI, userHandle.getIdentifier());
            try {
                Uri uriInsert = contentResolver.insert(uriMaybeAddUserId, contentValues);
                if (uriInsert != null) {
                    String lastPathSegment = uriInsert.getLastPathSegment();
                    if (lastPathSegment != null && lastPathSegment.equals(AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS)) {
                        Log.w(CallLog.LOG_TAG, "Failed to insert into call log due to appops denial; resultUri=" + uriInsert);
                    }
                } else {
                    Log.w(CallLog.LOG_TAG, "Failed to insert into call log; null result uri.");
                }
                if (contentValues.containsKey("subscription_id") && !TextUtils.isEmpty(contentValues.getAsString("subscription_id")) && contentValues.containsKey("subscription_component_name") && !TextUtils.isEmpty(contentValues.getAsString("subscription_component_name"))) {
                    contentResolver.delete(uriMaybeAddUserId, "_id IN (SELECT _id FROM calls WHERE subscription_component_name = ? AND subscription_id = ? ORDER BY date DESC LIMIT -1 OFFSET 500)", new String[]{contentValues.getAsString("subscription_component_name"), contentValues.getAsString("subscription_id")});
                } else {
                    contentResolver.delete(uriMaybeAddUserId, "_id IN (SELECT _id FROM calls ORDER BY date DESC LIMIT -1 OFFSET 500)", null);
                }
                return uriInsert;
            } catch (IllegalArgumentException e) {
                Log.w(CallLog.LOG_TAG, "Failed to insert calllog", e);
                return null;
            }
        }

        private static void updateDataUsageStatForData(ContentResolver contentResolver, String str) {
            contentResolver.update(ContactsContract.DataUsageFeedback.FEEDBACK_URI.buildUpon().appendPath(str).appendQueryParameter("type", "call").build(), new ContentValues(), null, null);
        }

        private static void updateNormalizedNumber(Context context, ContentResolver contentResolver, String str, String str2) {
            if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
                return;
            }
            String currentCountryIso = getCurrentCountryIso(context);
            if (TextUtils.isEmpty(currentCountryIso)) {
                return;
            }
            String numberToE164 = PhoneNumberUtils.formatNumberToE164(str2, currentCountryIso);
            if (TextUtils.isEmpty(numberToE164)) {
                return;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("data4", numberToE164);
            contentResolver.update(ContactsContract.Data.CONTENT_URI, contentValues, "_id=?", new String[]{str});
        }

        private static int getLogNumberPresentation(String str, int i) {
            return (i == 2 || i == 4) ? i : (TextUtils.isEmpty(str) || i == 3) ? 3 : 1;
        }

        private static String getLogAccountAddress(Context context, PhoneAccountHandle phoneAccountHandle) {
            TelecomManager telecomManagerFrom;
            PhoneAccount phoneAccount;
            Uri subscriptionAddress;
            try {
                telecomManagerFrom = TelecomManager.from(context);
            } catch (UnsupportedOperationException unused) {
                telecomManagerFrom = null;
            }
            if (telecomManagerFrom == null || phoneAccountHandle == null || (phoneAccount = telecomManagerFrom.getPhoneAccount(phoneAccountHandle)) == null || (subscriptionAddress = phoneAccount.getSubscriptionAddress()) == null) {
                return null;
            }
            return subscriptionAddress.getSchemeSpecificPart();
        }

        private static String getCurrentCountryIso(Context context) {
            Country countryDetectCountry;
            CountryDetector countryDetector = (CountryDetector) context.getSystemService(Context.COUNTRY_DETECTOR);
            if (countryDetector == null || (countryDetectCountry = countryDetector.detectCountry()) == null) {
                return null;
            }
            return countryDetectCountry.getCountryIso();
        }
    }
}
