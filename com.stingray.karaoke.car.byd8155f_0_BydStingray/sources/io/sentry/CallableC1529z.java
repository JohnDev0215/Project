package io.sentry;

import java.net.InetAddress;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: io.sentry.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class CallableC1529z implements Callable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f16992a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f16993b;

    public /* synthetic */ CallableC1529z(Object obj, int i7) {
        this.f16992a = i7;
        this.f16993b = obj;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        switch (this.f16992a) {
            case 0:
                B b10 = (B) this.f16993b;
                b10.getClass();
                try {
                    b10.f15821e.getClass();
                    b10.f15820b = InetAddress.getLocalHost().getCanonicalHostName();
                    b10.c = System.currentTimeMillis() + b10.f15819a;
                    b10.d.set(false);
                    return null;
                } catch (Throwable th) {
                    b10.d.set(false);
                    throw th;
                }
            default:
                StringBuilder sb = new StringBuilder();
                for (Map.Entry entry : ((io.sentry.metrics.a) this.f16993b).f16616a.entrySet()) {
                    ((Long) entry.getKey()).getClass();
                    Collection collectionValues = ((Map) entry.getValue()).values();
                    int i7 = io.sentry.metrics.c.f16619b;
                    Iterator it = collectionValues.iterator();
                    if (it.hasNext()) {
                        it.next().getClass();
                        throw new ClassCastException();
                    }
                }
                return sb.toString().getBytes(io.sentry.metrics.a.f16615b);
        }
    }
}
