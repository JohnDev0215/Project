package F0;

import S8.C0712i;
import android.view.Choreographer;
import h8.AbstractC1386c;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: F0.h0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ChoreographerFrameCallbackC0206h0 implements Choreographer.FrameCallback {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ C0712i f2208m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f2209n;

    public ChoreographerFrameCallbackC0206h0(C0712i c0712i, C0208i0 c0208i0, InterfaceC2782k interfaceC2782k) {
        this.f2208m = c0712i;
        this.f2209n = interfaceC2782k;
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j3) {
        Object objF;
        Object objF2;
        C0712i c0712i = this.f2208m;
        InterfaceC2782k interfaceC2782k = this.f2209n;
        switch (this.l) {
            case 0:
                try {
                    objF = interfaceC2782k.b(Long.valueOf(j3));
                } catch (Throwable th) {
                    objF = AbstractC1386c.f(th);
                }
                c0712i.resumeWith(objF);
                break;
            default:
                T.B b10 = T.B.l;
                try {
                    objF2 = interfaceC2782k.b(Long.valueOf(j3));
                } catch (Throwable th2) {
                    objF2 = AbstractC1386c.f(th2);
                }
                c0712i.resumeWith(objF2);
                break;
        }
    }

    public ChoreographerFrameCallbackC0206h0(C0712i c0712i, InterfaceC2782k interfaceC2782k) {
        this.f2208m = c0712i;
        this.f2209n = interfaceC2782k;
    }
}
