package com.android.providers.telephony;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Binder;
import android.os.ParcelFileDescriptor;
import android.provider.Telephony;
import android.system.ErrnoException;
import android.system.Os;
import android.text.TextUtils;
import android.util.Log;
import com.android.providers.telephony.TelephonyBackupAgent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class MmsProvider extends ContentProvider {
    private static final UriMatcher sURLMatcher;
    private SQLiteOpenHelper mOpenHelper;

    public static String getPduTable(boolean z) {
        return z ? "pdu_restricted" : "pdu";
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        setAppOps(14, 15);
        this.mOpenHelper = MmsSmsDatabaseHelper.getInstanceForCe(getContext());
        TelephonyBackupAgent.DeferredSmsMmsRestoreService.startIfFilesExist(getContext());
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String str3;
        String str4;
        String pduTable = getPduTable(ProviderUtil.isAccessRestricted(getContext(), getCallingPackage(), Binder.getCallingUid()));
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        int iMatch = sURLMatcher.match(uri);
        switch (iMatch) {
            case 0:
                constructQueryForBox(sQLiteQueryBuilder, 0, pduTable);
                break;
            case 1:
                sQLiteQueryBuilder.setTables(pduTable);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getPathSegments().get(0));
                break;
            case 2:
                constructQueryForBox(sQLiteQueryBuilder, 1, pduTable);
                break;
            case 3:
            case 5:
            case 7:
            case 9:
                sQLiteQueryBuilder.setTables(pduTable);
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getPathSegments().get(1));
                sQLiteQueryBuilder.appendWhere(" AND msg_box=" + getMessageBoxByMatch(iMatch));
                break;
            case 4:
                constructQueryForBox(sQLiteQueryBuilder, 2, pduTable);
                break;
            case 6:
                constructQueryForBox(sQLiteQueryBuilder, 3, pduTable);
                break;
            case 8:
                constructQueryForBox(sQLiteQueryBuilder, 4, pduTable);
                break;
            case 10:
                sQLiteQueryBuilder.setTables("part");
                break;
            case 11:
                sQLiteQueryBuilder.setTables("part");
                sQLiteQueryBuilder.appendWhere("mid=" + uri.getPathSegments().get(0));
                break;
            case 12:
                sQLiteQueryBuilder.setTables("part");
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getPathSegments().get(1));
                break;
            case 13:
                sQLiteQueryBuilder.setTables("addr");
                sQLiteQueryBuilder.appendWhere("msg_id=" + uri.getPathSegments().get(0));
                break;
            case 14:
                sQLiteQueryBuilder.setTables("rate");
                break;
            case 15:
                sQLiteQueryBuilder.setTables("addr INNER JOIN (SELECT P1._id AS id1, P2._id AS id2, P3._id AS id3, ifnull(P2.st, 0) AS delivery_status, ifnull(P3.read_status, 0) AS read_status FROM " + pduTable + " P1 INNER JOIN " + pduTable + " P2 ON P1.m_id=P2.m_id AND P2.m_type=134 LEFT JOIN " + pduTable + " P3 ON P1.m_id=P3.m_id AND P3.m_type=136 UNION SELECT P1._id AS id1, P2._id AS id2, P3._id AS id3, ifnull(P2.st, 0) AS delivery_status, ifnull(P3.read_status, 0) AS read_status FROM " + pduTable + " P1 INNER JOIN " + pduTable + " P3 ON P1.m_id=P3.m_id AND P3.m_type=136 LEFT JOIN " + pduTable + " P2 ON P1.m_id=P2.m_id AND P2.m_type=134) T ON (msg_id=id2 AND type=151) OR (msg_id=id3 AND type=137)");
                StringBuilder sb = new StringBuilder();
                sb.append("T.id1 = ");
                sb.append(uri.getLastPathSegment());
                sQLiteQueryBuilder.appendWhere(sb.toString());
                sQLiteQueryBuilder.setDistinct(true);
                break;
            case 16:
                sQLiteQueryBuilder.setTables("addr join " + pduTable + " on " + pduTable + "._id = addr.msg_id");
                StringBuilder sb2 = new StringBuilder();
                sb2.append(pduTable);
                sb2.append("._id = ");
                sb2.append(uri.getLastPathSegment());
                sQLiteQueryBuilder.appendWhere(sb2.toString());
                sQLiteQueryBuilder.appendWhere(" AND addr.type = 151");
                break;
            case 17:
            default:
                Log.e("MmsProvider", "query: invalid request: " + uri);
                return null;
            case 18:
                sQLiteQueryBuilder.setTables("drm");
                sQLiteQueryBuilder.appendWhere("_id=" + uri.getLastPathSegment());
                break;
            case 19:
                sQLiteQueryBuilder.setTables(pduTable + " group by thread_id");
                break;
        }
        if (TextUtils.isEmpty(str2)) {
            if (sQLiteQueryBuilder.getTables().equals(pduTable)) {
                str4 = "date DESC";
            } else if (sQLiteQueryBuilder.getTables().equals("part")) {
                str4 = "seq";
            } else {
                str3 = null;
            }
            str3 = str4;
        } else {
            str3 = str2;
        }
        try {
            Cursor cursorQuery = sQLiteQueryBuilder.query(this.mOpenHelper.getReadableDatabase(), strArr, str, strArr2, null, null, str3);
            cursorQuery.setNotificationUri(getContext().getContentResolver(), uri);
            return cursorQuery;
        } catch (SQLiteException e) {
            Log.e("MmsProvider", "returning NULL cursor, query: " + uri, e);
            return null;
        }
    }

    private void constructQueryForBox(SQLiteQueryBuilder sQLiteQueryBuilder, int i, String str) {
        sQLiteQueryBuilder.setTables(str);
        if (i != 0) {
            sQLiteQueryBuilder.appendWhere("msg_box=" + i);
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int iMatch = sURLMatcher.match(uri);
        if (iMatch != 12) {
            switch (iMatch) {
                case 0:
                case 2:
                case 4:
                case 6:
                case 8:
                    return "vnd.android-dir/mms";
                case 1:
                case 3:
                case 5:
                case 7:
                case 9:
                    return "vnd.android/mms";
                default:
                    return "*/*";
            }
        }
        Cursor cursorQuery = this.mOpenHelper.getReadableDatabase().query("part", new String[]{"ct"}, "_id = ?", new String[]{uri.getLastPathSegment()}, null, null, null);
        if (cursorQuery != null) {
            try {
                if (cursorQuery.getCount() == 1 && cursorQuery.moveToFirst()) {
                    return cursorQuery.getString(0);
                }
                Log.e("MmsProvider", "cursor.count() != 1: " + uri);
            } finally {
                cursorQuery.close();
            }
        } else {
            Log.e("MmsProvider", "cursor == null: " + uri);
        }
        return "*/*";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0444  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x013a  */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14, types: [android.net.Uri, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v32 */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.net.Uri insert(android.net.Uri r26, android.content.ContentValues r27) {
        /*
            Method dump skipped, instruction units count: 1121
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.MmsProvider.insert(android.net.Uri, android.content.ContentValues):android.net.Uri");
    }

    private int getMessageBoxByMatch(int i) {
        switch (i) {
            case 2:
            case 3:
                return 1;
            case 4:
            case 5:
                return 2;
            case 6:
            case 7:
                return 3;
            case 8:
            case 9:
                return 4;
            default:
                throw new IllegalArgumentException("bad Arg: " + i);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00f3 A[ADDED_TO_REGION] */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int delete(android.net.Uri r9, java.lang.String r10, java.lang.String[] r11) {
        /*
            Method dump skipped, instruction units count: 282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.telephony.MmsProvider.delete(android.net.Uri, java.lang.String, java.lang.String[]):int");
    }

    static int deleteMessages(Context context, SQLiteDatabase sQLiteDatabase, String str, String[] strArr, Uri uri) {
        Cursor cursorQuery = sQLiteDatabase.query("pdu", new String[]{"_id"}, str, strArr, null, null, null);
        if (cursorQuery == null) {
            return 0;
        }
        try {
            if (cursorQuery.getCount() == 0) {
                return 0;
            }
            while (cursorQuery.moveToNext()) {
                deleteParts(sQLiteDatabase, "mid = ?", new String[]{String.valueOf(cursorQuery.getLong(0))});
            }
            cursorQuery.close();
            int iDelete = sQLiteDatabase.delete("pdu", str, strArr);
            if (iDelete > 0) {
                Intent intent = new Intent("android.intent.action.CONTENT_CHANGED");
                intent.putExtra("deleted_contents", uri);
                context.sendBroadcast(intent);
            }
            return iDelete;
        } finally {
            cursorQuery.close();
        }
    }

    private static int deleteParts(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        return deleteDataRows(sQLiteDatabase, "part", str, strArr);
    }

    private static int deleteTempDrmData(SQLiteDatabase sQLiteDatabase, String str, String[] strArr) {
        return deleteDataRows(sQLiteDatabase, "drm", str, strArr);
    }

    private static int deleteDataRows(SQLiteDatabase sQLiteDatabase, String str, String str2, String[] strArr) {
        Cursor cursorQuery = sQLiteDatabase.query(str, new String[]{"_data"}, str2, strArr, null, null, null);
        if (cursorQuery == null) {
            return 0;
        }
        try {
            if (cursorQuery.getCount() == 0) {
                return 0;
            }
            while (cursorQuery.moveToNext()) {
                try {
                    String string = cursorQuery.getString(0);
                    if (string != null) {
                        new File(string).delete();
                    }
                } catch (Throwable th) {
                    Log.e("MmsProvider", th.getMessage(), th);
                }
            }
            cursorQuery.close();
            return sQLiteDatabase.delete(str, str2, strArr);
        } finally {
            cursorQuery.close();
        }
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        boolean z;
        String str2;
        String lastPathSegment;
        String str3;
        ContentValues contentValues2;
        String str4;
        if (contentValues != null && contentValues.containsKey("_data")) {
            return 0;
        }
        int callingUid = Binder.getCallingUid();
        String callingPackage = getCallingPackage();
        int iMatch = sURLMatcher.match(uri);
        if (iMatch == 11 || iMatch == 12) {
            z = false;
            str2 = "part";
            lastPathSegment = null;
        } else if (iMatch != 20) {
            switch (iMatch) {
                case 0:
                case 2:
                case 4:
                case 6:
                case 8:
                    lastPathSegment = null;
                    break;
                case 1:
                case 3:
                case 5:
                case 7:
                case 9:
                    lastPathSegment = uri.getLastPathSegment();
                    break;
                default:
                    Log.w("MmsProvider", "Update operation for '" + uri + "' not implemented.");
                    return 0;
            }
            str2 = "pdu";
            z = true;
        } else {
            try {
                Os.chmod(getContext().getDir("parts", 0).getPath() + '/' + uri.getPathSegments().get(1), 420);
            } catch (ErrnoException e) {
                Log.e("MmsProvider", "Exception in chmod: " + e);
            }
            return 0;
        }
        if (str2.equals("pdu")) {
            filterUnsupportedKeys(contentValues);
            if (ProviderUtil.shouldRemoveCreator(contentValues, callingUid)) {
                Log.w("MmsProvider", callingPackage + " tries to update CREATOR");
                contentValues.remove("creator");
            }
            contentValues2 = new ContentValues(contentValues);
            if (lastPathSegment != null) {
                str3 = "_id=" + lastPathSegment;
            } else {
                str4 = str;
                str3 = null;
                int iUpdate = this.mOpenHelper.getWritableDatabase().update(str2, contentValues2, concatSelections(str4, str3), strArr);
                if (z && iUpdate > 0) {
                    notifyChange(uri, null);
                }
                return iUpdate;
            }
        } else {
            if (!str2.equals("part")) {
                return 0;
            }
            ContentValues contentValues3 = new ContentValues(contentValues);
            if (iMatch == 11) {
                str3 = "mid=" + uri.getPathSegments().get(0);
            } else if (iMatch != 12) {
                contentValues2 = contentValues3;
                str3 = null;
            } else {
                str3 = "_id=" + uri.getPathSegments().get(1);
            }
            contentValues2 = contentValues3;
        }
        str4 = str;
        int iUpdate2 = this.mOpenHelper.getWritableDatabase().update(str2, contentValues2, concatSelections(str4, str3), strArr);
        if (z) {
            notifyChange(uri, null);
        }
        return iUpdate2;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        int iMatch = sURLMatcher.match(uri);
        if (Log.isLoggable("MmsProvider", 2)) {
            Log.d("MmsProvider", "openFile: uri=" + uri + ", mode=" + str + ", match=" + iMatch);
        }
        if (iMatch != 12) {
            return null;
        }
        return safeOpenFileHelper(uri, str);
    }

    private ParcelFileDescriptor safeOpenFileHelper(Uri uri, String str) throws FileNotFoundException {
        Cursor cursorQuery = query(uri, new String[]{"_data"}, null, null, null);
        int count = cursorQuery != null ? cursorQuery.getCount() : 0;
        if (count != 1) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            if (count == 0) {
                throw new FileNotFoundException("No entry for " + uri);
            }
            throw new FileNotFoundException("Multiple items at " + uri);
        }
        cursorQuery.moveToFirst();
        int columnIndex = cursorQuery.getColumnIndex("_data");
        String string = columnIndex >= 0 ? cursorQuery.getString(columnIndex) : null;
        cursorQuery.close();
        if (string == null) {
            throw new FileNotFoundException("Column _data not found.");
        }
        File file = new File(string);
        try {
            if (!file.getCanonicalPath().startsWith(getContext().getDir("parts", 0).getCanonicalPath())) {
                Log.e("MmsProvider", "openFile: path " + file.getCanonicalPath() + " does not start with " + getContext().getDir("parts", 0).getCanonicalPath());
                return null;
            }
            return ParcelFileDescriptor.open(file, ParcelFileDescriptor.parseMode(str));
        } catch (IOException e) {
            Log.e("MmsProvider", "openFile: create path failed " + e, e);
            return null;
        }
    }

    private void filterUnsupportedKeys(ContentValues contentValues) {
        contentValues.remove("d_tm_tok");
        contentValues.remove("s_vis");
        contentValues.remove("r_chg");
        contentValues.remove("r_chg_dl_tok");
        contentValues.remove("r_chg_dl");
        contentValues.remove("r_chg_id");
        contentValues.remove("r_chg_sz");
        contentValues.remove("p_s_by");
        contentValues.remove("p_s_d");
        contentValues.remove("store");
        contentValues.remove("mm_st");
        contentValues.remove("mm_flg_tok");
        contentValues.remove("mm_flg");
        contentValues.remove("store_st");
        contentValues.remove("store_st_txt");
        contentValues.remove("stored");
        contentValues.remove("totals");
        contentValues.remove("mb_t");
        contentValues.remove("mb_t_tok");
        contentValues.remove("qt");
        contentValues.remove("mb_qt");
        contentValues.remove("mb_qt_tok");
        contentValues.remove("m_cnt");
        contentValues.remove("start");
        contentValues.remove("d_ind");
        contentValues.remove("e_des");
        contentValues.remove("limit");
        contentValues.remove("r_r_mod");
        contentValues.remove("r_r_mod_txt");
        contentValues.remove("st_txt");
        contentValues.remove("apl_id");
        contentValues.remove("r_apl_id");
        contentValues.remove("aux_apl_id");
        contentValues.remove("drm_c");
        contentValues.remove("adp_a");
        contentValues.remove("repl_id");
        contentValues.remove("cl_id");
        contentValues.remove("cl_st");
        contentValues.remove("_id");
    }

    private void notifyChange(Uri uri, Uri uri2) {
        Context context = getContext();
        if (uri2 != null) {
            context.getContentResolver().notifyChange(uri2, null, true, -1);
        }
        context.getContentResolver().notifyChange(Telephony.MmsSms.CONTENT_URI, null, true, -1);
        if (uri2 != null) {
            uri = uri2;
        }
        ProviderUtil.notifyIfNotDefaultSmsApp(uri, getCallingPackage(), context);
    }

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        sURLMatcher = uriMatcher;
        uriMatcher.addURI("mms", null, 0);
        sURLMatcher.addURI("mms", "#", 1);
        sURLMatcher.addURI("mms", "inbox", 2);
        sURLMatcher.addURI("mms", "inbox/#", 3);
        sURLMatcher.addURI("mms", "sent", 4);
        sURLMatcher.addURI("mms", "sent/#", 5);
        sURLMatcher.addURI("mms", "drafts", 6);
        sURLMatcher.addURI("mms", "drafts/#", 7);
        sURLMatcher.addURI("mms", "outbox", 8);
        sURLMatcher.addURI("mms", "outbox/#", 9);
        sURLMatcher.addURI("mms", "part", 10);
        sURLMatcher.addURI("mms", "#/part", 11);
        sURLMatcher.addURI("mms", "part/#", 12);
        sURLMatcher.addURI("mms", "#/addr", 13);
        sURLMatcher.addURI("mms", "rate", 14);
        sURLMatcher.addURI("mms", "report-status/#", 15);
        sURLMatcher.addURI("mms", "report-request/#", 16);
        sURLMatcher.addURI("mms", "drm", 17);
        sURLMatcher.addURI("mms", "drm/#", 18);
        sURLMatcher.addURI("mms", "threads", 19);
        sURLMatcher.addURI("mms", "resetFilePerm/*", 20);
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
}
