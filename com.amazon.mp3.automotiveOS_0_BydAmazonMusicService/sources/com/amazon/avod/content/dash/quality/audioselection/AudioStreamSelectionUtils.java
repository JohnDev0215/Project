package com.amazon.avod.content.dash.quality.audioselection;

import com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AudioStreamSelectionUtils {
    @Nonnull
    public static AudioStreamAndQualityPair getLowestBitratePair(@Nonnull List<StreamIndex> list) {
        Preconditions.checkNotNull(list, "audioStreams");
        Preconditions.checkArgument(!list.isEmpty(), "audioStreams cannot be empty");
        Preconditions.checkArgument(list.get(0).getSortedQualityLevels(0).length != 0, "audio stream index must have at least one quality level");
        int i = Integer.MAX_VALUE;
        StreamIndex streamIndex = null;
        for (StreamIndex streamIndex2 : list) {
            int bitrate = streamIndex2.getSortedQualityLevels(0)[0].getBitrate();
            if (bitrate < i) {
                streamIndex = streamIndex2;
                i = bitrate;
            }
        }
        return new AudioStreamAndQualityPair(streamIndex, (AudioQualityLevel) streamIndex.getSortedQualityLevels(0)[0]);
    }

    @Nullable
    public static AudioStreamAndQualityPair getLowestBitratePair(@Nonnull List<StreamIndex> list, @Nullable String str) {
        Preconditions.checkNotNull(list, "audioStreams");
        Preconditions.checkArgument(!list.isEmpty(), "audioStreams cannot be empty");
        Preconditions.checkArgument(list.get(0).getSortedQualityLevels(0).length != 0, "audio stream index must have at least one quality level");
        int i = Integer.MAX_VALUE;
        AudioQualityLevel audioQualityLevel = null;
        StreamIndex streamIndex = null;
        for (StreamIndex streamIndex2 : list) {
            QualityLevel[] sortedQualityLevels = streamIndex2.getSortedQualityLevels(0);
            int length = sortedQualityLevels.length;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    QualityLevel qualityLevel = sortedQualityLevels[i2];
                    int bitrate = qualityLevel.getBitrate();
                    if ((str == null || qualityLevel.getFourCC().equalsIgnoreCase(str)) && bitrate < i) {
                        audioQualityLevel = (AudioQualityLevel) qualityLevel;
                        streamIndex = streamIndex2;
                        i = bitrate;
                        break;
                    }
                    i2++;
                }
            }
        }
        if (audioQualityLevel != null) {
            return new AudioStreamAndQualityPair(streamIndex, audioQualityLevel);
        }
        return null;
    }

    @Nullable
    public static AudioStreamAndQualityPair getHighestBitratePair(@Nonnull List<StreamIndex> list, @Nullable String str, int i) {
        List<AudioStreamAndQualityPair> highestBitratePairsPerAudioStream = getHighestBitratePairsPerAudioStream(list, str, i);
        AudioStreamAndQualityPair audioStreamAndQualityPair = null;
        if (highestBitratePairsPerAudioStream.isEmpty()) {
            return null;
        }
        int i2 = 0;
        for (AudioStreamAndQualityPair audioStreamAndQualityPair2 : highestBitratePairsPerAudioStream) {
            int bitrate = audioStreamAndQualityPair2.getAudioQualityLevel().getBitrate();
            if (bitrate > i2) {
                audioStreamAndQualityPair = audioStreamAndQualityPair2;
                i2 = bitrate;
            }
        }
        return audioStreamAndQualityPair;
    }

    @Nonnull
    public static List<AudioStreamAndQualityPair> getHighestBitratePairsPerAudioStream(@Nonnull List<StreamIndex> list, @Nullable String str, int i) {
        Preconditions.checkNotNull(list, "audioStreams");
        Preconditions.checkArgument(!list.isEmpty(), "audioStreams cannot be empty");
        ImmutableList.Builder builder = new ImmutableList.Builder();
        Iterator<StreamIndex> it = list.iterator();
        while (it.hasNext()) {
            AudioStreamAndQualityPair highestBitratePairForSingleAudioStream = getHighestBitratePairForSingleAudioStream(it.next(), str, i);
            if (highestBitratePairForSingleAudioStream != null) {
                builder.add(highestBitratePairForSingleAudioStream);
            }
        }
        return builder.build();
    }

    @Nullable
    public static AudioStreamAndQualityPair getHighestBitratePairForSingleAudioStream(@Nonnull StreamIndex streamIndex, @Nullable String str, int i) {
        Preconditions.checkNotNull(streamIndex, "audioStreams");
        Preconditions.checkArgument(streamIndex.getSortedQualityLevels(0).length != 0, "audio stream index must have at least one quality level");
        int i2 = 0;
        AudioQualityLevel audioQualityLevel = null;
        StreamIndex streamIndex2 = null;
        for (QualityLevel qualityLevel : streamIndex.getSortedQualityLevels(0)) {
            int bitrate = qualityLevel.getBitrate();
            if ((str == null || qualityLevel.getFourCC().equalsIgnoreCase(str)) && bitrate > i2 && bitrate <= i) {
                audioQualityLevel = (AudioQualityLevel) qualityLevel;
                streamIndex2 = streamIndex;
                i2 = bitrate;
            }
        }
        if (audioQualityLevel != null) {
            return new AudioStreamAndQualityPair(streamIndex2, audioQualityLevel);
        }
        return null;
    }

    public static boolean hasQualityLevelUnderBitrateCap(@Nonnull StreamIndex streamIndex, int i) {
        Preconditions.checkNotNull(streamIndex, "audioStream");
        Preconditions.checkArgument(streamIndex.getSortedQualityLevels(0).length != 0, "audio stream index must have at least one quality level");
        for (QualityLevel qualityLevel : streamIndex.getSortedQualityLevels(0)) {
            if (qualityLevel.getBitrate() <= i) {
                return true;
            }
        }
        return false;
    }
}
