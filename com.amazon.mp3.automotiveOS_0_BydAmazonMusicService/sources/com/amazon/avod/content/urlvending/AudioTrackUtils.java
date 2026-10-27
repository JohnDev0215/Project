package com.amazon.avod.content.urlvending;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.AudioStreamType;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AudioTrackUtils {
    @Nonnull
    public static ImmutableList<String> getAudioLanguageCodes(@Nonnull ImmutableList<String> immutableList, @Nonnull ImmutableList<AudioTrackMetadata> immutableList2) {
        Preconditions.checkNotNull(immutableList, "audioTrackIds");
        Preconditions.checkNotNull(immutableList2, "metadataList");
        if (immutableList2.size() == 1) {
            return ImmutableList.of(immutableList2.get(0).getLanguageCode());
        }
        LinkedHashSet linkedHashSetNewLinkedHashSet = Sets.newLinkedHashSet();
        UnmodifiableIterator<String> it = immutableList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            UnmodifiableIterator<AudioTrackMetadata> it2 = immutableList2.iterator();
            while (it2.hasNext()) {
                AudioTrackMetadata next2 = it2.next();
                if (next2.getAudioTrackId().equals(next)) {
                    linkedHashSetNewLinkedHashSet.add(next2.getLanguageCode());
                }
            }
        }
        if (!linkedHashSetNewLinkedHashSet.isEmpty()) {
            return ImmutableList.copyOf((Collection) linkedHashSetNewLinkedHashSet);
        }
        UnmodifiableIterator<AudioTrackMetadata> it3 = immutableList2.iterator();
        while (it3.hasNext()) {
            AudioTrackMetadata next3 = it3.next();
            if (next3.getIsDefaultAudioTrack()) {
                return ImmutableList.of(next3.getLanguageCode());
            }
        }
        return ImmutableList.of();
    }

    @Nullable
    public static String getDefaultLanguageCode(@Nonnull ImmutableList<String> immutableList, @Nonnull ImmutableList<AudioTrackMetadata> immutableList2) {
        Preconditions.checkNotNull(immutableList, "audioTrackIds");
        Preconditions.checkNotNull(immutableList2, "metadataList");
        ImmutableList<String> audioLanguageCodes = getAudioLanguageCodes(immutableList, immutableList2);
        if (audioLanguageCodes.isEmpty()) {
            return null;
        }
        return audioLanguageCodes.get(0);
    }

    @Nonnull
    public static String convertAudioFormatToFourCC(@Nonnull AudioFormat audioFormat) throws ContentException {
        Preconditions.checkNotNull(audioFormat, "audioFormat");
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$media$AudioFormat[audioFormat.ordinal()];
        if (i == 1) {
            return AudioStreamType.AACL.getFourCC();
        }
        if (i == 2) {
            return AudioStreamType.ATMOS.getFourCC();
        }
        if (i == 3) {
            return AudioStreamType.DDP.getFourCC();
        }
        throw new ContentException(ContentException.ContentError.UNKNOWN_AUDIO_TRACK, String.format(Locale.US, "Unknown audio stream type %s.", audioFormat));
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.urlvending.AudioTrackUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$AudioFormat;

        static {
            int[] iArr = new int[AudioFormat.values().length];
            $SwitchMap$com$amazon$avod$media$AudioFormat = iArr;
            try {
                iArr[AudioFormat.STEREO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$AudioFormat[AudioFormat.AC_3_7_1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$AudioFormat[AudioFormat.AC_3_5_1.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Nonnull
    public static AudioFormat convertFourCCToAudioFormat(@Nonnull String str, @Nullable String str2) {
        AudioFormat audioFormat;
        Preconditions.checkNotNull(str, "fourCC");
        if (AudioStreamType.ATMOS.getFourCC().equalsIgnoreCase(str)) {
            audioFormat = AudioFormat.AC_3_7_1;
        } else if (AudioStreamType.DDP.getFourCC().equalsIgnoreCase(str)) {
            audioFormat = AudioFormat.AC_3_5_1;
        } else {
            audioFormat = AudioFormat.STEREO;
        }
        AudioFormat audioFormat2 = AudioFormat.STEREO;
        return (audioFormat == audioFormat2 || str2 == null || !str2.equalsIgnoreCase("A000")) ? audioFormat : audioFormat2;
    }
}
