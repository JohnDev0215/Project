package K0;

import A.F;
import L0.o;
import S8.AbstractC0727y;
import S8.g0;
import S8.k0;
import android.graphics.Rect;
import android.os.CancellationSignal;
import android.view.ScrollCaptureCallback;
import android.view.ScrollCaptureSession;
import java.util.function.Consumer;
import n0.AbstractC1861K;

/* JADX INFO: loaded from: classes.dex */
public final class f implements ScrollCaptureCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final o f3493a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a1.i f3494b;
    public final l c;
    public final X8.c d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final j f3495e;

    public f(o oVar, a1.i iVar, X8.c cVar, l lVar) {
        this.f3493a = oVar;
        this.f3494b = iVar;
        this.c = lVar;
        this.d = new X8.c(cVar.l.v(h.l));
        this.f3495e = new j(iVar.d - iVar.f11036b, new e(this, null));
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object a(K0.f r11, android.view.ScrollCaptureSession r12, a1.i r13, s7.AbstractC2410c r14) {
        /*
            Method dump skipped, instruction units count: 368
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: K0.f.a(K0.f, android.view.ScrollCaptureSession, a1.i, s7.c):java.lang.Object");
    }

    public final void onScrollCaptureEnd(Runnable runnable) {
        AbstractC0727y.w(this.d, g0.f8113m, null, new a(this, runnable, null), 2);
    }

    public final void onScrollCaptureImageRequest(ScrollCaptureSession scrollCaptureSession, CancellationSignal cancellationSignal, Rect rect, Consumer consumer) {
        k0 k0VarW = AbstractC0727y.w(this.d, null, null, new b(this, scrollCaptureSession, rect, consumer, null), 3);
        k0VarW.z(new F(cancellationSignal, 22));
        cancellationSignal.setOnCancelListener(new g(k0VarW, 0));
    }

    public final void onScrollCaptureSearch(CancellationSignal cancellationSignal, Consumer consumer) {
        consumer.accept(AbstractC1861K.A(this.f3494b));
    }

    public final void onScrollCaptureStart(ScrollCaptureSession scrollCaptureSession, CancellationSignal cancellationSignal, Runnable runnable) {
        this.f3495e.f3501b = 0.0f;
        l lVar = this.c;
        lVar.f3503a.setValue(Boolean.TRUE);
        runnable.run();
    }
}
