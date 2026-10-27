package com.amazon.music.curate.skyfire.local;

import android.content.SharedPreferences;
import com.amazon.music.curate.data.database.AppDatabase;
import com.amazon.music.curate.skyfire.Curate;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class SyncScheduler {
    private static final String TAG = "SyncScheduler";
    private static final Logger logger = LoggerFactory.getLogger(SyncScheduler.class.getSimpleName());

    public static boolean shouldSyncContextMappingTable() {
        SharedPreferences sharedPreferences = Curate.getSharedPreferences();
        if (sharedPreferences == null) {
            return true;
        }
        return new Date().getTime() - sharedPreferences.getLong(ContextMappingConstants.CURATE_CONTEXT_MAPPING_TABLE_LAST_SYNC_TIME_KEY, 0L) > ContextMappingConstants.CURATE_CONTEXT_MAPPING_TABLE_SYNC_FREQUENCY_MS;
    }

    public static void saveContextMappingTableLastSync(final long lastSyncTime) {
        SharedPreferences sharedPreferences = Curate.getSharedPreferences();
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putLong(ContextMappingConstants.CURATE_CONTEXT_MAPPING_TABLE_LAST_SYNC_TIME_KEY, lastSyncTime);
        editorEdit.apply();
        logger.debug("Saved context mapping table last sync");
    }

    public static void resetContextMappingTableLastSync() {
        SharedPreferences sharedPreferences = Curate.getSharedPreferences();
        if (sharedPreferences == null) {
            return;
        }
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putLong(ContextMappingConstants.CURATE_CONTEXT_MAPPING_TABLE_LAST_SYNC_TIME_KEY, 0L);
        editorEdit.apply();
        logger.debug("Reset context mapping table last sync");
    }

    public static boolean isContextMappingEmpty(final AppDatabase appDatabase) {
        return CollectionUtils.isEmpty(appDatabase.libraryContextMappingDao().getMappingsByPageContext(ContextMappingConstants.LIBRARY_PAGE_CONTEXT_VALUE));
    }
}
