package com.amazon.avod.content.dash.quality.audioselection;

import com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class FixedAudioStreamAndQualitySelector implements AudioStreamAndQualitySelector {
    private final int mBitrate;

    public FixedAudioStreamAndQualitySelector(@Nonnegative int i) {
        Preconditions.checkArgument(i > 0, "bitrate must be non negative");
        this.mBitrate = i;
    }

    @Override // com.amazon.avod.content.dash.quality.audioselection.AudioStreamAndQualitySelector
    @Nonnull
    public List<AudioStreamAndQualityPair> select(@Nonnull List<StreamIndex> list) {
        Preconditions.checkNotNull(list, "audioStreams");
        Preconditions.checkArgument(list.size() > 0, "Number of audio streams must be positive");
        StreamIndex streamIndex = list.get(0);
        AudioQualityLevel audioQualityLevel = (AudioQualityLevel) streamIndex.getSortedQualityLevels(0)[0];
        int i = Integer.MAX_VALUE;
        for (StreamIndex streamIndex2 : list) {
            QualityLevel closestQualityLevel = streamIndex2.getClosestQualityLevel(0, this.mBitrate);
            int iAbs = Math.abs(closestQualityLevel.getBitrate() - this.mBitrate);
            if (iAbs < i) {
                streamIndex = streamIndex2;
                audioQualityLevel = (AudioQualityLevel) closestQualityLevel;
                i = iAbs;
            }
        }
        return ImmutableList.of(new AudioStreamAndQualityPair(streamIndex, audioQualityLevel));
    }
}
