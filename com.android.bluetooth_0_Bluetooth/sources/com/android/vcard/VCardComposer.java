package com.android.vcard;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.INetd;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class VCardComposer {
    private static final boolean DEBUG = false;
    public static final String FAILURE_REASON_FAILED_TO_GET_DATABASE_INFO = "Failed to get database information";
    public static final String FAILURE_REASON_NOT_INITIALIZED = "The vCard composer object is not correctly initialized";
    public static final String FAILURE_REASON_NO_ENTRY = "There's no exportable in the database";
    public static final String FAILURE_REASON_UNSUPPORTED_URI = "The Uri vCard composer received is not supported by the composer.";
    private static final String LOG_TAG = "VCardComposer";
    public static final String NO_ERROR = "No error";
    private static final String SHIFT_JIS = "SHIFT_JIS";
    private static final String UTF_8 = "UTF-8";
    private static final String[] sContactsProjection;
    private static final Map<Integer, String> sImMap;
    private final String mCharset;
    private final ContentResolver mContentResolver;
    private Uri mContentUriForRawContactsEntity;
    private Cursor mCursor;
    private boolean mCursorSuppliedFromOutside;
    private String mErrorReason;
    private boolean mFirstVCardEmittedInDoCoMoCase;
    private int mIdColumn;
    private boolean mInitDone;
    private final boolean mIsDoCoMo;
    private VCardPhoneNumberTranslationCallback mPhoneTranslationCallback;
    private RawContactEntitlesInfoCallback mRawContactEntitlesInfoCallback;
    private boolean mTerminateCalled;
    private final int mVCardType;

    public interface RawContactEntitlesInfoCallback {
        RawContactEntitlesInfo getRawContactEntitlesInfo(long j);
    }

    static {
        HashMap map = new HashMap();
        sImMap = map;
        map.put(0, VCardConstants.PROPERTY_X_AIM);
        sImMap.put(1, VCardConstants.PROPERTY_X_MSN);
        sImMap.put(2, VCardConstants.PROPERTY_X_YAHOO);
        sImMap.put(6, VCardConstants.PROPERTY_X_ICQ);
        sImMap.put(7, VCardConstants.PROPERTY_X_JABBER);
        sImMap.put(3, VCardConstants.PROPERTY_X_SKYPE_USERNAME);
        sContactsProjection = new String[]{"_id"};
    }

    public VCardComposer(Context context) {
        this(context, VCardConfig.VCARD_TYPE_DEFAULT, null, true);
    }

    public VCardComposer(Context context, int vcardType) {
        this(context, vcardType, null, true);
    }

    public VCardComposer(Context context, int vcardType, String charset) {
        this(context, vcardType, charset, true);
    }

    public VCardComposer(Context context, int vcardType, boolean careHandlerErrors) {
        this(context, vcardType, null, careHandlerErrors);
    }

    public VCardComposer(Context context, int vcardType, String charset, boolean careHandlerErrors) {
        this(context, context.getContentResolver(), vcardType, charset, careHandlerErrors);
    }

    public VCardComposer(Context context, ContentResolver resolver, int vcardType, String charset, boolean careHandlerErrors) {
        this.mErrorReason = NO_ERROR;
        boolean shouldAppendCharsetParam = true;
        this.mTerminateCalled = true;
        this.mVCardType = vcardType;
        this.mContentResolver = resolver;
        this.mIsDoCoMo = VCardConfig.isDoCoMo(vcardType);
        String charset2 = TextUtils.isEmpty(charset) ? "UTF-8" : charset;
        if (VCardConfig.isVersion30(vcardType) && "UTF-8".equalsIgnoreCase(charset2)) {
            shouldAppendCharsetParam = false;
        }
        if (this.mIsDoCoMo || shouldAppendCharsetParam) {
            if (!SHIFT_JIS.equalsIgnoreCase(charset2) && TextUtils.isEmpty(charset2)) {
                this.mCharset = SHIFT_JIS;
            } else {
                this.mCharset = charset2;
            }
        } else if (TextUtils.isEmpty(charset2)) {
            this.mCharset = "UTF-8";
        } else {
            this.mCharset = charset2;
        }
        Log.d(LOG_TAG, "Use the charset \"" + this.mCharset + "\"");
    }

    public boolean init() {
        return init(null, null);
    }

    @Deprecated
    public boolean initWithRawContactsEntityUri(Uri contentUriForRawContactsEntity) {
        return init(ContactsContract.Contacts.CONTENT_URI, sContactsProjection, null, null, null, contentUriForRawContactsEntity);
    }

    public boolean init(String selection, String[] selectionArgs) {
        return init(ContactsContract.Contacts.CONTENT_URI, sContactsProjection, selection, selectionArgs, null, null);
    }

    public boolean init(Uri contentUri, String selection, String[] selectionArgs, String sortOrder) {
        return init(contentUri, sContactsProjection, selection, selectionArgs, sortOrder, null);
    }

    public boolean init(Uri contentUri, String selection, String[] selectionArgs, String sortOrder, Uri contentUriForRawContactsEntity) {
        return init(contentUri, sContactsProjection, selection, selectionArgs, sortOrder, contentUriForRawContactsEntity);
    }

    public boolean init(Uri contentUri, String[] projection, String selection, String[] selectionArgs, String sortOrder, Uri contentUriForRawContactsEntity) {
        if (!"com.android.contacts".equals(contentUri.getAuthority())) {
            this.mErrorReason = FAILURE_REASON_UNSUPPORTED_URI;
            return false;
        }
        if (initInterFirstPart(contentUriForRawContactsEntity) && initInterCursorCreationPart(contentUri, projection, selection, selectionArgs, sortOrder) && initInterMainPart()) {
            return initInterLastPart();
        }
        return false;
    }

    public boolean init(Cursor cursor) {
        return initWithCallback(cursor, null);
    }

    public boolean initWithCallback(Cursor cursor, RawContactEntitlesInfoCallback rawContactEntitlesInfoCallback) {
        if (!initInterFirstPart(null)) {
            return false;
        }
        this.mCursorSuppliedFromOutside = true;
        this.mCursor = cursor;
        this.mRawContactEntitlesInfoCallback = rawContactEntitlesInfoCallback;
        if (initInterMainPart()) {
            return initInterLastPart();
        }
        return false;
    }

    private boolean initInterFirstPart(Uri contentUriForRawContactsEntity) {
        this.mContentUriForRawContactsEntity = contentUriForRawContactsEntity != null ? contentUriForRawContactsEntity : ContactsContract.RawContactsEntity.CONTENT_URI;
        if (this.mInitDone) {
            Log.e(LOG_TAG, "init() is already called");
            return false;
        }
        return true;
    }

    private boolean initInterCursorCreationPart(Uri contentUri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        this.mCursorSuppliedFromOutside = false;
        Cursor cursorQuery = this.mContentResolver.query(contentUri, projection, selection, selectionArgs, sortOrder);
        this.mCursor = cursorQuery;
        if (cursorQuery == null) {
            Log.e(LOG_TAG, String.format("Cursor became null unexpectedly", new Object[0]));
            this.mErrorReason = FAILURE_REASON_FAILED_TO_GET_DATABASE_INFO;
            return false;
        }
        return true;
    }

    private boolean initInterMainPart() {
        if (this.mCursor.getCount() == 0 || !this.mCursor.moveToFirst()) {
            closeCursorIfAppropriate();
            return false;
        }
        int columnIndex = this.mCursor.getColumnIndex("contact_id");
        this.mIdColumn = columnIndex;
        if (columnIndex < 0) {
            this.mIdColumn = this.mCursor.getColumnIndex("_id");
        }
        return this.mIdColumn >= 0;
    }

    private boolean initInterLastPart() {
        this.mInitDone = true;
        this.mTerminateCalled = false;
        return true;
    }

    public String createOneEntry() {
        return createOneEntry(null);
    }

    public String createOneEntry(Method getEntityIteratorMethod) throws Throwable {
        if (this.mIsDoCoMo && !this.mFirstVCardEmittedInDoCoMoCase) {
            this.mFirstVCardEmittedInDoCoMoCase = true;
        }
        String vcard = createOneEntryInternal(this.mCursor.getLong(this.mIdColumn), getEntityIteratorMethod);
        if (!this.mCursor.moveToNext()) {
            Log.e(LOG_TAG, "Cursor#moveToNext() returned false");
        }
        return vcard;
    }

    public static class RawContactEntitlesInfo {
        public final long contactId;
        public final Uri rawContactEntitlesUri;

        public RawContactEntitlesInfo(Uri rawContactEntitlesUri, long contactId) {
            this.rawContactEntitlesUri = rawContactEntitlesUri;
            this.contactId = contactId;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00ae A[Catch: all -> 0x0126, TRY_ENTER, TRY_LEAVE, TryCatch #2 {all -> 0x0126, blocks: (B:13:0x0028, B:17:0x0037, B:31:0x00ae, B:35:0x00ba, B:37:0x00c0, B:41:0x00db, B:43:0x00e1, B:44:0x00ef, B:46:0x00f5, B:48:0x0106, B:50:0x010e, B:51:0x0117, B:20:0x0053, B:21:0x005f, B:23:0x0061, B:25:0x007c, B:27:0x0097), top: B:67:0x0028, inners: #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ba A[Catch: all -> 0x0126, TRY_ENTER, TryCatch #2 {all -> 0x0126, blocks: (B:13:0x0028, B:17:0x0037, B:31:0x00ae, B:35:0x00ba, B:37:0x00c0, B:41:0x00db, B:43:0x00e1, B:44:0x00ef, B:46:0x00f5, B:48:0x0106, B:50:0x010e, B:51:0x0117, B:20:0x0053, B:21:0x005f, B:23:0x0061, B:25:0x007c, B:27:0x0097), top: B:67:0x0028, inners: #5, #6 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x012d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String createOneEntryInternal(long r17, java.lang.reflect.Method r19) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 305
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardComposer.createOneEntryInternal(long, java.lang.reflect.Method):java.lang.String");
    }

    public void setPhoneNumberTranslationCallback(VCardPhoneNumberTranslationCallback callback) {
        this.mPhoneTranslationCallback = callback;
    }

    public String buildVCard(Map<String, List<ContentValues>> contentValuesListMap) throws UnsupportedEncodingException {
        if (contentValuesListMap == null) {
            Log.e(LOG_TAG, "The given map is null. Ignore and return empty String");
            return INetd.NEXTHOP_NONE;
        }
        VCardBuilder builder = new VCardBuilder(this.mVCardType, this.mCharset);
        builder.appendNameProperties(contentValuesListMap.get("vnd.android.cursor.item/name")).appendNickNames(contentValuesListMap.get("vnd.android.cursor.item/nickname")).appendPhones(contentValuesListMap.get("vnd.android.cursor.item/phone_v2"), this.mPhoneTranslationCallback).appendEmails(contentValuesListMap.get("vnd.android.cursor.item/email_v2")).appendPostals(contentValuesListMap.get("vnd.android.cursor.item/postal-address_v2")).appendOrganizations(contentValuesListMap.get("vnd.android.cursor.item/organization")).appendWebsites(contentValuesListMap.get("vnd.android.cursor.item/website"));
        if ((this.mVCardType & 8388608) == 0) {
            builder.appendPhotos(contentValuesListMap.get("vnd.android.cursor.item/photo"));
        }
        builder.appendNotes(contentValuesListMap.get("vnd.android.cursor.item/note")).appendEvents(contentValuesListMap.get("vnd.android.cursor.item/contact_event")).appendIms(contentValuesListMap.get("vnd.android.cursor.item/im")).appendSipAddresses(contentValuesListMap.get("vnd.android.cursor.item/sip_address")).appendRelation(contentValuesListMap.get("vnd.android.cursor.item/relation"));
        return builder.toString();
    }

    public void terminate() {
        closeCursorIfAppropriate();
        this.mTerminateCalled = true;
    }

    private void closeCursorIfAppropriate() {
        Cursor cursor;
        if (!this.mCursorSuppliedFromOutside && (cursor = this.mCursor) != null) {
            try {
                cursor.close();
            } catch (SQLiteException e) {
                Log.e(LOG_TAG, "SQLiteException on Cursor#close(): " + e.getMessage());
            }
            this.mCursor = null;
        }
    }

    protected void finalize() throws Throwable {
        try {
            if (!this.mTerminateCalled) {
                Log.e(LOG_TAG, "finalized() is called before terminate() being called");
            }
        } finally {
            super.finalize();
        }
    }

    public int getCount() {
        Cursor cursor = this.mCursor;
        if (cursor == null) {
            Log.w(LOG_TAG, "This object is not ready yet.");
            return 0;
        }
        return cursor.getCount();
    }

    public boolean isAfterLast() {
        Cursor cursor = this.mCursor;
        if (cursor == null) {
            Log.w(LOG_TAG, "This object is not ready yet.");
            return false;
        }
        return cursor.isAfterLast();
    }

    public String getErrorReason() {
        return this.mErrorReason;
    }
}
