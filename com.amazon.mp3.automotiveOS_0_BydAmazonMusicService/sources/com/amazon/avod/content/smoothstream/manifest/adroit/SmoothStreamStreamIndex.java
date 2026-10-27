package com.amazon.avod.content.smoothstream.manifest.adroit;

import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.playback.smoothstream.AudioQualityLevel;
import com.amazon.avod.playback.smoothstream.Chunk;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@Immutable
public class SmoothStreamStreamIndex implements StreamIndex {
    private static final AdroitQualityLevelComparator ADROIT_QUALITY_LEVEL_COMPARATOR = new AdroitQualityLevelComparator();
    private final int mIndex;
    private final JSONObject mJsonRepresentation;
    private final SmoothStreamManifest mManifest;
    private final float mMaxFrameRate;
    private final int mMaxHeight;
    private final int mMaxWidth;
    private final SmoothStreamQualityLevel[] mQualityLevels;
    private final SmoothStreamQualityLevel[] mSortedQualityLevels;
    private final com.amazon.avod.playback.smoothstream.StreamIndex mStream;
    private final StreamType mType;

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getAudioTrackId() {
        return "unsupported";
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public double getChunkQualityScore(@Nonnull QualityLevel qualityLevel, int i) {
        return -1.0d;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getChunkTimeOffsetInNanos(int i) {
        return 0L;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nullable
    public byte[] getDefaultKeyId() {
        return null;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getExpectedChunkSizeInBytes(QualityLevel qualityLevel, int i) {
        return -1L;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getExpectedInitChunkSizeInBytes(QualityLevel qualityLevel) {
        return -1L;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel getQualityLevel(int i, QualityLevel qualityLevel) {
        return qualityLevel;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamHandle() {
        return 0L;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamStartTimestampNanos() {
        return 0L;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isOutOfWindow(int i) {
        return false;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public void release() {
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean requiresInitFragments() {
        return false;
    }

    private static class AdroitQualityLevelComparator implements Comparator<SmoothStreamQualityLevel>, Serializable {
        private AdroitQualityLevelComparator() {
        }

        @Override // java.util.Comparator
        public int compare(SmoothStreamQualityLevel smoothStreamQualityLevel, SmoothStreamQualityLevel smoothStreamQualityLevel2) {
            return Integer.compare(smoothStreamQualityLevel.getBitrate(), smoothStreamQualityLevel2.getBitrate());
        }
    }

    SmoothStreamStreamIndex(@Nonnull SmoothStreamManifest smoothStreamManifest, @Nonnull com.amazon.avod.playback.smoothstream.StreamIndex streamIndex, int i) {
        this.mManifest = (SmoothStreamManifest) Preconditions.checkNotNull(smoothStreamManifest, "manifest");
        com.amazon.avod.playback.smoothstream.StreamIndex streamIndex2 = (com.amazon.avod.playback.smoothstream.StreamIndex) Preconditions.checkNotNull(streamIndex, "stream");
        this.mStream = streamIndex2;
        this.mIndex = i;
        this.mType = StreamType.fromString(streamIndex2.getType());
        this.mQualityLevels = new SmoothStreamQualityLevel[streamIndex.getQualityLevels().length];
        this.mSortedQualityLevels = new SmoothStreamQualityLevel[streamIndex.getQualityLevels().length];
        float fMax = 0.0f;
        int i2 = 0;
        int iMax = 0;
        int iMax2 = 0;
        while (true) {
            SmoothStreamQualityLevel[] smoothStreamQualityLevelArr = this.mQualityLevels;
            if (i2 >= smoothStreamQualityLevelArr.length) {
                break;
            }
            smoothStreamQualityLevelArr[i2] = newTypedQualityLevel(i2);
            SmoothStreamQualityLevel[] smoothStreamQualityLevelArr2 = this.mSortedQualityLevels;
            SmoothStreamQualityLevel[] smoothStreamQualityLevelArr3 = this.mQualityLevels;
            smoothStreamQualityLevelArr2[i2] = smoothStreamQualityLevelArr3[i2];
            fMax = Math.max(fMax, smoothStreamQualityLevelArr3[i2].getFrameRate());
            iMax = Math.max(iMax, this.mQualityLevels[i2].getMaxWidth());
            iMax2 = Math.max(iMax2, this.mQualityLevels[i2].getMaxHeight());
            i2++;
        }
        this.mMaxFrameRate = fMax;
        this.mMaxWidth = iMax;
        this.mMaxHeight = iMax2;
        Arrays.sort(this.mSortedQualityLevels, ADROIT_QUALITY_LEVEL_COMPARATOR);
        HashMap mapNewHashMap = Maps.newHashMap();
        mapNewHashMap.put("Type", getType().name());
        mapNewHashMap.put("Language", getLanguage());
        SmoothStreamQualityLevel[] smoothStreamQualityLevelArr4 = this.mSortedQualityLevels;
        mapNewHashMap.put("BaseQuality", smoothStreamQualityLevelArr4.length > 0 ? smoothStreamQualityLevelArr4[0].toString() : "no quality levels found");
        this.mJsonRepresentation = new JSONObject((Map<?, ?>) mapNewHashMap);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getLanguage() {
        return this.mStream.getLanguage();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getIndex() {
        return this.mIndex;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isAudio() {
        return this.mType == StreamType.AUDIO;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isVideo() {
        return this.mType == StreamType.VIDEO;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public SmoothStreamQualityLevel[] getSortedQualityLevels(int i) {
        return this.mSortedQualityLevels;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public SmoothStreamQualityLevel getQualityLevel(int i, int i2) {
        return this.mQualityLevels[i2];
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public SmoothStreamQualityLevel getQualityLevelLessThanEqual(int i, int i2) {
        return getQualityLevel(i, ManifestUtil.getQualityLevelIndexLessThanEqual(this.mStream.getQualityLevels(), i2));
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public SmoothStreamQualityLevel getQualityLevelGreaterThanEqual(int i, int i2) {
        return getQualityLevel(0, ManifestUtil.getQualityLevelIndexGreaterThanEqual(this.mStream.getQualityLevels(), i2));
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public SmoothStreamQualityLevel getClosestQualityLevel(int i, int i2) {
        return getQualityLevel(0, ManifestUtil.findClosestQualityIndex(this.mStream.getQualityLevels(), i2));
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public VideoQualityLevel getClosestQualityLevelWithResolutionCap(int i, int i2, @Nonnull VideoResolution videoResolution) {
        Preconditions.checkNotNull(videoResolution, "maxResolution");
        Preconditions.checkState(this.mType.equals(StreamType.VIDEO), "getClosestQualityLevelWithResolutionCap called on non-video stream");
        int width = videoResolution.getWidth();
        int height = videoResolution.getHeight();
        int iFindClosestQualityIndex = ManifestUtil.findClosestQualityIndex(this.mStream.getQualityLevels(), i2);
        VideoQualityLevel videoQualityLevel = (VideoQualityLevel) getQualityLevel(0, iFindClosestQualityIndex);
        int maxWidth = videoQualityLevel.getMaxWidth();
        int maxHeight = videoQualityLevel.getMaxHeight();
        while (true) {
            if ((maxWidth <= width && maxHeight <= height) || iFindClosestQualityIndex <= 0) {
                break;
            }
            iFindClosestQualityIndex--;
            videoQualityLevel = (VideoQualityLevel) getQualityLevel(0, iFindClosestQualityIndex);
            maxWidth = videoQualityLevel.getMaxWidth();
            maxHeight = videoQualityLevel.getMaxHeight();
        }
        return videoQualityLevel;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getChunkTimeInNanos(int i) {
        return ManifestUtil.getChunkTimeStampInNanos(this.mManifest.getRawManifest(), this.mStream, i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getChunkIndexFromNanos(long j) {
        return ManifestUtil.getChunkIndex(this.mManifest.getRawManifest(), this.mStream, j);
    }

    public long getChunksDurationInNanos(int i, int i2) {
        return ManifestUtil.getChunksDurationInNanos(this.mManifest.getRawManifest(), this.mStream, i, i2);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getChunkDurationInNanos(int i) {
        return getChunksDurationInNanos(i, i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isLastPlayableChunk(int i) {
        return ManifestUtil.isLastChunk(this.mStream, i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isLastDownloadableChunk(int i) {
        return isLastPlayableChunk(i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getNumChunks() {
        return this.mStream.getChunks().length;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getInitUrl(String str, QualityLevel qualityLevel, int i) {
        throw new UnsupportedOperationException("SmoothStreaming does not use init fragments!");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getUrl(String str, QualityLevel qualityLevel, int i) {
        int iIndexOf = str.indexOf(63);
        if (iIndexOf >= 0) {
            str = str.substring(0, iIndexOf);
        }
        Locale locale = Locale.US;
        if (!str.toLowerCase(locale).endsWith("Manifest".toLowerCase(locale))) {
            throw new IllegalArgumentException("Manifest URL does not end with 'Manifest");
        }
        return str.substring(0, str.length() - 8) + this.mStream.getUrl().replace("{bitrate}", Integer.toString(qualityLevel.getBitrate())).replace("{start time}", Long.toString(this.mStream.getChunks()[i].getTimeStamp()));
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public Map<String, String> getInitHeaders(@Nonnull QualityLevel qualityLevel) {
        throw new UnsupportedOperationException("SmoothStreaming does not use init fragments!");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public Map<String, String> getHeaders(@Nonnull QualityLevel qualityLevel, int i) {
        return Collections.emptyMap();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public StreamType getType() {
        return this.mType;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public JSONObject toJson() {
        return this.mJsonRepresentation;
    }

    public String toString() {
        return this.mJsonRepresentation.toString();
    }

    private SmoothStreamQualityLevel newTypedQualityLevel(int i) {
        long timeScale = this.mStream.getTimeScale();
        if (isVideo()) {
            return new SmoothStreamVideoQualityLevel((com.amazon.avod.playback.smoothstream.VideoQualityLevel) this.mStream.getQualityLevels()[i], i, timeScale);
        }
        if (isAudio()) {
            return new SmoothStreamAudioQualityLevel((AudioQualityLevel) this.mStream.getQualityLevels()[i], i, timeScale);
        }
        return new SmoothStreamQualityLevel(this.mStream.getQualityLevels()[i], i, timeScale);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamDurationInNanos() {
        Chunk chunk = this.mStream.getChunks()[r0.length - 1];
        return TimeSpan.nanosecondsFromTimeScale(chunk.getTimeStamp() + chunk.getDuration(), this.mStream.getTimeScale());
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public double getDisplayAspectRatio() {
        return this.mStream.getDisplayAspectRatio();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isHdr() {
        return this.mManifest.isHdr();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public AudioFormat getAudioFormat() {
        if (!isAudio()) {
            return AudioFormat.STEREO;
        }
        return ((com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel) this.mQualityLevels[0]).getAudioFormat();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public String getFourCC() {
        if (this.mQualityLevels.length <= 0) {
            return "";
        }
        return "" + this.mQualityLevels[0].getFourCC();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamEndTimestampNanos() {
        return getStreamDurationInNanos();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getEncodeTimeMillis(long j) {
        throw new UnsupportedOperationException("getEncodeTimeMillis is unsupported for smooth manifests");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public float getMaxFrameRate() {
        return this.mMaxFrameRate;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getMaxHeight() {
        return this.mMaxHeight;
    }
}
