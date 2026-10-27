package com.amazon.avod.content.smoothstream.manifest;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.image.ImageQualityLevelInterface;
import com.amazon.avod.content.image.ImageStreamIndexInterface;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.util.CastUtils;
import com.google.common.base.Preconditions;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class RefreshableImageStreamIndex extends RefreshableStreamIndex implements ImageStreamIndexInterface {
    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public /* bridge */ /* synthetic */ AudioFormat getAudioFormat() {
        return super.getAudioFormat();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ String getAudioTrackId() {
        return super.getAudioTrackId();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ long getChunkDurationInNanos(int i) {
        return super.getChunkDurationInNanos(i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ int getChunkIndexFromNanos(long j) {
        return super.getChunkIndexFromNanos(j);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ double getChunkQualityScore(@Nonnull QualityLevel qualityLevel, int i) {
        return super.getChunkQualityScore(qualityLevel, i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ long getChunkTimeInNanos(int i) {
        return super.getChunkTimeInNanos(i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ long getChunkTimeOffsetInNanos(int i) {
        return super.getChunkTimeOffsetInNanos(i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ QualityLevel getClosestQualityLevel(int i, int i2) {
        return super.getClosestQualityLevel(i, i2);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ VideoQualityLevel getClosestQualityLevelWithResolutionCap(int i, int i2, @Nonnull VideoResolution videoResolution) {
        return super.getClosestQualityLevelWithResolutionCap(i, i2, videoResolution);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nullable
    public /* bridge */ /* synthetic */ byte[] getDefaultKeyId() {
        return super.getDefaultKeyId();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ double getDisplayAspectRatio() {
        return super.getDisplayAspectRatio();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ long getEncodeTimeMillis(long j) {
        return super.getEncodeTimeMillis(j);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ long getExpectedChunkSizeInBytes(QualityLevel qualityLevel, int i) {
        return super.getExpectedChunkSizeInBytes(qualityLevel, i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ long getExpectedInitChunkSizeInBytes(QualityLevel qualityLevel) {
        return super.getExpectedInitChunkSizeInBytes(qualityLevel);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public /* bridge */ /* synthetic */ String getFourCC() {
        return super.getFourCC();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public /* bridge */ /* synthetic */ Map getHeaders(@Nonnull QualityLevel qualityLevel, int i) {
        return super.getHeaders(qualityLevel, i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ int getIndex() {
        return super.getIndex();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public /* bridge */ /* synthetic */ Map getInitHeaders(@Nonnull QualityLevel qualityLevel) {
        return super.getInitHeaders(qualityLevel);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ String getInitUrl(String str, QualityLevel qualityLevel, int i) {
        return super.getInitUrl(str, qualityLevel, i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ String getLanguage() {
        return super.getLanguage();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ float getMaxFrameRate() {
        return super.getMaxFrameRate();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ int getMaxHeight() {
        return super.getMaxHeight();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ int getMaxWidth() {
        return super.getMaxWidth();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ int getNumChunks() {
        return super.getNumChunks();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ QualityLevel getQualityLevel(int i, int i2) {
        return super.getQualityLevel(i, i2);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ QualityLevel getQualityLevel(int i, QualityLevel qualityLevel) {
        return super.getQualityLevel(i, qualityLevel);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ QualityLevel getQualityLevelGreaterThanEqual(int i, int i2) {
        return super.getQualityLevelGreaterThanEqual(i, i2);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ QualityLevel getQualityLevelLessThanEqual(int i, int i2) {
        return super.getQualityLevelLessThanEqual(i, i2);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ QualityLevel[] getSortedQualityLevels(int i) {
        return super.getSortedQualityLevels(i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ long getStreamDurationInNanos() {
        return super.getStreamDurationInNanos();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ long getStreamEndTimestampNanos() {
        return super.getStreamEndTimestampNanos();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ long getStreamHandle() {
        return super.getStreamHandle();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ long getStreamStartTimestampNanos() {
        return super.getStreamStartTimestampNanos();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public /* bridge */ /* synthetic */ StreamType getType() {
        return super.getType();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ String getUrl(String str, QualityLevel qualityLevel, int i) {
        return super.getUrl(str, qualityLevel, i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ boolean isAudio() {
        return super.isAudio();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ boolean isHdr() {
        return super.isHdr();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ boolean isLastDownloadableChunk(int i) {
        return super.isLastDownloadableChunk(i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ boolean isLastPlayableChunk(int i) {
        return super.isLastPlayableChunk(i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ boolean isOutOfWindow(int i) {
        return super.isOutOfWindow(i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ boolean isVideo() {
        return super.isVideo();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex
    public /* bridge */ /* synthetic */ void refresh(@Nonnull StreamIndex streamIndex) throws ContentException {
        super.refresh(streamIndex);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ void release() {
        super.release();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ boolean requiresInitFragments() {
        return super.requiresInitFragments();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public /* bridge */ /* synthetic */ JSONObject toJson() {
        return super.toJson();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public RefreshableImageStreamIndex(@Nonnull StreamIndex streamIndex) {
        super((StreamIndex) Preconditions.checkNotNull(streamIndex, "originalStreamIndex"));
    }

    @Override // com.amazon.avod.content.image.ImageStreamIndexInterface
    public int getNumPeriods() {
        readLock();
        try {
            return ((ImageStreamIndexInterface) CastUtils.castTo(this.mLatestStreamIndex, ImageStreamIndexInterface.class)).getNumPeriods();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.image.ImageStreamIndexInterface
    @Nonnull
    public ImageQualityLevelInterface[] getSortedImageQualityLevels(@Nonnull String str) {
        readLock();
        try {
            return ((ImageStreamIndexInterface) CastUtils.castTo(this.mLatestStreamIndex, ImageStreamIndexInterface.class)).getSortedImageQualityLevels(str);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.RefreshableStreamIndex, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public String getPeriodIdWithTimestampNanos(long j) {
        readLock();
        try {
            return ((ImageStreamIndexInterface) CastUtils.castTo(this.mLatestStreamIndex, ImageStreamIndexInterface.class)).getPeriodIdWithTimestampNanos(j);
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.image.ImageStreamIndexInterface
    @Nonnull
    public Set<String> getAllPeriodIds() {
        readLock();
        try {
            return ((ImageStreamIndexInterface) CastUtils.castTo(this.mLatestStreamIndex, ImageStreamIndexInterface.class)).getAllPeriodIds();
        } finally {
            unlock();
        }
    }

    @Override // com.amazon.avod.content.image.ImageStreamIndexInterface
    @Nonnull
    public String getImageUrl(@Nullable String str, @Nonnull QualityLevel qualityLevel, int i, @Nonnull String str2) {
        readLock();
        try {
            return ((ImageStreamIndexInterface) CastUtils.castTo(this.mLatestStreamIndex, ImageStreamIndexInterface.class)).getImageUrl(str, qualityLevel, i, str2);
        } finally {
            unlock();
        }
    }
}
