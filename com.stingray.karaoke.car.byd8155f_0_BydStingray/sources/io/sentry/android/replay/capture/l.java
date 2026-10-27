package io.sentry.android.replay.capture;

import A.C0044v0;
import S0.p;
import android.view.MotionEvent;
import io.sentry.G1;
import io.sentry.android.replay.u;
import io.sentry.protocol.t;

/* JADX INFO: loaded from: classes.dex */
public interface l {
    void a(boolean z6, p pVar);

    void b();

    void c(C0044v0 c0044v0);

    void d(MotionEvent motionEvent);

    void e(u uVar);

    void f(u uVar, int i7, t tVar, G1 g12);

    l g();

    void stop();
}
