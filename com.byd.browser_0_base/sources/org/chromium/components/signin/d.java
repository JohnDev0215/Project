package org.chromium.components.signin;

import defpackage.i9;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.ThreadUtils;
import org.chromium.net.NetworkChangeNotifier;

/* JADX INFO: compiled from: ConnectionRetry.java */
/* JADX INFO: loaded from: classes3.dex */
public final class d<T> implements NetworkChangeNotifier.a {
    public final a<T> e;
    public final AtomicInteger f = new AtomicInteger(0);
    public final AtomicBoolean g = new AtomicBoolean(false);

    public d(a<T> aVar) {
        this.e = aVar;
    }

    @Override // org.chromium.net.NetworkChangeNotifier.a
    public final void d(int i) {
        if (NetworkChangeNotifier.a()) {
            NetworkChangeNotifier.f(this);
            Object obj = ThreadUtils.a;
            this.g.set(false);
            new c(this).c(i9.e);
        }
    }

    /* JADX INFO: compiled from: ConnectionRetry.java */
    public interface a<T> {
        T run();

        default void a(T t) {
        }

        default void b(boolean z) {
        }
    }
}
