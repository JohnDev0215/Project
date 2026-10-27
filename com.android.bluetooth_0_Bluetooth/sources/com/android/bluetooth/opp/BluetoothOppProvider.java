package com.android.bluetooth.opp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.INetd;
import android.net.Uri;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothOppProvider extends ContentProvider {
    private static final boolean D = false;
    private static final String DB_NAME = "btopp.db";
    private static final String DB_TABLE = "btopp";
    private static final int DB_VERSION = 1;
    private static final int DB_VERSION_NOP_UPGRADE_FROM = 0;
    private static final int DB_VERSION_NOP_UPGRADE_TO = 1;
    private static final int SHARES = 1;
    private static final int SHARES_ID = 2;
    private static final String SHARE_LIST_TYPE = "vnd.android.cursor.dir/vnd.android.btopp";
    private static final String SHARE_TYPE = "vnd.android.cursor.item/vnd.android.btopp";
    private static final String TAG = "BluetoothOppProvider";
    private static final boolean V = false;
    private static final UriMatcher sURIMatcher;
    private SQLiteOpenHelper mOpenHelper = null;

    static {
        UriMatcher uriMatcher = new UriMatcher(-1);
        sURIMatcher = uriMatcher;
        uriMatcher.addURI("com.android.bluetooth.opp", DB_TABLE, 1);
        sURIMatcher.addURI("com.android.bluetooth.opp", "btopp/#", 2);
    }

    private final class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, BluetoothOppProvider.DB_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase db) {
            BluetoothOppProvider.this.createTable(db);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
            if (oldV == 0) {
                if (newV == 1) {
                    return;
                } else {
                    oldV = 1;
                }
            }
            Log.i(BluetoothOppProvider.TAG, "Upgrading downloads database from version " + oldV + " to " + newV + ", which will destroy all old data");
            BluetoothOppProvider.this.dropTable(db);
            BluetoothOppProvider.this.createTable(db);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createTable(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE btopp(_id INTEGER PRIMARY KEY AUTOINCREMENT,uri TEXT, hint TEXT, _data TEXT, mimetype TEXT, direction INTEGER, destination TEXT, visibility INTEGER, confirm INTEGER, status INTEGER, total_bytes INTEGER, current_bytes INTEGER, timestamp INTEGER,scanned INTEGER); ");
        } catch (SQLException ex) {
            Log.e(TAG, "createTable: Failed.");
            throw ex;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dropTable(SQLiteDatabase db) {
        try {
            db.execSQL("DROP TABLE IF EXISTS btopp");
        } catch (SQLException ex) {
            Log.e(TAG, "dropTable: Failed.");
            throw ex;
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int match = sURIMatcher.match(uri);
        if (match == 1) {
            return SHARE_LIST_TYPE;
        }
        if (match == 2) {
            return SHARE_TYPE;
        }
        throw new IllegalArgumentException("Unknown URI in getType(): " + uri);
    }

    private static void copyString(String key, ContentValues from, ContentValues to) {
        String s = from.getAsString(key);
        if (s != null) {
            to.put(key, s);
        }
    }

    private static void copyInteger(String key, ContentValues from, ContentValues to) {
        Integer i = from.getAsInteger(key);
        if (i != null) {
            to.put(key, i);
        }
    }

    private static void copyLong(String key, ContentValues from, ContentValues to) {
        Long i = from.getAsLong(key);
        if (i != null) {
            to.put(key, i);
        }
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        if (sURIMatcher.match(uri) != 1) {
            throw new IllegalArgumentException("insert: Unknown/Invalid URI " + uri);
        }
        ContentValues filteredValues = new ContentValues();
        copyString("uri", values, filteredValues);
        copyString(BluetoothShare.FILENAME_HINT, values, filteredValues);
        copyString(BluetoothShare.MIMETYPE, values, filteredValues);
        copyString(BluetoothShare.DESTINATION, values, filteredValues);
        copyInteger(BluetoothShare.VISIBILITY, values, filteredValues);
        copyLong(BluetoothShare.TOTAL_BYTES, values, filteredValues);
        if (values.getAsInteger(BluetoothShare.VISIBILITY) == null) {
            filteredValues.put(BluetoothShare.VISIBILITY, (Integer) 0);
        }
        Integer dir = values.getAsInteger(BluetoothShare.DIRECTION);
        int con = values.getAsInteger(BluetoothShare.USER_CONFIRMATION);
        if (dir == null) {
            dir = 0;
        }
        if (dir.intValue() == 0 && con == null) {
            con = 2;
        }
        if (dir.intValue() == 1 && con == null) {
            con = 0;
        }
        filteredValues.put(BluetoothShare.USER_CONFIRMATION, con);
        filteredValues.put(BluetoothShare.DIRECTION, dir);
        filteredValues.put("status", Integer.valueOf(BluetoothShare.STATUS_PENDING));
        filteredValues.put("scanned", (Integer) 0);
        Long ts = values.getAsLong("timestamp");
        if (ts == null) {
            ts = Long.valueOf(System.currentTimeMillis());
        }
        filteredValues.put("timestamp", ts);
        Context context = getContext();
        long rowID = db.insert(DB_TABLE, null, filteredValues);
        if (rowID == -1) {
            Log.w(TAG, "couldn't insert " + uri + "into btopp database");
            return null;
        }
        context.getContentResolver().notifyChange(uri, null);
        return Uri.parse(BluetoothShare.CONTENT_URI + "/" + rowID);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.mOpenHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = this.mOpenHelper.getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setStrict(true);
        int match = sURIMatcher.match(uri);
        if (match == 1) {
            qb.setTables(DB_TABLE);
        } else if (match == 2) {
            qb.setTables(DB_TABLE);
            qb.appendWhere("_id=");
            qb.appendWhere(uri.getPathSegments().get(1));
        } else {
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        Cursor ret = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        if (ret == null) {
            Log.w(TAG, "query failed in downloads database");
            return null;
        }
        ret.setNotificationUri(getContext().getContentResolver(), uri);
        return ret;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        String myWhere;
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        int count = 0;
        int match = sURIMatcher.match(uri);
        if (match == 1 || match == 2) {
            if (selection == null) {
                myWhere = INetd.NEXTHOP_NONE;
            } else if (match == 1) {
                myWhere = "( " + selection + " )";
            } else {
                myWhere = "( " + selection + " ) AND ";
            }
            if (match == 2) {
                String segment = uri.getPathSegments().get(1);
                long rowId = Long.parseLong(segment);
                myWhere = myWhere + " ( _id = " + rowId + " ) ";
            }
            if (values.size() > 0) {
                count = db.update(DB_TABLE, values, myWhere, selectionArgs);
            }
            getContext().getContentResolver().notifyChange(uri, null);
            return count;
        }
        throw new UnsupportedOperationException("Cannot update unknown URI: " + uri);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        String myWhere;
        SQLiteDatabase db = this.mOpenHelper.getWritableDatabase();
        int match = sURIMatcher.match(uri);
        if (match == 1 || match == 2) {
            if (selection == null) {
                myWhere = INetd.NEXTHOP_NONE;
            } else if (match == 1) {
                myWhere = "( " + selection + " )";
            } else {
                myWhere = "( " + selection + " ) AND ";
            }
            if (match == 2) {
                String segment = uri.getPathSegments().get(1);
                long rowId = Long.parseLong(segment);
                myWhere = myWhere + " ( _id = " + rowId + " ) ";
            }
            int count = db.delete(DB_TABLE, myWhere, selectionArgs);
            getContext().getContentResolver().notifyChange(uri, null);
            return count;
        }
        throw new UnsupportedOperationException("Cannot delete unknown URI: " + uri);
    }
}
