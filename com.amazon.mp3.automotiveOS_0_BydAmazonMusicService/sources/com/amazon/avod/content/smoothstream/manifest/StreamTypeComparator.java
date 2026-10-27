package com.amazon.avod.content.smoothstream.manifest;

import com.amazon.avod.media.AudioStreamType;
import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: classes2.dex */
public class StreamTypeComparator implements Comparator<StreamIndex>, Serializable {
    @Override // java.util.Comparator
    public int compare(StreamIndex streamIndex, StreamIndex streamIndex2) {
        StreamType streamType;
        if (streamIndex == null) {
            return streamIndex2 == null ? 0 : 1;
        }
        if (streamIndex2 == null) {
            return -1;
        }
        if (streamIndex == streamIndex2) {
            return 0;
        }
        StreamType type = streamIndex.getType();
        StreamType type2 = streamIndex2.getType();
        StreamType streamType2 = StreamType.VIDEO;
        if (type == streamType2) {
            return type2 == streamType2 ? 0 : -1;
        }
        if (type2 == streamType2 || type != (streamType = StreamType.AUDIO)) {
            return 1;
        }
        if (type2 == streamType) {
            AudioStreamType.Companion companion = AudioStreamType.INSTANCE;
            AudioStreamType audioStreamTypeFromFourCC = companion.fromFourCC(streamIndex.getFourCC());
            AudioStreamType audioStreamTypeFromFourCC2 = companion.fromFourCC(streamIndex2.getFourCC());
            if (audioStreamTypeFromFourCC != null && audioStreamTypeFromFourCC2 != null) {
                return audioStreamTypeFromFourCC.compareTo(audioStreamTypeFromFourCC2);
            }
        }
        return -1;
    }
}
