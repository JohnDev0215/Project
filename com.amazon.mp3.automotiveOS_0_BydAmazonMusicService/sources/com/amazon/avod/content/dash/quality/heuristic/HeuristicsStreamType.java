package com.amazon.avod.content.dash.quality.heuristic;

import com.amazon.avod.content.smoothstream.manifest.StreamType;

/* JADX INFO: loaded from: classes2.dex */
public enum HeuristicsStreamType {
    Video(0),
    Audio(1),
    Subtitle(2);

    private final int mValue;

    HeuristicsStreamType(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.dash.quality.heuristic.HeuristicsStreamType$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType;

        static {
            int[] iArr = new int[StreamType.values().length];
            $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType = iArr;
            try {
                iArr[StreamType.VIDEO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType[StreamType.AUDIO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType[StreamType.SUBTITLES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static HeuristicsStreamType fromStreamType(StreamType streamType) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType[streamType.ordinal()];
        if (i == 1) {
            return Video;
        }
        if (i == 2) {
            return Audio;
        }
        if (i == 3) {
            return Subtitle;
        }
        throw new UnsupportedOperationException("Please add logic to new stream type");
    }
}
