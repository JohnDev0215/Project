package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import org.apache.commons.lang3.time.StopWatch;

/* JADX INFO: loaded from: classes3.dex */
public class DatabaseMigrationMetricsProvider {
    public void sendDbMigrationCompletedEvent(final DatabaseMigrationStats dbMigrationStats, final StopWatch dbMigrationStopWatch) {
        MetricsLogger.sendEvent(createDbMigrationCompletedEvent(dbMigrationStats, dbMigrationStopWatch));
    }

    public void sendDbMigrationFailedEvent(final DatabaseMigrationStats dbMigrationStats, final StopWatch dbMigrationStopWatch) {
        MetricsLogger.sendEvent(createDbMigrationFailedEvent(dbMigrationStats, dbMigrationStopWatch));
    }

    @VisibleForTesting
    FlexEvent createDbMigrationCompletedEvent(final DatabaseMigrationStats dbMigrationStats, final StopWatch dbMigrationStopWatch) {
        return FlexEvent.builder("androidDbMigrationCompleted").withFlexNum1(Float.valueOf(dbMigrationStats.getTotalNumberOfTracksMigrated())).withFlexNum2(Float.valueOf(dbMigrationStats.getTotalNumberOfPlaylistsMigrated())).withFlexNum3(Float.valueOf(dbMigrationStopWatch.getTime())).build();
    }

    @VisibleForTesting
    FlexEvent createDbMigrationFailedEvent(@Nullable final DatabaseMigrationStats dbMigrationStats, final StopWatch dbMigrationStopWatch) {
        FlexEvent.Builder builder = FlexEvent.builder("androidDbMigrationFailed");
        if (dbMigrationStats != null) {
            builder.withFlexStr1(String.valueOf(dbMigrationStats.getTotalNumberOfTracksToMigrate())).withFlexStr2(String.valueOf(dbMigrationStats.getTotalNumberOfPlaylistsToMigrate())).withFlexNum1(Float.valueOf(dbMigrationStats.getTotalNumberOfTracksMigrated())).withFlexNum2(Float.valueOf(dbMigrationStats.getTotalNumberOfPlaylistsMigrated()));
        }
        builder.withFlexNum3(Float.valueOf(dbMigrationStopWatch.getTime()));
        return builder.build();
    }
}
