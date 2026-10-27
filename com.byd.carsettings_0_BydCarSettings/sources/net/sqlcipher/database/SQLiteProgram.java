package net.sqlcipher.database;

import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public abstract class SQLiteProgram extends SQLiteClosable {
    private static final String TAG = "SQLiteProgram";
    public boolean mClosed = false;
    private SQLiteCompiledSql mCompiledSql;

    @Deprecated
    public SQLiteDatabase mDatabase;
    public final String mSql;

    @Deprecated
    public long nHandle;

    @Deprecated
    public long nStatement;

    public SQLiteProgram(SQLiteDatabase sQLiteDatabase, String str) {
        this.nHandle = 0L;
        this.nStatement = 0L;
        this.mDatabase = sQLiteDatabase;
        String strTrim = str.trim();
        this.mSql = strTrim;
        sQLiteDatabase.acquireReference();
        sQLiteDatabase.addSQLiteClosable(this);
        this.nHandle = sQLiteDatabase.mNativeHandle;
        strTrim = strTrim.length() >= 6 ? strTrim.substring(0, 6) : strTrim;
        if (!strTrim.equalsIgnoreCase("INSERT") && !strTrim.equalsIgnoreCase("UPDATE") && !strTrim.equalsIgnoreCase("REPLAC") && !strTrim.equalsIgnoreCase("DELETE") && !strTrim.equalsIgnoreCase("SELECT")) {
            SQLiteCompiledSql sQLiteCompiledSql = new SQLiteCompiledSql(sQLiteDatabase, str);
            this.mCompiledSql = sQLiteCompiledSql;
            this.nStatement = sQLiteCompiledSql.nStatement;
            return;
        }
        SQLiteCompiledSql compiledStatementForSql = sQLiteDatabase.getCompiledStatementForSql(str);
        this.mCompiledSql = compiledStatementForSql;
        if (compiledStatementForSql == null) {
            SQLiteCompiledSql sQLiteCompiledSql2 = new SQLiteCompiledSql(sQLiteDatabase, str);
            this.mCompiledSql = sQLiteCompiledSql2;
            sQLiteCompiledSql2.acquire();
            sQLiteDatabase.addToCompiledQueries(str, this.mCompiledSql);
            if (SQLiteDebug.DEBUG_ACTIVE_CURSOR_FINALIZATION) {
                Log.v(TAG, "Created DbObj (id#" + this.mCompiledSql.nStatement + ") for sql: " + str);
            }
        } else if (!compiledStatementForSql.acquire()) {
            long j = this.mCompiledSql.nStatement;
            this.mCompiledSql = new SQLiteCompiledSql(sQLiteDatabase, str);
            if (SQLiteDebug.DEBUG_ACTIVE_CURSOR_FINALIZATION) {
                Log.v(TAG, "** possible bug ** Created NEW DbObj (id#" + this.mCompiledSql.nStatement + ") because the previously created DbObj (id#" + j + ") was not released for sql:" + str);
            }
        }
        this.nStatement = this.mCompiledSql.nStatement;
    }

    private final native void native_clear_bindings();

    private void releaseCompiledSqlIfNotInCache() {
        if (this.mCompiledSql == null) {
            return;
        }
        synchronized (this.mDatabase.mCompiledQueries) {
            if (this.mDatabase.mCompiledQueries.containsValue(this.mCompiledSql)) {
                this.mCompiledSql.release();
            } else {
                this.mCompiledSql.releaseSqlStatement();
                this.mCompiledSql = null;
                this.nStatement = 0L;
            }
        }
    }

    public void bindBlob(int i, byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("the bind value at index " + i + " is null");
        }
        if (this.mClosed) {
            throw new IllegalStateException("program already closed");
        }
        if (this.mDatabase.isOpen()) {
            acquireReference();
            try {
                native_bind_blob(i, bArr);
                return;
            } finally {
                releaseReference();
            }
        }
        throw new IllegalStateException("database " + this.mDatabase.getPath() + " already closed");
    }

    public void bindDouble(int i, double d2) {
        if (this.mClosed) {
            throw new IllegalStateException("program already closed");
        }
        if (this.mDatabase.isOpen()) {
            acquireReference();
            try {
                native_bind_double(i, d2);
                return;
            } finally {
                releaseReference();
            }
        }
        throw new IllegalStateException("database " + this.mDatabase.getPath() + " already closed");
    }

    public void bindLong(int i, long j) {
        if (this.mClosed) {
            throw new IllegalStateException("program already closed");
        }
        if (this.mDatabase.isOpen()) {
            acquireReference();
            try {
                native_bind_long(i, j);
                return;
            } finally {
                releaseReference();
            }
        }
        throw new IllegalStateException("database " + this.mDatabase.getPath() + " already closed");
    }

    public void bindNull(int i) {
        if (this.mClosed) {
            throw new IllegalStateException("program already closed");
        }
        if (this.mDatabase.isOpen()) {
            acquireReference();
            try {
                native_bind_null(i);
                return;
            } finally {
                releaseReference();
            }
        }
        throw new IllegalStateException("database " + this.mDatabase.getPath() + " already closed");
    }

    public void bindString(int i, String str) {
        if (str == null) {
            throw new IllegalArgumentException("the bind value at index " + i + " is null");
        }
        if (this.mClosed) {
            throw new IllegalStateException("program already closed");
        }
        if (this.mDatabase.isOpen()) {
            acquireReference();
            try {
                native_bind_string(i, str);
                return;
            } finally {
                releaseReference();
            }
        }
        throw new IllegalStateException("database " + this.mDatabase.getPath() + " already closed");
    }

    public void clearBindings() {
        if (this.mClosed) {
            throw new IllegalStateException("program already closed");
        }
        if (this.mDatabase.isOpen()) {
            acquireReference();
            try {
                native_clear_bindings();
                return;
            } finally {
                releaseReference();
            }
        }
        throw new IllegalStateException("database " + this.mDatabase.getPath() + " already closed");
    }

    public void close() {
        if (!this.mClosed && this.mDatabase.isOpen()) {
            this.mDatabase.lock();
            try {
                releaseReference();
                this.mDatabase.unlock();
                this.mClosed = true;
            } catch (Throwable th) {
                this.mDatabase.unlock();
                throw th;
            }
        }
    }

    @Deprecated
    public void compile(String str, boolean z) {
    }

    public String getSqlString() {
        return this.mSql;
    }

    public final long getUniqueId() {
        return this.nStatement;
    }

    public final native void native_bind_blob(int i, byte[] bArr);

    public final native void native_bind_double(int i, double d2);

    public final native void native_bind_long(int i, long j);

    public final native void native_bind_null(int i);

    public final native void native_bind_string(int i, String str);

    @Deprecated
    public final native void native_compile(String str);

    @Deprecated
    public final native void native_finalize();

    @Override // net.sqlcipher.database.SQLiteClosable
    public void onAllReferencesReleased() {
        releaseCompiledSqlIfNotInCache();
        this.mDatabase.releaseReference();
        this.mDatabase.removeSQLiteClosable(this);
    }

    @Override // net.sqlcipher.database.SQLiteClosable
    public void onAllReferencesReleasedFromContainer() {
        releaseCompiledSqlIfNotInCache();
        this.mDatabase.releaseReference();
    }
}
