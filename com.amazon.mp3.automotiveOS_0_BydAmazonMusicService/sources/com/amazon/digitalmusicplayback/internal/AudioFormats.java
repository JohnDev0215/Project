package com.amazon.digitalmusicplayback.internal;

import android.media.AudioTrack;
import com.amazon.digitalmusicplayback.AudioFormat;
import com.amazon.digitalmusicplayback.AudioSampleFormat;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
final class AudioFormats {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int ENCODING_E_AC3_JOC = 18;
    public static final int ENCODING_IEC61937 = 13;
    public static final int ENCODING_MPEGH_BL_L3 = 23;
    public static final int ENCODING_MPEGH_BL_L4 = 24;
    public static final int ENCODING_MPEGH_LC_L3 = 25;
    public static final int ENCODING_MPEGH_LC_L4 = 26;
    public static final ArrayList<AudioFormat> kAllFormats;
    public static final AudioFormat kBasicDDP;
    public static final AudioFormat kNativeFormat;

    public static int toAndroidChannelMask(long j) {
        return ((int) j) << 2;
    }

    AudioFormats() {
    }

    public static boolean equals(AudioFormat audioFormat, AudioFormat audioFormat2) {
        return audioFormat.getSampleRate() == audioFormat2.getSampleRate() && audioFormat.getChannelLayout() == audioFormat2.getChannelLayout() && audioFormat.getSampleFormat() == audioFormat2.getSampleFormat() && audioFormat.getChannelCount() == audioFormat2.getChannelCount();
    }

    /* JADX INFO: renamed from: com.amazon.digitalmusicplayback.internal.AudioFormats$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$digitalmusicplayback$AudioSampleFormat;

        static {
            int[] iArr = new int[AudioSampleFormat.values().length];
            $SwitchMap$com$amazon$digitalmusicplayback$AudioSampleFormat = iArr;
            try {
                iArr[AudioSampleFormat.COMPRESSED_MPEGH_BL3.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$AudioSampleFormat[AudioSampleFormat.COMPRESSED_MPEGH_BL4.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$AudioSampleFormat[AudioSampleFormat.COMPRESSED_MPEGH_LC3.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$AudioSampleFormat[AudioSampleFormat.COMPRESSED_MPEGH_LC4.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$AudioSampleFormat[AudioSampleFormat.COMPRESSED_EAC3.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$AudioSampleFormat[AudioSampleFormat.COMPRESSED_EAC3_JOC.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$AudioSampleFormat[AudioSampleFormat.SAMPLEFORMATS16.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$digitalmusicplayback$AudioSampleFormat[AudioSampleFormat.SAMPLEFORMATFLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public static boolean isMPEGH(AudioSampleFormat audioSampleFormat) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$digitalmusicplayback$AudioSampleFormat[audioSampleFormat.ordinal()];
        return i == 1 || i == 2 || i == 3 || i == 4;
    }

    public static boolean isPCM(AudioSampleFormat audioSampleFormat) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$digitalmusicplayback$AudioSampleFormat[audioSampleFormat.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return false;
            default:
                return true;
        }
    }

    public static int toAndroidEncoding(AudioSampleFormat audioSampleFormat) {
        switch (AnonymousClass1.$SwitchMap$com$amazon$digitalmusicplayback$AudioSampleFormat[audioSampleFormat.ordinal()]) {
            case 1:
                return 23;
            case 2:
                return 24;
            case 3:
                return 25;
            case 4:
                return 26;
            case 5:
                return 6;
            case 6:
                return 18;
            case 7:
                return 2;
            case 8:
                return 4;
            default:
                return 0;
        }
    }

    public static String toShortString(int i, int i2, int i3) {
        return String.format("AndroidFormat{%skHz,enc=%s,%sch}", Integer.valueOf(i / 1000), Integer.valueOf(i2), Integer.valueOf(i3));
    }

    public static String toShortString(AudioTrack audioTrack) {
        return toShortString(audioTrack.getSampleRate(), audioTrack.getAudioFormat(), audioTrack.getChannelCount());
    }

    public static String toShortString(android.media.AudioFormat audioFormat) {
        return toShortString(audioFormat.getSampleRate(), audioFormat.getEncoding(), audioFormat.getChannelCount());
    }

    static {
        boolean z;
        ArrayList<AudioFormat> arrayList = new ArrayList<>();
        kAllFormats = arrayList;
        AudioSampleFormat audioSampleFormat = AudioSampleFormat.SAMPLEFORMATS16;
        arrayList.add(new AudioFormat(44100L, 3L, audioSampleFormat, 2));
        arrayList.add(new AudioFormat(48000L, 3L, audioSampleFormat, 2));
        arrayList.add(new AudioFormat(96000L, 3L, audioSampleFormat, 2));
        arrayList.add(new AudioFormat(192000L, 3L, audioSampleFormat, 2));
        AudioSampleFormat audioSampleFormat2 = AudioSampleFormat.SAMPLEFORMATFLOAT;
        arrayList.add(new AudioFormat(44100L, 3L, audioSampleFormat2, 2));
        arrayList.add(new AudioFormat(48000L, 3L, audioSampleFormat2, 2));
        arrayList.add(new AudioFormat(96000L, 3L, audioSampleFormat2, 2));
        arrayList.add(new AudioFormat(192000L, 3L, audioSampleFormat2, 2));
        kBasicDDP = new AudioFormat(48000L, 63L, AudioSampleFormat.COMPRESSED_EAC3, 6);
        arrayList.add(new AudioFormat(48000L, 63L, AudioSampleFormat.COMPRESSED_EAC3_JOC, 6));
        arrayList.add(new AudioFormat(48000L, 63L, AudioSampleFormat.COMPRESSED_MPEGH_BL3, 6));
        arrayList.add(new AudioFormat(48000L, 63L, AudioSampleFormat.COMPRESSED_MPEGH_BL4, 6));
        arrayList.add(new AudioFormat(48000L, 63L, AudioSampleFormat.COMPRESSED_MPEGH_LC3, 6));
        arrayList.add(new AudioFormat(48000L, 63L, AudioSampleFormat.COMPRESSED_MPEGH_LC4, 6));
        kNativeFormat = new AudioFormat(AudioTrack.getNativeOutputSampleRate(3), 3L, audioSampleFormat, 2);
        Iterator<AudioFormat> it = arrayList.iterator();
        while (true) {
            if (it.hasNext()) {
                if (equals(it.next(), kNativeFormat)) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (!z) {
            kAllFormats.add(0, kNativeFormat);
        }
        for (AudioFormat audioFormat : kAllFormats) {
        }
    }
}
