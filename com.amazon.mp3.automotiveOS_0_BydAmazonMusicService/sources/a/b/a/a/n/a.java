package a.b.a.a.n;

import com.netinsight.sye.syeClient.audio.SyeAudioCodec;
import com.netinsight.sye.syeClient.audio.SyeAudioCodecProfile;
import com.netinsight.sye.syeClient.generated.enums.AudioCodecProfile;
import com.netinsight.sye.syeClient.video.SyeVideoSample;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final a.b.a.a.o.b a(a.b.a.a.k.e.c cVar) {
        int iOrdinal = cVar.ordinal();
        return iOrdinal != 1 ? iOrdinal != 2 ? a.b.a.a.o.b.Undefined : a.b.a.a.o.b.HEVC : a.b.a.a.o.b.AVC;
    }

    public static final SyeAudioCodec a(a.b.a.a.k.e.a aVar) {
        int iOrdinal = aVar.ordinal();
        return iOrdinal != 1 ? iOrdinal != 2 ? SyeAudioCodec.Undefined : SyeAudioCodec.EC3 : SyeAudioCodec.AAC;
    }

    public static final SyeAudioCodecProfile a(AudioCodecProfile audioCodecProfile) {
        int iOrdinal = audioCodecProfile.ordinal();
        return iOrdinal != 1 ? iOrdinal != 2 ? iOrdinal != 3 ? SyeAudioCodecProfile.Undefined : SyeAudioCodecProfile.EC3 : SyeAudioCodecProfile.EC3_Atmos : SyeAudioCodecProfile.AAC_LC;
    }

    public static final Integer a(List<SyeVideoSample> list, long j) {
        int iNextIndex;
        ListIterator<SyeVideoSample> listIterator = list.listIterator(list.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                iNextIndex = -1;
                break;
            }
            SyeVideoSample syeVideoSamplePrevious = listIterator.previous();
            if (syeVideoSamplePrevious.getLocalTimeMicros() < j && syeVideoSamplePrevious.getIsIDRFrame()) {
                iNextIndex = listIterator.nextIndex();
                break;
            }
        }
        Integer numValueOf = Integer.valueOf(iNextIndex);
        if (numValueOf.intValue() >= 0) {
            return numValueOf;
        }
        return null;
    }
}
