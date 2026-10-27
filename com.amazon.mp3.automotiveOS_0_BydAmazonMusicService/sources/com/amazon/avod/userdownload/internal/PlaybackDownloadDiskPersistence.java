package com.amazon.avod.userdownload.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.avod.content.DownloadStoreType;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.content.urlvending.CuepointPlaylistInfo;
import com.amazon.avod.db.DBOpenHelperSupplier;
import com.amazon.avod.db.DBUtils;
import com.amazon.avod.drm.db.DrmRecord;
import com.amazon.avod.drm.db.DrmStoredRights;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.download.internal.PlaybackDownloadConfig;
import com.amazon.avod.media.download.internal.db.DownloadErrorUtils;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.userdownload.PlaybackDownload;
import com.amazon.avod.userdownload.PlaybackDownloadEventReporter;
import com.amazon.avod.userdownload.ProgressMilestone;
import com.amazon.avod.userdownload.UserDownloadLocation;
import com.amazon.avod.userdownload.UserDownloadState;
import com.amazon.avod.userdownload.internal.database.PlaybackDownloadsDatabase;
import com.amazon.avod.userdownload.internal.database.PlaybackDownloadsDatabaseUpgradeManager;
import com.amazon.avod.userdownload.internal.database.PlaybackDownloadsTable;
import com.amazon.avod.userdownload.internal.database.upgrade.DownloadsDBUpgradeActionFrom28To29;
import com.amazon.avod.util.CursorUtils;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.InitializationLatch;
import com.amazon.avod.util.Preconditions2;
import com.amazon.avod.util.Throwables2;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadDiskPersistence {
    private static final ImmutableMap<String, MediaQuality> QUALITY_DESERIALIZER;
    private static final ImmutableMap<MediaQuality, String> QUALITY_SERIALIZER;
    private final ImmutableMap<UserDownloadState, UserDownloadState> STATE_TO_PERSIST;
    private CollectionType mAudioTrackMetadataCollectionType;
    private DBOpenHelperSupplier mDBOpenHelperSupplier;
    private final DownloadErrorUtils mDownloadErrorUtils;
    private final InitializationLatch mInitializationLatch;
    private ObjectMapper mObjectMapper;
    private PlaybackDownloadEventReporter mPlaybackDownloadEventReporter;

    static {
        ImmutableMap<MediaQuality, String> immutableMap = (ImmutableMap) Preconditions2.checkFullKeyMapping(MediaQuality.class, ImmutableMap.of(MediaQuality.LOWEST, "LOWEST", MediaQuality.LOW, "LOW", MediaQuality.MEDIUM, "MEDIUM", MediaQuality.HIGH, "HIGH", MediaQuality.HIGHEST, "HIGHEST"));
        QUALITY_SERIALIZER = immutableMap;
        ImmutableMap.Builder builder = ImmutableMap.builder();
        UnmodifiableIterator<Map.Entry<MediaQuality, String>> it = immutableMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<MediaQuality, String> next = it.next();
            builder.put(next.getValue(), next.getKey());
        }
        QUALITY_DESERIALIZER = (ImmutableMap) Preconditions2.checkFullValueMapping(MediaQuality.class, builder.build());
    }

    public PlaybackDownloadDiskPersistence() {
        this(new DownloadErrorUtils());
    }

    PlaybackDownloadDiskPersistence(@Nonnull DownloadErrorUtils downloadErrorUtils) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        UserDownloadState userDownloadState = UserDownloadState.QUEUEING;
        UserDownloadState userDownloadState2 = UserDownloadState.QUEUED;
        ImmutableMap.Builder builderPut = builder.put(userDownloadState, userDownloadState2).put(userDownloadState2, userDownloadState2).put(UserDownloadState.WAITING, userDownloadState2);
        UserDownloadState userDownloadState3 = UserDownloadState.PAUSED;
        ImmutableMap.Builder builderPut2 = builderPut.put(userDownloadState3, userDownloadState3).put(UserDownloadState.DOWNLOADING, userDownloadState2);
        UserDownloadState userDownloadState4 = UserDownloadState.DOWNLOADED;
        ImmutableMap.Builder builderPut3 = builderPut2.put(userDownloadState4, userDownloadState4);
        UserDownloadState userDownloadState5 = UserDownloadState.DELETE_REQUESTED;
        ImmutableMap.Builder builderPut4 = builderPut3.put(userDownloadState5, userDownloadState5).put(UserDownloadState.DELETING, userDownloadState5);
        UserDownloadState userDownloadState6 = UserDownloadState.ERROR;
        this.STATE_TO_PERSIST = (ImmutableMap) Preconditions2.checkFullKeyMappingWithBlacklist(UserDownloadState.class, builderPut4.put(userDownloadState6, userDownloadState6).build(), ImmutableSet.of(UserDownloadState.DELETED));
        this.mInitializationLatch = new InitializationLatch(this);
        this.mDownloadErrorUtils = (DownloadErrorUtils) Preconditions.checkNotNull(downloadErrorUtils, "downloadErrorUtils");
    }

    void initialize(@Nonnull Context context, @Nonnull PlaybackDownloadSharedComponents playbackDownloadSharedComponents) {
        playbackDownloadSharedComponents.getMediaSystemSharedDependencies().waitForInitializationUninterruptibly();
        initializeInternal(playbackDownloadSharedComponents.getPlaybackDownloadEventReporter(), new DBOpenHelperSupplier(context, new PlaybackDownloadsDatabaseUpgradeManager(), new PlaybackDownloadsDatabase()), playbackDownloadSharedComponents, context);
    }

    void initializeInternal(@Nonnull PlaybackDownloadEventReporter playbackDownloadEventReporter, @Nonnull DBOpenHelperSupplier dBOpenHelperSupplier, @Nonnull PlaybackDownloadSharedComponents playbackDownloadSharedComponents, @Nonnull Context context) {
        this.mInitializationLatch.start(60L, TimeUnit.SECONDS);
        this.mPlaybackDownloadEventReporter = (PlaybackDownloadEventReporter) Preconditions.checkNotNull(playbackDownloadEventReporter, "playbackDownloadEventReporter");
        this.mDBOpenHelperSupplier = (DBOpenHelperSupplier) Preconditions.checkNotNull(dBOpenHelperSupplier, "dbOpenHelperSupplier");
        initializeAudioTrackMetadataCollection();
        this.mInitializationLatch.complete();
        SQLiteDatabase writableDatabase = this.mDBOpenHelperSupplier.get().getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            deleteDeletedRows(writableDatabase);
            deleteOrphanedRows(writableDatabase);
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            checkDownloadSanity();
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }

    void initializeAudioTrackMetadataCollection() {
        ObjectMapper objectMapper = new ObjectMapper();
        this.mObjectMapper = objectMapper;
        this.mAudioTrackMetadataCollectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, AudioTrackMetadata.class);
    }

    @Nonnull
    public PlaybackDownloadMemoryCache readAll() {
        this.mInitializationLatch.checkInitialized();
        return readAllInternal();
    }

    void upsert(@Nonnull PlaybackDownload playbackDownload) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(playbackDownload, "updatedDownload");
        SQLiteDatabase writableDatabase = this.mDBOpenHelperSupplier.get().getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            Optional<String> drmAssetId = playbackDownload.getDrmAssetId();
            boolean z = (!drmAssetId.isPresent() || upsertDrmRecord(ImmutableSet.of(playbackDownload), writableDatabase, drmAssetId.get(), playbackDownload.getDrmRecord())) && upsertPlaybackDownloadEntry(writableDatabase, playbackDownload);
            Preconditions2.checkStateWeakly(z, "DWNLD_SDK DB Upsert failed for disk persistence for %s while upserting the download entry", playbackDownload);
            if (z) {
                writableDatabase.setTransactionSuccessful();
            }
        } finally {
            writableDatabase.endTransaction();
        }
    }

    void delete(@Nonnull PlaybackDownload playbackDownload) {
        this.mInitializationLatch.checkInitialized();
        Preconditions.checkNotNull(playbackDownload, "download");
        SQLiteDatabase writableDatabase = this.mDBOpenHelperSupplier.get().getWritableDatabase();
        try {
            try {
                writableDatabase.beginTransaction();
                boolean z = writableDatabase.delete("playback_download", String.format(Locale.US, "%s=? AND %s=?", "offer_asin", "session_context"), new String[]{playbackDownload.getAsin(), playbackDownload.getSessionContext()}) > 0;
                deleteOrphanedRows(writableDatabase);
                Preconditions2.checkStateWeakly(z, "DWNLD_SDK DB Deletion failed from disk persistence for %s", playbackDownload);
                if (z) {
                    writableDatabase.setTransactionSuccessful();
                } else {
                    reportFailure(playbackDownload, "delete", "", false);
                }
            } catch (SQLException e) {
                reportFailure(ImmutableSet.of(playbackDownload), "delete", e);
                Throwables2.propagateIfWeakMode(e);
            }
        } finally {
            writableDatabase.endTransaction();
        }
    }

    void updateDrmRecord(@Nonnull ImmutableSet<PlaybackDownload> immutableSet, @Nonnull String str, @Nonnull DrmRecord drmRecord) {
        Preconditions.checkNotNull(immutableSet, "download");
        Preconditions.checkNotNull(str, "drmRecord");
        Preconditions.checkNotNull(drmRecord, "drmStoredRights");
        SQLiteDatabase writableDatabase = this.mDBOpenHelperSupplier.get().getWritableDatabase();
        try {
            writableDatabase.beginTransaction();
            boolean zUpsertDrmRecord = upsertDrmRecord(immutableSet, writableDatabase, str, Optional.of(drmRecord));
            Preconditions2.checkStateWeakly(zUpsertDrmRecord, "DWNLD_SDK DB Upsert failed for disk persistence for %s while updating drm info", str);
            if (zUpsertDrmRecord) {
                writableDatabase.setTransactionSuccessful();
            }
        } finally {
            writableDatabase.endTransaction();
        }
    }

    private boolean upsertPlaybackDownloadEntry(@Nonnull SQLiteDatabase sQLiteDatabase, @Nonnull PlaybackDownload playbackDownload) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("offer_asin", playbackDownload.getAsin());
            contentValues.put("audio_format", playbackDownload.getAudioFormat().name());
            contentValues.put("audio_track_ids", DBUtils.convertListToJSON(playbackDownload.getAudioTrackIds()));
            if (playbackDownload.getPersistedErrorCode().isPresent()) {
                contentValues.put("download_error_code", this.mDownloadErrorUtils.getErrorName(playbackDownload.getPersistedErrorCode().get()));
            } else {
                contentValues.putNull("download_error_code");
            }
            contentValues.put("manifest_url", playbackDownload.getUrl().orNull());
            contentValues.put("qos_session_id", playbackDownload.getSessionId().orNull());
            contentValues.put("download_state", this.STATE_TO_PERSIST.get(playbackDownload.getState()).getPersistenceName());
            contentValues.put("drm_asset_id", playbackDownload.getDrmAssetId().orNull());
            contentValues.put("file_size_kb", Long.valueOf(playbackDownload.getFileSizeBytes()));
            contentValues.put("is_ready_to_watch", Integer.valueOf(playbackDownload.getProgressMilestone().getNumericValue()));
            contentValues.put("media_quality", QUALITY_SERIALIZER.get(playbackDownload.getDownloadQuality()));
            contentValues.put("percent_downloaded", Float.valueOf(playbackDownload.getPercentage()));
            contentValues.put("storage_path", playbackDownload.getStoragePath().getAbsolutePath());
            contentValues.put("storage_path_type", playbackDownload.getUserDownloadLocation().getPersistenceName());
            if (playbackDownload.getRelativeStoragePath().isPresent()) {
                contentValues.put("relative_storage_path", playbackDownload.getRelativeStoragePath().get().getPath());
            }
            putAudioTrackMetadataList(contentValues, playbackDownload.getAudioTrackMetadataList());
            contentValues.put("download_store_type", playbackDownload.getDownloadStoreType().toString());
            contentValues.put("session_context", playbackDownload.getSessionContext());
            contentValues.put("player_sdk_playback_token_id", playbackDownload.getPlayerSDKPlaybackToken());
            contentValues.put("player_sdk_audio_stream_matchers_id", playbackDownload.getPlayerSDKAudioStreamMatchers());
            contentValues.put("player_sdk_timed_text_stream_matchers_id", playbackDownload.getPlayerSDKTimedTextStreamMatchers());
            contentValues.put("actual_runtime_ms", playbackDownload.getActualRuntimeInMs().orNull());
            if (playbackDownload.getCuepointPlaylistInfo().isPresent()) {
                putCuepointPlaylistInfo(contentValues, playbackDownload.getCuepointPlaylistInfo().get());
            }
            if (upsert(sQLiteDatabase, "playback_download", contentValues) > 0) {
                return true;
            }
            reportFailure(playbackDownload, "upsertPlaybackDownloadEntry", contentValuesToString(contentValues), false);
            DLog.warnf("DWNLD_SDK DB Failed to insert value for download: %s", playbackDownload);
            return false;
        } catch (SQLException e) {
            reportFailure(ImmutableSet.of(playbackDownload), "upsertPlaybackDownloadEntry", e);
            Throwables2.propagateIfWeakMode(e);
            return false;
        }
    }

    private boolean upsertDrmRecord(@Nonnull ImmutableSet<PlaybackDownload> immutableSet, @Nonnull SQLiteDatabase sQLiteDatabase, @Nonnull String str, @Nonnull Optional<DrmRecord> optional) {
        long j;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("drm_asset_id", str);
            if (optional.isPresent()) {
                DrmRecord drmRecord = optional.get();
                contentValues.put("drm_scheme", drmRecord.getDrmScheme().toString());
                contentValues.put("renderer_scheme", drmRecord.getRendererSchemeType().toString());
                contentValues.put("offline_keyid", drmRecord.getOfflineKeyId());
                contentValues.put("error_code", drmRecord.getErrorCode() == null ? null : drmRecord.getErrorCode().getName());
                DrmStoredRights drmStoredRights = drmRecord.getDrmStoredRights();
                contentValues.put("expiry", Long.valueOf(drmStoredRights.getExpiryTimeInSeconds()));
                contentValues.put(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, drmStoredRights.getLicenseType().getDBName());
                contentValues.put("view_hours", Long.valueOf(drmStoredRights.getViewingHours()));
                contentValues.put("last_refresh_sec", Long.valueOf(drmRecord.getLastLicenseRefreshSecs()));
                j = 0;
            } else {
                contentValues.putNull("drm_scheme");
                contentValues.putNull("renderer_scheme");
                contentValues.putNull("offline_keyid");
                contentValues.putNull("error_code");
                contentValues.putNull("expiry");
                contentValues.putNull(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
                contentValues.putNull("view_hours");
                j = 0;
                contentValues.put("last_refresh_sec", (Long) 0L);
            }
            if (upsert(sQLiteDatabase, "drm", contentValues) > j) {
                return true;
            }
            reportFailure(immutableSet, "upsertDrmRecord", contentValuesToString(contentValues), false);
            DLog.warnf("DWNLD_SDK DB Failed to upsert DrmStoredRights for drmAssetId: %s", str);
            return false;
        } catch (SQLException e) {
            reportFailure(immutableSet, "upsertDrmRecord", e);
            Throwables2.propagateIfWeakMode(e);
            return false;
        }
    }

    private void deleteDeletedRows(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete("playback_download", String.format(Locale.US, "%s=?", "download_state"), new String[]{"Deleted"});
    }

    private void deleteOrphanedRows(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(String.format(Locale.US, "DELETE FROM %1$s WHERE %2$s NOT IN (SELECT %1$s.%2$s FROM %1$s, %3$s WHERE %1$s.%4$s = %3$s.%5$s)", "drm", "drm_asset_id", "playback_download", "drm_asset_id", "drm_asset_id"));
    }

    private PlaybackDownloadMemoryCache readAllInternal() {
        return new PlaybackDownloadMemoryCache(readDownloadsTable(readDrmRecordTable()));
    }

    @Nonnull
    private ImmutableMap<String, ParsedDataWithError<DrmRecord>> readDrmRecordTable() {
        Cursor cursorQuery = this.mDBOpenHelperSupplier.get().getReadableDatabase().query("drm", null, null, null, null, null, null);
        ImmutableMap.Builder builder = ImmutableMap.builder();
        while (cursorQuery.moveToNext()) {
            String stringFromCursor = CursorUtils.getStringFromCursor(cursorQuery, "drm_asset_id");
            Preconditions.checkNotNull(stringFromCursor, "Primary key of drm table was null");
            builder.put(stringFromCursor, readDrmRecord(cursorQuery));
        }
        cursorQuery.close();
        return builder.build();
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00b8  */
    @javax.annotation.Nonnull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.amazon.avod.userdownload.internal.PlaybackDownloadDiskPersistence.ParsedDataWithError<com.amazon.avod.drm.db.DrmRecord> readDrmRecord(android.database.Cursor r20) {
        /*
            r19 = this;
            r0 = r20
            java.util.ArrayList r1 = com.google.common.collect.Lists.newArrayList()
            com.amazon.avod.userdownload.internal.PlaybackDownloadDiskPersistence$ParsedDataWithError r2 = r19.readDrmStoredRights(r20)
            com.google.common.collect.ImmutableList<java.lang.String> r3 = r2.errors
            r1.addAll(r3)
            T r3 = r2.data
            r4 = 0
            if (r3 != 0) goto L1a
            com.amazon.avod.userdownload.internal.PlaybackDownloadDiskPersistence$ParsedDataWithError r0 = new com.amazon.avod.userdownload.internal.PlaybackDownloadDiskPersistence$ParsedDataWithError
            r0.<init>(r4, r1)
            return r0
        L1a:
            java.lang.String r3 = "drm_asset_id"
            java.lang.String r3 = com.amazon.avod.util.CursorUtils.getStringFromCursor(r0, r3)
            java.lang.String r5 = "Primary key of drm table was null"
            com.google.common.base.Preconditions.checkNotNull(r3, r5)
            java.lang.String r3 = "drm_scheme"
            java.lang.String r3 = com.amazon.avod.util.CursorUtils.getStringFromCursor(r0, r3)
            java.lang.String r5 = "renderer_scheme"
            java.lang.String r5 = com.amazon.avod.util.CursorUtils.getStringFromCursor(r0, r5)
            java.lang.String r6 = "offline_keyid"
            java.lang.String r6 = com.amazon.avod.util.CursorUtils.getStringFromCursor(r0, r6)
            java.lang.String r7 = "error_code"
            java.lang.String r14 = com.amazon.avod.util.CursorUtils.getStringFromCursor(r0, r7)
            com.amazon.avod.playback.drm.DrmScheme r15 = com.amazon.avod.playback.drm.DrmScheme.fromSchemeString(r3)
            com.google.common.base.Optional r7 = com.amazon.avod.playback.renderer.RendererSchemeType.fromSchemeString(r5)
            java.lang.Object r7 = r7.orNull()
            r12 = r7
            com.amazon.avod.playback.renderer.RendererSchemeType r12 = (com.amazon.avod.playback.renderer.RendererSchemeType) r12
            java.lang.String r7 = "last_refresh_sec"
            long r16 = com.amazon.avod.util.CursorUtils.getLongFromCursor(r0, r7)
            if (r15 == 0) goto L86
            if (r12 == 0) goto L86
            T r0 = r2.data     // Catch: java.lang.RuntimeException -> L7f
            r10 = r0
            com.amazon.avod.drm.db.DrmStoredRights r10 = (com.amazon.avod.drm.db.DrmStoredRights) r10     // Catch: java.lang.RuntimeException -> L7f
            if (r14 != 0) goto L63
            r13 = r19
            r11 = r4
            goto L6c
        L63:
            r13 = r19
            com.amazon.avod.media.download.internal.db.DownloadErrorUtils r0 = r13.mDownloadErrorUtils     // Catch: java.lang.RuntimeException -> L7f
            com.amazon.avod.media.error.MediaErrorCode r0 = r0.fromErrorName(r14)     // Catch: java.lang.RuntimeException -> L7f
            r11 = r0
        L6c:
            r7 = r15
            r8 = r12
            r9 = r6
            r18 = r12
            r12 = r16
            com.amazon.avod.drm.db.DrmRecord r0 = com.amazon.avod.drm.db.DrmRecord.fromPersistedData(r7, r8, r9, r10, r11, r12)     // Catch: java.lang.RuntimeException -> L7d
            com.amazon.avod.userdownload.internal.PlaybackDownloadDiskPersistence$ParsedDataWithError r7 = new com.amazon.avod.userdownload.internal.PlaybackDownloadDiskPersistence$ParsedDataWithError     // Catch: java.lang.RuntimeException -> L7d
            r7.<init>(r0, r1)     // Catch: java.lang.RuntimeException -> L7d
            return r7
        L7d:
            r0 = move-exception
            goto L82
        L7f:
            r0 = move-exception
            r18 = r12
        L82:
            com.amazon.avod.util.Throwables2.propagateIfWeakMode(r0)
            goto L88
        L86:
            r18 = r12
        L88:
            java.util.Locale r0 = java.util.Locale.US
            r7 = 5
            java.lang.Object[] r7 = new java.lang.Object[r7]
            r8 = 0
            r7[r8] = r3
            r3 = 1
            r7[r3] = r5
            r3 = 2
            java.lang.String r5 = com.amazon.avod.util.DLog.maskString(r6)
            r7[r3] = r5
            r3 = 3
            r7[r3] = r14
            r3 = 4
            T r2 = r2.data
            r7[r3] = r2
            java.lang.String r2 = "Bad data: DrmRecord: drmScheme=%s, rendererScheme=%s, offlineKeyId=%s, error=%s, storedRights=%s"
            java.lang.String r0 = java.lang.String.format(r0, r2, r7)
            r1.add(r0)
            if (r15 == 0) goto Lb8
            r7 = r18
            if (r7 == 0) goto Lb8
            com.amazon.avod.media.download.error.DownloadExecutionErrorCode r0 = com.amazon.avod.media.download.error.DownloadExecutionErrorCode.BAD_DRM_RECORD
            com.amazon.avod.drm.db.DrmRecord r0 = com.amazon.avod.drm.db.DrmRecord.forMissingRights(r15, r7, r0)
            goto Lb9
        Lb8:
            r0 = r4
        Lb9:
            com.amazon.avod.userdownload.internal.PlaybackDownloadDiskPersistence$ParsedDataWithError r2 = new com.amazon.avod.userdownload.internal.PlaybackDownloadDiskPersistence$ParsedDataWithError
            r2.<init>(r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.userdownload.internal.PlaybackDownloadDiskPersistence.readDrmRecord(android.database.Cursor):com.amazon.avod.userdownload.internal.PlaybackDownloadDiskPersistence$ParsedDataWithError");
    }

    @Nonnull
    private ParsedDataWithError<DrmStoredRights> readDrmStoredRights(Cursor cursor) {
        String stringFromCursor = CursorUtils.getStringFromCursor(cursor, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        DrmStoredRights.LicenseType licenseTypeFromString = DrmStoredRights.LicenseType.fromString(stringFromCursor);
        long longFromCursor = CursorUtils.getLongFromCursor(cursor, "expiry");
        long longFromCursor2 = CursorUtils.getLongFromCursor(cursor, "view_hours");
        AnonymousClass1 anonymousClass1 = null;
        if (Strings.isNullOrEmpty(stringFromCursor)) {
            return new ParsedDataWithError<>(anonymousClass1, new String[0]);
        }
        try {
            return new ParsedDataWithError<>(DrmStoredRights.fromPersistedData(licenseTypeFromString, longFromCursor, longFromCursor2), new String[0]);
        } catch (RuntimeException e) {
            Throwables2.propagateIfWeakMode(e);
            return new ParsedDataWithError<>(DrmStoredRights.newMissingLicense(), new String[]{String.format(Locale.US, "Bad data: DrmStoredRights: licenseType=%s, expiry=%s, viewing=%s", stringFromCursor, Long.valueOf(longFromCursor), Long.valueOf(longFromCursor2))});
        }
    }

    private ImmutableSet<PlaybackDownload> readDownloadsTable(@Nonnull ImmutableMap<String, ParsedDataWithError<DrmRecord>> immutableMap) {
        Cursor cursorQuery = this.mDBOpenHelperSupplier.get().getReadableDatabase().query("playback_download", null, null, null, null, null, null);
        ImmutableSet.Builder builder = ImmutableSet.builder();
        while (cursorQuery.moveToNext()) {
            Optional<PlaybackDownload> optionalNewDownload = newDownload(cursorQuery, immutableMap);
            if (optionalNewDownload.isPresent()) {
                builder.add(optionalNewDownload.get());
            }
        }
        cursorQuery.close();
        return builder.build();
    }

    private Optional<PlaybackDownload> newDownload(@Nonnull Cursor cursor, @Nonnull ImmutableMap<String, ParsedDataWithError<DrmRecord>> immutableMap) {
        Optional<File> optionalAbsent;
        Optional<DrmRecord> optionalFromNullable;
        ImmutableList<String> immutableListOf;
        Optional<MediaErrorCode> optionalAbsent2;
        Optional<Long> optionalOf;
        String stringFromCursor = CursorUtils.getStringFromCursor(cursor, "offer_asin");
        UserDownloadLocation userDownloadLocationFromPersistenceName = UserDownloadLocation.fromPersistenceName(CursorUtils.getStringFromCursor(cursor, "storage_path_type"));
        File file = new File(CursorUtils.getStringFromCursor(cursor, "storage_path"));
        String stringFromCursor2 = CursorUtils.getStringFromCursor(cursor, "relative_storage_path");
        if (stringFromCursor2 != null) {
            optionalAbsent = Optional.of(new File((String) null, stringFromCursor2));
        } else {
            optionalAbsent = Optional.absent();
        }
        Optional<String> optionalFromNullable2 = Optional.fromNullable(CursorUtils.getStringFromCursor(cursor, "drm_asset_id"));
        if (!optionalFromNullable2.isPresent()) {
            optionalFromNullable = Optional.absent();
            immutableListOf = ImmutableList.of();
        } else {
            ParsedDataWithError<DrmRecord> parsedDataWithError = immutableMap.get(optionalFromNullable2.get());
            Preconditions2.checkStateWeakly(parsedDataWithError != null, "DWNLD_SDK DB out of sync: expected DRM Info from valid DRM Asset ID. ASIN: %s", stringFromCursor);
            optionalFromNullable = Optional.fromNullable(parsedDataWithError != null ? parsedDataWithError.data : null);
            immutableListOf = parsedDataWithError == null ? ImmutableList.of() : parsedDataWithError.errors;
        }
        UserDownloadState userDownloadStateFromPersistenceName = UserDownloadState.fromPersistenceName(CursorUtils.getStringFromCursor(cursor, "download_state"));
        if (UserDownloadState.DELETED == userDownloadStateFromPersistenceName) {
            Preconditions2.failWeakly("DWNLD_SDK DB out of sync: deleted title found when trying to read database from persistence. ASIN: %s", stringFromCursor);
            return Optional.absent();
        }
        UserDownloadState userDownloadState = this.STATE_TO_PERSIST.get(userDownloadStateFromPersistenceName);
        String stringFromCursor3 = CursorUtils.getStringFromCursor(cursor, "media_quality");
        ImmutableMap<String, MediaQuality> immutableMap2 = QUALITY_DESERIALIZER;
        MediaQuality mediaQuality = immutableMap2.containsKey(stringFromCursor3) ? immutableMap2.get(stringFromCursor3) : MediaQuality.LOW;
        AudioFormat audioFormatFromString = AudioFormat.fromString(CursorUtils.getStringFromCursor(cursor, "audio_format"));
        ImmutableList<String> immutableListConvertJSONToList = DBUtils.convertJSONToList(CursorUtils.getStringFromCursor(cursor, "audio_track_ids"));
        String stringFromCursor4 = CursorUtils.getStringFromCursor(cursor, "download_error_code");
        if (stringFromCursor4 != null) {
            optionalAbsent2 = Optional.of(this.mDownloadErrorUtils.fromErrorName(stringFromCursor4));
        } else {
            optionalAbsent2 = Optional.absent();
        }
        Optional<String> optionalFromNullable3 = Optional.fromNullable(CursorUtils.getStringFromCursor(cursor, "manifest_url"));
        Optional<String> optionalFromNullable4 = Optional.fromNullable(CursorUtils.getStringFromCursor(cursor, "qos_session_id"));
        float f = cursor.getFloat(cursor.getColumnIndex("percent_downloaded"));
        ImmutableList<String> immutableList = immutableListOf;
        Optional<MediaErrorCode> optional = optionalAbsent2;
        long longFromCursor = CursorUtils.getLongFromCursor(cursor, "file_size_kb");
        ProgressMilestone progressMilestoneFromNumericValue = ProgressMilestone.fromNumericValue(CursorUtils.getIntFromCursor(cursor, "is_ready_to_watch"));
        List<AudioTrackMetadata> audioTrackMetadataList = getAudioTrackMetadataList(cursor);
        CuepointPlaylistInfo cuepointPlaylistInfo = getCuepointPlaylistInfo(cursor);
        DownloadStoreType downloadStoreTypeFromString = DownloadStoreType.fromString(CursorUtils.getStringFromCursor(cursor, "download_store_type"));
        String stringFromCursor5 = CursorUtils.getStringFromCursor(cursor, "session_context");
        Optional<String> optionalFromNullable5 = Optional.fromNullable(CursorUtils.getStringFromCursor(cursor, "player_sdk_playback_token_id"));
        Optional<String> optionalFromNullable6 = Optional.fromNullable(CursorUtils.getStringFromCursor(cursor, "player_sdk_audio_stream_matchers_id"));
        Optional<String> optionalFromNullable7 = Optional.fromNullable(CursorUtils.getStringFromCursor(cursor, "player_sdk_timed_text_stream_matchers_id"));
        if (cursor.isNull(cursor.getColumnIndex("actual_runtime_ms"))) {
            optionalOf = Optional.absent();
        } else {
            optionalOf = Optional.of(Long.valueOf(cursor.getLong(cursor.getColumnIndex("actual_runtime_ms"))));
        }
        PlaybackDownload playbackDownloadBuild = PlaybackDownload.newBuilder(stringFromCursor).setSessionContext(stringFromCursor5).setStoragePath(userDownloadLocationFromPersistenceName, file, optionalAbsent).setDrmAssetId(optionalFromNullable2).setDrmRecord(optionalFromNullable).setState(userDownloadState).setDownloadQuality(mediaQuality).setAudioFormat(audioFormatFromString).setAudioTrackIds(immutableListConvertJSONToList).setPersistedErrorCode(optional).setUrl(optionalFromNullable3).setSessionId(optionalFromNullable4).setActualRuntimeinMS(optionalOf).setPercentage(f).setProgressFromPersistence(progressMilestoneFromNumericValue, f).setFileSizeBytes(longFromCursor).setAudioTrackMetadataList(ImmutableList.copyOf((Collection) audioTrackMetadataList)).setCuepointPlaylistInfo(cuepointPlaylistInfo).setDownloadStoreType(downloadStoreTypeFromString).setPlayerSDKPlaybackToken(optionalFromNullable5).setPlayerSDKAudioStreamMatchers(optionalFromNullable6).setPlayerSDKTimedTextStreamMatchers(optionalFromNullable7).build();
        Iterator<String> it = immutableList.iterator();
        while (it.hasNext()) {
            reportFailure(playbackDownloadBuild, "drmReadError", it.next(), false);
        }
        return Optional.of(playbackDownloadBuild);
    }

    private long upsert(@Nonnull SQLiteDatabase sQLiteDatabase, @Nonnull String str, @Nonnull ContentValues contentValues) {
        Preconditions.checkNotNull(sQLiteDatabase, "db");
        Preconditions.checkNotNull(str, "tableName");
        Preconditions.checkNotNull(contentValues, "values");
        return sQLiteDatabase.insertWithOnConflict(str, null, contentValues, 5);
    }

    private void reportFailure(@Nonnull ImmutableSet<PlaybackDownload> immutableSet, @Nonnull String str, @Nonnull RuntimeException runtimeException) {
        Preconditions.checkNotNull(immutableSet, "download");
        Preconditions.checkNotNull(str, "methodName");
        Preconditions.checkNotNull(runtimeException, "exception");
        reportFailure(immutableSet, str + "-" + runtimeException.getClass().getSimpleName(), runtimeException.getMessage(), true);
    }

    private void reportFailure(@Nonnull ImmutableSet<PlaybackDownload> immutableSet, @Nonnull String str, @Nonnull String str2, boolean z) {
        Preconditions.checkNotNull(immutableSet, "download");
        Preconditions.checkNotNull(str, "errorMessage");
        Preconditions.checkNotNull(str2, "note");
        UnmodifiableIterator<PlaybackDownload> it = immutableSet.iterator();
        while (it.hasNext()) {
            reportFailure(it.next(), str, str2, z);
        }
    }

    private void reportFailure(@Nonnull PlaybackDownload playbackDownload, @Nonnull String str, @Nonnull String str2, boolean z) {
        String str3 = "PlaybackDownloadDiskPersistence-FAILURE-" + str;
        String str4 = str3 + "\n" + (z ? DLog.sanitize(str2, 0) : str2) + "\n" + playbackDownload.toString();
        reportFailureToQOS(playbackDownload, str3 + "-" + str2);
        DLog.errorf("DWNLD_SDK DB %s", str4);
    }

    private void reportFailureToQOS(@Nonnull PlaybackDownload playbackDownload, @Nullable String str) {
        this.mPlaybackDownloadEventReporter.reportDownloadPersistenceFailure(playbackDownload, str);
    }

    private static String contentValuesToString(@Nonnull ContentValues contentValues) {
        Preconditions.checkNotNull(contentValues);
        Set<String> setKeySet = contentValues.keySet();
        StringBuilder sb = new StringBuilder();
        for (String str : setKeySet) {
            sb.append(str + "=" + DLog.sanitize(contentValues.get(str), 0) + ", ");
        }
        return sb.toString();
    }

    void putAudioTrackMetadataList(@Nonnull ContentValues contentValues, @Nonnull List<AudioTrackMetadata> list) {
        Preconditions.checkNotNull(contentValues, "contentValues");
        Preconditions.checkNotNull(list, "audioTrackMetadataList");
        if (list.size() > 0) {
            try {
                contentValues.put("audio_track_metadata_json", this.mObjectMapper.writeValueAsString(list));
            } catch (IOException e) {
                DLog.warnf("DWNLD_SDK DB %s", e.getMessage());
            }
        }
    }

    void putCuepointPlaylistInfo(@Nonnull ContentValues contentValues, @Nonnull CuepointPlaylistInfo cuepointPlaylistInfo) {
        Preconditions.checkNotNull(contentValues, "contentValues");
        Preconditions.checkNotNull(cuepointPlaylistInfo, "cuepointPlaylistInfo");
        try {
            contentValues.put("cuepoint_playlist", this.mObjectMapper.writeValueAsString(cuepointPlaylistInfo));
        } catch (IOException e) {
            DLog.warnf("ServerInsertedPlaybackSession DWNLD DB %s", e.getMessage());
        }
    }

    @Nullable
    CuepointPlaylistInfo getCuepointPlaylistInfo(@Nonnull Cursor cursor) {
        Preconditions.checkNotNull(cursor, "cursor");
        int columnIndex = cursor.getColumnIndex("cuepoint_playlist");
        if (columnIndex != -1) {
            String string = cursor.getString(columnIndex);
            if (!Strings.isNullOrEmpty(string)) {
                try {
                    return (CuepointPlaylistInfo) this.mObjectMapper.readValue(string, CuepointPlaylistInfo.class);
                } catch (IOException e) {
                    DLog.warnf("ServerInsertedPlaybackSession: Playback DWNLD DB Unable to read CuepointPlaylistInfo %s", e.getMessage());
                    return null;
                }
            }
            DLog.warnf("ServerInsertedPlaybackSession: cuepointPlaylistInfoJson is null or empty");
            return null;
        }
        DLog.warnf("ServerInsertedPlaybackSession: Playback DWNLD DB table doesn't have column named cuepoint_playlist");
        return null;
    }

    @Nonnull
    List<AudioTrackMetadata> getAudioTrackMetadataList(@Nonnull Cursor cursor) {
        String string;
        Preconditions.checkNotNull(cursor, "cursor");
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        int columnIndex = cursor.getColumnIndex("audio_track_metadata_json");
        if (columnIndex != -1) {
            string = cursor.getString(columnIndex);
        } else {
            DLog.warnf("DWNLD_SDK DB table don't have column named audio_track_metadata_json");
            string = null;
        }
        if (Strings.isNullOrEmpty(string)) {
            return arrayListNewArrayList;
        }
        try {
            return (List) this.mObjectMapper.readValue(string, this.mAudioTrackMetadataCollectionType);
        } catch (IOException e) {
            DLog.warnf("DWNLD_SDK DB %s", e.getMessage());
            return arrayListNewArrayList;
        }
    }

    private HashMap<String, String> extractExistingColumns(SQLiteDatabase sQLiteDatabase, String str) {
        StringBuilder sb = new StringBuilder(128);
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = sQLiteDatabase.rawQuery("PRAGMA table_info(" + str + ")", null);
            HashMap<String, String> map = new HashMap<>(cursorRawQuery.getCount());
            while (cursorRawQuery.moveToNext()) {
                String string = cursorRawQuery.getString(1);
                String string2 = cursorRawQuery.getString(2);
                int i = cursorRawQuery.getInt(3);
                String string3 = cursorRawQuery.getString(4);
                sb.append(string2);
                sb.append(' ');
                if (i != 0) {
                    sb.append("NOT NULL ");
                }
                if (!Strings.isNullOrEmpty(string3)) {
                    sb.append("DEFAULT ");
                    sb.append(string3);
                }
                map.put(string, sb.toString().trim());
            }
            cursorRawQuery.close();
            return map;
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void checkDownloadSanity() {
        if (PlaybackDownloadConfig.getInstance().doDownloadDiskPersistenceSanityCheck()) {
            DLog.logf("DWNLD_SDK Checking Download DB Sanity");
            SQLiteDatabase writableDatabase = this.mDBOpenHelperSupplier.get().getWritableDatabase();
            try {
                HashMap<String, String> mapExtractExistingColumns = extractExistingColumns(writableDatabase, "playback_download");
                ImmutableMap<String, String> columnsForVersion = PlaybackDownloadsTable.getColumnsForVersion(31);
                if (mapExtractExistingColumns.size() != columnsForVersion.size()) {
                    reportError(String.format(Locale.US, "%s database imbalanced", "playback_download"));
                    UnmodifiableIterator it = Sets.difference(mapExtractExistingColumns.keySet(), columnsForVersion.keySet()).iterator();
                    while (it.hasNext()) {
                        String str = (String) it.next();
                        reportError(String.format(Locale.US, "%s includes additional column %s AS %s", "playback_download", str, mapExtractExistingColumns.get(str)));
                    }
                    UnmodifiableIterator it2 = Sets.difference(columnsForVersion.keySet(), mapExtractExistingColumns.keySet()).iterator();
                    while (it2.hasNext()) {
                        String str2 = (String) it2.next();
                        Locale locale = Locale.US;
                        reportError(String.format(locale, "%s missing desired column %s AS %s", "playback_download", str2, columnsForVersion.get(str2)));
                        if (PlaybackDownloadConfig.getInstance().doDownloadDiskPersistenceSanityCheckFixes() && "actual_runtime_ms".equalsIgnoreCase(str2)) {
                            reportError(String.format(locale, "%s applying DownloadsDBUpgradeActionFrom28To29", "playback_download"));
                            new DownloadsDBUpgradeActionFrom28To29().applyUpgrade(writableDatabase);
                        }
                    }
                    return;
                }
                UnmodifiableIterator<String> it3 = columnsForVersion.keySet().iterator();
                while (it3.hasNext()) {
                    String next = it3.next();
                    String str3 = "";
                    String str4 = columnsForVersion.get(next) == null ? "" : columnsForVersion.get(next);
                    if (mapExtractExistingColumns.get(next) != null) {
                        str3 = mapExtractExistingColumns.get(next);
                    }
                    if (str4.equalsIgnoreCase(str3)) {
                        reportError(String.format(Locale.US, "%s corrupt column is %s, should be %s", "playback_download", str3, str4));
                    }
                }
            } catch (SQLException e) {
                DLog.exceptionf(e, "DWNLD_SDK DB Failed sanity check", new Object[0]);
                reportError(String.format(Locale.US, "%s Failed sanity check", "playback_download"));
            }
        }
    }

    private void reportError(String str) {
        this.mPlaybackDownloadEventReporter.reportDiagnosticNote("P55110778", str);
        DLog.errorf("DWNLD_SDK DB %s", str);
    }

    private static class ParsedDataWithError<T> {
        public final T data;
        public final ImmutableList<String> errors;

        private ParsedDataWithError(@Nullable T t, @Nullable String... strArr) {
            this(t, Lists.newArrayList(strArr));
        }

        private ParsedDataWithError(@Nullable T t, @Nonnull List<String> list) {
            this.data = t;
            this.errors = ImmutableList.copyOf(Collections2.filter(list, Predicates.notNull()));
        }
    }
}
