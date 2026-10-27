package com.android.common.content;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;
import android.net.Uri;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class SQLiteContentProvider extends ContentProvider implements SQLiteTransactionListener {
    private static final int MAX_OPERATIONS_PER_YIELD_POINT = 500;
    private static final int SLEEP_AFTER_YIELD_DELAY = 4000;
    private static final String TAG = "SQLiteContentProvider";
    private final ThreadLocal<Boolean> mApplyingBatch = new ThreadLocal<>();
    protected SQLiteDatabase mDb;
    private volatile boolean mNotifyChange;
    private SQLiteOpenHelper mOpenHelper;

    protected abstract int deleteInTransaction(Uri uri, String str, String[] strArr);

    protected abstract SQLiteOpenHelper getDatabaseHelper(Context context);

    protected abstract Uri insertInTransaction(Uri uri, ContentValues contentValues);

    protected abstract void notifyChange();

    protected abstract int updateInTransaction(Uri uri, ContentValues contentValues, String str, String[] strArr);

    public int getMaxOperationsPerYield() {
        return 500;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        Context context = getContext();
        this.mOpenHelper = getDatabaseHelper(context);
        return true;
    }

    public SQLiteOpenHelper getDatabaseHelper() {
        return this.mOpenHelper;
    }

    private boolean applyingBatch() {
        return this.mApplyingBatch.get() != null && this.mApplyingBatch.get().booleanValue();
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        Uri result;
        boolean applyingBatch = applyingBatch();
        if (!applyingBatch) {
            SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
            this.mDb = writableDatabase;
            writableDatabase.beginTransactionWithListener(this);
            try {
                result = insertInTransaction(uri, values);
                if (result != null) {
                    this.mNotifyChange = true;
                }
                this.mDb.setTransactionSuccessful();
                this.mDb.endTransaction();
                onEndTransaction();
            } catch (Throwable th) {
                this.mDb.endTransaction();
                throw th;
            }
        } else {
            result = insertInTransaction(uri, values);
            if (result != null) {
                this.mNotifyChange = true;
            }
        }
        return result;
    }

    @Override // android.content.ContentProvider
    public int bulkInsert(Uri uri, ContentValues[] values) {
        int numValues = values.length;
        SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        this.mDb = writableDatabase;
        writableDatabase.beginTransactionWithListener(this);
        for (ContentValues contentValues : values) {
            try {
                Uri result = insertInTransaction(uri, contentValues);
                if (result != null) {
                    this.mNotifyChange = true;
                }
                boolean savedNotifyChange = this.mNotifyChange;
                SQLiteDatabase savedDb = this.mDb;
                this.mDb.yieldIfContendedSafely();
                this.mDb = savedDb;
                this.mNotifyChange = savedNotifyChange;
            } catch (Throwable th) {
                this.mDb.endTransaction();
                throw th;
            }
        }
        this.mDb.setTransactionSuccessful();
        this.mDb.endTransaction();
        onEndTransaction();
        return numValues;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count;
        boolean applyingBatch = applyingBatch();
        if (!applyingBatch) {
            SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
            this.mDb = writableDatabase;
            writableDatabase.beginTransactionWithListener(this);
            try {
                count = updateInTransaction(uri, values, selection, selectionArgs);
                if (count > 0) {
                    this.mNotifyChange = true;
                }
                this.mDb.setTransactionSuccessful();
                this.mDb.endTransaction();
                onEndTransaction();
            } catch (Throwable th) {
                this.mDb.endTransaction();
                throw th;
            }
        } else {
            count = updateInTransaction(uri, values, selection, selectionArgs);
            if (count > 0) {
                this.mNotifyChange = true;
            }
        }
        return count;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count;
        boolean applyingBatch = applyingBatch();
        if (!applyingBatch) {
            SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
            this.mDb = writableDatabase;
            writableDatabase.beginTransactionWithListener(this);
            try {
                count = deleteInTransaction(uri, selection, selectionArgs);
                if (count > 0) {
                    this.mNotifyChange = true;
                }
                this.mDb.setTransactionSuccessful();
                this.mDb.endTransaction();
                onEndTransaction();
            } catch (Throwable th) {
                this.mDb.endTransaction();
                throw th;
            }
        } else {
            count = deleteInTransaction(uri, selection, selectionArgs);
            if (count > 0) {
                this.mNotifyChange = true;
            }
        }
        return count;
    }

    @Override // android.content.ContentProvider
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> operations) throws OperationApplicationException {
        int ypCount = 0;
        int opCount = 0;
        SQLiteDatabase writableDatabase = this.mOpenHelper.getWritableDatabase();
        this.mDb = writableDatabase;
        writableDatabase.beginTransactionWithListener(this);
        try {
            this.mApplyingBatch.set(true);
            int numOperations = operations.size();
            ContentProviderResult[] results = new ContentProviderResult[numOperations];
            for (int i = 0; i < numOperations; i++) {
                opCount++;
                if (opCount > getMaxOperationsPerYield()) {
                    throw new OperationApplicationException("Too many content provider operations between yield points. The maximum number of operations per yield point is 500", ypCount);
                }
                ContentProviderOperation operation = operations.get(i);
                if (i > 0 && operation.isYieldAllowed()) {
                    opCount = 0;
                    boolean savedNotifyChange = this.mNotifyChange;
                    if (this.mDb.yieldIfContendedSafely(4000L)) {
                        this.mDb = this.mOpenHelper.getWritableDatabase();
                        this.mNotifyChange = savedNotifyChange;
                        ypCount++;
                    }
                }
                results[i] = operation.apply(this, results, i);
            }
            this.mDb.setTransactionSuccessful();
            return results;
        } finally {
            this.mApplyingBatch.set(false);
            this.mDb.endTransaction();
            onEndTransaction();
        }
    }

    @Override // android.database.sqlite.SQLiteTransactionListener
    public void onBegin() {
        onBeginTransaction();
    }

    @Override // android.database.sqlite.SQLiteTransactionListener
    public void onCommit() {
        beforeTransactionCommit();
    }

    @Override // android.database.sqlite.SQLiteTransactionListener
    public void onRollback() {
    }

    protected void onBeginTransaction() {
    }

    protected void beforeTransactionCommit() {
    }

    protected void onEndTransaction() {
        if (this.mNotifyChange) {
            this.mNotifyChange = false;
            notifyChange();
        }
    }
}
