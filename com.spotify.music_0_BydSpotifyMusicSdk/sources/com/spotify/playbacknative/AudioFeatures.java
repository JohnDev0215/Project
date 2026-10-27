package com.spotify.playbacknative;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class AudioFeatures {
    public static final int CORE_AUDIO_CONTENT_TYPE_MUSIC = 0;
    public static final int CORE_AUDIO_CONTENT_TYPE_SPEECH = 1;
    public static final int CORE_STREAM_TYPE_ALARM = 1;
    public static final int CORE_STREAM_TYPE_MUSIC = 0;
    public static final Companion Companion = new Companion(null);
    private final AudioType audioType;
    private final AudioUsage audioUsage;

    public enum AudioType {
        MUSIC(2),
        SPEECH(1);

        private final int attr;

        AudioType(int i) {
            this.attr = i;
        }

        public final int getAttr() {
            return this.attr;
        }
    }

    public enum AudioUsage {
        MEDIA(1),
        ALARM(4);

        private final int attr;

        AudioUsage(int i) {
            this.attr = i;
        }

        public final int getAttr() {
            return this.attr;
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AudioFeatures fromCoreAudioFeatures(int i, int i2) {
            AudioUsage audioUsage;
            AudioType audioType;
            if (i == 0) {
                audioUsage = AudioUsage.MEDIA;
            } else if (i != 1) {
                Logger.Companion.w("Unknown core AudioStreamType %d provided. Falling back to default action.", Integer.valueOf(i));
                audioUsage = AudioUsage.MEDIA;
            } else {
                audioUsage = AudioUsage.ALARM;
            }
            if (i2 == 0) {
                audioType = AudioType.MUSIC;
            } else if (i2 != 1) {
                Logger.Companion.w("Unknown core AudioContentType %d provided. Falling back to default action.", Integer.valueOf(i2));
                audioType = AudioType.MUSIC;
            } else {
                audioType = AudioType.SPEECH;
            }
            return new AudioFeatures(audioUsage, audioType);
        }
    }

    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AudioUsage.values().length];
            try {
                iArr[AudioUsage.MEDIA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AudioUsage.ALARM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public AudioFeatures(AudioUsage audioUsage, AudioType audioType) {
        v03.j(audioUsage, "audioUsage");
        v03.j(audioType, "audioType");
        this.audioUsage = audioUsage;
        this.audioType = audioType;
    }

    public static final AudioFeatures fromCoreAudioFeatures(int i, int i2) {
        return Companion.fromCoreAudioFeatures(i, i2);
    }

    public final AudioType getAudioType() {
        return this.audioType;
    }

    public final AudioUsage getAudioUsage() {
        return this.audioUsage;
    }

    public final int getAudioUsageDeprecated() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.audioUsage.ordinal()];
        if (i == 1) {
            return 3;
        }
        if (i == 2) {
            return 4;
        }
        throw new NoWhenBranchMatchedException();
    }
}
