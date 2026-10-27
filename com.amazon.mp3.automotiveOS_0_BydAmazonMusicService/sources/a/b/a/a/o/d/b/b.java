package a.b.a.a.o.d.b;

import a.b.a.a.o.d.b.a;
import android.media.MediaCodec;
import com.netinsight.sye.syeClient.view.ISyeDisplaySettings;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a f111a;
    public final /* synthetic */ ISyeDisplaySettings.VideoGravity b;

    public b(a aVar, ISyeDisplaySettings.VideoGravity videoGravity) {
        this.f111a = aVar;
        this.b = videoGravity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ISyeDisplaySettings.VideoGravity videoGravity = this.b;
        if (videoGravity != null) {
            this.f111a.t = videoGravity;
        }
        a aVar = this.f111a;
        ISyeDisplaySettings.VideoGravity videoGravity2 = aVar.t;
        aVar.c.a(aVar.b, "Setting video scaling mode: " + videoGravity2.name());
        a aVar2 = this.f111a;
        if (aVar2.d != a.e.EXECUTING) {
            aVar2.c.a(aVar2.b, "Attempting to set video scaling mode in state: " + this.f111a.d.name());
            return;
        }
        MediaCodec mediaCodec = aVar2.k;
        if (mediaCodec == null) {
            aVar2.c.a(aVar2.b, "Attempting to set video scaling mode on null decoder");
            return;
        }
        try {
            mediaCodec.setVideoScalingMode(videoGravity2 == ISyeDisplaySettings.VideoGravity.ASPECT_FILL ? 2 : 1);
            a aVar3 = this.f111a;
            aVar3.c.a(aVar3.b, "Updated video scaling mode to " + videoGravity2.name());
        } catch (Exception unused) {
            a aVar4 = this.f111a;
            aVar4.c.e(aVar4.b, "Failed updating video scaling mode to " + videoGravity2.name());
        }
    }
}
