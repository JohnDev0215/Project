package a.b.a.a.o.d.b;

import a.b.a.a.o.d.b.a;
import a.b.a.a.o.d.b.m;
import android.media.MediaCodec;

/* JADX INFO: loaded from: classes.dex */
public final class n implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ m f128a;
    public final /* synthetic */ MediaCodec b;
    public final /* synthetic */ int c;
    public final /* synthetic */ MediaCodec.BufferInfo d;
    public final /* synthetic */ a.g e;

    public n(m mVar, MediaCodec mediaCodec, int i, MediaCodec.BufferInfo bufferInfo, a.g gVar) {
        this.f128a = mVar;
        this.b = mediaCodec;
        this.c = i;
        this.d = bufferInfo;
        this.e = gVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f128a.k.addLast(new m.c(this.b, this.c, this.d, this.e));
        this.f128a.a();
    }
}
