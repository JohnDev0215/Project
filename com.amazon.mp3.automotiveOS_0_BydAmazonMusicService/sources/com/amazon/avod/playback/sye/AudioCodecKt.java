package com.amazon.avod.playback.sye;

import com.amazon.avod.media.AudioFormat;
import com.google.android.gms.common.Scopes;
import com.netinsight.sye.syeClient.audio.SyeAudioCodec;
import com.netinsight.sye.syeClient.audio.SyeAudioCodecProfile;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AudioCodec.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u0005\u001a\u00020\u00042\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¨\u0006\u0006"}, d2 = {"Lcom/netinsight/sye/syeClient/audio/SyeAudioCodec;", "codec", "Lcom/netinsight/sye/syeClient/audio/SyeAudioCodecProfile;", Scopes.PROFILE, "Lcom/amazon/avod/media/AudioFormat;", "fromSyeAudioCodec", "ATVAndroidPlayback_release"}, k = 2, mv = {1, 6, 0})
public final class AudioCodecKt {

    /* JADX INFO: compiled from: AudioCodec.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[SyeAudioCodec.values().length];
            iArr[SyeAudioCodec.EC3.ordinal()] = 1;
            iArr[SyeAudioCodec.AAC.ordinal()] = 2;
            iArr[SyeAudioCodec.AC3.ordinal()] = 3;
            iArr[SyeAudioCodec.Undefined.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[AudioFormat.values().length];
            iArr2[AudioFormat.AC_3_5_1.ordinal()] = 1;
            iArr2[AudioFormat.AC_3_7_1.ordinal()] = 2;
            iArr2[AudioFormat.STEREO.ordinal()] = 3;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public static final AudioFormat fromSyeAudioCodec(SyeAudioCodec syeAudioCodec, SyeAudioCodecProfile syeAudioCodecProfile) {
        if (syeAudioCodec == null) {
            AudioFormat DEFAULT = AudioFormat.DEFAULT;
            Intrinsics.checkNotNullExpressionValue(DEFAULT, "DEFAULT");
            return DEFAULT;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[syeAudioCodec.ordinal()];
        if (i == 1) {
            if (SyeAudioCodecProfile.EC3_Atmos == syeAudioCodecProfile) {
                return AudioFormat.AC_3_7_1;
            }
            return AudioFormat.AC_3_5_1;
        }
        if (i == 2) {
            return AudioFormat.STEREO;
        }
        if (i == 3 || i == 4) {
            AudioFormat DEFAULT2 = AudioFormat.DEFAULT;
            Intrinsics.checkNotNullExpressionValue(DEFAULT2, "DEFAULT");
            return DEFAULT2;
        }
        AudioFormat DEFAULT3 = AudioFormat.DEFAULT;
        Intrinsics.checkNotNullExpressionValue(DEFAULT3, "DEFAULT");
        return DEFAULT3;
    }
}
