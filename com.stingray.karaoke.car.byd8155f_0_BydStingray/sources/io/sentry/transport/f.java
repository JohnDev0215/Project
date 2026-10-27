package io.sentry.transport;

import io.sentry.C1489n1;
import io.sentry.C1523x;
import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
public interface f extends Closeable {
    void a(boolean z6);

    m d();

    default boolean f() {
        return true;
    }

    void h(long j3);

    void n(C1489n1 c1489n1, C1523x c1523x);
}
