package com.android.providers.telephony;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import com.android.providers.telephony.TelephonyBackupAgent;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class MmsSmsProvider extends ContentProvider {
    private static final String[] MMS_ONLY_COLUMNS;
    private static final String[] MMS_SMS_COLUMNS;
    private static final String[] SMS_ONLY_COLUMNS;
    private static final String[] UNION_COLUMNS;
    private int mMinMatch;
    private SQLiteOpenHelper mOpenHelper;
    private boolean mUseStrictPhoneNumberComparation;
    private static final UriMatcher URI_MATCHER = new UriMatcher(-1);
    private static final String[] THREADS_COLUMNS = {"_id", "date", "recipient_ids", "message_count"};
    private static final String[] CANONICAL_ADDRESSES_COLUMNS_1 = {"address"};
    private static final String[] CANONICAL_ADDRESSES_COLUMNS_2 = {"_id", "address"};
    private static final Set<String> MMS_COLUMNS = new HashSet();
    private static final Set<String> SMS_COLUMNS = new HashSet();
    private static final String[] ID_PROJECTION = {"_id"};
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final String[] SEARCH_STRING = new String[1];

    private static String handleNullSortOrder(String str) {
        return str == null ? "normalized_date ASC" : str;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return "vnd.android-dir/mms-sms";
    }

    static {
        String[] strArr = {"_id", "date", "date_sent", "read", "thread_id", "locked", "sub_id"};
        MMS_SMS_COLUMNS = strArr;
        String[] strArr2 = {"ct_cls", "ct_l", "ct_t", "d_rpt", "exp", "m_cls", "m_id", "m_size", "m_type", "msg_box", "pri", "read_status", "resp_st", "resp_txt", "retr_st", "retr_txt_cs", "rpt_a", "rr", "st", "sub", "sub_cs", "tr_id", "v", "text_only"};
        MMS_ONLY_COLUMNS = strArr2;
        String[] strArr3 = {"address", "body", "person", "reply_path_present", "service_center", "status", "subject", "type", "error_code"};
        SMS_ONLY_COLUMNS = strArr3;
        UNION_COLUMNS = new String[strArr.length + strArr2.length + strArr3.length];
        URI_MATCHER.addURI("mms-sms", "conversations", 0);
        URI_MATCHER.addURI("mms-sms", "complete-conversations", 7);
        URI_MATCHER.addURI("mms-sms", "conversations/#", 1);
        URI_MATCHER.addURI("mms-sms", "conversations/#/recipients", 2);
        URI_MATCHER.addURI("mms-sms", "conversations/#/subject", 9);
        URI_MATCHER.addURI("mms-sms", "conversations/obsolete", 11);
        URI_MATCHER.addURI("mms-sms", "messages/byphone/*", 3);
        URI_MATCHER.addURI("mms-sms", "threadID", 4);
        URI_MATCHER.addURI("mms-sms", "canonical-address/#", 5);
        URI_MATCHER.addURI("mms-sms", "canonical-addresses", 13);
        URI_MATCHER.addURI("mms-sms", "search", 14);
        URI_MATCHER.addURI("mms-sms", "searchSuggest", 15);
        URI_MATCHER.addURI("mms-sms", "pending", 6);
        URI_MATCHER.addURI("mms-sms", "undelivered", 8);
        URI_MATCHER.addURI("mms-sms", "notifications", 10);
        URI_MATCHER.addURI("mms-sms", "draft", 12);
        URI_MATCHER.addURI("mms-sms", "locked", 16);
        URI_MATCHER.addURI("mms-sms", "locked/#", 17);
        URI_MATCHER.addURI("mms-sms", "messageIdToThread", 18);
        initializeColumnSets();
    }

    private static String getTextSearchQuery(String str, String str2) {
        return ("SELECT " + str + "._id AS _id,thread_id,address,body,date,date_sent,index_text,words._id FROM " + str + ",words WHERE (index_text MATCH ? AND " + str + "._id=words.source_id AND words.table_to_use=1)") + " UNION " + ("SELECT " + str2 + "._id,thread_id,addr.address,part.text AS body," + str2 + ".date," + str2 + ".date_sent,index_text,words._id FROM " + str2 + ",part,addr,words WHERE ((part.mid=" + str2 + "._id) AND (addr.msg_id=" + str2 + "._id) AND (addr.type=151) AND (part.ct='text/plain') AND (index_text MATCH ?) AND (part._id = words.source_id) AND (words.table_to_use=2))") + " GROUP BY thread_id ORDER BY thread_id ASC, date DESC";
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        setAppOps(14, 15);
        this.mOpenHelper = MmsSmsDatabaseHelper.getInstanceForCe(getContext());
        this.mUseStrictPhoneNumberComparation = getContext().getResources().getBoolean(android.R.bool.config_cecRoutingControl_userConfigurable);
        this.mMinMatch = getContext().getResources().getInteger(android.R.integer.config_fingerprintMaxTemplatesPerUser);
        TelephonyBackupAgent.DeferredSmsMmsRestoreService.startIfFilesExist(getContext());
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:90:0x0372  */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.database.Cursor query(android.net.Uri r19, java.lang.String[] r20, java.lang.String r21, java.lang.String[] r22, java.lang.String r23) {
        /*
            Method dump skipped, instruction units count: 938
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.MmsSmsProvider.query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    private long getSingleAddressId(String str) {
        String str2;
        String[] strArr;
        boolean zIsEmailAddress = Telephony.Mms.isEmailAddress(str);
        boolean zIsPhoneNumber = Telephony.Mms.isPhoneNumber(str);
        String lowerCase = zIsEmailAddress ? str.toLowerCase() : str;
        String string = "address=?";
        if (zIsPhoneNumber) {
            StringBuilder sb = new StringBuilder();
            sb.append("address=?");
            sb.append(" OR PHONE_NUMBERS_EQUAL(address, ?, ");
            if (this.mUseStrictPhoneNumberComparation) {
                str2 = "1)";
            } else {
                str2 = "0, " + this.mMinMatch + ")";
            }
            sb.append(str2);
            string = sb.toString();
            strArr = new String[]{lowerCase, lowerCase};
        } else {
            strArr = new String[]{lowerCase};
        }
        String[] strArr2 = strArr;
        Cursor cursor = null;
        try {
            Cursor cursorQuery = this.mOpenHelper.getReadableDatabase().query("canonical_addresses", ID_PROJECTION, string, strArr2, null, null, null);
            if (cursorQuery.getCount() == 0) {
                ContentValues contentValues = new ContentValues(1);
                contentValues.put("address", lowerCase);
                long jInsert = this.mOpenHelper.getWritableDatabase().insert("canonical_addresses", "address", contentValues);
                Log.d("MmsSmsProvider", "getSingleAddressId: insert new canonical_address for xxxxxx, _id=" + jInsert);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return jInsert;
            }
            long j = cursorQuery.moveToFirst() ? cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("_id")) : -1L;
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return j;
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    private Set<Long> getAddressIds(List<String> list) {
        HashSet hashSet = new HashSet(list.size());
        for (String str : list) {
            if (!str.equals("insert-address-token")) {
                long singleAddressId = getSingleAddressId(str);
                if (singleAddressId != -1) {
                    hashSet.add(Long.valueOf(singleAddressId));
                } else {
                    Log.e("MmsSmsProvider", "getAddressIds: address ID not found for " + str);
                }
            }
        }
        return hashSet;
    }

    private long[] getSortedSet(Set<Long> set) {
        int size = set.size();
        long[] jArr = new long[size];
        Iterator<Long> it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            jArr[i] = it.next().longValue();
            i++;
        }
        if (size > 1) {
            Arrays.sort(jArr);
        }
        return jArr;
    }

    private String getSpaceSeparatedNumbers(long[] jArr) {
        int length = jArr.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(' ');
            }
            sb.append(jArr[i]);
        }
        return sb.toString();
    }

    private void insertThread(String str, int i) {
        ContentValues contentValues = new ContentValues(4);
        long jCurrentTimeMillis = System.currentTimeMillis();
        contentValues.put("date", Long.valueOf(jCurrentTimeMillis - (jCurrentTimeMillis % 1000)));
        contentValues.put("recipient_ids", str);
        if (i > 1) {
            contentValues.put("type", (Integer) 1);
        }
        contentValues.put("message_count", (Integer) 0);
        Log.d("MmsSmsProvider", "insertThread: created new thread_id " + this.mOpenHelper.getWritableDatabase().insert("threads", null, contentValues) + " for recipientIds xxxxxxx");
        getContext().getContentResolver().notifyChange(Telephony.MmsSms.CONTENT_URI, null, true, -1);
    }

    private synchronized Cursor getThreadId(List<String> list) {
        Set<Long> addressIds = getAddressIds(list);
        String spaceSeparatedNumbers = "";
        Cursor cursorRawQuery = null;
        if (addressIds.size() == 0) {
            Log.e("MmsSmsProvider", "getThreadId: NO receipients specified -- NOT creating thread", new Exception());
            return null;
        }
        if (addressIds.size() == 1) {
            Iterator<Long> it = addressIds.iterator();
            while (it.hasNext()) {
                spaceSeparatedNumbers = Long.toString(it.next().longValue());
            }
        } else {
            spaceSeparatedNumbers = getSpaceSeparatedNumbers(getSortedSet(addressIds));
        }
        if (Log.isLoggable("MmsSmsProvider", 2)) {
            Log.d("MmsSmsProvider", "getThreadId: recipientIds (selectionArgs) =xxxxxxx");
        }
        String[] strArr = {spaceSeparatedNumbers};
        SQLiteDatabase readableDatabase = this.mOpenHelper.getReadableDatabase();
        readableDatabase.beginTransaction();
        try {
            cursorRawQuery = readableDatabase.rawQuery("SELECT _id FROM threads WHERE recipient_ids=?", strArr);
            if (cursorRawQuery.getCount() == 0) {
                cursorRawQuery.close();
                Log.d("MmsSmsProvider", "getThreadId: create new thread_id for recipients xxxxxxxx");
                insertThread(spaceSeparatedNumbers, list.size());
                cursorRawQuery = readableDatabase.rawQuery("SELECT _id FROM threads WHERE recipient_ids=?", strArr);
            }
            readableDatabase.setTransactionSuccessful();
        } finally {
            try {
            } finally {
            }
        }
        if (cursorRawQuery != null && cursorRawQuery.getCount() > 1) {
            Log.w("MmsSmsProvider", "getThreadId: why is cursorCount=" + cursorRawQuery.getCount());
        }
        return cursorRawQuery;
    }

    private static String concatSelections(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        if (TextUtils.isEmpty(str2)) {
            return str;
        }
        return str + " AND " + str2;
    }

    private static String[] handleNullMessageProjection(String[] strArr) {
        return strArr == null ? UNION_COLUMNS : strArr;
    }

    private static String[] handleNullThreadsProjection(String[] strArr) {
        return strArr == null ? THREADS_COLUMNS : strArr;
    }

    private Cursor getSimpleConversations(String[] strArr, String str, String[] strArr2, String str2) {
        return this.mOpenHelper.getReadableDatabase().query("threads", strArr, str, strArr2, null, null, " date DESC");
    }

    private Cursor getDraftThread(String[] strArr, String str, String str2, String str3, String str4) {
        String[] strArr2 = {"_id", "thread_id"};
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        SQLiteQueryBuilder sQLiteQueryBuilder2 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables(str4);
        sQLiteQueryBuilder2.setTables(str3);
        String strBuildUnionSubQuery = sQLiteQueryBuilder.buildUnionSubQuery("transport_type", strArr2, MMS_COLUMNS, 1, "mms", concatSelections(str, "msg_box=3"), null, null);
        String strBuildUnionSubQuery2 = sQLiteQueryBuilder2.buildUnionSubQuery("transport_type", strArr2, SMS_COLUMNS, 1, "sms", concatSelections(str, "type=3"), null, null);
        SQLiteQueryBuilder sQLiteQueryBuilder3 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder3.setDistinct(true);
        String strBuildUnionQuery = sQLiteQueryBuilder3.buildUnionQuery(new String[]{strBuildUnionSubQuery, strBuildUnionSubQuery2}, null, null);
        SQLiteQueryBuilder sQLiteQueryBuilder4 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder4.setTables("(" + strBuildUnionQuery + ")");
        return this.mOpenHelper.getReadableDatabase().rawQuery(sQLiteQueryBuilder4.buildQuery(strArr, null, null, null, str2, null), EMPTY_STRING_ARRAY);
    }

    private Cursor getConversations(String[] strArr, String str, String str2, String str3, String str4) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        SQLiteQueryBuilder sQLiteQueryBuilder2 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables(str4);
        sQLiteQueryBuilder2.setTables(str3);
        String[] strArrHandleNullMessageProjection = handleNullMessageProjection(strArr);
        String[] strArrMakeProjectionWithDateAndThreadId = makeProjectionWithDateAndThreadId(UNION_COLUMNS, 1000);
        String[] strArrMakeProjectionWithDateAndThreadId2 = makeProjectionWithDateAndThreadId(UNION_COLUMNS, 1);
        String strBuildUnionSubQuery = sQLiteQueryBuilder.buildUnionSubQuery("transport_type", strArrMakeProjectionWithDateAndThreadId, MMS_COLUMNS, 1, "mms", concatSelections(str, "(msg_box != 3 AND (m_type = 128 OR m_type = 132 OR m_type = 130))"), "thread_id", "date = MAX(date)");
        String strBuildUnionSubQuery2 = sQLiteQueryBuilder2.buildUnionSubQuery("transport_type", strArrMakeProjectionWithDateAndThreadId2, SMS_COLUMNS, 1, "sms", concatSelections(str, "(type != 3)"), "thread_id", "date = MAX(date)");
        SQLiteQueryBuilder sQLiteQueryBuilder3 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder3.setDistinct(true);
        String strBuildUnionQuery = sQLiteQueryBuilder3.buildUnionQuery(new String[]{strBuildUnionSubQuery, strBuildUnionSubQuery2}, null, null);
        SQLiteQueryBuilder sQLiteQueryBuilder4 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder4.setTables("(" + strBuildUnionQuery + ")");
        return this.mOpenHelper.getReadableDatabase().rawQuery(sQLiteQueryBuilder4.buildQuery(strArrHandleNullMessageProjection, null, "tid", "normalized_date = MAX(normalized_date)", str2, null), EMPTY_STRING_ARRAY);
    }

    private Cursor getFirstLockedMessage(String[] strArr, String str, String str2, String str3, String str4) {
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        SQLiteQueryBuilder sQLiteQueryBuilder2 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables(str4);
        sQLiteQueryBuilder2.setTables(str3);
        String[] strArr2 = {"_id"};
        String strBuildUnionSubQuery = sQLiteQueryBuilder.buildUnionSubQuery("transport_type", strArr2, null, 1, "mms", str, "_id", "locked=1");
        String strBuildUnionSubQuery2 = sQLiteQueryBuilder2.buildUnionSubQuery("transport_type", strArr2, null, 1, "sms", str, "_id", "locked=1");
        SQLiteQueryBuilder sQLiteQueryBuilder3 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder3.setDistinct(true);
        return this.mOpenHelper.getReadableDatabase().rawQuery(sQLiteQueryBuilder3.buildUnionQuery(new String[]{strBuildUnionSubQuery, strBuildUnionSubQuery2}, null, "1"), EMPTY_STRING_ARRAY);
    }

    private Cursor getCompleteConversations(String[] strArr, String str, String str2, String str3, String str4) {
        return this.mOpenHelper.getReadableDatabase().rawQuery(buildConversationQuery(strArr, str, str2, str3, str4), EMPTY_STRING_ARRAY);
    }

    private String[] makeProjectionWithDateAndThreadId(String[] strArr, int i) {
        int length = strArr.length;
        String[] strArr2 = new String[length + 2];
        strArr2[0] = "thread_id AS tid";
        strArr2[1] = "date * " + i + " AS normalized_date";
        for (int i2 = 0; i2 < length; i2++) {
            strArr2[i2 + 2] = strArr[i2];
        }
        return strArr2;
    }

    private Cursor getConversationMessages(String str, String[] strArr, String str2, String str3, String str4, String str5) {
        try {
            Long.parseLong(str);
            return this.mOpenHelper.getReadableDatabase().rawQuery(buildConversationQuery(strArr, concatSelections(str2, "thread_id = " + str), str3, str4, str5), EMPTY_STRING_ARRAY);
        } catch (NumberFormatException unused) {
            Log.e("MmsSmsProvider", "Thread ID must be a Long.");
            return null;
        }
    }

    private Cursor getMessagesByPhoneNumber(String str, String[] strArr, String str2, String str3, String str4, String str5) {
        String str6;
        String str7;
        String strSqlEscapeString = DatabaseUtils.sqlEscapeString(str);
        String strConcatSelections = concatSelections(str2, str5 + "._id = matching_addresses.address_msg_id");
        StringBuilder sb = new StringBuilder();
        sb.append("(address=");
        sb.append(strSqlEscapeString);
        sb.append(" OR PHONE_NUMBERS_EQUAL(address, ");
        sb.append(strSqlEscapeString);
        if (this.mUseStrictPhoneNumberComparation) {
            str6 = ", 1))";
        } else {
            str6 = ", 0, " + this.mMinMatch + "))";
        }
        sb.append(str6);
        String strConcatSelections2 = concatSelections(str2, sb.toString());
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        SQLiteQueryBuilder sQLiteQueryBuilder2 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setDistinct(true);
        sQLiteQueryBuilder2.setDistinct(true);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str5);
        sb2.append(", (SELECT msg_id AS address_msg_id FROM addr WHERE (address=");
        sb2.append(strSqlEscapeString);
        sb2.append(" OR PHONE_NUMBERS_EQUAL(addr.address, ");
        sb2.append(strSqlEscapeString);
        if (this.mUseStrictPhoneNumberComparation) {
            str7 = ", 1))) ";
        } else {
            str7 = ", 0, " + this.mMinMatch + "))) ";
        }
        sb2.append(str7);
        sb2.append("AS matching_addresses");
        sQLiteQueryBuilder.setTables(sb2.toString());
        sQLiteQueryBuilder2.setTables(str4);
        String[] strArrHandleNullMessageProjection = handleNullMessageProjection(strArr);
        String strBuildUnionSubQuery = sQLiteQueryBuilder.buildUnionSubQuery("transport_type", strArrHandleNullMessageProjection, MMS_COLUMNS, 0, "mms", strConcatSelections, null, null);
        String strBuildUnionSubQuery2 = sQLiteQueryBuilder2.buildUnionSubQuery("transport_type", strArrHandleNullMessageProjection, SMS_COLUMNS, 0, "sms", strConcatSelections2, null, null);
        SQLiteQueryBuilder sQLiteQueryBuilder3 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder3.setDistinct(true);
        return this.mOpenHelper.getReadableDatabase().rawQuery(sQLiteQueryBuilder3.buildUnionQuery(new String[]{strBuildUnionSubQuery, strBuildUnionSubQuery2}, str3, null), EMPTY_STRING_ARRAY);
    }

    private Cursor getConversationById(String str, String[] strArr, String str2, String[] strArr2, String str3) {
        try {
            Long.parseLong(str);
            String strConcatSelections = concatSelections(str2, "_id=" + str);
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            String[] strArrHandleNullThreadsProjection = handleNullThreadsProjection(strArr);
            sQLiteQueryBuilder.setDistinct(true);
            sQLiteQueryBuilder.setTables("threads");
            return sQLiteQueryBuilder.query(this.mOpenHelper.getReadableDatabase(), strArrHandleNullThreadsProjection, strConcatSelections, strArr2, str3, null, null);
        } catch (NumberFormatException unused) {
            Log.e("MmsSmsProvider", "Thread ID must be a Long.");
            return null;
        }
    }

    private static String joinPduAndPendingMsgTables(String str) {
        return str + " LEFT JOIN pending_msgs ON " + str + "._id = pending_msgs.msg_id";
    }

    private static String[] createMmsProjection(String[] strArr, String str) {
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals("_id")) {
                strArr2[i] = str + "._id";
            } else {
                strArr2[i] = strArr[i];
            }
        }
        return strArr2;
    }

    private Cursor getUndeliveredMessages(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4) {
        String[] strArrCreateMmsProjection = createMmsProjection(strArr, str4);
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        SQLiteQueryBuilder sQLiteQueryBuilder2 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setTables(joinPduAndPendingMsgTables(str4));
        sQLiteQueryBuilder2.setTables(str3);
        String strConcatSelections = concatSelections(str, "msg_box = 4");
        String strConcatSelections2 = concatSelections(str, "(type = 4 OR type = 5 OR type = 6)");
        String[] strArrHandleNullMessageProjection = handleNullMessageProjection(strArr);
        String[] strArrMakeProjectionWithDateAndThreadId = makeProjectionWithDateAndThreadId(handleNullMessageProjection(strArrCreateMmsProjection), 1000);
        String[] strArrMakeProjectionWithDateAndThreadId2 = makeProjectionWithDateAndThreadId(strArrHandleNullMessageProjection, 1);
        HashSet hashSet = new HashSet(MMS_COLUMNS);
        hashSet.add(str4 + "._id");
        hashSet.add("err_type");
        String strBuildUnionSubQuery = sQLiteQueryBuilder.buildUnionSubQuery("transport_type", strArrMakeProjectionWithDateAndThreadId, hashSet, 1, "mms", strConcatSelections, null, null);
        String strBuildUnionSubQuery2 = sQLiteQueryBuilder2.buildUnionSubQuery("transport_type", strArrMakeProjectionWithDateAndThreadId2, SMS_COLUMNS, 1, "sms", strConcatSelections2, null, null);
        SQLiteQueryBuilder sQLiteQueryBuilder3 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder3.setDistinct(true);
        String strBuildUnionQuery = sQLiteQueryBuilder3.buildUnionQuery(new String[]{strBuildUnionSubQuery2, strBuildUnionSubQuery}, null, null);
        SQLiteQueryBuilder sQLiteQueryBuilder4 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder4.setTables("(" + strBuildUnionQuery + ")");
        return this.mOpenHelper.getReadableDatabase().rawQuery(sQLiteQueryBuilder4.buildQuery(strArrHandleNullMessageProjection, null, null, null, str2, null), EMPTY_STRING_ARRAY);
    }

    private static String[] makeProjectionWithNormalizedDate(String[] strArr, int i) {
        int length = strArr.length;
        String[] strArr2 = new String[length + 1];
        strArr2[0] = "date * " + i + " AS normalized_date";
        System.arraycopy(strArr, 0, strArr2, 1, length);
        return strArr2;
    }

    private static String buildConversationQuery(String[] strArr, String str, String str2, String str3, String str4) {
        String[] strArrCreateMmsProjection = createMmsProjection(strArr, str4);
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        SQLiteQueryBuilder sQLiteQueryBuilder2 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder.setDistinct(true);
        sQLiteQueryBuilder2.setDistinct(true);
        sQLiteQueryBuilder.setTables(joinPduAndPendingMsgTables(str4));
        sQLiteQueryBuilder2.setTables(str3);
        String[] strArrHandleNullMessageProjection = handleNullMessageProjection(strArr);
        String[] strArrMakeProjectionWithNormalizedDate = makeProjectionWithNormalizedDate(handleNullMessageProjection(strArrCreateMmsProjection), 1000);
        String[] strArrMakeProjectionWithNormalizedDate2 = makeProjectionWithNormalizedDate(strArrHandleNullMessageProjection, 1);
        HashSet hashSet = new HashSet(MMS_COLUMNS);
        hashSet.add(str4 + "._id");
        hashSet.add("err_type");
        String strBuildUnionSubQuery = sQLiteQueryBuilder.buildUnionSubQuery("transport_type", strArrMakeProjectionWithNormalizedDate, hashSet, 0, "mms", concatSelections(concatSelections(str, "msg_box != 3"), "(msg_box != 3 AND (m_type = 128 OR m_type = 132 OR m_type = 130))"), null, null);
        String strBuildUnionSubQuery2 = sQLiteQueryBuilder2.buildUnionSubQuery("transport_type", strArrMakeProjectionWithNormalizedDate2, SMS_COLUMNS, 0, "sms", concatSelections(str, "(type != 3)"), null, null);
        SQLiteQueryBuilder sQLiteQueryBuilder3 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder3.setDistinct(true);
        String strBuildUnionQuery = sQLiteQueryBuilder3.buildUnionQuery(new String[]{strBuildUnionSubQuery2, strBuildUnionSubQuery}, handleNullSortOrder(str2), null);
        SQLiteQueryBuilder sQLiteQueryBuilder4 = new SQLiteQueryBuilder();
        sQLiteQueryBuilder4.setTables("(" + strBuildUnionQuery + ")");
        return sQLiteQueryBuilder4.buildQuery(strArrHandleNullMessageProjection, null, null, null, str2, null);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        int iDeleteMessages;
        SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        Context context = getContext();
        int iMatch = URI_MATCHER.match(uri);
        if (iMatch == 0) {
            iDeleteMessages = MmsProvider.deleteMessages(context, writableDatabase, str, strArr, uri) + writableDatabase.delete("sms", str, strArr);
            MmsSmsDatabaseHelper.updateThreads(writableDatabase, null, null);
        } else if (iMatch == 1) {
            try {
                long j = Long.parseLong(uri.getLastPathSegment());
                iDeleteMessages = deleteConversation(uri, str, strArr);
                MmsSmsDatabaseHelper.updateThread(writableDatabase, j);
            } catch (NumberFormatException unused) {
                Log.e("MmsSmsProvider", "Thread ID must be a long.");
                iDeleteMessages = 0;
            }
        } else if (iMatch == 11) {
            iDeleteMessages = writableDatabase.delete("threads", "_id NOT IN (SELECT DISTINCT thread_id FROM sms where thread_id NOT NULL UNION SELECT DISTINCT thread_id FROM pdu where thread_id NOT NULL)", null);
        } else {
            throw new UnsupportedOperationException("MmsSmsProvider does not support deletes, inserts, or updates for this URI." + uri);
        }
        if (iDeleteMessages > 0) {
            context.getContentResolver().notifyChange(Telephony.MmsSms.CONTENT_URI, null, true, -1);
        }
        return iDeleteMessages;
    }

    private int deleteConversation(Uri uri, String str, String[] strArr) {
        String lastPathSegment = uri.getLastPathSegment();
        SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        String strConcatSelections = concatSelections(str, "thread_id = " + lastPathSegment);
        return MmsProvider.deleteMessages(getContext(), writableDatabase, strConcatSelections, strArr, uri) + writableDatabase.delete("sms", strConcatSelections, strArr);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        int iMatch = URI_MATCHER.match(uri);
        if (iMatch == 6) {
            return uri.buildUpon().appendPath(Long.toString(writableDatabase.insert("pending_msgs", null, contentValues))).build();
        }
        if (iMatch == 5) {
            return uri.buildUpon().appendPath(Long.toString(writableDatabase.insert("canonical_addresses", null, contentValues))).build();
        }
        throw new UnsupportedOperationException("MmsSmsProvider does not support deletes, inserts, or updates for this URI." + uri);
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int iUpdate;
        int callingUid = Binder.getCallingUid();
        String callingPackage = getCallingPackage();
        SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        int iMatch = URI_MATCHER.match(uri);
        if (iMatch == 0) {
            ContentValues contentValues2 = new ContentValues(1);
            if (contentValues.containsKey("archived")) {
                contentValues2.put("archived", contentValues.getAsBoolean("archived"));
            }
            iUpdate = writableDatabase.update("threads", contentValues2, str, strArr);
        } else if (iMatch == 1) {
            iUpdate = updateConversation(uri.getPathSegments().get(1), contentValues, str, strArr, callingUid, callingPackage);
        } else if (iMatch == 5) {
            String str2 = "_id=" + uri.getPathSegments().get(1);
            if (!TextUtils.isEmpty(str)) {
                str2 = str2 + " AND " + str;
            }
            iUpdate = writableDatabase.update("canonical_addresses", contentValues, str2, null);
        } else if (iMatch == 6) {
            iUpdate = writableDatabase.update("pending_msgs", contentValues, str, null);
        } else {
            throw new UnsupportedOperationException("MmsSmsProvider does not support deletes, inserts, or updates for this URI." + uri);
        }
        if (iUpdate > 0) {
            getContext().getContentResolver().notifyChange(Telephony.MmsSms.CONTENT_URI, null, true, -1);
        }
        return iUpdate;
    }

    private int updateConversation(String str, ContentValues contentValues, String str2, String[] strArr, int i, String str3) {
        try {
            Long.parseLong(str);
            if (ProviderUtil.shouldRemoveCreator(contentValues, i)) {
                Log.w("MmsSmsProvider", str3 + " tries to update CREATOR");
                contentValues.remove("creator");
                contentValues.remove("creator");
            }
            SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
            String strConcatSelections = concatSelections(str2, "thread_id=" + str);
            return writableDatabase.update("pdu", contentValues, strConcatSelections, strArr) + writableDatabase.update("sms", contentValues, strConcatSelections, strArr);
        } catch (NumberFormatException unused) {
            Log.e("MmsSmsProvider", "Thread ID must be a Long.");
            return 0;
        }
    }

    private static void initializeColumnSets() {
        int length = MMS_SMS_COLUMNS.length;
        int length2 = MMS_ONLY_COLUMNS.length;
        int length3 = SMS_ONLY_COLUMNS.length;
        HashSet hashSet = new HashSet();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            MMS_COLUMNS.add(MMS_SMS_COLUMNS[i2]);
            SMS_COLUMNS.add(MMS_SMS_COLUMNS[i2]);
            hashSet.add(MMS_SMS_COLUMNS[i2]);
        }
        for (int i3 = 0; i3 < length2; i3++) {
            MMS_COLUMNS.add(MMS_ONLY_COLUMNS[i3]);
            hashSet.add(MMS_ONLY_COLUMNS[i3]);
        }
        for (int i4 = 0; i4 < length3; i4++) {
            SMS_COLUMNS.add(SMS_ONLY_COLUMNS[i4]);
            hashSet.add(SMS_ONLY_COLUMNS[i4]);
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            UNION_COLUMNS[i] = (String) it.next();
            i++;
        }
    }

    @Override // android.content.ContentProvider
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(getContext());
        if (TextUtils.isEmpty(defaultSmsPackage)) {
            defaultSmsPackage = "None";
        }
        printWriter.println("Default SMS app: " + defaultSmsPackage);
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        if ("is_restoring".equals(str)) {
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("restoring", TelephonyBackupAgent.getIsRestoring());
            return bundle2;
        }
        Log.w("MmsSmsProvider", "Ignored unsupported " + str + " call");
        return null;
    }
}
