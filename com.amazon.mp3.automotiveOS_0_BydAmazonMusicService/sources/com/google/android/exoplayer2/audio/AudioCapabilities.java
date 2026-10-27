package com.google.android.exoplayer2.audio;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import com.google.android.exoplayer2.util.Util;
import java.util.Arrays;

/* JADX INFO: loaded from: classes6.dex */
@TargetApi(21)
public final class AudioCapabilities {
    public static final AudioCapabilities DEFAULT_AUDIO_CAPABILITIES = new AudioCapabilities(new int[]{2}, 2);
    public static final AudioCapabilities SURROUND_AUDIO_CAPABILITIES = new AudioCapabilities(new int[]{2, 5, 6}, 6);
    private final int maxChannelCount;
    private final int[] supportedEncodings;

    public static AudioCapabilities getCapabilities(Context context) {
        return getCapabilities(context, context.registerReceiver(null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG")));
    }

    @SuppressLint({"InlinedApi"})
    static AudioCapabilities getCapabilities(Context context, Intent intent) {
        boolean zIsSurroundSoundEnabledV17;
        boolean zUseSurroundSoundFlagV17;
        if (Util.SDK_INT >= 17) {
            ContentResolver contentResolver = context.getContentResolver();
            zUseSurroundSoundFlagV17 = useSurroundSoundFlagV17(contentResolver);
            zIsSurroundSoundEnabledV17 = isSurroundSoundEnabledV17(contentResolver);
        } else {
            zIsSurroundSoundEnabledV17 = false;
            zUseSurroundSoundFlagV17 = false;
        }
        if (zUseSurroundSoundFlagV17) {
            return zIsSurroundSoundEnabledV17 ? SURROUND_AUDIO_CAPABILITIES : DEFAULT_AUDIO_CAPABILITIES;
        }
        if (intent == null || intent.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0) == 0) {
            return DEFAULT_AUDIO_CAPABILITIES;
        }
        return new AudioCapabilities(intent.getIntArrayExtra("android.media.extra.ENCODINGS"), intent.getIntExtra("android.media.extra.MAX_CHANNEL_COUNT", 0));
    }

    @TargetApi(17)
    public static boolean isSurroundSoundEnabledV17(ContentResolver contentResolver) {
        return Settings.Global.getInt(contentResolver, "external_surround_sound_enabled", 0) == 1;
    }

    public static boolean useSurroundSoundFlagV17(ContentResolver contentResolver) {
        return Settings.Global.getInt(contentResolver, "use_external_surround_sound_flag", 0) == 1;
    }

    AudioCapabilities(int[] iArr, int i) {
        if (iArr != null) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
            this.supportedEncodings = iArrCopyOf;
            Arrays.sort(iArrCopyOf);
        } else {
            this.supportedEncodings = new int[0];
        }
        this.maxChannelCount = i;
    }

    public boolean supportsEncoding(int i) {
        return Arrays.binarySearch(this.supportedEncodings, i) >= 0;
    }

    public int getMaxChannelCount() {
        return this.maxChannelCount;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AudioCapabilities)) {
            return false;
        }
        AudioCapabilities audioCapabilities = (AudioCapabilities) obj;
        return Arrays.equals(this.supportedEncodings, audioCapabilities.supportedEncodings) && this.maxChannelCount == audioCapabilities.maxChannelCount;
    }

    public int hashCode() {
        return this.maxChannelCount + (Arrays.hashCode(this.supportedEncodings) * 31);
    }

    public String toString() {
        return "AudioCapabilities[maxChannelCount=" + this.maxChannelCount + ", supportedEncodings=" + Arrays.toString(this.supportedEncodings) + "]";
    }
}
