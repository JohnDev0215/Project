package com.amazon.avod.userdownload.internal.database;

import com.amazon.avod.db.DBConstraint;
import com.amazon.avod.db.DBTable;
import com.amazon.avod.db.ForeignKeyConstraint;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadsTable implements DBTable {
    private static final ImmutableMap<String, String> COLUMNS = getColumnsForVersion(31);
    private static final ImmutableMap<String, String> INDICES = getIndexesForVersion(31);
    private static final ImmutableList<DBConstraint> CONSTRAINTS = getConstraintsForVersion(31);

    @Override // com.amazon.avod.db.DBTable
    public String getCleanUpWhereClause() {
        return null;
    }

    @Override // com.amazon.avod.db.DBTable
    public String getTableName() {
        return "playback_download";
    }

    private static class Constraints_V1 {
        public static final DBConstraint DRM_FOREIGN_KEY = new ForeignKeyConstraint("drm_asset_id", "drm");

        private Constraints_V1() {
        }
    }

    public static ImmutableMap<String, String> getColumnsForVersion(int i) {
        ImmutableMap.Builder builderPut = new ImmutableMap.Builder().put("offer_asin", "TEXT PRIMARY KEY").put("download_state", "TEXT not null").put("download_error_code", "TEXT").put("qos_session_id", "TEXT").put("media_quality", "TEXT not null").put("audio_format", "TEXT not null").put("audio_track_ids", "TEXT not null").put("storage_path_type", "TEXT not null").put("storage_path", "TEXT not null").put("file_size_kb", "INTEGER not null").put("percent_downloaded", "INTEGER not null").put("manifest_url", "TEXT").put("drm_asset_id", "TEXT").put("audio_track_metadata_json", "TEXT").put("download_store_type", "TEXT not null DEFAULT ''").put("relative_storage_path", "TEXT").put("session_context", "TEXT NOT NULL DEFAULT ''").put("player_sdk_playback_token_id", "TEXT").put("player_sdk_audio_stream_matchers_id", "TEXT").put("player_sdk_timed_text_stream_matchers_id", "TEXT");
        if (i >= 28) {
            builderPut.put("is_ready_to_watch", "INTEGER NOT NULL DEFAULT 0");
        }
        if (i >= 29) {
            builderPut.put("actual_runtime_ms", "INTEGER");
        }
        if (i >= 30) {
            builderPut.put("cuepoint_playlist", "TEXT");
        }
        return builderPut.build();
    }

    public static ImmutableMap<String, String> getIndexesForVersion(int i) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        builder.put("playback_downloads_asin_index", String.format(Locale.US, "(%s)", "offer_asin"));
        return builder.build();
    }

    public static ImmutableList<DBConstraint> getConstraintsForVersion(int i) {
        ImmutableList.Builder builder = ImmutableList.builder();
        builder.add(Constraints_V1.DRM_FOREIGN_KEY);
        return builder.build();
    }

    @Override // com.amazon.avod.db.DBTable
    public ImmutableMap<String, String> getColumns() {
        return COLUMNS;
    }

    @Override // com.amazon.avod.db.DBTable
    public ImmutableMap<String, String> getIndexes() {
        return INDICES;
    }

    @Override // com.amazon.avod.db.DBTable
    public ImmutableList<DBConstraint> getConstraints() {
        return CONSTRAINTS;
    }
}
