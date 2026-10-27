package s2;

import android.media.LoudnessCodecController$OnLoudnessCodecUpdateListener;
import android.media.MediaCodec;
import android.os.Bundle;
import h3.y;

/* JADX INFO: loaded from: classes.dex */
public final class h implements LoudnessCodecController$OnLoudnessCodecUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ y f22093a;

    public h(y yVar) {
        this.f22093a = yVar;
    }

    public final Bundle onLoudnessCodecUpdate(MediaCodec mediaCodec, Bundle bundle) {
        ((i) this.f22093a.f15500n).getClass();
        return bundle;
    }
}
