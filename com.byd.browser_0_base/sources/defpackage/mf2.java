package defpackage;

import J.N;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.CancellationSignal;
import android.util.Size;
import org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.c;

/* JADX INFO: compiled from: ScrollCaptureCallbackDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class mf2 implements c.a {
    public final /* synthetic */ CancellationSignal a;
    public final /* synthetic */ Runnable b;
    public final /* synthetic */ nf2 c;

    public mf2(nf2 nf2Var, CancellationSignal cancellationSignal, Runnable runnable) {
        this.c = nf2Var;
        this.a = cancellationSignal;
        this.b = runnable;
    }

    @Override // org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.c.a
    public final void a(int i) {
        if (i == 5 || i == 4) {
            return;
        }
        nf2 nf2Var = this.c;
        nf2Var.c.d.c(this);
        nf2Var.c.b();
        this.a.cancel();
        N.M9gwtxem();
        if (i == 1) {
            oj2.U(1, 3, "Sharing.ScrollCapture.BitmapGeneratorStatus");
        } else {
            oj2.U(2, 3, "Sharing.ScrollCapture.BitmapGeneratorStatus");
        }
    }

    @Override // org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.c.a
    public final void b(Size size, Point point) {
        nf2 nf2Var = this.c;
        nf2Var.c.d.c(this);
        if (size.getWidth() == 0 || size.getHeight() == 0) {
            nf2Var.c.b();
            this.a.cancel();
            oj2.U(2, 3, "Sharing.ScrollCapture.BitmapGeneratorStatus");
        } else {
            nf2Var.d = new Rect(0, 0, (int) Math.floor(size.getWidth() * nf2Var.f), (int) Math.floor(size.getHeight() * nf2Var.f));
            nf2Var.e = (int) Math.floor(point.y * nf2Var.f);
            oj2.U(0, 3, "Sharing.ScrollCapture.BitmapGeneratorStatus");
            this.b.run();
        }
    }
}
