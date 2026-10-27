package com.amazon.avod.userdownload;

import android.util.Base64;
import com.amazon.avod.content.DownloadStoreType;
import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.content.urlvending.CuepointPlaylistInfo;
import com.amazon.avod.drm.db.DrmRecord;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.MediaQuality;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.settings.DownloadQuality;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.amazon.avod.util.Preconditions2;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class PlaybackDownload implements PVDownloadPlayerShim {
    private final Optional<Long> mActualRuntime;
    private final AudioFormat mAudioFormat;
    private final ImmutableList<String> mAudioTrackIds;
    private final ImmutableList<AudioTrackMetadata> mAudioTrackMetadataList;

    @Nullable
    private final CuepointPlaylistInfo mCuepointPlaylistInfo;
    private final DownloadKey mDownloadKey;
    private final MediaQuality mDownloadQuality;
    private final DownloadStoreType mDownloadStoreType;
    private final Optional<String> mDrmAssetId;
    private final Optional<DrmRecord> mDrmRecord;
    private final long mFileSizeBytes;
    private final float mPercentage;
    private final Optional<MediaErrorCode> mPersistedErrorCode;
    private final Optional<String> mPlayerSDKAudioStreamMatchers;
    private final Optional<String> mPlayerSDKPlaybackToken;
    private final Optional<String> mPlayerSDKTimedTextStreamMatchers;
    private final ProgressMilestone mProgressMilestone;
    private final DateFormat mQosDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    private final int mQueuePosition;
    private final Optional<File> mRelativeStoragePath;
    private final String mSessionContext;
    private final Optional<String> mSessionId;
    private final UserDownloadState mState;
    private final File mStoragePath;
    private final Optional<String> mUrl;
    private final UserDownloadLocation mUserDownloadLocation;
    private static final ImmutableMap<MediaQuality, String> QOS_MEDIA_QUALITY_CONVERSION = (ImmutableMap) Preconditions2.checkFullKeyMapping(MediaQuality.class, ImmutableMap.of(MediaQuality.LOWEST, DownloadQuality.DATA_SAVER.name(), MediaQuality.LOW, DownloadQuality.GOOD.name(), MediaQuality.MEDIUM, DownloadQuality.BETTER.name(), MediaQuality.HIGH, DownloadQuality.BEST.name(), MediaQuality.HIGHEST, "Unknown"));
    private static final long BYTES_PER_MEGABYTE = DataUnit.MEGABYTES.toBytes(1.0f);

    PlaybackDownload(@Nonnull DownloadKey downloadKey, @Nonnull String str, @Nonnull File file, @Nonnull Optional<File> optional, @Nonnull UserDownloadLocation userDownloadLocation, @Nonnull Optional<String> optional2, @Nonnull Optional<DrmRecord> optional3, @Nonnull UserDownloadState userDownloadState, @Nonnull MediaQuality mediaQuality, @Nonnull ImmutableList<String> immutableList, @Nonnull AudioFormat audioFormat, @Nonnull Optional<MediaErrorCode> optional4, @Nonnull Optional<String> optional5, @Nonnull Optional<String> optional6, @Nonnull Optional<Long> optional7, @Nonnull ProgressMilestone progressMilestone, @Nonnegative float f, @Nonnegative long j, int i, @Nonnull Optional<String> optional8, @Nonnull Optional<String> optional9, @Nonnull Optional<String> optional10, @Nonnull ImmutableList<AudioTrackMetadata> immutableList2, @Nonnull DownloadStoreType downloadStoreType, @Nullable CuepointPlaylistInfo cuepointPlaylistInfo) {
        UserDownloadState userDownloadState2 = userDownloadState;
        this.mDownloadKey = (DownloadKey) Preconditions.checkNotNull(downloadKey, "downloadKey");
        this.mSessionContext = (String) Preconditions.checkNotNull(str, "sessionContext");
        this.mStoragePath = (File) Preconditions.checkNotNull(file, "storagePath");
        this.mRelativeStoragePath = (Optional) Preconditions.checkNotNull(optional, "relativeStoragePath");
        this.mUserDownloadLocation = (UserDownloadLocation) Preconditions.checkNotNull(userDownloadLocation, "downloadLocation");
        Optional<String> optional11 = (Optional) Preconditions.checkNotNull(optional2, "drmAssetId");
        this.mDrmAssetId = optional11;
        this.mDrmRecord = (Optional) Preconditions.checkNotNull(optional3, "drmRecord");
        if (optional3.isPresent()) {
            Preconditions.checkArgument(optional11.isPresent(), "drmAssetId is required when drmRecord is present");
        }
        Preconditions.checkNotNull(userDownloadState, "state");
        Optional<MediaErrorCode> optionalOf = optional4;
        Preconditions.checkNotNull(optionalOf, "persistedErrorCode");
        if (!UserDownloadState.WORK_NEEDED_STATES.contains(userDownloadState) && optional4.isPresent()) {
            Preconditions2.failWeakly("Unexpected state %s in the presence of an errorCode - %s. Overriding state to ERROR", userDownloadState2, optional4.orNull());
            userDownloadState2 = UserDownloadState.ERROR;
        } else if (userDownloadState2 == UserDownloadState.ERROR && !optional4.isPresent()) {
            Preconditions2.failWeakly("Error code is absent but download state is ERROR. Overriding error code to default.", new Object[0]);
            optionalOf = Optional.of(StandardErrorCode.DOWNLOAD_UNKNOWN_ERROR);
        }
        this.mState = userDownloadState2;
        this.mPersistedErrorCode = optionalOf;
        this.mDownloadQuality = (MediaQuality) Preconditions.checkNotNull(mediaQuality, "downloadQuality");
        this.mAudioTrackIds = (ImmutableList) Preconditions.checkNotNull(immutableList, "audioTrackIds");
        this.mAudioFormat = (AudioFormat) Preconditions.checkNotNull(audioFormat, "audioFormat");
        this.mUrl = (Optional) Preconditions.checkNotNull(optional5, ImagesContract.URL);
        this.mSessionId = (Optional) Preconditions.checkNotNull(optional6, Splash.PARAMS_SESSION_ID);
        Optional<Long> optional12 = (Optional) Preconditions.checkNotNull(optional7, "actualRuntime");
        this.mActualRuntime = optional12;
        if (optional12.isPresent()) {
            Preconditions2.checkNonNegative(optional12.get().longValue(), "mActualRuntime.get()");
        }
        this.mProgressMilestone = (ProgressMilestone) Preconditions.checkNotNull(progressMilestone, "progressMilestone");
        Preconditions.checkArgument(f >= 0.0f, "percentage = %s, percentage should be >= 0", Float.valueOf(f));
        this.mPercentage = Math.min(f, 100.0f);
        this.mFileSizeBytes = Preconditions2.checkNonNegative(j, "fileSizeBytes");
        this.mQueuePosition = i;
        this.mAudioTrackMetadataList = (ImmutableList) Preconditions.checkNotNull(immutableList2, "audioTrackMetadataList");
        this.mDownloadStoreType = (DownloadStoreType) Preconditions.checkNotNull(downloadStoreType, "downloadStoreType");
        this.mPlayerSDKPlaybackToken = (Optional) Preconditions.checkNotNull(optional8, "playerSDKPlaybackToken");
        this.mPlayerSDKAudioStreamMatchers = (Optional) Preconditions.checkNotNull(optional9, "playerSDKAudioStreamMatchers");
        this.mPlayerSDKTimedTextStreamMatchers = (Optional) Preconditions.checkNotNull(optional10, "playerSDKTimedTextStreamMatchers");
        this.mCuepointPlaylistInfo = cuepointPlaylistInfo;
    }

    public static Builder newBuilder(@Nonnull PlaybackDownload playbackDownload) {
        return newBuilder(playbackDownload, PlaybackDownloads.getInstance().getLocationConfig());
    }

    public static Builder newBuilder(@Nonnull String str) {
        return newBuilder(str, PlaybackDownloads.getInstance().getLocationConfig());
    }

    public static Builder newBuilder(@Nonnull PlaybackDownload playbackDownload, @Nonnull PlaybackDownloadLocationConfig playbackDownloadLocationConfig) {
        return new Builder(playbackDownloadLocationConfig);
    }

    public static Builder newBuilder(@Nonnull String str, @Nonnull PlaybackDownloadLocationConfig playbackDownloadLocationConfig) {
        return new Builder(new DownloadKey(str), playbackDownloadLocationConfig);
    }

    @Nonnull
    public String getAsin() {
        return this.mDownloadKey.getAsin();
    }

    @Nonnull
    public Map<String, String> getPlaybackSessionContext() {
        return DownloadSessionContextUtil.JSONToMap(getSessionContext());
    }

    @Nullable
    public String getPlayerSDKPlaybackToken() {
        return this.mPlayerSDKPlaybackToken.orNull();
    }

    @Nullable
    public String getPlayerSDKAudioStreamMatchers() {
        return this.mPlayerSDKAudioStreamMatchers.orNull();
    }

    @Nullable
    public String getPlayerSDKTimedTextStreamMatchers() {
        return this.mPlayerSDKTimedTextStreamMatchers.orNull();
    }

    @Nonnull
    public Optional<String> getDrmAssetId() {
        return this.mDrmAssetId;
    }

    @Override // com.amazon.avod.userdownload.PVDownloadPlayerShim
    @Nonnull
    public Optional<DrmRecord> getDrmRecord() {
        return this.mDrmRecord;
    }

    @Override // com.amazon.avod.userdownload.PVDownloadPlayerShim
    @Nonnull
    @Deprecated
    public Optional<String> getOfflineKeyId() {
        if (this.mDrmRecord.isPresent()) {
            return Optional.fromNullable(this.mDrmRecord.get().getOfflineKeyId());
        }
        return Optional.absent();
    }

    @Override // com.amazon.avod.userdownload.PVDownloadPlayerShim
    @Nonnull
    @Deprecated
    public Optional<DrmScheme> getDrmScheme() {
        return this.mDrmRecord.isPresent() ? Optional.of(this.mDrmRecord.get().getDrmScheme()) : Optional.absent();
    }

    @Override // com.amazon.avod.userdownload.PVDownloadPlayerShim
    @Nonnull
    @Deprecated
    public Optional<RendererSchemeType> getRendererSchemeType() {
        if (this.mDrmRecord.isPresent()) {
            return Optional.of(this.mDrmRecord.get().getRendererSchemeType());
        }
        return Optional.absent();
    }

    @Override // com.amazon.avod.userdownload.PVDownloadPlayerShim
    @Nonnull
    public ImmutableList<AudioTrackMetadata> getAudioTrackMetadataList() {
        return this.mAudioTrackMetadataList;
    }

    @Nonnull
    public Optional<CuepointPlaylistInfo> getCuepointPlaylistInfo() {
        return Optional.fromNullable(this.mCuepointPlaylistInfo);
    }

    @Override // com.amazon.avod.userdownload.PVDownloadPlayerShim
    @Nonnull
    public DownloadStoreType getDownloadStoreType() {
        return this.mDownloadStoreType;
    }

    @Override // com.amazon.avod.userdownload.PVDownloadPlayerShim
    @Nonnull
    public File getStoragePath() {
        return this.mStoragePath;
    }

    @Nonnull
    public Optional<File> getRelativeStoragePath() {
        return this.mRelativeStoragePath;
    }

    @Nonnull
    public UserDownloadLocation getUserDownloadLocation() {
        return this.mUserDownloadLocation;
    }

    @Override // com.amazon.avod.userdownload.PVDownloadPlayerShim
    @Nonnull
    public UserDownloadState getState() {
        return this.mState;
    }

    @Override // com.amazon.avod.userdownload.PVDownloadPlayerShim
    @Nonnull
    public MediaQuality getDownloadQuality() {
        return this.mDownloadQuality;
    }

    @Override // com.amazon.avod.userdownload.PVDownloadPlayerShim
    @Nonnull
    public ImmutableList<String> getAudioTrackIds() {
        return this.mAudioTrackIds;
    }

    @Override // com.amazon.avod.userdownload.PVDownloadPlayerShim
    @Nonnull
    public AudioFormat getAudioFormat() {
        return this.mAudioFormat;
    }

    @Nonnull
    public Optional<MediaErrorCode> getErrorCode() {
        return this.mState == UserDownloadState.ERROR ? this.mPersistedErrorCode : Optional.absent();
    }

    @Nonnull
    @Deprecated
    public Optional<MediaErrorCode> getPersistedErrorCode() {
        return this.mPersistedErrorCode;
    }

    @Override // com.amazon.avod.userdownload.PVDownloadPlayerShim
    @Nonnull
    public Optional<String> getUrl() {
        return this.mUrl;
    }

    @Override // com.amazon.avod.userdownload.PVDownloadPlayerShim
    @Nonnull
    public Optional<String> getSessionId() {
        return this.mSessionId;
    }

    @Nonnegative
    public Optional<Long> getActualRuntimeInMs() {
        return this.mActualRuntime;
    }

    @Nonnull
    public ProgressMilestone getProgressMilestone() {
        return this.mProgressMilestone;
    }

    public boolean isReadyToWatch() {
        return this.mProgressMilestone.getNumericValue() >= ProgressMilestone.READY_TO_WATCH.getNumericValue();
    }

    public boolean isCompleted() {
        return this.mProgressMilestone.getNumericValue() >= ProgressMilestone.COMPLETED.getNumericValue();
    }

    @Nonnegative
    public float getPercentage() {
        return this.mPercentage;
    }

    public long getFileSizeBytes() {
        return this.mFileSizeBytes;
    }

    @Nonnegative
    public long getDownloadedFileSize() {
        return (long) ((getPercentage() / 100.0f) * this.mFileSizeBytes);
    }

    public int getQueuePosition() {
        return this.mQueuePosition;
    }

    public String getOpaqueDownloadKey() {
        return this.mDownloadKey.asOpaqueKey();
    }

    @Nonnull
    public String getSessionContext() {
        return this.mSessionContext;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PlaybackDownload) {
            return this.mDownloadKey.equals(((PlaybackDownload) obj).mDownloadKey);
        }
        return false;
    }

    public int hashCode() {
        return this.mDownloadKey.hashCode();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues().add("Asin", this.mDownloadKey.getAsin()).add("Owner", DLog.maskString(getSessionContext())).add("SessionId", this.mSessionId.orNull()).add("Error", this.mPersistedErrorCode.orNull()).add("State", this.mState).toString();
    }

    @Nonnull
    public String toQosNote() {
        this.mQosDateFormat.setTimeZone(TimeZone.getDefault());
        MoreObjects.ToStringHelper toStringHelperAdd = MoreObjects.toStringHelper("").add("Owner", getSessionContext()).add("Loc", this.mUserDownloadLocation.getShortName()).add("Store", getDownloadStoreType().getShortName()).add("Qual", QOS_MEDIA_QUALITY_CONVERSION.get(this.mDownloadQuality));
        if (this.mPersistedErrorCode.isPresent()) {
            toStringHelperAdd.add("Error", this.mPersistedErrorCode.get());
        }
        toStringHelperAdd.add("Audio", this.mAudioTrackIds);
        toStringHelperAdd.add("Audio_Fmt", this.mAudioFormat);
        long j = this.mFileSizeBytes;
        if (j > 0) {
            toStringHelperAdd.add("Size", String.format(Locale.US, "%.2fMB", Double.valueOf(j / BYTES_PER_MEGABYTE)));
        }
        if (getDrmRecord().isPresent()) {
            toStringHelperAdd.addValue(getDrmRecord().get().toQosNote());
            UserDownloadState userDownloadState = this.mState;
            if (userDownloadState != UserDownloadState.DOWNLOADED && userDownloadState != UserDownloadState.DOWNLOADING) {
                toStringHelperAdd.add("Queue_Pos", this.mQueuePosition);
            }
        }
        if (this.mUrl.isPresent()) {
            toStringHelperAdd.add("Url", this.mUrl.get().length() >= 50 ? this.mUrl.get().substring(7, 50) : this.mUrl.get());
        }
        return toStringHelperAdd.toString();
    }

    @Nonnull
    public String toShortQosNote() {
        return MoreObjects.toStringHelper("").omitNullValues().add("Asin", this.mDownloadKey.getAsin()).add("Owner", DLog.maskString(getSessionContext())).toString();
    }

    @Immutable
    static final class DownloadKey {
        static final Joiner.MapJoiner OPAQUE_KEY_BUILDER = Joiner.on("&").withKeyValueSeparator("=");
        static final Splitter.MapSplitter OPAQUE_KEY_READER = Splitter.on("&").withKeyValueSeparator("=");
        private final String mAsin;

        DownloadKey(@Nonnull String str) {
            this.mAsin = (String) Preconditions.checkNotNull(str, "asin");
        }

        @Nonnull
        public String getAsin() {
            return this.mAsin;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof DownloadKey) {
                return Objects.equal(this.mAsin, ((DownloadKey) obj).mAsin);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(this.mAsin);
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("Asin", this.mAsin).toString();
        }

        public String asOpaqueKey() {
            return Base64.encodeToString(OPAQUE_KEY_BUILDER.join(ImmutableMap.of("version", "2", "asin", getAsin())).getBytes(Charsets.UTF_8), 0);
        }
    }

    public static class Builder {
        private Optional<Long> mActualRuntime;
        private AudioFormat mAudioFormat;
        private ImmutableList<String> mAudioTrackIds;
        private ImmutableList<AudioTrackMetadata> mAudioTrackMetadataList;

        @Nullable
        private CuepointPlaylistInfo mCuepointPlaylistInfo;
        private DownloadKey mDownloadKey;
        private UserDownloadLocation mDownloadLocation;
        private final PlaybackDownloadLocationConfig mDownloadLocationConfig;
        private MediaQuality mDownloadQuality;
        private DownloadStoreType mDownloadStoreType;
        private Optional<String> mDrmAssetId;
        private Optional<DrmRecord> mDrmRecord;
        private long mFileSizeBytes;
        private float mPercentage;
        private Optional<MediaErrorCode> mPersistedErrorCode;
        private Optional<String> mPlayerSDKAudioStreamMatchers;
        private Optional<String> mPlayerSDKPlaybackToken;
        private Optional<String> mPlayerSDKTimedTextStreamMatchers;
        private ProgressMilestone mProgressMilestone;
        private int mQueuePosition;
        private Optional<File> mRelativeStoragePath;
        private String mSessionContext;
        private Optional<String> mSessionId;
        private UserDownloadState mState;
        private File mStoragePath;
        private Optional<String> mUrl;

        private Builder(@Nonnull DownloadKey downloadKey, @Nonnull PlaybackDownloadLocationConfig playbackDownloadLocationConfig) {
            this.mPersistedErrorCode = Optional.absent();
            this.mUrl = Optional.absent();
            this.mSessionId = Optional.absent();
            this.mActualRuntime = Optional.absent();
            this.mProgressMilestone = ProgressMilestone.NOT_READY_TO_WATCH;
            this.mDrmAssetId = Optional.absent();
            this.mDrmRecord = Optional.absent();
            this.mPlayerSDKPlaybackToken = Optional.absent();
            this.mPlayerSDKAudioStreamMatchers = Optional.absent();
            this.mPlayerSDKTimedTextStreamMatchers = Optional.absent();
            this.mDownloadKey = (DownloadKey) Preconditions.checkNotNull(downloadKey, "key");
            this.mDownloadLocationConfig = (PlaybackDownloadLocationConfig) Preconditions.checkNotNull(playbackDownloadLocationConfig, "downloadLocationConfig");
        }

        private Builder(@Nonnull PlaybackDownload playbackDownload, @Nonnull PlaybackDownloadLocationConfig playbackDownloadLocationConfig) {
            this.mPersistedErrorCode = Optional.absent();
            this.mUrl = Optional.absent();
            this.mSessionId = Optional.absent();
            this.mActualRuntime = Optional.absent();
            this.mProgressMilestone = ProgressMilestone.NOT_READY_TO_WATCH;
            this.mDrmAssetId = Optional.absent();
            this.mDrmRecord = Optional.absent();
            this.mPlayerSDKPlaybackToken = Optional.absent();
            this.mPlayerSDKAudioStreamMatchers = Optional.absent();
            this.mPlayerSDKTimedTextStreamMatchers = Optional.absent();
            Preconditions.checkNotNull(playbackDownload, "download");
            this.mDownloadLocationConfig = (PlaybackDownloadLocationConfig) Preconditions.checkNotNull(playbackDownloadLocationConfig, "downloadLocationConfig");
            this.mDownloadKey = new DownloadKey(playbackDownload.getAsin());
            this.mSessionContext = playbackDownload.getSessionContext();
            this.mState = playbackDownload.getState();
            this.mAudioTrackIds = playbackDownload.getAudioTrackIds();
            this.mAudioFormat = playbackDownload.getAudioFormat();
            this.mDownloadQuality = playbackDownload.getDownloadQuality();
            this.mPersistedErrorCode = playbackDownload.getPersistedErrorCode();
            this.mUrl = playbackDownload.getUrl();
            this.mSessionId = playbackDownload.getSessionId();
            this.mActualRuntime = playbackDownload.getActualRuntimeInMs();
            this.mQueuePosition = playbackDownload.getQueuePosition();
            this.mProgressMilestone = playbackDownload.getProgressMilestone();
            this.mPercentage = playbackDownload.getPercentage();
            this.mFileSizeBytes = playbackDownload.getFileSizeBytes();
            this.mDrmAssetId = playbackDownload.getDrmAssetId();
            this.mDrmRecord = playbackDownload.getDrmRecord();
            this.mStoragePath = playbackDownload.getStoragePath();
            this.mRelativeStoragePath = playbackDownload.getRelativeStoragePath();
            this.mDownloadLocation = playbackDownload.getUserDownloadLocation();
            this.mAudioTrackMetadataList = playbackDownload.getAudioTrackMetadataList();
            this.mDownloadStoreType = playbackDownload.getDownloadStoreType();
            this.mPlayerSDKPlaybackToken = Optional.fromNullable(playbackDownload.getPlayerSDKPlaybackToken());
            this.mPlayerSDKAudioStreamMatchers = Optional.fromNullable(playbackDownload.getPlayerSDKAudioStreamMatchers());
            this.mPlayerSDKTimedTextStreamMatchers = Optional.fromNullable(playbackDownload.getPlayerSDKTimedTextStreamMatchers());
            this.mCuepointPlaylistInfo = playbackDownload.getCuepointPlaylistInfo().orNull();
        }

        public PlaybackDownload build() {
            return new PlaybackDownload(this.mDownloadKey, this.mSessionContext, this.mStoragePath, this.mRelativeStoragePath, this.mDownloadLocation, this.mDrmAssetId, this.mDrmRecord, this.mState, this.mDownloadQuality, this.mAudioTrackIds, this.mAudioFormat, this.mPersistedErrorCode, this.mUrl, this.mSessionId, this.mActualRuntime, this.mProgressMilestone, this.mPercentage, this.mFileSizeBytes, this.mQueuePosition, this.mPlayerSDKPlaybackToken, this.mPlayerSDKAudioStreamMatchers, this.mPlayerSDKTimedTextStreamMatchers, this.mAudioTrackMetadataList, this.mDownloadStoreType, this.mCuepointPlaylistInfo);
        }

        public Builder setStoragePath(@Nonnull UserDownloadLocation userDownloadLocation, @Nonnull File file, @Nonnull Optional<File> optional) {
            this.mDownloadLocation = (UserDownloadLocation) Preconditions.checkNotNull(userDownloadLocation, "downloadLocation");
            Preconditions.checkNotNull(file, "absolutePath");
            Preconditions.checkNotNull(optional, "relativePath");
            this.mStoragePath = this.mDownloadLocationConfig.getAbsoluteStoragePathIfAvailable(this.mDownloadLocation, optional.orNull()).or(file);
            this.mRelativeStoragePath = optional;
            return this;
        }

        public Builder setDrmAssetId(@Nonnull Optional<String> optional) {
            this.mDrmAssetId = (Optional) Preconditions.checkNotNull(optional, "drmAssetId");
            return this;
        }

        public Builder setDrmRecord(@Nonnull Optional<DrmRecord> optional) {
            this.mDrmRecord = (Optional) Preconditions.checkNotNull(optional, "drmRecord");
            return this;
        }

        public Builder setAudioTrackMetadataList(@Nonnull ImmutableList<AudioTrackMetadata> immutableList) {
            this.mAudioTrackMetadataList = (ImmutableList) Preconditions.checkNotNull(immutableList, "audioTrackMetadataList");
            return this;
        }

        public Builder setCuepointPlaylistInfo(@Nullable CuepointPlaylistInfo cuepointPlaylistInfo) {
            this.mCuepointPlaylistInfo = cuepointPlaylistInfo;
            return this;
        }

        public Builder setDownloadStoreType(@Nonnull DownloadStoreType downloadStoreType) {
            this.mDownloadStoreType = (DownloadStoreType) Preconditions.checkNotNull(downloadStoreType, "downloadStoreType");
            return this;
        }

        public Builder setDownloadQuality(@Nonnull MediaQuality mediaQuality) {
            this.mDownloadQuality = (MediaQuality) Preconditions.checkNotNull(mediaQuality, "downloadQuality");
            return this;
        }

        public Builder setAudioTrackIds(@Nonnull ImmutableList<String> immutableList) {
            this.mAudioTrackIds = (ImmutableList) Preconditions.checkNotNull(immutableList, "audioTrackIds");
            return this;
        }

        public Builder setAudioFormat(@Nonnull AudioFormat audioFormat) {
            this.mAudioFormat = (AudioFormat) Preconditions.checkNotNull(audioFormat, "audioFormat");
            return this;
        }

        public Builder setState(@Nonnull UserDownloadState userDownloadState) {
            this.mState = (UserDownloadState) Preconditions.checkNotNull(userDownloadState, "state");
            return this;
        }

        public Builder setPersistedErrorCode(@Nonnull MediaErrorCode mediaErrorCode) {
            Preconditions.checkNotNull(mediaErrorCode, "errorCode");
            return setPersistedErrorCode(Optional.of(mediaErrorCode));
        }

        public Builder setPersistedErrorCode(@Nonnull Optional<MediaErrorCode> optional) {
            this.mPersistedErrorCode = (Optional) Preconditions.checkNotNull(optional, "errorCode");
            return this;
        }

        public Builder setProgressFromPersistence(@Nonnull ProgressMilestone progressMilestone, float f) {
            this.mProgressMilestone = (ProgressMilestone) Preconditions.checkNotNull(progressMilestone, "progressMilestone");
            this.mPercentage = f;
            return this;
        }

        public Builder updateProgress(@Nonnull ProgressMilestone progressMilestone, float f) {
            this.mProgressMilestone = (ProgressMilestone) Preconditions.checkNotNull(progressMilestone, "progressMilestone");
            this.mPercentage = f;
            return this;
        }

        public Builder setPercentage(float f) {
            this.mPercentage = f;
            return this;
        }

        public Builder setQueuePosition(int i) {
            this.mQueuePosition = i;
            return this;
        }

        public Builder setUrl(@Nonnull Optional<String> optional) {
            this.mUrl = (Optional) Preconditions.checkNotNull(optional, ImagesContract.URL);
            return this;
        }

        public Builder setSessionId(@Nullable String str) {
            return setSessionId(Optional.fromNullable(str));
        }

        public Builder setSessionId(@Nonnull Optional<String> optional) {
            this.mSessionId = (Optional) Preconditions.checkNotNull(optional, Splash.PARAMS_SESSION_ID);
            return this;
        }

        public Builder setActualRuntimeinMS(Optional<Long> optional) {
            this.mActualRuntime = optional;
            return this;
        }

        public Builder setFileSizeBytes(long j) {
            this.mFileSizeBytes = j;
            return this;
        }

        public Builder setSessionContext(@Nonnull String str) {
            this.mSessionContext = (String) Preconditions.checkNotNull(str, "sessionContext");
            return this;
        }

        public Builder setPlayerSDKPlaybackToken(@Nonnull Optional<String> optional) {
            this.mPlayerSDKPlaybackToken = optional;
            return this;
        }

        public Builder setPlayerSDKAudioStreamMatchers(@Nonnull Optional<String> optional) {
            this.mPlayerSDKAudioStreamMatchers = optional;
            return this;
        }

        public Builder setPlayerSDKTimedTextStreamMatchers(@Nonnull Optional<String> optional) {
            this.mPlayerSDKTimedTextStreamMatchers = optional;
            return this;
        }
    }
}
