package com.amazon.avod.media.framework.network;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.amazon.avod.db.DBOpenHelperSupplier;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class NetworkHistoryPersistence {
    private static final TimeSpan MAX_RECORD_AGE = TimeSpan.fromHours(336);
    private final DBOpenHelperSupplier mDBOpenHelperSupplier;

    public NetworkHistoryPersistence(Context context) {
        this(new DBOpenHelperSupplier(context, new NetworkHistoryDatabaseUpgradeManager(), new NetworkHistoryDatabase()));
    }

    NetworkHistoryPersistence(DBOpenHelperSupplier dBOpenHelperSupplier) {
        this.mDBOpenHelperSupplier = dBOpenHelperSupplier;
    }

    /* JADX WARN: Not initialized variable reg: 4, insn: 0x00a0: MOVE (r22 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:35:0x00a0 */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0096 A[PHI: r4
  0x0096: PHI (r4v5 android.database.Cursor) = (r4v3 android.database.Cursor), (r4v4 android.database.Cursor), (r4v9 android.database.Cursor) binds: [B:26:0x0087, B:30:0x0094, B:18:0x0074] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a4  */
    @javax.annotation.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.avod.media.framework.network.NetworkHistory getNetworkHistory(java.lang.String r24) throws java.lang.Throwable {
        /*
            r23 = this;
            java.lang.String r0 = "bandwidth"
            java.lang.String r1 = "heuristics_state"
            java.lang.String r2 = "heuristic_bandwidth"
            java.lang.String r3 = "heuristic_bandwidth_stdev"
            java.lang.String[] r6 = new java.lang.String[]{r0, r1, r2, r3}
            r12 = 1
            java.lang.String[] r8 = new java.lang.String[r12]
            r13 = 0
            r8[r13] = r24
            r22 = 0
            r15 = r23
            com.amazon.avod.db.DBOpenHelperSupplier r4 = r15.mDBOpenHelperSupplier     // Catch: java.lang.Throwable -> L77 android.database.SQLException -> L79 android.database.sqlite.SQLiteException -> L88
            com.amazon.avod.db.DBOpenHelper r4 = r4.get()     // Catch: java.lang.Throwable -> L77 android.database.SQLException -> L79 android.database.sqlite.SQLiteException -> L88
            android.database.sqlite.SQLiteDatabase r4 = r4.getReadableDatabase()     // Catch: java.lang.Throwable -> L77 android.database.SQLException -> L79 android.database.sqlite.SQLiteException -> L88
            java.lang.String r5 = "access_point_table"
            java.lang.String r7 = "access_point_id=?"
            r9 = 0
            r10 = 0
            r11 = 0
            android.database.Cursor r4 = r4.query(r5, r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L77 android.database.SQLException -> L79 android.database.sqlite.SQLiteException -> L88
            if (r4 == 0) goto L74
            boolean r5 = r4.moveToNext()     // Catch: android.database.SQLException -> L70 android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L9f
            if (r5 == 0) goto L74
            int r0 = r4.getColumnIndex(r0)     // Catch: android.database.SQLException -> L70 android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L9f
            int r16 = r4.getInt(r0)     // Catch: android.database.SQLException -> L70 android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L9f
            int r0 = r4.getColumnIndex(r1)     // Catch: android.database.SQLException -> L70 android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L9f
            byte[] r0 = r4.getBlob(r0)     // Catch: android.database.SQLException -> L70 android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L9f
            int r1 = r4.getColumnIndex(r2)     // Catch: android.database.SQLException -> L70 android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L9f
            int r1 = r4.getInt(r1)     // Catch: android.database.SQLException -> L70 android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L9f
            int r2 = r4.getColumnIndex(r3)     // Catch: android.database.SQLException -> L70 android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L9f
            int r2 = r4.getInt(r2)     // Catch: android.database.SQLException -> L70 android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L9f
            if (r0 == 0) goto L5c
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.wrap(r0)     // Catch: android.database.SQLException -> L70 android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L9f
            r17 = r0
            goto L5e
        L5c:
            r17 = r22
        L5e:
            com.amazon.avod.media.framework.network.NetworkHistory r0 = new com.amazon.avod.media.framework.network.NetworkHistory     // Catch: android.database.SQLException -> L70 android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L9f
            long r5 = (long) r1     // Catch: android.database.SQLException -> L70 android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L9f
            long r1 = (long) r2     // Catch: android.database.SQLException -> L70 android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L9f
            r14 = r0
            r15 = r24
            r18 = r5
            r20 = r1
            r14.<init>(r15, r16, r17, r18, r20)     // Catch: android.database.SQLException -> L70 android.database.sqlite.SQLiteException -> L72 java.lang.Throwable -> L9f
            r4.close()
            return r0
        L70:
            r0 = move-exception
            goto L7c
        L72:
            r0 = move-exception
            goto L8b
        L74:
            if (r4 == 0) goto L99
            goto L96
        L77:
            r0 = move-exception
            goto La2
        L79:
            r0 = move-exception
            r4 = r22
        L7c:
            java.lang.String r1 = "Exception thrown while getting network history. %s"
            java.lang.Object[] r2 = new java.lang.Object[r12]     // Catch: java.lang.Throwable -> L9f
            r2[r13] = r0     // Catch: java.lang.Throwable -> L9f
            com.amazon.avod.util.DLog.warnf(r1, r2)     // Catch: java.lang.Throwable -> L9f
            if (r4 == 0) goto L99
            goto L96
        L88:
            r0 = move-exception
            r4 = r22
        L8b:
            java.lang.String r1 = "Could not open writable database. %s"
            java.lang.Object[] r2 = new java.lang.Object[r12]     // Catch: java.lang.Throwable -> L9f
            r2[r13] = r0     // Catch: java.lang.Throwable -> L9f
            com.amazon.avod.util.DLog.warnf(r1, r2)     // Catch: java.lang.Throwable -> L9f
            if (r4 == 0) goto L99
        L96:
            r4.close()
        L99:
            java.lang.String r0 = "It looks like we have an active network, but we could not load statistics for it."
            com.amazon.avod.util.DLog.logf(r0)
            return r22
        L9f:
            r0 = move-exception
            r22 = r4
        La2:
            if (r22 == 0) goto La7
            r22.close()
        La7:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.media.framework.network.NetworkHistoryPersistence.getNetworkHistory(java.lang.String):com.amazon.avod.media.framework.network.NetworkHistory");
    }

    public void putNetworkHistory(@Nonnull NetworkHistory networkHistory) {
        Preconditions.checkNotNull(networkHistory);
        try {
            SQLiteDatabase writableDatabase = this.mDBOpenHelperSupplier.get().getWritableDatabase();
            ContentValues contentValues = getContentValues(networkHistory);
            if (writableDatabase.update("access_point_table", contentValues, "access_point_id=?", new String[]{networkHistory.getNetworkName()}) <= 0 && writableDatabase.insert("access_point_table", null, contentValues) < 0) {
                DLog.errorf("Failed to add entry to network statistics database.");
            }
        } catch (SQLiteException e) {
            DLog.warnf("Could not open writable database. %s", e);
        } catch (SQLException e2) {
            DLog.warnf("Exception thrown while saving network history. %s", e2);
        }
        trimOldEntries();
    }

    private static ContentValues getContentValues(NetworkHistory networkHistory) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("access_point_id", networkHistory.getNetworkName());
        contentValues.put("bandwidth", Integer.valueOf(networkHistory.getAverageBandwidthBps()));
        contentValues.put("heuristic_bandwidth", Long.valueOf(networkHistory.getHeuristicAverageBandwidthBps()));
        contentValues.put("heuristic_bandwidth_stdev", Long.valueOf(networkHistory.getHeuristicStdevBandwidthBps()));
        ByteBuffer heuristicsState = networkHistory.getHeuristicsState();
        if (heuristicsState != null) {
            byte[] bArr = new byte[heuristicsState.capacity()];
            heuristicsState.get(bArr);
            contentValues.put("heuristics_state", bArr);
        }
        contentValues.put("last_used", Long.valueOf(System.currentTimeMillis() / 1000));
        return contentValues;
    }

    private void trimOldEntries() {
        try {
            int iDelete = this.mDBOpenHelperSupplier.get().getWritableDatabase().delete("access_point_table", "last_used<?", new String[]{Long.toString((System.currentTimeMillis() / 1000) - ((long) MAX_RECORD_AGE.getTotalSeconds()))});
            if (iDelete > 0) {
                DLog.logf("Trimmed %d old network statistics from the database", Integer.valueOf(iDelete));
            }
        } catch (SQLiteException e) {
            DLog.warnf("Could not open writable database. %s", e);
        } catch (SQLException e2) {
            DLog.warnf("Exception thrown while trimming old network history. %s", e2);
        }
    }
}
