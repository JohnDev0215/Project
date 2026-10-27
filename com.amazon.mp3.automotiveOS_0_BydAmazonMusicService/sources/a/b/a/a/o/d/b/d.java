package a.b.a.a.o.d.b;

import a.b.a.a.o.d.b.a;
import android.media.MediaCodec;
import java.util.concurrent.ScheduledExecutorService;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class d implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a f113a;
    public final /* synthetic */ MediaCodec b;
    public final /* synthetic */ long c;
    public final /* synthetic */ long d;

    public d(a aVar, MediaCodec mediaCodec, long j, long j2) {
        this.f113a = aVar;
        this.b = mediaCodec;
        this.c = j;
        this.d = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        a aVar = this.f113a;
        if (aVar.d != a.e.EXECUTING) {
            aVar.c.a(aVar.b, "Received available output buffer in state: " + this.f113a.d.name());
            return;
        }
        if (!Intrinsics.areEqual(this.b, aVar.k)) {
            a aVar2 = this.f113a;
            aVar2.c.a(aVar2.b, "Received available output buffer from old decoder");
            return;
        }
        m mVar = this.f113a.l;
        long j = this.c;
        long j2 = this.d;
        if (mVar.i) {
            ScheduledExecutorService renderThread = mVar.e;
            Intrinsics.checkExpressionValueIsNotNull(renderThread, "renderThread");
            mVar.a(renderThread, new o(mVar, j, j2));
        }
    }
}
