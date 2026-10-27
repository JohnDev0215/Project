package com.amazon.avod.media.playback.render;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.common.base.Preconditions;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(21)
public final class ReceiverAudioCapabilities {
    public static final ReceiverAudioCapabilities DEFAULT_RECEIVER_AUDIO_CAPABILITIES = new ReceiverAudioCapabilities(new int[]{2});
    private final int[] mSupportedEncodings;

    public static ReceiverAudioCapabilities getCapabilities(@Nonnull Context context) {
        Preconditions.checkNotNull(context, "context");
        Intent intentRegisterReceiver = context.registerReceiver(null, new IntentFilter("android.media.action.HDMI_AUDIO_PLUG"));
        if (intentRegisterReceiver == null || intentRegisterReceiver.getIntExtra("android.media.extra.AUDIO_PLUG_STATE", 0) == 0) {
            return DEFAULT_RECEIVER_AUDIO_CAPABILITIES;
        }
        return new ReceiverAudioCapabilities(intentRegisterReceiver.getIntArrayExtra("android.media.extra.ENCODINGS"));
    }

    ReceiverAudioCapabilities(@Nullable int[] iArr) {
        if (iArr != null) {
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
            this.mSupportedEncodings = iArrCopyOf;
            Arrays.sort(iArrCopyOf);
            return;
        }
        this.mSupportedEncodings = new int[0];
    }

    public boolean supportsEncoding(int i) {
        return Arrays.binarySearch(this.mSupportedEncodings, i) >= 0;
    }
}
