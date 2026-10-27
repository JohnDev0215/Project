package com.android.providers.telephony;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.storage.StorageManager;
import android.preference.PreferenceManager;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.telephony.PhoneFactory;
import com.google.android.mms.pdu.EncodedStringValue;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class MmsSmsDatabaseHelper extends SQLiteOpenHelper {
    private static MmsSmsDatabaseHelper sCeInstance = null;
    private static MmsSmsDatabaseErrorHandler sDbErrorHandler = null;
    private static MmsSmsDatabaseHelper sDeInstance = null;
    private static boolean sFakeLowStorageTest = false;
    private static boolean sTriedAutoIncrement = false;
    private final Context mContext;
    private LowStorageMonitor mLowStorageMonitor;
    private static final String[] BIND_ARGS_NONE = new String[0];
    private static AtomicBoolean sInitialCreateDone = new AtomicBoolean(false);

    @VisibleForTesting
    public static String CREATE_SMS_TABLE_STRING = "CREATE TABLE sms (_id INTEGER PRIMARY KEY,thread_id INTEGER,address TEXT,person INTEGER,date INTEGER,date_sent INTEGER DEFAULT 0,protocol INTEGER,read INTEGER DEFAULT 0,status INTEGER DEFAULT -1,type INTEGER,reply_path_present INTEGER,subject TEXT,body TEXT,service_center TEXT,locked INTEGER DEFAULT 0,sub_id INTEGER DEFAULT -1, error_code INTEGER DEFAULT -1, creator TEXT,seen INTEGER DEFAULT 0);";

    @VisibleForTesting
    public static String CREATE_ATTACHMENTS_TABLE_STRING = "CREATE TABLE attachments (sms_id INTEGER,content_url TEXT,offset INTEGER);";

    @VisibleForTesting
    public static String CREATE_RAW_TABLE_STRING = "CREATE TABLE raw (_id INTEGER PRIMARY KEY,date INTEGER,reference_number INTEGER,count INTEGER,sequence INTEGER,destination_port INTEGER,address TEXT,sub_id INTEGER DEFAULT -1, pdu TEXT,deleted INTEGER DEFAULT 0,message_body TEXT,display_originating_addr TEXT);";

    private static class MmsSmsDatabaseErrorHandler implements DatabaseErrorHandler {
        private Context mContext;
        private DefaultDatabaseErrorHandler mDefaultDatabaseErrorHandler = new DefaultDatabaseErrorHandler();

        MmsSmsDatabaseErrorHandler(Context context) {
            this.mContext = context;
        }

        @Override // android.database.DatabaseErrorHandler
        public void onCorruption(SQLiteDatabase sQLiteDatabase) {
            MmsSmsDatabaseHelper.localLogWtf("Corruption reported by sqlite on database: " + sQLiteDatabase.getPath());
            MmsSmsDatabaseHelper.sendDbLostIntent(this.mContext, true);
            this.mDefaultDatabaseErrorHandler.onCorruption(sQLiteDatabase);
        }
    }

    @VisibleForTesting
    MmsSmsDatabaseHelper(Context context, MmsSmsDatabaseErrorHandler mmsSmsDatabaseErrorHandler) {
        super(context, "mmssms.db", null, 67, mmsSmsDatabaseErrorHandler);
        this.mContext = context;
        setIdleConnectionTimeout(30000L);
        setWriteAheadLoggingEnabled(false);
        try {
            PhoneFactory.addLocalLog("MmsSmsDatabaseHelper", 100);
        } catch (IllegalArgumentException unused) {
        }
    }

    private static synchronized MmsSmsDatabaseErrorHandler getDbErrorHandler(Context context) {
        if (sDbErrorHandler == null) {
            sDbErrorHandler = new MmsSmsDatabaseErrorHandler(context);
        }
        return sDbErrorHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void sendDbLostIntent(Context context, boolean z) {
        Intent intent = new Intent("android.provider.action.SMS_MMS_DB_LOST");
        intent.putExtra("android.provider.extra.IS_CORRUPTED", z);
        intent.addFlags(16777216);
        context.sendBroadcast(intent);
    }

    static synchronized MmsSmsDatabaseHelper getInstanceForDe(Context context) {
        if (sDeInstance == null) {
            Context deviceEncryptedContext = ProviderUtil.getDeviceEncryptedContext(context);
            sDeInstance = new MmsSmsDatabaseHelper(deviceEncryptedContext, getDbErrorHandler(deviceEncryptedContext));
        }
        return sDeInstance;
    }

    static synchronized MmsSmsDatabaseHelper getInstanceForCe(Context context) {
        if (sCeInstance == null) {
            if (StorageManager.isFileEncryptedNativeOrEmulated()) {
                Context credentialEncryptedContext = ProviderUtil.getCredentialEncryptedContext(context);
                sCeInstance = new MmsSmsDatabaseHelper(credentialEncryptedContext, getDbErrorHandler(credentialEncryptedContext));
            } else {
                sCeInstance = getInstanceForDe(context);
            }
        }
        return sCeInstance;
    }

    private static void removeUnferencedCanonicalAddresses(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("threads", new String[]{"recipient_ids"}, null, null, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() == 0) {
                    sQLiteDatabase.delete("canonical_addresses", null, null);
                } else {
                    HashSet hashSet = new HashSet();
                    while (cursorQuery.moveToNext()) {
                        for (String str : cursorQuery.getString(0).split(" ")) {
                            try {
                                hashSet.add(Integer.valueOf(Integer.parseInt(str)));
                            } catch (Exception unused) {
                            }
                        }
                    }
                    StringBuilder sb = new StringBuilder();
                    Iterator it = hashSet.iterator();
                    sb.append("_id NOT IN (");
                    while (it.hasNext()) {
                        sb.append(it.next());
                        if (it.hasNext()) {
                            sb.append(",");
                        }
                    }
                    sb.append(")");
                    sQLiteDatabase.delete("canonical_addresses", sb.toString(), null);
                }
            } finally {
                cursorQuery.close();
            }
        }
    }

    public static void updateThread(SQLiteDatabase sQLiteDatabase, long j) {
        if (j < 0) {
            updateThreads(sQLiteDatabase, null, null);
        } else {
            updateThreads(sQLiteDatabase, "(thread_id = ?)", new String[]{String.valueOf(j)});
        }
    }

    public static void updateThreads(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        if (str == null) {
            str = "1";
        }
        if (strArr == null) {
            strArr = BIND_ARGS_NONE;
        }
        sQLiteDatabase.beginTransaction();
        try {
            if (sQLiteDatabase.delete("threads", "_id NOT IN ( SELECT DISTINCT thread_id FROM sms WHERE thread_id IS NOT NULL UNION SELECT DISTINCT thread_id FROM pdu WHERE thread_id IS NOT NULL)", null) > 0) {
                removeUnferencedCanonicalAddresses(sQLiteDatabase);
            }
            sQLiteDatabase.execSQL(" UPDATE threads SET message_count = ( SELECT COUNT(sms._id) FROM sms WHERE thread_id = threads._id AND sms.type != 3 ) + ( SELECT COUNT(pdu._id) FROM pdu WHERE thread_id = threads._id AND (m_type=132 OR m_type=130 OR m_type=128) AND msg_box != 3 ) WHERE EXISTS ( SELECT _id FROM sms WHERE thread_id = threads._id AND (" + str + ") LIMIT 1 );", strArr);
            sQLiteDatabase.execSQL(" WITH matches AS ( SELECT date * 1000 AS date, sub AS snippet, sub_cs AS snippet_cs, thread_id FROM pdu WHERE thread_id = threads._id UNION SELECT date, body AS snippet, 0 AS snippet_cs, thread_id FROM sms WHERE thread_id = threads._id ORDER BY date DESC LIMIT 1 ) UPDATE threads SET date   = (SELECT date FROM matches), snippet    = (SELECT snippet FROM matches), snippet_cs = (SELECT snippet_cs FROM matches) WHERE EXISTS ( SELECT _id FROM sms WHERE thread_id = threads._id AND (" + str + ") LIMIT 1 );", strArr);
            sQLiteDatabase.execSQL(" UPDATE threads SET error = EXISTS ( SELECT type FROM sms WHERE type=5 AND thread_id = threads._id ) WHERE EXISTS ( SELECT _id FROM sms WHERE thread_id = threads._id AND (" + str + ") LIMIT 1 );", strArr);
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            try {
            } finally {
            }
        }
    }

    public static int deleteOneSms(SQLiteDatabase sQLiteDatabase, int i) {
        Cursor cursorQuery = sQLiteDatabase.query("sms", new String[]{"thread_id"}, "_id=" + i, null, null, null, null);
        if (cursorQuery != null) {
            i = cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : -1;
            cursorQuery.close();
        }
        int iDelete = sQLiteDatabase.delete("sms", "_id=" + i, null);
        if (i > 0) {
            updateThread(sQLiteDatabase, i);
        }
        return iDelete;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Context context;
        localLog("onCreate: Creating all SMS-MMS tables.");
        if (!StorageManager.isFileEncryptedNativeOrEmulated() || ((context = this.mContext) != null && context.isCredentialProtectedStorage())) {
            localLog("onCreate: broadcasting ACTION_SMS_MMS_DB_CREATED");
            Intent intent = new Intent("android.provider.action.SMS_MMS_DB_CREATED");
            intent.addFlags(16777216);
            if (isInitialCreateDone()) {
                localLogWtf("onCreate: was already called once earlier");
                intent.putExtra("android.provider.extra.IS_INITIAL_CREATE", false);
            } else {
                setInitialCreateDone();
                intent.putExtra("android.provider.extra.IS_INITIAL_CREATE", true);
            }
            this.mContext.sendBroadcast(intent);
        }
        createMmsTables(sQLiteDatabase);
        createSmsTables(sQLiteDatabase);
        createCommonTables(sQLiteDatabase);
        createCommonTriggers(sQLiteDatabase);
        createMmsTriggers(sQLiteDatabase);
        createWordsTables(sQLiteDatabase);
        createIndices(sQLiteDatabase);
    }

    private static void localLog(String str) {
        Log.d("MmsSmsDatabaseHelper", str);
        PhoneFactory.localLog("MmsSmsDatabaseHelper", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void localLogWtf(String str) {
        Log.wtf("MmsSmsDatabaseHelper", str);
        PhoneFactory.localLog("MmsSmsDatabaseHelper", str);
    }

    private boolean isInitialCreateDone() {
        return PreferenceManager.getDefaultSharedPreferences(this.mContext).getBoolean("initial_create_done", false);
    }

    private void setInitialCreateDone() {
        if (sInitialCreateDone.getAndSet(true)) {
            return;
        }
        SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(this.mContext).edit();
        editorEdit.putBoolean("initial_create_done", true);
        editorEdit.commit();
    }

    private void populateWordsTable(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("sms", new String[]{"_id", "body"}, null, null, null, null, null);
        if (cursorQuery != null) {
            try {
                cursorQuery.moveToPosition(-1);
                ContentValues contentValues = new ContentValues();
                while (cursorQuery.moveToNext()) {
                    contentValues.clear();
                    long j = cursorQuery.getLong(0);
                    String string = cursorQuery.getString(1);
                    contentValues.put("_id", Long.valueOf(j));
                    contentValues.put("index_text", string);
                    contentValues.put("source_id", Long.valueOf(j));
                    contentValues.put("table_to_use", (Integer) 1);
                    sQLiteDatabase.insert("words", "index_text", contentValues);
                }
            } finally {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        cursorQuery = sQLiteDatabase.query("part", new String[]{"_id", "text"}, "ct = 'text/plain'", null, null, null, null);
        if (cursorQuery != null) {
            try {
                cursorQuery.moveToPosition(-1);
                ContentValues contentValues2 = new ContentValues();
                while (cursorQuery.moveToNext()) {
                    contentValues2.clear();
                    long j2 = cursorQuery.getLong(0);
                    String string2 = cursorQuery.getString(1);
                    contentValues2.put("_id", Long.valueOf(j2));
                    contentValues2.put("index_text", string2);
                    contentValues2.put("source_id", Long.valueOf(j2));
                    contentValues2.put("table_to_use", (Integer) 1);
                    sQLiteDatabase.insert("words", "index_text", contentValues2);
                }
            } finally {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        }
    }

    private void createWordsTables(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE VIRTUAL TABLE words USING FTS3 (_id INTEGER PRIMARY KEY, index_text TEXT, source_id INTEGER, table_to_use INTEGER);");
            sQLiteDatabase.execSQL("CREATE TRIGGER sms_words_update AFTER UPDATE ON sms BEGIN UPDATE words  SET index_text = NEW.body WHERE (source_id=NEW._id AND table_to_use=1);  END;");
            sQLiteDatabase.execSQL("CREATE TRIGGER sms_words_delete AFTER DELETE ON sms BEGIN DELETE FROM   words WHERE source_id = OLD._id AND table_to_use = 1; END;");
            populateWordsTable(sQLiteDatabase);
        } catch (Exception e) {
            Log.e("MmsSmsDatabaseHelper", "got exception creating words table: " + e.toString());
        }
    }

    private void createIndices(SQLiteDatabase sQLiteDatabase) {
        createThreadIdIndex(sQLiteDatabase);
        createThreadIdDateIndex(sQLiteDatabase);
        createPartMidIndex(sQLiteDatabase);
        createAddrMsgIdIndex(sQLiteDatabase);
    }

    private void createThreadIdIndex(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS typeThreadIdIndex ON sms (type, thread_id);");
        } catch (Exception e) {
            Log.e("MmsSmsDatabaseHelper", "got exception creating indices: " + e.toString());
        }
    }

    private void createThreadIdDateIndex(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS threadIdDateIndex ON sms (thread_id, date);");
        } catch (Exception e) {
            Log.e("MmsSmsDatabaseHelper", "got exception creating indices: " + e.toString());
        }
    }

    private void createPartMidIndex(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS partMidIndex ON part (mid)");
        } catch (Exception e) {
            Log.e("MmsSmsDatabaseHelper", "got exception creating indices: " + e.toString());
        }
    }

    private void createAddrMsgIdIndex(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS addrMsgIdIndex ON addr (msg_id)");
        } catch (Exception e) {
            Log.e("MmsSmsDatabaseHelper", "got exception creating indices: " + e.toString());
        }
    }

    @VisibleForTesting
    void createMmsTables(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE pdu (_id INTEGER PRIMARY KEY AUTOINCREMENT,thread_id INTEGER,date INTEGER,date_sent INTEGER DEFAULT 0,msg_box INTEGER,read INTEGER DEFAULT 0,m_id TEXT,sub TEXT,sub_cs INTEGER,ct_t TEXT,ct_l TEXT,exp INTEGER,m_cls TEXT,m_type INTEGER,v INTEGER,m_size INTEGER,pri INTEGER,rr INTEGER,rpt_a INTEGER,resp_st INTEGER,st INTEGER,tr_id TEXT,retr_st INTEGER,retr_txt TEXT,retr_txt_cs INTEGER,read_status INTEGER,ct_cls INTEGER,resp_txt TEXT,d_tm INTEGER,d_rpt INTEGER,locked INTEGER DEFAULT 0,sub_id INTEGER DEFAULT -1, seen INTEGER DEFAULT 0,creator TEXT,text_only INTEGER DEFAULT 0);");
        sQLiteDatabase.execSQL("CREATE TABLE addr (_id INTEGER PRIMARY KEY,msg_id INTEGER,contact_id INTEGER,address TEXT,type INTEGER,charset INTEGER);");
        sQLiteDatabase.execSQL("CREATE TABLE part (_id INTEGER PRIMARY KEY AUTOINCREMENT,mid INTEGER,seq INTEGER DEFAULT 0,ct TEXT,name TEXT,chset INTEGER,cd TEXT,fn TEXT,cid TEXT,cl TEXT,ctt_s INTEGER,ctt_t TEXT,_data TEXT,text TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE rate (sent_time INTEGER);");
        sQLiteDatabase.execSQL("CREATE TABLE drm (_id INTEGER PRIMARY KEY,_data TEXT);");
        sQLiteDatabase.execSQL("CREATE VIEW pdu_restricted AS SELECT * FROM pdu WHERE (msg_box=1 OR msg_box=2) AND (m_type!=130);");
    }

    private void createMmsTriggers(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS part_cleanup");
        sQLiteDatabase.execSQL("CREATE TRIGGER part_cleanup DELETE ON pdu BEGIN   DELETE FROM part  WHERE mid=old._id;END;");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS addr_cleanup");
        sQLiteDatabase.execSQL("CREATE TRIGGER addr_cleanup DELETE ON pdu BEGIN   DELETE FROM addr  WHERE msg_id=old._id;END;");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS cleanup_delivery_and_read_report");
        sQLiteDatabase.execSQL("CREATE TRIGGER cleanup_delivery_and_read_report AFTER DELETE ON pdu WHEN old.m_type=128 BEGIN   DELETE FROM pdu  WHERE (m_type=134    OR m_type=136)    AND m_id=old.m_id; END;");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS update_threads_on_insert_part");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_on_insert_part  AFTER INSERT ON part  WHEN new.ct != 'text/plain' AND new.ct != 'application/smil'  BEGIN   UPDATE threads SET has_attachment=1 WHERE _id IN    (SELECT pdu.thread_id FROM part JOIN pdu ON pdu._id=part.mid      WHERE part._id=new._id LIMIT 1);  END");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS update_threads_on_update_part");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_on_update_part  AFTER UPDATE of mid ON part  WHEN new.ct != 'text/plain' AND new.ct != 'application/smil'  BEGIN   UPDATE threads SET has_attachment=1 WHERE _id IN    (SELECT pdu.thread_id FROM part JOIN pdu ON pdu._id=part.mid      WHERE part._id=new._id LIMIT 1);  END");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS update_threads_on_delete_part");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_on_delete_part  AFTER DELETE ON part  WHEN old.ct != 'text/plain' AND old.ct != 'application/smil'  BEGIN   UPDATE threads SET has_attachment =    CASE     (SELECT COUNT(*) FROM part JOIN pdu      WHERE pdu.thread_id = threads._id      AND part.ct != 'text/plain' AND part.ct != 'application/smil'      AND part.mid = pdu._id)   WHEN 0 THEN 0    ELSE 1    END;  END");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS update_threads_on_update_pdu");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_on_update_pdu  AFTER UPDATE of thread_id ON pdu  BEGIN   UPDATE threads SET has_attachment=1 WHERE _id IN    (SELECT pdu.thread_id FROM part JOIN pdu      WHERE part.ct != 'text/plain' AND part.ct != 'application/smil'      AND part.mid = pdu._id); END");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS delete_mms_pending_on_delete");
        sQLiteDatabase.execSQL("CREATE TRIGGER delete_mms_pending_on_delete AFTER DELETE ON pdu BEGIN   DELETE FROM pending_msgs  WHERE msg_id=old._id; END;");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS delete_mms_pending_on_update");
        sQLiteDatabase.execSQL("CREATE TRIGGER delete_mms_pending_on_update AFTER UPDATE ON pdu WHEN old.msg_box=4  AND new.msg_box!=4 BEGIN   DELETE FROM pending_msgs  WHERE msg_id=new._id; END;");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS insert_mms_pending_on_insert");
        sQLiteDatabase.execSQL("CREATE TRIGGER insert_mms_pending_on_insert AFTER INSERT ON pdu WHEN new.m_type=130  OR new.m_type=135 BEGIN   INSERT INTO pending_msgs    (proto_type,     msg_id,     msg_type,     err_type,     err_code,     retry_index,     due_time)   VALUES     (1,      new._id,      new.m_type,0,0,0,0);END;");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS insert_mms_pending_on_update");
        sQLiteDatabase.execSQL("CREATE TRIGGER insert_mms_pending_on_update AFTER UPDATE ON pdu WHEN new.m_type=128  AND new.msg_box=4  AND old.msg_box!=4 BEGIN   INSERT INTO pending_msgs    (proto_type,     msg_id,     msg_type,     err_type,     err_code,     retry_index,     due_time)   VALUES     (1,      new._id,      new.m_type,0,0,0,0);END;");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS mms_words_update");
        sQLiteDatabase.execSQL("CREATE TRIGGER mms_words_update AFTER UPDATE ON part BEGIN UPDATE words  SET index_text = NEW.text WHERE (source_id=NEW._id AND table_to_use=2);  END;");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS mms_words_delete");
        sQLiteDatabase.execSQL("CREATE TRIGGER mms_words_delete AFTER DELETE ON part BEGIN DELETE FROM  words WHERE source_id = OLD._id AND table_to_use = 2; END;");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_date_subject_on_update");
        sQLiteDatabase.execSQL("CREATE TRIGGER pdu_update_thread_date_subject_on_update AFTER  UPDATE OF date, sub, msg_box  ON pdu   WHEN new.m_type=132    OR new.m_type=130    OR new.m_type=128 BEGIN  UPDATE threads SET    date = (strftime('%s','now') * 1000),     snippet = new.sub,     snippet_cs = new.sub_cs  WHERE threads._id = new.thread_id;   UPDATE threads SET message_count =      (SELECT COUNT(sms._id) FROM sms LEFT JOIN threads       ON threads._id = thread_id      WHERE thread_id = new.thread_id        AND sms.type != 3) +      (SELECT COUNT(pdu._id) FROM pdu LEFT JOIN threads       ON threads._id = thread_id      WHERE thread_id = new.thread_id        AND (m_type=132 OR m_type=130 OR m_type=128)        AND msg_box != 3)   WHERE threads._id = new.thread_id;   UPDATE threads SET read =     CASE (SELECT COUNT(*)          FROM pdu          WHERE read = 0            AND thread_id = threads._id             AND (m_type=132 OR m_type=130 OR m_type=128))       WHEN 0 THEN 1      ELSE 0    END  WHERE threads._id = new.thread_id; END;");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_on_delete");
        sQLiteDatabase.execSQL("CREATE TRIGGER pdu_update_thread_on_delete AFTER DELETE ON pdu BEGIN   UPDATE threads SET      date = (strftime('%s','now') * 1000)  WHERE threads._id = old.thread_id;   UPDATE threads SET message_count =      (SELECT COUNT(sms._id) FROM sms LEFT JOIN threads       ON threads._id = thread_id      WHERE thread_id = old.thread_id        AND sms.type != 3) +      (SELECT COUNT(pdu._id) FROM pdu LEFT JOIN threads       ON threads._id = thread_id      WHERE thread_id = old.thread_id        AND (m_type=132 OR m_type=130 OR m_type=128)        AND msg_box != 3)   WHERE threads._id = old.thread_id;   UPDATE threads SET snippet =    (SELECT snippet FROM     (SELECT date * 1000 AS date, sub AS snippet, thread_id FROM pdu      UNION SELECT date, body AS snippet, thread_id FROM sms)    WHERE thread_id = OLD.thread_id ORDER BY date DESC LIMIT 1)   WHERE threads._id = OLD.thread_id;   UPDATE threads SET snippet_cs =    (SELECT snippet_cs FROM     (SELECT date * 1000 AS date, sub_cs AS snippet_cs, thread_id FROM pdu      UNION SELECT date, 0 AS snippet_cs, thread_id FROM sms)    WHERE thread_id = OLD.thread_id ORDER BY date DESC LIMIT 1)   WHERE threads._id = OLD.thread_id; END;");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_on_insert");
        sQLiteDatabase.execSQL("CREATE TRIGGER pdu_update_thread_on_insert AFTER INSERT ON pdu   WHEN new.m_type=132    OR new.m_type=130    OR new.m_type=128 BEGIN  UPDATE threads SET    date = (strftime('%s','now') * 1000),     snippet = new.sub,     snippet_cs = new.sub_cs  WHERE threads._id = new.thread_id;   UPDATE threads SET message_count =      (SELECT COUNT(sms._id) FROM sms LEFT JOIN threads       ON threads._id = thread_id      WHERE thread_id = new.thread_id        AND sms.type != 3) +      (SELECT COUNT(pdu._id) FROM pdu LEFT JOIN threads       ON threads._id = thread_id      WHERE thread_id = new.thread_id        AND (m_type=132 OR m_type=130 OR m_type=128)        AND msg_box != 3)   WHERE threads._id = new.thread_id;   UPDATE threads SET read =     CASE (SELECT COUNT(*)          FROM pdu          WHERE read = 0            AND thread_id = threads._id             AND (m_type=132 OR m_type=130 OR m_type=128))       WHEN 0 THEN 1      ELSE 0    END  WHERE threads._id = new.thread_id; END;");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_read_on_update");
        sQLiteDatabase.execSQL("CREATE TRIGGER pdu_update_thread_read_on_update AFTER  UPDATE OF read  ON pdu   WHEN new.m_type=132    OR new.m_type=130    OR new.m_type=128 BEGIN   UPDATE threads SET read =     CASE (SELECT COUNT(*)          FROM pdu          WHERE read = 0            AND thread_id = threads._id             AND (m_type=132 OR m_type=130 OR m_type=128))       WHEN 0 THEN 1      ELSE 0    END  WHERE threads._id = new.thread_id; END;");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS update_threads_error_on_delete_mms");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_error_on_delete_mms   BEFORE DELETE ON pdu  WHEN OLD._id IN (SELECT DISTINCT msg_id                   FROM pending_msgs                   WHERE err_type >= 10) BEGIN   UPDATE threads SET error = error - 1  WHERE _id = OLD.thread_id; END;");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS update_threads_error_on_move_mms");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_error_on_move_mms   BEFORE UPDATE OF msg_box ON pdu   WHEN (OLD.msg_box = 4 AND NEW.msg_box != 4)   AND (OLD._id IN (SELECT DISTINCT msg_id                   FROM pending_msgs                   WHERE err_type >= 10)) BEGIN   UPDATE threads SET error = error - 1  WHERE _id = OLD.thread_id; END;");
    }

    @VisibleForTesting
    void createSmsTables(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(CREATE_SMS_TABLE_STRING);
        sQLiteDatabase.execSQL(CREATE_RAW_TABLE_STRING);
        sQLiteDatabase.execSQL(CREATE_ATTACHMENTS_TABLE_STRING);
        sQLiteDatabase.execSQL("CREATE TABLE sr_pending (reference_number INTEGER,action TEXT,data TEXT);");
        sQLiteDatabase.execSQL("CREATE VIEW sms_restricted AS SELECT * FROM sms WHERE type=1 OR type=2;");
        if (this.mContext.getPackageManager().hasSystemFeature("android.hardware.type.automotive")) {
            sQLiteDatabase.execSQL("CREATE TABLE sms_changes (_id INTEGER PRIMARY KEY,orig_rowid INTEGER,sub_id INTEGER,type INTEGER,new_read_status INTEGER);");
            sQLiteDatabase.execSQL("CREATE TRIGGER sms_update_on_read_change_row AFTER UPDATE OF read ON sms WHEN NEW.read != OLD.read BEGIN   INSERT INTO sms_changes VALUES(null, NEW._id, NEW.sub_id, 0, NEW.read); END;");
            sQLiteDatabase.execSQL("CREATE TRIGGER sms_delete_change_row AFTER DELETE ON sms BEGIN   INSERT INTO sms_changes values(null, OLD._id, OLD.sub_id, 1, null); END;");
        }
    }

    @VisibleForTesting
    void createCommonTables(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE canonical_addresses (_id INTEGER PRIMARY KEY AUTOINCREMENT,address TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE threads (_id INTEGER PRIMARY KEY AUTOINCREMENT,date INTEGER DEFAULT 0,message_count INTEGER DEFAULT 0,recipient_ids TEXT,snippet TEXT,snippet_cs INTEGER DEFAULT 0,read INTEGER DEFAULT 1,archived INTEGER DEFAULT 0,type INTEGER DEFAULT 0,error INTEGER DEFAULT 0,has_attachment INTEGER DEFAULT 0);");
        sQLiteDatabase.execSQL("CREATE TABLE pending_msgs (_id INTEGER PRIMARY KEY,proto_type INTEGER,msg_id INTEGER,msg_type INTEGER,err_type INTEGER,err_code INTEGER,retry_index INTEGER NOT NULL DEFAULT 0,due_time INTEGER,pending_sub_id INTEGER DEFAULT -1, last_try INTEGER);");
    }

    private void createCommonTriggers(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TRIGGER sms_update_thread_on_insert AFTER INSERT ON sms BEGIN  UPDATE threads SET    date = (strftime('%s','now') * 1000),     snippet = new.body,     snippet_cs = 0  WHERE threads._id = new.thread_id;   UPDATE threads SET message_count =      (SELECT COUNT(sms._id) FROM sms LEFT JOIN threads       ON threads._id = thread_id      WHERE thread_id = new.thread_id        AND sms.type != 3) +      (SELECT COUNT(pdu._id) FROM pdu LEFT JOIN threads       ON threads._id = thread_id      WHERE thread_id = new.thread_id        AND (m_type=132 OR m_type=130 OR m_type=128)        AND msg_box != 3)   WHERE threads._id = new.thread_id;   UPDATE threads SET read =     CASE (SELECT COUNT(*)          FROM sms          WHERE read = 0            AND thread_id = threads._id)      WHEN 0 THEN 1      ELSE 0    END  WHERE threads._id = new.thread_id; END;");
        sQLiteDatabase.execSQL("CREATE TRIGGER sms_update_thread_date_subject_on_update AFTER  UPDATE OF date, body, type  ON sms BEGIN  UPDATE threads SET    date = (strftime('%s','now') * 1000),     snippet = new.body,     snippet_cs = 0  WHERE threads._id = new.thread_id;   UPDATE threads SET message_count =      (SELECT COUNT(sms._id) FROM sms LEFT JOIN threads       ON threads._id = thread_id      WHERE thread_id = new.thread_id        AND sms.type != 3) +      (SELECT COUNT(pdu._id) FROM pdu LEFT JOIN threads       ON threads._id = thread_id      WHERE thread_id = new.thread_id        AND (m_type=132 OR m_type=130 OR m_type=128)        AND msg_box != 3)   WHERE threads._id = new.thread_id;   UPDATE threads SET read =     CASE (SELECT COUNT(*)          FROM sms          WHERE read = 0            AND thread_id = threads._id)      WHEN 0 THEN 1      ELSE 0    END  WHERE threads._id = new.thread_id; END;");
        sQLiteDatabase.execSQL("CREATE TRIGGER sms_update_thread_read_on_update AFTER  UPDATE OF read  ON sms BEGIN   UPDATE threads SET read =     CASE (SELECT COUNT(*)          FROM sms          WHERE read = 0            AND thread_id = threads._id)      WHEN 0 THEN 1      ELSE 0    END  WHERE threads._id = new.thread_id; END;");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_error_on_update_mms   AFTER UPDATE OF err_type ON pending_msgs   WHEN (OLD.err_type < 10 AND NEW.err_type >= 10)    OR (OLD.err_type >= 10 AND NEW.err_type < 10) BEGIN  UPDATE threads SET error =     CASE      WHEN NEW.err_type >= 10 THEN error + 1      ELSE error - 1    END   WHERE _id =   (SELECT DISTINCT thread_id    FROM pdu    WHERE _id = NEW.msg_id); END;");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_error_on_update_sms   AFTER UPDATE OF type ON sms  WHEN (OLD.type != 5 AND NEW.type = 5)    OR (OLD.type = 5 AND NEW.type != 5) BEGIN   UPDATE threads SET error =     CASE      WHEN NEW.type = 5 THEN error + 1      ELSE error - 1    END   WHERE _id = NEW.thread_id; END;");
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0130 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0141 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0152 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0163 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0174 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0185 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0196 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01a7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01b8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01c9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x01da A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:161:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0070 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0081 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0092 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00a3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00b4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00c5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00d6 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00e7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00ec A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00fd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x010e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x011f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0120  */
    @Override // android.database.sqlite.SQLiteOpenHelper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onUpgrade(android.database.sqlite.SQLiteDatabase r5, int r6, int r7) {
        /*
            Method dump skipped, instruction units count: 1050
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.MmsSmsDatabaseHelper.onUpgrade(android.database.sqlite.SQLiteDatabase, int, int):void");
    }

    private void dropAll(SQLiteDatabase sQLiteDatabase) {
        localLog("****DROPPING ALL SMS-MMS TABLES****");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS canonical_addresses");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS threads");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS pending_msgs");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sms");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS raw");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS attachments");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS thread_ids");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sr_pending");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS pdu;");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS addr;");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS part;");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS rate;");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS drm;");
    }

    private void upgradeDatabaseToVersion41(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS update_threads_error_on_move_mms");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_error_on_move_mms   BEFORE UPDATE OF msg_box ON pdu   WHEN (OLD.msg_box = 4 AND NEW.msg_box != 4)   AND (OLD._id IN (SELECT DISTINCT msg_id                   FROM pending_msgs                   WHERE err_type >= 10)) BEGIN   UPDATE threads SET error = error - 1  WHERE _id = OLD.thread_id; END;");
    }

    private void upgradeDatabaseToVersion42(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS sms_update_thread_on_delete");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS delete_obsolete_threads_sms");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS update_threads_error_on_delete_sms");
    }

    private void upgradeDatabaseToVersion43(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE threads ADD COLUMN has_attachment INTEGER DEFAULT 0");
        updateThreadsAttachmentColumn(sQLiteDatabase);
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_on_insert_part  AFTER INSERT ON part  WHEN new.ct != 'text/plain' AND new.ct != 'application/smil'  BEGIN   UPDATE threads SET has_attachment=1 WHERE _id IN    (SELECT pdu.thread_id FROM part JOIN pdu ON pdu._id=part.mid      WHERE part._id=new._id LIMIT 1);  END");
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_on_delete_part  AFTER DELETE ON part  WHEN old.ct != 'text/plain' AND old.ct != 'application/smil'  BEGIN   UPDATE threads SET has_attachment =    CASE     (SELECT COUNT(*) FROM part JOIN pdu      WHERE pdu.thread_id = threads._id      AND part.ct != 'text/plain' AND part.ct != 'application/smil'      AND part.mid = pdu._id)   WHEN 0 THEN 0    ELSE 1    END;  END");
    }

    private void upgradeDatabaseToVersion44(SQLiteDatabase sQLiteDatabase) {
        updateThreadsAttachmentColumn(sQLiteDatabase);
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_on_update_part  AFTER UPDATE of mid ON part  WHEN new.ct != 'text/plain' AND new.ct != 'application/smil'  BEGIN   UPDATE threads SET has_attachment=1 WHERE _id IN    (SELECT pdu.thread_id FROM part JOIN pdu ON pdu._id=part.mid      WHERE part._id=new._id LIMIT 1);  END");
    }

    private void upgradeDatabaseToVersion45(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE sms ADD COLUMN locked INTEGER DEFAULT 0");
        sQLiteDatabase.execSQL("ALTER TABLE pdu ADD COLUMN locked INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersion46(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE part ADD COLUMN text TEXT");
        Cursor cursorQuery = sQLiteDatabase.query("part", new String[]{"_id", "_data", "text"}, "ct = 'text/plain' OR ct == 'application/smil'", null, null, null, null);
        ArrayList<String> arrayList = new ArrayList();
        try {
            sQLiteDatabase.beginTransaction();
            if (cursorQuery != null) {
                int columnIndex = cursorQuery.getColumnIndex("_data");
                while (cursorQuery.moveToNext()) {
                    String string = cursorQuery.getString(columnIndex);
                    if (string != null) {
                        try {
                            FileInputStream fileInputStream = new FileInputStream(string);
                            byte[] bArr = new byte[fileInputStream.available()];
                            fileInputStream.read(bArr);
                            sQLiteDatabase.execSQL("UPDATE part SET _data = NULL, text = ?", new String[]{new EncodedStringValue(bArr).getString()});
                            fileInputStream.close();
                            arrayList.add(string);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
            for (String str : arrayList) {
                try {
                    new File(str).delete();
                } catch (SecurityException e2) {
                    Log.e("MmsSmsDatabaseHelper", "unable to clean up old mms file for " + str, e2);
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    private void upgradeDatabaseToVersion47(SQLiteDatabase sQLiteDatabase) {
        updateThreadsAttachmentColumn(sQLiteDatabase);
        sQLiteDatabase.execSQL("CREATE TRIGGER update_threads_on_update_pdu  AFTER UPDATE of thread_id ON pdu  BEGIN   UPDATE threads SET has_attachment=1 WHERE _id IN    (SELECT pdu.thread_id FROM part JOIN pdu      WHERE part.ct != 'text/plain' AND part.ct != 'application/smil'      AND part.mid = pdu._id); END");
    }

    private void upgradeDatabaseToVersion48(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE sms ADD COLUMN error_code INTEGER DEFAULT -1");
    }

    private void upgradeDatabaseToVersion51(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE sms add COLUMN seen INTEGER DEFAULT 0");
        sQLiteDatabase.execSQL("ALTER TABLE pdu add COLUMN seen INTEGER DEFAULT 0");
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("seen", (Integer) 1);
            Log.d("MmsSmsDatabaseHelper", "[MmsSmsDb] upgradeDatabaseToVersion51: updated " + sQLiteDatabase.update("sms", contentValues, "read=1", null) + " rows in sms table to have READ=1");
            Log.d("MmsSmsDatabaseHelper", "[MmsSmsDb] upgradeDatabaseToVersion51: updated " + sQLiteDatabase.update("pdu", contentValues, "read=1", null) + " rows in pdu table to have READ=1");
        } catch (Exception e) {
            Log.e("MmsSmsDatabaseHelper", "[MmsSmsDb] upgradeDatabaseToVersion51 caught ", e);
        }
    }

    private void upgradeDatabaseToVersion53(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS pdu_update_thread_read_on_update");
        sQLiteDatabase.execSQL("CREATE TRIGGER pdu_update_thread_read_on_update AFTER  UPDATE OF read  ON pdu   WHEN new.m_type=132    OR new.m_type=130    OR new.m_type=128 BEGIN   UPDATE threads SET read =     CASE (SELECT COUNT(*)          FROM pdu          WHERE read = 0            AND thread_id = threads._id             AND (m_type=132 OR m_type=130 OR m_type=128))       WHEN 0 THEN 1      ELSE 0    END  WHERE threads._id = new.thread_id; END;");
    }

    private void upgradeDatabaseToVersion54(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE sms ADD COLUMN date_sent INTEGER DEFAULT 0");
        sQLiteDatabase.execSQL("ALTER TABLE pdu ADD COLUMN date_sent INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersion55(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS delete_obsolete_threads_pdu");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS delete_obsolete_threads_when_update_pdu");
    }

    private void upgradeDatabaseToVersion56(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE pdu ADD COLUMN text_only INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersion57(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DELETE FROM pdu WHERE thread_id IS NULL");
    }

    private void upgradeDatabaseToVersion58(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE pdu ADD COLUMN sub_id INTEGER DEFAULT -1");
        sQLiteDatabase.execSQL("ALTER TABLE pending_msgs ADD COLUMN pending_sub_id INTEGER DEFAULT -1");
        sQLiteDatabase.execSQL("ALTER TABLE sms ADD COLUMN sub_id INTEGER DEFAULT -1");
        sQLiteDatabase.execSQL("ALTER TABLE raw ADD COLUMN sub_id INTEGER DEFAULT -1");
    }

    private void upgradeDatabaseToVersion59(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE pdu ADD COLUMN creator TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE sms ADD COLUMN creator TEXT");
    }

    private void upgradeDatabaseToVersion60(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE threads ADD COLUMN archived INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersion61(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE VIEW sms_restricted AS SELECT * FROM sms WHERE type=1 OR type=2;");
        sQLiteDatabase.execSQL("CREATE VIEW pdu_restricted  AS SELECT * FROM pdu WHERE (msg_box=1 OR msg_box=2) AND (m_type!=130);");
    }

    private void upgradeDatabaseToVersion62(SQLiteDatabase sQLiteDatabase) {
        try {
            String canonicalPath = this.mContext.getDir("parts", 0).getCanonicalPath();
            int iLastIndexOf = canonicalPath.lastIndexOf(File.separator, canonicalPath.lastIndexOf("parts"));
            String str = canonicalPath.substring(iLastIndexOf) + File.separator;
            sQLiteDatabase.execSQL("UPDATE part SET _data = '" + canonicalPath.substring(0, iLastIndexOf) + "' || SUBSTR(_data, INSTR(_data, '" + str + "')) WHERE INSTR(_data, '" + str + "') > 0");
        } catch (IOException e) {
            Log.e("MmsSmsDatabaseHelper", "openFile: check file path failed " + e, e);
        }
    }

    private void upgradeDatabaseToVersion64(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE raw ADD COLUMN deleted INTEGER DEFAULT 0");
    }

    private void upgradeDatabaseToVersion65(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE raw ADD COLUMN message_body TEXT");
        } catch (SQLiteException e) {
            Log.w("MmsSmsDatabaseHelper", "[upgradeDatabaseToVersion65] Exception adding column message_body; trying createThreadIdDateIndex() instead: " + e);
            createThreadIdDateIndex(sQLiteDatabase);
        }
    }

    private void upgradeDatabaseToVersion66(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("ALTER TABLE raw ADD COLUMN display_originating_addr TEXT");
        } catch (SQLiteException e) {
            Log.e("MmsSmsDatabaseHelper", "[upgradeDatabaseToVersion66] Exception adding column display_originating_addr; " + e);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public synchronized SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase writableDatabase;
        writableDatabase = super.getWritableDatabase();
        if (this.mContext.isCredentialProtectedStorage()) {
            setInitialCreateDone();
        }
        return writableDatabase;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase writableDatabase;
        boolean z;
        boolean z2;
        boolean z3;
        writableDatabase = super.getWritableDatabase();
        if (this.mContext.isCredentialProtectedStorage()) {
            setInitialCreateDone();
        }
        if (!sTriedAutoIncrement) {
            boolean z4 = true;
            sTriedAutoIncrement = true;
            boolean zHasAutoIncrement = hasAutoIncrement(writableDatabase, "threads");
            boolean zHasAutoIncrement2 = hasAutoIncrement(writableDatabase, "canonical_addresses");
            boolean zHasAutoIncrement3 = hasAutoIncrement(writableDatabase, "part");
            boolean zHasAutoIncrement4 = hasAutoIncrement(writableDatabase, "pdu");
            String str = "[getWritableDatabase] hasAutoIncrementThreads: " + zHasAutoIncrement + " hasAutoIncrementAddresses: " + zHasAutoIncrement2 + " hasAutoIncrementPart: " + zHasAutoIncrement3 + " hasAutoIncrementPdu: " + zHasAutoIncrement4;
            Log.d("MmsSmsDatabaseHelper", str);
            localLog(str);
            if (!zHasAutoIncrement) {
                writableDatabase.beginTransaction();
                try {
                    upgradeThreadsTableToAutoIncrement(writableDatabase);
                    writableDatabase.setTransactionSuccessful();
                } catch (Throwable th) {
                    try {
                        Log.e("MmsSmsDatabaseHelper", "Failed to add autoIncrement to threads;: " + th.getMessage(), th);
                        writableDatabase.endTransaction();
                        z = false;
                    } finally {
                    }
                }
            }
            z = true;
            if (!zHasAutoIncrement2) {
                writableDatabase.beginTransaction();
                try {
                    upgradeAddressTableToAutoIncrement(writableDatabase);
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                } catch (Throwable th2) {
                    try {
                        Log.e("MmsSmsDatabaseHelper", "Failed to add autoIncrement to canonical_addresses: " + th2.getMessage(), th2);
                        writableDatabase.endTransaction();
                        z2 = false;
                    } finally {
                    }
                }
            }
            z2 = true;
            if (!zHasAutoIncrement3) {
                writableDatabase.beginTransaction();
                try {
                    upgradePartTableToAutoIncrement(writableDatabase);
                    writableDatabase.setTransactionSuccessful();
                    writableDatabase.endTransaction();
                } catch (Throwable th3) {
                    try {
                        Log.e("MmsSmsDatabaseHelper", "Failed to add autoIncrement to part: " + th3.getMessage(), th3);
                        writableDatabase.endTransaction();
                        z3 = false;
                    } finally {
                    }
                }
            }
            z3 = true;
            if (!zHasAutoIncrement4) {
                writableDatabase.beginTransaction();
                try {
                    upgradePduTableToAutoIncrement(writableDatabase);
                    writableDatabase.setTransactionSuccessful();
                } catch (Throwable th4) {
                    try {
                        Log.e("MmsSmsDatabaseHelper", "Failed to add autoIncrement to pdu: " + th4.getMessage(), th4);
                        writableDatabase.endTransaction();
                        z4 = false;
                    } finally {
                    }
                }
            }
            if (z && z2 && z3 && z4) {
                if (this.mLowStorageMonitor != null) {
                    Log.d("MmsSmsDatabaseHelper", "Unregistering mLowStorageMonitor - we've upgraded");
                    this.mContext.unregisterReceiver(this.mLowStorageMonitor);
                    this.mLowStorageMonitor = null;
                }
            } else {
                if (sFakeLowStorageTest) {
                    sFakeLowStorageTest = false;
                }
                if (this.mLowStorageMonitor == null) {
                    Log.d("MmsSmsDatabaseHelper", "[getWritableDatabase] turning on storage monitor");
                    this.mLowStorageMonitor = new LowStorageMonitor(this);
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.intent.action.DEVICE_STORAGE_LOW");
                    intentFilter.addAction("android.intent.action.DEVICE_STORAGE_OK");
                    this.mContext.registerReceiver(this.mLowStorageMonitor, intentFilter);
                }
            }
        }
        return writableDatabase;
    }

    private boolean hasAutoIncrement(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("SELECT sql FROM sqlite_master WHERE type='table' AND name='" + str + "'", null);
        if (cursorRawQuery != null) {
            try {
                if (cursorRawQuery.moveToFirst()) {
                    String string = cursorRawQuery.getString(0);
                    zContains = string != null ? string.contains("AUTOINCREMENT") : false;
                    Log.d("MmsSmsDatabaseHelper", "[MmsSmsDb] tableName: " + str + " hasAutoIncrement: " + string + " result: " + zContains);
                }
            } finally {
                cursorRawQuery.close();
            }
        }
        return zContains;
    }

    private void upgradeThreadsTableToAutoIncrement(SQLiteDatabase sQLiteDatabase) {
        if (hasAutoIncrement(sQLiteDatabase, "threads")) {
            Log.d("MmsSmsDatabaseHelper", "[MmsSmsDb] upgradeThreadsTableToAutoIncrement: already upgraded");
            return;
        }
        Log.d("MmsSmsDatabaseHelper", "[MmsSmsDb] upgradeThreadsTableToAutoIncrement: upgrading");
        sQLiteDatabase.execSQL("CREATE TABLE threads_temp (_id INTEGER PRIMARY KEY AUTOINCREMENT,date INTEGER DEFAULT 0,message_count INTEGER DEFAULT 0,recipient_ids TEXT,snippet TEXT,snippet_cs INTEGER DEFAULT 0,read INTEGER DEFAULT 1,type INTEGER DEFAULT 0,error INTEGER DEFAULT 0,has_attachment INTEGER DEFAULT 0);");
        sQLiteDatabase.execSQL("INSERT INTO threads_temp SELECT * from threads;");
        sQLiteDatabase.execSQL("DROP TABLE threads;");
        sQLiteDatabase.execSQL("ALTER TABLE threads_temp RENAME TO threads;");
    }

    private void upgradeAddressTableToAutoIncrement(SQLiteDatabase sQLiteDatabase) {
        if (hasAutoIncrement(sQLiteDatabase, "canonical_addresses")) {
            Log.d("MmsSmsDatabaseHelper", "[MmsSmsDb] upgradeAddressTableToAutoIncrement: already upgraded");
            return;
        }
        Log.d("MmsSmsDatabaseHelper", "[MmsSmsDb] upgradeAddressTableToAutoIncrement: upgrading");
        sQLiteDatabase.execSQL("CREATE TABLE canonical_addresses_temp (_id INTEGER PRIMARY KEY AUTOINCREMENT,address TEXT);");
        sQLiteDatabase.execSQL("INSERT INTO canonical_addresses_temp SELECT * from canonical_addresses;");
        sQLiteDatabase.execSQL("DROP TABLE canonical_addresses;");
        sQLiteDatabase.execSQL("ALTER TABLE canonical_addresses_temp RENAME TO canonical_addresses;");
    }

    private void upgradePartTableToAutoIncrement(SQLiteDatabase sQLiteDatabase) {
        if (hasAutoIncrement(sQLiteDatabase, "part")) {
            Log.d("MmsSmsDatabaseHelper", "[MmsSmsDb] upgradePartTableToAutoIncrement: already upgraded");
            return;
        }
        Log.d("MmsSmsDatabaseHelper", "[MmsSmsDb] upgradePartTableToAutoIncrement: upgrading");
        sQLiteDatabase.execSQL("CREATE TABLE part_temp (_id INTEGER PRIMARY KEY AUTOINCREMENT,mid INTEGER,seq INTEGER DEFAULT 0,ct TEXT,name TEXT,chset INTEGER,cd TEXT,fn TEXT,cid TEXT,cl TEXT,ctt_s INTEGER,ctt_t TEXT,_data TEXT,text TEXT);");
        sQLiteDatabase.execSQL("INSERT INTO part_temp SELECT * from part;");
        sQLiteDatabase.execSQL("DROP TABLE part;");
        sQLiteDatabase.execSQL("ALTER TABLE part_temp RENAME TO part;");
        createMmsTriggers(sQLiteDatabase);
    }

    private void upgradePduTableToAutoIncrement(SQLiteDatabase sQLiteDatabase) {
        if (hasAutoIncrement(sQLiteDatabase, "pdu")) {
            Log.d("MmsSmsDatabaseHelper", "[MmsSmsDb] upgradePduTableToAutoIncrement: already upgraded");
            return;
        }
        Log.d("MmsSmsDatabaseHelper", "[MmsSmsDb] upgradePduTableToAutoIncrement: upgrading");
        sQLiteDatabase.execSQL("CREATE TABLE pdu_temp (_id INTEGER PRIMARY KEY AUTOINCREMENT,thread_id INTEGER,date INTEGER,date_sent INTEGER DEFAULT 0,msg_box INTEGER,read INTEGER DEFAULT 0,m_id TEXT,sub TEXT,sub_cs INTEGER,ct_t TEXT,ct_l TEXT,exp INTEGER,m_cls TEXT,m_type INTEGER,v INTEGER,m_size INTEGER,pri INTEGER,rr INTEGER,rpt_a INTEGER,resp_st INTEGER,st INTEGER,tr_id TEXT,retr_st INTEGER,retr_txt TEXT,retr_txt_cs INTEGER,read_status INTEGER,ct_cls INTEGER,resp_txt TEXT,d_tm INTEGER,d_rpt INTEGER,locked INTEGER DEFAULT 0,sub_id INTEGER DEFAULT -1, seen INTEGER DEFAULT 0,text_only INTEGER DEFAULT 0);");
        sQLiteDatabase.execSQL("INSERT INTO pdu_temp SELECT * from pdu;");
        sQLiteDatabase.execSQL("DROP TABLE pdu;");
        sQLiteDatabase.execSQL("ALTER TABLE pdu_temp RENAME TO pdu;");
        createMmsTriggers(sQLiteDatabase);
    }

    private class LowStorageMonitor extends BroadcastReceiver {
        public LowStorageMonitor(MmsSmsDatabaseHelper mmsSmsDatabaseHelper) {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("MmsSmsDatabaseHelper", "[LowStorageMonitor] onReceive intent " + action);
            if ("android.intent.action.DEVICE_STORAGE_OK".equals(action)) {
                boolean unused = MmsSmsDatabaseHelper.sTriedAutoIncrement = false;
            }
        }
    }

    private void updateThreadsAttachmentColumn(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("UPDATE threads SET has_attachment=1 WHERE _id IN   (SELECT DISTINCT pdu.thread_id FROM part    JOIN pdu ON pdu._id=part.mid    WHERE part.ct != 'text/plain' AND part.ct != 'application/smil')");
    }
}
