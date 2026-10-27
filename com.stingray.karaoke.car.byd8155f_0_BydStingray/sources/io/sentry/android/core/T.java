package io.sentry.android.core;

import io.sentry.EnumC1469h;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import io.sentry.Q0;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class T implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f16111m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f16112n;
    public final /* synthetic */ Object o;

    public /* synthetic */ T(Object obj, Object obj2, Object obj3, int i7) {
        this.l = i7;
        this.f16111m = obj;
        this.f16112n = obj2;
        this.o = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) this.f16112n;
                SendCachedEnvelopeIntegration sendCachedEnvelopeIntegration = (SendCachedEnvelopeIntegration) this.f16111m;
                sendCachedEnvelopeIntegration.getClass();
                try {
                    if (sendCachedEnvelopeIntegration.f16102t.get()) {
                        sentryAndroidOptions.getLogger().k(EnumC1495p1.INFO, "SendCachedEnvelopeIntegration, not trying to send after closing.", new Object[0]);
                        return;
                    }
                    boolean andSet = sendCachedEnvelopeIntegration.f16101s.getAndSet(true);
                    io.sentry.E e10 = (io.sentry.E) this.o;
                    if (!andSet) {
                        io.sentry.H connectionStatusProvider = sentryAndroidOptions.getConnectionStatusProvider();
                        sendCachedEnvelopeIntegration.o = connectionStatusProvider;
                        connectionStatusProvider.f(sendCachedEnvelopeIntegration);
                        sendCachedEnvelopeIntegration.f16100r = sendCachedEnvelopeIntegration.l.a(e10, sentryAndroidOptions);
                    }
                    io.sentry.H h10 = sendCachedEnvelopeIntegration.o;
                    if (h10 != null && h10.e() == io.sentry.F.DISCONNECTED) {
                        sentryAndroidOptions.getLogger().k(EnumC1495p1.INFO, "SendCachedEnvelopeIntegration, no connection.", new Object[0]);
                        return;
                    }
                    io.sentry.transport.m mVarD = e10.d();
                    if (mVarD != null && mVarD.g(EnumC1469h.All)) {
                        sentryAndroidOptions.getLogger().k(EnumC1495p1.INFO, "SendCachedEnvelopeIntegration, rate limiting active.", new Object[0]);
                        return;
                    }
                    Q0 q02 = sendCachedEnvelopeIntegration.f16100r;
                    if (q02 == null) {
                        sentryAndroidOptions.getLogger().k(EnumC1495p1.ERROR, "SendCachedEnvelopeIntegration factory is null.", new Object[0]);
                        return;
                    } else {
                        q02.a();
                        return;
                    }
                } catch (Throwable th) {
                    sentryAndroidOptions.getLogger().s(EnumC1495p1.ERROR, "Failed trying to send cached events.", th);
                    return;
                }
            case 1:
                Runnable runnable = (Runnable) this.f16112n;
                N0.q qVar = (N0.q) this.f16111m;
                qVar.getClass();
                try {
                    runnable.run();
                    return;
                } catch (Throwable unused) {
                    String str = (String) this.o;
                    if (str != null) {
                        ((SentryAndroidOptions) qVar.f4400n).getLogger().k(EnumC1495p1.WARNING, "Failed to execute ".concat(str), new Object[0]);
                        return;
                    }
                    return;
                }
            default:
                EnvelopeFileObserverIntegration envelopeFileObserverIntegration = (EnvelopeFileObserverIntegration) this.f16111m;
                F1 f12 = (F1) this.f16112n;
                String str2 = (String) this.o;
                synchronized (envelopeFileObserverIntegration.o) {
                    try {
                        if (!envelopeFileObserverIntegration.f16049n) {
                            envelopeFileObserverIntegration.b(f12, str2);
                        }
                    } finally {
                    }
                    break;
                }
                return;
        }
    }
}
