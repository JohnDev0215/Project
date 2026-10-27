package com.amazon.mp3.download.widevineMigration;

import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WidevineMigrationTrackerMetrics.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u0016\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002J\u0006\u0010\u0007\u001a\u00020\u0005J6\u0010\u000e\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002J\u0006\u0010\u000f\u001a\u00020\u0005JN\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0010J\u001e\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002R\u001c\u0010\u001c\u001a\n \u001b*\u0004\u0018\u00010\u00100\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006 "}, d2 = {"Lcom/amazon/mp3/download/widevineMigration/WidevineMigrationTrackerMetrics;", "", "", "totalLibraryTracks", "totalCatalogPlaylistTracks", "", "sendMigrationAssessed", "sendMigrationAsssessmentFailed", "originalLibraryTracks", "originalCatalogPlaylistTracks", "toBeDownloadedLibraryTracks", "toBeDownloadedCatalogPlaylistTracks", "queuedForDownloadLibraryTracks", "queuedForDownloadCatalogPlaylistTracks", "sendMigrationInitiated", "sendMigrationQueued", "", "progressStatus", "remainingLibraryTracks", "remainingCatalogPlaylistTracks", "ineligibleLibraryTracks", "ineligibleCatalogPlaylisTracks", "downloadState", "lastError", "sendMigrationProgress", "successReason", "sendMigrationCompletedSuccessful", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class WidevineMigrationTrackerMetrics {
    public static final WidevineMigrationTrackerMetrics INSTANCE = new WidevineMigrationTrackerMetrics();
    private static final String TAG = WidevineMigrationTrackerMetrics.class.getSimpleName();

    private WidevineMigrationTrackerMetrics() {
    }

    public final void sendMigrationAssessed(int totalLibraryTracks, int totalCatalogPlaylistTracks) {
        MetricsLogger.sendEvent(FlexEvent.builder("androidWidevineMigrationAssessed").withFlexStr1("Success").withFlexNum1(Float.valueOf(totalLibraryTracks)).withFlexNum2(Float.valueOf(totalCatalogPlaylistTracks)).build());
        Log.debug(TAG, "MigrationAnalyzed: \nLibrary Tracks: %s \nCatalog Playlist Tracks: %s ", Integer.valueOf(totalLibraryTracks), Integer.valueOf(totalCatalogPlaylistTracks));
    }

    public final void sendMigrationAsssessmentFailed() {
        MetricsLogger.sendEvent(FlexEvent.builder("androidWidevineMigrationAssessed").withFlexStr1("Failure").build());
        Log.debug(TAG, "MigrationAnalysisFailed");
    }

    public final void sendMigrationInitiated(int originalLibraryTracks, int originalCatalogPlaylistTracks, int toBeDownloadedLibraryTracks, int toBeDownloadedCatalogPlaylistTracks, int queuedForDownloadLibraryTracks, int queuedForDownloadCatalogPlaylistTracks) {
        MetricsLogger.sendEvent(FlexEvent.builder("androidWidevineMigrationInitiated").withFlexNum1(Float.valueOf(originalLibraryTracks + originalCatalogPlaylistTracks)).withFlexNum2(Float.valueOf(toBeDownloadedLibraryTracks + toBeDownloadedCatalogPlaylistTracks)).withFlexNum3(Float.valueOf(queuedForDownloadLibraryTracks + queuedForDownloadCatalogPlaylistTracks)).build());
        Log.debug(TAG, "MigrationInitiated: \nOriginal Library Tracks: %s \nOriginal Catalog Playlist Tracks: %s \nTo be downloaded Library Tracks: %s \nTo be downloaded Catalog Playlist Tracks: %s \nQueued for download Library Tracks: %s \nQueued for download Catalog Playlist Tracks: %s", Integer.valueOf(originalLibraryTracks), Integer.valueOf(originalCatalogPlaylistTracks), Integer.valueOf(toBeDownloadedLibraryTracks), Integer.valueOf(toBeDownloadedCatalogPlaylistTracks), Integer.valueOf(queuedForDownloadLibraryTracks), Integer.valueOf(queuedForDownloadCatalogPlaylistTracks));
    }

    public final void sendMigrationQueued() {
        MetricsLogger.sendEvent(FlexEvent.builder("androidWidevineMigrationQueued").build());
        Log.debug(TAG, "MigrationQueued");
    }

    public final void sendMigrationProgress(String progressStatus, int originalLibraryTracks, int originalCatalogPlaylistTracks, int remainingLibraryTracks, int remainingCatalogPlaylistTracks, int ineligibleLibraryTracks, int ineligibleCatalogPlaylisTracks, String downloadState, String lastError) {
        Intrinsics.checkNotNullParameter(progressStatus, "progressStatus");
        Intrinsics.checkNotNullParameter(downloadState, "downloadState");
        Intrinsics.checkNotNullParameter(lastError, "lastError");
        MetricsLogger.sendEvent(FlexEvent.builder("androidWidevineMigrationProgress").withFlexStr1(progressStatus).withFlexNum1(Float.valueOf(originalLibraryTracks + originalCatalogPlaylistTracks)).withFlexNum2(Float.valueOf(remainingLibraryTracks + remainingCatalogPlaylistTracks)).withFlexNum3(Float.valueOf(ineligibleLibraryTracks + ineligibleCatalogPlaylisTracks)).withFlexStr2(downloadState).withFlexStr3(lastError).build());
        Log.debug(TAG, "MigrationProgress: \nProgress Reason %s \nOriginal Library Tracks: %s \nOriginal Catalog Playlist Tracks: %s \nRemaining Library Tracks: %s \nRemaining Catalog Playlist Tracks: %s \nIneligible Library Tracks: %s \nIneligible Catalog Playlist Tracks: %s \nDownload State: %s \nLast Item Error: %s", progressStatus, Integer.valueOf(originalLibraryTracks), Integer.valueOf(originalCatalogPlaylistTracks), Integer.valueOf(remainingLibraryTracks), Integer.valueOf(remainingCatalogPlaylistTracks), Integer.valueOf(ineligibleLibraryTracks), Integer.valueOf(ineligibleCatalogPlaylisTracks), downloadState, lastError);
    }

    public final void sendMigrationCompletedSuccessful(String successReason, int totalLibraryTracks, int totalCatalogPlaylistTracks) {
        Intrinsics.checkNotNullParameter(successReason, "successReason");
        MetricsLogger.sendEvent(FlexEvent.builder("androidWidevineMigrationCompleted").withFlexStr1(successReason).withFlexNum1(Float.valueOf(totalLibraryTracks)).withFlexNum2(Float.valueOf(totalCatalogPlaylistTracks)).build());
        Log.debug(TAG, "MigrationSuccessful: %s \nOriginal Library Tracks: %s \nOriginal Catalog Playlist Tracks: %s ", successReason, Integer.valueOf(totalLibraryTracks), Integer.valueOf(totalCatalogPlaylistTracks));
    }
}
