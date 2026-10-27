package com.amazon.podcast.metrics.ui;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
@Dao
public interface UiMetricOperationsDao {
    @Query("DELETE FROM ui_metric_operations WHERE operationId IN (:ids)")
    void deleteIds(final List<String> ids);

    @Query("SELECT * FROM ui_metric_operations WHERE isProcessed == 0")
    List<UiMetricOperations> getUnProcessed();

    @Insert(onConflict = 1)
    void insert(UiMetricOperations uiMetric);

    @Query("UPDATE ui_metric_operations SET isProcessed = 1 WHERE operationId IN (:ids)")
    void updateToProcessing(final List<String> ids);
}
