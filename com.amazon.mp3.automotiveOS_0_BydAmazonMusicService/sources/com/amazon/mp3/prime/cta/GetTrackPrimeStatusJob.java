package com.amazon.mp3.prime.cta;

import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.service.job.Job;

/* JADX INFO: loaded from: classes4.dex */
public class GetTrackPrimeStatusJob extends Job {
    private ContentCatalogStatus mCatalogStatus;
    private final SQLiteDatabase mDb;
    private ContentOwnershipStatus mOwnershipStatus;
    private final String mTrackAsin;

    public GetTrackPrimeStatusJob(SQLiteDatabase db, String trackAsin) {
        this.mDb = db;
        this.mTrackAsin = trackAsin;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0044 A[Catch: all -> 0x0072, TryCatch #0 {all -> 0x0072, blocks: (B:4:0x0021, B:6:0x0027, B:7:0x0044, B:9:0x004e, B:11:0x0053, B:13:0x0065, B:14:0x006a, B:10:0x0051), top: B:20:0x0021 }] */
    @Override // com.amazon.mp3.service.job.Job
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int run() throws java.lang.Exception {
        /*
            r11 = this;
            java.lang.String r0 = "prime_status"
            java.lang.String r1 = "ownership_status"
            java.lang.String[] r4 = new java.lang.String[]{r0, r1}
            r10 = 1
            java.lang.String[] r6 = new java.lang.String[r10]
            java.lang.String r2 = r11.mTrackAsin
            r3 = 0
            r6[r3] = r2
            android.database.sqlite.SQLiteDatabase r2 = r11.mDb
            java.lang.String r3 = "Track"
            java.lang.String r5 = "asin=?"
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9)
            if (r2 == 0) goto L44
            boolean r3 = r2.moveToFirst()     // Catch: java.lang.Throwable -> L72
            if (r3 == 0) goto L44
            int r0 = r2.getColumnIndexOrThrow(r0)     // Catch: java.lang.Throwable -> L72
            int r0 = r2.getInt(r0)     // Catch: java.lang.Throwable -> L72
            int r1 = r2.getColumnIndexOrThrow(r1)     // Catch: java.lang.Throwable -> L72
            int r1 = r2.getInt(r1)     // Catch: java.lang.Throwable -> L72
            com.amazon.mp3.prime.ContentCatalogStatus r0 = com.amazon.mp3.prime.ContentCatalogStatus.fromValue(r0)     // Catch: java.lang.Throwable -> L72
            r11.mCatalogStatus = r0     // Catch: java.lang.Throwable -> L72
            com.amazon.mp3.prime.ContentOwnershipStatus r0 = com.amazon.mp3.prime.ContentOwnershipStatus.fromValue(r1)     // Catch: java.lang.Throwable -> L72
            r11.mOwnershipStatus = r0     // Catch: java.lang.Throwable -> L72
            goto L6e
        L44:
            com.amazon.mp3.account.details.AccountDetailUtil r0 = com.amazon.mp3.account.details.AccountDetailUtil.get()     // Catch: java.lang.Throwable -> L72
            boolean r0 = r0.isPrimeMusicSupported()     // Catch: java.lang.Throwable -> L72
            if (r0 == 0) goto L51
            com.amazon.mp3.prime.ContentCatalogStatus r0 = com.amazon.mp3.prime.ContentCatalogStatus.PRIME     // Catch: java.lang.Throwable -> L72
            goto L53
        L51:
            com.amazon.mp3.prime.ContentCatalogStatus r0 = com.amazon.mp3.prime.ContentCatalogStatus.HAWKFIRE     // Catch: java.lang.Throwable -> L72
        L53:
            r11.mCatalogStatus = r0     // Catch: java.lang.Throwable -> L72
            android.app.Application r0 = r11.getContext()     // Catch: java.lang.Throwable -> L72
            com.amazon.mp3.prime.cta.AddRemovePrimeManager r0 = com.amazon.mp3.prime.cta.AddRemovePrimeManager.getInstance(r0)     // Catch: java.lang.Throwable -> L72
            java.lang.String r1 = r11.mTrackAsin     // Catch: java.lang.Throwable -> L72
            boolean r0 = r0.isQueuedToAdd(r1)     // Catch: java.lang.Throwable -> L72
            if (r0 == 0) goto L6a
            com.amazon.mp3.prime.ContentOwnershipStatus r0 = com.amazon.mp3.prime.ContentOwnershipStatus.ADDED     // Catch: java.lang.Throwable -> L72
            r11.mOwnershipStatus = r0     // Catch: java.lang.Throwable -> L72
            goto L6e
        L6a:
            com.amazon.mp3.prime.ContentOwnershipStatus r0 = com.amazon.mp3.prime.ContentOwnershipStatus.NOT_IN_LIBRARY     // Catch: java.lang.Throwable -> L72
            r11.mOwnershipStatus = r0     // Catch: java.lang.Throwable -> L72
        L6e:
            com.amazon.mp3.util.DbUtil.closeCursor(r2)
            return r10
        L72:
            r0 = move-exception
            com.amazon.mp3.util.DbUtil.closeCursor(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.prime.cta.GetTrackPrimeStatusJob.run():int");
    }
}
