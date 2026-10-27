package com.android.providers.contacts;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Binder;
import android.os.Process;
import android.os.UserManager;
import android.provider.CallLog;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.util.ProviderAccessStats;
import com.android.providers.contacts.util.DbQueryUtils;
import com.android.providers.contacts.util.SelectionBuilder;
import com.android.providers.contacts.util.UserUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes.dex */
public class CallLogProvider extends ContentProvider {

    @VisibleForTesting
    static final String PARAM_KEY_QUERY_FOR_TESTING = "query_for_testing";

    @VisibleForTesting
    static final String PARAM_KEY_SET_TIME_FOR_TESTING = "set_time_for_testing";
    private static final Integer VOICEMAIL_TYPE;
    private static final ArrayMap<String, String> sCallsProjectionMap;
    private static Long sTimeForTestMillis;
    private static final UriMatcher sURIMatcher;
    private CallLogInsertionHelper mCallLogInsertionHelper;
    private DatabaseUtils.InsertHelper mCallsInserter;
    private CallLogDatabaseHelper mDbHelper;
    private int mMinMatch;
    private volatile CountDownLatch mReadAccessLatch;
    private ContactsTaskScheduler mTaskScheduler;
    private boolean mUseStrictPhoneNumberComparation;
    private VoicemailPermissions mVoicemailPermissions;
    public static final boolean VERBOSE_LOGGING = Log.isLoggable("CallLogProvider", 2);
    private static final String EXCLUDE_VOICEMAIL_SELECTION = DbQueryUtils.getInequalityClause("type", 4);
    private static final String EXCLUDE_HIDDEN_SELECTION = DbQueryUtils.getEqualityClause("phone_account_hidden", 0);

    @VisibleForTesting
    static final String[] CALL_LOG_SYNC_PROJECTION = {"number", "presentation", "type", "features", "date", "duration", "data_usage", "subscription_component_name", "subscription_id", "add_for_all_users"};
    static final String[] MINIMAL_PROJECTION = {"_id"};
    private final ThreadLocal<Boolean> mApplyingBatch = new ThreadLocal<>();
    private final ThreadLocal<Integer> mCallingUid = new ThreadLocal<>();
    private final ProviderAccessStats mStats = new ProviderAccessStats();

    private static String getLastSyncTimePropertyName(boolean z) {
        return z ? "call_log_last_synced_for_shadow" : "call_log_last_synced";
    }

