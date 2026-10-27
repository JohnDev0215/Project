package com.amazon.avod.media.events.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteFullException;
import androidx.annotation.Size;
import com.amazon.avod.db.DBOpenHelperSupplier;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.util.DLog;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.skyfire.models.TetheredMessageKey;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SQLiteMediaEventDAO implements MediaEventDAO {
    private static final ReadWriteLock DB_LOCK = new ReentrantReadWriteLock();
    private final AloysiusConfig mAloysiusConfig;
    private final SQLiteDatabase mReadableConnection;
    private final String mSelectAllFields;
    private final MediaEventTable mTable;
    private final SQLiteDatabase mWritableConnection;

    public SQLiteMediaEventDAO(@Nonnull DBOpenHelperSupplier dBOpenHelperSupplier) {
        this(AloysiusConfig.getInstance(), dBOpenHelperSupplier.get().getReadableDatabase(), dBOpenHelperSupplier.get().getWritableDatabase());
    }

    SQLiteMediaEventDAO(@Nonnull AloysiusConfig aloysiusConfig, @Nonnull SQLiteDatabase sQLiteDatabase, @Nonnull SQLiteDatabase sQLiteDatabase2) {
        this.mTable = MediaEventsDatabase.MEDIA_EVENT_TABLE;
        this.mSelectAllFields = Joiner.on(",").join("sequence", "appInstanceId", TetheredMessageKey.timestamp, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "payload");
        this.mAloysiusConfig = (AloysiusConfig) Preconditions.checkNotNull(aloysiusConfig, "aloysiusConfig");
        this.mReadableConnection = (SQLiteDatabase) Preconditions.checkNotNull(sQLiteDatabase, "readableConnection");
        this.mWritableConnection = (SQLiteDatabase) Preconditions.checkNotNull(sQLiteDatabase2, "writableConnection");
    }

    @Override // com.amazon.avod.media.events.dao.MediaEventDAO
    public long getLatestId() {
        ReadWriteLock readWriteLock = DB_LOCK;
        readWriteLock.readLock().lock();
        try {
            try {
                Cursor cursorRawQuery = this.mReadableConnection.rawQuery("SELECT MAX(sequence) FROM mediaEvent", null);
                try {
                    long longFromCursor = readLongFromCursor(cursorRawQuery);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    readWriteLock.readLock().unlock();
                    return longFromCursor;
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
                DB_LOCK.readLock().unlock();
                return -1L;
            }
        } catch (Throwable th4) {
            DB_LOCK.readLock().unlock();
            throw th4;
        }
    }

    @Override // com.amazon.avod.media.events.dao.MediaEventDAO
    @Nonnull
    public MediaEventRecord insert(@Nonnull MediaEventRecord mediaEventRecord) {
        ReadWriteLock readWriteLock = DB_LOCK;
        readWriteLock.writeLock().lock();
        try {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("sequence", Long.valueOf(mediaEventRecord.getSequence()));
                contentValues.put("appInstanceId", mediaEventRecord.getAppInstanceId());
                contentValues.put(TetheredMessageKey.timestamp, Long.valueOf(mediaEventRecord.getTimeStamp()));
                contentValues.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, mediaEventRecord.getType());
                contentValues.put("payload", mediaEventRecord.getPayload());
                this.mWritableConnection.insert(this.mTable.getTableName(), null, contentValues);
            } catch (SQLiteFullException e) {
                handleFatalDatabaseErrors(e);
                mediaEventRecord = new MediaEventRecord(0L, "", 0L, "", "");
                readWriteLock = DB_LOCK;
            }
            readWriteLock.writeLock().unlock();
            return mediaEventRecord;
        } catch (Throwable th) {
            DB_LOCK.writeLock().unlock();
            throw th;
        }
    }

    @Override // com.amazon.avod.media.events.dao.MediaEventDAO
    public int remove(@Nonnull String str, @Size(min = 1) @Nonnull List<Long> list) {
        Preconditions.checkArgument(!list.isEmpty(), "sequenceIds must have at least one element");
        DB_LOCK.writeLock().lock();
        try {
            Iterator it = Lists.partition(list, this.mAloysiusConfig.getSQLiteMaxVariableCount()).iterator();
            int iRemoveInternal = 0;
            while (it.hasNext()) {
                iRemoveInternal += removeInternal(str, (List) it.next());
            }
            return iRemoveInternal;
        } catch (SQLiteFullException e) {
            handleFatalDatabaseErrors(e);
            return 0;
        } finally {
            DB_LOCK.writeLock().unlock();
        }
    }

    private int removeInternal(@Nonnull String str, @Size(min = 1) @Nonnull List<Long> list) {
        Preconditions.checkArgument(!list.isEmpty(), "sequenceIds must have at least one element");
        String str2 = String.format(Locale.ENGLISH, "%s = ? AND %s IN (?%s)", "appInstanceId", "sequence", Strings.repeat(",?", list.size() - 1));
        int size = list.size() + 1;
        String[] strArr = new String[size];
        strArr[0] = str;
        for (int i = 1; i < size; i++) {
            strArr[i] = list.get(i - 1).toString();
        }
        return this.mWritableConnection.delete(this.mTable.getTableName(), str2, strArr);
    }

    @Override // com.amazon.avod.media.events.dao.MediaEventDAO
    @Nonnull
    public List<MediaEventRecord> getOldestEvents(long j) {
        ReadWriteLock readWriteLock = DB_LOCK;
        readWriteLock.readLock().lock();
        try {
            try {
                Cursor cursorRawQuery = this.mReadableConnection.rawQuery(String.format(Locale.ENGLISH, "SELECT %s FROM %s WHERE %s is not null ORDER BY %s ASC LIMIT ?", this.mSelectAllFields, this.mTable.getTableName(), "appInstanceId", "sequence"), new String[]{String.valueOf(j)});
                try {
                    List<MediaEventRecord> listFromCursor = readListFromCursor(cursorRawQuery, null);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    readWriteLock.readLock().unlock();
                    return listFromCursor;
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

    @Override // com.amazon.avod.media.events.dao.MediaEventDAO
    @Nonnull
    public List<MediaEventRecord> getNonNullAppInstanceId() {
        ReadWriteLock readWriteLock = DB_LOCK;
        readWriteLock.readLock().lock();
        try {
            try {
                Cursor cursorRawQuery = this.mReadableConnection.rawQuery(String.format(Locale.ENGLISH, "SELECT %s FROM %s WHERE %s is not null", this.mSelectAllFields, this.mTable.getTableName(), "appInstanceId"), null);
                try {
                    List<MediaEventRecord> listFromCursor = readListFromCursor(cursorRawQuery, null);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    readWriteLock.readLock().unlock();
                    return listFromCursor;
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

    @Override // com.amazon.avod.media.events.dao.MediaEventDAO
    @Nonnull
    public List<MediaEventRecord> backfillAppInstanceId(@Nonnull String str) {
        ReadWriteLock readWriteLock = DB_LOCK;
        readWriteLock.writeLock().lock();
        this.mWritableConnection.beginTransaction();
        try {
            try {
                Cursor cursorRawQuery = this.mWritableConnection.rawQuery(String.format(Locale.ENGLISH, "SELECT %s FROM %s WHERE %s is null", this.mSelectAllFields, this.mTable.getTableName(), "appInstanceId"), null);
                try {
                    List<MediaEventRecord> listFromCursor = readListFromCursor(cursorRawQuery, str);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("appInstanceId", str);
                    DLog.logf("setting %s appInstanceId on %d records", str, Integer.valueOf(listFromCursor.size()));
                    DLog.logf("%d records updated", Integer.valueOf(this.mWritableConnection.update(this.mTable.getTableName(), contentValues, "appInstanceId is null", null)));
                    this.mWritableConnection.setTransactionSuccessful();
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    this.mWritableConnection.endTransaction();
                    readWriteLock.writeLock().unlock();
                    return listFromCursor;
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
                this.mWritableConnection.endTransaction();
                DB_LOCK.writeLock().unlock();
                return arrayListNewArrayList;
            }
        } catch (Throwable th4) {
            this.mWritableConnection.endTransaction();
            DB_LOCK.writeLock().unlock();
            throw th4;
        }
    }

    @Override // com.amazon.avod.media.events.dao.MediaEventDAO
    public long fastGetDBSize(long j, boolean z) {
        long jFastGetNonNullAppInstanceIdDBSize;
        Lock lock;
        ReadWriteLock readWriteLock = DB_LOCK;
        readWriteLock.readLock().lock();
        try {
            try {
                if (z) {
                    jFastGetNonNullAppInstanceIdDBSize = fastGetAllDBSize(j);
                } else {
                    jFastGetNonNullAppInstanceIdDBSize = fastGetNonNullAppInstanceIdDBSize(j);
                }
                lock = readWriteLock.readLock();
            } catch (SQLiteFullException e) {
                handleFatalDatabaseErrors(e);
                jFastGetNonNullAppInstanceIdDBSize = 0;
                lock = DB_LOCK.readLock();
            }
            lock.unlock();
            return jFastGetNonNullAppInstanceIdDBSize;
        } catch (Throwable th) {
            DB_LOCK.readLock().unlock();
            throw th;
        }
    }

    private long fastGetAllDBSize(long j) {
        Cursor cursorRawQuery = this.mReadableConnection.rawQuery(String.format(Locale.ENGLISH, "SELECT COUNT(*) FROM (SELECT %s FROM %s LIMIT %d)", "sequence", this.mTable.getTableName(), Long.valueOf(j)), null);
        try {
            long longFromCursor = readLongFromCursor(cursorRawQuery);
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            return longFromCursor;
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
    }

    private long fastGetNonNullAppInstanceIdDBSize(long j) {
        Cursor cursorRawQuery = this.mReadableConnection.rawQuery(String.format(Locale.ENGLISH, "SELECT COUNT(*) FROM (SELECT %s FROM %s WHERE %s is not null LIMIT %d)", "sequence", this.mTable.getTableName(), "appInstanceId", Long.valueOf(j)), null);
        try {
            long longFromCursor = readLongFromCursor(cursorRawQuery);
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            return longFromCursor;
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
    }

    @Override // com.amazon.avod.media.events.dao.MediaEventDAO
    public int clear() {
        ReadWriteLock readWriteLock = DB_LOCK;
        readWriteLock.writeLock().lock();
        try {
            try {
                int iDelete = this.mWritableConnection.delete(this.mTable.getTableName(), null, null);
                readWriteLock.writeLock().unlock();
                return iDelete;
            } catch (SQLiteFullException e) {
                handleFatalDatabaseErrors(e);
                DB_LOCK.writeLock().unlock();
                return 0;
            }
        } catch (Throwable th) {
            DB_LOCK.writeLock().unlock();
            throw th;
        }
    }

    private long readLongFromCursor(@Nonnull Cursor cursor) {
        if (cursor.moveToFirst()) {
            return cursor.getLong(0);
        }
        return 0L;
    }

    @Nonnull
    private List<MediaEventRecord> readListFromCursor(@Nonnull Cursor cursor, @Nullable String str) {
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        while (cursor.moveToNext()) {
            arrayListNewArrayList.add(new MediaEventRecord(cursor.getLong(0), str != null ? str : cursor.getString(1), cursor.getLong(2), cursor.getString(3), cursor.getString(4)));
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
