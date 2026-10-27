package defpackage;

import java.util.concurrent.Executor;
import java.util.function.IntConsumer;
import org.chromium.base.ThreadUtils;

/* JADX INFO: compiled from: OngoingGesture.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ss1 {
    public static int f;
    public final int a;
    public final xv2 b;
    public final Executor c;
    public final IntConsumer d;
    public final long e;

    public ss1(xv2 xv2Var, Executor executor, IntConsumer intConsumer) {
        Object obj = ThreadUtils.a;
        int i = f + 1;
        f = i;
        this.a = i;
        this.b = xv2Var;
        this.c = executor;
        this.d = intConsumer;
        this.e = System.currentTimeMillis();
    }

    public final void a(int i) {
        Executor executor = this.c;
        if (executor == null || this.d == null) {
            oj2.U(0, 5, "InputMethod.StylusHandwriting.GestureResult");
            return;
        }
        executor.execute(new sb2(i, 7, this));
        oj2.U(i, 5, "InputMethod.StylusHandwriting.GestureResult");
        oj2.T("InputMethod.StylusHandwriting.GestureTime", System.currentTimeMillis() - this.e, 10L, 1000L, 50);
    }
}
