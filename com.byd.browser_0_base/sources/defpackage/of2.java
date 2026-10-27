package defpackage;

import J.N;
import android.graphics.Rect;
import android.os.CancellationSignal;
import android.os.SystemClock;
import android.view.ScrollCaptureCallback;
import android.view.ScrollCaptureSession;
import android.view.Surface;
import defpackage.nf2;
import java.util.function.Consumer;
import org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.c;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: ScrollCaptureCallbackImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class of2 implements ScrollCaptureCallback {
    public final nf2 a;

    public of2(nf2.a aVar) {
        this.a = new nf2(aVar);
    }

    @Override // android.view.ScrollCaptureCallback
    public final void onScrollCaptureEnd(Runnable runnable) {
        nf2 nf2Var = this.a;
        nf2Var.getClass();
        N.M9gwtxem();
        c cVar = nf2Var.c;
        if (cVar != null) {
            cVar.b();
            nf2Var.c = null;
        }
        if (nf2Var.g != 0) {
            oj2.b0(SystemClock.elapsedRealtime() - nf2Var.g, "Sharing.ScrollCapture.SuccessfulCaptureDuration");
        }
        nf2Var.g = 0L;
        nf2Var.d = null;
        nf2Var.e = 0;
        nf2Var.f = 1.0f;
        runnable.run();
    }

    @Override // android.view.ScrollCaptureCallback
    public final void onScrollCaptureImageRequest(ScrollCaptureSession scrollCaptureSession, CancellationSignal cancellationSignal, Rect rect, Consumer<Rect> consumer) {
        nf2 nf2Var = this.a;
        Surface surface = scrollCaptureSession.getSurface();
        on0 on0Var = new on0(12, consumer);
        rect.offset(0, nf2Var.e);
        if (!rect.intersect(nf2Var.d) || rect.height() < 20) {
            on0Var.onResult(new Rect());
            return;
        }
        c cVar = nf2Var.c;
        pa1 pa1Var = new pa1(cVar.c, rect, new ps(13));
        cVar.c(pa1Var, true);
        pa1Var.e = new lf2(nf2Var, pa1Var, on0Var, rect, surface);
        int i = pa1Var.c;
        if (i != 0) {
            pa1Var.b(i);
        }
    }

    @Override // android.view.ScrollCaptureCallback
    public final void onScrollCaptureSearch(CancellationSignal cancellationSignal, Consumer<Rect> consumer) {
        Rect rect;
        nf2 nf2Var = this.a;
        WebContents webContentsC = nf2Var.b.c();
        if (nf2Var.b.getView() == null || webContentsC == null || nf2Var.b.isFrozen()) {
            rect = new Rect();
        } else {
            za2 za2Var = ((WebContentsImpl) webContentsC).l;
            rect = new Rect(0, 0, (int) Math.ceil(za2Var.a(za2Var.e)), (int) Math.ceil(za2Var.a(za2Var.f)));
            nf2Var.f = za2Var.h;
        }
        consumer.accept(rect);
    }

    @Override // android.view.ScrollCaptureCallback
    public final void onScrollCaptureStart(ScrollCaptureSession scrollCaptureSession, CancellationSignal cancellationSignal, Runnable runnable) {
        nf2 nf2Var = this.a;
        nf2Var.getClass();
        nf2Var.g = SystemClock.elapsedRealtime();
        Tab tab = nf2Var.b;
        nf2Var.a.getClass();
        c cVar = new c(tab.getContext(), tab, true);
        nf2Var.c = cVar;
        cVar.a(new mf2(nf2Var, cancellationSignal, runnable));
        N.MJ3oAy5s();
    }
}
