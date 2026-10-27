package com.amazon.avod.content.smoothstream;

import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.dash.quality.audioselection.AudioStreamAndQualityPair;
import com.amazon.avod.content.smoothstream.manifest.AudioQualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class StreamSelections {
    private final List<AudioStreamAndQualityPair> mAudioStreamAndQualityPairs;
    private final Map<StreamIndex, AudioQualityLevel> mAudioStreamIndexToAudioQualityLevelMap;
    private final StreamIndex mImageStream;
    private final AudioStreamAndQualityPair mPrimaryAudioStreamAndQualityPair;
    private final String mSelectionParams;
    private final StreamIndex mSubtitleStream;
    private final StreamIndex mVideoStream;

    public StreamSelections(@Nonnull StreamIndex streamIndex, @Nonnull AudioStreamAndQualityPair audioStreamAndQualityPair, @Nonnull List<AudioStreamAndQualityPair> list, @Nullable StreamIndex streamIndex2, @Nonnull String str, @Nullable StreamIndex streamIndex3, @Nonnull ContentSessionType contentSessionType) {
        this.mVideoStream = (StreamIndex) Preconditions.checkNotNull(streamIndex, "videoStream");
        AudioStreamAndQualityPair audioStreamAndQualityPair2 = (AudioStreamAndQualityPair) Preconditions.checkNotNull(audioStreamAndQualityPair, "primaryAudioStreamAndQualityPair");
        this.mPrimaryAudioStreamAndQualityPair = audioStreamAndQualityPair2;
        List<AudioStreamAndQualityPair> list2 = (List) Preconditions.checkNotNull(list, "audioStreamAndQualityPairs");
        this.mAudioStreamAndQualityPairs = list2;
        Preconditions.checkState(list2.size() > 0, "audioStreamAndQualityPairs cannot be empty.");
        Preconditions.checkState(list2.contains(audioStreamAndQualityPair2), "audioStreamAndQualityPairs must contain the primary audio stream.");
        Preconditions.checkNotNull(contentSessionType, "contentSessionType");
        if (!contentSessionType.isDownload()) {
            Preconditions.checkState(list2.size() == 1, "audioStreamAndQualityPairs must contain exactly one audio stream for streaming session types.");
        }
        this.mSubtitleStream = streamIndex2;
        this.mSelectionParams = (String) Preconditions.checkNotNull(str, "selectionParams");
        this.mAudioStreamIndexToAudioQualityLevelMap = new HashMap();
        for (AudioStreamAndQualityPair audioStreamAndQualityPair3 : list2) {
            this.mAudioStreamIndexToAudioQualityLevelMap.put(audioStreamAndQualityPair3.getAudioStream(), audioStreamAndQualityPair3.getAudioQualityLevel());
        }
        this.mImageStream = streamIndex3;
    }

    @Nonnull
    public StreamIndex getAudioStream() {
        return this.mPrimaryAudioStreamAndQualityPair.getAudioStream();
    }

    @Nonnull
    public AudioQualityLevel getStartAudioQualityLevel() {
        return this.mPrimaryAudioStreamAndQualityPair.getAudioQualityLevel();
    }

    @Nonnull
    public List<AudioStreamAndQualityPair> getAudioStreamAndQualityPairs() {
        return this.mAudioStreamAndQualityPairs;
    }

    @Nonnull
    public AudioQualityLevel getSelectedAudioQualityLevelForStreamIndex(@Nonnull StreamIndex streamIndex) {
        Preconditions.checkNotNull(streamIndex, "audioStreamIndex");
        Preconditions.checkState(streamIndex.isAudio(), "This method must be called only for audio streams.");
        return this.mAudioStreamIndexToAudioQualityLevelMap.get(streamIndex);
    }

    @Nonnull
    public StreamIndex getVideoStream() {
        return this.mVideoStream;
    }

    @Nullable
    public StreamIndex getSubtitleStream() {
        return this.mSubtitleStream;
    }

    @Nullable
    public StreamIndex getImageStreamStream() {
        return this.mImageStream;
    }

    @Nonnull
    public String getSelectionParams() {
        return this.mSelectionParams;
    }

    public String toString() {
        HashMap mapNewHashMap = Maps.newHashMap();
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        for (AudioStreamAndQualityPair audioStreamAndQualityPair : this.mAudioStreamAndQualityPairs) {
            jSONArray.put(audioStreamAndQualityPair.getAudioStream().toJson());
            jSONArray2.put(audioStreamAndQualityPair.getAudioQualityLevel().toJson());
        }
        mapNewHashMap.put("AudioStreams", jSONArray);
        mapNewHashMap.put("AudioQualityLevels", jSONArray2);
        mapNewHashMap.put("VideoStream", this.mVideoStream.toJson());
        StreamIndex streamIndex = this.mSubtitleStream;
        mapNewHashMap.put("SubtitleStream", streamIndex != null ? streamIndex.toJson() : "None");
        StreamIndex streamIndex2 = this.mImageStream;
        mapNewHashMap.put("ImageStream", streamIndex2 != null ? streamIndex2.toJson() : "None");
        return new JSONObject((Map<?, ?>) mapNewHashMap).toString();
    }
}
