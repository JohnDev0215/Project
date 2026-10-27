package com.amazon.avod.media.contentcache.internal;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.urlvending.AudioVideoUrls;
import com.amazon.avod.download.Downloadable;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.contentcache.CacheRecord;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.playback.renderer.RendererSchemeType;
import com.amazon.avod.userdownload.PVDownloadsPlayerShim;
import com.amazon.video.sdk.player.PlaybackEnvelope;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Range;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class WritableCacheRecord implements CacheRecord {
    private int mAttempts;
    private AudioVideoUrls mAudioVideoUrls;
    private String mCorrelationId;
    private Date mDownloadTime;
    private long mFilesizeKb;
    private String mInitialSource;
    private boolean mIsDeferrable;
    private boolean mIsLicensable;
    private TimeSpan mLastCachedTime;
    private String mLatestSource;
    private String mOfflineKeyId;
    private PlaybackEnvelope mPlaybackEnvelope;
    private int mPriority;
    private Map<String, String> mSessionContext;
    private VideoSpecification mSpecification;
    private Downloadable.DownloadableState mState;
    private Set<String> mAllSources = new HashSet();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override // com.amazon.avod.download.Downloadable
    @Nullable
    public DrmScheme getDrmScheme() {
        return null;
    }

    @Override // com.amazon.avod.download.Downloadable
    @Nullable
    public RendererSchemeType getRendererSchemeType() {
        return null;
    }

    public WritableCacheRecord(VideoSpecification videoSpecification, Map<String, String> map) {
        this.mSpecification = videoSpecification;
        this.mSessionContext = map;
        setEndTime(videoSpecification.getStartTime());
    }

    public String toString() {
        MoreObjects.ToStringHelper toStringHelperAdd = MoreObjects.toStringHelper(this).add("titleId", this.mSpecification.getTitleId()).add("isTrailer", this.mSpecification.isTrailer()).add("initialSource", this.mInitialSource).add("latestSource", this.mLatestSource).add("startTime", this.mSpecification.getStartTime()).add("endTime", this.mLastCachedTime);
        Date date = this.mDownloadTime;
        return toStringHelperAdd.add("downloadtime", date != null ? this.dateFormat.format(date) : null).add("isCurrentGeneration", isCurrentGeneration()).add("priority", this.mPriority).add("isLicensable", this.mIsLicensable).add("audioTrackIds", this.mSpecification.getAudioTrackIds()).add("allSources", this.mAllSources).add("isDeferrable", this.mIsDeferrable).add("offlineKeyId", this.mOfflineKeyId).add("audioVideoUrls", this.mAudioVideoUrls).add("filesizeKb", this.mFilesizeKb).add("correlationId", this.mCorrelationId).toString();
    }

    @Override // com.amazon.avod.download.Downloadable
    @Nonnull
    public VideoSpecification getSpecification() {
        return this.mSpecification;
    }

    @Override // com.amazon.avod.download.Downloadable
    @Nonnull
    public Map<String, String> getSessionContext() {
        return this.mSessionContext;
    }

    public void setSpecification(@Nonnull VideoSpecification videoSpecification) {
        this.mSpecification = (VideoSpecification) Preconditions.checkNotNull(videoSpecification, "videoSpecification");
    }

    @Override // com.amazon.avod.download.Downloadable
    @Nonnull
    public File getStoragePath() {
        return new File(new File(PVDownloadsPlayerShim.getInstance().getStorageHelper().getGlobalSharedDir(), getSessionType().getDataPartition()), getSpecification().getUniqueIdentifier());
    }

    @Nullable
    public Date getDownloadTime() {
        return this.mDownloadTime;
    }

    @Override // com.amazon.avod.download.Downloadable
    @Nonnull
    public ContentSessionType getSessionType() {
        return this.mSpecification.isLiveStream() ? ContentSessionType.LIVE_CACHE : ContentSessionType.CONTENT_CACHE;
    }

    @Override // com.amazon.avod.download.Downloadable
    public AudioVideoUrls getAudioVideoUrls() {
        return this.mAudioVideoUrls;
    }

    @Override // com.amazon.avod.download.Downloadable
    @Nonnull
    public Downloadable.DownloadableState getDownloadableState() {
        return this.mState;
    }

    public TimeSpan getLastCachedTime() {
        return this.mLastCachedTime;
    }

    @Override // com.amazon.avod.media.contentcache.CacheRecord
    public int getPriority() {
        return this.mPriority;
    }

    public String getInitialSource() {
        return this.mInitialSource;
    }

    public String getLatestSource() {
        return this.mLatestSource;
    }

    @Nonnull
    public Set<String> getAllSources() {
        return this.mAllSources;
    }

    public boolean isDeferrable() {
        return this.mIsDeferrable;
    }

    @Override // com.amazon.avod.media.contentcache.CacheRecord
    @Nullable
    public PlaybackEnvelope getPlaybackEnvelope() {
        return this.mPlaybackEnvelope;
    }

    public boolean coversTimestamp(TimeSpan timeSpan) {
        TimeSpan startTime = getSpecification().getStartTime();
        return Range.closedOpen(startTime, TimeSpan.add(startTime, getSpecification().getDuration())).contains(timeSpan);
    }

    @Override // com.amazon.avod.media.contentcache.CacheRecord
    public boolean beginsAtTimestamp(TimeSpan timeSpan) {
        return getSpecification().getStartTime().getTotalSeconds() == timeSpan.getTotalSeconds();
    }

    @Override // com.amazon.avod.media.contentcache.CacheRecord
    public CacheRecord.RecordState getCacheState() {
        TimeSpan startTime = getSpecification().getStartTime();
        TimeSpan timeSpanAdd = TimeSpan.add(startTime, getSpecification().getDuration());
        TimeSpan timeSpan = (TimeSpan) MoreObjects.firstNonNull(this.mLastCachedTime, startTime);
        if (timeSpan.greaterThanEquals(timeSpanAdd)) {
            return CacheRecord.RecordState.FULLY_CACHED;
        }
        if (timeSpan.greaterThan(startTime)) {
            return CacheRecord.RecordState.IN_PROGRESS;
        }
        return CacheRecord.RecordState.NOT_STARTED;
    }

    public boolean isCurrentGeneration() {
        return getDownloadableState() != Downloadable.DownloadableState.TOMBSTONED;
    }

    @Override // com.amazon.avod.download.Downloadable
    public boolean isLicensable() {
        return this.mIsLicensable;
    }

    @Override // com.amazon.avod.download.Downloadable
    public String getQosNote() {
        return String.format(Locale.US, "Latest request source: %s", this.mLatestSource);
    }

    public int getNumberOfAttempts() {
        return this.mAttempts;
    }

    public long getFilesizeKB() {
        return this.mFilesizeKb;
    }

    public void setEndTime(TimeSpan timeSpan) {
        Preconditions.checkArgument(timeSpan.greaterThanEquals(this.mSpecification.getStartTime()));
        this.mLastCachedTime = timeSpan;
    }

    public void setAudioVideoUrls(@Nullable AudioVideoUrls audioVideoUrls) {
        this.mAudioVideoUrls = audioVideoUrls;
    }

    public void setState(Downloadable.DownloadableState downloadableState) {
        this.mState = downloadableState;
    }

    public void setPriority(int i) {
        this.mPriority = i;
    }

    public void setInitialSource(String str) {
        this.mInitialSource = str;
    }

    public void setLatestSource(String str) {
        this.mLatestSource = str;
    }

    public void addSource(String str) {
        this.mAllSources.add(str);
    }

    public void setAllSources(Set<String> set) {
        this.mAllSources = set;
    }

    public void setNumberOfAttempts(int i) {
        this.mAttempts = i;
    }

    public void setFilesizeKb(long j) {
        this.mFilesizeKb = j;
    }

    public void setIsLicensable(boolean z) {
        this.mIsLicensable = z;
    }

    public void setDownloadTime(Date date) {
        this.mDownloadTime = date;
    }

    public void incrementAttemptCount() {
        this.mAttempts++;
    }

    public void setDeferrable(boolean z) {
        this.mIsDeferrable = z;
    }

    public void setCorrelationId(@Nullable String str) {
        this.mCorrelationId = str;
    }

    public void setPlaybackEnvelope(@Nullable PlaybackEnvelope playbackEnvelope) {
        this.mPlaybackEnvelope = playbackEnvelope;
    }

    @Override // com.amazon.avod.download.Downloadable
    @Nullable
    public String getOfflineKeyId() {
        return this.mOfflineKeyId;
    }

    public void setOfflineKeyId(@Nullable String str) {
        this.mOfflineKeyId = str;
    }
}
