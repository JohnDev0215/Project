package com.amazon.avod.content.dash.quality.audioselection;

import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.media.AudioStreamType;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AudioFormatAndPreferredTrackIdBasedStreamAndQualitySelector implements AudioStreamAndQualitySelector {
    private final int mBitrateCap;
    private final AudioFormatBasedStreamAndQualitySelector mFallbackSelector;
    private final List<String> mOrderedPreferredAudioTrackIds;
    private final String mPreferredFourCC;

    public AudioFormatAndPreferredTrackIdBasedStreamAndQualitySelector(int i, @Nonnull String str, @Nonnull List<String> list) {
        this(i, str, list, new AudioFormatBasedStreamAndQualitySelector(i, str));
    }

    AudioFormatAndPreferredTrackIdBasedStreamAndQualitySelector(int i, @Nonnull String str, @Nonnull List<String> list, @Nonnull AudioFormatBasedStreamAndQualitySelector audioFormatBasedStreamAndQualitySelector) {
        Preconditions.checkArgument(i > 0, "bitrateCap must be positive");
        this.mBitrateCap = i;
        this.mPreferredFourCC = (String) Preconditions.checkNotNull(str, "preferredFourCC");
        this.mOrderedPreferredAudioTrackIds = (List) Preconditions.checkNotNull(list, "preferredAudioTrackIds");
        this.mFallbackSelector = (AudioFormatBasedStreamAndQualitySelector) Preconditions.checkNotNull(audioFormatBasedStreamAndQualitySelector, "audioFormatBasedStreamAndQualitySelector");
    }

    @Override // com.amazon.avod.content.dash.quality.audioselection.AudioStreamAndQualitySelector
    @Nonnull
    public List<AudioStreamAndQualityPair> select(@Nonnull List<StreamIndex> list) {
        Preconditions.checkNotNull(list, "audioStreams");
        Preconditions.checkArgument(!list.isEmpty(), "audioStreams cannot be empty");
        Preconditions.checkArgument(list.get(0).getSortedQualityLevels(0).length != 0, "audio stream index must have at least one quality level");
        List<AudioStreamAndQualityPair> listSelectBitrateQualityPairsWithFourCCAndPreferredAudioTrackIdsMatch = selectBitrateQualityPairsWithFourCCAndPreferredAudioTrackIdsMatch(list, AudioStreamType.ATMOS.getFourCC().equalsIgnoreCase(this.mPreferredFourCC) ? AudioStreamType.DDP.getFourCC() : this.mPreferredFourCC);
        return !listSelectBitrateQualityPairsWithFourCCAndPreferredAudioTrackIdsMatch.isEmpty() ? listSelectBitrateQualityPairsWithFourCCAndPreferredAudioTrackIdsMatch : this.mFallbackSelector.select(list);
    }

    @Nonnull
    private List<AudioStreamAndQualityPair> selectBitrateQualityPairsWithFourCCAndPreferredAudioTrackIdsMatch(@Nonnull List<StreamIndex> list, @Nonnull String str) {
        AudioStreamAndQualityPair highestBitratePair;
        List<StreamIndex> allParentFourCCMatchWithRespectToPreferredAudioTrackIds = getAllParentFourCCMatchWithRespectToPreferredAudioTrackIds(list, str);
        if (!allParentFourCCMatchWithRespectToPreferredAudioTrackIds.isEmpty() && (highestBitratePair = AudioStreamSelectionUtils.getHighestBitratePair(allParentFourCCMatchWithRespectToPreferredAudioTrackIds, this.mPreferredFourCC, this.mBitrateCap)) != null) {
            return ImmutableList.of(highestBitratePair);
        }
        return ImmutableList.of();
    }

    @Nonnull
    private List<StreamIndex> getAllParentFourCCMatchWithRespectToPreferredAudioTrackIds(@Nonnull List<StreamIndex> list, @Nonnull String str) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (String str2 : this.mOrderedPreferredAudioTrackIds) {
            for (StreamIndex streamIndex : list) {
                if (str.equals(streamIndex.getFourCC()) && str2.equalsIgnoreCase(streamIndex.getAudioTrackId())) {
                    builder.add(streamIndex);
                }
            }
        }
        return builder.build();
    }
}
