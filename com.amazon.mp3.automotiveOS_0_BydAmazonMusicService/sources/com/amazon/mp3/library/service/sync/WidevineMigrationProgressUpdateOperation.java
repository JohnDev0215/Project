package com.amazon.mp3.library.service.sync;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.widevineMigration.WidevineMigrationTracker;
import com.amazon.mp3.download.widevineMigration.WidevineMigrationTrackerMetrics;
import com.amazon.mp3.util.Log;
import com.facebook.react.bridge.BaseJavaModule;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WidevineMigrationProgressUpdateOperation.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\b\u0010\u0003\u001a\u00020\u0002H\u0014R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\u000b\u001a\n \n*\u0004\u0018\u00010\t0\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\t8\u0002X\u0082D¢\u0006\u0006\n\u0004\b\r\u0010\fR\u0014\u0010\u000e\u001a\u00020\t8\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u000e\u0010\fR\u0014\u0010\u000f\u001a\u00020\t8\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u000f\u0010\f¨\u0006\u0014"}, d2 = {"Lcom/amazon/mp3/library/service/sync/WidevineMigrationProgressUpdateOperation;", "Lcom/amazon/mp3/library/service/sync/SyncOperation;", "", BaseJavaModule.METHOD_TYPE_SYNC, "Landroid/database/sqlite/SQLiteDatabase;", "db", "Landroid/database/sqlite/SQLiteDatabase;", "getDb", "()Landroid/database/sqlite/SQLiteDatabase;", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Success_Reason", "Progress_Reason_Eligible", "Progress_Reason_InElgible", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Landroid/database/sqlite/SQLiteDatabase;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class WidevineMigrationProgressUpdateOperation extends SyncOperation {
    private final String Progress_Reason_Eligible;
    private final String Progress_Reason_InElgible;
    private final String Success_Reason;
    private final String TAG;
    private final SQLiteDatabase db;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WidevineMigrationProgressUpdateOperation(Context context, SQLiteDatabase db) {
        super(context, db);
        Intrinsics.checkNotNullParameter(db, "db");
        this.db = db;
        this.TAG = WidevineMigrationProgressUpdateOperation.class.getSimpleName();
        this.Success_Reason = "Successful";
        this.Progress_Reason_Eligible = "Eligible_Tracks_Pending";
        this.Progress_Reason_InElgible = "Only_Ineligible_Tracks_Pending";
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    protected int sync() {
        Log.debug(this.TAG, "Starting migration progress update operation to send the progress metrics");
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        WidevineMigrationTracker widevineMigrationTracker = new WidevineMigrationTracker(context, this.db);
        widevineMigrationTracker.refresh();
        MusicDownloader musicDownloader = MusicDownloader.getInstance(getContext());
        Intrinsics.checkNotNullExpressionValue(musicDownloader, "getInstance(context)");
        if (widevineMigrationTracker.getRemainingLibraryTracksCount() == 0 && widevineMigrationTracker.getRemainingCatalogPlaylistTracksCount() == 0) {
            SettingsUtil.setHasCompletedWidevineMigration(getContext(), true);
            musicDownloader.unregisterMigrationListener();
            WidevineMigrationTrackerMetrics.INSTANCE.sendMigrationCompletedSuccessful(this.Success_Reason, widevineMigrationTracker.getOriginalLibraryTracksCount(), widevineMigrationTracker.getOriginalCatalogPlaylistTracksCount());
            return 0;
        }
        String str = this.Progress_Reason_Eligible;
        if (widevineMigrationTracker.getRemainingLibraryTracksCount() == widevineMigrationTracker.getInEligibleLibraryTracksCount() && widevineMigrationTracker.getRemainingCatalogPlaylistTracksCount() == widevineMigrationTracker.getInEligibleCatalogPlaylistTracksCount()) {
            str = this.Progress_Reason_InElgible;
        }
        WidevineMigrationTrackerMetrics widevineMigrationTrackerMetrics = WidevineMigrationTrackerMetrics.INSTANCE;
        int originalLibraryTracksCount = widevineMigrationTracker.getOriginalLibraryTracksCount();
        int originalCatalogPlaylistTracksCount = widevineMigrationTracker.getOriginalCatalogPlaylistTracksCount();
        int remainingLibraryTracksCount = widevineMigrationTracker.getRemainingLibraryTracksCount();
        int remainingCatalogPlaylistTracksCount = widevineMigrationTracker.getRemainingCatalogPlaylistTracksCount();
        int inEligibleLibraryTracksCount = widevineMigrationTracker.getInEligibleLibraryTracksCount();
        int inEligibleCatalogPlaylistTracksCount = widevineMigrationTracker.getInEligibleCatalogPlaylistTracksCount();
        String downloadState = musicDownloader.getDownloadState();
        Intrinsics.checkNotNullExpressionValue(downloadState, "musicDownloader.downloadState");
        String lastError = musicDownloader.getLastError();
        Intrinsics.checkNotNullExpressionValue(lastError, "musicDownloader.lastError");
        widevineMigrationTrackerMetrics.sendMigrationProgress(str, originalLibraryTracksCount, originalCatalogPlaylistTracksCount, remainingLibraryTracksCount, remainingCatalogPlaylistTracksCount, inEligibleLibraryTracksCount, inEligibleCatalogPlaylistTracksCount, downloadState, lastError);
        return 0;
    }
}
