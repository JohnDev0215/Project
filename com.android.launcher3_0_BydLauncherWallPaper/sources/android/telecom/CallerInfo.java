package android.telecom;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Country;
import android.location.CountryDetector;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.android.internal.R;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class CallerInfo {
    private static final String TAG = "CallerInfo";
    public static final long USER_TYPE_CURRENT = 0;
    public static final long USER_TYPE_WORK = 1;
    private static final boolean VDBG = Log.VERBOSE;
    public Drawable cachedPhoto;
    public Bitmap cachedPhotoIcon;
    public String cnapName;
    private Uri contactDisplayPhotoUri;
    public boolean contactExists;
    private long contactIdOrZero;
    public Uri contactRefUri;
    public Uri contactRingtoneUri;
    public String geoDescription;
    public boolean isCachedPhotoCurrent;
    public String lookupKey;
    private String name;
    public int namePresentation;
    public boolean needUpdate;
    public String normalizedNumber;
    public String numberLabel;
    public int numberPresentation;
    public int numberType;
    public String phoneLabel;
    private String phoneNumber;
    public int photoResource;
    public ComponentName preferredPhoneAccountComponent;
    public String preferredPhoneAccountId;
    public boolean shouldSendToVoicemail;
    private boolean mIsEmergency = false;
    private boolean mIsVoiceMail = false;
    public long userType = 0;

    public static CallerInfo getCallerInfo(Context context, Uri uri, Cursor cursor) {
        int columnIndex;
        CallerInfo callerInfo = new CallerInfo();
        callerInfo.photoResource = 0;
        callerInfo.phoneLabel = null;
        callerInfo.numberType = 0;
        callerInfo.numberLabel = null;
        callerInfo.cachedPhoto = null;
        callerInfo.isCachedPhotoCurrent = false;
        callerInfo.contactExists = false;
        callerInfo.userType = 0L;
        boolean z = VDBG;
        if (z) {
            Log.v(TAG, "getCallerInfo() based on cursor...", new Object[0]);
        }
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int columnIndex2 = cursor.getColumnIndex("display_name");
                if (columnIndex2 != -1) {
                    callerInfo.name = cursor.getString(columnIndex2);
                }
                int columnIndex3 = cursor.getColumnIndex("number");
                if (columnIndex3 != -1) {
                    callerInfo.phoneNumber = cursor.getString(columnIndex3);
                }
                int columnIndex4 = cursor.getColumnIndex("normalized_number");
                if (columnIndex4 != -1) {
                    callerInfo.normalizedNumber = cursor.getString(columnIndex4);
                }
                int columnIndex5 = cursor.getColumnIndex("label");
                if (columnIndex5 != -1 && (columnIndex = cursor.getColumnIndex("type")) != -1) {
                    callerInfo.numberType = cursor.getInt(columnIndex);
                    String string = cursor.getString(columnIndex5);
                    callerInfo.numberLabel = string;
                    callerInfo.phoneLabel = ContactsContract.CommonDataKinds.Phone.getDisplayLabel(context, callerInfo.numberType, string).toString();
                }
                int columnIndexForPersonId = getColumnIndexForPersonId(uri, cursor);
                if (columnIndexForPersonId != -1) {
                    long j = cursor.getLong(columnIndexForPersonId);
                    if (j != 0 && !ContactsContract.Contacts.isEnterpriseContactId(j)) {
                        callerInfo.contactIdOrZero = j;
                        if (z) {
                            Log.v(TAG, "==> got info.contactIdOrZero: " + callerInfo.contactIdOrZero, new Object[0]);
                        }
                    }
                    if (ContactsContract.Contacts.isEnterpriseContactId(j)) {
                        callerInfo.userType = 1L;
                    }
                } else {
                    Log.w(TAG, "Couldn't find contact_id column for " + uri, new Object[0]);
                }
                int columnIndex6 = cursor.getColumnIndex("lookup");
                if (columnIndex6 != -1) {
                    callerInfo.lookupKey = cursor.getString(columnIndex6);
                }
                int columnIndex7 = cursor.getColumnIndex("photo_uri");
                if (columnIndex7 != -1 && cursor.getString(columnIndex7) != null) {
                    callerInfo.contactDisplayPhotoUri = Uri.parse(cursor.getString(columnIndex7));
                } else {
                    callerInfo.contactDisplayPhotoUri = null;
                }
                int columnIndex8 = cursor.getColumnIndex(ContactsContract.DataColumns.PREFERRED_PHONE_ACCOUNT_COMPONENT_NAME);
                if (columnIndex8 != -1 && cursor.getString(columnIndex8) != null) {
                    callerInfo.preferredPhoneAccountComponent = ComponentName.unflattenFromString(cursor.getString(columnIndex8));
                }
                int columnIndex9 = cursor.getColumnIndex(ContactsContract.DataColumns.PREFERRED_PHONE_ACCOUNT_ID);
                if (columnIndex9 != -1 && cursor.getString(columnIndex9) != null) {
                    callerInfo.preferredPhoneAccountId = cursor.getString(columnIndex9);
                }
                int columnIndex10 = cursor.getColumnIndex("custom_ringtone");
                if (columnIndex10 != -1 && cursor.getString(columnIndex10) != null) {
                    if (TextUtils.isEmpty(cursor.getString(columnIndex10))) {
                        callerInfo.contactRingtoneUri = Uri.EMPTY;
                    } else {
                        callerInfo.contactRingtoneUri = Uri.parse(cursor.getString(columnIndex10));
                    }
                } else {
                    callerInfo.contactRingtoneUri = null;
                }
                int columnIndex11 = cursor.getColumnIndex("send_to_voicemail");
                callerInfo.shouldSendToVoicemail = columnIndex11 != -1 && cursor.getInt(columnIndex11) == 1;
                callerInfo.contactExists = true;
            }
            cursor.close();
        }
        callerInfo.needUpdate = false;
        callerInfo.name = normalize(callerInfo.name);
        callerInfo.contactRefUri = uri;
        return callerInfo;
    }

    public static CallerInfo getCallerInfo(Context context, Uri uri) {
        ContentResolver currentProfileContentResolver = CallerInfoAsyncQuery.getCurrentProfileContentResolver(context);
        if (currentProfileContentResolver != null) {
            try {
                return getCallerInfo(context, uri, currentProfileContentResolver.query(uri, null, null, null, null));
            } catch (RuntimeException e) {
                Log.e(TAG, (Throwable) e, "Error getting caller info.", new Object[0]);
            }
        }
        return null;
    }

    public static CallerInfo getCallerInfo(Context context, String str) {
        if (VDBG) {
            Log.v(TAG, "getCallerInfo() based on number...", new Object[0]);
        }
        return getCallerInfo(context, str, SubscriptionManager.getDefaultSubscriptionId());
    }

    public static CallerInfo getCallerInfo(Context context, String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (PhoneNumberUtils.isLocalEmergencyNumber(context, str)) {
            return new CallerInfo().markAsEmergency(context);
        }
        if (PhoneNumberUtils.isVoiceMailNumber(null, i, str)) {
            return new CallerInfo().markAsVoiceMail(context, i);
        }
        CallerInfo callerInfoDoSecondaryLookupIfNecessary = doSecondaryLookupIfNecessary(context, str, getCallerInfo(context, Uri.withAppendedPath(ContactsContract.PhoneLookup.ENTERPRISE_CONTENT_FILTER_URI, Uri.encode(str))));
        if (TextUtils.isEmpty(callerInfoDoSecondaryLookupIfNecessary.phoneNumber)) {
            callerInfoDoSecondaryLookupIfNecessary.phoneNumber = str;
        }
        return callerInfoDoSecondaryLookupIfNecessary;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public long getContactId() {
        return this.contactIdOrZero;
    }

    public Uri getContactDisplayPhotoUri() {
        return this.contactDisplayPhotoUri;
    }

    public void SetContactDisplayPhotoUri(Uri uri) {
        this.contactDisplayPhotoUri = uri;
    }

    static CallerInfo doSecondaryLookupIfNecessary(Context context, String str, CallerInfo callerInfo) {
        if (callerInfo.contactExists || !PhoneNumberUtils.isUriNumber(str)) {
            return callerInfo;
        }
        String usernameFromUriNumber = PhoneNumberUtils.getUsernameFromUriNumber(str);
        return PhoneNumberUtils.isGlobalPhoneNumber(usernameFromUriNumber) ? getCallerInfo(context, Uri.withAppendedPath(ContactsContract.PhoneLookup.ENTERPRISE_CONTENT_FILTER_URI, Uri.encode(usernameFromUriNumber))) : callerInfo;
    }

    public boolean isEmergencyNumber() {
        return this.mIsEmergency;
    }

    public boolean isVoiceMailNumber() {
        return this.mIsVoiceMail;
    }

    CallerInfo markAsEmergency(Context context) {
        this.phoneNumber = context.getString(R.string.emergency_call_dialog_number_for_display);
        this.photoResource = R.drawable.picture_emergency;
        this.mIsEmergency = true;
        return this;
    }

    CallerInfo markAsVoiceMail(Context context, int i) {
        this.mIsVoiceMail = true;
        try {
            this.phoneNumber = ((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForSubscriptionId(i).getVoiceMailAlphaTag();
        } catch (SecurityException e) {
            Log.e(TAG, (Throwable) e, "Cannot access VoiceMail.", new Object[0]);
        }
        return this;
    }

    private static String normalize(String str) {
        if (str == null || str.length() > 0) {
            return str;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int getColumnIndexForPersonId(android.net.Uri r6, android.database.Cursor r7) {
        /*
            boolean r0 = android.telecom.CallerInfo.VDBG
            r1 = 0
            java.lang.String r2 = "CallerInfo"
            if (r0 == 0) goto L25
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "- getColumnIndexForPersonId: contactRef URI = '"
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.StringBuilder r3 = r3.append(r6)
            java.lang.String r4 = "'..."
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.Object[] r4 = new java.lang.Object[r1]
            android.telecom.Log.v(r2, r3, r4)
        L25:
            java.lang.String r6 = r6.toString()
            r3 = 0
            java.lang.String r4 = "content://com.android.contacts/data/phones"
            boolean r4 = r6.startsWith(r4)
            java.lang.String r5 = "contact_id"
            if (r4 == 0) goto L3f
            if (r0 == 0) goto L3d
            java.lang.Object[] r6 = new java.lang.Object[r1]
            java.lang.String r3 = "'data/phones' URI; using RawContacts.CONTACT_ID"
            android.telecom.Log.v(r2, r3, r6)
        L3d:
            r3 = r5
            goto L83
        L3f:
            java.lang.String r4 = "content://com.android.contacts/data"
            boolean r4 = r6.startsWith(r4)
            if (r4 == 0) goto L51
            if (r0 == 0) goto L3d
            java.lang.Object[] r6 = new java.lang.Object[r1]
            java.lang.String r3 = "'data' URI; using Data.CONTACT_ID"
            android.telecom.Log.v(r2, r3, r6)
            goto L3d
        L51:
            java.lang.String r4 = "content://com.android.contacts/phone_lookup"
            boolean r4 = r6.startsWith(r4)
            if (r4 == 0) goto L65
            if (r0 == 0) goto L62
            java.lang.Object[] r6 = new java.lang.Object[r1]
            java.lang.String r3 = "'phone_lookup' URI; using PhoneLookup._ID"
            android.telecom.Log.v(r2, r3, r6)
        L62:
            java.lang.String r3 = "_id"
            goto L83
        L65:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Unexpected prefix for contactRef '"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r6 = r4.append(r6)
            java.lang.String r4 = "'"
            java.lang.StringBuilder r6 = r6.append(r4)
            java.lang.String r6 = r6.toString()
            java.lang.Object[] r4 = new java.lang.Object[r1]
            android.telecom.Log.w(r2, r6, r4)
        L83:
            if (r3 == 0) goto L8a
            int r6 = r7.getColumnIndex(r3)
            goto L8b
        L8a:
            r6 = -1
        L8b:
            if (r0 == 0) goto Lb5
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r0 = "==> Using column '"
            java.lang.StringBuilder r7 = r7.append(r0)
            java.lang.StringBuilder r7 = r7.append(r3)
            java.lang.String r0 = "' (columnIndex = "
            java.lang.StringBuilder r7 = r7.append(r0)
            java.lang.StringBuilder r7 = r7.append(r6)
            java.lang.String r0 = ") for person_id lookup..."
            java.lang.StringBuilder r7 = r7.append(r0)
            java.lang.String r7 = r7.toString()
            java.lang.Object[] r0 = new java.lang.Object[r1]
            android.telecom.Log.v(r2, r7, r0)
        Lb5:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: android.telecom.CallerInfo.getColumnIndexForPersonId(android.net.Uri, android.database.Cursor):int");
    }

    public void updateGeoDescription(Context context, String str) {
        if (!TextUtils.isEmpty(this.phoneNumber)) {
            str = this.phoneNumber;
        }
        this.geoDescription = getGeoDescription(context, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d7 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getGeoDescription(android.content.Context r10, java.lang.String r11) {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.telecom.CallerInfo.getGeoDescription(android.content.Context, java.lang.String):java.lang.String");
    }

    private static String getCurrentCountryIso(Context context, Locale locale) {
        String countryIso;
        CountryDetector countryDetector = (CountryDetector) context.getSystemService(Context.COUNTRY_DETECTOR);
        if (countryDetector == null) {
            countryIso = null;
        } else {
            Country countryDetectCountry = countryDetector.detectCountry();
            if (countryDetectCountry != null) {
                countryIso = countryDetectCountry.getCountryIso();
            } else {
                Log.e(TAG, (Throwable) new Exception(), "CountryDetector.detectCountry() returned null.", new Object[0]);
                countryIso = null;
            }
        }
        if (countryIso != null) {
            return countryIso;
        }
        String country = locale.getCountry();
        Log.w(TAG, "No CountryDetector; falling back to countryIso based on locale: " + country, new Object[0]);
        return country;
    }

    protected static String getCurrentCountryIso(Context context) {
        return getCurrentCountryIso(context, Locale.getDefault());
    }

    public String toString() {
        return new StringBuilder(128).append(super.toString() + " { ").append("name " + (this.name == null ? "null" : "non-null")).append(", phoneNumber " + (this.phoneNumber != null ? "non-null" : "null")).append(" }").toString();
    }
}
