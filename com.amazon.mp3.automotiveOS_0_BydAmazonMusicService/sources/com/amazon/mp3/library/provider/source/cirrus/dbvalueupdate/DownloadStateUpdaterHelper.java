package com.amazon.mp3.library.provider.source.cirrus.dbvalueupdate;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadStateUpdaterHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/library/provider/source/cirrus/dbvalueupdate/DownloadStateUpdaterHelper;", "Lcom/amazon/mp3/library/provider/source/cirrus/dbvalueupdate/DownloadStateUpdater;", "context", "Landroid/content/Context;", "db", "Landroid/database/sqlite/SQLiteDatabase;", "(Landroid/content/Context;Landroid/database/sqlite/SQLiteDatabase;)V", "TAG", "", "kotlin.jvm.PlatformType", "getContext", "()Landroid/content/Context;", "update", "Lcom/amazon/mp3/library/provider/source/cirrus/dbvalueupdate/DownloadStateUpdateResult;", "cursor", "Landroid/database/Cursor;", "newState", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadStateUpdaterHelper implements DownloadStateUpdater {
    private final String TAG;
    private final Context context;
    private final SQLiteDatabase db;

    public DownloadStateUpdaterHelper(Context context, SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(db, "db");
        this.context = context;
        this.db = db;
        this.TAG = DownloadStateUpdaterHelper.class.getSimpleName();
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbvalueupdate.DownloadStateUpdater
    public DownloadStateUpdateResult update(Cursor cursor, int newState) {
        Intrinsics.checkNotNullParameter(cursor, "cursor");
        try {
            if (!cursor.moveToFirst()) {
                Log.verbose(this.TAG, "No downloaded tracks to update.");
                cursor.close();
                DownloadStateUpdateResult downloadStateUpdateResult = DownloadStateUpdateResult.NO_TRACKS_TO_UPDATE;
                CloseableKt.closeFinally(cursor, null);
                return downloadStateUpdateResult;
            }
            int count = cursor.getCount();
            int i = 0;
            int i2 = 0;
            while (!cursor.isAfterLast()) {
                MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(cursor);
                int iUpdate = track == null ? 0 : TrackDownloadStateUpdater.INSTANCE.update(this.db, track, newState);
                if (iUpdate > 0) {
                    i++;
                    getContext().getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
                }
                i2 += iUpdate;
                cursor.moveToNext();
            }
            Log.debug(this.TAG, "Updated the download states for " + i + " tracks out of " + count + " tracks, affecting " + i2 + " rows");
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(cursor, null);
            cursor.close();
            return DownloadStateUpdateResult.SUCCESS;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursor, th);
                throw th2;
            }
        }
    }
}
