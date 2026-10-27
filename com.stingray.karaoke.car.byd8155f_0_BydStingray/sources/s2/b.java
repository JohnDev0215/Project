package s2;

import E0.X;
import android.media.MediaCodec;
import android.os.Handler;
import android.os.Message;
import h2.AbstractC1343A;
import l8.C1789g;
import z2.C2758d;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements MediaCodec.OnFrameRenderedListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f22067a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C2758d f22068b;
    public final /* synthetic */ k c;

    public /* synthetic */ b(k kVar, C2758d c2758d, int i7) {
        this.f22067a = i7;
        this.c = kVar;
        this.f22068b = c2758d;
    }

    @Override // android.media.MediaCodec.OnFrameRenderedListener
    public final void onFrameRendered(MediaCodec mediaCodec, long j3, long j10) {
        switch (this.f22067a) {
            case 0:
                ((X) this.c).getClass();
                C2758d c2758d = this.f22068b;
                c2758d.getClass();
                if (AbstractC1343A.f15195a >= 30) {
                    c2758d.a(j3);
                } else {
                    Handler handler = c2758d.l;
                    handler.sendMessageAtFrontOfQueue(Message.obtain(handler, 0, (int) (j3 >> 32), (int) j3));
                }
                break;
            default:
                ((C1789g) this.c).getClass();
                C2758d c2758d2 = this.f22068b;
                c2758d2.getClass();
                if (AbstractC1343A.f15195a >= 30) {
                    c2758d2.a(j3);
                } else {
                    Handler handler2 = c2758d2.l;
                    handler2.sendMessageAtFrontOfQueue(Message.obtain(handler2, 0, (int) (j3 >> 32), (int) j3));
                }
                break;
        }
    }
}
