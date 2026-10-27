package com.amazon.avod.media.contentcache.db;

import androidx.core.app.NotificationCompat;
import com.amazon.avod.db.DBConstraint;
import com.amazon.avod.db.DBTable;
import com.amazon.avod.db.UniquenessConstraint;
import com.amazon.music.find.utils.PageUriUtils;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
class CachedContentTable implements DBTable {
    private static final ImmutableMap<String, String> COLUMNS = ImmutableMap.builder().put("_id", "INTEGER primary key autoincrement").put("title_id", "TEXT").put("audio_format", "TEXT").put("media_quality", "TEXT").put("start_position", "INTEGER").put("end_position", "INTEGER").put(ImagesContract.URL, "TEXT").put("encode_version", "TEXT").put("session_id", "TEXT").put("cdn", "TEXT").put("priority", "INTEGER").put(PageUriUtils.SOURCE_QUERY_PARAM, "TEXT").put(NotificationCompat.CATEGORY_STATUS, "TEXT").put("is_trailer", "TEXT").put("target_duration", "INTEGER").put("retry_attempts", "INTEGER").put("filesize_kb", "INTEGER").put("initial_source", "TEXT").put("is_licensable", "TEXT").put("download_time_seconds", "INTEGER").put("audio_track_ids", "STRING").put("all_sources", "STRING").put("urls_json", "TEXT").put("is_deferrable", "TEXT").put("offline_keyid", "TEXT").put("audio_track_metadata_json", "TEXT").put("returned_title_rendition", "TEXT").put("content_type", "TEXT").put("failover_map", "TEXT").put("playback_token", "TEXT").put("cuepoint_playlist_info_json", "TEXT").put("playback_settings", "TEXT").put("aux_cache_key", "TEXT").put("correlation_id", "TEXT").put("playback_envelope", "TEXT").put("eprivacy_cookie_consent_gvl", "TEXT").put("eprivacy_cookie_consent_avl", "TEXT").build();
    private static final ImmutableList<DBConstraint> CONSTRAINTS;
    private static final ImmutableMap<String, String> INDICES;
    private static final DBConstraint SECONDARY_KEY_CONSTRAINT;

    @Override // com.amazon.avod.db.DBTable
    public String getCleanUpWhereClause() {
        return null;
    }

    @Override // com.amazon.avod.db.DBTable
    public String getTableName() {
        return "cached_content_table";
    }

    CachedContentTable() {
    }

    static {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        Locale locale = Locale.US;
        INDICES = builder.put("index_title_id_start_position", String.format(locale, "(%s, %s)", "title_id", "start_position")).put("index_priority", String.format(locale, "(%s)", "priority")).put("status_priority", String.format(locale, "(%s)", NotificationCompat.CATEGORY_STATUS)).build();
        UniquenessConstraint uniquenessConstraint = new UniquenessConstraint(ImmutableList.of("title_id", "is_trailer", "start_position", "audio_track_ids"), "REPLACE");
        SECONDARY_KEY_CONSTRAINT = uniquenessConstraint;
        CONSTRAINTS = ImmutableList.of(uniquenessConstraint);
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
