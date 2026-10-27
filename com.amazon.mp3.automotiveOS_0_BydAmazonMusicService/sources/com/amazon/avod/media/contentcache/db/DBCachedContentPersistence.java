package com.amazon.avod.media.contentcache.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import androidx.core.app.NotificationCompat;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.AudioVideoUrlsBuilder;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.content.urlvending.CuepointPlaylistInfo;
import com.amazon.avod.content.urlvending.EPrivacyConsentData;
import com.amazon.avod.content.urlvending.FailoverManager;
import com.amazon.avod.content.urlvending.ReturnedTitleRendition;
import com.amazon.avod.db.DBOpenHelperSupplier;
import com.amazon.avod.db.DBUtils;
import com.amazon.avod.download.Downloadable;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.contentcache.internal.CachedContentPersistence;
import com.amazon.avod.media.contentcache.internal.WritableCacheRecord;
import com.amazon.avod.media.framework.MediaSystemSharedDependencies;
import com.amazon.avod.media.playback.ContentType;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playbackclient.audiotrack.AudioTrackConfig;
import com.amazon.avod.util.DLog;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.video.sdk.player.PlaybackEnvelope;
import com.amazon.video.sdk.player.PlaybackEnvelopeData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class DBCachedContentPersistence implements CachedContentPersistence {
    private static final CollectionType AUDIO_TRACK_METADATA_COLLECTION_TYPE;
    private static final CollectionType CONTENT_URL_COLLECTION_TYPE;
    private static final ReadWriteLock DB_LOCK;
    private static final ObjectMapper OBJECT_MAPPER;
    private static final String ORDER_BY_ASC;
    private static final String ORDER_BY_DESC;
    private static final TypeReference<Map<String, String>> PLAYBACK_SETTINGS_TYPE_REF;
    private final DBOpenHelperSupplier mDBOpenHelperSupplier;
    private final String mLiveAudioLanguage;
    private final MediaSystemSharedDependencies mMediaSystemSharedDependencies;

    static {
        Locale locale = Locale.US;
        ORDER_BY_DESC = String.format(locale, "%s DESC, %s ASC, %s DESC", "priority", "is_deferrable", "_id");
        ORDER_BY_ASC = String.format(locale, "%s ASC, %s DESC, %s ASC", "priority", "is_deferrable", "_id");
        ObjectMapper objectMapper = new ObjectMapper();
        OBJECT_MAPPER = objectMapper;
        PLAYBACK_SETTINGS_TYPE_REF = new TypeReference<Map<String, String>>() { // from class: com.amazon.avod.media.contentcache.db.DBCachedContentPersistence.1
        };
        CONTENT_URL_COLLECTION_TYPE = objectMapper.getTypeFactory().constructCollectionType(List.class, ContentUrl.class);
        AUDIO_TRACK_METADATA_COLLECTION_TYPE = objectMapper.getTypeFactory().constructCollectionType(List.class, AudioTrackMetadata.class);
        DB_LOCK = new ReentrantReadWriteLock();
    }

    private static String getRecordForSpecQuery(VideoSpecification videoSpecification) {
        return String.format(Locale.US, "%s = '%s' and IFNULL(%s, 'false') = '%s' and %s = %s and %s = '%s' and IFNULL(%s, '[]') = '%s'", "title_id", videoSpecification.getTitleId(), "is_trailer", Boolean.valueOf(videoSpecification.isTrailer()), "start_position", Integer.valueOf(videoSpecification.getStartTime().getTotalSeconds()), "audio_format", videoSpecification.getAudioFormat(), "audio_track_ids", DBUtils.convertListToJSON(videoSpecification.getAudioTrackIds()));
    }

    public DBCachedContentPersistence(@Nonnull Context context) {
        this(context, MediaSystemSharedDependencies.getInstance(), AudioTrackConfig.getInstance().getLiveAudioLanguage());
    }

    public DBCachedContentPersistence(@Nonnull Context context, @Nonnull MediaSystemSharedDependencies mediaSystemSharedDependencies, @Nonnull String str) {
        this.mDBOpenHelperSupplier = new DBOpenHelperSupplier(context, new CachedContentDatabaseUpgradeManager(), new CachedContentDatabase());
        this.mMediaSystemSharedDependencies = (MediaSystemSharedDependencies) Preconditions.checkNotNull(mediaSystemSharedDependencies, "mediaSystemSharedDependencies");
        this.mLiveAudioLanguage = (String) Preconditions.checkNotNull(str, "liveAudioLanguage");
    }

    @Override // com.amazon.avod.media.contentcache.internal.CachedContentPersistence
    public int getTotalRecords() {
        return getNumRecords(null, null);
    }

    @Override // com.amazon.avod.media.contentcache.internal.CachedContentPersistence
    public long getTotalCacheFilesizeKb() {
        ReadWriteLock readWriteLock = DB_LOCK;
        readWriteLock.readLock().lock();
        try {
            try {
                long jLongForQuery = DatabaseUtils.longForQuery(this.mDBOpenHelperSupplier.get().getReadableDatabase(), String.format(Locale.US, "SELECT sum(%s) from %s", "filesize_kb", "cached_content_table"), null);
                readWriteLock.readLock().unlock();
                return jLongForQuery;
            } catch (SQLiteException e) {
                DLog.exceptionf(e, "Could not open a readable database.", new Object[0]);
                DB_LOCK.readLock().unlock();
                return 0L;
            }
        } catch (Throwable th) {
            DB_LOCK.readLock().unlock();
            throw th;
        }
    }

    @Override // com.amazon.avod.media.contentcache.internal.CachedContentPersistence
    public WritableCacheRecord getRecordForSpec(VideoSpecification videoSpecification) {
        List<WritableCacheRecord> recordSet = getRecordSet(getRecordForSpecQuery(videoSpecification), null, null);
        Preconditions.checkState(recordSet.size() <= 1, "Shouldn't be able to violate primary key constraint on VideoSpecification!");
        if (recordSet.isEmpty()) {
            return null;
        }
        return recordSet.get(0);
    }

    @Override // com.amazon.avod.media.contentcache.internal.CachedContentPersistence
    public boolean putRecordBatch(List<WritableCacheRecord> list) {
        boolean zPutRecordInner;
        DB_LOCK.writeLock().lock();
        try {
            try {
                SQLiteDatabase writableDatabase = this.mDBOpenHelperSupplier.get().getWritableDatabase();
                writableDatabase.beginTransaction();
                try {
                    Iterator<WritableCacheRecord> it = list.iterator();
                    zPutRecordInner = true;
                    while (it.hasNext() && (zPutRecordInner = putRecordInner(it.next(), writableDatabase))) {
                    }
                    if (zPutRecordInner) {
                        writableDatabase.setTransactionSuccessful();
                    }
                } finally {
                    writableDatabase.endTransaction();
                }
            } catch (SQLiteException e) {
                zPutRecordInner = false;
                DLog.exceptionf(e, "Could not open a writable database.", new Object[0]);
            }
            return zPutRecordInner;
        } finally {
            DB_LOCK.writeLock().unlock();
        }
    }

    @Override // com.amazon.avod.media.contentcache.internal.CachedContentPersistence
    public boolean putRecord(WritableCacheRecord writableCacheRecord) {
        ReadWriteLock readWriteLock = DB_LOCK;
        readWriteLock.writeLock().lock();
        try {
            try {
                boolean zPutRecordInner = putRecordInner(writableCacheRecord, this.mDBOpenHelperSupplier.get().getWritableDatabase());
                readWriteLock.writeLock().unlock();
                return zPutRecordInner;
            } catch (SQLiteException e) {
                DLog.exceptionf(e, "Could not open a writable database.", new Object[0]);
                DB_LOCK.writeLock().unlock();
                return false;
            }
        } catch (Throwable th) {
            DB_LOCK.writeLock().unlock();
            throw th;
        }
    }

    private boolean putRecordInner(WritableCacheRecord writableCacheRecord, SQLiteDatabase sQLiteDatabase) {
        VideoSpecification specification = writableCacheRecord.getSpecification();
        ContentValues contentValuesBuildContentValuesFromRecord = buildContentValuesFromRecord(writableCacheRecord);
        if (sQLiteDatabase.update("cached_content_table", contentValuesBuildContentValuesFromRecord, getRecordForSpecQuery(specification), null) > 0) {
            return true;
        }
        try {
            return sQLiteDatabase.insertOrThrow("cached_content_table", null, contentValuesBuildContentValuesFromRecord) >= 0;
        } catch (SQLiteException e) {
            DLog.exceptionf(e, "Could not put record to database.", new Object[0]);
            return false;
        }
    }

    @Override // com.amazon.avod.media.contentcache.internal.CachedContentPersistence
    public boolean deleteRecord(WritableCacheRecord writableCacheRecord) {
        Lock lockWriteLock;
        VideoSpecification specification = writableCacheRecord.getSpecification();
        ReadWriteLock readWriteLock = DB_LOCK;
        readWriteLock.writeLock().lock();
        try {
            try {
                z = this.mDBOpenHelperSupplier.get().getWritableDatabase().delete("cached_content_table", getRecordForSpecQuery(specification), null) > 0;
                lockWriteLock = readWriteLock.writeLock();
            } catch (SQLiteException e) {
                DLog.exceptionf(e, "Could not open a writable database.", new Object[0]);
                lockWriteLock = DB_LOCK.writeLock();
            }
            lockWriteLock.unlock();
            return z;
        } catch (Throwable th) {
            DB_LOCK.writeLock().unlock();
            throw th;
        }
    }

    @Override // com.amazon.avod.media.contentcache.internal.CachedContentPersistence
    @Nonnull
    public List<WritableCacheRecord> getAllRecords() {
        return getRecordSet(null, null, null);
    }

    @Override // com.amazon.avod.media.contentcache.internal.CachedContentPersistence
    @Nonnull
    public List<WritableCacheRecord> getAllRecordsOfTypeDownloadedBefore(@Nonnull Date date, @Nonnull ContentSessionType contentSessionType) {
        String str;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = AnonymousClass3.$SwitchMap$com$amazon$avod$content$ContentSessionType[contentSessionType.ordinal()];
        if (i == 1) {
            str = String.format(Locale.US, "%s < ? AND %s IN (?, ?)", "download_time_seconds", "content_type");
        } else if (i == 2) {
            str = String.format(Locale.US, "%s < ? AND %s NOT IN (?, ?)", "download_time_seconds", "content_type");
        } else {
            throw new IllegalStateException(String.format(Locale.US, "Unexpected content session type: %s", contentSessionType));
        }
        return getRecordSet(str, new String[]{Long.toString(TimeUnit.MILLISECONDS.toSeconds(calendar.getTimeInMillis())), ContentType.LiveStreaming.name(), ContentType.External.name()}, null);
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.contentcache.db.DBCachedContentPersistence$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$ContentSessionType;

        static {
            int[] iArr = new int[ContentSessionType.values().length];
            $SwitchMap$com$amazon$avod$content$ContentSessionType = iArr;
            try {
                iArr[ContentSessionType.LIVE_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.CONTENT_CACHE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.amazon.avod.media.contentcache.internal.CachedContentPersistence
    @Nonnull
    public List<WritableCacheRecord> getRecordsIgnoringStartTime(VideoSpecification videoSpecification) {
        return getRecordSet(String.format(Locale.US, "%s = ? and IFNULL(%s, 'false') = ?  and %s = ? and IFNULL(%s, '[]') = ?", "title_id", "is_trailer", "audio_format", "audio_track_ids"), new String[]{videoSpecification.getTitleId(), Boolean.toString(videoSpecification.isTrailer()), videoSpecification.getAudioFormat().name(), DBUtils.convertListToJSON(videoSpecification.getAudioTrackIds())}, null);
    }

    @Override // com.amazon.avod.media.contentcache.internal.CachedContentPersistence
    public WritableCacheRecord getRecordByState(Downloadable.DownloadableState downloadableState, CachedContentPersistence.PriorityOrder priorityOrder) {
        List<WritableCacheRecord> recordSet = getRecordSet(String.format(Locale.US, "%s = ?", NotificationCompat.CATEGORY_STATUS), new String[]{downloadableState.name()}, priorityOrder == CachedContentPersistence.PriorityOrder.MAX ? ORDER_BY_DESC : ORDER_BY_ASC);
        if (recordSet.isEmpty()) {
            return null;
        }
        return recordSet.get(0);
    }

    @Override // com.amazon.avod.media.contentcache.internal.CachedContentPersistence
    public int getNumRecordsByState(Downloadable.DownloadableState downloadableState) {
        return getNumRecords(String.format(Locale.US, "%s = ?", NotificationCompat.CATEGORY_STATUS), new String[]{downloadableState.name()});
    }

    private int getNumRecords(String str, String[] strArr) {
        Lock lock;
        ReadWriteLock readWriteLock = DB_LOCK;
        readWriteLock.readLock().lock();
        int iQueryNumEntries = 0;
        try {
            try {
                iQueryNumEntries = (int) DatabaseUtils.queryNumEntries(this.mDBOpenHelperSupplier.get().getReadableDatabase(), "cached_content_table", str, strArr);
                lock = readWriteLock.readLock();
            } catch (SQLiteException e) {
                DLog.exceptionf(e, "Could not open a readable database.", new Object[0]);
                lock = DB_LOCK.readLock();
            }
            lock.unlock();
            return iQueryNumEntries;
        } catch (Throwable th) {
            DB_LOCK.readLock().unlock();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0060 A[PHI: r2
  0x0060: PHI (r2v3 android.database.Cursor) = (r2v1 android.database.Cursor), (r2v4 android.database.Cursor) binds: [B:19:0x005e, B:13:0x0050] A[DONT_GENERATE, DONT_INLINE]] */
    @javax.annotation.Nonnull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.List<com.amazon.avod.media.contentcache.internal.WritableCacheRecord> getRecordSet(java.lang.String r12, java.lang.String[] r13, java.lang.String r14) {
        /*
            r11 = this;
            java.util.concurrent.locks.ReadWriteLock r0 = com.amazon.avod.media.contentcache.db.DBCachedContentPersistence.DB_LOCK
            java.util.concurrent.locks.Lock r1 = r0.readLock()
            r1.lock()
            java.util.LinkedList r1 = com.google.common.collect.Lists.newLinkedList()
            r2 = 0
            com.amazon.avod.media.framework.MediaSystemSharedDependencies r3 = r11.mMediaSystemSharedDependencies     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteException -> L55
            com.amazon.avod.identity.IdentityShim r3 = r3.getIdentityShim()     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteException -> L55
            com.google.common.base.Optional r3 = r3.getCurrentUserAccountId()     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteException -> L55
            boolean r3 = r3.isPresent()     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteException -> L55
            if (r3 != 0) goto L26
            java.util.concurrent.locks.Lock r12 = r0.readLock()
            r12.unlock()
            return r1
        L26:
            com.amazon.avod.db.DBOpenHelperSupplier r0 = r11.mDBOpenHelperSupplier     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteException -> L55
            com.amazon.avod.db.DBOpenHelper r0 = r0.get()     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteException -> L55
            android.database.sqlite.SQLiteDatabase r3 = r0.getReadableDatabase()     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteException -> L55
            java.lang.String r4 = "cached_content_table"
            r5 = 0
            r8 = 0
            r9 = 0
            r6 = r12
            r7 = r13
            r10 = r14
            android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteException -> L55
        L3c:
            if (r2 == 0) goto L50
            boolean r12 = r2.moveToNext()     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteException -> L55
            if (r12 == 0) goto L50
            com.google.common.collect.ImmutableMap r12 = com.google.common.collect.ImmutableMap.of()     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteException -> L55
            com.amazon.avod.media.contentcache.internal.WritableCacheRecord r12 = r11.buildRecordFromCursor(r2, r12)     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteException -> L55
            r1.add(r12)     // Catch: java.lang.Throwable -> L53 android.database.sqlite.SQLiteException -> L55
            goto L3c
        L50:
            if (r2 == 0) goto L63
            goto L60
        L53:
            r12 = move-exception
            goto L6d
        L55:
            r12 = move-exception
            java.lang.String r13 = "Could not open a writable database."
            r14 = 0
            java.lang.Object[] r14 = new java.lang.Object[r14]     // Catch: java.lang.Throwable -> L53
            com.amazon.avod.util.DLog.exceptionf(r12, r13, r14)     // Catch: java.lang.Throwable -> L53
            if (r2 == 0) goto L63
        L60:
            r2.close()
        L63:
            java.util.concurrent.locks.ReadWriteLock r12 = com.amazon.avod.media.contentcache.db.DBCachedContentPersistence.DB_LOCK
            java.util.concurrent.locks.Lock r12 = r12.readLock()
            r12.unlock()
            return r1
        L6d:
            if (r2 == 0) goto L72
            r2.close()
        L72:
            java.util.concurrent.locks.ReadWriteLock r13 = com.amazon.avod.media.contentcache.db.DBCachedContentPersistence.DB_LOCK
            java.util.concurrent.locks.Lock r13 = r13.readLock()
            r13.unlock()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.media.contentcache.db.DBCachedContentPersistence.getRecordSet(java.lang.String, java.lang.String[], java.lang.String):java.util.List");
    }

    private WritableCacheRecord buildRecordFromCursor(Cursor cursor, Map<String, String> map) {
        IOException e;
        PlaybackEnvelope playbackEnvelope;
        VideoSpecification.Builder playbackToken = VideoSpecification.newBuilder().setTitleId(cursor.getString(cursor.getColumnIndex("title_id"))).setStartTime(TimeSpan.fromSeconds(cursor.getInt(cursor.getColumnIndex("start_position")))).setAudioFormat(AudioFormat.valueOf(cursor.getString(cursor.getColumnIndex("audio_format")))).setMediaQuality(MediaQuality.valueOf(cursor.getString(cursor.getColumnIndex("media_quality")))).setDuration(TimeSpan.fromSeconds(cursor.getInt(cursor.getColumnIndex("target_duration")))).setAudioTrackIds(DBUtils.convertJSONToList(cursor.getString(cursor.getColumnIndex("audio_track_ids")))).setPlaybackToken(cursor.getString(cursor.getColumnIndex("playback_token")));
        if (Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("is_trailer")))) {
            playbackToken.setContentType(ContentType.Trailer);
        } else {
            ContentType contentTypeFromString = ContentType.fromString(cursor.getString(cursor.getColumnIndex("content_type")));
            playbackToken.setContentType(contentTypeFromString);
            if (ContentType.isLive(contentTypeFromString)) {
                playbackToken.setAudioLanguage(this.mLiveAudioLanguage);
            }
        }
        String string = cursor.getString(cursor.getColumnIndex("playback_envelope"));
        PlaybackEnvelope playbackEnvelope2 = null;
        if (string != null) {
            try {
                playbackEnvelope = (PlaybackEnvelope) OBJECT_MAPPER.readValue(string, PlaybackEnvelopeData.class);
                try {
                    playbackToken.setPlaybackEnvelope(playbackEnvelope);
                } catch (IOException e2) {
                    e = e2;
                    DLog.errorf("Error deserializing playback envelope: %s", e.getMessage());
                }
            } catch (IOException e3) {
                e = e3;
                playbackEnvelope = null;
            }
            playbackEnvelope2 = playbackEnvelope;
        }
        playbackToken.setEPrivacyConsent(new EPrivacyConsentData(cursor.getString(cursor.getColumnIndex("eprivacy_cookie_consent_gvl")), cursor.getString(cursor.getColumnIndex("eprivacy_cookie_consent_avl"))));
        WritableCacheRecord writableCacheRecord = new WritableCacheRecord(playbackToken.build(), map);
        writableCacheRecord.setPriority(cursor.getInt(cursor.getColumnIndex("priority")));
        writableCacheRecord.setLatestSource(cursor.getString(cursor.getColumnIndex(PageUriUtils.SOURCE_QUERY_PARAM)));
        writableCacheRecord.setInitialSource(cursor.getString(cursor.getColumnIndex("initial_source")));
        writableCacheRecord.setAllSources(DBUtils.convertJSONToSet((String) MoreObjects.firstNonNull(cursor.getString(cursor.getColumnIndex("all_sources")), "")));
        writableCacheRecord.setNumberOfAttempts(cursor.getInt(cursor.getColumnIndex("retry_attempts")));
        writableCacheRecord.setFilesizeKb(cursor.getInt(cursor.getColumnIndex("filesize_kb")));
        writableCacheRecord.setIsLicensable(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("is_licensable"))));
        setContentUrlFromCursor(writableCacheRecord, cursor);
        writableCacheRecord.setState(Downloadable.DownloadableState.valueOf(cursor.getString(cursor.getColumnIndex(NotificationCompat.CATEGORY_STATUS))));
        writableCacheRecord.setEndTime(TimeSpan.fromSeconds(cursor.getInt(cursor.getColumnIndex("end_position"))));
        int i = cursor.getInt(cursor.getColumnIndex("download_time_seconds"));
        if (i > 0) {
            writableCacheRecord.setDownloadTime(new Date(TimeUnit.SECONDS.toMillis(i)));
        }
        writableCacheRecord.setDeferrable(Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex("is_deferrable"))));
        writableCacheRecord.setOfflineKeyId(cursor.getString(cursor.getColumnIndex("offline_keyid")));
        String string2 = cursor.getString(cursor.getColumnIndex("correlation_id"));
        if (string2 != null) {
            writableCacheRecord.setCorrelationId(string2);
        }
        if (playbackEnvelope2 != null) {
            writableCacheRecord.setPlaybackEnvelope(playbackEnvelope2);
        }
        return writableCacheRecord;
    }

    private static ContentValues buildContentValuesFromRecord(WritableCacheRecord writableCacheRecord) {
        ContentValues contentValues = new ContentValues();
        VideoSpecification specification = writableCacheRecord.getSpecification();
        contentValues.put("title_id", specification.getTitleId());
        contentValues.put("is_trailer", Boolean.toString(specification.isTrailer()));
        contentValues.put("start_position", Integer.valueOf(specification.getStartTime().getTotalSeconds()));
        contentValues.put("audio_format", specification.getAudioFormat().name());
        contentValues.put("media_quality", specification.getMediaQuality().name());
        contentValues.put("target_duration", Integer.valueOf(specification.getDuration().getTotalSeconds()));
        contentValues.put("audio_track_ids", DBUtils.convertListToJSON(specification.getAudioTrackIds()));
        contentValues.put("end_position", Integer.valueOf(writableCacheRecord.getLastCachedTime().getTotalSeconds()));
        contentValues.put("playback_token", specification.getPlaybackToken());
        AudioVideoUrls audioVideoUrls = writableCacheRecord.getAudioVideoUrls();
        contentValues.put("encode_version", audioVideoUrls == null ? null : writableCacheRecord.getAudioVideoUrls().getEncodeId());
        contentValues.put("aux_cache_key", audioVideoUrls == null ? null : writableCacheRecord.getAudioVideoUrls().getAuxCacheKey());
        if (audioVideoUrls != null) {
            putContentUrlList(contentValues, audioVideoUrls.getContentUrls());
            putAudioTrackMetadataList(contentValues, audioVideoUrls.getAudioTrackMetadataList());
            putReturnedTitleRendition(contentValues, audioVideoUrls.getReturnedTitleRendition());
            putCuePointPlaylistInfo(contentValues, audioVideoUrls.getCuepointPlaylistInfo());
            putFailoverMap(contentValues, audioVideoUrls.getFailoverManager());
            if (writableCacheRecord.getSessionType() == ContentSessionType.LIVE_CACHE) {
                putPlaybackSettings(contentValues, audioVideoUrls.getPlaybackSettingsOverride());
            }
        }
        contentValues.put("priority", Integer.valueOf(writableCacheRecord.getPriority()));
        contentValues.put(PageUriUtils.SOURCE_QUERY_PARAM, writableCacheRecord.getLatestSource());
        contentValues.put("initial_source", writableCacheRecord.getInitialSource());
        contentValues.put("all_sources", DBUtils.convertSetToJSON(writableCacheRecord.getAllSources()));
        contentValues.put(NotificationCompat.CATEGORY_STATUS, writableCacheRecord.getDownloadableState().name());
        contentValues.put("retry_attempts", Integer.valueOf(writableCacheRecord.getNumberOfAttempts()));
        contentValues.put("filesize_kb", Long.valueOf(writableCacheRecord.getFilesizeKB()));
        contentValues.put("is_licensable", Boolean.toString(writableCacheRecord.isLicensable()));
        if (writableCacheRecord.getDownloadTime() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(writableCacheRecord.getDownloadTime());
            contentValues.put("download_time_seconds", Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(calendar.getTimeInMillis())));
        }
        contentValues.put("is_deferrable", Boolean.toString(writableCacheRecord.isDeferrable()));
        contentValues.put("offline_keyid", writableCacheRecord.getOfflineKeyId());
        contentValues.put("content_type", specification.getContentType().name());
        if (writableCacheRecord.getPlaybackEnvelope() != null) {
            contentValues.put("correlation_id", writableCacheRecord.getPlaybackEnvelope() != null ? writableCacheRecord.getPlaybackEnvelope().getCorrelationId() : null);
            try {
                contentValues.put("playback_envelope", OBJECT_MAPPER.writeValueAsString(writableCacheRecord.getPlaybackEnvelope()));
            } catch (JsonProcessingException e) {
                DLog.errorf("Erroring writing playbacak envelope to DB: %s", e.getMessage());
            }
        }
        contentValues.put("eprivacy_cookie_consent_gvl", specification.getEPrivacyConsent().getEPrivacyConsentGVL());
        contentValues.put("eprivacy_cookie_consent_avl", specification.getEPrivacyConsent().getEPrivacyConsentAVL());
        return contentValues;
    }

    private static void putContentUrlList(ContentValues contentValues, @Nonnull List<ContentUrl> list) {
        Preconditions.checkNotNull(list, "url parameter cannot be null.");
        Collection collectionFilter = Collections2.filter(list, new Predicate<ContentUrl>() { // from class: com.amazon.avod.media.contentcache.db.DBCachedContentPersistence.2
            @Override // com.google.common.base.Predicate
            public boolean apply(ContentUrl contentUrl) {
                return (contentUrl == null || contentUrl.getUrl() == null) ? false : true;
            }
        });
        if (collectionFilter.size() > 0) {
            try {
                contentValues.put("urls_json", OBJECT_MAPPER.writeValueAsString(collectionFilter));
            } catch (IOException e) {
                DLog.warnf(e.getMessage());
            }
        }
    }

    static void setContentUrlFromCursor(WritableCacheRecord writableCacheRecord, Cursor cursor) {
        List list;
        Map<String, String> mapEmptyMap;
        int columnIndex = cursor.getColumnIndex("urls_json");
        String string = columnIndex != -1 ? cursor.getString(columnIndex) : null;
        if (Strings.isNullOrEmpty(string)) {
            list = null;
        } else {
            try {
                list = (List) OBJECT_MAPPER.readValue(string, CONTENT_URL_COLLECTION_TYPE);
            } catch (IOException e) {
                DLog.warnf(e.getMessage());
                list = null;
            }
        }
        List<AudioTrackMetadata> audioTrackMetadataList = getAudioTrackMetadataList(cursor);
        ReturnedTitleRendition returnedTitleRendition = getReturnedTitleRendition(cursor);
        FailoverManager failoverMap = getFailoverMap(cursor);
        if (writableCacheRecord.getSessionType() == ContentSessionType.LIVE_CACHE) {
            mapEmptyMap = getPlaybackSettings(cursor);
        } else {
            mapEmptyMap = Collections.emptyMap();
        }
        CuepointPlaylistInfo cuepointPlaylistInfo = getCuepointPlaylistInfo(cursor);
        String string2 = cursor.getString(cursor.getColumnIndex("encode_version"));
        String string3 = cursor.getColumnIndex("aux_cache_key") != -1 ? cursor.getString(cursor.getColumnIndex("aux_cache_key")) : null;
        if (list != null && list.size() > 0 && string2 != null) {
            if (audioTrackMetadataList.size() > 0) {
                writableCacheRecord.setAudioVideoUrls(new AudioVideoUrlsBuilder().setContentUrls(ImmutableList.copyOf((Collection) list)).setAudioTrackMetadataList(ImmutableList.copyOf((Collection) audioTrackMetadataList)).setEncodeId(string2).setPlaybackSettingsOverride(mapEmptyMap).setFailoverManager(failoverMap).setReturnedTitleRendition(returnedTitleRendition).setCuepointPlaylistInfo(cuepointPlaylistInfo).setAuxCacheKey(string3).build());
                return;
            } else {
                writableCacheRecord.setAudioVideoUrls(new AudioVideoUrlsBuilder().setContentUrls(ImmutableList.copyOf((Collection) list)).setAudioTrackMetadataList(ImmutableList.of()).setEncodeId(string2).setPlaybackSettingsOverride(mapEmptyMap).setFailoverManager(failoverMap).setReturnedTitleRendition(returnedTitleRendition).setCuepointPlaylistInfo(cuepointPlaylistInfo).setAuxCacheKey(string3).build());
                return;
            }
        }
        ContentUrl contentUrl = new ContentUrl(cursor.getString(cursor.getColumnIndex(ImagesContract.URL)), cursor.getString(cursor.getColumnIndex("session_id")), cursor.getString(cursor.getColumnIndex("cdn")));
        if (contentUrl.getUrl() != null) {
            writableCacheRecord.setAudioVideoUrls(new AudioVideoUrlsBuilder().setContentUrls(ImmutableList.of(contentUrl)).setAudioTrackMetadataList(ImmutableList.of()).setEncodeId(string2).setPlaybackSettingsOverride(Collections.emptyMap()).setReturnedTitleRendition(returnedTitleRendition).build());
        }
    }

    static void putAudioTrackMetadataList(@Nonnull ContentValues contentValues, @Nonnull List<AudioTrackMetadata> list) {
        Preconditions.checkNotNull(contentValues, "contentValues");
        Preconditions.checkNotNull(list, "audioTrackMetadataList");
        if (list.size() > 0) {
            try {
                contentValues.put("audio_track_metadata_json", OBJECT_MAPPER.writeValueAsString(list));
            } catch (IOException e) {
                DLog.warnf(e.getMessage());
            }
        }
    }

    @Nonnull
    static List<AudioTrackMetadata> getAudioTrackMetadataList(@Nonnull Cursor cursor) {
        String string;
        Preconditions.checkNotNull(cursor, "cursor");
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        int columnIndex = cursor.getColumnIndex("audio_track_metadata_json");
        if (columnIndex != -1) {
            string = cursor.getString(columnIndex);
        } else {
            DLog.warnf("CachedDB table don't have column named audio_track_metadata_json");
            string = null;
        }
        if (Strings.isNullOrEmpty(string)) {
            return arrayListNewArrayList;
        }
        try {
            return (List) OBJECT_MAPPER.readValue(string, AUDIO_TRACK_METADATA_COLLECTION_TYPE);
        } catch (IOException e) {
            DLog.warnf(e.getMessage());
            return arrayListNewArrayList;
        }
    }

    static void putCuePointPlaylistInfo(@Nonnull ContentValues contentValues, @Nullable CuepointPlaylistInfo cuepointPlaylistInfo) {
        Preconditions.checkNotNull(contentValues, "contentValues");
        if (cuepointPlaylistInfo != null) {
            try {
                contentValues.put("cuepoint_playlist_info_json", OBJECT_MAPPER.writeValueAsString(cuepointPlaylistInfo));
            } catch (IOException e) {
                DLog.warnf(e.getMessage());
            }
        }
    }

    static CuepointPlaylistInfo getCuepointPlaylistInfo(@Nonnull Cursor cursor) {
        Preconditions.checkNotNull(cursor, "cursor");
        int columnIndex = cursor.getColumnIndex("cuepoint_playlist_info_json");
        if (columnIndex != -1) {
            String string = cursor.getString(columnIndex);
            if (Strings.isNullOrEmpty(string)) {
                return null;
            }
            try {
                return (CuepointPlaylistInfo) OBJECT_MAPPER.readValue(string, CuepointPlaylistInfo.class);
            } catch (IOException e) {
                DLog.warnf(e.getMessage());
                return null;
            }
        }
        DLog.warnf("CachedDB table doesn't have column named cuepoint_playlist_info_json");
        return null;
    }

    static void putReturnedTitleRendition(@Nonnull ContentValues contentValues, @Nullable ReturnedTitleRendition returnedTitleRendition) {
        Preconditions.checkNotNull(contentValues, "contentValues");
        if (returnedTitleRendition != null) {
            try {
                contentValues.put("returned_title_rendition", OBJECT_MAPPER.writeValueAsString(returnedTitleRendition));
            } catch (IOException e) {
                DLog.warnf("Failed to putReturnedTitleRendition in CacheDB due to %s", e.getMessage());
            }
        }
    }

    static void putFailoverMap(@Nonnull ContentValues contentValues, @Nullable FailoverManager failoverManager) {
        Preconditions.checkNotNull(contentValues, "contentValues");
        if (failoverManager != null) {
            try {
                contentValues.put("failover_map", OBJECT_MAPPER.writeValueAsString(failoverManager));
            } catch (IOException e) {
                DLog.warnf("Failed to putFailoverMap in CacheDB due to %s", e.getMessage());
            }
        }
    }

    static void putPlaybackSettings(@Nonnull ContentValues contentValues, @Nonnull Map<String, String> map) {
        Preconditions.checkNotNull(contentValues, "contentValues");
        try {
            contentValues.put("playback_settings", OBJECT_MAPPER.writeValueAsString(map));
        } catch (IOException e) {
            DLog.warnf("Failed to putPlaybackSettings in CacheDB due to %s", e.getMessage());
        }
    }

    @Nullable
    static ReturnedTitleRendition getReturnedTitleRendition(@Nonnull Cursor cursor) {
        String string;
        Preconditions.checkNotNull(cursor, "cursor");
        int columnIndex = cursor.getColumnIndex("returned_title_rendition");
        if (columnIndex != -1) {
            string = cursor.getString(columnIndex);
        } else {
            DLog.warnf("CachedDB table don't have column named returned_title_rendition");
            string = null;
        }
        if (!Strings.isNullOrEmpty(string)) {
            try {
                return (ReturnedTitleRendition) OBJECT_MAPPER.readValue(string, ReturnedTitleRendition.class);
            } catch (IOException e) {
                DLog.warnf("Failed to getReturnedTitleRendition from CacheDB due to %s", e.getMessage());
            }
        }
        return null;
    }

    @Nullable
    static FailoverManager getFailoverMap(@Nonnull Cursor cursor) {
        Preconditions.checkNotNull(cursor, "cursor");
        String string = cursor.getString(cursor.getColumnIndex("failover_map"));
        if (Strings.isNullOrEmpty(string)) {
            return null;
        }
        try {
            return (FailoverManager) OBJECT_MAPPER.readValue(string, FailoverManager.class);
        } catch (IOException e) {
            DLog.warnf("Failed to getFailoverMap from CacheDB due to %s", e.getMessage());
            return null;
        }
    }

    @Nonnull
    static Map<String, String> getPlaybackSettings(@Nonnull Cursor cursor) {
        Preconditions.checkNotNull(cursor, "cursor");
        String string = cursor.getString(cursor.getColumnIndex("playback_settings"));
        if (!Strings.isNullOrEmpty(string)) {
            try {
                return (Map) OBJECT_MAPPER.readValue(string, PLAYBACK_SETTINGS_TYPE_REF);
            } catch (IOException e) {
                DLog.warnf("Failed to getPlaybackSettings from CacheDB due to %s", e.getMessage());
            }
        }
        return Collections.emptyMap();
    }
}
