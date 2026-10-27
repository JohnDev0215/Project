package s2;

import E0.X;
import android.os.HandlerThread;
import c5.InterfaceC1031h;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c implements InterfaceC1031h {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f22069m;

    public /* synthetic */ c(int i7, int i10) {
        this.l = i10;
        this.f22069m = i7;
    }

    @Override // c5.InterfaceC1031h
    public final Object get() {
        switch (this.l) {
            case 0:
                return new HandlerThread(X.t(this.f22069m, "ExoPlayer:MediaCodecAsyncAdapter:"));
            default:
                return new HandlerThread(X.t(this.f22069m, "ExoPlayer:MediaCodecQueueingThread:"));
        }
    }
}
