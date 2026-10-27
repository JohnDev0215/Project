package com.amazon.avod.userdownload.internal;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.DownloadStoreType;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.content.urlvending.AudioVideoUrlsBuilder;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.download.Downloadable;
import com.amazon.avod.drm.db.DrmRecord;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.userdownload.PlaybackDownload;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.File;
import java.util.Collections;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackDownloadJob implements Downloadable {
    private final AudioVideoUrls mAudioVideoUrls;
    private final PlaybackDownload mDownload;
    private final Downloadable.DownloadableState mDownloadableState;
    private final Map<String, String> mSessionContext;
    private final VideoSpecification mVideoSpecification;

    @Override // com.amazon.avod.download.Downloadable
    @Nullable
    public String getQosNote() {
        return null;
    }

    @Override // com.amazon.avod.download.Downloadable
    public boolean isLicensable() {
        return true;
    }

    PlaybackDownloadJob(@Nonnull PlaybackDownload playbackDownload, @Nonnull Downloadable.DownloadableState downloadableState, @Nonnull Map<String, String> map) {
        PlaybackDownload playbackDownload2 = (PlaybackDownload) Preconditions.checkNotNull(playbackDownload, "download");
        this.mDownload = playbackDownload2;
        this.mSessionContext = (Map) Preconditions.checkNotNull(map, "sessionContext");
        this.mDownloadableState = (Downloadable.DownloadableState) Preconditions.checkNotNull(downloadableState, "downloadableState");
        Preconditions.checkArgument(downloadableState == Downloadable.DownloadableState.DELETE_IMMEDIATELY || downloadableState == Downloadable.DownloadableState.QUEUED, "Unsupported job type %s", downloadableState);
        this.mVideoSpecification = VideoSpecification.newBuilder().setAudioFormat(playbackDownload2.getAudioFormat()).setAudioTrackIds(playbackDownload2.getAudioTrackIds()).setMediaQuality(playbackDownload2.getDownloadQuality()).setReportingTag(playbackDownload2.getSessionId().orNull()).setTitleId(playbackDownload2.getAsin()).setUrl(playbackDownload2.getUrl().orNull()).build();
        this.mAudioVideoUrls = (playbackDownload2.getUrl().isPresent() && playbackDownload2.getSessionId().isPresent()) ? new AudioVideoUrlsBuilder().setContentUrls(ImmutableList.of(new ContentUrl(playbackDownload2.getUrl().get(), playbackDownload2.getSessionId().get()))).setAudioTrackMetadataList(playbackDownload2.getAudioTrackMetadataList()).setPlaybackSettingsOverride(Collections.emptyMap()).build() : null;
    }

    @Nonnull
    public PlaybackDownload getDownload() {
        return this.mDownload;
    }

    @Override // com.amazon.avod.download.Downloadable
    @Nonnull
    public VideoSpecification getSpecification() {
        return this.mVideoSpecification;
    }

    @Override // com.amazon.avod.download.Downloadable
    @Nonnull
    public Map<String, String> getSessionContext() {
        return this.mSessionContext;
    }

    @Override // com.amazon.avod.download.Downloadable
    @Nonnull
    public File getStoragePath() {
        return this.mDownload.getStoragePath();
    }

    @Override // com.amazon.avod.download.Downloadable
    @Nonnull
    public ContentSessionType getSessionType() {
        return ContentSessionType.DOWNLOAD;
    }

    @Override // com.amazon.avod.download.Downloadable
    @Nullable
    public AudioVideoUrls getAudioVideoUrls() {
        return this.mAudioVideoUrls;
    }

    @Override // com.amazon.avod.download.Downloadable
    @Nonnull
    public Downloadable.DownloadableState getDownloadableState() {
        return this.mDownloadableState;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaybackDownloadJob)) {
            return false;
        }
        PlaybackDownloadJob playbackDownloadJob = (PlaybackDownloadJob) obj;
        return Objects.equal(this.mDownload, playbackDownloadJob.mDownload) && Objects.equal(this.mDownloadableState, playbackDownloadJob.mDownloadableState) && Objects.equal(getStoragePath(), playbackDownloadJob.getStoragePath());
    }

    public int hashCode() {
        return Objects.hashCode(this.mDownload, this.mDownloadableState, getStoragePath());
    }

    public boolean isEquivalent(Downloadable downloadable) {
        return equals(downloadable);
    }

    @Override // com.amazon.avod.download.Downloadable
    @Nullable
    public String getOfflineKeyId() {
        DrmRecord drmRecordOrNull = this.mDownload.getDrmRecord().orNull();
        if (drmRecordOrNull == null) {
            return null;
        }
        return drmRecordOrNull.getOfflineKeyId();
    }

    @Override // com.amazon.avod.download.Downloadable
    @Nullable
    public DrmScheme getDrmScheme() {
        DrmRecord drmRecordOrNull = this.mDownload.getDrmRecord().orNull();
        if (drmRecordOrNull == null) {
            return null;
        }
        return drmRecordOrNull.getDrmScheme();
    }

    @Nonnull
    public DownloadStoreType getDownloadStoreType() {
        return this.mDownload.getDownloadStoreType();
    }

    @Override // com.amazon.avod.download.Downloadable
    @Nullable
    public RendererSchemeType getRendererSchemeType() {
        DrmRecord drmRecordOrNull = this.mDownload.getDrmRecord().orNull();
        if (drmRecordOrNull == null) {
            return null;
        }
        return drmRecordOrNull.getRendererSchemeType();
    }

    public String toString() {
        return MoreObjects.toStringHelper("Job").addValue(this.mDownload).toString();
    }
}
