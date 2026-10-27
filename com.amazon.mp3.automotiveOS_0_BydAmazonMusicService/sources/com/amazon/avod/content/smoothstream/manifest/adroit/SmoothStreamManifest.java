package com.amazon.avod.content.smoothstream.manifest.adroit;

import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.smoothstream.manifest.ProtectionHeader;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamManifest implements Manifest {
    private final com.amazon.avod.playback.smoothstream.Manifest mManifest;
    private final StreamIndex[] mStreams;

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public long getAvailabilityStartTimeMillis() {
        return -1L;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nullable
    public StreamIndex getImageStream() {
        return null;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public long getManifestHandle() {
        return 0L;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public StreamIndex getSubtitleStream() {
        return null;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nullable
    public TimeSpan getTimeshiftBufferDuration() {
        return null;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public boolean hasMultiPeriodSupport() {
        return false;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public boolean hasStitchedAds() {
        return false;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public boolean isDynamic() {
        return false;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nullable
    public Boolean isPatternTemplateManifest() {
        return null;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public void release() {
    }

    public SmoothStreamManifest(com.amazon.avod.playback.smoothstream.Manifest manifest) {
        this.mManifest = manifest;
        this.mStreams = new SmoothStreamStreamIndex[manifest.getAvailableStreams().length];
        for (int i = 0; i < this.mManifest.getAvailableStreams().length; i++) {
            this.mStreams[i] = new SmoothStreamStreamIndex(this, this.mManifest.getAvailableStreams()[i], i);
        }
    }

    public com.amazon.avod.playback.smoothstream.Manifest getRawManifest() {
        return this.mManifest;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public TimeSpan getDuration() {
        return ManifestUtil.getDuration(this.mManifest);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public int getNumStreams() {
        return this.mStreams.length;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public List<StreamIndex> getStreams() {
        return ImmutableList.copyOf(this.mStreams);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public StreamIndex getVideoStream() {
        return this.mStreams[ManifestUtil.getVideoStreamIndex(this.mManifest)];
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public StreamIndex getAudioStream(@Nonnull String str, String str2) {
        if (SmoothStreamingPlaybackConfig.INSTANCE.shouldSelectLowerAudioBitrates()) {
            return getLowestAudioBitrateStream(str);
        }
        for (StreamIndex streamIndex : this.mStreams) {
            if (str.equalsIgnoreCase(streamIndex.getSortedQualityLevels(0)[0].getFourCC())) {
                return streamIndex;
            }
        }
        return null;
    }

    private StreamIndex getLowestAudioBitrateStream(String str) {
        int i = -1;
        int bitrate = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < getNumStreams(); i2++) {
            StreamIndex streamIndex = this.mStreams[i2];
            if (str.equalsIgnoreCase(streamIndex.getSortedQualityLevels(0)[0].getFourCC()) && streamIndex.getSortedQualityLevels(0)[0].getBitrate() < bitrate) {
                bitrate = streamIndex.getSortedQualityLevels(0)[0].getBitrate();
                i = i2;
            }
        }
        if (i < 0) {
            return null;
        }
        return this.mStreams[i];
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public boolean isHD() {
        StreamIndex videoStream = getVideoStream();
        if (videoStream != null) {
            for (QualityLevel qualityLevel : videoStream.getSortedQualityLevels(0)) {
                if (((VideoQualityLevel) qualityLevel).isHD()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public boolean isHdr() {
        StreamIndex videoStream = getVideoStream();
        if (videoStream == null) {
            return false;
        }
        QualityLevel[] sortedQualityLevels = videoStream.getSortedQualityLevels(0);
        String fourCC = sortedQualityLevels.length > 0 ? sortedQualityLevels[0].getFourCC() : null;
        return fourCC != null && fourCC.toLowerCase(Locale.US).contains("dvhe");
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public boolean isEncrypted() {
        return getProtectionHeader() != null;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public ProtectionHeader getProtectionHeader() {
        com.amazon.avod.playback.smoothstream.ProtectionHeader protectionHeader = this.mManifest.getProtectionHeader();
        if (protectionHeader == null || Strings.isNullOrEmpty(protectionHeader.getEncodedProtectionData())) {
            return null;
        }
        return new SmoothStreamProtectionHeader(protectionHeader);
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public Set<String> getAvailableAudioLanguages(String str) {
        return ImmutableSet.of();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public Set<String> getAvailableAudioLanguagesAcrossAudioStreams() {
        return ImmutableSet.of();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public TimeSpan getManifestStartTime() {
        return TimeSpan.ZERO;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public TimeSpan getManifestEndTime() {
        return getDuration();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public TimeSpan getSuggestedFrontMargin() {
        return TimeSpan.ZERO;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nonnull
    public TimeSpan getMinimumUpdatePeriod() {
        return TimeSpan.ZERO;
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    public List<StreamIndex> getSupportedAudioStreams(@Nullable String str, @Nonnull List<String> list) {
        Preconditions.checkNotNull(list, "supportedCodecs");
        ImmutableList.Builder builder = ImmutableList.builder();
        for (StreamIndex streamIndex : this.mStreams) {
            QualityLevel[] sortedQualityLevels = streamIndex.getSortedQualityLevels(0);
            if (sortedQualityLevels.length > 0 && list.contains(sortedQualityLevels[0].getFourCC()) && (str == null || str.equalsIgnoreCase(streamIndex.getLanguage()))) {
                builder.add(streamIndex);
            }
        }
        return builder.build();
    }

    @Override // com.amazon.avod.content.smoothstream.manifest.Manifest
    @Nullable
    public QualityLevel getHighestVideoQualityLevel() {
        StreamIndex videoStream = getVideoStream();
        if (videoStream == null) {
            return null;
        }
        return videoStream.getSortedQualityLevels(0)[r0.length - 1];
    }
}
