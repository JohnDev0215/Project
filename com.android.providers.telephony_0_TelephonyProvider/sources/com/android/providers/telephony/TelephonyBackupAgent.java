package com.android.providers.telephony;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.backup.BackupAgent;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.app.backup.FullBackupDataOutput;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.PowerManager;
import android.provider.Telephony;
import android.telephony.PhoneNumberUtils;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import android.util.SparseArray;
import com.android.internal.annotations.VisibleForTesting;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(23)
public class TelephonyBackupAgent extends BackupAgent {

    @VisibleForTesting
    static final Uri ALL_THREADS_URI;
    private static String ATTACHMENT_DATA_PATH = "/app_parts/";

    @VisibleForTesting
    static final String[] PROJECTION_ID;

    @VisibleForTesting
    static final Uri SINGLE_CANONICAL_ADDRESS_URI;
    private static int THREAD_ARCHIVED_IDX = 0;
    private static String[] THREAD_ARCHIVED_PROJECTION = null;

    @VisibleForTesting
    static final Uri THREAD_ID_CONTENT_URI;

    @VisibleForTesting
    static final String UNKNOWN_SENDER = "ʼUNKNOWN_SENDER!ʼ";
    private static volatile boolean sIsRestoring = false;

    @VisibleForTesting
    static final String sSmilTextOnly = "<smil><head><layout><root-layout/><region id=\"Text\" top=\"0\" left=\"0\" height=\"100%%\" width=\"100%%\"/></layout></head><body>%s</body></smil>";

    @VisibleForTesting
    static final String sSmilTextPart = "<par dur=\"5000ms\"><text src=\"%s\" region=\"Text\" /></par>";
    private long mBytesOverQuota;
    private ContentResolver mContentResolver;
    private long mUnknownSenderThreadId;

    @VisibleForTesting
    static final String[] SMS_PROJECTION = {"_id", "sub_id", "address", "body", "subject", "date", "date_sent", "status", "type", "thread_id", "read"};
    private static final String[] SMS_RECIPIENTS_PROJECTION = {"_id", "recipient_ids"};

    @VisibleForTesting
    static final String[] MMS_PROJECTION = {"_id", "sub_id", "sub", "sub_cs", "date", "date_sent", "m_type", "v", "msg_box", "ct_l", "thread_id", "tr_id", "read"};

    @VisibleForTesting
    static final String[] MMS_ADDR_PROJECTION = {"type", "address", "charset"};

    @VisibleForTesting
    static final String[] MMS_TEXT_PROJECTION = {"text", "chset"};
    private static ContentValues sDefaultValuesSms = new ContentValues(5);
    private static ContentValues sDefaultValuesMms = new ContentValues(6);
    private static final ContentValues sDefaultValuesAddr = new ContentValues(2);
    private static final ContentValues sDefaultValuesAttachments = new ContentValues(2);

    @VisibleForTesting
    int mMaxMsgPerFile = 1000;
    private SparseArray<String> mSubId2phone = new SparseArray<>();
    private Map<String, Integer> mPhone2subId = new ArrayMap();
    private Map<Long, Boolean> mThreadArchived = new HashMap();

    @VisibleForTesting
    Map<Long, List<String>> mCacheRecipientsByThread = null;

    @VisibleForTesting
    Map<Set<String>, Long> mCacheGetOrCreateThreadId = null;
    private SmsProviderQuery mSmsProviderQuery = new SmsProviderQuery() { // from class: com.android.providers.telephony.TelephonyBackupAgent.1
        @Override // com.android.providers.telephony.TelephonyBackupAgent.SmsProviderQuery
        public boolean doesSmsExist(ContentValues contentValues) {
            boolean z = false;
            Cursor cursorQuery = TelephonyBackupAgent.this.mContentResolver.query(Telephony.Sms.CONTENT_URI, TelephonyBackupAgent.PROJECTION_ID, String.format(Locale.US, "%s=%d AND %s=?", "date", contentValues.getAsLong("date"), "body"), new String[]{contentValues.getAsString("body")}, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.getCount() > 0) {
                        z = true;
                    }
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
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return z;
        }
    };

    public interface SmsProviderQuery {
        boolean doesSmsExist(ContentValues contentValues);
    }

    @Override // android.app.backup.BackupAgent
    public void onBackup(ParcelFileDescriptor parcelFileDescriptor, BackupDataOutput backupDataOutput, ParcelFileDescriptor parcelFileDescriptor2) throws IOException {
    }

    @Override // android.app.backup.BackupAgent
    public void onRestore(BackupDataInput backupDataInput, int i, ParcelFileDescriptor parcelFileDescriptor) throws IOException {
    }

    static {
        sDefaultValuesSms.put("read", (Integer) 1);
        sDefaultValuesSms.put("seen", (Integer) 1);
        sDefaultValuesSms.put("address", UNKNOWN_SENDER);
        sDefaultValuesSms.put("sub_id", (Integer) (-1));
        sDefaultValuesMms.put("read", (Integer) 1);
        sDefaultValuesMms.put("seen", (Integer) 1);
        sDefaultValuesMms.put("sub_id", (Integer) (-1));
        sDefaultValuesMms.put("msg_box", (Integer) 0);
        sDefaultValuesMms.put("text_only", (Integer) 1);
        sDefaultValuesAddr.put("type", (Integer) 0);
        sDefaultValuesAddr.put("charset", (Integer) 106);
        PROJECTION_ID = new String[]{"_id"};
        THREAD_ARCHIVED_PROJECTION = new String[]{"archived"};
        THREAD_ARCHIVED_IDX = 0;
        THREAD_ID_CONTENT_URI = Uri.parse("content://mms-sms/threadID");
        ALL_THREADS_URI = Telephony.Threads.CONTENT_URI.buildUpon().appendQueryParameter("simple", "true").build();
        SINGLE_CANONICAL_ADDRESS_URI = Uri.parse("content://mms-sms/canonical-address");
    }

