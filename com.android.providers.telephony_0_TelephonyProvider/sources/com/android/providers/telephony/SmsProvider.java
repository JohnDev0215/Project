package com.android.providers.telephony;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Binder;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.SubscriptionManager;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.providers.telephony.TelephonyBackupAgent;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SmsProvider extends ContentProvider {
    private static final UriMatcher sURLMatcher;

    @VisibleForTesting
    public SQLiteOpenHelper mCeOpenHelper;

    @VisibleForTesting
    public SQLiteOpenHelper mDeOpenHelper;
    private static final Uri NOTIFICATION_URI = Uri.parse("content://sms");
    private static final Uri ICC_URI = Uri.parse("content://sms/icc");
    private static final Uri ICC_SUBID_URI = Uri.parse("content://sms/icc_subId");
    private static final Integer ONE = 1;
    private static final String[] CONTACT_QUERY_PROJECTION = {"person"};
    private static final String[] ICC_COLUMNS = {"service_center_address", "address", "message_class", "body", "date", "status", "index_on_icc", "is_status_report", "transport_type", "type", "locked", "error_code", "_id"};
    private static final String[] sIDProjection = {"_id"};

    private int getMessageStatusForIcc(int i, boolean z) {
        if (i == 2) {
            return 5;
        }
        if (i == 4) {
            return 7;
        }
        return z ? 1 : 3;
    }

    public static String getSmsTable(boolean z) {
        return z ? "sms_restricted" : "sms";
    }

    private boolean isSupportedType(int i) {
        return i == 1 || i == 4 || i == 2;
    }

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        sURLMatcher = uriMatcher;
        uriMatcher.addURI("sms", null, 0);
        sURLMatcher.addURI("sms", "#", 1);
        sURLMatcher.addURI("sms", "inbox", 2);
        sURLMatcher.addURI("sms", "inbox/#", 3);
        sURLMatcher.addURI("sms", "sent", 4);
        sURLMatcher.addURI("sms", "sent/#", 5);
        sURLMatcher.addURI("sms", "draft", 6);
        sURLMatcher.addURI("sms", "draft/#", 7);
        sURLMatcher.addURI("sms", "outbox", 8);
        sURLMatcher.addURI("sms", "outbox/#", 9);
        sURLMatcher.addURI("sms", "undelivered", 27);
        sURLMatcher.addURI("sms", "failed", 24);
        sURLMatcher.addURI("sms", "failed/#", 25);
        sURLMatcher.addURI("sms", "queued", 26);
        sURLMatcher.addURI("sms", "conversations", 10);
        sURLMatcher.addURI("sms", "conversations/*", 11);
        sURLMatcher.addURI("sms", "raw", 15);
        sURLMatcher.addURI("sms", "raw/permanentDelete", 28);
        sURLMatcher.addURI("sms", "attachments", 16);
        sURLMatcher.addURI("sms", "attachments/#", 17);
        sURLMatcher.addURI("sms", "threadID", 18);
        sURLMatcher.addURI("sms", "threadID/*", 19);
        sURLMatcher.addURI("sms", "status/#", 20);
        sURLMatcher.addURI("sms", "sr_pending", 21);
        sURLMatcher.addURI("sms", "icc", 22);
        sURLMatcher.addURI("sms", "icc/#", 23);
        sURLMatcher.addURI("sms", "icc_subId/#", 29);
        sURLMatcher.addURI("sms", "icc_subId/#/#", 30);
        sURLMatcher.addURI("sms", "sim", 22);
        sURLMatcher.addURI("sms", "sim/#", 23);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        setAppOps(14, 15);
        this.mDeOpenHelper = MmsSmsDatabaseHelper.getInstanceForDe(getContext());
        this.mCeOpenHelper = MmsSmsDatabaseHelper.getInstanceForCe(getContext());
        TelephonyBackupAgent.DeferredSmsMmsRestoreService.startIfFilesExist(getContext());
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:67:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x02d8  */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.net.Uri, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v44, types: [android.database.Cursor] */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.database.Cursor query(android.net.Uri r17, java.lang.String[] r18, java.lang.String r19, java.lang.String[] r20, java.lang.String r21) {
        /*
            Method dump skipped, instruction units count: 836
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.SmsProvider.query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    private void purgeDeletedMessagesInRawTable(SQLiteDatabase sQLiteDatabase) {
        long jCurrentTimeMillis = System.currentTimeMillis() - 3600000;
        int iDelete = sQLiteDatabase.delete("raw", "deleted = 1 AND date < " + jCurrentTimeMillis, null);
        if (Log.isLoggable("SmsProvider", 2)) {
            Log.d("SmsProvider", "purgeDeletedMessagesInRawTable: num rows older than " + jCurrentTimeMillis + " purged: " + iDelete);
        }
    }

    private SQLiteOpenHelper getDBOpenHelper(int i) {
        if (i == 15 || i == 28) {
            return this.mDeOpenHelper;
        }
        return this.mCeOpenHelper;
    }

    private Object[] convertIccToSms(SmsMessage smsMessage, int i) {
        String recipientAddress;
        int statusOnIcc = smsMessage.getStatusOnIcc();
        int i2 = (statusOnIcc == 1 || statusOnIcc == 3) ? 1 : statusOnIcc != 5 ? statusOnIcc != 7 ? 0 : 4 : 2;
        Object[] objArr = new Object[13];
        objArr[0] = smsMessage.getServiceCenterAddress();
        if (i2 == 1) {
            recipientAddress = smsMessage.getDisplayOriginatingAddress();
        } else {
            recipientAddress = smsMessage.getRecipientAddress();
        }
        objArr[1] = recipientAddress;
        objArr[2] = String.valueOf(smsMessage.getMessageClass());
        objArr[3] = smsMessage.getDisplayMessageBody();
        objArr[4] = Long.valueOf(smsMessage.getTimestampMillis());
        objArr[5] = Integer.valueOf(statusOnIcc);
        objArr[6] = Integer.valueOf(smsMessage.getIndexOnIcc());
        objArr[7] = Boolean.valueOf(smsMessage.isStatusReportMessage());
        objArr[8] = "sms";
        objArr[9] = Integer.valueOf(i2);
        objArr[10] = 0;
        objArr[11] = -1;
        objArr[12] = Integer.valueOf(i);
        return objArr;
    }

    private Cursor getSingleMessageFromIcc(int i, int i2) {
        if (!SubscriptionManager.isValidSubscriptionId(i)) {
            throw new IllegalArgumentException("Invalid Subscription ID " + i);
        }
        SmsManager smsManagerForSubscriptionId = SmsManager.getSmsManagerForSubscriptionId(i);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            List messagesFromIcc = smsManagerForSubscriptionId.getMessagesFromIcc();
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            SmsMessage smsMessage = (SmsMessage) messagesFromIcc.get(i2);
            if (smsMessage == null) {
                throw new IllegalArgumentException("No message in index " + i2 + " for subId " + i);
            }
            MatrixCursor matrixCursor = new MatrixCursor(ICC_COLUMNS, 1);
            matrixCursor.addRow(convertIccToSms(smsMessage, 0));
            return matrixCursor;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            throw th;
        }
    }

    private Cursor getAllMessagesFromIcc(int i) {
        if (!SubscriptionManager.isValidSubscriptionId(i)) {
            throw new IllegalArgumentException("Invalid Subscription ID " + i);
        }
        SmsManager smsManagerForSubscriptionId = SmsManager.getSmsManagerForSubscriptionId(i);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            List messagesFromIcc = smsManagerForSubscriptionId.getMessagesFromIcc();
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            int size = messagesFromIcc.size();
            MatrixCursor matrixCursor = new MatrixCursor(ICC_COLUMNS, size);
            for (int i2 = 0; i2 < size; i2++) {
                SmsMessage smsMessage = (SmsMessage) messagesFromIcc.get(i2);
                if (smsMessage != null) {
                    matrixCursor.addRow(convertIccToSms(smsMessage, i2));
                }
            }
            return matrixCursor;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            throw th;
        }
    }

    private void constructQueryForBox(SQLiteQueryBuilder sQLiteQueryBuilder, int i, String str) {
        sQLiteQueryBuilder.setTables(str);
        if (i != 0) {
            sQLiteQueryBuilder.appendWhere("type=" + i);
        }
    }

    private void constructQueryForUndelivered(SQLiteQueryBuilder sQLiteQueryBuilder, String str) {
        sQLiteQueryBuilder.setTables(str);
        sQLiteQueryBuilder.appendWhere("(type=4 OR type=5 OR type=6)");
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int size = uri.getPathSegments().size();
        if (size != 0) {
            if (size != 1) {
                if (size != 2) {
                    return null;
                }
                return uri.getPathSegments().get(0).equals("conversations") ? "vnd.android.cursor.item/sms-chat" : "vnd.android.cursor.item/sms";
            }
            try {
                Integer.parseInt(uri.getPathSegments().get(0));
                return "vnd.android.cursor.item/sms";
            } catch (NumberFormatException unused) {
            }
        }
        return "vnd.android.cursor.dir/sms";
    }

    @Override // android.content.ContentProvider
    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        int callingUid = Binder.getCallingUid();
        String callingPackage = getCallingPackage();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            int i = 0;
            for (ContentValues contentValues : contentValuesArr) {
                if (insertInner(uri, contentValues, callingUid, callingPackage) != null) {
                    i++;
                }
            }
            notifyChange(sURLMatcher.match(uri) != 15, uri, callingPackage);
            return i;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        int callingUid = Binder.getCallingUid();
        String callingPackage = getCallingPackage();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            Uri uriInsertInner = insertInner(uri, contentValues, callingUid, callingPackage);
            int iMatch = sURLMatcher.match(uri);
            if (uriInsertInner != null || (iMatch != 22 && iMatch != 29)) {
                notifyChange(iMatch != 15, uriInsertInner, callingPackage);
            }
            return uriInsertInner;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:122:0x0239  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.net.Uri insertInner(android.net.Uri r29, android.content.ContentValues r30, int r31, java.lang.String r32) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 731
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.SmsProvider.insertInner(android.net.Uri, android.content.ContentValues, int, java.lang.String):android.net.Uri");
    }

    private boolean insertMessageToIcc(int i, String str, String str2, String str3, int i2, boolean z, long j) {
        if (!SubscriptionManager.isValidSubscriptionId(i)) {
            throw new IllegalArgumentException("Invalid Subscription ID " + i);
        }
        SmsManager smsManagerForSubscriptionId = SmsManager.getSmsManagerForSubscriptionId(i);
        int messageStatusForIcc = getMessageStatusForIcc(i2, z);
        SmsMessage.SubmitPdu smsPdu = SmsMessage.getSmsPdu(i, messageStatusForIcc, str, str2, str3, j);
        if (smsPdu == null) {
            throw new IllegalArgumentException("Failed to create SMS PDU");
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return smsManagerForSubscriptionId.copyMessageToIcc(smsPdu.encodedScAddress, smsPdu.encodedMessage, messageStatusForIcc);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:52:0x017d  */
    /* JADX WARN: Type inference failed for: r10v13, types: [boolean, int] */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int delete(android.net.Uri r10, java.lang.String r11, java.lang.String[] r12) {
        /*
            Method dump skipped, instruction units count: 389
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.SmsProvider.delete(android.net.Uri, java.lang.String, java.lang.String[]):int");
    }

    private boolean deleteMessageFromIcc(int i, int i2) {
        if (!SubscriptionManager.isValidSubscriptionId(i)) {
            throw new IllegalArgumentException("Invalid Subscription ID " + i);
        }
        SmsManager smsManagerForSubscriptionId = SmsManager.getSmsManagerForSubscriptionId(i);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return smsManagerForSubscriptionId.deleteMessageFromIcc(i2);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:16:0x007d  */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int update(android.net.Uri r12, android.content.ContentValues r13, java.lang.String r14, java.lang.String[] r15) {
        /*
            Method dump skipped, instruction units count: 332
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.SmsProvider.update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[]):int");
    }

    private void notifyChange(boolean z, Uri uri, String str) {
        Context context = getContext();
        ContentResolver contentResolver = context.getContentResolver();
        contentResolver.notifyChange(uri, null, true, -1);
        contentResolver.notifyChange(Telephony.MmsSms.CONTENT_URI, null, true, -1);
        contentResolver.notifyChange(Uri.parse("content://mms-sms/conversations/"), null, true, -1);
        if (z) {
            ProviderUtil.notifyIfNotDefaultSmsApp(uri, str, context);
        }
    }

    SQLiteDatabase getReadableDatabase(int i) {
        return getDBOpenHelper(i).getReadableDatabase();
    }

    SQLiteDatabase getWritableDatabase(int i) {
        return getDBOpenHelper(i).getWritableDatabase();
    }
}