    protected boolean isShadow() {
        return false;
    }

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        sURIMatcher = uriMatcher;
        uriMatcher.addURI("call_log", "calls", 1);
        sURIMatcher.addURI("call_log", "calls/#", 2);
        sURIMatcher.addURI("call_log", "calls/filter/*", 3);
        sURIMatcher.addURI("call_log_shadow", "calls", 1);
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        sCallsProjectionMap = arrayMap;
        arrayMap.put("_id", "_id");
        sCallsProjectionMap.put("number", "number");
        sCallsProjectionMap.put("post_dial_digits", "post_dial_digits");
        sCallsProjectionMap.put("via_number", "via_number");
        sCallsProjectionMap.put("presentation", "presentation");
        sCallsProjectionMap.put("date", "date");
        sCallsProjectionMap.put("duration", "duration");
        sCallsProjectionMap.put("data_usage", "data_usage");
        sCallsProjectionMap.put("type", "type");
        sCallsProjectionMap.put("features", "features");
        sCallsProjectionMap.put("subscription_component_name", "subscription_component_name");
        sCallsProjectionMap.put("subscription_id", "subscription_id");
        sCallsProjectionMap.put("phone_account_hidden", "phone_account_hidden");
        sCallsProjectionMap.put("phone_account_address", "phone_account_address");
        sCallsProjectionMap.put("new", "new");
        sCallsProjectionMap.put("voicemail_uri", "voicemail_uri");
        sCallsProjectionMap.put("transcription", "transcription");
        sCallsProjectionMap.put("transcription_state", "transcription_state");
        sCallsProjectionMap.put("is_read", "is_read");
        sCallsProjectionMap.put("name", "name");
        sCallsProjectionMap.put("numbertype", "numbertype");
        sCallsProjectionMap.put("numberlabel", "numberlabel");
        sCallsProjectionMap.put("countryiso", "countryiso");
        sCallsProjectionMap.put("geocoded_location", "geocoded_location");
        sCallsProjectionMap.put("lookup_uri", "lookup_uri");
        sCallsProjectionMap.put("matched_number", "matched_number");
        sCallsProjectionMap.put("normalized_number", "normalized_number");
        sCallsProjectionMap.put("photo_id", "photo_id");
        sCallsProjectionMap.put("photo_uri", "photo_uri");
        sCallsProjectionMap.put("formatted_number", "formatted_number");
        sCallsProjectionMap.put("add_for_all_users", "add_for_all_users");
        sCallsProjectionMap.put("last_modified", "last_modified");
        sCallsProjectionMap.put("call_screening_component_name", "call_screening_component_name");
        sCallsProjectionMap.put("call_screening_app_name", "call_screening_app_name");
        sCallsProjectionMap.put("block_reason", "block_reason");
        VOICEMAIL_TYPE = new Integer(4);
    }

    protected final String getProviderName() {
        return getClass().getSimpleName();
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        if (VERBOSE_LOGGING) {
            Log.v("CallLogProvider", "onCreate: " + getClass().getSimpleName() + " user=" + Process.myUserHandle().getIdentifier());
        }
        setAppOps(6, 7);
        if (Log.isLoggable("ContactsPerf", 3)) {
            Log.d("ContactsPerf", getProviderName() + ".onCreate start");
        }
        Context context = getContext();
        this.mDbHelper = getDatabaseHelper(context);
        this.mUseStrictPhoneNumberComparation = context.getResources().getBoolean(android.R.bool.config_cecRoutingControl_userConfigurable);
        this.mMinMatch = context.getResources().getInteger(android.R.integer.config_fingerprintMaxTemplatesPerUser);
        this.mVoicemailPermissions = new VoicemailPermissions(context);
        this.mCallLogInsertionHelper = createCallLogInsertionHelper(context);
        this.mReadAccessLatch = new CountDownLatch(1);
        ContactsTaskScheduler contactsTaskScheduler = new ContactsTaskScheduler(getClass().getSimpleName()) { // from class: com.android.providers.contacts.CallLogProvider.1
            @Override // com.android.providers.contacts.ContactsTaskScheduler
            public void onPerformTask(int i, Object obj) {
                CallLogProvider.this.performBackgroundTask(i, obj);
            }
        };
        this.mTaskScheduler = contactsTaskScheduler;
        contactsTaskScheduler.scheduleTask(0, null);
        if (Log.isLoggable("ContactsPerf", 3)) {
            Log.d("ContactsPerf", getProviderName() + ".onCreate finish");
        }
        return true;
    }

    @VisibleForTesting
    protected CallLogInsertionHelper createCallLogInsertionHelper(Context context) {
        return DefaultCallLogInsertionHelper.getInstance(context);
    }

    @VisibleForTesting
    public void setMinMatchForTest(int i) {
        this.mMinMatch = i;
    }

    @VisibleForTesting
    public int getMinMatchForTest() {
        return this.mMinMatch;
    }

    protected CallLogDatabaseHelper getDatabaseHelper(Context context) {
        return CallLogDatabaseHelper.getInstance(context);
    }

    protected boolean applyingBatch() {
        Boolean bool = this.mApplyingBatch.get();
        return bool != null && bool.booleanValue();
    }

    @Override // android.content.ContentProvider
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        Boolean bool = Boolean.FALSE;
        int callingUid = Binder.getCallingUid();
        this.mCallingUid.set(Integer.valueOf(callingUid));
        this.mStats.incrementBatchStats(callingUid);
        this.mApplyingBatch.set(Boolean.TRUE);
        try {
            return super.applyBatch(arrayList);
        } finally {
            this.mApplyingBatch.set(bool);
            this.mStats.finishOperation(callingUid);
        }
    }

    @Override // android.content.ContentProvider
    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        Boolean bool = Boolean.FALSE;
        int callingUid = Binder.getCallingUid();
        this.mCallingUid.set(Integer.valueOf(callingUid));
        this.mStats.incrementBatchStats(callingUid);
        this.mApplyingBatch.set(Boolean.TRUE);
        try {
            return super.bulkInsert(uri, contentValuesArr);
        } finally {
            this.mApplyingBatch.set(bool);
            this.mStats.finishOperation(callingUid);
        }
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int callingUid = Binder.getCallingUid();
        this.mStats.incrementQueryStats(callingUid);
        try {
            return queryInternal(uri, strArr, str, strArr2, str2);
        } finally {
            this.mStats.finishOperation(callingUid);
        }
    }

    private Cursor queryInternal(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String str3;
        String str4;
        if (VERBOSE_LOGGING) {
            Log.v("CallLogProvider", "query: uri=" + uri + "  projection=" + Arrays.toString(strArr) + "  selection=[" + str + "]  args=" + Arrays.toString(strArr2) + "  order=[" + str2 + "] CPID=" + Binder.getCallingPid() + " CUID=" + Binder.getCallingUid() + " User=" + UserUtils.getCurrentUserHandle(getContext()));
        }
        queryForTesting(uri);
        waitForAccess(this.mReadAccessLatch);
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("calls");
        sQLiteQueryBuilder.setProjectionMap(sCallsProjectionMap);
        sQLiteQueryBuilder.setStrict(true);
        if (!this.mVoicemailPermissions.callerHasReadAccess(getCallingPackage())) {
            sQLiteQueryBuilder.setStrictGrammar(true);
        }
        SelectionBuilder selectionBuilder = new SelectionBuilder(str);
        checkVoicemailPermissionAndAddRestriction(uri, selectionBuilder, true);
        selectionBuilder.addClause(EXCLUDE_HIDDEN_SELECTION);
        int iMatch = sURIMatcher.match(uri);
        if (iMatch != 1) {
            if (iMatch == 2) {
                selectionBuilder.addClause(DbQueryUtils.getEqualityClause("_id", parseCallIdFromUri(uri)));
            } else if (iMatch == 3) {
                List<String> pathSegments = uri.getPathSegments();
                String str5 = pathSegments.size() >= 2 ? pathSegments.get(2) : null;
                if (!TextUtils.isEmpty(str5)) {
                    sQLiteQueryBuilder.appendWhere("PHONE_NUMBERS_EQUAL(number, ");
                    sQLiteQueryBuilder.appendWhereEscapeString(str5);
                    if (this.mUseStrictPhoneNumberComparation) {
                        str4 = ", 1)";
                    } else {
                        str4 = ", 0, " + this.mMinMatch + ")";
                    }
                    sQLiteQueryBuilder.appendWhere(str4);
                } else {
                    sQLiteQueryBuilder.appendWhere("presentation!=1");
                }
            } else {
                throw new IllegalArgumentException("Unknown URL " + uri);
            }
        }
        int intParam = getIntParam(uri, "limit", 0);
        int intParam2 = getIntParam(uri, "offset", 0);
        if (intParam > 0) {
            str3 = intParam2 + "," + intParam;
        } else {
            str3 = null;
        }
        Cursor cursorQuery = sQLiteQueryBuilder.query(this.mDbHelper.getReadableDatabase(), strArr, selectionBuilder.build(), strArr2, null, null, str2, str3);
        if (cursorQuery != null) {
            cursorQuery.setNotificationUri(getContext().getContentResolver(), CallLog.CONTENT_URI);
        }
        return cursorQuery;
    }

    private void queryForTesting(Uri uri) {
        if (uri.getBooleanQueryParameter(PARAM_KEY_QUERY_FOR_TESTING, false)) {
            if (!getCallingPackage().equals("com.android.providers.contacts")) {
                throw new IllegalArgumentException("query_for_testing set from foreign package " + getCallingPackage());
            }
            String queryParameter = uri.getQueryParameter(PARAM_KEY_SET_TIME_FOR_TESTING);
            if (queryParameter != null) {
                if (queryParameter.equals("null")) {
                    sTimeForTestMillis = null;
                } else {
                    sTimeForTestMillis = Long.valueOf(Long.parseLong(queryParameter));
                }
            }
        }
    }

    @VisibleForTesting
    static Long getTimeForTestMillis() {
        return sTimeForTestMillis;
    }

    private int getIntParam(Uri uri, String str, int i) {
        String queryParameter = uri.getQueryParameter(str);
        if (queryParameter == null) {
            return i;
        }
        try {
            return Integer.parseInt(queryParameter);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Integer required for " + str + " parameter but value '" + queryParameter + "' was found instead.", e);
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int iMatch = sURIMatcher.match(uri);
        if (iMatch == 1) {
            return "vnd.android.cursor.dir/calls";
        }
        if (iMatch == 2) {
            return "vnd.android.cursor.item/calls";
        }
        if (iMatch == 3) {
            return "vnd.android.cursor.dir/calls";
        }
        throw new IllegalArgumentException("Unknown URI: " + uri);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        int iIntValue = applyingBatch() ? this.mCallingUid.get().intValue() : Binder.getCallingUid();
        this.mStats.incrementInsertStats(iIntValue, applyingBatch());
        try {
            return insertInternal(uri, contentValues);
        } finally {
            this.mStats.finishOperation(iIntValue);
        }
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int iIntValue = applyingBatch() ? this.mCallingUid.get().intValue() : Binder.getCallingUid();
        this.mStats.incrementInsertStats(iIntValue, applyingBatch());
        try {
            return updateInternal(uri, contentValues, str, strArr);
        } finally {
            this.mStats.finishOperation(iIntValue);
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        int iIntValue = applyingBatch() ? this.mCallingUid.get().intValue() : Binder.getCallingUid();
        this.mStats.incrementInsertStats(iIntValue, applyingBatch());
        try {
            return deleteInternal(uri, str, strArr);
        } finally {
            this.mStats.finishOperation(iIntValue);
        }
    }

    private Uri insertInternal(Uri uri, ContentValues contentValues) {
        if (VERBOSE_LOGGING) {
            Log.v("CallLogProvider", "insert: uri=" + uri + "  values=[" + contentValues + "] CPID=" + Binder.getCallingPid() + " CUID=" + Binder.getCallingUid());
        }
        waitForAccess(this.mReadAccessLatch);
        DbQueryUtils.checkForSupportedColumns(sCallsProjectionMap, contentValues);
        if (hasVoicemailValue(contentValues)) {
            checkIsAllowVoicemailRequest(uri);
            this.mVoicemailPermissions.checkCallerHasWriteAccess(getCallingPackage());
        }
        if (this.mCallsInserter == null) {
            this.mCallsInserter = new DatabaseUtils.InsertHelper(this.mDbHelper.getWritableDatabase(), "calls");
        }
        ContentValues contentValues2 = new ContentValues(contentValues);
        this.mCallLogInsertionHelper.addComputedValues(contentValues2);
        long jInsert = createDatabaseModifier(this.mCallsInserter).insert(contentValues2);
        if (jInsert > 0) {
            return ContentUris.withAppendedId(uri, jInsert);
        }
        return null;
    }

    private int updateInternal(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (VERBOSE_LOGGING) {
            Log.v("CallLogProvider", "update: uri=" + uri + "  selection=[" + str + "]  args=" + Arrays.toString(strArr) + "  values=[" + contentValues + "] CPID=" + Binder.getCallingPid() + " CUID=" + Binder.getCallingUid() + " User=" + UserUtils.getCurrentUserHandle(getContext()));
        }
        waitForAccess(this.mReadAccessLatch);
        DbQueryUtils.checkForSupportedColumns(sCallsProjectionMap, contentValues);
        if (hasVoicemailValue(contentValues)) {
            checkIsAllowVoicemailRequest(uri);
        }
        SelectionBuilder selectionBuilder = new SelectionBuilder(str);
        checkVoicemailPermissionAndAddRestriction(uri, selectionBuilder, false);
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("calls");
        sQLiteQueryBuilder.setProjectionMap(sCallsProjectionMap);
        sQLiteQueryBuilder.setStrict(true);
        if (!this.mVoicemailPermissions.callerHasReadAccess(getCallingPackage())) {
            sQLiteQueryBuilder.setStrictGrammar(true);
        }
        SQLiteDatabase writableDatabase = this.mDbHelper.getWritableDatabase();
        int iMatch = sURIMatcher.match(uri);
        if (iMatch != 1) {
            if (iMatch == 2) {
                selectionBuilder.addClause(DbQueryUtils.getEqualityClause("_id", parseCallIdFromUri(uri)));
            } else {
                throw new UnsupportedOperationException("Cannot update URL: " + uri);
            }
        }
        int iUpdate = sQLiteQueryBuilder.update(writableDatabase, contentValues, selectionBuilder.build(), strArr);
        if (iUpdate > 0) {
            DbModifierWithNotification.notifyCallLogChange(getContext());
        }
        return iUpdate;
    }

    private int deleteInternal(Uri uri, String str, String[] strArr) {
        if (VERBOSE_LOGGING) {
            Log.v("CallLogProvider", "delete: uri=" + uri + "  selection=[" + str + "]  args=" + Arrays.toString(strArr) + " CPID=" + Binder.getCallingPid() + " CUID=" + Binder.getCallingUid() + " User=" + UserUtils.getCurrentUserHandle(getContext()));
        }
        waitForAccess(this.mReadAccessLatch);
        SelectionBuilder selectionBuilder = new SelectionBuilder(str);
        checkVoicemailPermissionAndAddRestriction(uri, selectionBuilder, false);
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables("calls");
        sQLiteQueryBuilder.setProjectionMap(sCallsProjectionMap);
        sQLiteQueryBuilder.setStrict(true);
        if (!this.mVoicemailPermissions.callerHasReadAccess(getCallingPackage())) {
            sQLiteQueryBuilder.setStrictGrammar(true);
        }
        SQLiteDatabase writableDatabase = this.mDbHelper.getWritableDatabase();
        if (sURIMatcher.match(uri) == 1) {
            int iDelete = sQLiteQueryBuilder.delete(writableDatabase, selectionBuilder.build(), strArr);
            if (iDelete > 0) {
                DbModifierWithNotification.notifyCallLogChange(getContext());
            }
            return iDelete;
        }
        throw new UnsupportedOperationException("Cannot delete that URL: " + uri);
    }

    void adjustForNewPhoneAccount(PhoneAccountHandle phoneAccountHandle) {
        this.mTaskScheduler.scheduleTask(1, phoneAccountHandle);
    }

    private DatabaseModifier createDatabaseModifier(DatabaseUtils.InsertHelper insertHelper) {
        return new DbModifierWithNotification("calls", insertHelper, getContext());
    }

    private boolean hasVoicemailValue(ContentValues contentValues) {
        return VOICEMAIL_TYPE.equals(contentValues.getAsInteger("type"));
    }

    private void checkVoicemailPermissionAndAddRestriction(Uri uri, SelectionBuilder selectionBuilder, boolean z) {
        if (!isAllowVoicemailRequest(uri)) {
            selectionBuilder.addClause(EXCLUDE_VOICEMAIL_SELECTION);
        } else if (z) {
            this.mVoicemailPermissions.checkCallerHasReadAccess(getCallingPackage());
        } else {
            this.mVoicemailPermissions.checkCallerHasWriteAccess(getCallingPackage());
        }
    }

    private boolean isAllowVoicemailRequest(Uri uri) {
        return uri.getBooleanQueryParameter("allow_voicemails", false);
    }

    private void checkIsAllowVoicemailRequest(Uri uri) {
        if (!isAllowVoicemailRequest(uri)) {
            throw new IllegalArgumentException(String.format("Uri %s cannot be used for voicemail record. Please set '%s=true' in the uri.", uri, "allow_voicemails"));
        }
    }

    private long parseCallIdFromUri(Uri uri) {
        try {
            return Long.parseLong(uri.getPathSegments().get(1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid call id in uri: " + uri, e);
        }
    }

    private void syncEntries() {
        if (isShadow()) {
            return;
        }
        UserManager userManager = UserUtils.getUserManager(getContext());
        if (CallLog.Calls.shouldHaveSharedCallLogEntries(getContext(), userManager, userManager.getUserHandle())) {
            int userHandle = userManager.getUserHandle();
            if (userManager.isSystemUser()) {
                syncEntriesFrom(0, true, false);
            } else {
                syncEntriesFrom(0, false, true);
                syncEntriesFrom(userHandle, true, false);
            }
        }
    }

    private void syncEntriesFrom(int i, boolean z, boolean z2) {
        Uri uri = z ? CallLog.Calls.SHADOW_CONTENT_URI : CallLog.Calls.CONTENT_URI;
        long lastSyncTime = getLastSyncTime(z);
        Uri uriMaybeAddUserId = ContentProvider.maybeAddUserId(uri, i);
        ContentResolver contentResolver = getContext().getContentResolver();
        StringBuilder sb = new StringBuilder();
        sb.append("(" + EXCLUDE_VOICEMAIL_SELECTION + ") AND (date> ?)");
        if (z2) {
            sb.append(" AND (add_for_all_users=1)");
        }
        Cursor cursorQuery = contentResolver.query(uriMaybeAddUserId, CALL_LOG_SYNC_PROJECTION, sb.toString(), new String[]{String.valueOf(lastSyncTime)}, "date ASC");
        if (cursorQuery == null) {
            return;
        }
        try {
            long jCopyEntriesFromCursor = copyEntriesFromCursor(cursorQuery, lastSyncTime, z);
            if (z) {
                contentResolver.delete(uriMaybeAddUserId, "date<= ?", new String[]{String.valueOf(jCopyEntriesFromCursor)});
            }
        } finally {
            cursorQuery.close();
        }
    }

    private void adjustForNewPhoneAccountInternal(PhoneAccountHandle phoneAccountHandle) {
        PhoneAccount phoneAccount;
        String[] strArr = {phoneAccountHandle.getComponentName().flattenToString(), phoneAccountHandle.getId()};
        Cursor cursorQuery = query(CallLog.Calls.CONTENT_URI, MINIMAL_PROJECTION, "subscription_component_name =? AND subscription_id =?", strArr, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() >= 1) {
                    this.mDbHelper.getWritableDatabase().execSQL("UPDATE calls SET phone_account_hidden=0 WHERE subscription_component_name=? AND subscription_id=?;", strArr);
                } else {
                    TelecomManager telecomManager = (TelecomManager) getContext().getSystemService(TelecomManager.class);
                    if (telecomManager != null && (phoneAccount = telecomManager.getPhoneAccount(phoneAccountHandle)) != null && phoneAccount.getAddress() != null) {
                        this.mDbHelper.getWritableDatabase().execSQL("UPDATE calls SET phone_account_hidden=0 WHERE phone_account_address=?;", new String[]{phoneAccount.getAddress().toString()});
                    }
                }
            } finally {
                cursorQuery.close();
            }
        }
    }

    @VisibleForTesting
    long copyEntriesFromCursor(Cursor cursor, long j, boolean z) {
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase writableDatabase = this.mDbHelper.getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            String[] strArr = new String[2];
            cursor.moveToPosition(-1);
            long jLongValue = 0;
            while (cursor.moveToNext()) {
                contentValues.clear();
                DatabaseUtils.cursorRowToContentValues(cursor, contentValues);
                String asString = contentValues.getAsString("date");
                String asString2 = contentValues.getAsString("number");
                if (asString != null && asString2 != null) {
                    if (cursor.isLast()) {
                        try {
                            jLongValue = Long.valueOf(asString).longValue();
                        } catch (NumberFormatException unused) {
                            Log.e("CallLogProvider", "Call log entry does not contain valid start time: " + asString);
                        }
                    }
                    strArr[0] = asString;
                    strArr[1] = asString2;
                    if (DatabaseUtils.queryNumEntries(writableDatabase, "calls", "date = ? AND number = ?", strArr) <= 0) {
                        writableDatabase.insert("calls", null, contentValues);
                    }
                }
            }
            if (jLongValue > j) {
                setLastTimeSynced(jLongValue, z);
            }
            writableDatabase.setTransactionSuccessful();
            return jLongValue;
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @VisibleForTesting
    long getLastSyncTime(boolean z) {
        try {
            return Long.valueOf(this.mDbHelper.getProperty(getLastSyncTimePropertyName(z), "0")).longValue();
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }

    private void setLastTimeSynced(long j, boolean z) {
        this.mDbHelper.setProperty(getLastSyncTimePropertyName(z), String.valueOf(j));
    }

    private static void waitForAccess(CountDownLatch countDownLatch) {
        if (countDownLatch == null) {
            return;
        }
        while (true) {
            try {
                countDownLatch.await();
                return;
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performBackgroundTask(int i, Object obj) {
        if (i != 0) {
            if (i == 1) {
                adjustForNewPhoneAccountInternal((PhoneAccountHandle) obj);
            }
        } else {
            try {
                syncEntries();
            } finally {
                this.mReadAccessLatch.countDown();
                this.mReadAccessLatch = null;
            }
        }
    }

    @Override // android.content.ContentProvider
    public void shutdown() {
        this.mTaskScheduler.shutdownForTest();
    }

    @Override // android.content.ContentProvider
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        this.mStats.dump(printWriter, "  ");
    }
}
