package com.android.providers.telephony;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Environment;
import android.os.FileUtils;
import android.os.SystemProperties;
import android.provider.Telephony;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.telephony.util.TelephonyUtils;
import com.android.providers.telephony.nano.CarrierIdProto$CarrierAttribute;
import com.android.providers.telephony.nano.CarrierIdProto$CarrierId;
import com.android.providers.telephony.nano.CarrierIdProto$CarrierList;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public class CarrierIdProvider extends ContentProvider {

    @VisibleForTesting
    public static final String AUTHORITY = "carrier_id";
    private final Map<Integer, ContentValues> mCurrentSubscriptionMap = new ConcurrentHashMap();
    private CarrierIdDatabaseHelper mDbHelper;
    private static final String TAG = CarrierIdProvider.class.getSimpleName();
    private static final String PREF_FILE = CarrierIdProvider.class.getSimpleName();
    private static final UriMatcher s_urlMatcher = new UriMatcher(-1);
    private static final List<String> CARRIERS_ID_UNIQUE_FIELDS = new ArrayList(Arrays.asList("mccmnc", "gid1", "gid2", "plmn", "imsi_prefix_xpattern", "spn", "apn", "iccid_prefix", "privilege_access_rule", "parent_carrier_id"));

    @VisibleForTesting
    public static String getStringForCarrierIdTableCreation(String str) {
        return "CREATE TABLE " + str + "(_id INTEGER PRIMARY KEY,mccmnc TEXT NOT NULL,gid1 TEXT,gid2 TEXT,plmn TEXT,imsi_prefix_xpattern TEXT,spn TEXT,apn TEXT,iccid_prefix TEXT,privilege_access_rule TEXT,carrier_name TEXT," + AUTHORITY + " INTEGER DEFAULT -1,parent_carrier_id INTEGER DEFAULT -1,UNIQUE (" + TextUtils.join(", ", CARRIERS_ID_UNIQUE_FIELDS) + "));";
    }

    @VisibleForTesting
    public static String getStringForIndexCreation(String str) {
        return "CREATE INDEX IF NOT EXISTS mccmncIndex ON " + str + " (mccmnc);";
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() throws Throwable {
        Log.d(TAG, "onCreate");
        CarrierIdDatabaseHelper carrierIdDatabaseHelper = new CarrierIdDatabaseHelper(getContext());
        this.mDbHelper = carrierIdDatabaseHelper;
        carrierIdDatabaseHelper.getReadableDatabase();
        s_urlMatcher.addURI(AUTHORITY, "all", 1);
        s_urlMatcher.addURI(AUTHORITY, "all/update_db", 2);
        s_urlMatcher.addURI(AUTHORITY, "all/get_version", 3);
        updateDatabaseFromPb(this.mDbHelper.getWritableDatabase());
        return true;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        Log.d(TAG, "getType");
        return null;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int iMatch = s_urlMatcher.match(uri);
        if (iMatch == 1) {
            checkReadPermission();
            SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
            sQLiteQueryBuilder.setTables(AUTHORITY);
            return sQLiteQueryBuilder.query(getReadableDatabase(), strArr, str, strArr2, null, null, str2);
        }
        if (iMatch == 3) {
            checkReadPermission();
            MatrixCursor matrixCursor = new MatrixCursor(new String[]{"version"});
            matrixCursor.addRow(new Object[]{Integer.valueOf(getAppliedVersion())});
            return matrixCursor;
        }
        return queryCarrierIdForCurrentSubscription(uri, strArr);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        checkWritePermission();
        if (s_urlMatcher.match(uri) == 1) {
            long jInsertOrThrow = getWritableDatabase().insertOrThrow(AUTHORITY, null, contentValues);
            if (jInsertOrThrow <= 0) {
                return null;
            }
            Uri uriWithAppendedId = ContentUris.withAppendedId(Telephony.CarrierId.All.CONTENT_URI, jInsertOrThrow);
            getContext().getContentResolver().notifyChange(Telephony.CarrierId.All.CONTENT_URI, null);
            return uriWithAppendedId;
        }
        throw new IllegalArgumentException("Cannot insert that URL: " + uri);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        checkWritePermission();
        if (s_urlMatcher.match(uri) == 1) {
            int iDelete = getWritableDatabase().delete(AUTHORITY, str, strArr);
            Log.d(TAG, "  delete.count=" + iDelete);
            if (iDelete > 0) {
                getContext().getContentResolver().notifyChange(Telephony.CarrierId.All.CONTENT_URI, null);
            }
            return iDelete;
        }
        throw new IllegalArgumentException("Cannot delete that URL: " + uri);
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        checkWritePermission();
        int iMatch = s_urlMatcher.match(uri);
        if (iMatch != 1) {
            if (iMatch == 2) {
                return updateDatabaseFromPb(getWritableDatabase());
            }
            return updateCarrierIdForCurrentSubscription(uri, contentValues);
        }
        int iUpdate = getWritableDatabase().update(AUTHORITY, contentValues, str, strArr);
        Log.d(TAG, "  update.count=" + iUpdate);
        if (iUpdate > 0) {
            getContext().getContentResolver().notifyChange(Telephony.CarrierId.All.CONTENT_URI, null);
        }
        return iUpdate;
    }

    SQLiteDatabase getReadableDatabase() {
        return this.mDbHelper.getReadableDatabase();
    }

    SQLiteDatabase getWritableDatabase() {
        return this.mDbHelper.getWritableDatabase();
    }

    private class CarrierIdDatabaseHelper extends SQLiteOpenHelper {
        private final String TAG;

        public CarrierIdDatabaseHelper(Context context) {
            super(context, "carrierIdentification.db", (SQLiteDatabase.CursorFactory) null, 5);
            String simpleName = CarrierIdDatabaseHelper.class.getSimpleName();
            this.TAG = simpleName;
            Log.d(simpleName, "CarrierIdDatabaseHelper: 5");
            setWriteAheadLoggingEnabled(false);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            Log.d(this.TAG, "onCreate");
            sQLiteDatabase.execSQL(CarrierIdProvider.getStringForCarrierIdTableCreation(CarrierIdProvider.AUTHORITY));
            sQLiteDatabase.execSQL(CarrierIdProvider.getStringForIndexCreation(CarrierIdProvider.AUTHORITY));
        }

        public void createCarrierTable(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL(CarrierIdProvider.getStringForCarrierIdTableCreation(CarrierIdProvider.AUTHORITY));
            sQLiteDatabase.execSQL(CarrierIdProvider.getStringForIndexCreation(CarrierIdProvider.AUTHORITY));
        }

        public void dropCarrierTable(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS carrier_id;");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) throws Throwable {
            Log.d(this.TAG, "dbh.onUpgrade:+ db=" + sQLiteDatabase + " oldV=" + i + " newV=" + i2);
            if (i < 5) {
                dropCarrierTable(sQLiteDatabase);
                createCarrierTable(sQLiteDatabase);
                CarrierIdProvider.this.setAppliedVersion(0);
                CarrierIdProvider.this.updateDatabaseFromPb(sQLiteDatabase);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int updateDatabaseFromPb(SQLiteDatabase sQLiteDatabase) throws Throwable {
        String str = TAG;
        Log.d(str, "update database from pb file");
        CarrierIdProto$CarrierList updateCarrierList = getUpdateCarrierList();
        int i = 0;
        if (updateCarrierList == null) {
            return 0;
        }
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.delete(AUTHORITY, null, null);
            CarrierIdProto$CarrierId[] carrierIdProto$CarrierIdArr = updateCarrierList.carrierId;
            int length = carrierIdProto$CarrierIdArr.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                CarrierIdProto$CarrierId carrierIdProto$CarrierId = carrierIdProto$CarrierIdArr[i2];
                CarrierIdProto$CarrierAttribute[] carrierIdProto$CarrierAttributeArr = carrierIdProto$CarrierId.carrierAttribute;
                int length2 = carrierIdProto$CarrierAttributeArr.length;
                int i4 = i;
                while (i4 < length2) {
                    CarrierIdProto$CarrierAttribute carrierIdProto$CarrierAttribute = carrierIdProto$CarrierAttributeArr[i4];
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(AUTHORITY, Integer.valueOf(carrierIdProto$CarrierId.canonicalId));
                    CarrierIdProto$CarrierId[] carrierIdProto$CarrierIdArr2 = carrierIdProto$CarrierIdArr;
                    contentValues.put("carrier_name", carrierIdProto$CarrierId.carrierName);
                    if (carrierIdProto$CarrierId.parentCanonicalId > 0) {
                        contentValues.put("parent_carrier_id", Integer.valueOf(carrierIdProto$CarrierId.parentCanonicalId));
                    }
                    ArrayList arrayList = new ArrayList();
                    convertCarrierAttrToContentValues(contentValues, arrayList, carrierIdProto$CarrierAttribute, 0);
                    Iterator<ContentValues> it = arrayList.iterator();
                    while (it.hasNext()) {
                        if (sQLiteDatabase.insertWithOnConflict(AUTHORITY, null, it.next(), 4) > 0) {
                            i3++;
                        } else {
                            Log.e(str, "updateDatabaseFromPB insertion failure, row: " + i3 + "carrier id: " + carrierIdProto$CarrierId.canonicalId);
                        }
                    }
                    i4++;
                    carrierIdProto$CarrierIdArr = carrierIdProto$CarrierIdArr2;
                }
                i2++;
                i = 0;
            }
            Log.d(str, "update database from pb. inserted rows = " + i3);
            if (i3 > 0) {
                getContext().getContentResolver().notifyChange(Telephony.CarrierId.All.CONTENT_URI, null);
            }
            setAppliedVersion(updateCarrierList.version);
            sQLiteDatabase.setTransactionSuccessful();
            return i3;
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private void convertCarrierAttrToContentValues(ContentValues contentValues, List<ContentValues> list, CarrierIdProto$CarrierAttribute carrierIdProto$CarrierAttribute, int i) {
        int i2;
        if (i > 8) {
            ContentValues contentValues2 = new ContentValues(contentValues);
            if (list.contains(contentValues2)) {
                return;
            }
            list.add(contentValues2);
            return;
        }
        int i3 = 0;
        switch (i) {
            case 0:
                String[] strArr = carrierIdProto$CarrierAttribute.mccmncTuple;
                int length = strArr.length;
                i2 = 0;
                while (i3 < length) {
                    contentValues.put("mccmnc", strArr[i3]);
                    convertCarrierAttrToContentValues(contentValues, list, carrierIdProto$CarrierAttribute, i + 1);
                    contentValues.remove("mccmnc");
                    i3++;
                    i2 = 1;
                }
                i3 = i2;
                break;
            case 1:
                String[] strArr2 = carrierIdProto$CarrierAttribute.imsiPrefixXpattern;
                int length2 = strArr2.length;
                i2 = 0;
                while (i3 < length2) {
                    contentValues.put("imsi_prefix_xpattern", strArr2[i3]);
                    convertCarrierAttrToContentValues(contentValues, list, carrierIdProto$CarrierAttribute, i + 1);
                    contentValues.remove("imsi_prefix_xpattern");
                    i3++;
                    i2 = 1;
                }
                i3 = i2;
                break;
            case 2:
                String[] strArr3 = carrierIdProto$CarrierAttribute.gid1;
                int length3 = strArr3.length;
                i2 = 0;
                while (i3 < length3) {
                    contentValues.put("gid1", strArr3[i3].toLowerCase());
                    convertCarrierAttrToContentValues(contentValues, list, carrierIdProto$CarrierAttribute, i + 1);
                    contentValues.remove("gid1");
                    i3++;
                    i2 = 1;
                }
                i3 = i2;
                break;
            case 3:
                String[] strArr4 = carrierIdProto$CarrierAttribute.gid2;
                int length4 = strArr4.length;
                i2 = 0;
                while (i3 < length4) {
                    contentValues.put("gid2", strArr4[i3].toLowerCase());
                    convertCarrierAttrToContentValues(contentValues, list, carrierIdProto$CarrierAttribute, i + 1);
                    contentValues.remove("gid2");
                    i3++;
                    i2 = 1;
                }
                i3 = i2;
                break;
            case 4:
                String[] strArr5 = carrierIdProto$CarrierAttribute.plmn;
                int length5 = strArr5.length;
                i2 = 0;
                while (i3 < length5) {
                    contentValues.put("plmn", strArr5[i3]);
                    convertCarrierAttrToContentValues(contentValues, list, carrierIdProto$CarrierAttribute, i + 1);
                    contentValues.remove("plmn");
                    i3++;
                    i2 = 1;
                }
                i3 = i2;
                break;
            case 5:
                String[] strArr6 = carrierIdProto$CarrierAttribute.spn;
                int length6 = strArr6.length;
                i2 = 0;
                while (i3 < length6) {
                    contentValues.put("spn", strArr6[i3].toLowerCase());
                    convertCarrierAttrToContentValues(contentValues, list, carrierIdProto$CarrierAttribute, i + 1);
                    contentValues.remove("spn");
                    i3++;
                    i2 = 1;
                }
                i3 = i2;
                break;
            case 6:
                String[] strArr7 = carrierIdProto$CarrierAttribute.preferredApn;
                int length7 = strArr7.length;
                i2 = 0;
                while (i3 < length7) {
                    contentValues.put("apn", strArr7[i3]);
                    convertCarrierAttrToContentValues(contentValues, list, carrierIdProto$CarrierAttribute, i + 1);
                    contentValues.remove("apn");
                    i3++;
                    i2 = 1;
                }
                i3 = i2;
                break;
            case 7:
                String[] strArr8 = carrierIdProto$CarrierAttribute.iccidPrefix;
                int length8 = strArr8.length;
                i2 = 0;
                while (i3 < length8) {
                    contentValues.put("iccid_prefix", strArr8[i3]);
                    convertCarrierAttrToContentValues(contentValues, list, carrierIdProto$CarrierAttribute, i + 1);
                    contentValues.remove("iccid_prefix");
                    i3++;
                    i2 = 1;
                }
                i3 = i2;
                break;
            case 8:
                String[] strArr9 = carrierIdProto$CarrierAttribute.privilegeAccessRule;
                int length9 = strArr9.length;
                i2 = 0;
                while (i3 < length9) {
                    contentValues.put("privilege_access_rule", strArr9[i3]);
                    convertCarrierAttrToContentValues(contentValues, list, carrierIdProto$CarrierAttribute, i + 1);
                    contentValues.remove("privilege_access_rule");
                    i3++;
                    i2 = 1;
                }
                i3 = i2;
                break;
            default:
                Log.e(TAG, "unsupported index: " + i);
                break;
        }
        if (i3 == 0) {
            convertCarrierAttrToContentValues(contentValues, list, carrierIdProto$CarrierAttribute, i + 1);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.AutoCloseable] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r4v0, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r4v15, types: [java.io.FileInputStream, java.io.InputStream, java.lang.AutoCloseable] */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.lang.AutoCloseable] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.lang.AutoCloseable] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r9v0, types: [android.content.ContentProvider, com.android.providers.telephony.CarrierIdProvider] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v4 */
    private CarrierIdProto$CarrierList getUpdateCarrierList() throws Throwable {
        InputStream inputStreamOpen;
        ?? sb;
        CarrierIdProto$CarrierList from;
        Throwable th;
        ?? fileInputStream;
        IOException e;
        CarrierIdProto$CarrierList from2;
        int i;
        String str = TAG;
        int appliedVersion = getAppliedVersion();
        ?? r2 = 0;
        carrierIdProto$CarrierList = null;
        CarrierIdProto$CarrierList carrierIdProto$CarrierList = null;
        try {
            try {
                inputStreamOpen = getContext().getAssets().open("carrier_list.pb");
            } catch (IOException e2) {
                e = e2;
                inputStreamOpen = null;
            } catch (Throwable th2) {
                th = th2;
                FileUtils.closeQuietly((AutoCloseable) r2);
                throw th;
            }
            try {
                from = CarrierIdProto$CarrierList.parseFrom(readInputStreamToByteArray(inputStreamOpen));
                FileUtils.closeQuietly(inputStreamOpen);
                this = inputStreamOpen;
            } catch (IOException e3) {
                e = e3;
                sb = new StringBuilder();
                sb.append("read carrier list from assets pb failure: ");
                sb.append(e);
                Log.e(str, sb.toString());
                FileUtils.closeQuietly(inputStreamOpen);
                from = null;
                this = inputStreamOpen;
            }
            try {
                try {
                    fileInputStream = new FileInputStream(new File(Environment.getDataDirectory(), "misc/carrierid/carrier_list.pb"));
                } catch (IOException e4) {
                    fileInputStream = this;
                    e = e4;
                } catch (Throwable th3) {
                    sb = this;
                    th = th3;
                    FileUtils.closeQuietly((AutoCloseable) sb);
                    throw th;
                }
                try {
                    from2 = CarrierIdProto$CarrierList.parseFrom(readInputStreamToByteArray(fileInputStream));
                    FileUtils.closeQuietly((AutoCloseable) fileInputStream);
                    sb = fileInputStream;
                } catch (IOException e5) {
                    e = e5;
                    Log.e(str, "read carrier list from ota pb failure: " + e);
                    FileUtils.closeQuietly((AutoCloseable) fileInputStream);
                    from2 = null;
                    sb = fileInputStream;
                }
                if (from != null && (i = from.version) > appliedVersion) {
                    carrierIdProto$CarrierList = from;
                    appliedVersion = i;
                }
                if (from2 == null || (!(TelephonyUtils.IS_DEBUGGABLE && SystemProperties.getBoolean("persist.telephony.test.carrierid.ota", false)) && from2.version <= appliedVersion)) {
                    from2 = carrierIdProto$CarrierList;
                } else {
                    appliedVersion = from2.version;
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("latest version: ");
                sb2.append(appliedVersion);
                sb2.append(" need update: ");
                sb2.append(from2 != null);
                Log.d(str, sb2.toString());
                return from2;
            } catch (Throwable th4) {
                th = th4;
                FileUtils.closeQuietly((AutoCloseable) sb);
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            r2 = this;
            FileUtils.closeQuietly((AutoCloseable) r2);
            throw th;
        }
    }

    private int getAppliedVersion() {
        return getContext().getSharedPreferences(PREF_FILE, 0).getInt("version", -1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAppliedVersion(int i) {
        Log.d(TAG, "update version number: " + Integer.toHexString(i) + " relative version: " + (16777215 & i));
        SharedPreferences.Editor editorEdit = getContext().getSharedPreferences(PREF_FILE, 0).edit();
        editorEdit.putInt("version", i);
        editorEdit.apply();
    }

    private static byte[] readInputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[16384];
        while (true) {
            int i = inputStream.read(bArr, 0, 16384);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    private int updateCarrierIdForCurrentSubscription(Uri uri, ContentValues contentValues) {
        String str = TAG;
        try {
            int defaultSubscriptionId = Integer.parseInt(uri.getLastPathSegment());
            Log.d(str, "updateCarrierIdForSubId: " + defaultSubscriptionId);
            if (defaultSubscriptionId == Integer.MAX_VALUE) {
                defaultSubscriptionId = SubscriptionManager.getDefaultSubscriptionId();
            }
            SubscriptionManager subscriptionManager = (SubscriptionManager) getContext().getSystemService("telephony_subscription_service");
            if (!subscriptionManager.isActiveSubscriptionId(defaultSubscriptionId)) {
                ArrayList arrayList = new ArrayList();
                List<SubscriptionInfo> completeActiveSubscriptionInfoList = subscriptionManager.getCompleteActiveSubscriptionInfoList();
                if (completeActiveSubscriptionInfoList != null) {
                    Iterator<SubscriptionInfo> it = completeActiveSubscriptionInfoList.iterator();
                    while (it.hasNext()) {
                        arrayList.add(Integer.valueOf(it.next().getSubscriptionId()));
                    }
                }
                int i = 0;
                Iterator<Integer> it2 = this.mCurrentSubscriptionMap.keySet().iterator();
                while (it2.hasNext()) {
                    int iIntValue = it2.next().intValue();
                    if (!arrayList.contains(Integer.valueOf(iIntValue))) {
                        i++;
                        Log.d(str, "updateCarrierIdForSubId: " + iIntValue);
                        this.mCurrentSubscriptionMap.remove(Integer.valueOf(iIntValue));
                        getContext().getContentResolver().notifyChange(Telephony.CarrierId.CONTENT_URI, null);
                    }
                }
                return i;
            }
            this.mCurrentSubscriptionMap.put(Integer.valueOf(defaultSubscriptionId), new ContentValues(contentValues));
            getContext().getContentResolver().notifyChange(Telephony.CarrierId.CONTENT_URI, null);
            return 1;
        } catch (NumberFormatException unused) {
            throw new IllegalArgumentException("invalid subid in provided uri " + uri);
        }
    }

    private Cursor queryCarrierIdForCurrentSubscription(Uri uri, String[] strArr) {
        int defaultSubscriptionId = SubscriptionManager.getDefaultSubscriptionId();
        if (!TextUtils.isEmpty(uri.getLastPathSegment())) {
            try {
                defaultSubscriptionId = Integer.parseInt(uri.getLastPathSegment());
            } catch (NumberFormatException unused) {
                throw new IllegalArgumentException("invalid subid in provided uri" + uri);
            }
        }
        Log.d(TAG, "queryCarrierIdForSubId: " + defaultSubscriptionId);
        if (defaultSubscriptionId == Integer.MAX_VALUE) {
            defaultSubscriptionId = SubscriptionManager.getDefaultSubscriptionId();
        }
        if (!this.mCurrentSubscriptionMap.containsKey(Integer.valueOf(defaultSubscriptionId))) {
            return new MatrixCursor(strArr, 0);
        }
        MatrixCursor matrixCursor = new MatrixCursor(strArr, 1);
        MatrixCursor.RowBuilder rowBuilderNewRow = matrixCursor.newRow();
        for (int i = 0; i < matrixCursor.getColumnCount(); i++) {
            String columnName = matrixCursor.getColumnName(i);
            if (AUTHORITY.equals(columnName)) {
                rowBuilderNewRow.add(this.mCurrentSubscriptionMap.get(Integer.valueOf(defaultSubscriptionId)).get(AUTHORITY));
            } else if ("carrier_name".equals(columnName)) {
                rowBuilderNewRow.add(this.mCurrentSubscriptionMap.get(Integer.valueOf(defaultSubscriptionId)).get("carrier_name"));
            } else {
                throw new IllegalArgumentException("Invalid column " + strArr[i]);
            }
        }
        return matrixCursor;
    }

    private void checkReadPermission() {
        if (getContext().checkCallingOrSelfPermission("android.permission.READ_PRIVILEGED_PHONE_STATE") != 0) {
            throw new SecurityException("No permission to read CarrierId provider");
        }
    }

    private void checkWritePermission() {
        if (getContext().checkCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE") != 0) {
            throw new SecurityException("No permission to write CarrierId provider");
        }
    }
}
