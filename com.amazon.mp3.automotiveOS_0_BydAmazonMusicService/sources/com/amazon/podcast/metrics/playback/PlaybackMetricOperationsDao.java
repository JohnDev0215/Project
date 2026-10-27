package com.amazon.podcast.metrics.playback;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
@Dao
public interface PlaybackMetricOperationsDao {
    @Query("DELETE FROM playback_metric_operations WHERE operationId IN (:ids)")
    void deleteIds(final List<String> ids);

    @Query("SELECT * FROM playback_metric_operations WHERE processed == 0")
    List<PlaybackMetricOperations> getUnProcessed();

    @Insert(onConflict = 1)
    void insert(PlaybackMetricOperations playbackMetric);

    @Query("UPDATE playback_metric_operations SET processed = 1 WHERE operationId IN (:ids)")
    void updateToProcessing(final List<String> ids);
}
