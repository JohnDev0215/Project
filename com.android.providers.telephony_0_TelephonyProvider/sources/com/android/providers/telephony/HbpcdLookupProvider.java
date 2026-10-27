package com.android.providers.telephony;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class HbpcdLookupProvider extends ContentProvider {
    private static boolean DBG = false;
    private static final HashMap<String, String> sArbitraryProjectionMap;
    private static final HashMap<String, String> sConflictProjectionMap;
    private static final HashMap<String, String> sIddProjectionMap;
    private static final HashMap<String, String> sLookupProjectionMap;
    private static final HashMap<String, String> sNanpProjectionMap;
    private static final HashMap<String, String> sRangeProjectionMap;
    private static final UriMatcher sURIMatcher;
    private HbpcdLookupDatabaseHelper mDbHelper;

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        sURIMatcher = uriMatcher;
        uriMatcher.addURI("hbpcd_lookup", "idd", 1);
        sURIMatcher.addURI("hbpcd_lookup", "lookup", 2);
        sURIMatcher.addURI("hbpcd_lookup", "conflict", 3);
        sURIMatcher.addURI("hbpcd_lookup", "range", 4);
        sURIMatcher.addURI("hbpcd_lookup", "nanp", 5);
        sURIMatcher.addURI("hbpcd_lookup", "arbitrary", 6);
        sURIMatcher.addURI("hbpcd_lookup", "idd/#", 8);
        sURIMatcher.addURI("hbpcd_lookup", "lookup/#", 9);
        sURIMatcher.addURI("hbpcd_lookup", "conflict/#", 10);
        sURIMatcher.addURI("hbpcd_lookup", "range/#", 11);
        sURIMatcher.addURI("hbpcd_lookup", "nanp/#", 12);
        sURIMatcher.addURI("hbpcd_lookup", "arbitrary/#", 13);
        HashMap<String, String> map = new HashMap<>();
        sIddProjectionMap = map;
        map.put("_id", "_id");
        sIddProjectionMap.put("MCC", "MCC");
        sIddProjectionMap.put("IDD", "IDD");
        HashMap<String, String> map2 = new HashMap<>();
        sLookupProjectionMap = map2;
        map2.put("_id", "_id");
        sLookupProjectionMap.put("MCC", "MCC");
        sLookupProjectionMap.put("Country_Code", "Country_Code");
        sLookupProjectionMap.put("Country_Name", "Country_Name");
        sLookupProjectionMap.put("NDD", "NDD");
        sLookupProjectionMap.put("NANPS", "NANPS");
        sLookupProjectionMap.put("GMT_Offset_Low", "GMT_Offset_Low");
        sLookupProjectionMap.put("GMT_Offset_High", "GMT_Offset_High");
        sLookupProjectionMap.put("GMT_DST_Low", "GMT_DST_Low");
        sLookupProjectionMap.put("GMT_DST_High", "GMT_DST_High");
        HashMap<String, String> map3 = new HashMap<>();
        sConflictProjectionMap = map3;
        map3.put("GMT_Offset_Low", "mcc_lookup_table.GMT_Offset_Low");
        sConflictProjectionMap.put("GMT_Offset_High", "mcc_lookup_table.GMT_Offset_High");
        sConflictProjectionMap.put("GMT_DST_Low", "mcc_lookup_table.GMT_DST_Low");
        sConflictProjectionMap.put("GMT_DST_High", "mcc_lookup_table.GMT_DST_High");
        sConflictProjectionMap.put("MCC", "mcc_sid_conflict.MCC");
        sConflictProjectionMap.put("SID_Conflict", "mcc_sid_conflict.SID_Conflict");
        HashMap<String, String> map4 = new HashMap<>();
        sRangeProjectionMap = map4;
        map4.put("_id", "_id");
        sRangeProjectionMap.put("MCC", "MCC");
        sRangeProjectionMap.put("SID_Range_Low", "SID_Range_Low");
        sRangeProjectionMap.put("SID_Range_High", "SID_Range_High");
        HashMap<String, String> map5 = new HashMap<>();
        sNanpProjectionMap = map5;
        map5.put("_id", "_id");
        sNanpProjectionMap.put("Area_Code", "Area_Code");
        HashMap<String, String> map6 = new HashMap<>();
        sArbitraryProjectionMap = map6;
        map6.put("_id", "_id");
        sArbitraryProjectionMap.put("MCC", "MCC");
        sArbitraryProjectionMap.put("SID", "SID");
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        if (DBG) {
            Log.d("HbpcdLookupProvider", "onCreate");
        }
        HbpcdLookupDatabaseHelper hbpcdLookupDatabaseHelper = new HbpcdLookupDatabaseHelper(getContext());
        this.mDbHelper = hbpcdLookupDatabaseHelper;
        hbpcdLookupDatabaseHelper.getReadableDatabase();
        return true;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        if (!DBG) {
            return null;
        }
        Log.d("HbpcdLookupProvider", "getType");
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x018b  */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.database.Cursor query(android.net.Uri r15, java.lang.String[] r16, java.lang.String r17, java.lang.String[] r18, java.lang.String r19) {
        /*
            Method dump skipped, instruction units count: 438
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.HbpcdLookupProvider.query(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("Failed to insert row into " + uri);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("Cannot delete URL: " + uri);
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        SQLiteDatabase writableDatabase = this.mDbHelper.getWritableDatabase();
        if (sURIMatcher.match(uri) == 2) {
            return writableDatabase.update("mcc_lookup_table", contentValues, str, strArr);
        }
        throw new UnsupportedOperationException("Cannot update URL: " + uri);
    }
}