    @Override // android.app.backup.BackupAgent
    public void onCreate() {
        List<SubscriptionInfo> completeActiveSubscriptionInfoList;
        super.onCreate();
        SubscriptionManager subscriptionManagerFrom = SubscriptionManager.from(this);
        if (subscriptionManagerFrom != null && (completeActiveSubscriptionInfoList = subscriptionManagerFrom.getCompleteActiveSubscriptionInfoList()) != null) {
            for (SubscriptionInfo subscriptionInfo : completeActiveSubscriptionInfoList) {
                String normalizedNumber = getNormalizedNumber(subscriptionInfo);
                this.mSubId2phone.append(subscriptionInfo.getSubscriptionId(), normalizedNumber);
                this.mPhone2subId.put(normalizedNumber, Integer.valueOf(subscriptionInfo.getSubscriptionId()));
            }
        }
        this.mContentResolver = getContentResolver();
        initUnknownSender();
    }

    @VisibleForTesting
    void setContentResolver(ContentResolver contentResolver) {
        this.mContentResolver = contentResolver;
    }

    @VisibleForTesting
    void setSubId(SparseArray<String> sparseArray, Map<String, Integer> map) {
        this.mSubId2phone = sparseArray;
        this.mPhone2subId = map;
    }

    @VisibleForTesting
    void initUnknownSender() {
        long orCreateThreadId = getOrCreateThreadId(null);
        this.mUnknownSenderThreadId = orCreateThreadId;
        sDefaultValuesSms.put("thread_id", Long.valueOf(orCreateThreadId));
        sDefaultValuesMms.put("thread_id", Long.valueOf(this.mUnknownSenderThreadId));
    }

