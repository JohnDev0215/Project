package com.amazon.avod.smoothstream.dash;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.image.ImageStreamIndexInterface;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.content.smoothstream.manifest.validation.ImagePeriodView;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class ImageMultiPeriodStreamIndex implements ImageStreamIndexInterface {
    private final DrmScheme mDrmScheme;
    private ImagePeriodView mImagePeriodView;
    private Map<String, ImageStreamIndex> mImageStreamIndexMap;
    private final int mIndex;
    private JSONObject mJsonRepresentation;
    private final long mManifestHandle;
    private final DashManifestJni mManifestJni;
    private final long mManifestTimescale;
    private int mNumPeriods;

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public String getFourCC() {
        return "jpeg";
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isAudio() {
        return false;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isVideo() {
        return false;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean requiresInitFragments() {
        return false;
    }

    ImageMultiPeriodStreamIndex(@Nonnull DashManifestJni dashManifestJni, long j, int i, long j2, @Nonnull DrmScheme drmScheme) {
        this.mManifestJni = (DashManifestJni) Preconditions.checkNotNull(dashManifestJni, "manifestJni");
        this.mManifestHandle = j;
        this.mIndex = i;
        this.mManifestTimescale = j2;
        this.mDrmScheme = (DrmScheme) Preconditions.checkNotNull(drmScheme, "drmScheme");
    }

    private void initialize() throws ContentException {
        boolean z;
        int periodCount = this.mManifestJni.getPeriodCount(this.mManifestHandle);
        this.mNumPeriods = periodCount;
        if (periodCount < 1) {
            throw new ContentException(ContentException.ContentError.MALFORMED_MANIFEST, String.format(Locale.US, "period count is %d : can't be zero or negative", Integer.valueOf(this.mNumPeriods)));
        }
        this.mImageStreamIndexMap = Maps.newHashMap();
        this.mImagePeriodView = new ImagePeriodView();
        int i = 0;
        while (i < this.mNumPeriods) {
            int availableStreamCount = this.mManifestJni.getAvailableStreamCount(this.mManifestHandle, i);
            long periodHandle = this.mManifestJni.getPeriodHandle(this.mManifestHandle, i);
            long periodHandle2 = i == this.mNumPeriods - 1 ? -1L : this.mManifestJni.getPeriodHandle(this.mManifestHandle, i + 1);
            String periodIdString = this.mManifestJni.getPeriodIdString(periodHandle);
            if (periodIdString == null) {
                DLog.logf("periodId for periodHandle: %d is null", Long.valueOf(periodHandle));
            } else {
                int i2 = 0;
                while (true) {
                    if (i2 >= availableStreamCount) {
                        z = false;
                        break;
                    } else {
                        if (this.mManifestJni.getStreamIndexType(this.mManifestJni.getAvailableStream(this.mManifestHandle, i, i2)) == StreamType.IMAGE) {
                            z = true;
                            break;
                        }
                        i2++;
                    }
                }
                if (!z) {
                    DLog.logf("cant find the image streamNumber : %d for periodid : %s", Integer.valueOf(i2), periodIdString);
                } else {
                    DLog.logf("found the image streamNumber : %d for periodid : %s", Integer.valueOf(i2), periodIdString);
                    long availableStream = this.mManifestJni.getAvailableStream(this.mManifestHandle, i, i2);
                    if (availableStream == 0) {
                        DLog.logf("cant find image adaptation set periodIndex : %d, mIndex : %d, periodid: %s", Integer.valueOf(i), Integer.valueOf(this.mIndex), periodIdString);
                    } else {
                        ImageStreamIndex imageStreamIndexCreateImageStreamIndex = ImageStreamIndex.createImageStreamIndex(this.mManifestJni, this.mManifestHandle, periodHandle, periodHandle2, availableStream, i2, true, this.mManifestTimescale, this.mDrmScheme, this.mNumPeriods);
                        imageStreamIndexCreateImageStreamIndex.initializeIndex();
                        this.mImageStreamIndexMap.put(periodIdString, imageStreamIndexCreateImageStreamIndex);
                        this.mImagePeriodView.putPeriod(TimeSpan.nanosecondsFromTimeScale(this.mManifestJni.getPeriodStartTime(periodHandle), this.mManifestTimescale), periodIdString);
                    }
                }
            }
            i++;
        }
        JSONArray jSONArray = new JSONArray();
        Iterator<ImageStreamIndex> it = this.mImageStreamIndexMap.values().iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().toJson());
        }
        this.mJsonRepresentation = new JSONObject((Map<?, ?>) Collections.singletonMap("StreamIndices", jSONArray));
    }

    @Nonnull
    static ImageMultiPeriodStreamIndex createMultiPeriodDashIndex(@Nonnull DashManifestJni dashManifestJni, long j, int i, long j2, @Nonnull DrmScheme drmScheme) throws ContentException {
        ImageMultiPeriodStreamIndex imageMultiPeriodStreamIndex = new ImageMultiPeriodStreamIndex(dashManifestJni, j, i, j2, drmScheme);
        imageMultiPeriodStreamIndex.initialize();
        return imageMultiPeriodStreamIndex;
    }

    @Override // com.amazon.avod.content.image.ImageStreamIndexInterface
    public int getNumPeriods() {
        return this.mNumPeriods;
    }

    @Override // com.amazon.avod.content.image.ImageStreamIndexInterface, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getIndex() {
        return this.mIndex;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public StreamType getType() {
        return StreamType.IMAGE;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public void release() {
        Iterator<ImageStreamIndex> it = this.mImageStreamIndexMap.values().iterator();
        while (it.hasNext()) {
            it.next().release();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public JSONObject toJson() {
        return this.mJsonRepresentation;
    }

    @Nonnull
    public String toString() {
        return this.mJsonRepresentation.toString();
    }

    @Override // com.amazon.avod.content.image.ImageStreamIndexInterface
    @Nonnull
    public String getImageUrl(@Nullable String str, @Nonnull QualityLevel qualityLevel, int i, @Nonnull String str2) {
        Preconditions.checkNotNull(qualityLevel, "quality");
        Preconditions.checkNotNull(str2, "periodId");
        ImageStreamIndex imageStreamIndex = this.mImageStreamIndexMap.get(str2);
        Preconditions.checkNotNull(imageStreamIndex, "no matching period with id %s", str2);
        return imageStreamIndex.getImageUrl(str, qualityLevel, i, str2);
    }

    @Override // com.amazon.avod.content.image.ImageStreamIndexInterface
    @Nonnull
    public ImageQualityLevel[] getSortedImageQualityLevels(@Nonnull String str) {
        Preconditions.checkNotNull(str, "periodId");
        ImageStreamIndex imageStreamIndex = this.mImageStreamIndexMap.get(str);
        Preconditions.checkNotNull(imageStreamIndex, "no matching period with id %s", str);
        return imageStreamIndex.getSortedImageQualityLevels(str);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nullable
    public String getPeriodIdWithTimestampNanos(long j) {
        return this.mImagePeriodView.lookupPeriodIdWithTimestamp(j);
    }

    @Override // com.amazon.avod.content.image.ImageStreamIndexInterface, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getMaxWidth() {
        throw new UnsupportedOperationException("getMaxWidth is not supported for image streams");
    }

    @Override // com.amazon.avod.content.image.ImageStreamIndexInterface, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getMaxHeight() {
        throw new UnsupportedOperationException("getMaxHeight is not supported for image streams");
    }

    @Override // com.amazon.avod.content.image.ImageStreamIndexInterface
    @Nonnull
    public Set<String> getAllPeriodIds() {
        return this.mImageStreamIndexMap.keySet();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel[] getSortedQualityLevels(int i) {
        return this.mImageStreamIndexMap.values().iterator().next().getSortedQualityLevels(i);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getUrl(@Nullable String str, QualityLevel qualityLevel, int i) {
        throw new UnsupportedOperationException("getUrl without periodId is not supported for multiperiod image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel getQualityLevel(int i, int i2) {
        throw new UnsupportedOperationException("getQualityLevel is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel getQualityLevel(int i, QualityLevel qualityLevel) {
        throw new UnsupportedOperationException("getQualityLevel is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel getQualityLevelLessThanEqual(int i, int i2) {
        throw new UnsupportedOperationException("getQualityLevelLessThanEqual is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel getQualityLevelGreaterThanEqual(int i, int i2) {
        throw new UnsupportedOperationException("getQualityLevelGreaterThanEqual is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public QualityLevel getClosestQualityLevel(int i, int i2) {
        throw new UnsupportedOperationException("getClosestQualityLevel is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public VideoQualityLevel getClosestQualityLevelWithResolutionCap(int i, int i2, @Nonnull VideoResolution videoResolution) {
        throw new UnsupportedOperationException("getClosestQualityLevelWithResolutionCap is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getChunkTimeInNanos(int i) {
        throw new UnsupportedOperationException("getChunkTimeInNanos is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getChunkTimeOffsetInNanos(int i) {
        throw new UnsupportedOperationException("getChunkTimeOffsetInNanos is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getChunkIndexFromNanos(long j) {
        throw new UnsupportedOperationException("getChunkIndexFromNanos is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getChunkDurationInNanos(int i) {
        throw new UnsupportedOperationException("getChunkDurationInNanos is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isLastPlayableChunk(int i) {
        throw new UnsupportedOperationException("isLastPlayableChunk is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isLastDownloadableChunk(int i) {
        throw new UnsupportedOperationException("isLastDownloadableChunk is not supported for image streams");
    }

    @Override // com.amazon.avod.content.image.ImageStreamIndexInterface, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public int getNumChunks() {
        throw new UnsupportedOperationException("getNumChunks is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public String getInitUrl(String str, QualityLevel qualityLevel, int i) {
        throw new UnsupportedOperationException("getInitUrl is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public Map<String, String> getInitHeaders(@Nonnull QualityLevel qualityLevel) {
        throw new UnsupportedOperationException("getInitHeaders is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public Map<String, String> getHeaders(@Nonnull QualityLevel qualityLevel, int i) {
        throw new UnsupportedOperationException("getHeaders is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getExpectedInitChunkSizeInBytes(QualityLevel qualityLevel) {
        throw new UnsupportedOperationException("getExpectedInitChunkSizeInBytes is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getExpectedChunkSizeInBytes(QualityLevel qualityLevel, int i) {
        throw new UnsupportedOperationException("getExpectedChunkSizeInBytes is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public double getChunkQualityScore(@Nonnull QualityLevel qualityLevel, int i) {
        throw new UnsupportedOperationException("getChunkQualityScore is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamDurationInNanos() {
        throw new UnsupportedOperationException("getStreamDurationInNanos is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public String getLanguage() {
        throw new UnsupportedOperationException("getLanguage is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public String getAudioTrackId() {
        throw new UnsupportedOperationException("getAudioTrackId is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamHandle() {
        throw new UnsupportedOperationException("getStreamHandle is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isOutOfWindow(int i) {
        throw new UnsupportedOperationException("isOutOfWindow is not supported for image streams");
    }

    @Override // com.amazon.avod.content.image.ImageStreamIndexInterface, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public double getDisplayAspectRatio() {
        throw new UnsupportedOperationException("getDisplayAspectRatio is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public boolean isHdr() {
        throw new UnsupportedOperationException("isHdr is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nonnull
    public AudioFormat getAudioFormat() {
        return AudioFormat.STEREO;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    @Nullable
    public byte[] getDefaultKeyId() {
        throw new UnsupportedOperationException("getDefaultKeyId is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamEndTimestampNanos() {
        throw new UnsupportedOperationException("getStreamEndTimestampNanos is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getStreamStartTimestampNanos() {
        throw new UnsupportedOperationException("getStreamStartTimestampNanos is not supported for image streams");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public long getEncodeTimeMillis(long j) {
        throw new UnsupportedOperationException("getEncodeTimeMillis is not supported for image streams");
    }

    @Override // com.amazon.avod.content.image.ImageStreamIndexInterface, com.amazon.avod.content.smoothstream.manifest.StreamIndex
    public float getMaxFrameRate() {
        throw new UnsupportedOperationException("getMaxFrameRate is not supported for image streams");
    }
}
