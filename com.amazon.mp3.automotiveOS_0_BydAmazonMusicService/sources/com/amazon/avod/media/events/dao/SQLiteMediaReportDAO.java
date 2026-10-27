package com.amazon.avod.media.events.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import androidx.annotation.VisibleForTesting;
import com.amazon.avod.db.DBOpenHelperSupplier;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.events.dao.MediaEventsDatabaseNoOpUpgradeManager;
import com.amazon.avod.util.DLog;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SQLiteMediaReportDAO implements MediaReportDAO {
    private static final ReadWriteLock DB_LOCK = new ReentrantReadWriteLock();
    private final AloysiusConfig mAloysiusConfig;
    private final Context mContext;
    private final DBOpenHelperSupplier mDBOpenHelper;
    private final SQLiteDatabase mReadableConnection;
    private final String mSelectAllFields;
    private final MediaReportTable mTable;
    private final SQLiteDatabase mWritableConnection;

    public SQLiteMediaReportDAO(@Nonnull DBOpenHelperSupplier dBOpenHelperSupplier, @Nonnull AloysiusConfig aloysiusConfig, @Nonnull Context context) {
        this(dBOpenHelperSupplier, dBOpenHelperSupplier.get().getReadableDatabase(), dBOpenHelperSupplier.get().getWritableDatabase(), aloysiusConfig, context);
    }

    @VisibleForTesting
    SQLiteMediaReportDAO(@Nonnull DBOpenHelperSupplier dBOpenHelperSupplier, @Nonnull SQLiteDatabase sQLiteDatabase, @Nonnull SQLiteDatabase sQLiteDatabase2, @Nonnull AloysiusConfig aloysiusConfig, @Nonnull Context context) {
        this.mTable = MediaEventsDatabase.MEDIA_REPORT_TABLE;
        this.mSelectAllFields = Joiner.on(",").join("id", "appInstanceId", "payload");
        this.mDBOpenHelper = (DBOpenHelperSupplier) Preconditions.checkNotNull(dBOpenHelperSupplier, "dbOpenHelper");
        this.mReadableConnection = (SQLiteDatabase) Preconditions.checkNotNull(sQLiteDatabase, "readableConnection");
        this.mWritableConnection = (SQLiteDatabase) Preconditions.checkNotNull(sQLiteDatabase2, "writableConnection");
        this.mAloysiusConfig = (AloysiusConfig) Preconditions.checkNotNull(aloysiusConfig, "aloysiusConfig");
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
    }

    @Override // com.amazon.avod.media.events.dao.MediaReportDAO
    @Nonnull
    public MediaReportRecord insert(@Nonnull MediaReportRecord mediaReportRecord) {
        ReadWriteLock readWriteLock = DB_LOCK;
        readWriteLock.writeLock().lock();
        try {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("appInstanceId", mediaReportRecord.getAppInstanceId());
                contentValues.put("payload", mediaReportRecord.getPayload());
                MediaReportRecord mediaReportRecord2 = new MediaReportRecord(Long.valueOf(this.mWritableConnection.insert(this.mTable.getTableName(), null, contentValues)), mediaReportRecord.getAppInstanceId(), mediaReportRecord.getPayload());
                readWriteLock.writeLock().unlock();
                return mediaReportRecord2;
            } catch (SQLiteFullException e) {
                handleFatalDatabaseErrors(e);
                MediaReportRecord mediaReportRecord3 = new MediaReportRecord(0L, "", "");
                DB_LOCK.writeLock().unlock();
                return mediaReportRecord3;
            }
        } catch (Throwable th) {
            DB_LOCK.writeLock().unlock();
            throw th;
        }
    }

    @Override // com.amazon.avod.media.events.dao.MediaReportDAO
    public boolean remove(long j) {
        Lock lockWriteLock;
        ReadWriteLock readWriteLock = DB_LOCK;
        readWriteLock.writeLock().lock();
        try {
            try {
                z = this.mWritableConnection.delete(this.mTable.getTableName(), String.format(Locale.ENGLISH, "%s = ?", "id"), new String[]{String.valueOf(j)}) > 0;
                lockWriteLock = readWriteLock.writeLock();
            } catch (SQLiteFullException e) {
                handleFatalDatabaseErrors(e);
                lockWriteLock = DB_LOCK.writeLock();
            }
            lockWriteLock.unlock();
            return z;
        } catch (Throwable th) {
            DB_LOCK.writeLock().unlock();
            throw th;
        }
    }

    @Override // com.amazon.avod.media.events.dao.MediaReportDAO
    @Nonnull
    public List<Long> getAllIds() {
        ReadWriteLock readWriteLock = DB_LOCK;
        readWriteLock.readLock().lock();
        try {
            try {
                Cursor cursorRawQuery = this.mReadableConnection.rawQuery(String.format(Locale.ENGLISH, "SELECT %s FROM %s ORDER BY %s ASC", "id", this.mTable.getTableName(), "id"), null);
                try {
                    List<Long> idListFromCursor = readIdListFromCursor(cursorRawQuery);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    readWriteLock.readLock().unlock();
                    return idListFromCursor;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (cursorRawQuery != null) {
                            try {
                                cursorRawQuery.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        }
                        throw th2;
                    }
                }
            } catch (SQLiteFullException e) {
                handleFatalDatabaseErrors(e);
                ArrayList arrayListNewArrayList = Lists.newArrayList();
                DB_LOCK.readLock().unlock();
                return arrayListNewArrayList;
            }
        } catch (Throwable th4) {
            DB_LOCK.readLock().unlock();
            throw th4;
        }
    }

    @Override // com.amazon.avod.media.events.dao.MediaReportDAO
    public void clear() {
        ReadWriteLock readWriteLock = DB_LOCK;
        readWriteLock.writeLock().lock();
        try {
            try {
                this.mDBOpenHelper.get().cleanupDB();
            } catch (SQLiteFullException e) {
                handleFatalDatabaseErrors(e);
                readWriteLock = DB_LOCK;
            }
            readWriteLock.writeLock().unlock();
        } catch (Throwable th) {
            DB_LOCK.writeLock().unlock();
            throw th;
        }
    }

    @Override // com.amazon.avod.media.events.dao.MediaReportDAO
    public void clearLegacyDatabase() {
        if (!this.mAloysiusConfig.mIsLegacyMediaEventsDatabaseCleared.getValue().booleanValue() && this.mAloysiusConfig.shouldClearLegacyMediaEventsDatabase()) {
            ReadWriteLock readWriteLock = DB_LOCK;
            readWriteLock.writeLock().lock();
            try {
                try {
                    DBOpenHelperSupplier dBOpenHelperSupplier = new DBOpenHelperSupplier(this.mContext.getApplicationContext(), new MediaEventsDatabaseNoOpUpgradeManager(this.mContext.getApplicationContext()), MediaEventsDatabaseNoOpUpgradeManager.MediaEventsDatabaseV1.getInstance());
                    DLog.warnf("SQLiteMediaReportDAO deleting the legacy V1 copy of MediaEvents database");
                    dBOpenHelperSupplier.deleteDatabase();
                    this.mAloysiusConfig.mIsLegacyMediaEventsDatabaseCleared.updateValue(Boolean.TRUE);
                } catch (Exception e) {
                    DLog.warnf("SQLiteMediaReportDAO Failed to clear legacy MediaEvents database: %s", e);
                    this.mAloysiusConfig.mIsLegacyMediaEventsDatabaseCleared.updateValue(Boolean.TRUE);
                    readWriteLock = DB_LOCK;
                }
                readWriteLock.writeLock().unlock();
            } catch (Throwable th) {
                this.mAloysiusConfig.mIsLegacyMediaEventsDatabaseCleared.updateValue(Boolean.TRUE);
                DB_LOCK.writeLock().unlock();
                throw th;
            }
        }
    }

    @Override // com.amazon.avod.media.events.dao.MediaReportDAO
    @Nullable
    public MediaReportRecord getById(long j) {
        ReadWriteLock readWriteLock = DB_LOCK;
        readWriteLock.readLock().lock();
        try {
            try {
                Cursor cursorRawQuery = this.mReadableConnection.rawQuery(String.format(Locale.ENGLISH, "SELECT %s FROM %s WHERE %s = ? ORDER BY %s ASC", this.mSelectAllFields, this.mTable.getTableName(), "id", "id"), new String[]{Long.toString(j)});
                try {
                    MediaReportRecord mediaReportRecord = (MediaReportRecord) Iterables.get(readListFromCursor(cursorRawQuery), 0, null);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    readWriteLock.readLock().unlock();
                    return mediaReportRecord;
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (cursorRawQuery != null) {
                            try {
                                cursorRawQuery.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        }
                        throw th2;
                    }
                }
            } catch (SQLiteFullException e) {
                handleFatalDatabaseErrors(e);
                MediaReportRecord mediaReportRecord2 = new MediaReportRecord(0L, "", "");
                DB_LOCK.readLock().unlock();
                return mediaReportRecord2;
            }
        } catch (Throwable th4) {
            DB_LOCK.readLock().unlock();
            throw th4;
        }
    }

    @Nonnull
    private List<MediaReportRecord> readListFromCursor(@Nonnull Cursor cursor) {
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        while (cursor.moveToNext()) {
            long j = cursor.getLong(0);
            arrayListNewArrayList.add(new MediaReportRecord(Long.valueOf(j), cursor.getString(1), cursor.getString(2)));
        }
        return arrayListNewArrayList;
    }

    @Nonnull
    private List<Long> readIdListFromCursor(@Nonnull Cursor cursor) {
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        while (cursor.moveToNext()) {
            arrayListNewArrayList.add(Long.valueOf(cursor.getLong(0)));
        }
        return arrayListNewArrayList;
    }

    private void handleFatalDatabaseErrors(Exception exc) {
        if (this.mAloysiusConfig.shouldSuppressSQLiteExceptionsInMediaDAO() && (exc instanceof SQLiteFullException)) {
            DLog.exceptionf(exc, "Swallowing SQLiteFullException. Unrecoverable data loss in the application. This will be messaged to the user on the next playback.", new Object[0]);
            return;
        }
        throw new RuntimeException("Aloysius SQLiteException", exc);
    }
}
