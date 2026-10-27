package com.android.providers.userdictionary;

import android.app.backup.BackupManager;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Binder;
import android.os.Process;
import android.os.UserHandle;
import android.provider.UserDictionary;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.SpellCheckerInfo;
import android.view.textservice.TextServicesManager;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class UserDictionaryProvider extends ContentProvider {
    private static ArrayMap<String, String> sDictProjectionMap;
    private static final UriMatcher sUriMatcher;
    private BackupManager mBackupManager;
    private InputMethodManager mImeManager;
    private DatabaseHelper mOpenHelper;
    private TextServicesManager mTextServiceManager;

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        sUriMatcher = uriMatcher;
        uriMatcher.addURI("user_dictionary", "words", 1);
        sUriMatcher.addURI("user_dictionary", "words/#", 2);
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        sDictProjectionMap = arrayMap;
        arrayMap.put("_id", "_id");
        sDictProjectionMap.put("word", "word");
        sDictProjectionMap.put("frequency", "frequency");
        sDictProjectionMap.put("locale", "locale");
        sDictProjectionMap.put("appid", "appid");
        sDictProjectionMap.put("shortcut", "shortcut");
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, "user_dict.db", (SQLiteDatabase.CursorFactory) null, 2);
            setIdleConnectionTimeout(30000L);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE words (_id INTEGER PRIMARY KEY,word TEXT,frequency INTEGER,locale TEXT,appid INTEGER,shortcut TEXT);");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i == 1 && i2 == 2) {
                Log.i("UserDictionaryProvider", "Upgrading database from version " + i + " to version 2: adding shortcut column");
                sQLiteDatabase.execSQL("ALTER TABLE words ADD shortcut TEXT;");
                return;
            }
            Log.w("UserDictionaryProvider", "Upgrading database from version " + i + " to " + i2 + ", which will destroy all old data");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS words");
            onCreate(sQLiteDatabase);
        }
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.mOpenHelper = new DatabaseHelper(getContext());
        this.mBackupManager = new BackupManager(getContext());
        this.mImeManager = (InputMethodManager) getContext().getSystemService(InputMethodManager.class);
        this.mTextServiceManager = (TextServicesManager) getContext().getSystemService(TextServicesManager.class);
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (!canCallerAccessUserDictionary()) {
            return getEmptyCursorOrThrow(strArr);
        }
        SQLiteQueryBuilder sQLiteQueryBuilder = new SQLiteQueryBuilder();
        int iMatch = sUriMatcher.match(uri);
        if (iMatch == 1) {
            sQLiteQueryBuilder.setTables("words");
            sQLiteQueryBuilder.setProjectionMap(sDictProjectionMap);
        } else if (iMatch == 2) {
            sQLiteQueryBuilder.setTables("words");
            sQLiteQueryBuilder.setProjectionMap(sDictProjectionMap);
            sQLiteQueryBuilder.appendWhere("_id=" + uri.getPathSegments().get(1));
        } else {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "frequency DESC";
        }
        Cursor cursorQuery = sQLiteQueryBuilder.query(this.mOpenHelper.getReadableDatabase(), strArr, str, strArr2, null, null, str2);
        cursorQuery.setNotificationUri(getContext().getContentResolver(), uri);
        return cursorQuery;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int iMatch = sUriMatcher.match(uri);
        if (iMatch == 1) {
            return "vnd.android.cursor.dir/vnd.google.userword";
        }
        if (iMatch == 2) {
            return "vnd.android.cursor.item/vnd.google.userword";
        }
        throw new IllegalArgumentException("Unknown URI " + uri);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        ContentValues contentValues2;
        if (sUriMatcher.match(uri) != 1) {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (!canCallerAccessUserDictionary()) {
            return null;
        }
        if (contentValues != null) {
            contentValues2 = new ContentValues(contentValues);
        } else {
            contentValues2 = new ContentValues();
        }
        if (!contentValues2.containsKey("word")) {
            throw new SQLException("Word must be specified");
        }
        if (!contentValues2.containsKey("frequency")) {
            contentValues2.put("frequency", "1");
        }
        if (!contentValues2.containsKey("locale")) {
            contentValues2.put("locale", (String) null);
        }
        if (!contentValues2.containsKey("shortcut")) {
            contentValues2.put("shortcut", (String) null);
        }
        contentValues2.put("appid", (Integer) 0);
        long jInsert = this.mOpenHelper.getWritableDatabase().insert("words", "word", contentValues2);
        if (jInsert > 0) {
            Uri uriWithAppendedId = ContentUris.withAppendedId(UserDictionary.Words.CONTENT_URI, jInsert);
            getContext().getContentResolver().notifyChange(uriWithAppendedId, null);
            this.mBackupManager.dataChanged();
            return uriWithAppendedId;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        int iDelete;
        String str2;
        if (!canCallerAccessUserDictionary()) {
            return 0;
        }
        SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        int iMatch = sUriMatcher.match(uri);
        if (iMatch == 1) {
            iDelete = writableDatabase.delete("words", str, strArr);
        } else if (iMatch == 2) {
            String str3 = uri.getPathSegments().get(1);
            StringBuilder sb = new StringBuilder();
            sb.append("_id=");
            sb.append(str3);
            if (TextUtils.isEmpty(str)) {
                str2 = "";
            } else {
                str2 = " AND (" + str + ')';
            }
            sb.append(str2);
            iDelete = writableDatabase.delete("words", sb.toString(), strArr);
        } else {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        this.mBackupManager.dataChanged();
        return iDelete;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        int iUpdate;
        String str2;
        if (!canCallerAccessUserDictionary()) {
            return 0;
        }
        SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        int iMatch = sUriMatcher.match(uri);
        if (iMatch == 1) {
            iUpdate = writableDatabase.update("words", contentValues, str, strArr);
        } else if (iMatch == 2) {
            String str3 = uri.getPathSegments().get(1);
            StringBuilder sb = new StringBuilder();
            sb.append("_id=");
            sb.append(str3);
            if (TextUtils.isEmpty(str)) {
                str2 = "";
            } else {
                str2 = " AND (" + str + ')';
            }
            sb.append(str2);
            iUpdate = writableDatabase.update("words", contentValues, sb.toString(), strArr);
        } else {
            throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        this.mBackupManager.dataChanged();
        return iUpdate;
    }

    private boolean canCallerAccessUserDictionary() {
        int callingUid = Binder.getCallingUid();
        if (UserHandle.getAppId(callingUid) == 1000 || callingUid == 0 || callingUid == Process.myUid()) {
            return true;
        }
        String callingPackage = getCallingPackage();
        List<InputMethodInfo> enabledInputMethodList = this.mImeManager.getEnabledInputMethodList();
        if (enabledInputMethodList != null) {
            int size = enabledInputMethodList.size();
            for (int i = 0; i < size; i++) {
                InputMethodInfo inputMethodInfo = enabledInputMethodList.get(i);
                if (inputMethodInfo.getServiceInfo().applicationInfo.uid == callingUid && inputMethodInfo.getPackageName().equals(callingPackage)) {
                    return true;
                }
            }
        }
        SpellCheckerInfo[] enabledSpellCheckers = this.mTextServiceManager.getEnabledSpellCheckers();
        if (enabledSpellCheckers != null) {
            for (SpellCheckerInfo spellCheckerInfo : enabledSpellCheckers) {
                if (spellCheckerInfo.getServiceInfo().applicationInfo.uid == callingUid && spellCheckerInfo.getPackageName().equals(callingPackage)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Cursor getEmptyCursorOrThrow(String[] strArr) {
        if (strArr != null) {
            for (String str : strArr) {
                if (sDictProjectionMap.get(str) == null) {
                    throw new IllegalArgumentException("Unknown column: " + str);
                }
            }
        } else {
            int size = sDictProjectionMap.size();
            String[] strArr2 = new String[size];
            for (int i = 0; i < size; i++) {
                strArr2[i] = sDictProjectionMap.keyAt(i);
            }
            strArr = strArr2;
        }
        return new MatrixCursor(strArr, 0);
    }
}