    /* JADX WARN: Finally extract failed */
    @Override // android.app.backup.BackupAgent
    public void onFullBackup(FullBackupDataOutput fullBackupDataOutput) throws IOException {
        int i;
        SharedPreferences sharedPreferences = getSharedPreferences("backup_shared_prefs", 0);
        if (sharedPreferences.getLong("reset_quota_time", Long.MAX_VALUE) < System.currentTimeMillis()) {
            clearSharedPreferences();
        }
        long j = sharedPreferences.getLong("backup_data_bytes", 0L) - sharedPreferences.getLong("backup_quota_bytes", Long.MAX_VALUE);
        this.mBytesOverQuota = j;
        if (j > 0) {
            this.mBytesOverQuota = (long) (j * 1.1d);
        }
        Cursor cursorQuery = this.mContentResolver.query(Telephony.Sms.CONTENT_URI, SMS_PROJECTION, null, null, "date ASC");
        try {
            Cursor cursorQuery2 = this.mContentResolver.query(Telephony.Mms.CONTENT_URI, MMS_PROJECTION, null, null, "date ASC");
            if (cursorQuery != null) {
                try {
                    cursorQuery.moveToFirst();
                } catch (Throwable th) {
                    if (cursorQuery2 != null) {
                        try {
                            cursorQuery2.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
            if (cursorQuery2 != null) {
                cursorQuery2.moveToFirst();
            }
            int i2 = 0;
            while (cursorQuery != null && !cursorQuery.isAfterLast() && cursorQuery2 != null && !cursorQuery2.isAfterLast()) {
                if (TimeUnit.MILLISECONDS.toSeconds(getMessageDate(cursorQuery)) < getMessageDate(cursorQuery2)) {
                    i = i2 + 1;
                    backupAll(fullBackupDataOutput, cursorQuery, String.format(Locale.US, "%06d_sms_backup", Integer.valueOf(i2)));
                } else {
                    i = i2 + 1;
                    backupAll(fullBackupDataOutput, cursorQuery2, String.format(Locale.US, "%06d_mms_backup", Integer.valueOf(i2)));
                }
                i2 = i;
            }
            while (cursorQuery != null && !cursorQuery.isAfterLast()) {
                backupAll(fullBackupDataOutput, cursorQuery, String.format(Locale.US, "%06d_sms_backup", Integer.valueOf(i2)));
                i2++;
            }
            while (cursorQuery2 != null) {
                if (cursorQuery2.isAfterLast()) {
                    break;
                }
                int i3 = i2 + 1;
                backupAll(fullBackupDataOutput, cursorQuery2, String.format(Locale.US, "%06d_mms_backup", Integer.valueOf(i2)));
                i2 = i3;
            }
            if (cursorQuery2 != null) {
                cursorQuery2.close();
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            this.mThreadArchived = new HashMap();
        } catch (Throwable th3) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
            }
            throw th3;
        }
    }

    @VisibleForTesting
    void clearSharedPreferences() {
        getSharedPreferences("backup_shared_prefs", 0).edit().remove("backup_data_bytes").remove("backup_quota_bytes").remove("reset_quota_time").apply();
    }

    private static long getMessageDate(Cursor cursor) {
        return cursor.getLong(cursor.getColumnIndex("date"));
    }

    @Override // android.app.backup.BackupAgent
    public void onQuotaExceeded(long j, long j2) {
        SharedPreferences sharedPreferences = getSharedPreferences("backup_shared_prefs", 0);
        if (sharedPreferences.contains("backup_data_bytes") && sharedPreferences.contains("backup_quota_bytes")) {
            j = (long) (j + ((sharedPreferences.getLong("backup_data_bytes", 0L) - sharedPreferences.getLong("backup_quota_bytes", 0L)) * 1.1d));
        }
        sharedPreferences.edit().putLong("backup_data_bytes", j).putLong("backup_quota_bytes", j2).putLong("reset_quota_time", System.currentTimeMillis() + 2592000000L).apply();
    }

    private void backupAll(FullBackupDataOutput fullBackupDataOutput, Cursor cursor, String str) throws IOException {
        BackupChunkInformation backupChunkInformationPutMmsMessagesToJson;
        if (cursor == null || cursor.isAfterLast()) {
            return;
        }
        JsonWriter jsonWriter = getJsonWriter(str);
        try {
            if (str.endsWith("_sms_backup")) {
                backupChunkInformationPutMmsMessagesToJson = putSmsMessagesToJson(cursor, jsonWriter);
            } else {
                backupChunkInformationPutMmsMessagesToJson = putMmsMessagesToJson(cursor, jsonWriter);
            }
            if (jsonWriter != null) {
                jsonWriter.close();
            }
            backupFile(backupChunkInformationPutMmsMessagesToJson, str, fullBackupDataOutput);
        } catch (Throwable th) {
            if (jsonWriter != null) {
                try {
                    jsonWriter.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @VisibleForTesting
    BackupChunkInformation putMmsMessagesToJson(Cursor cursor, JsonWriter jsonWriter) throws IOException {
        BackupChunkInformation backupChunkInformation = new BackupChunkInformation();
        jsonWriter.beginArray();
        while (backupChunkInformation.count < this.mMaxMsgPerFile && !cursor.isAfterLast()) {
            writeMmsToWriter(jsonWriter, cursor, backupChunkInformation);
            cursor.moveToNext();
        }
        jsonWriter.endArray();
        return backupChunkInformation;
    }

    @VisibleForTesting
    BackupChunkInformation putSmsMessagesToJson(Cursor cursor, JsonWriter jsonWriter) throws IOException {
        BackupChunkInformation backupChunkInformation = new BackupChunkInformation();
        jsonWriter.beginArray();
        while (backupChunkInformation.count < this.mMaxMsgPerFile && !cursor.isAfterLast()) {
            writeSmsToWriter(jsonWriter, cursor, backupChunkInformation);
            BackupChunkInformation.access$104(backupChunkInformation);
            cursor.moveToNext();
        }
        jsonWriter.endArray();
        return backupChunkInformation;
    }

    private void backupFile(BackupChunkInformation backupChunkInformation, String str, FullBackupDataOutput fullBackupDataOutput) throws IOException {
        File file = new File(getFilesDir().getPath() + "/" + str);
        file.setLastModified(backupChunkInformation.timestamp);
        try {
            if (backupChunkInformation.count > 0) {
                if (this.mBytesOverQuota > 0) {
                    this.mBytesOverQuota -= file.length();
                    return;
                }
                super.fullBackupFile(file, fullBackupDataOutput);
            }
        } finally {
            file.delete();
        }
    }

    public static class DeferredSmsMmsRestoreService extends IntentService {
        private final Comparator<File> mFileComparator;
        private TelephonyBackupAgent mTelephonyBackupAgent;
        private PowerManager.WakeLock mWakeLock;

        public DeferredSmsMmsRestoreService() {
            super("DeferredSmsMmsRestoreService");
            this.mFileComparator = new Comparator<File>(this) { // from class: com.android.providers.telephony.TelephonyBackupAgent.DeferredSmsMmsRestoreService.1
                @Override // java.util.Comparator
                public int compare(File file, File file2) {
                    return file2.getName().compareTo(file.getName());
                }
            };
            setIntentRedelivery(true);
        }

        @Override // android.app.IntentService
        protected void onHandleIntent(Intent intent) {
            boolean z;
            Exception e;
            try {
                this.mWakeLock.acquire();
                boolean unused = TelephonyBackupAgent.sIsRestoring = true;
                File[] filesToRestore = getFilesToRestore(this);
                if (filesToRestore != null && filesToRestore.length != 0) {
                    Arrays.sort(filesToRestore, this.mFileComparator);
                    int length = filesToRestore.length;
                    boolean z2 = false;
                    for (int i = 0; i < length; i++) {
                        File file = filesToRestore[i];
                        String name = file.getName();
                        Log.d("DeferredSmsMmsRestoreService", "onHandleIntent restoring file " + name);
                        try {
                            try {
                                FileInputStream fileInputStream = new FileInputStream(file);
                                try {
                                    this.mTelephonyBackupAgent.doRestoreFile(name, fileInputStream.getFD());
                                    try {
                                        fileInputStream.close();
                                        z2 = true;
                                    } catch (Exception e2) {
                                        e = e2;
                                        z = true;
                                        Log.e("DeferredSmsMmsRestoreService", "onHandleIntent", e);
                                        file.delete();
                                        z2 = z;
                                    }
                                } catch (Throwable th) {
                                    try {
                                        fileInputStream.close();
                                    } catch (Throwable th2) {
                                        th.addSuppressed(th2);
                                    }
                                    throw th;
                                }
                            } finally {
                                file.delete();
                            }
                        } catch (Exception e3) {
                            z = z2;
                            e = e3;
                        }
                    }
                    if (z2) {
                        Log.d("DeferredSmsMmsRestoreService", "onHandleIntent done - notifying default sms app");
                        ProviderUtil.notifyIfNotDefaultSmsApp(null, null, this);
                    }
                }
            } finally {
                boolean unused2 = TelephonyBackupAgent.sIsRestoring = false;
                this.mWakeLock.release();
            }
        }

        @Override // android.app.IntentService, android.app.Service
        public void onCreate() {
            super.onCreate();
            TelephonyBackupAgent telephonyBackupAgent = new TelephonyBackupAgent();
            this.mTelephonyBackupAgent = telephonyBackupAgent;
            telephonyBackupAgent.attach(this);
            this.mTelephonyBackupAgent.onCreate();
            this.mWakeLock = ((PowerManager) getSystemService("power")).newWakeLock(1, "DeferredSmsMmsRestoreService");
        }

        @Override // android.app.IntentService, android.app.Service
        public void onDestroy() {
            TelephonyBackupAgent telephonyBackupAgent = this.mTelephonyBackupAgent;
            if (telephonyBackupAgent != null) {
                telephonyBackupAgent.onDestroy();
                this.mTelephonyBackupAgent = null;
            }
            super.onDestroy();
        }

        static void startIfFilesExist(Context context) {
            File[] filesToRestore = getFilesToRestore(context);
            if (filesToRestore == null || filesToRestore.length == 0) {
                return;
            }
            context.startService(new Intent(context, (Class<?>) DeferredSmsMmsRestoreService.class));
        }

        private static File[] getFilesToRestore(Context context) {
            return context.getFilesDir().listFiles(new FileFilter() { // from class: com.android.providers.telephony.TelephonyBackupAgent.DeferredSmsMmsRestoreService.2
                @Override // java.io.FileFilter
                public boolean accept(File file) {
                    return file.getName().endsWith("_sms_backup") || file.getName().endsWith("_mms_backup");
                }
            });
        }
    }

    @Override // android.app.backup.BackupAgent
    public void onRestoreFinished() {
        super.onRestoreFinished();
        DeferredSmsMmsRestoreService.startIfFilesExist(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doRestoreFile(String str, FileDescriptor fileDescriptor) throws IOException {
        Log.d("TelephonyBackupAgent", "Restoring file " + str);
        JsonReader jsonReader = getJsonReader(fileDescriptor);
        try {
            if (str.endsWith("_sms_backup")) {
                Log.d("TelephonyBackupAgent", "Restoring SMS");
                putSmsMessagesToProvider(jsonReader);
            } else if (str.endsWith("_mms_backup")) {
                Log.d("TelephonyBackupAgent", "Restoring text MMS");
                putMmsMessagesToProvider(jsonReader);
            } else {
                Log.e("TelephonyBackupAgent", "Unknown file to restore:" + str);
            }
            if (jsonReader != null) {
                jsonReader.close();
            }
        } catch (Throwable th) {
            if (jsonReader != null) {
                try {
                    jsonReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @VisibleForTesting
    void putSmsMessagesToProvider(JsonReader jsonReader) throws IOException {
        int i;
        Exception e;
        jsonReader.beginArray();
        int i2 = this.mMaxMsgPerFile;
        ContentValues[] contentValuesArr = new ContentValues[i2];
        int i3 = 0;
        while (jsonReader.hasNext()) {
            ContentValues smsValuesFromReader = readSmsValuesFromReader(jsonReader);
            try {
            } catch (Exception e2) {
                i = i3;
                e = e2;
            }
            if (!this.mSmsProviderQuery.doesSmsExist(smsValuesFromReader)) {
                i = i3 + 1;
                try {
                    contentValuesArr[i3 % i2] = smsValuesFromReader;
                    if (i % i2 == 0) {
                        this.mContentResolver.bulkInsert(Telephony.Sms.CONTENT_URI, contentValuesArr);
                    }
                } catch (Exception e3) {
                    e = e3;
                    Log.e("TelephonyBackupAgent", "putSmsMessagesToProvider", e);
                }
                i3 = i;
            }
        }
        int i4 = i3 % i2;
        if (i4 > 0) {
            this.mContentResolver.bulkInsert(Telephony.Sms.CONTENT_URI, (ContentValues[]) Arrays.copyOf(contentValuesArr, i4));
        }
        jsonReader.endArray();
    }

    @VisibleForTesting
    void putMmsMessagesToProvider(JsonReader jsonReader) throws IOException {
        jsonReader.beginArray();
        int i = 0;
        while (jsonReader.hasNext()) {
            Mms mmsFromReader = readMmsFromReader(jsonReader);
            try {
                if (doesMmsExist(mmsFromReader)) {
                    Log.w("TelephonyBackupAgent", "Mms: Found duplicate MMS");
                } else {
                    i++;
                    addMmsMessage(mmsFromReader);
                }
            } catch (Exception e) {
                Log.e("TelephonyBackupAgent", "putMmsMessagesToProvider", e);
            }
        }
        Log.d("TelephonyBackupAgent", "putMmsMessagesToProvider handled " + i + " new messages.");
    }

    @VisibleForTesting
    public void setSmsProviderQuery(SmsProviderQuery smsProviderQuery) {
        this.mSmsProviderQuery = smsProviderQuery;
    }

    private boolean doesMmsExist(Mms mms) {
        Cursor cursorQuery = this.mContentResolver.query(Telephony.Mms.CONTENT_URI, PROJECTION_ID, String.format(Locale.US, "%s = %d", "date", mms.values.getAsLong("date")), null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    do {
                        MmsBody mmsBody = getMmsBody(cursorQuery.getInt(0));
                        if (mmsBody != null && mmsBody.equals(mms.body)) {
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                            return true;
                        }
                    } while (cursorQuery.moveToNext());
                }
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
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return false;
    }

    private static String getNormalizedNumber(SubscriptionInfo subscriptionInfo) {
        if (subscriptionInfo == null) {
            return null;
        }
        if (!TextUtils.isEmpty(subscriptionInfo.getCountryIso())) {
            return PhoneNumberUtils.formatNumberToE164(subscriptionInfo.getNumber(), subscriptionInfo.getCountryIso().toUpperCase(Locale.US));
        }
        return subscriptionInfo.getNumber();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void writeSmsToWriter(JsonWriter jsonWriter, Cursor cursor, BackupChunkInformation backupChunkInformation) throws IOException {
        jsonWriter.beginObject();
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            String columnName = cursor.getColumnName(i);
            String string = cursor.getString(i);
            if (string != null) {
                byte b = -1;
                switch (columnName.hashCode()) {
                    case -1562235024:
                        if (columnName.equals("thread_id")) {
                            b = 1;
                        }
                        break;
                    case -891548806:
                        if (columnName.equals("sub_id")) {
                            b = 0;
                        }
                        break;
                    case -248892023:
                        if (columnName.equals("date_sent")) {
                            b = 4;
                        }
                        break;
                    case 94650:
                        if (columnName.equals("_id")) {
                            b = 2;
                        }
                        break;
                    case 3076014:
                        if (columnName.equals("date")) {
                            b = 3;
                        }
                        break;
                }
                if (b == 0) {
                    String str = this.mSubId2phone.get(cursor.getInt(i));
                    if (str != null) {
                        jsonWriter.name("self_phone").value(str);
                    }
                } else if (b == 1) {
                    handleThreadId(jsonWriter, cursor.getLong(i));
                } else if (b != 2) {
                    if (b != 3 && b != 4) {
                        jsonWriter.name(columnName).value(string);
                    } else {
                        backupChunkInformation.timestamp = findNewestValue(backupChunkInformation.timestamp, string);
                        jsonWriter.name(columnName).value(string);
                    }
                }
            }
        }
        jsonWriter.endObject();
    }

    private long findNewestValue(long j, String str) {
        if (str == null) {
            return j;
        }
        try {
            return Math.max(j, Long.valueOf(str).longValue());
        } catch (NumberFormatException unused) {
            Log.d("TelephonyBackupAgent", "Unable to parse value " + str);
            return j;
        }
    }

    private void handleThreadId(JsonWriter jsonWriter, long j) throws IOException {
        List<String> recipientsByThread = getRecipientsByThread(j);
        if (recipientsByThread == null || recipientsByThread.isEmpty()) {
            return;
        }
        writeRecipientsToWriter(jsonWriter.name("recipients"), recipientsByThread);
        if (this.mThreadArchived.containsKey(Long.valueOf(j))) {
            return;
        }
        boolean zIsThreadArchived = isThreadArchived(j);
        if (zIsThreadArchived) {
            jsonWriter.name("archived").value(true);
        }
        this.mThreadArchived.put(Long.valueOf(j), Boolean.valueOf(zIsThreadArchived));
    }

    private boolean isThreadArchived(long j) {
        Uri.Builder builderBuildUpon = Telephony.Threads.CONTENT_URI.buildUpon();
        builderBuildUpon.appendPath(String.valueOf(j)).appendPath("recipients");
        Cursor cursorQuery = getContentResolver().query(builderBuildUpon.build(), THREAD_ARCHIVED_PROJECTION, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    boolean z = cursorQuery.getInt(THREAD_ARCHIVED_IDX) == 1;
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return z;
                }
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
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return false;
    }

    private static void writeRecipientsToWriter(JsonWriter jsonWriter, List<String> list) throws IOException {
        jsonWriter.beginArray();
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                jsonWriter.value(it.next());
            }
        }
        jsonWriter.endArray();
    }

    private ContentValues readSmsValuesFromReader(JsonReader jsonReader) throws IOException {
        String strNextName;
        ContentValues contentValues = new ContentValues(sDefaultValuesSms.size() + 6);
        contentValues.putAll(sDefaultValuesSms);
        jsonReader.beginObject();
        long orCreateThreadId = -1;
        boolean zNextBoolean = false;
        while (jsonReader.hasNext()) {
            strNextName = jsonReader.nextName();
            switch (strNextName) {
                case "body":
                case "date":
                case "date_sent":
                case "status":
                case "type":
                case "subject":
                case "address":
                case "read":
                    contentValues.put(strNextName, jsonReader.nextString());
                    break;
                case "recipients":
                    orCreateThreadId = getOrCreateThreadId(getRecipients(jsonReader));
                    contentValues.put("thread_id", Long.valueOf(orCreateThreadId));
                    break;
                case "archived":
                    zNextBoolean = jsonReader.nextBoolean();
                    break;
                case "self_phone":
                    String strNextString = jsonReader.nextString();
                    if (!this.mPhone2subId.containsKey(strNextString)) {
                        break;
                    } else {
                        contentValues.put("sub_id", this.mPhone2subId.get(strNextString));
                        break;
                    }
                    break;
                default:
                    Log.w("TelephonyBackupAgent", "readSmsValuesFromReader encountered unknown name.");
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        archiveThread(orCreateThreadId, zNextBoolean);
        return contentValues;
    }

    private static Set<String> getRecipients(JsonReader jsonReader) throws IOException {
        ArraySet arraySet = new ArraySet();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arraySet.add(jsonReader.nextString());
        }
        jsonReader.endArray();
        return arraySet;
    }

    private void writeMmsToWriter(JsonWriter jsonWriter, Cursor cursor, BackupChunkInformation backupChunkInformation) throws IOException {
        int i = cursor.getInt(0);
        MmsBody mmsBody = getMmsBody(i);
        if (mmsBody == null || mmsBody.text == null) {
            return;
        }
        jsonWriter.beginObject();
        boolean z = true;
        for (int i2 = 0; i2 < cursor.getColumnCount(); i2++) {
            String columnName = cursor.getColumnName(i2);
            String string = cursor.getString(i2);
            if (string != null) {
                switch (columnName) {
                    case "sub_id":
                        String str = this.mSubId2phone.get(cursor.getInt(i2));
                        if (str != null) {
                            jsonWriter.name("self_phone").value(str);
                            break;
                        } else {
                            continue;
                            break;
                        }
                        break;
                    case "thread_id":
                        handleThreadId(jsonWriter, cursor.getLong(i2));
                        continue;
                        break;
                    case "date":
                    case "date_sent":
                        backupChunkInformation.timestamp = findNewestValue(backupChunkInformation.timestamp, string);
                        jsonWriter.name(columnName).value(string);
                        continue;
                        break;
                    case "sub":
                        z = false;
                        break;
                }
                jsonWriter.name(columnName).value(string);
            }
        }
        writeMmsAddresses(jsonWriter.name("mms_addresses"), i);
        jsonWriter.name("mms_body").value(mmsBody.text);
        jsonWriter.name("mms_charset").value(mmsBody.charSet);
        if (!z) {
            writeStringToWriter(jsonWriter, cursor, "sub_cs");
        }
        jsonWriter.endObject();
        BackupChunkInformation.access$108(backupChunkInformation);
    }

    private Mms readMmsFromReader(JsonReader jsonReader) throws IOException {
        String strNextName;
        String strNextString = null;
        Mms mms = new Mms();
        ContentValues contentValues = new ContentValues(sDefaultValuesMms.size() + 5);
        mms.values = contentValues;
        contentValues.putAll(sDefaultValuesMms);
        jsonReader.beginObject();
        int i = 0;
        long orCreateThreadId = -1;
        int iNextInt = 106;
        boolean zNextBoolean = false;
        while (true) {
            if (jsonReader.hasNext()) {
                strNextName = jsonReader.nextName();
                switch (strNextName) {
                    case "self_phone":
                        String strNextString2 = jsonReader.nextString();
                        if (!this.mPhone2subId.containsKey(strNextString2)) {
                            break;
                        } else {
                            mms.values.put("sub_id", this.mPhone2subId.get(strNextString2));
                            break;
                        }
                        break;
                    case "mms_addresses":
                        getMmsAddressesFromReader(jsonReader, mms);
                        break;
                    case "attachments":
                        getMmsAttachmentsFromReader(jsonReader, mms);
                        break;
                    case "smil":
                        mms.smil = jsonReader.nextString();
                        break;
                    case "mms_body":
                        strNextString = jsonReader.nextString();
                        break;
                    case "mms_charset":
                        iNextInt = jsonReader.nextInt();
                        break;
                    case "recipients":
                        orCreateThreadId = getOrCreateThreadId(getRecipients(jsonReader));
                        mms.values.put("thread_id", Long.valueOf(orCreateThreadId));
                        break;
                    case "archived":
                        zNextBoolean = jsonReader.nextBoolean();
                        break;
                    case "sub":
                    case "sub_cs":
                    case "date":
                    case "date_sent":
                    case "m_type":
                    case "v":
                    case "msg_box":
                    case "ct_l":
                    case "tr_id":
                    case "read":
                        mms.values.put(strNextName, jsonReader.nextString());
                        break;
                    default:
                        Log.d("TelephonyBackupAgent", "Unknown JSON element name:" + strNextName);
                        jsonReader.skipValue();
                        break;
                }
            } else {
                jsonReader.endObject();
                if (strNextString != null) {
                    mms.body = new MmsBody(strNextString, iNextInt);
                }
                ContentValues contentValues2 = mms.values;
                List<ContentValues> list = mms.attachments;
                if ((list == null || list.size() == 0) && strNextString != null) {
                    i = 1;
                }
                contentValues2.put("text_only", Integer.valueOf(i));
                if (mms.values.get("sub") != null && mms.values.get("sub_cs") == null) {
                    mms.values.put("sub_cs", (Integer) 106);
                }
                archiveThread(orCreateThreadId, zNextBoolean);
                return mms;
            }
        }
    }

    private void archiveThread(long j, boolean z) {
        if (j < 0 || !z) {
            return;
        }
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("archived", (Integer) 1);
        if (this.mContentResolver.update(Telephony.Threads.CONTENT_URI, contentValues, "_id=?", new String[]{Long.toString(j)}) != 1) {
            Log.e("TelephonyBackupAgent", "archiveThread: failed to update database");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.android.providers.telephony.TelephonyBackupAgent.MmsBody getMmsBody(int r7) {
        /*
            r6 = this;
            android.net.Uri r0 = android.provider.Telephony.Mms.CONTENT_URI
            android.net.Uri$Builder r0 = r0.buildUpon()
            java.lang.String r7 = java.lang.String.valueOf(r7)
            android.net.Uri$Builder r7 = r0.appendPath(r7)
            java.lang.String r0 = "part"
            android.net.Uri$Builder r7 = r7.appendPath(r0)
            android.net.Uri r1 = r7.build()
            android.content.ContentResolver r0 = r6.mContentResolver
            java.lang.String[] r2 = com.android.providers.telephony.TelephonyBackupAgent.MMS_TEXT_PROJECTION
            java.lang.String r6 = "text/plain"
            java.lang.String[] r4 = new java.lang.String[]{r6}
            java.lang.String r3 = "ct=?"
            java.lang.String r5 = "_id ASC"
            android.database.Cursor r6 = r0.query(r1, r2, r3, r4, r5)
            r7 = 0
            r0 = 0
            if (r6 == 0) goto L5d
            boolean r1 = r6.moveToFirst()     // Catch: java.lang.Throwable -> L51
            if (r1 == 0) goto L5d
            r2 = r7
            r1 = r0
        L36:
            java.lang.String r3 = r6.getString(r7)     // Catch: java.lang.Throwable -> L51
            if (r3 == 0) goto L49
            if (r1 != 0) goto L40
            r1 = r3
            goto L44
        L40:
            java.lang.String r1 = r1.concat(r3)     // Catch: java.lang.Throwable -> L51
        L44:
            r2 = 1
            int r2 = r6.getInt(r2)     // Catch: java.lang.Throwable -> L51
        L49:
            boolean r3 = r6.moveToNext()     // Catch: java.lang.Throwable -> L51
            if (r3 != 0) goto L36
            r7 = r2
            goto L5e
        L51:
            r7 = move-exception
            if (r6 == 0) goto L5c
            r6.close()     // Catch: java.lang.Throwable -> L58
            goto L5c
        L58:
            r6 = move-exception
            r7.addSuppressed(r6)
        L5c:
            throw r7
        L5d:
            r1 = r0
        L5e:
            if (r6 == 0) goto L63
            r6.close()
        L63:
            if (r1 != 0) goto L66
            goto L6b
        L66:
            com.android.providers.telephony.TelephonyBackupAgent$MmsBody r0 = new com.android.providers.telephony.TelephonyBackupAgent$MmsBody
            r0.<init>(r1, r7)
        L6b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.TelephonyBackupAgent.getMmsBody(int):com.android.providers.telephony.TelephonyBackupAgent$MmsBody");
    }

    private void writeMmsAddresses(JsonWriter jsonWriter, int i) throws IOException {
        Uri.Builder builderBuildUpon = Telephony.Mms.CONTENT_URI.buildUpon();
        builderBuildUpon.appendPath(String.valueOf(i)).appendPath("addr");
        Uri uriBuild = builderBuildUpon.build();
        jsonWriter.beginArray();
        Cursor cursorQuery = this.mContentResolver.query(uriBuild, MMS_ADDR_PROJECTION, null, null, "_id ASC");
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    do {
                        if (cursorQuery.getString(cursorQuery.getColumnIndex("address")) != null) {
                            jsonWriter.beginObject();
                            writeIntToWriter(jsonWriter, cursorQuery, "type");
                            writeStringToWriter(jsonWriter, cursorQuery, "address");
                            writeIntToWriter(jsonWriter, cursorQuery, "charset");
                            jsonWriter.endObject();
                        }
                    } while (cursorQuery.moveToNext());
                }
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
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        jsonWriter.endArray();
    }

    private static void getMmsAddressesFromReader(JsonReader jsonReader, Mms mms) throws IOException {
        mms.addresses = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            jsonReader.beginObject();
            ContentValues contentValues = new ContentValues(sDefaultValuesAddr);
            while (jsonReader.hasNext()) {
                String strNextName = jsonReader.nextName();
                byte b = -1;
                int iHashCode = strNextName.hashCode();
                if (iHashCode != -1147692044) {
                    if (iHashCode != 3575610) {
                        if (iHashCode == 739074380 && strNextName.equals("charset")) {
                            b = 1;
                        }
                    } else if (strNextName.equals("type")) {
                        b = 0;
                    }
                } else if (strNextName.equals("address")) {
                    b = 2;
                }
                if (b == 0 || b == 1) {
                    contentValues.put(strNextName, Integer.valueOf(jsonReader.nextInt()));
                } else if (b == 2) {
                    contentValues.put(strNextName, jsonReader.nextString());
                } else {
                    Log.d("TelephonyBackupAgent", "Unknown JSON Element name:" + strNextName);
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
            if (contentValues.containsKey("address")) {
                mms.addresses.add(contentValues);
            }
        }
        jsonReader.endArray();
    }

    private static void getMmsAttachmentsFromReader(JsonReader jsonReader, Mms mms) throws IOException {
        mms.attachments = new ArrayList();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            jsonReader.beginObject();
            ContentValues contentValues = new ContentValues(sDefaultValuesAttachments);
            while (jsonReader.hasNext()) {
                String strNextName = jsonReader.nextName();
                byte b = -1;
                int iHashCode = strNextName.hashCode();
                if (iHashCode != -734768633) {
                    if (iHashCode == -196041627 && strNextName.equals("mime_type")) {
                        b = 0;
                    }
                } else if (strNextName.equals("filename")) {
                    b = 1;
                }
                if (b == 0 || b == 1) {
                    contentValues.put(strNextName, jsonReader.nextString());
                } else {
                    Log.d("TelephonyBackupAgent", "getMmsAttachmentsFromReader Unknown name:" + strNextName);
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
            if (contentValues.containsKey("filename")) {
                mms.attachments.add(contentValues);
            } else {
                Log.d("TelephonyBackupAgent", "Attachment json with no filenames");
            }
        }
        jsonReader.endArray();
    }

    private void addMmsMessage(Mms mms) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Uri uriBuild = Telephony.Mms.CONTENT_URI.buildUpon().appendPath(String.valueOf(jCurrentTimeMillis)).appendPath("part").build();
        String str = String.format(Locale.US, "text.%06d.txt", 0);
        String str2 = TextUtils.isEmpty(mms.smil) ? String.format(sSmilTextOnly, String.format(sSmilTextPart, str)) : mms.smil;
        ContentValues contentValues = new ContentValues(7);
        contentValues.put("mid", Long.valueOf(jCurrentTimeMillis));
        contentValues.put("seq", (Integer) (-1));
        contentValues.put("ct", "application/smil");
        contentValues.put("name", "smil.xml");
        contentValues.put("cid", "<smil>");
        contentValues.put("cl", "smil.xml");
        contentValues.put("text", str2);
        if (this.mContentResolver.insert(uriBuild, contentValues) == null) {
            Log.e("TelephonyBackupAgent", "Could not insert SMIL part");
            return;
        }
        ContentValues contentValues2 = new ContentValues(8);
        contentValues2.put("mid", Long.valueOf(jCurrentTimeMillis));
        contentValues2.put("seq", (Integer) 0);
        contentValues2.put("ct", "text/plain");
        contentValues2.put("name", str);
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(str);
        String str3 = "<";
        sb.append(">");
        contentValues2.put("cid", sb.toString());
        contentValues2.put("cl", str);
        MmsBody mmsBody = mms.body;
        contentValues2.put("chset", Integer.valueOf(mmsBody == null ? 106 : mmsBody.charSet));
        MmsBody mmsBody2 = mms.body;
        contentValues2.put("text", mmsBody2 == null ? "" : mmsBody2.text);
        if (this.mContentResolver.insert(uriBuild, contentValues2) == null) {
            Log.e("TelephonyBackupAgent", "Could not insert body part");
            return;
        }
        List<ContentValues> list = mms.attachments;
        if (list != null) {
            Iterator<ContentValues> it = list.iterator();
            while (it.hasNext()) {
                ContentValues next = it.next();
                Iterator<ContentValues> it2 = it;
                ContentValues contentValues3 = new ContentValues(6);
                contentValues3.put("mid", Long.valueOf(jCurrentTimeMillis));
                contentValues3.put("seq", (Integer) 0);
                contentValues3.put("ct", next.getAsString("mime_type"));
                String asString = next.getAsString("filename");
                StringBuilder sb2 = new StringBuilder();
                long j = jCurrentTimeMillis;
                String str4 = str3;
                sb2.append(str4);
                sb2.append(asString);
                sb2.append(">");
                contentValues3.put("cid", sb2.toString());
                contentValues3.put("cl", asString);
                contentValues3.put("_data", getDataDir() + ATTACHMENT_DATA_PATH + asString);
                if (this.mContentResolver.insert(uriBuild, contentValues3) == null) {
                    Log.e("TelephonyBackupAgent", "Could not insert attachment part");
                    return;
                } else {
                    str3 = str4;
                    it = it2;
                    jCurrentTimeMillis = j;
                }
            }
        }
        Uri uriInsert = this.mContentResolver.insert(Telephony.Mms.CONTENT_URI, mms.values);
        if (uriInsert == null) {
            Log.e("TelephonyBackupAgent", "Could not insert mms");
            return;
        }
        long id = ContentUris.parseId(uriInsert);
        ContentValues contentValues4 = new ContentValues(1);
        contentValues4.put("mid", Long.valueOf(id));
        this.mContentResolver.update(uriBuild, contentValues4, null, null);
        Uri uriWithAppendedPath = Uri.withAppendedPath(uriInsert, "addr");
        Iterator<ContentValues> it3 = mms.addresses.iterator();
        while (it3.hasNext()) {
            ContentValues contentValues5 = new ContentValues(it3.next());
            contentValues5.put("msg_id", Long.valueOf(id));
            this.mContentResolver.insert(uriWithAppendedPath, contentValues5);
        }
    }

    private static final class MmsBody {
        public int charSet;
        public String text;

        public MmsBody(String str, int i) {
            this.text = str;
            this.charSet = i;
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof MmsBody)) {
                return false;
            }
            MmsBody mmsBody = (MmsBody) obj;
            return this.text.equals(mmsBody.text) && this.charSet == mmsBody.charSet;
        }

        public String toString() {
            return "Text:" + this.text + " charSet:" + this.charSet;
        }
    }

    private static final class Mms {
        public List<ContentValues> addresses;
        public List<ContentValues> attachments;
        public MmsBody body;
        public String smil;
        public ContentValues values;

        private Mms() {
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Values:");
            sb.append(this.values.toString());
            sb.append("\nRecipients:");
            sb.append(this.addresses.toString());
            sb.append("\nAttachments:");
            List<ContentValues> list = this.attachments;
            sb.append(list == null ? "none" : list.toString());
            sb.append("\nBody:");
            sb.append(this.body);
            return sb.toString();
        }
    }

    private JsonWriter getJsonWriter(String str) throws IOException {
        return new JsonWriter(new BufferedWriter(new OutputStreamWriter(new DeflaterOutputStream(openFileOutput(str, 0)), "UTF-8"), 32768));
    }

    private static JsonReader getJsonReader(FileDescriptor fileDescriptor) throws IOException {
        return new JsonReader(new InputStreamReader(new InflaterInputStream(new FileInputStream(fileDescriptor)), "UTF-8"));
    }

    private static void writeStringToWriter(JsonWriter jsonWriter, Cursor cursor, String str) throws IOException {
        String string = cursor.getString(cursor.getColumnIndex(str));
        if (string != null) {
            jsonWriter.name(str).value(string);
        }
    }

    private static void writeIntToWriter(JsonWriter jsonWriter, Cursor cursor, String str) throws IOException {
        int i = cursor.getInt(cursor.getColumnIndex(str));
        if (i != 0) {
            jsonWriter.name(str).value(i);
        }
    }

    private long getOrCreateThreadId(Set<String> set) {
        if (set == null) {
            set = new ArraySet<>();
        }
        if (set.isEmpty()) {
            set.add(UNKNOWN_SENDER);
        }
        if (this.mCacheGetOrCreateThreadId == null) {
            this.mCacheGetOrCreateThreadId = new HashMap();
        }
        if (!this.mCacheGetOrCreateThreadId.containsKey(set)) {
            long orCreateThreadId = this.mUnknownSenderThreadId;
            try {
                orCreateThreadId = Telephony.Threads.getOrCreateThreadId(this, set);
            } catch (RuntimeException e) {
                Log.e("TelephonyBackupAgent", "Problem obtaining thread.", e);
            }
            this.mCacheGetOrCreateThreadId.put(set, Long.valueOf(orCreateThreadId));
            return orCreateThreadId;
        }
        return this.mCacheGetOrCreateThreadId.get(set).longValue();
    }

    private List<String> getRecipientsByThread(long j) {
        if (this.mCacheRecipientsByThread == null) {
            this.mCacheRecipientsByThread = new HashMap();
        }
        if (!this.mCacheRecipientsByThread.containsKey(Long.valueOf(j))) {
            String rawRecipientIdsForThread = getRawRecipientIdsForThread(j);
            if (!TextUtils.isEmpty(rawRecipientIdsForThread)) {
                this.mCacheRecipientsByThread.put(Long.valueOf(j), getAddresses(rawRecipientIdsForThread));
            } else {
                this.mCacheRecipientsByThread.put(Long.valueOf(j), new ArrayList());
            }
        }
        return this.mCacheRecipientsByThread.get(Long.valueOf(j));
    }

    private String getRawRecipientIdsForThread(long j) {
        Cursor cursorQuery;
        if (j > 0 && (cursorQuery = this.mContentResolver.query(ALL_THREADS_URI, SMS_RECIPIENTS_PROJECTION, "_id=?", new String[]{String.valueOf(j)}, null)) != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    return cursorQuery.getString(1);
                }
            } finally {
                cursorQuery.close();
            }
        }
        return null;
    }

    private List<String> getAddresses(String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.split(" ")) {
            try {
                long j = Long.parseLong(str2);
                if (j < 0) {
                    Log.e("TelephonyBackupAgent", "getAddresses: invalid id " + j);
                } else {
                    Cursor cursorQuery = null;
                    try {
                        cursorQuery = this.mContentResolver.query(ContentUris.withAppendedId(SINGLE_CANONICAL_ADDRESS_URI, j), null, null, null, null);
                    } catch (Exception e) {
                        Log.e("TelephonyBackupAgent", "getAddresses: query failed for id " + j, e);
                    }
                    if (cursorQuery != null) {
                        try {
                            if (cursorQuery.moveToFirst()) {
                                String string = cursorQuery.getString(0);
                                if (TextUtils.isEmpty(string)) {
                                    Log.d("TelephonyBackupAgent", "Canonical MMS/SMS address is empty for id: " + j);
                                } else {
                                    arrayList.add(string);
                                }
                            }
                        } finally {
                            cursorQuery.close();
                        }
                    } else {
                        continue;
                    }
                }
            } catch (NumberFormatException e2) {
                Log.e("TelephonyBackupAgent", "getAddresses: invalid id " + e2, e2);
            }
        }
        if (arrayList.isEmpty()) {
            Log.d("TelephonyBackupAgent", "No MMS addresses found from ids string [" + str + "]");
        }
        return arrayList;
    }

    public static boolean getIsRestoring() {
        return sIsRestoring;
    }

    private static class BackupChunkInformation {
        private int count;
        private long timestamp;

        private BackupChunkInformation() {
            this.count = 0;
        }

        static /* synthetic */ int access$104(BackupChunkInformation backupChunkInformation) {
            int i = backupChunkInformation.count + 1;
            backupChunkInformation.count = i;
            return i;
        }

        static /* synthetic */ int access$108(BackupChunkInformation backupChunkInformation) {
            int i = backupChunkInformation.count;
            backupChunkInformation.count = i + 1;
            return i;
        }
    }
}
