package a.b.a.a.o.d.b;

import android.media.MediaCodec;
import java.util.concurrent.ExecutorService;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class g implements MediaCodec.OnFrameRenderedListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a f116a;

    public g(a aVar) {
        this.f116a = aVar;
    }

    @Override // android.media.MediaCodec.OnFrameRenderedListener
    public final void onFrameRendered(MediaCodec mediaCodec, long j, long j2) {
        a aVar = this.f116a;
        aVar.getClass();
        ExecutorService decodeThread = aVar.n;
        Intrinsics.checkExpressionValueIsNotNull(decodeThread, "decodeThread");
        aVar.a(decodeThread, new d(aVar, mediaCodec, j, j2));
    }
}
