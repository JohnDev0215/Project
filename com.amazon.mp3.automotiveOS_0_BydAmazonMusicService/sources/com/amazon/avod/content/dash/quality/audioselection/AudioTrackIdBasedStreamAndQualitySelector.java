package com.amazon.avod.content.dash.quality.audioselection;

import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.media.AudioStreamType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AudioTrackIdBasedStreamAndQualitySelector implements AudioStreamAndQualitySelector {
    private final List<String> mAudioTrackIds;
    private final boolean mEnableSecondaryTrackQualityConfig;
    private final String mPreferredFourCC;
    private final int mPrimaryBitrateCap;
    private final String mPrimaryTrackId;
    private final int mSecondaryBitrateCap;

    public AudioTrackIdBasedStreamAndQualitySelector(@Nonnull List<String> list, @Nullable String str, int i, int i2, @Nonnull String str2, boolean z) {
        List<String> list2 = (List) Preconditions.checkNotNull(list, "audioTrackIds");
        this.mAudioTrackIds = list2;
        Preconditions.checkState(list2.size() > 0, "audioTrackIds cannot be empty");
        Preconditions.checkState(i > 0, "bitrate must be positive");
        this.mPrimaryTrackId = str == null ? list.get(0) : str;
        this.mPrimaryBitrateCap = i;
        this.mSecondaryBitrateCap = i2;
        this.mPreferredFourCC = (String) Preconditions.checkNotNull(str2, "preferredFourCC");
        this.mEnableSecondaryTrackQualityConfig = z;
    }

    @Override // com.amazon.avod.content.dash.quality.audioselection.AudioStreamAndQualitySelector
    @Nonnull
    public List<AudioStreamAndQualityPair> select(@Nonnull List<StreamIndex> list) {
        AudioStreamAndQualityPair highestBitratePair;
        Preconditions.checkNotNull(list, "audioStreams");
        Preconditions.checkArgument(!list.isEmpty(), "audioStreams cannot be empty");
        Preconditions.checkArgument(list.get(0).getSortedQualityLevels(0).length != 0, "audio stream index must have at least one quality level");
        if (this.mEnableSecondaryTrackQualityConfig) {
            return selectUsingSecondaryBitrateCap(list);
        }
        String fourCC = AudioStreamType.ATMOS.getFourCC().equalsIgnoreCase(this.mPreferredFourCC) ? AudioStreamType.DDP.getFourCC() : this.mPreferredFourCC;
        ArrayList arrayList = new ArrayList();
        for (String str : this.mAudioTrackIds) {
            Iterator<StreamIndex> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    StreamIndex next = it.next();
                    if (str.equals(next.getAudioTrackId())) {
                        if (fourCC.equals(next.getFourCC()) && (highestBitratePair = AudioStreamSelectionUtils.getHighestBitratePair(ImmutableList.of(next), this.mPreferredFourCC, this.mPrimaryBitrateCap)) != null) {
                            arrayList.add(highestBitratePair);
                        } else {
                            AudioStreamAndQualityPair highestBitratePair2 = AudioStreamSelectionUtils.getHighestBitratePair(ImmutableList.of(next), null, this.mPrimaryBitrateCap);
                            if (highestBitratePair2 != null) {
                                arrayList.add(highestBitratePair2);
                            } else {
                                arrayList.add(AudioStreamSelectionUtils.getLowestBitratePair(ImmutableList.of(next)));
                            }
                        }
                    }
                }
            }
        }
        if (arrayList.isEmpty()) {
            DLog.warnf("Cannot find any audio streams for the given audio track ids, selecting the lowest audio bitrate");
            arrayList.add(AudioStreamSelectionUtils.getLowestBitratePair(list));
        }
        return arrayList;
    }

    @Nonnull
    private List<AudioStreamAndQualityPair> selectUsingSecondaryBitrateCap(@Nonnull List<StreamIndex> list) {
        String fourCC = AudioStreamType.ATMOS.getFourCC().equalsIgnoreCase(this.mPreferredFourCC) ? AudioStreamType.DDP.getFourCC() : this.mPreferredFourCC;
        ArrayList arrayList = new ArrayList();
        for (String str : this.mAudioTrackIds) {
            ArrayList<StreamIndex> arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            int i = str.equals(this.mPrimaryTrackId) ? this.mPrimaryBitrateCap : this.mSecondaryBitrateCap;
            for (StreamIndex streamIndex : list) {
                if (streamIndex.getAudioTrackId().equals(str)) {
                    if (AudioStreamSelectionUtils.hasQualityLevelUnderBitrateCap(streamIndex, i)) {
                        arrayList2.add(streamIndex);
                    } else {
                        arrayList3.add(streamIndex);
                    }
                }
            }
            if (arrayList2.size() > 0) {
                AudioStreamAndQualityPair highestBitratePair = null;
                for (StreamIndex streamIndex2 : arrayList2) {
                    if (fourCC.equals(streamIndex2.getFourCC()) && (highestBitratePair = AudioStreamSelectionUtils.getHighestBitratePair(ImmutableList.of(streamIndex2), fourCC, i)) != null) {
                        break;
                    }
                }
                if (highestBitratePair == null) {
                    highestBitratePair = AudioStreamSelectionUtils.getHighestBitratePair(ImmutableList.of(arrayList2.get(0)), null, str.equals(this.mPrimaryTrackId) ? this.mPrimaryBitrateCap : this.mSecondaryBitrateCap);
                }
                arrayList.add(highestBitratePair);
            } else {
                if (arrayList3.isEmpty()) {
                    break;
                }
                AudioStreamAndQualityPair lowestBitratePair = AudioStreamSelectionUtils.getLowestBitratePair(arrayList3, fourCC);
                if (lowestBitratePair == null) {
                    lowestBitratePair = AudioStreamSelectionUtils.getLowestBitratePair(arrayList3);
                }
                arrayList.add(lowestBitratePair);
            }
        }
        if (arrayList.isEmpty()) {
            DLog.warnf("Cannot find any audio streams for the given audio track ids, selecting the lowest audio bitrate");
            arrayList.add(AudioStreamSelectionUtils.getLowestBitratePair(list));
        }
        return arrayList;
    }
}
