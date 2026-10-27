package io.sentry.android.core;

import android.view.View;
import io.sentry.EnumC1495p1;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import k3.C1639f;
import k3.InterfaceC1636e;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d0 implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f16159m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f16160n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f16161p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f16162q;

    public /* synthetic */ d0(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, int i7) {
        this.l = i7;
        this.f16159m = obj;
        this.f16160n = obj2;
        this.o = obj3;
        this.f16161p = obj4;
        this.f16162q = obj5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.l) {
            case 0:
                AtomicReference atomicReference = (AtomicReference) this.f16159m;
                View view = (View) this.f16160n;
                List list = (List) this.o;
                CountDownLatch countDownLatch = (CountDownLatch) this.f16161p;
                try {
                    ArrayList arrayList = new ArrayList(1);
                    io.sentry.protocol.F f10 = new io.sentry.protocol.F("android_view_system", arrayList);
                    io.sentry.protocol.G gC = ViewHierarchyEventProcessor.c(view);
                    arrayList.add(gC);
                    ViewHierarchyEventProcessor.a(view, gC, list);
                    atomicReference.set(f10);
                    countDownLatch.countDown();
                } catch (Throwable th) {
                    ((io.sentry.K) this.f16162q).s(EnumC1495p1.ERROR, "Failed to process view hierarchy.", th);
                    return;
                }
                break;
            default:
                B8.t tVar = (B8.t) this.f16159m;
                tVar.getClass();
                ((InterfaceC1636e) this.f16160n).run().b(new io.sentry.android.replay.p(tVar, (AtomicBoolean) this.o, (C1639f) this.f16161p, (AtomicBoolean) this.f16162q, 1), h5.r.l);
                break;
        }
    }
}
