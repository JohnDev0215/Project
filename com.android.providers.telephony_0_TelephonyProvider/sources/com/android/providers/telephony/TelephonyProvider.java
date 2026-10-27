package com.android.providers.telephony;

import android.app.compat.CompatChanges;
import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.provider.Telephony;
import android.service.carrier.IApnSourceService;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.util.Pair;
import com.android.internal.annotations.GuardedBy;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.telephony.PhoneFactory;
import com.android.internal.util.XmlUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class TelephonyProvider extends ContentProvider {
    private static final Map<String, String> CARRIERS_UNIQUE_FIELDS_DEFAULTS;
    private static final Map<String, Integer> MVNO_TYPE_STRING_MAP;

    @VisibleForTesting
    static Boolean s_apnSourceServiceExists;
    private static final ContentValues s_currentNullMap;
    private static final ContentValues s_currentSetMap;

    @GuardedBy({"mLock"})
    private IApnSourceService mIApnSourceService;
    private Injector mInjector;
    protected final Object mLock;
    private boolean mManagedApnEnforced;
    private DatabaseHelper mOpenHelper;
    private static final UriMatcher s_urlMatcher = new UriMatcher(-1);
    private static final List<String> CARRIERS_UNIQUE_FIELDS = new ArrayList();
    private static final Set<String> CARRIERS_BOOLEAN_FIELDS = new HashSet();

    private static boolean bitmaskHasTarget(int i, int i2) {
        if (i == 0) {
            return true;
        }
        return (i2 == 0 || (i & i2) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getBitmaskForTech(int i) {
        if (i >= 1) {
            return 1 << (i - 1);
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int rilRadioTechnologyToNetworkTypeBitmask(int i) {
        switch (i) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
            case 5:
                return 8;
            case 6:
                return 64;
            case 7:
                return 16;
            case 8:
                return 32;
            case 9:
                return 128;
            case 10:
                return 256;
            case 11:
                return 512;
            case 12:
                return 2048;
            case 13:
                return 8192;
            case 14:
                return 4096;
            case 15:
                return 16384;
            case 16:
                return 32768;
            case 17:
                return 65536;
            case 18:
                return 131072;
            case 19:
                return 262144;
            case 20:
                return 524288;
            default:
                return 0;
        }
    }

    static {
        HashMap map = new HashMap();
        CARRIERS_UNIQUE_FIELDS_DEFAULTS = map;
        map.put("numeric", "");
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put("mcc", "");
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put("mnc", "");
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put("apn", "");
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put("proxy", "");
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put("port", "");
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put("mmsproxy", "");
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put("mmsport", "");
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put("mmsc", "");
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put("carrier_enabled", "1");
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put("bearer", "0");
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put("mvno_type", "");
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put("mvno_match_data", "");
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put("profile_id", "0");
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put("protocol", "IP");
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put("roaming_protocol", "IP");
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put("user_editable", "1");
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put("owned_by", String.valueOf(1));
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put("apn_set_id", String.valueOf(0));
        CARRIERS_UNIQUE_FIELDS_DEFAULTS.put(CarrierIdProvider.AUTHORITY, String.valueOf(-1));
        CARRIERS_UNIQUE_FIELDS.addAll(CARRIERS_UNIQUE_FIELDS_DEFAULTS.keySet());
        CARRIERS_BOOLEAN_FIELDS.add("carrier_enabled");
        CARRIERS_BOOLEAN_FIELDS.add("modem_cognitive");
        CARRIERS_BOOLEAN_FIELDS.add("user_visible");
        CARRIERS_BOOLEAN_FIELDS.add("user_editable");
        ArrayMap arrayMap = new ArrayMap();
        MVNO_TYPE_STRING_MAP = arrayMap;
        arrayMap.put("spn", 0);
        MVNO_TYPE_STRING_MAP.put("imsi", 1);
        MVNO_TYPE_STRING_MAP.put("gid", 2);
        MVNO_TYPE_STRING_MAP.put("iccid", 3);
        s_urlMatcher.addURI("telephony", "carriers", 1);
        s_urlMatcher.addURI("telephony", "carriers/current", 2);
        s_urlMatcher.addURI("telephony", "carriers/#", 3);
        s_urlMatcher.addURI("telephony", "carriers/restore", 4);
        s_urlMatcher.addURI("telephony", "carriers/preferapn", 5);
        s_urlMatcher.addURI("telephony", "carriers/preferapn_no_update", 6);
        s_urlMatcher.addURI("telephony", "carriers/preferapnset", 21);
        s_urlMatcher.addURI("telephony", "siminfo", 7);
        s_urlMatcher.addURI("telephony", "siminfo/#", 13);
        s_urlMatcher.addURI("telephony", "carriers/subId/*", 8);
        s_urlMatcher.addURI("telephony", "carriers/current/subId/*", 9);
        s_urlMatcher.addURI("telephony", "carriers/restore/subId/*", 10);
        s_urlMatcher.addURI("telephony", "carriers/preferapn/subId/*", 11);
        s_urlMatcher.addURI("telephony", "carriers/preferapn_no_update/subId/*", 12);
        s_urlMatcher.addURI("telephony", "carriers/preferapnset/subId/*", 22);
        s_urlMatcher.addURI("telephony", "carriers/update_db", 14);
        s_urlMatcher.addURI("telephony", "carriers/delete", 15);
        s_urlMatcher.addURI("telephony", "carriers/dpc", 16);
        s_urlMatcher.addURI("telephony", "carriers/dpc/#", 17);
        s_urlMatcher.addURI("telephony", "carriers/filtered", 18);
        s_urlMatcher.addURI("telephony", "carriers/filtered/#", 19);
        s_urlMatcher.addURI("telephony", "carriers/filtered/subId/*", 25);
        s_urlMatcher.addURI("telephony", "carriers/enforce_managed", 20);
        s_urlMatcher.addURI("telephony", "carriers/sim_apn_list", 23);
        s_urlMatcher.addURI("telephony", "carriers/sim_apn_list/#", 24);
        s_urlMatcher.addURI("telephony", "carriers/sim_apn_list/filtered", 26);
        s_urlMatcher.addURI("telephony", "carriers/sim_apn_list/filtered/subId/*", 27);
        ContentValues contentValues = new ContentValues(1);
        s_currentNullMap = contentValues;
        contentValues.put("current", "0");
        ContentValues contentValues2 = new ContentValues(1);
        s_currentSetMap = contentValues2;
        contentValues2.put("current", "1");
    }

    @VisibleForTesting
    public static String getStringForCarrierTableCreation(String str) {
        return "CREATE TABLE " + str + "(_id INTEGER PRIMARY KEY,name TEXT DEFAULT '',numeric TEXT DEFAULT '',mcc TEXT DEFAULT '',mnc TEXT DEFAULT ''," + CarrierIdProvider.AUTHORITY + " INTEGER DEFAULT -1,apn TEXT DEFAULT '',user TEXT DEFAULT '',server TEXT DEFAULT '',password TEXT DEFAULT '',proxy TEXT DEFAULT '',port TEXT DEFAULT '',mmsproxy TEXT DEFAULT '',mmsport TEXT DEFAULT '',mmsc TEXT DEFAULT '',authtype INTEGER DEFAULT -1,type TEXT DEFAULT '',current INTEGER,protocol TEXT DEFAULT IP,roaming_protocol TEXT DEFAULT IP,carrier_enabled BOOLEAN DEFAULT 1,bearer INTEGER DEFAULT 0,bearer_bitmask INTEGER DEFAULT 0,network_type_bitmask INTEGER DEFAULT 0,mvno_type TEXT DEFAULT '',mvno_match_data TEXT DEFAULT '',sub_id INTEGER DEFAULT -1,profile_id INTEGER DEFAULT 0,modem_cognitive BOOLEAN DEFAULT 0,max_conns INTEGER DEFAULT 0,wait_time INTEGER DEFAULT 0,max_conns_time INTEGER DEFAULT 0,mtu INTEGER DEFAULT 0,edited INTEGER DEFAULT 0,user_visible BOOLEAN DEFAULT 1,user_editable BOOLEAN DEFAULT 1,owned_by INTEGER DEFAULT 1,apn_set_id INTEGER DEFAULT 0,skip_464xlat INTEGER DEFAULT -1,UNIQUE (" + TextUtils.join(", ", CARRIERS_UNIQUE_FIELDS) + "));";
    }

    @VisibleForTesting
    public static String getStringForSimInfoTableCreation(String str) {
        return "CREATE TABLE " + str + "(_id INTEGER PRIMARY KEY AUTOINCREMENT,icc_id TEXT NOT NULL,sim_id INTEGER DEFAULT -1,display_name TEXT,carrier_name TEXT,name_source INTEGER DEFAULT 0,color INTEGER DEFAULT 0,number TEXT,display_number_format INTEGER NOT NULL DEFAULT 1,data_roaming INTEGER DEFAULT 0,mcc INTEGER DEFAULT 0,mnc INTEGER DEFAULT 0,mcc_string TEXT,mnc_string TEXT,ehplmns TEXT,hplmns TEXT,sim_provisioning_status INTEGER DEFAULT 0,is_embedded INTEGER DEFAULT 0,card_id TEXT NOT NULL,access_rules BLOB,access_rules_from_carrier_configs BLOB,is_removable INTEGER DEFAULT 0,enable_cmas_extreme_threat_alerts INTEGER DEFAULT 1,enable_cmas_severe_threat_alerts INTEGER DEFAULT 1,enable_cmas_amber_alerts INTEGER DEFAULT 1,enable_emergency_alerts INTEGER DEFAULT 1,alert_sound_duration INTEGER DEFAULT 4,alert_reminder_interval INTEGER DEFAULT 0,enable_alert_vibrate INTEGER DEFAULT 1,enable_alert_speech INTEGER DEFAULT 1,enable_etws_test_alerts INTEGER DEFAULT 0,enable_channel_50_alerts INTEGER DEFAULT 1,enable_cmas_test_alerts INTEGER DEFAULT 0,show_cmas_opt_out_dialog INTEGER DEFAULT 1,volte_vt_enabled INTEGER DEFAULT -1,vt_ims_enabled INTEGER DEFAULT -1,wfc_ims_enabled INTEGER DEFAULT -1,wfc_ims_mode INTEGER DEFAULT -1,wfc_ims_roaming_mode INTEGER DEFAULT -1,wfc_ims_roaming_enabled INTEGER DEFAULT -1,is_opportunistic INTEGER DEFAULT 0,group_uuid TEXT,is_metered INTEGER DEFAULT 1,iso_country_code TEXT," + CarrierIdProvider.AUTHORITY + " INTEGER DEFAULT -1,profile_class INTEGER DEFAULT -1,subscription_type INTEGER DEFAULT 0,group_owner TEXT,data_enabled_override_rules TEXT,imsi TEXT,uicc_applications_enabled INTEGER DEFAULT 1,allowed_network_types BIGINT DEFAULT -1,ims_rcs_uce_enabled INTEGER DEFAULT 0);";
    }

    @VisibleForTesting
    static class Injector {
        Injector() {
        }

        int binderGetCallingUid() {
            return Binder.getCallingUid();
        }
    }

    public TelephonyProvider() {
        this(new Injector());
    }

    @VisibleForTesting
    public TelephonyProvider(Injector injector) {
        this.mLock = new Object();
        this.mInjector = injector;
    }

    @VisibleForTesting
    public static int getVersion(Context context) {
        Resources resources = context.getResources();
        if (resources == null) {
            loge("resources=null, return version=" + Integer.toHexString(2949120));
            return 2949120;
        }
        XmlResourceParser xml = resources.getXml(android.R.xml.apns);
        try {
            XmlUtils.beginDocument(xml, "apns");
            return 2949120 | Integer.parseInt(xml.getAttributeValue(null, "version"));
        } catch (Exception e) {
            loge("Can't get version of APN database" + e + " return version=" + Integer.toHexString(2949120));
            return 2949120;
        } finally {
            xml.close();
        }
    }

    public static ContentValues setDefaultValue(ContentValues contentValues) {
        if (!contentValues.containsKey("sub_id")) {
            contentValues.put("sub_id", Integer.valueOf(SubscriptionManager.getDefaultSubscriptionId()));
        }
        return contentValues;
    }

    @VisibleForTesting
    public class DatabaseHelper extends SQLiteOpenHelper {
        private Context mContext;

        public DatabaseHelper(Context context) {
            super(context, "telephony.db", (SQLiteDatabase.CursorFactory) null, TelephonyProvider.getVersion(context));
            this.mContext = context;
            setIdleConnectionTimeout(30000L);
            setWriteAheadLoggingEnabled(false);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) throws Throwable {
            TelephonyProvider.log("dbh.onCreate:+ db=" + sQLiteDatabase);
            createSimInfoTable(sQLiteDatabase, "siminfo");
            createCarriersTable(sQLiteDatabase, "carriers");
            if (TelephonyProvider.apnSourceServiceExists(this.mContext)) {
                TelephonyProvider.log("dbh.onCreate: Skipping apply APNs from xml.");
            } else {
                TelephonyProvider.log("dbh.onCreate: Apply apns from xml.");
                initDatabase(sQLiteDatabase);
            }
            TelephonyProvider.log("dbh.onCreate:- db=" + sQLiteDatabase);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onOpen(SQLiteDatabase sQLiteDatabase) {
            try {
                sQLiteDatabase.query("siminfo", null, null, null, null, null, null);
                TelephonyProvider.log("dbh.onOpen: ok, queried table=siminfo");
            } catch (SQLiteException e) {
                TelephonyProvider.loge("Exception siminfoe=" + e);
                if (e.getMessage().startsWith("no such table")) {
                    createSimInfoTable(sQLiteDatabase, "siminfo");
                }
            }
            try {
                sQLiteDatabase.query("carriers", null, null, null, null, null, null);
                TelephonyProvider.log("dbh.onOpen: ok, queried table=carriers");
            } catch (SQLiteException e2) {
                TelephonyProvider.loge("Exception carriers e=" + e2);
                if (e2.getMessage().startsWith("no such table")) {
                    createCarriersTable(sQLiteDatabase, "carriers");
                }
            }
        }

        private void createSimInfoTable(SQLiteDatabase sQLiteDatabase, String str) {
            TelephonyProvider.log("dbh.createSimInfoTable:+ " + str);
            sQLiteDatabase.execSQL(TelephonyProvider.getStringForSimInfoTableCreation(str));
            TelephonyProvider.log("dbh.createSimInfoTable:-");
        }

        private void createCarriersTable(SQLiteDatabase sQLiteDatabase, String str) {
            TelephonyProvider.log("dbh.createCarriersTable: " + str);
            sQLiteDatabase.execSQL(TelephonyProvider.getStringForCarrierTableCreation(str));
            TelephonyProvider.log("dbh.createCarriersTable:-");
        }

        private long getChecksum(File file) {
            InputStream inputStreamOpenRawResource;
            CheckedInputStream checkedInputStream;
            CRC32 crc32 = new CRC32();
            long value = -1;
            try {
                checkedInputStream = new CheckedInputStream(new FileInputStream(file), crc32);
            } catch (FileNotFoundException e) {
                TelephonyProvider.loge("FileNotFoundException for " + file.getAbsolutePath() + ":" + e);
            } catch (IOException e2) {
                TelephonyProvider.loge("IOException for " + file.getAbsolutePath() + ":" + e2);
            }
            try {
                while (checkedInputStream.read(new byte[128]) >= 0) {
                }
                value = crc32.getValue();
                TelephonyProvider.log("Checksum for " + file.getAbsolutePath() + " is " + value);
                checkedInputStream.close();
                try {
                    inputStreamOpenRawResource = this.mContext.getResources().openRawResource(android.R.xml.apns);
                } catch (Resources.NotFoundException | IOException e3) {
                    TelephonyProvider.loge("Exception when calculating checksum for internal apn resources: " + e3);
                }
                try {
                    byte[] byteArray = toByteArray(inputStreamOpenRawResource);
                    crc32.reset();
                    crc32.update(byteArray);
                    value += crc32.getValue();
                    TelephonyProvider.log("Checksum after adding resource is " + crc32.getValue());
                    if (inputStreamOpenRawResource != null) {
                        inputStreamOpenRawResource.close();
                    }
                    return value;
                } catch (Throwable th) {
                    if (inputStreamOpenRawResource != null) {
                        try {
                            inputStreamOpenRawResource.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                try {
                    checkedInputStream.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
                throw th3;
            }
        }

        private byte[] toByteArray(InputStream inputStream) throws IOException {
            byte[] bArr = new byte[128];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int i = inputStream.read(bArr);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    return byteArrayOutputStream.toByteArray();
                }
            }
        }

        private long getApnConfChecksum() {
            return this.mContext.getSharedPreferences("telephonyprovider", 0).getLong("apn_conf_checksum", -1L);
        }

        private void setApnConfChecksum(long j) {
            SharedPreferences.Editor editorEdit = this.mContext.getSharedPreferences("telephonyprovider", 0).edit();
            editorEdit.putLong("apn_conf_checksum", j);
            editorEdit.apply();
        }

        private File getApnConfFile() {
            File file = new File(Environment.getRootDirectory(), "etc/apns-conf.xml");
            File file2 = new File(Environment.getOemDirectory(), "telephony/apns-conf.xml");
            return pickSecondIfExists(pickSecondIfExists(pickSecondIfExists(file, file2), new File(Environment.getProductDirectory(), "etc/apns-conf.xml")), new File(Environment.getDataDirectory(), "misc/apns/apns-conf.xml"));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean apnDbUpdateNeeded() {
            long checksum = getChecksum(getApnConfFile());
            long apnConfChecksum = getApnConfChecksum();
            TelephonyProvider.log("newChecksum: " + checksum);
            TelephonyProvider.log("oldChecksum: " + apnConfChecksum);
            return checksum != apnConfChecksum;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:54:0x0139 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r12v1 */
        /* JADX WARN: Type inference failed for: r12v9, types: [java.lang.String] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void initDatabase(android.database.sqlite.SQLiteDatabase r15) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 371
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.TelephonyProvider.DatabaseHelper.initDatabase(android.database.sqlite.SQLiteDatabase):void");
        }

        private File pickSecondIfExists(File file, File file2) {
            if (file2.exists()) {
                TelephonyProvider.log("Load APNs from " + file2.getPath() + " instead of " + file.getPath());
                return file2;
            }
            TelephonyProvider.log("Load APNs from " + file.getPath() + " instead of " + file2.getPath());
            return file;
        }

        /* JADX WARN: Removed duplicated region for block: B:54:0x0170  */
        @Override // android.database.sqlite.SQLiteOpenHelper
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onUpgrade(android.database.sqlite.SQLiteDatabase r19, int r20, int r21) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 961
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.TelephonyProvider.DatabaseHelper.onUpgrade(android.database.sqlite.SQLiteDatabase, int, int):void");
        }

        private void recreateSimInfoDB(Cursor cursor, SQLiteDatabase sQLiteDatabase, String[] strArr) {
            Cursor cursorQuery = sQLiteDatabase.query("siminfo", null, null, null, null, null, "_id ASC");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS siminfo_tmp");
            createSimInfoTable(sQLiteDatabase, "siminfo_tmp");
            copySimInfoDataToTmpTable(sQLiteDatabase, cursorQuery);
            cursorQuery.close();
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS siminfo");
            sQLiteDatabase.execSQL("ALTER TABLE siminfo_tmp rename to siminfo;");
        }

        private void copySimInfoDataToTmpTable(SQLiteDatabase sQLiteDatabase, Cursor cursor) {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    ContentValues contentValues = new ContentValues();
                    copySimInfoValuesV24(contentValues, cursor);
                    getCardIdfromIccid(contentValues, cursor);
                    try {
                        sQLiteDatabase.insert("siminfo_tmp", null, contentValues);
                    } catch (SQLException unused) {
                    }
                }
            }
        }

        private void copySimInfoValuesV24(ContentValues contentValues, Cursor cursor) {
            getStringValueFromCursor(contentValues, cursor, "icc_id");
            getStringValueFromCursor(contentValues, cursor, "display_name");
            getStringValueFromCursor(contentValues, cursor, "carrier_name");
            getStringValueFromCursor(contentValues, cursor, "number");
            getIntValueFromCursor(contentValues, cursor, "sim_id");
            getIntValueFromCursor(contentValues, cursor, "name_source");
            getIntValueFromCursor(contentValues, cursor, "color");
            getIntValueFromCursor(contentValues, cursor, "display_number_format");
            getIntValueFromCursor(contentValues, cursor, "data_roaming");
            getIntValueFromCursor(contentValues, cursor, "mcc");
            getIntValueFromCursor(contentValues, cursor, "mnc");
            getIntValueFromCursor(contentValues, cursor, "sim_provisioning_status");
            getIntValueFromCursor(contentValues, cursor, "is_embedded");
            getIntValueFromCursor(contentValues, cursor, "is_removable");
            getIntValueFromCursor(contentValues, cursor, "enable_cmas_extreme_threat_alerts");
            getIntValueFromCursor(contentValues, cursor, "enable_cmas_severe_threat_alerts");
            getIntValueFromCursor(contentValues, cursor, "enable_cmas_amber_alerts");
            getIntValueFromCursor(contentValues, cursor, "enable_emergency_alerts");
            getIntValueFromCursor(contentValues, cursor, "alert_sound_duration");
            getIntValueFromCursor(contentValues, cursor, "alert_reminder_interval");
            getIntValueFromCursor(contentValues, cursor, "enable_alert_vibrate");
            getIntValueFromCursor(contentValues, cursor, "enable_alert_speech");
            getIntValueFromCursor(contentValues, cursor, "enable_etws_test_alerts");
            getIntValueFromCursor(contentValues, cursor, "enable_channel_50_alerts");
            getIntValueFromCursor(contentValues, cursor, "enable_cmas_test_alerts");
            getIntValueFromCursor(contentValues, cursor, "show_cmas_opt_out_dialog");
            getIntValueFromCursor(contentValues, cursor, "volte_vt_enabled");
            getIntValueFromCursor(contentValues, cursor, "vt_ims_enabled");
            getIntValueFromCursor(contentValues, cursor, "wfc_ims_enabled");
            getIntValueFromCursor(contentValues, cursor, "wfc_ims_mode");
            getIntValueFromCursor(contentValues, cursor, "wfc_ims_roaming_mode");
            getIntValueFromCursor(contentValues, cursor, "wfc_ims_roaming_enabled");
            getBlobValueFromCursor(contentValues, cursor, "access_rules");
        }

        private void getCardIdfromIccid(ContentValues contentValues, Cursor cursor) {
            int columnIndex = cursor.getColumnIndex("icc_id");
            if (columnIndex != -1) {
                String string = cursor.getString(columnIndex);
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                contentValues.put("card_id", string);
            }
        }

        private void recreateDB(SQLiteDatabase sQLiteDatabase, String[] strArr, int i) {
            Cursor cursorQuery = sQLiteDatabase.query("carriers", null, null, null, null, null, null);
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS carriers_tmp");
            createCarriersTable(sQLiteDatabase, "carriers_tmp");
            copyDataToTmpTable(sQLiteDatabase, cursorQuery, i);
            cursorQuery.close();
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS carriers");
            sQLiteDatabase.execSQL("ALTER TABLE carriers_tmp rename to carriers;");
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x004f, code lost:
        
            if (r1 == 0) goto L34;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x0051, code lost:
        
            r1.close();
            r1 = r1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x005a, code lost:
        
            if (r1 == 0) goto L34;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v1 */
        /* JADX WARN: Type inference failed for: r1v10 */
        /* JADX WARN: Type inference failed for: r1v11 */
        /* JADX WARN: Type inference failed for: r1v13 */
        /* JADX WARN: Type inference failed for: r1v14 */
        /* JADX WARN: Type inference failed for: r1v15 */
        /* JADX WARN: Type inference failed for: r1v16 */
        /* JADX WARN: Type inference failed for: r1v17 */
        /* JADX WARN: Type inference failed for: r1v18 */
        /* JADX WARN: Type inference failed for: r1v2 */
        /* JADX WARN: Type inference failed for: r1v3, types: [java.io.FileReader] */
        /* JADX WARN: Type inference failed for: r1v4 */
        /* JADX WARN: Type inference failed for: r1v5, types: [java.io.FileReader] */
        /* JADX WARN: Type inference failed for: r1v6 */
        /* JADX WARN: Type inference failed for: r1v7 */
        /* JADX WARN: Type inference failed for: r1v8 */
        /* JADX WARN: Type inference failed for: r1v9 */
        /* JADX WARN: Type inference failed for: r4v0, types: [com.android.providers.telephony.TelephonyProvider$DatabaseHelper] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void preserveUserAndCarrierApns(android.database.sqlite.SQLiteDatabase r5) throws java.lang.Throwable {
            /*
                r4 = this;
                java.io.File r0 = new java.io.File
                java.io.File r1 = android.os.Environment.getRootDirectory()
                java.lang.String r2 = "etc/old-apns-conf.xml"
                r0.<init>(r1, r2)
                r1 = 0
                java.io.FileReader r2 = new java.io.FileReader     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e java.io.FileNotFoundException -> L55
                r2.<init>(r0)     // Catch: java.lang.Throwable -> L2c java.lang.Exception -> L2e java.io.FileNotFoundException -> L55
                org.xmlpull.v1.XmlPullParser r1 = android.util.Xml.newPullParser()     // Catch: java.lang.Throwable -> L24 java.lang.Exception -> L27 java.io.FileNotFoundException -> L2a
                r1.setInput(r2)     // Catch: java.lang.Throwable -> L24 java.lang.Exception -> L27 java.io.FileNotFoundException -> L2a
                java.lang.String r3 = "apns"
                com.android.internal.util.XmlUtils.beginDocument(r1, r3)     // Catch: java.lang.Throwable -> L24 java.lang.Exception -> L27 java.io.FileNotFoundException -> L2a
                r4.deleteMatchingApns(r5, r1)     // Catch: java.lang.Throwable -> L24 java.lang.Exception -> L27 java.io.FileNotFoundException -> L2a
                r2.close()     // Catch: java.io.IOException -> L5d
                goto L5d
            L24:
                r4 = move-exception
                r1 = r2
                goto L5e
            L27:
                r4 = move-exception
                r1 = r2
                goto L2f
            L2a:
                r1 = r2
                goto L55
            L2c:
                r4 = move-exception
                goto L5e
            L2e:
                r4 = move-exception
            L2f:
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2c
                r5.<init>()     // Catch: java.lang.Throwable -> L2c
                java.lang.String r2 = "preserveUserAndCarrierApns: Exception while parsing '"
                r5.append(r2)     // Catch: java.lang.Throwable -> L2c
                java.lang.String r0 = r0.getAbsolutePath()     // Catch: java.lang.Throwable -> L2c
                r5.append(r0)     // Catch: java.lang.Throwable -> L2c
                java.lang.String r0 = "'"
                r5.append(r0)     // Catch: java.lang.Throwable -> L2c
                r5.append(r4)     // Catch: java.lang.Throwable -> L2c
                java.lang.String r4 = r5.toString()     // Catch: java.lang.Throwable -> L2c
                com.android.providers.telephony.TelephonyProvider.access$200(r4)     // Catch: java.lang.Throwable -> L2c
                if (r1 == 0) goto L5d
            L51:
                r1.close()     // Catch: java.io.IOException -> L5d
                goto L5d
            L55:
                java.lang.String r4 = "PRESERVEUSERANDCARRIERAPNS: etc/old-apns-conf.xml NOT FOUND. IT IS NEEDED TO UPGRADE FROM OLDER VERSIONS OF APN DB WHILE PRESERVING USER/CARRIER ADDED/EDITED ENTRIES."
                com.android.providers.telephony.TelephonyProvider.access$200(r4)     // Catch: java.lang.Throwable -> L2c
                if (r1 == 0) goto L5d
                goto L51
            L5d:
                return
            L5e:
                if (r1 == 0) goto L63
                r1.close()     // Catch: java.io.IOException -> L63
            L63:
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.TelephonyProvider.DatabaseHelper.preserveUserAndCarrierApns(android.database.sqlite.SQLiteDatabase):void");
        }

        private void deleteMatchingApns(SQLiteDatabase sQLiteDatabase, XmlPullParser xmlPullParser) {
            if (xmlPullParser != null) {
                try {
                    XmlUtils.nextElement(xmlPullParser);
                    while (xmlPullParser.getEventType() != 1) {
                        ContentValues row = getRow(xmlPullParser);
                        if (row == null) {
                            throw new XmlPullParserException("Expected 'apn' tag", xmlPullParser, null);
                        }
                        deleteRow(sQLiteDatabase, row);
                        XmlUtils.nextElement(xmlPullParser);
                    }
                } catch (SQLException e) {
                    TelephonyProvider.loge("deleteMatchingApns: Got SQLException while deleting apns." + e);
                } catch (IOException e2) {
                    TelephonyProvider.loge("deleteMatchingApns: Got IOException while deleting apns." + e2);
                } catch (XmlPullParserException e3) {
                    TelephonyProvider.loge("deleteMatchingApns: Got XmlPullParserException while deleting apns." + e3);
                }
            }
        }

        private String queryValFirst(String str) {
            return str + "=?";
        }

        private String queryVal(String str) {
            return " and " + str + "=?";
        }

        private String queryValOrNull(String str) {
            return " and (" + str + "=? or " + str + " is null)";
        }

        private String queryVal2OrNull(String str) {
            return " and (" + str + "=? or " + str + "=? or " + str + " is null)";
        }

        private void deleteRow(SQLiteDatabase sQLiteDatabase, ContentValues contentValues) {
            String str = queryValFirst("numeric") + queryVal("mnc") + queryVal("mnc") + queryValOrNull("apn") + queryValOrNull("user") + queryValOrNull("server") + queryValOrNull("password") + queryValOrNull("proxy") + queryValOrNull("port") + queryValOrNull("mmsproxy") + queryValOrNull("mmsport") + queryValOrNull("mmsc") + queryValOrNull("authtype") + queryValOrNull("type") + queryValOrNull("protocol") + queryValOrNull("roaming_protocol") + queryVal2OrNull("carrier_enabled") + queryValOrNull("bearer") + queryValOrNull("mvno_type") + queryValOrNull("mvno_match_data") + queryValOrNull("profile_id") + queryVal2OrNull("modem_cognitive") + queryValOrNull("max_conns") + queryValOrNull("wait_time") + queryValOrNull("max_conns_time") + queryValOrNull("mtu");
            String[] strArr = new String[29];
            strArr[0] = contentValues.getAsString("numeric");
            strArr[1] = contentValues.getAsString("mcc");
            strArr[2] = contentValues.getAsString("mnc");
            strArr[3] = contentValues.getAsString("name");
            strArr[4] = contentValues.containsKey("apn") ? contentValues.getAsString("apn") : "";
            strArr[5] = contentValues.containsKey("user") ? contentValues.getAsString("user") : "";
            strArr[6] = contentValues.containsKey("server") ? contentValues.getAsString("server") : "";
            strArr[7] = contentValues.containsKey("password") ? contentValues.getAsString("password") : "";
            strArr[8] = contentValues.containsKey("proxy") ? contentValues.getAsString("proxy") : "";
            strArr[9] = contentValues.containsKey("port") ? contentValues.getAsString("port") : "";
            strArr[10] = contentValues.containsKey("mmsproxy") ? contentValues.getAsString("mmsproxy") : "";
            strArr[11] = contentValues.containsKey("mmsport") ? contentValues.getAsString("mmsport") : "";
            strArr[12] = contentValues.containsKey("mmsc") ? contentValues.getAsString("mmsc") : "";
            strArr[13] = contentValues.containsKey("authtype") ? contentValues.getAsString("authtype") : "-1";
            strArr[14] = contentValues.containsKey("type") ? contentValues.getAsString("type") : "";
            strArr[15] = contentValues.containsKey("protocol") ? contentValues.getAsString("protocol") : "IP";
            strArr[16] = contentValues.containsKey("roaming_protocol") ? contentValues.getAsString("roaming_protocol") : "IP";
            if (contentValues.containsKey("carrier_enabled")) {
                strArr[17] = TelephonyProvider.convertStringToBoolString(contentValues.getAsString("carrier_enabled"));
                strArr[18] = TelephonyProvider.convertStringToIntString(contentValues.getAsString("carrier_enabled"));
            } else {
                String str2 = (String) TelephonyProvider.CARRIERS_UNIQUE_FIELDS_DEFAULTS.get("carrier_enabled");
                strArr[17] = TelephonyProvider.convertStringToBoolString(str2);
                strArr[18] = str2;
            }
            strArr[19] = contentValues.containsKey("bearer") ? contentValues.getAsString("bearer") : "0";
            strArr[20] = contentValues.containsKey("mvno_type") ? contentValues.getAsString("mvno_type") : "";
            strArr[21] = contentValues.containsKey("mvno_match_data") ? contentValues.getAsString("mvno_match_data") : "";
            strArr[22] = contentValues.containsKey("profile_id") ? contentValues.getAsString("profile_id") : "0";
            if (contentValues.containsKey("modem_cognitive") && (contentValues.getAsString("modem_cognitive").equalsIgnoreCase("true") || contentValues.getAsString("modem_cognitive").equals("1"))) {
                strArr[23] = "true";
                strArr[24] = "1";
            } else {
                strArr[23] = "false";
                strArr[24] = "0";
            }
            strArr[25] = contentValues.containsKey("max_conns") ? contentValues.getAsString("max_conns") : "0";
            strArr[26] = contentValues.containsKey("wait_time") ? contentValues.getAsString("wait_time") : "0";
            strArr[27] = contentValues.containsKey("max_conns_time") ? contentValues.getAsString("max_conns_time") : "0";
            strArr[28] = contentValues.containsKey("mtu") ? contentValues.getAsString("mtu") : "0";
            sQLiteDatabase.delete("carriers", str, strArr);
        }

        private void copyDataToTmpTable(SQLiteDatabase sQLiteDatabase, Cursor cursor, int i) {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    ContentValues contentValues = new ContentValues();
                    copyAllApnValues(contentValues, cursor);
                    if (i == 24) {
                        getNetworkTypeBitmaskFromCursor(contentValues, cursor);
                    }
                    try {
                        sQLiteDatabase.insertWithOnConflict("carriers_tmp", null, contentValues, 2);
                    } catch (SQLException unused) {
                    }
                }
            }
        }

        private void copyApnValuesV17(ContentValues contentValues, Cursor cursor) {
            getStringValueFromCursor(contentValues, cursor, "name");
            getStringValueFromCursor(contentValues, cursor, "numeric");
            getStringValueFromCursor(contentValues, cursor, "mcc");
            getStringValueFromCursor(contentValues, cursor, "mnc");
            getStringValueFromCursor(contentValues, cursor, "apn");
            getStringValueFromCursor(contentValues, cursor, "user");
            getStringValueFromCursor(contentValues, cursor, "server");
            getStringValueFromCursor(contentValues, cursor, "password");
            getStringValueFromCursor(contentValues, cursor, "proxy");
            getStringValueFromCursor(contentValues, cursor, "port");
            getStringValueFromCursor(contentValues, cursor, "mmsproxy");
            getStringValueFromCursor(contentValues, cursor, "mmsport");
            getStringValueFromCursor(contentValues, cursor, "mmsc");
            getStringValueFromCursor(contentValues, cursor, "type");
            getStringValueFromCursor(contentValues, cursor, "protocol");
            getStringValueFromCursor(contentValues, cursor, "roaming_protocol");
            getStringValueFromCursor(contentValues, cursor, "mvno_type");
            getStringValueFromCursor(contentValues, cursor, "mvno_match_data");
            getIntValueFromCursor(contentValues, cursor, "authtype");
            getIntValueFromCursor(contentValues, cursor, "current");
            getIntValueFromCursor(contentValues, cursor, "carrier_enabled");
            getIntValueFromCursor(contentValues, cursor, "bearer");
            getIntValueFromCursor(contentValues, cursor, "sub_id");
            getIntValueFromCursor(contentValues, cursor, "profile_id");
            getIntValueFromCursor(contentValues, cursor, "modem_cognitive");
            getIntValueFromCursor(contentValues, cursor, "max_conns");
            getIntValueFromCursor(contentValues, cursor, "wait_time");
            getIntValueFromCursor(contentValues, cursor, "max_conns_time");
            getIntValueFromCursor(contentValues, cursor, "mtu");
            getIntValueFromCursor(contentValues, cursor, "bearer_bitmask");
            getIntValueFromCursor(contentValues, cursor, "edited");
            getIntValueFromCursor(contentValues, cursor, "user_visible");
        }

        private void copyAllApnValues(ContentValues contentValues, Cursor cursor) {
            getStringValueFromCursor(contentValues, cursor, "name");
            getStringValueFromCursor(contentValues, cursor, "numeric");
            getStringValueFromCursor(contentValues, cursor, "mcc");
            getStringValueFromCursor(contentValues, cursor, "mnc");
            getStringValueFromCursor(contentValues, cursor, "apn");
            getStringValueFromCursor(contentValues, cursor, "user");
            getStringValueFromCursor(contentValues, cursor, "server");
            getStringValueFromCursor(contentValues, cursor, "password");
            getStringValueFromCursor(contentValues, cursor, "proxy");
            getStringValueFromCursor(contentValues, cursor, "port");
            getStringValueFromCursor(contentValues, cursor, "mmsproxy");
            getStringValueFromCursor(contentValues, cursor, "mmsport");
            getStringValueFromCursor(contentValues, cursor, "mmsc");
            getStringValueFromCursor(contentValues, cursor, "type");
            getStringValueFromCursor(contentValues, cursor, "protocol");
            getStringValueFromCursor(contentValues, cursor, "roaming_protocol");
            getStringValueFromCursor(contentValues, cursor, "mvno_type");
            getStringValueFromCursor(contentValues, cursor, "mvno_match_data");
            getIntValueFromCursor(contentValues, cursor, "authtype");
            getIntValueFromCursor(contentValues, cursor, "current");
            getIntValueFromCursor(contentValues, cursor, "carrier_enabled");
            getIntValueFromCursor(contentValues, cursor, "bearer");
            getIntValueFromCursor(contentValues, cursor, "sub_id");
            getIntValueFromCursor(contentValues, cursor, "profile_id");
            getIntValueFromCursor(contentValues, cursor, "modem_cognitive");
            getIntValueFromCursor(contentValues, cursor, "max_conns");
            getIntValueFromCursor(contentValues, cursor, "wait_time");
            getIntValueFromCursor(contentValues, cursor, "max_conns_time");
            getIntValueFromCursor(contentValues, cursor, "mtu");
            getIntValueFromCursor(contentValues, cursor, "network_type_bitmask");
            getIntValueFromCursor(contentValues, cursor, "bearer_bitmask");
            getIntValueFromCursor(contentValues, cursor, "edited");
            getIntValueFromCursor(contentValues, cursor, "user_visible");
            getIntValueFromCursor(contentValues, cursor, "user_editable");
            getIntValueFromCursor(contentValues, cursor, "owned_by");
            getIntValueFromCursor(contentValues, cursor, "apn_set_id");
            getIntValueFromCursor(contentValues, cursor, "skip_464xlat");
        }

        /* JADX WARN: Can't wrap try/catch for region: R(14:9|(1:11)|12|13|(2:15|(1:17))(1:18)|19|(2:21|(2:60|35)(7:57|(1:33)|36|45|37|52|51))|55|36|45|37|52|51|7) */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x00d3, code lost:
        
            r3 = com.android.providers.telephony.TelephonyProvider.selectConflictingRow(r14, "carriers_tmp", r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x00d7, code lost:
        
            if (r3 != null) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x00d9, code lost:
        
            com.android.providers.telephony.TelephonyProvider.mergeFieldsAndUpdateDb(r14, "carriers_tmp", r3, r7, new android.content.ContentValues(), true, r13.mContext);
            r3.close();
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void copyPreservedApnsToNewTable(android.database.sqlite.SQLiteDatabase r14, android.database.Cursor r15) {
            /*
                Method dump skipped, instruction units count: 243
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.TelephonyProvider.DatabaseHelper.copyPreservedApnsToNewTable(android.database.sqlite.SQLiteDatabase, android.database.Cursor):void");
        }

        private void getStringValueFromCursor(ContentValues contentValues, Cursor cursor, String str) {
            int columnIndex = cursor.getColumnIndex(str);
            if (columnIndex != -1) {
                String string = cursor.getString(columnIndex);
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                contentValues.put(str, string);
            }
        }

        private void getNetworkTypeBitmaskFromCursor(ContentValues contentValues, Cursor cursor) {
            int columnIndex = cursor.getColumnIndex("network_type_bitmask");
            if (columnIndex != -1) {
                getStringValueFromCursor(contentValues, cursor, "network_type_bitmask");
                String string = cursor.getString(columnIndex);
                if (TextUtils.isEmpty(string) || !string.matches("\\d+")) {
                    return;
                }
                contentValues.put("bearer_bitmask", String.valueOf(TelephonyProvider.convertNetworkTypeBitmaskToBearerBitmask(Integer.valueOf(string).intValue())));
                return;
            }
            int columnIndex2 = cursor.getColumnIndex("bearer_bitmask");
            if (columnIndex2 != -1) {
                String string2 = cursor.getString(columnIndex2);
                if (TextUtils.isEmpty(string2) || !string2.matches("\\d+")) {
                    return;
                }
                contentValues.put("network_type_bitmask", String.valueOf(TelephonyProvider.convertBearerBitmaskToNetworkTypeBitmask(Integer.valueOf(string2).intValue())));
            }
        }

        private void getIntValueFromCursor(ContentValues contentValues, Cursor cursor, String str) {
            int columnIndex = cursor.getColumnIndex(str);
            if (columnIndex != -1) {
                String string = cursor.getString(columnIndex);
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                try {
                    contentValues.put(str, new Integer(string));
                } catch (NumberFormatException unused) {
                }
            }
        }

        private void getBlobValueFromCursor(ContentValues contentValues, Cursor cursor, String str) {
            byte[] blob;
            int columnIndex = cursor.getColumnIndex(str);
            if (columnIndex == -1 || (blob = cursor.getBlob(columnIndex)) == null) {
                return;
            }
            contentValues.put(str, blob);
        }

        private ContentValues getRow(XmlPullParser xmlPullParser) {
            int iConvertNetworkTypeBitmaskToBearerBitmask;
            String attributeValue;
            if (!"apn".equals(xmlPullParser.getName())) {
                return null;
            }
            ContentValues contentValues = new ContentValues();
            String attributeValue2 = xmlPullParser.getAttributeValue(null, "mcc");
            String attributeValue3 = xmlPullParser.getAttributeValue(null, "mnc");
            contentValues.put("numeric", attributeValue2 + attributeValue3);
            contentValues.put("mcc", attributeValue2);
            contentValues.put("mnc", attributeValue3);
            contentValues.put("name", xmlPullParser.getAttributeValue(null, "carrier"));
            addStringAttribute(xmlPullParser, "apn", contentValues, "apn");
            addStringAttribute(xmlPullParser, "user", contentValues, "user");
            addStringAttribute(xmlPullParser, "server", contentValues, "server");
            addStringAttribute(xmlPullParser, "password", contentValues, "password");
            addStringAttribute(xmlPullParser, "proxy", contentValues, "proxy");
            addStringAttribute(xmlPullParser, "port", contentValues, "port");
            addStringAttribute(xmlPullParser, "mmsproxy", contentValues, "mmsproxy");
            addStringAttribute(xmlPullParser, "mmsport", contentValues, "mmsport");
            addStringAttribute(xmlPullParser, "mmsc", contentValues, "mmsc");
            String attributeValue4 = xmlPullParser.getAttributeValue(null, "type");
            if (attributeValue4 != null) {
                contentValues.put("type", attributeValue4.replaceAll("\\s+", ""));
            }
            addStringAttribute(xmlPullParser, "protocol", contentValues, "protocol");
            addStringAttribute(xmlPullParser, "roaming_protocol", contentValues, "roaming_protocol");
            addIntAttribute(xmlPullParser, "authtype", contentValues, "authtype");
            addIntAttribute(xmlPullParser, "bearer", contentValues, "bearer");
            addIntAttribute(xmlPullParser, "profile_id", contentValues, "profile_id");
            addIntAttribute(xmlPullParser, "max_conns", contentValues, "max_conns");
            addIntAttribute(xmlPullParser, "wait_time", contentValues, "wait_time");
            addIntAttribute(xmlPullParser, "max_conns_time", contentValues, "max_conns_time");
            addIntAttribute(xmlPullParser, "mtu", contentValues, "mtu");
            addIntAttribute(xmlPullParser, "apn_set_id", contentValues, "apn_set_id");
            addIntAttribute(xmlPullParser, CarrierIdProvider.AUTHORITY, contentValues, CarrierIdProvider.AUTHORITY);
            addIntAttribute(xmlPullParser, "skip_464xlat", contentValues, "skip_464xlat");
            addBoolAttribute(xmlPullParser, "carrier_enabled", contentValues, "carrier_enabled");
            addBoolAttribute(xmlPullParser, "modem_cognitive", contentValues, "modem_cognitive");
            addBoolAttribute(xmlPullParser, "user_visible", contentValues, "user_visible");
            addBoolAttribute(xmlPullParser, "user_editable", contentValues, "user_editable");
            String attributeValue5 = xmlPullParser.getAttributeValue(null, "network_type_bitmask");
            int bitmaskFromString = attributeValue5 != null ? TelephonyProvider.getBitmaskFromString(attributeValue5) : 0;
            contentValues.put("network_type_bitmask", Integer.valueOf(bitmaskFromString));
            if (attributeValue5 != null) {
                iConvertNetworkTypeBitmaskToBearerBitmask = TelephonyProvider.convertNetworkTypeBitmaskToBearerBitmask(bitmaskFromString);
            } else {
                String attributeValue6 = xmlPullParser.getAttributeValue(null, "bearer_bitmask");
                int bitmaskFromString2 = attributeValue6 != null ? TelephonyProvider.getBitmaskFromString(attributeValue6) : 0;
                contentValues.put("network_type_bitmask", Integer.valueOf(TelephonyProvider.convertBearerBitmaskToNetworkTypeBitmask(bitmaskFromString2)));
                iConvertNetworkTypeBitmaskToBearerBitmask = bitmaskFromString2;
            }
            contentValues.put("bearer_bitmask", Integer.valueOf(iConvertNetworkTypeBitmaskToBearerBitmask));
            String attributeValue7 = xmlPullParser.getAttributeValue(null, "mvno_type");
            if (attributeValue7 != null && (attributeValue = xmlPullParser.getAttributeValue(null, "mvno_match_data")) != null) {
                contentValues.put("mvno_type", attributeValue7);
                contentValues.put("mvno_match_data", attributeValue);
            }
            return contentValues;
        }

        private void addStringAttribute(XmlPullParser xmlPullParser, String str, ContentValues contentValues, String str2) {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue != null) {
                contentValues.put(str2, attributeValue);
            }
        }

        private void addIntAttribute(XmlPullParser xmlPullParser, String str, ContentValues contentValues, String str2) {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue != null) {
                contentValues.put(str2, Integer.valueOf(Integer.parseInt(attributeValue)));
            }
        }

        private void addBoolAttribute(XmlPullParser xmlPullParser, String str, ContentValues contentValues, String str2) {
            String attributeValue = xmlPullParser.getAttributeValue(null, str);
            if (attributeValue != null) {
                contentValues.put(str2, Boolean.valueOf(Boolean.parseBoolean(attributeValue)));
            }
        }

        private void loadApns(SQLiteDatabase sQLiteDatabase, XmlPullParser xmlPullParser) {
            try {
                if (xmlPullParser != null) {
                    try {
                        sQLiteDatabase.beginTransaction();
                        XmlUtils.nextElement(xmlPullParser);
                        while (xmlPullParser.getEventType() != 1) {
                            ContentValues row = getRow(xmlPullParser);
                            if (row == null) {
                                throw new XmlPullParserException("Expected 'apn' tag", xmlPullParser, null);
                            }
                            insertAddingDefaults(sQLiteDatabase, row);
                            XmlUtils.nextElement(xmlPullParser);
                        }
                        sQLiteDatabase.setTransactionSuccessful();
                    } catch (SQLException e) {
                        TelephonyProvider.loge("Got SQLException while loading apns." + e);
                    } catch (IOException e2) {
                        TelephonyProvider.loge("Got IOException while loading apns." + e2);
                    } catch (XmlPullParserException e3) {
                        TelephonyProvider.loge("Got XmlPullParserException while loading apns." + e3);
                    }
                }
            } finally {
                sQLiteDatabase.endTransaction();
            }
        }

        private void insertAddingDefaults(SQLiteDatabase sQLiteDatabase, ContentValues contentValues) {
            TelephonyProvider.setDefaultValue(contentValues);
            try {
                sQLiteDatabase.insertWithOnConflict("carriers", null, contentValues, 2);
            } catch (SQLException unused) {
                Cursor cursorSelectConflictingRow = TelephonyProvider.selectConflictingRow(sQLiteDatabase, "carriers", contentValues);
                if (cursorSelectConflictingRow != null) {
                    ContentValues contentValues2 = new ContentValues();
                    int i = cursorSelectConflictingRow.getInt(cursorSelectConflictingRow.getColumnIndex("edited"));
                    if (i != 0) {
                        if (i == 2) {
                            i = 3;
                        } else if (i == 5) {
                            i = 6;
                        }
                        contentValues2.put("edited", Integer.valueOf(i));
                    }
                    TelephonyProvider.mergeFieldsAndUpdateDb(sQLiteDatabase, "carriers", cursorSelectConflictingRow, contentValues, contentValues2, false, this.mContext);
                    cursorSelectConflictingRow.close();
                }
            }
        }
    }

    public static void mergeFieldsAndUpdateDb(SQLiteDatabase sQLiteDatabase, String str, Cursor cursor, ContentValues contentValues, ContentValues contentValues2, boolean z, Context context) {
        int i = 0;
        if (contentValues.containsKey("type")) {
            String string = cursor.getString(cursor.getColumnIndex("type"));
            String asString = contentValues.getAsString("type");
            if (!string.equalsIgnoreCase(asString)) {
                if (string.equals("") || asString.equals("")) {
                    contentValues.put("type", "");
                } else {
                    String[] strArrSplit = string.toLowerCase().split(",");
                    String[] strArrSplit2 = asString.toLowerCase().split(",");
                    if (separateRowsNeeded(sQLiteDatabase, str, cursor, contentValues, context, strArrSplit, strArrSplit2)) {
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll(Arrays.asList(strArrSplit));
                    for (String str2 : strArrSplit2) {
                        if (!arrayList.contains(str2.trim())) {
                            arrayList.add(str2);
                        }
                    }
                    StringBuilder sb = new StringBuilder();
                    while (i < arrayList.size()) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(i == 0 ? "" : ",");
                        sb2.append((String) arrayList.get(i));
                        sb.append(sb2.toString());
                        i++;
                    }
                    contentValues.put("type", sb.toString());
                }
            }
            contentValues2.put("type", contentValues.getAsString("type"));
        }
        if (contentValues.containsKey("bearer_bitmask")) {
            int i2 = cursor.getInt(cursor.getColumnIndex("bearer_bitmask"));
            int iIntValue = contentValues.getAsInteger("bearer_bitmask").intValue();
            if (i2 != iIntValue) {
                if (i2 == 0 || iIntValue == 0) {
                    contentValues.put("bearer_bitmask", (Integer) 0);
                } else {
                    contentValues.put("bearer_bitmask", Integer.valueOf(i2 | iIntValue));
                }
            }
            contentValues2.put("bearer_bitmask", contentValues.getAsInteger("bearer_bitmask"));
        }
        if (contentValues.containsKey("network_type_bitmask")) {
            int i3 = cursor.getInt(cursor.getColumnIndex("network_type_bitmask"));
            int iIntValue2 = contentValues.getAsInteger("network_type_bitmask").intValue();
            if (i3 != iIntValue2) {
                if (i3 == 0 || iIntValue2 == 0) {
                    contentValues.put("network_type_bitmask", (Integer) 0);
                } else {
                    contentValues.put("network_type_bitmask", Integer.valueOf(i3 | iIntValue2));
                }
            }
            contentValues2.put("network_type_bitmask", contentValues.getAsInteger("network_type_bitmask"));
        }
        if (contentValues.containsKey("bearer_bitmask") && contentValues.containsKey("network_type_bitmask")) {
            syncBearerBitmaskAndNetworkTypeBitmask(contentValues2);
        }
        if (!z) {
            if (contentValues.containsKey("edited")) {
                int i4 = cursor.getInt(cursor.getColumnIndex("edited"));
                if (contentValues.getAsInteger("edited").intValue() == 0 && (i4 == 4 || i4 == 5 || i4 == 6 || i4 == 1 || i4 == 2 || i4 == 3)) {
                    contentValues.remove("edited");
                }
            }
            contentValues2.putAll(contentValues);
        }
        if (contentValues2.size() > 0) {
            sQLiteDatabase.update(str, contentValues2, "_id=" + cursor.getInt(cursor.getColumnIndex("_id")), null);
        }
    }

    private static boolean separateRowsNeeded(SQLiteDatabase sQLiteDatabase, String str, Cursor cursor, ContentValues contentValues, Context context, String[] strArr, String[] strArr2) {
        boolean z;
        boolean z2;
        if (context.getResources() != null) {
            for (String str2 : context.getResources().getStringArray(R.array.persist_apns_for_plmn)) {
                if (str2.equalsIgnoreCase(contentValues.getAsString("numeric"))) {
                    z = true;
                    break;
                }
            }
        } else {
            loge("separateRowsNeeded: resources=null");
        }
        z = false;
        if (!z) {
            return false;
        }
        ArrayList<String> arrayList = new ArrayList(Arrays.asList(strArr));
        ArrayList arrayList2 = new ArrayList(Arrays.asList(strArr2));
        if (arrayList.size() != arrayList2.size() + 1) {
            if (arrayList.size() + 1 == arrayList2.size()) {
                z2 = false;
                arrayList2 = arrayList;
                arrayList = arrayList2;
            }
            return false;
        }
        z2 = true;
        if (arrayList.contains("dun") && !arrayList2.contains("dun")) {
            arrayList2.add("dun");
            if (arrayList.containsAll(arrayList2) && cursor.getInt(cursor.getColumnIndex("profile_id")) == 0) {
                if (z2) {
                    ContentValues contentValues2 = new ContentValues();
                    StringBuilder sb = new StringBuilder();
                    boolean z3 = true;
                    for (String str3 : arrayList) {
                        if (!str3.equalsIgnoreCase("dun")) {
                            if (!z3) {
                                str3 = "," + str3;
                            }
                            sb.append(str3);
                            z3 = false;
                        }
                    }
                    contentValues2.put("type", sb.toString());
                    sQLiteDatabase.update(str, contentValues2, "_id=" + cursor.getInt(cursor.getColumnIndex("_id")), null);
                    return true;
                }
                contentValues.put("profile_id", new Integer(1));
                try {
                    sQLiteDatabase.insertWithOnConflict(str, null, contentValues, 5);
                    return true;
                } catch (SQLException unused) {
                    loge("Exception on trying to add new row after updating profile_id");
                }
            }
        }
        return false;
    }

    public static Cursor selectConflictingRow(SQLiteDatabase sQLiteDatabase, String str, ContentValues contentValues) {
        int i;
        if (!contentValues.containsKey("numeric") || !contentValues.containsKey("mcc") || !contentValues.containsKey("mnc")) {
            loge("dbh.selectConflictingRow: called for non-conflicting row: " + contentValues);
            return null;
        }
        String[] strArr = {"_id", "type", "edited", "bearer_bitmask", "network_type_bitmask", "profile_id"};
        String str2 = TextUtils.join("=? AND ", CARRIERS_UNIQUE_FIELDS) + "=?";
        int i2 = 0;
        String[] strArr2 = new String[CARRIERS_UNIQUE_FIELDS.size()];
        for (String str3 : CARRIERS_UNIQUE_FIELDS) {
            if (!contentValues.containsKey(str3)) {
                i = i2 + 1;
                strArr2[i2] = CARRIERS_UNIQUE_FIELDS_DEFAULTS.get(str3);
            } else if (CARRIERS_BOOLEAN_FIELDS.contains(str3)) {
                i = i2 + 1;
                strArr2[i2] = convertStringToIntString(contentValues.getAsString(str3));
            } else {
                i = i2 + 1;
                strArr2[i2] = contentValues.getAsString(str3);
            }
            i2 = i;
        }
        Cursor cursorQuery = sQLiteDatabase.query(str, strArr, str2, strArr2, null, null, null);
        if (cursorQuery != null) {
            if (cursorQuery.getCount() == 1) {
                if (cursorQuery.moveToFirst()) {
                    return cursorQuery;
                }
                loge("dbh.selectConflictingRow: moveToFirst() failed");
            } else {
                loge("dbh.selectConflictingRow: Expected 1 but found " + cursorQuery.getCount() + " matching rows found for cv " + contentValues);
            }
            cursorQuery.close();
        } else {
            loge("dbh.selectConflictingRow: Error - c is null; no matching row found for cv " + contentValues);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String convertStringToIntString(String str) {
        return ("0".equals(str) || "false".equalsIgnoreCase(str)) ? "0" : "1";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String convertStringToBoolString(String str) {
        return ("0".equals(str) || "false".equalsIgnoreCase(str)) ? "false" : "true";
    }

    SQLiteDatabase getReadableDatabase() {
        return this.mOpenHelper.getReadableDatabase();
    }

    SQLiteDatabase getWritableDatabase() {
        return this.mOpenHelper.getWritableDatabase();
    }

    void initDatabaseWithDatabaseHelper(SQLiteDatabase sQLiteDatabase) throws Throwable {
        this.mOpenHelper.initDatabase(sQLiteDatabase);
    }

    boolean needApnDbUpdate() {
        return this.mOpenHelper.apnDbUpdateNeeded();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean apnSourceServiceExists(Context context) {
        Boolean bool = Boolean.FALSE;
        Boolean bool2 = s_apnSourceServiceExists;
        if (bool2 != null) {
            return bool2.booleanValue();
        }
        try {
            String string = context.getResources().getString(R.string.apn_source_service);
            if (TextUtils.isEmpty(string)) {
                s_apnSourceServiceExists = bool;
            } else {
                s_apnSourceServiceExists = Boolean.valueOf(context.getPackageManager().getServiceInfo(ComponentName.unflattenFromString(string), 0) != null);
            }
        } catch (PackageManager.NameNotFoundException unused) {
            s_apnSourceServiceExists = bool;
        }
        return s_apnSourceServiceExists.booleanValue();
    }

    private void restoreApnsWithService(int i) {
        Context context = getContext();
        Resources resources = context.getResources();
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        ServiceConnection serviceConnection = new ServiceConnection() { // from class: com.android.providers.telephony.TelephonyProvider.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                TelephonyProvider.log("restoreApnsWithService: onServiceConnected");
                synchronized (TelephonyProvider.this.mLock) {
                    TelephonyProvider.this.mIApnSourceService = IApnSourceService.Stub.asInterface(iBinder);
                    TelephonyProvider.this.mLock.notifyAll();
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                TelephonyProvider.loge("mIApnSourceService has disconnected unexpectedly");
                synchronized (TelephonyProvider.this.mLock) {
                    TelephonyProvider.this.mIApnSourceService = null;
                }
            }

            @Override // android.content.ServiceConnection
            public void onBindingDied(ComponentName componentName) {
                TelephonyProvider.loge("The binding to the apn service connection is dead: " + componentName);
                synchronized (TelephonyProvider.this.mLock) {
                    atomicBoolean.set(true);
                    TelephonyProvider.this.mLock.notifyAll();
                }
            }

            @Override // android.content.ServiceConnection
            public void onNullBinding(ComponentName componentName) {
                TelephonyProvider.loge("Null binding: " + componentName);
                synchronized (TelephonyProvider.this.mLock) {
                    atomicBoolean.set(true);
                    TelephonyProvider.this.mLock.notifyAll();
                }
            }
        };
        Intent intent = new Intent(IApnSourceService.class.getName());
        intent.setComponent(ComponentName.unflattenFromString(resources.getString(R.string.apn_source_service)));
        log("binding to service to restore apns, intent=" + intent);
        try {
            try {
                if (context.bindService(intent, serviceConnection, 65)) {
                    synchronized (this.mLock) {
                        while (this.mIApnSourceService == null && !atomicBoolean.get()) {
                            try {
                                this.mLock.wait();
                            } catch (InterruptedException e) {
                                loge("Error while waiting for service connection: " + e);
                            }
                        }
                        if (atomicBoolean.get()) {
                            loge("The binding is invalid.");
                            context.unbindService(serviceConnection);
                            synchronized (this.mLock) {
                                this.mIApnSourceService = null;
                            }
                            return;
                        }
                        try {
                            ContentValues[] apns = this.mIApnSourceService.getApns(i);
                            if (apns != null) {
                                unsynchronizedBulkInsert(Telephony.Carriers.CONTENT_URI, apns);
                                log("restoreApnsWithService: restored");
                            }
                        } catch (RemoteException e2) {
                            loge("Error applying apns from service: " + e2);
                        }
                    }
                } else {
                    loge("unable to bind to service from intent=" + intent);
                }
                context.unbindService(serviceConnection);
                synchronized (this.mLock) {
                    this.mIApnSourceService = null;
                }
            } catch (SecurityException e3) {
                loge("Error applying apns from service: " + e3);
                context.unbindService(serviceConnection);
                synchronized (this.mLock) {
                    this.mIApnSourceService = null;
                }
            }
        } catch (Throwable th) {
            context.unbindService(serviceConnection);
            synchronized (this.mLock) {
                this.mIApnSourceService = null;
                throw th;
            }
        }
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        boolean z;
        this.mOpenHelper = new DatabaseHelper(getContext());
        try {
            PhoneFactory.addLocalLog("TelephonyProvider", 100);
        } catch (IllegalArgumentException unused) {
        }
        String str = SystemProperties.get("ro.build.id", (String) null);
        if (TextUtils.isEmpty(str)) {
            z = false;
        } else {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("build-id", 0);
            String string = sharedPreferences.getString("ro_build_id", "");
            if (str.equals(string)) {
                z = false;
            } else {
                localLog("onCreate: build id changed from " + string + " to " + str);
                z = true;
            }
            sharedPreferences.edit().putString("ro_build_id", str).apply();
        }
        if (z) {
            if (!apnSourceServiceExists(getContext())) {
                updateApnDb();
            }
            addAllApnSharedPrefToLocalLog();
        }
        this.mManagedApnEnforced = getContext().getSharedPreferences("dpc-apn-enforced", 0).getBoolean("enforced", false);
        return true;
    }

    private void addAllApnSharedPrefToLocalLog() {
        localLog("addAllApnSharedPrefToLocalLog");
        Map<String, ?> all = getContext().getSharedPreferences("preferred-apn", 0).getAll();
        for (String str : all.keySet()) {
            try {
                localLog(str + ":" + all.get(str).toString());
            } catch (Exception e) {
                localLog("Skipping over key " + str + " due to exception " + e);
            }
        }
        Map<String, ?> all2 = getContext().getSharedPreferences("preferred-full-apn", 0).getAll();
        for (String str2 : all2.keySet()) {
            try {
                localLog(str2 + ":" + all2.get(str2).toString());
            } catch (Exception e2) {
                localLog("Skipping over key " + str2 + " due to exception " + e2);
            }
        }
    }

    private static void localLog(String str) {
        Log.d("TelephonyProvider", str);
        PhoneFactory.localLog("TelephonyProvider", str);
    }

    private synchronized boolean isManagedApnEnforced() {
        return this.mManagedApnEnforced;
    }

    private void setManagedApnEnforced(boolean z) {
        SharedPreferences.Editor editorEdit = getContext().getSharedPreferences("dpc-apn-enforced", 0).edit();
        editorEdit.putBoolean("enforced", z);
        editorEdit.apply();
        synchronized (this) {
            this.mManagedApnEnforced = z;
        }
    }

    private void setPreferredApnId(Long l, int i, boolean z) {
        SharedPreferences.Editor editorEdit = getContext().getSharedPreferences("preferred-apn", 0).edit();
        editorEdit.putLong("apn_id" + i, l != null ? l.longValue() : -1L);
        StringBuilder sb = new StringBuilder();
        sb.append("setPreferredApnId: apn_id");
        sb.append(i);
        sb.append(":");
        sb.append(l != null ? l.longValue() : -1L);
        localLog(sb.toString());
        editorEdit.putBoolean("explicit_set_called" + i, z);
        localLog("setPreferredApnId: explicit_set_called" + i + ":" + z);
        editorEdit.apply();
        if (l == null || l.longValue() == -1) {
            deletePreferredApn(i);
        } else if (z) {
            setPreferredApn(l, i);
        }
    }

    private long getPreferredApnId(int i, boolean z) {
        long preferredApnIdFromApn = getContext().getSharedPreferences("preferred-apn", 0).getLong("apn_id" + i, -1L);
        if (preferredApnIdFromApn == -1 && z) {
            preferredApnIdFromApn = getPreferredApnIdFromApn(i);
            if (preferredApnIdFromApn != -1) {
                setPreferredApnId(Long.valueOf(preferredApnIdFromApn), i, false);
            }
        }
        return preferredApnIdFromApn;
    }

    private int getPreferredApnSetId(int i) {
        try {
            return Integer.parseInt(getContext().getSharedPreferences("preferred-full-apn", 0).getString("apn_set_id" + i, null));
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deletePreferredApnId(Context context) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("preferred-apn", 0).edit();
        editorEdit.clear();
        editorEdit.apply();
    }

    private void setPreferredApn(Long l, int i) {
        localLog("setPreferredApn: _id " + l + " subId " + i);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        List<String> list = CARRIERS_UNIQUE_FIELDS;
        Cursor cursorQuery = writableDatabase.query("carriers", (String[]) list.toArray(new String[list.size()]), "_id=" + l, null, null, null, null);
        if (cursorQuery != null) {
            if (cursorQuery.getCount() == 1) {
                cursorQuery.moveToFirst();
                SharedPreferences.Editor editorEdit = getContext().getSharedPreferences("preferred-full-apn", 0).edit();
                for (String str : CARRIERS_UNIQUE_FIELDS) {
                    editorEdit.putString(str + i, cursorQuery.getString(cursorQuery.getColumnIndex(str)));
                    localLog("setPreferredApn: " + str + i + ":" + cursorQuery.getString(cursorQuery.getColumnIndex(str)));
                }
                editorEdit.putString("version" + i, "2949120");
                localLog("setPreferredApn: version" + i + ":2949120");
                editorEdit.apply();
            } else {
                log("setPreferredApn: # matching APNs found " + cursorQuery.getCount());
            }
            cursorQuery.close();
            return;
        }
        log("setPreferredApn: No matching APN found");
    }

    private long getPreferredApnIdFromApn(int i) {
        log("getPreferredApnIdFromApn: for subId " + i);
        SQLiteDatabase readableDatabase = getReadableDatabase();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("preferred-full-apn", 0);
        for (String str : CARRIERS_UNIQUE_FIELDS) {
            String string = sharedPreferences.getString(str + i, null);
            if (string != null) {
                arrayList.add(str);
                arrayList2.add(string);
            }
        }
        long j = -1;
        if (arrayList.size() == 0) {
            return -1L;
        }
        Cursor cursorQuery = readableDatabase.query("carriers", new String[]{"_id"}, TextUtils.join("=? and ", arrayList) + "=?", (String[]) arrayList2.toArray(new String[arrayList2.size()]), null, null, null);
        if (cursorQuery != null) {
            if (cursorQuery.getCount() == 1) {
                cursorQuery.moveToFirst();
                j = cursorQuery.getInt(cursorQuery.getColumnIndex("_id"));
            } else {
                log("getPreferredApnIdFromApn: returning INVALID. # matching APNs found " + cursorQuery.getCount());
            }
            cursorQuery.close();
        } else {
            log("getPreferredApnIdFromApn: returning INVALID. No matching APN found");
        }
        return j;
    }

    private void deletePreferredApn(int i) {
        log("deletePreferredApn: for subId " + i);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("preferred-full-apn", 0);
        if (sharedPreferences.contains("version" + i)) {
            log("deletePreferredApn: apn is stored. Deleting it now for subId " + i);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.remove("version" + i);
            Iterator<String> it = CARRIERS_UNIQUE_FIELDS.iterator();
            while (it.hasNext()) {
                editorEdit.remove(it.next() + i);
            }
            editorEdit.apply();
        }
    }

    boolean isCallingFromSystemOrPhoneUid() {
        return this.mInjector.binderGetCallingUid() == 1000 || this.mInjector.binderGetCallingUid() == 1001;
    }

    void ensureCallingFromSystemOrPhoneUid(String str) {
        if (!isCallingFromSystemOrPhoneUid()) {
            throw new SecurityException(str);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02d6 A[Catch: SQLException -> 0x030e, all -> 0x0332, TryCatch #5 {SQLException -> 0x030e, blocks: (B:101:0x02ca, B:103:0x02d6, B:107:0x02f0, B:106:0x02df, B:108:0x0301), top: B:128:0x02ca, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0325 A[Catch: all -> 0x0332, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:8:0x0027, B:9:0x002b, B:12:0x0031, B:16:0x0048, B:18:0x004e, B:20:0x0059, B:19:0x0054, B:23:0x0064, B:24:0x0068, B:27:0x006e, B:31:0x0085, B:34:0x0095, B:35:0x0099, B:36:0x009d, B:38:0x00bb, B:42:0x00d2, B:46:0x00da, B:49:0x0102, B:53:0x011d, B:56:0x0128, B:58:0x0130, B:60:0x0145, B:63:0x014a, B:66:0x0160, B:68:0x0166, B:97:0x02b7, B:99:0x02bd, B:100:0x02c6, B:101:0x02ca, B:103:0x02d6, B:107:0x02f0, B:106:0x02df, B:108:0x0301, B:113:0x0325, B:111:0x030f, B:69:0x016d, B:70:0x0174, B:71:0x0180, B:72:0x0184, B:73:0x0188, B:75:0x01a8, B:78:0x01be, B:79:0x01c2, B:80:0x01c6, B:82:0x01e5, B:85:0x01fb, B:86:0x01ff, B:87:0x0203, B:89:0x024e, B:92:0x0264, B:93:0x026a, B:94:0x0283, B:95:0x02a7, B:96:0x02b2), top: B:121:0x0001, inners: #0, #2, #3, #4, #5, #6, #7, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004e A[Catch: all -> 0x0332, TryCatch #1 {, blocks: (B:3:0x0001, B:8:0x0027, B:9:0x002b, B:12:0x0031, B:16:0x0048, B:18:0x004e, B:20:0x0059, B:19:0x0054, B:23:0x0064, B:24:0x0068, B:27:0x006e, B:31:0x0085, B:34:0x0095, B:35:0x0099, B:36:0x009d, B:38:0x00bb, B:42:0x00d2, B:46:0x00da, B:49:0x0102, B:53:0x011d, B:56:0x0128, B:58:0x0130, B:60:0x0145, B:63:0x014a, B:66:0x0160, B:68:0x0166, B:97:0x02b7, B:99:0x02bd, B:100:0x02c6, B:101:0x02ca, B:103:0x02d6, B:107:0x02f0, B:106:0x02df, B:108:0x0301, B:113:0x0325, B:111:0x030f, B:69:0x016d, B:70:0x0174, B:71:0x0180, B:72:0x0184, B:73:0x0188, B:75:0x01a8, B:78:0x01be, B:79:0x01c2, B:80:0x01c6, B:82:0x01e5, B:85:0x01fb, B:86:0x01ff, B:87:0x0203, B:89:0x024e, B:92:0x0264, B:93:0x026a, B:94:0x0283, B:95:0x02a7, B:96:0x02b2), top: B:121:0x0001, inners: #0, #2, #3, #4, #5, #6, #7, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0054 A[Catch: all -> 0x0332, TryCatch #1 {, blocks: (B:3:0x0001, B:8:0x0027, B:9:0x002b, B:12:0x0031, B:16:0x0048, B:18:0x004e, B:20:0x0059, B:19:0x0054, B:23:0x0064, B:24:0x0068, B:27:0x006e, B:31:0x0085, B:34:0x0095, B:35:0x0099, B:36:0x009d, B:38:0x00bb, B:42:0x00d2, B:46:0x00da, B:49:0x0102, B:53:0x011d, B:56:0x0128, B:58:0x0130, B:60:0x0145, B:63:0x014a, B:66:0x0160, B:68:0x0166, B:97:0x02b7, B:99:0x02bd, B:100:0x02c6, B:101:0x02ca, B:103:0x02d6, B:107:0x02f0, B:106:0x02df, B:108:0x0301, B:113:0x0325, B:111:0x030f, B:69:0x016d, B:70:0x0174, B:71:0x0180, B:72:0x0184, B:73:0x0188, B:75:0x01a8, B:78:0x01be, B:79:0x01c2, B:80:0x01c6, B:82:0x01e5, B:85:0x01fb, B:86:0x01ff, B:87:0x0203, B:89:0x024e, B:92:0x0264, B:93:0x026a, B:94:0x0283, B:95:0x02a7, B:96:0x02b2), top: B:121:0x0001, inners: #0, #2, #3, #4, #5, #6, #7, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00d8 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00da A[Catch: all -> 0x0332, TRY_ENTER, TRY_LEAVE, TryCatch #1 {, blocks: (B:3:0x0001, B:8:0x0027, B:9:0x002b, B:12:0x0031, B:16:0x0048, B:18:0x004e, B:20:0x0059, B:19:0x0054, B:23:0x0064, B:24:0x0068, B:27:0x006e, B:31:0x0085, B:34:0x0095, B:35:0x0099, B:36:0x009d, B:38:0x00bb, B:42:0x00d2, B:46:0x00da, B:49:0x0102, B:53:0x011d, B:56:0x0128, B:58:0x0130, B:60:0x0145, B:63:0x014a, B:66:0x0160, B:68:0x0166, B:97:0x02b7, B:99:0x02bd, B:100:0x02c6, B:101:0x02ca, B:103:0x02d6, B:107:0x02f0, B:106:0x02df, B:108:0x0301, B:113:0x0325, B:111:0x030f, B:69:0x016d, B:70:0x0174, B:71:0x0180, B:72:0x0184, B:73:0x0188, B:75:0x01a8, B:78:0x01be, B:79:0x01c2, B:80:0x01c6, B:82:0x01e5, B:85:0x01fb, B:86:0x01ff, B:87:0x0203, B:89:0x024e, B:92:0x0264, B:93:0x026a, B:94:0x0283, B:95:0x02a7, B:96:0x02b2), top: B:121:0x0001, inners: #0, #2, #3, #4, #5, #6, #7, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0166 A[Catch: all -> 0x0332, TryCatch #1 {, blocks: (B:3:0x0001, B:8:0x0027, B:9:0x002b, B:12:0x0031, B:16:0x0048, B:18:0x004e, B:20:0x0059, B:19:0x0054, B:23:0x0064, B:24:0x0068, B:27:0x006e, B:31:0x0085, B:34:0x0095, B:35:0x0099, B:36:0x009d, B:38:0x00bb, B:42:0x00d2, B:46:0x00da, B:49:0x0102, B:53:0x011d, B:56:0x0128, B:58:0x0130, B:60:0x0145, B:63:0x014a, B:66:0x0160, B:68:0x0166, B:97:0x02b7, B:99:0x02bd, B:100:0x02c6, B:101:0x02ca, B:103:0x02d6, B:107:0x02f0, B:106:0x02df, B:108:0x0301, B:113:0x0325, B:111:0x030f, B:69:0x016d, B:70:0x0174, B:71:0x0180, B:72:0x0184, B:73:0x0188, B:75:0x01a8, B:78:0x01be, B:79:0x01c2, B:80:0x01c6, B:82:0x01e5, B:85:0x01fb, B:86:0x01ff, B:87:0x0203, B:89:0x024e, B:92:0x0264, B:93:0x026a, B:94:0x0283, B:95:0x02a7, B:96:0x02b2), top: B:121:0x0001, inners: #0, #2, #3, #4, #5, #6, #7, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x016d A[Catch: all -> 0x0332, TryCatch #1 {, blocks: (B:3:0x0001, B:8:0x0027, B:9:0x002b, B:12:0x0031, B:16:0x0048, B:18:0x004e, B:20:0x0059, B:19:0x0054, B:23:0x0064, B:24:0x0068, B:27:0x006e, B:31:0x0085, B:34:0x0095, B:35:0x0099, B:36:0x009d, B:38:0x00bb, B:42:0x00d2, B:46:0x00da, B:49:0x0102, B:53:0x011d, B:56:0x0128, B:58:0x0130, B:60:0x0145, B:63:0x014a, B:66:0x0160, B:68:0x0166, B:97:0x02b7, B:99:0x02bd, B:100:0x02c6, B:101:0x02ca, B:103:0x02d6, B:107:0x02f0, B:106:0x02df, B:108:0x0301, B:113:0x0325, B:111:0x030f, B:69:0x016d, B:70:0x0174, B:71:0x0180, B:72:0x0184, B:73:0x0188, B:75:0x01a8, B:78:0x01be, B:79:0x01c2, B:80:0x01c6, B:82:0x01e5, B:85:0x01fb, B:86:0x01ff, B:87:0x0203, B:89:0x024e, B:92:0x0264, B:93:0x026a, B:94:0x0283, B:95:0x02a7, B:96:0x02b2), top: B:121:0x0001, inners: #0, #2, #3, #4, #5, #6, #7, #8 }] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x02bd A[Catch: all -> 0x0332, TryCatch #1 {, blocks: (B:3:0x0001, B:8:0x0027, B:9:0x002b, B:12:0x0031, B:16:0x0048, B:18:0x004e, B:20:0x0059, B:19:0x0054, B:23:0x0064, B:24:0x0068, B:27:0x006e, B:31:0x0085, B:34:0x0095, B:35:0x0099, B:36:0x009d, B:38:0x00bb, B:42:0x00d2, B:46:0x00da, B:49:0x0102, B:53:0x011d, B:56:0x0128, B:58:0x0130, B:60:0x0145, B:63:0x014a, B:66:0x0160, B:68:0x0166, B:97:0x02b7, B:99:0x02bd, B:100:0x02c6, B:101:0x02ca, B:103:0x02d6, B:107:0x02f0, B:106:0x02df, B:108:0x0301, B:113:0x0325, B:111:0x030f, B:69:0x016d, B:70:0x0174, B:71:0x0180, B:72:0x0184, B:73:0x0188, B:75:0x01a8, B:78:0x01be, B:79:0x01c2, B:80:0x01c6, B:82:0x01e5, B:85:0x01fb, B:86:0x01ff, B:87:0x0203, B:89:0x024e, B:92:0x0264, B:93:0x026a, B:94:0x0283, B:95:0x02a7, B:96:0x02b2), top: B:121:0x0001, inners: #0, #2, #3, #4, #5, #6, #7, #8 }] */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized android.database.Cursor query(android.net.Uri r11, java.lang.String[] r12, java.lang.String r13, java.lang.String[] r14, java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 880
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.TelephonyProvider.query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0148  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.database.Cursor getSubscriptionMatchingAPNList(android.database.sqlite.SQLiteQueryBuilder r17, java.lang.String[] r18, java.lang.String r19, java.lang.String[] r20, java.lang.String r21, int r22) {
        /*
            Method dump skipped, instruction units count: 447
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.TelephonyProvider.getSubscriptionMatchingAPNList(android.database.sqlite.SQLiteQueryBuilder, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String, int):android.database.Cursor");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void appendCursorData(android.database.MatrixCursor r7, android.database.MatrixCursor r8) {
        /*
        L0:
            boolean r0 = r8.moveToNext()
            if (r0 == 0) goto L5a
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String[] r1 = r8.getColumnNames()
            int r2 = r1.length
            r3 = 0
        L11:
            if (r3 >= r2) goto L56
            r4 = r1[r3]
            int r4 = r8.getColumnIndex(r4)
            int r5 = r8.getType(r4)
            if (r5 == 0) goto L4c
            r6 = 1
            if (r5 == r6) goto L40
            r6 = 2
            if (r5 == r6) goto L34
            r6 = 3
            if (r5 == r6) goto L4c
            r6 = 4
            if (r5 == r6) goto L2c
            goto L53
        L2c:
            byte[] r4 = r8.getBlob(r4)
            r0.add(r4)
            goto L53
        L34:
            float r4 = r8.getFloat(r4)
            java.lang.Float r4 = java.lang.Float.valueOf(r4)
            r0.add(r4)
            goto L53
        L40:
            int r4 = r8.getInt(r4)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r0.add(r4)
            goto L53
        L4c:
            java.lang.String r4 = r8.getString(r4)
            r0.add(r4)
        L53:
            int r3 = r3 + 1
            goto L11
        L56:
            r7.addRow(r0)
            goto L0
        L5a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.TelephonyProvider.appendCursorData(android.database.MatrixCursor, android.database.MatrixCursor):void");
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int iMatch = s_urlMatcher.match(uri);
        if (iMatch == 1) {
            return "vnd.android.cursor.dir/telephony-carrier";
        }
        if (iMatch != 3) {
            if (iMatch == 8) {
                return "vnd.android.cursor.dir/telephony-carrier";
            }
            if (iMatch != 19 && iMatch != 25 && iMatch != 5 && iMatch != 6 && iMatch != 11 && iMatch != 12 && iMatch != 21 && iMatch != 22) {
                throw new IllegalArgumentException("Unknown URL " + uri);
            }
        }
        return "vnd.android.cursor.item/telephony-carrier";
    }

    @Override // android.content.ContentProvider
    public synchronized int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        return unsynchronizedBulkInsert(uri, contentValuesArr);
    }

    private int unsynchronizedBulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        boolean z = false;
        int i = 0;
        for (ContentValues contentValues : contentValuesArr) {
            Pair<Uri, Boolean> pairInsertSingleRow = insertSingleRow(uri, contentValues);
            if (pairInsertSingleRow.first != null) {
                i++;
            }
            if (((Boolean) pairInsertSingleRow.second).booleanValue()) {
                z = true;
            }
        }
        if (z) {
            getContext().getContentResolver().notifyChange(Telephony.Carriers.CONTENT_URI, null, true, -1);
        }
        return i;
    }

    @Override // android.content.ContentProvider
    public synchronized Uri insert(Uri uri, ContentValues contentValues) {
        Pair<Uri, Boolean> pairInsertSingleRow;
        pairInsertSingleRow = insertSingleRow(uri, contentValues);
        if (((Boolean) pairInsertSingleRow.second).booleanValue()) {
            getContext().getContentResolver().notifyChange(Telephony.Carriers.CONTENT_URI, null, true, -1);
        }
        return (Uri) pairInsertSingleRow.first;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.util.Pair<android.net.Uri, java.lang.Boolean> insertRowWithValue(android.content.ContentValues r11) {
        /*
            r10 = this;
            java.lang.String r0 = "carriers"
            android.database.sqlite.SQLiteDatabase r1 = r10.getWritableDatabase()
            r8 = 1
            r9 = 0
            r2 = 0
            r3 = 2
            long r3 = r1.insertWithOnConflict(r0, r9, r11, r3)     // Catch: android.database.SQLException -> L1c
            r5 = 0
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 < 0) goto L4c
            android.net.Uri r5 = android.provider.Telephony.Carriers.CONTENT_URI     // Catch: android.database.SQLException -> L1c
            android.net.Uri r10 = android.content.ContentUris.withAppendedId(r5, r3)     // Catch: android.database.SQLException -> L1c
            r9 = r10
            goto L4d
        L1c:
            r3 = move-exception
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "insert: exception "
            r4.append(r5)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            log(r3)
            android.database.Cursor r0 = selectConflictingRow(r1, r0, r11)
            if (r0 == 0) goto L4c
            android.content.ContentValues r5 = new android.content.ContentValues
            r5.<init>()
            r6 = 0
            android.content.Context r7 = r10.getContext()
            java.lang.String r2 = "carriers"
            r3 = r0
            r4 = r11
            mergeFieldsAndUpdateDb(r1, r2, r3, r4, r5, r6, r7)
            r0.close()
            goto L4d
        L4c:
            r8 = r2
        L4d:
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r8)
            android.util.Pair r10 = android.util.Pair.create(r9, r10)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.TelephonyProvider.insertRowWithValue(android.content.ContentValues):android.util.Pair");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:47:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.util.Pair<android.net.Uri, java.lang.Boolean> insertSingleRow(android.net.Uri r17, android.content.ContentValues r18) {
        /*
            Method dump skipped, instruction units count: 472
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.TelephonyProvider.insertSingleRow(android.net.Uri, android.content.ContentValues):android.util.Pair");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:46:0x02f0 A[Catch: all -> 0x0314, TRY_LEAVE, TryCatch #2 {, blocks: (B:3:0x0001, B:4:0x002b, B:5:0x002e, B:49:0x02ff, B:50:0x0313, B:6:0x0032, B:46:0x02f0, B:7:0x0049, B:8:0x0076, B:10:0x007c, B:11:0x0080, B:12:0x0084, B:14:0x00a3, B:15:0x00cd, B:16:0x00ce, B:17:0x00d2, B:18:0x00d6, B:20:0x00f5, B:21:0x011f, B:22:0x0120, B:23:0x0124, B:24:0x0128, B:26:0x0147, B:27:0x0171, B:28:0x0172, B:29:0x0176, B:30:0x017a, B:32:0x0199, B:33:0x01c3, B:34:0x01c4, B:35:0x01cc, B:40:0x01de, B:41:0x0205, B:42:0x025b, B:44:0x02ec, B:43:0x02a4), top: B:58:0x0001, inners: #0, #1, #3, #4 }] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0079  */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized int delete(android.net.Uri r12, java.lang.String r13, java.lang.String[] r14) {
        /*
            Method dump skipped, instruction units count: 830
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.TelephonyProvider.delete(android.net.Uri, java.lang.String, java.lang.String[]):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:100:0x03ac A[Catch: all -> 0x040c, TryCatch #4 {, blocks: (B:4:0x000b, B:5:0x0025, B:6:0x0028, B:109:0x03f7, B:110:0x040b, B:7:0x002c, B:9:0x0033, B:11:0x003b, B:84:0x0322, B:86:0x0332, B:88:0x0348, B:89:0x0359, B:91:0x0361, B:92:0x0372, B:94:0x037a, B:95:0x038b, B:97:0x0393, B:98:0x03a4, B:100:0x03ac, B:101:0x03bd, B:103:0x03c5, B:104:0x03d6, B:106:0x03de, B:12:0x004a, B:15:0x0053, B:17:0x006a, B:18:0x0085, B:19:0x0086, B:20:0x008a, B:21:0x008e, B:24:0x00ae, B:25:0x00bd, B:26:0x00d8, B:28:0x00da, B:29:0x0105, B:30:0x0106, B:31:0x010a, B:32:0x010e, B:34:0x012d, B:35:0x0158, B:36:0x0159, B:37:0x015d, B:38:0x0161, B:40:0x0180, B:41:0x01ab, B:42:0x01ac, B:43:0x01b0, B:44:0x01b4, B:46:0x01d3, B:47:0x01fe, B:48:0x01ff, B:50:0x020a, B:52:0x0212, B:58:0x022a, B:61:0x0232, B:63:0x023a, B:64:0x0243, B:67:0x0256, B:69:0x0272, B:70:0x0294, B:71:0x02af, B:72:0x02b0, B:74:0x02b8, B:75:0x02c1, B:76:0x02e5, B:78:0x02ed, B:79:0x02f6), top: B:122:0x000b, inners: #0, #1, #2, #3, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x03c5 A[Catch: all -> 0x040c, TryCatch #4 {, blocks: (B:4:0x000b, B:5:0x0025, B:6:0x0028, B:109:0x03f7, B:110:0x040b, B:7:0x002c, B:9:0x0033, B:11:0x003b, B:84:0x0322, B:86:0x0332, B:88:0x0348, B:89:0x0359, B:91:0x0361, B:92:0x0372, B:94:0x037a, B:95:0x038b, B:97:0x0393, B:98:0x03a4, B:100:0x03ac, B:101:0x03bd, B:103:0x03c5, B:104:0x03d6, B:106:0x03de, B:12:0x004a, B:15:0x0053, B:17:0x006a, B:18:0x0085, B:19:0x0086, B:20:0x008a, B:21:0x008e, B:24:0x00ae, B:25:0x00bd, B:26:0x00d8, B:28:0x00da, B:29:0x0105, B:30:0x0106, B:31:0x010a, B:32:0x010e, B:34:0x012d, B:35:0x0158, B:36:0x0159, B:37:0x015d, B:38:0x0161, B:40:0x0180, B:41:0x01ab, B:42:0x01ac, B:43:0x01b0, B:44:0x01b4, B:46:0x01d3, B:47:0x01fe, B:48:0x01ff, B:50:0x020a, B:52:0x0212, B:58:0x022a, B:61:0x0232, B:63:0x023a, B:64:0x0243, B:67:0x0256, B:69:0x0272, B:70:0x0294, B:71:0x02af, B:72:0x02b0, B:74:0x02b8, B:75:0x02c1, B:76:0x02e5, B:78:0x02ed, B:79:0x02f6), top: B:122:0x000b, inners: #0, #1, #2, #3, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x03de A[Catch: all -> 0x040c, TRY_LEAVE, TryCatch #4 {, blocks: (B:4:0x000b, B:5:0x0025, B:6:0x0028, B:109:0x03f7, B:110:0x040b, B:7:0x002c, B:9:0x0033, B:11:0x003b, B:84:0x0322, B:86:0x0332, B:88:0x0348, B:89:0x0359, B:91:0x0361, B:92:0x0372, B:94:0x037a, B:95:0x038b, B:97:0x0393, B:98:0x03a4, B:100:0x03ac, B:101:0x03bd, B:103:0x03c5, B:104:0x03d6, B:106:0x03de, B:12:0x004a, B:15:0x0053, B:17:0x006a, B:18:0x0085, B:19:0x0086, B:20:0x008a, B:21:0x008e, B:24:0x00ae, B:25:0x00bd, B:26:0x00d8, B:28:0x00da, B:29:0x0105, B:30:0x0106, B:31:0x010a, B:32:0x010e, B:34:0x012d, B:35:0x0158, B:36:0x0159, B:37:0x015d, B:38:0x0161, B:40:0x0180, B:41:0x01ab, B:42:0x01ac, B:43:0x01b0, B:44:0x01b4, B:46:0x01d3, B:47:0x01fe, B:48:0x01ff, B:50:0x020a, B:52:0x0212, B:58:0x022a, B:61:0x0232, B:63:0x023a, B:64:0x0243, B:67:0x0256, B:69:0x0272, B:70:0x0294, B:71:0x02af, B:72:0x02b0, B:74:0x02b8, B:75:0x02c1, B:76:0x02e5, B:78:0x02ed, B:79:0x02f6), top: B:122:0x000b, inners: #0, #1, #2, #3, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x02b8 A[Catch: all -> 0x040c, TryCatch #4 {, blocks: (B:4:0x000b, B:5:0x0025, B:6:0x0028, B:109:0x03f7, B:110:0x040b, B:7:0x002c, B:9:0x0033, B:11:0x003b, B:84:0x0322, B:86:0x0332, B:88:0x0348, B:89:0x0359, B:91:0x0361, B:92:0x0372, B:94:0x037a, B:95:0x038b, B:97:0x0393, B:98:0x03a4, B:100:0x03ac, B:101:0x03bd, B:103:0x03c5, B:104:0x03d6, B:106:0x03de, B:12:0x004a, B:15:0x0053, B:17:0x006a, B:18:0x0085, B:19:0x0086, B:20:0x008a, B:21:0x008e, B:24:0x00ae, B:25:0x00bd, B:26:0x00d8, B:28:0x00da, B:29:0x0105, B:30:0x0106, B:31:0x010a, B:32:0x010e, B:34:0x012d, B:35:0x0158, B:36:0x0159, B:37:0x015d, B:38:0x0161, B:40:0x0180, B:41:0x01ab, B:42:0x01ac, B:43:0x01b0, B:44:0x01b4, B:46:0x01d3, B:47:0x01fe, B:48:0x01ff, B:50:0x020a, B:52:0x0212, B:58:0x022a, B:61:0x0232, B:63:0x023a, B:64:0x0243, B:67:0x0256, B:69:0x0272, B:70:0x0294, B:71:0x02af, B:72:0x02b0, B:74:0x02b8, B:75:0x02c1, B:76:0x02e5, B:78:0x02ed, B:79:0x02f6), top: B:122:0x000b, inners: #0, #1, #2, #3, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02ed A[Catch: all -> 0x040c, TryCatch #4 {, blocks: (B:4:0x000b, B:5:0x0025, B:6:0x0028, B:109:0x03f7, B:110:0x040b, B:7:0x002c, B:9:0x0033, B:11:0x003b, B:84:0x0322, B:86:0x0332, B:88:0x0348, B:89:0x0359, B:91:0x0361, B:92:0x0372, B:94:0x037a, B:95:0x038b, B:97:0x0393, B:98:0x03a4, B:100:0x03ac, B:101:0x03bd, B:103:0x03c5, B:104:0x03d6, B:106:0x03de, B:12:0x004a, B:15:0x0053, B:17:0x006a, B:18:0x0085, B:19:0x0086, B:20:0x008a, B:21:0x008e, B:24:0x00ae, B:25:0x00bd, B:26:0x00d8, B:28:0x00da, B:29:0x0105, B:30:0x0106, B:31:0x010a, B:32:0x010e, B:34:0x012d, B:35:0x0158, B:36:0x0159, B:37:0x015d, B:38:0x0161, B:40:0x0180, B:41:0x01ab, B:42:0x01ac, B:43:0x01b0, B:44:0x01b4, B:46:0x01d3, B:47:0x01fe, B:48:0x01ff, B:50:0x020a, B:52:0x0212, B:58:0x022a, B:61:0x0232, B:63:0x023a, B:64:0x0243, B:67:0x0256, B:69:0x0272, B:70:0x0294, B:71:0x02af, B:72:0x02b0, B:74:0x02b8, B:75:0x02c1, B:76:0x02e5, B:78:0x02ed, B:79:0x02f6), top: B:122:0x000b, inners: #0, #1, #2, #3, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x031c  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0348 A[Catch: all -> 0x040c, TryCatch #4 {, blocks: (B:4:0x000b, B:5:0x0025, B:6:0x0028, B:109:0x03f7, B:110:0x040b, B:7:0x002c, B:9:0x0033, B:11:0x003b, B:84:0x0322, B:86:0x0332, B:88:0x0348, B:89:0x0359, B:91:0x0361, B:92:0x0372, B:94:0x037a, B:95:0x038b, B:97:0x0393, B:98:0x03a4, B:100:0x03ac, B:101:0x03bd, B:103:0x03c5, B:104:0x03d6, B:106:0x03de, B:12:0x004a, B:15:0x0053, B:17:0x006a, B:18:0x0085, B:19:0x0086, B:20:0x008a, B:21:0x008e, B:24:0x00ae, B:25:0x00bd, B:26:0x00d8, B:28:0x00da, B:29:0x0105, B:30:0x0106, B:31:0x010a, B:32:0x010e, B:34:0x012d, B:35:0x0158, B:36:0x0159, B:37:0x015d, B:38:0x0161, B:40:0x0180, B:41:0x01ab, B:42:0x01ac, B:43:0x01b0, B:44:0x01b4, B:46:0x01d3, B:47:0x01fe, B:48:0x01ff, B:50:0x020a, B:52:0x0212, B:58:0x022a, B:61:0x0232, B:63:0x023a, B:64:0x0243, B:67:0x0256, B:69:0x0272, B:70:0x0294, B:71:0x02af, B:72:0x02b0, B:74:0x02b8, B:75:0x02c1, B:76:0x02e5, B:78:0x02ed, B:79:0x02f6), top: B:122:0x000b, inners: #0, #1, #2, #3, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0361 A[Catch: all -> 0x040c, TryCatch #4 {, blocks: (B:4:0x000b, B:5:0x0025, B:6:0x0028, B:109:0x03f7, B:110:0x040b, B:7:0x002c, B:9:0x0033, B:11:0x003b, B:84:0x0322, B:86:0x0332, B:88:0x0348, B:89:0x0359, B:91:0x0361, B:92:0x0372, B:94:0x037a, B:95:0x038b, B:97:0x0393, B:98:0x03a4, B:100:0x03ac, B:101:0x03bd, B:103:0x03c5, B:104:0x03d6, B:106:0x03de, B:12:0x004a, B:15:0x0053, B:17:0x006a, B:18:0x0085, B:19:0x0086, B:20:0x008a, B:21:0x008e, B:24:0x00ae, B:25:0x00bd, B:26:0x00d8, B:28:0x00da, B:29:0x0105, B:30:0x0106, B:31:0x010a, B:32:0x010e, B:34:0x012d, B:35:0x0158, B:36:0x0159, B:37:0x015d, B:38:0x0161, B:40:0x0180, B:41:0x01ab, B:42:0x01ac, B:43:0x01b0, B:44:0x01b4, B:46:0x01d3, B:47:0x01fe, B:48:0x01ff, B:50:0x020a, B:52:0x0212, B:58:0x022a, B:61:0x0232, B:63:0x023a, B:64:0x0243, B:67:0x0256, B:69:0x0272, B:70:0x0294, B:71:0x02af, B:72:0x02b0, B:74:0x02b8, B:75:0x02c1, B:76:0x02e5, B:78:0x02ed, B:79:0x02f6), top: B:122:0x000b, inners: #0, #1, #2, #3, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x037a A[Catch: all -> 0x040c, TryCatch #4 {, blocks: (B:4:0x000b, B:5:0x0025, B:6:0x0028, B:109:0x03f7, B:110:0x040b, B:7:0x002c, B:9:0x0033, B:11:0x003b, B:84:0x0322, B:86:0x0332, B:88:0x0348, B:89:0x0359, B:91:0x0361, B:92:0x0372, B:94:0x037a, B:95:0x038b, B:97:0x0393, B:98:0x03a4, B:100:0x03ac, B:101:0x03bd, B:103:0x03c5, B:104:0x03d6, B:106:0x03de, B:12:0x004a, B:15:0x0053, B:17:0x006a, B:18:0x0085, B:19:0x0086, B:20:0x008a, B:21:0x008e, B:24:0x00ae, B:25:0x00bd, B:26:0x00d8, B:28:0x00da, B:29:0x0105, B:30:0x0106, B:31:0x010a, B:32:0x010e, B:34:0x012d, B:35:0x0158, B:36:0x0159, B:37:0x015d, B:38:0x0161, B:40:0x0180, B:41:0x01ab, B:42:0x01ac, B:43:0x01b0, B:44:0x01b4, B:46:0x01d3, B:47:0x01fe, B:48:0x01ff, B:50:0x020a, B:52:0x0212, B:58:0x022a, B:61:0x0232, B:63:0x023a, B:64:0x0243, B:67:0x0256, B:69:0x0272, B:70:0x0294, B:71:0x02af, B:72:0x02b0, B:74:0x02b8, B:75:0x02c1, B:76:0x02e5, B:78:0x02ed, B:79:0x02f6), top: B:122:0x000b, inners: #0, #1, #2, #3, #5 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0393 A[Catch: all -> 0x040c, TryCatch #4 {, blocks: (B:4:0x000b, B:5:0x0025, B:6:0x0028, B:109:0x03f7, B:110:0x040b, B:7:0x002c, B:9:0x0033, B:11:0x003b, B:84:0x0322, B:86:0x0332, B:88:0x0348, B:89:0x0359, B:91:0x0361, B:92:0x0372, B:94:0x037a, B:95:0x038b, B:97:0x0393, B:98:0x03a4, B:100:0x03ac, B:101:0x03bd, B:103:0x03c5, B:104:0x03d6, B:106:0x03de, B:12:0x004a, B:15:0x0053, B:17:0x006a, B:18:0x0085, B:19:0x0086, B:20:0x008a, B:21:0x008e, B:24:0x00ae, B:25:0x00bd, B:26:0x00d8, B:28:0x00da, B:29:0x0105, B:30:0x0106, B:31:0x010a, B:32:0x010e, B:34:0x012d, B:35:0x0158, B:36:0x0159, B:37:0x015d, B:38:0x0161, B:40:0x0180, B:41:0x01ab, B:42:0x01ac, B:43:0x01b0, B:44:0x01b4, B:46:0x01d3, B:47:0x01fe, B:48:0x01ff, B:50:0x020a, B:52:0x0212, B:58:0x022a, B:61:0x0232, B:63:0x023a, B:64:0x0243, B:67:0x0256, B:69:0x0272, B:70:0x0294, B:71:0x02af, B:72:0x02b0, B:74:0x02b8, B:75:0x02c1, B:76:0x02e5, B:78:0x02ed, B:79:0x02f6), top: B:122:0x000b, inners: #0, #1, #2, #3, #5 }] */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized int update(android.net.Uri r18, android.content.ContentValues r19, java.lang.String r20, java.lang.String[] r21) {
        /*
            Method dump skipped, instruction units count: 1084
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.TelephonyProvider.update(android.net.Uri, android.content.ContentValues, java.lang.String, java.lang.String[]):int");
    }

    private static Uri getNotifyContentUri(Uri uri, boolean z, int i) {
        if (!z) {
            return uri;
        }
        return Uri.withAppendedPath(uri, "" + i);
    }

    private void checkPermission() {
        if (getContext().checkCallingOrSelfPermission("android.permission.WRITE_APN_SETTINGS") == 0) {
            return;
        }
        String[] packagesForUid = getContext().getPackageManager().getPackagesForUid(Binder.getCallingUid());
        TelephonyManager telephonyManager = (TelephonyManager) getContext().getSystemService("phone");
        for (String str : packagesForUid) {
            if (telephonyManager.checkCarrierPrivilegesForPackageAnyPhone(str) == 1) {
                return;
            }
        }
        throw new SecurityException("No permission to access APN settings");
    }

    private void checkPermissionCompat(int i, String[] strArr) {
        if (!CompatChanges.isChangeEnabled(124107808L, Binder.getCallingUid())) {
            log("Using old permission behavior for telephony provider compat");
            checkQueryPermission(i, strArr);
        } else {
            checkPermission();
        }
    }

    private void checkQueryPermission(int i, String[] strArr) {
        if (i != 7) {
            if (strArr != null) {
                for (String str : strArr) {
                    if (!"type".equals(str) && !"mmsc".equals(str) && !"mmsproxy".equals(str) && !"mmsport".equals(str) && !"mvno_type".equals(str) && !"mvno_match_data".equals(str) && !"apn".equals(str)) {
                        checkPermission();
                        return;
                    }
                }
                return;
            }
            checkPermission();
        }
    }

    private void restoreDefaultAPN(int i) throws Throwable {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String whereClauseForRestoreDefaultApn = ((TelephonyManager) getContext().getSystemService("phone")).getPhoneCount() > 1 ? getWhereClauseForRestoreDefaultApn(writableDatabase, i) : null;
        if (TextUtils.isEmpty(whereClauseForRestoreDefaultApn)) {
            whereClauseForRestoreDefaultApn = "owned_by!=0";
        }
        log("restoreDefaultAPN: where: " + whereClauseForRestoreDefaultApn);
        try {
            writableDatabase.delete("carriers", whereClauseForRestoreDefaultApn, null);
        } catch (SQLException e) {
            loge("got exception when deleting to restore: " + e);
        }
        SharedPreferences.Editor editorEdit = getContext().getSharedPreferences("preferred-apn", 0).edit();
        editorEdit.clear();
        editorEdit.apply();
        SharedPreferences.Editor editorEdit2 = getContext().getSharedPreferences("preferred-full-apn", 0).edit();
        editorEdit2.clear();
        editorEdit2.apply();
        if (apnSourceServiceExists(getContext())) {
            restoreApnsWithService(i);
        } else {
            initDatabaseWithDatabaseHelper(writableDatabase);
        }
    }

    private String getWhereClauseForRestoreDefaultApn(SQLiteDatabase sQLiteDatabase, int i) {
        TelephonyManager telephonyManagerCreateForSubscriptionId = ((TelephonyManager) getContext().getSystemService(TelephonyManager.class)).createForSubscriptionId(i);
        String simOperator = telephonyManagerCreateForSubscriptionId.getSimOperator();
        Cursor cursorQuery = sQLiteDatabase.query("carriers", new String[]{"mvno_type", "mvno_match_data"}, "numeric='" + simOperator + "'", null, null, null, "name ASC");
        String str = null;
        if (cursorQuery == null) {
            return null;
        }
        cursorQuery.moveToFirst();
        while (true) {
            if (!cursorQuery.isAfterLast()) {
                String string = cursorQuery.getString(0);
                String string2 = cursorQuery.getString(1);
                if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2) && telephonyManagerCreateForSubscriptionId.matchesCurrentSimOperator(simOperator, getMvnoTypeIntFromString(string), string2)) {
                    str = "numeric='" + simOperator + "' AND mvno_type='" + string + "' AND mvno_match_data='" + string2 + "' AND owned_by!=0";
                    break;
                }
                cursorQuery.moveToNext();
            } else {
                break;
            }
        }
        cursorQuery.close();
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return "numeric='" + simOperator + "' AND (mvno_type='' OR mvno_match_data='') AND owned_by!=0";
    }

    private synchronized void updateApnDb() {
        if (apnSourceServiceExists(getContext())) {
            loge("called updateApnDb when apn source service exists");
            return;
        }
        if (!needApnDbUpdate()) {
            log("Skipping apn db update since apn-conf has not changed.");
            return;
        }
        SQLiteDatabase writableDatabase = getWritableDatabase();
        deletePreferredApnId(getContext());
        try {
            writableDatabase.delete("carriers", "edited=0 and owned_by!=0", null);
        } catch (SQLException e) {
            loge("got exception when deleting to update: " + e);
        }
        initDatabaseWithDatabaseHelper(writableDatabase);
        getContext().getContentResolver().notifyChange(Telephony.Carriers.CONTENT_URI, null, true, -1);
    }

    public static void fillInMccMncStringAtCursor(Context context, SQLiteDatabase sQLiteDatabase, Cursor cursor) {
        try {
            int i = cursor.getInt(cursor.getColumnIndexOrThrow("mcc"));
            int i2 = cursor.getInt(cursor.getColumnIndexOrThrow("mnc"));
            String string = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
            String str = String.format(Locale.getDefault(), "%03d", Integer.valueOf(i));
            String bestStringMnc = getBestStringMnc(context, str, i2);
            ContentValues contentValues = new ContentValues(2);
            contentValues.put("mcc_string", str);
            contentValues.put("mnc_string", bestStringMnc);
            sQLiteDatabase.update("siminfo", contentValues, "_id=?", new String[]{string});
        } catch (IllegalArgumentException unused) {
            Log.e("TelephonyProvider", "Possible database corruption -- some columns not found.");
        }
    }

    private static String getBestStringMnc(Context context, String str, int i) {
        if (i >= 100 && i <= 999) {
            return String.valueOf(i);
        }
        String str2 = String.format(Locale.getDefault(), "%02d", Integer.valueOf(i));
        String str3 = "0" + str2;
        Cursor cursorQuery = context.getContentResolver().query(Telephony.CarrierId.All.CONTENT_URI, null, "mccmnc=?", new String[]{str + str2}, null);
        try {
            if (cursorQuery.getCount() > 0) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return str2;
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return str3;
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

    private static void syncBearerBitmaskAndNetworkTypeBitmask(ContentValues contentValues) {
        if (contentValues.containsKey("network_type_bitmask")) {
            int iConvertNetworkTypeBitmaskToBearerBitmask = convertNetworkTypeBitmaskToBearerBitmask(contentValues.getAsInteger("network_type_bitmask").intValue());
            if (contentValues.containsKey("bearer_bitmask") && iConvertNetworkTypeBitmaskToBearerBitmask != contentValues.getAsInteger("bearer_bitmask").intValue()) {
                loge("Network type bitmask and bearer bitmask are not compatible.");
            }
            contentValues.put("bearer_bitmask", Integer.valueOf(convertNetworkTypeBitmaskToBearerBitmask(contentValues.getAsInteger("network_type_bitmask").intValue())));
            return;
        }
        if (contentValues.containsKey("bearer_bitmask")) {
            contentValues.put("network_type_bitmask", Integer.valueOf(convertBearerBitmaskToNetworkTypeBitmask(contentValues.getAsInteger("bearer_bitmask").intValue())));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(String str) {
        Log.d("TelephonyProvider", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void loge(String str) {
        Log.e("TelephonyProvider", str);
    }

    private static int getMvnoTypeIntFromString(String str) {
        if (!TextUtils.isEmpty(str)) {
            str = str.toLowerCase();
        }
        Integer num = MVNO_TYPE_STRING_MAP.get(str);
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getBitmaskFromString(String str) {
        int bitmaskForTech = 0;
        for (String str2 : str.split("\\|")) {
            try {
                int i = Integer.parseInt(str2.trim());
                if (i == 0) {
                    return 0;
                }
                bitmaskForTech |= getBitmaskForTech(i);
            } catch (NumberFormatException unused) {
                return 0;
            }
        }
        return bitmaskForTech;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int convertNetworkTypeBitmaskToBearerBitmask(int i) {
        if (i == 0) {
            return 0;
        }
        int bitmaskForTech = 0;
        for (int i2 = 0; i2 < 21; i2++) {
            if (bitmaskHasTarget(i, rilRadioTechnologyToNetworkTypeBitmask(i2))) {
                bitmaskForTech |= getBitmaskForTech(i2);
            }
        }
        return bitmaskForTech;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int convertBearerBitmaskToNetworkTypeBitmask(int i) {
        if (i == 0) {
            return 0;
        }
        int iRilRadioTechnologyToNetworkTypeBitmask = 0;
        for (int i2 = 0; i2 < 21; i2++) {
            if (bitmaskHasTarget(i, getBitmaskForTech(i2))) {
                iRilRadioTechnologyToNetworkTypeBitmask |= rilRadioTechnologyToNetworkTypeBitmask(i2);
            }
        }
        return iRilRadioTechnologyToNetworkTypeBitmask;
    }
}
