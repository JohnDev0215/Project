package com.amazon.avod.content.dash.quality.audioselection;

import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.media.AudioStreamType;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AudioFormatBasedStreamAndQualitySelector implements AudioStreamAndQualitySelector {
    private final int mBitrateCap;
    private final String mPreferredFourCC;

    public AudioFormatBasedStreamAndQualitySelector(int i, @Nonnull String str) {
        Preconditions.checkArgument(i > 0, "bitrateCap must be positive");
        this.mBitrateCap = i;
        this.mPreferredFourCC = (String) Preconditions.checkNotNull(str, "preferredFourCC");
    }

    @Override // com.amazon.avod.content.dash.quality.audioselection.AudioStreamAndQualitySelector
    @Nonnull
    public List<AudioStreamAndQualityPair> select(@Nonnull List<StreamIndex> list) {
        StreamIndex next;
        AudioStreamAndQualityPair highestBitratePair;
        Preconditions.checkNotNull(list, "audioStreams");
        Preconditions.checkArgument(!list.isEmpty(), "audioStreams cannot be empty");
        Preconditions.checkArgument(list.get(0).getSortedQualityLevels(0).length != 0, "audio stream index must have at least one quality level");
        String fourCC = AudioStreamType.ATMOS.getFourCC().equalsIgnoreCase(this.mPreferredFourCC) ? AudioStreamType.DDP.getFourCC() : this.mPreferredFourCC;
        Iterator<StreamIndex> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (fourCC.equals(next.getFourCC())) {
                break;
            }
        }
        if (next != null && (highestBitratePair = AudioStreamSelectionUtils.getHighestBitratePair(ImmutableList.of(next), this.mPreferredFourCC, this.mBitrateCap)) != null) {
            return ImmutableList.of(highestBitratePair);
        }
        AudioStreamAndQualityPair highestBitratePair2 = AudioStreamSelectionUtils.getHighestBitratePair(list, null, this.mBitrateCap);
        if (highestBitratePair2 != null) {
            return ImmutableList.of(highestBitratePair2);
        }
        return ImmutableList.of(AudioStreamSelectionUtils.getLowestBitratePair(list));
    }
}
