package defpackage;

import android.os.Build;
import android.os.Looper;
import android.view.SurfaceHolder;

/* JADX INFO: renamed from: yA, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SurfaceHolderCallback2C1807yA implements SurfaceHolder.Callback2 {
    public final /* synthetic */ AA a;

    public SurfaceHolderCallback2C1807yA(AA aa, AbstractC1752xA abstractC1752xA) {
        this.a = aa;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        InterfaceC1862zA interfaceC1862zA;
        this.a.a();
        AA aa = this.a;
        if (aa.b == null || (interfaceC1862zA = aa.a) == null) {
            return;
        }
        interfaceC1862zA.A(surfaceHolder.getSurface());
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        AA aa = this.a;
        if (aa.b == null || aa.a == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28) {
            aa.a();
        } else if (aa.f != Looper.myLooper()) {
            Ts.a("DSCore", "surfaceDestroyed called on wrong thread.  Avoiding proper shutdown.", new Object[0]);
            this.a.a.x();
            return;
        }
        this.a.a.x();
        this.a.a.M();
        this.a.a.G();
        this.a.a = null;
    }

    @Override // android.view.SurfaceHolder.Callback2
    public void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
    }
}
