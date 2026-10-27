package M;

import F0.W0;
import K.C0331b0;
import O.K;
import android.os.CancellationSignal;
import android.view.inputmethod.HandwritingGesture;
import android.view.inputmethod.PreviewableHandwritingGesture;
import java.util.concurrent.Executor;
import java.util.function.IntConsumer;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f4141a = new h();

    public final void a(C0331b0 c0331b0, K k10, HandwritingGesture handwritingGesture, W0 w02, Executor executor, IntConsumer intConsumer, InterfaceC2782k interfaceC2782k) {
        int i7 = c0331b0 != null ? r.f4149a.i(c0331b0, handwritingGesture, k10, w02, interfaceC2782k) : 3;
        if (intConsumer == null) {
            return;
        }
        if (executor != null) {
            executor.execute(new g(i7, 0, intConsumer));
        } else {
            intConsumer.accept(i7);
        }
    }

    public final boolean b(C0331b0 c0331b0, K k10, PreviewableHandwritingGesture previewableHandwritingGesture, CancellationSignal cancellationSignal) {
        if (c0331b0 != null) {
            return r.f4149a.A(c0331b0, previewableHandwritingGesture, k10, cancellationSignal);
        }
        return false;
    }
}
